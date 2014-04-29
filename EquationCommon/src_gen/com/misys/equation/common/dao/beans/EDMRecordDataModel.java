package com.misys.equation.common.dao.beans;

/**
 * EDM Record data-model.
 * 
 * @author deroset
 */
public class EDMRecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "EDMPF";

	private String startTime;
	private String phase;
	private String process;
	private String processDescription;
	private String processStatus;
	private String jobName;
	private String jobNumber;
	private String jobUser;
	private String type;
	private String identifier;
	private int processingDate;
	private String endTime;
	private String subStatusDescription;
	private String message;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1285137724474l;

	/**
	 * Default constructor
	 */
	public EDMRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public String getStartTime()
	{
		return this.startTime;
	}
	public void setStartTime(String parameter)
	{
		this.startTime = parameter;
	}
	public String getPhase()
	{
		return this.phase;
	}
	public void setPhase(String parameter)
	{
		this.phase = parameter;
	}
	public String getProcess()
	{
		return this.process;
	}
	public void setProcess(String parameter)
	{
		this.process = parameter;
	}
	public String getProcessDescription()
	{
		return this.processDescription;
	}
	public void setProcessDescription(String parameter)
	{
		this.processDescription = parameter;
	}
	public String getProcessStatus()
	{
		return this.processStatus;
	}
	public void setProcessStatus(String parameter)
	{
		this.processStatus = parameter;
	}
	public String getJobName()
	{
		return this.jobName;
	}
	public void setJobName(String parameter)
	{
		this.jobName = parameter;
	}
	public String getJobNumber()
	{
		return this.jobNumber;
	}
	public void setJobNumber(String parameter)
	{
		this.jobNumber = parameter;
	}
	public String getJobUser()
	{
		return this.jobUser;
	}
	public void setJobUser(String parameter)
	{
		this.jobUser = parameter;
	}
	public String getType()
	{
		return this.type;
	}
	public void setType(String parameter)
	{
		this.type = parameter;
	}
	public String getIdentifier()
	{
		return this.identifier;
	}
	public void setIdentifier(String parameter)
	{
		this.identifier = parameter;
	}
	public int getProcessingDate()
	{
		return this.processingDate;
	}
	public void setProcessingDate(int parameter)
	{
		this.processingDate = parameter;
	}
	public String getEndTime()
	{
		return this.endTime;
	}
	public void setEndTime(String parameter)
	{
		this.endTime = parameter;
	}
	public String getSubStatusDescription()
	{
		return this.subStatusDescription;
	}
	public void setSubStatusDescription(String parameter)
	{
		this.subStatusDescription = parameter;
	}
	public String getMessage()
	{
		return this.message;
	}
	public void setMessage(String parameter)
	{
		this.message = parameter;
	}
}