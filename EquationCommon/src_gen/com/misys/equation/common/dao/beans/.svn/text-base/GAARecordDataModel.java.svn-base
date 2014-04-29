package com.misys.equation.common.dao.beans;

/**
 * GAA Record data-model.
 * 
 * @author deroset
 * 
 */
public class GAARecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "GAA10LF";

	private long sequenceId;
	private int retrySequence;
	private String retryUser;
	private String applyType;
	private String applyStatus;
	private String applicationMessages;
	private java.sql.Timestamp applyTimestamp;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1270638835180l;

	/**
	 * Default constructor
	 */
	public GAARecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public GAARecordDataModel(long sequenceId, int retrySequence)
	{
		this.sequenceId = sequenceId;
		this.retrySequence = retrySequence;
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public long getSequenceId()
	{
		return this.sequenceId;
	}
	public void setSequenceId(long parameter)
	{
		this.sequenceId = parameter;
	}
	public int getRetrySequence()
	{
		return this.retrySequence;
	}
	public void setRetrySequence(int parameter)
	{
		this.retrySequence = parameter;
	}
	public String getRetryUser()
	{
		return this.retryUser;
	}
	public void setRetryUser(String parameter)
	{
		this.retryUser = parameter;
	}
	public String getApplyType()
	{
		return this.applyType;
	}
	public void setApplyType(String parameter)
	{
		this.applyType = parameter;
	}
	public String getApplyStatus()
	{
		return this.applyStatus;
	}
	public void setApplyStatus(String parameter)
	{
		this.applyStatus = parameter;
	}
	public String getApplicationMessages()
	{
		return this.applicationMessages;
	}
	public void setApplicationMessages(String parameter)
	{
		this.applicationMessages = parameter;
	}
	public java.sql.Timestamp getApplyTimestamp()
	{
		return this.applyTimestamp;
	}
	public void setApplyTimestamp(java.sql.Timestamp parameter)
	{
		this.applyTimestamp = parameter;
	}
}