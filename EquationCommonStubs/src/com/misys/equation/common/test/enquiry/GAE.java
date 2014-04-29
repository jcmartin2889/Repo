package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * 
 */
public class GAE extends EquationTestCase // Enhanced API Meta Data Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GAE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("T69DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZAID", "AAE"); // API Identifier (10A)
		listEnquiry.setFieldValue("HZDOC", "N"); // Retreive documentation (1A)
		listEnquiry.setFieldValue("HZROOT", "H60E"); // Function root (5A)
		listEnquiry.setFieldValue("HZFID", "AAE"); // Function ID (3A)
		listEnquiry.setFieldValue("HZATYP", "003"); // API Type (3A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
