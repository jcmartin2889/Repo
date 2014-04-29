
/* **********************************************************************************
 * Get the option list
 * **********************************************************************************/
function getOptionList(optionSearch,optionTable,newSearch)
{
	var gblNavigatorViewFrame = getFrameNavigatorView();
	var gblToolbarFrame = getFrameToolbar();
		
	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName('getEqDataList',nsuri);
	var qn_op_resp = new WS.QName('getEqDataListResponse',nsuri);

	var optionList = new String();
	var optionSearchIndex ="";
	var maxResults = 45;
	
	if(window.top.enabledDownHTML=="" || window.top.enabledDownHTML==null)
	{
		window.top.enabledUpHTML = gblNavigatorViewFrame.document.getElementById('SearchDivPrevious').innerHTML;
		window.top.enabledDownHTML = gblNavigatorViewFrame.document.getElementById('SearchDivNext').innerHTML;
		window.top.cSearchPage = 1;
	}
	var enabledUpHTML = window.top.enabledUpHTML;
	var enabledDownHTML = window.top.enabledDownHTML;
		
	if (newSearch==null)
	{
		window.top.cSearchPage = 1;
		gblNavigatorViewFrame.document.getElementById('SearchDivPrevious').innerHTML = getDisabledScrollButtonHTML(getLanguageLabel('GBLBACK'), '../images/EqPageUp_off.gif');	
		setCookie("optionSearchIndex","");
		optionSearchIndex = "";
		newSearch = 1;
		gblToolbarFrame.document.getElementById('progressBarButton').style.display = '';
		gblToolbarFrame.document.getElementById('optionSearch').disabled = true;
	}
	else if (newSearch==0)
	{
		window.top.cSearchPage = 1;
		gblNavigatorViewFrame.document.getElementById('SearchDivPrevious').innerHTML = getDisabledScrollButtonHTML(getLanguageLabel('GBLBACK'), '../images/EqPageUp_off.gif');	
		setCookie("optionSearchIndex","");
		optionSearchIndex = "";
		newSearch = 1;
		gblToolbarFrame.document.getElementById('progressBarButton').style.display = '';
		gblToolbarFrame.document.getElementById('optionSearch').disabled = true;
	}
	// Backwards
	else if (newSearch==-1)
	{
		window.top.cSearchPage--;
		optionSearchIndex = getCookie("optionFirstIndex");
		gblNavigatorViewFrame.document.getElementById('SearchDivProgressBar').style.display = '';
	}
	// Forwards
	else if (newSearch==1)
	{
		window.top.cSearchPage++;
		gblNavigatorViewFrame.document.getElementById('SearchDivPrevious').innerHTML = enabledUpHTML;
		optionSearchIndex = getCookie("optionLastIndex");
		gblNavigatorViewFrame.document.getElementById('SearchDivProgressBar').style.display = '';
	}
	setCookie("optionSearchText",optionSearch);

	// Set the pararmeters required by the service
	var optionFilter = '*  ';
	var userFilter   = '*   ';
	var appFilter    = '*  ';
	var descFilter   = '*' + optionSearch.trim() + '*';
	var optionSearch2 = optionFilter + userFilter + appFilter + descFilter;
	
	var sessionIdentifier = getSessionId();
	var nresult = maxResults + 1;
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'service',value:'GBR39R'},
								{name:'decode',value:''},
								{name:'security',value:''},
								{name:'primaryTable',value:'GBPF'},
								{name:'serviceKey',value:'GBOID'},
								{name:'query',value:optionSearch2},
								{name:'start',value:optionSearchIndex},
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
							var optionList = '';
							try
							{ 
								optionList = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();
							}
							catch (e)
							{
							}
							var optionListOptions = optionList.split(globalDelimeter);
							
							// Reverse the array if we are going backwards
							if (newSearch==-1)
							{									
								optionListOptions.reverse();
							}											

							// No more records to display
							if (optionListOptions.length <= maxResults) 
							{
								// Last page							
								if (newSearch == 1)
								{
									gblNavigatorViewFrame.document.getElementById('SearchDivNext').innerHTML = getDisabledScrollButtonHTML(getLanguageLabel('GBLNEXT'), '../images/EqPageDn_off.gif');
									gblNavigatorViewFrame.document.getElementById('SearchDivPrevious').innerHTML = enabledUpHTML;
								}
								
								// First page
								if (newSearch == -1)
								{
									gblNavigatorViewFrame.document.getElementById('SearchDivPrevious').innerHTML = getDisabledScrollButtonHTML(getLanguageLabel('GBLBACK'), '../images/EqPageUp_off.gif');
									gblNavigatorViewFrame.document.getElementById('SearchDivNext').innerHTML = enabledDownHTML;
								}							
							}
							else
							{
								if (newSearch == 1)
								{
									optionListOptions = optionListOptions.slice(0,maxResults);
									gblNavigatorViewFrame.document.getElementById('SearchDivNext').innerHTML = enabledDownHTML;
									gblNavigatorViewFrame.document.getElementById('SearchDivPrevious').innerHTML = enabledUpHTML;
								}
								
								if (newSearch == -1)
								{
									optionListOptions = optionListOptions.slice(1);
									gblNavigatorViewFrame.document.getElementById('SearchDivNext').innerHTML = enabledDownHTML;
									gblNavigatorViewFrame.document.getElementById('SearchDivPrevious').innerHTML = enabledUpHTML;
								}
							}

							// first page
							if(window.top.cSearchPage == 1)
							{
								gblNavigatorViewFrame.document.getElementById('SearchDivPrevious').innerHTML = getDisabledScrollButtonHTML(getLanguageLabel('GBLBACK'), '../images/EqPageUp_off.gif');														
							}

							// clear the table
							var optionTableSize = optionTable.rows.length;
							var optionTableSeq = -1;
							for (optionTableSeq = optionTableSize - 1;optionTableSeq > 0;optionTableSeq--) 
							{
								optionTable.deleteRow(optionTableSeq);
							}
														
							// load the data into the table
							if (optionListOptions[0] != "")
							{
								// loop through the options and bang em into the table
								var optionListSize = optionListOptions.length;
								var optionListSeq = -1;

								for (optionListSeq = 0;optionListSeq < optionListSize;optionListSeq++) 
								{
									// trim  the return value
									optionListOptions[optionListSeq] = optionListOptions[optionListSeq].rplHyphens();
									optionListOptions[optionListSeq] = optionListOptions[optionListSeq].trim();

									// Insert a row
									var optionListRow = optionTable.insertRow(optionListSeq + 1);
	
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
									var optInnerHtml = '<a class="wf_PVS" href ="javascript:routeToOption2(\'*D\',' + '\'' + unitId + '\',' + '\'' + optionListOptions[optionListSeq].substring(0,3) + '\'' + ',\'\')">';
									optInnerHtml += optionListOptions[optionListSeq].substring(0,3);
									optInnerHtml += '</a>';
									optionMnemonicCell.innerHTML =  optInnerHtml;
	
									// Insert the cell for the title
									var optionTitleCell = optionListRow.insertCell(1);
									optionTitleCell.className='labelText';
									optionTitleCell.innerHTML = '<a class="wf_PVS" href ="javascript:routeToOption2(\'*D\',' + '\'' + unitId + '\',' + '\'' + optionListOptions[optionListSeq].substring(0,3) + '\'' + ',\'\')">' + optionListOptions[optionListSeq].substring(10).nbsp(); + '</a>';
	
									// Insert the cell to fill up the row
									var optionFillerCell = optionListRow.insertCell(2);
									optionFillerCell.className='labelText';
									optionFillerCell.align='right';
									optionFillerCell.width='100%';
								}
							}								
							gblToolbarFrame.document.getElementById('progressBarButton').style.display = 'none';
							gblToolbarFrame.document.getElementById('optionSearch').disabled = false;
							gblNavigatorViewFrame.document.getElementById('SearchDivProgressBar').style.display = 'none';
							//Set focus back to the search input field
							gblToolbarFrame.document.getElementById('optionSearch').focus(); 
							
						}
					);
}


function destroyPools(serviceMethod)
{		
	var currentFrame = getFrameCurrent();
	if (currentFrame.eqDriver != 'Y')
	{
		errorAlert(20,getLanguageLabel("GBL900037"));
	}
	else
	{		
		window.top.poolsDestroyed = true;
		
		// need something to locate the web service defined wsdlsoap:address in the wsdl
		var sessionIdentifier = getSessionId();
		var call = getWSCall(); 
		
		// The targetNamespace defined in the wsdl
		var nsuri = getEquationServicePath();
	
		// Define the get and response
		var qn_op = new WS.QName(serviceMethod,nsuri);
		var qn_op_resp = new WS.QName(serviceMethod + 'Response',nsuri);
	
		// Set the pararmeters required by the service
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
								displayMessage(getLanguageLabel("GBL900036"));
								logoff_Processing();								
								closeBrowser();
							}
					);
	}				
}

// **********************************************************************************
// function navi_resize()
// Handle resizing event for the navigator view
//
// Parameters:
// None
// **********************************************************************************	
function navi_resize()
{
	var gblNavigatorViewFrame = getFrameNavigatorView();
	var docId = gblNavigatorViewFrame.document;
	
	// get the size of each navigator bar 
	var tabsize = 0;
	var divs = docId.getElementsByTagName('div');

	for (var i=0;i<divs.length;i++)
	{
		try{
			tabsize = tabsize + docId.getElementById(divs.item(i).id + "Header").offsetHeight;
		}
		catch(e){}
	}
	
	// compute the size
	var size = (gblNavigatorViewFrame.frameElement.offsetHeight-tabsize);
	if (size <= 0)
	{
		return;	
	}

	// set the size	
	for (var j=0;j<divs.length;j++)
	{
		try{
			docId.getElementById(divs.item(j).id).style.height = size + "px";
		}
		catch(e){}
	}	
}


//**********************************************************************************
// function refreshCache()
// Refresh desktop cache
//
// Parameters:
// None
//**********************************************************************************	
function refreshDesktopCache()
{		
	var currentFrame = getFrameCurrent();
	if (currentFrame.eqDriver != 'Y')
	{
		errorAlert(20,getLanguageLabel("GBL900037"));
	}
	else if(confirmMessage(getLanguageLabel("GBL900079")))
	{
		// need something to locate the web service defined wsdlsoap:address in the wsdl
		var sessionIdentifier = getSessionId();
		var call = getWSCall(); 
		
		// The targetNamespace defined in the wsdl
		var nsuri = getEquationServicePath();
	
		// Define the get and response
		var qn_op = new WS.QName("refreshDesktopCache",nsuri);
		var qn_op_resp = new WS.QName("refreshDesktopCache" + 'Response',nsuri);
	
		// Set the parameter required by the service
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
								displayMessage(getLanguageLabel("GBL900080"));
							}
					);
	}				
}
