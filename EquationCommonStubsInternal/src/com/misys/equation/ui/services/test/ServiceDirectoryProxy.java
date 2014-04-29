package com.misys.equation.ui.services.test;

public class ServiceDirectoryProxy implements com.misys.equation.ui.services.test.ServiceDirectory
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ServiceDirectoryProxy.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	private String _endpoint = null;
	private com.misys.equation.ui.services.test.ServiceDirectory serviceDirectory = null;

	public ServiceDirectoryProxy()
	{
		_initServiceDirectoryProxy();
	}

	public ServiceDirectoryProxy(String endpoint)
	{
		_endpoint = endpoint;
		_initServiceDirectoryProxy();
	}

	private void _initServiceDirectoryProxy()
	{
		try
		{
			serviceDirectory = (new com.misys.equation.ui.services.test.ServiceDirectoryServiceLocator()).getServiceDirectory();
			if (serviceDirectory != null)
			{
				if (_endpoint != null)
					((javax.xml.rpc.Stub) serviceDirectory)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) serviceDirectory)
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
		if (serviceDirectory != null)
			((javax.xml.rpc.Stub) serviceDirectory)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public com.misys.equation.ui.services.test.ServiceDirectory getServiceDirectory()
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory;
	}

	public void setContext(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3,
					java.lang.String in4) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		serviceDirectory.setContext(in0, in1, in2, in3, in4);
	}

	public void removeSession(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3,
					java.lang.String in4, java.lang.String in5) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		serviceDirectory.removeSession(in0, in1, in2, in3, in4, in5);
	}

	public java.lang.String getEqSession(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3,
					java.lang.String in4, java.lang.String in5, java.lang.String in6) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.getEqSession(in0, in1, in2, in3, in4, in5, in6);
	}

	public java.lang.String exchangeBFKey(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.exchangeBFKey(in0, in1);
	}

	public java.lang.String userExitValidate(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3, java.lang.String in4, java.lang.String in5, java.lang.String in6)
					throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.userExitValidate(in0, in1, in2, in3, in4, in5, in6);
	}

	public java.lang.String getEqDataList(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3,
					java.lang.String in4, java.lang.String in5, java.lang.String in6, java.lang.String in7, int in8, int in9)
					throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.getEqDataList(in0, in1, in2, in3, in4, in5, in6, in7, in8, in9);
	}

	public java.lang.String getEqReferral(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.getEqReferral(in0);
	}

	public java.lang.String getRecentOptionHTML(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.getRecentOptionHTML(in0);
	}

	public java.lang.String getFullMenuHTML(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.getFullMenuHTML(in0);
	}

	public java.lang.String getUserSpoolFilesHTML(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.getUserSpoolFilesHTML(in0);
	}

	public java.lang.String getUnitSpoolFilesHTML(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.getUnitSpoolFilesHTML(in0);
	}

	public java.lang.String getJobLogHTML(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3)
					throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.getJobLogHTML(in0, in1, in2, in3);
	}

	public java.lang.String getJobLogDirHTML(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3, java.lang.String in4, java.lang.String in5) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.getJobLogDirHTML(in0, in1, in2, in3, in4, in5);
	}

	public java.lang.String getJobLogEntryHTML(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3, java.lang.String in4, java.lang.String in5, java.lang.String in6, java.lang.String in7)
					throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.getJobLogEntryHTML(in0, in1, in2, in3, in4, in5, in6, in7);
	}

	public java.lang.String getMsgQueueHTML(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.getMsgQueueHTML(in0, in1);
	}

	public java.lang.String getMsgQueueDirHTML(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.getMsgQueueDirHTML(in0, in1, in2, in3);
	}

	public java.lang.String getMsgQueueEntryHTML(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3, java.lang.String in4, java.lang.String in5) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.getMsgQueueEntryHTML(in0, in1, in2, in3, in4, in5);
	}

	public java.lang.String getMsgFileEntryHTML(java.lang.String in0, java.lang.String in1, java.lang.String in2)
					throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.getMsgFileEntryHTML(in0, in1, in2);
	}

	public void destroyPools(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		serviceDirectory.destroyPools(in0);
	}

	public java.lang.String getWorkLoadHTML(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.getWorkLoadHTML(in0);
	}

	public java.lang.String getJournalPrint(java.lang.String in0, java.lang.String in1, int in2, int in3, int in4,
					java.lang.String in5, java.lang.String in6, java.lang.String in7) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.getJournalPrint(in0, in1, in2, in3, in4, in5, in6, in7);
	}

	public java.lang.String applyTransaction(java.lang.String in0, java.lang.String in1, int in2, int in3, int in4,
					java.lang.String in5, java.lang.String in6, java.lang.String in7, java.lang.String in8, java.lang.String in9)
					throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.applyTransaction(in0, in1, in2, in3, in4, in5, in6, in7, in8, in9);
	}

	public java.lang.String applyTransactionData(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3, java.lang.String in4, java.lang.String in5) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.applyTransactionData(in0, in1, in2, in3, in4, in5);
	}

	public java.lang.String remoteSupervisor(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3)
					throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.remoteSupervisor(in0, in1, in2, in3);
	}

	public void removeSupervisor(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		serviceDirectory.removeSupervisor(in0, in1, in2);
	}

	public java.lang.String checkSessionStatus(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.checkSessionStatus(in0, in1);
	}

	public void authoriseBySupervisorOverride(java.lang.String in0, java.lang.String in1, java.lang.String in2)
					throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		serviceDirectory.authoriseBySupervisorOverride(in0, in1, in2);
	}

	public void authoriseBySupervisorRm(java.lang.String in0, java.lang.String in1, java.lang.String in2, int in3)
					throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		serviceDirectory.authoriseBySupervisorRm(in0, in1, in2, in3);
	}

	public java.lang.String authoriseBySupervisorId(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3, java.lang.String in4) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.authoriseBySupervisorId(in0, in1, in2, in3, in4);
	}

	public void invalidateValidationUserExit(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3)
					throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		serviceDirectory.invalidateValidationUserExit(in0, in1, in2, in3);
	}

	public void logoffDesktop(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		serviceDirectory.logoffDesktop(in0, in1);
	}

	public java.lang.String getPromptHelpDetails(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3, java.lang.String in4) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.getPromptHelpDetails(in0, in1, in2, in3, in4);
	}

	public java.lang.String addChildFunctionHandler(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3, java.lang.String in4, java.lang.String in5) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.addChildFunctionHandler(in0, in1, in2, in3, in4, in5);
	}

	public java.lang.String resetContext(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.resetContext(in0);
	}

	public java.lang.String isSessionAlive(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.isSessionAlive(in0);
	}

	public java.lang.String updateFunctionData(java.lang.String in0, java.lang.String in1, java.lang.String in2)
					throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.updateFunctionData(in0, in1, in2);
	}

	public java.lang.String getJobId(java.lang.String in0) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.getJobId(in0);
	}

	public java.lang.String getPredictiveList(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3, java.lang.String in4, java.lang.String in5, int in6) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.getPredictiveList(in0, in1, in2, in3, in4, in5, in6);
	}

	public void removeSpooledFile(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3,
					java.lang.String in4, java.lang.String in5, int in6) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		serviceDirectory.removeSpooledFile(in0, in1, in2, in3, in4, in5, in6);
	}

	public java.lang.String repeatingGroupPageAction(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.repeatingGroupPageAction(in0, in1, in2, in3);
	}

	public java.lang.String breakByRepatingDataAction(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.breakByRepatingDataAction(in0, in1, in2, in3);
	}

	public java.lang.String repeatingGroupSortAction(java.lang.String in0, java.lang.String in1, java.lang.String in2,
					java.lang.String in3) throws java.rmi.RemoteException
	{
		if (serviceDirectory == null)
			_initServiceDirectoryProxy();
		return serviceDirectory.repeatingGroupSortAction(in0, in1, in2, in3);
	}

}
