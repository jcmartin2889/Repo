package com.misys.equation.common.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibm.as400.access.AS400Bin2;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.DsccnToolbox;
import com.misys.equation.common.utilities.EqDataListHelper;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.EquationPVMetaDataHelper;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;

/**
 * This class is used in place of the standard EquationDataList, for the non-standard prompt/validate module TVR20R.
 * 
 * @author GoldsmC1
 * 
 */
public class EquationDataListTVR20R implements IDataList
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationDataListTVR20R.java 10671 2011-03-24 13:38:24Z lima12 $";

	private String dataList;
	private static final EquationLogger LOG = new EquationLogger(EquationDataListTVR20R.class);

	/**
	 * This is start of the SQL used by this PV. The following details are effectively hard coded for TVR20R:
	 * <p>
	 * 1st DB file: TY10LF Customer profit structure by customer<br>
	 * 2nd DB file: TV10LF Profit structure<br>
	 * files joined on: TYPSTR and TVPSTR
	 */
	private static final String SQL_BASE = "SELECT * FROM TY10LF INNER JOIN TV10LF ON TYPSTR = TVPSTR";

	/**
	 * Name of the primary file
	 */
	private static final String PRIMARY_FILE = "TY10LF";

	/**
	 * Name of field on TVPF for Profit Categories.
	 */
	private static final String CATEGORIES_FIELD = "TVPC";

	/**
	 * This field is a not a real key field. (It is necessary to have this as a key field in the DS so that the PV works in Validate
	 * mode.)
	 */
	private static final String KEY_EXCEPTION_FIELD = "TYPSTR";

	/**
	 * Construct an empty data list
	 */
	public EquationDataListTVR20R()
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
	 * @param security
	 *            - security mode
	 * @param query
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

		// Build SQL statement to retrieve all profit categories for a given customer
		String sqlCategories = generateProfitCategoriesSQL(pvMetaData, equationPVData);

		// 'standard' query
		String sqlSelect = generateSQL(eqUser, decode, security, equationPVData, maxResults);

		// Perform the query
		performQuery(eqUser, sqlCategories, sqlSelect, maxResults, direction, start, pvMetaData, equationPVData);
	}

	/**
	 * @return Returns the dataList.
	 */
	public String getDataList()
	{
		return dataList;
	}

	/**
	 * @param dataList
	 *            The dataList to set.
	 */
	public void setDataList(String dataList)
	{
		this.dataList = dataList;
	}

	private void performQuery(EquationUser eqUser, String sqlCategories, String sqlSelect, int maxResults, int direction,
					String start, EquationPVMetaData pvMetaData, EquationPVData equationPVData)
	{
		ResultSet categoriesResultSet = null;
		PreparedStatement categoriesStatement = null;
		ResultSet equationResultSet = null;
		PreparedStatement equationStatement = null;

		try
		{
			Connection equationConnection = eqUser.getSession().getConnection();
			StringBuffer returnData = new StringBuffer();
			String[] profitCats;
			List<String> records = new ArrayList<String>();

			// Retrieve profit categories
			categoriesStatement = equationConnection.prepareStatement(sqlCategories, ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			categoriesResultSet = categoriesStatement.executeQuery();

			// we expect only one record
			if (categoriesResultSet.next())
			{
				profitCats = parseCategories(categoriesResultSet.getString(CATEGORIES_FIELD));

				for (String cat : profitCats)
				{
					// insert the profit category into the SQL
					int index = sqlSelect.indexOf(pvMetaData.getId());
					if (index >= 0)
					{
						sqlSelect = sqlSelect.substring(0, index + 10) + cat + sqlSelect.substring(index + 15);
					}

					// Build the result set
					equationStatement = equationConnection.prepareStatement(sqlSelect, ResultSet.TYPE_SCROLL_INSENSITIVE,
									ResultSet.CONCUR_READ_ONLY);
					equationResultSet = equationStatement.executeQuery();

					// Retrieve result
					while (equationResultSet.next())
					{
						AS400Bin2 textLength = new AS400Bin2();
						int length = (Short) textLength.toObject(equationResultSet.getBytes(1));
						records.add(DsccnToolbox
										.setupDSCCNFromBytes(equationResultSet.getBytes(1), equationPVData.getPvMetaData(), length,
														EquationPVMetaDataHelper.LEN_DSEPMS + 2, eqUser.getEquationUnit()
																		.getCcsid(), false));
					}
				}
			}

			// Select the results that we are going to return
			int recordIndex = 0;
			int countResults = 0;
			if (!start.trim().equals(""))
			{
				for (String record : records)
				{
					if (start.equals(record.substring(0, start.length())))
					{
						recordIndex = records.indexOf(record);
						recordIndex += direction == -1 ? -1 : 1;
						break;
					}

				}
			}
			while (recordIndex >= 0 && recordIndex < records.size() && countResults < maxResults)
			{
				returnData.append(records.get(recordIndex) + EqDataType.GLOBAL_DELIMETER);
				recordIndex += direction == -1 ? -1 : 1;
				countResults++;
			}

			// Setup the data list
			dataList = returnData.toString();

			// Remove the last delimeter
			if (!dataList.equals(""))
			{
				dataList = dataList.substring(0, dataList.length() - 3);
			}
		}
		catch (SQLException sqle)
		{
			LOG.error("Replace this", sqle);
			throw new RuntimeException("EqDataList: performQuery: " + Toolbox.getExceptionMessage(sqle), sqle);
		}
		finally
		{
			SQLToolbox.close(equationResultSet);
			SQLToolbox.close(equationStatement);
			SQLToolbox.close(categoriesResultSet);
			SQLToolbox.close(categoriesStatement);
		}
	}

	/**
	 * Generate the profit categories SQL
	 * 
	 * @param pvMetaData
	 *            - prompt validate meta data
	 * @param equationPVData
	 *            - prompt validate data
	 * @return - SQL statement to retrieve the profit categories
	 */
	private String generateProfitCategoriesSQL(EquationPVMetaData pvMetaData, EquationPVData equationPVData)
	{
		// sql statement
		StringBuffer sql = new StringBuffer(SQL_BASE);

		// add the WHERE clause
		boolean firstWhere = true;

		for (EquationPVFieldMetaData fmd : pvMetaData.getFieldMetaData())
		{
			String dsccnName = fmd.getId();

			// translate the query data. If the query is simply an *, then do not include this in the selection anymore
			String queryData = SQLToolbox.replaceWildCard(SQLToolbox.replaceWildCardSng(SQLToolbox
							.replaceSingleQuote(equationPVData.getFieldValue(dsccnName))));
			if (!Toolbox.trimr(queryData).equals("%"))
			{
				String dbField = fmd.getDb();
				String fieldType = fmd.getType();
				int fieldLength = fmd.getLength();

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

				// if there is a database field defined for the field, then perform WHERE clause on the file
				if (!dbField.equals("") && !dbField.equals(KEY_EXCEPTION_FIELD))
				{
					if (firstWhere)
					{
						sql.append(" WHERE ");
						firstWhere = false;
					}
					else
					{
						sql.append(" AND ");
					}

					// field name
					if (fmd.isAlpha())
					{
						sql.append(Toolbox.sqlCharToUppercaseWithRTRIM(dbField));
					}
					else if (fieldType.equals(EquationPVMetaDataHelper.TYPE_DDMMYY))
					{
						sql.append("SUBSTR(UCASE(" + dbField + " + 10000000),7,2)" + " || " + "SUBSTR(UCASE(" + dbField
										+ " + 10000000),5,2)" + " || " + "SUBSTR(UCASE(" + dbField + " + 10000000),3,2)" + " ");
					}
					else
					{
						sql.append(Toolbox.sqlNumberToCharFormat(dbField, fieldLength));
					}

					sql.append(" LIKE ");

					// field value
					if (fmd.isAlpha())
					{
						sql.append("UCASE('" + Toolbox.trimr(queryData) + "') ");
					}
					else if (fieldType.equals(EquationPVMetaDataHelper.TYPE_DDMMYY))
					{
						sql.append("UCASE('" + queryData + "') ");
					}
					else if (fmd.isZoned())
					{
						if (queryData.contains("%"))
						{
							sql.append("'" + queryData + "'");
						}
						else
						{
							sql.append("'" + Toolbox.padAtFront(queryData, "0", fieldLength) + "'");
						}
					}
					else
					{
						sql.append("'" + queryData + "'");
					}
					sql.append(" ");
				}
			}
		}

		return sql.toString();
	}

	/**
	 * Generate the SQL statement
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * @param decode
	 *            - decode character
	 * @param security
	 *            - security mode
	 * @param equationPVData
	 *            - prompt validate data
	 * @param maxResults
	 *            - maximum number of data to return
	 */
	private String generateSQL(EquationUser eqUser, String decode, String security, EquationPVData equationPVData, int maxResults)
	{
		// Set up primary table and key from meta-data based on decode
		EquationPVMetaData pvMetaData = equationPVData.getPvMetaData();
		String serviceKey = getKeyfieldWithSubstitution(eqUser, decode, equationPVData);

		// SQL statement buffer
		StringBuffer sqlSelect = new StringBuffer();

		// Build the SQL statement
		sqlSelect.append("WITH RS AS ( ");
		sqlSelect.append("SELECT UTW06RFNC('" + decode + "','" + pvMetaData.getId() + "', " + serviceKey + ",'N','N'," + "'"
						+ security + "'," + "''" + ") AS DTA," + serviceKey + " AS DTAKEY FROM " + PRIMARY_FILE + " ");

		// Append the search criteria
		StringBuffer fieldWithoutDb = new StringBuffer();

		boolean firstWhere = true;
		boolean firstRS = true;

		for (EquationPVFieldMetaData fmd : pvMetaData.getFieldMetaData())
		{
			String dsccnName = fmd.getId();

			// translate the query data. If the query is simply an *, then do not include this in the selection anymore
			String queryData = SQLToolbox.replaceWildCard(SQLToolbox.replaceWildCardSng(SQLToolbox
							.replaceSingleQuote(equationPVData.getFieldValue(dsccnName))));
			if (!Toolbox.trimr(queryData).equals("%"))
			{
				String dbField = fmd.getDb();
				String fieldType = fmd.getType();
				int fieldLength = fmd.getLength();

				// Exceptions...
				if (!dbField.equals(KEY_EXCEPTION_FIELD))
				{
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
						if (!fmd.getComposite())
						{
							if (firstRS)
							{
								firstRS = false;
							}
							else
							{
								fieldWithoutDb.append(" AND ");
							}
							fieldWithoutDb.append("UCASE(TRIM(SUBSTR(RS.DTA,"
											+ (EquationPVMetaDataHelper.LEN_DSEPMS + fmd.getIndex() + 1) + "," + fieldLength
											+ "))) LIKE UCASE(TRIM('" + Toolbox.trimr(queryData) + "')) ");
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
							sqlSelect.append(Toolbox.sqlCharToUppercaseWithRTRIM(dbField));
						}
						else if (fieldType.equals(EquationPVMetaDataHelper.TYPE_DDMMYY))
						{
							sqlSelect.append("SUBSTR(UCASE(" + dbField + " + 10000000),7,2)" + " || " + "SUBSTR(UCASE(" + dbField
											+ " + 10000000),5,2)" + " || " + "SUBSTR(UCASE(" + dbField + " + 10000000),3,2)" + " ");
						}
						else
						{
							sqlSelect.append(Toolbox.sqlNumberToCharFormat(dbField, fieldLength));
						}

						sqlSelect.append(" LIKE ");

						// field value
						if (fmd.isAlpha())
						{
							sqlSelect.append("UCASE('" + Toolbox.trimr(queryData) + "') ");
						}
						else if (fieldType.equals(EquationPVMetaDataHelper.TYPE_DDMMYY))
						{
							sqlSelect.append("UCASE('" + queryData + "') ");
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

		sqlSelect.append(") ");
		sqlSelect.append("SELECT * FROM RS WHERE SUBSTR(RS.DTA,2,2)<>'SM' ");
		if (fieldWithoutDb.length() > 0)
		{
			sqlSelect.append(" AND ");
			sqlSelect.append(fieldWithoutDb);
		}

		// Return the statement
		return sqlSelect.toString();
	}

	/**
	 * Parse the single Profit Categories field into an array.
	 * 
	 * @param categories
	 *            (single field)
	 * 
	 * @return array of categories
	 */
	private String[] parseCategories(String categories)
	{
		List<String> result = new ArrayList<String>();
		String cat;

		for (int i = 0; i < categories.length(); i = i + 5)
		{
			cat = categories.substring(i, i + 5);
			if (cat != null && !cat.trim().equals(""))
			{
				result.add(cat);
			}
		}

		return result.toArray(new String[1]);
	}

	/**
	 * Return the key fields with substitution of variables (e.g. �USER, �PDATEDB, number formating for SQL)
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * @param decode
	 *            - decode
	 * @param pvData
	 *            - the Equation PV data
	 * 
	 * @return key fields
	 */
	private String getKeyfieldWithSubstitution(EquationUser eqUser, String decode, EquationPVData pvData)
	{
		StringBuffer serviceKeyBuff = new StringBuffer();
		String delimiter = " || ";

		EquationPVMetaData pvMetaData = pvData.getPvMetaData();
		List<String> keyfields = pvMetaData.getDecodeMetaData(decode).getKeyFields();

		// loop through all the key fields
		for (int i = 0; i < keyfields.size(); i++)
		{
			String keyField = keyfields.get(i).trim();
			EquationPVFieldMetaData fmd;
			String fieldType;
			int fieldLength;

			// determine if the key field is a database field
			String dsccnName = pvMetaData.getDSCCNName(keyField);
			if (dsccnName != null)
			{
				// get the field meta-data
				fmd = pvMetaData.rtvFieldMetaData(dsccnName);
				fieldType = fmd.getType();
				fieldLength = fmd.getLength();

				// Exceptions...
				if (keyField.equals(KEY_EXCEPTION_FIELD))
				{
					serviceKeyBuff.append("'" + Toolbox.pad(null, " ", fieldLength) + "'");
				}
				else

				if (fmd.isAlpha())
				{
					serviceKeyBuff.append("UCASE(" + keyField + ") ");
				}
				else

				if (fieldType.equals(EquationPVMetaDataHelper.TYPE_DDMMYY))
				{
					serviceKeyBuff.append("SUBSTR(UCASE(" + keyField + " + 10000000),7,2)" + " || " + "SUBSTR(UCASE(" + keyField
									+ " + 10000000),5,2)" + " || " + "SUBSTR(UCASE(" + keyField + " + 10000000),3,2) ");
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
			if (i < keyfields.size() - 1)
			{
				serviceKeyBuff.append(delimiter);
			}
		}

		return serviceKeyBuff.toString();
	}

	/**
	 * Return null as this is not implemented here
	 * 
	 * @return return null
	 */
	public String getDataListForDisplay()
	{
		return null;
	}

}
