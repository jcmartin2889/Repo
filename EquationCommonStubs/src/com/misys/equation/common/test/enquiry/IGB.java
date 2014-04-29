package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class IGB extends EquationTestCase // Group profit category enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: IGB.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("N69DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZGRP", "ATLANT"); // Group name (6A)
		listEnquiry.setFieldValue("HZACO", "   "); // A/c officer (3A)
		listEnquiry.setFieldValue("HZCAT", "RS018"); // Profit category (5A)
		listEnquiry.setFieldValue("HZPSTD", "0990101"); // Period start date (7S,0)
		listEnquiry.setFieldValue("HZPETD", "1050101"); // Period end date (7S,0)
		listEnquiry.setFieldValue("HZPRT", "1"); // Profit type (1A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
