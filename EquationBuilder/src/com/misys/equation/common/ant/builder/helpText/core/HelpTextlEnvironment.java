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
package com.misys.equation.common.ant.builder.helpText.core;

import java.sql.Connection;

import com.ibm.as400.access.AS400JDBCDataSource;
import com.misys.equation.common.ant.builder.core.EquationControl;
import com.misys.equation.common.ant.builder.core.EquationLogger;
import com.misys.equation.common.ant.builder.core.EquationStandardSession;

/**
 * This is a helper that provides environment setting and connectivity.<br>
 * This class is going to be used by EquationHelpText classes.
 * 
 * @author Esther Williams
 */
public class HelpTextlEnvironment
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: HelpTextlEnvironment.java 14404 2012-09-04 12:07:16Z williae1 $";
	private static final EquationLogger LOG = new EquationLogger(HelpTextlEnvironment.class);
	private static HelpTextlEnvironment singletonContext;
	private HelpTextProperties helpTextProperties = HelpTextProperties.getInstance();

	private Connection connection;
	private EquationStandardSession standardSession;
	private EquationControl control;

	/*
	 * Get the singleton context
	 */
	public static synchronized HelpTextlEnvironment getHelpTextEnvironment()
	{
		// Create our one and only instance of this class
		if (singletonContext == null)
		{
			singletonContext = new HelpTextlEnvironment();
		}
		return singletonContext;
	}

	/*
	 * Constructor
	 */
	private HelpTextlEnvironment()
	{
		initialisation();
	}

	/**
	 * This method will initialise the environment.
	 */
	private void initialisation()
	{

		try
		{
			createConnection();
			standardSession = new EquationStandardSession(connection);
			control = new EquationControl();
			control.createAldonSQLObjects(getConnection(), helpTextProperties.getAldonLibrary());

			control.setLiblForAldon(connection, helpTextProperties.getAldonLibrary(), helpTextProperties.getGroup(),
							helpTextProperties.getApplication(), helpTextProperties.getRelease(), helpTextProperties
											.getEnvironment(), helpTextProperties.getDeveloper().trim());
		}
		catch (Exception exception)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Initialisation error. ", exception);
			}
		}
	}

	private void createConnection()
	{
		try
		{

			AS400JDBCDataSource dataSource = new AS400JDBCDataSource(helpTextProperties.getSystem());
			dataSource.setUser(helpTextProperties.getUser());
			dataSource.setPassword(helpTextProperties.getPassword());
			dataSource.setTranslateBinary(false);
			dataSource.setNaming("system");
			dataSource.setLibraries("*LIBL");

			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

		}
		catch (Exception exception)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Could not get connection: ", exception);
			}
		}
	}

	public String getSourceLibrary(String fileName)
	{
		String library = "";
		try
		{
			library = control.getSourceLibrary(connection, helpTextProperties.getAldonLibrary(), fileName);
		}
		catch (Exception exception)
		{
			if (LOG.isErrorEnabled())
			{

				LOG.error("Could not get source library: ", exception);
			}
		}
		return library;
	}

	public Connection getConnection()
	{
		return connection;
	}
	public EquationStandardSession getStandardSession()
	{
		return standardSession;
	}

}
