package com.misys.equation.bf;

import java.lang.reflect.Method;

import bf.com.misys.eqf.types.header.EqMessage;
import bf.com.misys.eqf.types.header.RqHeader;
import bf.com.misys.eqf.types.header.RsHeader;
import bf.com.misys.eqf.types.header.SearchRsHeader;

import com.misys.bf.autogen.AbstractEQ_CMN_SearchHandler;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationPVData;
import com.misys.equation.common.access.EquationPVFieldMetaData;
import com.misys.equation.common.access.EquationPVMetaData;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.context.EquationFunctionContext;
import com.misys.equation.function.runtime.FunctionBankFusion;
import com.misys.equation.function.runtime.FunctionMessages;
import com.misys.equation.function.runtime.FunctionMsgManager;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.XSDToolbox;
import com.trapedza.bankfusion.core.BankFusionException;
import com.trapedza.bankfusion.servercommon.commands.BankFusionEnvironment;

/**
 * BankFusion Activity Step implementation of an EQ4 Prompt/Validate
 * <p>
 * All PV specific Microflows invoke this generic BankFusion ActivityStep, which performs the actual invocation of the EQ4 PV.
 */
public class EQ_CMN_SearchHandler extends AbstractEQ_CMN_SearchHandler
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQ_CMN_SearchHandler.java 17325 2013-09-23 00:56:02Z williae1 $";
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EQ_CMN_SearchHandler.class);

	/**
	 * Constructor that takes an instance of a BankFusionEnvironment
	 * <p>
	 * Note that although the SearchHandlerActivityStep class implements this method, it is necessary to implement this method in
	 * this class too, otherwise a java.lang.NoSuchMethodException will be thrown. Also, although the Abstract class version of this
	 * method has JavaDoc indicating that the no-arg constructor should be used instead, this appears not to yet be the case (this
	 * is the constructor currently called).
	 * 
	 * @param env
	 * @throws BankFusionException
	 */
	public EQ_CMN_SearchHandler(BankFusionEnvironment env) throws BankFusionException
	{
		super(env);
		if (LOG.isInfoEnabled())
		{
			LOG.info("Constructor(BankFusionEnvironment), instance [" + System.identityHashCode(this) + "]");
		}
	}

	/**
	 * Main processing. This performs the actual service invocation
	 * 
	 * @param env
	 *            a BankFusionEnvironment
	 */
	@Override
	public void process(BankFusionEnvironment env)
	{
		FunctionBankFusion functionBankFusion = new FunctionBankFusion();
		FunctionMsgManager functionMsgManager = new FunctionMsgManager();

		String dataSourceName = null;
		String sessionId = null;
		try
		{
			super.process(env);
			RqHeader rqHeader = getF_IN_SearchHeader().getRqHeader();
			String systemId = rqHeader.getSystemId();
			String unitId = rqHeader.getUnitId();
			EquationUnit unit = EquationCommonContext.getContext().getEquationSystem(systemId).getUnit(unitId);

			// Establish connection with admin user for whom we have the password and switch to real user if they are
			// different
			String userId = rqHeader.getUserId();
			// Get the iSeries user if different from the connection user.
			if (userId == null || userId.equals(""))
			{
				userId = env.getUserSession().getUserId();
			}
			String equationIseriesProfile = null;
			if (userId.length() > 0)
			{
				if (rqHeader.getUserIdType().equals("1") || EquationCommonContext.getContext().isCASAuthentication())
				{
					equationIseriesProfile = EquationFunctionContext.getContext().getiSeriesUserForBFUser(unit, userId);
				}
				else
				{
					equationIseriesProfile = userId.toUpperCase();
				}
			}
			else
			{
				equationIseriesProfile = "";
			}

			sessionId = rqHeader.getSessionId();
			String sessionType = rqHeader.getSessionType();
			// Get the data source. Use default data source from equationConfiguration.properties if one is not supplied in request.
			dataSourceName = rqHeader.getDataSourceName();
			if (sessionType != null && sessionType.equals("1") && (dataSourceName == null || dataSourceName.length() == 0))
			{
				dataSourceName = EquationCommonContext.getContext().getDefaultDataSourceName();

				if (dataSourceName == null || dataSourceName.length() == 0)
				{
					// fatal error:
					throw new EQException("Data source name not found.");
				}
			}
			String pvId = getF_IN_SearchHeader().getPvId();
			String decode = getF_IN_SearchHeader().getDecode();
			Integer maxResults = getF_IN_SearchHeader().getMaxResults();
			String direction = getF_IN_SearchHeader().getDirection();
			String filterFields = getF_IN_SearchHeader().getFilterFields();
			String pagingFields = getF_IN_SearchHeader().getPagingFields();

			// default the output
			setF_OUT_OutputSearchData(null);

			if (LOG.isInfoEnabled())
			{
				LOG.info("Getting a system [" + systemId + "], a unit [" + unitId + "] and a user ["
								+ env.getUserSession().getUserId() + "], instance [" + System.identityHashCode(this) + "]");
			}
			sessionId = ActivityStepToolbox.getSession(systemId, unitId, env, equationIseriesProfile, sessionType, dataSourceName);

			if (LOG.isInfoEnabled())
			{
				LOG.info("Getting data list for PV " + pvId);
			}

			// Get PV meta data
			EquationUser user = EquationCommonContext.getContext().getEquationUser(sessionId);
			EquationPVMetaData pvMetaData = user.getEquationUnit().getPVMetaData(pvId);

			// format filter fields
			EquationPVData equationPVData = new EquationPVData(pvMetaData, user.getSession().getCcsid());
			String[] allFilterFields = filterFields.split(Toolbox.CONTEXT_DELIMETER);
			for (int i = 0; i < pvMetaData.rtvNumberOfFields(); i++)
			{
				equationPVData.setFieldValue(pvMetaData.rtvFieldMetaData(i).getId(),
								i < allFilterFields.length ? allFilterFields[i] : "*");
			}
			equationPVData.parseFieldsIntoDSCCN("Y");
			filterFields = equationPVData.getDataDsccn();

			// format page fields
			equationPVData = new EquationPVData(pvMetaData, user.getSession().getCcsid());
			String[] allPagingFields = pagingFields.split(Toolbox.CONTEXT_DELIMETER);
			for (int i = 0; i < pvMetaData.rtvNumberOfFields(); i++)
			{
				equationPVData.setFieldValue(pvMetaData.rtvFieldMetaData(i).getId(),
								i < allPagingFields.length ? allPagingFields[i] : "");
			}
			equationPVData.parseFieldsIntoDSCCN("N");
			pagingFields = equationPVData.getDataDsccn();

			// format other misc fields
			int directionInt = direction.equalsIgnoreCase("B") ? -1 : 1;
			int results = (maxResults == null || maxResults.intValue() == 0) ? 17 : maxResults.intValue();

			// call ServcieDirectory getEqDataList() instead?

			String dataList = EquationCommonContext.getContext().getEquationDataList(null, sessionId, pvId, decode, "",
							filterFields, pagingFields, directionInt, results).getDataList();

			// Populate PV Data Object
			Object bf_pvData = getNewInstanceBankFusionPVData();
			String[] resultsRows = dataList.split(EqDataType.GLOBAL_DELIMETER);
			// process each row
			for (String row : resultsRows)
			{
				Object bf_pvData_row = addPVRow(bf_pvData);
				equationPVData = new EquationPVData(pvMetaData, user.getSession().getCcsid());
				equationPVData.setDataDsccn(row);
				for (int i = 0; i < pvMetaData.rtvNumberOfFields(); i++)
				{
					// set the field value of the complex data type
					setFieldValue(bf_pvData_row, pvMetaData.rtvFieldMetaData(i), equationPVData.getFieldValue(pvMetaData
									.rtvFieldMetaData(i).getId()));
				}
			}
			setF_OUT_OutputSearchData(bf_pvData);
			// Set the output search header
			setOutputSearchHeader(functionMsgManager, functionBankFusion, sessionId);

			if (LOG.isInfoEnabled())
			{
				LOG.info("Activity step completed, instance [" + System.identityHashCode(this) + "]");
			}
		}
		catch (Exception e)
		{
			LOG.error("process()", e);
			functionMsgManager.insertOtherMessage(0, 0, "KSM7340", EQMessage.SEVERITY_ERROR, "KSM7340"
							+ Toolbox.getExceptionMessage(e), "", "");
			functionMsgManager.getFunctionMessages().insertMessages(functionMsgManager.getOtherMessages());
			// Set the output search header
			setOutputSearchHeader(functionMsgManager, functionBankFusion, "");

		}
		finally
		{
			if (dataSourceName != null && dataSourceName.length() > 0)
			{

				EquationUser eu = EquationCommonContext.getContext().getEquationUser(sessionId);
				EquationStandardSession session = eu.getSession();
				if (session != null)
				{
					// remove handles to session
					EquationFunctionContext.getContext().logOffSessionUserPool(session.getSessionIdentifier());
					// return the session to the pool
					FunctionRuntimeToolbox.returnEquationSessionToPool(dataSourceName, session);
				}

			}
		}
	}

	/**
	 * Add a new row to the PV complex data type object
	 * 
	 * @param bf_pvData
	 *            - the complex data type
	 * 
	 * @return the new row
	 */
	private Object addPVRow(Object bf_pvData)
	{
		Object rowObject = null;

		// Assume the same Package as the class, but note that getPackage returns null for
		// a class loaded by BankFusion, so determine this from the name
		String[] parts = bf_pvData.getClass().getName().split("\\.");
		parts[parts.length - 1] += XSDToolbox.EQ_PV_ROW;
		String rowsClassName = Toolbox.join(parts, ".");

		try
		{
			Class<?> rowClass = bf_pvData.getClass().getClassLoader().loadClass(rowsClassName);
			rowObject = rowClass.newInstance();
			// Use reflection to find the addRows method and invoke it, passing in the new object
			String methodName = "add" + parts[parts.length - 1];
			Method method = bf_pvData.getClass().getDeclaredMethod(methodName, rowClass);
			method.invoke(bf_pvData, rowObject);
		}
		catch (Exception e)
		{
			LOG.error("addPVRow", e);
			if (e instanceof BankFusionException)
			{
				throw (BankFusionException) e;
			}
			else
			{
				throw new BankFusionException(e);
			}
		}

		return rowObject;
	}

	/**
	 * Set the field value of the field name of a complex data type
	 * 
	 * @param bf_pvData_row
	 *            - the complex data type
	 * @param field
	 *            - the PV field meta data
	 * @param fieldValue
	 *            - the field value
	 */
	private void setFieldValue(Object bf_pvData_row, EquationPVFieldMetaData field, String fieldValue)
	{
		String pvFieldId = XSDToolbox.translateFieldId(field.getId());

		String methodName = "set" + pvFieldId + FunctionToolbox.UNDERSCORE + Toolbox.textToCamelCase(field.getDescription());

		try
		{
			Method method = bf_pvData_row.getClass().getDeclaredMethod(methodName, String.class);
			method.invoke(bf_pvData_row, fieldValue);
		}
		catch (Exception e)
		{
			LOG.error("setFieldValue", e);
			if (e instanceof BankFusionException)
			{
				throw (BankFusionException) e;
			}
			else
			{
				throw new BankFusionException(e);
			}
		}
	}
	/**
	 * Set the output search header
	 * 
	 * @param FunctionMsgManager
	 *            - the Function Message Manager
	 */
	private void setOutputSearchHeader(FunctionMsgManager functionMsgManager, FunctionBankFusion functionBankFusion,
					String sessionId)
	{
		// Prepare the output header
		SearchRsHeader outHeader = new SearchRsHeader();
		functionBankFusion.initialiseSearchRsHeader(outHeader);
		RsHeader rsHeader = outHeader.getRsHeader();

		// Return any messages to the caller in the Search Header:
		if (functionMsgManager.getFunctionMessages().getMsgSev() != FunctionMessages.MSG_NONE)
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug(functionMsgManager.getFunctionMessages().getMessages().toString());
			}
			rsHeader.getStatus().setEqMessages(
							functionBankFusion.rtvMessagesAsMessageArray(functionMsgManager.getFunctionMessages().getMessages(),
											null));
			/**
			 * This specifies the status of the transaction: 'S'-Success 'E'-Error 'I'-Success with Info Messages 'W'-Warning
			 * (non-overridden warnings exist) 'F'-Failure
			 */
			rsHeader.getStatus().setOverallStatus(
							FunctionRuntimeToolbox.cvtOverallStatus(functionMsgManager.getFunctionMessages().getMsgSev()));
		}
		else
		{
			EqMessage[] newMessages = new EqMessage[0];
			rsHeader.getStatus().setEqMessages(newMessages);
			rsHeader.getStatus().setOverallStatus(FunctionRuntimeToolbox.cvtOverallStatus(-1));
		}

		rsHeader.setSessionId(sessionId);

		// set the output header
		setF_OUT_OutputSearchHeader(outHeader);
	}
	/**
	 * Create a new instance of the BankFusion complex data type
	 * 
	 * @return the BankFusion complex data type
	 */
	private Object getNewInstanceBankFusionPVData()
	{
		Object bf_pvData = null;
		try
		{
			LOG.debug("Instantiating the bf PV data object ");
			bf_pvData = getF_IN_SearchData().getClass().newInstance();
		}
		catch (Exception e)
		{
			LOG.error("getNewInstanceBankFusionPVData", e);
			// Use the input parameter as an instance of the class
			bf_pvData = getF_IN_SearchData();
		}

		// return the output object
		return bf_pvData;
	}
}
