package com.misys.equation.common.test.validate;

import com.misys.equation.common.access.EquationData;
import com.misys.equation.common.test.EquationTestCaseValidate;

public class UHV02R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UHV02R.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	public UHV02R()
	{
		// Set up a valid CCN
		validCCN = "123456789";

		// Set up an invalid CCN
		invalidCCN = "12 4567890000";
	}
	@Override
	public void test00100_ValidateExistingCall() throws Exception
	{
		System.out.println("test00100_ValidateExistingCall()");
		EquationData data = getEQData(this.getClass().getSimpleName(), validCCN, decode, "N", "N");
		String error = data.getErrorMessage().trim();
		System.out.println("Validate Existing Call");
		if (!error.equals(""))
		{
			System.out.println("Error: " + error);
		}
		System.out.println("Data Returned: " + data.getData());
		assertEquals(true, error.equals(""));
	}

	@Override
	public void test00200_ValidateNonExistingCall() throws Exception
	{
		System.out.println("test00200_ValidateNonExistingCall()");
		EquationData data = getEQData(this.getClass().getSimpleName(), invalidCCN, " ", "N", "N");
		String error = data.getErrorMessage().trim();
		System.out.println("Validate Non-Existing Call");
		if (!error.equals(""))
		{
			System.out.println("Error: " + error);
		}
		System.out.println("Data Returned: " + data.getData());
		assertEquals(false, error.equals(""));
	}

}
