package com.misys.equation.common.globalprocessing;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400JPing;

/**
 * This class will ping the passed system and check if the system is available.
 */
public class AS400Ping
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AS400Ping.java 11001 2011-05-19 14:03:00Z MACDONP1 $";
	private final String systemName;

	public AS400Ping(String systemName)
	{
		this.systemName = systemName;
	}

	/**
	 * this method will ping the system and find out if the system is available.
	 * 
	 * @return true will be returned if the system is available.
	 */
	public boolean pingSystem()
	{
		AS400JPing pingObj = new AS400JPing(systemName, AS400.COMMAND, false);
		boolean result = pingObj.ping();
		return result;
	}
}
