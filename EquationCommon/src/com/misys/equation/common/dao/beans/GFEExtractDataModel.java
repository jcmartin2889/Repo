package com.misys.equation.common.dao.beans;

public class GFEExtractDataModel extends AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GFEExtractDataModel.java 7543 2010-05-28 09:18:43Z hempensp $";
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -5372958223809553063L;

	private String currency;
	private String positionDate;
	private long overnightPosition;
	private long forwardPosition;
	private long dailyPosition;
	private long todayPosition;

	public GFEExtractDataModel(String currency, String positionDate, long overnightPosition, long forwardPosition,
					long dailyPosition, long todayPosition)
	{
		super();
		this.currency = currency;
		this.positionDate = positionDate;
		this.overnightPosition = overnightPosition;
		this.forwardPosition = forwardPosition;
		this.dailyPosition = dailyPosition;
		this.todayPosition = todayPosition;
	}
	public String getCurrency()
	{
		return currency;
	}
	public void setCurrency(String currency)
	{
		this.currency = currency;
	}
	public String getPositionDate()
	{
		return positionDate;
	}
	public void setPositionDate(String positionDate)
	{
		this.positionDate = positionDate;
	}
	public long getOvernightPosition()
	{
		return overnightPosition;
	}
	public void setOvernightPosition(long overnightPosition)
	{
		this.overnightPosition = overnightPosition;
	}
	public long getForwardPosition()
	{
		return forwardPosition;
	}
	public void setForwardPosition(long forwardPosition)
	{
		this.forwardPosition = forwardPosition;
	}
	public long getDailyPosition()
	{
		return dailyPosition;
	}
	public void setDailyPosition(long dailyPosition)
	{
		this.dailyPosition = dailyPosition;
	}
	public long getTodayPosition()
	{
		return todayPosition;
	}
	public void setTodayPosition(long todayPosition)
	{
		this.todayPosition = todayPosition;
	}

}
