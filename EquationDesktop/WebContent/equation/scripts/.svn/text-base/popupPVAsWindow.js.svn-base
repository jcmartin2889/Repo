var gblPromptTitle = '';
var gblPromptFieldId = '';
var gblPromptId = '';
var gblPromptDecode = '';
var gblPromptFile = '';
var gblPromptKeys = '';
var gblReturnFieldLabels = '';
var gblReturnFieldPositions = '';
var gblReturnFieldLengths = '';
var gblBackStart = '';
var gblBackLength = '';
var gblDataFields = '';
var gblDbFields = '';
var gblMaxResults = '';
var asPopupWindow = false;

// **********************************************************************************
// function popupPVAsWindow_onload(e)
// On load event
//
// Parameters:
// e - the event
// **********************************************************************************	
function popupPVAsWindow_onload(e)
{
	// set flag
	asPopupWindow = true;

	// initialise fields
	gblPromptTitle = window.opener.gblPromptTitle;
	gblPromptFieldId = window.opener.gblPromptFieldId;
	gblPromptId = window.opener.gblPromptId;
	gblPromptDecode = window.opener.gblPromptDecode;
	gblPromptSecurity = window.opener.gblPromptSecurity;
	gblPromptFile = window.opener.gblPromptFile;
	gblPromptKeys = window.opener.gblPromptKeys;
	gblReturnFieldNames = window.opener.gblReturnFieldNames;
	gblReturnFieldLabels = window.opener.gblReturnFieldLabels;
	gblReturnFieldPositions = window.opener.gblReturnFieldPositions;
	gblReturnFieldLengths = window.opener.gblReturnFieldLengths;
	gblBackStart = window.opener.gblBackStart;
	gblBackLength = window.opener.gblBackLength;
	gblDataFields = window.opener.gblDataFields;
	gblDbFields = window.opener.gblDbFields;
	gblReturnFieldHdrPos = window.opener.gblReturnFieldHdrPos;
	gblMaxResults = window.opener.gblMaxResults;
	
	getNewPVList(gblPromptTitle,gblPromptFieldId,gblPromptId,gblPromptDecode,gblPromptSecurity,gblPromptFile,gblPromptKeys,
						gblReturnFieldNames,gblReturnFieldLabels,gblReturnFieldPositions,gblReturnFieldLengths,
						gblBackStart,gblBackLength,
						gblDataFields,gblDbFields,gblReturnFieldHdrPos,
						true,gblMaxResults
						);
	
}

// **********************************************************************************
// function popupPVAsWindow_bodyKeyDown(e)
// On key down event
//
// Parameters:
// e - the event
// **********************************************************************************	
function popupPVAsWindow_bodyKeyDown(e)
{
}

// **********************************************************************************
// function popupPVAsWindow_bodyKeyUp(e)
// On load event
//
// Parameters:
// e - the event
// **********************************************************************************	
function popupPVAsWindow_bodyKeyUp(e)
{
	var keynum = rtvKeyboardKey(e);

	if (keynum==27)
	{
		window.close();
	}
}


// **********************************************************************************
// function popupPVAsWindow_onresize(e)
// On resize event
//
// Parameters:
// e - the event
// **********************************************************************************	
function popupPVAsWindow_onresize(e)
{
}

// **********************************************************************************
// function popupPVAsWindow_onbeforeUnload(event)
// On close event
//
// Parameters:
// event - the event
// **********************************************************************************	
function popupPVAsWindow_onbeforeUnload(event)
{
	try
	{
		// explorer?
		if (window.event)
		{
			e = window.event;
		}
		
		if (e.clientX <= 0 || e.clientY <= 0)
		{
			if (!window.close())
			{
				dt_pvClosePopUp(false);
			}
		}
	}
	catch(e)
	{
	}
}


//**********************************************************************************
// function popupPVAsWindow_onunload(event)
// On before unload event
//
// Parameters:
// event - the event
//**********************************************************************************	
function popupPVAsWindow_onunload(event)
{
	try
	{
		if (!window.close())
		{
			dt_pvClosePopUp(false);
		}
	}
	catch(e)
	{
	}
}

