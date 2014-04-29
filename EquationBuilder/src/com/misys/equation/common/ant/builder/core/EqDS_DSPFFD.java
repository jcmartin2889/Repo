package com.misys.equation.common.ant.builder.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.misys.equation.common.ant.builder.helpText.backEnd.SQLToolbox;

public class EqDS_DSPFFD extends EquationDataStructure
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqDS_DSPFFD.java 10419 2011-02-03 11:37:34Z MACDONP1 $";
	private final String file;
	private static final EquationLogger LOG = new EquationLogger(EqDS_DSPFFD.class);

	/**
	 * Construct an Equation Data Structure from a file
	 * 
	 * @param file
	 *            - file
	 * @param session
	 *            - the Equation Standard Session
	 * 
	 */
	public EqDS_DSPFFD(String file, EquationStandardSession session)
	{
		super(file, 37);
		this.file = file;

		setUp(session);
		// final setup
		initialDefaultValue();
	}

	private void setUp(EquationStandardSession session)
	{
		// get a connection
		Connection connection = session.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;

		try
		{
			// generate the DSPFFD
			statement = connection.createStatement();
			String command = "DSPFFD FILE(" + file + ") OUTPUT(*OUTFILE) OUTFILE(QTEMP/GZFFD) OUTMBR(*FIRST *REPLACE)";

			// generate the cmdexec
			String query = SQLToolbox.getQcmdexcString(command);

			// execute and process it
			statement.execute(query);
			statement = connection.createStatement();
			resultSet = statement
							.executeQuery("select WHFILE, WHFLDI, WHFLDB, WHFLDD, WHFLDP, WHFTXT, WHFLDT, WHRFLD  from qtemp/GZFFD");

			// file, field name, field lengths in bytes, number of digits, decimal places, desc, ref field
			initialiseFFD(resultSet);
		}
		catch (Exception exception)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("EqDS_DSPFFD: initialiseFFD(): SQL Error", exception);
			}
		}
		finally
		{
			deleteFile(statement, resultSet, connection);
		}
	}

	/**
	 * this method will delete the file.
	 * 
	 * @param statement
	 *            this is the current <code>Statement</code>.
	 * @param resultSet
	 *            this is the current <code>ResultSet</code>.
	 * @param connection
	 *            this is the current <code>Connection</code>.
	 */
	private void deleteFile(Statement statement, ResultSet resultSet, Connection connection)
	{
		// delete the file
		try
		{
			SQLToolbox.closeResulset(resultSet);
			SQLToolbox.closeStatement(statement);

			String command = "DLTF FILE(QTEMP/GZFFD)";
			String query = SQLToolbox.getQcmdexcString(command);
			statement = connection.createStatement();
			statement.execute(query);
		}
		catch (Exception exception)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("EqDS_DSPFFD: setup(): SQL Error", exception);
			}
		}
		finally
		{
			SQLToolbox.closeStatement(statement);
		}

	}

	/**
	 * 
	 * @param resultSet
	 */
	private void initialiseFFD(ResultSet resultSet)
	{
		try
		{
			while (resultSet.next())
			{
				String fieldName = resultSet.getString(2).trim();
				int fieldLen = Integer.parseInt(resultSet.getString(3));
				int fieldDig = Integer.parseInt(resultSet.getString(4));
				int decimal = Integer.parseInt(resultSet.getString(5));
				String fieldLabel = resultSet.getString(6).trim();
				String fieldType = resultSet.getString(7).trim();

				if (fieldType.equals(EqDataType.TYPE_PACKED))
				{
					createPackedField(fieldName, fieldLabel, 0, fieldDig, decimal);
				}
				else if (fieldType.equals(EqDataType.TYPE_ZONED))
				{
					createZonedField(fieldName, fieldLabel, 0, fieldDig, decimal);
				}
				else
				{
					createCharacterField(fieldName, fieldLabel, 0, fieldLen);
				}
			}

		}
		catch (Exception exception)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("EqDS_DSPFFD: initialiseFFD(): SQL Error", exception);
			}
		}
		finally
		{

			SQLToolbox.closeResulset(resultSet);
		}
	}

	/**
	 * Return the file name
	 * 
	 * @return the file name
	 */
	public String getFile()
	{
		return file;
	}

}
