// **********************************************************************************
// function eqInit_StandAlone()
// Initialisation for stand-alone window (e.g not in container)
//
// Parameters:
// None
// **********************************************************************************	
function eqInit_StandAlone()
{
	//declaration for onkeydown and onhelp to ensure that they are handled even for stand-alone pop-up windows.
	//This is needed when JSP calls only eqInit_StandAlone intead of calling eqInit.
	if (document.addEventListener)
	{
			document.addEventListener('keydown', documentonkeydown, false);
	}
	else
	{
			document.attachEvent('onkeydown', documentonkeydown);
	}
	if( !isUXP())
	{
		document.onhelp = supressBrowserHelp;
	}
}


//**********************************************************************************
// function documentonkeydown(e)
// On key down event
//
// Parameters:
// e - event
//**********************************************************************************	
function documentonkeydown(e)
{
	// determine if this is a popup window or not
	var isMainWindow = (window.opener == null);
	
	// retrieve the key code
	var evt = e || window.event;
	
	var keycode = evt.keyCode || evt.charCode;
	var shiftkey = evt.shiftKey;

	// check the source object
	var evtSrc = evt.target || evt.srcElement;
	var id = evtSrc.id;
	var inputTag = evtSrc.tagName == "INPUT" || evtSrc.tagName == "A";

	// is this the print button?
	var printbutton = false;
	try
	{
		printbutton = (id == getLanguageLabel('GBLPRNT'));
	} 
	catch (e)
	{
	}

	var optionId = getCookie('optionID');
	// for maker-checker list screens, enable the Enter button on F10-Decline
	if ((optionId.toUpperCase() == 'AWL' || 
		optionId.toUpperCase() == 'RJL' ||
		optionId.toUpperCase() == 'SBL' || 
		optionId.toUpperCase() == 'ARL') &&
		keycode==121)
	{
		setCookie('disableEnter', 'N');
	}
	
	// Function Keys to be trapped (only if this is not a popup)
	if (isMainWindow)
	{
		// .. F1 (key code=112) to F11 (key code=122)
		// .. F12 (key code=123) - Allow IE's F12=Developer's for debugging purposes (when the source element is the Print button)
		//          Note: no specific requirement why Print button.  This button happens to be always enabled thus will cater
		//          for both normal desktop / classic login / direct desktop
		// .. Page up or Page down
		// .. Enter key (only if non-text field)
		if (   (keycode >=112 && keycode<=122) 
			|| (keycode ==123 && shiftkey)
			|| (keycode ==123 && !shiftkey && !printbutton)
			|| (keycode >= 33 && keycode <= 34)
			|| (keycode == 13 && shiftkey==false && !inputTag)
			)
			{
				return trapTransactionFunctionKeys(evt);
			}
	}
		
	// Disable F3
	if(keycode ==114)
	{
		window.event.keyCode = 505;
		return false;
	}

	// Disable F5
	if(window.event && window.event.keyCode == 116)
	{
		window.event.keyCode = 505;
	}

	// This is the dummy key code - always returns false
	if(window.event && window.event.keyCode == 505)
	{ 
		return false;
	}
	
	// For Firefox compatibility (disable F5)
	if(e.which && e.which.keyCode == 116)
    {
		e.which.keyCode = 505;
    }
	
	// This is the dummy key code - always returns false
	if(e.which && e.which.keyCode == 505)
	{ 
		return false;
	}
	
	if(evt.altKey && keycode==72 )	// Alt+H click 
	{
		if( isUXP() )
		{
			dispHelp();
			evt.preventDefault();
		}	
	}
}


//**********************************************************************************
// function documentonkeyup(e)	
// On key up event
//
// Parameters:
// e - event
//**********************************************************************************	
function documentonkeyup(e)	
{	
	if(window.event) // IE
	{
		keynum = event.keyCode;
	}
	else if(e.which) // Netscape/Firefox/Opera
	{		
		keynum = e.which;
	}	
	if( e.altKey)
	{
		if(keynum==112 ) //Alt+F1 (Help)
		{
			try
			{
				eval(getFrameToolbar().document.getElementById('helpButtonHref').href);
			}
			catch(e1)
			{
				//this doesn't exist yet in the login frame
			}			
		}
		if(keynum==113 ) //Alt+F2 (Home)
		{
			try
			{
				eval(getFrameToolbar().document.getElementById('homeButtonHref').href);
			}
			catch(e2)
			{
				//this doesn't exist yet in the login frame
			}				
		}	
		if(keynum==114 ) //Alt+F3 (Logout)
		{
			try
			{
				eval(getFrameToolbar().document.getElementById('logoutButtonHref').href);
			}
			catch(e3)
			{
				//this doesn't exist yet in the login frame
			}				
		}		
		if(keynum==71)	// Alt+G set focus to input-frame
		{
			try
			{	
				positionToFirstInputField();			
			}
			catch(e4)
			{
				//this doesn't exist yet in the login frame
			}	
		}	
		if(keynum==72 )	// Alt+H click toolbar show/hide-header
		{
			try
			{
				eval(getFrameToolbar().document.getElementById('sliderAnchor').href);
			}
			catch(e5)
			{
				//this doesn't exist yet in the login frame
			}	
		}
		if(keynum==80)	// Alt+P show UXP Process Search
		{
			if( window.top.equxp)
			{
				window.top.equxp.uxpKey("Alt+P");
			}
		}
		if(keynum==49 && e.shiftKey)	// Alt+Shift+1 display split screen for the Equation service/WebFacing for debugging purposes
		{
			try
			{
				var screenFrameset = getFrameScreen().document.getElementById('screenFrameset');
				screenFrameset.setAttribute("cols","50%,50%");
				getFrameScreen().document.getElementById('eqwfWrapFrameset').rows = "0,*,61";
				hideUXPBusyIndicator();
			}
			catch(e6)
			{
			}	
		}
	}
	
	// toolbar processing for firefox/chrome support
	toolbarKeyPress(e);	
}		

// **********************************************************************************
// function eqInit()
// Initialisation for all frames
//
// Parameters:
// None
// **********************************************************************************	
function eqInit(domain)
{	
	// set the domain
	if (domain && domain != '')
	{
		document.domain=domain;
	}
	
	if (document.addEventListener)
	{
		document.addEventListener('keyup', documentonkeyup, false);
	}
	else
	{
		document.attachEvent('onkeyup', documentonkeyup);
	}

	// Further initialisation
	eqInit_StandAlone();
}


//**********************************************************************************
//function supressBrowserHelp()
//On Help event for each of the document page to suppress the browser help
//
//Parameters:
//None
//**********************************************************************************  
function supressBrowserHelp()
{
	return false;
}


//**********************************************************************************
// function eqInit_writeStyle()
// Writes the correct stylesheet name
//
// Parameters:
// None
//**********************************************************************************  
function eqInit_writeStyle()
{
	if((eqDriver == 'Y'))
	{
		document.write('<link rel="stylesheet" id="stylesheet" href="..' + getCookie("equi.css") + '" type="text/css">');
	}
	else
	{
		if((pgmIdSet != null && !widHeaderFooter)) 
		{
			if(RTL && dspfRTL)
			{
				document.write('<link rel="stylesheet" id="stylesheet" href="..' + getCookie("equi_ar.css") + '" type="text/css">');
			}
			else 
			{ 
				document.write('<link rel="stylesheet" id="stylesheet" href="..' + getCookie("equi.css") + '" type="text/css">');
			}
		}
		else
		{
			if (RTL && dspfRTL) 
			{ 
				document.write('<link rel="stylesheet" id="stylesheet" href="..' + getCookie("eqclassicui_ar.css") + '" type="text/css">');
			}
			else 
			{ 
				document.write('<link rel="stylesheet" id="stylesheet" href="..' + getCookie("eqclassicui.css") + '" type="text/css">');
			}
	 	}
	}
	
	// User style is only for Desktop
	if (!isUXP())
	{
		document.write('<link rel="stylesheet" id="stylesheet" href="..' + getCookie("userui.css") + '" type="text/css">');
	}
}

//**********************************************************************************
//function isInputField(obj)
//Determine whether the object is an input field or not
//
//Parameters:
//obj - object
//**********************************************************************************	
function isInputField(obj)
{
	// read only
	if (obj.readOnly) 
	{
		return false;
	}
	
	// disabled
	if (obj.disabled)
	{
		return false;
	}
	
	// visibility is hidden
	if (obj.style.visibility == 'hidden')
	{
		return false;
	}
	
	// non display - EQ specific non display class
	if (obj.className.indexOf('_NDP')>0) 
	{
		return false;
	}

	// Input field must not be part of the toolbar
	// This will loop thru the parent element and see if within EqButtonBar div
	var parent = obj.parentElement;
	while(parent!=null)
	  {
	  	if(parent.id == 'EqButtonbar')
	  	{
	  		return false;
	  	}
	  	parent = parent.parentElement;
	  }
	
	// Input fields
	if( obj.type=='text' || 
	    obj.type=='button' || 
		obj.type=='checkbox' ||
		obj.type=='radio' )
	{
		return true;
	}
		
		
	return false;
}

// **********************************************************************************
// function positionToFirstInputField()
// Position the focus on the first input field of the main input frame
//
// Parameters:
// None
// **********************************************************************************	
function positionToFirstInputField()
{
	var gblCurrentFrame = getFrameCurrent();
    var objMainFrame = gblCurrentFrame.document;
	var objList      = objMainFrame.getElementsByTagName("input");

	for (var i=0; i<objList.length; i++)
	{
		if (isInputField(objList[i]))
		{
			x = objectFocus(objList[i], false);
			if (x) 
			{
				break;
			}
		}
	}
}

// **********************************************************************************
// function positionToLastInputField()
// Position the focus on the last input field of the main input frame
//
// Parameters:
// None
// **********************************************************************************	
function positionToLastInputField()
{
	var gblCurrentFrame = getFrameCurrent();
    var objMainFrame = gblCurrentFrame.document;
	var objList      = objMainFrame.getElementsByTagName("input");

	for (var i=objList.length-1; i>=0; i--)
	{
		if (isInputField(objList[i]))
		{
			x = objectFocus(objList[i], false);
			if (x)
			{
				break;
			}
		}
	}
}


//**********************************************************************************
//function trapTransactionFunctionKeys(event)
//Trapping of Function Keys
//
//Parameters: 
// event
//**********************************************************************************	
function trapTransactionFunctionKeys(event)
{
    var frame = getFrameCurrent();
    var disablebutton = true;

    // Transaction pending
    if (frame.eqDriver != null && frame.eqDriver != 'Y')
    {
    	var isWF = getFrameWebfacing().location == frame.location;
    	var fstring = '';
  
		// WebFacing
		if(isWF && frame.ms == null)
		{
   			fstring = convertEventCodes(event.keyCode, event.shiftKey, false);
   			
   			// for F1
			if(fstring=='CF01')
			{
	    		frame.dispHelp();
			}
			
			// for PageUp and PageDown
			else if(fstring=='PAGEDOWN' || fstring=='PAGEUP')
			{
				// this searches and triggers WebFacing PageUp/PageDown
				var executed = false;
				var myImages = frame.document.getElementsByTagName("IMG");
				for (var i=0 ;i<myImages.length ;i++ )
				{           
					if(myImages[i].alt!=null && myImages[i].alt.toUpperCase().indexOf(fstring)>=0
							&& myImages[i].parentElement != null && myImages[i].parentElement.onclick != null)
					{
						executed = true;
						startLoadingTransaction();
						myImages[i].click();
						break;
					}
				}
				
				// no images found, then execute pageup/down as per other function key only if pageup/down is valid
				if (!executed && frame.myglobalvars.validCmdKey.indexOf(fstring)>=0)
				{
					startLoadingTransaction();
					frame.validateAndSubmit(fstring, frame);
				}
			}
			
			// Other keys
			else
			{
				startLoadingTransaction();
				frame.validateAndSubmit(fstring, frame);
			}
		}
		
		// HATS
		else if (isWF) 
		{
			executeForDynamicWF(frame);
		}
		
	 	// Equation service
		else
		{
			frame.objCurrentInputField = frame.objLastFocusInputField;
			frame.dt_bodyKeyDown(window.event); 
		}
    }
    
    // No transaction in progress (Driver screen)
    else 
    {
    	// for F12=Developer's tool and Enter, do not disable
        disablebutton = (event.keyCode != 123 && event.keyCode != 13); 
    }
    
    // Disable the button or not?
    if (disablebutton)
    {
    	event.keyCode = 505;
    	disableKeyboardKey(event);
    	return false;
    }
    else
    {
    	return true;
    }
}


//**********************************************************************************
//function executeForDynamicWF(frame)
//Checking of Dynamic Webfacing
//
//Parameters: 
// frame
//**********************************************************************************
function executeForDynamicWF(frame)
{
   	var fstring = convertEventCodes(event.keyCode, event.shiftKey, true);
	try
	{
		startLoadingTransaction();
		frame.ms(fstring, 'SCREEN');
	}
	catch(e)
	{
		//do nothing! at the moment
	}
}


//**********************************************************************************
// function convertEventCodes(kcode, skey, wf)
// Checks what function keys are pressed and verify if dynamic or static webfacing
//
// Parameters: 
// kcode - keyCode
// skey - shiftKey
// dynamicWF - Dynamic Webfacing or Static Webfacing
//**********************************************************************************
function convertEventCodes(kcode, skey, dynamicWF)
{
	// WebFaced functions / Equation Service
	if (dynamicWF==false)
	{
		// Keys regardless of Shift button
		switch(kcode)
		{
			case 13: return "ENTER";
			case 33: return "PAGEUP";
			case 34: return "PAGEDOWN";
		}
		
		// Shift key off
		if (skey==false)
		{
			switch(kcode)
			{
				case 112: return "CF01";
				case 113: return "CF02";
				case 114: return "CF03";
				case 115: return "CF04";
				case 116: return "CF05";
				case 117: return "CF06";
				case 118: return "CF07";
				case 119: return "CF08";
				case 120: return "CF09";
				case 121: return "CF10";
				case 122: return "CF11";
				case 123: return "CF12";
			}
		}
		
		// Shift key on
		else
		{
			switch(kcode)
			{
				case 112: return "CF13";
				case 113: return "CF14";
				case 114: return "CF15";
				case 115: return "CF16";
				case 116: return "CF17";
				case 117: return "CF18";
				case 118: return "CF19";
				case 119: return "CF20";
				case 120: return "CF21";
				case 121: return "CF22";
				case 122: return "CF23";
				case 123: return "CF24";
			}
		}
	}
	
	// HATS (non-converted display files) 
	else 
	{
		// Keys regardless of Shift button
		switch(kcode)
		{
			case 13: return "[enter]";
			case 33: return "[pageup]";
			case 34: return "[pagedn]";
		}
		
		// Shift key off
		if (skey==false)
		{
			switch(kcode)
			{
			case 112: return "[pf1]";
			case 113: return "[pf2]";
			case 114: return "[pf3]";
			case 115: return "[pf4]";
			case 116: return "[pf5]";
			case 117: return "[pf6]";
			case 118: return "[pf7]";
			case 119: return "[pf8]";
			case 120: return "[pf9]";
			case 121: return "[pf10]";
			case 122: return "[pf11]";
			case 123: return "[pf12]";
			}
		}
		
		// Shift key on
		else
		{
			switch(kcode)
			{
			case 112: return "[pf13]";
			case 113: return "[pf14]";
			case 114: return "[pf15]";
			case 115: return "[pf16]";
			case 116: return "[pf17]";
			case 117: return "[pf18]";
			case 118: return "[pf19]";
			case 119: return "[pf20]";
			case 120: return "[pf21]";
			case 121: return "[pf22]";
			case 122: return "[pf23]";
			case 123: return "[pf24]";
			}
		}
	}
}
