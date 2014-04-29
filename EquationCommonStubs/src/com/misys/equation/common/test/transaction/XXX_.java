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
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author perkinj1
 */
public class XXX_ extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: XXX_.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardTransaction transaction;
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		/*
		 * Get an empty transaction class you'll need to enter the name of the transaction concatenated with the WMENU1 option id.
		 */
		transaction = getEquationStandardTransaction(EquationStandardTransaction.EDF_PGM + "XXX",
						EquationStandardTransaction.EDF_JRNF, "", 0);
	}
	public void testAdd() throws Exception
	{
		//
		transaction.setMode("J");

		//
		transaction.setFieldValue("GZIMG2", "A"); // Unique option id assigned by a user to a function (3A)
		transaction.setFieldValue("GZOID", "XXX"); // Unique option id assigned by a user to a function (3A)
		transaction.setFieldValue("GZAPP", "AP"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
		transaction.setFieldValue("GZWHO", "WHO AM I"); // Identity (15A)
		transaction.setFieldValue("GZSHN", "WHO AM I SHORT"); // Default account short name (15A)
		transaction.setFieldValue("GZJREF", "Journal REF"); // Journal reference; deal, posting, address, etc. (17A)
		transaction.setFieldValue("GZMES1", "Mes 1 "); // Message 1 (37A)
		transaction.setFieldValue("GZMES2", "Mes 2 "); // Message 2 (37A)
		transaction.setFieldValue("GZMES3", "Mes 3 "); // Message 3 (37A)
		transaction.setFieldValue("GZMES4", "Mes 4 "); // Message 4 (37A)
		transaction.setFieldValue("GZMES5", "Mes 5 "); // Message 5 (37A)
		transaction.setFieldValue("GZMES6", "Mes 6 "); // Message 6 (37A)
		transaction.setFieldValue("GZMES7", "Mes 7 "); // Message 7 (37A)
		transaction.setFieldValue("GZMES8", "Mes 8 "); // Message 8 (37A)
		transaction.setAext("N");
		transaction.setArec("N");
		// transaction.setField("GZAEXT", "N"); // Apply During External Input? (1A)
		// transaction.setField("GZAREC", "N"); // Apply During Recovery? (1A)

		/*
		 * See if it works
		 */
		assertTestStandardTransaction(transaction, true);
	}
}
