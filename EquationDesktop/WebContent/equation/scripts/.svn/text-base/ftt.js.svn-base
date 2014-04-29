
// **********************************************************************************
// function formatToSpan(str)
// Convert a string into a series of SPANs depending on the control character defined 
//   within the string
//
// Parameters:
// str   - string
// **********************************************************************************	
function setFTT(idField)
{
	var objField = document.getElementById(idField);
	if (objField == null)
	{
		return;
	}
	
	var strSpan  = formatToSpan(objField.innerHTML);
	objField.innerHTML = strSpan;
}

// **********************************************************************************
// function formatToSpan(str)
// Convert a string into a series of SPANs depending on the control character defined 
//   within the string
//
// Parameters:
// str   - string
// **********************************************************************************	
function formatToSpan(str)
{
	var i, n1, n2, attrib;
	var strHTML;
	
	strHTML = new String();
	n1      = 0;
	n2      = -1;
	attrib  = 0;

	for(i=0; i<str.length; i++)
	  {
	  	// is this a control character?
	  	if(isCtrCharCode(str.charCodeAt(i)))
	  	  {
	  	  	// convert previous text to HTML
	  	  	if (n2 >= n1)
	  	  	  {
	  	  	  	strHTML += convertToSpan(attrib,str.substring(n1,n2+1));
	  	  	  }
	  	  	  
	  	  	// setup indexes for this new text
	  	  	attrib = str.charCodeAt(i);
	  	  	n1 = i + 1;
	  	  	n2 = i;
	  	  }
	  	  
	  	// not a control character, then adjust indexes
	  	else 
	  	  {
	  	  	n2 = n2 + 1;
	  	  }
 	
	  }

	// convert last text
	if (n2 >= n1) 
	{
		strHTML += convertToSpan(attrib,str.substring(n1,n2+1));
	}

	return(strHTML);
}


// **********************************************************************************
// function convertToSpan(ctrCharCode,str)
// Conver a string into a a SPAN tag with a class name
//
// Parameters:
// ctrCharCode - control character
// str         - string
// **********************************************************************************	
function convertToSpan(ctrCharCode,str)
{
	var strHTML;
	var classHTML;
	
	
	classHTML = 'class="' + ctrCharCodeToClass(ctrCharCode) + '" ';

	if (ctrCharCode!=0)
	{
		strHTML = '<span ' + classHTML + '>&nbsp;' + str + '</span>';
	}
	else
	{
		strHTML = '<span ' + classHTML + '>' + str + '</span>';
	}
	return strHTML;
}

// **********************************************************************************
// function isCtrCharCode(ctrCharCode)
// Determine whether the character code is a special formating character
//
// Parameters:
// ctrCharCode - control character
// **********************************************************************************	
function isCtrCharCode(ctrCharCode)
{
	if (ctrCharCode >= 128 && ctrCharCode <= 159)
	{
		return true;
	}
	else
	{ 
		return false;
	}
}


// **********************************************************************************
// function ctrCharCodeToClass(ctrCharCode)
// Map the special control character into its equivalent class name
//
// Parameters:
// ctrCharCode - control character
// **********************************************************************************	
function ctrCharCodeToClass(ctrCharCode)
{
	switch(ctrCharCode)
	  {
	  	// normal
	  	case 0:
	  	case 128:	// x20
	  		return 'wf_default';
	  		
	  	// reverse image
	  	case 129:	// x21
	  		return 'wf_ri_default';
	  		
	  	// high intensity
	  	case 130:	// x22
	  		return 'wf_hi';
	  	
	  	// high intensity, reverse image
	  	case 131:	// x23
	  		return 'wf_hi wf_ri_default';

	  	// underline
	  	case 132:	// x24
	  		return 'wf_ul';

	  	// underline, reverse image
	  	case 133:	// x25
	  		return 'wf_ul wf_ri_default';
	  		
	  	// non display
	  	case 134:	// x26
	  		return 'wf_nd_pr';
	  		
	  	// default
	  	default:
	  		return 'wf_default';
	  }
}


// **********************************************************************************
// function ctrCharCodeToSev(ctrCharCode)
// Map the special control character into its equivalent severity
//
// Parameters:
// ctrCharCode - control character
// **********************************************************************************	
function ctrCharCodeToSev(ctrCharCode)
{
	switch(ctrCharCode)
	  {
	  	// normal
	  	case 0:
	  	case 128:	// x20
	  		return 0;
	  		
	  	// reverse image
	  	case 129:	// x21
	  		return 20;
	  		
	  	// high intensity
	  	case 130:	// x22
	  		return 10;
	  	
	  	// high intensity, reverse image
	  	case 131:	// x23
	  		return 20;

	  	// default
	  	default:
	  		return 0;
	  }
}


// **********************************************************************************
// function removeCtrlChar(str)
// Remove control characters within a string
//
// Parameters:
// str   - string
// **********************************************************************************	
function removeCtrlChar(str)
{
	var i, n1, n2;
	var strHTML;
	
	strHTML = new String();
	n1      = 0;
	n2      = -1;

	for(i=0; i<str.length; i++)
	  {
	  	// is this a control character?
	  	if(isCtrCharCode(str.charCodeAt(i)))
	  	  {
	  	  	// copy previous text
	  	  	if (n2 >= n1)
	  	  	  {
	  	  	  	strHTML += str.substring(n1,n2+1);
	  	  	  }
	  	  	  
	  	  	// setup indexes for this new text
	  	  	n1 = i + 1;
	  	  	n2 = i;
	  	  }
	  	  
	  	// not a control character, then adjust indexes
	  	else 
	  	  {
	  	  	n2 = n2 + 1;
	  	  }
 	
	  }

	// convert last text
	if (n2 >= n1) 
	{
		strHTML += str.substring(n1,n2+1);
	}

	return(strHTML);
}


// **********************************************************************************
// function getStrSev(str)
// Determine the severity given the a string
//
// Parameters:
// idField = Field name
// severity = Message severity
// **********************************************************************************	
function getStrSev(str)
{
	var i;
	var attrib;
	var sev = 0;
	
	for(i=0; i<str.length; i++)
	  {
	  	// is this a control character?
	  	if(isCtrCharCode(str.charCodeAt(i)))
	  	  {
	  	  	attrib = str.charCodeAt(i);
	  	  	sev = ctrCharCodeToSev(attrib);
	  	  	break;
	  	  }
	  }

	return(sev);
}