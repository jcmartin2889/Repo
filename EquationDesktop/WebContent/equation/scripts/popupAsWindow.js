var asPopupWindow = false;
var popuptype = '';

// **********************************************************************************
// function popupAsWindow_onload(e)
// On load event
//
// Parameters:
// None
// **********************************************************************************	
function popupAsWindow_onload(e)
{
	// as popup
	asPopupWindow = true;

	// get widget type
	popupType = window.opener.popupParam1;
	
	// calendar
	if (popupType == 'CAL')
	{
		document.title = getLanguageLabel("CALCAL");
		calShow(window.opener.popupParam2,document.getElementById('anchorTag'));
	}
	
	else if (popupType == 'FRQ')
	{
		document.title = getLanguageLabel("FRQFRQ");
		activateFRQPopup(window.opener.popupParam2,'anchorTag');
	}

	else if (popupType == 'LST')
	{
		document.title = getLanguageLabel(window.opener.popupParam2 + 'DES');
		activateListPopup(window.opener.popupParam2,window.opener.popupParam3,'anchorTag');
	}

}

// **********************************************************************************
// function popupAsWindow_bodyKeyDown(e)
// On key down event
//
// Parameters:
// None
// **********************************************************************************	
function popupAsWindow_bodyKeyDown(e)
{
}

// **********************************************************************************
// function popupAsWindow_bodyKeyUp(e)
// On load event
//
// Parameters:
// None
// **********************************************************************************	
function popupAsWindow_bodyKeyUp(e)
{
}


// **********************************************************************************
// function popupAsWindow_onresize(e)
// On resize event
//
// Parameters:
// None
// **********************************************************************************	
function popupAsWindow_onresize(e)
{
}

