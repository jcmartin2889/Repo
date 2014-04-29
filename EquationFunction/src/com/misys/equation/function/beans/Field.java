package com.misys.equation.function.beans;

import java.util.HashSet;
import java.util.Set;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationPVMetaDataHelper;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.valid.FunctionProblemLocation;
import com.misys.equation.function.beans.valid.IValidation;
import com.misys.equation.function.beans.valid.MessageContainer;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.tools.MappingToolbox;
import com.misys.equation.problems.Message;
import com.misys.equation.problems.ProblemLocation;

public abstract class Field extends Element implements IValidation
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: Field.java 14799 2012-11-05 11:54:35Z williae1 $";
	/** Specifies that Load mapping validation be performed */
	public static final String LOAD_VALIDATION = "LOAD_VALIDATION";
	/** Specifies that Validate mapping validation be performed */
	public static final String VALIDATE_VALIDATION = "VALIDATE_VALIDATION";
	/** Specifies that Update mapping validation be performed */
	public static final String UPDATE_VALIDATION = "UPDATE_VALIDATION";

	/** Indicates the field is not assigned by script or code */
	public static final String ASSIGNMENT_NONE = "";
	/** Indicates that there is an EL Script to assign the field value */
	public static final String ASSIGNMENT_SCRIPT = "1";
	/** Indicates that there is Java exit code to assign the field value */
	public static final String ASSIGNMENT_CODE = "2";

	/** Number of characters allowed in the field size length */
	private static int FIELD_SIZE_LIMIT = 5;
	/** Number of characters allowed in the decimals field */
	private static int DECIMALS_LIMIT = 2;

	/** Maximum size of character fields */
	public static final int MAX_CHARACTER_FIELD_LENGTH = 512;
	/** Maximum size of numeric fields (30 digits) */
	public static final int MAX_NUMERIC_FIELD_LENGTH = 30;

	private String size;
	private String decimals;
	private String dataType;
	private String type;
	private boolean isKey;

	private String updateScript;
	private String loadScript;
	private String validateScript;
	// Primitive and Output scripts needed for Load and Validate
	private String loadPrimitiveScript;
	private String validatePrimitiveScript;
	private String validateOutputScript;

	private String loadAssignment;
	private String updateAssignment;
	private String validateAssignment;
	private String loadPrimitiveAssignment;
	private String validateOutputAssignment;
	private String validatePrimitiveAssignment;

	/** The repeating group Id. Blank if not repeating */
	private String repeatingGroup;

	/**
	 * Set of valid data types (A - Alphanumeric etc)
	 */
	private static final Set<String> dataTypes = getDataTypes();
	private static final Set<String> pvDataTypes = getPVDataTypes();

	/**
	 * Construct a <code>Field</code>
	 */
	public Field()
	{
		super();
		commonInitialization();
	}

	/**
	 * Construct a <code>Field</code>
	 * 
	 * @param id
	 * @param label
	 * @param description
	 */
	public Field(String id, String label, String description)
	{
		super(id, label, description);
		commonInitialization();
	}

	/**
	 * Common initisalisation
	 */
	private void commonInitialization()
	{
		dataType = "";
		size = "";
		decimals = "";
		type = "";
		isKey = false;
		repeatingGroup = "";

		updateScript = "";
		loadScript = "";
		validateScript = "";
		loadPrimitiveScript = "";
		validatePrimitiveScript = "";
		validateOutputScript = "";

		loadAssignment = ASSIGNMENT_NONE;
		updateAssignment = ASSIGNMENT_NONE;
		validateAssignment = ASSIGNMENT_NONE;
		loadPrimitiveAssignment = ASSIGNMENT_NONE;
		validateOutputAssignment = ASSIGNMENT_NONE;
		validatePrimitiveAssignment = ASSIGNMENT_NONE;
	}

	/**
	 * Copy construct a new <code>Field</code>
	 * 
	 * @param field
	 */
	public Field(Field field)
	{
		super(field);
		dataType = field.getDataType();
		size = field.getSize();
		decimals = field.getDecimals();
		type = field.getType();
		isKey = field.isKey();
		repeatingGroup = field.getRepeatingGroup();

		// These fields need to be populated otherwise the field copy process does not work.
		updateScript = field.getUpdateScript();
		loadScript = field.getLoadScript();
		validateScript = field.getValidateScript();
		loadPrimitiveScript = field.getLoadPrimitiveScript();
		validatePrimitiveScript = field.getValidatePrimitiveScript();
		validateOutputScript = field.getValidateOutputScript();

		// These fields need to be populated otherwise the field copy process does not work.
		loadAssignment = field.getLoadAssignment();
		updateAssignment = field.getUpdateAssignment();
		validateAssignment = field.getValidateAssignment();
		loadPrimitiveAssignment = field.getLoadPrimitiveAssignment();
		validateOutputAssignment = field.getValidateOutputAssignment();
		validatePrimitiveAssignment = field.getValidatePrimitiveAssignment();
	}

	/**
	 * Set the field set of field
	 * 
	 * @param fieldSet
	 *            - the field set where the field belongs to
	 */
	public void setParentFieldSet(FieldSet fieldSet)
	{
		setParent(fieldSet);
	}

	/**
	 * Return the field set of the field
	 * 
	 * @return the field set of the field
	 */
	public FieldSet rtvParentFieldSet()
	{
		// A WorkField parent will be a Function, not a FieldSet, so guard against this:
		return rtvParent() instanceof FieldSet ? (FieldSet) rtvParent() : null;
	}

	/**
	 * Return the underlying type of the field. This should be one of the following:
	 * <p>
	 * <li>A - Character field</li>
	 * <li>B - Boolean field</li>
	 * <li>D - Date field</li>
	 * <li>P - Packed decimal field (this may be depreciated)</li>
	 * <li>S - Zoned decimal field</li>
	 * 
	 * @return the data type of the field
	 */
	public String getDataType()
	{
		return dataType;
	}

	/**
	 * Set the data type of the field
	 * 
	 * @param dataType
	 *            - the data type of the field
	 */
	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}

	/**
	 * Return the primitive field length of the field
	 * 
	 * @return the primitive field length of the field
	 */
	public String getSize()
	{
		return size;
	}

	/**
	 * Set the primitive field length of the field
	 * 
	 * @param size
	 *            - the primitive field length of the field
	 */
	public void setSize(String size)
	{
		this.size = size.trim();
	}

	/**
	 * Return the number of decimals of the field
	 * 
	 * @return the number of decimals of the field
	 */
	public String getDecimals()
	{
		return decimals;
	}

	/**
	 * Set the number of decimals of the field
	 * 
	 * @param decimals
	 *            - the number of decimals of the field
	 */
	public void setDecimals(String decimals)
	{
		this.decimals = decimals.trim();
	}

	/**
	 * Return the type of the field
	 * 
	 * @return the type of the field
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Set the type of the field.
	 * 
	 * This is the referenced type
	 * 
	 * @param type
	 *            - type of the field
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * Returns the update script
	 * 
	 * @return the update script
	 */
	public String getUpdateScript()
	{
		return updateScript;
	}

	/**
	 * Sets the update script for this <code>Field</code> the update mapping script should be null if the <code>Field</code> does
	 * not have a script
	 * 
	 * @param script
	 */
	public void setUpdateScript(String script)
	{
		this.updateScript = script;
	}

	/**
	 * @return the isKey
	 */
	public boolean isKey()
	{
		return isKey;
	}

	/**
	 * @param isKey
	 *            the isKey to set
	 */
	public void setKey(boolean isKey)
	{
		this.isKey = isKey;
	}

	/**
	 * Returns the load mapping script for the <code>Field</code>
	 * 
	 * @return the load mapping script for the <code>Field</code>
	 */
	public String getLoadScript()
	{
		return loadScript;
	}

	/**
	 * Returns the validate mapping script for the <code>Field</code>
	 * 
	 * @return the validate mapping script for the <code>Field</code>
	 */
	public String getValidateScript()
	{
		return validateScript;
	}

	/**
	 * Sets the load mapping script for the <code>Field</code>
	 * 
	 * @param loadScript
	 */
	public void setLoadScript(String loadScript)
	{
		this.loadScript = loadScript;
	}

	/**
	 * Sets the validate mapping script for the <code>Field</code>
	 * 
	 * @param validateScript
	 */
	public void setValidateScript(String validateScript)
	{
		this.validateScript = validateScript;
	}

	/**
	 * Returns the load primitive script. This is the script mapping associated with the primitive form of a field.
	 * 
	 * @return the load primitive script
	 */
	public String getLoadPrimitiveScript()
	{
		return loadPrimitiveScript;
	}

	/**
	 * Returns the validate primitive script. This is the script mapping associated with the primitive form of a field.
	 * 
	 * @return the validate primitive script
	 */
	public String getValidatePrimitiveScript()
	{
		return validatePrimitiveScript;
	}

	/**
	 * Returns the validate output script. This is the script mapping associated with the output form of a field.
	 * 
	 * @return the validate primitive script
	 */
	public String getValidateOutputScript()
	{
		return validateOutputScript;
	}

	/**
	 * Sets the load primitive script for the field. This is the script mapping associated with the primitive form of the field.
	 * 
	 * @param loadPrimitiveScript
	 */
	public void setLoadPrimitiveScript(String loadPrimitiveScript)
	{
		this.loadPrimitiveScript = loadPrimitiveScript;
	}

	/**
	 * Sets the validate primitive script for the field. This is the script mapping associated with the primitive form of the field.
	 * 
	 * @param validatePrimitiveScript
	 */
	public void setValidatePrimitiveScript(String validatePrimitiveScript)
	{
		this.validatePrimitiveScript = validatePrimitiveScript;
	}

	/**
	 * Sets the validate output script for the field. This is the script mapping associated with the output form of the field.
	 * 
	 * @param validateOutputScript
	 */
	public void setValidateOutputScript(String validateOutputScript)
	{
		this.validateOutputScript = validateOutputScript;
	}

	/**
	 * Static block to initialise the set of data types
	 * 
	 * @return Set<String> of data types
	 */
	private static Set<String> getDataTypes()
	{
		Set<String> result = new HashSet<String>();
		result.add(EqDataType.TYPE_CHAR);
		result.add(EqDataType.TYPE_BOOLEAN);
		result.add(EqDataType.TYPE_DATE);
		result.add(EqDataType.TYPE_PACKED);
		result.add(EqDataType.TYPE_ZONED);
		result.add(EqDataType.TYPE_TIMESTAMP);
		return result;
	}

	/**
	 * Static block to initialise the set of PV data types
	 * 
	 * @return Set<String> of data types
	 */
	private static Set<String> getPVDataTypes()
	{
		Set<String> result = new HashSet<String>();
		result.add(EquationPVMetaDataHelper.TYPE_HIDALPHA);
		result.add(EquationPVMetaDataHelper.TYPE_HIDZONED);
		result.add(EquationPVMetaDataHelper.TYPE_DDMMYY);
		return result;
	}

	/**
	 * Validate the contents of the bean.
	 * 
	 * @param messageContainer
	 *            a <code>MessageContainer</code> to add the messages to
	 * @param subSet
	 *            a String specifying a validation subset (one of {@link #LOAD_VALIDATION}, {@link #UPDATE_VALIDATION} or
	 *            {@link #VALIDATE_VALIDATION}), or null for full validation
	 * @param includeChildren
	 *            Whether to validate child objects as well
	 * 
	 * @return boolean (not currently used)
	 */
	public boolean validateBean(MessageContainer messageContainer, String subSet, boolean includeChildren)
	{
		ProblemLocation problemLocation = new FunctionProblemLocation(this);
		// Validate the reference type if specified:
		if (type.length() > 0)
		{
			if (type.length() > 5)
			{
				messageContainer.addErrorMessageId("Language.FieldTypeCannotBeLongerThan5Characters", problemLocation);
			}
		}
		if (dataType.length() == 0)
		{
			// PV Fields have different requirements
			if (!(this instanceof PVField))
			{
				// Not much that's sensible to validate if no data type specified
				messageContainer.addErrorMessageId("Language.DataTypeMustNotBeBlank", getId(), problemLocation);
			}
		}
		else
		{
			if (!dataTypes.contains(dataType))
			{
				// For PV field, there are special PV field types
				if (!(this instanceof PVField) || !pvDataTypes.contains(dataType))
				{
					messageContainer.addErrorMessageId("Language.InvalidDataType", getId(), problemLocation);
				}
			}
			else
			{
				// A valid data type; can perform more validation:
				int fieldLength = 0;
				if (size.length() == 0)
				{
					messageContainer.addErrorMessageId("Language.FieldLengthMustNotBeBlankOrZero", getId(), problemLocation);
				}
				else if (size.length() > FIELD_SIZE_LIMIT)
				{
					messageContainer.addErrorMessageId("Language.FieldLengthLongerThan5Chars", problemLocation);
				}
				else
				{
					try
					{
						fieldLength = Integer.parseInt(size);
						// could be zeros:
						if (fieldLength == 0)
						{
							messageContainer
											.addErrorMessageId("Language.FieldLengthMustNotBeBlankOrZero", getId(), problemLocation);
						}
					}
					catch (NumberFormatException e)
					{
						messageContainer.addErrorMessageId("Language.InvalidNumericsInFieldLength", problemLocation);
					}
				}

				// Only validate the following if the field length was valid:
				if (fieldLength > 0 && shouldValidateFieldLength())
				{
					if (EqDataType.TYPE_CHAR.equals(dataType) && fieldLength > MAX_CHARACTER_FIELD_LENGTH)
					{
						messageContainer.addMessage(LanguageResources.getFormattedString("Language.AlphanumericFieldTooLong",
										new String[] { getId(), String.valueOf(MAX_CHARACTER_FIELD_LENGTH) }),
										Message.SEVERITY_WARNING, problemLocation);
					}
					else if ((EqDataType.TYPE_PACKED.equals(dataType) || EqDataType.TYPE_ZONED.equals(dataType))
									&& fieldLength > MAX_NUMERIC_FIELD_LENGTH)
					{
						messageContainer.addErrorMessageId("Language.NumericFieldTooLong", getId(), problemLocation);
					}
					else if (EqDataType.TYPE_BOOLEAN.equals(dataType) && fieldLength != 1)
					{
						messageContainer.addErrorMessageId("Language.FieldLengthMustBe1ForBoolean", getId(), problemLocation);
					}
					else if (EqDataType.TYPE_DATE.equals(dataType) && fieldLength != 7)
					{
						messageContainer.addErrorMessageId("Language.FieldLengthMustBe7ForDateFields", getId(), problemLocation);
					}
				}

				// Validate the number of decimals - assume blank is OK for all datatypes
				if (!(getDecimals().length() == 0))
				{
					if (EqDataType.TYPE_PACKED.equals(dataType) || EqDataType.TYPE_ZONED.equals(dataType))
					{
						if (decimals.length() > DECIMALS_LIMIT)
						{
							messageContainer.addErrorMessageId("Language.DecimalPlacesGreaterThan2Characters", problemLocation);
						}
						else
						{
							// Validate number of decimals string is a (positive) integer value
							if (!ValidationHelper.validateNumericInteger(getDecimals()))
							{
								messageContainer.addErrorMessageId("Language.InvalidNumericsInDecimalPlaces", problemLocation);
							}
							else
							{
								// If a valid integer, compare against field length:
								if (ValidationHelper.getSafeIntegerValue(decimals) > fieldLength)
								{
									messageContainer.addErrorMessageId("Language.NumberOfDecimalsGreaterThanFieldLength",
													problemLocation);
								}
							}
						}
					}
					else
					{
						messageContainer.addErrorMessageId("Language.DecimalsCannotBeSpecifiedForThisDataType", problemLocation);
					}
				}
			}
		}

		// Need to get the function to check for mappings
		Element parentElement = rtvParent();
		Function service = null;
		while (parentElement != null && service == null)
		{
			if (parentElement instanceof Function)
			{
				service = (Function) parentElement;
			}
			else if (parentElement instanceof FieldSet)
			{
				service = ((FieldSet) parentElement).getFunction();
			}

			if (service == null)
			{
				parentElement = parentElement.rtvParent();
			}
		}

		if (subSet == null || subSet.equals(LOAD_VALIDATION))
		{
			if (ASSIGNMENT_SCRIPT.equals(loadAssignment))
			{
				// Check for invalid field type
				if (this instanceof InputField)
				{
					// Only sub-types of Input Fields can have load scripts
					messageContainer.addErrorMessageId("Language.InputFieldHasLoadScript", getId(), problemLocation);
				}
				// Note that reserved (internal) fields either may or may not be assigned
				else if (this instanceof APIField && !isKey && !APIFieldSet.chkReservedFieldNames(getId()))
				{
					// Only a warning
					messageContainer.addMessage(LanguageResources.getFormattedString("Language.NonKeyAPIFieldHasLoadScript",
									getId()), Message.SEVERITY_WARNING, problemLocation);
					// messageContainer.addErrorMessageId("Language.NonKeyAPIFieldHasLoadScript", getId(), problemLocation);
				}
				else if (service != null && service.hasFromLoadMapping(rtvPath()))
				{ // Check for multiple assignment
					messageContainer.addErrorMessageId("Language.FieldAssignedByScriptAndMapping", new String[] { getId(), "",
									MappingToolbox.LOAD }, problemLocation);
				}
				else
				{
					ValidationHelper.validateELMappingExpression(loadScript, MappingToolbox.LOAD, this, "", messageContainer,
									problemLocation);
				}
			}
			if (ASSIGNMENT_SCRIPT.equals(loadPrimitiveAssignment))
			{
				if (!(this instanceof InputField))
				{
					messageContainer.addErrorMessageId("Language.NonInputFieldHasALoadSubTypeScript", new String[] { getId(),
									MappingToolbox.PRIMITIVE }, problemLocation);
				}
				else if (service != null && service.hasFromLoadMapping(rtvPath() + ".SubField.Primitive"))
				{ // Check for multiple assignment
					messageContainer.addErrorMessageId("Language.FieldAssignedByScriptAndMapping", new String[] { getId(),
									MappingToolbox.PRIMITIVE, MappingToolbox.LOAD }, problemLocation);
				}
				else
				{
					ValidationHelper.validateELMappingExpression(loadPrimitiveScript, MappingToolbox.LOAD, this,
									MappingToolbox.PRIMITIVE, messageContainer, problemLocation);
				}
			}

			// For key fields, ensure that the field is assigned by mapping, script or code:
			// Note that this is only relevant to API fields in Load APIFieldSets
			// Note that the parent APIFieldSet object itself (rather than its Id) is used to check
			// whether this field is a Load APIField (as opposed to an Update APIField)
			if (service != null && this instanceof APIField && isKey() && ASSIGNMENT_NONE.equals(loadAssignment)
							&& service.rtvPrimaryInputFieldSet().getLoadAPIs().contains(rtvParent())
							&& !service.hasFromLoadMapping(rtvPath()))
			{
				messageContainer.addErrorMessageId("Language.KeyFieldMissingLoadAssignment", getId(), problemLocation);
			}

			// Check the mapping to the Load API field
			if (service != null && this instanceof APIField && ASSIGNMENT_NONE.equals(loadAssignment)
							&& service.hasFromLoadMapping(rtvPath()))
			{
				String path = service.rtvFromLoadMapping(rtvPath());
				try
				{
					Field field = getInputOrWorkField(path, service);
					validateMappedFieldSize(field, messageContainer, problemLocation);
				}
				catch (EQException e)
				{
					messageContainer.addMessage(Toolbox.getExceptionMessage(e), Message.SEVERITY_ERROR, problemLocation);
				}
			}

			// Check the mapping to the input field
			if (service != null && this instanceof InputField && ASSIGNMENT_NONE.equals(loadAssignment)
							&& service.hasFromLoadMapping(rtvPath() + MappingToolbox.SUBFIELD_PRIMITIVE))
			{
				String path = service.rtvFromLoadMapping(rtvPath() + MappingToolbox.SUBFIELD_PRIMITIVE);
				String inputFieldSet = MappingToolbox.getFieldSet(path);
				String apiField = MappingToolbox.getField(path);
				String apiFieldSet = MappingToolbox.getLoadAPIFieldSet(path);

				try
				{
					Field field = service.getInputFieldSet(inputFieldSet).getLoadAPI(apiFieldSet).getField(apiField);
					validateMappedFieldSize(field, messageContainer, problemLocation);
				}
				catch (EQException e)
				{
					messageContainer.addMessage(Toolbox.getExceptionMessage(e), Message.SEVERITY_ERROR, problemLocation);
				}
			}
		}

		if (subSet == null || subSet.equals(VALIDATE_VALIDATION))
		{
			if (ASSIGNMENT_SCRIPT.equals(validateAssignment))
			{
				// TODO: Only PV fields should have non-subfield validateScript
				if (service != null && service.hasFromValidateMapping(rtvPath()))
				{ // Check for multiple assignment
					messageContainer.addErrorMessageId("Language.FieldAssignedByScriptAndMapping", new String[] { getId(), "",
									MappingToolbox.VALIDATE }, problemLocation);
				}
				else
				{
					ValidationHelper.validateELMappingExpression(validateScript, MappingToolbox.VALIDATE, this, "",
									messageContainer, problemLocation);
				}
			}

			if (ASSIGNMENT_SCRIPT.equals(validatePrimitiveAssignment))
			{
				if (!(this instanceof InputField))
				{
					messageContainer.addErrorMessageId("Language.NonInputFieldHasAValidateSubTypeScript", new String[] { getId(),
									MappingToolbox.PRIMITIVE }, problemLocation);
				}
				else if (service != null && service.hasFromValidateMapping(rtvPath() + MappingToolbox.SUBFIELD_PRIMITIVE))
				{ // Check for multiple assignment
					messageContainer.addErrorMessageId("Language.FieldAssignedByScriptAndMapping", new String[] { getId(),
									MappingToolbox.PRIMITIVE, MappingToolbox.VALIDATE }, problemLocation);
				}
				else
				{
					ValidationHelper.validateELMappingExpression(validatePrimitiveScript, MappingToolbox.VALIDATE, this,
									MappingToolbox.PRIMITIVE, messageContainer, problemLocation);
				}
			}
			if (ASSIGNMENT_SCRIPT.equals(validateOutputAssignment))
			{
				if (!(this instanceof InputField))
				{
					messageContainer.addErrorMessageId("Language.NonInputFieldHasAValiateSubTypeScript", new String[] { getId(),
									MappingToolbox.OUTPUT }, problemLocation);
				}
				else if (service != null && service.hasFromValidateMapping(rtvPath() + MappingToolbox.SUBFIELD_OUTPUT))
				{ // Check for multiple assignment
					messageContainer.addErrorMessageId("Language.FieldAssignedByScriptAndMapping", new String[] { getId(),
									MappingToolbox.OUTPUT, MappingToolbox.VALIDATE }, problemLocation);
				}
				else
				{
					ValidationHelper.validateELMappingExpression(validateOutputScript, MappingToolbox.VALIDATE, this,
									MappingToolbox.OUTPUT, messageContainer, problemLocation);
				}
			}
			// For key fields, ensure that the field is assigned by mapping, script or code:
			// Note that this is only relevant to PV fields
			if (service != null && this instanceof PVField && isKey() && ASSIGNMENT_NONE.equals(validateAssignment)
							&& !service.hasFromValidateMapping(rtvPath()))
			{
				messageContainer.addErrorMessageId("Language.KeyFieldMissingValidateAssignment", getId(), problemLocation);
			}

			// Check the mapping to the PV field
			if (service != null && this instanceof PVField && ASSIGNMENT_NONE.equals(validateAssignment)
							&& service.hasFromValidateMapping(rtvPath()))
			{
				String path = service.rtvFromValidateMapping(rtvPath());
				try
				{
					Field field = getInputOrWorkField(path, service);
					validateMappedFieldSize(field, messageContainer, problemLocation);
				}
				catch (EQException e)
				{
					messageContainer.addMessage(Toolbox.getExceptionMessage(e), Message.SEVERITY_ERROR, problemLocation);
				}
			}

			// Check the mapping to the input field
			if (service != null && this instanceof InputField && ASSIGNMENT_NONE.equals(validateAssignment)
							&& service.hasFromValidateMapping(rtvPath() + MappingToolbox.SUBFIELD_PRIMITIVE))
			{
				String path = service.rtvFromValidateMapping(rtvPath() + MappingToolbox.SUBFIELD_PRIMITIVE);
				String inputFieldSet = MappingToolbox.getFieldSet(path);
				String inputField = MappingToolbox.getInputField(path);
				String pvFieldSet = MappingToolbox.getPVFieldSet(path);
				String pvField = MappingToolbox.getField(path);

				try
				{
					InputField iField = (InputField) service.getInputFieldSet(inputFieldSet).getField(inputField);
					Field field = iField.getPvFieldSet(pvFieldSet).getPVField(pvField);
					validateMappedFieldSize(field, messageContainer, problemLocation);
				}
				catch (EQException e)
				{
					messageContainer.addMessage(Toolbox.getExceptionMessage(e), Message.SEVERITY_ERROR, problemLocation);
				}
			}

		}

		if (subSet == null || subSet.equals(UPDATE_VALIDATION))
		{
			if (ASSIGNMENT_SCRIPT.equals(updateAssignment))
			{
				if (!(this instanceof APIField))
				{
					// Only API fields can have update scripts.
					messageContainer.addErrorMessageId("Language.NonAPIFieldHasUpdateScript", getId(), problemLocation);
				}
				else if (service != null && service.hasFromUpdateMapping(rtvPath()))
				{
					messageContainer.addErrorMessageId("Language.FieldAssignedByScriptAndMapping", new String[] { getId(), "",
									MappingToolbox.UPDATE }, problemLocation);
				}
				else
				{
					ValidationHelper.validateELMappingExpression(updateScript, MappingToolbox.UPDATE, this, "", messageContainer,
									problemLocation);
				}
			}
			// For key fields, ensure that the field is assigned by mapping, script or code:
			// Note that this is only relevant to API fields (in an Update API)
			// Note that the parent APIFieldSet object itself (rather than its Id) is used to check
			// whether this field is a Update APIField (as opposed to an Load APIField)
			if (service != null && this instanceof APIField && isKey() && ASSIGNMENT_NONE.equals(updateAssignment)
							&& service.getUpdateAPIs().contains(rtvParent()) && !service.hasFromUpdateMapping(rtvPath()))
			{
				messageContainer.addErrorMessageId("Language.KeyFieldMissingUpdateAssignment", getId(), problemLocation);
			}

			// Check the mapping to the API field
			if (service != null && this instanceof APIField && ASSIGNMENT_NONE.equals(updateAssignment)
							&& service.hasFromUpdateMapping(rtvPath()))
			{
				String path = service.rtvFromUpdateMapping(rtvPath());
				try
				{
					// Check if mapped from another update API
					String sourceAPI = MappingToolbox.getAPIUsingLabel(path);
					if (sourceAPI != null && !sourceAPI.equals(""))
					{
						String fieldId = MappingToolbox.getField(path);
						if (fieldId != null)
						{
							Field field = service.getUpdateAPI(sourceAPI).getField(fieldId);
							validateMappedFieldSize(field, messageContainer, problemLocation);
						}
						else
						{
							messageContainer
											.addErrorMessageId("Language.FieldDoesNotExist", new String[] { path }, problemLocation);
						}
					}
					else
					{

						Field field = getInputOrWorkField(path, service);
						if (field != null)
						{
							validateMappedFieldSize(field, messageContainer, problemLocation);
						}
						else
						{
							messageContainer
											.addErrorMessageId("Language.FieldDoesNotExist", new String[] { path }, problemLocation);
						}
					}
				}
				catch (EQException e)
				{
					messageContainer.addMessage(Toolbox.getExceptionMessage(e), Message.SEVERITY_ERROR, problemLocation);
				}
			}

		}
		// Check that Input Group exists for repeating input fields
		if (this instanceof InputField && Field.isRepeating(this))
		{
			InputFieldSet inputFieldSet = (InputFieldSet) rtvParentFieldSet();
			if (inputFieldSet != null && !inputFieldSet.hasInputGroup(getRepeatingGroup()))
			{
				messageContainer.addErrorMessageId("Language.FieldAssignedToNonExistingRepeatingGroup", new String[] { getId(),
								getRepeatingGroup() }, problemLocation);
			}
		}
		if (includeChildren)
		{
			// TODO:
		}

		return !messageContainer.hasErrorMessages();
	}

	/**
	 * Wraps the retrieval of either an InputField or WorkField
	 * 
	 * @param path
	 *            Mapping path
	 * @param service
	 *            Function instance
	 * @return Field The InputField or WorkField
	 * @throws EQException
	 */
	private Field getInputOrWorkField(String path, Function service) throws EQException
	{
		String inputFieldSet = MappingToolbox.getFieldSet(path);
		String fieldName = MappingToolbox.getField(path);
		Field field = null;
		if (inputFieldSet.length() > 0)
		{
			field = service.getInputFieldSet(inputFieldSet).getField(fieldName);
		}
		else
		{
			// Assume workfield
			field = service.getWorkFields().get(fieldName);
		}
		return field;
	}

	/**
	 * Compares a mapped field against this field and issues a warning if different sizes
	 * 
	 * @param field
	 * @param messageContainer
	 * @param problemLocation
	 */
	private void validateMappedFieldSize(Field field, MessageContainer messageContainer, ProblemLocation problemLocation)
	{

		int sourceSize = Toolbox.parseInt(field.getSize(), 0);
		int targetSize = Toolbox.parseInt(getSize(), 0);
		if (sourceSize > targetSize)
		{
			messageContainer.addMessage(LanguageResources.getFormattedString("Language.MappingExceedSize", new String[] { getId(),
							getSize(), field.getId(), field.getSize() }), Message.SEVERITY_WARNING, problemLocation);
		}
	}

	public String getLoadAssignment()
	{
		return loadAssignment;
	}

	public void setLoadAssignment(String loadAssignment)
	{
		this.loadAssignment = "0".equals(loadAssignment) ? null : loadAssignment;
	}

	public String getUpdateAssignment()
	{
		return updateAssignment;
	}

	public void setUpdateAssignment(String updateAssignment)
	{
		this.updateAssignment = "0".equals(updateAssignment) ? null : updateAssignment;
	}

	public String getValidateAssignment()
	{
		return validateAssignment;
	}

	public void setValidateAssignment(String validateAssignment)
	{
		this.validateAssignment = "0".equals(validateAssignment) ? null : validateAssignment;
	}

	public String getLoadPrimitiveAssignment()
	{
		return loadPrimitiveAssignment;
	}

	public void setLoadPrimitiveAssignment(String loadPrimitiveAssignment)
	{
		this.loadPrimitiveAssignment = "0".equals(loadPrimitiveAssignment) ? null : loadPrimitiveAssignment;
	}

	public String getValidateOutputAssignment()
	{
		return validateOutputAssignment;
	}

	public void setValidateOutputAssignment(String validateOutputAssignment)
	{
		this.validateOutputAssignment = "0".equals(validateOutputAssignment) ? null : validateOutputAssignment;
	}

	/** @return a <code>String</code> indicating how the ValidatePrimitive value is assigned */
	public String getValidatePrimitiveAssignment()
	{
		return validatePrimitiveAssignment;
	}

	/**
	 * Sets the validatePrimitiveAssignment
	 * 
	 * @param validatePrimitiveAssignment
	 *            a <code>String</code> indicating how the ValidatePrimitive value is assigned
	 * */
	public void setValidatePrimitiveAssignment(String validatePrimitiveAssignment)
	{
		this.validatePrimitiveAssignment = "0".equals(validatePrimitiveAssignment) ? null : validatePrimitiveAssignment;
	}

	/**
	 * @return the repeatingGroup
	 */
	public String getRepeatingGroup()
	{
		return repeatingGroup;
	}

	/**
	 * @param repeatingGroup
	 *            the repeatingGroup to set
	 */
	public void setRepeatingGroup(String repeatingGroup)
	{
		this.repeatingGroup = repeatingGroup;
	}

	/**
	 * Determines if a field is repeating or not
	 * 
	 * @param field
	 *            The field to check
	 * @return boolean
	 */
	public static boolean isRepeating(Field field)
	{
		String group = field.getRepeatingGroup();
		return !(group == null || group.trim().length() == 0);
	}

	/**
	 * Determine whether standard bean validation should validate the field length. Default is true
	 * 
	 * @return true to validate field length
	 */
	protected boolean shouldValidateFieldLength()
	{
		return true;
	}

	/**
	 * Return the equivalent iSeries type
	 * 
	 * @return the equivalent iSeries type
	 */
	public String rtvBaseType()
	{
		if (dataType.equals(EqDataType.TYPE_CHAR) || dataType.equals(EqDataType.TYPE_BOOLEAN))
		{
			return EqDataType.TYPE_CHAR;
		}
		else if (dataType.equals(EqDataType.TYPE_PACKED))
		{
			return EqDataType.TYPE_PACKED;
		}
		else if (dataType.equals(EqDataType.TYPE_ZONED) || dataType.equals(EqDataType.TYPE_DATE))
		{
			return EqDataType.TYPE_ZONED;
		}
		else
		{
			return dataType;
		}
	}

}
