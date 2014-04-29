package com.misys.equation.common.test.validate;

import com.misys.equation.common.access.EquationData;
import com.misys.equation.common.test.EquationTestCaseValidate;

public class XZV02R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: XZV02R.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	public XZV02R()
	{
		// Set up a valid CCN
		validCCN = "CUSBNK  001000201099290012221";

		// Set up an invalid CCN
		invalidCCN = "CUSBNK  001000201099290001002";
	}

	@Override
	public void test00100_ValidateExistingCall() throws Exception
	{
		System.out.println("test00100_ValidateExistingCall()");
		EquationData data = getEQData(this.getClass().getSimpleName(), validCCN, decode, "N");
		String error = data.getErrorMessage().trim();
		System.out.println("Validate Existing Call");
		if (!error.startsWith("KSM5332"))
		{
			System.out.println("Error: " + error);
		}
		System.out.println("Data Returned: " + data.getData());
		assertEquals(true, error.startsWith("KSM5332"));
	}
}