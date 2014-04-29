package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class V2R10R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: V2R10R.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "CITYCL9CRLL1N3      290200";
		invalidCCN = "CITYCL9CRLL1N3      290201";
	}
}