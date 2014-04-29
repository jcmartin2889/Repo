// **********************************************************************************
// function getNaviInfoFromServDir(serviceMethod, elementLoc)
// Processing to invoke the service directory 
// Assumption: to be used by the Navigator view and service directory returns a string
//
// Parameters:
// serviceMethod     - the method to invoke
// div               - division
// elementLoc        - element to receive the string (elementLoc.innerHTML)
// **********************************************************************************	
function getNaviInfoFromServDir(serviceMethod, div, elementLoc)
{		
	var gblNavigatorViewFrame = getFrameNavigatorView();
	var gblToolbarFrame = getFrameToolbar();

	// Display the progress bar
	gblNavigatorViewFrame.document.getElementById(div + 'ProgressBar').style.display = '';
		
	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName(serviceMethod, nsuri);
	var qn_op_resp = new WS.QName(serviceMethod + 'Response',nsuri);

	// Set the pararmeters required by the service
	var sessionIdentifier = getSessionId();
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier}
							];

	// Call the web service
	call.invoke_rpc(
						qn_op,
						getSessionParms,
						null,
						function(call,envelope) 
						{
							var htmlFormat = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();
							var objElement = document.getElementById(elementLoc);
							objElement.innerHTML = htmlFormat;
					      	x = formatTree(document.getElementById(elementLoc));
							gblNavigatorViewFrame.document.getElementById(div + 'ProgressBar').style.display = 'none';
						}
					);
}


//**********************************************************************************
// function getNaviInfoFromServDir(serviceMethod,div,elementLoc, parameter1)
// Processing to invoke the service directory 
// Assumption: to be used by the Navigator view and service directory returns a string
//
// Parameters:
// serviceMethod     - the method to invoke
// div               - division
// elementLoc        - element to receive the string (elementLoc.innerHTML)
// parameter1..x     - parameters
//
//**********************************************************************************	
function getNaviInfoFromServDirWithFilter(serviceMethod,div,elementLoc, searchId, sourceTreeId, targetTreeId, progBarId,
		parameter1, parameter2, parameter3, parameter4, parameter5, parameter6, parameter7, parameter8, parameter9, parameter10)
{	
	var gblNavigatorViewFrame = getFrameNavigatorView();
	var gblToolbarFrame = getFrameToolbar();

	// Display the progress bar
	gblNavigatorViewFrame.document.getElementById(div + 'ProgressBar').style.display = '';
		
	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName(serviceMethod,nsuri);
	var qn_op_resp = new WS.QName(serviceMethod + 'Response',nsuri);

	// Set the parameters required by the service
	var sessionIdentifier = getSessionId();
	if (serviceMethod == 'getAnySpoolFilesHTML')
	{
		var getSessionParms = 	[
									{name:'sessionIdentifier',value:sessionIdentifier},
									{name:'library',value:parameter1},
									{name:'outq',value:parameter2}
								];
	}

	// Call the web service
	call.invoke_rpc(
						qn_op,
						getSessionParms,
						null,
						function(call,envelope) 
						{
							var htmlFormat = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();
							var objElement = document.getElementById(elementLoc);
							objElement.innerHTML = htmlFormat;
					      	x = formatTree(document.getElementById(elementLoc));
					      	
					      	if (searchId != null)
					      	{
						      	naviSearchFilter(searchId,sourceTreeId,targetTreeId,progBarId);
								gblNavigatorViewFrame.document.getElementById(div + 'ProgressBar').style.display = 'none';
					      	}
						}
					);
}


// **********************************************************************************
// function getDataList
// Handle calling of the Equation Data List service
//
// Parameters:
// searchCriteria	- the search criteria
// name			- name of the HTML element
// newSearch 		- search option
//		null - new search
//		0    - new search
//		-1   - backward
//		1    - forward
// service		- the P/V module 
// file			- main file
// fieldname		- equivalent field name in the file
// decode		- decode character to the p/v module
// maxresult		- number of items to retrieve
// **********************************************************************************	
function getDataList(searchCriteria,name,newSearch,service,file,fieldNames,decode,maxResults)
{		
	// Navigator frame
	var gblNavigatorViewFrame = getFrameNavigatorView();

	// construct all the name
	var divPrevious = name + "DivPrevious";
	var divNext = name + "DivNext";
	var divProgressBar = name + "DivProgressBar";
	var divTable = name + "Table";
	var cookieCounter = name + "Counter";
	var cookieCriteria = name + "Criteria";
	var cookieFirstIndex = name + "FirstIndex";
	var cookieLastIndex = name + "LastIndex";
	var cookiePage = name + "Page";
	var cookieEnabledUpHTML = name + "EnabledUpHTML";
	var cookieEnabledDownHTML = name + "EnabledDownHTML";

	// get the element
	var divPreviousElement = gblNavigatorViewFrame.document.getElementById(divPrevious);
	var divNexteElement = gblNavigatorViewFrame.document.getElementById(divNext);
	var divProgressBarElement  = gblNavigatorViewFrame.document.getElementById(divProgressBar);
	var divTableElement = gblNavigatorViewFrame.document.getElementById(divTable);
		
	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName('getEqDataList',nsuri);
	var qn_op_resp = new WS.QName('getEqDataListResponse',nsuri);

	// Store the content of the enabled buttons (up and down arrow)
	var enabledUpHTML = getCookie(cookieEnabledUpHTML);
	var enabledDownHTML = getCookie(cookieEnabledDownHTML);
	if(enabledUpHTML=="" || enabledDownHTML=="")
	{
		enabledUpHTML = gblNavigatorViewFrame.document.getElementById(divPrevious).innerHTML;
		enabledDownHTML = gblNavigatorViewFrame.document.getElementById(divNext).innerHTML;
		setCookie(cookiePage,1);
		setCookie(cookieEnabledUpHTML,enabledUpHTML);
		setCookie(cookieEnabledDownHTML,enabledDownHTML);
	}
		
	// new search?
	var optionList = new String();
	var searchCriteriaIndex ="";
	var currentPage = 1;
	if (newSearch==0)
	{
		divPreviousElement.innerHTML = getDisabledScrollButtonHTML(getLanguageLabel('GBLBACK'), '../images/EqPageUp_off.gif');	
		searchCriteriaIndex = "";
		newSearch = 1;
		setCookie(cookiePage,currentPage);
	}
	// Backwards
	else if (newSearch==-1)
	{
		currentPage = getCookie(cookiePage);
		currentPage = currentPage - 1;
		setCookie(cookiePage,n);
		searchCriteriaIndex = getCookie(cookieFirstIndex);
		divProgressBarElement.style.display = '';
	}
	// Forwards
	else if (newSearch==1)
	{
		currentPage = getCookie(cookiePage);
		currentPage = currentPage + 1;
		setCookie(cookiePage,n);
		divPreviousElement.innerHTML = enabledUpHTML;
		searchCriteriaIndex = getCookie(cookieLastIndex);
		divProgressBarElement .style.display = '';
	}
	setCookie(cookieCriteria,searchCriteria);

	// Progress bar activate
	divProgressBarElement.style.display = '';

	// Set the parameters required by the service
	var sessionIdentifier = getSessionId();
	var nresult = maxResults + 1;
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'service',value:service},
								{name:'decode',value:decode},
								{name:'primaryTable',value:file},
								{name:'serviceKey',value:fieldNames},
								{name:'query',value:searchCriteria},
								{name:'start',value:searchCriteriaIndex},
								{name:'direction',value:newSearch},
								{name:'maxResults',value:nresult}
							];
					
		
	// Call the web service
	call.invoke_rpc(
						qn_op,
						getSessionParms,
						null,
						function(call,envelope) 
						{																						
							var optionList = getAttributeValue(envelope.get_body().get_all_children()[0].get_all_children()[0].get_value(),'dataList');
							var optionListOptions = optionList.split(globalDelimeter);

							// reverse the table if we are going backwards...
							if (newSearch==-1)
							{									
								optionListOptions.reverse();
							}											
							
							// last page - this is the last page if the maxresult+1 element is blank
							if (optionListOptions[nresult]==null) 
							{									
								if(newSearch!=-1)	
								{																	
									divNexteElement.innerHTML = getDisabledScrollButtonHTML(getLanguageLabel('GBLNEXT'), '../images/EqPageDn_off.gif');
								}
							}
							else{
								divNexteElement.innerHTML = enabledDownHTML;
							
							}
							
							// page one?
							if(currentPage == 1)
							{
								divPreviousElement.innerHTML = getDisabledScrollButtonHTML(getLanguageLabel('GBLBACK'), '../images/EqPageUp_off.gif');														
							}
														
							// clear the table
							if (optionListOptions[0] == "") //or cpage = 1
							{
								divPreviousElement.innerHTML = getDisabledScrollButtonHTML(getLanguageLabel('GBLBACK'), '../images/EqPageUp_off.gif');														
								// no more (grey out up/down)
							}
							else
							{								
								//more (ensure up/down not greyed out....)
								var outputTableSize = divTableElement.rows.length;
								var outputTableSeq = -1;
								for (outputTableSeq = outputTableSize - 1;outputTableSeq > 0;outputTableSeq--) 
								{
									divTableElement.deleteRow(outputTableSeq);
								}
								
								// loop through the options and bang em into the table
								var optionListSize = optionListOptions.length;
								var optionListSeq = -1;
								
								// recalculate the size if it is greater than the maximum result
								if (optionListSize > nresult) 
								{
									optionListSize = maxResutls;
								}
								
								for (optionListSeq = 0; optionListSeq < optionListSize; optionListSeq++) 
								{
									// trim  the return value
									optionListOptions[optionListSeq] = optionListOptions[optionListSeq].rplHyphens();
									optionListOptions[optionListSeq] = optionListOptions[optionListSeq].trim();
	
									// Insert a row
									var optionListRow = divTableElement.insertRow(optionListSeq + 1);
	
									// *************************************************
									// TODO: we need to parse the returned data!!
									// we need the positions of the key
									// we need the positions of the details we want to display
									// *************************************************
	
									// Store the first option in the list
									if(optionListSeq == 0)
									{
										setCookie("optionFirstIndex",optionListOptions[optionListSeq].substring(0,3));
									}
									
									// Store the last option in the list
									if(optionListSeq == optionListSize-1)
									{
										setCookie("optionLastIndex",optionListOptions[optionListSeq].substring(0,3));
									}
	
									// Insert the cell for the mnemonic
									var optionMnemonicCell = optionListRow.insertCell(0);
									optionMnemonicCell.className='labelText';
									
									//insert <a> tags around text to form link to routeToOption.. 									
									var optInnerHtml = '<a class="wf_PVS" href ="javascript:routeToOption(\'*D\',' + '\'' + unitId + '\',' + '\'' + optionListOptions[optionListSeq].substring(0,3) + '\'' + ',\'\')">';
									optInnerHtml += optionListOptions[optionListSeq].substring(0,3);
									optInnerHtml += '</a>';
									optionMnemonicCell.innerHTML =  optInnerHtml;
	
									// Insert the cell for the title
									var optionTitleCell = optionListRow.insertCell(1);
									optionTitleCell.className='labelText';
									optionTitleCell.innerHTML = '<a class="wf_PVS" href ="javascript:routeToOption(\'*D\',' + '\'' + unitId + '\',' + '\'' + optionListOptions[optionListSeq].substring(0,3) + '\'' + ',\'\')">' + optionListOptions[optionListSeq].substring(10).nbsp(); + '</a>';
	
									// Insert the cell to fill up the row
									var optionFillerCell = optionListRow.insertCell(2);
									optionFillerCell.className='labelText';
									optionFillerCell.align='right';
									optionFillerCell.width='100%';
								}
							}								
							divProgressBarElement .style.display = 'none';
						}
					);
}
