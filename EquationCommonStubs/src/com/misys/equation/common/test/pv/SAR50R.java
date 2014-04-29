package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class SAR50R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SAR50R.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "                  0991230KBSL@@CC 0000001";
		invalidCCN = "                  0991230KBSL@@CC 9999999";

		decode = "";
	}
}
