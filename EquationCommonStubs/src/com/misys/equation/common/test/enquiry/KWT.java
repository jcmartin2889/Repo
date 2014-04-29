package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class KWT extends EquationTestCase // S24c KWG Transmission Files Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: KWT.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("V12DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZFNRFZ", "848"); // From trans. file number (8P,0)
		listEnquiry.setFieldValue("HZFNRTZ", "848"); // To trans. file number (8P,0)
		listEnquiry.setFieldValue("HZDATF", "0990101"); // From creation date (7S,0)
		listEnquiry.setFieldValue("HZDATT", "1011231"); // To creation date (7S,0)
		listEnquiry.setFieldValue("HZYNI", "N"); // Show Rejections Only (1A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}