package com.misys.equation.common.dao.beans;

public class GMLDataModel extends AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GMLDataModel.java 10168 2010-12-18 16:55:12Z WRIGHTP1 $";

	private static final long serialVersionUID = 3483549204108810351L;

	// private final static String RECORD_NAME = "OM40LF";

	private String mmSelectable;
	private String mmLadderDate;
	private long mmPayAmt;
	private long mmReceiveAmt;
	private long mmNetAmt;
	private long mmCumulativeAmt;

	/**
	 * Default constructor
	 */
	public GMLDataModel()
	{
		super();
		// setEqFileName(RECORD_NAME);
	}

	public GMLDataModel(String mmSelectable, String mmLadderDate, long mmPayAmt, long mmReceiveAmt, long mmNetAmt,
					long mmCumulativeAmt)
	{
		super();
		this.mmSelectable = mmSelectable;
		this.mmLadderDate = mmLadderDate;
		this.mmPayAmt = mmPayAmt;
		this.mmReceiveAmt = mmReceiveAmt;
		this.mmNetAmt = mmNetAmt;
		this.mmCumulativeAmt = mmCumulativeAmt;
	}

	public String getMMSelectable()
	{
		return mmSelectable;
	}

	public void setMMSelectable(String mmSelectable)
	{
		this.mmSelectable = mmSelectable;
	}

	public String getMMLadderDate()
	{
		return mmLadderDate;
	}

	public void setMMLadderDate(String mmLadderDate)
	{
		this.mmLadderDate = mmLadderDate;
	}

	public long getMMPayAmt()
	{
		return mmPayAmt;
	}

	public void setMMPayAmt(long mmPayAmt)
	{
		this.mmPayAmt = mmPayAmt;
	}

	public long getMMReceiveAmt()
	{
		return mmReceiveAmt;
	}

	public void setMMReceiveAmt(long mmReceiveAmt)
	{
		this.mmReceiveAmt = mmReceiveAmt;
	}

	public long getMMNetAmt()
	{
		return mmNetAmt;
	}

	public void setMMNetAmt(long mmNetAmt)
	{
		this.mmNetAmt = mmNetAmt;
	}

	public long getMMCumulativeAmt()
	{
		return mmCumulativeAmt;
	}

	public void setMMCumulativeAmt(long mmCumulativeAmt)
	{
		this.mmCumulativeAmt = mmCumulativeAmt;
	}
}