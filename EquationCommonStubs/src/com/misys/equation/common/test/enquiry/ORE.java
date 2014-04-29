package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * 
 */
public class ORE extends EquationTestCase // Repayments Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ORE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("D07DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZLNP", "CDN"); // Loan type (3A)
		listEnquiry.setFieldValue("HZLNR", "CD1       001"); // Loan reference (13A)
		listEnquiry.setFieldValue("HZBRNM", "KBSL"); // Branch mnemonic (4A)
		listEnquiry.setFieldValue("HZCUS", "NTHSEA"); // Customer mnemonic (6A)
		listEnquiry.setFieldValue("HZCLC", "OIL"); // Customer location (3A)
		listEnquiry.setFieldValue("HZIRP", "1"); // Include repayments (1A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
