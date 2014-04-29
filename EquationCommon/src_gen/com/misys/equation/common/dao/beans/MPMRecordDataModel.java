package com.misys.equation.common.dao.beans;

/**
 * MPM Record data-model.
 * 
 * @author deroset
 * 
 */
public class MPMRecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "MPM10LF";

	private String codeFile;
	private String masterValue;
	private String unitMnemonic;
	private String serverName;
	private String unitValue;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1276834713073l;

	/**
	 * Default constructor
	 */
	public MPMRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public MPMRecordDataModel(String codeFile, String masterValue, String unitMnemonic, String serverName)
	{
		this.codeFile = codeFile;
		this.masterValue = masterValue;
		this.unitMnemonic = unitMnemonic;
		this.serverName = serverName;
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public String getCodeFile()
	{
		return this.codeFile;
	}
	public void setCodeFile(String parameter)
	{
		this.codeFile = parameter;
	}
	public String getMasterValue()
	{
		return this.masterValue;
	}
	public void setMasterValue(String parameter)
	{
		this.masterValue = parameter;
	}
	public String getUnitMnemonic()
	{
		return this.unitMnemonic;
	}
	public void setUnitMnemonic(String parameter)
	{
		this.unitMnemonic = parameter;
	}
	public String getServerName()
	{
		return this.serverName;
	}
	public void setServerName(String parameter)
	{
		this.serverName = parameter;
	}
	public String getUnitValue()
	{
		return this.unitValue;
	}
	public void setUnitValue(String parameter)
	{
		this.unitValue = parameter;
	}
}