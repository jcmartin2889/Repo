package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class ETX extends EquationTestCase // Tax Threshold & Utilisation Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ETX.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("V32DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZFAR", "222222"); // FA reference (16A)
		listEnquiry.setFieldValue("HZIRFNO", "222222"); // Reference number (18A)
		listEnquiry.setFieldValue("HZISDT", "1000101"); // Start date (7S,0)
		listEnquiry.setFieldValue("HZIEDT", "1010102"); // Expiry date (7S,0)
		listEnquiry.setFieldValue("HZISTS", "A"); // Threshold status (1A)
		listEnquiry.setFieldValue("HZIFAS", "N"); // FA? (1A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}