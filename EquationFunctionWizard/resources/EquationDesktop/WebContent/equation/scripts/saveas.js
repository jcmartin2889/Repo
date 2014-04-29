// **********************************************************************************
// function save_onload()
// Onload event of the window
//
// Parameters:
// None
// **********************************************************************************	
function save_onload(e)
{
	// User id object;
	objCurrentInputField = document.getElementById('tranId');
	
	// set the focus on the user name
	setTimeout( 
			function() 
			{
				objCurrentInputField.focus();
				objCurrentInputField.select();
			}
			,10);
	
	// adjust size of window
	testwidth = document.getElementById('saveTable').offsetWidth;
	testheight = document.getElementById('saveTable').offsetHeight;
	adjustWidthHeight(testwidth, testheight);
	
	// rtl?
	if (RTL)
	{
		document.getElementById('saveTable').dir = 'rtl';
	}
}

// **********************************************************************************
// function save_bodyKeyUp
// Handle key up events in the body
//
// Parameters:
// e - event
//
// Returns:
// None
// **********************************************************************************	
function save_bodyKeyUp(e)
{
	var keynum = rtvKeyboardKey(e);
	return true;
}


// **********************************************************************************
// function save_bodyKeyDown
// Handle key down events in the body
//
// Parameters:
// e - event
//
// Returns:
// None
// **********************************************************************************	
function save_bodyKeyDown(e)
{
	var keynum = rtvKeyboardKey(e);
	return true;	
}


// **********************************************************************************
// function tranid_onkeydown()
// Keydown event of the transaction id 
//
// Parameters:
// e - event
// **********************************************************************************	
function tranid_onkeydown(e)
{
	var keynum = rtvKeyboardKey(e);
	
	// enter key
	if (keynum==13)
	{
		request_save();
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
	request_save();
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
//function setStatus(sev,msg)
//Display the status
//
//Parameters:
// sev - message severity
//msg - message to display
//**********************************************************************************	
function setStatus(sev,msg)
{
	var iconimg = '<img class="buttonDisabled" border="0" src ="' + msgSevToImage(sev) + '">';
	var obj = document.getElementById('status');
	obj.innerHTML = iconimg + formatToSpan(msg);
	return msg;
}


// **********************************************************************************
// function request_save()
// Processing to save transaction 
//
// Parameters:
// None
// **********************************************************************************	
function request_save()
{
	// user id ready for server validation?
	var err = valid_tranId();
	if (err != '')
	{
		setStatus(20,err);
		return;
	}
	
	// set the transaction id
	var tranElement = window.opener.document.getElementById(OBJ_TRANID);
	tranElement.value = document.getElementById('tranId').value.toUpperCase().trim();
	
	// set the ckey
	var obj = window.opener.document.getElementById(OBJ_FKEY);
	obj.value = document.getElementById('ckey').value.trim();
	
	// submit it
	window.opener.getFrameCurrent().submitForm();

	// close this window
	window.close();
}

// **********************************************************************************
// function validate_tranId()
// Validate the tran id prior to processing by the server 
//
// Parameters:
// None
//
// Returns:
// Error message
// **********************************************************************************	
function valid_tranId()
{
	var tranId = document.getElementById('tranId').value;
	if (tranId == '') 
	{
		return getLanguageLabel('GBL900064');
	}
	if (tranId.trim() == '') 
	{
		return getLanguageLabel('GBL900064');
	}
	return '';
}