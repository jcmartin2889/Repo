package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class POE extends EquationTestCase // Postings Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: POE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("P02DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZDLP", "DDD"); // Deal type (3A)
		listEnquiry.setFieldValue("HZDLR", "208"); // Deal reference (13A)
		listEnquiry.setFieldValue("HZBRNM", "LOND"); // Branch mnemonic (4A)
		listEnquiry.setFieldValue("HZPOD1", "0991231"); // Posting date (7S,0)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
