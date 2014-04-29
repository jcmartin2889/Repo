
// Function keys
var KEY_NONE		= '99';	// None
var KEY_ENT		= '0'; // Enter key
var KEY_HELP		= '1'; // Help
var KEY_F2		= '2'; // Linked program 1
var KEY_EXIT		= '3'; // Exit
var KEY_PRMPT		= '4'; // Prompt
var KEY_VERI		= '5'; // Refresh or verify
var KEY_ADD		= '6'; // Add (non-supervisor)
var KEY_AUTH		= '6'; // Authorise warning (supervisor)
var KEY_PGUP		= '7'; // Page up
var KEY_PGDN		= '8'; // Page down
var KEY_CHARGE		= '9'; // Charge
var KEY_DECL		= '10'; // Decline
var KEY_F11		= '11'; // Linked program 2
var KEY_REFER		= '11'; // Refer
var KEY_PREV		= '12'; // Cancel
var KEY_F14		= '14'; // Linked program 3
var KEY_DRILLUP		= '15'; // Return to (top most) parent
var KEY_PRINT		= '16'; // Print
var KEY_TOGLE		= '17'; // Toggle fields
var KEY_AUTHA		= '18'; // Authorise all warnings (supervisor)
var KEY_ACCPT		= '18'; // Accept (non-supervisor)
var KEY_DEL		= '22'; // Delete
var KEY_F23		= '23'; // Linked program 4
var KEY_OVR		= '24'; // Override warning
var KEY_SAVE		= '51'; // Save
var KEY_SVTMPL		= '53'; // Save as template
var KEY_RMVSES		= '54'; // Remove the save session
var KEY_VERIWARN	= '55'; // Similar to KEY_VERI and also allow warnings
var KEY_LOAD		= '56'; // Load
var KEY_UNLOAD		= '57'; // Unload
var KEY_EXIT2		= '58'; // Similar to KEY_EXIT but no update of session
var KEY_EXIT_OFLNE_OVR	= '59'; // Exit when request for offline override
var KEY_DRILLDOWN	= '60'; // Function key to trigger drill down processing
var KEY_DRILLDOWN2	= '61'; // Function key to trigger drill down processing on a specific row
var KEY_DRILLDOWN3	= '62'; // Function key to trigger drill down processing on a global function
var KEY_TOGGLEFUNCKEY = '63'; // Function key to toggle the function key

var CF_KEY_NONE		= 'CF99'; // None
var CF_KEY_ENT		= 'CF00'; // Enter key
var CF_KEY_HELP		= 'CF01'; // Help
var CF_KEY_F2		= 'CF02'; // Linked program 1
var CF_KEY_EXIT		= 'CF03'; // Exit
var CF_KEY_PRMPT	= 'CF04'; // Prompt
var CF_KEY_VERI		= 'CF05'; // Refresh or verify
var CF_KEY_ADD		= 'CF06'; // Add (non-supervisor)
var CF_KEY_AUTH		= 'CF06'; // Authorise warning (supervisor)
var CF_KEY_PGUP		= 'CF07'; // Page up
var CF_KEY_PGDN		= 'CF08'; // Page down
var CF_KEY_CHARGE	= 'CF09'; // Charge
var CF_KEY_DECL		= 'CF10'; // Decline
var CF_KEY_F11		= 'CF11'; // Linked program 2
var CF_KEY_REFER	= 'CF11'; // Refer
var CF_KEY_PREV		= 'CF12'; // Cancel
var CF_KEY_F14		= 'CF14'; // Linked program 3
var CF_KEY_DRILLUP	= 'CF15'; // Return to (top most) parent
var CF_KEY_PRINT	= 'CF16'; // Print
var CF_KEY_TOGLE	= 'CF17'; // Toggle fields
var CF_KEY_AUTHA	= 'CF18'; // Authorise all warnings (supervisor)
var CF_KEY_ACCPT	= 'CF18'; // Accept (non-supervisor)
var CF_KEY_DEL		= 'CF22'; // Delete
var CF_KEY_F23		= 'CF23'; // Linked program 4
var CF_KEY_OVR		= 'CF24'; // Override warning
var CF_KEY_SAVE		= 'CF51'; // Save
var CF_KEY_SVTMPL	= 'CF53'; // Save as template
var CF_KEY_RMVSES	= 'CF54'; // Remove the save session
var CF_KEY_VERIWARN	= 'CF55'; // Similar to CF_KEY_VERI and also allow warnings
var CF_KEY_LOAD		= 'CF56'; // Load
var CF_KEY_UNLOAD	= 'CF57'; // Unload
var CF_KEY_EXIT2	= 'CF58'; // Similar to CF_KEY_EXIT but no update of session
var CF_KEY_EXIT_OFLNE_OVR = 'CF59'; // Exit when request for offline override
var CF_KEY_DRILLDOWN	= 'CF60'; // Function key to trigger drill down processing
var CF_KEY_DRILLDOWN2	= 'CF61'; // Function key to trigger drill down processing on a specific row
var CF_KEY_DRILLDOWN3	= 'CF62'; // Function key to trigger drill down processing on a global function
var CF_KEY_TOGGLEFUNCKEY 	= 'CF63'; // Function key to toggle the function key

// Session type
var SESSION_FULL_DESKTOP		= 0;
var SESSION_CLASSIC_DESKTOP		= 1;
var SESSION_DIRECT_TRANS_DESKTOP	= 2;
var SESSION_API_MODE			= 3;
var SESSION_CHILD_MODE			= 4;
var SESSION_OTHER_MODE			= 5;


// HTMLToolbox constant
var OBJ_NAME			= "___nameElement";
var OBJ_FIELDSET		= "___fieldSetElement";
var OBJ_CURSOR			= "___cursorElement";
var OBJ_FIRSTFIELD		= "___firstFieldElement";
var OBJ_LASTFIELD		= "___lastFieldElement";
var OBJ_SCNO			= "___scnoElement";
var OBJ_CUR_SCRN		= "___curScrnElement";
var OBJ_FCT			= "___fctElement";
var OBJ_FLDVAL			= "___fldValElement";
var OBJ_MSGSEV			= "___msgSevElement";
var OBJ_FKEY			= "___fkeyElement";
var OBJ_CHCKR			= "___checkerElement";
var OBJ_NMSGS			= "___nMsgsElement";
var OBJ_RCHKR			= "___reqCheckerElement";
var OBJ_SUPMSG			= "___supervisorMsg";
var OBJ_REFERTOUSERID		= "___referToUserId";
var OBJ_VFKEYS			= "___validFkeysElement";
var OBJ_FKYTXT			= "___funcKeyTextElement";
var OBJ_FFKYTXT			= "___fullFuncKeyTextElement";
var OBJ_UPDERROR		= "___updateErrorElement";
var OBJ_TRANID			= "___tranIdElement";
var OBJ_SYSTEMID		="___systemIdElement";
var OBJ_UNITID			="___unitIdElement";
var OBJ_LOADFIELDSET		= "___loadFieldSetElement";
var OBJ_LOADFIELD		= "___loadFieldElement";
var OBJ_MSGIDS			= "___msgIdsElement";
var OBJ_MSGAMT			= "___msgAmtElement";
var OBJ_MSGBRNM			= "___msgBrnmElement";
var OBJ_MSGDRCR			= "___msgDrCrElement";
var OBJ_DEFSUPER		= "___defaultSuper_";
var OBJ_DSPGRPID		= "___dspGrpId_";
var OBJ_KEYEXSTSET		= "___keyExistElement";
var OBJ_KEYDSPSET		= "___keyDisplayElement";
var OBJ_JOBID			= "___jobIdElement";
var OBJ_OPTIONID		= "___optionIdElement";
var OBJ_CRM_S			= "___crmSAuthElement";
var OBJ_TASKID			= "___taskID";
var OBJ_TASKPROCID		= "___taskProcessID";
var OBJ_TASKTYPE		= "___taskType";
var OBJ_COMPLETETASK		= "___completedTask";


var ID_LABEL			= "___Label";
var ID_OUTPUT			= "$$$Output";
var ID_DB			= "$$$DB";

var DIV_MAIN			= "mainTD";
var DIV_KEY			= "___mainDivKey";
var DIV_DETAIL			= "___mainDivDetail";
var TABLE_PREFIX		= "___TABLE_";
var TABLE_KEY			= "___TABLE_KEY_";
var TABLE_DETAIL		= "___TABLE_DETAIL_";
var TD_PREFIX			= "___TD_";
var TD1_PREFIX			= "___TD_01_";
var TD2_PREFIX			= "___TD_02_";
var TD3_PREFIX			= "___TD_03_";
var TD4_PREFIX			= "___TD_04_";
var TD5_PREFIX			= "___TD_05_";
var TD6_PREFIX			= "___TD_06_";
var TD7_PREFIX			= "___TD_07_";

var ROW_SUFFIX			= "___ROW";

var GROUPCONTROL_ROW		= "___GRP_ROW";
var GROUPBUTTONS_DIV		= "___GRP_BUTTONS_DIV";
var GROUPTABS_DIV		= "___GRP_TABS_DIV";
var GROUPBUTTONS_TABLE		= "___GRP_BUTTONS_TABLE";
var GROUPTABS_TABLE		= "___GRP_TABS_TABLE";
var GROUPTABS_CELL		= "___GRP_TABCELL_";

var LISTGROUPHDR		= "___DIVGROUP_HEADER_";
var LISTGROUPLST		= "___DIVGROUP_DATA_";
var LISTGROUPFTR		= "___DIVGROUP_FOOTER_";
var LISTTABLEHDR		= "___TABLEGROUP_HEADER_";
var LISTTABLELST		= "___TABLEGROUP_DATA_";
var LISTTABLEFTR		= "___TABLEGROUP_FOOTER_";
var REPEAT_HDR			= "___TDLIST_HEADER_";
var REPEAT_LST			= "___TDLIST_DATA_";
var REPEAT_FTR			= "___TDLIST_FOOTER_";
var GRP_FLD			= "___";


var DEFAUL_COL			= 2;

var NON_DISPLAY_CLASS1		= "wf_NDP";
var NON_DISPLAY_CLASS2		= "wf_DSPNONE";
var NON_RESIZE			= "NORESIZE";
var NON_IGNORESIZE		= "IGNORESIZE";


// Javascript
var BUT_WIDGET			= "$$Button";
var BUT_SHUTTER			= "$$ButtonShutter";
var BUT_PROMPT			= "$$ButtonPMT";
var BUT_OPTION			= "$$ButtonOPT";
var BUT_VALIDATE		= "$$ValButton";
var BUT_TOGGLE			= "$$ButtonToggle";
var BUT_HREF			= "Href";
var BUT_LINKED			= "$$LinkedButton";
var BUT_VOPTION			= "$$ButtonVOPT";
var BUT_GPAGEUP			= "$$PageUp";
var BUT_GPAGEDN			= "$$PageDown";


var ICON_SIZE			= 16;

var CONTEXT_DELIMETER   = ":";
var LINKEDOPTION_DELIMETER = "*";
var LITERAL_STR_DELIMETER = "'";
var LITERAL_ESC_DELIMETER = "''";

var LIST_INDEX_DELIMITER = "$";
var LIST_INDEX_LEN = 5;
var GLOBAL_DELIMETER = "!:!";
var GLOBAL_EQUALDELIMETER = "!=!";


var GROUP_SHTR_OPEN = "___SHTR_OPN";

var REPGROUP_FLDSORT = "___FLDSORT";
var REPGROUP_ORDERSORT = "___ORDERSORT";
var REPGROUP_NROWS = "___NROWS";
var REPGROUP_INPBREAKBY = "___INPBREAKBY";
var REPGROUP_SELBREAKBY = "___SELBREAKBY";
var REPGROUP_INVISIBLE = "___INVISIBLE";
var REPGROUP_BUTUP = "___BUTUP";
var REPGROUP_BUTDOWN = "___BUTDOWN";
var REPGROUP_BUTPROG = "___BUTPROG";
var REPGROUP_OPTION1 = "___OPT1";
var REPGROUP_OPTION2 = "___OPT2";

var ROW_INDEX_END = "999999999";

var YES = "Y";


var ONCLIENTSIDE_SEP = '£x£';

var FROMWEBFACINGTOEQSERV = 'E4';

var NEXTLINE = '\r\n';
var TAB = '\t';
var TABCLUE = '  ';
var TABCLUE_LENGTH = 35;

var TASK_TYPE_AUTH = 'AUTH';

var LOGGING_INFO = 0;
var LOGGING_WARN = 10;
var LOGGING_ERROR = 20;
