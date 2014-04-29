package com.misys.equation.dataaccess.connectionpooling;

import com.misys.equation.common.internal.eapi.core.EQException;

public class EQDataAccessException extends EQException
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQDataAccessException.java 7607 2010-06-01 17:05:56Z MACDONP1 $";
	/**
	 * 
	 */
	private static final long serialVersionUID = -7165517430035900779L;

	public EQDataAccessException(String message)
	{
		super(message);
	}

	public EQDataAccessException(Throwable cause)
	{
		super(cause);
	}
}
