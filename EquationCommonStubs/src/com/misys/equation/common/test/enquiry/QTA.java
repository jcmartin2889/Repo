package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class QTA extends EquationTestCase // Queued Transaction Audit Summary
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: QTA.java 10242 2011-01-11 10:40:11Z challip1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("Q33DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZEVM", "QCO"); // User defined event (6A)
		listEnquiry.setFieldValue("HZQAB", "0543"); // Original queued against account branch (4A)
		listEnquiry.setFieldValue("HZQAN", "123467"); // Original queued against account number (6A)
		listEnquiry.setFieldValue("HZQAS", "086"); // Original queued against account suffix (3A)
		listEnquiry.setFieldValue("HZOAB", "0543"); // Original owning account branch (4A)
		listEnquiry.setFieldValue("HZOAN", "123467"); // Original owning account number (6A)
		listEnquiry.setFieldValue("HZOAS", "087"); // Original owning account suffix (3A)
		listEnquiry.setFieldValue("HZBRM", ""); // Deal branch (4A)
		listEnquiry.setFieldValue("HZDLP", ""); // Deal type (3A)
		listEnquiry.setFieldValue("HZDLR", ""); // Deal reference (13A)
		listEnquiry.setFieldValue("HZCMR", ""); // Commitment reference (13A)
		listEnquiry.setFieldValue("HZTRN", "QCOTEST001"); // Transaction reference (16A)
		listEnquiry.setFieldValue("HZDTE", "1000105"); // Transaction date (7S,0)
		listEnquiry.setFieldValue("HZDRF", ""); // Subsidiary reference (16A)
		listEnquiry.setFieldValue("HZTSQ", "0"); // Transaction sequence number (16P,0)
		listEnquiry.setFieldValue("HZESQ", "0"); // Event sequence number (7P,0)
		listEnquiry.setFieldValue("HZSSQ", "0"); // Slave sequence number (7P,0)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
