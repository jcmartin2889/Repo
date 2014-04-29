package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class H0R10R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: H0R10R.java 11003 2011-05-19 14:03:48Z MACDONP1 $";

	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "CITYCLQY2C2104DAY2";
		invalidCCN = "CITYCLQY2C2104DAYX";
	}

	@Override
	public void test00200PV_GetDataCall() throws Exception
	{
		System.out.println("");
		System.out.println("test002A00PV_CheckMetaData");
	}

}