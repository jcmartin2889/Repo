package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * 
 */
public class GDQ extends EquationTestCase // Generic Deals Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GDQ.java 8426 2010-07-29 17:32:49Z CHALLIP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("U39DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZBRNM", "ACC1"); // Branch mnemonic (4A)
		listEnquiry.setFieldValue("HZDLP", "EQ4"); // Deal type (3A)
		listEnquiry.setFieldValue("HZDLR", "TEST6"); // Deal reference (13A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
