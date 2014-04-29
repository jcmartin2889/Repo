//******************************************************************************
//  Javascript Global Functions Module
// -----------------------------------
//  
//  Copyright and all other intellectual property rights in this software, 
//  in any form, is vested in Misys International Banking Systems Ltd ("Misys") 
//  or a related company.                                  
//                                                     
//  This software may not be copied, amended, compiled, translated, or developed
//  or sold, leased, hired, rented, or disclosed to any third party without the 
//  prior written consent of Misys.                     
//                                                     
//  Copyright © Misys International Banking Systems Ltd                           
// *****************************************************************************


// just default to 0
var jsLogging = 0;
var jsNotification = 0;


/* **********************************************************************************
 * Array.indexOf( value, begin, strict ) - Return index of the first element that matches value
 * **********************************************************************************/

Array.prototype.indexOf = function( v, b, s ) {
 for( var i = +b || 0, l = this.length; i < l; i++ ) 
 {
	 if( this[i]===v || s && this[i]==v )
	 {
		 return i;
	 }
 }
 return -1;
};
	


/* **********************************************************************************
 * REPLACE WHITE SPACE WITH NON BREAKING SPACES
 * **********************************************************************************/

String.prototype.nbsp = function() 
{
	return(this.replace(/ /g,'&nbsp;'));
};


/* **********************************************************************************
 * REPLACE NON BREAKING SPACES WITH WHITE SPACE
 * **********************************************************************************/
String.prototype.rplNbsp = function() 
{
	return(this.replace(/&nbsp;/g,' '));
};


/* **********************************************************************************
 * TRIM STRING
 * **********************************************************************************/
String.prototype.trim = function() 
{
	return(this.replace(/^\s+/,'').replace(/\s+$/,''));
};


/* **********************************************************************************
 * Right trim
 * **********************************************************************************/
String.prototype.trimr = function() 
{
	var str = this;
	var len = str.length - 1;
	if (len <= 0) 
	{
		return str;
	}
	
	// loop until non-blank
	while (str.charAt(len)==' ')
	{
		len--;
		if (len < 0)
		{
			break;
		}
	}
	
	if (len<0)
	{
		str='';
	}
	else 
	{
		str = str.substring(0,len+1);
	}
	
	return(str);
};


/* **********************************************************************************
 * Left trim
 * **********************************************************************************/
String.prototype.triml = function()
{
	var str = this;
	var len = str.length - 1;
	var i   = 0;
	if (len <= 0)
	{
		return str;
	}
	
	// loop until non-blank
	while (str.charAt(i)==' ')
	{
		i++;
		if (i>len)
		{
			break;
		}
	}
	
	if (i>len)
	{
		str=='';
	}
	else
	{
		str = str.substring(i,len+1);
	}
	
	return(str);
};


/* **********************************************************************************
 * Replace dots and colons with spaces
 * **********************************************************************************/
String.prototype.rplDots = function() 
{
	//Get rid of the dots and colons and return
	return (this.replace(/\./g, String.fromCharCode(32))).
			replace(/:/g ,String.fromCharCode(32));
};


/* **********************************************************************************
 * Replace hyphens with colons to stop breaking line for title
 * **********************************************************************************/
String.prototype.rplHyphens = function() 
{
	//Get rid of the hyphens and return
	return (this.replace(/\-/g, String.fromCharCode(8209)));
};


/* **********************************************************************************
 * Replace hex 160s with standard '20' spaces 
 * **********************************************************************************/
String.prototype.rpl160s = function() 
{		
	var c = String.fromCharCode(160);
	var returnvalue = this;
	
	var i = returnvalue.indexOf(c); 
	while (i >= 0)
	{
		returnvalue = returnvalue.replace(c, ' ');
		i = returnvalue.indexOf(c,i);
	}
	
	return returnvalue;
};


/* **********************************************************************************
 * Replace control characters with standard '20' spaces 
 * **********************************************************************************/
String.prototype.rplCtl = function() 
{		
	//return (this.replace( String.fromCharCode(160),String.fromCharCode(20)));	
	var returnvalue = "";
	
	for(var i=0; i <= this.length; i++)
	{		
			if(this.charCodeAt(i)>= 128 && this.charCodeAt(i)<=159)
			{ 
				returnvalue=returnvalue + String.fromCharCode(32);
			}
			else
			{
				returnvalue=returnvalue + this.substring(i,i+1);	
			}		
	}
	return returnvalue;
};


/* **********************************************************************************
 * Replace quotes with special code for quotes
 * **********************************************************************************/
String.prototype.rplQuotes = function() 
{
	return (this.replace(/\"/g, "&quot;"));
};


/* **********************************************************************************
 * Replace quotes with special code for quotes
 * **********************************************************************************/
String.prototype.rplSpaces = function() 
{
	return (this.replace(/ /g, "&nbsp;"));
};


/* **********************************************************************************
 * Replace quotes with \" (used if the string is enclosed withing a bigger quotes)
 * **********************************************************************************/
String.prototype.rplQuotesWithSlashQuotes = function() 
{
	return (this.replace(/\"/g, "&#92;&quot;"));
};


/* **********************************************************************************
 * Replace single quotes with special code for single quotes
 * **********************************************************************************/
String.prototype.rplSingleQuotes = function() 
{
	return (this.replace(/\'/g, "&#39;"));
};


/* **********************************************************************************
 * Replace single quotes with special code for single quotes
 * **********************************************************************************/
String.prototype.rplSingleQuotesWithSlashSingleQuotes = function() 
{
	return (this.replace(/\'/g, "&#92;&#39;"));
};


/* **********************************************************************************
 * Replace quotes with special code for quotes
 * **********************************************************************************/
String.prototype.rplSlash = function() 
{
	//Get rid of the hyphens and return
	return (this.replace(/\\/g, "&#92;&#92;"));
};


/* **********************************************************************************
 * Replace less than sign with special code for less than <
 * **********************************************************************************/
String.prototype.rplLessThan = function() 
{
	//Get rid of the hyphens and return
	return (this.replace(/</g, "&lt;"));
};


/* **********************************************************************************
 * Replace greater than sign with special code for greater than >
 * **********************************************************************************/
String.prototype.rplGreaterThan = function() 
{
	//Get rid of the hyphens and return
	return (this.replace(/>/g, "&gt;"));
};


 /* **********************************************************************************
  * Pad spaces
  * **********************************************************************************/
 String.prototype.pad = function(length) 
 {
	 var temps = this;
	 while (temps.length < length )
	 {
		 temps += " ";
	 }
	 return temps;
 };

 
  /* **********************************************************************************
   * Left pad the string
   * **********************************************************************************/
 String.prototype.leftPad = function(length, char)
  {
  	// already exceed the length, then return the same string
  	if (this.length >= length)
  	{
  		return str;
  	}
  	
  	// add zeroes
  	var newStr = "";
  	for (i=0; i<length-this.length; i++)
  	{
  		newStr = newStr + "0";
  	}

  	// return the padded string
  	return newStr + this;
  };


   /* **********************************************************************************
    * Left pad the string
    * **********************************************************************************/
  String.prototype.removeLeading = function(char)
   {
	  var str = this;
	  var index = 0;
	  while(str.charAt(index)==char)
	  {
		  index++;
		  
		  if (index>=str.length)
		  {
			  return "";
		  }
	  }
	  
	  return str.substring(index);
   };

    /* **********************************************************************************
     * Check if string starts with the specified argument
     * **********************************************************************************/
    String.prototype.startsWith = function(str)
    {
    	return (this.match("^"+str)==str);
    };
    
    /* **********************************************************************************
     * Check if string ends with the specified argument
     * **********************************************************************************/
    String.prototype.endsWith = function(str)
    {
    	return this.indexOf(str, this.length - str.length) !== -1;
    };

    
    /* **********************************************************************************
     * Search for non-blank
     * **********************************************************************************/
    String.prototype.indexOfNonBlank = function(startIndex) 
    {
    	for (var i=startIndex; i<this.length; i++)
    	{
    		if (this.charAt(i) != ' ')
    		{
    			return i;
    		}
    	}
    	return -1;
    };


    /* **********************************************************************************
     * Set the character at the specified position
     * **********************************************************************************/
    String.prototype.setCharAt = function(index, addStr) 
    {
    	// retrieve first part
    	var str = this.substr(0,index) + addStr;
    	
    	// retrieve last part
    	if (index < this.length-1)
    	{
    		str += this.substr(index+1);
    	}
    	
    	return str;
    };


    /* **********************************************************************************
     * Return the start-end position of a region of spaces
     * E.g. "   A  B " will return (0,2)
     * **********************************************************************************/
    String.prototype.searchBlankRegion = function(index) 
    {
    	var obj = new Object();
    	obj.startIndex = -1;
    	obj.endIndex = -1;
    	
		// search for first blank
		obj.startIndex = this.indexOf(' ', index);
		if (obj.startIndex == -1)
		{
			return obj;
		}
		
		// search for last blank
    	for (var i=obj.startIndex + 1; i<this.length; i++)
    	{
    		if (this.charAt(i) == ' ' || this.charAt(i) == TAB)
    		{
    		}
    		else
    		{
    			obj.endIndex = i;
    			break;
    		}
    	}
		
    	// no end blank, then set to the maximum length
		if (obj.endIndex == -1)
		{
			obj.endIndex = this.length-1;
		}
		
		// point to the last blank
		else
		{
			// is this 
			obj.endIndex--;
		}
		
		return obj;
    };

    
// **********************************************************************************
// function appendOption(selectBox,optionValue,optionText)
// APPEND AN OPTION TO A SELECT BOX
// 
// Parameters:
// selectBox: The Select box on which to append the option
// optionValue: The value of the option to append
// optionText: The text of the option to append
// **********************************************************************************
function appendOption(selectBox,optionValue,optionText)
{	
	var newOption = document.createElement('option');
	newOption.text = optionText;
	newOption.value = optionValue;
	try
	{
	    selectBox.add(newOption, null); // standards compliant; doesn't work in IE
	}
	catch(ex) 
	{
	    selectBox.add(newOption); // IE only
	}
}


// **********************************************************************************
// function amendOption(selectBox,optionValue, optionText, newOptionValue)
// Amend and option in a select box
// 
// Parameters:
// selectBox: The Select box containing the option to amend
// optionValue: The value of the existing option to amend
// newOptionText: The new text of the option
// newOptionValue: The new value of the option
// **********************************************************************************
function amendOption(selectBox,optionValue, newOptionText, newOptionValue)
{
	  selectBox.length = 0;

	for( var opt in optionList ) 
	{
		var copt = new Option( opt,optionList[ opt ] );
		if(copt.value == optionValue)
		{
			copt.valie = newOptionValue;
		}
	    selectBox.options[ selectBox.length ] = copt;
	}
}


// **********************************************************************************
// function clearOptions(selectBox)
// Clear all options in a select box
// 
// Parameters:
// selectBox: The Select box 
// **********************************************************************************
function clearOptions(selectBox)
{		
	if (!hasOptions(selectBox)) 
	{ 
		return; 
	}
	
	for (var i=(selectBox.options.length-1); i>=0; i--) 
	{ 
		selectBox.options[i] = null; 
	} 		
	selectBox.selectedIndex = -1; 
} 	
	

// **********************************************************************************
// function hasOptions(obj)
// CHECK IF A SELECT BOX HAS ANY OPTIONS
// **********************************************************************************
function hasOptions(obj) 
{
	if (obj!=null && obj.options!=null) 
	{
		return true; 
	}
	return false;
}
	
// **********************************************************************************
// getAnchorPosition(anchorname)
// GET ANCHOR POSITION
// Returns an Object() having .x and .y properties of the pixel coordinates
// of the upper-left corner of the anchor. Position is relative to the PAGE.
// This function returns an object having .x and .y properties which are the coordinates
// of the named anchor, relative to the page.
//
// Parameters:
// anchorname: the name of the anchor for which the position needs to be retrieved
// **********************************************************************************	
function getAnchorPosition(anchorname, targetDocument) 
{
	if(targetDocument==null){targetDocument=document;}
	// This function will return an Object with x and y properties
	var useWindow=false;
	var coordinates=new Object();
	var x=0,y=0;

	// Browser capability sniffing
	var use_gebi=false, use_css=false, use_layers=false;
	if (targetDocument.getElementById) 
	{ 
		use_gebi=true; 
	}
	else if (targetDocument.all)
	{
		use_css=true; 
	}
	else if (targetDocument.layers) 
	{
		use_layers=true; 
	}
	// Logic to find position
	if (use_gebi && targetDocument.all) 
	{
		x=AnchorPosition_getPageOffsetLeft(targetDocument.all[anchorname]);
		y=AnchorPosition_getPageOffsetTop(targetDocument.all[anchorname]);
	}
	else if (use_gebi) 
	{
		var o=targetDocument.getElementById(anchorname);
		x=AnchorPosition_getPageOffsetLeft(o);
		y=AnchorPosition_getPageOffsetTop(o);
	}
	else if (use_css) 
	{
		x=AnchorPosition_getPageOffsetLeft(targetDocument.all[anchorname]);
		y=AnchorPosition_getPageOffsetTop(targetDocument.all[anchorname]);
	}
	else if (use_layers) 
	{
		var found=0;
		for (var i=0; i<targetDocument.anchors.length; i++) 
		{
			if (targetDocument.anchors[i].name==anchorname) 
			{ 
				found=1; break; 
			}
		}
		if (found==0) 
		{
			coordinates.x=0; coordinates.y=0; return coordinates;
		}
		x=targetDocument.anchors[i].x;
		y=targetDocument.anchors[i].y;
	}
	else 
	{
		coordinates.x=0; coordinates.y=0; return coordinates;
	}
	coordinates.x=x;
	coordinates.y=y;
	return coordinates;
}
	
// **********************************************************************************
// getAnchorWindowPosition(anchorname)
// GET ANCHOR WINDOW POSITION
//  Returns an Object() having .x and .y properties of the pixel coordinates
//  of the upper-left corner of the anchor, relative to the WHOLE SCREEN.
//  This function returns an object having .x and .y properties which are the 
//  coordinates of the named anchor, relative to the window
//	
// Parameters:
// anchorname: the name of the anchor for which the window position needs to 
//			   be retrieved
// **********************************************************************************			
function getAnchorWindowPosition(anchorname, targetDocument) 
{
	if(targetDocument==null){targetDocument=document;}
	var coordinates=getAnchorPosition(anchorname, targetDocument);
	var x=0;
	var y=0;
	if (targetDocument.getElementById) 
	{
		if (isNaN(window.screenX)) 
		{
			x=coordinates.x-targetDocument.body.scrollLeft+window.screenLeft;
			y=coordinates.y-targetDocument.body.scrollTop+window.screenTop;
		}
		else 
		{
			x=coordinates.x+window.screenX+(window.outerWidth-window.innerWidth)-window.pageXOffset;
			y=coordinates.y+window.screenY+(window.outerHeight-24-window.innerHeight)-window.pageYOffset;
		}
	}
	else if (targetDocument.all) 
	{
		x=coordinates.x-targetDocument.body.scrollLeft+window.screenLeft;
		y=coordinates.y-targetDocument.body.scrollTop+window.screenTop;
	}
	else if (targetDocument.layers) 
	{
		x=coordinates.x+window.screenX+(window.outerWidth-window.innerWidth)-window.pageXOffset;
		y=coordinates.y+window.screenY+(window.outerHeight-24-window.innerHeight)-window.pageYOffset;
	}
	coordinates.x=x;
	coordinates.y=y;
	return coordinates;
}

// **********************************************************************************
// function AnchorPosition_getPageOffsetLeft(el)
// Function for IE to get the left page offset
//
// Parameters
// el: The element from which to get the left offset
// **********************************************************************************	
function AnchorPosition_getPageOffsetLeft(el) 
{
	var ol=el.offsetLeft;
	while ((el=el.offsetParent) != null) 
	{
		ol += el.offsetLeft; 
	}
	return ol;
}

// **********************************************************************************
// function AnchorPosition_getWindowOffsetLeft (el) 
// Function for IE to get the left window offset
//
// Parameters
// el: The element from which to get the window left offset
// **********************************************************************************	
function AnchorPosition_getWindowOffsetLeft (el, targetDocument)
{
	if(targetDocument==null){targetDocument=document;}
	return AnchorPosition_getPageOffsetLeft(el)-targetDocument.body.scrollLeft;
}	
	
// **********************************************************************************
// function AnchorPosition_getPageOffsetTop (el) 
// Function for IE to get the top page offset
//
// Parameters
// el: The element from which to get the top offset
// **********************************************************************************	
function AnchorPosition_getPageOffsetTop (el) 
{
	var ot=el.offsetTop;
	while((el=el.offsetParent) != null) 
	{
		ot += el.offsetTop; 
	}
	return ot;
}
	
// **********************************************************************************
// function AnchorPosition_getWindowOffsetTop (el) 
// Function for IE to get the top window offset
//
// Parameters
// el: The element from which to get the window top offset
// **********************************************************************************	
function AnchorPosition_getWindowOffsetTop (el,targetDocument) 
{
	if(targetDocument==null){targetDocument=document;}
	return AnchorPosition_getPageOffsetTop(el)-targetDocument.body.scrollTop;
}


// **********************************************************************************
// function PopupWindow_getXYPosition(anchorname)
// Get the position of the popup window based on the anchor
//
// Parameters
// anchorname: the name of the anchor for which the XY position needs to 
//			   be retrieved
// **********************************************************************************	
function PopupWindow_getXYPosition(anchorname, targetDocument) 
{
	var coordinates;
	if (this.type == "WINDOW") 
	{
		coordinates = getAnchorWindowPosition(anchorname, targetDocument);
	}
	else 
	{
		coordinates = getAnchorPosition(anchorname, targetDocument);
	}
	this.x = coordinates.x;
	this.y = coordinates.y;
	
	// cater if anchor has also been scrolled
	if (RTL)
	{
		var obj = document.getElementById(anchorname);
		this.x = this.x + getObjectScrollLeft(obj);
		this.y = this.y - getObjectScrollTop(obj);
	}
	else
	{
		var obj = document.getElementById(anchorname);
		this.x = this.x - getObjectScrollLeft(obj);
		this.y = this.y - getObjectScrollTop(obj);
	}
}

// **********************************************************************************
// function PopupWindow_setSize(width,height)
// Set width/height of DIV/popup window
//
// Parameters
// width: The width to set
// height: The height to set
// **********************************************************************************		
function PopupWindow_setSize(width,height) 
{
	this.width = width;
	this.height = height;
}

// **********************************************************************************
// function PopupWindow_populate(contents) 
// Fill the window with contents
//
// Parameters
// contents: The contents to populate
// **********************************************************************************		
function PopupWindow_populate(contents) 
{
	this.contents = contents;
	this.populated = false;
}

// **********************************************************************************
// function PopupWindow_setUrl(url) 
// Set the URL to go to
//
// Parameters
// url: The URL to set
// **********************************************************************************		
function PopupWindow_setUrl(url) 
{
	this.url = url;
}

// **********************************************************************************
// function PopupWindow_setWindowProperties(props)
// Set the window popup properties
//
// Parameters
// props: The window properties to set
// **********************************************************************************		
function PopupWindow_setWindowProperties(props) 
{
	this.windowProperties = props;
}

// **********************************************************************************
// function PopupWindow_refresh() 
// Refresh the displayed contents of the popup
//
// Parameters: None
// **********************************************************************************		
function PopupWindow_refresh(targetDocument) 
{
	if(targetDocument==null){targetDocument=document;}
	if (this.divName != null) 
	{
		// refresh the DIV object
		if (this.use_gebi) 
		{
			targetDocument.getElementById(this.divName).innerHTML = this.contents;
		}
		else if (this.use_css) 
		{ 
			targetDocument.all[this.divName].innerHTML = this.contents;
		}
		else if (this.use_layers) 
		{ 
			var d = targetDocument.layers[this.divName]; 
			d.document.open();
			d.document.writeln(this.contents);
			d.document.close();
		}
	}		
	else 
	{
		if (this.popupWindow != null && !this.popupWindow.closed) 
		{
			if (this.url!="") 
			{
				this.popupWindow.location.href=this.url;
			}
			else 
			{
				this.popupWindow.document.open();
				this.popupWindow.document.writeln(this.contents);
				this.popupWindow.document.close();
			}
			this.popupWindow.focus();
		}
	}
}


//**********************************************************************************
// function PopupWindow_showPopupAsWindow(x,y)
// Show the Window popup	
//
// Parameters
// None
//**********************************************************************************	
function PopupWindow_showPopupAsWindow()
{
	if (this.popupWindow == null || this.popupWindow.closed) 
	{
		// If the popup window will go off-screen, move it so it doesn't
		if (this.x < 0) 
		{
			this.x = 0; 
		}
		
		if (this.y < 0) 
		{ 
			this.y = 0; 
		}
		
		if (screen && screen.availHeight) 
		{
			if ((this.y + this.height) > screen.availHeight) 
			{
				this.y = screen.availHeight - this.height;
			}
		}
		if (screen && screen.availWidth) 
		{
			if ((this.x + this.width) > screen.availWidth) 
			{
				this.x = screen.availWidth - this.width;
			}
		}
		var avoidAboutBlank = window.opera || ( targetDocument.layers && !navigator.mimeTypes['*'] ) || navigator.vendor == 'KDE' || ( targetDocument.childNodes && !targetDocument.all && !navigator.taintEnabled );
		this.popupWindow = window.open(avoidAboutBlank?"":"about:blank","window_"+anchorname,this.windowProperties+",width="+this.width+",height="+this.height+",screenX="+this.x+",left="+this.x+",screenY="+this.y+",top="+this.y+"");
		this.visible = true;
	}
	
	this.refresh();
}


//**********************************************************************************
// function PopupWindow_setPositionOfDivPopup(x,y)
// Show the DIV popup	
//
// Parameters
// x - left position
// y - top position
// targetDocument - document
//**********************************************************************************	
function PopupWindow_setPositionOfDivPopup(x, y, targetDocument) 
{
	// Set position
	this.x = x;
	this.y = y;
	
	// Show the DIV object
	if (this.use_gebi) 
	{
		targetDocument.getElementById(this.divName).style.left = x + "px";
		targetDocument.getElementById(this.divName).style.top = y + "px";
		targetDocument.getElementById(this.divName).style.visibility = "visible";
	}
	else if (this.use_css) 
	{
		targetDocument.all[this.divName].style.left = x;
		targetDocument.all[this.divName].style.top = y;
		targetDocument.all[this.divName].style.visibility = "visible";
	}
	else if (this.use_layers) 
	{
		targetDocument.layers[this.divName].left = x;
		targetDocument.layers[this.divName].top = y;
		targetDocument.layers[this.divName].visibility = "visible";
	}
	
	this.visible = true;
}


//**********************************************************************************
// function PopupWindow_setDisplayOnOff(targetDocument, display)
// Display/undisplay popup	
//
// Parameters
// targetDocument - document
// display - '' to display, 'none' to hide
//**********************************************************************************	
function PopupWindow_setDisplayOnOff(targetDocument, display) 
{
	// Show the DIV object
	if (this.use_gebi) 
	{
		targetDocument.getElementById(this.divName).style.display = display;
	}
	else if (this.use_css) 
	{
		targetDocument.all[this.divName].style.display = display;
	}
	else if (this.use_layers) 
	{
		targetDocument.layers[this.divName].display = display;
	}
}




// **********************************************************************************
// function PopupWindow_showPopup(anchorname)
// Position and show the popup, relative to an anchor object	
//
// Parameters
// anchorname: The anchor for the popup
// **********************************************************************************	
function PopupWindow_showPopup(anchorname, targetDocument) 
{
	if(targetDocument==null){targetDocument=document;}
	
	// Get X,Y position
	this.getXYPosition(anchorname, targetDocument);
	this.x += this.offsetX;
	this.y += this.offsetY;
	
	if (!this.populated && (this.contents != "")) 
	{
		this.populated = true;		
		this.refresh(targetDocument);
	}
	
	// Popup as Div
	if (this.divName != null) 
	{
		// Undisplay the popup
		this.setDisplayOnOff(targetDocument, "");
	
		var myx = this.x;
		var myy = this.y;
		
		// get the width and height
		if (this.use_gebi) 
		{
			width = targetDocument.getElementById(this.divName).offsetWidth;
			height = targetDocument.getElementById(this.divName).offsetHeight;
		}
		else if (this.use_css) 
		{
			width = targetDocument.all[this.divName].offsetWidth;
			height = targetDocument.all[this.divName].offsetHeight;
		}
		else if (this.use_layers) 
		{
			width = targetDocument.layers[this.divName].offsetWidth;
			height = targetDocument.layers[this.divName].offsetHeight;
		}
	
		// RTL - then adjust the left position by the width of the div
		if (RTL)
		{
			if (this.use_gebi) 
			{
				myx = myx - width;
			}
			else if (this.use_css) 
			{
				myx = myx - width;
			}
			else if (this.use_layers) 
			{
				myx = myx - width;
			}
			
			myx = myx - 25;  // less by the size of the icon (16 x 16)
		}
	
		// is the popup going out of the current page?
		clientWidthHeight = getClientWidthHeight();
		scrollLeftTop = getScrollXY();
		if (myx + width  - scrollLeftTop[0] > clientWidthHeight[0])
		{
			myx = clientWidthHeight[0] - width + scrollLeftTop[0];
			if (myx<scrollLeftTop[0])
			{
				myx=scrollLeftTop[0];
			}
		}
		if (myy + height - scrollLeftTop[1] > clientWidthHeight[1])
		{
			myy = clientWidthHeight[1] - height + scrollLeftTop[1];
			if (myy<scrollLeftTop[1]) 
			{
				myy=scrollLeftTop[1];
			}
		}
		
		// Set the position
		this.setPositionOfDivPopup(myx, myy, targetDocument);
	}
	
	// Popup as Window
	else 
	{
		this.showPopupAsWindow();
	}
}


//**********************************************************************************
// function PopupWindow_showPopupBelow(anchorname)
// Position and show the popup below the anchor	
//
// Parameters
// anchorname: The anchor for the popup
//**********************************************************************************	
function PopupWindow_showPopupBelow(anchorname, targetDocument) 
{
	if(targetDocument==null){targetDocument=document;}

	// Get X,Y position
	this.getXYPosition(anchorname, targetDocument);

	// Adjust by the height of the anchor
	var obj = document.getElementById(anchorname);
	this.y += obj.offsetHeight;
	
	// UXP and RTL, then adjust by the size of the anchor
	if (isUXP() && RTL)
	{
		this.x = this.x + obj.offsetWidth;
	}
	
	this.showPopupXY(this.x, this.y, targetDocument);
}



// **********************************************************************************
// function PopupWindow_showPopupXY(x,y,targetDocument)
// Position and show the popup at X, Y position (taking in consideration RTL)
//
// Parameters
// anchorname: The anchor for the popup
// **********************************************************************************	
function PopupWindow_showPopupXY(x,y,targetDocument) 
{
	if(targetDocument==null){targetDocument=document;}

	// Position
	this.x = x +  this.offsetX;
	this.y = y + this.offsetY;

	if (!this.populated && (this.contents != "")) 
	{
		this.populated = true;		
		this.refresh(targetDocument);
	}
	
	// Popup as Div
	if (this.divName != null) 
	{
		// Undisplay the popup
		this.setDisplayOnOff(targetDocument, "");
		
		var myx = x;
		var myy = y;

		// RTL - then adjust the left position by the width of the div
		if (RTL)
		{
			if (this.use_gebi) 
			{
				myx = myx - targetDocument.getElementById(this.divName).offsetWidth;
			}
			else if (this.use_css) 
			{
				myx = myx - targetDocument.all[this.divName].offsetWidth;
			}
			else if (this.use_layers) 
			{
				myx = myx - targetDocument.layers[this.divName].offsetWidth;
			}
			
			myx = myx - this.width;
			if (myx < 0)
			{
				myx=0;
			}
		}
	
		// Set the position
		this.setPositionOfDivPopup(myx, myy, targetDocument);
	}
	
	// Popup as Window
	else 
	{
		this.showPopupAsWindow();
	}
}


//**********************************************************************************
// function PopupWindow_showPopupAbsoluteXY(x,y,targetDocument)
// Position and show the popup at X, Y position
//
// Parameters
// anchorname: The anchor for the popup
//**********************************************************************************	
function PopupWindow_showPopupAbsoluteXY(x,y,targetDocument) 
{
	if(targetDocument==null){targetDocument=document;}
	
	// Position
	this.x = x +  this.offsetX;
	this.y = y + this.offsetY;
	
	if (!this.populated && (this.contents != "")) 
	{
		this.populated = true;		
		this.refresh(targetDocument);
	}

	// Popup as Div
	if (this.divName != null) 
	{
		// Undisplay the popup
		this.setDisplayOnOff(targetDocument, "");
		
		// Set the position
		this.setPositionOfDivPopup(x, y, targetDocument);
	}
	
	// Popup as Window
	else 
	{
		this.showPopupAsWindow();
	}
}


// **********************************************************************************
// function PopupWindow_hidePopup()
// Hide the popup
//
// Parameters: None
// **********************************************************************************	
function PopupWindow_hidePopup(targetDocument) 
{
	if(targetDocument==null){targetDocument=document;}
	if (this.divName != null) 
	{
		this.setDisplayOnOff(targetDocument,"none");
		if (this.use_gebi) 
		{
			targetDocument.getElementById(this.divName).style.visibility = "hidden";
		}
		else if (this.use_css) 
		{
			targetDocument.all[this.divName].style.visibility = "hidden";
		}
		else if (this.use_layers) 
		{
			targetDocument.layers[this.divName].visibility = "hidden";
		}
	}
	else 
	{
		if (this.popupWindow && !this.popupWindow.closed) 
		{
			this.popupWindow.close();
			this.popupWindow = null;
		}
	}
	
	this.visible = false;
}


// **********************************************************************************
// function PopupWindow_isClicked(e)
// Pass an event and return whether or not it was the popup DIV that was clicked
//
// Parameters: 
// e: Event 
// **********************************************************************************	
function PopupWindow_isClicked(e, targetDocument) 
{
	if(targetDocument==null){targetDocument=document;}
	if (this.divName != null) 
	{
		if (this.use_layers) 
		{
			var clickX = e.pageX;
			var clickY = e.pageY;
			var t = targetDocument.layers[this.divName];
			if ((clickX > t.left) && (clickX < t.left+t.clip.width) && (clickY > t.top) && (clickY < t.top+t.clip.height)) 
			{
				return true;
			}
			else 
			{
			 return false; 
			}
		}
		else if (targetDocument.all) 
		{ 
		// Need to hard-code this to trap IE for error-handling
			var t = window.event.srcElement;
			while (t.parentElement != null) 
			{
				if (t.id==this.divName) 
				{
					return true;
				}
				t = t.parentElement;
			}
			return false;
		}
		else if (this.use_gebi && e) 
		{
			var t = e.originalTarget;
			try{
			while (t.parentNode != null) 
			{
				if (t.id==this.divName) 
				{
					return true;
				}
				t = t.parentNode;
			}
			}
			catch(divParentNodeException){} // Fix for Firefox bug
			return false;
		}
		return false;
	}
	return false;
}


// **********************************************************************************
// function PopupWindow_hideIfNotClicked(e) 
// Check an onMouseDown event to see if we should hide
//
// Parameters: 
// e: Event
// **********************************************************************************	
function PopupWindow_hideIfNotClicked(e) 
{
	if (this.autoHideEnabled && !this.isClicked(e)) 
	{				
		this.hidePopup();
	}
}


// **********************************************************************************
// function PopupWindow_autoHide()
// Call this to make the DIV disable automatically when mouse is clicked outside it
//
// Parameters: None
// **********************************************************************************	
function PopupWindow_autoHide() 
{		
	this.autoHideEnabled = true;
}


// *********************************************************************************************
// function PopupWindow_hidePopupWindows(e)
// This global function checks all PopupWindow objects onmouseup to see if they should be hidden
//
// Parameters: 
// e: Event
// *********************************************************************************************	
function PopupWindow_hidePopupWindows(e) 
{
	for (var i=0; i<popupWindowObjects.length; i++) 
	{	
		if (popupWindowObjects[i] != null) 
		{	
			var p = popupWindowObjects[i];	
			p.hideIfNotClicked(e);	
		}
	}
}


// **********************************************************************************
// function PopupWindow_attachListener()
// ATTACH AN EVENT LISTENER TO A POPUP WINDOW
// Run this immediately to attach the event listener
//
// Parameters: None
// **********************************************************************************	
function PopupWindow_attachListener(targetDocument) 
{
	if(targetDocument==null){targetDocument=document;}
	if (targetDocument.layers) 
	{
		targetDocument.captureEvents(Event.MOUSEUP);
	}
	window.popupWindowOldEventListener = targetDocument.onmouseup;
	if (window.popupWindowOldEventListener != null) 
	{
		targetDocument.onmouseup = new Function("window.popupWindowOldEventListener(); PopupWindow_hidePopupWindows();");
	}
	else 
	{
		targetDocument.onmouseup = PopupWindow_hidePopupWindows;
	}
}


// **********************************************************************************
// function PopupWindow() 
// CONSTRUCTOR for the PopupWindow object
// Pass it a DIV name to use a DHTML popup, otherwise will default to window popup
//
// Parameters: None
// **********************************************************************************	
function PopupWindow(isADiv, targetDocument) 
{
	if(targetDocument==null){targetDocument=document;}
	if (!window.popupWindowIndex) 
	{ 
		window.popupWindowIndex = 0; 
	}
	if (!window.popupWindowObjects) 
	{ 
		window.popupWindowObjects = new Array(); 
	}
	if (!window.listenerAttached) 
	{
		window.listenerAttached = true;
		PopupWindow_attachListener(targetDocument);
	}
	this.index = popupWindowIndex++;
	popupWindowObjects[this.index] = this;
	this.divName = null;
	this.popupWindow = null;
	this.width=0;
	this.height=0;
	this.populated = false;
	this.visible = false;
	this.autoHideEnabled = false;		
	this.contents = "";
	this.url="";
	this.windowProperties="toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable,alwaysRaised,dependent,titlebar=no";

	if (arguments.length>0) 
	{
		this.type="DIV";
		this.divName = arguments[0];
	}
	else 
	{
		this.type="WINDOW";
	}
	this.use_gebi = false;
	this.use_css = false;
	this.use_layers = false;

	if (targetDocument.getElementById) 
	{
		this.use_gebi = true; 
	}
	else if (targetDocument.all) 
	{
		this.use_css = true; 
	}
	else if (targetDocument.layers) 
	{ 
		this.use_layers = true; 
	}
	else 
	{ 
		this.type = "WINDOW"; 
	}

	this.offsetX = 0;
	this.offsetY = 0;

	// Method mappings
	this.getXYPosition = PopupWindow_getXYPosition;
	this.populate = PopupWindow_populate;
	this.setUrl = PopupWindow_setUrl;
	this.setWindowProperties = PopupWindow_setWindowProperties;
	this.refresh = PopupWindow_refresh;
	this.showPopup = PopupWindow_showPopup;
	this.showPopupXY = PopupWindow_showPopupXY;
	this.showPopupAbsoluteXY = PopupWindow_showPopupAbsoluteXY;
	this.showPopupBelow = PopupWindow_showPopupBelow;
	this.showPopupAsWindow = PopupWindow_showPopupAsWindow;
	this.hidePopup = PopupWindow_hidePopup;
	this.setSize = PopupWindow_setSize;
	this.isClicked = PopupWindow_isClicked;
	this.autoHide = PopupWindow_autoHide;
	this.hideIfNotClicked = PopupWindow_hideIfNotClicked;
	this.setPositionOfDivPopup = PopupWindow_setPositionOfDivPopup;
	this.setDisplayOnOff = PopupWindow_setDisplayOnOff;
}


// **********************************************************************************
// function errorAlert(messageSev, messageText)
// Displays an error message
//
// Parameters: 
// messageSev : Message severity
// messageText: The text to be displayed in the error message
// **********************************************************************************
function errorAlert(messageSev,messageText)
{	
	addMsgCtl(messageSev,messageText);
}


// **********************************************************************************
// Switch a divvie on and off
// **********************************************************************************
function switchMenu(shutterId, serviceMethod, elementLoc)
{
	var gblNavigatorViewFrame = getFrameNavigatorView();
	var menuDIVS = new Array("AccountMDiv","RecentDiv","PrimeMenuDiv","AllMenuDiv","SystemMenuDiv","SearchDiv","OutputDiv","UnitSpoolDiv","WebFormDiv","WorkLoadDiv","AnySpoolDiv");
	var navigationDocument = gblNavigatorViewFrame.document;
	var shutter = navigationDocument.getElementById(shutterId);
	var shutterSwitchImg;
	var shutterSwitch;
	var shutterHeader;
	var shutterCollapse;
	var shutterExpand;
	var shutterPrevious;
	var shutterNext;
	var shutterRefresh;
	var shutterUnitStatus;
	try
	{
		shutterUnitStatus = navigationDocument.getElementById(shutterId + 'UnitStatus');
	}
	catch(ex){}
	
	try
	{
		shutterSwitch = navigationDocument.getElementById(shutterId + 'Switch');
	}
	catch(ex){}
	try
	{
		shutterSwitchImg = navigationDocument.getElementById(shutterId + 'SwitchImg');
	}
	catch(ex){}
	try
	{
		shutterHeader = navigationDocument.getElementById(shutterId + 'Header');
	}
	catch(ex){}
	try
	{
		shutterCollapse = navigationDocument.getElementById(shutterId + 'Collapse');
	}
	catch(ex){}
	
	try
	{
		shutterExpand = navigationDocument.getElementById(shutterId + 'Expand');
	}
	catch(ex){}
	try
	{
		shutterPrevious = navigationDocument.getElementById(shutterId + 'Previous');
	}
	catch(ex){}
	try
	{
		shutterNext = navigationDocument.getElementById(shutterId + 'Next');
	}
	catch(ex){}
	try
	{
		shutterRefresh = navigationDocument.getElementById(shutterId + 'Refresh');
	}
	catch(ex){}

	if ( shutter.style.display != "none" )
	{
		if(navigationDocument == document)
		{
			shutter.style.display = 'none';
			if (shutterSwitch != null)
			{
				shutterSwitch.className="button";
			}
			if (shutterSwitchImg != null)
			{
				shutterSwitchImg.src="../images/down.gif";
				shutterSwitchImg.setAttribute("title", getLanguageLabel('GBL000021'));
			}
			if (shutterHeader != null)
			{
				shutterHeader.className="navigationbar";
			}
			if (shutterCollapse != null)
			{
				shutterCollapse.style.display = 'none';
			}			
			if (shutterUnitStatus != null)
			{
				shutterUnitStatus.style.display = 'none';
			}
			if (shutterExpand != null)
			{
				shutterExpand.style.display = 'none';
			}
			if (shutterPrevious != null)
			{
				shutterPrevious.style.display = 'none';
			}
			if (shutterNext != null)
			{
				shutterNext.style.display = 'none';
			}
			if (shutterRefresh != null)
			{
				shutterRefresh.style.display = 'none';
			}
		}
	}
	else 
	{
		var position = menuDIVS.indexOf(shutterId);		
		for(x=0;x<menuDIVS.length;x++)
		{
			if(x==position)
			{
				shutter.style.display = '';
				if (shutterSwitch != null)
				{
					shutterSwitch.className="button";
				}
				if (shutterSwitchImg != null)
				{
					shutterSwitchImg.src="../images/up.gif";
					shutterSwitchImg.setAttribute("title", getLanguageLabel('GBL000020'));
				}
				if (shutterHeader != null)
				{
					shutterHeader.className="navigationbarSelected";
				}
				if (shutterCollapse != null)
				{
					shutterCollapse.style.display = '';
				}				
				if (shutterUnitStatus != null)
				{
					shutterUnitStatus.style.display = '';
				}				
				if (shutterExpand != null)
				{
					shutterExpand.style.display = '';
				}
				if (shutterPrevious != null)
				{
					shutterPrevious.style.display = '';
				}
				if (shutterNext != null)
				{
					shutterNext.style.display = '';
				}
				if (shutterRefresh != null)
				{
					shutterRefresh.style.display = '';
				}
			}
			else
			{
				try
				{
					navigationDocument.getElementById(menuDIVS[x]).style.display='none';
					if (navigationDocument.getElementById(menuDIVS[x] + 'Switch') != null)
					{
						navigationDocument.getElementById(menuDIVS[x] + 'Switch').className="button";
					}
					if (navigationDocument.getElementById(menuDIVS[x] + 'SwitchImg') != null)
					{
						navigationDocument.getElementById(menuDIVS[x] + 'SwitchImg').src="../images/down.gif";
						navigationDocument.getElementById(menuDIVS[x] + 'SwitchImg').setAttribute("title", getLanguageLabel('GBL000021'));
					}
					if (navigationDocument.getElementById(menuDIVS[x] + 'Header') != null)
					{
						navigationDocument.getElementById(menuDIVS[x] + 'Header').className="navigationbar";
					}
					if (navigationDocument.getElementById(menuDIVS[x] + 'Collapse') != null)         
					{
						navigationDocument.getElementById(menuDIVS[x] + 'Collapse').style.display="none";
					}					
					if (navigationDocument.getElementById(menuDIVS[x] + 'UnitStatus') != null)
					{
						navigationDocument.getElementById(menuDIVS[x] + 'UnitStatus').style.display="none";
					}					
					if (navigationDocument.getElementById(menuDIVS[x] + 'Expand') != null)
					{
						navigationDocument.getElementById(menuDIVS[x] + 'Expand').style.display="none";
					}
					if (navigationDocument.getElementById(menuDIVS[x] + 'Previous') != null)
					{
						navigationDocument.getElementById(menuDIVS[x] + 'Previous').style.display="none";
					}
					if (navigationDocument.getElementById(menuDIVS[x] + 'Next') != null)
					{
						navigationDocument.getElementById(menuDIVS[x] + 'Next').style.display="none";
					}
					if (navigationDocument.getElementById(menuDIVS[x] + 'Refresh') != null)
					{
						navigationDocument.getElementById(menuDIVS[x] + 'Refresh').style.display="none";
					}
				}
				catch (e) {}
			}
		}		
	}
	
	var obj = document.getElementById(shutterId + 'Anchor');
	if (obj!=null)
	{
		obj.blur();
		
		try
		{
			obj.focus();
		}
		catch(e)
		{
		}
	}
	
	// Is this shutter opening?
	if (serviceMethod!=null && shutter.style.display != "none")
	{
		navigationDocument.getElementById("navi").className="naviExpanded";
		var obj = document.getElementById(elementLoc);
		if (obj.innerHTML.trim() == '')
		{
			javascript:getNaviInfoFromServDir(serviceMethod,shutterId,elementLoc);
		}
	}
	else
	{
		navigationDocument.getElementById("navi").className="navi";
	}
	
	// resize it
	navi_resize();
}

// **********************************************************************************
// function addWidgetButton()
// Slap out some html for a widget button
// **********************************************************************************
function addWidgetButton(hoverText,labelText,onClickAction,imagePath,classNames)
{
	// slap it out
	var widgetButtonHTML = new String();
	if( isUXP() && classNames )
	{
		widgetButtonHTML = '<a id="' + labelText + 'Href"' + ' class="' + classNames + '" alt="' + hoverText + '" title="' + hoverText + '" tabIndex="-1" href="javaScript:' + onClickAction + '" name="' + labelText + 'link" id="' + labelText + 'link"></a>';
	}
	else
	{
		widgetButtonHTML =	'<a id="' + labelText + 'Href"' + ' tabindex="-1" class="widgetButton" href="javaScript:' + onClickAction + '" name="' + labelText + 'link" id="' + labelText + 'link">' +
							'<img src="' + imagePath + '" alt="' + hoverText + '" title="' + hoverText + '" id="' + labelText + '" border="0">' +
						'</a>';
	}
	
	// this controls whether to perform document write or not, as during page up or down
	// of repeating data, it needs to process the script but should never issue document.write
	if (defaultButtonProc)
	{
		document.write(widgetButtonHTML);
	}
	return widgetButtonHTML;
}

//**********************************************************************************
//function addWidgetButtonOnClick()
//Slap out some html for a widget button
//**********************************************************************************
function addWidgetButtonOnClick(hoverText,labelText,onClickAction,imagePath,classNames)
{
	// slap it out
	var widgetButtonHTML = new String();
	if( isUXP() && classNames )
	{
		widgetButtonHTML =	'<a id="' + labelText + 'Href"' + ' tabindex="-1" class="' + classNames + '" alt="' + hoverText + '" title="' + hoverText + '" id="' + labelText + '" onclick="javaScript:' + onClickAction + '" hrefclick="javaScript:' + onClickAction + '" href="javaScript:' + "doNothing()" + '" name="' + labelText + 'link" id="' + labelText + 'link">'
						+ '<img id="' + labelText + '" class="wf_NDP">'
						+ '</a>';
	}
	else
	{
		widgetButtonHTML =	'<a id="' + labelText + 'Href"' + ' tabindex="-1" class="widgetButton" onclick="javaScript:' + onClickAction + '" hrefclick="javaScript:' + onClickAction + '" href="javaScript:' + "doNothing()" + '" name="' + labelText + 'link" id="' + labelText + 'link">' +
							'<img src="' + imagePath + '" alt="' + hoverText + '" title="' + hoverText + '" id="' + labelText + '" border="0">' +
						'</a>';
	}
	// this controls whether to perform document write or not, as during page up or down
	// of repeating data, it needs to process the script but should never issue document.write
	if (defaultButtonProc)
	{
		document.write(widgetButtonHTML);
	}
	return widgetButtonHTML;
}

//**********************************************************************************
//GET TEXT BUTTON HTML 2
//**********************************************************************************
getTextButtonHTML2 = function(id, hoverText, labelText, onClickAction, onKeydown, buttonClass)
{
	var textButtonHTML = new String();
	labelText = labelText.trim();
	hoverText = hoverText.trim();
	
	// If the buttonClass is not provided, use the default
	if (buttonClass == null || buttonClass == '') 
	{
		buttonClass = "inputButton";
		// Use the inputButtonExt style if label is greater than 10 chars
		if(labelText.length > 10)
		{
			buttonClass = "inputButtonExt";
		}
	}

	textButtonHTML =	'<input type="button" class="' + buttonClass + '" onclick="' + onClickAction + ';" name="' + id + '" id="' + id + '" value="' + labelText + '" title="' + hoverText + '" onkeydown="' + onKeydown + '">' +
						'</input>';

	return textButtonHTML;
};



// **********************************************************************************
// function getAttributeValue(String attributeString,String attributeKey)
// given a string such as xA="x1" xB="x2"... and key xB return x2
// **********************************************************************************
function getAttributeValue(attributeString,attributeKey)
{
	var attributeValue = "";
	attributeKey += '="';
	if (attributeString.indexOf(attributeKey) >0)
	{
		var attributeStart = attributeString.indexOf(attributeKey) + attributeKey.length;
		var attributeEnd = attributeString.indexOf('"',attributeStart);
		attributeValue = attributeString.substring(attributeStart,attributeEnd);
	}
	return attributeValue;
}
/* **********************************************************************************

 * Replace dots within the HTML of the passed object

 * **********************************************************************************/
function replaceDots(spanID)
{
	var spanObj = document.getElementById(spanID);
	if (spanObj == null)
	{
		return;
	}
	spanObj.innerHTML = spanObj.innerHTML.rplDots();
}

/* **********************************************************************************

 * Replace dots ("intelligently") within the HTML of the passed object

 * **********************************************************************************/
function replaceDots2(spanID)
{
	var spanObj = document.getElementById(spanID);
	if (spanObj == null)
	{
		return;
	}
	var str = spanObj.innerHTML;

	// Remove consecutive dots
	while(str.indexOf('..') >= 0)
	{
		str = str.replace('..', '&nbsp;&nbsp;');
	}
	// Replace dots with space
	str = str.replace(/&nbsp;\./g, '&nbsp;&nbsp;');
	str = str.replace(/\.&nbsp;/g, '&nbsp;&nbsp;');
	str = str.replace(/:/g, '&nbsp;');
	
	// Remove 1st and last dots if dots
	if (str.length>0)
	{
		if (str.charAt(0) == '.') 
		{
			str = str.replace('.', '&nbsp;');
		}
		if (str.charAt(str.length-1) == '.')
		{
			str = str.replace('.', '&nbsp;');
		}
	}
	
	// update the object
	spanObj.innerHTML = str;
	
	// perform formatting
	setFTT(spanID);
}

function slideHeader(idAnchor)
{	
	//use a work variable for the container frameset to prevent multiple DOM gets
	containerFramesetWRK = 	this.parent.document.getElementById('containerFrameset');
	var slide = document.getElementById("slideButton");
	if(containerFramesetWRK.rows=="0,26,*,18")
	{
		slideDown(containerFramesetWRK);		
		slide.src = '../images/up.gif';
		slide.title=getLanguageLabel("GBL000029");
	}
	else
	{
		slideUp(containerFramesetWRK);		
		slide.src = '../images/down.gif';
		slide.title=getLanguageLabel("GBL000028");
	}
	
	// set the focus on the slider
	var obj = document.getElementById(idAnchor);
	if (obj!=null)
	{
		obj.blur();  // need this to remove the focus from the input=text
		obj.focus();
	}
	
}
var headerHeight = 43;

function slideUp(containerFramesetWRK)
{
	var n = 0.5; //rate
	
	setTimeout(function (){							  
							   		setRows(containerFramesetWRK,(headerHeight/10) *9);
							   },(n * 100));
  	setTimeout(function (){							  
							   		setRows(containerFramesetWRK,(headerHeight/10) *8);
							   },(n * 200));
  	setTimeout(function (){							  
							   		setRows(containerFramesetWRK,(headerHeight/10) *7);
							   },(n * 300));
  	setTimeout(function (){							  
							   		setRows(containerFramesetWRK,(headerHeight/10) *6);
							   },(n * 400));
  	setTimeout(function (){							  
							   		setRows(containerFramesetWRK,(headerHeight/10) *5);
							   },(n * 500));
  	setTimeout(function (){							  
							   		setRows(containerFramesetWRK,(headerHeight/10) *4);
							   },(n * 600));
  	setTimeout(function (){							  
							   		setRows(containerFramesetWRK,(headerHeight/10) *3);
							   },(n * 700));
    setTimeout(function (){							  
							   		setRows(containerFramesetWRK,(headerHeight/10) *2);
							   },(n * 800));
  	setTimeout(function (){							  
							   		setRows(containerFramesetWRK,(headerHeight/10) *1);
							   },(n * 900));
  	setTimeout(function (){							  
							   		setRows(containerFramesetWRK,0);
							   	},(n * 1000));
}

function slideDown(containerFramesetWRK)
{
	var n = 0.5; //rate	
			
	setTimeout(function () {							  
							   		setRows(containerFramesetWRK,headerHeight/10);
							   	},(n * 100));
  	setTimeout(function () {							  
							   		setRows(containerFramesetWRK,(headerHeight/10) *2);
							   	},(n * 200));
  	setTimeout(function () {							  
							   		setRows(containerFramesetWRK,(headerHeight/10) *3);
							   	},(n * 300));
  	setTimeout(function () {							  
							   		setRows(containerFramesetWRK,(headerHeight/10) *4);
							   	},(n * 400));
  	setTimeout(function () {							  
							   		setRows(containerFramesetWRK,(headerHeight/10) *5);
							   	},(n * 500));
  	setTimeout(function () {							  
							   		setRows(containerFramesetWRK,(headerHeight/10) *6);
							   	},(n * 600));
  	setTimeout(function () {							  
							   		setRows(containerFramesetWRK,(headerHeight/10) *7);
							   	},(n * 700));
    setTimeout(function () {							  
							   		setRows(containerFramesetWRK,(headerHeight/10) *8);
							   	},(n * 800));
  	setTimeout(function () {							  
							   		setRows(containerFramesetWRK,(headerHeight/10) *9);
							   	},(n * 900));
  	setTimeout(function () {							  
							   		setRows(containerFramesetWRK,(headerHeight/10)*10);
							   	},(n * 1000));
}

function setRows(containerFramesetWRK,rows)
{	
	containerFramesetWRK.rows= rows + ",26,*,18";
}

function setCursor(currentFrame,cursorType)
{
	// how many frames does the window have
	var n = currentFrame.frames.length;
	var i = 0;
	for (i = 0;i<n;i++) 
	{
		try
		{
			currentFrame.frames[i].document.body.style.cursor=cursorType;
			//currentFrame.frames[i].document.body.style.visibility=cursorType;
			setCursor(currentFrame.frames[i]);
		}
		catch (e)
		{
		}
	}
}
function toolbarKeyPress(event)
{		
	var gblToolbarFrame = getFrameToolbar();
	if (gblToolbarFrame == null)
	{
		return;
	}
	
	if(window.event) // IE
	{
		keynum = event.keyCode;
	}
	else if(event.which) // Netscape/Firefox/Opera
	{		
		keynum = event.which;
	}
			
	keychar = String.fromCharCode(keynum);			
	if(keynum == 79 && event.altKey == true) // option
	{		
		//set focus to boxes...				
		gblToolbarFrame.document.getElementById('optionInput').focus();				
		gblToolbarFrame.document.getElementById('optionInput').select();
	}
	if(keynum == 83 && event.altKey == true) // search
	{		    
		//set focus to boxes..				
		gblToolbarFrame.document.getElementById('optionSearch').focus();
		gblToolbarFrame.document.getElementById('optionSearch').select();
	}
}


// **********************************************************************************
// GET Scroll BUTTON HTML
// **********************************************************************************
// . (**LANGUAGE)

getDisabledScrollButtonHTML = function(hoverText,imagePath)
{
	var imageButtonHTML = new String();
	imageButtonHTML = '<img src="' + imagePath + '" alt="' + hoverText + '" title="' + hoverText + '" border="0">';
	return imageButtonHTML;
};


// **********************************************************************************
// Set style to WF_LTOR and WF_RIGHT_ALIGN
// **********************************************************************************
function setFieldRTL(id)
{
	var obj = document.getElementById(id);
	obj.className += ' wf_LTOR wf_RIGHT_ALIGN';
}

//**********************************************************************************
// Top most frame
//**********************************************************************************
function getTopMostFrame()
{
	var iframe = getParentIFrame(self);
	if (iframe != null)
	{
		return iframe;
	}
	
	var frame = window.top.frames['loginFrame'];
	if (frame == null)
	{
		frame = window.top;
	}
	return frame;
}


//**********************************************************************************
// Main frame to determine whether direction transaction or not (null)
//**********************************************************************************
function getFrameMainFrame()
{
	frame = getTopMostFrame().frames['mainFrame'];	
	return frame;
}


// **********************************************************************************
// BottomBar frame
// **********************************************************************************
function getFrameBottomBar()
{
	// Non-full desktop
	var frame = getTopMostFrame().frames['screenFrame'];
	if(frame != null)
	{
		frame = getTopMostFrame().frames['screenFrame'].frames['bottomBarFrame'];
		return frame;
	}
	
	// Equation desktop
	frame =	getTopMostFrame().frames['mainFrame'].frames['inputFrame'].frames['screenFrame'].frames['bottomBarFrame'];
	return frame;
}


// **********************************************************************************
// ButtonBar frame
// **********************************************************************************
function getFrameButtonBar()
{
	// Non-full desktop
	var frame = getTopMostFrame().frames['buttonBarFrame'];
	if(frame != null) 
	{
		return frame;
	}
	
	// Equation desktop
	frame =	getTopMostFrame().frames['mainFrame'].frames['inputFrame'].frames['buttonBarFrame'];
	return frame;
}

// **********************************************************************************
// Screen frame
// **********************************************************************************
function getFrameScreen()
{
	// Non-full desktop
	var frame = getTopMostFrame().frames['screenFrame'];
	if(frame != null)
	{
		return frame;
	}
	
	// Equation desktop
	frame =	getTopMostFrame().frames['mainFrame'].frames['inputFrame'].frames['screenFrame'];
	return frame;
}


// **********************************************************************************
// Desktop frame
// **********************************************************************************
function getFrameDesktop()
{
	// Get the screen
	var screenFrame = getFrameScreen();

	// Equation desktop
	frame =	screenFrame.frames['desktopFrame'];
	return frame;
}


// **********************************************************************************
// Desktop frame
// **********************************************************************************
function getFrameWebfacing()
{
	// Get the screen
	var screenFrame = getFrameScreen();

	// Equation desktop
	frame =	screenFrame.frames['webfacingFrame'];
	return frame;
}


// **********************************************************************************
// Current frame: either the desktop or the webfacing frame
// **********************************************************************************
function getFrameCurrent()
{
	// Try the Webfacing frame
	frame = getFrameWebfacing();

	// Page not recognised, this page must have crashed, then assume this is the
	// current
	if( frame )
	{
		try
		{
			if (frame.eqDriver == null)
			{
				return frame;	
			}
			
			// Non-driver page, then this is the current
			if (frame.eqDriver == "N")
			{
				return frame;
			}
		}
		catch (e)
		{
			if( window.console) 
			{
				console.error( 'global.js getFrameCurrent error' + e  );
			}
		}
	}

	// Otherwise, the Desktop frame is the current frame
	return getFrameDesktop();
}


// **********************************************************************************
// Tab frame
// **********************************************************************************
function getFrameTab()
{
	// Non-full desktop
	var frame = getTopMostFrame().frames['infoFrame'];
	if(frame != null)
	{
		frame = getTopMostFrame().frames['infoFrame'].frames['tabsFrame'];
		return frame;
	}
	
	// Equation desktop
	frame =	getTopMostFrame().frames['mainFrame'].frames['inputFrame'].frames['infoFrame'].frames['tabsFrame'];
	return frame;
}

// **********************************************************************************
// TabBar frame
// **********************************************************************************
function getFrameTabBar()
{
	// Non-full desktop
	var frame = getTopMostFrame().frames['infoFrame'];
	if(frame != null)
	{
		frame = getTopMostFrame().frames['infoFrame'].frames['tabBarFrame'];
		return frame;
	}
	
	// Equation desktop
	frame =	getTopMostFrame().frames['mainFrame'].frames['inputFrame'].frames['infoFrame'].frames['tabBarFrame'];
	return frame;
}

// **********************************************************************************
// Toolbar frame
// **********************************************************************************
function getFrameToolbar()
{
	try
	{
		return getTopMostFrame().frames['toolbarFrame'];
	}
	catch (e)
	{
		return null;
	}
}

//**********************************************************************************
// Blank frame
//**********************************************************************************
function getFrameBlankFrame()
{
	try
	{
		return getTopMostFrame().frames['blankFrame'];
	}
	catch (e)
	{
		return null;
	}
}


//**********************************************************************************
// Navigator view frame
//**********************************************************************************
function getFrameNavigatorView()
{
	return getTopMostFrame().frames['mainFrame'].frames['navigatorFrame'].frames['navigatorViewFrame'];
}


//**********************************************************************************
// Blank frame
//**********************************************************************************
function getFrameBlankFrameLogin()
{
	try
	{
		return window.top.frames['blankInLoginFrame'];
	}
	catch (e)
	{
		return null;
	}
}



//**********************************************************************************
// Footer frame
//**********************************************************************************
function getFrameFooter()
{
	return getTopMostFrame().frames['footerFrame'];
}


// **********************************************************************************
// function printWindow()
// Print the main window
//
// Parameters:
// None
// **********************************************************************************	
function printWindow()
{
	var currentFrame = getFrameCurrent();
	currentFrame.focus();
	currentFrame.print();
}

// **********************************************************************************
// function isWebFacing()
// Determine when running a WebFacing function
//
// Parameters:
// None
// **********************************************************************************	
function isWebFacing()
{
	return webFacing;
}

// **********************************************************************************
// function setNPF(id)
// Change wf_field to wf_field2
//
// Parameters:
// id - field id
// **********************************************************************************
function setNPF(id)
{
     var obj = document.getElementById(id);
 	if (obj == null)
	{
		return;
	}

     obj.className = obj.className.replace('wf_field', 'wf_field2');
}

//**********************************************************************************
// function replaceAll(source,stringToFind,stringToReplace)
// Replace the occurrence of a string in a string
//
// Parameters:
// source - source string
// stringToFind - string to replace
// stringToReplace - replacement string
//**********************************************************************************
function replaceAll(source,stringToFind,stringToReplace)
{
	// blank string, then simply return the source string
	if (stringToFind=='')
	{
		return source;
	}
	
	var temp = source;
	var index = temp.indexOf(stringToFind);

	while(index != -1)
	{
		temp = temp.replace(stringToFind,stringToReplace);
		index = temp.indexOf(stringToFind,index+stringToReplace.length);
	}
	return temp;
}

//**********************************************************************************
// function convertToJavaScript(msg)
// Replace a string with control characters in order to retain the actual string
//
// Parameters:
// msg - the string
//**********************************************************************************
function convertToJavaScript(msg)
{
	return msg.rplQuotes().rplSingleQuotesWithSlashSingleQuotes().rplSlash();
}

//**********************************************************************************
// function getWebAppName()
// Return the Web Application name
//**********************************************************************************
function getWebAppName()
{
	if (webAppName== null)
	{
		webAppName = window.location.pathname.substring(1,window.location.pathname.indexOf('/',1));
	}
	return webAppName;
}

//**********************************************************************************
// function getWSCall()
// Return the WS Call
//**********************************************************************************
function getWSCall()
{
	return new WS.Call('/' + getWebAppName() + '/services/ServiceDirectory'); 
}


//**********************************************************************************
// function getEquationCom()
// Return the Equation service path
//**********************************************************************************
function getEquationServicePath() 
{
	return 'http://services.equation.misys.com';
}


//**********************************************************************************
// function printTime(str)
// Display the time
// 
// Parameters:
// str - string to be displayed
// add - add to existing string
//**********************************************************************************
function printTime(str,clear)
{
	return;
	var currentTime = new Date();
	var windowStatus = str + "=" + currentTime.getHours() + ":" + currentTime.getMinutes() + ":" + currentTime.getSeconds() + "."
								+ currentTime.getMilliseconds() + " ";
	
	if (clear)
	{
		window.status = windowStatus;
	}
	else
	{
		window.status += windowStatus;
	}
	
	return currentTime;
}


//**********************************************************************************
// function printTime(str)
// Display the time
//
// Parameters:
// str - string to be displayed
// referenceTime - referenceTime
//**********************************************************************************
function printIntervalTime(str, referenceTime)
{
	return;
	var currentTime = new Date();
	var windowStatus = ""; 
	if (referenceTime == null)
	{
		windowStatus = str + "=" + (currentTime.getTime() - startLoadTime.getTime()).toString() + " ";
	}
	else
	{
		windowStatus = str + "=" + (currentTime.getTime() - referenceTime.getTime()).toString() + " ";
	}
	window.status += windowStatus;
	return currentTime;
}


//**********************************************************************************
// function restoreWindowPosition()
// Restore the window to the last known position
//
// Parameters:
// None
//**********************************************************************************
function restoreWindowPosition()
{
	// position the window on the last known position
	pageLeft = parseInt(getCookie("windowLeft")); 
	pageTop = parseInt(getCookie("windowTop"));
	if (!isNaN(pageLeft) && !isNaN(pageTop) && pageLeft != 0 && pageTop != 0)
	{
		try
		{
			self.moveTo(pageLeft,pageTop);
			return true;
		}
		catch (e)
		{
		}
	}
	return false;
}

//**********************************************************************************
// function saveWindowPosition()
// Save the window position
//
// Parameters:
// None
//**********************************************************************************
function saveWindowPosition()
{
	setCookie("windowLeft", getTopWindowLeft() - window_outerWidth, 30);
	setCookie("windowTop", getTopWindowTop() - window_outerHeight, 30);
}


//**********************************************************************************
// function negateNum(str)
// Negate a number
//
// Parameters:
// numeric - a valid numeric - digits and . only
//**********************************************************************************
function negateNum(numeric)
{
	var buffer = '';
	for (var i = 0; i < numeric.length; i++)
	{
		c = numeric.charAt(i);

		// number?
		if (c >= '0' && c <= '9')
		{
			if (c == '0')
			{
				buffer += "9";
			}
			else if (c == '1')
			{
				buffer += "8";
			}
			else if (c == '2')
			{
				buffer += "7";
			}
			else if (c == '3')
			{
				buffer += "6";
			}
			else if (c == '4')
			{
				buffer += "5";
			}
			else if (c == '5')
			{
				buffer += "4";
			}
			else if (c == '6')
			{
				buffer += "3";
			}
			else if (c == '7')
			{
				buffer += "2";
			}
			else if (c == '8')
			{
				buffer += "1";
			}
			else if (c == '9')
			{
				buffer += "0";
			}
		}

		else if (c == '.')
		{
			buffer += ".";
		}

		// not a number, then simply return the original string
		else
		{
			return numeric;
		}
	}
	return buffer;
}


//**********************************************************************************
// function getStyle(obj, styleName)
// Retrieve the style value
//
// Parameters:
// obj       - the object
// styleName - the style to retrieve 
//**********************************************************************************
function getStyle(obj, styleName)
{
	if(obj.currentStyle)
	{
		return obj.currentStyle[styleName];
	}
	else
	{
		return document.defaultView.getComputedStyle(obj,null).getPropertyValue(styleName);
	}
}


//**********************************************************************************
// function doNothing()
// Empty function
//**********************************************************************************
function doNothing()
{
}


//**********************************************************************************
// function loginPage()
// Show the login page
//**********************************************************************************
function showLoginPage()
{
	window.top.location.replace("loginMain.jsp" + "?" + mandatoryParameterOnLogin());
}
function goToLogin()
{
	window.top.location.replace("equation/jsp/loginMain.jsp" + "?" + mandatoryParameterOnLogin());
}

//**********************************************************************************
// function mandatoryParameterOnLogin()
// Construct the mandatory parameters when doing a login again
//**********************************************************************************
function mandatoryParameterOnLogin()
{
	// setup necessary parameters
	var workstationId = getCookie("cWorkstationID");
	var style = getCookie("cStyle");
	
	// URL link
	var link = "workstationId=" + workstationId;
	link += "&style=" + style;
	
	return link;
}


//*********************************************************************************************
//function PopupWindow_hidePopupWindows(e)
//This global function checks all PopupWindow objects onmouseup to see if they should be hidden
//
//Parameters: 
//e: Event
//*********************************************************************************************	
function anyOpenPopupOtherThanSelectionBox(e) 
{
	if (window.popupWindowObjects == null)
	{
		return false;
	}
	
	for (var i=0; i<window.popupWindowObjects.length; i++) 
	{	
		if (window.popupWindowObjects[i] != null) 
		{	
			var p = popupWindowObjects[i];	
			if (p.id != 'mouseSelectionBoxPopupID' && p.isClicked(e))
			{
				return true;
			}
		}
	}
}


//*********************************************************************************************
// function dbgScptCon(dbgWid)
// Invoke when there are script errors only if the display file has been converted 
// with script consolidated=Y
//
// Parameters:
// dbgWid - debug info
// e      - exception
//*********************************************************************************************	
function dbgScptCon(dbgWid, e)
{
}



//*********************************************************************************************
// function startLoadingTransaction()
// This is invoked prior to loading a transaction (e.g. calling function.jsp) 
//
// Parameters:
// setTime - true to force re-setting of UXP execution time
// None
//*********************************************************************************************	
function startLoadingTransaction(setTime)
{
	// UXP Execution time 
	if( window.top.equxp && (setTime || !window.top.equxp.startTime))
	{
		window.top.equxp.startTime = new Date().getTime();
	}
			
	// Toolbar frame
	var gblToolbarFrame = getFrameToolbar();
	
	var gblNavigatorViewFrame = null;
	if (getFrameMainFrame() != null)
	{
		gblNavigatorViewFrame = getFrameNavigatorView();
	}
	
	var currentFrame = getFrameCurrent();

	// Toolbar frame
	if (gblToolbarFrame != null)
	{
		// display the progressbar
		var obj = gblToolbarFrame.document.getElementById("progressBarButton");
		if (obj != null)
		{
			obj.style.visibility="";
		}
		
		// change to waiting icon
		gblToolbarFrame.document.body.style.cursor='wait';
		gblToolbarFrame.document.body.disabled=true;
	}
	
	// Navigator frame
	if (gblNavigatorViewFrame != null)
	{
		gblNavigatorViewFrame.document.body.style.cursor='wait';
		gblNavigatorViewFrame.document.body.disabled=true;
	}
	
	// Main frame
	if (currentFrame != null && currentFrame.document.body != null)
	{
		currentFrame.document.body.style.cursor='wait';
	}
	
	// Bottombar frame
	gblButtonbarFrame = getFrameButtonBar();
	if (gblButtonbarFrame != null)
	{
		showSpinnerButton();

		// Start the error monitoring 
		gblButtonbarFrame.initialiseErrorMonitor();
		gblButtonbarFrame.startErrorMonitor();
	}
}

//*********************************************************************************************
// function endLoadingTransaction()
// This is invoked during onLoad event when the data has already been loaded 
//
// Parameters:
// None
//*********************************************************************************************	
function endLoadingTransaction()
{
	// Toolbar frame
	var gblToolbarFrame = getFrameToolbar();

	
	var gblNavigatorViewFrame = null;
	if (getFrameMainFrame() != null)
	{
		gblNavigatorViewFrame = getFrameNavigatorView();
	}
	
	var currentFrame = getFrameCurrent();

	// Toolbar frame
	if (gblToolbarFrame != null)
	{
		// display the progressbar
		var obj = gblToolbarFrame.document.getElementById("progressBarButton");
		if (obj != null)
		{
			obj.style.visibility="hidden";
		}
		
		// change to waiting icon
		gblToolbarFrame.document.body.style.cursor='';
		gblToolbarFrame.document.body.disabled=false;
	}

	// Navigator frame
	if (gblNavigatorViewFrame != null)
	{
		gblNavigatorViewFrame.document.body.style.cursor='';
		gblNavigatorViewFrame.document.body.disabled=false;
	}
	
	// Main frame
	if (currentFrame != null)
	{
		currentFrame.document.body.style.cursor='';
	}

	// Bottombar frame
	gblButtonbarFrame = getFrameButtonBar();
	if (gblButtonbarFrame != null)
	{
		// do not undo the spinner button
		
		// End the error monitoring 
		gblButtonbarFrame.endErrorMonitor();
	}

	// UXP Execution time 
	if( window.top.equxp && window.top.equxp.startTime)
	{
		window.top.equxp.updateExecutionTime(window.top.equxp.startTime, new Date().getTime());
		delete window.top.equxp.startTime;
	}
}

//**********************************************************************************
// function hideSpinnerButton()
// Hide the spinner button
//
// Parameters:
// None
//
// Returns:
// None
//**********************************************************************************
function hideSpinnerButton()
{
	if (gblButtonbarFrame == null)
	{
		gblButtonbarFrame = getFrameButtonBar();					
	}
	
	obj = gblButtonbarFrame.document.getElementById('spinnerImage');
	if (obj != null)
	{
		obj.style.visibility = "hidden";
	}
	
	hideUXPBusyIndicator();
}

//**********************************************************************************
// function showSpinnerButton()
// Show the spinner button
//
// Parameters:
// None
//
// Returns:
// None
//**********************************************************************************
function showSpinnerButton()
{
	obj = gblButtonbarFrame.document.getElementById('spinnerImage');
	if (obj != null)
	{
		obj.style.visibility = "";
	}
}


//**********************************************************************************
// function displayCurrentFrame()
// Display the main screen (either the desktop/webfacing frame)
//
// Parameters:
// None
//
// Returns:
// None
//**********************************************************************************
function displayCurrentFrame()
{
	var screenFrameset = getFrameScreen().document.getElementById('screenFrameset');
	if(getFrameWebfacing().eqDriver != 'Y' && getFrameWebfacing().eqDriver != 'X')
	{
		screenFrameset.setAttribute("cols","0%,100%");
	}
	else
	{
		screenFrameset.setAttribute("cols","100%,0%");
	}
	screenFrameset.style.visibility="";
}

//**********************************************************************************
// function hideCurrentFrame()
// Hide the main screen
//
// Parameters:
// None
//
// Returns:
// None
//**********************************************************************************
function hideCurrentFrame()
{
	var screenFrameset = getFrameScreen().document.getElementById('screenFrameset');
	screenFrameset.style.visibility="hidden";
}


//**********************************************************************************
// function getParentIFrame( currentFrame )
// Retrieve the parent IFrame  
//
// Parameters:
// None
//
// Returns:
// the parent iframe.  Null if not found
//**********************************************************************************
function getParentIFrame( currentFrame )
{
	if (currentFrame == null || currentFrame.frameElement == null)
	{
		return null;
	}
	if( currentFrame.frameElement.tagName === "IFRAME")
	{
		return currentFrame;
	}
	return getParentIFrame( currentFrame.parent);
}


//**********************************************************************************
// function getNamedFrame( start, name )
// Retrieve the specified frame  
//
// Parameters:
// start - the parent frame
// name  - the id of the frame inside the parent frame
//
// Returns:
// the specified frame.  Null if not found
//**********************************************************************************
function getNamedFrame( start, name )
{
	result = undefined;
	var index = 0;
	var childFrame;
	for( index = 0; index < start.frames.length; index++)
	{
		childFrame = start.frames[index];
		if( childFrame.name === name )
		{
			result = childFrame;
		}
		else
		{
			result = getNamedFrame( childFrame, name);
		}
		if(result !== undefined) 
		{
			break;
		}			
	}
	return result;
}


//**********************************************************************************
// function closeBrowser()
// Close the browser  
//
// Parameters:
// None
//
// Returns:
// None
//**********************************************************************************
function closeBrowser()
{
	window.top.close();
}


//**********************************************************************************
// function displayMessage(msg)
// Display message using window.alert()  
//
// Parameters:
// msg - message to be displayed
//
// Returns:
// None
//**********************************************************************************
function displayMessage(msg)
{
	window.alert(msg);
}


//**********************************************************************************
// function confirmMessage(msg)
// Display popup message using window.top.confirm()  
//
// Parameters:
// msg - message to be displayed
//
// Returns:
// result of window.top.confirm()
//**********************************************************************************
function confirmMessage(msg)
{
	return window.top.confirm(msg);
}


//**********************************************************************************
// function closeWebFacingTab()
// Close the WebFacing tab (in UXP browser)  
//
// Parameters:
// None
//
// Returns:
// None
//**********************************************************************************
function closeWebFacingTab()
{
	window.top.equxp.closeWebFacingTab();
}


//**********************************************************************************
// function closeEquationTab(name)
// Close the specified Equation tab (in UXP browser)
//
// Parameters:
// name - the tab ID
//
// Returns:
// None
//**********************************************************************************
function closeEquationTab(name)
{
	window.top.equxp.closeEquationTab(name);
}


//**********************************************************************************
// function closeThisEquationTab()
// Close the this Equation tab (in UXP browser)
//
// Parameters:
// None
//
// Returns:
// None
//**********************************************************************************
function closeThisEquationTab()
{
	var tab = getEquationTab();
	closeEquationTab(tab.id);
}


//**********************************************************************************
// function getEquationTab()
// Retrieve the this UXP tab
//
// Parameters:
// None
// 
// Returns:
// the Equation UXP tab
//**********************************************************************************
function getEquationTab()
{
	var frame = getTopMostFrame();
	var tab = window.top.equxp.getEquationTab(frame.name);
	return tab;
}


//**********************************************************************************
// function displayUXPErrorMessage(msg)
// Display an error message (in UXP browser)
//
// Parameters:
// msg - the message to be displayed
//
// Returns:
// None
//**********************************************************************************
function displayUXPErrorMessage(msg)
{
	window.top.equxp.renderMessage(window.top.ERROR, msg);
}


//**********************************************************************************
// function showUXPBusyIndicator()
// Display the busy indicator (in UXP browser)
//
// Parameters:
// None
//
// Returns:
// None
//**********************************************************************************
function showUXPBusyIndicator()
{
	try
	{
		if (window.top.equxp)
		{
			window.top.equxp.showUXPBusyIndicator();
		}
	}
	catch (e)
	{
		equationLogging(e.message);
	}
}


//**********************************************************************************
// function hideUXPBusyIndicator()
// Hide the busy indicator (in UXP browser)
//
// Parameters:
// None
//
// Returns:
// None
//**********************************************************************************
function hideUXPBusyIndicator()
{
	try
	{
		if (window.top.equxp)
		{
			window.top.equxp.hideUXPBusyIndicator();
		}
	}
	catch (e)
	{
		equationLogging(e.message);
	}
}

var RETURN_SUCCESS = 0;
var ERROR_DRIVER_FIELDS_NOT_FOUND = 1; 
var ERROR_FRAME_NOT_FOUND = 2;
var ERROR_PENDING_TRANSACTION = 3;
var ERROR_UNKNOWN = 4;

//**********************************************************************************
// function loadWebFacingTransaction(mode, unit, optionId, context)
// Load the specified Webfacing transaction
//
// Parameters:
// mode     - WF mode
// unit     - the Equation  unit mnemonic
// optionId - the option id
// context  - the context
//
// Returns:
// None
//**********************************************************************************
function loadWebFacingTransaction(mode, unit, optionId, context)
{
	var webFacingFrame = getFrameWebfacing();
	if (webFacingFrame == undefined)
	{
		return ERROR_FRAME_NOT_FOUND;
	}
	
	if (webFacingFrame.eqDriver == 'X')
	{
		var currentFrame = getFrameCurrent();
		setOptionId(optionId);			
		startLoadingTransaction();
		getWebAppName();
		currentFrame.location.replace("/" + getWebAppName() +  "/equation/jsp/function.jsp?name=&mode=" + mode + "&unit=" + unit.toUpperCase() + "&optionId=" + optionId.toUpperCase() + "&context=" + context.toUpperCase());
		return RETURN_SUCCESS;
	}
	
	var wfDoc = webFacingFrame.document;
	var zleMod = wfDoc.getElementById('l1_W97HMDA$ZLEMOD');
	if( zleMod == undefined)
	{
		return ERROR_PENDING_TRANSACTION;
	}
	
	webFacingFrame.document.getElementById('l1_W97HMDA$ZLEMOD').value=mode;							
	webFacingFrame.document.getElementById('l1_W97HMDA$ZLUNIT').value=unit;										
	webFacingFrame.document.getElementById('l1_W97HMDA$ZLOPT').value=optionId;										
	webFacingFrame.document.getElementById('l1_W97HMDA$ZLCONT').value=context;
	
	setTimeout( 
			function() 
			{
				webFacingFrame.setOptionId(optionId);
				webFacingFrame.startLoadingTransaction();
				webFacingFrame.validateAndSubmit('ENTER',webFacingFrame);										
			}
			, 50);
	
	return RETURN_SUCCESS;
}


//**********************************************************************************
// function webFacingLogoff()
// Logoff WebFacing transaction
//
// Parameters:
// None
//
// Returns:
// status whether WebFacing has been log-off, or reason why it cant
//**********************************************************************************
function webFacingLogoff()
{
	var webFacingFrame = getFrameWebfacing();
	if (webFacingFrame == undefined)
	{
		return ERROR_FRAME_NOT_FOUND;
	}

	// EqDriver undefined then page has crashed
	if (webFacingFrame.eqDriver == null || webFacingFrame.eqDriver == undefined)
	{
		return ERROR_NOT_DRIVER_SCREEN;
	}
	
	var wfDoc = webFacingFrame.document;
	var zleMod = wfDoc.getElementById('l1_W97HMDA$ZLEMOD');
	
	if( zleMod == undefined)
	{
		return ERROR_PENDING_TRANSACTION;
	}
	
	wfDoc.getElementById('l1_W97HMDA$ZLEMOD').value='*S';							
	webFacingFrame.validateAndSubmit('ENTER',webFacingFrame);

	// end the job
	closeWebFacingJob(webFacingFrame);
	
	return RETURN_SUCCESS;
}



//**********************************************************************************
// function closeWebFacingJob(webFacingFrame)
// Force closing of WebFacing job (this is copied from IBM code
//
// Parameters:
// webFacingFrame - the frame containing the WF job
//
// Returns:
// status whether WebFacing has been log-off, or reason why it cant
//**********************************************************************************
function closeWebFacingJob(webFacingFrame)
{
	// not specified, try to retrieve the WebFacing frame
	if (webFacingFrame == null)
	{
		webFacingFrame = getFrameWebfacing();
		if (webFacingFrame == null)
		{
			return ERROR_FRAME_NOT_FOUND;
		}
	}
	
	try
	{
		// webFacingFrame.c_i_e_i_w_7512_logoffAndSubmit("SCREEN");
		var formId = webFacingFrame.WF_FORMID;
		var elem= webFacingFrame.document.forms[formId].elements["AID"];
		elem.value= "LOGOFF";
		elem.disabled= false;
		webFacingFrame.document.forms[formId].submit();
	}
	catch(any_exp)
	{
		return ERROR_UNKNOWN;
	}
	
	return RETURN_SUCCESS;
}


//**********************************************************************************
// function isUXP()
// Determine if running in UXP mode
//
// Parameters:
// None
//
// Returns:
// true if running in UXP mode
//**********************************************************************************
function isUXP()
{
	var win = window.opener != null ? window.opener : window;
	if( win.opener != null )
	{
		win = win.opener;
	}
	var iframe = win.getParentIFrame(win);
	return iframe != null;
}


//**********************************************************************************
// function isF5(keyCode)
// Determine if key press is an F5
//
// Parameters:
// keyCode - the key code
//
// Returns:
// true if F5
//**********************************************************************************
function isF5(keyCode)
{
	return (keyCode == 116);
}


//**********************************************************************************
// function disableF5(e)
// Disable F5
//
// Parameters:
// e - event
//
// Returns:
// true if key is NOT F5
// false if key is F5 and has not disabled
//**********************************************************************************
function disableF5(e)
{
	// F5, disable it
	if (isF5(e.keyCode))
	{
		window.event.keyCode = 0;
		return false;
	}
	return true;
}


//**********************************************************************************
// function switchRTL()
// Switch to RTL if needed
//
// Parameters:
// None
//
// Returns:
// None
//**********************************************************************************
function switchRTL()
{
	if (RTL)
	{
		document.body.dir = 'RTL';
		
		// Add a class to the WebFacing main field table 'l1_activetable' to allow
		// the table to be right-aligned on the page
		var webFacingTable = document.getElementById('l1_activetable');
		if (webFacingTable != undefined)
		{
			webFacingTable.className = webFacingTable.className + " wf_TABLE_RIGHT_ALIGN";
		}
	}
}


//*********************************************************************************************
// function equationLogging(messageType, message)
// Logged a message
//
// Parameters:
// messageType - message type - LOGGING_INFO or LOGGING_WARN or LOGGING_ERROR
// message     - message
//
// Returns:
// None
//*********************************************************************************************	
function equationLogging(messageType, message)
{
	if (messageType >= jsLogging)
	{
		writeToLog(message);
	}
}

//*********************************************************************************************
// function isEquationNotification(messageType)
// Determine if the message is to be notified to the user
//
// Parameters:
// messageType - message type - LOGGING_INFO or LOGGING_WARN or LOGGING_ERROR
// message     - message
//
// Returns:
// true if the message will be notified to the user
//*********************************************************************************************	
function equationNotification(messageType, message)
{
	equationLogging(0,message);

	// notify
	if (messageType >= jsNotification)
	{
		displayMessage(message);
	}
}


//*********************************************************************************************
// function writeToLog(message)
// Write message to the log
//
// Parameters:
// messageType - message type - LOGGING_INFO or LOGGING_WARN or LOGGING_ERROR
// message - the message
//
// Returns:
// true - logged written
//*********************************************************************************************	
function writeToLog(messageType, message)
{
	// console exists?
	if ((typeof console == "undefined") || console==null || !console.log)
	{
		return false;
	}
	
	try
	{
		if (messageType == 20)
		{
			console.error(messageType + ":" + message);
		}
		else
		{
			console.log(messageType + ":" + message);
		}
		return true;
	}
	catch (e)
	{
		return false;
	}
}


//*********************************************************************************************
// function loadModalProcessing()
// Processing when loading a "modal" window
//
// When a Window is open that is supposed to be a modal Window, invoke this method passing
// the Window Id.  This will continuously check the Window if it is already closed.  This
// approach is taken, so that there is no need to put codes in each of the modal Window to 
// handle closing of the modal Window.
//
// Parameters:
// windowId - the window id as returned by window.open()
//
// Returns:
// None
//*********************************************************************************************	
function loadModalProcessing(windowId)
{
	// UXP, show the busy indicator to lock UXP
	showUXPBusyIndicator();
	
	// Monitor the window
	monitorModalProcessing(windowId);
}


//*********************************************************************************************
// function monitorModalProcessing(windowId)
// This will keep track of the window and see if it is still open or have already been closed.
//
// Parameters:
// windowId - the window id as returned by window.open()
//
// Returns:
// None
//*********************************************************************************************	
function monitorModalProcessing(windowId)
{
	// is window still open or closed?
	if (windowId != null)
	{
		if (windowId.closed)
		{
			unloadModalProcessing();
			return;
		}
	}
	
	// recheck again after a while
	setTimeout( 
			function() 
			{
				monitorModalProcessing(windowId);
			}
			,500);
}


//*********************************************************************************************
// function unloadModalProcessing()
// Processing when unloading a modal window
//
// Parameters:
// None
//
// Returns:
// None
//*********************************************************************************************	
function unloadModalProcessing()
{
	// UXP, remove busy indicator to unlock UXP
	hideUXPBusyIndicator();
}


//**********************************************************************************
//function disableKeyboardKey(e)
//This will disable the key so that the browser will not action it
//
//Parameters:
//e - event
//
//Returns:
//true  - key has been cleared
//false - key has not been cleared
//**********************************************************************************	
function disableKeyboardKey(e)
{
	// internet explorer?
	if (window.event)
	{
		window.event.keyCode = 0;
		window.event.cancelBubble = true;
		window.event.returnValue = false;
	}
	
	// other browser
	else if (e.which) 
	{
		//e.which = 0;
		e.cancelBubble = true;
	}
		
	// Other
	else
	{
		return true;
	}

	// key has been disabled	
	return true;
}


//*********************************************************************************************
// function isClickSameField(currentField, previousField, windowId)
// Determine whether the same button has been clicked again, and then clears out the
// last click details
//
// Parameters:
// currentField  - the popup Div
// previousField - the popup Window
//
// Returns:
// true -  if same button has been clicked
//*********************************************************************************************	
function isClickSameField(currentField, previousField, windowId)
{
	// Click the same field?
	var sameField = currentField == previousField; 
	if (sameField)
	{
		// Window popup?
		if (windowId != null)
		{
			windowId.close();
		}
	}
	
	// Reset everything
	lastCalendarDateClick = null;
	lastFrequencyClick = null;
	lastListClick = null;
	lastOptionClick = null;
	lastVOptionClick = null;
	
	// Return status
	return sameField;
}


//*********************************************************************************************
// function isButtonWithPopup(obj)
// Determines if the button display a popup when selected
//
// Parameters:
// obj - button object
//
// Returns:
// true - if the button display a popup when selected 
//*********************************************************************************************	
function isButtonWithPopup(obj)
{
	if (obj==null)
	{
		return false;
	}
	
	// only these buttons produces popup
	var id = obj.id;
	if (id.endsWith(BUT_WIDGET) 
			|| id.endsWith(BUT_VOPTION)
			|| id.endsWith(BUT_OPTION)
			|| id.endsWith(BUT_WIDGET + BUT_HREF) 
			|| id.endsWith(BUT_VOPTION + BUT_HREF)
			|| id.endsWith(BUT_OPTION + BUT_HREF)
		)
	{
		return true;
	}
	
	return false;
}


//*********************************************************************************************
// function getEquationTabTitle()
// Retrieve the Equation tab title (only if UXP)
//
// Parameters:
// fullTitle - true  - return the full tab title
//             false - return only the title without the option mnemonic
//
// Returns:
// The Equation tab title 
//*********************************************************************************************	
function getEquationTabTitle(fullTitle)
{
	// get the Equation tab
	var tab = getEquationTab();
	
	// return the full title
	if (fullTitle)
	{
		return tab.title;
	}
	
	// parse and return only the title after the :
	var index = tab.title.indexOf(':');
	if (!fullTitle && index >= 0)
	{
		return tab.title.substr(index+1);
	}
	else
	{
		return tab.title;
	}
}


//*********************************************************************************************
//function setUXPProblemAreaVisible(setVisible)
//Show or hide the problem area frame (only if UXP)
// For non-LRP show/hide the whole info tab frame 
// For LRP show/hide the problem frame within the info frame, leaving the comments frame visible
//Parameters:
// setVisible   	true  - show
// 	        		false - hide
//
//*********************************************************************************************	
function setUXPProblemAreaVisible(setVisible)
{	
	if (isUXP() == false)
		return;
	
	var iFrame = getTopMostFrame();
	
	var lrpTask = isLrpTask();
	if(!lrpTask )
	{
		// If not LRP task then show or hide the whole info frame
		
		// The height of the info frame is stored in a global variable at the iFrame level
		// so if the user resizes it using the splitter it will be remembered
		var savedInfoFrameHeight = iFrame.infoFrameHeight;
		if (savedInfoFrameHeight == undefined || savedInfoFrameHeight == '')
		{
			savedInfoFrameHeight = 100;
		}

		//use a work variable for the container frameset to prevent multiple DOM gets
		var containerFramesetWRK = 	getTopMostFrame().document.getElementById('txnFrameset');
		var infoFrame = containerFramesetWRK.document.getElementById("infoFrame");
		var height = infoFrame.height;
		
		if (setVisible == true && height < 1 )
		{
			containerFramesetWRK.rows="*,5," + savedInfoFrameHeight;
		}
		if (setVisible == false)
		{
			if (height > 0)
			{
				iFrame.infoFrameHeight = height;
			}
			containerFramesetWRK.rows="*,0,0";
		}
	}
	else
	{
		// If this is a LRP task then show or hide the problems frame leaving the comments 
		// frame always visible
		
		var uxpTabFramesetWRK = getNamedFrame(getTopMostFrame(), 'infoFrame').document.getElementById('uxpTabFrameset');

		
		// The width of the problems frame is stored in a global variable at the iFrame level
		// so if the user resizes it using the splitter it will be remembered
		var firstTime = false;
		var savedProblemFrameWidth = iFrame.problemFrameWidth;
		if (savedProblemFrameWidth == undefined || savedProblemFrameWidth == '')
		{
			// If this is the first call, the initial layout will have problem frame filling 100%
			// This is wrong and we need to default it to a 50% split
			savedProblemFrameWidth = '50%';
			iFrame.problemFrameWidth = savedProblemFrameWidth;

			firstTime = true;
		}
		
		
		var infoFrame = uxpTabFramesetWRK.document.getElementById("infoFrameset").document.getElementById("tabsFrame");
		var width = infoFrame.width;

		if (setVisible == true && width < 1 )
		{
			uxpTabFramesetWRK.cols="*,5," + savedProblemFrameWidth;
		}
		if (setVisible == false)
		{
			if (width > 0 && !firstTime)
			{	
				iFrame.problemFrameWidth = width;
			}
			uxpTabFramesetWRK.cols="*,0,0";
		
		}
		
		// On the first call the info frame and splitter need to be made visible
		if (firstTime)
		{
			var containerFramesetWRK = 	getTopMostFrame().document.getElementById('txnFrameset');
			containerFramesetWRK.rows="*,5,100";
		}
	}
}

