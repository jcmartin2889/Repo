var childWindows;

// **********************************************************************************
// function openChildWindow(mode,unitId,optionId,context)
// Determine whether any modal window is open
//
// Parameters:
// mode     - mode
// unitId   - unit mnemonic
// optionId - option id
// context  - context
// childType - the child's session type
// rowIndex  - the repeating group id + row index
// **********************************************************************************	
function openChildWindow(mode, unitId, optionId, context, childType, rowIndex)
{
	// generate the name of the child function handler
	var name = "Child_";

	// get the session id
	var sessionIdentifier = getSessionId();

	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName('addChildFunctionHandler',nsuri);
	var qn_op_resp = new WS.QName('addChildFunctionHandlerResponse',nsuri);

	// Set the pararmeters required by the service
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'name',value:name},
								{name:'option',value:optionId.toUpperCase()},
								{name:'context',value:context},
								{name:'childType',value:childType},
								{name:'rowIndex',value:rowIndex}
							];
		
	// Call the web service
	call.invoke_rpc(
						qn_op,
						getSessionParms,
						null,
						function(call,envelope) 
						{
							var returnFieldString = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();
							
							// if not allowed, then issue the message and go away
							var valid = returnFieldString.substr(0,2);
							var msg   = returnFieldString.substr(2,returnFieldString.length);
							
							// invalid
							if (valid == 20)
							{
								getFrameTab().frames['consoleFrame'].deleteMsgs();
								addMessageCtl(msg,'');
								return;
							}
							
							// valid, open a new window
							var arg = 'scrollbar=yes, resizable=yes, toolbar=no, menubar=no, location=no, status=yes, directories=no';
							
							var link = "input.jsp?mode=" + mode + 
										"&unit=" + unitId.toUpperCase() + 
										"&optionId=" + "NOLOAD" + 
										"&context=" + '' +
										"&name=" + name;
							
							childWindows = window.open("/" + getWebAppName() + "/equation/jsp/" + link, "childWindow", arg);
						}
					);
}


// **********************************************************************************
// function isChildWindowOpen()
// Determine whether any child window is open
//
// Parameters:
// None
//
// Returns:
// true if any child window is open
// **********************************************************************************	
function isChildWindowOpen()
{
	if (childWindows != null)
	{
		if (childWindows.closed)
		{
			childWindows = null;
		}
		else
		{
			return true;
		}
	}
	return false;
}

// **********************************************************************************
// function focusOnChildWindow()
// Focus on the child window
// **********************************************************************************
function focusOnChildWindow()
{
	if (childWindows != null)
	{
		childWindows.focus();
	}
}