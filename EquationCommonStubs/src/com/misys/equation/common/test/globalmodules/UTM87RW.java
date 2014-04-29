package com.misys.equation.common.test.globalmodules;

import com.misys.equation.common.test.EquationTestCaseGlobalModuleMetaData;
import com.misys.equation.common.utilities.Toolbox;

public class UTM87RW extends EquationTestCaseGlobalModuleMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UTM87RW.java 11511 2011-07-26 12:25:21Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		// 250,000 amount and 13.0000 rate - see WRKTBL QSYS/QEBCDIC on iSeries for pre-conversion hex values required
		String amount = Toolbox.cvtHexToStr("000000000226000C"); // hex value of 000000000250000+ before EBCDIC conversion
		String rate = Toolbox.cvtHexToStr("00130000000C"); // hex value of 00130000000+ before EBCDIC conversion

		validCCN = "09912311000105" + amount + rate + "1N0USD";
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
