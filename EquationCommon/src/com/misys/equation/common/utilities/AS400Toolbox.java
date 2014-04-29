package com.misys.equation.common.utilities;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400ConnectionPool;
import com.ibm.as400.access.ConnectionPoolException;
import com.ibm.as400.access.SecureAS400;
import com.misys.equation.common.access.EquationCommonContext;

/**
 * AS400 related helper methods
 */
public class AS400Toolbox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AS400Toolbox.java 15470 2013-03-08 15:56:33Z whittap1 $";

	/** Cached configuration property indicating whether to use secure AS400 communications (SSL) */
	private static Boolean isSecure;

	/**
	 * Indicates whether communications with the IBM i should be secure
	 * 
	 * This method is intended for use both by methods within this class, and by other classes which set the Secure property on
	 * DataSource instances.
	 * 
	 * @return boolean true if secure communications should be used
	 */
	public static boolean isSecure()
	{
		if (isSecure == null)
		{
			isSecure = "true".equals(EquationCommonContext.getConfigProperty("eq.secure.as400"));
		}
		return isSecure;
	}

	/**
	 * Obtains a new AS400 or SecureAS400 object
	 * 
	 * @return AS400 or SecureAS400 object
	 */
	public static AS400 getAS400()
	{
		return isSecure() ? new SecureAS400() : new AS400();
	}

	/**
	 * Obtains a new AS400 or SecureAS400 object
	 * 
	 * @param systemId
	 *            AS400 system name
	 * 
	 * @return AS400 or SecureAS400 object
	 */
	public static AS400 getAS400(String systemId)
	{
		return isSecure() ? new SecureAS400(systemId) : new AS400(systemId);
	}

	/**
	 * Obtains a new AS400 or SecureAS400 object
	 * 
	 * @param systemId
	 *            AS400 system name
	 * @param username
	 *            user name
	 * @param password
	 *            password
	 * 
	 * @return AS400 or SecureAS400 object
	 */
	public static AS400 getAS400(String systemId, String username, String password)
	{
		return isSecure() ? new SecureAS400(systemId, username, password) : new AS400(systemId, username, password);
	}

	/**
	 * Obtains a new AS400 or SecureAS400 object
	 * 
	 * @param as400
	 *            Another AS400 instance
	 * 
	 * @return AS400 or SecureAS400 object
	 */
	public static AS400 getAS400(AS400 as400)
	{
		return isSecure() ? new SecureAS400(as400) : new AS400(as400);
	}

	/**
	 * Obtains a new AS400 or SecureAS400 object from an AS400ConnectionPool
	 * 
	 * @param pool
	 *            An AS400ConnectionPool (a pool of AS400 objects)
	 * @param systemId
	 *            AS400 system name
	 * @param username
	 *            user name
	 * @param password
	 *            password
	 * @param service
	 *            The service to connect. See the service number constants defined by AS400 class
	 * 
	 * @return AS400 or SecureAS400 object
	 * 
	 * @throws ConnectionPoolException
	 */
	public static AS400 getConnection(AS400ConnectionPool pool, String systemId, String username, String password, int service)
					throws ConnectionPoolException
	{
		AS400 result = null;
		if (isSecure())
		{
			result = pool.getSecureConnection(systemId, username, password, service);
		}
		else
		{
			result = pool.getConnection(systemId, username, password, service);
		}
		return result;
	}
}
