/**
 * ServiceDirectoryServiceLocator.java
 * 
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.misys.equation.ui.services.test;

public class ServiceDirectoryServiceLocator extends org.apache.axis.client.Service implements
				com.misys.equation.ui.services.test.ServiceDirectoryService
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ServiceDirectoryServiceLocator.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";

	public ServiceDirectoryServiceLocator()
	{
	}

	public ServiceDirectoryServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	public ServiceDirectoryServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
					throws javax.xml.rpc.ServiceException
	{
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for ServiceDirectory
	private java.lang.String ServiceDirectory_address = "http://localhost:8080/EquationDesktop/services/ServiceDirectory";

	public java.lang.String getServiceDirectoryAddress()
	{
		return ServiceDirectory_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String ServiceDirectoryWSDDServiceName = "ServiceDirectory";

	public java.lang.String getServiceDirectoryWSDDServiceName()
	{
		return ServiceDirectoryWSDDServiceName;
	}

	public void setServiceDirectoryWSDDServiceName(java.lang.String name)
	{
		ServiceDirectoryWSDDServiceName = name;
	}

	public com.misys.equation.ui.services.test.ServiceDirectory getServiceDirectory() throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;
		try
		{
			endpoint = new java.net.URL(ServiceDirectory_address);
		}
		catch (java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getServiceDirectory(endpoint);
	}

	public com.misys.equation.ui.services.test.ServiceDirectory getServiceDirectory(java.net.URL portAddress)
					throws javax.xml.rpc.ServiceException
	{
		try
		{
			com.misys.equation.ui.services.test.ServiceDirectorySoapBindingStub _stub = new com.misys.equation.ui.services.test.ServiceDirectorySoapBindingStub(
							portAddress, this);
			_stub.setPortName(getServiceDirectoryWSDDServiceName());
			return _stub;
		}
		catch (org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	public void setServiceDirectoryEndpointAddress(java.lang.String address)
	{
		ServiceDirectory_address = address;
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
			if (com.misys.equation.ui.services.test.ServiceDirectory.class.isAssignableFrom(serviceEndpointInterface))
			{
				com.misys.equation.ui.services.test.ServiceDirectorySoapBindingStub _stub = new com.misys.equation.ui.services.test.ServiceDirectorySoapBindingStub(
								new java.net.URL(ServiceDirectory_address), this);
				_stub.setPortName(getServiceDirectoryWSDDServiceName());
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
		if ("ServiceDirectory".equals(inputPortName))
		{
			return getServiceDirectory();
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
		return new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "ServiceDirectoryService");
	}

	private java.util.HashSet ports = null;

	@Override
	public java.util.Iterator getPorts()
	{
		if (ports == null)
		{
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("http://services.ui.equation.misys.com", "ServiceDirectory"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException
	{

		if ("ServiceDirectory".equals(portName))
		{
			setServiceDirectoryEndpointAddress(address);
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