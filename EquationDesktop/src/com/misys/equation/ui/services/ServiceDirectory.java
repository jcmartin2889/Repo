package com.misys.equation.ui.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.misys.equation.bankfusion.lrp.engine.ITaskEngine;
import com.misys.equation.bankfusion.tools.BFToolbox;
import com.misys.equation.bankfusion.tools.LRPToolbox;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationPVMetaData;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.access.IDataList;
import com.misys.equation.common.access.UnitNotAvailableException;
import com.misys.equation.common.datastructure.EqDS_DSWSI2;
import com.misys.equation.common.datastructure.EqDS_DSWSID;
import com.misys.equation.common.globalcustomers.GlobalCustomersAccess;
import com.misys.equation.common.globalprocessing.GPSearch;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.search.results.PropertyType;
import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.adaptor.FieldSetAdaptor;
import com.misys.equation.function.adaptor.FunctionAdaptor;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.PVFieldSet;
import com.misys.equation.function.context.BFEQCredentials;
import com.misys.equation.function.context.EquationFunctionContext;
import com.misys.equation.function.runtime.FunctionContextHandler;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionHandlerData;
import com.misys.equation.function.runtime.FunctionSession;
import com.misys.equation.function.tools.RepeatingGroupPagingDetail;
import com.misys.equation.function.tools.SupervisorToolbox;
import com.misys.equation.ui.context.EquationDesktopContext;
import com.misys.equation.ui.tools.EqDesktopToolBox;

/**
 * WebService implementation class
 * 
 * After adding/removing methods or parameters in this class, to re-generate the WebService definitions, right-click on the file and
 * take the Web Services | Create Web service option. Ensure the generate service (top) slider is at "Start" and press Finish.
 */
public class ServiceDirectory
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ServiceDirectory.java 17587 2013-11-11 23:35:01Z williae1 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(ServiceDirectory.class);

	public String performActionOnUnitCustomer(String sessionIdentifier, String globaCustomerNum, String action, int sequenceNumber)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		globaCustomerNum = nullValidation(globaCustomerNum);
		action = nullValidation(action);

		sequenceNumber--; // JavaScript Problem when passing 0 ...
		try
		{
			if (action.equals("moveup"))
			{
				int selected = GlobalCustomersAccess.getImpl(sessionIdentifier).moveUnitCustomerLinkagePositionUp(
								sessionIdentifier, globaCustomerNum, sequenceNumber);
				return GlobalCustomersAccess.getImpl(sessionIdentifier).getLinkedCustomersHTMLList(sessionIdentifier,
								globaCustomerNum, selected);
			}
			else if (action.equals("movedown"))
			{
				int selected = GlobalCustomersAccess.getImpl(sessionIdentifier).moveUnitCustomerLinkagePositionDown(
								sessionIdentifier, globaCustomerNum, sequenceNumber);
				// Finally we return the newly generated content ...
				return GlobalCustomersAccess.getImpl(sessionIdentifier).getLinkedCustomersHTMLList(sessionIdentifier,
								globaCustomerNum, selected);

			}
			else if (action.equals("delete"))
			{
				GlobalCustomersAccess.getImpl(sessionIdentifier).deleteUnitCustomer(sessionIdentifier, globaCustomerNum,
								sequenceNumber);
			}
			else if (action.startsWith("updatecustomer"))
			{
				// updatecustomer:isSynchroniseFlag=true
				final String[] str = action.split(":");
				final String[] str2 = str[1].split("=");
				final String propertyId = str2[0];
				boolean value = str2[1].equals("true") ? true : false;
				GlobalCustomersAccess.getImpl(sessionIdentifier).updateUnitCustomerFlag(sessionIdentifier, globaCustomerNum,
								sequenceNumber, propertyId, value);
			}
			else if (action.startsWith("addcustomer"))
			{
				String[] str = action.split(":");

				String system = "";
				if (str[1].split("=").length == 2)
				{
					system = str[1].split("=")[1];
				}

				String unit = "";
				if (str[2].split("=").length == 2)
				{
					unit = str[2].split("=")[1];
				}

				String customerNumber = "";
				if (str[3].split("=").length == 2)
				{
					customerNumber = str[3].split("=")[1];
				}

				String customerMnemonic = "";
				if (str[4].split("=").length == 2)
				{
					customerMnemonic = str[4].split("=")[1];
				}

				String customerLocation = "";
				if (str[5].split("=").length == 2)
				{
					customerLocation = str[5].split("=")[1];
				}

				String customerName = str[6].split("=")[1];
				GlobalCustomersAccess.getImpl(sessionIdentifier).addUnitCustomer(sessionIdentifier, globaCustomerNum, system, unit,
								customerNumber, customerMnemonic, customerLocation, customerName);
			}
			// Finally we return the newly generated content ...
			return GlobalCustomersAccess.getImpl(sessionIdentifier).getLinkedCustomersHTMLList(sessionIdentifier, globaCustomerNum);
		}
		catch (EQException e)
		{
			return "";
		}
	}
	public String getGlobalCustomerLinkageList(String sessionIdentifier, String globaCustomerNum)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		globaCustomerNum = nullValidation(globaCustomerNum);

		try
		{
			return GlobalCustomersAccess.getImpl(sessionIdentifier).getLinkedCustomersHTMLList(sessionIdentifier, globaCustomerNum);
		}
		catch (EQException e)
		{
			return "";
		}
	}

	/**
	 * This method sets the current global context on the data area. It receives a comma delimited property/value string and sets
	 * the values accordingly
	 * 
	 * @param sessionIdentifier
	 *            - Unique session ID mapped to the client web session
	 * @param valuesToSet
	 *            - Comma delimited property/value string
	 */
	public String setCurrentContext(String sessionIdentifier, String valuesToSet)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		valuesToSet = nullValidation(valuesToSet);

		// Reset first...
		resetCurrentContext(sessionIdentifier);

		GPSearch gpSearch = new GPSearch();

		try
		{
			// Get a reference to the data area ...
			FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionIdentifier);
			FunctionHandlerData functionHandlerData = functionHandler.getFhd();
			FunctionContextHandler functionContextHandler = functionHandlerData.getGlobalProcessingContextHandler();

			// Remove any context data items that we set previously ...
			List<String> valuesToRemove = gpSearch.getDataContextHistory(sessionIdentifier);
			if (valuesToRemove != null)
			{
				for (String value : valuesToRemove)
				{
					functionContextHandler.setFieldValue(value, "");
				}
			}

			// From the input string we build a list of property types (these will be passed to the recordSelectedSearch) ...
			final String[] tuples = valuesToSet.split(",");
			final Map<PropertyType, String> properties = new HashMap<PropertyType, String>();
			for (String tuple : tuples)
			{
				String[] keyValuePair = tuple.split("=");
				if (keyValuePair.length == 2)
				{
					properties.put(PropertyType.valueOf(keyValuePair[0]), keyValuePair[1]);
				}
			}

			// After this we need to filter the properties that need to be saved in the context data area ...
			final List<String> listOfFieldsAddedToContext = new ArrayList<String>();
			for (Entry<PropertyType, String> propertyType : properties.entrySet())
			{
				final String dataAreaPropertyKey = PropertyType.mappings.get(propertyType.getKey());
				if (dataAreaPropertyKey != null)
				{
					functionContextHandler.setFieldValue(dataAreaPropertyKey, properties.get(propertyType.getKey()));
					listOfFieldsAddedToContext.add(dataAreaPropertyKey);
				}
			}

			// Keep track of what we set ...
			gpSearch.setDataContextHistory(sessionIdentifier, listOfFieldsAddedToContext);

			// Finally once the context is set we need to update the recent search history ...
			final String selectedSearchId = properties.get(PropertyType.ID);
			if (selectedSearchId == null)
			{
				properties.put(PropertyType.TIME_STAMP, String.valueOf(new Date().getTime()));
				gpSearch.recordSelectedSearch(sessionIdentifier, properties);
			}
			else
			{
				gpSearch.updateSearchItem(sessionIdentifier, Long.parseLong(selectedSearchId));
			}
			return "OK";

		}
		catch (Exception e)
		{
			// TODO Do we need to roll back changes if one of the writes to the data area fails ?
			return Toolbox.getExceptionMessage(e);
		}
	}

	/**
	 * This method resets the current global context on the data area
	 * 
	 * @param sessionIdentifier
	 *            - Unique session ID mapped to the client web session
	 */
	public void resetCurrentContext(String sessionIdentifier)
	{
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		try
		{
			FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionIdentifier);
			FunctionHandlerData functionHandlerData = functionHandler.getFhd();
			FunctionContextHandler functionContextHandler = functionHandlerData.getGlobalProcessingContextHandler();
			functionContextHandler.reset();
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
	}

	/**
	 * This web service method is invoked to retrieve the current context for the user.
	 * 
	 * @param sessionIdentifier
	 *            - Unique session ID mapped to the client web session
	 * 
	 * @return String - HTML content containing context information
	 */
	public String getCurrentContext(String sessionIdentifier)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		// Define the place holder for the HTML content
		final StringBuffer content = new StringBuffer();

		try
		{
			// Get a reference to the data area ...
			FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionIdentifier);
			FunctionHandlerData functionHandlerData = functionHandler.getFhd();
			FunctionContextHandler functionContextHandler = functionHandlerData.getGlobalProcessingContextHandler();

			// Show the customer current context (if any) ...
			final String customerNumber = functionContextHandler.getFieldValue(EqDS_DSWSID.CBNO);
			final String customerMnemonic = functionContextHandler.getFieldValue(EqDS_DSWSID.CUS);
			final String customerLocation = functionContextHandler.getFieldValue(EqDS_DSWSID.CLC);
			final StringBuffer customerContent = new StringBuffer();

			final EquationUser eqUser = EquationCommonContext.getContext().getEquationUser(sessionIdentifier);

			if (customerNumber != null && !customerNumber.equals(""))
			{
				customerContent.append("&nbsp;&nbsp;&nbsp;<b>"
								+ EquationCommonContext.getContext().getLanguageResource(eqUser, "search.context.customerno")
								+ "</b> " + customerNumber.trim());
				customerContent.append("<BR/>");
			}
			if (customerMnemonic != null && !customerMnemonic.equals(""))
			{
				customerContent.append("&nbsp;&nbsp;&nbsp;<b>"
								+ EquationCommonContext.getContext().getLanguageResource(eqUser, "search.context.customermnem")
								+ "</b> " + customerMnemonic.trim());
			}
			if (customerLocation != null && !customerLocation.equals(""))
			{
				customerContent.append(" " + customerLocation.trim());
			}
			if (customerContent.toString().length() > 0)
			{
				customerContent.append("<BR/>");
				content.append(customerContent);
				content.append("<HR/>");
			}

			// Show the deal current context (if any) ...
			final String accountBranch = functionContextHandler.getFieldValue(EqDS_DSWSI2.AB);
			final String accountNumber = functionContextHandler.getFieldValue(EqDS_DSWSI2.AN);
			final String accountSuffix = functionContextHandler.getFieldValue(EqDS_DSWSI2.AS);
			final String externalAccountNumber = functionContextHandler.getFieldValue(EqDS_DSWSI2.EAN);
			final String iban = functionContextHandler.getFieldValue(EqDS_DSWSI2.IBAN);
			final StringBuffer accountContent = new StringBuffer();
			if (accountBranch != null && !accountBranch.equals("") && accountNumber != null && !accountNumber.equals("")
							&& accountSuffix != null && !accountSuffix.equals(""))
			{
				accountContent.append("&nbsp;&nbsp;&nbsp;<b>"
								+ EquationCommonContext.getContext().getLanguageResource(eqUser, "search.context.accountno")
								+ "</b> " + accountBranch.trim());
				accountContent.append("-");
				accountContent.append(accountNumber.trim());
				accountContent.append("-");
				accountContent.append(accountSuffix.trim());
				accountContent.append("<BR/>");
			}
			if (externalAccountNumber != null && !externalAccountNumber.equals(""))
			{
				accountContent.append("&nbsp;&nbsp;&nbsp;<b>"
								+ EquationCommonContext.getContext().getLanguageResource(eqUser, "search.context.externalaccount")
								+ "</b> " + externalAccountNumber.trim());
				accountContent.append("<BR/>");
			}
			if (iban != null && !iban.equals(""))
			{
				accountContent.append("&nbsp;&nbsp;&nbsp;<b>"
								+ EquationCommonContext.getContext().getLanguageResource(eqUser, "search.context.iban") + "</b> "
								+ iban.trim());
				accountContent.append("<BR/>");
			}
			if (accountContent.toString().length() > 0)
			{
				content.append(accountContent);
				content.append("<HR/>");
			}

			// Show the deal current context (if any) ...
			final String dealType = functionContextHandler.getFieldValue(EqDS_DSWSID.DLP);
			final String dealReference = functionContextHandler.getFieldValue(EqDS_DSWSID.DLR);
			final String dealBranchNo = functionContextHandler.getFieldValue(EqDS_DSWSID.CBBN);
			final StringBuffer dealContent = new StringBuffer();
			if (dealType != null && !dealType.equals("") && dealReference != null && !dealReference.equals("")
							&& dealBranchNo != null && !dealBranchNo.equals(""))
			{
				dealContent.append("&nbsp;&nbsp;&nbsp;<b>"
								+ EquationCommonContext.getContext().getLanguageResource(eqUser, "search.context.dealref")
								+ "</b> ");
				dealContent.append(dealBranchNo.trim());
				dealContent.append("-");
				dealContent.append(dealType.trim());
				dealContent.append("-");
				dealContent.append(dealReference.trim());
				dealContent.append("<BR/>");
			}

			if (dealContent.toString().length() > 0)
			{
				content.append(dealContent);
				content.append("<HR/>");
			}

			// Show the branch current context (if any) ...
			final String branchNo = functionContextHandler.getFieldValue(EqDS_DSWSID.CBBN);
			final String branchMnem = functionContextHandler.getFieldValue(EqDS_DSWSID.BRNM);
			final StringBuffer branchContent = new StringBuffer();
			if (branchNo != null && !branchNo.equals("") && (dealType == null || dealType.equals("")))
			{
				branchContent.append("&nbsp;&nbsp;&nbsp;<b>"
								+ EquationCommonContext.getContext().getLanguageResource(eqUser, "search.context.branchno")
								+ "</b> " + branchNo.trim());
				branchContent.append("<BR/>");
			}
			if (branchMnem != null && !branchMnem.equals(""))
			{
				branchContent.append("&nbsp;&nbsp;&nbsp;<b>"
								+ EquationCommonContext.getContext().getLanguageResource(eqUser, "search.context.branchmnem")
								+ "</b> " + branchMnem.trim());
				branchContent.append("<BR/>");
			}
			if (branchContent.toString().length() > 0)
			{
				content.append(branchContent);
				content.append("<HR/>");
			}

			final String optionId = functionContextHandler.getFieldValue(EqDS_DSWSID.OPD);
			final StringBuffer optionContent = new StringBuffer();
			if (optionId != null && !optionId.equals(""))
			{
				content.append(optionContent);
				content.append("<HR/>");
			}

			if (content.length() > 0)
			{
				content.insert(0, "<HR/>");
			}
			content.append("<BR/>");
		}
		catch (Exception e)
		{
			content.append("<BR/>");
			// EquationCommonContext.getContext().getLOG().error(e);
		}

		// finally return the content ...
		return content.toString();
	}

	/**
	 * Return the session identifier
	 * 
	 * @param systemId
	 *            - the system
	 * @param unitId
	 *            - the unit mnemonic
	 * @param userId
	 *            - the user id
	 * @param password
	 *            - the password (encrypted)
	 * @param sessionIdentifier
	 *            - the session identifier
	 * @param ipAddress
	 *            - the IP address
	 * 
	 * @return the new session identifier
	 */
	public String getEqSession(String systemId, String unitId, String userId, String password, String sessionIdentifier,
					String ipAddress, String passwordType)
	{
		try
		{
			sessionIdentifier = nullValidation(sessionIdentifier);
			// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
			EqDesktopToolBox.setupThreadData(sessionIdentifier);

			systemId = nullValidation(systemId);
			unitId = nullValidation(unitId);
			password = nullValidation(password);
			ipAddress = nullValidation(ipAddress);
			passwordType = nullValidation(passwordType);

			int sessionType = EquationCommonContext.SESSION_OTHER_MODE;

			if (passwordType.equals(EquationCommonContext.PASSWORD_TYPE_PROFILETOKEN_ENCRYPTED))
			{
				// decrypt the password and set the type to decrypted
				password = EquationCommonContext.getContext().decryptPassword(sessionIdentifier, password);
				passwordType = EquationCommonContext.PASSWORD_TYPE_PROFILETOKEN_PLAIN;
				// For encrypted password (profile token or plain text), assume this is
				// a call from SRVWEB
				sessionType = EquationCommonContext.SESSION_SRVWEB;
			}
			if (passwordType.equals(EquationCommonContext.PASSWORD_TYPE_TEXT_ENCRYPTED))
			{
				// decrypt the password and set the type to decrypted
				password = EquationCommonContext.getContext().decryptPassword(sessionIdentifier, password);
				passwordType = EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN;
				sessionType = EquationCommonContext.SESSION_SRVWEB;
			}
			if (passwordType.equals(EquationCommonContext.PASSWORD_TYPE_TEXT_PADDED_ENCRYPTED))
			{
				// Padding (e.g. to 50 characters) prevent rogue characters appended to the end of
				// decrypted passwords, but this requires trimming:
				password = EquationCommonContext.getContext().decryptPassword(sessionIdentifier, password.trim()).trim();
				passwordType = EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN;
				// This is a call from SRVWEB
				sessionType = EquationCommonContext.SESSION_SRVWEB;
			}

			BFEQCredentials credentials = new BFEQCredentials(userId, password, passwordType, sessionIdentifier);

			// Session id/user id combination issue when 1 WAS profile used by UI and web services. Web Services should use pooled
			// connections and problem will disappear when pooled connections are used.
			String equationIseriesProfile = null;

			if (EquationCommonContext.isCASAuthentication())
			{
				equationIseriesProfile = EquationFunctionContext.getContext().getiSeriesUserForBFUser(
								EquationCommonContext.getContext().getEquationUnit(systemId, unitId), userId);
			}
			else
			{
				equationIseriesProfile = userId.toUpperCase();
			}

			return EquationFunctionContext.getContext().getEqSession(systemId, unitId, credentials, ipAddress, sessionType,
							equationIseriesProfile);
		}
		catch (Exception e)
		{
			LOG.error(e);
			return "";
		}
	}

	/**
	 * Exchange Blowfish keys
	 * 
	 * Exchange the partial keys that together make up the Blowfish key
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 * @param key
	 *            the partial key generated by Equation (16 bytes)
	 * 
	 * @return the partial key generated by the server
	 */
	public String exchangeBFKey(String sessionIdentifier, String key)
	{
		return EquationCommonContext.getContext().exchangeEncryptionKey(sessionIdentifier, key);
	}

	/**
	 * Invokes the Java processing for a RPG Validation User Exit
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 * @param program
	 *            - Name of the RPG validation program
	 * @param xfct
	 * @param xprmt
	 * @param xscrn
	 * @param xmode
	 * @param dsexit
	 * 
	 * @return String buffer containing messages and the updated screen DS
	 */
	public String userExitValidate(String sessionIdentifier, String program, String xfct, String xprmt, String xscrn, String xmode,
					String dsexit)
	{
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		return EquationCommonContext.getContext().getEqUserExit(sessionIdentifier, program, xfct, xprmt, xscrn, xmode, dsexit);
	}

	/**
	 * Return the Equation Data List <code>EqDataList</code>
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param service
	 *            - P/V program name
	 * @param decode
	 *            - decode character
	 * @param primaryTable
	 *            - NOT USED
	 * @param serviceKey
	 *            - NOT USED
	 * @param query
	 *            - query
	 * @param start
	 *            - relative key to the direction
	 * @param direction
	 *            - backward (-1) or forward (1)
	 * @param maxResults
	 *            - maximum record to retrieved
	 * 
	 * @return the data list
	 */
	public String getEqDataList(String sessionIdentifier, String fieldId, String service, String decode, String security,
					String primaryTable, String serviceKey, String query, String start, int direction, int maxResults)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		fieldId = nullValidation(fieldId);
		service = nullValidation(service);
		decode = nullValidation(decode);
		security = nullValidation(security);
		primaryTable = nullValidation(primaryTable);
		serviceKey = nullValidation(serviceKey);
		query = nullValidation(query);
		start = nullValidation(start);

		try
		{
			final EquationCommonContext ecc = EquationCommonContext.getContext();
			EquationUser eqUser = ecc.getEquationUser(sessionIdentifier);
			if (!ecc.sessionValid(sessionIdentifier))
			{
				return new StringBuilder().append("!#ERRORS#!").append("          ").append(
								EquationCommonContext.getContext().getLanguageResource(eqUser, "search.sessionerror")).toString();
			}

			EquationPVMetaData pvMetaData = ecc.getEquationUnit(sessionIdentifier).getPVMetaData(service);

			// THIS PIECE IS CODE IS FOR GLOBAL PROCESSING ONLY!
			String currentUnit = null;
			if (EquationCommonContext.getContext().checkIfGPIsInstalled(sessionIdentifier)
							&& EquationFunctionContext.getContext().functionHandlerExists(sessionIdentifier))
			{
				currentUnit = setGetDataListFunctionData(sessionIdentifier, service, fieldId, query, pvMetaData);
			}

			String queryResults = "";

			if ((pvMetaData.getId().length() < 4 || EquationPVMetaData.GLOBAL_PVS_IN_RESOURCES.containsKey(pvMetaData.getId())))
			{
				if (!pvMetaData.isPagingRequired())
				{
					queryResults = EquationCommonContext.getContext().getQueryResults(sessionIdentifier, service, decode, security,
									primaryTable, serviceKey, query, start, direction, maxResults);
				}
				else
				{
					IDataList iDataList = EquationCommonContext.getContext().getEquationDataSQLPagingList(currentUnit,
									sessionIdentifier, service, decode, security, query, start, direction, maxResults);
					queryResults = iDataList.getDataListForDisplay();
					if (queryResults == null)
					{
						queryResults = iDataList.getDataList();
					}
				}
			}
			else if (EquationPVMetaData.UNIT_PVS_IN_RESOURCES.containsKey(pvMetaData.getId()))
			{
				queryResults = EquationCommonContext.getContext().getQueryResults(sessionIdentifier, service, decode, security,
								primaryTable, serviceKey, query, start, direction, maxResults);
			}
			else
			{
				try
				{
					IDataList iDataList = EquationCommonContext.getContext().getEquationDataList(currentUnit, sessionIdentifier,
									service, decode, security, query, start, direction, maxResults);
					queryResults = iDataList.getDataListForDisplay();
					if (queryResults == null)
					{
						queryResults = iDataList.getDataList();
					}
				}
				catch (UnitNotAvailableException e)
				{
					StringBuilder errors = new StringBuilder();
					return errors.append("!#ERRORS#!").append("KSM8010   ").append(e.getSystem()).append("/").append(e.getUnit())
									.toString();
				}
			}
			return queryResults;
		}
		catch (Exception e)
		{
			LOG.error(e);
			return Toolbox.getExceptionMessage(e);
		}
	}

	private String setGetDataListFunctionData(String sessionId, String service, String fieldId, String query,
					EquationPVMetaData pvMetaData) throws EQException
	{
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionId);

		String currentUnit = null;

		// Get the function handler and the data for the current session...
		FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionId);
		FunctionHandlerData functionHandlerData = functionHandler.getFhd();

		// Only do this if in a function...
		if (!functionHandlerData.getOptionId().equals("") && !functionHandlerData.isLegacyOption())
		{
			FunctionData functionData = functionHandlerData.getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
			functionData.setPromptFieldData(pvMetaData, query);

			// Need to get the function adaptor (requires the "home" session)
			EquationStandardSession session = EquationCommonContext.getContext().getEquationUser(sessionId).getSession();
			FunctionAdaptor functionAdaptor = functionHandlerData.getFunctionAdaptorHandler().getFunctionAdaptor(session,
							functionHandlerData.getOptionId());

			// See if the field is in the function being processed. It is possible that a prompt has been pressed in the
			// container (account search etc)
			InputField inputField = (InputField) functionHandlerData.getScreenSetHandler().rtvScrnSetCurrent().getFunction()
							.rtvInputField(fieldId, false);
			if (inputField != null)
			{
				PVFieldSet pVFieldSet = inputField.getPvFieldSet(service);

				// Get the Field adaptor
				FieldSetAdaptor fieldSetAdaptor = functionAdaptor.getFieldSetAdaptor(session, pVFieldSet);
				fieldSetAdaptor.setFunctionData(functionHandlerData, functionData);

				fieldSetAdaptor.shouldExecute(pVFieldSet);

				// work out the unit...
				currentUnit = functionData.rtvFieldInputValue("CURUNT");
				if (currentUnit == null || currentUnit.trim().equals("") || currentUnit.equals(session.getUnitId()))
				{
					currentUnit = null;
				}
			}
		}
		return currentUnit;
	}

	/**
	 * Return the Equation Referral <code>EqReferral</code>
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * 
	 * @return the Equation referral in XML format
	 */
	public String getEqReferral(String sessionIdentifier)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		try
		{
			return EqBeanFactory.getEqBeanFactory().serialiseBeanAsXML(
							EquationDesktopContext.getContext().getEqReferral(sessionIdentifier));
		}
		catch (Exception e)
		{
			LOG.error(e);
			return "";
		}
	}

	/**
	 * Get a list of recent searches for a specific session
	 * 
	 * @param sessionIdentifier
	 *            - the session ID
	 * @return a list of recent searches for a specific session ID
	 */
	public String getRecentSearches(String sessionIdentifier)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		return EquationDesktopContext.getContext().getEqNavigator(sessionIdentifier).getRecentSearches();
	}

	/**
	 * Return the recent options made by the user
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * 
	 * @return the recent options in HTML format
	 */
	public String getRecentOptionHTML(String sessionIdentifier)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		return EquationDesktopContext.getContext().getEqNavigator(sessionIdentifier).getRecentOptionHTML();
	}

	/**
	 * Return all the valid options for the user
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * 
	 * @return all the valid options of the user in HTML format
	 */
	public String getFullMenuHTML(String sessionIdentifier)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		try
		{
			return EquationDesktopContext.getContext().getEqNavigator(sessionIdentifier).getEqMenu().getFullMenuHTML();
		}
		catch (Exception e)
		{
			LOG.error(e);
			return Toolbox.getExceptionMessage(e);
		}
	}

	/**
	 * Return all the spooled files of the user
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * 
	 * @return all the spooled files of the user in HTML format
	 */
	public String getUserSpoolFilesHTML(String sessionIdentifier)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		return EquationDesktopContext.getContext().getEqInfo(sessionIdentifier).getUserSpoolFilesHTML();
	}

	/**
	 * Return the unit's spooled files
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * 
	 * @return the unit's spooled files in HTML format
	 */
	public String getUnitSpoolFilesHTML(String sessionIdentifier)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		return EquationDesktopContext.getContext().getEqInfo(sessionIdentifier).getUnitSpoolFilesHTML();
	}

	/**
	 * Return the outq's spooled files
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param library
	 *            - the library where the output queue is located
	 * @param outq
	 *            - the output queue
	 * 
	 * @return the output queues spooled files in HTML format
	 */
	public String getAnySpoolFilesHTML(String sessionIdentifier, String library, String outq)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request. Seems to be
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		return EquationDesktopContext.getContext().getEqInfo(sessionIdentifier).getAnySpoolFilesHTML(library, outq);
	}

	/**
	 * Return the session's job log
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param jobName
	 *            - job name
	 * @param jobUser
	 *            - job user
	 * @param jobNumber
	 *            - job number
	 * 
	 * @return the session's job log in HTML format
	 */
	public String getJobLogHTML(String sessionIdentifier, String jobName, String jobUser, String jobNumber)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		jobName = nullValidation(jobName);
		jobUser = nullValidation(jobUser);
		jobNumber = nullValidation(jobNumber);

		return EquationDesktopContext.getContext().getEqInfo(sessionIdentifier).getJobLogHTML(jobName, jobUser, jobNumber);
	}

	/**
	 * Return the session's task comments
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param processId
	 *            - the process Id
	 * 
	 * @return the session's task comments in HTML format
	 */
	public String getTaskComment(String sessionIdentifier, String processId)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		processId = nullValidation(processId);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionIdentifier);
		try
		{
			// No LRP processing
			if (!EquationCommonContext.getContext().isLRPProcessing())
			{
				return EquationCommonContext.getContext().getLanguageResource(functionHandler.getFhd().getEquationUser(),
								"GBL900074");
			}

			ITaskEngine taskEngine = EquationFunctionContext.getContext().getTaskEngine(sessionIdentifier);
			return EquationDesktopContext.getContext().getEqInfo(sessionIdentifier).getTaskCommentHTML(taskEngine, processId);
		}
		catch (Exception e)
		{
			return EquationCommonContext.getContext().getLanguageResource(functionHandler.getFhd().getEquationUser(), "GBL900077");
		}
	}

	/**
	 * Returns the session's job log relative to the specified position
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param jobName
	 *            - job name
	 * @param jobUser
	 *            - job user
	 * @param jobNumber
	 *            - job number
	 * @param msgPos
	 *            - message position
	 * @param direction
	 *            - from start(0) or forward(1) or backward(2)
	 * 
	 * @return the session's job log relative to the specified position in HTML format
	 */
	public String getJobLogDirHTML(String sessionIdentifier, String jobName, String jobUser, String jobNumber, String msgPos,
					String direction)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		jobName = nullValidation(jobName);
		jobUser = nullValidation(jobUser);
		jobNumber = nullValidation(jobNumber);
		msgPos = nullValidation(msgPos);
		direction = nullValidation(direction);

		return EquationDesktopContext.getContext().getEqInfo(sessionIdentifier).getJobLogHTML(jobName, jobUser, jobNumber,
						Integer.valueOf(msgPos).intValue(), Integer.valueOf(direction).intValue());
	}

	/**
	 * Return a job log entry details
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param jobName
	 *            - job name
	 * @param jobUser
	 *            - job user
	 * @param jobNumber
	 *            - job number
	 * @param msgPos
	 *            - message position
	 * @param msgDate
	 *            - message date
	 * @param msgTime
	 *            - message time
	 * @param msgType
	 *            - message type
	 * 
	 * @return a job log entry details in HTML format
	 */
	public String getJobLogEntryHTML(String sessionIdentifier, String jobName, String jobUser, String jobNumber, String msgPos,
					String msgDate, String msgTime, String msgType)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		jobName = nullValidation(jobName);
		jobUser = nullValidation(jobUser);
		jobNumber = nullValidation(jobNumber);
		msgPos = nullValidation(msgPos);
		msgDate = nullValidation(msgDate);
		msgTime = nullValidation(msgTime);
		msgType = nullValidation(msgType);

		return EquationDesktopContext.getContext().getEqInfo(sessionIdentifier).getJobLogEntryHTML(jobName, jobUser, jobNumber,
						Integer.valueOf(msgPos).intValue(), msgDate, msgTime, msgType);
	}

	/**
	 * Return the content of a message queue
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param queue
	 * 
	 * @return the content of a message queue
	 */
	public String getMsgQueueHTML(String sessionIdentifier, String queue)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		queue = nullValidation(queue);

		return EquationDesktopContext.getContext().getEqInfo(sessionIdentifier).getMsgQueueHTML(queue);
	}

	/**
	 * Return the content of a message queue relative to a position
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param queue
	 *            - message queue
	 * @param msgPos
	 *            - message position
	 * @param direction
	 *            - from start(0) or forward(1) or backward(2)
	 * 
	 * @return the content of a message queue relative to a position in HTML format
	 */
	public String getMsgQueueDirHTML(String sessionIdentifier, String queue, String msgPos, String direction)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		queue = nullValidation(queue);
		msgPos = nullValidation(msgPos);
		direction = nullValidation(direction);

		return EquationDesktopContext.getContext().getEqInfo(sessionIdentifier).getMsgQueueHTML(queue,
						Integer.valueOf(msgPos).intValue(), Integer.valueOf(direction).intValue());
	}

	/**
	 * Return a message queue entry details
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param queue
	 *            - message queue
	 * @param msgPos
	 *            - message position
	 * @param msgDate
	 *            - message date
	 * @param msgTime
	 *            - message time
	 * @param msgType
	 *            - message type
	 * 
	 * @return a message queue entry details
	 */
	public String getMsgQueueEntryHTML(String sessionIdentifier, String queue, String msgPos, String msgDate, String msgTime,
					String msgType)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		queue = nullValidation(queue);
		msgPos = nullValidation(msgPos);
		msgDate = nullValidation(msgDate);
		msgTime = nullValidation(msgTime);
		msgType = nullValidation(msgType);

		return EquationDesktopContext.getContext().getEqInfo(sessionIdentifier).getMsgQueueEntryHTML(queue,
						Integer.valueOf(msgPos).intValue(), msgDate, msgTime, msgType);
	}

	/**
	 * Return a message from the message file KSMMSGF
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param msgd
	 *            - message description
	 * @param secondLevel
	 *            - additional text to be displayed as second level text
	 * 
	 * @return a message from the message file in HTML format
	 */
	public String getMsgFileEntryHTML(String sessionIdentifier, String msgd, String secondLevel)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		msgd = nullValidation(msgd);
		secondLevel = nullValidation(secondLevel);

		return EquationDesktopContext.getContext().getEqInfo(sessionIdentifier).getMsgFileEntryHTML(msgd, secondLevel,
						sessionIdentifier);
	}

	/**
	 * Destroy the pools
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 */
	public void destroyPools(String sessionIdentifier)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		try
		{
			EquationFunctionContext.getContext().removeEquationUnit(sessionIdentifier);
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
	}

	/**
	 * Refresh desktop cache
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 */
	public void refreshDesktopCache(String sessionIdentifier)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		try
		{
			EquationDesktopContext.getContext().refreshCache(sessionIdentifier);
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
	}

	/**
	 * Return the work load of the user
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * 
	 * @return the work load of the user in HTML format
	 */
	public String getWorkLoadHTML(String sessionIdentifier)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		return EquationDesktopContext.getContext().getEqInfo(sessionIdentifier).getWorkLoadHTML();
	}

	/**
	 * Return the journal print entries
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param workStation
	 *            - work station
	 * @param jrnDay
	 *            - journal day
	 * @param jrnTime
	 *            - journal time
	 * @param jrnSequence
	 *            - journal sequence
	 * @param optionId
	 *            - option id
	 * 
	 * @return the work load of the user in HTML format
	 */
	public String getJournalPrint(String sessionIdentifier, String workStation, int jrnDay, int jrnTime, int jrnSequence,
					String optionId, String printBlank, String library)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		workStation = nullValidation(workStation);
		optionId = nullValidation(optionId);
		printBlank = nullValidation(printBlank);
		library = nullValidation(library);

		FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionIdentifier, "");
		String str = functionHandler.printAsString(optionId, workStation, jrnDay, jrnTime, jrnSequence, printBlank.equals("Y"),
						library);
		return str;
	}

	/**
	 * Apply a transaction
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param workStation
	 *            - work station
	 * @param jrnDay
	 *            - journal day
	 * @param jrnTime
	 *            - journal time
	 * @param jrnSequence
	 *            - journal sequence
	 * @param image
	 *            - journal image
	 * @param fct
	 *            - function mode
	 * @param optionId
	 *            - option id
	 * @param library
	 *            - library where the journal is located
	 * @param journal
	 *            ? - journal it?
	 * 
	 * @return the work load of the user in HTML format
	 */
	public String applyTransaction(String sessionIdentifier, String workStation, int jrnDay, int jrnTime, int jrnSequence,
					String image, String fct, String optionId, String library, String journal)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		workStation = nullValidation(workStation);
		image = nullValidation(image);
		fct = nullValidation(fct);
		optionId = nullValidation(optionId);
		library = nullValidation(library);
		journal = nullValidation(journal);

		FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionIdentifier, "");
		String str = functionHandler.applyTransactionAsString(optionId, workStation, jrnDay, jrnTime, jrnSequence, image, fct,
						library, journal.equals("Y"));
		return str;
	}

	/**
	 * Apply a transaction.
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param data
	 *            - data
	 * @param image
	 *            - journal image
	 * @param fct
	 *            - function mode
	 * @param optionId
	 *            - option id
	 * @param journal
	 *            ? - journal it?
	 * 
	 * @return the work load of the user in HTML format
	 */
	public String applyTransactionData(String sessionIdentifier, String data, String image, String fct, String optionId,
					String journal)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		data = nullValidation(data);
		image = nullValidation(image);
		fct = nullValidation(fct);
		optionId = nullValidation(optionId);
		journal = nullValidation(journal);

		try
		{
			return EquationDesktopContext.getContext().applyTransactionData(sessionIdentifier, data, image, fct, optionId, journal);
		}
		catch (Exception e)
		{
			LOG.error(e);
			return "20KSM7340" + Toolbox.pad(e.getMessage(), 30);
		}
	}

	/**
	 * Request for remote supervisor override
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param name
	 *            - name of the function handler
	 * @param superId
	 *            - the supervisor id
	 * @param offline
	 *            - offline request?<br>
	 *            - N - online remote supervisor override<br>
	 *            - Y - offline remote supervisor override<br>
	 *            - L - LRP referrals<br>
	 * 
	 * @return the status whether the supervisor is able to override the warning. <br>
	 *         the format of the message will be <br>
	 *         code - 1 character (1) valid (2) not valid <br>
	 *         message
	 */
	public String remoteSupervisor(String sessionIdentifier, String name, String superId, String offline)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		name = nullValidation(name);
		superId = nullValidation(superId);
		offline = nullValidation(offline);

		FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionIdentifier, name);

		if (offline.equals(SupervisorToolbox.TYP_LRP))
		{
			return SupervisorToolbox.lrpSupervisor(superId, offline, functionHandler.getFhd());
		}

		return SupervisorToolbox.remoteSupervisor(superId, offline.equals("Y"), functionHandler.getFhd());
	}

	/**
	 * Remove the request for supervisor
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param name
	 *            - name of the function handler
	 * @param status
	 *            - status of the request
	 */
	public void removeSupervisor(String sessionIdentifier, String name, String status)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		name = nullValidation(name);
		status = nullValidation(status);

		FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionIdentifier, name);
		SupervisorToolbox.removeSupervisor(status, functionHandler.getFhd());
	}

	/**
	 * Check status of a session
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param name
	 *            - name of the function handler
	 */
	public String checkSessionStatus(String sessionIdentifier, String name)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		name = nullValidation(name);

		try
		{
			FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionIdentifier, name);
			return EqBeanFactory.getEqBeanFactory().serialiseBeanAsXML(
							SupervisorToolbox.checkSuperSessionStatus(functionHandler.getFhd()));
		}
		catch (Exception e)
		{
			LOG.error(e);
			return "";
		}
	}

	/**
	 * Perform authorisation
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param name
	 *            - name of the function handler
	 */
	public void authoriseBySupervisorOverride(String sessionIdentifier, String name)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		name = nullValidation(name);

		FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionIdentifier, name);
		SupervisorToolbox.authoriseBySupervisorOverride(functionHandler.getFhd());
	}

	/**
	 * Perform authorisation
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param name
	 *            - name of the function handler
	 * @param authLevel
	 *            - authorisation level
	 */
	public void authoriseBySupervisorRm(String sessionIdentifier, String name, String password, int ckey)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		name = nullValidation(name);
		password = nullValidation(password);

		FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionIdentifier, name);
		SupervisorToolbox.authoriseBySupervisorRemote(ckey, password, functionHandler.getFhd());
	}

	/**
	 * Perform authorisation
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param name
	 *            - name of the function handler
	 * @param superId
	 *            - the supervisor id (local override) or the function key (remote override)
	 * @param tranType
	 *            - the transaction type
	 */
	public String authoriseBySupervisorId(String sessionIdentifier, String name, String superId, String password, String tranType)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		name = nullValidation(name);
		superId = nullValidation(superId);
		password = nullValidation(password);
		tranType = nullValidation(tranType);

		FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionIdentifier, name);

		int ckey = Toolbox.parseInt(superId, -1);
		if (ckey == -1)
		{
			return SupervisorToolbox.authoriseBySupervisorId(superId, password, tranType, functionHandler.getFhd());
		}
		else
		{
			return SupervisorToolbox.authoriseBySupervisorRemote(ckey, password, functionHandler.getFhd());
		}
	}

	/**
	 * Remove a session
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param name
	 *            - name of the function handler
	 * @param optionId
	 *            - the option Id
	 * @param sessionId
	 *            - the session Id
	 * @param transactionId
	 *            - the transaction Id
	 * @param userId
	 *            - the user Id
	 */
	public void removeSession(String sessionIdentifier, String name, String optionId, String sessionId, String transactionId,
					String userId)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		name = nullValidation(name);
		optionId = nullValidation(optionId);
		sessionId = nullValidation(sessionId);
		transactionId = nullValidation(transactionId);
		userId = nullValidation(userId);

		FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionIdentifier, name);
		SupervisorToolbox.removeSession(new FunctionSession(optionId, sessionId, userId, transactionId), functionHandler.getFhd()
						.getEquationUser().getEquationUnit());
	}

	/**
	 * Clears validation user exit information from cache.
	 * 
	 * @param systemId
	 *            The name of the system
	 * @param unitId
	 *            The name of the unit
	 * @param program
	 *            The name of the RPG validation program
	 * @param className
	 *            The full class name (including package)
	 */
	public void invalidateValidationUserExit(String systemId, String unitId, String program, String className)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		systemId = nullValidation(systemId);
		unitId = nullValidation(unitId);
		program = nullValidation(program);
		className = nullValidation(className);

		EquationCommonContext.getContext().invalidateValidationUserExit(systemId, unitId, program, className);
	}

	/**
	 * Log-off from Equation Desktop
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param name
	 *            - name of the function handler
	 */
	public void logoffDesktop(String sessionIdentifier, String name)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		name = nullValidation(name);

		EquationDesktopContext.getContext().logOffDesktop(sessionIdentifier, name, true);
	}

	/**
	 * Return the detail of a particular field via using the PV module
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param service
	 *            - P/V name
	 * @param decode
	 *            - decode character
	 * @param dsccn
	 *            - dsccn data
	 * 
	 * @return the Equation data details in XML format
	 */
	public String getPromptHelpDetails(String sessionIdentifier, String service, String decode, String security, String dsccn)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		decode = nullValidation(decode);
		security = nullValidation(security);
		dsccn = nullValidation(dsccn);

		return EquationDesktopContext.getContext().getPromptHelpDetails(sessionIdentifier, service, decode, security, dsccn);
	}

	/**
	 * Add a child Function Handler to the session
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param name
	 *            - unique name of the child Function Handler
	 * @param option
	 *            - the option id
	 * @param context
	 *            - the context string
	 * @param childType
	 *            - determines how the child was invoked
	 * @param rowIndex
	 *            - the repeating data group + row index (when drilling down repeating group)
	 */
	public String addChildFunctionHandler(String sessionIdentifier, String name, String option, String context, String childType,
					String rowIndex)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		name = nullValidation(name);
		option = nullValidation(option);
		context = nullValidation(context);
		childType = nullValidation(childType);

		return EquationFunctionContext.getContext().addChildFunctionHandler(sessionIdentifier, name, option, context, childType,
						rowIndex);
	}

	/**
	 * Determine whether the session has not timed-out
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 * 
	 * @return 00 if session has not timed-out
	 */
	public String isSessionAlive(String sessionIdentifier)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		if (EquationFunctionContext.getContext().getFunctionHandlerTable(sessionIdentifier) == null)
		{
			return "20";
		}
		else
		{
			return "00";
		}

	}

	/**
	 * Update the function data
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 * @param name
	 *            - name of the function handler
	 * @param fieldValues
	 *            - the list of field values
	 * 
	 * @return ""
	 */
	public String updateFunctionData(String sessionIdentifier, String name, String fieldValues)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		name = nullValidation(name);
		fieldValues = nullValidation(fieldValues);

		FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionIdentifier, name);
		functionHandler.updateFields(fieldValues, EqDataType.GLOBAL_EQUALDELIMETER, EqDataType.GLOBAL_DELIMETER);
		return "";
	}

	/**
	 * Return the iSeries Job Id
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * 
	 * @return the JobId as 26 characters - Job Name + Job User + Job Number
	 */
	public String getJobId(String sessionIdentifier)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		try
		{
			return EquationCommonContext.getContext().getEquationUser(sessionIdentifier).getSession().getConnectionWrapper()
							.getJobId();
		}
		catch (Exception e)
		{
			LOG.error(e);
			return "";
		}
	}

	/**
	 * Return the Equation Data List <code>EqDataList</code>
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param service
	 *            - P/V program name
	 * @param decode
	 *            - decode character
	 * @param query
	 *            - query
	 * @param maxResults
	 *            - maximum record to retrieved
	 * 
	 * @return the data list
	 */
	public String getPredictiveList(String sessionIdentifier, String id, String service, String decode, String security,
					String query, int maxResults)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		id = nullValidation(id);
		service = nullValidation(service);
		decode = nullValidation(decode);
		security = nullValidation(security);
		query = nullValidation(query);

		return id + EqDataType.GLOBAL_DELIMETER + query + EqDataType.GLOBAL_DELIMETER
						+ getEqDataList(sessionIdentifier, null, service, decode, security, "", "", query, "", 1, maxResults);
	}

	/**
	 * Remove a spooled file
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param name
	 *            - name of the function handler
	 * @param spooledName
	 *            - the spooled file name
	 * @param jobName
	 *            - the job name
	 * @param jobUser
	 *            - the job user
	 * @param jobNumber
	 *            - the job number
	 * @param splfNum
	 *            - the spooled file number
	 */
	public void removeSpooledFile(String sessionIdentifier, String name, String spooledName, String jobName, String jobUser,
					String jobNumber, int splfNum)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		name = nullValidation(name);
		spooledName = nullValidation(spooledName);
		jobName = nullValidation(jobName);
		jobUser = nullValidation(jobUser);
		jobNumber = nullValidation(jobNumber);

		try
		{
			EquationUnit unit = EquationCommonContext.getContext().getEquationUser(sessionIdentifier).getEquationUnit();
			Toolbox.removeSpooledFile(unit.getEquationSystem(), spooledName, jobName, jobUser, jobNumber, splfNum);
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
	}

	/**
	 * Perform page up or down on the repeating group
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param name
	 *            - name of the function handler
	 * @param repeatingGroupId
	 *            - the repeating group id
	 * @param breakby
	 *            - breakby filtering
	 * 
	 * @return the HTML element containing the list
	 */
	public String breakByRepeatingDataAction(String sessionIdentifier, String name, String repeatingGroupId, String breakby)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		name = nullValidation(name);
		repeatingGroupId = nullValidation(repeatingGroupId);
		breakby = nullValidation(breakby);

		try
		{
			FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionIdentifier, name);
			RepeatingGroupPagingDetail paging = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().doBreakBy(
							repeatingGroupId, breakby);
			return paging.toString();
		}
		catch (Exception e)
		{
			LOG.error(e);
			return "";
		}
	}

	/**
	 * Perform page up or down on the repeating group
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param name
	 *            - name of the function handler
	 * @param repeatingGroupId
	 *            - the repeating group id
	 * @param contextFields
	 *            - list of fields delimited by CONTEXT_DELIMTER
	 * 
	 * @return the HTML element containing the list
	 */
	public String repeatingGroupSortAction(String sessionIdentifier, String name, String repeatingGroupId, String contextFields)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		name = nullValidation(name);
		repeatingGroupId = nullValidation(repeatingGroupId);
		contextFields = nullValidation(contextFields);

		try
		{
			FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionIdentifier, name);
			RepeatingGroupPagingDetail paging = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().doSorting(
							repeatingGroupId, contextFields);
			return paging.toString();
		}
		catch (Exception e)
		{
			LOG.error(e);
			return "";
		}
	}

	/**
	 * Perform page up or down on the repeating group
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param name
	 *            - name of the function handler
	 * @param repeatingGroupId
	 *            - the repeating group id
	 * @param pageUp
	 *            - page up (Y)
	 * 
	 * @return the HTML element containing the list
	 */
	public String repeatingGroupPageAction(String sessionIdentifier, String name, String repeatingGroupId, String pageUp)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		name = nullValidation(name);
		repeatingGroupId = nullValidation(repeatingGroupId);
		pageUp = nullValidation(pageUp);

		try
		{
			FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionIdentifier, name);
			RepeatingGroupPagingDetail paging = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().doPaging(
							repeatingGroupId, pageUp.equals("Y"));
			return paging.toString();
		}
		catch (Exception e)
		{
			LOG.error(e);
			return "";
		}
	}

	/**
	 * Returns a given session ID back to the pool
	 * 
	 * @param sessionIdentifier
	 */
	public void returnSessionToPool(String sessionIdentifier)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		try
		{
			EquationUnit unit = EquationCommonContext.getContext().getEquationUnit(sessionIdentifier);
			EquationStandardSession session = EquationCommonContext.getContext().getEquationUser(sessionIdentifier).getSession();
			unit.getEquationSessionPool().returnEquationStandardSession(session);
		}
		catch (EQException e)
		{
			LOG.error(e);
		}
	}

	/**
	 * Returns whether a session is valid or not
	 * 
	 * @param sessionIdentifier
	 *            - the Session ID to validate
	 * @return true if the session is valid, otherwise false
	 */
	public boolean isSessionValid(String sessionIdentifier)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		return EquationCommonContext.getContext().sessionValid(sessionIdentifier);
	}

	/**
	 * Validate a parameter whether it is NULL or not. If it is NULL, return blank space
	 * 
	 * @param parameter
	 *            - the parameter to be checked
	 * 
	 * @return the parameter (or blank if NULL)
	 */
	private String nullValidation(String parameter)
	{
		return parameter == null ? "" : parameter;
	}

	/**
	 * Retrieve the list of output queues from the specified library
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param library
	 *            - the library where the output queue is located
	 * 
	 * @return the list of output queues from the specified library
	 */
	public String getListOutputQueue(String sessionIdentifier, String library)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request. Seems to be
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		return EquationDesktopContext.getContext().getEqInfo(sessionIdentifier).getOutputQueues(library);
	}

	/**
	 * Determine if the user id is a valid potential owner for the task
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 * @param taskId
	 *            - the task id
	 * @param userId
	 *            - the potential user
	 * 
	 * @return the status whether user is able to own the task the format of the message will be <br>
	 *         message severity - 2 char message
	 */
	public String isValidReferToUser(String sessionIdentifier, String taskId, String userId)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request. Seems to be
		sessionIdentifier = nullValidation(sessionIdentifier);
		taskId = nullValidation(taskId);
		userId = nullValidation(userId);

		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionIdentifier);
		FunctionHandlerData fhd = functionHandler.getFhd();

		try
		{
			// No LRP processing
			if (!EquationCommonContext.getContext().isLRPProcessing())
			{
				return EquationCommonContext.getContext().getLanguageResource(functionHandler.getFhd().getEquationUser(),
								"GBL900074");
			}

			ITaskEngine taskEngine = EquationFunctionContext.getContext().getTaskEngine(sessionIdentifier);
			String rereferUser = userId;
			if (EquationFunctionContext.getContext().isEquationAuthentication())
			{
				rereferUser = LRPToolbox.correctUserCase(fhd.getEquationUser().getEquationUnit().rtvFullUserId(userId));
			}
			// Note for CAS, might be typed in wrong case...which will fail
			if (taskEngine.isUserCanClaimTask(taskId, rereferUser))
			{
				return "00";
			}
			else
			{
				fhd.getFunctionMsgManager().getOtherMessages().clearMessages();
				fhd.getFunctionMsgManager().insertOtherMessage(fhd.getEquationUser().getSession(),
								fhd.getScreenSetHandler().getCurScreenSet(), 0, "", 1, null, "KSM7605" + rereferUser.toUpperCase(),
								"", "");
			}
		}
		catch (Exception e)
		{
			return "20"
							+ EquationCommonContext.getContext().getLanguageResource(functionHandler.getFhd().getEquationUser(),
											"GBL900077");
		}

		return "20" + fhd.getFunctionMsgManager().getOtherMessages().getMessages().get(0).getEqMessage().getUnFormattedText();
	}

	/**
	 * Validates the supplied credentials
	 * 
	 * This method will check whether the supplied credentials are valid by passing them to CAS. Note that this method only
	 * validates credentials; it is called from Equation IBM i programs (via SRVWEB) to validate the entered credentials as part of
	 * supervisor overrides. The other operations associated with overrides (WE updates, WF checking etc) will still be done in the
	 * Equation IBM i programs.
	 * 
	 * @param sessionIdentifier
	 *            A valid session id
	 * @param user
	 *            The supervisor id
	 * @param password
	 *            The supervisor password
	 * 
	 * @return A blank string to indicate the credentials were valid, otherwise a KSM message (e.g. "KSM9989").
	 */
	public String validateCredentials(String sessionIdentifier, String user, String password)
	{
		// IBM (WAS) Problem - dealing with empty string in SOAP request. Seems to be
		sessionIdentifier = nullValidation(sessionIdentifier);
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);
		user = nullValidation(user);
		password = nullValidation(password);

		String result = "";

		if (!new BFToolbox().validateCredentials(user, password))
		{
			result = "KSM9989"; // Invalid password
		}
		return result;
	}
}