// **********************************************************************************
// function onBlurUserExitRoutine(fieldId)
// Perform on blur user exit routine 
//
// Parameters:
// fieldId - the field Id
// **********************************************************************************	
function onBlurUserExitRoutine(fieldId)
{
	// do not perform user exit script during supervisor mode
	if ((typeof(checker) != "undefined") && checker==1)
	{
		return;
	}

	var field = getInputField(fieldId);
	
	try
	{
		var onblurscript = getAttribute(field,'onblurscript');
		if (onblurscript != null)
		{
			if (onblurscript.trim() != '')
			{
				eval(onblurscript);
			}
		}
	}
	catch(e)
	{
		var msg = getLanguageLabel("GBL900049");
		msg = msg.replace('&1', fieldId);
		msg = msg + ' ' + getLanguageLabel("GBL900048");
		errorAlert(20,msg);
	}
}


// **********************************************************************************
// function getInputField(fieldId)
// Return the input field object 
//
// Parameters:
// fieldId - the field Id
//
// Returns:
// the input field object
// **********************************************************************************	
function getInputField(fieldId)
{
	var field = document.getElementById(fieldId);
	return field;
}


// ------------------ SUPPORTED FUNCTIONS IN THE USER EXIT JAVASCRIPT CODES ---------


// **********************************************************************************
// function setInputFieldValue(fieldId)
// Set the input field value of a field 
//
// Parameters:
// fieldId - the field Id
// fieldValue - the field value
//
// Return:
// the field value if the field has been set, otherwise null
// **********************************************************************************	
function setInputFieldValue(fieldId,fieldValue)
{
	var field = getInputField(fieldId);
	
	// field not found
	if (field==null)
	{
		return '';
	}
	
	if (field.value != null)
	{
		field.value = fieldValue;
		
		// y/n
		setFieldValueYNO(fieldId,fieldValue);
		setFieldValueS1B(fieldId,fieldValue);
		
		return field.value;
	}
	else
	{
		return null;
	}
}



// **********************************************************************************
// function getInputFieldValue(fieldId)
// Return the input field value of a field 
//
// Parameters:
// fieldId - the field Id
//
// Returns:
// the input value.  If the field does not exist, then it returns ''
// **********************************************************************************	
function getInputFieldValue(fieldId)
{
	var field = getInputField(fieldId);
	
	// field not found
	if (field==null)
	{
		return '';
	}
	
	return field.value;
}


// **********************************************************************************
// function setProtectField(fieldId)
// Protect the field 
//
// Parameters:
// fieldId - the field Id
// **********************************************************************************	
function setProtectField(fieldId)
{
	var field = getInputField(fieldId);
	if (field != null)
	{
		// make it read only
		field.readOnly = true;
		addClassName(field,'wf_pr');
		
		// disable the buttons
		disableAnchor(fieldId + BUT_PROMPT + BUT_HREF, true);
		disableAnchor(fieldId + BUT_OPTION + BUT_HREF, true);
		disableAnchor(fieldId + BUT_WIDGET + BUT_HREF, true);
		disableAnchor(fieldId + BUT_VALIDATE + BUT_HREF, true);
		disableAnchor(fieldId + BUT_TOGGLE + BUT_HREF, true);
		disableAnchor(fieldId + BUT_VOPTION + BUT_HREF, true);
		setProtectFieldYNO(fieldId,true);
		setProtectFieldS1B(fieldId,true);
		setProtectFieldValidValue(fieldId,true);
				
		// field is currently visible, then hide the prompt
		if (field.style.visibility != 'hidden')
		{
			visibleObj(fieldId + BUT_PROMPT, false);
			visibleObj(fieldId + BUT_OPTION, false);
			visibleObj(fieldId + BUT_VALIDATE, false);
			visibleObj(fieldId + BUT_TOGGLE ,false);
			visibleObj(fieldId + BUT_WIDGET, false);
			visibleObj(fieldId + BUT_VOPTION, false);
		}
	}
}


// **********************************************************************************
// function setUnprotectField(fieldId)
// Unprotect the field 
//
// Parameters:
// fieldId - the field Id
// **********************************************************************************	
function setUnprotectField(fieldId)
{
	var field = getInputField(fieldId);
	if (field != null)
	{
		// make it editable
		field.readOnly = false;
		removeClassName(field,'wf_pr');
		
		// enable the buttons
		disableAnchor(fieldId + BUT_PROMPT + BUT_HREF, false);
		disableAnchor(fieldId + BUT_OPTION + BUT_HREF, false);
		disableAnchor(fieldId + BUT_WIDGET + BUT_HREF, false);
		disableAnchor(fieldId + BUT_VALIDATE + BUT_HREF, false);
		disableAnchor(fieldId + BUT_TOGGLE + BUT_HREF, false);
		disableAnchor(fieldId + BUT_VOPTION + BUT_HREF, false);
		setProtectFieldYNO(fieldId,false);
		setProtectFieldS1B(fieldId,false);
		setProtectFieldValidValue(fieldId,false);
		
		// field is currently visible, then display the prompt as well
		if (field.style.visibility != 'hidden')
		{
			visibleObj(fieldId + BUT_PROMPT, true);
			visibleObj(fieldId + BUT_OPTION, true);
			visibleObj(fieldId + BUT_VALIDATE, true);
			visibleObj(fieldId + BUT_TOGGLE, true);
			visibleObj(fieldId + BUT_WIDGET, true);
			visibleObj(fieldId + BUT_VOPTION, true);
		}
	}
}


// **********************************************************************************
// function setVisibleField(fieldId)
// Show the field 
//
// Parameters:
// fieldId - the field Id
// **********************************************************************************	
function setVisibleField(fieldId)
{
	var field = getInputField(fieldId);
	if (field != null)
	{
		visibleObj(fieldId, true);
		visibleObj(fieldId + ROW_SUFFIX, true);
		visibleObj(rtvDisplayFieldFromInputField(field,'*field').id,true);
	}
}


// **********************************************************************************
// function setInvisibleField(fieldId)
// Hide the field 
//
// Parameters:
// fieldId - the field Id
// **********************************************************************************	
function setInvisibleField(fieldId)
{
	var field = getInputField(fieldId);
	if (field != null)
	{
		visibleObj(fieldId,false);
		visibleObj(fieldId + ROW_SUFFIX, false);
		visibleObj(rtvDisplayFieldFromInputField(field,'*field').id,false);
	}
}


