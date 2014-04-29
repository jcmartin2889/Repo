/*
 * 
 */
package com.misys.equation.common.test.userexits;

import com.misys.equation.common.userexit.GenericValidationUserExit;

/**
 * 
 */
public class K49FVR extends GenericValidationUserExit
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: K49FVR.java 4721 2009-09-15 11:14:17Z weddelc1 $";
	/**
	 * Constructor
	 */
	public K49FVR()
	{
	}

	public void execute(String xfct, String xscrn, String xmode)
	{
		if (xscrn.equals("2") && xmode.equals("D"))
		{
			if (getFieldValue("ZLRRD").trim().equals(""))
			{
				setFieldValue("ZLRRD", "JAVA exit default some text");
				setFieldValue("ZLCRRZ", "  3.5");
			}
		}
	}
}
