
// **********************************************************************************
// This create the equivalent version of SELECT HTML element to have more 
// control over the display and alignment
// **********************************************************************************

// **********************************************************************************
// function MySelect(id,highlight,col)  
// Create our custom made version of SELECT HTML
//
// Parameter:
// id        - the id for this element
// name		 - the name of the variable for this custom select - needed during event handling!!
// highlight - the class style when a selection is made
//
// Returns:
// None
// **********************************************************************************
function MySelect(id,name,highlight)
{
	this.id = id;
	this.name = name;
	this.highlight=highlight;
	this.afterHTML = '';
	
	// events
	this.clear     = MySelect_clear;
	this.addData   = MySelect_addData;
	this.getHTML   = MySelect_getHTML;
	this.selectRow = MySelect_selectRow;
	this.getIndex  = MySelect_getIndex;
	this.getLength = MySelect_getLength;
	this.getValue  = MySelect_getValue;
	this.addDataAfter = MySelect_addDataAfter;

	this.onKeyDown = MySelect_onKeyDown;
	this.onMouseOverRow = MySelect_onMouseOverRow;
	this.focus     = MySelect_focus;
	
	this.isFirstData= MySelect_isFirstData;
	this.isLastData = MySelect_isLastData;
	this.moveSelectionUp   = MySelect_moveSelectionUp;
	this.moveSelectionDown = MySelect_moveSelectionDown;
	this.moveSelectionFirst = MySelect_moveSelectionFirst;
	this.moveSelectionLast = MySelect_moveSelectionLast;
	
	this.clear();
}


//**********************************************************************************
// function MySelect_clear()  
// reset the list
//
// Parameter:
// None
//**********************************************************************************
function MySelect_clear()
{
	this.html = '';
	this.index = -1;
	this.length = 0;
}


//**********************************************************************************
// function MySelect_getHTML()  
// Return the generated HTML
//
// Parameter:
// None
//**********************************************************************************
function MySelect_getHTML()
{
	return	"<div "
			+ "onkeydown='return " + this.name + ".onKeyDown(event)'"
			+ ">"
			+ "<table " 
			+ "id='" + this.id + "' " 
			+ ">" 
			+ this.html 
			+ "</table>"
			+ this.afterHTML;
			+ "</div>"
			;
}


//**********************************************************************************
// function MySelect_addDataAfter(data)  
// Add additional details (in HTML Format) after the custom select
//
// Parameter:
// data - the HTML details to be added
//**********************************************************************************
function MySelect_addDataAfter(data)
{
	this.afterHTML = data;
}

// **********************************************************************************
// function MySelect_addData(value, data, style, selectFlag)  
// Add a row of data
//
// Parameter:
// value      - the internal value associated with the data
// data       - the array of data to be added
// style      - the class style of the data
// selectFlag - determines whether the data can be selected or not
// **********************************************************************************
function MySelect_addData(value, data, style, selectFlag)
{
	// index
	this.length++;
	
	var col = "";
	for (var i=0; i<data.length ; i++)
	{
		col += MySelect_addCol(data[i]);
	}
	
	this.html += MySelect_addRow(value, col, this.length-1, style, selectFlag, this.name);
}


//**********************************************************************************
// function MySelect_addRow(data, style, selectFlag)  
// Create a row
//
// Parameter:
// value      - the internal value associated with the data
// data       - the data to be added
// index      - the unique index within the list
// style      - the class style of the data
// selectFlag - determines whether the data can be selected or not
//**********************************************************************************
function MySelect_addRow(value, data, index, style, selectFlag, customSelectName)
{
	var attrib = '';
	if (selectFlag)
	{
		attrib = "select='true'";
	}
	else
	{
		attrib = "select='false'";
	}

	return "<tr " 
			+ "onmouseover='" + customSelectName + ".onMouseOverRow(event," + index + ")'"
			+ "value='" + value + "' "
			+ "class='" + style + "' "
			+ attrib
			+ ">" 
			+ data 
			+ "</tr>";
}

//**********************************************************************************
// function MySelect_addCol(data)  
// Create a column
//
// Parameter:
// data - the text to appear in the column
//**********************************************************************************
function MySelect_addCol(data)
{
	return "<td class='wf_LTOR'>" + data + "</td>";
}

//**********************************************************************************
// function MySelect_onKeyDown(rowObj)  
// Handle key up and key down event to control selected data
//
// Parameter:
// None
//**********************************************************************************
function MySelect_onKeyDown(e)
{
	var keynum = rtvKeyboardKey(e);

	// key down
	if(keynum == 40)
	{
		if (this.moveSelectionDown())
		{
			disableKeyboardKey(e);
			return false;
		}
	}
	
	// key up
	if(keynum == 38)
	{ 		
		if (this.moveSelectionUp())
		{
			disableKeyboardKey(e);	
			return false;
		}
	}
}


//**********************************************************************************
// function MySelect_SelectRow(rowIndex)  
// Select a row
//
// Parameter:
// rowIndex - the row to be selected
//
// Return:
// true - if row is selected
//**********************************************************************************
function MySelect_selectRow(rowIndex)
{
	// same row?
	if (this.index == rowIndex)
	{
		return true;
	}
	
	// get the object
	var table = document.getElementById(this.id);

	// can we select the selected row?
	if (rowIndex >=0 && getAttribute(table.rows[rowIndex],"select")=='false')
	{
		return false;
	}
	
	// deselect the current row
	if (this.index >= 0)
	{
		removeClassName(table.rows[this.index], this.highlight);
	}
	
	// select the current row
	this.index = rowIndex;
	if (rowIndex >=0)
	{
		addClassName(table.rows[this.index], this.highlight);
		return true;
	}
	else
	{
		return false;
	}
}


//**********************************************************************************
// function MySelect_moveSelectionDown()  
// Move the current selection down
//
// Parameter:
// None
//
// Returns:
// true if moves down
//**********************************************************************************
function MySelect_moveSelectionDown()
{
	if (this.index < this.length-1)
	{
		var index = this.index+1;
		while(!this.selectRow(index))
		{
			index++;
			if (index>=this.length)
			{
				return false;
			}
		}
		return true;
	}
	else
	{
		return false;
	}
}


//**********************************************************************************
// function MySelect_moveSelectionUp()  
// Move the current selection up
//
// Parameter:
// None
//
// Returns:
// true if moves up
//**********************************************************************************
function MySelect_moveSelectionUp()
{
	if (this.index > 0)
	{
		var index = this.index-1;
		while(!this.selectRow(index))
		{
			index--;
			if (index<0)
			{
				return false;
			}
		}
		return true;
	}
	else
	{
		return false;
	}
}


//**********************************************************************************
// function MySelect_moveSelectionFirst()  
// Move to the first selection
//
// Parameter:
// None
//
// Returns:
// true if selected
//**********************************************************************************
function MySelect_moveSelectionFirst()
{
	var index = 0;
	while (!this.selectRow(index))
	{
		index++;
	}
	return true;
}


//**********************************************************************************
// function MySelect_moveSelectionLast()  
// Move to the last selection
//
// Parameter:
// None
//
// Returns:
// true if selected
//**********************************************************************************
function MySelect_moveSelectionLast()
{
	var index = this.length -1 ;
	while (!this.selectRow(index))
	{
		index--;
	}
	return true;
}


//**********************************************************************************
// function MySelect_isFirstData()  
// Determine if currently selected data is the first one
//
// Parameter:
// None
//
// Returns:
// true if currently selected data is the first one
//**********************************************************************************
function MySelect_isFirstData()
{
	return this.index == 0;
}


//**********************************************************************************
// function MySelect_isLastData()  
// Determine if currently selected data is the last one
//
// Parameter:
// None
//
// Returns:
// true if currently selected data is the last one
//**********************************************************************************
function MySelect_isLastData()
{
	return this.index == (this.length-1);
}


//**********************************************************************************
// function MySelect_getIndex()  
// Return the selected index
//
// Parameter:
// None
//
// Returns:
// the selected row
//**********************************************************************************
function MySelect_getIndex()
{
	return this.index;
}


//**********************************************************************************
// function MySelect_getLength()  
// Return the number of records
//
// Parameter:
// None
//
// Returns:
// the number of records
//**********************************************************************************
function MySelect_getLength()
{
	return this.length;
}


//**********************************************************************************
// function MySelect_getValue()  
// Return the value of the selected index
//
// Parameter:
// None
//
// Returns:
// the value of the selected index
//**********************************************************************************
function MySelect_getValue()
{
	if (this.index > 0)
	{
		var table = document.getElementById(this.id);
		return table.rows[this.index].value;
	}
	return null;
}


//**********************************************************************************
// function MySelect_onMouseOverRow(event,customSelectObj,index)  
// Select the row
//
// Parameter:
// event - the event
// customSelectObj - the object
// index - the index to be selected
//**********************************************************************************
function MySelect_onMouseOverRow(event,index)
{
	this.focus();
	return this.selectRow(index);
}


//**********************************************************************************
// function MySelect_focus()  
// Set the focus to the select
//
// Parameter:
// None
//**********************************************************************************
function MySelect_focus()
{
	var table = document.getElementById(this.id);
	var obj = table.nextSibling;
	if (obj != null)
	{
		obj.focus();
	}
	else
	{
		table.focus();
	}
}
