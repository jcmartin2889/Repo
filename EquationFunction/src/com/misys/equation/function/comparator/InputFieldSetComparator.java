package com.misys.equation.function.comparator;

import com.misys.equation.function.beans.FieldSet;
import com.misys.equation.function.beans.InputFieldSet;

public class InputFieldSetComparator extends FieldSetComparator
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: InputFieldSetComparator.java 10091 2010-12-03 17:12:30Z MACDONP1 $";

	/**
	 * Construct an Input Field Set Comparator
	 */
	public InputFieldSetComparator()
	{
		super();
	}

	/**
	 * Return the list of properties that differs between 2 input fieldsets
	 * 
	 * @param inputFieldSet1
	 *            - first input fieldset
	 * @param inputFieldSet2
	 *            - second input fieldset
	 * @param tag
	 *            - the type of element being compared
	 * 
	 * @return the list of properties that differs between the 2 input fieldsets
	 */
	public ElementDifference compare(InputFieldSet inputFieldSet1, InputFieldSet inputFieldSet2, String tag)
	{
		// compare attributes of the field set
		ElementDifference property = compare((FieldSet) inputFieldSet1, (FieldSet) inputFieldSet2, tag);

		// compare the load APIs
		compareLoadAPIs(property, inputFieldSet1, inputFieldSet2);

		return property;
	}

	/**
	 * Compare the fields in the field set
	 * 
	 * @param property
	 *            - to contain the differences
	 * @param inputFieldSet1
	 *            - first input fieldset
	 * @param inputFieldSet2
	 *            - second input fieldset
	 */
	private void compareLoadAPIs(ElementDifference property, InputFieldSet inputFieldSet1, InputFieldSet inputFieldSet2)
	{
		ElementComparatorToolbox.compareListOfSubElements(property, inputFieldSet1.loadAPIKeys(), inputFieldSet2.loadAPIKeys(),
						inputFieldSet1.getLoadAPIs(), inputFieldSet2.getLoadAPIs(), ElementComparatorToolbox.TYPE_LOADAPI,
						copyDirection);
	}
}
