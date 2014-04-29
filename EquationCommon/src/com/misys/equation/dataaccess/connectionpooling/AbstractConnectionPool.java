package com.misys.equation.dataaccess.connectionpooling;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import com.misys.equation.common.utilities.EquationLogger;

/**
 * This class represents an abstract connection pool for equation. Different connections to the database in equation are retrieved
 * in a different manner. For instance unit connections are instantiated with the login user name and password. While global
 * connections are looked up via JNDI with a single user name.
 * 
 * @author camillen
 * 
 */
public abstract class AbstractConnectionPool
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractConnectionPool.java 9962 2010-11-18 17:31:39Z MACDONP1 $";
	protected final EquationLogger LOG = new EquationLogger(this.getClass());
	// The data source to the database.
	protected DataSource dataSource;
	private final boolean checkAuthorisaion;
	protected final Map<String, String> properties;

	/**
	 * Base constructor. Initialises the properties and calls the abstract method which initialises the data source.
	 * 
	 * @param properties
	 *            Map<String, String> - Properties
	 * 
	 * @throws EQDataAccessException
	 */
	protected AbstractConnectionPool(final Map<String, String> properties, final boolean checkAuthorisaion)
					throws EQDataAccessException
	{
		this.properties = properties;
		this.checkAuthorisaion = checkAuthorisaion;
		initialiseDataSource();
	}

	/**
	 * Constructor - Create an instance using a provided DataSource
	 * 
	 * @param properties
	 *            Map<String, String> - Properties
	 * 
	 * @param checkAuthorisaion
	 * 
	 * @param source
	 * 
	 * @throws EQDataAccessException
	 */
	protected AbstractConnectionPool(final Map<String, String> properties, final boolean checkAuthorisaion, DataSource source)
	{
		this.properties = properties;
		this.checkAuthorisaion = checkAuthorisaion;
		this.dataSource = source;
	}

	/**
	 * This method is implemented to setup the data source. Depending on the implementation this method should initialise the data
	 * source depending on the mechanism used for look up and connection pooling.
	 * 
	 * @throws EQDataAccessException
	 */
	protected abstract void initialiseDataSource() throws EQDataAccessException;

	/**
	 * This method returns a connection from the connection pool
	 * 
	 * @return Connection - DB Connection
	 * @throws EQDataAccessException
	 */
	public final Connection getConnection(final SessionUserCredential credential) throws EQDataAccessException
	{
		try
		{
			if (checkAuthorisaion && checkAuthorisation(credential))
			{
				throw new EQDataAccessException("Authorisation failed.");
			}
			else
			{
				return (dataSource).getConnection();
			}
		}
		catch (SQLException e)
		{
			LOG.error(e);
			throw new EQDataAccessException(e);
		}
	}

	public final Connection getConnection() throws EQDataAccessException
	{
		if (checkAuthorisaion)
		{
			throw new EQDataAccessException("Need to pass SessionUserCredential if checkAuthorisaion");
		}
		else
		{
			return getConnection(null);
		}
	}

	/**
	 * Returns the data source object.
	 * 
	 * @return
	 */
	public final DataSource getDataSource()
	{
		return dataSource;
	}

	/**
	 * Returns the properties related to this connection pool.
	 * 
	 * @return
	 */
	public Map<String, String> getProperties()
	{
		return properties;
	}

	/**
	 * This method will be executed by the VM and will check if the current instance still holds resources.<br>
	 * Given that the current instance is no longer used all resources have to be closed.<br>
	 * 
	 * Before an object is garbage collected, the runtime system calls its finalize() method. The intent is for finalize() to
	 * release system resources such as open files or open sockets before getting collected.
	 */
	@Override
	protected void finalize() throws Throwable
	{
		try
		{
			destroyConnectionPool();
		}
		finally
		{
			super.finalize();
		}
	}

	/**
	 * This method is to be implemented to perform any releasing of resources before the connection pool is destroyed.
	 */
	protected abstract void destroyConnectionPool();

	/**
	 * Given a SessionUserCredential this method will determine if the user should be given access to the database connection. Needs
	 * to be overridden if authorisation is to be implemented.
	 * 
	 * @param credential
	 *            SessionUserCredential - Session user information
	 * 
	 * @return boolean - Indicates if access should be given or not.
	 * 
	 */
	protected boolean checkAuthorisation(SessionUserCredential credential)
	{
		return checkAuthorisaion;
	}
}