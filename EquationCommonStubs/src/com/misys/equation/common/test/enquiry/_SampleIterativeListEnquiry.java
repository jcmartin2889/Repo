package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class _SampleIterativeListEnquiry extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: _SampleIterativeListEnquiry.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry iterativeListEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		iterativeListEnquiry = getEquationStandardListEnquiry("I31DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the fields required for the enquiry
		 */
		iterativeListEnquiry.setFieldValue("HZDLP", "CD1");// deal type
		iterativeListEnquiry.setFieldValue("HZCCY", "GBP");// currency
		iterativeListEnquiry.setFieldValue("HZDLA", "000000010000000");// deal amount
		iterativeListEnquiry.setFieldValue("HZSDTE", "1000101");// start date
		iterativeListEnquiry.setFieldValue("HZIDB", "3");// interest days basis
		iterativeListEnquiry.setFieldValue("HZRAT", "001000000000");// rate
		iterativeListEnquiry.setFieldValue("HZNCD", "1000201");// next cycle date
		iterativeListEnquiry.setFieldValue("HZRFRQ", "V01");// frequency
		iterativeListEnquiry.setFieldValue("HZNDT", "1000201");// next date
		iterativeListEnquiry.setFieldValue("HZNPY", "024");// number of payments
		iterativeListEnquiry.setFieldValue("HZDIF", "Y");
		iterativeListEnquiry.setFieldValue("HZMODE", "0");

		/*
		 * See if it works
		 */
		assertTestStandardIncrementalListEnquiry(iterativeListEnquiry, true);
	}
}
