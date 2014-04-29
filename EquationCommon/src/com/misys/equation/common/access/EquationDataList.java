package com.misys.equation.common.access;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.as400.access.AS400Bin2;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.DsccnToolbox;
import com.misys.equation.common.utilities.EqDataListHelper;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.EquationPVMetaDataHelper;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;

@SuppressWarnings("serial")
public class EquationDataList implements IDataList, Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationDataList.java 15470 2013-03-08 15:56:33Z whittap1 $";

	private String dataList;
	private String dataListForDisplay;
	private static final EquationLogger LOG = new EquationLogger(EquationDataList.class);

	// these are internal variables
	private StringBuilder returnData;
	private StringBuilder returnDataRTL;
	private int totalNumberofRecordsRequired; // this determines the total number of records required
	private int totalNumberofRecordsRead; // this determines the current number of records
	private String lastRecordRead; // this determines the last record read
	private HashMap<String, String> javaQuery = null;

	/**
	 * Construct an empty data list
	 */
	public EquationDataList()
	{
	}

	/**
	 * Construct a data list retrieving the file and key from the PV Meta-Data
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * @param service
	 *            - p/v program name
	 * @param decode
	 *            - decode character
	 * @param dsccn
	 *            - this is the DSCCN
	 * @param start
	 *            - if the direction is 1, then this is the starting key <br>
	 *            if the direction is -1, then this is a last key <br>
	 * @param direction
	 *            - (1) page down (-1) page up
	 * @param maxResults
	 *            - maximum number of data to return
	 * 
	 * @throws EQException
	 */
	public void initialise(EquationUser eqUser, String service, String decode, String dsccn, String start, int direction,
					int maxResults) throws EQException
	{
		initialise(eqUser, service, decode, "", dsccn, start, direction, maxResults);
	}

	/**
	 * Construct a data list retrieving the file and key from the PV Meta-Data
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * @param service
	 *            - p/v program name
	 * @param decode
	 *            - decode character
	 * @param security
	 *            - security mode
	 * @param dsccn
	 *            - this is the DSCCN
	 * @param start
	 *            - if the direction is 1, then this is the starting key <br>
	 *            if the direction is -1, then this is a last key <br>
	 * @param direction
	 *            - (1) page down (-1) page up
	 * @param maxResults
	 *            - maximum number of data to return
	 * 
	 * @throws EQException
	 */
	public void initialise(EquationUser eqUser, String service, String decode, String security, String dsccn, String start,
					int direction, int maxResults) throws EQException
	{
		// Retrieve the equation pv meta data
		EquationPVMetaData pvMetaData = eqUser.getEquationUnit().getPVMetaData(service);

		// Load DSCCN data into hashtable of name and value pairs
		EquationPVData equationPVData = new EquationPVData(pvMetaData, eqUser.getSession().getCcsid());
		equationPVData.setDataDsccn(dsccn);

		initialise(eqUser, service, decode, equationPVData, start, direction, maxResults);
	}

	/**
	 * Construct a data list retrieving the file and key from the PV Meta-Data
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * @param service
	 *            - p/v program name
	 * @param decode
	 *            - decode character
	 * @param equationPVData
	 *            - the Equation PV Data
	 * @param start
	 *            - if the direction is 1, then this is the starting key <br>
	 *            if the direction is -1, then this is a last key <br>
	 * @param direction
	 *            - (1) page down (-1) page up
	 * @param maxResults
	 *            - maximum number of data to return
	 * 
	 */
	public void initialise(EquationUser eqUser, String service, String decode, EquationPVData equationPVData, String start,
					int direction, int maxResults)
	{
		initialise(eqUser, service, decode, "", equationPVData, start, direction, maxResults);
	}

	/**
	 * Construct a data list retrieving the file and key from the PV Meta-Data
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * @param service
	 *            - p/v program name
	 * @param decode
	 *            - decode character
	 * @param security
	 *            - security mode
	 * @param equationPVData
	 *            - the Equation PV Data
	 * @param start
	 *            - if the direction is 1, then this is the starting key <br>
	 *            if the direction is -1, then this is a last key <br>
	 * @param direction
	 *            - (1) page down (-1) page up
	 * @param maxResults
	 *            - maximum number of data to return
	 * 
	 */
	public void initialise(EquationUser eqUser, String service, String decode, String security, EquationPVData equationPVData,
					String start, int direction, int maxResults)
	{
		totalNumberofRecordsRequired = maxResults;
		totalNumberofRecordsRead = 0;
		returnData = new StringBuilder();
		returnDataRTL = new StringBuilder();
		lastRecordRead = start;

		while (totalNumberofRecordsRead < totalNumberofRecordsRequired)
		{
			// Translate the start datakey into a valid data key
			String startQuery = translateStartQuery(equationPVData.getPvMetaData(), decode, lastRecordRead);

			// Generate the SQL
			String sqlSelect = generateSQL(eqUser, decode, security, equationPVData, startQuery, direction, maxResults);

			// Perform the query
			boolean more = performQuery(eqUser, sqlSelect.toString(), maxResults, direction, equationPVData);

			// Total number of records read less than the required, then it means no more data
			if (!more)
			{
				break;
			}
		}

		// setup return data
		setupReturnData(eqUser);
	}

	/**
	 * @return Returns the dataList.
	 */
	public String getDataList()
	{
		return dataList;
	}

	/**
	 * Return the data list for Display
	 * 
	 * @return Returns the dataList.
	 */
	public String getDataListForDisplay()
	{
		return dataListForDisplay;
	}

	/**
	 * Set the data list
	 * 
	 * @param dataList
	 *            The dataList to set.
	 */
	public void setDataList(String dataList)
	{
		this.dataList = dataList;
	}

	/**
	 * Perform the query
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * @param sqlSelect
	 *            - SQL statement
	 * @param maxResults
	 *            - number of details to retrieve
	 * @param direction
	 *            - direction of search
	 * 
	 * @return true if it is able to read the required number of records
	 */
	private boolean performQuery(EquationUser eqUser, String sqlSelect, int maxResults, int direction, EquationPVData equationPVData)
	{
		ResultSet equationResultSet = null;
		PreparedStatement equationStatement = null;

		try
		{
			Connection equationConnection = eqUser.getSession().getConnection();
			int ccsid = eqUser.getSession().getCcsid();
			boolean rtlCcsid = EquationCommonContext.isRtlCcsid(ccsid);

			// Build the result set
			equationStatement = equationConnection.prepareStatement(sqlSelect, ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			equationStatement.setMaxRows(maxResults + 1);
			equationStatement.setFetchSize(maxResults + 1);

			// record time
			long startTime = System.nanoTime();
			equationResultSet = equationStatement.executeQuery();
			if (LOG.isDebugEnabled())
			{
				// record time
				long endTime = System.nanoTime();
				// log the time taken to make the SQL call
				LOG.debug("PV Perform Query: " + sqlSelect + " (Duration: " + ((endTime - startTime) / 1000000) + "ms)");
			}

			// Retrieve result
			int count = 0;
			for (int j = 1; equationResultSet.next() && j <= maxResults; j++)
			{
				AS400Bin2 textLength = new AS400Bin2();
				int length = (Short) textLength.toObject(equationResultSet.getBytes(2));
				String result = DsccnToolbox.setupDSCCNFromBytes(equationResultSet.getBytes(2), equationPVData.getPvMetaData(),
								length, EquationPVMetaDataHelper.LEN_DSEPMS + 2, ccsid, false);
				String errorText = DsccnToolbox.setupDSCCNFromBytes(equationResultSet.getBytes(2), equationPVData.getPvMetaData(),
								37, 2, ccsid, false);

				// no error, try performing Java query
				if (errorText.equals("") && javaQuery != null && !javaQuery.isEmpty())
				{
					errorText = processJavaQuery(equationPVData.getPvMetaData(), result, ccsid, javaQuery);
				}

				// no error, then include in the data to be returned
				if (errorText.equals(""))
				{
					// if the ccsid supports RTL, then setup another buffer for outputting (e.g. HTML)
					if (rtlCcsid)
					{
						String resultRTL = DsccnToolbox.setupDSCCNFromBytes(equationResultSet.getBytes(2), equationPVData
										.getPvMetaData(), length, EquationPVMetaDataHelper.LEN_DSEPMS + 2, ccsid, true);
						returnDataRTL.append(resultRTL + EqDataType.GLOBAL_DELIMETER);
					}

					// result = equationResultSet.getString(1).substring(EquationPVMetaData.LEN_DSEPMS); // data only
					returnData.append(result + EqDataType.GLOBAL_DELIMETER);
					totalNumberofRecordsRead++;

					// read the required number of records?
					if (totalNumberofRecordsRead >= totalNumberofRecordsRequired)
					{
						break;
					}
				}

				// accumulate total records read
				lastRecordRead = result;
				count++;
			}

			// Return true if it only read less than the required
			return count >= maxResults;
		}
		catch (SQLException sqle)
		{
			LOG.error("SQL Statement: " + sqlSelect);
			LOG.error("Replace this", sqle);
			throw new RuntimeException("EqDataList: performQuery: " + Toolbox.getExceptionMessage(sqle), sqle);
		}
		finally
		{
			SQLToolbox.close(equationResultSet);
			SQLToolbox.close(equationStatement);
		}
	}

	/**
	 * Generate the SQL statement
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * @param service
	 *            - p/v program name
	 * @param decode
	 *            - decode character
	 * @param security
	 *            - security mode
	 * @param query
	 *            - this is the query selection based on the column heading of the PV meta-data (comma-separated)
	 * @param start
	 *            - if the direction is 1, then this is the starting key <br>
	 *            if the direction is -1, then this is a last key <br>
	 * @param direction
	 *            - (1) page down (-1) page up
	 * @param maxResults
	 *            - maximum number of data to return
	 */
	private String generateSQL(EquationUser eqUser, String decode, String security, EquationPVData equationPVData, String start,
					int direction, int maxResults)
	{
		// Set up primary table and key from meta-data based on decode
		EquationPVMetaData pvMetaData = equationPVData.getPvMetaData();
		EquationPVDecodeMetaData pvDecodeInfo = pvMetaData.getDecodeMetaData(decode);
		String primaryTable = pvDecodeInfo.getpFile();
		String serviceKey = getDSCCNInput(eqUser, decode, equationPVData);
		String serviceOrderBy = getOrderBy(decode, equationPVData, direction);

		// SQL statement buffer
		StringBuffer sqlSelect = new StringBuffer();

		// For optimisation purposes UCASE is not used if possible. Fields longer than 15 chars may be
		// mixed case (e.g. account short name) and so require UCASE in the SQL.
		final int UCASE_REQUIRED = 15;

		// Java query - only process filter fields once
		boolean accumulateJavaQuery = false;
		if (javaQuery == null)
		{
			accumulateJavaQuery = true;
			javaQuery = new HashMap<String, String>();
		}

		// Set the starting position...
		if ((start == null) || (start.equals("null")))
		{
			start = "";
		}

		// Set the operator depending on the direction of the search
		String operator = "";
		if (direction == -1)
		{
			operator = "<";
		}
		else
		{
			operator = ">";
		}

		String pvFunctionCall = "UTW06RFNC('" + decode + "','" + pvMetaData.getId() + "', " + serviceKey + ",'N','N'," + "'"
						+ security + "'," + "''" + ")";

		// Build the SQL statement
		sqlSelect.append("SELECT " + serviceKey + " AS DTAKEY, " + pvFunctionCall + " AS DTA FROM " + primaryTable + " ");

		// Append the search criteria
		StringBuffer fieldWithoutDb = new StringBuffer();

		boolean firstWhere = true;
		boolean firstRS = true;
		String dbField;
		String fieldType;
		int fieldLength;

		for (EquationPVFieldMetaData fmd : pvMetaData.getFieldMetaData())
		{
			String dsccnName = fmd.getId();

			// translate the query data. If the query is simply an *, then do not include this in the selection anymore
			String queryData = SQLToolbox.replaceWildCard(SQLToolbox.replaceWildCardSng(SQLToolbox
							.replaceSingleQuote(equationPVData.getFieldValue(dsccnName))));
			if (!Toolbox.trimr(queryData).equals("%"))
			{
				dbField = fmd.getDb();
				fieldType = fmd.getType();
				fieldLength = fmd.getLength();

				// if the query data is blank (e.g. filter on blank value) then pad spaces until required length
				if (queryData.trim().equals(""))
				{
					if (fmd.isAlpha())
					{
						queryData = Toolbox.pad(queryData, fieldLength);
					}
					else
					{
						queryData = Toolbox.padAtFront(queryData, "0", fieldLength);
					}
				}

				// there is no database field defined for the field, then perform the WHERE clause on the resulting DSCCN
				if (dbField.equals(""))
				{
					if (!fmd.getComposite() && accumulateJavaQuery)
					{
						// Convert prompt field to regular expression
						String pattern = convertPVFilterToRegEx(equationPVData.getFieldValue(dsccnName));
						// For longer fields that can be mixed case, add the "ignore case" flag to the regular expression
						if (fieldLength >= UCASE_REQUIRED)
							pattern = "(?i)" + pattern;
						javaQuery.put(dsccnName, pattern);
					}
				}

				// there is a database field defined for the field, then perform WHERE clause on the file
				else
				{
					if (firstWhere)
					{
						sqlSelect.append(" WHERE ");
						firstWhere = false;
					}
					else
					{
						sqlSelect.append(" AND ");
					}

					// field name
					if (fmd.isAlpha())
					{
						if (fieldLength < UCASE_REQUIRED)
						{
							sqlSelect.append(Toolbox.sqlCharWithRTRIM(dbField));
						}
						else
						{
							sqlSelect.append(Toolbox.sqlCharToUppercaseWithRTRIM(dbField));
						}
					}
					else if (fieldType.equals(EquationPVMetaDataHelper.TYPE_DDMMYY))
					{
						sqlSelect.append("SUBSTR(" + dbField + " + 10000000,7,2)" + " || " + "SUBSTR(" + dbField
										+ " + 10000000,5,2)" + " || " + "SUBSTR(" + dbField + " + 10000000,3,2)" + " ");
					}
					else
					{
						sqlSelect.append(Toolbox.sqlNumberToCharFormat(dbField, fieldLength));
					}

					sqlSelect.append(" LIKE ");

					// field value
					if (fmd.isAlpha())
					{
						// When comparing with LIKE we can change the compare value in Java and do not need to use SQL.
						sqlSelect.append("'" + Toolbox.trimr(queryData).toUpperCase() + "'");
					}
					else if (fieldType.equals(EquationPVMetaDataHelper.TYPE_DDMMYY))
					{
						sqlSelect.append(" '" + queryData + "'");
					}
					else if (fmd.isZoned())
					{
						if (queryData.contains("%"))
						{
							sqlSelect.append("'" + queryData + "'");
						}
						else
						{
							sqlSelect.append("'" + Toolbox.padAtFront(queryData, "0", fieldLength) + "'");
						}
					}
					else
					{
						sqlSelect.append("'" + queryData + "'");
					}
					sqlSelect.append(" ");
				}
			}
		}
		if (firstWhere)
		{
			sqlSelect.append(" WHERE ");
			firstWhere = false;
		}
		else
		{
			sqlSelect.append(" AND ");
		}

		if (isKeyDBFields(decode, equationPVData))
		{
			sqlSelect.append(getWhereClauseForDbKey(decode, equationPVData, start, operator) + " ");
		}
		else
		{
			sqlSelect.append(serviceKey + operator + "'" + start + "' ");
		}

		if (fieldWithoutDb.length() > 0)
		{
			sqlSelect.append(" AND ");
			sqlSelect.append(fieldWithoutDb);
		}

		// Append the ordering
		sqlSelect.append(" ORDER BY " + serviceOrderBy);

		// fetch only this rows
		sqlSelect.append(" FETCH FIRST " + (maxResults + 1) + " ROWS ONLY");

		// read uncommitted records
		sqlSelect.append(" WITH UR");

		// debug the SQL
		// SQL Statement is logged in the performQuery method with the time taken
		// LOG.debug(sqlSelect.toString());

		// Return the statement
		return sqlSelect.toString();
	}

	/**
	 * Return the key fields with substitution of variables (e.g. £USER, £PDATEDB, number formating for SQL)
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * @param decode
	 *            - decode
	 * @param pvData
	 *            - the Equation PV data
	 * 
	 * @param session
	 * 
	 * @return key fields
	 */
	private String getDSCCNInput(EquationUser eqUser, String decode, EquationPVData pvData)
	{
		EquationPVMetaData pvMetaData = pvData.getPvMetaData();
		List<String> dsccnInputFields = pvMetaData.getDecodeMetaData(decode).getDSCCNInputFields();

		StringBuffer serviceKeyBuff = new StringBuffer();
		String delimiterConcatenate = " || ";

		// assume we can subsequently optimise the where clause, but can only do this if the key is alpha and on the db...
		// loop through all the key fields
		EquationPVFieldMetaData fmd;
		String fieldType;
		int fieldLength;
		for (int i = 0; i < dsccnInputFields.size(); i++)
		{
			String keyField = dsccnInputFields.get(i).trim();
			// EquationPVFieldMetaData fmd;

			// determine if the key field is a database field
			String dsccnFieldName = pvMetaData.getDSCCNName(keyField);
			if (dsccnFieldName != null)
			{
				// get the field meta-data
				fmd = pvMetaData.rtvFieldMetaData(dsccnFieldName);
				fieldType = fmd.getType();
				fieldLength = fmd.getLength();
				if (fmd.isAlpha())
				{
					serviceKeyBuff.append(keyField);
				}
				else if (fieldType.equals(EquationPVMetaDataHelper.TYPE_DDMMYY))
				{
					serviceKeyBuff.append("SUBSTR(" + keyField + " + 10000000,7,2)" + " || " + "SUBSTR(" + keyField
									+ " + 10000000,5,2)" + " || " + "SUBSTR(" + keyField + " + 10000000,3,2) ");
				}
				else if (fmd.isZoned())
				{
					serviceKeyBuff.append(Toolbox.sqlNumberToCharFormat(keyField, fieldLength));
				}

			}

			// field does not have an equivalent database field
			else
			{
				// determine if this key field is a PV field
				fmd = pvMetaData.rtvFieldMetaData(keyField);
				if (fmd != null)
				{
					fieldType = fmd.getType();
					fieldLength = fmd.getLength();
					String queryData = pvData.getFieldValue(keyField);

					if (fmd.isAlpha())
					{
						queryData = Toolbox.pad(queryData, " ", fieldLength);
					}
					else
					{
						queryData = Toolbox.padAtFront(queryData, "0", fieldLength);
					}
					serviceKeyBuff.append("'" + queryData + "'");
				}

				// field is a pre-defined constant
				else
				{
					serviceKeyBuff.append(EqDataListHelper.getFieldValue(eqUser, keyField));
				}
			}

			// add the delimeter to concatenate all the key fields
			if (i < dsccnInputFields.size() - 1)
			{
				serviceKeyBuff.append(delimiterConcatenate);
			}
		}

		return serviceKeyBuff.toString();
	}
	/**
	 * Return the key fields with substitution of variables (e.g. £USER, £PDATEDB, number formating for SQL)
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * @param decode
	 *            - decode
	 * @param pvData
	 *            - the Equation PV data
	 * 
	 * @param session
	 * 
	 * @return key fields
	 */
	private String getOrderBy(String decode, EquationPVData pvData, int direction)
	{
		StringBuffer serviceKeyBuff = new StringBuffer();
		String delimiterOrderBy = ", ";
		String ascend = " ASC";
		String decend = " DESC";

		EquationPVMetaData pvMetaData = pvData.getPvMetaData();
		List<String> keyfields = pvMetaData.getDecodeMetaData(decode).getKeyFields();
		String keyField;
		String dsccnFieldName;
		// loop through all the key fields
		for (int i = 0; i < keyfields.size(); i++)
		{
			keyField = keyfields.get(i).trim();
			// determine if the key field is a database field
			dsccnFieldName = pvMetaData.getDSCCNName(keyField);
			if (dsccnFieldName != null)
			{
				// add the delimeter to concatenate all the key fields
				if (serviceKeyBuff.length() != 0)
				{
					serviceKeyBuff.append(delimiterOrderBy);
				}

				serviceKeyBuff.append(keyField);

				if (direction == -1)
				{
					serviceKeyBuff.append(decend);
				}
				else
				{
					serviceKeyBuff.append(ascend);
				}
			}
		}
		return serviceKeyBuff.toString();
	}
	/**
	 * Return the translated data key (e.g. set blank spaces if needed)
	 * 
	 * @param pvMetaData
	 *            - the Equation PV meta data
	 * @param decode
	 *            - the decode
	 * @param start
	 *            - the data key
	 * 
	 * @return the translated data key
	 */
	private String translateStartQuery(EquationPVMetaData pvMetaData, String decode, String startString)
	{
		EquationPVDecodeMetaData decodeMetaData = pvMetaData.getDecodeMetaData(decode);
		List<String> dsccnInputFields = decodeMetaData.getDSCCNInputFields();
		EquationPVFieldMetaData fmd;

		// Some fields may be input but not be key fields on the PF/LF file. These inputs will be declared in IVAL structure.
		StringBuffer startQuery = new StringBuffer(Toolbox.pad(startString, " ", pvMetaData.getDSCCNLength()));

		// loop through all the key fields
		int start = 0;
		for (int i = 0; i < dsccnInputFields.size(); i++)
		{
			String fieldName = dsccnInputFields.get(i);

			// constant spaces
			if (fieldName.startsWith("'"))
			{
				int end = start + fieldName.length() - 2;
				startQuery.replace(start, end, dsccnInputFields.get(i).substring(1, fieldName.length() - 1));
				start = end;
			}

			// field name
			else
			{
				// determine length, to adjust the start index of the next field
				String dsccnFieldName = pvMetaData.getDSCCNName(fieldName);
				fmd = pvMetaData.rtvFieldMetaData(dsccnFieldName);
				if (fmd != null)
				{
					start = start + fmd.getLength();
				}
				else
				{
					start = start + EqDataListHelper.getLength(fieldName);
				}
			}
		}

		// return translated data key
		return startQuery.toString().toUpperCase();
	}

	/**
	 * Return whether the key is suitable for optimisation in the where clause
	 * 
	 * @param decode
	 * @param pvData
	 * @return whether the key is suitable for optimisation in the where clause
	 */
	private boolean isKeyDBFields(String decode, EquationPVData pvData)
	{
		EquationPVMetaData pvMetaData = pvData.getPvMetaData();
		List<String> keyfields = pvMetaData.getDecodeMetaData(decode).getKeyFields();
		// loop through all the key fields
		if (keyfields.size() == 0)
		{
			return false;
		}
		return true;
	}

	/**
	 * Return where clause when key is suitable for optimisation
	 * 
	 * @param decode
	 * @param pvData
	 * @return the where clause when key is suitable for optimisation
	 */
	private String getWhereClauseForDbKey(String decode, EquationPVData pvData, String start, String operator)
	{

		EquationPVMetaData pvMetaData = pvData.getPvMetaData();
		EquationPVFieldMetaData fmd;
		String fieldType;
		int fieldLength;
		String keyField;

		List<EquationPVFieldMetaData> keyFieldsDBValue = pvMetaData.getDecodeMetaData(decode).getDbKeyFields();

		StringBuffer whereClause = new StringBuffer(" (");
		StringBuffer whereClauseCurrent = null;
		StringBuffer whereClauseLast = null;

		for (int i = 0; i < keyFieldsDBValue.size(); i++)
		{

			fmd = keyFieldsDBValue.get(i);
			fieldType = fmd.getType();
			fieldLength = fmd.getLength();
			keyField = fmd.getDb();

			if (fieldType.equals(EquationPVMetaDataHelper.TYPE_DDMMYY))
			{
				keyField = "SUBSTR(" + keyField + " + 10000000,7,2)" + " || " + "SUBSTR(" + keyField + " + 10000000,5,2)" + " || "
								+ "SUBSTR(" + keyField + " + 10000000,3,2) ";
			}
			else if (fmd.isZoned())
			{
				keyField = Toolbox.sqlNumberToCharFormat(keyField, fieldLength);
			}

			// Ensure start string has single quotes encoded to prevent problems with SQL Where Clause
			String field = SQLToolbox.replaceWildCard(SQLToolbox.replaceWildCardSng(SQLToolbox.replaceSingleQuote(start.substring(
							fmd.getIndex(), fmd.getIndex() + fmd.getLength()))));
			if (i == 0)
			{
				whereClauseCurrent = new StringBuffer(keyField + operator + "'" + field + "' ");

			}
			else
			{
				whereClauseCurrent = new StringBuffer(" (" + whereClauseLast + " AND " + keyField + operator + "'" + field + "'"
								+ ") ");

			}
			if (i == 0)
			{
				whereClauseLast = new StringBuffer(keyField + "=" + "'" + field + "'");
			}
			else
			{
				whereClauseLast = whereClauseLast.append(" AND " + keyField + "=" + "'" + field + "'");
			}

			if (i < keyFieldsDBValue.size() - 1 && keyFieldsDBValue.size() > 1)
			{
				whereClause = whereClause.append(whereClauseCurrent + "OR");
			}
			else
			{
				whereClause = whereClause.append(whereClauseCurrent);
			}

		}
		whereClause = whereClause.append(")");

		// Constants Clause
		List<EquationPVFieldMetaData> keyFieldsConstants = pvMetaData.getDecodeMetaData(decode).getConstantsKeyFields();
		StringBuffer constantsClause = new StringBuffer();

		for (int i = 0; i < keyFieldsConstants.size(); i++)
		{
			fmd = keyFieldsConstants.get(i);
			fieldType = fmd.getType();
			fieldLength = fmd.getLength();
			keyField = fmd.getDb();

			// Ensure start string has single quotes encoded to prevent problems with SQL Where Clause
			String field = SQLToolbox.replaceWildCard(SQLToolbox.replaceWildCardSng(SQLToolbox.replaceSingleQuote(start.substring(
							fmd.getIndex(), fmd.getIndex() + fmd.getLength()))));
			if (i == 0)
			{
				constantsClause = constantsClause.append(keyField + "=" + "'" + field + "'");
			}
			else
			{
				constantsClause = constantsClause.append(" AND " + keyField + "=" + "'" + field + "'");
			}
		}
		if (constantsClause.length() > 0)
		{

			whereClause = constantsClause.append(" AND " + whereClause);

		}
		return whereClause.toString();
	}

	/**
	 * Setup return data
	 * 
	 * @param equationuser
	 *            - the EquationUser
	 */
	private void setupReturnData(EquationUser equationUser)
	{
		int ccsid = equationUser.getSession().getCcsid();
		boolean rtlCcsid = EquationCommonContext.isRtlCcsid(ccsid);

		// Setup the data list
		dataList = returnData.toString();

		// Remove the last delimeter
		if (!dataList.equals(""))
		{
			dataList = dataList.substring(0, dataList.length() - 3);
		}

		// Setup second data list
		dataListForDisplay = null;
		if (rtlCcsid)
		{
			dataListForDisplay = returnDataRTL.toString();
			if (!dataListForDisplay.equals(""))
			{
				dataListForDisplay = dataListForDisplay.substring(0, dataListForDisplay.length() - 3);
			}
		}
	}

	/**
	 * Process a record and compare if it matches the search criteria
	 * 
	 * @param equationPVMetaData
	 *            - the PV meta data
	 * @param dsccn
	 *            - the DSCCN
	 * @param ccsid
	 *            - the CCSID
	 * @param query
	 *            - the query selection
	 * 
	 * @return error message if it does not match
	 */
	private String processJavaQuery(EquationPVMetaData equationPVMetaData, String dsccn, int ccsid, Map<String, String> query)
	{
		String error = "";
		EquationPVData equationPVData = new EquationPVData(equationPVMetaData, ccsid);
		equationPVData.setDataDsccn(dsccn);

		for (String pvField : query.keySet())
		{
			String value = equationPVData.getFieldValue(pvField);
			String filter = query.get(pvField);

			if (filter != null)
			{
				boolean match = value.matches(filter);
				if (!match)
				{
					error = "KSM0156";
					break;
				}
			}
		}

		return error;
	}

	/**
	 * Convert filter fields into a valid regular expressions
	 * 
	 * @param query
	 *            - the map of filter fields
	 */
	private void convertPVFilterToRegEx(Map<String, String> query)
	{
		for (String pvField : query.keySet())
		{
			String filter = query.get(pvField);
			String filterx = convertPVFilterToRegEx(filter);
			query.put(pvField, filterx);
		}
	}

	/**
	 * Convert filter field into a valid regular expression
	 * 
	 * @param filter
	 *            - the filter string
	 * 
	 * @return the equivalent regular expression
	 */
	private String convertPVFilterToRegEx(String filter)
	{
		StringBuilder regEx = new StringBuilder(filter);

		// append \ to these characters: . (dot), \ (back-slash), { (curly-bracket), [ (square-bracket)
		convertPVCharacterToRegExCharacter(regEx, "\\", "\\\\");
		convertPVCharacterToRegExCharacter(regEx, ".", "\\.");
		convertPVCharacterToRegExCharacter(regEx, "{", "\\{");
		convertPVCharacterToRegExCharacter(regEx, "[", "\\[");
		convertPVCharacterToRegExCharacter(regEx, ")", "\\)");
		convertPVCharacterToRegExCharacter(regEx, "(", "\\(");

		// convert the ? to . (match any one character)
		convertPVCharacterToRegExCharacter(regEx, "?", ".");

		// convert the * to .* (match one or more any characters)
		convertPVCharacterToRegExCharacter(regEx, "*", ".*");

		// return the string regex
		return regEx.toString();
	}

	/**
	 * Replace all matching string
	 * 
	 * @param regEx
	 *            - the String
	 * @param searchString
	 *            - the search string to be replace
	 * @param replacementString
	 *            - the replacement string
	 */
	private void convertPVCharacterToRegExCharacter(StringBuilder regEx, String searchString, String replacementString)
	{
		int start = regEx.indexOf(searchString);
		int searchStringLength = searchString.length();
		int replacementStringLength = replacementString.length();
		while (start >= 0)
		{
			regEx.replace(start, start + searchStringLength, replacementString);
			start = regEx.indexOf(searchString, start + replacementStringLength);
		}
	}

}
