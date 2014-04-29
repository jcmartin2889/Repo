package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class IPBR01R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: IPBR01R.java 12411 2011-12-21 18:02:15Z TODDJ1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "EURADM";
		invalidCCN = "MMM";
	}
}
