// ------------------------------------------------------------------------------
// Copyright Misys IBS
//
// Owner: Des Middlemass
//
// Class: EQException. Exception class to hold multiple messages.
// ------------------------------------------------------------------------------
package com.misys.equation.common.internal.eapi.core;

/**
 * An exception which can hold more than one descriptive message.
 * <P>
 * If constructed with a single String the corresponding Exception constructor is called and the public errorCodes array is empty.
 * <P>
 * If constructed with an array of Strings the default Exception constructor is called and the strings are stored in the public
 * errorCodes array.
 */
public class EQActionErrorException extends EQException
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQActionErrorException.java 15470 2013-03-08 15:56:33Z whittap1 $";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006 </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";
	private String actionError;
	private String parameter;

	public EQActionErrorException()
	{
		super();
	}

	/**
	 * Constructs a new <code>EqException</code> containing a description string, a nested <code>Exception</code> and an
	 * <code>ActionError</code>.
	 * 
	 * @param reason
	 *            a string describing the reason for the exception.
	 * @param nested
	 *            a <code>Throwable</code> to be nested within this <code>EqException</code>.
	 */
	public EQActionErrorException(String reason, Throwable nested, String aError)
	{
		super(reason, nested);
		this.actionError = aError;
		this.parameter = "";
	}

	/**
	 * Constructs a new <code>EqException</code> containing a description string, a nested <code>Exception</code>, an
	 * <code>ActionError</code> and <code> Parameter1 </code>
	 * 
	 * @param reason
	 *            a string describing the reason for the exception.
	 * @param nested
	 *            a <code>Throwable</code> to be nested within this <code>EqException</code>.
	 */
	public EQActionErrorException(String reason, Throwable nested, String aError, String parameter)
	{
		super(reason, nested);
		this.actionError = aError;
		this.parameter = parameter;
	}

	/**
	 * Constructs a new <code>EqException</code> containing a description string and an <code>ActionError</code>.
	 * 
	 * @param reason
	 *            a string describing the reason for the exception.
	 * @param nested
	 *            a <code>Throwable</code> to be nested within this <code>EqException</code>.
	 */
	public EQActionErrorException(String reason, String aError)
	{
		super(reason);
		this.actionError = aError;
		this.parameter = "";
	}

	/**
	 * Return the action error
	 * 
	 * @return the action error
	 */
	public String getActionError()
	{
		return actionError;
	}

	/**
	 * Return the parameter
	 * 
	 * @return the parameter
	 */
	public String getParameter()
	{
		return parameter;
	}

	/**
	 * Overridden to return the action error message id
	 */
	@Override
	public String getMessage()
	{
		return this.actionError;
	}

	/**
	 * Overridden to return the action error message id
	 */
	@Override
	public String getLocalizedMessage()
	{
		return this.actionError;
	}
}
