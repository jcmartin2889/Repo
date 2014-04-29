// **********************************************************************************
// function toggleFuncKey()
// Handle processing for the toggling of function key footer display
//
// Parameters:
// None
// **********************************************************************************	
function toggleFuncKey(idFuncDiv, idImage, idAnchor)
{
	// driver displayed, then ignore
	if (getFrameCurrent().eqDriver == 'Y')
	{
		return;
	}
	
	var gblBottomBarFrame = getFrameBottomBar();
	
	// determine whether currently displayed or not
	var str = getCookie("funckeydisp");
	
	// toggle visibility
	if(str!='visible')
	{
		str='visible';
		getFrameScreen().document.getElementById('eqwfWrapFrameset').rows = "0,*,61";
	}
	else 
	{
		str='hidden';
		getFrameScreen().document.getElementById('eqwfWrapFrameset').rows = "0,*,24";
	}

	// set cookie
	setCookie("funckeydisp",str,30);	

	// display or hide the function key DIV item
	var docID = gblBottomBarFrame.document;
	var divID = docID.getElementById(idFuncDiv);
	divID.style.visibility = str;
	setFuncKeyToolbarImage(idImage);
	
	// set the focus to the function key button
	var obj = document.getElementById(idAnchor);
	if (obj!=null)
	{
		obj.blur();  // need this to remove the focus from the input=text
		obj.focus();
	}
}


// **********************************************************************************
// function setFuncKeyToolbarImage(FieldId)
// Set the image style (simulate button down or button up)
//
// Parameters:
// FieldId - id of the parent containing the button
// **********************************************************************************	
function setFuncKeyToolbarImage(FieldId)
{
	obj = document.getElementById(FieldId);
	
	// determine whether currently displayed or not
	var str = getCookie("funckeydisp");
	
	// assume hidden
	if (obj == null)
	{
	}
	else if(str=='visible')
	{
		obj.className = 'buttonDown';
	}
	else
	{
		obj.className = 'button';
	}
}



function scrollObj(obj, offsethorizontal, offsetvertical, rate, iteration)
{
	//if(str!='visible') scrollObj(divID,0,-2,50,10);
	//else scrollObj(divID,0,+2,50,10);
	setTimeout(scrollObj1(obj, offsethorizontal, offsetvertical, rate, iteration), rate);
}


function scrollObj1(obj, offsethorizontal, offsetvertical, rate, increment, iteration)
{
	var left = parseInt(obj.style.left) + offsethorizontal;
	var top  = parseInt(obj.style.top) + offsetvertical;
	obj.style.left = left + "px";
	obj.style.top  = top + "px";
	iteration--;

	// reach number of iteration	
	if(iteration<=0)
	{
		return;
	}

	setTimeout(scrollObj1(obj, offsethorizontal, offsetvertical, rate, iteration), rate);
}
