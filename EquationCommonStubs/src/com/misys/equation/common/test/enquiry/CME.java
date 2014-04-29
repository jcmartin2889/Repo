package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * 
 */
public class CME extends EquationTestCase // Commitment Charges Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CME.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 * 
		 * This enquiry uses HZU051 and HZU052 (not HZU571 and HZU572)
		 */
		listEnquiry = getEquationStandardListEnquiry("U57DER", "HZU051", "HZU052");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZCMR", "COMCHG600 "); // Commitment reference (13A)
		// listEnquiry.setField("HZCHRG", "N"); // Held in fees receivable? (1A)
		// listEnquiry.setField("HZCGRP", "N"); // Summarised? (1A)
		listEnquiry.setFieldValue("HZSDT", "0991231"); // Date (7S,0)
		listEnquiry.setFieldValue("HZTDT", "1000105"); // Date (7S,0)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
