// **********************************************************************************
// function setValidValuesButton(fieldId)
// Prepare the valid value buttons
//
// Parameters:
// fieldId        - field id
// validValuesStr - list of valid values separated by :
// **********************************************************************************	
function setValidValuesButton(fieldId,validValuesStr)
{
	// get the field
	var obj = document.getElementById(fieldId);
	
	// set the class name
	className = obj.className;
	addClassName(obj,"wf_VALIDVALUE");

	// on change event	
	var onChange = "return validValue_OnChange('" + fieldId + "')";

	// select HTML element	
	var select = "<select " +
					"class='" + className + "' " + 
					"name=" + fieldId + "ValidValueSelect " +
					"id=" + fieldId + "ValidValueSelect " +
					"onchange=\"" + onChange + "\"" +
					"title=\"" + obj.title + "\"" +
					"label=\"" + obj.label + "\"" +
					">";
					
	// always include blank option
	select = select +
				"<option " +
					"value='' " +
					selected +
					">" +
				"</option>";
	
	var validValues = validValuesStr.split(CONTEXT_DELIMETER);
	var isSelected = false;
	for (i=0; i<validValues.length; i++)
	{
		var selected = "";
		if (obj.value == validValues[i])
		{
			selected = "selected";
			isSelected = true;
		}
		
		if (validValues[i].trim() != '')
		{
			select = select +
						"<option " +
							"value='" + validValues[i] + "' " +
							selected +
							">" + 
								validValues[i] + 
						"</option>";
		}
	}		
	
	select = select + "</select>";
	document.write(select);
	
	// default initial value to the first value
	if (!isSelected)
	{
		obj.value = '';
	}
	
	// Check if reference field is input capable
	var obj = document.getElementById(fieldId);
	if(obj.readOnly)
	{
		setProtectField(fieldId);
	}
	if(obj.className.indexOf(NON_DISPLAY_CLASS1) >= 0)
	{
		setInvisibleField(fieldId);
	}
}

// **********************************************************************************
// function setProtectFieldValidValue(fieldId,prot)
// Protect or unprotect a valid value field
//
// Parameters:
// fieldId - field id
// prot    - protect or unprotect
// **********************************************************************************	
function setProtectFieldValidValue(fieldId,prot)
{
	var field = document.getElementById(fieldId);
	if (field != null)
	{
		if (field.className.indexOf('wf_VALIDVALUE') >= 0)
		{
			disableObj(fieldId + 'ValidValueSelect',prot);
		}
	}
}

// **********************************************************************************
// function setProtectFieldValidValue(fieldId,prot)
// Show or hide a valid value field
//
// Parameters:
// fieldId - field id
// visi    - protect or unprotect
// **********************************************************************************	
function setVisibleFieldValidValue(fieldId,visi)
{
	var field = document.getElementById(fieldId);
	if (field != null)
	{
		if (field.className.indexOf('wf_VALIDVALUE') >= 0)
		{
			visibleObj(fieldId + 'ValidValueSelect',visi);
		}
	}
}

// **********************************************************************************
// function validValue_OnChange(fieldId)
// Show or hide a valid value field
//
// Parameters:
// fieldId - field id
// visi    - protect or unprotect
// **********************************************************************************	
function validValue_OnChange(fieldId)
{
	var field = document.getElementById(fieldId);
	var fieldChoice = document.getElementById(fieldId + "ValidValueSelect");
	field.value = fieldChoice.value;
	return true;
}


// **********************************************************************************
// function setFieldValueValid(fieldId,value)
// Set the field value combo box
//
// Parameters:
// fieldId - field id
// visi    - protect or unprotect
// **********************************************************************************	
function setFieldValueValid(fieldId,value)
{
	// get the object and ensure this is a value valid field
	var field = document.getElementById(fieldId);
	if (field.className.indexOf('wf_VALIDVALUE') < 0)
	{
		return;
	}

	// set the field
	var fieldChoice = document.getElementById(fieldId + "ValidValueSelect");
	field.value = value;
	fieldChoice.value = value;
	
	// is the value valid?
	if (fieldChoice.value.trim() == '')
	{
		field.value = '';
		errorAlert(20,getLanguageLabel("GBL900051"));
	}
	
	return true;
}
