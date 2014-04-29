package com.misys.equation.common.dao.beans;

/**
 * Header Record data-model.
 * 
 * @author barcelr1
 * 
 */
public class HeaderRecordDataModel extends AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: HeaderRecordDataModel.java 8910 2010-08-26 15:25:20Z MACDONP1 $";

	private static final long serialVersionUID = 1L;
	private long sequenceId;
	private String sessionId;
	private String user;
	private java.sql.Timestamp auditTimestamp;
	private String source;
	private String sourceUnit;
	private String sourceServer;
	private int processingDate;
	private String unitDefaultBranch;
	private String optionId;
	private String referenceDetailsType;
	private String referenceDetails;
	private String status;
	private String acknowledgedStatus;
	private String toUnit;
	private String toServer;
	private long propDataSequenceNumber;
	private long unitDatasequenceNumber;
	private String globalLayerStatus;
	private java.sql.Timestamp lastUpdate;
	private String lastAction;
	private int lastRetrySeq;

	/**
	 * Default constructor
	 */
	public HeaderRecordDataModel()
	{
		super();
	}

	public HeaderRecordDataModel(long sequenceId)
	{
		this.sequenceId = sequenceId;
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
	public String getSessionId()
	{
		return this.sessionId;
	}
	public void setSessionId(String parameter)
	{
		this.sessionId = parameter;
	}
	public String getUser()
	{
		return this.user;
	}
	public void setUser(String parameter)
	{
		this.user = parameter;
	}
	public java.sql.Timestamp getAuditTimestamp()
	{
		return this.auditTimestamp;
	}
	public void setAuditTimestamp(java.sql.Timestamp parameter)
	{
		this.auditTimestamp = parameter;
	}
	public String getSource()
	{
		return this.source;
	}
	public void setSource(String parameter)
	{
		this.source = parameter;
	}
	public String getSourceUnit()
	{
		return this.sourceUnit;
	}
	public void setSourceUnit(String parameter)
	{
		this.sourceUnit = parameter;
	}
	public String getSourceServer()
	{
		return this.sourceServer;
	}
	public void setSourceServer(String parameter)
	{
		this.sourceServer = parameter;
	}
	public int getProcessingDate()
	{
		return this.processingDate;
	}
	public void setProcessingDate(int parameter)
	{
		this.processingDate = parameter;
	}
	public String getUnitDefaultBranch()
	{
		return this.unitDefaultBranch;
	}
	public void setUnitDefaultBranch(String parameter)
	{
		this.unitDefaultBranch = parameter;
	}
	public String getOptionId()
	{
		return this.optionId;
	}
	public void setOptionId(String parameter)
	{
		this.optionId = parameter;
	}
	public String getReferenceDetailsType()
	{
		return this.referenceDetailsType;
	}
	public void setReferenceDetailsType(String parameter)
	{
		this.referenceDetailsType = parameter;
	}
	public String getReferenceDetails()
	{
		return this.referenceDetails;
	}
	public void setReferenceDetails(String parameter)
	{
		this.referenceDetails = parameter;
	}
	public String getStatus()
	{
		return this.status;
	}
	public void setStatus(String parameter)
	{
		this.status = parameter;
	}
	public String getAcknowledgedStatus()
	{
		return this.acknowledgedStatus;
	}
	public void setAcknowledgedStatus(String parameter)
	{
		this.acknowledgedStatus = parameter;
	}
	public String getToUnit()
	{
		return this.toUnit;
	}
	public void setToUnit(String parameter)
	{
		this.toUnit = parameter;
	}
	public String getToServer()
	{
		return this.toServer;
	}
	public void setToServer(String parameter)
	{
		this.toServer = parameter;
	}
	public long getPropDataSequenceNumber()
	{
		return this.propDataSequenceNumber;
	}
	public void setPropDataSequenceNumber(long parameter)
	{
		this.propDataSequenceNumber = parameter;
	}
	public long getUnitDatasequenceNumber()
	{
		return this.unitDatasequenceNumber;
	}
	public void setUnitDatasequenceNumber(long parameter)
	{
		this.unitDatasequenceNumber = parameter;
	}
	public String getGlobalLayerStatus()
	{
		return this.globalLayerStatus;
	}
	public void setGlobalLayerStatus(String parameter)
	{
		this.globalLayerStatus = parameter;
	}

	public java.sql.Timestamp getLastUpdate()
	{
		return lastUpdate;
	}

	public void setLastUpdate(java.sql.Timestamp lastUpdate)
	{
		this.lastUpdate = lastUpdate;
	}

	public String getLastAction()
	{
		return lastAction;
	}

	public void setLastAction(String lastAction)
	{
		this.lastAction = lastAction;
	}

	public int getLastRetrySeq()
	{
		return lastRetrySeq;
	}

	public void setLastRetrySeq(int lastRetrySeq)
	{
		this.lastRetrySeq = lastRetrySeq;
	}

}
