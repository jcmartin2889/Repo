var onload_proc = true;
var onresize_proc = true;

//**********************************************************************************
//function reason_onload()
//Onload event of the window
//
//Parameters:
//None
//**********************************************************************************	
function reason_onload(e)
{
	// initialise
	onload_proc = true;
	onresize_proc = true;
	
	// User id object;
	objCurrentInputField = document.getElementById('reason');
	
	
	// set the focus on the user name

	if (ckey == KEY_REFER)
	{
		objCurrentInputField = document.getElementById('referUserId');
	}
	
	// set the focus on the user name
	setTimeout( 
			function() 
			{
				objCurrentInputField.focus();
				objCurrentInputField.select();
			}
			,10);
	
	// get the objects
	var reasonTable = document.getElementById('reasonTable');
	var reasonDiv   = document.getElementById('reasonDiv');
	
	// determine size of table
	var testwidth  = reasonTable.offsetWidth;
	var testheight = reasonTable.offsetHeight;
	
	// increase the width to accomodate messages
	testwidth += 150;
	
	// adjust the size and window
	reasonTable.width = testwidth + "px";
	reasonTable.height = testheight + "px";
	reasonDiv.width = testwidth + "px";
	reasonDiv.height = testheight + "px";
	
	// for Chrome, adjust the width to ensure it will fit in the window
	if (isBrowserChrome())
	{
		testwidth += 30;
	}
	
	adjustWidthHeight(testwidth, testheight);
	
	// rtl?
	if (RTL)
	{
		document.getElementById('reasonTable').dir = 'rtl';
	}
}

//**********************************************************************************
//function password_onresize(e)
//Onresize event of the window
//
//Parameters:
//e - event
//**********************************************************************************	
function reason_onresize(e)
{
	// for Chrome browser, readjust the size
	if (onresize_proc && !onload_proc && isBrowserChrome())
	{
		onresize_proc = false;
		var reasonTable = document.getElementById('reasonTable');
		var testwidth  = reasonTable.offsetWidth;
		var testheight = reasonTable.offsetHeight;
		adjustWidthHeight(testwidth, testheight);
	}
}

//**********************************************************************************
//function reason_bodyKeyUp
//Handle key up events in the body
//
//Parameters:
//e - event
//
//Returns:
//None
//**********************************************************************************	
function reason_bodyKeyUp(e)
{
	var keynum = rtvKeyboardKey(e);
	return true;
}


//**********************************************************************************
//function reason_bodyKeyDown
//Handle key down events in the body
//
//Parameters:
//e - event
//
//Returns:
//None
//**********************************************************************************	
function reason_bodyKeyDown(e)
{
	var keynum = rtvKeyboardKey(e);
	
	// F4 Prompt - This function key should not submit the form, instead it should trigger
	// the prompt if exists
	if (ckey==KEY_REFER && keynum==115)
	{
		triggerPrompt(document.getElementById('referUserId'));
		disableKeyboardKey(e);
		return false;
	} 
	
	return true;
}


//**********************************************************************************
//function reason_onkeydown()
//Keydown event of the transaction id 
//
//Parameters:
//e - event
//**********************************************************************************	
function reason_onkeydown(e)
{
	var keynum = rtvKeyboardKey(e);
	
	// enter key
	if (keynum==13)
	{
		request_reason();
	}
}

//**********************************************************************************
//function ok_onclick()
//Onclick event of the remote override button 
//
//Parameters:
//None
//**********************************************************************************	
function ok_onclick(e)
{
	request_reason();
}

//**********************************************************************************
//function cancel_onclick()
//Onclick event of the remote override button 
//
//Parameters:
//None
//**********************************************************************************	
function cancel_onclick(e)
{
	window.close();
}

//**********************************************************************************
//function setStatus(sev,msg)
//Display the status
//
//Parameters:
//sev - message severity
//msg - message to display
//**********************************************************************************	
function setStatus(sev,msg)
{
	var iconimg = '<img class="buttonDisabled" border="0" src ="' + msgSevToImage(sev) + '">';
	var obj = document.getElementById('status');
	obj.innerHTML = iconimg + formatToSpan(msg);
	return msg;
}


//**********************************************************************************
// function request_reason()
// Processing to reason transaction 
//
// Parameters:
// None
//**********************************************************************************	
function request_reason()
{
	// validate user id
	if (ckey == KEY_REFER)
	{
		var err = valid_user();
		if (err != '')
		{
			setStatus(20,err);
			return;
		}
	}
	
	// validate reason
	var err = valid_reason();
	if (err != '')
	{
		setStatus(20,err);
		return;
	}
	
	// set the transaction id
	var superMsgElement = window.opener.document.getElementById(OBJ_SUPMSG);
	superMsgElement.value = document.getElementById('reason').value.trim();
	
	// set the user to refer to
	var referUser = window.opener.document.getElementById(OBJ_REFERTOUSERID);
	
	if (ckey == KEY_REFER)
	{
		referUser.value = document.getElementById('referUserId').value.trim();
		isUserAuthorise();
		return;
	} 
	else 
	{
		// have to put this line since  the value is not refreshed if the user jsut click other button after going back from refer
		referUser.value = "";
	}
	
	// set the ckey
	var obj = window.opener.document.getElementById(OBJ_FKEY);
	obj.value = ckey;
	
	// submit it
	window.opener.getFrameCurrent().submitForm();

	// close this window
	window.close();
}


//**********************************************************************************
// function validate_reason()
// Validate reason 
//
// Parameters:
// None
//
// Returns:
// Error message
//**********************************************************************************	
function valid_reason()
{
	var reason = document.getElementById('reason').value;
	if (reason == '') 
	{
		return getLanguageLabel('GBL900065');
	}
	if (reason.trim() == '') 
	{
		return getLanguageLabel('GBL900065');
	}
	return '';
}

//**********************************************************************************
// function valid_user()
// Validate user id 
//
// Parameters:
// None
//
// Returns:
// Error message
//**********************************************************************************	
function valid_user()
{
	var reason = document.getElementById('referUserId').value;
	if (reason == '') 
	{
		return getLanguageLabel('GBL900002');
	}
	if (reason.trim() == '') 
	{
		return getLanguageLabel('GBL900002');
	}
	return '';
}

//**********************************************************************************
//function ok_onclick()
//Onclick event of the remote override button 
//
//Parameters:
//None
//**********************************************************************************	
function isUserAuthorise()
{
	// get the session id
	var sessionIdentifier = getSessionId();

	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName('isValidReferToUser',nsuri);
	var qn_op_resp = new WS.QName('isValidReferToUserResponse',nsuri);

	// Retrieve details 
	var taskId = window.opener.getFrameDesktop().rtvTaskId();
	var userId = document.getElementById('referUserId').value;

	// Set the pararmeters required by the service
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'taskId',value:taskId},
								{name:'userId',value:userId}
							];

	// show progress bar
	rtvProgressBar().style.visibility='';
	
	// disable click
	disableObj('okButton',true);
	
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

							// invalid
							if (valid == 20)
							{
								// hide progress bar
								rtvProgressBar().style.visibility='hidden';
								
								// enable again
								disableObj('okButton',false);
								
								var msg   = returnFieldString.substr(2,returnFieldString.length);
								setStatus(20,msg);
								setStatus2('');
								return;
							}

							// valid
							var obj = window.opener.document.getElementById(OBJ_FKEY);
							obj.value = ckey;
							
							// submit it
							window.opener.getFrameCurrent().submitForm();

							// close this window
							window.close();
						}
					);
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
	return document.getElementById("userProgressBar");
}

