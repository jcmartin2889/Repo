
var maximizeListeners=new Object();
var leftCols = "25%";
var rightCols = "75%";
var bRestore = false;
document.ondblclick = mouseDblClickHandler;
var srcRoot = "";


function toggleFrame(title)
{	
	// no container, then exit	
	if (getFrameMainFrame() == null)
	{
		return;
	}
	
	gblMainFrameset = getFrameMainFrame().document.getElementById('mainFrameset');

	var navFrameSize = gblMainFrameset.getAttribute("cols");
	var comma = navFrameSize.indexOf(',');
	var left = navFrameSize.substring(0,comma);
	var right = navFrameSize.substring(comma+1);
	if (left == "*" || right == "*")
	{
		// restore frames
		if (RTL)
		{
			gblMainFrameset.setAttribute("cols", rightCols+","+leftCols);
		}
		else
		{
			gblMainFrameset.setAttribute("cols", leftCols+","+rightCols);
		}
		notifyMaximizeListeners(false);
	} 
	else
	{
		// the "cols" attribute is not always accurate, especially after resizing.
		// offsetWidth is also not accurate, so we do a combination of both and
		// should get a reasonable behavior
		var leftSize = getFrameMainFrame().document.getElementById('navigatorFrame').offsetWidth;
		var rightSize = getFrameMainFrame().document.getElementById('inputFrame').offsetWidth;


		leftCols = leftSize * 100 / (leftSize + rightSize);
		rightCols = 100 - leftCols;

		// maximize the frame.
		//leftCols = left;
		//rightCols = right;
		// Assumption: the content toolbar does not have a default title.

		if (RTL)
		{
			if (title != "") // this is the left side for left-to-right rendering
			{
				gblMainFrameset.setAttribute("cols", "*,100%");
			}
			else // this is the content toolbar
			{
				gblMainFrameset.setAttribute("cols", "100%,*");
			}
		}
		else
		{
			if (title != "") // this is the left side for left-to-right rendering
			{
				gblMainFrameset.setAttribute("cols", "100%,*");
			}
			else // this is the content toolbar
			{
				gblMainFrameset.setAttribute("cols", "*,100%");
			}
		}

		notifyMaximizeListeners(true);
	}
}

function setWindowStatus(buttonName)
{
	if (buttonName == "null")
	{
		if (buttonName == "maximize_restore")
		{
			if (bRestore)
			{
				window.status = getLanguageLabel('GBLRESTR');
			}
			else
			{
				window.status = getLanguageLabel('GBLMAXI');
			}
		}
		else
		{
			window.status = "null";
		}
	}

	if (buttonName == "maximize_restore")
	{
		if (buttonName == "maximize_restore")
		{
			if (bRestore)
			{
				window.status = getLanguageLabel('GBLRESTR');
			}
			else
			{
				window.status = getLanguageLabel('GBLMAXI');
			}
		}
		else
		{
			window.status = getLanguageLabel('GBLMAXI');
		}
	}
}

/**
 * Handler for double click: maximize/restore this view
 * Note: Mozilla browsers prior to 1.2.1 do not support programmatic frame resizing well.
 */
function mouseDblClickHandler(e)
{
	 // ignore double click on buttons
	 var target = null; 
	 if (window.event)
	 {
		 target=window.event.srcElement;
	 }
	 else if (e.srcElement)
	 {
		 target = e.srcElement;
	 }
	 else
	 {
		 target = e.target;
	 }
	 if (target.tagName && (target.tagName == "A" || target.tagName == "IMG"))
	 {
		return;
	 }
	 toggleFrame();
	 return false;
}		

function registerMaximizedChangedListener(barname){
	// get to the gblMainFrameset
	var p = parent;
	while (p && !p.registerMaximizeListener)
	{
		p = p.parent;
	}
	if (p!= null)
	{
		p.registerMaximizeListener(barname, maximizedChanged);
	}
}

function restore_maximize(button)
{
	toggleFrame();
	var isIE = navigator.userAgent.indexOf('MSIE') != -1;
	if (isIE && button && document.getElementById(button))
	{
		document.getElementById(button).blur();
	}
}

function maximizedChanged(maximizedNotRestored)
{
	if(maximizedNotRestored)
	{
		try //added exception handler for when buttonbar is clicked too quickly before page loaded fully
		{				
			
			document.getElementById("maximize_restore").src="../images/restore.gif";
			document.getElementById("maximize_restore").setAttribute("title", getLanguageLabel('GBLRESTR'));
			document.getElementById("maximize_restore").setAttribute("alt", getLanguageLabel('GBLRESTR'));		
			bRestore = true;
		}
		catch(e){}
	}
	else
	{
		try
		{	
			document.getElementById("maximize_restore").src="../images/maximize.gif";
			document.getElementById("maximize_restore").setAttribute("title", getLanguageLabel('GBLMAXI'));
			document.getElementById("maximize_restore").setAttribute("alt", getLanguageLabel('GBLMAXI'));		
			bRestore = false;
		}
		catch(e){}
	}
}

function setTitle(label)
{
	if( label == null)
	{
		label = "";
	}
	var title = document.getElementById("titleText");
	if (title == null)
	{
		return;
	}
	var text = title.lastChild;
	if (text == null)
	{
		return;
	}
	text.nodeValue = label;
}

function registerMaximizeListener(name, listener)
{
	maximizeListeners[name]=listener;
}

function notifyMaximizeListeners(maximizedNotRestored)
{
	for(i in maximizeListeners)
	{
		try
		{
			maximizeListeners[i](maximizedNotRestored);
		}
		catch(exc){}
	}
}