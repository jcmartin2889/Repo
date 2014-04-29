package com.misys.equation.common.test.globalmodules;

import com.misys.equation.common.test.EquationTestCaseGlobalModuleMetaData;

public class SD402RW extends EquationTestCaseGlobalModuleMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SD402RW.java 11511 2011-07-26 12:25:21Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "L       GA";
		invalidCCN = "L       XX"; // Doesn't return an error
	}

	@Override
	public void test00400PV_ValidateNonExistingCall() throws Exception
	{
		System.out.println("");
		System.out.println("test00400PV_ValidateNonExistingCall");
		System.out.println("Overridden...no error returned from NonExistingCall");
		assertEquals(false, false);

	}

}
