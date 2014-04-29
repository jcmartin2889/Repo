// **********************************************************************************
// APPEND AN F4 PROMPT BUTTON
// **********************************************************************************
// . (**LANGUAGE)

// **********************************************************************************
// function setPromptButton(promptField)
// Processing for promptable fields (PMT widget)
//
// Parameters:
// promptFieldId - field name
// **********************************************************************************	
function setPromptButton(promptFieldId)
{
	var value = "document.getElementById('" + promptFieldId + "').value";
	var onClickAction = "setFocusAndSubmitKey('" + promptFieldId + "'," + value + ",'CF04',document.getElementById('" +  promptFieldId + "Button'));";
	addPromptButton(promptFieldId, onClickAction);
}


// **********************************************************************************
// function setPromptAst(promptField, promptChar)
// Processing for promptable fields which does not support F4 but support the prompt character (PMA widget)
//
// Parameters:
// promptFieldId - field name
// promptChar    - Equation prompt character
// **********************************************************************************	
function setPromptAst(promptFieldId, promptChar)
{
	var onClickAction = 'processPromptAst(\'' + promptFieldId + '\', \'' + promptChar + '\')';
	addPromptButton(promptFieldId, onClickAction);
}


// **********************************************************************************
// function addPromptButton(promptField, onClickAction)
// Processing to add the prompt button
//
// Parameters:
// promptFieldId - field name
// onClickAction - processing when the button is clicked
// **********************************************************************************	
function addPromptButton(promptFieldId, onClickAction)
{
	var hoverText = getLanguageLabel('PMTDES');
	var labelText = promptFieldId + BUT_WIDGET;
	var imagePath = '../equation/images/prompt.gif';
	var classNames = 'promptWidget';

	// Check if reference field is input capable
	var obj = document.getElementById(promptFieldId);
	if(obj.readOnly == false)
	{
		addWidgetButton(hoverText,labelText,onClickAction,imagePath,classNames);
	}
}


// **********************************************************************************
// function processPromptAst(promtFieldId, promptChar)
// Processing when PMA widget button is click
//
// Parameters:
// promptFieldId - field name
// promptChar    - Equation prompt character
// **********************************************************************************	
function processPromptAst(promptFieldId, promptChar)
{
	// get the field
	obj = document.getElementById(promptFieldId);

	// assume data will be submitted
	var submit = 1;
	
	// blank field, set to prompt character
	if (obj.value.length==0)
	{
		obj.value = promptChar;
	}
	
	// prompt character not existing
	if (obj.value.indexOf(promptChar) == -1)
	{
		// assume prompt character is added at the end
		var x = -1;
		
		// assume data will not be submitted
		submit = 0;
		
		// search for the first space (after the characters)
		for (var i=obj.value.length-1; i>=0 ; i--)
		{
			// non-space?
			if (obj.value.charAt(i) != ' ') 
			{
				x = i + 1;
				break;
			}
			else if (i==0)
			{
				x = 0;
			}
		}
		
		// no space found, then add the prompt character at the end
		if (x==-1)
		{
			x = obj.value.length;
		}
		
		// add the prompt character only if it will not exceed the maximum allowed length
		if (x<obj.maxLength) 
		{
			obj.value = obj.value.substring(0,x) + promptChar;
			submit = 1;
		}
	}
	
	// submit, only when asterisk was added
	if (submit== 1)	
	{
		setFocusAndSubmitKey(promptFieldId,obj.value,'ENTER',document.getElementById(promptFieldId + BUT_WIDGET));
	}
}
