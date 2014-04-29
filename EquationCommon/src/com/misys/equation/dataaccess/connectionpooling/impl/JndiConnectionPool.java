package com.misys.equation.dataaccess.connectionpooling.impl;

import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.dataaccess.connectionpooling.AbstractConnectionPool;
import com.misys.equation.dataaccess.connectionpooling.EQDataAccessException;

/**
 * This is a JNDI based connection pool which differs in the way the data source is initialised.
 */
public class JndiConnectionPool extends AbstractConnectionPool
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: JndiConnectionPool.java 15669 2013-04-23 11:37:15Z abalosd1 $";

	// Static keys defined to identify properties in the map ...
	public static final String CONN_PROP_KEY_INIT_CONTEXT = "INIT";
	public static final String CONN_PROP_KEY_ENV_CONTEXT = "ENV";

	/**
	 * Constructor which calls the super constructor.
	 * 
	 * @param properties
	 * @param checkAuthorisaion
	 * @throws EQDataAccessException
	 */
	public JndiConnectionPool(final Map<String, String> properties, final boolean checkAuthorisaion) throws EQDataAccessException
	{
		super(properties, checkAuthorisaion);
	}

	/**
	 * Constructor which calls the super constructor accepting a DataSource
	 * 
	 * @param properties
	 * @param checkAuthorisaion
	 * @param source
	 */
	public JndiConnectionPool(Map<String, String> properties, boolean checkAuthorisaion, DataSource source)
	{
		super(properties, checkAuthorisaion, source);
	}

	/**
	 * Initialises the data source. In this case it simply looks up the data source using JNDI. The JNDI data source is passed read
	 * from the map of properties.
	 */
	@Override
	protected void initialiseDataSource() throws EQDataAccessException
	{
		if (isATomcatApplicationServer())
		{
			lookupATomcatDataSource();
		}
		else if (isAWebSphereApplicationServer())
		{
			lookupAWasDataSource();
		}
		else if (isAJBossApplicationServer())
		{
			lookupAJBossDataSource();
		}
		else
		{
			LOG.error("Invalid Application Server Type Specified");
		}
	}

	@Override
	protected void destroyConnectionPool()
	{

	}

	/**
	 * This method will check if the current application server is Tomcat.
	 * 
	 * @return true if the current application server is Tomcat.
	 */
	public static boolean isATomcatApplicationServer()
	{
		return Toolbox.isATomcatApplicationServer();
	}

	/**
	 * This method will check if the current application server is WebSphere.
	 * 
	 * @return true if the current application server is WebSphere.
	 */
	public static boolean isAWebSphereApplicationServer()
	{
		return Toolbox.isAWebSphereApplicationServer();
	}

	/**
	 * This method will check if the current application server is JBOSS.
	 * 
	 * @return true if the current application server is JBOSS.
	 */
	public static boolean isAJBossApplicationServer()
	{
		return Toolbox.isAJBossApplicationServer();
	}

	/**
	 * Checks the initial context is valid - i.e that we are running within an Application Server environment
	 * 
	 * @param initialContext
	 *            - the initial context string to lookup
	 * @return true if JNDI lookup is available, otherwise false.
	 */
	public static boolean checkJndiAvailable()
	{
		boolean available = true;
		try
		{
			if (isATomcatApplicationServer() || isAJBossApplicationServer())
			{
				String envContextString = "java:comp/env";
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup(envContextString);
				// envContext.lookup("jdbc/DEFAULT-DS");
			}
			else
			{
				Hashtable<String, String> env = new Hashtable<String, String>();
				env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
				new InitialContext(env);
			}
		}
		catch (Throwable e)
		{
			available = false;
		}
		return available;
	}

	/**
	 * This method will lookup a data-source on a Tomcat application server.
	 */
	private void lookupATomcatDataSource() throws EQDataAccessException
	{
		try
		{
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup(properties.get(CONN_PROP_KEY_INIT_CONTEXT));
			this.dataSource = (DataSource) envContext.lookup(properties.get(CONN_PROP_KEY_ENV_CONTEXT));
		}
		catch (NamingException e)
		{
			throw new EQDataAccessException("Error looking up Tomcat datasource " + properties.get(CONN_PROP_KEY_ENV_CONTEXT));
		}
	}

	/**
	 * This method will lookup a data-source on a JBoss application server.
	 */
	private void lookupAJBossDataSource() throws EQDataAccessException
	{
		try
		{
			Context initContext = new InitialContext();
			this.dataSource = (DataSource) initContext.lookup("java:" + properties.get(CONN_PROP_KEY_ENV_CONTEXT));
		}
		catch (NamingException e)
		{
			throw new EQDataAccessException("Error looking up JBoss datasource " + properties.get(CONN_PROP_KEY_ENV_CONTEXT));
		}
	}

	/**
	 * This method will lookup a data-source on a Websphere application server.
	 */
	private void lookupAWasDataSource() throws EQDataAccessException
	{
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");

		try
		{
			Context envContext = new InitialContext(env);
			this.dataSource = (DataSource) envContext.lookup(properties.get(CONN_PROP_KEY_ENV_CONTEXT));
		}
		catch (NamingException e)
		{
			throw new EQDataAccessException("Error looking up WAS datasource " + properties.get(CONN_PROP_KEY_ENV_CONTEXT));
		}
	}
}