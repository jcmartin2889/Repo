package com.misys.equation.common.test.globalmodules;

import com.misys.equation.common.test.EquationTestCaseGlobalModuleMetaData;

public class SN301RW extends EquationTestCaseGlobalModuleMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SN301RW.java 11511 2011-07-26 12:25:21Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "USDFRF000000000000000010000                     1   000003000000000Y                                                  1000104";
		invalidCCN = "USDFRF000000000000000010000                     1   000003000000000Y                                                  10X0104";
	}

}
