package com.misys.equation.xa.test;

public class ServiceDirectoryTestProxy implements com.misys.equation.xa.test.ServiceDirectoryTest
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ServiceDirectoryTestProxy.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	private String _endpoint = null;
	private com.misys.equation.xa.test.ServiceDirectoryTest serviceDirectoryTest = null;

	public ServiceDirectoryTestProxy()
	{
		_initServiceDirectoryTestProxy();
	}

	public ServiceDirectoryTestProxy(String endpoint)
	{
		_endpoint = endpoint;
		_initServiceDirectoryTestProxy();
	}

	private void _initServiceDirectoryTestProxy()
	{
		try
		{
			serviceDirectoryTest = (new com.misys.equation.xa.test.ServiceDirectoryTestServiceLocator()).getServiceDirectoryTest();
			if (serviceDirectoryTest != null)
			{
				if (_endpoint != null)
					((javax.xml.rpc.Stub) serviceDirectoryTest)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) serviceDirectoryTest)
									._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		}
		catch (javax.xml.rpc.ServiceException serviceException)
		{
		}
	}

	public String getEndpoint()
	{
		return _endpoint;
	}

	public void setEndpoint(String endpoint)
	{
		_endpoint = endpoint;
		if (serviceDirectoryTest != null)
			((javax.xml.rpc.Stub) serviceDirectoryTest)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public com.misys.equation.xa.test.ServiceDirectoryTest getServiceDirectoryTest()
	{
		if (serviceDirectoryTest == null)
			_initServiceDirectoryTestProxy();
		return serviceDirectoryTest;
	}

	public java.lang.String xaTests(java.lang.String sessionIdentifier, java.lang.String transactionIdentifier)
					throws java.rmi.RemoteException
	{
		if (serviceDirectoryTest == null)
			_initServiceDirectoryTestProxy();
		return serviceDirectoryTest.xaTests(sessionIdentifier, transactionIdentifier);
	}

}
