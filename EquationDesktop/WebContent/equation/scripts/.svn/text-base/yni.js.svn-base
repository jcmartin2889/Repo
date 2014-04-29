// Yes and No values. (**LANGUAGE)
var chsYes = getLanguageLabel('GBLY');
var chsNo = getLanguageLabel('GBLN');

// **********************************************************************************
// function setYNI(YNIFieldId,riInd)
// Set the YNI widget
//
// Parameters:
// YNIFieldId - field id
// riInd      - reverse image indicator
// **********************************************************************************	
function setYNI(YNIFieldId,riInd)
{
	var YNIField = YNIFieldId;
	var YNIFieldElement = $(YNIFieldId);
	var yniHTML = new String();
	var onClickActionY = new String();
	var onClickActionN = new String();
	
	// add the wf_YNO 
	addClassName(YNIFieldElement,'wf_YNO');
	
	// Fix for IE9: hidden type elements do not have a defaultValue
	YNIFieldElement.previousValue = YNIFieldElement.value;

	// onclick action
	onClickActionY = 'onclick="YNI_Y_Click(this,\'' + YNIFieldId + '\', \'' + YNIField + 'YYNI\');"';
	onClickActionN = 'onclick="YNI_N_Click(this,\'' + YNIFieldId + '\', \'' + YNIField + 'NYNI\');"';

	// disabled?
	var disabled = '';
	if (YNIFieldElement.readOnly)
	{
		disabled = 'disabled';
	}

	// RTL?
	var strRTL = '';
	if (RTL)
	{
		strRTL = 'wf_RIGHT_ALIGN';
	}
		
	// add the Y/N option
	yniHTML = 	'<div id="' + YNIField + 'YNIDiv" ';
	yniHTML += 'style="white-space: nowrap; width:55px"';
	yniHTML += 'class="' + YNIFieldElement.className + ' ' + strRTL + '" ';
	yniHTML += disabled + '>';
	
	// Y radio button
	var yHTML = 		'<input type="radio" ' + 
							'id="' + YNIField + 'YYNI' + '" ' + 
							'name="' + YNIField + 'YYNI' + '" ' + 
							'class="' + 'wf_YNO' + '" ' +  
							'value="' + chsYes + '" ' + 
							'title="' + YNIFieldElement.title + '" ' +
							'label="' + getAttribute(YNIFieldElement,'label') + '" ';
							
	if (YNIFieldElement.value==chsYes)
	{
		yHTML += 	'checked ';
	}
	
	yHTML +=  onClickActionY + '>';
	
	// N radio button
	var nHTML = 		'<input type="radio" ' + 
							'id="' + YNIField + 'NYNI' + '" ' +  
							'name="' + YNIField + 'NYNI' + '" ' +  
							'class="' + 'wf_YNO' + '" ' +  
							'value="' + chsNo + '" ' +
							'title="' + YNIFieldElement.title + '" ' +
							'label="' + getAttribute(YNIFieldElement,'label') + '" ';
							
	if (YNIFieldElement.value==chsNo)
	{
		nHTML += 	'checked ';
	}
	nHTML +=  onClickActionN + '>';
	
	if (RTL)
	{
		yniHTML += chsNo + nHTML + chsYes + yHTML;
	}
	else
	{
		yniHTML += yHTML + chsYes + nHTML + chsNo;
	}
	
	yniHTML += '</div>';

	if (defaultButtonProc)
	{
		document.write(yniHTML);
	}

	return yniHTML;
}	

// **********************************************************************************
// function YNI_Y_Click(thisObj,YNIFieldId, YNIField)
// Onclick event when Y is choosen
//
// Parameters:
// thisObj    - field id object
// YNIFieldId - field id
// YNIField   - option field id
// **********************************************************************************	
function YNI_Y_Click(thisObj,YNIFieldId, YNIField)
{
	var obj = $(YNIFieldId);

	// turn off N
	var fieldChoiceN = $(YNIFieldId + "NYNI");
	fieldChoiceN.checked = false;

	// already yes
	if(obj.value == chsYes)
	{
	  	thisObj.checked = false;
	  	obj.value   = ' ';
	}
	else
	{ 
		obj.value = chsYes;
	}
	
	// run onblur
	var onblurscript = getAttribute(obj,'onblurscript');
	if (onblurscript != null)
	{
		if (onblurscript.trim() != '')
		{
			onBlurUserExitRoutine(YNIFieldId);
		}
	}	
}

// **********************************************************************************
// function YNI_N_Click(thisObj,YNIFieldId, YNIField)
// Onclick event when N is choosen
//
// Parameters:
// thisObj    - field id object
// YNIFieldId - field id
// YNIField   - option field id
// **********************************************************************************	
function YNI_N_Click(thisObj,YNIFieldId, YNIField)
{
	var obj = $(YNIFieldId);

	// turn off Y
	var fieldChoiceY = $(YNIFieldId + "YYNI");
	fieldChoiceY.checked = false;

	// already No
	if(obj.value == chsNo)
	{
	  	thisObj.checked = false;
	  	obj.value   = ' ';
	}
	else
	{ 
		obj.value = chsNo;
	}

	// run onblur
	var onblurscript = getAttribute(obj,'onblurscript');
	if (onblurscript != null)
	{
		if (onblurscript.trim() != '')
		{
			onBlurUserExitRoutine(YNIFieldId);
		}
	}	
}

// **********************************************************************************
// function setYNO(YNIFieldId,riInd)
// Set Y or N
//
// Parameters:
// YNIFieldId - field id
// riInd      - reverse image indicator
// **********************************************************************************	
function setYNO(YNIFieldId,riInd)
{
	setYNI(YNIFieldId,riInd);
}

// **********************************************************************************
// function setProtectFieldYNO(YNIFieldId,prot)
// Protect or unprotect a YNI field
//
// Parameters:
// YNIFieldId - field id
// prot       - protect or unprotect
// **********************************************************************************	
function setProtectFieldYNO(YNIFieldId,prot)
{
	var field = $(YNIFieldId);
	if (field != null)
	{
		if (field.className.indexOf('wf_YNO') >= 0)
		{
			disableObj(YNIFieldId + 'YNIDiv',prot);
		}
	}
}

// **********************************************************************************
// function setProtectFieldYNO(YNIFieldId,prot)
// Show or hide a YNI field
//
// Parameters:
// YNIFieldId - field id
// visi       - protect or unprotect
// **********************************************************************************	
function setVisibleFieldYNO(YNIFieldId,visi)
{
	var field = $(YNIFieldId);
	if (field != null)
	{
		if (field.className.indexOf('wf_YNO') >= 0)
		{
			visibleObj(YNIFieldId + 'YNIDiv',visi);
		}
	}
}

// **********************************************************************************
// function setFieldValueValid(fieldId,value)
// Set the field value combo box
//
// Parameters:
// fieldId - field id
// visi    - protect or unprotect
// **********************************************************************************	
function setFieldValueYNO(fieldId,value)
{
	// get the object and ensure this is a Y/N field
	var field = $(fieldId);
	if (field.className.indexOf('wf_YNO') < 0)
	{
		return;
	}
	
	// retrieve the Y and N object element
	var fieldChoiceY = $(fieldId + "YYNI");
	var fieldChoiceN = $(fieldId + "NYNI");

	// tick appropriate radio buttons
	if (value==chsNo)
	{
		field.value = value;
		fieldChoiceY.checked = false;
		fieldChoiceN.checked = true;
	}
	else if (value==chsYes)
	{
		field.value = value;
		fieldChoiceY.checked = true;
		fieldChoiceN.checked = false;
	}
	else
	{
		field.value = '';
		fieldChoiceY.checked = false;
		fieldChoiceN.checked = false;
	}
}