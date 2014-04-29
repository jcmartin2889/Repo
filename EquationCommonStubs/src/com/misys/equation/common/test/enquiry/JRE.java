package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * 
 */
public class JRE extends EquationTestCase // Journal Enquiry - All Users
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: JRE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("Q23DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZBRNM", "LOND"); // Input branch (4A)
		listEnquiry.setFieldValue("HZWHO", "0543-123467-008"); // Account number, customer or nostro (15A)
		listEnquiry.setFieldValue("HZOID", "MDC"); // Option identifier (3A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
