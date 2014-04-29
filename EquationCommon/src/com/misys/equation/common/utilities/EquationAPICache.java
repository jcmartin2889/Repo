package com.misys.equation.common.utilities;

import com.misys.equation.common.access.EquationStandardValidation;
import com.misys.equation.common.access.IEquationStandardObject;

public class EquationAPICache
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationAPICache.java 10470 2011-02-10 10:31:03Z MACDONP1 $";
	private final IEquationStandardObject equationStandardObject;

	/**
	 * Construct an Equation PV key based on the Equation Standard Validation
	 * 
	 * @param equationStandardObject
	 *            - Equation Standard Object
	 */
	public EquationAPICache(IEquationStandardObject iEquationStandardObject)
	{
		this.equationStandardObject = iEquationStandardObject;
	}

	/**
	 * Return as the Equation Standard Validation
	 * 
	 * @return the Equation Standard Validation
	 */
	public EquationStandardValidation getEquationStandardValidation()
	{
		return (EquationStandardValidation) equationStandardObject;
	}

	/**
	 * Return the Equation Standard Object
	 * 
	 * @return the Equation Standard Object
	 */
	public IEquationStandardObject getEquationStandardObject()
	{
		return equationStandardObject;
	}
}