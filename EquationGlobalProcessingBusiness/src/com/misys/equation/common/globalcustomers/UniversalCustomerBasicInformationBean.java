package com.misys.equation.common.globalcustomers;

public class UniversalCustomerBasicInformationBean
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UniversalCustomerBasicInformationBean.java 10215 2011-01-06 12:06:08Z MACDONP1 $";
	private String globalUserId = "0000000";
	private String system;
	private String unit;
	private String customerNumber;
	private String masterFlag;

	private String customerMnemonic;
	private String customerLocation;

	public String getGlobalUserId()
	{
		return globalUserId;
	}

	public void setGlobalUserId(String globalUserId)
	{
		if (globalUserId != null)
		{
			this.globalUserId = globalUserId.trim();
		}
		else
		{
			this.globalUserId = null;
		}
	}

	public String getUnit()
	{
		return unit;
	}
	public void setUnit(String unit)
	{
		this.unit = unit;
	}
	public String getCustomerNumber()
	{
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber)
	{
		this.customerNumber = customerNumber;
	}
	public String getCustomerMnemonic()
	{
		return customerMnemonic;
	}
	public void setCustomerMnemonic(String customerMnemonic)
	{
		this.customerMnemonic = customerMnemonic;
	}
	public String getCustomerLocation()
	{
		return customerLocation;
	}
	public void setCustomerLocation(String customerLocation)
	{
		this.customerLocation = customerLocation;
	}
	public String getMasterFlag()
	{
		return masterFlag;
	}
	public void setMasterFlag(String masterFlag)
	{
		this.masterFlag = masterFlag;
	}
	public String getSystem()
	{
		return system;
	}
	public void setSystem(String system)
	{
		this.system = system.trim();
	}
}