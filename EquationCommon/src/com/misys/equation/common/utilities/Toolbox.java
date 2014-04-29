package com.misys.equation.common.utilities;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;

import javax.management.ObjectName;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400BidiTransform;
import com.ibm.as400.access.AS400PackedDecimal;
import com.ibm.as400.access.AS400Text;
import com.ibm.as400.access.AS400ZonedDecimal;
import com.ibm.as400.access.BidiStringType;
import com.ibm.as400.access.SpooledFile;
import com.ibm.websphere.management.AdminClient;
import com.ibm.websphere.management.AdminClientFactory;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.EquationSystem;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.datastructure.EqDS_DSSYSE;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQField;
import com.misys.equation.common.internal.eapi.core.EQFieldMessage;
import com.misys.equation.common.internal.eapi.core.EQList;
import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.internal.eapi.core.EQObject;
import com.misys.equation.common.language.LanguageResources;

/**
 * This is a utility class of useful methods for manipulation of data.
 */
public class Toolbox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: Toolbox.java 17623 2013-11-27 02:51:40Z williae1 $";

	/** Hex characters */
	private final static char hexArr[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	public final static String TEXT_DELIMITER = " - ";
	/** Bar delimiter */
	public final static String SEPARATION_DELIMITER = "|";
	public final static String COLON_DELIMITER = " : ";
	public final static String DASH_DELIMITER = " - ";

	/** Format of the timestamp written to the GAXIID field */
	public static final String TIMESTAMP_FORMAT = "yyyy.MM.dd HH:mm:ss S";

	/** Number of milliseconds */
	public static final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
	public static final long MILLSECS_PER_HOUR = 60 * 60 * 1000;

	/** Token expiration in milliseconds - 50 minutes */
	public static final long TOKEN_EXPIRATION_TIME = 50 * 60 * 1000;

	/** String containing "Y" for database Y/N fields */
	public static final String DB_YES = "Y";

	/** Default CCSID */
	public static final int DEF_CCSID = 37;

	/** Context delimeter */
	public final static String CONTEXT_DELIMETER = ":";

	/** New line */
	public final static String NEW_LINE = "\n";

	/** Line break */
	public final static String LINE_BREAK = "\r\n";

	/** Line break <br> */
	public final static String LINE_BREAK_BR = "<br>";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(Toolbox.class);

	/**
	 * Pad a string at the end with the specified string
	 * 
	 * @param inputString
	 * @param value
	 * @param length
	 * @return a string padded with the specified string
	 * @equation.external
	 */
	public static String pad(String inputString, String value, int length)
	{
		StringBuilder outputBuffer;
		if (inputString == null)
		{
			outputBuffer = new StringBuilder();
		}
		else
		{
			// already exceed the length?
			if (inputString.length() >= length)
			{
				return inputString;
			}

			outputBuffer = new StringBuilder(inputString);
		}
		while (outputBuffer.length() < length)
		{
			outputBuffer.append(value);
		}
		return outputBuffer.toString();
	}
	/**
	 * Left pad a string with the specified string
	 * 
	 * @param inputString
	 * @param value
	 * @param length
	 * @return a string padded with the specified string
	 */
	public static String leftPad(String inputString, String value, int length)
	{
		StringBuilder outputBuffer;
		if (inputString == null)
		{
			outputBuffer = new StringBuilder();
		}
		else
		{
			outputBuffer = new StringBuilder(inputString);
		}
		while (outputBuffer.length() < length)
		{
			outputBuffer.insert(0, value);
		}
		return outputBuffer.toString();
	}
	/**
	 * Pad a string at the front with the specified string
	 * 
	 * @param inputString
	 * @param value
	 * @param length
	 * @return a string padded with the specified string
	 * @equation.external
	 */
	public static String padAtFront(String inputString, String value, int length)
	{
		int inputStringLen = inputString.length();
		int padLength = length - inputStringLen;
		StringBuilder outputBuffer = new StringBuilder("");
		while (outputBuffer.length() < padLength)
		{
			outputBuffer.append(value);
		}
		return outputBuffer.toString() + inputString.trim();
	}
	/**
	 * This method will replace unicode-character-return for equation-character-return marks.
	 * 
	 * @param value
	 *            this the value to be evaluated.
	 * @return this is new <code>String</code>
	 */
	public static String convertUnicodeCharacterReturnToEqMark(String value)
	{
		value = value.replaceAll("\r\n", EqDataType.CHARACTER_RETURN);
		return value;
	}

	/**
	 * This method will replace equation-character-return marks for unicode-character-return .
	 * 
	 * @param value
	 *            this the value to be evaluated.
	 * @return this is new <code>String</code>
	 */
	public static String convertEqMarkToUnicodeCharacterReturn(String value)
	{
		value = value.replaceAll(EqDataType.CHARACTER_RETURN, "\r\n");
		return value;
	}

	/**
	 * This method will replace equation-character-return marks for HTML BR.
	 * 
	 * @param value
	 *            this the value to be evaluated.
	 * @return this is new <code>String</code>
	 */
	public static String convertEqMarkToHtmlBR(String value)
	{
		value = value.replaceAll(EqDataType.CHARACTER_RETURN, "<br>");
		return value;
	}

	/**
	 * Pad a string with spaces
	 * 
	 */
	public static String pad(String inputString, int length)
	{
		return pad(inputString, " ", length);
	}

	public static void printEQObjectKeyFieldErrors(EQObject eqobject)
	{
		Iterator<EQField> iterFields = eqobject.getFields().values().iterator();
		while (iterFields.hasNext())
		{
			EQField eqobjectField = iterFields.next();
			// Only interested in error messages for key fields
			if (eqobjectField.getDefinition().isKeyField() && !(eqobjectField.getMessages() == null))
			{
				LOG.debug(eqobjectField.getMessages().toString());
			}
		}
	}

	/**
	 * Returns the text representation of an integer, left padded with '0' characters.
	 * 
	 * Note that this method is not intended to cope with negative numbers.
	 * 
	 * For example, supplying a value of 6, and a length of 2 would return "06".
	 * 
	 * @param value
	 *            the input value
	 * @param length
	 *            the required maximum length
	 * @return a formatted String
	 * @equation.external
	 */
	public static String leftZeroPad(int value, int length)
	{
		StringBuilder result = new StringBuilder();
		String initialString = String.valueOf(value);
		int remaining = length - initialString.length();
		while (remaining-- > 0)
		{
			result.append('0');
		}
		result.append(initialString);
		return result.toString();
	}

	public static void printEQObject(EQObject eqobject)
	{
		// Loop through the fields and print their values
		Iterator<EQField> iterFields = eqobject.getFields().values().iterator();
		while (iterFields.hasNext())
		{
			EQField eqobjectField = iterFields.next();
			// Write out the fields values
			if (!eqobjectField.getValue().trim().equals(new String(new byte[] { 0x7F })))
			{
				LOG.debug(eqobjectField.getDefinition().getFieldName() + ":" + eqobjectField.getValue());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void printEQList(EQList list)
	{
		for (int i = 0; i < list.getRows().size(); i++)
		{
			LOG.debug("Printing row: " + i);
			HashMap<String, EQField> eqFields = list.getRowFields(i);
			Set<String> keys = eqFields.keySet();
			Iterator<String> keyIterator = keys.iterator();
			while (keyIterator.hasNext())
			{
				EQField eqField = eqFields.get(keyIterator.next());
				if (!eqField.getValue().trim().equals(new String(new byte[] { 0x7F })))
				{
					LOG.debug(eqField.getDefinition().getFieldName() + ":" + eqField.getValue());
				}
			}
		}
	}

	public static void printMessages(List<EQMessage> messages)
	{
		for (EQMessage message : messages)
		{
			LOG.debug("Message        : " + message.getFormattedMessage());
			LOG.debug("Message id     : " + message.getMessageID());
		}
	}

	public static void printFieldMessages(List<EQMessage> messages)
	{
		for (EQMessage fieldMessage : messages)
		{
			EQFieldMessage message = (EQFieldMessage) fieldMessage;
			LOG.debug("Message        : " + message.getFormattedMessage());
			LOG.debug("Message id     : " + message.getMessageID());
			LOG.debug("Field in error : " + message.getFieldName());
			LOG.debug("Severity	   : " + message.getSeverity());
		}
	}

	public static void log(String event)
	{
		LOG.debug(System.currentTimeMillis() + " : " + event);
	}

	public static String removeSQLChars(String input)
	{
		return input.replace('\'', ' ').replace('%', ' ').replace(';', ' ').replace('"', ' ');
	}

	/**
	 * Convert a string into number. If not a number, then default to the specified number
	 * 
	 * @param str
	 *            - string to convert
	 * @param def
	 *            - default number
	 * 
	 * @return converted number
	 * @equation.external
	 */
	public static int parseInt(String str, int def)
	{
		int number;
		try
		{
			number = Integer.parseInt(str);
			return number;
		}
		catch (NumberFormatException e)
		{
			return def;
		}
	}

	/**
	 * Convert a string into number. If not a number, then default to the specified number
	 * 
	 * @param str
	 *            - string to convert
	 * @param def
	 *            - default number
	 * 
	 * @return converted number
	 * @equation.external
	 */
	public static double parseDouble(String str, double def)
	{
		double number;
		try
		{
			number = Double.parseDouble(str);
			return number;
		}
		catch (NumberFormatException e)
		{
			return def;
		}
	}

	/**
	 * Convert a string into number. If not a number, then default to the specified number
	 * 
	 * @param str
	 *            - string to convert
	 * @param def
	 *            - default number
	 * 
	 * @return converted number
	 * @equation.external
	 */
	public static long parseLong(String str, long def)
	{
		long number;
		try
		{
			number = Long.parseLong(str);
			return number;
		}
		catch (NumberFormatException e)
		{
			return def;
		}
	}

	/**
	 * Convert a string into number. If not a number, then default to the specified number
	 * 
	 * @param str
	 *            - string to convert
	 * @param def
	 *            - default number
	 * 
	 * @return converted number
	 * @equation.external
	 */
	public static BigDecimal parseBigDecimal(String str, BigDecimal def)
	{
		BigDecimal number;
		try
		{
			number = new BigDecimal(str);
			return number;
		}
		catch (NumberFormatException e)
		{
			return def;
		}
	}

	/**
	 * Convert a yyymmdd into Gregorian Calendar date format
	 * 
	 * @param str
	 *            - date in yyymmdd format
	 * 
	 * @return converted number
	 * @equation.external
	 */
	public static GregorianCalendar parseEqDate(String str)
	{
		// invalid date
		if (str.length() < 6 || str.length() > 8)
		{
			return new GregorianCalendar(0, 0, 0);
		}

		// 6 digits, then add one to make it 7
		if (str.length() == 6)
		{
			str = "0" + str;
		}

		int year = Toolbox.parseInt(str.substring(0, 3), 0);
		int month = Toolbox.parseInt(str.substring(3, 5), 0);
		int dayOfMonth = Toolbox.parseInt(str.substring(5), 0);
		GregorianCalendar gc = new GregorianCalendar(1900 + year, month - 1, dayOfMonth);

		return gc;
	}

	public static String formatCYYMMDDDate(String cyymmddDate)
	{
		if (cyymmddDate.length() < 7)
		{
			cyymmddDate = "0" + cyymmddDate;
		}
		StringBuilder builder = new StringBuilder(cyymmddDate.substring(5, 7));
		return builder.append("/").append(cyymmddDate.substring(3, 5)).append("/").append(cyymmddDate.substring(1, 3)).toString();
	}

	/**
	 * Retrieve the substring with a text. If it exceeded the end position, then adjust to the end of the string
	 * 
	 * @param str
	 *            - string
	 * @param startIndex
	 *            - starting position
	 * @param endIndex
	 *            - end position
	 * 
	 * @return substring
	 */
	public static String subString(String str, int startIndex, int endIndex)
	{
		try
		{
			return (str.substring(startIndex, endIndex));
		}
		catch (IndexOutOfBoundsException e)
		{
			int nIndex = str.length();
			return (str.substring(startIndex, nIndex));
		}
	}

	/**
	 * Return the element from a list given the index. Return null if index is not valid
	 * 
	 * @param list
	 *            - list
	 * @param index
	 *            - index to the list
	 * 
	 * @return element at the specified index
	 * @equation.external
	 */
	public static Object getElement(List<?> list, int index)
	{
		// no list?
		if (list == null)
		{
			return null;
		}
		// get the size of the list
		int size = list.size();
		// index is more than the size?
		if (index >= size)
		{
			return null;
		}
		// return the element
		return list.get(index);
	}

	/**
	 * Return the String element from a list given the index. Return empty string if invalid
	 * 
	 * @param list
	 *            - list
	 * @param index
	 *            - index to the list
	 * 
	 * @return the string element at the specified index
	 * @equation.external
	 */
	public static String getStringElement(List<?> list, int index)
	{
		String obj = (String) getElement(list, index);
		if (obj == null)
		{
			return "";
		}
		else
		{
			return obj;
		}
	}

	/**
	 * Return the integer element from a list given the index. Return the default value if invalid
	 * 
	 * @param list
	 *            - list
	 * @param index
	 *            - index to the list
	 * @param defValue
	 *            - default integer
	 * 
	 * @return the integer element at the specified index
	 * @equation.external
	 */
	public static int getIntElement(List<?> list, int index, int defValue)
	{
		Object obj = getElement(list, index);
		if (obj == null)
		{
			return defValue;
		}
		else
		{
			return Toolbox.parseInt(((String) obj), defValue);
		}
	}

	/**
	 * Parse a huge string and return it in the form of an array.
	 * 
	 * @param hugeText
	 *            - the text
	 * @param len
	 *            - fix-length width
	 * 
	 * @return the equivalent text in array form delimited by the fix length
	 * @equation.external
	 */
	public static List<String> loadString(String hugeText, int len)
	{
		List<String> list = new ArrayList<String>();
		String text;
		int startIndex = 0;
		int endIndex = len;
		int hugeTextLen = hugeText.length();

		// retrieve the first text
		text = Toolbox.subString(hugeText, startIndex, endIndex);
		while (!text.equals(""))
		{
			list.add(text.trim());
			startIndex += len;
			endIndex += len;

			// exit loop when the start position exceeded the length of the huge text
			if (startIndex > hugeTextLen)
			{
				break;
			}

			// get next text
			text = Toolbox.subString(hugeText, startIndex, endIndex);
		}
		// return the list
		return list;
	}

	/**
	 * Parse a huge string and return it in the form of an array. Divide string using delimiter.
	 * 
	 * @param hugeText
	 *            - the text
	 * 
	 * @param delimiter
	 *            - delimiter character
	 * 
	 * @return the equivalent text in array form delimited by the fix length
	 * @equation.external
	 */
	public static List<String> loadString(String hugeText, char delimiter)
	{
		List<String> list = new ArrayList<String>();
		String text;
		int hugeTextLen = hugeText.length();
		int startIndex = 0;
		int endIndex = hugeText.indexOf(delimiter, startIndex);
		if (endIndex == -1)
		{
			endIndex = hugeTextLen;
		}
		// retrieve the first text
		text = Toolbox.subString(hugeText, startIndex, endIndex);
		while (!text.equals(""))
		{
			list.add(text.trim());
			startIndex = endIndex + 1;
			endIndex = hugeText.indexOf(delimiter, startIndex);
			if (endIndex == -1)
			{
				endIndex = hugeTextLen;
			}
			// exit loop when the start position exceeded the length of the huge text
			if (startIndex > hugeTextLen)
			{
				break;
			}
			// get next text
			text = Toolbox.subString(hugeText, startIndex, endIndex);
		}
		// return the list
		return list;
	}

	/**
	 * Parse a huge string and return it in the form of a List of Strings. Divide string using delimiter.
	 * 
	 * @param hugeText
	 *            - the text
	 * @param delimiter
	 *            - delimiter string
	 * 
	 * @return the equivalent text in array form delimited by the fix length
	 * @equation.external
	 */
	public static List<String> loadString(String hugeText, String delimiter)
	{
		List<String> list = new ArrayList<String>();
		String text;
		int hugeTextLen = hugeText.length();
		int delimiterLen = delimiter.length();
		int startIndex = 0;
		int endIndex = hugeText.indexOf(delimiter, startIndex);
		if (endIndex == -1)
		{
			endIndex = hugeTextLen;
		}
		// retrieve the first text
		text = Toolbox.subString(hugeText, startIndex, endIndex);
		while (!text.equals(""))
		{
			list.add(text.trim());
			startIndex = endIndex + 1;
			endIndex = hugeText.indexOf(delimiter, startIndex);
			if (endIndex == -1)
			{
				endIndex = hugeTextLen;
			}
			// exit loop when the start position exceeded the length of the huge text
			if (startIndex > hugeTextLen)
			{
				break;
			}
			// get next text
			text = Toolbox.subString(hugeText, startIndex + delimiterLen - 1, endIndex);
		}
		// return the list
		return list;
	}

	/**
	 * Parse a huge string up to the maximum length and return it in the form of an array
	 * 
	 * @param hugeText
	 *            - the text
	 * @param len
	 *            - fix-length width
	 * @param minLen
	 *            - minimum length to pad the hugeText string to
	 * 
	 * @return the equivalent text in array form delimited by the fix length
	 * @equation.external
	 */
	public static List<String> loadString(String hugeText, int len, int minLen)
	{
		if (hugeText.length() < minLen)
		{
			hugeText = Toolbox.pad(hugeText, minLen);
		}
		List<String> list = loadString(hugeText, len);
		return list;
	}

	/**
	 * Return the string representation of an array
	 * 
	 * @param array
	 *            - array
	 * @param delimeter
	 *            - the string is enclosed in this delimiter
	 * 
	 * @return the string representation of an array
	 * @equation.external
	 */
	public static String arrayToString(List<?> array, String delimeter)
	{
		StringBuilder str = new StringBuilder("[");
		for (int i = 0; i < array.size(); i++)
		{
			if (i != 0)
			{
				str.append(",");
			}

			str.append(delimeter + array.get(i) + delimeter);
		}
		str.append("]");
		return str.toString();
	}

	/**
	 * Print the content of a hash table
	 * 
	 * @param table
	 *            - a hashtable
	 */
	public static void printHashTable(Hashtable<?, ?> table)
	{
		LOG.debug(table.toString());
	}

	/**
	 * Print the content of a hash table
	 * 
	 * @param table
	 *            - a hashtable
	 */
	public static String cvtHashTableToString(Hashtable<?, ?> table)
	{
		StringBuffer buffer = new StringBuffer();
		Enumeration<?> keys = table.keys();
		while (keys.hasMoreElements())
		{
			Object key = keys.nextElement();
			buffer.append(key + " = " + table.get(key));
			buffer.append("\n");
		}
		return buffer.toString();
	}

	/**
	 * Print an enumeration
	 * 
	 * @param e
	 *            - an enumeration
	 */
	public static String printEnumeration(Enumeration<?> e)
	{
		StringBuffer buffer = new StringBuffer();
		while (e.hasMoreElements())
		{
			buffer.append(e.nextElement().toString());
			buffer.append("\n");
			LOG.debug(e.nextElement().toString());
		}
		return buffer.toString();
	}

	/**
	 * Print the content of a List
	 * 
	 * @param array
	 *            - a List
	 */
	public static String printList(List<String> array)
	{
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < array.size(); i++)
		{
			buffer.append(array.get(i));
			buffer.append("\n");
			LOG.debug(array.get(i));
		}
		return buffer.toString();
	}

	/**
	 * Print the content of a String array
	 * 
	 * @param array
	 *            The string array to print
	 */
	public static String printArray(String[] array)
	{
		StringBuffer buffer = new StringBuffer();
		for (String element : array)
		{
			buffer.append(element);
			buffer.append("\n");
			LOG.debug(element);
		}
		return buffer.toString();
	}

	/**
	 * Replace a string with the specified string from a hashtable
	 * 
	 * @param str
	 *            - string
	 * @param mapping
	 *            - replacement keyword and replacement string
	 * 
	 */
	public static String replaceWithMapping(StringBuffer str, Hashtable<String, String> mapping)
	{
		String key = "";
		int idx = 0;

		// loop through the mapping keys
		Enumeration<String> keys = mapping.keys();
		while (keys.hasMoreElements())
		{
			key = keys.nextElement();

			// check if this key exists in the string
			while ((idx = str.indexOf(key)) >= 0)
			{
				str.replace(idx, idx + key.length(), mapping.get(key));
			}
		}

		// return the string
		return str.toString();
	}

	/**
	 * Return the date in cyymmdd format
	 * 
	 * @param cal
	 *            calendar
	 * @return the date in cyymmdd format
	 * @equation.external
	 */
	public static int getDateDBFormat(Calendar cal)
	{
		return cal.get(Calendar.YEAR) * 10000 + (cal.get(Calendar.MONTH) * 100 + 100) + cal.get(Calendar.DAY_OF_MONTH) - 19000000;
	}

	/**
	 * Return the date in cyymmdd format
	 * 
	 * @param date
	 *            date
	 * @return the date in cyymmdd format
	 * @equation.external
	 */
	public static int getDateDBFormat(Date date)
	{
		GregorianCalendar cal = new GregorianCalendar(date.getYear(), date.getMonth(), date.getDay());
		return cal.get(Calendar.YEAR) * 10000 + (cal.get(Calendar.MONTH) * 100 + 100) + cal.get(Calendar.DAY_OF_MONTH) - 19000000;
	}

	/**
	 * Return the date in cyymmdd format
	 * 
	 * @param date
	 *            date
	 * @return the date in cyymmdd format
	 * @equation.external
	 */
	public static String getDateDBStringFormat(Calendar cal)
	{
		return cvtDbDateToStr(getDateDBFormat(cal));
	}

	public static String convertCalendarDay(Calendar calendar)
	{
		StringBuilder currentDate = new StringBuilder();
		currentDate.append(calendar.get(Calendar.DATE));
		currentDate.append("-");
		currentDate.append((calendar.get(Calendar.MONTH) + 1));
		currentDate.append("-");
		currentDate.append(calendar.get(Calendar.YEAR));

		return currentDate.toString();
	}

	/**
	 * Return the time in hhmmss format
	 * 
	 * @param cal
	 *            - calendar
	 * 
	 * @return the hhmmss format
	 * @equation.external
	 */
	public static int getTimeDBFormat(Calendar cal)
	{
		return cal.get(Calendar.HOUR_OF_DAY) * 10000 + cal.get(Calendar.MINUTE) * 100 + cal.get(Calendar.SECOND);
	}

	/**
	 * Return the time in hhmmss string format
	 * 
	 * @param cal
	 *            - calendar
	 * 
	 * @return the hhmmss format
	 * @equation.external
	 */
	public static String getTimeDBStrFormat(Calendar cal)
	{
		String hhmmss = String.valueOf(getTimeDBFormat(cal));
		if (hhmmss.length() < 6)
		{
			hhmmss = "0" + hhmmss;
		}
		return hhmmss;
	}

	/**
	 * Return the time in hhmmss + millisecond format
	 * 
	 * @param cal
	 *            calendar
	 * @equation.external
	 */
	public static String getTimeFormat(Calendar cal)
	{
		return getTimeDBStrFormat(cal) + cal.get(Calendar.MILLISECOND);
	}

	/**
	 * Return the date in cyymmdd format into string
	 * 
	 * @param date
	 *            - date in cyymmdd format
	 */
	public static String cvtDbDateToStr(int date)
	{
		String str = String.valueOf(date);
		if (str.length() == 6)
		{
			str = "0" + str;
		}
		else if (str.length() < 6)
		{
			str = "0000000";
		}
		return str;
	}
	/**
	 * Convert date in cyymmdd format into Timestamp
	 * 
	 * @param date
	 *            - date in cyymmdd format
	 * @equation.external
	 */
	public static Timestamp convertDbDateToTimestamp(int date)
	{
		// Convert unit business date into timestamp
		Calendar calendar = Toolbox.parseEqDate(new Integer(date).toString());
		Date parseDate = calendar.getTime();
		return new Timestamp(parseDate.getTime());
	}

	/**
	 * Return the first word
	 * 
	 * @param str
	 *            string
	 * @equation.external
	 */
	public static String getFirstWord(String str)
	{
		int index = str.indexOf(" ");

		// no space
		if (index == -1)
		{
			return str;
		}

		// return the first word
		return (str.substring(0, index));
	}
	/**
	 * Return the substring of the supplied string that contains the characters from the beginning to before the first underscore.
	 * Used for determining input field ID from a method name. For example A_AB_ = A_AB.
	 * 
	 * @param str
	 *            string
	 */
	public static String getStringBeforeFirstUnderscore(String str)
	{
		int index = str.indexOf("_");
		if (index == -1)
		{
			return null;
		}
		// return the first word
		return (str.substring(0, index));
	}
	/**
	 * Return the substring of the supplied string that contains the characters from the beginning to before the second underscore.
	 * Used for determining input field ID from a method name. For example A_AB_ = A_AB.
	 * 
	 * @param str
	 *            string
	 */
	public static String getStringBeforeSecondUnderscore(String str)
	{
		int index = str.indexOf("_");
		if (index == -1)
		{
			return null;
		}
		// Second _
		index = str.indexOf("_", index + 1);
		// no space
		if (index == -1)
		{
			return null;
		}
		// return the first word
		return (str.substring(0, index));
	}
	/**
	 * Return the substring of the supplied string that starts after the first underscore and ends before the second underscore.
	 * Used for determining API field ID from a method name. For example A_AB_ = AB.
	 * 
	 * @param str
	 *            string
	 */
	public static String getStingAfterFirstUnderscoreBeforeSecondUnderscore(String str)
	{
		int index = str.indexOf("_");
		if (index == -1)
		{
			return null;
		}
		// return the first word
		return (str.substring(index + 1));
	}
	/**
	 * Return the last string after the last '.'
	 * 
	 * @param str
	 *            - string
	 */
	public static String getLastStringAfterDot(String str)
	{
		int index = str.lastIndexOf(".");

		// no dot
		if (index == -1)
		{
			return str;
		}

		// return the first word
		return (str.substring(index + 1, str.length()));
	}

	/**
	 * Right justify a string
	 * 
	 * @param str
	 *            - the string to right justify
	 * @param shiftChar
	 *            - the character to be added
	 * @param length
	 *            - the maximum length
	 * 
	 * @return the right justified string with the specified length
	 * @equation.external
	 */
	public static String shiftRight(String str, char shiftChar, int length)
	{
		StringBuffer buffer = new StringBuffer();
		int strlength = str.length();
		if (strlength >= length)
		{
			buffer.append(str.subSequence(0, length));
		}
		else
		{
			buffer.append(str);
			while (strlength < length)
			{
				buffer.insert(0, shiftChar);
				strlength++;
			}
		}
		return buffer.toString();
	}

	/**
	 * Print a string in hex format
	 * 
	 * @param str
	 *            - the string to be printed
	 */
	public static void printBytes(String str)
	{
		printBytes(str.getBytes());
	}

	/**
	 * Print byte array in hex format
	 * 
	 * @param byteData
	 *            - the byte array to be printed
	 */
	public static void printBytes(byte[] byteData)
	{
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < byteData.length; i++)
		{
			if (i != 0)
			{
				buffer.append(", ");
			}
			buffer.append(String.format("%x", byteData[i]));
		}
		LOG.error(buffer.toString());
	}

	/**
	 * Convert a byte array into its equivalent Hexa-decimal string
	 * 
	 * @param byteData
	 *            - array of bytes to be converted
	 * 
	 * @return the string equivalent of the byte array
	 */
	public static String cvtBytesToHexString(byte[] byteData)
	{
		StringBuffer buffer = new StringBuffer();
		for (byte element : byteData)
		{
			buffer.append(cvtByteToHexString(element));
		}
		return buffer.toString();
	}

	/**
	 * Convert a byte into String HEX equivalent
	 * 
	 * @param byteData
	 *            - the byte
	 * 
	 * @return the String HEX equivalent
	 */
	public static String cvtByteToHexString(byte byteData)
	{
		// low bits
		int i1 = byteData & 0x000F;

		// high bits
		int i2 = byteData & 0x00F0;
		i2 = i2 >> 4;

		return String.valueOf(hexArr[i2]) + String.valueOf(hexArr[i1]);
	}

	/**
	 * Convert a Hexa-decimal string into a byte array
	 * 
	 * @param hexStr
	 *            - the hexa decimal string
	 * 
	 * @return the equivalent byte array
	 */
	public static byte[] cvtHexStringToBytes(String hexStr)
	{
		byte[] byteData = new byte[hexStr.length() / 2];
		int index = 0;

		// loop all the string
		for (int i = 0; i < hexStr.length(); i += 2)
		{
			char c1 = hexStr.charAt(i);
			char c2 = hexStr.charAt(i + 1);

			int i1 = (cvtCharToHex(c1) << 4) & 0x00FF;
			int i2 = cvtCharToHex(c2) & 0x00FF;
			byteData[index] = (byte) (i1 + i2);
			index++;
		}

		return byteData;
	}

	/**
	 * Convert a valid Hexa-decimal digit character into integer format
	 * 
	 * @param c
	 *            - a valid hex digit
	 * 
	 * @return the equivalent hex in integer
	 *         <p>
	 *         returns -1 if invalid
	 * 
	 */
	public static int cvtCharToHex(char c)
	{
		for (int i = 0; i < hexArr.length; i++)
		{
			if (hexArr[i] == c)
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * Return the string of the specified maximum length
	 * 
	 * @param str
	 *            - the string
	 * @param length
	 *            - the maximum length
	 * 
	 * @return the string of the specified maxixum length
	 * @equation.external
	 */
	public static String trim(String str, int length)
	{
		if (str.length() > length)
		{
			return str.substring(0, length);
		}
		else
		{
			return str;
		}
	}

	/**
	 * Return the string of the specified maximum length (trimming leading characters)
	 * 
	 * @param str
	 *            - the string
	 * @param length
	 *            - the maximum length
	 * 
	 * @return the string of the specified maxixum length
	 * @equation.external
	 */
	public static String trimAtFront(String str, int length)
	{
		if (str.length() > length)
		{
			return str.substring(str.length() - length, str.length());
		}
		else
		{
			return str;
		}
	}

	/**
	 * Return a string array for an array of objects using its toString methods
	 * 
	 * @param object
	 *            array - the array to toString
	 * 
	 * @return the array of toString for the objects in the array
	 * @equation.external
	 */
	public static String[] toString(Object[] object)
	{
		List<String> toStringsArrayList = new ArrayList<String>();
		String[] toStrings = { "" };
		for (Object element : object)
		{
			toStringsArrayList.add(element.toString());
		}
		return toStringsArrayList.toArray(toStrings);
	}

	/**
	 * Return the integer portion of a number in string
	 * 
	 * @param numberStr
	 *            - a number in string format
	 * 
	 * @return the integer portion of the number. Note: the string before the dot
	 * @equation.external
	 */
	public static String getInteger(String numberStr)
	{
		// search for a dot
		int index = numberStr.indexOf(".");

		if (index == -1)
		{
			return numberStr;
		}
		else if (index == 0)
		{
			return "0";
		}
		else
		{
			return numberStr.substring(0, index);
		}
	}

	/**
	 * Format a time using : separators
	 * 
	 * @param time
	 *            - the time in hhmmss format
	 * @param len
	 *            - either 6 (to include seconds) or 4 (to exclude seconds)
	 * 
	 * @return formatted time
	 * @equation.external
	 */
	public static String formatTime(String time, int len)
	{
		if (time.length() < 6)
		{
			time = pad("", "0", 6 - time.length()) + time;
		}

		StringBuffer buffer = new StringBuffer();
		buffer.append(time.substring(0, 2));
		buffer.append(":");
		buffer.append(time.substring(2, 4));
		if (len <= 4)
		{
			return buffer.toString();
		}
		buffer.append(":");
		buffer.append(time.substring(4, 6));
		return buffer.toString();
	}

	/**
	 * Format the given date using the given pattern
	 * 
	 * @param cal
	 *            - the date
	 * @param pattern
	 *            - the pattern
	 * @return the formatted date
	 * @equation.external
	 */
	public static String formatDate(Calendar cal, String pattern)
	{
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return (df.format(cal.getTime()));
	}

	/**
	 * Format the given date and time using the default locale
	 * 
	 * @param cal
	 *            - the date
	 * 
	 * @return the formatted date
	 * @equation.external
	 */
	public static String formatDate(Calendar cal)
	{
		DateFormat dateformat = DateFormat.getDateInstance(DateFormat.SHORT);
		return dateformat.format(cal.getTime());
	}

	/**
	 * Format the given date using the default locale
	 * 
	 * @param date
	 *            - the date in cyymmdd format
	 * @return the formatted date
	 * @equation.external
	 */
	public static String formatDate(String date)
	{
		if (date.length() == 6)
		{
			date = "0" + date;
		}
		GregorianCalendar dateInstance = new GregorianCalendar();
		dateInstance.set(1900 + Integer.parseInt(date.substring(0, 3)), Integer.parseInt(date.substring(3, 5)) - 1, Integer
						.parseInt(date.substring(5, 7)));
		return formatDate(dateInstance);
	}

	/**
	 * Right trim a string
	 * 
	 * @param str
	 *            - the string
	 * @return the (right) trimmed string
	 */
	public static String trimr(String str)
	{
		int index = 0;
		for (int i = str.length() - 1; i >= 0; i--)
		{
			if (!Character.isWhitespace(str.charAt(i)))
			{
				index = i + 1;
				break;
			}
		}

		return (str.substring(0, index));
	}

	/**
	 * Left trim a string
	 * 
	 * @param str
	 *            - the string
	 * 
	 * @return the (left) trimmed string
	 */
	public static String triml(String str)
	{
		int index = 0;
		for (int i = 0; i < str.length(); i++)
		{
			if (!Character.isWhitespace(str.charAt(i)))
			{
				index = i;
				break;
			}
		}

		return (str.substring(index));
	}
	/**
	 * Remove blank characters from a string
	 * 
	 * @param str
	 * @return a string without blank characters
	 * @equation.external
	 */
	public static String removeWhitesSpaces(String str)
	{
		StringBuffer string = new StringBuffer("");

		for (int i = 0; i < str.length(); i++)
		{
			if (Character.isWhitespace(str.charAt(i)))
			{
				continue;
			}
			else
			{
				string.append(str.charAt(i));
			}
		}

		return string.toString();
	}

	/**
	 * Validate the text in AS400 format
	 * 
	 * @param str
	 *            - text string
	 * @param length
	 *            the length of AS400 text
	 * @param ccsid
	 *            the CCSID to convert to
	 * 
	 * @return true if the text is within the valid length when converted into IBM format
	 * @equation.external
	 */
	public static boolean isValidAS400TextLength(String str, int length, int ccsid)
	{
		try
		{
			AS400Text as400Text = new AS400Text(length, ccsid);
			as400Text.toBytes(str);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * Validate the number in AS400 zoned format
	 * 
	 * @param num
	 *            the number to validate
	 * @param length
	 *            size of AS400 field
	 * @param decimal
	 *            number of decimals
	 * 
	 * @return true if the number is a valid IBM zoned decimal format
	 * @equation.external
	 */
	public static boolean isValidAS400ZonedLength(double num, int length, int decimal)
	{
		try
		{
			AS400ZonedDecimal as400Text = new AS400ZonedDecimal(length, decimal);
			as400Text.toBytes(num);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * Validate the number in AS400 packed format
	 * 
	 * @param num
	 *            the number to validate
	 * @param length
	 *            size of AS400 field
	 * @param decimal
	 *            number of decimals
	 * 
	 * @return true if the number is a valid IBM packed decimal format
	 * @equation.external
	 */
	public static boolean isValidAS400PackedLength(double num, int length, int decimal)
	{
		try
		{
			AS400PackedDecimal as400Text = new AS400PackedDecimal(length, decimal);
			as400Text.toBytes(num);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * Validate the text in AS400 format
	 * 
	 * @param str
	 *            - text string
	 * @param length
	 *            the length of AS400 text
	 * @param ccsid
	 *            the CCSID to convert to
	 * 
	 * @return true if the text can be converted into a valid text in the specified CCSID
	 * @equation.external
	 */
	public static boolean isValidAS400Text(String str, int length, int ccsid)
	{
		try
		{
			AS400Text as400Text = new AS400Text(length, ccsid);
			byte[] b = as400Text.toBytes(str);
			for (byte element : b)
			{
				if (element == 63) // 0x3F
				{
					return false;
				}
			}

			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * Truncate the given amount
	 * 
	 * @param amount
	 *            - the amount in database format
	 * @param decimal
	 *            - the number of decimal places
	 * @param trunc
	 *            - truncate the amount by this number of digits
	 * 
	 * @return the truncated amount
	 * @equation.external
	 */
	public static String truncateDbAmount(String amount, int decimal, int trunc)
	{
		int len = decimal + trunc;

		if (amount.length() <= len)
		{
			return "0";
		}

		return amount.substring(0, amount.length() - len);
	}

	/**
	 * Returns a 13 long reference based on the current time which, for example, may be used for deal reference generation
	 * 
	 * @return the timestamp in format CYYMMDDHHMMSS
	 * @equation.external
	 */
	public final static String getTimeBasedReference13()
	{
		// Create String of CYYMMDDHHMMSS

		String timeStamp;
		Calendar cal = Calendar.getInstance();
		int YYYY = cal.get(Calendar.YEAR);
		int MM = cal.get(Calendar.MONTH) + 1;
		int DD = cal.get(Calendar.DAY_OF_MONTH);
		int YYYYMMDD = (YYYY * 10000) + (MM * 100) + DD;
		if (YYYY < 2000)
		{
			timeStamp = "0" + Integer.valueOf(YYYYMMDD).toString().substring(2);
		}
		else
		{
			timeStamp = "1" + Integer.valueOf(YYYYMMDD).toString().substring(2);
		}

		timeStamp = timeStamp + Integer.valueOf(Toolbox.getTimeDBFormat(cal)).toString();
		return timeStamp;
	}
	/**
	 * Return the earliest timestamp 0001,01,01.
	 * 
	 * Null, empty string are not valid in DB timemstamp columns so this earliest timestamp can be used to indicate logically
	 * deleted records.
	 * 
	 * @return the earliest timestamp
	 * @equation.external
	 */
	public final static String getEarliestTimestamp()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(0);
		// LOVAL value for timestamp on iSeries is 0001-01-01-00.00.00.000000
		calendar.set(01, 00, 01, 00, 00, 00);
		String earlistDate = Toolbox.formatDate(calendar, Toolbox.TIMESTAMP_FORMAT);
		return earlistDate;
	}
	/**
	 * Replace the single quote with special codes for \' (to be used if the string is within a bigger string enclosed in single
	 * quotes
	 * 
	 * @param str
	 *            - string to be analysed
	 * 
	 * @return the converted string
	 * @equation.external
	 */
	public static String rplSingleQuoteWithSlashSingleQuote(String str)
	{
		str = str.replaceAll("'", "&#92;&#39;");
		return str;
	}

	/**
	 * Replace the single quote with special codes for '
	 * 
	 * @param str
	 *            - string to be analysed
	 * 
	 * @return the converted string
	 * @equation.external
	 */
	public static String rplSingleQuote(String str)
	{
		str = str.replaceAll("'", "&#39;");
		return str;
	}

	/**
	 * Replace the quote with special codes for "
	 * 
	 * @param str
	 *            - string to be analysed
	 * 
	 * @return the converted string
	 * @equation.external
	 */
	public static String rplQuote(String str)
	{
		str = str.replaceAll("\"", "&quot;");
		return str;
	}

	/**
	 * Replace the space with the non-breaking space
	 * 
	 * @param str
	 *            - string to be analysed
	 * 
	 * @return the converted string
	 * @equation.external
	 */
	public static String rplSpaces(String str)
	{
		str = str.replaceAll(" ", "&nbsp;");
		return str;
	}

	/**
	 * Replace the \ with 2 special code for \
	 * 
	 * @param str
	 *            - string to be analysed
	 * 
	 * @return the converted string
	 * @equation.external
	 */
	public static String rplSlashWith2Slash(String str)
	{
		// replace \ with \\
		str = str.replaceAll("\\\\", "&#92;&#92;");
		return str;
	}

	/**
	 * Replace the \ with special code for \
	 * 
	 * @param str
	 *            - string to be analysed
	 * 
	 * @return the converted string
	 * @equation.external
	 */
	public static String rplSlash(String str)
	{
		str = str.replaceAll("\\\\", "&#92;");
		return str;
	}

	/**
	 * Return the string representation of an array
	 * 
	 * @param array
	 *            - array
	 * @param delimiter
	 *            - the string is enclosed in this delimiter
	 * 
	 * @return the string representation of an array
	 */
	public static String arrayToStringRplForJavaScript(List<?> array, String delimiter)
	{
		StringBuilder str = new StringBuilder("[");
		for (int i = 0; i < array.size(); i++)
		{
			if (i != 0)
			{
				str.append(",");
			}

			str.append(delimiter + rplSlashWith2Slash(rplQuote(rplSingleQuoteWithSlashSingleQuote(array.get(i).toString())))
							+ delimiter);
		}
		str.append("]");
		return str.toString();
	}

	/**
	 * Parse a huge string and return it in the form of an array. Substitute constants where necessary (e.g " ":2 becomes ' ')
	 * 
	 * @param hugeText
	 *            - the text
	 * @param len
	 *            - fix-length width
	 * 
	 * @return the equivalent text in array form delimited by the fix length
	 */
	public static List<String> loadStringWithSubstituion(String hugeText, int len)
	{
		List<String> list = new ArrayList<String>();
		String text;

		int startIndex = 0;
		int endIndex = len;
		int fieldIndex = 0;
		int hugeTextLen = hugeText.length();
		// retrieve the first text
		text = Toolbox.subString(hugeText, startIndex, endIndex);

		while (!text.equals(""))
		{
			text = constantSubstitution(text);
			list.add(text.trim());
			startIndex += len;
			endIndex += len;
			// exit loop when the start position exceeded the length of the huge text
			if (startIndex > hugeTextLen)
			{
				break;
			}
			// get next text
			text = Toolbox.subString(hugeText, startIndex, endIndex);
			fieldIndex = fieldIndex + 1;
		}
		// return the list
		return list;
	}

	/**
	 * Process a string and substitute constants
	 * 
	 * @param text
	 *            - original string
	 * 
	 * @return new string with constant substitution
	 */
	public static String constantSubstitution(String text)
	{
		String newString = text;
		int posQuote1 = text.indexOf('"');
		if (posQuote1 != -1)
		{
			int posColon = text.indexOf(':');
			int posQuote2 = text.indexOf('"', posQuote1 + 1);
			String replaceValue = text.substring(posQuote1 + 1, posQuote2);
			newString = "";
			int occurance;
			if (posColon != -1)
			{
				occurance = new Integer((text.substring(posColon + 1)).trim()).intValue();
			}
			else
			{
				occurance = 1;
			}

			StringBuilder strBuilder = new StringBuilder("'");

			for (int i = 0; i < occurance; i++)
			{
				// get the key field
				strBuilder.append(replaceValue);
			}
			strBuilder.append("'");
			newString = strBuilder.toString();
		}
		return newString;
	}

	/**
	 * SQL conversion of numeric DB field to character field with zero fill (e.g SUBSTR(CAST(SAPOD + 10000000 AS CHAR(8)),2,7))
	 * 
	 * @param fieldName
	 *            field name
	 * @param fieldLength
	 *            field length
	 * 
	 * @return SQL field fragment
	 */
	public static String sqlNumberToCharFormat(String fieldName, int fieldLength)
	{
		StringBuilder addValue = new StringBuilder("1");
		for (int i = 0; i < fieldLength; i++)
		{
			addValue.append("0");
		}

		String strLength = Integer.valueOf(fieldLength).toString();
		String strLengthAddValue = Integer.valueOf(fieldLength + 1).toString();

		String sqlFormat = "SUBSTR(CAST(" + fieldName.trim() + " + " + addValue + " AS CHAR(" + strLengthAddValue + ")),2,"
						+ strLength + ") ";

		return sqlFormat;
	}
	/**
	 * SQL conversion of numeric DB field to character field with zero fill (e.g SUBSTR(CAST(SAPOD + 10000000 AS CHAR(8)),2,7))
	 * 
	 * @param fieldName
	 *            field name
	 * @param fieldLength
	 *            field length
	 * @param fieldDecimals
	 *            field decimals
	 * 
	 * @return SQL field fragment
	 */
	public static String sqlNumberToCharFormat(String fieldName, int fieldLength, int fieldDecimals)
	{
		int fieldIntegers = fieldLength - fieldDecimals;
		StringBuilder addValue = new StringBuilder("1");
		for (int i = 0; i < fieldIntegers; i++)
		{
			addValue.append("0");
		}

		int posLength = 0;
		int negLength = 0;
		int posStrLength = 0;
		int negStrLength = 0;
		if (fieldDecimals == 0)
		{
			posLength = fieldLength;
			negLength = fieldLength + 1;
			posStrLength = posLength + 1;
			negStrLength = negLength + 1;
			//
		}
		else
		{
			posLength = fieldLength + 1;
			negLength = fieldLength + 2;
			posStrLength = posLength + 1;
			negStrLength = negLength + 1;
		}

		// case
		// when gfadj < 0
		// then SUBSTR(CAST(GFADJ - 10000 AS CHAR(14)),1,1) concat(
		// SUBSTR(CAST(GFADJ - 10000 AS CHAR(14)),3,13) )
		// else
		// SUBSTR(CAST(GFADJ + 10000 AS CHAR(13)),2,12)
		// end

		String sqlFormat = "CASE WHEN " + fieldName.trim() + " > 0 THEN " + "SUBSTR(CAST(" + fieldName.trim() + " + " + addValue
						+ " AS CHAR(" + posStrLength + ")),2," + posLength + ") ELSE " + "SUBSTR(CAST(" + fieldName.trim() + " - "
						+ addValue + " AS CHAR(" + negStrLength + ")),1,1) CONCAT ( " + "SUBSTR(CAST(" + fieldName.trim() + " - "
						+ addValue + " AS CHAR(" + negStrLength + ")),3," + negLength + ")) END";

		return sqlFormat;
	}

	/**
	 * SQL conversion of DB field to uppercase with right trim.
	 * 
	 * @param fieldName
	 *            field name
	 * 
	 * @return SQL field fragment
	 */
	public static String sqlCharToUppercaseWithRTRIM(String fieldName)
	{
		String sqlFormat = "UCASE(RTRIM(" + fieldName + ")) ";
		return sqlFormat;
	}
	/**
	 * SQL conversion of DB field to right trim.
	 * 
	 * @param fieldName
	 *            field name
	 * 
	 * @return SQL field fragment
	 */
	public static String sqlCharWithRTRIM(String fieldName)
	{
		String sqlFormat = "RTRIM(" + fieldName + ") ";
		return sqlFormat;
	}
	/**
	 * Determine whether a String is not blank and not null
	 * 
	 * @param str
	 *            - the string
	 * 
	 * @return true if string is not blank and not null
	 */
	public static boolean stringNotBlank(String str)
	{
		if (str != null)
		{
			if (!str.equals(""))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Convert boolean into Y/N
	 * 
	 * @param flag
	 *            - boolean flag
	 * 
	 * @return returns Y for true, otherwise N
	 * @equation.external
	 */
	public static String cvtBooleanToYN(boolean flag)
	{
		if (flag)
		{
			return String.valueOf(EqDataType.YES);
		}
		else
		{
			return String.valueOf(EqDataType.NO);
		}
	}

	/**
	 * Convert PV mode to Yes, No or blank
	 * 
	 * @param mode
	 *            - the mode (Add, Maintain, Delete, Fully)
	 * 
	 * @return returns Y for Add, blank for Fully, otherwise N
	 */
	public static String cvtPVModeToYNB(String mode)
	{
		if (mode.equals(IEquationStandardObject.FCT_FUL))
		{
			return "";
		}
		else if (mode.equals(IEquationStandardObject.FCT_ADD))
		{
			return String.valueOf(EqDataType.YES);
		}
		else
		{
			return String.valueOf(EqDataType.NO);
		}
	}

	/**
	 * Convert a Yes or No flag to boolean True or False
	 * 
	 * @param ynFlag
	 *            - the Y/N flag
	 * 
	 * @param defaultValue
	 *            - the default value if the Y/N flag is neither Y or N
	 * 
	 * @return true if Y, false if N, otherwise the default value
	 * @equation.external
	 */
	public static boolean cvtYNToBoolean(String ynFlag, boolean defaultValue)
	{
		if (ynFlag == null)
		{
			return defaultValue;
		}
		else if (ynFlag.equals(EqDataType.YES))
		{
			return true;
		}
		else if (ynFlag.equals(EqDataType.NO))
		{
			return false;
		}
		else
		{
			return defaultValue;
		}
	}

	/**
	 * Convert a list of valid values (colon separated) into function key text
	 * 
	 * @param validValues
	 *            - the valid values
	 * 
	 * @return the function key text format
	 */
	public static String cvtValidValuesToFunctionKey(String validValues)
	{
		StringBuffer buffer = new StringBuffer();
		String[] list = validValues.split(":");
		for (String element : list)
		{
			buffer.append(element + "=" + element);
			buffer.append(" ");
		}
		return buffer.toString();
	}

	/**
	 * Convert a string of hex values to a string of bytes
	 * 
	 * @param txtInHex
	 *            - the string of hex values, e.g. "00010203"
	 * 
	 * @return the string of bytes, e.g. "123"
	 */
	public static String cvtHexToStr(String txtInHex)
	{
		byte[] txtInByte = new byte[txtInHex.length() / 2];
		int j = 0;
		for (int i = 0; i < txtInHex.length(); i += 2)
		{
			txtInByte[j++] = Byte.parseByte(txtInHex.substring(i, i + 2), 16);
		}
		String txtInString = new String(txtInByte);
		return txtInString;
	}

	/**
	 * Print the current time
	 */
	public static void printTime(String text)
	{
		Calendar cal = Calendar.getInstance();
		LOG.debug(text + cal.getTime() + " " + cal.getTimeInMillis());
	}

	/**
	 * Get the text with time
	 */
	public static String getTextWithTime(String text)
	{
		Calendar cal = Calendar.getInstance();
		return text + cal.getTime() + " " + cal.getTimeInMillis();
	}

	/**
	 * Get a list of comma separated fields
	 * 
	 * @param fieldNames
	 *            - a set of strings of field names
	 * 
	 * @return the list of comma seperated fields
	 */
	public static String cvtSetToCSV(Set<String> fieldNames)
	{
		// Start to build the sql
		StringBuilder string = new StringBuilder();
		// Add the fields
		for (String string2 : fieldNames)
		{
			string.append(string2 + ",");
		}
		string.deleteCharAt(string.length() - 1);
		return string.toString();
	}

	/**
	 * Return D or ''
	 * 
	 * @param delete
	 *            - true if delete mode
	 * 
	 * @return the mode
	 */
	public static String getDeleteMode(boolean delete)
	{
		if (delete)
		{
			return IEquationStandardObject.FCT_DEL;
		}
		else
		{
			return "";
		}
	}

	/**
	 * Convert and return a valid string valid as a replacement string (e.g. convert $ to \$)
	 * 
	 * @param replace
	 *            - the string replacement
	 * 
	 * @return the converted replacement string
	 */
	public static String replaceString(String replace)
	{
		return replace.replaceAll("\\$", "\\\\\\$");
	}

	/**
	 * Converts text such as a label into Camel Case format
	 * <p>
	 * Note that this will process only A-z, 0-9 characters from the incoming string.
	 * 
	 * @param text
	 *            a <code>String</code> containing the text to convert
	 * @return The Camel Case equivalent
	 * @equation.external
	 */
	public static String textToCamelCase(String text)
	{
		StringBuilder result = new StringBuilder();
		boolean firstWord = true;
		boolean inWord = false;
		for (char ch : text.trim().toCharArray())
		{
			// Only allow Latin letters and numbers as this method is used when converting labels for use in WSDL (schema fields
			// etc)
			boolean isLetterOrDigit = (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9');
			if (isLetterOrDigit)
			{
				result.append(inWord || firstWord ? Character.toLowerCase(ch) : Character.toUpperCase(ch));
				firstWord = false;
			}
			// Although apostrophes are always skipped, do not allow them
			// to cause a word break:
			if (ch != '\'')
			{
				inWord = isLetterOrDigit;
			}
		}
		return result.toString();
	}

	/**
	 * Remove leading zeroes
	 * 
	 * @param text
	 *            - the numeric string
	 * @param allowBlank
	 *            - if the string is all 0, then return blank, otherwise, return 0
	 * 
	 * @return the text with leading zeroes remove
	 * @equation.external
	 */
	public static String removeLeadingZeroes(String text, boolean allowBlank)
	{
		// empty string, do nothing
		if (text.length() == 0)
		{
			return text;
		}

		int i = 0;
		while (text.charAt(i) == '0')
		{
			i++;

			// but do not exceed the length
			if (i >= text.length())
			{
				if (allowBlank)
				{
					return "";
				}
				else
				{
					return "0";
				}
			}
		}

		// if this is a dot, then return the last zero (e.g. return 0.1, but not .1)
		if (i > 0 && (text.charAt(i) == '.' || text.charAt(i) == ','))
		{
			i--;
		}

		// return string
		return text.substring(i);
	}

	/**
	 * Remove trailing zeroes after the decimal character
	 * 
	 * @param text
	 *            - the numeric string
	 * @param decimalChar
	 *            - the decimal character
	 * 
	 * @return the text with leading zeroes remove
	 * @equation.external
	 */
	public static String removeTrailingZeroes(String text, String decimalChar)
	{
		// empty string, do nothing
		if (text.length() == 0)
		{
			return text;
		}

		// search for decimal character
		int deciN = text.indexOf(decimalChar);
		if (deciN < 0)
		{
			return text;
		}

		// search for the first non-zero
		int i = text.length() - 1;
		while (text.charAt(i) == '0')
		{
			i--;
		}

		// if it something like 1.000, ensure it returns 1.0 (instead of 1.)
		if (i == deciN && i < text.length() - 1)
		{
			i = i + 1;
		}

		// return string
		return text.substring(0, i + 1);
	}

	/**
	 * Convert AS400 time stamp (yyyyMMddHHmmssSSS) to {@link Calendar}
	 * 
	 * @param dateTime
	 * 
	 * @return the converted time stamp
	 * 
	 * @throws IllegalArgumentException
	 *             - when the length of 'dateTime' is invalid
	 * @equation.external
	 */
	public static Calendar convertAS400TimeStampToCalendar(String dateTime)
	{
		if (StringUtils.isEmpty(dateTime) || dateTime.length() < 17)
		{
			throw new IllegalArgumentException(LanguageResources.getFormattedString("Language.InvalidArgumentLength", dateTime));
		}

		final int yr = Integer.parseInt(dateTime.substring(0, 4));
		final int mo = Integer.parseInt(dateTime.substring(4, 6));
		final int dt = Integer.parseInt(dateTime.substring(6, 8));
		final int hh = Integer.parseInt(dateTime.substring(8, 10));
		final int mm = Integer.parseInt(dateTime.substring(10, 12));
		final int ss = Integer.parseInt(dateTime.substring(12, 14));
		final int ms = Integer.parseInt(dateTime.substring(14, 17));

		final Calendar serverTime = Calendar.getInstance();
		serverTime.set(Calendar.YEAR, yr);
		serverTime.set(Calendar.MONTH, mo - 1);
		serverTime.set(Calendar.DATE, dt);
		serverTime.set(Calendar.HOUR_OF_DAY, hh);
		serverTime.set(Calendar.MINUTE, mm);
		serverTime.set(Calendar.SECOND, ss);
		serverTime.set(Calendar.MILLISECOND, ms);

		return serverTime;
	}

	/**
	 * Convert text into RTL equivalent for display
	 * 
	 * @param value
	 *            - the string value to convert
	 * @param length
	 *            - maximum length
	 * @param ccsid
	 *            - the ccsid
	 * @param conversionType
	 *            - BidiStringType.ST9 - process RTL field and remove leading space<br>
	 * 
	 * @return the converted string for display
	 * @equation.external
	 */
	public static String convertTextRTLForDisplay(String value, int length, int ccsid, int conversionType)
	{
		// blank
		if (value.trim().length() == 0)
		{
			return "";
		}

		AS400BidiTransform abt = new AS400BidiTransform(ccsid);

		// if it is an RTL field, then set to ST6, as it is visually RTL only (not internal representation), so the
		// actual string needs to be reverse
		if (conversionType == BidiStringType.ST9)
		{
			abt.setJavaStringType(BidiStringType.ST6);
		}

		// non-RTL field, then output string should be exactly the same
		else
		{
			abt.setJavaStringType(BidiStringType.ST5);
		}

		String str = abt.toJavaLayout(value);

		// If we are going to display it, then remove the leading space - see reversal method convertTextRTLFromDisplay
		if (conversionType == BidiStringType.ST9)
		{
			str = ltrim(str);
		}
		return str;
	}

	/**
	 * Convert text into database format from RTL display
	 * 
	 * @param value
	 *            - the string value to convert
	 * @param ccsid
	 *            - the ccsid
	 * @param conversionType
	 *            - BidiStringType.ST9 - process RTL field and right align it<br>
	 * 
	 * @return the converted string (output format)
	 * @equation.external
	 */
	public static String convertTextRTLFromDisplay(String value, int length, int ccsid, int conversionType)
	{
		// blank
		if (value.trim().length() == 0)
		{
			return "";
		}

		AS400BidiTransform abt = new AS400BidiTransform(ccsid);

		// if it is an RTL field, then set to ST6, as it is visually RTL only (not internal representation), so the
		// actual string needs to be reverse
		if (conversionType == BidiStringType.ST9)
		{
			abt.setJavaStringType(BidiStringType.ST6);
		}

		// non-RTL field, then output string should be exactly the same
		else
		{
			abt.setJavaStringType(BidiStringType.ST5);
		}

		String str = abt.toAS400Layout(value);

		// add leading spaces - see reversal method convertTextRTLForDisplay
		if (conversionType == BidiStringType.ST9)
		{
			String s1 = rtrim(str);
			int spaces = length - s1.length();
			if (spaces > 0)
			{
				str = pad("", spaces) + s1;
			}
		}

		return str;
	}

	/**
	 * Convert the AS400 byte data into text taking into consideration the CCSID of the AS400 byte data
	 * 
	 * @param byteData
	 *            - the data
	 * @param length
	 *            - the length
	 * @param ccsid
	 *            - the CCSID of the byte data
	 * 
	 * @return the equivalent String
	 * @equation.external
	 */
	public static String convertAS400TextIntoCCSID(byte[] byteData, int length, int ccsid)
	{
		AS400Text text = new AS400Text(length, ccsid);
		String cvtData = (String) text.toObject(byteData);
		return cvtData;
	}

	/**
	 * Convert the AS400 zoned byte data into text
	 * 
	 * @param byteData
	 *            - the data
	 * @param length
	 *            - the length
	 * @param decimal
	 *            - number of decimals
	 * 
	 * @return the equivalent String
	 * @equation.external
	 */
	public static BigDecimal convertAS400Zoned(byte[] byteData, int length, int decimal)
	{
		AS400ZonedDecimal text = new AS400ZonedDecimal(length, decimal);
		BigDecimal cvtData = (BigDecimal) text.toObject(byteData);
		return cvtData;
	}

	/**
	 * Convert the AS400 byte data into text taking into consideration the CCSID of the AS400 byte data
	 * 
	 * @param byteData
	 *            - the data
	 * @param length
	 *            - the length
	 * @param decimal
	 *            - number of decimals
	 * 
	 * @return the equivalent String
	 * @equation.external
	 */
	public static BigDecimal convertAS400Packed(byte[] byteData, int length, int decimal)
	{
		AS400PackedDecimal text = new AS400PackedDecimal(length, decimal);
		BigDecimal cvtData = (BigDecimal) text.toObject(byteData);
		return cvtData;
	}

	/**
	 * Convert the String into AS400 text taking into consideration the CCSID of the String
	 * 
	 * @param strData
	 *            - the string to convert
	 * @param length
	 *            - the length
	 * @param ccsid
	 *            - the CCSID of the byte data
	 * @param type
	 *            - conversion type (see BidiStringType class)
	 * 
	 * @return the equivalent String
	 * @equation.external
	 */
	public static String convertTextIntoAS400TextCCSID(String strData, int length, int ccsid, int type)
	{
		AS400Text text = new AS400Text(length, ccsid);
		byte[] b = new byte[length];
		text.toBytes(strData, b, 0, type);
		String cvtData = (String) text.toObject(b);
		return cvtData;
	}

	/**
	 * Convert the String into AS400 text taking into consideration the CCSID of the String
	 * 
	 * @param strData
	 *            - the string to convert
	 * @param length
	 *            - the length
	 * @param ccsid
	 *            - the CCSID of the byte data
	 * @param type
	 *            - conversion type (see BidiStringType class)
	 * 
	 * @return the equivalent String
	 * @equation.external
	 */
	public static byte[] convertTextIntoAS400BytesCCSID(String strData, int length, int ccsid, int type)
	{
		AS400Text text = new AS400Text(length, ccsid);
		byte[] b = new byte[length];
		text.toBytes(strData, b, 0, type);
		return b;
	}

	/**
	 * Convert a number into an AS400 Packed Decimal
	 * 
	 * @param num
	 *            - a double to be converted
	 * @param length
	 *            - the buffer size
	 * @param decimal
	 *            - the number of decimal places
	 * 
	 * @return a byte array containing the Packed Decimal
	 * @equation.external
	 */
	public static byte[] convertNumberIntoAS400PackedBytes(double num, int length, int decimal)
	{
		AS400PackedDecimal as400Text = new AS400PackedDecimal(length, decimal);
		return as400Text.toBytes(num);
	}

	/**
	 * Convert a number into an AS400 Zoned Decimal
	 * 
	 * @param num
	 *            - a double to be converted
	 * @param length
	 *            - the buffer size
	 * @param decimal
	 *            - the number of decimal places
	 * 
	 * @return a byte array containing the Zoned Decimal
	 * @equation.external
	 */
	public static byte[] convertNumberIntoAS400ZonedBytes(double num, int length, int decimal)
	{
		AS400ZonedDecimal as400Text = new AS400ZonedDecimal(length, decimal);
		return as400Text.toBytes(num);
	}

	/**
	 * Determines if the specified text can be successfully round-tripped to EBCDIC using the specified CCSID
	 * <p>
	 * This is used to determine whether text contains invalid iSeries characters. In particular, this is used to ensure that field
	 * labels are valid when creating text column headings.
	 * 
	 * @param text
	 *            Text to attempt to roundtrip
	 * @param ccsid
	 *            The CCSID for which roundtripping will be tested
	 * @return true if the text was successfully round-tripped
	 * @equation.external
	 */
	public static boolean canRoundTrip(String text, int ccsid)
	{
		String start = text.trim();
		AS400Text as400Text = new AS400Text(start.length(), ccsid);
		byte[] bytes = as400Text.toBytes(start);
		String returned = (String) as400Text.toObject(bytes);
		return start.equals(returned.trim());
	}

	/**
	 * Utility method to sort a Map by values
	 * <p>
	 * This method caters for duplicate values. Where these exist, the duplicate entries are then sorted by key.
	 * 
	 * @param passedMap
	 *            - the Map to sort
	 * @return a LinkedHashMap sorted by value
	 * @equation.external
	 */
	public static LinkedHashMap<String, String> sortHashMapByValues(Map<String, String> passedMap)
	{
		// Take a copy of the map passed in, as later, entries must be removed:
		LinkedHashMap<String, String> copyMap = new LinkedHashMap<String, String>();
		copyMap.putAll(passedMap);

		List<String> mapKeys = new ArrayList<String>(copyMap.keySet());
		List<String> mapValues = new ArrayList<String>(copyMap.values());
		Collections.sort(mapValues);
		Collections.sort(mapKeys);

		LinkedHashMap<String, String> sortedMap = new LinkedHashMap<String, String>();
		for (String val : mapValues)
		{
			for (String key : mapKeys)
			{
				if (copyMap.get(key).equals(val))
				{
					sortedMap.put(key, val);
					// Remove this entry to cope with duplicate values (so that next duplicate value
					// returns different key)
					copyMap.remove(key);
					mapKeys.remove(key);
					break;
				}
			}
		}
		return sortedMap;
	}

	/**
	 * Validate whether a number is within a specified size
	 * 
	 * @param validNumber
	 *            - a valid number to be validated
	 * @param size
	 *            - the maximum size
	 * @param dec
	 *            - the number of decimal places
	 * 
	 * @return the error message
	 * @equation.external
	 */
	public static String validateNumber(String validNumber, int size, int dec)
	{
		// maximum digit (left hand side of decimal)
		int intsize = size - dec;

		// remove leading/trailing zeros
		String str = removeLeadingZeroes(removeTrailingZeroes(validNumber, EqDataType.DECIMALSEP), false);

		// position of .
		int index = str.indexOf(".");
		if (index < 0)
		{
			index = str.length();
		}

		// get left hand side
		String intPart = str.substring(0, index);

		// get right hand side
		String decPart = "";
		if (index < str.length() - 1)
		{
			decPart = str.substring(index + 1);
		}

		// left hand side exceeds digits
		if (intPart.length() > intsize)
		{
			return "KSM0586";
		}

		// right hand side exceeds digits
		if (decPart.length() > dec)
		{
			return "KSM0647";
		}

		// valid
		return "";
	}

	/**
	 * Determine if bytes has already been set or not
	 * 
	 * @param data
	 *            - the data bytes
	 * 
	 * @return true if data is all 0
	 * @equation.external
	 */
	public static boolean isBlank(byte[] data)
	{
		for (byte element : data)
		{
			if (element != 0)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Concatenates the Strings in the array, using the specified separator.
	 * <p>
	 * This is effectively the reverse of the String.split method
	 * 
	 * @param parts
	 *            A String[]
	 * @param separator
	 *            The String to be included between the parts
	 * @return The joined String
	 * @equation.external
	 */
	public static String join(String[] parts, String separator)
	{
		StringBuilder builder = new StringBuilder();
		// Use a for loop with an index to allow easy determination of whether first part
		for (int i = 0; i < parts.length; i++)
		{
			if (i > 0)
			{
				builder.append(separator);
			}
			builder.append(parts[i]);
		}
		return builder.toString();
	}

	/**
	 * Count number of leading spaces
	 * 
	 * @param str
	 *            - the string
	 * 
	 * @return the number of leading spaces
	 * @equation.external
	 */
	public static int countLeadingSpaces(String str)
	{
		for (int i = 0; i < str.length(); i++)
		{
			if (str.charAt(i) != ' ')
			{
				return i;
			}
		}
		return str.length();
	}

	/**
	 * Return the special DSEPMS with parameter marker
	 * 
	 * @param ksmId
	 *            - the KSM id
	 * @param parm1
	 *            - parameter 1
	 * @param parm2
	 *            - parameter 2
	 * @param parm3
	 *            - parameter 3
	 */
	public static String getDSEPMS(String ksmId, String parm1, String parm2, String parm3)
	{
		return ksmId + parm1 + EQMessage.PARAM_DELIMETER + parm2 + EQMessage.PARAM_DELIMETER + parm3;
	}

	/**
	 * Edit an edited number. This performs editing on an already edited number returned by the PV module (e.g. GWV30R)
	 * 
	 * @param number
	 *            - the number
	 * @param length
	 *            - the number of digits
	 * @param decimal
	 *            - the number of decimal places
	 * @param integerSep
	 *            - integer separator
	 * @param decimalSep
	 *            - decimal separator
	 * 
	 * @return re-edited number
	 */
	public static String reformatNumber1(String number, int length, int decimal, String integerSep, String decimalSep)
	{
		// get the string
		String str = number.trim();
		int lastStrIndex = str.length() - 1;

		// none?
		if (lastStrIndex < 0)
		{
			return str;
		}

		// is there negative sign at the end, move it to front
		if (str.substring(lastStrIndex).equals(EqDataType.MINUS_SIGN))
		{
			str = EqDataType.MINUS_SIGN + str.substring(0, lastStrIndex);
		}

		// ensure there are right number of decimal places (Eq PV, does not put the maximum number of decimal places for rate)
		if (decimal > 0)
		{
			int dotIndex = str.indexOf(decimalSep);
			if (dotIndex >= 0 && dotIndex < lastStrIndex)
			{
				String decimalPart = str.substring(dotIndex + 1);
				if (decimalPart.length() < decimal)
				{
					str = str + pad("", "0", decimal - decimalPart.length());
				}
			}
			else if (dotIndex >= 0)
			{
				str = str + pad("", "0", decimal);
			}
			else
			{
				str = str + decimalSep + pad("", "0", decimal);
			}
		}

		// return the string
		return str;
	}

	/**
	 * Edit a number so that it has the specified number of length and decimal
	 * 
	 * @param fieldValue
	 *            - the number
	 * @param length
	 *            - the maximum length
	 * @param decimal
	 *            - the number of decimal
	 * 
	 * @return the edited number
	 */
	public static String reformatNumber2(String fieldValue, int length, int decimal, String decimalSep)
	{
		String str = fieldValue;

		// decimal exists, then ensure it has the right number of decimals
		int indexDecimal = fieldValue.indexOf(decimalSep);
		if (indexDecimal >= 0)
		{
			int i = fieldValue.length() - indexDecimal - 1;
			if (i < decimal)
			{
				str = str + Toolbox.pad("", "0", decimal - i);
			}
		}

		// pad leading zeroes
		str = Toolbox.padAtFront(str, "0", length);

		return str;
	}

	/**
	 * Convert the AS400 byte data one at a time into text taking into consideration the CCSID of the AS400 byte data
	 * 
	 * @param byteData
	 *            - the data
	 * @param length
	 *            - the length
	 * @param ccsid
	 *            - the CCSID of the byte data
	 * 
	 * @return the equivalent String
	 * @equation.external
	 */
	public static String convertAS400TextIntoCCSIDOneAtATime(byte[] byteData, int length, int ccsid)
	{
		// shift in-out details
		boolean doubleData = false;
		byte[] doubleBytes = new byte[length];
		int doubleIndex = 0;

		byte[] bufferBytes = new byte[512];
		int nBuffer = 0;

		// the converted string buffer
		StringBuilder text = new StringBuilder();

		for (byte element : byteData)
		{
			// in double bytes mode?
			if (doubleData)
			{
				// shift out of double bytes? - then convert the byte details
				if (element == 15)
				{
					doubleBytes[doubleIndex] = 15;
					doubleIndex++;
					String s = Toolbox.convertAS400TextIntoCCSID(doubleBytes, doubleIndex, ccsid);
					text.append(s);
					doubleData = false;
					doubleIndex = 0;
				}

				// just add into the list
				else
				{
					doubleBytes[doubleIndex] = element;
					doubleIndex++;
				}
			}

			// shift to double bytes?
			else if (element == 14)
			{
				if (nBuffer > 0)
				{
					String s = Toolbox.convertAS400TextIntoCCSID(bufferBytes, nBuffer, ccsid);
					text.append(s + " ");
					bufferBytes = new byte[512];
					nBuffer = 0;
				}

				doubleBytes[0] = 14;
				doubleIndex++;
				doubleData = true;
			}
			else
			{
				byte[] b = new byte[1];
				b[0] = element;
				String s = Toolbox.convertAS400TextIntoCCSID(b, 1, ccsid);
				text.append(s);
			}

		}

		// anything left in the buffer bytes?
		if (nBuffer > 0)
		{
			String s = Toolbox.convertAS400TextIntoCCSID(bufferBytes, nBuffer, ccsid);
			text.append(s + " ");
		}

		// return the string
		String s = text.toString();
		return s;
	}

	/*******************************************************************************************************************************
	 * Return the string without the control characters
	 * <P>
	 * 
	 * @param str
	 *            - the string to process
	 * 
	 * @return the string without the control characters
	 */
	public static String removeControlCharacter(String str)
	{
		// removing control characters from text before creating string
		char[] buff = str.toCharArray();
		char character = ' ';
		int txtStartIndex = 0;
		int txtEndIndex = buff.length;
		for (int i = txtStartIndex; i < txtEndIndex; i++)
		{
			character = buff[i];
			if (character >= 0x0080 && character <= 0x0090)
			{
				buff[i] = ' ';
			}
		}

		return new String(buff).trim();
	}

	/**
	 * Builds a String[] from a List<String>
	 * 
	 * @param list
	 *            a <code>List<String></code> of strings.
	 * @return a <code>String[]</code>
	 * @equation.external
	 */
	public static String[] getStringArray(List<String> list)
	{
		// Convert the KeyedArrayList<String> to a simple String []
		String[] contentStrings = new String[list.size()];
		int index = 0;
		for (Object element : list)
		{
			contentStrings[index++] = (String) element;
		}
		return contentStrings;
	}

	/**
	 * get current date time in yyyyMMddhhmmss format
	 * 
	 * @return the date time in a string
	 */
	public static String getDateTimeNumber()
	{
		SimpleDateFormat sdfDDMMMYYhhmmss = new SimpleDateFormat("yyyyMMddhhmmss");
		Time time = new Time(System.currentTimeMillis());
		return sdfDDMMMYYhhmmss.format(time);
	}
	/**
	 * get current date time in user format
	 * 
	 * @param user
	 *            - the EquationUser whose preferences will be used to format the date
	 * 
	 * @return the date time in user format
	 */
	public static String getCurrentDateTimeInUserFormat(EquationUser user)
	{
		String dateInputFormat = user.getEquationUnit().getSystemOption(EqDS_DSSYSE.DATFM).substring(0, 1);

		SimpleDateFormat sdfYYYYmmddhhmmss = new SimpleDateFormat("yyyyMMddHms");
		Time time = new Time(System.currentTimeMillis());
		String yyyyMMddhhmmss = sdfYYYYmmddhhmmss.format(time);
		Integer yyyy = new Integer(yyyyMMddhhmmss.substring(0, 4));
		String century = null;
		if (yyyy < 2000)
		{
			century = "0";
		}
		else
		{
			century = "1";
		}
		String CYYMMDD = century + yyyyMMddhhmmss.substring(2, 8);
		String date = EqDataType.formatEquationDate(CYYMMDD, dateInputFormat, "", user
						.getSystemDictionary(EquationParameters.HA_SDJAN));
		return date + "  " + formatTime(yyyyMMddhhmmss.substring(8), 6);
	}
	/**
	 * Determine whether the program is non-Equation service program
	 * 
	 * @param programName
	 *            - the program name
	 * 
	 * @return true if the program is an Equation service program
	 */
	public static boolean isEqServiceGBRecord(String programName)
	{
		if (programName.startsWith(EquationStandardTransaction.EDF_SHORT_ROOT))
		{
			// Programs that are not related to Equation service
			if (programName.equals("W90DMR") || programName.equals("W90LMR"))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else
		{
			return false;
		}
	}

	/**
	 * Validate whether an option is valid or not for desktop
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * @param optionId
	 *            - the option id
	 * @param isWebFacing
	 *            - is webfacing installed?
	 * 
	 * @return true if option is valid
	 */
	public static boolean validateOption(EquationUser eqUser, String optionId, boolean isWebFacing)
	{
		// is authorised?
		boolean authorised = EqDataToolbox.validateOption(eqUser.getSession(), "V", "N", "N", optionId, eqUser.getUserId()).equals(
						"");

		// not authorised
		if (!authorised)
		{
			return false;
		}

		// legacy function and webfacing not installed
		try
		{
			if (!isWebFacing && eqUser.getEquationUnit().isLegacyOption(optionId))
			{
				return false;
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			return false;
		}

		// valid option
		return true;
	}

	/**
	 * Delete a spooled file
	 * 
	 * @param eqSystem
	 *            - the EquationSystem
	 * @param spooledName
	 *            - the spooled file name
	 * @param jobName
	 *            - the job name
	 * @param jobUser
	 *            - the user's name
	 * @param jobNumber
	 *            - the job number
	 * @param splfNum
	 *            - the spooled file name
	 */
	public static void removeSpooledFile(EquationSystem eqSystem, String spooledName, String jobName, String jobUser,
					String jobNumber, int splfNum) throws EQException
	{
		// get the AS400 system
		AS400 as400 = null;
		try
		{
			as400 = eqSystem.getAS400();
			removeSpooledFile(as400, spooledName, jobName, jobUser, jobNumber, splfNum);
		}
		catch (Exception e)
		{
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
		finally
		{
			if (as400 != null)
			{
				eqSystem.returnAS400(as400);
			}
		}
	}

	/**
	 * Delete a spooled file
	 * 
	 * @param as400
	 *            - the AS400 system
	 * @param spooledName
	 *            - the spooled file name
	 * @param jobName
	 *            - the job name
	 * @param jobUser
	 *            - the user's name
	 * @param jobNumber
	 *            - the job number
	 * @param splfNum
	 *            - the spooled file name
	 */
	public static void removeSpooledFile(AS400 as400, String spooledName, String jobName, String jobUser, String jobNumber,
					int splfNum) throws Exception
	{
		SpooledFile spooledFile = new SpooledFile(as400, spooledName, splfNum, jobName, jobUser, jobNumber);
		spooledFile.delete();
	}

	/**
	 * Negate a number
	 * 
	 * @param numeric
	 *            - a valid numeric number
	 * 
	 * @return the equivalent String of the result of 99999 less the number
	 * @equation.external
	 */
	public static String negate(String numeric)
	{
		StringBuffer buffer = new StringBuffer(numeric);
		for (int i = 0; i < buffer.length(); i++)
		{
			char c = buffer.charAt(i);

			// number?
			if (c >= '0' && c <= '9')
			{
				if (c == '0')
				{
					buffer.replace(i, i + 1, "9");
				}
				else if (c == '1')
				{
					buffer.replace(i, i + 1, "8");
				}
				else if (c == '2')
				{
					buffer.replace(i, i + 1, "7");
				}
				else if (c == '3')
				{
					buffer.replace(i, i + 1, "6");
				}
				else if (c == '4')
				{
					buffer.replace(i, i + 1, "5");
				}
				else if (c == '5')
				{
					buffer.replace(i, i + 1, "4");
				}
				else if (c == '6')
				{
					buffer.replace(i, i + 1, "3");
				}
				else if (c == '7')
				{
					buffer.replace(i, i + 1, "2");
				}
				else if (c == '8')
				{
					buffer.replace(i, i + 1, "1");
				}
				else if (c == '9')
				{
					buffer.replace(i, i + 1, "0");
				}
			}

			else if (c == '.')
			{
			}

			// not a number, then simply return the original string
			else
			{
				return numeric;
			}
		}
		return buffer.toString();
	}

	/**
	 * Convert a String containing a numeric into the form x000...000nnnn where n is passed and x can be a negative sign
	 * 
	 * @param ns
	 *            - string
	 * @param len
	 *            - length of the numeric portion without the sign
	 * 
	 * @return the formatted numeric string
	 * @equation.external
	 */
	public static String formatInteger(String ns, int len)
	{
		// if we can't parse it just return it "as is"
		int i = 0;
		try
		{
			i = Integer.parseInt(ns);
		}
		catch (Exception e)
		{
			return ns;
		}
		// if it is already long enough then return it as is
		if (ns.length() >= len)
		{
			return ns;
		}
		// inject the "-" at the back if required...
		if (i < 0)
		{
			return "-" + Toolbox.leftPad(Integer.toString(Math.abs(i)), "0", len);
		}
		else
		{
			return " " + Toolbox.leftPad(Integer.toString(Math.abs(i)), "0", len);
		}
	}

	/**
	 * Convert a String containing a numeric rate into the form x000...000nnnnmmmmmmmm000...000 where nnnn.mmmmmmmm is passed and x
	 * can be a negative sign
	 * 
	 * @param ns
	 *            - numeric string
	 * @param intLen
	 *            - the length of the integer part
	 * @param decLen
	 *            - the length of the decimal part
	 * 
	 * @return the formatted numeric string
	 * @equation.external
	 */
	public static String formatDouble(String ns, int intLen, int decLen)
	{
		// if we can't parse it just return it "as is"
		double d = 0;
		try
		{
			d = Double.parseDouble(ns);
		}
		catch (Exception e)
		{
			return ns;
		}
		// Need to process the integer and decimal part separately
		String intPart = "";
		String decPart = "";
		String nsAdj = ns.trim();
		if (nsAdj.startsWith("-"))
		{
			nsAdj = nsAdj.substring(1);
		}
		if (nsAdj.endsWith("-"))
		{
			nsAdj = nsAdj.substring(0, nsAdj.length() - 1);
		}
		if (nsAdj.indexOf(".") > -1)
		{
			intPart = nsAdj.substring(0, nsAdj.indexOf("."));
			if (nsAdj.indexOf(".") != (nsAdj.length() - 1))
			{
				decPart = nsAdj.substring(nsAdj.indexOf(".") + 1);
			}
		}
		else
		{
			intPart = String.valueOf(nsAdj);
		}
		// if int part is too long then leave it as
		if (intPart.length() > intLen)
		{
			return ns;
		}
		else
		{
			intPart = Toolbox.leftPad(intPart, "0", intLen);
		}
		// if dec part is too long then leave it as
		if (decPart.length() > decLen)
		{
			return ns;
		}
		else
		{
			decPart = Toolbox.pad(decPart, "0", decLen);
		}

		// inject the "-" at the back if required...
		if (d < 0)
		{
			return "-" + intPart + decPart;
		}
		else
		{
			return " " + intPart + decPart;
		}
	}

	/**
	 * Return the Exception message by iterating over all causes. Exceptions can be nested.
	 * 
	 * @param e
	 * @return message of concatenated messages
	 * @equation.external
	 */
	public static String getExceptionMessage(Throwable e)
	{
		String message = "";
		try
		{
			message = getExceptionCause(e, message);
			if (message.equals(""))
			{
				message = e.toString();
			}
			return message;
		}
		catch (Exception e1)
		{
			return "Exception Message processing failed.";
		}
	}

	/**
	 * Return the Exception message by iterating over all causes. Exceptions can be nested.
	 * 
	 * @param e
	 *            Throwable
	 * @param message
	 * @return message of concatenated messages
	 * @equation.external
	 * */
	private static String getExceptionCause(Throwable e, String message)
	{
		if (e.getLocalizedMessage() != null && !message.contains(e.getLocalizedMessage()))
		{
			if (message.length() != 0)
			{
				message = message + TEXT_DELIMITER;
			}
			message = message + e.getLocalizedMessage();
		}
		else
		{
			if (e.getMessage() != null && !message.contains(e.getMessage()))
			{
				if (message.length() != 0)
				{
					message = message + TEXT_DELIMITER;
				}
				message = message + e.getMessage();
			}
		}
		if (e.getCause() != null)
		{
			message = getExceptionCause(e.getCause(), message);
		}

		return message;
	}

	/**
	 * Converts a byte array to String in Base64 encoding
	 * <p>
	 * Note that this method uses the 'internal' sun.misc.BASE64Encoder class, however this is present in the IBM JDK for WebSphere
	 * 7.0
	 * 
	 * @param bytes
	 *            byte array
	 * @return String in Base 64 encoding
	 */
	public static String byteArrayToBase64String(byte[] bytes)
	{
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(bytes);
	}

	/**
	 * Converts a String in Base 64 encoding to a byte array.
	 * <p>
	 * Note that this method uses the 'internal' sun.misc.BASE64Dncoder class, however this is present in the IBM JDK for WebSphere
	 * 7.0
	 * 
	 * @param base64
	 *            String in Base 64 encoding
	 * @return byte array
	 */
	public static byte[] base64StringToByteArray(String base64)
	{
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] result = null;
		try
		{
			result = decoder.decodeBuffer(base64);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * Convert the encoded password into AS400 token bytes
	 * 
	 * @param password
	 *            - the password
	 * @param passwordType
	 *            - the password type
	 * 
	 * @return password in AS400 token bytes
	 * 
	 * @throws EQException
	 */
	public static byte[] cvtPwdToAS400TokenBytes(String password, String passwordType) throws EQException
	{
		byte[] tokenBytes = null;

		// Token as encoded by Equation
		if (passwordType.equals(EquationCommonContext.PASSWORD_TYPE_PROFILETOKEN_PLAIN))
		{
			tokenBytes = Toolbox.cvtHexStringToBytes(password);
		}

		// Token as Base 64
		else if (passwordType.equals(EquationCommonContext.PASSWORD_TYPE_PROFILETOKEN_BASE64))
		{
			tokenBytes = base64StringToByteArray(password);
		}

		// return the token bytes
		return tokenBytes;
	}

	/**
	 * Pad/trim the input string to the specified length
	 * 
	 * @param inputString
	 *            - the input string
	 * @param length
	 *            = the desired length
	 * 
	 * @return the padded/trimmed string
	 * @equation.external
	 * 
	 */
	public static String fixLengthChar(String inputString, int length)
	{
		return trim(pad(inputString, length), length);
	}

	/**
	 * Remove leading space
	 * 
	 * @param source
	 *            - source string
	 * @return string with leading spaces remove
	 */
	public static String ltrim(String source)
	{
		return source.replaceAll("^\\s+", "");
	}

	/**
	 * Remove trailing space
	 * 
	 * @param source
	 *            - source string
	 * @return string with trailing spaces remove
	 */
	public static String rtrim(String source)
	{
		return source.replaceAll("\\s+$", "");
	}

	/**
	 * Return the Date object equivalent of a String format in UTC time
	 * 
	 * @param utcTime
	 *            - the date in UTC time String format yyyy-MM-dd HH:mm:ss
	 * 
	 * @return the Date object
	 * @equation.external
	 */
	public static Date parseUTCTime(String utcTime)
	{
		String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATEFORMAT);

		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		try
		{
			Date dateToReturn = dateFormat.parse(utcTime);
			return dateToReturn;
		}
		catch (ParseException e)
		{
			LOG.error("parseUTCTime() : + " + utcTime + ":" + e);
			return null;
		}
	}
	/**
	 * Return true if the column is in the table
	 * 
	 * @param tableId
	 * @param columnId
	 * @param session
	 * @throws EQException
	 * @throws SQLException
	 * @return true if the column is in the table
	 */
	public static boolean isColumnInTable(String tableId, String columnId, EquationStandardSession session) throws EQException,
					SQLException
	{
		String query = "SELECT * FROM " + tableId + " WHERE 1=0";
		Statement rcdFmtStatement = null;
		ResultSet rcdFmtResultSet = null;

		try
		{
			// Execute the statement
			rcdFmtStatement = session.getConnection().createStatement();
			rcdFmtResultSet = rcdFmtStatement.executeQuery(query);

			String columnName = null;
			int i = 0;
			ResultSetMetaData rcdFmtResultSetMetaData = rcdFmtResultSet.getMetaData();
			int rcdFmtColumnCount = rcdFmtResultSetMetaData.getColumnCount();

			// Next put the fields into the fieldSet and store their names
			for (i = 1; i <= rcdFmtColumnCount; i++)
			{
				// create the Equation field definition
				columnName = rcdFmtResultSetMetaData.getColumnName(i);
				if (columnName.equals(columnId))
				{
					return true;
				}

			}
		}
		finally
		{
			SQLToolbox.close(rcdFmtResultSet);
			SQLToolbox.close(rcdFmtStatement);

		}
		return false;
	}

	/**
	 * This method will check if the current application server is Tomcat.
	 * 
	 * @return true if the current application server is Tomcat.
	 */
	public static boolean isATomcatApplicationServer()
	{
		String server = EquationCommonContext.getConfigProperty("eq.application.server");
		return server.trim().equals("TOMCAT");
	}

	/**
	 * This method will check if the current application server is WebSphere.
	 * 
	 * @return true if the current application server is WebSphere.
	 */
	public static boolean isAWebSphereApplicationServer()
	{
		String server = EquationCommonContext.getConfigProperty("eq.application.server");
		return server.trim().equals("WAS");
	}

	/**
	 * This method will check if the current application server is JBOSS.
	 * 
	 * @return true if the current application server is JBOSS.
	 */
	public static boolean isAJBossApplicationServer()
	{
		String server = EquationCommonContext.getConfigProperty("eq.application.server");
		return server.trim().equals("JBOSS");
	}

	/**
	 * This method will compose and invoke a call to WebSphere MBeans via JMX. This method is intended to perform default invocation
	 * for the MBean action. It does not specify any custom parameter for the actions.
	 * 
	 * @param host
	 *            - server name of the machine hosting the Websphere Application Server
	 * @param port
	 *            - SOAP port number of the Websphere server instance.
	 * @param mBeanType
	 *            - MBean Type (eg., DataSource )
	 * @param mBeanName
	 *            - registered name of the MBean.
	 * @param action
	 *            - name of action to be invoked for the MBean
	 * @return true if the method call completed with no exception encountered.
	 */
	@SuppressWarnings("unchecked")
	public static boolean sendJMXWebSphereMBeanRequest(String host, String port, String mBeanType, String mBeanName, String action)
	{
		Properties clientProps = new Properties();
		clientProps.setProperty(AdminClient.CONNECTOR_TYPE, AdminClient.CONNECTOR_TYPE_SOAP);
		clientProps.setProperty(AdminClient.CONNECTOR_HOST, host);
		clientProps.setProperty(AdminClient.CONNECTOR_PORT, port);
		clientProps.setProperty(AdminClient.CONNECTOR_SECURITY_ENABLED, "false");

		AdminClient adminClient = null;
		ObjectName queryName = null;
		Set<ObjectName> objectNames = null;
		ObjectName obj = null;

		try
		{
			adminClient = AdminClientFactory.createAdminClient(clientProps);
			String query = String.format("WebSphere:type=%s,name=%s,*", mBeanType, mBeanName);
			queryName = new ObjectName(query);
			objectNames = adminClient.queryNames(queryName, null);
			if (!objectNames.isEmpty())
			{
				obj = objectNames.iterator().next();
				LOG.info(String.format("Invoking %s for %s of %s JMX MBean Request.", action, mBeanName, mBeanType));
				adminClient.invoke(obj, action, null, null);
			}
			else
			{
				LOG.error(String.format("%s mbean with name %s not found while sending JMX MBean Request.", mBeanType, mBeanName));
				return false;
			}
		}
		catch (Exception e)
		{
			LOG.error("Exception encountered while sending JMX MBean Request.", e);
			e.printStackTrace();
			return false;
		}
		finally
		{

			if (obj != null)
			{
				obj = null;
			}
			if (objectNames != null)
			{
				objectNames = null;
			}
			if (queryName != null)
			{
				queryName = null;
			}
			if (adminClient != null)
			{
				adminClient = null;
			}
		}

		return true;
	}
}