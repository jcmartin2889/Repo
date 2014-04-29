var gblPromptTitle='';
var gblPromptFieldId='';
var gblPromptId='';
var gblPromptDecode='';
var gblPromptSecurity='';
var gblPromptFile='';
var gblPromptKeys='';
var gblPromptFieldValue='';
var gblArrPromptFieldValue='';
var gblSearchIndex='';
var gblFirstIndex='';
var gblLastIndex='';
var	gblFieldNames = '';
var	gblFieldLabels = '';
var	gblFieldPositions = 0;
var	gblFieldLengths = 0;
var	gblFieldHdrPos = '';
var gblReturnFieldNames = '';
var gblReturnFieldLabels = '';
var gblReturnFieldPositions = '';
var	gblReturnFieldLengths = '';
var	gblReturnFieldHdrPos = '';
var	gblBackStart=0;
var	gblBackEnd=0;
var	gblBackLength=0;
var	gblDataFields='';
var	gblArrDataFields='';
var	gblDbFields='';
var gblSearchDirection=0;
var pvPopup;
var gblMaxResults;

// is the PV current waiting for a reply?
var pv_processing = false;

// number of header line
var numberOfHeaderLine = 1;

// initial display?
var pv_init_display = true;

// display the PV in a new window
var asPopupPVWindow = true;
var asPopupWindow = false;

// enabled previous/next button
var pvPopUpEnabledPrevButton= '<a id="pvPopUpPrevHref" href= "javascript:getPVList(-1,%1);" onkeydown="return dt_kbdSelKeyDown2(event,\'pvPopUpPrevHref\',null,\'pvPopUpCanHref\')">' +
							  '    <img src="/' + getWebAppName() + '/equation/images/previous.gif" title="" border="0">' +
							  '	</a>';
var pvPopUpEnabledNextButton= '	<a id="pvPopUpNextHref" href= "javascript:getPVList(1,%1);" onkeydown="return dt_kbdSelKeyDown2(event,\'pvPopUpNextHref\',null,\'pvPopUpPrevHref\')">' +
							  '	   <img src="/' + getWebAppName() + '/equation/images/next.gif" title="" border="0">' +
							  '	</a>';

// enabled cancel button
var pvPopUpCancelButton = getTextButtonHTML2("pvPopUpCanHref",getWindowLanguageLabel("GBLCAN"),getWindowLanguageLabel("GBLCAN"),"dt_pvClosePopUp(true)", "return dt_kbdSelKeyDown2(event,'pvPopUpCanHref','pvPopUpPrevHref','%1')", "");

// pv type - this determines whether to invoke predictive prompting / help / prompt (default)
var pvTypePrompt = '';
var pvTypePredictive = 1;
var pvTypeHelp = 2;
var pvType = pvTypePrompt;

//indicator for maker-checker processing
var makerchecker = false;
var radioButtonSelectionOnly = false;
//**********************************************************************************
//function setPromptButtonNoWF(...)
//Processing for promptable fields for Desktop function
//
//Parameters:
//Refer to getNewPVList()
//**********************************************************************************	
function setPromptHTMLButtonNoWF(promptTitle,promptFieldId,promptId,promptDecode,promptSecurity,promptFile,promptKeys,
		returnFieldNames,returnFieldLabels,returnFieldPositions,returnFieldLengths,
		backStart,backLength,
		dataFields,returnDbFields,returnFieldHdrPos,maxResults, buttonName)
{
	var widgetButtonHTML = setPromptCommon(promptTitle,promptFieldId,promptId,promptDecode,promptSecurity,promptFile,promptKeys,
			returnFieldNames,returnFieldLabels,returnFieldPositions,returnFieldLengths,
			backStart,backLength,
			dataFields,returnDbFields,returnFieldHdrPos,maxResults, true, buttonName);
	return widgetButtonHTML;
}

function setPromptButtonNoWF(promptTitle,promptFieldId,promptId,promptDecode,promptSecurity,promptFile,promptKeys,
		returnFieldNames,returnFieldLabels,returnFieldPositions,returnFieldLengths,
		backStart,backLength,
		dataFields,returnDbFields,returnFieldHdrPos,maxResults, showButton)
{
	var widgetButtonHTML = setPromptCommon(promptTitle,promptFieldId,promptId,promptDecode,promptSecurity,promptFile,promptKeys,
			returnFieldNames,returnFieldLabels,returnFieldPositions,returnFieldLengths,
			backStart,backLength,
			dataFields,returnDbFields,returnFieldHdrPos,maxResults, false, '');
	return widgetButtonHTML;
}
// **********************************************************************************
// function setPromptButtonNoWF(...)
// Processing for promptable fields for Desktop function
//
// Parameters:
// Refer to getNewPVList()
// **********************************************************************************	
function setPromptCommon(promptTitle,promptFieldId,promptId,promptDecode,promptSecurity,promptFile,promptKeys,
							returnFieldNames,returnFieldLabels,returnFieldPositions,returnFieldLengths,
							backStart,backLength,
							dataFields,returnDbFields,returnFieldHdrPos,maxResults, showButton, buttonName)
{
	var onClickAction = "";
	if (asPopupPVWindow)
	{
		onClickAction = "getNewPVListAsWindow('" + promptTitle + "','" + promptFieldId + "','" + 
								promptId + "','" + promptDecode + "','" + promptSecurity + "','" + promptFile + "','" + 
								promptKeys + "','" +
								returnFieldNames + "','" +
								cvtArrToString(returnFieldLabels,globalDelimeter) + "','" + 
								returnFieldPositions + "','" + 
								returnFieldLengths + "','" + 
								backStart + "','" + backLength + "','" + 
								dataFields + "','" + returnDbFields +  "','" +
								returnFieldHdrPos +  "'," +
								true + "," +
								maxResults + 
								")";
	}
	else
	{
		onClickAction = "getNewPVList('" + promptTitle + "','" + promptFieldId + "','" + 
								promptId + "','" + promptDecode + "','" + promptSecurity + "','" + promptFile + "','" + 
								promptKeys + "','" + 
								returnFieldNames + "','" +
								cvtArrToString(returnFieldLabels,globalDelimeter) + "','" + 
								returnFieldPositions + "','" + 
								returnFieldLengths + "','" + 
								backStart + "','" + backLength + "','" + 
								dataFields + "','" + returnDbFields +  "','" +
								returnFieldHdrPos +  "'," +
								true + "," +
								maxResults + 
								")";
	}
	var hoverText = getWindowLanguageLabel('PMTDES');
	var labelText = promptFieldId + BUT_PROMPT;
	var imagePath = '/' + getWebAppName() + '/equation/images/prompt.gif';

	// WebFacing, then do not display the button if read-only
	var obj = document.getElementById(promptFieldId);
	if(isWebFacing() && obj.readOnly)
	{
		return;
	}
	var widgetButtonHTML = "";
	if(showButton){
		// add the prompt button
		widgetButtonHTML ='<input ' +
		'type="button" class="inputButton" onclick="javascript:' + onClickAction + '"' +
		'name="' + labelText + '" ' + 
		'id="' + labelText + '" ' +
		'value="' + buttonName + '" ' +
		'title="' + buttonName + '"/> ';
		document.write(widgetButtonHTML);		
	}
	else{
		// add the prompt icon
		var classNames = 'promptWidget';
		widgetButtonHTML  = addWidgetButtonOnClick(hoverText,labelText,onClickAction,imagePath,classNames);
	}
	
	// protected - then hide the widget 
	if (obj.readOnly)
	{
		disableAnchor(labelText + BUT_HREF, true);
		visibleObj(labelText, false);
	}
	
	return widgetButtonHTML;
}

// **********************************************************************************
// function setFieldValue(eleId,eleValue,row)
// Set the element to the specified value after retrieving the DSCCN from the PV module
//
// Parameters:
// eleId    - element id
// eleValue - value
// row      - the data
// **********************************************************************************	
function setFieldValue(eleId,eleValue,row)
{
	// populate all the fields needed
	var i = 0;
	var fieldId = '';
	var start = 0;
	var len = 0;
	var obj = null;
	var value = '';
	var outputFieldChange = false;

	for (i=0; i<gblArrDataFields.length; i=i+dataFieldData)
	{
		fieldId = gblArrDataFields[i+1];
		start   = parseInt(gblArrDataFields[i+2]);
		len     = parseInt(gblArrDataFields[i+3]);
		value   = row.substr(start,len).trimr();
		obj     = getField(fieldId);
		
		// is field locked or input only?
		if (gblArrDataFields[i+7] == 'Y' || gblArrDataFields[i+7] == 'I')
		{
			obj = null;
		}
	
		// valid field?	
		if (obj != null)
		{
			// span field?
			if (obj.value == null)
			{
				obj.innerHTML = value.trimr().rplSpaces();
				outputFieldChange = true;
			}
			
			// input field
			else
			{
				// default mapping, then always retrieve the full key mappings
				if (gblArrDataFields[i+5] == 'Y')
				{
					obj.value = eleValue;
					if (asPopupPVWindow)
					{
						window.opener.setAnyFieldValue(eleId,eleValue);
					}
					else
					{
						setAnyFieldValue(eleId,eleValue);
					}
				}
				else
				{
					obj.value = value;
					if (asPopupPVWindow && !makerchecker)
					{
						window.opener.setAnyFieldValue(fieldId,value);
					}
					else
					{
						setAnyFieldValue(fieldId,value);
					}
				}
			}
		}
	}
	
	// do we need to force page resizing in order to re-align the repeating data in case output field has been changed
	if (!isWebFacing() && outputFieldChange)
	{
		if (asPopupPVWindow)
		{
			window.opener.pageLoading = true;
			window.opener.setTableSize(true);
			window.opener.setTableSize(true);
			window.opener.pageLoading = false;
		}
		else
		{
			pageLoading = true;
			setTableSize(true);
			setTableSize(true);
			pageLoading = false;
		}
	}
	
}

// **********************************************************************************
// function populateHeader(tableid)
// Generate the PV column header
//
// Parameters:
// tableId - the table id
// **********************************************************************************	
function populateHeader(tableId)
{
	var table = document.getElementById(tableId);
	var pvResults = table.insertRow(table.rows.length);
	pvResults.className='tableHeader';
	var i = 0;

	// selection field
	var label;
	label = pvResults.insertCell(0);
	label.className='pvColumnHeadings';
	label.innerHTML="?";
	label.align="center";
	label.width = '10%';
	
	// column header
	for(i=0; i < gblFieldLabels.length; i++)
	{
		// check position
		hdrPos = gblFieldHdrPos[i].trim();
		
		if (hdrPos == '')
		{
			// add to the cell
			label = pvResults.insertCell(pvResults.cells.length);
			label.className='pvColumnHeadings';
			label.innerHTML=gblFieldLabels[i];
		}
		else
		{
			arrHdrPos = hdrPos.split(':');
			label = rtvNextCell(table,pvResults,arrHdrPos[0],parseInt(arrHdrPos[1]),parseInt(arrHdrPos[2]));
			label.className='pvColumnHeadings';
			label.innerHTML=gblFieldLabels[i];
			pvResults = label.parentElement;
			//numberOfHeaderLine
		}
	}
}

// **********************************************************************************
// function populateFilterFields(promptFieldId,tableId)
// Generate the filter fields 
//
// Parameters:
// promptFieldId - input field
// tableId - the table id
// **********************************************************************************	
function populateFilterFields(promptFieldId,tableId)
{		
	var table = document.getElementById(tableId);
	var pvResults = table.insertRow(table.rows.length);
	pvResults.className='tableHeader';

	// selection field
	var label;
	label = pvResults.insertCell(0);
	label.className='pvColumnHeadings';
	label.innerHTML="";
	
	// display the filter fields
	var i = 0;
	var style = '';
	var id = '';
	for(i=0; i < gblFieldNames.length; i++)
	{
		// check position
		hdrPos = gblFieldHdrPos[i].trim();
		if (hdrPos == '')
		{
			label = pvResults.insertCell(pvResults.cells.length);
		}
		else
		{
			arrHdrPos = hdrPos.split(':');
			label = rtvNextCell(table,pvResults,arrHdrPos[0],parseInt(arrHdrPos[1]),parseInt(arrHdrPos[2]));
			pvResults = label.parentElement;
		}
		
		// set cell
		label.className='pvColumnHeadings';

		// make it uppercase
		style = 'wf_UPPERCASE';
		
		// setup the id
		id = "filter_" + promptFieldId + i;
		
		// locked?
		var readonly = "";
		var arrDataFields = gblDataFields.split(',');
		var n = getDataFieldOfField(gblFieldNames[i],arrDataFields);
		if (n>=0)
		{
			if (arrDataFields[n+7]=='Y')
			{
				readonly = "readonly disabled ";
			}
		}
		
		// setup the length
		len = parseInt(gblFieldLengths[i]);
		size = len + (len * 0.20);
		
		// setup the input html
		label.innerHTML="<INPUT type='input' class='pvWidgetField " + style + "' " + 
						"id='" + id + "' " + 
						"name='" + id + "' " + 
						"value='" + gblArrPromptFieldValue[i].rplSingleQuotes() + "' " + 
						"maxlength='" + len + "' " + 
						"size='" + size + "' " + 
						"onkeydown=\"return dt_kbdSelKeyDown2(event,\'" + id + "\',null,null)\"" + 
						"onkeypress=\"return dt_kbdFilterFieldKeyPress(event,\'" + id + "\')\"" + 
						readonly +
						"> ";
	}
}


// **********************************************************************************
// function populateDetails (promptFieldId,tableId,rowNumber,row)
// Generate the pv details
//
// Parameters:
// promptFieldId - input field
// tableId       - table id
// rowNumber     - nth row
// row           - returned data from the pv
// **********************************************************************************	
function populateDetails(promptFieldId,tableId,rowNumber,row)
{
	var table = document.getElementById(tableId);
	var pvResults = table.insertRow(table.rows.length);
	var i = 0;

	// setup selection id
	var selId = 'sel_' + promptFieldId;
	var idx = padl(rowNumber,3,'0');

	// selection field
	var details;
	details = pvResults.insertCell(0);
	details.className='pvColumnDetails';
	details.align="center";
	var onClickAction = 'javascript:setFieldValue(\'' + promptFieldId + '\',\'' + row.substring(gblBackStart,gblBackEnd).rplSingleQuotes().rplSlash().rplQuotesWithSlashQuotes() + '\',\'' + row.rplSingleQuotesWithSlashSingleQuotes().rplSlash().rplQuotesWithSlashQuotes() + '\');';
	if (makerchecker)
	{
		onClickAction = onClickAction + 'offline_makerchecker_onclick(event);';
	}
	else
	{
		onClickAction = onClickAction + 'dt_pvClosePopUp(true);';
	}
	details.innerHTML = '<input type="radio" ' + 
				  		'name="' + selId + idx + '" ' +
						'id="' + selId + idx + '" ' +
						'value="1" '  +
						'onClick="' + onClickAction + '" ' +
						'onfocus="this.checked=true;" ' +
						'onblur="this.checked=false;" ' +
                        'onkeydown="return dt_kbdSelKeyDown(event,\'' + selId + idx + '\',\'' + promptFieldId + '\',\'' + selId + '\',\'' + idx + '\')"' +
                        '>';

	var start = 0;
	var len = 0;
	for(i=0; i < gblFieldPositions.length; i++)
	{
		// check position
		hdrPos = gblFieldHdrPos[i].trim();
		if (hdrPos == '')
		{
			details = pvResults.insertCell(i+1);
		}
		else
		{
			arrHdrPos = hdrPos.split(':');
			details = rtvNextCell(table,pvResults,arrHdrPos[0],parseInt(arrHdrPos[1]),parseInt(arrHdrPos[2]));
			pvResults = label.parentElement;
		}
		
		start = new Number(gblFieldPositions[i]);
		end = start + new Number(gblFieldLengths[i]);
		var onclick = '';
		if (radioButtonSelectionOnly)
		{
			onclick = 'onclick = "return false"';
		}
		details.innerHTML='<a class="wf_PVS" href="' + onClickAction + '"' + onclick +'>' + row.substring(start,end).rplQuotes().trimr().rplSpaces().rplLessThan().rplGreaterThan() + '</a>';
		
		if (RTL)
		{
			details.className='pvColumnDetails wf_LTOR wf_RIGHT_ALIGN';
		}
		else
		{
			details.className='pvColumnDetails';
		}
	}
}

// **********************************************************************************
// function getNewPVListAsWindow(promptTitle,promptFieldId,promptId,promptDecode,promptFile,promptKeys,returnFieldLabels,returnFieldPositions,returnFieldLengths,backStart,backLength,dataFields,maxResults)
//
// Parameters:
// refer to getNewPVList
// **********************************************************************************	
function getNewPVListAsWindow(promptTitle,promptFieldId,promptId,promptDecode,promptSecurity,promptFile,promptKeys,
						returnFieldNames,returnFieldLabels,returnFieldPositions,returnFieldLengths,
						backStart,backLength,
						dataFields,dbFields,returnFieldHdrPos,
						init,maxResults
						)
{
	// predictive?
	if (pvType == pvTypePredictive)
	{
		getPredictiveListDetailsStart(promptTitle,promptFieldId,promptId,promptDecode,promptSecurity,promptFile,promptKeys,
				returnFieldNames,returnFieldLabels,returnFieldPositions,returnFieldLengths,
				backStart,backLength,
				dataFields,dbFields,
				init,maxResults
				);
		
		return;
	}
	
	// help?
	else if (pvType == pvTypeHelp)
	{
		getPromptHelpDetails(promptTitle,promptFieldId,promptId,promptDecode,promptSecurity,promptFile,promptKeys,
				returnFieldNames,returnFieldLabels,returnFieldPositions,returnFieldLengths,
				backStart,backLength,
				dataFields,dbFields,
				init,maxResults
				);
		return;
	}
	
	// modal window open?
	if (isModalWindowOpenAlert(true))
	{
		return;
	}

	// pending processing?
	if (pv_processing)
	{
		return;
	}

	gblPromptTitle = promptTitle;
	gblPromptFieldId = promptFieldId;
	gblPromptId = promptId;
	gblPromptDecode = promptDecode;
	gblPromptSecurity = promptSecurity;
	gblPromptFile = promptFile;
	gblPromptKeys = promptKeys;
	gblReturnFieldNames = returnFieldNames;
	gblReturnFieldLabels = returnFieldLabels;
	gblReturnFieldPositions = returnFieldPositions;
	gblReturnFieldLengths = returnFieldLengths;
	gblBackStart = new Number(backStart);
	gblBackLength = Number(backLength);
	gblDataFields = dataFields;
	gblDbFields = dbFields;
	gblReturnFieldHdrPos = returnFieldHdrPos;
	gblMaxResults = maxResults;

	openPopupAsWindow(0,0, gblPromptFieldId + BUT_PROMPT, 'popupPV.jsp');
}

// **********************************************************************************
// function getNewPVList(promptTitle,promptFieldId,promptId,promptDecode,promptFile,promptKeys,returnFieldNames,returnFieldLabels,returnFieldPositions,returnFieldLengths,backStart,backLength,dataFields,maxResults)
//
// Parameters:
// promptTitle   - pv title
// promptFieldId - element id of the input field
// promptId      - pv program name
// promptDecode  - decode character 
// promptFile    - file
// promtKeys     - keys of the file - separated by "||" if more than one keys (e.g SCAB || SCAN || SCAS)
// returnFieldNames     - column field name - separated by "," (e.g. TCD,DSC)
// returnFieldLabels    - column headers - separated by "," (e.g. Transaction code,Description)
// returnFieldPositions - column positions - separated by ","
// returnFieldLengths   - column lengths - separated by ","
// backStart            - returned input field position 
// backLength           - returned input field length (this is the text to retrieve from the PV to display to the field)
// dataFields           - this is the list of fields, position and lengths to populate after selecting from the pv
//                        It will always be multiple of 3, separated by ","
//                        - Field name
//                        - Position
//                        - Length                    
// dbFields             - equivalent database fields of the filter fields
// hdrPos				- column header positions (column position)
// init                 - true - if redisplaying from scratch?
// maxResults           - number of records to return
// **********************************************************************************	
function getNewPVList(promptTitle,promptFieldId,promptId,promptDecode,promptSecurity,promptFile,promptKeys,
						returnFieldNames,returnFieldLabels,returnFieldPositions,returnFieldLengths,
						backStart,backLength,
						dataFields,dbFields,returnFieldHdrPos,
						init,maxResults
						)
{
	// predictive?
	if (pvType == pvTypePredictive)
	{
		getPredictiveListDetailsStart(promptTitle,promptFieldId,promptId,promptDecode,promptSecurity,promptFile,promptKeys,
				returnFieldNames,returnFieldLabels,returnFieldPositions,returnFieldLengths,
				backStart,backLength,
				dataFields,dbFields,
				init,maxResults
				);
		
		return;
	}
	
	// help?
	else if (pvType == pvTypeHelp)
	{
		getPromptHelpDetails(promptTitle,promptFieldId,promptId,promptDecode,promptSecurity,promptFile,promptKeys,
				returnFieldNames,returnFieldLabels,returnFieldPositions,returnFieldLengths,
				backStart,backLength,
				dataFields,dbFields,
				init,maxResults
				);
		return;
	}
	
	// modal window open?
	if (isModalWindowOpenAlert(false))
	{
		return;
	}

	// pending processing?
	if (pv_processing)
	{
		return;
	}
	
	// initial display?
	if (init)
	{
		pv_init_display = true;
	}
	else
	{
		pv_init_display = false;
	}
		
	// get the session id
	var sessionIdentifier = getSessionId();

	// store the original parameters!
	gblPromptTitle = promptTitle;
	gblPromptFieldId = promptFieldId;
	gblPromptId = promptId;
	gblPromptDecode = promptDecode;
	gblPromptSecurity = promptSecurity;
	gblPromptFile = promptFile;
	gblPromptKeys = promptKeys;
	gblReturnFieldNames = returnFieldNames;
	gblReturnFieldLabels = returnFieldLabels;
	gblReturnFieldPositions = returnFieldPositions;
	gblReturnFieldLengths = returnFieldLengths;
	gblBackStart = new Number(backStart);
	gblBackLength = Number(backLength);
	gblDataFields = dataFields;
	gblDbFields = dbFields;
	gblReturnFieldHdrPos = returnFieldHdrPos;
	gblMaxResults = maxResults;

	// other global fields
	gblSearchDirection=1;
	gblSearchIndex = '';
	gblFieldNames = returnFieldNames.split(',');
	gblFieldLabels = returnFieldLabels.split(globalDelimeter);
	gblFieldPositions = returnFieldPositions.split(',');
	gblFieldLengths = returnFieldLengths.split(',');
	gblBackEnd = gblBackStart + new Number(backLength);
	gblArrDataFields = dataFields.split(',');
	gblPromptFieldValue = setupPromptFields(init);
	gblArrPromptFieldValue = gblPromptFieldValue.split(globalDelimeter);
	gblFieldHdrPos = returnFieldHdrPos.split(',');
	
	// display the initial popup
	if (init)
	{
		pvCreateDiv();
	}

	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName('getEqDataList',nsuri);
	var qn_op_resp = new WS.QName('getEqDataListResponse',nsuri);

	// Set the parameters required by the service
	maxResults = parseInt(maxResults);
	if (maxResults<=0)
	{
		maxResults=10;
	}
	var nresult = maxResults + 1;
	
	prefix = getGlobalProcessingFlags(gblPromptFieldId,gblDataFields,'Y') +
		setupFilterFieldsInDSCCN(getDsccn(gblPromptFieldId,gblDataFields,'Y'));
	
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'fieldId',value:gblPromptFieldId},
								{name:'service',value:gblPromptId},
								{name:'decode',value:gblPromptDecode},
								{name:'security',value:gblPromptSecurity},
								{name:'primaryTable',value:gblPromptFile},
								{name:'serviceKey',value:gblPromptKeys},
								{name:'query',value:prefix},
								{name:'start',value:gblSearchIndex},
								{name:'direction',value:gblSearchDirection},
								{name:'maxResults',value:nresult}
							];
					
		
	// Call the web service
	call.invoke_rpc(
						qn_op,
						getSessionParms,
						null,
						function(call,envelope) 
						{
							// End processing
							pv_processing = false;
							
							var returnFieldString = '';
							try
							{ 
								returnFieldString = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();
							}
							catch (e)
							{
							}							
							var errorArray = returnFieldString.split("!#ERRORS#!");
							var errorCodes = "";
							
							//If there are errors to return, populate the variable
							if(errorArray.length>1)
							{
								errorCodes = errorArray[errorArray.length-1];
							}
							var returnFieldRows = errorArray[0].split(globalDelimeter);
							
							// No returned data
							if (returnFieldString == '')
							{
								returnFieldRows = '';
							}
							
							// determine actual number of rows to be displayed
							if (returnFieldRows.length > maxResults)
							{
								nresult = maxResults;
							}
							else
							{
								nresult = returnFieldRows.length;
							}

							var lastSelection = 'sel_' + gblPromptFieldId + padl(nresult-1,3,'0');
							pvPopUpDIV.innerHTML = 	'<div id="pvPopUp" class="pvContainer"  onkeydown="javascript:return dt_kbdSelKeyDown2(event,null,null,null)">' +
														'<table id="pvPopUpHeader" class="pvHeader" cellpadding="0" cellspacing="0">' + 
															'<tr>' +
																'<td id="pvPopUpTitle" class="labelText">' +
																	'<span>' + gblPromptTitle + '</span>' +
																'</td>' +
																'<td class="labelText" align="right" width="100%"></td>' +
																'<td id="PvPopUpWorking" align="center" class="button" style="display:none;">' +
																'	<img src="/' + getWebAppName() + '/equation/images/EqSpin.gif" border="0">' +
																'</td>' +
																'<td id="PvPopUpPrev" align="center" class="button" height="18">' +
																pvPopUpEnabledPrevButton.replace("%1",maxResults) +
																'</td>' +
																'<td id="PvPopUpNext" align="center" class="button" height="18">' +
																pvPopUpEnabledNextButton.replace("%1",maxResults) +
																'</td>' +
																'<td id="pvPopUpExit" align="center" class="button" height="18">' +
																'	<a id="pvPopUpExitHref" href= "javascript:dt_pvClosePopUp(true);"  onkeydown="return dt_kbdSelKeyDown2(event,\'pvPopUpExitHref\',null,\'pvPopUpNextHref\')">' +
																'	   <img id="pvPopuUpExitImg" src="/' + getWebAppName() + '/equation/images/EQExit.gif" title="" border="0">' +
																'	</a>' +
																'</td>' +
															'</tr>' +
														'</table>' +
														'<div id="pvPopUpBodyDIV" class="pvBody">' +
															'<table id="pvPopUpBody" cellpadding="0" cellspacing="0"></table>' +
														'</div>' +
														'<table id="pvPopUpFooter" class="pvFooter" cellpadding="0" cellspacing="0">' +
														'			<tr>'+
														'				<td id="pvPopUpCancelButtonTD" class="wf_LTOR" align="right">'+
																				pvPopUpCancelButton.replace("%1", lastSelection) +
														'				</td>'+
														'				<td class="labelText" id="pvPopUpMsg"></td>'+
														'			</tr>'+
														'</table>' +
													'</div>';

							// First page?
							document.getElementById('PvPopUpPrev').innerHTML = getDisabledScrollButtonHTML(getWindowLanguageLabel('GBLBACK'), '/' + getWebAppName() + '/equation/images/EqPageUp_off.gif');							

							// Last page?
							if (returnFieldRows.length > maxResults) 
							{									
								returnFieldRows = returnFieldRows.slice(0,maxResults);
							}
							else
							{
								document.getElementById('PvPopUpNext').innerHTML = getDisabledScrollButtonHTML(getWindowLanguageLabel('GBLNEXT'), '/' + getWebAppName() + '/equation/images/EqPageDn_off.gif');
							}

							// Add the header row to the body
							populateHeader('pvPopUpBody');

							// Add the filter fields
							populateFilterFields(gblPromptFieldId,'pvPopUpBody');

							// Add the detail rows
							var i = 0;
							for(i=0; i < returnFieldRows.length; i++)
							{
								populateDetails(gblPromptFieldId,'pvPopUpBody',i,returnFieldRows[i]);
							}

							// Set up first and last index key
							if (returnFieldRows.length > 0)
							{
								gblFirstIndex=returnFieldRows[0].substring(gblBackStart,gblBackEnd);
								gblLastIndex=returnFieldRows[returnFieldRows.length-1].substring(gblBackStart,gblBackEnd);
							}
							
							// No details to report?
							var objElement = document.getElementById('pvPopUpMsg');
							if (returnFieldRows.length==0)
							{
								objElement.innerHTML = getWindowLanguageLabel('GBL900039');
							}
							else
							{
								objElement.innerHTML = '';
							}
												
							if (errorCodes != '')
							{
								//Incomplete GP data?
								var unitslabel = getWindowLanguageLabel('GBL900067');
								unitslabel = unitslabel.replace("%1",errorCodes);
								objElement.innerHTML = unitslabel;
							}
							
							// Adjust the widths
							resizePVTable();

							// Create and display popup
							setupAndDisplay(init);
							
							// Position initial cursor
							setTimeout( 
									function() 
									{
										positionCursor();
									}
									,10);
							
							// Keep track of the number of records
							objElement = document.getElementById('sel_numrec');
							objElement.value = returnFieldRows.length;
							
							// Keep track of the number of column headers
							objElement = document.getElementById('sel_numcol');
							objElement.value = gblFieldLabels.length;
						}
					);
					
	// Mark start of processing
	pv_processing = true;
}

// **********************************************************************************
// function getPVList(direction,maxResults)
//
// Parameters:
// direction - -1 (page up) or 1 (page down)
// maxResults - number of records to retrieved
// **********************************************************************************	
function getPVList(direction,maxResults)
{
	// pending processing?
	if (pv_processing)
	{
		return;
	}

	// get the session id
	var sessionIdentifier = getSessionId();

	// page up or page down?
	gblSearchDirection=direction;
	if(gblSearchDirection==1)
	{
		gblSearchIndex=gblLastIndex;
	}
	else
	{
		gblSearchIndex=gblFirstIndex;
	}
	
	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName('getEqDataList',nsuri);
	var qn_op_resp = new WS.QName('getEqDataListResponse',nsuri);

	prefix = getGlobalProcessingFlags(gblPromptFieldId,gblDataFields,'Y') +
		setupFilterFieldsInDSCCN(getDsccn(gblPromptFieldId,gblDataFields,'Y'));

	// Set the pararmeters required by the service
	var nresult = maxResults + 1;
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'fieldId',value:gblPromptFieldId},
								{name:'service',value:gblPromptId},
								{name:'decode',value:gblPromptDecode},
								{name:'security',value:gblPromptSecurity},
								{name:'primaryTable',value:gblPromptFile},
								{name:'serviceKey',value:gblPromptKeys},
								{name:'query',value:prefix},
								{name:'start',value:gblSearchIndex},
								{name:'direction',value:gblSearchDirection},
								{name:'maxResults',value:nresult}
							];

	// Display the progress bar					
	var obj = document.getElementById('PvPopUpWorking');
	obj.style.display = "";

	// Call the web service
	call.invoke_rpc(
						qn_op,
						getSessionParms,
						null,
						function(call,envelope) 
						{
							// End processing
							pv_processing = false;
							
							var returnFieldString = '';
							try
							{ 
								returnFieldString = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();
							}
							catch (e)
							{
							}
							
							var errorArray = returnFieldString.split("!#ERRORS#!");
							var errorCodes = "";
							
							//If there are errors to return, populate the variable
							if(errorArray.length>1)
							{
								errorCodes = errorArray[errorArray.length-1];
							}
							var returnFieldRows = errorArray[0].split(globalDelimeter);
					
							// no returned data
							if (returnFieldString == '')
							{
								returnFieldRows = '';
							}

							// Reverse the array if we are going backwards...
							if(gblSearchDirection==-1 && returnFieldRows.length>1)
							{
								returnFieldRows.reverse();
							}
							
							// determine actual number of rows to be displayed
							if (returnFieldRows.length > maxResults)
							{
								nresult = maxResults;
							}
							else
							{
								nresult = returnFieldRows.length;
							}
							
							// Clear out what we have
							pvTable = document.getElementById('pvPopUpBody');
							var pvTableSize = pvTable.rows.length;
							var pvTableSeq = -1;
							for (pvTableSeq = pvTableSize - 1;pvTableSeq > numberOfHeaderLine;pvTableSeq--) 
							{
								pvRow = pvTable.rows[pvTableSeq];
								if (pvRow.className != 'tableHeader')
								{
									pvTable.deleteRow(pvTableSeq);
								}
							}
							// No more records to display
							if (returnFieldRows.length <= maxResults) 
							{
								// Last page							
								if (gblSearchDirection == 1)
								{
									document.getElementById('PvPopUpNext').innerHTML = getDisabledScrollButtonHTML(getWindowLanguageLabel('GBLNEXT'), '/' + getWebAppName() + '/equation/images/EqPageDn_off.gif');
									document.getElementById('PvPopUpPrev').innerHTML = pvPopUpEnabledPrevButton.replace("%1",maxResults);
								}
								
								// First page
								if (gblSearchDirection == -1)
								{
									document.getElementById('PvPopUpPrev').innerHTML = getDisabledScrollButtonHTML(getWindowLanguageLabel('GBLBACK'), '/' + getWebAppName() + '/equation/images/EqPageUp_off.gif');
									document.getElementById('PvPopUpNext').innerHTML = pvPopUpEnabledNextButton.replace("%1",maxResults);
								}							
							}
							else
							{
								if (gblSearchDirection == 1)
								{
									returnFieldRows = returnFieldRows.slice(0,maxResults);
									document.getElementById('PvPopUpNext').innerHTML = pvPopUpEnabledNextButton.replace("%1",maxResults);
									document.getElementById('PvPopUpPrev').innerHTML = pvPopUpEnabledPrevButton.replace("%1",maxResults);
								}
								
								if (gblSearchDirection == -1)
								{
									returnFieldRows = returnFieldRows.slice(1);
									document.getElementById('PvPopUpNext').innerHTML = pvPopUpEnabledNextButton.replace("%1",maxResults);
									document.getElementById('PvPopUpPrev').innerHTML = pvPopUpEnabledPrevButton.replace("%1",maxResults);
								}
							}
							
							// Add the detail rows
							var i = 0;
							for(i=0; i < returnFieldRows.length; i++)
							{
								if(returnFieldRows[i]!='')
								{
									populateDetails(gblPromptFieldId,'pvPopUpBody',i,returnFieldRows[i]);
								}
							}

							// Set up first and last index key
							if (returnFieldRows.length > 0)
							{
								gblFirstIndex=returnFieldRows[0].substring(gblBackStart,gblBackEnd);
								gblLastIndex=returnFieldRows[returnFieldRows.length-1].substring(gblBackStart,gblBackEnd);
							}							
							
							// No details to report?
							var pvMessages = document.getElementById('pvPopUpMsg');
								
														
							if (errorCodes != '')
							{
								//Incomplete GP data?
								var unitslabel = getWindowLanguageLabel('GBL900067');
								unitslabel = unitslabel.replace("%1",errorCodes);
								pvMessages.innerHTML = unitslabel;
							}
							
							// Adjust the widths
							resizePVTable();

							// Setup the cancel button
							var lastSelection = 'sel_' + gblPromptFieldId + padl(nresult-1,3,'0');
							var objElement = document.getElementById('pvPopUpCancelButtonTD');
							objElement.innerHTML = pvPopUpCancelButton.replace("%1",lastSelection);

							// Show the popup
							setupAndDisplay(false);
							
							// Position initial cursor
							setTimeout( 
									function() 
									{
										positionCursor();
									}
									,10);
							
							// Hide the progress bar
							var obj = document.getElementById('PvPopUpWorking');
							obj.style.display = "none";
							
							// Keep track of the number of records
							objElement = document.getElementById('sel_numrec');
							objElement.value = returnFieldRows.length;
							
							// Keep track of the number of column headers
							objElement = document.getElementById('sel_numcol');
							objElement.value = gblFieldLabels.length;
						}
					);
	// Mark start of processing
	pv_processing = true;
}


// **********************************************************************************
// function setupPromptFields()
// Concatenate the filter fields 
//
// Parameters:
// init - true if re-initialisation 
// **********************************************************************************	
function setupPromptFields(init)
{
	var obj;
	var promptFieldsStr = '';
	
	// Not existing?
	if (init)
	{
		// Then initialise the filter fields based on the input fields
		for(i=0; i < gblFieldNames.length; i++)
		{
			// default of *
			var filter = '*';
			
			// determine if the field is mapped into
			x=i;
			var index = getDataFieldOfField(gblFieldNames[i],gblArrDataFields);
			i=x;
			if (index >= 0)
			{
				// ensure this is not a DVAL/OVAL field
				if (gblArrDataFields[index+4] == 'N')
				{
					// retrieve the HTML element
					var obj = getField(gblArrDataFields[index+1]);
					if (obj != null)
					{
						// is this data field based on default input mapping?
						if (gblArrDataFields[index+5]=='N')
						{
							filter = obj.value.trim();
						}
						else
						{
							filter = obj.value.substr(gblArrDataFields[index+2], gblArrDataFields[index+3]);
						}
					}
					else if (gblArrDataFields[index+1].substr(0,1) == '.')
					{
						filter = gblArrDataFields[index+1].substr(1);
						if (filter.trim().length == 0)
						{
							filter = '*';
						}
					}
				}
				
				// add an * if there is no asterisk
				if (filter.indexOf('*') == -1)
				{
					filter = filter + '*';
				}

				// ensure it will not exceed length
				len = parseInt(gblFieldLengths[i]);
				if (filter.length >= len)
				{
					filter = filter.substr(0,len);
				}
			}
			promptFieldsStr += globalDelimeter + filter;
		}
	}
	
	// existing
	else
	{
		for (i=0; i<gblFieldLabels.length; i++)
		{
			obj = document.getElementById('filter_' + gblPromptFieldId + i);
			promptFieldsStr += globalDelimeter + obj.value;
		}
	}
	
	// remove the initial delimeter
	promptFieldsStr = promptFieldsStr.substr(globalDelimeter.length);
	
	return promptFieldsStr;
}


// **********************************************************************************
// function pvCreateDiv(title,fieldId,description)
// Create the initial popup for the PV
//
// Parameters:
// title       - field title       
// fieldId     - field id
// description - field description
// **********************************************************************************
function pvCreateDiv()
{
	try
	{
		pvPopUpDIV = document.getElementById('pvPopUpDIV');
		if (pvPopUpDIV==null)
		{
			pvPopUpDIV = document.createElement('div');
			pvPopUpDIV.id = 'pvPopUpDIV';
			
			if (asPopupPVWindow)
			{
				document.getElementById('mainPopupDiv').appendChild(pvPopUpDIV);
			}
			else
			{
				pvPopUpDIV.className = 'pvContainerDIV';
				document.body.appendChild(pvPopUpDIV);
			}
		}
	}
	catch (e)
	{
		pvPopUpDIV = document.createElement('div');
		pvPopUpDIV.id = 'pvPopUpDIV';
		
		if (asPopupPVWindow)
		{
			document.getElementById('mainPopupDiv').appendChild(pvPopUpDIV);
		}
		else
		{
			pvPopUpDIV.className = 'pvContainerDIV';
			document.body.appendChild(pvPopUpDIV);
		}
	}
	
	pvPopUpDIV.innerHTML = 	'<div id="pvPopUp" class="pvContainer">' +
								'<table id="pvPopUpHeader" class="pvHeader" cellpadding="0" cellspacing="0">' + 
									'<tr>' +
										'<td id="pvPopUpTitle" class="labelText">' +
											gblPromptTitle +
										'</td>' +
										'<td class="labelText" align="right" width="100%"></td>' +
										'<td id="PvPopUpWorking" align="center" class="button">' +
										'	<img src="/' + getWebAppName() + '/equation/images/EqSpin.gif" border="0">' +
										'</td>' +
										'<td id="PvPopUpPrev" align="center" class="button" height="18">' +
										pvPopUpEnabledPrevButton.replace("%1",0) +
										'</td>' +
										'<td id="PvPopUpNext" align="center" class="button" height="18">' +
										pvPopUpEnabledNextButton.replace("%1",0) +
										'</td>' +
										'<td id="pvPopUpExit" align="center" class="button" height="18">' +
										'	<a id="pvPopUpExitHref" href= "javascript:dt_pvClosePopUp(true);"  onkeydown="return dt_kbdSelKeyDown2(event,\'pvPopUpExitHref\',null,\'pvPopUpNextHref\')">' +
										'	   <img id="pvPopuUpExitImg" src="/' + getWebAppName() + '/equation/images/EQExit.gif" title="" border="0">' +
										'	</a>' +
										'</td>' +
									'</tr>' +
								'</table>' +
								'<div id="pvPopUpBodyDIV" class="pvBody">' +
									'<table id="pvPopUpBody" cellpadding="0" cellspacing="0"></table>' +
								'</div>' +
								'<table id="pvPopUpFooter" class="pvFooter" cellpadding="0" cellspacing="0">' +
								'</table>' +
							'</div>';
							
	// Disable buttons
	document.getElementById('PvPopUpPrev').innerHTML = getDisabledScrollButtonHTML(getWindowLanguageLabel('GBLBACK'), '/' + getWebAppName() + '/equation/images/EqPageUp_off.gif');							
	document.getElementById('PvPopUpNext').innerHTML = getDisabledScrollButtonHTML(getWindowLanguageLabel('GBLNEXT'), '/' + getWebAppName() + '/equation/images/EqPageDn_off.gif');

	// Populat the filter fields
	populateHeader('pvPopUpBody');
	populateFilterFields(gblPromptFieldId,'pvPopUpBody');

	// Adjust the widths
	resizePVTable();

	// Show the popup
	setupAndDisplay(true);
}

// **********************************************************************************
// function setupAndDisplay()
// Show the PV list
//
// Parameters:
// adjustPos - adjust window position?
// **********************************************************************************
function setupAndDisplay(adjustPos)
{
	if (asPopupPVWindow)
	{
		width = document.getElementById('pvPopUpHeader').offsetWidth;
		height = document.getElementById('mainPopupDiv').offsetHeight - 6;
		adjustWidthHeight(width,height);
		
		// retrieve again the size of the window.  
		// there is a minimum size of the window, in this case resize the table
		x = getClientWidthHeight()[0];
		if (x > document.getElementById('pvPopUpBody').offsetWidth)
		{
			resizePVTable(x);
		}
		
		// right-to-left?
		if (adjustPos && RTL)
		{
			left = parseInt(leftAnchor) - x + ICON_SIZE - 6;
			if (left < 0)
			{
				left = 1;
			}
			window.moveTo(left, getWindowTop()-window_outerHeight);
		}
		
		adjustLeftTop(width,height);
	}
	else
	{
		pvPopup = new PopupWindow('pvPopUpDIV');
		pvPopup.autoHide();
		pvPopup.showPopup(gblPromptFieldId + BUT_PROMPT);
	}
}


// **********************************************************************************
// function setupAndDisplay()
// Show the PV list
//
// Parameters:
// None       
// **********************************************************************************
function resizePVTable(width)
{
	var testwidth = width;
	if (testwidth == null)
	{
		// determine the highest width among body, header and footer
		testwidth = document.getElementById('pvPopUpBody').offsetWidth;
	}

	// does the width exceeds the screen resolution?
	document.getElementById('pvPopUpBodyDIV').style.overflowX="hidden";
	if (testwidth >= (screen.width * 0.75))
	{
		testwidth =  screen.width * 0.75;
		document.getElementById('pvPopUpBodyDIV').style.overflowX = "scroll";
	}
	
	document.getElementById('pvPopUpBody').style.width=testwidth +'px';
	document.getElementById('pvPopUpHeader').style.width=testwidth +'px';
	document.getElementById('pvPopUpFooter').style.width=testwidth +'px';
	document.getElementById('pvPopUpBodyDIV').style.width = testwidth +'px';
	
	// recheck the size again
	if (document.getElementById('pvPopUpHeader').offsetWidth > testwidth)
	{
		testwidth = document.getElementById('pvPopUpHeader').offsetWidth;
		document.getElementById('pvPopUpBody').style.width=testwidth +'px';
		document.getElementById('pvPopUpHeader').style.width=testwidth +'px';
		document.getElementById('pvPopUpFooter').style.width=testwidth +'px';
		document.getElementById('pvPopUpBodyDIV').style.width = testwidth +'px';
	}
}

//**********************************************************************************
// function positionCursor()
// Show the PV list
//
// Parameters:
// None
//**********************************************************************************
function positionCursor()
{
	// position to first selection
	objElement = document.getElementById('sel_' + gblPromptFieldId + '000');
	if (objElement != null)
	{
		objElement.focus();
	}
	
	// Position to first filter 
	else
	{
		i = 0;
		while (true)
		{
			objElement = document.getElementById('filter_' + gblPromptFieldId + i);
			i++;
			
			if (objElement == null)
			{
				return;
			}
			
			if (!objElement.readOnly)
			{
				break;
			}
		}
		objElement.select();
		objElement.focus();
	}
}


//**********************************************************************************
// function rtvNextCell(table, row, command, colPos, colSpan)
// Generate the next cell within the table
//
// Parameters:
// sourceTable  - table
// sourceRow    - the current row
// command      - the command (e.g. NT / SM)
// colPos       - the column position
// colSpan      - the column span
//**********************************************************************************
function rtvNextCell(sourceTable, sourceRow, command, colPos, colSpan)
{
	try
	{
		// next line?
		if (command == 'NT')
		{
			// add a new row
			row = sourceTable.insertRow(sourceTable.rows.length);
			row.className=sourceRow.className;
			
			// add initial columns (note: there is a selection input, hence adjusted by 1)
			label = row.insertCell(0);
			label.colSpan = colPos;
			label.innerHTML = "&nbsp;";
			
			// add to the cell
			label = row.insertCell(1);
			label.colSpan = colSpan;
		}
		
		// same line?
		else if (command == 'SM')
		{
			// add to the cell
			row = sourceRow;
			label = row.insertCell(row.cells.length);
			label.colSpan = colSpan;
		}
		
		// invalid
		else 
		{
			row = sourceRow;
			label = row.insertCell(row.cells.length);
		}
	}
	catch (e)
	{
		row = sourceRow;
		label = row.insertCell(row.cells.length);
	}
	
	// return the cell
	return label;
}