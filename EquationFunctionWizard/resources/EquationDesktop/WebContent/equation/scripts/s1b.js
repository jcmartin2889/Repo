// **********************************************************************************
// function setS1B(FieldId, value)
// Add a checkbox 
//
// Parameters:
// FieldId - field name
// value   - value when the check box is checked
// **********************************************************************************	
function setS1B(FieldId, Value)
{
	setSelection(FieldId, Value, 'checkbox', 'N');
}


// **********************************************************************************
// function setR1B(FieldId, value)
// Add a auto-enter radio button
//
// Parameters:
// FieldId - field name
// value   - value when the check box is checked
// **********************************************************************************	
function setR1B(FieldId, Value)
{
	setSelection(FieldId, Value, 'radio', 'Y');
}


// **********************************************************************************
// function S1B_Click(FieldId, chkBoxID)
// Processing when the checkbox is clicked
//
// Parameters:
// FieldId  - id of the field name
// chkBoxId - id of the check box
// **********************************************************************************	
function S1B_Click(FieldId, chkBoxId, AutoEnter)
{
	var objFieldId = document.getElementById(FieldId);
	var objChkBox = document.getElementById(chkBoxId);

	if (objChkBox.checked) 
	{
		objFieldId.value = objChkBox.value;
		if (AutoEnter == 'Y')
		{
			if (isWebFacing())
			{
				setFocusAndSubmitKey('document.SCREEN.' + FieldId,  objChkBox.value, 'ENTER', this);
			}
			else
			{
				validateAndSubmit('CF0',null);
			}
		}
			
	}
	else 
	{
		objFieldId.value = ' ';
	}

	// run onblur
	onBlurUserExitRoutine(FieldId);
}


// **********************************************************************************
// function setSelection(FieldId, Value, Type, AutoEnter)
// Adds a checkbox/radio
//
// Parameters:
// FieldId   - field name
// Value     - value when the check box is checked
// Type      - checkbox or radio 
// AutoEnter - submit the form when selected
// **********************************************************************************	
function setSelection(FieldId, Value, Type, AutoEnter)
{
	var objFieldId = document.getElementById(FieldId);
	var chkBoxId = FieldId + 'S1B';
	var s1bHTML = new String();
	var sClass = new String();
	var onClickAction = new String();
	
	// add the wf_S1B
	addClassName(objFieldId,'wf_S1B');
	
	// Fix for IE9: hidden type elements do not have a defaultValue
	objFieldId.previousValue = objFieldId.value;

	// on click action
	var onClickAction = 'onclick="S1B_Click(\'' + FieldId  + '\', \'' + chkBoxId + '\', \'' + AutoEnter + '\');"';
	
	// disabled?
	var disabled = '';
	if (objFieldId.readOnly)
	{
		disabled = 'disabled';
	}

	// RTL?
	var strRTL = '';
	if (RTLOrg)
	{
		strRTL = 'wf_RIGHT_ALIGN';
	}
	
	s1bHTML = 	'<div id="' + FieldId + 'S1BDiv" ';
	s1bHTML += 'style="white-space: nowrap; width:15px"';
	s1bHTML += 'class="' + objFieldId.className + ' ' + strRTL + '" ';
	s1bHTML += 	disabled + '>';
	s1bHTML += 		'<input type="' + Type + '" ' +
						'id="' + chkBoxId + '" ' +  
						'name="' + chkBoxId + '" ' +  
						'class="' + 'wf_S1B' + '" ' +  
						'value="' + Value + '" ';
						
	if (objFieldId.value==Value)
	{
		s1bHTML += 	'checked ';
	}
	s1bHTML +=  onClickAction + '>';
	s1bHTML += '</div>';

	if (defaultButtonProc)
	{
		document.write(s1bHTML);
	}

	// set attribute for this field
	objFieldId.S1Bwidget = 'S1B';
	objFieldId.S1BValue = Value;
	
	// protected - then protect the field 
//	if (objFieldId.readOnly)
//	{
//		setProtectFieldS1B(FieldId,true)
//	}
	
	return s1bHTML;
}


// **********************************************************************************
// function setProtectFieldS1B(S1BFieldId,prot)
// Protect or unprotect a S1B field
//
// Parameters:
// S1BFieldId - field id
// prot       - protect or unprotect
// **********************************************************************************	
function setProtectFieldS1B(S1BFieldId,prot)
{
	var field = document.getElementById(S1BFieldId);
	if (field != null)
	{
		if (field.S1Bwidget == 'S1B') 
		{
			disableObj(S1BFieldId + 'S1BDiv',prot);
		}
	}
}

// **********************************************************************************
// function setProtectFieldS1B(S1BFieldId,prot)
// Show or hide a S1B field
//
// Parameters:
// S1BFieldId - field id
// visi       - protect or unprotect
// **********************************************************************************	
function setVisibleFieldS1B(S1BFieldId,visi)
{
	var field = document.getElementById(S1BFieldId);
	if (field != null)
	{
		if (field.S1Bwidget == 'S1B') 
		{
			visibleObj(S1BFieldId + 'S1BDiv',visi);
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
function setFieldValueS1B(fieldId, value)
{
	// get the object and ensure this is a Y/N field
	var field = document.getElementById(fieldId);
	if (field.S1Bwidget != 'S1B') 
	{
		return;
	}
	
	// retrieve the element
	var fieldChoice = document.getElementById(fieldId + "S1B");

	// tick appropriate radio buttons
	if (value == field.S1BValue)
	{
		field.value = value;
		fieldChoice.checked = true;
	}
	else
	{
		field.value = '';
		fieldChoice.checked = false;
	}
	
}
