package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class SO extends EquationTestCase // Standing Order Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SO.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardEnquiry enquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		enquiry = getEquationStandardEnquiry("H44EER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		enquiry.setFieldValue("HZREF", "Y2A03"); // Standing order reference (16A)
		enquiry.setFieldValue("HZAB", "0132"); // Account branch (4A)
		enquiry.setFieldValue("HZAN", "100000"); // Basic part of account number (6A)
		enquiry.setFieldValue("HZAS", "003"); // Account suffix (3A)

		/*
		 * See if it works
		 */
		assertTestStandardEnquiry(enquiry, true);
	}
}
