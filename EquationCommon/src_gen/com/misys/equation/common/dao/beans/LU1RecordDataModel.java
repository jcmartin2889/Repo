package com.misys.equation.common.dao.beans;

/**
 * LU1 Record data-model.
 * 
 * @author deroset
 * 
 */
public class LU1RecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "LU10LF";

	private String branchNumber;
	private String cycleStatus;
	private String linkStatus;
	private String operationalStatus;
	private String downloadStatus;
	private String cycleStatusOverridden;
	private String cashierVersion;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1280744204976l;

	/**
	 * Default constructor
	 */
	public LU1RecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public LU1RecordDataModel(String branchNo)
	{
		this.branchNumber = branchNo;
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public String getBranchNumber()
	{
		return this.branchNumber;
	}
	public void setBranchNumber(String parameter)
	{
		this.branchNumber = parameter;
	}
	public String getCycleStatus()
	{
		return this.cycleStatus;
	}
	public void setCycleStatus(String parameter)
	{
		this.cycleStatus = parameter;
	}
	public String getLinkStatus()
	{
		return this.linkStatus;
	}
	public void setLinkStatus(String parameter)
	{
		this.linkStatus = parameter;
	}
	public String getOperationalStatus()
	{
		return this.operationalStatus;
	}
	public void setOperationalStatus(String parameter)
	{
		this.operationalStatus = parameter;
	}
	public String getDownloadStatus()
	{
		return this.downloadStatus;
	}
	public void setDownloadStatus(String parameter)
	{
		this.downloadStatus = parameter;
	}
	public String getCycleStatusOverridden()
	{
		return this.cycleStatusOverridden;
	}
	public void setCycleStatusOverridden(String parameter)
	{
		this.cycleStatusOverridden = parameter;
	}
	public String getCashierVersion()
	{
		return this.cashierVersion;
	}
	public void setCashierVersion(String parameter)
	{
		this.cashierVersion = parameter;
	}
}