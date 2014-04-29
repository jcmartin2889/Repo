/**
 * Copyright and all other intellectual property rights in this software, in any form, is vested in Misys International Banking
 * Systems Ltd ("Misys") or a related company.
 * 
 * This software may not be copied, amended, compiled, translated, or developed; or sold, leased, hired, rented, or disclosed to any
 * third party without the prior written consent of Misys.
 * 
 * Copyright Misys International Banking Systems Ltd, 1975 and later
 */

package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseMaintain;

/**
 * Equation test cases for Assign WebFacing Widgets
 */
public class AWW extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AWW.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "A36MRR";
	String optionId = "AWW";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
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
	 * Setup a non-existing key fields
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDSPF", "XXXXX"); // Display file (10A)
		transaction.setFieldValue("GZRCDF", "XXXXXX"); // Record format (10A)
		transaction.setFieldValue("GZFLD", "XXXX"); // Field (10A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDSPF", "AAAR10D"); // Display file (10A)
		transaction.setFieldValue("GZRCDF", "EQSFLCTL"); // Record format (10A)
		transaction.setFieldValue("GZFLD", "ZLSITD"); // Field (10A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZFDES", "Description selection-EQ4 "); // Field description (50A)
		transaction.setFieldValue("GZROW", "004"); // Field row position (3S,0)
		transaction.setFieldValue("GZCOL", "031"); // Field column position (3S,0)
	}

}
