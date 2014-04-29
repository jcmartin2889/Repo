package com.misys.equation.dataaccess.connectionpooling.impl;

import java.io.File;

/**
 * This class will hold Jndi-connection-pool information which is common for the whole application.
 */
public class JndiConnectionPoolConfiguration
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: JndiConnectionPoolConfiguration.java 8910 2010-08-26 15:25:20Z MACDONP1 $";

	private static JndiConnectionPoolConfiguration currentInstance;

	private boolean hasAJndiConnectionPool = false;

	private JndiConnectionPoolConfiguration()
	{

	}

	/**
	 * This method will return the only <code>JndiConnectionPoolConfiguration</code> instance.
	 * 
	 * @return the only <code>JndiConnectionPoolConfiguration</code>.
	 */
	public static synchronized JndiConnectionPoolConfiguration getInstance()
	{
		if (currentInstance == null)
		{
			currentInstance = new JndiConnectionPoolConfiguration();
		}
		return currentInstance;
	}

	public boolean isHasAJndiConnectionPool()
	{
		return hasAJndiConnectionPool;
	}

	public void checkIfJndiConnectionPoolIsSet(String filePath)
	{
		File file = new File(filePath);
		hasAJndiConnectionPool = file.exists();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}
}