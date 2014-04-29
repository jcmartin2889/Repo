package com.misys.equation.xa.test;

import java.net.URL;

public class LaunchPerformanceTest
{
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LaunchPerformanceTest.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public static void main(String[] args)
	{
		LaunchPerformanceTest test = new LaunchPerformanceTest();
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
			endpointURL = new URL("http://localhost:9082/EquationDesktop/services/ServiceDirectoryTest");
			stub = new ServiceDirectoryTestSoapBindingStub(endpointURL, null);
			stub.xaTests(null, "PERF");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
