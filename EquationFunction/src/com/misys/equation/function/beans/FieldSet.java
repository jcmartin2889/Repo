package com.misys.equation.function.beans;

import java.util.ArrayList;
import java.util.List;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQRuntimeException;
import com.misys.equation.function.beans.valid.IValidation;
import com.misys.equation.function.beans.valid.MessageContainer;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.tools.XSDStructureHelper;
import com.misys.equation.problems.ProblemLocation;

/**
 * This class represent a field set.
 * <p>
 * 
 * A field set must have a unique ID within its group. A field set contains a list of fields
 * 
 */
public abstract class FieldSet extends Element implements IValidation
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FieldSet.java 17190 2013-09-03 11:49:59Z Lima12 $";

	/** Specifies that validation of the execute script be performed */
	public static final String SCRIPT_VALIDATION = "SCRIPT_VALIDATION";

	private final List<Field> fields = new ArrayList<Field>();
	private final List<String> fieldKeys = new ArrayList<String>();
	private Function function;

	/** Indicates that the API or PV module represented by this class should always be executed */
	public static final int EXECUTE_ALWAYS = 0;
	/** Indicates that the API or PV module represented by this class should never be executed. */
	public static final int EXECUTE_NEVER = 1;
	/**
	 * Indicates that the API or PV module represented by this class should be executed depending on the return value of the
	 * <code>executionScript</code> Script
	 */
	public static final int EXECUTE_SCRIPT = 2;
	/**
	 * Indicates that the API or PV module represented by this class should be executed depending on the return value of the a Java
	 * user exit method
	 */
	public static final int EXECUTE_CODE = 3;

	/**
	 * For FieldSets representing an API or PV module, this field controls whether the module is actually executed
	 * <p>
	 * Possible values are:
	 * 
	 * <ul>
	 * <li>0 - Always execute</li>
	 * <li>1 - Never execute</li>
	 * <li>2 - Run an EL Script to determine whether to execute the module</li>
	 * <li>3 - Call a Java user exit method to determine whether to execute the module</li>
	 * </ul>
	 */
	private int executeMode;
	/** An EL Script that indicates whether the API or PV module represented by this FieldSet should be executed */
	private String executeScript;

	protected FieldSet()
	{
		super();
		commonInitialisation();
	}

	protected FieldSet(String id, String label, String description)
	{
		super(id, label, description);
		commonInitialisation();
	}

	protected FieldSet(Element element)
	{
		super(element);
		commonInitialisation();
	}

	/**
	 * Common initisalisation
	 */
	private void commonInitialisation()
	{
		executeMode = 0;
		executeScript = "";
	}

	/**
	 * Copy constructor
	 * <p>
	 * Note that this does NOT copy the fields; superclasses must perform the specific field construction
	 * 
	 * @param fieldSet
	 */
	protected FieldSet(FieldSet fieldSet)
	{
		super(fieldSet);
		setParent(fieldSet.rtvParent());
		function = fieldSet.function;
		executeMode = fieldSet.executeMode;
		executeScript = fieldSet.executeScript;
	}
	/**
	 * Set the function of the field set. TODO: do we need a function?
	 * 
	 * @param function
	 */
	public void setFunction(Function function)
	{
		this.function = function;
	}

	/**
	 * Return the function associated with the field set
	 * 
	 * @return Function
	 */
	protected Function getFunction()
	{
		return function;
	}

	/**
	 * Return the field keys of the field set
	 * 
	 * @return the field keys of the field set
	 */
	public List<String> fieldKeys()
	{
		return fieldKeys;
	}

	/**
	 * Determines if a field Id already exists in the field set
	 * 
	 * @param fieldKey
	 *            - field id
	 * 
	 * @return true - if the field with the same field id exists in the field set
	 */
	public boolean containsField(String fieldKey)
	{
		return fieldKeys.contains(fieldKey);
	}

	/**
	 * Return the fields of the field set
	 * 
	 * @return the fields of the field set
	 */
	protected List getFields()
	{
		return fields;
	}

	/**
	 * Return the fields of the field set
	 * 
	 * @return the fields of the field set
	 */
	public List rtvFields()
	{
		return fields;
	}

	/**
	 * Add a field into the field set
	 * 
	 * @param field
	 *            - the field to be added
	 * @throws EQException
	 * 
	 */
	protected void addField(Field field) throws EQException
	{
		String key = field.getId();
		if (fieldKeys.contains(key))
		{
			throw new EQException(LanguageResources.getString("FieldSet.FieldExists")); //$NON-NLS-1$
		}
		else
		{
			fields.add(field);
			fieldKeys.add(key);
			field.setParentFieldSet(this);
			// Set up HTML for this field
		}
	}

	/**
	 * Add a field into the field set at the specified index
	 * 
	 * @param index
	 *            The zero-based index to add the item at
	 * @param field
	 *            - the field to be added
	 */
	protected void addField(int index, Field field)
	{
		String key = field.getId();
		if (fieldKeys.contains(key))
		{
			throw new EQRuntimeException(LanguageResources.getString("FieldSet.FieldExists")); //$NON-NLS-1$
		}
		else
		{
			fields.add(index, field);
			fieldKeys.add(index, key);
			// Set this as the parent:
			field.setParentFieldSet(this);
		}
	}

	/**
	 * Swap a field with the one in the specified position
	 * 
	 * @param to
	 *            the zero-based index to move the field to
	 * @param id
	 *            a <code>String</code> containing the field id
	 * @throws EQException
	 *             if the field does not exist on the <code>APIFieldSet</code>
	 */
	protected void swapField(int to, String id) throws EQException
	{
		if (!fieldKeys.contains(id))
		{
			throw new EQException(LanguageResources.getFormattedString("FieldSet.FieldDoesNotExist", new String[] { id, getId() })); //$NON-NLS-1$
		}
		else
		{
			int from = fieldKeys.indexOf(id);
			if (from != to)
			{
				Field tempField = fields.get(to);
				String tempKey = fieldKeys.get(to);
				fields.set(to, getField(id));
				fieldKeys.set(to, id);
				fields.set(from, tempField);
				fieldKeys.set(from, tempKey);
			}
		}
	}

	/**
	 * Modifies the Id of a field.
	 * <p>
	 * Changes the fields id and updates the mapping paths.
	 * 
	 * @param oldKey
	 *            a <code>String</code> containing the current Id of a field
	 * @param newKey
	 *            a <code>String</code> containing the new Id of that field
	 * 
	 * @throws EQException
	 *             - if the field does not exist
	 */
	public void modifyFieldId(String oldKey, String newKey) throws EQException
	{
		if (!fieldKeys.contains(oldKey))
		{
			throw new EQException(LanguageResources.getFormattedString(
							"FieldSet.FieldDoesNotExist", new String[] { oldKey, getId() })); //$NON-NLS-1$
		}
		else
		{
			int keyIndex = fieldKeys.indexOf(oldKey);

			// Rename mapping
			function.modifyFieldId(getId(), newKey, oldKey);

			fieldKeys.set(keyIndex, newKey);
			(fields.get(keyIndex)).setId(newKey);
		}
	}

	/**
	 * Modifies the Id of a field.
	 * <p>
	 * Changes the fields id and updates the mapping paths.
	 * 
	 * @param apiField
	 *            the API field to be modified
	 * @param newKey
	 *            a <code>String</code> containing the new Id of that field
	 * 
	 * @throws EQException
	 *             - if the field does not exist
	 */
	public void modifyAPIFieldId(APIField apiField, String newKey) throws EQException
	{
		String oldKey = apiField.getId();
		if (!fieldKeys.contains(oldKey))
		{
			throw new EQException(LanguageResources.getFormattedString(
							"FieldSet.FieldDoesNotExist", new String[] { oldKey, getId() })); //$NON-NLS-1$
		}
		else
		{
			int keyIndex = fieldKeys.indexOf(oldKey);

			// Old mapping path
			String oldPath = apiField.rtvPath();
			if (apiField.rtvParentFieldSet().rtvParent() == null)
			{
				oldPath = getFunction().rtvPrimaryInputFieldSet().rtvPath() + "." + oldPath;
			}

			fieldKeys.set(keyIndex, newKey);
			(fields.get(keyIndex)).setId(newKey);

			// New mapping path
			String newPath = apiField.rtvPath();
			if (apiField.rtvParentFieldSet().rtvParent() == null)
			{
				newPath = getFunction().rtvPrimaryInputFieldSet().rtvPath() + "." + newPath;
			}

			function.modifyFieldId(newPath, oldPath);
		}
	}

	/**
	 * Return the field identified by the id
	 * 
	 * @param key
	 *            - the field id
	 * 
	 * @return - the field identified by the id
	 * 
	 * @throws EQException
	 *             - if the field does not exist
	 */
	protected Field getField(String key) throws EQException
	{
		if (!fieldKeys.contains(key))
		{
			throw new EQException(LanguageResources.getFormattedString("FieldSet.FieldDoesNotExist", new String[] { key, getId() })); //$NON-NLS-1$
		}
		else
		{
			int keyIndex = fieldKeys.indexOf(key);
			return fields.get(keyIndex);
		}
	}

	/**
	 * Return true if the field identified by the id is in the field set
	 * 
	 * @param key
	 *            - the field id
	 * 
	 * @return - true if the field identified by the id is in the field set
	 * 
	 * @throws EQException
	 *             - if the field does not exist
	 */
	public boolean hasField(String key)
	{
		if (!fieldKeys.contains(key))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	/**
	 * Return the field sequence in the field set
	 * 
	 * @param key
	 *            - the field id
	 * 
	 * @return - the field sequence in the field set
	 * 
	 * @throws EQException
	 *             - if the field does not exist
	 */
	public int rtvFieldSequence(String key) throws EQException
	{
		if (!fieldKeys.contains(key))
		{
			throw new EQException(LanguageResources.getFormattedString("FieldSet.FieldDoesNotExist", new String[] { key, getId() })); //$NON-NLS-1$
		}
		else
		{
			int keyIndex = fieldKeys.indexOf(key);
			return keyIndex;
		}
	}

	/**
	 * Return the field identified by the id
	 * 
	 * @param key
	 *            - the field id
	 * 
	 * @return - the field identified by the id
	 * 
	 * @throws EQException
	 *             - if the field does not exist
	 */
	public Field rtvField(String key) throws EQException
	{
		return getField(key);
	}

	/**
	 * Remove a given field given an ID
	 * 
	 * @param fieldId
	 *            - the field ID
	 * @throws EQException
	 *             if the fieldID does not exist
	 */
	public void removeField(String fieldId) throws EQException
	{
		removeField(getField(fieldId));
	}

	/**
	 * Remove the field from the field set
	 * 
	 * @param field
	 *            - the field to be removed
	 * 
	 * @throws EQException
	 *             - if the field does not exist
	 */
	public void removeField(Field field) throws EQException
	{
		String key = field.getId();
		if (!fieldKeys.contains(key))
		{
			throw new EQException(LanguageResources.getFormattedString("FieldSet.FieldDoesNotExist", new String[] { key, getId() })); //$NON-NLS-1$
		}
		else
		{
			// must remove the fields mappings first
			function.removeUpdateMappings(field);
			function.removeValidateMappings(field);
			function.removeLoadMappings(field);

			// also need to remove any PV mappings...
			if (field instanceof InputField)
			{
				List<PVFieldSet> pvFieldSets = ((InputField) field).getPvFieldSets();
				for (PVFieldSet pvFieldSet : pvFieldSets)
				{
					List<PVField> pvFields = pvFieldSet.getPVFields();
					for (PVField pvField : pvFields)
					{
						function.removeUpdateMappings(pvField);
						function.removeValidateMappings(pvField);
						function.removeLoadMappings(pvField);
					}
				}

				// remove from xsd mapping
				XSDStructureHelper.removeFieldFromXSD(function, field.getId());
			}
			// remove from the fieldSet
			int keyIndex = fieldKeys.indexOf(key);
			fields.remove(keyIndex);
			fieldKeys.remove(keyIndex);
		}
	}

	/**
	 * Move the field one position up
	 * 
	 * @param field
	 *            - the field to be moved
	 * 
	 * @throws EQException
	 *             - if the field does not exist
	 */
	public void moveFieldUp(Field field) throws EQException
	{
		String key = field.getId();
		if (!fieldKeys.contains(key))
		{
			throw new EQException(LanguageResources.getFormattedString("FieldSet.FieldDoesNotExist", new String[] { key, getId() })); //$NON-NLS-1$
		}
		else
		{
			int position = fieldKeys.indexOf(key);
			if (position > 0)
			{
				fieldKeys.remove(position);
				fieldKeys.add(position - 1, key);
				fields.remove(position);
				fields.add(position - 1, field);
			}
		}
	}

	/**
	 * Move the field one position down
	 * 
	 * @param field
	 *            - the field to be moved
	 * 
	 * @throws EQException
	 *             - if the field does not exist
	 */
	public void moveFieldDown(Field field) throws EQException
	{
		String key = field.getId();
		if (!fieldKeys.contains(key))
		{
			throw new EQException(LanguageResources.getFormattedString("FieldSet.FieldDoesNotExist", new String[] { key, getId() })); //$NON-NLS-1$
		}
		else
		{
			int position = fieldKeys.indexOf(key);
			if (position < fieldKeys.size() - 1)
			{
				fieldKeys.remove(position);
				fieldKeys.add(position + 1, key);
				fields.remove(position);
				fields.add(position + 1, field);
			}
		}
	}

	/**
	 * @return the field
	 */
	public Field rtvParentField()
	{
		return (Field) rtvParent();
	}

	/**
	 * @return the fieldSet
	 */
	public FieldSet rtvParentFieldSet()
	{
		return (FieldSet) rtvParent();
	}

	/**
	 * @param parentField
	 *            the field to set
	 */
	protected void setParentField(Field parentField)
	{
		setParent(parentField);
	}

	/**
	 * @param parentFieldSet
	 *            the parent fieldSet to set
	 */
	public void setParentFieldSet(FieldSet parentFieldSet)
	{
		setParent(parentFieldSet);
	}

	/**
	 * For FieldSets representing an API or PV module, this field controls whether the module is actually executed
	 * <p>
	 * Possible values are:
	 * 
	 * <ul>
	 * <li>0 - Always execute</li>
	 * <li>1 - Never execute</li>
	 * <li>2 - Run an EL Script to determine whether to execute the module</li>
	 * <li>3 - Call a Java user exit method to determine whether to execute the module</li>
	 * </ul>
	 */
	public int getExecuteMode()
	{
		return executeMode;
	}

	public void setExecuteMode(int executeMode)
	{
		this.executeMode = executeMode;
	}

	/**
	 * @return A <code>String</code> containing an EL Script that indicates whether the API or PV module represented by this
	 *         FieldSet should be executed
	 */
	public String getExecuteScript()
	{
		return executeScript;
	}

	public void setExecuteScript(String executeScript)
	{
		this.executeScript = executeScript;
	}

	/**
	 * Validate this bean
	 * 
	 * @param messageContainer
	 * @param subSet
	 * @param includeChildren
	 * @return true if the bean is valid, otherwise false
	 */
	public boolean validateBean(MessageContainer messageContainer, String subSet, boolean includeChildren)
	{
		ProblemLocation problemLocation = new ProblemLocation(this.getClass().getSimpleName(), getId());
		// Only check label if not a PV Fieldset
		if (!(this instanceof PVFieldSet) && (getLabel().length() == 0 || getLabel().trim().equals(DEFAULT_TEXT_VALUE)))
		{
			messageContainer.addErrorMessageId("Language.FieldSetTitleCannotBeBlank", getId(), problemLocation); //$NON-NLS-1$
		}

		if (subSet == null || subSet.equals(SCRIPT_VALIDATION))
		{
			if (executeMode == EXECUTE_SCRIPT)
			{
				ValidationHelper.validateBooleanELExpression(executeScript, this, messageContainer,
								ValidationHelper.BooleanELType.EXECUTE_EXPRESSION, problemLocation);
			}
		}

		if (includeChildren)
		{
			// Validate all child fields of this fieldset
			for (Field field : fields)
			{
				field.validateBean(messageContainer, subSet, includeChildren);
			}
		}
		return false;
	}
	/**
	 * Determine whether the field set contains at least one field which is a key
	 * 
	 * @return true if the field set contains at least one field which is a key
	 */
	public boolean containsKeyFields()
	{
		for (int i = 0; i < fields.size(); i++)
		{
			if (fields.get(i).isKey())
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Determine whether the field set contains at least one field which is a non-key
	 * 
	 * @return true if the field set contains at least one field which is a non-key
	 */
	public boolean containsNonKeyFields()
	{
		for (int i = 0; i < fields.size(); i++)
		{
			if (!fields.get(i).isKey())
			{
				return true;
			}
		}
		return false;
	}

}
