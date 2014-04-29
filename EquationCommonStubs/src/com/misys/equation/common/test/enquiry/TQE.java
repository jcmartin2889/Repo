package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class TQE extends EquationTestCase // Transaction Queue Manager Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TQE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("Q32DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZQTR", "01"); // Queued by (2A)
		listEnquiry.setFieldValue("HZQAB", "0543"); // Queued against account branch (4A)
		listEnquiry.setFieldValue("HZQAN", "123467"); // Queued against account number (6A)
		listEnquiry.setFieldValue("HZQAS", "086"); // Queued against account suffix (3A)
		listEnquiry.setFieldValue("HZCTRN", "Y"); // Include completed transactions (1A)
		listEnquiry.setFieldValue("HZTRNE", "N"); // Only show transactions in error (1A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
