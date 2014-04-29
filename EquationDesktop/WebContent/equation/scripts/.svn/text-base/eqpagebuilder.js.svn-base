// **********************************************************************************
// function format_screen()
// Removes blank lines at top and bottom of screen
// Parameters: None
// **********************************************************************************	
function format_screen()
{
	var wfForm = document.getElementById('SCREEN');
	var wfTable = wfForm.getElementsByTagName('TABLE')[0];
	// table id is non-blank for converted screens
	if( !wfTable.id )
	{
		return;
	}
	
	var rows = wfTable.getElementsByTagName('tr');
	var index;
	var bottom = rows.length - 1;
	var suppress = [];
	var top = -1;
	for( index=0; index < rows.length;index++)
	{
		suppress[index] = canSuppressRow(rows, index);
		if( !suppress[index])
		{
			bottom = index;
			if( top === -1)
			{
				top = index;
			}
		}
	}

	// top has not been set?
	if (top==-1)
	{
		top = 0;
	}
	
	// Now update DOM:
	for( index=0; index< top;index++)
	{
		rows[index].style.display = "none";
	}
	for( index=rows.length-1; index > bottom;index--)
	{
		rows[index].style.display = "none";
	}
	// Remaining rows; fix the height:
	var index; 
	for( index = top; index <= bottom; index++) 
	{
		if( suppress[index])
		{
			if( index > 0 && suppress[index - 1])
			{
				rows[index].style.display = "none";
			}
			else
			{
				rows[index].style.height = "10px";
			}			
		}
		else
		{
			rows[index].style.height = "32px";
		}
	}
	
	if( top > 0  )
	{
		wfTable.style.paddingTop = "11px"; // For 15px to top of input field
	}

	// Increase field sizes
	var inputs = wfTable.getElementsByTagName('input');
	for( index=0; index < inputs.length; index++)
	{
		if( inputs[index].type === 'text')
		{
			var newWidth = 12 + (inputs[index].size * 8)
			inputs[index].style.width = newWidth + "px";
		}
	}

	// Set the left margin
	wfTable.style.marginLeft = '14px';
}

// **********************************************************************************
// function canSuppressRow(rows,index)
//
// Parameters:
// rows - array of tr elements
// index - current row index
// returns true if the row can be suppressed
// **********************************************************************************	
function canSuppressRow( rows, index)
{
	var suppress = false;
	if( rows[index].style.visibility == "hidden")
	{
		suppress = true;
	}
	else
	{
		var column;
		var empty = true;
		for(column=0; column < rows[index].cells.length;column++)
		{
			var cell = rows[index].cells[column];
			var spans = cell.getElementsByTagName('span');
			if( spans.length > 0 )
			{
				// Assume only one span
				var text = typeof(spans[0].innerText) != 'undefined' ? spans[0].innerText : spans[0].textContent; 
				if(text.trim().length > 0 )
				{
					if( elementVisible( spans[0] ))
					{
						empty = false;
						break;
					}
				}
			}
			else
			{
				// Assume either span OR content
				if( cell.innerHTML != '&nbsp;' && cell.innerHTML.trim() != '' )
				{
					empty = false;
					break;
				}
			}
		}
		if( empty )
		{
			suppress = true;
		}
	}
	return suppress;
}


// **********************************************************************************
// function elementVisible(obj)
//
// Parameters:
// obj - HTML element
// returns true if the element is visible
// **********************************************************************************
function elementVisible(obj)
{
    if (obj == document) return true

    if (!obj) return false
    if (!obj.parentNode) return false
    if (obj.style) {
        if (obj.style.display == 'none') return false
        if (obj.style.visibility == 'hidden') return false
    }

    //Try the computed style in a standard way
    if (window.getComputedStyle) {
        var style = window.getComputedStyle(obj, "")
        if (style.display == 'none') return false
        if (style.visibility == 'hidden') return false
    }

    //Or get the computed style using IE's  proprietary way
    var style = obj.currentStyle
    if (style) {
        if (style['display'] == 'none') return false
        if (style['visibility'] == 'hidden') return false
    }

    return elementVisible(obj.parentNode)
}

