var isPV = false;

// **********************************************************************************
// function setPVS(refField)
// Determine whether to display the option button or not
//
// Parameters:
// refField: Field name of the selection button
// curField: Field name of this field
// **********************************************************************************
function setPVS(refField, curField)
{	
	isPV = true;
	var widgetButtonHTML = new String();
	var onClickAction = new String();
	var onStype = new String();
	var newHTML = new String();
	var n;

	onClickAction = ' onClick="setFocusAndSubmitKey(\'document.SCREEN.' + refField + '\', \'1\', \'ENTER\', this);" ';
	onStyle = ' title="select" style="cursor: pointer; cursor:hand;" ';
	
	// Check if reference field is input capable
	var obj = document.getElementById(refField);
	var curobj = document.getElementById(curField);
	
	// RTL: no processing, because at this point the field ZLSINP is not yet in the webpage
	//      (since it is at the right most field of the record)
	if (RTL)
	{
		return;
	}

	if(obj.readOnly == false)
	{	
		n = curobj.outerHTML.indexOf(curField) + curField.length;
		newHTML = curobj.outerHTML.substring(0,n) + onClickAction + onStyle + 
					curobj.outerHTML.substring(n);
		curobj.outerHTML = newHTML;
	}

}
