package com.misys.equation.function.beans;

import org.apache.commons.lang.StringEscapeUtils;

/*
 * This class is used to store keys needed to launch a menu option with context or to launch a referral. This class will normally be
 * null in functionHandlerData unless there is drill down in UXP to a WebFacing function. Function.jsp will use nextAction values in
 * functionHandlerData and set newAction to null in functionHandlerData after doing launch of process in new page.
 */
public class NextAction
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: NextAction.java 16593 2013-06-24 15:32:19Z perkinj1 $";

	/** the type of action "routeToOption2" or "routeToSessionRestore2" */
	private String actionType;

	/*
	 * the type of control 1 - Restore a work in progress session 2 - Restore a session for the supervisor override 3 - Restore a
	 * session that has been authorised/declined by the supervisor 4 - Restore a LRP task
	 */
	private String control;

	/** the type of option ("EQ" or "WF*) */
	private String optionType;

	/** the option */
	private String optionId;

	/** the option description */
	private String optionDescription;

	/** the key field data used by the option */
	private String context;

	/** the session id of the referral */
	private String sessionId;

	/** the transaction id of the referral */
	private String transactionId;

	/** the original full user id of the referral */
	private String originalFullUser;

	/** the status of the referral */
	private String status;

	/** the authority type of the referral */
	private String authorityLevel;

	/** the screen set id of the referral */
	private int screenSetId;

	/** the screen number of the referral */
	private int screenNumber;
	/**
	 * Disable default constructor
	 */
	@SuppressWarnings("unused")
	private NextAction()
	{
	}

	/**
	 * Construct a next action
	 */
	public NextAction(String actionType, String control, String optionType, String optionId, String optionDescription,
					String context, String sessionId, String transactionId, String originalFullUser, String status,
					String authorityLevel, int screenSetId, int screenNumber)
	{
		this.actionType = actionType;
		this.control = control;
		this.optionType = optionType;
		this.optionId = optionId;
		this.optionDescription = optionDescription;
		this.context = context;
		this.sessionId = sessionId;
		this.transactionId = transactionId;
		this.originalFullUser = originalFullUser;
		this.status = status;
		this.authorityLevel = authorityLevel;
		this.screenSetId = screenSetId;
		this.screenNumber = screenNumber;
	}

	public String getActionType()
	{
		return actionType;
	}

	public void setActionType(String actionType)
	{
		this.actionType = actionType;
	}

	public String getControl()
	{
		return control;
	}

	public void setControl(String control)
	{
		this.control = control;
	}

	public String getOptionType()
	{
		return optionType;
	}

	public void setOptionType(String optionType)
	{
		this.optionType = optionType;
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
	 * Return the option description
	 * 
	 * @return the option description
	 */
	public String getOptionDescription()
	{
		return optionDescription;
	}

	/**
	 * Set the option description
	 * 
	 * @param optionDescription
	 *            - the option description
	 */
	public void setOptionDescription(String optionDescription)
	{
		this.optionDescription = optionDescription;
	}

	/**
	 * Return the context
	 * 
	 * @return the context
	 */
	public String getContext()
	{
		return StringEscapeUtils.escapeJavaScript(context);
	}

	/**
	 * Set the context
	 * 
	 * @param context
	 *            - the context
	 */
	public void setContext(String context)
	{
		this.context = context;
	}

	public String getSessionId()
	{
		return sessionId;
	}

	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}

	public String getTransactionId()
	{
		return transactionId;
	}

	public void setTransactionId(String transactionId)
	{
		this.transactionId = transactionId;
	}

	public String getOriginalFullUser()
	{
		return originalFullUser;
	}

	public void setOriginalFullUser(String originalFullUser)
	{
		this.originalFullUser = originalFullUser;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getAuthorityLevel()
	{
		return authorityLevel;
	}

	public void setAuthorityLevel(String authorityLevel)
	{
		this.authorityLevel = authorityLevel;
	}

	public int getScreenSetId()
	{
		return screenSetId;
	}

	public void setScreenSetId(int screenSetId)
	{
		this.screenSetId = screenSetId;
	}

	public int getScreenNumber()
	{
		return screenNumber;
	}

	public void setScreenNumber(int screenNumber)
	{
		this.screenNumber = screenNumber;
	}

}
