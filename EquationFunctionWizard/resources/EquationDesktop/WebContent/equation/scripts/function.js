function checkForOpenTransaction(){
	// get current frame
	var currentFrame = getFrameCurrent();

	if (currentFrame.eqDriver != 'Y')
	{					
		// any modal window open?
		if (currentFrame.isAnyModalWindowOpen() || currentFrame.name=="webfacingFrame")
		{
			errorAlert(20,getLanguageLabel("GBL900053"));
			return false;
		}
	}
	
	return true;
}

/* **********************************************************************************
 * Route to a given option in Equation
 * **********************************************************************************/
function routeToOption2(mode,unitId,optionId,ctxt)
{
	routeToOption(mode,unitId,optionId,ctxt);
}
function routeToOption(mode,unitId,optionId,ctxt)
{
	// Webfacing frame - is it still ok?
	if (checkFrameInError(true))
	{
		return false;
	}

	// option specified
	optionId = optionId.trim();
	if(optionId !='')
	{
		// get current frame
		var currentFrame = getFrameCurrent();
		var toolbarFrame = getFrameToolbar();
		var optionInput = toolbarFrame.document.getElementById('optionInput');
		var contextInput = toolbarFrame.document.getElementById('contextInput');
		if (currentFrame.eqDriver != 'Y')
		{					
			// any modal window open?
			if (currentFrame.isAnyModalWindowOpen() || currentFrame.name=="webfacingFrame")
			{
				errorAlert(20,getLanguageLabel("GBL900053"));
				return false;
			}
			
			currentFrame.openChildWindow(mode,unitId,optionId,ctxt,'', '');						
			return true;
		}
		else
		{
			setOptionId(optionId);			
			startLoadingTransaction();
			currentFrame.location.replace("function.jsp?name=&mode=" + mode + "&unit=" + unitId.toUpperCase() + "&optionId=" + optionId.toUpperCase() + "&context=" + ctxt.toUpperCase());
			optionInput.innerText = optionId;
			contextInput.innerText = ctxt;
			return true;
		}
	}
	else
	{
		errorAlert(20,getLanguageLabel("GBL900019"));
		return false;
	}
}

/* **********************************************************************************
 * Log-off session
 * **********************************************************************************/
function logoff()
{
	// get the current frame		
	var currentFrame = getFrameCurrent();

	// ensure it is displaying the driver screen						
	if (currentFrame.eqDriver != null && currentFrame.eqDriver != 'Y')
	{					
		errorAlert(20,getLanguageLabel("GBL900031"));
	}
	else
	{		
		window.top.document.dir = 'ltr';
		if(confirmMessage(getLanguageLabel("GBL900016")))
		{
			logoff_Processing();
			
			// now disable the autologout of the blank frame
			blankFrame = getFrameBlankFrame();
			if (blankFrame != null)
			{
				blankFrame.setAutoLogout(false);
			}
			showLoginPage();			
		}							
	}
}

/* **********************************************************************************
 * Refresh unit
 * **********************************************************************************/
function refresh(unitValue)
{		
	// get the current frame		
	var currentFrame = getFrameCurrent();
						
	// ensure it is displaying the driver screen						
	if (currentFrame.eqDriver != 'Y')
	{					
		errorAlert(20,getLanguageLabel("GBL900032"));
	}
	else
	{		
		//pass a unit
		var loginURL = "login.jsp?user=" + userId + "&system=" + system + "&unit=" + unitValue + "&submit=no";
		window.top.document.dir = 'ltr';
		if(confirmMessage(getLanguageLabel("GBL900015")))
		{
			logoff_Processing();
			
			// now disable the autologout of the blank frame
			blankFrame = getFrameBlankFrame();
			if (blankFrame != null)
			{
				blankFrame.setAutoLogout(false);
			}
			
			window.top.location.replace(loginURL);	
		}									
	}
}

/* **********************************************************************************
 * Event for toolbar
 * **********************************************************************************/
function toolbarEnter(event)
{
	var keynum;
	if(window.event) // IE
	{
		keynum = event.keyCode;
	}
	else if(event.which) // Netscape/Firefox/Opera
	{		
		keynum = event.which;
	}
	
	//check key code and call EnterButton if req'd.
	if(keynum == 13)
	{
		var optionInput = document.getElementById('optionInput');
		var contextInput = document.getElementById('contextInput');
		routeToOption(mode,unitId,optionInput.value,contextInput.value);						
	}	
}

/* **********************************************************************************
 * Event for searching
 * **********************************************************************************/
function searchEnter(event)
{
	var gblNavigatorViewFrame = getFrameNavigatorView();
	
	var keynum;
	if(window.event) // IE
	{
		keynum = event.keyCode;
	}
	else if(event.which) // Netscape/Firefox/Opera
	{		
		keynum = event.which;
	}
	
	//check key code and call EnterButton if req'd.
	if(keynum == 13)
	{
		if(document.getElementById('optionSearch').value.trim() == '')
		{
			errorAlert(20,getLanguageLabel("GBL900020"));
		}
		else
		{		
			document.getElementById('progressBarButton').style.display = '';		
			getOptionList(document.getElementById('optionSearch').value,gblNavigatorViewFrame.document.getElementById('optionSearchResults'));
			switchMenu('SearchDiv');
		}
	}	
}

/* **********************************************************************************
 * Search up or down
 * **********************************************************************************/
function searchUpDown(event)
{
	var gblNavigatorViewFrame = getFrameNavigatorView();
	var keynum;
	if(window.event) // IE
	{
		keynum = event.keyCode;
	}
	else if(event.which) // Netscape/Firefox/Opera
	{		
		keynum = event.which;
	}
	
	// check key code and call page up
	if(keynum == 33)
	{
		if(document.getElementById('optionSearch').value.trim() == '')
		{
			errorAlert(20,getLanguageLabel("GBL900020"));
		}
		else if (gblNavigatorViewFrame.document.getElementById('searchDivPrevious').innerHTML.substr(0,2) == '<A')
		{
			document.getElementById('progressBarButton').style.display = '';		
			getOptionList(document.getElementById('optionSearch').value,gblNavigatorViewFrame.document.getElementById('optionSearchResults'),'-1');
			
		}
	}	
	
	// check key code and call page down
	if(keynum == 34)
	{
		if(document.getElementById('optionSearch').value.trim() == '')
		{
			errorAlert(20,getLanguageLabel("GBL900020"));
		}
		else if (gblNavigatorViewFrame.document.getElementById('SearchDivNext').innerHTML.substr(0,2) == '<A')
		{
			document.getElementById('progressBarButton').style.display = '';			
			getOptionList(document.getElementById('optionSearch').value,gblNavigatorViewFrame.document.getElementById('optionSearchResults'),'1');
			
		}
	}
}

