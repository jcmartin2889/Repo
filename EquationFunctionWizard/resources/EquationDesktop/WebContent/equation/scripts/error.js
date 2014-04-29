var gblButtonbarFrame = null;
var monitorJob = null;
var uxpBusyIndicator = null;

var intervalJob = 100;
var maximumWaitTime = 1000;
var LEADING_WHITE_SPACE_REGEX = new RegExp("^[\\s]+", "g");

//**********************************************************************************
// function initialiseErrorMonitor()
// This perform initialisation
//
// Parameters:
// None
//**********************************************************************************	
function initialiseErrorMonitor()
{
}


//**********************************************************************************
// function startErrorMonitor()
// This starts the monitoring process
//
// Parameters:
// None
//**********************************************************************************	
function startErrorMonitor()
{
	internal_startErrorMonitor();
	
	// when in UXP, also start UXP error monitoring
	if (isUXP())
	{
		window.top.equxp.startErrorMonitor(getEquationTab().id);
	}
}


//**********************************************************************************
// function internal_startErrorMonitor()
// Invoke the timeout job
//
// Parameters:
// None
//**********************************************************************************	
function internal_startErrorMonitor()
{
	monitorJob = setTimeout(function ()
		{							  
		checkMonitor(); 
	    },
	    intervalJob);

	uxpBusyIndicator = setTimeout(function ()
			{
				if (monitorJob != null)
				{
					showUXPBusyIndicator();
				}
			}, 
			1000);
}


//**********************************************************************************
// function endErrorMonitor()
// This stops the monitoring process
//
// Parameters:
// None
//**********************************************************************************	
function endErrorMonitor()
{
	monitorJob = null;
}


//**********************************************************************************
//function startErrorMonitor()
//This starts the monitoring process
//
//Parameters:
//None
//**********************************************************************************	
function checkMonitor()
{
	// ended already?
	if (monitorJob == null)
	{
		return;
	}
	
	var frame = getFrameCurrent();
	
	// check the frame;
	var body = null;
	try
	{
		body = frame.document.body;
	}
	catch (e)
	{
		var str3 = getLanguageLabel('GBL900085');
		if (isUXP())
		{
			hideSpinnerButton();
			displayUXPErrorMessage(str3);
			closeThisEquationTab();
			return;
		}
		else
		{
			displayMessage(str3);
			closeBrowser();
			monitorJob = null;
			return;
		}
	}

	// driver loaded
	if (frame.eqDriver == null && body != null && body.innerHTML.trim().length > 0)
	{
		// An error has been encountered
		var str1 = getLanguageLabel('GBL900082');
		var str2 = getLanguageLabel('GBL900083');
		
		var innerText = typeof(body.innerText) != 'undefined' ? body.innerText : body.textContent; 
		innerText = innerText.replace(LEADING_WHITE_SPACE_REGEX, "");
		var innerHTML = body.innerHTML; 
		
		// HTTP Status 500?
		var i = innerText.indexOf("HTTP Status 500");
		if (i!=-1 && i <=10)
		{
			pageMonitored(false, false, str2, innerHTML);
			monitorJob = null;
		}
		
		// Application error, "Error(s)" for user disabled etc, session reuse
//		else if (innerText.indexOf("Application Error") ===0 || 
//						innerText.indexOf("Error(s)") ===0 || 
//						innerText.indexOf("WebFacing Session Reuse Error") ===0)
//						innerText.indexOf("Service Temporarily Unavailable") ===0)
		else
		{
			// time-out error has occured?
			var timeout = innerText.indexOf("Your session has been invalidated") >= 0;
			
			// not set at the moment
			var forceLogin = false;
			
			if (timeout)
			{
				pageMonitored(forceLogin, true, str1, innerHTML);
			}
			else
			{
				pageMonitored(forceLogin, false, str2, innerHTML);
			}
			monitorJob = null;
		}
	}
	
	// start again
	if (monitorJob != null)
	{
		internal_startErrorMonitor();
	}
}


//**********************************************************************************
// function pageMonitored()
// This is invoked once a page has been loaded to determine the next action
//
// Parameters:
// forceLogin - true to force redisplay of login page
// timeout    - session has timedout
// msg        - message
// fullMsg  - the full error page
//**********************************************************************************	
function pageMonitored(forceLogin, timeout, msg, fullMsg)
{
	var frame = getFrameMainFrame();
	
	// uxp?
	if (isUXP())
	{
		hideSpinnerButton();
		hideCurrentFrame();
		
		// time-out
		if (timeout || forceLogin)
		{
			displayUXPErrorMessage(msg);
			closeThisEquationTab();
		}
		
		// others
		else if(confirmMessage(msg)) 
		{
			closeThisEquationTab();
		}
		
		// display page
		else
		{
			displayCurrentFrame();
		}
	}
	
	// time-out
	else if (timeout)
	{
		hideCurrentFrame();		
		displayMessage(msg);
		showLoginPage();
	}
	
	// force login
	else if (forceLogin)
	{
		displayMessage(msg);
		showLoginPage();
	}
	
	// non-full desktop, just display the frame
	else if (frame==null)
	{
		displayCurrentFrame();
	}
	
	// others
	else if(confirmMessage(msg))
	{
		checkFrameInError(false);
	}
	
	// display page
	else
	{
		displayCurrentFrame();
	}
	
	endLoadingTransaction();	
	hideSpinnerButton();
}


//**********************************************************************************
// function parseMessage(str)
// Parse the HTML page - removing some tags
// This removes some tags from the HTML page, as it does not make sense when
// displayed via the UXP error message box
//
// Parameters:
// str - the HTML page
//
// Returns
// the parsed HTML page
//**********************************************************************************	
function parseMessage(str)
{
 	var index = str.indexOf("<HR>");
	if (index > 0)
	{
		str = str.slice(index + 4);
	}
	
	str = parseRemoveTag("<IMG ", "</IMG>", str);
	str = parseRemoveTag("<IMG ", ">", str);
	str = parseRemoveTag("<A ", "</A>", str);
	
	str = replaceAll(str,"<HR>", "");
	str = replaceAll(str,"<P>", "<SPAN>");
	str = replaceAll(str,"<P ", "<SPAN ");
	str = replaceAll(str,"</P>", "</SPAN>");

	str = replaceAll(str,"<INPUT>", "</INPUT>");
	str = replaceAll(str,"<INPUT ", "</INPUT>");
	
	return str;
}


//**********************************************************************************
// function parseRemoveTag(startTag, endTag, str)
// Remove from the given string all occurences of the startTag-endTag combination
//
// Parameters:
// startTag - the start marker to be deleted
// endTag   - the end marker to be deleted
// str      - the string
//
// Returns
// the parsed String
//**********************************************************************************	
function parseRemoveTag(startTag, endTag, str)
{
	var startIndex;
	var endIndex;
	startIndex = str.indexOf(startTag);
	while (startIndex >= 0)
	{
		// search of end index
		endIndex = str.indexOf(endTag,startIndex);
		
		if (endIndex >=0)
		{
			if (startIndex==0)
			{
				str = str.slice(endIndex+ endTag.length);
			}
			else
			{
				str = str.slice(0, startIndex) + str.slice(endIndex + endTag.length);
			}
		}

		// next iteration
		startIndex = str.indexOf(startTag,startIndex+1);
	}
	return str;
}
