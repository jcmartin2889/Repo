package com.misys.equation.function.comparator;

import java.util.Arrays;

import com.misys.equation.function.beans.Element;
import com.misys.equation.function.beans.Function;

public class FunctionComparator extends ElementComparator
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionComparator.java 14801 2012-11-05 11:56:00Z williae1 $";

	// list of ignored methods - must be sorted
	private static final String[] IGNORE_METHODS = { "getSubfield", "isBaseLanguageValid", "isImportAllowedValid", "isSubfield" };

	/**
	 * Construct the list of differences between 2 elements
	 * 
	 * @param id
	 *            - the id
	 */
	public FunctionComparator()
	{
		super();
	}

	/**
	 * Determine whether to ignore the method or not
	 * 
	 * @param methodName
	 *            - the method name
	 * 
	 * @return true if method is ignored
	 */
	@Override
	protected boolean ignoreMethod(String methodName)
	{
		return Arrays.binarySearch(IGNORE_METHODS, methodName) >= 0;
	}
	/**
	 * Return the list of properties that differs between 2 functions
	 * 
	 * @param Function
	 *            - first function
	 * @param function1
	 *            - second function
	 * @param tag
	 *            - the type of element being compared
	 * 
	 * @return the list of properties that differs between the 2 functions
	 */
	public ElementDifference compare(Function function1, Function function2, String tag)
	{
		// compare attributes of the function
		ElementDifference property = compare((Element) function1, (Element) function2, tag);

		// compare Input field set
		compareInputFieldSets(property, function1, function2);

		// compare Update APIs
		compareUpdateAPI(property, function1, function2);

		return property;
	}

	/**
	 * Compare the input fieldsets between 2 functions
	 * 
	 * @param property
	 *            - to contain the differences
	 * @param function1
	 *            - first function
	 * @param function2
	 *            - second function
	 */
	private void compareInputFieldSets(ElementDifference property, Function function1, Function function2)
	{
		ElementComparatorToolbox.compareListOfSubElements(property, function1.inputFieldSetKeys(), function2.inputFieldSetKeys(),
						function1.getInputFieldSets(), function2.getInputFieldSets(), ElementComparatorToolbox.TYPE_INPUTFIELDSET,
						copyDirection);
	}

	/**
	 * Compare the input fieldsets between 2 functions
	 * 
	 * @param property
	 *            - to contain the differences
	 * @param function1
	 *            - first function
	 * @param function2
	 *            - second function
	 */
	private void compareUpdateAPI(ElementDifference property, Function function1, Function function2)
	{
		ElementComparatorToolbox.compareListOfSubElements(property, function1.updateAPIKeys(), function2.updateAPIKeys(), function1
						.getUpdateAPIs(), function2.getUpdateAPIs(), ElementComparatorToolbox.TYPE_UPDATEAPI, copyDirection);
	}
}
