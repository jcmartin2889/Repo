package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class R4R01R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: R4R01R.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		// Whole unique key must be supplied. This included Type, Currency, Serial Number, and Stop Amount
		// 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5
		// 123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456
		validCCN = "0132012008050                                                                       GBPS                                     00000010                      0";
		invalidCCN = "0132012008050                                                                       GBPS                                     00000010                      1";
	}
}
