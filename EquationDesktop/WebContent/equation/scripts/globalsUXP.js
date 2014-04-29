// These are the functions that are loaded in the UXP and Equation Desktop code base

var webAppName = null;
var defaultButtonProc = true;
var globalDelimeter = '!:!';

// hashtable for the language.js
var languageUser = null;

// initialise the top level cookie
if( window.top.myCookie != undefined )
{
	try
	{
		// Access the myCookie object. This might throw a 
		// "Can't execute code from a freed script" error
		value = window.top.myCookie.get('Test');
	}
	catch(e)
	{
		if( e.number == -2146823277)
		{
			window.top.myCookie = undefined;
		}
	}
}

if (window.top.myCookie == null || typeof(window.top.myCookie) == 'undefined')
{
	window.top.myCookie = new HashTable();
}


/* **********************************************************************************
 * Set a cookie
 * 
 * Parameters:
 * c_name 	  - the name of the cookie
 * value  	  - the value
 * expiredays - expiry date
 * **********************************************************************************/
function setCookie(c_name,value,expiredays)
{
	// since it does not need to persist then store in the hashtable
	if (expiredays == null || typeof(expiredays) == 'undefined')
	{
		window.top.myCookie.put(c_name, value);
	}
	
	// when expire days is specified, then store this as a real cookie
	else
	{
		var exdate=new Date();
		exdate.setDate(exdate.getDate()+expiredays);
		window.top.document.cookie=c_name+ "=" + escape(value) + ((expiredays==null) ? "" : ";expires="+exdate.toGMTString());
	}
}


/* **********************************************************************************
 * Get a cookie
 * **********************************************************************************/
function getCookie(c_name)
{
	// check if it is in the hashtable
	var value = window.top.myCookie.get(c_name);
	if (value != null)
	{
		return value;
	}

	// check if it is a cookie
	if (document.cookie.length>0)
	{
		c_start=window.top.document.cookie.indexOf(c_name + "=");
		if (c_start!=-1)
		{ 
			c_start=c_start + c_name.length+1;
			c_end=window.top.document.cookie.indexOf(";",c_start);
			if (c_end==-1) 
			{
				c_end=window.top.document.cookie.length;
			}
			return unescape(window.top.document.cookie.substring(c_start,c_end));
		} 
	}
	
	return "";
}


/* **********************************************************************************
 * Initialise language.js related  
 * **********************************************************************************/
function initialiseLanguage()
{
	languageUser = getCookie("languageId");
	window.top.languageLabel = new HashTable();
	window.top.languageLabelGB = new HashTable();
	window.top.languageList = new HashTable();
	window.top.languageListGB = new HashTable();
}


/* **********************************************************************************
 * function getXLanguageLabel()
 * Return the language label in user's language hashtable 
 * **********************************************************************************/
function getXLanguageLabel()
{
	var list = null;
	
	// check if current window has the language setup
	list = window.top.languageLabel;
	if (typeof(list) != 'undefined')
	{
		return list;
	}
	
	// check opener
	if (window.top.opener != null)
	{
		list = window.top.opener.getXLanguageLabel();
	}
	if (typeof(list) != 'undefined')
	{
		return list;
	}
	
	// not found
	return null;
}


/* **********************************************************************************
 * function getXLanguageLabel()
 * Return the language label in GB hashtable 
 * **********************************************************************************/
function getXLanguageLabelGB()
{
	var list = null;
	
	// check if current window has the language setup
	list = window.top.languageLabelGB;
	if (typeof(list) != 'undefined')
	{
		return list;
	}
	
	// check opener
	if (window.top.opener != null)
	{
		list = window.top.opener.getXLanguageLabelGB();
	}
	if (typeof(list) != 'undefined')
	{
		return list;
	}
	
	// not found
	return null;
}


/* **********************************************************************************
 * function getXLanguageList()
 * Return the language list in user's language hashtable 
 * **********************************************************************************/
function getXLanguageList()
{
	var list = null;
	
	// check if current window has the language setup
	list = window.top.languageList;
	if (typeof(list) != 'undefined')
	{
		return list;
	}
	
	// check opener
	if (window.top.opener != null)
	{
		list = window.top.opener.getXLanguageList();
	}
	if (typeof(list) != 'undefined')
	{
		return list;
	}
	
	// not found
	return null;
}


/* **********************************************************************************
 * function getXLanguageListGB()
 * Return the language list in GB hashtable 
 * **********************************************************************************/
function getXLanguageListGB()
{
	var list = null;
	
	// check if current window has the language setup
	list = window.top.languageListGB;
	if (typeof(list) != 'undefined')
	{
		return list;
	}
	
	// check opener
	if (window.top.opener != null)
	{
		list = window.top.opener.getXLanguageListGB();
	}
	if (typeof(list) != 'undefined')
	{
		return list;
	}
	
	// not found
	return null;
	
}


/* **********************************************************************************
 * Set a label (language) 
 * **********************************************************************************/
function setLanguageLabel(languageId,labelKey,labelValue)
{	
	// user's language has not been setup?
	if (languageUser == null)
	{
		initialiseLanguage();
	}
	
	// User's language
	if (languageId == languageUser)
	{
		getXLanguageLabel().put(labelKey, labelValue);
	}

	// Default language
	if (languageId == 'GB')
	{
		getXLanguageLabelGB().put(labelKey, labelValue);
	}
}

/* **********************************************************************************
 * Get a label (language)
 * **********************************************************************************/
function getLanguageLabel(labelKey)
{
	// load from user's language
	var labelValue = getXLanguageLabel().get(labelKey);

	// not found in the user's language, then load default language
	if (labelValue == null)
	{
		labelValue = getXLanguageLabelGB().get(labelKey);
	}
	
	// still not found
	if (labelValue == null)
	{
		try
		{
			return getWindowTopLanguageLabel(labelKey);
		}
		catch(e)
		{
			return labelKey;
		}
	}
	
	// still not found
	if (labelValue == null)
	{
		return labelKey;
	}
	
	// return the value
	return labelValue;
}


 /* **********************************************************************************
 * Get a label using the parent's window (language)
 * **********************************************************************************/
function getWindowTopLanguageLabel(labelKey)
{
	if (window.opener != null)
	{
		if (window.opener.opener != null)
		{
			return window.opener.opener.getLanguageLabel(labelKey);
		}
		else
		{
			return window.opener.getLanguageLabel(labelKey);
		}
	}
	
	if (window.top.opener != null)
	{
		try
		{
			obj = window.top.opener.opener;
			if (obj==null)
			{
				obj = window.top.opener;
			}
		}
		catch (e)
		{
			obj = window.top.opener;
		}
		
		// get it
		return obj.getLanguageLabel(labelKey);
	}
}


 
 /* **********************************************************************************
  * Get a label using the parent's window (language)
  * **********************************************************************************/
function getWindowLanguageLabel(labelKey)
{
	if (window.opener != null)
	{
		if (window.opener.opener != null)
		{
			return window.opener.opener.getLanguageLabel(labelKey);
		}
		else
		{
			return window.opener.getLanguageLabel(labelKey);
		}
	}
	else
	{
		return getLanguageLabel(labelKey);
	}
}


/* **********************************************************************************
 * Set a list (language) 
 * **********************************************************************************/
function setLanguageList(languageId,listKey,listValue)
{
	// user's language has not been setup?
	if (languageUser == null)
	{
		languageUser = getCookie("languageId");
	}
	
	// User's language
	if (languageId == languageUser)
	{
		getXLanguageList().put(listKey, listValue);
	}
	
	// Default language
	if (languageId == 'GB')
	{
		getXLanguageListGB().put(listKey, listValue);
	}
}


/* **********************************************************************************
 * Get a list (language)
 * **********************************************************************************/
function getLanguageList(listKey)
{
	// load from user's language
	var listValue = getXLanguageList().get(listKey);
		
	// not found in the user's language, then load default language
	if (listValue == null)
	{
		listValue = getXLanguageListGB().get(listKey);
	}

	// still not found
	if (listValue == null)
	{
		return getWindowTopLanguageList(listKey);
	}
	
	// still not found - return an empty array
	if (listValue == null)
	{
		return new Array();
	}
	
	// return the list
	return listValue;
}


 /* **********************************************************************************
 * Get a label using the parent's window (language)
 * **********************************************************************************/
function getWindowTopLanguageList(labelKey)
{
	if (window.opener != null)
	{
		if (window.opener.opener != null)
		{
			return window.opener.opener.getLanguageList(labelKey);
		}
		else
		{
			return window.opener.getLanguageList(labelKey);
		}
	}
	
	if (window.top.opener != null)
	{
		try
		{
			obj = window.top.opener.opener;
			if (obj==null)
			{
				obj = window.top.opener;
			}
		}
		catch (e)
		{
			obj = window.top.opener;
		}
		
		// get it
		return obj.getLanguageList(labelKey);
	}
}


//**********************************************************************************
//function getAttribute(obj, attributeName)
//Retrieve the attribute value of an object
//
//Parameters:
//obj           - the object
//attributeName - the attribute value to retrieve
//**********************************************************************************
function getAttribute(obj, attributeName)
{
	// not valid?
	if (obj == null || obj.attributes == null)
	{
		return null;
	}
	
	// get attribute
	var node = obj.attributes.getNamedItem(attributeName);
	if (node != null)
	{
		return node.value;
	}
	else
	{
		return null;
	}
}


//*********************************************************************************************
//function HashTable()
//Implementation for an HashTable
//*********************************************************************************************	
function HashTable()
{
	this.length = 0;
	this.items = new Array();
	
	// initialisation for the constructor
	for (var i = 0; i < arguments.length; i += 2) 
	{
		if (typeof(arguments[i + 1]) != 'undefined') 
		{
			this.items[arguments[i]] = arguments[i + 1];
			this.length++;
		}
	};

	//*************************************
	// Get an item
	//*************************************	
	this.get = function(in_key) 
	{
		// get the item
		var tmp_previous = this.items[in_key];
		
		// item does not exist, then set to null
		if (typeof(tmp_previous) == 'undefined')
		{
			tmp_previous = null;
		}
		
		return tmp_previous;
	};

	//*************************************
	// add/set an item
	//*************************************
	this.put = function(in_key, in_value)
	{
		var tmp_previous = null;
		if (typeof(in_value) != 'undefined') 
		{
			if (typeof(this.items[in_key]) == 'undefined') 
			{
				this.length++;
			}
			else 
			{
				tmp_previous = this.items[in_key];
			}

			this.items[in_key] = in_value;
		}
	   
		return tmp_previous;
	};

	//*************************************
	// search an item if it is a key
	//*************************************
	this.containsKey = function(in_key)
	{
		return typeof(this.items[in_key]) != 'undefined';
	};

	//*************************************
	// clear
	//*************************************
	this.clear = function()
	{
		// remove all items
		for (var i in this.items) 
		{
			delete this.items[i];
		}

		// size to 0
		this.length = 0;
	};
	
	//*************************************
	// length
	//*************************************
	this.size = function()
	{
		return this.length;
	};
	
	//*************************************
	// remove an item
	//*************************************
	this.remove = function(in_key)
	{
		// get the item
		var tmp_previous = this.items[in_key];
		
		// item exists, then remove it
		if (typeof(tmp_previous) != 'undefined') 
		{
			this.length--;
			tmp_previous = this.items[in_key];
			delete this.items[in_key];
		}
		
		// item does not exist, then set to null
		else
		{
			tmp_previous = null;
		}
	   
		return tmp_previous;
	};
}


//*********************************************************************************************
//function ArrayList()
//Implementation for an ArrayList
//*********************************************************************************************	
function ArrayList()
{
	this.length = 0;
	this.items = new Array();
	this.i = 0;
	
	// initialisation for the constructor
	for (var i = 0; i < arguments.length; i ++) 
	{
		if (typeof(arguments[i + 1]) != 'undefined') 
		{
			this.items[i] = arguments[i];
			this.length++;
		}
	};

	//*************************************
	// get an item 
	//*************************************
	this.get = function(in_key) 
	{
		// get the item
		var tmp_previous = this.items[in_key];
		
		// item does not exist, then set to null
		if (typeof(tmp_previous) == 'undefined')
		{
			tmp_previous = null;
		}
		
		return tmp_previous;
	};

	//*************************************
	// add an item
	//*************************************
	this.add = function(in_value)
	{
		var tmp_previous = null;
		if (typeof(in_value) != 'undefined') 
		{
			this.items[this.length] = in_value;
			this.length++;
			return true;
		}
		
		// collection has not changed
		return false;
	};

	//*************************************
	// modify an existing item
	//*************************************
	this.set = function(index, in_value)
	{
		var tmp_previous = null;
		
		// make sure it is a valid index
		if (index < 0 || index >= this.length)
		{
			return false;
		}
		
		if (typeof(in_value) != 'undefined') 
		{
			this.items[index] = in_value;
			return true;
		}
		
		// collection has not changed
		return false;
	};

	//*************************************
	// add all items from another list
	//*************************************
	this.addAll = function(list)
	{
		for (this.i=0; this.i<list.size(); this.i++)
		{
			this.add(list.get(this.i));
		}
		
		return list.size() > 0;
	};

	//*************************************
	// search an item
	//*************************************
	this.indexOf = function(in_value)
	{
		return this.indexOfFrom(in_value,0);
	};

	//******************************************************
	// search an item starting from the specified index
	//******************************************************
	this.indexOfFrom = function(in_value, startIndex)
	{
		for (this.i=startIndex; this.i < this.length; this.i++)
		{
			if (this.items[this.i] == in_value)
			{
				return this.i;
			}
		}
		
		// not found
		return -1;
	};

	//******************************************************
	// determine if an item exist
	//******************************************************
	this.contain = function(in_value)
	{
		return this.indexOf(in_value) >= 0;
	};

	//******************************************************
	// clear
	//******************************************************
	this.clear = function()
	{
		this.length = 0;
		this.items.length = 0;
	};
	
	//******************************************************
	// length
	//******************************************************
	this.size = function()
	{
		return this.length;
	};
	
	//*************************************
	// return array
	//*************************************
	this.toArray = function()
	{
		return this.items;
	};
	
}


//**********************************************************************************
// function setOptionId(optionId)
// Set the option id (in the cookie)
//
// Parameters:
// optionId - the option id
//**********************************************************************************
function setOptionId(optionId)
{
	setCookie('optionID', optionId);
}


//**********************************************************************************
// function getOptionId()
// Retrieve the current option id
//
// Parameters:
// None
//**********************************************************************************
function getOptionId()
{
	var optionId = getCookie('optionID'); 
		
	// for uxp, retrieve the option id from the UXP tab
	if (isUXP())
	{
		var tab = getEquationTab();
		optionId = tab.option;
	}
		
	return optionId;
}


//**********************************************************************************
// function setLanguageId(languageId)
// Set the language (in the cookie)
//
// Parameters:
// languageId - the language id
//**********************************************************************************
function setLanguageId(languageId)
{
	setCookie('languageId', languageId);
}


//**********************************************************************************
// function getLanguageId()
// Retrieve the language id
//
// Parameters:
// None
//**********************************************************************************
function getLanguageId()
{
	return getCookie('languageId');
}


//**********************************************************************************
//function retrieveReferralItem(referralItem)
//Generate the HTML element of the referral item
//
//Parameters:
//referralItem - referral item
//**********************************************************************************
function retrieveReferralItem(referralItem)
{
	var authlevel = getAttribute(referralItem, 'authLevel');
	var referredUser =   getAttribute(referralItem, 'referredUser');
	var referredOption = getAttribute(referralItem, 'referredOption');							
	var desktopUser = getAttribute(referralItem, 'desktopUser');							
	var desktopSession = getAttribute(referralItem, 'desktopSession');							
	var desktopTranId = getAttribute(referralItem, 'desktopTranId');
	var supervisor = getAttribute(referralItem, 'supervisor');							
	var screenSetId = getAttribute(referralItem, 'screenSetId');							
	var primeScrn = getAttribute(referralItem, 'primeScrn');							
	var status = getAttribute(referralItem, 'status');							
	var reason = getAttribute(referralItem, 'reason');							
	var offline = getAttribute(referralItem, 'offline');
	var taskType = getAttribute(referralItem, 'taskType');
	var optionTitle = getAttribute(referralItem, 'optionTitle');
	var formattedDate = getAttribute(referralItem, 'formattedDate');
	var formattedTime = getAttribute(referralItem, 'formattedTime');
	var jobDescription = eqTrim(getAttribute(referralItem, 'jobDescription'));
	var sessionUser = getAttribute(referralItem, 'sessionUser');
	var displayUser = getAttribute(referralItem, 'displayUser');
		
	if (taskType == 'BREAKMSG')
	{

		// UXP is running
		if (typeof equxp !== 'undefined')
		{
			eqBreakMessage(status, displayUser, formattedDate, reason); 
			return null;
		} 
		else
		{
			var fromUserText = getLanguageLabel('GBL000048');
			var displayText = null;
			reason = reason.split("<br>").join("\n");
			displayText = fromUserText + "\t" + displayUser + "\t" + formattedDate + "\n\n" + reason;
			displayMessage(displayText);
			return null;
		}
	}

	if (taskType == 'eq.suspend' )
	{
		// logoff was done already
		eqSuspend();	
		return null;
	}
	if (taskType == 'eq.resume' )
	{
		eqResume();
		return null;
	}
	
	var referralItem = new Object();
	referralItem.onClickAction = '';
	referralItem.image = '';
	referralItem.text = '';
	referralItem.hover = '';
	referralItem.isOffline = true;
	
	// authorised by the supervisor
	if (status == '1' && authlevel == 'A')
	{
		referralItem.onClickAction = "var ok=routeToOffOverride('" + referredOption + "','" + desktopSession + "','" + desktopTranId + "','" + desktopUser + "','" + status + "','" + authlevel + "'," + screenSetId+ "," + primeScrn + ")";
		referralItem.image = 'EQWarning.gif';
		referralItem.text  = getLanguageLabel('GBL900041');
		referralItem.text = referralItem.text.replace("%1",displayUser);
		referralItem.text = referralItem.text.replace("%2",referredOption);
		referralItem.hover = referredOption + " " + desktopSession + " " + desktopTranId + " " + desktopUser;
	}
	
	// authorised by the supervisor (partial)
	else if (status == '1')
	{
		referralItem.onClickAction = "var ok=routeToOffOverride('" + referredOption + "','" + desktopSession + "','" + desktopTranId + "','" + desktopUser + "','" + status + "','" + authlevel + "'," + screenSetId+ "," + primeScrn + ")";
		referralItem.image = 'EQWarning.gif';
		referralItem.text  = getLanguageLabel('GBL900052');
		referralItem.text = referralItem.text.replace("%1",displayUser);
		referralItem.text = referralItem.text.replace("%2",referredOption);
		referralItem.hover = referredOption + " " + desktopSession + " " + desktopTranId + " " + desktopUser;
	}
	
	// declined by the supervisor
	else if (status == '4')
	{
		referralItem.onClickAction = "var ok=routeToOffOverride('" + referredOption + "','" + desktopSession + "','" + desktopTranId + "','" + desktopUser + "','" + status + "','" + authlevel + "'," + screenSetId+ "," + primeScrn + ")";
		referralItem.image = 'EQWarning.gif';
		referralItem.text  = getLanguageLabel('GBL900042');
		referralItem.text = referralItem.text.replace("%1",displayUser);
		referralItem.text = referralItem.text.replace("%2",referredOption);
		referralItem.text = referralItem.text + " - " + reason;
		referralItem.hover = referredOption + " " + desktopSession + " " + desktopTranId + " " + desktopUser;
	}
	
	// request aborted by the user
	else if (status == '7')
	{
		referralItem.onClickAction = "var ok=true";
		referralItem.image = 'EQWarning.gif';
		referralItem.text  = getLanguageLabel('GBL900043');
		referralItem.text = referralItem.text.replace("%1",displayUser);
		referralItem.text = referralItem.text.replace("%2",referredOption);
		referralItem.hover = referredOption + " " + desktopSession + " " + desktopTranId + " " + desktopUser;
	}
	
	// request timed-out
	else if (status == '8')
	{
		referralItem.onClickAction = "var ok=true";
		referralItem.image = 'EQWarning.gif';
		referralItem.text  = getLanguageLabel('GBL900045');
		referralItem.text = referralItem.text.replace("%1",displayUser);
		referralItem.text = referralItem.text.replace("%2",referredOption);
		referralItem.hover = referredOption + " " + desktopSession + " " + desktopTranId + " " + desktopUser;
	}
	
	// referred to me by another user
	else if (status == '9')
	{
		referralItem.onClickAction = "var ok=routeToSessionRestore('" + referredOption + "','" + desktopSession + "','" + desktopTranId + "','" + desktopUser + "')";
		referralItem.image = 'EQWarning.gif';
		referralItem.text  = getLanguageLabel('GBL900066');
		referralItem.text = referralItem.text.replace("%1",displayUser);
		referralItem.text = referralItem.text.replace("%2",referredOption);
		referralItem.hover = referredOption + " " + desktopSession + " " + desktopTranId + " " + desktopUser;
	}
	
	// LRP task autorization
	else if (taskType == 'ESF')
	{
		referralItem.onClickAction = "var ok=routeToLRPTask('" + desktopTranId + "','" + taskType + "','" + referredOption + "')";
		referralItem.image = 'EQWarning.gif';
		referralItem.text  = getLanguageLabel('GBL900044');
		referralItem.text = referralItem.text.replace("%1",displayUser);
		referralItem.text = referralItem.text.replace("%2",referredOption);
		referralItem.hover = desktopTranId;
	}
	
	// Maker-Checker transactions
	else if (status == 'S')
	{
		if (sessionUser == referredUser && sessionUser != supervisor)
		{
			// submitted for checking
			if (jobDescription == '')
			{
				referralItem.onClickAction = "var ok=routeToOffOverride2('" + referredOption + "','" + desktopSession + "','" + desktopTranId + "','" + desktopUser + "','" + status + "','" + authlevel + "'," + screenSetId+ "," + primeScrn + ")";
			}
			else
			{
				referralItem.onClickAction = "var ok=routeToOption2('*D',unitId,'SBL','')";
			}
		}
		else
		{
			// awaiting checking
			if (jobDescription == '')
			{
				referralItem.onClickAction = "var ok=routeToSupervisor2('" + referredOption + "','" + desktopSession + "','" + desktopTranId + "','" + desktopUser + "'," + screenSetId+ "," + primeScrn + ")";
				referralItem.isOffline = (offline == 'Y');
			}
			else
			{
				referralItem.onClickAction = "var ok=routeToOption2('*D',unitId,'AWL','')";
			}
		}
		referralItem.image = 'EQWarning.gif';
		//referralItem.text  = getLanguageLabel('GBL900089');
		referralItem.text  = getLanguageLabel('GBL900089');
		referralItem.text = referralItem.text.replace("%1",displayUser);
		referralItem.text = referralItem.text.replace("%2",formattedDate + " " + formattedTime + " " + referredOption + " " + optionTitle + " " + desktopTranId);
		referralItem.hover = referredOption + " " + desktopSession + " " + desktopTranId + " " + desktopUser;
	}

	// approved by checker
	else if (status == 'P')
	{	
		if (jobDescription == '')
		{
			referralItem.onClickAction = "var ok=routeToOffOverride2('" + referredOption + "','" + desktopSession + "','" + desktopTranId + "','" + desktopUser + "','" + status + "','" + authlevel + "'," + screenSetId+ "," + primeScrn + ")";
		}
		else
		{
			referralItem.onClickAction = "var ok=routeToOption2('*D',unitId,'ARL','')";
		}
		referralItem.image = 'EQWarning.gif';
		referralItem.text  = getLanguageLabel('GBL900090');
		referralItem.text = referralItem.text.replace("%1",displayUser);
		referralItem.text = referralItem.text.replace("%2",formattedDate + " " + formattedTime + " " + referredOption + " " + optionTitle + " " + desktopTranId);
		referralItem.hover = referredOption + " " + desktopSession + " " + desktopTranId + " " + desktopUser;
	}
	
	// rejected by checker
	else if (status == 'R')
	{
		if (jobDescription == '')
		{
			referralItem.onClickAction = "var ok=routeToOffOverride2('" + referredOption + "','" + desktopSession + "','" + desktopTranId + "','" + desktopUser + "','" + status + "','" + authlevel + "'," + screenSetId+ "," + primeScrn + ")";	
		}
		else
		{
			referralItem.onClickAction = "var ok=routeToOption2('*D',unitId,'RJL','')";
		}
		referralItem.image = 'EQWarning.gif';
		referralItem.text  = getLanguageLabel('GBL900091');
		referralItem.text = referralItem.text.replace("%1",displayUser);
		referralItem.text = referralItem.text.replace("%2",formattedDate + " " + formattedTime + " " + referredOption + " " + optionTitle + " " + desktopTranId);
		referralItem.text = referralItem.text + " - " + reason;
		referralItem.hover = referredOption + " " + desktopSession + " " + desktopTranId + " " + desktopUser;
	}
	
	//completed by checker
	else if (status == 'C')
	{
		referralItem.onClickAction = "var ok=true";
		referralItem.image = 'EQWarning.gif';
		referralItem.text  = getLanguageLabel('GBL900092');
		referralItem.text = referralItem.text.replace("%1",displayUser);
		referralItem.text = referralItem.text.replace("%2",formattedDate + " " + formattedTime + " " + referredOption + " " + optionTitle + " " + desktopTranId);
		referralItem.hover = referredOption + " " + desktopSession + " " + desktopTranId + " " + desktopUser;
	}
	
	// Green screen supervisor authorisation
	else if (jobDescription !='' || eqTrim(desktopUser)=='')
	{
		referralItem.onClickAction = 'var ok=refokButtonClick()';
		referralItem.image = 'EQWarning.gif';
		referralItem.text  = getLanguageLabel('GBL900044');
		referralItem.text = referralItem.text.replace("%1",displayUser);
		referralItem.text = referralItem.text.replace("%2",referredOption);
		referralItem.hover = referredOption + " " + referredUser;
		referralItem.isOffline = false;
	}
	
	// Desktop screen supervisor authorisation
	else
	{
		referralItem.onClickAction = "var ok=routeToSupervisor('" + referredOption + "','" + desktopSession + "','" + desktopTranId + "','" + desktopUser + "'," + screenSetId+ "," + primeScrn + ")";
		referralItem.image = 'EQWarning.gif';
		referralItem.text  = getLanguageLabel('GBL900044');
		referralItem.text = referralItem.text.replace("%1",displayUser);
		referralItem.text = referralItem.text.replace("%2",referredOption);
		referralItem.hover = referredOption + " " + desktopSession + " " + desktopTranId + " " + desktopUser;
		referralItem.isOffline = (offline == 'Y');
	}

	// Add date/time if not already included in the referralItem.text
	if(status !='C' && status !='P' && status != 'R' && status != 'S' )
	{
		referralItem.text = formattedDate + " " + formattedTime + " " + referralItem.text;
	}
	
	// return the item
	return referralItem;
}


function eqTrim(str)
{
	return(str.replace(/^\s+/,'').replace(/\s+$/,''));
}

function eqSuspend()
{
	var suspendMessage = getLanguageLabel('GBL900117');
	equxp.reloadMenuAction();	
	eqBreakMessage('INFORMATION', null, null, suspendMessage); 
}
function eqResume()
{
	var resumeMessage = getLanguageLabel('GBL900118');
	equxp.eqLoginAction();
	equxp.reloadMenuAction();
	eqBreakMessage('INFORMATION', null, null, resumeMessage); 
}
//*********************************************************************************************
//function eqBreakMessage
//Display break message
//
//Parameters:
//status - INFORMATION, WARNING, or ERROR
//sender - sender name
//formattedDateTime - the date and time to display
//message - the message to display
//*********************************************************************************************	
eqBreakMessage = function(status, sender, formattedDateTime, message) 
{
	var fromUserText = getLanguageLabel('GBL000048');
	var displayText = null;
	// HTML
	// replace doggy HTML just in case but preserve line breaks
	message = message.split("<br>").join("\n");
	message = message.split("<").join("&lt");
	message = message.split(">").join("&gt");
	message = message.split("\n").join("<br>");
	if (sender == null || eqTrim(sender)=='')
	{
		if (formattedDateTime == null || formattedDateTime == '')
		{
			displayText = message;
		}
		else
		{	
			displayText = formattedDateTime + "<br><br>" + message;
		}
	}
	else
	{
		if (formattedDateTime == null || formattedDateTime == '')
		{
			displayText = fromUserText + "&nbsp;&nbsp;&nbsp;&nbsp;" + sender + "<br><br>" + message;
		}
		else
		{	
			displayText = fromUserText + "&nbsp;&nbsp;&nbsp;&nbsp;" + sender + "&nbsp;&nbsp;&nbsp;&nbsp;" + formattedDateTime + "<br><br>" + message;
		}
	}
	window.top.equxp.renderMessage(status, displayText);
	return null;
};
// After loading this script, if running in window.top in the UX Desktop, then 
// also load language.js 
if( typeof(equxp) != 'undefined' )
{
	equxp.loadJavaScript(equxp.getEquationWebApp() + '/equation/scripts/language.js');
}


