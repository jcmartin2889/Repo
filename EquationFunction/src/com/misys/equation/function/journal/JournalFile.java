package com.misys.equation.function.journal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.Element;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;

public class JournalFile
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: JournalFile.java 14802 2012-11-05 11:56:11Z williae1 $";

	private final static String GZPREFIX = "GZX";
	private final static String GZSUFFIX = "1";
	private final static String GZRECSUFFIX = "PR";

	/** Standard prefix for repeating journal records */
	private final static String GSPREFIX = "GSX";
	/** Standard suffix for repeating journal records */
	private final static String GSSUFFIX = "1";

	/** The maximum journal record size (9999) */
	public final static int MAX_JOURNAL_BUFFER_SIZE = 9999;

	/** The buffer size required for the standard journal fields */
	public final static int STANDARD_JOURNAL_FIELD_SIZE = 20;

	private final String optionId;
	private String fileName;
	private final String libraryName;
	private final Function function;

	/**
	 * An ordered list of standard journal columns and the associated column heading/field text
	 * <p>
	 * The 'value' is a String[] which is the set of column headers.
	 */
	private static Map<String, String[]> standardJournalFieldColumns = new LinkedHashMap<String, String[]>();

	/**
	 * Populates the standardJournalFieldColumns list
	 */
	static
	{
		standardJournalFieldColumns.put("GZWSID", new String[] { "Workstation", "Id" });
		standardJournalFieldColumns.put("GZDIM", new String[] { "Day in Month" });
		standardJournalFieldColumns.put("GZTIM", new String[] { "Time", "HHMMSS" });
		standardJournalFieldColumns.put("GZSEQ", new String[] { "Sequence", "Number" });
		standardJournalFieldColumns.put("GZIMG", new String[] { "Journal", "Image" });
		standardJournalFieldColumns.put("GZEFC", new String[] { "Apply", "EFC?" });
		standardJournalFieldColumns.put("GZSMCK", new String[] { "Suppress", "Maker", "Checker" });
		standardJournalFieldColumns.put("GZ4STS", new String[] { "Maker", "Checker", "Status" });
	}

	/**
	 * Constructor for a function
	 * 
	 * @param function
	 *            function
	 */
	public JournalFile(Function function)
	{
		this.optionId = function.getId();
		this.libraryName = "";
		this.fileName = JournalFile.getJournalName(optionId);
		this.function = function;
	}

	/**
	 * Constructor for a function and a library
	 * 
	 * @param function
	 *            function
	 * @param library
	 *            - where the journal file is located
	 */
	public JournalFile(Function function, String library)
	{
		this.optionId = function.getId();
		this.libraryName = library;
		this.fileName = JournalFile.getJournalName(optionId);
		this.function = function;
	}

	/**
	 * Return the name of the journal header file for the service
	 */
	public static String getJournalName(String optionId)
	{
		return GZPREFIX + optionId + GZSUFFIX;
	}

	/**
	 * Return the name of the journal detail (repeating) file for the service
	 */
	public static String getJournalDetailName(String optionId)
	{
		return GSPREFIX + optionId + GSSUFFIX;
	}

	/**
	 * Set the file name
	 * 
	 * @param fileName
	 *            - the file name
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * Return the full path of the GZ file
	 */
	private String getFullPath()
	{
		if (libraryName.equals(""))
		{
			return fileName;
		}
		else
		{
			return libraryName + "/" + fileName;
		}
	}

	/**
	 * Generate the standard GZ fields
	 * 
	 * @return SQL command for the GZ creation
	 */
	private String createStandardGZFields()
	{
		StringBuffer str = new StringBuffer();
		str.append("GZWSID CHAR(4) NOT NULL DEFAULT '', ");
		str.append("GZDIM NUMERIC(2, 0) NOT NULL DEFAULT 0 , ");
		str.append("GZTIM NUMERIC(6, 0) NOT NULL DEFAULT 0 , ");
		str.append("GZSEQ DECIMAL(7, 0) NOT NULL DEFAULT 0 , ");
		str.append("GZIMG CHAR(1) NOT NULL DEFAULT '', ");
		str.append("GZEFC CHAR(1) NOT NULL DEFAULT '', ");
		str.append("GZSMCK CHAR(1) NOT NULL DEFAULT '', ");
		str.append("GZ4STS CHAR(1) NOT NULL DEFAULT '' ");
		// If additional fields are added, the STANDARD_JOURNAL_FIELD_SIZE
		// constant must be updated
		return str.toString();
	}

	/**
	 * Generate column heading text for the standard GZ columns
	 * 
	 * @return SQL command to specify column headings
	 */
	private String createStandardGZFieldColumnHeadings()
	{
		StringBuffer str = new StringBuffer();

		for (Entry<String, String[]> entry : standardJournalFieldColumns.entrySet())
		{
			if (str.length() != 0)
			{
				str.append(", ");
			}
			str.append(entry.getKey());
			str.append(" IS '");
			str.append(getColumnHeadingFromStringArray(entry.getValue()));
			str.append("'");

		}
		return str.toString();
	}

	/**
	 * Builds a column heading string
	 * <p>
	 * Column headings are 3 x 20 characters, so if there are multiple headings, each heading (apart from the last) needs to be
	 * padded to 20 characters in order to achieve the required multi-line effect.
	 * 
	 * @param strings
	 *            a String[] containing the individual column headings
	 * @return a String containing the full column heading
	 */
	private String getColumnHeadingFromStringArray(String[] strings)
	{
		StringBuffer result = new StringBuffer();
		for (int index = 0; index < strings.length; index++)
		{
			String toAdd = strings[index];
			if (index < strings.length)
			{
				toAdd = Toolbox.pad(toAdd, 20);
			}
			result.append(toAdd);
		}
		return result.toString();
	}

	/**
	 * Generate Field Text for the standard GZ columns
	 * 
	 * @return SQL command to specify Field Text
	 */
	private String createStandardGZFieldTexts()
	{
		StringBuffer result = new StringBuffer();
		for (Entry<String, String[]> entry : standardJournalFieldColumns.entrySet())
		{
			if (result.length() != 0)
			{
				result.append(", ");
			}
			result.append(entry.getKey());
			result.append(" TEXT  IS '");
			String[] strings = entry.getValue();
			for (int index = 0; index < strings.length; index++)
			{
				result.append(strings[index]);
				if (index < strings.length)
				{
					result.append(" ");
				}
			}
			result.append("'");
		}
		return result.toString();
	}

	/**
	 * Generate the CREATE TABLE SQL command
	 * 
	 * @return SQL command for the GZ creation
	 */
	private String createTableSQL()
	{
		StringBuffer str = new StringBuffer();

		// Keep track of the list of fields
		List<String> listFields = new ArrayList<String>();

		// Table command
		str.append("CREATE TABLE " + getFullPath() + "( ");
		str.append(createStandardGZFields());

		// Loop through all InputFields
		for (InputFieldSet fieldSet : function.getInputFieldSets())
		{
			for (InputField inputField : fieldSet.getInputFields())
			{
				// Phase 2 does not support repeating fields being journalled
				if (!Field.isRepeating(inputField))
				{
					// Field
					String fieldName = SQLToolbox.cvtToSQLFieldName(inputField.getId());

					// add if not yet existing
					if (listFields.indexOf(fieldName) == -1)
					{
						str.append(", ");
						String fieldDataType = inputField.getDataType();
						String fieldType = SQLToolbox.cvtToSQLFieldType(fieldDataType, inputField.getSize(), inputField
										.getDecimals());
						String fieldDefault = SQLToolbox.cvtToSQLDefault(fieldDataType);
						str.append(fieldName + " " + fieldType + " " + " not null default " + fieldDefault);
						listFields.add(fieldName);

					}
				}
			}
		}

		// End
		str.append(" ) ");
		// RCDFMT " + getJournalRcdName());

		return str.toString();
	}

	/**
	 * Generate the LABEL TABLE SQL command
	 * 
	 * @return SQL command for the GZ creation
	 */
	private String createLabelTableSQL()
	{
		return "LABEL ON TABLE " + getFullPath() + " IS '" + SQLToolbox.cvtToSQLFieldColHdg(function.getLabel()) + "'";
	}

	/**
	 * Generates a LABEL ON COLUMN SQL command
	 * 
	 * @param isFieldText
	 *            a boolean indicating whether generating Field Text or Column Heading SQL script
	 * 
	 * @return SQL command for the generation of either Field Text or Column Heading text
	 */
	private String createLabelFieldSQL(boolean isFieldText)
	{
		StringBuffer str = new StringBuffer();

		String sqlOperand = isFieldText ? " TEXT IS " : " IS ";

		// Keep track of the list of fields
		List<String> listFields = new ArrayList<String>();

		// Table command
		str.append("LABEL ON COLUMN " + getFullPath() + "( ");
		str.append(isFieldText ? createStandardGZFieldTexts() : createStandardGZFieldColumnHeadings());

		// Loop through all InputFields
		for (InputFieldSet fieldSet : function.getInputFieldSets())
		{
			for (InputField inputField : fieldSet.getInputFields())
			{
				// Phase 2 does not support repeating fields being journalled
				if (!Field.isRepeating(inputField))
				{
					String fieldName = SQLToolbox.cvtToSQLFieldName(inputField.getId());
					// add only if not existing
					if (listFields.indexOf(fieldName) == -1)
					{
						str.append(", ");
						String label = inputField.getLabel();
						if (label.trim().equals(Element.DEFAULT_TEXT_VALUE))
						{
							label = inputField.getId();
						}
						str.append(fieldName + sqlOperand + "'" + SQLToolbox.cvtToSQLFieldColHdg(label) + "'");
						listFields.add(fieldName);
					}
				}
			}
		}

		// End
		str.append(" ) ");
		return str.toString();
	}

	/**
	 * Return the option Id
	 * 
	 * @return the option Id
	 */
	public String getOptionId()
	{
		return optionId;
	}

	/**
	 * Return the function this journal file is attached to
	 * 
	 * @return the function
	 */
	public Function getFunction()
	{
		return function;
	}

	/**
	 * Return the file name
	 * 
	 * @return the file name
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Return the library
	 * 
	 * @return the library
	 */
	public String getLibraryName()
	{
		return libraryName;
	}

	/**
	 * Determine if journal file already exists
	 * 
	 * @param connection
	 *            a <code>Connection</code>
	 * @return true if the file exist
	 * @throws EQException
	 */
	public boolean isFileExists(Connection connection) throws EQException
	{
		Statement statement = null;
		ResultSet resultSet = null;
		boolean exists = false;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			String sqlStatement = "SELECT COUNT(*) FROM SYSTABLES WHERE TABLE_NAME = '" + fileName.trim() + "' AND "
							+ "SYSTEM_TABLE_SCHEMA = '" + libraryName.trim() + "'";
			resultSet = statement.executeQuery(sqlStatement);
			if (resultSet.next())
			{
				if (resultSet.getInt(1) == 1)
				{
					exists = true;
				}
			}
			return exists;
		}
		catch (SQLException e)
		{
			throw new EQException("JournalFile: isFileExists Failed: ", e);
		}
		finally
		{
			SQLToolbox.close(resultSet);
			SQLToolbox.close(statement);
		}
	}

	/**
	 * Drop the journal file
	 * 
	 * @throws EQException
	 * 
	 */
	public void dropFile(Connection connection) throws EQException
	{
		Statement statement = null;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			String sqlStatement = "DROP TABLE " + getFullPath();
			statement.execute(sqlStatement);
		}
		catch (SQLException e)
		{
			throw new EQException("JournalFile: dropFile Failed: ", e);
		}
		finally
		{
			SQLToolbox.close(statement);
		}
	}

	/**
	 * Create the Journal File
	 * 
	 * @param connection
	 *            a <code>Connection</code>
	 * @throws EQException
	 */
	public void writeFile(EquationStandardSession session, String inpLibrary) throws EQException
	{
		Statement statement = null;
		try
		{
			// create the statement
			statement = session.getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			// create the table
			String sqlStatement = createTableSQL();
			statement.execute(sqlStatement);

			// change the label of the table
			sqlStatement = createLabelTableSQL();
			statement.execute(sqlStatement);

			// Set the column headings
			sqlStatement = createLabelFieldSQL(false);
			statement.execute(sqlStatement);

			// Set the Field text for the columns
			sqlStatement = createLabelFieldSQL(true);
			statement.execute(sqlStatement);
		}
		catch (SQLException e)
		{
			throw new EQException("JournalFile: writeFile Failed: ", e);
		}
		finally
		{
			SQLToolbox.close(statement);
		}
	}

	/**
	 * Determine whether the field name is a standard GZ field name
	 * 
	 * @param fieldName
	 *            - the field name to be validated
	 * @return true if the field name is a standard GZ field name
	 */
	public static boolean isStandardGZField(String fieldName)
	{
		return standardJournalFieldColumns.get(fieldName) != null;
	}
}