package com.misys.equation.common.test;

import com.misys.equation.common.access.EquationData;

/**
 * Test cases for PVs
 */
abstract public class EquationTestCaseValidate extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationTestCaseValidate.java 5802 2010-01-12 16:53:48Z esther.williams $";
	protected String validCCN;
	protected String invalidCCN;
	protected String decode = " ";
	protected boolean largeCCN = false;

	/**
	 * Test: validate mode: existing record<br>
	 * Expected result: Pass<br>
	 * 
	 * @throws Exception
	 */
	public void test00100_ValidateExistingCall() throws Exception
	{
		System.out.println("test00100_ValidateExistingCall()");

		String pvName;
		String className = this.getClass().getSimpleName();
		int _position = className.indexOf('_');
		if (_position == -1)
		{
			pvName = className;
		}
		else
		{
			pvName = className.substring(0, _position);
		}

		EquationData data = getEQData(pvName, validCCN, decode, "N");
		String error = data.getErrorMessage().trim();
		System.out.println("Validate Existing Call");
		if (!error.equals(""))
		{
			System.out.println("Error: " + error);
		}
		System.out.println("Data Returned: " + data.getData());
		assertEquals(true, error.equals(""));
	}

	/**
	 * Test: validate mode: Non-existing record<br>
	 * Expected result: Pass<br>
	 * 
	 * @throws Exception
	 */
	public void test00200_ValidateNonExistingCall() throws Exception
	{
		System.out.println("test00200_ValidateNonExistingCall()");
		String pvName;
		String className = this.getClass().getSimpleName();
		int _position = className.indexOf('_');
		if (_position == -1)
		{
			pvName = className;
		}
		else
		{
			pvName = className.substring(0, _position);
		}
		EquationData data = getEQData(pvName, invalidCCN, decode, "N");
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
