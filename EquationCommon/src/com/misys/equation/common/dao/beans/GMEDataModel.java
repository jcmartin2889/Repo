package com.misys.equation.common.dao.beans;

public class GMEDataModel extends AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GMEDataModel.java 10168 2010-12-18 16:55:12Z WRIGHTP1 $";

	private static final long serialVersionUID = 3483549204108810351L;

	// private final static String RECORD_NAME = "OM40LF";

	private String mmSystem;
	private String mmUnit;
	private String mmBranch;
	private long mmPayAmt;
	private long mmReceiveAmt;
	private long mmNetAmt;

	/**
	 * Default constructor
	 */
	public GMEDataModel()
	{
		super();
		// setEqFileName(RECORD_NAME);
	}

	public GMEDataModel(String mmSystem, String mmUnit, String mmBranch, long mmPayAmt, long mmReceiveAmt, long mmNetAmt)
	{
		super();
		this.mmSystem = mmSystem;
		this.mmUnit = mmUnit;
		this.mmBranch = mmBranch;
		this.mmPayAmt = mmPayAmt;
		this.mmReceiveAmt = mmReceiveAmt;
		this.mmNetAmt = mmNetAmt;
	}

	public String getMMSystem()
	{
		return mmSystem;
	}

	public void setMMSystem(String mmSystem)
	{
		this.mmSystem = mmSystem;
	}

	public String getMMUnit()
	{
		return mmUnit;
	}

	public void setMMUnit(String mmUnit)
	{
		this.mmUnit = mmUnit;
	}

	public String getMMBranch()
	{
		return mmBranch;
	}

	public void setMMBranch(String mmBranch)
	{
		this.mmBranch = mmBranch;
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
}