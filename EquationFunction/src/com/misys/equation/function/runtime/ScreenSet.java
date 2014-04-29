package com.misys.equation.function.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.html.dom.HTMLDocumentImpl;
import org.w3c.dom.html.HTMLElement;

import bf.com.misys.eqf.types.header.ServiceRqHeader;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationLogin;
import com.misys.equation.common.access.EquationRecords;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.dao.beans.WERecordDataModel;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.Enhancement;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EqTimingTest;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.adaptor.FunctionAdaptor;
import com.misys.equation.function.adaptor.LayoutAdaptor;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayGroup;
import com.misys.equation.function.beans.DisplayItemList;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionControlData;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.Layout;
import com.misys.equation.function.beans.LinkedFunction;
import com.misys.equation.function.beans.LoadFieldSetStatus;
import com.misys.equation.function.beans.NextAction;
import com.misys.equation.function.beans.RepeatingDataManager;
import com.misys.equation.function.beans.RepeatingGroupStatus;
import com.misys.equation.function.beans.TextBean;
import com.misys.equation.function.el.ELRuntime;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.linkage.ServiceLinkage;
import com.misys.equation.function.tools.FieldFilterLocator;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;
import com.misys.equation.function.tools.HTMLToolbox;
import com.misys.equation.function.tools.LinkedFunctionsToolbox;
import com.misys.equation.function.tools.RepeatingGroupPagingDetail;
import com.misys.equation.function.tools.XMLToolbox;
import com.misys.equation.function.tools.XSDStructureLink;

public class ScreenSet
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ScreenSet.java 17356 2013-09-25 14:37:20Z Lima12 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(ScreenSet.class);

	public static final int SCRN_NEXT = -1;
	public static final int SCRN_PREV = -2;
	public static final int SCRN_CUR = -3;

	// Screen set id
	protected int id;

	// Service definition
	protected Function function;

	// Layout definition to use with this service
	protected Layout layout;

	// Data
	protected FunctionData functionData;

	// Before images
	protected FunctionTransactions beforeImagesTransactions;
	protected FunctionData functionDataBefore;

	// Current screen number to display
	protected int scrnNo;

	// Last screen set displayed
	protected int lastScrnNoDisplayed;

	// Maximum number of screens for this function
	protected int maxScrnNo;

	// Whether this screen set is being displayed for the first time
	protected boolean firstTime;

	// Enter key to next screen set or whatever is the function key
	// This determines the function key to press by the user in order to proceed to the next
	// function. For some, it will be enter (default) or F18 (Accept)
	protected int fKeyToNextFunction;

	// Display group handler
	protected DisplayGroupHandler displayGroupHandler;

	// Repeating group handler
	protected RepeatingGroupStatusHandler repeatingGroupStatusHandler;

	// Function messages
	protected FunctionMessages functionMessages;

	// Function adaptor
	protected FunctionAdaptor functionAdaptor;

	// User Exit adaptor for Layouts (UI)
	protected LayoutAdaptor layoutAdaptor;

	// Display input field set status handler
	protected LoadFieldSetStatusHandler loadFieldSetStatusHandler;

	// Shutter group handler
	protected ShutterGroupHandler shutterHandler;

	// Function mode
	protected String mode;

	// Seleciton option exists during the last validation?
	protected boolean selectionExists;
	protected boolean selectionExistsError;

	// HTML generation incomplete
	protected boolean htmlGenerationIncomplete;

	// Load API generation incomplete
	protected List<String> loadAPIIncomplete; // due to exceeding limit per API
	protected List<String> loadAPI2Incomplete; // due to exceeding total limit for API

	// Function handler details
	protected FunctionHandlerData fhd;
	protected ScreenSetHandler screenSetHandler;
	protected EquationStandardSession equationStandardSession;
	protected EquationUser eqUser;
	protected FunctionMsgManager functionMsgManager;
	protected SecurityLevel securityLevel;
	protected FunctionInfo functionInfo;
	protected FunctionKeys functionKeys;
	protected FunctionSession functionSession;

	private String gpSystem = "";
	private String gpUnit = "";

	protected ServiceLinkage serviceLinkage;

	/**
	 * Construct an empty screen set
	 */
	public ScreenSet()
	{
	}

	/**
	 * Construct a new screens
	 * 
	 * @param id
	 *            - screen set id
	 * @param functionHandlerData
	 *            the Function Handler Data
	 * @param optionId
	 *            - option id
	 * 
	 * @throws EQException
	 */
	public ScreenSet(int id, FunctionHandlerData functionHandlerData, String optionId) throws EQException
	{
		this.fhd = functionHandlerData;
		this.screenSetHandler = functionHandlerData.getScreenSetHandler();
		this.functionMsgManager = functionHandlerData.getFunctionMsgManager();
		this.eqUser = functionHandlerData.getEquationUser();
		this.securityLevel = functionHandlerData.getSecurityLevel();
		this.functionInfo = functionHandlerData.getFunctionInfo();
		this.functionKeys = functionHandlerData.getFunctionKeys();
		this.functionSession = functionHandlerData.getFunctionSession();
		this.equationStandardSession = eqUser.getSessionForNonUpdate();
		this.loadAPIIncomplete = new ArrayList<String>();
		this.loadAPI2Incomplete = new ArrayList<String>();

		this.id = id;

		this.scrnNo = 0;
		this.lastScrnNoDisplayed = -1;
		this.fKeyToNextFunction = FunctionKeys.KEY_ENT;
		this.functionMessages = new FunctionMessages();
		this.firstTime = true;
		this.shutterHandler = new ShutterGroupHandler();
		this.beforeImagesTransactions = null;
		this.functionDataBefore = null;
		this.mode = IEquationStandardObject.FCT_ADD;

		boolean devUnit = equationStandardSession.getUnit().isDevelopmentUnit();

		// check if link service
		this.serviceLinkage = XMLToolbox.getXMLToolbox().getLinkService(equationStandardSession, optionId, devUnit);
		if (this.serviceLinkage == null)
		{
			this.serviceLinkage = null;
			this.layout = XMLToolbox.getXMLToolbox().getLayout(equationStandardSession, optionId, devUnit, true);
			this.function = XMLToolbox.getXMLToolbox().getFunction(equationStandardSession, layout.getServiceId(), devUnit);
		}
		else
		{
			this.function = serviceLinkage.getLinkedFunction();
			this.layout = serviceLinkage.getLinkedLayout();
		}

		resetup(function, layout);
	}

	/**
	 * Re-setup details based on the new function and layout
	 * 
	 * @param function
	 *            - the new function
	 * @param layout
	 *            - the new layout
	 * 
	 * @throws EQException
	 */
	public void resetup(Function function, Layout layout) throws EQException
	{
		// set up the function
		this.function = function;
		if (function == null)
		{
			throw new EQException(LanguageResources.getString("ScreenSet.UnableToLoadFunction"));
		}

		// set up the layout
		this.layout = layout;
		if (layout == null)
		{
			throw new EQException(LanguageResources.getString("ScreenSet.UnableToLoadLayout"));
		}

		// setup other fields dependent on the function and layout
		this.functionData = new FunctionData(function, fhd);
		this.functionData.initFromDisplay(layout, function);
		this.maxScrnNo = function.getInputFieldSets().size();
		this.displayGroupHandler = new DisplayGroupHandler(layout);
		this.loadFieldSetStatusHandler = new LoadFieldSetStatusHandler(function);
		this.repeatingGroupStatusHandler = new RepeatingGroupStatusHandler();

		// set the control data in the function data
		FunctionControlData functionControlData = functionData.getFunctionControlData();
		functionControlData.addData(FunctionControlData.ORG_USER, eqUser.getUserId());
		functionControlData.addData(FunctionControlData.CREATED_BY, "ScreenSet");
		functionControlData.setLoadFieldSetStatuses(loadFieldSetStatusHandler.getLoadFieldSetStatuses());
		functionControlData.setCurrentDisplay(displayGroupHandler.getCurrentDisplay());
		functionControlData.setShutters(shutterHandler.getShutters());
		functionControlData.setRepeatingGroupStatuses(repeatingGroupStatusHandler.getRepeatingGroupStatuses());

		// initialise the adaptor
		setFunctionAdaptor(fhd.getFunctionAdaptorHandler().getFunctionAdaptor(equationStandardSession, getFunctionId()));
		setLayoutAdaptor(fhd.getLayoutAdaptorHandler().getLayoutAdaptor(equationStandardSession, getOptionId()));

		// link service, then also setup the secondary services id
		if (isLinkService())
		{
			functionAdaptor.setSecondaryFunctionIds(equationStandardSession, serviceLinkage.getSecondayFunctionIds(), "");
			layoutAdaptor.setSecondaryFunctionIds(equationStandardSession, serviceLinkage.getSecondayFunctionIds());
		}

		// set the parent of the inputfieldsets and displayattributesets
		for (DisplayAttributesSet attributeSet : layout.getDisplayAttributesSets())
		{

			if (attributeSet.rtvParent() == null)
			{
				attributeSet.setParent(layout);
			}
		}

		for (InputFieldSet fieldSet : function.getInputFieldSets())
		{
			if (fieldSet.rtvParent() == null)
			{
				fieldSet.setParent(function);
			}
		}

		// reload service texts in the cache
		if (this.eqUser != null && this.eqUser.getEquationUnit() != null && this.eqUser.getEquationUnit().isDevelopmentUnit())
		{
			// determine if this is a predefined ScreenSet or not
			boolean preDefinedScreenSet = (this instanceof ScreenSetCRM) || (this instanceof ScreenSetAC2)
							|| (this instanceof ScreenSetAC1) || (this instanceof ScreenSetAC3);

			EquationRecords records = eqUser.getEquationUnit().getRecords();
			if (!preDefinedScreenSet)
			{
				records.reloadHBXRecords(equationStandardSession, TextBean.OWNER_BANK, true);
				records.reloadHBXRecords(equationStandardSession, TextBean.OWNER_MISYS, true);
			}

			records.reloadHBXRecords(equationStandardSession, function.rtvTextOwner(), true);
			records.reloadHBXRecords(equationStandardSession, layout.rtvTextOwner(), true);
		}
	}

	/**
	 * Return the screen set ID
	 * 
	 * @return the screen set ID
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Set the screen set ID
	 * 
	 * @param id
	 *            - the screen set ID
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Return the Function Handler Data
	 * 
	 * @return the Function Handler Data
	 */
	public FunctionHandlerData getFhd()
	{
		return fhd;
	}

	/**
	 * Return the function data
	 * 
	 * @return the function data
	 */
	public FunctionData getFunctionData()
	{
		return functionData;
	}

	/**
	 * Set the function data
	 * 
	 * @param functionData
	 *            - the function data
	 */
	public void setFunctionData(FunctionData functionData)
	{
		this.functionData = functionData;
		this.functionAdaptor.setFunctionData(fhd, functionData);
		this.layoutAdaptor.setFunctionData(fhd, functionData);
	}

	/**
	 * Get the current screen number
	 * 
	 * @return the current screen number
	 */
	public int getScrnNo()
	{
		return scrnNo;
	}

	/**
	 * Set the current screen number
	 * 
	 * @param scrnNo
	 *            - the current screen number
	 */
	public void setScrnNo(int scrnNo)
	{
		this.scrnNo = scrnNo;
	}

	/**
	 * Return the last screen number displayed
	 * 
	 * @return the last screen number displayed
	 */
	public int getLastScrnNoDisplayed()
	{
		return lastScrnNoDisplayed;
	}

	/**
	 * Set the last screen number displayed
	 * 
	 * @param lastScrnNoDisplayed
	 *            - the last screen number displayed
	 */
	public void setLastScrnNoDisplayed(int lastScrnNoDisplayed)
	{
		this.lastScrnNoDisplayed = lastScrnNoDisplayed;
	}

	/**
	 * Return the layout id
	 * 
	 * @return the layout id
	 */
	public String getOptionId()
	{
		return layout.getId();
	}

	/**
	 * Return the function id
	 * 
	 * @return the function id
	 */
	public String getFunctionId()
	{
		return function.getId();
	}

	/**
	 * Return the function
	 * 
	 * @return the function
	 */
	public Function getFunction()
	{
		return function;
	}

	/**
	 * Return the layout
	 * 
	 * @return the layout
	 */
	public Layout getLayout()
	{
		return layout;
	}

	/**
	 * Return the maximum screen number
	 * 
	 * @return the maximum screen number
	 */
	public int getMaxScrnNo()
	{
		return maxScrnNo;
	}

	/**
	 * Set the maximum screen number
	 * 
	 * @param maxScrnNo
	 *            - the maximum screen number
	 */
	public void setMaxScrnNo(int maxScrnNo)
	{
		this.maxScrnNo = maxScrnNo;
	}

	/**
	 * Return the function key to pressed to proceed
	 * 
	 * @return the function key to pressed to proceed
	 */
	public int getFKeyToNextScreenSet()
	{
		return fKeyToNextFunction;
	}

	/**
	 * Set the function key to pressed to proceed
	 * 
	 * @param keyToNextFunction
	 *            - the function key to pressed to proceed
	 */
	public void setFKeyToNextFunction(int keyToNextFunction)
	{
		fKeyToNextFunction = keyToNextFunction;
	}

	/**
	 * Determine if first time to display this screen set
	 * 
	 * @return true, if first time to display this screen set
	 */
	public boolean isFirstTime()
	{
		return firstTime;
	}

	/**
	 * Set the first time flag
	 * 
	 * @param firstTime
	 *            - the first time flag
	 */
	public void setFirstTime(boolean firstTime)
	{
		this.firstTime = firstTime;
	}

	/**
	 * Return the group display handler
	 * 
	 * @return the group display handler
	 */
	public DisplayGroupHandler getDisplayGroupHandler()
	{
		return displayGroupHandler;
	}

	/**
	 * Return the shutter group handler
	 * 
	 * @return the shutter group handler
	 */
	public ShutterGroupHandler getShutterHandler()
	{
		return shutterHandler;
	}

	/**
	 * Return the repeating group status handler
	 * 
	 * @return the repeating group status handler
	 */
	public RepeatingGroupStatusHandler getRepeatingGroupStatusHandler()
	{
		return repeatingGroupStatusHandler;
	}

	/**
	 * Return the list of messages
	 * 
	 * @return the list of messages
	 */
	public FunctionMessages getFunctionMessages()
	{
		return functionMessages;
	}

	/**
	 * Set the list of messages
	 * 
	 * @param functionMessages
	 *            - the list of messages
	 */
	public void setFunctionMessages(FunctionMessages functionMessages)
	{
		this.functionMessages = functionMessages;
	}

	/**
	 * Return the function adaptor
	 * 
	 * @return the function adaptor
	 */
	public FunctionAdaptor getFunctionAdaptor()
	{
		return functionAdaptor;
	}

	/**
	 * Set the function adaptor
	 * 
	 * @param functionAdaptor
	 *            - the function adaptor
	 */
	public void setFunctionAdaptor(FunctionAdaptor functionAdaptor)
	{
		this.functionAdaptor = functionAdaptor;
		functionAdaptor.setFunctionData(fhd, functionData);
	}

	/**
	 * Return the layout adaptor
	 * 
	 * @return the layout adaptor
	 */
	public LayoutAdaptor getLayoutAdaptor()
	{
		return layoutAdaptor;
	}

	/**
	 * Set the layout adaptor
	 * 
	 * @param layoutAdaptor
	 *            - the layout adaptor
	 */
	public void setLayoutAdaptor(LayoutAdaptor layoutAdaptor)
	{
		this.layoutAdaptor = layoutAdaptor;
		layoutAdaptor.setFunctionData(fhd, functionData);
	}

	/**
	 * Return the function mode
	 * 
	 * @return the function mode
	 */
	public String getMode()
	{
		return mode;
	}

	/**
	 * Set the function mode
	 * 
	 * @param mode
	 *            - the function mode
	 */
	public void setMode(String mode)
	{
		this.mode = mode;
	}

	/**
	 * Return whether the function is in add, maintain, delete or enquire mode
	 * 
	 * @return the function mode
	 */
	public String rtvMode()
	{
		if (mode.equals(IEquationStandardObject.FCT_FUL))
		{
			return IEquationStandardObject.FCT_MNT;
		}
		else
		{
			return mode;
		}
	}

	/**
	 * Determine if selection option has been specified during the validation pass
	 * 
	 * @return true if selection option has been specified during the validation pass
	 */
	public boolean isSelectionExists()
	{
		return selectionExists;
	}

	/**
	 * Determine if error with selection option
	 * 
	 * @return true if error with selection option
	 */
	public boolean isSelectionExistsError()
	{
		return selectionExistsError;
	}

	/**
	 * Determine if HTML generation is complete or not
	 * 
	 * @return true if HTML generation is complete
	 */
	public boolean isHtmlGenerationIncomplete()
	{
		return htmlGenerationIncomplete;
	}

	/**
	 * Return the list APIs that were not loaded completely
	 * 
	 * @return the list APIs that were not loaded completely
	 */
	public List<String> getLoadAPIIncomplete()
	{
		return loadAPIIncomplete;
	}

	/**
	 * Return the list APIs that were not loaded completely
	 * 
	 * @return the list APIs that were not loaded completely
	 */
	public List<String> getLoadAPI2Incomplete()
	{
		return loadAPI2Incomplete;
	}

	/**
	 * Clear the Load API Incomplete status
	 */
	public void clearLoadIncomplete()
	{
		loadAPIIncomplete.clear();
		loadAPI2Incomplete.clear();
	}

	/**
	 * Retrieve the current field set Id
	 * 
	 * @return the current field set Id
	 */
	public String getCurrentFieldSet()
	{
		return function.getInputFieldSets().get(scrnNo).getId();
	}

	/**
	 * Retrieve the required field set Id
	 * 
	 * @return the field set Id
	 */
	public String getFieldSetId(int reqScrnNo)
	{
		// return input field set id
		if (reqScrnNo > 0 && reqScrnNo <= maxScrnNo)
		{
			return function.getInputFieldSets().get(reqScrnNo - 1).getId();
		}

		// return load api id
		if (reqScrnNo > FunctionUpdate.UPDSCRN && reqScrnNo < FunctionUpdate.PREUPDSCRN)
		{
			int index = reqScrnNo - FunctionUpdate.UPDSCRN - 1;
			if (index < function.rtvPrimaryInputFieldSet().getLoadAPIs().size())
			{
				return function.rtvPrimaryInputFieldSet().getLoadAPIs().get(index).getId();
			}
		}

		String id;
		switch (reqScrnNo)
		{
			case 0:
				id = "KEY";
				break;
			case FunctionUpdate.UPDSCRN:
				id = "UPDATE";
				break;
			case FunctionUpdate.PREUPDSCRN:
				id = "PREUPDATE";
				break;
			case FunctionUpdate.POSTUPDSCRN:
				id = "POSTUPDATE";
				break;
			case FunctionUpdate.FINALUPDSCRN:
				id = "FINALUPDATE";
				break;
			default:
				id = Integer.toString(reqScrnNo);
		}
		return id;
	}

	/**
	 * Retrieve the screen number for a field set
	 * 
	 * @return the screen number
	 */
	public int getScrnNoFromId(String id)
	{
		int screen = function.inputFieldSetKeys().indexOf(id);
		if (screen != -1)
		{
			screen++;
		}
		return screen;
	}

	/**
	 * Proceed to the next screen
	 * 
	 * @return true, if the next screen will be displayed
	 */
	public boolean nextScreen() throws EQException
	{
		if (chkLastScreen())
		{
			return false;
		}
		else
		{
			setScrnNo(scrnNo + 1);
			// retrieve(scrnNo);
			return true;
		}
	}
	/**
	 * Proceed to the previous screen
	 * 
	 * @Return true, if the previous screen will be displayed
	 */
	public boolean prevScreen()
	{
		if (chkFirstScreen())
		{
			return false;
		}
		else
		{
			setScrnNo(scrnNo - 1);
			return true;
		}
	}

	/**
	 * Determines whether the screen set is already on the last screen
	 * 
	 * @return true, if it is already on the last screen
	 */
	public boolean chkLastScreen()
	{
		return (scrnNo == maxScrnNo - 1);
	}

	/**
	 * Determines whether the screen set is on the first screen
	 * 
	 * @return true, if it is already on the first screen
	 */
	public boolean chkFirstScreen()
	{
		return (scrnNo == 0);
	}

	/**
	 * Returns the current screen in HTML format<br>
	 * 
	 * @return the HTML fieldSet of the current screen
	 * 
	 * @throws EQException
	 */
	public String rtvScreen() throws EQException
	{
		// html toolbox
		HTMLToolbox htmlToolbox = new HTMLToolbox();
		htmlToolbox.setScreenSet(this);

		// generate the html document
		HTMLDocumentImpl htmlDocument = new HTMLDocumentImpl();
		HTMLElement htmlElement = rtvHTML(htmlToolbox, htmlDocument);

		// any particular field to receive the focus?
		if (functionKeys.getFieldId().length() > 0)
		{
			htmlToolbox.setFirstInputFieldInError(functionKeys.getFieldId());
		}

		// setup the hidden elements
		htmlToolbox.addDefaultElements(htmlDocument, htmlElement);
		chgDefaultElement(htmlToolbox, htmlDocument);

		// last minute processing
		htmlToolbox.finalProcessing(htmlDocument, htmlElement);
		htmlGenerationIncomplete = htmlToolbox.isExceedNumberFieldLimit();

		// setup the last screen displayed - only during non-key screen processing
		if (!isKeyOpen())
		{
			lastScrnNoDisplayed = scrnNo;
		}

		// serialise the element
		EqTimingTest.printStartTime("ScreenSet.rtvScreen().serialisation", "Seriliation of HTML");
		String htmlString = htmlToolbox.serialiseHTML(htmlElement);
		EqTimingTest.printTime("ScreenSet.rtvScreen().serialisation", "Seriliation of HTML");
		return htmlString;
	}

	/**
	 * Set the values of the default element
	 * 
	 * @param htmlToolbox
	 *            - the HTML toolbox
	 * @param htmlDocument
	 *            - the HTML document
	 */
	protected void chgDefaultElement(HTMLToolbox htmlToolbox, HTMLDocumentImpl htmlDocument)
	{
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_NAME, functionInfo.getName());
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_SCNO, String.valueOf(maxScrnNo));
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_CUR_SCRN, String.valueOf(scrnNo + 1));
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_FIELDSET, getCurrentFieldSet());
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_FKEY, String.valueOf(functionKeys.getFuncKey()));
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_FCT, mode);
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_MSGSEV, String.valueOf(functionMsgManager.getFunctionMessages()
						.getMsgSev()));
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_CHCKR, String.valueOf(securityLevel.getCheckerType()));
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_NMSGS, String.valueOf(functionMsgManager.getFunctionMessages()
						.getMessages().size()));
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_RCHKR, String.valueOf(securityLevel.getRequiredCheckerType()));
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_VFKEYS, functionKeys.rtvFunctionKeys());
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_FKYTXT, functionKeys.rtvFunctionKeysText());
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_FFKYTXT, functionKeys.rtvFunctionKeysWithLabel());
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_UPDERROR, String.valueOf(securityLevel.isUpdateMessage()));
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_MSGIDS, functionMsgManager.getFunctionMessages().rtvMessageIds());
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_MSGAMT, functionMsgManager.getFunctionMessages().rtvAmounts());
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_MSGBRNM, functionMsgManager.getFunctionMessages().rtvBranches());
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_MSGDRCR, functionMsgManager.getFunctionMessages().rtvDrCr());
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_DEFSUPER, functionData.rtvFieldDbValue(FunctionData.FLD_SUPERID));
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_JOBID, equationStandardSession.getConnectionWrapper().getJobId());
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_OPTIONID, getOptionId());
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_UNITID, gpUnit);
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_SYSTEMID, gpSystem);
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_REFERTOUSERID, fhd.getReferToUserId());
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_SUPMSG, fhd.getReason());

		// setup the LRP specific details
		if (fhd.getTaskDetail() != null)
		{
			htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_TASKID, fhd.getTaskDetail().getTkiid());
			htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_TASKPROCID, fhd.getTaskDetail().getParent());
			if (fhd.getTaskRqHeader() != null)
			{
				htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_TASKTYPE, fhd.getTaskRqHeader().getBasicDetail()
								.getTaskType());
			}
			htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_COMPLETETASK, fhd.getCompletedTask());
			fhd.setCompletedTask("");
		}

		// session restored, pass the transaction id
		if (functionSession.isSessionRestored())
		{
			htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_TRANID, functionSession.getTransactionId());
		}

		// determine whether key details are displayed
		LoadFieldSetStatus loadFieldSetStatus = loadFieldSetStatusHandler.getFieldSetStatus(function.getInputFieldSets()
						.get(scrnNo).getId());
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_KEYEXSTSET, String
						.valueOf(securityLevel.getCheckerType() == SecurityLevel.CHCKR_NONE && loadFieldSetStatus.isKeyExist()));
		htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_KEYDSPSET, String.valueOf(!loadFieldSetStatus.isDetailOpen()));

		// add all the group display
		for (Entry<String, Integer> entry : displayGroupHandler.getCurrentDisplay().entrySet())
		{
			int level = (displayGroupHandler.getCurrentDisplay().get(entry.getKey())).intValue();
			htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_DSPGRPID + entry.getKey(), String.valueOf(level));
		}
	}

	/**
	 * Return the display input field set status handler
	 * 
	 * @return the display input field set status handler
	 */
	public LoadFieldSetStatusHandler getLoadFieldSetStatusHandler()
	{
		return loadFieldSetStatusHandler;
	}

	/**
	 * Return the list of transactions' before image
	 * 
	 * @return the list of transactions' before images
	 */
	public FunctionTransactions getBeforeImagesTransactions()
	{
		return beforeImagesTransactions;
	}

	/**
	 * Set the list of transactions' before image
	 * 
	 * @param beforeImagesTransactions
	 *            - the list of transactions' before images
	 */
	public void setBeforeImagesTransactions(FunctionTransactions beforeImagesTransactions)
	{
		this.beforeImagesTransactions = beforeImagesTransactions;
	}

	/**
	 * Return the before image of this function
	 * 
	 * @return the before image of this function
	 */
	public FunctionData getFunctionDataBefore()
	{
		return functionDataBefore;
	}

	/**
	 * Set the before image of this function
	 * 
	 * @param functionDataBefore
	 *            - the before image of this function
	 */
	public void setFunctionDataBefore(FunctionData functionDataBefore)
	{
		this.functionDataBefore = functionDataBefore;
	}

	/**
	 * Execute load API for the given field set id
	 * 
	 * @param inputFieldSetId
	 *            - the input field set Id
	 * 
	 * @return the message severity
	 */
	public int retrieve(String inputFieldSetId) throws EQException
	{
		// clear messages
		functionMessages.clearMessages();

		// clear repeating data
		functionData.clearRepeatingData();

		// search for the load api
		for (int i = 0; i < function.getInputFieldSets().size(); i++)
		{
			if (inputFieldSetId.equals(function.getInputFieldSets().get(i).getId()))
			{
				retrieve(i);
				break;
			}
		}
		return functionMessages.getMsgSev();
	}

	/**
	 * Execute load API for the given input field set
	 * 
	 * @param index
	 *            - the index of the input field set
	 * 
	 * @return the message severity
	 */
	public int retrieve(int index) throws EQException
	{
		// get the field set
		InputFieldSet inputFieldSet = function.getInputFieldSets().get(index);

		// any load api field set?
		if (!inputFieldSet.isPrimary() && inputFieldSet.getLoadAPIs().size() == 0)
		{
			return FunctionMessages.MSG_NONE;
		}

		FunctionUpdate functionUpdate = new FunctionUpdate(fhd);
		functionUpdate.setCommitProcessing(false);
		functionData.setChecksum(null);
		String interimMode = functionUpdate.retrieve(this, inputFieldSet);
		functionMessages.insertMessages(functionUpdate.getFunctionMessages());

		// no error, then do checksum processing
		if (index == 0 && functionMessages.getMsgSev() < FunctionMessages.MSG_ERROR)
		{
			checkSumProcessing();
		}

		// primary field set
		if (index == 0 && functionMessages.getMsgSev() < FunctionMessages.MSG_ERROR)
		{
			setMode(processMode(interimMode, fhd.getServiceMode()));
			getFunctionData().chgFieldDbValue(FunctionData.FLD_FCT, rtvMode());
			setBeforeImagesTransactions(functionUpdate.getFunctionTransactions());
			setFunctionDataBefore(functionData.duplicateFunctionData());
			securityLevel.setEnquireMode(rtvMode().equals(IEquationStandardObject.FCT_ENQ));
		}

		// non-primary field set
		else
		{
			if (beforeImagesTransactions == null)
			{
				beforeImagesTransactions = new FunctionTransactions(fhd, IEquationStandardObject.FCT_ADD);
			}
			beforeImagesTransactions.addTransaction(functionUpdate.getFunctionTransactions().getEquationTransactions());
		}

		// error loading primary field set's load apis, reset all the detail screens
		if (index == 0 && functionMessages.getMsgSev() >= FunctionMessages.MSG_ERROR)
		{
			functionData.resetFields(function, false, fhd);
		}

		return functionMessages.getMsgSev();
	}

	/**
	 * Unload the load API for the given input field set id
	 * 
	 * @param inputFieldSetId
	 *            - the input field set Id
	 * 
	 * @return the message severity
	 */
	public int unload(String inputFieldSetId) throws EQException
	{
		repeatingGroupStatusHandler.clearRepeatingGroupStatus();
		functionMessages.clearMessages();
		for (int i = 0; i < function.getInputFieldSets().size(); i++)
		{
			if (inputFieldSetId.equals(function.getInputFieldSets().get(i).getId()))
			{
				unload(i);
				break;
			}
		}

		return functionMessages.getMsgSev();
	}

	/**
	 * Unload the load API for the given input field set
	 * 
	 * @param index
	 *            - the index of the nput field set
	 * 
	 * @return the message severity
	 * 
	 * @throws EQException
	 */
	public int unload(int index) throws EQException
	{
		if (index == 0)
		{
			beforeImagesTransactions = null;
			functionDataBefore = null;
		}

		return FunctionMessages.MSG_NONE;
	}

	/**
	 * Determine whether mode is valid or not
	 * 
	 * @param interimMode
	 *            - the mode as automatically determined by the system
	 * @param expectedMode
	 *            - the mode that should be
	 * 
	 * @return any error message
	 * 
	 * @throws EQException
	 */
	private String processMode(String interimMode, String expectedMode) throws EQException
	{
		// valid modes
		boolean allowedAdd = function.isAllowedAdd();
		boolean allowedMaint = function.isAllowedMaint();
		boolean allowedDel = function.isAllowedDel();
		boolean allowedEnq = function.isAllowedEnq();

		// assume not error
		String dsepms = "";
		String derivedMode = IEquationStandardObject.FCT_ADD;

		// is expected mode specified, then change the automatically derived mode
		if (expectedMode != null && expectedMode.length() != 0)
		{
			// this must be an Add function
			if (expectedMode.equals(IEquationStandardObject.FCT_ADD))
			{
				// .. but already exist
				if (!interimMode.equals(IEquationStandardObject.FCT_ADD))
				{
					dsepms = "KSM7603";
				}
				else
				{
					interimMode = expectedMode;
				}
			}

			// this must be a Maintain mode
			else if (expectedMode.equals(IEquationStandardObject.FCT_MNT) || expectedMode.equals(IEquationStandardObject.FCT_DEL)
							|| expectedMode.equals(IEquationStandardObject.FCT_ENQ))
			{
				// .. but does not exist
				if (interimMode.equals(IEquationStandardObject.FCT_ADD))
				{
					dsepms = "KSM7602";
				}
				else
				{
					interimMode = expectedMode;
				}
			}
		}

		// error already
		if (dsepms.length() > 0)
		{
		}

		// enquiry only, then it will always be in enquiry mode
		else if (allowedEnq)
		{
			derivedMode = IEquationStandardObject.FCT_ENQ;
		}

		// fully functional, then determine actual mode
		else if (interimMode.equals(IEquationStandardObject.FCT_FUL))
		{
			// add, maintain, delete not allowed
			if (!allowedDel && !allowedAdd && !allowedMaint)
			{
				dsepms = "KSM7358";
			}

			// maintain and delete not allowed, then cannot maintain/delete
			else if (!allowedDel && !allowedMaint)
			{
				dsepms = "KSM7362";
			}

			// maintain not allowed, then delete mode
			else if (!allowedMaint)
			{
				derivedMode = IEquationStandardObject.FCT_DEL;
			}

			// delete not allowed, then maintain mode
			else if (!allowedDel)
			{
				derivedMode = IEquationStandardObject.FCT_MNT;
			}

			// fully functional
			else
			{
				derivedMode = IEquationStandardObject.FCT_FUL;
			}
		}

		// add mode?
		else if (interimMode.equals(IEquationStandardObject.FCT_ADD))
		{
			if (!allowedAdd)
			{
				dsepms = "KSM7359";
			}
			else
			{
				derivedMode = IEquationStandardObject.FCT_ADD;
			}
		}

		// delete mode?
		else if (interimMode.equals(IEquationStandardObject.FCT_DEL))
		{
			if (!allowedDel)
			{
				dsepms = "KSM7360";
			}
			else
			{
				derivedMode = IEquationStandardObject.FCT_DEL;
			}
		}

		// maintain mode?
		else if (interimMode.equals(IEquationStandardObject.FCT_MNT))
		{
			if (!allowedMaint)
			{
				dsepms = "KSM7361";
			}
			else
			{
				derivedMode = IEquationStandardObject.FCT_MNT;
			}
		}

		// enquiry mode?
		else if (interimMode.equals(IEquationStandardObject.FCT_ENQ))
		{
			derivedMode = IEquationStandardObject.FCT_ENQ;
		}

		// any error?
		if (dsepms.length() > 0)
		{
			functionMsgManager.generateMessage(equationStandardSession, functionMessages, id, scrnNo, "", 0, null, dsepms, "", "",
							FunctionMessages.MSG_NONE);
		}

		// return the mode
		return derivedMode;
	}

	/**
	 * Set other details in the screen set. This is to initialise details that has not been setup in the Function Handler Data at
	 * the time the screen set is initialised
	 */
	protected void setOtherDetails()
	{
		functionSession = fhd.getFunctionSession();
	}

	/**
	 * Determine if load API exists
	 * 
	 * @return true if load API exists
	 */
	public boolean isKeyFieldExists()
	{
		return (function.getInputFieldSets().get(0).containsKeyFields());
	}

	/**
	 * Return the first input field set
	 * 
	 * @return the first input field set
	 */
	public InputFieldSet getMainInputFieldSet()
	{
		return (function.getInputFieldSets().get(0));
	}

	/**
	 * Determine if this screen set is in Enquiry mode
	 * 
	 * @return true if the screen is in enquiry mode
	 */
	public boolean chkEnquiry()
	{
		return mode.equals(IEquationStandardObject.FCT_ENQ);
	}

	/**
	 * Perform selection option processing across all repeating groups
	 * 
	 * @return true if a selection option has been processed in any of the repeating groups
	 * 
	 * @throws EQException
	 */
	public boolean processSelectionOption() throws EQException
	{
		// are there any repeating data managers?
		if (functionData.getRepeatingDataManagers().size() <= 0)
		{
			return false;
		}

		// blank it out
		fhd.setDrillDownRepeatingGroup("");
		fhd.getFunctionMsgManager().clearMessages();

		// loop through the display items looking for any repeating groups
		DisplayItemList displayItems = layout.getDisplayAttributesSets().get(scrnNo).getDisplayItems();

		// loop through the current layout and look for any repeating group
		for (int i = 0; i < displayItems.size(); i++)
		{
			if (displayItems.get(i) instanceof DisplayGroup)
			{
				DisplayGroup displayGroup = (DisplayGroup) displayItems.get(i);
				if (DisplayGroup.isRepeating(displayGroup) && displayGroup.getLinkedFunctions().size() > 0)
				{
					boolean selectionMade = processSelectionOption(displayGroup.getRepeatingGroup(), displayGroup
									.getLinkedFunctions());
					if (selectionMade)
					{
						return true;
					}
				}
			}
		}

		// no selection option
		return false;
	}

	/**
	 * Perform selection option processing on the given repeating group
	 * 
	 * @return true if a selection option has been processed on the repeating group
	 * 
	 * @throws EQException
	 */
	public boolean processSelectionOption(String repeatingGroupId) throws EQException
	{
		fhd.setDrillDownRepeatingGroup("");
		fhd.getFunctionMsgManager().clearMessages();

		// loop through the display items looking for any repeating groups
		DisplayGroup displayGroup = layout.getDisplayAttributesSets().get(scrnNo).rtvRepeatingGroup(repeatingGroupId);
		if (DisplayGroup.isRepeating(displayGroup) && displayGroup.getLinkedFunctions().size() > 0)
		{
			boolean selectionMade = processSelectionOption(displayGroup.getRepeatingGroup(), displayGroup.getLinkedFunctions());
			if (selectionMade)
			{
				fhd.setDrillDownRepeatingGroup(repeatingGroupId);
				return true;
			}
		}

		// no selection option
		return false;
	}

	/**
	 * Perform selection option processing on the given list
	 * 
	 * @param repeatingGroupId
	 *            - the repeating group id
	 * @param linkedFunctions
	 *            - the linked functions for the repeating group
	 * 
	 * @return true if a selection option has been processed
	 * 
	 * @throws EQException
	 */
	protected boolean processSelectionOption(String repeatingGroupId, List<LinkedFunction> linkedFunctions) throws EQException
	{
		RepeatingDataManager dataManager = functionData.getRepeatingDataManager(repeatingGroupId);
		LinkedFunctionsToolbox linkedFunctionsTool = new LinkedFunctionsToolbox(linkedFunctions);

		dataManager.moveFirst();
		while (dataManager.next())
		{
			String selectionOption = dataManager.getInputValue(dataManager.rtvSelectionOptionId()).trim();

			this.gpSystem = dataManager.getInputValue(dataManager.rtvGPUnit());
			this.gpUnit = dataManager.getInputValue(dataManager.rtvGPUnit());

			if (selectionOption.length() > 0)
			{
				String linkOption = linkedFunctionsTool.getOption(selectionOption);
				if (linkOption.length() > 0)
				{
					int i = processLinkOptionOfSelectionOption(selectionOption, linkOption, repeatingGroupId, linkedFunctionsTool,
									dataManager);
					if (i == 1)
					{
						// If we are going to do next action processing then remove selection from the repeating data
						if (fhd.getNextAction() != null)
						{
							functionData.rtvFieldData(dataManager.rtvSelectionOptionId()).setInputValue("");
						}
						return true;
					}
				}
			}
		}

		// no selection option
		return false;
	}

	/**
	 * Perform default selection option of a particular row of a repeating group
	 * 
	 * @param repeatingGroupId
	 *            - the repeating group id
	 * @param selectionOption
	 *            - the selection option
	 * @param rowIndex
	 *            - the row index
	 * 
	 * @return true if a selection option has been processed on the repeating group
	 * 
	 * @throws EQException
	 */
	public boolean processSingleSelectionOption(String repeatingGroupId, String selectionOption, int rowIndex) throws EQException
	{
		fhd.setDrillDownRepeatingGroup(EqDataType.GLOBAL_DELIMETER);

		// set the row in the datamanager
		RepeatingDataManager dataManager = functionData.getRepeatingDataManager(repeatingGroupId);
		dataManager.setRow(rowIndex);

		DisplayGroup displayGroup = layout.getDisplayAttributesSets().get(scrnNo).rtvRepeatingGroup(repeatingGroupId);
		LinkedFunctionsToolbox linkedFunctionsTool = new LinkedFunctionsToolbox(displayGroup.getLinkedFunctions());
		processLinkOptionOfSelectionOption(selectionOption, linkedFunctionsTool.getOption(selectionOption), repeatingGroupId,
						linkedFunctionsTool, dataManager);
		return true;
	}

	/**
	 * Perform global selection option on a repeating group
	 * 
	 * @param repeatingGroupId
	 *            - the repeating group id
	 * @param selectionOption
	 *            - the selection option
	 * 
	 * @return true if a selection option has been processed on the repeating group
	 * 
	 * @throws EQException
	 */
	public boolean processSingleSelectionOption(String repeatingGroupId, String selectionOption) throws EQException
	{
		fhd.setDrillDownRepeatingGroup(EqDataType.GLOBAL_DELIMETER);
		DisplayGroup displayGroup = layout.getDisplayAttributesSets().get(scrnNo).rtvRepeatingGroup(repeatingGroupId);
		RepeatingDataManager dataManager = functionData.getRepeatingDataManager(repeatingGroupId);
		LinkedFunctionsToolbox linkedFunctionsTool = new LinkedFunctionsToolbox(displayGroup.getLinkedFunctions());
		processLinkOptionOfSelectionOption(selectionOption, linkedFunctionsTool.getOption(selectionOption), repeatingGroupId,
						linkedFunctionsTool, dataManager);
		return true;
	}

	/**
	 * Process a selection option of a repeating group row
	 * 
	 * @param selectionOption
	 *            - the selection option
	 * @param linkOption
	 *            - the link option associated with the selection option
	 * @param repeatingGroupId
	 *            - the repeating group id
	 * @param linkedFunctionsTool
	 *            - the linked function tool box
	 * @param dataManager
	 *            - the repeating group data manager
	 * 
	 * @return 1=process and exit, 2=process and go to next record
	 * 
	 * @throws EQException
	 */
	private int processLinkOptionOfSelectionOption(String selectionOption, String linkOption, String repeatingGroupId,
					LinkedFunctionsToolbox linkedFunctionsTool, RepeatingDataManager dataManager) throws EQException
	{
		// assume it will fail
		boolean ok = false;

		EquationLogin equationLogin = EquationCommonContext.getContext().getEqLogins().get(functionSession.getSessionId());
		EquationUnit unit = fhd.getEquationUser().getEquationUnit();
		// For UXP run linked option in new page if the linked option is Webfacing or the enquiry is Transaction Workload (TWL)
		if (equationLogin.getUserInterfaceType() == EquationCommonContext.UI_UXP
						&& (unit.isLegacyOption(linkOption) || linkOption.equals("TWL")))
		{
			NextAction nextAction = linkedFunctionsTool.getNextAction(unit, linkOption, selectionOption, functionData);
			fhd.setNextAction(nextAction);
			return 1;
		}

		if (linkedFunctionsTool.isSpecialCode(selectionOption))
		{
			ok = FunctionRuntimeToolbox.addDrillDownScreenSet(fhd.getFunctionHandler(), linkOption, repeatingGroupId, dataManager
							.currentRowNumber());
		}
		else
		{
			boolean legacyFunction = fhd.getEquationUser().getEquationUnit().isLegacyOption(linkOption);
			String context = linkedFunctionsTool.getContextString(selectionOption, functionData, legacyFunction);
			ok = FunctionRuntimeToolbox.addDrillDownChild(fhd.getFunctionHandler(), linkOption, context, repeatingGroupId,
							dataManager.currentRowNumber());
			if (!ok)
			{
				FunctionHandler childFh = fhd.removeDrillDownChild();
				fhd.getFunctionMsgManager().getOtherMessages().insertMessages(
								childFh.getFhd().getFunctionMsgManager().getOtherMessages());
				fhd.getFunctionMsgManager().getFunctionMessages().insertMessages(
								childFh.getFhd().getFunctionMsgManager().getFunctionMessages());
				fhd.setLastRepeatingGroup(repeatingGroupId);
				fhd.setLastRepeatingRow(dataManager.currentRowNumber() + 1);
				return 1;
			}

			// GP: it will pick up the selected Unit form the current row. UNIT is the name of the column that contains the units.
			// the GP Unit to use is set; using the value of repeating group unit column
			String gpUnit = "";
			String gpSystem = "";
			if (dataManager.getFieldValues("RA_UNIT") != null)
			{
				gpUnit = dataManager.getFieldValues("RA_UNIT").getDbValue();
				gpSystem = dataManager.getFieldValues("RA_SYS").getDbValue();
			}
			if (dataManager.getFieldValues("RB_UNIT") != null)
			{
				gpUnit = dataManager.getFieldValues("RB_UNIT").getDbValue();
				gpSystem = dataManager.getFieldValues("RB_SYS").getDbValue();
			}
			if (dataManager.getFieldValues("RC_UNIT") != null)
			{
				gpUnit = dataManager.getFieldValues("RC_UNIT").getDbValue();
				gpSystem = dataManager.getFieldValues("RC_SYS").getDbValue();
			}

			if (gpUnit.equals(fhd.getEquationUser().getEquationUnit().getUnitId())
							&& gpSystem.equals(fhd.getEquationUser().getEquationUnit().getSystem().getSystemId()))
			{
				gpUnit = "";
				gpSystem = "";
			}
			fhd.getFunctionHandler().getFhd().getChildFunctionHandler().getFhd().setGpUnit(gpUnit);
			fhd.getFunctionHandler().getFhd().getChildFunctionHandler().getFhd().setGpSystem(gpSystem);

			// are going to auto apply this thing?
			if (linkedFunctionsTool.isAutoExecute(selectionOption))
			{
				FunctionHandler childFh = fhd.getChildFunctionHandler();

				// reload context
				ScreenSet childMainScreenSet = childFh.getFhd().getScreenSetHandler().rtvScreenSetMain();
				childMainScreenSet.getFunctionData().loadFieldDataFromContext(childMainScreenSet.getFunction(), context, true);

				int msgSev = FunctionMessages.MSG_NONE;
				if (msgSev == FunctionMessages.MSG_NONE)
				{
					childFh.getFhd().getFunctionInfo().setGenerateWarningInfo(true);
					msgSev = childFh.applyTransaction();

					// remove the child
					childFh = fhd.removeDrillDownChild();
				}

				// no error, then clear selection and exit with code=2
				if (msgSev == FunctionMessages.MSG_NONE)
				{
					dataManager.setDbValue(dataManager.rtvSelectionOptionId(), "");
					dataManager.setInputValue(dataManager.rtvSelectionOptionId(), "");
					return 2;
				}

				// display the message
				fhd.getFunctionMsgManager().getOtherMessages().insertMessages(
								childFh.getFhd().getFunctionMsgManager().getOtherMessages());
				fhd.getFunctionMsgManager().getFunctionMessages().insertMessages(
								childFh.getFhd().getFunctionMsgManager().getFunctionMessages());
				FunctionRuntimeToolbox.changeFieldName(fhd.getFunctionMsgManager().getOtherMessages(), dataManager
								.rtvSelectionOptionId(), dataManager.currentRowNumber() + 1);
				FunctionRuntimeToolbox.changeFieldName(fhd.getFunctionMsgManager().getFunctionMessages(), dataManager
								.rtvSelectionOptionId(), dataManager.currentRowNumber() + 1);
				fhd.setLastRepeatingGroup(repeatingGroupId);
				fhd.setLastRepeatingRow(dataManager.currentRowNumber() + 1);

				// exit with code 1, but do not clear the selection
				return 1;
			}
		}

		// blank it out
		dataManager.setDbValue(dataManager.rtvSelectionOptionId(), "");
		dataManager.setInputValue(dataManager.rtvSelectionOptionId(), "");
		return 1;
	}

	/**
	 * Performs validation prior to doing update
	 * 
	 * @return the message severity
	 */
	public int lastValidate() throws EQException
	{
		// perform checking again from screen 1 till this last screen in case the user has done some invalid mappings
		for (int i = 0; i < maxScrnNo; i++)
		{
			FunctionValidate fv = new FunctionValidate(fhd);
			fv.setDefaultValues(false);
			int n = fv.validate(i);

			// desktop session, any message display it
			if (EquationCommonContext.isDesktopSession(fhd.getFunctionInfo().getSessionType()))
			{
				if (n >= FunctionMessages.MSG_INFO)
				{
					scrnNo = i;
					functionMessages = fv.getFunctionMessages();
					return n;
				}
			}

			// Non-desktop, then stop if there is any error
			else if (n >= FunctionMessages.MSG_ERROR)
			{
				scrnNo = i;
				functionMessages = fv.getFunctionMessages();
				return n;
			}
		}

		// Perform API validation
		// FunctionUpdate functionUpdate = new FunctionUpdate(fhd);
		// functionUpdate.validate();
		// functionMessages.insertMessages(functionUpdate.getFunctionMessages());

		// return message severity
		return functionMessages.getMsgSev();
	}

	/**
	 * Calls the FieldSet initialisation script for any fields in the specified InputFieldSet
	 * <p>
	 * Note that as this script is not valid for InputFields in the Primary InputFieldSet, this method should not be called for the
	 * Primary InputFieldSet
	 * 
	 * @param screenNumber
	 *            the screen index
	 * 
	 * @return true if anything has been changed
	 */
	public boolean fieldSetInitialisationScriptProcessing(int screenNumber)
	{
		boolean changed = false;

		// retrieve the field set
		InputFieldSet inputFieldSet = function.getInputFieldSets().get(screenNumber);

		// read all the input fields
		for (InputField inputField : inputFieldSet.getInputFields())
		{
			String script = inputField.getFieldInitialisationScript().trim();
			if (script.length() > 0)
			{
				// An initialisation script exists for this field, so run it:
				String dbValue = ELRuntime.runStringScript(script, functionData, inputField.getId(), LanguageResources
								.getString("Language.FieldSetAssignment"), null, ELRuntime.DB_VALUE);
				if (dbValue != null && !dbValue.equals(functionData.rtvFieldInputValue(inputField.getId())))
				{
					changed = true;
					functionData.chgFieldDbValue(inputField.getId(), dbValue);
					LOG.debug("Setting primitive form of [" + inputField.getId() + "] to [" + dbValue
									+ "] from fieldset initialisation script");
				}
			}
		}

		// changed?
		return changed;
	}

	/**
	 * Perform paging
	 * 
	 * @param repeatingGroupid
	 *            - the repeating group id
	 * @param pageUp
	 *            - true if paging up
	 * 
	 * @return the paging details
	 */
	public RepeatingGroupPagingDetail doPaging(String repeatingGroupId, boolean pageUp) throws EQException
	{
		RepeatingGroupStatus repeatingGroupStatus = repeatingGroupStatusHandler.getRepeatingGroupStatuses(repeatingGroupId);

		int rrnTop = repeatingGroupStatus.getRrnTop();
		int rrnBottom = repeatingGroupStatus.getRrnBottom();

		// page up?
		if (pageUp)
		{
			repeatingGroupStatus.setRrnTop(-1);
			repeatingGroupStatus.setRrnBottom(rrnTop - 1);
		}

		// page down
		else
		{
			repeatingGroupStatus.setRrnTop(rrnBottom + 1);
			repeatingGroupStatus.setRrnBottom(-1);
		}

		// get the details
		InputFieldSet inputFieldSet = function.getInputFieldSets().get(scrnNo);
		DisplayGroup displayGroup = layout.getDisplayAttributesSets().get(scrnNo).rtvRepeatingGroup(repeatingGroupId);
		HTMLToolbox htmlToolbox = new HTMLToolbox();
		htmlToolbox.setScreenSet(this);
		return htmlToolbox.generateRepeatingGroupOnPaging(displayGroup, inputFieldSet, repeatingGroupStatus, pageUp);
	}

	/**
	 * Perform breakby processing
	 * 
	 * @param repeatingGroupid
	 *            - the repeating group id
	 * @param breakby
	 *            - breakby filter
	 * 
	 * @return the paging details
	 */
	public RepeatingGroupPagingDetail doBreakBy(String repeatingGroupId, String breakby) throws EQException
	{
		RepeatingGroupStatus repeatingGroupStatus = repeatingGroupStatusHandler.getRepeatingGroupStatuses(repeatingGroupId);
		repeatingGroupStatus.setBreakBy(breakby);
		repeatingGroupStatus.setRrnTop(0);
		repeatingGroupStatus.setRrnBottom(-1);
		RepeatingGroupPagingDetail pageDetail = doPaging(repeatingGroupId, false);
		pageDetail.setFirstRecord(true);
		return pageDetail;
	}

	/**
	 * Perform breakby processing
	 * 
	 * @param repeatingGroupid
	 *            - the repeating group id
	 * @param contextFields
	 *            - list of fields delimited by CONTEXT_DELIMTER
	 * 
	 * @return the paging details
	 */
	public RepeatingGroupPagingDetail doSorting(String repeatingGroupId, String contextFields) throws EQException
	{
		RepeatingGroupStatus repeatingGroupStatus = repeatingGroupStatusHandler.getRepeatingGroupStatuses(repeatingGroupId);

		// already sorted by these items, then reverse the order
		if (repeatingGroupStatus.getFieldOrder().equals(contextFields))
		{
			repeatingGroupStatus.reverseSort(repeatingGroupId, contextFields);
		}

		// sort it
		else
		{
			repeatingGroupStatus.sort(repeatingGroupId, contextFields);
		}

		repeatingGroupStatus.setRrnTop(0);
		repeatingGroupStatus.setRrnBottom(-1);
		RepeatingGroupPagingDetail pageDetail = doPaging(repeatingGroupId, false);
		pageDetail.setFirstRecord(true);
		return pageDetail;
	}

	/**
	 * Determine if key field is open for the screen set
	 * 
	 * @return true if key is open
	 */
	public boolean isKeyOpen()
	{
		return loadFieldSetStatusHandler.isKeyOpen(function.getInputFieldSets().get(0).getId());
	}

	// --------------------------------------------- THESE SET OF METHODS ARE MEANT TO BE OVERLOADED
	// --------------------------------------------- BY SUBCLASSES (if necessary)

	/**
	 * Called to do any processing prior to displaying this screen set upon entry <br>
	 * from previous screen set(e.g. paging down)
	 * 
	 * @param sourceScreenSetId
	 *            - screen set Id of the current screen set
	 * 
	 * @return SCRN_PREV - if the previous screen set should be displayed<br>
	 *         SCRN_NEXT - if the next screen set should be displayed<br>
	 *         SCRN_CUR - if this screen set should be displayed
	 */
	protected int onEntryScreenSetFromPrev(int sourceScreenSetId) throws EQException
	{
		firstTime = false;
		return SCRN_CUR;
	}

	/**
	 * Called to do any processing prior to displaying this screen set upon entry <br>
	 * from next screen set (e.g. paging up)
	 * 
	 * @param sourceScreenSetId
	 *            - screen set Id of the current screen set
	 * 
	 * @return SCRN_PREV - if the previous screen set should be displayed<br>
	 *         SCRN_NEXT - if the next screen set should be displayed<br>
	 *         SCRN_CUR - if this screen set should be displayed
	 * 
	 */
	protected int onEntryScreenSetFromNext(int sourceScreenSetId) throws EQException
	{
		return SCRN_CUR;
	}

	/**
	 * Called to do any processing prior to exiting this screen set <br>
	 * to the previous screen set(e.g. paging up)
	 * 
	 * @param sourceScreenSetId
	 *            - screen set Id of the current screen set
	 * 
	 * @return SCRN_PREV - if the previous screen set should be displayed<br>
	 *         0..n - display this screen set (if existing, otherwise similar to -1)
	 */
	protected int onExitScreenSetToPrev(int sourceScreenSetId) throws EQException
	{
		return SCRN_PREV;
	}

	/**
	 * Called to do any processing prior to exiting this screen set <br>
	 * to the next screen set(e.g. paging down)
	 * 
	 * @param sourceScreenSetId
	 *            - screen set Id of the current screen set
	 * 
	 * @return SCRN_NEXT - if the next screen set should be displayed<br>
	 *         SCRN_PREV - if the previous screen set should be displayed<br>
	 *         0..n - display this screen set (if existing, otherwise similar to -1)<br>
	 */
	protected int onExitScreenSetToNext(int sourceScreenSetId) throws EQException
	{
		return SCRN_NEXT;
	}

	/**
	 * Returns the current screen in HTML format<br>
	 * 
	 * @param htmlToolbox
	 *            - the HTML toolbox
	 * @param htmlDocument
	 *            - the HTML document
	 * 
	 * @return the HTML document of the current screen
	 * 
	 * @throws EQException
	 */
	protected HTMLElement rtvHTML(HTMLToolbox htmlToolbox, HTMLDocumentImpl htmlDocument) throws EQException
	{
		// generate the HTML element
		HTMLElement htmlElement = htmlToolbox.getScreenHTMLElement(htmlDocument, scrnNo);

		// return the HTML element
		return htmlElement;
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
	public int validate(int fromScrnNo, int toScrnNo) throws EQException
	{
		// create the validator
		FunctionValidate funcValidate = new FunctionValidate(fhd);

		// enquiry, allow all validation
		if (mode.equals(IEquationStandardObject.FCT_ENQ))
		{
			funcValidate.setHaltOnError(false);
			funcValidate.setValidateAllFixedFields(FunctionValidate.VAL_FIELD_NO);
			funcValidate.setValidateAllRepeatingFields(FunctionValidate.VAL_FIELD_NO);
		}

		// validate it
		int msgSev = funcValidate.validate(fromScrnNo, toScrnNo);

		// function messages
		functionMessages = funcValidate.getFunctionMessages();
		selectionExists = funcValidate.isSelectionExists();
		selectionExistsError = funcValidate.isSelectionExistsError();

		// if no major severity and validating the last screen
		if (msgSev < FunctionMessages.MSG_ERROR && toScrnNo == maxScrnNo - 1)
		{
			// perform limit check if user is not authorised to override it
			if (eqUser.getLimitOverride().equals(EqDataType.NO)
							&& eqUser.getEquationUnit().isEnhancementInstalled(Enhancement.K324))
			{
				String dsepms = FunctionRuntimeToolbox.isCRMLimitCheckError(equationStandardSession, this);
				if (dsepms.length() > 0)
				{
					functionMsgManager.generateMessage(equationStandardSession, functionMessages, screenSetHandler
									.getCurScreenSet(), toScrnNo, "", 0, null, dsepms, "", LanguageResources
									.getString("ScreenSet.ReturnedMessageFromCRMCheckLimit"), FunctionMessages.MSG_NONE);
				}
			}
		}

		return msgSev;
	}

	/**
	 * Performs update on the specified screen
	 * 
	 * @param journalHeader
	 *            - the Journal Header
	 * @param functionTransactions
	 *            - the list of transactions already executed prior to this update
	 * @param session
	 *            - the Equation standard session
	 * 
	 * @return true - this screen has already handled the update processing<br>
	 *         false - let the standard process perform update
	 * 
	 * @throws EQException
	 */
	public boolean update(JournalHeader journalHeader, FunctionTransactions functionTransactions, EquationStandardSession session)
					throws EQException
	{
		functionMessages.clearMessages();
		return false;
	}

	/**
	 * Generate the function key for the screen
	 * 
	 * @return the function keys
	 */
	protected FunctionKeys generateFkeys() throws EQException
	{
		if (securityLevel.getCheckerType() == SecurityLevel.CHCKR_NONE
						|| (securityLevel.getCheckerType() == SecurityLevel.CHCKR_MAKER_MAKER && (fhd.getFunctionSession()
										.getStatus().equals(WERecordDataModel.MAKER_CHECKER_STAT_AWAIT) || fhd.getFunctionSession()
										.getStatus().equals(WERecordDataModel.MAKER_CHECKER_STAT_REJCTD))))
		{
			functionKeys.generateFunctionKeys(mode, screenSetHandler, this, functionMsgManager.getFunctionMessages(),
							securityLevel, fhd.getGbRecord(), fhd.getAaiRecord(), functionSession, fhd.isAllowedSaveTemplate());
		}
		else if (securityLevel.getCheckerType() == SecurityLevel.CHCKR_SUPER)
		{
			functionKeys.generateSuperFunctionKeys(screenSetHandler, this, functionMsgManager.getFunctionMessages());
		}
		else if (securityLevel.getCheckerType() == SecurityLevel.CHCKR_MAKER_CHECKER)
		{
			functionKeys.generateSuperFunctionKeys(screenSetHandler, this, functionMsgManager.getFunctionMessages());
		}
		else if (securityLevel.getCheckerType() == SecurityLevel.CHCKR_MAKER_MAKER)
		{
			functionKeys.generateSuperFunctionKeys(screenSetHandler, this, functionMsgManager.getFunctionMessages());
		}
		return functionKeys;
	}

	/**
	 * Perform validation of the function key
	 * 
	 * @return true - this screen has already handled the validation of the function key<br>
	 *         false - let the standard process validate the function key
	 */
	protected boolean validateFkey()
	{
		return false;
	}

	/**
	 * Action the function key
	 * 
	 * @param ckey
	 *            - function key
	 * 
	 * @return 0 - redisplaying current screen <br>
	 *         1 - exit the function<br>
	 *         2 - go back to screen 1<br>
	 *         3 - let the standard process action the function key
	 */
	protected int actionFkey(int ckey) throws EQException
	{
		return FunctionRuntimeToolbox.PROC_LET_SYSTEM_DECIDE;
	}

	/**
	 * Return true if the screenset wants to do some validation
	 * 
	 * @param fromScrnNo
	 *            - from screen number
	 * @param toScrnNo
	 *            - to screen number
	 * 
	 * @return true if the screenset wants to do some validation
	 */
	protected boolean performValidateDuringNoUpdate(int fromScrnNo, int toScrnNo)
	{
		return false;
	}

	/**
	 * Called when a session (template) is restored
	 * 
	 */
	protected void restoreTemplate()
	{
	}

	/**
	 * Called when a session (work in progress) is restored
	 * 
	 */
	protected void restoreWorkProgress()
	{
	}

	/**
	 * Called when a session is restored by supervisor
	 * 
	 */
	protected void restoreSupervisor()
	{
	}

	/**
	 * Performs printing on the specified screen
	 * 
	 * @returns the list of lines
	 * 
	 * @throws EQException
	 */
	protected List<String> print() throws EQException
	{
		List<String> lines = new ArrayList<String>();

		// Set up the function printer
		FunctionPrinter functionPrinter = new FunctionPrinter(fhd);
		functionPrinter.setPrintHeader(true);
		functionPrinter.setPrintBlankLine(true);

		// Read all the screens the print it
		int n = fhd.getScreenSetHandler().getCurScreenSet();
		for (int i = 0; i < 1; i++)
		{
			fhd.getScreenSetHandler().setCurScreenSet(i);
			functionPrinter.print(this);
			lines.addAll(functionPrinter.getLines());
		}
		fhd.getScreenSetHandler().setCurScreenSet(n);

		return lines;
	}

	/**
	 * Perform ScreenSet specific field visibility routine
	 * 
	 * @param inputFieldSet
	 *            - the input field set where the field belongs to
	 * @param inputField
	 *            - the input field
	 * @param displayAttributesSet
	 *            - the display attributes set
	 * @param displayAttributes
	 *            - the display attributes
	 * @param currentStatus
	 *            - the current status of the field - visible (true) / invisible (false)
	 * 
	 * @return true if field must be visible
	 */
	public boolean fieldVisiblity(InputFieldSet inputFieldSet, InputField inputField, DisplayAttributesSet displayAttributeSet,
					DisplayAttributes displayAttributes, boolean currentStatus)
	{
		return currentStatus;
	}

	/**
	 * Perform ScreenSet specific field protected routine
	 * 
	 * @param inputFieldSet
	 *            - the input field set where the field belongs to
	 * @param inputField
	 *            - the input field
	 * @param displayAttributesSet
	 *            - the display attributes set
	 * @param displayAttributes
	 *            - the display attributes
	 * @param currentStatus
	 *            - the current status of the field - protected (true) / non-protected (false)
	 * 
	 * @return true if field must be protected
	 */
	public boolean fieldProtected(InputFieldSet inputFieldSet, InputField inputField, DisplayAttributesSet displayAttributesSet,
					DisplayAttributes displayAttributes, boolean currentStatus)
	{
		return currentStatus;
	}

	/**
	 * Perform ScreenSet specific field mandatory routine
	 * 
	 * @param inputFieldSet
	 *            - the input field set where the field belongs to
	 * @param inputField
	 *            - the input field
	 * @param displayAttributesSet
	 *            - the display attributes set
	 * @param displayAttributes
	 *            - the display attributes
	 * @param currentStatus
	 *            - the current status of the field - mandatory (true) / optional (false)
	 * 
	 * @return true if field must be protected
	 */
	public boolean fieldMandatory(InputFieldSet inputFieldSet, InputField inputField, boolean currentStatus)
	{
		return currentStatus;
	}

	/**
	 * Perform ScreenSet specific field defaulting routine. This is only executed only if the field remains blank
	 * 
	 * @param inputFieldSet
	 *            - the input field set where the field belongs to
	 * @param inputField
	 *            - the input field
	 * 
	 * @return true if field must be protected
	 */
	public String fieldDefaultValue(InputFieldSet inputFieldSet, InputField inputField)
	{
		return "";
	}

	/**
	 * Perform input field set initialisation
	 * 
	 * @param inputFieldSet
	 *            - the input field set
	 */

	public void initialiseInputFieldSet(InputFieldSet fieldSetId) throws EQException
	{
		functionAdaptor.getInputFieldSetAdaptor(equationStandardSession, fieldSetId).initialiseMode();
	}

	/**
	 * Convert the data into the BF complex type
	 * 
	 * @return the converted BF complex type
	 * 
	 * @throws EQException
	 */
	public Object cvtDataToBfData() throws EQException
	{
		ConversionRules conversionRules = FunctionRuntimeToolbox.getConversionRules(null, fhd);
		conversionRules.cvtToResponse(); // response xsd type to be passed to the WPS process

		FunctionBankFusion fb = new FunctionBankFusion();
		Object bf = fb.getBankFusionDataType(fhd.getEquationUser().getSessionForNonUpdate(), functionAdaptor, function,
						functionData, false, conversionRules);
		return bf;
	}

	/**
	 * Determine if this is a link service
	 * 
	 * @return true if this is a link service
	 */
	public boolean isLinkService()
	{
		return serviceLinkage != null;
	}

	/**
	 * Return the service linkage detail
	 * 
	 * @return the service linkage detail
	 */
	public ServiceLinkage getServiceLinkage()
	{
		return serviceLinkage;
	}

	/**
	 * Return the primary function if this is a link service. If not a link service, then just return the function
	 * 
	 * @return the primary function if this is a link service
	 */
	public Function getPrimaryFunction()
	{
		if (isLinkService())
		{
			return serviceLinkage.getPrimaryFunction();
		}
		else
		{
			return getFunction();
		}
	}

	/**
	 * Check sum processing
	 * 
	 * @throws EQException
	 */
	public void checkSumProcessing() throws EQException
	{
		ServiceRqHeader serviceRqHeader = fhd.getServiceRqHeader();

		// no service header
		if (serviceRqHeader == null)
		{
			return;
		}

		// only do checksum processing when in retrieve mode or checksum has been specified
		if (serviceRqHeader.getServiceMode().equals(IEquationStandardObject.FCT_RTV) || serviceRqHeader.getChecksum() != null
						&& serviceRqHeader.getChecksum().trim().length() > 0)
		{
		}
		else
		{
			return;
		}

		// initialise list of checksum filter
		FieldFilterLocator fieldFilterLocator = new FieldFilterLocator();
		fieldFilterLocator.setFieldFilter(serviceRqHeader.getChecksumFilter(), "");

		// Exclude all?
		if (fieldFilterLocator.isExcludeAll())
		{
			return;
		}

		// derive the checksum (note: the list of checksum refers to response XSD)
		XSDStructureLink link = XMLToolbox.getXMLToolbox().getXSDStructureLink(equationStandardSession.getUnitId(),
						function.getId());
		functionData.rtvChecksum(function, fieldFilterLocator, false, link);

		// has checksum been specified in the header?
		if (serviceRqHeader.getChecksum() != null && serviceRqHeader.getChecksum().trim().length() > 0)
		{
			// check sum different?
			if (!functionData.getChecksum().equals(serviceRqHeader.getChecksum()))
			{
				String dsepms = Toolbox.getDSEPMS("KSM7340", LanguageResources.getString("FunctionUpdate.CheckSumDoesNotMatch"),
								"", "");
				functionMsgManager.generateMessage(equationStandardSession, functionMessages, screenSetHandler.getCurScreenSet(),
								scrnNo, "", 0, null, dsepms, "", "", FunctionMessages.MSG_NONE);
			}
		}
	}

}