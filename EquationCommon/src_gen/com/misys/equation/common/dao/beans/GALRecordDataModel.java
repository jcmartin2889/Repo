package com.misys.equation.common.dao.beans;

/**
 * GAL Record data-model.
 * 
 * @author deroset
 * 
 */
public class GALRecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "GAL10LF";

	private long sequenceId;
	private int actionSequence;
	private String toUnit;
	private String toServer;
	private String userId;
	private String workstationId;
	private int dayInMonth;
	private int timeHhmmss;
	private int sequenceNumber;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1270638835383l;

	/**
	 * Default constructor
	 */
	public GALRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public GALRecordDataModel(long sequenceId, int actionSequence)
	{
		this.sequenceId = sequenceId;
		this.actionSequence = actionSequence;
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
	public int getActionSequence()
	{
		return this.actionSequence;
	}
	public void setActionSequence(int parameter)
	{
		this.actionSequence = parameter;
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
	public String getUserId()
	{
		return this.userId;
	}
	public void setUserId(String parameter)
	{
		this.userId = parameter;
	}
	public String getWorkstationId()
	{
		return this.workstationId;
	}
	public void setWorkstationId(String parameter)
	{
		this.workstationId = parameter;
	}
	public int getDayInMonth()
	{
		return this.dayInMonth;
	}
	public void setDayInMonth(int parameter)
	{
		this.dayInMonth = parameter;
	}
	public int getTimeHhmmss()
	{
		return this.timeHhmmss;
	}
	public void setTimeHhmmss(int parameter)
	{
		this.timeHhmmss = parameter;
	}
	public int getSequenceNumber()
	{
		return this.sequenceNumber;
	}
	public void setSequenceNumber(int parameter)
	{
		this.sequenceNumber = parameter;
	}
}