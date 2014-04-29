// ------------------------------------------------------------------------------
// Copyright Misys IBS
//
// Owner: Des Middlemass
//
// Class: EQException. Exception class to hold multiple messages.
// ------------------------------------------------------------------------------
package com.misys.equation.common.internal.eapi.core;

/**
 * An exception which is thrown if there is a connection error.
 * 
 * This is a RuntimeException. A UI Error Dialog should be shown at the time this error is thrown.
 */
public class EQConnectionException extends Exception
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQConnectionException.java 8910 2010-08-26 15:25:20Z MACDONP1 $";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor.
	 * <P>
	 * No errrorCodes are stored (errorCodes default to a String array with a single null element.)
	 */
	@SuppressWarnings("unused")
	private EQConnectionException()
	{
	}
	/**
	 * Constructor with an EQException.
	 * <P>
	 * The corresponding Exception constructor is called.
	 * <P>
	 * 
	 * @param EQException
	 */
	public EQConnectionException(EQException e)
	{
		super(e);
	}
	/**
	 * Constructor with a single message.
	 * <P>
	 * The corresponding Exception constructor is called.
	 * <P>
	 * 
	 * @param String
	 *            an error message.
	 */
	public EQConnectionException(String cause)
	{
		super(cause);
	}
}
