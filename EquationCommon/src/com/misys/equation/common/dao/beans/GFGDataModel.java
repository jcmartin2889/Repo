package com.misys.equation.common.dao.beans;

public class GFGDataModel extends AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GFGDataModel.java 10168 2010-12-18 16:55:12Z WRIGHTP1 $";

	private static final long serialVersionUID = 3483549204108810351L;

	// private final static String RECORD_NAME = "OM40LF";

	private String gfgSelectable;
	private String upToDate;
	private long gfgPayTotal;
	private long gfgReceiveTotal;
	private long gfgSumTotal;
	private long gfgAggregate;
	private String gfgIntervalStart;
	private String gfgIntervalEnd;
	private String processingDate;

	/**
	 * Default constructor
	 */
	public GFGDataModel()
	{
		super();
		// setEqFileName(RECORD_NAME);
	}

	public GFGDataModel(String gfgSelectable, String upToDate, long gfgPayTotal, long gfgReceiveTotal, long gfgSumTotal,
					long gfgAggregate, String gfgIntervalStart, String gfgIntervalEnd, String processingDate)
	{
		super();
		this.gfgSelectable = gfgSelectable;
		this.upToDate = upToDate;
		this.gfgPayTotal = gfgPayTotal;
		this.gfgReceiveTotal = gfgReceiveTotal;
		this.gfgSumTotal = gfgSumTotal;
		this.gfgAggregate = gfgAggregate;
		this.gfgIntervalStart = gfgIntervalStart;
		this.gfgIntervalEnd = gfgIntervalEnd;
		this.processingDate = processingDate;
	}

	public String getSelectable()
	{
		return gfgSelectable;
	}

	public void setSelectable(String gfgSelectable)
	{
		this.gfgSelectable = gfgSelectable;
	}

	public String getUpToDate()
	{
		return upToDate;
	}

	public void setUpToDate(String upToDate)
	{
		this.upToDate = upToDate;
	}

	public long getGfgPayTotal()
	{
		return gfgPayTotal;
	}

	public void setGfgPayTotal(long gfgPayTotal)
	{
		this.gfgPayTotal = gfgPayTotal;
	}

	public long getGfgReceiveTotal()
	{
		return gfgReceiveTotal;
	}

	public void setGfgReceiveTotal(long gfgReceiveTotal)
	{
		this.gfgReceiveTotal = gfgReceiveTotal;
	}

	public long getGfgSumTotal()
	{
		return gfgSumTotal;
	}

	public void setGfgSumTotal(long gfgSumTotal)
	{
		this.gfgSumTotal = gfgSumTotal;
	}

	public long getGfgAggregate()
	{
		return gfgAggregate;
	}

	public void setGfgAggregate(long gfgAggregate)
	{
		this.gfgAggregate = gfgAggregate;
	}
	public String getGfgIntervalStart()
	{
		return gfgIntervalStart;
	}

	public void setGfgIntervalStart(String gfgIntervalStart)
	{
		this.gfgIntervalStart = gfgIntervalStart;
	}

	public String getGfgIntervalEnd()
	{
		return gfgIntervalEnd;
	}

	public void setGfgIntervalEnd(String gfgIntervalEnd)
	{
		this.gfgIntervalEnd = gfgIntervalEnd;
	}

	public String getProcessingDate()
	{
		return processingDate;
	}

	public void setProcessingDate(String processingDate)
	{
		this.processingDate = processingDate;
	}
}