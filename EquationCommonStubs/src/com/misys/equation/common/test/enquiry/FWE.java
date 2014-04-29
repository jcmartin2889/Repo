package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class FWE extends EquationTestCase // Free Withdrawal Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FWE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardEnquiry enquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		enquiry = getEquationStandardEnquiry("B18EER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		enquiry.setFieldValue("HZAB", "0543"); // Account branch (4A)
		enquiry.setFieldValue("HZAN", "444444"); // Basic part of account number (6A)
		enquiry.setFieldValue("HZAS", "000"); // Account suffix (3A)
		enquiry.setFieldValue("HZEDT", "1000105"); // Effective from date (7S,0)

		enquiry.setFieldValue("HZACT", "NE"); // Account type (2A)
		enquiry.setFieldValue("HZCCY", "USD"); // Currency mnemonic (3A)

		/*
		 * See if it works
		 */
		assertTestStandardEnquiry(enquiry, true);
	}
}