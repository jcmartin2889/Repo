package com.misys.equation.common.test.globalmodules;

import com.misys.equation.common.test.EquationTestCaseGlobalModuleMetaData;

public class SC899RW extends EquationTestCaseGlobalModuleMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SC899RW.java 11511 2011-07-26 12:25:21Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "USD840G";
		invalidCCN = "GBP826G";
	}

}