package com.misys.equation.function.tools;

import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.misys.equation.common.access.EquationFieldDefinition;
import com.misys.equation.common.access.EquationPVDecodeMetaData;
import com.misys.equation.common.access.EquationPVFieldMetaData;
import com.misys.equation.common.access.EquationPVMetaData;
import com.misys.equation.common.access.EquationStandardQueryList;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IGAERecordDao;
import com.misys.equation.common.dao.IGAZRecordDao;
import com.misys.equation.common.dao.IHBXRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GAERecordDataModel;
import com.misys.equation.common.dao.beans.GAZRecordDataModel;
import com.misys.equation.common.dao.beans.HBXRecordDataModel;
import com.misys.equation.common.datastructure.EqDS_DSPFFD;
import com.misys.equation.common.datastructure.EqDS_Report;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.APIField;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayLabel;
import com.misys.equation.function.beans.Element;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.PVField;
import com.misys.equation.function.beans.PVFieldSet;
import com.misys.equation.function.beans.TextBean;
import com.misys.equation.function.beans.WorkField;
import com.misys.equation.function.language.LanguageResources;

public class FunctionToolbox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionToolbox.java 12396 2011-12-12 01:55:51Z williae1 $";

	/** Underscore character used for separating name parts */
	public static final String UNDERSCORE = "_";

	/**
	 * This is the additional length added to the key field of an SQL Query when the key field's operator is LIKE. The LIKE operator
	 * makes the length of the field 32000+, which is an invalid length in Service Composer
	 */
	public static final int SQL_FIELD_LENGTH_LIKE = 2;

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(FunctionToolbox.class);
	private static DaoFactory daoFactory = new DaoFactory();

	/**
	 * Get an empty GAE record
	 * 
	 * @param session
	 *            - a session in an Equation unit
	 * @return GAERecordDao ready for use
	 */
	private static IGAERecordDao getGAERecordDao(EquationStandardSession session)
	{
		IGAERecordDao dao = daoFactory.getGAEDao(session, new GAERecordDataModel());
		return dao;
	}

	/**
	 * Retrieves the PV meta data, and adds all the PVField definitions to the supplied PVFieldSet
	 * 
	 * @param session
	 *            an <code>EquationStandardSession</code>
	 * @param pvFieldSet
	 *            a <code>PVFieldSet</code> instance, before the fields have been added
	 * @param inputField
	 *            an <code>InputField</code> that is the parent of this PVFieldSet. Should be null for Load PVs.
	 * @param function
	 *            Must be a <code>Function</code> instance if the inputField parameter is not null.
	 * @param addMappings
	 *            Indicates whether default validate mappings should be created
	 * @throws EQException
	 */
	public static void populatePVFieldSetFields(EquationStandardSession session, PVFieldSet pvFieldSet, InputField inputField,
					Function function, boolean addMappings) throws EQException
	{
		if (inputField != null && function == null)
		{
			throw new IllegalArgumentException("function parameter must not be null if inputField is not null");
		}

		// Get a GAE ready for the PV meta data
		IGAERecordDao gaeRecordDao = getGAERecordDao(session);
		Hashtable<String, GAERecordDataModel> pvGAE;
		String whereClause = "GAESCN = '" + pvFieldSet.getId() + "'";
		pvGAE = gaeRecordDao.getGAERecordKeyedByScreenHandler(whereClause);
		GAERecordDataModel gaeRecord = pvGAE.get(pvFieldSet.getId());

		if (gaeRecord == null)
		{
			LOG.error("Failed to get GAE meta-data for PV [" + pvFieldSet.getId() + "]");
		}
		else
		{
			// Put the PV description in both the Label and the Description fields
			pvFieldSet.setLabel(gaeRecord.getDescription());
			pvFieldSet.setDescription(gaeRecord.getDescription());
		}

		// Get the PV meta data
		EquationPVMetaData eqPVMD = session.getUnit().getPVMetaData(pvFieldSet.getId());
		EquationPVDecodeMetaData decodeMetaData = eqPVMD.getDecodeMetaData(pvFieldSet.getDecode());
		if (decodeMetaData != null)
		{
			// Process all the fields
			for (int x = 0; x < eqPVMD.rtvNumberOfFields(); x++)
			{
				EquationPVFieldMetaData fieldMetaData = eqPVMD.rtvFieldMetaData(x);
				PVField pvField = new PVField(fieldMetaData.getId(), fieldMetaData.getDescription(), fieldMetaData.getDescription());
				pvFieldSet.addPVField(pvField);
				pvFieldSet.setGlobalTable(eqPVMD.isGlobalLibraryPrompt());
				pvFieldSet.setConsolidatedGlobalPV(eqPVMD.isGlobalConsolidatedPrompt());
				pvField.setDataType(fieldMetaData.getType());
				if (fieldMetaData.getDecimal() > 0)
				{
					pvField.setDecimals(Integer.toString(fieldMetaData.getDecimal()));
				}
				pvField.setSize(Integer.toString(fieldMetaData.getLength()));
				pvField.setKey(decodeMetaData.getPvFields().contains(pvField.getId()));

				if (addMappings && inputField != null && decodeMetaData.getKeyFields().size() < 2)
				{
					createPVFieldMappings(eqPVMD, decodeMetaData, function, inputField, pvField);
				}
			}
		}
	}

	/**
	 * Retrieves the PV meta data, and adds all the PVField definitions to the supplied PVFieldSet
	 * 
	 * @param pVFieldSet
	 *            a <code>PVFieldSet</code> instance, before the fields have been added
	 * @throws EQException
	 */
	private static void createPVFieldMappings(EquationPVMetaData pvMetaData, EquationPVDecodeMetaData decodeMetaData,
					Function function, InputField inputField, PVField pvField) throws EQException
	{
		List<String> pvIvals = decodeMetaData.getPvFields();
		List<String> pvOvals = pvMetaData.getPvOvals();
		List<String> pvDvals = pvMetaData.getPvDvals();

		String pvFieldPath = pvField.rtvPath();
		if (!pvFieldPath.startsWith(inputField.rtvPath()))
		{
			pvFieldPath = inputField.rtvPath() + "." + pvFieldPath;
		}
		// Mapping from inputField TO PV
		// TODO: This will always just map the input field to only the first PV field.
		// I.E. Doesn't take into account multiple keys.
		if (!function.hasToValidateMapping(inputField.rtvPath()))
		{
			if (!function.hasFromValidateMapping(pvFieldPath))
			{
				function.addValidateMapping(inputField.rtvPath(), pvFieldPath);
			}
		}

		if (pvDvals == null || pvDvals.size() == 0)
		{
			LOG.warn("No Primitive Values for: " + pvField.getId());
		}
		else if (pvDvals.get(0).equals(pvField.getId()))
		{
			if (!function.hasFromValidateMapping(inputField.rtvPath() + MappingToolbox.SUBFIELD_PRIMITIVE))
			{
				function.addValidateMapping(pvFieldPath, (inputField.rtvPath() + MappingToolbox.SUBFIELD_PRIMITIVE));
			}
		}

		// TODO: need to decide if we really need to default IVAL in the service composer, as we automatically
		// derive the IVAL at runtime. Also the code below still consider one IVAL field!
		if (pvIvals == null || pvIvals.size() == 0)
		{
			LOG.warn("No Input Values for: " + pvField.getId());
		}
		else if (pvIvals.get(0).equals(pvField.getId()) && pvDvals.size() == 0)
		{
			if (!function.hasFromValidateMapping(inputField.rtvPath() + MappingToolbox.SUBFIELD_PRIMITIVE))
			{
				function.addValidateMapping(pvFieldPath, (inputField.rtvPath() + MappingToolbox.SUBFIELD_PRIMITIVE));
			}
		}

		if (pvOvals == null || pvOvals.size() == 0)
		{
			LOG.warn("No Output Values for: " + pvField.getId());
		}
		else if (pvOvals.get(0).equals(pvField.getId()))
		{
			if (!function.hasFromValidateMapping(inputField.rtvPath() + MappingToolbox.SUBFIELD_OUTPUT))
			{
				function.addValidateMapping(pvFieldPath, (inputField.rtvPath() + MappingToolbox.SUBFIELD_OUTPUT));
			}
		}
	}
	/**
	 * Generate a default input field
	 * 
	 * @param id
	 *            - field id
	 * @param label
	 *            - field label
	 * @param desc
	 *            - field description
	 * @param dataType
	 *            - field data type
	 * @param length
	 *            - field length
	 * @param decimal
	 *            - field decimal
	 * 
	 * @return the InputField
	 */
	public static InputField getInputField(String id, String label, String desc, String dataType, String length, String decimal)
	{
		InputField inputField = new InputField();
		inputField.setId(id);
		inputField.setLabel(label);
		inputField.setDescription(desc);
		inputField.setDataType(dataType);
		inputField.setSize(length);
		inputField.setMaxLength("");
		inputField.setMinLength("");
		inputField.setDecimals(decimal);
		inputField.setKey(false);
		inputField.setLocked("");
		inputField.setMandatory(InputField.MANDATORY_NO);
		inputField.setRegExp("");
		inputField.setType("");
		inputField.setUpperCase(dataType.equals(EqDataType.TYPE_CHAR));
		inputField.setValidValues("");
		return inputField;
	}

	/**
	 * Generate a default input field
	 * 
	 * @param id
	 *            - field id
	 * @param label
	 *            - field label
	 * @param desc
	 *            - field description
	 * @param dataType
	 *            - field data type
	 * @param length
	 *            - field length
	 * @param decimal
	 *            - field decimal
	 * 
	 * @return the InputField
	 */
	public static InputField getInputFieldParameter(String id, String label, String desc, String dataType, String length,
					String decimal)
	{
		InputField inputField = getInputField(id, label, desc, dataType, length, decimal);
		inputField.setLabelType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
		inputField.setDescriptionType(Element.TEXT_VALUE_REUSABLE_REFERENCE);
		return inputField;
	}

	/**
	 * Determines the name of the input field given an API field
	 * <p>
	 * This removes the GZ prefix, and concatenates the API Set Id with the remaining field name, using the
	 * <code>FIELD_NAME_SEPARATOR</code> separator.
	 * 
	 * @param apiField
	 *            an <code>APIField</code> that is in the bean tree (must have a parent APIFieldSet)
	 * @return a <code>String</code> containing the name of the InputField
	 */
	public static String getInputFieldNameFromAPIField(APIField apiField)
	{
		// set the input field id, but ditch the GZ bit as it is superfluous
		if (apiField.getId().startsWith("GZ"))
		{
			return apiField.rtvParentFieldSet().getId().trim() + UNDERSCORE + apiField.getId().substring(2).trim();
		}
		return apiField.rtvParentFieldSet().getId().trim() + UNDERSCORE + apiField.getId().trim();
	}
	/**
	 * Creates and initialises a new InputField using an APIField as a template
	 * 
	 * @param apiField
	 *            an APIField instance
	 * 
	 * @return the newly created InputField instance
	 */
	public static InputField getInputField(APIField apiField)
	{
		InputField inputField = new InputField();
		inputField.setId(getInputFieldNameFromAPIField(apiField));
		inputField.setLabel(apiField.getLabel());
		inputField.setDescription(apiField.getDescription());
		inputField.setDataType(apiField.getDataType());
		inputField.setSize(apiField.getSize());
		inputField.setInitialValue("");
		inputField.setMaxLength("");
		inputField.setMinLength("");
		inputField.setDecimals(apiField.getDecimals());
		inputField.setKey(false);
		inputField.setLocked("");
		inputField.setMandatory(InputField.MANDATORY_NO);
		inputField.setRegExp("");
		inputField.setType("");
		if (apiField.getDataType().equals(EqDataType.TYPE_CHAR))
		{
			inputField.setUpperCase(true);
		}
		else
		{
			inputField.setUpperCase(false);
		}
		inputField.setValidValues("");
		return inputField;
	}

	/**
	 * Creates and initialise the display attribute of a field
	 * 
	 * @param inputField
	 *            - the input field
	 * 
	 * @return the newly created DisplayAttribute instance
	 */
	public static DisplayAttributes getInputFieldAttribute(InputField inputField)
	{
		DisplayAttributes displayAttribute = new DisplayAttributes();
		displayAttribute.setId(inputField.getId());
		displayAttribute.setLabel(inputField.getLabel());
		displayAttribute.setLabelType(inputField.getLabelType());
		displayAttribute.setDescription(inputField.getDescription());
		displayAttribute.setDescriptionType(inputField.getDescriptionType());
		displayAttribute.setKeepWithPrevious(false);
		displayAttribute.setLabelPosition(DisplayAttributes.FIELD_LABEL_LEFT);
		displayAttribute.setMask("");
		displayAttribute.setPrompt("");
		displayAttribute.setProtected(DisplayAttributes.PROTECTED_NO);
		displayAttribute.setVisible(DisplayAttributes.VISIBLE_YES);
		displayAttribute.setWidget("");
		return displayAttribute;
	}

	/**
	 * Add an input field to the input field set
	 * 
	 * @param inputFieldSet
	 *            - the InputFieldSet
	 * @param inputField
	 *            - the InputField
	 * 
	 * @return the InputField, updated with the path.
	 * 
	 * @throws EQException
	 */
	public static InputField addInputField(InputFieldSet inputFieldSet, InputField inputField) throws EQException
	{
		inputFieldSet.addInputField(inputField);
		return inputField;
	}

	/**
	 * Add an input field attribute to the attribute sets
	 * 
	 * @param attributesSet
	 *            - the attribute set
	 * @param displayAttributes
	 *            - the field display attribute
	 * @return the field display attribute
	 * 
	 */
	public static DisplayAttributes addDisplayAttributes(DisplayAttributesSet attributesSet, DisplayAttributes displayAttributes)
	{
		attributesSet.addItem(displayAttributes);
		return displayAttributes;
	}

	/**
	 * Add an display label to the attribute sets
	 * 
	 * @param attributesSet
	 *            - the attribute set
	 * @param displayLabel
	 *            - the display label
	 * 
	 * @return the field display attribute
	 */
	public static DisplayLabel addDisplayLabel(DisplayAttributesSet attributesSet, DisplayLabel displayLabel)
	{
		attributesSet.addItem(displayLabel);
		return displayLabel;
	}

	/**
	 * Add a PV field to the PV field set
	 * 
	 * @param pvFieldSet
	 *            - the PV field set
	 * @param pvField
	 *            - the PV field
	 * 
	 * @return the updated pv field
	 * 
	 * @throws EQException
	 */
	public static PVField addPVField(PVFieldSet pvFieldSet, PVField pvField) throws EQException
	{
		// update the path
		pvFieldSet.addPVField(pvField);
		return pvField;
	}

	/**
	 * Add a PV field set to a field
	 * 
	 * @param inputField
	 *            - the InputFieldSet
	 * @param pvFieldSet
	 *            - the PV field set
	 * 
	 * @return the updated PV Field set
	 * 
	 * @throws EQException
	 */
	public static PVFieldSet addPVFieldSet(InputField inputField, PVFieldSet pvFieldSet) throws EQException
	{
		// update the path
		inputField.addPvFieldSet(pvFieldSet);
		return pvFieldSet;
	}

	/**
	 * Return a PV Field based on a PV Field Meta Data
	 * 
	 * @param fieldMetaData
	 *            - the PV field meta data
	 * 
	 * @return the equivalent PV Field
	 */
	public static PVField getPVField(EquationPVFieldMetaData fieldMetaData)
	{
		PVField pvField = new PVField(fieldMetaData.getId(), fieldMetaData.getDescription(), fieldMetaData.getDescription());
		pvField.setSize(String.valueOf(fieldMetaData.getLength()));

		pvField.setDataType(fieldMetaData.getType());
		if (pvField.getDataType() == EqDataType.TYPE_PACKED || pvField.getDataType() == EqDataType.TYPE_ZONED)
		{
			pvField.setDecimals(String.valueOf(fieldMetaData.getDecimal()));
		}
		else
		{
			pvField.setDataType(EqDataType.TYPE_CHAR);
		}
		return pvField;
	}

	/**
	 * Return a PV Field Set representation of a PV program
	 * 
	 * @param session
	 *            - the Equation Standard session
	 * @param pvName
	 *            - the PV name
	 * @param decode
	 *            - the decode
	 * @param blankAllowed
	 *            - blank allowed?
	 * @param newCode
	 *            - new code?
	 * 
	 * @return the PV Field Set
	 * 
	 * @throws EQException
	 */
	public static PVFieldSet getPVFieldSet(EquationStandardSession session, String pvName, String decode, boolean blankAllowed,
					String newCode) throws EQException
	{
		EquationPVMetaData pvMetaData = session.getUnit().getPVMetaData(pvName);

		// set up the PV field set
		PVFieldSet pvFieldSet = new PVFieldSet(pvName, "", decode, newCode, blankAllowed);
		pvFieldSet.setGlobalTable(pvMetaData.isGlobalLibraryPrompt());
		pvFieldSet.setConsolidatedGlobalPV(pvMetaData.isGlobalConsolidatedPrompt());
		// get all the fields of the pv meta data
		for (int i = 0; i < pvMetaData.rtvNumberOfFields(); i++)
		{
			addPVField(pvFieldSet, getPVField(pvMetaData.rtvFieldMetaData(i)));
		}

		return pvFieldSet;
	}

	/**
	 * Return the API Field representation of an EquationFieldDefinition
	 * 
	 * @param efd
	 * 
	 * @return the API Field
	 */
	public static APIField getAPIField(EquationFieldDefinition efd, String keys)
	{
		APIField apiField = new APIField(efd.getFieldName(), efd.getFieldLabel(), efd.getFieldLabel());
		apiField.setDataType(efd.getFieldDataTypeString());
		apiField.setSize(String.valueOf(efd.getFieldLength()));

		if (apiField.getDataType().equals(EqDataType.TYPE_DATE) || apiField.getDataType().equals(EqDataType.TYPE_PACKED)
						|| apiField.getDataType().equals(EqDataType.TYPE_ZONED))
		{
			apiField.setDecimals(String.valueOf(efd.getFieldDecimal()));
		}
		// TODO Change this code - method should receive a set of key fields
		boolean isKey = false;
		String[] keyFields = keys.split(Toolbox.CONTEXT_DELIMETER);
		for (String keyField : keyFields)
		{
			if (apiField.getId().equals(keyField.trim()))
			{
				isKey = true;
			}
		}
		apiField.setKey(isKey);
		apiField.setType(efd.getFieldType());
		return apiField;
	}

	/**
	 * Return the APIField representation of a SQL parameter (a key field)
	 * 
	 * @param rsMeta
	 *            A ParameterMetaData instance
	 * @param rsMeta2
	 *            - A ParameterMetaData instance where the LIKE operation has been replaced by = operation to determine the actual
	 *            length of the field
	 * @param index
	 *            The parameter index to generate the APIField for
	 * 
	 * @return the API Field
	 * @throws SQLException
	 */
	private static APIField getAPIField(ParameterMetaData rsMeta, ParameterMetaData rsMeta2, int index) throws SQLException
	{
		APIField apiField = getAPIField(rsMeta, index);

		int length = rsMeta.getPrecision(index);
		int length2 = rsMeta2.getPrecision(index);
		if (length > length2)
		{
			apiField.setSize(String.valueOf(length2 + SQL_FIELD_LENGTH_LIKE));
		}

		return apiField;
	}

	/**
	 * Return the APIField representation of a SQL parameter (a key field)
	 * 
	 * @param rsMeta
	 *            A ParameterMetaData instance
	 * @param index
	 *            The parameter index to generate the APIField for
	 * 
	 * @return the API Field
	 * @throws SQLException
	 */
	private static APIField getAPIField(ParameterMetaData rsMeta, int index) throws SQLException
	{
		APIField apiField = null;
		String name = EquationStandardQueryList.PARM_PREFIX + index;
		// The label is the field name. Need to load DB definitions and use that label.
		String label = name;
		apiField = new APIField(name, label, label);

		apiField.setDataType(SQLToolbox.sqlDataTypeToEqDataType(rsMeta.getParameterType(index)));

		// get the length and ensure will not exceed the maximum
		int length = rsMeta.getPrecision(index);
		apiField.setSize(String.valueOf(length));

		// get the decimal place for numeric
		if (apiField.getDataType().equals(EqDataType.TYPE_PACKED) || apiField.getDataType().equals(EqDataType.TYPE_ZONED))
		{
			apiField.setDecimals(String.valueOf(rsMeta.getScale(index)));
		}

		apiField.setKey(true);
		//
		// apiField.setType("");
		return apiField;
	}

	/**
	 * Return the APIField representation of a SQL result column
	 * 
	 * @param rsMeta
	 *            A ResultSetMetaData instance
	 * @param column
	 *            The column index to generate the APIField for
	 * 
	 * @return the API Field.
	 * @throws SQLException
	 */
	private static APIField getAPIField(ResultSetMetaData rsMeta, int column) throws SQLException
	{
		APIField apiField = null;
		String name = rsMeta.getColumnName(column);
		// The label is the field name. Need to load DB definitions and use that label.
		String label = rsMeta.getColumnLabel(column);
		apiField = new APIField(name, label, label);

		apiField.setDataType(SQLToolbox.sqlDataTypeToEqDataType(rsMeta.getColumnType(column)));

		apiField.setSize(String.valueOf(rsMeta.getPrecision(column)));
		if (apiField.getDataType().equals(EqDataType.TYPE_PACKED) || apiField.getDataType().equals(EqDataType.TYPE_ZONED))
		{
			apiField.setDecimals(String.valueOf(rsMeta.getScale(column)));
		}

		apiField.setKey(false);
		apiField.setRepeatingGroup("");
		// TODO: Obtain type
		// apiField.setType("");
		return apiField;
	}

	/**
	 * Add an API field to an API field set
	 * 
	 * @param apiFieldSet
	 *            - the API field set
	 * @param apiField
	 *            - the API field
	 * 
	 * @return the updated API field
	 * 
	 * @throws EQException
	 */
	public static APIField addAPIField(APIFieldSet apiFieldSet, APIField apiField) throws EQException
	{
		apiFieldSet.addAPIField(apiField);
		return apiField;
	}

	/**
	 * Return the API Field Set representation of a function
	 * 
	 * @param session
	 *            - the Equation Standard session
	 * @param id
	 *            - API field set id
	 * @param optionId
	 *            - option id of the function
	 * @param pgmRoot
	 *            - program root of the function
	 * @param description
	 *            - description of the field set
	 * @param mode
	 *            - function mode
	 * @param repeatingId
	 *            - the repeating group id to be populated
	 * @param loadMode
	 *            - flag on how to populate the specified repeating group id
	 * @param joinAPIFileds
	 *            - list of API fields for comparison
	 * @param joinInputFields
	 *            - list of input fields for comparison
	 * 
	 * @return the API Field Set
	 * 
	 * @throws EQException
	 */
	public static APIFieldSet getAPIFieldSet(EquationStandardSession session, String id, String optionId, String pgmRoot,
					String description, String mode, RepeatingDetails repeatingDetails) throws EQException
	{
		IGAERecordDao gaeRecordDao = getGAERecordDao(session);
		String whereClause = "GAEFID='" + optionId + "'";
		Hashtable<String, GAERecordDataModel> gaeRecords = gaeRecordDao.getGAERecordKeyedByApiId(whereClause);
		GAERecordDataModel gaeRecord = gaeRecords.get(optionId);

		if (gaeRecord == null)
		{
			gaeRecord = new GAERecordDataModel();
		}

		// Get the (fixed) journal file field definitions
		EqDS_DSPFFD fixedDSPFFD = new EqDS_DSPFFD(gaeRecord.getHeaderJournalFileName(), session);

		// create the field set
		APIFieldSet apiFieldSet = new APIFieldSet(id, optionId, description);
		apiFieldSet.setRoot(pgmRoot);
		apiFieldSet.setKeyFields(gaeRecord.getKeys());
		apiFieldSet.setMode(mode);

		// Consider list enquiry fields:
		EqDS_DSPFFD listDSPFFD = null;

		if (!gaeRecord.getDetailJournalFileName().equals(""))
		{
			listDSPFFD = new EqDS_DSPFFD(gaeRecord.getDetailJournalFileName(), session);
		}
		boolean repeatingGroup = false;
		if (gaeRecord.getType().equals(GAERecordDataModel.TYPE_LIST_ENQUIRY_API))
		{
			repeatingGroup = true;
			apiFieldSet.setRepeatingGroup(repeatingDetails.getRepeatingGroupId());
			apiFieldSet.setRepeatingGroupLoadMode(repeatingDetails.getJoinType());
			apiFieldSet.setRepeatingGroupLoadJoinAPIField(repeatingDetails.getJoinAPIFields());
			apiFieldSet.setRepeatingGroupLoadJoinInputField(repeatingDetails.getInputFields());
		}

		// Set the APIFieldSet type according to whether this is a Service, Input or Enquiry transaction:
		if (EquationStandardTransaction.EDF_ROOT.equals(gaeRecord.getProgramRoot()))
		{
			apiFieldSet.setType(IEquationStandardObject.TYPE_SERVICE);
		}
		else if (GAERecordDataModel.TYPE_FIXED_ENQUIRY_API.equals(gaeRecord.getType()))
		{
			apiFieldSet.setType(IEquationStandardObject.TYPE_ENQUIRY);
		}
		else if (GAERecordDataModel.TYPE_LIST_ENQUIRY_API.equals(gaeRecord.getType()))
		{
			apiFieldSet.setType(IEquationStandardObject.TYPE_LIST_ENQUIRY);
		}
		else
		{
			apiFieldSet.setType(IEquationStandardObject.TYPE_TRANSACTION);
		}

		// add the fields
		addFieldsToAPIFieldSet(apiFieldSet, gaeRecord, fixedDSPFFD, false);
		if (listDSPFFD != null)
		{
			addFieldsToAPIFieldSet(apiFieldSet, gaeRecord, listDSPFFD, repeatingGroup);
		}

		return apiFieldSet;
	}

	/**
	 * Return the API Field Set representation of a function
	 * 
	 */
	public static APIFieldSet getAPIFieldSet(EquationStandardSession session, String id, String optionId, String pgmRoot,
					String description, String mode) throws EQException
	{
		return getAPIFieldSet(session, id, optionId, pgmRoot, description, mode, new RepeatingDetails());
	}

	/**
	 * Adds fields from an Equation DSPPFD to an APIFieldSet
	 * 
	 * @param apiFieldSet
	 *            The APIFieldSet to add the fields to
	 * @param gaeRecord
	 *            The GAE details (used to check whether to ignore the GZEFC field from the DSPPFD)
	 * @param dsFile
	 *            The Equation DSPPFD
	 * @param repeating
	 *            Whether this set of fields are repeating
	 * @throws EQException
	 */
	public static void addFieldsToAPIFieldSet(APIFieldSet apiFieldSet, GAERecordDataModel gaeRecord, EqDS_DSPFFD dsFile,
					boolean repeating) throws EQException
	{
		// create the fields
		for (Entry<String, EquationFieldDefinition> entry : dsFile.getFieldDefinitions().entrySet())
		{
			String fieldName = entry.getKey();
			if (!fieldName.equals("GZWSID")
							&& !fieldName.equals("GZDIM")
							&& !fieldName.equals("GZTIM")
							&& !fieldName.equals("GZSEQ")
							&& !fieldName.equals("GZIMG")
							&& !fieldName.equals("GSWSID")
							&& !fieldName.equals("GSDIM")
							&& !fieldName.equals("GSTIM")
							&& !fieldName.equals("GSSEQ")

							&& !(EquationStandardTransaction.EDF_ROOT.equals(gaeRecord.getProgramRoot()) && fieldName
											.equals("GZEFC")))
			{
				EquationFieldDefinition fieldDefinition = entry.getValue();
				APIField apiField = getAPIField(fieldDefinition, gaeRecord.getKeys());
				if (repeating)
				{
					apiField.setRepeatingGroup(apiFieldSet.getRepeatingGroup());
				}
				addAPIField(apiFieldSet, apiField);
			}
		}
	}

	/**
	 * Return the API Field Set representation of a function
	 * 
	 * @param session
	 *            - the Equation Standard session
	 * @param id
	 *            - API field set id
	 * @param optionId
	 *            - option id of the function
	 * @param pgmRoot
	 *            - program root of the function
	 * @param gzFile
	 *            - journal file of the function
	 * @param description
	 *            - description of the field set
	 * @param mode
	 *            - function mode
	 * @param determineMode
	 *            - is this load API determines the overall mode of the function?
	 * 
	 * @return the API Field Set
	 * 
	 * @throws EQException
	 */
	public static APIFieldSet getLoadAPIFieldSet(EquationStandardSession session, String id, String optionId, String pgmRoot,
					String gzFile, String description, String mode, boolean determineMode) throws EQException
	{
		Hashtable<String, GAERecordDataModel> gaeRecords;
		IGAERecordDao gaeRecordDao = getGAERecordDao(session);
		String whereClause = "GAEFID='" + optionId + "'";

		gaeRecords = gaeRecordDao.getGAERecordKeyedByApiId(whereClause);
		GAERecordDataModel gaeRecord = gaeRecords.get(optionId);

		if (gaeRecord == null)
		{
			gaeRecord = new GAERecordDataModel();
		}

		// create the field set
		APIFieldSet loadAPIFieldSet = new APIFieldSet(id, optionId, description);
		loadAPIFieldSet.setMode(mode);
		loadAPIFieldSet.setRoot(pgmRoot);
		loadAPIFieldSet.setKeyFields(gaeRecord.getKeys());
		loadAPIFieldSet.setType(IEquationStandardObject.TYPE_TRANSACTION);
		loadAPIFieldSet.setDetermineMode(determineMode);

		// create the fields
		EqDS_DSPFFD dsFile = new EqDS_DSPFFD(gzFile, session);
		Map<String, EquationFieldDefinition> hashMap = dsFile.getFieldDefinitions();
		Iterator<String> fieldNames = hashMap.keySet().iterator();
		while (fieldNames.hasNext())
		{
			String fieldName = fieldNames.next();
			if (!fieldName.equals("GZWSID") && !fieldName.equals("GZDIM") && !fieldName.equals("GZTIM")
							&& !fieldName.equals("GZSEQ") && !fieldName.equals("GZIMG") && !fieldName.equals("GSWSID")
							&& !fieldName.equals("GSDIM") && !fieldName.equals("GSTIM") && !fieldName.equals("GSSEQ"))
			{
				EquationFieldDefinition fieldDefinition = hashMap.get(fieldName);
				APIField apiField = getAPIField(fieldDefinition, gaeRecord.getKeys());
				addAPIField(loadAPIFieldSet, apiField);
			}
		}

		return loadAPIFieldSet;
	}

	/**
	 * Return the API Field Set representation of an enquiry function
	 * 
	 * @param session
	 *            - the Equation Standard session
	 * @param id
	 *            - API field set id
	 * @param optionId
	 *            - option id of the function
	 * @param pgmRoot
	 *            - program root of the function
	 * @param gzFile
	 *            - journal file of the function
	 * @param description
	 *            - description of the field set
	 * @param determineMode
	 *            - is this load API determines the overall mode of the function?
	 * 
	 * @return the API Field Set
	 * 
	 * @throws EQException
	 */
	public static APIFieldSet getEnquiryFieldSet(EquationStandardSession session, String id, String optionId, String pgmRoot,
					String gzFile, String description, boolean determineMode) throws EQException
	{
		APIFieldSet apiFieldSet = getLoadAPIFieldSet(session, id, optionId, pgmRoot, gzFile, description,
						IEquationStandardObject.FCT_ENQ, determineMode);
		apiFieldSet.setType(IEquationStandardObject.TYPE_ENQUIRY);
		return apiFieldSet;
	}

	/**
	 * Return the API Field Set representation of a report
	 * 
	 * @param id
	 *            - API field set id
	 * @param report
	 *            - report mnemonic
	 * @param selection
	 *            - report selection
	 * 
	 * @return the API Field Set
	 * 
	 * @throws EQException
	 */
	public static APIFieldSet getReportFieldSet(String id, String report, String selection) throws EQException
	{
		APIFieldSet apiFieldSet = new APIFieldSet(id, "", "");
		apiFieldSet.setRoot(report);
		apiFieldSet.setKeyFields(selection);
		apiFieldSet.setType(IEquationStandardObject.TYPE_REPORT);
		apiFieldSet.setLabel(report);
		apiFieldSet.setDescription(LanguageResources.getString("Language.ReportRequest"));

		// get a data structure
		EqDS_Report eqds_Report = new EqDS_Report();
		EquationFieldDefinition fd1 = eqds_Report.getFieldDefinition(EqDS_Report.RPT);
		EquationFieldDefinition fd2 = eqds_Report.getFieldDefinition(EqDS_Report.SEL);

		APIField apiField = new APIField(EqDS_Report.RPT, fd1.getFieldLabel(), fd1.getFieldLabel());
		apiField.setDataType(fd1.getFieldDataTypeString());
		apiField.setSize(String.valueOf(fd1.getFieldLength()));
		if (EqDataType.isNumeric(apiField.getDataType()))
		{
			apiField.setDecimals(String.valueOf(fd1.getFieldDecimal()));
		}
		apiFieldSet.addAPIField(apiField);

		apiField = new APIField(EqDS_Report.SEL, fd2.getFieldLabel(), fd2.getFieldLabel());
		apiField.setDataType(fd2.getFieldDataTypeString());
		apiField.setSize(String.valueOf(fd2.getFieldLength()));
		if (EqDataType.isNumeric(apiField.getDataType()))
		{
			apiField.setDecimals(String.valueOf(fd2.getFieldDecimal()));
		}
		apiFieldSet.addAPIField(apiField);

		return apiFieldSet;
	}

	/**
	 * Return the API Field Set representation of a function
	 * 
	 * @param session
	 *            - the Equation Standard session
	 * @param id
	 *            - API field set id
	 * @param pvName
	 *            - the PV name
	 * @param decode
	 *            - the PV decode
	 * @param blankAllowed
	 *            - whether blank allowed?
	 * @param newCode
	 *            - whether code must be existing or new?
	 * @param description
	 *            - description of the field set
	 * @param determineMode
	 *            - is this load API determines the overall mode of the function?
	 * 
	 * @return the API Field Set
	 * 
	 * @throws EQException
	 */
	public static APIFieldSet getLoadPVFieldSet(EquationStandardSession session, String id, String pvName, String decode,
					boolean blankAllowed, String newCode, String description, boolean determineMode) throws EQException
	{
		APIFieldSet apiFieldSet = new APIFieldSet(id, pvName, description);
		apiFieldSet.setType(IEquationStandardObject.TYPE_PV);
		apiFieldSet.setMode(IEquationStandardObject.FCT_ENQ);
		apiFieldSet.setDecode(decode);
		apiFieldSet.setDetermineMode(determineMode);

		// construct the PV field set
		PVFieldSet pvFieldSet = getPVFieldSet(session, pvName, decode, blankAllowed, newCode);
		for (int i = 0; i < pvFieldSet.getPVFields().size(); i++)
		{
			PVField pvField = pvFieldSet.getPVFields().get(i);
			APIField apiField = new APIField(pvField.getId(), pvField.getLabel(), pvField.getDescription());
			apiField.setDataType(pvField.getDataType());
			apiField.setSize(pvField.getSize());
			apiField.setDecimals(pvField.getDecimals());
			apiFieldSet.addAPIField(apiField);
		}

		return apiFieldSet;
	}

	/**
	 * Return the API Field Set representation of a function
	 * 
	 * @param session
	 *            - the Equation Standard session
	 * @param id
	 *            - API field set id
	 * @param tableId
	 *            - table name
	 * @param description
	 *            - description of the field set
	 * @param keys
	 *            - the list of keys of the table
	 * @param root
	 *            - the index or logical file
	 * @param mode
	 *            - the mode
	 * @param determineMode
	 *            - is this load API determines the overall mode of the function?
	 * 
	 * @return the API Field Set
	 * 
	 * @throws EQException
	 */
	public static APIFieldSet getTableFieldSet(EquationStandardSession session, String id, String tableId, String description,
					String keys, String root, String mode, boolean determineMode, boolean globalTable) throws EQException
	{
		// create the field set
		APIFieldSet apiFieldSet = new APIFieldSet(id, tableId, description);
		apiFieldSet.setKeyFields(keys);
		apiFieldSet.setMode(mode);
		apiFieldSet.setRoot(root);
		apiFieldSet.setType(IEquationStandardObject.TYPE_TABLE);
		apiFieldSet.setDetermineMode(determineMode);
		apiFieldSet.setGlobalLibrary(globalTable);

		EqDS_DSPFFD dsFile = new EqDS_DSPFFD(tableId, session, globalTable);
		Map<String, EquationFieldDefinition> hashMap = dsFile.getFieldDefinitions();
		Iterator<String> fieldNames = hashMap.keySet().iterator();
		while (fieldNames.hasNext())
		{
			String fieldName = fieldNames.next();
			EquationFieldDefinition fieldDefinition = hashMap.get(fieldName);
			APIField apiField = getAPIField(fieldDefinition, keys);
			addAPIField(apiFieldSet, apiField);
		}
		return apiFieldSet;
	}

	/**
	 * Returns a API Field Set that represents a database table.
	 * <p>
	 * The returned API field Set is not ready for adding directly to the function; it is intended to allow the user to select
	 * required fields for example. Therefore it will not have an API id or other specific details.
	 * 
	 * @param session
	 *            - the Equation Standard session
	 * @param tableId
	 *            - table name
	 * 
	 * @return the API Field Set
	 * 
	 * @throws EQException
	 */
	public static APIFieldSet getTableFieldSet(EquationStandardSession session, String tableId) throws EQException
	{
		String keys = session.getUnit().getUniqueKeys("KFIL", tableId, false);
		String root = session.getUnit().getTableForIndex("KFIL", tableId, true);

		// create the field set
		APIFieldSet apiFieldSet = new APIFieldSet();
		apiFieldSet.setKeyFields(keys);
		apiFieldSet.setRoot(root);
		apiFieldSet.setType(IEquationStandardObject.TYPE_TABLE);

		// create the fields
		EqDS_DSPFFD dsFile = new EqDS_DSPFFD(tableId, session);
		Map<String, EquationFieldDefinition> hashMap = dsFile.getFieldDefinitions();
		Iterator<String> fieldNames = hashMap.keySet().iterator();
		while (fieldNames.hasNext())
		{
			String fieldName = fieldNames.next();
			EquationFieldDefinition fieldDefinition = hashMap.get(fieldName);
			APIField apiField = getAPIField(fieldDefinition, keys);
			addAPIField(apiFieldSet, apiField);
		}
		return apiFieldSet;
	}

	/**
	 * Return the API Field Set representation of a database table when imported as a list.
	 * 
	 * @param session
	 *            - the Equation Standard session
	 * @param id
	 *            - API field set id
	 * @param tableId
	 *            - table name
	 * @param description
	 *            - description of the field set
	 * @param keys
	 *            - the list of keys of the table
	 * @param root
	 *            - the index or logical file
	 * @param mode
	 *            - the mode
	 * 
	 * @return the API Field Set
	 * 
	 * @throws EQException
	 */
	public static APIFieldSet getTableFieldSetAsList(EquationStandardSession session, String id, String tableId,
					String description, String keys, String root, String mode, RepeatingDetails repeatingDetails)
					throws EQException
	{
		// create the field set
		APIFieldSet apiFieldSet = new APIFieldSet(id, tableId, description);
		apiFieldSet.setKeyFields(keys);
		apiFieldSet.setMode(mode);
		apiFieldSet.setRoot(root);
		apiFieldSet.setType(IEquationStandardObject.TYPE_LIST_TABLE);
		apiFieldSet.setDetermineMode(false);

		// Repeating details
		apiFieldSet.setRepeatingGroup(repeatingDetails.getRepeatingGroupId());
		apiFieldSet.setRepeatingGroupLoadMode(repeatingDetails.getJoinType());
		apiFieldSet.setRepeatingGroupLoadJoinAPIField(repeatingDetails.getJoinAPIFields());
		apiFieldSet.setRepeatingGroupLoadJoinInputField(repeatingDetails.getInputFields());

		// create the fields
		EqDS_DSPFFD dsFile = new EqDS_DSPFFD(tableId, session);
		Map<String, EquationFieldDefinition> hashMap = dsFile.getFieldDefinitions();
		Iterator<String> fieldNames = hashMap.keySet().iterator();
		while (fieldNames.hasNext())
		{
			String fieldName = fieldNames.next();
			EquationFieldDefinition fieldDefinition = hashMap.get(fieldName);
			APIField apiField = getAPIField(fieldDefinition, keys);
			addAPIField(apiFieldSet, apiField);
			if (!apiField.isKey())
			{
				apiField.setRepeatingGroup(apiFieldSet.getRepeatingGroup());
			}
		}
		return apiFieldSet;
	}

	/**
	 * Return the API Field Set representation of a database table when imported as a list. This is just in test stub - do not
	 * remove
	 * 
	 * @param session
	 *            - the Equation Standard session
	 * @param id
	 *            - API field set id
	 * @param tableId
	 *            - table name
	 * @param description
	 *            - description of the field set
	 * @param keys
	 *            - the list of keys of the table
	 * @param root
	 *            - the index or logical file
	 * @param mode
	 *            - the mode
	 * @param determineMode
	 *            - is this load API determines the overall mode of the function?
	 * @param repeatingId
	 *            - the repeating group id to be populated
	 * @param loadMode
	 *            - flag on how to populate the specified repeating group id
	 * @param joinAPIFileds
	 *            - list of API fields for comparison
	 * @param joinInputFields
	 *            - list of input fields for comparison
	 * 
	 * @return the API Field Set
	 * 
	 * @throws EQException
	 */
	public static APIFieldSet getTableFieldSetAsList(EquationStandardSession session, String id, String tableId,
					String description, String keys, String root, String mode, boolean determineMode, String repeatingId,
					int loadMode, String joinAPIFields, String joinInputFields) throws EQException
	{
		// create the field set
		APIFieldSet apiFieldSet = new APIFieldSet(id, tableId, description);
		apiFieldSet.setKeyFields(keys);
		apiFieldSet.setMode(mode);
		apiFieldSet.setRoot(root);
		apiFieldSet.setType(IEquationStandardObject.TYPE_LIST_TABLE);
		apiFieldSet.setDetermineMode(determineMode);

		// Consider list enquiry fields:
		apiFieldSet.setRepeatingGroup(repeatingId);
		apiFieldSet.setRepeatingGroupLoadMode(loadMode);
		apiFieldSet.setRepeatingGroupLoadJoinAPIField(joinAPIFields);
		apiFieldSet.setRepeatingGroupLoadJoinInputField(joinInputFields);

		// create the fields
		EqDS_DSPFFD dsFile = new EqDS_DSPFFD(tableId, session);
		Map<String, EquationFieldDefinition> hashMap = dsFile.getFieldDefinitions();
		Iterator<String> fieldNames = hashMap.keySet().iterator();
		while (fieldNames.hasNext())
		{
			String fieldName = fieldNames.next();
			EquationFieldDefinition fieldDefinition = hashMap.get(fieldName);
			APIField apiField = getAPIField(fieldDefinition, keys);
			apiField.setRepeatingGroup(repeatingId);
			addAPIField(apiFieldSet, apiField);
		}

		return apiFieldSet;
	}

	/**
	 * Return the APIFieldSet representation of a SQL query result
	 * <p>
	 * 
	 * @param session
	 *            - the Equation Standard session
	 * @param id
	 *            - The Id for the new APIFieldSet
	 * @param description
	 *            - description of the field set
	 * @param sql
	 *            - the SQL query
	 * 
	 * @return the APIFieldSet
	 * 
	 * @throws EQException
	 */
	public static APIFieldSet getSqlFieldSet(EquationStandardSession session, String id, String description, String sql)
					throws EQException
	{
		// create the field set
		// What to use for the label?
		APIFieldSet apiFieldSet = new APIFieldSet(id, description, description);
		// apiFieldSet.setKeyFields(keys);
		// apiFieldSet.setMode(mode);
		apiFieldSet.setRoot(sql);
		apiFieldSet.setType(IEquationStandardObject.TYPE_LIST_QUERY);

		// create the fields
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		try
		{
			ps = session.getConnection().prepareStatement(sql);
			ps2 = session.getConnection().prepareStatement(sql.toLowerCase().replaceAll("like", "="));

			// Parameter fields are currently assumed to be key fields
			ParameterMetaData meta = ps.getParameterMetaData();
			ParameterMetaData meta2 = ps2.getParameterMetaData();

			for (int i = 1; i <= meta.getParameterCount(); i++)
			{
				APIField apiField = getAPIField(meta, meta2, i);
				addAPIField(apiFieldSet, apiField);
			}

			// All the result columns are added as repeating fields
			ResultSetMetaData rsMeta = ps.getMetaData();
			for (int i = 1; i <= rsMeta.getColumnCount(); i++)
			{
				APIField apiField = getAPIField(rsMeta, i);
				apiField.setRepeatingGroup(id);
				addAPIField(apiFieldSet, apiField);
			}
		}
		catch (SQLException e)
		{
			throw new EQException(e);
		}
		finally
		{
			SQLToolbox.close(ps);
			SQLToolbox.close(ps2);
		}

		return apiFieldSet;
	}

	/**
	 * Return the API Field Set representation of a SQL query result
	 * 
	 * @param session
	 *            - the Equation Standard session
	 * @param id
	 *            - API field set id
	 * @param description
	 *            - description of the field set
	 * @param sql
	 *            - the sql query
	 * @param mode
	 *            - the mode
	 * @param determineMode
	 *            - is this load API determines the overall mode of the function?
	 * @param repeatingId
	 *            - the repeating group id to be populated
	 * @param loadMode
	 *            - flag on how to populate the specified repeating group id
	 * @param joinAPIFileds
	 *            - list of API fields for comparison
	 * @param joinInputFields
	 *            - list of input fields for comparison
	 * 
	 * @return the API Field Set
	 * 
	 * @throws EQException
	 */
	public static APIFieldSet getSqlFieldSet(EquationStandardSession session, String id, String description, String sql,
					String mode, boolean determineMode, String repeatingId, int loadMode, String joinAPIFields,
					String joinInputFields) throws EQException
	{
		// create the field set
		APIFieldSet apiFieldSet = getSqlFieldSet(session, id, description, sql);

		apiFieldSet.setRepeatingGroup(repeatingId);
		apiFieldSet.setRepeatingGroupLoadMode(loadMode);
		apiFieldSet.setRepeatingGroupLoadJoinAPIField(joinAPIFields);
		apiFieldSet.setRepeatingGroupLoadJoinInputField(joinInputFields);

		return apiFieldSet;
	}

	/**
	 * Returns the list of fields from an SQL API on which the SQL parameters are based
	 * 
	 * @param apiFieldSet
	 *            - the SQL API field set
	 * 
	 * @return the list of API fields
	 */
	public static List<String> getSQLParamFields(APIFieldSet apiFieldSet)
	{
		ArrayList<String> results = new ArrayList<String>();

		// Get SQL
		char[] sqlChars = apiFieldSet.getRoot().toCharArray();

		// Blank out anything in quotes to avoid problem characters like '?' and '='
		boolean quotes = false;
		for (int i = 0; i < sqlChars.length; i++)
		{
			if (sqlChars[i] == '\'')
			{
				quotes = !quotes;
			}
			else if (quotes)
			{
				sqlChars[i] = 'X';
			}
		}

		// convert to an upper case string
		String sql = new String(sqlChars).toUpperCase();

		// Simplify the SQL by replacing operators with "="
		String[] operators = { " LIKE ", " IN ", "<>", "<=", "!<", "!>", "!=", ">=", "¬<", "¬>", "¬=", "<", ">" };
		for (String op : operators)
		{
			sql = sql.replaceAll(op, "=");
		}
		sql = sql.replaceAll(" IN\\?", " =?");
		sql = sql.replaceAll("\\?IN ", "?= ");
		sql = sql.replaceAll(" LIKE\\?", " =?");
		sql = sql.replaceAll("\\?LIKE ", "?= ");

		// The BETWEEN operator generates two parameters,
		// replace (e.g.) 'GFCUS BETWEEN ? AND ?' with 'GFCUS = ? AND GFCUS = ?'
		sql = sql.replaceAll("\\sNOT\\s+BETWEEN", " BETWEEN");
		int index = 0;
		while ((index = sql.indexOf("BETWEEN")) > 0)
		{
			String[] words = sql.substring(0, index).split("\\s");
			int andIndex = sql.indexOf("AND", index);
			if (andIndex > 0)
			{
				sql = sql.substring(0, andIndex + 3) + " " + words[words.length - 1] + " = " + sql.substring(andIndex + 3);
				sql = sql.replaceFirst("BETWEEN", "=");
			}
		}

		// Extract the API fields that the parameters are based on
		String[] words;
		String[] sqlSections = sql.split("=");
		for (int i = 0; i < sqlSections.length; i++)
		{
			if (i > 0 && sqlSections[i].trim().startsWith("?"))
			{
				words = sqlSections[i - 1].trim().split("[ \\(\\)]");
				if (words.length > 0)
				{
					results.add(words[words.length - 1]);
				}
			}
			if (i + 1 < sqlSections.length && sqlSections[i].trim().endsWith("?"))
			{
				words = sqlSections[i + 1].trim().split("[ \\(\\)]");
				if (words.length > 0)
				{
					results.add(words[0]);
				}
			}
		}

		return results;
	}
	/**
	 * Return the API Field Set representation of a function
	 * 
	 * @param session
	 *            - the Equation Standard session
	 * @param id
	 *            - API field set id
	 * @param optionId
	 *            - option id of the function
	 * @param description
	 *            - description of the function
	 * @param mode
	 *            - function mode
	 * @param determineMode
	 *            - is this load API determines the overall mode of the function?
	 * 
	 * @return the API Field Set
	 * 
	 * @throws EQException
	 */
	public static APIFieldSet getServiceFieldSet(EquationStandardSession session, String id, String optionId, String description,
					String mode, boolean determineMode) throws EQException
	{
		// create the field set
		APIFieldSet apiFieldSet = new APIFieldSet(id, optionId, description);
		apiFieldSet.setMode(mode);
		apiFieldSet.setType(IEquationStandardObject.TYPE_SERVICE);
		apiFieldSet.setDetermineMode(determineMode);

		// get the main function
		Function function = XMLToolbox.getXMLToolbox().getFunction(session, optionId, true);

		// loop all the input fields
		List<InputFieldSet> fieldSets = function.getInputFieldSets();
		for (int i = 0; i < fieldSets.size(); i++)
		{
			InputFieldSet fieldSet = fieldSets.get(i);
			List<InputField> fields = fieldSet.getInputFields();
			for (int j = 0; j < fields.size(); j++)
			{
				InputField field = fields.get(j);
				EquationFieldDefinition fieldDefinition = new EquationFieldDefinition(field.getId(), 0, 0, Toolbox.parseInt(field
								.getSize(), 0), Toolbox.parseInt(field.getDecimals(), 0), field.getLabel(), field.getDataType());
				String key = "";
				if (field.isKey())
				{
					key = field.getId();
				}
				APIField apiField = getAPIField(fieldDefinition, key);
				addAPIField(apiFieldSet, apiField);
			}
		}

		// return the API field set
		return apiFieldSet;
	}

	/**
	 * Generate a display label
	 * 
	 * @param id
	 *            - field id
	 * @param label
	 *            - field label
	 * @param desc
	 *            - field description
	 * 
	 * @return the DisplayLabel
	 */
	public static DisplayLabel getDisplayLabel(String id, String label, String desc)
	{
		DisplayLabel displayLabel = new DisplayLabel(id, label, desc);
		return displayLabel;
	}

	/**
	 * Add a work field
	 * 
	 * @param function
	 *            - the function
	 * @param id
	 *            - work field id
	 * @param label
	 *            - work field label
	 * @param desc
	 *            - work field description
	 * @param dataType
	 *            - field data type
	 * @param length
	 *            - length of the field
	 * @param decimals
	 *            - a <code>String</code> containing the number of decimals
	 * 
	 * @return the newly added <code>WorkField</code> instance
	 */
	public static WorkField addWorkField(Function function, String id, String label, String desc, String type, String length,
					String decimal)
	{
		WorkField workField = new WorkField(id, label, desc);
		workField.setDataType(type);
		workField.setSize(length);
		workField.setDecimals(decimal);
		function.addWorkField(workField);
		return workField;
	}

	/**
	 * Get the number of inner classes for a given class of the GAX
	 * 
	 * @param session
	 *            - a session in an Equation unit
	 * @param optionId
	 *            the OptionId
	 * @param subField
	 *            The subField (blank for the main class)
	 * 
	 * 
	 * @return An int containing the number of inner classes
	 */
	public static int getNumberInnerClasses(EquationStandardSession session, String optionId, String subField)
	{
		IGAZRecordDao dao = daoFactory.getGAZDao(session, new GAZRecordDataModel());
		List<AbsRecord> records = dao.getRecordBy("GAZOID='" + optionId + "' AND GAZPVN='" + subField + "' AND GAZTYP ='"
						+ GAZRecordDataModel.TYP_BFTYPEDESCRIPTOR + "' ");
		return records.size() - 1;
	}

	/**
	 * Gets the set of repeating data classes for a given class of the GAX
	 * 
	 * @param session
	 *            - a session in an Equation unit
	 * @param optionId
	 *            the OptionId
	 * 
	 * @return a List of GAZRecordDataModel instances
	 */
	@SuppressWarnings("unchecked")
	public static List<GAZRecordDataModel> getOptionListClassTypes(EquationStandardSession session, String optionId)
	{
		IGAZRecordDao dao = daoFactory.getGAZDao(session, new GAZRecordDataModel());
		List<AbsRecord> records = dao.getRecordBy("GAZOID='" + optionId + "' AND (GAZPVN <> '' AND GAZPVN <> '" + optionId
						+ "') AND GAZTYP ='" + GAZRecordDataModel.TYP_BFTYPE + "' ");
		return (List) records;
	}

	/**
	 * Gets the set of GAZRecords for the inner classes of a given option id
	 * 
	 * @param session
	 *            - a session in an Equation unit
	 * @param optionId
	 *            the OptionId
	 * @param subField
	 *            The subField (blank for the main class)
	 * 
	 * @return a List of GAZRecordDataModel instances
	 */
	@SuppressWarnings("unchecked")
	public static List<GAZRecordDataModel> getOptionInnerClasses(EquationStandardSession session, String optionId, String subField)
	{
		IGAZRecordDao dao = daoFactory.getGAZDao(session, new GAZRecordDataModel());
		List<AbsRecord> records = dao.getRecordBy("GAZOID='" + optionId + "' AND GAZPVN='" + subField + "' AND GAZTYP ='"
						+ GAZRecordDataModel.TYP_BFTYPEDESCRIPTOR + "' ");
		return (List) records;
	}

	/**
	 * Convert an API field set into a PV field set
	 * 
	 * @param apiFieldSet
	 *            - the API field set
	 * 
	 * @return the equivalent PV field set
	 */
	public static PVFieldSet cvtAPIToPVFieldSet(APIFieldSet apiFieldSet)
	{
		// create the PV field set
		PVFieldSet pvFieldSet = new PVFieldSet(apiFieldSet);
		pvFieldSet.setType(cvtAPITypeToPVType(apiFieldSet.getType()));
		pvFieldSet.setDecode(apiFieldSet.getDecode());
		pvFieldSet.setNewField(apiFieldSet.getNewField());
		pvFieldSet.setRoot(apiFieldSet.getRoot());
		pvFieldSet.setKeyFields(apiFieldSet.getKeyFields());

		// copy the fields
		for (APIField existingField : apiFieldSet.getAPIFields())
		{
			PVField newField = new PVField(existingField);
			newField.setParent(apiFieldSet);
			try
			{
				pvFieldSet.addPVField(newField);
			}
			catch (EQException e)
			{
				throw new RuntimeException(e);
			}
		}

		return pvFieldSet;
	}

	/**
	 * Convert an API Field into a PV field
	 * 
	 * @param apiField
	 *            - the API field
	 * 
	 * @return the equivalent PV field
	 */
	public static PVField cvtAPIToPVField(APIField apiField)
	{
		PVField pvField = new PVField(apiField);
		return pvField;
	}

	/**
	 * Convert an PV field set into a API field set
	 * 
	 * @param pvFieldSet
	 *            - the PV ield set
	 * 
	 * @return the equivalent API field set
	 */
	public static APIFieldSet cvtPVToAPIFieldSet(PVFieldSet pvFieldSet)
	{
		// create the API field set
		APIFieldSet apiFieldSet = new APIFieldSet(pvFieldSet);

		if (pvFieldSet.getType().trim().length() == 0)
		{
			apiFieldSet = new APIFieldSet(pvFieldSet.getId(), pvFieldSet);
		}
		else
		{
			apiFieldSet.setType(cvtPVTypetoAPIType(pvFieldSet.getType()));
			apiFieldSet.setRoot(pvFieldSet.getRoot());
			apiFieldSet.setDecode(pvFieldSet.getDecode());
			apiFieldSet.setNewField(pvFieldSet.getNewField());
			apiFieldSet.setKeyFields(pvFieldSet.getKeyFields());
		}

		// copy the fields
		for (PVField existingField : pvFieldSet.getPVFields())
		{
			APIField newField = new APIField(existingField);
			newField.setParent(apiFieldSet);
			try
			{
				apiFieldSet.addAPIField(newField);
			}
			catch (EQException e)
			{
				throw new RuntimeException(e);
			}
		}

		return apiFieldSet;
	}

	/**
	 * Convert an API Field into a PV field
	 * 
	 * @param apiField
	 *            - the API field
	 * 
	 * @return the equivalent PV field
	 */
	public static APIField cvtPVToAPIField(PVField pvField)
	{
		APIField apiField = new APIField(pvField);
		return apiField;
	}

	/**
	 * Convert a PV type to an API type
	 * 
	 * @param type
	 *            - the PV type
	 * 
	 * @return the equivalent API type
	 */
	public static String cvtPVTypetoAPIType(String type)
	{
		// blank PV type, then this is a PV
		if (type.trim().length() == 0)
		{
			return IEquationStandardObject.TYPE_PV;
		}

		// input transaction 2, then return the type transaction
		else if (type.equals(IEquationStandardObject.TYPE_TRANSACTION2))
		{
			return IEquationStandardObject.TYPE_TRANSACTION;
		}

		// same type
		else
		{
			return type;
		}
	}

	/**
	 * Convert an API type to a PV type
	 * 
	 * @param type
	 *            - the API type
	 * 
	 * @return the equivalent PV type
	 */
	public static String cvtAPITypeToPVType(String type)
	{
		// blank API type, then this is an input transaction
		if (type.trim().length() == 0)
		{
			return IEquationStandardObject.TYPE_TRANSACTION2;
		}

		// same type
		else
		{
			return type;
		}
	}
	/**
	 * Add API fields to the PV
	 * 
	 * @param promptValidate
	 * @param session
	 */
	public static void addAPIFields(EquationStandardSession session, EquationPVMetaData promptValidate, String promptValidateId,
					String tableId)
	{
		try
		{
			String keys = session.getUnit().getUniqueKeys("KFIL", tableId, false);
			String root = session.getUnit().getTableForIndex("KFIL", tableId, true);

			APIFieldSet apiFieldSet = FunctionToolbox.getTableFieldSet(session, promptValidateId, tableId, "PROMPT", keys, root,
							"", false, promptValidate.isGlobalLibraryPrompt());
			int index = 0;
			for (APIField field : apiFieldSet.getAPIFields())
			{
				int fieldSize = Integer.parseInt(field.getSize());
				int decimals = 0;
				if (!field.getDecimals().equals(""))
				{
					decimals = Integer.parseInt(field.getDecimals());
				}
				EquationPVFieldMetaData pvField = new EquationPVFieldMetaData(promptValidateId, field.getId(), field
								.getDescription(), field.getDataType(), "B", fieldSize, index, decimals, field.getDescription(),
								field.getId(), false);
				pvField.setKey(field.isKey());
				index += fieldSize;
				promptValidate.addFieldMetaData(pvField);
				if (pvField.isKey() && promptValidate.getPvDvals().isEmpty())
				{
					List<String> dval = new ArrayList<String>();
					dval.add(pvField.getId());
					promptValidate.setPvDvals(dval);
					promptValidate.setPvOvals(dval);
				}
				promptValidate.addHdrName(field.getId());
			}
		}
		catch (EQException e)
		{
			LOG.error(e);
		}
	}

	/**
	 * Retrieves the reusable text based on the key, owner, type and language
	 * 
	 * @param session
	 * @param owner
	 * @param language
	 * @param key
	 * @param type
	 * @return
	 */
	public static String getReuseableText(EquationStandardSession session, String owner, String language, String key, String type)
	{
		IHBXRecordDao hBXRecordDao = daoFactory.getHBXDao(session, new HBXRecordDataModel());
		String whereClause = "HBXTYP LIKE '" + type + "' AND HBXOWN = '" + owner + "' AND HBXLNM = '" + language
						+ "' AND HBXKEY = '" + key + "'";
		List<AbsRecord> records = hBXRecordDao.getRecordBy(whereClause);

		if (records == null || records.isEmpty())
		{
			return key;
		}

		return ((HBXRecordDataModel) records.get(0)).getText();
	}

	// public static boolean isFoundInHBXPF(List<AbsRecord> hbxRecords, String type, String owner, List<String> baseLanguageList,
	// String prefix, String key)
	public static boolean isFoundInHBXPF(Map<String, HBXRecordDataModel> hbxRecords, String type, String owner,
					List<String> baseLanguageList, String prefix, String key)
	{
		boolean isFound = false;
		HBXRecordDataModel hbxRecordDataModel = null;

		if (type.equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE) && !key.equalsIgnoreCase(""))
		{
			/*
			 * for (int x = 0; x < hbxRecords.size(); x++) { hbxRecordDataModel = (HBXRecordDataModel) hbxRecords.get(x); for
			 * (String baseLanguage : baseLanguageList) { if (hbxRecordDataModel.getOwner().equalsIgnoreCase(owner) &&
			 * hbxRecordDataModel.getLanguageCode().equalsIgnoreCase(baseLanguage) &&
			 * hbxRecordDataModel.getType().equalsIgnoreCase(prefix) && hbxRecordDataModel.getKey().equalsIgnoreCase(key)) { isFound
			 * = true; break; } }
			 * 
			 * }
			 */

			Iterator i = hbxRecords.keySet().iterator();

			while (i.hasNext())
			{
				String mapKey = (String) i.next();

				for (String baseLanguage : baseLanguageList)
				{
					if (key.equalsIgnoreCase(new StringBuffer().append(owner).append(baseLanguage).append(prefix).append(key)
									.toString()))
					{
						isFound = true;
						break;
					}
				}
			}
		}
		else if (type.equalsIgnoreCase(Element.TEXT_VALUE_REUSABLE_REFERENCE) && !key.equalsIgnoreCase(""))
		{
			/*
			 * for (int x = 0; x < hbxRecords.size(); x++) { hbxRecordDataModel = (HBXRecordDataModel) hbxRecords.get(x); for
			 * (String baseLanguage : baseLanguageList) { if ((hbxRecordDataModel.getOwner().equalsIgnoreCase(TextBean.OWNER_MISYS)
			 * || hbxRecordDataModel.getOwner() .equalsIgnoreCase(TextBean.OWNER_BANK)) &&
			 * hbxRecordDataModel.getLanguageCode().equalsIgnoreCase(baseLanguage) &&
			 * hbxRecordDataModel.getType().equalsIgnoreCase(prefix) && hbxRecordDataModel.getKey().equalsIgnoreCase(key)) { isFound
			 * = true; break; } } }
			 */

			Iterator i = hbxRecords.keySet().iterator();

			while (i.hasNext())
			{
				String mapKey = (String) i.next();

				for (String baseLanguage : baseLanguageList)
				{
					if ((key.equalsIgnoreCase(new StringBuffer().append(TextBean.OWNER_MISYS).append(baseLanguage).append(type)
									.append(key).toString()))
									|| (key.equalsIgnoreCase(new StringBuffer().append(TextBean.OWNER_BANK).append(baseLanguage)
													.append(type).append(key).toString())))
					{
						isFound = true;
						break;
					}
				}
			}
		}
		else if (type.equalsIgnoreCase(Element.TEXT_VALUE_TEXT) || type.equalsIgnoreCase(Element.TEXT_VALUE_LINE))
		{
			isFound = true;
		}
		if ((type.equalsIgnoreCase(Element.TEXT_VALUE_REUSABLE_REFERENCE) || type.equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
						&& (key.equalsIgnoreCase("") || key.trim().equalsIgnoreCase(Element.DEFAULT_TEXT_VALUE)))
		{
			isFound = true;
		}

		return isFound;
	}

	/**
	 * Validate whether an id is a valid API id
	 * 
	 * @param id
	 *            - the API id
	 * 
	 * @return non-blank if invalid. Null if valid
	 */
	public static String isValidAPIId(String id)
	{
		// not specified
		if (id.trim().length() == 0)
		{
			return "Language.IdentifierMustBeEntered";
		}

		// first character must be A to Z
		char ch = id.charAt(0);
		if ((ch < 'A' || ch > 'Z'))
		{
			return "Language.IdMustStartWithAtoZ";
		}

		// succeeding characters must be A to Z or 0 to 9
		for (int i = 1; i < id.length(); i++)
		{
			char ch1 = id.charAt(i);
			if (ch1 >= 'A' && ch1 <= 'Z')
			{
				// valid
			}
			else if (ch1 >= '0' && ch1 <= '9')
			{
				// valid
			}
			else
			{
				return "Language.IdMustBeAToZor0To9";
			}
		}

		return null;
	}

}