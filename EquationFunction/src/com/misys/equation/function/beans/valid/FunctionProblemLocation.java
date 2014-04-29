package com.misys.equation.function.beans.valid;

import com.misys.equation.function.beans.Element;
import com.misys.equation.problems.ProblemLocation;

public class FunctionProblemLocation extends ProblemLocation
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionProblemLocation.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	/**
	 * Constructor accepting an Element
	 * <p>
	 * The class name will be determined from the Element instance. The item property will be set to the path of the element
	 * 
	 * @param element
	 *            the element
	 */
	public FunctionProblemLocation(Element element)
	{
		super(element.getClass().getSimpleName(), element.rtvPath());
	}

	/**
	 * Constructor
	 * 
	 * @param validatorClassName
	 *            Name of the class in which the error was found
	 * @param item
	 *            The item (typically an id)
	 */
	public FunctionProblemLocation(String validatorClassName, String item)
	{
		super(validatorClassName, item);
	}
}
