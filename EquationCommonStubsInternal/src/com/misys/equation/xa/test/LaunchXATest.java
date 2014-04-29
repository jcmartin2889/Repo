package com.misys.equation.xa.test;

import java.net.URL;

public class LaunchXATest
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LaunchXATest.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public static void main(String[] args)
	{
		LaunchXATest test = new LaunchXATest();
		test.test();
	}

	/**
	 * 
	 */
	public void test()
	{
		URL endpointURL = null;
		ServiceDirectoryTestSoapBindingStub stub = null;
		try
		{
			endpointURL = new URL("http://localhost:9083/EquationDesktop/services/ServiceDirectoryTest");
			stub = new ServiceDirectoryTestSoapBindingStub(endpointURL, null);
			stub.xaTests(null, "XA");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}