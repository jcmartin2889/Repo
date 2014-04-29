package com.misys.equation.common.test;

import junit.framework.TestCase;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400JPing;

public class AS400PingTest extends TestCase
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AS400PingTest.java 7859 2010-06-18 14:05:11Z lima12 $";
	@Override
	public void setUp()
	{

	}

	public void testAs400Ping()
	{

		AS400JPing pingObj = new AS400JPing("SLOUGH1", AS400.COMMAND, false);
		boolean result = pingObj.ping();

		if (result)
		{
			System.out.println("SUCCESS");
		}
		else
		{
			System.out.println("FAILED");
		}
		assertTrue(result);

	}

}
