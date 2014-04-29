package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class RCE extends EquationTestCase // Loan Repayment Calculator Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RCE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
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
		// Set some transaction fields up
		iterativeListEnquiry.setFieldValue("HZDLP", "CD1");
		iterativeListEnquiry.setFieldValue("HZBRNM", "LOND");
		iterativeListEnquiry.setFieldValue("HZCCY", "GBP");
		iterativeListEnquiry.setFieldValue("HZDLA", "000000010000000");
		iterativeListEnquiry.setFieldValue("HZSDTE", "1000101");
		iterativeListEnquiry.setFieldValue("HZIDB", "3");
		iterativeListEnquiry.setFieldValue("HZRAT", "000150000000");
		iterativeListEnquiry.setFieldValue("HZCRL", "N");
		iterativeListEnquiry.setFieldValue("HZMDT", "1000601");
		iterativeListEnquiry.setFieldValue("HZNCD", "1000201");
		iterativeListEnquiry.setFieldValue("HZRFRQ", "V01");
		iterativeListEnquiry.setFieldValue("HZNDT", "1000601");
		iterativeListEnquiry.setFieldValue("HZNPY", "1");
		iterativeListEnquiry.setFieldValue("HZDIF", "Y");
		iterativeListEnquiry.setFieldValue("HZMODE", "0");

		/*
		 * See if it works
		 */
		assertTestStandardIncrementalListEnquiry(iterativeListEnquiry, true);

		while (!iterativeListEnquiry.isComplete())
		{
			iterativeListEnquiry.setFieldValue("HZMODE", "4");
			assertTestStandardIncrementalListEnquiry(iterativeListEnquiry, true);
		}
	}
}
