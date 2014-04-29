package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * 
 */
public class SA extends EquationTestCase // Stop Order Amount Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SA.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("H41DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZCCY", "GBP"); // Currency mnemonic (3A)
		// Note it is necessary to pad the amount with leading zeros
		listEnquiry.setFieldValue("HZAMT", "000000003750000"); // Stop order amount (15A)
		listEnquiry.setFieldValue("HZAB", "3210"); // Account branch (4A)
		listEnquiry.setFieldValue("HZAN", "100000"); // Basic part of account number (6A)
		listEnquiry.setFieldValue("HZAS", "001"); // Account suffix (3A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
