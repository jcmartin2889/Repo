
// **********************************************************************************
// function refreshJobLog()
// Processing for retrieving the job log
//
// Parameters:
// None
// **********************************************************************************	
function refreshJobLog()
{
	// get the job id
	var jobName   = getCookie('jobName');
	var jobUser   = getCookie('jobUser');
	var jobNumber = getCookie('jobNumber');

	// retrieve via using services
	var sessionIdentifier = getSessionId();
	var sessionParms = 	[
							{name:'sessionIdentifier',value:sessionIdentifier},
							{name:'jobName',value:jobName},
							{name:'jobUser',value:jobUser},
							{name:'jobNumber',value:jobNumber}
						];
	getTabInfoFromServDir('getJobLogHTML',sessionParms,'joblog','joblogDiv');
}



// **********************************************************************************
// function refreshJobLogDir(dir,pos)
// Processing for retrieving the job log via page up / page down
//
// Parameters:
// dir - 1 (page up), 2 (page down)
// pos - current position
// **********************************************************************************	
function refreshJobLogDir(dir,pos)
{
	// get the job id
	var jobName   = getCookie('jobName');
	var jobUser   = getCookie('jobUser');
	var jobNumber = getCookie('jobNumber');

	// retrieve via using services
	var sessionIdentifier = getSessionId();
	var sessionParms = 	[
							{name:'sessionIdentifier',value:sessionIdentifier},
							{name:'jobName',value:jobName},
							{name:'jobUser',value:jobUser},
							{name:'jobNumber',value:jobNumber},
							{name:'msgPos',value:pos},
							{name:'diretion',value:dir}
						];
	getTabInfoFromServDir('getJobLogDirHTML',sessionParms,'joblog','joblogDiv');
}


// **********************************************************************************
// function showJobLog(msgDate, msgTime, msgType, msgPos)
// Processing for retrieving a message queue
//
// Parameters:
// jobName 	  - job name
// jobUser    - job user
// jobNumber  - job number
// msgDate    - date sent
// msgTime    - time sent
// msgType    - message type
// msgPos     - position of the message within the job log.  The joblog detail retrieved
//              at this position is compared against these other parameters to confirm
//              that this is the joblog required
// **********************************************************************************	
function showJobLog(jobName, jobUser, jobNumber, msgDate, msgTime, msgType, msgPos)
{
	// retrieve via using services
	var sessionIdentifier = getSessionId();
	var sessionParms = 	[
							{name:'sessionIdentifier',value:sessionIdentifier},
							{name:'jobName',value:jobName},
							{name:'jobUser',value:jobUser},
							{name:'jobNumber',value:jobNumber},
							{name:'msgPos',value:msgPos},
							{name:'msgDate',value:msgDate},
							{name:'msgTime',value:msgTime},
							{name:'msgType',value:msgType}
						];
	getTabWindowFromServDir('getJobLogEntryHTML',sessionParms,'joblog','img' + msgPos);
}