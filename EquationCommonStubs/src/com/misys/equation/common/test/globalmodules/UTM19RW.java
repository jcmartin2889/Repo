package com.misys.equation.common.test.globalmodules;

import com.misys.equation.common.test.EquationTestCaseGlobalModuleMetaData;

public class UTM19RW extends EquationTestCaseGlobalModuleMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UTM19RW.java 11511 2011-07-26 12:25:21Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "1000105";
		invalidCCN = "X000105";
	}

}