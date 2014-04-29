package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * 
 */
public class CGE extends EquationTestCase // Account Charges Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CGE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("U53DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZAB", "0543"); // Account branch (4A)
		listEnquiry.setFieldValue("HZAN", "123467"); // Basic part of account number (6A)
		listEnquiry.setFieldValue("HZAS", "009"); // Account suffix (3A)
		// The following fields would normally be defaulted in U53DMR
		listEnquiry.setFieldValue("HZYNI", "N"); // Include receivable charges? (1A)
		listEnquiry.setFieldValue("HZFDT", "0991230"); // Charge start date (7S,0)
		listEnquiry.setFieldValue("HZTDT", "1000105"); // Charge to date (7S,0)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
