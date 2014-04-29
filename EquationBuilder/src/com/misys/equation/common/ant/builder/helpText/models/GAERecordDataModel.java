package com.misys.equation.common.ant.builder.helpText.models;

import com.misys.equation.common.ant.builder.helpText.helpers.Toolbox;

/**
 * GAERecord data-model.
 * 
 * @author deroset
 * 
 */
public class GAERecordDataModel extends AbsRecord
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GAERecordDataModel.java 4581 2009-09-02 13:53:07Z esther.williams $";
	private String root;
	private String id;
	private String description;
	private String keys;
	private String type;
	private String headerJournalFileName;
	private String detailJournalFileName;

	public String getRoot()
	{
		return root;

	}

	public void setRoot(String gaeapir)
	{
		root = gaeapir;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String gaefnm)
	{
		description = gaefnm;
	}

	public String getKeys()
	{
		return keys;
	}

	public void setKeys(String gaekey)
	{
		keys = gaekey;
	}

	public String getType()
	{
		return type;
	}
	public void setType(String gaeatyp)
	{
		type = gaeatyp;
	}

	public String getHeaderJournalFileName()
	{
		return headerJournalFileName;
	}

	public void setHeaderJournalFileName(String headerJournalFile)
	{
		this.headerJournalFileName = headerJournalFile;
	}

	public String getDetailJournalFileName()
	{
		return detailJournalFileName;
	}

	public void setDetailJournalFileName(String detailJournalFile)
	{
		this.detailJournalFileName = detailJournalFile;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder(1024);
		builder.append(getId().trim());
		builder.append(Toolbox.TEXT_DELIMITER);
		builder.append(getDescription().trim());
		builder.append(Toolbox.TEXT_DELIMITER);
		builder.append(getRoot());
		return builder.toString();
	}
}
