package com.misys.equation.common.dao.beans;

/**
 * GAU Record data-model.
 * 
 * @author deroset
 * 
 */
public class GAURecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "GAU10LF";

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
	private long unitDataSequenceNumber;
	private String globalLayerStatus;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1275047167602l;

	/**
	 * Default constructor
	 */
	public GAURecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public GAURecordDataModel(long sequenceId)
	{
		this.sequenceId = sequenceId;
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
	public long getUnitDataSequenceNumber()
	{
		return this.unitDataSequenceNumber;
	}
	public void setUnitDataSequenceNumber(long parameter)
	{
		this.unitDataSequenceNumber = parameter;
	}
	public String getGlobalLayerStatus()
	{
		return this.globalLayerStatus;
	}
	public void setGlobalLayerStatus(String parameter)
	{
		this.globalLayerStatus = parameter;
	}
}