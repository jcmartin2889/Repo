var vOptPopupInput=null;
var vOptPopup = null;
var kbdVOptPopup = null;
var inputValue = '';
var vOptZindex = 5;

// Determine the last valid option click
var lastVOptionClick = null;



//**********************************************************************************
// function setVOptionButton(listType,listInputFieldId,validValues)
// Keyboard key handler for the keyboard-widget popup
//
// Parameters:
// listType: List type
// listInputFieldId: Field ID
// validValues: list of valid values
//**********************************************************************************
function setVOptionButton(listType,listInputFieldId,validValues)
{
	// special processing for linked functions
	validValues = getSpecialLinkedFunctionValidValues(validValues);
	
	var hoverText = getLanguageLabel('GBL000096');
	var listInputFieldButtonId = listInputFieldId + BUT_VOPTION;
	var onClickAction = 'activateVOptPopup(document.getElementById(\'' +  listInputFieldId + '\'),\'' + listInputFieldButtonId + '\',\'' + validValues + '\');';
	var imagePath = '/' + getWebAppName() + '/equation/images/list.gif';

	// WebFacing, then do not display the button if read-only
	var obj = document.getElementById(listInputFieldId);
	if(isWebFacing() && obj.readOnly)
	{
		return;
	}
	
	// add the button
	var widgetButtonHTML = addWidgetButton(hoverText,listInputFieldButtonId,onClickAction,imagePath,'promptWidget');
	
	// protected - then hide the widget 
	if (obj.readOnly)
	{
		disableAnchor(listInputFieldButtonId + BUT_HREF, true);
		visibleObj(listInputFieldButtonId, false);
	}
	
	return widgetButtonHTML;
}

// **********************************************************************************
// function activateVOptPopup(obj,anchor,validValues)
// Activate the VOption Popup
//
// Parameters:
// obj: The popup object 
// anchor: The anchor for the popup
// validValues: list of valid values
// **********************************************************************************	
function activateVOptPopup(obj,anchor,validValues)
{
	// determine if same button has been clicked again
	if (isClickSameField(obj, lastVOptionClick))
	{
		return;
	}
	
	// store this
	lastVOptionClick = obj;
	
	// Create the PopupWindow object		
	vOptPopup = new PopupWindow('vOptPopupId');
	createVOptListStructure(obj, validValues);
   	vOptPopup.autoHide();

   	// Reference the input
	vOptPopupInput=obj;

	// Save the Original input
	inputValue = obj.value;		

	// Display the popup
	if (isUXP())
	{
		vOptPopup.showPopupBelow(obj.id);
	}
	else
	{
		vOptPopup.showPopup(anchor);
	}
	
	// select the right radio button
	setVOptListValue(obj);	
}

// **********************************************************************************
// function setVOptListValue(listInput)
// Set the VOption Widget's value
//
// Parameters:
// listInput: The Input Box
// **********************************************************************************
function setVOptListValue(listInput)
{			
	var num = listInput.value;
	var radioVOptElement = document.getElementById('vOpt' + listInput.value);
	try
	{
		radioVOptElement.checked = true;
		radioVOptElement.focus();
	  	current_selected = radioVOptElement;
	}
	catch(e)
	{
		var radioVOptElementNone = document.getElementById('vOptNone');
		radioVOptElementNone.checked = true;
		radioVOptElementNone.focus();
	  	current_selected = radioVOptElementNone;
	}
}

// **********************************************************************************
// function vOptionKeyPress(event,obj,anchor,vOptKeyLnIn1,vOptKeyLnIn2)
// Bring up the keyboard widget with ctrl+space if pressed
//
// Parameters:
// event: The Key press event
// obj: The popup object 
// anchor: The anchor for the popup
// validValues: list of valid values
// **********************************************************************************
function vOptionKeyPress(event,obj,anchor,validValues)
{		
	// Check if reference field is input capable
	if(obj.readOnly == true)
	{
		return;
	}

	if (event != null)
	{
		var keynum = rtvKeyboardKey(event);

		// Ctrl-space / F4
		if ( (keynum==115 && !isShiftKey(e) && !isAltKey(e) && !isCtrlKey(e)) ||
				 (keynum == 32 && isCtrlKey(e) && !isShiftKey(e) && !isAltKey(e)) 
			)
		{
			activateVOptPopup(obj, anchor+ BUT_VOPTION, validValues);			
		}
	}
}

// **********************************************************************************
// function createKbdVOptListStructure(inputObj, validValues)
// Bring up the keyboard widget with ctrl+space if pressed
//
// Parameters:
// inputObj   : the object
// validValues: list of valid values
// **********************************************************************************
function createKbdVOptListStructure(inputObj, validValues)
{
 	  var vOptKeyLbl = new String();
      var vOptKbdHTML = new String();

	  // Create a div element for this keyboard widget
      vOptKbdDiv = document.createElement('div');
      vOptKbdDiv.id = "kbdVOptPopupId";
      vOptKbdDiv.className = 'widgetContainerDIV';    
      vOptKbdDiv.style.zIndex = vOptZindex;
      document.body.appendChild(vOptKbdDiv);        

	  // RTL
	  if (RTL)
	  {
		  vOptKbdDiv.className += ' wf_RTOL';
	  }

	  var list = validValues.split(CONTEXT_DELIMETER);
	  for (i=0; i<list.length; i++)
	  {
		  vOptKbdHTML += '<option value="'+ list[i] + '">' + list[i] + '</option>';
      }
      
      // set vOptlength to maximum of 10 if needed
	  vOptlength = list.length;
      if (vOptlength > 10)
      {
      	vOptlength = 10;
      }      

      var listKbdHTML = '';
      if (getAttribute(inputObj,'mandatory') != YES)
      {
    	  listKbdHTML = '<option value="">' + getLanguageLabel("GBLNONE") + '</option>';
    	  vOptlength++;
      }

      // Build the widget container
	  // RTL?
      var strRTL = '>';
      if (RTLOrg)
      {
    	  strRTL = 'class="wf_LTOR wf_RIGHT_ALIGN">';
      }
	
	    popupHTML = 
	    '	<div id="kbdVOptWidgetContainer">' +			
		'		<div id = "kbdVOptWidgetBody">' +
		'			<table id="kbdVOptList">' +
		'				<tr>' +
		'					<td ' + strRTL + 
								'<select id="kbdVOpt" size="' + vOptlength + '"' + 
                                   ' onclick="kbdVOptPopupPick();"' +
                                   ' onkeypress="return kbdVOptKeyPress(event)"' +
                                   ' onkeydown="return kbdVOptKeyDown(event)"' +
                                   '>' + 
                                listKbdHTML +
								vOptKbdHTML +
								'</select>' +
		'					</td>' +
		'				</tr>' +
		'			</table>' +
		'		</div>' +		
		'	</div>';		 
      
      kbdVOptPopup.populate(popupHTML);
}

// **********************************************************************************
// function kbdVOptKeyPress(e)
// Keyboard key handler for the keyboard-widget popup
//
// Parameters:
// e: The event that triggered the listener
// **********************************************************************************
function kbdVOptKeyPress(e)
{	
	if(window.event) // IE
	{
		keynum = e.keyCode;
	}
	else if(e.which) // Netscape/Firefox/Opera
	{		
		keynum = e.which;
	}
	
	// Enter
	if(keynum == 13)
	{	
		kbdVOptPopupPick();
		disableKeyboardKey(e);
		return false;
	}	
	
	// Escape key
	if(keynum == 27)
	{ 		
		//Hide the popup
		kbdVOptPopup.hidePopup();
		isClickSameField(1,2);
		
		//Focus back on input field
		vOptPopupInput.focus(); 
	}	
}


// **********************************************************************************
// function createVOptListStructure(listStructureTitle,listStructureVal,listStructureDsc,vOptKeyLnIn)
// Create the vOption list widget structure
//
// Parameters:
// inputObj - the input object
// validValues - list of valid values
// **********************************************************************************
function createVOptListStructure(inputObj, validValues)
{
      var vOptKeyLbl = new String();
      var vOptHTML = new String();
		
	  // RTL?
	  var strRTL = '>';
	  if (RTLOrg)
	  {
		  strRTL = 'class="wf_LTOR wf_RIGHT_ALIGN">';
	  }

	  var list = validValues.split(CONTEXT_DELIMETER);
	  for (i=0; i<list.length; i++)
	  {
          vOptHTML += 
            	'<tr>' +
            	'<td>' +
            	'<input type="radio" name="vOpt" value="' + list[i] + ' "id="vOpt' + list[i] + '" ' + 'onclick="vOptPopup_onclick(event,this,\'' + list[i] + '\');">' + 
            	'</td>' +
            	'<td ' + strRTL +
            	list[i] + 
            	'</td>' +
            	'</tr>';
      }
	  
	  	var noneHTML = '';
	  	if (getAttribute(inputObj,'mandatory') != YES)
	  	{
	  		noneHTML =  
					'<tr>' +
                  	'<td>' +
					'<input type="radio" name="vOpt" value=" "id="vOptNone"  onclick="vOptPopup_onclick(event,this,\'\');">' + 
                  	'</td>' +
                  	'<td ' + strRTL + 
					getLanguageLabel("GBLNONE").trim() ;
                  	'</td>' +
                  	'</tr>';
	  	}

		var strClass = 'widgetContainer';
		if (isUXP())
		{
			strClass = 'widgetContainerBelow';
		}
		
        // Build the widget container
	    popupHTML = 
	    '	<div class="' + strClass + '" id="vOptWidgetContainer" onkeydown="containerVOptKeyDown(event)" onclick="containerVOptOnClick(event)" >'+			
		'		<div class="widgetBody" id = "vOptWidgetBody">'+
		'			<table id="vOptList">' +
                        noneHTML + 
						vOptHTML +
		'			</table>' +
		'		</div>' +
		'	</div>';		 
      
	  // We need a divvie
	  vOptDiv = document.getElementById('vOptPopupId');
	  if (vOptDiv == null)
	  {
	      vOptDiv = document.createElement('div');
	      vOptDiv.id = "vOptPopupId";
	      vOptDiv.className = 'widgetContainerDIV';
	      vOptDiv.style.zindex = vOptZindex;
	      document.body.appendChild(vOptDiv);
	  }
	  vOptDiv.style.display = "";
      vOptDiv.innerHTML = popupHTML;
      //vOptPopup.populate(popupHTML);
      
	  //Get size of the list and adjust the widget
	if (RTLOrg)
	{
		var testwidth = document.getElementById('vOptList').clientWidth;
		var testheaderwidth = document.getElementById('vOptWidgetBody').offsetWidth;
		if(testheaderwidth>testwidth)
		{
			testwidth = testheaderwidth;	
		}
		document.getElementById('vOptList').style.width = testwidth +'px';	  
		document.getElementById('vOptWidgetBody').style.width = testwidth +'px';
	}
}

// **********************************************************************************
// function vOptPopupPick(val)
// Set the input box with a value
//
// Parameters:
// val: The value to set 
// **********************************************************************************
function vOptPopupPick(val)
{	
      vOptPopupInput.value = val.substr(0,vOptPopupInput.maxLength);
      vOptPopup.hidePopup();
      vOptPopupInput.focus();      
      isClickSameField(1,2);
}    


//**********************************************************************************
// function vOptPopup_onclick(e, item)
// On click event for the radio button
//
// Parameters:
// e    - event
// this - radio button
// item - item selected
//**********************************************************************************
function vOptPopup_onclick(e, radioObj, item)
{
	// action only if not due to keyboard up/down
	if (!pending_keyboard && radioObj.checked) 
	{
		vOptPopupPick(item);
	}
	
	// currently selected radio button
	current_selected = radioObj;
	
	// reset
	pending_keyboard = false;
}    



// **********************************************************************************
// function kbdVOptPopupPick()
// Set the input box with a value
//
// Parameters: none
// ********************************************************************************** 
function kbdVOptPopupPick()
{	
      var selectEle = document.getElementById("kbdVOpt");	
      vOptPopupInput.value = selectEle.value.substr(0,vOptPopupInput.maxLength);
      kbdVOptPopup.hidePopup();
      vOptPopupInput.focus();      
      isClickSameField(1,2);
}   

// only deals with F4 
function vOptKeyDown(e,anchor,vOptKeyLnIn1,vOptKeyLnIn2)
{
	textBox = document.getElementById(anchor);	
	if(window.event) // IE
	{
		keynum = event.keyCode;
	}
	else if(e.which) // Netscape/Firefox/Opera
	{		
		keynum = e.which;
	}


	//F4 allows prompt also
	if(keynum == 115)
	{ 				
		if( document.all )
	    {
	    	// IE
	    	event.cancelBubble = true;	    	
	       	vOptionKeyPress(event,textBox,anchor,vOptKeyLnIn1,vOptKeyLnIn2);
	    }
	    else
	    {
	    	// Non IE
	    	e.cancelBubble = true;	    	
	        vOptionKeyPress(e,textBox,anchor,vOptKeyLnIn1,vOptKeyLnIn2);
	   	}
		disableKeyboardKey(e);
		return false;
   	}
   	
	// tab key
	if (keynum==9 && event.shiftKey==false && textBox==objLastInputField)
	{
		moveToBottomFirstField(objLastInputField);
		return false;
	}
	
	// shift-tab key
	if (keynum==9 && event.shiftKey && textBox==objFirstInputField)
	{
		moveToBottomLastField(objFirstInputField);
		return false;
	}
   	
}


function vOptKeyPress(e,anchor,vOptKeyLnIn1,vOptKeyLnIn2)
{
	if (e.keyCode != 115)
	{ // fix for letter 'S' 
		textBox = document.getElementById(anchor);	

		if( document.all )
	    {
    		// IE
       		vOptionKeyPress(event,textBox,anchor,vOptKeyLnIn1,vOptKeyLnIn2);
	    }
	    else
    	{    	
	    	// Non IE
    	    vOptionKeyPress(e,textBox,anchor,vOptKeyLnIn1,vOptKeyLnIn2);
	   	}		
   	}
}


function kbdVOptKeyDown(e)
{
	if(window.event) // IE
	{
		keynum = e.keyCode;
	}
	else if(e.which) // Netscape/Firefox/Opera
	{		
		keynum = e.which;
	}

	// backspace - disable
	if(keynum == 8)
	{ 		
		disableKeyboardKey(e);
		return false;
	}
	
	if(keynum == 9) //tab
	{ 		
		//Hide the popup
		kbdVOptPopup.hidePopup();
		isClickSameField(1,2);
		
		//Focus back on input field
		vOptPopupInput.focus();
		if(vOptPopupInput == objLastInputField) 
		{
			return false;
		}
	}	

	// Enter
	if(keynum == 13)
	{	
		kbdVOptPopupPick();
		disableKeyboardKey(e);
		return false;
	}	
}

function getFunctionKeyText(sLine1, sLine2)
{
	var sLine = new String();

	// Command key 1, sometimes we pass the plain text!
	if (sLine1.indexOf("$")==-1)
	{
		sLine = sLine1.rpl160s();
	}
	else
	{
		sLine = document.getElementById(sLine1).lastChild.data.rpl160s();
	}

	// Command key 2, sometimes we pass the plain text!
	if (sLine2.indexOf("$")==-1)
	{
		sLine += ' ' + sLine2.rpl160s();
	}
	else
	{
		sLine += ' ' + document.getElementById(sLine2).lastChild.data.rpl160s();
	}

	return sLine;
}

function parseFunctionKey(text)
{
	var vOptionArray = new Array();
	
	// first equal sign
	var equalIndex = text.indexOf('=');
	
	// while there are = sign
	while (equalIndex >= 0)
	{
		// next location of next equal sign
		var equalIndex2 = text.indexOf('=', equalIndex + 1);
		
		// retrieve the start of text for the first equal sign
		var leftIndex = text.lastIndexOf(' ',equalIndex);

		// retrieve the end of text for the first equal sign
		if (equalIndex2 >= 0)
		{
			leftIndex2 = text.lastIndexOf(' ', equalIndex2);
		}
		else
		{
			leftIndex2 = text.length;
		}
		
		// retrieve it
		vOptionArray[vOptionArray.length] = text.substr(leftIndex,leftIndex2).trim();
		
		// on to next =
		equalIndex = equalIndex2;
	}
	
	// return the array of function key
	return vOptionArray;
	
}	


function isSpecialLinkedFunctionValidValues(validValues)
{
	if (validValues.indexOf(CONTEXT_DELIMETER)==-1 && validValues.endsWith(REPGROUP_OPTION2))
	{
		return true;
	}
	return false;
}


function getSpecialLinkedFunctionValidValues(validValues)
{
	if (isSpecialLinkedFunctionValidValues(validValues))
	{
		return eval(validValues);
	}
	else
	{
		return validValues;
	}
}


//**********************************************************************************
// function vOptKeyDown(e)
// On key down event
//
// Parameters:
// e - event
//**********************************************************************************
function containerVOptKeyDown(e)
{
	var keynum = rtvKeyboardKey(e);
	
	// escape/tab
	if (keynum==27 || keynum==9)
	{
	    vOptPopup.hidePopup();
	    vOptPopupInput.focus();
		disableKeyboardKey(e);
		isClickSameField(1,2);
		return false;
	}
	
	// enter
	else if (keynum==13)
	{
		vOptPopupPick(current_selected.value.trim());
		disableKeyboardKey(e);
		return false;
	}
	
	// cursor up/down/left/right
	else if (keynum==38 || keynum==40 || keynum==37 || keynum==39)
	{
		pending_keyboard = true;
		return false;
	}
	
}


//**********************************************************************************
// function containerVOpttOnClick(e)
// On click down event
//
// Parameters:
// e - event
//**********************************************************************************
function containerVOptOnClick(e)
{
	disableKeyboardKey(e);
	return false;
}