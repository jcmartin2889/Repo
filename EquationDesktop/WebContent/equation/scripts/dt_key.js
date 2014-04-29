
// **********************************************************************************
// function rtvKeyboardKey()
// Retrieve the keyboard key depending on the browser
//
// Parameters:
// e - event
//
// Returns:
// Keyboard character
// **********************************************************************************	
function rtvKeyboardKey(e)
{
	// internet explorer
	if (window.event) 
	{
		return window.event.keyCode;
	}
	
	// other browser
	else if (e.which)
	{
		return e.which;
	}
	
	// recheck key code
	else if (e.keyCode)
	{
		return e.keyCode;
	}
	
	// unknown
	return 0;
}



// **********************************************************************************
// function isShiftKey(e)
// Determine if shift key has been pressed
//
// Parameters:
// e - event
//
// Returns:
// true  - Shift key is pressed
// false - Shift key is not pressed
// **********************************************************************************	
function isShiftKey(e)
{
	// internet explorer
	if (window.event) 
	{
		return window.event.shiftKey;
	}
	
	// other browser
	else if (e.which)
	{
		return e.shiftKey;
	}
	
	// recheck event
	else if (e.shiftKey != null)
	{
		return e.shiftKey;
	}
	
	// unknown
	return false;
}


// **********************************************************************************
// function isCtrlKey(e)
// Determine if control key has been pressed
//
// Parameters:
// e - event
//
// Returns:
// true  - Shift key is pressed
// false - Shift key is not pressed
// **********************************************************************************	
function isCtrlKey(e)
{
	// internet explorer
	if (window.event) 
	{
		return window.event.ctrlKey;
	}
	
	// other browser
	else if (e.which)
	{
		return e.ctrlKey;
	}

	// recheck event
	else if (e.ctrlKey != null)
	{
		return e.ctrlKey;
	}
	
	// unknown
	return false;
}


//**********************************************************************************
// function isAltKey(e)
// Determine if alt key has been pressed
//
// Parameters:
// e - event
//
// Returns:
// true  - Alt key is pressed
// false - Alt key is not pressed
//**********************************************************************************	
function isAltKey(e)
{
	// internet explorer
	if (window.event) 
	{
		return window.event.altKey;
	}
	
	// other browser
	else if (e.which)
	{
		return e.altKey;
	}
	
	// recheck event
	else if (e.altKey != null)
	{
		return e.altKey;
	}
	
	// unknown
	return false;
}




// **********************************************************************************
// function isSpecialKey()
// Determine if this is a special key that will immediately submit the 
// screen to the server
//
// Parameters:
// keynum - key
//
// Returns:
// True  - special key
// False - not a special key
// **********************************************************************************	
function isSpecialKey(keynum)
{
	// Enter 
	if (keynum == 13)
	{
		return true;
	}
	 
	// F1..F12
	if (keynum >= 112 && keynum <=123)
	{
		return true;
	}
	
	// Page up and page down
	if (keynum >= 33 && keynum <= 34) 
	{
		return true;
	}
	
	// Not a special key
	return false;
}


// **********************************************************************************
// function cvtSpecialKey(shift, keynum)
// Convert the special key into function keys 
//
// Parameters:
// shift  - shift is pressed while key is pressed
// keynum - key
//
// Returns:
// Equivalent function key
// **********************************************************************************	
function cvtSpecialKey(shift, keynum)
{
	var key = '  ';
	
	// Enter 
	if (keynum == 13)
	{
		return '0';
	} 
	// F1..F12
	else if (keynum >= 112 && keynum <=123)
	{
		key = (keynum - 111);
		if (shift) 
		{
			key = key + 12; 
		}
		key = key + ' ';
		key = key.trim();
	}
	
	// Page up (F7) and Page down (F8)
	if (keynum >= 33 && keynum <= 34)
	{
		key = (keynum - 26) + ' ';
		key = key.trim();
	}
	
	// return the key
	return key;
}


// **********************************************************************************
// function actionSpecialKey()
// Perform processing for the special key 
//
// Parameters:
// ckey - function key
//
// Returns:
// None
// **********************************************************************************	
function actionSpecialKey(ckey,ctrlKey)
{
	// ctrl-F5
	if (ctrlKey && ckey==5)
	{
		// ensure the cursor is in a field
		if (objCurrentInputField!=null) 
		{
			// ensure that this support field validation
			var obj = document.getElementById(objCurrentInputField.id + "FldVal");
		
			// validate it
			if (obj!=null)
			{
				validateAndSubmitField(objCurrentInputField.id, null);
			}
		}
	}
	
	// other function keys
	else
	{
		validateAndSubmit('CF' + ckey, null);
	}
}


//**********************************************************************************
// function getEventTarget(e)
// Return the source object of the event
//
// Parameters:
// e - the event
//
// Returns:
// the source object of the event
//**********************************************************************************	
function getEventTarget(e)
{
	if (e.srcElement)
	{
		return e.srcElement;
	}
	else
	{
		return e.target;
	}
}

//**********************************************************************************
// function addMyEventListener()
// Add an event listener
//
// Parameters:
// obj         - the object to whom the event will be attached
// eventName   - the event name (e.g. keydown, blur, etc)
// functionptr - function pointer
//**********************************************************************************	
function addMyEventListener(obj, eventName, functionptr)
{
	if (document.addEventListener)
	{
		obj.addEventListener(eventName, functionptr, false);
	}
	else
	{
		obj.attachEvent('on' + eventName, functionptr);
	}
}

//**********************************************************************************
//function removeMyEventListener()
//Remove an event listener
//
//Parameters:
//obj         - the object to whom the event will be attached
//eventName   - the event name (e.g. keydown, blur, etc)
//functionptr - function pointer
//**********************************************************************************	
function removeMyEventListener(obj, eventName, functionptr)
{
	if (document.removeEventListener)
	{
		obj.removeEventListener(eventName, functionptr, false);
	}
	else
	{
		obj.detachEvent('on' + eventName, functionptr);
	}
}