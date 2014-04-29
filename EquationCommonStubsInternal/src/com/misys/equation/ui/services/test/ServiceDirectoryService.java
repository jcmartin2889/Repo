/**
 * ServiceDirectoryService.java
 * 
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.misys.equation.ui.services.test;

public interface ServiceDirectoryService extends javax.xml.rpc.Service
{
	public java.lang.String getServiceDirectoryAddress();

	public com.misys.equation.ui.services.test.ServiceDirectory getServiceDirectory() throws javax.xml.rpc.ServiceException;

	public com.misys.equation.ui.services.test.ServiceDirectory getServiceDirectory(java.net.URL portAddress)
					throws javax.xml.rpc.ServiceException;
}
