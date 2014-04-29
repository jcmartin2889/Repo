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
 * @author weddelc1
 */
public class ACG_2 extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACG_2.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardTransaction transaction;
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		/*
		 * Get an empty transaction class you'll need to enter the name of the transaction concatenated with the WMENU1 option id.
		 */
		transaction = getEquationStandardTransaction("C62ARRACG", 1000);
	}
	public void testAdd() throws Exception
	{
		/*
		 * Set the transaction type
		 */
		transaction.setMode("A");
		/*
		 * Set the transaction fields
		 */
		/*
		 * Set the transaction fields
		 */
		transaction.setFieldValue("GZDLP", "CR2");
		transaction.setFieldValue("GZDLR", "SADFA");
		transaction.setFieldValue("GZDBRM", "LOND");
		transaction.setFieldValue("GZCMR", "");
		transaction.setFieldValue("GZAB", "");
		transaction.setFieldValue("GZAN", "");
		transaction.setFieldValue("GZAS", "");

		transaction.setFieldValue("GZEVNM", "RLA");
		transaction.setFieldValue("GZTREF", "");
		transaction.setFieldValue("GZCAB", "");
		transaction.setFieldValue("GZCAN", "");
		transaction.setFieldValue("GZCAS", "");
		transaction.setFieldValue("GZDDTD", "1000229");
		transaction.setFieldValue("GZAREF", "");
		transaction.setFieldValue("GZESQN", "000");

		transaction.setFieldValue("GZDCHG", "N");
		transaction.setFieldValue("GZFLAG", "A");
		transaction.setFieldValue("GZVAL", "1000229");
		transaction.setFieldValue("GZUSER", "LOND");

		transaction.setGSFieldValue("GSECNM", "RLA002"); // fixed rated
		transaction.setGSFieldValue("GSCHGC", "");
		transaction.setGSFieldValue("GSCHA", "200");

		transaction.setGSFieldValue("GSSDT", "991231");
		transaction.setGSFieldValue("GSEND", "991231");
		transaction.setGSFieldValue("GSFRQ", "");

		transaction.setGSFieldValue("GSNA1", "Calculationnarrative1");
		transaction.setGSFieldValue("GSNA2", "Calculationnarrative2");
		transaction.setGSFieldValue("GSNA3", "Calculationnarrative3");
		transaction.setGSFieldValue("GSNA4", "Calculationnarrative4");

		transaction.setGSFieldValue("GSWAMD", "100");
		transaction.setGSFieldValue("GSMIND", "");
		transaction.setGSFieldValue("GSMAXD", "");

		transaction.setGSFieldValue("GSFAB", "9132");
		transaction.setGSFieldValue("GSFAN", "234567");
		transaction.setGSFieldValue("GSFAS", "001");

		transaction.setGSFieldValue("GSCAP", "N");

		transaction.setGSFieldValue("GSNP1", "Postingnarrative1");
		transaction.setGSFieldValue("GSNP2", "Postingnarrative2");
		transaction.setGSFieldValue("GSNP3", "Postingnarrative3");
		transaction.setGSFieldValue("GSNP4", "Postingnarrative4");
		transaction.setGSFieldValue("GSUC1", "Usercode1");
		transaction.setGSFieldValue("GSUC2", "Usercode2");
		transaction.setGSFieldValue("GSTAX", "");
		transaction.setGSFieldValue("GSFTF", "N");
		/*
		 * See if it works
		 */
		assertTestStandardTransaction(transaction, true);
	}
}
