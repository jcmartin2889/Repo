package com.misys.equation.common.files;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;

public abstract class DatabaseFile
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DatabaseFile.java 13250 2012-04-18 22:22:11Z jonathan.perkins $";

	// file name
	private String eqFileName;

	// library where the file name is located
	private String library;

	// EQ Logger
	protected final EquationLogger LOG = new EquationLogger(this.getClass());

	/**
	 * Construct an empty EQFile
	 */
	public DatabaseFile()
	{
		eqFileName = "";
		library = "";
	}

	/**
	 * Return the file name
	 * 
	 */
	public String getEqFileName()
	{
		return eqFileName;
	}

	/**
	 * Set the file name
	 * 
	 * @param eqFileName
	 *            - the file name
	 */
	public void setEqFileName(String eqFileName)
	{
		this.eqFileName = eqFileName;
	}

	/**
	 * Return the library where the file is located
	 * 
	 * @return the library where the file is located
	 */
	public String getLibrary()
	{
		return library;
	}

	/**
	 * Set the library where the file is located
	 * 
	 * @param library
	 *            - the library where the file is located
	 */
	public void setLibrary(String library)
	{
		this.library = library;
	}

	/**
	 * Return the file name
	 */
	public String rtvFullPath()
	{
		if (library.equals(""))
		{
			return eqFileName;
		}
		else
		{
			return library + "/" + eqFileName;
		}
	}

	/**
	 * Return the list of field names in the format of:
	 * <p>
	 * field1, field2, field3, ...
	 * <p>
	 * The ordering must be the same as the rtvFieldValues();
	 * 
	 * @return the list of field names
	 */
	public abstract String rtvFieldNames();

	/**
	 * Return the list of field names in the format of ?, ?, ?, ...
	 * 
	 * @return the list of "?" which corresponds to the field names
	 */
	public abstract String rtvFieldNames2();

	/**
	 * Return the list of field values in the format of:
	 * <p>
	 * value1, value2, value3, ...
	 * <p>
	 * The ordering must be the same as the rtvFieldNames();
	 * 
	 * @return the list of field values
	 */
	public abstract String rtvFieldValues();

	/**
	 * Return the list of name and value pairs in the format of:
	 * <p>
	 * field1=value1, field2=value2, field3=value3, ...
	 * 
	 * @return the list of name and value pairs
	 */
	public abstract String rtvFieldNamesValues();

	/**
	 * Return the list of name and value pairs in the format of:
	 * <p>
	 * field1=?, field2=?, field3=?, ...
	 * 
	 * @return the list of name and value pairs
	 */
	public abstract String rtvFieldNamesValues2();

	/**
	 * Return the list of name and values pairs to select this record from the file in the format of:
	 * <p>
	 * field1=value1 and field2=value2 and field3=value3 and ...
	 * 
	 * @return the list of name and values pairs to select this record from the file
	 */
	public abstract String rtvSQLRecord();

	/**
	 * Return the key field for this record
	 */
	public abstract String rtvKey();

	/**
	 * Set up the field values after retrieving the record
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param resultset
	 *            - the resultset
	 * 
	 */
	protected abstract void rtvData(EquationStandardSession session, ResultSet resultset);

	/**
	 * Set the parameter for the prepared statement. This must corresponds to the number of ? from the methods rtvFieldNames2() and
	 * rtvFieldNamesValues2
	 * 
	 * @param statement
	 *            - the SQL statement
	 * 
	 * @throws EQException
	 */
	protected abstract void chgPreparedStatement(EquationStandardSession session, PreparedStatement statement) throws EQException;

	/**
	 * Execute a query
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param sqlStatement
	 *            - the SQL statement
	 * 
	 * @throws EQException
	 */
	public void executeStatement(EquationStandardSession session, String sqlStatement) throws EQException
	{
		Statement statement = null;
		try
		{
			Connection connection = session.getConnection();
			statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			statement.execute(sqlStatement);
		}
		catch (SQLException e)
		{
			throw new EQException("EQFile: executeStatement() Failed: " + Toolbox.getExceptionMessage(e), e);
		}
		finally
		{
			SQLToolbox.close(statement);
		}
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
	protected void executePreparedStatement(EquationStandardSession session, String sqlStatement) throws EQException
	{
		PreparedStatement statement = null;

		try
		{
			Connection connection = session.getConnection();
			statement = connection.prepareStatement(sqlStatement);
			chgPreparedStatement(session, statement);
			statement.execute();
		}
		catch (SQLException e)
		{
			throw new RuntimeException("EQFile: executePreparedStatement() Failed: " + Toolbox.getExceptionMessage(e), e);
		}
		finally
		{
			SQLToolbox.close(statement);
		}
	}

	/**
	 * Perform SELECT COUNT(*) on the specified where statement
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param sqlWhereStatement
	 *            - the WHERE clause of the SQL statement
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 * 
	 */
	public boolean executeCount(EquationStandardSession session, String sqlWhereStatement)
	{
		Statement statement = null;
		ResultSet resultset = null;
		boolean exists = false;
		try
		{
			String sqlStatement = "SELECT COUNT(*) FROM " + rtvFullPath() + " WHERE " + sqlWhereStatement;
			Connection connection = session.getConnection();
			statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			resultset = statement.executeQuery(sqlStatement);
			if (resultset.next())
			{
				if (resultset.getInt(1) == 0)
				{
					exists = false;
				}
				else
				{
					exists = true;
				}
			}
			else
			{
				throw new RuntimeException("EQFile: executeCount() failed: cannot read from " + rtvFullPath());
			}
		}
		catch (SQLException e)
		{
			throw new RuntimeException("EQFile: executeCount() Failed: " + Toolbox.getExceptionMessage(e), e);

		}
		finally
		{
			SQLToolbox.close(resultset);
			SQLToolbox.close(statement);
		}

		// record selected?
		return exists;
	}

	/**
	 * Determine whether the record already exists
	 * 
	 * @return true if the record already exists
	 * 
	 */
	public boolean chkRecordExists(EquationStandardSession session)
	{
		return executeCount(session, rtvSQLRecord());
	}

	/**
	 * Retrieve the record by the condition specified in rtvSQLRecord()
	 * 
	 * @return true if the record has been retrieved
	 *         <p>
	 *         false if the record has not been retrieved
	 * 
	 */
	public boolean rtvRecord(EquationStandardSession session)
	{
		Statement statement = null;
		ResultSet resultset = null;
		boolean exists = false;
		try
		{
			String sqlStatement = "SELECT " + rtvFieldNames() + " FROM " + rtvFullPath() + " WHERE " + rtvSQLRecord();
			Connection connection = session.getConnection();
			statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			resultset = statement.executeQuery(sqlStatement);
			if (resultset.next())
			{
				rtvData(session, resultset);
				exists = true;
			}
		}
		catch (SQLException e)
		{
			throw new RuntimeException("EQFile: rtvRecord() Failed: " + Toolbox.getExceptionMessage(e), e);
		}
		finally
		{
			SQLToolbox.close(resultset);
			SQLToolbox.close(statement);
		}

		// record exists?
		return exists;
	}

	/**
	 * Add or update the record to the file
	 * 
	 * @param session
	 *            - the Equation standard session
	 * 
	 * @return true - if the record has been added
	 *         <p>
	 *         false - if the record has been updated
	 * 
	 */
	protected boolean update(EquationStandardSession session, boolean prepStatement) throws EQException
	{
		boolean added = false;
		boolean exists = chkRecordExists(session);

		// update if existing
		if (exists)
		{
			if (prepStatement)
			{
				String sqlStatement = "UPDATE " + rtvFullPath() + " SET " + rtvFieldNamesValues2() + " WHERE " + rtvSQLRecord();
				executePreparedStatement(session, sqlStatement);
			}
			else
			{
				String sqlStatement = "UPDATE " + rtvFullPath() + " SET " + rtvFieldNamesValues() + " WHERE " + rtvSQLRecord();
				executeStatement(session, sqlStatement);
			}
			added = false;
		}

		// add if not existing
		else
		{
			if (prepStatement)
			{
				String sqlStatement = "INSERT INTO " + rtvFullPath() + " (" + rtvFieldNames() + ") VALUES (" + rtvFieldNames2()
								+ ")";
				executePreparedStatement(session, sqlStatement);
				added = true;
			}
			else
			{
				String sqlStatement = "INSERT INTO " + rtvFullPath() + " (" + rtvFieldNames() + ") VALUES ( " + rtvFieldValues()
								+ ")";
				executeStatement(session, sqlStatement);
			}
			added = true;
		}

		// record added or updated?
		return added;
	}

	/**
	 * Add or update the record to the file
	 * 
	 * @param session
	 *            - the Equation Standard session
	 * 
	 * @return true - if the record has been added
	 *         <p>
	 *         false - if the record has been updated
	 * 
	 */
	public boolean update(EquationStandardSession session) throws EQException
	{
		return update(session, false);
	}

	/**
	 * Delete the record from the file
	 * 
	 * @param session
	 *            - the Equation standard session
	 * 
	 * @return true - if the record has been deleted
	 *         <p>
	 *         false - if the record is not existing
	 * 
	 */
	public boolean delete(EquationStandardSession session) throws EQException
	{
		boolean deleted = false;
		boolean exists = chkRecordExists(session);

		// delete if existing
		if (exists)
		{
			String sqlStatement = "DELETE FROM " + rtvFullPath() + " WHERE " + rtvSQLRecord();
			executeStatement(session, sqlStatement);
			deleted = true;
		}

		// deleted?
		return deleted;
	}

	/**
	 * Start journalling of the file. The journal file is the default journal file of the Equation unit
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param unitName
	 *            - unit mnemonic
	 * 
	 * @return true if start journalling is successful
	 * 
	 * @throws EQException
	 */
	public boolean startJournal(EquationStandardSession session, String unitName) throws EQException
	{
		try
		{
			// Note that the standard non-default options when starting journalling are to journal both images (before and after)
			// and to omit file open/close journal entries
			String journalFile = session.getUnit().getNIJournalFullPath();
			String command = "STRJRNPF FILE(" + rtvFullPath() + ")" + " JRN(" + journalFile + ") IMAGES(*BOTH) OMTJRNE(*OPNCLO)";
			executeStatement(session, SQLToolbox.getQcmdexcString(command));
		}
		catch (EQException e)
		{
			String message = Toolbox.getExceptionMessage(e);
			if ((message.indexOf("CPF7030")) == -1)
			{
				throw e;
			}
			return false;
		}
		return true;
	}

	/**
	 * End journalling of the file.
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param unitName
	 *            - unit mnemonic
	 * 
	 * @return true if end journalling is successful
	 * 
	 * @throws EQException
	 */
	public boolean endJournal(EquationStandardSession session, String unitName) throws EQException
	{
		String journalFile = session.getUnit().getNIJournalFullPath();
		String command = "ENDJRNPF FILE(" + rtvFullPath() + ")" + " JRN(" + journalFile + ")";
		String sqlStatement = SQLToolbox.getQcmdexcString(command);
		try
		{
			executeStatement(session, sqlStatement);
		}
		catch (EQException e)
		{
			String message = Toolbox.getExceptionMessage(e);
			if ((message.indexOf("CPF7032")) == -1)
			{
				throw e;
			}
			return false;
		}
		return true;
	}

	/**
	 * Retrieve a list of records from the file
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param aclass
	 *            - the EQFile class
	 * @param whereClause
	 *            - the selection
	 * 
	 * @return a hashtable of EQFiles
	 * 
	 * @throws EQException
	 */
	public Hashtable<String, DatabaseFile> rtvList(EquationStandardSession session, Class aclass, String whereClause)
					throws EQException
	{
		Hashtable<String, DatabaseFile> results = new Hashtable<String, DatabaseFile>();
		DatabaseFile result = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try
		{
			Connection connection = session.getConnection();
			String sqlStatement = "SELECT " + rtvFieldNames() + " FROM " + rtvFullPath();
			if (whereClause != null)
			{
				sqlStatement = sqlStatement + " WHERE " + whereClause;
			}
			statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(sqlStatement);
			while (resultSet.next())
			{
				result = (DatabaseFile) aclass.newInstance();
				result.rtvData(session, resultSet);
				results.put(result.rtvKey().trim(), result);
			}
		}
		catch (Exception e)
		{
			throw new EQException("EQFile: rtvList()" + Toolbox.getExceptionMessage(e), e);
		}
		finally
		{
			SQLToolbox.close(resultSet);
			SQLToolbox.close(statement);
		}
		return results;
	}
}