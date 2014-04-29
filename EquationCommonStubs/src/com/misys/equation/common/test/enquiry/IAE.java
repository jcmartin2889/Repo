package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * 
 */
public class IAE extends EquationTestCase // Customer profit enquiry extract
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: IAE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("N62DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZCUS", "ATLANT"); // Customer mnemonic (6A)
		listEnquiry.setFieldValue("HZCLC", "IND"); // Customer location (3A)
		listEnquiry.setFieldValue("HZPSTD", "0990201"); // Period start date (7S,0)
		listEnquiry.setFieldValue("HZPETD", "1000101"); // Period end date (7S,0)
		listEnquiry.setFieldValue("HZACO", "TF "); // Account officer (3A)
		listEnquiry.setFieldValue("HZCCY", "USD"); // Reporting currency (3A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
