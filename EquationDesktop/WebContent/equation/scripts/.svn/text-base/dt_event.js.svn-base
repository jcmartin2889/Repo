
// **********************************************************************************
// function bodyKeyUp
// Handle key up events in the body
//
// Parameters:
// e - event
//
// Returns:
// None
// **********************************************************************************	
function dt_bodyKeyUp(e)
{
	var keynum = rtvKeyboardKey(e);
	
	// trigger predictive prompting, only values has been changed
	if (!isWebFacing() && objCurrentInputField != null && 
			getAttribute(objCurrentInputField,'predprompt') != null && 
			objCurrentInputField.value != objCurrentValue)
	{
		objCurrentValue = objCurrentInputField.value;
		triggerPredictivePrompt(objCurrentInputField);
	}
	
	return false;
}


// **********************************************************************************
// function bodyKeyDown
// Handle key down events in the body
//
// Parameters:
// e - event
//
// Returns:
// None
// **********************************************************************************	
function dt_bodyKeyDown(e)
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
				if (obj!=null)
				{	
					if (obj.style.visibility == 'visible')
					{
						// this popup does not have any event, so simply let this process it 
						if (p.divName != 'mouseSelectionBoxPopupID')
						{
							return true;
						}
					}
				} 
			}
		}
	}
	
	// F1 Help - This function key should not submit the form, instead it should trigger
	// the help if exists
	if (keynum==112  && !isShiftKey(e))
	{
		triggerHelp(objCurrentInputField);
		disableKeyboardKey(e);
		return false;
	} 
	
	// F4 Prompt - This function key should not submit the form, instead it should trigger
	// the prompt if exists
	if ( (keynum==115 && !isShiftKey(e) && !isAltKey(e) && !isCtrlKey(e)) ||
		 (keynum == 32 && isCtrlKey(e) && !isShiftKey(e) && !isAltKey(e) )
		)
	{
		triggerPrompt(objCurrentInputField);
		disableKeyboardKey(e);
		return false;
	} 
	
	// Shift-F1 Prompt - this function key should not submit the form, instead it should trigger
	// child window
	if (keynum==112 && isShiftKey(e))
	{
		triggerChildWindow(objCurrentInputField);
		disableKeyboardKey(e);
		return false;
	} 
	
	// F17 Toggle - This function key should not submit the form, instead it should toggle
	// fields in the browser
	if (keynum==116 && isShiftKey(e))
	{
		triggerToggleField(objCurrentInputField);
		disableKeyboardKey(e);
		return false;
	} 
	
	// determine if this is a special key
	if (isSpecialKey(keynum))
	{
		disableKeyboardKey(e);
		actionSpecialKey(cvtSpecialKey(isShiftKey(e),keynum), isCtrlKey(e));
		return false;
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
	
	return true;	
}


// **********************************************************************************
// function bodyKeyPress
// Handle key press events in the body
//
// Parameters:
// e - event
//
// Returns:
// None
// **********************************************************************************	
function dt_bodyKeyPress(e)
{
	var keynum = rtvKeyboardKey(e);
}


// **********************************************************************************
// function dt_onfocus(e)
// On focus event of the window
//
// Parameters:
// e - event
// **********************************************************************************	
function dt_onfocus(e)
{
	// modal window open?
	if (isAnyModalWindowOpen())
	{
		disableKeyboardKey(e);
		focusOnModalWindow();
		return false;
	}

	// tabbing from previous input field?
	if (tabbing==1)
	{
		tabbing = 0;
		moveToBottomLastField(objLastFocusInputField);
	}
	
	return true; 
}


// **********************************************************************************
// function dt_onresize(e)
// Handle resize events in the body
//
// Parameters:
// e - event
//
// Returns:
// None
// **********************************************************************************	
function dt_onresize(e)
{
	if (!pageLoading)
	{
		setTableSize(false);
	}
	
	maximumHeight();
	
	return true;
}


//**********************************************************************************
// function scrollRepeatingData(dataDiv, headerDiv)
// Scroll the column header, whenever the column data is being scrolled
//
// Parameters:
// dataDiv - id of the column data being scrolled
// headerDiv - id of the column header
// footerDiv - id of the colun footer
//**********************************************************************************	
function scrollRepeatingData(dataDiv, headerDiv, footerDiv)
{
	dataDivObj = document.getElementById(dataDiv);
	headerDivObj = document.getElementById(headerDiv);
	footerDivObj = document.getElementById(footerDiv);
	headerDivObj.scrollLeft = dataDivObj.scrollLeft;
	footerDivObj.scrollLeft = dataDivObj.scrollLeft;
}
