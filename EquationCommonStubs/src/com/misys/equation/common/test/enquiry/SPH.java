package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class SPH extends EquationTestCase // Shadow Adjustment History Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SPH.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("S71DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZAB", "0543"); // Account branch (4A)
		listEnquiry.setFieldValue("HZAN", "800008"); // Basic part of account number (6A)
		listEnquiry.setFieldValue("HZAS", "001"); // Account suffix (3A)
		// listEnquiry.setField("HZDTEF", "1000101"); // Date from (7S,0)
		// listEnquiry.setField("HZDTET", "1000105"); // Date to (7S,0)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
