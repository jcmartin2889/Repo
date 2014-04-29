package com.misys.equation.function.test;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.KeyedArrayList;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.test.helper.DisplayFieldWrapper;

/**
 * This class manages a InputFieldSet and DisplayAttributesSet pair.
 * <p>
 * This simplifies access to the set of fields when dealing with both the business and UI layers at the same time. This situation is
 * currently often found in Test Cases.
 * 
 * @see DisplayFieldWrapper
 */
public class DisplayFieldSetWrapper
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DisplayFieldSetWrapper.java 4741 2009-09-16 16:33:23Z esther.williams $";
	/**
	 * Logger for this class
	 */
	private static final EquationLogger LOG = new EquationLogger(DisplayFieldSetWrapper.class);
	private final InputFieldSet inputFieldSet;
	private final DisplayAttributesSet displayAttributesSet;
	private final KeyedArrayList<DisplayFieldWrapper> fieldWrappers = new KeyedArrayList<DisplayFieldWrapper>();

	public DisplayFieldSetWrapper()
	{
		inputFieldSet = new InputFieldSet();
		displayAttributesSet = new DisplayAttributesSet();
	}

	public InputFieldSet getInputFieldSet()
	{
		return inputFieldSet;
	}

	public DisplayAttributesSet getDisplayAttributesSet()
	{
		return displayAttributesSet;
	}

	public DisplayFieldWrapper addDisplayField(String id)
	{
		DisplayFieldWrapper result = new DisplayFieldWrapper();
		result.setId(id);
		addDisplayFieldWrapper(result);

		return result;
	}

	public void addDisplayFieldWrapper(DisplayFieldWrapper displayFieldWrapper)
	{
		fieldWrappers.add(displayFieldWrapper, displayFieldWrapper.getInputField().getId());
		// Also need to add to the real sets
		try
		{
			inputFieldSet.addInputField(displayFieldWrapper.getInputField());
			displayAttributesSet.addItem(displayFieldWrapper.getDisplayAttributes());
		}
		catch (EQException e)
		{
			LOG.error(e);
		}
	}

	public void setId(String id)
	{
		inputFieldSet.setId(id);
		displayAttributesSet.setId(id);
	}

	public void setLabel(String label)
	{
		inputFieldSet.setLabel(label);
		displayAttributesSet.setLabel(label);
	}

	public void setDescription(String description)
	{
		inputFieldSet.setDescription(description);
		displayAttributesSet.setDescription(description);
	}
}
