
// **********************************************************************************
// function showSpooledFile(spooledName, jobName, jobUser, jobNumber, splfNum)
// Processing to display spooled file
//
// Parameters:
// spooledName - spooled file name
// jobName     - job name
// jobUser     - job user
// jobNumber   - job number
// splfNum     - spooled file number
// totalPageNo - total number of pages
// pagesize    - the default page size to show to the user
// maxpagesize - the maximum page size before a warning is shown to the user 
// **********************************************************************************	
function showSpooledFile(spooledName, jobName, jobUser, jobNumber, splfNum, totalPageNo, pagesize, maxpagesize)
{
	// retrieve and convert to PDF
	var spooledFileURL = "";
	
	// page size not specified, then default to the total number of pages
	if (pagesize == 0)
	{
		pagesize = totalPageNo;
	}

	// spooled file url
	spooledFileURL = '/' +  getWebAppName() + "/equation/jsp/spooledfileFrame.jsp?spoolName=" + escape(spooledName) + 
					"&jobName=" + escape(jobName) +
					"&jobUser=" + jobUser + 
					"&jobNumber=" + jobNumber + 
					"&spoolNumber=" + splfNum +
					"&totalPageNo=" + totalPageNo +
					"&pagesize=" + pagesize +
					"&maxpagesize=" + maxpagesize +
					"&rtl=" + RTL;
						
	// replace % with A
	var jobNameStr = escape(jobName);
	while (jobNameStr.indexOf('%') >= 0)
	{
		jobNameStr = jobNameStr.replace('%', 'A');
	}
	var spooledNameStr = escape(spooledName);
	while (spooledNameStr.indexOf('%') >= 0)
	{
		spooledNameStr = spooledNameStr.replace('%', 'A');
	}	

	// setup parameter for the window
	var arg = 'scrollbar=yes, resizable=yes, toolbar=no, menubar=no, location=no, status=no, directories=no, ';
	var name = jobNameStr+jobUser+jobNumber+splfNum+spooledNameStr;

	// open the window
	window.open(spooledFileURL,name,arg);
}


//**********************************************************************************
// function spooledFile_Popoup(e, obj,  spooledName, jobName, jobUser, jobNumber, splfNum)
// Event for the oncontext
//
// Parameters:
// e           - the event
// obj         - the obj element
// spooledName - spooled file name
// jobName     - job name
// jobUser     - job user
// jobNumber   - job number
// splfNum     - spooled file number
//**********************************************************************************	
function spooledFile_Popoup(e, obj, spooledName, jobName, jobUser, jobNumber, splfNum)
{
	// currently, only delete is supported, so display popup only if same user is authorised to remove it
	// in the future, the checking of user must be done in activateSpooledFilePopup to conditionally display 
	// the menu available to the user
	if (jobUser == webFacUser)
	{
		activateSpooledFilePopup(e, obj, spooledName, jobName, jobUser, jobNumber, splfNum);
	}
	return false;
}

//**********************************************************************************
// function activateSpooledFilePopup(event, obj, spooledName, jobName, jobUser, jobNumber, splfNum)
// Invoke a popup to allow deletion of spooled file
//
// Parameters:
// e           - the event
// obj         - the obj element
// spooledName - spooled file name
// jobName     - job name
// jobUser     - job user
// jobNumber   - job number
// splfNum     - spooled file number
//**********************************************************************************	
function activateSpooledFilePopup(e, obj, spooledName, jobName, jobUser, jobNumber, splfNum)
{
	var listKeys = ['D'];
	var listDescs = [getLanguageLabel('GBLDEL')];
	var funcParameter = "'" + spooledName + "','" + jobName + "','" + jobUser + "','" + jobNumber + "'," + splfNum;

	createPopup(e.x, e.y, 'spooledFile', listKeys, listDescs, 'spooledFile_Click', funcParameter);
	return false;
}

//**********************************************************************************
// function spooledFile_Click(event, obj, spooledName, jobName, jobUser, jobNumber, splfNum)
// Processing to remove a spooled file 
//
// Parameters:
// event       - the event
// obj         - the object
// value       - the key of the selected item
// desc        - the description
// spooledName - spooled file name
// jobName     - job name
// jobUser     - job user
// jobNumber   - job number
// splfNum     - spooled file number
//**********************************************************************************	
function spooledFile_Click(event, obj, value, desc, spooledName, jobName, jobUser, jobNumber, splfNum)
{
	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName('removeSpooledFile',nsuri);
	var qn_op_resp = new WS.QName('removeSpooledFileResponse',nsuri);

	// Set the parameters required by the service
	var sessionIdentifier = getSessionId();
	var getSessionParms = [
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'name',value:''},
								{name:'spooledName',value:spooledName},
								{name:'jobName',value:jobName},
								{name:'jobUser',value:jobUser},
								{name:'jobNumber',value:jobNumber},
								{name:'splfNum',value:splfNum}
							];
		
	// Call the web service
	call.invoke_rpc(
						qn_op,
						getSessionParms,
						null,
						function(call,envelope) 
						{
						}
					);
}

