package com.misys.equation.function.journal;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.misys.equation.common.access.EquationDataStructure;
import com.misys.equation.common.access.EquationDataStructureData;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.files.DatabaseFile;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.FieldData;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.RepeatingFieldData;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.tools.PluginVersion;

public class JournalRecord extends DatabaseFile
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: JournalRecord.java 17453 2013-10-18 14:41:52Z williae1 $";

	// Function data image
	public final static String IMAGE_AFT = "A";
	public final static String IMAGE_BEF = "B";

	// Standard journal key of Equation
	private String workstationID;
	private int jrnDay;
	private int jrnTime;
	private int jrnSequence;
	private String image;

	// Other standard key
	private String efc;
	private String suppMakerChecker;
	private String statMakerChecker;

	// Flag to check if function is Maker Checker enabled
	private boolean isMakerCheckerService = false;

	// Journal data in data structure format
	Function function;
	EquationDataStructure jrnDs;
	EquationDataStructureData jrnData;

	// Source field
	FunctionData functionData;

	/**
	 * Constructor
	 * 
	 */
	public JournalRecord(Function function)
	{
		super();
		commonInitialisation();
		setEqFileName(JournalFile.getJournalName(function.getId()));

		this.function = function;
		this.jrnDs = null;
		this.jrnData = null;

		// Does Equation service has the maker/checker fields?
		if (function != null && PluginVersion.isMakerCheckerService(function.getPluginVersion()))
		{
			isMakerCheckerService = true;
		}
	}

	/**
	 * Common initialisation
	 */
	private void commonInitialisation()
	{
		efc = "";
		suppMakerChecker = "";
		statMakerChecker = "";
	}

	/**
	 * Set the journal key based on the journal header
	 * 
	 */
	public void chgJournalKey(JournalHeader journalHeader)
	{
		this.workstationID = journalHeader.getWorkstationID();
		this.jrnDay = journalHeader.getJrnDay();
		this.jrnTime = journalHeader.getJrnTime();
		this.jrnSequence = journalHeader.getJrnSequence();
		this.image = journalHeader.getFunctionMode();
	}

	/**
	 * Return the workstation ID
	 * 
	 * @return the workstation ID
	 */
	public String getWorkstationID()
	{
		return workstationID;
	}

	/**
	 * Set the workstation ID
	 * 
	 * @param workstationID
	 *            workstation ID
	 */
	public void setWorkstationID(String workstationID)
	{
		if (workstationID.length() <= 4)
		{
			this.workstationID = workstationID;
		}
		else
		{
			this.workstationID = workstationID.substring(0, 4);
		}
	}

	/**
	 * Return the day of the month of the journal
	 * 
	 * @return the day in month
	 */
	public int getJrnDay()
	{
		return jrnDay;
	}

	/**
	 * Set the day of the month of the journal
	 * 
	 * @param jrnDay
	 *            day in month
	 */
	public void setJrnDay(int jrnDay)
	{
		this.jrnDay = jrnDay;
	}

	/**
	 * Return the time
	 * 
	 * @return the time
	 */
	public int getJrnTime()
	{
		return jrnTime;
	}

	/**
	 * Set the journal time
	 * 
	 * @param jrnTime
	 *            journal time
	 */
	public void setJrnTime(int jrnTime)
	{
		this.jrnTime = jrnTime;
	}

	/**
	 * Return the journal sequence number
	 * 
	 * @return the journal sequence number
	 */
	public int getJrnSequence()
	{
		return jrnSequence;
	}

	/**
	 * Set the journal sequence number
	 * 
	 * @param jrnSequence
	 *            journal sequence number
	 */
	public void setJrnSequence(int jrnSequence)
	{
		this.jrnSequence = jrnSequence;
	}

	/**
	 * Return the journal image
	 * 
	 * @return journal image
	 */
	public String getImage()
	{
		return image;
	}

	/**
	 * Set the journal image
	 * 
	 * @param image
	 *            journal image
	 */
	public void setImage(String image)
	{
		this.image = image;
	}

	/**
	 * Return the EFC flag
	 * 
	 * @return the EFC flag
	 */
	public String getEfc()
	{
		return efc;
	}

	/**
	 * Set the EFC flag
	 * 
	 * @param efc
	 *            - the EFC flag
	 */
	public void setEfc(String efc)
	{
		this.efc = efc;
	}

	/**
	 * Return the journal details
	 * 
	 * @return the journal d
	 */
	public EquationDataStructureData getJrnData()
	{
		return jrnData;
	}

	/**
	 * Set the journal details
	 * 
	 * @param jrnData
	 *            journal data structure data details
	 */
	public void setJrnData(EquationDataStructureData jrnData)
	{
		this.jrnData = jrnData;
	}

	/**
	 * Set after image
	 * 
	 */
	public void markAfterImg()
	{
		setImage(IMAGE_AFT);
	}

	/**
	 * Set before image
	 * 
	 */
	public void markBeforeImg()
	{
		setImage(IMAGE_BEF);
	}

	/**
	 * Retrieve the journal data in bytes
	 * 
	 */
	public byte[] rtvJrnDataBytes()
	{
		if (jrnData == null)
		{
			return null;
		}
		else
		{
			return jrnData.getBytes();
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
	@Override
	public String rtvFieldNames()
	{
		return "*";
	}

	/**
	 * Return the list of field values in the format of:
	 * <p>
	 * value1, value2, value3, ...
	 * <p>
	 * The ordering must be the same as the rtvFieldNames();
	 * 
	 * @return the list of field values
	 */
	@Override
	public String rtvFieldValues()
	{
		return "";
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
	public String rtvLocalFieldNames()
	{
		String fieldnames = "GZWSID, GZDIM, GZTIM, GZSEQ, GZIMG, GZEFC";

		// Does Equation service has the maker/checker fields, then include the additional fields
		if (isMakerCheckerService)
		{
			fieldnames += ", GZSMCK, GZ4STS";
		}

		// Return field names
		return fieldnames;

	}

	/**
	 * Return the list of field names in the format of ?, ?, ?, ...
	 * 
	 * @return the list of "?" which corresponds to the field names
	 */
	@Override
	public String rtvFieldNames2()
	{
		return ""; // not used
	}

	/**
	 * Return the list of name and value pairs in the format of:
	 * <p>
	 * field1=?, field2=?, field3=?, ...
	 * 
	 * @return the list of name and value pairs
	 */
	@Override
	public String rtvFieldNamesValues2()
	{
		return ""; // not used
	}

	/**
	 * Set the parameter for the prepared statement. This must corresponds to the number of ? from the methods rtvFieldNames2() and
	 * rtvFieldNamesValues2
	 * 
	 * @param statement
	 *            - the SQL statement
	 */
	@Override
	protected void chgPreparedStatement(EquationStandardSession session, PreparedStatement statement) throws EQException
	{
		// Get all the record fieldSet
		int maxrecords = function.getInputFieldSets().size() - 1;
		int count = 0;

		for (int i = 0; i <= maxrecords; i++)
		{
			InputFieldSet fieldSet = function.getInputFieldSets().get(i);
			List<InputField> fields = fieldSet.getInputFields();
			int maxfields = fields.size() - 1;
			for (int j = 0; j <= maxfields; j++)
			{
				// Field name
				InputField field = fields.get(j);
				String fieldName = field.getId();

				// Field value
				String fieldValue;
				if (functionData == null)
				{
					fieldValue = jrnData.getFieldValue(fieldName);
				}
				else
				{
					// Suppress passwords
					if (field.isPassword())
					{
						fieldValue = "";
					}
					else
					{
						fieldValue = rtvValue(functionData, fieldName);
					}
				}

				// Not blank, include the field
				if (!fieldValue.equals(""))
				{
					count++;
					try
					{
						if (field.getDataType().equals(EqDataType.TYPE_DATE))
						{
							statement.setInt(count, Integer.parseInt(fieldValue));
						}
						else if (field.getDataType().equals(EqDataType.TYPE_PACKED)
										|| field.getDataType().equals(EqDataType.TYPE_ZONED))
						{
							statement.setBigDecimal(count, new BigDecimal(fieldValue));
						}
						else
						{
							statement.setString(count, fieldValue);
						}
					}
					catch (Exception e)
					{
						throw new EQException(LanguageResources.getFormattedString("JournalRecord.ErrorFieldValue", new String[] {
										fieldValue, fieldName }));
					}
				}
			}
		}
	}

	/**
	 * Return the list of field values in the format of:
	 * <p>
	 * value1, value2, value3, ...
	 * <p>
	 * The ordering must be the same as the rtvFieldNames();
	 * 
	 * @return the list of field values
	 */
	public String rtvLocalFieldValues()
	{
		StringBuilder fieldValues = new StringBuilder();
		fieldValues.append("'" + workstationID + "', " + jrnDay + "," + jrnTime + "," + jrnSequence + ",'" + image + "','" + efc
						+ "'");

		// Does Equation service has the maker/checker fields, then include the additional fields
		if (isMakerCheckerService)
		{
			fieldValues.append(",'" + suppMakerChecker + "','" + statMakerChecker + "'");
		}

		// return the field values
		return fieldValues.toString();
	}

	/**
	 * Return the list of name and value pairs in the format of:
	 * <p>
	 * field1=value1, field2=value2, field3=value3, ...
	 * 
	 * @return the list of name and value pairs
	 */
	@Override
	public String rtvFieldNamesValues()
	{
		return "";
	}

	/**
	 * Generate the INSERT SQL command using function data
	 * 
	 * @param functionData
	 *            FunctionData
	 * 
	 * @return SQL command for the GZ creation
	 */
	private String createAddRecordSQL(FunctionData functionData)
	{
		StringBuffer fieldNames = new StringBuffer(rtvLocalFieldNames());
		StringBuffer fieldValues = new StringBuffer(rtvLocalFieldValues());

		// Get all the record fieldSet
		int maxrecords = function.getInputFieldSets().size() - 1;
		for (int i = 0; i <= maxrecords; i++)
		{
			InputFieldSet fieldSet = function.getInputFieldSets().get(i);
			List<InputField> fields = fieldSet.getInputFields();
			int maxfields = fields.size() - 1;
			for (int j = 0; j <= maxfields; j++)
			{
				// Field name
				InputField field = fields.get(j);
				String fieldName = field.getId();

				// Field value
				String fieldValue = null;
				// Suppress passwords
				if (field.isPassword())
				{
					fieldValue = "";
				}
				else
				{
					fieldValue = rtvValue(functionData, fieldName);
				}

				// Not blank, include the field
				if (!fieldValue.equals(""))
				{
					// include comma
					fieldNames.append(", ");
					fieldValues.append(", ");

					fieldNames.append(SQLToolbox.cvtToSQLFieldName(fieldName));
					fieldValues.append("?");
				}
			}
		}

		// Insert command
		StringBuffer str = new StringBuffer();
		str.append("INSERT INTO " + rtvFullPath() + " ( " + fieldNames + " ) VALUES(" + fieldValues + ")");
		return str.toString();
	}

	/**
	 * Generate the INSERT SQL command using the journal data structure data
	 * 
	 * @return SQL command for the GZ creation
	 */
	private String createAddRecordSQL()
	{
		StringBuffer fieldNames = new StringBuffer(rtvLocalFieldNames());
		StringBuffer fieldValues = new StringBuffer(rtvLocalFieldValues());

		// Get all the record fieldSet
		int maxrecords = function.getInputFieldSets().size() - 1;
		for (int i = 0; i <= maxrecords; i++)
		{
			InputFieldSet fieldSet = function.getInputFieldSets().get(i);
			List<InputField> fields = fieldSet.getInputFields();
			int maxfields = fields.size() - 1;
			for (int j = 0; j <= maxfields; j++)
			{
				// Field name
				Field field = fields.get(j);
				String fieldName = field.getId();

				// Field value
				String fieldValue = jrnData.getFieldValue(fieldName);

				// Not blank, include the field
				if (!fieldValue.equals(""))
				{
					// include comma
					fieldNames.append(", ");
					fieldValues.append(", ");

					fieldNames.append(SQLToolbox.cvtToSQLFieldName(fieldName));
					fieldValues.append("?");
				}
			}
		}

		// Insert command
		StringBuffer str = new StringBuffer();
		str.append("INSERT INTO " + rtvFullPath() + " ( " + fieldNames + " ) VALUES(" + fieldValues + ")");
		return str.toString();
	}

	/**
	 * Return the list of name and values pairs to select this record from the file in the format of:
	 * <p>
	 * field1=value1 and field2=value2 and field3=value3 and ...
	 * 
	 * @return the list of name and values pairs to select this record from the file
	 */
	@Override
	public String rtvSQLRecord()
	{
		return "GZWSID='" + workstationID + "' and " + "GZDIM=" + jrnDay + " and " + "GZTIM=" + jrnTime + " and " + "GZSEQ="
						+ jrnSequence + " and " + "GZIMG='" + image + "'";
	}

	/**
	 * Return the key field for this record
	 */
	@Override
	public String rtvKey()
	{
		return workstationID + jrnDay + jrnTime + jrnSequence + image;
	}

	/**
	 * Set up the field values after retrieving the record
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param resultset
	 *            - the resultset
	 */
	@Override
	protected void rtvData(EquationStandardSession session, ResultSet resultset)
	{
		try
		{
			// Journal data structure not set
			if (jrnDs == null)
			{
				this.jrnDs = new EquationDataStructure(getEqFileName(), resultset, session.getCcsid());
			}

			EquationDataStructureData journalData = new EquationDataStructureData(jrnDs, resultset);
			setJrnData(journalData);
		}
		catch (EQException e)
		{
			throw new RuntimeException("JournalRecord: rtvData() Failed: " + Toolbox.getExceptionMessage(e), e);
		}
	}
	/**
	 * Add the record to the file
	 * 
	 * @param session
	 *            - an EquationStandardSession
	 * 
	 * @return true - if the record has been added
	 * 
	 */
	@Override
	public boolean update(EquationStandardSession session) throws EQException
	{
		String sqlStatement = createAddRecordSQL();
		this.functionData = null;
		executePreparedStatement(session, sqlStatement);
		return true;
	}

	/**
	 * Add the record to the file using the details in function data
	 * 
	 * @param session
	 *            - an EquationStandardSession
	 * @param functionData
	 *            - the function data
	 * 
	 * @return true - if the record has been added
	 * 
	 */
	public boolean update(EquationStandardSession session, FunctionData functionData) throws EQException
	{
		String sqlStatement = createAddRecordSQL(functionData);
		this.functionData = functionData;
		executePreparedStatement(session, sqlStatement);
		return true;

	}

	/**
	 * Return the field value of a non-repeating field
	 * 
	 * @param functionData
	 *            - the function data
	 * @param fieldName
	 *            - the field name
	 * 
	 * @return the field value of a non-repeating field
	 */
	private String rtvValue(FunctionData functionData, String fieldName)
	{
		FieldData fieldData = functionData.rtvFieldData(fieldName);
		if (!(fieldData instanceof RepeatingFieldData))
		{
			return fieldData.getDbValue();
		}
		else
		{
			return "";
		}
	}

	/**
	 * Return the Suppress Maker Checker flag
	 * 
	 * @return the Suppress Maker Checker flag
	 */
	public String getSuppMakerChecker()
	{
		return suppMakerChecker;
	}

	/**
	 * Set the Suppress Maker Checker flag
	 * 
	 * @param Suppress
	 *            Maker Checker - the Suppress Maker Checker flag
	 */
	public void setSuppMakerChecker(String suppMakerChecker)
	{
		this.suppMakerChecker = suppMakerChecker;
	}

	/**
	 * Return the Maker Checker status flag
	 * 
	 * @return the Maker Checker status flag
	 */
	public String getStatMakerChecker()
	{
		return statMakerChecker;
	}

	/**
	 * Set the Maker Checker status flag
	 * 
	 * @param Maker
	 *            -Checker status - the Maker Checker status flag
	 */
	public void setStatMakerChecker(String statMakerChecker)
	{
		this.statMakerChecker = statMakerChecker;
	}
}