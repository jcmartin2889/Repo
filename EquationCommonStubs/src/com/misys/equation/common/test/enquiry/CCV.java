package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class CCV extends EquationTestCase // Clean Payments Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CCV.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardEnquiry enquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		enquiry = getEquationStandardEnquiry("K61EER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		enquiry.setFieldValue("HZREF", "CITY00103H000020"); // Payment reference (16A)

		/*
		 * See if it works
		 */
		assertTestStandardEnquiry(enquiry, true);
	}
}