var objFirstInputField = null;
var objFirstField = null;
var objLastInputField = null;
var objCurrentInputField = null;
var objLastFocusInputField = null;
var pageLoading = true;
var objCurrentValue = '';
var predictiveListObj = null; // this contains the field whose PV is attached to

var mouseSelectionBoxStatus = null;
var mouseSelectionBoxPopup = null;
var mouseSelectionBoxDiv = null;

var mouseSelectionSpecialCode = "EquationDesktopSpecialPasteAction:::";
var mouseSelectionStatusCode = "Code:::";
var toleranceTop = 5;
var toleranceLeft = 10;

// this store the key character code pressed, before
// WebFacing code cleared this code
var keyPressed = null;


// **********************************************************************************
// function setupMainBody()
// Do any more processing after loading the main input frame
//
// Parameters:
// None
// **********************************************************************************	
function setupMainBody()
{
	// switch to RTL if needed
	if (isWebFacing())
	{
		switchRTL();
	}
	
	// make it visible
	document.getElementById('mainTD').style.visibility = "";
	
	// Frame in error and classic login
	if (sessionType==SESSION_CLASSIC_DESKTOP && getFrameWebfacing().eqDriver==null)
	{
		closeBrowser();
		return;
	}
	
	// Stop the progress bar
	endLoadingTransaction();
	
	// Determine if any frame in error?
	if (checkFrameInError(true))
	{
		return;
	}

	// Display the Webfacing frame or the Desktop frame?
	var screenFrameset = getFrameScreen().document.getElementById('screenFrameset');
	screenFrameset.style.visibility="";
	if (isWebFacing() && eqDriver == "N")
	{
		screenFrameset.setAttribute("cols","0%,100%");
	}
	else
	{
		screenFrameset.setAttribute("cols","100%,0%");
	}
	
	// When Equation desktop is called with a link to a transaction, ensure driver is not
	// displayed
	if (sessionType == SESSION_DIRECT_TRANS_DESKTOP && eqDriver == 'Y')
	{
		// webFacing and linking to another function
		if ((isWebFacing() && document.getElementById('l1_W97HMDA$ZLEMOD').value != FROMWEBFACINGTOEQSERV) &&
				getFrameDesktop().submit==null)
		{
			document.getElementById('mainTD').style.visibility = "hidden";
			setTimeout( 
				function() 
				{
					logoff_Processing();								
					window.top.location.replace("../equation/jsp/login.jsp");
					closeBrowser();
				}
				, 100);
			return;
		}
	}
	
	// Desktop frame to be loaded?
	if (sessionType!=SESSION_CLASSIC_DESKTOP && isWebFacing() && eqDriver == "Y")
	{
		var name = rtvFunctionHandlerName();
		// is this linked transaction?
		var zlemod = document.getElementById('l1_W97HMDA$ZLEMOD').value;
		if (zlemod != FROMWEBFACINGTOEQSERV)
		{
			if (getFrameDesktop().submit == null)
			{
				getFrameDesktop().location = "../equation/jsp/welcome.jsp?clearmsg=false&name=" + name;
			}
			else
			{
				getFrameDesktop().submit();
			}
		}
		else
		{
			getFrameDesktop().location = "../equation/jsp/function.jsp?name=" + name + "&mode=*D" + 
					"&unit=" + document.getElementById('l1_W97HMDA$ZLUNIT').value + 
					"&optionId=" + document.getElementById('l1_W97HMDA$ZLOPT').value + 
					"&context=" + " " +
					"&dawsid1=" + document.getElementById('l1_W97HMDA$ZLDS11').value.pad(64) + 
									document.getElementById('l1_W97HMDA$ZLDS12').value.pad(64) + 
									document.getElementById('l1_W97HMDA$ZLDS13').value.pad(64) + 
									document.getElementById('l1_W97HMDA$ZLDS14').value.pad(64) + 
									document.getElementById('l1_W97HMDA$ZLDS15').value.pad(64) + 
									document.getElementById('l1_W97HMDA$ZLDS16').value.pad(64) + 
									document.getElementById('l1_W97HMDA$ZLDS17').value.pad(64) + 
									document.getElementById('l1_W97HMDA$ZLDS18').value.pad(64) +
					"&dawsid2=" + document.getElementById('l1_W97HMDA$ZLDS21').value.pad(64) + 
									document.getElementById('l1_W97HMDA$ZLDS22').value.pad(64) + 
									document.getElementById('l1_W97HMDA$ZLDS23').value.pad(64) + 
									document.getElementById('l1_W97HMDA$ZLDS24').value.pad(64) + 
									document.getElementById('l1_W97HMDA$ZLDS25').value.pad(64) + 
									document.getElementById('l1_W97HMDA$ZLDS26').value.pad(64) + 
									document.getElementById('l1_W97HMDA$ZLDS27').value.pad(64) + 
									document.getElementById('l1_W97HMDA$ZLDS28').value.pad(64);
		}

		// Position cursor again
		setTimeout( 
				function() 
				{
					gblToolbarFrame = getFrameToolbar();
					if (gblToolbarFrame != null)
					{
						gblToolbarFrame.document.getElementById('optionInput').focus();
						gblToolbarFrame.document.getElementById('optionInput').select();
					}
				}
				, 50);
		
		return;
	}

	// WebFacing?
	if (isWebFacing()) 
	{
		// displaying classic screen? - unconverted DDS
		if (pgmIdSet == null || functionKeysList == null)
		{
			setFunctionKeyButtons('','','','');
		}

		// add default class wf_LBL to all unnamed fields
		chgUnnamed();

		// bottom bar processing
		mainBodyBottomBarProcessing();

		// field processing
		mainBodyFieldProcessing();
	}
	
	// Equation service
	else
	{
		// Equation desktop function, setup up function key buttons
		var defaultFKeys = setupFunctionKeyNoWF();
		setFunctionKeyButtons(defaultFKeys,'','functionTitle','');
		
		// bottom bar processing
		mainBodyBottomBarProcessing();

		// field processing
		mainBodyFieldProcessingNoWF();
	}
	
	// Classic signin
	if (sessionType == SESSION_CLASSIC_DESKTOP)
	{
		setTimeout( 
				function() 
				{
					if (
							isWebFacing()&&
							(
								widgetHasAnyRtl(functionKeysList.cmdKeyLnIn1ID) ||
								widgetHasAnyRtl(functionKeysList.cmdKeyLnIn2ID) ||
								widgetHasAnyRtl(functionKeysList.titleInID) ||
								widgetHasAnyRtl(functionKeysList.strSflNPgUpDnID)

							)
						)
					{
						setFunctionKeyButtons(functionKeysList.cmdKeyLnIn1ID,functionKeysList.cmdKeyLnIn2ID,functionKeysList.titleInID,functionKeysList.strSflNPgUpDnID);
						mainBodyBottomBarProcessing();
					}
				}
				, 1);
		
		getFrameScreen().document.getElementById('eqwfWrapFrameset').rows = "0,*,61";
		return;
	}
	
	// function key text processing
	setTimeout( 
			function() 
			{
				if (
						isWebFacing()&&
						(
							widgetHasAnyRtl(functionKeysList.cmdKeyLnIn1ID) ||
							widgetHasAnyRtl(functionKeysList.cmdKeyLnIn2ID) ||
							widgetHasAnyRtl(functionKeysList.titleInID) ||
							widgetHasAnyRtl(functionKeysList.strSflNPgUpDnID)

						)
					)
				{
					setFunctionKeyButtons(functionKeysList.cmdKeyLnIn1ID,functionKeysList.cmdKeyLnIn2ID,functionKeysList.titleInID,functionKeysList.strSflNPgUpDnID);
					mainBodyBottomBarProcessing();
				}
							
				populateButtonbar('EqButtonbar', functionKeysList.title,
						functionKeysList.butsCommon.toArray(), functionKeysList.butsNavi.toArray(),
						functionKeysList.butsTran.toArray(), functionKeysList.butsOther.toArray());
				mainBodyFunctionKeyProcessing();
				hideSpinnerButton();
				// printTime("F", false);
				// printIntervalTime("F", window.top.startLoadTime);
				// Ensure scrolled to the top:
				if (!RTL)
				{
					getFrameCurrent().scrollTo(0,0);
				}
			}
			, 1);
	
	// option key processing
	mainBodyOptionKeyProcessing();

	// position to Option field in the toolbar when displaying the driver screen
	if (eqDriver == 'Y' && getFrameToolbar() != null)
	{
		getFrameToolbar().document.getElementById('optionInput').select();
		getFrameToolbar().document.getElementById('optionInput').focus();
	}
	
	// for RTL, right-align all fields
	if (RTL) 
	{
		chgFieldToRTL();
	}

	// protect or unprotect the option and context field
	var gblToolbarFrame = getFrameToolbar();
	if (gblToolbarFrame != null)
	{
		if (eqDriver == 'Y')
		{
			gblToolbarFrame.document.getElementById('optionInput').readOnly=false;
			gblToolbarFrame.document.getElementById('contextInput').readOnly=false;
		}
		else
		{
			//gblToolbarFrame.document.getElementById('optionInput').readOnly=true;
			//gblToolbarFrame.document.getElementById('contextInput').readOnly=true;
		}
	}

	// clear popup - trap error in case console tab has not been loaded
	try
	{
		gblTabFrame = getFrameTab();
		gblConsoleFrame = gblTabFrame.frames['consoleFrame'];
		gblConsoleFrame.tabHidePopup();
	}
	catch(e)
	{
	}
	
	// additional Equation desktop function processing
	if (!isWebFacing())
	{
		mainProcessingNoWF();
		setTableSize(true);
	}
	
	// end of page loading
	pageLoading = false;
	// Do drill down processing if required
	if (typeof nextAction !="undefined" && nextAction !=null)
	{
		if (nextAction.actionType == 'routeToSessionRestore2')
		{
			window.top.equxp.routeToSessionRestore2(nextAction.control, nextAction.optionId, nextAction.optionDescription, nextAction.sessionId, nextAction.transactionId, nextAction.originalFullUser, nextAction.status, nextAction.authorityLevel, nextAction.screenSetId, nextAction.screenNumber);
		}
		else if(nextAction.actionType == 'routeToLRP')
		{
			window.top.equxp.routeToLRP(nextAction.optionId, nextAction.optionDescription, nextAction.sessionId, nextAction.transactionId);
		}
		else
		{
		 	window.top.equxp.routeToOption2(nextAction.optionType, nextAction.optionId, nextAction.optionDescription, nextAction.context);
		}
	}
	else
	{
		// In UXP, auto-load LRP comments
		if(isUXP() && typeof(isLrpTask) != "undefined" && isLrpTask())
		{
			setTimeout( function() 
			{
				var commentFrame = getNamedFrame(getFrameTabBar().parent, 'uxpCommentsFrame');
				commentFrame.refreshComment();
			}, 1);
		}
	}
}


//**********************************************************************************
// function mainBodyBottomBarProcessing()
// Handle bottom bar related processing after loading the web page
//
// Parameters:
// None
//**********************************************************************************
function mainBodyBottomBarProcessing()
{
	// setup bottom bar (the BottomButtons variables were set in the buttonbar processing)
	if (eqDriver == 'Y')
	{
		functionKeysList.commonBottomButtons.clear();
		functionKeysList.otherBottomButtons.clear();
	}
	
	var gblBottomBar = getFrameBottomBar();
	var docID = gblBottomBar.document;
	
	// setup div
	var obj = docID.getElementById('EqBottomBarDiv');
	obj.innerHTML = '<table id="EqBottombarTable" cellpadding="0" cellspacing="0"></table>';

	// setup table
	var obj = docID.getElementById('EqBottombarTable').insertRow(0);
	insertIntoBottomBar(obj,functionKeysList.commonBottomButtons.toArray(),false);
	insertIntoBottomBar(obj,functionKeysList.otherBottomButtons.toArray(),true);

	// bottom bar field processing
	gblBottomBar.bottomBarFieldProcessing();
}


//**********************************************************************************
// function mainBodyFieldProcessing()
// Handle field related processing after loading the web page
//
// Parameters:
// None
//**********************************************************************************
function mainBodyFieldProcessing()
{
	// retrieve the list of input fields
	var objList = document.getElementsByTagName("input");
	
	// set the onKeyDown of the first input field
	for(var i=0; i<objList.length; i++)
	{
		if(isInputField(objList[i]))
		{
			objFirstInputField = objList[i];
			addMyEventListener(objFirstInputField, 'keydown', firstInputFieldKeyDown);
			break;
		}
	}
	
	// get the first field - could be disabled, read only, etc
	for(var i=0; i<objList.length; i++)
	{
		if(objList[i].type=='text' || objList[i].type=='button' ||
				objList[i].type=='checkbox' || objList[i].type=='radio' )
		{
			objFirstField = objList[i];
			break;
		}
	}

	// set the onKeyDown of the last input field
	for(var i=objList.length-1; i>=0; i--)
	{
		if(isInputField(objList[i]))
		{
			// set onkeydown
			objLastInputField = objList[i];
			addMyEventListener(objLastInputField, 'keydown', lastInputFieldKeyDown);
			
			// if this is a valid value field, then also set the onkeydown of the select element
			if (objLastInputField.className.indexOf('wf_VALIDVALUE') >=0)
			{
				var obj = document.getElementById(objLastInputField.id + 'ValidValueSelect');
				addMyEventListener(obj, 'keydown', lastInputFieldKeyDown);
			}
			
			break;
		}
	}
	
	// set the onFocus and onBlur for all input fields (text and radio)
	for(var i=0; i<objList.length; i++)
	{
		if(objList[i].type == 'text' || objList[i].type == 'radio' || objList[i].type == 'checkbox')
		{
			// get the object
			obj = objList[i];

			// add event
			addMyEventListener(obj, 'focus', inputFieldFocus);
			addMyEventListener(obj, 'blur', inputFieldBlur);
			addMyEventListener(obj, 'keydown', inputFieldKeyDown);
		}
		
		// run the user exit routine to default initial page
		var onblurscript = getAttribute(objList[i],'onblurscript');
		if (onblurscript != null)
		{
			// need to check for non-zero, as in WebFacing sometimes it is 0!
			if (onblurscript != 0 && onblurscript.trim() != '')
			{
				onBlurUserExitRoutine(objList[i].id);
			}
		}
	}
	
	// set the focus and blur for SELECT elements
	var objList = document.getElementsByTagName("select");
	for(var i=0; i<objList.length; i++)
	{
		addEventMyListener(objList[i], 'focus', inputFieldFocus);
		addEventMyListener(objList[i], 'blur', inputFieldBlur);
	}
}

//**********************************************************************************
// function mainBodyFunctionKeyProcessing()
// Handle function key text label processing after loading the web page
//
// Parameters:
// None
//**********************************************************************************
function mainBodyFunctionKeyProcessing()
{
	// setup the function keys at the bottom
	var gblBottomBar = getFrameBottomBar();
	var docID = gblBottomBar.document;
	var divID = docID.getElementById('functionKeys');
	
	if (divID != null)
	{
		var str = '';
		for (i=0; i<functionKeysList.allFunctionKeys.size(); i++)
		{
			str += functionKeysList.allFunctionKeys.get(i) + '&nbsp;&nbsp;';
		}
		divID.innerHTML = str;
	}
	
	// display the function key at the bottom?
	var divID = docID.getElementById('footerTexts');
	str = getCookie("funckeydisp");
	if (isUXP())
	{
		divID.style.visibility = 'hidden';
		getFrameScreen().document.getElementById('eqwfWrapFrameset').rows = "0,*,35";
		getFrameBottomBar().document.body.style.height = "35px";
	}
	else if(str=='visible') 
	{
		divID.style.visiblity = 'visible';
		getFrameScreen().document.getElementById('eqwfWrapFrameset').rows = "0,*,61";
	}
	else 
	{
		divID.style.visibility = 'hidden';
		getFrameScreen().document.getElementById('eqwfWrapFrameset').rows = "0,*,24";
	}	
}

//**********************************************************************************
// function mainBodyOptionKeyProcessing()
// Handle option key text label processing after loading the web page
//
// Parameters:
// None
//**********************************************************************************
function mainBodyOptionKeyProcessing()
{
	// fully converted PV, then do not display option key (1=Select)
	if (!widHeaderFooter && isPV)
	{
		functionKeysList.allOptionKeys.clear();
	}
	
	// fully converted
	else if (!widHeaderFooter)
	{
//		// retrieve function key text 
//		optionKeyText = getFunctionKeyText(realOptionKey1, realOptionKey2);
//		
//		allOptionKeys = "";
//		optionArray = parseFunctionKey(optionKeyText)
//		for (i=0; i<optionArray.length; i++)
//		{
//			equalIndex = optionArray[i].indexOf('=');
//			if (equalIndex > 0)
//			{
//				leftSide = optionArray[i].substr(0,equalIndex);
//				rightSide = optionArray[i].substr(equalIndex+1);
//				if (leftSide == 'F' || leftSide.charAt(0) != 'F')
//				{
//					allOptionKeys += optionArray[i] + ' ';
//				}
//			}
//		}
	}
	
	// setup the function keys at the bottom
	var gblBottomBar = getFrameBottomBar();
	var docID = gblBottomBar.document;
	var divID = docID.getElementById('optionKeys');

	// put it in the div	
	if (divID != null)
	{
		var str = '';
		for (i=0; i<functionKeysList.allOptionKeys.size(); i++)
		{
			str += functionKeysList.allOptionKeys.get(i) + '&nbsp;&nbsp;';
		}
		
		// put into div
		divID.innerHTML = str;
	}
}

//**********************************************************************************
// function firstInputFieldKeyDown(e)
// Handle key down event for the first input field
//
// Parameters:
// e:         event
//**********************************************************************************
function firstInputFieldKeyDown(e)
{
	var keynum = rtvKeyboardKey(e);

	// shift tab
	if (keynum==9 && e.shiftKey)
	{
		moveToBottomLastField(objFirstInputField);
		disableKeyboardKey(e);
		return false;
	}
	
	// tab
	if (keynum==9 && e.shiftKey==false) 
	{
		// tab - one input field only
		if (objFirstInputField==objLastInputField)
		{
			moveToBottomFirstField(objFirstInputField);
			disableKeyboardKey(e);
			return false;
		}
		// tab - more than one input field
		else
		{	
			if (focusOnNextTab(true, e.srcElement))
			{
				disableKeyboardKey(e);
				return false;
			}
		}
	}

	// store key pressed
	keyPressed = keynum;
}


//**********************************************************************************
// function lastInputFieldKeyDown(e)
// Handle key down event for the last input field
//
// Parameters:
// e:         event
//**********************************************************************************
function lastInputFieldKeyDown(e)
{
	var keynum = rtvKeyboardKey(e);

	// tab
	if (keynum==9 && e.shiftKey == false)
	{
		moveToBottomFirstField(objLastInputField);
		disableKeyboardKey(e);
		return false;
	}
	
	// shift tab
	if (keynum==9 && e.shiftKey)
	{
		// shift tab - one input field only
		if (objFirstInputField==objLastInputField)
		{
			moveToBottomLastField(objFirstInputField);
			disableKeyboardKey(e);
			return false;
		}
		// shift tab - more than one input field
		else
		{
			if (focusOnNextTab(false, e.srcElement))
			{
				disableKeyboardKey(e);
				return false;
			}
		}
	}

	// store key pressed
	keyPressed = keynum;
}



// **********************************************************************************
// function moveToBottomFirstField(currentField)
// Move the focus to the bottombar's first field
//
// Parameters:
// currentField - current object with the focus
// **********************************************************************************	
function moveToBottomFirstField(currentField)
{
	var gblBottomBar = getFrameBottomBar();
	
	try
	{
		currentField.blur();
	}
	catch (e)
	{
	}
	
	if (gblBottomBar.objBottomFirstInputField != null)
	{
		gblBottomBar.objBottomFirstInputField.focus();
	}
	else
	{
		objectFocus(objFirstInputField);
	}
}


// **********************************************************************************
// function moveToBottomLastField(currentField)
// Move the focus to the bottombar's last field
//
// Parameters:
// currentField - current object with the focus
// **********************************************************************************	
function moveToBottomLastField(currentField)
{
	var gblBottomBar = getFrameBottomBar();

	try
	{	
		currentField.blur();
	}
	catch (e)
	{
	}
	
	if (gblBottomBar.objBottomLastInputField != null)
	{
		gblBottomBar.objBottomLastInputField.focus();
	}
	else
	{
		objectFocus(objLastInputField);
	}
}


// **********************************************************************************
// function bodyKeyUp
// Handle key up events in the body
//
// Parameters:
// None
// **********************************************************************************	
function bodyKeyUp(e)
{
	var keynum = rtvKeyboardKey(e);

	// F1 key - no ALT, no SHIFT
	if (keynum==112 && e.altKey == false && e.shiftKey == false)
	{
		var optionId = getOptionId();
		if (optionId!='' || eqDriver=='Y' )
		{
			dispHelp();
			disableKeyboardKey(e);
			return false;
		}
	}
}


// **********************************************************************************
// function bodyKeyDown
// Handle key down events in the body
//
// Parameters:
// None
// **********************************************************************************	
function bodyKeyDown(e)
{
	var keynum = rtvKeyboardKey(e);
	
	// trap right-to-left trigger
	if (keynum == 144)
	{
		toggleRtoL(objCurrentInputField);
	}

	// Copy - CTRL-Shift-C (desktop style copying)
	if (keynum==67 && isCtrlKey(e) && isShiftKey(e) )
	{
		return triggerCopy(true);
	}
	
	// Copy - CTRL-C
	if (keynum==67 && isCtrlKey(e))
	{
		return triggerCopy(false);
	}
	
	// Has WebFacing sent the details to server?
	if (isWebFacing() && myglobalvars!=null && myglobalvars.bufferAlreadySentToHost)
	{
		startLoadingTransaction(true);
	}
	
	// Reset
	keyPressed = null;
	
	// print the time
	// window.top.startLoadTime = printTime("Submit",true);
}


// **********************************************************************************
// function bodyKeyPress
// Handle key press events in the body
//
// Parameters:
// None
// **********************************************************************************
function bodyKeyPress(e)
{
	var keynum = rtvKeyboardKey(e);
}


// **********************************************************************************
// function dispHelp(langId,optionId)
// Display the help file reference by the parameter or by the cookie 'optionID'
//
// Parameters:
// langId   - language
// optionId - help file to display file
// **********************************************************************************	
function dispHelp(langId, optionId)
{
	// Equation services?
//	gblCurrentFrame = getFrameCurrent();
//	if (!gblCurrentFrame.isWebFacing())
//	{
//		gblCurrentFrame.triggerHelp(gblCurrentFrame.objLastFocusInputField);
//		return;
//	}

	// language id not specified, then default from cookie
	if(langId==null)
	{
		langId = getLanguageId();
	}
	
	// optionId not specified, then default from cookie
	if(optionId==null)
	{	
		optionId = getOptionId();
	}
	
	// is help existing for this option id?
	if (!isHelpExisting(optionId))
	{
		return;
	}

	// retrieve the language URL
	//var helpURL = getCookie('helpURL') + langId + '\\' + getHelpHTM(optionId);
	var helpURL =  '\\' + getWebAppName() + '\\equation\\html\\'+ langId + '\\' + getHelpHTM(optionId);

	
	// open help file in a new window
	var fails;
	try
	{
		win = window.open(helpURL);
		fails = false;
	}
	catch (e)
	{
		fails = true; 
	}

	// try open the English help
	if (fails && langId!='GB')
	{
		helpURL =  '\\' + getWebAppName() + '\\equation\\html\\'+ 'GB' + '\\' + getHelpHTM(optionId);
		
		try
		{
			win = window.open(helpURL);
		}
		catch (e)
		{
		}
	}
}



// **********************************************************************************
// function getHelpHTM(optionID)
// Retrieve the HTML file for an option
//
// Parameters:
// optionId - the option to trigger help
// **********************************************************************************	
function getHelpHTM(optionID)
{
	// not specified, then default page
	if (optionID == null) 
	{
		optionID ='toc';
	}
	else if (optionID == '') 
	{
		optionID = 'toc';
	}

	// generate the html file
	var helpHTM = optionID + '.htm';
	if (optionID != 'toc')
	{
		helpHTM = optionID.toUpperCase() + '.htm';
	}
	
	return (helpHTM);
}


// **********************************************************************************
// function isHelpExisting(optionID)
// Determine whether help file exists for the option
//
// Parameters:
// optionID: option id
// **********************************************************************************	
function isHelpExisting(optionID)
{
	// KMENUs does not have help file
	if (optionID == 'KMA' ||
		optionID == 'KMB' ||
		optionID == 'KMC' ||
		optionID == 'KMD' ||
		optionID == 'KME' ||
		optionID == 'KMF' ||
		optionID == 'KMG' ||
		optionID == 'KMH' ||
		optionID == 'KMI' ||
		optionID == 'KML' ||
		optionID == 'KMR' ||
		optionID == 'KMS' ||
		optionID == 'KMT' ||
		optionID == 'KMV' ||
		optionID == 'KMW' ||
		optionID == 'KMX' ||
		optionID == 'KMY' ||
		optionID == 'KMZ' ||
		optionID == 'KM0' ||
		optionID == 'KM1' ||
		optionID == 'KM6' ||
		optionID == 'KM8' ||
		optionID == 'CMD' ||
		optionID == 'D1'  ||
		optionID == 'D2'  ||
		optionID == 'D3'  ||
		optionID == 'D4'
		)
		return false;
	
	return (true);
}



// **********************************************************************************
// function inputFieldBlur(evt)
// Handle events when a text field loses focus
//
// Parameters:
// evt - Event
// **********************************************************************************	
function inputFieldBlur(evt)
{
	//setupRtoLonBlur(objCurrentInputField);
	
	// source field blank, then ignore
	srcTarget = getEventTarget(evt);
	if (srcTarget.id=='')
	{
		return;
	}
	
	objLastFocusInputField = srcTarget;
	objCurrentInputField = null;

	// Equation desktop, run the onblur user exit routine	
	if (!isWebFacing())
	{
		onBlurUserExitRoutine(srcTarget.id);
	}
}


// **********************************************************************************
// function inputFieldFocus()
// Handle events when a text field receives the focus
//
// Parameters:
// evt - Event
// **********************************************************************************	
function inputFieldFocus(evt)
{
	// modal window open?
	if (isAnyModalWindowOpen())
	{
		focusOnModalWindow();
	}

	try
	{
		objCurrentInputField = getEventTarget(evt);
		objCurrentValue = objCurrentInputField.value;
		tabbing = 0;
		//setupRtoLonFocus(objCurrentInputField);
	}
	catch (e)
	{
		objCurrentInputfield = null;
	}

	// hide the predictive popup?
	if (!isWebFacing() && predictiveListObj!=undefined && predictiveListObj != null)
	{
		// the current field is now in the different field vs the predictive prompt
		if (objCurrentInputField!=predictiveListObj)
		{
			// determine if this is part of keepWithPreviousField
			var predLeftmostfield = getAttribute(predictiveListObj,'leftmostfield');
			var objLeftmostfield = getAttribute(objCurrentInputField,'leftmostfield');
			if (objCurrentInputField.id == predLeftmostfield)
			{
			}
			else if (objLeftmostfield != null && 
					predLeftmostfield != null && 
					objLeftmostfield==predLeftmostfield)
			{
			}
			else
			{
				// different field, then hide it
				hidePredictivePopup(true);
			}
		}
	}
}



// **********************************************************************************
// function inputFieldKeyDown(evt)
// Handle events when a text field receives a keydown event
//
// Parameters:
// evt - Event
// **********************************************************************************	
function inputFieldKeyDown(evt)
{
	var keynum = rtvKeyboardKey(evt);
	
	// for RTL field, it should lose the focus in order to properly format the field
	// (e.g. add leading spaces).  However, pressing the keyboard will not make the
	// field "lose the focus" (will not trigger the onblur event).  Thus the system
	// need to trap any keys that will submit the page
	if (objCurrentInputField!=null)
	{
		var pageup = 1;
		var pagedown = 1;
		var globalvars = myglobalvars;
		
		// myglobalvars is also defined if this is called via the IBM WebFacing
		if (globalvars != null)
		{
			pageup = globalvars.validCmdKey.indexOf('PAGEDOWN');  // Page down valid?
			pagedown = globalvars.validCmdKey.indexOf('PAGEUP');   // Page up valid?
		}
		
		// Is the key requires submission of page to the server? 
		if (keynum==13 ||							// enter key
			(keynum>=112 && keynum<=123) ||			// F1-F12
			(keynum==33 && pageup>=0) ||			// page up
			(keynum==34	&& pagedown>=0)				// page down
		   )
		{
			setupRtoLonBlur(objCurrentInputField);

			// Equation desktop, run the onblur user exit routine	
			if (!isWebFacing())
			{
				onBlurUserExitRoutine(objCurrentInputField.id);
			}
		}
	}
	
	// record the tab key
	if (keynum==9 && objCurrentInputField != objFirstInputField && objCurrentInputField != objLastInputField)
	{
		if (evt.shiftKey)
		{
			tabbing = 1; // tabbing to previous field
			if (focusOnNextTab(false, evt.srcElement))
			{
				disableKeyboardKey(evt);
				return false;
			}
		}
		else
		{
			tabbing = 2; // tabbing to next field
			if (focusOnNextTab(true, evt.srcElement))
			{
				disableKeyboardKey(evt);
				return false;
			}
		}
	}
	else
	{
		tabbing = 0;
	}
	
	// pressing down arrow key, if the predictive popup is displayed for this field, then go to the popup
	if (keynum==40 && !isWebFacing() && predictiveListObj!=null)
	{
		var predLeftmostfield = getAttribute(predictiveListObj,'leftmostfield');
		var objLeftmostfield = getAttribute(objCurrentInputField,'leftmostfield');
		var selected = (objCurrentInputField.id == predictiveListObj.id) || 
						(objLeftmostfield == predLeftmostfield) ||
						(objCurrentInputField.id == predLeftmostfield)
						;
		
		if (selected)
		{
			focusPredictivePopup(1);
		}
		
		disableKeyboardKey(evt);
		return false;
	}
	
	// pressing up arrow key, if the predictive popoup is displayed for this field, then go to the popup
	if (keynum==38 && !isWebFacing() && predictiveListObj!=null)
	{
		var predLeftmostfield = getAttribute(predictiveListObj,'leftmostfield');
		var objLeftmostfield = getAttribute(objCurrentInputField,'leftmostfield');
		var selected = (objCurrentInputField.id == predictiveListObj.id) || 
						(objLeftmostfield == predLeftmostfield) ||
						(objCurrentInputField.id == predLeftmostfield)
						;
		
		if (selected)
		{
			focusPredictivePopup(2);
		}
		
		disableKeyboardKey(evt);
		return false;
	}
	
	// escape
	if (keynum==27 && !isWebFacing() && predictiveListObj!=null)
	{
		hidePredictivePopup(true);
		disableKeyboardKey(evt);
		return false;
	}
	
	// store key pressed
	keyPressed = keynum;
}


//**********************************************************************************
// function inputFieldPress(evt)
// Handle events when a text field receives a keypress event
//
// Parameters:
// evt - Event
//**********************************************************************************	
function inputFieldKeyUp(evt)
{
	var keynum = rtvKeyboardKey(evt);

}


// **********************************************************************************
// function toggleRtoL(inputField)
// Processing to toggle left-to-right or right-to-left orientation
//
// Parameters:
// inputField - text field object
// **********************************************************************************	
function toggleRtoL(inputField)
{
	// Removed for WDSC 7 fix.
}


// **********************************************************************************
// function setupRtoLonFocus(inputField)
// Processing for right-to-left orientation field when it receives the focus
//
// Parameters:
// inputField - text field object
// **********************************************************************************	
function setupRtoLonFocus(inputField)
{
	// Removed for WDSC 7 fix.
}


// **********************************************************************************
// function setupRtoLonBlur(inputField)
// Processing for right-to-left orientation field when it loses the focus
//
// Parameters:
// inputField - text field object
// **********************************************************************************	
function setupRtoLonBlur(inputField)
{
	// Removed for WDSC 7 fix.
}

// **********************************************************************************
// function updateTitleBar(messages)
// This updates the title bar to include the message severity
//
// Parameters:
// messages - messages
// **********************************************************************************	
function updateTitleBar(messages)
{
	// if no messages, or in UXP Client, no update required
	if (messages.length <= 0 || isUXP())
	{
		return;
	}

	// get the button bar frame
	var gblButtonbarFrame = getFrameButtonBar();

	// get the greatest valued character code of the message	
	var charCode = 0;
	for (i = 0; i < messages.length; i++)
	{
		if(messages[i].charCodeAt(0) > charCode)
		{
			charCode = messages[i].charCodeAt(0);
		}
	}	
	// invalid char code?
	if(charCode != 128 && charCode != 130 && charCode != 131)
	{
		charCode = messages[0].charCodeAt(messages[0].length-8);
	}
	
	var image = '<img class="buttonDisabled" border="0" id="buttonBarImage" src ="' + 
		  					ctrCharCodeToImage(charCode) + '">';

	var title = gblButtonbarFrame.document.getElementById('buttonBarTitle').innerHTML;
	
	if (RTL)
	{
		gblButtonbarFrame.document.getElementById('buttonBarTitle').innerHTML = title + image;
	}
	else
	{
		gblButtonbarFrame.document.getElementById('buttonBarTitle').innerHTML = image + title;
	}
}

//**********************************************************************************
// function positionFocusOnNextElement(currentObj)
// Position the focus on the next element
//
// Parameters:
// currentObj - the current object with the focus
// forward    - true, position to next element (e.g. tab)
//              false, position to previous element (e.g. shift tab)
// 
// Returns:
// true if successfully move the focus
//**********************************************************************************	
function positionFocusOnNextElement(currentObj, forward)
{
	var focus = false;
	
	// loop through all the elements
	var objs = document.getElementsByTagName('input');
	for (i=0; i<objs.length; i++)
	{
		obj = objs[i];
		if (obj.id == currentObj.id)
		{
			focus = true;
			
			// focus on previous, element, then search back again!
			if (!forward)
			{
				for (j=i-1; j>=0; j--)
				{
					obj = objs[j];
					if (!obj.readOnly && obj.type!='hidden')
					{
						try
						{
							obj.focus();
							return true;
						}
						catch (e)
						{
						}
					}
				}
				return;
			}
		}
		
		// found the target object, then position to the next available element
		else if (focus && !obj.readOnly && obj.type!='hidden')
		{
			try
			{
				obj.focus();
				return true;
			}
			catch (e)
			{
			}
		}
	}
	return false;
}


//**********************************************************************************
//setNextTab(fromField, toField)
//Sets an attribute in both the From field and the To field to indicate tab order
//
//Parameters:
//from - text field object
//to   - text field object
//**********************************************************************************	
function setNextTab(from, to)
{
	var fromField = document.getElementById(from);
	var toField = document.getElementById(to);
	
	// special case - YNI radio fields
	if (fromField != null && fromField.type == 'hidden' && document.getElementById(from + 'NYNI') != null)
	{
		fromField = document.getElementById(from + 'NYNI');
	}
	if (toField != null && toField.type == 'hidden' && document.getElementById(to + 'YYNI') != null)
	{
		toField = document.getElementById(to + 'YYNI');
	}
	
	// map fields
	if (fromField != null && isInputField(fromField) && fromField.isDisabled == false)
	{	
		if (toField != null && isInputField(toField) && toField.isDisabled == false)
		{
			// standard mapping
			fromField.nextTab = toField.id;
			if (toField.prevTab == null)
			{
				toField.prevTab = fromField.id;
			}
			
			// add extra mapping YYNI to NYNI ?
			if (toField.id == to + 'YYNI' && document.getElementById(to + 'NYNI') != null)
			{
				toField.nextTab = to + 'NYNI';
				document.getElementById(to + 'NYNI').prevTab = toField.id;
			}
		}
		
		// if the 'to field' doesn't exist map the id anyway so we can attempt to use it later
		else
		{
			fromField.nextTab = to;
		}
		
		// if mapped to the first input field then this is the last field on the
		// screen tab-wise, which may be different to the 'last input field' set up in
		// mainBodyFieldProcessing(), if so adjust it now...
		if (toField != null && toField.id == objFirstField.id && fromField.id != objLastInputField.id)
		{	
			// remove old last input field Listeners
			removeMyEventListener(objLastInputField, 'keydown', lastInputFieldKeyDown);
			if (objLastInputField.className.indexOf('wf_VALIDVALUE') >=0)
			{
				var obj = document.getElementById(objLastInputField.id + 'ValidValueSelect');
				removeMyEventListener(obj, 'keydown', lastInputFieldKeyDown);
			}

			// add new last input field Listeners
			objLastInputField = fromField;
			addMyEventListener(objLastInputField, 'keydown', lastInputFieldKeyDown);
			if (objLastInputField.className.indexOf('wf_VALIDVALUE') >=0)
			{
				var obj = document.getElementById(objLastInputField.id + 'ValidValueSelect');
				addMyEventListener(obj, 'keydown', lastInputFieldKeyDown);
			}
		}
	}
	
	// handle 'from field' is not an input field
	else
	{
		// try to find a field that is mapped to the from field's id and replace
		// the from field's id with the to field's id
		var objList = document.getElementsByTagName("input");
		for(var i=0; i<objList.length; i++)
		{
			if (objList[i].nextTab == from)
			{
				if (toField != null)
				{
					objList[i].nextTab = toField.id;
				
					if (toField.prevTab == null)
					{
						toField.prevTab = objList[i].id;
					}
				
					// add extra mapping YYNI to NYNI ?
					if (toField.id == to + 'YYNI' && document.getElementById(to + 'NYNI') != null)
					{
						toField.nextTab = to + 'NYNI';
						document.getElementById(to + 'NYNI').prevTab = toField.id;
					}
				}
				else
				{
					objList[i].nextTab = to;
				}
				
				break;
			}
		}
	}
}


//**********************************************************************************
//focusOnNextTab(forward, field)
//Sets focus on to the next input capable field when Tab is pressed, in the order
//set up by the setNextTab() function
//
//Parameters:
//forward - true = Tab, false = Shift Tab
//field   - text field object, the field currently in focus
//
//Returns:
//true if successfully set the focus
//**********************************************************************************	
function focusOnNextTab(forward, field)
{
	var tabField = null;
	
	// Tab
	if (forward)
	{
		if (field.nextTab == null)
		{
			return false;
		}
		
		tabField = document.getElementById(field.nextTab);
		if (tabField == null)
		{
			return false;
		}
	
		while (!isInputField(tabField))
		{
			if (tabField.nextTab == null)
			{
				return false;
			}
			tabField = document.getElementById(tabField.nextTab);
			if (tabField == null)
			{
				return false;
			}
		}
	}
	
	// Shift tab
	else
	{
		if (field.prevTab == null)
		{
			return false;
		}
		
		tabField = document.getElementById(field.prevTab);
		if (tabField == null)
		{
			return false;
		}
	
		while (!isInputField(tabField))
		{
			if (tabField.prevTab == null)
			{
				return false;
			}
			tabField = document.getElementById(tabField.prevTab);
			if (tabField == null)
			{
				return false;
			}
		}
	}
	
	objectFocus(tabField);
	return true;
}


//**********************************************************************************
// function bodyMouseDown(e)
// Mouse down event
//
// Parameters:
// e - event
//
// Returns:
// true if event handled
//**********************************************************************************	
function bodySelectionMouseDown(e)
{
	// hide it
	if (mouseSelectionBoxPopup != null)
	{
		mouseSelectionBoxPopup.hidePopup();
	}
	
	// click on other popup?
	if (anyOpenPopupOtherThanSelectionBox(e))
	{
		return;
	}

	// target element must not be a clickable item
	var srcElement = getEventTarget(e);
	var srcElementTag = srcElement.tagName;
	if (srcElementTag == 'IMG' || srcElementTag == 'INPUT' || srcElementTag=='SPAN'
		 || srcElementTag=='SELECT' || srcElementTag=='A'
		 || (srcElementTag == 'TD' && (srcElement.children.length == 1 && srcElement.firstChild.tagName == 'SPAN'))
		 || (
				 srcElementTag == 'DIV' && 
				 (srcElement.className == 'scrollingY repeatingDataDiv' || srcElement.className == 'scrollingX repeatingFooterDiv')
			)
		)
	{
		return true;
	}
	
	// create the selection box
	if (mouseSelectionBoxPopup == null)
	{
		mouseSelectionBoxPopup = new PopupWindow('mouseSelectionBoxPopupID'); 		     	
		mouseSelectionBoxDiv = document.createElement('div');
		mouseSelectionBoxDiv.id = 'mouseSelectionBoxPopupID';    
		mouseSelectionBoxDiv.className = 'mouseSelectionBox';    
	 	document.body.appendChild(mouseSelectionBoxDiv);
 		mouseSelectionBoxPopup.hidePopup();
    	mouseSelectionBoxStatus = new MouseSelectionBoxStatus();
	}

	// store the location
	pos = getMousePosition(e);
	mouseSelectionBoxStatus.setActive(true);
	mouseSelectionBoxStatus.setCoordinates(pos[0],pos[1],pos[0],pos[1]);
	mouseSelectionBoxStatus.showMouseSelectionBox();
	return false;
}


//**********************************************************************************
// function bodyMouseDown(e)
// Mouse down event
//
// Parameters:
// e - event
//
// Returns:
// true if event handled
//**********************************************************************************	
function bodySelectionMouseUp(e)
{
	// selection box active?
	if (mouseSelectionBoxStatus==null || !mouseSelectionBoxStatus.isActive())
	{
		return true;
	}
	
	// store the location
	pos = getMousePosition(e);
	mouseSelectionBoxStatus.setActive(false);
	mouseSelectionBoxStatus.setRBCoordinates(pos[0], pos[1]);
	mouseSelectionBoxStatus.adjustCoordinates();
	
	// no size?
	if (mouseSelectionBoxStatus.isEmpty())
	{
		mouseSelectionBoxPopup.hidePopup();
	}
	
	return false;
}


//**********************************************************************************
// function bodyMouseDown(e)
// Mouse down event
//
// Parameters:
// e - event
//
// Returns:
// true if event handled
//**********************************************************************************	
function bodySelectionMouseMove(e)
{
	// selection box active?
	if (mouseSelectionBoxStatus==null || !mouseSelectionBoxStatus.isActive())
	{
		return true;
	}

	// get current position
	pos = getMousePosition(e);
	
	// adjust the size of the box
	mouseSelectionBoxStatus.setRBCoordinates(pos[0], pos[1]);
	mouseSelectionBoxStatus.showMouseSelectionBox();
	return false;
}


//**********************************************************************************
// function MouseSelectionBoxStatus()
// Selection box object
//**********************************************************************************	
function MouseSelectionBoxStatus()
{
	this.mouseSelectionBoxLeft = 0;
	this.mouseSelectionBoxTop = 0;
	this.mouseSelectionBoxRight = 0;
	this.mouseSelectionBoxBottom = 0;
	this.mouseSelectionBoxActive = false;
	
	//*************************************
	// Set whether box is active or not
	//*************************************	
	this.setActive = function(active)
	{
		this.mouseSelectionBoxActive = active;
	};
	
	//*************************************
	// Determine whether box is active or not
	//*************************************	
	this.isActive = function()
	{
		return this.mouseSelectionBoxActive;
	};
	
	//*************************************
	// Set the coordinates
	//*************************************	
	this.setCoordinates = function(left,top,right,bottom)
	{
		this.mouseSelectionBoxLeft = left;
		this.mouseSelectionBoxTop = top;
		this.mouseSelectionBoxRight = right;
		this.mouseSelectionBoxBottom = bottom;
	};
	
	//*************************************
	// Set the right-bottom coordinates
	//*************************************	
	this.setRBCoordinates = function(right,bottom)
	{
		this.mouseSelectionBoxRight = right;
		this.mouseSelectionBoxBottom = bottom;
	};
	
	//*************************************
	// Determine if empty box
	//*************************************	
	this.isEmpty = function()
	{
		if (this.mouseSelectionBoxLeft==this.mouseSelectionBoxRight &&
				this.mouseSelectionBoxTop==this.mouseSelectionBoxBottom)
		{
			return true;
		}
		else
		{
			return false;
		}
	};
		
	//*************************************
	// Show the selection box
	//*************************************	
	this.showMouseSelectionBox = function()
	{
		var xleft, xright, xtop, xbottom;

		// movement of mouse is left to right, or right to left?
		if (this.mouseSelectionBoxLeft <= this.mouseSelectionBoxRight)
		{
			xleft = this.mouseSelectionBoxLeft;
			xright = this.mouseSelectionBoxRight;
		}
		else
		{
			xleft = this.mouseSelectionBoxRight;
			xright = this.mouseSelectionBoxLeft;
		}
		
		// movement of mouse is top to bottom, or bottom to top?
		if (this.mouseSelectionBoxTop <= this.mouseSelectionBoxBottom)
		{
			xtop = this.mouseSelectionBoxTop;
			xbottom = this.mouseSelectionBoxBottom;
		}
		else
		{
			xtop = this.mouseSelectionBoxBottom;
			xbottom = this.mouseSelectionBoxTop;
		}
		
		// adjust the box
		mouseSelectionBoxDiv.style.width  = (xright - xleft) + "px" ;
		mouseSelectionBoxDiv.style.height = (xbottom - xtop) + "px";
		mouseSelectionBoxPopup.showPopupAbsoluteXY(xleft, xtop, null);
	};
	
	//**********************************************************************************
	// Determine whether object is within the mouse selection box
	//
	// Parameters:
	// obj - the object element
	//
	// Returns:
	// the object containing the position and a return status of
	//	 	0 - within the box
	// 		-1 - not within a box as the object is below 
	// 		-2 - not within a box as the object is above
	// 		-3 - not within a box as the object is on the left
	// 		-4 - not within a box as the object is on the right
	//**********************************************************************************	
	this.withinMouseSelectionBox = function(obj)
	{
		var pos = getPos(obj);
		var ret = new Object();
		
		ret.left = pos.left;
		ret.right = ret.left + obj.offsetWidth;
		ret.top = pos.top;
		ret.bottom = ret.top + obj.offsetHeight;
		ret.status = 0;
		
		// object is below the box
		if (this.mouseSelectionBoxBottom < ret.bottom)
		{
			ret.status = -1;
		}
		
		// object is above the box
		else if (this.mouseSelectionBoxTop > ret.top)
		{
			ret.status = -2;
		}
		
		// object is on the left
		else if (this.mouseSelectionBoxLeft > ret.left)
		{
			ret.status = -3;
		}
		
		// object is on the right
		else if (this.mouseSelectionBoxRight < ret.right)
		{
			ret.status = -4;
		}
		
		return ret;
	};
	
	//*************************************
	// Adjust coordinates making sure upper-left < bottom-right
	//*************************************	
	this.adjustCoordinates = function()
	{
		var i = 0;
		
		// adjust mouse selection box, make sure left<right and top<bottom
		if (this.mouseSelectionBoxLeft > this.mouseSelectionBoxRight)
		{
			i = this.mouseSelectionBoxLeft;
			this.mouseSelectionBoxLeft = this.mouseSelectionBoxRight;
			this.mouseSelectionBoxRight = i;
		}
		
		if (this.mouseSelectionBoxTop > this.mouseSelectionBoxBottom)
		{
			i = this.mouseSelectionBoxTop;
			this.mouseSelectionBoxTop = this.mouseSelectionBoxBottom;
			this.mouseSelectionBoxBottom = i;
		}
	};
}


//**********************************************************************************
// function ClipBoardDataCopy()
// Clipboard data during copying
//**********************************************************************************	
function ClipBoardDataCopy()
{
	this.text  = '';
	this.texts = new ArrayList();
	this.flags = new ArrayList();
	this.fieldFieldLeftPos = 0;
	this.fieldFieldRightPos = 0;
	this.leftMostFieldPosition = 0;
	this.rightMostFieldPosition = 0;

	//*************************************
	// Determine if there is no data
	//*************************************	
	this.isEmpty = function()
	{
		return (this.texts.size == 0);
	};
	
	
	//*************************************
	// Add a new line
	//*************************************
	this.newLine = function()
	{
		this.texts.add("");
	};
	
	
	//*************************************
	// Set the text in the buffer
	//*************************************
	this.setText = function(text)
	{
		this.text = text;
	};
	
	
	//*************************************
	// Save the buffer text into the array
	//*************************************
	this.saveBuffer = function(flag)
	{
		if (this.text.length > 0)
		{
			this.texts.add(this.text);
			this.flags.add(flag);
			this.text = '';
		}
	};
	
	
	//*************************************
	// Add buffer text into existing line
	//*************************************
	this.addBufferText = function(text)
	{
		this.text += text;
	};
	
	
	//*************************************
	// Return the copied text
	//*************************************	
	this.getData = function()
	{
		var i = 0;
		var data = '';
		for (i=0; i<this.texts.size(); i++)
		{
			data += this.texts.get(i) + NEXTLINE;
		}
		
		return data;
	};
	

	//*************************************
	// Save data into clipboard
	//*************************************	
	this.save = function(desktopStyle)
	{
		if (desktopStyle)
		{
			var lastLine = 0;
			while (lastLine < this.texts.size())
			{
				lastLine = this.tabular(lastLine,this.texts.size()-1);
				if (lastLine == -1)
				{
					break;
				}
				lastLine++;
			}
		}
		
		window.clipboardData.setData("Text", this.getData());
	};
	

	//*************************************
	// Reparse copied data.  This will processed only 
	// those flags that are TRUE
	// 
	// Returns the last line processed
	//*************************************	
	this.tabular = function(startLineIndex, endLineIndex)
	{
		// retrieve first line to be parsed from startIndex
		var n1 = -1;
		for (var i=startLineIndex; i<=endLineIndex; i++)
		{
			if (this.flags.get(i))
			{
				n1 = i;
				break;
			}
		}
		
		// found?
		if (n1 == -1)
		{
			return -1;
		}

		// retrieve last line to be parsed until endIndex
		var n2 = endLineIndex;
		for (var i=n1+1; i<=endLineIndex; i++)
		{
			if (!this.flags.get(i))
			{
				// let us peek on the next one
				// if to be parsed, then assume it is on the
				// same tabular format
				if (this.flags.get(i+1) != true)
				{
					n2 = i - 1;
					break;
				}
			}
		}
		
		// one line only, then only process this line
		if (n1==n2)
		{
			this.texts.set(n1,this.tabular_processWithTabClue(this.texts.get(n1), TABCLUE));
			return n2;
		}
		
		// process all the regions of space for the start line
		var blankIndex = 0;
		while (blankIndex >=0 && blankIndex < this.texts.get(n1).length)
		{
			blankIndex = this.tabular_processLine(n1,n2,blankIndex);
			if (blankIndex == -1)
			{
				break;
			}
			blankIndex++;
		}
		
		// return the last line parsed
		return n2;
	};
	
	
	//**********************************************************************************
	// This compares all the lines from startIndex to endIndex, starting from the
	// index position blankIndex.  It will put the TAB character where there is a 
	// space in all of the affected lines
	//**********************************************************************************
	this.tabular_processLine = function(startIndex,endIndex, blankIndex)
	{
		// get a region of space
		var blankObj = this.texts.get(startIndex).searchBlankRegion(blankIndex);
		var lastBlankIndex = blankObj.endIndex;
		
		// no blanks?
		if (lastBlankIndex == -1)
		{
			return lastBlankIndex;
		}
		
		// are there any tab in between?
		var tabIndex = this.texts.get(startIndex).indexOf(TAB);
		
		// try to put tab in the rest of the lines
		var fSetChar = false;
		if (tabIndex >= blankObj.startIndex && tabIndex <= blankObj.endIndex)
		{
			fSetChar = true;
		}

		// check all the spaces in the rest of the lines
		else
		{
			for (var i=startIndex+1; i<=endIndex; i++)
			{
				if (this.flags.get(i))
				{
					var nextBlankObj = this.texts.get(i).searchBlankRegion(blankObj.startIndex);
					
					// there are no blanks on this line, then ignore this region of space
					if (nextBlankObj.endIndex == -1)
					{
						break;
					}
					
					// adjust start index
					if (nextBlankObj.startIndex > blankObj.startIndex)
					{
						blankObj.startIndex = nextBlankObj.startIndex;
					}
					
					// adjust end index
					if (nextBlankObj.endIndex < blankObj.endIndex)
					{
						blankObj.endIndex = nextBlankObj.endIndex;
					}
					
					// no more?
					if (blankObj.startIndex > blankObj.endIndex)
					{
						break;
					}
				}
			}
			
			// are there space?
			if (blankObj.startIndex <= blankObj.endIndex)
			{
				tabIndex = blankObj.startIndex;
				fSetChar = true;
			}
		}
		
		// set the tab?
		if (fSetChar)
		{
			for (var i=startIndex; i<=endIndex; i++)
			{
				if (this.flags.get(i))
				{
					// blank, set to TAB
					if (this.texts.get(i).charAt(tabIndex) == ' ')
					{
						this.texts.set(i,this.texts.get(i).setCharAt(tabIndex,TAB));
					}
				}
			}
		}
		
		// return the last blank index of the first line
		return lastBlankIndex;
	}
	
	//**********************************************************************************
	// Replace the string containing tabclue with tab character
	// It replaces consecutive spaces (held in TABCLUE) with TAB key
	//**********************************************************************************
	this.tabular_processWithTabClue = function(value, tabclue)
	{
		var i = 0;
		while (i>=0)
		{
			// search for the marker/flag
			i = value.indexOf(tabclue,i);
			if (i>=0)
			{
				// determine next non-blank
				var j = i;
				while (value.charAt(j) == ' ')
				{
					j++;
					if (j >= value.length)
					{
						break;
					}
				}
				
				// copy the data
				value = value.substr(0,i) + TAB + value.substr(j);
			}
		}
		return value;
	}
	
}


//**********************************************************************************
// function ClipBoardDataPaste()
// Clipboard data during pasting
//
// Parameters:
// firstField - the first input field 
//**********************************************************************************	
function ClipBoardDataPaste(firstField)
{
	// initialise the text from the clipboard
	this.text = window.clipboardData.getData("Text");

	// initialise other coordinates
	this.left = 0;
	this.top = 0;
	this.right= 0;
	this.bottom = 0;
	this.firstfieldleft = 0;
	this.leftdiff = 0;
	this.rightdiff = 0;
	
	// desktop clipboard?
	if(this.text.indexOf(mouseSelectionSpecialCode) == 0)
	{
		// remove the special code
		this.text = this.text.substr(mouseSelectionSpecialCode.length);

		// retrieve the status code
		if (this.text.indexOf(mouseSelectionStatusCode) == 0)
		{
			var i = this.text.indexOf(NEXTLINE);
			var status = this.text.substr(0,i).substr(mouseSelectionStatusCode.length);
			this.text = this.text.substr(i+NEXTLINE.length);
			
			var arr = status.split(",");
			this.left =  arr.length>=1 ? (arr[0] - 0) : 0;
			this.top = arr.length>=2 ? (arr[1] - 0) : 0;
			this.right= arr.length>=3 ? (arr[2] - 0) : 0;
			this.bottom = arr.length>=4 ? (arr[3] - 0) : 0;
			this.firstfieldleft = arr.length>=5 ? (arr[4] - 0) : 0;  	// first field left position
			this.firstfieldright = arr.length>=6 ? (arr[5] - 0) : 0;  	// first field right position
			this.leftdiff = arr.length>=7 ? (arr[6] - 0) : 0; 			// difference to the left most field
			this.rightdiff = arr.length>=8 ? (arr[7] - 0) : 0; 		// difference to the right most field
			var isRTL = arr.length>=9 ? arr[8] : RTL; 						// rtl orientation?
			
			// copying / pasting on different orientation, then switch the left/right diff
			if (isRTL != RTL + "")
			{
				i = this.leftdiff;
				this.leftdiff = -this.rightdiff;
				this.rightdiff = -i;
			}
		}
	}
	
	// check for the data to be pasted
	else
	{
		// let desktop handle pasting when
		// - the data contains the next line / tab character
		// - the data is larger than the field it is pasting into
		if ((this.text.indexOf(NEXTLINE) >= 0) || 
			(this.text.indexOf(TAB) >= 0) ||
			(getFieldLength(firstField) < this.text.length)
			)
		{
			// do nothing
		}
		
		// let window handle pasting
		else
		{
			this.text = '';
		}
	}

	//*************************************
	// Set the data
	//*************************************	
	this.setData = function()
	{
		return this.text;
	};
	
	//*************************************
	// Determine if there is no data
	//*************************************	
	this.isEmpty = function()
	{
		return (this.text.length == 0);
	};

	//**********************************************************************************
	// Return the next set of characters up to the specified length
	//**********************************************************************************
	this.getNextClipBoardItem = function(length)
	{
		// got text in the clipboard?
		if (this.text.length == 0)
		{
			return "";
		}
		
		// determine the next line index
		var nextlineIndex = this.text.indexOf(NEXTLINE);
		if (nextlineIndex==0)
		{
			return "";
		}

		// retrieve the value for this current line
		var value = this.text;
		if (nextlineIndex > 0)
		{
			value = this.text.substr(0,nextlineIndex);
		}
		
		// does it contain tab - a tab means it is for the next field
		var tabindex = value.indexOf(TAB);
		if (tabindex >= 0 && tabindex < length)
		{
			value = value.substr(0,tabindex);
			this.removeTextFromClipBoardItem(tabindex + 1);
			return value.trim();
		}
		
		// if the required length is greater than or equal the current line, then set the field
		// to the current line 
		if (length >= value.length)
		{
			this.removeTextFromClipBoardItem(value.length);
			return value.trim();
		}
		
		// if the required length is less than the current line, then set only the subset
		else
		{
			// is the next one a space/tab?  If it is, then also removed it, this is because
			// the system add a space/tab between fields 
			// E.g. 0000 000001 001 - if it is pasting 0000, it should also removed the
			// space afterwards, in order to paste the 000001 property on the next field
			if (value.substr(length,1) == ' ' || value.substr(length,1) == TAB)
			{
				this.removeTextFromClipBoardItem(length + 1);
			}
			else
			{
				this.removeTextFromClipBoardItem(length);
			}
			
			// return the value
			return value.substr(0,length).trim();
		}
	};


	//**********************************************************************************
	// Return the next set of characters on the next line
	//**********************************************************************************
	this.getNextLineClipBoardItem = function(length)
	{
		// remove the current line
		this.removeCurrentLineFromClipBoardItem();
		
		// return the text
		return this.getNextClipBoardItem(length);
	};

	//**********************************************************************************
	// Remove the current line
	//**********************************************************************************
	this.removeCurrentLineFromClipBoardItem = function()
	{
		// determine the next line index
		var nextlineIndex = this.text.indexOf(NEXTLINE);
		
		// no more lines, then blank it out
		if (nextlineIndex==-1)
		{
			this.text = "";
		}
		
		// remove current line
		else
		{
			this.text = this.text.substr(nextlineIndex + NEXTLINE.length);
		}
	};

	//**********************************************************************************
	// Remove the specified number of characters
	//**********************************************************************************
	this.removeTextFromClipBoardItem = function(length)
	{
		this.text = this.text.substr(length);
	};
	
}
	
	
//**********************************************************************************
// function validObjectMouseSelectionBox(obj,copy)
// Determine whether object is valid for copying/pasting
//
// Parameters:
// obj - the object element
// copy - true if copying, false if pasting
//
// Returns:
// true if this is a valid object for copying/pasting
//**********************************************************************************
function validObjectMouseSelectionBox(obj,copy)
{
	// can copy input element or span only
	var tagName = obj.tagName;
	if (tagName != 'INPUT' && tagName != 'SPAN' && tagName != 'SELECT')
	{
		return false;
	}
	
	// must not have any child
	if (obj.children.length > 0)
	{
		return false;
	}
	
	// object must not be hidden
	if(obj.type == 'hidden' || getStyle(obj,'visibility')=='hidden' || getStyle(obj,'display')=='none')
	{
		return false;
	}
	
	// make sure parent is not hidden - check up to 2 level only - cell - row
	var parent = obj.parentElement
	while(parent)
	{
		if (getStyle(parent,'visibility')=='hidden' || getStyle(parent,'display')=='none')
		{
			return false;
		}
		parent = parent.parentElement;
	}
	
	// field must not be hidden
	var inputFieldObj = rtvInputFieldFromDisplayField(obj);
	if (isHiddenField(inputFieldObj))
	{
		return false;
	}
	
	// row must not be hidden
	var row = document.getElementById(inputFieldObj.id + ROW_SUFFIX);
	if (row == null)
	{
		if (inputFieldObj.leftmostfield != null)
		{
			row = document.getElementById(inputFieldObj.leftmostfield + ROW_SUFFIX);
		}
	}
	
	if (row != null)
	{
		if(getStyle(row,'visibility')=='hidden' || getStyle(row,'display')=='none')
		{
			return false;
		}
	}
	
	// yno?
	if (obj.className.indexOf('wf_YNO') >= 0)
	{
		inputFieldObj = rtvInputFieldFromDisplayField(obj);
		if (inputFieldObj == null)
		{
			return false;
		}
		
		// process only the first element
		displayField = rtvDisplayFieldFromInputField(inputFieldObj,'*first');
		if (obj.id != displayField.id)
		{
			return false;
		}
		
		// is this visible?
		displayField = rtvDisplayFieldFromInputField(inputFieldObj,'*field');
		if(getStyle(displayField,'visibility')=='hidden' || getStyle(displayField,'display')=='none')
		{
			return false;
		}
		
		return true;
	}
	
	// text box?
	if (obj.type == 'text')
	{
		return true;
	}

	// check box?
	if (obj.type == 'checkbox')
	{
		return true;
	}

	// span
	if (obj.tagName == 'SPAN')
	{
		if (copy)
		{
			return true;
			// return getValueFromSpanInput(obj).trim().length > 0;
		}
		else
		{
			return false;
		}
	}
	
	// radio 
	if (obj.type == 'radio')
	{
		return true;
	}

	// select 
	if (obj.tagName == 'SELECT')
	{
		return true;
	}

	// valid value?
	if (obj.className.indexOf('wf_VALIDVALUE') >= 0)
	{
		return true;
	}

	// not valid
	return false;
}


//**********************************************************************************
// function triggerCopy()
// Copy selected fields.  This put into internal memory all the field values within
// the selection box
//
// Parameters:
// desktopStyle - determine whether copy desktop copy style or not?
// - true: copied data will contain marker to determine field separator,
// 		row separator, etc
// - false: make the copied data compatible with other application
//
// Returns:
// true - let browser handle event
// false - the system has handled event
//**********************************************************************************
function triggerCopy(desktopStyle)
{
	// is the mouse selection box displayed?
	if (mouseSelectionBoxDiv==null || mouseSelectionBoxDiv.style.visibility == 'hidden')
	{
		return true;
	}
	
	// copy all the fields within the selection box
	elem = document.all;
	
	var mouseSelectionCopy = "";
	var clipboard = new ClipBoardDataCopy();	
	var previousHeight = 0;
	var flag = false;

	for(var i = 0; i < elem.length; i++) 
	{ 
		var obj = elem[i];
		
		// process 
		if (validObjectMouseSelectionBox(obj,true))
		{
			// retrieve the actual field
			var inputFieldObj = rtvInputFieldFromDisplayField(obj);
			
			if (inputFieldObj != null)
			{
				// determine if within the box
				within = mouseSelectionBoxStatus.withinMouseSelectionBox(obj);
				if (within.status>=0)
				{
					var length = getFieldLength(inputFieldObj);
					var value = getValueFromSpanInput(inputFieldObj);
					var valueCopy = value.pad(length);
					
					if (previousHeight == 0)
					{
						clipboard.addBufferText(valueCopy);
						clipboard.fieldFieldLeftPos = within.left;
						clipboard.fieldFieldRightPos = within.right;
					}
					
					// next line?
					else if (!fieldWithinSameHeight(previousHeight, within.top))
					{
						clipboard.saveBuffer(flag);
						clipboard.addBufferText(valueCopy);
						flag = false;
					}
					
					// same line as before
					else
					{
						if (desktopStyle)
						{
							clipboard.addBufferText(TAB + valueCopy);
						}
						else
						{
							clipboard.addBufferText(' ' + valueCopy);
						}
					}
					
					// big field?
					if (valueCopy.length > TABCLUE_LENGTH)
					{
						flag = true;
					}
					
					// set previous height
					previousHeight = within.top;
					
					// determine position of left most field
					if (clipboard.leftMostFieldPosition == 0)
					{
						clipboard.leftMostFieldPosition = within.left;
					}
					else if (clipboard.leftMostFieldPosition > within.left)
					{
						clipboard.leftMostFieldPosition = within.left;
					}
					
					// determine position of right most field
					if (clipboard.rightMostFieldPosition == 0)
					{
						clipboard.rightMostFieldPosition = within.right;
					}
					else if (clipboard.rightMostFieldPosition < within.right)
					{
						clipboard.rightMostFieldPosition = within.right;
					}
				}
				else if (within.status==-1)
				{
					break;
				}
			}
		}
	}
	
	// copy to clipboard - if there is something to copy
	clipboard.saveBuffer(flag);
	if (!clipboard.isEmpty())
	{
		clipboard.save(desktopStyle);
	}
	
	// hide the popup
	mouseSelectionBoxPopup.hidePopup();
	
	return false;
}


//**********************************************************************************
// function triggerPaste(srcElement)
// Paste selected fields.  This paste the internal memory into the fields starting
// from this object
//
// Parameters:
// srcElement - starting object
//
// Returns:
// true - let browser handle event
// false - the system has handled event
//**********************************************************************************
function triggerPaste(srcElement)
{
	// clipboard
	var clipboard = new ClipBoardDataPaste(srcElement);
	
	// let browser handle standard processing if the clipboard is not special
	if (clipboard.isEmpty())
	{
		return true;
	}
	
	// retrieve the actual hidden input field
	inputSrcElement = rtvInputFieldFromDisplayField(srcElement);
	
	// search for this element within the list
	var fieldFieldLeftPos = 0;
	var fieldFieldRightPos = 0;
	var previousHeight = 0;
	var found = false;
	var elem = document.forms[0].elements;
	for(var i = 0; i < elem.length; i++) 
	{
		var obj = elem[i];
		if (inputSrcElement.id == obj.id)
		{
			found = true;
		}
		
		// start copying if the source object has been found, and this is a valid object
		if (found && validObjectMouseSelectionBox(obj,false))
		{
			// determine position of object
			var pos = getPos(obj);
			
			// if the object's position is on the same column or to the right of the left-most object during copying
			if (previousHeight == 0 
					|| (!RTL && fieldOnTheRight(clipboard, fieldFieldLeftPos, pos.left))
					|| (RTL && fieldOnTheLeft(clipboard, fieldFieldRightPos, pos.left+obj.offsetWidth))
				)
			{
				obj = rtvInputFieldFromDisplayField(obj);
	
				if (obj != null)
				{
					var value = "";
					var length = getFieldLength(obj);
					if (previousHeight == 0)
					{
						value = clipboard.getNextClipBoardItem(length);
						fieldFieldLeftPos = pos.left;
						fieldFieldRightPos = pos.left + obj.offsetWidth;
					}
					else if (!fieldWithinSameHeight(previousHeight, pos.top))
					{
						value = clipboard.getNextLineClipBoardItem(length);
					}
					else
					{
						value = clipboard.getNextClipBoardItem(length);
					}
					previousHeight = pos.top;
	
					// set the value
					if (!obj.readOnly && value.length > 0)
					{
						obj.value = value;
						setAnyFieldValue(obj.id, value);
					}
					
					// got text in the clipboard?
					if (clipboard.isEmpty())
					{
						break;
					}
				}
			}
		}
	}
	
	return false;
}


//**********************************************************************************
// function fieldWithinSameHeight(height1, height2)
// Determine whether the height is within the same row.  This is determined if the difference is within specified limit
//
// Parameters:
// height1 - the first top position of field 1
// height2 - the second top position of field 2
//
// Returns:
// None
//**********************************************************************************
function fieldWithinSameHeight(height1, height2)
{
	var diff = 0;
	if (height1 == height2)
	{
		return true;
	}
	else if (height1 > height2)
	{
		diff = height1 - height2;
	}
	else
	{
		diff = height2 - height1;
	}
	
	return (diff <= toleranceTop);  // within 5 pixel
}


//**********************************************************************************
// function fieldOnTheRight(clipboard, refFieldPos, objFieldPos)
// Determine whether a field in on the right hand side in relation to copying
//
// Parameters:
// clipboard - the clipboard
// refFieldPos - the reference field left position
// objFieldPos - the object field left position
//
// Returns:
// true if it is on the right of the reference field
//**********************************************************************************
function fieldOnTheRight(clipboard, refFieldPos, objFieldPos)
{
	// Copy: from 2 fields
	// Reference 1: __________________     
	// Reference 2: __________________     
	//
	// If we are pasting on a screen with 4 fields arrange as in 2 x 2,
	//
	// Narrative 1: __________________     Narrative 2: _________________
	// Narrative 3: __________________     Narrative 4: _________________
	//
	// If we paste on Narrative 1, it should populate Narrative 1 and 3
	// If we paste on Narrative 2, it should populate Narrative 2 and 4

	var diff = refFieldPos - objFieldPos;
	
	// Copy action - negative or 0 - object is aligned or to the right
	// Reference 1: __________________     
	//      Reference 2: __________________
	if (clipboard.leftdiff <= 0)
	{
		// Paste action:
		// negative - then it means that the object is on the right of the reference field
		// Narrative 2: _________________
		//     Narrative 4: _________________
		if (diff <= 0)
		{
			// TODO: do we need to check if it is too much to the right?
			return true;
		}
		
		// Paste action:
		// positive - object is on the left of the reference field
		//      Narrative 2: _________________
		// Narrative 4: _________________
		else
		{
			// within tolerance
			return (diff <= toleranceLeft);
		}
	}

	// Copy action - position - object is on the left
	//      Reference 1: __________________     
	// Reference 2: __________________
	else
	{
		// Paste action:
		// negative - then it means that the object is on the right of the reference field
		// Narrative 2: _________________
		//     Narrative 4: _________________
		if (diff <= 0)
		{
			// TODO: do we need to check if it is too much to the right?
			return true;
		}
		
		// Paste action:
		// positive - object is on the left of the reference field
		//      Narrative 2: _________________
		// Narrative 4: _________________
		else
		{
			// make sure not too far on the left, within tolerance
			return (Math.abs(diff - clipboard.leftdiff)) < toleranceLeft;
			
		}
	}
}


//**********************************************************************************
// function fieldOnTheRight(clipboard, refFieldPos, objFieldPos)
// Determine whether a field in on the left hand side in relation to copying
//
// Parameters:
// clipboard - the clipboard
// refFieldPos - the reference field left position
// objFieldPos - the object field left position
//
// Returns:
// true if it is on the right of the reference field
//**********************************************************************************
function fieldOnTheLeft(clipboard, refFieldPos, objFieldPos)
{
	// Copy: from 2 fields
	//  __________________ : 1 Reference     
	//  __________________ : 2 Reference     
	//
	// If we are pasting on a screen with 4 fields arrange as in 2 x 2,
	//
	// __________________ : 2 Narrative     _________________ : 1 Narrative
	// __________________ : 4 Narrative     _________________ : 3 Narrative
	//
	// If we paste on Narrative 1, it should populate Narrative 1 and 3
	// If we paste on Narrative 2, it should populate Narrative 2 and 4

	var diff = refFieldPos - objFieldPos;
	
	// Copy action - positive or 0 - object is aligned or to the left
	//         __________________ : 1 Reference     
	//  __________________ : 2 Reference     
	if (clipboard.rightdiff >= 0)
	{
		// Paste action:
		// positive - then it means that the object is on the left of the reference field
		//         __________________ : 1 Reference     
		//  __________________ : 2 Reference     
		if (diff >= 0)
		{
			// TODO: do we need to check if it is too much to the left?
			return true;
		}
		
		// Paste action:
		// negative - object is on the right of the reference field
		//  __________________ : 1 Reference     
		//        __________________ : 2 Reference     
		else
		{
			// within tolerance
			return ((-diff) <= toleranceLeft);
		}
	}

	// Copy action - position - object is on the left
	//  __________________ : 1 Reference     
	//        __________________ : 2 Reference     
	else
	{
		// Paste action:
		// positive - then it means that the object is on the left of the reference field
		//         __________________ : 1 Reference     
		//  __________________ : 2 Reference     
		if (diff >= 0)
		{
			// TODO: do we need to check if it is too much to the left?
			return true;
		}
		
		// Paste action:
		// negative - object is on the right of the reference field
		//  __________________ : 1 Reference     
		//        __________________ : 2 Reference     
		else
		{
			// make sure not too far on the right, within tolerance
			return (Math.abs(diff - clipboard.rightdiff)) < toleranceLeft;
			
		}
	}
}


//**********************************************************************************
// function bodyOnClick(e)
// Onclick event for the body
//
// Parameters:
// e - event
//
// Returns:
// None
//**********************************************************************************
function bodyOnClick(e)
{
	// Clear selected fields
	// .. when same button has been selected, the processing will be handled
	//    by that button
	if (!isButtonWithPopup(e.srcElement))
	{
		isClickSameField(1,2);
	}
}
