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
import com.misys.equation.common.test.EquationTestCaseAddMaintain;

/**
 * Equation test cases for Maintain function
 */
public class NTA extends EquationTestCaseAddMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: NTA.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "M32FRR";
	String optionId = "NTA";

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
	 * Setup a non-existing key fields only
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZFAB", "0543"); // From MDS account branch (4A)
		transaction.setFieldValue("GZFAN", "123467"); // From MDS account number (6A)
		transaction.setFieldValue("GZFAS", "001"); // From MDS account suffix (3A)
		transaction.setFieldValue("GZFREF", "NCA0000001"); // From NCA reference (10A)
		transaction.setFieldValue("GZTAB", "0543"); // To MDS account branch (4A)
		transaction.setFieldValue("GZTAN", "123467"); // To MDS account number (6A)
		transaction.setFieldValue("GZTAS", "009"); // To MDS account suffix (3A)
		transaction.setFieldValue("GZTREF", "NCA0000009"); // To NCA reference (10A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAMT", "50000"); // Amount to transfer (15P,0)
		transaction.setFieldValue("GZAUT", "N"); // Authorised ? yes or no (1A)
		transaction.setFieldValue("GZAUTH", "N"); // F18 = Authorised pressed (1A)
		transaction.setFieldValue("GZAUDT", "0"); // Authorised date (7S,0)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAUT", "Y"); // Authorised ? yes or no (1A)
		transaction.setFieldValue("GZAUTH", "Y"); // F18 = Authorised pressed (1A)
		transaction.setFieldValue("GZAUDT", "1000105"); // Authorised date (7S,0)
	}
	@Override
	public void test01300Maint_RetrievalMaintain_ExistingRecord() throws Exception
	{
		/**
		 * During Add, a record is being added to MD503LF. Once maintained, the record in MD503LF was deleted. Test01300 is skipped
		 * since the record in MD503LF was already been deleted in Test01200. KSM2010 will be issued if Test01300 was not skipped.
		 */

	}
}
