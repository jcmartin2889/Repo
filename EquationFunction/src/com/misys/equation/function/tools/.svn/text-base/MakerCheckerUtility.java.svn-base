package com.misys.equation.function.tools;

import java.util.Calendar;

import bf.com.misys.eqf.types.header.TaskEsfRsHeader;

import com.misys.equation.bankfusion.lrp.engine.ITaskEngine;
import com.misys.equation.bankfusion.lrp.engine.TaskEngineToolbox;
import com.misys.equation.bankfusion.tools.LRPToolbox;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IWECRecordDao;
import com.misys.equation.common.dao.IWEHRecordDao;
import com.misys.equation.common.dao.IWERecordDao;
import com.misys.equation.common.dao.beans.WARecordDataModel;
import com.misys.equation.common.dao.beans.WECRecordDataModel;
import com.misys.equation.common.dao.beans.WEHRecordDataModel;
import com.misys.equation.common.dao.beans.WERecordDataModel;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.context.EquationFunctionContext;
import com.misys.equation.function.runtime.FunctionCommonData;
import com.misys.equation.function.runtime.FunctionHandlerData;
import com.misys.equation.function.runtime.FunctionInfo;
import com.misys.equation.function.runtime.FunctionKeys;
import com.misys.equation.function.runtime.FunctionMessages;
import com.misys.equation.function.runtime.FunctionMsgManager;
import com.misys.equation.function.runtime.FunctionSession;
import com.misys.equation.function.runtime.ScreenSet;
import com.misys.equation.function.runtime.SecurityLevel;

public class MakerCheckerUtility
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MakerCheckerUtility.java 13368 2012-05-10 04:50:11Z bernie.terrado $";
	private static final EquationLogger LOG = new EquationLogger(MakerCheckerUtility.class);

	// KSM identifier signifying maker-checker processing
	public static final String MAKER_CHECKER_KSM = WARecordDataModel.MAKER_CHECKER_KSM;

	// Maker-Checker program for updating WEPF, WEHPF
	public static final String MAKER_CHECKER_HISTORY_UPDATE_PGM = "A07FRR";

	// Maker-Checker option for updating WEPF, WEHPF
	public static final String MAKER_CHECKER_HISTORY_UPDATE_OPTION = "TSM";

	/**
	 * Retrieves the maker/checker configuration for a given option id
	 * 
	 * @param session
	 *            Current transaction session
	 * @param optionId
	 *            Equation Function or service mnemonic
	 * @return WECRecordDataModel Maker/checker configuration record of the Equation Function or service
	 */
	public static WECRecordDataModel getMakerCheckerConfig(EquationStandardSession session, String optionId)
	{
		WECRecordDataModel wecRecord = new WECRecordDataModel(optionId);
		DaoFactory daoFactory = new DaoFactory();
		IWECRecordDao wecDao = daoFactory.getWECDao(session, wecRecord);
		wecRecord = wecDao.getWECRecord();
		return wecRecord;
	}

	/**
	 * Retrieves the current authoriser of the transaction
	 * 
	 * @param fhd
	 *            Function handler data of transaction
	 * @param eqUnit
	 *            Equation unit
	 * @return String Previous authoriser of the transaction
	 */
	public static String getCurrentSupervisor(FunctionHandlerData fhd, EquationUnit eqUnit)
	{
		EquationStandardSession sessionFromPool = null;
		String authorisor = "";
		try
		{
			sessionFromPool = FunctionRuntimeToolbox.rtvEquationSessionFromPool(fhd.getEquationUser().getEquationUnit());
			DaoFactory daoFactory = new DaoFactory();
			FunctionSession fs = fhd.getFunctionSession();
			WERecordDataModel weRecord = new WERecordDataModel(fs.getOptionId(), fs.getSessionId(), fs.getTransactionId(), fs
							.getUserId());
			IWERecordDao weDao = daoFactory.getWEDao(sessionFromPool, weRecord);
			weRecord = weDao.getWERecord();

			authorisor = weRecord == null ? "" : weRecord.getAuthorisor();
		}
		catch (EQException e)
		{
			LOG.error(e);
		}
		finally
		{
			FunctionRuntimeToolbox.returnEquationSessionToPool(eqUnit, sessionFromPool);
		}
		return authorisor;
	}

	/**
	 * Determines if the service requires maker-checker
	 * 
	 * @param session
	 *            Current transaction session
	 * @param optionId
	 *            Equation Function or service mnemonic
	 * @return true if service requires maker-checker
	 * @throws EQException
	 */
	public static boolean isMakerCheckerRequired(EquationStandardSession session, String optionId) throws EQException
	{
		boolean isMakerCheckerRequired = false;

		WECRecordDataModel wecRecord = session.getUnit().getRecords().getWECRecord(optionId);

		if (wecRecord != null && wecRecord.getRequiresMakerChecker().equalsIgnoreCase(WECRecordDataModel.MAKER_CHECKER_REQ))
		{
			isMakerCheckerRequired = true;
		}

		return isMakerCheckerRequired;
	}

	/**
	 * Determines if the transaction undergoing maker/checker is to be completed by Checker.
	 * 
	 * @param session
	 *            Current transaction session
	 * @param optionId
	 *            Equation Function or service mnemonic
	 * @return true if Checker is to complete the Maker/Checker processing of transaction otherwise the Maker is to complete
	 */
	public static boolean isCheckerToComplete(EquationStandardSession session, String optionId)
	{
		boolean isCheckerToComplete = false;
		WECRecordDataModel wecRecord = getMakerCheckerConfig(session, optionId);

		if (wecRecord != null && wecRecord.getCompleteByChecker().equals(EqDataType.YES))
		{
			isCheckerToComplete = true;
		}

		return isCheckerToComplete;
	}

	/**
	 * Updates the Maker/Checker status on the Referred items file WEPF and also updates the History items file WEHPF; this calls an
	 * overloaded updateMakerCheckerStatus having an EquationStandardSession parameter
	 * 
	 * @param functionKey
	 *            Button/Key for action performed
	 * @param reason
	 *            The reason for rejecting the transaction; it must be set to an empty string if not a rejection
	 * @param fhd
	 *            Function handler data of transaction
	 * @param authorisor
	 *            The authorising user
	 * @param writeJournal
	 *            - write the journal record?
	 * 
	 * @return int Severity number resulting from updating WEPF
	 * 
	 * @throws EQException
	 *             Exception from updating WEPF / WEHPF
	 */
	public static int updateMakerCheckerStatus(int functionKey, String reason, FunctionHandlerData fhd, String authorisor,
					boolean writeJournal) throws EQException
	{
		EquationStandardSession sessionFromPool = null;
		boolean rollback = true;
		try
		{
			// retrieve session
			sessionFromPool = FunctionRuntimeToolbox.rtvEquationSessionFromPool(fhd.getEquationUser().getEquationUnit());
			sessionFromPool.startEquationTransaction();
			int msgSev = updateMakerCheckerStatus(sessionFromPool, functionKey, reason, fhd, authorisor, writeJournal);
			sessionFromPool.endEquationTransaction();

			// success?
			if (msgSev < FunctionMessages.MSG_ERROR)
			{
				sessionFromPool.commitTransaction();
				rollback = false;
			}

			return msgSev;
		}
		finally
		{
			try
			{
				if (rollback && sessionFromPool != null)
				{
					sessionFromPool.rollbackTransactions();
				}
			}
			catch (Exception e)
			{
				LOG.error(e);
			}

			FunctionRuntimeToolbox.returnEquationSessionToPool(fhd.getEquationUser().getEquationUnit(), sessionFromPool);
		}
	}

	/**
	 * Sets-up the Maker/Checker status on the Referred items file WEPF and updates the LRP session based on the Function key
	 * pressed; Based on the Function key pressed, a cancelled Maker Checker referral is deleted.
	 * 
	 * @param sessionFromPool
	 *            Equation standard session of current transaction
	 * @param functionKey
	 *            Button/Key for action performed
	 * @param reason
	 *            The reason for rejecting the transaction; it must be set to an empty string if not a rejection
	 * @param fhd
	 *            Function handler data of transaction
	 * @param authorisor
	 *            The authorising user
	 * @param writeJournal
	 *            - write the journal record?
	 * 
	 * @return int Severity number resulting from updating WEPF
	 * 
	 * @throws EQException
	 *             Exception from updating WEPF / WEHPF
	 */
	public static int updateMakerCheckerStatus(EquationStandardSession sessionFromPool, int functionKey, String reason,
					FunctionHandlerData fhd, String authorisor, boolean writeJournal) throws EQException
	{

		EquationStandardTransaction transaction = new EquationStandardTransaction(MAKER_CHECKER_HISTORY_UPDATE_PGM
						+ MAKER_CHECKER_HISTORY_UPDATE_OPTION, sessionFromPool);
		if (!transaction.getValid() && transaction.getErrorList().size() != 0)
		{
			throw new EQException(transaction.getErrorList().get(0).getFormattedMessage());
		}

		// clear messages
		FunctionMsgManager functionMsgManager = fhd.getFunctionMsgManager();
		functionMsgManager.clearOtherMessages();

		// initialise
		String authStat = "";
		String taskAction = null;

		// process the function key
		if (functionKey == FunctionKeys.KEY_AUTH)
		{
			if (fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_MAKER_MAKER
							|| (fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_MAKER_CHECKER && functionKey == fhd
											.getScreenSetHandler().rtvScrnSetCurrent().getFKeyToNextScreenSet()))
			{
				authStat = WERecordDataModel.MAKER_CHECKER_STAT_COMPL;
				taskAction = null;
			}
			else
			{
				authStat = WERecordDataModel.MAKER_CHECKER_STAT_APPRVD;
				taskAction = TaskEngineToolbox.TASK_ACTION_AUTH;
			}
		}

		// Decline
		else if (functionKey == FunctionKeys.KEY_DECL)
		{
			authStat = WERecordDataModel.MAKER_CHECKER_STAT_REJCTD;
			taskAction = TaskEngineToolbox.TASK_ACTION_DECL;
		}

		// Exit
		else if (functionKey == FunctionKeys.KEY_EXIT || functionKey == FunctionKeys.KEY_PREV)
		{
			authStat = WERecordDataModel.MAKER_CHECKER_STAT_AWAIT;
			taskAction = null;
		}

		// Delete
		else if (functionKey == FunctionKeys.KEY_DEL)
		{
			authStat = WERecordDataModel.MAKER_CHECKER_STAT_CANCLD;
			taskAction = null;
		}

		// Submit
		else if (functionKey == FunctionKeys.KEY_ACCPT)
		{
			authStat = WERecordDataModel.MAKER_CHECKER_STAT_AWAIT;
			taskAction = null;
		}

		// Authorise - this is a special case during non-normal input (e.g. external input, recovery, api)
		else if (functionKey == FunctionKeys.KEY_OVR)
		{
			authStat = WERecordDataModel.MAKER_CHECKER_STAT_COMPL;
			taskAction = null;
		}

		// LRP process
		if (fhd.getTaskDetail() != null && taskAction != null)
		{
			updateLRPStatus(fhd, sessionFromPool, taskAction, reason, true);
		}

		// setup parameters for A07
		transaction.setWorkStationId(fhd.getFunctionInfo().getWorkStationId());
		transaction.setFieldValue("GZUID", fhd.getFunctionSession().getUserId().trim());
		transaction.setFieldValue("GZSID", fhd.getFunctionSession().getSessionId());
		transaction.setFieldValue("GZOID", Toolbox.trim(fhd.getFunctionSession().getOptionId(), 3));
		transaction.setFieldValue("GZTID", fhd.getFunctionSession().getTransactionId());
		transaction.setFieldValue("GZAUID", Toolbox.trim(authorisor, 4));
		transaction.setFieldValue("GZASTS", authStat);

		if (authStat == WERecordDataModel.MAKER_CHECKER_STAT_AWAIT)
		{
			transaction.setFieldValue("GZWE", EqDataType.NO);
		}
		else
		{
			transaction.setFieldValue("GZWE", EqDataType.YES);
		}

		Calendar cal = Calendar.getInstance();
		transaction.setFieldValue("GZSDTE", String.valueOf(Toolbox.getDateDBFormat(cal)));
		transaction.setFieldValue("GZSTIM", String.valueOf(Toolbox.getTimeDBFormat(cal)));
		transaction.setFieldValue("GZRTXT", reason);
		transaction.setFieldValue("GZ1STS", fhd.getFunctionSession().getStatusOriginal());

		ScreenSet screenSet = fhd.getScreenSetHandler().rtvScreenSetMain();
		// Set the EFC data
		if (screenSet.getFunction().containsAPIFieldSet(Function.EFC_FIELDSET))
		{
			// retrieve the EFC details
			FunctionCommonData mapData = new FunctionCommonData(sessionFromPool, screenSet.getFunction(), screenSet.getFunction()
							.getUpdateAPI(Function.EFC_FIELDSET), screenSet.getFunctionData(), screenSet.getFunctionAdaptor(), fhd);

			// Setup the fields
			transaction.setFieldValue("GZBRNM", mapData.getFieldValue(FunctionCommonData.EFC_EDAB));
			transaction.setFieldValue("GZACC1", mapData.getFieldValue(FunctionCommonData.EFC_EDAN));
			transaction.setFieldValue("GZREF1", mapData.getFieldValue(FunctionCommonData.EFC_ETREF));
			transaction.setFieldValue("GZACC2", mapData.getFieldValue(FunctionCommonData.EFC_ECAN));
			transaction.setFieldValue("GZREF2", mapData.getFieldValue(FunctionCommonData.EFC_EXREF));
		}

		// apply transaction
		sessionFromPool.applyEquationTransaction(transaction);

		// check if transaction has been updated correctly or not
		if (!transaction.getValid())
		{
			functionMsgManager.generateEQMessages(functionMsgManager.getOtherMessages(), 0, 0, "", 0, null, transaction
							.getMessages(), "", "", FunctionMessages.MSG_NONE);
		}

		// update the status
		fhd.getScreenSetHandler().rtvScreenSetMain().getFunctionData().chgFieldDbValue(FunctionData.FLD_STAT_MKR_CHKR, authStat);

		// write the journal for the service?
		if (writeJournal)
		{
			writeJournal(sessionFromPool, fhd);
		}

		return functionMsgManager.getOtherMessages().getMsgSev();
	}

	/**
	 * Updates the LRP status
	 * 
	 * @param fhd
	 *            Function handler data of transaction
	 * @param reason
	 *            The reason for rejecting the transaction; it must be set to an empty string if not a rejection
	 * @param sessionFromPool
	 *            The equation session
	 * @param taskAction
	 *            The task action of the process
	 * @param reason
	 *            The reason for rejection
	 * @param rollback
	 *            boolean to check if transaction should rollback
	 * @return int Severity number resulting from updating LRP
	 * @throws EQException
	 *             Exception from updating LRP
	 */
	private static int updateLRPStatus(FunctionHandlerData fhd, EquationStandardSession sessionFromPool, String taskAction,
					String reason, boolean rollback) throws EQException
	{
		// Task engine
		ITaskEngine engine = EquationFunctionContext.getContext().getTaskEngine(fhd.getFunctionInfo().getSessionId());

		// make sure user still owns the task
		if (!engine.isTaskOwnedByUser(fhd.getTaskDetail().getTkiid(), fhd.getEquationUser().getUserId()))
		{
			fhd.getFunctionMsgManager().insertOtherMessage(fhd.getEquationUser().getSession(),
							fhd.getScreenSetHandler().getCurScreenSet(), 0, "", 1, null, "KSM7609", "", "");
			return fhd.getFunctionMsgManager().getOtherMessages().getMsgSev();
		}

		// Setup the payload
		TaskEsfRsHeader payload = LRPToolbox.getRespondEsfPayload(taskAction, reason);

		// update the authorisor in WE. Note: this should not be here but in WID, is there a way to know the user who
		// claimed a task?
		DaoFactory daoFactory = new DaoFactory();
		FunctionSession fs = fhd.getFunctionSession();
		WERecordDataModel weRecord = new WERecordDataModel(fs.getOptionId(), fs.getSessionId(), fs.getTransactionId(), fs
						.getUserId());
		IWERecordDao weDao = daoFactory.getWEDao(sessionFromPool, weRecord);
		weRecord = weDao.getWERecord();
		weRecord.setAuthorisor(fhd.getEquationUser().getUserId());
		weDao.updateRecord(weRecord);

		// complete the task
		engine.completEsfTask(fhd.getTaskDetail().getTkiid(), payload, reason);

		sessionFromPool.commitTransaction();
		rollback = false;

		// Task completed?
		if (EquationCommonContext.getContext().isLRPProcessing() && fhd.getTaskDetail() != null)
		{
			fhd.setCompletedTask(fhd.getTaskDetail().getTkiid());
		}
		return FunctionMessages.MSG_NONE;
	}

	/**
	 * Updates the Maker/Checker status of Completed transactions on the Referred items file WEPF and also updates the History items
	 * file WEHPF
	 * 
	 * This is used instead of updateMakerCheckerStatus() to prevent the program A07FRR from deleting the WEPF record and let java
	 * do the updating and deleting
	 * 
	 * @param session
	 *            The Equation session
	 * @param fhd
	 *            The Function Handler Data
	 * @param authorisor
	 *            The authorising user
	 * @param securityLevel
	 *            security level
	 */
	public static void updateMakerCheckerCompletedStatus(EquationStandardSession session, FunctionHandlerData fhd,
					String authorisor, int securityLevel)
	{
		try
		{
			FunctionSession fs = fhd.getFunctionSession();
			WEHRecordDataModel wehRecord = new WEHRecordDataModel();
			DaoFactory daoFactory = new DaoFactory();

			IWEHRecordDao wehDao = daoFactory.getWEHDao(session, wehRecord);
			wehRecord.setMaker(fhd.getFunctionSession().getUserId().trim());
			wehRecord.setChecker(Toolbox.trim(authorisor, 4));
			wehRecord.setSessionId(fs.getSessionId());
			wehRecord.setTransactionId(fs.getTransactionId());
			wehRecord.setOptionId(Toolbox.trim(fs.getOptionId(), 3));
			int sequence = wehDao.getLastSequenceNumber();
			wehRecord.setSequence(sequence + 1);
			wehRecord.setStatus(WERecordDataModel.MAKER_CHECKER_STAT_COMPL);
			Calendar cal = Calendar.getInstance();
			wehRecord.setProcessedTime(Toolbox.getTimeDBFormat(cal));
			wehRecord.setProcessedDate(Toolbox.getDateDBFormat(cal));
			wehRecord.setReference(Toolbox.trim(fs.getTransactionId(), 20));

			ScreenSet screenSet = fhd.getScreenSetHandler().rtvScreenSetMain();
			// Set the EFC data
			if (screenSet.getFunction().containsAPIFieldSet(Function.EFC_FIELDSET))
			{
				// retrieve the EFC details
				FunctionCommonData mapData = new FunctionCommonData(session, screenSet.getFunction(), screenSet.getFunction()
								.getUpdateAPI(Function.EFC_FIELDSET), screenSet.getFunctionData(), screenSet.getFunctionAdaptor(),
								fhd);

				// Setup the fields
				wehRecord.setBranch(mapData.getFieldValue(FunctionCommonData.EFC_EDAB));
				wehRecord.setAccount(mapData.getFieldValue(FunctionCommonData.EFC_EDAN));
				wehRecord.setReference(mapData.getFieldValue(FunctionCommonData.EFC_ETREF));
				wehRecord.setSecondAccount(mapData.getFieldValue(FunctionCommonData.EFC_ECAN));
				wehRecord.setAdditionalRef(mapData.getFieldValue(FunctionCommonData.EFC_EXREF));
			}
			wehDao.insertRecord(wehRecord);
			// only update the WEPF if Checker to Complete so we can see the alert on the Referral Tab
			// indicating that this transaction has been completed by the checker
			if (securityLevel == SecurityLevel.CHCKR_MAKER_CHECKER)
			{
				fhd.getFunctionSession().update(session, true, WERecordDataModel.MAKER_CHECKER_STAT_COMPL,
								WERecordDataModel.LVL_ALL, "", "N");
			}
		}
		catch (EQException e)
		{
			LOG.error(e);
		}
	}

	/**
	 * Writes the journal for the transaction
	 * 
	 * @param session
	 *            Session of transaction
	 * @param fhd
	 *            Function handler data of transaction
	 * @throws EQException
	 *             Error writing to journal file of transaction
	 */
	public static void writeJournal(EquationStandardSession session, FunctionHandlerData fhd) throws EQException
	{
		FunctionInfo functionInfo = fhd.getFunctionInfo();
		ScreenSet screenSetMain = fhd.getScreenSetHandler().rtvScreenSetMain();
		EquationUser eqUser = fhd.getEquationUser();
		FunctionMsgManager functionMsgManager = fhd.getFunctionMsgManager();

		String mode = screenSetMain.getMode();
		if (mode.equals(IEquationStandardObject.FCT_FUL))
		{
			mode = IEquationStandardObject.FCT_MNT;
		}

		// setup journalHeader
		JournalHeader journalHeader = FunctionRuntimeToolbox.setupJournal(mode, functionInfo.getWorkStationId(), screenSetMain,
						eqUser, functionMsgManager.getOverWarnMessages());

		EquationStandardTransaction transaction = FunctionRuntimeToolbox.writeJournalTransaction(session, journalHeader,
						screenSetMain.getFunction(), screenSetMain.getFunctionData(), screenSetMain.getFunctionDataBefore(),
						screenSetMain.getFunctionAdaptor(),
						functionInfo.getSessionType() != EquationCommonContext.SESSION_RECOVERY, fhd);

		if (!transaction.getValid())
		{
			throw new EQException(transaction.getErrorList().get(0).getFormattedMessage());
		}
	}
}