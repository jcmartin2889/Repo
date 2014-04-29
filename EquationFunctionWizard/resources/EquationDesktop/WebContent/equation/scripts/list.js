// Display the list widget in a new window?
asPopupLstWindow = false;
asPopupWindow = false;
popupParam1 = null;
popupParam2 = null;
popupParam3 = null;

// **********************************************************************************
// LIST PICKER
// **********************************************************************************

var listPopupInput=null;
var listPopup = null;
var currentListStructureType = new String();
var currentKbdListStructureType = new String();
var inputValue = '';
var listZindex = 5;

// Determine the last frequency click
var lastListClick = null;

function activateListPopupAsWindow(listStructureType,listStructureInput,listStructureAnchor)
{
	// modal window open?
	if (isModalWindowOpenAlert(true))
	{
		return;
	}
	
	// setup the parameters
	popupParam1 = "LST";
	popupParam2 = listStructureType;
	popupParam3 = listStructureInput;
	popupParam4 = listStructureAnchor;
	
	// open the new window
	openPopupAsWindow(0,0, listStructureAnchor, 'popup.jsp');
}


function activateListPopup(listStructureType,listStructureInput,listStructureAnchor)
{
	// modal window open?
	if (isModalWindowOpenAlert(false))
	{
		return;
	}
		
	// determine if same button has been clicked again
	if (isClickSameField(listStructureInput, lastListClick))
	{
		return;
	}
	
	// store this
	lastListClick = listStructureInput;
	
	// Create the structure if we haven't already done so.
	if (listStructureType!=currentListStructureType)
	{
		createListStructure(listStructureType,getLanguageLabel(listStructureType + 'DES'),getLanguageList(listStructureType + 'VAL'),getLanguageList(listStructureType + 'DSC'));
	
		// Create the PopupWindow object
		listPopup = new PopupWindow(listStructureType + 'WidgetContainerDIV');
		
		if (asPopupWindow == false)
		{
			listPopup.autoHide();
		}

		// Finished initialisation
		currentListStructureType = listStructureType;
	}

	// reference the input
	listPopupInput=listStructureInput;
	
	// Save the Original input for cancel button
	inputValue = listPopupInput.value;
	
	// select the right radio button
	var selectedRadioButton = setListValue(listStructureType,listPopupInput,getLanguageList(listStructureType + 'VAL'));
	
	// uxp, hide header
	if (isUXP())
	{
		document.getElementById(listStructureType + 'WidgetHeader').style.display = "none";
	}
	
	// show the popup
	showListPopup(listPopupInput.id,listStructureAnchor);	
	
	// adjust size of window
	if (asPopupWindow)
	{
		width = document.getElementById(listStructureType + 'WidgetContainerDIV').offsetWidth;
		height = document.getElementById(listStructureType + 'WidgetContainerDIV').offsetHeight;
		adjustWidthHeight(width,height);
		adjustLeftTop(width,height);
		
		// retrieve the windows width
		clientWidthHeight = getClientWidthHeight();
		if (clientWidthHeight[0] > width)
		{
			document.getElementById(listStructureType + 'WidgetHeader').style.width = clientWidthHeight[0] +'px';	  
			document.getElementById(listStructureType + 'WidgetBody').style.width = clientWidthHeight[0] +'px';	  
			document.getElementById(listStructureType + 'WidgetFooter').style.width = clientWidthHeight[0] +'px';	  
			document.getElementById(listStructureType + 'WidgetContainer').style.width = clientWidthHeight[0] +'px';
			document.getElementById(listStructureType + 'WidgetContainerDIV').style.width = clientWidthHeight[0] +'px';
		}
		showListPopup(listPopupInput.id,listStructureAnchor);	
	}

	// select element
	selectedRadioButton.focus();
	current_selected = selectedRadioButton;
}

// **********************************************************************************
// function listPopupPick()
// Populate the input field
//
// Parameters: None
// **********************************************************************************
function listPopupPick(val)
{	
	listPopupInput.value = val;
	setListOutputDesc(listPopupInput.id, currentListStructureType, val);
	
	if (asPopupWindow)
	{
		window.opener.setAnyFieldValue(listPopupInput.id,val);
	}
	else
	{
		setAnyFieldValue(listPopupInput.id,val);
	}
}

// **********************************************************************************
// function listCancelButtonClick()
// Hide the popup if the cancel button is clicked
//
// Parameters: None
// **********************************************************************************
function listCancelButtonClick()
{
	listPopupInput.value = inputValue;
	setListOutputDesc(listPopupInput.id, currentListStructureType, inputValue);
	
	if (asPopupWindow)
	{
		window.opener.setAnyFieldValue(listPopupInput.id,inputValue);
	}
	else
	{
		setAnyFieldValue(listPopupInput.id,inputValue);
	}
	
	try
	{	
		listPopupInput.focus();
	}
	catch (e)
	{
	}

	// close the popup
	if (asPopupWindow)
	{
		window.close();
	}
	else
	{
		listPopup.hidePopup();
		isClickSameField(1,2);
	}
}

// **********************************************************************************
// function listOkButtonClick()
// Accept the selected options if the OK button is clicked
//
// Parameters: None
// **********************************************************************************
function listOkButtonClick()
{		
	try
	{	
		listPopupInput.focus();
	}
	catch (e)
	{
	}

	// close the popup
	if (asPopupWindow)
	{
		window.close();
	}
	else
	{
		listPopup.hidePopup();
		isClickSameField(1,2);
	}

}

// **********************************************************************************
// function listKeyPress(event,obj,listType,anchor)
// Bring up the keyboard widget with ctrl+space if pressed
//
// Parameters:
// event: The Key press event
// obj: The popup object
// anchor: The anchor for the popup
// **********************************************************************************
function listKeyPress(event,obj,listStructureType,anchor)
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
				 (keynum == 32 && isCtrlKey(e) && !isShiftKey(e) && !isAltKey(e) )
			)
		{
			activateListPopup(listStructureType,obj,anchor + BUT_WIDGET);
		}
	}	
}

// **********************************************************************************
// function
// createKbdListStructure(listStructureTitle,listStructureVal,listStructureDsc)
// Bring up the keyboard widget with ctrl+space if pressed
//
// Parameters:
// listStructureType: Type of list structure
// listStructureTitle: Title of list structure
// listStructureVal: Value of list structure
// listStructureDsc: Description of list structure
// **********************************************************************************
function createKbdListStructure(listStructureType,listStructureTitle,listStructureVal,listStructureDsc)
{		
	// Build the HTML to appear inside the widget container
	var listStructureSize = listStructureVal.length;
	var listStructureSeq = -1;
	var listLength = 1;
	var listKbdHTML = new String();

	// RTL?
	var strRTL = '>';
	if (RTLOrg) 
	{
		strRTL = 'class="wf_LTOR wf_RIGHT_ALIGN">';
	}

	var listKbdHTML = '<option value="">' + getLanguageLabel("GBLNONE").trim() + '</option>';
	
	for (listStructureSeq = 0;listStructureSeq < listStructureSize;listStructureSeq++) 
	{
		listKbdHTML += '<option value="'+ listStructureVal[listStructureSeq] + '">' + listStructureDsc[listStructureSeq].trim() + '</option>';
		listLength++;
	}
	
	  // Create a div element for this keyboard widget
      listKbdDiv = document.createElement('div');
      listKbdDiv.id = "kbdOptPopupId";
      listKbdDiv.className = 'widgetContainerDIV';    
      listKbdDiv.style.zIndex = listZindex;
      document.body.appendChild(listKbdDiv);        

       	// set listLength to maximum of 10 if needed
      	if (listLength > 10)
      	{
      		listLength = 10;
      	}      

		// Build the widget container
	    popupHTML = 
	    '	<div id="kbdOptWidgetContainer" >'+			
		'		<div id = "kbdOptWidgetBody" >'+
		'			<table id="kbdOptList" >' +
		'				<tr>' +
		'					<td id="tdlistKbdOpt" ' + strRTL + 
								'<select id="listKbdOpt" size="' + listLength + '"' + 
                                   ' onclick="kbdListPopupPick();"' +
                                   ' onkeypress="kbdListKeyPress(event)"' +
                                   ' onkeydown="return kbdListKeyDown(event)" >' +
								listKbdHTML +
								'</select>' +
		'					</td>' +
		'				</tr>' +
		'			</table>' +
		'		</div>' +		
		'	</div>';		 
      
      listKbdOptPopup.populate(popupHTML);
    
}

// **********************************************************************************
// function kbdListKeyPress(e)
// Keyboard key handler for the keyboard-widget popup
//
// Parameters:
// e: The event that triggered the listener
// **********************************************************************************
function kbdListKeyPress(e)
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
		kbdListPopupPick();	
		disableKeyboardKey(e);
		return false;
	}	

	// Escape key
	if(keynum == 27)
	{ 		
		// Hide the popup
		listKbdOptPopup.hidePopup();
		isClickSameField(1,2);
		
		// Focus back on input field
		listPopupInput.focus(); 
	}	
}

function kbdListKeyDown(e)
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
	
	// Tab
	if(keynum == 9)
	{ 		
		// Hide the popup
		listKbdOptPopup.hidePopup();
		isClickSameField(1,2);
		
		// Focus back on input field
		listPopupInput.focus(); 

		if(listPopupInput == objLastInputField)
		{
			return false;
		}
	}
	
	// enter
	if(keynum == 13)
	{ 
		kbdListPopupPick();
		disableKeyboardKey(e);
		return false;
	}	
	
}


// **********************************************************************************
// function kbdListPopupPick()
// Set the input box with a value
//
// Parameters: none
// **********************************************************************************
function kbdListPopupPick()
{	
      var selectEle = document.getElementById("listKbdOpt");	
      listPopupInput.value = selectEle.value;
      setListOutputDesc(listPopupInput.id, currentKbdListStructureType, selectEle.value);
      listKbdOptPopup.hidePopup();
      listPopupInput.focus();     
      isClickSameField(1,2);
}   

// **********************************************************************************
// function
// createListStructure(listStructureType,listStructureTitle,listStructureVal,listStructureDsc)
// Write out the HTML for a widget containing a list of values
//
// Parameters:
// listStructureType: Type of list structure
// listStructureTitle: Title of list structure
// listStructureVal: Value of list structure
// listStructureDsc: Description of list structure
// **********************************************************************************

function createListStructure(listStructureType,listStructureTitle,listStructureVal,listStructureDsc)
{	
	listStructureTitle = listStructureTitle.nbsp();	
	// Build the HTML to appear inside the widget container
	var listStructureSize = listStructureVal.length;
	var listStructureSeq = -1;
	
	// RTL?
	var strRTL = '>';
	var strRTL2 = '';
	if (RTLOrg)
	{
		strRTL = 'class="wf_LTOR wf_RIGHT_ALIGN">';
		strRTL2 = ' wf_LTOR wf_RIGHT_ALIGN ';
	}

	var listHTML = 
			'<tr>' +
			'<td>' +
			'<input type="radio" id="' + listStructureType + '_0" name="' + listStructureType + '" value="" onclick="list_onclick(event,this,\'\');">' + 
			'</td>' +
			'<td ' + strRTL +
			getLanguageLabel("GBLNONE").trim() +
			'<td>' +
			'</tr>';
	
	for (listStructureSeq = 0;listStructureSeq < listStructureSize;listStructureSeq++) 
	{
		listHTML += 
			'<tr>' +
			'<td>' +
				'<input type="radio" id="' + listStructureType + listStructureVal[listStructureSeq] + '" name="' + listStructureType + '" value="' + listStructureVal[listStructureSeq] + '" onclick="list_onclick(event,this,\'' + listStructureVal[listStructureSeq] + '\');">' + 
			'</td>' +
			'<td ' + strRTL + 
				(listStructureDsc[listStructureSeq]).trim() +
			'<td>' +
			'</tr>';
	}

	var strClass = 'widgetContainer';
	if (isUXP())
	{
		strClass = 'widgetContainerBelow';
	}
	
	if (asPopupLstWindow) 
	{
		strClass = 'widgetContainerAsPopup'; 
	}

	// Build the widget container
    popupHTML = 
    '	<div class="' + strClass + '" id="' + listStructureType + 'WidgetContainer" onkeydown="containerListKeyDown(event)" onclick="containerListOnClick(event)">'+
	'		<table class="widgetHeader" id = "' + listStructureType + 'WidgetHeader" cellpadding="0" cellspacing="0" >'+
	'			<tr>'+
	'				<td class="labelText ' + strRTL2 + '"> ' + 
						listStructureTitle.trim() +
	'				</td>'+
	'			</tr>'+
	'		</table>'+
	'		<div class="widgetBody" id = "' + listStructureType + 'WidgetBody">'+
	'			<table id="' + listStructureType + 'List">' +
							listHTML +
	'			</table>' +
	'		</div>' +
	'	</div>';		 

	// We need a divvie
	listDiv = document.createElement('div');
	listDiv.id = listStructureType + 'WidgetContainerDIV';
	listDiv.className = 'widgetContainerDIV';				
	listDiv.innerHTML = popupHTML;
	listDiv.style.zIndex = listZindex;
	document.body.appendChild(listDiv);

    // Get size of the list and adjust the widget
	var testwidth = document.getElementById(listStructureType + 'List').clientWidth;
	var testheaderwidth = document.getElementById(listStructureType + 'WidgetHeader').offsetWidth;
	
	if(testheaderwidth>testwidth)
	{
		testwidth = testheaderwidth;	
	}

	var footerwidth = getLanguageLabel("GBLOK").trim().length + getLanguageLabel("GBLCAN").trim().length*13;
	if(testwidth<96)
	{
		testwidth=96;
	}

	document.getElementById(listStructureType + 'WidgetHeader').style.width = testwidth +'px';	  
	document.getElementById(listStructureType + 'WidgetBody').style.width = testwidth +'px';	  
	document.getElementById(listStructureType + 'WidgetContainer').style.width = testwidth +'px';
	document.getElementById(listStructureType + 'WidgetContainerDIV').style.width = testwidth +'px';
}

// **********************************************************************************
// function setListValue(listStructureType,listInput,listStructureVal)
// Set the value selected
//
// Parameters:
// listStructureType: The type of list structure
// listInput: Input list
// listStructureVal: List structure value
//
// Return
// Selected radio button.  If none is selected, the first radio button
// **********************************************************************************
function setListValue(listStructureType,listInput,listStructureVal)
{	
	// Set the value
	var listStructureSize = listStructureVal.length;
	var listStructureSeq = -1;
	
	// default to none
	var obj = document.getElementById(listStructureType + '_0'); 
	obj.checked=true;
	
	for (listStructureSeq = 0;listStructureSeq < listStructureSize;listStructureSeq++) 
	{
		if (listInput.value == listStructureVal[listStructureSeq])
		{	
			obj = document.getElementById(listStructureType + listStructureVal[listStructureSeq]); 
			obj.checked=true;
		}
	}
	
	return obj;
}

// **********************************************************************************
// function lstKeyPress(e, listType)
// Event handler for when a key is pressed on the widget to deal with ctrl +
// space
// functionality.
//
// Parameters:
// e: The Event object. This is passed explicitly for Firefox compatibility.
// listType: The list type
// **********************************************************************************
function lstKeyPress(e, listType, inputField)
{
	textBox = document.getElementById(inputField);	

	if(window.event) // IE
	{
		keynum = event.keyCode;
	}
	else if(e.which) // Netscape/Firefox/Opera
	{		
		keynum = e.which;
	}
	
	if(keynum != 115)
	{			
		listKeyPress(e,textBox,listType, inputField);
	}
}

// **********************************************************************************
// function lstKeyDown(e, listType)
// Event handler for when a key is pressed on the widget to deal with F4
// functionality only.
// 
// Parameters:
// e: The Event object. This is passed explicitly for Firefox compatibility.
// listType: The list type
// **********************************************************************************
function lstKeyDown(e, listType, inputField) 
{
	textBox = document.getElementById(inputField);	
	if(window.event) // IE
	{
		keynum = event.keyCode;
	}
	else if(e.which) // Netscape/Firefox/Opera
	{		
		keynum = e.which;
	}
	
	if(keynum == 115 && !isShiftKey(e))// F4 allows prompt also
	{ 		
		if( document.all )
	    {
	    	event.cancelBubble = true;
	    	// IE
	       	listKeyPress(event,textBox,listType,inputField);
	    }
	    else
	    {
	    	e.cancelBubble = true;
	    	// Non IE
	        listKeyPress(e,textBox,listType,inputField);
	   	}
		disableKeyboardKey(e);
		return false;
   	}		

		// tab key
		if (keynum==9 && event.shiftKey==false && textBox==objLastInputField)
		{
			positionToFirstInputField();
			return false;
		}
		
		// shift-tab key
		if (keynum==9 && event.shiftKey && textBox==objFirstInputField)
		{
			objFirstInputField.blur();
			objectFocus(objLastInputField);
			return false;
		}
}

// **********************************************************************************
// function setListButton(listType,listInputFieldId
// Set the button for the widget
//
// Parameters:
// listType: The type of list button widget
// listInputFieldId: The field ID for input
// **********************************************************************************
function setListButton(listType,listInputFieldId)
{
	var hoverText = getLanguageLabel(listType + 'DES');
	var listInputFieldButtonId = listInputFieldId + BUT_WIDGET;
	var imagePath = '/' + getWebAppName() + '/equation/images/list.gif';

	var onclickAction = '';
	if (asPopupLstWindow)
	{
		onClickAction = 'activateListPopupAsWindow(\'' + listType + '\',document.getElementById(\'' +  listInputFieldId + '\'),\'' + listInputFieldButtonId + '\');';
	}
	else
	{
		onClickAction = 'activateListPopup(\'' + listType + '\',document.getElementById(\'' +  listInputFieldId + '\'),\'' + listInputFieldButtonId + '\');';
	}

	// WebFacing, then do not display the button if read-only
	var obj = document.getElementById(listInputFieldId);
	if(isWebFacing() && obj.readOnly)
	{
		return;
	}
	
	// add the list button
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
// function setListOutputDesc(val)
// Set the description field of a list widget
//
// Parameters:
// inputId - the input id
// listType - the list type (e.g. IDB)
// value - the selected value from the list
// **********************************************************************************
function setListOutputDesc(inputId, listType, value)
{
	// is there any output desc?
	var descObj = document.getElementById(inputId + ID_OUTPUT);
	
	// description not defined, get out
	if (descObj == null)
	{
		return;
	}
	
	// ensure it does not overwrite any description already set on the field
	if (!descObj.byWidget)
	{
		if (descObj.innerHTML.trim() != '')
		{
			return;
		}
	}
	
	// is value specified, if not retrieve from the input field
	if (value == null)
	{
		value = document.getElementById(inputId).value;
	}
	
	var valueList = getLanguageList(listType + 'VAL');
	var descList = getLanguageList(listType + 'DSC');
	var origValue = descObj.innterHTML;
	var noMatch = true;
	for (i=0; i<valueList.length; i++)
	{
		// match with the item?
		if (valueList[i] == value)
		{
			descObj.innerHTML = descList[i];
			descObj.byWidget = true;
			noMatch = false;
			break;
		}
	}

	// does not match with anything, then blank it out
	if (noMatch)
	{
		descObj.byWidget = true;
		descObj.innerHTML = '';
	}
	
	// force resizing if this is a repeating field which is identified by a $
	// and the value has changed
	if (!isWebFacing() && !pageLoading && inputId.indexOf('$') > 0 && origValue != descObj.innerHTML)
	{
		pageLoading = true;
		setTableSize(true);
		setTableSize(true);
		pageLoading = false;
	}
}


//**************************************************************************************
// function showListPopup(objId,anchor)
// Display the list popup
//
// Parameters:
// objId    - the field id
// anchor - the frequency button id
//**************************************************************************************
function showListPopup(objId,anchor)
{
	if (isUXP())
	{
		listPopup.showPopupBelow(objId);
	}
	else
	{
		listPopup.showPopup(anchor);
	}
}


//**************************************************************************************
// function showListPopup(objId,anchor)
// Display the list popup
//
// Parameters:
// objId    - the field id
// anchor - the frequency button id
//**************************************************************************************
function showListKbdPopup(objId,anchor)
{
	if (isUXP())
	{
		listKbdOptPopup.showPopupBelow(objId);
	}
	else
	{
		listKbdOptPopup.showPopup(anchor);
	}
}


//**************************************************************************************
// function list_onclick(e,radioObj,value)
// On click event of the radio button
//
// Parameters:
// e        - event
// radioObj - the radio button object
// value    - the radio button value
//**************************************************************************************
function list_onclick(e,radioObj,value)
{
	// action only if not due to keyboard up/down
	if (!pending_keyboard && radioObj.checked) 
	{
		listPopupPick(value);
		listOkButtonClick();
	}
	
	// currently selected radio button
	current_selected = radioObj;
	
	// reset
	pending_keyboard = false;
}


//**********************************************************************************
// function containerListKeyDown(e)
// On key down event
//
// Parameters:
// e - event
//**********************************************************************************
function containerListKeyDown(e)
{
	var keynum = rtvKeyboardKey(e);
	
	// escape/tab
	if (keynum==27 || keynum==9)
	{
		listCancelButtonClick();
		disableKeyboardKey(e);
		return false;
	}
	
	// enter
	else if (keynum==13)
	{
		listPopupPick(current_selected.value.trim());
		listOkButtonClick();
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
// function containerListOnClick(e)
// On click down event
//
// Parameters:
// e - event
//**********************************************************************************
function containerListOnClick(e)
{
	disableKeyboardKey(e);
	return false;
}
