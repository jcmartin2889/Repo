package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * 
 */
public class ECE extends EquationTestCase // External Cashflows Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ECE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("B40DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZDLP", "GD1"); // Account branch (4A)
		listEnquiry.setFieldValue("HZDLR", "SPLAT1"); // Basic part of account number (6A)
		listEnquiry.setFieldValue("HZBRNM", "KBSL"); // Account suffix (3A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}