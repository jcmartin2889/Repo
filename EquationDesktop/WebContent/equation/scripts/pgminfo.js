
// function info
var pgmIdSet = null;
var pgmId = '';
var pgmLvl = '';
var pgmTitle = '';
var pgmFuncMode = '';
var dteHost = '';
var timHost = '';
var dspfRTL = false;
var widHeaderFooter = false;

// Eq driver is currently displayed?
var eqDriver = 'N';

// **********************************************************************************
// function setPgmName(idField)
// set the program name 
//
// Parameters:
// idField: Field name
// **********************************************************************************	
function setPgmName(idField)
{
	pgmId = getValue(idField);
	pgmIdSet = pgmId;
	widHeaderFooter = false;
}

// **********************************************************************************
// function setPgmLevel(idField)
// set the program level 
//
// Parameters:
// idField: Field name
// **********************************************************************************	
function setPgmLevel(idField)
{
	pgmLvl = getValue(idField);
}

// **********************************************************************************
// function setPgmTitle(idField)
// set the program title
//
// Parameters:
// idField: Field name
// **********************************************************************************	
function setPgmTitle(idField)
{
	pgmTitle = getValue(idField);
}

// **********************************************************************************
// function setFuncMode(idField)
// set the function mode 
//
// Parameters:
// idField: Field name
// **********************************************************************************	
function setFuncMode(idField)
{	
	pgmFuncMode = getValue(idField);
}

// **********************************************************************************
// function setSysDate(idField)
// set the system date
//
// Parameters:
// idField: Field name
// **********************************************************************************	
function setSysDate(idField)
{
	dteHost = getValue(idField);
}

// **********************************************************************************
// function setSysTime(idField)
// set the system time
//
// Parameters:
// idField: Field name
// **********************************************************************************	
function setSysTime(idField)
{
	timHost = getValue(idField);
}

// **********************************************************************************
// function setDspfRTL()
// set the RTL orientation of the display file
//
// Parameters:
// None
// **********************************************************************************	
function setDspfRTL()
{
	dspfRTL = true;
}

// **********************************************************************************
// function setDspfLTR()
// set the LTR orientation of the display file
//
// Parameters:
// None
// **********************************************************************************	
function setDspfLTR()
{
	dspfRTL = false;
	
	// do this processing only if display file has been translated right to left
	if (RTL)
	{
		chgHTML('class','i',"mainTD",'wf_LTOR ');
		document.getElementById('mainTD').dir = 'ltr';
		RTL = false;
	}
}


// **********************************************************************************
// function setHFW()
// set that only the display file's header/footer has been widgetised
//
// Parameters:
// None
// **********************************************************************************	
function setHFW()
{
	widHeaderFooter = true;
}


// **********************************************************************************
// function getValue(field)
// return the value of the field name
//
// Parameters:
// field: Field name or Text
// **********************************************************************************	
function getValue(field)
{
	var sLine = new String();

	// Command key 1, sometimes we pass the plain text!
	if (field.indexOf("$")==-1)
	{
		sLine = field.rpl160s();
	}
	else
	{
		if (document.getElementById(field).value != null)
		{
			sLine = document.getElementById(field).value;
		}
		else
		{
			sLine = document.getElementById(field).lastChild.data.rpl160s();
		}
	}

	return sLine;
}


// **********************************************************************************
// function chgHTML(property,mode,idField,value)
// this insert the Value into the Property of idField
//
// Parameters:
// property: Property to be changed
// mode    : I-Insert
// idField : Field Name
// value   : value
// **********************************************************************************	
function chgHTML(property,mode,idField,value)
{
	var obj = document.getElementById(idField);
	if (obj == null) 
	{
		return;
	}

	// changing class name
	if (property == 'class' && mode == 'i')
	  {
	  	obj.className += ' ' + value;
	  }
}


// **********************************************************************************
// function chgParentHTML(property,mode,idField,value)
// this insert the Value into the Property of idField
//
// Parameters:
// property: Property to be changed
// mode    : I-Insert
// idField : Field Name
// value   : value
// **********************************************************************************	
function chgParentHTML(property,mode,idField,value)
{
	var obj = document.getElementById(idField);
	if (obj == null) 
	{
		return;
	}
	var parent = obj.parentElement;

	// changing class
	if (property == 'class' && mode == 'i')
	  {
	  	parent.className += ' ' + value;
	  }
	
}


// **********************************************************************************
// function chgUnnamed()
// this function will add the class WF_LBL to all unnamed fields
//
// Parameters:
// None
// **********************************************************************************	
function chgUnnamed()
{
	var arrObj = document.getElementsByTagName('span');
	var obj;
	var i;

	// include the class wf_LBL to all unnamed fields!
	for(i=0; i<arrObj.length; i++)
	  {
	  	obj = arrObj[i];
	  	if (obj.id.indexOf('Unnamed') >= 0)
	  	  {
	  	  	chgHTML('class','i',obj.id,'wf_LBL');
	  	  }
	  }
	  
}


// **********************************************************************************
// function chgFieldToRTL()
// this function will add the class wf_RIGHT_ALIGN to all fields 
//
// Parameters:
// None
// **********************************************************************************	
function chgFieldToRTL()
{
	var arrObj, obj, i;

	// Equation desktop function, then exit
	if (!isWebFacing())
	{
		return;
	}

	// main TD, right-aligned and left-to-right always
	chgHTML('class','i',"mainTD",'wf_RIGHT_ALIGN wf_LTOR ');
	document.getElementById('mainTD').dir = 'ltr';
	
	// Change all INPUT fields
	arrObj = document.getElementsByTagName('input');
	for(i=0; i<arrObj.length; i++)
	{
		obj = arrObj[i];
	  	if (obj!=null)
	  	{
	  		if (obj.id != '' && obj.type != 'hidden')
	  		{
	  			var d = obj.dir;
				chgParentHTML('class','i',obj.id,'wf_RTOL');

				// compare the size of this input field against the actual string
				// if this was entered as rtl then the size is the maximum size,
				// otherwise, this has been entered left-justified
				if (d=='rtl' && obj.value.length>0 && obj.value.length<obj.maxLength-1)
				{
					d='ltr';
				}
				
				// put classes
				if (d=='rtl')
				{
					chgHTML('class','i',obj.id,'wf_RIGHT_ALIGN wf_LTOR wf_CODERTL');
				}
				else
				{
					chgHTML('class','i',obj.id,'wf_LTOR');
				}
			}
	  	}
	}

	// Change all SPAN fields
	arrObj = document.getElementsByTagName('span');
	for(i=0; i<arrObj.length; i++)
	{
		obj = arrObj[i];
	  	if (obj!=null)
	  	{
	  		if (obj.id != '')
	  		{
	  			obj.innerText = obj.innerText.trimr();
				chgParentHTML('class','i',obj.id,'wf_RIGHT_ALIGN');
			}
	  	}
	}
}

// **********************************************************************************
// function setW97HMR(idField)
// Processing when EQ driver is displayed
//
// Parameters:
// idField: Field name containing the jobname/jobuser/jobnumber
// **********************************************************************************	
function setW97HMR(idField)
{
	// driver
	eqDriver = 'Y';
		
	// get the job name, job user, job number
	obj = document.getElementById(idField);
	str = obj.innerHTML;
	
	// remove all spaces
	while (str.indexOf('&nbsp;') >=0)
	{
		str = str.replace('&nbsp;', '');
	}
	
	// split it
	arr = str.split('/');
	
	// store it in a global array
	if (arr.length == 3)
	{
		setJobName(arr[2], arr[1], arr[0]);
	}

	// reset cookie
	setOptionId('');
}



// **********************************************************************************
// function setJobName(jName, jUser, jNumber)
// Set the job name
//
// Parameters:
// jName   - job name
// jUser   - job user
// jNumber - job number
//
// Return
// true - job name updated to the status bar
// **********************************************************************************	
function setJobName(jName, jUser, jNumber)
{
	// save the job id
	setCookie('jobName', jName);
	setCookie('jobUser', jUser);
	setCookie('jobNumber', jNumber);

	// change the job id in the status bar
	if (getFrameFooter() != null)
	{
		docID = getFrameFooter().document;
		tdID  = docID.getElementById('jobid');
		
		// not yet initialised?
		if (tdID == null)
		{
			return false;
		}
		
		tdID.innerText = jNumber + '/' + jUser + '/' + jName;
		return true;
	}
	
	return false;
}
