// *****************************************************************************
//  Javascript Frequency Widget
// -----------------------------
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

// **********************************************************************************
// FREQUENCY PICKER
// **********************************************************************************
	
	//Define the Input Box and the Popup
	var FRQPopupInput=null;	
	var FRQPopup = null;
	// text between select boxes, can't be blank as this does not create invisible 
	// text child element
	
	var text1 = " ";
	var text2 = " ";
	var text3 = " ";
	
    frqZindex = 5;
	
    // Display the frequency widget in a new window?
    asPopupFrqWindow = false;
    asPopupWindow = false;
    popupParam1 = null;
    popupParam2 = null;
    popupParam3 = null;

    // Determine the last frequency click
    var lastFrequencyClick = null;
    
    // Ok/Cancel button
    var frqOkButton = null;
    var frqCancelButton = null;
    var frqSelectElement = null;
	
	// **********************************************************************************
	// function activateFRQPopupAsWindow(obj,anchor)
	// Activate the frequency in a new window
	//
	// Parameters:
	// obj: The popup object 
	// anchor: The anchor for the popup
	// **********************************************************************************	
	function activateFRQPopupAsWindow(obj,anchor)
	{
		// modal window open?
		if (isModalWindowOpenAlert(true))
		{
			return;
		}
		
		// setup the parameters
		popupParam1 = "FRQ";
		popupParam2 = obj;
		popupParam3 = anchor;
		
		// open the new window
		openPopupAsWindow(0,0, anchor, 'popup.jsp');
	}
		
	// **********************************************************************************
	// function activateFRQPopup(obj,anchor)
	// Activate the popup
	//
	// Parameters:
	// obj: The popup object 
	// anchor: The anchor for the popup
	// **********************************************************************************	
	function activateFRQPopup(obj,anchor)
	{	
		// modal window open?
		if (isModalWindowOpenAlert(false))
		{
			return;
		}
		
		// determine if same button has been clicked again
		if (isClickSameField(obj, lastFrequencyClick))
		{
			return;
		}
		
		// store this
		lastFrequencyClick = obj;
		
		// Create a Div for the Popup		
		FRQDiv = document.createElement('div');		
		FRQDiv.id = "FRQPopupId";		
		FRQDiv.className = "widgetContainerDIV";				
		FRQDiv.style.zIndex=frqZindex;
		document.body.appendChild(FRQDiv);

		// Create PopupWindow objects
		FRQPopup = new PopupWindow("FRQPopupId");		
		FRQPopup.offsetX=0;
		FRQPopup.offsetY=0;
			
		if (asPopupWindow == false)
		{
			FRQPopup.autoHide();
		}

		FRQPopupInput=obj;
	    							
		// Create HTML for Select boxes and Buttons
		//
		// frqC is the first selection box: Daily, weekly, monthly, yearly etc.
		//
		// frqD is the second selection box and specifies the day for fortnightly and weekly and the
		// date for monthly as well as the initial starting months for Quarterly, half-yearly and yearly.
		//
		// frqE is the third selection box: This is only used for Quarterly, Half-Yearly and yearly and
		// it specifies the date on which the action will occur. 
		//
		// okButton allows the user to select the entered frequency to be passed back to the inputbox
		// cancelButton cancels any changes to the frequency and restores the inputBox to its previous value
		var x = getLanguageLabel("FRQFRQ");
		
		// setup buttons 
		var okButton = getTextButtonHTML(getLanguageLabel("GBLOK"),getLanguageLabel("GBLOK"),"frqokButtonClick()","");
		var cancelButton = getTextButtonHTML(getLanguageLabel("GBLCAN"),getLanguageLabel("GBLCAN"),"frqcancelButtonClick()","");

		// RTL?
		var strRTL = '';
		var strRTL2 = '';
		var strClass = 'widgetContainer';
		if (isUXP())
		{
			strClass = 'widgetContainerBelow';
		}
		
		if (RTLOrg) 
		{
			strRTL = ' wf_LTOR wf_RIGHT_ALIGN ';
		}
		if (RTLOrg) 
		{
			strRTL2 = ' class="wf_LTOR wf_RIGHT_ALIGN" ';
		}
		if (asPopupFrqWindow)
		{
		strClass = 'widgetContainerAsPopup'; 
		}
		
		var popupHTML = '<div class="' + strClass + '" id="frqWidgetContainer" onkeydown="containerFrqKeyDown(event)" onclick="containerFrqOnClick(event)">'+
	'		<table class="widgetHeader" id="frqWidgetHeader" cellpadding="0" cellspacing="0">'+
	'			<tr>'+
	'				<td colspan="2" class="labelText' + strRTL + '">'+
					getLanguageLabel("FRQFRQ") +
	'				</td>'+
	'			</tr>'+
	'		</table>'+
	'		<div class="widgetBody" id="widgetBodyFrq">'+
    '		<table id="widgetTableFrq">'+
    '       <tr>' +
    '			<td class="labelText' + strRTL + '" >' + 
            		getLanguageLabel("FRQM04") +
    '			</td>' + 
   		'		<td ' + strRTL2 + '>' + 
			'		<SELECT name="frqC" id="frqC" class="widgetFieldDropDown" ' +
						'onchange="var frqC = document.getElementById(\'frqC\');' + 						
						'var frqCode=frqC.value;' +						
						'frqCOnChange(frqCode);">' +							
					'</SELECT>'  +
		'		</td>' + 
	'       </tr>' +	
    '       <tr>' +
    			'<td class="labelText ' + strRTL + '" id=text1Cell>' +
							text1	+	
				'</td>' +		
				'		<td ' + strRTL2 + '>' +	   											
							'<SELECT name="frqD" id="frqD" class="widgetFieldDropDown" ' +							
							'onchange="var frqC = document.getElementById(\'frqC\');' +
							'var frqD = document.getElementById(\'frqD\');' +
							'var frqCode=frqC.value;var frqDay = frqD.value;' +
							'frqDOnChange(frqCode, frqDay);">' +														
							'</SELECT>' +
				'		</td>' + 
 	'       </tr>' +
	'       <tr>' +
				'<td class="labelText ' + strRTL + '" id=text2Cell>' +
							text2	+
				'</td>' +	
				'<td id="text3Cell" ' + strRTL2 + '>' +													
						'<INPUT type="text" name="frqE" id="frqE" value="" size="2" maxlength="2" class="widgetField">' +
						'</INPUT>' +	
						text3	+
				'</td>' +	
	'       </tr>' +
	'		</table>'+								
	'		</div>'+
	'		<div class="widgetBodyFooter" id="widgetFooterFrq">'+
	'		<table class="widgetFooter" id=frqWidgetFooter cellpadding="0" cellspacing="0">'+
	'			<tr>'+
	'				<td>'+
	                okButton +
   	'				</td>'+
	'				<td>'+
	                cancelButton +
	'				</td>'+
	'			</tr>'+
	'		</table>'+
	'		</div>'+
	'	</div> ';		
			
		//Populate the frequency popup with the HTML we have specified above									
		FRQPopup.populate(popupHTML);
		
		//Display the popup using a supplied anchor
		showFRQPopup(obj.id,anchor);
		
		var testwidth = document.getElementById('widgetTableFrq').offsetWidth;
		document.getElementById('widgetBodyFrq').style.width = testwidth +'px';
		document.getElementById('frqWidgetHeader').style.width = testwidth +'px';
		
		// Re-display the popup
		showFRQPopup(obj.id,anchor);

		// Resize the window		
		if (asPopupWindow)
		{
			width = document.getElementById('widgetBodyFrq').offsetWidth;
			height = document.getElementById('FRQPopupId').offsetHeight;
			adjustWidthHeight(width,height);
			adjustLeftTop(width,height);
			showFRQPopup(obj.id,anchor);
		}
		
		//Convert any user entered code to uppercase so it is easier to handle 
		FRQPopupInput.value = FRQPopupInput.value.toUpperCase();
			
		// Set the frequency options for the first box 
		var frqCSelectElement = document.getElementById('frqC');
		setFirstSelectOptions(frqCSelectElement);
		
		// Switch to the selected code..
		var currentCode = FRQPopupInput.value;
		
		// Substring the letter in the code
		currentCode = currentCode.substring(0,1);
		
		// Do some validation here to prevent the user entering garbage
		if(validateCode(FRQPopupInput.value))
		{		
			//Set the initial visibility parameters for the select boxes		
			setInitialVisibility();			
			switchToFreq(frqCSelectElement, currentCode);					
			var frqDSelectElement = document.getElementById('frqD');
			setSecondSelectOptions(frqDSelectElement, FRQPopupInput.value);
		
			//Switch to the selected day				
			switchToDay(frqDSelectElement);
		
			//Poplate the options for the third box and show it if it is needed				
					
			if(currentCode != '' && currentCode != 'Z' && currentCode != 'W' && currentCode != 'Y')
		   	{		   		
				switchBoxThree(FRQPopupInput.value);
			}			
		}
		if( document.activeElement )
		{
			document.activeElement.blur();
		}
		
		// Set the focus on the first element
		frqSelectElement = document.getElementById('frqC');
		if (frqSelectElement != null)
		{
			try
			{
				frqSelectElement.focus();
			} 
			catch (e) {}
		}
		
		// Retrieve the ok/cancel object
		var container = document.getElementById('frqWidgetContainer');
		var inputs = container.getElementsByTagName('input');
		for (var i=0; i<inputs.length; i++)
		{
			if (inputs[i].id == getLanguageLabel("GBLOK"))
			{
				frqOkButton = inputs[i];
			}
			if (inputs[i].id == getLanguageLabel("GBLCAN"))
			{
				frqCancelButton = inputs[i];
			}
		}
	}
	
	// **********************************************************************************
	// function validateCode(inputValue)
	// Validate a user entered input string
	//
	// Parameters:
	// inputValue: The text entered in the inputBox
	// **********************************************************************************	
	function validateCode(inputValue)
	{	
		// Return 1 if a valid entry or 0 if invalid		
		if(inputValue == '' || inputValue == 'Z')
		{
			return 1;
		}
			
		if (inputValue.length > 3)
		{			
			FRQPopupInput.value = '';
			FRQPopupInput.focus();
			closeFRQPopup();
			errorAlertInPopup(20,getLanguageLabel("GBL000027"));
			return 0;
		}
		else
		{
			if(inputValue.length < 3)
			{
				FRQPopupInput.value = '';
				closeFRQPopup();
				errorAlertInPopup(20,getLanguageLabel("GBL000027"));
				return 0;
			}
		
			// do some more validation
			root = inputValue.substring(0,1);
			//check root is a letter. 
			
			//Note: There is a bug in parseInt such that for 08 and 09 it attempts to parse as octal 
			// so 08 gives 0, 09 gives 1. Therefore parseInt(val,10) is used to force decimal base.
			  
			if(root == parseInt(root,10))
			{
				FRQPopupInput.value = '';
				closeFRQPopup();
				errorAlertInPopup(20,getLanguageLabel("GBL000027"));
				return 0;
			}
			//check root is not X (the only invalid starting letter)
			if(root == 'X')
			{
				FRQPopupInput.value = '';
				closeFRQPopup();
				errorAlertInPopup(20,getLanguageLabel("GBL000027"));				
				return 0;
			}
						
			duration = inputValue.substring(1,3);
			if((duration != parseInt(duration,10)) || parseInt(duration,10) > 31)
			{
				FRQPopupInput.value = '';
				closeFRQPopup();
				errorAlertInPopup(20,getLanguageLabel("GBL000027"));
				return 0;
			}
			
		}
		return 1;
	}
		
	// **********************************************************************************
	// function setFirstSelectOptions(frqCSelectElement)
	// Set the options in the first select box (frqC)
	//
	// Parameters:
	// frqCSelectElement: The select element object for the box frqC 
	// **********************************************************************************	
	
	function setFirstSelectOptions(frqCSelectElement)
	{		
		clearOptions(frqCSelectElement);
		appendOption(frqCSelectElement,"","(" + getLanguageLabel("FRQM01").trim() + ")");
		appendOption(frqCSelectElement,"Z",getLanguageLabel("FRQDAILY").trim());		
		appendOption(frqCSelectElement,"W",getLanguageLabel("FRQWEEK").trim());
		appendOption(frqCSelectElement,"Y",getLanguageLabel("FRQFORT").trim());
		appendOption(frqCSelectElement,"V",getLanguageLabel("FRQMON").trim());
		
		//These three are holder names and are replaced later...
		appendOption(frqCSelectElement,"SX",getLanguageLabel("FRQQUAR").trim());
		appendOption(frqCSelectElement,"MYX",getLanguageLabel("FRQHALF").trim());
		appendOption(frqCSelectElement,"AX",getLanguageLabel("FRQYEAR").trim());
	}
	
	
	// **********************************************************************************
	// function setSecondSelectOptions(frqDSelectElement, frqCode)
	// Set the options in the second select box (frqD)
	//
	// Parameters:
	// frqDSelectElement: The select element object for the box frqD
	// frqCode: The frequency code with which to set the box's value 
	// **********************************************************************************	
	function setSecondSelectOptions(frqDSelectElement, frqCode)
	{	
		//Clear any current options in this Select box
	   	clearOptions(frqDSelectElement);
	    	
	   	//Append the options required based on the input code
	    	
	   	//Weekly
	   	if(frqCode.substring(0,1) == "W")
	   	{
	    	appendOption(frqDSelectElement,"","(" + getLanguageLabel("FRQM01").trim() + ")");
	   		appendOption(frqDSelectElement,"01",getLanguageLabel("FRQWK1").trim());
	   		appendOption(frqDSelectElement,"02",getLanguageLabel("FRQWK2").trim());
	   		appendOption(frqDSelectElement,"03",getLanguageLabel("FRQWK3").trim());
	   		appendOption(frqDSelectElement,"04",getLanguageLabel("FRQWK4").trim());
	   		appendOption(frqDSelectElement,"05",getLanguageLabel("FRQWK5").trim());
	   		appendOption(frqDSelectElement,"06",getLanguageLabel("FRQWK6").trim());
	   		appendOption(frqDSelectElement,"07",getLanguageLabel("FRQWK7").trim());
		}	   
	   
		//Fortnightly
		if(frqCode.substring(0,1) == "Y")
		{
			appendOption(frqDSelectElement,"","(" + getLanguageLabel("FRQM01").trim() + ")");
		    appendOption(frqDSelectElement,"01",getLanguageLabel("FRQPOS1").trim() + " " + getLanguageLabel("FRQWK1").trim());
		    appendOption(frqDSelectElement,"02",getLanguageLabel("FRQPOS1").trim() + " " + getLanguageLabel("FRQWK2").trim());
		    appendOption(frqDSelectElement,"03",getLanguageLabel("FRQPOS1").trim() + " " + getLanguageLabel("FRQWK3").trim());
		    appendOption(frqDSelectElement,"04",getLanguageLabel("FRQPOS1").trim() + " " + getLanguageLabel("FRQWK4").trim());
		    appendOption(frqDSelectElement,"05",getLanguageLabel("FRQPOS1").trim() + " " + getLanguageLabel("FRQWK5").trim());
		    appendOption(frqDSelectElement,"06",getLanguageLabel("FRQPOS1").trim() + " " + getLanguageLabel("FRQWK6").trim());
		    appendOption(frqDSelectElement,"07",getLanguageLabel("FRQPOS1").trim() + " " + getLanguageLabel("FRQWK7").trim());
		    appendOption(frqDSelectElement,"08",getLanguageLabel("FRQPOS2").trim() + " " + getLanguageLabel("FRQWK1").trim());
		    appendOption(frqDSelectElement,"09",getLanguageLabel("FRQPOS2").trim() + " " + getLanguageLabel("FRQWK2").trim());
		    appendOption(frqDSelectElement,"10",getLanguageLabel("FRQPOS2").trim() + " " + getLanguageLabel("FRQWK3").trim());
		    appendOption(frqDSelectElement,"11",getLanguageLabel("FRQPOS2").trim() + " " + getLanguageLabel("FRQWK4").trim());
		    appendOption(frqDSelectElement,"12",getLanguageLabel("FRQPOS2").trim() + " " + getLanguageLabel("FRQWK5").trim());
		    appendOption(frqDSelectElement,"13",getLanguageLabel("FRQPOS2").trim() + " " + getLanguageLabel("FRQWK6").trim());
			appendOption(frqDSelectElement,"14",getLanguageLabel("FRQPOS2").trim() + " " + getLanguageLabel("FRQWK7").trim());
		}
		   
		//Monthly   
		if(frqCode.substring(0,1) == "V")
		{
			frqDSelectElement.style.display = 'none';
		}
			
		//Quarterly
		if(frqCode.substring(0,2) == "SX" || frqCode.substring(0,1) == "S" || frqCode.substring(0,1) == "T" || frqCode.substring(0,1) == "U" )
		{
			appendOption(frqDSelectElement,"","(" + getLanguageLabel("FRQM01").trim() + ")");
			appendOption(frqDSelectElement,"S",getLanguageLabel("GBLJAN").trim() + "/" +  getLanguageLabel("GBLAPR").trim() + "/" +  getLanguageLabel("GBLJUL").trim() + "/" +  getLanguageLabel("GBLOCT").trim());
		    appendOption(frqDSelectElement,"T",getLanguageLabel("GBLFEB").trim() + "/" +  getLanguageLabel("GBLMAY").trim() + "/" +  getLanguageLabel("GBLAUG").trim() + "/" +  getLanguageLabel("GBLNOV").trim());
		    appendOption(frqDSelectElement,"U",getLanguageLabel("GBLMAR").trim() + "/" +  getLanguageLabel("GBLJUN").trim() + "/" +  getLanguageLabel("GBLSEP").trim() + "/" +  getLanguageLabel("GBLDEC").trim());
		}
			
		//Half-Yearly
		if(frqCode.substring(0,3) == "MYX" || frqCode.substring(0,1) == "M" || frqCode.substring(0,1) == "N" || 
			frqCode.substring(0,1) == "O"  || frqCode.substring(0,1) == "P" || frqCode.substring(0,1) == "Q" || frqCode.substring(0,1) == "R")
		{
			appendOption(frqDSelectElement,"","(" + getLanguageLabel("FRQM01") + ")");
			appendOption(frqDSelectElement,"M",getLanguageLabel("GBLJAN").trim() + "/" +  getLanguageLabel("GBLJUL").trim());
			appendOption(frqDSelectElement,"N",getLanguageLabel("GBLFEB").trim() + "/" +  getLanguageLabel("GBLAUG").trim());
			appendOption(frqDSelectElement,"O",getLanguageLabel("GBLMAR").trim() + "/" +  getLanguageLabel("GBLSEP").trim());
			appendOption(frqDSelectElement,"P",getLanguageLabel("GBLAPR").trim() + "/" +  getLanguageLabel("GBLOCT").trim());
			appendOption(frqDSelectElement,"Q",getLanguageLabel("GBLMAY").trim() + "/" +  getLanguageLabel("GBLNOV").trim());
			appendOption(frqDSelectElement,"R",getLanguageLabel("GBLJUN").trim() + "/" +  getLanguageLabel("GBLDEC").trim());
		}
			
		//Yearly
		if(frqCode.substring(0,1) == "A" || frqCode.substring(0,1) == "B" || frqCode.substring(0,1) == "C" || 
			frqCode.substring(0,1) == "D" || frqCode.substring(0,1) == "E" || frqCode.substring(0,1) == "F" || 
			frqCode.substring(0,1) == "G" || frqCode.substring(0,1) == "H" || frqCode.substring(0,1) == "I" || 
			frqCode.substring(0,1) == "J" || frqCode.substring(0,1) == "K" || frqCode.substring(0,1) == "L")
		{
			appendOption(frqDSelectElement,"","(" + getLanguageLabel("FRQM01").trim() + ")");
			appendOption(frqDSelectElement,"A",getLanguageLabel("GBLJAN").trim());
			appendOption(frqDSelectElement,"B",getLanguageLabel("GBLFEB").trim());
			appendOption(frqDSelectElement,"C",getLanguageLabel("GBLMAR").trim());
			appendOption(frqDSelectElement,"D",getLanguageLabel("GBLAPR").trim());
			appendOption(frqDSelectElement,"E",getLanguageLabel("GBLMAY").trim());
			appendOption(frqDSelectElement,"F",getLanguageLabel("GBLJUN").trim());
			appendOption(frqDSelectElement,"G",getLanguageLabel("GBLJUL").trim());
			appendOption(frqDSelectElement,"H",getLanguageLabel("GBLAUG").trim());
			appendOption(frqDSelectElement,"I",getLanguageLabel("GBLSEP").trim());
			appendOption(frqDSelectElement,"J",getLanguageLabel("GBLOCT").trim());
			appendOption(frqDSelectElement,"K",getLanguageLabel("GBLNOV").trim());
			appendOption(frqDSelectElement,"L",getLanguageLabel("GBLDEC").trim());
		}
	
	}		
	

	// **********************************************************************************
	// function setInitialVisibility()
	// Set the initial visibility of the select boxes
	// 
	// Parameters: None
	// **********************************************************************************
	function setInitialVisibility()
	{
		//Always display the first select box
		document.getElementById('frqC').style.display = '';
	
		// The visibility of the other two boxes must be set manually
		// If the input value is not entered then start with boxes D and E closed. 
		if (FRQPopupInput.value == null || FRQPopupInput.value == '' )
		{					
			document.getElementById('frqD').style.display = 'none';				
			document.getElementById('frqE').style.display = 'none';
		}		
		// If the user has entered daily and a number then we just replace
		// it with the standard daily value 'Z' and hide boxes D and E. 
		else if (FRQPopupInput.value.substring(0,1) == 'Z') 
		{
			FRQPopupInput.value = 'Z';
			document.getElementById('frqD').style.display = 'none';
			document.getElementById('frqE').style.display = 'none';				
		}
		// Otherwise if we have selected Monthly, Quarterly, Yearly or Half-Yearly
		// then set the third box E visible. 
		else if (
				 FRQPopupInput.value.substring(0,1) != 'Y' && 				
				 FRQPopupInput.value.substring(0,1) != 'W')
		{			
			document.getElementById('frqE').style.display = '';
		}
		// If not then set the third box hidden
		else
		{
			document.getElementById('frqE').style.display = 'none';
		}
	}


	// **********************************************************************************
	// function frqCOnChange(frqInit)
	// Set the visibility of the first select box given the root code on change
	//
	// Parameters:
	// frqInit: The first character of the input frequency code
	// **********************************************************************************	
	function frqCOnChange(frqInit)
	{			
		var frqC = document.getElementById('frqC');
		var frqD = document.getElementById('frqD');
		var frqE = document.getElementById('frqE');
		setFrqText(frqInit, 'C');
			   
		if( frqInit == 'Z' || frqInit == 'Y' ||
		    frqInit == 'W' || frqInit == '')
		{
			frqE.style.display = 'none';
			frqE.value = '';
		}
		if( frqInit == '' || frqInit == 'Z')
		{
			frqD.style.display = 'none';
		}
		else
		{			
			frqD.style.display = '';
			frqE.value = '';
			if(frqInit != 'SX' && frqInit != 'AX' && frqInit != 'MYX')
			{						
				frqE.style.display = 'none';
			}
			else
			{
				frqE.style.display = '';
			}
		}
		
		
		if( frqInit == 'V'){
			frqD.style.display = 'none';
			frqE.style.display = '';
		}
		
		if(frqInit!='Z')  
		{ 										
			var frqDSelectElement = document.getElementById('frqD');
			if (frqDSelectElement != null)
			{ 
				setSecondSelectOptions(frqDSelectElement, frqInit);
			}
		} 
		
	}	
	
	// **********************************************************************************
	// function frqDOnChange(frqCode, frqDay)
	// Set the visibility of the second select box given the root code on change
	//
	// Parameters:
	// frqCode: The frequency mask if one exists or the first letter of the code
	// frqDay: The value from the second select box - frqD, this will be the day
	//         with the two-box values or the first letter of the code otherwise.
	// **********************************************************************************	
	function frqDOnChange(frqCode, frqDay)
	{		
		var frqC = document.getElementById('frqC');
		var frqD = document.getElementById('frqD');
		var frqE = document.getElementById('frqE');
		
		if(frqDay!='')
		{ 
			if(frqCode.substring(0,2) != 'SX' && frqCode.substring(0,3) != 'MYX' &&
			   frqCode.substring(0,2) != 'AX' && frqE.value == '' )
			{ 
				frqE.style.display = 'none'; 													
			}
			else 
			{  
				frqCode = frqDay;
				setFrqText(frqCode, 'D');
				frqE.style.display = '';
				if (frqE.value != '')
				{
					var frqCode = frqD.value;							
				}										 
			} 
		}
	}	
	
	// **********************************************************************************
	// function frqcancelButtonClick()
	// Hide the popup if the cancel button is clicked
	//
	// Parameters: None
	// **********************************************************************************
	function frqcancelButtonClick()
	{
			var frqD = document.getElementById('frqE');
			var frqE = document.getElementById('frqE');
			closeFRQPopup();
			frqD.style.display = 'none';
			frqE.style.display = 'none';
			FRQPopupInput.focus();			
	}
	
	// **********************************************************************************
	// function frqokButtonClick()
	// Accept the selected options if the OK button is clicked
	//
	// Parameters: None
	// **********************************************************************************
	function frqokButtonClick()
	{			
		var frqC = document.getElementById('frqC');
		var frqD = document.getElementById('frqD');
		var frqE = document.getElementById('frqE');
		var valid = true;	
		//Check which boxes are used and create our code: 
		x= frqC.value;
		y= frqD.value;
		z= frqE.value;
		
		//check daily
		if(x=='Z')
		{
	  	FRQPopupInput.value = x;
		}
		else
		{
			//else if it's just boxes 1 + 2
			if(z=='')
			{
				if(y != '' && frqE.style.display != '')
				{
					FRQPopupInput.value = x + y;
				}
				else
				{
					errorAlertInPopup(20,getLanguageLabel("GBL000027"));
					valid = false;
				}
			}	
			else
			{
				if(z != '' && y != '')
				{		
					//validate Z now it's user entered
					if(z == parseInt(z,10) && z<=31){
					//repair z if needed					
					if(z.length == 1)
					{
						z = '0' + z;
					}
					
					//all three boxes
					FRQPopupInput.value = y + z;
					}
					else{
						errorAlertInPopup(20,getLanguageLabel("GBL000027"));
						valid = false;
					}
				}
				else
				{
				//check for monthly
					if (x== 'V')
					{
						//check ok
						if(z == parseInt(z,10) && z<=31)
						{		
							if(z.length == 1){
								z = '0' + z;
							}			
							FRQPopupInput.value = x + z;
						}
						else
						{
							errorAlertInPopup(20,getLanguageLabel("GBL000027"));
							valid = false;
						}						
					}
					else
					{
						errorAlertInPopup(20,getLanguageLabel("GBL000027"));
						valid = false;
					}
				}
			}		
		}
		if(valid ==true)
		{
			closeFRQPopup();
			frqD.style.display = 'none';
			frqE.style.display = 'none';
			FRQPopupInput.focus();
		}
	}
	
	// *************************************************************************************
	// function switchToFreq(frqCSelectElement, currentCode)
	// Open the first select box with the correct option based on the value in the input box
	//
	// Parameters:
	// frqCSelectElement: The select element for the first select box - frqC
	// currentCode: The code the box is to be switched to
	// *************************************************************************************	
	function switchToFreq(frqCSelectElement, currentCode)
	{				
		var frqCSelectElementSize = frqCSelectElement.length;
		var frqCSelectElementSeq = -1;
		//set currentCode to the correct root for Halfly, Quarterly etc. 
		
		if	(currentCode == 'S' || currentCode == 'T' || currentCode == 'U')
		{				
			 currentCode = 'SX';
		}
		
		if	(currentCode == 'M' || currentCode == 'N' || currentCode == 'O' ||
			 currentCode == 'P' || currentCode == 'Q' || currentCode == 'R') 
		{		
			 currentCode = 'MYX';
		}
		
		if	(currentCode == 'A' || currentCode == 'B' || currentCode == 'C' ||
			 currentCode == 'D' || currentCode == 'E' || currentCode == 'F' ||
			 currentCode == 'G' || currentCode == 'H' || currentCode == 'I' ||
			 currentCode == 'J' || currentCode == 'K' || currentCode == 'L') 
		{			
			 currentCode = 'AX';
		}
		setFrqText(currentCode, 'C');
		
		for (frqCSelectElementSeq = 0;frqCSelectElementSeq < frqCSelectElementSize;frqCSelectElementSeq++) 
		{
			if(frqCSelectElement.options[frqCSelectElementSeq].value==currentCode)
			{
				frqCSelectElement.selectedIndex=frqCSelectElementSeq;
			}
		}
	}
	
	// **************************************************************************************
	// function switchToDay(frqDSelectElement)
	// Open the second select box with the correct option based on the value in the input box
	//
	// Parameters:
	// frqDSelectElement: The select element for the second select box - frqD
	// **************************************************************************************
	function switchToDay(frqDSelectElement)
	{
		
		var currentDay = FRQPopupInput.value;
		var currentDayInit = currentDay.substring(0,1);		
		if	(currentDayInit != 'W' && currentDayInit != 'Y' && currentDayInit != 'V')		
			 {			
			 currentDay = currentDayInit;
			 }
		else
			{
			 currentDay = currentDay.substring(1,3);
			}		
				
		var frqDSelectElementSize = frqDSelectElement.length;
		var frqDSelectElementSeq = -1;		
		for (frqDSelectElementSeq = 0;frqDSelectElementSeq < frqDSelectElementSize;frqDSelectElementSeq++) 
		{
			if(frqDSelectElement.options[frqDSelectElementSeq].value==currentDay)
			{
				frqDSelectElement.selectedIndex=frqDSelectElementSeq;
			}
		}
	}
		
	// *************************************************************************************
	// function switchBoxThree(frqESelectElement, currentCode)
	// Open the third select box with the correct option based on the value in the input box
	//
	// Parameters:
	// frqESelectElement: The select element for the third select box - frqE
	// currentCode: The code the box is to be switched to
	// *************************************************************************************	
	function switchBoxThree(currentCode)
	{	
		var frqE = document.getElementById('frqE');
		//Set third box frequency position if there is one			
		currentCode = currentCode.substring(1,3);		
		var switched = false;
		
		if (currentCode != '')
		{	
			setFrqText(currentCode, 'D');		
			if( (currentCode == parseInt(currentCode,10)) && (currentCode <=31))
			{
				switched = true;	
				frqE.value = currentCode;
			}
			else{
				frqE.value = '';
			}
				
		}		
		
		if (switched == true)
		{
			frqE.style.display = '';
		}		
		else{
			frqE.style.display = 'none';
		}		
	}	
	
	// **********************************************************************************
	// function setFrqText(frqInit)
	// Sets the text between the select buttons
	//
	// Parameters:
	// frqinit: The initial of the frequency
	// selectbox: The box we have just changed
	// **********************************************************************************	
	function setFrqText(frqInit, selectbox)
	{		
	
		if (selectbox == 'C')
			{
		    if(frqInit == 'W' || frqInit == 'Y') 
			{
				document.getElementById('text1Cell').innerText = ' ' + getLanguageLabel("FRQM07"); 
				document.getElementById('text2Cell').innerText = ' ';				
				document.getElementById('text3Cell').lastChild.nodeValue = ' ';
			}			
			else if(frqInit == 'V')
			{
				document.getElementById('text1Cell').innerText = ' ';
				document.getElementById('text2Cell').innerText = getLanguageLabel("FRQM03") + ' ';
				document.getElementById('text3Cell').lastChild.nodeValue = '  ' + getLanguageLabel("FRQM05");			
			}
			else 
			{
				if(frqInit == 'SX' || frqInit == 'MYX' || frqInit == 'AX' )
				{
					document.getElementById('text1Cell').innerText = getLanguageLabel("FRQM06");
					document.getElementById('text2Cell').innerText = getLanguageLabel("FRQM03") + ' ';	
					document.getElementById('text3Cell').lastChild.nodeValue = '  ' +  getLanguageLabel("FRQM05");
				}
				else
				{
					document.getElementById('text1Cell').innerText = ' ';
					document.getElementById('text2Cell').innerText = ' ';					
					document.getElementById('text3Cell').lastChild.nodeValue = ' ';
					
				}
			}	
		}
		if (selectbox == 'D')		
		{ 
			
			if(frqInit != parseInt(frqInit,10))
			{
				document.getElementById('text1Cell').innerText= getLanguageLabel("FRQM06");
				document.getElementById('text2Cell').innerText = getLanguageLabel("FRQM03") + ' ';
				document.getElementById('text3Cell').lastChild.nodeValue = '  ' +  getLanguageLabel("FRQM05");		
			}												
		}			
		
	}	
	
	// **********************************************************************************
	// function setFrequencyButton(frqFieldId)
	// Append a frequency picker
	//
	// Parameters:
	// frqFieldId: The field ID of the new frequency picker
	// **********************************************************************************	
	function setFrequencyButton(frqFieldId)
	{		
		var onclickAction = '';

		if (asPopupFrqWindow)
		{
			onClickAction = 'activateFRQPopupAsWindow(document.getElementById(\'' +  frqFieldId + '\'),\'' + frqFieldId + BUT_WIDGET + '\');';
		}
		else
		{
			if( isUXP())
			{
				onClickAction = 'activateFRQPopup(document.getElementById(\'' +  frqFieldId + '\'),\'' + frqFieldId + BUT_WIDGET + 'Href\');';
			}
			else
			{
				onClickAction = 'activateFRQPopup(document.getElementById(\'' +  frqFieldId + '\'),\'' + frqFieldId + BUT_WIDGET + '\');';
			}
		}
	
		var hoverText = getLanguageLabel('FRQDES');
		var labelText = frqFieldId + BUT_WIDGET;
		var imagePath = '/' + getWebAppName() + '/equation/images/frq.gif';
		var classNames = 'frqWidget';
		
		// WebFacing, then do not display the button if read-only
		var obj = document.getElementById(frqFieldId);
		if(isWebFacing() && obj.readOnly)
		{
			return;
		}
		
		// add the frequency button
		var widgetButtonHTML = addWidgetButton(hoverText,labelText,onClickAction,imagePath,classNames);
		
		// protected - then hide the widget 
		if (obj.readOnly)
		{
			disableAnchor(labelText + BUT_HREF, true);
			visibleObj(labelText, false);
		
		}
		return widgetButtonHTML;
	}

	
// **************************************************************************************
// function getmaxLength()
// return the maximum length of the option
//
// Parameters:
// None
// **************************************************************************************
function getmaxLength()
{
	i = getLanguageLabel("FRQM01").trim().length;
	i = max(i,getLanguageLabel("FRQDAILY").trim().length);
	i = max(i,getLanguageLabel("FRQWEEK").trim().length);
	i = max(i,getLanguageLabel("FRQFORT").trim().length);
	i = max(i,getLanguageLabel("FRQMON").trim().length);
	i = max(i,getLanguageLabel("FRQQUAR").trim().length);
	i = max(i,getLanguageLabel("FRQHALF").trim().length);
	i = max(i,getLanguageLabel("FRQYEAR").trim().length);
	
	x = max(getLanguageLabel("FRQPOS1").trim().length,getLanguageLabel("FRQPOS2").trim().length);
	i = max(i,getLanguageLabel("FRQM01").trim().length+x);
	i = max(i,getLanguageLabel("FRQWK1").trim().length+x);
	i = max(i,getLanguageLabel("FRQWK2").trim().length+x);
	i = max(i,getLanguageLabel("FRQWK3").trim().length+x);
	i = max(i,getLanguageLabel("FRQWK4").trim().length+x);
	i = max(i,getLanguageLabel("FRQWK5").trim().length+x);
	i = max(i,getLanguageLabel("FRQWK6").trim().length+x);
	i = max(i,getLanguageLabel("FRQWK7").trim().length+x);

	i = max(i,getLanguageLabel("GBLJAN").trim().length + getLanguageLabel("GBLAPR").trim().length + getLanguageLabel("GBLJUL").trim().length +  getLanguageLabel("GBLOCT").trim().length + 3);
	i = max(i,getLanguageLabel("GBLFEB").trim().length + getLanguageLabel("GBLMAY").trim().length + getLanguageLabel("GBLAUG").trim().length +  getLanguageLabel("GBLNOV").trim().length + 3);
	i = max(i,getLanguageLabel("GBLMAR").trim().length + getLanguageLabel("GBLJUN").trim().length + getLanguageLabel("GBLSEP").trim().length +  getLanguageLabel("GBLDEC").trim().length + 3);

	if (i<=10)
	{
		return 0;
	}
	return ((i-10)*4);
}


// **************************************************************************************
// function max(n1,n2)
// return the maximum number between n1 and n2
//
// Parameters:
// n1, n2 = numbers to compare
// **************************************************************************************
function max(n1,n2)
{
	if (n1>n2) 
	{
		return n1;
	}
	else
	{
	return n2;
	}
}

// **************************************************************************************
// function closeFRQPopup()
// Close the FRQ popup
//
// Parameters:
// None
// **************************************************************************************
function closeFRQPopup()
{
	if (asPopupWindow)
	{
		window.close();
	}
	else
	{
		FRQPopup.hidePopup();
		isClickSameField(1,2);
	}
}


//**************************************************************************************
// function showFRQPopup(obj,anchor)
// Display the Frequency popup
//
// Parameters:
// objId    - the field id
// anchor - the frequency button id
//**************************************************************************************
function showFRQPopup(objId,anchor)
{
	if (isUXP())
	{
		FRQPopup.showPopupBelow(objId);
	}
	else
	{
		FRQPopup.showPopup(anchor);
	}
}


//**********************************************************************************
// function containerFrqKeyDown(e)
// On key down event
//
// Parameters:
// e - event
//**********************************************************************************
function containerFrqKeyDown(e)
{
	var keynum = rtvKeyboardKey(e);
	
	// escape
	if (keynum==27)
	{
		frqcancelButtonClick();
		disableKeyboardKey(e);
		return false;
	}
	
	// enter
	else if (keynum==13)
	{
		if (e.srcElement == frqOkButton)
		{
			frqOkButton.click();
		}
		else if (e.srcElement == frqCancelButton)
		{
			frqCancelButton.click();
		}
		
		disableKeyboardKey(e);
		return false;
	}
	
	// tab
	else if (keynum==9)
	{
		var shiftKey = isShiftKey(e);
		if (shiftKey && e.srcElement == frqSelectElement)
		{
			frqCancelButton.focus();
			disableKeyboardKey(e);
		}
		else if (!shiftKey && e.srcElement == frqCancelButton)
		{
			frqSelectElement.focus();
			disableKeyboardKey(e);
		}
	}
}


//**********************************************************************************
// function containerFrqOnClick(e)
// On click down event
//
// Parameters:
// e - event
//**********************************************************************************
function containerFrqOnClick(e)
{
	disableKeyboardKey(e);
	return false;
}
