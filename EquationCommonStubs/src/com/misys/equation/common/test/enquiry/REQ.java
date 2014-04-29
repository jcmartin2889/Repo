package com.misys.equation.common.test.enquiry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class REQ extends EquationTestCase // EESF Rule Exceptions
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: REQ.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardListEnquiry listEnquiry;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		/*
		 * Get the enquiry class - you'll need to enter the name of the enquiry program e.g. H68EER
		 * 
		 * This enquiry uses HZX191 and HZX192 (not HZB601 and HZB602)
		 */
		listEnquiry = getEquationStandardListEnquiry("B61EER", "HZX201", "HZX202");
	}

	public void testRetrieve() throws Exception
	{
		/*
		 * Set up the key fields required for the enquiry
		 */
		listEnquiry.setFieldValue("HZGRP", "NTEST1"); // User group (6A)
		listEnquiry.setFieldValue("HZUSR", "    "); // User id (4A)
		listEnquiry.setFieldValue("HZRNO", "00001"); // Rule number (5P,0)

		/*
		 * See if it works
		 */
		assertTestStandardListEnquiry(listEnquiry, true);
	}
}
