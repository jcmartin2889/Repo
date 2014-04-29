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
 * Equation test cases for Add Model Security
 */
public class CSM extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CSM.java 4721 2009-09-15 11:14:17Z weddelc1 $";
	String programName = "M20ARR";
	String optionId = "CSM";

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
		transaction.setFieldValue("GZSMN", "CARL"); // Security mnemonic
		transaction.setFieldValue("GZBSP", ""); // Base on security
	}

	/**
	 * Setup the mandatory fields
	 */
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZACE", "N"); // Acceptance code
		transaction.setFieldValue("GZTMD", "N"); // Tradeable model
		transaction.setFieldValue("GZIBS", "N"); // Interest bearing security
		transaction.setFieldValue("GZISS", "N"); // Own issue
		transaction.setFieldValue("GZIPR", "1"); // Issue price per 100
		transaction.setFieldValue("GZNMP", "1"); // Notional maturity price per 100
		transaction.setFieldValue("GZDG3D", "Guarantor/Drawer"); // Drawer/guarantor 3 description
		transaction.setFieldValue("GZEG1D", "Guarantor/Endorser"); // Endorser 1/guarantor 1 description
		transaction.setFieldValue("GZEG2D", "Guarantor/Endorser"); // Endorser 2/guarantor 2 description
		transaction.setFieldValue("GZIAPD", "Issuer/Acceptor"); // Issuer/acceptor/promissor description
		transaction.setFieldValue("GZUS1D", "User Code 1"); // User reference 1 description
		transaction.setFieldValue("GZUS2D", "User Code 2"); // User reference 2 description
		transaction.setFieldValue("GZUS3D", "User Code 3"); // User reference 3 description
		transaction.setFieldValue("GZUS4D", "User Code 4"); // User reference 4 description
		transaction.setFieldValue("GZUS5D", "User Code 5"); // User reference 5 description
		transaction.setFieldValue("GZIDTC", "N"); // Security field characteristic 1
		transaction.setFieldValue("GZMDTC", "P"); // Security field characteristic 2
		transaction.setFieldValue("GZNMPC", "P"); // Security field characteristic 3
		transaction.setFieldValue("GZRGBC", "P"); // Security field characteristic 4
		transaction.setFieldValue("GZPINC", "M"); // Security field characteristic 5
		transaction.setFieldValue("GZCFPC", "M"); // Security field characteristic 6
		transaction.setFieldValue("GZCLIC", "P"); // Security field characteristic 7
		transaction.setFieldValue("GZFORC", "M"); // Security field characteristic 8
		transaction.setFieldValue("GZDFDC", "P"); // Security field characteristic 9
		transaction.setFieldValue("GZBCIC", "P"); // Security field characteristic 10
		transaction.setFieldValue("GZIDBC", "N"); // Security field characteristic 11
		transaction.setFieldValue("GZCPRC", "N"); // Security field characteristic 12
		transaction.setFieldValue("GZBRTC", "N"); // Security field characteristic 13
		transaction.setFieldValue("GZBRCC", "N"); // Security field characteristic 14
		transaction.setFieldValue("GZCSRC", "N"); // Security field characteristic 15
		transaction.setFieldValue("GZCFQC", "N"); // Security field characteristic 16
		transaction.setFieldValue("GZCDBC", "N"); // Security field characteristic 17
		transaction.setFieldValue("GZBDMC", "N"); // Security field characteristic 18
		transaction.setFieldValue("GZADCC", "N"); // Security field characteristic 19
		transaction.setFieldValue("GZLCDC", "N"); // Security field characteristic 20
		transaction.setFieldValue("GZNCDC", "N"); // Security field characteristic 21
		transaction.setFieldValue("GZRFQC", "N"); // Security field characteristic 22
		transaction.setFieldValue("GZLREC", "N"); // Security field characteristic 23
		transaction.setFieldValue("GZITFC", "N"); // Security field characteristic 24
		transaction.setFieldValue("GZECPC", "N"); // Security field characteristic 25
		transaction.setFieldValue("GZBCDC", "N"); // Security field characteristic 26
		transaction.setFieldValue("GZNRDC", "N"); // Security field characteristic 27
		transaction.setFieldValue("GZNCRC", "N"); // Security field characteristic 28
		transaction.setFieldValue("GZNBRC", "N"); // Security field characteristic 29
		transaction.setFieldValue("GZNSRC", "N"); // Security field characteristic 30
		transaction.setFieldValue("GZNCFC", "N"); // Security field characteristic 31
		transaction.setFieldValue("GZNRFC", "N"); // Security field characteristic 32
		transaction.setFieldValue("GZNCCC", "N"); // Security field characteristic 33
		transaction.setFieldValue("GZCXRC", "N"); // Security field characteristic 34
		transaction.setFieldValue("GZDCPC", "N"); // Security field characteristic 35
		transaction.setFieldValue("GZIANC", "N"); // Security field characteristic 36
		transaction.setFieldValue("GZIAPC", "N"); // Security field characteristic 37
		transaction.setFieldValue("GZE1NC", "N"); // Security field characteristic 38
		transaction.setFieldValue("GZEG1C", "N"); // Security field characteristic 39
		transaction.setFieldValue("GZE2NC", "N"); // Security field characteristic 40
		transaction.setFieldValue("GZEG2C", "N"); // Security field characteristic 41
		transaction.setFieldValue("GZD3NC", "N"); // Security field characteristic 42
		transaction.setFieldValue("GZDG3C", "N"); // Security field characteristic 43
		transaction.setFieldValue("GZSOCC", "N"); // Security field characteristic 44
		transaction.setFieldValue("GZLGCC", "N"); // Security field characteristic 45
		transaction.setFieldValue("GZUS1C", "N"); // Security field characteristic 46
		transaction.setFieldValue("GZUS2C", "N"); // Security field characteristic 47
		transaction.setFieldValue("GZUS3C", "N"); // Security field characteristic 48
		transaction.setFieldValue("GZUS4C", "N"); // Security field characteristic 49
		transaction.setFieldValue("GZUS5C", "N"); // Security field characteristic 50
		transaction.setFieldValue("GZIPRC", "P"); // Security field characteristic 51
		transaction.setFieldValue("GZDN1C", "N"); // Security field characteristic 52
		transaction.setFieldValue("GZDN2C", "N"); // Security field characteristic 53
		transaction.setFieldValue("GZISZC", "N"); // Security field characteristic 54
		transaction.setFieldValue("GZIAOC", "N"); // Security field characteristic 55
		transaction.setFieldValue("GZTPRC", "N"); // Security field characteristic 56
		transaction.setFieldValue("GZFTDC", "N"); // Security field characteristic 57
		transaction.setFieldValue("GZMM1C", "N"); // Security field characteristic 58
		transaction.setFieldValue("GZMM2C", "N"); // Security field characteristic 59
		transaction.setFieldValue("GZSTPC", "N"); // Security field characteristic 60
		transaction.setFieldValue("GZMDRC", "N"); // Security field characteristic 61
		transaction.setFieldValue("GZINRC", "N"); // Security field characteristic 62
		transaction.setFieldValue("GZSELC", "N"); // Security field characteristic 63
	}

}
