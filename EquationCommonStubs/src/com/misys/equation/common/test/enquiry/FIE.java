package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class FIE extends EquationTestCase // Free Interest Withdrawal Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FIE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("B21DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZAB", "0543"); // Account branch (4A)
		listEnquiry.setFieldValue("HZAN", "444444"); // Basic part of account number (6A)
		listEnquiry.setFieldValue("HZAS", "000"); // Account suffix (3A)

		// May also be required
		// listEnquiry.setField("HZEDT", "1000105"); // Effective from date (7S,0)
		// listEnquiry.setField("HZACT", "NE"); // Account type (2A)
		// listEnquiry.setField("HZCCY", "GBP"); // Currency mnemonic (3A)
		// listEnquiry.setField("HZCTP", "  "); // Customer type (2A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}