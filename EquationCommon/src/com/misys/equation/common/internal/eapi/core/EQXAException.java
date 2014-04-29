// ------------------------------------------------------------------------------
// Copyright Misys IBS
//
// Owner: Des Middlemass
//
// Class: EQXARollback. Exception class to hold multiple messages.
// ------------------------------------------------------------------------------
package com.misys.equation.common.internal.eapi.core;

/**
 * An type of EQXARollback raised to force XA Rollback. BankFusion Transaction Management will only rollback on receiving an
 * Exception.
 */
public class EQXAException extends EQException
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQXAException.java 8481 2010-08-03 18:15:31Z esther.williams $";
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006 </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";
	/**
	 * Default Constructor.
	 * <P>
	 * No errrorCodes are stored (errorCodes default to a String array with a single null element.)
	 */
	public EQXAException()
	{
		super();
	}
	/**
	 * Constructor with a single message.
	 * <P>
	 * The corresponding Exception constructor is called.
	 * <P>
	 * No errorCodes are stored (errorCodes default to a String array with a single null element.)
	 * <P>
	 * 
	 * @param arg0
	 *            an error message.
	 */
	public EQXAException(String arg0)
	{
		super(arg0);
	}
	/**
	 * Constructor with multiple messages.
	 * <P>
	 * The corresponding Exception constructor is called.
	 * <P>
	 * The passed string array is stored in errorCodes.
	 * <P>
	 * 
	 * @param arg0
	 *            an array of messages.
	 */
	public EQXAException(String[] arg0)
	{
		super(arg0[0]);
	}
	/**
	 * Construct an exception with the specified cause.
	 * <P>
	 * The corresponding Exception constructor is called.
	 * <P>
	 * No errrorCodes are stored (errorCodes default to a String array with a single null element.)
	 */
	public EQXAException(Throwable cause)
	{
		super(cause);
	}
	/**
	 * Construct an exception with a single message and the specified cause.
	 * <P>
	 * The corresponding Exception constructor is called.
	 * <P>
	 * No errorCodes are stored (errorCodes default to a String array with a single null element.)
	 * <P>
	 * 
	 * @param arg0
	 *            an error message.
	 */
	public EQXAException(String arg0, Throwable cause)
	{
		super(arg0, cause);
	}
	/**
	 * Constructor with multiple messages and the specified cause.
	 * <P>
	 * The corresponding Exception constructor is called.
	 * <P>
	 * The passed string array is stored in errorCodes.
	 * <P>
	 * 
	 * @param arg0
	 *            an array of messages.
	 */
	public EQXAException(String[] arg0, Throwable cause)
	{
		super(arg0[0], cause);
	}

}
