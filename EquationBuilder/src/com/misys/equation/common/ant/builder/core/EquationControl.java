package com.misys.equation.common.ant.builder.core;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.misys.equation.common.ant.builder.helpText.backEnd.SQLToolbox;

/**
 * @author weddelc1
 */
public class EquationControl
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationControl.java 4741 2009-09-16 16:33:23Z esther.williams $";

	// start from EQSessionImpl

	private static final EquationLogger LOG = new EquationLogger(EquationControl.class);

	public void createAldonSQLObjects(Connection connection, String aldonLibrary)
	{
		String checkExists;
		String createSQLObject;

		// make sure we have the procedure for retrieving the file member path
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = '" + aldonLibrary.trim()
						+ "' AND SPECIFIC_NAME = 'UTW55CPRC'";

		createSQLObject = "CREATE PROCEDURE " + aldonLibrary.trim() + "/UTW55CPRC " + "( " + "IN FILE CHAR(10), "
						+ "OUT SRCLIB CHAR(10), " + "OUT SRCFILE CHAR(10), " + "OUT SRCMBR CHAR(10) " + ") " + "LANGUAGE CL "
						+ "NOT DETERMINISTIC " + "NO SQL " + "EXTERNAL NAME UTW55C " + "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the procedure for setting the library list for Aldon
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA LIKE '" + aldonLibrary.trim()
						+ "%' AND SPECIFIC_NAME LIKE 'UTW56CPRC%'";
		createSQLObject = "CREATE PROCEDURE " + aldonLibrary.trim() + "/UTW56CPRC " + "( " + "IN GROUP CHAR(10), "
						+ "IN APP CHAR(10), " + "IN RELEASE CHAR(10), " + "IN ENV CHAR(3), " + "IN DVPNAME CHAR(10), "
						+ "OUT RTNSTS CHAR(1) " + ") " + "LANGUAGE CL " + "NOT DETERMINISTIC " + "NO SQL "
						+ "EXTERNAL NAME UTW56C " + "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

	}

	private static void createSQLObject(Connection connection, String checkExists, String createProcedure)
	{

		Statement checkExistsStatement = null;
		ResultSet checkExistsRS = null;
		Statement createProcedureStatement = null;
		try
		{
			checkExistsStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			checkExistsRS = checkExistsStatement.executeQuery(checkExists);
			// ...

			if (checkExistsRS.next())
			{
				if (checkExistsRS.getInt(1) == 0)
				{
					createProcedureStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
					createProcedureStatement.execute(createProcedure);
					createProcedureStatement.close();
				}
			}
		}
		catch (SQLException e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Equation Control: createSQLObject Failed", e);
			}
		}
		finally
		{
			SQLToolbox.closeResulset(checkExistsRS);
			SQLToolbox.closeStatement(checkExistsStatement);
			SQLToolbox.closeStatement(createProcedureStatement);
		}
	}

	/**
	 * Retrieve the object path
	 * 
	 * @param objName
	 *            - the object name
	 * @param objType
	 *            - the object path
	 * @param objLib
	 *            - the object library
	 * @param retVal
	 *            - required return value 1-object library 2-object path
	 * 
	 * @return the returned object library or the object path
	 * 
	 * @throws EQException
	 */
	public void setLiblForAldon(Connection connection, String aldonLibrary, String group, String app, String release,
					String environment, String dvpname)
	{
		CallableStatement callableStatement = null;

		try
		{

			callableStatement = connection.prepareCall("CALL " + aldonLibrary.trim() + "/UTW56CPRC(?,?,?,?,?,?)");

			// Register output parameters
			callableStatement.registerOutParameter(6, java.sql.Types.CHAR);

			// Set the parameters
			callableStatement.setString(1, group);
			callableStatement.setString(2, app);
			callableStatement.setString(3, release);
			callableStatement.setString(4, environment);
			callableStatement.setString(5, dvpname);

			// Call the API
			callableStatement.execute();

			String strCallStatus = callableStatement.getString(6).trim();
			// Retrieve the returned values
			if (!strCallStatus.equals("0"))
			{
				String msg = "UTW56C call failed.";
				if (LOG.isErrorEnabled())
				{
					LOG.error(msg);
				}
			}

		}
		catch (SQLException sQLException)
		{
			if (LOG.isErrorEnabled())
			{

				if (LOG.isErrorEnabled())
				{
					LOG.error(sQLException);
				}
			}

		}
		finally
		{

			SQLToolbox.closePreparedStatement(callableStatement);
		}
	}
	public String getSourceLibrary(Connection connection, String aldonLibrary, String fileName)
	{
		String library = "";
		CallableStatement callableStatement = null;

		try
		{

			callableStatement = connection.prepareCall("CALL " + aldonLibrary.trim() + "/UTW55CPRC(?,?,?,?)");

			// Register output parameters
			callableStatement.registerOutParameter(2, java.sql.Types.CHAR);
			callableStatement.registerOutParameter(3, java.sql.Types.CHAR);
			callableStatement.registerOutParameter(4, java.sql.Types.CHAR);

			// Set the parameters
			callableStatement.setString(1, fileName);

			// Call the API
			callableStatement.execute();

			library = callableStatement.getString(2).trim();
			// Retrieve the returned values
			if (library.trim().equals(""))
			{
				String msg = "UTW55C call failed.";
				if (LOG.isErrorEnabled())
				{
					LOG.error(msg);
				}
			}
		}
		catch (SQLException sQLException)
		{
			if (LOG.isErrorEnabled())
			{

				if (LOG.isErrorEnabled())
				{
					LOG.error(sQLException);
				}
			}

		}
		finally
		{

			SQLToolbox.closePreparedStatement(callableStatement);
		}
		return library;
	}

}
