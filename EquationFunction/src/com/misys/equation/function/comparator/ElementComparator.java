package com.misys.equation.function.comparator;

import java.lang.reflect.Method;

import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.function.beans.Element;

public class ElementComparator
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ElementComparator.java 10938 2011-05-11 21:41:50Z perkinj1 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(ElementComparator.class);

	public static final int COPY_NONE = 0;
	public static final int COPY_TO_FIRST = 1;
	public static final int COPY_TO_SECOND = 2;
	/** Determine the copy direction flag whether to copy to the first or second element when the values are different */
	protected int copyDirection = 0;

	/**
	 * Construct an Element Comparator
	 * 
	 */
	public ElementComparator()
	{
	}

	/**
	 * Determine whether to ignore the method or not
	 * 
	 * @param methodName
	 *            - the method name
	 * 
	 * @return true if method is ignored
	 */
	protected boolean ignoreMethod(String methodName)
	{
		return false;
	}

	/**
	 * Return the copy direction flag
	 * 
	 * @return the copy direction flag
	 */
	public int getCopyDirection()
	{
		return copyDirection;
	}

	/**
	 * Set the copy direction flag
	 * 
	 * @param copyDirection
	 *            - the copy direction flag
	 */
	public void setCopyDirection(int copyDirection)
	{
		this.copyDirection = copyDirection;
	}

	/**
	 * Return the list of properties that differs between 2 elements
	 * 
	 * @param element1
	 *            - the first element
	 * @param element2
	 *            - the second element
	 * @param tag
	 *            - the type of element being compared
	 * 
	 * @return the list of properties that differs between 2 elements
	 */
	public ElementDifference compare(Element element1, Element element2, String tag)
	{
		ElementDifference properties = new ElementDifference(element1.getId(), tag);

		// use reflection to retrieve getter methods
		Method[] methods = element1.getClass().getMethods();
		for (Method method : methods)
		{
			// interested only in getter method
			String methodName = method.getName();
			if (!ignoreMethod(methodName) && ElementComparatorToolbox.isGetterPrimitive(method))
			{
				PropertyDifference pd = ElementComparatorToolbox.compareProperty(method, element1, element2, tag);
				if (pd != null)
				{
					properties.addPropertyDifference(pd);

					// copy the value?
					if (copyDirection != COPY_NONE && pd.getFlag() == PropertyDifference.FLAG_CHANGED)
					{
						String setterMethodName;
						if (methodName.startsWith("get"))
						{
							setterMethodName = methodName.replace("get", "set");
						}
						else
						{
							setterMethodName = methodName.replace("is", "set");
						}

						try
						{
							Method setterMethod = element1.getClass().getMethod(setterMethodName, method.getReturnType());
							if (copyDirection == COPY_TO_FIRST)
							{
								ElementComparatorToolbox.setProperty(setterMethod, element1, pd.getObject2());
							}
							else
							{
								ElementComparatorToolbox.setProperty(setterMethod, element2, pd.getObject1());
							}
						}
						catch (NoSuchMethodException e)
						{
							LOG.debug("No method exists for " + setterMethodName);
						}

					}
				}
			}
		}

		return properties;
	}

}
