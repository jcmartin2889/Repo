var currentProcessId = '';

// **********************************************************************************
// function refreshComment()
// Processing for retrieving the task comments
//
// Parameters:
// None
// **********************************************************************************	
function refreshComment()
{
	var frame = getFrameCurrent();
	var processId = frame.document.getElementById(OBJ_TASKPROCID);
	
	// No process id
	if (processId == null || processId.value.trim().length==0)
	{
		var div = document.getElementById('commentDiv');
		
		var str = '<table><tr>';
		if (RTL)
		{
			str += 	
				'<td>' +
				getLanguageLabel("GBL900076") +					
				'</td>' +
				'<td>' +
					'<img src="../images/EqMsgInfo.gif" ' + 
					'alt="' + getLanguageLabel("GBLINFOL") + '" ' + 
					'title="' + getLanguageLabel("GBLINFOL") + '" ' +
				    'border="0">' +
				'</td>' ;
		}
		else
		{
			str += 	
					'<td>' +
						'<img src="../images/EqMsgInfo.gif" ' + 
						'alt="' + getLanguageLabel("GBLINFOL") + '" ' + 
						'title="' + getLanguageLabel("GBLINFOL") + '" ' +
					    'border="0">' +
					'</td>' +
					'<td>' +
						getLanguageLabel("GBL900076") +					
					'</td>';
		}
		str += '</tr></table>';
		
		div.innerHTML = str;
		return;  
	}

	// retrieve via using services
	var sessionIdentifier = getSessionId();
	var sessionParams = 	[
							{name:'sessionIdentifier',value:sessionIdentifier},
							{name:'processId',value:processId.value}
						];
	if( isUXP())
	{
		var commentDiv = document.getElementById('commentDiv')
		uxpLoadLRPComment('getTaskComment',sessionParams,commentDiv);
	}
	else
	{
		getTabInfoFromServDir('getTaskComment',sessionParams,'comment','commentDiv');
	}
	currentProcessId = processId;
}

// **********************************************************************************
// function uxpLoadLRPComment(serviceMethod, frame, objElement)
// Processing to invoke the service directory and display it within the frame
//
// Parameters:
// serviceMethod     - the method to invoke
// sessionParms      - array of parameters
// objElement        - element to receive the string (elementLoc.innerHTML)
// **********************************************************************************	
function uxpLoadLRPComment(serviceMethod,sessionParms,objElement)
{
	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName(serviceMethod,nsuri);
	var qn_op_resp = new WS.QName(serviceMethod + 'Response',nsuri);
	
	var currFrame = getFrameCurrent();
	
	if(currFrame.sessionType == currFrame.SESSION_DIRECT_TRANS_DESKTOP && getCookie("jobNumber") == "")
	{			
		var jobDetails = currFrame.jobDetails;
		var jobName = jobDetails.substr(0,10).trim();
		var jobUser = jobDetails.substr(10,10).trim();
		var jobNumber = jobDetails.substr(20).trim();
		
		currFrame.setJobName(jobName, jobUser, jobNumber);
		
		for(var i=0, length=sessionParms.length; i < length; i++)
		{	
			switch(sessionParms[i].name)
			{
				case "jobName" 		: sessionParms[i].value = jobName; break;
				case "jobUser" 		: sessionParms[i].value = jobUser; break;				
				case "jobNumber" 	: sessionParms[i].value = jobNumber; break;
			}
		}
	}	

	// Call the web service
	call.invoke_rpc(
						qn_op,
						sessionParms,
						null,
						function(call,envelope) 
						{
							// get the returned value
							var htmlFormat = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();
							
							// remove the control character
							var a = htmlFormat.split('*x*x*x*');
							

							// put the returned value to the element
							if (a.length==2) 
							{
								objElement.innerHTML = a[1];
							}
							else
							{
								objElement.innerHTML = a[0];
							}
							
						}
					);
}

