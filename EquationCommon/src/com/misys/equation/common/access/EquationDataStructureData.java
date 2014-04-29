package com.misys.equation.common.access;

import java.io.CharConversionException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import com.ibm.as400.access.AS400Text;
import com.ibm.as400.access.ExtendedIllegalArgumentException;
import com.ibm.as400.access.Record;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;

/**
 * @author weddelc1
 */
public class EquationDataStructureData implements Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationDataStructureData.java 15717 2013-04-30 15:46:31Z whittap1 $";
	private static final long serialVersionUID = 1L;
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EquationDataStructureData.class);
	protected final EquationDataStructure eqDS;
	protected Record rcd = null;

	/**
	 * constructor
	 */
	public EquationDataStructureData(EquationDataStructure eqDS)
	{
		this.eqDS = eqDS;
		try
		{
			rcd = new Record(eqDS.getRcdFmt(), eqDS.getInitialBytes());
		}
		catch (UnsupportedEncodingException e)
		{
			LOG.error(e);
		}
	}

	/**
	 * Constructor to create the data structure data from a result set
	 * 
	 * @param eqDs
	 *            - the Equation data structure
	 * @param rcdRmtResultSet
	 *            - the result set
	 * 
	 */
	public EquationDataStructureData(EquationDataStructure eqDS, ResultSet rcdRmtResultSet) throws EQException
	{
		try
		{
			this.eqDS = eqDS;

			// default to initial bytes
			rcd = new Record(eqDS.getRcdFmt(), eqDS.getInitialBytes());

			// set the data
			setResultSet(rcdRmtResultSet);
		}
		catch (UnsupportedEncodingException e)
		{
			LOG.error(e);
			throw new EQException("EquationDataStructureData: Constructor Failed: " + Toolbox.getExceptionMessage(e), e);
		}
	}

	/**
	 * Set the field value
	 * 
	 * @param fieldName
	 *            - the field name
	 * @param fieldValue
	 *            - the field value
	 */
	public void setFieldValue(String fieldName, String fieldValue)
	{
		EquationFieldDefinition fieldDefinition = eqDS.getFieldDefinition(fieldName);
		if (fieldDefinition != null)
		{
			int fieldType = fieldDefinition.getFieldDataType();
			int fieldLength = fieldDefinition.getFieldLength();

			// character
			if (fieldType == Types.CHAR || fieldType == Types.BINARY || fieldType == Types.VARCHAR || fieldType == Types.VARBINARY)
			{
				if (fieldValue == null)
				{
					fieldValue = "";
				}
				rcd.setField(fieldName.toUpperCase(), Toolbox.trim(fieldValue, fieldLength));
			}

			// packed or zoned decimal
			else if (fieldType == Types.NUMERIC || fieldType == Types.DECIMAL)
			{
				// Have a bash at parsing what we've been passed as a decimal
				try
				{
					// not defined
					if (fieldValue == null)
					{
						fieldValue = "0";
					}

					// blank
					else if (fieldValue.trim().length() == 0)
					{
						fieldValue = "0";
					}

					// ensure valid decimal places
					else
					{
						fieldValue = Toolbox.removeTrailingZeroes(fieldValue, EqDataType.DECIMALSEP);
					}

					// create the big decimal
					BigDecimal fieldValueNumber = new BigDecimal(fieldValue);
					rcd.setField(fieldName.toUpperCase(), fieldValueNumber);
				}
				catch (NumberFormatException e)
				{
					LOG.warn(e);
					LOG.warn("Trying to set Field [" + fieldName + "] with value of [" + fieldValue + "]");
					rcd.setField(fieldName.toUpperCase(), new BigDecimal(0));
				}
				catch (ExtendedIllegalArgumentException e)
				{
					LOG.warn(e);
					LOG.warn("Trying to set Field [" + fieldName + "] with value of [" + fieldValue + "]");
					rcd.setField(fieldName.toUpperCase(), new BigDecimal(0));
				}
			}

			// other types treated as character
			else
			{
				if (fieldValue == null)
				{
					fieldValue = "";
				}
				rcd.setField(fieldName.toUpperCase(), Toolbox.trim(fieldValue, fieldLength));
			}
		}
		else
		{
			// If the field is not found, then log an error:
			LOG.error("Field [" + fieldName + "] not found in data structure [" + eqDS.getFormatId() + "]");
		}
	}

	/**
	 * Set the data via bytes
	 * 
	 * @param bytes
	 *            - the data bytes
	 */
	public void setBytes(byte[] bytes)
	{
		try
		{
			// Ensure that the gzData contains the correct length
			int length = getEqDS().getInitialBytes().length;
			if (bytes.length >= length)
			{
				rcd.setContents(bytes);
			}
			else
			{
				byte[] gzDataTmp = new byte[length];
				System.arraycopy(bytes, 0, gzDataTmp, 0, bytes.length);
				for (int i = bytes.length; i < length; i++)
				{
					gzDataTmp[i] = 0x40; // blank in HEX
				}
				rcd.setContents(gzDataTmp);
			}
		}
		catch (UnsupportedEncodingException e)
		{
			LOG.error(e);
		}
	}

	/**
	 * Return the data in bytes
	 * 
	 * @return the data in bytes
	 */
	public byte[] getBytes()
	{
		try
		{
			return rcd.getContents();
		}
		catch (CharConversionException e)
		{
			LOG.error(e);
			return null;
		}
		catch (UnsupportedEncodingException e)
		{
			LOG.error(e);
			return null;
		}
	}

	/**
	 * Return the Equation Data Structure
	 * 
	 * @return the Equation Data Structure
	 */
	public EquationDataStructure getEqDS()
	{
		return eqDS;
	}

	/**
	 * Return the field value of the field
	 * 
	 * @param fieldName
	 *            The name of the field
	 * 
	 * @return the field value of the field
	 * 
	 */
	public String getFieldValue(String fieldName)
	{
		EquationFieldDefinition fieldDefinition = eqDS.getFieldDefinition(fieldName);
		String fieldValue = "";
		if (!(fieldDefinition == null))
		{
			try
			{
				int fieldType = fieldDefinition.getFieldDataType();
				if (fieldType == Types.CHAR || fieldType == Types.BINARY || fieldType == Types.VARCHAR
								|| fieldType == Types.VARBINARY)
				{
					fieldValue = (String) (rcd.getField(fieldName.toUpperCase()));
				}
				else if (fieldType == Types.NUMERIC || fieldType == Types.DECIMAL)
				{
					BigDecimal fieldValueNumber = (BigDecimal) (rcd.getField(fieldName.toUpperCase()));
					fieldValue = fieldValueNumber.toPlainString();
				}
				else
				{
					fieldValue = (String) (rcd.getField(fieldName.toUpperCase()));
				}
			}
			catch (UnsupportedEncodingException e)
			{
				LOG.error("Trying to retrieve Field [" + fieldName + "]");
				LOG.error(e);
			}
			catch (NumberFormatException e)
			{
				LOG.error("Trying to retrieve Field [" + fieldName + "]");
				LOG.error(e);
			}
		}
		return fieldValue;
	}

	/**
	 * Return the field value of the field
	 * 
	 * @return the field value of the field
	 * 
	 */
	public byte[] getFieldValueBytes(String fieldName)
	{
		return rcd.getFieldAsBytes(fieldName.toUpperCase());
	}

	/**
	 * Return the String representation of the content of the data structure
	 * 
	 * @return the String representation of the content of the data structure
	 */
	@Override
	public String toString()
	{
		StringBuilder serialisation = new StringBuilder();

		for (String fieldName : eqDS.getFieldNames())
		{
			EquationFieldDefinition fd = getEqDS().getFieldDefinition(fieldName);

			String fieldValueCol = getFieldValue(fieldName).trim();
			String fieldTypeCol = fd.rtvDataTypeString();
			String fieldValueHexCol = getBytesString(fd.getFieldStartIndex(), fd.getByteLength());

			serialisation.append(fieldName.length() >= 20 ? fieldName : Toolbox.pad(fieldName, 20)).append(":").append(
							fieldValueCol.length() >= 20 ? fieldValueCol : Toolbox.pad(fieldValueCol, 20)).append(":").append(
							fieldTypeCol.length() >= 20 ? fieldTypeCol : Toolbox.pad(fieldTypeCol, 20)).append(":").append(
							fieldValueHexCol.length() >= 20 ? fieldValueHexCol : Toolbox.pad(fieldValueHexCol, 20)).append("\n");
		}
		return serialisation.toString();
	}

	/**
	 * Return the String representation of a substring of the byte array
	 * 
	 * @param start
	 *            - starting index within the array
	 * @param length
	 *            - the length
	 * 
	 * @return the String representation of a byte array
	 */
	private String getBytesString(int start, int length)
	{
		// get the byte data
		byte[] byteData = getBytes();

		// initialise the end position
		int end = start + length;
		if (byteData.length < end)
		{
			end = byteData.length;
		}

		int reqLength = end - start;
		byte[] reqBytes = new byte[reqLength];
		System.arraycopy(byteData, start, reqBytes, 0, reqLength);
		String hexString = getHex(reqBytes);
		return hexString;

	}

	static final String HEXES = "0123456789ABCDEF";
	public static String getHex(final byte[] raw)
	{
		if (raw == null)
		{
			return null;
		}
		final StringBuilder hex = new StringBuilder(2 * raw.length);
		for (final byte b : raw)
		{
			hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F))).append(" ");
		}
		return hex.toString();
	}

	/**
	 * Copy content from another Equation data structure data of the same definition
	 * 
	 * @param eqDsData
	 *            - the Equation data structure data to copy from
	 */
	public void copy(EquationDataStructureData eqDsData)
	{
		this.setBytes(eqDsData.getBytes());
	}

	/**
	 * Reset the content to the initial value
	 */
	public void reset()
	{
		this.setBytes(eqDS.getInitialBytes());
	}

	/**
	 * Set the data via ResultSet
	 * 
	 * @param rcdRmtResultSet
	 *            - the result set
	 */
	public void setResultSet(ResultSet rcdRmtResultSet) throws EQException
	{
		try
		{
			// set up the value based on the result set
			ResultSetMetaData rcdFmtResultSetMetaData = rcdRmtResultSet.getMetaData();
			int rcdFmtColumnCount = rcdFmtResultSetMetaData.getColumnCount();

			for (int i = 1; i <= rcdFmtColumnCount; i++)
			{
				int fieldType = rcdFmtResultSetMetaData.getColumnType(i);
				String fieldName = rcdFmtResultSetMetaData.getColumnName(i);

				if (rcdRmtResultSet.getBytes(i) == null)
				{
					setFieldValue(fieldName, "");
					continue;
				}

				if (fieldType == Types.DECIMAL || fieldType == Types.NUMERIC)
				{
					BigDecimal fieldValue = rcdRmtResultSet.getBigDecimal(i);
					setFieldValue(fieldName, String.valueOf(fieldValue));
				}
				else if (fieldType == Types.CHAR || fieldType == Types.VARCHAR)
				{
					// convert
					AS400Text text = new AS400Text(rcdFmtResultSetMetaData.getPrecision(i), eqDS.getCcsid());
					String fieldValue = (String) text.toObject(rcdRmtResultSet.getBytes(i));
					// String fieldValue = rcdRmtResultSet.getString(i);
					setFieldValue(fieldName, fieldValue);
				}
				else
				{
					String fieldValue = rcdRmtResultSet.getString(i);
					setFieldValue(fieldName, fieldValue);
				}
			}
		}
		catch (SQLException e)
		{
			throw new EQException(e);
		}
	}

}
