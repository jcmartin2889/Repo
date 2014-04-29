// ------------------------------------------------------------------------------
// Copyright Misys IBS
//
// Owner: Des Middlemass
//
// Class: EQException. Exception class to hold multiple messages.
// ------------------------------------------------------------------------------
package com.misys.equation.common.internal.eapi.core;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * An unchecked exception which can hold more than one descriptive message.
 * <P>
 * If constructed with a single String the corresponding Exception constructor is called and the public errorCodes array is empty.
 * <P>
 * If constructed with an array of Strings the default Exception constructor is called and the strings are stored in the public
 * errorCodes array.
 */
public class EQRuntimeException extends RuntimeException
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQRuntimeException.java 7206 2010-05-10 12:13:44Z weddelc1 $";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006 </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";
	/**
	 * Multiple error messages.
	 */
	private final String[] errorCodes;
	/**
	 * Default Constructor.
	 * <P>
	 * No errrorCodes are stored (errorCodes default to a String array with a single null element.)
	 */
	public EQRuntimeException()
	{
		super();
		errorCodes = new String[1];
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
	public EQRuntimeException(String arg0)
	{
		super(arg0);
		errorCodes = new String[1];
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
	public EQRuntimeException(String[] arg0)
	{
		super(arg0[0]);
		this.errorCodes = arg0;
	}
	/**
	 * Construct an exception with the specified cause.
	 * <P>
	 * The corresponding Exception constructor is called.
	 * <P>
	 * No errrorCodes are stored (errorCodes default to a String array with a single null element.)
	 */
	public EQRuntimeException(Throwable cause)
	{
		super(cause);
		errorCodes = new String[1];
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
	public EQRuntimeException(String arg0, Throwable cause)
	{
		super(arg0, cause);
		errorCodes = new String[1];
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
	public EQRuntimeException(String[] arg0, Throwable cause)
	{
		super(arg0[0], cause);
		this.errorCodes = arg0;
	}

	/**
	 * Get multiple error messages.
	 * <P>
	 * Returns a string array containing all of the error messages.
	 * <P>
	 * 
	 * @return all error messages.
	 */
	public String[] getErrorCodes()
	{
		return errorCodes;
	}
	/**
	 * if the specified condition is not met a EqException is thrown with the supplied description.
	 */
	public static void assertException(boolean condition, String description) throws EQRuntimeException
	{
		if (!condition)
		{
			throw new EQRuntimeException(description);
		}
	}
	/**
	 * Prints the stack trace of this exception, including the stack trace of the nested exception, if any, to System.err.
	 */
	@Override
	public void printStackTrace()
	{
		printStackTrace(new PrintWriter(System.err));
	}
	/**
	 * Prints the stack trace of this exception, including the stack trace of the nested exception, if any.
	 * 
	 * @param out
	 *            a <code>PrintStream</code> to which the stack trace should be printed.
	 */
	@Override
	public void printStackTrace(PrintStream out)
	{
		printStackTrace(new PrintWriter(out));
	}
	/**
	 * Prints the stack trace of this exception, including the stack trace of the nested exception, if any.
	 * 
	 * @param out
	 *            a <code>PrintWriter</code> to which the stack trace should be printed.
	 */
	@Override
	public void printStackTrace(PrintWriter out)
	{
		super.printStackTrace(out);
		out.flush();
	}
	/**
	 * Returns the stack trace of this exception in a string, including the stack trace of the nested exception, if any.
	 * 
	 * @return the stack trace of this exception in a string, including the stack trace of the nested exception, if any.
	 */
	public String getStackTraceString()
	{
		return getStackTraceString(this);
	}
	/**
	 * Returns the stack trace of the specified exception in a string, including the stack trace of the nested exception, if any.
	 * 
	 * @return the stack trace of the specified exception in a string, including the stack trace of the nested exception, if any.
	 */
	public static String getStackTraceString(Throwable e)
	{
		StringWriter trace = new StringWriter();
		PrintWriter out = new PrintWriter(trace);
		e.printStackTrace(out);
		out.close();
		return trace.toString();
	}
}