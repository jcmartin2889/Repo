
// **********************************************************************************
// function setSinpButton(sSinpText, sSelText)
// Determine whether to display the option button or not
//
// Parameters:
// sSinpText: Field ID of the radio button
// sSelText: Field Name
// **********************************************************************************
function setSinpButton(sSinpText, sSelText)
{
	var widgetButtonHTML = new String();
	var onClickAction = new String();

	onClickAction = 'if(this.checked) { setFocusAndSubmitKey(\'document.SCREEN.' + sSelText + '\', \'1\', \'ENTER\', this);}';
	
	widgetButtonHTML = '<input type="radio" ' + 
				  		'name="' + sSinpText + '" ' +
						'id="' + sSinpText + '" ' +
						'value="1" '  +
						'onClick="' + onClickAction + '" ' +
						'onfocus="this.checked=true;" ' +
						'onblur="this.checked=false;" ' +
                        'onkeydown="return kbdSelKeyDown(event,\'' + sSinpText + '\',\'' + sSelText + '\')"' +
                        '>';

	// Check if reference field is input capable
	var obj = document.getElementById(sSelText);
	if(obj.readOnly == false)
	{
		document.write(widgetButtonHTML);
	}
}


// **********************************************************************************
// function kbdSelKeyDown(e, sSinpText, sSelText
// Handle up, arrow and enter key on a selection button
//
// Parameters:
// e:         event
// sSinpText: Field ID of the radio button
// sSelText: Field Name
// **********************************************************************************
function kbdSelKeyDown(e, sSinpText, sSelText)
{
	if(window.event) // IE
	{
		keynum = event.keyCode;
	}
	else if(e.which) // Netscape/Firefox/Opera
	{		
		keynum = e.which;
	}
	
	var obj = document.getElementById(sSinpText);

	// enter
	if (keynum==13)
	  {
	  	obj.checked = true;
	  	document.getElementById(sSelText).value = '1';
	  }

	// key up
	if (keynum==38)
	  {
	  	moveSubfile(sSelText,sSinpText,-1);
	    return false;	    	
	  }
	
	// key down
	if (keynum==40)
	  {
	  	moveSubfile(sSelText,sSinpText,1);
	    return false;	    	
	  }
	  
	// key left / right
	if (keynum==37 || keynum==39)
	  {
	  	return false;
	  }
	  
	// tab key
	if (keynum==9 && event.shiftKey==false && obj==objLastInputField)
	{
		moveToBottomFirstField(objLastInputField);
		return false;
	}
	
	// shift-tab key
	if (keynum==9 && event.shiftKey && obj==objFirstInputField)
	{
		moveToBottomLastField(objFirstInputField);
		return false;
	}
	
}

// **********************************************************************************
// function MoveSubfile(sSinpText,disp)
// Set the focus to another radio button
//
// Parameters:
// sSinpText: Field ID of the current radio button
// disp     : displacement from the current radio button
// **********************************************************************************
function moveSubfile(sSelText,sSinpText,disp)
{
	var idx, len, str, obj;
	
	// get length of string
	len = sSelText.length;
	
	// get first position of $
	idx = sSelText.indexOf('$');

	// get second position of $
	if (idx+1 < len)
	{
		idx = sSelText.indexOf('$',idx+1);
	}
	else 
	{
		idx = 0;
	}
	
	// get the number
	str = sSelText.substr(idx+1);
	num = parseInt(str) + disp;
	
	// get the next radio button
	str = sSinpText.substring(0, sSinpText.lastIndexOf(str));
	str = str + num;	
	obj = document.getElementById(str);
	if (obj != null) 
	  {
	  	obj.focus();
	  	obj.checked = true;

		obj2 = document.getElementById(sSinpText);
		obj2.checked = false;
	  }
}