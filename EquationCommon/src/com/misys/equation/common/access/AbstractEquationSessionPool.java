package com.misys.equation.common.access;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQSessionProperties;
import com.misys.equation.common.utilities.EquationLogger;

/**
 * This class is a connection pool abstraction. The class implements all data source methods and handles all database pool
 * attributes.
 */
public abstract class AbstractEquationSessionPool
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractEquationSessionPool.java 16593 2013-06-24 15:32:19Z perkinj1 $";

	/** Logger for this class */
	private static final EquationLogger LOG = new EquationLogger(AbstractEquationSessionPool.class);

	// Data source
	protected DataSource aS400dataSource;

	// Data-source attributes.
	private boolean prompt;
	private boolean trace;
	private boolean translateBinary;
	private String naming;
	private String libraries;

	// Pool connection attributes.
	private int maxConnections;
	private long maxLifetime;
	private int fill;

	// Connection attribute
	private boolean autoCommit;

	/** Default login timeout */
	private static final int DEFAULT_LOGIN_TIMEOUT = 30;

	/**
	 * Get a connection from the connection pool.
	 * 
	 * BE SURE TO RETURN THE CONNECTION TO THE POOL IMMEDIATELY AFTER USE.
	 * 
	 * @return the connection
	 */
	public abstract Connection getConnection(String userId) throws EQException;

	/**
	 * @return the Equation Unit
	 */
	public abstract EquationUnit getUnit();

	/**
	 * Returns the <code>PrintWriter</code> from the <code>AS400JSBCConnectionPoolDataSource</code>
	 * 
	 * @return the <code>PrintWriter</code>
	 * 
	 * @throws SQLException
	 */
	public PrintWriter getLogWriter() throws SQLException
	{
		return aS400dataSource.getLogWriter();
	}

	/**
	 * Returns the login timeout for the data source
	 * 
	 * @return an int value for the login timeout
	 */
	public int getLoginTimeout()
	{
		try
		{
			return aS400dataSource.getLoginTimeout();
		}
		catch (SQLException e)
		{
			LOG.error(e);
		}
		catch (UnsupportedOperationException e)
		{
			return DEFAULT_LOGIN_TIMEOUT;
		}
		return -1;
	}

	/**
	 * Utility method to return a connection to the pool if it exists
	 * 
	 * @param pool
	 *            - the EquationSessionPool to return the connection to
	 * @param connection
	 *            - the Connection Object to return
	 */
	public void returnConnectionToPool(Connection connection)
	{
		// Return the connection to the pool
		if (connection != null)
		{
			try
			{
				returnConnnection(connection);
			}
			catch (Exception e)
			{
				LOG.error(e);
			}
		}
	}

	/**
	 * Sets the <code>PrintWriter</code> for the data source
	 * 
	 * @param printWriter
	 *            - the <code>PrintWriter</code>
	 * 
	 * @throws SQLException
	 */
	public void setLogWriter(PrintWriter printWriter) throws SQLException
	{
		aS400dataSource.setLogWriter(printWriter);
	}

	/**
	 * Sets the login timeout for the data source
	 * 
	 * @param timeout
	 *            - the login timeout
	 * 
	 * @throws SQLException
	 */
	public void setLoginTimeout(int timeout) throws SQLException
	{
		aS400dataSource.setLoginTimeout(timeout);
	}

	/**
	 * @deprecated Given that EQ4 environment the connection was already created and contained in pool.<br>
	 *             The implementation of this method is been deprecated.
	 * 
	 *             This method will return a as400 connection from the connection pool.
	 * 
	 * @param username
	 *            - this is user name which will be used to get as400 connection.
	 * @param password
	 *            - this is password which will be used to get as400 connection.
	 * 
	 * @return as400 connection from the connection pool.
	 */
	@Deprecated
	public Connection getConnection(String username, String password) throws EQException
	{
		return getConnection(username);
	}

	/*--------getter and setters -------------*/

	/**
	 * Returns the data source prompt attribute
	 */
	public boolean isPrompt()
	{
		return prompt;
	}

	/**
	 * Set the data source prompt attribute
	 * 
	 * @param prompt
	 *            - the data source prompt attribute
	 */
	public void setPrompt(boolean prompt)
	{
		this.prompt = prompt;
	}

	/**
	 * Return the data source translate binary attribute
	 * 
	 * @return the data source translate binary attribute
	 */
	public boolean isTranslateBinary()
	{
		return translateBinary;
	}

	/**
	 * Set the data source translate binary attribute
	 * 
	 * @param translateBinary
	 *            - the data source translate binary attribute
	 */
	public void setTranslateBinary(boolean translateBinary)
	{
		this.translateBinary = translateBinary;
	}

	/**
	 * Return the data source naming attribute
	 * 
	 * @return the data source naming attribute
	 */
	public String getNaming()
	{
		return naming;
	}

	/**
	 * Set the data source naming attribute
	 * 
	 * @param naming
	 *            - the data source naming attribute
	 */
	public void setNaming(String naming)
	{
		this.naming = naming;
	}

	/**
	 * Return the data source library attribute
	 * 
	 * @return the data source library attribute
	 */
	public String getLibraries()
	{
		return libraries;
	}

	/**
	 * Set the data source library attribute
	 * 
	 * @param libraries
	 *            - the data source library attribute
	 */
	public void setLibraries(String libraries)
	{
		this.libraries = libraries;
	}

	/**
	 * Return the data source maximum connection attribute
	 * 
	 * @return the data source maximum connection attribute
	 */
	public int getMaxConnections()
	{
		return maxConnections;
	}

	/**
	 * Set the data source maximum connection attribute
	 * 
	 * @param maxConnections
	 *            - the data source maximum connection attribute
	 */
	public void setMaxConnections(int maxConnections)
	{
		this.maxConnections = maxConnections;
	}

	/**
	 * Return the data source maximum lifetime attribute
	 * 
	 * @return the data source maximum lifetime attribute
	 */
	public long getMaxLifetime()
	{
		return maxLifetime;
	}

	/**
	 * Set the data source maximum lifetime attribute
	 * 
	 * @param maxLifetime
	 *            - the data source maximum lifetime attribute
	 */
	public void setMaxLifetime(long maxLifetime)
	{
		this.maxLifetime = maxLifetime;
	}

	/**
	 * Return the data source fill attribute
	 * 
	 * @return the data source fill attribute
	 */
	public int getFill()
	{
		return fill;
	}

	/**
	 * Set the data source fill attribute
	 * 
	 * @param fill
	 *            - the data source fill attribute
	 */
	public void setFill(int fill)
	{
		this.fill = fill;
	}

	/**
	 * Return whether the data source is auto-commit
	 * 
	 * @return true if the data source is auto-commit
	 */
	public boolean isAutoCommit()
	{
		return autoCommit;
	}

	/**
	 * Set whether the data source is auto-commit
	 * 
	 * @param autoCommit
	 *            - true if the data source is auto-commit
	 */
	public void setAutoCommit(boolean autoCommit)
	{
		this.autoCommit = autoCommit;
	}

	/**
	 * @return whether trace is enabled for this session pool
	 */
	public boolean isTrace()
	{
		return trace;
	}

	/**
	 * @param trace
	 */
	public void setTrace(boolean trace)
	{
		this.trace = trace;
	}

	/**
	 * Return a connection to the pool
	 * 
	 * @param connection
	 *            - the connection to return
	 * @throws EQException
	 *             if there is any error an exception will be thrown.
	 */
	public abstract void returnConnnection(Connection connection) throws EQException;

	/**
	 * Return the connection back to the session pool
	 * 
	 * @param connectionWrapper
	 *            this is the wrapper of the connection to be returned.
	 * 
	 * @throws EQException
	 *             if there is any error an exception will be thrown.
	 */
	public abstract void returnConnnection(EquationConnectionWrapper connectionWrapper) throws EQException;

	/**
	 * Get an <code>EquationStandardSession</code> object constructed with a pooled connection
	 * 
	 * @return the <code>EquationStandardSession</code>
	 * @throws EQException
	 */
	public abstract EquationStandardSession getEquationStandardSession() throws EQException;

	/**
	 * Close the connection pool
	 */
	public abstract void close();

	/**
	 * @return the user ID of the pool's creator
	 */
	public abstract String getUserId();

	/**
	 * Set the Equation unit this session pool is associated with
	 * 
	 * @param equationUnit
	 *            - the unit to set
	 */
	public abstract void setUnit(EquationUnit equationUnit);

	/**
	 * Initialise the session pool
	 * 
	 * @param userId
	 *            - the user ID of the user to initialise the pool with
	 * @param password
	 *            - the password of the user to initialise the pool with
	 * @throws EQException
	 */
	public abstract void initialisePool(String userId, String password) throws EQException;

	/**
	 * This method will logOff the session which is passed as a parameter.<br>
	 * <ul>
	 * <li>1)The current connection will be returned to the pool</li>
	 * </ul>
	 * 
	 * @param session
	 *            this is the current session to be closed.
	 * 
	 * @throws EQException
	 *             if there is any error an exception will be thrown.
	 */
	public abstract void returnEquationStandardSession(EquationStandardSession session) throws EQException;;

	/**
	 * @return the <code>EQSessionProperties</code> bean
	 * @throws EQException
	 */
	public abstract EQSessionProperties getSessionProperties() throws EQException;

	/**
	 * Get an XA connection
	 * 
	 * @param userId
	 *            - the user ID
	 * @param password
	 *            - the password
	 * @param equationManagedXA
	 *            -
	 * @param transactionConnection
	 *            - specify whether the connection is transactional
	 * @param equationIseriesProfile
	 *            - the iSeries/Equation user profile
	 * @return an <code>EquationConnectionWrapper</code> object wrapping the XA connection
	 * @throws EQException
	 */
	public abstract EquationConnectionWrapper getXAConnection(String userId, String password, boolean equationManagedXA,
					boolean transactionConnection, String equationIseriesProfile) throws EQException;

	/**
	 * Get a single user connection from the pool
	 * 
	 * @param userId
	 *            - the user ID
	 * @param password
	 *            - the password
	 * @param equationIseriesProfile
	 *            - the iSeries/Equation user profile
	 * @return an <code>EquationConnectionWrapper</code> object wrapping the single connection
	 */
	public abstract EquationConnectionWrapper getSingleConnection(String userId, String password, String equationIseriesProfile)
					throws EQException;
}
