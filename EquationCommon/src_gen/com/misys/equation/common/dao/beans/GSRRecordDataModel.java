package com.misys.equation.common.dao.beans;

import java.sql.Timestamp;

/**
 * GSR Record data-model.
 * 
 * @author deroset
 */
public class GSRRecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "GSR10LF";

	private String searchedBy;
	private String searchedInSystem;
	private String searchedInUnit;
	private String searchType;
	private long searchIdentifier;
	private Timestamp timestamp;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1287559304120l;

	/**
	 * Default constructor
	 */
	public GSRRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public GSRRecordDataModel(long searchIdentifier)
	{
		this.searchIdentifier = searchIdentifier;
		setEqFileName(RECORD_NAME);
	}

	public GSRRecordDataModel(long searchIdentifier, String searchedBy, String searchedInSystem, String searchedInUnit,
					Timestamp timestamp, String searchType)
	{
		this.searchedBy = searchedBy;
		this.searchedInSystem = searchedInSystem;
		this.searchedInUnit = searchedInUnit;
		this.searchIdentifier = searchIdentifier;
		this.timestamp = timestamp;
		this.searchType = searchType;
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public String getSearchedBy()
	{
		return this.searchedBy;
	}
	public void setSearchedBy(String parameter)
	{
		this.searchedBy = parameter;
	}
	public String getSearchedInSystem()
	{
		return this.searchedInSystem;
	}
	public void setSearchedInSystem(String parameter)
	{
		this.searchedInSystem = parameter;
	}
	public String getSearchedInUnit()
	{
		return this.searchedInUnit;
	}
	public void setSearchedInUnit(String parameter)
	{
		this.searchedInUnit = parameter;
	}
	public String getSearchType()
	{
		return this.searchType;
	}
	public void setSearchType(String parameter)
	{
		this.searchType = parameter;
	}
	public long getSearchIdentifier()
	{
		return this.searchIdentifier;
	}
	public void setSearchIdentifier(long parameter)
	{
		this.searchIdentifier = parameter;
	}
	public Timestamp getTimestamp()
	{
		return this.timestamp;
	}
	public void setTimestamp(Timestamp parameter)
	{
		this.timestamp = parameter;
	}
}