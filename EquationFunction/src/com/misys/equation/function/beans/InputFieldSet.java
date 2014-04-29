package com.misys.equation.function.beans;

import java.util.ArrayList;
import java.util.List;

import com.misys.equation.common.access.EquationStandardQueryList;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IACMRecordDao;
import com.misys.equation.common.dao.IXVRecordDao;
import com.misys.equation.common.dao.beans.ACMRecordDataModel;
import com.misys.equation.common.dao.beans.GAERecordDataModel;
import com.misys.equation.common.dao.beans.XVRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQRuntimeException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.valid.MessageContainer;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.MappingToolbox;
import com.misys.equation.function.tools.RepeatingDetails;
import com.misys.equation.function.tools.TranslationToolbox;
import com.misys.equation.function.tools.XSDStructureHelper;

public class InputFieldSet extends FieldSet
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: InputFieldSet.java 17190 2013-09-03 11:49:59Z Lima12 $";
	private String defaultUserExit = "";
	private String validateUserExit = "";
	// Load APIs
	private final List<APIFieldSet> loadAPIs = new ArrayList<APIFieldSet>();
	private final List<String> loadAPIKeys = new ArrayList<String>();

	/** List of groups to contain repeating data */
	private final List<InputGroup> inputGroups = new ArrayList<InputGroup>();

	/** PVs used for narrative validation (that will be excluded when importing an API) */
	public static final String[] narrativePVs = new String[] { "GWV59R", "GWV60R" };

	/** Flag to indicate if a BankFusion Microflow should be called to perform default processing. */
	private boolean defaultMicroflow = false;
	/** Flag to indicate if a BankFusion Microflow should be called to perform validate processing. */
	private boolean validateMicroflow = false;

	/**
	 * Construct an Input field set
	 */
	public InputFieldSet()
	{
		super();
	}

	/**
	 * Construct an Input field set
	 * 
	 * @param id
	 * @param label
	 * @param description
	 */
	public InputFieldSet(String id, String label, String description)
	{
		super(id, label, description);
	}

	/**
	 * Construct an Input field set
	 * 
	 * @param element
	 */
	public InputFieldSet(Element element)
	{
		super(element);
	}

	/**
	 * Constructor to create a new input field set from an existing one
	 * 
	 * @param inputFieldSet
	 *            - the input field set to copy
	 */
	public InputFieldSet(InputFieldSet inputFieldSet)
	{
		super(inputFieldSet);
		this.defaultUserExit = inputFieldSet.defaultUserExit;
		this.validateUserExit = inputFieldSet.validateUserExit;
		this.loadAPIs.addAll(inputFieldSet.getLoadAPIs());
		this.loadAPIKeys.addAll(inputFieldSet.loadAPIKeys);
		this.inputGroups.addAll(inputFieldSet.getInputGroups());
		this.defaultMicroflow = inputFieldSet.defaultMicroflow;
		this.validateMicroflow = inputFieldSet.getValidateMicroflow();
		this.fieldKeys().addAll(inputFieldSet.fieldKeys());
		this.getInputFields().addAll(inputFieldSet.getInputFields());
	}

	/**
	 * Add fields from an Equation API
	 * <p>
	 * Note that any combination adding Input Fields, Load API and Update API may be specified
	 * <p>
	 * The attemptValidation parameters allow control over the adding of validation as it cannot be guaranteed that validation is
	 * always correctly added
	 * 
	 * @param session
	 *            an <code>EquationStandardSession</code> instance, used to retrieve journal file fields
	 * @param gaeRecord
	 *            The GAE record for this Input or Enquiry API
	 * @param apiId
	 *            service API Id to uniquely identify this API
	 * @param loadAPI
	 *            a boolean indicating that a Load API definition should be created
	 * @param updateAPI
	 *            a boolean indicating that an Update API definition should be created
	 * @param isInput
	 *            a boolean indicating if Input fields should be created
	 * @param attemptValidationModules
	 *            a boolean indicating if an attempt should be made to add default validation modules
	 * @param attemptValidationMappings
	 *            a boolean indicating if an attempt should be made to add mappings for validation modules
	 * 
	 * @return the Update <code>APIFieldSet</code>
	 * @throws EQException
	 */
	public APIFieldSet addAPI(EquationStandardSession session, GAERecordDataModel gaeRecord, String apiId, boolean loadAPI,
					boolean updateAPI, boolean isInput, boolean attemptValidationModules, boolean attemptValidationMappings,
					String newField, boolean applyUpdateInExternalInput, boolean applyUpdateInRecovery,
					RepeatingDetails repeatingDetails, boolean isAdditionalInfo, String allKeyFields, List allInputFields,
					String fileKey) throws EQException
	{
		// TODO Change this code - GAE keys should be supported as a set
		String[] keyFields = gaeRecord.getKeys().split(Toolbox.CONTEXT_DELIMETER);

		APIFieldSet updateAPIFieldSet = null;
		APIFieldSet loadAPIFieldSet = null;

		updateAPIFieldSet = FunctionToolbox.getAPIFieldSet(session, apiId, gaeRecord.getApiId(), gaeRecord.getProgramRoot(),
						gaeRecord.getDescription(), IEquationStandardObject.FCT_FUL, repeatingDetails);
		updateAPIFieldSet.setFunction(getFunction());

		// set keys for additional information APIs
		if (isAdditionalInfo)
		{
			updateAPIFieldSet.updateKeyFields(allKeyFields);
			// default script for Equation File key field
			if (updateAPIFieldSet.containsField("GZEQF") && !fileKey.equals(""))
			{
				updateAPIFieldSet.getAPIField("GZEQF").setUpdateAssignment("1");
				updateAPIFieldSet.getAPIField("GZEQF").setUpdateScript(fileKey);
			}
		}

		// Need to check for key
		if (isInput)
		{
			List<APIField> fields = updateAPIFieldSet.getAPIFields();
			for (APIField field : fields)
			{
				// Is field required?
				if (!isAdditionalInfo || allInputFields.contains(field.getId()))
				{
					ACMRecordDataModel acmRecord = null;
					if (field.getType().length() > 0)
					{
						ACMRecordDataModel acmRecordDummy = new ACMRecordDataModel();
						acmRecordDummy.setTypeName(field.getType());
						IACMRecordDao acmDAO = new DaoFactory().getACMDao(session, acmRecordDummy);
						acmRecord = acmDAO.getACMRecord();
					}
					boolean isGAEKey = false;
					for (String keyField : keyFields)
					{
						if (field.getId().equals(keyField.trim()))
						{
							isGAEKey = true;
						}
					}
					boolean isKey = (!isAdditionalInfo && isGAEKey) || (isAdditionalInfo && field.isKey());
					addInputFieldFromAPIField(session, isKey, updateAPIFieldSet, field, loadAPI, updateAPI,
									attemptValidationModules, attemptValidationMappings, acmRecord);
				}
			}
			// Add new InputGroup if a new repeating Group
			if (repeatingDetails.getRepeatingGroupId().length() > 0)
			{
				InputGroup inputGroup = new InputGroup(repeatingDetails.getRepeatingGroupId());
				inputGroup.defaultLabelBaseOnId();
				addInputGroup(inputGroup);
			}
		}

		if (loadAPI)
		{
			// Load APIS live on a fieldSet
			loadAPIFieldSet = FunctionToolbox.getAPIFieldSet(session, apiId, gaeRecord.getApiId(), gaeRecord.getProgramRoot(),
							gaeRecord.getDescription(), IEquationStandardObject.FCT_FUL, repeatingDetails);
			loadAPIFieldSet.setParentFieldSet(this);

			// set keys for additional information APIs
			if (isAdditionalInfo)
			{
				loadAPIFieldSet.updateKeyFields(allKeyFields);
				// default script for Equation File key field
				if (loadAPIFieldSet.containsField("GZEQF") && !fileKey.equals(""))
				{
					loadAPIFieldSet.getAPIField("GZEQF").setLoadAssignment("1");
					loadAPIFieldSet.getAPIField("GZEQF").setLoadScript(fileKey);
				}
			}

			loadAPIFieldSet.setNewField(newField);

			// If this is the first API FieldSet then default it to being the one that determines the mode.
			if (getLoadAPIs().size() == 0)
			{
				loadAPIFieldSet.setDetermineMode(true);
			}
			getFunction().rtvPrimaryInputFieldSet().addLoadAPI(loadAPIFieldSet);
		}

		// Update APIs exist on their own at function level
		// Note this needs to be called AFTER adding the fields as InputFields,
		// as the addReservedFields method will add fields that should not
		// have corresponding InputFields
		if (updateAPI)
		{
			updateAPIFieldSet.addReservedFields();
			updateAPIFieldSet.setNewField(newField);
			updateAPIFieldSet.setAlwaysApplyInExtInput(applyUpdateInExternalInput);
			updateAPIFieldSet.setAlwaysApplyInRecovery(applyUpdateInRecovery);
			getFunction().addUpdateAPI(updateAPIFieldSet);
		}

		return updateAPIFieldSet;
	}
	/**
	 * Add fields from an Equation Table
	 * 
	 * <p>
	 * Note that any combination adding Input Fields, Load API and Update API may be specified
	 * <p>
	 * 
	 * @param unit
	 *            an <code>EqUnit</code> instance
	 * @param TableId
	 * @param apiId
	 * @param isInput
	 *            a boolean indicating if Input fields should be created
	 * @param updateAPI
	 *            a boolean indicating that an Update API definition should be created
	 * @param loadAPI
	 *            a boolean indicating that a Load API definition should be created
	 * 
	 * @return the Update <code>APIFieldSet</code>
	 * @throws EQException
	 * 
	 */
	public APIFieldSet addTable(EquationStandardSession session, String tableId, String id, String description, boolean isInput,
					boolean updateAPI, boolean loadAPI, String newField, boolean applyUpdateViaExternalInput,
					boolean applyUpdateViaRecovery, boolean globalTable) throws EQException
	{
		APIFieldSet updateTableFieldSet = null;
		APIFieldSet loadTableFieldSet = null;
		String keys = session.getUnit().getUniqueKeys("KFIL", tableId, globalTable);
		String root = session.getUnit().getTableForIndex("KFIL", tableId, globalTable);

		// This is a basic API field set we are using to get the input records from,
		// we only add it as an update API if the flag is Y

		updateTableFieldSet = FunctionToolbox.getTableFieldSet(session, id, tableId, description, keys, root,
						IEquationStandardObject.FCT_FUL, false, globalTable);

		updateTableFieldSet.setNewField(newField);
		updateTableFieldSet.setAlwaysApplyInExtInput(applyUpdateViaExternalInput);
		updateTableFieldSet.setAlwaysApplyInRecovery(applyUpdateViaRecovery);
		updateTableFieldSet.setFunction(getFunction());
		updateTableFieldSet.setGlobalLibrary(globalTable);

		// Need to check for key
		if (isInput)
		{
			List<APIField> fields = updateTableFieldSet.getAPIFields();

			for (APIField field : fields)
			{
				boolean key = keys.contains(field.getId()) && isPrimary();
				addInputFieldFromAPIField(session, key, updateTableFieldSet, field, loadAPI, updateAPI, false, false, null);
			}
		}

		if (loadAPI)
		{
			// Load APIS live on a fieldSet
			loadTableFieldSet = FunctionToolbox.getTableFieldSet(session, id, tableId, description, keys, root, "", false,
							globalTable);
			loadTableFieldSet.setParentFieldSet(this);
			loadTableFieldSet.setNewField(newField);
			loadTableFieldSet.setGlobalLibrary(globalTable);
			getFunction().rtvPrimaryInputFieldSet().addLoadAPI(loadTableFieldSet);
		}

		// Update APIs exist on their own at function level
		if (updateAPI)
		{
			updateTableFieldSet.addReservedFields();
			getFunction().addUpdateAPI(updateTableFieldSet);
		}

		return updateTableFieldSet;
	}

	/**
	 * Add fields from an Equation Table when imported as a List
	 * 
	 * <p>
	 * Note that any combination adding Input Fields, Load API and Update API may be specified
	 * <p>
	 * When importing a table as a list, this is done to show some of the fields in the list, and probably use a set of key fields
	 * to select a required subset of data.
	 * 
	 * @param session
	 *            an <code>EquationStandardSession</code> instance
	 * @param tableId
	 *            Name of the table
	 * @param id
	 *            API Id (APIFieldSet Id)
	 * @param isInput
	 *            a boolean indicating if Input fields should be created
	 * @param updateAPI
	 *            a boolean indicating that an Update API definition should be created
	 * @param loadAPI
	 *            a boolean indicating that a Load API definition should be created
	 * @param keys
	 *            a string of delimited key fields
	 * 
	 * @return the Update <code>APIFieldSet</code>
	 * @throws EQException
	 * 
	 */
	public APIFieldSet addTableAsList(EquationStandardSession session, String tableId, String id, String description,
					boolean isInput, boolean updateAPI, boolean loadAPI, String keys, RepeatingDetails repeatingDetails)
					throws EQException
	{
		APIFieldSet updateTableFieldSet = null;
		APIFieldSet loadTableFieldSet = null;
		String root = session.getUnit().getTableForIndex("KFIL", tableId, true);

		// This is a basic API field set we are using to get the input records from,
		// we only add it as an update API if the flag is Y
		updateTableFieldSet = FunctionToolbox.getTableFieldSetAsList(session, id, tableId, description, keys, root,
						IEquationStandardObject.FCT_FUL, repeatingDetails);

		updateTableFieldSet.setFunction(getFunction());

		// Need to check for key
		if (isInput)
		{
			// Add new input group, if it doesn't already exist
			InputGroup inputGroup = new InputGroup(repeatingDetails.getRepeatingGroupId());
			inputGroup.defaultLabelBaseOnId();
			addInputGroup(inputGroup);

			List<APIField> fields = updateTableFieldSet.getAPIFields();

			for (APIField field : fields)
			{
				boolean key = keys.contains(field.getId()) && isPrimary();
				addInputFieldFromAPIField(session, key, updateTableFieldSet, field, loadAPI, updateAPI, false, false, null);
			}
		}

		if (loadAPI)
		{
			// Load APIS live on a fieldSet
			loadTableFieldSet = FunctionToolbox.getTableFieldSetAsList(session, id, tableId, description, keys, root, "",
							repeatingDetails);
			loadTableFieldSet.setParentFieldSet(this);
			getFunction().rtvPrimaryInputFieldSet().addLoadAPI(loadTableFieldSet);
		}

		// Update APIs exist on their own at function level
		if (updateAPI)
		{
			updateTableFieldSet.addReservedFields();
			getFunction().addUpdateAPI(updateTableFieldSet);
		}
		return updateTableFieldSet;
	}
	/**
	 * Adds a LoadPV module to the list of Load APIs
	 * <p>
	 * Note that Load PVs are only added to the list of Load APIs; Input Fields and Update APIs are not relevant to PVs
	 * <p>
	 * 
	 * @param unit
	 *            an <code>EqUnit</code> instance
	 * @param pvId
	 *            the ID (module name) of the PV
	 * 
	 * @param apiId
	 *            an API identifier, which must be unique across both Load and Update APIs
	 * @param description
	 *            The description of the PV
	 * @param decode
	 *            The Decode value
	 * 
	 * @return the <code>APIFieldSet</code>
	 * @throws EQException
	 */
	public APIFieldSet addLoadPV(EquationStandardSession session, String pvId, String apiId, String description, String decode,
					String newField) throws EQException
	{
		// Use existing methods to create an PVFieldSet using the supplied details
		PVFieldSet pvFieldSet = new PVFieldSet(pvId, description, decode, newField, false);
		FunctionToolbox.populatePVFieldSetFields(session, pvFieldSet, null, null, false);
		// Copy PV details over to an APIFieldset and add to the collection of Load 'APIs'
		APIFieldSet apiFieldSet = new APIFieldSet(apiId, pvFieldSet);
		apiFieldSet.setNewField(newField);
		addLoadAPI(apiFieldSet);

		return apiFieldSet;
	}

	/**
	 * Adds an API definition based on a SQL query
	 * <p>
	 * 
	 * @param session
	 *            an <code>EquationStandardSession</code> instance
	 * @param tableId
	 *            Name of the table
	 * @param id
	 *            API Id (APIFieldSet Id)
	 * @param isInput
	 *            a boolean indicating if Input fields should be created
	 * 
	 * @return the <code>APIFieldSet</code>
	 * @throws EQException
	 */
	public APIFieldSet addSQLQuery(EquationStandardSession session, String id, String description, String sql, boolean isInput,
					RepeatingDetails repeatingDetails) throws EQException
	{
		APIFieldSet apiFieldSet = null;

		apiFieldSet = FunctionToolbox.getSqlFieldSet(session, id, description, sql);
		apiFieldSet.setFunction(getFunction());

		// Repeating details
		apiFieldSet.setRepeatingGroup(repeatingDetails.getRepeatingGroupId());
		apiFieldSet.setRepeatingGroupLoadMode(repeatingDetails.getJoinType());
		apiFieldSet.setRepeatingGroupLoadJoinAPIField(repeatingDetails.getJoinAPIFields());
		apiFieldSet.setRepeatingGroupLoadJoinInputField(repeatingDetails.getInputFields());

		// Need to check for key
		if (isInput)
		{
			// Add new input group, if it doesn't already exist
			InputGroup inputGroup = new InputGroup(repeatingDetails.getRepeatingGroupId());
			inputGroup.defaultLabelBaseOnId();
			addInputGroup(inputGroup);

			for (APIField field : apiFieldSet.getAPIFields())
			{
				boolean key = field.isKey() && isPrimary();
				addInputFieldFromAPIField(session, key, apiFieldSet, field, true, false, false, false, null);
			}
		}

		// add load API
		getFunction().rtvPrimaryInputFieldSet().addLoadAPI(apiFieldSet);

		return apiFieldSet;
	}

	/**
	 * Edits an existing API definition that is based on a SQL query
	 * <p>
	 * 
	 * @param session
	 *            an <code>EquationStandardSession</code> instance
	 * @param id
	 *            API Id (APIFieldSet Id)
	 * @param description
	 *            the query description (currently the sql statement)
	 * @param sql
	 *            a <code>String</code> containing the sql statement
	 * @param isInput
	 *            a boolean indicating if Input fields should be created
	 * @param repeatingDetails
	 *            details of the repeating group
	 * @param oldFieldSet
	 *            the previous API Field Set
	 * 
	 * @return the <code>APIFieldSet</code>
	 * @throws EQException
	 */
	public List<String> editSQLQuery(EquationStandardSession session, String id, String description, String sql, boolean isInput,
					RepeatingDetails repeatingDetails, APIFieldSet oldFieldSet, String languageId, String owner,
					Translation translation) throws EQException
	{
		// Fields that have had their ids change (old id / new id)
		// Map<String, String> idChanges = new HashMap<String, String>();
		List<String> idChanges = new ArrayList<String>();

		// Get the new API field set from the new SQL
		APIFieldSet newFieldSet = null;

		newFieldSet = FunctionToolbox.getSqlFieldSet(session, id, description, sql);
		newFieldSet.setFunction(getFunction());

		// Repeating details
		newFieldSet.setRepeatingGroup(repeatingDetails.getRepeatingGroupId());
		newFieldSet.setRepeatingGroupLoadMode(repeatingDetails.getJoinType());
		newFieldSet.setRepeatingGroupLoadJoinAPIField(repeatingDetails.getJoinAPIFields());
		newFieldSet.setRepeatingGroupLoadJoinInputField(repeatingDetails.getInputFields());

		if (oldFieldSet.getFunction() == null)
		{
			oldFieldSet.setFunction(getFunction());
		}

		// Retrieve PARAM reference fields
		List<String> newParamFields = FunctionToolbox.getSQLParamFields(newFieldSet);
		List<String> oldParamFields = FunctionToolbox.getSQLParamFields(oldFieldSet);

		// Remove unwanted PARAM fields
		int paramIndex = 0;
		List<String> copyNewParamFields = new ArrayList<String>(newParamFields);
		for (String oldParam : oldParamFields)
		{
			paramIndex++;
			if (!copyNewParamFields.contains(oldParam) && oldFieldSet.containsField(oldParam))
			{
				APIField oldField = oldFieldSet.getAPIField(EquationStandardQueryList.PARM_PREFIX + paramIndex);
				if (isInput)
				{
					String inputFieldId = FunctionToolbox.getInputFieldNameFromAPIField(oldField);
					if (containsField(inputFieldId))
					{
						// if the user removed the input field, also remove the corresponding reference texts if any from the
						// translation bean
						TranslationToolbox.removeText(getField(inputFieldId), languageId, owner, translation);
						removeField(inputFieldId);
					}
				}
				// if the user removed the input field, also remove the corresponding reference texts if any from the
				// translation bean
				TranslationToolbox.removeText(oldField, languageId, owner, translation);
				oldFieldSet.removeField(oldField);
			}
			else
			{
				int oldIndex = copyNewParamFields.indexOf(oldParam);
				copyNewParamFields.remove(oldIndex);
				copyNewParamFields.add(oldIndex, null);
			}
		}

		// Rename existing PARAM fields; add new PARAM fields
		for (APIField field : oldFieldSet.getAPIFields())
		{
			if (field.getId().startsWith(EquationStandardQueryList.PARM_PREFIX))
			{
				String oldInputFieldId = FunctionToolbox.getInputFieldNameFromAPIField(field);
				String oldApiFieldId = field.getId();
				updateFieldDescriptionLabel(field, "X" + field.getId(), isInput);
				oldFieldSet.modifyAPIFieldId(field, "X" + field.getId());
				if (isInput)
				{
					if (containsField(oldInputFieldId))
					{
						modifyFieldId(oldInputFieldId, FunctionToolbox.getInputFieldNameFromAPIField(field));
						idChanges.add(oldFieldSet.getId().trim() + FunctionToolbox.UNDERSCORE + oldApiFieldId
										+ EqDataType.GLOBAL_DELIMETER + oldFieldSet.getId().trim() + FunctionToolbox.UNDERSCORE
										+ field.getId());
					}
				}
			}
		}
		paramIndex = 0;
		for (String newParam : newParamFields)
		{
			paramIndex++;
			if (oldParamFields.contains(newParam))
			{
				// rename
				int oldIndex = oldParamFields.indexOf(newParam);
				oldParamFields.remove(oldIndex);
				oldParamFields.add(oldIndex, null);
				oldIndex++;
				String oldApiFieldId = "X" + EquationStandardQueryList.PARM_PREFIX + oldIndex;
				APIField apiField = oldFieldSet.getAPIField(oldApiFieldId);
				String oldInputFieldId = FunctionToolbox.getInputFieldNameFromAPIField(apiField);
				updateFieldDescriptionLabel(apiField, EquationStandardQueryList.PARM_PREFIX + paramIndex, isInput);
				oldFieldSet.modifyAPIFieldId(apiField, EquationStandardQueryList.PARM_PREFIX + paramIndex);
				if (isInput)
				{
					if (containsField(oldInputFieldId))
					{
						modifyFieldId(oldInputFieldId, FunctionToolbox.getInputFieldNameFromAPIField(apiField));
						idChanges.add(oldFieldSet.getId().trim() + FunctionToolbox.UNDERSCORE + oldApiFieldId
										+ EqDataType.GLOBAL_DELIMETER + oldFieldSet.getId().trim() + FunctionToolbox.UNDERSCORE
										+ apiField.getId());
					}
				}
			}
			else
			{
				// add
				APIField newField = newFieldSet.getAPIField(EquationStandardQueryList.PARM_PREFIX + paramIndex);
				oldFieldSet.addAPIField(new APIField(newField));
				if (isInput)
				{
					boolean key = newField.isKey() && isPrimary();
					addInputFieldFromAPIField(session, key, newFieldSet, newField, true, false, false, false, null);
				}
			}
		}

		// Remove unwanted fields
		List<APIField> oldFields = new ArrayList<APIField>(oldFieldSet.getAPIFields());
		for (APIField field : oldFields)
		{
			if (!newFieldSet.containsField(field.getId()) && !field.getId().startsWith(EquationStandardQueryList.PARM_PREFIX)
							&& !APIFieldSet.chkReservedFieldNames(field.getId()))
			{
				// if the user removed the input field, also remove the corresponding reference texts if any from the
				// translation bean
				TranslationToolbox.removeText(field, languageId, owner, translation);
				oldFieldSet.removeField(field);
				if (isInput)
				{
					String inputFieldId = FunctionToolbox.getInputFieldNameFromAPIField(field);
					if (containsField(inputFieldId))
					{
						// if the user removed the input field, also remove the corresponding reference texts if any from the
						// translation bean
						TranslationToolbox.removeText(getField(inputFieldId), languageId, owner, translation);
						removeField(inputFieldId);
					}
				}
			}
		}

		// Add fields
		List<APIField> newFields = new ArrayList<APIField>(newFieldSet.getAPIFields());
		for (APIField field : newFields)
		{
			if (!oldFieldSet.containsField(field.getId()) && !field.getId().startsWith(EquationStandardQueryList.PARM_PREFIX))
			{
				oldFieldSet.addAPIField(new APIField(field));
				if (isInput)
				{
					boolean key = field.isKey() && isPrimary();
					addInputFieldFromAPIField(session, key, newFieldSet, field, true, false, false, false, null);
				}
			}
		}

		// Sort API fields
		paramIndex = 0;
		for (APIField field : newFields)
		{
			oldFieldSet.swapAPIField(paramIndex++, field.getId());
		}

		// update the API's description etc with the new SQL statement,
		oldFieldSet.setLabel(description);
		oldFieldSet.setDescription(description);
		oldFieldSet.setRoot(sql);

		return idChanges;
	}
	/**
	 * Helper method to update a fields description and label if the field is going to be renamed
	 * 
	 * @param field
	 *            - the API field to be maintained
	 * @param newId
	 *            - the fields new id
	 * @param isInput
	 *            a boolean indicating if Input fields should be created
	 */
	private void updateFieldDescriptionLabel(APIField field, String newId, boolean isInput) throws EQException
	{
		String oldId = field.getId();

		// API field description and label
		if (field.getDescription().equals(oldId))
		{
			field.setDescription(newId);
		}
		if (field.getLabel().equals(oldId))
		{
			field.setLabel(newId);
		}

		// Input field description and label
		if (isInput)
		{
			String inputFieldId = FunctionToolbox.getInputFieldNameFromAPIField(field);
			if (containsField(inputFieldId))
			{
				InputField inputField = getInputField(inputFieldId);
				if (inputField.getDescription().equals(oldId))
				{
					inputField.setDescription(newId);
				}
				if (inputField.getLabel().equals(oldId))
				{
					inputField.setLabel(newId);
				}
			}
		}
	}
	/**
	 * Adds a new InputField, based on the supplied APIField.
	 * <p>
	 * This method is used both when initially adding an API and if subsequently re-adding a field from an existing API.
	 * <p>
	 * The caller must have previously ensured that the field does not already exist in the InputFieldSet, or an exception will be
	 * thrown.
	 * 
	 * @param session
	 *            an <code>EquationStandardSession</code>
	 * @param isKey
	 *            indicates whether the field is a key field. However if the apiField is properly populated, this parameter could be
	 *            removed.
	 * @param apiFieldSet
	 *            The <code>APIFieldSet</code> which is used
	 * @param apiField
	 *            The <code>APIField</code> used as a base for the <code>InputField</code>
	 * @param addLoadMappings
	 *            Indicates whether load mappings should be created
	 * @param addUpdateMappings
	 *            Indicates whether update mappings should be created
	 * @param addValidateMappings
	 *            Indicates whether validate mappings should be created
	 * @param acmRecord
	 *            an <code>IACMRecordDao</code> (or null)
	 * @return the new <code>InputField</code> instance
	 */
	public InputField addInputFieldFromAPIField(EquationStandardSession session, boolean isKey, APIFieldSet apiFieldSet,
					APIField apiField, boolean addLoadMappings, boolean addUpdateMappings, boolean addValidateModules,
					boolean addValidateMappings, ACMRecordDataModel acmRecord)
	{
		// Create an input field
		InputField inputField;

		if (acmRecord == null)
		{
			inputField = FunctionToolbox.getInputField(apiField);
		}
		else
		{
			inputField = new InputField();
			inputField.setId(FunctionToolbox.getInputFieldNameFromAPIField(apiField));
			inputField.setLabel(apiField.getLabel());

			// Following values are from the ACM record:
			inputField.setDescription(acmRecord.getDescription());
			inputField.setDataType(acmRecord.getDataType());
			inputField.setSize(Integer.toString(acmRecord.getLength()));
			// Only set decimals if a numeric field:
			if (EqDataType.TYPE_PACKED.equals(inputField.getDataType()) || EqDataType.TYPE_ZONED.equals(inputField.getDataType()))
			{
				inputField.setDecimals(Integer.toString(acmRecord.getDecimals()));
			}

			inputField.setType(acmRecord.getTypeName());
			inputField.setLocked(Boolean.TRUE.toString());
			inputField.setUpperCase(acmRecord.isUpperCase());
			if (acmRecord.getInitialValue().length() > 0)
			{
				inputField.setInitialValueType(WorkField.INIT_VALUE_CONSTANT);
				inputField.setInitialValue(acmRecord.getInitialValue());
			}
			inputField.setMaxLength(acmRecord.getMaxLength());
			inputField.setMinLength(acmRecord.getMinLength());
			inputField.setValidValues(acmRecord.getValidValues());
			inputField.setRegExp(acmRecord.getRegEx());
		}

		// Regardless of whether the field definition is from the ACMPF
		// or from the API field, set the repeating group Id if required
		if (Field.isRepeating(apiField))
		{
			inputField.setRepeatingGroup(apiFieldSet.getRepeatingGroup());
		}

		try
		{
			// Set whether the field is key
			inputField.setKey(isKey && isPrimary());

			// cement its position in the tree
			addInputField(inputField);

			// add to XSD
			if (Field.isRepeating(apiField))
			{
				XSDStructureHelper
								.appendFieldToGroupXSD(getFunction(), inputField, apiFieldSet.getRepeatingGroup(), "", true, true);
			}
			else
			{
				XSDStructureHelper.appendFieldToXSD(getFunction(), inputField);
			}

			// Add validation modules if appropriate
			if (addValidateModules
							&& (apiFieldSet.getType().equals(IEquationStandardObject.TYPE_TRANSACTION) || apiFieldSet.getType()
											.equals(IEquationStandardObject.TYPE_ENQUIRY)))
			{
				XVRecordDataModel record = new XVRecordDataModel(apiFieldSet.getRoot().trim() + "VR", "ZL"
								+ apiField.getId().substring(2));
				IXVRecordDao xvDao = new DaoFactory().getXVDao(session, record);
				XVRecordDataModel model = xvDao.getXVRecord();
				// TODO: Oops - the field name doesn't always match on the XV so we can't really restrict it here
				if (model != null)
				{
					String pvModuleName = model.getPvModule().trim();
					boolean narrativePV = false;
					for (String npv : narrativePVs)
					{
						if (npv.equals(pvModuleName))
						{
							narrativePV = true;
						}
					}
					if (narrativePV == false)
					{
						PVFieldSet pvfs = new PVFieldSet(pvModuleName, "", model.getDecode().trim(), model.getNewCode().trim(),
										Boolean.parseBoolean(model.getBlankAllowed()));
						// Cement the PVfieldSet into the input field
						if (addValidateModules)
						{
							inputField.addPvFieldSet(pvfs);
						}

						// Populate the PVfieldSet
						FunctionToolbox.populatePVFieldSetFields(session, pvfs, inputField, getFunction(), addValidateMappings);
					}
				}
			}

			// Add an Load Mapping if we're adding an Load API
			if (addLoadMappings)
			{
				String apiPath = apiField.rtvPath();
				if (apiField.rtvParentFieldSet().rtvParent() == null)
				{
					// If the apiFieldSet parent of the apiField has not yet been added
					// into the bean model (when initially adding the API), the
					// InputFieldSet part of the path needs to be explicitly prefixed:
					// apiPath = inputField.rtvParentFieldSet().rtvPath() + "." + apiPath;
					apiPath = getFunction().rtvPrimaryInputFieldSet().rtvPath() + "." + apiPath;
				}

				if (isKey)
				{
					// Add load mapping from Input Field to API field
					getFunction().addLoadMapping(inputField.rtvPath(), apiPath);

				}
				// Always map from the API to the Input field
				getFunction().addLoadMapping(apiPath, inputField.rtvPath() + MappingToolbox.SUBFIELD_PRIMITIVE);
			}

			// Add an Update mapping if we're adding an Update API
			if (addUpdateMappings)
			{
				getFunction().addUpdateMapping(inputField.rtvPath(), apiField.rtvPath());
			}
		}
		catch (EQException e)
		{
			throw new EQRuntimeException(e);
		}
		return inputField;
	}
	/**
	 * Move the API field set one position up
	 * 
	 * @param fieldSet
	 *            - the API field set to be moved
	 * 
	 * @throws EQException
	 *             - if the field set does not exist
	 */
	public void moveLoadAPIUp(APIFieldSet fieldSet) throws EQException
	{
		String key = fieldSet.getId();
		if (!loadAPIKeys.contains(key))
		{
			throw (new EQException(LanguageResources.getString("Function.FieldSetDoesNotExist"))); //$NON-NLS-1$
		}
		else
		{
			int position = loadAPIKeys.indexOf(key);
			if (position > 0)
			{
				loadAPIKeys.remove(position);
				loadAPIKeys.add(position - 1, key);
				loadAPIs.remove(position);
				loadAPIs.add(position - 1, fieldSet);
			}
		}
	}

	/**
	 * Move the API field set one position down
	 * 
	 * @param fieldSet
	 *            - the API field set to be moved
	 * 
	 * @throws EQException
	 *             - if the field set does not exist
	 */
	public void moveLoadAPIDown(APIFieldSet fieldSet) throws EQException
	{
		String key = fieldSet.getId();
		if (!loadAPIKeys.contains(key))
		{
			throw (new EQException(LanguageResources.getString("Function.FieldSetDoesNotExist"))); //$NON-NLS-1$
		}
		else
		{
			int position = loadAPIKeys.indexOf(key);
			if (position < loadAPIKeys.size() - 1)
			{
				loadAPIKeys.remove(position);
				loadAPIKeys.add(position + 1, key);
				loadAPIs.remove(position);
				loadAPIs.add(position + 1, fieldSet);
			}
		}
	}

	/**
	 * Return the API field sets of the function
	 * 
	 * @return the API field sets of the function
	 */
	public List<APIFieldSet> getLoadAPIs()
	{
		return loadAPIs;
	}

	/**
	 * Return the API field key sets of the function
	 * 
	 * @return the API field key sets of the function
	 */
	// Note this method is intentionally not called getAPIKeys, this is because it should not form part of the bean
	// serialisation
	public List<String> loadAPIKeys()
	{
		return loadAPIKeys;
	}

	/**
	 * Add an API field set into the function
	 * 
	 * @param fieldSet
	 *            - the field set to be added
	 * 
	 * @throws EQException
	 *             - if an API field set with the same ID already exists
	 */
	public void addLoadAPI(APIFieldSet fieldSet) throws EQException
	{
		String key = fieldSet.getId();
		// If this is an API (either Input or Enquiry), then add the "MODE" field
		if (IEquationStandardObject.TYPE_TRANSACTION.equals(fieldSet.getType())
						|| IEquationStandardObject.TYPE_ENQUIRY.equals(fieldSet.getType()))
		{
			fieldSet.addReservedFields();
		}

		if (loadAPIKeys.contains(key))
		{
			throw (new EQException(LanguageResources.getFormattedString("InputFieldSet.LoadAPIFieldSetAlreadyExists", key))); //$NON-NLS-1$
		}
		else
		{
			loadAPIs.add(fieldSet);
			loadAPIKeys.add(key);
			fieldSet.setParentFieldSet(this);
			fieldSet.setFunction(getFunction());
		}
	}
	/**
	 * Determines whether the given load API exists in the input field set.
	 * <p>
	 * This is used by the Field Copy facility.
	 * 
	 * @param apiId
	 * @return a <code>boolean</code> indicating whether the API exists
	 */
	public boolean hasLoadAPI(String apiId)
	{
		if (!loadAPIKeys.contains(apiId))
		{
			return false;
		}
		else
		{

			return true;
		}
	}
	/**
	 * Return the API field set identified by the id
	 * 
	 * @param key
	 *            - the API field set ID
	 * 
	 * @return the API field set identified by the key
	 * 
	 * @throws EQException
	 *             - if the API field set does not exist
	 */
	public APIFieldSet getLoadAPI(String key) throws EQException
	{
		if (!loadAPIKeys.contains(key))
		{
			throw new EQException(LanguageResources.getFormattedString("InputFieldSet.LoadAPIFieldSetDoesNotExist", key)); //$NON-NLS-1$
		}
		else
		{
			int keyIndex = loadAPIKeys.indexOf(key);
			return loadAPIs.get(keyIndex);
		}
	}
	/**
	 * Return the API field set sequence identified by the id
	 * 
	 * @param key
	 *            - the API field set ID
	 * 
	 * @return the API field set sequence identified by the key
	 * 
	 * @throws EQException
	 *             - if the API field set does not exist
	 */
	public int rtvLoadAPISequence(String key) throws EQException
	{
		if (!loadAPIKeys.contains(key))
		{
			throw new EQException(LanguageResources.getFormattedString("InputFieldSet.LoadAPIFieldSetDoesNotExist", key)); //$NON-NLS-1$
		}
		else
		{
			int keyIndex = loadAPIKeys.indexOf(key);
			return keyIndex;
		}
	}

	/**
	 * Remove the API field set from the function
	 * 
	 * @param fieldSet
	 *            - the API field set to be removed
	 * 
	 * @throws EQException
	 *             - if the API field set does not exist
	 */
	public void removeLoadAPI(APIFieldSet fieldSet) throws EQException
	{
		String key = fieldSet.getId();
		if (!loadAPIKeys.contains(key))
		{
			throw new EQException(LanguageResources.getFormattedString("InputFieldSet.LoadAPIFieldSetDoesNotExist", key)); //$NON-NLS-1$
		}
		else
		{
			List<Field> fields = fieldSet.getFields();
			for (Field field : fields)
			{
				getFunction().removeUpdateMappings(field);
				getFunction().removeValidateMappings(field);
				getFunction().removeLoadMappings(field);
			}
			int keyIndex = loadAPIKeys.indexOf(key);
			loadAPIs.remove(keyIndex);
			loadAPIKeys.remove(keyIndex);
		}
	}

	/**
	 * Return the user exit for defaulting of fields of the field set
	 * 
	 * @return the default user exit of the field set
	 */
	public String getDefaultUserExit()
	{
		return defaultUserExit;
	}

	/**
	 * Set the user exit for defaulting of fields of the field set
	 * 
	 * @param defaultUserExit
	 *            - the default user exit of the field
	 */
	public void setDefaultUserExit(String defaultUserExit)
	{
		this.defaultUserExit = defaultUserExit;
	}

	/**
	 * Return the user exit for validating the fields of the field set
	 * 
	 * @return the default validate user exit of the field set
	 */
	public String getValidateUserExit()
	{
		return validateUserExit;
	}

	/**
	 * Get the <code>InputField</code>s on this <code>InputFieldSet</code>
	 * 
	 * @return an <code>List</code> of <code>InputField</code>s
	 */
	public List<InputField> getInputFields()
	{
		return super.getFields();
	}

	/**
	 * Add an <code>InputField</code> to this <code>InputFieldSet</code>
	 * 
	 * @param field
	 * 
	 * @throws EQException
	 */
	public void addInputField(InputField field) throws EQException
	{
		super.addField(field);
	}
	/**
	 * Add an <code>InputField</code> to this <code>InputFieldSet at the specified location.</code>
	 * 
	 * @param field
	 * 
	 * @throws EQException
	 */
	public void addInputField(int index, InputField field) throws EQException
	{
		super.addField(index, field);
	}
	/**
	 * Swap an <code>InputField</code> with the one in the specified position
	 * 
	 * @param index
	 *            the zero-based index to move the field to
	 * @param id
	 *            a <code>String</code> containing the field id
	 * @throws EQException
	 */
	public void swapInputField(int index, String id) throws EQException
	{
		super.swapField(index, id);
	}

	/**
	 * Get an <code>InputField</code> by key
	 * 
	 * @param key
	 *            - The key of the <code>InputField</code>
	 * 
	 * @return - the <code>InputField</code>
	 * 
	 * @throws EQException
	 */
	public InputField getInputField(String key) throws EQException
	{
		return (InputField) super.getField(key);
	}

	/**
	 * Set the user exit for validating the fields of the field set
	 * 
	 * @param validateUserExit
	 *            - The user exit for validating the fields of the field set
	 */
	public void setValidateUserExit(String validateUserExit)
	{
		this.validateUserExit = validateUserExit;
	}

	/**
	 * Returns true if this Input field set is the primary Input field set
	 * 
	 * @return
	 */
	public boolean isPrimary()
	{
		return getId().equals(Function.PRIMARY_ID);
	}

	/**
	 * Gets the defaultMicroflow property.
	 */
	public boolean getDefaultMicroflow()
	{
		return defaultMicroflow;
	}

	/**
	 * Sets the defaultMicroflow property.
	 */
	public void setDefaultMicroflow(boolean defaultMicroflow)
	{
		this.defaultMicroflow = defaultMicroflow;
	}

	/**
	 * Gets the validateMicroflow property.
	 */
	public boolean getValidateMicroflow()
	{
		return validateMicroflow;
	}

	/**
	 * Sets the validateMicroflow property.
	 */
	public void setValidateMicroflow(boolean validateMicroflow)
	{
		this.validateMicroflow = validateMicroflow;
	}

	/**
	 * Indicates whether the specified group Id exists
	 * 
	 * @param groupId
	 * @return boolean
	 */
	public boolean hasInputGroup(String groupId)
	{
		for (InputGroup group : inputGroups)
		{
			if (group.getId().equals(groupId))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds an InputGroup
	 * 
	 * @param newGroup
	 */
	public void addInputGroup(InputGroup newGroup)
	{
		// this.inputGroups.add(
		if (!hasInputGroup(newGroup.getId()))
		{
			inputGroups.add(newGroup);
		}
	}

	/**
	 * Removes an InputGroup
	 * 
	 * @param groupId
	 *            - the id of the input group to be removed
	 * @param removeAssociations
	 *            - if true then also remove all associated Input Fields and mappings and remove all references to this Input Group
	 *            in API Fields and API Field Sets.
	 */
	public void removeInputGroup(String groupId, boolean removeInputAssociations) throws EQException
	{
		InputGroup group = getInputGroup(groupId);
		if (group == null)
		{
			throw new EQException(LanguageResources.getFormattedString("InputFieldSet.InputGroupDoesNotExist", groupId)); //$NON-NLS-1$
		}
		else
		{
			if (removeInputAssociations)
			{
				List<InputField> removes = getInputGroupFields(groupId);
				for (InputField exField : removes)
				{
					removeField(exField);
				}
			}

			// remove input group
			inputGroups.remove(group);

			// remove group from XSD
			XSDStructureHelper.removeRepeatingGroupFromXSD(getFunction(), group.getId());
		}
	}

	/**
	 * Get the list of InputGroups held by this InputFieldSet
	 * 
	 * @return an <code>List</code> of <code>InputGroups</code>s
	 */
	public List<InputGroup> getInputGroups()
	{
		return inputGroups;
	}

	/**
	 * Gets the specified InputGroup
	 * 
	 * @param groupId
	 * @return the InputGroup or null if it does not exist
	 */
	public InputGroup getInputGroup(String groupId)
	{
		for (InputGroup group : inputGroups)
		{
			if (group.getId().equals(groupId))
			{
				return group;
			}
		}
		return null;
	}

	/**
	 * Get the list of InputGroup Ids
	 * 
	 * @return Return a list of input group Ids
	 */
	public List<String> rtvInputGroupIds()
	{
		List<String> ids = new ArrayList<String>();
		for (InputGroup group : getInputGroups())
		{
			ids.add(group.getId());
		}

		return ids;
	}

	/**
	 * Get the list of fields from the specified InputGroup
	 * 
	 * @param groupId
	 * @return the <code>List</code> of <code>InputField</code>s from the group
	 */
	public List<InputField> getInputGroupFields(String groupId)
	{
		List<InputField> fields = new ArrayList<InputField>();
		List<InputField> allFields = super.getFields();
		for (InputField field : allFields)
		{
			if (field.getRepeatingGroup().equals(groupId))
			{
				fields.add(field);
			}
		}
		return fields;
	}

	/**
	 * Move up a group of fields
	 * 
	 * The first field in the repeating group is moved up a position. If the position above is part of a different group then the
	 * first field is moved above the whole group. All the other fields in the group are moved sequentially after the first one.
	 * 
	 * @param group
	 *            - the repeating group of fields to be moved
	 * 
	 * @throws EQException
	 *             - if the group does not exist
	 */
	public void moveFieldGroupUp(InputGroup group) throws EQException
	{
		if (!inputGroups.contains(group))
		{
			throw new EQException(LanguageResources.getFormattedString("InputFieldSet.InputGroupDoesNotExist", group.getId())); //$NON-NLS-1$
		}
		else
		{
			List<InputField> fieldsInGroup = getInputGroupFields(group.getId());

			// only if group is not already at the top
			if (fieldKeys().indexOf(fieldsInGroup.get(0).getId()) > 0)
			{
				int moveToPosition = 0;
				for (int i = 0; i < fieldsInGroup.size(); i++)
				{
					InputField groupField = fieldsInGroup.get(i);
					int position = fieldKeys().indexOf(groupField.getId());
					// first pass
					if (i == 0)
					{
						moveToPosition = position - 1;
						// if the position above contains another group then move up above the whole group
						String groupAbove = getInputFields().get(moveToPosition).getRepeatingGroup();
						if (!groupAbove.equals(""))
						{
							while (moveToPosition > 0
											&& groupAbove.equals(getInputFields().get(moveToPosition - 1).getRepeatingGroup()))
							{
								moveToPosition--;
							}
							// ...also move the InputGroup up
							moveInputGroupUp(group);
						}
					}
					else
					{
						moveToPosition++;
					}

					// move field
					if (moveToPosition >= 0)
					{
						fieldKeys().remove(position);
						fieldKeys().add(moveToPosition, groupField.getId());
						getInputFields().remove(position);
						getInputFields().add(moveToPosition, groupField);
					}
				}
			}
		}
	}
	/**
	 * Move down a group of fields
	 * 
	 * The first field in the repeating group is moved down to one position below the last field. If there is a different group
	 * directly below this one then the first field is moved below the second group. All the other fields in the group are moved
	 * sequentially after the first one.
	 * 
	 * @param group
	 *            - the repeating group of fields to be moved
	 * 
	 * @throws EQException
	 *             - if the group does not exist
	 */
	public void moveFieldGroupDown(InputGroup group) throws EQException
	{
		if (!inputGroups.contains(group))
		{
			throw new EQException(LanguageResources.getFormattedString("InputFieldSet.InputGroupDoesNotExist", group.getId())); //$NON-NLS-1$
		}
		else
		{
			List<InputField> fieldsInGroup = getInputGroupFields(group.getId());
			int lastIndex = fieldsInGroup.size() - 1;

			// only if group is not already at the bottom
			if (fieldKeys().indexOf(fieldsInGroup.get(lastIndex).getId()) < (fieldKeys().size() - 1))
			{
				int moveToPosition = 0;
				for (int i = lastIndex; i >= 0; i--)
				{
					InputField groupField = fieldsInGroup.get(i);
					int position = fieldKeys().indexOf(groupField.getId());
					// first pass
					if (i == lastIndex)
					{
						moveToPosition = position + 1;
						// if the position below contains another group then move down below the whole group
						String groupBelow = getInputFields().get(moveToPosition).getRepeatingGroup();
						if (!groupBelow.equals(""))
						{
							while (moveToPosition < fieldKeys().size() - 1
											&& groupBelow.equals(getInputFields().get(moveToPosition + 1).getRepeatingGroup()))
							{
								moveToPosition++;
							}
							// ...also move the InputGroup down
							moveInputGroupDown(group);
						}
					}
					else
					{
						moveToPosition--;
					}

					// move field
					if (moveToPosition < fieldKeys().size())
					{
						fieldKeys().remove(position);
						fieldKeys().add(moveToPosition, groupField.getId());
						getInputFields().remove(position);
						getInputFields().add(moveToPosition, groupField);
					}
				}
			}
		}
	}
	/**
	 * Move the input field one position up
	 * 
	 * The input field is moved up one position. If the position above is part of a repeating group then the field is moved up above
	 * the whole group.
	 * 
	 * @param field
	 *            - the field to be moved
	 * 
	 * @throws EQException
	 *             - if the field does not exist
	 */
	public void moveFieldUpOne(InputField field) throws EQException
	{
		String key = field.getId();
		if (!fieldKeys().contains(key))
		{
			throw new EQException(LanguageResources.getFormattedString("FieldSet.FieldDoesNotExist", new String[] { key, getId() })); //$NON-NLS-1$
		}
		else
		{
			int position = fieldKeys().indexOf(key);
			if (position > 0)
			{
				int moveToPosition = position - 1;
				moveFieldAbove(field, position, moveToPosition);
			}
		}
	}
	/**
	 * Move an input field to above the specified position
	 * 
	 * The input field is moved to above the specified position. If the specified position is a repeating group then the field is
	 * moved up above the whole group.
	 * 
	 * @param field
	 *            - the field to be moved
	 * @param position
	 *            - the fields position
	 * @param moveToPosition
	 *            - the position that the field will be moved above
	 */
	private void moveFieldAbove(InputField field, int position, int moveToPosition)
	{
		// if the position above contains another group then move up above the whole group
		String groupAbove = getInputFields().get(moveToPosition).getRepeatingGroup();
		if (!groupAbove.equals("") && !groupAbove.equals(getInputFields().get(position).getRepeatingGroup()))
		{
			while (moveToPosition > 0 && groupAbove.equals(getInputFields().get(moveToPosition - 1).getRepeatingGroup()))
			{
				moveToPosition--;
			}
		}
		if (moveToPosition > position)
		{
			moveToPosition--;
		}
		fieldKeys().remove(position);
		fieldKeys().add(moveToPosition, field.getId());
		getInputFields().remove(position);
		getInputFields().add(moveToPosition, field);
	}
	/**
	 * Move the input field one position down
	 * 
	 * The input field is moved down one position. If the position below is part of a repeating group then the field is moved down
	 * below the whole group.
	 * 
	 * @param field
	 *            - the field to be moved
	 * 
	 * @throws EQException
	 *             - if the field does not exist
	 */
	public void moveFieldDownOne(InputField field) throws EQException
	{
		String key = field.getId();
		if (!fieldKeys().contains(key))
		{
			throw new EQException(LanguageResources.getFormattedString("FieldSet.FieldDoesNotExist", new String[] { key, getId() })); //$NON-NLS-1$
		}
		else
		{
			int position = fieldKeys().indexOf(key);
			if (position < (fieldKeys().size() - 1))
			{
				int moveToPosition = position + 1;
				moveFieldBelow(field, position, moveToPosition);
			}
		}
	}
	/**
	 * Move an input field to below the specified position
	 * 
	 * The input field is moved to below the specified position. If the specified position is a repeating group then the field is
	 * moved to below the whole group.
	 * 
	 * @param field
	 *            - the field to be moved
	 * @param position
	 *            - the fields position
	 * @param moveToPosition
	 *            - the position that the field will be moved below
	 */
	private void moveFieldBelow(InputField field, int position, int moveToPosition)
	{
		int lastIndex = fieldKeys().size() - 1;
		// if the position below contains another group then move down below the whole group
		String groupBelow = getInputFields().get(moveToPosition).getRepeatingGroup();
		if (!groupBelow.equals("") && !groupBelow.equals(getInputFields().get(position).getRepeatingGroup()))
		{
			while (moveToPosition < lastIndex && groupBelow.equals(getInputFields().get(moveToPosition + 1).getRepeatingGroup()))
			{
				moveToPosition++;
			}
		}
		if (moveToPosition < position)
		{
			moveToPosition++;
		}
		fieldKeys().remove(position);
		fieldKeys().add(moveToPosition, field.getId());
		getInputFields().remove(position);
		getInputFields().add(moveToPosition, field);
	}
	/**
	 * Move an input field to below the specified position
	 * 
	 * The input field is moved to below the specified position. If the specified position is a repeating group then the field is
	 * moved to below the whole group.
	 * 
	 * @param field
	 *            - the field to be moved
	 * @param position
	 *            - the fields position
	 * @param moveToPosition
	 *            - the position that the field will be moved below
	 */
	public void copyFieldBelow(InputField field)
	{
		int lastIndex = fieldKeys().size() - 1;
		fieldKeys().add(lastIndex, field.getId());
		getInputFields().add(lastIndex, field);
	}
	/**
	 * Move an input field to the position above or below another input field
	 * 
	 * The input field is moved either above or below the specified position. If the specified position is a repeating group then
	 * the field is moved above or below the whole group.
	 * 
	 * @param moveId
	 *            - the id of the field to be moved
	 * @param toId
	 *            - the id of the field to be moved to (above or below)
	 * @param moveAbove
	 *            - if true move above the field, if false move below it
	 * 
	 * @throws EQException
	 *             - if either field does not exist
	 */
	public void moveFieldToField(String moveId, String toId, boolean moveAbove) throws EQException
	{
		if (!fieldKeys().contains(moveId))
		{
			throw new EQException(LanguageResources.getFormattedString(
							"FieldSet.FieldDoesNotExist", new String[] { moveId, getId() })); //$NON-NLS-1$
		}
		else if (!fieldKeys().contains(toId))
		{
			throw new EQException(LanguageResources
							.getFormattedString("FieldSet.FieldDoesNotExist", new String[] { toId, getId() })); //$NON-NLS-1$
		}
		else
		{
			int position = fieldKeys().indexOf(moveId);
			int moveToPosition = fieldKeys().indexOf(toId);
			if (moveAbove)
			{
				moveFieldAbove(getInputFields().get(position), position, moveToPosition);
			}
			else
			{
				moveFieldBelow(getInputFields().get(position), position, moveToPosition);
			}
		}
	}

	/**
	 * Move an input field to the position above or below another input field
	 * 
	 * The input field is moved either above or below the specified position. If the specified position is a repeating group then
	 * the field is moved above or below the whole group.
	 * 
	 * @param moveId
	 *            - the id of the field to be moved
	 * @param toId
	 *            - the id of the field to be moved to (above or below)
	 * @param moveAbove
	 *            - if true move above the field, if false move below it
	 * 
	 * @throws EQException
	 *             - if either field does not exist
	 */
	public void moveFieldToStart(String moveId) throws EQException
	{
		if (!fieldKeys().contains(moveId))
		{
			throw new EQException(LanguageResources.getFormattedString(
							"FieldSet.FieldDoesNotExist", new String[] { moveId, getId() })); //$NON-NLS-1$
		}
		else
		{
			int position = fieldKeys().indexOf(moveId);
			moveFieldAbove(getInputFields().get(position), position, 0);
		}
	}

	public void moveFieldFromOtherFieldSet(InputFieldSet fromFieldSet, String moveId, String toId, boolean moveAbove)
					throws EQException
	{
		if (!fromFieldSet.fieldKeys().contains(moveId))
		{
			throw new EQException(LanguageResources.getFormattedString(
							"FieldSet.FieldDoesNotExist", new String[] { moveId, fromFieldSet.getId() })); //$NON-NLS-1$
		}
		else if (!toId.equals("") && !fieldKeys().contains(toId))
		{
			throw new EQException(LanguageResources
							.getFormattedString("FieldSet.FieldDoesNotExist", new String[] { toId, getId() })); //$NON-NLS-1$
		}
		else
		{
			InputField field = fromFieldSet.getInputField(moveId);
			// if this input field set is currently empty
			if (toId.equals(""))
			{
				fieldKeys().add(field.getId());
				getInputFields().add(field);
			}
			else
			{
				// Add field into this field set
				int moveToPosition = fieldKeys().indexOf(toId);
				if (moveAbove)
				{
					// if the position above contains another group then move up above the whole group
					String groupAbove = getInputFields().get(moveToPosition).getRepeatingGroup();
					if (!groupAbove.equals("") && !groupAbove.equals(field.getRepeatingGroup()))
					{
						while (moveToPosition > 0
										&& groupAbove.equals(getInputFields().get(moveToPosition - 1).getRepeatingGroup()))
						{
							moveToPosition--;
						}
					}
				}
				else
				{
					int lastIndex = fieldKeys().size() - 1;
					// if the position below contains another group then move down below the whole group
					String groupBelow = getInputFields().get(moveToPosition).getRepeatingGroup();
					if (!groupBelow.equals("") && !groupBelow.equals(field.getRepeatingGroup()))
					{
						while (moveToPosition < lastIndex
										&& groupBelow.equals(getInputFields().get(moveToPosition + 1).getRepeatingGroup()))
						{
							moveToPosition++;
						}
					}
				}
				fieldKeys().add(moveToPosition, field.getId());
				getInputFields().add(moveToPosition, field);
			}

			// Collect all the existing field mappings
			String oldPath = field.rtvPath();
			Function function = getFunction();
			List<String> updateMappingsFrom = function.getUpdateMappingsFrom(field);
			List<String> updateMappingsTo = function.getUpdateMappingsTo(field);
			List<String> validateMappingsFrom = function.getValidateMappingsFrom(oldPath);
			List<String> validateMappingsTo = function.getValidateMappingsTo(oldPath);
			List<String> loadMappingsFrom = function.getLoadMappingsFrom(oldPath);
			List<String> loadMappingsTo = function.getLoadMappingsTo(oldPath);

			// Remove the old field mappings
			function.removeUpdateMappings(field);
			function.removeValidateMappings(field);
			function.removeLoadMappings(field);

			// Get the new path
			field.setParentFieldSet(this);
			String newPath = field.rtvPath();
			field.setParentFieldSet(fromFieldSet);

			// Add the new field mappings
			for (String mapping : updateMappingsFrom)
			{
				function.addUpdateMapping(mapping, newPath);
			}
			for (String mapping : updateMappingsTo)
			{
				function.addUpdateMapping(newPath, mapping);
			}
			for (String mapping : validateMappingsFrom)
			{
				function.addValidateMapping(mapping, newPath);
			}
			for (String mapping : validateMappingsTo)
			{
				function.addValidateMapping(newPath, mapping);
			}
			for (String mapping : loadMappingsFrom)
			{
				function.addLoadMapping(mapping, newPath);
			}
			for (String mapping : loadMappingsTo)
			{
				function.addLoadMapping(newPath, mapping);
			}

			// Update all the PV mappings
			List<PVFieldSet> pvFieldSets = field.getPvFieldSets();
			for (PVFieldSet pvFieldSet : pvFieldSets)
			{
				List<PVField> pvFields = pvFieldSet.getPVFields();
				for (PVField pvField : pvFields)
				{
					updateMappingsFrom = function.getUpdateMappingsFrom(pvField);
					updateMappingsTo = function.getUpdateMappingsTo(pvField);
					validateMappingsFrom = function.getValidateMappingsFrom(pvField);
					validateMappingsTo = function.getValidateMappingsTo(pvField);
					loadMappingsFrom = function.getLoadMappingsFrom(pvField);
					loadMappingsTo = function.getLoadMappingsTo(pvField);

					String newPvPath = pvField.rtvPath().replaceFirst(oldPath, newPath);

					function.removeUpdateMappings(pvField);
					function.removeValidateMappings(pvField);
					function.removeLoadMappings(pvField);

					for (String mapping : function.getUpdateMappingsFrom(pvField))
					{
						function.addUpdateMapping(mapping.replaceFirst(oldPath, newPath), newPvPath);
					}
					for (String mapping : function.getUpdateMappingsTo(pvField))
					{
						function.addUpdateMapping(newPvPath, mapping.replaceFirst(oldPath, newPath));
					}
					for (String mapping : function.getValidateMappingsFrom(pvField))
					{
						function.addValidateMapping(mapping.replaceFirst(oldPath, newPath), newPvPath);
					}
					for (String mapping : function.getValidateMappingsTo(pvField))
					{
						function.addValidateMapping(newPvPath, mapping.replaceFirst(oldPath, newPath));
					}
					for (String mapping : function.getLoadMappingsFrom(pvField))
					{
						function.addLoadMapping(mapping.replaceFirst(oldPath, newPath), newPvPath);
					}
					for (String mapping : function.getLoadMappingsTo(pvField))
					{
						function.addLoadMapping(newPvPath, mapping.replaceFirst(oldPath, newPath));
					}
				}
			}

			// Remove the field from the old input field set
			fromFieldSet.removeField(field);

			// change the input field set for this field
			field.setParentFieldSet(this);
		}
	}

	/**
	 * Move an InputGroup up one place
	 * 
	 * @param group
	 *            - the repeating group of fields to be moved
	 * 
	 * @throws EQException
	 *             - if the group does not exist
	 */
	public void moveInputGroupUp(InputGroup group) throws EQException
	{
		if (!inputGroups.contains(group))
		{
			throw new EQException(LanguageResources.getFormattedString("InputFieldSet.InputGroupDoesNotExist", group.getId())); //$NON-NLS-1$
		}
		else
		{
			int position = inputGroups.indexOf(group);
			if (position > 0)
			{
				inputGroups.remove(position);
				inputGroups.add(position - 1, group);
			}
		}
	}
	/**
	 * Move an InputGroup down one place
	 * 
	 * @param group
	 *            - the repeating group of fields to be moved
	 * 
	 * @throws EQException
	 *             - if the group does not exist
	 */
	public void moveInputGroupDown(InputGroup group) throws EQException
	{
		if (!inputGroups.contains(group))
		{
			throw new EQException(LanguageResources.getFormattedString("InputFieldSet.InputGroupDoesNotExist", group.getId())); //$NON-NLS-1$
		}
		else
		{
			int position = inputGroups.indexOf(group);
			if (position < inputGroups.size() - 1)
			{
				inputGroups.remove(position);
				inputGroups.add(position + 1, group);
			}
		}
	}
	/**
	 * Re-order InputGroups
	 * 
	 * Sort the inputGroups into the correct order based on the order of InputFields
	 */
	public void reorderInputGroups()
	{
		// List<InputGroup> newInputGroups = new ArrayList<InputGroup>();
		List<String> groupIds = rtvInputGroupIds();
		String currentGroupId = "";
		int position = 0;
		for (InputField inputField : getInputFields())
		{
			if (!inputField.getRepeatingGroup().equals(currentGroupId) && groupIds.contains(inputField.getRepeatingGroup()))
			{
				currentGroupId = inputField.getRepeatingGroup();
				InputGroup inputGroup = getInputGroup(currentGroupId);
				int oldPosition = inputGroups.indexOf(inputGroup);
				if (oldPosition > position)
				{
					inputGroups.remove(oldPosition);
					inputGroups.add(position, inputGroup);
				}
				position++;
			}
		}
	}

	/**
	 * Validate the bean
	 */
	@Override
	public boolean validateBean(MessageContainer messageContainer, String subSet, boolean includeChildren)
	{
		// First call superclass validation:
		super.validateBean(messageContainer, subSet, includeChildren);

		// Now perform this class's specific validation:
		for (APIFieldSet apiFieldSet : loadAPIs)
		{
			apiFieldSet.validateBean(messageContainer, subSet, includeChildren);
		}
		return false;
	}

	/**
	 * Return number of xsd web service type name references
	 * <p>
	 * This method can be used to ensure that a field's xsd web service type name reference is unique across the whole service
	 * definition. All InputFieldSets are checked to find a match.
	 * 
	 * @param webServiceFieldTypeName
	 *            a <code>String</code> containing the xsd web service type name of the field to check
	 * @return a <code>integer</code> indicating number of xsd web service type name references
	 */
	public int rtvNumberXsdWebServiceNameReferences(String webServiceFieldTypeName)
	{
		int numberXsdWebServiceNameReferences = 0;
		int xsdGeneration = getFunction().getXsdGeneration();

		for (InputField field : getInputFields())
		{
			String webServiceName = field.rtvWebServiceName(xsdGeneration);
			if (webServiceName != null && webServiceName.equalsIgnoreCase(webServiceFieldTypeName))
			{
				numberXsdWebServiceNameReferences = numberXsdWebServiceNameReferences + 1;
			}
		}
		return numberXsdWebServiceNameReferences;
	}
	/**
	 * Rename currency linked field
	 * 
	 * @param fieldName
	 *            - the field name
	 * @param newFieldName
	 *            - the new field name
	 */
	public void renameCurrencyLinkedField(String fieldName, String newFieldName)
	{
		for (InputField field : getInputFields())
		{
			if (field.getCcyLinkedField() != null && field.getCcyLinkedField().trim().length() > 0
							&& fieldName.equals(field.getCcyLinkedField()))
			{
				field.setCcyLinkedField(newFieldName);
			}
		}

	}
}
