package com.misys.equation.bf;

import bf.com.misys.eqf.types.header.EsfKey;
import bf.com.misys.eqf.types.header.UpdateEsfStatusRqHeader;
import bf.com.misys.eqf.types.header.UpdateEsfStatusRsHeader;

import com.misys.bf.autogen.AbstractEQ_CMN_UpdateEsfStatus;
import com.misys.equation.bankfusion.lrp.engine.TaskEngineToolbox;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IWE2RecordDao;
import com.misys.equation.common.dao.IWERecordDao;
import com.misys.equation.common.dao.beans.WE2RecordDataModel;
import com.misys.equation.common.dao.beans.WERecordDataModel;
import com.misys.equation.common.language.LanguageResources;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;
import com.trapedza.bankfusion.core.BankFusionException;
import com.trapedza.bankfusion.servercommon.commands.BankFusionEnvironment;

public class EQ_CMN_UpdateEsfStatus extends AbstractEQ_CMN_UpdateEsfStatus
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQ_CMN_UpdateEsfStatus.java 17189 2013-09-03 11:49:03Z Lima12 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EQ_CMN_UpdateEsfStatus.class);

	/**
	 * Constructor
	 * 
	 * @param env
	 *            - the BankFusion environment
	 * 
	 * @throws BankFusionException
	 */
	public EQ_CMN_UpdateEsfStatus(BankFusionEnvironment env) throws BankFusionException
	{
	}

	/**
	 * Main processing
	 * 
	 * @param env
	 *            a BankFusionEnvironment
	 */
	@Override
	public void process(BankFusionEnvironment env)
	{
		UpdateEsfStatusRqHeader input = getF_IN_input();

		// Retrieve the WE key
		EsfKey esfKey = input.getEsfKey();
		if (esfKey == null)
		{
			setErrorMessage(4);
			return;
		}

		String weOptionId = esfKey.getOptionId();
		String weSessionId = esfKey.getSessionId();
		String weTransactionId = esfKey.getTransactionId();
		String weUserId = esfKey.getUserId();

		// Retrieve login details
		String systemId = input.getSystemId();
		String unitId = input.getUnitId();
		String userId = input.getUserId();

		// User id is blank, then default to the BankFusion user id
		if (userId == null || userId.trim().length() == 0)
		{
			userId = env.getUserSession().getUserId();
		}

		// Login details must be specified
		if (systemId == null || systemId.trim().length() == 0)
		{
			setErrorMessage(5);
			return;
		}
		if (unitId == null || unitId.trim().length() == 0)
		{
			setErrorMessage(6);
			return;
		}
		if (userId == null || userId.trim().length() == 0)
		{
			setErrorMessage(7);
			return;
		}

		// Retrieve details
		String reason = input.getReason();
		String action = input.getAction();

		// Any null values, then default to blank
		if (weOptionId == null)
		{
			weOptionId = "";
		}
		if (weSessionId == null)
		{
			weSessionId = "";
		}
		if (weTransactionId == null)
		{
			weTransactionId = "";
		}
		if (weUserId == null)
		{
			weUserId = "";
		}
		if (reason == null)
		{
			reason = "";
		}
		if (action == null)
		{
			action = "";
		}

		// Clear return messages
		UpdateEsfStatusRsHeader output = getF_OUT_output();
		output.setErrorCode("");
		output.setErrorMessage("");

		// Invalid action?
		if (!action.equalsIgnoreCase(TaskEngineToolbox.TASK_ACTION_AUTH)
						&& !action.equalsIgnoreCase(TaskEngineToolbox.TASK_ACTION_DECL)
						&& !action.equalsIgnoreCase(TaskEngineToolbox.TASK_ACTION_CANCEL)
						&& !action.equalsIgnoreCase(TaskEngineToolbox.TASK_ACTION_ONE)
						&& !action.equalsIgnoreCase(TaskEngineToolbox.TASK_ACTION_REVIEW))
		{
			setErrorMessage(0);
			return;
		}

		EquationStandardSession sessionFromPool = null;
		EquationUnit unit = null;
		boolean rollback = true;
		try
		{

			unit = EquationCommonContext.getContext().getEquationUnit(systemId, unitId);

			sessionFromPool = FunctionRuntimeToolbox.rtvEquationSessionFromPool(unit);

			// Retrieve the WE record
			WERecordDataModel weRecord = getWERecord(sessionFromPool, weOptionId, weSessionId, weTransactionId, weUserId);
			if (weRecord == null)
			{
				setErrorMessage(2);
				return;
			}

			// Update the WE record
			updateWERecord(sessionFromPool, weRecord, action, reason, input.getUserId());

			LOG.info("Committing updating");
			sessionFromPool.commitTransaction();
			rollback = false;

			// Completed
			LOG.info("Completed");
		}
		catch (Exception e)
		{
			LOG.error(e);

			setErrorMessage(3);
			return;
		}
		finally
		{
			try
			{
				if (rollback && sessionFromPool != null)
				{
					LOG.info("Rollback transactions");
					sessionFromPool.rollbackTransactions();
				}
			}
			catch (Exception e2)
			{
				LOG.error(e2);
			}

			if (sessionFromPool != null)
			{
				FunctionRuntimeToolbox.returnEquationSessionToPool(unit, sessionFromPool);
			}
		}
	}

	/**
	 * Retrieve the WE record
	 * 
	 * @param session
	 *            - the Equation Standard Session
	 * @param weOptionId
	 *            - the option id
	 * @param weSessionId
	 *            - the session id
	 * @param weTransactionId
	 *            - the transaction id
	 * @param weUserId
	 *            - the user id
	 * 
	 * @return the WE record
	 */
	private WERecordDataModel getWERecord(EquationStandardSession session, String weOptionId, String weSessionId,
					String weTransactionId, String weUserId)
	{
		// Retrieve the WE record
		LOG.info("WE record: " + "[" + weOptionId + "]" + "[" + weSessionId + "]" + "[" + weTransactionId + "]" + "[" + weUserId
						+ "]");

		LOG.info("Retrieving WE record");
		WERecordDataModel weRecord = new WERecordDataModel(weOptionId, weSessionId, weTransactionId, weUserId);
		DaoFactory daoFactory = new DaoFactory();
		IWERecordDao weDao = daoFactory.getWEDao(session, weRecord);
		weRecord = weDao.getWERecord();
		return weRecord;
	}

	/**
	 * Update WE record
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param weRecord
	 *            - the WE record
	 * @param action
	 *            - action
	 * @param reason
	 *            - reason
	 * @param supervisorId
	 *            - the supervisor
	 * 
	 * @throws Exception
	 */
	private void updateWERecord(EquationStandardSession session, WERecordDataModel weRecord, String action, String reason,
					String supervisorId) throws Exception
	{
		// Update the record
		LOG.info("Updating WE record: " + action);

		// update details
		weRecord.setAuthStat(convertAction(action));
		weRecord.setReasonRejection(reason);
		weRecord.setAuthLevel(WERecordDataModel.LVL_ALL);

		// alert the teller - except cancellation
		if (!action.equalsIgnoreCase(TaskEngineToolbox.TASK_ACTION_CANCEL))
		{
			weRecord.setUserAlerted(EqDataType.NO);
		}

		// authorised first warning only?
		if (action.equalsIgnoreCase(TaskEngineToolbox.TASK_ACTION_ONE))
		{
			weRecord.setAuthLevel(WERecordDataModel.LVL_ONE);
		}

		// supervisor id specified?
		if (supervisorId != null && supervisorId.trim().length() > 0)
		{
			weRecord.setAuthorisor(supervisorId);
		}

		// update
		DaoFactory daoFactory = new DaoFactory();
		IWERecordDao weDao = daoFactory.getWEDao(session, weRecord);
		weDao.updateRecord(weRecord);

		// remove we2 record
		WE2RecordDataModel we2Record = new WE2RecordDataModel(weRecord.getSessionId(), weRecord.getUserId(),
						weRecord.getOptionId(), weRecord.getTransactionId());
		IWE2RecordDao we2Dao = daoFactory.getWE2Dao(session, we2Record);
		we2Dao.deleteRecord();
	}

	/**
	 * Set the return error
	 * 
	 * @param errorCode
	 *            - the error code
	 */
	private void setErrorMessage(int errorCode)
	{
		// retrieve the language key
		String key = getLanguageKey(errorCode);

		// retrieve text and log
		String msg = LanguageResources.getString(key);
		LOG.error(key + ": " + msg);

		// set return error
		UpdateEsfStatusRsHeader output = getF_OUT_output();
		output.setErrorCode(String.valueOf(errorCode));
		output.setErrorMessage(msg);
	}

	/**
	 * Return the language key
	 * 
	 * @param errorCode
	 *            - the error code
	 * 
	 * @return the language key for the error code
	 */
	private String getLanguageKey(int errorCode)
	{
		String key = "";
		if (errorCode == 0)
		{
			key = "EQ_CMN_UpdateEsfStatus.InvalidAction";
		}
		else if (errorCode == 1)
		{
			key = "EQ_CMN_UpdateEsfStatus.UnableEstablishConnection";
		}
		else if (errorCode == 2)
		{
			key = "EQ_CMN_UpdateEsfStatus.RecordNotFound";
		}
		else if (errorCode == 3)
		{
			key = "EQ_CMN_UpdateEsfStatus.UnknownError";
		}
		else if (errorCode == 4)
		{
			key = "EQ_CMN_UpdateEsfStatus.KeyNotSpecified";
		}
		else if (errorCode == 5)
		{
			key = "EQ_CMN_UpdateEsfStatus.SystemIdNotSpecified";
		}
		else if (errorCode == 6)
		{
			key = "EQ_CMN_UpdateEsfStatus.UnitIdNotSpecified";
		}
		else if (errorCode == 7)
		{
			key = "EQ_CMN_UpdateEsfStatus.UserIdNotSpecified";
		}

		return key;
	}

	/**
	 * Convert into WE status
	 * 
	 * @param action
	 *            - the action
	 * 
	 * @return the equivalent WE status
	 */
	private String convertAction(String action)
	{
		// authorise?
		if (action.equalsIgnoreCase(TaskEngineToolbox.TASK_ACTION_AUTH)
						|| action.equalsIgnoreCase(TaskEngineToolbox.TASK_ACTION_ONE))
		{
			return WERecordDataModel.STAT_AUTH;
		}

		// decline?
		else if (action.equalsIgnoreCase(TaskEngineToolbox.TASK_ACTION_DECL))
		{
			return WERecordDataModel.STAT_DECL;
		}

		// reviewing?
		else if (action.equalsIgnoreCase(TaskEngineToolbox.TASK_ACTION_REVIEW))
		{
			return WERecordDataModel.STAT_CHECK;
		}

		// cancel
		else
		{
			return WERecordDataModel.STAT_AVAIL;
		}
	}

}
