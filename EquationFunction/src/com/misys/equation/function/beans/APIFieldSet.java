package com.misys.equation.function.beans;

import java.util.Arrays;
import java.util.List;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.tools.FunctionToolbox;

public class APIFieldSet extends FieldSet
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: APIFieldSet.java 12576 2012-03-06 04:33:02Z fraramos $";

	/** Name of the reserved field automatically added to API field sets to allow control of the mode */
	public static final String MODE_FIELD_NAME = "MODE";

	private static final String[] RESERVED_FIELD_NAMES = new String[] { MODE_FIELD_NAME };

	private String type;
	private String mode;
	private String root;
	private String keyFields;
	private String decode;
	private boolean cache;
	private boolean globalLibrary;

	// These are LOAD API specific details
	private boolean determineMode;
	private String newField; // Y, N, or blank
	private String[] mandatoryFields = null;
	private String relatedFields;
	// These are UPDATE API specific details
	/**
	 * If the Equation service is going to be applied during recovery or external input,<br>
	 * these properties allows control on the underlying APIs whether the Equation service <br>
	 * regenerated these updates (false) or ignores it (true - let the recovery/external input <br>
	 * process apply the GY journal details of the underlying APIs).<br>
	 * <br>
	 * These flags are ignored when the Equation service is NOT going to be applied <br>
	 * (in this case the GY of underlying APIs will always be applied)
	 */
	private boolean alwaysApplyInRecovery;
	private boolean alwaysApplyInExtInput;

	// These are repeating fields specific details
	/**
	 * If the repeating group is specified, then this API field set is associated with that particular repeating group
	 */
	private String repeatingGroup;
	/**
	 * This is relevant only for Load API and determines how it will populate the repeating group<br>
	 * RPTGRP_LOAD_DEFAULT - This is use if you want to display all the details (as defined in the Load Mapping) returned by the API
	 * 
	 * RPTGRP_LOAD_CHAIN - This is used if you want to add additional information into an existing repeating group that is not
	 * available in the previous API. It is important that there must another API with a load mode of RPTGRP_LOAD_DEFAULT. The
	 * RPTGRP_LOAD_KEY will provide the link to the repeating group
	 * 
	 * RPTGRP_LOAD_CHAIN_ADD - This is a combination of RPTGRP_LOAD_DEFAULT and RPTGRP_LOAD_CHAIN. If the key exists, then it will
	 * add additional information into the list. If the key does not exist, then the details are added
	 * 
	 * RPTGRP_LOAD_SQL_AGGREGATE - This is executed per record in the list and the resulting query must return one a single record.
	 * If more than one record, then only the first record is retrieved
	 * 
	 */
	private int repeatingGroupLoadMode;
	public static final int RPTGRP_LOAD_DEFAULT = 0;
	public static final int RPTGRP_LOAD_CHAIN = 1;
	public static final int RPTGRP_LOAD_CHAIN_ADD = 2;
	public static final int RPTGRP_LOAD_LOOKUP = 3;
	public static final int RPTGRP_LOAD_SQL_AGGREGATE = 4;

	/**
	 * This determines the join field with the input fields in the repeating group.<br>
	 * It contains the list of API fields delimited by Toolbox.CONTEXT_DELIMETER. <br>
	 * The format is <APIField1:APIField2:..>
	 */
	private String repeatingGroupLoadJoinAPIField;
	/**
	 * This determines the join field with the API fields.<br>
	 * It contains the list of input fields delimited by Toolbox.CONTEXT_DELIMETER. <br>
	 * The format is <InputField1:InputField2:..>
	 */
	private String repeatingGroupLoadJoinInputField;

	/**
	 * This determines the list of messages to be automatically overridden during Update API<br>
	 * OVERRIDE_ALL - override all messages (default) <br>
	 * OVERRIDE_NONE - override no messages <br>
	 * KSM ids - list of KSM id delimeted by CONTEXT_DELIMETER (e.g. KSM1020:KSM2020:...) <br>
	 */
	private String overrideWarningMessages;
	public static final String OVERRIDE_ALL = "*ALL";
	public static final String OVERRIDE_NONE = "*NONE";

	/**
	 * Construct a new empty field set
	 */
	public APIFieldSet()
	{
		super();
		commonInitialisation();
	}
	/**
	 * Construct a APIFieldSet using a Field Set
	 * 
	 */
	public APIFieldSet(APIFieldSet fieldSet)
	{
		super(fieldSet);
		this.type = fieldSet.type;
		this.mode = fieldSet.mode;
		this.root = fieldSet.root;
		this.keyFields = fieldSet.keyFields;
		this.decode = fieldSet.decode;
		this.cache = fieldSet.cache;
		this.globalLibrary = fieldSet.globalLibrary;
		this.determineMode = fieldSet.determineMode;
		this.newField = fieldSet.newField;
		this.mandatoryFields = fieldSet.mandatoryFields;
		this.alwaysApplyInRecovery = fieldSet.alwaysApplyInRecovery;
		this.alwaysApplyInExtInput = fieldSet.alwaysApplyInExtInput;
		this.repeatingGroup = fieldSet.repeatingGroup;
		this.repeatingGroupLoadMode = fieldSet.repeatingGroupLoadMode;
		this.repeatingGroupLoadJoinAPIField = fieldSet.repeatingGroupLoadJoinAPIField;
		this.repeatingGroupLoadJoinInputField = fieldSet.repeatingGroupLoadJoinInputField;
		this.overrideWarningMessages = fieldSet.overrideWarningMessages;
		this.relatedFields = fieldSet.relatedFields;
		// fields and field keys are not copied in super!
		for (APIField apiField : fieldSet.getAPIFields())
		{
			try
			{
				this.addAPIField(apiField);
			}
			catch (EQException e)
			{
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Construct a APIFieldSet using a Field Set
	 */
	public APIFieldSet(FieldSet fieldSet)
	{
		super(fieldSet);
		commonInitialisation();
	}

	/**
	 * Construct an instance of <code>APIFieldSet</code>
	 * 
	 * @param id
	 *            - A <code>String</code> ID for the <code>APIFieldSet</code>
	 * @param label
	 *            - A <code>String</code> label for the <code>APIFieldSet</code>
	 * @param description
	 *            - A <code>String</code> description for the <code>APIFieldSet</code>
	 */
	public APIFieldSet(String id, String label, String description)
	{
		super(id, label, description);
		commonInitialisation();
	}

	/**
	 * Constructs an instance of <code>APIFieldSet</code> from a PVFieldSet and PVFieldSet must be a PV transaction only
	 * 
	 * @param apiId
	 *            a <code>String</code> containing the API identifier
	 * 
	 * @param pvFieldSet
	 *            the <code>PVFieldSet</code> to 'copy'
	 */
	public APIFieldSet(String apiId, PVFieldSet pvFieldSet)
	{
		super(apiId, pvFieldSet.getId(), pvFieldSet.getDescription());
		commonInitialisation();
		mode = "";
		type = IEquationStandardObject.TYPE_PV;
		decode = pvFieldSet.getDecode();
		cache = pvFieldSet.isCache();
		newField = pvFieldSet.getNewField();

		for (PVField pvField : pvFieldSet.getPVFields())
		{
			APIField apiField = new APIField(pvField.getId(), pvField.getLabel(), pvField.getDescription());
			apiField.setDataType(pvField.getDataType());
			apiField.setDecimals(pvField.getDecimals());
			apiField.setKey(pvField.isKey());
			apiField.setSize(pvField.getSize());
			apiField.setType(pvField.getType());
			try
			{
				addAPIField(apiField);
			}
			catch (EQException e)
			{
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Common initialisation
	 */
	private void commonInitialisation()
	{
		mode = IEquationStandardObject.FCT_FUL;
		root = "";
		keyFields = "";
		type = IEquationStandardObject.TYPE_TRANSACTION;
		determineMode = false;
		decode = "";
		alwaysApplyInRecovery = false;
		alwaysApplyInExtInput = false;
		newField = "";
		repeatingGroup = "";
		repeatingGroupLoadMode = RPTGRP_LOAD_DEFAULT;
		repeatingGroupLoadJoinAPIField = "";
		repeatingGroupLoadJoinInputField = "";
		cache = false;
		overrideWarningMessages = OVERRIDE_ALL;
		globalLibrary = false;
	}

	/**
	 * Returns the root of the <code>APIFieldSet</code> this is the initial characters of the API e.g. H15A
	 * 
	 * @return a <code>String</code> of the root
	 */
	public String getRoot()
	{
		return root;
	}

	/**
	 * Sets the root of the <code>APIFieldSet</code>
	 * 
	 * @param root
	 */
	public void setRoot(String root)
	{
		this.root = root;
	}

	/**
	 * Return whether this LOAD API field set will determine the main mode of the transaction
	 * 
	 * @return true if this LOAD API field set will determine the main mode of the transaction
	 */
	public boolean isDetermineMode()
	{
		return determineMode;
	}

	/**
	 * Set whether this LOAD API field set will determine the main mode of the transaction
	 * 
	 * @param determineMode
	 *            - true if this LOAD API field set will determine the main mode of the transaction
	 */
	public void setDetermineMode(boolean determineMode)
	{
		this.determineMode = determineMode;
	}

	/**
	 * Returns the <code>APIField</code>s on this <code>APIFieldSet</code>
	 * 
	 * @return a <code>List</code> of <code>APIField</code>s
	 */
	public List<APIField> getAPIFields()
	{
		return super.getFields();
	}

	/**
	 * Add an <code>APIField</code> to this <code>APIFieldSet</code>
	 * 
	 * @param field
	 * @throws EQException
	 *             if the field already exists on the <code>APIFieldSet</code>
	 */
	public void addAPIField(APIField field) throws EQException
	{
		super.addField(field);
	}

	/**
	 * Swap an <code>APIField</code> with the one in the specified position
	 * 
	 * @param index
	 *            the zero-based index to move the field to
	 * @param id
	 *            a <code>String</code> containing the field id
	 * @throws EQException
	 *             if the field does not exist on the <code>APIFieldSet</code>
	 */
	public void swapAPIField(int index, String id) throws EQException
	{
		super.swapField(index, id);
	}

	/**
	 * Returns an <code>APIField</code> given a <code>String</code> key
	 * 
	 * @param key
	 * @return APIField
	 * @throws EQException
	 *             if the field does not exist
	 */
	public APIField getAPIField(String key) throws EQException
	{
		return (APIField) super.getField(key);
	}

	/**
	 * Returns the key fields of this <code>APIFieldSet</code>
	 * 
	 * @return a <code>String</code> of the key fields delimited by ||
	 */
	public String getKeyFields()
	{
		return keyFields;
	}

	/**
	 * Sets the key fields
	 * 
	 * @param keyFields
	 *            - a <code>String</code> of the key fields delimited by ||
	 */
	public void setKeyFields(String keyFields)
	{
		this.keyFields = keyFields;
	}

	/**
	 * Change the key fields
	 * 
	 * @param newKeyFields
	 */
	@SuppressWarnings("unchecked")
	public void updateKeyFields(String newKeyFields) throws EQException
	{
		this.keyFields = newKeyFields;
		for (Field field : (List<Field>) super.getFields())
		{
			field.setKey(false);
		}
		for (String newKeyField : newKeyFields.trim().split(Toolbox.CONTEXT_DELIMETER))
		{
			if (super.containsField(newKeyField))
			{
				super.getField(newKeyField).setKey(true);
			}
		}
	}

	/**
	 * Return the type of the API field set
	 * <p>
	 * This must be one of the TYPE constants defined in {@link IEquationStandardObject}, for example
	 * {@link IEquationStandardObject#TYPE_TRANSACTION}.
	 * 
	 * @see IEquationStandardObject
	 * @return the type of the API field set
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Set the type of the API field set
	 * <p>
	 * This must be one of the TYPE constants defined in {@link IEquationStandardObject}, for example
	 * {@link IEquationStandardObject#TYPE_TRANSACTION}.
	 * 
	 * @param type
	 *            - the type of the API field set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * Returns the mode of this FieldSet
	 * 
	 * @return a <code>String</code> of the mode This will be 'A' for Add, 'M' for Maintain, 'D' for Delete or 'F' for
	 *         Fully-Functional.
	 */
	public String getMode()
	{
		return mode;
	}

	/**
	 * Set the mode of this FieldSet
	 * 
	 * @param mode
	 *            - a <code>String</code> of the mode. This will be 'A' for Add, 'M' for Maintain, 'D' for Delete or 'F' for
	 *            Fully-Functional.
	 * 
	 */
	public void setMode(String mode)
	{
		this.mode = mode;
	}

	/**
	 * Adds APIFieldSet reserved fields to the field set if not already present.
	 * <p>
	 * This currently adds a "mode" field which will be used to hold the operation mode for the API. Note that after calling this
	 * method, this FieldSet will contain fields that should not be added as InputFields.
	 */
	public void addReservedFields()
	{
		if (!containsField(MODE_FIELD_NAME))
		{
			APIField modeField = new APIField(MODE_FIELD_NAME, LanguageResources.getString("Language.APIOperationMode"),
							LanguageResources.getString("Language.APIOperationMode"));
			modeField.setDataType(EqDataType.TYPE_CHAR);
			modeField.setSize("1");
			addField(0, modeField); // Add as the first field
		}
	}

	/**
	 * @return a <code>List<String></code> containing the set of reserved field names in APIFieldSets
	 */
	public static List<String> getReservedFieldNames()
	{
		return Arrays.asList(RESERVED_FIELD_NAMES);
	}

	/**
	 * Check if field id is a reserved name
	 * 
	 * @param fieldName
	 *            - the field id to checked
	 * 
	 * @return true if field id is a reserved field name
	 */
	public static boolean chkReservedFieldNames(String fieldName)
	{
		for (String element : RESERVED_FIELD_NAMES)
		{
			if (fieldName.equals(element))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Get the decode for the prompt/validate module of the field TODO: 2 decodes, one for prompt and one for validate
	 * 
	 * @return the decode of the prompt/validate module of the field
	 */
	public String getDecode()
	{
		return decode;
	}

	/**
	 * Set the decode for the prompt/validate module of the field
	 * 
	 * @param decode
	 *            - decode for the prompt/validate module of the field
	 */
	public void setDecode(String decode)
	{
		this.decode = decode;
	}

	/**
	 * Determine whether the prompt/validate is cache or not
	 * 
	 * @return true if prompt/validate is cache
	 */
	public boolean isCache()
	{
		return cache;
	}

	/**
	 * Set whether the prompt/validate is cache or not
	 * 
	 * @param cache
	 *            - true if prompt/validate is cache
	 */
	public void setCache(boolean cache)
	{
		this.cache = cache;
	}

	/**
	 * Determine whether this update API will always be applied during recovery
	 * 
	 * @return true if this update API will always be applied during recovery
	 */
	public boolean isAlwaysApplyInRecovery()
	{
		return alwaysApplyInRecovery;
	}

	/**
	 * Set whether this update API will always be applied during recovery
	 * 
	 * @param alwaysApplyInRecovery
	 *            - true if this update API will always be applied during recovery
	 */
	public void setAlwaysApplyInRecovery(boolean alwaysApplyInRecovery)
	{
		this.alwaysApplyInRecovery = alwaysApplyInRecovery;
	}

	/**
	 * Determine whether this update API will always be applied during external input
	 * 
	 * @return true if this update API will always be applied during external input
	 */
	public boolean isAlwaysApplyInExtInput()
	{
		return alwaysApplyInExtInput;
	}

	/**
	 * Set whether this update API will always be applied during external input
	 * 
	 * @param alwaysApplyInExtInput
	 *            - true if this update API will always be applied during external input
	 */
	public void setAlwaysApplyInExtInput(boolean alwaysApplyInExtInput)
	{
		this.alwaysApplyInExtInput = alwaysApplyInExtInput;
	}

	/**
	 * Determine whether the Load API record must exist or must not exist or either
	 * 
	 * @return whether the record must exist or must not exist or either
	 */
	public String getNewField()
	{
		return newField;
	}

	/**
	 * Set whether the Load API must exist or must not exist or either
	 * 
	 * @param newField
	 *            - the flag to determine whether record must exist or must not exist or either
	 */
	public void setNewField(String newField)
	{
		this.newField = newField;
	}

	/**
	 * @return a best attempt for the fields that are mandatory
	 */
	public String[] rtvMandatoryFields()
	{
		if (mandatoryFields == null)
		{
			mandatoryFields = EquationCommonContext.getContext().getMinimumFields(getLabel());
		}
		return mandatoryFields;
	}

	/**
	 * Return the repeating group id that this API field set is associated to
	 * 
	 * @return the repeating group id that this API field set is associated to
	 */
	public String getRepeatingGroup()
	{
		return repeatingGroup;
	}

	/**
	 * Set the repeating group id that this API field set is associated to
	 * 
	 * @param repeatingGroup
	 *            - the repeating group id that this API field set is associated to
	 */
	public void setRepeatingGroup(String repeatingGroup)
	{
		this.repeatingGroup = repeatingGroup;
	}

	/**
	 * Return how this API field set will populate the data
	 * 
	 * @return how this API field set will populate the data
	 */
	public int getRepeatingGroupLoadMode()
	{
		return repeatingGroupLoadMode;
	}

	/**
	 * Set how this API field set will populate the data
	 * 
	 * @param repeatingGroupLoadMode
	 *            - how this API field set will populate the data
	 */
	public void setRepeatingGroupLoadMode(int repeatingGroupLoadMode)
	{
		this.repeatingGroupLoadMode = repeatingGroupLoadMode;
	}

	/**
	 * Return the list of API fields used for joining with the input fields in the repeating data
	 * 
	 * @return the list of API fields used for joining with the input fields in the repeating data
	 */
	public String getRepeatingGroupLoadJoinAPIField()
	{
		return repeatingGroupLoadJoinAPIField;
	}

	/**
	 * Set the list of API fields used for joining with the input fields in the repeating data
	 * 
	 * @param repeatingGroupLoadJoinAPIField
	 *            - the list of API fields used for joining with the input fields in the repeating data
	 */
	public void setRepeatingGroupLoadJoinAPIField(String repeatingGroupLoadJoinAPIField)
	{
		this.repeatingGroupLoadJoinAPIField = repeatingGroupLoadJoinAPIField;
	}

	/**
	 * Return the list of input fields used for joining with the API fields
	 * 
	 * @return the list of input fields used for joining with the API fields
	 */
	public String getRepeatingGroupLoadJoinInputField()
	{
		return repeatingGroupLoadJoinInputField;
	}

	/**
	 * Set the list of input fields used for joining with the API fields
	 * 
	 * @param repeatingGroupLoadJoinInputField
	 *            -
	 */
	public void setRepeatingGroupLoadJoinInputField(String repeatingGroupLoadJoinInputField)
	{
		this.repeatingGroupLoadJoinInputField = repeatingGroupLoadJoinInputField;
	}

	/**
	 * Return the list of KSM to be automatically overridden
	 * 
	 * @return the list of KSM to be automatically overridden
	 */
	public String getOverrideWarningMessages()
	{
		return overrideWarningMessages;
	}

	/**
	 * Set the list of KSM to be automatically overridden
	 * 
	 * @param overrideWarningMessages
	 *            - the list of KSM to be automatically overridden
	 */
	public void setOverrideWarningMessages(String overrideWarningMessages)
	{
		this.overrideWarningMessages = overrideWarningMessages;
	}

	/**
	 * Determine whether message is ignored or not
	 * 
	 * @param ksmId
	 *            - the KSM message id
	 * 
	 * @return true if messages is ignored
	 */
	public boolean chkIgnoreMessage(String ksmId)
	{
		if (overrideWarningMessages.equals(OVERRIDE_ALL))
		{
			return true;
		}
		else if (overrideWarningMessages.equals(OVERRIDE_NONE))
		{
			return false;
		}
		else
		{
			return overrideWarningMessages.indexOf(ksmId) >= 0;
		}
	}

	/**
	 * Validates an API Set Id
	 * 
	 * TODO: Move to Bean validation
	 * 
	 * @param id
	 *            string
	 * @return message text or null if OK
	 */
	public static String isAPISetIdentifierValid(String id)
	{
		String message = null;

		// check if valid id
		String validId = FunctionToolbox.isValidAPIId(id);
		if (validId != null)
		{
			message = LanguageResources.getString(validId);
		}

		// The API Set ID must not match certain reserved field set IDs
		if (Function.containsReservedName(id))
		{
			message = LanguageResources.getFormattedString("Language.APIIdIsReserved", id);
		}
		// Assume length validation handled by UI
		return message;
	}

	/**
	 * Does the API FieldSet load from a table in the global library
	 * 
	 * @return true if the APIFieldSet is both loaded from a table <BR>
	 *         and the table is part of the global library, otherwise false
	 */
	public boolean isGlobalLibrary()
	{
		return globalLibrary;
	}

	/**
	 * Set whether the API loads from a global library
	 * 
	 * @param globalLibrary
	 */
	public void setGlobalLibrary(boolean globalLibrary)
	{
		this.globalLibrary = globalLibrary;
	}

	/**
	 * Getter method of relatedField
	 * 
	 * @return String
	 */
	public String getRelatedFields()
	{
		return relatedFields;
	}

	/**
	 * Setter method of relatedField
	 * 
	 * @param relatedFields
	 */
	public void setRelatedFields(String relatedFields)
	{
		this.relatedFields = relatedFields;
	}

}