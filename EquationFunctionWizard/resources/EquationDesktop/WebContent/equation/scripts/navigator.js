var previousSearchText = 'DUMMY SEARCH RESULT';
var anySpoolFileShutterAlreadyOpened = false;
var anySpoolFileBluringEventActive = false;

// **********************************************************************************
// function navi_onkeydown(e,searchId,sourceTreeId,targetTreeId,progBarId)
// Perform filtering 
//
// Parameters:
// searchId     - input element
// sourceTreeId - source tree
// targetTreeId - target tree
// progBarId    - progressbar id
// **********************************************************************************	
function navi_onkeydown(e,searchId,sourceTreeId,targetTreeId,progBarId)
{
	var keynum = rtvKeyboardKey(e);
	
	// Enter key
	if (keynum==13)
	{
		previousSearchText = "";
		naviSearchFilter(searchId,sourceTreeId,targetTreeId,progBarId);
	}
}

//**********************************************************************************
// function navi_onkeyup(e,searchId,sourceTreeId,targetTreeId,progBarId)
// Perform filtering 
//
// Parameters:
// searchId     - input element
// sourceTreeId - source tree
// targetTreeId - target tree
// progBarId    - progressbar id
//**********************************************************************************	
function navi_onkeyup(e,searchId,sourceTreeId,targetTreeId,progBarId)
{
	naviSearchFilter(searchId,sourceTreeId,targetTreeId,progBarId);
}

// **********************************************************************************
// function naviSearchFilter(searchId,sourceTreeId,targetTreeId)
// Perform seach on a tree 
//
// Parameters:
// searchId     - input element
// sourceTreeId - source tree
// targetTreeId - target tree
// progBarId    - progressbar id
// **********************************************************************************	
function naviSearchFilter(searchId,sourceTreeId,targetTreeId,progBarId)
{
	var searchText = document.getElementById(searchId).value.trim().toUpperCase();
	var sourceTree = document.getElementById(sourceTreeId);
	var targetTree = document.getElementById(targetTreeId);
	
	// same searchText as before?
	if (searchText == previousSearchText)
	{
		return;
	}
	
	// store search text for next comparison
	previousSearchText = searchText;
	
	// progress bar defined?
	if (progBarId != null)
	{
		var gblNavigatorViewFrame = getFrameNavigatorView();
		gblNavigatorViewFrame.document.getElementById(progBarId).style.display = '';
	}
	
	// spawn in another thread
	setTimeout(function (){							  
		     			navi_startSearch(searchText,sourceTree,targetTree,progBarId); 
	    			},1);
}

// **********************************************************************************
// function naviSearchFilter(searchId,sourceTreeId,targetTreeId,progBarId)
// Perform search on subchilds 
//
// Parameters:
// searchIText - search text
// sourceTree  - source tree
// targetTree  - target tree
// progBarId    - progressbar id
// **********************************************************************************	
function navi_startSearch(searchText,sourceTree,targetTree,progBarId)
{
	// clear the search output
	targetTree.innerHTML = '';
	
	// traverse the tree
	naviSearchFilterRecurse(searchText,sourceTree,sourceTree,targetTree,targetTree.id);

	// add some more LI and UL!
	var innerHTML = targetTree.innerHTML; 
	targetTree.innerHTML = "<li>" + getLanguageLabel('GBL000079') + "<ul>" + innerHTML + "</ul></li>";

	// format the tree
	x = formatTree(targetTree);
	expandTree(targetTree.id);
	
	// progressbar defined?
	if (progBarId != null)
	{
		var gblNavigatorViewFrame = getFrameNavigatorView();
		gblNavigatorViewFrame.document.getElementById(progBarId).style.display = 'none';
	}
	
}

// **********************************************************************************
// function naviSearchFilter(searchId,sourceTreeId,targetTreeId)
// Perform search on subchilds 
//
// Parameters:
// searchIText - search text
// sourceNode  - source node
// sourceTree  - source tree
// targetTree  - target tree
// topLiId     - top level LI id 
// **********************************************************************************	
function naviSearchFilterRecurse(searchText,sourceNode,sourceTree,targetTree,topLiId)
{
	for (var itemi=0;itemi<sourceNode.childNodes.length;itemi++) 
	{
		var item = sourceNode.childNodes[itemi];

		// anchor node?
		if (item.nodeName == 'A')
		{
			navi_item_add(searchText,item,sourceTree,targetTree,topLiId);
		}
		else if (item.nodeName == 'UL')
		{
			var liId = topLiId;
			naviSearchFilterRecurse(searchText,item,sourceTree,targetTree,liId);
		}
		else if (item.nodeName == 'LI')
		{
			var liId = item.id;
			naviSearchFilterRecurse(searchText,item,sourceTree,targetTree,liId);
		}
		else if (item.nodeName == 'SPAN')
		{
			item.className =  item.className.replace(' wf_SELECTED','');
		}
	}
}


// **********************************************************************************
// function navi_item_add(searchText,item,targetTree)
// Determine if the item will be added or not 
//
// Parameters:
// searchIText - search text
// item        - source node
// sourceTree  - source tree
// targetTree  - target tree
// topLiId     - top level LI id 
// **********************************************************************************	
function navi_item_add(searchText,item,sourceTree,targetTree,topLiId)
{
	// node A only (anchor tag)
	if (item.nodeName != "A")
	{
		return;
	}

	// remove the selected
	var parent = item.parentElement;
	parent.className =  parent.className.replace(' wf_SELECTED','');

	// is the search text in the item
	var n = item.innerHTML.indexOf(searchText);
	if (n<=-1 || searchText.length==0)
	{
		return;
	}

	// highlight it	
	parent.className = parent.className + " wf_SELECTED";
	
	// create the node
	var onClickAction = "expandToItem('" + sourceTree.id + "','" + topLiId + "')";
	var imageButton = getImageButtonHTML(item.title,item.innerHTML,onClickAction,'../images/search2.gif').split('£x£')[0];
	var child = imageButton + item.outerHTML;
	
	var childNode = document.createElement('li');
	childNode.innerHTML = child;
	
	// add to the target tree
	targetTree.appendChild(childNode);
}


//**********************************************************************************
// function combinedLoadFilter(e, shutterId, serviceMethod, elementLoc, searchId, sourceTreeId, targetTreeId, progBarId)
// Perform refresh and filtering 
//
// Parameters:
// shutterId, serviceMethod, elementDoc - see switchMenu()
// searchId, sourceTreeId, targetTreeId, progBarId - see naviSearchFilter()
// parameter1..x: parameters
//**********************************************************************************	
function combinedRefreshFilter(shutterId, serviceMethod, elementLoc, searchId, sourceTreeId, targetTreeId, progBarId, 
		parameter1, parameter2, parameter3, parameter4, parameter5, parameter6, parameter7, parameter8, parameter9, parameter10)
{
	// load the details
	if (serviceMethod != null)
	{
		previousSearchText = "";
		getNaviInfoFromServDirWithFilter(serviceMethod,shutterId,elementLoc, 
									searchId, sourceTreeId, targetTreeId, progBarId,
									parameter1, parameter2, parameter3, parameter4, parameter5,
									parameter6, parameter7, parameter8, parameter9, parameter10);
	}
}


//**********************************************************************************
// function anySpoolFileLibrary_keydown(anySpoolLibraryText, anySpoolText, e, progBarId)
// Key down event for the library 
//
// Parameters:
// anySpoolLibraryText - the HTML input field containing the library
// anySpoolText        - the HTML input field containing the output queue
// e                   - event
// progBarId           - progress bar id
//**********************************************************************************	
function anySpoolFileLibrary_keydown(anySpoolLibraryText, anySpoolOutputQueueCombo, e, progBarId)
{
	var keynum = rtvKeyboardKey(e);
	
	// Enter key
	if (keynum==13)
	{
		anySpoolFileBluringEventActive = false;
		anySpoolLibraryText.blur();
		anySpoolLibraryText.focus();
	}
}


//**********************************************************************************
// function anySpoolFileLibrary_blur(anySpoolLibraryText, anySpoolOutputQueueCombo, e, progBarId)
// On blur event for the library 
//
// Parameters:
// anySpoolLibraryText       - the HTML input field containing the library
// anySpoolOutputQueueCombo  - combo box to contain the list of output queue
// anySpoolText        - the HTML input field containing the output queue
// e                   - event
// progBarId           - progress bar id
//**********************************************************************************	
function anySpoolFileLibrary_blur(anySpoolLibraryText, anySpoolOutputQueueCombo, anySpoolText, e, progBarId)
{
	if (anySpoolFileBluringEventActive)
	{
		return;
	}
	
	anySpoolFileBluringEventActive = true;
	getListOfOutputQueues(anySpoolLibraryText,anySpoolOutputQueueCombo,anySpoolText,progBarId);
}


//**********************************************************************************
// function anySpoolFileOutputQueue_keydown(anySpoolLibraryText, anySpoolText, e, shutterId, serviceMethod, elementLoc, searchId, sourceTreeId, targetTreeId, progBarId)
// Key down event for the library for the output queue 
//
// Parameters:
// anySpoolLibraryText - the HTML input field containing the library
// anySpoolText - the HTML input field containing the output queue
// shutterId, serviceMethod, elementDoc - see switchMenu()
// e, searchId, sourceTreeId, targetTreeId, progBarId - see naviSearchFilter();
//**********************************************************************************	
function anySpoolFileOutputQueue_keydown(anySpoolLibraryText, anySpoolText, e, shutterId, serviceMethod, elementLoc, searchId, sourceTreeId, targetTreeId, progBarId)
{
	var keynum = rtvKeyboardKey(e);
	
	// Enter key
	if (keynum==13)
	{
		anySpoolFileOutputQueue_click(anySpoolLibraryText, anySpoolText, shutterId, serviceMethod, elementLoc, searchId, sourceTreeId, targetTreeId, progBarId);
	}
}

//**********************************************************************************
// function anySpoolFileOutputQueue_keydown(anySpoolLibraryText, anySpoolText, e, shutterId, serviceMethod, elementLoc, searchId, sourceTreeId, targetTreeId, progBarId)
// Output queue Combo box on change 
//
// Parameters:
// anySpoolLibraryText - the HTML input field containing the library
// anySpoolOutputQueueCombo - the HTML select field containing the list of output queues
// shutterId, serviceMethod, elementDoc - see switchMenu()
// e, searchId, sourceTreeId, targetTreeId, progBarId - see naviSearchFilter();
//**********************************************************************************	
function anySpoolOutputQueueCombo_onchange(anySpoolLibraryText, anySpoolOutputQueueCombo, e, shutterId, serviceMethod, elementLoc, searchId, sourceTreeId, targetTreeId, progBarId)
{
	// library not specified, then default to QGPL
	if (anySpoolLibraryText.value.trim() == '')
	{
		anySpoolLibraryText.value = "QGPL";
	}
	
	// output queue not specified?
	if (anySpoolOutputQueueCombo.selectedIndex == -1)
	{
		return;
	}

	var outq = anySpoolOutputQueueCombo.options[anySpoolOutputQueueCombo.selectedIndex].value;
	combinedRefreshFilter(shutterId, serviceMethod, elementLoc, 
				searchId, sourceTreeId, targetTreeId, progBarId, 
				anySpoolLibraryText.value, outq);
	
	obj = document.getElementById('anySpoolFileCurrentDisplay');
	obj.innerHTML = anySpoolLibraryText.value.toUpperCase() + '/' + outq.toUpperCase();
}


//**********************************************************************************
// function anySpoolFileOutputQueue_keydown(anySpoolLibraryText, anySpoolText, e, shutterId, serviceMethod, elementLoc, searchId, sourceTreeId, targetTreeId, progBarId)
// Key down event for the library for the output queue 
//
// Parameters:
// anySpoolLibraryText - the HTML input field containing the library
// anySpoolText - the HTML input field containing the output queue
// shutterId, serviceMethod, elementDoc - see switchMenu()
// e, searchId, sourceTreeId, targetTreeId, progBarId - see naviSearchFilter();
//**********************************************************************************	
function anySpoolFileOutputQueue_click(anySpoolLibraryText, anySpoolText, shutterId, serviceMethod, elementLoc, searchId, sourceTreeId, targetTreeId, progBarId)
{
	// library not specified, then default to QGPL
	if (anySpoolLibraryText.value.trim() == '')
	{
		anySpoolLibraryText.value = "QGPL";
	}
	
	// output queue not specified?
	if (anySpoolText.value.trim() == '')
	{
		alert(getLanguageLabel("GBL900068"));
		return;
	}
	
	combinedRefreshFilter(shutterId, serviceMethod, elementLoc, 
				searchId, sourceTreeId, targetTreeId, progBarId, 
				anySpoolLibraryText.value, anySpoolText.value);
	
	obj = document.getElementById('anySpoolFileCurrentDisplay');
	obj.innerHTML = anySpoolLibraryText.value.toUpperCase() + '/' + anySpoolText.value.toUpperCase();
}


//**********************************************************************************
// function anySpoolFileOutputQueue_refresh(anySpoolFileCurrentDisplay, shutterId, serviceMethod, elementLoc, searchId, sourceTreeId, targetTreeId, progBarId)
// Refresh the currently selected display file 
//
// Parameters:
// anySpoolFileCurrentDisplay - the HTML field containing the current library and output queue
// shutterId, serviceMethod, elementDoc - see switchMenu()
// e, searchId, sourceTreeId, targetTreeId, progBarId - see naviSearchFilter();
//**********************************************************************************	
function anySpoolFileOutputQueue_refresh(anySpoolFileCurrentDisplay, shutterId, serviceMethod, elementLoc, searchId, sourceTreeId, targetTreeId, progBarId)
{
	// output queue not specified?
	if (anySpoolFileCurrentDisplay.innerHTML.trim() == '')
	{
		var obj = document.getElementById(elementLoc);
		obj.innerHTML = getLanguageLabel("GBL900073"); 
		return;
	}
	
	var i = anySpoolFileCurrentDisplay.innerHTML.indexOf("/");
	var lib = anySpoolFileCurrentDisplay.innerHTML.substring(0,i);
	var outq = anySpoolFileCurrentDisplay.innerHTML.substring(i+1);
	
	combinedRefreshFilter(shutterId, serviceMethod, elementLoc, 
				searchId, sourceTreeId, targetTreeId, progBarId, 
				lib, outq);
}

//**********************************************************************************
// function navi_onkeydown(e,searchId,sourceTreeId,targetTreeId,progBarId)
// Perform filtering 
//
// Parameters:
// searchId     - input element
// sourceTreeId - source tree
// targetTreeId - target tree
// progBarId    - progressbar id
//**********************************************************************************	
function anySpoolFileFilter_keydown(anySpoolFileCurrentDisplay,e,searchId,sourceTreeId,targetTreeId,progBarId)
{
	// user has not selected any out queue
	if (anySpoolFileCurrentDisplay.innerHTML.trim() == '')
	{
		return;
	}
	
	var keynum = rtvKeyboardKey(e);
	
	// Enter key
	if (keynum==13)
	{
		previousSearchText = "";
		naviSearchFilter(searchId,sourceTreeId,targetTreeId,progBarId);
	}
}

//**********************************************************************************
// function navi_onkeyup(e,searchId,sourceTreeId,targetTreeId,progBarId)
// Perform filtering 
//
// Parameters:
// searchId     - input element
// sourceTreeId - source tree
// targetTreeId - target tree
// progBarId    - progressbar id
//**********************************************************************************	
function anySpoolFileFilter_keyup(anySpoolFileCurrentDisplay,e,searchId,sourceTreeId,targetTreeId,progBarId)
{
	// user has not selected any out queue
	if (anySpoolFileCurrentDisplay.innerHTML.trim() == '')
	{
		return;
	}
	
	naviSearchFilter(searchId,sourceTreeId,targetTreeId,progBarId);
}


//**********************************************************************************
// function anySpoolFileShutterOpen(anySpoolLibraryText,anySpoolOutputQueueCombo,anySpoolText,progBarId) 
// This is executed once when the shutter is opened 
//
// Parameters:
// anySpoolLibraryText       - HTML input field for the library
// anySpoolOutputQueueCombo  - combo box to contain the list of output queue
// anySpoolText        		 - the HTML input field containing the output queue
// progBarId				 - the progress bar id
//**********************************************************************************	
function anySpoolFileShutterOpen(anySpoolLibraryText,anySpoolOutputQueueCombo,anySpoolText,progBarId)
{
	// first time opening?
	if (anySpoolFileShutterAlreadyOpened)
	{
		return;
	}
	
	// set the values of anySpoolLibraryText and anySpoolText
	var anySpoolFileCurrentDisplay = document.getElementById('anySpoolFileCurrentDisplay');
	if (anySpoolFileCurrentDisplay.innerHTML.trim() != '')
	{
		anySpoolFileCurrentDisplay.innerHTML = anySpoolFileCurrentDisplay.innerHTML.trim();
		var i = anySpoolFileCurrentDisplay.innerHTML.indexOf("/");
		
		anySpoolLibraryText.value = anySpoolFileCurrentDisplay.innerHTML.substring(0,i);
		anySpoolText.value = anySpoolFileCurrentDisplay.innerHTML.substring(i+1);
	
	}
	
	// mark as already open
	anySpoolFileShutterAlreadyOpened = true;
	getListOfOutputQueues(anySpoolLibraryText,anySpoolOutputQueueCombo,anySpoolText,progBarId);
	
	// refresh the page 
	if (anySpoolFileCurrentDisplay.innerHTML.trim() != '') {
		anySpoolFileOutputQueue_refresh(anySpoolFileCurrentDisplay, 'AnySpoolDiv',
			'getAnySpoolFilesHTML','eqAnySpool','anySpoolFilterText','eqAnySpool','eqFilterAnySpool','AnySpoolDivProgressBar');
	}
		
}


//**********************************************************************************
// function getListOfOutputQueues(anySpoolLibraryText,anySpoolOutputQueueCombo,progBarId, elementLoc) 
// Retrieve the list of output queues 
//
// Parameters:
// anySpoolLibraryText       - HTML input field for the library
// anySpoolOutputQueueCombo  - combo box to contain the list of output queue
// anySpoolText       		 - HTML input field for the output queue
// progBarId				 - the progress bar id
//**********************************************************************************	
function getListOfOutputQueues(anySpoolLibraryText,anySpoolOutputQueueCombo,anySpoolText,progBarId)
{		
	var gblNavigatorViewFrame = getFrameNavigatorView();
	var gblToolbarFrame = getFrameToolbar();

	// library not specified, then default to QGPL
	if (anySpoolLibraryText.value.trim() == '')
	{
		anySpoolLibraryText.value = "QGPL";
	}
	
	// Display the progress bar
	gblNavigatorViewFrame.document.getElementById(progBarId).style.display = '';
		
	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName('getListOutputQueue',nsuri);
	var qn_op_resp = new WS.QName('getListOutputQueue' + 'Response',nsuri);

	// Set the pararmeters required by the service
	var sessionIdentifier = getSessionId();
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'library',value:anySpoolLibraryText.value.toUpperCase()}
							];

	// Call the web service
	call.invoke_rpc(
						qn_op,
						getSessionParms,
						null,
						function(call,envelope) 
						{
							var list = "";
							try
							{
								list = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();
							}
							catch (e)
							{
							}
							
							var arr = list.split(GLOBAL_DELIMETER);

							// error?
							if (list.length >= 2)
							{
								error = list.substring(0,2);
								if (error=='20')
								{
									gblNavigatorViewFrame.document.getElementById(progBarId).style.display = 'none';
									alert(list.substring(2));
									anySpoolFileBluringEventActive = false;
									return;
								}
							}
							
							
							anySpoolOutputQueueCombo.length = 0;
							for (i=0; i < arr.length; i++)
							{								
								if (arr[i].trim() != '')
								{
									var elOptNew = document.createElement('option');
									elOptNew.text = arr[i];
									elOptNew.value = arr[i];
								
									if (anySpoolText.value.trim() != '' && anySpoolText.value.trim() == elOptNew.value) { 
										elOptNew.selected = true;
									}
									
									try 
									{
										anySpoolOutputQueueCombo.add(elOptNew, null); // standards compliant; doesn't work in IE
									}
									catch(ex) 
									{
										anySpoolOutputQueueCombo.add(elOptNew); // IE only
									}
								}
							}
							
							// default to the first output queue
							if (i>=1)
							{
								if (anySpoolText.value.trim() == '') { 
									anySpoolText.value = arr[0];
								}
							}
							
							gblNavigatorViewFrame.document.getElementById(progBarId).style.display = 'none';
							anySpoolFileBluringEventActive = false;
						}
					);
}
