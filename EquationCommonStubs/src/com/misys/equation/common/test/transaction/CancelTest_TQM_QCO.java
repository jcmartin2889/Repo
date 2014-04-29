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
import com.misys.equation.common.test.EquationTestCaseAddOnce;

/**
 * @author weddelc1
 */
public class CancelTest_TQM_QCO extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CancelTest_TQM_QCO.java 8426 2010-07-29 17:32:49Z CHALLIP1 $";
	String programName = "H18ARR";
	String optionId = "ITA";

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
		transaction.setFieldValue("GZTREF", "QCOTEST001"); // Transfer reference (16A)
		transaction.setFieldValue("GZAB1", "0543"); // Debit account branch (4A)
		transaction.setFieldValue("GZAN1", "123467"); // Debit account basic no (6A)
		transaction.setFieldValue("GZAS1", "086"); // Debit account suffix (3A)
		transaction.setFieldValue("GZAB2", "0543"); // Credit account branch (4A)
		transaction.setFieldValue("GZAN2", "123467"); // Credit account basic no (6A)
		transaction.setFieldValue("GZAS2", "087"); // Credit account suffix (3A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTCD1", "010"); // Debit transaction code (3A)
		transaction.setFieldValue("GZAMA1", "-100"); // Debit amount (15P,0)
		transaction.setFieldValue("GZVFR1", "1000105"); // Debit value date (7S,0)
		transaction.setFieldValue("GZDRF1", "JTEST DEBIT"); // Debit user's reference (16A)

		transaction.setFieldValue("GZTCD2", "510"); // Credit transaction code (3A)
		transaction.setFieldValue("GZAMA2", "100"); // Credit amount (15P,0)
		transaction.setFieldValue("GZVFR2", "1000105"); // Credit value date (7S,0)
		transaction.setFieldValue("GZDRF2", "JTEST CREDIT"); // Credit user's reference (16A)

		transaction.setFieldValue("GZBRNM", "LOND"); // Transfer branch (4A)
		transaction.setFieldValue("GZBRND", "LOND"); // Branch for whom the transaction is being done (4A)
		transaction.setFieldValue("GZPBR", "JUNT"); // Posting group id or user id, and group level (5A)
		transaction.setFieldValue("GZFONT", "N"); // Fontis transfer? (1A)

		transaction.setFieldValue("GZPDTE", "1000105"); // Date entered (7S,0)
		transaction.setFieldValue("GZFOR", "Y"); // Force to queue (1A)
		transaction.setFieldValue("GZHOLD", "Y"); // Hold on queue (1A)
		transaction.setFieldValue("GZQNQ", "2"); // Automatic queuing (1A)
		transaction.setFieldValue("GZQEVM", "QCO"); // Events (6A)
		transaction.setFieldValue("GZQMSQ", "2"); // Maintenance sequence number (7P,0)
		transaction.setFieldValue("GZQACT", "02"); // Action (2A)
		transaction.setFieldValue("GZQBAL", "100"); // Queued balance (15P,0)
		transaction.setFieldValue("GZQDTE", "1000105"); // Queued date (7S,0)

		// transaction.setField("GZWSID", ""); // Workstation id (4A)
		// transaction.setField("GZDIM", ""); // Day in month (2S,0)
		// transaction.setField("GZTIM", ""); // Time; hhmmss (6S,0)
		// transaction.setField("GZSEQ", ""); // Sequence number (7P,0)
		// transaction.setField("GZIMG", ""); // Journal image (1A)
		// transaction.setField("GZREF", ""); // Redundant (5A)
		// transaction.setField("GZBRNM", ""); // Transfer branch (4A)
		// transaction.setField("GZPBR", ""); // Posting group id or user id, and group level (5A)
		// transaction.setField("GZPSQ", ""); // Obsolete - use GZPSQ7 (5P,0)
		// transaction.setField("GZBRND", ""); // Branch for whom the transaction is being done (4A)
		// transaction.setField("GZEAND", ""); // Debit external account (20A)
		// transaction.setField("GZAB1", ""); // Debit account branch (4A)
		// transaction.setField("GZAN1", ""); // Debit account basic no (6A)
		// transaction.setField("GZAS1", ""); // Debit account suffix (3A)
		// transaction.setField("GZVFR1", ""); // Debit value date (7S,0)
		// transaction.setField("GZDRF1", ""); // Debit user's reference (16A)
		// transaction.setField("GZAMA1", ""); // Debit amount (15P,0)
		// transaction.setField("GZAPP1", ""); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
		// transaction.setField("GZTCD1", ""); // Debit transaction code (3A)
		// transaction.setField("GZCCY1", ""); // Debit account currency (3A)
		// transaction.setField("GZSRC1", ""); // Debit sundry reference code (2A)
		// transaction.setField("GZUC1", ""); // Debit user code 1 (3A)
		// transaction.setField("GZUC2", ""); // Debit user code 2 (3A)
		// transaction.setField("GZNR1", ""); // Debit narrative line 1 (35A)
		// transaction.setField("GZNR2", ""); // Debit narrative line 2 (35A)
		// transaction.setField("GZNR3", ""); // Debit narrative line 3 (35A)
		// transaction.setField("GZNR4", ""); // Debit narrative line 4 (35A)
		// transaction.setField("GZCED1", ""); // Debit currency edit code (1A)
		// transaction.setField("GZMCH1", ""); // Debit matched/obsolete today (1A)
		// transaction.setField("GZEANC", ""); // Credit external account (20A)
		// transaction.setField("GZAB2", ""); // Credit account branch (4A)
		// transaction.setField("GZAN2", ""); // Credit account basic no (6A)
		// transaction.setField("GZAS2", ""); // Credit account suffix (3A)
		// transaction.setField("GZVFR2", ""); // Credit value date (7S,0)
		// transaction.setField("GZDRF2", ""); // Credit user's reference (16A)
		// transaction.setField("GZAMA2", ""); // Credit amount (15P,0)
		// transaction.setField("GZAPP2", ""); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
		// transaction.setField("GZTCD2", ""); // Credit transaction code (3A)
		// transaction.setField("GZCCY2", ""); // Credit account currency (3A)
		// transaction.setField("GZSRC2", ""); // Credit sundry reference code (2A)
		// transaction.setField("GZUC3", ""); // Credit user code 1 (3A)
		// transaction.setField("GZUC4", ""); // Credit user code 2 (3A)
		// transaction.setField("GZNR5", ""); // Credit narrative line 1 (35A)
		// transaction.setField("GZNR6", ""); // Credit narrative line 2 (35A)
		// transaction.setField("GZNR7", ""); // Credit narrative line 3 (35A)
		// transaction.setField("GZNR8", ""); // Credit narrative line 4 (35A)
		// transaction.setField("GZCED2", ""); // Credit currency edit code (1A)
		// transaction.setField("GZMCH2", ""); // Credit matched/obsolete today (1A)
		// transaction.setField("GZRRT", ""); // Reserved (3A)
		// transaction.setField("GZEXR", ""); // Obsolete (14A)
		// transaction.setField("GZFONT", ""); // Fontis transfer? (1A)
		// transaction.setField("GZEXRH", ""); // Exchange rate (15P,9)
		// transaction.setField("GZBAM1", ""); // Equivalent amount in base currency - Dr (15P,0)
		// transaction.setField("GZOAM1", ""); // Equivalent amount in user currency - Dr (15P,0)
		// transaction.setField("GZBAM2", ""); // Equivalent amount in base currency - Cr (15P,0)
		// transaction.setField("GZOAM2", ""); // Equivalent amount in user currency - Cr (15P,0)
		// transaction.setField("GZIEA", ""); // Inter a/c transfer with equivalent amounts flag (1A)
		// transaction.setField("GZOID", ""); // Inter a/c transfer option id (3A)
		// transaction.setField("GZSOT", ""); // Standing order transfer? (1A)
		// transaction.setField("GZTREF", ""); // Transfer reference (16A)
		// transaction.setField("GZANMD", ""); // System generated account mnemonic (6A)
		// transaction.setField("GZGDP", ""); // Generic deal posting (1A)
		// transaction.setField("GZCRT", ""); // Court Order (1A)
		// transaction.setField("GZALL", ""); // Hold whole a/c balance (1A)
		// transaction.setField("GZHOLD", ""); // Hold on queue (1A)
		// transaction.setField("GZPDTE", ""); // Date entered (7S,0)
		// transaction.setField("GZFOR", ""); // Force to queue (1A)
		// transaction.setField("GZDRTY", ""); // Days to retry queued tran. (3A)
		// transaction.setField("GZMRTY", ""); // Months to retry queued tran. (2A)
		// transaction.setField("GZDTTY", ""); // Date of last retry of queued transaction (7S,0)
		// transaction.setField("GZQTR", ""); // Queued by (2A)
		// transaction.setField("GZQTR2", ""); // Queued again (2A)
		// transaction.setField("GZQSTS", ""); // Queue status (5A)
		// transaction.setField("GZQST2", ""); // Queue status 2 (5A)
		// transaction.setField("GZQRES", ""); // Reason for queuing (5A)
		// transaction.setField("GZQRS2", ""); // Reason for queuing 2 (5A)
		// transaction.setField("GZQNQ", ""); // Automatic queuing (1A)
		// transaction.setField("GZQMSQ", ""); // Maintenance sequence number (7P,0)
		// transaction.setField("GZQACT", ""); // Action (2A)
		// transaction.setField("GZQSCR", ""); // Display screen (1A)
		// transaction.setField("GZQUID", ""); // User ID (10A)
		// transaction.setField("GZUPTY", ""); // User priority code (5A)
		// transaction.setField("GZQBAL", ""); // Queued balance (15P,0)
		// transaction.setField("GZQDTE", ""); // Queued date (7S,0)
		// transaction.setField("GZQPTY", ""); // Priority code (5A)
		// transaction.setField("GZQRRC", ""); // Reason (3A)
		// transaction.setField("GZQAID", ""); // Assigned to user (10A)
		// transaction.setField("GZQGRP", ""); // Authorisation Group (10A)
		// transaction.setField("GZQCHK", ""); // Number of checkers (2S,0)
		// transaction.setField("GZQCHD", ""); // Times checked (2S,0)
		// transaction.setField("GZQRED", ""); // Required date (7S,0)
		// transaction.setField("GZQRET", ""); // Required time (6S,0)
		// transaction.setField("GZQBID", ""); // Batch ID (16A)
		// transaction.setField("GZQLVL", ""); // Authorisation level (2A)
		// transaction.setField("GZQEVM", ""); // Events (6A)
		// transaction.setField("GZDRR", ""); // Debit reversal transaction (1A)
		// transaction.setField("GZCRR", ""); // Credit reversal transaction (1A)
		// transaction.setField("GZDRCL", ""); // Debit amount in credit limit currency (15P,0)
		// transaction.setField("GZCRCL", ""); // Credit amount in credit limit currency (15P,0)
		// transaction.setField("GZDRO", ""); // Original debit reversal transaction (1A)
		// transaction.setField("GZCRO", ""); // Original credit reversal transaction (1A)
		// transaction.setField("GZPSQ7", ""); // Reserved (7P,0)

	}

}
