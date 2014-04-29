
var predictiveTimeout = null;
var predictiveArrDataFields = ''; // the data fields
var predictiveReturnRows = ''; // the list of returned rows

var predictiveListPopup = null; // popup object 
var predictiveSelect = null;

var predictiveListObj = null; // this contains the field whose PV is attached to
var predictiveListCriteria = ''; // the list criteria when a request is made, for comparison purposes
var predictiveListContext = ''; // this contains the list of relevant fields
var predictiveListContextValue = ''; // this contains the list of field values at the time of the query

//**********************************************************************************
// function triggerPredictivePrompt(fieldObj)
// Invoke the prompt of the specified field
//
// Parameters:
// fieldObj - the field to invoke the prompt
//**********************************************************************************	
function triggerPredictivePrompt(fieldObj)
{
	// reset
	predictiveListObj = null;
	
	// null?
	if (fieldObj==null)
	{
		return;
	}
	
	// protected?
	if (fieldObj.readOnly)
	{
		hidePredictivePopup(true);		
		return;
	}
	
	// predictive listing exists?
	var obj = document.getElementById(fieldObj.id + BUT_PROMPT + "Href");
	
	// Button defined, trigger predictive prompting
	if (obj != null)
	{
		//disableAnchor(fieldObj.id + "ButtonPredictivePMTHref",false);
		predictiveListObj = fieldObj;
		pvType = pvTypePredictive;
		
		if (obj.hrefclick != null)
		{
			eval(obj.hrefclick);
		}
		else
		{
			eval(obj.getAttribute('hrefclick'));
		}
		
		pvType = pvTypePrompt;
		//disableAnchor(fieldObj.id + "ButtonPredictivePMTHref",true);
	}
	
	// Button not defined, then trigger the prompt of the next field
	else
	{
		var nextfield = getAttribute(fieldObj,'nextfield');
		if (nextfield != null && nextfield != '')
		{
			triggerPredictivePrompt(document.getElementById(nextfield));
		}
	}
}

//**********************************************************************************
// function setPredictiveListButton(...)
// Processing for promptable fields for Desktop function
//
// Parameters:
// Refer to getNewPVList()
//**********************************************************************************	
function setPredictiveListButton(promptTitle,promptFieldId,promptId,promptDecode,promptSecurity,promptFile,promptKeys,
							returnFieldNames,returnFieldLabels,returnFieldPositions,returnFieldLengths,
							backStart,backLength,
							dataFields,dbFields,fieldHdrPos,maxResults)
{
	var onClickAction = "getPredictiveListDetailsStart('" + promptTitle + "','" + promptFieldId + "','" + 
							promptId + "','" + promptDecode + "','" + promptSecurity + "','" + promptFile + "','" + 
							promptKeys + "','" + 
							returnFieldNames + "','" + 
							returnFieldLabels + "','" + 
							returnFieldPositions + "','" + returnFieldLengths + "','" + 
							backStart + "','" + backLength + "','" + 
							dataFields + "','" + dbFields +  "'," +
							true + "," +
							maxResults + 
							")";
	var hoverText = getLanguageLabel('PMTDES');
	var labelText = promptFieldId + 'ButtonPredictivePMT';
	var imagePath = '/' + getWebAppName() + '/equation/images/EQInfo.gif';

	// add the prompt button
	var widgetButtonHTML = addWidgetButton(hoverText,labelText,onClickAction,imagePath);
	visibleObj(labelText,false);
	disableAnchor(labelText + "Href",true);
	return widgetButtonHTML;
}

//**********************************************************************************
//function getPredictiveListDetailsStart(...)
//Retrieve the help details
//
//Parameters:
//Refer to getNewPVList()
//**********************************************************************************	
function getPredictiveListDetailsStart(promptTitle,promptFieldId,promptId,promptDecode,promptSecurity,promptFile,promptKeys,
						returnFieldNames,returnFieldLabels,returnFieldPositions,returnFieldLengths,
						backStart,backLength,
						dataFields,dbFields,
						init,maxResults
						)
{
	// kill previous request if it has not been run yet
	if (predictiveTimeout != null)
	{
		clearTimeout(predictiveTimeout);
		predictiveTimeout = null;
	}
	
	predictiveTimeout = setTimeout( 
			function() 
			{
				getPredictiveListDetails(promptTitle,promptFieldId,promptId,promptDecode,promptSecurity,promptFile,promptKeys,
						returnFieldNames,returnFieldLabels,returnFieldPositions,returnFieldLengths,
						backStart,backLength,
						dataFields,dbFields,
						init,maxResults);
			}
			,20);

}
	


//**********************************************************************************
//function getPredictiveListDetails(...)
//Retrieve the help details
//
//Parameters:
//Refer to getNewPVList()
//**********************************************************************************	
function getPredictiveListDetails(promptTitle,promptFieldId,promptId,promptDecode,promptSecurity,promptFile,promptKeys,
						returnFieldNames,returnFieldLabels,returnFieldPositions,returnFieldLengths,
						backStart,backLength,
						dataFields,dbFields,
						init,maxResults
						)
{
	if (predictiveListObj == null)
	{
		return;
	}
	
	// get the session id
	var sessionIdentifier = getSessionId();

	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();
	
	// Generate DSCCN
	if (getAttribute(predictiveListObj,'predprompt') == '')
	{
		predictiveListCriteria = getDsccn(promptFieldId,dataFields,'Y1').trim();
	}
	else
	{
		predictiveListCriteria = getDsccnForPredictivePrompt(promptFieldId,dataFields).trim();
	}
	
	// is dsccn just full of *?
	if (replaceAll(predictiveListCriteria,'*','').trim() == '')
	{
		hidePredictivePopup(true);		
		return;
	}
	
	// is the key fully filled up?
	if (predictiveListCriteria.substr(backStart,backLength).indexOf('*')==-1)
	{
		hidePredictivePopup(true);		
		return;
	}
	
	// Define the get and response
	var qn_op = new WS.QName('getPredictiveList',nsuri);
	var qn_op_resp = new WS.QName('getPredictiveListResponse',nsuri);
	
	// Set the parameters required by the service
	if (maxResults<=0)
	{
		maxResults=16;
	}
	if (maxResults>16)
	{
		maxResults=16;
	}
	var nresult = maxResults + 1;
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'id',value:promptFieldId},
								{name:'service',value:promptId},
								{name:'decode',value:promptDecode},
								{name:'security',value:promptSecurity},
								{name:'query',value:predictiveListCriteria},
								{name:'maxResults',value:nresult}
							];
		
	// Call the web service
	call.invoke_rpc(
						qn_op,
						getSessionParms,
						null,
						function(call,envelope) 
						{
							// invalid already
							if (predictiveListObj==null)
							{
								hidePredictivePopup(false);		
								return;
							}
							
							var returnFieldString = '';
							try
							{ 
								returnFieldString = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();
							}
							catch (e)
							{
							}
							var returnFieldRows = returnFieldString.split(globalDelimeter);

							// no returned data
							if (returnFieldRows.length <= 2 || returnFieldRows[2]=='')
							{
								hidePredictivePopup(false);		
								return;
							}
							
							// different id/criteria, then ignore this result
							if (returnFieldRows[0] != predictiveListObj.id || returnFieldRows[1] != predictiveListCriteria)
							{
								return;
							}
							
							// store details
							predictiveArrDataFields = dataFields.split(',');
							predictiveReturnRows = returnFieldRows;
							
							// construct list of context fields
							contextObj = rtvFullContextFieldValue(predictiveListObj);
							predictiveListContext = contextObj.contextfields;
							predictiveListContextValue = contextObj.contextfieldvalues;
							
							// only one selected item and the same
							if (returnFieldRows.length==3 && predictiveListContextValue == returnFieldRows[2].substr(backStart,backLength))
							{
								hidePredictivePopup(false);		
								return;
							}
							
							// create and display the list
							createPredictiveList(returnFieldRows,backStart,backLength,maxResults);
							
							var predLeftmostfield = getAttribute(predictiveListObj,'leftmostfield');
							if (predLeftmostfield != null && predLeftmostfield != '')
							{
								leftmostfield = document.getElementById(predLeftmostfield);
								predictiveListPopup.showPopup(predLeftmostfield);
							}
							else
							{
								leftmostfield = predictiveListObj;
								predictiveListPopup.showPopup(predictiveListObj.id);
							}
							
							// try to adjust the position of the popup
							var popupObj = document.getElementById('predictiveListPopupID');
							var fieldPos = getPos(leftmostfield);
							var scrollLeft = getObjectScrollLeft(leftmostfield);
							var scrollTop = getObjectScrollTop(leftmostfield);
							
							// always move the popup underneath the field
							if (RTL)
							{
								popupObj.style.top = (fieldPos.top + predictiveListObj.offsetHeight - scrollTop) + "px";
								popupObj.style.left = (fieldPos.left + scrollLeft - popupObj.clientWidth + leftmostfield.clientWidth) + "px";
							}
							else
							{
								popupObj.style.top = (fieldPos.top + predictiveListObj.offsetHeight - scrollTop) + "px";
								popupObj.style.left = (fieldPos.left - scrollLeft) + "px";
							}
								
						}
					);

}


//**********************************************************************************
// function createPredictiveList(returnFieldRows,backStart,backLength)
// Create the list of items
//
// Parameters:
// returnFieldRows - the list of selected items
// backStart       - starting position of relevant data
// backLength      - length of relevant data
// maxResults      - maxResults to display
//**********************************************************************************	
function createPredictiveList(returnFieldRows,backStart,backLength,maxResults)
{
	if (predictiveSelect == null)
	{
		predictiveSelect = new MySelect("predListSelect", "predictiveSelect", "customSelect");
	}
	else
	{
		predictiveSelect.clear();
	}
	
	var listLength = 0;
	for (i=2; (i<returnFieldRows.length && i<maxResults+2); i++)
	{
		var x = i;
		var value = returnFieldRows[i].substring(backStart,backLength).rplSingleQuotes().rplSlash().rplQuotesWithSlashQuotes();
		var rowContent = getPredictiveFieldDescription(returnFieldRows[i],backStart,backLength);
		predictiveSelect.addData(value,rowContent.split(GLOBAL_DELIMETER),'',true);
		var i = x;
	}

	// more data to display?
	if (returnFieldRows.length-2 > maxResults)
	{
		predictiveSelect.addDataAfter("<input readonly class='dummyfield wfpr labelTextBold' value='...' size='1'></input>");			
		//predictiveSelect.addData('',('&nbsp;...' + GLOBAL_DELIMETER).split(GLOBAL_DELIMETER),'labelTextBold',false);
	}
	else
	{
		predictiveSelect.addDataAfter("<input readonly class='dummyField wfpr' size='1'></input>");			
	}
	
	// Create a div element for this element
    if (predictiveListPopup == null)
    {
    	predictiveListPopup = new PopupWindow('predictiveListPopupID'); 		     	
    	predictiveListPopup.autoHide();		   	
    	
    	listKbdDiv = document.createElement('div');
    	listKbdDiv.id = "predictiveListPopupID";
    	listKbdDiv.className = 'widgetContainerDIV';    
    	listKbdDiv.style.zIndex = listZindex;
    	document.body.appendChild(listKbdDiv);
    }
  	
	// Build the widget container
    popupHTML = 
	'		<div ' + 
    ' 			class="customSelectContainer"' +
    ' 			onclick="predListClick();"' +
    ' 			onkeypress="return predListKeyPress(event)"' +
    ' 			onkeydown="return predListKeyDown(event)"' +
    '  			>'+
    			predictiveSelect.getHTML() + 
	'		</div>';		
  
    predictiveListPopup.populate(popupHTML);
}

//**********************************************************************************
// function predListKeyPress(e)
// Event handler for key press
//
// Parameters:
// e: The event that triggered the listener
//**********************************************************************************
function predListKeyPress(e)
{
	var keynum = rtvKeyboardKey(e);

	// enter key
	if(keynum == 13)
	{		
		predListClick();		
	}	

	// escape key
	if(keynum == 27)
	{ 
		objLastFocusInputField.focus(); 
		hidePredictivePopup(true);		
	}	
}

//**********************************************************************************
// function predListKeyDown(e)
// Event handler for key down
//
// Parameters:
// e: The event that triggered the listener
//**********************************************************************************
function predListKeyDown(e)
{
	var keynum = rtvKeyboardKey(e);

	// backspace - disable
	if(keynum == 8)
	{ 		
		disableKeyboardKey(e);
		return false;
	}
	
	// tab
	if(keynum == 9)
	{ 		
		predListClick();
		disableKeyboardKey(e);
		return false;
	}
	
	// key down
	if(keynum == 40)
	{
		objLastFocusInputField.focus();
		disableKeyboardKey(e);
		predictiveSelect.selectRow(-1);
		return false;
	}
	
	// key up
	if(keynum == 38)
	{ 		
		objLastFocusInputField.focus();
		disableKeyboardKey(e);
		predictiveSelect.selectRow(-1);
		return false;
	}
}


//**********************************************************************************
// function predListClick(e)
// Event handler for mouse click
//
// Parameters:
// e: The event that triggered the listener
//**********************************************************************************
function predListClick()
{	
	setPredictiveFieldValue(predictiveListObj.id, predictiveSelect.getValue(), predictiveReturnRows[predictiveSelect.getIndex()+2]);
	objLastFocusInputField.focus();
	hidePredictivePopup(true);		
}   


// **********************************************************************************
// function hidePredictivePopup()
// Hide the predictive popup
//
// Parameters:
// blurObject - reset the object selected
//**********************************************************************************
function hidePredictivePopup(blurObject)
{
	if (predictiveListPopup != null)
	{
		predictiveListPopup.hidePopup();
		
		if (blurObject)
		{
			predictiveListObj = null;
		}
	}
}

//**********************************************************************************
// function focusPredictivePopup()
// Put the focus on the predictive popup
//
// Parameters:
// control - 1 (position top), 2 (position bottom)
//**********************************************************************************
function focusPredictivePopup(control)
{
	try
	{
		predictiveSelect.focus();
		if (control==2)
		{
			predictiveSelect.moveSelectionLast();
		}
		else
		{
			predictiveSelect.moveSelectionFirst();
		}
	}
	catch (e)
	{
	}
}


//**********************************************************************************
// function setPredictiveFieldValue(eleId,eleValue,row)
// Set the element to the specified value after retrieving the DSCCN 
//
// Parameters:
// eleId    - element id
// eleValue - value
// row      - the data
//**********************************************************************************	
function setPredictiveFieldValue(eleId,eleValue,row)
{
	// populate all the fields needed
	var i = 0;
	var fieldId = '';
	var start = 0;
	var len = 0;
	var obj = null;
	var value = '';
	var outputFieldChange = false;

	for (i=0; i<predictiveArrDataFields.length; i=i+dataFieldData)
	{
		fieldId = predictiveArrDataFields[i+1];
		start   = parseInt(predictiveArrDataFields[i+2]);
		len     = parseInt(predictiveArrDataFields[i+3]);
		value   = row.substr(start,len).trimr();
		obj     = getField(fieldId);
		
		// is field locked or input only?
		if (predictiveArrDataFields[i+7] == 'Y' || predictiveArrDataFields[i+7] == 'I')
		{
			obj = null;
		}
	
		// valid field?	
		if (obj != null)
		{
			// alter input field only, never alter output field
			if (obj.value != null)
			{
				// default mapping, then always retrieve the full key mappings
				if (predictiveArrDataFields[i+5] == 'Y')
				{
					obj.value = eleValue;
					setAnyFieldValue(eleId,eleValue);
				}
				else
				{
					obj.value = value;
					setAnyFieldValue(fieldId,value);
				}
			}
		}
	}
}


//**********************************************************************************
// function getPredictiveFieldDescription(eleId,row)
// Return the description associated with the field from a PV row 
//
// Parameters:
// row      - the data
//**********************************************************************************	
function getPredictiveFieldDescription(row,backStart,backLength)
{
	// retrieve values
	var keyValue = '';
	var outputValue = '';
	for (i=0; i<predictiveArrDataFields.length; i=i+dataFieldData)
	{
		var start = parseInt(predictiveArrDataFields[i+2]);
		var len   = parseInt(predictiveArrDataFields[i+3]);
		if (predictiveArrDataFields[i] == getAttribute(predictiveListObj,'predprompt'))
		{
			outputValue = row.substr(start,len).trim().rplSingleQuotes().rplSlash().rplQuotesWithSlashQuotes().trim();
		}
		
		else if (predictiveArrDataFields[i+4]=='N' && predictiveArrDataFields[i+1].charAt(0) != '.' && start<backStart+backLength-1) 
		{
			var value = row.substr(start,len).trim();
			keyValue += (value + " ");
		}

	}
	
	return keyValue + GLOBAL_DELIMETER + outputValue;
}


