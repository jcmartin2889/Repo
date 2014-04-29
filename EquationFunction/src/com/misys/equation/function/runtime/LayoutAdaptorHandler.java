package com.misys.equation.function.runtime;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.adaptor.LayoutAdaptor;

/**
 * This class manages access to the Java user exit code for Layout definitions.
 */
public class LayoutAdaptorHandler
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LayoutAdaptorHandler.java 6793 2010-03-31 12:10:20Z deroset $";

	/**
	 * Return a Layout Reflection of the option
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param optionId
	 *            - the option Id
	 * 
	 * @return the layout reflection of the option
	 */
	public LayoutAdaptor getLayoutAdaptor(EquationStandardSession session, String optionId) throws EQException
	{
		LayoutAdaptor fr = new LayoutAdaptor(session, optionId);
		return fr;
	}

}
