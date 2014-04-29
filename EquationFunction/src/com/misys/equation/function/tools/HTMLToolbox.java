/*
 * 
 */
package com.misys.equation.function.tools;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.html.dom.HTMLBodyElementImpl;
import org.apache.html.dom.HTMLDivElementImpl;
import org.apache.html.dom.HTMLDocumentImpl;
import org.apache.html.dom.HTMLElementImpl;
import org.apache.html.dom.HTMLInputElementImpl;
import org.apache.html.dom.HTMLScriptElementImpl;
import org.apache.html.dom.HTMLTableCellElementImpl;
import org.apache.html.dom.HTMLTableElementImpl;
import org.apache.html.dom.HTMLTableRowElementImpl;
import org.apache.xml.serialize.HTMLSerializer;
import org.apache.xml.serialize.OutputFormat;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.html.HTMLBodyElement;
import org.w3c.dom.html.HTMLElement;

import com.ibm.as400.access.BidiStringType;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationLogin;
import com.misys.equation.common.access.EquationPVDecodeMetaData;
import com.misys.equation.common.access.EquationPVFieldMetaData;
import com.misys.equation.common.access.EquationPVMetaData;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.access.EquationWidget;
import com.misys.equation.common.dao.beans.GAZRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EqTimingTest;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.EquationPVMetaDataCache;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.adaptor.AttributesAdaptor;
import com.misys.equation.function.adaptor.DisplayButtonLinkAdaptor;
import com.misys.equation.function.adaptor.DisplayGroupAdaptor;
import com.misys.equation.function.adaptor.DisplayLabelAdaptor;
import com.misys.equation.function.adaptor.FieldAdaptor;
import com.misys.equation.function.adaptor.FunctionAdaptor;
import com.misys.equation.function.adaptor.LayoutAdaptor;
import com.misys.equation.function.adaptor.ValueAdaptor;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayButtonLink;
import com.misys.equation.function.beans.DisplayGroup;
import com.misys.equation.function.beans.DisplayItemList;
import com.misys.equation.function.beans.DisplayLabel;
import com.misys.equation.function.beans.Element;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.FieldData;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.IDisplayItem;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.Layout;
import com.misys.equation.function.beans.LinkedFunction;
import com.misys.equation.function.beans.LoadFieldSetStatus;
import com.misys.equation.function.beans.Mapping;
import com.misys.equation.function.beans.PVField;
import com.misys.equation.function.beans.PVFieldSet;
import com.misys.equation.function.beans.RepeatingDataManager;
import com.misys.equation.function.beans.RepeatingFieldData;
import com.misys.equation.function.beans.RepeatingGroupStatus;
import com.misys.equation.function.beans.WorkField;
import com.misys.equation.function.el.ELRuntime;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.runtime.DisplayGroupHandler;
import com.misys.equation.function.runtime.FunctionDebugInfo;
import com.misys.equation.function.runtime.FunctionHandlerData;
import com.misys.equation.function.runtime.FunctionMessage;
import com.misys.equation.function.runtime.FunctionMessages;
import com.misys.equation.function.runtime.LoadFieldSetStatusHandler;
import com.misys.equation.function.runtime.RepeatingGroupStatusHandler;
import com.misys.equation.function.runtime.ScreenSet;
import com.misys.equation.function.runtime.SecurityLevel;
import com.misys.equation.function.runtime.ShutterGroup;
import com.misys.equation.function.runtime.ShutterGroupHandler;
import com.misys.equation.function.useraccess.UserExitListColumnData;

/**
 * 
 */
public class HTMLToolbox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: HTMLToolbox.java 17505 2013-11-04 13:01:55Z williae1 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(FunctionToolbox.class);

	// the name of the function handler
	public final static String OBJ_NAME = "___nameElement";

	// the field set name of the current screen
	public final static String OBJ_FIELDSET = "___fieldSetElement";

	// the field to position the cursor
	public final static String OBJ_CURSOR = "___cursorElement";

	// the first input element field
	public final static String OBJ_FIRSTFIELD = "___firstFieldElement";

	// the last input element field
	public final static String OBJ_LASTFIELD = "___lastFieldElement";

	// the number of screen
	public final static String OBJ_SCNO = "___scnoElement";

	// the current screen
	public final static String OBJ_CUR_SCRN = "___curScrnElement";

	// the function mode
	public final static String OBJ_FCT = "___fctElement";

	// the specific field to validate
	public final static String OBJ_FLDVAL = "___fldValElement";

	// the message severity
	public final static String OBJ_MSGSEV = "___msgSevElement";

	// the function key pressed
	public final static String OBJ_FKEY = "___fkeyElement";

	// the checker mode
	public final static String OBJ_CHCKR = "___checkerElement";

	// the number of messages issued
	public final static String OBJ_NMSGS = "___nMsgsElement";

	// requires referral to this type of checker
	public final static String OBJ_RCHKR = "___reqCheckerElement";

	// supervisor message element
	public final static String OBJ_SUPMSG = "___supervisorMsg";

	// refer to user id
	public final static String OBJ_REFERTOUSERID = "___referToUserId";

	// valid function keys
	public final static String OBJ_VFKEYS = "___validFkeysElement";

	// function key text
	public final static String OBJ_FKYTXT = "___funcKeyTextElement";

	// function key text full list
	public final static String OBJ_FFKYTXT = "___fullFuncKeyTextElement";

	// update error?
	public final static String OBJ_UPDERROR = "___updateErrorElement";

	// transaction id
	public final static String OBJ_TRANID = "___tranIdElement";

	// GP System ID
	public final static String OBJ_SYSTEMID = "___systemIdElement";

	// GP Unit ID
	public final static String OBJ_UNITID = "___unitIdElement";

	// load information
	public final static String OBJ_LOADFIELDSET = "___loadFieldSetElement";
	public final static String OBJ_LOADFIELD = "___loadFieldElement";

	// list of messages
	public final static String OBJ_MSGIDS = "___msgIdsElement";
	public final static String OBJ_MSGAMT = "___msgAmtElement";
	public final static String OBJ_MSGBRNM = "___msgBrnmElement";
	public final static String OBJ_MSGDRCR = "___msgDrCrElement";

	// default supervisor
	public final static String OBJ_DEFSUPER = "___defaultSuper_";

	// display group status
	public final static String OBJ_DSPGRPID = "___dspGrpId_";

	// key field set status
	public final static String OBJ_KEYEXSTSET = "___keyExistElement";
	public final static String OBJ_KEYDSPSET = "___keyDisplayElement";

	// job id
	public final static String OBJ_JOBID = "___jobIdElement";

	// option id
	public final static String OBJ_OPTIONID = "___optionIdElement";

	// CRM S-authorisation
	public final static String OBJ_CRM_S = "___crmSAuthElement";

	// label field
	public final static String ID_LABEL = "___Label";

	// output field
	public final static String ID_OUTPUT = "$$$Output";

	// database field
	public final static String ID_DB = "$$$DB";

	// rtl field
	public final static String ID_RTL = "___RTL";

	// task id
	public final static String OBJ_TASKID = "___taskID";

	// task's process id
	public final static String OBJ_TASKPROCID = "___taskProcessID";

	// task type
	public final static String OBJ_TASKTYPE = "___taskType";

	// completed task
	public final static String OBJ_COMPLETETASK = "___completedTask";

	// These are work variables for the main table
	public final static String DIV_MAIN = "mainTD";
	public final static String DIV_KEY = "___mainDivKey";
	public final static String DIV_DETAIL = "___mainDivDetail";
	public final static String DIV_WORKFIELDS = "___mainDivWorkFields";
	public final static String TABLE_KEY = "___TABLE_KEY_";
	public final static String TABLE_DETAIL = "___TABLE_DETAIL_";
	public final static String TABLE_WORKFIELD = "___TABLE_WORKFIELD_";
	public final static String TD1_PREFIX = "___TD_01_";
	public final static String TD2_PREFIX = "___TD_02_";
	public final static String TD3_PREFIX = "___TD_03_";
	public final static String TD4_PREFIX = "___TD_04_";
	public final static String TD5_PREFIX = "___TD_05_";
	public final static String TD6_PREFIX = "___TD_06_";
	public final static String TD7_PREFIX = "___TD_07_";
	private int tableNumber;

	// These are the work variables for the internal table
	public final static String ROW_SUFFIX = "___ROW";

	// These are other work variables
	public final static String GROUPCONTROL_ROW = "___GRP_ROW";
	public final static String GROUPBUTTONS_DIV = "___GRP_BUTTONS_DIV";
	public final static String GROUPTABS_DIV = "___GRP_TABS_DIV";
	public final static String GROUPBUTTONS_TABLE = "___GRP_BUTTONS_TABLE";
	public final static String GROUPTABS_TABLE = "___GRP_TABS_TABLE";
	public final static String GROUPTABS_CELL = "___GRP_TABCELL_";

	// These are work variables for repeating fields
	public final static String LISTGROUPHDR = "___DIVGROUP_HEADER_";
	public final static String LISTGROUPLST = "___DIVGROUP_DATA_";
	public final static String LISTGROUPFTR = "___DIVGROUP_FOOTER_";
	public final static String LISTTABLEHDR = "___TABLEGROUP_HEADER_";
	public final static String LISTTABLELST = "___TABLEGROUP_DATA_";
	public final static String LISTTABLEFTR = "___TABLEGROUP_FOOTER_";
	public final static String REPEAT_HDR = "___TDLIST_HEADER_";
	public final static String REPEAT_LST = "___TDLIST_DATA_";
	public final static String REPEAT_FTR = "___TDLIST_FOOTER_";
	public final static String GRP_FLD = "___";

	// Default column number
	public final static int DEFAULT_COL = 3;

	/** HTML Class that specifies non-display */
	public final static String NON_DISPLAY_CLASS1 = "wf_NDP";
	public final static String NON_DISPLAY_CLASS2 = "wf_DSPNONE";
	public final static String NON_DISPLAY_CLASS = NON_DISPLAY_CLASS1 + " " + NON_DISPLAY_CLASS2;
	public final static String NON_RESIZE = "NORESIZE";

	// Display group information
	public final static String GROUP_SHTR_OPEN = "___SHTR_OPN";

	// Repeating group information
	public final static String REPGROUP_FLDSORT = "___FLDSORT";
	public final static String REPGROUP_ORDERSORT = "___ORDERSORT";
	public final static String REPGROUP_NROWS = "___NROWS";
	public final static String REPGROUP_INPBREAKBY = "___INPBREAKBY";
	public final static String REPGROUP_SELBREAKBY = "___SELBREAKBY";
	public final static String REPGROUP_INVISIBLE = "___INVISIBLE";
	public final static String REPGROUP_BUTUP = "___BUTUP";
	public final static String REPGROUP_BUTDOWN = "___BUTDOWN";
	public final static String REPGROUP_BUTPROG = "___BUTPROG";
	public final static String REPGROUP_OPTION1 = "___OPT1";
	public final static String REPGROUP_OPTION2 = "___OPT2";

	public final static String HTML_NBSP = "\u00a0";

	private final static int TOP_MOST_ROW = 1;
	private final static int BOTTOM_MOST_ROW = 2;
	private final static int ABOVE_ROW = 3;
	private final static int BELOW_ROW = 4;

	private final static String ROW_INDEX_END = "999999999";

	// the screen set
	private ScreenSet screenSet;

	// the function data
	private FunctionData functionData;

	// checker mode
	private SecurityLevel securityLevel;

	// debug mode
	private boolean debugMode;

	// right to left?
	private boolean rtl;

	// Function handler data
	private FunctionHandlerData fhd; // do not access any properties inside fhd

	// Function
	private Function function;

	// Layout
	private Layout layout;

	// session
	private EquationStandardSession session = null;

	// Function Adaptor
	private FunctionAdaptor functionAdaptor;

	// Layout adaptor
	private LayoutAdaptor layoutAdaptor;

	// Display input field set handler
	private LoadFieldSetStatusHandler loadFieldSetStatusHandler;

	// Display group field handler
	private DisplayGroupHandler displayGroupHandler;

	// Shutter handler
	private ShutterGroupHandler shutterHandler;

	// Repeating group status handler
	private RepeatingGroupStatusHandler repeatingGroupStatusHandler;

	// Work fields during loading of function data
	private String firstInputField = "";
	private String lastInputField = "";
	private String firstInputFieldInError = "";
	private boolean anyFieldMsgInShutter = false;
	private int anyFieldMsgInGroup = FunctionMessages.MSG_NONE;

	// Function debug info
	private FunctionDebugInfo functionDebugInfo;

	// Shutter displayed as closed
	private final List<String> closeShutterIds;

	// Repeating display group sort on display
	private final List<String> repeatingGroupSortByField;

	// List of fields with onblurscript
	private final List<String> onBlurFieldIds;

	// List of repeating groups
	private final List<String> listRepeatingGroups;

	// List of repeating group cell
	private final List<String> listRepeatingGroupsCells;

	// Eq User
	private EquationUser eqUser;

	/** Variable used to pass information about the current row (if any) */
	private String rowId = null;

	// Use to generate a unique id
	private int nextId = 0;

	// break by toolbox
	private BreakByToolbox breakByToolbox;

	// linked function toolbox
	private LinkedFunctionsToolbox linkedFunctionTool;

	// Number of PV records to be displayed
	private int pvResult;

	// Maximum number of fields
	private int maximumNumberField;

	// Current number of fields
	private int currentNumberField;
	private boolean exceedNumberFieldLimit;

	// Session's ccsid
	private int ccsid;

	// Determines whether ccsid supports rtl
	private boolean rtlCcsid;

	// FunctionMessages
	private FunctionMessages functionMessages;

	// UXP mode?
	private boolean uxp = false;

	// Fixed header
	private boolean fixedListHeader;

	/*
	 * Construct a new HTML generator
	 */
	public HTMLToolbox()
	{
		functionData = null;
		debugMode = false;
		rtl = false;
		closeShutterIds = new ArrayList<String>();
		onBlurFieldIds = new ArrayList<String>();
		listRepeatingGroups = new ArrayList<String>();
		listRepeatingGroupsCells = new ArrayList<String>();
		repeatingGroupSortByField = new ArrayList<String>();

		pvResult = Toolbox.parseInt(EquationCommonContext.getConfigProperty("eq.layout.PVResult"), 16);
		maximumNumberField = Toolbox.parseInt(EquationCommonContext.getConfigProperty("eq.layout.MaximumInputFields"), 3000);
		fixedListHeader = EquationCommonContext.getConfigProperty("eq.list.fixedHeader").equals("true");

		currentNumberField = 0;
	}

	/**
	 * Set the screen set to generate the screen from
	 */
	public void setScreenSet(ScreenSet screenSet) throws EQException
	{
		this.screenSet = screenSet;
		this.fhd = screenSet.getFhd();
		this.function = screenSet.getFunction();
		this.layout = screenSet.getLayout();
		this.securityLevel = screenSet.getFhd().getSecurityLevel();
		this.debugMode = screenSet.getFhd().getFunctionInfo().isDebugMode();
		this.rtl = screenSet.getFhd().getEquationUser().isLanguageRTL();
		this.functionData = screenSet.getFunctionData();
		this.session = screenSet.getFhd().getEquationUser().getSessionForNonUpdate();
		this.loadFieldSetStatusHandler = screenSet.getLoadFieldSetStatusHandler();
		this.displayGroupHandler = screenSet.getDisplayGroupHandler();
		this.repeatingGroupStatusHandler = screenSet.getRepeatingGroupStatusHandler();
		this.shutterHandler = screenSet.getShutterHandler();
		this.functionAdaptor = screenSet.getFunctionAdaptor();
		this.layoutAdaptor = screenSet.getLayoutAdaptor();
		this.functionDebugInfo = fhd.getFunctionDebugInfo();
		this.eqUser = screenSet.getFhd().getEquationUser();
		this.ccsid = session.getCcsid();
		this.rtlCcsid = EquationCommonContext.isRtlCcsid(ccsid);

		this.fhd.getFunctionMsgManager().getFunctionMessages().populateAllFunctionMessages();
		this.functionMessages = this.fhd.getFunctionMsgManager().getFunctionMessages();

		EquationLogin equationLogin = EquationCommonContext.getContext().getEquationLogin(fhd.getFunctionInfo().getSessionId());
		if (equationLogin != null)
		{
			uxp = equationLogin.chkUXPUserInterface();
		}
	}

	/**
	 * Set the Function Data
	 * 
	 * @param functionData
	 *            - the Function Data
	 */
	public void setFunctionData(FunctionData functionData)
	{
		this.functionData = functionData;
	}

	/**
	 * Set the Equation standard session
	 * 
	 * @param session
	 *            - the Equation standard session
	 */
	public void setSession(EquationStandardSession session)
	{
		this.session = session;
	}

	/**
	 * Set the Equation user
	 * 
	 * @param eqUser
	 *            - the Equation user
	 */
	public void setEqUser(EquationUser eqUser)
	{
		this.eqUser = eqUser;
	}

	/**
	 * Generate the HTML screen of the main field set
	 * 
	 * @param htmlDocument
	 *            - HTML document
	 * @param scrnNo
	 *            - screen number (0..x)
	 * 
	 * @return the HTML representation of the field set
	 */
	public HTMLElementImpl getScreenHTMLElement(HTMLDocumentImpl htmlDocument, int scrnNo) throws EQException
	{
		// debug
		functionDebugInfo.printHTMLScreen(scrnNo);
		EqTimingTest.printStartTime("HTMLToolbox.getScreenHTMLElement()", "");

		// initialise
		this.firstInputField = "";
		this.lastInputField = "";
		this.firstInputFieldInError = "";
		this.anyFieldMsgInShutter = false;

		// retrieve the current screen
		InputFieldSet inputFieldSet = function.getInputFieldSets().get(scrnNo);

		// retrieve the current screen
		DisplayAttributesSet displayAttributeFieldSet = layout.getDisplayAttributesSets().get(scrnNo);

		// create the body
		HTMLBodyElement body = createBodyElement(htmlDocument, "");

		// generate the html of this field set
		HTMLElementImpl element = getFieldSetHTMLElement(htmlDocument, inputFieldSet, displayAttributeFieldSet, false, 0);

		// generate the html of the work fields
		element.appendChild(getWorkFieldsHTMLElement(htmlDocument, function.getWorkFields()));

		// include only other fields if displaying the details
		LoadFieldSetStatus fieldSetStatus = loadFieldSetStatusHandler.getFieldSetStatus(inputFieldSet.getId());
		if (fieldSetStatus.isDetailOpen())
		{
			// add the fields from the other screen
			for (int i = 0; i < function.getInputFieldSets().size(); i++)
			{
				if (i != scrnNo)
				{
					element.appendChild(getFieldSetHTMLElement(htmlDocument, function.getInputFieldSets().get(i)));
				}
			}
		}

		// add the elements to the document
		htmlDocument.appendChild(body).appendChild(element);

		EqTimingTest.printTime("HTMLToolbox.getScreenHTMLElement()", "");
		return element;
	}

	/**
	 * Generate the HTML representation of a field set
	 * 
	 * @param htmlDocument
	 *            - HTML document
	 * @param inputFieldSet
	 *            - the input field set
	 * @return An HTMLElementImpl representing the InputFieldSet
	 */
	private HTMLElementImpl getFieldSetHTMLElement(HTMLDocumentImpl htmlDocument, InputFieldSet inputFieldSet)
	{
		HTMLDivElementImpl div = createDivElement(htmlDocument, "");
		div.setClassName(NON_DISPLAY_CLASS);

		for (int i = 0; i < inputFieldSet.getInputFields().size(); i++)
		{
			InputField inputField = inputFieldSet.getInputFields().get(i);
			FieldData fieldData = functionData.rtvFieldData(inputField.getId());
			if (!(fieldData instanceof RepeatingFieldData))
			{
				addHiddenFieldValue(htmlDocument, div, fieldData);
			}

		}
		return div;
	}

	/**
	 * Add the field values as hidden element
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param containerElement
	 *            - the container to contain the hidden HTML element
	 * @param fieldData
	 *            - the field data
	 */
	private void addHiddenFieldValue(HTMLDocumentImpl htmlDocument, HTMLElement containerElement, FieldData fieldData)
	{
		// field name
		String fieldName = getInputFieldId(fieldData.getFieldName());

		// input
		HTMLInputElementImpl input = createHiddenInputElement(htmlDocument, fieldName, false);
		input.setValue(fieldData.getInputValue());
		containerElement.appendChild(input);

		// db
		if (!EqDataType.isAlpha(fieldData.getFieldType()))
		{
			HTMLInputElementImpl dbElement = createHiddenInputElement(htmlDocument, fieldName + ID_DB, false);
			dbElement.setValue(fieldData.getDbValue());
			containerElement.appendChild(dbElement);
		}

		// output
		HTMLElement outputElement = createDefaultSpanElement(htmlDocument, fieldName + ID_OUTPUT, "");
		outputElement.appendChild(htmlDocument.createTextNode(fieldData.rtvOutputMaskValue(functionData, "", false)));
		containerElement.appendChild(outputElement);
	}

	/**
	 * Generate the HTML representation of a field set
	 * 
	 * @param htmlDocument
	 *            - HTML document
	 * @param inputFieldSet
	 *            - the input field set
	 * @param displayAttributeFieldSet
	 *            - the display attributes field set of the input field set
	 * @param parentKeyOpen
	 *            - is the parent's key field open?
	 * @param iteration
	 *            - nth iteration
	 * 
	 * @throws EQException
	 */
	private HTMLElementImpl getFieldSetHTMLElement(HTMLDocumentImpl htmlDocument, InputFieldSet inputFieldSet,
					DisplayAttributesSet displayAttributeFieldSet, boolean parentKeyOpen, int iteration) throws EQException
	{
		// // overwrite the display attribute's description
		// displayAttributeFieldSet.setDescription(inputFieldSet.getDescription());
		// displayAttributeFieldSet.setDescriptionType(inputFieldSet.getDescriptionType());

		// is the key of the field set open?
		LoadFieldSetStatus fieldSetStatus = loadFieldSetStatusHandler.getFieldSetStatus(inputFieldSet.getId());

		// supervisor, then always display key and detail
		if (securityLevel.getCheckerType() == SecurityLevel.CHCKR_SUPER)
		{
			fieldSetStatus.setDetailOpen(true);
		}

		// main div
		HTMLDivElementImpl mainDiv = createDivElement(htmlDocument, DIV_MAIN);
		mainDiv.setClassName("inputArea MainDivClass");

		// get all the fields of the field set
		DisplayItemList displayItems = displayAttributeFieldSet.getDisplayItems();

		// display all the key fields
		tableNumber = 0;
		if (fieldSetStatus.isKeyExist())
		{
			HTMLElementImpl divKeyDetails = getDisplayItemsHtmlElement(htmlDocument, TABLE_KEY + inputFieldSet.getId(),
							inputFieldSet, displayAttributeFieldSet, displayItems, fieldSetStatus, parentKeyOpen, true);
			divKeyDetails.setId(DIV_KEY);
			divKeyDetails.setClassName(divKeyDetails.getClassName() + " " + "KeyGroupClass");
			mainDiv.appendChild(divKeyDetails);
		}

		// only include non-key details if it is currently going to be displayed
		if (fieldSetStatus.isDetailOpen())
		{
			// display all the non-key fields
			HTMLElementImpl divNonKeyDetails = getDisplayItemsHtmlElement(htmlDocument, TABLE_DETAIL + inputFieldSet.getId(),
							inputFieldSet, displayAttributeFieldSet, displayItems, fieldSetStatus, parentKeyOpen, false);
			divNonKeyDetails.setId(DIV_DETAIL);
			divNonKeyDetails.setClassName(divNonKeyDetails.getClassName() + " " + "DetailGroupClass");

			// add the non-key fields
			mainDiv.appendChild(divNonKeyDetails);
		}

		return mainDiv;
	}

	/**
	 * Generate the key or non-key HTML representation of a field set
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param tableId
	 *            - the table Id
	 * @param inputFieldSet
	 *            - the input field set
	 * @param displayAttributeFieldSet
	 *            - the display attribute field set
	 * @param displayItems
	 *            - the list of items of this field set
	 * @param fieldSetStatus
	 *            - the field set status whether the key or detail is open or close
	 * @param parentKeyOpen
	 *            - whether the parent field is open (not implemented)
	 * @param isKey
	 *            - whether to display key fields or not
	 * 
	 * @return the HTML representation
	 * 
	 * @throws EQException
	 */
	private HTMLElementImpl getDisplayItemsHtmlElement(HTMLDocumentImpl htmlDocument, String tableId, InputFieldSet inputFieldSet,
					DisplayAttributesSet displayAttributeFieldSet, DisplayItemList displayItems, LoadFieldSetStatus fieldSetStatus,
					final boolean parentKeyOpen, final boolean isKey) throws EQException
	{
		// print timing test
		EqTimingTest.printStartTime("HTMLToolbox.getDisplayItemsHtmlElement()", "");

		// work fields
		HTMLDivElementImpl div = createDivElement(htmlDocument, "");

		// set the table
		HTMLTableElementImpl table = null;

		HTMLElementImpl rowElement;

		// Must always create first field in group (KeepWithPrevious doesn't work across group boundaries).
		boolean anyFields = false;
		boolean anyButtonLinks = false;
		boolean anyLabels = false;

		// display all the key fields
		for (int i = 0; i < displayItems.size(); i++)
		{
			// Input field
			if (displayItems.get(i) instanceof DisplayAttributes)
			{
				DisplayAttributes displayAttributes = (DisplayAttributes) displayItems.get(i);
				InputField inputField = inputFieldSet.getInputField(displayAttributes.getId());
				if (inputField.isKey() == isKey)
				{
					if (!anyFields || !displayAttributes.isKeepWithPrevious())
					{
						// table has not been created, then create one
						if (table == null)
						{
							tableNumber++;
							table = createTableElement(htmlDocument, tableId + tableNumber, "");
							table.setAttribute("defTable", EqDataType.YES);
							table.setClassName(table.getClassName() + " " + "NonGroupTableClass");
							table.appendChild(getFirstRow(htmlDocument, tableId + tableNumber, inputFieldSet.getId() + tableNumber,
											displayAttributeFieldSet.rtvLabel(eqUser), displayAttributeFieldSet
															.rtvDescription(eqUser), DEFAULT_COL));
							table.appendChild(createDefaultRowSpacer(htmlDocument, "", "", DEFAULT_COL));
							anyFieldMsgInShutter = false;
						}

						rowElement = getFieldHTMLElement(htmlDocument, inputFieldSet, inputField, displayAttributeFieldSet,
										displayAttributes, displayItems, i, !fieldSetStatus.isDetailOpen(), parentKeyOpen);
						anyFields = true;
						table.appendChild(rowElement);
					}
				}
			}

			// Label
			else if (displayItems.get(i) instanceof DisplayLabel)
			{
				DisplayLabel displayLabel = (DisplayLabel) displayItems.get(i);
				// retrieve the display label adaptor
				DisplayLabelAdaptor displayLabelAdaptor = layoutAdaptor.getDisplayLabelAdaptor(session, displayLabel.rtvBareId());
				// process only when visible
				if (displayLabelAdaptor.isVisible(displayLabel))
				{
					// Only display the DisplayLabel if the key state matches
					boolean isThisInKeyGroup = isDisplayLabelInKeyGroup(displayLabel);
					if (isThisInKeyGroup == isKey)
					{
						// table has not been created, then create one
						if (table == null)
						{
							tableNumber++;
							table = createTableElement(htmlDocument, tableId + tableNumber, "");
							table.setAttribute("defTable", EqDataType.YES);
							table.setClassName(table.getClassName() + " " + "NonGroupTableClass");
							table.appendChild(getFirstRow(htmlDocument, tableId + tableNumber, inputFieldSet.getId() + tableNumber,
											displayAttributeFieldSet.rtvLabel(eqUser), displayAttributeFieldSet
															.rtvDescription(eqUser), DEFAULT_COL));
							table.appendChild(createDefaultRowSpacer(htmlDocument, "", "", DEFAULT_COL));
							anyFieldMsgInShutter = false;
						}

						rowElement = getDisplayLabelHTMLElement(htmlDocument, displayAttributeFieldSet, displayLabel, displayItems,
										i);
						table.appendChild(rowElement);
						anyLabels = true;
					}
				}
			}

			// Button or Link
			else if (displayItems.get(i) instanceof DisplayButtonLink)
			{
				DisplayButtonLink displayButtonLink = (DisplayButtonLink) displayItems.get(i);

				// Only display the DisplayButtonLink if the key state matches
				boolean isThisInKeyGroup = isDisplayButtonLinkInKeyGroup(displayButtonLink);
				if (isThisInKeyGroup == isKey)
				{
					if (!(anyFields || anyButtonLinks || anyLabels) || !displayButtonLink.isKeepWithPrevious())
					{
						// retrieve the display button link adaptor
						DisplayButtonLinkAdaptor displayButtonLinkAdaptor = layoutAdaptor.getDisplayButtonLinkAdaptor(session,
										displayButtonLink.rtvBareId());
						// process only when visible
						if (displayButtonLinkAdaptor.isVisible(displayButtonLink))
						{

							// table has not been created, then create one
							if (table == null)
							{
								tableNumber++;
								table = createTableElement(htmlDocument, tableId + tableNumber, "");
								table.setAttribute("defTable", EqDataType.YES);
								table.setClassName(table.getClassName() + " " + "NonGroupTableClass");
								table.appendChild(getFirstRow(htmlDocument, tableId + tableNumber, inputFieldSet.getId()
												+ tableNumber, displayAttributeFieldSet.rtvLabel(eqUser), displayAttributeFieldSet
												.rtvDescription(eqUser), DEFAULT_COL));
								table.appendChild(createDefaultRowSpacer(htmlDocument, "", "", DEFAULT_COL));
								anyFieldMsgInShutter = false;
							}

							rowElement = getDisplayButtonLinkHTMLElement(displayButtonLinkAdaptor, htmlDocument,
											displayAttributeFieldSet, displayButtonLink, displayItems, i, 1);
							table.appendChild(rowElement);

							anyButtonLinks = true;
						}
					}
				}

			}

			// Group
			else
			{
				// get the display group
				DisplayGroup displayGroup = (DisplayGroup) displayItems.get(i);

				// retrieve the display group adaptor
				DisplayGroupAdaptor displayGroupAdaptor = layoutAdaptor.getDisplayGroupAdaptor(session, displayGroup.rtvBareId(),
								"");

				// process only when visible
				if (displayGroupAdaptor.isVisible(displayGroup))
				{
					// create a new table for the group
					tableNumber++;
					HTMLTableElementImpl groupTable = createTableElement(htmlDocument, tableId + tableNumber, "");
					groupTable.setClassName(groupTable.getClassName() + " " + "GroupTableClass");
					boolean anyFieldMsgInShutter2 = anyFieldMsgInShutter;
					anyFieldMsgInShutter = false;

					// add the first row of the table
					groupTable.appendChild(getFirstRow(htmlDocument, tableId + tableNumber, displayGroup.getId(),
									FunctionRuntimeToolbox.getLabel(eqUser, displayGroup, functionData), displayGroup
													.rtvDescription(eqUser), DEFAULT_COL, true));
					groupTable.appendChild(createDefaultRowSpacer(htmlDocument, "", "", DEFAULT_COL));

					// traverse all the fields of the group
					getDisplayGroupItemsHtmlElement(htmlDocument, groupTable, inputFieldSet, displayAttributeFieldSet,
									displayGroup, fieldSetStatus, parentKeyOpen, isKey, "", 0);

					// include a blank row
					groupTable.appendChild(createDefaultRowSpacer(htmlDocument, "", "", DEFAULT_COL));

					// add this group table - does the group have any displayable fields?
					// note: this only happens if a group has all key fields or non-key fields
					// note: the group name is always added + 2 empty rows, hence there are always at least 3 children
					if (groupTable.getChildNodes().getLength() > 3)
					{
						// add the previous table to the div
						if (table != null)
						{
							table.appendChild(createDefaultRowSpacer(htmlDocument, "", "", DEFAULT_COL));
							div.appendChild(createDefaultGroupDiv(htmlDocument, "", table, false, ""));
							if (!anyFieldMsgInShutter2)
							{
								processShutter(htmlDocument, tableId + (tableNumber - 1),
												inputFieldSet.getId() + (tableNumber - 1), true);
							}
						}

						div.appendChild(createDefaultGroupDiv(htmlDocument, "", groupTable, false, ""));
						if (!anyFieldMsgInShutter)
						{
							processShutter(htmlDocument, tableId + tableNumber, displayGroup.getId(), displayGroup.isDefaultOpen());
						}

						table = null;
						anyFieldMsgInShutter = false;
					}
					else
					{
						tableNumber--; // table not added, then revert back
						anyFieldMsgInShutter = anyFieldMsgInShutter2; // restore original status of the table
					}
				}
			}

			// maximum number of fields?
			if (currentNumberField > maximumNumberField)
			{
				exceedNumberFieldLimit = true;
				break;
			}
		}

		// add the last table
		if (table != null)
		{
			table.appendChild(createDefaultRowSpacer(htmlDocument, "", "", DEFAULT_COL));
			div.appendChild(createDefaultGroupDiv(htmlDocument, "", table, false, ""));
			if (!anyFieldMsgInShutter)
			{
				processShutter(htmlDocument, tableId + tableNumber, inputFieldSet.getId() + tableNumber, true);
			}
		}

		// print timing test
		EqTimingTest.printTime("HTMLToolbox.getDisplayItemsHtmlElement()", "");

		// return the div
		return div;
	}
	/**
	 * Return the HTML for group elements
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param table
	 *            - the table
	 * @param inputFieldSet
	 *            - the input field set
	 * @param displayAttributeFieldSet
	 *            - the display attribute field set
	 * @param displayGroup
	 *            - the DisplayGroup to be displayed
	 * @param fieldSetStatus
	 *            - the field set status whether the key or detail is open or close
	 * @param parentKeyOpen
	 *            - whether the parent field is open (not implemented)
	 * @param isKey
	 *            - whether to display key fields or not parentGroup - the parent group
	 * @param parentGroupId
	 *            - the parent group id of the alternate group
	 * @param subGroupIndex
	 *            - subgroup index (e.g. first alternate group is 1, second alternate group is 2, etc)
	 * 
	 * @return the table containing the display group
	 * 
	 * @throws EQException
	 */
	private HTMLElementImpl getDisplayGroupItemsHtmlElement(HTMLDocumentImpl htmlDocument, HTMLTableElementImpl table,
					InputFieldSet inputFieldSet, DisplayAttributesSet displayAttributeFieldSet, DisplayGroup displayGroup,
					LoadFieldSetStatus fieldSetStatus, final boolean parentKeyOpen, final boolean isKey, String parentGroupId,
					int subGroupIndex) throws EQException
	{
		// print timing test
		EqTimingTest.printStartTime("HTMLToolbox.getDisplayGroupItemsHtmlElement()", displayGroup.getId());

		// Must always create first field in group (KeepWithPrevious doesn't work across group boundaries).
		boolean anyFields = false;
		boolean anyButtonLinks = false;
		boolean anyLabels = false;

		int subGroupLevel = 0;
		String subGroupIndexStr = String.valueOf(subGroupIndex);

		// Process repeating group only if non-key fields mode
		if (!isKey && isRepeatingGroup(displayGroup, inputFieldSet))
		{
			breakByToolbox = null;
			linkedFunctionTool = null;
			HTMLElement repeatingDiv = getRepeatingDisplayGroupItemsHtmlElement(htmlDocument, inputFieldSet,
							displayAttributeFieldSet, displayGroup, fieldSetStatus);
			HTMLTableRowElementImpl repeatingRow = createRowElement(htmlDocument, "");
			HTMLTableCellElementImpl repeatingCell = createCellElement(htmlDocument, "", "");
			repeatingCell.appendChild(repeatingDiv);
			repeatingCell.setColSpan(DEFAULT_COL);
			repeatingCell.setClassName("defaultPadding");
			repeatingRow.appendChild(repeatingCell);
			table.appendChild(repeatingRow);
			listRepeatingGroups.add(displayGroup.getRepeatingGroup());
			createRepeatingGroupButtons(htmlDocument, table, repeatingGroupStatusHandler.getRepeatingGroupStatuses(displayGroup
							.getRepeatingGroup().trim()), repeatingCell, displayGroup);
			EqTimingTest.printTime("HTMLToolbox.getDisplayGroupItemsHtmlElement()", displayGroup.getId());
			return table;
		}

		table.setAttribute("defTable", EqDataType.YES);
		DisplayItemList displayItems = displayGroup.getDisplayItems();

		// display all the fields
		for (int i = 0; i < displayItems.size(); i++)
		{
			HTMLElementImpl rowElement = null;
			if (displayItems.get(i) instanceof DisplayAttributes)
			{
				DisplayAttributes displayAttributes = (DisplayAttributes) displayItems.get(i);
				InputField inputField = inputFieldSet.getInputField(displayAttributes.getId());
				if (inputField.isKey() == isKey)
				{
					if (!anyFields || !displayAttributes.isKeepWithPrevious())
					{
						rowElement = getFieldHTMLElement(htmlDocument, inputFieldSet, inputField, displayAttributeFieldSet,
										displayAttributes, displayItems, i, !fieldSetStatus.isDetailOpen(), parentKeyOpen);
						anyFields = true;
					}
				}
			}
			else if (displayItems.get(i) instanceof DisplayLabel)
			{
				DisplayLabel displayLabel = (DisplayLabel) displayItems.get(i);
				// retrieve the display label adaptor
				DisplayLabelAdaptor displayLabelAdaptor = layoutAdaptor.getDisplayLabelAdaptor(session, displayLabel.rtvBareId());
				// process only when visible
				if (displayLabelAdaptor.isVisible(displayLabel))
				{
					// Only display the DisplayLabel if the key state matches
					boolean isThisInKeyGroup = isDisplayLabelInKeyGroup(displayLabel);
					if (isThisInKeyGroup == isKey)
					{
						rowElement = getDisplayLabelHTMLElement(htmlDocument, displayAttributeFieldSet, displayLabel, displayItems,
										i);
						anyLabels = true;
					}
				}
			}
			else if (displayItems.get(i) instanceof DisplayButtonLink)
			{
				DisplayButtonLink displayButtonLink = (DisplayButtonLink) displayItems.get(i);

				// Only display the DisplayButtonLink if the key state matches
				boolean isThisInKeyGroup = isDisplayButtonLinkInKeyGroup(displayButtonLink);
				if (isThisInKeyGroup == isKey)
				{
					if (!(anyFields || anyButtonLinks || anyLabels) || !displayButtonLink.isKeepWithPrevious())
					{
						// retrieve the display button link adaptor
						DisplayButtonLinkAdaptor displayButtonLinkAdaptor = layoutAdaptor.getDisplayButtonLinkAdaptor(session,
										displayButtonLink.rtvBareId());
						// process only when visible
						if (displayButtonLinkAdaptor.isVisible(displayButtonLink))
						{
							// table has not been created, then create one
							rowElement = getDisplayButtonLinkHTMLElement(displayButtonLinkAdaptor, htmlDocument,
											displayAttributeFieldSet, displayButtonLink, displayItems, i, 2);
							anyButtonLinks = true;
						}
					}
				}
			}
			else
			{
				// get the display group
				subGroupLevel++;
				DisplayGroup subDisplayGroup = (DisplayGroup) displayItems.get(i);

				// is this visible
				DisplayGroupAdaptor displayGroupAdaptor = layoutAdaptor.getDisplayGroupAdaptor(session, displayGroup.rtvBareId(),
								subDisplayGroup.rtvBareId());

				if (displayGroupAdaptor.isVisible(subDisplayGroup))
				{
					anyFieldMsgInGroup = FunctionMessages.MSG_NONE;
					getDisplayGroupItemsHtmlElement(htmlDocument, table, inputFieldSet, displayAttributeFieldSet, subDisplayGroup,
									fieldSetStatus, parentKeyOpen, isKey, displayGroup.getId(), subGroupLevel);

					// group details
					String groupId = subDisplayGroup.rtvParent().getId();
					String levelId = String.valueOf(subGroupLevel);

					// cell
					HTMLTableCellElementImpl tabCell = createCellElement(htmlDocument, GROUPTABS_CELL + groupId + "_"
									+ subGroupLevel, "");
					if (displayGroupHandler.getCurrentSubLevel(groupId) == subGroupLevel)
					{
						tabCell.setClassName("groupTabTextSelected");
					}
					else
					{
						tabCell.setClassName("groupTabText");
						if (anyFieldMsgInGroup == FunctionMessages.MSG_INFO)
						{
							tabCell.setClassName("groupTabTextWithMsg wf_INFO");
						}
						else if (anyFieldMsgInGroup == FunctionMessages.MSG_WARN)
						{
							tabCell.setClassName("groupTabTextWithMsg wf_WARNING");
						}
						else if (anyFieldMsgInGroup == FunctionMessages.MSG_ERROR)
						{
							tabCell.setClassName("groupTabTextWithMsg wf_ERROR");
						}

					}

					// create the span containing the label
					HTMLElement labelElement = createDefaultSpanElement(htmlDocument, "", FunctionRuntimeToolbox.getLabel(eqUser,
									subDisplayGroup, functionData));

					// create the cell
					tabCell.setAttribute("onclick", "javascript:setSubGroupClick('" + table.getId() + "','" + groupId + "','"
									+ subDisplayGroup.getId() + "'," + levelId + ");");
					tabCell.setClassName(tabCell.getClassName() + " " + "mousePointerHover");
					tabCell.appendChild(labelElement);
					tabCell.setTitle(subDisplayGroup.rtvDescription(eqUser));
					tabCell.setAttribute("groupId", groupId);
					tabCell.setAttribute("levelId", levelId);

					// retrieve the row
					HTMLTableRowElementImpl tabRow = (HTMLTableRowElementImpl) getShutterRowLocationOfInnerTableOfFirstRow(table);
					tabRow.appendChild(tabCell);
				}
			}

			// add the row to the table
			if (rowElement != null)
			{
				table.appendChild(rowElement);

				// under an alternate group?
				if (subGroupIndex > 0)
				{
					rowElement.setAttribute("displayGroupId", parentGroupId);
					rowElement.setAttribute("displayGroupSubLevel", subGroupIndexStr);

					// alternate group not active, then hide the row
					int currentSubLevel = displayGroupHandler.getCurrentSubLevel(parentGroupId);
					if (subGroupIndex != currentSubLevel)
					{
						addClass(rowElement, NON_DISPLAY_CLASS);
					}
				}
			}

			// maximum number of fields?
			if (currentNumberField > maximumNumberField)
			{
				exceedNumberFieldLimit = true;
				break;
			}
		}

		// print timing test
		EqTimingTest.printTime("HTMLToolbox.getDisplayGroupItemsHtmlElement()", displayGroup.getId());

		return table;
	}
	/**
	 * Determines if a group contains repeating data or not
	 * 
	 * @param group
	 * @param inputFieldSet
	 * @return
	 */
	private boolean isRepeatingGroup(DisplayGroup group, InputFieldSet inputFieldSet)
	{
		boolean repeating = false;
		// Whizz through all the items to check for repeating data
		for (IDisplayItem displayItem : group.getDisplayItems())
		{
			if (displayItem instanceof DisplayAttributes)
			{
				DisplayAttributes displayAttributes = (DisplayAttributes) displayItem;
				InputField inputField;
				try
				{
					inputField = inputFieldSet.getInputField(displayAttributes.getId());
					if (Field.isRepeating(inputField))
					{
						repeating = true;
						break;
					}
				}
				catch (EQException e)
				{
					LOG.error("Failed to get input field with id [" + displayAttributes.getId() + "]");
				}
			}
		}
		return repeating;
	}

	/**
	 * Return the HTML for a repeating data group.
	 * <p>
	 * Note that currently, it is assumed that
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param inputFieldSet
	 *            - the input field set
	 * @param displayAttributeFieldSet
	 *            - the display attribute field set
	 * @param group
	 *            - the DisplayGroup to be processed
	 * @param fieldSetStatus
	 *            - the field set status whether the key or detail is open or close
	 * 
	 * @return a HTML DIV element containing the repeating display group
	 * 
	 * @throws EQException
	 */
	private HTMLElement getRepeatingDisplayGroupItemsHtmlElement(HTMLDocumentImpl htmlDocument, InputFieldSet inputFieldSet,
					DisplayAttributesSet displayAttributeFieldSet, DisplayGroup group, LoadFieldSetStatus fieldSetStatus)
					throws EQException
	{
		// print timing test
		EqTimingTest.printStartTime("HTMLToolbox.getRepeatingDisplayGroupItemsHtmlElement()", group.getId());

		// repeating group id
		String repeatingGroupId = group.getRepeatingGroup().trim();

		// dummy rows
		HTMLTableRowElementImpl dataDummyRow = createRowElement(htmlDocument, "");
		dataDummyRow.setAttribute("rowindex", ROW_INDEX_END);
		HTMLTableRowElementImpl footerDummyRow = createRowElement(htmlDocument, "");

		// summation arrays
		String[] summationBy = new String[0];
		if (group.getSummationBy().trim().length() > 0)
		{
			summationBy = group.getSummationBy().trim().split(Toolbox.CONTEXT_DELIMETER);
			for (int i = 0; i < summationBy.length; i++)
			{
				summationBy[i] = summationBy[i].trim();
			}
		}

		// create the main div containing the column header and column data
		HTMLElement mainDiv = createDivElement(htmlDocument, "");
		mainDiv.setClassName("repeatingDiv");

		// linked function
		linkedFunctionTool = new LinkedFunctionsToolbox(group.getLinkedFunctions());

		// create the repeating group status
		RepeatingGroupStatus repeatingGroupStatus = repeatingGroupStatusHandler.getRepeatingGroupStatuses(repeatingGroupId);
		if (repeatingGroupStatus == null)
		{
			repeatingGroupStatusHandler.addRepeatingGroupStatus(repeatingGroupId, new RepeatingGroupStatus(repeatingGroupId));
			repeatingGroupStatus = repeatingGroupStatusHandler.getRepeatingGroupStatuses(repeatingGroupId);

			// generate the linked functions
			boolean linkedFunctionExists = group.getLinkedFunctions().size() > 0;

			// supervisor, allow display only
			if (linkedFunctionExists && securityLevel.getCheckerType() == SecurityLevel.CHCKR_SUPER)
			{
				if (linkedFunctionTool.getValidValueLinkedFunctionSupervisor().length() == 0)
				{
					linkedFunctionExists = false;
				}
			}

			// set the linked function
			repeatingGroupStatus.setLinkFuncExists(linkedFunctionExists);

			// add all the repeating data managers
			repeatingGroupStatus.setRepeatingDataManager(functionData.getRepeatingDataManager(repeatingGroupId));
		}

		// is repeating data manager empty, then populate it - this happens during restore of repeating list
		if (repeatingGroupStatus.currentSortedRepeatingData() == null)
		{
			// add the repeating groups
			repeatingGroupStatus.setRepeatingDataManager(functionData.getRepeatingDataManager(repeatingGroupId));

			if (repeatingGroupStatus.getPageSize() > 0)
			{
				repeatingGroupStatus.sort(repeatingGroupId, repeatingGroupStatus.getFieldOrder());
				if (!repeatingGroupStatus.isSortAsAsc())
				{
					repeatingGroupStatus.reverseSort(repeatingGroupId, repeatingGroupStatus.getFieldOrder());
				}
			}
		}

		// set page size
		if (group.getPagingSize() != 0
						&& group.getPagingSize() < repeatingGroupStatus.currentSortedRepeatingData().getRepeatingDataManager()
										.totalRows())
		{
			repeatingGroupStatus.setPageSize(group.getPagingSize());
		}
		else
		{
			repeatingGroupStatus.setPageSize(0);
		}

		// ------------------------------------------------------------------------------------------------------------------
		// list of display attributes visible on the table
		ArrayList<DisplayAttributes> listOfVisiDisplayAttributesOnTable = new ArrayList<DisplayAttributes>();
		ArrayList<DisplayAttributes> listOfInvisiDisplayAttributesOnTable = new ArrayList<DisplayAttributes>();

		// create accumulator for the grouping and totalling
		breakByToolbox = new BreakByToolbox();

		// generate the repeating group header
		HTMLElement headerDiv = generateRepeatingGroupHeader(htmlDocument, group, repeatingGroupStatus, inputFieldSet,
						repeatingGroupStatus.isLinkFuncExists(), linkedFunctionTool, dataDummyRow, footerDummyRow,
						listOfVisiDisplayAttributesOnTable, listOfInvisiDisplayAttributesOnTable);
		HTMLTableElementImpl headerTable = (HTMLTableElementImpl) headerDiv.getFirstChild();
		if (fixedListHeader)
		{
			mainDiv.appendChild(headerDiv);
		}

		// sort on display?
		if (repeatingGroupStatus.getFieldOrder().length() > 0 && repeatingGroupStatus.getPageSize() == 0)
		{
			repeatingGroupSortByField.add(repeatingGroupId);
		}

		// create an invisible div
		HTMLDivElementImpl invisibleDiv = createDivElement(htmlDocument, repeatingGroupId + REPGROUP_INVISIBLE);
		invisibleDiv.setClassName(NON_DISPLAY_CLASS);

		// create the hidden element for the sorted by column field
		HTMLInputElementImpl fieldSortOrder = createHiddenInputElement(htmlDocument, repeatingGroupId + REPGROUP_FLDSORT, true);
		fieldSortOrder.setValue(repeatingGroupStatus.getFieldOrder());
		invisibleDiv.appendChild(fieldSortOrder);

		// create the hidden element for the sorted by (asc or desc)
		HTMLInputElementImpl sortStatus = createHiddenInputElement(htmlDocument, repeatingGroupId + REPGROUP_ORDERSORT, true);
		sortStatus.setValue(String.valueOf(repeatingGroupStatus.isSortAsAsc()));
		invisibleDiv.appendChild(sortStatus);

		// create the hidden element for the groupby
		HTMLInputElementImpl inputBreak = createHiddenInputElement(htmlDocument, repeatingGroupId + REPGROUP_INPBREAKBY, true);
		inputBreak.setValue("");
		invisibleDiv.appendChild(inputBreak);

		// generate the repeating group data
		HTMLElement repeatingDiv = generateRepeatingGroupData(htmlDocument, group, inputFieldSet, repeatingGroupStatus,
						linkedFunctionTool, listOfVisiDisplayAttributesOnTable, listOfInvisiDisplayAttributesOnTable,
						breakByToolbox, summationBy, invisibleDiv);
		HTMLTableElementImpl repeatingTable = (HTMLTableElementImpl) repeatingDiv.getFirstChild();
		mainDiv.appendChild(repeatingDiv);

		// generate the repeating group footer
		HTMLElement footerDiv = generateRepeatingGroupFooter(htmlDocument, group, inputFieldSet, repeatingGroupStatus,
						listOfVisiDisplayAttributesOnTable, breakByToolbox, summationBy, mainDiv);
		HTMLTableElementImpl footerTable = (HTMLTableElementImpl) footerDiv.getFirstChild();
		if (fixedListHeader)
		{
			mainDiv.appendChild(footerDiv);
		}

		// Moveable header
		if (!fixedListHeader)
		{
			moveableListHeaderProcessing(headerTable, repeatingTable, footerTable);
		}

		// now, let us add a dummy row
		repeatingTable.appendChild(dataDummyRow);
		if (fixedListHeader)
		{
			footerTable.appendChild(footerDummyRow);
		}

		// add the invisible div
		mainDiv.appendChild(invisibleDiv);

		// reset the row id
		this.rowId = null;

		// print timing test
		EqTimingTest.printTime("HTMLToolbox.getRepeatingDisplayGroupItemsHtmlElement()", group.getId());

		// return the div element
		return mainDiv;
	}

	/**
	 * Add all the header/footer row to the data table
	 * 
	 * @param headerTable
	 *            - the header table
	 * @param repeatingTable
	 *            - the repeating table
	 * @param footerTable
	 *            - the footer table
	 */

	private void moveableListHeaderProcessing(HTMLTableElementImpl headerTable, HTMLTableElementImpl repeatingTable,
					HTMLTableElementImpl footerTable)
	{
		HTMLElement referenceNode = (HTMLElement) repeatingTable.getFirstChild();
		HTMLElement element;

		// add the header table to the data table
		for (int i = headerTable.getRows().getLength() - 1; i >= 0; i--)
		{
			element = (HTMLElement) headerTable.getRows().item(i);
			element.setClassName(element.getClassName() + " repeatingHeaderDivAsRow");
			element.setAttribute("rowindex", "0");
			if (referenceNode == null)
			{
				repeatingTable.appendChild(element);
				referenceNode = element;
			}
			else
			{
				repeatingTable.insertBefore(element, referenceNode);
			}
		}

		// add the footer table to the data table
		referenceNode = null;
		for (int i = footerTable.getRows().getLength() - 1; i >= 0; i--)
		{
			element = (HTMLElement) footerTable.getRows().item(i);
			element.setClassName(element.getClassName() + " repeatingFooterDivAsRow");
			element.setAttribute("rowindex", ROW_INDEX_END);

			if (referenceNode == null)
			{
				repeatingTable.appendChild(element);
				referenceNode = element;
			}
			else
			{
				repeatingTable.insertBefore(element, referenceNode);
			}
		}
	}

	/**
	 * Generate the header details of a repeating group
	 * 
	 * @param htmlDocument
	 * @param repeatingGroup
	 * @param repeatingGroupStatus
	 * @param inputFieldSet
	 * @param linkedFunctionExists
	 * @param linkedFunctionTool
	 * @param dataDummyRow
	 * @param footerDummyRow
	 * @param listOfVisiDisplayAttributesOnTable
	 * @param listOfInvisiDisplayAttributesOnTable
	 * 
	 * @return the div element for the repeating group header
	 * 
	 * @throws EQException
	 */
	private HTMLElement generateRepeatingGroupHeader(HTMLDocumentImpl htmlDocument, DisplayGroup repeatingGroup,
					RepeatingGroupStatus repeatingGroupStatus, InputFieldSet inputFieldSet, boolean linkedFunctionExists,
					LinkedFunctionsToolbox linkedFunctionTool, HTMLElement dataDummyRow, HTMLElement footerDummyRow,
					ArrayList<DisplayAttributes> listOfVisiDisplayAttributesOnTable,
					ArrayList<DisplayAttributes> listOfInvisiDisplayAttributesOnTable) throws EQException
	{
		EqTimingTest.printStartTime("HTMLToolbox.generateRepeatingGroupHeader()", "");

		// repeating group id
		String repeatingGroupId = repeatingGroup.getRepeatingGroup().trim();

		// create the table for the column header
		HTMLTableElementImpl headerTable = createTableElement(htmlDocument, LISTTABLEHDR + repeatingGroupId, "");
		String tableStyle = ELRuntime.runStringScript(repeatingGroup.getTableHeaderStyleExpression(), functionData, repeatingGroup
						.getId(), LanguageResources.getString("Language.TableHeaderStyle"), "", ELRuntime.DB_VALUE);
		if (tableStyle.length() > 0)
		{
			headerTable.setClassName(tableStyle);
		}

		// create the div
		HTMLElement headerDiv = createDivElement(htmlDocument, LISTGROUPHDR + repeatingGroupId);
		headerDiv.setAttribute("style", "overflow:hidden");
		headerDiv.setClassName("repeatingHeaderDiv");
		headerDiv.appendChild(headerTable);

		// ------------------------------------------------------------------------------------------------------------------
		// Create headings
		HTMLTableRowElementImpl tableHeadingsRow = createRowElement(htmlDocument, "");
		headerTable.appendChild(tableHeadingsRow);

		// Any linked function, then auto generate the selection option
		if (linkedFunctionExists)
		{
			// create the text
			HTMLElement labelElement = createSpanElement(htmlDocument, "", "labelColumnHeaderText", LanguageResources
							.getString("Language.SelectOptionLabel"));

			// create the cell
			HTMLTableCellElementImpl radioColumnHeadingCell = createCellElement(htmlDocument, REPEAT_HDR + repeatingGroupId
							+ GRP_FLD + RepeatingDataManager.rtvSelectionOptionId(repeatingGroup.getRepeatingGroup()), "");
			radioColumnHeadingCell.appendChild(labelElement);
			listRepeatingGroupsCells.add(radioColumnHeadingCell.getId());

			// Button to trigger the drill down of this repeating group but only if there are many selections or selection is not
			// '1'
			if (!linkedFunctionTool.predictableLinkedFunction())
			{
				HTMLScriptElementImpl scriptVal = createScriptElement(htmlDocument, "");
				scriptVal.setText("showDrillDownButton('" + repeatingGroupId + "')");
				radioColumnHeadingCell.appendChild(scriptVal);

				String contextStr = Toolbox.rplSlash(Toolbox.rplQuote(Toolbox
								.rplSingleQuoteWithSlashSingleQuote(LinkedFunctionsToolbox.cvtListToString(repeatingGroup
												.getLinkedFunctions()))));
				scriptVal = createScriptElement(htmlDocument, "");
				scriptVal.setText("var " + repeatingGroupId.trim() + REPGROUP_OPTION1 + "='" + contextStr + "';" + "var "
								+ repeatingGroupId.trim() + REPGROUP_OPTION2 + "='" + linkedFunctionTool.getValidValueListing()
								+ "'");
				radioColumnHeadingCell.appendChild(scriptVal);
			}

			// add to the row
			tableHeadingsRow.appendChild(radioColumnHeadingCell);

			// dummy rows
			String selOptionId = repeatingGroupStatus.currentSortedRepeatingData().getRepeatingDataManager().rtvSelectionOptionId();
			HTMLTableCellElementImpl dummyElement = createCellElement(htmlDocument, REPEAT_LST + repeatingGroupId + GRP_FLD
							+ selOptionId, "");
			dataDummyRow.appendChild(dummyElement);

			dummyElement = createCellElement(htmlDocument, REPEAT_FTR + repeatingGroupId + GRP_FLD + selOptionId, "");
			footerDummyRow.appendChild(dummyElement);
		}

		// Add headings for all the columns (fields)
		HTMLTableCellElementImpl headingElement = null;
		for (int index = 0; index < repeatingGroup.getDisplayItems().size(); index++)
		{
			IDisplayItem displayItem = repeatingGroup.getDisplayItems().get(index);
			if (displayItem instanceof DisplayAttributes)
			{
				DisplayAttributes displayAttributes = (DisplayAttributes) displayItem;
				// Only include visible fields,
				if (isRepeatingFieldVisible(displayAttributes))
				{
					// add to the list
					listOfVisiDisplayAttributesOnTable.add(displayAttributes);

					// and don't output a separate column if keeping with the previous
					if (displayAttributes.isKeepWithPrevious() && headingElement != null)
					{
						// Bump up the colspan in the 'current' heading element:
						// headingElement.setColSpan(headingElement.getColSpan() + 1);
					}
					else
					{
						// And start processing the next heading.
						InputField inputField = inputFieldSet.getInputField(displayAttributes.getId());
						headingElement = getLabelHtmlElement(htmlDocument, displayAttributes, inputField);
						headingElement.setId(REPEAT_HDR + repeatingGroupId + GRP_FLD + displayAttributes.getId());

						// sorting allowed?
						if (repeatingGroup.isAllowSorting())
						{
							headingElement.setClassName(headingElement.getClassName() + " " + "mousePointerHover");

							if (repeatingGroupStatus.getPageSize() == 0)
							{
								headingElement.setAttribute("onclick", "javascript:sortColumn('" + repeatingGroupId + "','"
												+ inputField.getId() + "','" + LISTTABLELST + repeatingGroupId + "');");
							}
							else
							{
								headingElement.setAttribute("onclick", "javascript:sortColumnOnServer('" + repeatingGroupId + "','"
												+ inputField.getId() + "','" + LISTTABLELST + repeatingGroupId + "');");

							}
						}

						// add to the list
						listRepeatingGroupsCells.add(headingElement.getId());

						// is field amount, then center it
						if (inputField.getDataType().equals(EqDataType.TYPE_PACKED)
										|| inputField.getDataType().equals(EqDataType.TYPE_ZONED)
										|| displayAttributes.isShowAsRightAligned())
						{
							addClass(headingElement, "wf_CENTER_ALIGN");
						}

						// Not keeping with previous, so add previous column heading (if any)
						if (headingElement != null)
						{
							tableHeadingsRow.appendChild(headingElement);

							// dummy rows
							HTMLTableCellElementImpl dummyElement = createCellElement(htmlDocument, REPEAT_LST + repeatingGroupId
											+ GRP_FLD + displayAttributes.getId(), "");
							dataDummyRow.appendChild(dummyElement);

							dummyElement = createCellElement(htmlDocument, REPEAT_FTR + repeatingGroupId + GRP_FLD
											+ displayAttributes.getId(), "");
							footerDummyRow.appendChild(dummyElement);
						}
					}
				}
				else
				{
					listOfInvisiDisplayAttributesOnTable.add(displayAttributes);
				}
			}
		}

		EqTimingTest.printTime("HTMLToolbox.generateRepeatingGroupHeader()", "");

		// return the divvy
		return headerDiv;
	}

	/**
	 * Generate the data for a repeating group
	 * 
	 * @param htmlDocument
	 * @param repeatingGroup
	 * @param inputFieldSet
	 * @param linkedFunctionTool
	 * @param listOfVisiDisplayAttributesOnTable
	 * @param listOfInvisiDisplayAttributesOnTable
	 * @param breakByToolbox
	 * @param summationBy
	 * @param invisibleDiv
	 * 
	 * @return the div element for the repeating group data
	 * 
	 * @throws EQException
	 */
	private HTMLElement generateRepeatingGroupData(HTMLDocumentImpl htmlDocument, DisplayGroup repeatingGroup,
					InputFieldSet inputFieldSet, RepeatingGroupStatus repeatingGroupStatus,
					LinkedFunctionsToolbox linkedFunctionTool, ArrayList<DisplayAttributes> listOfVisiDisplayAttributesOnTable,
					ArrayList<DisplayAttributes> listOfInvisiDisplayAttributesOnTable, BreakByToolbox breakByToolbox,
					String[] summationBy, HTMLElement invisibleDiv) throws EQException
	{
		EqTimingTest.printStartTime("HTMLToolbox.generateRepeatingGroupData()", repeatingGroup.getId());

		// repeating group id
		String repeatingGroupId = repeatingGroup.getRepeatingGroup().trim();

		// sorted list
		SortedRepeatingData sortedRepeatingData = repeatingGroupStatus.currentSortedRepeatingData();

		// generate the repeating group data style
		HTMLTableElementImpl repeatingTable = createTableElement(htmlDocument, LISTTABLELST + repeatingGroupId, "");
		String tableStyle = ELRuntime.runStringScript(repeatingGroup.getTableDataStyleExpression(), functionData, repeatingGroup
						.getId(), LanguageResources.getString("Language.TableDataStyle"), "", ELRuntime.DB_VALUE);
		if (tableStyle.length() > 0)
		{
			repeatingTable.setClassName(tableStyle);
		}

		// create the div
		HTMLElement repeatingDiv = createDivElement(htmlDocument, LISTGROUPLST + repeatingGroupId);
		if (fixedListHeader)
		{
			repeatingDiv.setClassName("scrollingY repeatingDataDiv");
		}
		else
		{
			repeatingDiv.setClassName("scrollingXY repeatingDataDiv");
		}
		repeatingDiv.appendChild(repeatingTable);

		// create the hidden element for the number of rows displayed
		if (repeatingGroup.getRepeatingRowsDisplayed() != 0
						&& sortedRepeatingData.totalRows() > repeatingGroup.getRepeatingRowsDisplayed())
		{
			repeatingTable.setAttribute("maxRowDisplayed", String.valueOf(repeatingGroup.getRepeatingRowsDisplayed()));
		}

		// Get the display group adaptor
		DisplayGroupAdaptor displayGroupAdaptor = layoutAdaptor.getDisplayGroupAdaptor(session, repeatingGroup.rtvBareId(), "");

		// setup the row style
		String rowStyle1 = ELRuntime.runStringScript(repeatingGroup.getRowStyleOddExpression(), functionData, repeatingGroup
						.getId(), LanguageResources.getString("Language.RowStyle"), tableStyle, ELRuntime.DB_VALUE);
		String rowStyle2 = ELRuntime.runStringScript(repeatingGroup.getRowStyleEvenExpression(), functionData, repeatingGroup
						.getId(), LanguageResources.getString("Language.RowStyle"), tableStyle, ELRuntime.DB_VALUE);
		repeatingTable.setAttribute("rowStyle1", rowStyle1);
		repeatingTable.setAttribute("rowStyle2", rowStyle2);

		// input capable?
		boolean defaultInputCapable = isInputCapable();

		// default editing (new screen), then fields in the table
		if (defaultInputCapable)
		{
			defaultInputCapable = repeatingGroup.getEditMode().equals(DisplayGroup.EDIT_MODE_IN_PLACE);
		}

		// --------------------------------------------------------------------------------------------------------------------
		// Create the top most row
		HTMLTableRowElementImpl topMostRow = getRepeatingGroupRowFromUserExit(1, repeatingGroup, displayGroupAdaptor,
						listOfVisiDisplayAttributesOnTable, repeatingGroupStatus.isLinkFuncExists(), TOP_MOST_ROW, htmlDocument,
						inputFieldSet);
		if (topMostRow != null)
		{
			topMostRow.setAttribute("rowindex", "0");
			repeatingTable.appendChild(topMostRow);
		}

		// get the starting top index
		int rrnTop = repeatingGroupStatus.getRrnTop();
		repeatingGroupStatus.setRrnTop(0);
		repeatingGroupStatus.setRrnBottom(-1);

		// Create the real data
		generateRepeatingGroupOnPageDown(htmlDocument, repeatingGroup, inputFieldSet, repeatingGroupStatus,
						listOfVisiDisplayAttributesOnTable, listOfInvisiDisplayAttributesOnTable, displayGroupAdaptor,
						linkedFunctionTool, repeatingTable, invisibleDiv, defaultInputCapable, breakByToolbox, summationBy, false,
						rrnTop);

		// --------------------------------------------------------------------------------------------------------------------
		// Create the bottom most row
		HTMLTableRowElementImpl bottomMostRow = getRepeatingGroupRowFromUserExit(1, repeatingGroup, displayGroupAdaptor,
						listOfVisiDisplayAttributesOnTable, repeatingGroupStatus.isLinkFuncExists(), BOTTOM_MOST_ROW, htmlDocument,
						inputFieldSet);
		if (bottomMostRow != null)
		{
			bottomMostRow.setAttribute("rowindex", ROW_INDEX_END);
			repeatingTable.appendChild(bottomMostRow);
		}

		EqTimingTest.printTime("HTMLToolbox.generateRepeatingGroupData()", "");

		// return the divvy
		return repeatingDiv;
	}

	/**
	 * Add the repeating data row
	 * 
	 * @param htmlDocument
	 * @param rowIndex
	 * @param repeatingGroup
	 * @param inputFieldSet
	 * @param repeatingGroupStatus
	 * @param linkedFunctionTool
	 * @param listOfVisiDisplayAttributesOnTable
	 * @param listOfInvisiDisplayAttributesOnTable
	 * @param invisibleDiv
	 * @param defaultInputCapable
	 * @param repeatingTable
	 * @param displayGroupAdaptor
	 * @param breakByValue
	 * @param descending
	 *            - whether the data is being built is descending or ascending order
	 * 
	 * @return the repeating data row
	 * 
	 * @throws EQException
	 */
	private HTMLElement addRepeatingGroupRow(HTMLDocumentImpl htmlDocument, int rowIndex, DisplayGroup repeatingGroup,
					InputFieldSet inputFieldSet, RepeatingGroupStatus repeatingGroupStatus,
					LinkedFunctionsToolbox linkedFunctionTool, ArrayList<DisplayAttributes> listOfVisiDisplayAttributesOnTable,
					ArrayList<DisplayAttributes> listOfInvisiDisplayAttributesOnTable, HTMLElement invisibleDiv,
					boolean defaultInputCapable, HTMLElement repeatingTable, DisplayGroupAdaptor displayGroupAdaptor,
					String breakByValue, boolean descending) throws EQException
	{
		// repeating group id
		String repeatingGroupId = repeatingGroup.getRepeatingGroup().trim();

		// sorted repeating data
		SortedRepeatingData sortedRepeatingData = repeatingGroupStatus.currentSortedRepeatingData();

		// data
		HTMLTableRowElementImpl tableDataRow = createRowElement(htmlDocument, "");
		tableDataRow.setAttribute("rowindex", String.valueOf(rowIndex));
		tableDataRow.setAttribute("breakby", breakByValue);

		// Supervisor linked function
		String validValueLinkedFunctionSupervisor = "";
		if (securityLevel.getCheckerType() == SecurityLevel.CHCKR_SUPER)
		{
			validValueLinkedFunctionSupervisor = linkedFunctionTool.getValidValueLinkedFunctionSupervisor();
		}

		// --------------------------------------------------------------------------------------------------------------------
		// Auto generate the selection option
		if (repeatingGroupStatus.isLinkFuncExists())
		{
			String validValues = repeatingGroupId.trim() + REPGROUP_OPTION2;
			String linkedFunctionButton = "";

			if (linkedFunctionTool.validateLinkedFunction(functionData, eqUser, fhd.getFunctionInfo().getSessionType(), fhd
							.getFunctionInfo().isWebFacingInstalled()))
			{
				validValues = linkedFunctionTool.getValidatedValidValues();
				linkedFunctionButton = Toolbox.rplSlash(Toolbox.rplQuote(Toolbox
								.rplSingleQuoteWithSlashSingleQuote(LinkedFunctionsToolbox.cvtListToString(linkedFunctionTool
												.getValidatedLinkedFunctions()))));
				if (linkedFunctionButton.trim().length() == 0)
				{
					linkedFunctionButton = "none";
				}
			}

			// Construct a dummy input field
			InputField inputField = new InputField(sortedRepeatingData.getRepeatingDataManager().rtvSelectionOptionId(), "", "");
			inputField.setDataType(EqDataType.TYPE_CHAR);
			inputField.setSize("1");
			inputField.setMandatory(InputField.MANDATORY_NO);
			inputField.setValidValues(validValues);
			inputField.setValidValuesType(InputField.TEXT_VALUE_TEXT);

			// supervisor, allow display only
			if (securityLevel.getCheckerType() == SecurityLevel.CHCKR_SUPER)
			{
				inputField.setValidValues(validValueLinkedFunctionSupervisor);
			}

			// Construct a dummy display attribute
			DisplayAttributes displayAttributes = new DisplayAttributes(inputField.getId(), inputField.getLabel(), inputField
							.getDescription());
			displayAttributes.setProtected(DisplayAttributes.PROTECTED_NO);
			displayAttributes.setVisible(DisplayAttributes.VISIBLE_YES);
			displayAttributes.setParent(repeatingGroup.rtvParent());

			// Add the selection option
			FieldData fieldData = functionData.rtvFieldData(inputField.getId());
			int msgSev = getMsgSev(fieldData);
			HTMLInputElementImpl inputSelectionOption = addInputField(htmlDocument, inputFieldSet, inputField, displayAttributes,
							fieldData, false, false, true, true, false, "", "", null, null, null, false, true);
			addMessageSeverityClassName(inputSelectionOption, msgSev);
			HTMLTableCellElementImpl inputSelectionCell = (HTMLTableCellElementImpl) inputSelectionOption.getParentNode();
			inputSelectionCell.setClassName("textInputRepeatingGroup");
			inputSelectionCell.setNoWrap(true);

			if (msgSev != FunctionMessages.MSG_NONE)
			{
				inputSelectionCell.setAttribute("fixRowStyle", EqDataType.YES);
			}

			// Green arrow is no longer to be shown
			// linked function to display in popup window
			// if (!EquationCommonContext.isChildDesktopSessionPopup(fhd.getFunctionInfo().getSessionType())
			// && securityLevel.getCheckerType() != SecurityLevel.CHCKR_SUPER)
			// {
			// HTMLScriptElementImpl scriptVal = createScriptElement(htmlDocument, "");
			// scriptVal.setText("showRepeatingLinkedFunctionButton('"
			// + getInputFieldId(sortedRepeatingData.getRepeatingDataManager().rtvSelectionOptionId()) + "','"
			// + linkedFunctionButton + "','" + repeatingGroupId + "','" + this.rowId + "')");
			// inputSelectionCell.appendChild(scriptVal);
			// }

			// add to the row
			tableDataRow.appendChild(inputSelectionCell);
		}

		// Loop through the columns
		HTMLTableCellElementImpl previousDataElement = null;
		HTMLInputElementImpl previousFieldElement = null;
		for (DisplayAttributes displayAttributes : listOfVisiDisplayAttributesOnTable)
		{
			HTMLTableCellElementImpl dataElement = null;
			if (displayAttributes.isDisplayAsUrl())
			{
				dataElement = getRepeatingUrlHtmlElement(htmlDocument, displayAttributes.getId());
			}
			else
			{
				InputField inputField = inputFieldSet.getInputField(displayAttributes.getId());
				dataElement = getRepeatingFieldHtmlElement(htmlDocument, (DisplayAttributesSet) repeatingGroup.rtvParent(),
								displayAttributes, inputFieldSet, inputField, repeatingGroup, defaultInputCapable);
			}
			dataElement.setClassName("textInputRepeatingGroup");
			if (displayAttributes.addSpooledFileScript())
			{
				dataElement.setClassName("textInputRepeatingGroup" + " " + "mousePointerHover");
				dataElement.setAttribute("onclick", functionData.rtvFieldData(displayAttributes.getId()).getInputValue());
			}
			dataElement.setNoWrap(true);

			// if this field is continued from previous field
			if (displayAttributes.isKeepWithPrevious() && previousDataElement != null)
			{
				previousFieldElement.setAttribute("nextField", getInputFieldId(displayAttributes.getId()));

				// into a new line?
				if (displayAttributes.isKeepWithPreviousInNewLine())
				{
					previousDataElement.appendChild(htmlDocument.createElement("br"));
				}

				// add all the children of this cell into the previous cell
				previousFieldElement = (HTMLInputElementImpl) dataElement.getChildNodes().item(0);
				transferChilds(dataElement, previousDataElement);
			}

			// otherwise, add this cell to the row
			else
			{
				tableDataRow.appendChild(dataElement);
				previousDataElement = dataElement;
				if (previousDataElement.getFirstChild() instanceof HTMLInputElementImpl)
				{
					previousFieldElement = (HTMLInputElementImpl) previousDataElement.getFirstChild();
				}
			}
		}

		// add the value of the invisible display attributes
		for (DisplayAttributes displayAttributes : listOfInvisiDisplayAttributesOnTable)
		{
			currentNumberField++;
			addHiddenFieldValue(htmlDocument, invisibleDiv, functionData.rtvFieldData(displayAttributes.getId()));
		}

		// --------------------------------------------------------------------------------------------------------------------
		// Create the above row
		HTMLTableRowElementImpl tableDataRowAbove = getRepeatingGroupRowFromUserExit(1, repeatingGroup, displayGroupAdaptor,
						listOfVisiDisplayAttributesOnTable, repeatingGroupStatus.isLinkFuncExists(), ABOVE_ROW, htmlDocument,
						inputFieldSet);
		if (tableDataRowAbove != null)
		{
			tableDataRowAbove.setAttribute("rowrefindex", tableDataRow.getAttribute("rowindex"));
			tableDataRowAbove.setAttribute("breakby", breakByValue);
			addDefaultDoubleClickAction(tableDataRowAbove, repeatingGroupStatus, linkedFunctionTool);
		}

		// Create the bottom row
		HTMLTableRowElementImpl tableDataRowBelow = getRepeatingGroupRowFromUserExit(1, repeatingGroup, displayGroupAdaptor,
						listOfVisiDisplayAttributesOnTable, repeatingGroupStatus.isLinkFuncExists(), BELOW_ROW, htmlDocument,
						inputFieldSet);
		if (tableDataRowBelow != null)
		{
			tableDataRowBelow.setAttribute("rowrefindex", tableDataRow.getAttribute("rowindex"));
			tableDataRowBelow.setAttribute("breakby", breakByValue);
			addDefaultDoubleClickAction(tableDataRowBelow, repeatingGroupStatus, linkedFunctionTool);
		}

		addDefaultDoubleClickAction(tableDataRow, repeatingGroupStatus, linkedFunctionTool);

		// descending order
		if (descending)
		{
			if (tableDataRowBelow != null)
			{
				repeatingTable.insertBefore(tableDataRowBelow, repeatingTable.getFirstChild());
			}
			repeatingTable.insertBefore(tableDataRow, repeatingTable.getFirstChild());
			if (tableDataRowAbove != null)
			{
				repeatingTable.insertBefore(tableDataRowAbove, repeatingTable.getFirstChild());
			}
		}

		// ascending order
		else
		{
			if (tableDataRowAbove != null)
			{
				repeatingTable.appendChild(tableDataRowAbove);
			}
			repeatingTable.appendChild(tableDataRow);
			if (tableDataRowBelow != null)
			{
				repeatingTable.appendChild(tableDataRowBelow);
			}
		}

		// return the data row
		return tableDataRow;
	}

	/**
	 * Generate the footer section of a repeating data
	 * 
	 * @param htmlDocument
	 * @param repeatingGroup
	 * @param inputFieldSet
	 * @param repeatingGroupStatus
	 * @param listOfVisiDisplayAttributesOnTable
	 * @param breakByToolbox
	 * @param summationBy
	 * @param mainDiv
	 * 
	 * @return the div element of the footer section of a repeating data
	 * 
	 * @throws EQException
	 */
	private HTMLElement generateRepeatingGroupFooter(HTMLDocumentImpl htmlDocument, DisplayGroup repeatingGroup,
					InputFieldSet inputFieldSet, RepeatingGroupStatus repeatingGroupStatus,
					ArrayList<DisplayAttributes> listOfVisiDisplayAttributesOnTable, BreakByToolbox breakByToolbox,
					String[] summationBy, HTMLElement mainDiv) throws EQException
	{
		EqTimingTest.printStartTime("HTMLToolbox.generateRepeatingGroupFooter()", "");

		// repeating group id
		String repeatingGroupId = repeatingGroup.getRepeatingGroup().trim();

		// create the table for the footer
		HTMLTableElementImpl footerTable = createTableElement(htmlDocument, LISTTABLEFTR + repeatingGroupId, "");
		footerTable.setCellPadding("1");
		String tableStyle = ELRuntime.runStringScript(repeatingGroup.getTableFooterStyleExpression(), functionData, repeatingGroup
						.getId(), LanguageResources.getString("Language.TableFooterStyle"), "", ELRuntime.DB_VALUE);
		if (tableStyle.length() > 0)
		{
			footerTable.setClassName(tableStyle);
		}
		else
		{
			tableStyle = "repeatingFooterDiv";
		}

		// create the div
		HTMLElement footerDiv = createDivElement(htmlDocument, LISTGROUPFTR + repeatingGroupId);
		footerDiv.setAttribute("onscroll", "scrollRepeatingData('" + LISTGROUPFTR + repeatingGroupId + "','" + LISTGROUPHDR
						+ repeatingGroupId + "','" + LISTGROUPLST + repeatingGroupId + "')");
		footerDiv.setClassName("scrollingX repeatingFooterDiv");
		footerDiv.appendChild(footerTable);

		// display subtotalling
		if (summationBy.length > 0)
		{
			Iterator<String> iter = breakByToolbox.getBreakBy().keySet().iterator();
			while (iter.hasNext())
			{
				String grouping = iter.next();
				if (grouping.length() > 0) // do not include grand total
				{
					addRepeatingGroupFooter(repeatingGroup, listOfVisiDisplayAttributesOnTable, repeatingGroupStatus
									.isLinkFuncExists(), htmlDocument, footerTable, grouping, breakByToolbox.getBreakBy(grouping),
									inputFieldSet, tableStyle);
				}
			}

			// grand total style
			String grandTotalStyle = ELRuntime
							.runStringScript(repeatingGroup.getGrandTotalStyleExpression(), functionData, repeatingGroup.getId(),
											LanguageResources.getString("Language.GrandTotalStyle"), "", ELRuntime.DB_VALUE);
			if (grandTotalStyle.length() == 0)
			{
				grandTotalStyle = tableStyle;
			}

			// add all the grand total
			addRepeatingGroupFooter(repeatingGroup, listOfVisiDisplayAttributesOnTable, repeatingGroupStatus.isLinkFuncExists(),
							htmlDocument, footerTable, "", breakByToolbox.getBreakBy(""), inputFieldSet, grandTotalStyle);
		}

		// construct a combo box
		// if (repeatingGroup.getBreakBy().trim().length() > 0 && breakByToolbox.getBreakBy().size() > 1)
		// {
		// HTMLElement selectElement = createRepeatingGroupBreakBy(repeatingGroupId, htmlDocument, breakByToolbox.getBreakBy()
		// .keySet(), repeatingGroupStatus);
		// mainDiv.insertBefore(selectElement, mainDiv.getFirstChild());
		// }

		EqTimingTest.printTime("HTMLToolbox.generateRepeatingGroupFooter()", "");

		// return the divvy
		return footerDiv;
	}

	/**
	 * Add footer details for the repeating group
	 * 
	 * @param repeatingGroup
	 *            - the repeating group
	 * @param listOfVisiDisplayAttributesOnTable
	 *            , - list of visible column
	 * @param htmlDocument
	 *            - the HTML document
	 * @param table
	 *            - the table to insert the row
	 * @param grouping
	 *            - the grouping name
	 * @param totals
	 *            - the totals for the grouping
	 * @param inputFieldSet
	 *            - the input field set
	 */
	private void addRepeatingGroupFooter(DisplayGroup repeatingGroup,
					ArrayList<DisplayAttributes> listOfVisiDisplayAttributesOnTable, boolean linkedFunctionExists,
					HTMLDocumentImpl htmlDocument, HTMLTableElementImpl table, String grouping, SummationToolbox totals,
					InputFieldSet inputFieldSet, String rowStyle) throws EQException
	{
		// no totals, then exit
		if (totals == null)
		{
			return;
		}

		// create the footer row
		HTMLTableRowElementImpl tableFooterRow = createRowElement(htmlDocument, "");
		tableFooterRow.setAttribute("breakby", grouping);
		tableFooterRow.setClassName(rowStyle);
		table.appendChild(tableFooterRow);

		// add a cell for the selection option if exists
		boolean notfirstone = false;
		boolean textLeft = true;
		HTMLTableCellElementImpl previousDataElement = null;
		if (linkedFunctionExists)
		{
			HTMLTableCellElementImpl footerElement = createCellElement(htmlDocument, "", "");
			tableFooterRow.appendChild(footerElement);
			footerElement.appendChild(htmlDocument.createTextNode(HTML_NBSP));
			textLeft = false;
			previousDataElement = footerElement;
		}

		for (DisplayAttributes displayAttributes : listOfVisiDisplayAttributesOnTable)
		{
			// the cell
			HTMLTableCellElementImpl dataElement = null;

			// get the value and create the input element if value exists
			BigDecimal value = totals.getValue(displayAttributes.getId());
			if (value != null)
			{
				FieldData fieldData = functionData.rtvFieldData(displayAttributes.getId());
				FieldData dummyFieldData = new FieldData(getUniqueUnnamedId(), fieldData);
				dummyFieldData.setDbValue(value.toPlainString());
				dummyFieldData.setInputValue(value.toPlainString());
				String outputValue = FunctionRuntimeToolbox.editEquationData(eqUser, functionData, value.toPlainString(),
								displayAttributes.getId(), fieldData.getFieldType(), fieldData.getFieldLength(), fieldData
												.getFieldDecimal(), fieldData.getEditCode(), fieldData.getEditCodeParameter(),
								layoutAdaptor.getAttributesAdaptor(session, displayAttributes.getId()), displayAttributes
												.getEditCodeParameterReplacements());
				dummyFieldData.setOutputValue(outputValue);

				DisplayAttributes dummyAttributes = getDummyDisplayAttribute(dummyFieldData.getFieldName(), displayAttributes);
				InputField dummyInputField = getDummyInputField(dummyFieldData.getFieldName(), inputFieldSet
								.getInputField(displayAttributes.getId()));

				HTMLInputElementImpl input = addInputField(htmlDocument, inputFieldSet, dummyInputField, dummyAttributes,
								dummyFieldData, false, false, false, true, false, "", "", null, null, null, false, false);
				addClass(input, rowStyle);
				dataElement = (HTMLTableCellElementImpl) input.getParentNode();
				dataElement.setClassName("textInputRepeatingGroup");
			}

			// if this field is continued from previous field
			if (displayAttributes.isKeepWithPrevious() && previousDataElement != null && value != null && notfirstone)
			{
				// into a new line?
				if (displayAttributes.isKeepWithPreviousInNewLine())
				{
					previousDataElement.appendChild(htmlDocument.createElement("br"));
				}

				// add all the children of this cell into the previous cell
				transferChilds(dataElement, previousDataElement);
				// int nChilds = dataElement.getChildNodes().getLength();
				// for (int i = 0; i < nChilds; i++)
				// {
				// // always get the zero index as adding a child to another element
				// // removes it from the original parent!
				// Node node = dataElement.getChildNodes().item(0);
				// previousDataElement.appendChild(node);
				// }
				textLeft = true;
			}

			// otherwise, add this cell to the row
			else if (value != null)
			{
				tableFooterRow.appendChild(dataElement);
				previousDataElement = dataElement;
				textLeft = true;
			}

			// position for the total label text?
			else if (displayAttributes.getId().equals(repeatingGroup.getTotalLabelPosition()))
			{
				HTMLTableCellElementImpl footerElement;
				if (displayAttributes.isKeepWithPrevious() && previousDataElement != null && notfirstone)
				{
					footerElement = previousDataElement;
				}
				else
				{
					footerElement = createCellElement(htmlDocument, "", "");
					footerElement.setClassName("textInputRepeatingGroup");
				}
				String labelTotal = "";
				functionData.chgFieldDbValue(FunctionData.FLD_BREAKBY, grouping.replaceAll(EqDataType.GLOBAL_DELIMETER, " - "));
				if (grouping.length() == 0)
				{
					labelTotal = ELRuntime.runStringScript(repeatingGroup.getTotalLabelTextScript(), functionData, repeatingGroup
									.getId(), LanguageResources.getString("Language.TotalText"), "", ELRuntime.DB_VALUE);
				}
				else
				{
					labelTotal = ELRuntime.runStringScript(repeatingGroup.getTotalSubLabelTextScript(), functionData,
									repeatingGroup.getId(), LanguageResources.getString("Language.TotalText"), "",
									ELRuntime.DB_VALUE);
				}
				footerElement.appendChild(createSpanElement(htmlDocument, "", "", labelTotal));
				tableFooterRow.appendChild(footerElement);
				textLeft = false;
				previousDataElement = footerElement;
			}

			// empty space
			else if (!displayAttributes.isKeepWithPrevious() || !notfirstone)
			{
				HTMLTableCellElementImpl footerElement = createCellElement(htmlDocument, "", "");
				if (textLeft)
				{
					footerElement.appendChild(htmlDocument.createTextNode(HTML_NBSP));
					tableFooterRow.appendChild(footerElement);
					previousDataElement = footerElement;
					textLeft = false;
				}
				else
				{
					previousDataElement.setColSpan(previousDataElement.getColSpan() + 1);
				}
			}

			notfirstone = true;
		}
	}

	/**
	 * Add a row of details for the repeating group
	 * 
	 * @param counter
	 *            - determines the nth user defined row
	 * @param repeatingGroup
	 *            - the repeating group
	 * @param listOfVisiDisplayAttributesOnTable
	 *            , - list of visible column
	 * @param htmlDocument
	 *            - the HTML document
	 * @param table
	 *            - the table to insert the row
	 * @param grouping
	 *            - the grouping name
	 * @param totals
	 *            - the totals for the grouping
	 * @param inputFieldSet
	 *            - the input field set
	 */
	private HTMLTableRowElementImpl getRepeatingGroupRowFromUserExit(int counter, DisplayGroup repeatingGroup,
					DisplayGroupAdaptor displayGroupAdaptor, ArrayList<DisplayAttributes> listOfVisiDisplayAttributesOnTable,
					boolean linkedFunctionExists, int control, HTMLDocumentImpl htmlDocument, InputFieldSet inputFieldSet)
					throws EQException
	{
		String userDefinedRowStyle = null;

		HTMLTableRowElementImpl tableRow = createRowElement(htmlDocument, "");
		tableRow.setAttribute("breakby", "");

		// add a cell for the selection option if exists
		boolean dataExists = false;
		boolean notfirstone = false;
		boolean textLeft = true;
		HTMLTableCellElementImpl previousDataElement = null;
		if (linkedFunctionExists)
		{
			HTMLTableCellElementImpl cellElement = createCellElement(htmlDocument, "", "");
			tableRow.appendChild(cellElement);
			cellElement.appendChild(htmlDocument.createTextNode(HTML_NBSP));
			textLeft = false;
			previousDataElement = cellElement;
		}

		for (DisplayAttributes displayAttributes : listOfVisiDisplayAttributesOnTable)
		{
			// the cell
			HTMLTableCellElementImpl dataElement = null;
			String fieldName = displayAttributes.getId();

			// get the value
			UserExitListColumnData returnValues = null;
			if (control == TOP_MOST_ROW)
			{
				returnValues = displayGroupAdaptor.getTopColumnDetails(counter, fieldName);
			}
			else if (control == BOTTOM_MOST_ROW)
			{
				returnValues = displayGroupAdaptor.getBottomColumnDetails(counter, fieldName);
			}
			else if (control == ABOVE_ROW)
			{
				returnValues = displayGroupAdaptor.getAboveRowColumnDetails(counter, fieldName);
			}
			else if (control == BELOW_ROW)
			{
				returnValues = displayGroupAdaptor.getBelowRowColumnDetails(counter, fieldName);
			}

			// does return value contains data?
			if (returnValues != null && !returnValues.containsData())
			{
				returnValues = null;
			}

			// create the cell if there is value
			if (returnValues != null)
			{
				FieldData fieldData = functionData.rtvFieldData(fieldName);
				FieldData dummyFieldData = new FieldData(getUniqueUnnamedId(), fieldData);
				dummyFieldData.setDbValue(returnValues.getDbValue());
				dummyFieldData.setInputValue(returnValues.getInputValue());
				dummyFieldData.setOutputValue(returnValues.getOutputValue());

				if (returnValues.getInputValue().trim().length() == 0)
				{
					dummyFieldData.setInputValue(returnValues.getDbValue());
				}

				// is this the first cell to have a value?
				if (userDefinedRowStyle == null)
				{
					userDefinedRowStyle = returnValues.getRowStyle();
					if (userDefinedRowStyle.trim().length() > 0)
					{
						addClass(tableRow, userDefinedRowStyle);
						tableRow.setAttribute("fixRowStyle", EqDataType.YES);
					}
				}

				DisplayAttributes dummyAttributes = getDummyDisplayAttribute(dummyFieldData.getFieldName(), displayAttributes);
				InputField dummyInputField = getDummyInputField(dummyFieldData.getFieldName(), inputFieldSet
								.getInputField(displayAttributes.getId()));

				// if this is numeric then determine if the returned value is a valid number or not
				boolean valid = true;

				// check if value contains a marker to say that it is a narrative
				if (returnValues.getDbValue().indexOf(EqDataType.CHARACTER_RETURN) >= 0)
				{
					valid = false;
				}

				if (!valid)
				{
				}
				else if (EqDataType.isNumeric(dummyFieldData.getFieldType()))
				{
					try
					{
						BigDecimal bigDecimal = new BigDecimal(returnValues.getDbValue());

						if (returnValues.getInputValue().trim().length() == 0)
						{
							dummyFieldData.setInputValue(EqDataType.cvtDbNumericToInput(bigDecimal.toPlainString(), functionData
											.getIntegerSeparator(), functionData.getDecimalSeparator()));
						}
					}
					catch (Exception e)
					{
						dummyInputField.setDataType(EqDataType.TYPE_CHAR);
						dummyInputField.setSize(String.valueOf(returnValues.getInputValue().length()));
						dummyAttributes.setShowDescriptionAsValue(true);
						valid = false;
					}
				}
				else if (EqDataType.isDate(dummyFieldData.getFieldType()))
				{
					// if date not valid, then set the field to alphanumeric
					String inputDate = EqDataType.cvtDbDateToInput(returnValues.getDbValue(),
									dummyFieldData.chkExtAttributeDate8(), functionData.getDateInputFormat(), functionData
													.getOpenDateAbbr());
					if (inputDate.equals(returnValues.getDbValue()) || inputDate.equals(""))
					{
						valid = false;
					}
					else if (returnValues.getInputValue().trim().length() == 0)
					{
						dummyFieldData.setInputValue(inputDate);
					}
				}
				else if (returnValues.getDbValue().length() > dummyFieldData.getFieldLength())
				{
					valid = false;
				}

				// valid value, then try to edit it
				if (valid)
				{
					if (returnValues.getOutputValue().trim().length() == 0)
					{
						String outputValue = FunctionRuntimeToolbox.editEquationData(eqUser, functionData, dummyFieldData
										.getDbValue(), displayAttributes.getId(), dummyFieldData.getFieldType(), dummyFieldData
										.getFieldLength(), dummyFieldData.getFieldDecimal(), dummyFieldData.getEditCode(),
										dummyFieldData.getEditCodeParameter(), layoutAdaptor.getAttributesAdaptor(session,
														fieldName), displayAttributes.getEditCodeParameterReplacements());
						dummyFieldData.setOutputValue(outputValue);
					}
				}
				else
				{
					dummyInputField.setDataType(EqDataType.TYPE_CHAR);
					dummyInputField.setSize(String.valueOf(returnValues.getDbValue().length()));
					dummyAttributes.setShowDescriptionAsValue(true);
					dummyFieldData.setFieldDecimal(0);
					dummyFieldData.setFieldLength(returnValues.getDbValue().length());
					dummyFieldData.setDbValue(returnValues.getDbValue());
					dummyFieldData.setInputValue(returnValues.getInputValue());
					dummyFieldData.setOutputValue(returnValues.getOutputValue());
				}

				if (valid)
				{
					HTMLInputElementImpl input = addInputField(htmlDocument, inputFieldSet, dummyInputField, dummyAttributes,
									dummyFieldData, false, false, false, true, false, "", "", null, null, null, false, false);
					dataElement = (HTMLTableCellElementImpl) input.getParentNode();
					if (returnValues.getStyle().trim().length() > 0)
					{
						addClass(input, returnValues.getStyle());
					}
					else if (userDefinedRowStyle.length() > 0)
					{
						addClass(input, userDefinedRowStyle);
					}
				}
				else
				{
					dataElement = createCellElement(htmlDocument, "", "");
					convertCharacterReturn(htmlDocument, dataElement, returnValues.getDbValue(), "");
				}

				// set the style
				dataElement.setClassName("textInputRepeatingGroup");
				if (returnValues.getStyle().trim().length() > 0)
				{
					addClass(dataElement, returnValues.getStyle());
					dataElement.setAttribute("fixRowStyle", EqDataType.YES);
				}
			}

			// if this field is continued from previous field
			if (displayAttributes.isKeepWithPrevious() && previousDataElement != null && returnValues != null && notfirstone)
			{
				// into a new line?
				if (displayAttributes.isKeepWithPreviousInNewLine())
				{
					previousDataElement.appendChild(htmlDocument.createElement("br"));
				}

				// add all the children of this cell into the previous cell
				int nChilds = dataElement.getChildNodes().getLength();
				for (int i = 0; i < nChilds; i++)
				{
					// always get the zero index as adding a child to another element
					// removes it from the original parent!
					Node node = dataElement.getChildNodes().item(0);
					previousDataElement.appendChild(node);
				}
				textLeft = false;
			}

			// otherwise, add this cell to the row
			else if (returnValues != null)
			{
				tableRow.appendChild(dataElement);
				previousDataElement = dataElement;
				textLeft = false;
				dataExists = true;
			}

			// empty space
			else if (!displayAttributes.isKeepWithPrevious() || !notfirstone)
			{
				HTMLTableCellElementImpl cellElement = createCellElement(htmlDocument, "", "");
				if (textLeft)
				{
					cellElement.appendChild(htmlDocument.createTextNode(HTML_NBSP));
					tableRow.appendChild(cellElement);
					previousDataElement = cellElement;
					textLeft = false;
				}
				else
				{
					previousDataElement.setColSpan(previousDataElement.getColSpan() + 1);
				}
			}

			notfirstone = true;

		}

		// return the row
		if (dataExists)
		{
			return tableRow;
		}
		else
		{
			return null;
		}
	}

	/**
	 * Generate the combo box for user to select which group to display
	 * 
	 * @param repeatingGroup
	 *            - the repeating group
	 * @param htmlDocument
	 *            - the HTML document
	 * @param listOfSubTotals
	 *            - the set of all grouping (breakby)
	 * @param repeatingGroupStatus
	 *            - repeating group status
	 * 
	 * @return the combo box element
	 */
	private HTMLElement createRepeatingGroupBreakBy(String repeatingGroupId, HTMLDocumentImpl htmlDocument,
					Set<String> listOfSubTotals, RepeatingGroupStatus repeatingGroupStatus)
	{
		// create the select element
		HTMLElement selectElement = (HTMLElement) htmlDocument.createElement("select");
		selectElement.setAttribute("id", repeatingGroupId + REPGROUP_SELBREAKBY);

		// no paging, then client side processing
		if (repeatingGroupStatus.getPageSize() == 0)
		{
			selectElement.setAttribute("onchange", "breakByRepeatingData('" + repeatingGroupId + "','" + LISTTABLELST
							+ repeatingGroupId + "','" + LISTTABLEFTR + repeatingGroupId + "')");
		}
		// paging, then server side processing
		else
		{
			selectElement.setAttribute("onchange", "breakByOnServerRepeatingData('" + repeatingGroupId + "','" + LISTTABLELST
							+ repeatingGroupId + "','" + LISTTABLEFTR + repeatingGroupId + "')");
		}

		// convert hashtable to list
		ArrayList<String> list2 = new ArrayList<String>(listOfSubTotals);
		Collections.sort(list2);

		// add all the totals into the footer div
		boolean selected = false;
		for (String grouping : list2)
		{
			// create the text in the option
			if (grouping.length() > 0)
			{
				String text = grouping.replaceAll(EqDataType.GLOBAL_DELIMETER, " - ");
				HTMLElement optionElement = (HTMLElement) htmlDocument.createElement("option");
				optionElement.setAttribute("value", grouping);
				selectElement.appendChild(optionElement);
				optionElement.appendChild(htmlDocument.createTextNode(text));

				if (grouping.equals(repeatingGroupStatus.getBreakBy()))
				{
					optionElement.setAttribute("selected", "");
					selected = true;
				}
			}
		}

		// add the grouping for grand total
		HTMLElement optionElement = (HTMLElement) htmlDocument.createElement("option");
		optionElement.appendChild(htmlDocument.createTextNode(EquationCommonContext.getContext().getLanguageResource(eqUser,
						"GBLALL")));
		optionElement.setAttribute("value", "");
		selectElement.insertBefore(optionElement, selectElement.getFirstChild());
		if (!selected)
		{
			optionElement.setAttribute("selected", "");
		}

		return selectElement;
	}

	/**
	 * Gets a HTML Element for a label
	 * <p>
	 * This is currently for repeating data table header labels.
	 * 
	 * @param htmlDocument
	 * @param displayAttributes
	 * @param inputField
	 * 
	 * @return a HTMLTableCellElementImpl
	 */
	private HTMLTableCellElementImpl getLabelHtmlElement(HTMLDocumentImpl htmlDocument, DisplayAttributes displayAttributes,
					InputField inputField)
	{
		// create a divvy inside
		HTMLElement divvy = createDivElement(htmlDocument, "");
		divvy.setClassName("defaultPadding");

		HTMLTableCellElementImpl labelTableCell = createCellElement(htmlDocument, TD1_PREFIX + inputField.getId(), "");
		labelTableCell.setNoWrap(false);
		labelTableCell.appendChild(divvy);

		// class name
		String style = ELRuntime.runStringScript(displayAttributes.getDisplayStyleLabel(), functionData, inputField.getId(),
						LanguageResources.getString("HTMLToolbox.displayStyleLabel"), "", ELRuntime.DB_VALUE).trim();

		// label
		String label = FunctionRuntimeToolbox.getLabel(eqUser, displayAttributes, functionData);
		String[] labels = label.split(EqDataType.CHARACTER_RETURN);
		for (int i = 0; i < labels.length; i++)
		{
			// create a span
			HTMLElement labelElement = createSpanElement(htmlDocument, "", "labelColumnHeaderTextWithWrap" + " " + style, labels[i]);
			labelElement.setTitle(displayAttributes.rtvDescription(eqUser));

			// add the text into the divvy
			divvy.appendChild(labelElement);

			// add a break if not the last
			if (i < labels.length - 1)
			{
				divvy.appendChild(htmlDocument.createElement("br"));
			}
		}

		return labelTableCell;
	}

	/**
	 * Gets a HTML Element for a repeat value
	 * <p>
	 * This is currently for repeating data table value cells
	 * 
	 * @param htmlDocument
	 * @param displayAttributes
	 * @param inputFieldSet
	 * @param inputField
	 * 
	 * @return a HTMLTableCellElementImpl
	 * 
	 * @throws EQException
	 */
	private HTMLTableCellElementImpl getRepeatingFieldHtmlElement(HTMLDocumentImpl htmlDocument,
					DisplayAttributesSet displayAttributesSet, DisplayAttributes displayAttributes, InputFieldSet inputFieldSet,
					InputField inputField, DisplayGroup displayGroup, boolean defaultInputCapable) throws EQException
	{
		FieldData fieldData = functionData.rtvFieldData(inputField.getId());
		int msgSev = getMsgSev(fieldData);

		// use the protected property
		boolean inputCapable = defaultInputCapable;
		if (inputCapable)
		{
			inputCapable = !DisplayAttributes.PROTECTED_YES.equals(displayAttributes.getProtected());
		}

		inputCapable = !screenSet.fieldProtected(inputFieldSet, inputField, displayAttributesSet, displayAttributes, !inputCapable);

		// add the input field
		HTMLInputElementImpl input = addInputField(htmlDocument, inputFieldSet, inputField, displayAttributes, fieldData, false,
						false, inputCapable, true, false, "", "", null, null, null, true, inputCapable);
		addMessageSeverityClassName(input, msgSev);

		HTMLTableCellElementImpl cell2 = (HTMLTableCellElementImpl) input.getParentNode();
		if (msgSev != FunctionMessages.MSG_NONE)
		{
			cell2.setAttribute("fixRowStyle", EqDataType.YES);
		}

		return cell2;
	}
	/**
	 * Gets a HTML Element for a repeat URL Element
	 * <p>
	 * This is currently for repeating data table URL cells
	 * 
	 * @param htmlDocument
	 * @param fieldId
	 * 
	 * @return a HTMLTableCellElementImpl
	 * 
	 * @throws EQException
	 */
	private HTMLTableCellElementImpl getRepeatingUrlHtmlElement(HTMLDocumentImpl htmlDocument, String fieldId) throws EQException
	{
		FieldData link = functionData.rtvFieldData(fieldId);
		StringBuilder builder = new StringBuilder("showButtonLinkButton('");

		// Append Label
		builder.append(Toolbox.rplSingleQuote(link.getOutputValue()));
		builder.append("', '");

		// Append Description
		String description = "";
		if (Element.DEFAULT_TEXT_VALUE.equals(description.trim()))
		{
			builder.append("");
		}
		else
		{
			builder.append(Toolbox.rplSingleQuote(description));
		}
		builder.append("', ");

		// Append Label Position
		builder.append(0);
		builder.append(", ");

		// Append Display as Button or Hyperlink
		builder.append(2);
		builder.append(", ");

		// Append URL or Command flag
		builder.append(1);
		builder.append(", '");

		// Command and Parameters
		builder.append(Toolbox.rplSingleQuote(link.getInputValue()));
		builder.append("', ");

		// Protected
		builder.append(false);
		builder.append(", '");

		// Label Style
		String style = "";
		builder.append(Toolbox.rplSingleQuote(style));
		builder.append("')");

		HTMLScriptElementImpl scriptElement = createScriptElement(htmlDocument, "");
		scriptElement.setText(builder.toString());
		HTMLTableCellElementImpl cell2 = null;
		cell2 = createCellElement(htmlDocument, "", "");
		cell2.appendChild(scriptElement);
		// return the script element
		return cell2;

	}
	/**
	 * Determines if the specified DisplayLabel is the child of the KEYS display group
	 * 
	 * @param displayLabel
	 *            - the display label
	 * 
	 * @return true if the display label belongs to the KEY group
	 */
	private boolean isDisplayLabelInKeyGroup(DisplayLabel displayLabel)
	{
		Element parent = displayLabel.rtvParent();
		while (parent instanceof DisplayGroup)
		{
			if (DisplayGroup.FULL_KEY_GROUP_ID.equals(parent.getId()))
			{
				return true;
			}
			parent = parent.rtvParent();
		}

		return false;
	}

	/**
	 * Determines if the specified DisplayButtonLink is the child of the KEYS display group
	 * 
	 * @param displayButtonLink
	 *            - the display button or link
	 * 
	 * @return true if the display button or link belongs to the KEY group
	 */
	private boolean isDisplayButtonLinkInKeyGroup(DisplayButtonLink displayButtonLink)
	{
		Element parent = displayButtonLink.rtvParent();
		while (parent != null && parent instanceof DisplayGroup)
		{
			if (DisplayGroup.FULL_KEY_GROUP_ID.equals(parent.getId()))
			{
				return true;
			}
			parent = parent.rtvParent();
		}

		return false;
	}

	/**
	 * Default elements in the HTML
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param div
	 *            - the DIV element to contain these elements
	 */
	public void addDefaultElements(HTMLDocumentImpl htmlDocument, HTMLElement div)
	{
		HTMLInputElementImpl firstField = createHiddenInputElement(htmlDocument, OBJ_FIRSTFIELD, false);
		firstField.setValue(firstInputField);

		HTMLInputElementImpl lastField = createHiddenInputElement(htmlDocument, OBJ_LASTFIELD, false);
		lastField.setValue(lastInputField);

		HTMLInputElementImpl cursorField = createHiddenInputElement(htmlDocument, OBJ_CURSOR, false);
		if (firstInputFieldInError.length() == 0)
		{
			cursorField.setValue(firstInputField);
		}
		else
		{
			cursorField.setValue(firstInputFieldInError);
		}

		// override the cursor when a row has just been processed
		if (fhd.getLastRepeatingGroup().length() > 0)
		{
			int index = fhd.getLastRepeatingRow();
			String fieldId = RepeatingDataManager.rtvSelectionOptionId(fhd.getLastRepeatingGroup())
							+ RepeatingDataManager.INDEX_DELIMITER
							+ Toolbox.leftZeroPad(index, RepeatingDataManager.LIST_INDEX_LEN);
			cursorField.setValue(fieldId);
			fhd.setLastRepeatingGroup("");
			fhd.setLastRepeatingRow(0);
		}

		// get first child
		Node refNode = div.getFirstChild();

		// add everything
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_NAME, true), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_FIELDSET, false), refNode);
		div.insertBefore(cursorField, refNode);
		div.insertBefore(firstField, refNode);
		div.insertBefore(lastField, refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_SCNO, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_CUR_SCRN, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_FCT, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_FLDVAL, true), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_MSGSEV, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_FKEY, true), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_CHCKR, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_NMSGS, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_RCHKR, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_VFKEYS, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_FKYTXT, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_FFKYTXT, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_UPDERROR, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_UNITID, true), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_SYSTEMID, true), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_TRANID, true), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_SUPMSG, true), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_REFERTOUSERID, true), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_LOADFIELDSET, true), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_LOADFIELD, true), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_MSGAMT, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_MSGBRNM, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_MSGDRCR, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_DEFSUPER, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_MSGIDS, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_KEYEXSTSET, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_KEYDSPSET, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_JOBID, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_OPTIONID, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_CRM_S, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_TASKID, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_TASKPROCID, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_TASKTYPE, false), refNode);
		div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_COMPLETETASK, false), refNode);

		// Comment by Rhose 17June2011 as these lines generate the following error
		// The method keys() is undefined for the type Map<String,Integer> HTMLToolbox.java
		// EquationFunction/src/com/misys/equation/function/tools line 2555 Java Problem
		// add all the group display
		// Enumeration<String> keys = displayGroupHandler.getCurrentDisplay().keys();
		// while (keys.hasMoreElements())
		// {
		// String groupId = keys.nextElement();
		// div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_DSPGRPID + groupId, true), refNode);
		// }

		for (Map.Entry<String, Integer> key : displayGroupHandler.getCurrentDisplay().entrySet())
		{
			div.insertBefore(createHiddenInputElement(htmlDocument, OBJ_DSPGRPID + key.getKey(), true), refNode);
		}
	}

	/**
	 * Generate the HTML for the first row of a group. This creates a new row with a table within it. The table will have a maximum
	 * of 2 cells: one cell for the text and another cell for the buttons/shutter
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param tableId
	 *            - the table Id
	 * @param rowId
	 *            - the unique group id
	 * @param label
	 *            - the label
	 * @param desc
	 *            - the description
	 * @param col
	 *            - number of column
	 * @param buttons
	 *            - allocate cell for buttons?
	 * 
	 * @return the row HTML
	 */
	public HTMLTableRowElementImpl getFirstRow(HTMLDocumentImpl htmlDocument, String tableId, String rowId, String label,
					String desc, int col, boolean buttons)
	{
		// the first row will contain a table. This table will contain all the details
		HTMLTableElementImpl innerTable = createTableElement(htmlDocument, "", "100%");

		// create the row
		HTMLTableRowElementImpl fieldTableRow = createRowElement(htmlDocument, "");
		HTMLTableCellElementImpl fieldTableCell = createCellElement(htmlDocument, "", "100%");
		fieldTableCell.setColSpan(col);
		fieldTableCell.appendChild(innerTable);
		fieldTableRow.appendChild(fieldTableCell);

		// now add all the details into the table

		// determine number of columns for the title bar (1/3 of the row will be allocated to the buttons)
		int colSpan = col;
		if (buttons)
		{
			colSpan = (int) (Math.ceil(col * 0.66));
			if (colSpan == col)
			{
				colSpan = col - 1;
			}
		}

		// create the row
		HTMLTableRowElementImpl innerRow = createRowElement(htmlDocument, rowId + GROUPCONTROL_ROW);
		innerRow.setClassName(innerRow.getClassName() + " " + "groupHeaderRowClass");

		// create the ondoubleclick event
		innerRow.setAttribute("ondblclick", "setRowDoubleClickAction('" + rowId + "','" + tableId + "')");

		// add the row to the main table
		innerTable.appendChild(innerRow);

		// create the cell
		HTMLTableCellElementImpl labelTableCell = createCellElement(htmlDocument, "", "");
		labelTableCell.setNoWrap(true);
		labelTableCell.setColSpan(colSpan);
		labelTableCell.setClassName(labelTableCell.getClassName() + " " + "groupHeaderCellClass");
		if (desc.trim().length() > 0)
		{
			labelTableCell.setTitle(desc);
		}
		innerRow.appendChild(labelTableCell);

		// create the shutter button (hide)
		HTMLScriptElementImpl hideShutter = createScriptElement(htmlDocument, "");
		hideShutter.setText("setHideShutterButton('" + rowId + "','" + tableId + "')");
		labelTableCell.appendChild(hideShutter);

		// create the label field
		labelTableCell.appendChild(createSpanElement(htmlDocument, "", "", label));

		// create the hidden element for the shutter status
		HTMLInputElementImpl shutterStatus = createHiddenInputElement(htmlDocument, tableId + GROUP_SHTR_OPEN, true);
		shutterStatus.setValue("true");
		labelTableCell.appendChild(shutterStatus);

		// create the cell for the buttons
		if (buttons)
		{
			labelTableCell = createCellElement(htmlDocument, "", "");
			labelTableCell.setNoWrap(true);
			labelTableCell.setColSpan(col - colSpan);
			labelTableCell.setClassName(labelTableCell.getClassName() + " " + "groupHeaderCellClass");
			innerRow.appendChild(labelTableCell);

			// create the repository of other buttons
			HTMLDivElementImpl div = createDivElement(htmlDocument, rowId + GROUPBUTTONS_DIV);
			div.setClassName(div.getClassName() + " " + "groupButtonDiv");
			labelTableCell.appendChild(div);
			HTMLTableElementImpl table = createTableElement(htmlDocument, rowId + GROUPBUTTONS_TABLE, "");
			if (rtl)
			{
				table.setAlign("left");
			}
			else
			{
				table.setAlign("right");
			}
			table.appendChild(createRowElement(htmlDocument, ""));
			div.appendChild(table);

			// create the repository of group buttons
			div = createDivElement(htmlDocument, rowId + GROUPTABS_DIV);
			div.setClassName(div.getClassName() + " " + "groupTabDiv");
			labelTableCell.appendChild(div);
			table = createTableElement(htmlDocument, rowId + GROUPTABS_TABLE, "");
			if (rtl)
			{
				table.setAlign("left");
			}
			else
			{
				table.setAlign("right");
			}
			table.appendChild(createRowElement(htmlDocument, ""));
			div.appendChild(table);
		}

		// return the row
		return fieldTableRow;
	}

	/**
	 * Generate the HTML for the first row of a group. This does not create a table as there is no need for it. The code here is a
	 * hacked version of the above method. Basically the inner row processing
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param tableId
	 *            - the table Id
	 * @param rowId
	 *            - the unique group id
	 * @param label
	 *            - the label
	 * @param desc
	 *            - the description
	 * @param col
	 *            - number of column
	 * 
	 * @return the row HTML
	 */
	public HTMLTableRowElementImpl getFirstRow(HTMLDocumentImpl htmlDocument, String tableId, String rowId, String label,
					String desc, int col)
	{
		// create the row
		HTMLTableRowElementImpl innerRow = createRowElement(htmlDocument, rowId + GROUPCONTROL_ROW);
		innerRow.setClassName(innerRow.getClassName() + " " + "groupHeaderRowClass");

		// create the ondoubleclick event
		innerRow.setAttribute("ondblclick", "setRowDoubleClickAction('" + rowId + "','" + tableId + "')");

		// create the cell
		HTMLTableCellElementImpl labelTableCell = createCellElement(htmlDocument, "", "");
		labelTableCell.setNoWrap(true);
		labelTableCell.setColSpan(col);
		labelTableCell.setClassName(labelTableCell.getClassName() + " " + "groupHeaderCellClass");
		if (desc.trim().length() > 0)
		{
			labelTableCell.setTitle(desc);
		}
		innerRow.appendChild(labelTableCell);

		// create the shutter button (hide)
		HTMLScriptElementImpl hideShutter = createScriptElement(htmlDocument, "");
		hideShutter.setText("setHideShutterButton('" + rowId + "','" + tableId + "')");
		labelTableCell.appendChild(hideShutter);

		// create the label field
		labelTableCell.appendChild(createSpanElement(htmlDocument, "", "", label));

		// create the hidden element for the shutter status
		HTMLInputElementImpl shutterStatus = createHiddenInputElement(htmlDocument, tableId + GROUP_SHTR_OPEN, true);
		shutterStatus.setValue("true");
		labelTableCell.appendChild(shutterStatus);

		// return the row
		return innerRow;
	}

	/**
	 * Generate the HTML elements (in the HTML document) representing a DisplayLabel definition
	 * <p>
	 * This generates simple text output rendering of a Layout Text Line definition
	 * 
	 * @param htmlDocument
	 *            - HTML document
	 * @param displayAttributeFieldSet
	 *            the parent <code>DisplayAttributesSet</code> (for logging purposes)
	 * @param displayLabel
	 *            - the display label to be rendered
	 */
	private HTMLElementImpl getDisplayLabelHTMLElement(HTMLDocumentImpl htmlDocument,
					DisplayAttributesSet displayAttributeFieldSet, DisplayLabel displayLabel, DisplayItemList displayItems,
					int fieldIndex) throws EQException
	{
		functionDebugInfo.printHTMLScreenTextLine(displayAttributeFieldSet.getId(), displayLabel.getId());

		// create the row
		HTMLTableRowElementImpl fieldTableRow = createRowElement(htmlDocument, displayLabel.getId() + ROW_SUFFIX);

		// Output a single cell that spans the whole table
		HTMLTableCellElementImpl labelTableCell = createCellElement(htmlDocument, TD1_PREFIX + displayLabel.getId(), "");

		// labelTableCell.setNoWrap(true);
		labelTableCell.setColSpan(DEFAULT_COL);
		labelTableCell.setClassName(NON_RESIZE);
		fieldTableRow.appendChild(labelTableCell);

		// retrieve label if there is any
		String label = "";
		label = displayLabel.rtvLabel(eqUser);

		// determine the style
		String style = ELRuntime.runStringScript(displayLabel.getDisplayStyle(), functionData, displayLabel.getId(),
						LanguageResources.getString("HTMLToolbox.displayStyle"), "", ELRuntime.DB_VALUE).trim();

		// display a line?
		if (displayLabel.getLabelType().equals(Element.TEXT_VALUE_LINE))
		{
			org.w3c.dom.Element hrElement = htmlDocument.createElement("hr");
			// Use either the default style, or the the specified style.
			hrElement.setAttribute("class", style.length() == 0 ? " hr_default" : style);
			labelTableCell.appendChild(hrElement);
		}

		// blank line?
		else if (label.trim().length() == 0 || displayLabel.getLabelType().equals(Element.TEXT_VALUE_EMPTY_LINE))
		{
			labelTableCell.appendChild(htmlDocument.createElement("br"));
		}

		// display label
		else
		{
			// Assume the basic style, and append user specified styles
			String labelStyle = "labelText";
			if (style.length() > 0)
			{
				labelStyle += " " + style;
			}
			// Each line in the text string will created as a span, and each of these
			// will have the style applied
			convertCharacterReturn(htmlDocument, labelTableCell, label, labelStyle);

			// check if there are button/hyperlinks to display and displaywithprevious is true
			for (int index = fieldIndex + 1; index < displayItems.size(); index++)
			{
				IDisplayItem displayItem = displayItems.get(index);
				if (displayItem instanceof DisplayButtonLink)
				{
					DisplayButtonLink nextDisplayButtonLink = (DisplayButtonLink) displayItem;
					if (nextDisplayButtonLink.isKeepWithPrevious())
					{
						functionDebugInfo.printHTMLScreenFieldFollowOn(displayAttributeFieldSet.getId(), nextDisplayButtonLink
										.getId());

						DisplayButtonLinkAdaptor nextDisplayButtonLinkAdaptor = layoutAdaptor.getDisplayButtonLinkAdaptor(session,
										nextDisplayButtonLink.rtvBareId());
						// process only when visible
						if (nextDisplayButtonLinkAdaptor.isVisible(nextDisplayButtonLink))
						{
							HTMLScriptElementImpl sriptElement = getDisplayButtonLinkHTMLElement2(nextDisplayButtonLinkAdaptor,
											htmlDocument, displayAttributeFieldSet, nextDisplayButtonLink);
							labelTableCell.appendChild(sriptElement);
						}
					}
					else
					{
						break;
					}
				}
				else
				{
					break;
				}
			}
		}

		// return the row
		return fieldTableRow;
	}
	/**
	 * Generate the field element (in the HTML document) of a field
	 * <p>
	 * This is called with the set of items at this level (group level) in the hierarchy. This is used when checking for follow on
	 * fields; it is assumed that follow on fields are only relevant within a particular level.
	 * 
	 * @param htmlDocument
	 *            - HTML document
	 * @param inputFieldSet
	 *            - the input field set
	 * @param inputField
	 *            - the input field
	 * @param displayAttributeFieldSet
	 *            - the display attributes field set of the input field set
	 * @param displayAttributes
	 *            - the display attribute of the input field
	 * @param items
	 *            The collection of display items (at this level)
	 * @param fieldIndex
	 *            - field index within the field set
	 * @param keyOpen
	 *            - is the field set key open?
	 * @param parentKeyOpen
	 *            - is the parent key open?
	 * 
	 * @return the table row
	 * 
	 * @throws EQException
	 */
	private HTMLElementImpl getFieldHTMLElement(HTMLDocumentImpl htmlDocument, InputFieldSet inputFieldSet, InputField inputField,
					DisplayAttributesSet displayAttributeFieldSet, DisplayAttributes displayAttributes, DisplayItemList items,
					int fieldIndex, boolean keyOpen, boolean parentKeyOpen) throws EQException
	{
		// debug
		functionDebugInfo.printHTMLScreenField(inputFieldSet.getId(), inputField.getId());

		// retrieve the field adaptor
		FieldAdaptor fieldAdaptor = functionAdaptor.getFieldAdaptor(session, inputField.getId(), GAZRecordDataModel.TYP_FIELD);
		AttributesAdaptor attributesAdaptor = layoutAdaptor.getAttributesAdaptor(session, inputField.getId());

		// field data
		FieldData fieldData = functionData.rtvFieldData(inputField.getId());

		// determine whether the field is input capable or not
		boolean inputCapable = true;
		if (parentKeyOpen || (inputField.isKey() && !keyOpen) || (!inputField.isKey() && keyOpen))
		{
			inputCapable = false;
		}

		// no validation
		if (securityLevel.chkNoValidate())
		{
			inputCapable = false;
		}
		// authorisation and no update
		else if (fhd.isLRPAuthTask() && !securityLevel.isUpdateAllowed())
		{
			inputCapable = false;
		}
		// enquire mode, force protect of non-key fields
		else if (securityLevel.isEnquireMode())
		{
			if (!inputField.isKey())
			{
				inputCapable = false;
			}
		}

		// Linked transaction via the play button, then always protect the keys
		if (inputField.isKey() && keyOpen
						&& EquationCommonContext.isChildDesktopSessionKeyProtect(fhd.getFunctionInfo().getSessionType()))
		{
			inputCapable = false;
		}

		// screen set protected checking
		inputCapable = !screenSet.fieldProtected(inputFieldSet, inputField, displayAttributeFieldSet, displayAttributes,
						!inputCapable);

		// determine whether the field is visible or not
		// .. if parent key is open, then the detail screen is not visible
		boolean visible = !parentKeyOpen;

		// .. field is not a key and key is open
		if (visible && !inputField.isKey() && keyOpen)
		{
			visible = false;
		}

		// .. field attributes
		if (visible)
		{
			visible = attributesAdaptor.isVisible(displayAttributes);
		}

		// screen set visibility checking
		visible = screenSet.fieldVisiblity(inputFieldSet, inputField, displayAttributeFieldSet, displayAttributes, visible);

		// create the row
		HTMLTableRowElementImpl fieldTableRow = createRowElement(htmlDocument, inputField.getId() + ROW_SUFFIX);

		// is invisible?
		if (!visible)
		{
			addClass(fieldTableRow, NON_DISPLAY_CLASS);
		}

		// *****************************************************************
		// Label column (1)
		// *****************************************************************
		boolean anyFieldIsMandatory = false;
		HTMLTableCellElementImpl labelTableCell = createCellElement(htmlDocument, TD1_PREFIX + inputField.getId(), "20px");
		labelTableCell.setNoWrap(true);
		fieldTableRow.appendChild(labelTableCell);

		// class name
		String style = ELRuntime.runStringScript(displayAttributes.getDisplayStyleLabel(), functionData, inputField.getId(),
						LanguageResources.getString("HTMLToolbox.displayStyleLabel"), "", ELRuntime.DB_VALUE).trim();

		// create the label field
		HTMLElement labelElement = createSpanElement(htmlDocument, "", "labelTextBold" + " " + style, "");

		// label position on the left hand side
		if (displayAttributes.getLabelPosition() == DisplayAttributes.FIELD_LABEL_LEFT)
		{
			String label = FunctionRuntimeToolbox.getLabel(eqUser, displayAttributes, functionData);
			labelElement.appendChild(createTextElement(htmlDocument, label.replaceAll(EqDataType.CHARACTER_RETURN, "")));
			labelTableCell.appendChild(labelElement);
		}

		// *****************************************************************
		// Input column (2)
		// *****************************************************************
		HTMLTableCellElementImpl inputTableCell = createCellElement(htmlDocument, TD2_PREFIX + inputField.getId(), "");
		inputTableCell.setNoWrap(true);
		inputTableCell.setColSpan(DEFAULT_COL - 1);
		fieldTableRow.appendChild(inputTableCell);

		// create the table
		HTMLTableElementImpl table = createTableElement(htmlDocument, inputField.getId() + "table", "");
		table.setCellPadding("1");
		table.setCellSpacing("0");

		// create the row for the label top / input field / label bottom
		HTMLTableRowElementImpl row1 = createRowElement(htmlDocument, inputField.getId() + "row1");
		HTMLTableRowElementImpl row2 = createRowElement(htmlDocument, inputField.getId() + "row2");
		HTMLTableRowElementImpl row3 = createRowElement(htmlDocument, inputField.getId() + "row3");

		// list of related fields via KeepWithPrevious
		ArrayList<HTMLInputElementImpl> listofKeepWithPrevious = new ArrayList<HTMLInputElementImpl>();

		// field is mandatory?
		boolean mandatory = fieldAdaptor.isMandatory(inputField);
		mandatory = screenSet.fieldMandatory(inputFieldSet, inputField, mandatory);
		if (mandatory)
		{
			anyFieldIsMandatory = true;
		}

		// add the field to the row
		boolean protectedField = fieldData.isLocked() || attributesAdaptor.isProtected(displayAttributes);

		HTMLInputElementImpl previousFieldElement = addInputField(htmlDocument, inputFieldSet, inputField, displayAttributes,
						fieldData, keyOpen, parentKeyOpen, inputCapable && !protectedField, visible, mandatory, "", "", row1, row2,
						row3, true, true);
		listofKeepWithPrevious.add(previousFieldElement);

		// change the field row to input field's style
		style = ELRuntime.runStringScript(displayAttributes.getDisplayStyleRow(), functionData, inputField.getId(),
						LanguageResources.getString("HTMLToolbox.displayStyleRow"), "", ELRuntime.DB_VALUE).trim();

		if (style.length() > 0)
		{
			fieldTableRow.setClassName(fieldTableRow.getClassName() + " " + style);
		}

		// keep track of the highest message severity of these group of fields via KeepWithPrevious
		int msgSev = getMsgSev(fieldData);

		// check if there are follow on fields
		boolean isTop = displayAttributes.getLabelPosition() == DisplayAttributes.FIELD_LABEL_ABOVE;
		boolean isBottom = displayAttributes.getLabelPosition() == DisplayAttributes.FIELD_LABEL_BELOW;

		for (int i = fieldIndex + 1; i < items.size(); i++)
		{
			IDisplayItem displayItem2 = items.get(i);
			// Only process DisplayAttributes - assume that KeepWithPrevious does not apply over
			// different group levels:
			if (displayItem2 instanceof DisplayAttributes)
			{
				DisplayAttributes displayAttributes2 = (DisplayAttributes) displayItem2;
				InputField inputField2 = inputFieldSet.getInputField(displayAttributes2.getId());

				if (displayAttributes2.isKeepWithPrevious())
				{
					previousFieldElement.setAttribute("nextField", inputField2.getId());

					functionDebugInfo.printHTMLScreenFieldFollowOn(inputFieldSet.getId(), inputField2.getId());
					FieldAdaptor fieldAdaptor2 = functionAdaptor.getFieldAdaptor(session, inputField2.getId(),
									GAZRecordDataModel.TYP_FIELD);
					fieldAdaptor2.setFunctionData(fhd, functionData);
					AttributesAdaptor attributesAdaptor2 = layoutAdaptor.getAttributesAdaptor(session, inputField2.getId());
					attributesAdaptor2.setFunctionData(fhd, functionData);
					FieldData fieldData2 = functionData.rtvFieldData(inputField2.getId());

					// determine visibility
					boolean visible2 = attributesAdaptor2.isVisible(displayAttributes2);
					visible2 = screenSet.fieldVisiblity(inputFieldSet, inputField2, displayAttributeFieldSet, displayAttributes2,
									visible2);

					// field is mandatory?
					boolean mandatory2 = fieldAdaptor2.isMandatory(inputField2);
					if (!anyFieldIsMandatory)
					{
						if (mandatory2)
						{
							anyFieldIsMandatory = true;
						}
					}

					// add the input field
					previousFieldElement = addInputField(htmlDocument, inputFieldSet, inputField2, displayAttributes2, fieldData2,
									keyOpen, parentKeyOpen, inputCapable && !fieldData2.isLocked()
													&& !attributesAdaptor2.isProtected(displayAttributes2), visible2, mandatory2,
									inputField.getId(), previousFieldElement.getId(), row1, row2, row3, true, true);
					listofKeepWithPrevious.add(previousFieldElement);
					if (getMsgSev(fieldData2) > msgSev)
					{
						msgSev = getMsgSev(fieldData2);
					}

					// flag to determine whether label top/bottom exists
					if (displayAttributes2.getLabelPosition() == DisplayAttributes.FIELD_LABEL_ABOVE)
					{
						isTop = true;
					}
					if (displayAttributes2.getLabelPosition() == DisplayAttributes.FIELD_LABEL_BELOW)
					{
						isBottom = true;
					}
				}
				else
				{
					// If this 'next' field does not have keep with previous, then stop looking now.
					break;
				}
			}
			else if (displayItem2 instanceof DisplayButtonLink)
			{

				DisplayButtonLink displayButtonLink = (DisplayButtonLink) displayItem2;
				if (displayButtonLink.isKeepWithPrevious())
				{
					functionDebugInfo.printHTMLScreenFieldFollowOn(inputFieldSet.getId(), displayButtonLink.getId());

					DisplayButtonLinkAdaptor displayButtonLinkAdaptor = layoutAdaptor.getDisplayButtonLinkAdaptor(session,
									displayButtonLink.rtvBareId());
					// process only when visible
					if (displayButtonLinkAdaptor.isVisible(displayButtonLink))
					{

						HTMLTableCellElementImpl cell2 = null;
						if (row2.getChildNodes().getLength() > 0)
						{
							cell2 = (HTMLTableCellElementImpl) row2.getLastChild();
						}
						else
						{
							cell2 = createCellElement(htmlDocument, "", "");
						}

						HTMLScriptElementImpl sriptElement = getDisplayButtonLinkHTMLElement2(displayButtonLinkAdaptor,
										htmlDocument, displayAttributeFieldSet, displayButtonLink);
						cell2.appendChild(sriptElement);
					}
				}
				else
				{
					// If this 'next' field does not have keep with previous, then stop looking now.
					break;
				}
			}
			else
			{
				// Stop looking if anything apart from a DisplayAttributes is encountered
				break;
			}
		}

		// now loop through this group of fields via KeepWithPrevious and changes the style to signify error or warning
		if (msgSev > FunctionMessages.MSG_NONE)
		{
			for (int i = 0; i < listofKeepWithPrevious.size(); i++)
			{
				HTMLInputElementImpl input = listofKeepWithPrevious.get(i);
				if (Boolean.valueOf(input.getAttribute("highlightWithPrevious")))
				{
					addMessageSeverityClassName(input, msgSev);
				}
				else if (this.functionMessages.isMessageExists(input.getId()))
				{
					addMessageSeverityClassName(input, msgSev);
				}

				// reposition cursor to the left most field
				if (firstInputFieldInError.equals(input.getId()))
				{
					HTMLInputElementImpl input2 = listofKeepWithPrevious.get(0);
					firstInputFieldInError = input2.getId();
				}
			}
		}

		// add the top labels
		if (isTop)
		{
			table.appendChild(row1);
		}

		// add the input field
		table.appendChild(row2);

		// add the bottom labels
		if (isBottom)
		{
			table.appendChild(row3);
		}

		// now realign the text.
		// .. If top label only exists, align it to the bottom
		if (isTop && !isBottom)
		{
			labelTableCell.setClassName(labelTableCell.getClassName() + " alignBottom");
		}
		// .. If bottom label only exists, then align it to the top.
		else if (isBottom && !isTop)
		{
			labelTableCell.setClassName(labelTableCell.getClassName() + " alignTop");
		}
		// .. just in the middle
		else
		{
			labelTableCell.setClassName(labelTableCell.getClassName() + " alignMiddle");
		}

		// add the table into the column 2
		if (isTop || isBottom || row2.getChildNodes().getLength() > 1)
		{
			inputTableCell.appendChild(table);
		}
		else
		{
			transferChilds(row2.getFirstChild(), inputTableCell);
		}

		// // *****************************************************************
		// // Output column
		// // *****************************************************************
		// HTMLTableCellElementImpl outputCell = createCellElement(htmlDocument, "", "");
		// outputCell = createCellElement(htmlDocument, "", "");
		// outputCell.setNoWrap(true);
		// row2.appendChild(outputCell);
		//
		// // The output column should be suppressed (blank) if the output is shown as the input:
		// String outputText = (inputCapable && !protectedField) || !displayAttributes.isShowDescriptionAsValue() ? fieldData
		// .getOutputMaskValue(displayAttributes.rtvMask(eqUser)) : "";
		//
		// // create the output field value
		// HTMLElement outputElement = createSpanElement(htmlDocument, inputField.getId() + ID_OUTPUT, outputText);
		// outputElement.setClassName(outputElement.getClassName() + " " + "labelText");
		//
		// // create the hidden database field value
		// HTMLInputElementImpl dbElement;
		// if (debugMode)
		// {
		// dbElement = createInputElement(htmlDocument, inputField.getId() + ID_DB, "input");
		// dbElement.setReadOnly(true);
		// dbElement.setClassName("wf_ul wf_hi wf_default wf_field");
		// dbElement.setValue(fieldData.getDbValue());
		// }
		// else
		// {
		// dbElement = createHiddenInputElement(htmlDocument, inputField.getId() + ID_DB);
		// dbElement.setValue(fieldData.getDbValue());
		// }
		//
		// // add the fields to the cell
		// outputCell.appendChild(outputElement);
		// outputCell.appendChild(dbElement);
		//
		// // add rtl element for right-to-left language
		// if (rtl)
		// {
		// HTMLInputElementImpl rtlElement = createHiddenInputElement(htmlDocument, inputField.getId() + ID_RTL);
		// rtlElement.setValue(String.valueOf(fieldData.getOrientation()));
		// outputCell.appendChild(rtlElement);
		// }
		//
		// any field is mandatory, then change the label for visual clue
		if (anyFieldIsMandatory)
		{
			labelElement.setClassName(labelElement.getClassName() + " " + "mandatoryText");
		}

		// return the row
		return fieldTableRow;
	}
	/**
	 * Generate the elements in Column 2
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param inputField
	 *            - the field
	 * @param displayAttributes
	 *            - the display attribute of the input field
	 * @param fieldData
	 *            - the FieldData
	 * @param keyOpen
	 *            - key open for input for the field set?
	 * @param parentKeyOpen
	 *            - is the parent key open?
	 * @param inputCapable
	 *            - input capable?
	 * @param visible
	 *            - visible?
	 * @param leftMostField
	 *            - the field Id of the left most field (if this field has the KeepWithPrevious set to True
	 * @param previousField
	 *            - the field Id of the previous field (if this field has the KeepWithPrevious set to True)
	 * @param row1
	 *            - row 1 - populated when label on top
	 * @param row2
	 *            - row 2 - field's row
	 * @param row3
	 *            - row 3 - populated when label on bottom
	 * @param dbFieldCreate
	 *            - create db field?
	 * @param possibleInput
	 *            - whether field can be input capable. If this is false, certain attributes are supppress
	 * 
	 * @return the input field element
	 * 
	 * @throws EQException
	 */
	private HTMLInputElementImpl addInputField(HTMLDocumentImpl htmlDocument, InputFieldSet inputFieldSet, InputField inputField,
					DisplayAttributes displayAttributes, FieldData fieldData, boolean keyOpen, boolean parentKeyOpen,
					boolean inputCapable, boolean visible, boolean mandatory, String leftMostField, String previousField,
					HTMLTableRowElementImpl row1, HTMLTableRowElementImpl row2, HTMLTableRowElementImpl row3,
					boolean dbFieldCreate, boolean possibleInput) throws EQException
	{
		boolean repeating = row3 == null;
		currentNumberField++;

		// create the cell for the label top
		HTMLTableCellElementImpl cell1 = createCellElement(htmlDocument, "", "");

		// create the cell for the actual input field
		HTMLTableCellElementImpl cell2 = createCellElement(htmlDocument, "", "");

		// create the cell for the label bottom
		HTMLTableCellElementImpl cell3 = createCellElement(htmlDocument, "", "");

		// add to the last cell if possible
		if (row2 != null
						&& row2.getChildNodes().getLength() > 0
						&& (displayAttributes.getLabelPosition() == DisplayAttributes.FIELD_LABEL_NONE || displayAttributes
										.getLabelPosition() == DisplayAttributes.FIELD_LABEL_LEFT))
		{
			cell1 = (HTMLTableCellElementImpl) row1.getLastChild();
			cell2 = (HTMLTableCellElementImpl) row2.getLastChild();
			cell3 = (HTMLTableCellElementImpl) row3.getLastChild();
		}

		// create the input field
		String fieldId = getInputFieldId(inputField);
		HTMLInputElementImpl input = null;
		if (inputField.isPassword())
		{
			input = createInputElement(htmlDocument, fieldId, possibleInput, "password");
		}
		else
		{
			input = createInputElement(htmlDocument, fieldId, possibleInput, "input");
		}
		input.setTitle(displayAttributes.rtvDescription(eqUser));
		input.setAttribute("highlightWithPrevious", Boolean.toString(displayAttributes.isHighlightWithPrevious()));
		// set the default class name
		StringBuffer className = new StringBuffer(input.getClassName());
		className.append(" " + "wf_ul wf_hi wf_default wf_field");
		if (displayAttributes.addSpooledFileScript())
		{
			className.append(" " + "mousePointerHover");
			input.setAttribute("onclick", functionData.rtvFieldData(displayAttributes.getId()).getInputValue());
		}
		// maximum displayed characters
		int length = getFieldHTMLSize(inputField.getDataType(), inputField.getSize(), inputField.getDecimals(), inputField
						.getExtendedAttribute());
		Double fieldSize = Math.ceil(length + (length * 0.15));
		input.setSize(String.valueOf(fieldSize.intValue()));
		updateHTMLFieldWidth(input, length, repeating);

		// maximum number of characters
		input.setMaxLength(length);

		// is field must be in upper case?
		if (inputField.isUpperCase())
		{
			className.append(" " + "wf_UPPERCASE");
		}

		// is invisible?
		if (!visible)
		{
			className.append(" " + NON_DISPLAY_CLASS1);
		}

		// is field amount, then right-justify
		if (inputField.getDataType().equals(EqDataType.TYPE_PACKED) || inputField.getDataType().equals(EqDataType.TYPE_ZONED)
						|| displayAttributes.isShowAsRightAligned())
		{
			className.append(" " + "wf_RIGHT_ALIGN");
		}

		// field protected?
		if (!inputCapable)
		{
			input.setReadOnly(true);
			input.setTabIndex(-1);
			className.append(" " + "wf_pr");
		}

		// field mandatory?
		if (mandatory)
		{
			input.setAttribute("mandatory", EqDataType.YES);
			className.append(" mandatory");
		}

		// set the class name
		input.setClassName(className.toString());

		// key field?
		if (inputField.isKey())
		{
			input.setAttribute("key", EqDataType.YES);
		}

		// field type
		String fieldType = inputField.getDataType();
		if (EqDataType.isDate(fieldType) || EqDataType.isNumeric(fieldType))
		{
			input.setAttribute("fieldType", fieldType);
		}

		// mask character
		String maskStr = displayAttributes.rtvMask(eqUser).trim();

		// onBlur event script
		if (displayAttributes.getOnBlurEventScript().trim().length() > 0)
		{
			input.setAttribute("onBlurScript", displayAttributes.getOnBlurEventScript());
			onBlurFieldIds.add(fieldId);
		}

		// left-most field
		if (leftMostField.length() > 0)
		{
			input.setAttribute("leftMostField", leftMostField);
		}

		// previous field
		if (previousField.length() > 0)
		{
			input.setAttribute("previousField", previousField);
		}

		// default to LTR
		boolean inputInRtl = false;

		// display group
		if (possibleInput)
		{
			// label
			input.setAttribute("label", displayAttributes.rtvLabel(eqUser));

			// previous value
			input.setAttribute("previousValue", input.getValue());
		}

		// rtl in field data has been set, then default from the attribute
		if (fieldData.getOrientation() == FieldData.ORIENT_NOT_SET)
		{
			fieldData.setOrientation(rtl && displayAttributes.isRtl() ? FieldData.ORIENT_RTL : FieldData.ORIENT_LTR);
		}

		// rtl?
		if (rtl && fieldData.chkRTL())
		{
			input.setClassName(input.getClassName().replaceAll("wf_LTOR", "wf_RTOL"));
			inputInRtl = true;
		}

		// The value to show depends on whether the show description (output) as value
		// (input) processing applies:
		String fieldValue = fieldData.getInputValue();
		fieldData.setShowDescAsValue(false);
		if (input.getReadOnly() && displayAttributes.isShowDescriptionAsValue())
		{
			fieldValue = fieldData.rtvOutputMaskValue(functionData, maskStr, true);
			if (fieldValue.length() > 0)
			{
				// does it need to increase the size in case description is longer?
				if (fieldValue.length() > fieldSize)
				{
					input.setSize(String.valueOf(fieldValue.length()));
					updateHTMLFieldWidth(input, fieldValue.length(), repeating);
				}
				// does it need to shrink the size in case description is shorter?
				if (fieldValue.length() < fieldSize)
				{
					int outputLength = fieldValue.length();
					Double outputFieldSize = Math.ceil(outputLength + (outputLength * 0.15));
					input.setSize(String.valueOf(outputFieldSize.intValue()));
					updateHTMLFieldWidth(input, outputFieldSize.intValue(), repeating);
				}
				// add some attributes
				fieldData.setShowDescAsValue(true);
				input.setAttribute("showDescriptionAsValue", "true");
			}
		}

		// RTL?
		if (inputInRtl)
		{
			input.setValue(Toolbox.convertTextRTLForDisplay(fieldValue, Toolbox.parseInt(inputField.getSize(), 0), ccsid,
							BidiStringType.ST9));
		}

		// CCSID support RTL, then try to use this conversion
		else if (rtlCcsid)
		{
			String str = Toolbox.convertTextRTLForDisplay(fieldValue, Toolbox.parseInt(inputField.getSize(), 0), ccsid, 0);
			input.setValue(str);
		}

		// others, just set it
		else
		{
			input.setValue(fieldValue);
		}

		// any error, then change the class
		int msgSev = getMsgSev(fieldData);
		if (msgSev != FunctionMessages.MSG_NONE)
		{
			anyFieldMsgInShutter = true;
			anyFieldMsgInGroup = msgSev;
		}

		// class name
		String style = ELRuntime.runStringScript(displayAttributes.getDisplayStyleValue(), functionData, fieldId,
						LanguageResources.getString("HTMLToolbox.displayStyleValue"), "", ELRuntime.DB_VALUE).trim();
		if (style.length() > 0)
		{
			input.setClassName(input.getClassName() + " " + style);
		}

		// append to the document
		cell2.appendChild(input);

		// valid value defined?
		String validValueStr = inputField.rtvValidValues(eqUser);
		// Show light bulb with selection options but only if there are many selections or selection is not '1'
		if (possibleInput
						&& !validValueStr.equals("")
						&& (linkedFunctionTool == null || (linkedFunctionTool != null && !linkedFunctionTool
										.predictableLinkedFunction())))
		{
			input.setAttribute("widget", "VDV");
			HTMLScriptElementImpl script = createScriptElement(htmlDocument, "");
			Hashtable<String, String> mapping = getMapping(inputField, displayAttributes);
			mapping.put("&WID", "VDV");
			mapping.put("&ADDFLD1", validValueStr);
			mapping.put("&ADDFLD2", "");
			script.setText(getScriptWidget(input, inputField, displayAttributes, "VDV", mapping));
			cell2.appendChild(script);
			// HTMLScriptElementImpl script = createScriptElement(htmlDocument, inputField.getId() + "ValidValues");
			// script.setText(getScriptValidValues(inputField.getId(), validValueStr));
			// cell2.appendChild(script);
		}

		// Y or N?
		if (inputField.getDataType().equals(EqDataType.TYPE_BOOLEAN) && !displayAttributes.getWidget().equals("YNO"))
		{
			input.setAttribute("widget", "YNO");
			HTMLScriptElementImpl script = createScriptElement(htmlDocument, "");
			script.setText(getScriptWidget(input, inputField, displayAttributes, "YNO", getMapping(inputField, displayAttributes)));
			cell2.appendChild(script);
		}

		// widget button and script
		String widgetType = "";
		String widgetScript = "";
		if (!displayAttributes.getWidget().equals(""))
		{
			widgetType = displayAttributes.getWidget();
			input.setAttribute("widget", widgetType);
			HTMLScriptElementImpl script = createScriptElement(htmlDocument, "");
			widgetScript = getScriptWidget(input, inputField, displayAttributes, displayAttributes.getWidget(), getMapping(
							inputField, displayAttributes));
			script.setText(widgetScript);
			cell2.appendChild(script);
		}

		// prompt script
		String scriptPmt = "";
		if (possibleInput && !displayAttributes.getPrompt().equals(""))
		{
			// get the pv field set
			PVFieldSet pvFieldSet = inputField.getPvFieldSet(Toolbox.getFirstWord(displayAttributes.getPrompt()));

			// create the script element
			HTMLScriptElementImpl script = createScriptElement(htmlDocument, "");
			scriptPmt = getScriptPMT(function, inputField, displayAttributes.getPrompt(), pvFieldSet.getDecode(), pvFieldSet
							.getSecurity());
			script.setText(scriptPmt);
			cell2.appendChild(script);

			// create the help script element
			// HTMLScriptElementImpl scriptHelp = createScriptElement(htmlDocument, "");
			// scriptHelp.setText(scriptPmt.replace("setPromptButtonNoWF", "setHelpPromptButton"));
			// cell2.appendChild(scriptHelp);
			//
			// // create the predictive list script element
			// HTMLScriptElementImpl scriptPredictive = createScriptElement(htmlDocument, "");
			// scriptPredictive.setText(scriptPmt.replace("setPromptButtonNoWF", "setPredictiveListButton"));
			// cell2.appendChild(scriptPredictive);

			// allow predictive texting?
			if (displayAttributes.isAllowPredictivePrompt())
			{
				EquationPVMetaData pvMetaData = session.getUnit()
								.getPVMetaData(Toolbox.getFirstWord(displayAttributes.getPrompt()));

				if (pvMetaData.getPvOvals().size() > 0)
				{
					EquationPVFieldMetaData fmd = pvMetaData.rtvFieldMetaData(pvMetaData.getPvOvals().get(0));
					input.setAttribute("predPrompt", fmd.getId());
					if (input.getMaxLength() < fmd.getLength())
					{
						input.setAttribute("fieldLength", String.valueOf(length));
						input.setMaxLength(fmd.getLength());
					}
				}
				else
				{
					input.setAttribute("predPrompt", "");
				}
			}
		}

		// validate button
		if (possibleInput && inputField.hasPvFieldSets() && !displayAttributes.isHideValidateWidget() && !uxp)
		{
			HTMLScriptElementImpl scriptVal = createScriptElement(htmlDocument, "");
			scriptVal.setText("showValidateFieldButton('" + fieldId + "')");
			cell2.appendChild(scriptVal);
		}

		// linked function
		if (displayAttributes.getLinkedFuncId().trim().length() > 0
						&& !EquationCommonContext.isChildDesktopSessionPopup((fhd.getFunctionInfo().getSessionType())))
		{
			if (FunctionRuntimeToolbox.isAllowLinkFunction(fhd.getFunctionInfo().getSessionType(), eqUser, displayAttributes
							.getLinkedFuncId(), fhd.getFunctionInfo().isWebFacingInstalled()))
			{
				String optionDesc = eqUser.getEquationUnit().getRecords().getGBRecord(displayAttributes.getLinkedFuncId())
								.getProgramTitle();

				HTMLScriptElementImpl scriptVal = createScriptElement(htmlDocument, "");
				optionDesc = Toolbox.rplSlash(Toolbox.rplQuote(Toolbox.rplSingleQuote(optionDesc.trim())));
				String contextStr = Toolbox.rplSlash(Toolbox.rplQuote(Toolbox.rplSingleQuoteWithSlashSingleQuote(displayAttributes
								.getLinkedFuncContext())));
				String legacyOption = Toolbox.cvtBooleanToYN(eqUser.getEquationUnit().isLegacyOption(
								displayAttributes.getLinkedFuncId()));
				scriptVal.setText("showLinkedFunctionButton('" + fieldId + "','" + displayAttributes.getLinkedFuncId() + "','"
								+ contextStr + "','" + optionDesc + "','" + legacyOption + "')");

				cell2.appendChild(scriptVal);
			}
		}

		// label (top or bottom)?
		if (displayAttributes.getLabelPosition() == DisplayAttributes.FIELD_LABEL_ABOVE
						|| displayAttributes.getLabelPosition() == DisplayAttributes.FIELD_LABEL_BELOW)
		{
			HTMLElement labelElement = createSpanElement(htmlDocument, "", "labelTextTopBottom", FunctionRuntimeToolbox.getLabel(
							eqUser, displayAttributes, functionData));

			if (mandatory)
			{
				labelElement.setClassName(labelElement.getClassName() + " " + "mandatoryText");
			}

			// if there is top and bottom, then this field is probably not a continuation of the
			// previous field, so add some spaces in between
			if (previousField.length() > 0)
			{
				cell1.setClassName(cell1.getClassName() + " defaultSpaceTopBottom");
				cell2.setClassName(cell2.getClassName() + " defaultSpaceTopBottom");
				cell3.setClassName(cell3.getClassName() + " defaultSpaceTopBottom");
			}

			// right align?
			String rightAlign = "";
			if (inputField.getDataType().equals(EqDataType.TYPE_PACKED) || inputField.getDataType().equals(EqDataType.TYPE_ZONED)
							|| fieldData.chkRTL())
			{
				rightAlign = "wf_RIGHT_ALIGN";
			}

			// top or bottom?
			if (displayAttributes.getLabelPosition() == DisplayAttributes.FIELD_LABEL_ABOVE)
			{
				cell1.setClassName(cell1.getClassName() + " " + rightAlign);
				cell1.appendChild(labelElement);
			}
			else
			{
				cell3.setClassName(cell3.getClassName() + " " + rightAlign);
				cell3.appendChild(labelElement);
			}
		}

		// description and database field

		// The output column should be suppressed (blank) if the output is shown as the input:
		String outputText = (inputCapable || !displayAttributes.isShowDescriptionAsValue()) ? fieldData.rtvOutputMaskValue(
						functionData, maskStr, false) : "";
		if (rtlCcsid)
		{
			outputText = Toolbox.convertTextRTLForDisplay(outputText, outputText.length(), ccsid, 0);
		}

		// create the output field value
		if (possibleInput || outputText.length() > 0)
		{
			HTMLElement outputElement = createDefaultSpanElement(htmlDocument, fieldId + ID_OUTPUT, outputText);
			outputElement.setClassName(outputElement.getClassName() + " " + "descriptionText");

			style = ELRuntime.runStringScript(displayAttributes.getDisplayStyleDescription(), functionData,
							displayAttributes.getId(), LanguageResources.getString("HTMLToolbox.displayStyleDesc"), "",
							ELRuntime.DB_VALUE).trim();
			if (style.length() > 0)
			{
				outputElement.setClassName(outputElement.getClassName() + " " + style);
			}
			cell2.appendChild(outputElement);
		}

		// any script for the description?
		if (widgetScript.indexOf("setListButton") >= 0)
		{
			HTMLScriptElementImpl script = createScriptElement(htmlDocument, "");
			script.setText("setListOutputDesc('" + fieldId + "', '" + widgetType + "')");
			cell2.appendChild(script);
		}

		// create the hidden database field value
		if ((dbFieldCreate && !EqDataType.isAlpha(fieldData.getFieldType()) || debugMode))
		{
			HTMLInputElementImpl dbElement;
			if (debugMode)
			{
				dbElement = createInputElement(htmlDocument, fieldId + ID_DB, false, "input");
				dbElement.setReadOnly(true);
				dbElement.setClassName("wf_ul wf_hi wf_default wf_field");
				dbElement.setValue(fieldData.getDbValue());
				cell2.appendChild(dbElement);
			}
			else
			{
				dbElement = createHiddenInputElement(htmlDocument, fieldId + ID_DB, false);
				dbElement.setValue(fieldData.getDbValue());
				cell2.appendChild(dbElement);
			}
		}

		// add rtl element for right-to-left language
		if ((possibleInput && rtl) || inputInRtl)
		{
			HTMLInputElementImpl rtlElement = createHiddenInputElement(htmlDocument, fieldId + ID_RTL, true);
			rtlElement.setValue(String.valueOf(fieldData.getOrientation()));
			cell2.appendChild(rtlElement);
		}

		// set cursor to the first input field
		if (!input.getReadOnly() && visible)
		{
			// first input field
			if (firstInputField.length() == 0)
			{
				firstInputField = fieldId;
			}

			// first input field in error
			if (firstInputFieldInError.length() == 0)
			{
				if (getMsgSev(fieldData) > FunctionMessages.MSG_NONE)
				{
					firstInputFieldInError = fieldId;
				}
			}

			// last input field
			lastInputField = fieldId;
		}

		// attach the events
		if (possibleInput)
		{
			input.setAttribute("onfocus", "javascript:return inputFieldFocus(event);");
			input.setAttribute("onblur", "javascript:return inputFieldBlur(event);");
			input.setAttribute("onkeydown", "javascript:return inputFieldKeyDown(event);");
		}

		// append the cells
		if (row1 != null)
		{
			row1.appendChild(cell1);
		}
		if (row2 != null)
		{
			row2.appendChild(cell2);
		}
		if (row2 != null)
		{
			row3.appendChild(cell3);
		}

		// return the input field element
		return input;
	}

	/**
	 * Generate the HTML elements (in the HTML document) representing a DisplayButtonLink definition
	 * <p>
	 * This generates simple text output rendering of a Layout Button Link definition
	 * 
	 * @param displayButtonLinkAdaptor
	 *            - the DisplayButtonLinkAdaptor
	 * @param htmlDocument
	 *            - HTML document
	 * @param displayAttributeFieldSet
	 *            - the parent <code>DisplayAttributesSet</code> (for logging purposes)
	 * @param displayButtonLink
	 *            - the buttonlink to convert to an html element
	 * @param displayItems
	 *            - the display items
	 * @param fieldIndex
	 *            - current index in the items list
	 * @param origin
	 *            - value is 1 for items and 2 for groups
	 * @return
	 * @throws EQException
	 */
	private HTMLElementImpl getDisplayButtonLinkHTMLElement(DisplayButtonLinkAdaptor displayButtonLinkAdaptor,
					HTMLDocumentImpl htmlDocument, DisplayAttributesSet displayAttributeFieldSet,
					DisplayButtonLink displayButtonLink, DisplayItemList displayItems, int fieldIndex, int origin)
					throws EQException
	{
		functionDebugInfo.printHTMLScreenTextLine(displayAttributeFieldSet.getId(), displayButtonLink.getId());

		// create the row
		HTMLTableRowElementImpl fieldTableRow = createRowElement(htmlDocument, displayButtonLink.getId() + ROW_SUFFIX);

		// Output a single cell that spans the whole table
		HTMLTableCellElementImpl labelTableCell = createCellElement(htmlDocument, TD1_PREFIX + displayButtonLink.getId(), "");

		// first column will be blank
		labelTableCell.setClassName(NON_RESIZE);
		labelTableCell.appendChild(htmlDocument.createTextNode(HTML_NBSP));
		fieldTableRow.appendChild(labelTableCell);

		// second column will contain the element to render the button
		labelTableCell = createCellElement(htmlDocument, TD2_PREFIX + displayButtonLink.getId(), "");
		labelTableCell.setColSpan(DEFAULT_COL - 1);
		labelTableCell.setClassName(NON_RESIZE);
		labelTableCell.appendChild(getDisplayButtonLinkHTMLElement2(displayButtonLinkAdaptor, htmlDocument,
						displayAttributeFieldSet, displayButtonLink));
		fieldTableRow.appendChild(labelTableCell);

		// Check if the next buttons/links display with previous flag is on based on the caller
		if (origin == 1 || origin == 2)
		{
			for (int index = fieldIndex + 1; index < displayItems.size(); index++)
			{
				IDisplayItem displayItem = displayItems.get(index);
				if (displayItem instanceof DisplayButtonLink)
				{
					DisplayButtonLink nextDisplayButtonLink = (DisplayButtonLink) displayItem;
					if (nextDisplayButtonLink.isKeepWithPrevious())
					{
						functionDebugInfo.printHTMLScreenFieldFollowOn(displayAttributeFieldSet.getId(), nextDisplayButtonLink
										.getId());

						DisplayButtonLinkAdaptor nextDisplayButtonLinkAdaptor = layoutAdaptor.getDisplayButtonLinkAdaptor(session,
										nextDisplayButtonLink.rtvBareId());
						// process only when visible
						if (nextDisplayButtonLinkAdaptor.isVisible(nextDisplayButtonLink))
						{
							HTMLScriptElementImpl sriptElement = getDisplayButtonLinkHTMLElement2(nextDisplayButtonLinkAdaptor,
											htmlDocument, displayAttributeFieldSet, nextDisplayButtonLink);
							labelTableCell.appendChild(sriptElement);
						}
					}
					else
					{
						break;
					}
				}
				else
				{
					break;
				}
			}
		}
		return fieldTableRow;
	}

	/**
	 * Return the script element for the button or link
	 * 
	 * This generates the script that will invoke the javascript that will create the actual button or link
	 * 
	 * @param displayButtonLinkAdaptor
	 *            - the DisplayButtonLinkAdaptor
	 * @param htmlDocument
	 *            - HTML document
	 * @param displayAttributeFieldSet
	 *            - the parent <code>DisplayAttributesSet</code> (for logging purposes)
	 * @param displayButtonLink
	 *            - the button link to convert to an script html element to display the button
	 * @return
	 */
	private HTMLScriptElementImpl getDisplayButtonLinkHTMLElement2(DisplayButtonLinkAdaptor displayButtonLinkAdaptor,
					HTMLDocumentImpl htmlDocument, DisplayAttributesSet displayAttributeFieldSet,
					DisplayButtonLink displayButtonLink)
	{
		StringBuilder builder = new StringBuilder("showButtonLinkButton('");

		// Append Label
		builder.append(Toolbox.rplSingleQuote(FunctionRuntimeToolbox.getLabel(eqUser, displayButtonLink, functionData)));
		builder.append("', '");

		// Append Description
		String description = displayButtonLink.rtvDescription(eqUser);
		if (Element.DEFAULT_TEXT_VALUE.equals(description.trim()))
		{
			builder.append("");
		}
		else
		{
			builder.append(Toolbox.rplSingleQuote(description));
		}
		builder.append("', ");

		// Append Label Position
		builder.append(displayButtonLink.getLabelPosition());
		builder.append(", ");

		// Append Display as Button or Hyperlink or Image
		builder.append(displayButtonLink.getDisplayAs());
		builder.append(", ");

		// Append URL or Command flag
		builder.append(displayButtonLink.getAction());
		builder.append(", '");

		// Command and Parameters
		builder.append(Toolbox.rplSingleQuote(displayButtonLinkAdaptor.getCommandParameter(displayButtonLink)));
		builder.append("', ");

		// Protected
		builder.append(displayButtonLinkAdaptor.isProtected(displayButtonLink));
		builder.append(", '");

		// Label Style
		String style = ELRuntime.runStringScript(displayButtonLink.getDisplayStyleLabel(), functionData, displayButtonLink.getId(),
						LanguageResources.getString("HTMLToolbox.displayStyle"), "", ELRuntime.DB_VALUE).trim();
		builder.append(Toolbox.rplSingleQuote(style));
		builder.append("')");

		HTMLScriptElementImpl scriptElement = createScriptElement(htmlDocument, "");
		scriptElement.setText(builder.toString());

		// return the script element
		return scriptElement;
	}

	/**
	 * Create a new default input element
	 * 
	 * @param htmlDocument
	 *            - HTML document
	 * @param elementId
	 *            - the id attribute in the HTML element
	 * @param fElementName
	 *            - whether to set (true) the name attribute in the HTML element or not
	 * @param elementType
	 *            - input element type
	 * 
	 * @return the HTML input element
	 */
	public HTMLInputElementImpl createInputElement(HTMLDocumentImpl htmlDocument, String elementId, boolean fElementName,
					String elementType)
	{
		HTMLInputElementImpl inputElement = new HTMLInputElementImpl(htmlDocument, "input");
		inputElement.setId(elementId);
		inputElement.setAttribute("type", elementType);
		inputElement.setClassName("wf_LTOR");

		if (fElementName)
		{
			inputElement.setName(elementId);
		}

		return inputElement;
	}

	/**
	 * Create a new default input element
	 * 
	 * @param htmlDocument
	 *            - HTML document
	 * @param elementId
	 *            - the id attribute in the HTML element
	 * @param fElementName
	 *            - whether to set (true) the name attribute in the HTML element or not
	 * 
	 * @return the HTML input element
	 */
	public HTMLInputElementImpl createHiddenInputElement(HTMLDocumentImpl htmlDocument, String elementId, boolean fElementName)
	{
		HTMLInputElementImpl inputElement = new HTMLInputElementImpl(htmlDocument, "input");
		inputElement.setId(elementId);
		inputElement.setAttribute("type", "hidden");

		if (fElementName)
		{
			inputElement.setName(elementId);
		}

		return inputElement;
	}

	/**
	 * Create a new default span element
	 * 
	 * @param htmlDocument
	 *            - HTML document
	 * @param elementId
	 *            - input element ID
	 * @param value
	 *            - text
	 * 
	 * @return the HTML span element
	 */
	public HTMLElement createDefaultSpanElement(HTMLDocumentImpl htmlDocument, String elementId, String value)
	{
		return createSpanElement(htmlDocument, elementId, "wf_default wf_field", value);
	}

	/**
	 * Create a new span element
	 * 
	 * @param htmlDocument
	 *            - HTML document
	 * @param elementId
	 *            - input element ID
	 * @param className
	 *            - the class name
	 * @param value
	 *            - text
	 * 
	 * @return the HTML span element
	 */
	public HTMLElement createSpanElement(HTMLDocumentImpl htmlDocument, String elementId, String className, String value)
	{
		HTMLElement outputElement = (HTMLElement) htmlDocument.createElement("span");
		outputElement.setAttribute("dir", "ltr");

		if (elementId.length() > 0)
		{
			outputElement.setId(elementId);
		}

		if (className.length() > 0)
		{
			outputElement.setAttribute("class", className);
		}

		if (value.length() > 0)
		{
			outputElement.appendChild(createTextElement(htmlDocument, value));
		}

		return outputElement;
	}

	/**
	 * Create a new Row element
	 * 
	 * @param htmlDocument
	 *            - HTML document
	 * @param elementId
	 *            - input element ID
	 * 
	 * @return the HTML row element
	 */
	public HTMLTableRowElementImpl createRowElement(HTMLDocumentImpl htmlDocument, String elementId)
	{
		HTMLTableRowElementImpl row = new HTMLTableRowElementImpl(htmlDocument, "tr");
		if (elementId.length() > 0)
		{
			row.setId(elementId);
		}
		return row;
	}

	/**
	 * Create a new default cell element
	 * 
	 * @param htmlDocument
	 *            - HTML document
	 * @param rowElement
	 *            - the HTML row element
	 * @param elementId
	 *            - input element ID
	 * @param position
	 *            - nth cell within the row
	 * @param colSpan
	 *            - the column span
	 * 
	 * @return the HTML col element
	 */
	public HTMLTableCellElementImpl createCellElement(HTMLDocumentImpl htmlDocument, HTMLTableRowElementImpl rowElement,
					String elementId, int position, int colSpan, String width)
	{
		HTMLTableCellElementImpl cellElement = (HTMLTableCellElementImpl) rowElement.insertCell(position);
		cellElement.setId(elementId);
		cellElement.setColSpan(colSpan);
		cellElement.setNoWrap(true);

		// set width
		if (!width.equals(""))
		{
			cellElement.setWidth(width);
		}

		return cellElement;
	}

	/**
	 * Create a new default cell element
	 * 
	 * @param htmlDocument
	 *            - HTML document
	 * @param elementId
	 *            - input element ID
	 * @param width
	 *            - String containing the width of the field
	 * 
	 * @return the HTML col element
	 */
	public HTMLTableCellElementImpl createCellElement(HTMLDocumentImpl htmlDocument, String elementId, String width)
	{
		HTMLTableCellElementImpl cellElement = new HTMLTableCellElementImpl(htmlDocument, "td");

		if (elementId.length() > 0)
		{
			cellElement.setId(elementId);
		}

		cellElement.setNoWrap(true);
		cellElement.setColSpan(1);

		// set width
		if (!width.equals(""))
		{
			cellElement.setWidth(width);
		}

		return cellElement;
	}

	/**
	 * Create a new default body element
	 * 
	 * @param htmlDocument
	 *            - HTML document
	 * @param elementId
	 *            - input element ID
	 * 
	 * @return the HTML body element
	 */
	public HTMLBodyElement createBodyElement(HTMLDocumentImpl htmlDocument, String elementId)
	{
		HTMLBodyElement body = new HTMLBodyElementImpl(htmlDocument, "body");

		if (elementId.length() > 0)
		{
			body.setId(elementId);
		}

		return body;
	}

	/**
	 * Create a new default table element
	 * 
	 * @param htmlDocument
	 *            - HTML document
	 * @param elementId
	 *            - input element ID
	 * 
	 * @return the HTML table element
	 */
	public HTMLTableElementImpl createTableElement(HTMLDocumentImpl htmlDocument, String elementId, String width)
	{
		HTMLTableElementImpl table = new HTMLTableElementImpl(htmlDocument, "table");

		if (elementId.length() > 0)
		{
			table.setId(elementId);
		}

		table.setCellPadding("0");
		table.setCellSpacing("0");
		table.setBorder("0");

		if (!width.equals(""))
		{
			table.setWidth(width);
		}

		return table;
	}

	/**
	 * Create a new default div element
	 * 
	 * @param htmlDocument
	 *            - HTML document
	 * @param elementId
	 *            - input element ID
	 * 
	 * @return the HTML div element
	 */
	public HTMLDivElementImpl createDivElement(HTMLDocumentImpl htmlDocument, String elementId)
	{
		HTMLDivElementImpl div = new HTMLDivElementImpl(htmlDocument, "div");

		if (elementId.length() > 0)
		{
			div.setId(elementId);
		}

		return div;
	}

	/**
	 * Create a text node
	 * 
	 * @param htmlDocument
	 *            - HTML document
	 * @param value
	 *            - the text
	 * 
	 * @return the text element
	 */
	public Text createTextElement(HTMLDocumentImpl htmlDocument, String value)
	{
		return htmlDocument.createTextNode(value);
	}

	/**
	 * Create a new default script element
	 * 
	 * @param htmlDocument
	 *            - HTML document
	 * @param elementId
	 *            - script element ID
	 * 
	 * @return the HTML input element
	 */
	public HTMLScriptElementImpl createScriptElement(HTMLDocumentImpl htmlDocument, String elementId)
	{
		HTMLScriptElementImpl scriptElement = new HTMLScriptElementImpl(htmlDocument, "script");

		if (elementId.length() > 0)
		{
			scriptElement.setId(elementId);
		}
		scriptElement.setAttribute("type", "text/javascript");

		return scriptElement;
	}

	/**
	 * Determine the maximum character of the input HTML field for the given field type
	 * 
	 * @param fieldType
	 *            field type
	 * @param fieldLength
	 *            field length
	 * @param fieldDec
	 *            number of decimal places
	 * 
	 * @return an int containing the determined length
	 */
	private int getFieldHTMLSize(String fieldType, String fieldLength, String fieldDec, int extendedAttribute)
	{
		int length = Toolbox.parseInt(fieldLength, 1);
		int decimal = Toolbox.parseInt(fieldDec, 0);
		int wholenum = length - decimal;

		if (fieldType.equals(EqDataType.TYPE_ZONED) || fieldType.equals(EqDataType.TYPE_PACKED))
		{
			length = length + (wholenum / 3) + 1;
		}
		else if (fieldType.equals(EqDataType.TYPE_DATE))
		{
			if (extendedAttribute == InputField.EXT_ATTRIB_DATE_INPUT8D)
			{
				length = EqDataType.INPUT_DATE_LEN_FULLYEAR;
			}
			else
			{
				length = EqDataType.INPUT_DATE_LEN;
			}
		}

		return length;
	}

	/**
	 * If in a UXP session, updates the width of the field using CSS width. This is required to boost the size of shorter fields, as
	 * the OpenSansRegular font used in UXP does not have the same metrics as the Arial font used in the Equation Desktop.
	 * 
	 * @param input
	 *            the HTMLInputElementImpl to update
	 * @param size
	 *            The number of characters in the field
	 * @param repeating
	 *            whether this is a single input field, or in a repeating data table
	 */
	private void updateHTMLFieldWidth(HTMLInputElementImpl input, int size, boolean repeating)
	{
		if (uxp && !repeating)
		{
			int width = 12 + (size * 8);
			String value = Integer.toString(width) + "px";
			updateStyleAttribute(input, "width", value);
		}
	}

	/**
	 * Updates the style attribute of an HTMLElement, taking into account any existing style attributes
	 * 
	 * @param element
	 *            an HTMLElement to update
	 * @param name
	 *            The individual style attribute name
	 * @param value
	 */
	private void updateStyleAttribute(HTMLElement element, String name, String value)
	{
		String prev = element.getAttribute("style");
		String[] items = prev.split(";");

		StringBuilder newStyle = new StringBuilder();
		for (String item : items)
		{
			String itemName = item.split(":")[0].trim();
			if (itemName.length() > 0 && !name.equals(itemName))
			{
				newStyle.append(item);
				newStyle.append(";");
			}
		}
		// Add the new attribute at the end:
		newStyle.append(name);
		newStyle.append(":");
		newStyle.append(value);
		newStyle.append(";");

		element.setAttribute("style", newStyle.toString());
	}

	/**
	 * Serialise the HTML elements
	 * 
	 * @param htmlElement
	 *            - the HTML element
	 * 
	 * @return the serialised version of the HTML element
	 * 
	 * @throws EQException
	 */
	public String serialiseHTML(HTMLElement htmlElement) throws EQException
	{
		String htmlString = null;

		try
		{
			OutputFormat htmlOutputFieldSet = new OutputFormat();
			htmlOutputFieldSet.setIndenting(true);
			htmlOutputFieldSet.setOmitDocumentType(true);
			htmlOutputFieldSet.setOmitXMLDeclaration(true);
			StringWriter htmlStringWriter = new StringWriter();
			HTMLSerializer htmlSerializer = new HTMLSerializer(htmlStringWriter, htmlOutputFieldSet);
			htmlSerializer.serialize(htmlElement);
			htmlString = htmlStringWriter.toString();
		}
		catch (IOException ioe)
		{
			throw new EQException("HTMLToolbox: SerialiseHTML - Failed:" + Toolbox.getExceptionMessage(ioe), ioe);
		}
		catch (OutOfMemoryError e)
		{
			throw new EQException("HTMLToolbox: SerialiseHTML - Failed:" + Toolbox.getExceptionMessage(e), e);
		}
		return htmlString;
	}

	/**
	 * Retrieve the script for the prompt widget
	 * 
	 * @param function
	 *            - A <code>Function</code> instance
	 * @param field
	 *            - field definition
	 * @param pvName
	 *            - the PV name
	 * @param decode
	 *            - the decode
	 * @param security
	 *            - the security mode (G, H or T)
	 * 
	 * @return the script for PMT widget
	 * 
	 * @throws EQException
	 */
	public String getScriptPMT(Function function, InputField field, String pvName, String decode, String security)
					throws EQException
	{
		// get the p/v meta data
		EquationPVMetaData pvMetaData = session.getUnit().getPVMetaData(Toolbox.getFirstWord(pvName));

		// no field names, PV does not exist
		if (pvMetaData.rtvNumberOfFields() == 0)
		{
			throw new EQException(LanguageResources.getFormattedString("HTMLToolbox.PVNotExist", new String[] { pvMetaData.getId(),
							field.getId() }));
		}

		// retrieve the decode to determine the start index and length of the default input field
		EquationPVDecodeMetaData decodeMetaData = pvMetaData.getDecodeMetaData(decode);
		int defaultInputStart = decodeMetaData.getIndex();
		int defaultInputLen = decodeMetaData.getLength();

		// initialise the data fields details. The format is:
		// field name, start index, length, etc
		// when a PV has been selected, it will automatically populate the fields based on the
		// start and length of the data returned by the pv
		String dataFields = setupDataField(pvMetaData, function, field, decode);

		// construct the arraylist
		EquationPVMetaDataCache pvMetaDataCache = session.getUnit().getPvMetaDataCacheHandler().getPvMetaDataCache(
						pvMetaData.getId());
		pvMetaDataCache.setupDisplay();

		// number of rows per screen
		int maxResult = pvResult / pvMetaDataCache.getNumberOfLinesHeader();
		if (maxResult <= 0)
		{
			maxResult = 3;
		}

		// setup the script for the PMT button
		// 1. Prompt title
		// 2. HTML input field
		// 3. Prompt id
		// 4. Decode character
		// 5. Prompt file - obsolete
		// 6. Prompt key - obsolete
		// 7. Column header id - separated by comma
		// 8. Column header label - separated by comma
		// 9. Column header position - separated by comma
		// 10. Column header length - separated by comma
		// 11. Default key start position
		// 12. Default key length position
		// 13. Data fields - refer to setupDataFields()
		// 14. Column header equivalent database fields
		// 15. Column header label position
		// 16. Maximum result

		// is ZP parameter specified?
		String desc = "";
		if (decodeMetaData.getZpParam().trim().length() == 0)
		{
			desc = pvMetaData.getLabel();
		}
		else
		{
			desc = eqUser.getEquationUnit().getRecords().getHBRecord(eqUser.getLanguageId(), "QR",
							Toolbox.getFirstWord(decodeMetaData.getZpParam())).rtvCodeDescription(session.getCcsid());
		}

		// include the PV id in the desc
		desc = pvMetaData.getId() + " " + desc;

		String str = "setPromptButtonNoWF(" + "'"
						+ Toolbox.rplSlashWith2Slash(Toolbox.rplSingleQuoteWithSlashSingleQuote(Toolbox.rplQuote(desc))) + "', "
						+ "'" + getInputFieldId(field) + "', " + "'" + pvMetaData.getId() + "', '" + decode + "', '" + security
						+ "'," + "'" + "', " + "'" + "', " + pvMetaDataCache.getHdrNames() + ","
						+ pvMetaDataCache.rtvHdrDescs(eqUser) + "," + pvMetaDataCache.getHdrStarts() + ","
						+ pvMetaDataCache.getHdrLens() + "," + defaultInputStart + "," + defaultInputLen + ",'" + dataFields + "',"
						+ pvMetaDataCache.getHdrDbs() + "," + pvMetaDataCache.getHdrPoss() + "," + maxResult + ");";

		// return the script
		return str;
	}

	/**
	 * Retrieve the script for the prompt widget
	 * 
	 * @param input
	 *            - the html input field
	 * @param field
	 *            - the input field
	 * @param displayAttributes
	 *            - display attributes definition
	 * @param widgetName
	 *            - the widget name
	 * @param mapping
	 *            - the table of replacement strings
	 * 
	 * @return the script for PMT widget
	 * 
	 * @throws EQException
	 */
	public String getScriptWidget(HTMLInputElementImpl input, InputField field, DisplayAttributes displayAttributes,
					String widgetName, Hashtable<String, String> mapping) throws EQException
	{
		EquationWidget widget = session.getUnit().getWidget(widgetName);

		// widget not found
		if (widget == null)
		{
			throw new EQException(LanguageResources.getFormattedString("HTMLToolbox.WidgetNotFound", new String[] { widgetName,
							field.getId() }));
		}

		// include the scripts into the input HTML element
		processFieldAttribBasedOnWidget(field, widget, mapping, input);

		// return the widget's script
		return widget.getWidgetScript(mapping);
	}

	/**
	 * Retrieve the script for the prompt widget
	 * 
	 * @param id
	 *            - input id element
	 * @param validValue
	 *            - valid values
	 * 
	 * @return the script for PMT widget
	 */
	public String getScriptValidValues(String id, String validValue)
	{
		// setup the script for the PMT button
		String str = "setValidValuesButton('" + id + "','" + validValue + "')";

		// return the script
		return str;
	}

	/**
	 * Retrieve the script for the load button
	 * 
	 * @param fieldSet
	 *            - the input field set
	 * 
	 * @return the script for load button
	 * 
	 */
	public String getScriptLoad(String fieldSet, String field)
	{
		// setup the script for the load button
		String str = "showLoadButton(true,'" + fieldSet + "','" + field + "')";

		// return the script
		return str;
	}

	/**
	 * Retrieve the script for the load button
	 * 
	 * @param fieldSet
	 *            - the input field set
	 * 
	 * @return the script for unload button
	 * 
	 */
	public String getScriptUnload(String fieldSet, String field)
	{
		// setup the script for the unload button
		String str = "showLoadButton(false,'" + fieldSet + "','" + field + "')";

		// return the script
		return str;
	}

	/**
	 * Setup the data fields for the prompt module<br>
	 * 
	 * The data fields is String separated by comma that identifies the mapping to/from the DSCCN.<br>
	 * 
	 * It is an array of multiple of 5, where each group of 5 consists of the: <br>
	 * 1. field name of the PV,<br>
	 * 2. HTML input element mapped to it,<br>
	 * 3. start position, <br>
	 * 4. length<br>
	 * 5. details from OVAL/DVAL meta-data - This is set to Y if the details where derived from the OVAL / DVAL meta data <br>
	 * 6. default input mapping? - This is set to Y if the field is derived from the key meta data (basically when there is no
	 * mapping defined as input)<br>
	 * 7. field type <br>
	 * 8. locked data? - This is set to Y if the field cannot be changed <br>
	 * 
	 * The first N group will be the input/output to the DSCCN, while the rest of the group will be the output <br>
	 * from the DSCCN. The first N fields will corresponds to the promptable fields.
	 * 
	 * @param pvMetaData
	 *            - the PV meta data
	 * @param field
	 *            - the field
	 * 
	 * @return the data fields
	 */
	public String setupDataField(EquationPVMetaData pvMetaData, Function function, InputField field, String decode)
					throws EQException
	{
		StringBuffer buffer = new StringBuffer();

		// Retrieve the decode meta data
		EquationPVDecodeMetaData decodeMetaData = pvMetaData.getDecodeMetaData(decode);

		// Retrieve the default input field
		List<String> keysDefaultInputList = decodeMetaData.getPvFields();

		// Get the PV field set
		PVFieldSet pvFieldSet = field.getPvFieldSet(pvMetaData.getId());

		// Get all the mapping from the PV back to the field
		LinkedList<Mapping> outputMappings = new LinkedList<Mapping>();
		String path = MappingToolbox.getFullPVFieldSetPath(field.rtvPath(), pvMetaData.getId());
		for (Mapping mapping : function.getValidateMappings())
		{
			if (mapping.getSource().indexOf(path) >= 0)
			{
				outputMappings.add(mapping);
			}
		}

		// Determine if input mapping exists
		boolean inputMappingExists = false;
		for (EquationPVFieldMetaData fmd : pvMetaData.getFieldMetaData())
		{
			// determine if there are any field map into it
			int index = MappingToolbox.getMappedToPVFieldIndex(field.rtvPath(), pvMetaData.getId(), fmd.getId(), function
							.getValidateMappings());

			if (index >= 0)
			{
				inputMappingExists = true;
				break;
			}
		}

		// Input to DSCCN
		for (EquationPVFieldMetaData fmd : pvMetaData.getFieldMetaData())
		{
			// get the pv field data
			PVField pvField = pvFieldSet.getPVField(fmd.getId());

			// determine if there are any field map into it
			String mapFromStr = "";
			String locked = "";
			int index = MappingToolbox.getMappedToPVFieldIndex(field.rtvPath(), pvMetaData.getId(), fmd.getId(), function
							.getValidateMappings());

			// a field is mapped into the PV field
			if (index >= 0)
			{
				mapFromStr = Toolbox.getLastStringAfterDot(function.getValidateMappings().get(index).getSource());
				FieldData fieldData = functionData.rtvFieldData(mapFromStr);
				if (fieldData != null)
				{
					locked = Toolbox.cvtBooleanToYN(fieldData.isLocked());
					if (locked.equals(EqDataType.NO))
					{
						locked = "I"; // special I so that the PV knows that it is an input only, but not output
						for (int j = 0; j < outputMappings.size(); j++)
						{
							if (MappingToolbox.getField(outputMappings.get(j).getTarget()).equals(mapFromStr)
											&& (MappingToolbox.isMappedToPrimitive(outputMappings.get(j).getTarget()) || MappingToolbox
															.isMappedToWorkField(outputMappings.get(j).getTarget())))
							{
								locked = EqDataType.NO;
								break;
							}
						}
					}
					buffer.append(formatDataField(fmd, getInputFieldId(mapFromStr, fieldData), "N", "N", locked));
				}
				else
				{
					LOG.error(LanguageResources.getFormattedString("HTMLToolbox.PVMappingErrorNotExistField", mapFromStr));
				}
			}

			// any script expression?
			else if (Field.ASSIGNMENT_SCRIPT.equals(pvField.getValidateAssignment()))
			{
				String newValue = ELRuntime.runStringScript(pvField.getValidateScript(), functionData, pvFieldSet.getId() + " "
								+ pvField.getId(), LanguageResources.getString("Language.PVFieldAssignment"), null,
								ELRuntime.DB_VALUE);
				if (newValue != null)
				{
					buffer.append(formatDataField(fmd, "." + newValue, "N", "N", "N"));
				}
				else
				{
					buffer.append(formatDataField(fmd, ".*", "N", "N", "N"));
				}
			}
			else if (Field.ASSIGNMENT_CODE.equals(pvField.getValidateAssignment()))
			{
				ValueAdaptor valueAdaptor = functionAdaptor.getValueAdaptor(session, field.getId(), AdaptorToolbox.getPVNameField(
								pvMetaData.getId(), fmd.getId()), GAZRecordDataModel.TYP_VALUE);
				String newValue = valueAdaptor.getValue("");
				if (newValue != null)
				{
					buffer.append(formatDataField(fmd, "." + newValue, "N", "N", "N"));
				}
				else
				{
					buffer.append(formatDataField(fmd, ".*", "N", "N", "N"));
				}
			}

			// PV field is the default input field
			else
			{
				boolean defaultField = false;
				if (!inputMappingExists)
				{
					for (int j = 0; j < keysDefaultInputList.size(); j++)
					{
						if (keysDefaultInputList.get(j).equals(fmd.getId()))
						{
							locked = Toolbox.cvtBooleanToYN(functionData.rtvFieldData(field.getId()).isLocked());
							FieldData fieldData = functionData.rtvFieldData(field.getId());
							buffer.append(formatDataField(fmd, getInputFieldId(field.getId(), fieldData), "N", "Y", locked));
							defaultField = true;
							break;
						}
					}
				}

				// not a default field, then always default with an * in the desktop
				if (!defaultField)
				{
					buffer.append(formatDataField(fmd, ".*", "N", "N", "N"));
				}
			}

		}

		// Output from DSCCN
		path = MappingToolbox.getFullPVFieldSetPath(field.rtvPath(), pvMetaData.getId());
		boolean mappingExists = false;

		for (Mapping mapping : function.getValidateMappings())
		{
			if (mapping.getSource().indexOf(path) >= 0)
			{
				String mapFromStr = Toolbox.getLastStringAfterDot(mapping.getSource());
				String mapToStr = MappingToolbox.getField(mapping.getTarget());
				String locked = "";

				// check that the input field exists
				FieldData fieldData = functionData.rtvFieldData(mapToStr);
				if (fieldData == null)
				{
					LOG.error(LanguageResources.getFormattedString("HTMLToolbox.MappingErrorNotExistField", mapToStr));
				}

				// check that the PV field meta data exists
				EquationPVFieldMetaData fmd = pvMetaData.rtvFieldMetaData(mapFromStr);
				if (fmd == null)
				{
					LOG.error(LanguageResources.getFormattedString("HTMLToolbox.PVMappingErrorNotExistPVField", new String[] {
									mapFromStr, path }));
				}

				// both must exist
				if (fieldData != null && fmd != null)
				{
					// mapped to output field
					if (MappingToolbox.isMappedToOutput(mapping.getTarget()))
					{
						buffer.append(formatDataField(fmd, getInputFieldId(mapToStr, fieldData) + ID_OUTPUT, "Y", "N", "N"));
						mappingExists = true;
					}

					// mapped to input field
					else if (MappingToolbox.isMappedToInput(mapping.getTarget()))
					{
						locked = Toolbox.cvtBooleanToYN(fieldData.isLocked());
						buffer.append(formatDataField(fmd, getInputFieldId(mapToStr, fieldData), "Y", "N", locked));
						mappingExists = true;
					}

					// mapped to database field
					else if (MappingToolbox.isMappedToPrimitive(mapping.getTarget()))
					{
						// if this is alphanumeric, then populate the input field, as input/db must always be the same
						if (EqDataType.isAlpha(fieldData.getFieldType()))
						{
							locked = Toolbox.cvtBooleanToYN(fieldData.isLocked());
							buffer.append(formatDataField(fmd, getInputFieldId(mapToStr, fieldData), "Y", "N", locked));
							mappingExists = true;
						}
						// other type, populate the database field
						else
						{
							locked = Toolbox.cvtBooleanToYN(fieldData.isLocked());
							buffer.append(formatDataField(fmd, getInputFieldId(mapToStr, fieldData) + ID_DB, "Y", "N", locked));
							mappingExists = true;
						}
					}
				}
			}
		}

		// if no mapping, then include the default output mappings
		if (!inputMappingExists && !mappingExists)
		{
			// retrieve the default output field
			if (pvMetaData.getPvOvals().size() > 0)
			{
				EquationPVFieldMetaData fmd = pvMetaData.rtvFieldMetaData(pvMetaData.getPvOvals().get(0));
				buffer.append(formatDataField(fmd, getInputFieldId(field.getId()) + ID_OUTPUT, "Y", "N", "N"));
			}

			// retrieve the default db field
			if (pvMetaData.getPvDvals().size() > 0)
			{
				FieldData fieldData = functionData.rtvFieldData(field.getId());
				// if this is alphanumeric, then populate the input field, as input/db must always be the same
				if (EqDataType.isAlpha(fieldData.getFieldType()))
				{
					EquationPVFieldMetaData fmd = pvMetaData.rtvFieldMetaData(pvMetaData.getPvDvals().get(0));
					String locked = Toolbox.cvtBooleanToYN(fieldData.isLocked());
					buffer.append(formatDataField(fmd, getInputFieldId(field.getId()), "Y", "N", locked));
				}
				// other type, populate the database field
				else
				{
					EquationPVFieldMetaData fmd = pvMetaData.rtvFieldMetaData(pvMetaData.getPvDvals().get(0));
					String locked = Toolbox.cvtBooleanToYN(fieldData.isLocked());
					buffer.append(formatDataField(fmd, getInputFieldId(field.getId()) + ID_DB, "Y", "N", locked));
				}
			}
			else
			{
				EquationPVFieldMetaData fmd = new EquationPVFieldMetaData();
				fmd.setId("KEYS_DS");
				fmd.setIndex(decodeMetaData.getIndex());
				fmd.setLength(decodeMetaData.getLength());
				fmd.setType("A");
				String locked = Toolbox.cvtBooleanToYN(functionData.rtvFieldData(field.getId()).isLocked());
				buffer.append(formatDataField(fmd, getInputFieldId(field.getId()), "Y", "N", locked));
			}
		}

		// return and remove the first comma
		return buffer.toString().substring(1);
	}

	/**
	 * Generate the data field information
	 * 
	 * @param fmd
	 *            - the field meta data
	 * @param htmlFieldName
	 *            - the html field name
	 * @param ovaldvalFlag
	 *            - Y if the details where derived from the OVAL / DVAL meta data
	 * @param defaultInputMapFlag
	 *            - Y if field is derived from the key meta data (basically when there is no mapping defined as input)
	 * @param locked
	 *            - field locked?
	 * 
	 * @return concatenation of details separated by comma
	 */
	private String formatDataField(EquationPVFieldMetaData fmd, String htmlFieldName, String ovaldvalFlag,
					String defaultInputMapFlag, String locked)
	{
		return "," + fmd.getId() + "," + htmlFieldName + "," + String.valueOf(fmd.getIndex()) + ","
						+ String.valueOf(fmd.getLength()) + "," + ovaldvalFlag + "," + defaultInputMapFlag + "," + fmd.getType()
						+ "," + locked;
	}

	/**
	 * Generate the mapping table for the replacement string in the widget
	 * 
	 * @param field
	 *            Field
	 * @param displayAttributes
	 *            The DisplayAttributes associated with this field
	 * 
	 * @return the Hashtable for the mapping
	 */
	public Hashtable<String, String> getMapping(InputField field, DisplayAttributes displayAttributes)
	{
		Hashtable<String, String> mapping = new Hashtable<String, String>();

		// &wid
		String widget = displayAttributes.getWidget();
		if (widget.indexOf(Toolbox.TEXT_DELIMITER) > 0)
		{
			mapping.put("&WID", widget.substring(0, widget.indexOf(Toolbox.TEXT_DELIMITER)));
		}
		else
		{
			mapping.put("&WID", widget);
		}

		// &curfld
		mapping.put("&CURFLD", getInputFieldId(field));

		// there are no replacement texts for these fields
		// &addfldi1
		// &addfld2i
		// &curfldi
		// &addfld1
		// &addfld2
		// &wf01
		// &sflctls

		return mapping;
	}

	/**
	 * Process the widget's attribute
	 * 
	 * @param field
	 *            Field
	 */
	public void processFieldAttribBasedOnWidget(InputField field, EquationWidget widget, Hashtable<String, String> mapping,
					HTMLElementImpl input)
	{
		Hashtable<String, String> attribs = widget.getWidgetAttribs();
		Enumeration<String> attrib = attribs.keys();

		while (attrib.hasMoreElements())
		{
			String key = attrib.nextElement();
			String value = Toolbox.replaceWithMapping(new StringBuffer(attribs.get(key)), mapping);
			input.setAttribute(key, value);
		}
	}

	/**
	 * Change the value of an input field (with value attribute)
	 * 
	 * @param htmlDocument
	 *            - HTML document
	 * @param elementId
	 *            - element ID
	 * @param value
	 *            - value
	 */
	public void chgInputElement(HTMLDocumentImpl htmlDocument, String elementId, String value)
	{
		HTMLInputElementImpl inputElement = (HTMLInputElementImpl) htmlDocument.getElementById(elementId);
		inputElement.setValue(value);
	}

	/**
	 * Set the cursor position of a list
	 * 
	 * @param htmlDocument
	 *            - HTML document
	 * @param items
	 *            - number of items
	 * @param idRowName
	 *            - root id name of the row
	 * @param idFieldName
	 *            - root id name of the field to position the cursor
	 */
	public void positionCursorList(HTMLDocumentImpl htmlDocument, int items, String idRowName, String idFieldName)
	{
		for (int j = 1; j <= items; j++)
		{
			String index = "_" + j;
			HTMLElementImpl element = (HTMLElementImpl) htmlDocument.getElementById(idFieldName + index);
			if (!element.getReadOnly())
			{
				// first input field
				if (firstInputField.length() == 0)
				{
					firstInputField = idFieldName + index;
				}

				// first input with error
				HTMLTableRowElementImpl row = (HTMLTableRowElementImpl) htmlDocument.getElementById(idRowName + index);
				if ((row.getClassName().indexOf("wf_WARNING") >= 0) || (row.getClassName().indexOf("wf_ERROR") >= 0)
								|| (row.getClassName().indexOf("wf_INFO") >= 0))
				{
					if (firstInputFieldInError.length() == 0)
					{
						firstInputFieldInError = idFieldName + index;
					}
					break;
				}

				// last input field
				lastInputField = idFieldName + index;
			}
		}
	}

	/**
	 * Set an input field to read only
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param id
	 *            - the input field to be set to read only
	 */
	public void setReadOnly(HTMLDocumentImpl htmlDocument, String id)
	{
		HTMLInputElementImpl input = (HTMLInputElementImpl) htmlDocument.getElementById(id);
		input.setReadOnly(true);
	}

	/**
	 * Adds a style class name to the specified HTMLElement
	 * <p>
	 * This method will only add the class if it does not already exist.
	 * 
	 * @param htmlElement
	 *            the HTMLElement to modify
	 * @param newClassName
	 *            the class name to add. Note that this should be a single class name, not a list of classes for accurate checking
	 *            of whether the classes are already defined
	 */
	private void addClass(final HTMLElement htmlElement, final String newClassName)
	{
		String existingClass = htmlElement.getClassName().trim();
		if (existingClass.indexOf(newClassName) == -1)
		{
			if (existingClass.length() > 0)
			{
				existingClass += " ";
			}
			htmlElement.setClassName(existingClass + newClassName);
		}
	}

	/**
	 * Adds a style class name to the specified HTMLElement
	 * <p>
	 * This method will only add the class if it does not already exist.
	 * 
	 * @param htmlElement
	 *            the HTMLElement to modify
	 * @param newClassName
	 *            the class name to add. Note that this should be a single class name, not a list of classes for accurate checking
	 *            of whether the classes are already defined
	 */
	private void replaceClass(HTMLElement htmlElement, String existingClassName, String newClassName)
	{
		String existingClass = htmlElement.getClassName().trim();
		htmlElement.setClassName(existingClass.replaceAll(existingClassName, newClassName));
	}

	/**
	 * Create a default non-group table with
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param tableId
	 *            - the table Id
	 * @param rowId
	 *            - the row Id
	 * @param label
	 *            - the table label
	 * @param desc
	 *            - the table description
	 * @param colNumber
	 *            - number of columns
	 * @param buttons
	 *            - allocated cell for button?
	 * 
	 * @return the table
	 */
	public HTMLTableElementImpl createDefaultTableWithRow(HTMLDocumentImpl htmlDocument, String tableId, String rowId,
					String label, String desc, int colNumber, boolean buttons)
	{
		HTMLTableElementImpl table = createTableElement(htmlDocument, HTMLToolbox.TABLE_DETAIL + tableId, "");
		table.setClassName(table.getClassName() + " " + "NonGroupTableClass");
		table.appendChild(getFirstRow(htmlDocument, HTMLToolbox.TABLE_DETAIL + tableId, rowId, label, desc, colNumber, buttons));
		return table;
	}

	/**
	 * Create a row with cell and an element
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param rowId
	 *            - the row Id
	 * @param cellId
	 *            - the cell Id
	 * @param colSpan
	 *            - the column span
	 * @param element
	 *            - the element to be added to the cell
	 * 
	 * @return the row element
	 */
	public HTMLTableRowElementImpl createDefaultRow(HTMLDocumentImpl htmlDocument, String rowId, String cellId, int colSpan,
					String rowClassName, HTMLElement element)
	{
		// create the cell
		HTMLTableCellElementImpl cell = createCellElement(htmlDocument, cellId, "");
		cell.setColSpan(colSpan);

		if (element != null)
		{
			cell.appendChild(element);
		}

		// create the row
		HTMLTableRowElementImpl row = createRowElement(htmlDocument, rowId);
		row.setClassName(rowClassName);
		row.appendChild(cell);

		// return the row
		return row;
	}

	/**
	 * Create a row with cell and an element
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param rowId
	 *            - the row Id
	 * @param cellId
	 *            - the cell Id
	 * @param colSpan
	 *            - the column span
	 * 
	 * @return the row element
	 */
	public HTMLTableRowElementImpl createDefaultRowSpacer(HTMLDocumentImpl htmlDocument, String rowId, String cellId, int colSpan)
	{
		return createDefaultRow(htmlDocument, rowId, cellId, colSpan, "rowSpacer", (HTMLElement) htmlDocument.createElement("br"));
	}

	/**
	 * Create default div with element
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param id
	 *            - the div id
	 * @param htmlElement
	 *            - the html element to be added inside the div
	 * @param noresize
	 *            - no resizing
	 * @param className
	 *            - the div class name
	 * 
	 * @return the div element
	 */
	public HTMLElement createDefaultGroupDiv(HTMLDocumentImpl htmlDocument, String id, HTMLElement htmlElement, boolean noresize,
					String className)
	{
		HTMLDivElementImpl div = createDivElement(htmlDocument, id);
		div.setClassName("GroupClass");

		if (noresize)
		{
			div.setClassName(div.getClassName() + " " + NON_RESIZE);
		}

		if (!className.equals(""))
		{
			div.setClassName(div.getClassName() + " " + className);
		}

		div.appendChild(htmlElement);
		return div;
	}

	/**
	 * Processing to hide the shutter
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param tableId
	 *            - the table Id
	 * @param rowId
	 *            - the row Id
	 * @param defaultView
	 *            - if this is a new shutter group, then is it open (true) or close (false)
	 */
	public void processShutter(HTMLDocumentImpl htmlDocument, String tableId, String rowId, boolean defaultView)
	{
		ShutterGroup shutterGroup = shutterHandler.getShutterGroup(tableId);

		// first time to process this shutter?
		boolean open;
		if (shutterGroup == null)
		{
			shutterHandler.chgShutterGroup(tableId, defaultView);
			open = defaultView;
			shutterGroup = shutterHandler.getShutterGroup(tableId);
		}

		// already held
		else
		{
			open = shutterGroup.isOpen();
		}

		// close, then add to the list
		if (!open)
		{
			closeShutterIds.add(tableId);
			closeShutterIds.add(rowId);
		}
	}

	/**
	 * Return the list of shutter ids
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param shutterLists
	 *            - list of shutter ids
	 * 
	 * @return the list of shutter ids in script format
	 */
	public HTMLScriptElementImpl getProcessedShutter(HTMLDocumentImpl htmlDocument, List<?> shutterLists)
	{
		HTMLScriptElementImpl script = createScriptElement(htmlDocument, "");
		script.setText("var closedShutterIds =" + Toolbox.arrayToStringRplForJavaScript(shutterLists, "'"));
		return script;
	}

	/**
	 * Return the list of fields with onblur event
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param onBlurFields
	 *            - list of fields with onblur script
	 * 
	 * @return the list of fields with onblur event
	 */
	public HTMLScriptElementImpl getOnBlurField(HTMLDocumentImpl htmlDocument, List<?> onBlurFields)
	{
		HTMLScriptElementImpl script = createScriptElement(htmlDocument, "");
		script.setText("var onBlurScriptIds =" + Toolbox.arrayToStringRplForJavaScript(onBlurFields, "'"));
		return script;
	}

	/**
	 * Return the list of repeating groups
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param repeatingGroups
	 *            - list of repeating groups
	 * 
	 * @return the list of repeating groups
	 */
	public HTMLScriptElementImpl getListRepeatinGroup(HTMLDocumentImpl htmlDocument, List<?> repeatingGroups)
	{
		HTMLScriptElementImpl script = createScriptElement(htmlDocument, "");
		script.setText("var listRepeatingGroups =" + Toolbox.arrayToStringRplForJavaScript(repeatingGroups, "'"));
		return script;
	}

	/**
	 * Return the list of shutter ids
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param repeatingGroupSortByField
	 *            - list of repeating groups to be sorted
	 * 
	 * @return the list of shutter ids in script format
	 */
	public HTMLScriptElementImpl getSortRepeatingGroup(HTMLDocumentImpl htmlDocument, List<?> repeatingGroupSortByField)
	{
		HTMLScriptElementImpl script = createScriptElement(htmlDocument, "");
		script.setText("var repeatingGroupSortByField =" + Toolbox.arrayToStringRplForJavaScript(repeatingGroupSortByField, "'"));
		return script;
	}

	/**
	 * Return the list of repeating groups cells
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param repeatingGroupsCells
	 *            - list of repeating groups cells
	 * 
	 * @return the list of repeating groups
	 */
	public HTMLScriptElementImpl getListRepeatinGroupsCells(HTMLDocumentImpl htmlDocument, List<?> repeatingGroupsCells)
	{
		HTMLScriptElementImpl script = createScriptElement(htmlDocument, "");
		script.setText("var listRepeatingGroupsCells =" + Toolbox.arrayToStringRplForJavaScript(repeatingGroupsCells, "'"));
		return script;
	}

	/**
	 * Final processing
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param element
	 *            - the element
	 */
	public void finalProcessing(HTMLDocumentImpl htmlDocument, HTMLElement element)
	{
		element.appendChild(getProcessedShutter(htmlDocument, closeShutterIds));
		element.appendChild(getOnBlurField(htmlDocument, onBlurFieldIds));
		element.appendChild(getListRepeatinGroup(htmlDocument, listRepeatingGroups));
		element.appendChild(getSortRepeatingGroup(htmlDocument, repeatingGroupSortByField));
		element.appendChild(getListRepeatinGroupsCells(htmlDocument, listRepeatingGroupsCells));
	}

	/**
	 * Set the field with error in order to position the cursor to this field
	 * 
	 * @param firstInputFieldInError
	 *            - the field with error in order to position the cursor to this field
	 */
	public void setFirstInputFieldInError(String firstInputFieldInError)
	{
		this.firstInputFieldInError = firstInputFieldInError;
	}

	/**
	 * Retrieve the inner table of the first row
	 * 
	 * @param table
	 *            - the table
	 * 
	 * @return the inner table of the first row
	 */
	public HTMLElement getInnerTableOfFirstRow(HTMLElement table)
	{
		return (HTMLTableElementImpl) table.getFirstChild().getFirstChild().getFirstChild();
	}

	/**
	 * Retrieve the row containing the shutter of the first row of the table
	 * 
	 * @param table
	 *            - the table
	 * 
	 * @return the row containing the shutter of the first row of the table
	 */
	public HTMLElement getShutterRowLocationOfInnerTableOfFirstRow(HTMLElement table)
	{
		// get the first cell of the inner table
		HTMLElement innerCell = (HTMLTableCellElementImpl) getInnerTableOfFirstRow(table).getFirstChild().getFirstChild();

		// get the divvy containing the shutter table
		HTMLElement tabDiv = (HTMLElement) innerCell.getNextSibling().getFirstChild().getNextSibling();

		// get the row
		HTMLElement tabRow = (HTMLElement) tabDiv.getFirstChild().getFirstChild();

		return tabRow;
	}

	/**
	 * Retrieve the row containing the buttons of the first row of the table
	 * 
	 * @param table
	 *            - the table
	 * 
	 * @return the row containing the shutter of the first row of the table
	 */
	public HTMLElement getButtonRowLocationOfInnerTableOfFirstRow(HTMLElement table)
	{
		// get the first cell of the inner table
		HTMLElement innerCell = (HTMLTableCellElementImpl) getInnerTableOfFirstRow(table).getFirstChild().getFirstChild();

		// get the divvy containing the button table
		HTMLElement tabDiv = (HTMLElement) innerCell.getNextSibling().getFirstChild();

		// get the row
		HTMLElement tabRow = (HTMLElement) tabDiv.getFirstChild().getFirstChild();

		return tabRow;
	}
	/**
	 * Generate the HTML representation of the list of work fields
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param workFields
	 *            - the list of work fields
	 * 
	 * @return the work field in HTML
	 */
	private HTMLElementImpl getWorkFieldsHTMLElement(HTMLDocumentImpl htmlDocument, ArrayList<WorkField> workFields)
					throws EQException
	{
		// create the div
		HTMLDivElementImpl div = createDivElement(htmlDocument, DIV_WORKFIELDS);
		div.setClassName(div.getClassName() + " " + "DetailGroupClass");

		// there are no work fields?
		if (workFields.size() == 0)
		{
			return div;
		}

		// not visible, then simply hide it. It is still passed in order to allow desktop to access the value
		if (!layout.isDisplayWorkFields())
		{
			div.setClassName(div.getClassName() + " " + NON_DISPLAY_CLASS);
		}

		// create the table
		HTMLTableElementImpl table = createTableElement(htmlDocument, TABLE_WORKFIELD, "");
		table.setClassName(table.getClassName() + " " + "NonGroupTableClass");
		table.appendChild(getFirstRow(htmlDocument, TABLE_WORKFIELD, TABLE_WORKFIELD + "row", "WorkFields", "List of work fields",
						DEFAULT_COL));
		table.appendChild(createDefaultRowSpacer(htmlDocument, "", "", DEFAULT_COL));
		div.appendChild(table);

		// loop through all the work fields
		for (int i = 0; i < workFields.size(); i++)
		{
			HTMLElementImpl element = getWorkFieldHTMLElement(htmlDocument, workFields.get(i));
			table.appendChild(element);
		}

		// default to close
		processShutter(htmlDocument, TABLE_WORKFIELD, TABLE_WORKFIELD + "row", false);

		// return the div
		return div;
	}

	/**
	 * Generate the HTML representation of a work field
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param workField
	 *            - the work field
	 * 
	 * @return the work field in HTML
	 * @throws EQException
	 */
	private HTMLElementImpl getWorkFieldHTMLElement(HTMLDocumentImpl htmlDocument, WorkField workField) throws EQException
	{
		// create the row
		HTMLTableRowElementImpl fieldTableRow = createRowElement(htmlDocument, workField.getId() + ROW_SUFFIX);

		// column 1
		HTMLTableCellElementImpl labelTableCell = createCellElement(htmlDocument, TD1_PREFIX + workField.getId(), "20px");
		labelTableCell.setNoWrap(true);
		fieldTableRow.appendChild(labelTableCell);

		// create the label text
		HTMLElement labelElement = createSpanElement(htmlDocument, "", "labelTextBold", workField.getId());
		labelTableCell.appendChild(labelElement);

		// column 2
		HTMLTableCellElementImpl inputTableCell = createCellElement(htmlDocument, TD2_PREFIX + workField.getId(), "");
		inputTableCell.setNoWrap(true);
		inputTableCell.setColSpan(DEFAULT_COL - 1);
		fieldTableRow.appendChild(inputTableCell);

		// create the work field text
		HTMLInputElementImpl input = createInputElement(htmlDocument, workField.getId(), true, "input");
		input.setClassName(input.getClassName() + " " + "wf_ul wf_hi wf_default wf_field wf_pr");
		input.setValue(functionData.rtvFieldDbValue(workField.getId()));
		int length = getFieldHTMLSize(workField.getDataType(), workField.getSize(), workField.getDecimals(), 0);

		Double size = Math.ceil(length + (length * 0.15));
		input.setSize(String.valueOf(size.intValue()));
		updateHTMLFieldWidth(input, length, false);

		input.setReadOnly(true);
		inputTableCell.appendChild(input);

		// return the row
		return fieldTableRow;
	}

	/**
	 * Determines the number of visible fields in the DisplayGroup.
	 * <p>
	 * This method is called for repeating data so that the number of columns in the repeating data table.
	 * 
	 * @param group
	 *            a DisplayGroup
	 * @return int number of visible fields
	 * @throws EQException
	 */
	private int visibleFieldCount(DisplayGroup group) throws EQException
	{
		int result = 0;
		for (IDisplayItem item : group.getDisplayItems())
		{
			if (item instanceof DisplayAttributes && isRepeatingFieldVisible((DisplayAttributes) item))
			{
				result++;
			}
		}
		// Add one for the select column if required
		if (group.getLinkedFunctions().size() > 0)
		{
			result++;
		}
		return result;
	}

	/**
	 * Checks whether a display attribute is visible or not
	 * 
	 * @param displayAttributes
	 *            - the display attributes
	 * 
	 * @return true if repeating field is visible
	 * 
	 * @throws EQException
	 */
	private boolean isRepeatingFieldVisible(DisplayAttributes displayAttributes) throws EQException
	{
		AttributesAdaptor attributesAdaptor = layoutAdaptor.getAttributesAdaptor(session, displayAttributes.getId());
		return attributesAdaptor.isInGridVisible(displayAttributes);
	}

	/**
	 * This is used to get the id of a field.
	 * <p>
	 * This handles repeating data as well as normal non-repeating data, by adding the row id (for example $001) for repeating data
	 * fields to ensure uniqueness.
	 * 
	 * @param inputField
	 *            - the input field
	 * 
	 * @return the id to use
	 */
	private String getInputFieldId(InputField inputField)
	{
		String id = inputField.getId();
		// rowId will be set for Repeating data
		if (this.rowId != null)
		{
			id = id + this.rowId;
		}
		return id;
	}

	/**
	 * This is used to get the id of a field.
	 * <p>
	 * This handles repeating data as well as normal non-repeating data, by adding the row id (for example $001) for repeating data
	 * fields to ensure uniqueness.
	 * 
	 * @param id
	 *            - the id
	 * 
	 * @return the id to use
	 */
	private String getInputFieldId(String id)
	{
		// rowId will be set for Repeating data
		if (this.rowId != null)
		{
			id = id + this.rowId;
		}
		return id;
	}

	/**
	 * This is used to get the id of a field.
	 * <p>
	 * This handles repeating data as well as normal non-repeating data, by adding the row id (for example $001) for repeating data
	 * fields to ensure uniqueness.
	 * 
	 * @param id
	 *            - the id
	 * @param fieldData
	 *            - the corresponding field data to determine whether this is part of the repeating data or not
	 * 
	 * @return the id to use
	 */
	private String getInputFieldId(String id, FieldData fieldData)
	{
		// rowId will be set for Repeating data
		if (fieldData instanceof RepeatingFieldData)
		{
			id = id + this.rowId;
		}
		return id;
	}

	/**
	 * Add the class name to visually identify the message severity
	 * 
	 * @param input
	 *            - the input element
	 * @param msgSev
	 *            - the message severity
	 */
	private void addMessageSeverityClassName(HTMLElement input, int msgSev)
	{
		if (msgSev == FunctionMessages.MSG_WARN)
		{
			input.setClassName(input.getClassName() + " wf_WARNING");
		}
		else if (msgSev == FunctionMessages.MSG_ERROR)
		{
			input.setClassName(input.getClassName() + " wf_ERROR");
		}
		else if (msgSev == FunctionMessages.MSG_INFO)
		{
			input.setClassName(input.getClassName() + " wf_INFO");
		}

	}

	/**
	 * Return the next unique id for the HTML document
	 * 
	 * @return the next unique id for the HTML document
	 */
	private String getUniqueUnnamedId()
	{
		nextId++;
		return "UNNAMED$" + String.valueOf(nextId);
	}

	/**
	 * Return a dummy attributes based on the existing display attributes
	 * 
	 * @param fieldName
	 *            - a new unique name
	 * @param displayAttributes
	 *            - the display attributes
	 * 
	 * @return a dummy attributes based on the existing display attributes
	 */
	private DisplayAttributes getDummyDisplayAttribute(String fieldName, DisplayAttributes displayAttributes)
	{
		DisplayAttributes dummyAttributes = new DisplayAttributes(displayAttributes);
		dummyAttributes.setId(fieldName);
		dummyAttributes.setLinkedFuncId("");
		dummyAttributes.setLinkedFuncContext("");
		dummyAttributes.setWidget("");
		dummyAttributes.setPrompt("");
		return dummyAttributes;
	}

	/**
	 * Return a dummy input field based on the existing input field
	 * 
	 * @param fieldName
	 *            - a new unique name
	 * @param inputField
	 *            - the input field
	 * 
	 * @return a dummy input field based on the existing input field
	 */
	private InputField getDummyInputField(String fieldName, InputField inputField)
	{
		InputField dummyInputField = new InputField(inputField);
		dummyInputField.setId(fieldName);
		dummyInputField.getPvFieldSets().clear();
		return dummyInputField;
	}

	/**
	 * Convert a label into multiple spans depending on the character return
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param parentElement
	 *            - the parent element to contains all the spans element
	 * @param labelText
	 *            - the label text
	 * @param className
	 *            - the class name style
	 */
	private void convertCharacterReturn(HTMLDocumentImpl htmlDocument, HTMLElement parentElement, String labelText, String className)
	{
		String[] labels = labelText.split(EqDataType.CHARACTER_RETURN);

		for (int index = 0; index < labels.length; index++)
		{
			HTMLElement labelElement = createSpanElement(htmlDocument, "", className, labels[index]);
			parentElement.appendChild(labelElement);

			if (index != (labels.length - 1))
			{
				HTMLElement brElement = (HTMLElement) htmlDocument.createElement("br");
				parentElement.appendChild(brElement);
			}
		}

	}

	/**
	 * Transfer children from element to another element
	 * 
	 * @param fromElement
	 *            - source element
	 * @param toElement
	 *            - target element
	 */
	private void transferChilds(Node fromElement, Node toElement)
	{
		int nChilds = fromElement.getChildNodes().getLength();
		for (int i = 0; i < nChilds; i++)
		{
			// always get the zero index as adding a child to another element
			// removes it from the original parent!
			Node node = fromElement.getChildNodes().item(0);
			toElement.appendChild(node);
		}
	}

	/**
	 * Determine whether screens are input capable or not
	 * 
	 * @return true if function is input capable
	 */
	private boolean isInputCapable()
	{
		// no validation
		if (securityLevel.chkNoValidate())
		{
			return false;
		}

		// enquire mode
		else if (securityLevel.isEnquireMode())
		{
			return false;
		}

		return true;
	}

	/**
	 * Determine whether the row will be displayed to the user or not
	 * 
	 * @param breakByValue
	 *            - the break by value of the row
	 * @param filteredBy
	 *            - the filtering
	 * 
	 * @return true if the row will be displayed to the user
	 */
	private boolean rowSelectedForDisplay(String breakByValue, String filteredBy)
	{
		// no filtering
		if (filteredBy.length() == 0)
		{
			return true;
		}

		// filtered, is row selected?
		else if ((breakByValue + EqDataType.GLOBAL_DELIMETER).indexOf(filteredBy + EqDataType.GLOBAL_DELIMETER) >= 0)
		{
			return true;
		}

		// not selected
		else
		{
			return false;
		}
	}

	/**
	 * Create the repeating group buttons
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param table
	 *            - the display group table
	 * @param repeatingGroupStatus
	 *            - the repeating group status
	 * @param displayGroup
	 *            - the display group
	 */
	private void createRepeatingGroupButtons(HTMLDocumentImpl htmlDocument, HTMLElement table,
					RepeatingGroupStatus repeatingGroupStatus, HTMLElement cell, DisplayGroup displayGroup)
	{
		// repeating group id
		String repeatingGroupId = repeatingGroupStatus.getId();

		// add the global linked functions
		StringBuffer scriptBuffer = new StringBuffer();
		for (LinkedFunction linkedFunction : linkedFunctionTool.getGlobalLinkedFunctions())
		{
			if (!linkedFunctionTool.validateLinkedFunction(functionData, linkedFunction))
			{
				scriptBuffer.append("showGlobalLinkedFunctionButton('" + repeatingGroupId + "','"
								+ linkedFunction.getSelectionOption() + "','" + linkedFunction.getOptionId() + "','"
								+ linkedFunction.getOptionDescription() + "'); ");
			}
		}
		if (scriptBuffer.length() > 0)
		{
			HTMLScriptElementImpl scriptElement = createScriptElement(htmlDocument, "");
			scriptElement.setText(scriptBuffer.toString());
			cell.appendChild(scriptElement);
		}

		// add the breakby/buttons on top of the table
		if (breakByToolbox == null && repeatingGroupStatus.getPageSize() == 0)
		{
			return;
		}

		// html elements
		String script;
		HTMLScriptElementImpl scriptVal;
		HTMLTableCellElementImpl tabCell;
		HTMLTableRowElementImpl tabRow = (HTMLTableRowElementImpl) getButtonRowLocationOfInnerTableOfFirstRow(table);

		// progress bar
		script = "showRepeatingGroupProgressButton();";
		scriptVal = createScriptElement(htmlDocument, "");
		scriptVal.setText(script);
		tabCell = createCellElement(htmlDocument, repeatingGroupId + REPGROUP_BUTPROG, "");
		tabCell.setClassName("wf_NDP repeatingGroupButtons");
		tabCell.appendChild(scriptVal);
		tabRow.appendChild(tabCell);

		// filtering
		if (breakByToolbox != null && breakByToolbox.getBreakBy().size() > 1)
		{
			HTMLElement selectElement = createRepeatingGroupBreakBy(repeatingGroupId, htmlDocument, breakByToolbox.getBreakBy()
							.keySet(), repeatingGroupStatus);
			tabCell = createCellElement(htmlDocument, "", "");
			String breakByText = ELRuntime.runStringScript(displayGroup.getBreakByText(), functionData, displayGroup.getId(),
							LanguageResources.getString("HTMLToolbox.breakByText"), "", ELRuntime.DB_VALUE);
			if (breakByText.trim().length() > 0)
			{
				tabCell.appendChild(createSpanElement(htmlDocument, "", "", breakByText));
				tabCell.appendChild(htmlDocument.createTextNode(HTML_NBSP));
			}
			tabCell.appendChild(selectElement);
			tabRow.appendChild(tabCell);
		}

		// page up and down
		if (repeatingGroupStatus.getPageSize() != 0)
		{
			// add the up button
			script = "showRepeatingGroupPageUpButton('" + repeatingGroupId + "'," + !repeatingGroupStatus.isFirstRecord() + ");";
			scriptVal = createScriptElement(htmlDocument, "");
			scriptVal.setText(script);
			tabCell = createCellElement(htmlDocument, repeatingGroupId + REPGROUP_BUTUP, "");
			tabCell.setClassName("repeatingGroupButtons");
			tabCell.appendChild(scriptVal);
			tabRow.appendChild(tabCell);

			// add the down button
			script = "showRepeatingGroupPageDownButton('" + repeatingGroupId + "'," + !repeatingGroupStatus.isLastRecord() + ");";
			scriptVal = createScriptElement(htmlDocument, "");
			scriptVal.setText(script);
			tabCell = createCellElement(htmlDocument, repeatingGroupId + REPGROUP_BUTDOWN, "");
			tabCell.setClassName("repeatingGroupButtons");
			tabCell.appendChild(scriptVal);
			tabRow.appendChild(tabCell);
		}
	}

	/**
	 * Process repeating data on page up
	 * 
	 * @param htmlDocument
	 * @param repeatingGroup
	 * @param inputFieldSet
	 * @param repeatingGroupStatus
	 * @param listOfVisiDisplayAttributesOnTable
	 * @param listOfInvisiDisplayAttributesOnTable
	 * @param displayGroupAdaptor
	 * @param linkedFunctionTool
	 * @param repeatingTable
	 * @param invisibleDiv
	 * @param defaultInputCapable
	 * @param checkExistOnly
	 *            - do checking if record will be read
	 * 
	 * @return true if at least one record has been selected
	 * 
	 * @throws EQException
	 */
	public boolean generateRepeatingGroupOnPageUp(HTMLDocumentImpl htmlDocument, DisplayGroup repeatingGroup,
					InputFieldSet inputFieldSet, RepeatingGroupStatus repeatingGroupStatus,
					ArrayList<DisplayAttributes> listOfVisiDisplayAttributesOnTable,
					ArrayList<DisplayAttributes> listOfInvisiDisplayAttributesOnTable, DisplayGroupAdaptor displayGroupAdaptor,
					LinkedFunctionsToolbox linkedFunctionTool, HTMLElement repeatingTable, HTMLElement invisibleDiv,
					boolean defaultInputCapable, boolean checkExistOnly) throws EQException
	{
		// sorted list
		SortedRepeatingData sortedRepeatingData = repeatingGroupStatus.currentSortedRepeatingData();

		// set initial record in the repeating data manager
		RepeatingDataManager repeatingDataManager = sortedRepeatingData.getRepeatingDataManager();
		int pagingSize = repeatingGroupStatus.rtvPageSize();
		int counter = 0;
		sortedRepeatingData.setRow(repeatingGroupStatus.getRrnBottom() + 1);

		// read all the records from this index
		while (sortedRepeatingData.prev())
		{
			// break by?
			String breakByValue = LinkedFunctionsToolbox.getContextValue(repeatingGroup.getBreakBy(), functionData, false,
							EqDataType.GLOBAL_DELIMETER);

			// display the record?
			boolean selected = rowSelectedForDisplay(breakByValue, repeatingGroupStatus.getBreakBy());

			// add the row only if it needs to be shown to the user
			if (selected)
			{
				// only need to check if record exists
				if (checkExistOnly)
				{
					return true;
				}

				// number of records selected so far
				counter++;

				// enough rows already?
				if (counter > pagingSize)
				{
					break;
				}

				// Generate the index to unique identify each row
				int rowIndex = repeatingDataManager.currentRowNumber() + 1;
				String rowString = Toolbox.leftZeroPad(rowIndex, RepeatingDataManager.LIST_INDEX_LEN);
				this.rowId = RepeatingDataManager.INDEX_DELIMITER + rowString;

				// index according to sort order
				int sortIndex = sortedRepeatingData.currentRowNumber();

				// set the last index
				if (counter == 1)
				{
					repeatingGroupStatus.setRrnBottom(sortIndex);
				}

				// set the first index
				repeatingGroupStatus.setRrnTop(sortIndex);

				// add the row
				addRepeatingGroupRow(htmlDocument, rowIndex, repeatingGroup, inputFieldSet, repeatingGroupStatus,
								linkedFunctionTool, listOfVisiDisplayAttributesOnTable, listOfInvisiDisplayAttributesOnTable,
								invisibleDiv, defaultInputCapable, repeatingTable, displayGroupAdaptor, breakByValue, true);
			}
		}

		// if the counter is more than the page size, then it means there are more records
		repeatingGroupStatus.setFirstRecord(counter <= pagingSize);
		repeatingGroupStatus.setLastRecord(false);

		// at least one record has been processed
		return counter > 0;
	}

	/**
	 * Process repeating data on page down
	 * 
	 * @param htmlDocument
	 * @param repeatingGroup
	 * @param inputFieldSet
	 * @param repeatingGroupStatus
	 * @param listOfVisiDisplayAttributesOnTable
	 * @param listOfInvisiDisplayAttributesOnTable
	 * @param displayGroupAdaptor
	 * @param linkedFunctionTool
	 * @param repeatingTable
	 * @param invisibleDiv
	 * @param defaultInputCapable
	 * @param breakByToolbox
	 * @param summationBy
	 * @param checkExistOnly
	 *            - do checking if record will be read
	 * @param startingIndex
	 *            - the first record to be included in the display. This is needed when performing a refresh on the entire screen,
	 *            as it needs to process all records for the totals/subtotal, but will need to display records on the curent screen
	 * 
	 * @return true if at least one record has been selected
	 * 
	 * @throws EQException
	 */
	public boolean generateRepeatingGroupOnPageDown(HTMLDocumentImpl htmlDocument, DisplayGroup repeatingGroup,
					InputFieldSet inputFieldSet, RepeatingGroupStatus repeatingGroupStatus,
					ArrayList<DisplayAttributes> listOfVisiDisplayAttributesOnTable,
					ArrayList<DisplayAttributes> listOfInvisiDisplayAttributesOnTable, DisplayGroupAdaptor displayGroupAdaptor,
					LinkedFunctionsToolbox linkedFunctionTool, HTMLElement repeatingTable, HTMLElement invisibleDiv,
					boolean defaultInputCapable, BreakByToolbox breakByToolbox, String[] summationBy, boolean checkExistOnly,
					int startingIndex) throws EQException
	{
		EqTimingTest.printStartTime("HTMLToolbox.generateRepeatingGroupOnPageDown()", repeatingGroup.getId());

		// sorted list
		SortedRepeatingData sortedRepeatingData = repeatingGroupStatus.currentSortedRepeatingData();

		// assume first record is displayed
		boolean firstRecord = true;

		// set initial record in the repeating data manager
		RepeatingDataManager repeatingDataManager = sortedRepeatingData.getRepeatingDataManager();
		int pagingSize = repeatingGroupStatus.rtvPageSize();
		int counter = 0;
		sortedRepeatingData.setRow(repeatingGroupStatus.getRrnTop() - 1);

		// read all the records from this index
		while (sortedRepeatingData.next())
		{
			// break by?
			String breakByValue = LinkedFunctionsToolbox.getContextValue(repeatingGroup.getBreakBy(), functionData, false,
							EqDataType.GLOBAL_DELIMETER);

			// display the record - if not paging then always return back to the client
			boolean selected = true;
			if (repeatingGroupStatus.getPageSize() > 0)
			{
				selected = rowSelectedForDisplay(breakByValue, repeatingGroupStatus.getBreakBy());
			}

			// index according to sort order
			int sortIndex = sortedRepeatingData.currentRowNumber();

			// add the row only if it needs to be shown to the user
			if (selected && sortIndex >= startingIndex)
			{
				// only need to check if record exists
				if (checkExistOnly)
				{
					return true;
				}

				// number of records selected so far
				counter++;

				// enough rows already and no totalling, then exit
				if (counter > pagingSize && summationBy == null)
				{
					break;
				}

				// add
				if (counter <= pagingSize)
				{
					// retrieve the index of the row (this is the index of the row in the repeating data
					int rowIndex = repeatingDataManager.currentRowNumber() + 1;
					String rowString = Toolbox.leftZeroPad(rowIndex, RepeatingDataManager.LIST_INDEX_LEN);
					this.rowId = RepeatingDataManager.INDEX_DELIMITER + rowString;

					// set the first index
					if (counter == 1)
					{
						repeatingGroupStatus.setRrnTop(sortIndex);
					}

					// set the last index
					repeatingGroupStatus.setRrnBottom(sortIndex);

					// add the row
					addRepeatingGroupRow(htmlDocument, rowIndex, repeatingGroup, inputFieldSet, repeatingGroupStatus,
									linkedFunctionTool, listOfVisiDisplayAttributesOnTable, listOfInvisiDisplayAttributesOnTable,
									invisibleDiv, defaultInputCapable, repeatingTable, displayGroupAdaptor, breakByValue, false);
				}
			}

			// selected but the displayed,
			else if (selected)
			{
				firstRecord = false;
			}

			// performing totalling
			if (summationBy != null)
			{
				for (String element : summationBy)
				{
					BigDecimal bigDecimal = Toolbox.parseBigDecimal(functionData.rtvFieldDbValue(element), new BigDecimal(0));
					breakByToolbox.add(breakByValue, element, bigDecimal);
				}
				if (breakByValue.length() > 0 && summationBy.length == 0)
				{
					breakByToolbox.add(breakByValue, "", new BigDecimal(0));
				}
			}

			// maximum number of fields?
			if (currentNumberField > maximumNumberField)
			{
				exceedNumberFieldLimit = true;
				break;
			}
		}

		// if the counter is more than the page size, then it means there are more records
		repeatingGroupStatus.setFirstRecord(firstRecord);
		repeatingGroupStatus.setLastRecord(counter <= pagingSize);

		EqTimingTest.printTime("HTMLToolbox.generateRepeatingGroupOnPageDown()", repeatingGroup.getId());

		// at least one record has been processed
		return counter > 0;
	}

	/**
	 * Generate the list of visible and invisible repeating fields
	 * 
	 * @param displayGroup
	 *            - the display group (repeating group)
	 * @param listOfVisiDisplayAttributesOnTable
	 *            - list of visible fields
	 * @param listOfInvisiDisplayAttributesOnTable
	 *            - list of invisible fields
	 * 
	 * @throws EQException
	 */
	public void addListOfVisiOrInvisiDisplayAttributesOnTable(DisplayGroup displayGroup,
					ArrayList<DisplayAttributes> listOfVisiDisplayAttributesOnTable,
					ArrayList<DisplayAttributes> listOfInvisiDisplayAttributesOnTable) throws EQException
	{
		for (int index = 0; index < displayGroup.getDisplayItems().size(); index++)
		{
			IDisplayItem displayItem = displayGroup.getDisplayItems().get(index);
			if (displayItem instanceof DisplayAttributes)
			{
				DisplayAttributes displayAttributes = (DisplayAttributes) displayItem;
				// Only include visible fields,
				if (isRepeatingFieldVisible(displayAttributes))
				{
					listOfVisiDisplayAttributesOnTable.add(displayAttributes);
				}
				else
				{
					listOfInvisiDisplayAttributesOnTable.add(displayAttributes);
				}
			}
		}
	}

	/**
	 * Processing for page up or down
	 * 
	 * @param repeatingGroup
	 * @param inputFieldSet
	 * @param repeatingGroupStatus
	 * @param pageUp
	 * 
	 * @throws EQException
	 */
	public RepeatingGroupPagingDetail generateRepeatingGroupOnPaging(DisplayGroup repeatingGroup, InputFieldSet inputFieldSet,
					RepeatingGroupStatus repeatingGroupStatus, boolean pageUp) throws EQException
	{
		// determine list of visible and invisible fields
		ArrayList<DisplayAttributes> listOfVisiDisplayAttributesOnTable = new ArrayList<DisplayAttributes>();
		ArrayList<DisplayAttributes> listOfInvisiDisplayAttributesOnTable = new ArrayList<DisplayAttributes>();
		addListOfVisiOrInvisiDisplayAttributesOnTable(repeatingGroup, listOfVisiDisplayAttributesOnTable,
						listOfInvisiDisplayAttributesOnTable);

		// generate html elements
		HTMLDocumentImpl htmlDocument = new HTMLDocumentImpl();

		// note: do not use the default method of creating div/table as the generated
		// html is being processed
		HTMLDivElementImpl invisibleDiv = new HTMLDivElementImpl(htmlDocument, "div");
		HTMLTableElementImpl repeatingTable = new HTMLTableElementImpl(htmlDocument, "table");

		// retrieve the display group adaptor
		DisplayGroupAdaptor displayGroupAdaptor = layoutAdaptor.getDisplayGroupAdaptor(session, repeatingGroup.rtvBareId(), "");

		// assume never input capable
		boolean defaultInputCapable = false; // this is the assumption at this phase

		// linked function
		LinkedFunctionsToolbox linkedFunctionTool = new LinkedFunctionsToolbox(repeatingGroup.getLinkedFunctions());

		// generate data
		if (pageUp)
		{
			generateRepeatingGroupOnPageUp(htmlDocument, repeatingGroup, inputFieldSet, repeatingGroupStatus,
							listOfVisiDisplayAttributesOnTable, listOfInvisiDisplayAttributesOnTable, displayGroupAdaptor,
							linkedFunctionTool, repeatingTable, invisibleDiv, defaultInputCapable, false);
		}
		else
		{
			generateRepeatingGroupOnPageDown(htmlDocument, repeatingGroup, inputFieldSet, repeatingGroupStatus,
							listOfVisiDisplayAttributesOnTable, listOfInvisiDisplayAttributesOnTable, displayGroupAdaptor,
							linkedFunctionTool, repeatingTable, invisibleDiv, defaultInputCapable, null, null, false,
							repeatingGroupStatus.getRrnTop());
			repeatingGroupStatus.setFirstRecord(false);
		}

		// convert to string
		String repeatingTableHTML = serialiseHTML(repeatingTable);
		String invisibleDivHTML = serialiseHTML(invisibleDiv);

		// parse the details for return
		RepeatingGroupPagingDetail repeatingGroupPagingDetail = new RepeatingGroupPagingDetail();
		repeatingGroupPagingDetail.setFirstRecord(repeatingGroupStatus.isFirstRecord());
		repeatingGroupPagingDetail.setLastRecord(repeatingGroupStatus.isLastRecord());
		repeatingGroupPagingDetail.setHtml(repeatingTableHTML);
		repeatingGroupPagingDetail.setInvisibleHTML(invisibleDivHTML);

		// reset to null
		this.rowId = null;

		return repeatingGroupPagingDetail;
	}

	/**
	 * Add the double click action for the repeating group row
	 * 
	 * @param rowElement
	 *            - the row element
	 * @param repeatingGroupStatus
	 *            - the repeating group tatus
	 * 
	 * @return true if double click action has been added
	 */
	private boolean addDefaultDoubleClickAction(HTMLElement rowElement, RepeatingGroupStatus repeatingGroupStatus,
					LinkedFunctionsToolbox linkedFunctionTool)
	{
		// linked function exists?
		if (repeatingGroupStatus.isLinkFuncExists())
		{
			// get the selection option
			String selection = linkedFunctionTool.getDefaultSelectionOption();
			if (selection.length() > 0)
			{
				rowElement.setClassName(rowElement.getClassName() + " " + "mousePointerHover");
				rowElement.setAttribute("ondblclick", "setRepeatingRowDoubleClickAction('" + repeatingGroupStatus.getId() + "','"
								+ +repeatingGroupStatus.currentSortedRepeatingData().getRepeatingDataManager().currentRowNumber()
								+ "','" + selection + "')");
				return true;
			}
		}
		return false;
	}

	/**
	 * Determine if exceeded the maximum number of fields
	 * 
	 * @return true if exceeded the maximum number of fields
	 */
	public boolean isExceedNumberFieldLimit()
	{
		return exceedNumberFieldLimit;
	}

	/**
	 * Return message severity. If the screen has not been displayed yet, then assume there is no message to be displayed (except
	 * during Enquiry)
	 * 
	 * @param fieldData
	 *            - the field data
	 * 
	 * @return the message severity
	 */
	private int getMsgSev(FieldData fieldData)
	{
		// default severity to -1
		int svrty = FunctionMessages.MSG_NONE;

		// repeating field data
		if (fieldData instanceof RepeatingFieldData)
		{
			int row = Integer.parseInt(this.rowId.substring(1, 6));
			List<FunctionMessage> fMessages = this.fhd.getFunctionMsgManager().getFunctionMessages().getMessages();
			for (FunctionMessage fMessage : fMessages)
			{
				if (row == fMessage.getSequence() && (fMessage.getFieldName() != null)
								&& (fMessage.getFieldName().indexOf(fieldData.getFieldName()) > -1))
				{
					svrty = Integer.parseInt(fMessage.getEqMessage().getSeverity());
					// check if fields are of the same list
					if (fMessage.getFieldName().indexOf(fieldData.getFieldName()) != 0)
					{
						String[] arrFieldNames = fMessage.getFieldName().split(":");
						String id1 = ((RepeatingFieldData) functionData.getFieldDatas().get(arrFieldNames[0]))
										.rtvRepeatingDataManager().getId();
						String id2 = ((RepeatingFieldData) fieldData).rtvRepeatingDataManager().getId();
						// if not the same, do not mark as error/warning/info
						if (!id1.equals(id2))
						{
							svrty = FunctionMessages.MSG_NONE;
						}
					}
				}
			}
		}

		// field data
		else
		{
			// if single field or first field in the group, return severity if not -1
			if (fieldData.getMsgSev() != FunctionMessages.MSG_NONE)
			{
				return fieldData.getMsgSev();
			}

			// else check if defined anywhere in FunctionMessages fields
			List<FunctionMessage> msgs = this.fhd.getFunctionMsgManager().getFunctionMessages().getMessages();
			for (FunctionMessage fm : msgs)
			{
				if ((fm.getFieldName() != null) && (fm.getFieldName().indexOf(fieldData.getFieldName()) > 0))
				{
					String[] arrFieldNames = fm.getFieldName().split(":");
					svrty = ((FieldData) functionData.getFieldDatas().get(arrFieldNames[0])).getMsgSev();
				}
			}
		}

		// return severity
		return svrty;
	}
}
