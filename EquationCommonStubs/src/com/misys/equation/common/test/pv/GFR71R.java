package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class GFR71R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GFR71R.java 11505 2011-07-25 18:55:19Z ESTHER.WILLIAMS $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		// validCCN = "ATLANT";
		validCCN = "ATLANT   ";
		invalidCCN = "XXXXXXXXX";
	}
}
