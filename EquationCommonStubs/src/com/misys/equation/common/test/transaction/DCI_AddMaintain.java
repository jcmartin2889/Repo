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
 * Equation test cases for Standing Settlement Instructions (Add/Maintain)
 * */
public class DCI_AddMaintain extends EquationTestCaseAddMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DCI_AddMaintain.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G03FRR";
	String optionId = "DCI";

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
		transaction.setFieldValue("GZCUS", "ATLANT"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", "PS"); // Customer location (3A)
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZMVT", "P"); // Movement type; I=Interest, P=Principal (1A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZPCCY", "GBP"); // Pay settlement currency (3A)
		transaction.setFieldValue("GZINA1", "NON-MANDATORY; TO ADD FVPF RECORD"); // Intermediary postal address 1 (35A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZPCCY", "USD"); // Pay settlement currency (3A)
		transaction.setFieldValue("GZINA1", "NON-MANDATORY;TO CHANGE FVPF RECORD"); // Intermediary postal address 1 (35A)
	}

}