/*
 * 
 */
package com.misys.equation.common.test.userexits;

import com.misys.equation.common.userexit.GenericValidationUserExit;

/**
 * 
 */
public class H60FVR extends GenericValidationUserExit
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: H60FVR.java 4721 2009-09-15 11:14:17Z weddelc1 $";
	/**
	 * Constructor
	 */
	public H60FVR()
	{
	}

	public void execute(String xfct, String xscrn, String xmode)
	{
		if (getFieldValue("ZLPZIP").trim().equals("SL6 2NF") && getFieldValue("ZLNA1").trim().equals(""))
		{
			setFieldValue("ZLNA1", "1, White Hart Cottages");
			setFieldValue("ZLNA2", "Moneyrow Green");
			setFieldValue("ZLNA3", "Holyport");
			setFieldValue("ZLNA4", "Maidenhead");
			setFieldValue("ZLNA5", "Berkshire");
		}
	}
}
