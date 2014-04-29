/*
 * This sample code is provided by Misys for illustrative purposes only.
 * 
 * These examples have not been thoroughly tested under all conditions.
 * 
 * Misys, therefore, cannot guarantee or imply reliability, serviceability, or function of these programs.
 * 
 * All programs contained herein are provided to you "AS IS" without any warranties of any kind. The implied warranties of
 * non-infringement, merchantability and fitness for a particular purpose are expressly disclaimed.
 */
package com.misys.equation.global.test;

import java.net.InetAddress;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingException;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.internal.eapi.core.EQSession;
import com.misys.equation.common.test.TestEnvironmentApplicationProperties;
import com.misys.equation.common.utilities.ApplicationContextManager;
import com.misys.equation.common.utilities.EquationLogger;

/**
 * This is a helper that provides some testing facilities such as environment setting and connectivity.<br>
 * This class is going to be used by classes whose have testing propose.
 * 
 * @author deroset
 */
public class TestEnvironmentGlobal
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TestEnvironmentGlobal.java 16593 2013-06-24 15:32:19Z perkinj1 $";
	/**
	 * Logger for this class
	 */
	private static final EquationLogger LOG = new EquationLogger(TestEnvironmentGlobal.class);

	private static TestEnvironmentGlobal singletonContext;
	private EquationStandardSession standardSession;
	private EQSession enhancedAPISession;
	private String sessionIdentifier;
	private String system;
	private String inputBranch;
	private String unit;
	private String user;
	private String password;
	private TestEnvironmentApplicationProperties testingApplicationProperties;
	private static InitialContext ic;

	private Hashtable<String, String> optionParameters;
	/*
	 * Constructor
	 */
	private TestEnvironmentGlobal()
	{
		initialiseTestEnvironment();
	}

	/**
	 * This method is going to initialise the TestEnvironment, <br>
	 * the connection properties are going to be set and the setup will be executed.
	 */
	private void initialiseTestEnvironment()
	{
		setupConnection();
		optionParameters = new Hashtable<String, String>();
	}

	/**
	 * This method is going to initialise the TestEnvironment for a non instance Environment, <br>
	 * the connection properties are going to be set and the setup will be executed.
	 */
	public static void setTestEnvironment()
	{
		getTestEnvironment();
	}

	/**
	 * This method is going to get the current data-base connection properties and set class local attributes.
	 */
	public void setupConnection()
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();

		testingApplicationProperties = (TestEnvironmentApplicationProperties) applicationContextManager
						.getBean("TestingApplicationProperties");

		system = testingApplicationProperties.getSystem();
		inputBranch = testingApplicationProperties.getInputBranch();
		unit = testingApplicationProperties.getUnit();
		user = testingApplicationProperties.getUser();
		password = testingApplicationProperties.getPassword();

		// Create initial context
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
		System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");

		try
		{
			ic = new InitialContext();
			setUpLocalJndi();
			setUpGlobalJndi();
		}
		catch (Exception exception)
		{
			LOG.error(exception);
		}
	}

	private void initialiseSession()
	{
		try
		{
			sessionIdentifier = EquationCommonContext.getContext().getEqSession(system, unit, user, password, null,
							InetAddress.getLocalHost().getHostAddress(), EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN,
							EquationCommonContext.SESSION_OTHER_MODE, null);

			standardSession = EquationCommonContext.getContext().getEquationUser(sessionIdentifier).getSession();

			// EquationCommonContext.getContext().getEquationStandardSession(system, unit, user, password);

			// print the AS400 job attribute
			if (LOG.isInfoEnabled())
			{
				LOG.info("TestEnvironment job: " + standardSession);
			}

			standardSession.setBranchId(inputBranch);
			enhancedAPISession = null;
		}
		catch (Exception e)
		{
			LOG.error("systemSetup()", e); //$NON-NLS-1$
		}
	}

	/*
	 * Get the singleton context
	 */
	public static synchronized TestEnvironmentGlobal getTestEnvironment()
	{
		// Create our one and only instance of this class
		if (singletonContext == null)
		{
			singletonContext = new TestEnvironmentGlobal();
		}
		return singletonContext;
	}

	/**
	 * This method will set up the JNDI parameters and bind the Data source resource with the associated name.
	 * 
	 * @throws Exception
	 *             if there is any error and <code>Exception</code> will be thrown.
	 */
	public static void setUpGlobalJndi() throws Exception
	{
		TestPoolConnectionProperties globalPoolConnectionProperties = (TestPoolConnectionProperties) ApplicationContextManager
						.getInstance().getBean("globalPoolConnectionBean");
		try
		{
			// Create initial context
			System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
			System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");

			try
			{
				org.apache.tomcat.dbcp.dbcp.BasicDataSource ds = new org.apache.tomcat.dbcp.dbcp.BasicDataSource();

				ds.setUrl(globalPoolConnectionProperties.getUrl());
				ds.setDriverClassName(globalPoolConnectionProperties.getDriverClassName());
				ds.setUsername(globalPoolConnectionProperties.getUser());
				ds.setPassword(globalPoolConnectionProperties.getPassword());
				ds.setMaxWait(globalPoolConnectionProperties.getMaxWaitIntValue());
				// Set it to a small value to ensure that the test does not run for long.
				ds.setMaxActive(globalPoolConnectionProperties.getMaxConnectionsInt() + 10);
				ds.setTimeBetweenEvictionRunsMillis(globalPoolConnectionProperties.getTimeBetweenEvictionRunsMillisInt());
				ic.bind(globalPoolConnectionProperties.getBind(), ds);
			}
			catch (NameAlreadyBoundException nameAlreadyBoundException)
			{
				if (LOG.isInfoEnabled())
				{
					LOG.info("The name is already bound.", nameAlreadyBoundException);
				}
			}
		}
		catch (NamingException namingException)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(" There was a problem trying to get the pool connection.", namingException);
			}
		}
	}

	/**
	 * This method will set up the JNDI parameters and bind the Data source resource with the associated name.
	 * 
	 * @throws Exception
	 *             if there is any error and <code>Exception</code> will be thrown.
	 */
	public static void setUpLocalJndi() throws Exception
	{
		LocalPoolConnectionProperties poolConnectionProperties = (LocalPoolConnectionProperties) ApplicationContextManager
						.getInstance().getBean("localPoolConnectionBean");
		try
		{
			// Create initial context
			System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
			System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
			try
			{
				ic.createSubcontext("java:");
				ic.createSubcontext("java:comp");
				ic.createSubcontext("java:comp/env");
				ic.createSubcontext("java:comp/env/jdbc");

				org.apache.tomcat.dbcp.dbcp.BasicDataSource ds = new org.apache.tomcat.dbcp.dbcp.BasicDataSource();

				ds.setUrl(poolConnectionProperties.getUrl());
				ds.setDriverClassName(poolConnectionProperties.getDriverClassName());
				ds.setUsername(poolConnectionProperties.getUser());
				ds.setPassword(poolConnectionProperties.getPassword());
				ds.setMaxWait(poolConnectionProperties.getMaxWaitIntValue());

				// Set it to a small value to ensure that the test does not run for long.
				ds.setMaxActive(poolConnectionProperties.getMaxConnectionsInt() + 10);
				ds.setTimeBetweenEvictionRunsMillis(poolConnectionProperties.getTimeBetweenEvictionRunsMillisInt());

				for (String bind : poolConnectionProperties.getBinds())
				{
					ic.bind(bind, ds);
				}
			}
			catch (NameAlreadyBoundException nameAlreadyBoundException)
			{
				if (LOG.isInfoEnabled())
				{
					LOG.info("The name is already bound.", nameAlreadyBoundException);
				}
			}
		}
		catch (NamingException namingException)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(" There was a problem trying to get the pool connection.", namingException);
			}
		}
	}

	/*
	 * Protect against cloning
	 */
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
		// that'll teach 'em
	}

	public EquationUnit getEquationUnit()
	{
		if (sessionIdentifier == null)
		{
			initialiseSession();
		}

		EquationUnit eqUnit = null;
		try
		{
			eqUnit = EquationCommonContext.getContext().getEquationUnit(sessionIdentifier);
		}
		catch (Exception e)
		{
			LOG.error("Canny get a unit", e);
		}
		return eqUnit;
	}
	public EquationUser getEquationUser()
	{
		if (sessionIdentifier == null)
		{
			initialiseSession();
		}
		return EquationCommonContext.getContext().getEquationUser(sessionIdentifier);
	}

	public EquationStandardSession getStandardSession()
	{
		if (sessionIdentifier == null)
		{
			initialiseSession();
		}
		return standardSession;
	}

	public EQSession getEnhancedAPISession()
	{
		return enhancedAPISession;
	}
	public void putParameter(String option, String value)
	{
		optionParameters.put(option, value);
	}
	public String getParameter(String option)
	{
		return optionParameters.get(option);
	}
	public void removeParameter(String option)
	{
		optionParameters.remove(option);
	}
	public String getSessionId()
	{
		if (sessionIdentifier == null)
		{
			initialiseSession();
		}
		return sessionIdentifier;
	}

	public String getSystem()
	{
		return system;
	}

	public String getUnit()
	{
		return unit;
	}

	public String getUser()
	{
		return user;
	}

	public String getPassword()
	{
		return password;
	}

}
