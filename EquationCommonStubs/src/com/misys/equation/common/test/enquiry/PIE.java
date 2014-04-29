package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * 
 */
public class PIE extends EquationTestCase // Pool Interest Rates Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: PIE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("O04DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZPOOL", "OST021"); // Pool (5A)
		listEnquiry.setFieldValue("HZCCY", "GBP"); // Currency mnemonic (3A)
		listEnquiry.setFieldValue("HZDTE", "1000104"); // Database date; cyymmdd (7S,0)
		/*
		 * For enquiry api the HZDAYS field needs to be populated instead of the term e.g. instead of Term = '1W', HZDAYS should = 7
		 * instead of Term = '1M', HZDAYS should = 30 etc.
		 */
		listEnquiry.setFieldValue("HZDAYS", "00007"); // Enquiry format number 5-digit (5S,0)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
