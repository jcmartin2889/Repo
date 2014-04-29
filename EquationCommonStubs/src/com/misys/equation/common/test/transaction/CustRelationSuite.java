/**
 * Copyright and all other intellectual property rights in this software, in any form, is vested in Misys International Banking
 * Systems Ltd ("Misys") or a related company.
 * 
 * This software may not be copied, amended, compiled, translated, or developed; or sold, leased, hired, rented, or disclosed to any
 * third party without the prior written consent of Misys.
 * 
 * Copyright Misys International Banking Systems Ltd, 1975 and later
 */

package com.misys.equation.common.test.transaction;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.misys.equation.common.test.AbstractTestSuite;

public class CustRelationSuite extends AbstractTestSuite
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CustRelationSuite.java 4967 2009-09-28 15:54:02Z challip1 $";
	public static Test suite()
	{
		// test suite
		TestSuite suite = new TestSuite("Test for Customer Relationships");

		addTests(suite, ACS.class); // Add Customer Relationships
		addTests(suite, ACS_RelatedCustomer.class); // Add Customer Relationships
		addTests(suite, MCS.class); // Maintain Customer Relationships
		addTests(suite, MCS_RelatedCustomer.class); // Maintain Customer Relationships

		return suite;
	}

}
