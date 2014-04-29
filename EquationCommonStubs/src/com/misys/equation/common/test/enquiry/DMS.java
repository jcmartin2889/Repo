// Data correct ; not returning correct result

package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * 
 */
public class DMS extends EquationTestCase // DD Mandates Summary Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DMS.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("D20DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZAB", "0000"); // Account branch (4A)
		listEnquiry.setFieldValue("HZAN", "000001"); // Basic part of account number (6A)
		listEnquiry.setFieldValue("HZAS", "001"); // Account suffix (3A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
