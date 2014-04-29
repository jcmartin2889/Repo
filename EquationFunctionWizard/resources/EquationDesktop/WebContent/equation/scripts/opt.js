
// **********************************************************************************
// OPTION PICKER
// **********************************************************************************
// . (**LANGUAGE)

var optPopupInput=null;
var optPopup = null;
var kbdOptPopup = null;
var inputValue = '';
var listCreated = 'false';
var kbdlistCreated = 'false';
var optZindex = 5;
var pending_keyboard = false;
var current_selected = null;

//Determine the last valid option click
var lastOptionClick = null;

// **********************************************************************************
// function activateOptPopup(obj,anchor,optKeyLnIn1,optKeyLnIn2)
// Activate the Option Popup
//
// Parameters:
// obj: The popup object 
// anchor: The anchor for the popup
// optKeyLnIn1: First line of Function Key/Options text
// optKeyLnIn2: Second line of Function Key/Options text
// **********************************************************************************	

function activateOptPopup(obj,anchor,optKeyLnIn1,optKeyLnIn2)
{
	// determine if same button has been clicked again
	if (isClickSameField(obj, lastOptionClick))
	{
		return;
	}
	
	// store this
	lastOptionClick = obj;
	
	var optKeyLnIn;

	optKeyLnIn = getFunctionKeyText(optKeyLnIn1, optKeyLnIn2);

	// Create the popup
	if (listCreated == 'false')
	{		
		// Create the PopupWindow object		
      	optPopup = new PopupWindow('optPopupId');
      	kbdOptPopup = new PopupWindow('kbdOptPopupId');     	
		// We need to find all the options and functions within the command key label line, we do this by finding "=" and cutting it up.
   		createOptListStructure(optKeyLnIn);
	   	optPopup.autoHide();
		listCreated = 'true';
	}
	
	// Reference the input
	optPopupInput=obj;
	
	// Save the Original input
	inputValue = obj.value;
	
	// display
	if (isUXP())
	{
		optPopup.showPopupBelow(obj.id);
	}
	else
	{
		optPopup.showPopup(anchor);
	}
	
	// select the right radio button  	
	setOptListValue(obj,optKeyLnIn);	
}

// **********************************************************************************
// function setOptListValue(listInput,optKeyLnIn)
// Set the Option Widget's value
//
// Parameters:
// listInput: The Input Box
// optKeyLnIn: Concatenated string of the option keys
// **********************************************************************************
function setOptListValue(listInput,optKeyLnIn)
{			
	  var num = listInput.value;
	  var radioOptElement = document.getElementById('opt' + listInput.value);
	  try
	  {
	  	radioOptElement.checked = true;
	  	radioOptElement.focus();
	  	current_selected = radioOptElement;
	  }

	  //set none
	  catch(e)
	  {
	  	var radioOptElementNone = document.getElementById('optNone');
	  	radioOptElementNone.checked = true;
	  	radioOptElementNone.focus();
	  	current_selected = radioOptElementNone;
	  }
}

// **********************************************************************************
// function optionKeyPress(event,obj,anchor,optKeyLnIn1,optKeyLnIn2)
// Bring up the keyboard widget with ctrl+space if pressed
//
// Parameters:
// event: The Key press event
// obj: The popup object 
// anchor: The anchor for the popup
// optKeyLnIn1: First line of Function Key/Options text
// optKeyLnIn2: Second line of Function Key/Options text
// **********************************************************************************
function optionKeyPress(event,obj,anchor,optKeyLnIn1,optKeyLnIn2)
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
			activateOptPopup(obj, anchor + BUT_OPTION, optKeyLnIn1, optKeyLnIn2);			
			disableKeyboardKey(e);
			return false;
		}
	}
}


// **********************************************************************************
// function createKbdOptListStructure(listStructureTitle,listStructureVal,listStructureDsc,optKeyLnIn)
// Bring up the keyboard widget with ctrl+space if pressed
//
// Parameters:
// listStructureTitle: Title of list structure
// listStructureVal: Value of list structure
// listStructureDsc: Description of list structure
// optKeyLnIn: String of all Function Key/Options text
// **********************************************************************************
function createKbdOptListStructure(optKeyLnIn)
{
 	  var optKeyLbl = new String();
      var optKbdHTML = new String();
      var currentE = -1;
      var currentEnd = -1;
      var currentStart = -1;
      var nextE = -1;	
      var optlength = 1;

	  // Create a div element for this keyboard widget
      optKbdDiv = document.createElement('div');
      optKbdDiv.id = "kbdOptPopupId";
      optKbdDiv.className = 'widgetContainerDIV';    
      optKbdDiv.style.zIndex = optZindex;
      document.body.appendChild(optKbdDiv);        

	  // RTL
	  if (RTL) 
	  {
		  optKbdDiv.className += ' wf_RTOL';
	  }
            
      currentE = optKeyLnIn.indexOf('=');
      currentStart = 0;
      while(optKeyLnIn.substring(currentStart,currentStart + 1) == ' ')
      {
            currentStart++;
      }
      while(currentE != -1)
      {      
            nextE = optKeyLnIn.indexOf('=',currentE + 1);
            if(nextE == -1)
            {
                  currentEnd = optKeyLnIn.length;
            }
            else
            {
                  currentEnd = optKeyLnIn.lastIndexOf(' ',nextE);
            }
            
            // Only interested in providing radio buttons for options
            if(   optKeyLnIn.substring(currentStart,currentStart + 1) != "F" ||
                  ((currentStart+1) == currentE)  )
            {            
                  // Find out which option we have
                  var okey = new String();
                  okey = optKeyLnIn.substring(currentStart,currentE);
                  optlength++;			
                  // Get the option key label                 
                  optKeyLbl = optKeyLnIn.substring(currentE + 1,currentEnd).trim();                  
                  optKbdHTML += '<option value="'+ okey + '">' + optKeyLbl + '</option>';
            }
            
            //Move onto the next one.
            currentE = nextE;
            currentStart = currentEnd + 1;
      }
      
      	// set optlength to maximum of 10 if needed
      	if (optlength > 10)
      	{
      		optlength = 10;
      	}      

		var listKbdHTML = '<option value="">' + getLanguageLabel("GBLNONE") + '</option>';
		  	
		// Build the widget container
	    // RTL?
		var strRTL = '>';
		if (RTLOrg) 
		{
			strRTL = 'class="wf_LTOR wf_RIGHT_ALIGN">';
		}
	
	    popupHTML = 
	    '	<div id="kbdOptWidgetContainer">'+			
		'		<div id = "kbdOptWidgetBody">'+
		'			<table id="kbdOptList">' +
		'				<tr>' +
		'					<td ' + strRTL + 
								'<select id="kbdOpt" size="' + optlength + '"' + 
                                   ' onclick="kbdOptPopupPick();"' +
                                   ' onkeypress="return kbdOptKeyPress(event)"' +
                                   ' onkeydown="return kbdOptKeyDown(event)"' +
                                   '>' + 
                                listKbdHTML +
								optKbdHTML +
								'</select>' +
		'					</td>' +
		'				</tr>' +
		'			</table>' +
		'		</div>' +		
		'	</div>';		 
      
      kbdOptPopup.populate(popupHTML);   
}

// **********************************************************************************
// function kbdOptKeyPress(e)
// Keyboard key handler for the keyboard-widget popup
//
// Parameters:
// e: The event that triggered the listener
// **********************************************************************************
function kbdOptKeyPress(e)
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
		kbdOptPopupPick();
	}	
	
	// Escape key
	if(keynum == 27)
	{
		//Hide the popup
		kbdOptPopup.hidePopup();
		isClickSameField(1,2);
		
		//Focus back on input field
		optPopupInput.focus();
		
	}	
}


// **********************************************************************************
// function createOptListStructure(listStructureTitle,listStructureVal,listStructureDsc,optKeyLnIn)
// Create the option list widget structure
//
// Parameters:
// listStructureTitle: Title of list structure
// listStructureVal: Value of list structure
// listStructureDsc: Description of list structure
// optKeyLnIn: String of all Function Key/Options text
// **********************************************************************************
function createOptListStructure(optKeyLnIn)
{	 	
	var optKeyLbl = new String();
	var optHTML = new String();
	var currentE = -1;
	var currentEnd = -1;
	var currentStart = -1;
	var nextE = -1;	
		
	// RTL?
	var strRTL = '>';
	if (RTLOrg) 
	{
		strRTL = 'class="wf_LTOR wf_RIGHT_ALIGN">';
	}

	currentE = optKeyLnIn.indexOf('=');
	currentStart = 0;
	while(optKeyLnIn.substring(currentStart,currentStart + 1) == ' ')
	{
		currentStart++;
	}
	while(currentE != -1)
	{      
		nextE = optKeyLnIn.indexOf('=',currentE + 1);
		if(nextE == -1)
		{
			currentEnd = optKeyLnIn.length;
		}
		else
		{
			currentEnd = optKeyLnIn.lastIndexOf(' ',nextE);
		}
          
		// Only interested in providing radio buttons for options - not Function key
		if(optKeyLnIn.substring(currentStart,currentStart + 1) != "F" || ((currentStart+1) == currentE))
		{            
			// Find out which option we have
			var okey = new String();
			okey = optKeyLnIn.substring(currentStart,currentE);			
			// Get the option key label
			optKeyLbl = optKeyLnIn.substring(currentE + 1,currentEnd).trim();
			optHTML += 
						'<tr>' +
	                	'<td>' +
	                	'<input type="radio" name="opt" value="' + okey + ' "id="opt' + okey + '" ' + 'onclick="optPopup_onclick(event,this,\'' + okey +'\');">' + 
	                	'</td>' +
	                	'<td ' + strRTL +
	                	optKeyLbl + 
	                  	'</td>' +
	                  	'</tr>';
		}
            
		//Move onto the next one.
		currentE = nextE;
		currentStart = currentEnd + 1;
	}      

	var noneHTML =  
					'<tr>' +
                  	'<td>' +
					'<input type="radio" name="opt" value=" "id="optNone"  onclick="optPopup_onclick(event,this,\'\');">' + 
                  	'</td>' +
                  	'<td ' + strRTL + 
					getLanguageLabel("GBLNONE").trim() ;
                  	'</td>' +
                  	'</tr>';
    
   	// Build the widget container
	var strClass = 'widgetContainer';
	if (isUXP())
	{
		strClass = 'widgetContainerBelow';
	}
                  	
	popupHTML = 
	    '	<div class="' + strClass + '" id="optWidgetContainer" onkeydown="containerOptKeyDown(event)" onclick="containerOptOnClick(event)">'+			
		'		<div class="widgetBody" id = "optWidgetBody">'+
		'			<table id="optList">' +
                        noneHTML + 
						optHTML +
		'			</table>' +
		'		</div>' +
		'	</div>';		 
      
	// We need a divvie
	optDiv = document.createElement('div');
	optDiv.id = "optPopupId";
	optDiv.className = 'widgetContainerDIV';
	optDiv.style.zindex = optZindex;
	optDiv.innerHTML = popupHTML;
	document.body.appendChild(optDiv);
      
	//Get size of the list and adjust the widget
	if (RTLOrg)
	{
		var testwidth = document.getElementById('optList').clientWidth;
		var testheaderwidth = document.getElementById('optWidgetBody').offsetWidth;
		if(testheaderwidth>testwidth)
		{
			testwidth = testheaderwidth;	
		}
		document.getElementById('optList').style.width = testwidth +'px';	  
		document.getElementById('optWidgetBody').style.width = testwidth +'px';
	}
}

// **********************************************************************************
// function optPopupPick(val)
// Set the input box with a value
//
// Parameters:
// val: The value to set 
// **********************************************************************************
function optPopupPick(val)
{	
	optPopupInput.value = val;
	optPopup.hidePopup();
	optPopupInput.focus();      
	isClickSameField(1,2);
}    

//**********************************************************************************
// function optPopup_onclick(e, item)
// On click event for the radio button
//
// Parameters:
// e    - event
// this - radio button
// item - item selected
//**********************************************************************************
function optPopup_onclick(e, radioObj, item)
{
	// action only if not due to keyboard up/down
	if (!pending_keyboard && radioObj.checked) 
	{
		optPopupPick(item);
	}
	
	// currently selected radio button
	current_selected = radioObj;
	
	// reset
	pending_keyboard = false;
}    


// **********************************************************************************
// function kbdOptPopupPick()
// Set the input box with a value
//
// Parameters: none
// ********************************************************************************** 
function kbdOptPopupPick()
{	
	var selectEle = document.getElementById("kbdOpt");	
	optPopupInput.value = selectEle.value;
	kbdOptPopup.hidePopup();
	isClickSameField(1,2);
	optPopupInput.focus();      
}   

// **********************************************************************************
// function setOptionButton(listType,listInputFieldId,fKeyLine1,fKeyLine2)
// Keyboard key handler for the keyboard-widget popup
//
// Parameters:
// listType: List type
// listInputFieldId: Field ID
// fKeyLine1: Function Keys Line 1
// fKeyLine2: Function Keys Line 2
// **********************************************************************************
function setOptionButton(listType,listInputFieldId,fKeyLine1,fKeyLine2)
{
		// *LANGUAGE
		var hoverText = getLanguageLabel('GBL000095');
		var listInputFieldButtonId = listInputFieldId + BUT_OPTION;
		var onClickAction = 'activateOptPopup(document.getElementById(\'' +  listInputFieldId + '\'),\'' + listInputFieldButtonId + '\',\'' + fKeyLine1 + '\',\'' + fKeyLine2 + '\');';
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


// only deals with F4 
function optKeyDown(e,anchor,optKeyLnIn1,optKeyLnIn2)
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
	if(keynum == 115 && !isShiftKey(e))
	{ 				
		if( document.all )
	    {
	    	// IE
	    	event.cancelBubble = true;	    	
	       	optionKeyPress(event,textBox,anchor,optKeyLnIn1,optKeyLnIn2);
	    }
	    else
	    {
	    	// Non IE
	    	e.cancelBubble = true;	    	
	        optionKeyPress(e,textBox,anchor,optKeyLnIn1,optKeyLnIn2);
	   	}
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


function optKeyPress(e,anchor,optKeyLnIn1,optKeyLnIn2)
{
	if (e.keyCode != 115)
	{ // fix for letter 'S' 
		textBox = document.getElementById(anchor);	

		if( document.all )
	    {
    		// IE
       		optionKeyPress(event,textBox,anchor,optKeyLnIn1,optKeyLnIn2);
	    }
	    else
    	{    	
	    	// Non IE
    	    optionKeyPress(e,textBox,anchor,optKeyLnIn1,optKeyLnIn2);
	   	}		
   	}
}


function kbdOptKeyDown(e)
{
	if(window.event) // IE
	{
		keynum = e.keyCode;
	}
	else if(e.which) // Netscape/Firefox/Opera
	{		
		keynum = e.which;
	}

	if(keynum == 9) //tab
	{ 		
		//Hide the popup
		kbdOptPopup.hidePopup();
		isClickSameField(1,2);
		
		//Focus back on input field
		optPopupInput.focus();
		if(optPopupInput == objLastInputField) 
		{
			return false;
		}
	}
	
	//enter
	if(keynum == 13)
	{	
		kbdOptPopupPick();
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
	var optionArray = new Array();
	
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
		optionArray[optionArray.length] = text.substr(leftIndex,leftIndex2).trim();
		
		// on to next =
		equalIndex = equalIndex2;
	}
	
	// return the array of function key
	return optionArray;
	
}	


//**********************************************************************************
// function optKeyDown(e)
// On key down event
//
// Parameters:
// e - event
//**********************************************************************************
function containerOptKeyDown(e)
{
	var keynum = rtvKeyboardKey(e);
	
	// escape/tab
	if (keynum==27 || keynum==9)
	{
	    optPopup.hidePopup();
	    optPopupInput.focus();
		disableKeyboardKey(e);
		isClickSameField(1,2);
		return false;
	}
	
	// enter
	else if (keynum==13)
	{
		optPopupPick(current_selected.value.trim());
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
// function containerOptOnClick(e)
// On click down event
//
// Parameters:
// e - event
//**********************************************************************************
function containerOptOnClick(e)
{
	disableKeyboardKey(e);
	return false;
}
