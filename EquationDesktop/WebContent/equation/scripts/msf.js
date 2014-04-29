var LTR_OVERRIDE = '\u202D';	// Force Left to Right 
// **********************************************************************************
// function setMsgCtl(arrMsgf, msgID)
// Function to handle display of message control
//
// Parameters:
// arrMsgf - array of messages (hardcoded in the widget as allItems)
// msgID   - ID of the message control (hardcoded in the widget as l1r24)
// **********************************************************************************	
function setMsgCtl(arrMsgf, msgID, secondLevelTexts) 
{
	// Enable re-ordering of Arabic text in WebFacing messages
	if(msgID == 'l1r24') // Called from WebFacing widget
	{
		var msgIndex;
		for(msgIndex=0; msgIndex<arrMsgf.length;msgIndex++)
		{
			var msgItem = arrMsgf[msgIndex];
			if( hasAnyRtl(msgItem) && msgItem.charAt(0) != LTR_OVERRIDE)
			{
				arrMsgf[msgIndex]= LTR_OVERRIDE + msgItem;
			}
		}
	}
	
	try
	{
		gblTabFrame = getFrameTab();
		gblConsoleFrame = gblTabFrame.frames['consoleFrame'];
		var docID = gblConsoleFrame.document;
		var divID = docID.getElementById('consoleDiv');
		var msgIDObj = document.getElementById(msgID);
		var textHTML = new String();
		var msgfHTML = new String();
		var UXPmode = isUXP();
	
		// no messages to be displayed
		msgctl_ID_obj = null;
		if((typeof arrMsgf == "undefined") || arrMsgf == null)
		{
			divID.innerHTML = ' ';
			if (UXPmode == true)
			{
				setUXPProblemAreaVisible(false);
			}
			return;
		}

		if (UXPmode == true)
		{
			setUXPProblemAreaVisible(true);
		}

		
		// no second level text
		if((typeof secondLevelTexts == "undefined") || secondLevelTexts == null)
		{
			secondLevelTexts = null;
		}
	
		// make the IBM message control invisible
		msgIDObj.style.visibility = 'hidden';
		  
		// display all the messages
		for(i=0; i<arrMsgf.length; i++)
		{
			secondLevel = '';
			if (secondLevelTexts != null && i < secondLevelTexts.length)
			{
				secondLevel = secondLevelTexts[i];
			}
			
			msgfHTML += getMsgCtlHTML(gblConsoleFrame,arrMsgf[i],secondLevel,i);
		}

		var tableClass = 'consoleMessageTable';
		if (gblConsoleFrame.RTL)
		{
			tableClass += ' wf_LTOR wf_RIGHT_ALIGN';
		}
		textHTML = '<table id="msgCtlTable" class="' + tableClass + '">' + msgfHTML + '</table>';
		divID.innerHTML = textHTML;
		alertNewMsgCtl();
	}
	catch (e)
	{
		// Do nothing, the page probably had not finished loading
	}
}

//**********************************************************************************
// function addMessageCtl(msg,secondLevel)
// Add a message in the console tab
//
// Parameters:
// msg          - message to display
// secondLevel - additional message
//**********************************************************************************	
function addMessageCtl(msg,secondLevel)
{
	try
	{
		gblTabFrame = getFrameTab();
		gblConsoleFrame = gblTabFrame.frames['consoleFrame'];
		var docID = gblConsoleFrame.document;
		var divID = docID.getElementById('consoleDiv');
		var tableId = docID.getElementById('msgCtlTable');
		
		// table id not found
		var exist = true;
		if (tableId == null)
		{
			exist = false;
			i = 1;
		}
		else
		{
			i = tableId.rows.length + 1;
		}
	
		html = getMsgCtlHTML(gblConsoleFrame,msg,secondLevel,i);
		
		if (exist)
		{
			// search for </table>
			i = tableId.outerHTML.indexOf("</TABLE>");
			if (i==-1)
			{
				i = tableId.outerHTML.length;
			}
			tableId.outerHTML = tableId.outerHTML.substring(0,i) + html + tableId.outerHTML.substring(i+8);
		}
		else
		{
			if (gblConsoleFrame.RTL)
			{
				textHTML = '<TABLE id="msgCtlTable" class="wf_LTOR wf_RIGHT_ALIGN">' + html + '</TABLE>';
			}
			else
			{
				textHTML = '<table id="msgCtlTable">' + html + '</table>';
			}
			divID.innerHTML = textHTML;
		}
	}
	catch (e)
	{
		// Do nothing, the poage probably had not finished loading
	}
}


//**********************************************************************************
// function getMsgCtlHTML(msg,secondLevel,counter)
// Return the HTML equivalent of a message
//
// Parameters:
// consoleTab   - the console tab to display the message
// msg          - message to display
// secondLevel - additional message
//**********************************************************************************	
function getMsgCtlHTML(consoleTab,msg,secondLevel,counter)
{
  	onClickAction = "javascript:showMsgFile('" + convertToJavaScript(msg.rplCtl()) + "','" + counter + "','" + secondLevel + "');";

	if (consoleTab.RTL)
	{
		var charCode = msg.charCodeAt(0);
		
		// invalid char code?
		if(charCode!=128 && charCode!=130 && charCode!=131)
		{
			charCode = msg.charCodeAt(msg.length-8);
		}
		
	  	html = '<tr>' +
  				'<td class="consoleTextCell"><a class="consoleStyle" href="' + onClickAction + '">' +
  				msg.rplCtl() +
  				'</a></td>' +
  				'<td class="consoleImageCell"><a href="' + onClickAction + '">' +
  				'<img class="consoleImage" id="img' + counter + '" src ="' + 
  					ctrCharCodeToImage(charCode) + '" ' +
  								'>' +
  				'</a></td>';
	}
	else
	{	
	  	html = '<tr>' +
  				'<td class="consoleImageCell"><a class="consoleAnchor" href="' + onClickAction + '">' +
  				'<img class="consoleImage" id="img' + counter + '" src ="' + 
  					ctrCharCodeToImage(msg.charCodeAt(0)) + '" ' +
  								'>' +
  				'</a></td>' +
  				'<td class="consoleTextCell"><a class="consoleStyle" href="' + onClickAction + '">' +
  				msg.rplCtl() +
  				'</a></td>';
  	}
	
	return html;
}


// **********************************************************************************
// function showMsgFile(msgd)
// Processing to display additional message file info
//
// Parameters:
// msgd - message displayed
// **********************************************************************************	
function showMsgFile(msgd,msgPos,secondLevel)
{
	var sessionIdentifier = getSessionId();
	
	// retrieve via using services
	var sessionParms = 	[
							{name:'sessionIdentifier',value:sessionIdentifier},
							{name:'msgd',value:msgd},
							{name:'secondLevel',value:secondLevel}
						];
	getTabWindowFromServDir('getMsgFileEntryHTML',sessionParms,'console', 'img' + msgPos);
}


// **********************************************************************************
// function ctrCharCodeToImage(ctrCharCode)
// Map the special control character into its equivalent image
//
// Parameters:
// ctrCharCode - control character
// **********************************************************************************	
function ctrCharCodeToImage(ctrCharCode)
{
	// Map ctrCharCode to a message severity
	var msgSev;
	switch(ctrCharCode) 
	{
	  	// info
	  	case 128:
			msgSev = 0;
	  		break;
	  	// warning
	  	case 130:	// x22
			msgSev = 10;
			break;
	  	// default
	  	default:
			msgSev = 20;
			break;
	}
	// Use the message severity to obtain image
	return msgSevToImage(msgSev);
}


// **********************************************************************************
// function msgSevToImage(ctrCharCode)
// Map the severity code into its equivalent image
//
// Parameters:
// messageSev - message severity
// **********************************************************************************	
function msgSevToImage(messageSev)
{
	switch(messageSev)
	{	
	  	// info
	  	case 0:
	  		return isUXP() ? '../images/information.png' :  '../images/EQInfo.gif'; 
	  	// warning
	  	case 10:
			return isUXP() ? '../images/warning.png' :  '../images/EQWarning.gif'; 
	  	// default
	  	default:
			return isUXP() ? '../images/error.png' :  '../images/EQError.gif'; 
	  }
}


// **********************************************************************************
// function addMsgCtl(messageSev, messageText)
// Processing to display additional message file info
//
// Parameters:
// message severity - message severity
// message text     - error message
// **********************************************************************************	
function addMsgCtl(messageSev, messageText)
{
	var gblConsoleFrame = getFrameTab().frames['consoleFrame'];
	var docID = gblConsoleFrame.document;
	var divID = docID.getElementById('consoleDiv');
	var textHTML = new String();
	var msgfHTML = new String();

	var imgCell = '<td class="consoleImageCell"><img class="consoleImage" src ="' + msgSevToImage(messageSev) + '" ' + '></td>';
	var textCell = '<td class="consoleTextCell"><span class="consoleText">' + messageText + '</span></td>';
		
	var lhs = gblConsoleFrame.RTL ? textCell : imgCell;
	var rhs = gblConsoleFrame.RTL ? imgCell : textCell;

  	msgfHTML += '<tr>' + lhs + rhs + '</tr>' ;

	var tblClass = "consoleMessageTable";
	if( gblConsoleFrame.RTL )
	{
		tblClass += " wf_RIGHT_ALIGN";
	}

	textHTML = '<table class="' + tblClass + '">' + msgfHTML + '</table>';
	divID.innerHTML = textHTML + divID.innerHTML;
	alertNewMsgCtl();
	
	// In UXP make sure the problem area is shown
	if (isUXP() == true)
	{
		setUXPProblemAreaVisible(true);
	}

}


// **********************************************************************************
// function alertNewMsgCtl()
// Processing to alert the user of messages in the message control
//
// Parameters:
// None
// **********************************************************************************	
function alertNewMsgCtl()
{
	// lights up or lights out
	setFlashingTab('console',1, 2, 2, 400);
}

// **********************************************************************************
// function alertNewReferral()
// Processing to alert the user of messages in the referral tab
//
// Parameters:
// None
// **********************************************************************************	
function alertNewReferral()
{
	// lights up or lights out
	setFlashingTab('referrals',1, 2, 2, 400);
}



// **********************************************************************************
// function setCON(idField, Severity)
// Processing to display field in the console frame
//
// Parameters:
// idField = Field name
// severity = Message severity
// **********************************************************************************	
function setCON(idField, sev)
{
	if (sev==null) 
	{
		sev = getStrSev(getValue(idField));
	}
	addMsgCtl(sev,removeCtrlChar(getValue(idField)));
}