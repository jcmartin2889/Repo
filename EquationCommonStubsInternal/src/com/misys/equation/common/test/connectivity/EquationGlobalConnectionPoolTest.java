package com.misys.equation.common.test.connectivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingException;

import com.misys.equation.common.test.EquationTestCase;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.dataaccess.connectionpooling.ConnectionAccess;
import com.misys.equation.dataaccess.connectionpooling.EQDataAccessException;

public class EquationGlobalConnectionPoolTest extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationGlobalConnectionPoolTest.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	/**
	 * Logger for this class
	 */
	private static final EquationLogger LOG = new EquationLogger(TestEnvironment.class);

	private static final int MAX_CONNECTIONS = 2;

	/**
	 * This method sets up the JNDI context for the global connection.
	 */
	@Override
	public void setUp() throws Exception
	{
		try
		{
			// Create initial context
			System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
			System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
			InitialContext ic = new InitialContext();

			try
			{
				ic.createSubcontext("java:");
				ic.createSubcontext("java:comp");
				ic.createSubcontext("java:comp/env");
				ic.createSubcontext("java:comp/env/jdbc");

				org.apache.tomcat.dbcp.dbcp.BasicDataSource ds = new org.apache.tomcat.dbcp.dbcp.BasicDataSource();

				ds.setUrl("jdbc:as400://Slough1/KGRPGLB");
				ds.setDriverClassName("com.ibm.as400.access.AS400JDBCDriver");
				ds.setUsername("EQUIADMIN");
				ds.setPassword("EQUIADMIN");
				ds.setMaxActive(MAX_CONNECTIONS);
				ds.setMaxWait(2000); // Set it to a small value to ensure that the test does not run for long.
				ic.bind("java:comp/env/jdbc/GlobalDB", ds);

			}
			catch (NameAlreadyBoundException e)
			{
				// Could be already bound, no problem!
			}
		}
		catch (NamingException ex)
		{
			LOG.error(ex);
			assertFalse(true);
		}

	}

	/**
	 * This test is going to evaluate the database connection.
	 */
	public void testGlobalEquationPoolConnection()
	{
		try
		{
			Connection conn = ConnectionAccess.getGlobalConnectionPool().getConnection();
			conn.close();
			assertNotNull(conn);
		}
		catch (EQDataAccessException e)
		{
			LOG.error(e);
			assertTrue(false);
		}
		catch (SQLException e)
		{
			LOG.error(e);
			assertTrue(false);
		}
	}

	/**
	 * Test the maximum connections property of the pool.
	 */
	public void testMaxConnections()
	{
		Connection[] connection = new Connection[MAX_CONNECTIONS];
		for (int i = 0; i < MAX_CONNECTIONS; i++)
		{
			try
			{
				connection[i] = ConnectionAccess.getGlobalConnectionPool().getConnection();
				assertNotNull(connection[i]);
			}
			catch (EQDataAccessException e)
			{
				LOG.error(e);
				assertTrue(false);
			}
		}

		try
		{
			// First try but before close one. We already reached the max.
			connection[0].close(); // release
			connection[0] = ConnectionAccess.getGlobalConnectionPool().getConnection();
		}
		catch (EQDataAccessException e)
		{
			assertTrue(false);
		}
		catch (SQLException e)
		{
			assertTrue(false);
		}

		try
		{
			// Then try without closing. We already reached the max.
			Connection conn = ConnectionAccess.getGlobalConnectionPool().getConnection();
		}
		catch (EQDataAccessException e)
		{
			assertTrue(true);
		}

		// At the end close (release) all connections
		for (int i = 0; i < MAX_CONNECTIONS; i++)
		{
			try
			{
				connection[i].close();
			}
			catch (SQLException e)
			{
				LOG.error(e);
				assertTrue(false);
			}
		}

	}

	/**
	 * Perform a simple query on a global table.
	 */
	public void testSimpleQuery()
	{
		Connection conn = null;
		try
		{
			conn = ConnectionAccess.getGlobalConnectionPool().getConnection();
			Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = statement.executeQuery("SELECT * FROM CLH10LF");
			if (!resultSet.next())
			{
				assertFalse(true);
			}
		}
		catch (SQLException e)
		{
			LOG.error(e);
			assertFalse(true);
		}
		catch (EQDataAccessException e)
		{
			LOG.error(e);
			assertFalse(true);
		}
		finally
		{
			if (conn != null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					LOG.error(e);
					assertFalse(true);
				}
			}
		}
	}

	/**
	 * Perform a simple query on a non-global table. Should fail!
	 */
	public void testAccessNonGlobalTable()
	{
		Connection conn = null;
		try
		{
			conn = ConnectionAccess.getGlobalConnectionPool().getConnection();
			Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeQuery("SELECT * FROM GF03LF");
			assertFalse(true); // Should fail after running the query above.
		}
		catch (Exception e)
		{
			assertTrue(true);
		}
		finally
		{
			if (conn != null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					LOG.error(e);
					assertFalse(true);
				}
			}
		}
	}

	/**
	 * TODO - CAMILLN1 This method should implement tests for access with a specific database other than I-Series. No other database
	 * available at present.
	 */
	public void testAnotherDatabase()
	{
		assertTrue(true); // TODO Implement this test once we have an 3rd party DB setup.
	}

}
