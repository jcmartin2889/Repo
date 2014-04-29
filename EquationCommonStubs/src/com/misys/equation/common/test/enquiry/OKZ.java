package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class OKZ extends EquationTestCase // Commercial Paper Enquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OKZ.java 8426 2010-07-29 17:32:49Z CHALLIP1 $";
	private EquationStandardEnquiry enquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 */
		enquiry = getEquationStandardEnquiry("W70EER");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		enquiry.setFieldValue("HZBPN", "05436677"); // Paper Number (12A)

		/*
		 * See if it works
		 */
		assertTestStandardEnquiry(enquiry, true);
	}
}
