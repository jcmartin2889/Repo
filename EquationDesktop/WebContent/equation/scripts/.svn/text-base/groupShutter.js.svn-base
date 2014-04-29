// **********************************************************************************
// function function setShowShutterButton(id)
// Processing to display the show shutter button
//
// Parameters:
// id - unique id
// tableId - table id
// **********************************************************************************	
function setShowShutterButton(id,tableId)
{
	var hoverText = getLanguageLabel('GBL000021');
	var labelText = id + BUT_SHUTTER;
	var imagePath = '/' + getWebAppName() + '/equation/images/' + (isUXP()?'arrGrayR.png':'down.gif');
	var onClickAction = "setShutterAction('" + id + "','" + tableId + "')";
	
	// add the button
	var widgetButtonHTML = addWidgetButton(hoverText,labelText,onClickAction,imagePath);
	return widgetButtonHTML;
}

// **********************************************************************************
// function function setHideShutterButton(id)
// Processing to display the hide shutter button
//
// Parameters:
// id - unique id
// tableId - table id
// **********************************************************************************	
function setHideShutterButton(id,tableId)
{
	var hoverText = getLanguageLabel('GBL000020');
	var labelText = id + BUT_SHUTTER;
	var imagePath = '/' + getWebAppName() + '/equation/images/' + (isUXP()?'arrGrayD.png':'up.gif');
	var onClickAction = "setShutterAction('" + id + "','" + tableId + "')";
	
	// add the button
	var widgetButtonHTML = addWidgetButton(hoverText,labelText,onClickAction,imagePath);
	return widgetButtonHTML;
}

// **********************************************************************************
// function setRowDoubleClickAction(id, tableId)
// Processing on double click action for the row
//
// Parameters:
// id - unique id
// tableId - table id
// **********************************************************************************	
function setRowDoubleClickAction(id, tableId)
{
	setShutterAction(id,tableId);
}

// **********************************************************************************
// function setClosedShutter(id, tableId)
// Force closed the shutter
//
// Parameters:
// id - unique id
// tableId - table id
// **********************************************************************************	
function setClosedShutter(id, tableId)
{
	document.getElementById(tableId + GROUP_SHTR_OPEN).value = 'true';
	setShutterAction(id,tableId);
}


// **********************************************************************************
// function setShutterAction(id, tableId)
// Processing to display the shutter
//
// Parameters:
// id - unique id
// tableId - table id
// **********************************************************************************	
function setShutterAction(id, tableId)
{
	var obj = document.getElementById(id + BUT_SHUTTER);
	var shutterDb = document.getElementById(tableId + GROUP_SHTR_OPEN);
	
	// shutter currently open, then close it
	if (shutterDb.value == 'true')
	{
		setRowVisible(tableId, false);
		obj .src = '/' + getWebAppName() + '/equation/images/' + (isUXP()?'arrGrayR.png':'down.gif');
		obj.title = getLanguageLabel('GBL000021');
		shutterDb.value = 'false';
		
		// hide the rest of the columns
		visibleObj(id + GROUPBUTTONS_DIV, false);
		visibleObj(id + GROUPTABS_DIV, false);
	}
	
	// shutter currently closed, then open it
	else
	{
		setRowVisible(tableId, true);
		obj .src = '/' + getWebAppName() + '/equation/images/' + (isUXP()?'arrGrayD.png':'up.gif')
		obj.title = getLanguageLabel('GBL000020');
		shutterDb.value = 'true';

		// show the rest of the columns
		visibleObj(id + GROUPBUTTONS_DIV, true);
		visibleObj(id + GROUPTABS_DIV, true);
		
		// force resizing
		setTableSize(true);
	}

	// lose the focus on the anchor
	obj = document.getElementById(id + BUT_SHUTTER + BUT_HREF);
	obj.blur();

	// focus on the button
	obj = document.getElementById(id + BUT_SHUTTER);
	try
	{
		obj.focus();
	}
	catch (e)
	{
	}
	
	// lose the focus on the input field
	objLastFocusInputField = null;
	objCurrentInputField = null;
	
}

// **********************************************************************************
// function setRowVisible(tableId,visible)
// Hide or show all the rows of the table (except row 1)
//
// Parameters:
// tableId - table id
// visible - true (if visible) else false
// **********************************************************************************	
function setRowVisible(tableId,visible)
{
	var focusObj = false;
	var table = document.getElementById(tableId);
	for (i=1; i<table.rows.length; i++)
	{
		var rowObj = table.rows[i];
		
		// assume it needs to toggle the row 
		var changed = true;

		// row belongs to subgroup and subgroup is not displayed
		if (getAttribute(rowObj,'displaygroupid') != null)
		{
			var displayGroupObj = document.getElementById(OBJ_DSPGRPID + getAttribute(rowObj,'displaygroupid'));
			if (getAttribute(rowObj,'displaygroupsublevel') != displayGroupObj.value)
			{
				changed = false; 
			}
		}
		
		// is field invisible?
		if (changed)
		{
			// get the name of the row
			var index = rowObj.id.indexOf(ROW_SUFFIX);
			var name = rowObj.id.substr(0,index);
			var fieldObj = document.getElementById(name);
			if (fieldObj == null || !isHiddenField(fieldObj))
			{
				changed = true;
			}
			else
			{
				changed = false; // field is already invisible, then do not changed
			}
		}

		// change row visibility 
		if (changed)
		{
			visibleObjInternal(rowObj,visible);
			
			// set the focus on the first visible field
			if (visible && !focusObj)
			{
				x = i;
				objectFocus(fieldObj);
				focusObj = true;
				i = x;
			}
		}
	}
}

