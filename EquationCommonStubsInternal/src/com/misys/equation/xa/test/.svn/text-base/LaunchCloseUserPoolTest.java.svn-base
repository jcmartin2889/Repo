package com.misys.equation.xa.test;

import java.net.URL;

public class LaunchCloseUserPoolTest
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LaunchPerformanceTest.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public static void main(String[] args)
	{
		LaunchCloseUserPoolTest test = new LaunchCloseUserPoolTest();
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
			endpointURL = new URL("http://localhost:9081/EquationDesktop010/services/ServiceDirectoryTest");
			stub = new ServiceDirectoryTestSoapBindingStub(endpointURL, null);
			String dataSourceName = "EQ-SLOUGH1-EW9-ESTHER";
			String testParameter = "closeUserPoolTest:" + dataSourceName;
			stub.xaTests(null, testParameter);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
