package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * 
 */
public class DCE extends EquationTestCase // Deal Charges Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DCE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("I80DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZDLP", "RT1"); // Deal type (3A)
		listEnquiry.setFieldValue("HZDLR", "RDU-TODAY-3-1"); // Deal reference (13A)
		listEnquiry.setFieldValue("HZBRNM", "HEAD"); // Branch mnemonic (4A)
		listEnquiry.setFieldValue("HZIRC", "N"); // Include receivable charges (1A)
		listEnquiry.setFieldValue("HZFDT", "0991231"); // Show charges from date (7S,0)
		listEnquiry.setFieldValue("HZTDT", "1000105"); // Show charges to date (7S,0)
		// listEnquiry.setField("HZSBC", "N"); // Summarised by charge group (1A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
