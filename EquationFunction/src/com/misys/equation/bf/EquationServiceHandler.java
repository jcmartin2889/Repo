package com.misys.equation.bf;

import java.util.Map;

import bf.com.misys.eqf.types.header.EqMessage;
import bf.com.misys.eqf.types.header.ExtensionDataRq;
import bf.com.misys.eqf.types.header.JournalKey;
import bf.com.misys.eqf.types.header.RqHeader;
import bf.com.misys.eqf.types.header.RsHeader;
import bf.com.misys.eqf.types.header.ServiceResponse;
import bf.com.misys.eqf.types.header.ServiceRqHeader;
import bf.com.misys.eqf.types.header.ServiceRsHeader;
import bf.com.misys.eqf.types.header.UiControlRq;
import bf.com.misys.eqf.types.header.UiControlRs;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationTransactionManager;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQXAException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.function.adaptor.FunctionAdaptor;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.context.BFEQCredentials;
import com.misys.equation.function.context.EquationFunctionContext;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.runtime.ConversionRules;
import com.misys.equation.function.runtime.FunctionAPI;
import com.misys.equation.function.runtime.FunctionBankFusion;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionHandlerData;
import com.misys.equation.function.runtime.FunctionMessages;
import com.misys.equation.function.runtime.ScreenSet;
import com.misys.equation.function.tools.ComplexTypeToolbox;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;
import com.misys.equation.function.tools.IdempotencyToolbox;
import com.misys.equation.function.tools.XMLToolbox;
import com.misys.equation.function.tools.XSDToolbox;

/**
 * BankFusion Activity Step implementation to invoke an EQ4 service.
 * <p>
 * 
 */
public class EquationServiceHandler
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationServiceHandler.java 17765 2014-01-13 13:07:25Z lima12 $";

	// Class level variables
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EquationServiceHandler.class);

	// this tracks whether the unit environment has been initialised before
	private static boolean contextInitialised = false;

	// instance variables
	private FunctionBankFusion functionBankFusion = null;
	private EquationUnit unit;
	private String dataSourceName;
	private Object outputResponseData;
	private ServiceRsHeader serviceRsHeader;
	private Object outputServiceData;
	private Object processingServiceData;
	// the user and passwords from the J2C Aliases
	private String adminUserId;
	private String adminPassword;
	String lastUserIdParameter;
	String equationIseriesProfile;
	// the function elements
	private Function function;
	private Function primaryFunction;
	private FunctionData functionData;
	private ScreenSet mainScreenSet;
	private ExtensionDataRq linkageExtensionData = new ExtensionDataRq();
	// this indicates whether the extension data has been set by the caller(true), or is on the header(false).
	private boolean extensionDataSet = false;

	private String primaryOptionId = "";

	/** The message id on input */
	private String correlationId = "";

	/** Debug mode */
	private boolean debugMode = false;

	/**
	 * Create EquationServiceHandler for processing Equation Services using a data source. All transactions will be processed using
	 * the data source connection user. The naming convention of the data source is jdbc/EQ-iSeries-unit-xxx where may be any
	 * length/value.
	 * 
	 * @param dataSourceName
	 *            - the data source
	 * @throws EQException
	 */
	public EquationServiceHandler(String dataSourceName) throws EQException
	{
		lastUserIdParameter = "";
		equationIseriesProfile = "";

		String errorText;
		if (dataSourceName == null)
		{
			errorText = "EquationServiceHandler(String) - cannot construct without a Data Source";
			LOG.error(errorText);
			throw new EQException(errorText + " Data source must be supplied");
		}
		this.dataSourceName = dataSourceName;
		// example of jndi name - jdbc/EQ-SLOUGH1-CAS-XA
		String[] parts = dataSourceName.split("-");
		String systemId = parts[1];
		String unitId = parts[2];
		// we get the admin alias from equationConfiguration.properties and use it to set up the environment
		setAdminCredentials();
		if (!contextInitialised)
		{
			EquationCommonContext.getContext().initialiseUnitEnvironment(systemId, unitId, adminUserId, adminPassword,
							EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN);
			contextInitialised = true;
		}
		unit = EquationCommonContext.getContext().getEquationSystem(systemId).getUnit(unitId);
		functionBankFusion = new FunctionBankFusion();
		// Check if the pool is initialised
		if (!unit.initialiseConnectionPool(dataSourceName))
		{
			errorText = "EquationServiceHandler(String) - failed to initialise connection pool.";
			LOG.error(errorText);
			throw new EQException(errorText);
		}
	}

	/**
	 * Create EquationServiceHandler for processing Equation Services. EquationConfiguration.properties file must be configured to
	 * hold the administrator alias - eq.admin.aliases. All transactions will be processed using the administrator alias unless
	 * userId is specified in the service request header supplied when using the process method.
	 * 
	 * @param systemId
	 *            - the iSeries system id
	 * @param unitId
	 *            - the unit id
	 * @throws EQException
	 */
	public EquationServiceHandler(String systemId, String unitId) throws EQException
	{
		this.dataSourceName = null;
		lastUserIdParameter = "";
		equationIseriesProfile = "";

		// we get the admin alias from equationConfiguration.properties and use it to set up the environment
		setAdminCredentials();
		if (!contextInitialised)
		{
			EquationCommonContext.getContext().initialiseUnitEnvironment(systemId, unitId, adminUserId, adminPassword,
							EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN);
			contextInitialised = true;
		}
		unit = EquationCommonContext.getContext().getEquationSystem(systemId).getUnit(unitId);
		functionBankFusion = new FunctionBankFusion();
	}

	/**
	 * Create EquationServiceHandler for processing Equation Services.
	 * 
	 * @param flag
	 *            - control<br>
	 *            1 - parameter is session id
	 * @param parameter
	 *            - the parameter depending on the flag
	 * 
	 * @throws EQException
	 */
	public EquationServiceHandler(int flag, Object parameter) throws EQException
	{
		this.dataSourceName = null;
		lastUserIdParameter = "";
		equationIseriesProfile = "";

		if (flag == 1)
		{
			unit = EquationCommonContext.getContext().getEquationUser((String) parameter).getEquationUnit();
		}
		functionBankFusion = new FunctionBankFusion();
	}

	/**
	 * Create EquationServiceHandler for processing Equation Services. EquationConfiguration.properties file must be configured to
	 * hold the server startup properties - eq.units, eq.systems, eq.admin.users, eq.admin.passwords, All transactions will be
	 * processed using the user specified unless userId is specified in the service request header supplied when using the process
	 * method.
	 * 
	 * @throws EQException
	 */
	public EquationServiceHandler() throws EQException
	{
		this.dataSourceName = null;
		lastUserIdParameter = "";
		equationIseriesProfile = "";

		// we get the admin alias from equationConfiguration.properties and use it to set up the environment
		String systemId = EquationCommonContext.getConfigProperty("eq.systems");
		String unitId = EquationCommonContext.getConfigProperty("eq.units");
		adminUserId = EquationCommonContext.getConfigProperty("eq.admin.users");
		adminPassword = EquationCommonContext.getConfigProperty("eq.admin.passwords");
		if (!contextInitialised)
		{
			EquationCommonContext.getContext().initialiseUnitEnvironment(systemId, unitId, adminUserId, adminPassword,
							EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN);
			contextInitialised = true;
		}
		unit = EquationCommonContext.getContext().getEquationSystem(systemId).getUnit(unitId);
		functionBankFusion = new FunctionBankFusion();

	}

	/**
	 * Create EquationServiceHandler for processing Equation Services in a non-WAS environment (e.g. JUnit tests). The connection
	 * details are passed as parameters, All transactions will be processed using the user specified unless userId is specified in
	 * the service request header supplied when using the process method.
	 * 
	 * @param systemId
	 *            - the iSeries system id
	 * @param unitId
	 *            - the unit id
	 * @param userId
	 *            - the connection user profile
	 * @param password
	 *            - the connection password
	 * @throws EQException
	 */
	public EquationServiceHandler(String systemId, String unitId, String userId, String password) throws EQException
	{
		this.dataSourceName = null;
		lastUserIdParameter = "";
		equationIseriesProfile = "";

		adminUserId = userId;
		adminPassword = password;
		if (!contextInitialised)
		{
			EquationCommonContext.getContext().initialiseUnitEnvironment(systemId, unitId, adminUserId, adminPassword,
							EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN);
			contextInitialised = true;
		}
		unit = EquationCommonContext.getContext().getEquationSystem(systemId).getUnit(unitId);
		functionBankFusion = new FunctionBankFusion();
	}

	/**
	 * Main processing. This performs the actual service invocation
	 * 
	 * @param env
	 *            a BankFusionEnvironment
	 * @throws EQException
	 */

	public void process(ServiceRqHeader serviceRqHeader, Object inputServiceData) throws EQException
	{
		String sessionId = null;
		String errorText;
		try
		{
			// initialise header if NULL
			functionBankFusion = new FunctionBankFusion();
			functionBankFusion.initialiseServiceRqHeader(serviceRqHeader);
			functionBankFusion.printServiceRqHeader(serviceRqHeader);

			// retrieve information
			if (!extensionDataSet)
			{
				linkageExtensionData = serviceRqHeader.getUserExtensionData();
			}
			// ExtensionDataRq userExtensionDataRq = serviceRqHeader.getUserExtensionData();
			ExtensionDataRq misysExtensionDataRq = serviceRqHeader.getMisysExtensionData();
			RqHeader rqHeader = serviceRqHeader.getRqHeader();
			String systemId = rqHeader.getSystemId();
			if (systemId == null || systemId.length() == 0)
			{
				systemId = unit.getSystem().getSystemId();
			}
			else if (!systemId.equals(unit.getSystem().getSystemId()))
			{
				errorText = "process() - System id in service request header does not match constructor.";
				LOG.error(errorText);
				throw new EQException(errorText);
			}

			String unitId = rqHeader.getUnitId();
			if (unitId == null || unitId.length() == 0)
			{
				unitId = unit.getUnitId();
			}
			else if (!unitId.equals(unit.getUnitId()))
			{
				errorText = "process() - Unit id in service request header does not match constructor.";
				LOG.error(errorText);
				throw new EQException(errorText);
			}

			// retrieve the correlation id
			correlationId = rqHeader.getMessageId();

			String optionId = serviceRqHeader.getOptionId();

			String mode = serviceRqHeader.getServiceMode();

			// overwrite option id based on the user extension
			primaryOptionId = optionId;
			optionId = Toolbox.getOptionIdFromExtensionData(optionId, linkageExtensionData, misysExtensionDataRq);

			UiControlRq uiControl = serviceRqHeader.getUiControlRq();
			String uiMode = uiControl == null ? "" : uiControl.getUiMode();

			// default the output parameter to the input parameter
			outputResponseData = null; // this is the combined header and data
			outputServiceData = inputServiceData;
			processingServiceData = inputServiceData;

			mainScreenSet = null;
			function = null;
			primaryFunction = null;
			functionData = null;

			// Establish connection with admin user for whom we have the password and switch to real user if they are
			// different
			String userId = serviceRqHeader.getRqHeader().getUserId();
			// Get the iSeries user if different from the connection user.
			if (userId == null)
			{
				userId = "";
			}
			if (!userId.equals(lastUserIdParameter))
			{
				if (userId.length() > 0)
				{
					if (rqHeader.getUserIdType().equals("1"))
					{
						equationIseriesProfile = EquationFunctionContext.getContext().getiSeriesUserForBFUser(unit, userId);
					}
					else
					{
						equationIseriesProfile = userId.toUpperCase();
					}
				}
				else
				{
					equationIseriesProfile = "";
				}
				lastUserIdParameter = userId;
			}

			String sessionType = rqHeader.getSessionType();
			// If data source not in constructor try the service header
			if (dataSourceName == null || dataSourceName.length() == 0)
			{
				dataSourceName = rqHeader.getDataSourceName();
			}
			else
			{
				// constructor has dataSourceName so session type should be 1.
				sessionType = "1";
			}

			// Get the data source. Use default data source from equationConfiguration.properties if one is not supplied in request.
			if (sessionType != null && sessionType.equals("1") && (dataSourceName == null || dataSourceName.length() == 0))
			{
				dataSourceName = EquationCommonContext.getContext().getDefaultDataSourceName();

				if (dataSourceName == null || dataSourceName.length() == 0)
				{
					// fatal error:
					throw new EQException("Data source name not found.");
				}
			}
			if (sessionType.equals(""))
			{
				sessionId = rqHeader.getSessionId();

				BFEQCredentials credentials = new BFEQCredentials(adminUserId, adminPassword,
								EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN, sessionId);
				int internalSessionType = EquationCommonContext.SESSION_API_MODE;
				sessionId = EquationFunctionContext.getContext()
								.getEqSessionNoBankFusion(systemId, unitId, credentials,
												serviceRqHeader.getRqHeader().getOrig().getOrigTCPIP(), internalSessionType,
												equationIseriesProfile);

			}
			else
			{
				// Missing parameters
				boolean xaMode = false;
				int internalSessionType = EquationCommonContext.SESSION_API_MODE;
				sessionId = EquationFunctionContext.getContext().getEqSession(dataSourceName, internalSessionType, sessionId,
								xaMode, equationIseriesProfile);
			}

			// create the function handler
			String fhname = "";
			// The function handler name in UI mode is currently specified in the Reference field
			// for UI mode in order to differentiate the base transaction and a pop-up transaction.
			// To allow BankFusion callers to still specify the Reference field, this
			// determination of the function handler name is now only performed if the uiMode is
			// not blank; this should never be supplied if in non-UI mode.
			if (uiMode.length() > 0)
			{
				fhname = serviceRqHeader.getReference().trim(); // TODO: change the getReference()
				LOG.debug("Getting a function handler for option " + optionId + "; fh[ " + fhname + "]");
			}
			FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionId, fhname);
			functionHandler.getFhd().setServiceRqHeader(serviceRqHeader);
			functionHandler.getFhd().getFunctionInfo().setDebugMode(debugMode);

			// Desktop - If Dummy Validate Microflow was called then perform validate process
			if (uiMode.equals("V"))
			{
				processValidate(functionHandler, uiControl);
			}
			// Desktop - If Dummy Update Microflow was called then perform update process
			else if (uiMode.equals("U"))
			{
				processUpdate(functionHandler, uiControl);
			}
			// Online Input / External Input - Retrieve
			else if (uiMode.equals("R"))
			{
				processOnlineRetrieve(functionHandler, uiControl);
			}
			// Online Input / External Input - Update
			else if (uiMode.equals("A"))
			{
				processOnlineUpdate(functionHandler, uiControl, mode);
			}

			// Non-desktop mode
			else
			{
				EquationStandardSession session = EquationCommonContext.getContext().getEquationUser(sessionId)
								.getSessionForNonUpdate();
				if (session.getUnit().isIPVPFInstalled())
				{
					ServiceResponse serviceResponse = IdempotencyToolbox.retrieveProcessing(session, serviceRqHeader);
					if (serviceResponse != null)
					{
						serviceRsHeader = serviceResponse.getHeader();
						outputServiceData = serviceResponse.getData();
						boolean valid = functionHandler.doNewTransaction(optionId, "");
						if (valid)
						{
							mainScreenSet = functionHandler.getFhd().getScreenSetHandler().rtvScreenSetMain();
							primaryFunction = mainScreenSet.getPrimaryFunction();
							if (primaryFunction.chkXSDGeneric())
							{
								setupOutputResponseData();
								return;
							}
						}
					}
				}

				boolean validTransaction = processNonDesktop(functionHandler, uiControl, mode, optionId, serviceRqHeader,
								misysExtensionDataRq);
				if (!validTransaction)
				{
					setupOutputResponseData();
					return;
				}
			}

			// set output data
			setOutputServiceData(serviceRqHeader, functionHandler.getFhd());

			// logging
			if (LOG.isInfoEnabled())
			{
				LOG.info("Activity step completed, instance [" + System.identityHashCode(this) + "]");
			}
		}
		catch (Exception e)
		{
			LOG.error("process()", e);

			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
		finally
		{
			if (dataSourceName != null && dataSourceName.length() > 0)
			{

				EquationUser eu = EquationCommonContext.getContext().getEquationUser(sessionId);
				EquationStandardSession session = eu.getSession();
				if (session != null)
				{
					// remove handles to session
					EquationFunctionContext.getContext().logOffSessionUserPool(session.getSessionIdentifier());
					// return the session to the pool
					FunctionRuntimeToolbox.returnEquationSessionToPool(dataSourceName, session);
				}
			}
		}
	}

	/**
	 * Set the output data
	 * 
	 * @param serviceRqHeader
	 *            - the Service rq header
	 * @param fhd
	 *            - the Function Handler data
	 * 
	 * @return the output data
	 * 
	 * @throws EQException
	 */
	public Object setOutputServiceData(ServiceRqHeader serviceRqHeader, FunctionHandlerData fhd) throws EQException
	{
		/**
		 * NOTE: this method is called outside of the EquationServiceHandler. Do not access any class variables without making sure
		 * that it is initialised properly. See setData();
		 */

		// Set the output parameter data
		ConversionRules conversionRuleOutput = FunctionRuntimeToolbox.getConversionRules(serviceRqHeader, fhd);
		conversionRuleOutput.cvtToUserFormat();
		conversionRuleOutput.cvtToResponse();
		conversionRuleOutput.setFieldFilter(serviceRqHeader.getRqHeader().getResponseFilter());
		conversionRuleOutput.addFieldFilter(fhd.getResponseFilters());

		if (conversionRuleOutput.isGenerics())
		{
			Object bf_functionData = conversionRuleOutput.loadResponseClasses();
			if (bf_functionData != null)
			{
				if (conversionRuleOutput.isExcludedAll())
				{
					outputServiceData = bf_functionData;
				}
				else
				{
					outputServiceData = functionBankFusion.getBankFusionDataType(primaryFunction, functionData, bf_functionData,
									false, conversionRuleOutput);
				}
			}
			setupOutputResponseData();
		}
		else
		{
			// filter excluded, just return empty class
			if (conversionRuleOutput.isExcludedAll())
			{
				outputServiceData = getNewInstanceBankFusionData(processingServiceData);
			}
			else
			{
				outputServiceData = functionBankFusion.getBankFusionDataType(primaryFunction, functionData,
								getNewInstanceBankFusionData(processingServiceData), false, conversionRuleOutput);
			}
		}

		return outputServiceData;
	}

	/**
	 * Set the output service header
	 * 
	 * @param functionHandler
	 *            - the Function Handler
	 * @param conversionRules
	 *            - the conversion rule to convert between user format and Equation format
	 */
	public ServiceRsHeader setOutputServiceHeader(FunctionHandlerData fhd, ConversionRules conversionRules) throws EQException
	{
		/**
		 * NOTE: this method is called outside of the EquationServiceHandler. Do not access any class variables without making sure
		 * that it is initialised properly. See setData();
		 */

		// Prepare the output header
		serviceRsHeader = new ServiceRsHeader();
		functionBankFusion.initialiseServiceRsHeader(serviceRsHeader);
		RsHeader rsHeader = serviceRsHeader.getRsHeader();

		// Return any messages to the caller in the Function Header:
		if (fhd.getFunctionMsgManager().getFunctionMessages().getMsgSev() != FunctionMessages.MSG_NONE)
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug(fhd.getFunctionMsgManager().getFunctionMessages().toString());
			}
			rsHeader.getStatus().setEqMessages(
							functionBankFusion.rtvMessagesAsMessageArray(fhd.getFunctionMsgManager().getFunctionMessages()
											.getMessages(), fhd.getScreenSetHandler()));
			/**
			 * This specifies the status of the transaction: 'S'-Success 'E'-Error 'I'-Success with Info Messages 'W'-Warning
			 * (non-overridden warnings exist) 'F'-Failure
			 */
			rsHeader.getStatus().setOverallStatus(
							FunctionRuntimeToolbox.cvtOverallStatus(new Integer(fhd.getFunctionMsgManager().getFunctionMessages()
											.getMsgSev()).intValue()));
		}
		else
		{
			EqMessage[] newMessages = new EqMessage[0];
			rsHeader.getStatus().setEqMessages(newMessages);
			rsHeader.getStatus().setOverallStatus(FunctionRuntimeToolbox.cvtOverallStatus(-1));
		}
		// set the output Session Id
		rsHeader.setSessionId(fhd.getFunctionInfo().getSessionId());

		// set the output Journal Key
		JournalHeader journalHeader = fhd.getJournalHeader();
		if (journalHeader != null)
		{
			JournalKey journalKey = new JournalKey();
			journalKey.setCcLinkTime(new Integer(journalHeader.getCcLinkTime()).toString());
			journalKey.setCcSequence(new Integer(journalHeader.getCcLinkSeq()).toString());
			journalKey.setCreateDate(new Integer(journalHeader.getCreateDate()).toString());
			journalKey.setDayInMonth(new Integer(journalHeader.getJrnDay()).toString());
			journalKey.setJobNumber(new Integer(journalHeader.getJobNumber()).toString());
			journalKey.setProgramRoot(journalHeader.getProgramRoot());
			journalKey.setRecoveryStatus(journalHeader.getRecovStat());
			journalKey.setSequence(new Integer(journalHeader.getJrnSequence()).toString());
			journalKey.setTime(new Integer(journalHeader.getJrnTime()).toString());
			journalKey.setTransactionType(journalHeader.getFunctionMode());
			journalKey.setWorkstationId(journalHeader.getWorkstationID());
			serviceRsHeader.setJournalKey(journalKey);
		}

		// Set the output parameter data
		ConversionRules conversionRulesForLink = null;

		// load to user extension
		ScreenSet mainScreenSet = fhd.getScreenSetHandler().rtvScreenSetMain();
		// make sure the linked service complex type class is loaded
		// find the linked option and load the class
		Object secondaryClass = null;
		if (mainScreenSet.isLinkService())
		{
			String secondaryFunctionId = mainScreenSet.getServiceLinkage().getSecondayFunctionIds()[0];
			Function secondaryFunction = mainScreenSet.getServiceLinkage().getSecondaryFunctions()[0];
			conversionRulesForLink = FunctionRuntimeToolbox.getConversionRulesForLinkService(conversionRules, secondaryFunction,
							mainScreenSet.getFunctionAdaptor().getSecondaryFunctionAdaptors().get(0));
			conversionRulesForLink.cvtToUserFormat();
			conversionRulesForLink.cvtToResponse();

			try
			{
				if (conversionRulesForLink.isGenerics())
				{
					secondaryClass = conversionRulesForLink.loadResponseClasses();
				}
				else
				{
					secondaryClass = getServiceDataClass(secondaryFunctionId);
				}
			}
			catch (EQException eqE)
			{
				String errorText = "setOutputServiceHeader - failed to retrieve linked service Complex type class.";
				LOG.error(errorText);
				throw new EQException(errorText + eqE);
			}
		}
		Toolbox.outputExtensionData(mainScreenSet, null, serviceRsHeader, secondaryClass, conversionRulesForLink);

		// set the workstation id
		serviceRsHeader.getJournalKey().setWorkstationId(fhd.getFunctionInfo().getWorkStationId());

		// set the correlation id
		rsHeader.setCorrelationId(correlationId);

		// set the version
		rsHeader.setBuid(EquationCommonContext.PLUGIN_VERSION);
		if (primaryFunction != null)
		{
			rsHeader.setVersion(primaryFunction.getXsdVersion());
		}

		// set checksum
		serviceRsHeader.setChecksum(mainScreenSet.getFunctionData().getChecksum());
		// Set output Enquiry details if they exist
		serviceRsHeader.getEnquiryRs().setPagingInformation(
						mainScreenSet.getFunctionData().rtvHeaderResponseData("EnquiryPagingData"));
		serviceRsHeader.getEnquiryRs().setTotalPages(
						new Integer(mainScreenSet.getFunctionData().rtvHeaderResponseDataAsInt("EnquiryTotalPages")));

		return serviceRsHeader;
	}

	/**
	 * Create a new instance of the BankFusion complex data type
	 * 
	 * @return the BankFusion complex data type
	 */
	private Object getNewInstanceBankFusionData(Object inputServiceData)
	{
		Object bf_functionData = null;
		try
		{
			LOG.debug("Instantiating the bf function data object ");
			bf_functionData = inputServiceData.getClass().newInstance();
		}
		catch (Exception e)
		{
			LOG.error("getNewInstanceBankFusionData", e);
			// Use the input parameter as an instance of the class
			bf_functionData = inputServiceData;
		}

		// return the output object
		return bf_functionData;
	}

	/**
	 * Create a new instance of the BankFusion complex data type
	 * 
	 * @param inputServiceData
	 *            the object representing the incoming data
	 * @param repeatingGroup
	 *            the name of the repeating group
	 * @return the complex type representation of the row
	 */
	public Object getNewRow(Object inputServiceData, String repeatingGroup) throws EQException
	{
		Object bf_row = null;
		try
		{
			bf_row = functionBankFusion.addRow(inputServiceData, repeatingGroup);
		}
		catch (Exception e)
		{
			LOG.error("getNewRow", e);
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}

		// return the output object
		return bf_row;
	}
	/**
	 * Set up the extension data for calls from meridian
	 * 
	 * @param linkageID
	 *            - this is the 3 character mnenonic of the linkage service
	 * @param extensionData
	 *            - this is any object, which will be passed to the linked service (outside of the functiondata.
	 */
	public void setExtensionData(String linkageID, Object extensionData)
	{
		linkageExtensionData.setServiceLinkageId(linkageID);
		linkageExtensionData.setUserExtension(extensionData);
		extensionDataSet = true;
	}

	/**
	 * Check XA Status and force rollback if required
	 * 
	 * @throws EQException
	 * 
	 */
	private void checkXAStatus(FunctionHandler functionHandler) throws EQException
	{
		// Only try to create transaction manager if XA is supposed to be used
		if (EquationCommonContext.getContext().isXAUsed())
		{
			// If we have an XA error then throw a BankFusion Exception now
			EquationTransactionManager txnManager = new EquationTransactionManager();

			if (txnManager != null && txnManager.isMarkedForRollback())
			{
				if (functionHandler.rtvMsgSev() != FunctionMessages.MSG_NONE)
				{
					EQXAException exception = new EQXAException(functionHandler.rtvFunctionMessages().rtvMessagesAsStringArray());
					throw new EQException(exception);
				}
				else
				{
					EQXAException exception = new EQXAException("XA Rollback Required");
					throw new EQException(exception);
				}
			}
		}
	}
	/**
	 * get the data source name
	 * 
	 * @return the data source name
	 */
	public String getDataSourceName()
	{
		return dataSourceName;
	}
	/**
	 * get the Service Request Header
	 * 
	 * @return the service request header
	 */
	public ServiceRsHeader getServiceRsHeader()
	{
		return serviceRsHeader;
	}
	/**
	 * get the output service data
	 * 
	 * @return the output service data
	 */
	public Object getOutputServiceData()
	{
		return outputServiceData;
	}

	/**
	 * Return the combined Response of header and data
	 * 
	 * @return the combined Response of header and data
	 */
	public Object getOutputResponseData()
	{
		return outputResponseData;
	}

	/**
	 * get the complex type class from Equation (GAZPF)
	 * 
	 * @param optionId
	 *            - the 3 character option id of the function in Equation.
	 * @return an object of the complex type
	 */
	public Object getServiceDataClass(String optionId) throws EQException
	{
		EquationStandardSession sessionFromPool = null;
		try
		{
			sessionFromPool = FunctionRuntimeToolbox.rtvEquationSessionFromPool(unit);

			// Load the function. This is needed in order to re-initialise the unit's class loader if needed
			XMLToolbox.getXMLToolbox().getFunction(sessionFromPool, optionId, false);

			FunctionAdaptor functionAdaptor = new FunctionAdaptor(sessionFromPool, optionId, "");
			Class<Object> c = functionAdaptor.getBFComplexTypeClass(sessionFromPool, optionId);
			Object serviceData = null;

			if (c == null)
			{
				throw new EQException(LanguageResources.getString("EquationServiceHandler.NoComplexType"));
			}

			try
			{
				serviceData = c.newInstance();
			}
			catch (Exception e)
			{
				LOG.error("FunctionBankFusion.NoComplexType", e);
				if (e instanceof EQException)
				{
					throw (EQException) e;
				}
				else
				{
					throw new EQException(e);
				}
			}

			return serviceData;

		}
		finally
		{
			FunctionRuntimeToolbox.returnEquationSessionToPool(unit, sessionFromPool);
		}
	}

	/**
	 * set the field on the service data with the value supplied
	 * 
	 * @return the data source name
	 */
	public void setFieldValue(Object serviceData, String fieldName, String fieldValue)
	{
		functionBankFusion.setFieldValue(serviceData, "set" + fieldName, fieldValue);
	}

	/**
	 * Set the field on the service data with the value supplied
	 * 
	 * @param serviceData
	 *            - the service data
	 * @param webServiceName
	 *            - the Web service name
	 * @param fieldValue
	 *            - the field value. This must be of the correct type
	 */
	public void setFieldObject(Object serviceData, String webServiceName, Object fieldValue)
	{
		functionBankFusion.setFieldObject(serviceData, "set" + webServiceName, fieldValue);
	}

	// this gets the admin alias from the equationConfiguration.properties and sets them up for the class
	private void setAdminCredentials()
	{
		Map<String, String[]> adminAlias = EquationCommonContext.getAdminAlias();
		String[] adminUsers = (String[]) adminAlias.get("Users");
		String[] adminPasswords = (String[]) adminAlias.get("Passwords");
		adminUserId = adminUsers[0];
		adminPassword = adminPasswords[0];
	}

	private void processValidate(FunctionHandler functionHandler, UiControlRq uiControl) throws EQException
	{
		mainScreenSet = functionHandler.getFhd().getScreenSetHandler().rtvScreenSetMain();
		function = mainScreenSet.getFunction();
		primaryFunction = mainScreenSet.getPrimaryFunction();
		functionData = mainScreenSet.getFunctionData();

		functionHandler.validateFkey(new Integer(uiControl.getCkey()).intValue(), uiControl.getFldValidate(), uiControl
						.getTransactionId());

		// Set the output function header
		setOutputServiceHeader(functionHandler.getFhd(), null);
	}
	private void processUpdate(FunctionHandler functionHandler, UiControlRq uiControl) throws EQException
	{
		mainScreenSet = functionHandler.getFhd().getScreenSetHandler().rtvScreenSetMain();
		function = mainScreenSet.getFunction();
		primaryFunction = mainScreenSet.getPrimaryFunction();
		functionData = mainScreenSet.getFunctionData();

		int nextProcess = functionHandler.actionFkey(new Integer(uiControl.getCkey()).intValue(), uiControl.getReason(), uiControl
						.getLoadFieldSet(), uiControl.getLoadField());

		// Set the output function header and update with nextProcess information.
		setOutputServiceHeader(functionHandler.getFhd(), null);
		ServiceRsHeader serviceRsHeader = new ServiceRsHeader();
		UiControlRs uiControlRs = serviceRsHeader.getUiControlRs();
		uiControlRs.setNextProcess(nextProcess);
		serviceRsHeader.setUiControlRs(uiControlRs);
		// checkXAStatus(functionHandler.getFhd().isUpdateMade());
	}
	private void processOnlineRetrieve(FunctionHandler functionHandler, UiControlRq uiControl) throws EQException
	{
		mainScreenSet = functionHandler.getFhd().getScreenSetHandler().rtvScreenSetMain();
		function = mainScreenSet.getFunction();
		primaryFunction = mainScreenSet.getPrimaryFunction();
		functionData = mainScreenSet.getFunctionData();

		functionHandler.applyLoadAPI(functionHandler);

		// Set the output function header and update with nextProcess information.
		setOutputServiceHeader(functionHandler.getFhd(), null);
	}
	private void processOnlineUpdate(FunctionHandler functionHandler, UiControlRq uiControl, String mode) throws EQException
	{
		mainScreenSet = functionHandler.getFhd().getScreenSetHandler().rtvScreenSetMain();
		function = mainScreenSet.getFunction();
		primaryFunction = mainScreenSet.getPrimaryFunction();
		functionData = mainScreenSet.getFunctionData();

		FunctionAPI functionAPI = new FunctionAPI(functionHandler.getFhd(), !mode.equals("D"), functionHandler.getFhd()
						.getFunctionInfo().isCommitProcessing(), false);
		functionAPI.applyTransaction(functionData);
		functionHandler.getFhd().getFunctionMsgManager().getFunctionMessages().insertMessages(functionAPI.getFunctionMessages());

		// Set the output function header and update with nextProcess information.
		setOutputServiceHeader(functionHandler.getFhd(), null);
	}
	private boolean processNonDesktop(FunctionHandler functionHandler, UiControlRq uiControl, String mode, String optionId,
					ServiceRqHeader serviceRqHeader, ExtensionDataRq misysExtensionDataRq) throws EQException
	{
		// clear messages
		functionHandler.getFhd().getFunctionMsgManager().clearAllMessages();

		// setup details in function handler based on the parameters
		functionHandler.getFhd().getFunctionInfo().setGenerateWarningInfo(
						serviceRqHeader.getRqHeader().getOverrides().getOverrideType().equals(EqDataType.NO));
		functionHandler.getFhd().setServiceMode(mode);

		// setup the option
		LOG.debug("Initialising function handler for option " + optionId);
		boolean valid = functionHandler.doNewTransaction(optionId, "");
		if (!valid)
		{
			outputServiceData = FunctionRuntimeToolbox.loadDefaultResponse(null, primaryOptionId, null, functionHandler,
							outputServiceData);
			serviceRsHeader = Toolbox.setupReturnMessaage(functionHandler.getFhd().getFunctionMsgManager().getOtherMessages(),
							functionHandler.getFhd().getScreenSetHandler());
			return false;
		}

		// Map the Bankfusion parameter data into Function data
		mainScreenSet = functionHandler.getFhd().getScreenSetHandler().rtvScreenSetMain();
		function = mainScreenSet.getFunction();
		primaryFunction = mainScreenSet.getPrimaryFunction();
		functionData = mainScreenSet.getFunctionData();

		// Conversion rules
		ConversionRules conversionRule = FunctionRuntimeToolbox.getConversionRules(serviceRqHeader, functionHandler.getFhd());
		conversionRule.cvtToEquationFormat();
		conversionRule.cvtToRequest();

		// option id has been overridden?
		if (!optionId.equals(serviceRqHeader.getOptionId()))
		{
			valid = functionHandler.validateLinkService(serviceRqHeader.getOptionId());
			if (!valid)
			{
				outputServiceData = FunctionRuntimeToolbox.loadDefaultResponse(primaryFunction, primaryFunction.getId(),
								conversionRule, functionHandler, outputServiceData);
				serviceRsHeader = Toolbox.setupReturnMessaage(functionHandler.getFhd().getFunctionMsgManager().getOtherMessages(),
								functionHandler.getFhd().getScreenSetHandler());
				return false;
			}
		}

		functionBankFusion.loadToFunctionData(primaryFunction, functionData, processingServiceData, false, conversionRule);
		Toolbox.loadExtensionData(mainScreenSet, linkageExtensionData, misysExtensionDataRq, conversionRule);

		// any errors?
		if (conversionRule.getFunctionMessages().getMsgSev() == FunctionMessages.MSG_ERROR)
		{
			outputServiceData = FunctionRuntimeToolbox.loadDefaultResponse(primaryFunction, primaryFunction.getId(),
							conversionRule, functionHandler, outputServiceData);
			serviceRsHeader = Toolbox.setupReturnMessaage(conversionRule.getFunctionMessages(), null);
			return false;
		}

		if (LOG.isDebugEnabled())
		{
			LOG.debug("Function data \n" + functionData);
		}

		// If Equation service contains keys, then perform retrieval
		if (mainScreenSet.isKeyFieldExists())
		{
			LOG.debug("Loading key");
			functionHandler.applyRetrieveTransaction();

			// If an input service, then reload data
			if (!function.isAllowedEnq() && !mode.equals(IEquationStandardObject.FCT_RTV))
			{
				// reload the Bankfusion data into Function data
				functionBankFusion.loadToFunctionData(primaryFunction, functionData, processingServiceData, false, conversionRule);
				Toolbox.loadExtensionData(mainScreenSet, linkageExtensionData, misysExtensionDataRq, conversionRule);
			}
		}

		// No error yet and for non-enquiry only
		if (functionHandler.rtvMsgSev() != FunctionMessages.MSG_ERROR && !function.isAllowedEnq())
		{
			// retrieval
			if (mode.equals(IEquationStandardObject.FCT_RTV))
			{
				LOG.debug("Applying transaction retrieve");
				// do nothing, retrieval happens above
			}

			// validate
			else if (mode.equals(IEquationStandardObject.FCT_VAL))
			{
				LOG.debug("Applying transaction validate");
				functionHandler.applyValidateTransaction();
			}

			// Delete?
			else if (mode.equals(IEquationStandardObject.FCT_DEL))
			{
				LOG.debug("Applying transaction delete");
				functionHandler.applyTransactionDelete();
				// checkXAStatus(functionHandler.getFhd().isUpdateMade());
			}

			// Maintain or Update
			else
			{
				LOG.debug("Applying transaction");
				functionHandler.applyTransaction();
				// checkXAStatus(functionHandler.getFhd().isUpdateMade());
			}
		}

		// Set the output function header
		setOutputServiceHeader(functionHandler.getFhd(), conversionRule);
		return true;
	}

	/**
	 * Return the equivalent complex data type of the Function
	 * 
	 * @param session
	 *            - the Equation Standard Session
	 * @param id
	 *            - the id
	 * @param pvId
	 *            - the pv id
	 * 
	 * @return the complex data type
	 * 
	 * @throws EQException
	 */
	public Object getBFComplexType(String optionId, String webServiceName) throws EQException
	{
		EquationStandardSession sessionFromPool = null;
		try
		{
			sessionFromPool = FunctionRuntimeToolbox.rtvEquationSessionFromPool(unit);

			Function function = XMLToolbox.getXMLToolbox().getFunction(sessionFromPool, optionId, false);

			// For enhanced XSD only
			if (!function.chkXSDGeneric())
			{
				return null;
			}

			FunctionAdaptor functionAdaptor = new FunctionAdaptor(sessionFromPool, optionId, "");
			String fullClassName = ComplexTypeToolbox.getComplexTypePackageEnhancedXsd(function, webServiceName);
			Object serviceData = functionAdaptor.getBankFusionDataType(sessionFromPool, "", fullClassName);
			return serviceData;
		}
		finally
		{
			FunctionRuntimeToolbox.returnEquationSessionToPool(unit, sessionFromPool);
		}
	}

	/**
	 * Set debug mode
	 * 
	 * @param debugMode
	 *            - debug mode
	 */
	public void setDebugMode(boolean debugMode)
	{
		this.debugMode = debugMode;
	}

	/**
	 * Return the data as function data
	 * 
	 * @return the data as function data
	 */
	public FunctionData rtvFunctionData()
	{
		return this.functionData;
	}

	/**
	 * Setup the output response data
	 * 
	 * @return the output response data
	 */
	private Object setupOutputResponseData() throws EQException
	{
		outputResponseData = null;
		if (primaryFunction == null)
		{
			return outputResponseData;
		}
		if (!primaryFunction.chkXSDGeneric())
		{
			return outputResponseData;
		}

		outputResponseData = getResponseCombinedType(primaryFunction, serviceRsHeader, outputServiceData);
		return outputResponseData;
	}

	/**
	 * Return the request combined type
	 * 
	 * @param service
	 *            - the function
	 * @param serviceHeader
	 *            - the service response header
	 * @param serviceData
	 *            - the data
	 */
	public Object getResponseCombinedType(Function service, ServiceRsHeader serviceHeader, Object serviceData) throws EQException
	{
		String combinedResponseType = XSDToolbox.getCombinedComplexTypeResponseName(service);
		Object combinedData = getBFComplexType(service.getId(), combinedResponseType);

		if (combinedData != null)
		{
			setFieldObject(combinedData, XSDToolbox.SERVICE_HEADER_TAG, serviceHeader);
			setFieldObject(combinedData, XSDToolbox.SERVICE_DATA_TAG, serviceData);
		}

		return combinedData;
	}

	/**
	 * Set up data
	 * 
	 * @param functionData
	 *            - the Function data
	 * @param primaryFunction
	 *            - the Primary function
	 * @param inputServiceData
	 *            - the Input service Data
	 * @param correlationId
	 *            - the correlation id
	 */
	public void setData(FunctionData functionData, Function primaryFunction, Object inputServiceData, String correlationId)
	{
		this.functionData = functionData;
		this.primaryFunction = primaryFunction;
		this.processingServiceData = inputServiceData;
		this.correlationId = correlationId;
	}

}
