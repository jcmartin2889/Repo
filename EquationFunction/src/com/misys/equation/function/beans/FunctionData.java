package com.misys.equation.function.beans;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.ibm.as400.access.BidiStringType;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationDataStructure;
import com.misys.equation.common.access.EquationDataStructureData;
import com.misys.equation.common.access.EquationFieldDefinition;
import com.misys.equation.common.access.EquationPVFieldMetaData;
import com.misys.equation.common.access.EquationPVMetaData;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.datastructure.EqDS_DSJOBE;
import com.misys.equation.common.datastructure.EqDS_DSSYSE;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.journal.JournalFile;
import com.misys.equation.function.journal.JournalRecord;
import com.misys.equation.function.runtime.FunctionHandlerData;
import com.misys.equation.function.runtime.FunctionMessages;
import com.misys.equation.function.tools.CheckSumCalculator;
import com.misys.equation.function.tools.ContextString;
import com.misys.equation.function.tools.ExtensionData;
import com.misys.equation.function.tools.FieldFilterLocator;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;
import com.misys.equation.function.tools.HTMLToolbox;
import com.misys.equation.function.tools.XSDStructureLink;
import com.misys.equation.function.useraccess.UserAccessFields;

/**
 * This class represents the run time data of a function
 */
public class FunctionData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionData.java 17334 2013-09-23 01:05:40Z williae1 $";

	// Log
	private static final EquationLogger LOG = new EquationLogger(FunctionData.class);

	// Pre-defined fields
	/** Next request */
	public static final String FLD_NEXT_REQUEST = "GZNEXTREQUEST";
	/** enable (Y) or disable (N) EFC */
	public static final String FLD_EFC = "GZEFC";
	/** default supervisor id when warning is issued */
	public static final String FLD_SUPERID = "GZUSUPERID";
	/** key screen (Y) or non-key screen (N) */
	public static final String FLD_KEYLOADED = "GZKEYLOADED";
	/** function mode A-Add, M-Maintain, D-Delete, E-Enquiry */
	public static final String FLD_FCT = "GZFCT";
	/** selection option prefix for list */
	public static final String FLD_SEL = "GZSEL_";
	/** break by grouping */
	public static final String FLD_BREAKBY = "GZBREAKBY";
	/** suppress maker checker */
	public static final String FLD_SUPP_MKR_CHKR = "GZSMCK";
	/** maker checker status */
	public static final String FLD_STAT_MKR_CHKR = "GZ4STS";

	/** clear all messages */
	public static final int CLEAR_MSG_ALL = 1;
	/** clear only fixed details */
	public static final int CLEAR_MSG_FIX = 2;
	/** clear only repeating details */
	public static final int CLEAR_MSG_RPT = 3;

	// details in user language format
	private String decimalSeparator;
	private String integerSeparator;
	private String yesValue;
	private String noValue;
	private String dateInputFormat;
	private String openDateAbbr;
	private String openDateFull;

	// List of field data
	private String optionId;
	private Hashtable<String, FieldData> fieldDatas;
	private Hashtable<String, String> headerResponseData;
	private final Hashtable<String, String> promptFieldDatas = new Hashtable<String, String>();
	private LinkedHashMap<String, FieldDefinition> fieldDefinitions;

	private List<RepeatingDataManager> repeatingDataManagers = new ArrayList<RepeatingDataManager>();

	private FunctionControlData functionControlData;
	private UserAccessFields userAccessFields;

	private ExtensionData inputExtensionData = new ExtensionData();
	private ExtensionData outputExtensionData = new ExtensionData();

	private String checksum = null;

	/**
	 * Construct a new empty FunctionData instance
	 */
	public FunctionData()
	{
		init();
	}

	/**
	 * Construct a new FunctionData instance from a function
	 * 
	 * @param function
	 *            - the current function
	 * @param fhd
	 *            - the Function Handler data
	 * @throws EQException
	 */
	public FunctionData(Function function, FunctionHandlerData fhd) throws EQException
	{
		if (fhd != null)
		{
			init(fhd.getEquationUser());
		}
		else
		{
			init();
		}
		userAccessFields = new UserAccessFields(fhd);
		loadAllFieldsFromFunction(function, fhd);
	}

	/**
	 * Initialise details
	 */
	private void init()
	{
		this.decimalSeparator = "";
		this.integerSeparator = "";
		this.yesValue = "";
		this.noValue = "";
		this.dateInputFormat = "";
		this.openDateAbbr = "";
		this.openDateFull = "";
		this.fieldDatas = new Hashtable<String, FieldData>();
		this.headerResponseData = new Hashtable<String, String>();
		this.fieldDefinitions = new LinkedHashMap<String, FieldDefinition>();
		this.functionControlData = new FunctionControlData();
	}

	/**
	 * Initialise default properties of this class
	 * 
	 * @param eqUser
	 */
	private void init(EquationUser eqUser)
	{
		this.decimalSeparator = eqUser.getDsjobctle().getFieldValue(EqDS_DSJOBE.DECST);
		this.integerSeparator = eqUser.getDsjobctle().getFieldValue(EqDS_DSJOBE.INTST);
		this.yesValue = eqUser.getDsjobctle().getFieldValue(EqDS_DSJOBE.YAB);
		this.noValue = eqUser.getDsjobctle().getFieldValue(EqDS_DSJOBE.NAB);
		this.openDateFull = eqUser.getDsjobctle().getFieldValue(EqDS_DSJOBE.OPN);
		this.openDateAbbr = openDateFull.substring(0, 1);
		this.fieldDatas = new Hashtable<String, FieldData>();
		this.headerResponseData = new Hashtable<String, String>();
		this.fieldDefinitions = new LinkedHashMap<String, FieldDefinition>();
		this.functionControlData = new FunctionControlData();

		// date format
		String dateFormat = eqUser.getDsjobctle().getFieldValue(EqDS_DSJOBE.INPD);
		if (dateFormat.equals("D"))
		{
			this.dateInputFormat = EqDataType.DATE_DMY;
		}
		else if (dateFormat.equals("M"))
		{
			this.dateInputFormat = EqDataType.DATE_MDY;
		}
		else if (dateFormat.equals("Y"))
		{
			this.dateInputFormat = EqDataType.DATE_YMD;
		}
		else
		{
			this.dateInputFormat = eqUser.getEquationUnit().getSystemOption(EqDS_DSSYSE.DATFM).substring(0, 1);
		}
	}

	/**
	 * Add pre-defined fields
	 * 
	 * @param function
	 *            The current Function definition
	 */
	private void initFields(Function function)
	{
		EquationFieldDefinition fd;

		// next request
		fd = new EquationFieldDefinition(FLD_NEXT_REQUEST, 0, 0, 3, 0, "", EqDataType.TYPE_CHAR);
		insertFieldData(FLD_NEXT_REQUEST, fd, "", false);

		// apply efc?
		fd = new EquationFieldDefinition(FLD_EFC, 0, 0, 1, 0, "", EqDataType.TYPE_CHAR);
		insertFieldData(FLD_EFC, fd, EqDataType.YES, false);

		// Suppress Maker / Checker
		fd = new EquationFieldDefinition(FLD_SUPP_MKR_CHKR, 0, 0, 1, 0, "", EqDataType.TYPE_CHAR);
		insertFieldData(FLD_SUPP_MKR_CHKR, fd, "", false);

		// Maker / Checker Status
		fd = new EquationFieldDefinition(FLD_STAT_MKR_CHKR, 0, 0, 1, 0, "", EqDataType.TYPE_CHAR);
		insertFieldData(FLD_STAT_MKR_CHKR, fd, "", false);

		// supervisor id?
		fd = new EquationFieldDefinition(FLD_SUPERID, 0, 0, 4, 0, "", EqDataType.TYPE_CHAR);
		insertFieldData(FLD_SUPERID, fd, "", false);

		// function mode
		fd = new EquationFieldDefinition(FLD_FCT, 0, 0, 1, 0, "", EqDataType.TYPE_CHAR);
		insertFieldData(FLD_FCT, fd, IEquationStandardObject.FCT_ADD, false);

		// key loaded?
		fd = new EquationFieldDefinition(FLD_KEYLOADED, 0, 0, 1, 0, "", EqDataType.TYPE_CHAR);
		insertFieldData(FLD_KEYLOADED, fd, "", false);

		// initialise fields
		if (function.getInputFieldSets().get(0).containsKeyFields())
		{
			chgFieldDbValue(FLD_KEYLOADED, EqDataType.YES);
		}
		else
		{
			chgFieldDbValue(FLD_KEYLOADED, EqDataType.NO);
		}

		// break by grouping - this contains the current grouping in HTMLToolbox
		fd = new EquationFieldDefinition(FLD_BREAKBY, 0, 0, 35, 0, "", EqDataType.TYPE_CHAR);
		insertFieldData(FLD_BREAKBY, fd, "", false);
	}

	/**
	 * Add pre-defined repeating fields for the given repeating group
	 * 
	 * @param repeatingDataManager
	 *            - the Repeating Data Manager
	 */
	private void initRepeatingFields(RepeatingDataManager repeatingDataManager)
	{
		EquationFieldDefinition fd;

		String selectionOptionId = repeatingDataManager.rtvSelectionOptionId();
		fd = new EquationFieldDefinition(selectionOptionId, 0, 0, 10, 0, "", EqDataType.TYPE_CHAR);
		insertRepeatingFieldData(repeatingDataManager, selectionOptionId, fd, "", false);
	}

	/**
	 * Create the list of field data from the given function
	 * 
	 * @param function
	 *            - the current function
	 * @param fhd
	 *            - the Function Handler data
	 * @throws EQException
	 */
	private void loadAllFieldsFromFunction(Function function, FunctionHandlerData fhd) throws EQException
	{
		// setup the option id
		optionId = function.getId();

		// clear previous field definitions
		fieldDefinitions.clear();

		// clear previous fields
		fieldDatas.clear();
		headerResponseData.clear();

		// default fields
		initFields(function);

		// create input fields
		for (InputFieldSet fieldSet : function.getInputFieldSets())
		{
			loadFieldsFromFieldSet(fieldSet, fhd);
		}

		// create work fields
		for (WorkField workField : function.getWorkFields())
		{
			FieldData fieldData = new FieldData(workField);
			if (fhd != null)
			{
				String initialValue = FunctionRuntimeToolbox.rtvInitialValue(workField, workField.getInitialValueType(), workField
								.getInitialValue(), fhd, this);
				fieldData.setInputValue(initialValue);
				fieldData.setDbValue(initialValue);
			}
			addFieldData(workField.getId(), fieldData);
		}

		// Sort out repeating data
		LinkedHashMap<String, LinkedHashMap<String, FieldDefinition>> repeatingFields = new LinkedHashMap<String, LinkedHashMap<String, FieldDefinition>>();
		for (Entry<String, FieldDefinition> entry : fieldDefinitions.entrySet())
		{
			if (entry.getValue().isRepeating())
			{
				// get the field data to determine the repeating group id
				RepeatingFieldData repeatData = (RepeatingFieldData) rtvFieldData(entry.getKey());
				String repeatingGroupId = repeatData.rtvRepeatingDataManager().getId();

				// retrieve the list of repeating fields per repeating group
				LinkedHashMap<String, FieldDefinition> listFieldDef = repeatingFields.get(repeatingGroupId);
				if (listFieldDef == null)
				{
					listFieldDef = new LinkedHashMap<String, FieldDefinition>();
					repeatingFields.put(repeatingGroupId, listFieldDef);
				}

				// add the field definition
				listFieldDef.put(entry.getKey(), entry.getValue());
			}
		}

		// Repeating data Managers will have been added in loadFieldsFromFieldSet
		for (RepeatingDataManager repeatingDataManager : getRepeatingDataManagers())
		{
			String repeatingGroupId = repeatingDataManager.getId();
			initRepeatingFields(repeatingDataManager);

			// get the list of field def for this repeating group
			LinkedHashMap<String, FieldDefinition> listFieldDef = repeatingFields.get(repeatingGroupId);

			// add it
			listFieldDef.put(repeatingDataManager.rtvSelectionOptionId(), fieldDefinitions.get(repeatingDataManager
							.rtvSelectionOptionId()));

			// intitialise the repeating data manager
			repeatingDataManager.initialise(repeatingFields.get(repeatingGroupId));
		}

		// set the control data
		functionControlData.setData(FunctionControlData.OPTION, optionId);
	}

	/**
	 * Load the list of fields of a field set
	 * 
	 * @param fieldSet
	 *            - the InputFieldSet
	 * @param fhd
	 *            - the Function Handler data
	 * @throws EQException
	 */
	private void loadFieldsFromFieldSet(InputFieldSet fieldSet, FunctionHandlerData fhd) throws EQException
	{
		for (InputField field : fieldSet.getInputFields())
		{
			FieldData fieldData = getFieldDataFromField(field);
			if (fhd != null)
			{
				String inputValue = FunctionRuntimeToolbox.rtvInitialValue(field, field.getInitialValueType(), field
								.getInitialValue(), fhd, this);

				if (field.getInitialValueAs().equals(InputField.VALUE_INPUT))
				{
					fieldData.setInputValue(inputValue);
					fieldData.setDbValue(cvtInputIntoDb(fieldData, inputValue));
					furtherSetup(fhd, field, fieldData);
				}
				else if (field.getInitialValueAs().equals(InputField.VALUE_DATABASE))
				{
					fieldData.setDbValue(inputValue);
					fieldData.setInputValue(cvtDbIntoInput(fieldData, inputValue));
					furtherSetup(fhd, field, fieldData);
				}
			}

			// add to the list
			addFieldData(field.getId(), fieldData);
		}
	}

	/**
	 * Creates and returns a new FieldData instance for an InputField
	 * 
	 * @param inputField
	 * @return The new FieldData instance
	 */
	private FieldData getFieldDataFromField(InputField inputField)
	{
		FieldData result;
		if (Field.isRepeating(inputField))
		{
			if (getRepeatingDataManager(inputField.getRepeatingGroup()) == null)
			{
				addRepeatingDataManager(new RepeatingDataManager(inputField.getRepeatingGroup()));
			}
			result = new RepeatingFieldData(inputField, getRepeatingDataManager(inputField.getRepeatingGroup()));
		}
		else
		{
			result = new FieldData(inputField);
		}

		result.setUpperCase(inputField.isUpperCase());
		return result;
	}

	/**
	 * Further setup
	 * 
	 * @param fhd
	 *            - the Function Handler data
	 * @param field
	 *            - the input field
	 * @param fieldData
	 *            - the field data
	 */
	private void furtherSetup(FunctionHandlerData fhd, InputField field, FieldData fieldData)
	{
		EquationUser eqUser = fhd.getEquationUser();
		int contextFieldType = field.getFieldContextType();
		if (contextFieldType == InputField.FLDCTXTYP_BRNM)
		{
			// user has no inter-branch authority, then default and lock the data
			if (!eqUser.isInterBranchAllowed())
			{
				fieldData.setDbValue(eqUser.getInputBranch());
				fieldData.setInputValue(eqUser.getInputBranch());
				fieldData.setLocked(true);
				fieldData.setDefaultLocked(true);
			}
		}
		else if (contextFieldType == InputField.FLDCTXTYP_BBN)
		{
			// user has not inter-branch, then default and lock the data
			if (!eqUser.isInterBranchAllowed())
			{
				fieldData.setDbValue(eqUser.getInputBranchNo());
				fieldData.setInputValue(eqUser.getInputBranchNo());
				fieldData.setLocked(true);
				fieldData.setDefaultLocked(true);
			}
		}
		else if (contextFieldType == InputField.FLDCTXTYP_ACN)
		{
			// user has not inter-branch, then default and lock the data
			if (!eqUser.isInterBranchAllowed())
			{
				fieldData.setDbValue(eqUser.getInputBranchNo());
				fieldData.setInputValue(eqUser.getInputBranchNo());
				fieldData.setLocked(true);
				fieldData.setDefaultLocked(true);
			}
		}
	}

	/**
	 * Return the option id of the function associated with this data
	 * 
	 * @return the option id of the function
	 */
	public String getOptionId()
	{
		return optionId;
	}

	/**
	 * Set the option id of the function associated with this data
	 * 
	 * @param optionId
	 *            - option id of the function
	 */
	public void setOptionId(String optionId)
	{
		this.optionId = optionId;
	}

	/**
	 * Set the field data
	 * 
	 * @param fieldDatas
	 *            - field datas
	 */
	public void setFieldDatas(Hashtable<String, FieldData> fieldDatas)
	{
		this.fieldDatas = fieldDatas;
	}

	/**
	 * Return the field data
	 * 
	 * @return the field data
	 */
	public Hashtable<String, FieldData> getFieldDatas()
	{
		return fieldDatas;
	}

	/**
	 * Add a field data
	 * 
	 * @param fieldName
	 *            The name of the field
	 * 
	 * @param fieldData
	 *            - a field data implementation
	 */
	public void addFieldData(String fieldName, FieldData fieldData)
	{
		fieldDatas.put(fieldName.toUpperCase(), fieldData);
		FieldDefinition fieldDefinition = new FieldDefinition(fieldData);
		fieldDefinitions.put(fieldDefinition.getFieldName(), fieldDefinition);
	}
	/**
	 * Add a field data to the header response data collection
	 * 
	 * @param fieldName
	 *            The name of the field
	 * 
	 * @param fieldData
	 *            - a String of field data
	 */
	public void putHeaderResponseData(String fieldName, String fieldData)
	{
		headerResponseData.put(fieldName.toUpperCase(), fieldData);
	}
	/**
	 * Return the decimal separator
	 * 
	 * @return the decimal separator
	 */
	public String getDecimalSeparator()
	{
		return decimalSeparator;
	}

	/**
	 * Set the decimal separator
	 * 
	 * @param decimalSeparator
	 *            the decimal separator
	 */
	public void setDecimalSeparator(String decimalSeparator)
	{
		this.decimalSeparator = decimalSeparator;
	}

	/**
	 * Return the integer separator
	 * 
	 * @return the integer separator
	 */
	public String getIntegerSeparator()
	{
		return integerSeparator;
	}

	/**
	 * Set the integer separator
	 * 
	 * @param integerSeparator
	 *            the integer separator
	 */
	public void setIntegerSeparator(String integerSeparator)
	{
		this.integerSeparator = integerSeparator;
	}

	/**
	 * Return the yes character value
	 * 
	 * @return the yes character value
	 */
	public String getYesValue()
	{
		return yesValue;
	}

	/**
	 * Set the yes character value
	 * 
	 * @param yesValue
	 *            the yes character value
	 */
	public void setYesValue(String yesValue)
	{
		this.yesValue = yesValue;
	}

	/**
	 * Return the no character value
	 * 
	 * @return the no character value
	 */
	public String getNoValue()
	{
		return noValue;
	}

	/**
	 * Set the no character value
	 * 
	 * @param noValue
	 *            the no character value
	 */
	public void setNoValue(String noValue)
	{
		this.noValue = noValue;
	}

	/**
	 * Return the date format
	 * 
	 * @return the date format
	 */
	public String getDateInputFormat()
	{
		return dateInputFormat;
	}

	/**
	 * Set the date format
	 * 
	 * @param dateInputFormat
	 *            the date format
	 */
	public void setDateInputFormat(String dateInputFormat)
	{
		this.dateInputFormat = dateInputFormat;
	}

	/**
	 * Return the open data abbreviation
	 * 
	 * @return the open data abbreviation
	 */
	public String getOpenDateAbbr()
	{
		return openDateAbbr;
	}

	/**
	 * Set the open date abbreviation
	 * 
	 * @param openDateAbbr
	 *            - open date abbreviation
	 */
	public void setOpenDateAbbr(String openDateAbbr)
	{
		this.openDateAbbr = openDateAbbr;
	}

	/**
	 * Return the open date text
	 * 
	 * @return the open date text
	 */
	public String getOpenDateFull()
	{
		return openDateFull;
	}

	/**
	 * Set the open date text
	 * 
	 * @param openDateFull
	 *            - the open date text
	 */
	public void setOpenDateFull(String openDateFull)
	{
		this.openDateFull = openDateFull;
	}

	/**
	 * Return the control data
	 * 
	 * @return the control data
	 */
	public FunctionControlData getFunctionControlData()
	{
		return functionControlData;
	}

	/**
	 * Set the control data
	 * 
	 * @param functionControlData
	 *            - the control data
	 */
	public void setFunctionControlData(FunctionControlData functionControlData)
	{
		this.functionControlData = functionControlData;
	}

	/**
	 * Returns the field data of the specified field name
	 * 
	 * @param fieldName
	 *            - field name
	 * 
	 * @return the Field data for the field name
	 */
	public FieldData rtvFieldData(String fieldName)
	{
		FieldData fieldData = fieldDatas.get(fieldName.toUpperCase());

		if (fieldData instanceof RepeatingFieldData)
		{
			RepeatingFieldData repeatingFieldData = (RepeatingFieldData) fieldData;
			if (repeatingFieldData.rtvRepeatingDataManager().chkSelectedRow())
			{
				repeatingFieldData.copy(repeatingFieldData.rtvRepeatingDataManager().getFieldValues(fieldName));
			}
		}

		// return the field data
		return fieldData;
	}
	/**
	 * Returns the field data of the specified field name from the header response data collection
	 * 
	 * @param fieldName
	 *            - field name
	 * 
	 * @return the Field data for the field name
	 */
	public String rtvHeaderResponseData(String fieldName)
	{
		if (headerResponseData.get(fieldName.toUpperCase()) == null)
		{
			return "";
		}
		return headerResponseData.get(fieldName.toUpperCase());
	}
	/**
	 * Returns the field data of the specified field name from the header response data collection
	 * 
	 * @param fieldName
	 *            - field name
	 * 
	 * @return the Field data for the field name
	 */
	public int rtvHeaderResponseDataAsInt(String fieldName)
	{
		String value = headerResponseData.get(fieldName.toUpperCase());
		if (value == null || value.trim().equals(""))
		{
			return 0;
		}
		return new Integer(value).intValue();
	}
	/**
	 * Change the field values of the specified field name
	 * 
	 * @param fieldName
	 *            - field name
	 * @param sourceData
	 *            - input value
	 * 
	 * @return true if the field name is not existing
	 */
	public boolean chgFieldValues(String fieldName, FieldData sourceData)
	{
		// retrieve the field data
		FieldData fieldData = rtvFieldData(fieldName);

		// not found?
		if (fieldData == null)
		{
			return true;
		}

		// set the field
		fieldData.setDbValue(sourceData.getDbValue());
		fieldData.setInputValue(sourceData.getInputValue());
		fieldData.setOutputValue(sourceData.getOutputValue());

		// field found
		return false;
	}

	/**
	 * Change the field input value of the specified field name
	 * 
	 * @param fieldName
	 *            - field name
	 * @param inputValue
	 *            - input value
	 * 
	 * @return true if the field name is not existing
	 */
	public boolean chgFieldInputValue(String fieldName, String inputValue)
	{
		// retrieve the field data
		FieldData fieldData = rtvFieldData(fieldName);

		// not found?
		if (fieldData == null)
		{
			return true;
		}

		// set the field
		fieldData.setInputValue(inputValue);

		// field found
		return false;
	}

	/**
	 * Change the field database value of the specified field name
	 * 
	 * @param fieldName
	 *            - field name
	 * @param dbValue
	 *            = field value in database fieldSet
	 * 
	 * @return true if the field name is not existing
	 */
	public boolean chgFieldDbValue(String fieldName, String dbValue)
	{
		// retrieve the field data
		FieldData fieldData = rtvFieldData(fieldName);

		// not found?
		if (fieldData == null)
		{
			return true;
		}

		// set the database field value
		fieldData.setDbValue(dbValue);
		fieldData.setInputValue(cvtDbIntoInput(fieldData, fieldData.getDbValue()));

		// field found
		return false;
	}

	/**
	 * Change the field output value of the specified field name
	 * 
	 * @param fieldName
	 *            - field name
	 * @param outputValue
	 *            - output value
	 * 
	 * @return true if the field name is not existing
	 */
	public boolean chgFieldOutputValue(String fieldName, String outputValue)
	{
		// retrieve the field data
		FieldData fieldData = rtvFieldData(fieldName);

		// not found?
		if (fieldData == null)
		{
			return true;
		}

		// set the field
		fieldData.setOutputValue(outputValue);

		// field found
		return false;
	}

	/**
	 * Return the field input value of the specified field name
	 * 
	 * @param fieldName
	 *            - field name
	 * 
	 * @return the field input value
	 */
	public String rtvFieldInputValue(String fieldName)
	{
		// retrieve the field data
		FieldData fieldData = rtvFieldData(fieldName);

		// not found?
		if (fieldData == null)
		{
			return "";
		}

		// set the field
		return (fieldData.getInputValue());
	}

	/**
	 * Return the field database value of the specified field name
	 * 
	 * @param fieldName
	 *            - field name
	 * 
	 * @return the field database value
	 */
	public String rtvFieldDbValue(String fieldName)
	{
		// retrieve the field data
		FieldData fieldData = rtvFieldData(fieldName);

		// not found?
		if (fieldData == null)
		{
			return "";
		}

		// set the field
		return (fieldData.getDbValue());
	}

	/**
	 * Return the field database value of the specified field name
	 * 
	 * @param fieldName
	 *            - field name
	 * 
	 * @return the field database value
	 */
	public String rtvFieldOutputValue(String fieldName)
	{
		// retrieve the field data
		FieldData fieldData = rtvFieldData(fieldName);

		// not found?
		if (fieldData == null)
		{
			return "";
		}

		// set the field
		return (fieldData.getOutputValue());
	}

	/**
	 * Add a new field (if not existing) or modify existing field
	 * 
	 * @param fieldName
	 *            - field name
	 * @param fieldDataDef
	 *            - field data to copy the definition
	 * @param fieldValue
	 *            value - field value
	 * @param bInputValue
	 *            - true - update input value <br>
	 *            false - update database value
	 * 
	 * @return the field data
	 */
	public FieldData insertFieldData(String fieldName, FieldData fieldDataDef, String fieldValue, boolean bInputValue)
	{
		// retrieve the field data
		FieldData fieldData = rtvFieldData(fieldName);

		// not found?
		if (fieldData == null)
		{
			fieldData = new FieldData(fieldName, fieldDataDef);
			addFieldData(fieldName, fieldData);
		}

		// change field
		if (bInputValue)
		{
			fieldData.setInputValue(fieldValue);
		}
		else
		{
			fieldData.setDbValue(fieldValue);
			fieldData.setInputValue(cvtDbIntoInput(fieldData, fieldValue));
		}

		// field found
		return fieldData;
	}

	/**
	 * Add a new field (if not existing) or modify existing field
	 * 
	 * @param fieldName
	 *            - field name
	 * @param fieldDataDef
	 *            - field data to copy the definition
	 * @param fieldValue
	 *            value - field value
	 * @param bInputValue
	 *            - true - update input value <br>
	 *            false - update database value
	 * 
	 * @return the field data
	 */
	public FieldData insertRepeatingFieldData(String fieldName, RepeatingFieldData fieldDataDef, String fieldValue,
					boolean bInputValue)
	{
		// retrieve the field data
		FieldData fieldData = rtvFieldData(fieldName);

		// not found?
		if (fieldData == null)
		{
			fieldData = new RepeatingFieldData(fieldName, fieldDataDef);
			addFieldData(fieldName, fieldData);
		}

		// change field
		if (bInputValue)
		{
			fieldData.setInputValue(fieldValue);
		}
		else
		{
			fieldData.setDbValue(fieldValue);
			fieldData.setInputValue(cvtDbIntoInput(fieldData, fieldValue));
		}

		// field found
		return fieldData;
	}

	/**
	 * Add a new field (if not existing) or modify existing field
	 * 
	 * @param fieldName
	 *            - field name
	 * @param fieldDef
	 *            - the Equation field definition
	 * @param fieldValue
	 *            - field value
	 * @param bInputValue
	 *            - true - update input value <br>
	 *            false - update database value
	 * 
	 * @return true if the field name has been added
	 */
	public FieldData insertFieldData(String fieldName, EquationFieldDefinition fieldDef, String fieldValue, boolean bInputValue)
	{
		FieldData fieldDataDef = new FieldData(fieldName, fieldDef.getFieldDataTypeString(), fieldDef.getFieldLength(), fieldDef
						.getFieldDecimal());
		fieldDataDef.setUpperCase(true);
		return insertFieldData(fieldName, fieldDataDef, fieldValue, bInputValue);
	}

	/**
	 * Add a new field (if not existing) or modify existing field
	 * 
	 * @param repeatingDataManager
	 *            the repeating DataManager to add the fields to
	 * @param fieldName
	 *            - field name
	 * @param fieldDef
	 *            - the Equation field definition
	 * @param fieldValue
	 *            - field value
	 * @param bInputValue
	 *            - true - update input value <br>
	 *            false - update database value
	 * 
	 * @return true if the field name has been added
	 */
	private FieldData insertRepeatingFieldData(RepeatingDataManager repeatingDataManager, String fieldName,
					EquationFieldDefinition fieldDef, String fieldValue, boolean bInputValue)
	{
		RepeatingFieldData fieldDataDef = new RepeatingFieldData(fieldName, fieldDef.getFieldDataTypeString(), fieldDef
						.getFieldLength(), fieldDef.getFieldDecimal(), repeatingDataManager);
		fieldDataDef.setUpperCase(true);
		return insertRepeatingFieldData(fieldName, fieldDataDef, fieldValue, bInputValue);
	}

	/**
	 * Loads the field value from an Equation Data Structure into the function data
	 * 
	 * @param fdTemplate
	 *            - the Function data template. When a field is to be added, the field definition will be similar to the Function
	 *            Data template, if existing. Otherwise, the field definition will be derived from the EquationDataStructure
	 * @param eqDsDta
	 *            - Equation data structure date
	 * @param prefix
	 *            - string to be added to the field name
	 * @param suffix
	 *            - string to be appended to the field name
	 * @param bInputValue
	 *            - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 * 
	 * @return true
	 */
	public boolean insertFieldData(FunctionData fdTemplate, EquationDataStructureData eqDsDta, String prefix, String suffix,
					boolean bInputValue)
	{
		EquationDataStructure eqDs = eqDsDta.getEqDS();
		Set<String> fieldNames = eqDsDta.getEqDS().getFieldNames();
		Iterator<String> fieldNamesIterator = fieldNames.iterator();

		while (fieldNamesIterator.hasNext())
		{
			String fieldName = fieldNamesIterator.next();
			EquationFieldDefinition fd = eqDs.getFieldDefinition(fieldName);

			// is this field in the template?
			FieldData fieldDataDef = fdTemplate.getFieldDatas().get(fieldName);
			if (fieldDataDef == null)
			{
				fieldDataDef = new FieldData(fieldName, fd.getFieldDataTypeString(), fd.getFieldLength(), fd.getFieldDecimal());
				fieldDataDef.setUpperCase(true);
			}

			String fieldValue = "";
			try
			{
				fieldValue = eqDsDta.getFieldValue(fieldName).trim();
			}
			catch (Exception e)
			{
				fieldValue = "";
			}

			insertFieldData(prefix + fieldName + suffix, fieldDataDef, fieldValue, bInputValue);
		}

		// returns true
		return true;
	}

	/**
	 * Convert a database field value into input field value
	 * 
	 * @param fieldData
	 *            - field data
	 * @param dbValue
	 *            - database field value
	 * 
	 * @return input field value
	 */
	public String cvtDbIntoInput(FieldData fieldData, String dbValue)
	{
		// input field value
		String inputValue = dbValue;

		// determine field data type
		String fieldType = fieldData.getFieldType();

		if (fieldType.equals(EqDataType.TYPE_CHAR))
		{
			inputValue = dbValue;
		}
		else if (fieldType.equals(EqDataType.TYPE_ZONED) || fieldType.equals(EqDataType.TYPE_PACKED))
		{
			String numeric = Toolbox.removeLeadingZeroes(Toolbox.removeTrailingZeroes(dbValue, EqDataType.DECIMALSEP), false);
			inputValue = EqDataType.cvtDbNumericToInput(numeric, integerSeparator, decimalSeparator);
		}
		else if (fieldType.equals(EqDataType.TYPE_BOOLEAN))
		{
			inputValue = String.valueOf(EqDataType.cvtDbYNOToInput(dbValue, yesValue, noValue));
		}
		else if (fieldType.equals(EqDataType.TYPE_DATE))
		{
			if (fieldData.chkExtAttributeDate8())
			{
				inputValue = EqDataType.cvtDbDateToInput8D(dbValue, dateInputFormat, openDateAbbr);
			}
			else
			{
				inputValue = EqDataType.cvtDbDateToInput6D(dbValue, dateInputFormat, openDateAbbr);
			}
		}

		// return the input value
		return inputValue;
	}

	/**
	 * Convert a database field value into input field value
	 * 
	 * @param fieldData
	 *            - field data
	 * @param inputValue
	 *            - input field value
	 * 
	 * @return input field value
	 */
	public String cvtInputIntoDb(FieldData fieldData, String inputValue)
	{
		// input field value
		String dbValue = inputValue;

		// determine field data type
		String fieldType = fieldData.getFieldType();

		if (fieldType.equals(EqDataType.TYPE_BOOLEAN))
		{
			dbValue = String.valueOf(EqDataType.cvtInputYNOToDb(inputValue, yesValue, noValue));
		}
		else if (EqDataType.isNumeric(fieldType))
		{
			dbValue = EqDataType.cvtNumericToDb(inputValue, integerSeparator, decimalSeparator);
		}
		else if (fieldType.equals(EqDataType.TYPE_DATE))
		{
			if (fieldData.chkExtAttributeDate8())
			{
				dbValue = EqDataType.cvtDate8DToDb(inputValue, dateInputFormat, openDateAbbr);
			}
			else
			{
				dbValue = EqDataType.cvtDate6DToDb(inputValue, dateInputFormat, openDateAbbr);
			}
		}

		// return the input value
		return dbValue;
	}

	/**
	 * Remove all messages
	 * 
	 * @param type
	 *            - select which messages to clear
	 * 
	 */
	public void clearMessages(int type)
	{
		if (type == CLEAR_MSG_ALL || type == CLEAR_MSG_FIX)
		{
			Enumeration<FieldData> enumeration = fieldDatas.elements();
			while (enumeration.hasMoreElements())
			{
				FieldData fieldData = enumeration.nextElement();
				fieldData.clearMessages();
			}
		}

		if (type == CLEAR_MSG_ALL || type == CLEAR_MSG_RPT)
		{
			for (RepeatingDataManager repeatingDataManager : repeatingDataManagers)
			{
				repeatingDataManager.clearMessages();
			}
		}
	}

	/**
	 * Remove all messages lower than the specified message severity
	 * 
	 * @param type
	 *            - select which messages to clear
	 * @param msgSev
	 *            - message severity
	 */
	public void clearMessages(int type, int msgSev)
	{
		if (type == CLEAR_MSG_ALL || type == CLEAR_MSG_FIX)
		{
			Enumeration<FieldData> enumeration = fieldDatas.elements();
			while (enumeration.hasMoreElements())
			{
				FieldData fieldData = enumeration.nextElement();
				int mSev = fieldData.getMsgSev();
				if (mSev != FunctionMessages.MSG_NONE && mSev < msgSev)
				{
					fieldData.clearMessages();
				}
			}
		}

		if (type == CLEAR_MSG_ALL || type == CLEAR_MSG_RPT)
		{
			for (RepeatingDataManager repeatingDataManager : repeatingDataManagers)
			{
				repeatingDataManager.clearMessages(msgSev);
			}
		}
	}

	/**
	 * Duplicate this function data via XML
	 * 
	 * @return FunctionData A copy of this FunctionData
	 * 
	 * @throws EQException
	 */
	public FunctionData duplicateFunctionDataXML() throws EQException
	{
		LOG.debug("duplicateFunctionData 1:");
		String xml = cvtToXML();
		EqBeanFactory eqBeanFactory = EqBeanFactory.getEqBeanFactory();
		FunctionData functionDataCopy = (FunctionData) eqBeanFactory.deserialiseXMLAsBean(xml, FunctionData.class);

		// Explicitly copy over the repeating field definitions which aren't
		// serialised
		for (RepeatingDataManager mgr : repeatingDataManagers)
		{
			// TODO: Why aren't the Repeating Data Managers deserialised?
			RepeatingDataManager copyManager = new RepeatingDataManager(mgr.getId());
			copyManager.initialise((LinkedHashMap<String, FieldDefinition>) mgr.getFieldDefinitions());
			functionDataCopy.addRepeatingDataManager(copyManager);
		}
		LOG.debug("duplicateFunctionData 2:");
		return functionDataCopy;
	}

	/**
	 * Duplicate this function data
	 * 
	 * @return FunctionData A copy of this FunctionData
	 * 
	 * @throws EQException
	 */
	public FunctionData duplicateFunctionData() throws EQException
	{
		LOG.debug("duplicateFunctionData 3:");

		// create an empty one
		FunctionData functionDataCopy = new FunctionData();

		// copy all the field datas
		for (String fieldName : fieldDatas.keySet())
		{
			FieldData fieldData = fieldDatas.get(fieldName);
			FieldData copyFieldData = null;
			if (fieldData instanceof RepeatingFieldData)
			{
				RepeatingFieldData repeatingFieldData = new RepeatingFieldData(fieldName, (RepeatingFieldData) fieldData);
				copyFieldData = repeatingFieldData;

				// also create a new copy of the data manager if not yet exists
				String idDataManager = repeatingFieldData.rtvRepeatingDataManager().getId();
				RepeatingDataManager repeatingDataManager = functionDataCopy.getRepeatingDataManager(idDataManager);
				if (repeatingDataManager == null)
				{
					repeatingDataManager = repeatingFieldData.rtvRepeatingDataManager().duplicate();
					functionDataCopy.addRepeatingDataManager(repeatingDataManager);
				}
				repeatingFieldData.chgRepeatingDataManager(repeatingDataManager);
			}
			else
			{
				copyFieldData = new FieldData(fieldName, fieldData);
			}
			functionDataCopy.addFieldData(fieldName, copyFieldData);
		}

		// copy the attribute
		functionDataCopy.copyAttrib(this);
		functionDataCopy.setOptionId(this.optionId);

		// copy function control data
		// ???

		LOG.debug("duplicateFunctionData 4:");
		return functionDataCopy;
	}

	/**
	 * Set either the input value or the database value of the field in the function data
	 * 
	 * @param fieldName
	 *            - field name
	 * @param fieldValue
	 *            - field value
	 * @param bInputValue
	 *            - true - update input value <br>
	 *            false - update database value
	 */
	public void chgInputOrDBField(String fieldName, String fieldValue, boolean bInputValue)
	{
		if (fieldValue != null)
		{
			if (bInputValue)
			{
				chgFieldInputValue(fieldName, fieldValue);
			}
			else
			{
				chgFieldDbValue(fieldName, fieldValue);
			}
		}
	}

	/**
	 * Check whether the field value is different for updating
	 * 
	 * @param fieldName
	 *            - field name
	 * @param fieldValue
	 *            - field value
	 * @param bInputValue
	 *            - true - check for field input value
	 *            <p>
	 *            otherwise check for field database value
	 * 
	 * @return true - if the field is different. If field is locked, then always returns false
	 */
	public boolean chkFieldChange(String fieldName, String fieldValue, boolean bInputValue)
	{
		// Retrieve the field data
		FieldData fieldData = rtvFieldData(fieldName);

		// not found?
		if (fieldData == null)
		{
			return false;
		}

		// locked?
		if (fieldData.isLocked())
		{
			return false;
		}

		String currentValue;
		if (bInputValue)
		{
			currentValue = fieldData.getInputValue();
		}
		else
		{
			currentValue = fieldData.getDbValue();
		}

		return (!currentValue.equals(fieldValue));
	}

	/**
	 * Loads the field value from a Map into the function data.
	 * 
	 * @param map
	 *            - table of field names and field values
	 * @param bInputValue
	 *            - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 * 
	 * @return true - if at least one field has been changed
	 */
	public boolean loadFieldDataFromMap(Map<String, String> map, boolean bInputValue)
	{
		Set<String> fieldNames = map.keySet();
		Iterator<String> fieldNamesIterator = fieldNames.iterator();
		String fieldName;
		String fieldValue;
		boolean changed = false;

		while (fieldNamesIterator.hasNext())
		{
			fieldName = fieldNamesIterator.next();
			fieldValue = map.get(fieldName);

			// check if the value will be updated
			if (!changed)
			{
				changed = chkFieldChange(fieldName, fieldValue, bInputValue);
			}

			// if there is no changed, then there is no need to update
			if (changed)
			{
				chgInputOrDBField(fieldName, fieldValue, bInputValue);
			}
		}

		// has any data changed?
		return changed;
	}

	/**
	 * Loads the field value from a Map into the function data. The map value is an array
	 * 
	 * @param map
	 *            - table of field names and field values
	 * @param bInputValue
	 *            - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 * @param ccsid
	 *            - the CCSID of the session
	 * 
	 * @return true - if at least one field has been changed
	 */
	public boolean loadFieldDataFromMap2(Map<String, String[]> map, boolean bInputValue, int ccsid)
	{
		Set<String> fieldNames = map.keySet();
		Iterator<String> fieldNamesIterator = fieldNames.iterator();
		String fieldName;
		String fieldValue;
		String[] fieldValuex;
		boolean changed = false;
		boolean rtlCcsid = EquationCommonContext.isRtlCcsid(ccsid);

		while (fieldNamesIterator.hasNext())
		{
			fieldName = fieldNamesIterator.next();
			fieldValuex = map.get(fieldName);
			fieldValue = null;

			// with value value?
			if (fieldValuex != null)
			{
				fieldValue = fieldValuex[0];
			}

			// retrieve field data
			FieldData fieldData = rtvFieldData(FunctionRuntimeToolbox.getRootFieldNameForRepeatingField(fieldName));

			// is this repeating data, then setup the field row
			if (fieldData instanceof RepeatingFieldData)
			{
				((RepeatingFieldData) fieldData).rtvRepeatingDataManager().setRow(fieldName);
			}

			if (fieldData != null && fieldValue != null && !fieldData.isShowDescAsValue())
			{
				// is rtl?
				String[] rtlValuex = map.get(fieldName + HTMLToolbox.ID_RTL);
				if (rtlValuex != null)
				{
					String rtlValue = rtlValuex[0];
					fieldData.setOrientation(Toolbox.parseInt(rtlValue, FieldValues.ORIENT_NOT_SET));

					if (fieldData.chkRTL())
					{
						fieldValue = Toolbox.convertTextRTLFromDisplay(fieldValue, fieldData.getFieldLength(), ccsid,
										BidiStringType.ST9);
					}
					else
					{
						fieldValue = Toolbox.convertTextRTLFromDisplay(fieldValue, fieldData.getFieldLength(), ccsid, 0);
					}
				}

				// not specified as RTL, check if CCSID can support RTL, as user can still input this language
				else if (rtlCcsid)
				{
					fieldValue = Toolbox.convertTextRTLFromDisplay(fieldValue, fieldData.getFieldLength(), ccsid, 0);
				}

				// work field - set the details but do not marked as dirty
				if (fieldData.isWorkField())
				{
					chgFieldDbValue(fieldName, fieldValue);
					continue;
				}

				// check if the value will be updated
				if (!changed)
				{
					changed = chkFieldChange(fieldData.getFieldName(), fieldValue, bInputValue);
				}

				// if there is no changed, then there is no need to update
				if (changed)
				{
					chgInputOrDBField(fieldData.getFieldName(), fieldValue, bInputValue);
				}
			}
		}

		// has any data changed?
		return changed;
	}

	/**
	 * Loads the field value from bytes data into the function data
	 * 
	 * @param eqDs
	 *            - Equation data structure
	 * @param gzData
	 *            - GZ data
	 * @param bInputValue
	 *            - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 * 
	 * @return true - if at least one field has been changed
	 */
	public boolean loadFieldDataFromBytes(EquationDataStructure eqDs, byte[] gzData, boolean bInputValue)
	{
		// create the DS data and set the field
		EquationDataStructureData eqDsDta = new EquationDataStructureData(eqDs);

		// Set the data
		eqDsDta.setBytes(gzData);

		// set the function data
		return loadFieldDataFromDS(eqDsDta, bInputValue);
	}

	/**
	 * Loads the field value from an Equation Data Structure into the function data
	 * 
	 * @param eqDsDta
	 *            - Equation data structure date
	 * @param bInputValue
	 *            - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 * 
	 * @return true - if at least one field has been changed
	 */
	public boolean loadFieldDataFromDS(EquationDataStructureData eqDsDta, boolean bInputValue)
	{
		Set<String> fieldNames = eqDsDta.getEqDS().getFieldNames();
		Iterator<String> fieldNamesIterator = fieldNames.iterator();
		String fieldName;
		String fieldValue;
		boolean changed = false;

		while (fieldNamesIterator.hasNext())
		{
			fieldName = fieldNamesIterator.next();

			try
			{
				fieldValue = eqDsDta.getFieldValue(fieldName);
			}
			catch (Exception e)
			{
				fieldValue = "";
			}

			// check if the value will be updated
			if (!changed)
			{
				changed = chkFieldChange(fieldName, fieldValue, bInputValue);
			}

			// if there is no changed, then there is no need to update
			if (changed)
			{
				chgInputOrDBField(fieldName, fieldValue, bInputValue);
			}
		}

		// has any data changed?
		return changed;
	}

	/**
	 * Loads the field value from another Function Data
	 * 
	 * @param inFunctionData
	 *            - the Function Data to copy from
	 * @param bInputValue
	 *            - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 * @param predefined
	 *            - include predefined fields (true) or not (false)
	 * 
	 * @return true - if at least one field has been changed
	 */
	public boolean loadFieldDataFromFunctionData(FunctionData inFunctionData, boolean bInputValue, boolean predefined)
	{
		boolean changed = false;

		Iterator<String> iter = inFunctionData.getFieldDatas().keySet().iterator();
		while (iter.hasNext())
		{
			String fieldName = iter.next();

			// non-predefined fields are always to be updated. For pre-defined fields, this depends on the parameter
			if (predefined || !isPredefinedField(fieldName))
			{
				FieldData fieldData = rtvFieldData(fieldName);

				// not lock
				if (fieldData != null && !fieldData.isLocked())
				{
					String fieldValue;
					if (bInputValue)
					{
						fieldValue = inFunctionData.rtvFieldInputValue(fieldName);
					}
					else
					{
						fieldValue = inFunctionData.rtvFieldDbValue(fieldName);
					}

					// check if the value will be updated
					if (!changed)
					{
						changed = chkFieldChange(fieldName, fieldValue, bInputValue);
					}

					// if there is no changed, then there is no need to update
					if (changed)
					{
						chgInputOrDBField(fieldName, fieldValue, bInputValue);
					}
				}
			}
		}

		return changed;
	}
	/**
	 * Loads the field value from an Journal record into the function data
	 * 
	 * @param journalRecord
	 *            record - journal record
	 * 
	 * @return true - if at least one field has been changed
	 */
	public boolean loadFieldDataFromJournalRecord(JournalRecord journalRecord)
	{
		return loadFieldDataFromDS(journalRecord.getJrnData(), false);
	}

	/**
	 * Load the field data from an XML
	 * 
	 * @param xml
	 *            - the XML representation of a Function Data
	 * 
	 * @return true
	 * 
	 * @throws EQException
	 */
	public boolean loadFieldDataFromXML(String xml) throws EQException
	{
		EqBeanFactory eqBeanFactory = EqBeanFactory.getEqBeanFactory();
		FunctionData functionData = (FunctionData) eqBeanFactory.deserialiseXMLAsBean(xml, FunctionData.class);
		this.fieldDatas = functionData.fieldDatas;
		this.functionControlData = functionData.functionControlData;
		this.decimalSeparator = functionData.decimalSeparator;
		this.integerSeparator = functionData.integerSeparator;
		this.yesValue = functionData.yesValue;
		this.noValue = functionData.noValue;
		this.dateInputFormat = functionData.dateInputFormat;
		this.openDateAbbr = functionData.openDateAbbr;
		this.openDateFull = functionData.openDateFull;
		this.optionId = functionData.optionId;
		this.repeatingDataManagers = functionData.repeatingDataManagers;

		return true;
	}

	/**
	 * Load the field value from the context string
	 * 
	 * @param function
	 *            - the function
	 * @param contextStr
	 *            - the string context
	 * @param bInputValue
	 *            true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 * 
	 * @return true - if at least one field has been changed
	 */
	public boolean loadFieldDataFromContext(Function function, String contextStr, boolean bInputValue)
	{
		// blank
		contextStr = contextStr.trim();
		if (contextStr.equals(""))
		{
			return false;
		}

		// parse it
		int index = -1;
		String[] list = ContextString.parseString(contextStr).getContextAsArray();

		// no entries?
		if (list.length == 0)
		{
			return false;
		}

		// loop through the function
		boolean changed = false;
		String fieldName;
		String fieldValue;
		List<InputFieldSet> inputFieldSets = function.getInputFieldSets();
		for (int i = 0; i < inputFieldSets.size(); i++)
		{
			List<InputField> fields = inputFieldSets.get(i).getInputFields();
			for (int j = 0; j < fields.size(); j++)
			{
				index++;
				fieldName = fields.get(j).getId();
				fieldValue = list[index];

				// check for '='
				int equal = list[index].indexOf("=");
				if (equal >= 0)
				{
					fieldName = list[index].substring(0, equal);
					fieldValue = list[index].substring(equal + 1);

					// if this is not a valid field name, then assume this is a field value
					// with equal sign as a value
					if (rtvFieldData(fieldName) == null)
					{
						fieldName = fields.get(j).getId();
						fieldValue = list[index];
					}
				}

				// check if the value will be updated
				if (!changed)
				{
					changed = chkFieldChange(fieldName, fieldValue, bInputValue);
				}

				// if there is no changed, then there is no need to update
				if (changed)
				{
					chgInputOrDBField(fieldName, fieldValue, bInputValue);
				}

				// end?
				if (index >= list.length - 1)
				{
					break;
				}
			}

			// end?
			if (index + 1 >= list.length - 1)
			{
				break;
			}
		}

		// has any data changed?
		return changed;
	}

	/**
	 * Convert the function data to an Equation data structure data
	 * 
	 * @return the equivalent Equation data structure data
	 * @throws EQException
	 */
	public EquationDataStructureData cvtToDS(EquationStandardSession unit) throws EQException
	{
		EquationDataStructure eqDs = new EquationDataStructure(JournalFile.getJournalName(optionId), unit);
		EquationDataStructureData eqDsDta = new EquationDataStructureData(eqDs);

		// retrieve all the field names
		Enumeration<String> fieldNames = fieldDatas.keys();
		while (fieldNames.hasMoreElements())
		{
			String fieldName = fieldNames.nextElement();
			FieldData fieldValue = fieldDatas.get(fieldName);
			eqDsDta.setFieldValue(fieldName, fieldValue.getDbValue());
		}

		return eqDsDta;
	}

	/**
	 * Convert the function data to a journal record
	 * 
	 * @return the equivalent Equation data structure data
	 * @throws EQException
	 */
	public JournalRecord cvtToJournalRecord(EquationStandardSession unit) throws EQException
	{
		EquationDataStructureData jrnData = cvtToDS(unit);
		JournalRecord journalRecord = new JournalRecord(null);
		journalRecord.setJrnData(jrnData);

		return journalRecord;
	}

	/**
	 * Convert the function data to an XML
	 * 
	 * @return the equivalent XML of the function data
	 * 
	 * @throws EQException
	 */
	public String cvtToXML() throws EQException
	{
		EqBeanFactory eqBeanFactory = EqBeanFactory.getEqBeanFactory();
		String xml = eqBeanFactory.serialiseBeanAsXML(this);
		return xml;
	}

	/**
	 * Return the String representation
	 * 
	 * @return the String representation
	 */
	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();

		Iterator<String> fieldNames = fieldDatas.keySet().iterator();
		while (fieldNames.hasNext())
		{
			String fieldName = fieldNames.next();
			FieldData fieldData = fieldDatas.get(fieldName);
			if (fieldData != null)
			{
				buffer.append(fieldData + "\n");
			}
		}

		// print the repeating data
		for (RepeatingDataManager repeatingDataManager : repeatingDataManagers)
		{
			buffer.append(repeatingDataManager.toString());
		}

		return buffer.toString();
	}

	/**
	 * Return the first field name that is different between the data
	 * 
	 * @param comparisonData
	 *            - true if the Function Data is the same
	 * 
	 * @return the field name that causes the comparison to fail
	 */
	public String equalFd(Function function, FunctionData comparisonData)
	{
		// list of repeating groups that already been checked
		Hashtable<String, String> repeatingGroupChecked = new Hashtable<String, String>();

		for (int i = 0; i < function.getInputFieldSets().size(); i++)
		{
			InputFieldSet inputFieldSet = function.getInputFieldSets().get(i);
			for (InputField inputField : inputFieldSet.getInputFields())
			{
				String fieldName = inputField.getId();
				if (Field.isRepeating(inputField))
				{
					if (repeatingGroupChecked.get(inputField.getRepeatingGroup()) == null)
					{
						// compare repeating data
						RepeatingDataManager afd = getRepeatingDataManager(inputField.getRepeatingGroup());
						RepeatingDataManager bfd = comparisonData.getRepeatingDataManager(inputField.getRepeatingGroup());
						String fieldName2 = afd.equalFd(inputFieldSet, bfd);
						repeatingGroupChecked.put(inputField.getRepeatingGroup(), "");
						if (fieldName2.length() > 0)
						{
							return fieldName2;
						}
					}
				}
				else
				{
					FieldData afd = rtvFieldData(fieldName);
					FieldData bfd = comparisonData.rtvFieldData(fieldName);

					// comparison field data does not contain the field
					if (bfd == null)
					{
						return fieldName;
					}

					// check input field value
					if (!afd.getInputValue().equals(bfd.getInputValue()))
					{
						return fieldName;
					}

					// check database field value
					if (!afd.getDbValue().equals(bfd.getDbValue()))
					{
						return fieldName;
					}
				}
			}
		}

		return "";
	}

	/**
	 * Locked all input fields (but not work fields)
	 * 
	 */
	public void lockedInputFields()
	{
		// locked all fields so that it cannot be changed
		Iterator<String> fieldNames = fieldDatas.keySet().iterator();
		while (fieldNames.hasNext())
		{
			String fieldName = fieldNames.next();
			FieldData fieldData = fieldDatas.get(fieldName);
			if (!fieldData.isWorkField())
			{
				fieldData.setLocked(true);
			}
		}
	}

	/**
	 * Update the function data based on the list of field values
	 * 
	 * @param fieldValues
	 *            - the list of field values with the specified separator
	 * @param fieldValueDelim
	 *            - separator between field name and field value
	 * @param fieldDelim
	 *            - separator between fields
	 * 
	 * @return true - if at least one field has been changed
	 */
	public boolean updateFields(String fieldValues, String fieldValueDelim, String fieldDelim)
	{
		String[] list = fieldValues.split(fieldDelim);
		return updateFields(list, fieldValueDelim);
	}

	/**
	 * Update the function data based on the list of field values
	 * 
	 * @param fieldValues
	 *            - the list of field values
	 * @param fieldValueDelim
	 *            - separator between field name and field value
	 */
	public boolean updateFields(String[] fieldValues, String fieldValueDelim)
	{
		boolean changed = false;
		for (String fieldValue : fieldValues)
		{
			String[] list = fieldValue.split(fieldValueDelim);
			if (list.length >= 2)
			{
				// check if the value will be updated
				if (!changed)
				{
					changed = chkFieldChange(list[0], list[1], true);
				}

				// if there is no changed, then there is no need to update
				if (changed)
				{
					chgFieldInputValue(list[0], list[1]);
				}
			}
		}
		return changed;
	}

	/**
	 * Return the field value depending on the field type. See FieldData methods for more infor
	 * 
	 * @return the object representing the value
	 */
	public Object rtvFieldValueAsRealType(String fieldName)
	{
		// retrieve the field data
		FieldData fieldData = rtvFieldData(fieldName);

		// not found?
		if (fieldData == null)
		{
			return "";
		}

		// set the field
		return (fieldData.rtvFieldValueAsRealType());
	}

	/**
	 * Get the RepeatingDataManager with the specified Id
	 * 
	 * @param id
	 *            The Id of the required RepeatingDataManager
	 * 
	 * @return The RepeatingDataManager instance (or null if not existing)
	 */
	public RepeatingDataManager getRepeatingDataManager(String id)
	{
		for (RepeatingDataManager repeatingDataManager : repeatingDataManagers)
		{
			if (repeatingDataManager.getId().equals(id))
			{
				return repeatingDataManager;
			}
		}
		return null;
	}

	/**
	 * Return the list of repeating data managers
	 * 
	 * @return the list of repeating data managers
	 */
	public List<RepeatingDataManager> getRepeatingDataManagers()
	{
		return repeatingDataManagers;
	}

	/**
	 * Set the list of repeating data managers
	 * 
	 * @param repeatingDataManagers
	 *            - the list of repeating data managers
	 */
	public void setRepeatingDataManagers(List<RepeatingDataManager> repeatingDataManagers)
	{
		this.repeatingDataManagers = repeatingDataManagers;
	}

	/**
	 * @param repeatingDataManager
	 */
	public void addRepeatingDataManager(RepeatingDataManager repeatingDataManager)
	{
		// TODO: This has been added to allow Betwixt to serialise this
		// RepeatingDataManager when copying FunctionDatas. Revisit
		this.repeatingDataManagers.add(repeatingDataManager);
	}

	/**
	 * Copy the language specific format
	 * 
	 * @param templateData
	 *            A FunctionData instance to copy certain attributes from
	 */
	public void copyAttrib(FunctionData templateData)
	{
		setDateInputFormat(templateData.getDateInputFormat());
		setDecimalSeparator(templateData.getDecimalSeparator());
		setIntegerSeparator(templateData.getIntegerSeparator());
		setNoValue(templateData.getNoValue());
		setYesValue(templateData.getYesValue());
		setOpenDateAbbr(templateData.getOpenDateAbbr());
		setOpenDateFull(templateData.getOpenDateFull());
	}

	/**
	 * Copy the data from another Function Data
	 * 
	 * @param sourceFunctionData
	 *            - the source function data to copy
	 * 
	 * @return true if successful
	 */
	public boolean copyFixedData(FunctionData sourceFunctionData)
	{
		for (String fieldName : fieldDatas.keySet())
		{
			FieldData fieldData = fieldDatas.get(fieldName);
			FieldData sourceFieldData = sourceFunctionData.rtvFieldData(fieldName);

			if (sourceFieldData != null)
			{
				fieldData.copyFieldValues(sourceFieldData);
			}
		}

		return true;
	}

	/**
	 * Locked all the key fields
	 * 
	 * @param function
	 *            - the function
	 */
	public void lockedKeyFields(Function function)
	{
		// lock all key fields so it cannot be changed
		InputFieldSet inputFieldSet = function.rtvPrimaryInputFieldSet();
		for (int i = 0; i < inputFieldSet.getInputFields().size(); i++)
		{
			InputField inputField = inputFieldSet.getInputFields().get(i);
			if (inputField.isKey())
			{
				FieldData fieldData = rtvFieldData(inputField.getId());
				if (fieldData != null)
				{
					fieldData.setLocked(true);
				}
			}
		}
	}

	/**
	 * Unlocked all the key fields
	 * 
	 * @param function
	 *            - the function
	 */
	public void unlockedKeyFields(Function function)
	{
		// unprotect key fields
		InputFieldSet inputFieldSet = function.rtvPrimaryInputFieldSet();
		for (int i = 0; i < inputFieldSet.getInputFields().size(); i++)
		{
			InputField inputField = inputFieldSet.getInputFields().get(i);
			FieldData fieldData = rtvFieldData(inputField.getId());
			if (fieldData != null)
			{
				if (inputField.isKey())
				{
					fieldData.setLocked(fieldData.isDefaultLocked());
				}
			}
		}

	}

	/**
	 * Reset the values to initial setting
	 * 
	 * @param function
	 *            - the function
	 * @param includeKey
	 *            - true if keys are to be reset together with non-key, false only non-keys are reset
	 * 
	 */
	public void resetFields(Function function, boolean includeKey, FunctionHandlerData fhd) throws EQException
	{
		// reset input fields
		for (InputFieldSet inputFieldSet : function.getInputFieldSets())
		{
			for (InputField inputField : inputFieldSet.getInputFields())
			{
				FieldData fieldData = rtvFieldData(inputField.getId());
				if (fieldData != null)
				{
					if (!inputField.isKey() || (inputField.isKey() && includeKey))
					{
						String inputValue = FunctionRuntimeToolbox.rtvInitialValue(inputField, inputField.getInitialValueType(),
										inputField.getInitialValue(), fhd, this);
						if (inputField.getInitialValueAs().equals(InputField.VALUE_INPUT))
						{
							fieldData.setOutputValue("");
							fieldData.setInputValue(inputValue);
							fieldData.setDbValue(cvtInputIntoDb(fieldData, inputValue));
						}
						else if (inputField.getInitialValueAs().equals(InputField.VALUE_DATABASE))
						{
							fieldData.setOutputValue("");
							fieldData.setDbValue(inputValue);
							fieldData.setInputValue(cvtDbIntoInput(fieldData, inputValue));
						}

					}
				}
			}
		}

		// reset work fields only if keys
		if (includeKey)
		{
			for (WorkField workField : function.getWorkFields())
			{
				FieldData fieldData = rtvFieldData(workField.getId());
				if (fieldData != null)
				{
					String inputValue = FunctionRuntimeToolbox.rtvInitialValue(workField, workField.getInitialValueType(),
									workField.getInitialValue(), fhd, this);
					fieldData.setOutputValue("");
					fieldData.setInputValue(inputValue);
					fieldData.setDbValue(inputValue);
				}
			}
		}

		// reset repeating data
		for (RepeatingDataManager repeatingDataManager : repeatingDataManagers)
		{
			repeatingDataManager.clear();
		}
	}

	/**
	 * Update the function data with the content of the source function data
	 * 
	 * @param sourceFunctionData
	 *            - the source Function Data
	 */
	public void reSyncFunctionData(FunctionData sourceFunctionData)
	{
		// loop through all the field data
		Iterator<String> fieldNames = getFieldDatas().keySet().iterator();
		while (fieldNames.hasNext())
		{
			String fieldName = fieldNames.next();
			FieldData srcFieldData = sourceFunctionData.rtvFieldData(fieldName);
			if (srcFieldData != null)
			{
				FieldData dstFieldData = rtvFieldData(fieldName);
				dstFieldData.copyFieldValues(srcFieldData);
				dstFieldData.setFunctionMessages(srcFieldData.getFunctionMessages());
			}
		}

		// populate repeating data as well
		for (RepeatingDataManager dataManager : getRepeatingDataManagers())
		{
			String id = dataManager.getId();
			RepeatingDataManager templateDataManager = sourceFunctionData.getRepeatingDataManager(id);
			if (templateDataManager != null)
			{
				dataManager.reSyncData(templateDataManager);
			}
		}
	}

	/**
	 * Initialise data based on the layout
	 * 
	 * @param layout
	 *            - the layout
	 */
	public void initFromDisplay(Layout layout, Function function)
	{
		for (DisplayAttributesSet displayAttributeSet : layout.getDisplayAttributesSets())
		{
			for (IDisplayItem displayItem : displayAttributeSet.getDisplayItems())
			{
				initFromDisplay(displayItem, function);
			}
		}
	}

	/**
	 * Initialise data based on the display item
	 * 
	 * @param displayItem
	 *            - the display item
	 */
	public void initFromDisplay(IDisplayItem displayItem, Function function)
	{
		if (displayItem instanceof DisplayAttributes)
		{
			DisplayAttributes displayAttributes = (DisplayAttributes) displayItem;
			String fieldName = displayAttributes.getId();

			FieldData fieldData = rtvFieldData(displayAttributes.getId());
			if (fieldData == null)
			{
				InputField inputField = function.rtvInputField(fieldName);
				if (inputField != null)
				{
					fieldData = getFieldDataFromField(inputField);
					addFieldData(inputField.getId(), fieldData);
				}
			}
			if (fieldData != null)
			{
				fieldData.setEditCode(displayAttributes.getEditCode());

				// no edit code?
				if (displayAttributes.getEditCodeParameterStatus().equals(DisplayAttributes.EDIT_PARAMETER_CODE))
				{
					fieldData.setEditCodeParameter("");
				}
				else if (displayAttributes.getEditCodeParameterStatus().equals(DisplayAttributes.EDIT_PARAMETER_NONE))
				{
					fieldData.setEditCodeParameter(EqDataType.GLOBAL_DELIMETER);
				}
				else
				{
					fieldData.setEditCodeParameter(displayAttributes.getEditCodeParameter());
				}
			}
		}
		else if (displayItem instanceof DisplayGroup)
		{
			DisplayGroup displayGroup = (DisplayGroup) displayItem;
			for (IDisplayItem displayItem1 : displayGroup.getDisplayItems())
			{
				initFromDisplay(displayItem1, function);
			}

			if (!displayGroup.getRepeatingGroup().equals(""))
			{
				RepeatingDataManager dataManager = getRepeatingDataManager(displayGroup.getRepeatingGroup());

				dataManager.setGPUnitMnemonicField(displayGroup.getUnitMnemonic());
				dataManager.setGPSystemIdField(displayGroup.getSystemId());
			}
		}
	}

	/**
	 * Clear content of all repeating data
	 */
	public void clearRepeatingData()
	{
		// clear all repeating datas
		for (RepeatingDataManager dataManager : getRepeatingDataManagers())
		{
			dataManager.clear();
		}
	}

	/**
	 * Determine if the key screen is displayed or key has not been loaded
	 * 
	 * @return true if the key screen is displayed or key has not been loaded
	 */
	public boolean chkKeyDisplayed()
	{
		return rtvFieldData(FLD_KEYLOADED).getDbValue().equals(EqDataType.YES);
	}
	/**
	 * Set the prompt data
	 * 
	 * @param equationPVMetaData
	 */
	public void setPromptFieldData(EquationPVMetaData equationPVMetaData, String zlslct)
	{
		zlslct = Toolbox.pad(zlslct, equationPVMetaData.getDSCCNLength());
		List<EquationPVFieldMetaData> equationPVFieldMetaDatas = equationPVMetaData.getFieldMetaData();
		zlslct = zlslct.replace("*".charAt(0), " ".charAt(0));
		zlslct = zlslct.replace("?".charAt(0), " ".charAt(0));
		promptFieldDatas.clear();

		for (int i = 0; i < equationPVFieldMetaDatas.size(); i++)
		{
			EquationPVFieldMetaData equationPVFieldMetaData = equationPVFieldMetaDatas.get(i);
			String fieldName = equationPVFieldMetaData.getId();
			promptFieldDatas.put(fieldName, zlslct.substring(equationPVFieldMetaData.getIndex(), equationPVFieldMetaData.getIndex()
							+ equationPVFieldMetaData.getLength()));
		}
	}

	/**
	 * Return the prompt field or null if not a prompt or not found
	 * 
	 * @param field
	 *            - prompt field Id
	 * @return the prompt fields value, trimmed and with "*" or "?" control characters removed
	 */
	public String rtvPromptFieldValue(String field)
	{
		return promptFieldDatas.get(field);
	}

	/**
	 * Determine whether field is predefined or not
	 * 
	 * @param fieldName
	 *            - the field to be validated
	 * 
	 * @return true if field is predefined
	 */
	private boolean isPredefinedField(String fieldName)
	{
		return fieldName.equals(FLD_BREAKBY) || fieldName.equals(FLD_EFC) || fieldName.equals(FLD_FCT)
						|| fieldName.equals(FLD_KEYLOADED) || fieldName.equals(FLD_NEXT_REQUEST) || fieldName.startsWith(FLD_SEL)
						|| fieldName.equals(FLD_SUPERID) || fieldName.equals(FLD_STAT_MKR_CHKR)
						|| fieldName.equals(FLD_SUPP_MKR_CHKR);
	}

	/**
	 * Returns the user access fields object
	 * 
	 * @return UserAccessFields
	 */
	public UserAccessFields rtvUserAccessFields()
	{
		return userAccessFields;
	}

	/**
	 * Return the input extension data
	 * 
	 * @return the input extension data
	 */
	public ExtensionData getInputExtensionData()
	{
		return inputExtensionData;
	}

	/**
	 * Set the input extension data
	 * 
	 * @param inputExtensionData
	 *            - the input extension data
	 */
	public void setInputExtensionData(ExtensionData inputExtensionData)
	{
		if (inputExtensionData == null)
		{
			inputExtensionData = new ExtensionData();
		}
		this.inputExtensionData = inputExtensionData;
	}

	/**
	 * Return the output extension data
	 * 
	 * @return the output extension data
	 */
	public ExtensionData getOutputExtensionData()
	{
		return outputExtensionData;
	}

	/**
	 * Set the output extension data
	 * 
	 * @param outputExtensionData
	 *            - the output extension data
	 */
	public void setOutputExtensionData(ExtensionData outputExtensionData)
	{
		if (outputExtensionData == null)
		{
			outputExtensionData = new ExtensionData();
		}
		this.outputExtensionData = outputExtensionData;
	}

	/**
	 * Return the checksum (previously calculated)
	 * 
	 * @return the checksum (previously calculated)
	 */
	public String getChecksum()
	{
		return checksum;
	}

	/**
	 * Set the checksum
	 * 
	 * @param checksum
	 *            - the checksum
	 */
	public void setChecksum(String checksum)
	{
		this.checksum = checksum;
	}

	/**
	 * Recalculate the checksum
	 * 
	 * @param function
	 *            - the Function bean
	 * @param fieldFilterLocator
	 *            - the list of fields included in the checksum
	 * @param request
	 *            - This is relevant only if function is using enhanced XSD<br>
	 *            - true - the list of fields refers to Request XSD<br>
	 *            - false - the list of fields refers to Response XSD<br>
	 * @param xsdStructureLink
	 *            - the XSD structure link of the function
	 * 
	 * @return the checksum (in HEX format)
	 */
	public String rtvChecksum(Function function, FieldFilterLocator fieldFilterLocator, boolean request, XSDStructureLink link)
					throws EQException
	{
		CheckSumCalculator checksum = new CheckSumCalculator(this, function, link);
		String cs = checksum.calculate(fieldFilterLocator, request);
		setChecksum(cs);
		return cs;
	}

}
