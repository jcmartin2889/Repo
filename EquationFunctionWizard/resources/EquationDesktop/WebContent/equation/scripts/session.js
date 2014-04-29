// **********************************************************************************
// function activateSessionPopup(event,obj,optionId,sessionId,transactionId,userId)
// Invoke a popup to allow deletion of session
//
// Parameters:
// e             - the event
// obj           - the obj element
// optionId      - option id
// sessionId     - session id
// transactionId - transaction id
// userId        - user
// **********************************************************************************	
function activateSessionPopup(e,obj,optionId,sessionId,transactionId,userId)
{
	var listKeys = new Array();
	var listDescs = new Array();
	listKeys[0] = 'D';
	listDescs[0] = getLanguageLabel('GBLDEL');
	var funcParameter = "'" + optionId + "','" + sessionId + "','" + transactionId + "','" + userId + "'" ;

	createPopup(e.x, e.y, 'session',listKeys, listDescs, 'session_Click', funcParameter);
	return false;
}

// **********************************************************************************
// function session_Popoup(event,obj,optionId,sessionId,transactionId,userId)
// Event for the oncontext
//
// Parameters:
// e             - the event
// obj           - the obj element
// optionId      - option id
// sessionId     - session id
// transactionId - transaction id
// userId        - user
// status        - transaction status 
// **********************************************************************************	
function session_Popoup(e,obj,optionId,sessionId,transactionId,userId,status)
{
	// maker checker transaction?
	if (status == 'S' || status == 'P' || status == 'R' || status == 'C' || status == 'D')
	{
		return false;
	}
	
	// currently, only delete is supported, so display popup only if same user is authorised to remove it
	// in the future, the checking of user must be done in activateSessionPopup to conditionally display 
	// the menu available to the user
	if (userId == webFacUser)
	{
		activateSessionPopup(e,obj,optionId,sessionId,transactionId,userId);
	}
	return false;
}

function session_Popup2(e,obj)
{
	return false;
}

// **********************************************************************************
// function session_Click(event,obj,value,desc,optionId,sessionId,transactionId,userId)
// Processing to remove a session 
//
// Parameters:
// event        - the event
// obj          - the object
// value        - the key of the selected item
// desc         - the description
// optionId     - the option Id of the session
// sessionId    - the session Id 
// tranactionId - the transaction Id
// userId       - the user id
// **********************************************************************************	
function session_Click(event,obj,value,desc,optionId,sessionId,transactionId,userId)
{
	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the WSDL
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName('removeSession',nsuri);
	var qn_op_resp = new WS.QName('removeSessionResponse',nsuri);

	// Set the parameters required by the service
	var sessionIdentifier = getSessionId();
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'name',value:''},
								{name:'optionId',value:optionId},
								{name:'sessionId',value:sessionId},
								{name:'transactionId',value:transactionId},
								{name:'userId',value:userId}
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