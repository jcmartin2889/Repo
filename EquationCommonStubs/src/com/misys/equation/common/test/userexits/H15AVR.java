/*
 * 
 */
package com.misys.equation.common.test.userexits;

import com.misys.equation.common.userexit.GenericValidationUserExit;
import com.misys.equation.common.userexit.ValidationUserExitMessage;

/**
 * 
 */
public class H15AVR extends GenericValidationUserExit
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: H15AVR.java 4721 2009-09-15 11:14:17Z weddelc1 $";
	/**
	 * Constructor
	 */
	public H15AVR()
	{
	}

	public void execute(String xfct, String xscrn, String xmode)
	{
		if (!getFieldValue("ZLTCCY").trim().equals("USD") && !getFieldValue("ZLTCCY").trim().equals(""))
		{
			// Return a test message
			ValidationUserExitMessage mess1 = addMessage("KSM2078", 19);
			mess1.setParameter1("USD(exit)");

			// and another
			addMessage("KSM0017", 0);
		}
	}
}
