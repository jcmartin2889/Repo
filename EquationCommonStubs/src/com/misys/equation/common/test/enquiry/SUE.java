package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class SUE extends EquationTestCase // UII Status Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SUE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardEnquiry enquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		enquiry = getEquationStandardEnquiry("W83EER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		enquiry.setFieldValue("HZSIT", "ST01"); // Stock item type (4A)
		enquiry.setFieldValue("HZUII", "1234567890"); // UII number (50A)
		enquiry.setFieldValue("HZBRF", "BATCH001"); // Batch reference (20A)
		enquiry.setFieldValue("HZAB", "0000"); // Account branch (4A)
		enquiry.setFieldValue("HZAN", "000001"); // Account basic number (6A)
		enquiry.setFieldValue("HZAS", "001"); // Account suffix (3A)

		/*
		 * See if it works
		 */
		assertTestStandardEnquiry(enquiry, true);
	}
}
