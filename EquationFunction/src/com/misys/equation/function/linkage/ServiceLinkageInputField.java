package com.misys.equation.function.linkage;

import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.PVFieldSet;
import com.misys.equation.function.beans.ValidationExpression;
import com.misys.equation.function.beans.WorkField;

public class ServiceLinkageInputField extends ServiceLinkageWorkField
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ServiceLinkageInputField.java 14806 2012-11-05 11:58:11Z williae1 $";
	// the target input field
	protected InputField targetInputField;

	/**
	 * Constructor
	 * 
	 * @param targetInputField
	 *            - the target input field
	 */
	public ServiceLinkageInputField(InputField targetInputField)
	{
		super(targetInputField);
		this.targetInputField = targetInputField;
	}

	/**
	 * Link the source input field into the target input field
	 * 
	 * @param sourceInputField
	 *            - the source input field
	 * @param locationId
	 *            - the parent id of field 2
	 * 
	 * @return the target input field
	 */
	public InputField link(InputField sourceInputField, String locationId)
	{
		// clear messages
		messageContainer.clear();

		// validate it
		validateDuplicateField(targetInputField, sourceInputField, locationId);

		// must be same group
		validateRepeatinGroups(sourceInputField, locationId);

		// sync it
		sync(sourceInputField, locationId);

		return targetInputField;
	}

	/**
	 * Validate whether fields belong in the same repeating group
	 * 
	 * @param sourceInputField
	 *            - the source input field
	 * @param locationId
	 *            - the parent id of field 2
	 */
	protected void validateRepeatinGroups(InputField sourceInputField, String locationId)
	{
		// Fields must be in the same group
		if (!targetInputField.getRepeatingGroup().trim().equals(sourceInputField.getRepeatingGroup().trim()))
		{
			addMessage("Language.LinkageFieldsInDifferentRepeatingGroup", sourceInputField.getId(), locationId, "", "", "");
		}
	}

	/**
	 * Copy relevant information from the source to the target
	 * 
	 * @param targetInputField
	 *            - the target input field
	 * @param sourceInputField
	 *            - the source input field
	 */
	protected void sync(InputField sourceInputField, String locationId)
	{
		// syncUpperCase(sourceInputField, locationId);
		// syncInitialValue(sourceInputField, locationId);
		// syncFieldInitialiasstionExpression(sourceInputField, locationId);
		// syncDefaultValue(sourceInputField, locationId);
		// syncMinLength(sourceInputField, locationId);
		// syncMaxLength(sourceInputField, locationId);
		// syncValidValues(sourceInputField, locationId);
		// syncRegularExpression(sourceInputField, locationId);
		// syncMandatory(sourceInputField, locationId);
		syncApplicationValidation(sourceInputField, locationId);
		syncValidateModules(sourceInputField, locationId);
	}

	/**
	 * Sync upper case
	 * 
	 * @param sourceInputField
	 *            - the source input field
	 * @param locationId
	 *            - the parent id of field 2
	 */
	protected void syncUpperCase(InputField sourceInputField, String locationId)
	{
		if (!targetInputField.isUpperCase())
		{
			targetInputField.setUpperCase(sourceInputField.isUpperCase());
		}
	}

	/**
	 * Sync initial value information
	 * 
	 * @param sourceInputField
	 *            - the source input field
	 * @param locationId
	 *            - the parent id of field 2
	 */
	protected void syncInitialValue(InputField sourceInputField, String locationId)
	{
		// not specified and not Java
		if (targetInputField.getInitialValue().length() == 0
						&& !targetInputField.getInitialValueConstantType().equals(WorkField.VALUE_CONSTANT_CODE))
		{
			targetInputField.setInitialValue(sourceInputField.getInitialValue());
			targetInputField.setInitialValueAs(sourceInputField.getInitialValueAs());
			targetInputField.setInitialValueConstantType(sourceInputField.getInitialValueConstantType());
			targetInputField.setInitialValueType(sourceInputField.getInitialValueType());
		}
	}

	/**
	 * Sync field initialisation expression
	 * 
	 * @param sourceInputField
	 *            - the source input field
	 * @param locationId
	 *            - the parent id of field 2
	 */
	protected void syncFieldInitialiasstionExpression(InputField sourceInputField, String locationId)
	{
		if (targetInputField.getFieldInitialisationScript().length() == 0)
		{
			targetInputField.setFieldInitialisationScript(sourceInputField.getFieldInitialisationScript());
		}
	}

	/**
	 * Sync default value information
	 * 
	 * @param sourceInputField
	 *            - the source input field
	 * @param locationId
	 *            - the parent id of field 2
	 */
	protected void syncDefaultValue(InputField sourceInputField, String locationId)
	{
		// not specified and not Java
		if (targetInputField.getDefaultValue().length() == 0
						&& targetInputField.getDefaultValueType().equals(WorkField.VALUE_CONSTANT_CODE))
		{
			targetInputField.setDefaultValue(sourceInputField.getDefaultValue());
			targetInputField.setDefaultValueAs(sourceInputField.getDefaultValueAs());
			targetInputField.setDefaultValueType(sourceInputField.getDefaultValueType());
		}
	}

	/**
	 * Sync minimum length
	 * 
	 * @param sourceInputField
	 *            - the source input field
	 * @param locationId
	 *            - the parent id of field 2
	 */
	protected void syncMinLength(InputField sourceInputField, String locationId)
	{
		int fieldLength = Toolbox.parseInt(targetInputField.getSize(), 0);

		// minimum length specified?
		int targetMinimumLength = Toolbox.parseInt(targetInputField.getMinLength(), -1);
		int sourceMinimumLength = Toolbox.parseInt(sourceInputField.getMinLength(), -1);

		// overwrite only if
		// .. minimum length has not been specified
		// .. new minimum length is greater than the current minimum length
		if (targetMinimumLength == -1 || (sourceMinimumLength > targetMinimumLength && sourceMinimumLength < fieldLength))
		{
			targetInputField.setMinLength(String.valueOf(sourceMinimumLength));
		}
	}

	/**
	 * Sync maximum length
	 * 
	 * @param sourceInputField
	 *            - the source input field
	 * @param locationId
	 *            - the parent id of field 2
	 */
	protected void syncMaxLength(InputField sourceInputField, String locationId)
	{
		int fieldLength = Toolbox.parseInt(targetInputField.getSize(), -1);

		// minimum length specified?
		int targetMaximumLength = Toolbox.parseInt(targetInputField.getMaxLength(), -1);
		int sourceMaximumLength = Toolbox.parseInt(sourceInputField.getMaxLength(), -1);

		// overwrite only if
		// .. maximum length has not been specified
		// .. new maximum length is less than the current minimum length
		if (targetMaximumLength == -1 || (sourceMaximumLength < targetMaximumLength && sourceMaximumLength < fieldLength))
		{
			targetInputField.setMaxLength(String.valueOf(sourceMaximumLength));
		}
	}

	/**
	 * Sync valid values
	 * 
	 * @param sourceInputField
	 *            - the source input field
	 * @param locationId
	 *            - the parent id of field 2
	 */
	protected void syncValidValues(InputField sourceInputField, String locationId)
	{
		// not implemented due to issue with Reference (as it is referring to the original function)
	}

	/**
	 * Sync regular expression
	 * 
	 * @param sourceInputField
	 *            - the source input field
	 * @param locationId
	 *            - the parent id of field 2
	 */
	protected void syncRegularExpression(InputField sourceInputField, String locationId)
	{
		// not implemented due to issue with Reference (as it is referring to the original function)
	}

	/**
	 * Sync mandatory
	 * 
	 * @param sourceInputField
	 *            - the source input field
	 * @param locationId
	 *            - the parent id of field 2
	 */
	protected void syncMandatory(InputField sourceInputField, String locationId)
	{
		if (targetInputField.getMandatory().equals(InputField.MANDATORY_NO))
		{
			targetInputField.setMandatory(sourceInputField.getMandatory());
			targetInputField.setMandatoryExpression(sourceInputField.getMandatoryExpression());
		}
	}

	/**
	 * Sync application validation
	 * 
	 * @param sourceInputField
	 *            - the source input field
	 * @param locationId
	 *            - the parent id of field 2
	 */
	protected void syncApplicationValidation(InputField sourceInputField, String locationId)
	{
		for (ValidationExpression validationExpression : sourceInputField.getValidationExpressions())
		{
			targetInputField.addValidationExpression(validationExpression);
		}
	}

	/**
	 * Sync validate modules
	 * 
	 * @param sourceInputField
	 *            - the source input field
	 * @param locationId
	 *            - the parent id of field 2
	 */
	protected void syncValidateModules(InputField sourceInputField, String locationId)
	{
		for (PVFieldSet pvFieldSet : sourceInputField.getPvFieldSets())
		{
			try
			{
				targetInputField.addPvFieldSet(pvFieldSet);
			}
			catch (Exception e)
			{
				// addMessage("Language.LinkagePVDuplicateError", sourceInputField.getId(), pvFieldSet.getId(), locationId, "", "");
			}
		}
	}

}
