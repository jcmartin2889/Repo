package com.misys.equation.common.test.globalmodules;

import com.misys.equation.common.test.EquationTestCaseGlobalModuleMetaData;
import com.misys.equation.common.utilities.Toolbox;

public class UTH22RW extends EquationTestCaseGlobalModuleMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UTH22RW.java 11511 2011-07-26 12:25:21Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		// 250,000 amount and 13.0000 rate - see WRKTBL QSYS/QEBCDIC on iSeries for pre-conversion hex values required
		String amount = Toolbox.cvtHexToStr("00000000000000000000000226000D"); // hex value of 000000000250000- before EBCDIC
		// conversion

		validCCN = amount + "USD";
		invalidCCN = amount + "YUK";

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
