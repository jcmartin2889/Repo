package com.misys.equation.function.linkage;

import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.WorkField;

public class ServiceLinkageWorkField extends AbstractServiceLinkage
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ServiceLinkageWorkField.java 14806 2012-11-05 11:58:11Z williae1 $";
	// the target work field
	protected WorkField targetTargetField;

	/**
	 * Constructor
	 * 
	 * @param targetTargetField
	 *            - the target work field
	 */
	public ServiceLinkageWorkField(WorkField targetTargetField)
	{
		this.targetTargetField = targetTargetField;
	}

	/**
	 * Validate whether the 2 fields can be combined
	 * 
	 * @param field1
	 *            - field 1
	 * @param field2
	 *            - field 2
	 * @param locationId
	 *            - the parent id of field 2
	 */
	public void validateDuplicateField(Field field1, Field field2, String locationId)
	{
		// Same data type?
		if (field1.getDataType().equals(field2.getDataType()))
		{
			// not same size?
			if (!field1.getSize().equals(field2.getSize()))
			{
				addMessage("Language.LinkageFieldLengthWarning", field1.getId(), locationId, "", "", "");
			}

			// not same decimal places?
			if (!field1.getDecimals().equals(field2.getDecimals()))
			{
				addMessage("Language.LinkageFieldLengthWarning", field1.getId(), locationId, "", "", "");
			}

		}

		// Not same data type
		else
		{
			addMessage("Language.LinkageFieldNotSameTypeError", field1.getId(), locationId, "", "", "");
		}
	}

	/**
	 * Link the source work field into the target work field
	 * 
	 * @param sourceWorkField
	 *            - the source work field
	 * @param locationId
	 *            - the parent id of the source work field
	 * 
	 * @return the target work field
	 */
	public WorkField link(WorkField sourceWorkField, String locationId)
	{
		// clear messages
		messageContainer.clear();

		// validate it
		validateDuplicateField(targetTargetField, sourceWorkField, locationId);

		return targetTargetField;
	}

}
