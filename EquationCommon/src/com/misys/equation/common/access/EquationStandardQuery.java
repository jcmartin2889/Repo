package com.misys.equation.common.access;

import java.util.ArrayList;
import java.util.List;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.utilities.SQLToolbox;

/**
 * This class provides methods for constructing SQL statements and retrieving values from the result set returned - a single row
 * result is supported.
 */
public class EquationStandardQuery implements IEquationStandardObject
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationStandardQuery.java 15069 2012-12-18 18:49:02Z williae1 $";

	private static final long serialVersionUID = 1L;

	private EquationDataStructureData queryData;
	private String sql;
	private List<EQMessage> errorList = new ArrayList<EQMessage>();
	private byte[] beforeImage;
	private boolean valid = true;
	private boolean exists = false;
	private String mode = "";
	private String id = "";
	private List<String> keys = null;

	/**
	 * Construct an empty class
	 */
	public EquationStandardQuery()
	{
	}

	/**
	 * Construct a standard transaction with SQL statement
	 * 
	 * @param sql
	 *            - the SELECT SQL statement
	 * @param equationStandardSession
	 *            - the Equation standard session
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public EquationStandardQuery(String sql, EquationStandardSession equationStandardSession) throws EQException
	{
		initialiseQueryData(sql, equationStandardSession);
	}

	/**
	 * Initialise the query data
	 * 
	 * @param sql
	 *            - the SELECT SQL statement
	 * @param equationStandardSession
	 *            - the Equation standard session
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public void initialiseQueryData(String sql, EquationStandardSession equationStandardSession) throws EQException
	{
		this.sql = sql;
		EquationDataStructure eqDs = new EquationDataStructure(id, sql, equationStandardSession);
		queryData = new EquationDataStructureData(eqDs);
	}

	/**
	 * Set the table field name
	 * 
	 * @param fieldName
	 *            - field name
	 * @param fieldValue
	 *            - field value
	 * @equation.external
	 */
	public void setFieldValue(String fieldName, String fieldValue)
	{
		queryData.setFieldValue(fieldName, fieldValue);
	}

	/**
	 * Set the table fields
	 * 
	 * @param bytes
	 *            - the table fields in bytes
	 */
	public void setBytes(byte[] bytes)
	{
		queryData.setBytes(bytes);
	}

	/**
	 * Return the table fields in bytes
	 * 
	 * @return the table fields in bytes
	 */
	public byte[] getBytes()
	{
		return queryData.getBytes();
	}

	/**
	 * Return the String representation
	 * 
	 * @return the String representation
	 */
	@Override
	public String toString()
	{
		StringBuffer serialisation = new StringBuffer(queryData.toString());
		return serialisation.toString();
	}

	/**
	 * Return the field value of the GZ field
	 * 
	 * @param fieldName
	 *            - field name in GZ file
	 * @equation.external
	 */
	public String getFieldValue(String fieldName)
	{
		return queryData.getFieldValue(fieldName);
	}

	/**
	 * Return the list of errors
	 * 
	 * @return - the list of errors
	 * @equation.external
	 */
	public List<EQMessage> getErrorList()
	{
		return errorList;
	}

	/**
	 * Set the list of errors
	 * 
	 * @param errorList
	 *            - the list of errors
	 */
	public void setErrorList(List<EQMessage> errorList)
	{
		this.errorList = errorList;
	}

	/**
	 * Return the data
	 * 
	 * @return the data
	 */
	public EquationDataStructureData getQueryData()
	{
		return queryData;
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
	 * Return the list of messages
	 * 
	 * @return the list of messages
	 * @equation.external
	 */
	public List<EQMessage> getMessages()
	{
		return getErrorList();
	}

	/**
	 * Return the id of this transaction
	 * 
	 * @return the id of this transaction
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Set the id of this transaction
	 * 
	 * @param id
	 *            - the id of this transaction
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Return whether the table operation has encountered an error or not
	 * 
	 * @return true if table is valid
	 * @equation.external
	 */
	public boolean getValid()
	{
		return valid;
	}

	/**
	 * Set whether the table is valid or not
	 * 
	 * @param valid
	 *            - true if table is valid
	 */
	public void setValid(boolean valid)
	{
		this.valid = valid;
	}

	/**
	 * Set the mode
	 * 
	 * @param mode
	 *            - the function mode
	 */
	public void setMode(String mode)
	{
		this.mode = mode;
	}

	/**
	 * Return the mode
	 * 
	 * @return the mode
	 */
	public String getMode()
	{
		return mode;
	}

	/**
	 * Determine whether the record exists or not
	 * 
	 * @return true if the record exists
	 * @equation.external
	 */
	public boolean exists()
	{
		return exists;
	}

	/**
	 * Set whether the record exists or not
	 * 
	 * @param exists
	 *            - true if the record exists
	 */
	public void setExists(boolean exists)
	{
		this.exists = exists;
	}

	/**
	 * Return the SQL query
	 * 
	 * @return the SQL query
	 */
	public String getSql()
	{
		return sql;
	}

	/**
	 * Return the keys
	 * 
	 * @return the keys
	 */
	public List<String> getKeys()
	{
		return keys;
	}

	/**
	 * Set the keys
	 * 
	 * @param keys
	 *            - the keys
	 */
	public void setKeys(List<String> keys)
	{
		this.keys = keys;
	}

	/**
	 * Return the SQL query with selection
	 * 
	 * @return the SQL query with selection
	 */
	public String rtvSql()
	{
		String where = SQLToolbox.getWhere(queryData, keys);
		String sqlString = SQLToolbox.injectWhereToSQLString(sql, where);
		return sqlString;
	}

}
