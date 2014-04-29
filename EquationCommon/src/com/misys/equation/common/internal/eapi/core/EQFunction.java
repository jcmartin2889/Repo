// ------------------------------------------------------------------------------
// Copyright Misys IBS
//
// Owner: Des Middlemass
//
// Class: EQFunction: Base class to store the state for an Equation function call
// -------------------------------------------------------------------------------
package com.misys.equation.common.internal.eapi.core;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.misys.equation.common.utilities.EQCommonConstants;
import com.misys.equation.common.utilities.Toolbox;

/***********************************************************************************************************************************
 * Internal class to control an Equation API call.
 * <P>
 * EQTransactionImpl and EQEnquiryImpl extend this class to implement their behaviour. EQFunction's public methods are for
 * diagnostic purposes only.
 * <P>
 * 
 * @author Misys International Banking Systems Ltd.
 */
public class EQFunction extends EQObjectImpl implements java.io.Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQFunction.java 8582 2010-08-11 01:26:18Z esther.williams $";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006" </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";
	/*******************************************************************************************************************************
	 * The interface version.
	 * <P>
	 * The minimum version of Equation that this interface is compatible with.
	 */
	public static final String equationVersion = "CX011";
	// Internal API mode Constants
	/*******************************************************************************************************************************
	 * Mode Constant - Indicates the Function is initialising.
	 */
	public static final String API_MODE_INIT = "I";
	/*******************************************************************************************************************************
	 * Mode Constant - Indicates the Function is verifying.
	 */
	public static final String API_MODE_VERIFY = "V";
	/*******************************************************************************************************************************
	 * Mode Constant - Indicates the Function is retrieving.
	 */
	public static final String API_MODE_RETRIEVE = "R";
	/*******************************************************************************************************************************
	 * Mode Constant - Indicates the Function is updating.
	 */
	public static final String API_MODE_UPDATE = "U";
	/*******************************************************************************************************************************
	 * API SQL Stored Procedure Parameters
	 */
	private static final int PARAM_EQFUNCTION_CONTROLS = 1;
	private static final int PARAM_EQFUNCTION_SUPERVISOR = 2;
	private static final int PARAM_EQFUNCTION_INCREMENTALCONTROL = 3;
	private static final int PARAM_EQFUNCTION_MESSAGES = 4;
	private static final int PARAM_EQFUNCTION_OVERRIDES = 5;
	private static final int PARAM_EQFUNCTION_JOURNALKEY = 6;
	private static final int PARAM_EQFUNCTION_EVENTKEY = 7;
	private static final int PARAM_EQFUNCTION_BEFOREIMAGE = 8;
	private static final int PARAM_EQFUNCTION_FIXEDIMAGE = 9;
	private static final int PARAM_EQFUNCTION_LIST_CONTROLS = 10;
	private static final int[] PARAM_EQFUNCTION_LIST_DSROWS = { 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
	private static final int PARAM_EQFUNCTION_PROMPT_CONTROLS = 21;
	private static final int PARAM_EQFUNCTION_PROMPT_FILTER = 22;
	private static final int[] PARAM_EQFUNCTION_PROMPT_DSROWS = { 23, 24, 25, 26, 27, 28, 29, 30, 31, 32 };
	private static final int PARAM_EQFUNCTION_FIELDSTATE = 33;
	private static final int PARAM_EQFUNCTION_FKEY = 34;
	private static final int PARAM_EQFUNCTION_CRMWARNMSG = 35;
	// For JDBC Function call
	private static final String storedProcedureCallEQFUNC = "CALL EQFNCTJ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	// incremental mode propoerty
	// determines if we run in batch (false) or incremental(true) mode
	protected boolean bIncrementalMode;
	// enquiry property
	// prevents list data from returning to the host
	// Note that Transaction classes can set also set this to true
	// if they are running in Enquiry mode.
	// This also means that the object cannot update the database
	private boolean bEnquiry;
	// valid property
	// set to false whenever a value is modified
	// set to true after an API call in validate or update mode when no errors are returned
	private boolean valid;
	// complete property
	// set to true when the function has updated the database
	protected boolean complete;
	// Class to support prompting (incremental mode only) - exposed for better access performance
	protected transient EQPromptImpl prompt;
	/*
	 * Controls
	 */
	protected String AUDITUSERID;
	protected String INPUTREF;
	protected String APIMODE;
	protected String maintenanceMode;
	private String RETURNJRNKEY;
	private String RETURNEVENTKEY;
	private String RETURNBEFOREIMAGE;
	private String FORMATOUTPUT;
	private String USEBEFOREIMAGE;
	protected String DEFAULTCHARGES;
	protected String AUTOOVERRIDE;
	private String APPLYUSINGEXTERNALINPUT;
	private String APPLYUSINGRECOVERY;
	protected String CCSTARTTRANSACTION;
	protected String CCENDTRANSACTION;
	private String EXTERNALCOMMIT;
	protected String CHECKEXTRADATA;
	protected String JOURNALENQUIRY;
	protected String SUPERVISORUSID;
	private String ENCRYPTION;
	protected String SUPERVISORCOMMENT;
	protected String INPUTBRANCH;
	protected String CHECKREF;
	private static final int EQFUNCTION_CONTROLS_SIZE = 128;
	private static final String EQFUNCTION_BLANK_CONTROLS = EQCommonConstants.initBlankStringBuffer(EQFUNCTION_CONTROLS_SIZE)
					.toString();
	/*
	 * Supervisor password - always encryted
	 */
	protected transient byte[] SUPERVISORPASSWORD;
	private static final int EQFUNCTION_SUPERVISORPWD_SIZE = 64;
	private static final byte[] EQFUNCTION_BLANK_SUPERVISORPWD = EQCommonConstants
					.initBlankByteArray(EQFUNCTION_SUPERVISORPWD_SIZE);
	/*
	 * Incremental Mode Controls
	 */
	protected String ENTRYEVENT;
	protected String FORMAT;
	private String SERVERDATE;
	private String SERVERTIME;
	protected String PVROOT;
	protected String promptFieldID;
	protected String promptSelected;
	private static final int EQFUNCTION_INCREMENTALCONTROL_SIZE = 128;
	private static final String EQFUNCTION_BLANK_INCREMENTALCONTROL = EQCommonConstants.initBlankStringBuffer(
					EQFUNCTION_INCREMENTALCONTROL_SIZE).toString();
	/*
	 * Equation Journal Key
	 */
	protected String GYWSID; // 0-4
	protected String GYDIM; // 4-6
	protected String GYTIM; // 6-12
	protected String GYSEQ; // 12-19
	protected String GYFRO; // 19-23
	protected String GYJTT; // 23-24
	protected String GYCDT; // 24-31
	protected String GYCLTM; // 31-37
	protected String GYCSEQ; // 37-44
	protected String GYJOB; // 44-50
	// String GYREC; // 50-51
	private static final int EQFUNCTION_GY_KEY_SIZE = 51;
	private static final String EQFUNCTION_BLANK_GY_KEY = EQCommonConstants.initBlankStringBuffer(EQFUNCTION_GY_KEY_SIZE)
					.toString();
	private static final int EQFUNCTION_EVENT_KEY_SIZE = 512;
	private static final String EQFUNCTION_BLANK_EVENT_KEY = EQCommonConstants.initBlankStringBuffer(EQFUNCTION_EVENT_KEY_SIZE)
					.toString();
	protected String AAHEVNK;
	/*
	 * Equation's Before Image
	 */
	private byte[] BEFOREIMAGE;
	protected ArrayList<EQMessage> OVERRIDES;
	protected ArrayList<String> TXTFLD;
	protected ArrayList<String> FKEY;
	/*******************************************************************************************************************************
	 * Default Constructor.
	 */
	protected EQFunction()
	{
	}
	/*******************************************************************************************************************************
	 * Constructor given an EQObjectMetaData of field definitions.
	 */
	@Override
	protected void initialise(EQObjectMetaData map)
	{
		super.initialise(map);
		bIncrementalMode = false;
		bEnquiry = map.isEnquiry();
		valid = false;
		complete = false;
		/*
		 * Controls - others initialised by reset
		 */
		AUDITUSERID = "";
		INPUTREF = "";
		APIMODE = "";
		maintenanceMode = "";
		SUPERVISORUSID = "";
		ENCRYPTION = "Y";
		SUPERVISORCOMMENT = "";
		APPLYUSINGEXTERNALINPUT = "";
		APPLYUSINGRECOVERY = "";
		INPUTBRANCH = "";
		CHECKREF = "N";
		/*
		 * Supervisor
		 */
		SUPERVISORPASSWORD = null;
		/*
		 * Incremental Mode Controls - others initialised by reset
		 */
		// initialised by reset
		MSGS = null;
		OVERRIDES = null;
		TXTFLD = null;
		FKEY = null;
		/*
		 * Before Image
		 */
		BEFOREIMAGE = new byte[0];
		promptFieldID = "";
		promptSelected = "N";
		prompt = null;
		// Application specific parameters retrieved from environment
		try
		{
			EQEnvironment env = EQEnvironment.getAppEnvironment();
			RETURNJRNKEY = env.getProperty("API_RETURNJRNKEY", "Y");
			RETURNEVENTKEY = env.getProperty("API_RETURNEVENTKEY", "Y");
			RETURNBEFOREIMAGE = env.getProperty("API_RETURNBEFOREIMAGE", "N");
			FORMATOUTPUT = env.getProperty("API_FORMATOUTPUT", "N");
			USEBEFOREIMAGE = env.getProperty("API_USEBEFOREIMAGE", "N");
			DEFAULTCHARGES = env.getProperty("API_DEFAULTCHARGES", "N");
			CHECKEXTRADATA = env.getProperty("API_CHECKEXTRADATA", "Y");
			JOURNALENQUIRY = env.getProperty("API_JOURNALENQUIRY", "N");
		}
		catch (Exception e)
		{
		}
		// no need to worry, env problems will get reported earlier
	}
	/*******************************************************************************************************************************
	 * Specifies whether the iSeries job executing this function will issue commits.
	 * <P>
	 * By default the iSeries jobs used to process Equation's functions will be in control of when the commit operation is executed.
	 * This flag allows the calling application to issue the commit.
	 * <P>
	 * 
	 * @param externalCommit
	 *            if true the job will not execute the commit itself.
	 */
	protected void setExternalCommitFlag(boolean externalCommit)
	{
		EXTERNALCOMMIT = externalCommit ? "Y" : "N";
	}
	/*******************************************************************************************************************************
	 * Determine if an iSeries job executing this function will issue commits.
	 * <P>
	 * 
	 * @return true if the job will not execute commits itself.
	 */
	protected boolean getExternalCommitFlag()
	{
		return EXTERNALCOMMIT.equalsIgnoreCase("Y");
	}
	/*******************************************************************************************************************************
	 * Marks the function's data as invalid.
	 */
	protected void invalidate()
	{
		valid = false;
	}
	/*******************************************************************************************************************************
	 * Determine if the function has been succesfully validated.
	 * <P>
	 * Until a succesful validation call to Equation the function is marked invalid.
	 * <P>
	 * If the application modifies any field's value after the function has been validated it is marked as invalid again.
	 * <P>
	 * Note that only applies if the application modifies field values through the function or list's methods. Not if field objects
	 * are modified directly.
	 * <P>
	 * 
	 * @return true if the function's data has been validated by Equation.
	 */
	protected boolean isValid()
	{
		if (list != null && !list.isValid())
		{
			return false;
		}
		return valid;
	}
	/*******************************************************************************************************************************
	 * Determine if the function is operating as an Enquiry.
	 * <P>
	 * Functions are used by both Enquiries and Transactions. A transaction object can, however, be used to offer an enquiry service
	 * by not providing the ability to update the database. In this situation it marks the function(s) as operating in Enquiry mode.
	 * <P>
	 * 
	 * @return true if the object is acting as an enquiry. i.e. it cannot update the database.
	 */
	protected boolean isEnquiryMode()
	{
		return bEnquiry;
	}
	/*******************************************************************************************************************************
	 * Get the current mode of the Function call.
	 * <P>
	 * This mode is an internal state variable which is set automatically. It is exposed for diagnostic purposes only.
	 * <P>
	 * 
	 * @return the mode for the last call. One of the Mode Constants.
	 */
	public String getAPIMode()
	{
		return APIMODE;
	}
	/*******************************************************************************************************************************
	 * Get the string for the function's SQL Stored Procedure APJI call.
	 * <P>
	 * 
	 * @return the string used for the function's SQL Stored Procedure API call e.g. "CALL PROCNAME(?,?,.....?)
	 */
	private String getStoredProcedureCallString()
	{
		return EQFunction.storedProcedureCallEQFUNC;
	}
	/*******************************************************************************************************************************
	 * Resets all of the function's attributes to its uninitialised state.
	 */
	@Override
	protected void reset()
	{
		super.reset();
		promptFieldID = "";
		prompt = null;
		valid = false;
		complete = false;
		resetKey();
		resetPrompt();
	}
	/*******************************************************************************************************************************
	 * Reset method for the function's Key properties.
	 */
	private void resetKey()
	{
		SUPERVISORPASSWORD = null;
		SUPERVISORUSID = "";
		SUPERVISORCOMMENT = "";
		setControls(EQFUNCTION_BLANK_CONTROLS);
		setIncrementalControls(EQFUNCTION_BLANK_INCREMENTALCONTROL);
		resetGYKey();
		resetEventKey();
		MSGS = new ArrayList<EQMessage>(0);
		OVERRIDES = new ArrayList<EQMessage>(0);
		AUTOOVERRIDE = "N";
		TXTFLD = new ArrayList<String>(0);
		FKEY = new ArrayList<String>(0);
	}
	/*******************************************************************************************************************************
	 * Reset method for the equation journal properties.
	 */
	private void resetGYKey()
	{
		try
		{
			setEquationJournalKey(EQFUNCTION_BLANK_GY_KEY);
			complete = false;
		}
		catch (Exception e)
		{
		}
	}
	/*******************************************************************************************************************************
	 * Reset method for the equation event journal properties.
	 */
	private void resetEventKey()
	{
		try
		{
			setEQEventKey(EQFUNCTION_BLANK_EVENT_KEY);
			complete = false;
		}
		catch (Exception e)
		{
		}
	}
	/*******************************************************************************************************************************
	 * Register the output fields in the callable statement. This is done for all EQFunction parameters and the EQList &
	 * EQPromptImpl parameters.
	 */
	private void registerOutParameters(CallableStatement cs) throws SQLException
	{
		// EQFunction Controls
		cs.registerOutParameter(PARAM_EQFUNCTION_CONTROLS, EQCommonConstants.VARCHAR);
		// EQFunction Supervisor Data
		// cs.registerOutParameter(PARAM_EQFUNCTION_SUPERVISOR,EQCommonConstants.CHAR);
		// EQFunction Incremental Control
		cs.registerOutParameter(PARAM_EQFUNCTION_INCREMENTALCONTROL, EQCommonConstants.VARCHAR);
		// EQFunction Messages
		cs.registerOutParameter(PARAM_EQFUNCTION_MESSAGES, EQCommonConstants.VARCHAR);
		// EQFunction Overridden Messages
		cs.registerOutParameter(PARAM_EQFUNCTION_OVERRIDES, EQCommonConstants.VARCHAR);
		// EQFunction Journal Key
		cs.registerOutParameter(PARAM_EQFUNCTION_JOURNALKEY, EQCommonConstants.VARCHAR);
		// EQFunction Event Key
		cs.registerOutParameter(PARAM_EQFUNCTION_EVENTKEY, EQCommonConstants.VARCHAR);
		// EQFunction Before Fixed Image
		cs.registerOutParameter(PARAM_EQFUNCTION_BEFOREIMAGE, EQCommonConstants.VARCHAR);
		// EQFunction Fixed Image
		cs.registerOutParameter(PARAM_EQFUNCTION_FIXEDIMAGE, EQCommonConstants.VARCHAR);
		// EQFunction List Controls
		cs.registerOutParameter(PARAM_EQFUNCTION_LIST_CONTROLS, EQCommonConstants.VARCHAR);
		// EQFunction List Rows Input/Output
		for (int i = 0; i < EQList.nListParameters; i++)
		{
			cs.registerOutParameter(PARAM_EQFUNCTION_LIST_DSROWS[i], EQCommonConstants.VARCHAR);
		}
		// EQPromptImpl Controls
		cs.registerOutParameter(PARAM_EQFUNCTION_PROMPT_CONTROLS, EQCommonConstants.VARCHAR);
		// EQPromptImpl Filter
		cs.registerOutParameter(PARAM_EQFUNCTION_PROMPT_FILTER, EQCommonConstants.VARCHAR);
		// EQPromptImpl Output
		for (int i = 0; i < EQList.nListParameters; i++)
		{
			cs.registerOutParameter(PARAM_EQFUNCTION_PROMPT_DSROWS[i], EQCommonConstants.VARCHAR);
		}
		// EQFunction Field State
		cs.registerOutParameter(PARAM_EQFUNCTION_FIELDSTATE, EQCommonConstants.VARCHAR);
		// EQFunction Function Key
		cs.registerOutParameter(PARAM_EQFUNCTION_FKEY, EQCommonConstants.VARCHAR);
		// EQFunction CRM Messages
		cs.registerOutParameter(PARAM_EQFUNCTION_CRMWARNMSG, EQCommonConstants.VARCHAR);
	}
	/*******************************************************************************************************************************
	 * Set the parameters in the CallableStatement.
	 */
	private void populateCallableStatement(CallableStatement cs) throws SQLException
	{
		cs.setString(PARAM_EQFUNCTION_CONTROLS, getControls());
		// pass the supervisor password as bytes
		cs.setBytes(PARAM_EQFUNCTION_SUPERVISOR, getSupervisorPasswordEncrypted());
		cs.setString(PARAM_EQFUNCTION_INCREMENTALCONTROL, getIncrementalControls());
		cs.setBytes(PARAM_EQFUNCTION_BEFOREIMAGE, getBeforeImage());
		cs.setString(PARAM_EQFUNCTION_FIXEDIMAGE, getFieldImage());
		if (list == null)
		{
			cs.setString(PARAM_EQFUNCTION_LIST_CONTROLS, "");
			for (int i = 0; i < EQList.nListParameters; i++)
			{
				cs.setString(PARAM_EQFUNCTION_LIST_DSROWS[i], "");
			}
		}
		else
		{
			if (bEnquiry)
			{
				for (int i = 0; i < EQList.nListParameters; i++)
				{
					cs.setString(PARAM_EQFUNCTION_LIST_DSROWS[i], "");
				}
				list.resetNumberOfRowsReturned();
			}
			else
			{
				String sImages[] = list.getListImage();
				for (int i = 0; i < EQList.nListParameters; i++)
				{
					cs.setString(PARAM_EQFUNCTION_LIST_DSROWS[i], sImages[i]);
				}
			}
			cs.setString(PARAM_EQFUNCTION_LIST_CONTROLS, list.getControls());
		}
	}
	/*******************************************************************************************************************************
	 * Set the EQFunction's attributes from the results returned from the call.
	 */
	private int setFieldsFromCallableStatement(CallableStatement cs) throws EQException
	{
		try
		{
			boolean debugLogging = LOG.isDebugEnabled();
			if (debugLogging)
			{
				LOG.debug("####### Begin Results from API Call");
			}
			MSGS = new ArrayList<EQMessage>(0);
			OVERRIDES = new ArrayList<EQMessage>(0);
			// extract controls first to get status info
			setControls(cs.getString(PARAM_EQFUNCTION_CONTROLS));
			if (debugLogging)
			{
				LOG.debug("####### Last Return Code = " + status + ' ' + getStatusString());
			}
			if (status == EQTransaction.STATUS_FAILED)
			{
				// Extract any messages (info/warnings/errors)
				setMessages(cs.getString(PARAM_EQFUNCTION_MESSAGES));
				int numMessages = MSGS.size();
				if (numMessages > 0)
				{
					String msgText[] = new String[numMessages];
					for (int i = 0; i < numMessages; i++)
					{
						EQFieldMessage msg = (EQFieldMessage) MSGS.get(i);
						msgText[i] = msg.getFormattedMessage();
						if (debugLogging)
						{
							LOG.debug("####### Message = " + i + ' ' + msgText[i]);
						}
					}
					throw new EQException(msgText);
				}
				throw new EQException("Return code STATUS_FAILED(-1)");
			}
			else if (status == EQTransaction.STATUS_VALID)
			{
				valid = true;
				if (list != null)
				{
					list.valid = true;
				}
			}
			else if (status == EQTransaction.STATUS_COMPLETE)
			{
				setEquationJournalKey(cs.getString(PARAM_EQFUNCTION_JOURNALKEY));
				setEQEventKey(cs.getString(PARAM_EQFUNCTION_EVENTKEY));
				complete = true;
				modified = false;
				if (list != null)
				{
					list.modified = false;
				}
			}
			setIncrementalControls(cs.getString(PARAM_EQFUNCTION_INCREMENTALCONTROL));
			if (debugLogging)
			{
				LOG.debug("####### Set Before Image");
			}
			setBeforeImage(cs.getBytes(PARAM_EQFUNCTION_BEFOREIMAGE));
			// Extract individual field values
			if (debugLogging)
			{
				LOG.debug("####### Set Fields From Image");
			}
			setFieldsFromImage(cs.getString(PARAM_EQFUNCTION_FIXEDIMAGE));
			if (list != null && PVROOT.length() == 0)
			{
				if (!bEnquiry)
				{
					list.rowsReset();
				}
				list.setControls(cs.getString(PARAM_EQFUNCTION_LIST_CONTROLS));
				String[] imageArray = new String[EQList.nListParameters];
				for (int i = 0; i < EQList.nListParameters; i++)
				{
					if (list.NUMRET[i] > 0)
					{
						imageArray[i] = cs.getString(PARAM_EQFUNCTION_LIST_DSROWS[i]);
					}
				}
				if (debugLogging)
				{
					LOG.debug("####### Set List");
				}
				list.setList(imageArray);
			}
			// Extract field attributes (input/output/visibility etc)
			if (bIncrementalMode)
			{
				if (debugLogging)
				{
					LOG.debug("####### Set Field State");
				}
				setFieldState(cs.getString(PARAM_EQFUNCTION_FIELDSTATE));
			}
			// Extract any messages (info/warnings/errors)
			if (debugLogging)
			{
				LOG.debug("####### Set Messages");
			}
			setMessages(cs.getString(PARAM_EQFUNCTION_MESSAGES));
			// Extract any previosuly overriden warning messages
			if (debugLogging)
			{
				LOG.debug("####### Set Overrides");
			}
			setOverrides(cs.getString(PARAM_EQFUNCTION_OVERRIDES));
			// Extract any function keys and selection keys
			if (debugLogging)
			{
				LOG.debug("####### Set Keys");
			}
			setKeys(cs.getString(PARAM_EQFUNCTION_FKEY));
			// Extract any CRM messages
			if (debugLogging)
			{
				LOG.debug("####### Set CRM Messages");
			}
			setCRMMessages(cs.getString(PARAM_EQFUNCTION_CRMWARNMSG));
			// These should be reset after each attemp to override
			AUTOOVERRIDE = "N";
			if (debugLogging)
			{
				LOG.debug("####### End Results from API Call");
			}
		}
		catch (SQLException e)
		{
			LOG.error("EQFunction::setFieldsFromCallableStatement: SQL error:" + Toolbox.getExceptionMessage(e) + e.getSQLState()
							+ e.getErrorCode());
			status = EQObject.STATUS_SQL_ERROR;
		}
		return status;
	}
	/*******************************************************************************************************************************
	 * Get controls.
	 * <P>
	 * 
	 * @return a string containing the control values
	 */
	private String getControls()
	{
		char controls[] = new char[EQFUNCTION_CONTROLS_SIZE];
		// initialse with blanks
		EQFUNCTION_BLANK_CONTROLS.getChars(0, EQFUNCTION_CONTROLS_SIZE, controls, 0);
		// copy over any control information
		AUDITUSERID.getChars(0, AUDITUSERID.length(), controls, 0);
		metaData.identifier.getChars(0, metaData.identifier.length(), controls, 10);
		metaData.getRootID().getChars(0, metaData.getRootID().length(), controls, 13);
		INPUTREF.getChars(0, INPUTREF.length(), controls, 27);
		APIMODE.getChars(0, APIMODE.length(), controls, 43);
		maintenanceMode.getChars(0, maintenanceMode.length(), controls, 44);
		RETURNJRNKEY.getChars(0, RETURNJRNKEY.length(), controls, 45);
		RETURNEVENTKEY.getChars(0, RETURNEVENTKEY.length(), controls, 46);
		RETURNBEFOREIMAGE.getChars(0, RETURNBEFOREIMAGE.length(), controls, 47);
		FORMATOUTPUT.getChars(0, FORMATOUTPUT.length(), controls, 50);
		DEFAULTCHARGES.getChars(0, DEFAULTCHARGES.length(), controls, 52);
		AUTOOVERRIDE.getChars(0, AUTOOVERRIDE.length(), controls, 53);
		SUPERVISORUSID.getChars(0, SUPERVISORUSID.length(), controls, 54);
		ENCRYPTION.getChars(0, ENCRYPTION.length(), controls, 64);
		SUPERVISORCOMMENT.getChars(0, SUPERVISORCOMMENT.length(), controls, 65);
		APPLYUSINGEXTERNALINPUT.getChars(0, APPLYUSINGEXTERNALINPUT.length(), controls, 95);
		APPLYUSINGRECOVERY.getChars(0, APPLYUSINGRECOVERY.length(), controls, 96);
		CCSTARTTRANSACTION.getChars(0, CCSTARTTRANSACTION.length(), controls, 97);
		CCENDTRANSACTION.getChars(0, CCENDTRANSACTION.length(), controls, 98);
		// CALLSTATUS.getChars(0, CALLSTATUS.length(), controls, 99);
		// DETAILEDSTATUS.getChars(0, DETAILEDSTATUS.length(), controls, 100);
		EXTERNALCOMMIT.getChars(0, EXTERNALCOMMIT.length(), controls, 105);
		// Reset of job is not to be done at a function level. See EQSession.
		controls[106] = 'N';
		CHECKEXTRADATA.getChars(0, CHECKEXTRADATA.length(), controls, 107);
		if (isEnquiryMode())
		{
			JOURNALENQUIRY.getChars(0, JOURNALENQUIRY.length(), controls, 108);
		}
		else
		{
			controls[108] = 'N';
		}
		INPUTBRANCH.getChars(0, INPUTBRANCH.length(), controls, 109);
		CHECKREF.getChars(0, CHECKREF.length(), controls, 113);
		if (LOG.isDebugEnabled())
		{
			LOG.debug("####### API Mode - " + APIMODE);
			LOG.debug("####### Transaction Type - " + maintenanceMode);
			LOG.debug("####### Automatic Override - " + AUTOOVERRIDE);
			LOG.debug("####### CC Start - " + CCSTARTTRANSACTION);
			LOG.debug("####### CC End - " + CCENDTRANSACTION);
			LOG.debug("####### External Commit - " + EXTERNALCOMMIT);
			LOG.debug("####### Input Branch - " + INPUTBRANCH);
			LOG.debug("####### Check Reference - " + CHECKREF);
		}
		return EQCommonConstants.rightTrimmedString(controls, 0, EQFUNCTION_CONTROLS_SIZE);
	}
	/*******************************************************************************************************************************
	 * Set the EQFunction's controls, i.e control parameters returned from call.
	 */
	private void setControls(String s)
	{
		// We ignore AUDITUSERID as it is input only
		// ENTRYOPTID = s.substring(10, 13);
		// We ignore INPUTREF as it is input only
		// We ignore APIMODE as it is input only
		// We ignore maintenanceMode as it is input only
		// We ignore RETURNJRNKEY as it is input only
		// We ignore RETURNEVENTKEY as it is input only
		// We ignore RETURNBEFOREIMAGE as it is input only
		// We ignore FORMATOUTPUT as it is input only
		// We ignore DEFAULTCHARGES as it is input only
		// We ignore SUPERVISORUSID as it is input only
		// We ignore SUPERVISORCOMMENT as it is input only
		// These should be reset after each attemp
		// We ignore APPLYUSINGEXTERNALINPUT as it is input only
		// We ignore APPLYUSINGRECOVERY as it is input only
		// These should be reset after each attempt
		CCSTARTTRANSACTION = "N";
		CCENDTRANSACTION = "N";
		EXTERNALCOMMIT = "N";
		// We ignore CHECKEXTRADATA as it is input only
		// We ignore JOURNALENQUIRY as it is input only
		// We ignore INPUTBRANCH as it is input only
		// Determine our state
		try
		{
			status = Integer.parseInt(s.substring(100, 104));
		}
		catch (Exception e)
		{
			status = EQTransaction.STATUS_FAILED;
		}
	}
	/*******************************************************************************************************************************
	 * Get incremental controls.
	 * <P>
	 * 
	 * @return a string containing the incremental control data
	 */
	private String getIncrementalControls()
	{
		char incrementalControls[] = new char[EQFUNCTION_INCREMENTALCONTROL_SIZE];
		// initialse with blanks
		EQFUNCTION_BLANK_INCREMENTALCONTROL.getChars(0, EQFUNCTION_INCREMENTALCONTROL_SIZE, incrementalControls, 0);
		// copy over any control information
		if (bIncrementalMode)
		{
			incrementalControls[0] = 'Y';
		}
		else
		{
			incrementalControls[0] = 'N';
		}
		ENTRYEVENT.getChars(0, ENTRYEVENT.length(), incrementalControls, 4);
		FORMAT.getChars(0, FORMAT.length(), incrementalControls, 6);
		PVROOT.getChars(0, PVROOT.length(), incrementalControls, 18);
		// PVDECODE.getChars(0, PVDECODE.length(), incrementalControls, 23);
		if (promptFieldID != null)
		{
			promptFieldID.getChars(0, promptFieldID.length(), incrementalControls, 24);
		}
		promptSelected.getChars(0, promptSelected.length(), incrementalControls, 39);
		// SERVERDATE.getChars(0, SERVERDATE.length(), incrementalControls, 62);
		// SERVERTIME.getChars(0, SERVERTIME.length(), incrementalControls, 69);
		metaData.getSourceApplication().getChars(0, metaData.getSourceApplication().length(), incrementalControls, 88);
		metaData.getSourceVersion().getChars(0, metaData.getSourceVersion().length(), incrementalControls, 94);
		metaData.getSourceRelease().getChars(0, metaData.getSourceRelease().length(), incrementalControls, 102);
		metaData.getSourceTask().getChars(0, metaData.getSourceTask().length(), incrementalControls, 109);
		if (LOG.isDebugEnabled())
		{
			LOG.debug("####### Entry Event - " + ENTRYEVENT);
		}
		return EQCommonConstants.rightTrimmedString(incrementalControls, 0, EQFUNCTION_INCREMENTALCONTROL_SIZE);
	}
	/*******************************************************************************************************************************
	 * Set the EQFunction's incremental control data.
	 */
	private void setIncrementalControls(String s)
	{
		int len = s.length();
		// We ignore INCREMENTALMODE as it is input only
		ENTRYEVENT = "";
		if (len > 6)
		{
			FORMAT = s.substring(6, 16).trim();
		}
		if (len > 18)
		{
			PVROOT = s.substring(18, 23).trim();
		}
		// We ignore promptSelected as it is input only
		if (len > 62)
		{
			SERVERDATE = s.substring(62, 69);
		}
		if (len > 69)
		{
			SERVERTIME = s.substring(69, 81);
		}
		// We ignore sourceApplication as it is input only
		// We ignore sourceVersion as it is input only
		// We ignore sourceRelease as it is input only
		// We ignore sourceTask as it is input only
	}
	/*******************************************************************************************************************************
	 * Get before image.
	 * <P>
	 * 
	 * @return a string containing the before image
	 */
	private byte[] getBeforeImage()
	{
		return BEFOREIMAGE;
	}
	/*******************************************************************************************************************************
	 * Set the EQFunction's before image, i.e before image returned from call.
	 */
	private void setBeforeImage(byte[] b)
	{
		// we only do this when signaled to
		if (USEBEFOREIMAGE.equals("Y"))
		{
			BEFOREIMAGE = b;
		}
	}
	/*******************************************************************************************************************************
	 * Setup the GY key for this EQFunction from a string.
	 */
	private void setEquationJournalKey(String s)
	{
		int nImageLength = s.length();
		if (nImageLength > 0)
		{
			GYWSID = s.substring(0, 4);
			GYDIM = s.substring(4, 6);
			GYTIM = s.substring(6, 12);
			GYSEQ = s.substring(12, 19);
			GYFRO = s.substring(19, 23);
			GYJTT = s.substring(23, 24);
			GYCDT = s.substring(24, 31);
			GYCLTM = s.substring(31, 37);
			GYCSEQ = s.substring(37, 44);
			GYJOB = s.substring(44, 50);
			// if (nImageLength < 51) {
			// GYREC = "";
			// } else {
			// GYREC = s.substring(50, 51);
			// }
		}
	}
	/*******************************************************************************************************************************
	 * Setup the Equation Event Journal key for this EQFunction from a string.
	 */
	private void setEQEventKey(String s)
	{
		AAHEVNK = s;
	}
	/*******************************************************************************************************************************
	 * Setup the Messages for this EQFunction.
	 */
	private void setMessages(String messages)
	{
		MSGS.addAll(getMessagesFromImage(messages, true));
	}
	/*******************************************************************************************************************************
	 * Setup the Override Messages for this EQFunction.
	 */
	private void setOverrides(String overrides)
	{
		OVERRIDES.addAll(getMessagesFromImage(overrides, false));
	}
	/*******************************************************************************************************************************
	 * Setup the CRM Messages for this EQFunction
	 */
	private void setCRMMessages(String crmMessages)
	{
		if (AUTOOVERRIDE.equals("N"))
		{
			MSGS.addAll(getCRMMessagesFromImage(crmMessages));
		}
		else
		{
			OVERRIDES.addAll(getCRMMessagesFromImage(crmMessages));
		}
	}
	/*******************************************************************************************************************************
	 * Helper function to parse Message image into ArrayList of EQFieldMessage objects.
	 */
	private ArrayList<EQMessage> getMessagesFromImage(String image, boolean addMessagesToFields)
	{
		int numMsgs = image.length() / 172;
		ArrayList<EQMessage> msgv = new ArrayList<EQMessage>(numMsgs);
		char[] msgBuf = image.toCharArray();
		// StringBuffer msgBuf;
		int nImagePointerStart = 0;
		for (int i = 0; (i < 20) && (i < numMsgs); i++)
		{
			nImagePointerStart = 172 * i;
			EQFieldMessage msg = new EQFieldMessage(msgBuf, nImagePointerStart);
			if (addMessagesToFields)
			{
				// add this message to the field if it has a related field.
				EQField eqField = null;
				if (msg.fieldName.length() > 0)
				{
					// field is in the fixed format hash map
					if (msg.rowNumber == -1)
					{
						if ((eqField = hmFields.get(msg.fieldName)) != null)
						{
							eqField.addMessage(msg);
						}
						else
						{
							LOG.error("field missing for message " + msg.fieldName + "-" + msg.formattedMessage);
						}
					}
					else
					{
						// field is in the list hash map
						if ((eqField = (EQField) getList().getRow(msg.rowNumber - 1).get(msg.fieldName)) != null)
						{
							eqField.addMessage(msg);
						}
						else
						{
							LOG.error("field missing for message " + msg.fieldName + "-" + msg.formattedMessage);
						}
					}
				}
			}
			// Add this message to the list of messages
			msgv.add(msg);
		}
		return msgv;
	}
	/*******************************************************************************************************************************
	 * Helper function to parse Message image into ArrayList of EQCRMMessage objects.
	 */
	private ArrayList<EQMessage> getCRMMessagesFromImage(String image)
	{
		int numMsgs = image.length() / 236;
		numMsgs += image.length() % 236 > 0 ? 1 : 0;
		ArrayList<EQMessage> msgv = new ArrayList<EQMessage>(numMsgs);
		char[] msgBuf = image.toCharArray();
		// StringBuffer msgBuf;
		int nImagePointerStart = 0;
		for (int i = 0; (i < 42) && (i < numMsgs); i++)
		{
			nImagePointerStart = 236 * i;
			EQCRMMessage msg = new EQCRMMessage(msgBuf, nImagePointerStart);
			// Add this message to the list of messages
			msgv.add(msg);
		}
		return msgv;
	}
	/*******************************************************************************************************************************
	 * @return the list of currently visible function keys for the object
	 *         <P>
	 *         Note: This list is not fixed, it can change at run-time Valid in Incremental mode only
	 */
	protected ArrayList<String> getFunctionKeys()
	{
		return FKEY;
	}
	/*******************************************************************************************************************************
	 * Setup the Function Keys for this EQFunction.
	 */
	private void setKeys(String fkeys)
	{
		int numFkeys = fkeys.length() / 25;
		if (fkeys.length() % 25 > 0)
		{
			numFkeys++;
		}
		ArrayList<String> fkeyv = new ArrayList<String>(numFkeys);
		int nImagePointerEnd = 0, nImageLength = fkeys.length();
		for (int i = 0; (i < numFkeys); i++)
		{
			nImagePointerEnd = (25 * i) + 25;
			if (nImagePointerEnd > nImageLength)
			{
				nImagePointerEnd = nImageLength;
			}
			String fkey = fkeys.substring((25 * i), nImagePointerEnd).trim();
			// function key must start with "F", selection info is excluded here
			if (fkey.substring(0, 1).equals("F"))
			{
				// Add this function key to the list of function keys
				fkeyv.add(fkey);
			}
		}
		FKEY = fkeyv;
	}
	/*******************************************************************************************************************************
	 * Set all the field visibility for this EQFunction.
	 */
	private void setFieldVisibility(String fieldState)
	{
		int nFieldPos = 0;
		int nFieldStateLength = fieldState.length();
		EQFieldDefinition eqFieldDefinition = null;
		EQField eqField = null;
		for (int nCount = 0; (nCount < metaData.nFixedInputFields); nCount++)
		{
			eqFieldDefinition = metaData.arrFixedInputFieldDefinitions.get(nCount);
			eqField = (hmFields.get(eqFieldDefinition.fieldName));
			if (nFieldPos < nFieldStateLength)
			{
				eqField.setVisibility(fieldState.charAt(nFieldPos));
			}
			else
			{
				eqField.setVisibility(' ');
			}
			nFieldPos++;
		}
		for (int nCount = 0; (nCount < metaData.nFixedOutputFields); nCount++)
		{
			eqFieldDefinition = metaData.arrFixedOutputFieldDefinitions.get(nCount);
			eqField = (hmFields.get(eqFieldDefinition.fieldName));
			if (nFieldPos < nFieldStateLength)
			{
				eqField.setVisibility(fieldState.charAt(nFieldPos));
			}
			else
			{
				eqField.setVisibility(' ');
			}
			nFieldPos++;
		}
	}
	/*******************************************************************************************************************************
	 * Setup the field and text state for this EQFunction.
	 */
	private void setFieldState(String fieldState)
	{
		if (fieldState.length() > 0)
		{
			if (bIncrementalMode == false)
			{
				return;
			}
			// determine field visiblity info first
			setFieldVisibility(fieldState);
			// now determine text field visiblity
			int nFunctionFields = metaData.nFixedInputFields + metaData.nFixedOutputFields;
			int visibleElementsCnt = (fieldState.length() - nFunctionFields) / 4;
			ArrayList<String> txtv = new ArrayList<String>(visibleElementsCnt);
			int nImagePointerEnd = 0, nImageLength = fieldState.length();
			for (int i = 0; i < visibleElementsCnt; i++)
			{
				nImagePointerEnd = nFunctionFields + (4 * i) + 4;
				if (nImagePointerEnd > nImageLength)
				{
					nImagePointerEnd = nImageLength;
				}
				int offset = nFunctionFields + (4 * i);
				String txtfield = metaData.getRootID() + fieldState.substring(offset, offset + 4).trim();
				txtv.add(txtfield);
			}
			TXTFLD = txtv;
		}
	}
	/*******************************************************************************************************************************
	 * Get the server date returned on the last call.
	 * <P>
	 * 
	 * @return the server date in the format yyyy-mm-dd
	 */
	protected String getLastAPICallServerDate()
	{
		return EQCommonConstants.getIsoDate(SERVERDATE);
	}
	/*******************************************************************************************************************************
	 * Get the server time returned on the last call.
	 * <P>
	 * 
	 * @return the server time in the format hh.mm.ss.mmmmmm
	 */
	protected String getLastAPICallServerTime()
	{
		return EQCommonConstants.getIsoTime(SERVERTIME);
	}
	/*******************************************************************************************************************************
	 * Determine whether the Function is operating in incremental mode.
	 * <P>
	 * In incremental mode Equation processes its business logic screen by screen and returns visibility information for fields and
	 * text on those screens.
	 * <P>
	 * 
	 * @return true if the function makes calls in incremental mode
	 */
	protected boolean isIncrementalMode()
	{
		return bIncrementalMode;
	}
	/*******************************************************************************************************************************
	 * Make the call to the Equation SQL Stored Procedure. For list, loop as many times as needed to get the number of rows
	 * requested.
	 */
	protected int callAPILoop(EQSession session) throws EQException
	{
		status = EQObject.STATUS_FAILED;
		callAPI(session);
		if (PVROOT != null && prompt != null)
		{
			while (((status == EQObject.STATUS_PROMPTED || status == EQObject.STATUS_INCOMPLETE) && prompt.list.BEGIN == 'M'
							&& !prompt.list.isPageComplete() && !prompt.list.isComplete()))
			{
				callAPI(session);
			}
		}
		else
		{
			if (list != null)
			{
				while (list.BEGIN == 'M' && status == EQObject.STATUS_INCOMPLETE && !list.isPageComplete() && !list.isComplete())
				{
					callAPI(session);
				}
			}
		}
		return status;
	}
	/*******************************************************************************************************************************
	 * Make the call to the Equation SQL Stored Procedure. Only one call is made to the iSeries.
	 */
	private int callAPI(EQSession session) throws EQException
	{
		if (AUDITUSERID.length() == 0)
		{
			throw new EQException("function has no AuditUserID set");
		}
		if (!session.isLoggedOn())
		{
			throw new EQException("the connection is not ready");
		}
		CallableStatement cs = null;
		try
		{
			cs = session.getConnection().prepareCall(getStoredProcedureCallString());
			// Set up the callable statement from the Function
			try
			{
				populateCallableStatement(cs);
			}
			catch (SQLException e)
			{
				LOG.error("EQEnquiry::CallAPI:populateCallableStatement: SQL error:" + Toolbox.getExceptionMessage(e)
								+ e.getSQLState() + e.getErrorCode());
				status = STATUS_SQL_ERROR;
				return status;
			}
			if (prompt != null)
			{
				// pass the prompt details through
				cs.setString(EQFunction.PARAM_EQFUNCTION_PROMPT_CONTROLS, prompt.getListControls());
				cs.setString(EQFunction.PARAM_EQFUNCTION_PROMPT_FILTER, prompt.getFieldImage());
				cs.setString(EQFunction.PARAM_EQFUNCTION_PROMPT_DSROWS[0], prompt.getSelection());
				for (int i = 1; i < EQList.nListParameters; i++)
				{
					cs.setString(EQFunction.PARAM_EQFUNCTION_PROMPT_DSROWS[i], "");
				}
			}
			else
			{
				if (promptFieldID != null && promptFieldID.length() > 0)
				{
					// this is an event driven prompt with the field id specified, so do first retrieval
					cs.setString(EQFunction.PARAM_EQFUNCTION_PROMPT_CONTROLS, EQPromptImpl.getControlsForFirstRetrieval());
					cs.setString(EQFunction.PARAM_EQFUNCTION_PROMPT_FILTER, "");
					for (int i = 0; i < EQList.nListParameters; i++)
					{
						cs.setString(EQFunction.PARAM_EQFUNCTION_PROMPT_DSROWS[i], "");
					}
				}
				else
				{
					// otherwise use the static method for defaults
					cs.setString(EQFunction.PARAM_EQFUNCTION_PROMPT_CONTROLS, EQPromptImpl.getDefaultControls());
					cs.setString(EQFunction.PARAM_EQFUNCTION_PROMPT_FILTER, "");
					for (int i = 0; i < EQList.nListParameters; i++)
					{
						cs.setString(EQFunction.PARAM_EQFUNCTION_PROMPT_DSROWS[i], "");
					}
				}
			}
			// Register output parameters
			try
			{
				registerOutParameters(cs);
			}
			catch (SQLException e)
			{
				LOG.error("EQEnquiry::CallAPI:registerOutParameters: SQL error:" + Toolbox.getExceptionMessage(e) + e.getSQLState()
								+ e.getErrorCode());
				status = STATUS_SQL_ERROR;
				return status;
			}
			// Execute the call statement
			try
			{
				cs.setQueryTimeout(session.getProperties().getTimeOut());
				if (LOG.isDebugEnabled())
				{
					LOG.debug("callAPI,------> before call");
				}
				cs.execute();
				if (LOG.isDebugEnabled())
				{
					LOG.debug("callAPI,<------ after  call");
				}
			}
			catch (SQLException e)
			{
				LOG.error("EQEnquiry::CallAPI:cs.execute: SQL error:" + Toolbox.getExceptionMessage(e) + e.getSQLState()
								+ e.getErrorCode());
				status = STATUS_SQL_ERROR;
				return status;
			}
			// Pass the results from the call back to the function
			if (setFieldsFromCallableStatement(cs) < STATUS_SUCCESS)
			{
				status = STATUS_FAILED;
				return status;
			}
			if (PVROOT != null)
			{
				// get the name of the prompt called
				String promptName = PVROOT;
				// check that the call resulted in a prompt call, if not remove the prompt
				if (promptName.length() == 0)
				{
					prompt = null;
				}
				else
				{
					if (prompt == null)
					{
						setPromptClass(session, promptName);
					}
					// retrieve prompt information and store it in prompt
					prompt.list.setControls(cs.getString(EQFunction.PARAM_EQFUNCTION_PROMPT_CONTROLS));
					prompt.setFieldsFromImage(cs.getString(EQFunction.PARAM_EQFUNCTION_PROMPT_FILTER));
					String[] pvList = new String[EQList.nListParameters];
					for (int i = 0; i < EQList.nListParameters; i++)
					{
						pvList[i] = cs.getString(EQFunction.PARAM_EQFUNCTION_PROMPT_DSROWS[i]);
					}
					prompt.getList().setList(pvList);
					prompt.modified = false;
					if (prompt.list != null)
					{
						prompt.list.modified = false;
						// }
					}
				}
			}
			// Determine display name
			// StringBuffer buff = new StringBuffer(20);
			// if (prompt != null) {
			// // display is set for a prompt
			// buff.append(EQPromptImpl.DEFAULT_PROMPT_VIEW);
			// displayName = buff.toString();
			// }
			// we're done with this event
			ENTRYEVENT = "  ";
		}
		catch (SQLException e)
		{
			LOG.error("EQEnquiry::CallAPI: SQL error:" + Toolbox.getExceptionMessage(e) + e.getSQLState() + e.getErrorCode());
			status = STATUS_SQL_ERROR;
			return status;
		}
		finally
		{
			try
			{
				if (cs != null)
				{
					cs.close();
				}
			}
			catch (Exception e)
			{
				LOG.error(e);
			}
		}
		return status;
	}
	/*******************************************************************************************************************************
	 * Helper method to setup a prompt class.
	 */
	private void setPromptClass(EQSession session, String promptIdentifier)
	{
		// allocate the prompt to process the output
		if (prompt == null)
		{
			try
			{
				// Construct an appropriate prompt
				prompt = (EQPromptImpl) session.createEQObject(promptIdentifier);
			}
			catch (Exception e)
			{
				LOG.error("EQUserInterfaceHelper:setPromptClass failed: " + e);
			}
		}
	}
	@SuppressWarnings("unchecked")
	public void readObject(java.io.ObjectInputStream s, EQObjectMetaData newMap) throws EQException,
					java.lang.ClassNotFoundException, java.io.IOException
	{
		// create the object with the old mapping information
		readObject(s);
		if (newMap != null)
		{
			// Add new fields and adjust existing fields field definitions
			int nFunctionFields = newMap.nFixedInputFields + newMap.nFixedOutputFields;
			EQFieldDefinition eqFieldDefinition = null;
			EQField eqField = null;
			if (nFunctionFields > hmFields.size())
			{
				int nCount = 0;
				for (nCount = newMap.nFixedInputFields; --nCount > 0;)
				{
					eqFieldDefinition = newMap.arrFixedInputFieldDefinitions.get(nCount);
					if (!hmFields.containsKey(eqFieldDefinition.fieldName))
					{
						eqField = new EQField(eqFieldDefinition);
						hmFields.put(eqFieldDefinition.fieldName, eqField);
					}
					else
					{
						eqField = (hmFields.get(eqFieldDefinition.fieldName));
						eqField.definition = eqFieldDefinition;
					}
				}
				for (nCount = newMap.nFixedOutputFields; --nCount > 0;)
				{
					eqFieldDefinition = newMap.arrFixedOutputFieldDefinitions.get(nCount);
					if (!hmFields.containsKey(eqFieldDefinition.fieldName))
					{
						eqField = new EQField(eqFieldDefinition);
						hmFields.put(eqFieldDefinition.fieldName, eqField);
					}
					else
					{
						eqField = (hmFields.get(eqFieldDefinition.fieldName));
						eqField.definition = eqFieldDefinition;
					}
				}
			}
			// List Manipulation
			if (list != null)
			{
				// No of Rows
				int nListRows = list.getRows().size();
				for (int nListFieldCount = 0; nListFieldCount < newMap.nListInputFields; nListFieldCount++)
				{
					// Checking the First Row would Suffice
					eqFieldDefinition = newMap.arrListInputFieldDefinitions.get(nListFieldCount);
					if (!((HashMap) list.rows.get(0)).containsKey(eqFieldDefinition.fieldName))
					{
						// Field does not exist in the List
						for (int nC = 0; nC < nListRows; nC++)
						{
							eqField = new EQField(eqFieldDefinition);
							((HashMap) list.rows.get(nC)).put(eqFieldDefinition.fieldName, eqField);
						}
					}
					else
					{
						// Field exists in the List so update the definition
						for (int nC = 0; nC < nListRows; nC++)
						{
							eqField = (EQField) (((HashMap) list.rows.get(nC)).get(eqFieldDefinition.fieldName));
							eqField.definition = eqFieldDefinition;
						}
					}
				}
				for (int nListFieldCount = 0; nListFieldCount < newMap.nListOutputFields; nListFieldCount++)
				{
					// Checking the First Row would Suffice
					eqFieldDefinition = newMap.arrListOutputFieldDefinitions.get(nListFieldCount);
					if (!((HashMap) list.rows.get(0)).containsKey(eqFieldDefinition.fieldName))
					{
						// Field does not exist in the List
						for (int nC = 0; nC < nListRows; nC++)
						{
							eqField = new EQField(eqFieldDefinition);
							((HashMap) list.rows.get(nC)).put(eqFieldDefinition.fieldName, eqField);
						}
					}
					else
					{
						// Field exists in the List so update the definition
						for (int nC = 0; nC < nListRows; nC++)
						{
							eqField = (EQField) (((HashMap) list.rows.get(nC)).get(eqFieldDefinition.fieldName));
							eqField.definition = eqFieldDefinition;
						}
					}
				}
			}
		}
		else
		{
			throw new EQException("Warning:  API mapping information may not be current.");
		}
	}
	private void readObject(java.io.ObjectInputStream s) throws java.lang.ClassNotFoundException, java.io.IOException
	{
		// First the defaults
		s.defaultReadObject();
	}
	/*******************************************************************************************************************************
	 * Helper method to reset prompting in this object.
	 */
	protected void resetPrompt()
	{
		// if(currentDisplayName.trim().length() != 0){
		// displayName = currentDisplayName;
		// }else{
		// displayName = EQFunction.DEFAULT_FUNCTION_VIEW;
		// }
		PVROOT = "";
		promptFieldID = "";
		promptSelected = "N";
		prompt = null;
	}
	/*******************************************************************************************************************************
	 * Get supervisor password in an encrypted form.
	 * <P>
	 * 
	 * @return a byte array containing the encrypted supervisor password data
	 */
	private byte[] getSupervisorPasswordEncrypted()
	{
		if (SUPERVISORPASSWORD != null)
		{
			return SUPERVISORPASSWORD;
		}
		else
		{
			return EQFUNCTION_BLANK_SUPERVISORPWD;
		}
	}
}
