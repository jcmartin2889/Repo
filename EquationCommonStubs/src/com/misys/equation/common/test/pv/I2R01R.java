package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class I2R01R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: I2R01R.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "CTAMT     000";
		invalidCCN = "CTAMT     001";
	}
}
