
// **********************************************************************************
// function dt_kbdSelKeyDown(e, selId, fieldName, selRoot, selIdx)
// Handle up, arrow and enter key on a selection button
//
// Parameters:
// e         - event
// selId     - select ID of the radio button
// fieldName - Field Name
// selRoot   - selection ID root
// selIdx    - selection index
// **********************************************************************************
function dt_kbdSelKeyDown(e, selId, fieldName, selRoot, selIdx)
{
	var keynum = rtvKeyboardKey(e);

	// enter
	if (keynum==13)
	  {
		var obj = document.getElementById(selId);
	  	obj.checked = true;
	  	document.getElementById(selId).value = '1';
	  	obj.click();
		disableKeyboardKey(e);	  	
	    return false;	    	
	  }

	// key up / left
	if (keynum==38 || keynum==37)
	  {
	  	dt_moveSubfile(selId,fieldName,-1,selRoot,selIdx);
		disableKeyboardKey(e);	  	
	    return false;	    	
	  }
	
	// key down / right
	if (keynum==40 || keynum==39)
	  {
	  	dt_moveSubfile(selId,fieldName,1,selRoot,selIdx);
		disableKeyboardKey(e);	  	
	    return false;	    	
	  }
	  
	// tab key
	if (keynum==9 && !isShiftKey(e))
	{
	  	dt_moveTab(selId,fieldName,1,selRoot,selIdx);
		disableKeyboardKey(e);	  	
	    return false;	    	
	}
	
	// shift-tab key
	if (keynum==9 && isShiftKey(e))
	{
	  	dt_moveTab(selId,fieldName,-1,selRoot,selIdx);
		disableKeyboardKey(e);	  	
	    return false;	    	
	}
	
	// page up
	if (keynum==33)
	{
		var s = document.getElementById('PvPopUpPrev').innerHTML.trim().substr(0,2);
		if (s == '<a' || s == '<A')
		{
			obj = document.getElementById('pvPopUpPrevHref');
			eval(obj.href);
		}		  	
		disableKeyboardKey(e);	  	
	    return false;	    	
	}
	
	// page down
	if (keynum==34)
	{
		var s = document.getElementById('PvPopUpNext').innerHTML.trim().substr(0,2);
		if (s == '<a' || s == '<A')
		{
			obj = document.getElementById('pvPopUpNextHref');
			eval(obj.href);
		}		  	
		disableKeyboardKey(e);	  	
	    return false;	    	
	}

	// esc
	if (keynum==27)
	{
		dt_pvClosePopUp(true);
		disableKeyboardKey(e);
	    return false;	    	
	}
}

// **********************************************************************************
// function dt_moveSubfile(sSinpText,disp)
// Set the focus to another radio button
//
// Parameters:
// selId     - select ID of the radio button
// fieldName - Field Name
// disp      - displacement from the current radio button
// selRoot   - selection ID root
// selIdx    - selection index
// **********************************************************************************
function dt_moveSubfile(selId,fieldName,disp,selRoot,selIdx)
{
	var idx, obj, obj2;

	// get the next radio button
	var idx = parseInt(triml(selIdx,'0')) + disp;
	obj = document.getElementById(selRoot + padl(idx,3,'0'));
	
	if (obj != null) 
	{
		obj2 = document.getElementById(selId);
		obj2.checked = false;
		
	  	obj.focus();
	  	obj.checked = true;
	}
	
	// no object
	else
	{
		// go to the last record
		if (idx < 0)
		{
			obj2 = document.getElementById('sel_numrec');
			obj  = document.getElementById(selRoot + padl((parseInt(obj2.value)-1),3,'0'));
			obj.focus();
		}
		
		// go to the first button
		else
		{
			obj = document.getElementById(selRoot + '000');
			obj.focus();
		}
	}
}


// **********************************************************************************
// function dt_moveTab(sSinpText,disp)
// Set the focus to another radio button
//
// Parameters:
// selId     - select ID of the radio button
// fieldName - Field Name
// disp      - displacement from the current radio button
// selRoot   - selection ID root
// selIdx    - selection index
// **********************************************************************************
function dt_moveTab(selId,fieldName,disp,selRoot,selIdx)
{
	var obj;

	// go to the next radio button	
	var idx = parseInt(triml(selIdx,'0')) + disp;
	obj = document.getElementById(selRoot + padl(idx,3,'0'));

	if (obj!=null)
	{
		dt_moveSubfile(selId,fieldName,disp,selRoot,selIdx);
	}
	
	// no object
	else
	{
		// go to the last filter input field
		if (idx < 0)
		{
			// get the number of columns
			n = parseInt(document.getElementById('sel_numcol').value) - 1 ;
			obj = document.getElementById('filter_' + fieldName + n);
			obj.focus();
			var numcol = parseInt(obj.value);
		}
		
		// go to the cancel button
		else
		{
			// get the number of records
			obj = document.getElementById('pvPopUpCanHref');
			obj.focus();
		}
	}
}

// **********************************************************************************
// function dt_kbdSelKeyDown2(e, curId, toId, prevId)
// Handle tab keys on selected fields in the popup - Basically to control the tab
// order on the first and the last fields
//
// Parameters:
// e       - event
// curId   - the current field 
// toId    - the next field
// prevId  - the previous field
// **********************************************************************************
function dt_kbdSelKeyDown2(e, curId, toId, prevId)
{
	var keynum = rtvKeyboardKey(e);

	// tab key
	if (toId!=null && keynum==9 && !isShiftKey(e))
	{
		var obj = document.getElementById(toId);
		
		// cancel button?
		if (obj==null && curId=='pvPopUpCanHref')
		{
			obj = document.getElementById('pvPopUpNextHref');
			if (obj==null)
			{
				obj = document.getElementById('pvPopUpExitHref');
			}
		}
		
		if (obj==null)
		{
			return true;
		}
		obj.focus();
		disableKeyboardKey(e);	  	
	    return false;	    	
	}
	
	// shift-tab key
	if (prevId!=null && keynum==9 && isShiftKey(e))
	{
		var obj = document.getElementById(prevId);

		// next button?
		if (obj==null && curId=='pvPopUpNextHref')
		{
			obj = document.getElementById('pvPopUpCanHref');
		}
		
		// close button?
		if (obj==null && curId=='pvPopUpExitHref')
		{
			obj = document.getElementById('pvPopUpPrevHref');
			if (obj==null)
			{
				obj = document.getElementById('pvPopUpCanHref');
			}
		}
		
		if (obj==null)
		{
			return true;
		}
		obj.focus();
		disableKeyboardKey(e);	  	
	    return false;	    	
	}

	// esc
	if (keynum==27)
	{
		dt_pvClosePopUp(true);
		disableKeyboardKey(e);
	    return false;	    	
	}

	// page up
	if (keynum==33)
	{
		// is page up active?
		var pageup = document.getElementById('pvPopUpPrevHref');
		if (pageup!=null)
		{
			// blur the current field - this is needed for the input field to deselect the text
			if (obj != null)
			{
				var obj = document.getElementById(curId);
				obj.blur();
			}
			
			// page up
			pageup.click();
		}
		
		disableKeyboardKey(e);	  	
	    return false;	    	
	}
	
	// page down
	if (keynum==34)
	{
		// is page down active?
		var pagedown = document.getElementById('pvPopUpNextHref');
		if (pagedown != null)
		{
			// blur the current field - this is needed for the input field to deselect the text
			if (obj != null)
			{
				var obj = document.getElementById(curId);
				obj.blur();
			}
			
			// page down
			pagedown.click();
		}
		
		disableKeyboardKey(e);	  	
	    return false;	    	
	}
	
}

// **********************************************************************************
// function dt_pvclosePopUp(closeWindow)
// Close the pv poupup
//
// Parameters:
// closeWindow - true, close the window
// **********************************************************************************
function dt_pvClosePopUp(closeWindow)
{
	// hide the popup
	if (asPopupPVWindow)
	{
		try
		{
			objectFocus(window.opener.document.getElementById(gblPromptFieldId));
		}
		catch (e)
		{
		}
		
		if (closeWindow)
		{
			window.close();
		}
	}
	else
	{
		pvPopup.hidePopup();
			
		// set the focus back to the field
		var obj  = document.getElementById(gblPromptFieldId);
		try
		{
			obj.focus();
		}
		catch(e1)
		{
		}
	}	  	
}

// **********************************************************************************
// function dt_kbdFilterFieldKeyPress(e,fieldId)
// Handle key press for the filter fields
//
// Parameters:
// e       - event
// fieldId - field field id
// **********************************************************************************
function dt_kbdFilterFieldKeyPress(e,fieldId)
{
	var keynum = rtvKeyboardKey(e);
	
	// enter key
	if (keynum==13)
	{
		// Display the progress bar					
		var obj = document.getElementById('PvPopUpWorking');
		obj.style.display = "";
		getNewPVList(gblPromptTitle,gblPromptFieldId,gblPromptId,gblPromptDecode,gblPromptSecurity,gblPromptFile,
					gblPromptKeys,gblReturnFieldNames,gblReturnFieldLabels,gblReturnFieldPositions,gblReturnFieldLengths,
					gblBackStart,gblBackLength,
					gblDataFields, gblDbFields, gblReturnFieldHdrPos, false,gblMaxResults);
	}
}
