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
 * Equation test cases for Maintain function
 */
public class ACQ extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACQ.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "M31FRR";
	String optionId = "ACQ";

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

	// Note: Cheque number field is 16 bytes right justified.
	// F22=Cancel will work only if the ACIC/Cheque record is not yet authorized.
	// NCA reference must already exist and authorized. (To create, use WMENU1 NCA)
	// (Please see K493 MDS enhancement notes for details)

	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZREF", "ACIC147903"); // ACIC/ADA reference (10A)
		transaction.setFieldValue("GZNREF", "NCA1479001"); // NCA reference (10A)
		transaction.setFieldValue("GZCHQN", "        00000001"); // Cheque serial number (16A)

	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "1479"); // MDS account branch (4A)
		transaction.setFieldValue("GZAN", "110221"); // MDS account number (6A)
		transaction.setFieldValue("GZAS", "001"); // MDS account suffix (3A)
		transaction.setFieldValue("GZCHQA", "700000"); // Cheque amount (15P,0)
		transaction.setFieldValue("GZDTE", "1000101"); // Transaction date (7S,0)
		transaction.setFieldValue("GZCDTE", "1000101"); // Cheque date (7S,0)
		transaction.setFieldValue("GZCCDT", "0"); // Cheque cancelled date (7S,0)
		transaction.setFieldValue("GZOBJ", "101"); // Object class allotment (10A)
		transaction.setFieldValue("GZPAY", "ACQ Payee fr 1479-110221-001"); // Payee information'(mandatory) (35A)
		transaction.setFieldValue("GZAUT", "N"); // Authorised Flag? yes or no (1A)
		transaction.setFieldValue("GZAUTH", " "); // F18 = Authorised pressed (1A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "1479"); // MDS account branch (4A)
		transaction.setFieldValue("GZAN", "110221"); // MDS account number (6A)
		transaction.setFieldValue("GZAS", "001"); // MDS account suffix (3A)
		transaction.setFieldValue("GZCHQA", "900000"); // Cheque amount (15P,0)
		transaction.setFieldValue("GZDTE", "1000101"); // Transaction date (7S,0)
		transaction.setFieldValue("GZCDTE", "1000101"); // Cheque date (7S,0)
		transaction.setFieldValue("GZCCDT", "0"); // Cheque cancelled date (7S,0)
		transaction.setFieldValue("GZOBJ", "101"); // Object class allotment (10A)
		transaction.setFieldValue("GZPAY", "ACQ Payee changed 1479-110221-001"); // Payee information'(mandatory) (35A)
		transaction.setFieldValue("GZAUT", "N"); // Authorised Flag? yes or no (1A)
		transaction.setFieldValue("GZAUTH", " "); // F18 = Authorised pressed (1A)
	}

}
