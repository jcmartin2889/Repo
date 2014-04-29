package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class DDM extends EquationTestCase // DD Mandates Detail Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DDM.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardEnquiry enquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		enquiry = getEquationStandardEnquiry("D20EER", "HZD203");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		enquiry.setFieldValue("HZAB", "0543"); // Account branch (4A)
		enquiry.setFieldValue("HZAN", "131313"); // Basic part of account number (6A)
		enquiry.setFieldValue("HZAS", "107"); // Account suffix (3A)

		// The internal reference from L5PF is required
		enquiry.setFieldValue("HZREF", "0543000105000001"); // Internal reference (16A)

		/*
		 * See if it works
		 */
		assertTestStandardEnquiry(enquiry, true);
	}
}