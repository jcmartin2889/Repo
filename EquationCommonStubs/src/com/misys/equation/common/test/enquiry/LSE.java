package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * 
 */
public class LSE extends EquationTestCase // Loan Status History Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LSE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("D03DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZLNP", "SRV"); // Loan type (3A)
		listEnquiry.setFieldValue("HZLNR", "020"); // Loan reference (13A)
		listEnquiry.setFieldValue("HZBRNM", "HEAD"); // Branch mnemonic (4A)
		// listEnquiry.setField("HZCNUM", "H00001"); // Customer number (6A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
