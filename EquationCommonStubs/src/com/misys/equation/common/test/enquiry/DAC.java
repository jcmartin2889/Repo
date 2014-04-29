package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class DAC extends EquationTestCase // Deal Amortised Cost Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DAC.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardEnquiry enquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		enquiry = getEquationStandardEnquiry("I93EER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		enquiry.setFieldValue("HZBRNM", "KBSL"); // Branch (4A)
		enquiry.setFieldValue("HZDLP", "CDF"); // Deal type (3A)
		enquiry.setFieldValue("HZDLR", "MD1       400"); // Deal reference (13A)

		/*
		 * See if it works
		 */
		assertTestStandardEnquiry(enquiry, true);
	}
}
