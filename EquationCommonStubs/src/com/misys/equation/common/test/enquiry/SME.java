package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class SME extends EquationTestCase // Stock Movement Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SME.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("S13DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZSIT", "HERR"); // Stock item type (4A)
		listEnquiry.setFieldValue("HZCCY", "GBP"); // Currency (3A)

		// The following fields are used as selection fields,
		// so if they are not specified they should be set to "*".
		listEnquiry.setFieldValue("HZSTSE", "*"); // Status (1A)
		listEnquiry.setFieldValue("HZADTE", "*"); // Selection field Start date (7A)
		listEnquiry.setFieldValue("HZASRC", "*"); // Selection field Source branch/issuer (15A)
		listEnquiry.setFieldValue("HZASRF", "*"); // Selection field Source reference (35A)
		listEnquiry.setFieldValue("HZATRG", "*"); // Selection field Target branch/issuer (15A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
