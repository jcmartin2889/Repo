package com.misys.equation.common.dao.beans;

public class FPGDataModel extends AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FPGDataModel.java 10168 2010-12-18 16:55:12Z WRIGHTP1 $";

	private static final long serialVersionUID = 3483549204108810351L;

	// private final static String RECORD_NAME = "OM40LF";

	private String fxSystem;
	private String unitMnem;
	private String branchMnem;
	private long fxPayTotal;
	private long fxReceiveTotal;
	private long fxSumTotal;

	/**
	 * Default constructor
	 */
	public FPGDataModel()
	{
		super();
		// setEqFileName(RECORD_NAME);
	}

	public FPGDataModel(String fxSystem, String unitMnem, String branchMnem, long fxPayTotal, long fxReceiveTotal, long fxSumTotal)
	{
		super();
		this.fxSystem = fxSystem;
		this.unitMnem = unitMnem;
		this.branchMnem = branchMnem;
		this.fxPayTotal = fxPayTotal;
		this.fxReceiveTotal = fxReceiveTotal;
		this.fxSumTotal = fxSumTotal;
	}

	public String getFxSystem()
	{
		return fxSystem;
	}

	public void setFxSystem(String fxSystem)
	{
		this.fxSystem = fxSystem;
	}

	public String getUnitMnem()
	{
		return unitMnem;
	}

	public void setUnitMnem(String unitMnem)
	{
		this.unitMnem = unitMnem;
	}

	public String getBranchMnem()
	{
		return branchMnem;
	}

	public void setBranchMnem(String branchMnem)
	{
		this.branchMnem = branchMnem;
	}

	public long getFxPayTotal()
	{
		return fxPayTotal;
	}

	public void setFxPayTotal(long fxPayTotal)
	{
		this.fxPayTotal = fxPayTotal;
	}

	public long getFxReceiveTotal()
	{
		return fxReceiveTotal;
	}

	public void setFxReceiveTotal(long fxReceiveTotal)
	{
		this.fxReceiveTotal = fxReceiveTotal;
	}

	public long getFxSumTotal()
	{
		return fxSumTotal;
	}

	public void setFxSumTotal(long fxSumTotal)
	{
		this.fxSumTotal = fxSumTotal;
	}
}