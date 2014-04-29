var dataFieldData = 8;

// **********************************************************************************
// function getDsccn(fieldId,dataFields)
// Setup the DSCCN based on the data fields 
//
// Parameters:
// fieldId     - fieldId
// dataFields  - data fields
// prompt      - prompt mode? - Y or N or Y1 (force *)        
// **********************************************************************************
function getDsccn(fieldId,dataFields,prompt)
{
	var list = dataFields.split(',');
	var dsccn = pad('',256,' ');

	for(i=0; i<list.length; i+=dataFieldData)
	{
		// should we include this field?
		if (list[i+4] == 'N')
		{
			// retrieve start and length
			var start = parseInt(list[i+2]);
			var length = parseInt(list[i+3]);
			
			// get the value
			value = '';
			
			// default value?
			if (list[i+1].indexOf('.') == 0)
			{
				value = list[i+1].substr(1);
			}
			else
			{
				var obj = getField(list[i+1]);
				if (obj != null)
				{
					// is this data field based on default input mapping?
					if (list[i+5]=='N')
					{
						value = obj.value;
					}
					else
					{
						// initial display, then default details from the html input field
						// or this is a hidden field
						if (pv_init_display || list[i+6]=='V' || list[i+6]=='U')
						{
							value = obj.value.substr(list[i+2], list[i+3]);
						}
						else
						{
							value = '*';
						}
					}
				
					// upper case?
					if (obj.className.indexOf('wf_UPPERCASE') >=0)
					{
						value = value.toUpperCase();
					}
					
					// prompt mode, add *
					if ((obj.id==fieldId && prompt == 'Y') || (prompt == 'Y1')) 
					{
						if (value.indexOf('*') == -1 && value.length<length)
						{
							value = value + '*';
						}
					}
				}
			}
			
			// ensure value does not exceed the length
			if (value.length > length)
			{
				value = value.substring(0,length);
			}

			// move to DSCCN if it is not * or the dsccn is still blank			
			if (value != '*' || 
				(value == '*' && (prompt=='Y' || prompt=='Y1') && dsccn.substr(start,length).trim()=='')
				)
			{
				dsccn = addToDSCCN(dsccn,value,start,length);
			}
		}
	}
	
	// no mapping, default to the input field
	if (dsccn.length==0)
	{
		dsccn = (getField(fieldId).value).trim() + '*';
	}
	
	return dsccn;
}


// **********************************************************************************
// function addToDSCCN(dsccn,value,start,length)
// Set the DSCCN positions start/length with value 
//
// Parameters:
// dsccn	- current DSCCN
// value	- value
// start	- start position
// length	- length        
// **********************************************************************************
function addToDSCCN(dsccn,value,start,length)
{
	var newCCN = '';
	
	// retrieve the first part of the string
	if (start>0)
	{
		newCCN = dsccn.substr(0,start);
	}
	
	x=i;
	newCCN = newCCN + pad(value,length,' ');
	i=x;
	
	// retrieve the last part of the string
	if (dsccn.length > start+length)
	{
		newCCN = newCCN + dsccn.substr(start+length);
	}
	
	return newCCN;
}


// **********************************************************************************
// function getField(fieldId)
// Retrieve the field 
//
// Parameters:
// fieldId     - fieldId
// **********************************************************************************
function getField(fieldId)
{
	if (asPopupWindow)
	{
		return window.opener.document.getElementById(fieldId);
	}
	else
	{
		return document.getElementById(fieldId);
	}
}

// **********************************************************************************
// function getDataFieldOfField(fieldId)
// Retrieve the data field of a PV field 
//
// Parameters:
// fieldId     - PV field
// **********************************************************************************
function getDataFieldOfField(fieldId,arrDataFields)
{
	for(i=0; i<arrDataFields.length; i+=dataFieldData)
	{
		// is this the desired field?
		if (arrDataFields[i] == fieldId)
		{
			return i;
		}
	}
	return -1;
}


// **********************************************************************************
// function setupFilterFieldsInDSCCN(dsccn)
// Setup the filter fields in the DSCCN 
//
// Parameters:
// dsccn - the DSCCN data
//
// Returns:
// Updated DSCCN
// **********************************************************************************
function setupFilterFieldsInDSCCN(dsccn)
{
	for (i=0; i<gblFieldNames.length; i++)
	{
		var filterObj = document.getElementById('filter_' + gblPromptFieldId + i);
		var value = filterObj.value;
		
		// upper case?
		if (filterObj.className.indexOf('wf_UPPERCASE')>=0)
		{
			value = value.toUpperCase();
		}
		
		dsccn = addToDSCCN(dsccn,value,parseInt(gblFieldPositions[i]),parseInt(gblFieldLengths[i]));
	}
	
	return dsccn;
}


//**********************************************************************************
// function rtvFullContextFieldValue(contextObj)
// Return the full list of context fields and context fields values given a field object 
//
// Parameters:
// contextObj - the field object
//
// Returns:
// the full context fields and context field values
//**********************************************************************************
function rtvFullContextFieldValue(contextObj)
{
	var leftmostfield = getAttribute(contextObj,'leftmostfield');
	if (leftmostfield == null)
	{
		return rtvRightContextFieldValue(contextObj);
	}
	
	else if (leftmostfield == '')
	{
		return rtvRightContextFieldValue(contextObj);
	}
	
	else
	{
		return rtvRightContextFieldValue(document.getElementById(leftmostfield));
	}
}
	
//**********************************************************************************
// function rtvRightContextFieldValue(contextObj)
// Return the list of context fields and context fields values from the given field object 
//
// Parameters:
// contextObj - the field object
//
// Returns:
// the context fields and context field values from the given field object
//**********************************************************************************
function rtvRightContextFieldValue(contextObj)
{
	contextfields = '';
	contextfieldvalues = '';
	
	while (contextObj != null)
	{
		contextfields += contextObj.id + GLOBAL_DELIMETER;
		contextfieldvalues += rtvContextFieldValue(contextObj);
		
		var nextfield = getAttribute(contextObj,'nextfield');
		if (nextfield != null && nextfield != '')
		{
			contextObj = document.getElementById(nextfield);
		}
		else
		{
			contextObj = null;
		}
	}
	
	contextObj = new Object();
	contextObj.contextfields = contextfields;
	contextObj.contextfieldvalues = contextfieldvalues;
	return contextObj;
}


//**********************************************************************************
// function rtvContextFieldValue(contextObj)
// Return the field value of an input field 
//
// Parameters:
// contextObj - the field object
//
// Returns:
// the field value taking into consideration upper case and length
//**********************************************************************************
function rtvContextFieldValue(contextObj)
{
	if (contextObj.className.indexOf('wf_UPPERCASE') >=0)
	{
		return contextObj.value.toUpperCase().pad(contextObj.maxLength);
	}
	else
	{
		return contextObj.value.pad(contextObj.maxLength);
	}
}



//**********************************************************************************
// function getDsccnForPredictivePrompt(fieldId,dataFields)
// Setup the DSCCN for predictive prompting.  This will allow user to "prompt" on the description of the code rather than on the code itself  
//
// Parameters:
// fieldId     - fieldId
// dataFields  - data fields
//**********************************************************************************
function getDsccnForPredictivePrompt(fieldId,dataFields)
{
	var list = dataFields.split(',');
	var dsccn = pad('',256,' ');

	for(i=0; i<list.length; i+=dataFieldData)
	{
		// should we include this field?
		if (list[i+4] == 'N')
		{
			// retrieve start and length
			var start = parseInt(list[i+2]);
			var length = parseInt(list[i+3]);
			
			// get the value
			value = '*';
			
			// is this the PV description field?
			if (getAttribute(predictiveListObj,'predprompt') == list[i])
			{
				value = '*' + predictiveListObj.value + '*';
			}
			
			// ensure value does not exceed the length
			if (value.length > length)
			{
				value = value.substring(0,length);
			}

			// move to DSCCN
			dsccn = addToDSCCN(dsccn,value,start,length);
		}
	}
	
	return dsccn;
}


//**********************************************************************************
function rtvContextFieldValue(contextObj)
{
	if (contextObj.className.indexOf('wf_UPPERCASE') >=0)
	{
		return contextObj.value.toUpperCase().pad(contextObj.maxLength);
	}
	else
	{
		return contextObj.value.pad(contextObj.maxLength);
	}
}//prefix
//**********************************************************************************
function getGlobalProcessingFlags(fieldId,dataFields,prompt)
{
	var list = dataFields.split(',');
	var dsccn = pad('',256,' ');

	prefix = '';
	systemSet = false;
	unitSet = false;
	unitDescSet = false;
	
	for(i=0; i<list.length; i+=dataFieldData)
	{
		if(list[i]=='SYSTEM' && !systemSet){
			var start = parseInt(list[i+2]);
			var length = parseInt(list[i+3]);
			prefix += '$SYSTEM,' + start + ',' + length + ',';
			systemSet = true;
		}
		if(list[i]=='UNIT' && !unitSet){
			var start = parseInt(list[i+2]);
			var length = parseInt(list[i+3]);
			prefix += '$UNIT,' + start + ',' + length + ',';
			unitSet = true;
		}
		else if(list[i]=='UNITDESC' && !unitDescSet){
			var start = parseInt(list[i+2]);
			var length = parseInt(list[i+3]);
			prefix += '$UNITDESC,' + start + ',' + length;
			unitDescSet = true;
		}
	}
	if(prefix!=''){
		prefix = '[' + prefix + ']';
	}	
	
	return prefix;
}