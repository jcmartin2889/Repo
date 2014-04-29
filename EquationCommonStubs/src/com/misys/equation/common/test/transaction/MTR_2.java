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
public class MTR_2 extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MTR_2.java 4721 2009-09-15 11:14:17Z weddelc1 $";
	// CHANGE THE PROGRAM NAME
	String programName = "C89FRR";

	// CHANGE THE OPTION ID
	String optionId = "MTR";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
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
		// REMOVE ALL THE GZ FIELDS EXCEPT THE KEY FIELDS (IN KEY SCREEN). IN THIS EXAMPLE
		// THERE IS ONLY ONE KEY FIELD

		// SETUP THE TRANSACTION CODE. IN THIS EXAMPLE, THE TRANSACTION CODE IS "011"

		transaction.setFieldValue("GZTCD", "011"); // Transaction code (3A)

	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		// REMOVE THE STANDARD JOURNAL FIELDS GZWSID, GZDIM, GZTIM, GZSEQ GZIMG
		// transaction.setField("GZWSID", ""); // Workstation id (4A)
		// transaction.setField("GZDIM", ""); // Day in month (2S,0)
		// transaction.setField("GZTIM", ""); // Time; hhmmss (6S,0)
		// transaction.setField("GZSEQ", ""); // Sequence number (7P,0)
		// transaction.setField("GZIMG", ""); // Journal image (1A)

		// REMOVE THE KEY FIELDS SETUP AB0VE
		// transaction.setField("GZTCD", ""); // Transaction code (3A)

		transaction.setFieldValue("GZTCN", "Test code"); // Transaction code name (35A)
		transaction.setFieldValue("GZDCI", "D"); // Debit/Credit (1A)
		transaction.setFieldValue("GZTCM", "DR1"); // Transaction code mnemonic (3A)
		transaction.setFieldValue("GZTC1", "001"); // Suffix range 1 low (3A)
		transaction.setFieldValue("GZTC2", "998"); // Suffix range 1 high (3A)
		transaction.setFieldValue("GZTC3", "000"); // Suffix range 2 low (3A)
		transaction.setFieldValue("GZTC4", "000"); // Suffix range 2 high (3A)
		transaction.setFieldValue("GZTCDR", "510"); // Reversal trans code (3A)
		transaction.setFieldValue("GZTCA", "00"); // Default int. days local (2P,0)
		transaction.setFieldValue("GZTCC", "07"); // Default int. days other (2P,0)
		transaction.setFieldValue("GZTAP", "N"); // Transaction advice (1A)
		transaction.setFieldValue("GZPMA", "Y"); // Valid for movement a/c (1A)
		transaction.setFieldValue("GZPTA", "N"); // Valid for term accounts (1A)
		transaction.setFieldValue("GZPEA", "N"); // Valid for exchange a/c (1A)
		transaction.setFieldValue("GZPCA", "N"); // Valid for contingent a/c (1A)
		transaction.setFieldValue("GZPIA", "Y"); // Valid for internal a/c (1A)
		transaction.setFieldValue("GZRVT", "N"); // Reversal trans code (1A)
		transaction.setFieldValue("GZDDB", "N"); // Add default days for Dr (1A)
		transaction.setFieldValue("GZPTN", "Y"); // Print trans code name (1A)
		transaction.setFieldValue("GZSUD", "N"); // Suppress update DLM (1A)
		transaction.setFieldValue("GZSTR", "N"); // Turnover stats reversal (1A)
		transaction.setFieldValue("GZDFA", "N"); // Debit if funds available (1A)
		transaction.setFieldValue("GZBTR", "N"); // Bulking allowed (1A)
		transaction.setFieldValue("GZCTC", "N"); // Stopped cheque validation (1A)
		transaction.setFieldValue("GZMID", "N"); // Multi-item deposit (1A)
		transaction.setFieldValue("GZVXD", "N"); // Valid for EXIMBILLS deals (1A)
		transaction.setFieldValue("GZCFK", ""); // Reference parameter (5A)
		transaction.setFieldValue("GZTCE", ""); // Electronic message mnem (3A)

		// THESE SEEMS TO BE NOT NEEDED SO REMOVED - NOTE I DID NOT INVESTIGATE THIS
		// THIS IS JUST A SAMPLE. OPTIONAL FIELDS MUST BE INCLUDED (EVEN AS BLANK).
		// NON-RELEVANT FIELDS MUST BE REMOVED (BASICALLY, THOSE FIELDS THAT ARE NOT IN
		// SR/MVTODS MUST BE REMOVED)
		// transaction.setField("GZSWC", ""); // SWIFT confirmation reqd (1A)
		// transaction.setField("GZELB", ""); // Eligible for bonus (1A)
		// transaction.setField("GZMR7", ""); // Statement field 61 / 7 (4A)
		// transaction.setField("GZMR8", ""); // Statement field 61 / 8 (4A)
		// transaction.setField("GZMR9", ""); // Statement field 61 / 9 (4A)
		// transaction.setField("GZ861", ""); // Statement field 86/1 part 1 (4A)
		// transaction.setField("GZ862", ""); // Statement field 86/2 part 1 (4A)
		// transaction.setField("GZ863", ""); // Statement field 86/3 part 1 (4A)
		// transaction.setField("GZ864", ""); // Statement field 86/4 part 1 (4A)
		// transaction.setField("GZ865", ""); // Statement field 86/5 part 1 (4A)
		// transaction.setField("GZ866", ""); // Statement field 86/6 part 1 (4A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		// REMOVE THE STANDARD JOURNAL FIELDS GZWSID, GZDIM, GZTIM, GZSEQ GZIMG
		// transaction.setField("GZWSID", ""); // Workstation id (4A)
		// transaction.setField("GZDIM", ""); // Day in month (2S,0)
		// transaction.setField("GZTIM", ""); // Time; hhmmss (6S,0)
		// transaction.setField("GZSEQ", ""); // Sequence number (7P,0)
		// transaction.setField("GZIMG", ""); // Journal image (1A)

		// REMOVE THE KEY FIELDS SETUP AB0VE
		// transaction.setField("GZTCD", ""); // Transaction code (3A)

		transaction.setFieldValue("GZTCN", "Test code"); // Transaction code name (35A)
		transaction.setFieldValue("GZDCI", "D"); // Debit/Credit (1A)
		transaction.setFieldValue("GZTCM", "DR1"); // Transaction code mnemonic (3A)
		transaction.setFieldValue("GZTC1", "001"); // Suffix range 1 low (3A)
		transaction.setFieldValue("GZTC2", "998"); // Suffix range 1 high (3A)
		transaction.setFieldValue("GZTC3", "000"); // Suffix range 2 low (3A)
		transaction.setFieldValue("GZTC4", "000"); // Suffix range 2 high (3A)
		transaction.setFieldValue("GZTCDR", "510"); // Reversal trans code (3A)
		transaction.setFieldValue("GZTCA", "00"); // Default int. days local (2P,0)
		transaction.setFieldValue("GZTCC", "07"); // Default int. days other (2P,0)
		transaction.setFieldValue("GZTAP", "N"); // Transaction advice (1A)
		transaction.setFieldValue("GZPMA", "Y"); // Valid for movement a/c (1A)
		transaction.setFieldValue("GZPTA", "N"); // Valid for term accounts (1A)
		transaction.setFieldValue("GZPEA", "N"); // Valid for exchange a/c (1A)
		transaction.setFieldValue("GZPCA", "N"); // Valid for contingent a/c (1A)
		transaction.setFieldValue("GZPIA", "Y"); // Valid for internal a/c (1A)
		transaction.setFieldValue("GZRVT", "N"); // Reversal trans code (1A)
		transaction.setFieldValue("GZDDB", "N"); // Add default days for Dr (1A)
		transaction.setFieldValue("GZPTN", "Y"); // Print trans code name (1A)
		transaction.setFieldValue("GZSUD", "N"); // Suppress update DLM (1A)
		transaction.setFieldValue("GZSTR", "N"); // Turnover stats reversal (1A)
		transaction.setFieldValue("GZDFA", "N"); // Debit if funds available (1A)
		transaction.setFieldValue("GZBTR", "N"); // Bulking allowed (1A)
		transaction.setFieldValue("GZCTC", "N"); // Stopped cheque validation (1A)
		transaction.setFieldValue("GZMID", "N"); // Multi-item deposit (1A)
		transaction.setFieldValue("GZVXD", "N"); // Valid for EXIMBILLS deals (1A)
		transaction.setFieldValue("GZCFK", ""); // Reference parameter (5A)
		transaction.setFieldValue("GZTCE", ""); // Electronic message mnem (3A)

		// THESE SEEM NOT NEEDED SO REMOVED - NOTE I DID NOT INVESTIGATE THIS
		// THIS IS JUST A SAMPLE. OPTIONAL FIELDS MUST BE INCLUDED (EVEN AS BLANK).
		// NON-RELEVANT FIELDS MUST BE REMOVED (BASICALLY, THOSE FIELDS THAT ARE NOT IN
		// SR/MVTODS MUST BE REMOVED)
		// transaction.setField("GZSWC", ""); // SWIFT confirmation reqd (1A)
		// transaction.setField("GZELB", ""); // Eligible for bonus (1A)
		// transaction.setField("GZMR7", ""); // Statement field 61 / 7 (4A)
		// transaction.setField("GZMR8", ""); // Statement field 61 / 8 (4A)
		// transaction.setField("GZMR9", ""); // Statement field 61 / 9 (4A)
		// transaction.setField("GZ861", ""); // Statement field 86/1 part 1 (4A)
		// transaction.setField("GZ862", ""); // Statement field 86/2 part 1 (4A)
		// transaction.setField("GZ863", ""); // Statement field 86/3 part 1 (4A)
		// transaction.setField("GZ864", ""); // Statement field 86/4 part 1 (4A)
		// transaction.setField("GZ865", ""); // Statement field 86/5 part 1 (4A)
		// transaction.setField("GZ866", ""); // Statement field 86/6 part 1 (4A)
	}

}
