
// **********************************************************************************
// function refreshMessages()
// Processing for retrieving the current user's messages
//
// Parameters:
// None
// **********************************************************************************	
function refreshMessages()
{
	var msgq = '/QSYS.LIB/QUSRSYS.LIB/' + webFacUser + '.MSGQ';
	refreshMsgQueue(msgq, 'messages');
}


// **********************************************************************************
// function refreshSystem(unit)
// Processing for retrieving the system's messages
//
// Parameters:
// unit - unit
// **********************************************************************************	
function refreshSystem()
{
	var msgq = '/QSYS.LIB/KLIB' + webFacUnit + '.LIB/KAPSTATUS.MSGQ';
	refreshMsgQueue(msgq, 'system');
}


// **********************************************************************************
// function refreshMsgQueue()
// Processing for retrieving the message queue
//
// Parameters:
// msgq - message queue to retrieve
// **********************************************************************************	
function refreshMsgQueue(msgq, frame)
{
	// retrieve via using services
	var sessionIdentifier = getSessionId();
	var sessionParms = 	[
							{name:'sessionIdentifier',value:sessionIdentifier},
							{name:'queue',value:msgq}
						];
	getTabInfoFromServDir('getMsgQueueHTML',sessionParms,frameName,frameName + 'Div');
}


// **********************************************************************************
// function refreshMsgQueueDir(dir,pos)
// Processing for retrieving the message queue via page up / page down
//
// Parameters:
// dir - 1 (page up), 2 (page down)
// pos - current position
// **********************************************************************************	
function refreshMsgQueueDir(frame,dir,pos)
{
	var msgq;
	if (frame=='messages') 
	{
		msgq = '/QSYS.LIB/QUSRSYS.LIB/' + webFacUser + '.MSGQ';
	}
	else
	{
		msgq = '/QSYS.LIB/KLIB' + webFacUnit + '.LIB/KAPSTATUS.MSGQ';
	}

	// retrieve via using services
	var sessionIdentifier = getSessionId();
	var sessionParms = 	[
							{name:'sessionIdentifier',value:sessionIdentifier},
							{name:'queue',value:msgq},
							{name:'msgPos',value:pos},
							{name:'diretion',value:dir}
						];
	getTabInfoFromServDir('getMsgQueueDirHTML',sessionParms,frameName,frameName + 'Div');
}


// **********************************************************************************
// function showMsgQueue(msgDate, msgTime, msgType, msgPos)
// Processing for retrieving a message queue
//
// Parameters:
// msgDate - date sent
// msgTime - time sent
// msgType - message type
// msgPos  - position of the message within the job log.  The joblog detail retrieved
//           at this position is compared against these other parameters to confirm
//           that this is the joblog required
// **********************************************************************************	
function showMsgQueue(msgq, msgDate, msgTime, msgType, msgPos)
{
	// retrieve via using services
	var sessionIdentifier = getSessionId();
	var sessionParms = 	[
							{name:'sessionIdentifier',value:sessionIdentifier},
							{name:'queue',value:msgq},
							{name:'msgPos',value:msgPos},
							{name:'msgDate',value:msgDate},
							{name:'msgTime',value:msgTime},
							{name:'msgType',value:msgType}
						];
	getTabWindowFromServDir('getMsgQueueEntryHTML',sessionParms, frameName,'img' + msgPos);
}