package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class IRE extends EquationTestCase // Retirement Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: IRE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("M56EER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZBRNM", "ACC1"); // Branch mnemonic (4A)
		listEnquiry.setFieldValue("HZDLP", "AAA"); // Deal prefix (3A)
		listEnquiry.setFieldValue("HZDLR", "X"); // Deal reference (13A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
