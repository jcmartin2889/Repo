package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class KWH extends EquationTestCase // S24c KWG Reported Aut Hldrs Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: KWH.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardEnquiry enquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		enquiry = getEquationStandardEnquiry("V16EER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		enquiry.setFieldValue("HZACC", "0543-123467-008"); // Account (20A)
		enquiry.setFieldValue("HZCCY", "USD"); // Currency mnemonic (3A)
		enquiry.setFieldValue("HZBLZ", "112233"); // Sort code (8A)
		enquiry.setFieldValue("HZPGR", "100"); // Product Group (3A)
		enquiry.setFieldValue("HZREF", "CRG01"); // Reference (12A)
		enquiry.setFieldValue("HZVDT", "1000101"); // Valid date (7S,0)
		enquiry.setFieldValue("HZVTM", "120000"); // Valid time (6S,0)
		enquiry.setFieldValue("HZANR", "00001"); // Authority holder number (5S,0)

		/*
		 * See if it works
		 */
		assertTestStandardEnquiry(enquiry, true);
	}
}
