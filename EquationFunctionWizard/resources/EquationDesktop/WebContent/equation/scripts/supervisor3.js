// DECLINE processing

var onload_proc = true;
var onresize_proc = true;

// **********************************************************************************
// function reason_onload()
// Onload event of the window
//
// Parameters:
// None
// **********************************************************************************	
function reason_onload(e)
{
	// initialise
	onload_proc = true;
	onresize_proc = true;
	
	// User id object;
	objCurrentInputField = document.getElementById('reason');
	
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
	testwidth += 0;
	
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

// **********************************************************************************
// function reason_bodyKeyUp
// Handle key up events in the body
//
// Parameters:
// e - event
//
// Returns:
// None
// **********************************************************************************	
function reason_bodyKeyUp(e)
{
	var keynum = rtvKeyboardKey(e);
	return true;
}


// **********************************************************************************
// function reason_bodyKeyDown
// Handle key down events in the body
//
// Parameters:
// e - event
//
// Returns:
// None
// **********************************************************************************	
function reason_bodyKeyDown(e)
{
	var keynum = rtvKeyboardKey(e);
	return true;
}


// **********************************************************************************
// function reason_onkeydown()
// Keydown event of the transaction id 
//
// Parameters:
// e - event
// **********************************************************************************	
function reason_onkeydown(e)
{
	var keynum = rtvKeyboardKey(e);
	
	// enter key
	if (keynum==13)
	{
		request_reason();
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
	request_reason();
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
	window.close();
}

//**********************************************************************************
// function setStatus(sev,msg)
// Display the status
//
// Parameters:
// sev - message severity
// msg - message to display
//**********************************************************************************	
function setStatus(sev,msg)
{
	var iconimg = '<img class="buttonDisabled" border="0" src ="' + msgSevToImage(sev) + '">';
	var obj = document.getElementById('status');
	obj.innerHTML = iconimg + formatToSpan(msg);
	return msg;
}


// **********************************************************************************
// function request_reason()
// Processing to reason transaction 
//
// Parameters:
// None
// **********************************************************************************	
function request_reason()
{
	// user id ready for server validation?
	var err = valid_reason();
	if (err != '')
	{
		setStatus(20,err);
		return;
	}
	
	// set the transaction id
	var superMsgElement = window.opener.document.getElementById(OBJ_SUPMSG);
	superMsgElement.value = document.getElementById('reason').value.trim();
	
	// set the ckey
	var obj = window.opener.document.getElementById(OBJ_FKEY);
	obj.value = '10';
	
	// submit it
	window.opener.getFrameCurrent().submitForm();

	// close this window
	window.close();
}


// **********************************************************************************
// function validate_reason()
// Validate the tran id prior to processing by the server 
//
// Parameters:
// None
//
// Returns:
// Error message
// **********************************************************************************	
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