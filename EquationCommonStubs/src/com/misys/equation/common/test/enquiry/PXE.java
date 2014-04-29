package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * 
 */
public class PXE extends EquationTestCase // Pool Exchange Rates Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: PXE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("O15DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZPOOL", "OSX01"); // Pool (5A)
		listEnquiry.setFieldValue("HZCCY", "GBP"); // Currency mnemonic (3A)
		listEnquiry.setFieldValue("HZDTE", "1000105"); // Start date (7S,0)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
