package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class QTE extends EquationTestCase // Queued Transaction Audit Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: QTE.java 10243 2011-01-11 10:40:35Z challip1 $";
	private EquationStandardEnquiry enquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		enquiry = getEquationStandardEnquiry("Q33EER", "HZQ333");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		enquiry.setFieldValue("HZEVM", "QCO"); // User defined event (6A)
		enquiry.setFieldValue("HZQAB", "0543"); // Original queued against account branch (4A)
		enquiry.setFieldValue("HZQAN", "123467"); // Original queued against account number (6A)
		enquiry.setFieldValue("HZQAS", "086"); // Original queued against account suffix (3A)
		enquiry.setFieldValue("HZOAB", "0543"); // Original owning account branch (4A)
		enquiry.setFieldValue("HZOAN", "123467"); // Original owning account number (6A)
		enquiry.setFieldValue("HZOAS", "087"); // Original owning account suffix (3A)
		enquiry.setFieldValue("HZBRM", ""); // Deal branch (4A)
		enquiry.setFieldValue("HZDLP", ""); // Deal type (3A)
		enquiry.setFieldValue("HZDLR", ""); // Deal reference (13A)
		enquiry.setFieldValue("HZCMR", ""); // Commitment reference (13A)
		enquiry.setFieldValue("HZTRN", "QCOTEST001"); // Transaction reference (16A)
		enquiry.setFieldValue("HZDTE", "1000105"); // Transaction date (7S,0)
		enquiry.setFieldValue("HZDRF", ""); // Subsidiary reference (16A)
		enquiry.setFieldValue("HZTSQ", "0"); // Transaction sequence number (16P,0)
		enquiry.setFieldValue("HZESQ", "0"); // Event sequence number (7P,0)
		enquiry.setFieldValue("HZSSQ", "0"); // Slave sequence number (7P,0)

		/*
		 * See if it works
		 */
		assertTestStandardEnquiry(enquiry, true);
	}
}
