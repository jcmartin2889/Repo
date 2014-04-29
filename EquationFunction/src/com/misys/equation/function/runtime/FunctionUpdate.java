package com.misys.equation.function.runtime;

import java.util.Calendar;
import java.util.List;

import bf.com.misys.eqf.types.header.MessageStatus;
import bf.com.misys.eqf.types.header.TaskRqHeader;
import bf.com.misys.eqf.types.header.TaskRsHeader;

import com.misys.equation.bankfusion.lrp.bean.TaskDetail;
import com.misys.equation.bankfusion.lrp.engine.ITaskEngine;
import com.misys.equation.bankfusion.lrp.engine.TaskEngineToolbox;
import com.misys.equation.bankfusion.tools.LRPToolbox;
import com.misys.equation.common.access.Equation38Session;
import com.misys.equation.common.access.Equation4Session;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationConnectionWrapper;
import com.misys.equation.common.access.EquationDataStructureData;
import com.misys.equation.common.access.EquationLogin;
import com.misys.equation.common.access.EquationStandardObjectHelper;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationTransactionManager;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.access.IListTransaction;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IWERecordDao;
import com.misys.equation.common.dao.beans.GAZRecordDataModel;
import com.misys.equation.common.dao.beans.WERecordDataModel;
import com.misys.equation.common.datastructure.EqDS_DSSYS3;
import com.misys.equation.common.datastructure.EqDS_SVJOB4;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.globalprocessing.SystemStatusManager;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.internal.eapi.core.EQXAException;
import com.misys.equation.common.utilities.Enhancement;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EqTimingTest;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.adaptor.FieldSetAdaptor;
import com.misys.equation.function.adaptor.FunctionAdaptor;
import com.misys.equation.function.adaptor.ValueAdaptor;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.RelatedFields;
import com.misys.equation.function.context.EquationFunctionContext;
import com.misys.equation.function.el.ELRuntime;
import com.misys.equation.function.journal.ParentTransactionDetail;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;
import com.misys.equation.function.tools.IdempotencyToolbox;
import com.misys.equation.function.tools.MakerCheckerUtility;
import com.misys.equation.function.tools.PluginVersion;
import com.misys.equation.function.tools.ReturnDataMFUserExit;
import com.misys.equation.function.useraccess.UserExitMessage;

/**
 * This class represent a Function Updater
 */
public class FunctionUpdate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionUpdate.java 17761 2014-01-10 16:06:28Z lima12 $";

	// Log
	private static final EquationLogger LOG = new EquationLogger(FunctionUpdate.class);

	public final static int UPDSCRN = 100;
	public final static int PREUPDSCRN = 900;
	public final static int POSTUPDSCRN = 901;
	public final static int FINALUPDSCRN = 902;

	public final static String UPD_PREUPDATE = "PREUPDATE";
	public final static String UPD_POSTUPDATE = "POSTUPDATE";
	public final static String UPD_SUCCESS = "SUCCESS";

	public final static int BEFORE_IMAGE_UNDER_API = 1;
	public final static int BEFORE_IMAGE_BIZ_LAYER = 2;

	private FunctionHandlerData fhd;
	private EquationStandardSession equationStandardSession;
	private FunctionMsgManager functionMsgManager;
	private FunctionData functionData;
	private final FunctionMessages functionMessages;
	private ScreenSetHandler screenSetHandler;
	private ScreenSet screenSetMain;
	private FunctionInfo functionInfo;
	private EquationUser eqUser;
	private FunctionSession functionSession;

	// Journal header
	private JournalHeader journalHeader;

	// function transactions
	private FunctionTransactions functionTransactions;

	// debug info
	private final FunctionDebugInfo functionDebugInfo;

	// before image processing
	// this identify whether the before image processing is trigged via:
	// 1. underlying API - This means that the before image on a load is stored and passed to the
	// relevant update API.
	// 2. business layer before image - This means that the before image is retrieve during the update
	// process and compared against the before image on a load. This will only compare those
	// details relevant to the function
	private int beforeImageProcessing;

	// commitment processing
	// this determines whether this transaction will perform commitment processing
	private boolean commitProcessing;

	// this determines whether messages (warnings and info) are ignored by the system or should be returned back to the
	// calling program
	private boolean generateWarningInfo;

	// this determines whether update has been update after calling update()
	private boolean updateMade;

	// load limit
	private long maximumBytesPerLoadApi;
	private long maximumBytesAllLoadApi;

	// this determines the last update api field set being processed
	String lastUpdateApiFieldSetId;

	// XA Transaction Manager
	private EquationTransactionManager txnManager;
	private EquationStandardSession originalStandardSession;

	/**
	 * Construct a new Function Update given the Function Handler
	 * 
	 */
	public FunctionUpdate(FunctionHandlerData functionHandlerData)
	{
		setupFromFunctionHandler(functionHandlerData);
		this.functionMessages = new FunctionMessages();
		this.journalHeader = null;
		this.functionDebugInfo = new FunctionDebugInfo();
		this.commitProcessing = true;
		this.generateWarningInfo = false;
		this.updateMade = false;

		this.maximumBytesPerLoadApi = Toolbox.parseInt(
						EquationCommonContext.getConfigProperty("eq.loadapi.MaximumBytesPerLoadApi"), 100000);
		this.maximumBytesAllLoadApi = Toolbox.parseInt(EquationCommonContext.getConfigProperty("eq.loadapi.MaximumBytesAll"), 0);
	}

	/**
	 * Construct a new Function Update given the Function Handler and the function data details to apply
	 * 
	 */
	public FunctionUpdate(FunctionHandlerData functionHandlerData, FunctionData functionDataAft, FunctionData functionDataBef)
	{
		setupFromFunctionHandler(functionHandlerData);
		this.functionData = functionDataAft;
		this.functionMessages = new FunctionMessages();
		this.journalHeader = null;
		this.functionDebugInfo = new FunctionDebugInfo();
		this.commitProcessing = true;
		this.generateWarningInfo = false;
		this.updateMade = false;
	}

	/**
	 * Setup properties based on function handler
	 * 
	 * @param functionHandler
	 *            - the function handler
	 */
	private void setupFromFunctionHandler(FunctionHandlerData functionHandlerData)
	{
		this.fhd = functionHandlerData;
		this.eqUser = functionHandlerData.getEquationUser();
		this.equationStandardSession = eqUser.getSession();
		this.functionMsgManager = functionHandlerData.getFunctionMsgManager();
		this.screenSetHandler = functionHandlerData.getScreenSetHandler();
		this.screenSetMain = screenSetHandler.rtvScreenSetMain();
		this.functionData = screenSetMain.getFunctionData();
		this.functionInfo = functionHandlerData.getFunctionInfo();
		this.functionSession = functionHandlerData.getFunctionSession();

		// before image processing
		String str = LanguageResources.getString("FunctionUpdate.BeforeImageProcessing").substring(0, 1);
		beforeImageProcessing = Toolbox.parseInt(str, BEFORE_IMAGE_UNDER_API);
	}

	/**
	 * Returns the list of messages
	 * 
	 * @return the list of messages
	 */
	public FunctionMessages getFunctionMessages()
	{
		return functionMessages;
	}

	/**
	 * Returns the journal header
	 * 
	 * @return the journal header
	 */
	public JournalHeader getJournalHeader()
	{
		return journalHeader;
	}

	/**
	 * Return the list of transactions
	 * 
	 * @return the list of transactions
	 */
	public FunctionTransactions getFunctionTransactions()
	{
		return functionTransactions;
	}

	/**
	 * Return the before image processing
	 * 
	 * @return the before image processing
	 */
	public int getBeforeImageProcessing()
	{
		return beforeImageProcessing;
	}

	/**
	 * Set the before image processing
	 * 
	 * @param beforeImageProcessing
	 *            - the before image processing
	 */
	public void setBeforeImageProcessing(int beforeImageProcessing)
	{
		this.beforeImageProcessing = beforeImageProcessing;
	}

	/**
	 * Return the commitment control processing
	 * 
	 * @return the commitment control processing
	 */
	public boolean isCommitProcessing()
	{
		return commitProcessing;
	}

	/**
	 * Set the commitment control processing
	 * 
	 * @param commitProcessing
	 *            - the commitment control processing
	 */
	public void setCommitProcessing(boolean commitProcessing)
	{
		this.commitProcessing = commitProcessing;
	}

	/**
	 * Determine whether warnings and infos messages are generated
	 * 
	 * @return true if warnings and infos messages are generated
	 */
	public boolean isGenerateWarningInfo()
	{
		return generateWarningInfo;
	}

	/**
	 * Set whether warnings and infos messages are generated
	 * 
	 * @param ignoreWarningInfo
	 *            - true if warnings and infos messages are generated
	 */
	public void setGenerateWarningInfo(boolean generateWarningInfo)
	{
		this.generateWarningInfo = generateWarningInfo;
	}

	/**
	 * Determine whether update has been made
	 * 
	 * @return true if update has been made
	 */
	public boolean isUpdateMade()
	{
		return updateMade;
	}

	/**
	 * Start the Equation update process
	 * 
	 * @param session
	 *            - the Equation standard session
	 * 
	 * @return true if successful
	 */
	private boolean startTransaction(EquationStandardSession session) throws EQException
	{
		if (!commitProcessing)
		{
			return false;
		}

		session.startEquationTransaction();
		return true;
	}

	/**
	 * Start a transaction for all sessions in a List
	 * 
	 * @param sessions
	 *            - the sessions for which to start the transaction
	 * @return true if successful, otherwise false
	 * @throws EQException
	 */
	private boolean startTransaction(List<EquationStandardSession> sessions) throws EQException
	{
		if (!commitProcessing)
		{
			return false;
		}

		for (EquationStandardSession session : sessions)
		{
			session.startEquationTransaction();
		}
		return true;
	}

	/**
	 * End the Equation update process
	 * 
	 * @param session
	 *            - the Equation standard session
	 * 
	 * @return true if successful
	 * 
	 * @throws EQException
	 */
	private boolean endTransaction(EquationStandardSession session) throws EQException
	{
		if (!commitProcessing)
		{
			return false;
		}

		session.endEquationTransaction();
		return true;
	}

	/**
	 * End the Equation update process for a list of sessions
	 * 
	 * @param sessions
	 *            - the List of Equation standard sessions
	 * 
	 * @return true if successful
	 * 
	 * @throws EQException
	 */
	private boolean endTransaction(List<EquationStandardSession> sessions) throws EQException
	{
		if (!commitProcessing)
		{
			return false;
		}

		for (EquationStandardSession session : sessions)
		{
			session.endEquationTransaction();
		}
		return true;
	}

	/**
	 * Roll back the Equation update process
	 * 
	 * @param session
	 *            - the Equation standard session
	 * 
	 * @return true if successful
	 * 
	 * @throws EQException
	 */
	public boolean rollbackTransaction(EquationStandardSession session) throws EQException
	{
		if (!commitProcessing)
		{
			return false;
		}

		session.rollbackTransactions();
		return true;
	}

	/**
	 * Roll back the Equation update process for all provided sessions
	 * 
	 * @param sessions
	 *            - the List of Equation standard sessions
	 * 
	 * @return true if successful
	 * 
	 * @throws EQException
	 */
	private boolean rollbackTransaction(List<EquationStandardSession> sessions) throws EQException
	{
		if (!commitProcessing)
		{
			return false;
		}

		for (EquationStandardSession session : sessions)
		{
			session.rollbackTransactions();
		}
		return true;
	}

	/**
	 * Commit the Equation update process
	 * 
	 * @param session
	 *            - the Equation standard session
	 * 
	 * @return true if successful
	 * 
	 * @throws EQException
	 */
	public boolean commitTransaction(EquationStandardSession session) throws EQException
	{
		if (!commitProcessing)
		{
			return false;
		}

		try
		{
			session.connectionCommit();
			session.commitTransactions();
		}
		catch (Exception e)
		{
			LOG.error(e);
			throw new EQException("FunctionUpdate: commitTransaction():" + Toolbox.getExceptionMessage(e), e);
		}
		return true;
	}

	/**
	 * Commit the Equation update process for all sessions
	 * 
	 * @param sessions
	 *            - the List of Equation standard sessions
	 * 
	 * @return true if successful
	 * 
	 * @throws EQException
	 */
	private boolean commitTransaction(List<EquationStandardSession> sessions) throws EQException
	{
		if (!commitProcessing)
		{
			return false;
		}

		for (EquationStandardSession session : sessions)
		{
			try
			{
				session.connectionCommit();
				session.commitTransactions();
			}
			catch (Exception e)
			{
				LOG.error(e);
				throw new EQException("FunctionUpdate: commitTransaction():" + Toolbox.getExceptionMessage(e), e);
			}
		}
		return true;
	}

	/**
	 * Performs retrieve mode for all load APIs
	 * 
	 * @param screenSet
	 *            - the screen set
	 * @param inputFieldSet
	 *            - the input field set
	 * 
	 * @return the function mode
	 * 
	 * @throws EQException
	 */
	public String retrieve(ScreenSet screenSet, InputFieldSet inputFieldSet) throws EQException
	{
		// debug info
		functionDebugInfo.printStartMethod("FunctionUpdate: retrieve(1)");
		EqTimingTest.printStartTime("FunctionUpdate.retrieve()", "");

		String mode = IEquationStandardObject.FCT_ADD;
		functionTransactions = null;
		try
		{
			// reset message severity
			functionMessages.clearMessages();

			// start the transaction boundary
			startTransaction(equationStandardSession);

			// setup journalHeader
			journalHeader = FunctionRuntimeToolbox.setupJournal(IEquationStandardObject.FCT_RTV, functionInfo.getWorkStationId(),
							screenSetMain, eqUser, functionMsgManager.getOverWarnMessages());

			// use the function transactions to perform API validation on the field set
			functionTransactions = new FunctionTransactions(fhd, IEquationStandardObject.FCT_RTV);
			functionTransactions.setAdaptor(GAZRecordDataModel.TYP_LOAD);
			functionTransactions.setJournalHeader(journalHeader);

			// Retrieve all the field sets
			functionDebugInfo.printLoadAPI("");
			mode = internal_retrieve(screenSet, inputFieldSet);
		}
		catch (Exception e)
		{
			LOG.error(e);
			functionDebugInfo.printLoadAPIError("");
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
		finally
		{
			journalHeader = null;
			endTransaction(equationStandardSession);
			// TODO: Should we really be rolling back every time?
			rollbackTransaction(equationStandardSession);
			functionDebugInfo.printEndMethod("FunctionUpdate: retrieve(1)");
			EqTimingTest.printTime("FunctionUpdate.retrieve()", "");

			// Log all messages
			if (!functionMessages.chkNoMessage())
			{
				LOG.debug(LanguageResources.getString("FunctionUpdate.ReturnedMessageFromRetrieve") + " : " + functionMessages);
			}
		}

		// returns the message severity
		return mode;
	}

	/**
	 * Perform load API processing
	 * 
	 * @param screenSet
	 *            - the screen set
	 * @param inputFieldSet
	 *            - the input field set
	 * 
	 * @return the function mode
	 * 
	 * @throws EQException
	 */
	private String internal_retrieve(ScreenSet screenSet, InputFieldSet inputFieldSet) throws EQException
	{
		// debug info
		functionDebugInfo.printStartMethod("FunctionUpdate: retrieve(2)");

		// get other details from the function handler
		FunctionAdaptor functionAdaptor = screenSet.getFunctionAdaptor();
		Function function = screenSet.getFunction();

		// function mode default to Add
		String mode = IEquationStandardObject.FCT_ADD;
		// .. unless maintain only function?
		if (function.isAllowedMaint() && !function.isAllowedAdd() && !function.isAllowedDel())
		{
			mode = IEquationStandardObject.FCT_MNT;
		}
		// .. unless delete only function?
		else if (function.isAllowedDel() && !function.isAllowedMaint() && !function.isAllowedAdd())
		{
			mode = IEquationStandardObject.FCT_DEL;
		}
		// .. unless enquiry only function?
		else if (function.isAllowedEnq())
		{
			mode = IEquationStandardObject.FCT_ENQ;
		}

		// current size loaded for a list API
		long currentSize = 0;

		// invoke pre-load user exit
		functionAdaptor.preLoad();
		if (functionAdaptor.getFunctionAdaptorImpl() != null)
		{
			List<UserExitMessage> messages = functionAdaptor.getFunctionAdaptorImpl().getReturnMessages().getReturnMessages();
			if (messages.size() > 0)
			{
				String secondLevelText = LanguageResources.getString("FunctionUpdate.ReturnedMessageFromUserExitPreLoadMode");
				LOG.debug(secondLevelText + ":" + functionAdaptor.getFunctionAdaptorImpl().getReturnMessages());
				functionMsgManager.generateMessages(equationStandardSession, functionMessages, screenSetHandler.getCurScreenSet(),
								UPDSCRN, "", 0, null, messages, "", secondLevelText, functionData, FunctionMessages.MSG_NONE);
			}
		}

		// clear list of APIs incomplete
		screenSet.clearLoadIncomplete();

		// execute each load API field set
		for (int i = 0; i < inputFieldSet.getLoadAPIs().size() && functionMessages.getMsgSev() < FunctionMessages.MSG_ERROR; i++)
		{
			APIFieldSet apiFieldSet = inputFieldSet.getLoadAPIs().get(i);
			FieldSetAdaptor fieldSetAdaptor = functionAdaptor.getFieldSetAdaptor(equationStandardSession, apiFieldSet);
			if (fieldSetAdaptor.shouldExecute(apiFieldSet))
			{
				// will it exceed the limit?
				if (maximumBytesAllLoadApi != 0 && currentSize >= maximumBytesAllLoadApi)
				{
					screenSet.getLoadAPI2Incomplete().add(apiFieldSet.getId() + " " + apiFieldSet.getLabel());
					continue;
				}

				functionDebugInfo.printLoadAPI(apiFieldSet.getId());
				functionTransactions.applyTransaction(equationStandardSession, functionAdaptor, apiFieldSet, functionData,
								function, getLimitBytes(currentSize));
				functionDebugInfo.printLoadAPICompleted(apiFieldSet.getId());

				// get the transaction
				IEquationStandardObject transaction = functionTransactions.getEquationTransactions().get(apiFieldSet.getId());

				// key not exist, but should be existing
				EQMessage eqMessage = FunctionRuntimeToolbox.getKeyNotFoundMsg(transaction.getMessages());
				if (eqMessage != null)
				{
					if (apiFieldSet.getNewField().equals(EqDataType.NO))
					{
						functionMsgManager.generateMessage(equationStandardSession, functionMessages, screenSetHandler
										.getCurScreenSet(), UPDSCRN + i, "", 0, null, eqMessage.getDsepms(), "", LanguageResources
										.getFormattedString("FunctionUpdate.ReturnedMessageFromLoadAPI", transaction.getId() + " "
														+ apiFieldSet.getLabel()), FunctionMessages.MSG_NONE);
					}
				}

				// key exist, but should not be existing
				else if (apiFieldSet.getNewField().equals(EqDataType.YES))
				{
					functionMsgManager.generateMessage(equationStandardSession, functionMessages, screenSetHandler
									.getCurScreenSet(), UPDSCRN + i, "", 0, null, "KSM7383", "", LanguageResources
									.getFormattedString("FunctionUpdate.ReturnedMessageFromLoadAPI", transaction.getId() + " "
													+ apiFieldSet.getLabel()), FunctionMessages.MSG_NONE);
				}

				// does this load API determines the mode of the function?
				if (apiFieldSet.isDetermineMode())
				{
					if (transaction.getValid())
					{
						mode = IEquationStandardObject.FCT_FUL;
					}
					else
					{
						mode = IEquationStandardObject.FCT_ADD;
					}
				}

				// list complete?
				if (transaction.getValid() && transaction instanceof IListTransaction)
				{
					IListTransaction lTransaction = (IListTransaction) transaction;

					// transaction completed?
					if (!lTransaction.isComplete())
					{
						screenSet.getLoadAPIIncomplete().add(apiFieldSet.getId() + " " + apiFieldSet.getLabel());
					}

					// calculate current size
					currentSize += (lTransaction.getRecordCount() * lTransaction.getRecordLength());
				}

				checkTransactionLoad(equationStandardSession, transaction, apiFieldSet, i);
			}
		}

		// no error, then do load assigments
		if (functionMessages.getMsgSev() < FunctionMessages.MSG_ERROR)
		{
			// After calling all the Load APIs, now process all EL Script and Java
			// user exit assignments of InputField values (all to the Primitive form)
			FunctionRuntimeToolbox.processFunctionLoadAssignments(screenSet.getFunction(), functionData, screenSet
							.getFunctionAdaptor(), fhd.getEquationUser().getSession(), fhd);
		}

		// no error, then invoke post load user exit
		if (functionMessages.getMsgSev() < FunctionMessages.MSG_ERROR)
		{
			String fct = functionAdaptor.postLoad(functionTransactions.getEquationTransactions());
			if (functionAdaptor.getFunctionAdaptorImpl() != null)
			{
				List<UserExitMessage> messages = functionAdaptor.getFunctionAdaptorImpl().getReturnMessages().getReturnMessages();
				if (messages.size() > 0)
				{
					String secondLevelText = LanguageResources.getString("FunctionUpdate.ReturnedMessageFromUserExitPostLoadMode");
					LOG.debug(secondLevelText + ":" + functionAdaptor.getFunctionAdaptorImpl().getReturnMessages());
					functionMsgManager.generateMessages(equationStandardSession, functionMessages, screenSetHandler
									.getCurScreenSet(), UPDSCRN, "", 0, null, messages, "", secondLevelText, functionData,
									FunctionMessages.MSG_NONE);
				}
			}

			// mode returned
			if (fct != null)
			{
				if (EquationStandardObjectHelper.isValidModeOnLoad(fct))
				{
					mode = fct;
				}
				else
				{
					LOG.error(LanguageResources.getFormattedString("FunctionUpdate.ModeErrorDetermineByLoadUserExit", fct));
				}
			}
		}

		// debug info
		functionDebugInfo.printEndMethod("FunctionUpdate: retrieve(2)");

		return mode;
	}

	/**
	 * Processes an assignment (if any) to an input field.
	 * <p>
	 * This will check to see if either a Script or Code assignment exists and if so, then action it.
	 * 
	 * @param inputField
	 *            The target input field
	 * @param functionAdaptor
	 *            The FunctionAdaptor instance
	 * @throws EQException
	 */
	private void processScriptOrCodeAssignment(InputField inputField, FunctionAdaptor functionAdaptor) throws EQException
	{
		// After calling all the Load APIs, now process all EL Script and Java
		// user exit assignments of InputField values (all to the Primitive form)
		if (Field.ASSIGNMENT_SCRIPT.equals(inputField.getLoadPrimitiveAssignment()))
		{
			// Mapping script expression specified
			String dbValue = ELRuntime.runStringScript(inputField.getLoadPrimitiveScript(), functionData, inputField.getId(),
							LanguageResources.getString("Language.LoadAssignment"), null, ELRuntime.DB_VALUE);
			if (dbValue != null)
			{
				functionData.chgFieldDbValue(inputField.getId(), dbValue);
			}
		}
		else if (Field.ASSIGNMENT_CODE.equals(inputField.getLoadPrimitiveAssignment()))
		{
			String curValue = functionData.rtvFieldDbValue(inputField.getId());
			ValueAdaptor valueAdaptor = functionAdaptor.getValueAdaptor(equationStandardSession, inputField.getId(), "",
							GAZRecordDataModel.TYP_LOAD);
			String newValue = valueAdaptor.getValue(curValue);
			if (newValue != null)
			{
				functionData.chgFieldDbValue(inputField.getId(), newValue);
			}
		}

	}

	/**
	 * Performs validate mode
	 * 
	 * @return the highest message severity
	 * 
	 * @throws EQException
	 */
	public int validate() throws EQException
	{
		functionTransactions = null;
		try
		{
			// reset message severity
			functionMessages.clearMessages();

			// start the transaction boundary
			startTransaction(equationStandardSession);

			// setup journalHeader
			journalHeader = FunctionRuntimeToolbox.setupJournal(IEquationStandardObject.FCT_VAL, functionInfo.getWorkStationId(),
							screenSetMain, eqUser, functionMsgManager.getOverWarnMessages());

			// retrieve current screenset
			ScreenSet screenSet = screenSetHandler.rtvScrnSetCurrent();

			// use the function transactions to perform API validation on the field set
			functionTransactions = new FunctionTransactions(fhd, IEquationStandardObject.FCT_VAL);
			functionTransactions.setJournalHeader(journalHeader);
			internal_validate(screenSet);
		}
		catch (Exception e)
		{
			LOG.error(e);
			throw new EQException("FunctionUpdate: validate():" + Toolbox.getExceptionMessage(e), e);
		}
		finally
		{
			journalHeader = null;
			endTransaction(equationStandardSession);
			rollbackTransaction(equationStandardSession);
		}

		// returns the message severity
		return functionMessages.getMsgSev();
	}

	/**
	 * Perform validate API
	 * 
	 * @param screenSet
	 *            - the screen set to validate
	 * 
	 * @return true if successful
	 */
	private boolean internal_validate(ScreenSet screenSet) throws EQException
	{
		// assume success
		boolean success = true;

		// get other details from the function handler
		FunctionAdaptor functionAdaptor = screenSet.getFunctionAdaptor();
		Function function = screenSet.getFunction();

		// Retrieve all the field sets
		List<APIFieldSet> apiFieldSets = screenSet.getFunction().getUpdateAPIs();
		for (int i = 0; i < apiFieldSets.size(); i++)
		{
			APIFieldSet apiFieldSet = apiFieldSets.get(i);
			lastUpdateApiFieldSetId = apiFieldSet.getId();
			boolean isCrmFieldSet = isCRMFieldSet(lastUpdateApiFieldSetId);
			boolean preDefined = isCrmFieldSet || lastUpdateApiFieldSetId.equals(Function.EFC_FIELDSET)
							|| lastUpdateApiFieldSetId.equals(Function.GY_FIELDSET)
							|| lastUpdateApiFieldSetId.equals(Function.G5_FIELDSET);
			if (!preDefined)
			{
				FieldSetAdaptor fieldSetAdaptor = functionAdaptor.getFieldSetAdaptor(equationStandardSession, apiFieldSet);
				boolean shouldExecute = fieldSetAdaptor.shouldExecute(apiFieldSet);
				if (shouldExecute)
				{
					success = functionTransactions.applyTransaction(equationStandardSession, functionAdaptor, apiFieldSets.get(i),
									functionData, function, 0);

					// check messages
					checkTransaction(equationStandardSession, functionTransactions.getEquationTransactions().get(
									apiFieldSet.getId()), apiFieldSet, i);
					if (!success)
					{
						break;
					}
				}
			}
		}

		// transactions applied successfully?
		return success;
	}

	/**
	 * Performs update
	 * 
	 * @param updateMode
	 *            - update/add/maintain mode (true) or delete mode (false)
	 * 
	 * @return the highest message severity
	 * 
	 * @throws EQException
	 */
	public int update(boolean updateMode) throws EQException
	{
		// check mode
		if (modeInError(updateMode))
		{
			return functionMessages.getMsgSev();
		}

		int ckey = fhd.getFunctionKeys().getFuncKey();

		// LRP processing, but do not perform Equation database update
		if (EquationCommonContext.getContext().isLRPProcessing() && fhd.getTaskDetail() != null)
		{
			TaskRqHeader requestPayload = fhd.getTaskRqHeader();

			if (!requestPayload.isPerformUpdate() || ckey == FunctionKeys.KEY_REFER || ckey == FunctionKeys.KEY_DECL)
			{
				updateLRP(FunctionRuntimeToolbox.cvtFKeytoTaskAction(retrieveLrpKey()), fhd.getReferToUserId(), fhd.getReason(),
								updateMode);
				tranUpdateSuccess();
				return FunctionMessages.MSG_NONE;
			}
		}

		// This will return just the home session if GP is not installed
		List<EquationStandardSession> eqSessions = null;

		if (equationStandardSession.getUnit().isEnhancementInstalled(Enhancement.K542))
		{
			eqSessions = EquationCommonContext.getContext().getGlobalProcessingEquationStandardSessions(
							equationStandardSession.getSessionIdentifier());
		}

		// update status
		lastUpdateApiFieldSetId = "";

		// swap non-XA session/connection for XA session/connection if necessary
		boolean txnStartedHere = swapSessionForXASession();

		try
		{
			// debug info
			functionDebugInfo.printStartMethod("FunctionUpdate: update()");
			EqTimingTest.printStartTime("FunctionUpdate.update()", "");

			updateMade = false;
			functionTransactions = null;

			// success status
			boolean continueProcessing = true;

			// create the Equation session
			if (eqSessions != null)
			{
				startTransaction(eqSessions);
			}
			else
			{
				startTransaction(equationStandardSession);
			}

			// get the mode
			String mode = screenSetMain.getMode();
			if (mode.equals(IEquationStandardObject.FCT_FUL))
			{
				mode = IEquationStandardObject.FCT_MNT;
			}
			else if (mode.equals(IEquationStandardObject.FCT_DEL))
			{
				updateMode = false;
			}

			// setup the journal header
			if (updateMode)
			{
				journalHeader = FunctionRuntimeToolbox.setupJournal(mode, functionInfo.getWorkStationId(), screenSetMain, eqUser,
								functionMsgManager.getOverWarnMessages());
			}
			else
			{
				journalHeader = FunctionRuntimeToolbox.setupJournal(IEquationStandardObject.FCT_DEL, functionInfo
								.getWorkStationId(), screenSetMain, eqUser, functionMsgManager.getOverWarnMessages());
			}

			// for linked transaction then consider the journal of parent transaction
			if (EquationCommonContext.isLinkedSession(fhd.getFunctionInfo().getSessionType()))
			{
				ParentTransactionDetail parentTransactionDetail = fhd.getFunctionInfo().getParentTransactionDetail();
				if (parentTransactionDetail != null)
				{
					journalHeader.setArec(Toolbox.cvtBooleanToYN(parentTransactionDetail.isApplyDuringRecovery()));
					journalHeader.setAext(Toolbox.cvtBooleanToYN(parentTransactionDetail.isApplyDuringExternalInput()));
					journalHeader.setInputRef(parentTransactionDetail.getJournalHeader().getInputRef());
					journalHeader.setAppId(parentTransactionDetail.getJournalHeader().getAppId());
				}
			}

			// Maker-checker processing when completing a transaction
			continueProcessing = preMakerCheckerCompleteProcessing(ckey);

			// user exit pre-update mode
			lastUpdateApiFieldSetId = UPD_PREUPDATE;
			continueProcessing = preUpdateUserExit() < FunctionMessages.MSG_ERROR;

			// Setup the function transaction
			functionTransactions = new FunctionTransactions(fhd, Toolbox.getDeleteMode(!updateMode));
			functionTransactions.setBeforeImageTransactions(screenSetMain.getBeforeImagesTransactions());
			functionTransactions.setJournalHeader(journalHeader);

			// read all the function API field sets and perform update on each
			if (continueProcessing)
			{
				continueProcessing = updateScreenSets();
			}

			// user exit post update mode
			if (continueProcessing)
			{
				lastUpdateApiFieldSetId = UPD_POSTUPDATE;
				continueProcessing = postUpdateUserExit() < FunctionMessages.MSG_ERROR;
			}

			// force rollback?
			if (fhd.getFunctionInfo().isRollback())
			{
				functionMsgManager.generateMessage(equationStandardSession, functionMessages, screenSetHandler.getCurScreenSet(),
								UPDSCRN, "", 0, null, "KSM7371", "", "", FunctionMessages.MSG_NONE);
				continueProcessing = false;
			}

			// Maker-checker processing when submitting a transaction
			if (continueProcessing)
			{
				continueProcessing = postMakerCheckerSubmitProcessing();
			}

			// is transaction going to be rolled back?
			int msgSev = functionMessages.getMsgSev();
			boolean rollback = (msgSev >= FunctionMessages.MSG_ERROR)
							|| (generateWarningInfo && msgSev != FunctionMessages.MSG_NONE);

			// LRP update processing
			if (!rollback && EquationCommonContext.getContext().isLRPProcessing() && fhd.getTaskDetail() != null)
			{
				updateLRP(FunctionRuntimeToolbox.cvtFKeytoTaskAction(retrieveLrpKey()), fhd.getReferToUserId(), fhd.getReason(),
								updateMode);
			}

			// Idempotency check (only for enhanced XSD)
			if (!rollback && continueProcessing && screenSetMain.getFunction().chkXSDGeneric()
							&& equationStandardSession.getUnit().isIPVPFInstalled())
			{
				IdempotencyToolbox.updateProcessing(equationStandardSession, fhd, journalHeader);
			}

			// Determine message severity, if no major severity then perform final update processing
			msgSev = functionMessages.getMsgSev();

			// error?
			if (msgSev >= FunctionMessages.MSG_ERROR)
			{
				journalHeader = null;
				if (eqSessions != null)
				{
					endTransaction(eqSessions);
					rollbackTransaction(eqSessions);
				}
				else
				{
					endTransaction(equationStandardSession);
					rollbackTransaction(equationStandardSession);
				}
			}

			// generate the warning and info
			else if (generateWarningInfo && msgSev != FunctionMessages.MSG_NONE)
			{
				journalHeader = null;
				if (eqSessions != null)
				{
					endTransaction(eqSessions);
					rollbackTransaction(eqSessions);
				}
				else
				{
					endTransaction(equationStandardSession);
					rollbackTransaction(equationStandardSession);
				}
			}

			// commit the transactions
			else
			{
				functionData.clearMessages(FunctionData.CLEAR_MSG_ALL, msgSev);
				tranUpdateSuccess();

				// Commit XA transaction if it was started in this method
				if (txnStartedHere == true)
				{
					txnManager.commit();
				}

				else
				{
					if (eqSessions != null)
					{
						endTransaction(eqSessions);
						commitTransaction(eqSessions);
					}
					else
					{
						endTransaction(equationStandardSession);
						commitTransaction(equationStandardSession);
					}

					// Print journal if Print Desktop Transaction ($PDT) is enabled and a Desktop Session
					// ~ pass the journal key details
					if ((equationStandardSession instanceof Equation4Session)
									&& EquationCommonContext.getContext().getEquationUnit(
													equationStandardSession.getSessionIdentifier()).getPrintDesktop().equals("Y")
									&& EquationCommonContext.isDesktopSession(fhd.getFunctionInfo().getSessionType()))
					{
						Equation4Session s = (Equation4Session) equationStandardSession;
						s.callUTW70C(journalHeader.getProgramRoot(), journalHeader.getWorkstationID(), journalHeader.getJrnDay(),
										journalHeader.getJrnTime(), journalHeader.getJrnSequence(), journalHeader.getUser(),
										journalHeader.getFunctionMode(), "", "", "", "", journalHeader.getOption());
					}
				}
				updateMade = true;
				lastUpdateApiFieldSetId = UPD_SUCCESS;
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			functionDebugInfo.printLoadAPIError("");

			// If exception is an EQXAException then end and rollback has already occurred so don't do again.
			if (!(e instanceof EQXAException))
			{
				if (eqSessions != null)
				{
					endTransaction(eqSessions);
					rollbackTransaction(eqSessions);
				}
				else
				{
					endTransaction(equationStandardSession);
					rollbackTransaction(equationStandardSession);
				}
			}
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
		finally
		{
			// XA - Restore the earlier saved non-XA session/connection
			if (commitProcessing && equationStandardSession.getConnectionWrapper().isXA())
			{
				if (equationStandardSession.getConnectionWrapper().isTransactionConnection())
				{
					equationStandardSession.getConnectionWrapper().cleanupXAConnection();
					// equationStandardSession.closeConnection();
				}
				equationStandardSession = originalStandardSession;
				txnManager = null;
			}
			functionDebugInfo.printEndMethod("FunctionUpdate: update()");
			EqTimingTest.printTime("FunctionUpdate.update()", "");

			// Log all messages
			if (!functionMessages.chkNoMessage())
			{
				LOG.debug(LanguageResources.getString("FunctionUpdate.ReturnedMessageFromUpdate") + " : " + functionMessages);
			}

			// Final update processing
			finalUpdateUserExit(lastUpdateApiFieldSetId);
		}

		// returns the message severity
		return functionMessages.getMsgSev();
	}
	
	/**
	 * Determines if Maker-Checker processing is to proceed for the current transaction
	 * 
	 * @return true if transaction met all the prerequisites of for Maker-Checker processing
	 */
	private boolean willMakerCheckerProceed() throws EQException
	{
		boolean proceedWithMakerChecker = true;
		// Maker-Checker Enhancement K552 installed?

		// current checker must not be the same checker to authorise
		// transaction just submitted will be bypassed
		if (fhd.getFunctionSession() != null
						&& fhd.getFunctionSession().getStatus() != null
						&& !fhd.getFunctionSession().getStatus().equalsIgnoreCase(WERecordDataModel.MAKER_CHECKER_STAT_AWAIT)
						&& MakerCheckerUtility.getCurrentSupervisor(fhd, eqUser.getEquationUnit()).length() > 0
						&& MakerCheckerUtility.getCurrentSupervisor(fhd, eqUser.getEquationUnit()).equalsIgnoreCase(
										fhd.getSecurityLevel().getRequiredCheckerId()))
		{
			proceedWithMakerChecker = false;
		}

		// Maker-Checker KSM must be non-"L" in WMENU1/SOM
		if (!eqUser.getEquationUnit().isMakerCheckerKSMnonL())
		{
			proceedWithMakerChecker = false;
		}

		// ensures that the processing for a service is compatible with maker-checker
		// i.e, service contains the additional GZ fields for maker/checker
		if (!PluginVersion.isMakerCheckerService(screenSetMain.getFunction().getPluginVersion()))
		{
			proceedWithMakerChecker = false;
		}

		// service requires maker-checker processing
		if (!MakerCheckerUtility.isMakerCheckerRequired(equationStandardSession, fhd.getOptionId()))
		{
			proceedWithMakerChecker = false;
		}

		// Maker-Checker should not be suppressed
		if (functionData.rtvFieldData("GZSMCK").getDbValue().equalsIgnoreCase("Y"))
		{
			proceedWithMakerChecker = false;
		}

		return proceedWithMakerChecker;
	}

	/**
	 * Swap non-XA Session/Connection for XA Session/Connection.
	 * 
	 * Only do this if we are not already using an XA connection as the update(boolean) method is recursive and the session may
	 * already have been swapped.
	 * 
	 * @return transactionStartedHere
	 * @throws EQException
	 */
	private boolean swapSessionForXASession() throws EQException
	{
		boolean txnStartedHere = false;

		if (!equationStandardSession.getConnectionWrapper().isXA())
		{
			EquationUnit unit = equationStandardSession.getUnit();
			if (unit.getXaDataSource() != null)
			{
				// Transaction Manager will not be null if we are operating in XA mode on WAS .
				txnManager = new EquationTransactionManager();
				if (txnManager != null && !txnManager.isTransactionStarted())
				{
					// Equation is in charge of transaction if BankFusion is not installed.
					if (!EquationCommonContext.isBankFusionInstalled())
					{
						// Begin the logical transaction
						txnManager.begin();
						txnStartedHere = true;
					}
					else
					{
						// BankFusion should already have started the Transaction when dummy microflow wrapper was called.
						throw new EQException("BankFusion Transaction not started.");
					}
				}

				originalStandardSession = equationStandardSession;
				// Change the connection to an XA connection if in XA mode
				EquationLogin login = EquationCommonContext.getContext().getEquationLogin(
								equationStandardSession.getSessionIdentifier());
				EquationConnectionWrapper xaConnectionWrapper = unit.getEquationSessionPool().getXAConnection(login.getUserId(),
								login.rtvPassword(equationStandardSession), txnStartedHere, true, eqUser.getUserId());

				if (unit.getUnitVersion().equals(EquationUnit.VERSION_EQ4))
				{
					equationStandardSession = new Equation4Session(eqUser, xaConnectionWrapper);
					equationStandardSession.setSessionIdentifier(originalStandardSession.getSessionIdentifier());
				}
				else if (unit.getUnitVersion().equals(EquationUnit.VERSION_EQ38))
				{
					equationStandardSession = new Equation38Session(eqUser, xaConnectionWrapper);
					equationStandardSession.setSessionIdentifier(originalStandardSession.getSessionIdentifier());
					// throw new UnsupportedOperationException("Invalid unit level");
				}
			}
		}
		return txnStartedHere;
	}

	/**
	 * Read all the function API field sets and perform update on each
	 * 
	 * @return contiueProcessing to indicate whether update process should proceed
	 * @throws EQException
	 */
	private boolean updateScreenSets() throws EQException
	{
		boolean continueProcessing = true;
		for (int i = 0; i < screenSetHandler.getScreenSets().size(); i++)
		{
			screenSetHandler.setCurScreenSet(i);
			functionDebugInfo.printUpdateScreenSet(i);

			// get the current screen set
			ScreenSet screenSet = screenSetHandler.rtvScrnSetCurrent();

			// check before image against current database content (only perform this for desktop transactions)
			if (beforeImageProcessing == BEFORE_IMAGE_BIZ_LAYER && screenSet.getFunctionDataBefore() != null)
			{
				if (EquationCommonContext.isDesktopSession(functionInfo.getSessionType()))
				{
					continueProcessing = !checkIntermediateUpdate(screenSet);
					if (!continueProcessing)
					{
						break;
					}
				}
			}

			// main function (screenset ==0)
			if (screenSetHandler.getCurScreenSet() == 0)
			{
				// apply the transaction
				continueProcessing = internal_update(screenSet);

				// journal the transaction
				if (continueProcessing)
				{
					functionDebugInfo.printJournal();

					FunctionAdaptor functionAdaptor = screenSet.getFunctionAdaptor();

					// during API, resetup warning messages
					if (EquationCommonContext.isNonBrowserMode(functionInfo.getSessionType())
									&& functionMessages.getMsgSev() == FunctionMessages.MSG_WARN)
					{
						journalHeader.setWarningMessages(functionMessages.rtvDsepms());
						journalHeader.setAuthorisors(functionMessages.rtvAuthorisor());
					}

					// write the journal
					if (screenSet.chkEnquiry())
					{
						lastUpdateApiFieldSetId = Function.G5_FIELDSET;
						continueProcessing = functionTransactions.writeJournalEnquiry(equationStandardSession, journalHeader,
										screenSet.getFunction(), functionData, functionAdaptor);
						// Check for error messages returned from API call
						checkTransaction(equationStandardSession, functionTransactions.getEquationTransactions().get(
										Function.G5_FIELDSET), null, i);
					}
					else
					{
						lastUpdateApiFieldSetId = Function.GY_FIELDSET;
						continueProcessing = functionTransactions.writeJournalTransaction(equationStandardSession, journalHeader,
										screenSet.getFunction(), functionData, screenSet.getFunctionDataBefore(), functionAdaptor,
										functionInfo.getSessionType() != EquationCommonContext.SESSION_RECOVERY);
						// Check for error messages returned from API call
						checkTransaction(equationStandardSession, functionTransactions.getEquationTransactions().get(
										Function.GY_FIELDSET), null, i);
					}

					// debugging info
					if (continueProcessing)
					{
						functionDebugInfo.printJournalComplete();
					}
					else
					{
						functionDebugInfo.printJournalError();
					}
				}
			}

			// succeeding functions (screenset > 0)
			else
			{
				// does the screen set want to do its own update?
				lastUpdateApiFieldSetId = i == 1 ? Function.CRM_FIELDSET : Function.EFC_FIELDSET;
				boolean update = screenSet.update(journalHeader, functionTransactions, equationStandardSession);

				// yes, the screen set wants to do its own update
				if (update)
				{
					continueProcessing = screenSet.getFunctionMessages().getMsgSev() <= FunctionMessages.MSG_WARN;
					if (screenSet.getFunctionMessages().getMsgSev() >= FunctionMessages.MSG_ERROR
									|| !(screenSet instanceof ScreenSetAC2))
					{
						functionMsgManager.generateMessages(equationStandardSession, functionMessages, screenSetHandler
										.getCurScreenSet(), UPDSCRN, "", 0, null, screenSet.getFunctionMessages().rtvDsepms(), "",
										"", FunctionMessages.MSG_NONE);
					}
				}

				// no, standard update via update API
				else
				{
					continueProcessing = internal_update(screenSet);
				}
			}

			// has the transaction been successfully applied?
			if (continueProcessing)
			{
				functionDebugInfo.printUpdateScreenSetComplete(i);
			}
			else
			{
				functionDebugInfo.printUpdateScreenSetError(i);
				break;
			}
		}
		return continueProcessing;
	}

	/**
	 * @param fieldSetId
	 *            - the ID of the FieldSet to check
	 * @return true if the FieldSet is a CRM FieldSet, otherwise false.
	 */
	private boolean isCRMFieldSet(String fieldSetId)
	{
		String id;
		if (fieldSetId.length() > 3)
		{
			id = fieldSetId.substring(0, 3);
		}
		else
		{
			id = fieldSetId;
		}
		return id.equals(Function.CRM_FIELDSET);
	}

	/**
	 * Apply transactions during update
	 * 
	 * @param session
	 *            - the Equation session
	 * @param apiFieldSets
	 *            - list of API field sets
	 * @param functionData
	 *            - function data
	 * 
	 * @return true - if all transactions have been successfully applied
	 * 
	 * @throws EQException
	 */
	public boolean internal_update(ScreenSet screenSet) throws EQException
	{
		// assume true
		boolean success = true;

		// get other details
		Function function = screenSet.getFunction();
		List<APIFieldSet> apiFieldSets = function.getUpdateAPIs();
		FunctionAdaptor functionAdaptor = screenSet.getFunctionAdaptor();

		// Retrieve all the field sets
		for (int i = 0; i < apiFieldSets.size(); i++)
		{
			// As we use different sessions for different parts of the transaction, lets have a "current handle"
			EquationStandardSession currentSession = equationStandardSession;

			APIFieldSet apiFieldSet = apiFieldSets.get(i);
			lastUpdateApiFieldSetId = apiFieldSet.getId();

			boolean isCrmFieldSet = isCRMFieldSet(lastUpdateApiFieldSetId);

			boolean preDefined = isCrmFieldSet || lastUpdateApiFieldSetId.equals(Function.EFC_FIELDSET)
							|| lastUpdateApiFieldSetId.equals(Function.GY_FIELDSET)
							|| lastUpdateApiFieldSetId.equals(Function.G5_FIELDSET);
			if (!preDefined)
			{
				boolean shouldExecute = true;

				// recovery - if underlying API is always recovered, then do not update anymore
				if (EquationCommonContext.isRecoverySession(fhd.getFunctionInfo().getSessionType()))
				{
					shouldExecute = !apiFieldSet.isAlwaysApplyInRecovery();
				}

				// external input - - if underlying API is always externally input, then do not update anymore
				else if (EquationCommonContext.isExtInpSession(fhd.getFunctionInfo().getSessionType()))
				{
					shouldExecute = !apiFieldSet.isAlwaysApplyInExtInput();
				}

				// execute?
				if (shouldExecute)
				{
					FieldSetAdaptor fieldSetAdaptor = functionAdaptor.getFieldSetAdaptor(equationStandardSession, apiFieldSet);
					shouldExecute = fieldSetAdaptor.shouldExecute(apiFieldSet);
					if (shouldExecute)
					{

						// *************************************************************************************************************
						// GLOBAL PROCESSING: Need to see if the current unit in which to execute the API is anything but the
						// default...
						String currentUnit = functionData.rtvFieldInputValue("CURUNT");
						String currentSystem = functionData.rtvFieldInputValue("CURSYS");
						if (currentUnit != null
										&& !currentUnit.trim().equals("")
										&& currentSystem != null
										&& !currentSystem.trim().equals("")
										&& ((!currentUnit.equals(equationStandardSession.getUnitId()) || !currentSystem
														.equals(equationStandardSession.getSystemId()))))
						{

							if (!SystemStatusManager.getInstance().getUnitStatus(currentSystem, currentUnit).isAvailable())
							{
								currentSession = equationStandardSession;
								shouldExecute = false;
							}
							else
							{

								// should use a session dedicated to the user in the unit specified...
								currentSession = EquationCommonContext.getContext().getEquationUser(currentSystem, currentUnit,
												equationStandardSession.getSessionIdentifier()).getSession();
							}
							// Need to reset the current unit so that next time it will be reset
							functionData.chgFieldInputValue("CURUNT", "");
							functionData.chgFieldInputValue("CURSYS", "");
						}
						// *************************************************************************************************************

						if (shouldExecute)
						{
							success = functionTransactions.applyTransaction(currentSession, functionAdaptor, apiFieldSet,
											functionData, function, 0);

							// check messages
							checkTransaction(currentSession, functionTransactions.getEquationTransactions()
											.get(apiFieldSet.getId()), apiFieldSet, i);
							if (!success)
							{
								break;
							}
						}
					}
				}
			}
		}
		// transactions applied successfully?
		return success;
	}

	/**
	 * Check the returned messages of a transaction during load
	 * 
	 * @param transactions
	 *            list of transactions
	 * 
	 * @return the highest message severity
	 */
	private int checkTransactionLoad(EquationStandardSession session, IEquationStandardObject transaction, APIFieldSet apiFieldSet,
					int index)
	{
		// any messages?
		List<EQMessage> eqMessages = transaction.getMessages();
		RelatedFields relatedFields = null;
		if (apiFieldSet != null)
		{
			relatedFields = new RelatedFields(apiFieldSet.getRelatedFields());
		}
		else
		{
			relatedFields = new RelatedFields("");
		}
		// copy the messages?
		for (EQMessage eqMessage : eqMessages)
		{
			if (!FunctionRuntimeToolbox.isIgnoreKsmAtLoad(eqMessage.getMessageID()))
			{
				if (!transaction.getValid() || !apiFieldSet.chkIgnoreMessage(eqMessage.getMessageID()))
				{
					functionMsgManager.generateMessage(functionMessages, screenSetHandler.getCurScreenSet(), UPDSCRN + index,
									relatedFields.getRelatedFields(), 0, functionData.rtvFieldData(relatedFields.getFirstField()),
									eqMessage, "", LanguageResources.getFormattedString(
													"FunctionUpdate.ReturnedMessageFromLoadAPI", transaction.getId()),
									FunctionMessages.MSG_NONE);
				}
			}
		}

		return functionMessages.getMsgSev();
	}

	/**
	 * Check the returned messages of a transaction
	 * 
	 * @param transactions
	 *            list of transactions
	 * 
	 * @return the highest message severity
	 */
	private int checkTransaction(EquationStandardSession session, IEquationStandardObject transaction, APIFieldSet apiFieldSet,
					int index)
	{
		// any messages?
		List<EQMessage> eqMessages = transaction.getMessages();
		RelatedFields relFields;
		if (apiFieldSet != null)
		{
			relFields = new RelatedFields(apiFieldSet.getRelatedFields());
		}
		else
		{
			relFields = new RelatedFields("");
		}
		// copy the messages?
		for (EQMessage eqMessage : eqMessages)
		{
			if (!transaction.getValid() || apiFieldSet == null || !apiFieldSet.chkIgnoreMessage(eqMessage.getMessageID()))
			{
				functionMsgManager.generateMessage(functionMessages, screenSetHandler.getCurScreenSet(), UPDSCRN + index, relFields
								.getRelatedFields(), 0, functionData.rtvFieldData(relFields.getFirstField()), eqMessage, "",
								LanguageResources.getFormattedString("FunctionUpdate.ReturnedMessageFromAPI", transaction.getId()),
								FunctionMessages.MSG_NONE);
			}
		}

		return functionMessages.getMsgSev();
	}

	/**
	 * Perform processing when all transactions have been applied without major severity
	 * 
	 * @param session
	 *            - Equation standard session
	 * @throws EQException
	 */
	private void tranUpdateSuccess() throws EQException
	{
		// Remove the session record if exists
		if (functionSession != null)
		{
			if (functionSession.isSessionRestored())
			{
				functionSession.delete(equationStandardSession, false);
			}
		}

		// Task completed?
		if (EquationCommonContext.getContext().isLRPProcessing() && fhd.getTaskDetail() != null)
		{
			fhd.setCompletedTask(fhd.getTaskDetail().getTkiid());
		}
	}

	/**
	 * Check for intermediate update
	 * 
	 * @return true if intermediate update error
	 */
	private boolean checkIntermediateUpdate(ScreenSet screenSet) throws EQException
	{
		// create a copy of the before image
		FunctionData functionDataBefore = screenSet.getFunctionDataBefore().duplicateFunctionData();

		// backup the existing function data
		FunctionData backupfunctionData = screenSet.getFunctionData();

		// set the screen set function data
		screenSet.setFunctionData(screenSet.getFunctionDataBefore());
		screenSet.getFunctionAdaptor().resetFunctionData(screenSet.getFunctionDataBefore());

		// reload API
		FunctionUpdate functionUpdate = new FunctionUpdate(fhd);
		functionUpdate.setCommitProcessing(false);
		InputFieldSet inputFieldSet = screenSet.getMainInputFieldSet();

		// clear all repeating datas
		screenSet.getFunctionData().clearRepeatingData();

		// retrieve details
		functionUpdate.retrieve(screenSet, inputFieldSet);

		// compare
		boolean error = false;
		String id = screenSet.getBeforeImagesTransactions().compare(screenSet,
						functionUpdate.getFunctionTransactions().getEquationTransactions());
		if (id.length() > 0)
		{
			functionMsgManager.generateMessage(equationStandardSession, functionMessages, screenSetHandler.getCurScreenSet(),
							UPDSCRN, "", 0, null, "KSM7365" + id, "", LanguageResources.getFormattedString(
											"FunctionUpdate.IntermediateUpdateLoadAPI", id), FunctionMessages.MSG_NONE);
			error = true;
		}

		// compare each field
		String fieldName = functionDataBefore.equalFd(screenSet.getFunction(), screenSet.getFunctionData());
		if (fieldName.length() > 0)
		{
			functionMsgManager.generateMessage(equationStandardSession, functionMessages, screenSetHandler.getCurScreenSet(),
							UPDSCRN, "", 0, null, "KSM7366" + fieldName, "", LanguageResources.getFormattedString(
											"FunctionUpdate.IntermediateUpdateField", fieldName), FunctionMessages.MSG_NONE);

			error = true;
		}

		// restore the function data
		screenSet.setFunctionData(backupfunctionData);
		screenSet.setFunctionDataBefore(functionDataBefore);
		screenSet.getFunctionAdaptor().resetFunctionData(backupfunctionData);

		// need to overwrite the list of functions in the function transaction's before image
		// because in case other fields (not relevant to this transaction) were maintained by
		// other user (after loading the details), then this transaction should not overwrite
		// those fields
		if (!error)
		{
			FunctionTransactions fts = new FunctionTransactions(fhd, "");
			fts.addTransaction(functionUpdate.getFunctionTransactions().getEquationTransactions());
			functionTransactions.setBeforeImageTransactions(fts);
		}

		return error;
	}

	/**
	 * Call pre update user exit
	 * 
	 * @return the message severity
	 * 
	 * @throws EQException
	 */
	private int preUpdateUserExit() throws EQException
	{
		// JAVA user exit
		try
		{
			FunctionAdaptor functionAdaptor = screenSetMain.getFunctionAdaptor();
			functionAdaptor.preUpdate(journalHeader);
			if (functionAdaptor.getFunctionAdaptorImpl() != null)
			{
				List<UserExitMessage> messages = functionAdaptor.getFunctionAdaptorImpl().getReturnMessages().getReturnMessages();
				if (messages.size() > 0)
				{
					String secondLevelText = LanguageResources.getString("FunctionUpdate.ReturnedMessageFromUserExitPreUpdateMode");
					LOG.debug(secondLevelText + " : " + functionAdaptor.getFunctionAdaptorImpl().getReturnMessages());
					functionMsgManager
									.generateMessages(equationStandardSession, functionMessages, screenSetMain.getId(), PREUPDSCRN,
													"", 0, null, messages, "", secondLevelText, functionData,
													FunctionMessages.MSG_NONE);
				}
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			functionMsgManager.generateMessage(equationStandardSession, functionMessages, screenSetMain.getId(), PREUPDSCRN, "", 0,
							null, "KSM7377", "", Toolbox.getExceptionMessage(e), FunctionMessages.MSG_NONE);
		}

		// return message severity
		return functionMessages.getMsgSev();
	}

	/**
	 * Call post update user exit
	 * 
	 * @return the message severity
	 * 
	 * @throws EQException
	 */
	private int postUpdateUserExit() throws EQException
	{
		// JAVA user exit
		FunctionAdaptor functionAdaptor = screenSetMain.getFunctionAdaptor();
		try
		{
			EqTimingTest.printStartTime("FunctionUpdate.postUpdateUserExit", "Java");
			functionAdaptor.postUpdate(journalHeader, functionTransactions.getEquationTransactions());
			if (functionAdaptor.getFunctionAdaptorImpl() != null)
			{
				List<UserExitMessage> messages = functionAdaptor.getFunctionAdaptorImpl().getReturnMessages().getReturnMessages();
				if (messages.size() > 0)
				{
					String secondLevelText = LanguageResources
									.getString("FunctionUpdate.ReturnedMessageFromUserExitPostUpdateMode");
					LOG.debug(secondLevelText + " : " + functionAdaptor.getFunctionAdaptorImpl().getReturnMessages());
					functionMsgManager.generateMessages(equationStandardSession, functionMessages, screenSetMain.getId(),
									POSTUPDSCRN, "", 0, null, messages, "", secondLevelText, functionData,
									FunctionMessages.MSG_NONE);
				}
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			functionMsgManager.generateMessage(equationStandardSession, functionMessages, screenSetMain.getId(), POSTUPDSCRN, "",
							0, null, "KSM7377", "", Toolbox.getExceptionMessage(e), FunctionMessages.MSG_NONE);
		}
		finally
		{
			EqTimingTest.printTime("FunctionUpdate.postUpdateUserExit", "Java");
		}

		// Bankfusion user exit
		if (EquationCommonContext.isBankFusionInstalled())
		{
			try
			{
				EqTimingTest.printStartTime("FunctionUpdate.postUpdateUserExit", "BankFusion");
				FunctionBankFusionLinkService functionBankFusionLinkService = new FunctionBankFusionLinkService();
				ReturnDataMFUserExit returnData = functionBankFusionLinkService.callPostUpdateUserExitLinkService(screenSetMain);

				if (functionBankFusionLinkService.isBFNotInstalledError())
				{
					String secondLevelText = LanguageResources
									.getString("FunctionUpdate.ReturnedMessageFromMicroflowPostUpdateMode");
					functionMsgManager.generateMessage(equationStandardSession, functionMessages, screenSetMain.getId(),
									POSTUPDSCRN, "", 0, null, "KSM7340" + LanguageResources.getString("Language.BankNotInstalled"),
									"", secondLevelText, FunctionMessages.MSG_NONE);
				}

				else
				{
					if (returnData != null && returnData.getMessages() != null && returnData.getMessages().size() > 0)
					{
						String secondLevelText = LanguageResources
										.getString("FunctionUpdate.ReturnedMessageFromMicroflowPostUpdateMode");
						LOG.debug(secondLevelText + " : " + returnData);
						functionMsgManager.generateMessages(equationStandardSession, functionMessages, screenSetMain.getId(),
										POSTUPDSCRN, "", 0, null, returnData.getMessages(), "", secondLevelText, functionData,
										FunctionMessages.MSG_NONE);
					}
				}
			}
			catch (Exception e)
			{
				LOG.error(e);
				functionMsgManager.generateMessage(equationStandardSession, functionMessages, screenSetMain.getId(), POSTUPDSCRN,
								"", 0, null, "KSM7377", "", Toolbox.getExceptionMessage(e), FunctionMessages.MSG_NONE);
			}
			finally
			{
				EqTimingTest.printTime("FunctionUpdate.postUpdateUserExit", "BankFusion");
			}
		}
		// return message severity
		return functionMessages.getMsgSev();
	}

	/**
	 * Call final update user exit
	 * 
	 * @param lastUpdateApiFieldSetId
	 *            - the last update api field set
	 * 
	 * @return the message severity
	 * 
	 * @throws EQException
	 */
	private int finalUpdateUserExit(String lastUpdateApiFieldSetId) throws EQException
	{
		// JAVA user exit
		FunctionAdaptor functionAdaptor = screenSetMain.getFunctionAdaptor();
		try
		{
			EqTimingTest.printStartTime("FunctionUpdate.finalUpdateUserExit", "Java");
			functionAdaptor.onFinalUpdate(journalHeader, lastUpdateApiFieldSetId, functionMessages);
		}
		catch (Exception e)
		{
			LOG.error(e);
			functionMsgManager.generateMessage(equationStandardSession, functionMessages, screenSetMain.getId(), FINALUPDSCRN, "",
							0, null, "KSM7377", "", Toolbox.getExceptionMessage(e), FunctionMessages.MSG_NONE);
		}
		finally
		{
			EqTimingTest.printTime("FunctionUpdate.finalUpdateUserExit", "Java");
		}

		// return message severity
		return functionMessages.getMsgSev();
	}

	/**
	 * Determine the number of bytes to be loaded from the API
	 * 
	 * @param currentBytes
	 *            - the current number of bytes already loaded
	 * @return the number of bytes to be loaded from the API
	 */
	private long getLimitBytes(long currentBytes)
	{
		long remaining = 0;

		// maximum bytes not specified, then simply return the value of the maximum bytes per api
		if (maximumBytesAllLoadApi == 0)
		{
			remaining = maximumBytesPerLoadApi;
		}

		// maximum bytes per api not specified, then simply return the value of the maximum bytes
		else if (maximumBytesPerLoadApi == 0)
		{
			remaining = maximumBytesAllLoadApi - currentBytes;
		}

		// calculate
		else
		{
			remaining = maximumBytesAllLoadApi - currentBytes;
			if (remaining > maximumBytesPerLoadApi)
			{
				remaining = maximumBytesPerLoadApi;
			}
		}

		// negative - this should not happen, in any case, simply return one row of data
		if (remaining < 0)
		{
			remaining = 1;
		}

		// return
		return remaining;
	}

	/**
	 * Determine if mode is correct
	 * 
	 * @param updateMode
	 *            - add/update (true) or delete (false)
	 * @return
	 */
	private boolean modeInError(boolean updateMode) throws EQException
	{
		// In delete mode, then ensure delete mode is valid, which is valid only for Delete services or
		// fully functional services
		if (!updateMode)
		{
			String mode = screenSetMain.getMode();
			if (!screenSetMain.getFunction().isAllowedDel() || !mode.equals(IEquationStandardObject.FCT_DEL)
							&& !mode.equals(IEquationStandardObject.FCT_FUL))
			{
				functionMsgManager.generateMessage(equationStandardSession, functionMessages, screenSetHandler.getCurScreenSet(),
								UPDSCRN, "", 0, null, "KSM7360", "", "", FunctionMessages.MSG_NONE);
				return true;
			}
		}

		// If the expected mode is not the same as the automatically derived mode
		String expectedMode = fhd.getServiceMode(); // expected mode
		String interimMode = screenSetMain.getMode(); // derived mode
		if (expectedMode != null && expectedMode.length() != 0)
		{
			// this must be an Add function
			if (expectedMode.equals(IEquationStandardObject.FCT_ADD))
			{
				// .. but already exist
				if (!interimMode.equals(IEquationStandardObject.FCT_ADD))
				{
					functionMsgManager.generateMessage(equationStandardSession, functionMessages, screenSetHandler
									.getCurScreenSet(), UPDSCRN, "", 0, null, "KSM0004", "", "", FunctionMessages.MSG_NONE);
					return true;
				}
			}

			// this must be a Maintain mode
			else if (expectedMode.equals(IEquationStandardObject.FCT_MNT) || expectedMode.equals(IEquationStandardObject.FCT_DEL)
							|| expectedMode.equals(IEquationStandardObject.FCT_ENQ))
			{
				// .. but does not exist
				if (interimMode.equals(IEquationStandardObject.FCT_ADD))
				{
					functionMsgManager.generateMessage(equationStandardSession, functionMessages, screenSetHandler
									.getCurScreenSet(), UPDSCRN, "", 0, null, "KSM2022", "", "", FunctionMessages.MSG_NONE);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Perform completion of task
	 * 
	 * @param taskAction
	 *            - the task action
	 * @param referToUser
	 *            - refer to user (if referred)
	 * @param comment
	 *            - comment
	 * @param updateMode
	 *            - true if maintain mode
	 * 
	 * @throws EQException
	 */
	public void updateLRP(String taskAction, String referToUser, String comment, boolean updateMode) throws EQException
	{
		TaskDetail taskDetail = fhd.getTaskDetail();
		if (taskDetail == null)
		{
			return;
		}

		String taskId = taskDetail.getTkiid();
		if (taskId.trim().length() == 0)
		{
			return;
		}

		// Task engine
		ITaskEngine taskEngine = EquationFunctionContext.getContext().getTaskEngine(fhd.getFunctionInfo().getSessionId());

		// make sure user still owns the task
		String actualUser = EquationFunctionContext.getContext().getLoginUserBySessionId(
						equationStandardSession.getSessionIdentifier());
		String eqUserX = fhd.getEquationUser().getUserId();
		if (!taskEngine.isTaskOwnedByUser(taskDetail.getTkiid(), actualUser))
		{
			functionMsgManager.generateMessage(equationStandardSession, functionMessages, 0, UPDSCRN, "", 0, null, "KSM7609", "",
							"", FunctionMessages.MSG_NONE);
			return;
		}

		// Refer - just refer the task without completing it
		if (taskAction.equals(TaskEngineToolbox.TASK_ACTION_REFER))
		{
			// can user claim the task?
			String userId = referToUser; // OK for CAS
			if (EquationFunctionContext.getContext().isEquationAuthentication())
			{
				userId = LRPToolbox.correctUserCase(eqUser.getEquationUnit().rtvFullUserId(referToUser));
			}
			if (!taskEngine.isUserCanClaimTask(taskId, userId))
			{
				functionMsgManager.generateMessage(equationStandardSession, functionMessages, 0, UPDSCRN, "", 0, null, "KSM7605"
								+ userId.toUpperCase(), "", "", FunctionMessages.MSG_NONE);
				return;
			}

			taskEngine.reReferTask(taskId, taskDetail.getOwner(), userId, comment);
			return;
		}

		// Function BankFusion
		FunctionBankFusion functionBankFusion = new FunctionBankFusion();

		// Messages
		MessageStatus messageStatus = new MessageStatus();
		messageStatus.setEqMessages(functionBankFusion.rtvMessagesAsMessageArray(fhd.getFunctionMsgManager().getFunctionMessages()
						.getMessages(), screenSetHandler));
		messageStatus.setOverallStatus(FunctionRuntimeToolbox.cvtOverallStatus(fhd.getFunctionMsgManager().getFunctionMessages()
						.getMsgSev()));

		// Request payload
		TaskRqHeader requestPayload = fhd.getTaskRqHeader();

		// Respond payload
		TaskRsHeader respondPayload = LRPToolbox.getRespondPayload();
		respondPayload.setMessages(messageStatus);
		respondPayload.setReason(comment);
		respondPayload.setTaskAction(taskAction);

		// set function mode
		respondPayload.setFunctionMode(screenSetMain.getMode());
		if (screenSetMain.getMode().equals(IEquationStandardObject.FCT_FUL))
		{
			if (updateMode)
			{
				respondPayload.setFunctionMode(IEquationStandardObject.FCT_MNT);
			}
			else
			{
				respondPayload.setFunctionMode(IEquationStandardObject.FCT_DEL);
			}
		}

		// Update service data
		ConversionRules conversionRules = FunctionRuntimeToolbox.getConversionRules(null, fhd);
		conversionRules.cvtToResponse();
		Object bf_functionData = functionBankFusion.getBankFusionDataType(equationStandardSession, screenSetMain
						.getFunctionAdaptor(), screenSetMain.getFunction(), functionData, false, conversionRules);
		respondPayload.setServiceData(bf_functionData);

		// Update CRM
		if (screenSetHandler.isScreenSetCRMExist())
		{
			FunctionBankFusionSrv functionBankFusionSrv = new FunctionBankFusionSrv();
			ScreenSetCRM screenSetCRM = (ScreenSetCRM) screenSetHandler.rtvScreenSet(ScreenSetHandler.FUNCTION_CRM_SCREEN);
			Object crmData = functionBankFusionSrv.getBankFusionDataTypeCRM(equationStandardSession, screenSetCRM);
			respondPayload.setCrmData(crmData);
		}

		// Update EFC
		if (screenSetHandler.isScreenSetEFCExist())
		{
			FunctionBankFusionSrv functionBankFusionSrv = new FunctionBankFusionSrv();
			ScreenSetAC2 screenSetAC2 = (ScreenSetAC2) screenSetHandler.rtvScreenSet(ScreenSetHandler.FUNCTION_EFC_SCREEN_1);
			Object efcData = functionBankFusionSrv.getBankFusionDataTypeAC2(equationStandardSession, screenSetAC2);
			respondPayload.setEfcData(efcData);
		}

		// complete the task
		taskEngine.completeTask(taskId, respondPayload, comment);
	}

	/**
	 * Retrieve the function key for LRP processing
	 * 
	 * @return the function key for LRP processing
	 */
	private int retrieveLrpKey()
	{
		// authorisation not allowed, then assume enter key. Note: this is needed as EFC also uses EFC F18=Accept
		int ckey = fhd.getFunctionKeys().getFuncKey();
		if (ckey == FunctionKeys.KEY_AUTHA && !fhd.getSecurityLevel().isAuthorizeAllowed())
		{
			ckey = FunctionKeys.KEY_ENT;
		}

		// delete on a data entry, then assume enter key
		if (ckey == FunctionKeys.KEY_DEL
						&& fhd.getTaskRqHeader().getBasicDetail().getTaskType().equals(TaskEngineToolbox.TASK_TYPE_DATA_ENTRY))
		{
			ckey = FunctionKeys.KEY_ENT;
		}

		return ckey;
	}

	/**
	 * Maker Checker during API update processing
	 * 
	 * @param superId
	 *            - the supervisor Id
	 * @param externalInput
	 *            - external input processing (or API)?
	 * 
	 * @throws EQException
	 */
	private void makerCheckerDuringAPI(String superId, boolean externalInput) throws EQException
	{
		// rollback transactions
		rollbackTransaction(equationStandardSession);
		endTransaction(equationStandardSession);

		// restart commitment boundary
		startTransaction(equationStandardSession);

		// set to maker checker
		fhd.getSecurityLevel().setRequiredCheckerType(SecurityLevel.CHCKR_MAKER_CHECKER);

		// set the WE status
		String status = WERecordDataModel.MAKER_CHECKER_STAT_AWAIT;
		functionData.chgFieldDbValue(FunctionData.FLD_STAT_MKR_CHKR, status);

		MakerCheckerUtility.updateMakerCheckerStatus(equationStandardSession, FunctionKeys.KEY_ACCPT, "", fhd, superId,
						externalInput);
		fhd.getFunctionSession().save(equationStandardSession, false, fhd.getScreenSetHandler(), fhd.getFunctionMsgManager(),
						superId, status, WERecordDataModel.LVL_ALL, true);

		// transfer messages from other messages to the function messages
		functionMessages.insertMessages(functionMsgManager.getOtherMessages());

		// reset functionSession so the session record and WE record for resubmitted txns won't be deleted
		functionSession = null;
	}

	/**
	 * This method temporarily creates a WEPF record for the transaction with a status of Completed This allows the maker to see the
	 * transaction as completed by the checker in the referral tab
	 */
	private void makerCheckerInsertCompletedStatus() throws EQException
	{

		DaoFactory daoFactory = new DaoFactory();
		FunctionSession fs = fhd.getFunctionSession();

		// Create a temporary session Id for this WEPF record based on the existing session Id
		// to avoid duplicate key error
		String tempRecordSessionId;
		if (fs.getSessionId().trim().length() < 50)
		{
			tempRecordSessionId = fs.getSessionId().trim() + "T";
		}
		else
		{
			// For CAS session id, overwrite the non-unique prefix (TGT) of the session id with 'TMP'
			tempRecordSessionId = "TMP" + fs.getSessionId().substring(3);
		}

		WERecordDataModel weRecord = new WERecordDataModel(fs.getOptionId().trim(), tempRecordSessionId, fs.getTransactionId()
						.trim(), fs.getUserId().trim(), "", "", screenSetHandler.rtvScrnSetCurrent().getScrnNo() + 1,
						screenSetHandler.getCurScreenSet(), screenSetHandler.getLastScreenSetViewed());

		weRecord.setAuthStat(WERecordDataModel.MAKER_CHECKER_STAT_COMPL);
		weRecord.setAuthLevel("A");
		weRecord.setAuthorisor(eqUser.getUserId());
		weRecord.setUserAlerted("N");
		weRecord.setOptionTitle(screenSetHandler.rtvScreenSetMain().getFunction().getLabel());
		weRecord.setMessageAmounts(functionMsgManager.getFunctionMessages().rtvAmounts());
		weRecord.setMessageBranches(functionMsgManager.getFunctionMessages().rtvBranches());
		weRecord.setOfflineRequest("Y");
		Calendar cal = Calendar.getInstance();
		weRecord.setTranTime(Toolbox.getTimeDBFormat(cal));
		weRecord.setTranDate(Toolbox.getDateDBFormat(cal));

		IWERecordDao weDao = daoFactory.getWEDao(equationStandardSession, weRecord);
		weDao.insertRecord(weRecord);
	}

	/**
	 * Maker checker complete processing (before any Equation service update)
	 * 
	 * @param ckey
	 *            - the function keys
	 * 
	 * @return true if no error
	 * 
	 * @throws EQException
	 */
	private boolean preMakerCheckerCompleteProcessing(int ckey) throws EQException
	{
		// Maker checker not installed
		if (!eqUser.getEquationUnit().hasMakerCheckerEnhancement())
		{
			return true;
		}

		// assume no issues
		int msgSev = FunctionMessages.MSG_NONE;

		// desktop session, the processing is to issue the warning
		if (EquationCommonContext.isDesktopSession(fhd.getFunctionInfo().getSessionType()))
		{
			if (fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_MAKER_MAKER
							|| fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_MAKER_CHECKER)
			{
				msgSev = MakerCheckerUtility.updateMakerCheckerStatus(equationStandardSession, ckey, "", fhd, eqUser.getUserId(),
								false);
				if (fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_MAKER_CHECKER)
				{
					makerCheckerInsertCompletedStatus();
				}
			}
		}

		// Recovery or External Input or API
		else if (fhd.getFunctionInfo().getSessionType() == EquationCommonContext.SESSION_RECOVERY
						|| fhd.getFunctionInfo().getSessionType() == EquationCommonContext.SESSION_EXT_INPUT)
		{
			// submitted during normal input - then add this to WE
			if (functionData.rtvFieldDbValue(FunctionData.FLD_STAT_MKR_CHKR).equals(WERecordDataModel.MAKER_CHECKER_STAT_COMPL))
			{
				// retrieve session id from data area
				EquationDataStructureData svjob4 = equationStandardSession.getSVJOB4EX();
				fhd.setFunctionSession(new FunctionSession(fhd.getOptionId(), svjob4.getFieldValue(EqDS_SVJOB4.SVRSID), svjob4
								.getFieldValue(EqDS_SVJOB4.SVRUSID), svjob4.getFieldValue(EqDS_SVJOB4.SVRTID)));
				fhd.getFunctionSession().readWE(equationStandardSession);
				msgSev = MakerCheckerUtility.updateMakerCheckerStatus(equationStandardSession, FunctionKeys.KEY_OVR, "", fhd,
								eqUser.getUserId(), false);

				// transfer messages from other messages to the function messages
				functionMessages.insertMessages(functionMsgManager.getOtherMessages());
			}
		}

		// note during API - you are not allowed to complete via online API
		else if (fhd.getFunctionInfo().getSessionType() == EquationCommonContext.SESSION_API_MODE)
		{
		}

		// continue processing?
		return msgSev < FunctionMessages.MSG_ERROR;
	}

	/**
	 * Maker checker submit processing (after any Equation service update)
	 * 
	 * @return true - to continue processing
	 * 
	 * @throws EQException
	 */
	private boolean postMakerCheckerSubmitProcessing() throws EQException
	{
		// no maker checker?
		if (!eqUser.getEquationUnit().hasMakerCheckerEnhancement())
		{
			return true;
		}

		// desktop session, the processing is to issue the warning
		if (EquationCommonContext.isDesktopSession(fhd.getFunctionInfo().getSessionType()) && willMakerCheckerProceed())
		{
			// if transaction requires maker-checker processing, insert corresponding maker-checker history record
			if (fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_NONE)
			{
				functionMsgManager.generateMessage(equationStandardSession, functionMessages, screenSetHandler.getCurScreenSet(),
								UPDSCRN, "", 0, null, MakerCheckerUtility.MAKER_CHECKER_KSM, "", "", FunctionMessages.MSG_NONE);
				return false;
			}
		}

		// Recovery or External Input
		if (fhd.getFunctionInfo().getSessionType() == EquationCommonContext.SESSION_RECOVERY
						|| fhd.getFunctionInfo().getSessionType() == EquationCommonContext.SESSION_EXT_INPUT)
		{
			// retrieve session id from data area
			EquationDataStructureData svjob4 = equationStandardSession.getSVJOB4EX();
			fhd.setFunctionSession(new FunctionSession(fhd.getOptionId(), svjob4.getFieldValue(EqDS_SVJOB4.SVRSID), svjob4
							.getFieldValue(EqDS_SVJOB4.SVRUSID), svjob4.getFieldValue(EqDS_SVJOB4.SVRTID)));

			// submitted during normal input - then add this to WE
			if (functionData.rtvFieldDbValue(FunctionData.FLD_STAT_MKR_CHKR).equals(WERecordDataModel.MAKER_CHECKER_STAT_AWAIT))
			{
				makerCheckerDuringAPI(eqUser.getEquationUnit().getSystemOption(EqDS_DSSYS3.MCCHK), fhd.getFunctionInfo()
								.getSessionType() == EquationCommonContext.SESSION_EXT_INPUT);
			}
		}

		// Online API (and no message to be returned back to the user)
		if (fhd.getFunctionInfo().getSessionType() == EquationCommonContext.SESSION_API_MODE
						&& (!generateWarningInfo || (generateWarningInfo && functionMessages.getMsgSev() == FunctionMessages.MSG_NONE)))
		{
			// setup session id
			fhd.setFunctionSession(new FunctionSession(fhd.getOptionId(), fhd.getFunctionInfo().getSessionId(), fhd
							.getEquationUser().getUserId()));

			// only if maker-checker is not suppressed
			if (!functionData.rtvFieldData(FunctionData.FLD_SUPP_MKR_CHKR).toString().equals(EqDataType.YES)
							&& willMakerCheckerProceed())
			{
				makerCheckerDuringAPI(eqUser.getEquationUnit().getSystemOption(EqDS_DSSYS3.MCCHK), true);
			}
		}

		return true;
	}

}