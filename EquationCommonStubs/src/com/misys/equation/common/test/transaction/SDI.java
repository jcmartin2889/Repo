package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseFully;

/**
 * Equation test cases for Maintain Stock Issuer Details
 */
public class SDI extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SDI.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "S11FRR";
	String optionId = "SDI";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		retrieveBeforeCancel = false;
	}

	// ------------------------------------------------------------------------ Helper methods
	/**
	 * Return a transaction
	 * 
	 * @return a transaction
	 * 
	 * @throws Exception
	 */
	@Override
	public EquationStandardTransaction getTransaction() throws Exception
	{
		EquationStandardTransaction transaction = getEquationStandardTransaction(programName + optionId);
		transaction.setWorkStationId(WORKSTATIONID);
		return transaction;
	}

	// ------------------------------------------------------------------------ Field setups

	/**
	 * Setup a non-existing key fields only
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZSIT", "CARL"); // Stock item type
		transaction.setFieldValue("GZCUS", "ABRAHA"); // Issuer-customer mnemonic
		transaction.setFieldValue("GZCLC", "ISA"); // Issuer-customer location
		transaction.setFieldValue("GZCPNC", "700004"); // Issuer-customer number
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZSIT", "CARL"); // Stock item type
		transaction.setFieldValue("GZCUS", "ABRAHA"); // Issuer-customer mnemonic
		transaction.setFieldValue("GZCLC", "ISA"); // Issuer-customer location
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZSTA", "SP101"); // Stock account
		transaction.setFieldValue("GZSTCA", "SP101"); // Stock contra account
	}

}
