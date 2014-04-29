package com.misys.equation.utility.test;

import com.misys.equation.common.test.EquationTestCase;

public class GHUpdate_SGH02R extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GHUpdate_SGH02R.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public void test()
	{
		// Have a bash...
		try
		{
			session.updateGH("A", "OID", "program", "c", "app", "tcpIp");
			session.updateGH("M", "OID", "program", "c", "app", "tcpIp");
			session.updateGH("O", "OID", "program", "c", "app", "tcpIp");
			session.updateGH("D", "OID", "program", "c", "app", "tcpIp");
			System.out.println("Done");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
