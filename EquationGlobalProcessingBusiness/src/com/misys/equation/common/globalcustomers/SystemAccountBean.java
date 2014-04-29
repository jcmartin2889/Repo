package com.misys.equation.common.globalcustomers;

public class SystemAccountBean
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SystemAccountBean.java 9210 2010-09-17 15:31:21Z deroset $";
	private String internalCustomer;
	private String systemAccountId;
	private String unit;

	public String getInternalCustomer()
	{
		return internalCustomer;
	}
	public void setInternalCustomer(String internalCustomer)
	{
		this.internalCustomer = internalCustomer;
	}
	public String getSystemAccountId()
	{
		return systemAccountId;
	}
	public void setSystemAccountId(String systemAccountId)
	{
		this.systemAccountId = systemAccountId;
	}
	public String getUnit()
	{
		return unit;
	}
	public void setUnit(String unit)
	{
		this.unit = unit;
	}
}