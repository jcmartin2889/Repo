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
import com.misys.equation.common.test.EquationTestCaseAddMore;

/**
 * Equation test cases for Add External Event
 */
public class IGA extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: IGA.java 4824 2009-09-22 11:11:11Z challip1 $";
	String programName = "H18ARR";
	String optionId = "IGA";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
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
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTREF", "IGATEST002"); // Transfer reference (16A)
		transaction.setFieldValue("GZAB1", "0543"); // Debit account branch (4A)
		transaction.setFieldValue("GZAN1", "123467"); // Debit account basic no (6A)
		transaction.setFieldValue("GZAS1", "086"); // Debit account suffix (3A)
		transaction.setFieldValue("GZAB2", "0000"); // Credit account branch (4A)
		transaction.setFieldValue("GZAN2", "000001"); // Credit account basic no (6A)
		transaction.setFieldValue("GZAS2", "600"); // Credit account suffix (3A)
	}

	/**
	 * Setup an existing key fields
	 */
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBRNM", "LOND");
		transaction.setFieldValue("GZTREF", "IGATEST002"); // Transfer reference (16A)
		transaction.setFieldValue("GZAB1", "0543"); // Debit account branch (4A)
		transaction.setFieldValue("GZAN1", "123467"); // Debit account basic no (6A)
		transaction.setFieldValue("GZAS1", "086"); // Debit account suffix (3A)
		transaction.setFieldValue("GZAB2", "0543"); // Credit account branch (4A)
		transaction.setFieldValue("GZAN2", "123467"); // Credit account basic no (6A)
		transaction.setFieldValue("GZAS2", "085"); // Credit account suffix (3A)

	}

	/**
	 * Setup the mandatory fields
	 */
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZVFR1", "991231"); // Debit value date (7S,0)
		transaction.setFieldValue("GZAMA1", "-1000"); // Debit amount (15P,0)
		transaction.setFieldValue("GZAPP1", "GD"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
		transaction.setFieldValue("GZTCD1", "010"); // Debit transaction code (3A)
		transaction.setFieldValue("GZCCY1", "USD"); // Debit account currency (3A
		transaction.setFieldValue("GZCED1", "2"); // Debit currency edit code (1A)
		transaction.setFieldValue("GZVFR2", "1000107"); // Credit value date (7S,0)
		transaction.setFieldValue("GZAMA2", "1000"); // Credit amount (15P,0)
		transaction.setFieldValue("GZAPP2", "GD"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
		transaction.setFieldValue("GZTCD2", "510"); // Credit transaction code (3A)
		transaction.setFieldValue("GZCCY2", "USD"); // Credit account currency (3A)
		transaction.setFieldValue("GZCED2", "2"); // Credit currency edit code (1A)
		transaction.setFieldValue("GZFONT", "N"); // Fontis transfer? (1A)
		transaction.setFieldValue("GZEXRH", ".000000000"); // Exchange rate (15P,9)
		transaction.setFieldValue("GZBAM1", "0"); // Equivalent amount in base currency - Dr (15P,0)
		transaction.setFieldValue("GZOAM1", "0"); // Equivalent amount in user currency - Dr (15P,0)
		transaction.setFieldValue("GZBAM2", "0"); // Equivalent amount in base currency - Cr (15P,0)
		transaction.setFieldValue("GZOAM2", "0"); // Equivalent amount in user currency - Cr (15P,0)
		transaction.setFieldValue("GZIEA", "N"); // Inter a/c transfer with equivalent amounts flag (1A)
		transaction.setFieldValue("GZSOT", "N"); // Standing order transfer? (1A)
		transaction.setFieldValue("GZGDP", "Y"); // Generic deal posting (1A)
		transaction.setFieldValue("GZCRT", "N"); // Court Order (1A)
		transaction.setFieldValue("GZALL", "N"); // Hold whole a/c balance (1A)
		transaction.setFieldValue("GZHOLD", "N"); // Hold on queue (1A)
		transaction.setFieldValue("GZPDTE", "991231"); // Date entered (7S,0)
		transaction.setFieldValue("GZFOR", "N"); // Force to queue (1A)
		transaction.setFieldValue("GZDTTY", "0"); // Date of last retry of queued transaction (7S,0)
		transaction.setFieldValue("GZQSCR", "N"); // Display screen (1A)
		transaction.setFieldValue("GZQDTE", "1000105"); // Queued date (7S,0)
		transaction.setFieldValue("GZQMSQ", "0"); // Maintenance sequence number (7P,0)
		transaction.setFieldValue("GZQCHK", "0"); // Number of checkers (2S,0)
		transaction.setFieldValue("GZQCHD", "0"); // Times checked (2S,0)
		transaction.setFieldValue("GZQRED", "0"); // Required date (7S,0)
		transaction.setFieldValue("GZQRET", "0"); // Required time (6S,0)
		transaction.setFieldValue("GZQEVM", "IGA"); // Events (6A)
		transaction.setFieldValue("GZDRR", "N"); // Debit reversal transaction (1A)
		transaction.setFieldValue("GZCRR", "N"); // Credit reversal transaction (1A)
		transaction.setFieldValue("GZDRCL", "0"); // Debit amount in credit limit currency (15P,0)
		transaction.setFieldValue("GZCRCL", "0"); // Credit amount in credit limit currency (15P,0)
		transaction.setFieldValue("GZPBR", "EQUI"); // Posting group id or user id, and group level (5A)
		// transaction.setField("GZDRO", "N"); // Original debit reversal transaction (1A)
		// transaction.setField("GZCRO", "N"); // Original credit reversal transaction (1A)
	}

}
