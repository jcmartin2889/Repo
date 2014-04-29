function hidediv(id) 
{	
	//safe function to hide an element with a specified id
	if (document.getElementById) 
	{ 
		// DOM3 = IE5, NS6
		$(id).style.display = 'none';
	}
	else 
	{
		if (document.layers) 
		{ 
			// Netscape 4
			document.id.display = 'none';
		}
		else 
		{
			// IE 4
			document.all.id.style.display = 'none';
		}
	}
}	

function showdiv(id) 
{	
	//safe function to hide an element with a specified id
	if (document.getElementById) 
	{
		 // DOM3 = IE5, NS6
		$(id).style.display = '';
	}
	else 
	{
		if (document.layers) 
		{ 
			// Netscape 4
			document.id.display = '';
		}
		else
		{ 
			// IE 4
			document.all.id.style.display = '';
		}
	}
}

function unitDropDownManuallyChanged()
{
	var systemUnitString = $('UNITDRPDWN').value;
	var tokens = systemUnitString.split( " " );

	if(tokens[0] != 'undefined' && tokens[0] != null )
	{
		$("SYSTEM").value = tokens[0];
	}
	else
	{
		$("SYSTEM").value = '';
	}
	
	if(tokens[1] != 'undefined' && tokens[1] != null )
	{
		$("UNIT").value = tokens[1];
	}
	else
	{
		$("UNIT").value = '';
	}	
}

function unitDropDownChanged()
{			
	var newUnitValue = $('UNIT').value;
	var newSystemValue = $('SYSTEM').value;
	select_Value_Set('UNITDRPDWN', newUnitValue + newSystemValue);
}

/*
 * This method shows/hides the search panel depending on the value
*/
function showHideSearch(value)
{
	//Clear out System/Unit (only if not manually set)
	if($('UNITDRPDWN').value == '*')
	{
		$('UNIT').value = '';
		$('SYSTEM').value= '';
	}
	//And clear down any stored data
	$('ACCNTBRCH').value = '';
	$('ACCNTNO').value = '';
	$('ACCNTSFX').value = '';
	$('EXTACCNT').value = '';
	$('IBAN').value = '';
	$('CUST_MNEM').value = '';
	$('CUST_LOC').value = '';
	$('CUST').value = '';
	$('BRCHMNEM').value = '';
	$('BRANCHNO').value = '';
	$('DEALTYPE').value = '';
	$('DEALREF').value = '';
	
	// If the value is customers then show the customers search and switch to the first search criteria ...
	if(value == 'Customers')
	{
		showdiv('SearchCustomersDiv');	
		switchSearchCriteria('SearchCustomersDiv', false);	
	}
	// Else hide the customers search ...
	else
	{
		hidediv('SearchCustomersDiv');	
	}

	// If the value is accounts then show the accounts search and switch to the first search criteria ...
	if(value == 'Accounts')
	{
		showdiv('SearchAccountsDiv');
		switchSearchCriteria('SearchAccountsDiv', false);	
	}
	// Else hide the accounts search ...
	else
	{
		hidediv('SearchAccountsDiv');	
	}

	// If the value is branches then show the accounts search and switch to the first search criteria ...
	if(value == 'Branches')
	{
		showdiv('SearchBranchesDiv');	
		switchSearchCriteria('SearchBranchesDiv', false);	
	}
	// Else hide the branches search ...
	else
	{
		hidediv('SearchBranchesDiv');	
	}

	// If the value is branches then show the deals search and switch to the first search criteria ...
	if(value == 'Deals')
	{
		showdiv('SearchDealsDiv');	
		switchSearchCriteria('SearchDealsDiv', false);	
	}
	// Else hide the deals search ...
	else
	{
		hidediv('SearchDealsDiv');	
	}
}

/*
 * This method checks if there is an open transaction in the main frame.
 * Given that we need to disallow any global switching if a transaction is already in progress.
 * The check is implemented by checking if the welcome JSP is show in the main transaction frame.
 */
function checkForOpenTransaction()
{	
	frame = getFrameScreen().document.getElementById('desktopFrame');
	if (frame.contentWindow.document.body.innerHTML.indexOf("welcomeTitle")<0)
	{
		errorAlert(20,getLanguageLabel("GBL900053"));
		return false;
	}
	return true;
}

function switchUnit()
{	
    var gblToolbarFrame = getFrameToolbar();
    var frame = getFrameMainFrame().document.getElementById(('mainFrameset'));
	var cols = frame.getAttribute('cols');
	cols = cols.replace("%", "PERCENTAGE");
	cols = cols.replace("%", "PERCENTAGE");

	disableLogoffOnBlankFrame();
	disableLogoffOnBlankFrameLogin();

	if(frame != null)
	{
		window.top.location.replace("../../switchtohome.do?system=" + $('SYSTEM').value + "&alternateUnit=" + $('UNIT').value + "&mainFrameset="+cols + "&optionInput=" + gblToolbarFrame.document.getElementById('optionInput').value + "&" + mandatoryParameterOnLogin());
	}
	else
	{
		window.top.location.replace("../../switchtohome.do?system=" + $('SYSTEM').value + "alternateUnit=" + $('UNIT').value + "&optionInput=" + gblToolbarFrame.document.getElementById('optionInput').value + "&" + mandatoryParameterOnLogin());
	}
}


function resetCurrentContext()
{	
	var sessionIdentifier = getSessionId();
	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName('resetCurrentContext',nsuri);
	var qn_op_resp = new WS.QName('resetCurrentContextResponse',nsuri);

	// Set the parameters required by the service
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier}
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
	$("SearchDivReset").style.display = "none";
	getNaviInfoFromServDir('getCurrentContext', 'SearchDiv', 'eqContext');
}

function setCurrentContext(valuesToSet, unitId)
{	
	var sessionIdentifier = getSessionId();
	// need something to locate the web service defined wsdlsoap:address in the wsdl
	var call = getWSCall(); 
	// We wait for the return prior to processing ..
	var ContextHandler = Class.create();
	ContextHandler.prototype = (new WS.Handler()).extend({
	  on_request : function(envelope) 
	  {
	     // pre-request processing
	  },
	  on_response : function(call,envelope) 
	  {
			var reponse = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();
			if(reponse.indexOf('OK') != -1)
			{
				// The code below is called only when the WS returns ...
				if($('UNIT').value != unitId)
				{
					// switch the unit
					switchUnit();
				}
				else
				{
					// Open the current context shutter & refresh contents ...
					$("SearchDivReset").style.display = "";
					getNaviInfoFromServDir('getCurrentContext','SearchDiv','eqContext');
				
					// Route to the option if necessary ...
					var gblToolbarFrame = getFrameToolbar();
					optionInput = gblToolbarFrame.document.getElementById('optionInput').value;
					if(optionInput != null && optionInput != '')
					{
						routeToOption('*D', unitId, optionInput, '');
					}
				}
			}
			else
			{
				errorAlert(20,reponse);
				return;
			}
	  },
	  on_error : function(call,envelope) {
	  }
	});
	call.add_handler(new ContextHandler());
	
	// The targetNamespace defined in the wsdl
	var nsuri = getEquationServicePath();

	// Define the get and response
	var qn_op = new WS.QName('setCurrentContext',nsuri);
	var qn_op_resp = new WS.QName('setCurrentContextResponse',nsuri);

	// Set the parameters required by the service
	var getSessionParms = 	[
								{name:'sessionIdentifier',value:sessionIdentifier},
								{name:'valuesToSet',value:valuesToSet}
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