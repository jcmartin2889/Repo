package com.misys.equation.problems;

/**
 * A validation message for validation the EQ4 function bean model
 * 
 * @author perkinj1
 */
public class Message
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: Message.java 6893 2010-04-12 04:00:00Z macdonp1 $";

	public static final int SEVERITY_NONE = 0;
	public static final int SEVERITY_INFO = 1;
	public static final int SEVERITY_WARNING = 2;
	public static final int SEVERITY_ERROR = 3;

	/** The text of the message */
	private String text;
	private int severity = SEVERITY_NONE;
	private ProblemLocation problemLocation;

	public Message(String text, int severity, ProblemLocation problemLocation)
	{
		this.text = text;
		this.severity = severity;
		this.problemLocation = problemLocation;
	}

	/**
	 * @return the message text
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * @return the severity
	 */
	public int getSeverity()
	{
		return severity;
	}

	/**
	 * @return the problem location
	 */
	public ProblemLocation getProblemLocation()
	{
		return problemLocation;
	}

}
