package com.misys.equation.common.dao.beans;

import com.misys.equation.common.utilities.Toolbox;

/**
 * IPV Record data-model.
 */
public class IPVRecordDataModel extends AbsRecord
{
	public final static String RECORD_NAME = "IPVPF";

	private String referenceId;
	private String applicationId;
	private int sequence;
	private int createDate;
	private int ccLinkTime;
	private int ccLinkSeqNo;
	private int jobNumber;
	private String userId;
	private String optionId;
	private int processedDate;
	private int expiryDate;
	private byte[] response;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1271749368778l;

	/**
	 * Default constructor
	 */
	public IPVRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public IPVRecordDataModel(String referenceId, String applicationId, int sequence)
	{
		this.referenceId = referenceId;
		this.applicationId = applicationId;
		this.sequence = sequence;
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	/**
	 * Return the reference id
	 * 
	 * @return the reference id
	 */
	public String getReferenceId()
	{
		return referenceId;
	}

	/**
	 * Set the the reference id
	 * 
	 * @param referenceId
	 *            - the reference id
	 */
	public void setReferenceId(String referenceId)
	{
		this.referenceId = Toolbox.trim(referenceId, 64);
	}

	/**
	 * Return the application id
	 * 
	 * @return the application id
	 */
	public String getApplicationId()
	{
		return applicationId;
	}

	/**
	 * Set the application id
	 * 
	 * @param applicationId
	 *            - the application id
	 */
	public void setApplicationId(String applicationId)
	{
		this.applicationId = Toolbox.trim(applicationId, 3);
	}

	/**
	 * Return the sequence number
	 * 
	 * @return the sequence number
	 */
	public int getSequence()
	{
		return sequence;
	}

	/**
	 * Set the sequence number
	 * 
	 * @param sequence
	 *            - the sequence number
	 */
	public void setSequence(int sequence)
	{
		this.sequence = sequence;
	}

	/**
	 * Return the creation date
	 * 
	 * @return the creation date
	 */
	public int getCreateDate()
	{
		return createDate;
	}

	/**
	 * Set the creation date
	 * 
	 * @param createDate
	 *            - the creation date
	 */
	public void setCreateDate(int createDate)
	{
		this.createDate = createDate;
	}

	/**
	 * Return the CC link time
	 * 
	 * @return the CC link time
	 */
	public int getCcLinkTime()
	{
		return ccLinkTime;
	}

	/**
	 * Set the CC link time
	 * 
	 * @param ccLinkTime
	 *            - the CC link time
	 */
	public void setCcLinkTime(int ccLinkTime)
	{
		this.ccLinkTime = ccLinkTime;
	}

	/**
	 * Return the CC link sequence number
	 * 
	 * @return the CC link sequence number
	 */
	public int getCcLinkSeqNo()
	{
		return ccLinkSeqNo;
	}

	/**
	 * Set the CC link sequence number
	 * 
	 * @param ccLinkSequenceNumber
	 *            - the CC link sequence number
	 */
	public void setCcLinkSeqNo(int ccLinkSeqNo)
	{
		this.ccLinkSeqNo = ccLinkSeqNo;
	}

	/**
	 * Return the job number
	 * 
	 * @return the job number
	 */
	public int getJobNumber()
	{
		return jobNumber;
	}

	/**
	 * Set the job number
	 * 
	 * @param jobNumber
	 *            - the job number
	 */
	public void setJobNumber(int jobNumber)
	{
		this.jobNumber = jobNumber;
	}

	/**
	 * Return the user id
	 * 
	 * @return the user id
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * Set the user id
	 * 
	 * @param userId
	 *            - the user id
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	/**
	 * Return the option id
	 * 
	 * @return the option id
	 */
	public String getOptionId()
	{
		return optionId;
	}

	/**
	 * Set the option id
	 * 
	 * @param optionId
	 *            - the option id
	 */
	public void setOptionId(String optionId)
	{
		this.optionId = optionId;
	}

	/**
	 * Return the processed date
	 * 
	 * @return the processed date
	 */
	public int getProcessedDate()
	{
		return processedDate;
	}

	/**
	 * Set the processed date
	 * 
	 * @param processedDate
	 *            - the processed date
	 */
	public void setProcessedDate(int processedDate)
	{
		this.processedDate = processedDate;
	}

	/**
	 * Return the expiry date
	 * 
	 * @return the expiry date
	 */
	public int getExpiryDate()
	{
		return expiryDate;
	}

	/**
	 * Set the expiry date
	 * 
	 * @param expiryDate
	 *            - the expiry date
	 */
	public void setExpiryDate(int expiryDate)
	{
		this.expiryDate = expiryDate;
	}

	/**
	 * Return the response
	 * 
	 * @return the response
	 */
	public byte[] getResponse()
	{
		return response;
	}

	/**
	 * Set the response
	 * 
	 * @param response
	 *            - the response
	 */
	public void setResponse(byte[] response)
	{
		this.response = response;
	}

}