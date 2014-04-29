                
// **********************************************************************************
// function setHelpPromptButton(...)
// Processing for promptable fields for Desktop function
//
// Parameters:
// Refer to getNewPVList()
// **********************************************************************************	
function setHelpPromptButton(promptTitle,promptFieldId,promptId,promptDecode,promptSecurity,promptFile,promptKeys,
							returnFieldNames,returnFieldLabels,returnFieldPositions,returnFieldLengths,
							backStart,backLength,
							dataFields,dbFields,fieldHdrPos,maxResults)
{
	var onClickAction = "getPromptHelpDetails('" + promptTitle + "','" + promptFieldId + "','" + 
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
	var labelText = promptFieldId + 'ButtonHelpPMT';
	var imagePath = '/' + getWebAppName() + '/equation/images/EQInfo.gif';

	// add the prompt button
	var widgetButtonHTML = addWidgetButton(hoverText,labelText,onClickAction,imagePath);
	visibleObj(labelText,false);
	disableAnchor(labelText + "Href",true);
	return widgetButtonHTML;
}

// **********************************************************************************
// function getPromptHelpDetails(...)
// Retrieve the help details
//
// Parameters:
// Refer to getNewPVList()
// **********************************************************************************	
function getPromptHelpDetails(promptTitle,promptFieldId,promptId,promptDecode,promptSecurity,promptFile,promptKeys,
						returnFieldNames,returnFieldLabels,returnFieldPositions,returnFieldLengths,
						backStart,backLength,
						dataFields,dbFields,
						init,maxResults
						)
{
	// create the initial popup
	dt_helpCreateDiv(replaceAll(promptTitle,'%20',' '),promptFieldId, document.getElementById(promptFieldId).title);

	// get the session id
	var sessionIdentifier = getSessionId();

	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName('getPromptHelpDetails',nsuri);
	var qn_op_resp = new WS.QName('getPromptHelpDetailsResponse',nsuri);

	var pv_init_display = true;
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'service',value:promptId},
								{name:'decode',value:promptDecode},
								{name:'security',value:promptSecurity},
								{name:'dsccn',value:getDsccn(promptFieldId,dataFields,'N')}
							];

	// Call the web service
	call.invoke_rpc(
						qn_op,
						getSessionParms,
						null,
						function(call,envelope) 
						{
							var returnFieldString = '';
							try
							{ 
								returnFieldString = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();
							}
							catch (e)
							{
							}
							
							// Populate the details
							populateHelpDetails("helpPopUpBody",returnFieldString);
							
							// Adjust the widths
							document.getElementById('helpPopUpHeader').style.width=document.getElementById('helpPopUpBody').offsetWidth;
							document.getElementById('helpPopUpBody').style.width=document.getElementById('helpPopUpHeader').offsetWidth;

							// Show the popup
							helpPopup.showPopup(promptFieldId + BUT_PROMPT);
							
							// Turn off spinner
							visibleObj("helpPopUpWorking",false);
						}
					);

}


// **********************************************************************************
// function dt_helpClosePopUp(promptFieldId)
// Close the pv poupup
//
// Parameters:
// promptFieldId - field id
// **********************************************************************************
function dt_helpClosePopUp(promptFieldId)
{
	// hide the popup
	helpPopup.hidePopup();
		
	// set the focus back to the field
	objectFocus(document.getElementById(promptFieldId));
}

// **********************************************************************************
// function dt_helpOnKeyDown(fieldId)
// On key down event
//
// Parameters:
// fieldId - fieldId
// **********************************************************************************
function dt_helpOnKeyDown(e,fieldId)
{
	var keynum = rtvKeyboardKey(e);
	
	// esc
	if (keynum==27)
	{
		dt_helpClosePopUp(fieldId);
		disableKeyboardKey(e);
	    return false;	    	
	}
	
}

// **********************************************************************************
// function dt_helpCreateDiv(title,fieldId)
// Create the initial popup for the help 
//
// Parameters:
// title       - field title       
// fieldId     - field id
// description - field description
// **********************************************************************************
function dt_helpCreateDiv(title,fieldId,description)
{
	// Create the help div
	try
	{
		helpPopUpDIV = document.getElementById('helpPopUpDIV');
		if (helpPopUpDIV==null)
		{
			helpPopUpDIV = document.createElement('div');
			helpPopUpDIV.id = 'helpPopUpDIV';
			helpPopUpDIV.className = 'helpContainerDIV';
			document.body.appendChild(helpPopUpDIV);
		}
	}
	catch (e)
	{
		helpPopUpDIV = document.createElement('div');
		helpPopUpDIV.id = 'helpPopUpDIV';
		helpPopUpDIV.className = 'helpContainerDIV';
	}

	// Construct the div
	helpPopUpDIV.innerHTML = '<div id="helpPopUp" class="helpContainer" onkeydown="javascript:dt_helpOnKeyDown(event,\'' + fieldId + '\');">' +
								'<table id="helpPopUpHeader" class="helpHeader" cellpadding="0" cellspacing="0">' + 
									'<tr>' +
										'<td id="helpPopUpTitle" class="labelText">' +
											'<span>' + title + '</span>' +
										'</td>' +
										'<td class="labelText" align="right" width="100%"></td>' +
										'<td id="helpPopUpWorking" align="center" class="button">' +
										'	<img src="/' + getWebAppName() + '/equation/images/EqSpin.gif" border="0">' +
										'</td>' +
										'<td id="helpPopUpExit" align="center" class="button" height="18">' +
										'	<a id="helpPopUpExitHref" href= "javascript:dt_helpClosePopUp(\'' + fieldId + '\');">' +
										'	   <img id="helpPopuUpExitImg" src="/' + getWebAppName() + '/equation/images/EQExit.gif" title="" border="0">' +
										'	</a>' +
										'</td>' +
									'</tr>' +
								'</table>' +
								'<div id="helpPopUpBodyDIV" class="helpBody">' +
									'<table id="helpPopUpBody" cellpadding="0" cellspacing="0" class="helpTableBody">' +
										'<tr>' +
										'<td class="helpDescription" colspan="2"> ' + description + ' </td>' + 
										'</tr>' +
									'</table>' +
								'</div>' +
							'</div>';

	// Create the PopupWindow object
	helpPopup = new PopupWindow('helpPopUpDIV');
	helpPopup.autoHide();
	
	// Show the popup
	document.getElementById('helpPopUpHeader').style.width=document.getElementById('helpPopUpBody').offsetWidth + "px";
	document.getElementById('helpPopUpBody').style.width=document.getElementById('helpPopUpHeader').offsetWidth + "px";
	document.getElementById('helpPopUpHeader').style.width=document.getElementById('helpPopUpBody').offsetWidth + "px";
	
	if (document.getElementById(fieldId + BUT_PROMPT) == null)
	{
		helpPopup.showPopup(fieldId);
	}
	else
	{
		helpPopup.showPopup(fieldId + BUT_PROMPT);
	}
	
	// Focus on the exit
	document.getElementById('helpPopUpExitHref').focus();
}

// **********************************************************************************
// function populateHelpDetails(tableId,helpDetail)
// Setup the help details 
//
// Parameters:
// tableId     - table        
// helpDetail  - help details
// **********************************************************************************
function populateHelpDetails(tableId,helpDetailStr)
{
	// split into array
	var helpDetails = helpDetailStr.split(globalDelimeter);
	
	// get the table
	var tableObj = document.getElementById(tableId);

	var row = 1;
	for(i=0; i<helpDetails.length; i+=3)
	{
		if (helpDetails[i].trim() == '')
		{
			return;
		}
	
		// add a new row
		var rowObj = tableObj.insertRow(row);

		// insert column 1
		var cellObj = rowObj.insertCell(-1);
		cellObj.className='helpColumnHeadings';
		cellObj.innerHTML=helpDetails[i];
		cellObj.title = helpDetails[i+2];
		
		// insert column 2
		var cellObj = rowObj.insertCell(-1);
		cellObj.className='helpColumnDetails';
		cellObj.innerHTML=helpDetails[i+1];
		cellObj.title = helpDetails[i+2];
		
		// next row
		row = row + 1;
	}
}

// **********************************************************************************
// function triggerHelp(fieldObj)
// Trigger help details 
//
// Parameters:
// fieldObj - field object
// **********************************************************************************
function triggerHelp(fieldObj)
{
	// help for the function
	if (fieldObj == null)
	{
		return;
	}
	
	// Button help defined, triger help
	var obj = document.getElementById(fieldObj.id + BUT_PROMPT + "Href");
	
	if (obj != null)
	{
		//disableAnchor(fieldObj.id + "ButtonHelpPMTHref",false);
		pvType = pvTypeHelp;
		
		if (obj.hrefclick != null)
		{
			eval(obj.hrefclick);
		}
		else
		{
			eval(obj.getAttribute('hrefclick'));
		}
		
		pvType = pvTypePrompt;
		//disableAnchor(fieldObj.id + "ButtonHelpPMTHref",true);
	}
	
	// Button not defined, then trigger the prompt of the next field
	else
	{
		var nextfield = getAttribute(fieldObj,'nextfield');
		if (nextfield != null && nextfield != '')
		{
			triggerHelp(document.getElementById(nextfield));
		}
		else
		{
			dt_helpCreateDiv(getAttribute(fieldObj,'label'),fieldObj.id,fieldObj.title);
			visibleObj("helpPopUpWorking",false);
		}
	}
}
