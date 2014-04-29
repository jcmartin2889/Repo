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
 * Equation test cases for Group Limits Maintenance
 * */
public class GLM_Part1 extends EquationTestCaseAddMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GLM_Part1.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G29FRR";
	String optionId = "GLM";

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
		transaction.setFieldValue("GZGRP", "P1479"); // Group name (6A)
		transaction.setFieldValue("GZBRNM", ""); // Branch mnemonic (4A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{

		transaction.setFieldValue("GZLED", "1000104"); // Limit expiry date (7S,0)
		transaction.setFieldValue("GZXDL", "10000000000"); // FX daily settlement limits (15P,0)
		transaction.setFieldValue("GZXTL", "10000000000"); // FX trading limits (15P,0)
		transaction.setFieldValue("GZMPL", "10000000000"); // MM placement limit (15P,0)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZLED", "1000106"); // Limit expiry date (7S,0)
		transaction.setFieldValue("GZXDL", "20000000000"); // FX daily settlement limits (15P,0)
		transaction.setFieldValue("GZXTL", "20000000000"); // FX trading limits (15P,0)
		transaction.setFieldValue("GZMPL", "20000000000"); // MM placement limit (15P,0)
	}

}
