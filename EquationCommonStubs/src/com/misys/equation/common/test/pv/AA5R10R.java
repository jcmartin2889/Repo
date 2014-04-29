package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class AA5R10R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AA5R10R.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "02RE2EUR       DEPOSIT - MATURING AT S   - EURO                  0991201";
		invalidCCN = "02RE2EUR       DEPOSIT - MATURING AT S   - EURO                  0991202";
	}
}
