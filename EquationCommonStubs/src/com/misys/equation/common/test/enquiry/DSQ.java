package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class DSQ extends EquationTestCase // Deal SWIFT status enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DSQ.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		listEnquiry = getEquationStandardListEnquiry("T51DER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZDLP", "AAA"); // Deal type (3A)
		listEnquiry.setFieldValue("HZDLR", "ALKDJFLKD"); // Deal reference (13A)
		listEnquiry.setFieldValue("HZBRNM", "LOND"); // Branch mnemonic (4A)
		listEnquiry.setFieldValue("HZFDTE", "1000105"); // From date (7A)
		listEnquiry.setFieldValue("HZFBI", "F"); // Yes/no indicator? (1A)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}