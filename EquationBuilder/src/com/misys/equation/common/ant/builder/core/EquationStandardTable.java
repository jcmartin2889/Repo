package com.misys.equation.common.ant.builder.core;

/**
 * @author weddelc1
 */
public class EquationStandardTable implements IEquationStandardObject
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationStandardTable.java 7606 2010-06-01 17:04:23Z MACDONP1 $";

	private EquationStandardSession equationStandardSession = null;
	private EquationDataStructureData tableData;
	private String tableName;
	private String indexName;
	private String keys;
	private byte[] beforeImage;
	private boolean valid = true;
	private boolean exists = false;
	private String mode = "";
	private String id = "";

	/**
	 * Construct a standard transaction with default journal name
	 * 
	 * @param tableName
	 *            - API program name + option id
	 * @param equationStandardSession
	 *            - the Equation standard session
	 * 
	 * @throws EQException
	 */
	public EquationStandardTable(String tableName, EquationStandardSession equationStandardSession)
	{
		this.equationStandardSession = equationStandardSession;
		setTableName(tableName);
		initialiseTable();
	}
	/**
	 * Construct a standard transaction with default journal name
	 * 
	 * @param tableName
	 *            - API program name + option id
	 * @param equationStandardSession
	 *            - the Equation standard session
	 * 
	 * @throws EQException
	 */
	public EquationStandardTable(String tableName, String indexName, String keys, EquationStandardSession equationStandardSession)

	{
		this.equationStandardSession = equationStandardSession;
		setTableName(tableName);
		setIndexName(indexName);
		setKeys(keys);
		initialiseTable();
	}

	/**
	 * Set the name of the table being used
	 * 
	 * @param tableName
	 *            - Table name
	 */
	private void setTableName(String tableName)
	{
		if (this.id.equals("") || this.tableName.equals(id))
		{
			id = tableName;
		}
		this.tableName = tableName;
	}

	/**
	 * Return the name of the table being used
	 * 
	 * @return the name of the table being used
	 */
	public String getTableName()
	{
		return tableName;
	}

	/**
	 * Initialise the GZ journal data structure
	 * 
	 */
	private void initialiseTable()
	{
		tableData = new EquationDataStructureData(equationStandardSession.getEquationDataStructure(tableName));
	}
	/**
	 * Set the table field name
	 * 
	 * @param fieldName
	 *            - field name
	 * @param fieldValue
	 *            - field value
	 */
	public void setFieldValue(String fieldName, String fieldValue)
	{
		tableData.setFieldValue(fieldName, fieldValue);
	}

	/**
	 * Set the table fields
	 * 
	 * @param bytes
	 *            - the table fields in bytes
	 */
	public void setBytes(byte[] bytes)
	{
		tableData.setBytes(bytes);
	}

	/**
	 * Return the table fields in bytes
	 * 
	 * @return the table fields in bytes
	 */
	public byte[] getBytes()
	{
		return tableData.getBytes();
	}

	/**
	 * Return the String representation
	 * 
	 * @return the String representation
	 */
	@Override
	public String toString()
	{
		StringBuffer serialisation = new StringBuffer(tableData.toString());
		return serialisation.toString();
	}

	/**
	 * Return the field value of the GZ field
	 * 
	 * @param fieldName
	 *            - field name in GZ file
	 */
	public String getFieldValue(String fieldName)
	{
		return tableData.getFieldValue(fieldName);
	}

	/**
	 * Return the table data
	 * 
	 * @return the table data
	 */
	public EquationDataStructureData getTableData()
	{
		return tableData;
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
		this.beforeImage = beforeImage;
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

	public boolean getValid()
	{
		return valid;
	}

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
	public String getIndexName()
	{
		return indexName;
	}
	public void setIndexName(String indexName)
	{
		this.indexName = indexName;
	}
	public String getKeys()
	{
		return keys;
	}
	public void setKeys(String keys)
	{
		this.keys = keys;
	}
	public boolean exists()
	{
		return exists;
	}
	public void setExists(boolean exists)
	{
		this.exists = exists;
	}
}
