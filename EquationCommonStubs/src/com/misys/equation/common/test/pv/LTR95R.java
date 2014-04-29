package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class LTR95R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LTR95R.java 8505 2010-08-05 15:48:25Z CHALLIP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "CITYCDQY2C2103      YOUNG GRPCAD       I            1000104000IT04JAN00 900000000000900             I";
		invalidCCN = "CITYCDQY2C2103      YOUNG GRPCAD       I            1000104000IT04JAN00 900000000000900             X";
		decode = "";
	}

	@Override
	public void test002A00PV_CheckMetaData() throws Exception
	{
	}
}
