package com.misys.equation.common.globalprocessing;

import com.misys.equation.common.internal.eapi.core.EQException;

/**
 * Thrown when a record required to complete the operation.
 * 
 * @author berzosa
 */
@SuppressWarnings("serial")
public class RecordNotFoundException extends EQException
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RecordNotFoundException.java 9210 2010-09-17 15:31:21Z deroset $";

	/**
	 * Indicates that the operation could not find a required record.
	 * 
	 * @param message
	 *            The message to pass to the superclass Exception.
	 */
	public RecordNotFoundException(String message)
	{
		super(message);
	}
}
