package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class LPE extends EquationTestCase // Loan Pay-off Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LPE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardEnquiry enquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		enquiry = getEquationStandardEnquiry("R86EER");
	}

	public void testRetrieve() throws Exception
	{
		// Set up the key fields required for the enquiry

		enquiry.setFieldValue("HZLNP", "COA"); // Deal type (3A)
		enquiry.setFieldValue("HZLNR", "COA 001"); // Deal reference (13A)
		enquiry.setFieldValue("HZBRNM", "MKTI"); // Branch mnemonic (4A)
		enquiry.setFieldValue("HZCUS", "ATLANT"); // Customer mnemonic (6A)
		enquiry.setFieldValue("HZCLC", "IND"); // Customer location (3A)
		enquiry.setFieldValue("HZPDTE", "1000105"); // Payoff date (7S,0)

		// See if it works
		assertTestStandardEnquiry(enquiry, true);
	}
}
