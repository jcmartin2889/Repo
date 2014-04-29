package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * 
 */
public class KC1 extends EquationTestCase // Control System Tailoring Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("H61DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZTYP", "2"); // Variable type (1A)
		listEnquiry.setFieldValue("HZSGP", ""); // System variable group (2A)
		listEnquiry.setFieldValue("HZSGP1", ""); // System variable sub-group (1A)
		listEnquiry.setFieldValue("HZFLN", ""); // Field name (6A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
