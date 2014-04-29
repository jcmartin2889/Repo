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
 * Equation test cases for the MAINTAIN part of the (Watch List) Check Deal Settlement function (see the EWD_AddMore.java class for
 * the ADD part). The AddMaintain and AddOnce classes are not appropriate for EWD because it is a non-standard function and sets @FCT
 * automatically in the function. For instance, if you do 2 'adds', the 2nd one will default to a 'maintain', and so will fail, so
 * the Add_DuplicateRecord test is N/A. Similarly with the Maint_NonExistingRecord test.
 */
public class EWD_Maintain extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EWD_Maintain.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "W12MRR";
	String optionId = "EWD";
	boolean isDelayExecuted = false;
	

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		if (!isDelayExecuted)
		{

			Thread.sleep(1500);
			isDelayExecuted = true;
		}
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
	 * Setup a non-existing key fields NB. These are function key fields NOT WLC case key fields. So, this must be for a
	 * non-existing deal NOT an existing deal without an existing case.
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDLP", "RDX"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "XEF-D-001"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "ACC1"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZGDLP", ""); // Group deal type (3A)
		transaction.setFieldValue("GZGDLR", ""); // Group deal reference (13A)
		transaction.setFieldValue("GZGBNM", ""); // Group deal branch (4A)

		/**
		 * Although not actual key fields, these are needed here. Otherwise, case status will be incorrect, i.e. a no hit status
		 * after the last test.
		 */
		transaction.setFieldValue("GZDLFD", "Y"); // Load from database (1A)
		transaction.setFieldValue("GZDUPF", "Y"); // Update database if watch list unavailable? (1A)
	}

	/**
	 * Setup an existing key fields This will re-check (maintain) the WLC case raised for the customer created in the
	 * AddForWLC_ANC.java class.
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDLP", "OP1"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "DG006"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "ACC1"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZGDLP", ""); // Group deal type (3A)
		transaction.setFieldValue("GZGDLR", ""); // Group deal reference (13A)
		transaction.setFieldValue("GZGBNM", ""); // Group deal branch (4A)

		/**
		 * Although not actual key fields, these are needed here. Otherwise, case status will be incorrect, i.e. a no hit status
		 * after the last test.
		 */
		transaction.setFieldValue("GZDLFD", "Y"); // Load from database (1A)
		transaction.setFieldValue("GZDUPF", "Y"); // Update database if watch list unavailable? (1A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDSES", "N"); // Suspect on external system (1A)
		transaction.setFieldValue("GZDSWC", "N"); // Suppress watch list checking (1A)
		transaction.setFieldValue("GZDCEX", "N"); // Case exists (1A)
		transaction.setFieldValue("GZDSAU", "N"); // Suppress authorisation (1A)
		transaction.setFieldValue("GZDWLC", "N"); // Already performed watch list checking (1A)
	}

}
