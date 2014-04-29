// **********************************************************************************
// function replaceWithImage(fieldId, image, hoverText)
// Replace an object's inner HTML with an image
//
// Parameters:
// fieldId - the ID of the field
// image - image name
// hoverText - hover text
// **********************************************************************************	
function replaceWithImage(fieldId, image, hoverText)
{
	var obj = $(fieldId);
	var imagePath = '../equation/images/' + image;
	var imageHTML = '<img src="' + imagePath + '" alt="' + obj.innerText + '" title="' + hoverText + '" id="' + fieldId + '" border="0">';
	
	obj.innerHTML = imageHTML;
}

// **********************************************************************************
// function setAst(fieldId)
// Processing for the warning image
//
// Parameters:
// fieldId: Field name
// **********************************************************************************	
function setAST(fieldId, Image)
{
	var obj = $(fieldId);
	if (obj == null) 
	{
		return;
	}

	// innerText is undefined in FF/Chrome, blank in IE
	if (obj.innerText != undefined && obj.innerText != ' ' && obj.innerText != '')
	{
		if (Image == ' ')
		{
			Image = isUXP() ? 'warning.png' : 'EQWarning.gif';
		}
		replaceWithImage(fieldId,Image,'');
	}
}