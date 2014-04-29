var sortingInProgress = false;

//**********************************************************************************
// function sortColumn(repeatingGroupId, inputFieldId, tableName)
// Sort a repeating group
//
// Parameters:
// repeatingGroupId - the repeating group id
// inputFieldId     - the input field id to sort 
// tableName        - the table name
//**********************************************************************************	
function sortColumn(repeatingGroupId, inputFieldId, tableName)
{
	// table information
	var table = document.getElementById(tableName);
	var nRows = table.rows.length - 1; // always have one invisible row
	
	// 0 or 1 row, then there is nothing to sort
	if (nRows<=1)
	{
		return;
	}
	
	// sorting in progress?
	if (sortingInProgress)
	{
		return;
	}
	
	// change mouse cursor to wait
	sortingInProgress = true;
	document.body.style.cursor='wait';
	var tdSort = document.getElementById(REPEAT_HDR + repeatingGroupId + GRP_FLD + inputFieldId);
	tdSort.style.cursor='wait';
	
	setTimeout( 
			function() 
			{
				// already sorted by this field?
				var sortObj = document.getElementById(repeatingGroupId + REPGROUP_FLDSORT);
				if (sortObj.value == inputFieldId)
				{
					// reverse the order
					var ascObj = document.getElementById(repeatingGroupId + REPGROUP_ORDERSORT);
					ascObj.value = !(ascObj.value == "true");

					// reverse the ordering
					var elements = new Array(nRows);
					var index = 0;
					for (rowIndex=nRows-1; rowIndex>=0; rowIndex--)
					{
						var rowObj = table.rows[rowIndex];
						var rowObjrowIndex = rtvAttrRowIndex(rowObj);
						if (rowObjrowIndex != null && rowObjrowIndex != '0' && rowObjrowIndex != ROW_INDEX_END)
						{
							var rowStr = rowObjrowIndex.leftPad(LIST_INDEX_LEN,"0");
							var fieldName = inputFieldId + LIST_INDEX_DELIMITER + rowStr;
							elements[index] = GLOBAL_DELIMETER + fieldName;
							index++;
						}
					}
					
					// make sure elements is compact
					if (index>0)
					{
						elements = elements.slice(0,index);
					}
				}

				// sort it
				else
				{
					// set the field order
					sortObj.value = inputFieldId;
					
					// default to ascending
					var ascObj = document.getElementById(repeatingGroupId + REPGROUP_ORDERSORT);
					ascObj.value = "true";
				
					// copy the relevant data into a new array
					var elements = new Array(nRows);
					for (rowIndex=1; rowIndex<=nRows; rowIndex++)
					{
						var rowStr = (rowIndex+'').leftPad(LIST_INDEX_LEN,"0");
						var fieldName = inputFieldId + LIST_INDEX_DELIMITER + rowStr;
						var obj = document.getElementById(fieldName);
						var dbobj = document.getElementById(fieldName + ID_DB);
						
						// no db object, then simply point to the input object
						if (dbobj == null)
						{
							dbobj = obj;
						}
						
						// field name not found, then no more data, the table can have more records that the actual number of relevant 
						// records due to user defined rows
						if (obj==null)
						{
							elements = elements.slice(0,rowIndex-1);
							break;
						}
						
						// get the content
						valueElement = getFullSortValue(obj,dbobj);
						
						// are there continuation fields?
						var nextfield = rtvNextField(obj);
						while (nextfield != null && nextfield != '')
						{
							obj = document.getElementById(nextfield);
							dbobj = document.getElementById(obj.id + ID_DB);
							// no db object, then simply point to the input object
							if (dbobj == null)
							{
								dbobj = obj;
							}
							valueElement += getFullSortValue(obj,dbobj);
							nextfield = rtvNextField(obj);
						}
				
						elements[rowIndex-1] = valueElement + GLOBAL_DELIMETER + fieldName;
					}
				
					// start sorting - merge sort algorithm
					elements = sort(elements);
				}
				
				// refresh the table
				refreshSortedTable(table,elements);
				
				// update row style
				reupdateRepeatingRowStyle(table);
				
				// restore to default
				document.body.style.cursor='';
				tdSort.style.cursor='pointer';
				sortingInProgress = false;
			}
			,10);
}

//**********************************************************************************
// function refreshSortedTable(table,elements)
// Update the repeating table with the sorted elements
//
// Parameters:
// table - the table to be sorted/refreshed
// elements - the sorted elements 
//**********************************************************************************	
function refreshSortedTable(table,elements)
{
	// create pointers to the row
	var pointerRow = new Array(table.rows.length);
	var pointerIndex = new Array(table.rows.length);
	for (rowIndex=0; rowIndex<table.rows.length; rowIndex++)
	{
		pointerRow[rowIndex] = table.rows[rowIndex].cloneNode(true);
		
		// get the original row index of this row
		index = rtvAttrRowIndex(table.rows[rowIndex]);
		if (index != null && index != '0' && index != ROW_INDEX_END)
		{
			pointerIndex[index] = rowIndex;
		}
	}
	
	// clear the table
	var highestFooter = -1;
	for (rowIndex = table.rows.length - 1; rowIndex >= 0; rowIndex--)
	{
		rowObj = table.rows[rowIndex];
		rowObjrowIndex = rtvAttrRowIndex(rowObj);
		
		if (rowObjrowIndex == "0")
		{
			// this is the user defined row relative to the top, retain it
			// end processing as it means previous rows will have the same rowindex
			break;
		}
		else if (rowObjrowIndex == ROW_INDEX_END)
		{
			// this is the user defined row relative to the bottom, retain it
			// keep track of the index of the highest row
			highestFooter = rowIndex;
			table.deleteRow(rowIndex);
		}
		else
		{
			table.deleteRow(rowIndex);
		}
	}

	// retrieve the tbody of the table
	var tbody = table.tBodies[0];
	
	// add back the rows according to the sort order
	for (rowIndex=0; rowIndex<elements.length; rowIndex++)
	{
		// get the row number
		var index = elements[rowIndex].indexOf(GLOBAL_DELIMETER);
		var index = elements[rowIndex].indexOf(LIST_INDEX_DELIMITER,index+GLOBAL_DELIMETER.length);
		var rowStr = elements[rowIndex].substr(index+ LIST_INDEX_DELIMITER.length);
		var rowNum = parseInt(rowStr.removeLeading("0"));

		var rowObj = pointerRow[pointerIndex[rowNum]];
		
		// peek and add if there are any user defined row (above) for this row
		index = pointerIndex[rowNum]-1;
		var rowObj3 = null;
		while (index >= 0)
		{
			rowObj2 = pointerRow[index];
			rowrefindex = rtvAttrRowRefIndex(rowObj2);
			if (rowrefindex != null && rowrefindex == rtvAttrRowIndex(rowObj))
			{
				if (rowObj3 == null)
				{
					tbody.appendChild(rowObj2);
					rowObj3 = rowObj2;
				}
				else
				{
					tbody.insertBefore(rowObj2, rowObj3);
					rowObj3 = rowObj2;
				}
				index--;
			}
			else
			{
				index = -1;
			}
		}

		// add the row
		tbody.appendChild(rowObj);

		// peek and add if there are any user defined row (bottom) for this row
		index = pointerIndex[rowNum]+1;
		while (index >= 0)
		{
			rowObj2 = pointerRow[index];
			rowrefindex = rtvAttrRowRefIndex(rowObj2);
			if (rowrefindex != null && rowrefindex == rtvAttrRowIndex(rowObj))
			{
				tbody.appendChild(rowObj2);
				index++;
			}
			else
			{
				index = -1;
			}
		}
	}
	
	// add the user defined row relative to the bottom
	for (rowIndex=highestFooter; rowIndex<pointerRow.length; rowIndex++)
	{
		tbody.appendChild(pointerRow[rowIndex]);
	}
}


//**********************************************************************************
// function sort(arrElements)
// Sort an array
//
// Parameters:
// arrElements - the array to be sorted
//
// Returns:
// the sorted array
//**********************************************************************************	
function sort(arrElements)
{
	return mergeSortAlgorithm(arrElements,0,arrElements.length-1,0,null,null);
}

//**********************************************************************************
// function mergeSortAlgorithm(arrElements, startIndex, endIndex)
// Sort an array using merge sort algorithm
//
// Parameters:
// arrElements - the array elements
// startIndex  - starting index within the array to sort
// endIndex    - ending index within the array to sort
// median      - dummy parameter to support recursion
// leftSide    - dummy parameter to support recursion
// rightSide   - dummy parameter to support recursion
//
// Returns:
// the sorted array in the specified start and end index
//**********************************************************************************	
function mergeSortAlgorithm(arrElements, startIndex, endIndex, median, leftSide, rightSide)
{
	// zero or one element only, then the list is already sorted
	if (endIndex - startIndex <= 0)
	{
		leftSide = new Array(1);
		leftSide[0] = arrElements[startIndex];
		return leftSide;
	}
	
	// divide the array into 2
	median = Math.floor((endIndex + startIndex) / 2);
	leftSide = mergeSortAlgorithm(arrElements, startIndex, median, 0, null, null);
	rightSide = mergeSortAlgorithm(arrElements, median+1, endIndex, 0, null, null);
	
	// if last item on the left side is greater than the first item on the right side, then perform merging
	if (leftSide[leftSide.length-1] > rightSide[0])
	{
		return mergeSort(leftSide, rightSide);
	}
	else
	{
		return leftSide.concat(rightSide);
	}
}

//**********************************************************************************
// function mergeSort(arrLeftSide, arrRightSide)
// Merge sort routine
//
// Parameters:
// arrLeftSide - left side of the array (as a result of merge sorting algorithm)
// arrRightSide - right side of the array (as a result of merge sorting algorithm)
//
// Returns:
// the consolidated sorted array
//**********************************************************************************	
function mergeSort(arrLeftSide, arrRightSide)
{
	var leftSideLast = arrLeftSide.length - 1;
	var rightSideLast = arrRightSide.length - 1;
	var leftSideIndex = 0;
	var rightSideIndex = 0;
	
	var newArray = new Array(arrLeftSide.length + arrRightSide.length);
	var newArrayIndex = 0;
	
	while (leftSideIndex <= leftSideLast || rightSideIndex <= rightSideLast)
	{
		// no more left side, then add the right side 
		if (leftSideIndex > leftSideLast)
		{
			newArray[newArrayIndex] = arrRightSide[rightSideIndex];
			rightSideIndex++;
		}
		
		// no more right side, then add the left side
		else if (rightSideIndex > rightSideLast)
		{
			newArray[newArrayIndex] = arrLeftSide[leftSideIndex];
			leftSideIndex++;
		}
		
		// left side is smaller than right side?
		else if (arrLeftSide[leftSideIndex] < arrRightSide[rightSideIndex])
		{
			newArray[newArrayIndex] = arrLeftSide[leftSideIndex];
			leftSideIndex++;
		}
		
		// right side is smaller than left side
		else
		{
			newArray[newArrayIndex] = arrRightSide[rightSideIndex];
			rightSideIndex++;
		}
		
		// next slot in the new array
		newArrayIndex++;
	}
	
	return newArray;
}


//**********************************************************************************
// function breakByRepeatingData(repeatingGroupId, tableDataId, tableFooterId)
// Show/hides rows depending on the selection
//
// Parameters:
// repeatingGroupId - the repeating group id
// tableDataId      - the table id for the data
// tableFooterId    - the table id for the footer
//**********************************************************************************	
function breakByRepeatingData(repeatingGroupId, tableDataId, tableFooterId)
{
	selectElement = document.getElementById(repeatingGroupId + REPGROUP_SELBREAKBY);
	if (selectElement == null)
	{
		return;
	}

	// in progress?
	if (sortingInProgress)
	{
		return;
	}
	
	// set in progress 
	sortingInProgress = true;
	
	selectedIndex = selectElement.selectedIndex;
	if (selectedIndex>=0)
	{
		// break by this grouping
		breakby = selectElement.options[selectedIndex].value;
		
		if (selectedIndex == 0)
		{
			breakby = "";
		}

		// update the corresponding input element
		obj = document.getElementById(repeatingGroupId + REPGROUP_INPBREAKBY);
		obj.value = breakby;
		
		// loop the table and check for group by
		table = document.getElementById(tableDataId);
		for (i=0; i<table.rows.length; i++)
		{
			j = 0;
			var rowBreakby = rtvAttrBreakby(table.rows[i]);
			if (breakby != '' && rowBreakby != '' && rowBreakby != null)
			{
				j = (rowBreakby + GLOBAL_DELIMETER).indexOf(breakby + GLOBAL_DELIMETER);
			}
			
			// footer - then display only the relevant footer
			var rowObjrowIndex = rtvAttrRowIndex(table.rows[i]);
			if (rowObjrowIndex == ROW_INDEX_END && rowBreakby != null && rowBreakby != '' && breakby != rowBreakby)
			{
				j = -1;
			}
			
			if (j>=0)
			{
				table.rows[i].style.display = "";
			}
			else
			{
				table.rows[i].style.display = "none";
			}
		}
		
		// loop the table and check for group by
		table = document.getElementById(tableFooterId);
		if (table != null)
		{
			for (i=0; i<table.rows.length; i++)
			{
				j = 0;
				var rowBreakby = rtvAttrBreakby(table.rows[i]);
				if (rowBreakby != '' && rowBreakby != null)
				{
					if (rowBreakby != breakby)
					{
						j = -1;
					}
				}
				if (j>=0)
				{
					table.rows[i].style.display = "";
				}
				else
				{
					table.rows[i].style.display = "none";
				}
			}
		}
		
		// not during page loading, then readjust size of the repeating group
		if (!pageLoading)
		{
			setRepeatingGroupSize(repeatingGroupId, true);
		}
	}
	
	// update the row style 
	if (!pageLoading)
	{
		reupdateRepeatingRowStyle(document.getElementById(tableDataId));
	}
	
	// not in progress anymore 
	sortingInProgress = false;
}

//**********************************************************************************
// function setTableSizesForRepeatingGroup(tableHdr, tableList, tableFtr)
// Make sure all tables are of the same width
//
// Parameters:
// repeatingGroupId - the repeating group id
// tableHdr  - header table
// tableList - list table
// tableFtr  - footer table
//**********************************************************************************	
function setTableSizesForRepeatingGroup(repeatingGroupId, tableHdr, tableList, tableFtr)
{
	// ensure all tables are the same size
	adj = tableHdr.offsetWidth;
	if (tableList.offsetWidth > adj)
	{
		adj = tableList.offsetWidth;
	}
	
	if (tableFtr.offsetWidth > adj)
	{
		adj = tableFtr.offsetWidth;
	}
	tableHdr.style.width = adj + 'px';
	tableList.style.width = adj + 'px';
	tableFtr.style.width = adj + 'px';
}

//**********************************************************************************
// function setTableListHeight(divList, tableList)
// Set the height of the list to reflect the number of requested row to be displayed
//
// Parameters:
// repeatingGroupId - the repeating group id
// divList   - the div containing the list table
// tableList - list table 
//
// Return:
// true if more records are available that can fit the current display
//**********************************************************************************	
function setTableListHeightForRepeatingGroup(repeatingGroupId, divList, tableList)
{
	// adjust the height of the div list to reflect the requested number of rows to be displayed
	v_scrollbar = false;
	try
	{
		nRows = parseInt(getAttribute(tableList,'maxrowdisplayed'),10);
	}
	catch (e)
	{
		return v_scrollbar;
	}
		
	// more data than the specified rows
	if (nRows>0 && nRows < tableList.rows.length)
	{
		height = 0;
		ctr=0;
		for (j=0; j < tableList.rows.length-1; j++)
		{
			// calculate the rows that will be displayed
			if (tableList.rows[j].style.display == '')
			{
				if (ctr >= nRows)
				{
					ctr++;
					break;
				}
				
				height += tableList.rows[j].offsetHeight;
				ctr++;
			}
		}

		// scroll bar to appear?
		if (ctr > nRows)
		{
			divList.style.height=(height) + 'px';
			v_scrollbar = true;
			divList.style.overflowY="";
		}
		else
		{
			divList.style.height = '';
			divList.style.overflowY="hidden";
		}
	}
	
	// less data than the specified rows
	else
	{
		divList.style.height = '';
		divList.style.overflowY="hidden";
	}

	// return whether vertical scroll bar needs to be displayed (which means there are more data that can fit in the current display)
	return v_scrollbar;
}


//**********************************************************************************
// function setRepeatingGroupSize(repeatingGroupId, forceResize)
// Set the repeating group size
//
// Parameters:
// repeatingGroupId - the repeating group id
// forceResize - force resizing
//**********************************************************************************	
function setRepeatingGroupSize(repeatingGroupId, forceResize)
{
	// ensure all columns for related repeating groups are the same (header, data, footer)
	var tableHdr = document.getElementById(LISTTABLEHDR + repeatingGroupId);
	var tableList = document.getElementById(LISTTABLELST + repeatingGroupId);
	var tableFtr = document.getElementById(LISTTABLEFTR + repeatingGroupId);
	
	// do resizing only if header is found 
	if (tableHdr != null)
	{
		var row = tableHdr.rows[0];
		var td = row.cells[0];
		while (td != null)
		{
			resizeRepeatingGroupColumn(td,forceResize, tableHdr, tableList, tableFtr);
			td = td.nextSibling;
		}
	}
	
	// adjust the repeating group width and height
	setRepeatingGroupDiv(repeatingGroupId);
}


//**********************************************************************************
// function setRepeatingGroupDiv(repeatingGroupId)
// Set the height and width of a repeating group
//
// Parameters:
// repeatingGroupId - the repeating group id
//**********************************************************************************	
function setRepeatingGroupDiv(repeatingGroupId)
{
	var divHdr = document.getElementById(LISTGROUPHDR + repeatingGroupId);
	var tableHdr = document.getElementById(LISTTABLEHDR + repeatingGroupId);
	var divList = document.getElementById(LISTGROUPLST + repeatingGroupId);
	var tableList = document.getElementById(LISTTABLELST + repeatingGroupId);
	var divFtr = document.getElementById(LISTGROUPFTR + repeatingGroupId);
	var tableFtr = document.getElementById(LISTTABLEFTR + repeatingGroupId);
	var isIE9 = navigator.userAgent.indexOf( 'MSIE 9.0') > -1;
	
	// always suppress the scroll bar
	if (divFtr != null)
	{
		divList.style.overflowX="hidden";
		divFtr.style.overflowY="hidden";
	}

	// make sure all table's width are the same
	if (divHdr != null)
	{
		setTableSizesForRepeatingGroup(repeatingGroupId, tableHdr, tableList, tableFtr);
	}
	
	// assume no scrollbar will appear
	var v_scrollbar = setTableListHeightForRepeatingGroup(repeatingGroupId, divList, tableList);

	// is the div list width adjustable?
	//if (divList.className.indexOf(NON_RESIZE)==-1)
	{
		var adj = isIE9 ? 37: 20;
		// if the list width will exceed the window width, then force the width to the maximum width only
		if (tableList.offsetWidth > window_size-adj)
		{
			// set the size of the width for the list
			divList.style.width=(window_size-adj) + 'px';
			
			// set the size of the width for the header, taking into consideration whether vertical scroll bar will appear or not
			if (divHdr != null)
			{
				if (v_scrollbar)
				{
					divHdr.style.width=(window_size-adj-vscrollbar_width) + 'px';
					divFtr.style.width=(window_size-adj-vscrollbar_width) + 'px';
				}
				else
				{
					divHdr.style.width=(window_size-adj) + 'px';
					divFtr.style.width=(window_size-adj) + 'px';
				}
	
				// For IE7 - adjust the height
				if (isBrowserIE7())
				{
					divFtr.style.height=tableFtr.scrollHeight + hscrollbar_height +  'px';
				}
			}
		}
		
		// if the list width will not exceed and vertical scroll bar will appear, then adjust to the width + vertical scrollbar
		else if (v_scrollbar)
		{
			divList.style.width=(tableList.offsetWidth+16) + 'px';
			if (divHdr != null)
			{
				divHdr.style.width=(tableList.offsetWidth+16) + 'px';
				divFtr.style.width=(tableList.offsetWidth+16) + 'px';
			}
		}
		
		// otherwise, just the size of the list
		else
		{
			divList.style.width=(tableList.offsetWidth) + 'px';
			if (divHdr != null)
			{
				divHdr.style.width=(tableList.offsetWidth) + 'px';
				divFtr.style.width=(tableList.offsetWidth) + 'px';
			}
		}
	}
}


//**********************************************************************************
// function reupdateRepeatingRowStyle(table)
// Update the row style based on the current visible row
//
// Parameters:
// table - the table to be updated
//**********************************************************************************	
function reupdateRepeatingRowStyle(table)
{
	var rowStyle1 = getAttribute(table,'rowstyle1');
	var rowStyle2 = getAttribute(table,'rowstyle2');

	// not specified, then set to blank
	if (rowStyle1 == null)
	{
		rowStyle1 = '';
	}

	if (rowStyle2 == null)
	{
		rowStyle2 = '';
	}
	
	if (rowStyle1 == '' && rowStyle2 == '')
	{
		return;
	}

	// current row style
	var currentRowStyle = rowStyle2;

	// loop all the rows
	for (rowIndex=0; rowIndex<table.rows.length; rowIndex++)
	{
		var rowObj = table.rows[rowIndex];
		
		// update only visible row and if the style has been automatically set by the system
		if (rowObj.style.display != "none" && rtvFixRowStyle(rowObj) != 'Y')
		{
			// select row style
			if (currentRowStyle == rowStyle1)
			{
				currentRowStyle = rowStyle2;
			}
			else
			{
				currentRowStyle = rowStyle1; 
			}
			
			// replace current row style with new style
			className = replaceAll(rowObj.className, rowStyle1, "");
			className = replaceAll(className, rowStyle2, "");
			className += " " + currentRowStyle;
			rowObj.className = className;
			
			// also change all the row style of the input elements
			for (i=0; i < rowObj.children.length; i++)
			{
				colObj = rowObj.children[i];
				for (j=0; j< colObj.children.length; j++)
				{
					obj = colObj.children[j];
					if (rtvFixRowStyle(colObj) != 'Y' && obj.readOnly && obj.tagName == "INPUT" && obj.type == "text")
					{
						className = replaceAll(obj.className, rowStyle1, "");
						className = replaceAll(className, rowStyle2, "");
						className += " " + currentRowStyle;
						obj.className = className;
					}
				}
			}
		}
	}
}

//**********************************************************************************
// function getFullSortValue(inputObj, dbObj)
// Get the field value for the sorting order
//
// Parameters:
// inputObj - the input object
// dbObj - the db object
//**********************************************************************************	
function getFullSortValue(inputObj, dbObj)
{
	var fieldtype = rtvFieldType(inputObj);
	if (fieldtype=='P' || fieldtype=='S')
	{
		// are there negative?
		var str = dbObj.value;
		var negative = false;
		if (str.indexOf("-") >= 0)
		{
			str = replaceAll(str,"-",""); 
			negative = true;
		}
		
		str = str.leftPad(inputObj.maxLength,"0");

		// now, add some control character at the front to make negative numbers appear first
		if (negative)
		{
			str = "0" + negateNum(str);
		}
		else
		{
			str = "1" + str;
		}
		
		return str;
	}
	else
	{
		return dbObj.value.pad(inputObj.maxLength);
	}
}

//**********************************************************************************
// function showRepeatingGroupPageUpButton(repeatingGroupId, enabled)
// Add the page up for the repeating group
//
// Parameters:
// repeatingGroupId - the repeating group id
// enabled		    - true if button is enabled
//**********************************************************************************	
function showRepeatingGroupPageUpButton(repeatingGroupId, enabled)
{
	var hoverText = getWindowLanguageLabel('GBL000065');
	var labelText = repeatingGroupId + BUT_GPAGEUP;
	var imagePath = '/' + getWebAppName() + '/equation/images/EqPageUp.gif';
	var onClickAction = 'repeatingGroupPaging(\'' + repeatingGroupId + '\'' + ',\'Y\');';
	
	// add the prompt button
	if (enabled)
	{
		addWidgetButton(hoverText,labelText,onClickAction,imagePath);
	}
	else
	{
		document.write(replaceAll(getImageHTML(hoverText, labelText, '/' + getWebAppName() +  '/equation/images/EqPageUp_off.gif'),ONCLIENTSIDE_SEP,''));
	}
}

//**********************************************************************************
// function showRepeatingGroupPageDownButton(repeatingGroupId, enabled)
// Add the page down for the repeating group
//
// Parameters:
// repeatingGroupId - the repeating group id
// enabled		    - true if button is enabled
//**********************************************************************************	
function showRepeatingGroupPageDownButton(repeatingGroupId, enabled)
{
	var hoverText = getWindowLanguageLabel('GBL000066');
	var labelText = repeatingGroupId + BUT_GPAGEDN;
	var imagePath = '/' + getWebAppName() + '/equation/images/EqPageDn.gif';
	var onClickAction = 'repeatingGroupPaging(\'' + repeatingGroupId + '\'' + ',\'N\');';

	// add the prompt button
	if (enabled)
	{
		addWidgetButton(hoverText,labelText,onClickAction,imagePath);
	}
	else
	{
		document.write(replaceAll(getImageHTML(hoverText, labelText, '/' + getWebAppName() + '/equation/images/EqPageDn_off.gif'),ONCLIENTSIDE_SEP,''));
	}
}

//**********************************************************************************
// function showRepeatingGroupProgressButton()
// Add the progress bar for the repeating group
//
// Parameters:
// None
//**********************************************************************************	
function showRepeatingGroupProgressButton()
{
	document.write(replaceAll(getImageHTML('spinnerImage', 'spinnerImage', '/' + getWebAppName() + '/equation/images/EqSpin.gif'),ONCLIENTSIDE_SEP,''));
}


//**********************************************************************************
// function processRepeatingPageDetail(repeatingGroupId, returnFieldString)
// Display the return list of details back to the repeating table
//
// Parameters:
// repeatingGroupId  - the repeating group id
// returnFieldString - all the details
//**********************************************************************************	
function processRepeatingPageDetail(repeatingGroupId, returnFieldString)
{
	var firstRecord = returnFieldString[0];
	var lastRecord = returnFieldString[1];
	var html = returnFieldString[2];
	var invisibleHTML = returnFieldString[3];
	
	// invisible div
	var invisibleDiv = document.getElementById(repeatingGroupId + REPGROUP_INVISIBLE);
	
	// since IE disallow changing of the table or a rows INNERHTML, then we need to actually construct a table
	// element that can be accessed via DOM, so temporary put this in the invisible divvy
	invisibleDiv.innerHTML = html;
	var newTableData = invisibleDiv.childNodes[0];
	
	// repeating data table
	var repeatingTable = document.getElementById(LISTTABLELST + repeatingGroupId);
	
	// retrieve reference to the last row to be retained
	var bottomRow = null;
	for (i=repeatingTable.rows.length-1; i>=0; i--)
	{
		var row = repeatingTable.rows[i];
		var rowObjrowindex = rtvAttrRowIndex(row);
		if (rowObjrowindex == ROW_INDEX_END)
		{
			bottomRow = row;
		}
		else if (rowObjrowindex != 0)
		{
			repeatingTable.deleteRow(i);
		}
		else
		{
			break;
		}
	}

	// loop through the new table and add to the repeating data
	defaultButtonProc = false;
	n = newTableData.rows.length;
	for (i=0; i<n; i++)
	{
		ix = i;
		// run all scripts!
		var rowObj = newTableData.rows[0];
		var cellObj = rowObj.cells[0];
		while (cellObj != null)
		{
			var obj = cellObj.firstChild;
			while (obj != null)
			{
				if (obj.tagName == 'SCRIPT')
				{
					var suffixHTML = '';
					if (obj.innerHTML.indexOf(');') >= 0)
					{
						listScript = obj.innerHTML.trim().split(');');
						suffixHTML = ');';
					}
					else
					{
						listScript = new Array();
						listScript[0] = obj.innerHTML;
					}
					
					scriptHTMLs = '';
					for (j=0; j<listScript.length; j++)
					{
						if (listScript[j].trim() != '')
						{
							try
							{
								scriptHTML = eval(listScript[j] + suffixHTML);
								if (scriptHTML != null)
								{
									scriptHTMLs += scriptHTML;
								}
							}
							catch (e)
							{
								// alert('script processing error processRepeatingPageDetail()');
							}
						}
					}
					
					tempobj = obj.nextSibling;
					obj.outerHTML = scriptHTMLs;
					obj = tempobj;
				}
				else
				{
					obj = obj.nextSibling;
				}
			}
			
			cellObj = cellObj.nextSibling;
		}
		
		i = ix;
		if (bottomRow==null)
		{
			repeatingTable.tBodies[0].appendChild(rowObj);
		}
		else
		{
			repeatingTable.tBodies[0].insertBefore(rowObj, bottomRow);
		}
	}
	defaultButtonProc = true;

	// replace the invisible divvy with the real content
	invisibleDiv.innerHTML = invisibleHTML;
	
	// replace the up button
	var hoverText = getWindowLanguageLabel('GBL000065');
	var labelText = repeatingGroupId + BUT_GPAGEUP;
	if (firstRecord == 'Y')
	{
		var imagePath = '/' + getWebAppName() + '/equation/images/EqPageUp_off.gif';
		document.getElementById(repeatingGroupId + REPGROUP_BUTUP).innerHTML = 
			replaceAll(getImageHTML(hoverText, labelText, imagePath),ONCLIENTSIDE_SEP,'');
	}
	else
	{
		var imagePath = '/' + getWebAppName() + '/equation/images/EqPageUp.gif';
		var onClickAction = 'repeatingGroupPaging(\'' + repeatingGroupId + '\'' + ',\'Y\');';
		document.getElementById(repeatingGroupId + REPGROUP_BUTUP).innerHTML = 
			replaceAll(getImageButtonHTML(hoverText, labelText, onClickAction, imagePath, "widgetButton"),ONCLIENTSIDE_SEP,'');
	}
		
	// replace the down button
	var hoverText = getWindowLanguageLabel('GBL000066');
	var labelText = repeatingGroupId + BUT_GPAGEDN;
	if (lastRecord == 'Y')
	{
		var imagePath = '/' + getWebAppName() + '/equation/images/EqPageDn_off.gif';
		document.getElementById(repeatingGroupId + REPGROUP_BUTDOWN).innerHTML = 
			replaceAll(getImageHTML(hoverText, labelText, imagePath),ONCLIENTSIDE_SEP,'');
	}
	else
	{
		var imagePath = '/' + getWebAppName() + '/equation/images/EqPageDn.gif';
		var onClickAction = 'repeatingGroupPaging(\'' + repeatingGroupId + '\'' + ',\'N\');';
		document.getElementById(repeatingGroupId + REPGROUP_BUTDOWN).innerHTML = 
			replaceAll(getImageButtonHTML(hoverText, labelText, onClickAction, imagePath, "widgetButton"),ONCLIENTSIDE_SEP,'');
	}

	// update the style
	reupdateRepeatingRowStyle(repeatingTable);
	
	// update the size
	setRepeatingGroupSize(repeatingGroupId, true);
}


//**********************************************************************************
// function repeatingGroupPaging(repeatingGroupId, pageUp)
// Perform paging up or down the repeating group
//
// Parameters:
// repeatingGroupId - the repeating group id
// pageUp			- page up (true)
//**********************************************************************************	
function repeatingGroupPaging(repeatingGroupId, pageUp)
{
	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();
	
	// Get the name
	var sessionIdentifier = getSessionId();
	var name = document.getElementById(OBJ_NAME).value;

	// Define the get and response
	var qn_op = new WS.QName('repeatingGroupPageAction',nsuri);
	var qn_op_resp = new WS.QName('repeatingGroupPageActionResponse',nsuri);

	// Set the parameters required by the service
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'name',value:name},
								{name:'repeatingGroupId',value:repeatingGroupId},
								{name:'pageUp',value:pageUp}
							];

	// Turn on the progress bar
	document.getElementById(repeatingGroupId + REPGROUP_BUTPROG).className = 'repeatingGroupButtons';

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
								returnFieldString = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value().split(GLOBAL_EQUALDELIMETER);
								processRepeatingPageDetail(repeatingGroupId, returnFieldString);
							}
							catch (e)
							{
							}
							
							// Turn off the progress bar
							document.getElementById(repeatingGroupId + REPGROUP_BUTPROG).className = 'wf_NDP';
						}
					);
}


//**********************************************************************************
// function sortColumnOnServer(repeatingGroupId, inputFieldId, tableName)
// Sort a repeating group on server
//
// Parameters:
// repeatingGroupId - the repeating group id
// inputFieldId     - the input field id to sort 
// tableName        - the table name
//**********************************************************************************	
function sortColumnOnServer(repeatingGroupId, inputFieldId, tableName)
{
	// construct the context fields
	var table = document.getElementById(tableName);
	var contextFields = '';
	for (i=0; i<table.rows.length; i++)
	{
		var rowObj = table.rows[i];
		var rowObjrowindex = rtvAttrRowIndex(rowObj);
		if (rowObjrowindex != null && 
				rowObjrowindex != 0 && 
				rowObjrowindex != ROW_INDEX_END)
		{
			var rowStr = rowObjrowindex.leftPad(LIST_INDEX_LEN,"0");
			var fieldName = inputFieldId + LIST_INDEX_DELIMITER + rowStr;
			var obj = document.getElementById(fieldName);
			if (obj == null)
			{
				break;
			}

			contextFields = inputFieldId;
			var nextfield = rtvNextField(obj);
			while (nextfield != null && nextfield != '')
			{
				var  index = nextfield.indexOf(LIST_INDEX_DELIMITER);
				contextFields += CONTEXT_DELIMETER + nextfield.substring(0,index);
				obj = document.getElementById(nextfield);
				nextfield = rtvNextField(obj);
			}
			
			break;
		}
	}
	
	// is there a context fields?
	if (contextFields == '')
	{
		return;
	}

	// sorting in progress?
	if (sortingInProgress)
	{
		return;
	}
	
	// sorting
	sortingInProgress = true;
	
	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();
	
	// Get the name
	var sessionIdentifier = getSessionId();
	var name = document.getElementById(OBJ_NAME).value;

	// Define the get and response
	var qn_op = new WS.QName('repeatingGroupSortAction',nsuri);
	var qn_op_resp = new WS.QName('repeatingGroupSortActionResponse',nsuri);

	// Set the parameters required by the service
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'name',value:name},
								{name:'repeatingGroupId',value:repeatingGroupId},
								{name:'contextFields',value:contextFields}
							];

	// Turn on the progress bar
	document.getElementById(repeatingGroupId + REPGROUP_BUTPROG).className = 'repeatingGroupButtons';

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
								returnFieldString = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value().split(GLOBAL_EQUALDELIMETER);
								processRepeatingPageDetail(repeatingGroupId, returnFieldString);
							}
							catch (e)
							{
							}
							
							// Turn off the progress bar
							document.getElementById(repeatingGroupId + REPGROUP_BUTPROG).className = 'wf_NDP';
							sortingInProgress = false;
						}
					);
}

//**********************************************************************************
// function breakByOnServerRepeatingData(repeatingGroupId, tableDataId, tableFooterId)
// Perform breakby processing on server side
//
// Parameters:
// repeatingGroupId - the repeating group id
// tableDataId      - the table id for the data
// tableFooterId    - the table id for the footer
//**********************************************************************************	
function breakByOnServerRepeatingData(repeatingGroupId, tableDataId, tableFooterId)
{
	var breakby = '';
	var selectElement = document.getElementById(repeatingGroupId + REPGROUP_SELBREAKBY);
	if (selectElement == null)
	{
		return;
	}
	selectedIndex = selectElement.selectedIndex;
	if (selectedIndex>=0)
	{
		breakby = selectElement.options[selectedIndex].value;
		if (selectedIndex == 0)
		{
			breakby = "";
		}
	}
	else
	{
		return;
	}
	
	// sorting in progress?
	if (sortingInProgress)
	{
		return;
	}
	
	// in progress
	sortingInProgress = true;
	
	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();
	
	// Get the name
	var sessionIdentifier = getSessionId();
	var name = document.getElementById(OBJ_NAME).value;

	// Define the get and response
	var qn_op = new WS.QName('breakByRepeatingDataAction',nsuri);
	var qn_op_resp = new WS.QName('breakByRepeatingDataActionResponse',nsuri);

	// Set the parameters required by the service
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'name',value:name},
								{name:'repeatingGroupId',value:repeatingGroupId},
								{name:'breakby',value:breakby}
							];

	// Turn on the progress bar
	document.getElementById(repeatingGroupId + REPGROUP_BUTPROG).className = 'repeatingGroupButtons';

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
								returnFieldString = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value().split(GLOBAL_EQUALDELIMETER);
								processRepeatingPageDetail(repeatingGroupId, returnFieldString);
								sortingInProgress = false;
								breakByRepeatingData(repeatingGroupId, LISTTABLELST + repeatingGroupId, LISTTABLEFTR + repeatingGroupId);
							}
							catch (e)
							{
							}
							
							
							// Turn off the progress bar
							document.getElementById(repeatingGroupId + REPGROUP_BUTPROG).className = 'wf_NDP';
							sortingInProgress = false;
						}
					);
}

//**********************************************************************************
// function showGlobalLinkedFunctionButton(repeatingGroupId,selectionOption)
// Display the global linked function button
//
// Parameters:
// repeatingGroupId - the repeating group id
// selectionOption  - the selection option
//**********************************************************************************	
function showGlobalLinkedFunctionButton(repeatingGroupId, selectionOption, optionId, optionDesc)
{
	cmdKeyAct = "setRepeatingRowGlobalLinkAction('" + repeatingGroupId + "','" + selectionOption + "')";
	if (RTL)
	{
		var widgetButtonHTML = replaceAll(getTextButtonHTML(optionDesc, optionId + " = " + selectionOption, cmdKeyAct,''),ONCLIENTSIDE_SEP,'');
	}
	else
	{
		var widgetButtonHTML = replaceAll(getTextButtonHTML(optionDesc, selectionOption + " = " + optionId, cmdKeyAct,''),ONCLIENTSIDE_SEP,'');
	}
	document.write(widgetButtonHTML);
}


//**********************************************************************************
// function setRepeatingRowDoubleClickAction(repeatingGroupId, rowIndex, selectionOption)
// Handle events when double clicking a repeating row
//
// Parameters:
// repeatingGroupId - the repeating group id
// rowIndex         - the row index
// selectionOption  - the selection option to trigger
//**********************************************************************************	
function setRepeatingRowDoubleClickAction(repeatingGroupId, rowIndex, selectionOption)
{
	// page already submitted, then ignore any more request
	if(submitted)
	{
		return false;
	}
	
	// modal window open?
	if (isAnyModalWindowOpen())
	{
		errorAlert(20,getLanguageLabel('GBL900046'));
		return false;
	}

	// drill down key pressed
	var obj = document.getElementById(OBJ_FKEY);
	obj.value = KEY_DRILLDOWN2;
	
	// set the repeating group to trigger
	obj = document.getElementById(OBJ_FLDVAL);
	obj.value = repeatingGroupId + GLOBAL_DELIMETER + selectionOption + GLOBAL_DELIMETER + rowIndex;  

	// submit page
	submitForm();
	return true;
}


//**********************************************************************************
// function setRepeatingRowGlobalLinkAction(repeatingGroupId, selectionOption)
// Handle events for global linked function
//
// Parameters:
// repeatingGroupId - the repeating group id
// selectionOption  - the selection option to trigger
//**********************************************************************************	
function setRepeatingRowGlobalLinkAction(repeatingGroupId, selectionOption)
{
	// page already submitted, then ignore any more request
	if(submitted)
	{
		return false;
	}
	
	// modal window open?
	if (isAnyModalWindowOpen())
	{
		errorAlert(20,getLanguageLabel('GBL900046'));
		return false;
	}

	// drill down key pressed
	var obj = document.getElementById(OBJ_FKEY);
	obj.value = KEY_DRILLDOWN3;
	
	// set the repeating group to trigger
	obj = document.getElementById(OBJ_FLDVAL);
	obj.value = repeatingGroupId + GLOBAL_DELIMETER + selectionOption;  

	// submit page
	submitForm();
	return true;
}


//**********************************************************************************
// function rtvAttrRowIndex(obj)
// Retrieve the rowindex attribute of an object
//
// Parameters:
// obj - the Row object
//**********************************************************************************	
function rtvAttrRowIndex(obj)
{
	var atr = getAttribute(obj,'rowindex');
	if (atr == 'null')
	{
		atr = obj.rowindex;
	}
	return atr;
}

//**********************************************************************************
// function rtvAttrRowRefIndex(obj)
// Retrieve the rowrefindex attribute of an object
//
// Parameters:
// obj - the Row object
//**********************************************************************************	
function rtvAttrRowRefIndex(obj)
{
	var atr = getAttribute(obj,'rowrefindex');
	if (atr == 'null')
	{
		atr = obj.rowrefindex;
	}
	return atr;
}

//**********************************************************************************
// function rtvAttrBreakby(obj)
// Retrieve the breakby attribute of an object
//
// Parameters:
// obj - the Row object
//**********************************************************************************	
function rtvAttrBreakby(obj)
{
	var atr = getAttribute(obj,'breakby');
	if (atr == 'null' || atr == "undefined")
	{
		atr = obj.breakby;
	}
	return atr;
}

//**********************************************************************************
// function rtvFixRowStyle(obj)
// Retrieve the fixrowstyle attribute of an object
//
// Parameters:
// obj - the Row object
//**********************************************************************************	
function rtvFixRowStyle(obj)
{
	var atr = getAttribute(obj,'fixrowstyle');
	if (atr == 'null')
	{
		atr = obj.fixrowstyle;
	}
	return atr;
}

//**********************************************************************************
// function rtvNextField(obj)
// Retrieve the nextfield attribute of an object
//
// Parameters:
// obj - the Row object
//**********************************************************************************	
function rtvNextField(obj)
{
	var atr = getAttribute(obj,'nextfield');
	if (atr == 'null')
	{
		atr = obj.nextfield;
	}
	return atr;
}

//**********************************************************************************
// function rtvFieldType(obj)
// Retrieve the fieldtype attribute of an object
//
// Parameters:
// obj - the Row object
//**********************************************************************************	
function rtvFieldType(obj)
{
	var atr = getAttribute(obj,'fieldtype');
	if (atr == 'null')
	{
		atr = obj.fieldtype;
	}
	return atr;
}