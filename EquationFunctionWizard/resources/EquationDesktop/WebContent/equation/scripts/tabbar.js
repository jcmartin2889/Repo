// Current frame (located in tabBarFrame)
var currentFrame = '';

// Popup window when an entry is selected (located in the individual tabsFrame)
var tabwinpopup = null;

// Determine whether these buttons are displayed or not (located in the individual tabsFrame)
var butPageUp = false;
var butPageDn = false;
var butProgressBar = false;

// Position of topmost message for page up/page down (located in the individual tabsFrame) 
var currentPos;

// For scripting
var gblTabFrameScript;
var gblTabFrameFrame;

// Number of fix icons in the tab bar
var consoleIconCount = null;

// **********************************************************************************
// function toggleBottomFrame(frame)
// Processing for toggling the info tab bar
//
// Parameters:
// frame - selected frame
// **********************************************************************************	
function toggleBottomFrames(frame)
{
	if(isUXP())
	{
		return;
	}
	var gblTabFrame = getFrameTab();
	var gblTabFrameset = gblTabFrame.document.getElementById('tabFrameset');
	var gblTabBarFrame = getFrameTabBar();
	var tabButtons = new ArrayList();
	
	if (consoleIconCount== null)
	{
		consoleIconCount = document.getElementById("infoTabBar").cells.length;
	}
	
	// not yet set, do nothing
	gblTabFrameScript = gblTabFrame;
	if (gblTabFrameset==null)
	{
		return;
	}
	
	// Reset all the tabs to unselected
	var obj = document.getElementById("consoleTabText");
	if (obj != null)
	{
		obj.className='tabBarText';
		if (RTL)
		{
			setFieldRTL("consoleTabText");
		}
	}
	obj = document.getElementById("consoleTabImage");
	if (obj != null)
	{
		obj.className='tabBarImage';
		if (RTL)
		{
			setFieldRTL("consoleTabImage");
		}
	}
	obj = document.getElementById("referralsTabText");
	if (obj != null)
	{
		obj.className='tabBarText';
		if (RTL)
		{
			setFieldRTL("referralsTabText");
		}
	}
	obj = document.getElementById("referralsTabImage");
	if (obj != null)
	{
		obj.className='tabBarImage';
		if (RTL)
		{
			setFieldRTL("referralsTabImage");
		}
	}
	obj = document.getElementById("messagesTabText");
	if (obj != null)
	{
		obj.className='tabBarText';
		if (RTL)
		{
			setFieldRTL("messagesTabText");
		}
	}
	obj = document.getElementById("messagesTabImage");
	if (obj != null)
	{
		obj.className='tabBarImage';
		if (RTL)
		{
			setFieldRTL("messagesTabImage");
		}
	}
	obj = document.getElementById("systemTabText");
	if (obj != null)
	{
		obj.className='tabBarText';
		if (RTL)
		{
			setFieldRTL("systemTabText");
		}
	}
	obj = document.getElementById("systemTabImage");
	if (obj != null)
	{
		obj.className='tabBarImage';
		if (RTL)
		{
			setFieldRTL("systemTabImage");
		}
	}
	obj = document.getElementById("joblogTabText");
	if (obj != null)
	{
		obj.className='tabBarText';
		if (RTL)
		{
			setFieldRTL("joblogTabText");
		}
	}
	obj = document.getElementById("joblogTabImage");
	if (obj != null)
	{
		obj.className='tabBarImage';
		if (RTL)
		{
			setFieldRTL("joblogTabImage");
		}
	}
	obj = document.getElementById("commentTabText");
	if (obj != null)
	{
		obj.className='tabBarText';
		if (RTL)
		{
			setFieldRTL("commentTabText");
		}
	}
	obj = document.getElementById("commentTabImage");
	if (obj != null)
	{
		obj.className='tabBarImage';
		if (RTL)
		{
			setFieldRTL("commentTabImage");
		}
	}
	
	// Highlight the right one
	document.getElementById(frame + "TabText").className = 'tabBarTextSelected';
	document.getElementById(frame + "TabImage").className = 'tabBarImageSelected';

	if(frame=='console')
	{
		tabButtons.add(getImageHTML(getLanguageLabel('GBLPROG'),"ProgressBar","../images/EqSpin.gif"));
		tabButtons.add(getImageButtonHTML(getLanguageLabel('GBL000087'),"DeleteAllMessages","javascript:gblTabFrameScript.frames['consoleFrame'].deleteMsgs();","../images/delete.gif")); 
		insertIntoButtonBar(document.getElementById("infoTabBar"),tabButtons.toArray(),false,consoleIconCount,true);
	}
	else if(frame=='referrals')
	{
		// pop on the buttons (none at this time...)
		tabButtons.add(getImageButtonHTML(getLanguageLabel('GBL000088'),"DeleteSelAlerts","javascript:gblTabFrameScript.frames['referralsFrame'].deleteSel();","../images/deletesel.gif")); 
		tabButtons.add(getImageButtonHTML(getLanguageLabel('GBL000087'),"DeleteAllAlerts","javascript:gblTabFrameScript.frames['referralsFrame'].deleteAll();","../images/delete.gif")); 
		insertIntoButtonBar(document.getElementById("infoTabBar"),tabButtons.toArray(),false,consoleIconCount,true);
	}
	else if(frame=='messages')
	{
		// pop on the buttons
		tabButtons.add(getImageHTML(getLanguageLabel('GBLPROG'),"ProgressBar","../images/EqSpin.gif"));
		tabButtons.add(getImageHTML(getLanguageLabel('GBL000065'),"PageUp","../images/EqPageUp_off.gif"));
		tabButtons.add(getImageHTML(getLanguageLabel('GBL000066'),"PageDn","../images/EqPageDn_off.gif"));
		tabButtons.add(getImageButtonHTML(getLanguageLabel('GBLREFR'),"refreshMessagesButton","javascript:gblTabFrameScript.frames['messagesFrame'].refreshMessages();","../images/refresh.gif")); 
		insertIntoButtonBar(document.getElementById("infoTabBar"),tabButtons.toArray(),false,consoleIconCount,true);
	}
	else if(frame=='system')
	{
		// pop on the buttons
		tabButtons.add(getImageHTML(getLanguageLabel('GBLPROG'),"ProgressBar","../images/EqSpin.gif"));
		tabButtons.add(getImageHTML(getLanguageLabel('GBL000065'),"PageUp","../images/EqPageUp_off.gif"));
		tabButtons.add(getImageHTML(getLanguageLabel('GBL000065'),"PageDn","../images/EqPageDn_off.gif"));
		tabButtons.add(getImageButtonHTML(getLanguageLabel('GBLREFR'),"refreshSystemButton","javascript:gblTabFrameScript.frames['systemFrame'].refreshSystem();","../images/refresh.gif"));
		insertIntoButtonBar(document.getElementById("infoTabBar"),tabButtons.toArray(),false,consoleIconCount,true);
	}
	else if(frame=='joblog')
	{
		// pop on the buttons
		tabButtons.add(getImageHTML(getLanguageLabel('GBLPROG'),"ProgressBar","../images/EqSpin.gif"));
		tabButtons.add(getImageHTML(getLanguageLabel('GBL000065'),"PageUp","../images/EqPageUp_off.gif"));
		tabButtons.add(getImageHTML(getLanguageLabel('GBL000065'),"PageDn","../images/EqPageDn_off.gif"));
		tabButtons.add(getImageButtonHTML(getLanguageLabel('GBLREFR'),"refreshJoblogButton","javascript:gblTabFrameScript.frames['joblogFrame'].refreshJobLog();","../images/refresh.gif")); 
		insertIntoButtonBar(document.getElementById("infoTabBar"),tabButtons.toArray(),false,consoleIconCount,true);
	}
	else if(frame=='comment')
	{
		// pop on the buttons
		tabButtons.add(getImageHTML(getLanguageLabel('GBLPROG'),"ProgressBar","../images/EqSpin.gif"));
		tabButtons.add(getImageButtonHTML(getLanguageLabel('GBLREFR'),"refreshCommentButton","javascript:gblTabFrameScript.frames['commentFrame'].refreshComment();","../images/refresh.gif")); 
		insertIntoButtonBar(document.getElementById("infoTabBar"),tabButtons.toArray(),false,consoleIconCount,true);
	}

	// Compose the col value of the frameset
	var colx = "";
	for (var i = 0; i<gblTabFrameset.children.length; i++)
	{
		if (gblTabFrameset.children[i].name == frame+"Frame")
		{
			if (i == 0)
			{
				colx = colx + "*";
	        }
			else
			{
				colx = colx + ",*";     
	        }
		}
		else
		{
			if (i == 0)
			{
				colx = colx + "0";
			}
			else
			{
				colx = colx + ",0";
			}
		}
	}
	gblTabFrameset.cols = colx;
	
	// Redisplay buttons
	gblTabBarFrame.currentFrame = frame;
	redisplayButtons(frame);
}	


// **********************************************************************************
// function setFlashingTab(frame, color, interval)
// Simulate flashing tab
//
// Parameters:
// frame     - tab frame to flash
// color     - 1 (selected or 0 (not selected)
// iteration - number of times to execute this command
// flashCtrl - control
//           - 0 - Only stop flashing when iteration reaches 0 (or negative)
//           - 1 - Immediately stop flashing when tab is selected (or when iteration reaches 0)
//           - 2 - Flash for at least X times (iteration) and then continue flashing till tab is selected
// interval  - time interval to lights up or out
// **********************************************************************************	
function setFlashingTab(frame, color, iteration, flashCtrl, interval)
{
	if(isUXP())
	{
		return;
	}
	var gblTabFrame = getFrameTab();
	var gblTabFrameset = gblTabFrame.document.getElementById('tabFrameset');
	var gblTabBarFrame = getFrameTabBar();
	var docID = gblTabBarFrame.document;
	
	// Stop flashing, when iteration is 0
	if (flashCtrl==0 || flashCtrl==1)
	{
		if (iteration<=0)
		{ 
			return;
		}
	}
	
	// Stop flashing, when tab is selected
	if (flashCtrl==1 || (iteration<=0 &&flashCtrl==2) )
	{
		if (docID.getElementById(frame + "TabImage").className.indexOf('tabBarImageSelected') >= 0)
		{
			return;
		}
	}
	
	// flash
	var className = docID.getElementById(frame + "TabText").className;
	if (color==1)
	{
		className = className + ' tabBarFlash';
		color = 0;
	}
	else
	{
		className = className.replace(' tabBarFlash','');
		color = 1;
	}
	docID.getElementById(frame + "TabText").className = className;
	
	// set timeout again
	iteration = iteration - 1;
	setTimeout( function() { setFlashingTab(frame, color, iteration, flashCtrl, interval); }, interval);
}


// **********************************************************************************
// function getTabInfoFromServDir(serviceMethod, frame, sessionParms, elementLoc)
// Processing to invoke the service directory and display it within the frame
//
// Parameters:
// serviceMethod     - the method to invoke
// sessionParms      - array of parameters
// frame             - frame
// elementLoc        - element to receive the string (elementLoc.innerHTML)
// **********************************************************************************	
function getTabInfoFromServDir(serviceMethod,sessionParms,frame,elementLoc)
{
	var gblTab0Frame = getFrameTab();
	var gblTabBarFrame = getFrameTabBar();
	var gblTabFrame = gblTab0Frame.frames[frame + 'Frame'];

	// Display the progress bar
	butProgressBar = true;
	gblTabBarFrame.document.getElementById('ProgressBar').style.display = '';
		
	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName(serviceMethod,nsuri);
	var qn_op_resp = new WS.QName(serviceMethod + 'Response',nsuri);
	
	var currFrame = getFrameCurrent();
	
	if(currFrame.sessionType == currFrame.SESSION_DIRECT_TRANS_DESKTOP && getCookie("jobNumber") == "")
	{			
		var jobDetails = currFrame.jobDetails;
		var jobName = jobDetails.substr(0,10).trim();
		var jobUser = jobDetails.substr(10,10).trim();
		var jobNumber = jobDetails.substr(20).trim();
		
		currFrame.setJobName(jobName, jobUser, jobNumber);
		
		for(var i=0, length=sessionParms.length; i < length; i++)
		{	
			switch(sessionParms[i].name)
			{
				case "jobName" 		: sessionParms[i].value = jobName; break;
				case "jobUser" 		: sessionParms[i].value = jobUser; break;				
				case "jobNumber" 	: sessionParms[i].value = jobNumber; break;
			}
		}
	}	

	// Call the web service
	call.invoke_rpc(
						qn_op,
						sessionParms,
						null,
						function(call,envelope) 
						{
							// get the returned value
							var htmlFormat = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();
							
							// remove the control character
							var a = htmlFormat.split('*x*x*x*');
							
							// determine the buttons
							if (a.length==2) 
							{
								setButtons(frame,a[0]);
							}

							// put the returned value to the element
							var objElement = gblTabFrame.document.getElementById(elementLoc);
							if (a.length==2) 
							{
								objElement.innerHTML = a[1];
							}
							else
							{
								objElement.innerHTML = a[0];
							}
							
							// redisplay buttons
							butProgressBar = false;
							redisplayButtons(frame);
						}
					);
}


// **********************************************************************************
// function getTabWindowFromServDir(serviceMethod, sessionParms, frame)
// Processing to invoke the service directory and display it as a popup within the frame
//
// Parameters:
// serviceMethod     - the method to invoke
// sessionParms      - array of parameters
// frame             - frame
// elementLoc        - anchor
// **********************************************************************************	
function getTabWindowFromServDir(serviceMethod,sessionParms,frame,elementLoc)
{
	var gblTabBarFrame = getFrameTabBar();

	// Display the progress bar
	butProgressBar = true;
	showProgressBar();
		
	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName(serviceMethod,nsuri);
	var qn_op_resp = new WS.QName(serviceMethod + 'Response',nsuri);

	// Call the web service
	call.invoke_rpc(
						qn_op,
						sessionParms,
						null,
						function(call,envelope) 
						{
							var htmlFormat = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();
							openWindow(htmlFormat,frame,elementLoc);
							butProgressBar = false;
							hideProgressBar();
						}
					);
}


// **********************************************************************************
// function openWindow(html,frame,elementLoc)
// Processing to display the html in a popup
//
// Parameters:
// html			- html
// frame        - frame
// elementLoc   - anchor, where popup will appear
// **********************************************************************************	
function openWindow(html,frame,elementLoc)
{
	var gblTab0Frame = getFrameTab();
	var gblTabFrame = gblTab0Frame.frames[frame + 'Frame'];
	
	//var arg = 'height=400, width=400, scrollbars=yes, resizable=no, toolbar=no, menubar=no, location=no, status=no, directories=no, ';
	//var win = window.open('template.jsp','',arg);

	// construct the html
	var gblClose = getLanguageLabel('GBLCLOSE');
	html = 	"<div class='tabDiv'>" + 
	'		<table class="widgetFooter" id=frqWidgetFooter cellpadding="0" cellspacing="0">'+
	'			<tr>'+
	'				<td class="labelText" align="left">' + getLanguageLabel('GBL000046') + '</td>'+
	'				<td class="labelText" align="right" width="100%">&nbsp;</td>'+
	'				<td class="button" align="right">'+
	'					<a href="javascript:tabwinpopup.hidePopup()">'+
	'					<img src=\'../images/EQExit.gif\' border=\'0\' alt=\'' + gblClose + '\' hover=\'' + gblClose + '\'  >' +
	'					</a>'+
	'				</td>'+
	'			</tr>'+
	'		</table>'+
			html + 
			"</div>";
	
	// Create a Div for the Popup		
	var div = document.createElement('div');		
	div.id = "PopupId";
	div.className = "widgetContainerDIV";				
	document.body.appendChild(div);	
	div.style.width = gblTabFrame.frameElement.offsetWidth * 0.80 + "px";
	
	tabwinpopup = new PopupWindow("PopupId");
	tabwinpopup.offsetX=0;
	tabwinpopup.offsetY=0;
	tabwinpopup.autoHide();
	tabwinpopup.populate(html);

	if (isUXP())
	{
		tabwinpopup.showPopupBelow(elementLoc);
	}
	else
	{
		tabwinpopup.showPopup(elementLoc);
	}
}



// **********************************************************************************
// function setButtons(frame,ctl)
// Determine whether page up/down are enabled or not
//
// Parameters:
// frame - frame
// ctl   - status (see EqInfo - addStatus())
// **********************************************************************************	
function setButtons(frame,ctl)
{
	// later messages (page up)
	if (ctl.substring(0,1) == 'Y')
	{
		butPageUp = true;
	}
	else
	{
		butPageUp = false;
	}
	
	// older messages (page down)
	if (ctl.substring(1,2) == 'Y')
	{
		butPageDn = true;
	}
	else
	{
		butPageDn = false;
	}
	
	// position of topmost message
	currentPos = ctl.substring(2,10).trim();
}


// **********************************************************************************
// function redisplayButtons(frame)
// Display/hide buttons
//
// Parameters:
// frame - frame
// **********************************************************************************	
function redisplayButtons(frame)
{
	var gblTab0Frame = getFrameTab();
	var gblTabBarFrame = getFrameTabBar();
	var gblTabFrame = gblTab0Frame.frames[frame + 'Frame'];
	var obj, str;
	
	// only update when viewing this frame!
	gblTabFrameFrame = gblTabFrame;
	if(frame!=gblTabBarFrame.currentFrame)
	{
		return;
	}

	// Progress bar
	obj = gblTabBarFrame.document.getElementById('ProgressBar');
	if (obj != null && !gblTabFrame.butProgressBar)
	{
		obj.style.display = 'none';
	}

	// Page up
	obj = gblTabBarFrame.document.getElementById('PageUp');
	if (obj != null)
	{
		if (gblTabFrame.butPageUp) 
		{
			str = getImageButtonHTML(getLanguageLabel('GBL000065'),"PageUp","javascript:gblTabFrameFrame.nextPage('" + frame + "','1');","../images/EqPageUp.gif");
		}
		else
		{
			str = getImageHTML(getLanguageLabel('GBL000065'),"PageUp","../images/EqPageUp_off.gif");
		}
		str = str.replace('£x£','');		
		obj.outerHTML = str;
	}
		
	// Page down
	obj = gblTabBarFrame.document.getElementById('PageDn');
	if (obj != null)
	{
		if (gblTabFrame.butPageDn) 
		{
			str = getImageButtonHTML(getLanguageLabel('GBL000066'),"PageDn","javascript:gblTabFrameFrame.nextPage('" + frame + "','2');","../images/EqPageDn.gif");
		}
		else
		{
			str = getImageHTML(getLanguageLabel('GBL000066'),"PageDn","../images/EqPageDn_off.gif");
		}
		
		str = str.replace('£x£','');		
		obj.outerHTML = str;
	}
}

// **********************************************************************************
// function nextPage(frame,dir)
// Perform page up / page down processing
//
// Parameters:
// frame - frame where paging occured
// dir   - 1 pageup, 2 pagedown
// **********************************************************************************	
function nextPage(frame,dir)
{
	if (frame=='messages')
	{
		refreshMsgQueueDir(frame,dir,currentPos);
	}
	else if (frame=='system')
	{
		refreshMsgQueueDir(frame,dir,currentPos);
	}
	else if (frame=='joblog') 
	{
		refreshJobLogDir(dir,currentPos);
	}
}


//**********************************************************************************
// function tabHidePopup()
// Hide all the popups
//
// Parameters:
// None
//**********************************************************************************	
function tabHidePopup()
{
	if (tabwinpopup != null)
	{
		tabwinpopup.hidePopup();
	}
}


//**********************************************************************************
// function showProgressBar()
// Show the progress bar
//
// Parameters:
// None
//**********************************************************************************	
function showProgressBar()
{
	var gblTabBarFrame = getFrameTabBar();
	var obj = gblTabBarFrame.document.getElementById('ProgressBar');
	if (obj != null)
	{
		obj.style.display = '';
	}
}


//**********************************************************************************
// function hideProgressBar()
// Hide the progress bar
//
// Parameters:
// None
//**********************************************************************************	
function hideProgressBar()
{
	var gblTabBarFrame = getFrameTabBar();
	var obj = gblTabBarFrame.document.getElementById('ProgressBar');
	if (obj != null)
	{
		obj.style.display = 'none';
	}
		
}