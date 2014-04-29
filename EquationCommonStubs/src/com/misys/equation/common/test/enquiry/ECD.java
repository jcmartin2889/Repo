package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class ECD extends EquationTestCase // Direct Debit Claims Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ECD.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardEnquiry enquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		enquiry = getEquationStandardEnquiry("Q81EER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		enquiry.setFieldValue("HZCUS", "BORROW"); // Customer mnemonic (6A)
		enquiry.setFieldValue("HZCLC", "   "); // Customer location (3A)
		enquiry.setFieldValue("HZREF", "RL1-DOY2"); // Originators reference (20A)
		enquiry.setFieldValue("HZBRNM", "LOND"); // Branch mnemonic (4A)
		enquiry.setFieldValue("HZLNRZ", "RL1 RL1-DOY2"); // Edited loan reference (17A)
		enquiry.setFieldValue("HZCDTE", "0991112"); // Repayment due date (7S,0)
		enquiry.setFieldValue("HZCLTP", "C"); // Claim type (1A)
		enquiry.setFieldValue("HZCLCD", " 01"); // Claim code (3A)

		/*
		 * See if it works
		 */
		assertTestStandardEnquiry(enquiry, true);
	}
}