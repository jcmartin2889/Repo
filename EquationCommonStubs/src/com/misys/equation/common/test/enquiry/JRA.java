package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * 
 */
public class JRA extends EquationTestCase // Journal Enquiry - Current User
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: JRA.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
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
		listEnquiry.setFieldValue("HZBRNM", ""); // Input branch (4A)
		listEnquiry.setFieldValue("HZWHO", ""); // Account number, customer or nostro (15A)
		listEnquiry.setFieldValue("HZOID", "MCD"); // Option identifier (3A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
