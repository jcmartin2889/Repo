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
 * Equation test cases for Add/Maintain Transfer Method
 */
public class MTM extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MTM.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "S43FRR";
	String optionId = "MTM";

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
		transaction.setFieldValue("GZXM", "IV"); // Transfer method (2A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		// Screen 2
		transaction.setFieldValue("GZXMD", "Test"); // Transfer method description (25A)
		transaction.setFieldValue("GZEXF", "1"); // Spot/user/retail rate (1A)
		transaction.setFieldValue("GZNSF1", "N"); // Mail document (1A)
		transaction.setFieldValue("GZNSF2", "N"); // Telex (1A)
		transaction.setFieldValue("GZNSF3", "N"); // Telex via SWIFT (1A)
		transaction.setFieldValue("GZNSF4", "N"); // SWIFT format (1A)
		transaction.setFieldValue("GZNSF5", "N"); // Facsimile (1A)
		transaction.setFieldValue("GZNSF6", "N"); // No document required (1A)
		// Screen 3
		transaction.setFieldValue("GZPRT", "N"); // Print owning settlement? (1A)
		transaction.setFieldValue("GZASI", "N"); // Abbreviated settlement instructions (1A)
		transaction.setFieldValue("GZVXD", "N"); // Valid for EXIMBILLS? (1A)
		transaction.setFieldValue("GZVBT", "N"); // Valid for BACS debit transfer? (1A)
		transaction.setFieldValue("GZCBT", "N"); // Valid for BACS credit transfer? (1A)
		transaction.setFieldValue("GZBCC", "N"); // BACS claims raised on the due date (1A)
		transaction.setFieldValue("GZBCI", "N"); // Bank cheque/draft item (1A)
		transaction.setFieldValue("GZNBAL", "0"); // No of beneficiary address lines (1A)
		// Screen 4
		transaction.setFieldValue("GZCI57", "Y"); // Include field 57 (CR) (1A)
		transaction.setFieldValue("GZC57O", "1"); // Field 57A option (CR) (1A)
		transaction.setFieldValue("GZCI59", "N"); // Include field 59A (CR) (1A)
		transaction.setFieldValue("GZCI70", "N"); // Include field 70 (CR) (1A)
		// Screen 5
		transaction.setFieldValue("GZI57", "Y"); // Include field 57 (DR) (1A)
		transaction.setFieldValue("GZD57O", "1"); // Field 57A option (DR) (1A)
		transaction.setFieldValue("GZDI59", "N"); // Include field 59A (DR) (1A)
		transaction.setFieldValue("GZDI70", "N"); // Include field 70 (DR) (1A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		// Screen 2
		transaction.setFieldValue("GZXMD", "Test 1"); // Transfer method description (25A)
	}

}
