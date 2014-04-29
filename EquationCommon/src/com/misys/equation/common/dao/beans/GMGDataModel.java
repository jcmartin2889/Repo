package com.misys.equation.common.dao.beans;

public class GMGDataModel extends AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GMGDataModel.java 10168 2010-12-18 16:55:12Z WRIGHTP1 $";

	private static final long serialVersionUID = 3483549204108810351L;

	// private final static String RECORD_NAME = "OM40LF";

	private String gapSelectable;
	private String gapDate;
	private long gapPayAmt;
	private long gapReceiveAmt;
	private long gapNetAmt;
	private long gapCumulativeAmt;
	private String intervalStartDate;
	private String intervalEndDate;
	private String processingDate;

	/**
	 * Default constructor
	 */
	public GMGDataModel()
	{
		super();
		// setEqFileName(RECORD_NAME);
	}

	public GMGDataModel(String gapSelectable, String gapDate, long gapPayAmt, long gapReceiveAmt, long gapNetAmt,
					long gapCumulativeAmt, String intervalStartDate, String intervalEndDate, String processingDate)
	{
		super();
		this.gapSelectable = gapSelectable;
		this.gapDate = gapDate;
		this.gapPayAmt = gapPayAmt;
		this.gapReceiveAmt = gapReceiveAmt;
		this.gapNetAmt = gapNetAmt;
		this.gapCumulativeAmt = gapCumulativeAmt;
		this.intervalStartDate = intervalStartDate;
		this.intervalEndDate = intervalEndDate;
		this.processingDate = processingDate;
	}

	public String getSelectable()
	{
		return gapSelectable;
	}

	public void setSelectable(String gapSelectable)
	{
		this.gapSelectable = gapSelectable;
	}

	public String getGapDate()
	{
		return gapDate;
	}

	public void setGapDate(String gapDate)
	{
		this.gapDate = gapDate;
	}

	public long getGapPayAmt()
	{
		return gapPayAmt;
	}

	public void setGapPayAmt(long gapPayAmt)
	{
		this.gapPayAmt = gapPayAmt;
	}

	public long getGapReceiveAmt()
	{
		return gapReceiveAmt;
	}

	public void setGapReceiveAmt(long gapReceiveAmt)
	{
		this.gapReceiveAmt = gapReceiveAmt;
	}

	public long getGapNetAmt()
	{
		return gapNetAmt;
	}

	public void setGapNetAmt(long gapNetAmt)
	{
		this.gapNetAmt = gapNetAmt;
	}

	public long getGapCumulativeAmt()
	{
		return gapCumulativeAmt;
	}

	public void setGapCumulativeAmt(long gapCumulativeAmt)
	{
		this.gapCumulativeAmt = gapCumulativeAmt;
	}
	public String getIntervalStartDate()
	{
		return intervalStartDate;
	}

	public void setIntervalStartDate(String intervalStartDate)
	{
		this.intervalStartDate = intervalStartDate;
	}

	public String getIntervalEndDate()
	{
		return intervalEndDate;
	}

	public void setIntervalEndDate(String intervalEndDate)
	{
		this.intervalEndDate = intervalEndDate;
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