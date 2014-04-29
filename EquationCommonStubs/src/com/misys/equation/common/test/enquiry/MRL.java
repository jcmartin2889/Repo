package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * 
 */
public class MRL extends EquationTestCase // Master Request List Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MRL.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("X35EER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */

		// Note HZSQNS is used to position the first record in the list
		listEnquiry.setFieldValue("HZSQNS", "0970"); // MRL sequence (4A)

		// Note HZMRLS is used to select records
		listEnquiry.setFieldValue("HZMRLS", "*"); // MRL record first 64 characters (64A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
