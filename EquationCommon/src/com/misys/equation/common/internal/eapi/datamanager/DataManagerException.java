package com.misys.equation.common.internal.eapi.datamanager;

import com.misys.equation.common.internal.eapi.core.EQException;

public class DataManagerException extends EQException
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DataManagerException.java 4646 2009-09-07 16:28:30Z weddelc1 $";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006 </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";

	/**
	 * Stores the exception related information.
	 */
	private String errorMsg;

	/**
	 * Constructor for DataManagerException
	 */
	public DataManagerException()
	{
		super();
	}

	/**
	 * Constructor which takes in the exception reason
	 * 
	 * @param msg
	 *            - the error message
	 */
	public DataManagerException(String msg)
	{
		errorMsg = msg;
	}

	/**
	 * Writes the error to the system output file.
	 */
	public void log()
	{
	}

	/**
	 * Logs the exception information with the stack trace.
	 */
	public void logStackTrace()
	{
		this.printStackTrace();
	}

	/**
	 * Return the error message
	 * 
	 * @return the error message
	 */
	public String getErrorMsg()
	{
		return errorMsg;
	}

	/**
	 * Set the error message
	 * 
	 * @param errorMsg
	 *            - the error message
	 */
	public void setErrorMsg(String errorMsg)
	{
		this.errorMsg = errorMsg;
	}

}
