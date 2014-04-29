package com.misys.equation.common.access;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.DsccnToolbox;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationPVMetaDataHelper;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;

@SuppressWarnings("serial")
public class EquationDataSQLPagingList implements IDataList, Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationDataSQLPagingList.java 17583 2013-11-11 23:27:15Z williae1 $";

	EquationPVMetaData pvMetaData;
	EquationPVData equationPVData;
	private EquationStandardSQLPagingList sqlPagingList;

	private String dataList;
	private String dataListForDisplay;

	/**
	 * Construct an empty data list
	 */
	public EquationDataSQLPagingList()
	{
	}

	/**
	 * Construct a data list retrieving the file and key from the PV Meta-Data
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * @param pvId
	 *            - p/v program name
	 * @param decode
	 *            - decode character
	 * @param security
	 *            - not used
	 * @param dsccnFilter
	 *            - filter fields in dsccn format
	 * @param dsccnStart
	 *            - if the direction is 1, then this is the starting key in dsccn format<br>
	 *            if the direction is -1, then this is a last key in dsccn format<br>
	 * @param direction
	 *            - (1) page down (-1) page up
	 * @param maxResults
	 *            - maximum number of data to return
	 * @throws Exception
	 * 
	 */
	public void initialise(EquationUser eqUser, String pvId, String decode, String security, String dsccnFilter, String dsccnStart,
					int direction, int maxResults) throws EQException
	{
		// Retrieve the equation pv meta data
		pvMetaData = eqUser.getEquationUnit().getPVMetaData(pvId);

		// Load DSCCN data into hashtable of name and value pairs
		equationPVData = new EquationPVData(pvMetaData, eqUser.getSession().getCcsid());
		equationPVData.setDataDsccn(dsccnFilter);

		// Setup EquationStandardSQLPagingList
		String sqlSelectStart = getSelect(decode);
		String sqlSelectFilter = getSelectFilter(decode);

		sqlPagingList = new EquationStandardSQLPagingList(null, sqlSelectStart, sqlSelectFilter, maxResults, null);
		setupKeyFields(decode);

		int requestedPage = 0;
		// Execute the queries
		if (direction == 1)
		{
			// first time
			if (dsccnStart.equals(""))
			{
				sqlPagingList.setCurrentPage(0);
				sqlPagingList.setInitialisedKeyFields(true);
				requestedPage = 1;
			}
			else
			{
				// Convert DSCCN start to a last row used by SQL Paging List.
				equationPVData.setDataDsccn(dsccnStart);
				HashMap<String, String> lastRow = setupStartFields();
				// Set current page and requested page to invoke forward processing
				sqlPagingList.setInitialisedKeyFields(true);
				sqlPagingList.setLastRow(lastRow);
				sqlPagingList.setCurrentPage(1);
				sqlPagingList.setMoreRows(true);
				requestedPage = 2;
			}
		}

		if (direction == -1)
		{
			// Convert DSCCN start to a last row used by SQL Paging List
			equationPVData.setDataDsccn(dsccnStart);
			HashMap<String, String> firstRow = setupStartFields();
			// Set current page and requested page to invoke backward processing processing
			sqlPagingList.setInitialisedKeyFields(true);
			sqlPagingList.setFirstRow(firstRow);
			sqlPagingList.setCurrentPage(2);
			requestedPage = 1;
		}
		try
		{
			ArrayList<HashMap<String, String>> results = sqlPagingList.executeQuery(eqUser.getSession(), requestedPage);
			// setup return data
			setupReturnData(eqUser, results, direction);
		}
		catch (Exception e)
		{
			throw new EQException(e);
		}
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

	private String getSelect(String decode)
	{
		// SQL statement buffer
		StringBuffer sqlSelect = new StringBuffer();

		// Build the SQL statement
		sqlSelect.append("SELECT ");

		boolean firstField = true;
		for (EquationPVFieldMetaData fmd : pvMetaData.getFieldMetaData())
		{
			if (!fmd.getDb().equals(""))
			{
				if (!firstField)
				{
					sqlSelect.append(",");
				}
				String fieldName = fmd.getId();
				sqlSelect.append(fieldName);
				firstField = false;
			}
		}

		sqlSelect.append(" FROM " + pvMetaData.getDecodeMetaData(decode).getSqlFrom());

		return sqlSelect.toString();
	}
	private String getSelectFilter(String decode)
	{

		// SQL statement buffer
		StringBuffer sqlSelect = new StringBuffer();

		// For optimisation purposes UCASE is not used if possible. Fields longer than 15 chars may be
		// mixed case (e.g. account short name) and so require UCASE in the SQL.
		final int UCASE_REQUIRED = 15;

		boolean firstWhere = true;

		if (!pvMetaData.getDecodeMetaData(decode).getSqlWhere().equals(""))
		{
			sqlSelect.append(pvMetaData.getDecodeMetaData(decode).getSqlWhere());
			firstWhere = false;
		}

		String dbField;
		String fieldType;
		int fieldLength;

		for (EquationPVFieldMetaData fmd : pvMetaData.getFieldMetaData())
		{
			if (!fmd.getDb().equals(""))
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

					// there is a database field defined for the field, then perform WHERE clause on the file
					else
					{
						if (firstWhere)
						{
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
							sqlSelect.append(Toolbox.sqlNumberToCharFormat(dbField, fieldLength, fmd.getDecimal()));
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
		}
		return sqlSelect.toString();
	}

	private void setupKeyFields(String decode)
	{
		List<String> keyfields = pvMetaData.getDecodeMetaData(decode).getKeyFields();
		for (EquationPVFieldMetaData fmd : pvMetaData.getFieldMetaData())
		{
			String fieldName = fmd.getId();
			if (keyfields.contains(fieldName))
			{
				String fieldValue = "";

				// Set key fields and start position in the file
				sqlPagingList.addPrimaryKeyFieldValues(fieldName, fieldValue, fmd.getLength(), true);
			}
		}

	}

	private HashMap<String, String> setupStartFields()
	{

		HashMap<String, String> startRow = new HashMap<String, String>();
		for (EquationPVFieldMetaData fmd : pvMetaData.getFieldMetaData())
		{
			String fieldName = fmd.getId();
			String fieldValue = SQLToolbox.replaceWildCard(SQLToolbox.replaceWildCardSng(SQLToolbox
							.replaceSingleQuote(equationPVData.getFieldValue(fieldName))));
			startRow.put(fieldName, fieldValue);

		}
		return startRow;
	}
	/**
	 * Setup return data
	 * 
	 * @param equationUser
	 * @param results
	 * @param direction
	 */
	private void setupReturnData(EquationUser equationUser, ArrayList<HashMap<String, String>> results, int direction)
	{
		// Setup the dataList results fields
		int ccsid = equationUser.getSession().getCcsid();
		boolean rtlCcsid = EquationCommonContext.isRtlCcsid(ccsid);

		StringBuilder returnData = new StringBuilder();
		StringBuilder returnDataRTL = new StringBuilder();

		if (direction != -1)
		{
			for (HashMap<String, String> row : results)
			{
				setupReturnDataRow(row, returnData, returnDataRTL, ccsid, rtlCcsid);
			}
		}
		else
		{
			HashMap<String, String> row = null;
			for (int i = results.size() - 1; i >= 0; i--)
			{
				row = results.get(i);
				setupReturnDataRow(row, returnData, returnDataRTL, ccsid, rtlCcsid);
			}
		}

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
	 * Setup return data per row
	 * 
	 * @param row
	 * @param returnData
	 * @param returnDataRTL
	 * @param ccsid
	 * @param rtlCcsid
	 */
	private void setupReturnDataRow(HashMap<String, String> row, StringBuilder returnData, StringBuilder returnDataRTL, int ccsid,
					boolean rtlCcsid)
	{
		StringBuffer buffer = DsccnToolbox.getBuffer();
		StringBuffer bufferRTL = DsccnToolbox.getBuffer();
		for (EquationPVFieldMetaData fmd : pvMetaData.getFieldMetaData())
		{
			String fieldName = fmd.getId();
			String fieldValue = null;
			if (EqDataType.isAlpha(fmd.getType()))
			{
				fieldValue = row.get(fieldName);
			}
			else
			{
				fieldValue = row.get(fieldName).trim();
			}
			// append to the buffer
			DsccnToolbox.setupDSCCNFromField(buffer, fieldName, pvMetaData, fieldValue, true);

			// if the ccsid supports RTL, then setup another buffer for outputting (e.g. HTML)
			if (rtlCcsid)
			{
				String fieldValueRTL = null;
				if (EqDataType.isAlpha(fmd.getType()))
				{
					fieldValueRTL = Toolbox.convertTextRTLForDisplay(fieldValue, fieldValue.length(), ccsid, 0);
				}
				else
				{
					fieldValueRTL = row.get(fieldName).trim();
				}
				DsccnToolbox.setupDSCCNFromField(bufferRTL, fieldName, pvMetaData, fieldValueRTL, false);
			}
		}

		returnData.append(buffer + EqDataType.GLOBAL_DELIMETER);
		returnDataRTL.append(bufferRTL + EqDataType.GLOBAL_DELIMETER);

	}
}
