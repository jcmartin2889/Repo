// *****************************************************************************
//  Copyright and all other intellectual property rights in this software, 
//  in any form, is vested in Misys International Banking Systems Ltd ("Misys") 
//  or a related company.                                  
//                                                     
//  This software may not be copied, amended, compiled, translated, or developed
//  or sold, leased, hired, rented, or disclosed to any third party without the 
//  prior written consent of Misys.                     
//                                                     
//  Copyright © Misys International Banking Systems Ltd                           
// *****************************************************************************

//Referral list
var refHTML = "";
var referralTimeout;

// **********************************************************************************
// function getEqReferral(referralStatusId)
// 
// Invoke the Web Service to get the EqReferral object serialised as XML.
// **********************************************************************************	

function getEqReferral(referralStatusId)
{	
	if (window.top.poolsDestroyed != true)
	{
		var gblCurrentFrame = getFrameScreen();
		
		// need something to locate the web service defined wsdlsoap:address in the wsdl
		var call = getWSCall();
		 
		// The targetNamespace defined in the wsdl
		var nsuri = getEquationServicePath();
	
		// get the referral element that we want to populate
		var referralStatus = document.getElementById(referralStatusId);
	
		// Define the get and response
		var qn_op = new WS.QName('getEqReferral',nsuri);	
	
		// Set the pararmeters required by the service
		var sessionIdentifier = getSessionId();
		var getSessionParms = 	[
									{name:'sessionIdentifier',value:sessionIdentifier}
								];
	
		// Call the web service
		call.invoke_rpc
		(
			qn_op,
			getSessionParms,
			null,
			function(call,envelope) 
			{	
				
				var referral = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();			
				setRefMsgs(referral);
			
				referralTimeout = setTimeout(function (){							  
					     getEqReferral(referralStatusId); 
					    },30000);
			}
		);
	}
} 

//**********************************************************************************
//function  loadXMLString(txt) 
//Loads the XML document
//
//Parameters: 
//txt        - the XML string
//********************************************************************************** 
function loadXMLString(txt) 
{
	if (window.DOMParser)
	{
		parser=new DOMParser();
		xmlDoc=parser.parseFromString(txt,"text/xml");
	}
	else // Internet Explorer
	{
		xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.async="false";
		xmlDoc.loadXML(txt); 
	}
	return xmlDoc;
}

//**********************************************************************************
//function  getAttribute(referralItem, attribute) 
//Retrieves the attribute from the element
//
//Parameters: 
//referralItem        - the referral item
//attribute			  - the attribute
//********************************************************************************** 
function getAttribute(referralItem, attribute) 
{
	var attribute = referralItem.getAttribute(attribute);
	if (attribute == null) 
	{
		attribute = '';
	}
	
	return attribute;
}

// **********************************************************************************
// function  clearRefTimeout()
// Removes the referral timeout
//
// Parameters: none
// ********************************************************************************** 
function clearRefTimeout()
{
	try
 	{
	   clearTimeout(referralTimeout);
 	}
	catch(e){}
}

//**********************************************************************************
//function setRefMsgs(referral)
//Populate the referral tabs
//
//Parameters:
//referral        - the referral xml list
//********************************************************************************** 
function setRefMsgs(referral)
{
	var gblReferralsFrame = getFrameTab().frames['referralsFrame'];
	var docID = gblReferralsFrame.document;
	var divID = docID.getElementById('referralsDiv');	
	var textHTML = new String();
	var msgfHTML = new String();
	msgfHTML = "";	
	
	// Retrieve referral items
	xmlDoc = loadXMLString(referral);
	referralItems = xmlDoc.getElementsByTagName("ReferralItem");
	
	// No referral items
	if (referralItems == null || referralItems.length == 0) 
	{
		return;
	}
	
	var newAlert;
	var referralIndexElement = document.getElementById('referralIndex');
	var referralIndex = parseInt(referralIndexElement.value);
	var tableElement = document.getElementById('referralTable');

	// Display all the details if there are any
	for(x=0; x<referralItems.length; x++)
	{
		var referralItem = retrieveReferralItem(referralItems[x]);
		
		if (referralItem != null)
		{
			newAlert = true;
			// add standard on click action
			referralItem.onClickAction = referralItem.onClickAction + '; if (ok) {referralClick(' + referralIndex + ')};';
		
			// Add an new row
			var rowElement = tableElement.insertRow(0);
			rowElement.id = "refrow_" + referralIndex;
		
			// Add first cell
			var cell = rowElement.insertCell(0);
			cell.className = 'labelText';
			cell.innerHTML = '<input type="checkbox" id="chkReferral_' + referralIndex + '" name="chkReferral_' + referralIndex + '" value="Y">';
		
			// Add second cell
			var cell = rowElement.insertCell(1);
			cell.className = 'labelText';
		
			if (referralItem.isOffline)
			{
				cell.innerHTML = '<a href="javascript:' + referralItem.onClickAction + '" title="' + referralItem.hover + '">' +
				referralItem.text +
				'</a>';
			}
			else
			{
				cell.innerHTML = '<a class="onlineRequest" href="javascript:' + referralItem.onClickAction + '" title="' + referralItem.hover + '">' +
				referralItem.text +
				'</a>';
			}

			// Next index
			referralIndex++;
		}
	}
	
	if( newAlert)
	{
		// update the index
		referralIndexElement.value = referralIndex;
		alertNewReferral();
	}
}


// **********************************************************************************
// function refcancelButtonClick()
// Hide the popup if the cancel button is clicked
//
// Parameters: None
// **********************************************************************************
function refcancelButtonClick()
{
		window.top.REFPopup.hidePopup(gblCurrentFrame.document);						
}

// **********************************************************************************
// function refokButtonClick()
// Accept the selected options if the OK button is clicked
//
// Parameters: None
// **********************************************************************************
function refokButtonClick()
{								
	var mode = '*D';	
	return routeToOption(mode,unitId,'AU','');
}

// **********************************************************************************
// function referralClick(index)
// Delete the named alert in the list of alert
//
// Parameters:
// index - id suffix of the alert
// **********************************************************************************
function referralClick(index)
{
	// loop through the table
	var tableElement = document.getElementById('referralTable');
	var rows = tableElement.getElementsByTagName('tr');
	for (i=0; i<rows.length; i++)
	{
		var id = 'refrow_' + index;
		if (rows[i].id == id)
		{
			tableElement.deleteRow(i);
			break;
		}
	}
}		
	
// **********************************************************************************
// function deleteAll()
// Delete all the alert messages
//
// Parameters:
// None
// **********************************************************************************
function deleteAll()
{
	// loop through the table
	var tableElement = document.getElementById('referralTable');
	while (tableElement.rows.length>0)
	{
		tableElement.deleteRow(0);
	}
}

// **********************************************************************************
// function deleteSel()
// Delete selected alerts
//
// Parameters:
// None
// **********************************************************************************
function deleteSel()
{
	// loop through the table
	var tableElement = document.getElementById('referralTable');
	var rows = tableElement.getElementsByTagName('tr');
	
	for (i=rows.length-1; i>=0; i--)
	{
		var pos = (rows[i].id).indexOf('_');
		var index = (rows[i].id).substr(pos+1);
		var id = 'chkReferral_' + index;
		var chkElement = document.getElementById(id);
		if (chkElement.checked)
		{
			tableElement.deleteRow(i);
		}
	}

}