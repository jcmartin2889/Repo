package com.misys.equation.common.ant.builder.helpText.backEnd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.misys.equation.common.ant.builder.core.EquationDataStructure;
import com.misys.equation.common.ant.builder.core.EquationDataStructureData;
import com.misys.equation.common.ant.builder.core.EquationLogger;
import com.misys.equation.common.ant.builder.core.EquationStandardTable;
import com.misys.equation.common.ant.builder.helpText.helpers.Toolbox;

public class SQLToolbox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SQLToolbox.java 4741 2009-09-16 16:33:23Z esther.williams $";
	private static final EquationLogger LOG = new EquationLogger(SQLToolbox.class);

	/**
	 * Formats a valid iSeries command into the required format for calling QCMDEXC
	 * <p>
	 * Note that one reason for creating a method to wrap this relatively simple processing is to ensure the correct format of the
	 * length (must be 15 digits, with 5 decimal places). Although some levels of the iSeries OS do allow the length parameter to be
	 * unformatted, on other levels an unformatted length will fail with java.sql.SQLException: [CPF0006] Errors occurred in command
	 * 
	 * @param command
	 *            A valid OS400 command string
	 * 
	 * @return a String containing the formatted SQL required to call QCMDEXC with the specified command
	 */
	public static String getQcmdexcString(String command)
	{
		return "CALL QCMDEXC('" + command + "'," + Toolbox.leftZeroPad(command.length(), 10) + ".00000)";
	}

	/**
	 * @param table
	 * @return
	 */
	public static String getWhereFromTable(EquationStandardTable table)
	{
		StringBuilder sqlString = new StringBuilder();
		EquationDataStructureData tableData = table.getTableData();
		EquationDataStructure tableDataStructure = tableData.getEqDS();
		String[] keyNames = table.getKeys().split(":");
		for (int i = 0; i < keyNames.length; i++)
		{
			if (i > 0)
			{
				sqlString.append(" AND ");
			}
			String quote = "";
			int fieldDataType = tableDataStructure.getFieldDataType(keyNames[i]);
			if (fieldDataType == Types.CHAR || fieldDataType == Types.BINARY || fieldDataType == Types.VARCHAR
							|| fieldDataType == Types.VARBINARY)
			{
				quote = "'";
			}
			sqlString.append(keyNames[i] + "=" + quote + tableData.getFieldValue(keyNames[i]) + quote);
		}
		return sqlString.toString();
	}

	/**
	 * This method will close the resulSet
	 * 
	 * @param resultSet
	 *            this is the resulSet to be closed.
	 */
	public static void closeResulset(ResultSet resultSet)
	{

		try
		{
			if (resultSet != null)
			{
				resultSet.close();
			}

		}
		catch (SQLException sQLException)
		{
			if (LOG.isErrorEnabled())
			{

				LOG.error(sQLException);
			}

		}
	}

	/**
	 * This method will close the PreparedStatement.
	 * 
	 * @param resultSet
	 *            this is the PreparedStatement to be closed.
	 */
	public static void closePreparedStatement(PreparedStatement preparedStatement)
	{

		try
		{
			if (preparedStatement != null)
			{
				preparedStatement.close();
			}

		}
		catch (SQLException sQLException)
		{
			if (LOG.isErrorEnabled())
			{

				LOG.error(sQLException);
			}

		}
	}

	/**
	 * This method will close the Statement.
	 * 
	 * @param resultSet
	 *            this is the Statement to be closed.
	 */
	public static void closeStatement(Statement statement)
	{

		try
		{
			if (statement != null)
			{
				statement.close();
			}

		}
		catch (SQLException sQLException)
		{
			if (LOG.isErrorEnabled())
			{

				LOG.error(sQLException);
			}

		}
	}

}
