package com.misys.equation.common.dao.beans;

/**
 * GAM Record data-model.
 * 
 * @author deroset
 * 
 */
public class GAMRecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "GAMPF";

	private String dbFile;
	private String codeFile;
	private String api;
	private String desc;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1278915083526l;

	/**
	 * Default constructor
	 */
	public GAMRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public GAMRecordDataModel(String dbFile, String codeFile, String api)
	{
		this.dbFile = dbFile;
		this.codeFile = codeFile;
		this.api = api;
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public String getDbFile()
	{
		return this.dbFile;
	}
	public void setDbFile(String parameter)
	{
		this.dbFile = parameter;
	}
	public String getCodeFile()
	{
		return this.codeFile;
	}
	public void setCodeFile(String parameter)
	{
		this.codeFile = parameter;
	}
	public String getApi()
	{
		return this.api;
	}
	public void setApi(String parameter)
	{
		this.api = parameter;
	}
	public String getDesc()
	{
		return this.desc;
	}
	public void setDesc(String parameter)
	{
		this.desc = parameter;
	}
}