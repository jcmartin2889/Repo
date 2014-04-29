package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class PAE extends EquationTestCase // Principal Increase/Decrease Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: PAE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardEnquiry enquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		enquiry = getEquationStandardEnquiry("G89EER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		enquiry.setFieldValue("HZDLP", "CL9"); // Deal type (3A)
		enquiry.setFieldValue("HZDLR", "CRLL2V3"); // Deal reference (13A)
		enquiry.setFieldValue("HZBRNM", "CITY"); // Deal branch (4A)
		enquiry.setFieldValue("HZDTE", "1000131"); // Movement date (7S,0)
		/*
		 * If more than one principal increase/decrease exists on the same date it is necessary to specify the sequence number for
		 * the 'PO' movement in HZNS3. HZNS3 defaults to zero i.e. the first principal increase/decrease on that date.
		 */
		enquiry.setFieldValue("HZNS3", "002"); // Sequence number (3P,0)

		/*
		 * See if it works
		 */
		assertTestStandardEnquiry(enquiry, true);
	}
}
