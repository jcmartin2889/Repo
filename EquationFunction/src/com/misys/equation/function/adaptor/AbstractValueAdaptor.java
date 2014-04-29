package com.misys.equation.function.adaptor;

import com.misys.equation.function.runtime.UserData;

public abstract class AbstractValueAdaptor implements IValueAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractValueAdaptor.java 6793 2010-03-31 12:10:20Z deroset $";
	/**
	 * Return the value of the field
	 * 
	 * @param curValue
	 *            - the current value of the field
	 * @param userData
	 *            - the User Data
	 * 
	 * @return the value of the field (or null if the returned value is to be ignored)
	 */
	public String getValue(String curValue, UserData userData)
	{
		return null;
	}
}
