package com.misys.equation.common.access;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.support.nativejdbc.WebSphereNativeJdbcExtractor;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400JDBCConnection;
import com.ibm.as400.access.AS400JDBCConnectionHandle;
import com.ibm.as400.access.AS400JDBCDataSource;
import com.ibm.as400.security.auth.ProfileTokenCredential;
import com.ibm.websphere.rsadapter.WSCallHelper;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQSessionProperties;
import com.misys.equation.common.utilities.AS400Toolbox;
import com.misys.equation.common.utilities.EqJobWatcher;
import com.misys.equation.common.utilities.EquationControl;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.ThreadData;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.dataaccess.connectionpooling.EQDataAccessException;
import com.misys.equation.dataaccess.connectionpooling.impl.JndiConnectionPool;

/**
 * This class is an connection pool implementation. The class implements all data source methods in order to provide database
 * connection facilities.
 * 
 * @author deroset
 */
public class EquationSessionPool extends AbstractEquationSessionPool
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationSessionPool.java 17189 2013-09-03 11:49:03Z Lima12 $";

	// Session Pool keys
	public static final String SESSIONPOOL_SINGLEJOB = "SingleJob";
	public static final String SESSIONPOOL_POOLEDJOB = "PooledJob";

	private JndiConnectionPool jndiConnectionPool;

	private EquationUnit unit;
	private String systemId;
	private String unitId;
	private String poolUserId;

	private static final EquationLogger LOG = new EquationLogger(EquationSessionPool.class);
	private Map<String, String> connectionProperties;

	private static final String APPLICATION_NAME_EQUATION = "EQUATION";
	private static final String APPLICATION_NAME_POOL = "POOL";
	private static final int MAX_PASSWORD_LENGTH = 10;

	/**
	 * Construct an empty Equation Session pool
	 */
	public EquationSessionPool()
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("The database pool is going to be created");
		}
	}

	/**
	 * Initialise the Equation Session pool using the specified user and password
	 * 
	 * @param userId
	 *            this is the current user id.
	 * @param password
	 *            - unused in JNDI
	 * 
	 * @throws EQException
	 *             if there is any error that one will be logged in the output and an exception will be thrown.
	 */
	@Override
	public void initialisePool(String userId, String password) throws EQException
	{
		initialisePool(userId, password, null);
	}
	/**
	 * Initialise the Equation Session pool using the specified user and password
	 * 
	 * @param userId
	 *            this is the current user id.
	 * @param password
	 *            - unused in JNDI
	 * @param dataSourceName
	 *            - the name of the Data Source this pool is being created with. Data source name is optional and if not supplied
	 *            will be in the form EQ-SYSTEM-UNIT.
	 * 
	 * 
	 * @throws EQException
	 *             if there is any error that one will be logged in the output and an exception will be thrown.
	 */
	public void initialisePool(String userId, String password, String dataSourceName) throws EQException
	{
		this.systemId = unit.getEquationSystem().getSystemId();
		this.unitId = unit.getUnitId();
		this.poolUserId = userId;

		if (connectionProperties == null)
		{
			connectionProperties = new HashMap<String, String>();
			connectionProperties.put(JndiConnectionPool.CONN_PROP_KEY_INIT_CONTEXT, "java:comp/env");
			if (dataSourceName == null)
			{
				connectionProperties.put(JndiConnectionPool.CONN_PROP_KEY_ENV_CONTEXT, "jdbc/EQ-" + systemId + "-" + unitId);
			}
			else
			{
				connectionProperties.put(JndiConnectionPool.CONN_PROP_KEY_ENV_CONTEXT, "jdbc/" + dataSourceName);
			}
		}

		// initialise the JNDI connection pools
		jndiConnectionPool = createJNDIConnectionPool();

		// initialise the base attributes (number users etc.)
		setBaseAttributes(userId);
	}

	/**
	 * Initialise the base attributes of the session pool
	 * 
	 * @throws EQException
	 */
	private void setBaseAttributes(String userId) throws EQException
	{
		// Establish a connection to the Equation iSeries
		Connection connection = null;
		try
		{
			// get an AS400 job
			connection = getConnection(userId);

			// make sure the unit has all the required SQL functions and procedures
			EquationControl.createUnitSQLObjects(connection, unitId);

			// Close the JDBC connections as they are worthless now
			connection.commit();
		}
		catch (SQLException sQLException)
		{
			LOG.error("setBaseAttributes: failed.", sQLException);
		}
		finally
		{
			returnConnectionToPool(connection);
		}
	}

	/**
	 * Construct a JNDI connection pool using the data source on the pool
	 * 
	 * @return a JNDI connection pool
	 */
	private JndiConnectionPool createJNDIConnectionPool() throws EQDataAccessException
	{
		JndiConnectionPool pool = null;

		if (aS400dataSource == null)
		{
			pool = new JndiConnectionPool(connectionProperties, false);
			aS400dataSource = pool.getDataSource();
		}
		else
		{
			pool = new JndiConnectionPool(connectionProperties, false, aS400dataSource);
		}

		return pool;
	}

	/**
	 * Get the native connection from the wrapper given a wrapped connection
	 * 
	 * @param wrappedConnection
	 *            - the wrapped connection
	 * @return the native AS400JDBCConnection
	 * @throws SQLException
	 *             - if there is a problem retrieving the native connection
	 */
	private AS400JDBCConnection getAS400Connection(Connection wrappedConnection) throws SQLException
	{
		if (wrappedConnection instanceof AS400JDBCConnection)
		{
			return (AS400JDBCConnection) wrappedConnection;
		}

		Connection wasConnection = null;
		if (JndiConnectionPool.isAWebSphereApplicationServer())
		{
			wasConnection = WSCallHelper.getNativeConnection(wrappedConnection);
		}

		AS400JDBCConnection asConnection = null;

		if (wasConnection != null)
		{
			asConnection = (AS400JDBCConnection) wasConnection.getMetaData().getConnection();
		}
		else
		{
			asConnection = (AS400JDBCConnection) wrappedConnection.getMetaData().getConnection();
		}
		return asConnection;
	}

	/**
	 * This method will return an AS400 connection from the connection pool.
	 * 
	 * BE SURE TO RETURN THE CONNECTION TO THE POOL IMMEDIATELY AFTER USE.
	 * 
	 * @return AS400 connection from the connection pool.
	 */
	@Override
	public Connection getConnection(String userId) throws EQException
	{
		Connection connection = null;
		try
		{
			connection = jndiConnectionPool.getConnection();
			AS400JDBCConnection asConnection = getAS400Connection(connection);

			// Equation DataSources must be configured to use system naming
			// TODO Move this check into pool initialization
			String naming = asConnection.getMetaData().getCatalogSeparator();
			if (!"/".equals(naming))
			{
				throw new RuntimeException("AS400JDBCDataSource naming property was not set to system");
			}

			// Comment 16June2011
			// String loggedIn = asConnection.getClientInfo("ApplicationName");

			// if (loggedIn == null || loggedIn == "" || loggedIn.equals("POOL"))
			// {
			// EquationControl.logIntoEquation(connection, systemId, unitId, EquationControl.NONDESKTOP_MODE);
			// asConnection.setClientInfo("ApplicationName", "EQUATION");
			// }

			String loggedIn = asConnection.getClientInfo("ApplicationName");

			if (loggedIn == null || loggedIn == "" || loggedIn.equals(APPLICATION_NAME_POOL))
			{
				// Real user (equation iSeries user) does not apply to session pools
				EquationControl.logIntoEquation(connection, systemId, unitId, EquationControl.NONDESKTOP_MODE, null);
				asConnection.setClientInfo("ApplicationName", APPLICATION_NAME_EQUATION);
			}

			// Set underlying connection properties
			asConnection.setAutoCommit(isAutoCommit());
			asConnection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			asConnection.setClientInfo("ClientUser", userId);

			// Ensure wrapper connection matches
			connection.setAutoCommit(isAutoCommit());
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

			if (LOG.isDebugEnabled())
			{
				StringBuilder message = new StringBuilder("Has got a handle to the following job from the pool: ");
				message.append(asConnection.getServerJobIdentifier());
				// if (LOG.isDebugEnabled())
				// {
				LOG.debug(message.toString());
				// }
				// Pop the session identifier onto the thread
				ThreadData.set(SESSIONPOOL_POOLEDJOB, EqJobWatcher.formatServerJobId(asConnection.getServerJobIdentifier()));
			}
		}
		catch (Exception e)
		{
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}

		return connection;
	}

	/**
	 * This method will return a standalone AS400 connection using the user id and password (NOT from the Equation session pool)
	 * 
	 * @param userId
	 *            - the user to initialise the pool with
	 * @param password
	 *            - not required for JNDI connections
	 * @param equationIseriesProfile
	 *            - the iSeries/Equation user profile
	 * 
	 * @return a wrapped standalone AS400 connection
	 */
	@Override
	public EquationConnectionWrapper getSingleConnection(String userId, String password, String equationIseriesProfile)
					throws EQException
	{
		AS400JDBCDataSource as400dataSource = null;
		AS400 as400 = null;
		Connection connection = null;

		try
		{
			if (password.length() > MAX_PASSWORD_LENGTH)
			{
				as400 = AS400Toolbox.getAS400(systemId);
				byte[] tokenBytes = Toolbox.cvtHexStringToBytes(password);
				if (tokenBytes.length != ProfileTokenCredential.TOKEN_LENGTH)
				{
					// Might be base64 token (from BankFusion)
					tokenBytes = Toolbox.base64StringToByteArray(password);
				}
				ProfileTokenCredential token = new ProfileTokenCredential(as400, tokenBytes,
								ProfileTokenCredential.TYPE_SINGLE_USE, 3600);
				// Add the profile token to the AS400 and cross our fingers
				as400.setProfileToken(token);
				as400dataSource = new AS400JDBCDataSource(as400);
			}
			else
			{
				as400dataSource = new AS400JDBCDataSource(systemId);
				as400dataSource.setUser(userId);
				as400dataSource.setPassword(password);
			}

			as400dataSource.setSecure(AS400Toolbox.isSecure());
			as400dataSource.setPrompt(isPrompt());
			as400dataSource.setTranslateBinary(isTranslateBinary());
			as400dataSource.setNaming(getNaming());
			as400dataSource.setLibraries(getLibraries());
			as400dataSource.setTrace(isTrace());

			connection = as400dataSource.getConnection();
			connection.setAutoCommit(isAutoCommit());
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			if (equationIseriesProfile == null || equationIseriesProfile.length() == 0 || userId.equals(equationIseriesProfile))
			{
				EquationControl.logIntoEquation(connection, systemId, unitId, EquationControl.DESKTOP_MODE, null);
			}
			else
			{
				EquationControl.logIntoEquation(connection, systemId, unitId, EquationControl.DESKTOP_MODE, equationIseriesProfile);
			}

			// Pop the session identifier onto the thread
			ThreadData.set(SESSIONPOOL_SINGLEJOB, EqJobWatcher.formatServerJobId(((AS400JDBCConnection) connection)
							.getServerJobIdentifier()));

			// print the AS400 job attribute
			if (LOG.isInfoEnabled())
			{
				String jobId = ((AS400JDBCConnection) connection).getServerJobIdentifier();
				LOG.info("Getting a standalone iSeries job: " + jobId);
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}

		// return the connection
		return new EquationConnectionWrapper(connection, this, false, false);
	}

	/**
	 * This method will return an XA connection that is initialised
	 * 
	 * @return a wrapped XA connection that is initialised
	 */
	@Override
	public EquationConnectionWrapper getXAConnection(String userId, String password, boolean equationManagedXA,
					boolean transactionConnection, String equationIseriesProfile) throws EQException
	{
		// TODO - Do we need to use equationIseriesProfile in the login process? Use of XA and pools needs review once we are truly
		// stateless.
		Connection connection = null;
		String jobId = null;

		try
		{
			// If we have a profile token then get a connection without password and switch user of job in login process.
			if (password.length() > MAX_PASSWORD_LENGTH)
			{
				connection = unit.getXaDataSource().getConnection();
			}
			else
			{
				connection = unit.getXaDataSource().getConnection(userId, password);
			}

			WebSphereNativeJdbcExtractor jdbcExtractor = new WebSphereNativeJdbcExtractor();
			AS400JDBCConnectionHandle nativeconnection = (AS400JDBCConnectionHandle) jdbcExtractor.getNativeConnection(connection);
			jobId = nativeconnection.getServerJobIdentifier();
			ThreadData.set(EquationSessionPool.SESSIONPOOL_SINGLEJOB, EqJobWatcher.formatServerJobId(jobId));

			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

			// Change the user of the job with the profile token
			if (password.length() > MAX_PASSWORD_LENGTH)
			{
				byte[] tokenBytes = Toolbox.cvtHexStringToBytes(password);
				if (tokenBytes.length != ProfileTokenCredential.TOKEN_LENGTH)
				{
					// Might be base64 token (from BankFusion)
					tokenBytes = Toolbox.base64StringToByteArray(password);
				}
				EquationControl.logIntoEquation(connection, systemId, unitId, EquationControl.XA_MODE, userId, null, tokenBytes);
			}
			else
			{
				EquationControl.logIntoEquation(connection, systemId, unitId, EquationControl.XA_MODE, userId, null, null);
			}

			// print the AS400 job attribute
			if (LOG.isInfoEnabled())
			{
				LOG.info("Getting a standalone XA iSeries job: " + jobId);
			}

		}
		catch (Exception e)
		{
			LOG.info("Error getting a standalone XA iSeries job: " + jobId, e);
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
		// return the connection
		return new EquationConnectionWrapper(connection, this, true, transactionConnection);
	}

	/**
	 * Return the Equation session pool properties
	 * 
	 * @return the Equation session pool properties
	 */
	@Override
	public EQSessionProperties getSessionProperties() throws EQException
	{
		EQSessionProperties sessionProperties = new EQSessionProperties(systemId, unitId);
		sessionProperties.setAutoEQCommit(isAutoCommit());
		sessionProperties.setTimeOut(getLoginTimeout());
		sessionProperties.setTransactionIsolationLevel(EQSessionProperties.TRANSACTION_ISOLATION_EQUATION_ONLY);
		return sessionProperties;
	}

	/**
	 * Return an <code>EquationStandardSession</code> with connection from the unit's Equation session pool
	 * 
	 * @return the Equation Standard Session
	 * 
	 * @throws EQException
	 *             if there is any error an exception will be thrown.
	 */
	@Override
	public EquationStandardSession getEquationStandardSession() throws EQException
	{
		EquationStandardSession session = null;

		if (unit.getUnitVersion().equals(EquationUnit.VERSION_EQ4))
		{
			session = new Equation4Session(unit);
		}
		else if (unit.getUnitVersion().equals(EquationUnit.VERSION_EQ38))
		{
			session = new Equation38Session(unit);
		}
		else if (unit.getUnitVersion().equals(EquationUnit.VERSION_EQ342))
		{
			session = new EquationPre38Session(unit);
		}
		else
		{
			// by default supply an Equation4Session
			session = new Equation4Session(unit);
		}

		return session;
	}

	/** {@inheritDoc} */
	@Override
	public void returnConnnection(Connection connection) throws EQException
	{
		try
		{
			String jobId = getAS400Connection(connection).getServerJobIdentifier();
			StringBuilder message = new StringBuilder("Is closing the job: ");
			message.append(jobId);
			LOG.debug(message.toString());

			// Return the connection to the pool
			connection.close();
		}
		catch (SQLException e)
		{
			throw new EQException(e);
		}
	}

	/** {@inheritDoc} */
	@Override
	public void returnConnnection(EquationConnectionWrapper connectionWrapper) throws EQException
	{
		if (LOG.isDebugEnabled())
		{
			StringBuilder message = new StringBuilder("Returning the following EquationUser job to the pool: ");
			message.append(connectionWrapper.getJobId());
			LOG.debug(message.toString());
		}
		try
		{
			connectionWrapper.getConnection().close();
		}
		catch (SQLException e)
		{
			throw new EQException(e);
		}
	}

	/** {@inheritDoc} */
	@Override
	public void returnEquationStandardSession(EquationStandardSession session) throws EQException
	{
		session.closeStatements();
		returnConnnection(session.getConnectionWrapper());
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
			// close resources.
			close();
		}
		finally
		{
			super.finalize();
		}
	}

	/**
	 * This method will close all resources <code>EquationSessionPool</code>.<br>
	 * <ul>
	 * <li>1)<code>JndiConnectionPool</code> will be closed</li>
	 * </ul>
	 */
	@Override
	public void close()
	{
		closeAS400JNDIConnectionPools();
	}

	/**
	 * This method will close <code>JndiConnectionPool</code> resources.
	 */
	private void closeAS400JNDIConnectionPools()
	{
		if (jndiConnectionPool == null)
			return;

		try
		{
			Connection c = jndiConnectionPool.getDataSource().getConnection();

			if (getAS400Connection(c).getClientInfo("ApplicationName").equals(APPLICATION_NAME_EQUATION))
			{
				getAS400Connection(c).setClientInfo("ApplicationName", APPLICATION_NAME_POOL);
				EquationControl.logOffFromEquation(c, systemId, unitId);

				// This function is called recursively to get the remaining pre-initialised connections
				// with out closing any until all are logged off. This avoids leaving jobs still logged on to the unit
				closeAS400JNDIConnectionPools();

				// Close once all connections have been logged off
				c.close();
				return;

			}
			else
			{
				// No more existing pooled connections, close and return
				c.close();
				return;
			}

		}
		catch (SQLException e)
		{
			LOG.error("Problem closing connection pool - some connections may not have been closed", e);
		}
		catch (EQException e)
		{
			LOG.error("Problem closing connection pool - some connections may not have been closed", e);
		}
	}

	/**
	 * Return the unit
	 * 
	 * @return the unit
	 */
	@Override
	public EquationUnit getUnit()
	{
		return unit;
	}

	/**
	 * Set the unit
	 * 
	 * @param unit
	 *            - the unit
	 */
	@Override
	public void setUnit(EquationUnit unit)
	{
		this.unit = unit;
	}
	/**
	 * Return the user id
	 * 
	 * @return the user id
	 */
	@Override
	public String getUserId()
	{
		return poolUserId;
	}

	/**
	 * Set the user id
	 * 
	 * @param userId
	 *            - the user id to set
	 */
	public void setUserId(String userId)
	{
		this.poolUserId = userId;
	}
}