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
import com.misys.equation.common.test.EquationTestCaseFully;

/**
 * Equation test cases for Add/Maintain Collateral Items
 */
public class CLT extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CLT.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "U12FRR";
	String optionId = "CLT";

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
		transaction.setFieldValue("GZCLR", "CN146863 COLL"); // Collateral reference (35A)
		transaction.setFieldValue("GZCLP", "RD"); // Collateral type (3A)
		transaction.setFieldValue("GZMCV" , "99999");// Maximum value
		transaction.setFieldValue("GZSVM", "000.0"); // Special valuation margin (5P,3)
		//transaction.setFieldValue("GZDLP", "RDX");
		//transaction.setFieldValue("GZDLR", "REF-C-003");
		//transaction.setFieldValue("GZDBNM", "KBSL");
		
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCUS", "ACCS"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", "DTA"); // Customer location (3A)
		transaction.setFieldValue("GZNR1", "NARRATIVE"); // Narrative line 1 (35A)
		transaction.setFieldValue("GZCCM", "N"); // Collateral complete (1A)
		transaction.setFieldValue("GZCXD", "9999999"); // Collateral expiry date (7S,0)
		transaction.setFieldValue("GZSVM", "99999"); // Special valuation margin (5P,3)
		transaction.setFieldValue("GZLRD", "0"); // Last review date (7S,0)
		transaction.setFieldValue("GZNRD", "0"); // Next review date (7S,0)
		transaction.setFieldValue("GZIXD", "9999999"); // Insurance expiry date (7S,0)
		transaction.setFieldValue("GZNOU", "2"); // Number of units (9P,0)
		//transaction.setFieldValue("GZCLV", 9999999);
		
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZNR1", "DESCRIPTION"); // Narrative line 1 (35A)
	}

}
