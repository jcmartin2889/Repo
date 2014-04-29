// this determines if all pages have been loaded by the user
var pageFullyLoaded = false;

// spooled file loaded?
var spooledFileLoaded = false;

//**********************************************************************************
// function getStartPageNo()
// Return the start page no of the page range
//
// Parameters:
// None
//
// Returns:
// the start page number of the page range
//**********************************************************************************	
function getStartPageNo()
{
	try
	{
		return parseInt(document.getElementById('startPageNo').value);
	}
	catch (e)
	{
		return 0;
	}
}


//**********************************************************************************
// function getEndPageNo()
// Return the end page no of the page range
//
// Parameters:
// None
//
// Returns:
// the end page number of the page range
//**********************************************************************************	
function getEndPageNo()
{
	try
	{
		return parseInt(document.getElementById('endPageNo').value);
	}
	catch (e)
	{
		return 0;
	}
}


//**********************************************************************************
// function rtvPageNo()
// Validate and return the appropriate page number 
//
// Parameters:
// pageNo - the page number
//
// Returns:
// the validated page number 
//**********************************************************************************	
function rtvPageNo(pageNo)
{
	// invalid page number, then display first page
	if (pageNo <= 0)
	{
		pageNo = 1;
	}
	
	// invalid page number, then display last page
	else if (pageNo > totalPageNo)
	{
		pageNo = totalPageNo;
	}
	
	// return the page number
	return pageNo; 
}


//**********************************************************************************
// function setStartPageNo(value)
// Set the start page no of the page range 
//
// Parameters:
// value - the new value
//**********************************************************************************
function setStartPageNo(value)
{
	document.getElementById('startPageNo').value = value;
}


//**********************************************************************************
// function setEndPageNo(value)
// Set the end page no of the page range 
//
// Parameters:
// value - the new value
//**********************************************************************************
function setEndPageNo(value)
{
	document.getElementById('endPageNo').value = value;
}


//**********************************************************************************
// function setStartingPage(value)
// Set the starting page 
//
// Parameters:
// value - the new value
//**********************************************************************************
function setStartingPage(value)
{
	document.getElementById('startingPage').innerText = value;
}


//**********************************************************************************
// function spooledFileHeader_onLoad()
// On load event  
//
// Parameters:
// None
//**********************************************************************************	
function spooledFileHeader_onLoad()
{
	// resize it
	spooledFileHeader_onResize();
	
	// display initial page
	cmdGoPageSelectionFirst_onClick();	
}


//**********************************************************************************
// function spooledFileHeader_onResize()
// On resize event 
//
// Parameters:
// None
//**********************************************************************************	
function spooledFileHeader_onResize()
{
	// fully loaded - then hide the buttons
	if (pageFullyLoaded)
	{
		window.top.frames['spooledFileFrame'].rows="0,*";
	}
	
	// make sure the buttons are visible
	else
	{
		window.top.frames['spooledFileFrame'].rows=document.body.clientHeight + ",*";
	}
}


//**********************************************************************************
// function doPageSelection(fromPageNo, toPageNo)
// Perform display page selection 
//
// Parameters:
// fromPageNo - starting page number to display
// toPageNo   - end page number to display
//**********************************************************************************
function doPageSelection(fromPageNo, toPageNo)
{
	// spooled file is in progress or fully loaded already?
	if (document.getElementById('maintable').disabled || pageFullyLoaded)
	{
		return;
	}
	
	// check if this is within the limit
	var numOfPages = toPageNo - fromPageNo + 1;
	if (maxpagesize > 0 && numOfPages > maxpagesize)
	{
		// show warning to the user
		if(confirmMessage(getLanguageLabel("GBL900088")))
		{
			// user pressed OK, then do nothing
		}
		
		// user chooses to cancel, then close the window
		else
		{
			// no spooled file displayed, then close the window
			if (!spooledFileLoaded)
			{
				closeBrowser();
			}
			return;
		}
	}
	
	// To page is less?
	if (toPageNo < fromPageNo)
	{
		toPageNo = fromPageNo;
	}
	
	// set the new page numbers
	setStartPageNo(fromPageNo);
	setEndPageNo(toPageNo);
	
	// display it
	displaySpooledFile(fromPageNo, toPageNo);
	
	// disable or enable buttons
	showHideButtonPageSelection(fromPageNo, toPageNo);
	spooledFileLoaded = true;
}


//**********************************************************************************
// function showHideButtonPageSelection(pageNo)
// Enable or disable buttons 
//
// Parameters:
// fromPageNo - start page number
// toPageNo   - end page number
//**********************************************************************************	
function showHideButtonPageSelection(fromPageNo, toPageNo)
{
	pageFullyLoaded = (fromPageNo<=1) && (toPageNo>=totalPageNo);
	document.getElementById('cmdGoPageSelectionFirst').disabled = (fromPageNo==1);
	document.getElementById('cmdGoPageSelectionPrev').disabled = (fromPageNo==1);
	document.getElementById('cmdGoPageSelectionNext').disabled = (toPageNo==totalPageNo);
	document.getElementById('cmdGoPageSelectionLast').disabled = (toPageNo==totalPageNo);
	document.getElementById('cmdGoPageSelectionAll').disabled = pageFullyLoaded;
}


//**********************************************************************************
// function cmdGoPageSelection_onClick()
// On click event to display the selected range of page 
//
// Parameters:
// None
//**********************************************************************************	
function cmdGoPageSelection_onClick()
{
	// retrieve the start page number
	var startPageNo = rtvPageNo(getStartPageNo());
	var endPageNo = rtvPageNo(getEndPageNo());
	
	// action
	doPageSelection(startPageNo, endPageNo);
}


//**********************************************************************************
// function cmdGoPageSelectionAll_onClick()
// On click event to display all pages 
//
// Parameters:
// None
//**********************************************************************************	
function cmdGoPageSelectionAll_onClick()
{
	// retrieve the start page number
	var startPageNo = 1;
	var endPageNo = totalPageNo;
	
	// action
	doPageSelection(startPageNo, endPageNo);
}


//**********************************************************************************
// function cmdGoPageSelectionFirst_onClick()
// On click event to display the first set of pages
//
// Parameters:
// None
//**********************************************************************************	
function cmdGoPageSelectionFirst_onClick()
{
	// retrieve the start page number
	var startPageNo = 1;
	var endPageNo = rtvPageNo(pagesize);
	
	// action
	doPageSelection(startPageNo, endPageNo);
}


//**********************************************************************************
// function cmdGoPageSelectionPrev_onClick()
// On click event to display the previous set of pages
//
// Parameters:
// None
//**********************************************************************************	
function cmdGoPageSelectionPrev_onClick()
{
	// retrieve the start page number
	var endPageNo = rtvPageNo(getStartPageNo() - 1);
	var startPageNo = rtvPageNo(endPageNo - pagesize + 1);
	
	// action
	doPageSelection(startPageNo, endPageNo);
}


//**********************************************************************************
// function cmdGoPageSelectionNext_onClick()
// On click event to display the next set of pages
//
// Parameters:
// None
//**********************************************************************************	
function cmdGoPageSelectionNext_onClick()
{
	// retrieve the start page number
	var startPageNo = rtvPageNo(getEndPageNo() + 1);
	var endPageNo = rtvPageNo(startPageNo + pagesize - 1);
	
	// action
	doPageSelection(startPageNo, endPageNo);
}


//**********************************************************************************
// function cmdGoPageSelectionLast_onClick()
// On click event to display the last set of pages
//
// Parameters:
// None
//**********************************************************************************	
function cmdGoPageSelectionLast_onClick()
{
	// retrieve the start page number
	var startPageNo = totalPageNo - pagesize + 1;
	var endPageNo = totalPageNo;
	
	// action
	doPageSelection(startPageNo, endPageNo);
}


//**********************************************************************************
// function startPageNo_onKeyDown(e)
// On keydown event for the start page no text 
//
// Parameters:
// e - the event
//**********************************************************************************	
function startPageNo_onKeyDown(e)
{
	var keynum = rtvKeyboardKey(e);
	
	// enter key?
	if (keynum==13)
	{
		cmdGoPageSelection_onClick();
	}
}


//**********************************************************************************
// function endPageNo_onKeyDown(e)
// On keydown event for the end page no text 
//
// Parameters:
// e - the event
//**********************************************************************************	
function endPageNo_onKeyDown(e)
{
	var keynum = rtvKeyboardKey(e);
	
	// enter key?
	if (keynum==13)
	{
		cmdGoPageSelection_onClick();
	}
}

//**********************************************************************************
// function displaySpooledFile(pageNo)
// Display spooled file 
//
// Parameters:
// fromPageNo - start page number
// toPageNo   - end page number
//**********************************************************************************	
function displaySpooledFile(fromPageNo, toPageNo)
{
	// Do not directly call the EqSpooledFileServlet, as there does not seem a way 
	// to determine if it has already been loaded.
	
	// Load first the blankSpooledFile.jsp - to determine when loading complete, frame.
	// document.readyState == complete
	
	// Then load the EqSpooledFileServlet - to determine when loading complete, 
	// frame.document == null
	
	setTimeout( 
			function() 
			{
				loadingBlankJsp(fromPageNo, toPageNo);
			}
			,10);
}


//**********************************************************************************
// function loadingBlankJsp(fromPageNo, toPageNo)
// Load the blank jsp 
//
// Parameters:
// fromPageNo - start page number
// toPageNo   - end page number
//**********************************************************************************	
function loadingBlankJsp(fromPageNo, toPageNo)
{
	// retrieve the frame where to load the spooled file
	var frame = window.top.frames['spooledFileData'];
	frame.location.replace('/' +  getWebAppName() + "/equation/jsp/blankSpooledFile.jsp" 
			+ "?rtl=" + RTL);
	
	// check if blankSpooledFile.jsp has been loaded successfully
	checkBlankJspLoaded(frame, fromPageNo, toPageNo);
}


//**********************************************************************************
// function loadedBlankJsp(fromPageNo, toPageNo)
// Processing after blankSpooledFile.jsp has been loaded.  This will call the EqSpooledFile
//    Servlet to actually display the spooled file.
//
// Parameters:
// fromPageNo - start page number
// toPageNo   - end page number
//**********************************************************************************	
function loadedBlankJsp(fromPageNo, toPageNo)	
{	
	// to page not specified?
	if (toPageNo == null)
	{
		toPageNo = fromPageNo;
	}

	// construct the url
	var spooledFileURL = "EqSpooledFileServlet" + 
		"?spoolName=" + escape(spoolName) + 
		"&jobName=" + escape(jobName) +
		"&jobUser=" + jobUser + 
		"&jobNumber=" + jobNumber + 
		"&spoolNumber=" + spoolNumber +
    	"&fromPageNo=" + fromPageNo +
		"&toPageNo=" + toPageNo;

	// retrieve the frame where to load the spooled file
	var frame = window.top.frames['spooledFileData'];
	
	// put a marker on the page
	document.body.style.cursor='wait';
	document.getElementById('maintable').disabled = true;
	
	// display it on the data frame
	frame.location.replace(spooledFileURL);	
	
	// set the starting page
	if (fromPageNo == toPageNo)
	{
		setStartingPage(fromPageNo);
	}
	else
	{
		setStartingPage("(" + fromPageNo + " - " + toPageNo + ")");
	}

	// check if page has loaded
	checkPDFLoaded(frame, fromPageNo, toPageNo);
}


//**********************************************************************************
// function checkPDFLoaded()
// Determine if PDF has finished loading or not, in order to refresh the buttons
//
// Parameters:
// frame      - frame where spooled file is being loaded
// fromPageNo - start page number
// toPageNo   - end page number
//**********************************************************************************	
function checkPDFLoaded(frame, fromPageNo, toPageNo)
{
	// check frame's document
	var isFrameDocumentValid = isFrameDocumentExists(frame);
	
	// not yet finish loading.  When frame.document is NOT NULL, then it means it is currently
	// displaying the blank page.  Once PDF has started loading, frame.document become NULL
	if(isFrameDocumentValid)
	{
		// recheck again
		setTimeout( 
				function() 
				{
					checkPDFLoaded(frame, fromPageNo, toPageNo);
				}
				,200);
	}
	
	// PDF has started loading, wait until the page has fully loaded
	else if (frame.frameElement.readyState != 'complete')
	{
		// recheck again
		setTimeout( 
				function() 
				{
					checkPDFLoaded(frame, fromPageNo, toPageNo);
				}
				,200);
	}
	
	// done loading
	else
	{
		// change back the cursor
		document.body.style.cursor='';
		
		// enable the buttons depending if fully loaded or not
		document.getElementById('maintable').disabled = pageFullyLoaded;
		
		// resize
		if (pageFullyLoaded)
		{
			spooledFileHeader_onResize();
		}
	}
}


//**********************************************************************************
// function checkBlankJspLoaded(frame, fromPageNo, toPageNo)
// Determine if blankSpooledFile.jsp has finished loading or not.  Once blank.jsp is loaded,
//    then it will start loading the spooled file
//
// Parameters:
// frame      - frame where blank page is being loaded
// fromPageNo - start page number
// toPageNo   - end page number
//**********************************************************************************	
function checkBlankJspLoaded(frame, fromPageNo, toPageNo)
{
	// check frame's document
	var isFrameDocumentValid = isFrameDocumentExists(frame);
	
	// not yet finish loading
	if(!isFrameDocumentValid || 
			(frame.location.href.indexOf('blankSpooledFile' > 0) && 
			 frame.document && frame.document.readyState!="complete" && 
			 frame.document.eqDriver!="X"))
	{
		// recheck again
		setTimeout( 
				function() 
				{
					checkBlankJspLoaded(frame, fromPageNo, toPageNo);
				}
				,10);
	}
	
	// finish loading, then load the spooled file
	else
	{
		setTimeout( 
				function() 
				{
					loadedBlankJsp(fromPageNo, toPageNo);
				}
				,10);
	}	
}


//**********************************************************************************
// function isFrameDocumentExists(frame)
// Determine if the frame's document exists or not
//
// Parameters:
// frame - the frame to be checked
//**********************************************************************************	
function isFrameDocumentExists(frame)
{
	// determine if the frame's document is valid or not
	try
	{
		return frame.document != null;
	}
	catch(e)
	{
		// any error, then assume frame's document is not valid anymore
		return false;
	}	
}

