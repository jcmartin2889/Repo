package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class HPC extends EquationTestCase // Hypothetical Charge Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: HPC.java 8426 2010-07-29 17:32:49Z CHALLIP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("U64DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZEVNT", "RLA   "); // Event mnemonic (6A)
		listEnquiry.setFieldValue("HZCCY", "GBP"); // Currency (3A)
		// listEnquiry.setFieldValue("HZAB", "0543"); // Account branch (4A)
		// listEnquiry.setFieldValue("HZAN", "123467"); // Basic part of account number (6A)
		// listEnquiry.setFieldValue("HZAS", "006"); // Account suffix (3A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
