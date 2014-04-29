/**
 * ServiceDirectoryTestServiceLocator.java
 * 
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.misys.equation.xa.test;

public class ServiceDirectoryTestServiceLocator extends org.apache.axis.client.Service implements
				com.misys.equation.xa.test.ServiceDirectoryTestService
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ServiceDirectoryTestServiceLocator.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";

	public ServiceDirectoryTestServiceLocator()
	{

	}

	public ServiceDirectoryTestServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	public ServiceDirectoryTestServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
					throws javax.xml.rpc.ServiceException
	{
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for ServiceDirectoryTest
	private java.lang.String ServiceDirectoryTest_address = "http://localhost:8080/EquationDesktopStubs/services/ServiceDirectoryTest";

	public java.lang.String getServiceDirectoryTestAddress()
	{
		return ServiceDirectoryTest_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String ServiceDirectoryTestWSDDServiceName = "ServiceDirectoryTest";

	public java.lang.String getServiceDirectoryTestWSDDServiceName()
	{
		return ServiceDirectoryTestWSDDServiceName;
	}

	public void setServiceDirectoryTestWSDDServiceName(java.lang.String name)
	{
		ServiceDirectoryTestWSDDServiceName = name;
	}

	public com.misys.equation.xa.test.ServiceDirectoryTest getServiceDirectoryTest() throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;
		try
		{
			endpoint = new java.net.URL(ServiceDirectoryTest_address);
		}
		catch (java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getServiceDirectoryTest(endpoint);
	}

	public com.misys.equation.xa.test.ServiceDirectoryTest getServiceDirectoryTest(java.net.URL portAddress)
					throws javax.xml.rpc.ServiceException
	{
		try
		{
			com.misys.equation.xa.test.ServiceDirectoryTestSoapBindingStub _stub = new com.misys.equation.xa.test.ServiceDirectoryTestSoapBindingStub(
							portAddress, this);
			_stub.setPortName(getServiceDirectoryTestWSDDServiceName());
			return _stub;
		}
		catch (org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	public void setServiceDirectoryTestEndpointAddress(java.lang.String address)
	{
		ServiceDirectoryTest_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has no port for the given interface, then
	 * ServiceException is thrown.
	 */
	@Override
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException
	{
		try
		{
			if (com.misys.equation.xa.test.ServiceDirectoryTest.class.isAssignableFrom(serviceEndpointInterface))
			{
				com.misys.equation.xa.test.ServiceDirectoryTestSoapBindingStub _stub = new com.misys.equation.xa.test.ServiceDirectoryTestSoapBindingStub(
								new java.net.URL(ServiceDirectoryTest_address), this);
				_stub.setPortName(getServiceDirectoryTestWSDDServiceName());
				return _stub;
			}
		}
		catch (java.lang.Throwable t)
		{
			throw new javax.xml.rpc.ServiceException(t);
		}
		throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  "
						+ (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation. If this service has no port for the given interface, then
	 * ServiceException is thrown.
	 */
	@Override
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface)
					throws javax.xml.rpc.ServiceException
	{
		if (portName == null)
		{
			return getPort(serviceEndpointInterface);
		}
		java.lang.String inputPortName = portName.getLocalPart();
		if ("ServiceDirectoryTest".equals(inputPortName))
		{
			return getServiceDirectoryTest();
		}
		else
		{
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	@Override
	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName("http://test.xa.equation.misys.com", "ServiceDirectoryTestService");
	}

	private java.util.HashSet ports = null;

	@Override
	public java.util.Iterator getPorts()
	{
		if (ports == null)
		{
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("http://test.xa.equation.misys.com", "ServiceDirectoryTest"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException
	{

		if ("ServiceDirectoryTest".equals(portName))
		{
			setServiceDirectoryTestEndpointAddress(address);
		}
		else
		{ // Unknown Port Name
			throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
		}
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address)
					throws javax.xml.rpc.ServiceException
	{
		setEndpointAddress(portName.getLocalPart(), address);
	}

}
