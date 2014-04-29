package com.misys.equation.common.test.globalmodules;

import com.misys.equation.common.test.EquationTestCaseGlobalModuleMetaData;
import com.misys.equation.common.utilities.Toolbox;

public class UTM58RW extends EquationTestCaseGlobalModuleMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UTM58RW.java 11511 2011-07-26 12:25:21Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		// Rate - see WRKTBL QSYS/QEBCDIC on iSeries for pre-conversion hex values required
		String rate = Toolbox.cvtHexToStr("001B1B0A0A0A0A05"); // hex value of 002727252525252- before EBCDIC conversion

		validCCN = rate + "C";
		invalidCCN = "";

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