var globalPopup = null; // Pointer to the popup created by createPopup()
var popupAsWindow = null; // Pointer to the window created by openPopupAsWindow()

var listPopupZIndex = 5;

// **********************************************************************************
// function createPopup(posx, posy, name,listKeys, listDescs, onClickFunc,funcParameter)
// Create a popup
//
// Parameters:
// posx          - position x
// posy          - position y
// name          - popup id
// listKeys      - list of item keys
// listDescs     - list of item descriptions
// onClickFunc   - list of onClick function
// funcParameter - list of parameteters for the onClick function
// **********************************************************************************	
function createPopup(posx, posy, name,listKeys, listDescs, onClickFunc,funcParameter)
{
	// RTL?
	var strRTL = '>';
	var strRTL2 = '';
	if (RTLOrg)
	{
		strRTL = 'class="wf_LTOR wf_RIGHT_ALIGN">';
		strRTL2 = ' wf_LTOR wf_RIGHT_ALIGN ';
	}

	var listHTML = '';
	for (i=0; i<listKeys.length; i++) 
	{
		var id = name + listKeys[i];
		listHTML += 
			'<tr>' +
			'<td>' +
				'<a id="' + id + '" ' + 
					'name="' + id + '" ' + 
					'class="' + 'contextMenuStyle' + '" ' + 
					'href="javascript:' + onClickFunc + '(event,this,\'' + listKeys[i] + '\',\'' + listDescs[i] + '\',' + funcParameter  + '); hidePopup(); "' +
					'>' + listDescs[i] + '</a>' +  
			'</td>' +
			'</tr>';
	}

	// Build the widget container
    popupHTML = 
    '	<div class="popupWidgetContainer" id="' + name + 'PopupWidgetContainer" >'+
	'		<div class="popupWidgetHeader" id = "' + name + 'PopupWidgetBody">'+
	'			<table id="' + name + 'PopupList">' +
							listHTML +
	'			</table>' +
	'		</div>' +
	'	</div>';
    
	// div
	var listDiv = document.getElementById(name + 'WidgetContainerDIV');
	if (listDiv==null)
	{
		listDiv = document.createElement('div');
		listDiv.id = name + 'WidgetContainerDIV';
		listDiv.className = 'widgetContainerDIV';
		document.body.appendChild(listDiv);
	}				
	listDiv.innerHTML = popupHTML;
	listDiv.style.zIndex = listPopupZIndex ;

	// 	popup
	globalPopup = new PopupWindow(name + 'WidgetContainerDIV');
	globalPopup.autoHide();
	globalPopup.showPopupXY(posx,posy,null);
}


// **********************************************************************************
// function hidePopup()
// Close the popup
// **********************************************************************************	
function hidePopup()
{
	if (globalPopup!=null)
	{
		globalPopup.hidePopup();
	}
}


// **********************************************************************************
// function openPopupAsWindow()
// Open a window for the popup
//
// Parameters:
// posx  - x
// posy  - y
// objId - object id
// **********************************************************************************	
function openPopupAsWindow(posx, posy, objId, jspPage)
{
	// setup the position of the window
	obj = document.getElementById(objId);
	coordinates = getPos(obj);
	var left = coordinates.left + getWindowLeft() - getScrollLeft();
	var top  = coordinates.top + getWindowTop() - getScrollTop();

	// if running under UXP, adjust 
	// not sure why the coordinates are not correct if inside UXP 
	if (isUXP())
	{
		left = left + 10;
		top = top - 150;
	}
	else
	{
		left = left + obj.offsetWidth + 10;
	}
	
	// right to left?
	param = '';
	if (RTL)
	{
		// adjust if field is scrolled
		left = left + getObjectScrollLeft(obj);
		top = top - getObjectScrollTop(obj);

		left = left + ICON_SIZE + getScrollLeft();
		param = '?RTL=' + RTL + 
				'&leftAnchor=' + left; 

		left = left - window_min_width + ICON_SIZE;
		if (left <0)
		{
			left = 1;
		}

	}
	
	// adjust if field is scrolled
	else
	{
		left = left - getObjectScrollLeft(obj);
		top = top - getObjectScrollTop(obj);
	}
	
	var arg = 'left=' + left  + 'px' +
				', top=' + top + 'px';
		
	// open the window
	arg = arg + ', height=10px, width=10px ,titlebar=0, scrollbar=no, resizable=no, toolbar=no, menubar=no, location=no, status=no, directories=no, location=no, modal=yes, outerWidth=0, outerHeight=0';
	popupAsWindow = window.open("/" + getWebAppName() + "/equation/jsp/" + jspPage + param,"popupAsWindowName",arg);
	loadModalProcessing(popupAsWindow);
}


// **********************************************************************************
// function ispopupAsWindowOpen()
// Determine whether the popup window is open
// **********************************************************************************	
function isPopupAsWindowOpen()
{
	if (popupAsWindow != null)
	{
		if (popupAsWindow.closed)
		{
			popupAsWindow = null;
		}
		else
		{
			return true;
		}
	}
	
	return false;
}

// **********************************************************************************
// function focusPopupAsWindow()
// Determine whether the supervisor window is open
// **********************************************************************************	
function focusPopupAsWindow()
{
	popupAsWindow.focus();
}


// **********************************************************************************
// function clearPopupAsWindow()
// Determine whether the supervisor window is open
// **********************************************************************************	
function clearPopupAsWindow()
{
	popupAsWindow = null;
}

