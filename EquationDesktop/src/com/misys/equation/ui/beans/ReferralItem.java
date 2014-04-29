package com.misys.equation.ui.beans;

public class ReferralItem
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ReferralItem.java 15473 2013-03-08 16:08:07Z whittap1 $";

	// user and option
	private String referredUser;
	private String referredOption;

	// additional information for the session
	private String desktopUser;
	private String desktopSession;
	private String desktopTranId;
	private String supervisor;
	private String screenSetId;
	private String primeScrn;
	private String status;
	private String authLevel;
	private String reason;
	private String offline;
	private String referredDate;
	private String referredTime;
	private String taskType;
	private String optionTitle;
	private String sessionUser;
	private String formattedDate;
	private String formattedTime;
	private String jobDescription;
	/** The user Id shown when displaying the referral */
	private String displayUser;

	public ReferralItem()
	{
	}

	/**
	 * Return the option to be authorised
	 * 
	 * @return the option to be authorised
	 */
	public String getReferredOption()
	{
		return referredOption;
	}

	/**
	 * Set the option to be authorised
	 * 
	 * @param referredOption
	 *            - the option to be authorised
	 */
	public void setReferredOption(String referredOption)
	{
		this.referredOption = referredOption;
	}

	/**
	 * Return the user requiring authorisation
	 * 
	 * @return the user requiring authorisation
	 */
	public String getReferredUser()
	{
		return referredUser;
	}

	/**
	 * Set the user requiring authorisation
	 * 
	 * @param referredUser
	 *            - the user requiring authorisation
	 */
	public void setReferredUser(String referredUser)
	{
		this.referredUser = referredUser;
	}

	/**
	 * Return the desktop user requiring authorisation
	 * 
	 * @return the desktop user requiring authorisation
	 */
	public String getDesktopUser()
	{
		return desktopUser;
	}

	/**
	 * Sets the desktop user requiring authorisation
	 * 
	 * @param desktopUser
	 *            the desktop user requiring authorisation
	 */
	public void setDesktopUser(String desktopUser)
	{
		this.desktopUser = desktopUser;
	}

	/**
	 * Return the session id requiring authorisation
	 * 
	 * @return the session id requiring authorisation
	 * 
	 */
	public String getDesktopSession()
	{
		return desktopSession;
	}

	/**
	 * Set the session id requiring authorisation
	 * 
	 * @param desktopSession
	 *            - the session id requiring authorisation
	 */
	public void setDesktopSession(String desktopSession)
	{
		this.desktopSession = desktopSession;
	}

	/**
	 * Return the transaction id requiring authorisation
	 * 
	 * @return the transaction id requiring authorisation
	 */
	public String getDesktopTranId()
	{
		return desktopTranId;
	}

	/**
	 * Set the transaction id requiring authorisation
	 * 
	 * @param desktopTranId
	 *            - the transaction id requiring authorisation
	 */
	public void setDesktopTranId(String desktopTranId)
	{
		this.desktopTranId = desktopTranId;
	}

	/**
	 * Return the screen set id
	 * 
	 * @return the screen set id
	 */
	public String getScreenSetId()
	{
		return screenSetId;
	}

	/**
	 * Set the screen set id
	 * 
	 * @param screenSetId
	 *            - the screen set id
	 */
	public void setScreenSetId(String screenSetId)
	{
		this.screenSetId = screenSetId;
	}

	/**
	 * Return the screen
	 * 
	 * @return the screen
	 */
	public String getPrimeScrn()
	{
		return primeScrn;
	}

	/**
	 * Set the screen
	 * 
	 * @param primeScrn
	 *            -
	 */
	public void setPrimeScrn(String primeScrn)
	{
		this.primeScrn = primeScrn;
	}

	/**
	 * Return the supervisor
	 * 
	 * @return the supervisor
	 */
	public String getSupervisor()
	{
		return supervisor;
	}

	/**
	 * Set the supervisor
	 * 
	 * @param supervisor
	 *            - supervisor
	 */
	public void setSupervisor(String supervisor)
	{
		this.supervisor = supervisor;
	}

	/**
	 * Return the status
	 * 
	 * @return the status
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * Set the status
	 * 
	 * @param status
	 *            - the status
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

	/**
	 * Return the authorisation level
	 * 
	 * @return the authorisation level
	 */
	public String getAuthLevel()
	{
		return authLevel;
	}

	/**
	 * Set the authorisation level
	 * 
	 * @param authLevel
	 *            - the authorisation level
	 */
	public void setAuthLevel(String authLevel)
	{
		this.authLevel = authLevel;
	}

	/**
	 * Return the reason of rejection
	 * 
	 * @return reason of rejection
	 */
	public String getReason()
	{
		return reason;
	}

	/**
	 * Set the reason of rejection
	 * 
	 * @param reason
	 *            - the reason of rejection
	 */
	public void setReason(String reason)
	{
		this.reason = reason;
	}

	/**
	 * Return the offline flag
	 * 
	 * @return the offline flag
	 */
	public String getOffline()
	{
		return offline;
	}

	/**
	 * Set the offline flag
	 * 
	 * @param offline
	 *            - the offline flag
	 */
	public void setOffline(String offline)
	{
		this.offline = offline;
	}

	/**
	 * Set the referred date
	 * 
	 * @param referredDate
	 *            - the referred date
	 */
	public void setReferredDate(String referredDate)
	{
		this.referredDate = referredDate;
	}

	/**
	 * Return the referred date
	 * 
	 * @return the referred date
	 */
	public String getReferredDate()
	{
		return referredDate;
	}

	/**
	 * Set the referred time
	 * 
	 * @param referredTime
	 *            - the referred time
	 */
	public void setReferredTime(String referredTime)
	{
		this.referredTime = referredTime;
	}

	/**
	 * Return the referred time
	 * 
	 * @return the referred time
	 */
	public String getReferredTime()
	{
		return referredTime;
	}

	/**
	 * Set the task type for LRP tasks
	 * 
	 * @param taskType
	 *            - the task type
	 */
	public void setTaskType(String taskType)
	{
		this.taskType = taskType;
	}

	/**
	 * Return the task type for LRP tasks
	 * 
	 * @return the task type for LRP tasks
	 */
	public String getTaskType()
	{
		return taskType;
	}

	/**
	 * Return the option title
	 * 
	 * @return the option title
	 */
	public String getOptionTitle()
	{
		return optionTitle;
	}

	/**
	 * Set the option title
	 * 
	 * @param optionTitle
	 *            - the option title
	 */
	public void setOptionTitle(String optionTitle)
	{
		this.optionTitle = optionTitle;
	}

	/**
	 * Return the user name of the current session
	 * 
	 * @return the user name
	 */
	public String getSessionUser()
	{
		return sessionUser;
	}

	/**
	 * Set the user name of the current session
	 * 
	 * @param sessionUser
	 *            - the user name
	 */
	public void setSessionUser(String sessionUser)
	{
		this.sessionUser = sessionUser;
	}

	/**
	 * Set the formatted date
	 * 
	 * @param formattedDate
	 *            - the formatted date
	 */
	public void setFormattedDate(String formattedDate)
	{
		this.formattedDate = formattedDate;
	}

	/**
	 * Return the formatted date
	 * 
	 * @return the formatted date
	 */
	public String getFormattedDate()
	{
		return formattedDate;
	}

	/**
	 * Set the formatted time
	 * 
	 * @param formattedTime
	 *            - the formatted time
	 */
	public void setFormattedTime(String formattedTime)
	{
		this.formattedTime = formattedTime;
	}

	/**
	 * Return the formatted time
	 * 
	 * @return the formatted time
	 */
	public String getFormattedTime()
	{
		return formattedTime;
	}

	/**
	 * Set the job description
	 * 
	 * @param jobDescription
	 *            - the job description
	 */
	public void setJobDescription(String jobDescription)
	{
		this.jobDescription = jobDescription;
	}

	/**
	 * Return the job description
	 * 
	 * @return the job description
	 */
	public String getJobDescription()
	{
		return jobDescription;
	}

	/**
	 * @return the user to show
	 */
	public String getDisplayUser()
	{
		return displayUser;
	}

	/**
	 * @param displayUser
	 *            the User ID to show
	 */
	public void setDisplayUser(String displayUser)
	{
		this.displayUser = displayUser;
	}
}