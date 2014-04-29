package com.misys.equation.function.test.run;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.TestCase;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.EquationLogger;

public class FunctionHandlerStubTestCase extends TestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStubTestCase.java 7544 2010-05-28 09:47:29Z lima12 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(FunctionHandlerStubTestCase.class);

	protected static EquationStandardSession session;
	protected static EquationUnit unit;
	protected static EquationUser user;
	protected static boolean externalInputTest = false;
	public static boolean cleanUp = true;

	@Override
	protected void setUp() throws Exception
	{
		LOG.info("entering setUp()");

		System.out.println("======" + this.getClass().getSimpleName() + "======");
		if (session == null)
		{
			unit = TestEnvironment.getTestEnvironment().getEquationUnit();
			user = TestEnvironment.getTestEnvironment().getEquationUser();
			session = user.getSession();
		}
		super.setUp();
		LOG.info("exiting setUp()");
	}
	/**
	 * Execute a prepared statement
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param sqlStatement
	 *            - the SQL statement
	 * 
	 * @throws EQException
	 */
	protected String executeQuery(EquationStandardSession session, String sqlString)
	{
		Statement statement = null;
		ResultSet resultSet = null;
		String result = null;
		try
		{
			Connection connection = session.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlString);
			if (resultSet.next())
			{
				result = resultSet.getString(1);
			}
			resultSet.close();
		}
		catch (SQLException e)
		{
			throw new RuntimeException("EQFile: executePreparedStatement() Failed: " + e.getMessage(), e);
		}
		finally
		{
			try
			{
				if (statement != null)
				{
					statement.close();
				}
			}
			catch (SQLException ex)
			{
				LOG.error(ex);
			}
		}
		return result;
	}

	protected void closeResultSet(ResultSet rs)
	{
		if (rs != null)
		{
			try
			{
				rs.close();
			}
			catch (SQLException e)
			{
				LOG.error(e);
			}
		}
	}

	protected static void cleanUp()
	{
		if (cleanUp)
		{
			// close resources
			user.logOffUser();
		}
	}

	@Override
	protected void tearDown() throws Exception
	{
		cleanUp();
		super.tearDown();
	}

}
