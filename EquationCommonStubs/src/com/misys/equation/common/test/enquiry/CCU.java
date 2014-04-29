package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * 
 */
public class CCU extends EquationTestCase // Clean Payments Events Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CCU.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("K60DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZREF", "CITY00103H000020"); // Payment reference (16A)
		listEnquiry.setFieldValue("HZAPP", "CP"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
		listEnquiry.setFieldValue("HZPYT", "3CN"); // Payment type (3A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
