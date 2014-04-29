package com.misys.equation.common.utilities;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetMetaDataImpl;

import org.apache.commons.beanutils.BeanUtils;

import com.misys.equation.common.access.EquationConsolidatedTable;
import com.misys.equation.common.access.EquationDataStructure;
import com.misys.equation.common.access.EquationDataStructureData;
import com.misys.equation.common.access.EquationStandardQueryList;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTable;
import com.misys.equation.common.internal.eapi.core.EQRuntimeException;
import com.misys.equation.common.language.LanguageResources;
import com.sun.rowset.CachedRowSetImpl;

public class SQLToolbox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SQLToolbox.java 17189 2013-09-03 11:49:03Z Lima12 $";
	public static final String FROM = " FROM ";
	public static final String WHERE = " WHERE ";
	public static final String GROUP_BY = " GROUP BY ";
	public static final String ORDER_BY = " ORDER BY ";
	public static final String HAVING = " HAVING ";
	public static final String FOR_UPDATE_OF = " FOR UPDATE OF ";
	public static final String UNION = " UNION ";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(SQLToolbox.class);

	/** Map of SQL types (Integer) to type names */
	private static Map<Integer, String> sqlTypeNameMap;

	/** Pattern definition for searching for variables of the form ${xxx} within a template string */
	private final static Pattern VAR_PATTERN = Pattern.compile("\\$\\{([a-zA-Z0-9\\.\\[\\]\\(\\)'\\$]+)\\}");

	/**
	 * Static block to initialize the collection of SQL type names
	 */
	static
	{
		sqlTypeNameMap = new HashMap<Integer, String>();

		// Use reflection to add the names of all the types to the map
		Field[] fields = java.sql.Types.class.getFields();
		for (Field field : fields)
		{
			try
			{
				String name = field.getName();
				Integer value = (Integer) field.get(null);
				sqlTypeNameMap.put(value, name);
			}
			catch (IllegalAccessException e)
			{
			}
		}
	}

	/**
	 * Return a valid SQL field name
	 * 
	 * @param fieldName
	 *            field name
	 * 
	 * @return valid SQL field name
	 */
	public static String cvtToSQLFieldName(String fieldName)
	{
		StringBuffer str = new StringBuffer(fieldName);

		// replace all the dots
		int idx;
		while ((idx = str.indexOf(".")) >= 0)
		{
			str.replace(idx, idx + 1, "_");
		}

		return str.toString();
	}

	/**
	 * Return a valid SQL field name
	 * 
	 * @param fieldDesc
	 *            field description
	 * 
	 * @return valid SQL field name
	 */
	public static String cvtToSQLFieldColHdg(String fieldDesc)
	{
		return (fieldDesc.replaceAll("\\?", "")).replaceAll("'", "''");
	}

	/**
	 * Return the equivalent SQL field type
	 * 
	 * @param fieldType
	 *            field type
	 * @param fieldLength
	 *            field length
	 * @param fieldDec
	 *            number of decimals
	 * 
	 * @return equivalent SQL field type
	 */
	public static String cvtToSQLFieldType(String fieldType, String fieldLength, String fieldDec)
	{

		if (fieldType.equals(EqDataType.TYPE_DATE) || fieldType.equals(EqDataType.TYPE_ZONED))
		{
			int dec = Toolbox.parseInt(fieldDec, 0);
			return "NUMERIC (" + cvtToSQLFieldLength(fieldLength) + "," + String.valueOf(dec) + ") ";
		}

		else if (fieldType.equals(EqDataType.TYPE_PACKED))
		{
			int dec = Toolbox.parseInt(fieldDec, 0);
			return "DECIMAL (" + cvtToSQLFieldLength(fieldLength) + "," + String.valueOf(dec) + ") ";
		}

		// default is CHAR
		return "CHAR (" + cvtToSQLFieldLength(fieldLength) + ") ";
	}

	/**
	 * Return the equivalent SQL field length
	 * 
	 * @param fieldLength
	 *            field length
	 * 
	 * @return equivalent SQL field type
	 */
	public static String cvtToSQLFieldLength(String fieldLength)
	{
		int length = Toolbox.parseInt(fieldLength, 1);
		if (length <= 0)
		{
			length = 1;
		}
		return String.valueOf(length);
	}

	/**
	 * Determine the CCSID of the field
	 * 
	 * @param fieldType
	 *            field type
	 * 
	 * @return CCSID of the field
	 */
	public static String cvtToSQLCCSID(String fieldType)
	{
		if (fieldType.equals(EqDataType.TYPE_DATE) || fieldType.equals(EqDataType.TYPE_PACKED)
						|| fieldType.equals(EqDataType.TYPE_ZONED))
		{
			return "";
		}

		// default is CHAR field
		return "CCSID 37";
	}

	/**
	 * Return the default value of the field
	 * 
	 * @param fieldType
	 *            field type
	 * 
	 * @return default value of the field
	 */
	public static String cvtToSQLDefault(String fieldType)
	{
		if (fieldType.equals(EqDataType.TYPE_DATE) || fieldType.equals(EqDataType.TYPE_PACKED)
						|| fieldType.equals(EqDataType.TYPE_ZONED))
		{
			return "0";
		}

		// default is CHAR field
		return "''";
	}

	/**
	 * Return the correct size of a field
	 * 
	 * @param fieldValue
	 *            - value
	 * @param fieldType
	 *            - field type
	 * @param fieldSize
	 *            - maximum field size
	 * 
	 * @return the value with the correct length
	 */
	public static String cvtToSQLValue(String fieldValue, String fieldType, String fieldSize)
	{
		int length = Toolbox.parseInt(fieldSize, 1);

		if (fieldType.equals(EqDataType.TYPE_DATE) || fieldType.equals(EqDataType.TYPE_PACKED)
						|| fieldType.equals(EqDataType.TYPE_ZONED))
		{
			return fieldValue;
		}

		// For string, ensure within the maximum string
		if (fieldValue.length() > length)
		{
			return "'" + fieldValue.substring(0, length).replaceAll("'", "''") + "'";
		}
		else
		{
			return "'" + fieldValue.replaceAll("'", "''") + "'";
		}
	}

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
	 * Similar to getQcmdexcString except that it replaces ' with ''
	 * 
	 * @param command
	 *            - a valid OS400 command string
	 * 
	 * @return a String containing the formatted SQL required to call QCMDEXC with the specified command
	 */
	public static String getQcmdexcString2(String command)
	{
		String s = command.replaceAll("'", "''");
		return "CALL QCMDEXC('" + s + "'," + Toolbox.leftZeroPad(command.length(), 10) + ".00000)";
	}

	/**
	 * Replace the String with single quote with double quote
	 * 
	 * @param str
	 *            - the string
	 * 
	 * @return the update string with single quote replaced by 2 quotes
	 */
	public static String replaceSingleQuote(String str)
	{
		return (str.replaceAll("'", "''"));
	}

	/**
	 * Replace the String with * with %
	 * 
	 * @param str
	 *            - the string
	 * 
	 * @return the update string with single quote replaced by 2 quotes
	 */
	public static String replaceWildCard(String str)
	{
		return (str.replaceAll("\\*", "%"));
	}
	/**
	 * Replace the String with ? with _
	 * 
	 * @param str
	 *            - the string
	 * 
	 * @return the update string with single quote replaced by 2 quotes
	 */
	public static String replaceWildCardSng(String str)
	{
		return (str.replaceAll("\\?", "_"));
	}
	/**
	 * @param table
	 * @return a String containing a where clause for the table
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
			sqlString.append(keyNames[i] + "=" + quote + SQLToolbox.replaceSingleQuote(tableData.getFieldValue(keyNames[i]))
							+ quote);
		}
		return sqlString.toString();
	}

	/**
	 * Construct the SQL SELECT where clause part given the data structure and the selection string
	 * 
	 * @param dataStructure
	 *            - the data structure
	 * @param zlslct
	 *            - the selection string
	 * 
	 * @return the SQL SELECT where clause part
	 */
	public static String getWhereFromZLSLCT(EquationDataStructure dataStructure, String zlslct)
	{
		zlslct = Toolbox.pad(zlslct, dataStructure.getInitialBytes().length);
		StringBuilder sqlString = new StringBuilder();
		Set<String> fieldNames = dataStructure.getFieldNames();
		int fieldOffset = 0;
		zlslct = zlslct.replace("*".charAt(0), "%".charAt(0));
		zlslct = zlslct.replace("?".charAt(0), "_".charAt(0));
		int maxLength = zlslct.length();

		for (String fieldName : fieldNames)
		{
			// field Name
			int fieldLength = dataStructure.getFieldLength(fieldName);

			// field Name
			int endIndex = fieldOffset + fieldLength;
			String fieldValue = "";
			if (endIndex > maxLength)
			{
				fieldValue = zlslct.substring(fieldOffset);
			}
			else
			{
				fieldValue = zlslct.substring(fieldOffset, endIndex);
			}

			// Offset
			fieldOffset = fieldOffset + fieldLength;

			// Quote required
			String quote = "";
			int fieldDataType = dataStructure.getFieldDataType(fieldName);
			if (fieldDataType == Types.CHAR || fieldDataType == Types.BINARY || fieldDataType == Types.VARCHAR
							|| fieldDataType == Types.VARBINARY || fieldDataType == Types.CLOB || fieldDataType == Types.TIME
							|| fieldDataType == Types.DATE || fieldDataType == Types.TIMESTAMP)
			{
				quote = "'";
			}

			String comparator = "=";
			if (fieldValue.indexOf("%") > -1 || fieldValue.indexOf("_") > -1)
			{
				comparator = " LIKE ";
				quote = "'"; // always enclosed in quotes
			}

			// add the condition to the where clause
			if (!fieldValue.trim().equals("%"))
			{
				if (sqlString.length() > 0)
				{
					sqlString.append(" AND ");
				}

				// for date/time/timestamp, always cast into characters
				if (fieldDataType == Types.TIME || fieldDataType == Types.DATE || fieldDataType == Types.TIMESTAMP)
				{
					sqlString.append("cast(" + fieldName + " as char(" + dataStructure.getFieldLength(fieldName) + "))"
									+ comparator + quote + fieldValue.trim() + quote);
				}

				// for other types
				else
				{
					sqlString.append("UCASE(" + fieldName + ")" + comparator + "UCASE(" + quote + fieldValue.trim() + quote + ")");
				}
			}
		}
		return sqlString.toString();
	}

	/**
	 * @param dataStructure
	 * @return
	 */
	public static String[] getWhereFromKeyLast(EquationConsolidatedTable table, String keyLast, int units)
	{
		String[] wheres = new String[units];
		String[] keyFields = table.getKeyFields();

		for (int i = 0; i < units; i++)
		{
			wheres[i] = "";
			if (keyLast.length() > 0)
			{
				int unitIdx = Integer.parseInt(keyLast.substring(table.getKeyLength()));
				StringBuilder sqlString = new StringBuilder();
				String operator = ">=";
				if (i <= unitIdx)
				{
					operator = ">";
				}
				// int offset = 0;
				for (int j = 0; j < keyFields.length; j++)
				{
					// int length = table.getDataStructure().getFieldLength(keyFields[j]);
					// String keyValue = "'" + keyLast.substring(offset, offset + length).trim() + "'";
					// offset += length;
					sqlString.append(keyFields[j]);
					if (j != keyFields.length - 1)
					{
						sqlString.append(" || ");
					}
					else
					{
						sqlString.append(" " + operator + "'" + Toolbox.trimr(keyLast.substring(0, table.getKeyLength())) + "'");
					}
				}
				wheres[i] = sqlString.toString();
			}
		}
		return wheres;
	}

	/**
	 * @param dataStructure
	 * @return
	 */
	public static String[] getWhereFromKeyLast(EquationConsolidatedTable table, String keyLast, int units, int direction)
	{
		String[] wheres = new String[units];
		String[] keyFields = table.getKeyFields();
		EquationDataStructure eqDs = table.getDataStructure();

		if (table.isConsolidatedQuery() && !keyLast.equals(""))
		{
			// Remove 31 for 'real' pre-key containing system/unit/des
			// String unit = keyLast.substring(0, 8);
			// String system = keyLast.substring(8, 11);
			// String unitDes = keyLast.substring(11, 31);
			keyLast = keyLast.substring(31);
		}

		for (int i = 0; i < units; i++)
		{
			wheres[i] = "";
			if (keyLast != null && keyLast.length() > 0)
			{
				StringBuilder sqlString = new StringBuilder(1024);
				String operator;
				if (direction == 1)
				{
					operator = ">";
				}
				else
				{
					operator = "<";
				}

				for (int j = 0; j < keyFields.length; j++)
				{
					String fieldName = keyFields[j];
					int type = eqDs.getFieldDataType(fieldName);
					if (type == Types.DECIMAL || type == Types.NUMERIC)
					{
						sqlString.append(Toolbox.sqlNumberToCharFormat(fieldName, eqDs.getFieldLength(fieldName)));
					}
					else
					{
						// for non-numeric, simply add the field name
						sqlString.append(fieldName);
					}

					if (j != keyFields.length - 1)
					{
						sqlString.append(" || ");
					}
					else
					{
						sqlString.append(" " + operator + "'" + Toolbox.trimr(keyLast.substring(0, table.getKeyLength())) + "'");
					}
				}
				wheres[i] = sqlString.toString();
			}
		}
		return wheres;
	}

	/**
	 * Construct the SQL SELECT where clause part for non-blank data
	 * 
	 * @param dataStructureData
	 *            - the data structure
	 * @param keys
	 *            - the list of keys to be included in the where clause
	 * 
	 * @return the SQL SELECT where clause part
	 */
	public static String getWhere(EquationDataStructureData dataStructureData, List<String> keys)
	{
		EquationDataStructure dataStructure = dataStructureData.getEqDS();
		StringBuilder sqlString = new StringBuilder();
		Set<String> fieldNames = dataStructure.getFieldNames();

		for (String fieldName : fieldNames)
		{
			String fieldValue = dataStructureData.getFieldValue(fieldName);

			// if keys is not specified, then include all fields
			if (keys != null)
			{
				// is the field name not in the list, then ignore it
				if (!keys.contains(fieldName))
				{
					continue;
				}
			}

			// Quote required
			String quote = "";
			int fieldDataType = dataStructure.getFieldDataType(fieldName);
			if (fieldDataType == Types.CHAR || fieldDataType == Types.BINARY || fieldDataType == Types.VARCHAR
							|| fieldDataType == Types.VARBINARY || fieldDataType == Types.CLOB || fieldDataType == Types.TIME
							|| fieldDataType == Types.DATE || fieldDataType == Types.TIMESTAMP)
			{
				quote = "'";
			}

			// add the condition to the where clause
			if (fieldValue.trim().length() > 0)
			{
				String comparator = "=";
				if (sqlString.length() > 0)
				{
					sqlString.append(" AND ");
				}

				// for date/time/timestamp, always cast into characters
				if (fieldDataType == Types.TIME || fieldDataType == Types.DATE || fieldDataType == Types.TIMESTAMP)
				{
					sqlString.append("cast(" + fieldName + " as char(" + dataStructure.getFieldLength(fieldName) + "))"
									+ comparator + quote + fieldValue.trim() + quote);
				}

				// for other types
				else
				{
					sqlString.append(fieldName + comparator + quote + fieldValue.trim() + quote);
				}

			}
		}
		return sqlString.toString();
	}

	public static String injectWhereToSQLString(String sql, String where)
	{
		StringBuilder sqlString = new StringBuilder();
		where = where.trim();
		if (where.length() > 0)
		{
			int whereIndex = sql.indexOf(WHERE);
			int groupByIndex = sql.indexOf(GROUP_BY);
			int havingIndex = sql.indexOf(HAVING);
			int orderByIndex = sql.indexOf(ORDER_BY);
			int forUpdateOfIndex = sql.indexOf(FOR_UPDATE_OF);
			int unionIndex = sql.indexOf(UNION);
			if (whereIndex > -1)
			{
				sqlString.append(sql.substring(0, whereIndex + WHERE.length()) + where + " AND "
								+ sql.substring(whereIndex + WHERE.length()));
			}
			else if (groupByIndex > -1)
			{
				sqlString.append(sql.substring(0, groupByIndex) + WHERE + where + sql.substring(groupByIndex));
			}
			else if (havingIndex > -1)
			{
				sqlString.append(sql.substring(0, havingIndex) + WHERE + where + sql.substring(havingIndex));
			}
			else if (orderByIndex > -1)
			{
				sqlString.append(sql.substring(0, orderByIndex) + WHERE + where + sql.substring(orderByIndex));
			}
			else if (forUpdateOfIndex > -1)
			{
				sqlString.append(sql.substring(0, forUpdateOfIndex) + WHERE + where + sql.substring(forUpdateOfIndex));
			}
			else if (unionIndex > -1)
			{
				sqlString.append(sql.substring(0, unionIndex) + WHERE + where + sql.substring(unionIndex));
			}
			else
			{
				sqlString.append(sql + WHERE + where);
			}
			return sqlString.toString();
		}
		else
		{
			return sql;
		}
	}

	/**
	 * Injects variables into the SQL statement of the form ${xxx} using the given context object and jakarta PropertyUtils.
	 * <p>
	 * Sample usage:
	 * 
	 * <PRE>
	 * &lt;code&gt;
	 * 	final String template = &quot;SELECT * FROM KFIL${unit}/XXXX WHERE EQUSR = ${user.name}&quot;;
	 * 	final Map&lt;String, Object&gt; context = new HashMap&lt;String, Object&gt;();
	 * 	context.put(&quot;unit&quot;, &quot;GP4&quot;);
	 * 
	 * 	final Map&lt;String, String&gt; userInfo = new HashMap&lt;String, String&gt;();
	 * 	userInfo.put(&quot;name&quot;, &quot;Europa&quot;);
	 * 	context.put(&quot;user&quot;, userInfo);
	 * 
	 * 	System.out.println(injectVariables(template, context));
	 * &lt;/code&gt;
	 * </PRE>
	 * 
	 * @param template
	 *            A template SQL statement
	 * @param context
	 *            The context object to use for populating variables
	 * @return The original SQL statement with variables
	 */
	public static String injectVariables(String template, Object context)
	{
		// keep track of the 'var' attribute so that we can properly report errors
		String var = null;
		try
		{
			final StringBuilder sql = new StringBuilder();
			final Matcher m = VAR_PATTERN.matcher(template);

			int index = 0;
			while (m.find())
			{
				// get variable name
				var = m.group(1);
				sql.append(template.substring(index, m.start()));
				index = m.end();

				// get the variable's value
				final String varValue = BeanUtils.getProperty(context, var);
				if (varValue == null)
				{
					throw new IllegalArgumentException("Variable not found in context: " + var);
				}

				sql.append(varValue);
			}

			// append remaining
			sql.append(template.substring(index));

			// return SQL with variables injected
			return sql.toString();
		}
		catch (InvocationTargetException ite)
		{
			// wrap in runtime error (unexpected since we are dealing with templates!)
			throw new IllegalArgumentException("Unable to retrieve value: " + var, ite);
		}
		catch (IllegalAccessException iae)
		{
			// wrap in runtime error (unexpected since we are dealing with templates!)
			throw new IllegalArgumentException("Unable to access variable: " + var, iae);
		}
		catch (NoSuchMethodException nsme)
		{
			// wrap in runtime error (unexpected since we are dealing with templates!)
			throw new IllegalArgumentException("Invalid variable: " + var, nsme);
		}
	}

	/**
	 * Converts the given filter that may contain wildcards into a Pattern instance. Valid wildcard characters for the 'pattern'
	 * includes '?' and '*'; any other character will be treated as a literal.
	 * 
	 * @param pattern
	 *            The search pattern
	 * @return A {@link Pattern} instance that can be used for searching strings of text based on the given wildcard pattern text.
	 */
	public static Pattern wildcardsToPattern(String pattern, boolean caseSensitive)
	{
		// convert to a regex pattern for comparison
		StringBuilder patternText = new StringBuilder("^");
		pattern = pattern.trim();
		for (int i = 0; i < pattern.length(); i++)
		{
			final char c = pattern.charAt(i);
			if (c == '?')
			{
				// underscores match exactly one character
				patternText.append(".");
			}
			else if (c == '*')
			{
				// asterisks match any number of characters (even zero)
				patternText.append(".*");
			}
			else if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'))
			{
				// alphanumeric characters should match exactly
				patternText.append(c);
			}
			else
			{
				// anything else is treated as a literal; for safety, we escape it into hex codes to avoid pattern compilation
				// errors
				patternText.append("\\x");
				patternText.append(Integer.toHexString(c & 0xffff));
			}
		}

		// match to end of line
		patternText.append("$");

		// convert to regex pattern for matching...
		return Pattern.compile(patternText.toString(), caseSensitive ? 0 : Pattern.CASE_INSENSITIVE);
	}

	/**
	 * Checks if the given 'test' string matches the pattern. Valid wildcard characters for the 'pattern' includes '?' and '*'; any
	 * other character will be treated as a literal.
	 * <p>
	 * E.g.: <code>
	 * 		SQLToolbox.matches("GP*", "GP4"); // --> true
	 * 		SQLToolbox.matches("GP*", "GR4"); // --> false
	 * 		SQLToolbox.matches("GP*", "GR4"); // --> false
	 * 		SQLToolbox.matches("G??", "GPX"); // --> true
	 * 		SQLToolbox.matches("G??", "GP"); // --> false
	 * 		SQLToolbox.matches("G?", "GP"); // --> true
	 * 		SQLToolbox.matches("G?", "GP "); // --> true
	 * </code>
	 * 
	 * @param pattern
	 *            The pattern use to check for a match against the test string.
	 * @param test
	 *            The test string to check for a match against the pattern.
	 * @return true if the test string matches the given pattern, false otherwise.
	 */
	public static boolean matches(String pattern, String test)
	{
		// convert the wildcards pattern text into a Pattern instance
		final Pattern regex = wildcardsToPattern(pattern, false);

		// then check for a match
		return regex.matcher(test != null ? test.trim() : "").matches();
	}
	/**
	 * Construct a list of comma-separated field values of the Equation Standard Table
	 * 
	 * @param table
	 *            - the Equation standard table
	 * 
	 * @return a list of comma-separated field values of the Equation Standard Table
	 */
	public static String getValuesFromTable(EquationStandardTable table)
	{
		StringBuilder sqlString = new StringBuilder();
		EquationDataStructureData tableData = table.getTableData();
		EquationDataStructure tableDataStructure = tableData.getEqDS();
		Set<String> fieldNames = tableDataStructure.getFieldNames();

		for (String fieldName : fieldNames)
		{
			String quote = "";
			int fieldDataType = tableDataStructure.getFieldDataType(fieldName);
			if (fieldDataType == Types.CHAR || fieldDataType == Types.BINARY || fieldDataType == Types.VARCHAR
							|| fieldDataType == Types.VARBINARY || fieldDataType == Types.TIMESTAMP)
			{
				quote = "'";
			}
			sqlString.append(quote + tableData.getFieldValue(fieldName) + quote + ",");
		}
		sqlString.deleteCharAt(sqlString.length() - 1);
		return sqlString.toString();
	}

	/**
	 * Construct a list of comma-separated field name/field values of the Equation Standard Table
	 * 
	 * @param table
	 *            - the Equation standard table
	 * 
	 * @return a list of comma-separated field name/field values of the Equation Standard Table
	 */
	public static String getUpdateValuesFromTable(EquationStandardTable table)
	{
		StringBuilder sqlString = new StringBuilder();
		EquationDataStructureData tableData = table.getTableData();
		EquationDataStructure tableDataStructure = tableData.getEqDS();
		Set<String> fieldNames = tableDataStructure.getFieldNames();

		for (String fieldName : fieldNames)
		{
			String quote = "";
			int fieldDataType = tableDataStructure.getFieldDataType(fieldName);
			if (fieldDataType == Types.CHAR || fieldDataType == Types.BINARY || fieldDataType == Types.VARCHAR
							|| fieldDataType == Types.VARBINARY || fieldDataType == Types.TIMESTAMP)
			{
				quote = "'";
			}
			sqlString.append(fieldName + "=" + quote + tableData.getFieldValue(fieldName) + quote + ",");
		}
		sqlString.deleteCharAt(sqlString.length() - 1);
		return sqlString.toString();
	}

	/**
	 * Construct the SQL statement to select from the Equation Standard Table
	 * 
	 * @param table
	 *            - the Equation standard table
	 * 
	 * @return the SQL statement to the from the Equation Standard Table
	 */
	public static String getSelectFromTable(EquationStandardTable table)
	{
		// set the keys and retrieve
		EquationDataStructureData tableData = table.getTableData();
		EquationDataStructure tableDataStructure = tableData.getEqDS();
		Set<String> fieldNames = tableDataStructure.getFieldNames();

		// list of fields for SELECT has been specified?
		if (table.getFieldsForSelect() != null && table.getFieldsForSelect().trim().length() > 0)
		{
			fieldNames = table.rtvFields();
		}

		String tableName = table.getTableName();

		// Start to build the sql
		StringBuilder sqlString = new StringBuilder();
		sqlString.append("SELECT ");
		sqlString.append(Toolbox.cvtSetToCSV(fieldNames));

		// Add the table name
		sqlString.append(" FROM ");
		sqlString.append(tableName);

		// Add the where
		if (table.getKeys().trim().length() > 0)
		{
			sqlString.append(" WHERE ");
			sqlString.append(getWhereFromTable(table));
		}

		return sqlString.toString();
	}

	/**
	 * Construct the SQL statement to add to the Equation Standard Table
	 * 
	 * @param table
	 *            - the Equation standard table
	 * 
	 * @return the SQL statement to add to the Equation Standard Table
	 */
	public static String getInsertFromTable(EquationStandardTable table)
	{
		// set the keys and retrieve
		EquationDataStructureData tableData = table.getTableData();
		EquationDataStructure tableDataStructure = tableData.getEqDS();
		Set<String> fieldNames = tableDataStructure.getFieldNames();
		String tableName = table.getTableName();

		// Start to build the sql
		StringBuilder sqlString = new StringBuilder();
		sqlString.append("INSERT INTO ");

		// Add the table name
		sqlString.append(tableName);

		// Add the field names
		sqlString.append("(" + Toolbox.cvtSetToCSV(fieldNames) + ") ");

		// Add the field Values
		sqlString.append("VALUES(" + getValuesFromTable(table) + ")");

		return sqlString.toString();
	}

	/**
	 * Construct the SQL statement to update an Equation Standard Table
	 * 
	 * @param table
	 *            - the Equation standard table
	 * 
	 * @return the SQL statement to update an Equation Standard Table
	 */
	public static String getUpdateFromTable(EquationStandardTable table)
	{
		// Start to build the sql
		StringBuilder sqlString = new StringBuilder();
		sqlString.append("UPDATE ");

		// Add the table name
		sqlString.append(table.getTableName());

		// Add the set
		sqlString.append(" SET ");

		// Add the field names
		sqlString.append(getUpdateValuesFromTable(table));

		// Add the where
		sqlString.append(" WHERE ");
		sqlString.append(getWhereFromTable(table));
		return sqlString.toString();
	}

	/**
	 * Construct the SQL statement to delete an Equation Standard Table
	 * 
	 * @param table
	 *            - the Equation standard table
	 * 
	 * @return the SQL statement to delete an Equation Standard Table
	 */
	public static String getDeleteFromTable(EquationStandardTable table)
	{
		// Start to build the sql
		StringBuilder sqlString = new StringBuilder();
		sqlString.append("DELETE FROM ");

		// Add the table name
		sqlString.append(table.getTableName());

		// Add the where
		sqlString.append(" WHERE ");
		sqlString.append(getWhereFromTable(table));

		// Return the SQL
		return sqlString.toString();
	}

	/**
	 * Translates java.sql.Type types to the Service Composer data types
	 * 
	 * @param sqlDataType
	 *            An integer containing a java.sql.Type value
	 * @return a String containing one of the the EqDataType values.
	 * @throws EQRuntimeException
	 *             if the SQL type is not supported
	 */
	public static String sqlDataTypeToEqDataType(int sqlDataType)
	{
		switch (sqlDataType)
		{
			case java.sql.Types.CHAR:
			case java.sql.Types.VARCHAR:
				return EqDataType.TYPE_CHAR;
			case java.sql.Types.DECIMAL:
				return EqDataType.TYPE_PACKED;
			case java.sql.Types.NUMERIC:
				return EqDataType.TYPE_ZONED;
			default:
				throw new EQRuntimeException("Unsupported java.sql.Type = [" + sqlDataType + "]");
		}
	}

	/**
	 * Returns a String name of the specified SQL type
	 * 
	 * @param sqlType
	 *            integer SQL Type value
	 * @return The name of the type (obtained by using reflection)
	 */
	public static String getSQLTypeName(int sqlType)
	{
		return sqlTypeNameMap.get(sqlType);
	}

	/**
	 * Creates a CachedRowSet from a ResultSet object.
	 * <p>
	 * This method should be used instead of the CachedRowSetImpl.populate method, as that does not work with a AS400JDBCResultSet.
	 * 
	 * @param rs
	 *            The ResultSet to copy from
	 * @param limitBytes
	 *            - the maximum bytes to be retrieved
	 * 
	 * @return The populated CachedRowSet
	 * 
	 * @throws SQLException
	 */
	public static CachedRowSet populate(ResultSet rs, long limitBytes) throws SQLException
	{
		return populate(rs, limitBytes, null);
	}

	/**
	 * Creates a CachedRowSet from a ResultSet object.
	 * <p>
	 * This method should be used instead of the CachedRowSetImpl.populate method, as that does not work with a AS400JDBCResultSet.
	 * 
	 * @param rs
	 *            The ResultSet to copy from
	 * @param limitBytes
	 *            - the maximum bytes to be retrieved
	 * @param query
	 *            - the Equation Standard Query
	 * 
	 * @return The populated CachedRowSet
	 * 
	 * @throws SQLException
	 */
	public static CachedRowSet populate(ResultSet rs, long limitBytes, EquationStandardQueryList query) throws SQLException
	{
		CachedRowSet rowset = new CachedRowSetImpl();
		rowset.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
		rowset.setConcurrency(ResultSet.CONCUR_UPDATABLE);
		ResultSetMetaData rsMeta = rs.getMetaData();

		// Record length
		int recordLength = 0;

		// Create the rowset metadata from the resultset metadata
		RowSetMetaData rowsetMetaData = new RowSetMetaDataImpl();
		rowsetMetaData.setColumnCount(rsMeta.getColumnCount());
		for (int i = 1; i < rsMeta.getColumnCount() + 1; i++)
		{
			rowsetMetaData.setColumnName(i, rsMeta.getColumnName(i));
			rowsetMetaData.setColumnType(i, rsMeta.getColumnType(i));
			rowsetMetaData.setColumnLabel(i, rsMeta.getColumnLabel(i));
			rowsetMetaData.setPrecision(i, rsMeta.getPrecision(i));
			rowsetMetaData.setScale(i, rsMeta.getScale(i));
			recordLength += rsMeta.getPrecision(i);
		}
		rowset.setMetaData(rowsetMetaData);

		// Assume all records complete
		if (query != null)
		{
			query.setRecordCount(0);
			query.setRecordLength(recordLength);
			query.setComplete(true);
		}

		// And now copy the actual data
		int recordCount = 0;
		long limit = 0;
		while (rs.next())
		{
			if (limitBytes != 0 && limit > limitBytes)
			{
				if (query != null)
				{
					query.setComplete(false);
				}
				break;
			}
			limit += recordLength;

			rowset.moveToInsertRow();
			for (int i = 1; i < rsMeta.getColumnCount() + 1; i++)
			{
				rowset.updateObject(i, rs.getObject(i));
			}
			rowset.insertRow();
			recordCount++;
		}
		rowset.moveToCurrentRow();
		rowset.beforeFirst();

		// set the record count
		if (query != null)
		{
			query.setRecordCount(recordCount);
		}

		return rowset;
	}

	public static String[] dissectSQLStatement(final String sql)
	{
		String[] sqlParts = new String[4];
		// Start by finding the SELECT part of the query (this should be between SELECT and FROM)
		int start = sql.indexOf("SELECT");
		int end = sql.indexOf("FROM");
		sqlParts[0] = sql.substring(start, end);

		// Find the FROM part of the query (this should be between FROM AND WHERE|ORDER BY|End of SQL)
		start = sql.indexOf("FROM");
		end = sql.indexOf("WHERE");
		if (end == -1)
		{
			end = sql.indexOf("ORDER BY");
			if (end == -1)
			{
				end = sql.length();
			}
		}
		if (start != -1 && end != -1)
		{
			sqlParts[1] = sql.substring(start, end);
		}

		// Find the WHERE part of the query (this should be between WHERE AND ORDER BY|End of Query)
		start = sql.indexOf("WHERE");
		end = sql.indexOf("ORDER BY");
		if (end == -1)
		{
			end = sql.length();
		}

		if (start != -1 && end != -1)
		{
			sqlParts[2] = sql.substring(start, end);
		}

		// Find the ORDER BY part of the query (this should be between FROM AND WHERE|ORDER BY|End of SQL)
		start = sql.indexOf("ORDER BY");
		end = sql.length();
		if (start != -1 && end != -1)
		{
			sqlParts[3] = sql.substring(start, end);
		}
		return sqlParts;
	}

	/**
	 * This method will close the resultSet
	 * 
	 * @param resultSet
	 *            this is the resultSet to be closed.
	 */
	public static void close(ResultSet resultSet)
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
	 * This method will close the callableStatement
	 * 
	 * @param callableStatement
	 *            this is the {@link CallableStatement} to be closed.
	 */
	public static void close(CallableStatement callableStatement)
	{
		try
		{
			if (callableStatement != null)
			{
				callableStatement.close();
			}
		}
		catch (SQLException exception)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(exception);
			}
		}
	}

	/**
	 * This method will close the connection
	 * 
	 * @param connection
	 *            this is the {@link Connection} to be closed.
	 */
	public static void close(Connection connection)
	{
		try
		{
			if (connection != null)
			{
				connection.close();
			}
		}
		catch (SQLException exception)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(exception);
			}
		}
	}

	/**
	 * This method will close the PreparedStatement.
	 * 
	 * @param preparedStatement
	 *            this is the {@link PreparedStatement} to be closed.
	 */
	public static void close(PreparedStatement preparedStatement)
	{
		try
		{
			if (preparedStatement != null)
			{
				preparedStatement.close();
			}
		}
		catch (SQLException exception)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(exception);
			}
		}
	}

	/**
	 * This method will close the Statement.
	 * 
	 * @param statement
	 *            this is the {@link Statement} to be closed.
	 */
	public static void close(Statement statement)
	{
		try
		{
			if (statement != null)
			{
				statement.close();
			}
		}
		catch (SQLException exception)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(exception);
			}
		}
	}

	/**
	 * Returns a dummy {@link ResultSet} instance that has no data and does not support any update methods.
	 * 
	 * @return A {@link ResultSet} instance that has no data ({@link ResultSet#next()} always return false).
	 */
	public static ResultSet newEmptyResultSet()
	{
		return (ResultSet) Proxy.newProxyInstance(SQLToolbox.class.getClassLoader(), new Class<?>[] { ResultSet.class },
						EMPTY_RESULTSET_HANDLER);
	}

	/**
	 * An InvocationHandler implementation to be used for the {@link ResultSet} interface for supporting an empty result set.
	 */
	private static final InvocationHandler EMPTY_RESULTSET_HANDLER = new InvocationHandler()
	{
		/** A fixed set of return values that represent what you would expect an empty result set to return */
		final Map<String, Object> methodReturnValues = new HashMap<String, Object>();

		// initialisation block plugs in fixed return values expected of an empty resultset:
		{
			// no rows, so there is never any 'next' record and neither can the cursor ever be before the first record
			methodReturnValues.put("next", Boolean.FALSE);
			methodReturnValues.put("isBeforeFirst", Boolean.FALSE);
			methodReturnValues.put("isFirst", Boolean.FALSE);
			methodReturnValues.put("isLast", Boolean.FALSE);
			methodReturnValues.put("first", Boolean.FALSE);
			methodReturnValues.put("last", Boolean.FALSE);

			// no rows, so the cursor is always 'after' the last
			methodReturnValues.put("isAfterLast", Boolean.TRUE);

			// close(), beforeFirst(), and afterLast() are 'void' methods and don't do anything for empty ResultSets!
			methodReturnValues.put("close", null);
			methodReturnValues.put("beforeFirst", null);
			methodReturnValues.put("afterLast", null);

			// no rows, so always '0'
			methodReturnValues.put("getRow", Integer.valueOf(0));

			// include "toString()" since it's used by the debugger
			methodReturnValues.put("toString", "EmptyResultSet");
		}

		/**
		 * Supports only the minimum methods in the {@link ResultSet} to simulate an 'empty' result set. This method will throw an
		 * {@link UnsupportedOperationException} for any methods not directly related to returning an empty result set (such as
		 * update methods).
		 * 
		 * {@inheritDoc}
		 */
		public Object invoke(Object proxy, Method method, Object[] args) throws UnsupportedOperationException
		{
			// determine method's name so we can respond appropriately
			final String name = method.getName();
			if ("equals".equals(name) && args.length == 1)
			{
				// use object identity for 'equals()' on the proxy object
				return proxy == args[0];
			}
			else if ("hashCode".equals(name))
			{
				// use object identity for 'hashCode()' on the proxy object
				return proxy.hashCode();
			}
			else if (methodReturnValues.containsKey(name))
			{
				// return fixed responses (these include 'null' responses since HashMap supports null values)
				return methodReturnValues.get(name);
			}

			// all other method are not supported!
			throw new UnsupportedOperationException("Method '" + name + "' Not supported by EmptyResultSet");
		}
	};

	/**
	 * Validate the fields for PV
	 * 
	 * @param md
	 *            - the result set
	 * 
	 * @return the field number of the invalid field type
	 */
	public static int validatePVSQLDataTypes(ResultSetMetaData md) throws SQLException
	{
		for (int i = 1; i < md.getColumnCount() + 1; i++)
		{
			switch (md.getColumnType(i))
			{
				case Types.CHAR:
				case Types.VARCHAR:
				case Types.BINARY:
				case Types.VARBINARY:
				case Types.DECIMAL:
				case Types.NUMERIC:
				case Types.DATE:
				case Types.TIME:
				case Types.TIMESTAMP:
					// OK
					break;

				default:
					return i;
			}
		}

		// all fields valid
		return -1;
	}

	/**
	 * Determines if a table contains a column which will not be supported by the runtime.
	 * <p>
	 * The results are cached.
	 * <p>
	 * 
	 * @param table
	 *            Name of the table or view
	 * 
	 * @return String containing the error message, or null if OK
	 */
	public static String checkForSupportedPVSQLTypes(String sqlStatement, EquationStandardSession session) throws SQLException
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		String result = "";
		try
		{
			connection = session.getConnection();
			statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sqlStatement);
			ResultSetMetaData md = rs.getMetaData();
			int i = validatePVSQLDataTypes(md);
			if (i >= 0)
			{
				result = LanguageResources.getFormattedString("Language.UnsupportedColumnType",
								new String[] {
												md.getColumnName(i),
												Integer.toString(md.getColumnType(i)) + " ("
																+ SQLToolbox.getSQLTypeName(md.getColumnType(i)) + ")" });
			}
		}
		finally
		{
			SQLToolbox.close(rs);
			SQLToolbox.close(statement);
		}
		return result;
	}

	/**
	 * Construct the Select SQL statement given the from clause and the where clause
	 * 
	 * @param fromClause
	 *            - the from clause
	 * @param whereClause
	 *            - the where clause
	 * 
	 * @return a valid Select SQL statement
	 */
	public static String constructSQLFromWhere(String fromClause, String whereClause)
	{
		StringBuilder buffer = new StringBuilder();
		buffer.append("select *");
		buffer.append(FROM);
		buffer.append(fromClause);

		// where clause?
		if (whereClause.trim().length() > 0)
		{
			buffer.append(WHERE + whereClause);
		}

		// return
		return buffer.toString();
	}

	/**
	 * Validate SQL
	 * 
	 * @param fromPart
	 *            - the from part
	 * @param wherePart
	 *            - the where part
	 * 
	 * @return the error message
	 */
	public static String validateSQLForPV(String fromPart, String wherePart, EquationStandardSession session)
	{
		// construct SQL
		String sql = SQLToolbox.constructSQLFromWhere(fromPart, wherePart);

		// Check for invalid data type in the file
		String errorMessage = "";
		try
		{
			errorMessage = SQLToolbox.checkForSupportedPVSQLTypes(sql, session);
		}
		catch (SQLException exception)
		{
			LOG.error(exception);
			errorMessage = Toolbox.getExceptionMessage(exception);
		}

		return errorMessage;
	}
}