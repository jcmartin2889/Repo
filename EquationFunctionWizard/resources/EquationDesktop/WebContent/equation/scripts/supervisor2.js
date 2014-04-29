// Supervisor entering the password

var onload_proc = true;
var onresize_proc = true;

// **********************************************************************************
// function rtvUserIdObj()
// Return the user id object element
//
// Parameters:
// None
// **********************************************************************************	
function rtvUserIdObj()
{
	return document.getElementById("userId");
}

// **********************************************************************************
// function rtvPasswordObj()
// Return the password object element
//
// Parameters:
// None
// **********************************************************************************	
function rtvPasswordObj()
{
	return document.getElementById("passwordText");
}

//**********************************************************************************
//function rtvProgressBarObj()
//Return the progress bar object element
//
//Parameters:
//None
//**********************************************************************************	
function rtvProgressBar()
{
	return document.getElementById("passwordProgressBar");
}


// **********************************************************************************
// function rtvUserId()
// Return the user id 
//
// Parameters:
// None
// **********************************************************************************	
function rtvUserId()
{
	var obj = rtvUserIdObj();
	if (obj.className.indexOf('wf_UPPERCASE') >=0)
    {
    	return obj.value.toUpperCase();
    }
	return obj.value;
}

// **********************************************************************************
// function rtvPassword()
// Return the password
//
// Parameters:
// None
// **********************************************************************************	
function rtvPassword()
{
	var obj = rtvPasswordObj();
	if (obj.className.indexOf('wf_UPPERCASE') >=0)
    {
    	return obj.value.toUpperCase();
    }
	return obj.value;
}

// **********************************************************************************
// function setStatus(sev,msg)
// Display the status
//
// Parameters:
// msg - message to display
// **********************************************************************************	
function setStatus(sev,msg)
{
	var iconimg = '<img class="buttonDisabled" border="0" src ="' + msgSevToImage(sev) + '">';
	var obj = document.getElementById('status');
	obj.innerHTML = iconimg + formatToSpan(msg);
	return msg;
}

// **********************************************************************************
// function setStatusId(msgId)
// Display the status via the content of an input element
//
// Parameters:
// msgId - input element
// **********************************************************************************	
function setStatusId(msgId)
{
	var obj = document.getElementById(msgId);
	var msg = setStatus(00,obj.value);
	return msg;
}

// **********************************************************************************
// function setStatus(msg)
// Display the status
//
// Parameters:
// msg - message to display
// **********************************************************************************	
function setStatus2(msg)
{
	var obj = document.getElementById('status2');
	obj.innerHTML = formatToSpan(msg);
	return msg;
}

// **********************************************************************************
// function password_onload()
// Onload event of the window
//
// Parameters:
// None
// **********************************************************************************	
function password_onload(e)
{
	// initialise
	onload_proc = true;
	onresize_proc = true;
	
	addClassName(rtvProgressBar(), NON_DISPLAY_CLASS1);

	// set the focus
	setTimeout( 
			function() 
			{
				rtvPasswordObj().focus();
				rtvPasswordObj().select();
			}
			,10);
	
	// get the objects
	var passwordTable = document.getElementById('passwordTable');
	var passwordDiv   = document.getElementById('passwordDiv');
	
	// determine size of table
	var testwidth  = passwordTable.offsetWidth;
	var testheight = passwordTable.offsetHeight;
	
	// increase the width to accomodate messages
	testwidth += 150;
	
	// adjust the size and window
	passwordTable.width = testwidth + "px";
	passwordTable.height = testheight + "px";
	passwordDiv.width = testwidth + "px";
	passwordDiv.height = testheight + "px";
	
	// for Chrome, adjust the width to ensure it will fit in the window
	if (isBrowserChrome())
	{
		testwidth += 30;
	}

	adjustWidthHeight(testwidth, testheight);

	// rtl?
	if (RTL)
	{
		document.getElementById('passwordTable').dir = 'rtl';
	}
}

//**********************************************************************************
//function password_onresize(e)
//Onresize event of the window
//
//Parameters:
//e - event
//**********************************************************************************	
function password_onresize(e)
{
	// for Chrome browser, readjust the size
	if (onresize_proc && !onload_proc && isBrowserChrome())
	{
		onresize_proc = false;
		var passwordTable = document.getElementById('passwordTable');
		var testwidth  = passwordTable.offsetWidth;
		var testheight = passwordTable.offsetHeight;
		adjustWidthHeight(testwidth, testheight);
	}
}


// **********************************************************************************
// function password_focus(e)
// Handle onfocus events in the body
//
// Parameters:
// e - event
//
// Returns:
// None
// **********************************************************************************	
function password_focus(e)
{
	rtvPasswordObj().focus();
	rtvPasswordObj().select();
	return false;
}

// **********************************************************************************
// function field_focus(e)
// Handle onfocus events in the body
//
// Parameters:
// e - event
//
// Returns:
// None
// **********************************************************************************	
function field_focus(e)
{
	return password_focus(e);
}

// **********************************************************************************
// function password_bodyKeyUp
// Handle key up events in the body
//
// Parameters:
// e - event
//
// Returns:
// None
// **********************************************************************************	
function password_bodyKeyUp(e)
{
	var keynum = rtvKeyboardKey(e);
	return false;
}


// **********************************************************************************
// function password_bodyKeyDown
// Handle key down events in the body
//
// Parameters:
// e - event
//
// Returns:
// None
// **********************************************************************************	
function password_bodyKeyDown(e)
{
	var keynum = rtvKeyboardKey(e);
	return true;	
}


// **********************************************************************************
// function passwordText_onkeydown()
// Keydown event of the password 
//
// Parameters:
// e - event
// **********************************************************************************	
function passwordText_onkeydown(e)
{
	var keynum = rtvKeyboardKey(e);
	
	// enter key
	if (keynum==13)
	{
		ok_onclick(e);	
	}
}

// **********************************************************************************
// function ok_onclick()
// Onclick event of the remote override button 
//
// Parameters:
// None
// **********************************************************************************	
function ok_onclick(e)
{
	// get the session id
	var sessionIdentifier = getSessionId();

	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName('authoriseBySupervisorId',nsuri);
	var qn_op_resp = new WS.QName('authoriseBySupervisorIdResponse',nsuri);

	// Retrieve details 
	var superId = rtvUserId();
	var password = rtvPassword();
	var name = rtvFunctionHandlerName();

	// Set the parameters required by the service
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'name',value:name},
								{name:'ckey',value:ckey},
								{name:'password',value:password},
								{name:'tranType',value:''}
							];

	// Progress bar
	removeClassName(rtvProgressBar(), NON_DISPLAY_CLASS1);
	
	// Call the web service
	call.invoke_rpc(
						qn_op,
						getSessionParms,
						null,
						function(call,envelope) 
						{
							var returnFieldString = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();
							
							// if not allowed, then issue the message and go away
							var valid = returnFieldString.substr(0,2);
							var msg   = returnFieldString.substr(2,returnFieldString.length);
							
							// invalid
							if (valid == 20)
							{
								addClassName(rtvProgressBar(), NON_DISPLAY_CLASS1);
								setStatus(20,msg);
								return;
							}

							// valid
							window.opener.clearSupervisorWindow();
							window.opener.validateAndSubmit(CF_KEY_EXIT2);
							window.close();
						}
					);

}

// **********************************************************************************
// function cancel_onclick()
// Onclick event of the remote override button 
//
// Parameters:
// None
// **********************************************************************************	
function cancel_onclick(e)
{
	// close the window	
	window.close();
}
