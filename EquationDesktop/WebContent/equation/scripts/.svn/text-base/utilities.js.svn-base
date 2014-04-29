var windowSupervisor = null;
var popupAsWindow = null;
var jSessionId = null;


function select_Value_Set(SelectName, Value) 
{
	selectBox = document.getElementById(SelectName);
	for(index = 0; index < selectBox.length; index++) 
	{
		if(selectBox[index].value == Value)
		{
		   selectBox.selectedIndex = index;
		}
	}	   
}

// **********************************************************************************
// function pad(str,length,padchar)
// Pad a string with the specified character until the specified length
//
// Parameters:
// str     - string 
// length  - required length
// padchar - pad character
// **********************************************************************************	
function pad(str,length,padchar)
{
	// convert it to a string
	str = str + ' ';
	str = str.substr(0,str.length-1);
	
	var len    = str.length;
	var n      = length - len;
	var padstr = str;
	
	for(i=0; i<n; i++)
	{
		padstr += padchar;
	}
	
	return padstr;
}

// **********************************************************************************
// function padl(str,length,padchar)
// Pad a string to the left with the specified character until the specified length
//
// Parameters:
// str     - string 
// length  - required length
// padchar - pad character
// **********************************************************************************	
function padl(str,length,padchar)
{
	// convert it to a string
	str = str + ' ';
	str = str.substr(0,str.length-1);
	
	var len    = str.length;
	var n      = length - len;
	var padstr = str;
	
	for(i=0; i<n; i++)
	{
		padstr = padchar + padstr;
	}
	
	return padstr;
}

// **********************************************************************************
// function triml(str)
// Remove leading characters on the left of the string
//
// Parameters:
// str      - string 
// charlead - leading character to remove 
// **********************************************************************************	
function triml(str,charlead)
{
	for (i=0; i<str.length; i++)
	{
		if (str.charAt(i) != charlead)
		{
			return str.substring(i,str.length);
		}
	}
	
	// if it reaches this code, either the str is blank, or only contains the character to remove
	// in this case, just return the original str
	return str; 
}


// **********************************************************************************
// function logoffDesktop()
// Processing to perform logoff processing 
//
// Parameters:
// childName - the child id
// **********************************************************************************	
function logoffDesktop(childName)
{
	// get the session id
	var sessionIdentifier = getSessionId();

	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName('logoffDesktop',nsuri);
	var qn_op_resp = new WS.QName('logoffDesktopResponse',nsuri);

	// Current frame
	gblFrameDesktop = getFrameDesktop();
	if (gblFrameDesktop == null)
	{
		name = "";
	}
	else
	{
		try
		{
			name = gblFrameDesktop.document.getElementById(OBJ_NAME).value;
			
			// For popup, logoff the child window using the parent frame
			// There is issue with freezing IE8 when the window is closed and
			// AJAX call has not been completed
			if (name.trim().length != 0)
			{
				try
				{
					var gblFrameDesktop2 = window.top.opener.getFrameDesktop();
					gblFrameDesktop2.logoffDesktop(name);
				} 
				catch (e2)
				{
				}
				return;
			}
			
			// name specified?
			if (childName != null)
			{
				name = childName;
			}
		}
		catch (e)
		{
			name = '';
		}
	}

	// Set the parameters required by the service
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'name',value:name}
							];

	// Call the web service
	call.invoke_rpc(
						qn_op,
						getSessionParms,
						null,
						function(call,envelope) 
						{
						}
					);
}


//**********************************************************************************
// function logoffDesktopDuringLogin()
// Processing to perform logoff processing during the login process
//
// Parameters:
// None
//**********************************************************************************	
function logoffDesktopDuringLogin()
{
	// get the session id
	var sessionIdentifier = getSessionId();

	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName('logoffDesktop',nsuri);
	var qn_op_resp = new WS.QName('logoffDesktopResponse',nsuri);
	var name = '';
	
	// Set the parameters required by the service
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'name',value:name}
							];

	// Call the web service
	call.invoke_rpc(
						qn_op,
						getSessionParms,
						null,
						function(call,envelope) 
						{
						}
					);
}


// **********************************************************************************
// function getSessionId()
// Get the session identifier 
//
// Parameters:
// None
//
// Returns:
// the session identifier
// **********************************************************************************	
function getSessionId()
{
	// session id already known
	if (jSessionId != null)
	{
		return jSessionId;
	}

	// retrieve session id from UXP
	if (isUXP())
	{
		var winTop = window.opener != null ? window.opener.top : window.top;
		if( winTop.opener != null )
		{
			winTop = winTop.opener.top;
		}
		if( winTop != null && winTop.getUserToken )
		{
			sessionIdentifier = winTop.getUserToken();
			if (sessionIdentifier != null && sessionIdentifier.trim().length != 0)
			{
				return sessionIdentifier;
			}
		}
	}
	
	// retrieve the session id from the cookie
	var sessionIdentifier = getCookie('JSESSIONID');
	var i = sessionIdentifier.indexOf(':');
	if (i > 0)
	{
		sessionIdentifier = sessionIdentifier.substring(4,i);
	}
	jSessionId = sessionIdentifier;
	return sessionIdentifier;
}


// **********************************************************************************
// function checkFrameInError(alert)
// Reload default pages if frame in error 
//
// Parameters:
// alert - true if to show alert
//
// Returns:
// true if either frame in error
// **********************************************************************************	
function checkFrameInError(alert)
{
	// WebFacing frame
	if(getFrameWebfacing().eqDriver == null)
	{
		if (alert)
		{
			displayMessage(getLanguageLabel("GBL900038"));
		}
		getFrameScreen().document.getElementById('webfacingFrame').src = "../jsp/blank.jsp";
		getFrameScreen().document.getElementById('desktopFrame').src = "../jsp/welcome.jsp";
		return true;
	}
	
	// Desktop frame
	else if (getFrameDesktop().eqDriver == null)
	{
		if (alert)
		{
			displayMessage(getLanguageLabel("GBL900038"));
		}
		getFrameScreen().document.getElementById('desktopFrame').src = "../jsp/welcome.jsp";
		return true;
	}
	
	return false;
}


//**********************************************************************************
// function logoffWebFacing()
// Log-off webfacing session 
//
// Parameters:
// None
//**********************************************************************************	
function logoffWebfacing()
{
	// check if WebFacing driver is active
	var webfacingFrame = getFrameWebfacing();
	if (webfacingFrame.eqDriver == 'Y')
	{		
		webfacingFrame.document.getElementById('l1_W97HMDA$ZLEMOD').value="*S";								
		webfacingFrame.validateAndSubmit('ENTER',webfacingFrame);
	}
}


// **********************************************************************************
// function logoff_Processing()
// Log-off processing 
//
// Parameters:
// None
// **********************************************************************************	
function logoff_Processing()
{
	// desktop
	logoffDesktop();

	// webfacing
	logoffWebfacing();
}

// **********************************************************************************
// function disableObj(objId, disable)
// Enable/disable an element
//
// Parameters:
// objId   - object id
// disable - true/false to enable/disable it
// **********************************************************************************	
function disableObj(objId, disable)
{
	var obj = document.getElementById(objId);
	if (obj != null)
	{
		obj.disabled = disable;
	} 
}

//**********************************************************************************
//function disableAllObj(objId, disable)
//Enable/disable all objects in a Div/Table
//
//Parameters:
//objId   - object id
//disable - true/false to enable/disable it
//**********************************************************************************	
function disableAllObj(objId, disable) 
{
	toggleDisabled(document.getElementById(objId), disable);
}
function toggleDisabled(el, disable)
{	
	try
	{
		el.disabled = disable;
		disableAnchorInternal(el,disable);
		
	}
	catch (e)
	{	
	}
	if (el.childNodes && el.childNodes.length > 0) 
	{
		for (var x = 0; x < el.childNodes.length; x++) 
		{
			toggleDisabled(el.childNodes[x], disable);
		}
	}
}

// **********************************************************************************
// function disableMultipleObj(objId, element, disable)
// Enable/disable multiple element with the same id
//
// Parameters:
// objId   - object id
// element - the html element (e.g. input, img, etc) 
// disable - true/false to enable/disable it
// **********************************************************************************	
function disableMultipleObj(objId, element,disable)
{
	var objList = document.getElementsByTagName(element);
	
	for (i=0; i<objList.length; i++)
	{
		var obj = objList[i];
		if (obj.id == objId)
		{
			obj.disabled = disable;
		}
	}
}


// **********************************************************************************
// function disableAnchorInternal(objId, disable)
// Enable/disable an anchor
//
// Parameters:
// obj     - object 
// disable - true/false to enable/disable it
// **********************************************************************************	
function disableAnchorInternal(obj,disable)
{
	if (disable)
	{
		var href = obj.getAttribute("href");
		if(href != null && href != '')
		{
			obj.setAttribute('hrefbak',href);
			obj.removeAttribute('href');
		}
	}
	else
	{
		var href = obj.getAttribute("hrefbak");
		if (href != null)
		{
			obj.setAttribute('href',href);
		}
	} 
}

// **********************************************************************************
// function disableAnchor(objId, disable)
// Enable/disable an anchor
//
// Parameters:
// objId   - object id
// disable - true/false to enable/disable it
// **********************************************************************************	
function disableAnchor(objId, disable)
{
	var obj = document.getElementById(objId);
	
	if (obj != null)
	{
		visibleObjInternal(obj,!disable);
		disableAnchorInternal(obj,disable);
	}
}


// **********************************************************************************
// function disableMultipleAnchor(objId, disable)
// Enable/disable anchors with the same id
//
// Parameters:
// objId   - object id
// disable - true/false to enable/disable it
// **********************************************************************************	
function disableMultipleAnchor(objId, disable)
{
	var objList = document.getElementsByTagName('a');
	
	for (i=0; i<objList.length; i++)
	{
		var obj = objList[i];
		if (obj.id == objId)
		{
			disableAnchorInternal(obj,disable);
		}
	}
}


// **********************************************************************************
// function visibleObjInternal(obj,visible)
// Show/hide an element
//
// Parameters:
// obj     - object id
// visible - true/false to show/hide it
// **********************************************************************************	
function visibleObjInternal(obj,visible)
{
	if (visible==true)
	{
		removeClassName(obj, NON_DISPLAY_CLASS1);
		removeClassName(obj, NON_DISPLAY_CLASS2);
	}
	else
	{
		addClassName(obj, NON_DISPLAY_CLASS1);
		addClassName(obj, NON_DISPLAY_CLASS2);
	}
}


// **********************************************************************************
// function visibleObj(objId,visible)
// Show/hide an element
//
// Parameters:
// objId   - object id
// visible - true/false to show/hide it
// **********************************************************************************	
function visibleObj(objId,visible)
{
	var obj = document.getElementById(objId);
	
	if (obj != null)
	{
		visibleObjInternal(obj,visible);
	}
}


// **********************************************************************************
// function visibleMultipleObj(objId,visible)
// Show/hide elements with the same id
//
// Parameters:
// objId   - object id
// element - the html element (e.g. input, img, etc) 
// visible - true/false to show/hide it
// **********************************************************************************	
function visibleMultipleObj(objId,element,visible)
{
	var objList = document.getElementsByTagName(element);
	
	for (i=0; i<objList.length; i++)
	{
		var obj = objList[i];
		if (obj.id == objId)
		{
			visibleObjInternal(obj,visible);
		}
	}
}

// **********************************************************************************
// function addClassName(obj,name)
// Add the class name to the field's class
//
// Parameters:
// obj  - object
// name - class name to be added
// 
// Returns:
// true - if class name has been added
// **********************************************************************************	
function addClassName(obj,name)
{
	if (obj.className.indexOf(name) == -1)
	{
		obj.className = obj.className.trim() + ' ' + name;
		return true;
	}
	else
	{
		return false;
	}
}

// **********************************************************************************
// function removeClassName(obj,name)
// Remove the class name to the field's class
//
// Parameters:
// obj  - object
// name - class name to be remove
// 
// Returns:
// true - if class name has been removed
// **********************************************************************************	
function removeClassName(obj,name)
{
	if (obj.className.indexOf(name) >= 0)
	{
		obj.className = obj.className.replace(name,'').trim();
		return true;
	}
	else
	{
		return false;
	}
}


// **********************************************************************************
// function cvtArrToString(arrayStr,separator)
// Convert an array into String representation
//
// Parameters:
// arrayStr  - list of string
// separator - delimeter that separates one element from another 
// 
// Returns:
// the list of javascript compatible string
// **********************************************************************************	
function cvtArrToString(arrayStr,separator)
{
	var str = '';
	for(i=0; i<arrayStr.length; i++)
	{
		str += arrayStr[i];
		
		if (i+1 < arrayStr.length)
		{
			str += separator;
		}
	}
	return str;
}


// **********************************************************************************
// function objectFocus(fieldId)
// Set the focus on a field
//
// Parameters:
// fieldObj - field object
// positionToFirst - false if dont position to first input field
// **********************************************************************************	
function objectFocus(fieldObj,positionToFirst)
{
	if (fieldObj == null)
	{
		return false;
	}

	try
	{
		cursorObj2 = rtvDisplayFieldFromInputField(fieldObj);
		cursorObj2.focus();
		cursorObj2.focus();
		if(cursorObj2.type == 'text')
		{
			fieldObj.select();
			fieldObj.focus();
		}
	}
	catch(e)
	{
		if (positionToFirst != false)
		{
			objectFocus(objFirstInputField,false);
		}
		else
		{
			getFrameBottomBar().objBottomFirstInputField.focus();
		}
		
		return false;
	}
	return true;
}


// **********************************************************************************
// function errorAlertInPopup(sev,msg)
// Display alert for popups
//
// Parameters:
// sev - severity
// msg - message
// **********************************************************************************	
function errorAlertInPopup(sev,msg)
{
	if (asPopupWindow)
	{
		window.opener.errorAlert(sev,msg);
	}
	else
	{
		errorAlert(sev,msg);
	}
}


// **********************************************************************************
// function isAnyModalWindowOpen()
// Determine whether any modal window is open
// **********************************************************************************	
function isAnyModalWindowOpen()
{
	// child window open?
	if (isChildWindowOpen())
	{
		return true;
	}
	
	// pv window open?
	if (popupAsWindow != null)
	{
		if (popupAsWindow.closed)
		{
			popupAsWindow = null;
		}
		else
		{
			return true;
		}
	}
	
	// supervisor window open?
	if (windowSupervisor != null)
	{
		if (windowSupervisor.closed)
		{
			windowSupervisor = null;
		}
		else
		{
			return true;
		}
	}
	
	return false;
}


// **********************************************************************************
// function focusOnModalWindow()
// Set the focus on the modal window
// **********************************************************************************	
function focusOnModalWindow()
{
	// focus on child
	if (isChildWindowOpen())
	{
		focusOnChildWindow();
		return;
	}

	// focus on pv window
	if (popupAsWindow != null)
	{
		if (popupAsWindow.closed)
		{
			popupAsWindow = null;
		}
		else
		{
			try
			{
				popupAsWindow.focus();
			}
			catch (e)
			{
				popupAsWindow = null;
			}
		}
		return;
	}

	// focus on supervisor
	if (windowSupervisor != null)
	{
		windowSupervisor.focus();
		return;
	}
}

// **********************************************************************************
// function isModalWindowOpenAlert(popup)
// Determine whether any modal window is open and alert the user
//
// Parameters:
// popup - popup window?
// **********************************************************************************	
function isModalWindowOpenAlert(popup)
{
	// modal window open?
	if (isAnyModalWindowOpen())
	{
		if (popup)
		{
			errorAlertInPopup(20,getLanguageLabel('GBL900046'));
		}
		else
		{
			errorAlert(20,getLanguageLabel('GBL900046'));
		}
		return true;
	}
	return false;
}


// **********************************************************************************
// function clearSupervisorWindow()
// Determine whether the supervisor window is open
// **********************************************************************************	
function clearSupervisorWindow()
{
	windowSupervisor = null;
}

// **********************************************************************************
// function getCurrentField()
// Determine the current field
//
// Parameters:
// None
//
// Returns:
// the current field
// **********************************************************************************	
function getCurrentField()
{
	if(objCurrentInputField==null) 
	{
		return objLastFocusInputField;
	}
	return objCurrentInputField;
}


// **********************************************************************************
// function isHiddenField(fieldObj)
// Determine if a field is hidden 
//
// Parameters:
// fieldObj - the field object
//
// Returns:
// true if object is hidden
// **********************************************************************************	
function isHiddenField(fieldObj)
{
	if (fieldObj == null)
	{
		return true;
	}

	curobjObj2 = rtvDisplayFieldFromInputField(fieldObj,'*field');
	if (curobjObj2.className.indexOf(NON_DISPLAY_CLASS1) == -1)
	{
		return false;
	}
	else
	{
		return true;
	}
		
}


//**********************************************************************************
//function rtvFunctionHandlerName()
//Return the function handler name
//
//Parameters:
//None
//**********************************************************************************	
function rtvFunctionHandlerName()
{
	var name;
	var doc = window.opener != null ? window.opener.document : document;
	if( doc.getElementById(OBJ_NAME) )
	{
		name= doc.getElementById(OBJ_NAME).value;
	}
	return name;
}


//**********************************************************************************
// function rtvDisplayFieldFromInputField(inputFieldObj)
// Return the displaying object given the input field (e.g. checkbox object of an Y/N field
//
// Parameters:
// inputFieldObj - the input field object
// counter - if specified, then the nth element if the input field is displayed as multi-field
//**********************************************************************************	
function rtvDisplayFieldFromInputField(inputFieldObj,counter)
{
	// is it a valid value?
	if (inputFieldObj.className.indexOf('wf_VALIDVALUE')>=0)
	{
		obj = gblCurrentFrame.document.getElementById(inputFieldObj.id + 'ValidValueSelect');
	}
		
	// yes/no field
	else if (inputFieldObj.className.indexOf('wf_YNO')>=0)
	{
		if (counter=='*last')
		{
			obj = gblCurrentFrame.document.getElementById(inputFieldObj.id + 'NYNI');
		}
		if (counter=='*field')
		{
			obj = gblCurrentFrame.document.getElementById(inputFieldObj.id + 'YNIDiv');
		}
		else
		{
			obj = gblCurrentFrame.document.getElementById(inputFieldObj.id + 'YYNI');
		}
	}
	
	// S1B
	else if (inputFieldObj.className.indexOf('wf_S1B')>=0)
	{
		obj = gblCurrentFrame.document.getElementById(inputFieldObj.id + 'S1B');
	}
	
	// just return the same object
	else
	{
		return inputFieldObj;
	}
	
	// no object, then simply return the same object
	if (obj == null)
	{
		return inputFieldObj;
	}
	else
	{
		return obj;
	}
}

//**********************************************************************************
// function rtvInputFieldFromDisplayField(displayFieldObj)
// Return the input field of a display object (e.g. Y/N input field of the checkbox
//
// Parameters:
// displayFieldObj - the display object
//**********************************************************************************	
function rtvInputFieldFromDisplayField(displayFieldObj)
{
	if (displayFieldObj.className.indexOf('wf_YNO') >= 0)
	{
		var objId = displayFieldObj.id.substr(0, displayFieldObj.id.length-4);
		return document.getElementById(objId);
	}
	
	else if (displayFieldObj.className.indexOf('wf_S1B') >= 0)
	{
		var objId = displayFieldObj.id.substr(0, displayFieldObj.id.length-3);
		return document.getElementById(objId);
	}
	
	else if (displayFieldObj.className.indexOf('wf_VALIDVALUE')>=0)
	{
		var objId = displayFieldObj.id.substr(0, displayFieldObj.id.length-16);
		return document.getElementById(objId);
	}
	
	else
	{
		return displayFieldObj;
	}

}


//**********************************************************************************
//function setAnyField(fieldId,value)
//Set the value of special fields (e.g. valid value, YNO)
//
//Parameters:
//fieldId - field id
//value   - value to set
//**********************************************************************************	
function setAnyFieldValue(fieldId,value)
{
	var obj = document.getElementById(fieldId);
	
	if (obj.className.indexOf('wf_VALIDVALUE') >= 0)
	{
		setFieldValueValid(fieldId,value);
	}
	
	if (obj.className.indexOf('wf_YNO') >= 0)
	{
		setFieldValueYNO(fieldId,value);
	}
	
	if (obj.className.indexOf('wf_S1B') >= 0)
	{
		setFieldValueS1B(fieldId,value);
	}
}


//**********************************************************************************
// function disableLogoffOnBlankFrame() 
// Disables the auto-logout of the blank frame
//
// Parameters:
// None
//**********************************************************************************
function disableLogoffOnBlankFrame() 
{
	// now disable the auto-logout of the blank frame
	var blankFrame = getFrameBlankFrame();
	if (blankFrame != null)
	{
		blankFrame.setAutoLogout(false);
	}
}


//**********************************************************************************
// function disableLogoffOnBlankFrameLogin()
// Disables the auto-logout of the blank frame
//
// Parameters:
// None
//**********************************************************************************
function disableLogoffOnBlankFrameLogin() 
{
	// now disable the auto-logout of the blank frame
	var blankFrame = getFrameBlankFrameLogin();
	if (blankFrame != null)
	{
		blankFrame.setAutoLogout(false);
	}
}


//**********************************************************************************
// function getFieldLength(obj) 
// Return the field length
//
// Parameters:
// obj - the object
//
// Returns:
// the field size
//**********************************************************************************
function getFieldLength(obj)
{
	if (obj.type == 'radio')
	{
		return 1;
	}
	else if (obj.fieldlength != null)
	{
		return parseInt(obj.fieldlength);
	}
	else if (obj.maxLength != null)
	{
		return obj.maxLength;
	}
	else 
	{
		return getValueFromSpanInput(obj).length;
	}
}


//**********************************************************************************
// function getValueFromSpanInput(obj) 
// Retrieve the value from an INPUT text or SPAN
//
// Parameters:
// obj - the object
//
// Returns:
// the value
//**********************************************************************************
function getValueFromSpanInput(obj)
{
	if (obj.type == 'radio')
	{
		if (obj.checked)
		{
			return obj.value;
		}
		return ' ';
	}
	else if (obj.value == null)
	{
		return obj.innerText;
	}
	else
	{
		return obj.value;
	}
}


//**********************************************************************************
// function executeShell(command, parameter) 
// Execute 
//
// Parameters:
// command - the command to execute
// parameter - the parameters
//**********************************************************************************
function executeShell(command, parameter)
{
	var oShell = new ActiveXObject("Shell.Application");
	oShell.ShellExecute(command, parameter, "", "open", 1);
	oShell = null;
	
}

//**********************************************************************************
//function disableAllFields()
//Disables all input fields and hides prompt fields/buttons
//
//**********************************************************************************
function disableAllFields() 
{
	// disable all input fields
	var el = document.getElementsByTagName('input');
	
	for(i=0;i<el.length;i++)
	{
		obj = el[i];
		if (obj.type == "text" || obj.type == "radio")
		{
			obj.disabled = true;
		}
		if (obj.type == "text")
		{
			addClassName(obj, "wf_pr");
		}
	}
	
	//hide all prompt fields/buttons
	var el = document.getElementsByTagName('a');
	var hrefsToRemove = [];
	
	for (var i = 0; i < el.length; i++) 
	{
		hrefsToRemove.push(el[i]);
	}
	
	for(var i = 0; i < hrefsToRemove.length; i++) 
	{
		// do not delete the shutter button
		if (!hrefsToRemove[i].id.endsWith(BUT_SHUTTER + BUT_HREF))
		{
			hrefsToRemove[i].parentNode.removeChild(hrefsToRemove[i]);
		}
	}
}