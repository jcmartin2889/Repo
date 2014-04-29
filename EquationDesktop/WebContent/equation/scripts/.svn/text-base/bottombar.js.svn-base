var objBottomFirstInputField = null;
var objBottomLastInputField = null;
var gblBottomBarFrame = null;

//**********************************************************************************
// function insertIntoBottomBar
// Insert button to the bottombar
//
// Parameters:
// None
//**********************************************************************************
insertIntoBottomBar = function(bottomBar,bottomBarList,separator)
{
	// loop through the common buttons
	var buttonArray = bottomBarList;
	var buttonSize = buttonArray.length;
	var buttonSeq = -1;
	var cell;

	// anything to add?
	if (buttonSize <= 0)
	{
		return;
	}
	
	// add vertical line
	if (buttonSize>1 && separator)
	{
		var vertLine = bottomBar.insertCell(-1);
		vertLine.innerHTML = '&nbsp;';	
	}

	// add all the buttons. button definitions are expected to use inputButtonExt class
	gblBottomBarFrame = getFrameBottomBar();
	var calcWidth;
	for (buttonSeq = 0;buttonSeq < buttonSize; buttonSeq++) 
	{
		cell = bottomBar.insertCell(-1);
		if (gblBottomBarFrame.RTL)
		{
			cell.className+=' wf_LTOR wf_RIGHT_ALIGN';
		}
		cell.align="center";
		if( !calcWidth)
		{
			// Create a dummy button using fixed width class to determine minimum width
			cell.innerHTML='<input id=\"Test\" class=\"inputButton\" type=\"button\">';
			calcWidth = cell.clientWidth;
		}
		cell.innerHTML=buttonArray[buttonSeq];
		var button =  cell.children[0];
		if(button && button.clientWidth < calcWidth)
		{
			button.className = "inputButton";	// Ensure minimum button width
		}
	}
};


//**********************************************************************************
// function bottomBarFieldProcessing()
// Handle bottom bar field related processing after loading the web page
//
// Parameters:
// None
//**********************************************************************************
function bottomBarFieldProcessing()
{
	objBottomFirstInputField = null;
	objBottomLastInputField = null;

	// retrieve the list of input fields
	gblBottomBarFrame = getFrameBottomBar();
	var docID = gblBottomBarFrame.document;
	var objList = docID.getElementsByTagName("input");
	
	// set the onKeyDown of the first input field
	for(var i=0; i<objList.length; i++)
	{
		if(isInputField(objList[i]))
		{
			objBottomFirstInputField = objList[i];
			if(objList[i].onkeydown==null)
			{
				objList[i].onkeydown = firstBottomInputFieldKeyDown;
			}
			break;
		}
	}

	// set the onKeyDown of the last input field
	for(var i=objList.length-1; i>=0; i--)
	{
		if(isInputField(objList[i]))
		{
			// set onkeydown
			objBottomLastInputField = objList[i];
			if(objList[i].onkeydown==null)
			{
				objList[i].onkeydown = lastBottomInputFieldKeyDown;
			}
			break;
		}
	}	
	
	// always re-enable on load
	showAfterSubmitBottomBar();	
}


//**********************************************************************************
// function firstBottomInputFieldKeyDown(e)
// Handle key down event for the first input field
//
// Parameters:
// e:         event
//**********************************************************************************
function firstBottomInputFieldKeyDown(e)
{
	if(window.event) // 
	{
		keynum = event.keyCode;
	}
	else if(e.which) // Netscape/Firefox/Opera
	{		
		keynum = e.which;
	}

	// shift tab
	if (keynum==9 && event.shiftKey)
	{
		moveToLastField(this);
		disableKeyboardKey(e);
		return false;
	}
	
	// tab
	if (keynum==9 && event.shiftKey==false && objBottomFirstInputField==objBottomLastInputField)
	{
		moveToFirstField(this);
		disableKeyboardKey(e);
		return false;
	}

}


//**********************************************************************************
// function lastBottomInputFieldKeyDown(e)
// Handle key down event for the last input field
//
// Parameters:
// e:         event
//**********************************************************************************
function lastBottomInputFieldKeyDown(e)
{
	if(window.event) // 
	{
		keynum = event.keyCode;
	}
	else if(e.which) // Netscape/Firefox/Opera
	{		
		keynum = e.which;
	}

	// tab
	if (keynum==9 && event.shiftKey == false)
	{
		moveToFirstField(this);
		disableKeyboardKey(e);
		return false;
	}

	// one input field only
	if (keynum==9 && event.shiftKey && objBottomFirstInputField==objBottomLastInputField)
	{
		moveToLastField(this);
		disableKeyboardKey(e);
		return false;
	}

}



// **********************************************************************************
// function moveToFirstField(currentField)
// Move the focus to the first field
//
// Parameters:
// currentField - current object with the focus
// **********************************************************************************	
function moveToFirstField(currentField)
{
	var currentFrame = getFrameCurrent();
	currentField.blur();
	
	if (currentFrame.objFirstInputField != null)
	{
		var ok = objectFocus(currentFrame.objFirstInputField);
		if (!ok)
		{
			positionToFirstInputField();
		}
	}
	else
	{
		objBottomFirstInputField.focus();
	}
}


// **********************************************************************************
// function moveToLastField(currentField)
// Move the focus to the last field
//
// Parameters:
// currentField - current object with the focus
// **********************************************************************************	
function moveToLastField(currentField)
{
	var currentFrame = getFrameCurrent();
	currentField.blur();
	
	if (currentFrame.objLastInputField != null)
	{
		var ok = objectFocus(currentFrame.objLastInputField);
		if (!ok)
		{
			positionToLastInputField();
		}
	}
	else
	{
		objBottomLastInputField.focus();
	}
}

//**********************************************************************************
// function botbar_bodyKeyDown(e)
// Handle key down event for the bottom bar
//
// Parameters:
// e - the event
//**********************************************************************************	
function botbar_bodyKeyDown(e)
{
	frame = getFrameCurrent();
	if (frame.name == 'desktopFrame')
	{
		var keynum = rtvKeyboardKey(e);
		if (keynum!=13 && isSpecialKey(keynum))
		{
			frame.dt_bodyKeyDown(e);
			disableKeyboardKey(e);
			return false;
		}
	}
	
	return true;
	
}

//**********************************************************************************
// function botbar_onfocus(event)
// Handle on focus event for the bottom bar
//
//Parameters:
//e - the event
//**********************************************************************************	
function botbar_onfocus(e)
{
	try
	{
		gblBottomBarFrame = getFrameBottomBar();
		var gblCurrentFrame = getFrameCurrent();
		if (gblCurrentFrame.tabbing==2)
		{
			gblBottomBarFrame.objBottomFirstInputField.focus();
			gblCurrentFrame.tabbing = 0;
		}
	}
	catch(e1)
	{
	}
	return true;
}

//**********************************************************************************
// function hideOnSubmitBottomBar()
// Disable the bottom bar when page is submitted
//
// Parameters:
// None
//**********************************************************************************	
function hideOnSubmitBottomBar()
{
	// change mouse cursor to hourglass on the bottombar
	gblButtonbarFrame = getFrameBottomBar();
	gblButtonbarFrame.document.body.style.cursor='wait';
	
	gblButtonbarFrame.document.getElementById('EqBottomBarDiv').disabled = true;
	gblButtonbarFrame.document.getElementById('footerTexts').disabled = true;
}


//**********************************************************************************
// function showAfterSubmitBottomBar()
// Re-enable the bottom bar when page is submitted
//
// Parameters:
// None
//**********************************************************************************	
function showAfterSubmitBottomBar()
{
	// change mouse cursor to hourglass on the bottombar
	gblButtonbarFrame = getFrameBottomBar();
	gblButtonbarFrame.document.body.style.cursor='default';
	
	gblButtonbarFrame.document.getElementById('EqBottomBarDiv').disabled = false;
	gblButtonbarFrame.document.getElementById('footerTexts').disabled = false;
}