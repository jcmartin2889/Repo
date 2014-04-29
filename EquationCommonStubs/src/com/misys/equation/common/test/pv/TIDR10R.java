package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class TIDR10R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TIDR10R.java 12411 2011-12-21 18:02:15Z TODDJ1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "BFLCE";
		invalidCCN = "XXXYY";
	}
}