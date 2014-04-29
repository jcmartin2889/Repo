var maxRetry = 0;
var curRetry = 0;
var objCurrentInputField = null;
var gblTimeout = false;
var gblPollingTime = 2000;
var gblAuthStat = 5;
var requestStarted = false;
var timeoutSupervisor = null;
var onload_proc = true;
var onresize_proc = true;
var makerChecker = false;

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
// function rtvRetryObj()
// Return the retry object element
//
// Parameters:
// None
// **********************************************************************************	
function rtvRetryObj()
{
	return document.getElementById("retry");
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
// function rtvRetry()
// Return the retry value
//
// Parameters:
// None
// **********************************************************************************	
function rtvRetry()
{
	var obj = rtvRetryObj();
	var value = obj.value;
	
	// first digit is 0?
	if (value.substr(0,1) == '0')
	{
		value = value.substr(1,value.length);
	}

	// second digit is 0?
	if (value.substr(0,1) == '0')
	{
		value = value.substr(1,value.length);
	}

	return value; 
}

// **********************************************************************************
// function setPasswordDivVisibility(visible)
// Show/hide the waiting div visibility
//
// Parameters:
// visible - true - shows the div
// **********************************************************************************	
function setPasswordDivVisibility(visible)
{
	var obj = document.getElementById('passwordDiv');
	if (visible==true)
	{
		disableObj('userId',false);
		disableObj('retry',false);
		disableObj('passwordText',false);
		disableObj('okButton',false);
		disableObj('cancelButton',false);
		if (tranType != 'S')
		{
			disableAnchor('userId' + BUT_PROMPT + BUT_HREF,false);
			disableObj('remoteButton',false);
			disableObj('offlineButton',false);
			disableObj('lrpButton',false);
		}
		addClassName(rtvProgressBar(), NON_DISPLAY_CLASS1);
	}
	else
	{
		disableObj('userId',true);
		disableObj('retry',true);
		disableAnchor('userId' + BUT_PROMPT + BUT_HREF,true);
		disableObj('passwordText',true);
		disableObj('okButton',true);
		disableObj('cancelButton',true);
		disableObj('remoteButton',true);
		disableObj('offlineButton',true);
		disableObj('lrpButton',true);
		removeClassName(rtvProgressBar(), NON_DISPLAY_CLASS1);
	}
}

//**********************************************************************************
//function setCheckerDivVisibility(visible)
//Show/hide the checker list div visibility
//
//Parameters:
//visible - true - shows the div
//**********************************************************************************	
function setCheckerDivVisibility(visible)
{
	var obj = document.getElementById('PvPopUpWorking');
	if (visible==true)
	{
		obj.style.display = "none";
		disableAllObj("mainPopupDiv",false);
	}
	else
	{
		obj.style.display = "";
		disableAllObj("mainPopupDiv",true);
	}
}
// **********************************************************************************
// function setStatus(sev,msg)
// Display the status
//
// Parameters:
// sev - message severity
// msg - message to display
// **********************************************************************************	
function setStatus(sev,msg)
{
	var iconimg = '<img class="buttonDisabled" border="0" src ="' + msgSevToImage(sev) + '">';
	var obj;
	if (makerchecker)
	{
		obj = document.getElementById('pvPopUpMsg');
	}
	else
	{
		obj = document.getElementById('status');
	}
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
	str = '';
	if (msg != '')
	{
		str = ' - ';
	}

	var obj = document.getElementById('status2');
	obj.innerHTML = str + formatToSpan(msg);
	return msg;
}

// **********************************************************************************
// function password_onload()
// Onload event of the window
//
// Parameters:
// e - event
// **********************************************************************************	
function password_onload(e)
{
	// initialise
	onload_proc = true;
	onresize_proc = true;
	
	// User id object;
	objCurrentInputField = rtvUserIdObj();

	// Hide the remote and offline for CRM-S authorisation
	if (tranType == 'S')
	{
		visibleObj('userId' + BUT_PROMPT,false);
		disableAnchor('userId' + BUT_PROMPT + BUT_HREF,true);
		disableObj('remoteButton',true);
		disableObj('offlineButton',true);
		disableObj('lrpButton',true);
	}
	
	// div
	setPasswordDivVisibility(true);

	// set the focus on the user name
	setTimeout( 
			function() 
			{
				objCurrentInputField.focus();
				objCurrentInputField.select();
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
	
	onload_proc = false;
}

//**********************************************************************************
// function password_onresize(e)
// Onresize event of the window
//
// Parameters:
// e - event
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
// Parameters:
// e - event
//
// Returns:
// None
// **********************************************************************************	
function password_focus(e)
{
	if (isAnyModalWindowOpen())
	{
		focusOnModalWindow();
	}
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

	// is there any popop currently displayed, then let the pop-up handles the key event
	// this is odd for popup as the mainbody receives the event first before the popup!
	if (typeof popupWindowObjects!='undefined')
	{
		for (var i=0; i<popupWindowObjects.length; i++) 
		{	
			if (popupWindowObjects[i] != null) 
			{	
				var p = popupWindowObjects[i];
				var obj = document.getElementById(p.divName);	
				if (obj.style.visibility == 'visible') 
				{
					return true;
				}
			}
		}
	}
	
	// F4 Prompt - This function key should not submit the form, instead it should trigger
	// the prompt if exists
	if (keynum==115)
	{
		triggerPrompt(objCurrentInputField);
		disableKeyboardKey(e);
		return false;
	} 
	
	return true;	
}


// **********************************************************************************
// function retry_onkeydown()
// Keydown event of the retry button
//
// Parameters:
// None
// **********************************************************************************	
function retry_onkeydown(e)
{
	var keynum = rtvKeyboardKey(e);
	
	// handle enter key and trigger remote override
	if (keynum==13)
	{
		remote_onclick(e);
	}
}

// **********************************************************************************
// function userId_onkeydown()
// Keydown event of the user id 
//
// Parameters:
// e - event
// **********************************************************************************	
function userId_onkeydown(e)
{
	var keynum = rtvKeyboardKey(e);

	// handle F4=Prompt
	if (keynum==115 && !isShiftKey(e))
	{
		triggerPrompt(rtvUserIdObj());
		disableKeyboardKey(e);
		return false;
	}

	// enter key
	if (keynum==13)
	{
		if (tranType == 'S')
		{
			ok_onclick(e);
		}
		else
		{
			request_supervisor();
		}
	}
}

//**********************************************************************************
//function userId_onkeydown_mc()
//Keydown event of the user id for maker checker - do nothing when 'Enter' key is pressed  
//
//Parameters:
//e - event
//**********************************************************************************	
function userId_onkeydown_mc(e)
{
	
	// user id ready for server validation?
	var err = valid_userid();
	if (err != '')
	{
		setStatus(20,err);
		setStatus2('');
		return;
	} else {
		var obj = document.getElementById('status');
		obj.innerHTML = '';
	}
	
	var keynum = rtvKeyboardKey(e);

	// handle F4=Prompt
	if (keynum==115 && !isShiftKey(e))
	{
		triggerPrompt(rtvUserIdObj());
		disableKeyboardKey(e);
		return false;
	}

	// enter key
	if (keynum==13)
	{
		if (tranType == 'S')
		{
			ok_onclick(e);
		} else {
			makerChecker = true;
			offline_supervisor("Y");		
		}
	}
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
	// user id ready for server validation?
	var err = valid_userid();
	if (err != '')
	{
		setStatus(20,err);
		setStatus2('');
		return;
	}
			
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

	// Set the pararmeters required by the service
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'name',value:name},
								{name:'superId',value:superId},
								{name:'password',value:password},
								{name:'tranType',value:tranType}
							];
		
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
								setStatus(20,msg);
								setStatus2('');
								return;
							}

							// valid
							window.opener.clearSupervisorWindow();
							window.opener.validateAndSubmit(CF_KEY_VERIWARN);
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
	// if request has already been started
	if (requestStarted)
	{
		setStatusId('usercancel');
		setStatus2('');
		if (timeoutSupervisor != null)
		{
			clearTimeout(timeoutSupervisor);
			timeoutSupervisor = null;
		}
		requestStarted = false;
		removeSupervisor('7');
		setPasswordDivVisibility(true);
	}
	//
	else
	{
		// authorisation approved?
		if(gblAuthStat==1)
		{
			window.opener.clearSupervisorWindow();
			window.opener.validateAndSubmit(CF_KEY_VERIWARN);
		}
		
		// close the window	
		window.close();
	}
}

// **********************************************************************************
// function remote_onclick()
// Onclick event of the remote override button 
//
// Parameters:
// None
// **********************************************************************************	
function remote_onclick(e)
{
	request_supervisor();
}

// **********************************************************************************
// function offline_onclick()
// Onclick event of the offline override button 
//
// Parameters:
// None
// **********************************************************************************	
function offline_onclick(e)
{
	offline_supervisor("Y");
}

//**********************************************************************************
//function offline_onclick()
//Onclick event of the offline override button 
//
//Parameters:
//None
//**********************************************************************************	
function lrp_onclick(e)
{
	offline_supervisor("L");
}

//**********************************************************************************
//function offline_makerchecker_onclick()
//Onclick event of the offline override button for maker-checker 
//
//Parameters:
//None
//**********************************************************************************	
function offline_makerchecker_onclick(e)
{
	makerChecker = true;
	offline_supervisor("Y");
}

// **********************************************************************************
// function request_supervisor()
// Processing for remote online supervisor override 
//
// Parameters:
// None
// **********************************************************************************	
function request_supervisor()
{
	// user id ready for server validation?
	var err = valid_userid();
	if (err != '')
	{
		setStatus(20,err);
		setStatus2('');
		return;
	}
	
	try
	{
		// inform the user that it is waiting for the supervisor
		setStatusId('awaiting');
		setStatus2('');
		setPasswordDivVisibility(false);
	
		// get the session id
		var sessionIdentifier = getSessionId();

		// need something to locate the web service defined wsdlsoap:address in the wsdl
		var call = getWSCall(); 
		
		// The targetNamespace defined in the wsdl
		var nsuri = getEquationServicePath();
	
		// Define the get and response
		var qn_op = new WS.QName('remoteSupervisor',nsuri);
		var qn_op_resp = new WS.QName('remoteSupervisorResponse',nsuri);
		var name = rtvFunctionHandlerName();
	
		// Set the pararmeters required by the service
		var getSessionParms = 	[
									{name:'sessionIdentifier',value:sessionIdentifier},
									{name:'name',value:name},
									{name:'userId',value:rtvUserId()},
									{name:'offline',value:'N'}
								];
			
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
									setStatus(20,msg);
									setStatus2('');
									setPasswordDivVisibility(true);
									rtvUserIdObj().select();
									rtvUserIdObj().focus();
									return;
								}
								
								// wait for this number of seconds
								maxRetry = parseInt(rtvRetry(),10);
								if (maxRetry<=0)
								{
									maxRetry = 10;
								}
								maxRetry = maxRetry * 1000;
							
								// time-out wait
								requestStarted = true;
								disableObj('cancelButton',false);
								gblTimeout = false;
								gblAuthStat = 5;
								timeoutSupervisor = setTimeout(function (){							  
											     			supervisorTimeout(); 
											    			},maxRetry);
							
								// time-out to check every 2 seconds to determine if
								// supervisor has begun reviewing/authorised it
								if (maxRetry > gblPollingTime)
								{
									hasSuperReview(gblPollingTime);
								}
							}
						);
	}
	catch (e)
	{
		setPasswordDivVisibility(true);
	}
	finally
	{
	}
}


// **********************************************************************************
// function offline_supervisor(mode)
// Processing for offline supervisor override 
//
// Parameters:
// mode - Y - offline override
//        L - LRP referral
//
// **********************************************************************************	
function offline_supervisor(mode)
{
	// user id ready for server validation?
	var err = valid_userid();
	if (err != '')
	{
		setStatus(20,err);
		if (!makerchecker)
		{
			setStatus2('');
		}
		return;
	}
	
	try
	{
		if (makerchecker)
		{
			setCheckerDivVisibility(false);
		}
		else
		{
			// inform the user that it is waiting for the supervisor
			setPasswordDivVisibility(false);
		}
	
		// get the session id
		var sessionIdentifier = getSessionId();

		// need something to locate the web service defined wsdlsoap:address in the wsdl
		var call = getWSCall(); 
		
		// The targetNamespace defined in the wsdl
		var nsuri = getEquationServicePath();
	
		// Define the get and response
		var qn_op = new WS.QName('remoteSupervisor',nsuri);
		var qn_op_resp = new WS.QName('remoteSupervisorResponse',nsuri);
		var name = rtvFunctionHandlerName();
	
		// Set the pararmeters required by the service
		var getSessionParms = 	[
									{name:'sessionIdentifier',value:sessionIdentifier},
									{name:'name',value:name},
									{name:'userId',value:rtvUserId()},
									{name:'offline',value:mode}
								];
			
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
									setStatus(20,msg);
									if (makerchecker)
									{
										setCheckerDivVisibility(true);
									}
									else
									{
										setStatus2('');
										setPasswordDivVisibility(true);
									}
									return;
								}
								// if maker checker, show a confirmation alert that the transaction was submitted for checking
								if (makerChecker)
								{
									var labels = new Object();
									labels.ok = getLanguageLabel('GBLOK');
									labels.submitted = getLanguageLabel('GBL900115');
									labels.makerchecker = getLanguageLabel('GBL000394');
									var arg = 'resizable=no; dialogHeight=200px; dialogWidth=300px; status=no;';
									var submitted = window.showModalDialog("/" + getWebAppName() + "/equation/jsp/confirmation.jsp", labels, arg);
									
									if (submitted == true)
									{
										// tell the window to exit the function								
										window.opener.clearSupervisorWindow();
										window.opener.validateAndSubmit(CF_KEY_EXIT_OFLNE_OVR);
										window.close();
									}
								}
								else
								{
									// tell the window to exit the function								
									window.opener.clearSupervisorWindow();
									window.opener.validateAndSubmit(CF_KEY_EXIT_OFLNE_OVR);
									window.close();
								}
							}
						);
	}
	catch (e)
	{
		if (makerchecker)
		{
			setCheckerDivVisibility(true);
		}
		else
		{
			setPasswordDivVisibility(true);
		}	
	}
	finally
	{
	}

}



// **********************************************************************************
// function hasSuperReview(increment)
// Determine if supervisor has started reviewing the transaction 
//
// Parameters:
// increment - the number of seconds
// **********************************************************************************	
function hasSuperReview(increment)
{
	// request?
	if (!requestStarted)
	{
		return;
	}
	
	// get the session id
	var sessionIdentifier = getSessionId();
	
	// request status of WE.  If WE has been authorised or being reviewed then exit
	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName('checkSessionStatus',nsuri);
	var qn_op_resp = new WS.QName('checkSessionStatusResponse',nsuri);
	var name = rtvFunctionHandlerName();

	// Set the pararmeters required by the service
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
							var authStat = getAttributeValue(envelope.get_body().get_all_children()[0].get_all_children()[0].get_value(),'authStat');
							
							// supervisor has authorised it
							if (authStat == "1")
							{
								var authLevel = getAttributeValue(envelope.get_body().get_all_children()[0].get_all_children()[0].get_value(),'authLevel');
								supervisorApprove(authLevel);
								return;
							}
							
							// supervisor has rejected it
							if (authStat == "4")
							{
								var reasonRejection = getAttributeValue(envelope.get_body().get_all_children()[0].get_all_children()[0].get_value(),'reasonRejection');
								supervisorDecline(reasonRejection);
								return;
							}

							// supervisor has begun checking it
							if (authStat == "6")
							{
								supervisorReview();
							}

							// supervisor has begun reviewing it but has not taken any action 
							// (authorise/decline).  If it has already timed-out, then 
							// trigger timeout processing.  Otherwise, keep on polling
							if (gblAuthStat=="6" && authStat=="5")
							{
								if (gblTimeout)
								{
									supervisorCancel();
									return;
								}
								else
								{
									setStatusId('awaiting');
									setStatus2('');
									gblAuthStat = "5";
									disableObj('cancelButton',false);
								}
							}
							
							// record not found
							if (authStat == ' ')
							{
								return;
							}

							// supervisor has not authorised/decline, then keep on polling
							
							// wait for more
							timeoutPolling = setTimeout(function (){							  
											     hasSuperReview(increment); 
											    },increment);
						}
					);

}

// **********************************************************************************
// function supervisorReview()
// Processing when supervisor has begun reviewing the transaction 
//
// Parameters:
// None
// **********************************************************************************	
function supervisorReview()
{
	gblAuthStat = "6";
	setStatusId('review');
	setStatus2('');
	disableObj('cancelButton',true);
}

// **********************************************************************************
// function supervisorApprove(authLevel)
// Processing when supervisor has approved the transaction 
//
// Parameters:
// authLevel - 1 (first warning only) or A (all warnings)
// **********************************************************************************	
function supervisorApprove(authLevel)
{
	// get the session id
	var sessionIdentifier = getSessionId();

	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName('authoriseBySupervisorOverride',nsuri);
	var qn_op_resp = new WS.QName('authoriseBySupervisorOverrideResponse',nsuri);
	var name = rtvFunctionHandlerName();

	// Set the pararmeters required by the service
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
							gblAuthStat = "1";
							var msg = '';
							if (authLevel==1)
							{
								msg = setStatusId('approveone');
							}
							else
							{
								msg = setStatusId('approve');
							}
							setStatus2('');
							
							requestStarted = false;
							setPasswordDivVisibility(false);
							addClassName(rtvProgressBar(), NON_DISPLAY_CLASS1);
							disableObj('cancelButton',false);
							document.getElementById('cancelButton').value = document.getElementById('closebutton').value;
						}
					);
}

// **********************************************************************************
// function supervisorDecline()
// Processing when supervisor has declined the transaction 
//
// Parameters:
// reasonRejection - reason for rejection
// **********************************************************************************	
function supervisorDecline(reasonRejection)
{
	gblAuthStat = "4";
	setPasswordDivVisibility(true);
	removeSupervisor(gblAuthStat);
	reasonRejection = reasonRejection.trim();
	setStatusId('decline');
	setStatus2(reasonRejection);
	requestStarted = false;
}

// **********************************************************************************
// function supervisorCancel()
// Processing when supervisor has abandoned the review 
//
// Parameters:
// None
// **********************************************************************************	
function supervisorCancel()
{
	// Remove the supervisor request
	removeSupervisor('');

	// timeout
	setPasswordDivVisibility(true);
	setStatusId('supercancel');
	setStatus2('');
}

// **********************************************************************************
// function supervisorTimeout()
// Processing when supervisor has not begun reviewing the transaction 
//
// Parameters:
// None
// **********************************************************************************	
function supervisorTimeout()
{
	// timeout
	gblTimeout = true;

	// still has not been actioned?
	if (gblAuthStat!="5")
	{
		return;
	}

	// Remove the supervisor request
	requestStarted = false;
	removeSupervisor('8');

	// timeout
	setPasswordDivVisibility(true);
	setStatusId('timeout');
	setStatus2('');
	
	// position
	var obj = document.getElementById('remoteButton');
	obj.focus();
}

// **********************************************************************************
// function removeSupervisor(status)
// Remove the supervisor request 
//
// Parameters:
// None
// **********************************************************************************	
function removeSupervisor(status)
{
	// get the session id
	var sessionIdentifier = getSessionId();

	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName('removeSupervisor',nsuri);
	var qn_op_resp = new WS.QName('removeSupervisorResponse',nsuri);
	var name = rtvFunctionHandlerName();

	// Set the pararmeters required by the service
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'name',value:name},
								{name:'status',value:status}
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
// function validate_userid
// Validate the user id prior to processing by the server 
//
// Parameters:
// None
//
// Returns:
// Error message
// **********************************************************************************	
function valid_userid()
{
	var superId = rtvUserId();
	if (superId == '') 
	{
		return getLanguageLabel('GBL900002');
	}
	if (superId.trim() == '') 
	{
		return getLanguageLabel('GBL900002');
	}
	return '';
}

//**********************************************************************************
//function ok_confirm()

//Parameters:
//None
//**********************************************************************************	
function ok_confirm(e)
{
	window.close();
}

//**********************************************************************************
//function getCheckerList()
//Gets the list of Checkers for authorisation
//
//Parameters:
//None
//**********************************************************************************	
function getCheckerList()
{
    // If not using CAS, prompt should show the 4 character user id
	if (isEquationAuthentication == 'true')
	{
		getNewPVList(getWindowLanguageLabel('GBL000397'),
			'userId',
			'OCR32R',
			'A',
			'',
			'',
			'',
			'$OCBBN,$OCUSID,$OCRBBN,$OCNAME,$OCPHN,$OCAVIL,$OCONLN',
			cvtArrToString([getWindowLanguageLabel('GBLBRNM'),getWindowLanguageLabel('GBL000233'),getWindowLanguageLabel('GBL000234'),getWindowLanguageLabel('GBLNAME'),getWindowLanguageLabel('GBL000235'),getWindowLanguageLabel('GBLAVAIL'),getWindowLanguageLabel('GBLONLN')],globalDelimeter),
			'0,4,8,12,47,67,68',
			'4,4,4,35,20,1,1',
			0,
			12,
			'$OCBBN,.*,0,4,N,N,A,N,$OCUSID,userId,4,4,N,N,A,N,$OCRBBN,.*,8,4,N,N,A,N,$OCNAME,.*,12,35,N,N,A,N,$OCPHN,.*,47,20,N,N,A,N,$OCAVIL,.*,67,1,N,N,A,N,$OCONLN,.*,68,1,N,N,A,N,$OCEMH,$OCEMH,69,740,N,N,A,N,$OCEA,$OCEA,809,300,N,N,A,N,$OCEB,$OCEB,1109,80,N,N,A,N,$OCDR,$OCDR,1189,20,N,N,A,N,$OCBFUS,.*,1209,30,N,N,A,N,$OCUSID,userId,4,4,Y,N,A,N,$OCONLN,$OCONLN,68,1,Y,N,A,N',
			'WDBBN,WDUID,WDRBBN,,,,',
			',,,,,,',
			true,
			16);
	}
	else
	{
	    // If are using CAS, prompt should show the longer 30 character user id
		getNewPVList(getWindowLanguageLabel('GBL000397'),
				'userId',
				'OCR32R',
				'B',
				'',
				'',
				'',
				'$OCBBN,$OCBFUS,$OCUSID,$OCRBBN,$OCNAME,$OCPHN,$OCAVIL,$OCONLN',
				cvtArrToString([getWindowLanguageLabel('GBLBRNM'),getWindowLanguageLabel('GBL000400'), getWindowLanguageLabel('GBL000233'),getWindowLanguageLabel('GBL000234'),getWindowLanguageLabel('GBLNAME'), getWindowLanguageLabel('GBL000235'),getWindowLanguageLabel('GBLAVAIL'),getWindowLanguageLabel('GBLONLN')],globalDelimeter),
				'0,1209,4,8,12,47,67,68',
				'4,30,4,4,35,20,1,1',
				0,
				12,
				'$OCBBN,.*,0,4,N,N,A,N,$OCUSID,.*,4,4,N,N,A,N,$OCRBBN,.*,8,4,N,N,A,N,$OCNAME,.*,12,35,N,N,A,N,$OCPHN,.*,47,20,N,N,A,N,$OCAVIL,.*,67,1,N,N,A,N,$OCONLN,.*,68,1,N,N,A,N,$OCEMH,$OCEMH,69,740,N,N,A,N,$OCEA,$OCEA,809,300,N,N,A,N,$OCEB,$OCEB,1109,80,N,N,A,N,$OCDR,$OCDR,1189,20,N,N,A,N,$OCBFUS,userId,1209,30,N,N,A,N,$OCBFUS,userId,1209,30,Y,N,A,N,$OCONLN,$OCONLN,68,1,Y,N,A,N',
				'WDBBN,,WDUID,WDRBBN,,,,',
				',,,,,,,',
				true,
				16);		
		
	}
}