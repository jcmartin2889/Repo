package com.misys.equation.common.dao.beans;

/**
 * GSP Record data-model.
 * 
 * @author deroset
 */
public class GSPRecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "GSP10LF";

	private long searchIdentifier;
	private String searchProperties;
	private String searchValue;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1275040799707l;

	/**
	 * Default constructor
	 */
	public GSPRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public GSPRecordDataModel(long searchIdentifier, String searchProperties, String searchValue)
	{
		this.searchIdentifier = searchIdentifier;
		this.searchProperties = searchProperties;
		this.searchValue = searchValue;
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public long getSearchIdentifier()
	{
		return this.searchIdentifier;
	}
	public void setSearchIdentifier(long parameter)
	{
		this.searchIdentifier = parameter;
	}
	public String getSearchProperties()
	{
		return this.searchProperties;
	}
	public void setSearchProperties(String parameter)
	{
		this.searchProperties = parameter;
	}
	public String getSearchValue()
	{
		return this.searchValue;
	}
	public void setSearchValue(String parameter)
	{
		this.searchValue = parameter;
	}
}