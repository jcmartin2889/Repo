var previousWidth = 0;
var tabbing = 0;
var window_size = 0;
var TYPE_PDF = 1;
var TYPE_EXCEL = 2;

//**********************************************************************************
// function submitForm()
// Submit the form 
//
// Parameters:
// None
//**********************************************************************************	
function submitForm()
{
	var form = getFrameCurrent().document.getElementsByTagName('form')[0];
	submitted = true;
	
	// change mouse cursor to hourglass
	startLoadingTransaction(true);
	document.body.style.cursor='wait';
	
	// submit
	form.submit();
	
	// disable input
	disableAllElements();
	document.getElementById('mainTD').disabled = true;
	hideOnSubmitBottomBar();
	
	// print the time
	window.top.startLoadTime = printTime("Submit",true);
	
	return form;
}

// **********************************************************************************
// function validateAndSubmit(key, frame)
// Submit the form and validate the page
//
// Parameters:
// key - function key pressed
// frame - not used
//
// Returns:
// true if page submitted
// **********************************************************************************	
function validateAndSubmit(key, frame)
{
	// page is still loading
	if(pageLoading)
	{
		return false;
	}
	
	// page already submitted, then ignore any more request
	if(submitted)
	{
		return false;
	}
	
	// modal window open?
	if (isAnyModalWindowOpen())
	{
		errorAlert(20,getLanguageLabel('GBL900046'));
		return false;
	}

	// setup the function key
	var ckey = KEY_ENT;
	if (key == 'PAGEUP') 
	{
		ckey = KEY_PGUP;
	}
	else if (key == 'PAGEDOWN') 
	{
		ckey = KEY_PGDN;
	}
	else if (key.substr(0,2) == 'CF') 
	{
		ckey = key.substr(2,key.length);
	}

	// is this a valid function key?
	if (!isFKeyValid(ckey))
	{
		errorAlert(20,getLanguageLabel('GBL900040'));
		return false;
	}
	
	// processing prior to submitting it
	if (validateAndSubmitUserExit(ckey))
	{
		return false;
	}

	// set the function key
	var obj = document.getElementById(OBJ_FKEY);
	obj.value = ckey;
	
	// additional LRP processing on <enter> key
	if (ckey == KEY_ENT || ckey == KEY_DEL || ckey == KEY_AUTHA)
	{
		lrpOnEnterSubmit();
	}

	// submit page
	submitForm();
	return true;
}

// **********************************************************************************
// function validateAndSubmitField(fieldName)
// Submit the form and validate the field
//
// Parameters:
// fieldName - field name
//
// Returns:
// true if page submitted
// **********************************************************************************	
function validateAndSubmitField(fieldName)
{
	// page already submitted, then ignore any more request
	if(submitted)
	{
		return false;
	}
	
	// modal window open?
	if (isAnyModalWindowOpen())
	{
		errorAlert(20,getLanguageLabel('GBL900046'));
		return false;
	}

	// assume F5=Verify has been pressed
	var obj = document.getElementById(OBJ_FKEY);
	obj.value = KEY_VERI;
	
	// set the field to validate
	obj = document.getElementById(OBJ_FLDVAL);
	obj.value = fieldName;

	// submit page
	submitForm();
	return true;
}

// **********************************************************************************
// function validateAndSubmitLoad(fieldSet,field)
// Submit the form and validate the field
//
// Parameters:
// fieldName - field name
//
// Returns:
// true if page submitted
// **********************************************************************************	
function validateAndSubmitLoad(fieldSet,field,loadKey)
{
	// page already submitted, then ignore any more request
	if(submitted)
	{
		return false;
	}
	
	// modal window open?
	if (isAnyModalWindowOpen())
	{
		errorAlert(20,getLanguageLabel('GBL900046'));
		return false;
	}

	// assume F9=Load has been pressed
	var obj = document.getElementById(OBJ_FKEY);
	obj.value = loadKey;
	
	// set the fields
	obj = document.getElementById(OBJ_LOADFIELDSET);
	obj.value = fieldSet;

	obj = document.getElementById(OBJ_LOADFIELD);
	obj.value = field;

	// submit page
	submitForm();
	return true;
}

//**********************************************************************************
// function validateAndSubmitDrillDown(repeatingGroup)
// Submit the form and perform drill down on the specified repeating group
//
// Parameters:
// repeatingGroup - perform selection processing on the repeating group
//
// Returns:
// true if page submitted
//**********************************************************************************	
function validateAndSubmitDrillDown(repeatingGroup)
{
	// page already submitted, then ignore any more request
	if(submitted)
	{
		return false;
	}
	
	// modal window open?
	if (isAnyModalWindowOpen())
	{
		errorAlert(20,getLanguageLabel('GBL900046'));
		return false;
	}

	// drill down key pressed
	var obj = document.getElementById(OBJ_FKEY);
	obj.value = KEY_DRILLDOWN;
	
	// set the repeating group to trigger
	obj = document.getElementById(OBJ_FLDVAL);
	obj.value = repeatingGroup;

	// submit page
	submitForm();
	return true;
}



// **********************************************************************************
// function validateAndSubmitUserExit(ckey)
// Processing prior to submitting the page
//
// Parameters:
// ckey - function key pressed
//
// Returns:
// true - abort submitting the page
// **********************************************************************************	
function validateAndSubmitUserExit(ckey)
{
	// F16 = Print
	if (ckey == KEY_PRINT)
	{
		printSession();
		return true;
	}

	// LRP Buttons for Decline and Refer
	var lrpTask = isLrpTask();
	if ((ckey == KEY_DECL || ckey == KEY_REFER) && lrpTask)
	{
		promptLRPComment(ckey);
		return true;
	}
	
	// F10 = Decline (by supervisor / checker)
	
	if (ckey == KEY_DECL && (checker == 1 || checker == 2 || checker == 3))
	{
		promptDecline();
		return true;
	}
	
	// Save / Save as Template
	if (ckey == KEY_SAVE || ckey == KEY_SVTMPL)
	{
		promptSaveAs(ckey, document.getElementById(OBJ_TRANID).value);
		return true;
	}
	
	// Toggle
	if (ckey == KEY_TOGLE)
	{
		triggerToggleField(objLastFocusInputField);
		return true;
	}	

	// If enter key has been pressed on a key details, then perform load processing
	if (ckey == KEY_ENT || ckey == '00')
	{
		var keyExist = document.getElementById(OBJ_KEYEXSTSET);
		var keyDetail = document.getElementById(OBJ_KEYDSPSET);
		if (keyExist.value == 'true'  && keyDetail.value == 'true')
		{
			validateAndSubmitLoad(document.getElementById(OBJ_FIELDSET).value,'',KEY_LOAD);
			return true;
		}
	}
	// If F12=Previous has been pressed on a non-key details, then go back to key screen
	if (ckey == KEY_PREV && status != 'R' && status != 'S')
	{
		var keyExist2 = document.getElementById(OBJ_KEYEXSTSET);
		var keyDetail2 = document.getElementById(OBJ_KEYDSPSET);
		if (keyExist2.value == 'true'  && keyDetail2.value == 'false')
		{
			validateAndSubmitLoad(document.getElementById(OBJ_FIELDSET).value,'',KEY_UNLOAD);
			return true;
		}
	}

	// Authorisation - prompt for supervisor password when remotely authorising transaction
	if (checker==1)
	{
		if (ckey == KEY_AUTH || ckey == KEY_AUTHA || ckey == '06')
		{
			 promptUserPassword(ckey);
			 return true;
		}
	}

	return false;
}

// **********************************************************************************
// function isFKeyValid(ckey)
// Determine whether a function key is valid or not
//
// Parameters:
// ckey - function key
// **********************************************************************************	
function isFKeyValid(ckey)
{
	var validKeys = document.getElementById(OBJ_VFKEYS).value;
	ckey = ' ' + triml(ckey,'0') + ' ';
	return (validKeys.indexOf(ckey) >= 0);	
}

// **********************************************************************************
// function showValidateFieldButton(fieldName)
// Display validate button to validate a specific field
//
// Parameters:
// fieldName - the field to be validated
// **********************************************************************************	
function showValidateFieldButton(fieldName)
{
	var onClickAction = "validateAndSubmitField('" + fieldName + "')";
	var hoverText = getLanguageLabel('GBLVERI');
	var labelText = fieldName + BUT_VALIDATE;
	var imagePath = '/' + getWebAppName() + '/equation/images/validate.gif';

	// WebFacing, then do not display the button if read-only
	var obj = document.getElementById(fieldName);
	if(isWebFacing() && obj.readOnly)
	{
		return;
	}
	
	// add the widget button
	var widgetButtonHTML = addWidgetButton(hoverText,labelText,onClickAction,imagePath);

	// protected - then hide the widget 
	if (obj.readOnly)
	{
		disableAnchor(labelText + BUT_HREF, true);
		visibleObj(labelText, false);
	}
	
	return widgetButtonHTML;
}

// **********************************************************************************
// function showLoadButton(fieldSet,field)
// Display load button
//
// Parameters:
// load     - true(load) false(unload)
// fieldSet - field set name
// field    - field name
// **********************************************************************************	
function showLoadButton(load,fieldSet,field)
{
	var onClickAction;
	var labelText = field + 'LoadButton';
	
	var hoverText;
	var imagePath;
	if (load)
	{
		onClickAction = "validateAndSubmitLoad('" + fieldSet + "','" + field + "',KEY_LOAD)";
		hoverText = getLanguageLabel('GBL000092');
		imagePath = '/' + getWebAppName() + '/equation/images/load.gif';
	}
	else
	{
		onClickAction = "validateAndSubmitLoad('" + fieldSet + "','" + field + "',validateAndSubmitLoad)";
		hoverText = getLanguageLabel('GBL000093');
		imagePath = '/' + getWebAppName() + '/equation/images/unload.gif';
	}
	
	var widgetButtonHTML = addWidgetButton(hoverText,labelText,onClickAction,imagePath);
	return widgetButtonHTML;
}

// **********************************************************************************
// function saveSession()
// Save the current session
//
// Parameters:
// None
// **********************************************************************************	
function saveSession()
{
	validateAndSubmit('CF51', null);
}

// **********************************************************************************
// function saveAsTemplate()
// Save the session as template
//
// Parameters:
// None
// **********************************************************************************	
function saveAsTemplate()
{
	validateAndSubmit('CF53', null);
}

// **********************************************************************************
// function retrieve()
// Trigger form load
//
// Parameters:
// None
// **********************************************************************************	
function retrieve()
{
	validateAndSubmit('CF55', null);
}


// **********************************************************************************
// function printSession()
// Print the current session
//
// Parameters:
// None
// **********************************************************************************	
function printSession(printType)
{
	updateFunctionDataField(rtvFieldDetails(), printSession2(printType));
}

function printSession2(printType)
{
	// Default print type is pdf
	if (typeof printType == 'undefined')
	{
		printType  = TYPE_PDF;
	}
	// setup parameter for the window
	var arg = 'scrollbar=yes, resizable=yes, toolbar=no, menubar=yes, location=no, status=no, directories=no ';
	
	// name
	var name = document.getElementById(OBJ_NAME).value;

	// open the window
	var path = '/' +  getWebAppName() + '/equation/jsp/PrintSessionServlet';
	window.open(path + "?name=" + name + "&printType=" + printType,'',arg);
}

// **********************************************************************************
// function routeToSessionRestore(optionId, sessionId, transactionId, userId)
// Restore a session
//
// Parameters:
// optionId - option id of the session to be restored
// sessionId - session id of the session to be restored
// transactionId - transaction id of the session to be restored
// userId - user id of the session to be restored
//
// Return:
// True - if successful
// **********************************************************************************
function routeToSessionRestore2(optionId, sessionId, transactionId, userId)
{
	routeToSessionRestore(optionId, sessionId, transactionId, userId);
}

function routeToSessionRestore(optionId, sessionId, transactionId, userId)
{
	var currentFrame = getFrameCurrent();

	// Allow only if not currently in a transaction
	if(currentFrame.eqDriver != 'Y')
	{
		errorAlert(20,getLanguageLabel("GBL900018"));
		return false;
	}
	
	// option id must be specified
	if(optionId !='')
	{		
		startLoadingTransaction();
		currentFrame.location="function.jsp?name=&optionId=" + optionId + "&control=1" + "&sessionId=" + sessionId + "&transactionId=" + transactionId + "&userId=" + userId;
		return true;
	}
	else
	{
		errorAlert(20,getLanguageLabel("GBL900019"));
		return false;
	}
}

// **********************************************************************************
// function routeToSupervisor(optionId, sessionId, transactionId, userId, screenSetId, scrnNo)
// Send a session to a supervisor
//
// Parameters:
// optionId      - option id of the session to be restored
// sessionId     - session id of the session to be restored
// transactionId - transaction id of the session to be restored
// userId        - user id of the person requesting authorisation
// screenSetId   - screen set where the message is generated
// scrnNo        - screen number where the message is generated
// **********************************************************************************	
function routeToSupervisor2(optionId, sessionId, transactionId, userId, screenSetId, scrnNo)
{
	routeToSupervisor(optionId, sessionId, transactionId, userId, screenSetId, scrnNo);
}

function routeToSupervisor(optionId, sessionId, transactionId, userId, screenSetId, scrnNo)
{
	var currentFrame = getFrameCurrent();

	// Allow only if not currently in a transaction
	if(currentFrame.eqDriver != 'Y')
	{
		errorAlert(20,getLanguageLabel("GBL900018"));
		return false;
	}
	
	// option id must be specified
	if(optionId !='')
	{		
		startLoadingTransaction();
		currentFrame.location="function.jsp?name=&optionId=" + optionId + "&control=2" + "&sessionId=" + sessionId + "&transactionId=" + transactionId + "&userId=" + userId + "&scrnNo=" + scrnNo + "&screenSetId=" + screenSetId;
		return true;
	}
	else
	{
		errorAlert(20,getLanguageLabel("GBL900019"));
		return false;
	}
}


// **********************************************************************************
// function routeToSupervisor(optionId, sessionId, transactionId, userId, screenSetId, scrnNo)
// Send a session to a supervisor
//
// Parameters:
// optionId      - option id of the session to be restored
// sessionId     - session id of the session to be restored
// transactionId - transaction id of the session to be restored
// userId        - user id of the person requesting authorisation
// status        - authorisation status
// authLevel     - authorisation level
// screenSetId   - screen set where the message is generated
// scrnNo        - screen number where the message is generated
// **********************************************************************************	
function routeToOffOverride2(optionId, sessionId, transactionId, userId, status, authLevel, screenSetId, scrnNo)
{
	routeToOffOverride(optionId, sessionId, transactionId, userId, status, authLevel, screenSetId, scrnNo);
}

function routeToOffOverride(optionId, sessionId, transactionId, userId, status, authLevel, screenSetId, scrnNo)
{
	var currentFrame = getFrameCurrent();

	// Allow only if not currently in a transaction
	if(currentFrame.eqDriver != 'Y')
	{
		errorAlert(20,getLanguageLabel("GBL900018"));
		return false;
	}
	
	// option id must be specified
	if(optionId !='')
	{					
		startLoadingTransaction();
		currentFrame.location="function.jsp?name=&optionId=" + optionId + "&control=3" + "&sessionId=" + sessionId + "&transactionId=" + transactionId + "&userId=" + userId + "&scrnNo=" + scrnNo + "&screenSetId=" + screenSetId + "&status=" + status + "&authLevel=" + authLevel;
		return true;
	}
	else
	{
		errorAlert(20,getLanguageLabel("GBL900019"));
		return false;
	}
}


//**********************************************************************************
// function mainBodyBottomBarProcessingNoWF()
//
// Parameters:
// None
//**********************************************************************************
function mainBodyBottomBarProcessingNoWF()
{
	// Proceed (based on enter)
	var cmdKeyLbl = getLanguageLabel('GBLPRCD');
	var cmdKeyHvr = getLanguageLabel('GBLENT'); 
	var cmdKeyAct = "gblCurrentFrame.validateAndSubmit(\'' + 'CF0' + '\',gblCurrentFrame);";
	var cmdKeyImg = '../images/EQEnter.gif';		
	var cmdKeySubmit = getTextButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
	
	var gblBottomBar = getFrameBottomBar();
	var docID = gblBottomBar.document;
	
	// setup div
	var obj = docID.getElementById('EqBottomBarDiv');
	obj.innerHTML = '<table id="EqBottombarTable" cellpadding="0" cellspacing="0"></table>';

	// setup table
	var obj = docID.getElementById('EqBottombarTable').insertRow(0);
	insertIntoBottomBar(obj,new Array(cmdKeySubmit),false);

	// bottom bar field processing
	gblBottomBar.bottomBarFieldProcessing();
	
}

// **********************************************************************************
// function mainProcessingNoWF()
// Processing for desktop function display
//
// Parameters:
// None
// **********************************************************************************	
function mainProcessingNoWF()
{
	// set the console tab
	if (document.getElementById('errorParameterised') != null)
	{
		var obj = document.getElementById('errorHeader');
		obj.style.display = 'none';
	
		// construct an error of string error messages based on the the struts
		var arrObjErrors = document.getElementsByTagName("li");
		var allItems = new Array();
		var allSecondLevelItems = new Array();
		var i = 0;
		var j = 0;
		var k = 0;
		for (i=0; i<arrObjErrors.length; i++)
		{
			obj = arrObjErrors[i];
			if (obj.id == 'errorParameterised')
			{
				allItems[j] = obj.innerHTML;
				obj.style.visibility = 'hidden';
				j = j + 1;
			}  
			else if (obj.id == 'secondLevelErrorParameterised')
			{
				allSecondLevelItems[k] = obj.innerHTML;
				obj.style.visibility = 'hidden';
				k = k + 1;
			}  
		}
		
		setMsgCtl(allItems, 'errorHeader', allSecondLevelItems);
		setTimeout( 
				function() 
				{
					updateTitleBar(allItems);
				}
				,20);

		//alertNewMsgCtl();
	}
	else
	{
		setMsgCtl(null, null);
	}
	
	// run the onblur
	for (i=0; i<onBlurScriptIds.length; i++)
	{
		onBlurUserExitRoutine(onBlurScriptIds[i]);
	}

	// close the shutters
	for (i = 0; i < closedShutterIds.length; i = i + 2)
	{
		x = i;
		setClosedShutter(closedShutterIds[i + 1], closedShutterIds[i]);
		i = x;
	} 
	
	// sort repeating groups
	for (i = 0; i < repeatingGroupSortByField.length; i++)
	{
		x = i;
		var ascending = document.getElementById(repeatingGroupSortByField[i] + REPGROUP_ORDERSORT).value;
		var obj = document.getElementById(repeatingGroupSortByField[i] + REPGROUP_FLDSORT);
		var fieldId = obj.value;
		obj.value = "";
		
		// sort the column in ascending order
		sortColumn(repeatingGroupSortByField[i], fieldId, LISTTABLELST + repeatingGroupSortByField[i]);

		// to be sorted descending order, then call again to reverse the order
		if (ascending != 'true')
		{
			// .. temporary set to false, so it can sort it in descending order
			sortingInProgress = false;
			sortColumn(repeatingGroupSortByField[i], fieldId, LISTTABLELST + repeatingGroupSortByField[i]);
		}
		
		i = x;
	}
	
	// control grouping by repeating group id
	for (i = 0; i < listRepeatingGroups.length; i++)
	{
		// filter via breakby if specified
		selectElement = document.getElementById(listRepeatingGroups[i] + REPGROUP_SELBREAKBY);
		if (selectElement != null)
		{
			selectedIndex = selectElement.selectedIndex;
			if (selectedIndex >= 0)
			{
				breakByRepeatingData(listRepeatingGroups[i], LISTTABLELST + listRepeatingGroups[i], LISTTABLEFTR + listRepeatingGroups[i]);
				obj = document.getElementById(listRepeatingGroups[i] + REPGROUP_INPBREAKBY);
				obj.value = selectElement.options[selectedIndex].value;
			}
		}
		
		// update row style
		reupdateRepeatingRowStyle(document.getElementById(LISTTABLELST + listRepeatingGroups[i]));
	}

	// set the cursor to the correct field.  Default to the first input field
	setTimeout( 
		function() 
		{
			var cursorObj = objFirstInputField;
			var cursorElementObj = document.getElementById(OBJ_CURSOR);
			if (cursorElementObj != null)
			{
				cursorName = cursorElementObj.value;
				if (cursorName != '') 
				{
					cursorObj = document.getElementById(cursorName);
				}
				if (cursorObj == null) 
				{
					cursorObj = objFirstInputField;
				}
			}
		
			// set position of cursor
			try
			{
				if (cursorObj==null)
				{
					getFrameBottomBar().objBottomFirstInputField.focus();
				}
				else
				{
					objectFocus(cursorObj);
				}
			}
			catch (e)
			{
			}

			// set up EQ driver
			eqDriver = 'N';
			// require supervisor override?
			var fkeyElementObj = document.getElementById(OBJ_FKEY);
			
			if (requiredChecker==2)
			{
				supervisorPasswordForMakerChecker();
			} 
			else if (requiredChecker == 1 && fkeyElementObj.value != KEY_VERI)
			{
				supervisorPassword();
			}
			
			// set the height
			maximumHeight();
			
			// remove completed task from workload list
			var completedTask = document.getElementById(OBJ_COMPLETETASK);
			if (completedTask != null)
			{
				removeCompletedTask(completedTask.value);
			}
		}
		,50);
	
	// clears the comment at the bottom
	lrpInitialisation();

	// disable the fields if on submitted, approved or rejected list
	if (status == 'S' || status == 'P' || (status == 'R' && isResubmitted == 'false'))
	{
		disableAllFields();
	}
}

// **********************************************************************************
// function setupFunctionKeyNoWF()
// Processing to determine the function key to be displayed
//
// Parameters:
// None
// **********************************************************************************	
function setupFunctionKeyNoWF()
{
	var text = document.getElementById(OBJ_FKYTXT).value;
	return text;
}


// **********************************************************************************
// function triggerPrompt(fieldObj)
// Invoke the prompt of the specified field
//
// Parameters:
// fieldObj - the field to invoke the prompt
// **********************************************************************************	
function triggerPrompt(fieldObj)
{
	if (fieldObj==null)
	{
		return;
	}

	// get the anchor tag associated with the object 
	
	// Prompt widget
	var obj = document.getElementById(fieldObj.id + BUT_PROMPT + BUT_HREF);
	
	// List widget
	if (obj==null)
	{
		obj = document.getElementById(fieldObj.id + BUT_WIDGET + BUT_HREF);
		if (obj != null)
		{
			obj.click();
			return;
		}
	}
	
	// Option widget
	if (obj==null)
	{
		obj = document.getElementById(fieldObj.id + BUT_OPTION + BUT_HREF);
		if (obj != null)
		{
			obj.click();
			return;
		}
	}
	
	// Valid option widget
	if (obj==null)
	{
		obj = document.getElementById(fieldObj.id + BUT_VOPTION + BUT_HREF);
		if (obj != null)
		{
			obj.click();
			return;
		}
	}
	
	// Button defined, trigger prompt
	if (obj != null)
	{
		if (obj.hrefclick != null)
		{
			eval(obj.hrefclick);
		}
		else
		{
			eval(obj.getAttribute('hrefclick'));
		}
	}
	
	// Button not defined, then trigger the prompt of the next field
	else
	{
		var nextfield = getAttribute(fieldObj,'nextfield');
		if (nextfield != null && nextfield != '')
		{
			triggerPrompt(document.getElementById(nextfield));
		}
	}
}

// **********************************************************************************
// function supervisorPassword(authLevel)
// Invoke to accept password details from the supervisor
//
// Parameters:
// level - required authority level
// **********************************************************************************	
function supervisorPassword(authLevel)
{
	// setup the parameters
	var parm = '?';
	parm = parm + 'msgIds=' + document.getElementById(OBJ_MSGIDS).value;
	parm = parm + '&msgBrnms=' + document.getElementById(OBJ_MSGBRNM).value;
	parm = parm + '&msgAmts=' + document.getElementById(OBJ_MSGAMT).value;
	parm = parm + '&msgDrCrs=' + document.getElementById(OBJ_MSGDRCR).value;
	parm = parm + '&supervisor=' + document.getElementById(OBJ_DEFSUPER).value;
	parm = parm + '&tranType=' + document.getElementById(OBJ_CRM_S).value;
	parm = parm + '&retry=' + getLanguageLabel("GBL500001");
		
	// open the window
	var arg = 'scrollbar=no, resizable=no, height=150px, width=300px, toolbar=no, menubar=no, location=no, status=no, directories=no, modal=yes';
	windowSupervisor = window.open("/" + getWebAppName() + "/equation/jsp/supervisor.jsp" + parm,"supervisorPassword",arg);
	loadModalProcessing(windowSupervisor);
}

//**********************************************************************************
//function supervisorPasswordForMakerChecker(authLevel)
//Invoke to accept password details from the supervisor
//
//Parameters:
//level - required authority level
//**********************************************************************************	
function supervisorPasswordForMakerChecker(authLevel)
{
	// setup the parameters
	var parm = '?';
	parm = parm + 'msgIds=' + document.getElementById(OBJ_MSGIDS).value;
	parm = parm + '&msgBrnms=' + document.getElementById(OBJ_MSGBRNM).value;
	parm = parm + '&msgAmts=' + document.getElementById(OBJ_MSGAMT).value;
	parm = parm + '&msgDrCrs=' + document.getElementById(OBJ_MSGDRCR).value;
	parm = parm + '&supervisor=' + document.getElementById(OBJ_DEFSUPER).value;
	parm = parm + '&tranType=' + document.getElementById(OBJ_CRM_S).value;
	parm = parm + '&retry=' + getLanguageLabel("GBL500001");
	// open the window
	var arg = 'scrollbar=no, resizable=no, height=150px, width=300px, toolbar=no, menubar=no, location=no, status=no, directories=no, modal=yes';
	windowSupervisor = window.open("/" + getWebAppName() + "/equation/jsp/supervisor4.jsp" + parm,"supervisorPassword",arg);
	loadModalProcessing(windowSupervisor);
}
//**********************************************************************************
// function promptUserPassword(ckey)
// Prompt for user password
//
// Parameters:
// ckey - function key pressed
//**********************************************************************************	
function promptUserPassword(ckey)
{
	// setup the parameters
	var parm = '?';
	parm = parm + 'supervisor=' + currentUser;
	parm = parm + '&ckey=' + ckey;

	// open the window
	var arg = 'scrollbar=no, resizable=no, height=125px, width=175px, toolbar=no, menubar=no, location=no, status=no, directories=no, modal=yes';
	windowSupervisor = window.open("/" + getWebAppName() + "/equation/jsp/supervisor2.jsp" + parm,"promptUserPassword",arg);
	loadModalProcessing(windowSupervisor);
}

//**********************************************************************************
// function promptSaveAs(ckey,tranId)
// Invoke the save as window 
//
// Parameters:
// ckey - the function key
// trainId - the default transaction id
//**********************************************************************************	
function promptSaveAs(ckey, tranId)
{
	// parameter
	var parm = '?ckey=' + ckey;
	parm += '&transactionid=' + tranId;
	
	// open the window
	var arg = 'scrollbar=no, resizable=no, height=175px, width=460px, toolbar=no, menubar=no, location=no, status=no, directories=no, modal=yes';
	windowSupervisor = window.open("/" + getWebAppName() + "/equation/jsp/saveas.jsp" + parm,"saveAsWindow",arg);
	loadModalProcessing(windowSupervisor);
}

//**********************************************************************************
// function promptDecline()
// Invoke the supervisor decline as window 
//
// Parameters:
// ckey - the function key
//**********************************************************************************	
function promptDecline()
{
	// open the window
	var arg = 'scrollbar=no, resizable=no, height=175px, width=460px, toolbar=no, menubar=no, location=no, status=no, directories=no, modal=yes';
	windowSupervisor = window.open("/" + getWebAppName() + "/equation/jsp/supervisor3.jsp","promptDecline",arg);
	loadModalProcessing(windowSupervisor);
}

//**********************************************************************************
//function promptLRPComment()
//Invoke the LRP Comment as window 
//
//Parameters:
//ckey - the function key
//**********************************************************************************	
function promptLRPComment(ckey)
{
	// setup the parameter
	var parm = '?ckey=' + ckey;
		
	// open the window
	var arg = 'scrollbar=no, resizable=no, height=175px, width=460px, toolbar=no, menubar=no, location=no, status=no, directories=no, modal=yes';
	windowSupervisor = window.open("/" + getWebAppName() + "/equation/jsp/processLRPAuth.jsp" + parm,"promptLRPComment",arg);
	loadModalProcessing(windowSupervisor);
}



// **********************************************************************************
// function setTableSize(forceResize)
// Set the size of the table
//
// Parameters:
// forceResize - force resizing
// **********************************************************************************	
function setTableSize(forceResize)
{
	// get the width of the window
	var width = getClientWidthHeight()[0];
	
	// determine margin of main input area to adjust the width so it will have approximately
	// the same margin on the other side
	var mainTd = document.getElementById('mainTD');
	if (!RTL && mainTd.offsetLeft > 5)
	{
		width = width - (mainTd.offsetLeft+5);
	} 
	
	// not IE, then further reduce the size
	if (!isBrowserIE())
	{
		width -= 15;
	}
	var isIE9 = navigator.userAgent.indexOf( 'MSIE 9.0') > -1;
	
	// size has not changed since last updated?
	if (!forceResize && width==previousWidth)
	{
		return;
	}

	// store previous width
	previousWidth = width;
	
	// calculate column size
	columnsize1 = 0;
	columnsize2 = 0;

	// determine the size of the first cell of each relevant table
	var tableList = document.getElementsByTagName('table');
	for (i=0; i<tableList.length; i++)
	{
		var table = tableList[i];
		if (getAttribute(table,'deftable') == 'Y')
		{
			for (j=2; j<table.rows.length; j++)
			{
				// get the first cell
				td = table.rows[j].cells[0];
				if (td.colSpan == 1)
				{
					x = i;
					w = getWidth(td);
					i = x;
					if (w > columnsize1)
					{
						columnsize1 = w;
					}
				}
			}
		}
	}
	
	// adjust size of column1
	columnsize1 += 30; // this value needs to be deducted from above

	// size of column 2
	var columnsize2 = width - columnsize1;
	if (columnsize2 < 50)
	{
		columnsize2 = 200;
	}

	// maximum size
	window_size = columnsize1+columnsize2;

	// set the size of the td - interested with cell with id prefix of TD_PREFIX
	for (i=0; i<tableList.length; i++)
	{
		var table = tableList[i];

		// ensure relevant table has maximum size
		if (table.id.indexOf(TABLE_PREFIX)==0 && table.className.indexOf(NON_RESIZE)==-1)
		{
			table.style.width= isIE9 ? "100%" :window_size + 'px';
		}
		
		// change the size of relevant table
		if (getAttribute(table,'deftable') == 'Y')
		{
			for (j=2; j<table.rows.length; j++)
			{
				// get the first cell
				td = table.rows[j].cells[0];
				if (td.colSpan == 1)
				{
					td.style.width=columnsize1 + 'px';
					
					// get the next cell
					td = td.nextSibling;
					td.style.width=columnsize2 + 'px';
				}
			}
		}
	}

	// interested with div with id prefix of LISTGROUP to adjust the size of the list divvy
	for (i=0; i<listRepeatingGroups.length; i++)
	{
		setRepeatingGroupSize(listRepeatingGroups[i],forceResize);
	}
}


//**********************************************************************************
// function resizeRepeatingGroupColumn(td)
// Ensure the columns are the same for all related repeating groups (data, footer, header)
//
// Parameters:
// td          - the repeating group's table cell element to be checked
// forceResize - force resizing
// tableHdr    - table for the header
// tableList   - table for the data list
// tableFtr    - table for the footer
//**********************************************************************************	
function resizeRepeatingGroupColumn(td1,forceResize,tableHdr, tableList, tableFtr)
{
	// already been set, then ignore
	if (!forceResize && td1.style.width != '')
	{
		return;
	}
	
	// is the parent invisible?
	//        td - tr      - tbody    - table    - div      - repdiv   - td       - tr
	var obj = td1.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode;
	if(getStyle(obj,'visibility') == 'hidden')
	{
		return;
	}
	
	// get the starting index of the root name
	var index = td1.id.indexOf(REPEAT_HDR);
	if (index!=0)
	{
		return;
	}
	
	// repeating group id
	var repeatingGroupId = td1.id.substr(index+REPEAT_HDR.length);
	
	// get the data column
	td2 = REPEAT_LST + repeatingGroupId;
	td2 = document.getElementById(td2);
	
	// get the footer column
	td3 = REPEAT_FTR + repeatingGroupId;
	td3 = document.getElementById(td3);
	
	if (td1 != null && td2 != null)
	{
		w1 = td1.scrollWidth-2;
		w2 = td2.scrollWidth-2;
		w3 = td3.scrollWidth-2;
		
		// style already been set and the difference is just 1 or 2, then do not reset width
		if (td1.style.width != '')
		{
			if (Math.abs(w1-w2) <= 2)
			{
				return;
			}
		}
		
		// choose whichever is higher between the data and the footer
		// note, the header is not included here as it is allowing the header to wrap around, there is an adjustment
		// later on if the header is larger than the data/footer column
		var width = w2;
		if (w3 > w2)
		{
			width = w3;
		}
		
		// add few pixels
		adj = 0;
		if (td1.style.width == '')
		{
			adj = 2;
		}
		width += adj;
		
		// now before changing the column width, also adjust the width of the respective table of the adjustment
		// header
		if (!isBrowserIE7())
		{
			adjustTables(tableHdr, tableList, tableFtr, width, w1, w2, w3);
		}
		
		td1.style.width = (width) + 'px';
		td2.style.width= (width+2) + 'px'; // dont know why +2 is needed!!!
		td3.style.width= (width+2) + 'px';
		
		// this is the adjustment when header is larger than the data/footer column
		w1 = td1.scrollWidth - 2;
		if (w1 > width)
		{
			width = w1 + adj;
			w2 = td2.scrollWidth-2;
			w3 = td3.scrollWidth-2;
			adjustTables(tableHdr, tableList, tableFtr, width, w1, w2, w3);
			td1.style.width = width + 'px';
			td2.style.width = width + 'px';
			td3.style.width = width + 'px';
		}
	}
}


//**********************************************************************************
// function adjustTables(tableHdr, tableList, tableFtr, width, w1, w2, w3)
// Adjust the size of the table
//
// Parameters:
// tableHdr    - table for the header
// tableList   - table for the data list
// tableFtr    - table for the footer
// width       - the new width of one of the column in the table
// w1          - the current width of the column in the header table
// w2          - the current width of the column in the data list table
// w3          - the current width of the column in the footer table
//**********************************************************************************	
function adjustTables(tableHdr, tableList, tableFtr, width, w1, w2, w3)
{
	// this will adjust the size of the table if the column size is increasing

	// column for the header is increasing?
	var offset = width - w1;
	if (offset > 0)
	{
		tableHdr.style.width = (tableHdr.scrollWidth + offset) + 'px';
	}
	
	// column for the data is increasing?
	offset = width - w2;
	if (offset > 0)
	{
		tableList.style.width = (tableList.scrollWidth + offset) + 'px';
	}
	
	// column for the footer is increasing?
	offset = width - w3;
	if (offset > 0)
	{
		tableFtr.style.width = (tableFtr.scrollWidth + offset) + 'px';
	}
}


//**********************************************************************************
// function showLinkedFunctionButton(fieldName, optionId, context, desc)
// Display the linked function button
//
// Parameters:
// fieldName - the field name
// optionId  - the option Id
// context   - the context
// desc      - the description
// legacyOpt - Y if legacy option
//**********************************************************************************	
function showLinkedFunctionButton(fieldName,optionId, context, desc, legacyOpt)
{
	var onClickAction = "showLinkedFunction('" + fieldName + "','" + optionId + "','" + context + "','" + legacyOpt + "','" + escape(desc) + "')";
	var hoverText = optionId + ' ' + desc;
	var labelText = fieldName + BUT_LINKED;
	if (RTL)
	{
		var imagePath = '/' + getWebAppName() + '/equation/images/linkedr.gif';
	}
	else
	{
		var imagePath = '/' + getWebAppName() + '/equation/images/linked.gif';
	}

	// add the widget button
	var widgetButtonHTML = addWidgetButton(hoverText,labelText,onClickAction,imagePath);
	return widgetButtonHTML;
}

//**********************************************************************************
// function showRepeatingLinkedFunctionButton(fieldName, linkedFunctions, repeatingGroup, rowIndex)
// Display the linked function button
//
// Parameters:
// fieldName       - the field name
// linkedFunctions - the list of linked functions (e.g. 1:ASI:AB:AN:AS*2:ANC:CUS:CLC)
// repeatingGroup  - the repeating group id
// rowIndex        - row index
//**********************************************************************************	
function showRepeatingLinkedFunctionButton(fieldName, linkedFunctions, repeatingGroup, rowIndex)
{
	if (linkedFunctions=='')
	{
		linkedFunctions = eval(repeatingGroup + REPGROUP_OPTION1);
	}
	
	var onClickAction = "showRepeatingLinkedFunction('" + fieldName + "','" + linkedFunctions + "','" +repeatingGroup + "','" + rowIndex + "')";
	var labelText = fieldName + BUT_LINKED;
	var imagePath;
	if (RTL)
	{
		imagePath = '/' + getWebAppName() + '/equation/images/linkedr.gif';
	}
	else
	{
		imagePath = '/' + getWebAppName() + '/equation/images/linked.gif';
	}

	// add the widget button
	var widgetButtonHTML = addWidgetButton('',labelText,onClickAction,imagePath);
	return widgetButtonHTML;
}

//**********************************************************************************
// function showDrillDownButton(repeatingGroup)
// Display the linked function button
//
// Parameters:
// repeatingGroup  - the repeating group id
//**********************************************************************************	
function showDrillDownButton(repeatingGroup)
{
	var onClickAction = "showDrillDownFunction('" + repeatingGroup + "')";
	var labelText = repeatingGroup + BUT_LINKED;
	var imagePath;
	if (RTL)
	{
		imagePath = '/' + getWebAppName() + '/equation/images/drilldownr.gif';
	}
	else
	{
		imagePath = '/' + getWebAppName() + '/equation/images/drilldown.gif';
	}

	// add the widget button
	var widgetButtonHTML = addWidgetButton('',labelText,onClickAction,imagePath);
	return widgetButtonHTML;
}

//**********************************************************************************
// function showLinkedFunction(fieldName, optionId, context)
// Called when the play button is pressed
//
// Parameters:
// fieldName - the field this linked function is attached to
// optionId - the option Id
// context  - the context
// legacyOpt - Y if legacy option
// desc      - the description
//**********************************************************************************	
function showLinkedFunction(fieldName, optionId, context, legacyOpt, desc)
{
	// modal window open?
	if (isModalWindowOpenAlert(true))
	{
		return;
	}
	
	// update the relevant field values
	updateFunctionDataField(rtvFieldDetails(), 
			function()
			{
				var ctxt = retrieveFieldValueFromContextFields(context,'');
				if (isUXP())
				{
					window.top.equxp.routeToOption2('EQ', optionId, optionId + " : " + desc, ctxt);
				}
				else
				{
					openChildWindow('*D', unitId, optionId, ctxt, "PLAY", '');
				}
			}
	);
	
	// focus back to the field
	objectFocus(document.getElementById(fieldName));
}

//**********************************************************************************
// function showRepeatingLinkedFunction(fieldName, linkedFunctions, repeatingGroup, rowIndex)
// Called when the play button is pressed for a repeating group
//
// Parameters:
// fieldName       - the field name
// linkedFunctions - the list of linked functions (e.g. 1:ASI:AB:AN:AS*2:ANC:CUS:CLC)
// repeatingGroup  - the repeating group id
// rowIndex        - row index (e.g. $00001)
//**********************************************************************************	
function showRepeatingLinkedFunction(fieldName, linkedFunctions, repeatingGroup, rowIndex)
{
	// modal window open?
	if (isModalWindowOpenAlert(true))
	{
		return;
	}

	// focus back to the field
	objectFocus(document.getElementById(fieldName));
	
	// get the selection
	var selectionOption = document.getElementById(fieldName).value;
	if (selectionOption.trim().length==0)
	{
		return;
	}

	// check if valid selection
	var listOptions = linkedFunctions.split(LINKEDOPTION_DELIMETER);
	for (i=0; i<listOptions.length; i++)
	{
		var str = listOptions[i];
		var index = str.indexOf(selectionOption + CONTEXT_DELIMETER);
		if (index==0)
		{
			// update the relevant field values
			updateFunctionDataField(rtvFieldDetails(), 
				function()
				{
					// remove the selection option
					str = str.substr(str.indexOf(CONTEXT_DELIMETER)+1);
							
					// get the option
					option = str.substr(0,str.indexOf(CONTEXT_DELIMETER));
					context = str.substr(str.indexOf(CONTEXT_DELIMETER)+1);
								
					// drill-down to support both WebFacing and Equation services
					setRepeatingRowDoubleClickAction(repeatingGroup, parseInt(rowIndex.substr(1),10) - 1, selectionOption);
				}
			);
			
			return;
		}
	}

	// invalid selection
	errorAlert(20, getLanguageLabel('GBL900051'));
}

// **********************************************************************************
// function showDrillDownFunction(this,repeatingGroup)
// Drill down processing
//
// Parameters:
// repeatingGroup  - the repeating group id
// **********************************************************************************	
function showDrillDownFunction(repeatingGroup)
{
	document.getElementById(repeatingGroup + BUT_LINKED).style.cursor = 'wait';
	validateAndSubmitDrillDown(repeatingGroup);
}

//**********************************************************************************
// function updateFunctionDataField(fieldValues,callbackProc)
// Update the function data
//
// Parameters:
// fieldValues - the list of fields
// callbackProc - the callback procedure
//**********************************************************************************	
function updateFunctionDataField(fieldValues, callbackProc)
{
	// get the session id
	var sessionIdentifier = getSessionId();
	var name = document.getElementById(OBJ_NAME).value;

	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName('updateFunctionData',nsuri);
	var qn_op_resp = new WS.QName('updateFunctionDataResponse',nsuri);
	
	// Set the parameters required by the service
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'name',value:name},
								{name:'fieldValues',value:fieldValues}
							];
		
	// Call the web service
	call.invoke_rpc(
						qn_op,
						getSessionParms,
						null,
						function(call,envelope) 
						{
							if (callbackProc != null)
							{
								callbackProc();
							}
						}
					);
}


//**********************************************************************************
// function retrieveFieldDetails()
// Retrieve current screen details
//
// Parameters:
// None
//
// Returns:
// the field details
//**********************************************************************************	
function rtvFieldDetails()
{
	var list = getFrameCurrent().document.getElementsByTagName('input');
	var fieldValues = '';

	for (i=0; i<list.length; i++)
	{
		var obj = list[i];
		if (obj.type == 'text')
		{
			fieldValues += obj.id + GLOBAL_EQUALDELIMETER + obj.value + GLOBAL_DELIMETER; 
		}
		
		else if (obj.type == 'radio' || obj.type == 'checkbox')
		{
			// YNI?
			var idx = obj.id.lastIndexOf('YYNI');
			
			// S1B
			if (idx <= 0)
			{
				idx = obj.id.lastIndexOf('S1B');
			}

			if (idx >= 1)
			{
				obj = document.getElementById(obj.id.substr(0,idx));
				fieldValues += obj.id + GLOBAL_EQUALDELIMETER + obj.value + GLOBAL_DELIMETER; 
			}
			
		}
	}

	// return the field values
	return fieldValues;
}


//**********************************************************************************
// function getFunctionKeyText(ckey)
// Retrieve the key text of a function key
//
// Parameters:
// ckey - the function key (e.g. F12)
//
// Returns:
// The text associated with the function key
//**********************************************************************************	
function getFunctionKeyTextLabel(ckey)
{
	var obj = document.getElementById(OBJ_FFKYTXT);
	var fkeys = obj.value;
	
	var idx = fkeys.indexOf(ckey);
	if (idx >= 0)
	{
		var equal = fkeys.indexOf('=',idx);
		var equalNext = fkeys.indexOf('=',equal+1);
		
		// no more next equal sign, then this is the last function key
		if (equalNext==-1)
		{
			return fkeys.substr(equal+1);
		}
		
		// move backward
		else
		{
			var startNextFkey = fkeys.lastIndexOf(' ',equalNext);
			return fkeys.substr(equal+1, startNextFkey-(equal+1)); 
		}
	}
	else
	{
		return '';
	}
}

//**********************************************************************************
// function mainBodyFieldProcessingNoWF()
// main body field processing for non WebFAcing
//**********************************************************************************	
function mainBodyFieldProcessingNoWF()
{
	value = document.getElementById(OBJ_FIRSTFIELD).value;
	if (value.trim() != '')
	{
		objFirstInputField = document.getElementById(value);
		objFirstInputField = rtvDisplayFieldFromInputField(objFirstInputField);
		addMyEventListener(objFirstInputField, 'keydown', firstInputFieldKeyDown);
	}

	value = document.getElementById(OBJ_LASTFIELD).value;
	if (value.trim() != '')
	{
		objLastInputField = document.getElementById(value);
		objLastInputField = rtvDisplayFieldFromInputField(objLastInputField,'*last');
		addMyEventListener(objLastInputField, 'keydown', lastInputFieldKeyDown);
	}
}

//**********************************************************************************
// function triggerChildWindow(fieldObj)
// Invoke the child window of the specified field
//
// Parameters:
// fieldObj - the field to invoke the child window 
//**********************************************************************************	
function triggerChildWindow(fieldObj)
{
	if (fieldObj==null)
	{
		return;
	}

	var obj = document.getElementById(fieldObj.id + BUT_LINKED + "Href");
	
	// Button defined, trigger
	if (obj != null)
	{
		eval(obj.href);
	}
}

//**********************************************************************************
// function retrieveFieldValueFromContextFields(context)
// Return the field values given the context fields (e.g. return 0000:000001:001 given AB:AN:AS)
//
// Parameters:
// context  - the context fields
// rowIndex - the row index
//
// Returns:
// the field values
//**********************************************************************************	
function retrieveFieldValueFromContextFields(context, rowIndex)
{
	// generate the list of values for the context
	var listFields = context.split(CONTEXT_DELIMETER);
	var ctxt = '';
	
	// blank list of fields
	if (context.trim().length == 0)
	{
		return ctxt;
	}
	
	// empty string
	if (context.trim() == "''")
	{
		return ctxt;
	}
	
	for (i=0; i<listFields.length; i++)
	{
		// is this a field or a constant
		var obj = document.getElementById(listFields[i]);
		
		if (obj == null && rowIndex != '')
		{
			obj = document.getElementById(listFields[i] + rowIndex);
		}
		
		if (obj != null)
		{
			ctxt += LITERAL_STR_DELIMETER + obj.value.replace(/'/g,LITERAL_ESC_DELIMETER) + LITERAL_STR_DELIMETER 
					+ CONTEXT_DELIMETER;
		}
		else
		{
			// if the string is enclosed by LITERAL_STR_DELIMETER (e.g. ') , then strip it off
			obj = listFields[i];
			var n1 = 0;
			if (obj.length>1 && obj.charAt(0) == '\'')
			{
				n1 = 1;
			}
			var n2 = obj.length;
			if (n1==1 && obj.length>1 && obj.charAt(obj.length-1) == '\'')
			{
				n2 = obj.length-1;
			}
			else
			{
				n1 = 0;
			}
			obj = obj.substring(n1,n2);
			ctxt += LITERAL_STR_DELIMETER + obj.replace(/'/g,LITERAL_ESC_DELIMETER) + LITERAL_STR_DELIMETER 
					+ CONTEXT_DELIMETER;
		}
	}
	
	return ctxt;
}

//**********************************************************************************
// function refreshEnquiry()
// Redisplay the screen
//**********************************************************************************	
function refreshEnquiry()
{
	var obj = document.getElementById(OBJ_FKEY);
	obj.value = KEY_VERI;
	
	// submit page
	submitForm();
}


//**********************************************************************************
// function maximumHeight()
// Change the height of the frame to occupy based on the browser height
//
// Parameters:
// None
//**********************************************************************************	
function maximumHeight()
{
	// pointer to frame
	if (gblCurrentFrame == null)
	{
		gblCurrentFrame = getFrameCurrent();
	}
	if (gblBottomBarFrame == null)
	{
		gblBottomBarFrame = getFrameBottomBar();
	}
	
	// adjust size of the mainTD to the actual size of browser
	var height = gblBottomBarFrame.screenTop - gblCurrentFrame.screenTop;
	if (document.body.clientHeight < height)
	{
		document.getElementById('mainTD').style.height = (height-50) + 'px'; 
	}
}

//**********************************************************************************
// function disableAllElements()
// Disable all elements
//
// Parameters:
// None
//**********************************************************************************	
function disableAllElements()
{
	// disable all input fields
	var el = document.getElementsByTagName('input');
	for(i=15;i<el.length;i++)
	{
		obj = el[i];
		if (obj.type == "text")
		{
			obj.setAttribute('disabled',true);
		}
		if (i>=200)
		{
			break;
		}
	}
	
	// disable all anchor tag
	var el = document.getElementsByTagName('a');
	for(i=0;i<el.length;i++)
	{
		obj = el[i];
		disableAnchorInternal(obj, true);
		obj.style.cursor = 'wait';
	}

	// control grouping by repeating group id
	for (i=0; i<listRepeatingGroups.length; i++)
	{
		// change the cell of the header
		table = document.getElementById(LISTTABLEHDR + listRepeatingGroups[i]);
		if (table != null)
		{
			row = table.rows[0];
			for (j=0; j< row.cells.length; j++)
			{
				row.cells[j].style.cursor = 'wait';
			}
		}
		
		// change the table's data
		table = document.getElementById(LISTTABLELST + listRepeatingGroups[i]);
		for (j=0; j< table.rows.length; j++)
		{
			table.rows[j].style.cursor = 'wait';
		}
	}
}


//**********************************************************************************
//function showButtonLinkButton(label, description, labelPosition, displayAs
// 				urlOrCommandFlag, commandParameters, protectedStatus, labelStyle)
//Show a button or a link or an image depending on the value of displayAs
//
//Parameters:
//label 			- the label of the button or link or image
//description 		- displayed as tool tip
//labelPosition 	- for images, this determines where the label is placed
//displayAs 		- determines if element is displayed as button or link or image
//urlOrCommonFlag 	- determines the type of action 
//commandParameter 	- the URL or the command
//protectedStatus 	- determines if the element is protected (not enabled)
//labelStyle 		- style of the button or link or image
//
//**********************************************************************************	
function showButtonLinkButton(label, description, labelPosition, displayAs, 
		urlOrCommandFlag, commandParameters, protectedStatus, labelStyle)
{
	var onClickAction = "showButtonLink(" + urlOrCommandFlag + ",'" + commandParameters + "')";
	
	// If show as image
	if (displayAs == 3) 
	{
		// Get the correct icon to show
		var imagePath;
		var imagePathOff;
		var buttonLinkHtml = '';
		
		if (urlOrCommandFlag == 1) 
		{
			imagePath = '/' + getWebAppName() + '/equation/images/showPage.gif';
			imagePathOff = '/' + getWebAppName() + '/equation/images/showPage_off.gif';
		}
		else
		{
			imagePath = '/' + getWebAppName() + '/equation/images/showCommand.gif';
			imagePathOff = '/' + getWebAppName() + '/equation/images/showCommand_off.gif';
		}
		
		// If label position is left
		if (labelPosition == 3) 
		{
			buttonLinkHtml = getSpanHTML(label, labelStyle, description);
		}
		
		if (protectedStatus) 
		{
			buttonLinkHtml += getImageHTML(description, label, imagePathOff);
		} 
		else 
		{
			defaultButtonProc = false;
			buttonLinkHtml += addWidgetButton(description, label, onClickAction, imagePath);
			defaultButtonProc=true;
		}
		
		// If label position is right
		if (labelPosition == 4) 
		{
			buttonLinkHtml += getSpanHTML(label, labelStyle, description);
		}
	}
	
	// If show as button
	else if (displayAs == 1) 
	{
		if (protectedStatus) 
		{
			buttonLinkHtml = getTextDisabledButtonHTML(description, label, onClickAction, '', labelStyle);			
		}
		else 
		{
			buttonLinkHtml = getTextButtonHTML(description, label, onClickAction, '', labelStyle);
		}
	}
	
	// If show as hyperlink
	else if (displayAs == 2) 
	{
		if (protectedStatus) 
		{
			buttonLinkHtml = getSpanHTML(label, labelStyle, description);
		} 
		else 
		{
			buttonLinkHtml = getAnchorHTML(label, description, onClickAction, labelStyle);
		}
	}
	
	// Print the button or link or image
	document.write(buttonLinkHtml);	
}

//**********************************************************************************
//function showButtonLink(urlOrCommandFlag, commandParameter)
//Determines the action once the button or image or link is clicked, this either
//opens the URL on a new window or execute a shell command
//
//Parameters:
//urlOrCommandFlag	- URL or command flag
//commandParameter	- the URL or the command and parameters
//
//**********************************************************************************	
function showButtonLink(urlOrCommandFlag, commandParameter) {
	
	// If this is a URL
	if (urlOrCommandFlag == 1) 
	{
		var arg = 'scrollbar=yes, resizable=yes, toolbar=no, menubar=yes, location=yes, status=yes, directories=no'
		window.open(commandParameter, "buttonLinkChildWindow", arg);
	}
	// If this is a Command
	else if (urlOrCommandFlag == 2) 
	{
		var command = '';
		var parameter = '';
		
		if (commandParameter.startsWith("\"")) 
		{
			var i = commandParameter.indexOf("\" ");
			if (i >= 0) 
			{
				command = commandParameter.substring(1,i);
				parameter = commandParameter.substring(i+2);
			}
		}
		else
		{
			var i = commandParameter.indexOf(" ");
			if (i >= 0) 
			{
				command = commandParameter.substring(0,i);
				parameter = commandParameter.substring(i+1);
			}
		}
		
		if (command == '') 
		{
			command = commandParameter;
		}
		
		executeShell(command, parameter);
	}
}