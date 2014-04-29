
// **********************************************************************************
// function setDisplayGroup(groupId, levelId)
// Display the specified group
//
// Parameters:
// tableId - the table id containing all the details
// groupId - groupId
// levelId  - the level id
// **********************************************************************************	
function setDisplayGroup(tableId, groupId, levelId)
{
	var obj = document.getElementById(tableId);
	displayGroup(obj, groupId, levelId);
}

// **********************************************************************************
// function getNextLevel(groupId, levelId, objList)
// Return the next level of the group
//
// Parameters:
// tableObj - the table object containing all the details
// groupId  - the group id
// levelId  - the level id
// **********************************************************************************	
function getNextLevel(tableObj, groupId, levelId)
{
	var newLevel = -1;
	var smallestLevel = Number.MAX_VALUE;

	// retrieve all the rows of the table and determine the next level (basically the lowest level 
	// but greater than the current level
	for (i=0; i<tableObj.rows.length; i++)
	{
		var rowObj = tableObj.rows[i];
		if (getAttribute(rowObj,'displaygroupid')==groupId)
		{
			var level = parseInt(getAttribute(rowObj,'displaygroupsublevel'));
			if (level > levelId)
			{
				if (level < newLevel || newLevel == -1)
				{
					newLevel = level; 
				}
			}
			
			// get the smallest level
			if (level < smallestLevel)
			{
				smallestLevel = level;
			}
		}
	}
	
	// new level has not been set, then toggle back to the smallest level
	if (newLevel == -1)
	{
		newLevel = smallestLevel;
	}

	// next level	
	return newLevel;
}

// **********************************************************************************
// function displayGroup(groupId, levelId, objList)
// Display the group's level
//
// Parameters:
// tableObj - the table object containing all the details
// groupId  - the group name
// levelId  - the level id
// **********************************************************************************	
function displayGroup(tableObj, groupId, levelId)
{
	// loop again to set to visible / invisible
	var firstTime = true;
	for (i=0; i<tableObj.rows.length; i++)
	{
		var rowObj = tableObj.rows[i];
		if (getAttribute(rowObj,'displaygroupid')==groupId)
		{
			var level = parseInt(getAttribute(rowObj,'displaygroupsublevel'));
			var x = i;
			
			// retrieve the object associated with this row
			var fieldObj = null;
			if (level != 0)
			{
				fieldObj = document.getElementById(rowObj.id.substr(0, rowObj.id.indexOf(ROW_SUFFIX)));
			}

			if (level == 0 || (fieldObj != null && isHiddenField(fieldObj)) )
			{
			}
			else if (level == levelId)
			{
				visibleObjInternal(rowObj,true);
				if (firstTime)
				{
					obj = document.getElementById(rowObj.id.substr(0,rowObj.id.indexOf(ROW_SUFFIX)));
					try
					{
						obj.focus();
						firstTime = false;
					}
					catch (e)
					{
					}
				}
			}
			else
			{
				visibleObjInternal(rowObj,false);
			}
			i = x;
		}
	}
	
	// update the group
	var group = document.getElementById(OBJ_DSPGRPID + groupId);
	group.value = levelId;
	highlightTab(groupId, levelId);
	
	// force resizing
	setTableSize(true);
}

// **********************************************************************************
// function highlightTab(groupId, levelId)
// Highlight the appropriate tab
//
// Parameters:
// groupId  - the group name
// levelId  - the level id
// **********************************************************************************	
function highlightTab(groupId, levelId)
{
	// change to selected and unselect other cell
	var objTdList = document.getElementsByTagName('td');
	for (i=0; i<objTdList.length; i++)
	{
		if (objTdList[i].id.indexOf(GROUPTABS_CELL)==0)
		{
			if (getAttribute(objTdList[i],'groupid') == groupId)
			{
				if (getAttribute(objTdList[i],'levelid') == levelId)
				{
					removeClassName(objTdList[i], 'groupTabText');
					addClassName(objTdList[i], 'groupTabTextSelected');
				}
				else
				{
					removeClassName(objTdList[i], 'groupTabTextSelected');
					addClassName(objTdList[i], 'groupTabText');
				}
			}
		}
	}
}


// **********************************************************************************
// function triggerFieldToggle(fieldObj)
// Invoke the prompt of the specified field
//
// Parameters:
// fieldObj - the field to invoke the prompt
// **********************************************************************************	
function triggerToggleField(fieldObj)
{
	// not set
	if (fieldObj == null)
	{
		errorAlert(0,getLanguageLabel('GBL900050'));
		return;
	}
	
	// retrieve input field
	fieldObj = rtvInputFieldFromDisplayField(fieldObj);
	
	// retrieve the display group it belongs to via checking the parents
	obj = fieldObj;
	while (obj != null)
	{
		var fieldGroup = getAttribute(obj,'displaygroupid'); 
		var fieldGroupLevel = getAttribute(obj,'displaygroupsublevel');
		if (fieldGroup==null || fieldGroup=='' || fieldGroupLevel==null)
		{
			obj = obj.parentNode;
		}
		else
		{
			// retrieve the level of the field
			var fieldGroupLevel = parseInt(fieldGroupLevel);
			var newLevel = getNextLevel(obj.parentNode.parentNode, fieldGroup, fieldGroupLevel);
			
			// level has not change (maybe there is only one group, then no need to toggle)
			if (newLevel == fieldGroupLevel)
			{
				return;
			}
			
			// display the selected group
			displayGroup(obj.parentNode.parentNode, fieldGroup, newLevel);
			return;
		}
	}

	// not part of a display group, then display message
	errorAlert(0,getLanguageLabel('GBL900050'));
}


// **********************************************************************************
// function setSubGroupClick(groupId, subGroupId, levelId)
// On click event for the tab
//
// Parameters:
// tableId - the table id containing all the details
// groupId - group id
// subGroupId - subgroup id
// levelId - level of the sub group
// **********************************************************************************	
function setSubGroupClick(tableId, groupId, subGroupId, levelId)
{
	setDisplayGroup(tableId, groupId, levelId);
}

