var gblButtonbarFrame = getFrameButtonBar();
var gblCurrentFrame = getFrameCurrent();
var functionKeysList = null;

//**********************************************************************************
// Function key
//**********************************************************************************
function FunctionKey()
{
	this.title = '';
	this.allFunctionKeys = new ArrayList();
	this.allOptionKeys = new ArrayList();
	this.commonBottomButtons = new ArrayList();
	this.otherBottomButtons = new ArrayList();
	this.butsCommon = new ArrayList();
	this.butsNavi = new ArrayList();
	this.butsTran = new ArrayList();
	this.butsOther = new ArrayList();
	this.cmdKeyLnIn1ID='';
	this.cmdKeyLnIn2ID='';
	this.titleInID='';
	this.strSflNPgUpDnID='';
}


// **********************************************************************************
// GET IMAGE BUTTON HTML
// **********************************************************************************
// . (**LANGUAGE)

getImageButtonHTML = function(hoverText,labelText,onClickAction,imagePath, className) 
{
	var imageButtonHTML = new String();
	labelText = labelText.trim();
	hoverText = hoverText.trim();
	
	if (className==null)
	{
		className = '';
	}
	
	imageButtonHTML =	'<a tabindex="-1" href="javaScript:' + onClickAction + 
						';" name="' + labelText + 
						'" id="' + labelText + 
						'" class="' + className + 
						'">' +
								'<img src="' + imagePath + '" alt="' + hoverText + '" title="' + hoverText + '" id="' + labelText + '" border="0">' +
						'</a>';
	return imageButtonHTML;
};


getImageHTML = function(hoverText,labelText,imagePath)
{
	var imageButtonHTML = new String();
	labelText = labelText.trim();
	hoverText = hoverText.trim();
	imageButtonHTML = '<img src="' + imagePath + '" ' +  
							'alt="' + hoverText + '" ' +
							'class="' + 'buttonDisabled' + '" ' +
							'title="' + hoverText + '" ' +
							'id="' + labelText + '" ' +
							'border="0">';
	return imageButtonHTML;
};


getDivButtonHTML = function(hoverText,labelText,onClickAction,className) 
{
	var imageButtonHTML = new String();
	labelText = labelText.trim();
	hoverText = hoverText.trim();
	
	if (className==null)
	{
		className = '';
	}
	
	imageButtonHTML =	'<a tabindex="-1" href="javaScript:' + onClickAction + 
						';" name="' + labelText + 
						'" id="' + labelText + 
						'">' +
								'<div class="' + className + '" ' +
								     'title="' + hoverText + '" ' +
								'>'; 
								'</div>' +
						'</a>';
	return imageButtonHTML;
};

// **********************************************************************************
// GET TEXT BUTTON HTML
// **********************************************************************************
// . (**LANGUAGE)

getTextButtonHTML = function(hoverText,labelText,onClickAction,imagePath, buttonClass)
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

	textButtonHTML =	'<input type="button" class="' + buttonClass + '" onclick="' + onClickAction + ';" name="' + labelText + '" id="' + labelText + '" value="' + labelText + '" title="' + hoverText + '">' +
						'</input>';
	
	return textButtonHTML;
};

//**********************************************************************************
//function getTextDisabledButtonHTML
//Returns a disabled button HTML string 
//**********************************************************************************
getTextDisabledButtonHTML = function(hoverText,labelText,onClickAction,imagePath,buttonClass)
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
	
	textButtonHTML =	'<input disabled type="button" class="' + buttonClass + '" onclick="' + onClickAction + ';" name="' + labelText + '" id="' + labelText + '" value="' + labelText + '" title="' + hoverText + '">' +
						'</input>';
	return textButtonHTML;
};

//**********************************************************************************
//function getBottomBarButtonHTML
//Returns a  button HTML string for the button bar. Unlike other button text methods
//this defaults to using a non-fixed width class. If the button is narrower than 
//the standard width when created, it will be modified to use the fixed width
//**********************************************************************************
getBottomBarButtonHTML = function(hoverText,labelText,onClickAction,imagePath,isDisabled)
{
	var textButtonHTML;
	var disabledText = isDisabled ? "disabled " : " ";
	labelText = labelText.trim();
	hoverText = hoverText.trim();
	
	textButtonHTML =	'<input ' + disabledText + 'type="button" class="inputButtonExt" onclick="' + onClickAction + ';" name="' + labelText + '" id="' + labelText + '" value="' + labelText + '" title="' + hoverText + '">' +
						'</input>';
	return textButtonHTML;
};


//**********************************************************************************
//function getAnchorHTML
//Returns an anchor HTML string 
//**********************************************************************************
getAnchorHTML = function(labelText,hoverText,onClickAction,anchorClass)
{
	var anchorHTML = new String();
	if (anchorClass == null || anchorClass == '') 
	{
		anchorClass = 'functionKeysAnchor';
	}
	anchorHTML 	 = '<a class="'+ anchorClass + '" title="'+ hoverText +'" href="javaScript:' + onClickAction + '">' +
				labelText.trim() + '</a>';
	return anchorHTML;
};

//**********************************************************************************
//function getSpanHTML
//Returns a span HTML string 
//**********************************************************************************
getSpanHTML = function(labelText,labelStyle,hoverText)
{
	var spanHTML = new String();
	spanHTML 	 = '<span title="'+ hoverText +'" class="' +labelStyle+ '">'+ labelText +'</span>';
	return spanHTML;
};

// **********************************************************************************
// INSERT BUTTONS INTO THE BUTTON BAR
// **********************************************************************************
insertIntoButtonBar = function(buttonBar,buttonList,separator,fromIndex,flush)
{
	// loop through the common buttons
	var buttonArray = buttonList;
	var buttonSize = buttonArray.length;
	var buttonSeq = -1;
	var button;
	var index = 2;

	if (fromIndex != null)
	{
		index = fromIndex;
	}
	
	if (flush != null)
	{
		if (flush)
		{
			while (buttonBar.cells.length > index) 
			{
				// why the array index isn't the cell index is beyond my ken, but hey-ho...
				buttonBar.deleteCell(buttonBar.cells[index].cellIndex);
			}
		}
	}

	// add vertical line
	if (buttonSize>1 && separator)
	  {
		var vertLine = buttonBar.insertCell(2);
		vertLine.innerHTML = "<table></table>";
		vertLine.style.borderRight = "1px solid #C0C0C0";
	  }

	// add all the buttons
	for (buttonSeq = 0;buttonSeq < buttonSize; buttonSeq++) 
	{
		button = buttonBar.insertCell(buttonSeq + index);
		button.className="button";
		if (gblButtonbarFrame.RTL)
		{
			button.className+=' wf_LTOR wf_RIGHT_ALIGN';
		}
		button.align="center";
		button.innerHTML=buttonArray[buttonSeq];
	}
};

//**********************************************************************************
//function populateCustomButtonBar
//Allows us to place the custom buttons defined in custom JSPs into the generic button place holder
//
//Parameters:
//buttonbarDivId 
//customButtonBarDivId       
//
//Returns:
//None
//**********************************************************************************	
function populateCustomButtonBar(buttonbarDivId, customButtonBarDivId)
{	
	var gblBottomBar = getFrameBottomBar();
	var div = gblBottomBar.document.getElementById(buttonbarDivId);
	var customDiv = document.getElementById(customButtonBarDivId);
	div.innerHTML = customDiv.innerHTML; 
	customDiv.style.visibility = 'hidden'; 
	div.style.visibility = 'visible'; 
}

// **********************************************************************************
// function populateButtonBar
// Setup the button bar in the Equation Desktop
//
// Parameters:
// buttonbarID - ID of the button bar element
// title       - title
// butsCommon  - common buttons
// butsNavi    - navigation buttons
// butsTran    - transactional buttons
// butsOther   - other buttons
//
// Returns:
// None
// **********************************************************************************	
function populateButtonbar(buttonbarId, title, butsCommon, butsNavi, butsTran, butsOther)
{		
	// Now we have a fixed jsp for the content we need to 'clear' it before we repopulate all the stuff
	// or we'll get multiple rows.
	
	// Frame not yet loaded, then ignore
	if (gblButtonbarFrame==null) 
	{
		gblButtonbarFrame = getFrameButtonBar();
	}
	
	// Get the table
	var table = gblButtonbarFrame.document.getElementById(buttonbarId);
	if (table==null)
	{
		return;
	}

	// delete all rows
	for(i=0; i<table.rows.length; i++)
	{
		table.deleteRow(0);
	}

	// Get the table we will be using as the buttonbar
	var buttonBar=table.insertRow(0);
	
	// Pop on the title
	var titleBar = buttonBar.insertCell(0);
	titleBar.id = "buttonBarTitle";
	titleBar.className='buttonBarTitle';
	
	if (isUXP())
	{
		table.className='';
		titleBar.className='buttonBarTitleUXP';
	}
	
	if (gblButtonbarFrame.RTL) 
	{
		titleBar.className+=' wf_LTOR wf_RIGHT_ALIGN';
	}
	titleBar.align='center';

	if (isWebFacing() && getWidgetInnerHTML(functionKeysList.titleInID)!= title)
	{
		//force RLE using leading RLE and trailing PDF control characters
		title = '\u202A' + getWidgetInnerText(functionKeysList.titleInID).rpl160s() + '\u202C';
	}

	// Title is blank and UXP, then retrieve the title from the UXP tab
	if (isWebFacing() && isUXP() && title.trim().length==0)
	{
		title = getEquationTabTitle(false);
	}

	// Set the title
	titleBar.innerHTML=title;
	
	if ((pgmId + pgmLvl + pgmFuncMode).trim() != '')
	{
		titleBar.title= pgmId + ' ' + pgmLvl + ' ' + pgmFuncMode;
	}

	// for UXP
	if (isUXP())
	{
		// Driver screen for WebFacing, then reset the title
		if (isWebFacing() && eqDriver == 'Y' && window.top.equxp != null)
		{
			try
			{
				window.top.equxp.updateEquationTabTitle(window.top.equxp.webFacingTabName, "", getLanguageLabel('GBL900102'));
			} catch (e)
			{
			}
			// Don't add buttons in UXP if the driver screen is shown
			return;
		}
		
	}
	
	// Pop in the filler
	var fillerBar = buttonBar.insertCell(1);
	if (!isUXP())
	{
		fillerBar.className='buttonBarFiller';
	}
	fillerBar.align='right';
	fillerBar.width='100%';
	
	// loop through the common buttons
	insertIntoButtonBar(buttonBar,butsCommon,false);
	insertIntoButtonBar(buttonBar,butsNavi,true);
	insertIntoButtonBar(buttonBar,butsTran,true);	
	insertIntoButtonBar(buttonBar,butsOther,true);
}


// **********************************************************************************
// COMMAND KEY TWEAKER
// **********************************************************************************
// . (**LANGUAGE)
function setFunctionKeyButtons(cmdKeyLnIn1,cmdKeyLnIn2,titleIn,strSflNPgUpDn)
{	
	// get the current frame
	gblCurrentFrame = getFrameCurrent();
	
	// set the current frame of the buttonbar frame
	getFrameButtonBar().gblCurrentFrame= gblCurrentFrame;
	getFrameBottomBar().gblCurrentFrame= gblCurrentFrame;
	
	// initialise function key
	functionKeysList = new FunctionKey();
	
	if (functionKeysList.cmdKeyLnIn1ID==''||
			functionKeysList.cmdKeyLnIn2ID==''||
			functionKeysList.titleInID==''||
			functionKeysList.strSflNPgUpDnID==''
		)
	{

		functionKeysList.cmdKeyLnIn1ID=cmdKeyLnIn1;
		functionKeysList.cmdKeyLnIn2ID=cmdKeyLnIn2;
		functionKeysList.titleInID=titleIn;
		functionKeysList.strSflNPgUpDnID=strSflNPgUpDn;

	}

	// setup the array for record format (subfile)
	var arrSflNPgUpDn = strSflNPgUpDn.split(',');
	
	var cmdKeyLnIn = new String();
	var cmdKeyLbl = new String();
	var cmdKeyAct = new String();
	var cmdKeyHvr = new String();
	var cmdKeyImg = new String();
	var currentE = -1;
	var currentEnd = -1;
	var currentStart = -1;
	var nextE = -1;
	var myposE = 0;
	var fkey = new String();
	var fkey2 = new String();
	var botsOther = new ArrayList();
	var botsLinked = new ArrayList();
	var cmdKeyHdr = new String();
	var cmdKeyHelp = new String();
	var cmdKeyCheck = new String();
	var cmdKeyBack = new String();
	var cmdKeyNext = new String();
	var cmdKeyExit = new String();
	var cmdKeySubmit = new String();
	var cmdKeyCancel = new String();
	var cmdKeyAdd = new String();
	var cmdKeyDel = new String();
	var cmdKeyWarn = new String();
	var cmdKeyView = new String();
	var cmdKeyToggle = new String();
	var cmdKeyAccept = new String();
	var cmdKeySave = new String();
	var cmdKeySave2 = new String();
	var cmdKeyPrint = new String();
	var cmdKeyPrint2 = new String();
	var cmdKeyExcel = new String();
	var cmdKeyF7 = new String();
	var cmdKeyF8 = new String();
	var cmdKeyF12 = new String();
	var cmdKeyToggleFunc = new String();
	var proceed = true;
	var langId = getLanguageId();


	// Command key 1, sometimes we pass the plain text
	if (cmdKeyLnIn1.indexOf("$")==-1)
	{
		cmdKeyLnIn = cmdKeyLnIn1.rpl160s();
	}
	else
	{
		var obj = document.getElementById(cmdKeyLnIn1);
		if (obj == null)
		{
		}
		else if (obj.innerText)
		{
			cmdKeyLnIn = obj.innerText.rpl160s();
		}
		else
		{
			cmdKeyLnIn = obj.innerHTML.rplNbsp().rpl160s();
		}
	}

	// Command key 2
	if (cmdKeyLnIn2.indexOf("$")==-1)
	{
		cmdKeyLnIn += '  ' + cmdKeyLnIn2.rpl160s();
	}
	else
	{
		var obj = document.getElementById(cmdKeyLnIn2);
		if (obj == null)
		{
		}
		else if (obj.innerText)
		{
			cmdKeyLnIn += '  ' + obj.innerText.rpl160s();
		}
		else
		{
			cmdKeyLnIn +=  '  ' + obj.innerHTML.rplNbsp().rpl160s();
		}
	}

	// Program title
	if (document.getElementById(titleIn) == null)
	{
		if (pgmTitle != null) 
		{
			functionKeysList.title = pgmTitle.rpl160s().rplHyphens().trim().nbsp();
		}
	}
	else if (document.getElementById(titleIn).lastChild != null)
	{
		functionKeysList.title = document.getElementById(titleIn).lastChild.data.rpl160s().rplHyphens().trim().nbsp();
	}

	// Setup default disabled buttons if not UXP. For UXP the buttons should not be shown if not enabled.
	if (!isUXP())
	{
		cmdKeyCheck  = getImageHTML(getLanguageLabel('GBLREFR'), getLanguageLabel('GBLREFR'), '../images/refresh_off.gif');
		cmdKeyCancel = getImageHTML(getLanguageLabel('GBLCAN'), getLanguageLabel('GBLCAN'),  '../images/cancel_off.gif');
		cmdKeyExit  = getImageHTML(getLanguageLabel('GBLEXIT'), getLanguageLabel('GBLEXIT'), '../images/EQExit_off.gif');
		cmdKeySave = getImageHTML(getLanguageLabel('GBLSAVE'), getLanguageLabel('GBLSAVE'),  '../images/save_off.gif');
		cmdKeySave2 = getImageHTML(getLanguageLabel('GBL000090'), getLanguageLabel('GBL000090'),  '../images/savetmplt_off.gif');
		cmdKeyF7 = getBottomBarButtonHTML(getLanguageLabel('GBL000065'),getLanguageLabel('GBL000065'),'','', true);
		cmdKeyF8 = getBottomBarButtonHTML(getLanguageLabel('GBL000066'),getLanguageLabel('GBL000066'),'','', true);
		cmdKeyF12 = getBottomBarButtonHTML(getLanguageLabel('GBLCAN'),getLanguageLabel('GBLCAN'),'','', true);
		cmdKeyPrint2 = getImageHTML(getLanguageLabel('GBL000089'), getLanguageLabel('GBL000089'), '../images/printses_off.gif');		
		cmdKeyExcel = getImageHTML('EXCEL', 'EXCEL', '../images/excel_off.gif');		
	}
	
	if (!isWebFacing())
	{
		cmdKeyF7 = getBottomBarButtonHTML(getLanguageLabel('GBLBACK'),getLanguageLabel('GBLBACK'),'','', true);
		cmdKeyBack = getImageHTML(getLanguageLabel('GBLBACK'), getLanguageLabel('GBLBACK'),  '../images/EQBack_off.gif');
		cmdKeyF8 = getBottomBarButtonHTML(getLanguageLabel('GBLNEXT'),getLanguageLabel('GBLNEXT'),'','',true);
		cmdKeyNext = getImageHTML(getLanguageLabel('GBLNEXT'), getLanguageLabel('GBLNEXT'),  '../images/EQNext_off.gif');
	}
	
	// We need to find all the options and functions within the command key label line, we do tgis by finding "=" and cutting it up.
	currentE = cmdKeyLnIn.indexOf('=');
	currentStart = 0;
	while(cmdKeyLnIn.substring(currentStart,currentStart + 1) == ' ')
	{
		currentStart++;
	}		
	while(currentE != -1)
	{		
		nextE = cmdKeyLnIn.indexOf('=',currentE + 1);
		if(nextE == -1)
		{
			currentEnd = cmdKeyLnIn.length;
		}
		else
		{
			currentEnd = cmdKeyLnIn.lastIndexOf(" ",nextE);
		}

		// Reformat the function key into English format
		cmdKeyText = (fkeyEnglish(cmdKeyLnIn.substring(currentStart,currentEnd))).trim();
		
		// Parse the function key into fkey and cmdKeyLbl
		myposE = cmdKeyText.indexOf('=');
		fkey = (cmdKeyText.substring(0,myposE)).trim();
		cmdKeyLbl = (cmdKeyText.substring(myposE+1,cmdKeyText.length)).trim();

		 // Interested only on non-Fxx and header/footer widgetisation only
        if (fkey.substring(0,1) != "F" || fkey == 'F')
        {
        	functionKeysList.allOptionKeys.add(fkey + "=" + cmdKeyLbl.nbsp() + "  ".nbsp());
        }
		
		// Interested only on Fxx
		if(fkey.substring(0,1) == "F" && (currentE - currentStart) > 1)
		{
			// Reformat to Fxx
			if(fkey.length==2)
			{
				fkey2 = "F0" + fkey.substring(1,2);
			}
			else
			{
				fkey2 = fkey;
			}

			// Special processing for particular function keys
			// F1=Help
			if(fkey2 == 'F01' && (cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'HELP'))
			{
				if (langId == 'GB')
				{
					cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				}
				else
				{
					cmdKeyHvr = fkeyFormat(fkey,getLanguageLabel('GBLHELP'));
				}

				var optionId = getCookie('optionID');
				
				if (isWebFacing())
				{
					cmdKeyAct = 'dispHelp()';
				}
				
				// display help for maker checker services
				else if(optionId.toUpperCase() == 'MKO' || 
						optionId.toUpperCase() == 'MKC' ||
						optionId.toUpperCase() == 'MHC')
				{
					cmdKeyAct = 'dispHelp()';
				}
				
				else
				{
					cmdKeyAct = 'void gblCurrentFrame.triggerHelp(gblCurrentFrame.objLastFocusInputField)';
				}
				cmdKeyImg = '../images/EQHelp.gif';
				cmdKeyHelp = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
				
				// in classic mode, help key must be disabled as we dont have access to the
				// eqhelppath data area
				if (sessionType==1)
				{
					cmdKeyHelp = '';
				}

				// disable the F1 key as it really screws things up.
				try
				{					
					regCmdKey('kCF01', 'CF01', '','','gblCurrentFrame');
				}
				catch (e)
				{
					// Do nothing
				}
			}

			// F3=Exit/Cancel - For English user
			else if(fkey2 == 'F03' && langId=='GB' && 
					((cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'EXIT') ||
					 (cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'CANC')))
			{		
				if(cmdKeyLbl.substring(cmdKeyLbl.length-3,5).toUpperCase()=='PROMPT')
				{
					proceed = false;
				}
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'CF03' + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/EQExit.gif';
				cmdKeyExit = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
			}
			
			// F3=Exit/Exit Prompting - For non-English user
			else if(fkey2 == 'F03' && langId!='GB' && 
					((cmdKeyLbl.toUpperCase() == 'EXIT') || (cmdKeyLbl.toUpperCase() == 'EXIT PROMPTING')))
			{
				if(cmdKeyLbl.substring(cmdKeyLbl.length-3,5).toUpperCase()=='PROMPT')
				{
					proceed = false;
					cmdKeyHvr = fkeyFormat(fkey,getLanguageLabel('GBL000067'));
				}
				else
				{
					cmdKeyHvr = fkeyFormat(fkey,getLanguageLabel('GBLEXIT'));
				}
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'CF03' + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/EQExit.gif';
				cmdKeyExit = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
			}

			// F3=Cancel - For non-English user
			else if(fkey2 == 'F03' && langId!='GB' && (cmdKeyLbl.toUpperCase() == 'CANCEL'))
			{		
				cmdKeyHvr = fkeyFormat(fkey,getLanguageLabel('GBLCAN'));
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'CF03' + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/EQExit.gif';
				cmdKeyExit = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
			}			

			// F4=Prompt - For English user
			else if(fkey2 == 'F04' && langId=='GB' && 
						( (cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'PROM') ||
						  (cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'PRMP'))
						)
			{
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'CF04' + '\',gblCurrentFrame)';
			}

			// F4=Prompt - For non-English user
			else if(fkey2 == 'F04' && langId!='GB' && (cmdKeyLbl.toUpperCase() == 'PRMPT' || cmdKeyLbl.toUpperCase() == 'PROMPT'))
			{
				cmdKeyHvr = fkeyFormat(fkey,getLanguageLabel('GBLPRMPT'));
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'CF04' + '\',gblCurrentFrame)';
			}
			
			// F5=Verify/Refresh - For English user
			else if(fkey2 == 'F05' && langId=='GB' && 
						(	(cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'VERI') ||
							(cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'REFR') ||
							(cmdKeyLbl.length== 3 && cmdKeyLbl.substring(0,3).toUpperCase() == 'VER')  ))
			{
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'CF05' + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/EQCheck.gif';
				cmdKeyCheck = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
				
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}

			// F5=Verify - For non-English user
			else if(fkey2 == 'F05' && langId!='GB' && ( cmdKeyLbl.toUpperCase() == 'VERIFY' || cmdKeyLbl.toUpperCase() == 'VER'))
			{
				cmdKeyLbl = getLanguageLabel('GBLVERI');
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'CF05' + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/EQCheck.gif';
				cmdKeyCheck = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
				
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}
			
			// F5=Refresh - For non-English user
			else if(fkey2 == 'F05' && langId!='GB' && (cmdKeyLbl.toUpperCase() == 'REFRESH'))
			{
				cmdKeyLbl = getLanguageLabel('GBLREFR');
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'CF05' + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/EQCheck.gif';
				cmdKeyCheck = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
				
				if (isUXP())
				{
					botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
				}
			}

			// F6=Authorise
			else if(fkey2 == 'F06' && cmdKeyLbl.length > 4 && cmdKeyLbl.substring(0,5).toUpperCase() == 'AUTHO' && !isWebFacing())
			{
				cmdKeyLbl = getLanguageLabel('GBLAUTH');
				cmdKeyImg = '../images/function.gif';
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}

			// F6=Authorise first warning
			else if(fkey2 == 'F06' && cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,5).toUpperCase() == 'AUTH ' && !isWebFacing())
			{
				cmdKeyLbl = getLanguageLabel('GBL000099');
				cmdKeyImg = '../images/function.gif';
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}

			// F6=Add
			else if(fkey2 == 'F06' && cmdKeyLbl.length >= 3 && cmdKeyLbl.toUpperCase() == 'ADD' && !isWebFacing())
			{
				cmdKeyLbl = getLanguageLabel('GBLADD');
				cmdKeyImg = '../images/function.gif';
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}

			// F6=Complete
			else if(fkey2 == 'F06' && cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,5).toUpperCase() == 'COMPL ' && !isWebFacing())
			{
				cmdKeyLbl = getLanguageLabel('GBLCOMP');
				cmdKeyImg = '../images/function.gif';
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}
			
			// F6=Resubmit
			else if(fkey2 == 'F06' && cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,5).toUpperCase() == 'RESUB ' && !isWebFacing())
			{
				cmdKeyLbl = getLanguageLabel('GBLRESUB');
				cmdKeyImg = '../images/function.gif';
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}

			// F7=Back
			else if(fkey2 == 'F07' && cmdKeyLbl.toUpperCase() == 'BACK' && !isWebFacing())
			{
				cmdKeyImg = '../images/EQBack.gif';
				cmdKeyLbl = getLanguageLabel('GBLBACK');
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				cmdKeyBack = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
				cmdKeyF7 = getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,'');
			}

			// F8=Next
			else if(fkey2 == 'F08' && cmdKeyLbl.toUpperCase() == 'NEXT' && !isWebFacing())
			{
				cmdKeyImg = '../images/EQNext.gif';
				cmdKeyLbl = getLanguageLabel('GBLNEXT');
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				cmdKeyNext = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
				cmdKeyF8 = getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,'');
			}

			// F7=Backward
			else if(fkey2 == 'F07' && (
									  (langId=='GB' &&	
									  	((cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'BKWD') ||
										(cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'BACK') ||
										(cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'PAGE') ||
										(cmdKeyLbl.length ==3 && cmdKeyLbl.toUpperCase() == 'B/W'))
									  )  ||
									  (langId!='GB' &&	
									  	((cmdKeyLbl.toUpperCase() == 'BKWD') ||
										(cmdKeyLbl.toUpperCase() == 'BACKWARD') ||
										(cmdKeyLbl.toUpperCase() == 'PAGE UP') ||
										(cmdKeyLbl.toUpperCase() == 'B/W'))
									  )
									 )
					)
			{
				if (langId=='GB')
				{
					cmdKeyHvr = fkeyFormat('PgUp',cmdKeyLbl); 
				}
				else 
				{
					cmdKeyHvr = getLanguageLabel('GBL000065');
				}
				
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'PAGEUP' + '\',gblCurrentFrame)';
				if (gblButtonbarFrame.RTL)
				{
					cmdKeyImg = '../images/EqPageUp.gif';
				}
				else
				{
					cmdKeyImg = '../images/EQBack.gif';
				}
				
				var arrParms = new Array();
				arrParms[0] = '0';
				arrParms[1] = cmdKeyAct;
				arrParms[2] = cmdKeyImg;

				if (arrSflNPgUpDn.length>0)
				{
					changeActionPage('PAGEUP','CF07','../images/EqPageUp.gif',arrSflNPgUpDn,arrParms);
				}

				// overwrite default action for F7
				if (arrParms[0] == '1')  
				{
				  	cmdKeyAct = arrParms[1];
				  	cmdKeyImg = arrParms[2];
				}

				// F7 is specified, but PageUp is not a valid on this page
				if (arrParms[0] != '2')  //
				{
					cmdKeyBack = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
					cmdKeyF7 = getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,'');
				}
				else
				  {
					cmdKeyBack = getImageHTML(cmdKeyHvr, cmdKeyHvr, '../images/EqPageUp_off.gif');
					cmdKeyAct  = '';
					cmdKeyF7 = getBottomBarButtonHTML(cmdKeyLbl,cmdKeyLbl,'','', true);
				  }
			}
			
			// F8=Forward
			else if(fkey2 == 'F08' && (
									  (langId=='GB' &&	
									  	((cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'FORW') ||
										(cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'PAGE') ||
										(cmdKeyLbl.length ==3 && cmdKeyLbl.toUpperCase() == 'FWD'))
									  )  ||
									  (langId!='GB' &&	
									  	((cmdKeyLbl.toUpperCase() == 'FORWARD') ||
										(cmdKeyLbl.toUpperCase() == 'FWD') ||
										(cmdKeyLbl.toUpperCase() == 'PAGE DOWN') ||
										(cmdKeyLbl.toUpperCase() == 'PAGEDOWN'))
									  )
									 )
					)
			{
				if (langId=='GB')
				{
					cmdKeyHvr = fkeyFormat('PgDn',cmdKeyLbl); 
				}
				else
				{
					cmdKeyHvr =  getLanguageLabel('GBL000066');
				}
				
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'PAGEDOWN' + '\',gblCurrentFrame)';
				if (gblButtonbarFrame.RTL)
				{
					cmdKeyImg = '../images/EqPageDn.gif';
				}
				else
				{
					cmdKeyImg = '../images/EQNext.gif';
				}

				var arrParms = new Array();
				arrParms[0] = '0';
				arrParms[1] = cmdKeyAct;
				arrParms[2] = cmdKeyImg;
				if (arrSflNPgUpDn.length>0)
				{
					changeActionPage('PAGEDOWN','CF08','../images/EqPageDn.gif',arrSflNPgUpDn,arrParms);
				}

				// overwrite default action for F7
				if (arrParms[0] == '1')  
				{
					cmdKeyAct = arrParms[1];
				  	cmdKeyImg = arrParms[2];
				}

				// F8 is specified, but PageDown is not a valid on this page
				if (arrParms[0] != '2')  //
				{
					cmdKeyNext = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
					cmdKeyF8 = getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,'');
				}
				else
				{
					cmdKeyNext   = getImageHTML(cmdKeyHvr, cmdKeyHvr, '../images/EqPageDn_off.gif');
					cmdKeyAct  = '';
					cmdKeyF8 = getBottomBarButtonHTML(cmdKeyLbl,cmdKeyLbl,'','',true);
				}
			}

			// F9/F11=View - For English user			
			else if( (fkey2 == 'F09' && langId=='GB' && (cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'VIEW')) ||
					 (fkey2 == 'F11' && langId=='GB' && (cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'VIEW')))
			{

				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/view.gif';
				cmdKeyView = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
			}
			
			// F9=Charges
			else if(fkey2 == 'F09' && !isWebFacing())
			{
				cmdKeyLbl = getLanguageLabel('GBLCHGRS');
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/function.gif';
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,''));
			}
			
			// F9=Charges			
			else if(fkey2 == 'F09' && cmdKeyLbl.toUpperCase() == 'CHARGES')
			{
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/view.gif';
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,''));
			}
			
			// F11=Refer
			else if(fkey2 == 'F11' && cmdKeyLbl.length >= 3 && cmdKeyLbl.toUpperCase() == 'REF' && !isWebFacing())
			{
				cmdKeyLbl = getLanguageLabel('GBLREFER');
				cmdKeyImg = '../images/function.gif';
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}

			// F19=Alt - For English user
			else if ((fkey2 == 'F19' && langId=='GB' && (cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'ALT ')))
			{
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/toggle.gif';
				cmdKeyToggle = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);			
			}

			// F12=Cancel			
			else if(fkey2 == 'F12' && !isWebFacing())
			{
				cmdKeyLbl = getLanguageLabel('GBLCAN');
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/cancel.gif';
				cmdKeyCancel = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
				cmdKeyF12 = getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,'');
			}
			
			// F12=Previous/Cancel - For English user
			else if(fkey2 == 'F12' && langId=='GB' && 
					(	(cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'PREV') ||
						(cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'CANC')))
			{
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'CF12' + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/cancel.gif';
				cmdKeyCancel = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
				cmdKeyF12 = getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,'');
			}			

			// F12=Previous - For non-English user
			else if(fkey2 == 'F12' && langId!='GB' && 
					( cmdKeyLbl.toUpperCase() == 'PREV' || cmdKeyLbl.toUpperCase() == 'PREV.' || cmdKeyLbl.toUpperCase() == 'PREVIOUS'))
			{
				cmdKeyHvr = fkeyFormat(fkey,getLanguageLabel('GBLPREV'));
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'CF12' + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/cancel.gif';
				cmdKeyCancel = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
				cmdKeyF12 = getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,'');
			}
			
			// F12=Cancel - For non-English user
			else if(fkey2 == 'F12' && langId!='GB' && (cmdKeyLbl.toUpperCase() == 'CANCEL'))
			{
				cmdKeyHvr = fkeyFormat(fkey,getLanguageLabel('GBLCAN'));
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'CF12' + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/cancel.gif';
				cmdKeyCancel = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
				cmdKeyF12 = getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,'');
			}
			
			// F24=Override			
			else if(fkey2 == 'F24' && !isWebFacing())
			{
				cmdKeyLbl = getLanguageLabel('GBLOVR');
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/cancel.gif';
				cmdKeyWarn = getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
			}
			
			// F24=Override - For English user
			else if(fkey2 == 'F24' && langId=='GB' && ((cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'OVER')))
			{
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'CF24' + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/override.gif';
				cmdKeyWarn = getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
			}
			
			// F24=Override - For non-English user
			else if(fkey2 == 'F24' && langId!='GB' && (cmdKeyText==getLanguageLabel('GBL700002')))
			{
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'CF24' + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/override.gif';
				cmdKeyWarn = getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
			}
			
			// F22=Delete
			else if(fkey2 == 'F22' && !isWebFacing())
			{
				cmdKeyLbl = getLanguageLabel('GBLDEL');
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/cancel.gif';
				cmdKeyWarn = getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
			}
			
			// F22=Delete - For English user
			else if(fkey2 == 'F22' && langId=='GB' && ((cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'DELE')))
			{
				cmdKeyImg = '../images/function.gif';
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'CF22' + '\',gblCurrentFrame)';
				cmdKeyDel = getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
			}
			
			// F22=Delete - For non-English user
			else if(fkey2 == 'F22' && langId!='GB' && (cmdKeyLbl.toUpperCase() == 'DELETE'))
			{
				cmdKeyLbl = getLanguageLabel('GBLDEL');
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'CF22' + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/function.gif';
				cmdKeyDel = getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
			}
			
			// F10=Decline
			else if(fkey2 == 'F10' && !isWebFacing())
			{
				cmdKeyLbl = getLanguageLabel('GBLDECL');
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/function.gif';
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}
			
			// F10=Decline
			else if( (fkey2 == 'F10' && (cmdKeyLbl.length > 2 && cmdKeyLbl.substring(0,3).toUpperCase() == 'DEC'))
					) 
			{
				cmdKeyImg = '../images/function.gif';
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame); void gblCurrentFrame.disableEnterButton(\'' + 'N' + '\')';
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}

			// F18=Accept
			else if(fkey2 == 'F18' && (cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'ACCE') && !isWebFacing())
			{
				cmdKeyLbl = getLanguageLabel('GBLACPT');
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/function.gif';
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}
			
			// F18=Authorise all
			else if(fkey2 == 'F18' && (cmdKeyLbl.toUpperCase() == 'AUTHORISE ALL') && !isWebFacing())
			{
				cmdKeyLbl = getLanguageLabel('GBL000098');
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/function.gif';
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}
			
			// F18=Authorise
			else if(fkey2 == 'F18' && (cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'AUTH') && !isWebFacing())
			{
				cmdKeyLbl = getLanguageLabel('GBLAUTH');
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/function.gif';
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}
			
			// F3/F18=Process/Authorised/Accept/Log - For English user
			else if( (fkey2 == 'F03' && langId=='GB' && (cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'PROC')) ||
					 (fkey2 == 'F18' && langId=='GB' && (cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'AUTH')) ||
					 (fkey2 == 'F18' && langId=='GB' && (cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'ACCE')) ||
					 (fkey2 == 'F18' && langId=='GB' && (cmdKeyLbl.length == 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'LOG')) 
					) 
			{
				cmdKeyImg = '../images/function.gif';
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}

			// F3=Process - For non-English user
			else if (fkey2 == 'F03' && langId!='GB' && (cmdKeyLbl.toUpperCase() == 'PROCESS'))
			{
				cmdKeyHvr = fkeyFormat(fkey,getLanguageLabel('GBLPROC'));
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/function.gif';
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}
			
			// F18=Authorise - For non-English user
			else if (fkey2 == 'F18' && langId!='GB' && (cmdKeyLbl.substring(0,4).toUpperCase() == 'AUTH'))
			{
				cmdKeyHvr = fkeyFormat(fkey,getLanguageLabel('GBLAUTH'));
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/function.gif';
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}
			
			// F18=Accept - For non-English user
			else if (fkey2 == 'F18' && langId!='GB' && (cmdKeyLbl.length > 3 && cmdKeyLbl.substring(0,4).toUpperCase() == 'ACCE'))
			{
				cmdKeyHvr = fkeyFormat(fkey,getLanguageLabel('GBLACPT'));
				cmdKeyLbl = getLanguageLabel('GBLACPT');
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/function.gif';
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}
			
			// F18=Log - For non-English user
			else if (fkey2 == 'F18' && langId!='GB' && (cmdKeyLbl.toUpperCase() == 'LOG'))
			{
				cmdKeyHvr = fkeyFormat(fkey,getLanguageLabel('GBLLOG'));
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/function.gif';
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}

			// F16=Print
			else if (fkey2 == 'F16' && !isWebFacing())
			{
				cmdKeyLbl = getLanguageLabel('GBL000089');
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.printSession(1)'; 
				cmdKeyImg = '../images/printsession.gif';
				cmdKeyPrint2 = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
			}
			
			// F17=Toggle
			else if (fkey2 == 'F17' && !isWebFacing())
			{
				cmdKeyLbl = getLanguageLabel('GBLTOGLE');
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				cmdKeyImg = '../images/toggle.gif';
				cmdKeyToggle = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);			
			}
			
			// Linked function
			else if (!isWebFacing() && (fkey2=='F02' || fkey2=='F11' || fkey2=='F14' || fkey2=='F23'))
			{
				cmdKeyImg = '../images/function.gif';
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				botsLinked.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}

			// Other function keys			
			else
			{
				cmdKeyImg = '../images/function.gif';
				cmdKeyHvr = fkeyFormat(fkey,cmdKeyLbl);
				cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'C' + fkey2 + '\',gblCurrentFrame)';
				botsOther.add(getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg));
			}
			
			// Get list of function keys
			// Note: there is no need to check for right to left as the CKTMSGF is being translated
			if (cmdKeyAct != '')
			{
				functionKeysList.allFunctionKeys.add(getAnchorHTML(cmdKeyHvr,cmdKeyHvr,cmdKeyAct,''));
			}
		}		
		
		//Move onto the next one.
		currentE = nextE;
		currentStart = currentEnd + 1;
	}

	// Print window
	cmdKeyLbl = getLanguageLabel('GBLPRNT');
	cmdKeyHvr = getLanguageLabel('GBLPRNT');
	cmdKeyAct = 'void gblCurrentFrame.printWindow()'; 
	cmdKeyImg = '../images/EQPrint.gif';
	cmdKeyPrint = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);

	cmdKeyLbl = "Toggle Links";
	cmdKeyImg = "Toggle Links";
	cmdKeyHvr = "Toggle Links";
	cmdKeyAct = "JavaScript:toggleFuncKey('footerTexts','funcKeyToggle','funcKeyAnchor');";
	cmdKeyToggleFunc = getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
	
	// Excel window
	if (!isWebFacing() && isFKeyValid(63))
	{
		cmdKeyLbl = 'EXCEL';
		cmdKeyHvr = 'EXCEL';
		cmdKeyAct = 'void gblCurrentFrame.printSession(2)'; 
		cmdKeyImg = '../images/excel.gif';
		cmdKeyExcel = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
	}
	
	// Max/Restore
	var cmdMaxRestore = '';
	if (getFrameMainFrame() != null)
	{
		cmdMaxRestore = 	'<a href="javascript:restore_maximize(\'a_maximize_restore\');"' + 
								'onmouseover="javascript:setWindowStatus(\'maximize_restore\');return true;"' +
								'onmouseout="window.status=\'\';"' +
							   	'id="a_maximize_restore">' +
									'<img src=\'../images/maximize.gif\'' + 
									'alt=\'' + getLanguageLabel('GBLMAXI').trim() + '\'' +
									'title=\'' + getLanguageLabel('GBLMAXI').trim() + '\'' +
									'border="0"' +
									'id="maximize_restore">' +
							'</a>';
	}

	// No help key, then show default help page
	if (cmdKeyHelp == '')
	{
		var optionId = getOptionId();
		if ((isHelpExisting(getOptionId()) && optionId!='') || eqDriver=='Y' )
		{
			cmdKeyHvr = fkeyFormat('F1',getLanguageLabel('GBLHELP'));
			cmdKeyAct = 'dispHelp()';
			cmdKeyImg = '../images/EQHelp.gif';
			cmdKeyHelp = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
		}
		else
		{
			cmdKeyHelp   = getImageHTML(getLanguageLabel('GBLHELP'), 'Help', '../images/EqHelp_off.gif');
		}
	}

    // No F7 - additional checking in case the function has subfile but no F7 key in the footer
    if (cmdKeyBack == '')
	{
		if (gblButtonbarFrame.RTL) 
		{
			cmdKeyBack   = getImageHTML(getLanguageLabel('GBL000065'), 'PageUp', '../images/EqPageUp_off.gif');
		}
		else
		{
			cmdKeyBack   = getImageHTML(getLanguageLabel('GBL000065'), 'PageUp', '../images/EQBack_off.gif');
		}
	    if (arrSflNPgUpDn.length>0)
	    {
            var arrParms = new Array();
            arrParms[0] = '0';
            arrParms[1] = '';
            arrParms[2] = '';
            changeActionPage('PAGEUP','CF07','../images/EqPageUp.gif',arrSflNPgUpDn,arrParms);
            if (arrParms[0]==1 && arrParms[2]!='')
            {
            	cmdKeyLbl = getLanguageLabel('GBLBACK');
                cmdKeyHvr = fkeyFormat('PgUp',cmdKeyLbl);
                cmdKeyAct = arrParms[1];
                cmdKeyImg = arrParms[2];
                cmdKeyBack = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
				cmdKeyF7 = getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,'');
            }
	    }
	}
	
	// No F8 - additional checking in case the function has subfile but no F8 key in the footer
	if (cmdKeyNext == '')
	{
	    if (gblButtonbarFrame.RTL) 
	    {
	    	cmdKeyNext = getImageHTML(getLanguageLabel('GBL000066'), 'PageDown', '../images/EqPageDn_off.gif');
	    }
	    else
	    {
	    	cmdKeyNext   = getImageHTML(getLanguageLabel('GBL000066'), 'PageDown', '../images/EQNext_off.gif');
	    }
	    if (arrSflNPgUpDn.length>0)
	    {
	        var arrParms = new Array();
	        arrParms[0] = '0';
	        arrParms[1] = '';
	        arrParms[2] = '';
	        changeActionPage('PAGEDOWN','CF08','../images/EqPageDn.gif',arrSflNPgUpDn,arrParms);
	        if (arrParms[0]==1 && arrParms[2]!='')
	        {
	        	cmdKeyLbl = getLanguageLabel('GBLNEXT');
                cmdKeyHvr = fkeyFormat('PgDn', cmdKeyLbl);
                cmdKeyAct = arrParms[1];
                cmdKeyImg = arrParms[2];
                cmdKeyNext = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
				cmdKeyF8 = getBottomBarButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,'');
	        }
	    }
	}
	
	// Save
	var checkerElementObj = document.getElementById(OBJ_CHCKR);
	if (!isWebFacing() && checkerElementObj.value == 0)
	{
		if (isFKeyValid(51))
		{
			cmdKeyLbl = getLanguageLabel('GBLSAVE');
			cmdKeyHvr = getLanguageLabel('GBLSAVE');
			cmdKeyAct = 'void gblCurrentFrame.saveSession()'; 
			cmdKeyImg = '../images/save.gif';
			cmdKeySave = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
		}
		if (isFKeyValid(53))
		{
			cmdKeyLbl = getLanguageLabel('GBL000090');
			cmdKeyHvr = getLanguageLabel('GBL000090');
			cmdKeyAct = 'void gblCurrentFrame.saveAsTemplate()'; 
			cmdKeyImg = '../images/savetmplt.gif';
			cmdKeySave2 = getImageButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
		}
	}
	
	// Now append all the buttons for the button bar and bang 'em out
	functionKeysList.butsCommon.add(cmdKeySave);
	functionKeysList.butsCommon.add(cmdKeySave2);
	functionKeysList.butsCommon.add(cmdKeyPrint);
	functionKeysList.butsCommon.add(cmdKeyPrint2);
	functionKeysList.butsCommon.add(cmdKeyExcel);
	functionKeysList.butsCommon.add(cmdKeyHelp);
	functionKeysList.butsCommon.add(cmdMaxRestore);
	functionKeysList.butsCommon.add(cmdKeyExit);
	// F5,F12,Forward,Back buttons no longer required as they exist as buttons in the FKey area
	
	// Add spinner image if not UXP
	if (!isUXP())
	{
		functionKeysList.butsTran.add(getImageHTML('spinnerImage', 'spinnerImage', '../images/EqSpin.gif'));
	}
	
	functionKeysList.butsTran.add(cmdKeyAdd);
	functionKeysList.butsTran.add(cmdKeyView);
	functionKeysList.butsTran.add(cmdKeyToggle);
	
	// Driver screen or classic signon, then setup the toolbar
	if (eqDriver=='Y' || sessionType==SESSION_CLASSIC_DESKTOP)
	{
		setTimeout( 
				function() 
				{
					populateButtonbar('EqButtonbar', functionKeysList.title,
							functionKeysList.butsCommon.toArray(), functionKeysList.butsNavi.toArray(),
							functionKeysList.butsTran.toArray(), functionKeysList.butsOther.toArray());
					hideSpinnerButton();
				}
				,10);
	}

	// Now append all the buttons for the bottom bar
	if(isUXP())
	{
		// For UXP, show Previous button before Enter button
		functionKeysList.commonBottomButtons.add(cmdKeyF12);
		functionKeysList.commonBottomButtons.add(setupEnterButton(proceed));
	}
	else
	{
		functionKeysList.commonBottomButtons.add(setupEnterButton(proceed));
		functionKeysList.commonBottomButtons.add(cmdKeyF12);
	}
	functionKeysList.commonBottomButtons.add(cmdKeyF7);
	functionKeysList.commonBottomButtons.add(cmdKeyF8);
	
//	functionKeysList.otherBottomButtons.add(cmdKeyToggleFunc);
	functionKeysList.otherBottomButtons.addAll(botsLinked);
	functionKeysList.otherBottomButtons.addAll(botsOther);
	addIfNotBlank(functionKeysList.otherBottomButtons, cmdKeyDel);
	addIfNotBlank(functionKeysList.otherBottomButtons, cmdKeyWarn);
}

//**********************************************************************************
// function addIfNotBlank()
// Conditionally adds a string to an array 
//**********************************************************************************
function addIfNotBlank( array, string)
{
	if( string.length > 0)
	{
		array.add( string);
	}
}

// **********************************************************************************
// CHANGE ACTION FOR PAGE UP AND PAGE DOWN
// This function checks for WebFacing subfile page up and page down as page up 
//   and page down action is handled differently
// cmdKey    = PAGEUP / PAGEDOWN
// cmdKey2   = Equivalent function key (e.g CF07 / CF08)
// simage    = Image to use
// arrRcdFmt = List of subfile record format
// arrParms  = Array of fields
//             Element 0 - Action to be taken
//					0 - Do not overwrite default action
//             		1 - Overwrite 
//             		2 - Do not display this button
//             Element 1 - On click action
//             Element 2 - Image file
// **********************************************************************************
function changeActionPage(cmdKey, cmdKey2, sImage, arrRcdFmt, arrParms)
{
	// don't do anything if global vars aren't defined.
	try
	{
		myglobalvars = myglobalvars;
		if (myglobalvars == null)
		{
			return;
		}
	}
	catch (e)
	{
		return;
	}
	
	var sId, obj;
	var i;
	var subfile = false;
	var sOnClick = ' ';
	var globalvars = myglobalvars;
	
	for(i=0; i<arrRcdFmt.length; i++)
	  {
		// construct the objects ID
		switch(cmdKey)
		  {
		  	case 'PAGEUP': 
		  		sId = arrRcdFmt[i] + '$scrollbarTableUpArrow';
		  		break;
		  	
		  	case 'PAGEDOWN': 
		  		sId = arrRcdFmt[i] + '$scrollbarTableDownArrow';
		  		break;
		  }
		  
		 // get the object reference
		obj = document.getElementById(sId);
		
		// no object, then get next record;
		if(obj==null)
		{
			continue;
		}
		
		// onclick not defined
		subfile = true;
		arrParms[0] = '2';
		if(obj.onclick==null)
		{
			break;
		}
		
		// Processing to retrieve the onclick action of this object
		sOnClick = getAttributeValue(obj.outerHTML,'onclick');
		arrParms[0] = '1';
		arrParms[1] = 'void gblCurrentFrame.' + sOnClick;
		arrParms[2] = sImage;
		break;
	  }

	// If we fail to setup an onClick and this command key is not valid, then do not display
	if (globalvars.validCmdKey.indexOf(cmdKey) == -1 && sOnClick == ' ')
	  {
	  	arrParms[0] = '2';
	  }

	// This processing handles the scenario wherein CF07 and CF08 are defined in the DDS, but not
	//    page up and page down.
	// If cmdKey2 is actually a valid key, then use this
	if (globalvars.validCmdKey.indexOf(cmdKey2) >= 0 && sOnClick == ' ' && !subfile)
	{
		arrParms[0] = '1';
		arrParms[1] = 'void gblCurrentFrame.validateAndSubmit(\'' + cmdKey2 + '\',gblCurrentFrame)';
	}
}



// **********************************************************************************
// This function adds button at the bottom of the page
// **********************************************************************************
function setupEnterButton(proceed)
{
	// Suppress this button as not supported in HATS mode
	if(isWebFacing() && pgmIdSet==null)
	{
		var cmdKeyLbl = getLanguageLabel('GBLENT');
		return getTextDisabledButtonHTML(cmdKeyLbl,cmdKeyLbl,'','');				
	}
	
	// Determine label for enter key
	if(proceed)
	{
		// Proceed (based on enter)
		var cmdKeyLbl = getLanguageLabel('GBLENT');
		var cmdKeyHvr = cmdKeyLbl; 
		
		// Not webfacing, determine label of the enter key
		if (!isWebFacing())
		{
			// get the label associated with the enter key
			cmdKeyLbl = getFunctionKeyTextLabel('F0');
			if (cmdKeyLbl == 'Load')
			{
				cmdKeyLbl = getLanguageLabel('GBLCONT');
			}
			else 
			{
				// disabled?
				if (cmdKeyLbl == '')
				{
					cmdKeyLbl = getLanguageLabel('GBLFIN');
					return getTextDisabledButtonHTML(cmdKeyLbl,cmdKeyLbl,'','');
				}
				
				cmdKeyLbl = getLanguageLabel('GBLFIN');
			}
			
		}
		else
		{
			var optionId = getOptionId();
			var disableEnterButton = getCookie('disableEnter');
			if (disableEnterButton != 'N')
			{
				disableEnterButton = 'Y';
				setCookie('disableEnter', 'Y');
			}
			var title = functionKeysList.title.substring(0, 6);
			// for maker-checker list screens, disable the Enter button
			if (disableEnterButton == 'Y' && (optionId.toUpperCase() == 'AWL' || (optionId.toUpperCase() == 'RJL' && title.toUpperCase() == 'REJECT') || optionId.toUpperCase() == 'SBL' || optionId.toUpperCase() == 'ARL'))
			{	
				
				return getTextDisabledButtonHTML(cmdKeyLbl,cmdKeyLbl,'','');
			}
			setCookie('disableEnter', 'Y');
		}

		var cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'ENTER' + '\',gblCurrentFrame)';
		var cmdKeyImg = '../images/EQEnter.gif';		
		var cmdKeySubmit = getTextButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
	}
	else
	{
		var cmdKeyLbl = getLanguageLabel('GBLFILT');
		var cmdKeyHvr = getLanguageLabel('GBLFILT'); 
		var cmdKeyAct = 'void gblCurrentFrame.validateAndSubmit(\'' + 'ENTER' + '\',gblCurrentFrame)';
		var cmdKeyImg = '../images/EQEnter.gif';		
		var cmdKeySubmit = getTextButtonHTML(cmdKeyHvr,cmdKeyLbl,cmdKeyAct,cmdKeyImg);
	}
	return cmdKeySubmit;
}


// **********************************************************************************
// This function returns a string in the format of X=Y or Y=X depending if RTL or not
// **********************************************************************************
function fkeyFormat(fkey,desc)
{
	if (gblButtonbarFrame.RTL)
	{
		return desc + '=' + fkey;
	}
	else
	{
		return fkey + '=' + desc;
	}
}


// **********************************************************************************
// This function reformat a string into standard Fkey function (Fxx=Desc)
// Input can either be Fxx=Desc or Desc=Fxx, and should always returns Fxx=Desc
// **********************************************************************************
function fkeyEnglish(fkeyfull)
{
	// non-right-to-left user
	if (!gblButtonbarFrame.RTL) 
	{
		return fkeyfull;
	}

	// check for =
	var posE = fkeyfull.indexOf('=');
	if (posE == -1) 
	{
		return fkeyfull;
	}
	
	// Parse into left and right side
	var left  = fkeyfull.substring(0,posE); 
	var right = fkeyfull.substring(posE+1,fkeyfull.length);
	
	// Function Key?
	var fleft = validateFKey(left);
	var fright = validateFKey(right);

	// No function key?
	if (!fleft && !fright)
	{
		return fkeyfull;
	}
	
	// both function key?
	if (fleft && fright) 
	{
		return fkeyfull;
	}
	
	// function key on the left side
	if (fleft) 
	{
		return (left + '=' + right);
	}
	
	// function key on the right side
	return (right + '=' + left);
}

// **********************************************************************************
// This function validates a string whether it is Fxx format
// **********************************************************************************
function validateFKey(fkey)
{
	// Fxx format?
	if (fkey.substring(0,1)!='F')
	{
		return false;
	}
	
	// Either 1 or 2 characters
	if (fkey.length == 1)
	{
		return false;
	}
	
	// Next character should be from 1..9
	if ( (fkey.substring(1,2) >= '1') && (fkey.substring(1,2) <= '9') )
	{
		return true;
	}
	
	// assume false
	return false;
}

//**********************************************************************************
//This function sets a disable enter key indicator
//**********************************************************************************
function disableEnterButton(isDisabled)
{
	setCookie('disableEnter', isDisabled);
}

//**********************************************************************************
//function hasAnyRtl
//tests if the string word contains any RTL oriented (just arabic for now) characters.
//
//Parameters:
//strWord - word
//
//Returns: true if an occurence of the Arabic character is detected.
//**********************************************************************************
function hasAnyRtl(str){
	//var rtlChars= '\u0591-\u07FF\uFB1D-\uFDFF\uFE70-\uFEFC';
	var rtlChars= '\u0600-\u06FF\u0750-\u077F\uFB50-\uFDFF\uFE70-\uFEFF';
	var rXp = new RegExp('[' + rtlChars + ']');
	return rXp.test(str);
}


//**********************************************************************************
//function widgetHasAnyRtl
//tests if the widget text contains any RTL oriented (just arabic for now) characters.
//
//Parameters:
//strWord - widgetID
//
//Returns: true if an occurence of the Arabic character is detected on the widget.
//**********************************************************************************
function widgetHasAnyRtl(widgetID){
	return hasAnyRtl(getWidgetInnerText(widgetID));
}

//**********************************************************************************
//function getWidgetInnerHTML
//retrieves the text content of the widget ID.
//
//Parameters:
//strWord - widgetID
//
//Returns: The innerHTML content of the widget represented by the parameter.
//**********************************************************************************
function getWidgetInnerHTML(widgetID){

	if (	widgetID!='' &&
			document.getElementById(widgetID)!=null &&
			document.getElementById(widgetID).innerHTML.trim() != ''
		)
	{
		//force RLE using leading RLE and trailing PDF control characters
		return document.getElementById(widgetID).innerHTML;
	}
	else
	{
		return '';
	}
}

//**********************************************************************************
//function getWfWidgetInnerText
//retrieves the content of the widget ID.
//
//Parameters:
//strWord - widgetID
//
//Returns: The innerText content of the widget represented by the parameter.
//**********************************************************************************
function getWidgetInnerText(widgetID){
	var result = '';
	var wgt;
	if ( widgetID !='' )
	{
		wgt = document.getElementById(widgetID);
	}
	if( wgt)
	{
		if( wgt.innerText) // IE
		{
			result = wgt.innerText;
		}
		else if( wgt.textContent) // FF
		{
			result = wgt.textContent;	
		}
	}
	return result;
}