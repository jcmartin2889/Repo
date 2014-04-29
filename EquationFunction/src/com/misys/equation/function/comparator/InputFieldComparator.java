package com.misys.equation.function.comparator;

import com.misys.equation.function.beans.Element;
import com.misys.equation.function.beans.InputField;

public class InputFieldComparator extends ElementComparator
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: InputFieldComparator.java 9784 2010-11-10 14:25:42Z MACDONP1 $";

	/**
	 * Construct an Input Field Comparator
	 * 
	 */
	public InputFieldComparator()
	{
		super();
	}

	/**
	 * Return the list of properties that differs between 2 input fields
	 * 
	 * @param inputField1
	 *            - first input field
	 * @param inputField2
	 *            - second input field
	 * @param tag
	 *            - the type of element being compared
	 * 
	 * @return the list of properties that differs between the 2 input fields
	 */
	public ElementDifference compare(InputField inputField1, InputField inputField2, String tag)
	{
		// compare attributes of the input field
		ElementDifference property = compare((Element) inputField1, (Element) inputField2, tag);

		// compare PV field sets
		comparePVFieldSets(property, inputField1, inputField2);

		// return difference
		return property;
	}

	/**
	 * Compare the PV fieldsets between 2 input fields
	 * 
	 * @param property
	 *            - to contain the differences
	 * @param inputField1
	 *            - first input field
	 * @param inputField2
	 *            - second input field
	 */
	private void comparePVFieldSets(ElementDifference property, InputField inputField1, InputField inputField2)
	{
		ElementComparatorToolbox.compareListOfSubElements(property, inputField1.pvFieldSetKeys(), inputField2.pvFieldSetKeys(),
						inputField1.getPvFieldSets(), inputField2.getPvFieldSets(), ElementComparatorToolbox.TYPE_PVFIELDSET,
						copyDirection);
	}
}
