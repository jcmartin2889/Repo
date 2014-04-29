package com.misys.equation.utility.test;

import com.misys.equation.common.test.EquationTestCase;

public class SupervisorPassword_UTW21R extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SupervisorPassword_UTW21R.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public void test()
	{
		// Have a bash...
		try
		{
			session.supervisorPassword("LIMA", "LIMA", "");
			System.out.println("Done");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
