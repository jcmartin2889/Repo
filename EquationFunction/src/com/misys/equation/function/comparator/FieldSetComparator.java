package com.misys.equation.function.comparator;

import com.misys.equation.function.beans.Element;
import com.misys.equation.function.beans.FieldSet;

public class FieldSetComparator extends ElementComparator
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FieldSetComparator.java 10091 2010-12-03 17:12:30Z MACDONP1 $";

	/**
	 * Construct an Field Set Comparator
	 * 
	 */
	public FieldSetComparator()
	{
		super();
	}

	/**
	 * Return the list of properties that differs between 2 fieldsets
	 * 
	 * @param fieldSet1
	 *            - first fieldset
	 * @param fieldSet2
	 *            - second fieldset
	 * @param tag
	 *            - the type of element being compared
	 * 
	 * @return the list of properties that differs between the 2 fieldsets
	 */
	public ElementDifference compare(FieldSet fieldSet1, FieldSet fieldSet2, String tag)
	{
		// compare attributes of the field set
		ElementDifference property = compare((Element) fieldSet1, (Element) fieldSet2, tag);

		// compare the fields in the field set
		compareSubFields(property, fieldSet1, fieldSet2);

		return property;
	}

	/**
	 * Compare the fields in the field set
	 * 
	 * @param property
	 *            - to contain the differences
	 * @param fieldSet1
	 *            - first fieldset
	 * @param fieldSet2
	 *            - second fieldset
	 */
	private void compareSubFields(ElementDifference property, FieldSet fieldSet1, FieldSet fieldSet2)
	{
		ElementComparatorToolbox.compareListOfSubElements(property, fieldSet1.fieldKeys(), fieldSet2.fieldKeys(),
						fieldSet1.rtvFields(), fieldSet2.rtvFields(), ElementComparatorToolbox.TYPE_SUBFIELD, copyDirection);
	}

}
