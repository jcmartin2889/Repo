
var window_outerWidth = 10;

// being used during RTL processing
var window_outerHeight = 22;
var window_min_width = 258;

var vscrollbar_width = 16;
var hscrollbar_height = 16;

var icon_width = 18;

// **********************************************************************************
// function getScrollXY()
// Retrieve the scrollLeft and scrollTop of the docuement
//
// Parameters:
// None
// 
// Returns:
// An array containing the left and top
// **********************************************************************************	
function getScrollXY()
{
	var scrOfX = 0, scrOfY = 0;
	
	//Netscape compliant
	if( typeof( window.pageYOffset ) == 'number' )
	{
		scrOfY = window.pageYOffset;
		scrOfX = window.pageXOffset;
	} 
	
    //DOM compliant
	else if (document.body && ( document.body.scrollLeft || document.body.scrollTop ) ) 
	{
	    scrOfY = document.body.scrollTop;
	    scrOfX = document.body.scrollLeft;
	} 
	
    //IE6 standards compliant mode
	else if(document.documentElement && ( document.documentElement.scrollLeft || document.documentElement.scrollTop ) ) 
	{
	    scrOfY = document.documentElement.scrollTop;
	    scrOfX = document.documentElement.scrollLeft;
	}

  return [ scrOfX, scrOfY ];
}


// **********************************************************************************
// function getClientWidthHeight()
// Retrieve the width and height of the window
//
// Parameters:
// None
// 
// Returns:
// An array containing the width and height
// **********************************************************************************	
function getClientWidthHeight()
{
	var myWidth = 0, myHeight = 0;
	
    //Non-IE
	if( typeof( window.innerWidth ) == 'number' )
	{
	    myWidth = window.innerWidth;
	    myHeight = window.innerHeight;
	} 
	
    //IE 6+ in 'standards compliant mode'
	else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) 
	{
	    myWidth = document.documentElement.clientWidth;
	    myHeight = document.documentElement.clientHeight;
	} 
	
    //IE 4 compatible
	else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) )
	{
	    myWidth = document.body.clientWidth;
	    myHeight = document.body.clientHeight;
	}
	
  return [ myWidth, myHeight ];
}


// **********************************************************************************
// function getPos(obj)
// Get the absolute position of a field relative to the document
//
// Parameters:
// None
// 
// Returns:
// The left and top position
// **********************************************************************************	
function getPos(obj)
{
	var output = new Object();
	var mytop=0, myleft=0;

	while(obj)
	{
		mytop+= obj.offsetTop;
		myleft+= obj.offsetLeft;
		obj= obj.offsetParent;
	}

	output.left = myleft;
	output.top = mytop;
	return output;
}


// **********************************************************************************
// function adjustLeftTop(width,height)
// Adjust the position of the window
//
// Parameters:
// width - width of the window
// height - height of the window
// **********************************************************************************	
function adjustLeftTop(width,height)
{
	// get the left and top position
	var left = window.screenLeft;
	var top = window.screenTop;
	
	// width more than the width of the screen?
	if (left + width > screen.availWidth)
	{
		left = screen.availWidth - width;
	}
	
	// height more than the height of the screen?
	if (top + height > screen.availHeight)
	{
		top = screen.availHeight - height;
	}
	
	// both left and top
	if (left != window.screenLeft && top != window.screenTop)
	{
		window.moveTo(left,top);
	}
	
	// left only
	if (left != window.screenLeft)
	{
		window.moveBy(left-window.screenLeft,0);
	}
	
	// top only
	if (top != window.screenTop)
	{
		window.moveBy(0,top-window.screenTop);
	}
}

// **********************************************************************************
// function getScrollLeft()
// Get the scroll left of the document
//
// Parameters:
// None
//
// Returns:
// the scroll left
// **********************************************************************************	
function getScrollLeft()
{
	if (window.pageXOffset)
	{
		return window.pageXOffset;
	}
	
	else if (document.documentElement)
	{
		return document.documentElement.scrollLeft;
	}
	
	else
	{
		document.body.scrollLeft;
	}
}


// **********************************************************************************
// function getScrollTop()
// Get the scroll top of the document
//
// Parameters:
// None
//
// Returns:
// the scroll top
// **********************************************************************************	
function getScrollTop()
{
	if (window.pageYOffset)
	{
		return window.pageYOffset;
	}
	
	else if (document.documentElement)
	{
		return document.documentElement.scrollTop;
	}
	
	else
	{
		document.body.scrollTop;
	}
}


// **********************************************************************************
// function getWidth(obj)
// Get the width of an object (first level only)
//
// Parameters:
// Obj
//
// Returns:
// the width
// **********************************************************************************	
function getWidth(obj)
{
	var w = 0;
	for (i=0; i<obj.children.length; i++)
	{
		w += obj.children[i].offsetWidth;
	}
	return w;
}

// **********************************************************************************
// function getWindowLeft()
// Get the left position of the window
//
// Returns:
// the left position
// **********************************************************************************	
function getWindowLeft()
{
	if (document.all)
	{
		return window.screenLeft;
	}
	else
	{
		return window.screenX;
	}
}

// **********************************************************************************
// function getWindowTop()
// Get the top position of the window
//
// Returns:
// the top position
// **********************************************************************************	
function getWindowTop()
{
	if (document.all)
	{
		return window.screenTop;
	}
	else
	{
		return window.screenY;
	}
}


// **********************************************************************************
// function adjustWidthHeight(newWidth, newHeight)
// Set the window to the specified new width and height
//
// Returns:
// the top position
// **********************************************************************************	
function adjustWidthHeight(newWidth, newHeight)
{
	var widthHeight = getClientWidthHeight();
	
	// not specified, then do not change the width
	if (newWidth == null)
	{
		dx = 0;
	}
	else
	{
		dx = newWidth - widthHeight[0]; 
	}
	
	// not specified, then do not change the height
	if (newHeight == null)
	{
		dy = 0;
	}
	else
	{
		dy = newHeight - widthHeight[1];
	}
	
	window.resizeBy(dx, dy);
}


//**********************************************************************************
//function getObjectScrollLeft(obj)
//Get the scroll left of the document
//
//Parameters:
//None
//
//Returns:
//the scroll left
//**********************************************************************************	
function getObjectScrollLeft(obj)
{
	var ol=obj.scrollLeft;
	while ((obj=obj.offsetParent) != null) 
	{
		if (RTL)
		{
			ol += obj.scrollWidth - obj.scrollLeft - obj.offsetWidth;
		}
		else
		{
			ol += obj.scrollLeft;
		}
		
		// consider scrolling of the parent as well in case, parent is not the offsetParent
		if (obj.parentNode != null && obj.offsetParent != null && obj.offsetParent != obj.parentNode)
		{
			ol += obj.parentNode.scrollLeft;
			if (!RTL && ol < 0)
			{
				ol = 0;
			}
		}
		
	}
	return ol;
}


//**********************************************************************************
//function getObjectScrollTop(obj)
//Get the scroll top of the document
//
//Parameters:
//None
//
//Returns:
//the scroll top
//**********************************************************************************	
function getObjectScrollTop(obj)
{
	var ol=obj.scrollTop;
	while ((obj=obj.offsetParent) != null) 
	{
		ol += obj.scrollTop; 
		
		// consider scrolling of the parent as well in case, parent is not the offsetParent
		if (obj.parentNode != null && obj.offsetParent != null && obj.offsetParent != obj.parentNode)
		{
			ol += obj.parentNode.scrollTop;
			if (ol < 0)
			{
				ol = 0;
			}
		}
		
	}
	return ol;
}


//**********************************************************************************
// function getTopWindowLeft()
// Get the left position of the actual browser
//
// Returns:
// the left position
//**********************************************************************************	
function getTopWindowLeft()
{
	if (document.all)
	{
		return window.top.screenLeft;
	}
	else
	{
		return window.top.screenX;
	}
}

//**********************************************************************************
// function getTopWindowTop()
// Get the top position of the actual browser
//
// Returns:
// the top position
//**********************************************************************************	
function getTopWindowTop()
{
	if (document.all)
	{
		return window.top.screenTop;
	}
	else
	{
		return window.top.ascreenY;
	}
}


//**********************************************************************************
// function isBrowserChrome()
// Determine if Chrome browser
//
// Returns:
// true if Chrome browser
//**********************************************************************************	
function isBrowserChrome()
{
	return (navigator.userAgent.indexOf('Chrome') > 0);
}

//**********************************************************************************
// function isBrowserFirefox()
// Determine if Firefox browser
//
// Returns:
// true if Firefox browser
//**********************************************************************************	
function isBrowserFirefox()
{
	return (navigator.userAgent.indexOf('Firefox') > 0);
}

//**********************************************************************************
// function isBrowserIE()
// Determine if IE browser
//
// Returns:
// true if IE browser
//**********************************************************************************	
function isBrowserIE()
{
	return (navigator.appName.indexOf('Internet Explorer') > 0);
}


//**********************************************************************************
// function getMousePosition(e)
// Return the mouse position relative to the page
//
// Returns:
// the mouse coordinates relative to the page
//**********************************************************************************	
function getMousePosition(e)
{
	if (isBrowserIE()) 
	{
		pos = getScrollXY();
		tempX = e.clientX + pos[0];
		tempY = e.clientY + pos[1];
		
		if (RTL)
		{
			tempX = e.clientX - icon_width;
		}
	} 
	else 
	{
		tempX = e.pageX;
		tempY = e.pageY;
	}
	
	return [ tempX, tempY]; 
}


//**********************************************************************************
// function isBrowserIE7()
// Determine if IE browser
//
// Returns:
// true if IE browser 7
//**********************************************************************************	
function isBrowserIE7()
{
	// This isIE7 variable is defined using IE conditional statement.
	// This is currently only set in function.jsp
	//
	// If you need this isBrowserIE7() in other parts of the desktop,
	// you will need to add this conditional statement in that particular
	// jsp
	
	// undefined
	if( typeof isIE7 == 'undefined')
	{
		return false;
	}
	
	// IE7
	if (isIE7 == true)
	{
		return true;
	}
	
	// Not IE7
	return false;
}


