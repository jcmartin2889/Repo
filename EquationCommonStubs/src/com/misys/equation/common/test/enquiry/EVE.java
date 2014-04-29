package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class EVE extends EquationTestCase // Event Charges Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EVE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("U65DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZAB", "0000"); // Account branch (4A)
		listEnquiry.setFieldValue("HZAN", "000001"); // Account basic number (6A)
		listEnquiry.setFieldValue("HZAS", "001"); // Account suffix (3A)
		listEnquiry.setFieldValue("HZEVNM", "ASI"); // Event (6A)

		listEnquiry.setFieldValue("HZTREF", "LONDLIMA"); // Transaction Reference (22A)
		listEnquiry.setFieldValue("HZDDTZ", "1000112"); // Date (7S,0)
		listEnquiry.setFieldValue("HZSQN1", "0000000000000001"); // Sequence number (16P,0)
		listEnquiry.setFieldValue("HZESQ", "000"); // Event sequence (3P,0)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
