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
 * Equation test cases for Add/Maintain Payment Type
 */
public class DPT extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DPT.java 4721 2009-09-15 11:14:17Z weddelc1 $";
	String programName = "K50FRR";
	String optionId = "DPT";

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
		transaction.setFieldValue("GZPYT", "AAB");// Payment type
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZPYTD", "Description"); // Payment type description
		transaction.setFieldValue("GZIO", "1"); // Inward tfr=1, Outward tfr=2
		transaction.setFieldValue("GZMT", "1"); // Customer tfr=1, Bank tfr=2
		transaction.setFieldValue("GZXCP", "N"); // Cross currency payment?
		transaction.setFieldValue("GZRVP", "Y"); // Review payment?
		transaction.setFieldValue("GZRPDU", "Y"); // Review payment by different user?
		transaction.setFieldValue("GZAUP", "Y"); // Authorise payment?
		transaction.setFieldValue("GZAPDU", "Y"); // Auth payment by different user?
		transaction.setFieldValue("GZNOAU", "1"); // Number of authorisers
		transaction.setFieldValue("GZPRAS", "N"); // Allow same receive & pay accounts?
		transaction.setFieldValue("GZUVEA", "N"); // Use via Eq API only?
		//
		transaction.setFieldValue("GZRDCO", "N"); // Receive date cut-off?
		transaction.setFieldValue("GZRFCO", "N"); // Force receive cut-off?
		transaction.setFieldValue("GZPDCO", "N"); // Pay date cut-off?
		transaction.setFieldValue("GZPFCO", "N"); // Force pay cut-off?
		transaction.setFieldValue("GZACR", "Y"); // Accounting required?
		transaction.setFieldValue("GZLOG", "CANCNFA/C"); // Issue payment - log
		//
		transaction.setFieldValue("GZPYFN", "2"); // Payment funded
		transaction.setFieldValue("GZRBID", "N"); // Identification?
		transaction.setFieldValue("GZPYPS", "N"); // Payment purpose?
		transaction.setFieldValue("GZBFAD", "N"); // Beneficiary advice?
		transaction.setFieldValue("GZPMC", "Y"); // Payment confirmation?
		transaction.setFieldValue("GZPCC", "Y"); // Cancel confirmation?
		transaction.setFieldValue("GZNTR", "N"); // Notice to receive?
		transaction.setFieldValue("GZREF", "Y"); // Generate reference
		transaction.setFieldValue("GZREFC", "Y"); // Generate cheque number
		transaction.setFieldValue("GZCTT", "1"); // Customer transfer type
		//
		transaction.setFieldValue("GZSA", "Y"); // Maintain entered amount?
		transaction.setFieldValue("GZEDR", "Y"); // Maintain deal reference?
		transaction.setFieldValue("GZER", "Y"); // Maintain exchange rate - Y/N
		transaction.setFieldValue("GZVC", "Y"); // Maintain charges?
		transaction.setFieldValue("GZVP", "Y"); // Maintain postings?
		transaction.setFieldValue("GZPC", "Y"); // Maintain payment conf?
		transaction.setFieldValue("GZXR", "Y"); // Maintain external reference?
		transaction.setFieldValue("GZBA", "Y"); // Maintain beneficiary advice?
		transaction.setFieldValue("GZRI", "Y"); // Maintain remitter id?
		transaction.setFieldValue("GZNSR", "Y"); // Maintain pay account details?
		transaction.setFieldValue("GZVDR", "Y"); // Maintain pay value date?
		transaction.setFieldValue("GZCTTR", "Y"); // Maintain Customer Transfer Type
		transaction.setFieldValue("GZDPP", "Y"); // Maintain details of payment?
		transaction.setFieldValue("GZPPP", "Y"); // Maintain payment purpose?
		transaction.setFieldValue("GZBYP", "Y"); // Maintain beneficiary?
		transaction.setFieldValue("GZAIP", "Y"); // Maintain account with inst?
		transaction.setFieldValue("GZNSP", "Y"); // Maintain receive account details?
		transaction.setFieldValue("GZVDP", "Y"); // Maintain receive value date?
		transaction.setFieldValue("GZNRR", "Y"); // Maintain notice to receive?
		transaction.setFieldValue("GZOIR", "Y"); // Maintain ordering inst?
		transaction.setFieldValue("GZOCR", "Y"); // Maintain ordering customer?
		transaction.setFieldValue("GZSRR", "Y"); // Maintain sender?
		transaction.setFieldValue("GZSNR", "Y"); // Maintain receive settlement nar?
		transaction.setFieldValue("GZRRP", "Y"); // Maintain receiver?
		transaction.setFieldValue("GZSCP", "Y"); // Maintain senders correspondent?
		transaction.setFieldValue("GZIYP", "Y"); // Maintain intermediary?
		transaction.setFieldValue("GZRCP", "Y"); // Maintain receivers correspondent?
		transaction.setFieldValue("GZTRIR", "Y"); // Maintain third reimbursement institution?
		transaction.setFieldValue("GZSRP", "Y"); // Maintain sender to receiver info?
		transaction.setFieldValue("GZSNP", "Y"); // Maintain pay settlement nar?
		transaction.setFieldValue("GZCFP", "Y");
		//
		transaction.setFieldValue("GZTINR", "Y"); // Maintain time indications?
		transaction.setFieldValue("GZBOPR", "Y"); // Maintain bank operation code?
		transaction.setFieldValue("GZINCR", "Y"); // Maintain instruction codes?
		transaction.setFieldValue("GZTTCR", "Y"); // Maintain transaction type code?
		transaction.setFieldValue("GZICAR", "Y"); // Maintain instructed currency/amount?
		transaction.setFieldValue("GZIARR", "Y"); // Maintain instructed amount exchange rate?
		transaction.setFieldValue("GZSCHR", "Y"); // Maintain sender's charges?
		transaction.setFieldValue("GZRCHR", "Y"); // Maintain receiver's charges?
		transaction.setFieldValue("GZREGR", "Y"); // Maintain regulatory reporting?
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZPYTD", "Changed Description"); // Payment type description
	}

}
