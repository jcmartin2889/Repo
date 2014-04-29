package com.misys.equation.common.globalprocessing.calculators;

public class DateAndBalance
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DateAndBalance.java 9962 2010-11-18 17:31:39Z MACDONP1 $";
	private String nostroBalance;
	private String dbFormatDate;

	public DateAndBalance()
	{

	}
	public String getNostroBalance()
	{
		return nostroBalance;
	}
	public void setNostroBalance(String nostroBalance)
	{
		this.nostroBalance = nostroBalance;
	}
	public String getDbFormatDate()
	{
		return dbFormatDate;
	}
	public void setDbFormatDate(String date)
	{
		this.dbFormatDate = date;
	}
}
