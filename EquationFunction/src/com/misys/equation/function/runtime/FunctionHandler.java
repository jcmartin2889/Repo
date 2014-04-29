package com.misys.equation.function.runtime;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bf.com.misys.eqf.types.header.TaskEsfRqHeader;
import bf.com.misys.eqf.types.header.TaskRqHeader;

import com.ibm.as400.access.BidiStringType;
import com.misys.equation.bankfusion.lrp.bean.TaskDetail;
import com.misys.equation.bankfusion.lrp.engine.ITaskEngine;
import com.misys.equation.bankfusion.lrp.engine.TaskEngineToolbox;
import com.misys.equation.bankfusion.tools.LRPToolbox;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationDataStructure;
import com.misys.equation.common.access.EquationDataStructureData;
import com.misys.equation.common.access.EquationLogin;
import com.misys.equation.common.access.EquationRecords;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.ISessionRecordDao;
import com.misys.equation.common.dao.IWARecordDao;
import com.misys.equation.common.dao.IWE2RecordDao;
import com.misys.equation.common.dao.IWERecordDao;
import com.misys.equation.common.dao.beans.OCRecordDataModel;
import com.misys.equation.common.dao.beans.SessionRecordDataModel;
import com.misys.equation.common.dao.beans.WARecordDataModel;
import com.misys.equation.common.dao.beans.WE2RecordDataModel;
import com.misys.equation.common.dao.beans.WERecordDataModel;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.globalprocessing.SystemStatusManager;
import com.misys.equation.common.globalprocessing.audit.AuditType;
import com.misys.equation.common.globalprocessing.audit.GlobalAuditingManager;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.Enhancement;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EqTimingTest;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Option;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.adaptor.FunctionAdaptor;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayButtonLink;
import com.misys.equation.function.beans.DisplayGroup;
import com.misys.equation.function.beans.DisplayItemList;
import com.misys.equation.function.beans.DisplayLabel;
import com.misys.equation.function.beans.Element;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionControlData;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.IDisplayItem;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.InputGroup;
import com.misys.equation.function.beans.Layout;
import com.misys.equation.function.beans.LoadFieldSetStatus;
import com.misys.equation.function.beans.NextAction;
import com.misys.equation.function.beans.TextBean;
import com.misys.equation.function.context.EquationFunctionContext;
import com.misys.equation.function.journal.JournalFile;
import com.misys.equation.function.journal.JournalRecord;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.tools.ExtensionData;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;
import com.misys.equation.function.tools.MakerCheckerUtility;
import com.misys.equation.function.tools.SupervisorToolbox;

/**
 * This class represents a function handler
 */
public class FunctionHandler
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandler.java 17343 2013-09-23 12:04:01Z perkinj1 $";

	// Log
	private static final EquationLogger LOG = new EquationLogger(FunctionHandler.class);

	// Function handler data
	private final FunctionHandlerData fhd;

	// -------------------------------------------------------------- CONSTRUCTOR: BEGIN
	/**
	 * Construct a new empty Function Handler
	 * 
	 */
	public FunctionHandler(EquationUser eqUser, FunctionInfo functionInfo)
	{
		this.fhd = new FunctionHandlerData(this, eqUser, functionInfo);
		setupOnce();
	}

	/**
	 * Provide initialisation when this class is instantiated
	 * 
	 * @return 0
	 */
	private int setupOnce()
	{
		// check if user allowed to save templates
		boolean allowedSaveTemplate = fhd.getEquationUser().checkAuthorisedOption(Option.STP, true);
		fhd.setAllowedSaveTemplate(allowedSaveTemplate);

		return 0;
	}

	// -------------------------------------------------------------- CONSTRUCTOR: END

	// -------------------------------------------------------------- INITIALISATION: BEGIN

	/**
	 * Processing to initialise the function handler for a new function
	 * 
	 * @param optionId
	 *            - option id of the function
	 * 
	 * @return true if there is no error
	 * 
	 * @throws EQException
	 */
	public boolean doNewTransaction(String optionId, String context) throws EQException
	{
		// get the Equation standard session
		EquationStandardSession equationSession = rtvEquationSession();
		try
		{
			EquationUnit eqUnit = fhd.getEquationUser().getEquationUnit();
			EquationUser eqUser = fhd.getEquationUser();
			int sessionType = fhd.getFunctionInfo().getSessionType();

			globalAudit(equationSession, optionId);

			// check if the user is already known to be authorised to the given option
			boolean authorised = eqUser.isAuthorised(optionId);

			// check if user is authorised to the option
			if (!authorised)
			{
				String messageText = "";
				if (EquationCommonContext.isLinkedSession(sessionType))
				{
					// Only call Equation if GB record has not been loaded,
					if (eqUnit.getRecords().getGBRecord(optionId) == null)
					{
						messageText = FunctionRuntimeToolbox.chkOptionExist(equationSession, optionId, eqUser.getUserId());
					}
				}
				else
				{
					messageText = FunctionRuntimeToolbox.chkAuthorised(equationSession, optionId, eqUser.getUserId());
					eqUser.toggleAuthorisedOption(optionId, messageText.trim().equals(""));
				}

				// any message?
				if (!messageText.equals(""))
				{
					fhd.getFunctionMsgManager().insertOtherMessage(equationSession, 0, 0, "", 0, null, messageText, "", "");
					LOG.error(fhd.getFunctionMsgManager().getOtherMessages().toString());
					return false;
				}
			}

			// Check if the option exists in the unit's cache, reload if necessary
			if (eqUnit.getRecords().getGBRecord(optionId) == null)
			{
				eqUnit.getRecords().refreshOptionFiles();
			}

			// determine if option is legacy option
			boolean legacyOption = eqUnit.isLegacyOption(optionId);
			fhd.setLegacyOption(legacyOption);

			// legacy option, cannot be non-desktop
			if (legacyOption && !EquationCommonContext.isDesktopSession(sessionType))
			{
				fhd.getFunctionMsgManager().insertOtherMessage(equationSession, 0, 0, "", 0, null, "KSM7354", "", "");
				return false;
			}

			// legacy option, ensure IBM WebFacing is installed
			if (legacyOption && !fhd.getFunctionInfo().isWebFacingInstalled())
			{
				fhd.getFunctionMsgManager().insertOtherMessage(equationSession, 0, 0, "", 0, null, "KSM7355", "", "");
				return false;
			}

			// legacy option
			if (legacyOption)
			{
				// set the option
				fhd.setOptionId(optionId);

				// update option
				if (sessionType == EquationCommonContext.SESSION_FULL_DESKTOP)
				{
					FunctionRuntimeToolbox.updateOptionInGH(equationSession, optionId, fhd.getEquationUser().getUserId(), fhd
									.getFunctionInfo().getSessionId());
				}

				return true;
			}

			// initialise option
			return initTransaction(optionId, context, FunctionRuntimeToolbox.INIT_DESK, ScreenSetHandler.SCREENSET_DEFAULT);
		}
		finally
		{
			releaseLocks();
		}
	}

	/**
	 * This method will audit the following option if the current one if a global function.
	 * 
	 * @param equationSession
	 *            this is the current session
	 * @param optionId
	 *            this is the option name.
	 * @throws EQException
	 *             if there is any error it will be reported.
	 */
	private void globalAudit(EquationStandardSession equationSession, String optionId) throws EQException
	{
		if (EquationCommonContext.getContext().checkIfGPIsInstalled(equationSession.getSessionIdentifier()))
		{
			GlobalAuditingManager globalAuditingManager = new GlobalAuditingManager(equationSession);
			globalAuditingManager.auditOption(optionId, AuditType.DESKTOPENQUIRY);
		}
	}

	/**
	 * Initialise transaction
	 * 
	 * @param optionId
	 *            - option id
	 * @param contextString
	 *            - string context
	 * @param flag
	 *            - processing type<br>
	 *            FunctionRuntimeToolbox.INIT_DESK - start a new transaction<br>
	 *            FunctionRuntimeToolbox.INIT_SAVE - restore a transaction<br>
	 * @param screenSetType
	 *            - screen set to generate<br>
	 * 
	 * @return true if there is no error
	 * 
	 * @throws EQException
	 */
	private boolean initTransaction(String optionId, String contextString, int flag, int screenSetType) throws EQException
	{
		LOG.info("initTransaction: ");
		fhd.setup(optionId, screenSetType);

		// main transaction processing
		if (flag == FunctionRuntimeToolbox.INIT_DESK || flag == FunctionRuntimeToolbox.INIT_DESK_LINK)
		{
			// setup session
			fhd.setFunctionSession(new FunctionSession(optionId, fhd.getFunctionInfo().getSessionId(), fhd.getEquationUser()
							.getUserId()));

			// set details
			fhd.getScreenSetHandler().setOtherDetails();

			// load context
			ScreenSet screenSet = fhd.getScreenSetHandler().rtvScreenSetMain();

			/*
			 * Rolldown from head to ML 2011.05.19 start fhd.getContextHandler().loadContext(screenSet.getFunction(),
			 * screenSet.getFunctionData(), contextString, fhd.getGbRecord());
			 */

			boolean contextSet = fhd.getContextHandler().loadContext(screenSet.getFunction(), screenSet.getFunctionData(),
							contextString, fhd.getGbRecord());
			// Rolldown from head to ML 2011.05.19 end

			// save context
			fhd.getContextHandler().saveFunctionToContextData(screenSet.getFunction(), screenSet.getFunctionData());

			// screen set field set initialisation
			screenSet.fieldSetInitialisationScriptProcessing(0);

			// first field set initialisation
			screenSet.initialiseInputFieldSet(screenSet.function.getInputFieldSets().get(0));

			// retrieve field set status
			LoadFieldSetStatus status = FunctionRuntimeToolbox.getPrimaryFieldSetStatus(fhd);

			// desktop session
			if (EquationCommonContext.isDesktopSession(fhd.getFunctionInfo().getSessionType()))
			{
				// Check if this function is allowed in Desktop
				if (screenSet.getFunction().isNoDesktopInProduction()
								&& !fhd.getEquationUser().getEquationUnit().isDevelopmentUnit())
				{
					fhd.getFunctionMsgManager().insertOtherMessage(rtvEquationSession(), 0, 0, "", 0, null, "KSM2645", "", "");
					return false;
				}
				// Check if this function is allowed in Desktop
				if (screenSet.getServiceLinkage() != null && screenSet.getServiceLinkage().isNoDesktopInProduction()
								&& !fhd.getEquationUser().getEquationUnit().isDevelopmentUnit())
				{
					fhd.getFunctionMsgManager().insertOtherMessage(rtvEquationSession(), 0, 0, "", 0, null, "KSM2645", "", "");
					return false;
				}
				// load key details if all keys are populated
				if (EquationCommonContext.isChildDesktopSessionKeyProtect(fhd.getFunctionInfo().getSessionType())
								|| FunctionRuntimeToolbox.isKeysPopulated(fhd.getScreenSetHandler().rtvScreenSetMain()))
				{
					// not a child and context string not passed - this will prevent loading of details when initial values has been
					// setup
					/*
					 * Rolldown from head to ML 2011.05.19 start if (flag == FunctionRuntimeToolbox.INIT_DESK &&
					 * contextString.trim().length() == 0 &&
					 * !EquationCommonContext.isChildDesktopSession(fhd.getFunctionInfo().getSessionType())) { }
					 */
					if (flag == FunctionRuntimeToolbox.INIT_DESK && !contextSet
									&& !EquationCommonContext.isChildDesktopSession(fhd.getFunctionInfo().getSessionType()))
					{
					}
					else if (status.isKeyExist())
					{
						int msgSev = applyRetrieveTransaction();
						fhd.setKeysPopulated(msgSev == FunctionMessages.MSG_NONE);
					}
				}
			}

			// no key fields then automatic do retrievals
			if (!status.isKeyExist())
			{
				try
				{
					status.setDetailOpen(false);
					status.setKeyExist(true);
					loadKeyFieldSet(status.getId(), "", true, false);
					fhd.getScreenSetHandler().rtvScreenSetMain().getFunctionData().clearMessages(FunctionData.CLEAR_MSG_ALL);

					// any error?
					if (fhd.getFunctionMsgManager().getFunctionMessages().getMsgSev() >= FunctionMessages.MSG_ERROR)
					{
						fhd.getFunctionMsgManager().getOtherMessages().insertMessages(
										fhd.getFunctionMsgManager().getFunctionMessages());
						return false;
					}
				}
				finally
				{
					status.setKeyExist(false);
					status.setDetailOpen(true);
				}
			}
		}

		// update option
		if (fhd.getFunctionInfo().getSessionType() == EquationCommonContext.SESSION_FULL_DESKTOP)
		{
			FunctionRuntimeToolbox.updateOptionInGH(rtvEquationSession(), optionId, fhd.getEquationUser().getUserId(), fhd
							.getFunctionInfo().getSessionId());
		}

		// return
		LOG.info("initTransaction: ");
		return true;
	}

	// -------------------------------------------------------------- INITIALISATION: END

	// -------------------------------------------------------------- SCREEN HANDLER: BEGIN

	/**
	 * Performs processing prior to displaying the function
	 * 
	 * This function is called before displaying the function. It will perform processing depending on the function key pressed from
	 * the previous display or the result of the validate/update
	 * 
	 * @param optionId
	 *            - option id of the function. <br>
	 *            If this is not null, then it means it is processing a new function <br>
	 *            If this is null, then it means it is processing the same function
	 * @param optionID2
	 *            - if the same function needs to be refreshed (restart from screen 1), then this will contain the same option ID.
	 *            Valid only if optionID is null
	 * 
	 * @return true - processing OK
	 */
	public boolean process(String optionId, String context, String dataarea1, String dataarea2)
	{
		try
		{
			// debug info
			fhd.getFunctionDebugInfo().clear();
			fhd.getFunctionDebugInfo().printStartMethod("process(1)");

			// clear other messages
			fhd.getFunctionMsgManager().clearOtherMessages();

			// determine processing
			if (chkNewFunction(optionId))
			{
				// reset context for new function
				fhd.getContextHandler().reset();
				fhd.getContextHandler().setContext(dataarea1, dataarea2);

				// update the context in the login if exists
				EquationLogin equationLogin = EquationCommonContext.getContext().getEquationLogin(
								fhd.getFunctionInfo().getSessionId());
				if (equationLogin != null)
				{
					equationLogin.setContextData1("");
					equationLogin.setContextData2("");
					if (dataarea1 != null)
					{
						equationLogin.setContextData1(dataarea1);
						equationLogin.setContextData2(dataarea2);
					}
				}

				// process function
				boolean ok = doNewTransaction(optionId, context);
				if (!ok)
				{
					return false;
				}
			}
			checkGPUnitAvailability();

			// setup function keys
			if (!fhd.isLegacyOption())
			{
				generateFkey();
			}

			return true;
		}
		catch (Exception e)
		{
			LOG.error(e);
			FunctionRuntimeToolbox.generateOtherMessages(fhd.getFunctionMsgManager(), fhd.getEquationUser().getSession(), 0, 0,
							"process", e);
			LOG.error(fhd.getFunctionDebugInfo().toString());
			return false;
		}
		finally
		{
			releaseLocks();
			fhd.getFunctionDebugInfo().clear();
		}
	}

	/**
	 * Perform processing to restore a saved session
	 * 
	 * @param optionId
	 *            - option id
	 * @param sessionId
	 *            - session id to restore
	 * @param transactionId
	 *            - transaction id to restore
	 * 
	 * @return true - processing OK
	 * 
	 */
	public boolean processSession(String optionId, String sessionId, String transactionId, String userId)
	{
		try
		{
			// debug info
			fhd.getFunctionDebugInfo().clear();
			fhd.getFunctionDebugInfo().printStartMethod("process(2)");

			// get the Equation standard session
			EquationStandardSession equationSession = rtvEquationSession();

			// restore transaction session
			boolean found = restore(optionId, sessionId, userId, transactionId, null, ScreenSetHandler.SCREENSET_DEFAULT);

			// Session restored
			if (found)
			{
				fhd.getFunctionMsgManager().insertOtherMessage(equationSession, fhd.getScreenSetHandler().getCurScreenSet(), 0, "",
								1, null, "KSM7343", "", "");

				// for template transaction, clear all messages and position to first screen
				if (fhd.getFunctionSession().getStatus().equals(WERecordDataModel.STAT_TMPLT))
				{
					fhd.getFunctionMsgManager().clearAllMessages();
					fhd.getScreenSetHandler().clearFunctionDataMessages();
					fhd.getScreenSetHandler().setCurScreenSet(0);
					fhd.getScreenSetHandler().rtvScrnSetCurrent().setScrnNo(0);
					fhd.getScreenSetHandler().processingRestoreTemplate();
					fhd.setFunctionSession(new FunctionSession(optionId, fhd.getFunctionInfo().getSessionId(), fhd
									.getEquationUser().getUserId(), ""));
				}
				else
				{
					fhd.getScreenSetHandler().positionToScreenSet(fhd.getScreenSetHandler().getCurScreenSet());
				}

				// new function session
				fhd.getFunctionMsgManager().getFunctionMessages().setMsgSev(FunctionMessages.MSG_INFO);
				fhd.getScreenSetHandler().setOtherDetails();
			}

			// Session not restored
			else
			{
				fhd.getFunctionMsgManager().insertOtherMessage(equationSession, fhd.getScreenSetHandler().getCurScreenSet(), 0, "",
								1, null, "KSM7342", "", "");
				return false;
			}

			// setup function keys
			generateFkey();

			// Session found?
			return (found);
		}
		catch (Exception e)
		{
			LOG.error(e);
			FunctionRuntimeToolbox.generateOtherMessages(fhd.getFunctionMsgManager(), fhd.getEquationUser().getSession(), 0, 0,
							"process (2)", e);
			LOG.error(fhd.getFunctionDebugInfo().toString());
			return false;
		}
		finally
		{
			releaseLocks();
			fhd.getFunctionDebugInfo().clear();
		}
	}

	/**
	 * Perform processing for a supervisor
	 * 
	 * @param optionId
	 *            - option id
	 * @param sessionId
	 *            - session id to restore
	 * @param transactionId
	 *            - transaction id to restore
	 * 
	 * @return true - processing OK
	 */
	public boolean processSuper(String optionId, String sessionId, String transactionId, String userId, int screenSetId, int scrnNo)
	{
		EquationStandardSession sessionFromPool = null;
		try
		{
			// debug info
			fhd.getFunctionDebugInfo().clear();
			fhd.getFunctionDebugInfo().printStartMethod("processSuper()");

			// get the Equation standard session
			EquationStandardSession equationSession = rtvEquationSession();

			// clear other messages
			fhd.getFunctionMsgManager().clearOtherMessages();

			// retrieve the session information
			FunctionSession fs = new FunctionSession(optionId, sessionId, userId, transactionId);
			sessionFromPool = FunctionRuntimeToolbox.rtvEquationSessionFromPool(fhd.getEquationUser().getEquationUnit());
			WERecordDataModel weRecord = fs.check(sessionFromPool);

			// verify that the session still exists
			boolean ok = false;

			// determine default status
			String defaultStatus = WERecordDataModel.STAT_CHECK;
			if (weRecord != null && weRecord.getAuthStat().equals(WERecordDataModel.MAKER_CHECKER_STAT_AWAIT))
			{
				defaultStatus = null;
			}

			if (weRecord == null)
			{
				fhd.getFunctionMsgManager().insertOtherMessage(equationSession, fhd.getScreenSetHandler().getCurScreenSet(), 0, "",
								1, null, "KSM7344", "", "");
			}

			// .. and user is still supervisor (but not for LRP)
			else if (!weRecord.getAuthorisor().equals(Toolbox.trim(fhd.getEquationUser().getUserId(), 4)) && !fhd.isLRPTask())
			{
				fhd.getFunctionMsgManager().insertOtherMessage(equationSession, fhd.getScreenSetHandler().getCurScreenSet(), 0, "",
								1, null, "KSM7344", "", "");
			}

			// .. and record is still available for authorisation
			else if (!weRecord.getAuthStat().equals(WERecordDataModel.STAT_AVAIL)
							&& (!weRecord.getAuthStat().equals(WERecordDataModel.STAT_CHECK))
							&& (!weRecord.getAuthStat().equals(WERecordDataModel.MAKER_CHECKER_STAT_AWAIT)))
			{
				fhd.getFunctionMsgManager().insertOtherMessage(equationSession, fhd.getScreenSetHandler().getCurScreenSet(), 0, "",
								1, null, "KSM7344", "", "");
			}

			// try restore the session
			else if (restore(optionId, sessionId, userId, transactionId, defaultStatus, ScreenSetHandler.SCREENSET_SUPERVISOR))
			{
				ScreenSetHandler screenSetHandler = fhd.getScreenSetHandler();
				screenSetId = weRecord.getScreenSetId();
				scrnNo = weRecord.getScrnNo();

				// is the last screen set a RepeatingFied, then move this to index 1 (thus removing any CRM/EFC screens)
				if (screenSetHandler.getScreenSets().get(screenSetId) instanceof ScreenSetRepeatFields)
				{
					screenSetHandler.getScreenSets().add(1, screenSetHandler.getScreenSets().get(screenSetId));
					screenSetId = 1;
					screenSetHandler.setLastScreenSetViewed(screenSetId);
				}

				screenSetHandler.setCurScreenSet(screenSetId); // set to this screen set id
				screenSetHandler.dropFunctionScreenSet(screenSetHandler.getLastScreenSetViewed() + 1);
				ScreenSet screenSet = screenSetHandler.positionToScreenSet(screenSetId);
				screenSet.setId(screenSetId);
				// screenSet.setMaxScrnNo(scrnNo); // set this as the maximum screen
				screenSet.setScrnNo(scrnNo - 1); // set to this screen
				fhd.getSecurityLevel().setCheckerType(SecurityLevel.CHCKR_SUPER);

				// maker-checker?
				if (fhd.getEquationUser().getEquationUnit().hasMakerCheckerEnhancement())
				{
					if (weRecord.getAuthStat().equals(WERecordDataModel.MAKER_CHECKER_STAT_AWAIT))
					{
						screenSetHandler.setCurScreenSet(0);
						screenSet = screenSetHandler.positionToScreenSet(0);
						screenSet.setScrnNo(0);
						fhd.getSecurityLevel().setCheckerType(SecurityLevel.CHCKR_MAKER_CHECKER);
						// does checker completes the transaction?
						if (MakerCheckerUtility.isCheckerToComplete(sessionFromPool, optionId))
						{
							screenSetHandler.setFKeyToNextFunctions(FunctionKeys.KEY_AUTH);
						}
					}
					// at this stage, i.e., status, Maker is to complete
					if (weRecord.getAuthStat().equals(WERecordDataModel.MAKER_CHECKER_STAT_APPRVD))
					{
						fhd.getSecurityLevel().setCheckerType(SecurityLevel.CHCKR_MAKER_MAKER);
						screenSet.getFunctionData().lockedKeyFields(screenSet.getFunction());
					}
				}

				fhd.getScreenSetHandler().processingRestoreBySupervisor();
				generateFkey();
				ok = true;
			}
			else
			{
				fhd.getFunctionMsgManager().insertOtherMessage(equationSession, fhd.getScreenSetHandler().getCurScreenSet(), 0, "",
								1, null, "KSM7344", "", "");
			}

			// success?
			return ok;

		}
		catch (Exception e)
		{
			LOG.error(e);
			FunctionRuntimeToolbox.generateOtherMessages(fhd.getFunctionMsgManager(), fhd.getEquationUser().getSession(), 0, 0,
							"processSuper", e);
			LOG.error(fhd.getFunctionDebugInfo().toString());
			return false;
		}
		finally
		{
			releaseLocks();
			FunctionRuntimeToolbox.returnEquationSessionToPool(fhd.getEquationUser().getEquationUnit(), sessionFromPool);
			fhd.getFunctionDebugInfo().clear();
		}
	}

	/**
	 * Perform processing when a user has restored an authorised/declined transactions
	 * 
	 * @param optionId
	 *            - option id
	 * @param sessionId
	 *            - session id to restore
	 * @param transactionId
	 *            - transaction id to restore
	 * 
	 * @return true - processing OK
	 */
	public boolean processOverride(String optionId, String sessionId, String transactionId, String userId, String status,
					String authLevel, int screenSetId, int scrnNo)
	{
		EquationStandardSession sessionFromPool = null;
		try
		{
			// debug info
			fhd.getFunctionDebugInfo().clear();
			fhd.getFunctionDebugInfo().printStartMethod("processOverride()");

			// get the Equation standard session
			EquationStandardSession equationSession = rtvEquationSession();

			// clear other messages
			fhd.getFunctionMsgManager().clearOtherMessages();

			// assume session not existing anymore
			boolean found = false;

			// retrieve the session information
			FunctionSession fs = new FunctionSession(optionId, sessionId, userId, transactionId);
			sessionFromPool = FunctionRuntimeToolbox.rtvEquationSessionFromPool(fhd.getEquationUser().getEquationUnit());
			WERecordDataModel weRecord = fs.check(sessionFromPool);

			// determine session status
			String stat = "";
			if (weRecord != null)
			{
				stat = weRecord.getAuthStat();
			}

			// verify that the session still exists
			if (weRecord == null)
			{
				fhd.getFunctionMsgManager().insertOtherMessage(equationSession, fhd.getScreenSetHandler().getCurScreenSet(), 0, "",
								1, null, "KSM7344", "", "");
			}

			// if the session has not been reviewed by the supervisor
			else if (stat.equals(WERecordDataModel.STAT_AVAIL))
			{
				found = restore(optionId, sessionId, userId, transactionId, WERecordDataModel.STAT_ABORT,
								ScreenSetHandler.SCREENSET_DEFAULT);
				if (found)
				{
					screenSetId = weRecord.getScreenSetId();
					scrnNo = weRecord.getScrnNo();
					fhd.getScreenSetHandler().setCurScreenSet(screenSetId); // set to this screen set id
					ScreenSet screenSet = fhd.getScreenSetHandler().positionToScreenSet(screenSetId);
					screenSet.setScrnNo(scrnNo - 1); // set to this screen

					// if supervisor has not been notified yet then remove the session
					if (weRecord.getUserAlerted().equals("N"))
					{
						fhd.getFunctionSession().delete(sessionFromPool, true);
					}
					else
					{
						fhd.getFunctionSession().deleteWE2(sessionFromPool, true);
					}
				}
			}

			// if the supervisor has already started reviewing the transaction
			else if (stat.equals(WERecordDataModel.STAT_CHECK))
			{
				fhd.getFunctionMsgManager().insertOtherMessage(equationSession, fhd.getScreenSetHandler().getCurScreenSet(), 0, "",
								1, null, "KSM7345", "", "");
			}

			// if the supervisor has already authorised or declined the transaction (maker / checker)
			else if (fhd.getEquationUser().getEquationUnit().hasMakerCheckerEnhancement()
							&& (stat.equals(WERecordDataModel.MAKER_CHECKER_STAT_APPRVD)
											|| stat.equals(WERecordDataModel.MAKER_CHECKER_STAT_REJCTD) || stat
											.equals(WERecordDataModel.MAKER_CHECKER_STAT_AWAIT)))
			{
				found = restore(optionId, sessionId, userId, transactionId, stat, ScreenSetHandler.SCREENSET_DEFAULT);
				if (found)
				{
					ScreenSet screenSet = fhd.getScreenSetHandler().setCurScreenSet(0);
					screenSet.getFunctionData().lockedKeyFields(screenSet.getFunction());
					screenSet.setScrnNo(0);
					fhd.getScreenSetHandler().positionToScreenSet(0);
					fhd.getFunctionMsgManager().overrideAllWarning(fhd.getFunctionSession().getAuthorisor());
					fhd.getSecurityLevel().setCheckerType(SecurityLevel.CHCKR_MAKER_MAKER);

					if (stat.equals(WERecordDataModel.MAKER_CHECKER_STAT_APPRVD))
					{
						fhd.getScreenSetHandler().setFKeyToNextFunctions(FunctionKeys.KEY_AUTH);
					}
					else
					{
						fhd.getFunctionSession().getFunctionMessages().clearMessages();
						fhd.getFunctionMsgManager().clearAllMessages();
						fhd.getScreenSetHandler().clearMessages();
					}
				}
			}

			// if the supervisor has already authorised or declined the transaction
			else if (stat.equals(WERecordDataModel.STAT_AUTH) || stat.equals(WERecordDataModel.STAT_DECL))
			{
				found = restore(optionId, sessionId, userId, transactionId, null, ScreenSetHandler.SCREENSET_DEFAULT);
				if (found)
				{
					screenSetId = weRecord.getScreenSetId();
					scrnNo = weRecord.getScrnNo();
					ScreenSet screenSet = fhd.getScreenSetHandler().setCurScreenSet(screenSetId); // set to this screen set id
					fhd.getScreenSetHandler().positionToScreenSet(screenSetId);
					screenSet.setScrnNo(scrnNo - 1); // set to this screen

					// authorised all warnings?
					if (stat.equals(WERecordDataModel.STAT_AUTH))
					{
						if (fhd.getFunctionSession().getAuthLevel().equals(WERecordDataModel.LVL_ALL))
						{
							fhd.getFunctionMsgManager().overrideAllWarning(fhd.getFunctionSession().getAuthorisor());
						}
						else
						{
							fhd.getFunctionMsgManager().overrideFirstWarning(fhd.getFunctionSession().getAuthorisor());
						}
					}
				}
			}

			else
			{
				fhd.getFunctionMsgManager().insertOtherMessage(equationSession, fhd.getScreenSetHandler().getCurScreenSet(), 0, "",
								1, null, "KSM7346", "", "");
			}

			// session restored?
			if (fhd.getFunctionMsgManager().getOtherMessages().getMsgSev() == FunctionMessages.MSG_NONE)
			{
				if (found)
				{
					fhd.getFunctionMsgManager().clearFunctionMessages();
					processReturnedMessages(fhd.getFunctionSession().getFunctionMessages());
					fhd.getFunctionKeys().setFuncKey(FunctionKeys.KEY_VERI);
					generateFkey();
				}
				else
				{
					fhd.getFunctionMsgManager().insertOtherMessage(equationSession, fhd.getScreenSetHandler().getCurScreenSet(), 0,
									"", 1, null, "KSM7344", "", "");
				}
			}

			// session found?
			return found;

		}
		catch (Exception e)
		{
			LOG.error(e);
			FunctionRuntimeToolbox.generateOtherMessages(fhd.getFunctionMsgManager(), fhd.getEquationUser().getSession(), 0, 0,
							"processOverride", e);
			LOG.error(fhd.getFunctionDebugInfo().toString());
			return false;
		}
		finally
		{
			releaseLocks();
			FunctionRuntimeToolbox.returnEquationSessionToPool(fhd.getEquationUser().getEquationUnit(), sessionFromPool);
			fhd.getFunctionDebugInfo().clear();
		}
	}

	/**
	 * Perform processing when a user has restored an authorised/declined transactions
	 * 
	 * @param optionId
	 *            - option id
	 * @param sessionId
	 *            - session id to restore
	 * @param transactionId
	 *            - transaction id to restore
	 * 
	 * @return true - processing OK
	 */
	public boolean processLRPTask(String optionId, String taskId, String taskType)
	{
		// retrieve the task engine
		String sessionIdentifer = fhd.getFunctionInfo().getSessionId();
		ITaskEngine taskEngine = EquationFunctionContext.getContext().getTaskEngine(sessionIdentifer);
		EquationFunctionContext equationFunctionContext = EquationFunctionContext.getContext();

		// conversion rules
		ConversionRules conversionRules = FunctionRuntimeToolbox.getConversionRules(null, fhd);

		// has this processing claimed it?
		boolean claimed = false;

		try
		{
			// debug info
			fhd.getFunctionDebugInfo().clear();
			fhd.getFunctionDebugInfo().printStartMethod("processLRPTask()");

			// get the Equation standard session
			EquationStandardSession equationSession = rtvEquationSession();

			// clear all messages
			fhd.getFunctionMsgManager().clearAllMessages();

			// User is authorise to it?
			if (!fhd.getEquationUser().checkAuthorisedOption(optionId, false))
			{
				fhd.getFunctionMsgManager().generateMessage(rtvEquationSession(), fhd.getFunctionMsgManager().getOtherMessages(),
								0, 0, "", 0, null, "KSM2044" + Toolbox.pad(optionId, 10) + fhd.getEquationUser().getUserId(), "",
								"", FunctionMessages.MSG_NONE);
				return false;
			}

			// retrieve the task details
			TaskDetail taskDetail = taskEngine.getTaskData(taskId);

			// Task is valid?
			if (!validateTask(taskDetail, true))
			{
				return false;
			}

			// Mark as being viewed
			equationFunctionContext.addOpenTask(taskId, sessionIdentifer);

			// Try to complete claim
			if (!taskDetail.isClaimed())
			{
				equationFunctionContext.claimTask(fhd.getFunctionInfo().getSessionId(), taskEngine, taskId);
				taskDetail = taskEngine.getTaskData(taskId);
				claimed = true;
			}

			// Determine task type
			boolean esfTask = taskType.equals(TaskEngineToolbox.TASK_TYPE_ESF);
			boolean authTask = taskType.equals(TaskEngineToolbox.TASK_TYPE_AUTH);

			// for ESF, then revert back to original ESF processing
			if (esfTask)
			{
				TaskEsfRqHeader payload = taskEngine.getPayloadForESFTask(taskId);
				String option = payload.getEsfKey().getOptionId();
				String sessionId = payload.getEsfKey().getSessionId();
				String transactionId = payload.getEsfKey().getTransactionId();
				String userId = payload.getEsfKey().getUserId();

				WERecordDataModel weRecord = new WERecordDataModel(option, sessionId, transactionId, userId);
				DaoFactory daoFactory = new DaoFactory();
				IWERecordDao weDao = daoFactory.getWEDao(fhd.getEquationUser().getSession(), weRecord);
				weRecord = weDao.getWERecord();

				fhd.setTaskDetail(taskDetail);
				boolean ok = processSuper(optionId, sessionId, transactionId, userId, weRecord.getScreenSetId(), weRecord
								.getScrnNo());
				fhd.setTaskDetail(taskDetail);
				if (!ok)
				{
					unClaimTask();
				}
				return ok;
			}

			// retrieve the task payload
			TaskRqHeader payload = taskEngine.getPayloadForTask(taskId);

			// initialise the transaction
			initTransaction(optionId, "", FunctionRuntimeToolbox.INIT_SAVE, ScreenSetHandler.SCREENSET_DEFAULT);

			// Determine the action that can be taken against the task
			SecurityLevel securityLevel = fhd.getSecurityLevel();
			if (authTask)
			{
				String taskActionList = payload.getTaskActionList().trim();
				securityLevel.setAuthorizeAllowed(LRPToolbox.isTaskActionAuth(taskActionList));
				securityLevel.setReferAllowed(LRPToolbox.isTaskActionRefer(taskActionList));
				securityLevel.setDeclineAllowed(LRPToolbox.isTaskActionDecline(taskActionList));
				securityLevel.setUpdateAllowed(LRPToolbox.isTaskActionUpdate(taskActionList));
			}

			// work field
			ScreenSetHandler screenSetHandler = fhd.getScreenSetHandler();
			ScreenSet screenSetMain = screenSetHandler.rtvScreenSetMain();

			// setup other details
			fhd.setFunctionSession(new FunctionSession(optionId, fhd.getFunctionInfo().getSessionId(), fhd.getEquationUser()
							.getUserId()));
			screenSetHandler.setOtherDetails();

			// transfer payload into the function data
			FunctionBankFusion functionBankFusion = new FunctionBankFusion();
			Function function = screenSetMain.getFunction();
			FunctionData functionData = screenSetMain.getFunctionData();

			Object bf_functionData = null;
			if (payload.getServiceData() != null)
			{
				bf_functionData = payload.getServiceData();
				functionBankFusion.loadToFunctionData(function, functionData, bf_functionData, false, conversionRules);
			}

			// Setup CRM
			Object crm_bf_functionData = null;
			if (payload.getCrmData() != null && screenSetHandler.isScreenSetCRMExist())
			{
				ScreenSet screenSetCRM = screenSetHandler.rtvScreenSet(ScreenSetHandler.FUNCTION_CRM_SCREEN);

				FunctionBankFusionSrv functionBankFusionSrv = new FunctionBankFusionSrv();
				crm_bf_functionData = payload.getCrmData();
				functionBankFusionSrv.loadToCRMFunctionData(equationSession, screenSetCRM, crm_bf_functionData, false);
			}

			// Setup EFC
			Object efc_bf_functionData = null;
			if (payload.getEfcData() != null && screenSetHandler.isScreenSetEFCExist())
			{
				ScreenSet screenSetEFC = screenSetHandler.rtvScreenSet(ScreenSetHandler.FUNCTION_EFC_SCREEN_1);

				FunctionBankFusionSrv functionBankFusionSrv = new FunctionBankFusionSrv();
				efc_bf_functionData = payload.getEfcData();
				functionBankFusionSrv.loadToEFCFunctionData(equationSession, screenSetEFC, efc_bf_functionData, optionId, false);

			}

			// authorisation task?
			if (authTask)
			{
				screenSetMain.setFKeyToNextFunction(FunctionKeys.KEY_AUTHA);
				if (screenSetHandler.isScreenSetCRMExist())
				{
					screenSetHandler.getScreenSets().get(ScreenSetHandler.FUNCTION_CRM_SCREEN).setFKeyToNextFunction(
									FunctionKeys.KEY_AUTHA);
				}
			}

			// set the expected mode
			if (payload.getFunctionMode().trim().length() > 0)
			{
				fhd.setServiceMode(payload.getFunctionMode().trim());
			}

			// key screen exists, try to load the key screen
			LoadFieldSetStatus fieldSetStatus = FunctionRuntimeToolbox.getPrimaryFieldSetStatus(fhd);
			if (fieldSetStatus.isKeyExist() && FunctionRuntimeToolbox.isKeysPopulated(screenSetMain))
			{
				int msgSev = applyRetrieveTransaction();

				// reload details
				if (msgSev != FunctionMessages.MSG_ERROR)
				{
					fhd.setKeysPopulated(true);
					if (bf_functionData != null)
					{
						functionBankFusion.loadToFunctionData(function, functionData, bf_functionData, false, conversionRules);
					}
				}
			}

			// successful
			fhd.setTaskDetail(taskDetail);
			fhd.setTaskRqHeader(payload);

			// Generate function key
			generateFkey();

			return true;
		}
		catch (Exception e)
		{
			LOG.error(e);
			equationFunctionContext.clearTaskActiveList(fhd);
			FunctionRuntimeToolbox.generateOtherMessages(fhd.getFunctionMsgManager(), fhd.getEquationUser().getSession(), 0, 0,
							"processLRPTask", e);
			LOG.error(fhd.getFunctionDebugInfo().toString());

			// unclaim it, just log any error coming out of the unclaim
			try
			{
				if (claimed)
				{
					equationFunctionContext.unclaimTask(fhd.getFunctionInfo().getSessionId(), taskEngine, taskId);
				}
			}
			catch (Exception e2)
			{
				LOG.error("Unable to unclaim task " + taskId);
			}

			return false;
		}
		finally
		{
			fhd.getFunctionDebugInfo().clear();
		}
	}

	/**
	 * Processing to process an existing function
	 * 
	 * @param ckey
	 *            - function key pressed
	 * @param reason
	 *            - supervisor message (valid only during supervisor mode F10)
	 * @param loadFieldSet
	 *            field set to load (valid only during Load)
	 * @param loadField
	 *            field to load (valid only during Load)
	 * 
	 * @return next processing
	 */
	public int actionFkey(int ckey, String reason, String loadFieldSet, String loadField)
	{
		int nextRequest = FunctionRuntimeToolbox.PROC_REDISPLAY_SCREEN;
		ScreenSet screenSetMain = fhd.getScreenSetHandler().rtvScreenSetMain();
		ScreenSet screenSet = fhd.getScreenSetHandler().rtvScrnSetCurrent();

		try
		{
			// debug info
			fhd.getFunctionDebugInfo().clear();
			fhd.getFunctionDebugInfo().printStartMethod("actionFkey()");

			// Let the specific screen handle the processing (but never during supervisor mode)
			if (fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_NONE)
			{
				nextRequest = screenSet.actionFkey(ckey);
				if (nextRequest != FunctionRuntimeToolbox.PROC_LET_SYSTEM_DECIDE)
				{
					return nextRequest;
				}
			}

			// Default is to redisplay the screen
			nextRequest = FunctionRuntimeToolbox.PROC_REDISPLAY_SCREEN;

			// Supervisor?
			boolean supervisor = fhd.getSecurityLevel().getCheckerType() != SecurityLevel.CHCKR_NONE;

			// ------------------------------------------------------------------- LRP mode
			// LRP Authorisation - decline or refer
			if (fhd.isLRPAuthTask() && (ckey == FunctionKeys.KEY_REFER || ckey == FunctionKeys.KEY_DECL))
			{
				int scrnSet = fhd.getScreenSetHandler().getCurScreenSet();
				int msgSev = update1(ckey != FunctionKeys.KEY_DEL);
				fhd.getScreenSetHandler().setCurScreenSet(scrnSet);
				if (msgSev < FunctionMessages.MSG_ERROR)
				{
					EquationFunctionContext.getContext().clearTaskActiveList(fhd);
					nextRequest = FunctionRuntimeToolbox.PROC_EXIT_FUNCTION;
				}
			}

			// ------------------------------------------------------------------- SUPERVISOR MODE OR CHECKER MODE
			// F3=Exit / F6=Authorise warnings / F18=Authorise all / F10=Decline (ESF supervisor)
			else if (fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_SUPER
							&& (ckey == FunctionKeys.KEY_AUTH || ckey == FunctionKeys.KEY_AUTHA || ckey == FunctionKeys.KEY_DECL
											|| ckey == FunctionKeys.KEY_EXIT || ckey == FunctionKeys.KEY_PREV))
			{
				if (reason == null)
				{
					reason = "";
				}

				int msgSev = SupervisorToolbox.updateSuper(ckey, reason, fhd);
				if (msgSev < FunctionMessages.MSG_ERROR)
				{
					nextRequest = FunctionRuntimeToolbox.PROC_EXIT_FUNCTION;
				}
			}

			// F3=Exit / F6=Authorise warnings / F18=Authorise all / F10=Decline (checker mode)
			else if (fhd.getEquationUser().getEquationUnit().hasMakerCheckerEnhancement()
							&& fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_MAKER_CHECKER
							&& ((ckey == FunctionKeys.KEY_AUTH && screenSet.getFKeyToNextScreenSet() != FunctionKeys.KEY_AUTH) || ckey == FunctionKeys.KEY_DECL))
			{
				if (reason == null)
				{
					reason = "";
				}
				int msgSev = MakerCheckerUtility.updateMakerCheckerStatus(ckey, reason, fhd, fhd.getFunctionSession()
								.getAuthorisor(), false);
				if (msgSev < FunctionMessages.MSG_ERROR)
				{
					nextRequest = FunctionRuntimeToolbox.PROC_EXIT_FUNCTION;
				}
				updateAlertStatus();
			}

			// Checker exiting the transaction (Cancel button press)
			else if (fhd.getEquationUser().getEquationUnit().hasMakerCheckerEnhancement()
							&& fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_MAKER_CHECKER
							&& (ckey == FunctionKeys.KEY_EXIT || ckey == FunctionKeys.KEY_PREV))
			{
				fhd.getFunctionSession().reset(fhd.getEquationUser().getSession(), true,
								WERecordDataModel.MAKER_CHECKER_STAT_AWAIT, "N");
				nextRequest = FunctionRuntimeToolbox.PROC_EXIT_FUNCTION;
			}
			// Maker resubmitting the transaction
			else if (fhd.getEquationUser().getEquationUnit().hasMakerCheckerEnhancement()
							&& fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_MAKER_MAKER
							&& ckey == FunctionKeys.KEY_AUTH && screenSet.getFKeyToNextScreenSet() != FunctionKeys.KEY_AUTH)
			{
				fhd.getFunctionSession().setResubmitted(true);
				fhd.getSecurityLevel().setCheckerType(SecurityLevel.CHCKR_NONE);
				nextRequest = FunctionRuntimeToolbox.PROC_REDISPLAY_SCREEN;
			}
			// Maker declining the transaction
			else if (fhd.getEquationUser().getEquationUnit().hasMakerCheckerEnhancement()
							&& fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_MAKER_MAKER
							&& ckey == FunctionKeys.KEY_DECL)
			{
				if (reason == null)
				{
					reason = "";
				}
				int msgSev = MakerCheckerUtility.updateMakerCheckerStatus(FunctionKeys.KEY_DEL, reason, fhd, fhd
								.getFunctionSession().getAuthorisor(), false);
				if (msgSev < FunctionMessages.MSG_ERROR)
				{
					fhd.getFunctionSession().delete(rtvEquationSession(), true);
					nextRequest = FunctionRuntimeToolbox.PROC_EXIT_FUNCTION;
				}
			}

			// Next screen during supervisor override
			else if (ckey == FunctionKeys.KEY_PGDN && supervisor)
			{
				boolean disp = screenSet.nextScreen();
				if (!disp)
				{
					fhd.getScreenSetHandler().nextScreenSet();
				}
				nextRequest = FunctionRuntimeToolbox.PROC_REDISPLAY_SCREEN;

				if (fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_MAKER_MAKER
								&& fhd.getFunctionSession().getStatus() != null
								&& fhd.getFunctionSession().getStatus().equals(WERecordDataModel.MAKER_CHECKER_STAT_AWAIT))
				{
					fhd.getFunctionMsgManager().clearMessages();
				}
			}

			// Previous screen during supervisor override
			else if (ckey == FunctionKeys.KEY_PGUP && supervisor)
			{
				boolean disp = screenSet.prevScreen();
				if (!disp)
				{
					fhd.getScreenSetHandler().prevScreenSet();
				}
				nextRequest = FunctionRuntimeToolbox.PROC_REDISPLAY_SCREEN;
			}

			// Exit
			else if (ckey == FunctionKeys.KEY_EXIT2)
			{
				nextRequest = FunctionRuntimeToolbox.PROC_EXIT_FUNCTION;
			}

			// Drill down?
			else if (ckey == FunctionKeys.KEY_DRILLDOWN)
			{
				fhd.getScreenSetHandler().rtvScrnSetCurrent().processSelectionOption(loadField);
			}

			// Drill down on specific row - repeating group + selection option + row index
			else if (ckey == FunctionKeys.KEY_DRILLDOWN2)
			{
				String[] selection = loadField.split(EqDataType.GLOBAL_DELIMETER);
				fhd.getScreenSetHandler().rtvScrnSetCurrent().processSingleSelectionOption(selection[0], selection[1],
								Toolbox.parseInt(selection[2], 0));
			}

			// Drill down on global link function - repeating group + selection option
			else if (ckey == FunctionKeys.KEY_DRILLDOWN3)
			{
				String[] selection = loadField.split(EqDataType.GLOBAL_DELIMETER);
				fhd.getScreenSetHandler().rtvScrnSetCurrent().processSingleSelectionOption(selection[0], selection[1]);
			}

			// Other key during supervisor - redisplay
			else if (fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_SUPER)
			{
				nextRequest = FunctionRuntimeToolbox.PROC_REDISPLAY_SCREEN;
			}

			// Other key during maker-checker supervisor - redisplay
			else if (fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_MAKER_CHECKER && ckey != FunctionKeys.KEY_AUTH
							&& ckey != FunctionKeys.KEY_DEL)
			{
				nextRequest = FunctionRuntimeToolbox.PROC_REDISPLAY_SCREEN;
			}

			// ------------------------------------------------------------------- NORMAL MODE

			// Next screen only if no messages
			else if (ckey == FunctionKeys.KEY_PGDN && fhd.getFunctionMsgManager().getFunctionMessages().chkNoMessage())
			{
				int next = fhd.getScreenSetHandler().getNextExpectedScreen();
				if (next < 0)
				{
					screenSet.nextScreen();

					// initialise next screen
					screenSet.initialiseInputFieldSet(screenSet.function.getInputFieldSets().get(screenSet.getScrnNo()));
				}
				else
				{
					screenSet.setScrnNo(next);
				}

				// enquiry, then do validation to display messages in advance
				if (fhd.getSecurityLevel().isEnquireMode())
				{
					validate(screenSet.getScrnNo());
				}

				nextRequest = FunctionRuntimeToolbox.PROC_REDISPLAY_SCREEN;
			}

			// Previous screen, allow only if errors or no message, for warnings, it has to be overridden
			// and for info, it has to be displayed
			else if (ckey == FunctionKeys.KEY_PGUP)
			{
				int prev = fhd.getScreenSetHandler().getNextExpectedScreen();
				if (prev < 0)
				{
					screenSet.prevScreen();

					// initialise previous screen
					screenSet.initialiseInputFieldSet(screenSet.function.getInputFieldSets().get(screenSet.getScrnNo()));
				}
				else
				{
					screenSet.setScrnNo(prev);
				}

				// enquiry, then do validation to display messages in advance
				if (fhd.getSecurityLevel().isEnquireMode())
				{
					validate(screenSet.getScrnNo());
				}

				nextRequest = FunctionRuntimeToolbox.PROC_REDISPLAY_SCREEN;
			}

			// Function key for exit due to request for offline supervisor
			else if (ckey == FunctionKeys.KEY_EXIT_OFLNE_OVR)
			{
				// delete(fhd.getFunctionSession().getOptionId(), fhd.getFunctionSession().getSessionId(), fhd.getFunctionSession()
				// .getUserId(), fhd.getFunctionSession().getTransactionId());
				nextRequest = FunctionRuntimeToolbox.PROC_EXIT_FUNCTION;
			}

			// Function key for exit
			else if (ckey == FunctionKeys.KEY_EXIT || ckey == FunctionKeys.KEY_EXIT2)
			{
				nextRequest = FunctionRuntimeToolbox.PROC_EXIT_FUNCTION;
				if (onCancelUserExit(ckey) == FunctionMessages.MSG_NONE)
				{
					// process each parent
					FunctionHandler fh = fhd.getParentFunctionHandler();
					while (fh != null)
					{
						FunctionHandlerData fhd = fh.getFhd();
						fhd.removeDrillDownChild();
						fhd.getFunctionMsgManager().clearMessages();
						// execute, onCancel()
						if (fh.onCancelUserExit(ckey) == FunctionMessages.MSG_NONE)
						{
							fh = fhd.getParentFunctionHandler();
						}
						// messages from onCancel(), then display this
						else
						{
							nextRequest = FunctionRuntimeToolbox.PROC_REDISPLAY_SCREEN;
							processReturnedMessages(fhd.getFunctionMsgManager().getFunctionMessages());
							fh = null;
						}
					}
				}
				else
				{
					nextRequest = FunctionRuntimeToolbox.PROC_REDISPLAY_SCREEN;
				}
			}

			// Function key for back to screen 1 (or previous function)
			else if (ckey == FunctionKeys.KEY_PREV)
			{
				// first screen set, then either reload the key or reset or exit
				if (fhd.getScreenSetHandler().chkFirstScreenSet())
				{
					// execute the on cancel user exit
					if (onCancelUserExit(ckey) != FunctionMessages.MSG_NONE)
					{
					}
					// drill down function, then go back to the parent
					else if (fhd.getParentFunctionHandler() != null)
					{
						removeDrillDownChildProcessing();
					}
					// if keys has been populated, then exit
					else if (fhd.isKeysPopulated())
					{
						nextRequest = FunctionRuntimeToolbox.PROC_EXIT_FUNCTION;
					}
					// if not on the first screen, then reinitialise and display first screen
					else if (fhd.getScreenSetHandler().rtvScrnSetCurrent().getScrnNo() > 0)
					{
						unClaimTask();
						initTransaction(fhd.getOptionId(), "", FunctionRuntimeToolbox.INIT_DESK, ScreenSetHandler.SCREENSET_DEFAULT);
						nextRequest = FunctionRuntimeToolbox.PROC_SCREEN1;
					}
					// exit
					else
					{
						nextRequest = FunctionRuntimeToolbox.PROC_EXIT_FUNCTION;
					}
				}
				// not on first screen set, then just go to the previous screen set
				else
				{
					fhd.getFunctionMsgManager().clearMessages();
					fhd.getScreenSetHandler().prevScreenSet();
					processReturnedMessages(fhd.getScreenSetHandler().rtvScrnSetCurrent().getFunctionMessages());
				}
			}

			// Function key for linked option 1/2/3/4
			else if (ckey == FunctionKeys.KEY_F2 || ckey == FunctionKeys.KEY_F11 || ckey == FunctionKeys.KEY_F14
							|| ckey == FunctionKeys.KEY_F23)
			{
				String linkOption = "";

				// execute the on cancel user exit
				if (onCancelUserExit(ckey) != FunctionMessages.MSG_NONE)
				{
				}
				// Function key for linked option 1
				else if (ckey == FunctionKeys.KEY_F2)
				{
					linkOption = fhd.getGbRecord().getUserFuncKey1();
				}
				// Function key for linked option 2
				else if (ckey == FunctionKeys.KEY_F11)
				{
					linkOption = fhd.getGbRecord().getUserFuncKey2();
				}
				// Function key for linked option 3
				else if (ckey == FunctionKeys.KEY_F14)
				{
					linkOption = fhd.getGbRecord().getUserFuncKey3();
				}
				// Function key for linked option 4
				else if (ckey == FunctionKeys.KEY_F23)
				{
					linkOption = fhd.getGbRecord().getUserFuncKey4();
				}

				// legacy function
				if (linkOption.length() > 0)
				{
					EquationLogin equationLogin = EquationCommonContext.getContext().getEquationLogin(
									fhd.getFunctionInfo().getSessionId());
					if (equationLogin != null && equationLogin.getUserInterfaceType() == EquationCommonContext.UI_UXP)

					{
						NextAction nextAction = getNextAction(fhd.getEquationUser().getEquationUnit(), linkOption, "");
						fhd.setNextAction(nextAction);
					}
					else
					{

						if (fhd.getEquationUser().getEquationUnit().isLegacyOption(linkOption))
						{
							fhd.setLegacyOption(true);
							fhd.setOptionId(linkOption);
						}
						else
						{
							unClaimTask();
							initTransaction(linkOption, "", FunctionRuntimeToolbox.INIT_DESK_LINK,
											ScreenSetHandler.SCREENSET_DEFAULT);
						}
					}
				}
			}

			// Save, exit function
			else if (ckey == FunctionKeys.KEY_SAVE)
			{
				nextRequest = FunctionRuntimeToolbox.PROC_EXIT_FUNCTION;
			}

			// Save as template, exit function
			else if (ckey == FunctionKeys.KEY_SVTMPL)
			{
				nextRequest = FunctionRuntimeToolbox.PROC_EXIT_FUNCTION;
			}

			// Refresh
			else if (ckey == FunctionKeys.KEY_RFRSH && screenSetMain.chkEnquiry())
			{
				refreshEnquiry();
			}

			// Load
			else if (ckey == FunctionKeys.KEY_LOAD)
			{
				loadKeyFieldSet(loadFieldSet, loadField, true, true);
			}

			// Unload
			else if (ckey == FunctionKeys.KEY_UNLOAD)
			{
				if (onCancelUserExit(ckey) == FunctionMessages.MSG_NONE)
				{
					if (loadFieldSet.trim().length() == 0)
					{
						loadFieldSet = fhd.getScreenSetHandler().rtvScrnSetCurrent().getCurrentFieldSet();
					}
					unloadKeyFieldSet(loadFieldSet, loadField);
					if (fhd.isKeysPopulated())
					{
						// drill down function, then go back to the parent
						if (fhd.getParentFunctionHandler() != null)
						{
							removeDrillDownChildProcessing();
						}
						else
						{
							nextRequest = FunctionRuntimeToolbox.PROC_EXIT_FUNCTION;
						}
					}
				}
			}

			// Drill down?
			else if (ckey == FunctionKeys.KEY_DRILLDOWN)
			{
				fhd.getScreenSetHandler().rtvScrnSetCurrent().processSelectionOption(loadField);
			}

			// Drill up?
			else if (ckey == FunctionKeys.KEY_DRILLUP)
			{
				if (onCancelUserExit(ckey) == FunctionMessages.MSG_NONE)
				{
					// drill down function, then go back to the (top most) parent
					FunctionHandler fh = fhd.getParentFunctionHandler();
					FunctionHandlerData fhd = fh.getFhd();
					while (fh != null)
					{
						fhd.removeDrillDownChild();
						fhd.getFunctionMsgManager().clearMessages();
						// no more parent, then this is the top most parent
						if (fhd.getParentFunctionHandler() == null)
						{
							fh = null;
						}
						// execute, onCancel()
						else if (fh.onCancelUserExit(ckey) == FunctionMessages.MSG_NONE)
						{
							fh = fhd.getParentFunctionHandler();
							fhd = fh.getFhd();
						}
						// messages from onCancel(), then display this
						else
						{
							processReturnedMessages(fhd.getFunctionMsgManager().getFunctionMessages());
							fh = null;
						}
					}
				}
			}

			// Any error message, then exit
			else if (fhd.getSecurityLevel().isUpdateMessage())
			{
				nextRequest = FunctionRuntimeToolbox.PROC_EXIT_FUNCTION;
			}

			// Charges
			else if (ckey == FunctionKeys.KEY_CHARGE)
			{
				// keep track of the current screen set
				boolean nextFunction = fhd.getScreenSetHandler().nextScreenSet();
				if (nextFunction)
				{
					processReturnedMessages(fhd.getScreenSetHandler().rtvScrnSetCurrent().getFunctionMessages());
				}
			}

			// Enter key has been pressed during transaction mode and not yet on the last screen
			else if (ckey == FunctionKeys.KEY_ENT && !screenSet.chkLastScreen())
			{
				// processed selection option if exists
				if (fhd.getScreenSetHandler().rtvScrnSetCurrent().processSelectionOption())
				{
					return nextRequest;
				}
			}

			// Enter key (or appropriate function key) has been pressed during transaction mode on the last screen
			else if (((ckey == screenSet.getFKeyToNextScreenSet() || ckey == FunctionKeys.KEY_DEL) && screenSet.chkLastScreen())
			// For maker-checker screens, allow completion of txn on either page (if multiple screens)
							|| (ckey == screenSet.getFKeyToNextScreenSet() && fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_MAKER_CHECKER))
			{
				// processed selection option if exists (only if enter key has been pressed)
				if (ckey == FunctionKeys.KEY_ENT && fhd.getScreenSetHandler().rtvScrnSetCurrent().processSelectionOption())
				{
					return nextRequest;
				}

				// is this the first screen set, then perform validation of entire screen again
				int msgSev = FunctionMessages.MSG_NONE;
				if (screenSet.getId() == 0)
				{
					msgSev = lastValidate();
				}

				// any outstanding messages?
				boolean nextScreenSet = true;
				if (msgSev == FunctionMessages.MSG_NONE || screenSet.chkEnquiry())
				{
					nextScreenSet = fhd.getScreenSetHandler().nextScreenSet();
				}

				// this is the last function, then performs update
				if (!nextScreenSet)
				{
					// performs update
					update(ckey != FunctionKeys.KEY_DEL);

					// no messages to be displayed
					if (rtvMsgSev() == FunctionMessages.MSG_NONE)
					{
						// drill down function, then go back to the parent
						if (fhd.getParentFunctionHandler() != null)
						{
							removeDrillDownChildProcessing();
						}
						// child session
						else if (EquationCommonContext.isChildDesktopSession(fhd.getFunctionInfo().getSessionType()))
						{
							// remove the task since already completed
							EquationFunctionContext.getContext().clearTaskActiveList(fhd);

							nextRequest = FunctionRuntimeToolbox.PROC_EXIT_FUNCTION;
						}
						// direct transaction
						else if (fhd.getFunctionInfo().getSessionType() == EquationCommonContext.SESSION_DIRECT_TRANS_DESKTOP)
						{
							// remove the task since already completed
							EquationFunctionContext.getContext().clearTaskActiveList(fhd);

							nextRequest = FunctionRuntimeToolbox.PROC_EXIT_DESKTOP;
						}
						// all other mode
						else
						{
							// remove the task since already completed
							EquationFunctionContext.getContext().clearTaskActiveList(fhd);

							// determine next request
							String optionNextRequest = "";
							boolean authorised = false;

							// from the function data
							optionNextRequest = fhd.getScreenSetHandler().rtvScreenSetMain().getFunctionData().rtvFieldInputValue(
											FunctionData.FLD_NEXT_REQUEST).trim();
							if (optionNextRequest.length() > 0)
							{
								authorised = fhd.getEquationUser().checkAuthorisedOption(optionNextRequest,
												fhd.getFunctionInfo().isWebFacingInstalled());
							}

							// from GB record
							if (!authorised)
							{
								optionNextRequest = fhd.getGbRecord().getMandatoryNextReq().trim();
								if (optionNextRequest.length() > 0)
								{
									authorised = fhd.getEquationUser().checkAuthorisedOption(optionNextRequest,
													fhd.getFunctionInfo().isWebFacingInstalled());
								}
							}

							// if authorised
							if (authorised)
							{
								EquationLogin equationLogin = EquationCommonContext.getContext().getEquationLogin(
												fhd.getFunctionInfo().getSessionId());
								if (equationLogin != null && equationLogin.getUserInterfaceType() == EquationCommonContext.UI_UXP
												&& fhd.getEquationUser().getEquationUnit().isLegacyOption(optionNextRequest))

								{
									NextAction nextAction = getNextAction(fhd.getEquationUser().getEquationUnit(),
													optionNextRequest, "");
									fhd.setNextAction(nextAction);
									// remove the task since already completed
									EquationFunctionContext.getContext().clearTaskActiveList(fhd);
									nextRequest = FunctionRuntimeToolbox.PROC_EXIT_FUNCTION;
								}
								else
								{
									// legacy function
									if (fhd.getEquationUser().getEquationUnit().isLegacyOption(optionNextRequest))
									{
										doNewTransaction(optionNextRequest, "");
									}
									else
									{
										initTransaction(optionNextRequest, "", FunctionRuntimeToolbox.INIT_DESK_LINK,
														ScreenSetHandler.SCREENSET_DEFAULT);
									}
								}
							}

							// if not authorised
							else
							{
								// drill down function, then go back to the parent
								if (fhd.getParentFunctionHandler() != null)
								{
									removeDrillDownChildProcessing();
								}
								else if (fhd.getGbRecord().getRepeatProcessing().equals(EqDataType.YES))
								{
									initTransaction(fhd.getOptionId(), "", FunctionRuntimeToolbox.INIT_DESK,
													ScreenSetHandler.SCREENSET_DEFAULT);
								}
								else
								{
									nextRequest = FunctionRuntimeToolbox.PROC_EXIT_FUNCTION;
								}
							}
						}
					}
					else
					{
						nextRequest = FunctionRuntimeToolbox.PROC_REDISPLAY_SCREEN;
						// fhd.getSecurityLevel().setUpdateMessage(true);
						// fhd.getSecurityLevel().setRequiredCheckerType(SecurityLevel.CHCKR_NONE);
						// fhd.getFunctionMsgManager().getFunctionMessages().setMsgSev(FunctionMessages.MSG_INFO);
						// fhd.getScreenSetHandler().setCurScreenSet(curScreenSet);
					}
				}
				else
				{
					processReturnedMessages(fhd.getScreenSetHandler().rtvScrnSetCurrent().getFunctionMessages());
				}
			}

			// Unclaim if needed
			if (nextRequest != FunctionRuntimeToolbox.PROC_REDISPLAY_SCREEN)
			{
				// .. only unclaim if session is not restored (this mean this is an ESF)
				FunctionSession fs = fhd.getFunctionSession();
				if (!fs.isSessionRestored() || fhd.getSecurityLevel().getCheckerType() != SecurityLevel.CHCKR_NONE
								|| (fs.isSessionRestored() && fs.getStatus().equals(WERecordDataModel.STAT_ABORT)))
				{
					unClaimTask();
				}
				else
				{
					EquationFunctionContext.getContext().clearTaskActiveList(fhd);
				}
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			FunctionRuntimeToolbox.generateOtherMessages(fhd.getFunctionMsgManager(), fhd.getEquationUser().getSession(), fhd
							.getScreenSetHandler().getCurScreenSet(), fhd.getScreenSetHandler().rtvScrnSetCurrent().getScrnNo(),
							"actionFkey", e);
			LOG.error(fhd.getFunctionDebugInfo().toString());
		}
		finally
		{
			releaseLocks();
			fhd.getFunctionDebugInfo().clear();

			// Exit function
			if (nextRequest == FunctionRuntimeToolbox.PROC_EXIT_FUNCTION)
			{
				FunctionRuntimeToolbox.updateOptionInGH(rtvEquationSession(), "", fhd.getEquationUser().getUserId(), fhd
								.getFunctionInfo().getSessionId());

				FunctionHandler parent = fhd.rtvTopDrillDownParent();
				if (parent != null)
				{
					parent.getFhd().removeDrillDownChild();
				}
			}

			// Direct transaction, then exit desktop if function has been completed
			if (nextRequest == FunctionRuntimeToolbox.PROC_EXIT_FUNCTION
							&& fhd.getFunctionInfo().getSessionType() == EquationCommonContext.SESSION_DIRECT_TRANS_DESKTOP)
			{
				nextRequest = FunctionRuntimeToolbox.PROC_EXIT_DESKTOP;
			}
		}

		return nextRequest;
	}

	/**
	 * Determines if this function needs to re-initialise itself for a new function
	 * 
	 * @param optionId
	 *            - option ID of the function
	 * 
	 * @return true, if a new function has been invoked
	 */
	private boolean chkNewFunction(String optionId)
	{
		// optionId is null then assume do not load
		if (optionId == null)
		{
			return false;
		}

		// predefined value
		if (optionId.equals(FunctionRuntimeToolbox.NO_LOADOPTION))
		{
			return false;
		}

		// new function to be loaded
		return true;
	}

	/**
	 * Perform processing based on the function key
	 * 
	 * @param ckey
	 *            - function key
	 * @param fldValidate
	 *            - the specific field to validate. If not specified, then validate the current screen
	 * @param tranId
	 *            - transaction id (valid only during Save button)
	 * 
	 * @return true - if function key has been processed <br>
	 *         false - function key not processed
	 */
	public boolean validateFkey(int ckey, String fldValidate, String tranId)
	{
		try
		{
			// Debug info
			fhd.getFunctionDebugInfo().clear();
			fhd.getFunctionDebugInfo().printStartMethod("validateFkey()");

			// Screen set main
			ScreenSet screenSetMain = fhd.getScreenSetHandler().rtvScreenSetMain();
			screenSetMain.getFunctionData().chgFieldDbValue(FunctionData.FLD_FCT, screenSetMain.rtvMode());

			// Screen set
			ScreenSet screenSet = fhd.getScreenSetHandler().rtvScrnSetCurrent();

			// Determine if function key is valid or not
			if (!fhd.getFunctionKeys().chkValidKey(ckey))
			{
				return false;
			}

			// Clear other messages
			fhd.getFunctionMsgManager().clearOtherMessages();

			// Set the next expected screen set to default processing
			fhd.getScreenSetHandler().setNextExpectedScreen(-1);

			// Supervisor mode?
			if (fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_SUPER)
			{
				// not page up
				if (ckey == FunctionKeys.KEY_PGUP || !screenSet.performValidateDuringNoUpdate(-1, -1))
				{
					return true;
				}
			}

			// Let the specific function handle the function key processing
			if (screenSet.validateFkey())
			{
				return true;
			}

			// Save?
			if (ckey == FunctionKeys.KEY_SAVE)
			{
				save(WERecordDataModel.STAT_WIP, tranId);
				return true;
			}

			// Save as template
			if (ckey == FunctionKeys.KEY_SVTMPL)
			{
				fhd.getFunctionMsgManager().clearMessages();
				processReturnedMessages(fhd.getFunctionMsgManager().getFunctionMessages());
				save(WERecordDataModel.STAT_TMPLT, tranId);
				return true;
			}

			// Linked programs?
			if (ckey == FunctionKeys.KEY_F2 || ckey == FunctionKeys.KEY_F11 || ckey == FunctionKeys.KEY_F14
							|| ckey == FunctionKeys.KEY_F23)
			{
				fhd.getFunctionMsgManager().clearMessages();
				processReturnedMessages(fhd.getFunctionMsgManager().getFunctionMessages());
				return true;
			}

			// Page up?
			if (ckey == FunctionKeys.KEY_PGUP)
			{
				fhd.getFunctionMsgManager().clearMessages();
				int prev = prevScrnUserExit(screenSet);
				fhd.getScreenSetHandler().setNextExpectedScreen(prev);
				return true;
			}

			// Toggle?
			if (ckey == FunctionKeys.KEY_TOGLE)
			{
				DisplayGroupHandler displayGroupHandler = screenSet.getDisplayGroupHandler();

				// is there only one group field, then always toggle this field
				boolean proc = false;
				if (displayGroupHandler.isGroupExists(screenSet.getCurrentFieldSet()) == 1)
				{
					proc = true;
					displayGroupHandler.chgFieldSetNextSubLevel(screenSet.getCurrentFieldSet());
				}

				// check if the field is toggle-capable
				if (!proc && !fldValidate.equals(""))
				{
					DisplayAttributes displayAttributes = screenSet.getLayout().rtvDisplayAttributes(fldValidate);
					if (displayAttributes != null)
					{
						if (!displayAttributes.getGroupId().equals(""))
						{
							proc = true;
							displayGroupHandler.chgNextSubLevel(displayAttributes.getGroupId());
						}
					}
				}

				// not processed?
				fhd.getFunctionMsgManager().clearMessages();
				FunctionMessages fms = new FunctionMessages();
				if (!proc)
				{
					fhd.getFunctionMsgManager().generateMessage(rtvEquationSession(), fms, screenSet.getId(),
									screenSet.getScrnNo(), "", 0, null, "KSM7356", "", "", FunctionMessages.MSG_NONE);
				}
				processReturnedMessages(fms);

				return true;
			}

			// Refresh?
			if (ckey == FunctionKeys.KEY_RFRSH && screenSetMain.chkEnquiry())
			{
				fhd.getFunctionMsgManager().clearMessages();
				return true;
			}

			// Delete?
			if (ckey == FunctionKeys.KEY_DEL)
			{
				screenSetMain.getFunctionData().chgFieldDbValue(FunctionData.FLD_FCT, IEquationStandardObject.FCT_DEL);
			}

			// Drill down?
			if (ckey == FunctionKeys.KEY_DRILLDOWN || ckey == FunctionKeys.KEY_DRILLDOWN2 || ckey == FunctionKeys.KEY_DRILLDOWN3)
			{
				fldValidate = ""; // blank out this field as this contains the repeating group
				fhd.getFunctionKeys().setFieldId("");
			}

			// Drill up?
			if (ckey == FunctionKeys.KEY_DRILLUP)
			{
				fhd.getFunctionMsgManager().clearMessages();
				return true;
			}

			// LRP refer and decline?
			if (fhd.isLRPTask() && (ckey == FunctionKeys.KEY_REFER || ckey == FunctionKeys.KEY_DECL))
			{
				fhd.getFunctionMsgManager().clearMessages();
				processReturnedMessages(fhd.getFunctionMsgManager().getFunctionMessages());
				return true;
			}

			if (fhd.getEquationUser().getEquationUnit().hasMakerCheckerEnhancement())
			{
				// Declined by Supervisor
				if (fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_SUPER && ckey == FunctionKeys.KEY_DECL)
				{
					// transaction still pending?
					if (!fhd.getFunctionSession().validatePendingStatus(rtvEquationSession()))
					{
						return false; // do not proceed when status changed
					}
				}

				// Maker (maker-checker) resubmit/cancel
				if (fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_MAKER_MAKER
								&& (ckey == FunctionKeys.KEY_ENT || ckey == FunctionKeys.KEY_PREV))
				{
					if (!fhd.getFunctionSession().validateCancelledStatus(rtvEquationSession()))
					{
						return false; // do not proceed when status changed
					}
					fhd.getFunctionMsgManager().clearMessages();
					fhd.getScreenSetHandler().rtvScreenSetMain().getFunctionMessages().clearMessages();
					processReturnedMessages(fhd.getFunctionMsgManager().getFunctionMessages());
					return true;
				}

				// Checker (maker-checker) authorising/declining transactions, then clear all messages
				if (fhd.getSecurityLevel().getCheckerType() == SecurityLevel.CHCKR_MAKER_CHECKER)
				{
					if (ckey == FunctionKeys.KEY_AUTH || ckey == FunctionKeys.KEY_DECL)
					{
						// transaction still pending?
						if (!fhd.getFunctionSession().validatePendingStatus(rtvEquationSession()))
						{
							return false; // do not proceed for when status changed
						}
					}
					fhd.getFunctionMsgManager().clearMessages();
					fhd.getScreenSetHandler().rtvScreenSetMain().getFunctionMessages().clearMessages();
					processReturnedMessages(fhd.getFunctionMsgManager().getFunctionMessages());
					return true;
				}
			}

			// Determine the current message severity
			int msgSev = rtvMsgSev();

			// Informational message - if these have been displayed then move to the list of displayed informational message so that
			// it
			// will not be displayed anymore
			if (msgSev == FunctionMessages.MSG_INFO)
			{
				fhd.getFunctionMsgManager().markDispInfoMsg();
				fhd.getFunctionMsgManager().getFunctionMessages().setMsgSev(FunctionMessages.MSG_NONE);
			}

			// Warning message - if user has override a warning, move the first warning message, so that it will not be issued
			// anymore
			if (ckey == FunctionKeys.KEY_OVR && msgSev == FunctionMessages.MSG_WARN)
			{
				fhd.getFunctionMsgManager().overrideFirstWarning(SupervisorToolbox.AUTO_SUPERVISOR);
			}

			// Validating the entire screen?
			if (fldValidate.equals(""))
			{
				// validate the screen(s)
				int next = -1;
				int id = screenSet.getId();
				int currentScreen = screenSet.getScrnNo();
				for (int i = screenSet.getScrnNo(); i < screenSet.getMaxScrnNo(); i++)
				{
					if (!screenSet.isKeyOpen())
					{
						screenSet.setLastScrnNoDisplayed(i);
					}

					if (i != currentScreen)
					{
						screenSet.initialiseInputFieldSet(screenSet.function.getInputFieldSets().get(screenSet.getScrnNo()));
					}

					validate(i);

					// selection option exists during the validation and user presses enter key, then stop validation to process
					// the selection option
					// TODO: is this ok, or a new function key needs to trigger the selection option processing?
					if (screenSet.isSelectionExists() && ckey == FunctionKeys.KEY_ENT)
					{
						break;
					}

					// if enter key is press, then proceed to next screen if there is no message
					else if ((ckey == screenSet.getFKeyToNextScreenSet() || ckey == FunctionKeys.KEY_DEL || ckey == FunctionKeys.KEY_CHARGE)
									&& rtvMsgSev() == FunctionMessages.MSG_NONE && id == screenSet.getId())
					{
						if (screenSet.nextScreen())
						{
						}
					}

					// if page down has been pressed and there is no messages, then try to determine the next
					// screen to display
					else if (ckey == FunctionKeys.KEY_PGDN && rtvMsgSev() == FunctionMessages.MSG_NONE && next == -1)
					{
						next = nextScrnUserExit(screenSet);
						if (next <= 0 || (i + 1 == next))
						{
							break;
						}
						if (screenSet.nextScreen())
						{
						}
					}
					// .. and then validate all the screens prior to it
					else if (ckey == FunctionKeys.KEY_PGDN && rtvMsgSev() == FunctionMessages.MSG_NONE && (i + 1) < next)
					{
						if (screenSet.nextScreen())
						{
						}
					}

					// otherwise, exit
					else
					{
						break;
					}
				}
			}
			else
			{
				validate(screenSet.getScrnNo(), screenSet.getFunction().getInputFieldSets().get(screenSet.getScrnNo()).getId(),
								fldValidate);
			}

			// process
			return true;
		}
		catch (Exception e)
		{
			LOG.error(e);
			FunctionRuntimeToolbox.generateOtherMessages(fhd.getFunctionMsgManager(), fhd.getEquationUser().getSession(), fhd
							.getScreenSetHandler().getCurScreenSet(), fhd.getScreenSetHandler().rtvScrnSetCurrent().getScrnNo(),
							"validateFkey", e);
			LOG.error(fhd.getFunctionDebugInfo().toString());
			return false;
		}
		finally
		{
			releaseLocks();
			fhd.getFunctionDebugInfo().clear();
		}
	}

	/**
	 * Generate the function key
	 * 
	 */
	private void generateFkey() throws EQException
	{
		ScreenSet screenSet = fhd.getScreenSetHandler().rtvScrnSetCurrent();
		screenSet.generateFkeys();
	}

	/**
	 * Processing when any data has been changed
	 */
	public void dirtyScreen(int scno)
	{
		// remove displayed messages from the specified screen
		fhd.getFunctionMsgManager().getDispInfoMessages().clearMessages(fhd.getScreenSetHandler().getCurScreenSet(), scno);

		// remove overridden messages from the specified screen
		fhd.getFunctionMsgManager().getOverWarnMessages().clearMessages(fhd.getScreenSetHandler().getCurScreenSet(), scno);

		// also remove higher valued "screens"
		fhd.getFunctionMsgManager().getDispInfoMessages().clearHigherScreen(fhd.getScreenSetHandler().getCurScreenSet(), scno);
		fhd.getFunctionMsgManager().getOverWarnMessages().clearHigherScreen(fhd.getScreenSetHandler().getCurScreenSet(), scno);
	}

	/**
	 * Return the function's HTML representation
	 * 
	 * @return the function's HTML representation
	 */
	public String rtvScrnSetHTML()
	{
		try
		{
			EqTimingTest.printStartTime("FunctionHandler.rtvScrnSetHTML()", "");
			fhd.getFunctionDebugInfo().clear();
			return fhd.getScreenSetHandler().rtvScrnSetHTML();
		}
		catch (Exception e)
		{
			LOG.error(e);
			LOG.error(fhd.getFunctionDebugInfo().toString());
			FunctionRuntimeToolbox.generateOtherMessages(fhd.getFunctionMsgManager(), fhd.getEquationUser().getSession(), fhd
							.getScreenSetHandler().getCurScreenSet(), fhd.getScreenSetHandler().rtvScrnSetCurrent().getScrnNo(),
							"rtvScrnSetHTML", e);
			return null;
		}
		finally
		{
			EqTimingTest.printTime("FunctionHandler.rtvScrnSetHTML()", "");
			fhd.getFunctionDebugInfo().clear();
		}
	}

	// -------------------------------------------------------------- SCREEN HANDLER: END

	// -------------------------------------------------------------- FUNCTION HANDLER PROPERTIES: BEGIN

	/**
	 * Return the current function handler data
	 * 
	 * @return the current function handler data
	 */
	public FunctionHandlerData getFhd()
	{
		return fhd;
	}

	/**
	 * Return the Equation standard session
	 * 
	 * @return an Equation standard session
	 */
	public EquationStandardSession rtvEquationSession()
	{
		return fhd.getEquationUser().getSession();
	}

	/**
	 * Returns the main function data
	 * 
	 * @return the main function data
	 */
	public FunctionData rtvFunctionData()
	{
		return fhd.getScreenSetHandler().rtvScreenSetMain().getFunctionData();
	}

	/**
	 * Returns the message severity of the current function
	 * 
	 * @return the message severity
	 */
	public int rtvMsgSev()
	{
		return fhd.getFunctionMsgManager().getFunctionMessages().getMsgSev();
	}

	/**
	 * Return the function error
	 * 
	 * @return the function error
	 */
	public FunctionMessages rtvFunctionMessages()
	{
		return fhd.getFunctionMsgManager().getFunctionMessages();
	}

	// -------------------------------------------------------------- FUNCTION HANDLER PROPERTIES: END

	// -------------------------------------------------------------- MESSSAGE PROCESSING: BEGIN

	/**
	 * Process the returned list of messages
	 * 
	 * @param fms
	 *            - returned list of messages
	 * 
	 * @return the message severity
	 */
	private int processReturnedMessages(FunctionMessages fms) throws EQException
	{
		// current screen set
		ScreenSet screenSet = fhd.getScreenSetHandler().rtvScrnSetCurrent();

		// pass to the message manager
		int msgSev = fhd.getFunctionMsgManager().insertMessages(fhd.getScreenSetHandler().getCurScreenSet(), screenSet.getScrnNo(),
						fms);

		// clear lower message severity
		// screenSet.getFunctionData().clearMessages(msgSev);

		// processReferral
		processReferral();

		// return the message severity
		return msgSev;
	}

	/**
	 * Determine whether to refer it to another checker
	 * 
	 */
	public void processReferral() throws EQException
	{
		SecurityLevel securityLevel = fhd.getSecurityLevel();

		// always reset
		securityLevel.setRequiredCheckerType(SecurityLevel.CHCKR_NONE);
		securityLevel.setRequiredCheckerId("");

		// enquiry, never refer
		if (securityLevel.isEnquireMode())
		{
			return;
		}

		// warning messages to be referred to supervisor?
		if (securityLevel.getCheckerType() == SecurityLevel.CHCKR_NONE
						&& rtvFunctionMessages().getMsgSev() == FunctionMessages.MSG_WARN)
		{
			// if this is CRM, supervisor authorisation is needed if user is not allowed to override it
			if (fhd.getScreenSetHandler().rtvScrnSetCurrent().getOptionId().equals(ScreenSetCRM.OPTION)
							&& (fhd.getEquationUser().getLimitOverride().equals(OCRecordDataModel.LIMIT_OVERRIDE_SUPERVISOR) || (fhd
											.getEquationUser().getEquationUnit().isEsfActive() && fhd.getEquationUser()
											.getLimitOverride().equals(OCRecordDataModel.LIMIT_OVERRIDE_ESF))))
			{
				securityLevel.setRequiredCheckerType(SecurityLevel.CHCKR_SUPER);
			}

			// otherwise, supervisor authorisation is required depending on the KSM
			else if (fhd.getEquationUser().getEquationUnit().isEsfActive())
			{
				WARecordDataModel dataModel = new WARecordDataModel(fhd.getFunctionMsgManager().getFunctionMessages().getMessages()
								.get(0).getEqMessage().getMessageID());

				IWARecordDao waDao = fhd.getDaoFactory().getWADao(rtvEquationSession(), dataModel);
				WARecordDataModel waRecord = waDao.getWARecord();

				if (waRecord == null || !waRecord.getSev().trim().equals(WARecordDataModel.NO_SUPERVISOR))
				{
					securityLevel.setRequiredCheckerType(SecurityLevel.CHCKR_SUPER);
				}
			}
		}

		// is this maker-checker specific KSM
		if (fhd.getEquationUser().getEquationUnit().hasMakerCheckerEnhancement()
						&& securityLevel.getRequiredCheckerType() == SecurityLevel.CHCKR_SUPER
						&& fhd.getFunctionMsgManager().getFunctionMessages().getMessages().get(0).getEqMessage().getMessageID()
										.equals(MakerCheckerUtility.MAKER_CHECKER_KSM))
		{
			securityLevel.setRequiredCheckerType(SecurityLevel.CHCKR_MAKER_CHECKER);
			fhd.getFunctionMsgManager().getFunctionMessages().getMessages().remove(0);
		}
	}

	// -------------------------------------------------------------- MESSSAGE PROCESSING: END

	// -------------------------------------------------------------- VALIDATE INTERFACE: BEGIN

	/**
	 * Performs loading of details specified by the keys of the specified field set
	 * 
	 * @param inputFieldSetId
	 *            - field set to load
	 * @param loadField
	 *            - field
	 * @param journal
	 *            - journal it?
	 * @param withKey
	 *            - true if called for function with keys
	 * 
	 * @return message severity
	 * 
	 * @throws EQException
	 */
	private int loadKeyFieldSet(String inputFieldSetId, String loadField, boolean journal, boolean withKey) throws EQException
	{
		// clear messages
		fhd.getFunctionMsgManager().clearMessages();

		// load field set status
		ScreenSet screenSet = fhd.getScreenSetHandler().rtvScrnSetCurrent();
		LoadFieldSetStatusHandler loadFieldSetStatusHandler = screenSet.getLoadFieldSetStatusHandler();
		LoadFieldSetStatus status = loadFieldSetStatusHandler.getFieldSetStatus(inputFieldSetId);

		// detail currently displayed, then ignore
		if (status.isDetailOpen())
		{
			return FunctionMessages.MSG_NONE;
		}

		// load it
		screenSet.retrieve(inputFieldSetId);

		// process messages
		processReturnedMessages(screenSet.getFunctionMessages());

		// process function mode
		int sessionType = fhd.getFunctionInfo().getSessionType();
		if (screenSet.getFunctionMessages().getMsgSev() == FunctionMessages.MSG_NONE
						&& EquationCommonContext.isDesktopSession(sessionType)
						|| screenSet.getFunctionMessages().getMsgSev() <= FunctionMessages.MSG_ERROR
						&& !EquationCommonContext.isDesktopSession(sessionType))
		{
			// open the detail
			loadFieldSetStatusHandler.openDetailFieldSet(inputFieldSetId);
			screenSet.getFunctionData().chgFieldDbValue(FunctionData.FLD_KEYLOADED, EqDataType.NO);

			// journal it
			ScreenSet screenSetMain = fhd.getScreenSetHandler().rtvScreenSetMain();
			if ((journal || EquationCommonContext.isDesktopSession(fhd.getFunctionInfo().getSessionType()))
							&& screenSetMain.chkEnquiry())
			{
				JournalHeader journalHeader = FunctionRuntimeToolbox.setupJournal(IEquationStandardObject.FCT_ENQ, fhd
								.getFunctionInfo().getWorkStationId(), screenSetMain, fhd.getEquationUser(), fhd
								.getFunctionMsgManager().getOverWarnMessages());
				FunctionRuntimeToolbox.writeJournalEnquiry(fhd.getEquationUser().getSession(), journalHeader, screenSetMain
								.getFunction(), screenSetMain.getFunctionData(), screenSetMain.getFunctionAdaptor(), fhd);
				fhd.setJournalHeader(journalHeader);
			}

			// lock all key fields so it cannot be changed
			screenSet.getFunctionData().lockedKeyFields(screenSet.getFunction());

			// does not contain any non-key details, then automatically proceed to the next screen
			if (EquationCommonContext.isDesktopSession(fhd.getFunctionInfo().getSessionType())
							&& !screenSet.getFunction().getInputFieldSet(inputFieldSetId).containsNonKeyFields())
			{
				screenSet.nextScreen();
			}

			// validate all screens - only if load API has actually been executed
			if (withKey || !screenSet.getFunctionAdaptor().isAbstractPostLoadMethodExecute()
							|| screenSet.getFunction().rtvPrimaryInputFieldSet().getLoadAPIs().size() > 0)
			{
				FunctionValidate funcValidate = new FunctionValidate(fhd);
				funcValidate.setHaltOnError(false);
				funcValidate.setDefaultValues(true);
				funcValidate.setApplicationValidate(false);
				funcValidate.setLoadMode(true);
				for (int i = 0; i < screenSet.getMaxScrnNo(); i++)
				{
					funcValidate.validate(i, i);
				}

				// for enquiry, another validation pass to return the list of messages
				if (fhd.getSecurityLevel().isEnquireMode())
				{
					validate(screenSet.getScrnNo());
				}
				else
				{
					screenSet.getFunctionData().clearMessages(FunctionData.CLEAR_MSG_ALL);
				}
			}
		}

		// return the message severity
		return rtvMsgSev();
	}

	/**
	 * Performs unloading of details specified by the keys of the specified field set
	 * 
	 * @param inputFieldSetId
	 *            - field set to load
	 * @param loadField
	 *            - field
	 * 
	 * @return message severity
	 * 
	 * @throws EQException
	 */
	private int unloadKeyFieldSet(String inputFieldSetId, String loadField) throws EQException
	{
		LoadFieldSetStatusHandler loadFieldSetStatusHandler = fhd.getScreenSetHandler().rtvScrnSetCurrent()
						.getLoadFieldSetStatusHandler();
		LoadFieldSetStatus status = loadFieldSetStatusHandler.getFieldSetStatus(inputFieldSetId);

		// unloading only if detail is currently displayed and there are keys
		if (status.isDetailOpen() && status.isKeyExist())
		{
			// open the key
			ScreenSet screenSet = fhd.getScreenSetHandler().rtvScrnSetCurrent();
			loadFieldSetStatusHandler.openKeyFieldSet(inputFieldSetId);
			screenSet.getFunctionData().chgFieldDbValue(FunctionData.FLD_KEYLOADED, EqDataType.YES);

			if (inputFieldSetId.equals(screenSet.getMainInputFieldSet().getId()))
			{
				screenSet.setBeforeImagesTransactions(null);
				screenSet.setFunctionDataBefore(null);
				screenSet.setMode(IEquationStandardObject.FCT_ADD);
				screenSet.setLastScrnNoDisplayed(-1);
				screenSet.getFunctionData().chgFieldDbValue(FunctionData.FLD_FCT, screenSet.rtvMode());
				screenSet.unload(inputFieldSetId);
				screenSet.clearLoadIncomplete();

				fhd.getSecurityLevel().setEnquireMode(false);
				unClaimTask();

				// unprotect key fields
				screenSet.getFunctionData().unlockedKeyFields(screenSet.getFunction());
				screenSet.getFunctionData().resetFields(screenSet.getFunction(), false, fhd);
			}
		}

		// clear messages
		FunctionMsgManager functionMsgManager = fhd.getFunctionMsgManager();
		functionMsgManager.clearAllMessages();
		processReturnedMessages(functionMsgManager.getFunctionMessages());

		return FunctionMessages.MSG_NONE;
	}

	/**
	 * Performs validation of all screens
	 * 
	 * @return the message severity
	 * 
	 * @throws EQException
	 */
	public int validate() throws EQException
	{
		return validate(0, fhd.getScreenSetHandler().rtvScrnSetCurrent().getMaxScrnNo() - 1);
	}

	/**
	 * Performs validation on the specified screen
	 * 
	 * @param scrnNo
	 *            - screen number
	 * 
	 * @return the message severity
	 * 
	 * @throws EQException
	 */
	private int validate(int scrnNo) throws EQException
	{
		return validate(scrnNo, scrnNo);
	}

	/**
	 * Performs validation on the specified screens
	 * 
	 * @param fromScrnNo
	 *            - from screen number
	 * @param toScrnNo
	 *            - to screen number
	 * 
	 * @return the message severity
	 * 
	 * @throws EQException
	 */
	private int validate(int fromScrnNo, int toScrnNo) throws EQException
	{
		// current screen set
		ScreenSet screenSet = fhd.getScreenSetHandler().rtvScrnSetCurrent();

		// transaction cannot be updated
		if (fhd.getSecurityLevel().chkNoValidate())
		{
			if (!screenSet.performValidateDuringNoUpdate(fromScrnNo, toScrnNo))
			{
				return FunctionMessages.MSG_NONE;
			}
		}

		// print timing test
		EqTimingTest.printStartTime("FunctionHandler.validate()", "");

		// clear the messages
		fhd.getFunctionMsgManager().clearMessages();

		// validate
		int msgSev = screenSet.validate(fromScrnNo, toScrnNo);

		// add the returned messages
		msgSev = processReturnedMessages(screenSet.getFunctionMessages());

		// enquiry, then suppress message
		if (fhd.getSecurityLevel().isEnquireMode() && !screenSet.isSelectionExistsError())
		{
			fhd.getFunctionMsgManager().getFunctionMessages().setMsgSev(FunctionMessages.MSG_NONE);
		}

		// load to context
		fhd.getContextHandler().saveFunctionToContextData(screenSet.getFunction(), screenSet.getFunctionData());

		// print timing test
		EqTimingTest.printTime("FunctionHandler.validate()", "");

		// return the message severity
		return (msgSev);
	}

	/**
	 * Performs validation on the current screenset
	 * 
	 * @return the message severity
	 * 
	 * @throws EQException
	 */
	private int lastValidate() throws EQException
	{
		// current screen set
		ScreenSet screenSet = fhd.getScreenSetHandler().rtvScrnSetCurrent();

		// transaction cannot be updated
		if (fhd.getSecurityLevel().chkNoValidate())
		{
			return FunctionMessages.MSG_NONE;
		}

		// print timing test
		EqTimingTest.printStartTime("FunctionHandler.lastValidate()", "");

		// clear the messages
		fhd.getFunctionMsgManager().clearMessages();

		// validate
		int msgSev = screenSet.lastValidate();

		// print timing test
		EqTimingTest.printTime("FunctionHandler.lastValidate()", "");

		// return the message severity
		return (msgSev);
	}

	/**
	 * Performs validation of an specific field in a field set
	 * 
	 * @return the message severity
	 * 
	 * @throws EQException
	 */
	private int validate(int scrnNo, String fieldSet, String fieldName) throws EQException
	{
		// transaction cannot be updated
		if (fhd.getSecurityLevel().chkNoValidate())
		{
			return FunctionMessages.MSG_NONE;
		}

		// clear the messages
		fhd.getFunctionMsgManager().clearMessages();

		// create the validator
		FunctionValidate funcValidate = new FunctionValidate(fhd);

		// validate it
		int msgSev = funcValidate.validate(scrnNo, fieldSet, fieldName);

		// add the returned messages
		msgSev = processReturnedMessages(funcValidate.getFunctionMessages());

		// return the message severity
		return (msgSev);
	}

	// -------------------------------------------------------------- VALIDATE INTERFACE: END

	// -------------------------------------------------------------- UPDATE INTERFACE: BEGIN

	/**
	 * Performs update
	 * 
	 * @return the message severity
	 * 
	 * @throws EQException
	 */
	public int update(boolean updateMode) throws EQException
	{
		// transaction cannot be updated
		if (fhd.getSecurityLevel().chkNoUpdate())
		{
			// delete the saved session
			if (fhd.getSecurityLevel().getCheckerType() != SecurityLevel.CHCKR_SUPER)
			{
				fhd.getFunctionSession().delete(fhd.getEquationUser().getSession(), true);
			}

			return FunctionMessages.MSG_NONE;
		}

		return update1(updateMode);
	}

	/**
	 * Perform update
	 * 
	 * @param updateMode
	 *            - update mode
	 * 
	 * @return the message severity
	 */
	private int update1(boolean updateMode) throws EQException
	{
		// clear the messages
		fhd.getFunctionMsgManager().clearMessages();
		int msgSev = FunctionMessages.MSG_NONE;

		// debug info
		fhd.getFunctionDebugInfo().printStartMethod("update()");

		try
		{
			// screen set handler
			ScreenSetHandler screenSetHandler = fhd.getScreenSetHandler();

			// perform update on all the screens
			screenSetHandler.setCurScreenSet(0);
			FunctionUpdate functionUpdate = new FunctionUpdate(fhd);
			functionUpdate.setCommitProcessing(fhd.getFunctionInfo().isCommitProcessing());
			functionUpdate.setGenerateWarningInfo(fhd.getFunctionInfo().isGenerateWarningInfo());
			functionUpdate.update(updateMode);

			// retrieve message severity
			msgSev = functionUpdate.getFunctionMessages().getMsgSev();

			// Generate message
			if (msgSev >= FunctionMessages.MSG_ERROR || fhd.getFunctionInfo().isGenerateWarningInfo()
							&& msgSev != FunctionMessages.MSG_NONE)
			{
				int screenSet = functionUpdate.getFunctionMessages().getMessages().get(0).getScreenSetId();
				screenSetHandler.setCurScreenSet(screenSet);
				screenSetHandler.lastScreenSetBefore();
			}
			// No message
			else
			{
				screenSetHandler.setCurScreenSet(0);
				functionUpdate.getFunctionMessages().clearMessages(); // clear warnings
				fhd.setJournalHeader(functionUpdate.getJournalHeader());
				fhd.setUpdateMade(functionUpdate.isUpdateMade());
			}

			msgSev = processReturnedMessages(functionUpdate.getFunctionMessages());
		}
		catch (Exception e)
		{
			LOG.error(e);
			LOG.error(fhd.getFunctionDebugInfo().toString());
			FunctionMessages fms = new FunctionMessages();
			fhd.getFunctionMsgManager().generateMessage(rtvEquationSession(), fms, fhd.getScreenSetHandler().getCurScreenSet(), 0,
							"", 0, null, "KSM7340 " + Toolbox.getExceptionMessage(e), "", "", FunctionMessages.MSG_NONE);
			fhd.getScreenSetHandler().lastScreenSetBefore();
			msgSev = processReturnedMessages(fms);
		}
		finally
		{
			fhd.getFunctionDebugInfo().printEndMethod("update()");
		}

		// During update mode, check the level of severity, if there are major severity, then protect all the fields and
		// user need to exit from the function
		// if (msgSev >= FunctionMessages.MSG_ERROR)
		// {
		// fhd.getSecurityLevel().setUpdateMessage(true);
		// }

		// return the message severity
		return msgSev;
	}

	// -------------------------------------------------------------- UPDATE INTERFACE: END

	// -------------------------------------------------------------- PRINTER INTERFACE: BEGIN

	/**
	 * Performs printing
	 * 
	 * @return the journal print entries
	 * 
	 * @throws EQException
	 */
	public List<String> print() throws EQException
	{
		ArrayList<String> lines = new ArrayList<String>();
		ScreenSetHandler screenSetHandler = fhd.getScreenSetHandler();
		for (int i = 0; i <= screenSetHandler.getLastScreenSetViewed(); i++)
		{
			ScreenSet screenSet = screenSetHandler.getScreenSets().get(i);
			lines.addAll(screenSet.print());
			lines.add(" ");
			lines.add(" ");
		}

		return lines;
	}

	/**
	 * Performs printing of GZ
	 * 
	 * @param workStation
	 *            - work station
	 * @param jrnDay
	 *            - journal day
	 * @param jrnTime
	 *            - journal time
	 * @param jrnSequence
	 *            - journal sequence
	 * @param printBlank
	 *            - print blank lines?
	 * @param library
	 *            - library where the journal is located
	 * 
	 * @return the journal print entries
	 * 
	 * @throws EQException
	 */
	public List<String> print(String workStation, int jrnDay, int jrnTime, int jrnSequence, boolean printBlank, String library)
					throws EQException
	{
		try
		{
			FunctionPrinter functionPrinter = new FunctionPrinter(fhd);
			functionPrinter.setPrintHeader(false);
			functionPrinter.setPrintBlankLine(printBlank);
			functionPrinter.print(workStation, jrnDay, jrnTime, jrnSequence, library);
			return functionPrinter.getLines();
		}
		catch (EQException e)
		{
			LOG.error(e);
			throw e;
		}
		finally
		{
			releaseLocks();
		}
	}

	/**
	 * Performs printing of GZ
	 * 
	 * @param workStation
	 *            - work station
	 * @param jrnDay
	 *            - journal day
	 * @param jrnTime
	 *            - journal time
	 * @param jrnSequence
	 *            - journal sequence
	 * @param printBlank
	 *            - print blank lines?
	 * @param library
	 *            - library where the journal is located
	 * 
	 * @return the journal print entries
	 */
	public String printAsString(String optionId, String workStation, int jrnDay, int jrnTime, int jrnSequence, boolean printBlank,
					String library)
	{
		FunctionHandler fh = null;
		String lines = null;
		try
		{
			fh = new FunctionHandler(fhd.getEquationUser(), fhd.getFunctionInfo());
			fh.initTransaction(optionId, "", FunctionRuntimeToolbox.INIT_SAVE, ScreenSetHandler.SCREENSET_DEFAULT);

			FunctionPrinter functionPrinter = new FunctionPrinter(fh.getFhd());
			functionPrinter.setPrintHeader(false);
			functionPrinter.setPrintBlankLine(printBlank);
			functionPrinter.print(workStation, jrnDay, jrnTime, jrnSequence, library);

			lines = functionPrinter.rtvAsString();
		}
		catch (Exception e)
		{
			LOG.error(e);
			lines = Toolbox.pad(Toolbox.getExceptionMessage(e), 132);
		}
		finally
		{
			releaseLocks();
		}

		// return
		byte[] linesInBytes = Toolbox.convertTextIntoAS400BytesCCSID(lines, lines.length() * 2, fh.getFhd().getEquationUser()
						.getSession().getCcsid(), BidiStringType.DEFAULT);
		String linesInHex = Toolbox.cvtBytesToHexString(linesInBytes);
		return linesInHex;
	}

	// -------------------------------------------------------------- PRINTER INTERFACE: END

	// -------------------------------------------------------------- API INTERFACE: BEGIN

	/**
	 * Performs API (add or maintain) on the current data
	 * 
	 * @return the highest message severity
	 * 
	 * @throws EQException
	 * 
	 */
	public int applyTransaction() throws EQException
	{
		try
		{
			FunctionAPI functionAPI = new FunctionAPI(fhd);
			functionAPI.setCommitProcessing(fhd.getFunctionInfo().isCommitProcessing());
			functionAPI.setEfcProcessing(!EquationCommonContext.isLinkedSession(fhd.getFunctionInfo().getSessionType()));
			int msgSev = functionAPI.applyTransaction();
			fhd.getFunctionMsgManager().getFunctionMessages().insertMessages(functionAPI.getFunctionMessages());
			return msgSev;
		}
		catch (EQException e)
		{
			throw e;
		}
		finally
		{
			releaseLocks();
		}
	}

	/**
	 * Performs API (delete mode) on the current data
	 * 
	 * @return the highest message severity
	 * 
	 * @throws EQException
	 * 
	 */
	public int applyTransactionDelete() throws EQException
	{
		try
		{
			FunctionAPI functionAPI = new FunctionAPI(fhd);
			functionAPI.setCommitProcessing(fhd.getFunctionInfo().isCommitProcessing());
			functionAPI.setEfcProcessing(!EquationCommonContext.isLinkedSession(fhd.getFunctionInfo().getSessionType()));
			functionAPI.setUpdateMode(false);
			int msgSev = functionAPI.applyTransaction();
			fhd.getFunctionMsgManager().getFunctionMessages().insertMessages(functionAPI.getFunctionMessages());
			return msgSev;
		}
		catch (EQException e)
		{
			throw e;
		}
		finally
		{
			releaseLocks();
		}
	}

	/**
	 * Performs validation on the current data
	 * 
	 * @return the highest message severity
	 * 
	 * @throws EQException
	 * 
	 */
	public int applyValidateTransaction() throws EQException
	{
		try
		{
			int msgSev;

			// load field set status
			LoadFieldSetStatus status = FunctionRuntimeToolbox.getPrimaryFieldSetStatus(fhd);

			// validate everything if detail is open
			FunctionAPI functionAPI = new FunctionAPI(fhd);
			functionAPI.setCommitProcessing(fhd.getFunctionInfo().isCommitProcessing());
			if (status.isDetailOpen())
			{
				msgSev = functionAPI.validate();
				fhd.getFunctionMsgManager().getFunctionMessages().insertMessages(functionAPI.getFunctionMessages());
			}
			else
			{
				msgSev = functionAPI.validate(0, 0);
				fhd.getFunctionMsgManager().getFunctionMessages().insertMessages(functionAPI.getFunctionMessages());
			}

			return msgSev;
		}
		catch (EQException e)
		{
			throw e;
		}
		finally
		{
			releaseLocks();
		}
	}

	/**
	 * Performs retrieval on the current data
	 * 
	 * @return the highest message severity
	 * 
	 * @throws EQException
	 * 
	 */
	public int applyRetrieveTransaction() throws EQException
	{
		try
		{
			// load field set status
			LoadFieldSetStatus status = FunctionRuntimeToolbox.getPrimaryFieldSetStatus(fhd);

			// key actually exists?
			if (!status.isKeyExist())
			{
				return FunctionMessages.MSG_NONE;
			}

			// key already loaded, then unload it
			if (status.isDetailOpen())
			{
				unloadKeyFieldSet(status.getId(), "");
			}

			// validate
			FunctionAPI functionAPI = new FunctionAPI(fhd);
			functionAPI.setCommitProcessing(fhd.getFunctionInfo().isCommitProcessing());
			int msgSev = functionAPI.validate(0, 0);
			if (msgSev == FunctionMessages.MSG_ERROR)
			{
				fhd.getFunctionMsgManager().getFunctionMessages().insertMessages(functionAPI.getFunctionMessages());
				return msgSev;
			}

			// retrieve
			return loadKeyFieldSet(status.getId(), "", false, status.isKeyExist());
		}
		catch (EQException e)
		{
			throw e;
		}
		finally
		{
			releaseLocks();
		}
	}

	/**
	 * Performs retrieval on the current data
	 * 
	 * @return the highest message severity
	 * 
	 * @throws EQException
	 * 
	 */
	public int applyUnloadTransaction() throws EQException
	{
		try
		{
			// load field set status
			LoadFieldSetStatus status = FunctionRuntimeToolbox.getPrimaryFieldSetStatus(fhd);

			// key already loaded, then unload it
			if (status.isDetailOpen())
			{
				unloadKeyFieldSet(status.getId(), "");
			}

			return FunctionMessages.MSG_NONE;
		}
		catch (EQException e)
		{
			throw e;
		}
		finally
		{
			releaseLocks();
		}
	}
	/**
	 * Performs API on the current data for use from within Service User Exit
	 * 
	 * @return function messages
	 * 
	 * @throws EQException
	 * 
	 */
	public FunctionMessages applyTransaction(EquationDataStructureData journalData, String fct) throws Exception
	{
		return applyTransaction(journalData, fct, true, null);

	}
	/**
	 * Performs API on the current data
	 * 
	 * @param journalData
	 *            - the journal data
	 * @param fct
	 *            - the function mode
	 * @param insideBankFusionMicroflow
	 *            - true if this method is already invoked from within a Microflow
	 * @param extensionData
	 *            - the extension data
	 * 
	 * @return function messages
	 * 
	 * @throws EQException
	 * 
	 */
	public FunctionMessages applyTransaction(EquationDataStructureData journalData, String fct, boolean insideBankFusionMicroflow,
					ExtensionData extensionData) throws Exception
	{
		ScreenSet screenSetMain = null;
		String backupFct = null;
		try
		{
			// conversion rules
			ConversionRules conversionRules = FunctionRuntimeToolbox.getConversionRules(null, fhd);

			// set the service mode
			backupFct = fhd.getServiceMode();
			fhd.setServiceMode(fct);

			// processing when Load API exists
			screenSetMain = fhd.getScreenSetHandler().rtvScreenSetMain();

			// bankfusion installed?
			boolean bankFusionIsInstalled = EquationCommonContext.isBankFusionInstalled();

			// When key field exists, then validate the key fields and try to load the details
			if (screenSetMain.isKeyFieldExists())
			{
				FunctionData functionData = screenSetMain.getFunctionData();
				functionData.loadFieldDataFromDS(journalData, false);
				functionData.setInputExtensionData(extensionData);

				if (bankFusionIsInstalled && !insideBankFusionMicroflow)
				{
					FunctionBankFusion functionBankFusion = new FunctionBankFusion();
					functionBankFusion.callDummyAPIMicroflow("R", fct, screenSetMain, functionData, conversionRules);
				}
				else
				{
					// are there any Load APIs
					applyLoadAPI(this);
				}

				// any error message during key field validation
				if (fhd.getFunctionMsgManager().getFunctionMessages().getMsgSev() >= FunctionMessages.MSG_ERROR)
				{
					return fhd.getFunctionMsgManager().getFunctionMessages();
				}
			}

			// enquiry?
			if (fhd.getSecurityLevel().chkNoUpdate() || fct.equals(IEquationStandardObject.TYPE_ENQUIRY))
			{
				return fhd.getFunctionMsgManager().getFunctionMessages();
			}

			// setup the function data
			FunctionData functionData = screenSetMain.getFunctionData();
			functionData.loadFieldDataFromDS(journalData, false);

			// apply the transaction
			if (bankFusionIsInstalled && !insideBankFusionMicroflow)
			{
				FunctionBankFusion functionBankFusion = new FunctionBankFusion();
				return functionBankFusion.callDummyAPIMicroflow("A", fct, screenSetMain, functionData, conversionRules);
			}
			else
			{
				// apply the transaction
				FunctionAPI functionAPI = new FunctionAPI(fhd, !fct.equals("D"), fhd.getFunctionInfo().isCommitProcessing(), false);
				functionAPI.applyTransaction(functionData);
				fhd.getFunctionMsgManager().getFunctionMessages().insertMessages(functionAPI.getFunctionMessages());
				return functionAPI.getFunctionMessages();
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			try
			{
				fhd.getFunctionMsgManager().generateMessage(fhd.getEquationUser().getSession(),
								fhd.getFunctionMsgManager().getFunctionMessages(), screenSetMain.getId(), 0, "", 0, null,
								"KSM7340", "", Toolbox.getExceptionMessage(e), FunctionMessages.MSG_NONE);
				return fhd.getFunctionMsgManager().getFunctionMessages();
			}
			catch (EQException e1)
			{
				throw e;
			}
		}
		finally
		{
			releaseLocks();
			fhd.setServiceMode(backupFct);
		}
	}

	/**
	 * Performs API given the journal key - External Input
	 * 
	 * @param workStation
	 *            - work station
	 * @param jrnDay
	 *            - journal day
	 * @param jrnTime
	 *            - journal time
	 * @param jrnSequence
	 *            - journal sequence
	 * @param image
	 *            - image
	 * @param fct
	 *            - function mode
	 * @param library
	 *            - library where the journal is located
	 * @param externalInput
	 *            - write journal - this determines whether recovery (false) or external input (true)
	 * 
	 * @return the journal print entries
	 */
	public String applyTransactionAsString(String optionId, String workStation, int jrnDay, int jrnTime, int jrnSequence,
					String image, String fct, String library, boolean externalInput)
	{
		ScreenSet screenSetMain = null;
		try
		{
			if (externalInput)
			{
				fhd.getFunctionInfo().setSessionType(EquationCommonContext.SESSION_EXT_INPUT);
			}
			else
			{
				fhd.getFunctionInfo().setSessionType(EquationCommonContext.SESSION_RECOVERY);
			}

			// initialise option
			initTransaction(optionId, "", FunctionRuntimeToolbox.INIT_SAVE, ScreenSetHandler.SCREENSET_DEFAULT);

			// retrieve the main screen set
			screenSetMain = fhd.getScreenSetHandler().rtvScreenSetMain();

			// Get the linkage id (if this is a linkage service)
			String linkageServiceId = FunctionRuntimeToolbox.getLinkageServiceId(screenSetMain);

			// retrieve the journal record data
			JournalRecord journalRecord = FunctionRuntimeToolbox.initialiseJournalRecord(screenSetMain.getFunction(), workStation,
							jrnDay, jrnTime, jrnSequence, image, fct, library, linkageServiceId);
			boolean exists = journalRecord.rtvRecord(fhd.getEquationUser().getSession());

			// journal record does not exist, do not apply anymore
			if (!exists)
			{
				fhd.getFunctionMsgManager().generateMessage(getFhd().getEquationUser().getSession(), 0, 0, "", 0, null,
								"KSM2056" + journalRecord.getEqFileName(), "", "", FunctionMessages.MSG_NONE);
			}
			else
			{
				ExtensionData extensionData = new ExtensionData();
				extensionData.loadUserExtensionData(fhd.getEquationUser().getSessionForNonUpdate(), workStation, jrnDay, jrnTime,
								jrnSequence, fct, library);
				applyTransaction(journalRecord.getJrnData(), fct, false, extensionData);
			}

			return FunctionRuntimeToolbox.rtvAsString(getFhd().getFunctionMsgManager().getFunctionMessages());

		}
		catch (Exception e)
		{
			LOG.error(e);
			return "20KSM7340" + Toolbox.pad(e.getMessage(), 30);
		}
		finally
		{
			releaseLocks();
		}
	}

	/**
	 * Performs API given the journal key - Online Input
	 * 
	 * @param optionId
	 *            - the option id
	 * @param gzData
	 *            - the journal data
	 * @param image
	 *            - journal image
	 * @param fct
	 *            - function mode
	 * 
	 * @return the journal print entries
	 */
	public String applyTransactionAsString(String optionId, String gzData, String image, String fct)
	{
		try
		{
			// initialise function handler
			getFhd().getFunctionInfo().setSessionType(EquationCommonContext.SESSION_API_MODE);

			// initialise option
			initTransaction(optionId, "", FunctionRuntimeToolbox.INIT_SAVE, ScreenSetHandler.SCREENSET_DEFAULT);

			// convert to bytes
			byte[] gzDataBytes = Toolbox.cvtHexStringToBytes(gzData);

			// determiner the function id. For link services, this revert back to the option id
			ScreenSet screenSetMain = getFhd().getScreenSetHandler().rtvScreenSetMain();
			String functionId = screenSetMain.getFunctionId();
			if (screenSetMain.isLinkService())
			{
				functionId = optionId;
			}

			// Create the data structure data
			EquationDataStructure eqDs = new EquationDataStructure(JournalFile.getJournalName(functionId), fhd.getEquationUser()
							.getSession());
			EquationDataStructureData eqDsDta = new EquationDataStructureData(eqDs);

			// Set the bytes
			eqDsDta.setBytes(gzDataBytes);

			applyTransaction(eqDsDta, fct, false, null);

			return FunctionRuntimeToolbox.rtvAsString(getFhd().getFunctionMsgManager().getFunctionMessages());
		}
		catch (Exception e)
		{
			LOG.error(e);
			return "20KSM7340" + Toolbox.pad(e.getMessage(), 30);
		}
		finally
		{
			releaseLocks();
		}
	}

	/**
	 * Perform retrieval if exists
	 * 
	 * @return the highest message severity
	 */
	public int applyLoadAPI(FunctionHandler fh) throws EQException
	{
		// validate
		int msgSev = fh.validate(0);
		if (msgSev == FunctionMessages.MSG_ERROR)
		{
			return msgSev;
		}

		// retrieve
		ScreenSet screenSet = fh.getFhd().getScreenSetHandler().rtvScreenSetMain();
		String inputFieldSetId = screenSet.getMainInputFieldSet().getId();
		LoadFieldSetStatusHandler loadFieldSetStatusHandler = screenSet.getLoadFieldSetStatusHandler();
		LoadFieldSetStatus status = loadFieldSetStatusHandler.getFieldSetStatus(inputFieldSetId);
		return fh.loadKeyFieldSet(inputFieldSetId, "", false, status.isKeyExist());
	}

	// -------------------------------------------------------------- API INTERFACE: END

	// -------------------------------------------------------------- FUNCTION SESSION INTERFACE: BEGIN

	/**
	 * Save the transaction to the database
	 * 
	 * @param status
	 *            - session status (WERecordDataModel.STAT_WIP or WERecordDataModel.STAT_TMPLT)
	 * @param tranId
	 *            - the transaction Id
	 * 
	 * @throws EQException
	 * 
	 */
	public void save(String status, String tranId) throws EQException
	{
		// equation session from the pool
		EquationStandardSession sessionFromPool = null;

		// always in uppercase
		tranId = tranId.toUpperCase();

		try
		{
			sessionFromPool = FunctionRuntimeToolbox.rtvEquationSessionFromPool(fhd.getEquationUser().getEquationUnit());
			ISessionRecordDao daoSession = fhd.getDaoFactory().getSessionDao(sessionFromPool,
							new SessionRecordDataModel("", "", "", ""));
			boolean found = false;

			// validate transaction id contain valid characters
			if (!FunctionRuntimeToolbox.validateTransactionIdSyntax(tranId))
			{
				ScreenSet screenSet = fhd.getScreenSetHandler().rtvScrnSetCurrent();
				FunctionMessages fms = new FunctionMessages();
				fhd.getFunctionMsgManager().generateMessage(rtvEquationSession(), fms, screenSet.getId(), screenSet.getScrnNo(),
								"", 0, null, "KSM7379", "", "", FunctionMessages.MSG_NONE);
				fhd.getFunctionMsgManager().getOtherMessages().insertMessages(fms);
				return;
			}

			// ensure transaction id is unique, unless re-updating the same transaction
			if (!(tranId.trim().equals("")) && !tranId.equals(fhd.getFunctionSession().getTransactionId()))
			{
				found = daoSession.checkIfThisSessionIsInTheDBBaseOnTransactionId(fhd.getEquationUser().getUserId(), tranId);
				if (!found)
				{
					fhd.getFunctionSession().setTransactionId(tranId);
				}
			}

			// transaction id already exists
			if (found)
			{
				ScreenSet screenSet = fhd.getScreenSetHandler().rtvScrnSetCurrent();
				FunctionMessages fms = new FunctionMessages();
				fhd.getFunctionMsgManager().generateMessage(rtvEquationSession(), fms, screenSet.getId(), screenSet.getScrnNo(),
								"", 0, null, "KSM7347", "", "", FunctionMessages.MSG_NONE);
				fhd.getFunctionMsgManager().getOtherMessages().insertMessages(fms);
			}

			// unique transaction id, then save it
			else
			{
				fhd.getFunctionSession()
								.save(sessionFromPool, true, fhd.getScreenSetHandler(), fhd.getFunctionMsgManager(), status);
				fhd.getFunctionMsgManager().clearMessages();
				processReturnedMessages(fhd.getFunctionMsgManager().getFunctionMessages());
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			throw new EQException("FunctionHandler: save() Failed: " + Toolbox.getExceptionMessage(e), e);
		}
		finally
		{
			FunctionRuntimeToolbox.returnEquationSessionToPool(fhd.getEquationUser().getEquationUnit(), sessionFromPool);
		}
	}

	/**
	 * Refer the transaction to another user
	 * 
	 * @param toUserId
	 *            - user to refer the transaction to
	 * @param tranId
	 *            - transaction id
	 * 
	 * @throws EQException
	 * 
	 */
	public void referTo(String userId, String tranId) throws EQException
	{
		// equation session from the pool
		EquationStandardSession sessionFromPool = null;

		try
		{
			// setup details
			sessionFromPool = FunctionRuntimeToolbox.rtvEquationSessionFromPool(fhd.getEquationUser().getEquationUnit());
			FunctionSession fs = new FunctionSession(fhd.getOptionId(), fhd.getFunctionInfo().getSessionId(), userId);
			boolean found = false;

			// validate transaction id contain valid characters
			if (!FunctionRuntimeToolbox.validateTransactionIdSyntax(tranId))
			{
				FunctionMessages fms = new FunctionMessages();
				fhd.getFunctionMsgManager().generateMessage(rtvEquationSession(), fms, 0, 0, "", 0, null, "KSM7379", "", "",
								FunctionMessages.MSG_NONE);
				fhd.getFunctionMsgManager().getOtherMessages().insertMessages(fms);
				return;
			}

			// validate only if non-blank and check the transaction id if unique for the user
			if (!(tranId.trim().equals("")))
			{
				ISessionRecordDao daoSession = fhd.getDaoFactory().getSessionDao(sessionFromPool,
								new SessionRecordDataModel("", "", "", ""));
				found = daoSession.checkIfThisSessionIsInTheDBBaseOnTransactionId(userId, tranId);
				if (!found)
				{
					fs.setTransactionId(tranId);
				}
			}

			// transaction id already exists
			if (found)
			{
				FunctionMessages fms = new FunctionMessages();
				fhd.getFunctionMsgManager().generateMessage(rtvEquationSession(), fms, 0, 0, "", 0, null, "KSM7347", "", "",
								FunctionMessages.MSG_NONE);
				fhd.getFunctionMsgManager().getOtherMessages().insertMessages(fms);
			}

			// unique transaction id, then save it
			else
			{
				fs.save(sessionFromPool, true, fhd.getScreenSetHandler(), fhd.getFunctionMsgManager(), "",
								WERecordDataModel.STAT_REFUSR, WERecordDataModel.LVL_ALL, true);
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			throw new EQException("FunctionHandler: referTo() Failed: " + Toolbox.getExceptionMessage(e), e);
		}
		finally
		{
			FunctionRuntimeToolbox.returnEquationSessionToPool(fhd.getEquationUser().getEquationUnit(), sessionFromPool);
		}
	}

	/**
	 * Restore a session from the database
	 * 
	 * @param optionId
	 *            - option id
	 * @param sessionId
	 *            - session id
	 * @param userId
	 *            - user id
	 * @param transactionId
	 *            - transaction id
	 * @param status
	 *            - new status of the record after restoring
	 * @param screenSetType
	 *            - the screen set type
	 * 
	 * @return true - if session successfully restored
	 * 
	 * @throws EQException
	 * 
	 */
	public boolean restore(String optionId, String sessionId, String userId, String transactionId, String status, int screenSetType)
					throws EQException
	{
		EquationStandardSession sessionFromPool = null;
		try
		{
			FunctionSession fs = new FunctionSession(optionId, sessionId, userId, transactionId);
			sessionFromPool = FunctionRuntimeToolbox.rtvEquationSessionFromPool(fhd.getEquationUser().getEquationUnit());
			if (fs.restore(sessionFromPool, true, status, "N"))
			{
				fhd.setFunctionSession(fs);
				initTransaction(optionId, "", FunctionRuntimeToolbox.INIT_SAVE, screenSetType);
				this.fhd.getFunctionMsgManager().getFunctionMessages().insertMessages(fs.getFunctionMessages());
				this.fhd.getFunctionMsgManager().getOverWarnMessages().insertMessages(fs.getWarningMessages());

				// populate the data
				fhd.getScreenSetHandler().setCurScreenSet(ScreenSetHandler.FUNCTION_DATA_SCREEN);
				ScreenSet ss = fhd.getScreenSetHandler().rtvScrnSetCurrent();
				ss.getFunctionData().reSyncFunctionData(fs.getFunctionData());
				ss.getLoadFieldSetStatusHandler().reSyncLoadFieldSetStatus(
								fs.getFunctionData().getFunctionControlData().getLoadFieldSetStatuses());
				ss.getDisplayGroupHandler().reSyncGroupStatus(fs.getFunctionData().getFunctionControlData().getCurrentDisplay());
				ss.getShutterHandler().reSyncLoadShutterStatus(fs.getFunctionData().getFunctionControlData().getShutters());
				ss.getRepeatingGroupStatusHandler().reSyncRepeatingGroupStatus(
								fs.getFunctionData().getFunctionControlData().getRepeatingGroupStatuses());
				ss.getFunctionMessages().insertMessages(fs.getFunctionMessages());

				// load the before images
				if (fs.getBeforeImage() != null)
				{
					FunctionTransactions functionTransaction = new FunctionTransactions(fhd, "");
					functionTransaction.addTransaction(fs.loadBeforeImages(sessionFromPool, fhd.getScreenSetHandler()
									.rtvScreenSetMain()));
					ss.setBeforeImagesTransactions(functionTransaction);
					// ss.setFunctionDataBefore(fs.getBeforeImage());
					ss.setFunctionDataBefore(new FunctionData(ss.getFunction(), fhd));
					ss.getFunctionDataBefore().reSyncFunctionData(fs.getBeforeImage());
					ss.setMode(fs.getLoadMode());
					ss.getFunctionData().chgFieldDbValue(FunctionData.FLD_FCT, ss.rtvMode());
					fhd.getSecurityLevel().setEnquireMode(ss.getMode().equals(IEquationStandardObject.FCT_ENQ));
				}

				// Refer to FunctionSession save() transaction on how data are being saved to the database

				// populate the next data if has already been displayed (CRM)
				int lastScreenSetId = fs.getLastScrnSetId();
				if (lastScreenSetId >= ScreenSetHandler.FUNCTION_CRM_SCREEN && fs.getFunctionCRMData() != null)
				{
					fhd.getScreenSetHandler().setCurScreenSet(ScreenSetHandler.FUNCTION_CRM_SCREEN);
					ss = fhd.getScreenSetHandler().rtvScrnSetCurrent();
					ss.setFunctionData(fs.getFunctionCRMData());
					ss.getLoadFieldSetStatusHandler().reSyncLoadFieldSetStatus(
									fs.getFunctionCRMData().getFunctionControlData().getLoadFieldSetStatuses());
					ss.getDisplayGroupHandler().reSyncGroupStatus(
									fs.getFunctionCRMData().getFunctionControlData().getCurrentDisplay());
					ss.getShutterHandler().reSyncLoadShutterStatus(fs.getFunctionCRMData().getFunctionControlData().getShutters());
				}

				// populate the next data if has already been displayed (EFC)
				if (lastScreenSetId >= ScreenSetHandler.FUNCTION_EFC_SCREEN_1 && fs.getFunctionEFCData() != null)
				{
					fhd.getScreenSetHandler().setCurScreenSet(ScreenSetHandler.FUNCTION_EFC_SCREEN_1);
					ss = fhd.getScreenSetHandler().rtvScrnSetCurrent();
					ss.setFunctionData(fs.getFunctionEFCData());
					ss.getLoadFieldSetStatusHandler().reSyncLoadFieldSetStatus(
									fs.getFunctionEFCData().getFunctionControlData().getLoadFieldSetStatuses());
					((ScreenSetAC2) ss).setupChargeKeyBasedOnHZ();
					ss.getDisplayGroupHandler().reSyncGroupStatus(
									fs.getFunctionEFCData().getFunctionControlData().getCurrentDisplay());
					ss.getShutterHandler().reSyncLoadShutterStatus(fs.getFunctionEFCData().getFunctionControlData().getShutters());
				}

				// populate the next data if has already been displayed (EFC 2)
				if (fs.getScreenSetId() >= ScreenSetHandler.FUNCTION_EFC_SCREEN_2 && fs.getFunctionEFCData() != null
								&& fs.getFunctionEFC2Data() != null)
				{
					ScreenSetAC3 ssAC3 = new ScreenSetAC3(ScreenSetHandler.FUNCTION_EFC_SCREEN_2, fhd);
					ScreenSetAC2 ssAC2 = (ScreenSetAC2) fhd.getScreenSetHandler().rtvScreenSet(
									ScreenSetHandler.FUNCTION_EFC_SCREEN_1);
					ssAC3.inData(ssAC2.getFunctionData(), ssAC2.rtvCurIndex());
					ssAC3.setFunctionData(fs.getFunctionEFC2Data());
					fhd.getScreenSetHandler().addScreenSet(ssAC3);
					fhd.getScreenSetHandler().setCurScreenSet(ScreenSetHandler.FUNCTION_EFC_SCREEN_2);
					ss.getDisplayGroupHandler().reSyncGroupStatus(
									fs.getFunctionEFC2Data().getFunctionControlData().getCurrentDisplay());
					ss.getShutterHandler().reSyncLoadShutterStatus(fs.getFunctionEFC2Data().getFunctionControlData().getShutters());
				}

				// special processing if EFCData is blank, but EFC2Data is populated, then it means EFC2Data is a RepeatingField
				if (lastScreenSetId >= ScreenSetHandler.FUNCTION_CRM_SCREEN && fs.getFunctionEFCData() == null
								&& fs.getFunctionEFC2Data() != null)
				{
					FunctionControlData functionControlData = fs.getFunctionEFC2Data().getFunctionControlData();
					String repeatingGroup = functionControlData.getFieldValue(FunctionControlData.REPEATING_GROUP);
					String operation = functionControlData.getFieldValue(FunctionControlData.REPEATING_GROUP_OPER);
					int rowIndex = Toolbox.parseInt(functionControlData.getFieldValue(FunctionControlData.REPEATING_GROUP_ROW), 0);
					ScreenSetRepeatFields ssRepeatingField = new ScreenSetRepeatFields(0, fhd, 0, optionId, repeatingGroup,
									operation);
					ScreenSet screenSet = fhd.getScreenSetHandler().rtvScreenSet(0);
					ssRepeatingField.inData(screenSet.getFunctionData(), rowIndex);
					ssRepeatingField.getFunctionData().reSyncFunctionData(fs.getFunctionEFC2Data());
					fhd.getScreenSetHandler().addScreenSet(ssRepeatingField);
					fhd.getScreenSetHandler().setCurScreenSet(lastScreenSetId);
					ss.getDisplayGroupHandler().reSyncGroupStatus(
									fs.getFunctionEFC2Data().getFunctionControlData().getCurrentDisplay());
					ss.getShutterHandler().reSyncLoadShutterStatus(fs.getFunctionEFC2Data().getFunctionControlData().getShutters());
				}

				// send the function messages to the specific screen set
				fhd.getScreenSetHandler().rtvScreenSet(fs.getScreenSetId()).getFunctionMessages().clearMessages();
				fhd.getScreenSetHandler().rtvScreenSet(fs.getScreenSetId()).getFunctionMessages().insertMessages(
								fs.getFunctionMessages());

				// mark all of this as displayed
				fhd.getScreenSetHandler().markAsDisplayed(lastScreenSetId);
				fhd.getScreenSetHandler().setLastScreenSetViewed(lastScreenSetId);

				// set the screen of this screenset
				fhd.getScreenSetHandler().setCurScreenSet(fs.getLastScrnSetId());
				fhd.getScreenSetHandler().rtvScrnSetCurrent().setScrnNo(fs.getScrnNo() - 1);

				// Is this related to an LRP task and user is the one who created it - this means this
				// WE is linked to a task, so do task processing as well so task will also get updated
				if (EquationCommonContext.getContext().isLRPProcessing()
								&& fs.getUserId().trim().equalsIgnoreCase(fhd.getEquationUser().getUserId()))
				{
					return processLRPTaskFromWE();
				}

				return true;
			}
			else
			{
				return false;
			}
		}
		finally
		{
			FunctionRuntimeToolbox.returnEquationSessionToPool(fhd.getEquationUser().getEquationUnit(), sessionFromPool);
		}
	}
	/**
	 * Delete a session from the database
	 * 
	 * @param optionId
	 *            - option id
	 * @param sessionId
	 *            - session id
	 * @param userId
	 *            - user id
	 * @param transactionId
	 *            - transaction id
	 * 
	 * @throws EQException
	 */
	public void delete(String optionId, String sessionId, String userId, String transactionId) throws EQException
	{
		EquationStandardSession sessionFromPool = null;
		try
		{
			FunctionSession fs = new FunctionSession(optionId, sessionId, userId, transactionId);
			sessionFromPool = FunctionRuntimeToolbox.rtvEquationSessionFromPool(fhd.getEquationUser().getEquationUnit());
			fs.delete(sessionFromPool, true);
		}
		finally
		{
			FunctionRuntimeToolbox.returnEquationSessionToPool(fhd.getEquationUser().getEquationUnit(), sessionFromPool);
		}
	}
	// -------------------------------------------------------------- FUNCTION SESSION INTERFACE: END

	/**
	 * Release any locks
	 */
	private void releaseLocks()
	{
		try
		{
			// fhd.getEqUser().getSession().rollbackTransactions();
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
	}

	/**
	 * Update the function data with the list of field values
	 * 
	 * @param fieldValues
	 *            - the list of field values separated by the delimeters
	 * @param fieldValueDelim
	 *            - separator between the field name and field value
	 * @param fieldDelim
	 *            - separator between fields
	 */
	public void updateFields(String fieldValues, String fieldValueDelim, String fieldDelim)
	{
		ScreenSet screenSet = fhd.getScreenSetHandler().rtvScreenSetMain();
		FunctionData functionData = screenSet.getFunctionData();
		boolean changed = functionData.updateFields(fieldValues, fieldValueDelim, fieldDelim);
		fhd.getContextHandler().saveFunctionToContextData(screenSet.getFunction(), functionData);

		// any details changed, then dirty the screen
		if (changed)
		{
			dirtyScreen(screenSet.getScrnNo());
		}
	}

	/**
	 * Call previous screen user exit
	 * 
	 * @param screenSet
	 *            - the current screen set
	 * 
	 * @return the previous screen to display
	 * 
	 * @throws EQException
	 */
	private int prevScrnUserExit(ScreenSet screenSet) throws EQException
	{
		int prevScrn = -1;

		// JAVA user exit
		try
		{
			screenSet.getFunctionMessages().clearMessages();
			prevScrn = screenSet.getLayoutAdaptor().prevScreen(screenSet.getScrnNo());
		}
		catch (Exception e)
		{
			screenSet.getFhd().getFunctionMsgManager().generateMessage(
							screenSet.getFhd().getEquationUser().getSessionForNonUpdate(), screenSet.getFunctionMessages(),
							screenSet.getId(), screenSet.getScrnNo(), "", 0, null, "KSM7384", "", Toolbox.getExceptionMessage(e),
							FunctionMessages.MSG_NONE);
			LOG.error(e);
		}

		// Bankfusion user exit
		try
		{
			if (screenSet.getLayout().isPrevScrnMicroflow() && !screenSet.getFunction().chkXSDGeneric())
			{
				FunctionBankFusion functionBankFusion = new FunctionBankFusion();
				int prevScrn2 = functionBankFusion.callPrevScrnUserExit(screenSet, screenSet.getScrnNo());
				if (prevScrn2 != -1)
				{
					prevScrn = prevScrn2;
				}
			}
		}
		catch (Exception e)
		{
			screenSet.getFhd().getFunctionMsgManager().generateMessage(
							screenSet.getFhd().getEquationUser().getSessionForNonUpdate(), screenSet.getFunctionMessages(),
							screenSet.getId(), screenSet.getScrnNo(), "", 0, null, "KSM7384", "", Toolbox.getExceptionMessage(e),
							FunctionMessages.MSG_NONE);
			LOG.error(e);
		}

		// process any messages
		processReturnedMessages(screenSet.getFunctionMessages());

		// ensure valid previous screen
		if (prevScrn < 0 || prevScrn >= screenSet.getScrnNo())
		{
			prevScrn = -1;
		}

		// return the next screen
		return prevScrn;
	}

	/**
	 * Call next screen user exit
	 * 
	 * @param screenSet
	 *            - the current screen set
	 * 
	 * @return the next screen to display
	 * 
	 * @throws EQException
	 */
	private int nextScrnUserExit(ScreenSet screenSet) throws EQException
	{
		int nextScrn = -1;

		// JAVA user exit
		try
		{
			screenSet.getFunctionMessages().clearMessages();
			nextScrn = screenSet.getLayoutAdaptor().nextScreen(screenSet.getScrnNo());
		}
		catch (Exception e)
		{
			screenSet.getFhd().getFunctionMsgManager().generateMessage(
							screenSet.getFhd().getEquationUser().getSessionForNonUpdate(), screenSet.getFunctionMessages(),
							screenSet.getId(), screenSet.getScrnNo(), "", 0, null, "KSM7385", "", Toolbox.getExceptionMessage(e),
							FunctionMessages.MSG_NONE);
			LOG.error(e);
		}

		// Bankfusion user exit
		try
		{
			if (screenSet.getLayout().isNextScrnMicroflow() && !screenSet.getFunction().chkXSDGeneric())
			{
				FunctionBankFusion functionBankFusion = new FunctionBankFusion();
				int nextScrn2 = functionBankFusion.callNextScrnUserExit(screenSet, screenSet.getScrnNo());
				if (nextScrn2 != -1)
				{
					nextScrn = nextScrn2;
				}
			}
		}
		catch (Exception e)
		{
			screenSet.getFhd().getFunctionMsgManager().generateMessage(
							screenSet.getFhd().getEquationUser().getSessionForNonUpdate(), screenSet.getFunctionMessages(),
							screenSet.getId(), screenSet.getScrnNo(), "", 0, null, "KSM7385", "", Toolbox.getExceptionMessage(e),
							FunctionMessages.MSG_NONE);
			LOG.error(e);
		}

		// process any messages
		processReturnedMessages(screenSet.getFunctionMessages());

		// ensure valid next screen
		if (nextScrn <= screenSet.getScrnNo() || nextScrn >= screenSet.getMaxScrnNo())
		{
			nextScrn = -1;
		}

		// return the next screen
		return nextScrn;
	}

	/**
	 * Remove this drill down child processing
	 */
	public void removeDrillDownChildProcessing()
	{
		// remove the child
		FunctionHandler fh = fhd.getParentFunctionHandler().getFhd().removeDrillDownChild();
		fh.getFhd().getFunctionMsgManager().clearAllMessages();

		// retrieve parent information
		FunctionHandler parentFh = fh.getFhd().getParentFunctionHandler();
		parentFh.getFhd().getFunctionMsgManager().clearAllMessages();
		parentFh.getFhd().setDrillDownUpdateMade(fh.getFhd().isUpdateMade() || parentFh.getFhd().isDrillDownUpdateMade());

		// perform drill up processing on the parent to determine if it needs to process further selected records
		boolean selectionMade = parentFh.drillUpProcessing();

		// selection made, then copy the messages
		if (selectionMade)
		{
			if (parentFh.getFhd().getChildFunctionHandler() == null)
			{
				// from the parent
				getFhd().getFunctionMsgManager().getOtherMessages().insertMessages(
								parentFh.getFhd().getFunctionMsgManager().getOtherMessages());
				getFhd().getFunctionMsgManager().getFunctionMessages().insertMessages(
								parentFh.getFhd().getFunctionMsgManager().getFunctionMessages());
			}
			else
			{
				// from the new child
				getFhd().getFunctionMsgManager().getOtherMessages().insertMessages(
								parentFh.getFhd().getChildFunctionHandler().getFhd().getFunctionMsgManager().getOtherMessages());
				getFhd().getFunctionMsgManager().getFunctionMessages().insertMessages(
								parentFh.getFhd().getChildFunctionHandler().getFhd().getFunctionMsgManager().getFunctionMessages());
			}
		}
	}

	/**
	 * Refresh a function handler if it is an enquiry
	 * 
	 * @return true if function has been refreshed
	 */
	public boolean refreshEnquiry()
	{
		LoadFieldSetStatus status = FunctionRuntimeToolbox.getPrimaryFieldSetStatus(fhd);
		boolean key = status.isKeyExist();
		try
		{
			ScreenSet screenSetMain = getFhd().getScreenSetHandler().rtvScreenSetMain();
			if (screenSetMain.chkEnquiry())
			{
				status.setKeyExist(true);
				unloadKeyFieldSet(status.getId(), "");
				loadKeyFieldSet(status.getId(), "", false, key);
				return true;
			}
			return false;
		}
		catch (Exception e)
		{
			// TODO: what to do if there are error in refreshing????
			LOG.error(e);
			return false;
		}
		finally
		{
			status.setKeyExist(key);
		}
	}

	/**
	 * Remove drill down child processing
	 * 
	 * @param repeatingGroupId
	 *            - the repeating group id in processed
	 * 
	 * @return true if processing were made in the drill up processing
	 */
	protected boolean drillUpProcessing()
	{
		try
		{
			boolean selectionMade;

			// is it processing only an specific repeating group?
			if (fhd.getDrillDownRepeatingGroup().equals(EqDataType.GLOBAL_DELIMETER))
			{
				selectionMade = false;
			}
			else if (fhd.getDrillDownRepeatingGroup().length() > 0)
			{
				selectionMade = fhd.getScreenSetHandler().rtvScrnSetCurrent().processSelectionOption(
								fhd.getDrillDownRepeatingGroup());
			}
			else
			{
				selectionMade = fhd.getScreenSetHandler().rtvScrnSetCurrent().processSelectionOption();
			}

			// refresh if drill down function has made any update
			// TODO: do we blindly refresh as long as it is an update?!?!?
			if (!selectionMade && fhd.isDrillDownUpdateMade())
			{
				fhd.setDrillDownUpdateMade(false);
				refreshEnquiry();
			}

			// selection made?
			return selectionMade;
		}
		catch (Exception e)
		{
			LOG.error(e);
			return false;
		}
	}

	/**
	 * Perform on cancel user exit
	 * 
	 * @parm ckey - function key pressed
	 * @return the highest message severity
	 * 
	 * @throws EQException
	 */
	private int onCancelUserExit(int ckey) throws EQException
	{
		// messages
		FunctionMessages fm = new FunctionMessages();

		// JAVA user exit
		ScreenSet screenSetMain = fhd.getScreenSetHandler().rtvScrnSetCurrent();
		try
		{
			FunctionAdaptor functionAdaptor = screenSetMain.getFunctionAdaptor();
			functionAdaptor.onCancelUserExit();
			if (functionAdaptor.getFunctionAdaptorImpl() != null)
			{
				fhd.getFunctionMsgManager().generateMessages(fhd.getEquationUser().getSession(), fm, screenSetMain.getId(),
								screenSetMain.getScrnNo(), "", 0, null,
								functionAdaptor.getFunctionAdaptorImpl().getReturnMessages().getReturnMessages(), "",
								LanguageResources.getString("FunctionHandler.ReturnedMessageFromUserExitOnCancelMode"),
								screenSetMain.getFunctionData(), FunctionMessages.MSG_NONE);
			}
		}
		catch (Exception e)
		{
			fhd.getFunctionMsgManager().generateMessage(fhd.getEquationUser().getSession(), fm, screenSetMain.getId(),
							screenSetMain.getScrnNo(), "", 0, null, "KSM7377", "", Toolbox.getExceptionMessage(e),
							FunctionMessages.MSG_NONE);
		}

		// process
		fhd.setMessageOnCancelUserExit(fm.getMsgSev() != FunctionMessages.MSG_NONE);
		fhd.setFunctionKeyPressOnCancel(ckey);
		fhd.getFunctionMsgManager().clearMessages();
		processReturnedMessages(fm);

		// return message severity
		return fm.getMsgSev();
	}

	/**
	 * Override function key pressed if needed
	 * 
	 * @param ckey
	 *            - the function key pressed
	 * @return
	 */
	public int overrideCkey(int ckey)
	{
		// if messages have been returned from the onCancel user exit, it should not perform validation when
		// overriding messages
		if (fhd.isMessageOnCancelUserExit())
		{
			if (ckey == FunctionKeys.KEY_OVR || ckey == FunctionKeys.KEY_VERIWARN)
			{
				if (ckey == FunctionKeys.KEY_OVR)
				{
					fhd.getFunctionMsgManager().overrideFirstWarning(SupervisorToolbox.AUTO_SUPERVISOR);
				}
				return fhd.getFunctionKeyPressOnCancel();
			}

			if (fhd.getFunctionMsgManager().getFunctionMessages().getMsgSev() == FunctionMessages.MSG_INFO)
			{
				fhd.getFunctionMsgManager().markDispInfoMsg();
				fhd.getFunctionMsgManager().getFunctionMessages().setMsgSev(FunctionMessages.MSG_NONE);
			}
		}

		fhd.setMessageOnCancelUserExit(false);
		return ckey;
	}

	/**
	 * Checks the availability of units when processing Global Processing enquiries
	 * 
	 * @throws EQException
	 */
	private void checkGPUnitAvailability() throws EQException
	{
		if (!fhd.isLegacyOption() && fhd.getEquationUser().getEquationUnit().isEnhancementInstalled(Enhancement.K541)
						&& fhd.getScreenSetHandler().rtvScreenSetMain().getFunction().isGlobalEnquiry())
		{
			for (String unit : SystemStatusManager.getInstance().getAllUnavailableUnits())
			{
				FunctionMessages messages = fhd.getFunctionMsgManager().getOtherMessages();

				fhd.getFunctionMsgManager().generateMessage(fhd.getEquationUser().getSessionForNonUpdate(), messages, 0, 0, "", 1,
								null, "KSM8010" + unit, "", "", FunctionMessages.MSG_NONE);
			}
		}
	}

	/**
	 * Validates if the reference texts or reusable reference texts are found in HBX
	 * 
	 * @param screenSet
	 * @return false if an error was found, otherwise true
	 */
	public boolean checkTextReferences(ScreenSet screenSet)
	{
		Function function = screenSet.getFunction();
		Layout layout = screenSet.getLayout();

		// Get user language
		String userLanguageId = null;
		if (screenSet.getFhd() != null && screenSet.getFhd().getEquationUser() != null)
		{
			if (screenSet.getFhd().getEquationUser().getLanguageId() != null)
			{
				userLanguageId = screenSet.getFhd().getEquationUser().getLanguageId().trim();
			}
		}

		try
		{
			// Check Function elements
			checkFunctionElements(userLanguageId, function);

			// Check layout elements
			checkLayoutElements(userLanguageId, layout);

		}
		catch (EQException e)
		{
			// If there is an error found, build the message and return false
			FunctionRuntimeToolbox.generateOtherMessages(fhd.getFunctionMsgManager(), fhd.getEquationUser().getSession(), fhd
							.getScreenSetHandler().getCurScreenSet(), fhd.getScreenSetHandler().rtvScrnSetCurrent().getScrnNo(),
							"checkTextReferences", e);

			return false;
		}

		return true;

	}

	/**
	 * Checks text references of function elements
	 * 
	 * @param userLanguageId
	 * @param function
	 * @throws EQException
	 *             if the element reference text is not found
	 */
	private void checkFunctionElements(String userLanguageId, Function function) throws EQException
	{
		List<String> languageList = new ArrayList<String>();

		// Get the user language
		if (userLanguageId != null)
		{
			languageList.add(userLanguageId);
		}

		// Get service base language, if not found, default to GB
		if (function.getBaseLanguage() != null && function.getBaseLanguage().trim().length() > 0)
		{
			languageList.add(function.getBaseLanguage().trim());
		}

		// Use default GB
		if (!languageList.contains(EquationUser.DEF_LANG))
		{
			languageList.add(EquationUser.DEF_LANG);
		}

		// Check the layout description
		checkReferenceText(function.getDescriptionType(), function.getDescriptionTextOwner(), languageList, function
						.getDescription(), TextBean.TYPE_DESCRIPTION);

		// Check the layout label
		checkReferenceText(function.getLabelType(), function.getLabelTextOwner(), languageList, function.getLabel(),
						TextBean.TYPE_LABEL);

		for (int x = 0; x < function.getInputFieldSets().size(); x++)
		{
			InputFieldSet fieldSet = function.getInputFieldSets().get(x);

			// Check the field set description
			checkReferenceText(fieldSet.getDescriptionType(), fieldSet.getDescriptionTextOwner(), languageList, fieldSet
							.getDescription(), TextBean.TYPE_DESCRIPTION);

			// Check input fields
			for (int y = 0; y < fieldSet.getInputFields().size(); y++)
			{
				InputField field = fieldSet.getInputFields().get(y);

				// Check the field description
				checkReferenceText(field.getDescriptionType(), field.getDescriptionTextOwner(), languageList, field
								.getDescription(), TextBean.TYPE_DESCRIPTION);

				// Check the field set reg expression
				checkReferenceText(field.getRegExpType(), field.getRegExpTextOwner(), languageList, field.getRegExp(),
								TextBean.TYPE_REGULAR_EXPRESSION);

				// Check the field set valid values
				checkReferenceText(field.getValidValuesType(), field.getValidValuesTextOwner(), languageList, field
								.getValidValues(), TextBean.TYPE_VALID_VALUE);

			}
			for (int y = 0; y < fieldSet.getInputGroups().size(); y++)
			{
				InputGroup inputGroup = fieldSet.getInputGroups().get(y);
				checkReferenceText(inputGroup.getDescriptionType(), inputGroup.getDescriptionTextOwner(), languageList, inputGroup
								.getDescription(), TextBean.TYPE_DESCRIPTION);

			}
		}

	}
	/**
	 * Checks the reference texts of the layout elements
	 * 
	 * @param userLanguageId
	 * @param layout
	 * @throws EQException
	 *             when reference text is not found
	 */
	private String checkLayoutElements(String userLanguageId, Layout layout) throws EQException
	{
		List<String> languageList = new ArrayList<String>();

		// Get the user language
		if (userLanguageId != null)
		{
			languageList.add(userLanguageId);
		}

		// Get layout base language, if not found, default to GB
		if (layout.getBaseLanguage() != null && layout.getBaseLanguage().trim().length() > 0)
		{
			languageList.add(layout.getBaseLanguage().trim());
		}

		// Use default GB
		if (!languageList.contains(EquationUser.DEF_LANG))
		{
			languageList.add(EquationUser.DEF_LANG);
		}

		// Check the description
		checkReferenceText(layout.getDescriptionType(), layout.getDescriptionTextOwner(), languageList, layout.getDescription(),
						TextBean.TYPE_DESCRIPTION);

		// Check the label
		checkReferenceText(layout.getLabelType(), layout.getLabelTextOwner(), languageList, layout.getLabel(), TextBean.TYPE_LABEL);

		for (int x = 0; x < layout.getDisplayAttributesSets().size(); x++)
		{

			DisplayAttributesSet displaySet = layout.getDisplayAttributesSets().get(x);

			// Check the display attributes sets description
			checkReferenceText(displaySet.getDescriptionType(), displaySet.getDescriptionTextOwner(), languageList, displaySet
							.getDescription(), TextBean.TYPE_DESCRIPTION);

			// Check the display attributes sets label
			checkReferenceText(displaySet.getLabelType(), displaySet.getLabelTextOwner(), languageList, displaySet.getLabel(),
							TextBean.TYPE_LABEL);

			// Check the child item references (including sub children)
			checkChildItemsReferences(displaySet.getDisplayItems(), languageList);

		}

		return null;
	}

	/**
	 * Searches for the reference text in the user / service or layout language / default language
	 * 
	 * @param type
	 * @param owner
	 * @param languageList
	 * @param key
	 * @param keyType
	 * @throws EQException
	 *             when reference text is not found
	 */
	private boolean checkReferenceText(String type, String owner, List<String> languageList, String key, String keyType)
					throws EQException
	{
		// If key is blank, return true
		if (key == null || key.trim().length() == 0)
		{
			return true;
		}

		// If this is a reference text the key can't be <none>, return true
		if (key.trim().equalsIgnoreCase(Element.DEFAULT_TEXT_VALUE) && type.equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
		{
			return true;
		}

		// If type is not a reference text or reusable reference
		if (!(type.equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE) || type.equalsIgnoreCase(Element.TEXT_VALUE_REUSABLE_REFERENCE)))
		{
			return true;
		}

		if ((owner == null || owner.trim().length() == 0) && type.equals(Element.TEXT_VALUE_REUSABLE_REFERENCE))
		{
			owner = TextBean.OWNER_MISYS;
		}

		// Get the hbx records
		EquationRecords records = fhd.getEquationUser().getEquationUnit().getRecords();
		boolean isFound = false;
		for (String language : languageList)
		{
			try
			{
				isFound = records.getHBXRecords().containsKey(owner + language + keyType + key);

				if (!isFound)
				{
					// backward compatibility for text with type LAB but are not used as LAB
					if (type.equals(Element.TEXT_VALUE_REUSABLE_REFERENCE) && !TextBean.TYPE_LABEL.equals(keyType)
									&& TextBean.OWNER_MISYS.equals(owner))
					{
						isFound = records.getHBXRecords().containsKey(owner + language + TextBean.TYPE_LABEL + key);
					}
				}
			}
			catch (Exception exception)
			{
				LOG.error(exception);
				LOG.debug("Error finding reference text: " + key + ":" + language + ":" + owner + ":" + keyType);
				isFound = false;
			}
			if (isFound)
			{
				break;
			}
		}

		if (!isFound)
		{
			String keyTypeDescription = LanguageResources.getString("FunctionHandler.ProblemLoadingReferenceText." + keyType);
			if (keyTypeDescription == null || keyTypeDescription.length() == 0)
			{
				keyTypeDescription = keyType;
			}

			throw new EQException(LanguageResources.getFormattedString("FunctionHandler.ProblemLoadingReferenceText", new String[] {
							key, owner, keyTypeDescription }));
		}

		return isFound;
	}

	/**
	 * Checks the reference text of the layout child elements
	 * 
	 * @param items
	 * @param languageList
	 * @throws EQException
	 *             when reference text is not found
	 */
	private void checkChildItemsReferences(DisplayItemList items, List<String> languageList) throws EQException
	{
		if (items == null)
		{
			return;
		}

		Element element = null;
		for (int index = 0; index < items.size(); index++)
		{
			IDisplayItem item = items.get(index);
			element = (Element) item;

			String descriptionTextOwner = null;
			String labelTextOwner = null;

			// If this is a DisplayGroup, check the child items. Check until the most innermost child
			if (item instanceof DisplayGroup)
			{
				element = (Element) item;

				checkChildItemsReferences(((DisplayGroup) item).getDisplayItems(), languageList);

				descriptionTextOwner = ((DisplayGroup) item).getDescriptionTextOwner();
				labelTextOwner = ((DisplayGroup) item).getLabelTextOwner();

			}
			else if (item instanceof DisplayAttributes)
			{
				descriptionTextOwner = ((DisplayAttributes) item).getDescriptionTextOwner();
				labelTextOwner = ((DisplayAttributes) item).getLabelTextOwner();

			}
			else if (item instanceof DisplayLabel)
			{

				descriptionTextOwner = ((DisplayLabel) item).getDescriptionTextOwner();
				labelTextOwner = ((DisplayLabel) item).getLabelTextOwner();
			}
			else if (item instanceof DisplayButtonLink)
			{
				descriptionTextOwner = ((DisplayButtonLink) item).getDescriptionTextOwner();
				labelTextOwner = ((DisplayButtonLink) item).getLabelTextOwner();
			}

			// Check the label
			checkReferenceText(element.getLabelType(), labelTextOwner, languageList, element.getLabel(), TextBean.TYPE_LABEL);

			// Check the description
			checkReferenceText(element.getDescriptionType(), descriptionTextOwner, languageList, element.getDescription(),
							TextBean.TYPE_DESCRIPTION);

			// Check the mask if this is a DisplayAttribute element
			if (element instanceof DisplayAttributes)
			{
				DisplayAttributes displayAttribute = (DisplayAttributes) element;
				checkReferenceText(displayAttribute.getMaskType(), displayAttribute.getMaskTextOwner(), languageList,
								displayAttribute.getMask(), TextBean.TYPE_MASK);

			}
		}

	}

	/**
	 * Unclaim a task
	 */
	private void unClaimTask() throws EQException
	{
		if (!EquationCommonContext.getContext().isLRPProcessing())
		{
			return;
		}
		EquationFunctionContext.getContext().unclaimTask(this);
		fhd.setTaskDetail(null);
		fhd.setTaskRqHeader(null);
		fhd.setReason("");
		fhd.setReferToUserId("");
	}

	/**
	 * Determine if task can be viewed or not
	 * 
	 * @param taskDetail
	 *            - the task detail
	 * 
	 * @return true if valid
	 * 
	 * @throws EQException
	 */
	private boolean validateTask(TaskDetail taskDetail, boolean checkLinkedTransaction) throws EQException
	{
		// Null, or Task already claimed by others?
		String currentUser = EquationFunctionContext.getContext().getDisplayUser(fhd.getEquationUser().getEquationUnit(),
						fhd.getEquationUser().getUserId());
		if (taskDetail == null || (taskDetail.getOwner() != null && taskDetail.isClaimed())
						&& !taskDetail.getOwner().equalsIgnoreCase(currentUser))
		{
			fhd.getFunctionMsgManager().generateMessage(rtvEquationSession(), fhd.getFunctionMsgManager().getOtherMessages(), 0, 0,
							"", 0, null, "KSM7606", "", "", FunctionMessages.MSG_NONE);
			return false;
		}

		// Task already completed
		if (taskDetail.isTaskCompleted())
		{
			fhd.getFunctionMsgManager().generateMessage(rtvEquationSession(), fhd.getFunctionMsgManager().getOtherMessages(), 0, 0,
							"", 0, null, "KSM7606", "", "", FunctionMessages.MSG_NONE);
			return false;
		}

		// Task waiting for ESF referral?
		if (checkLinkedTransaction)
		{
			IWE2RecordDao dao = new DaoFactory().getWE2Dao(rtvEquationSession(), new WE2RecordDataModel(taskDetail.getTkiid()));
			WE2RecordDataModel we2record = dao.getWE2Record();
			if (we2record != null)
			{
				fhd.getFunctionMsgManager().generateMessage(rtvEquationSession(), fhd.getFunctionMsgManager().getOtherMessages(),
								0, 0, "", 0, null, "KSM7607", "", "", FunctionMessages.MSG_NONE);
				return false;
			}
		}

		// Task cannot be viewed
		if (EquationFunctionContext.getContext().isOpenTask(taskDetail.getTkiid()))
		{
			fhd.getFunctionMsgManager().generateMessage(rtvEquationSession(), fhd.getFunctionMsgManager().getOtherMessages(), 0, 0,
							"", 0, null, "KSM7601", "", "", FunctionMessages.MSG_NONE);
			return false;
		}

		return true;
	}

	/**
	 * Process an LRP start from a saved transaction
	 * 
	 * @return true if valid
	 */
	private boolean processLRPTaskFromWE() throws EQException
	{
		// Are there any LRP task waiting?
		FunctionSession fs = fhd.getFunctionSession();
		IWE2RecordDao dao = new DaoFactory().getWE2Dao(rtvEquationSession(), new WE2RecordDataModel(fs.getSessionId(), fs
						.getUserId(), fs.getOptionId(), fs.getTransactionId(), true));
		WE2RecordDataModel we2record = dao.getWE2LinkedRecord();
		if (we2record == null || we2record.getTaskId().trim().length() == 0)
		{
			return true;
		}

		// retrieve the task engine
		String sessionIdentifer = fhd.getFunctionInfo().getSessionId();
		ITaskEngine taskEngine = EquationFunctionContext.getContext().getTaskEngine(sessionIdentifer);
		EquationFunctionContext equationFunctionContext = EquationFunctionContext.getContext();

		// retrieve the task details
		TaskDetail taskDetail = taskEngine.getTaskData(we2record.getTaskId());

		// Task is valid?
		if (!validateTask(taskDetail, false))
		{
			return false;
		}

		// Mark as being viewed
		String taskId = taskDetail.getTkiid();
		equationFunctionContext.addOpenTask(taskId, sessionIdentifer);

		try
		{
			equationFunctionContext.addOpenTask(taskId, sessionIdentifer);

			// Try to complete claim
			if (!taskDetail.isClaimed())
			{
				equationFunctionContext.claimTask(fhd.getFunctionInfo().getSessionId(), taskEngine, taskId);
			}

			// retrieve the task payload
			TaskRqHeader payload = taskEngine.getPayloadForTask(taskId);

			// Determine the action that can be taken against the task
			SecurityLevel securityLevel = fhd.getSecurityLevel();
			boolean authTask = payload.getBasicDetail().getTaskType().equals(TaskEngineToolbox.TASK_TYPE_AUTH);
			if (authTask)
			{
				String taskActionList = payload.getTaskActionList().trim();
				securityLevel.setAuthorizeAllowed(LRPToolbox.isTaskActionAuth(taskActionList));
				securityLevel.setReferAllowed(LRPToolbox.isTaskActionRefer(taskActionList));
				securityLevel.setDeclineAllowed(LRPToolbox.isTaskActionDecline(taskActionList));
				securityLevel.setUpdateAllowed(LRPToolbox.isTaskActionUpdate(taskActionList));
			}

			// authorisation task?
			if (authTask)
			{
				ScreenSetHandler screenSetHandler = fhd.getScreenSetHandler();
				ScreenSet screenSetMain = screenSetHandler.rtvScreenSetMain();

				screenSetMain.setFKeyToNextFunction(FunctionKeys.KEY_AUTHA);
				if (screenSetHandler.isScreenSetCRMExist())
				{
					screenSetHandler.getScreenSets().get(ScreenSetHandler.FUNCTION_CRM_SCREEN).setFKeyToNextFunction(
									FunctionKeys.KEY_AUTHA);
				}
			}

			// set the expected mode
			if (payload.getFunctionMode().trim().length() > 0)
			{
				fhd.setServiceMode(payload.getFunctionMode().trim());
			}

			// successful
			fhd.setTaskDetail(taskDetail);
			fhd.setTaskRqHeader(payload);
			return true;
		}
		catch (EQException e)
		{
			equationFunctionContext.clearTaskActiveList(fhd);
			throw e;
		}
	}

	/**
	 * Updates the alert status of the transaction
	 * 
	 * @throws EQException
	 */
	private void updateAlertStatus() throws EQException
	{
		try
		{
			FunctionSession fs = fhd.getFunctionSession();
			WERecordDataModel weRecord = new WERecordDataModel(fs.getOptionId(), fs.getSessionId(), fs.getTransactionId(), fs
							.getUserId());
			DaoFactory daoFactory = new DaoFactory();
			IWERecordDao weDao = daoFactory.getWEDao(rtvEquationSession(), weRecord);
			weDao = daoFactory.getWEDao(rtvEquationSession(), weRecord);
			weRecord = weDao.getWERecord();

			if (weRecord != null)
			{
				weRecord.setUserAlerted("N");
				weDao.insertOrUpdateRecord(weRecord);
			}
			rtvEquationSession().connectionCommit();
		}
		catch (SQLException e)
		{
			try
			{
				rtvEquationSession().connectionRollback();

			}
			catch (Exception e2)
			{
				throw new EQException("FunctionHandler: updateAlertStatus() Failed: " + Toolbox.getExceptionMessage(e2), e2);
			}
		}
	}

	/**
	 * If this a linkage service, then validate that the primary function matches with the specified option id
	 * 
	 * @param optionId
	 *            - the option id
	 * 
	 * @return true if no error
	 */
	public boolean validateLinkService(String optionId) throws EQException
	{
		// Service linkage, then make sure that the option id matches with the primary function
		// of the service linkage
		ScreenSet screenSet = fhd.getScreenSetHandler().rtvScreenSetMain();
		if (screenSet.isLinkService())
		{
			String primaryId = screenSet.getServiceLinkage().getPrimaryFunction().getId();
			if (!optionId.equals(primaryId))
			{
				EquationStandardSession session = rtvEquationSession();
				String linkageId = screenSet.getServiceLinkage().getLinkService().getId();
				fhd.getFunctionMsgManager().insertOtherMessage(
								session,
								0,
								0,
								"",
								0,
								null,
								"KSM7340"
												+ LanguageResources.getFormattedString("Language.LinkageIdDoesNotMatchServiceMF",
																new String[] { linkageId, optionId }), "", "");
				return false;
			}
		}
		return true;

	}
	/**
	 * Return next action
	 * 
	 * @param unit
	 *            - the unit
	 * @param optionId
	 *            - the option identifier
	 * @param context
	 *            - the option context
	 * 
	 * @return next action
	 * @throws EQException
	 */
	public NextAction getNextAction(EquationUnit unit, String optionId, String context) throws EQException
	{
		// NextAction is used by jsp/js to launch new options.
		// For use with options related to function keys and mandatory next request.

		NextAction nextAction = null;

		String actionType = null;
		String control = null;
		String sessionId = null;
		String transactionId = null;
		String originalFullUser = null;
		String status = null;
		String authorityLevel = null;
		int screenSetId = 0;
		int screenNumber = 0;

		String optionType = unit.isLegacyOption(optionId) ? "WF" : "EQ";
		String optionDescription = unit.getOptionDescription(optionId);

		nextAction = new NextAction(actionType, control, optionType, optionId, optionDescription, context, sessionId,
						transactionId, originalFullUser, status, authorityLevel, screenSetId, screenNumber);
		return nextAction;
	}

}