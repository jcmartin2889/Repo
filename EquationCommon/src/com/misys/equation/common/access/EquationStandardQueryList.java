package com.misys.equation.common.access;

import java.math.BigDecimal;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.CachedRowSet;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.utilities.EquationLogger;

/**
 * This class provides methods for constructing SQL statements and retrieving values from the result set returned - multiple row
 * results is supported.
 */
public class EquationStandardQueryList implements IEquationStandardObject, IListTransaction
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationStandardQueryList.java 15070 2012-12-18 18:50:51Z williae1 $";

	/** Serialisation UID */
	private static final long serialVersionUID = 1L;

	/** Logger for this class */
	private static final EquationLogger LOG = new EquationLogger(EquationStandardQueryList.class);

	/** Prefix for the query fields */
	public static final String PARM_PREFIX = "PARAM";

	private String id = "";
	private final Map<String, String> keys = new HashMap<String, String>();

	private CachedRowSet rowSet;
	private byte[] beforeImage;
	private boolean valid = true;

	private final String sql;

	/** List of messages (not intended to change) */
	private final List<EQMessage> errorList = new ArrayList<EQMessage>();

	private boolean complete;
	private int recordLength;
	private int recordCount;

	public Map<String, String> getKeys()
	{
		return keys;
	}

	public String getSql()
	{
		return sql;
	}

	public void setRowSet(CachedRowSet rowSet)
	{
		this.rowSet = rowSet;
	}

	/**
	 * Construct a standard query list
	 * 
	 * @param sql
	 *            - The SQL query
	 * @param equationStandardSession
	 *            - the Equation standard session
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public EquationStandardQueryList(String sql, EquationStandardSession equationStandardSession) throws EQException
	{
		this.sql = sql;
	}

	/**
	 * Set the field name
	 * 
	 * @param fieldName
	 *            - field name
	 * @param fieldValue
	 *            - field value
	 * @equation.external
	 * 
	 */
	public void setFieldValue(String fieldName, String fieldValue)
	{
		// Read only
		keys.put(fieldName, fieldValue);
	}

	/**
	 * Set the field values in bytes
	 * 
	 * @param bytes
	 *            - the table fields in bytes
	 */
	public void setBytes(byte[] bytes)
	{
		// read only
	}

	/**
	 * Return the field values in bytes
	 * 
	 * @return the field values in bytes
	 */
	public byte[] getBytes()
	{
		// TODO:
		return null;
	}

	/**
	 * Return the String representation
	 * 
	 * @return the String representation
	 */
	@Override
	public String toString()
	{
		StringBuffer serialisation = new StringBuffer();

		// add the content of the keys
		serialisation.append(keys);

		// add the row set
		if (rowSet != null)
		{
			serialisation.append(rowSet.toString());
		}
		return serialisation.toString();
	}

	/**
	 * Return the field value
	 * 
	 * @param fieldName
	 *            - field name in GZ file
	 * @equation.external
	 */
	public String getFieldValue(String fieldName)
	{
		String result = "";
		try
		{
			// This method will be also called prior to SETTING key details, to
			// determine if a change has been made. Make sure that this is 'safe'
			if (keys.containsKey(fieldName))
			{
				result = keys.get(fieldName);
			}
			else
			{
				if (rowSet != null)
				{
					int type = getFieldNameType(fieldName);
					if (type == Types.CHAR)
					{
						result = rowSet.getString(fieldName);
					}

					else if (type == Types.DECIMAL || type == Types.FLOAT || type == Types.DOUBLE || type == Types.REAL)
					{
						BigDecimal bd = rowSet.getBigDecimal(fieldName);
						if (bd != null)
							result = bd.toPlainString();
						else
							result = String.valueOf(0);

					}

					else if (type == Types.TINYINT || type == Types.BIGINT || type == Types.NUMERIC || type == Types.INTEGER
									|| type == Types.SMALLINT)
					{
						result = String.valueOf(rowSet.getLong(fieldName));
					}

					else
					{
						result = rowSet.getString(fieldName);
					}
				}
			}
		}
		catch (SQLException e)
		{
			LOG.error(e);
		}
		return result;
	}

	/**
	 * Return the the before image
	 * 
	 * @return the before image
	 */
	public byte[] getBeforeImage()
	{
		return beforeImage;
	}

	public void load(EquationStandardSession session)
	{
	}

	/**
	 * Set the before image
	 * 
	 * @param beforeImage
	 *            the before image
	 */
	public void setBeforeImage(byte[] beforeImage)
	{

		if (beforeImage == null)
		{
			this.beforeImage = null;
		}
		else
		{
			this.beforeImage = new byte[beforeImage.length];
			System.arraycopy(beforeImage, 0, this.beforeImage, 0, beforeImage.length);
		}
	}

	/**
	 * Return the next record
	 * 
	 * @return true if record has been read
	 * @equation.external
	 */
	public boolean next()
	{
		try
		{
			return rowSet.next();
		}
		catch (SQLException e)
		{
			LOG.error(e);
		}
		return false;
	}

	/**
	 * Move to the first record
	 * 
	 * @equation.external
	 */
	public void moveFirst()
	{
		try
		{
			rowSet.beforeFirst();
		}
		catch (SQLException e)
		{
			LOG.error(e);
		}
	}

	/**
	 * Return the id
	 * 
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Return the list of messages
	 * 
	 * @return the list of messages
	 * @equation.external
	 */
	public List<EQMessage> getMessages()
	{
		// TODO Shouldn't the caller be prepared to handle nulls?
		return errorList;
	}

	/**
	 * Return the mode - This currently supports enquiry only, thus always in retrieval mode
	 * 
	 * @return the mode
	 */
	public String getMode()
	{
		return IEquationStandardObject.FCT_RTV;
	}

	/**
	 * Determine whether the transaction is valid
	 * 
	 * @return true if the transaction is valid
	 * @equation.external
	 */
	public boolean getValid()
	{
		return valid;
	}

	/**
	 * Set the id
	 * 
	 * @param id
	 *            - the transaction id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Set the mode - This currently supports enquiry only, thus always in retrieval mode
	 * 
	 * @param mode
	 *            - the mode
	 */
	public void setMode(String mode)
	{
		// unused
	}

	/**
	 * Set whether transaction is valid
	 * 
	 * @param valid
	 *            - true if transaction is valid
	 */
	public void setValid(boolean valid)
	{
		this.valid = valid;
	}

	/**
	 * Return the column index of a field name
	 * 
	 * @param fieldName
	 *            - the field name to locate
	 * 
	 * @return the column index
	 * 
	 * @throws SQLException
	 */
	private int getFieldNameIndex(String fieldName) throws SQLException
	{
		ResultSetMetaData meteData = rowSet.getMetaData();
		int count = meteData.getColumnCount();
		for (int i = 1; i <= count; i++)
		{
			if (meteData.getColumnName(i).equals(fieldName))
			{
				return i;
			}
		}
		return 0;
	}

	/**
	 * Return the type of the field name
	 * 
	 * @param fieldName
	 *            - the field name
	 * 
	 * @return the field type
	 * 
	 * @throws SQLException
	 */
	private int getFieldNameType(String fieldName) throws SQLException
	{
		int index = getFieldNameIndex(fieldName);
		if (index == 0)
		{
			return Types.CHAR;
		}
		return rowSet.getMetaData().getColumnType(index);
	}

	/**
	 * Set whether the all the records have been retrieved or not. There will be scenarios where this is FALSE when trying to
	 * retrieve only the first n records to prevent memory error
	 * 
	 * @param listComplete
	 *            - true if all records have been retrieved
	 */
	public void setComplete(boolean complete)
	{
		this.complete = complete;
	}

	/**
	 * Determines whether all the records have been retrieved. There will be scenarios where this is FALSE when trying to retrieve
	 * only the first n records to prevent memory error
	 * 
	 * @return true if all records have been retrieved.
	 * @equation.external
	 */
	public boolean isComplete()
	{
		return complete;
	}

	/**
	 * Return maximum record length
	 * 
	 * @return the maximum record length
	 */
	public int getRecordLength()
	{
		return recordLength;
	}

	/**
	 * Set maximum record length
	 * 
	 * @param recordLength
	 *            - maximum record length
	 */
	public void setRecordLength(int recordLength)
	{
		this.recordLength = recordLength;
	}

	/**
	 * Return the number of records
	 * 
	 * @return the number of records
	 * @equation.external
	 */
	public int getRecordCount()
	{
		return recordCount;
	}

	/**
	 * Set the number of records
	 * 
	 * @param recordCount
	 *            - the number of records
	 */
	public void setRecordCount(int recordCount)
	{
		this.recordCount = recordCount;
	}
}
