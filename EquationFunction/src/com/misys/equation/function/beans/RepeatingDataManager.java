package com.misys.equation.function.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.runtime.FunctionMessages;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;

/**
 * Manages an set of repeating data
 * <p>
 * There is currently one instance of this class per FunctionData instance (only one set of repeating data supported).
 */
public class RepeatingDataManager
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RepeatingDataManager.java 10275 2011-01-17 14:55:18Z MACDONP1 $";

	// Log
	private static final EquationLogger LOG = new EquationLogger(RepeatingDataManager.class);

	/** Repeating data field information */
	private Map<String, FieldDefinition> fieldDefinitions;

	/** Repeating data */
	private List<RepeatingDataRow> datas = new ArrayList<RepeatingDataRow>();

	/** Current selected row */
	private RepeatingDataRow currentRow = null;
	private int currentRowNumber = -1;

	/** Id of the repeating data group */
	private String id;

	// This is the column that contains the unit for a row
	private String unitMnemonicField;

	// This is the column that contains the system for a row
	private String systemIdField;

	/** Delimiter that separates the field id from the row number (e.g. A_HLD$003) */
	public final static String INDEX_DELIMITER = "$";
	public final static int LIST_INDEX_LEN = 5;

	/**
	 * Construct empty Repeating Data Manager
	 * 
	 */
	public RepeatingDataManager()
	{
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            The unique id of this repeating group
	 */
	public RepeatingDataManager(String id)
	{
		setId(id);
	}

	/**
	 * Initialise with a list of field definitions
	 * 
	 * @param fieldDefinitions
	 *            - the field definition
	 */
	public void initialise(LinkedHashMap<String, FieldDefinition> fieldDefinitions)
	{
		this.fieldDefinitions = fieldDefinitions;
	}

	/**
	 * Clears current data
	 */
	public void clear()
	{
		datas = new ArrayList<RepeatingDataRow>();
		setRow(-1);
	}

	/**
	 * Create a new field default empty row
	 */
	private RepeatingDataRow createNewRowData()
	{
		RepeatingDataRow row = new RepeatingDataRow();
		for (Entry<String, FieldDefinition> entry : fieldDefinitions.entrySet())
		{
			row.addFieldValue(entry.getValue().getFieldName(), new FieldValues());
		}
		return row;
	}

	/**
	 * Adds a row of data to the collection.
	 * <p>
	 * This is used when initially populating from an API
	 * 
	 * @return the row number (zero based)
	 */
	public int addRow()
	{
		datas.add(createNewRowData());
		setRow(datas.size() - 1);
		return currentRowNumber;
	}

	/**
	 * Insert a row of data to the collection at the given index
	 * 
	 * @return the row number (zero based)
	 */
	public int addRow(int index)
	{
		datas.add(index, createNewRowData());
		setRow(index);
		return currentRowNumber;
	}

	/**
	 * Moves to the start of the the repeating data.
	 * <p>
	 * After calling this method, the next() method should be called (repeatedly) to scroll through the rows whilst checking for
	 * data.
	 */
	public void moveFirst()
	{
		setRow(-1);
	}

	/**
	 * Move to the next record
	 * 
	 * @return a boolean indicating whether there is a row of data to process
	 */
	public boolean next()
	{
		boolean result = false;
		if (currentRowNumber < (datas.size() - 1))
		{
			setRow(currentRowNumber + 1);
			result = true;
		}
		return result;
	}

	/**
	 * Move to the previous record
	 * 
	 * @return a boolean indicating whether there is a row of data to process
	 */
	public boolean prev()
	{
		boolean result = false;
		if (currentRowNumber > 0)
		{
			setRow(currentRowNumber - 1);
			result = true;
		}
		return result;
	}

	/**
	 * Move to the specified row
	 * 
	 * @param row
	 *            - Zero based row number
	 * 
	 * @return true if able to locate to the row
	 */
	public boolean setRow(int row)
	{
		currentRowNumber = row;
		currentRow = row <= -1 ? null : datas.get(currentRowNumber);
		return (currentRow != null);
	}

	/**
	 * Move to the specified row given the field id
	 * 
	 * @param fieldId
	 *            - the field id (e.g. A_HZAB$00002 which means set the row to the 2nd row)
	 * 
	 * @return true if able to locate to the row
	 */
	public boolean setRow(String fieldId)
	{
		int row = FunctionRuntimeToolbox.isRepeatingFieldName(fieldId);
		if (row > 0)
		{
			return setRow(row - 1); // zero-based
		}
		return false;
	}

	/**
	 * Obtains the FieldValues for the specified field in the row
	 * 
	 * @param row
	 *            - Zero based row number
	 * @param fieldName
	 *            The name of the field
	 * @return The FieldValues instance, or null if either there is no current row or the field is not in the set of repeating data
	 */
	public FieldValues getFieldValues(int row, String fieldName)
	{
		RepeatingDataRow crow = row <= -1 ? null : datas.get(currentRowNumber);
		return crow == null ? null : crow.rtvFieldValue(fieldName);
	}

	/**
	 * Obtains the FieldValues for the specified field in the current row
	 * 
	 * @param fieldName
	 *            The name of the field
	 * @return The FieldValues instance, or null if either there is no current row or the field is not in the set of repeating data
	 */
	public FieldValues getFieldValues(String fieldName)
	{
		return currentRow == null ? null : currentRow.rtvFieldValue(fieldName);
	}

	/**
	 * Set the field input value for the specified field in the current row
	 * 
	 * @param fieldName
	 *            The name of the field to update
	 * @param inputValue
	 *            - field input value
	 */
	public void setInputValue(String fieldName, String inputValue)
	{
		FieldValues fieldValues = getFieldValues(fieldName);
		if (fieldValues != null)
		{
			fieldValues.setInputValue(inputValue);
		}
	}

	/**
	 * Return the input value for the specified field in the current row
	 * 
	 * @param fieldName
	 *            The name of the field
	 * 
	 * @return the field input value
	 */
	public String getInputValue(String fieldName)
	{
		String result = "";
		FieldValues fieldValues = getFieldValues(fieldName);
		if (fieldValues != null)
		{
			result = fieldValues.getInputValue();
		}
		if (result == null)
		{
			result = "";
		}
		return result;
	}

	/**
	 * Set the field output value for the specified field in the current row
	 * 
	 * @param fieldName
	 *            The name of the field to update
	 * @param outputValue
	 *            - field output value
	 */
	public void setOutputValue(String fieldName, String outputValue)
	{
		FieldValues fieldValues = getFieldValues(fieldName);
		if (fieldValues != null)
		{
			fieldValues.setOutputValue(outputValue);
		}
	}

	/**
	 * Return the field output value for the specified field in the current row
	 * 
	 * @param fieldName
	 *            The name of the field
	 * @return the field output value
	 */
	public String getOutputValue(String fieldName)
	{
		String result = "";
		FieldValues fieldValues = getFieldValues(fieldName);
		if (fieldValues != null)
		{
			result = fieldValues.getOutputValue();
		}
		if (result == null)
		{
			result = "";
		}
		return result;
	}

	/**
	 * Set the field DB value for the specified field in the current row
	 * 
	 * @param fieldName
	 *            The name of the field to update
	 * @param dbValue
	 *            - field DB value
	 */
	public void setDbValue(String fieldName, String dbValue)
	{
		FieldValues fieldValues = getFieldValues(fieldName);
		if (fieldValues != null)
		{
			fieldValues.setDbValue(dbValue);
		}
	}

	/**
	 * Return the field DB value for the specified field in the current row
	 * 
	 * @param fieldName
	 *            The name of the field
	 * @return the field DB value
	 */
	public String getDbValue(String fieldName)
	{
		String result = "";
		FieldValues fieldValues = getFieldValues(fieldName);
		if (fieldValues != null)
		{
			result = fieldValues.getDbValue();
		}
		if (result == null)
		{
			result = "";
		}
		return result;
	}

	/**
	 * Determine if value has been set to the description in the current row
	 * 
	 * @return true if value has been set to the description
	 */
	public boolean isShowDescAsValue(String fieldName)
	{
		FieldValues fieldValues = getFieldValues(fieldName);
		if (fieldValues != null)
		{
			return fieldValues.isShowDescAsValue();
		}
		else
		{
			return false;
		}
	}

	/**
	 * Set if value has been set to the description in the current row
	 * 
	 * @param showDescAsValue
	 *            - true if value has been set to the description
	 */
	public void setShowDescAsValue(String fieldName, boolean showDescAsValue)
	{
		FieldValues fieldValues = getFieldValues(fieldName);
		if (fieldValues != null)
		{
			fieldValues.setShowDescAsValue(showDescAsValue);
		}
	}

	/**
	 * Determine if the field cannot be changed
	 * 
	 * @return true if the field cannot be changed
	 */
	public boolean isLocked(String fieldName)
	{
		FieldValues fieldValues = getFieldValues(fieldName);
		if (fieldValues != null)
		{
			return fieldValues.isLocked();
		}
		else
		{
			return false;
		}
	}

	/**
	 * Set if the field cannot be changed
	 * 
	 * @param locked
	 *            - true if the field cannot be changed
	 */
	public void setLocked(String fieldName, boolean locked)
	{
		FieldValues fieldValues = getFieldValues(fieldName);
		if (fieldValues != null)
		{
			fieldValues.setLocked(locked);
		}
	}

	/**
	 * Return the text orientation
	 * 
	 * @return the text orientation
	 */
	public int getOrientation(String fieldName)
	{
		FieldValues fieldValues = getFieldValues(fieldName);
		if (fieldValues != null)
		{
			return fieldValues.getOrientation();
		}
		else
		{
			return FieldValues.ORIENT_NOT_SET;
		}
	}

	/**
	 * Set the text orientation
	 * 
	 * @param orientation
	 *            - the text orientation
	 */
	public void setOrientation(String fieldName, int orientation)
	{
		FieldValues fieldValues = getFieldValues(fieldName);
		if (fieldValues != null)
		{
			fieldValues.setOrientation(orientation);
		}
	}

	/**
	 * Return the field definitions
	 * 
	 * @return the fieldDefinitions - the field definitions
	 */
	public Map<String, FieldDefinition> getFieldDefinitions()
	{
		return fieldDefinitions;
	}

	/**
	 * Set the field definitions
	 * 
	 * @param fieldDefinitions
	 *            - the fieldDefinitions to set
	 */
	public void setFieldDefinitions(Map<String, FieldDefinition> fieldDefinitions)
	{
		this.fieldDefinitions = fieldDefinitions;
	}

	/**
	 * Add a the field definition
	 * 
	 * @param fieldDefinitions
	 *            - the fieldDefinitions to set
	 */
	public void addFieldDefinition(String id, FieldDefinition fieldDefinition)
	{
		this.fieldDefinitions.put(id, fieldDefinition);
	}

	/**
	 * Return all the data rows
	 * 
	 * @return all the data rows
	 */
	public List<RepeatingDataRow> getDatas()
	{
		return datas;
	}

	/**
	 * Set the data row
	 * 
	 * @param data
	 *            - the data rows
	 */
	public void setDatas(List<RepeatingDataRow> data)
	{
		this.datas = data;
	}

	/**
	 * Add a data
	 * 
	 * @param data
	 *            - the data row
	 */
	public void addData(RepeatingDataRow data)
	{
		this.datas.add(data);
	}

	/**
	 * Return the String representation
	 * 
	 * @return the String representation
	 */
	@Override
	public String toString()
	{
		// string buffer
		StringBuilder buffer = new StringBuilder();
		buffer.append(id);
		buffer.append("\n");
		for (RepeatingDataRow values : datas)
		{
			Iterator<String> iter = values.rtvFieldSet().iterator();
			while (iter.hasNext())
			{
				String fieldName = iter.next();
				FieldValues fieldValues = values.rtvFieldValue(fieldName);
				buffer.append(fieldName + ":" + fieldValues.toString() + ", ");
			}
			buffer.append("\n");
		}

		return buffer.toString();
	}

	/**
	 * Return the number of rows
	 * 
	 * @return the number of rows
	 */
	public int totalRows()
	{
		return datas.size();
	}

	/**
	 * Return the current row number
	 * 
	 * @return the current row number
	 */
	public int currentRowNumber()
	{
		return currentRowNumber;
	}

	/**
	 * Return the current row number
	 * 
	 * @return the current row number
	 */
	public boolean chkSelectedRow()
	{
		return (currentRow != null);
	}

	/**
	 * Remove all messages
	 * 
	 */
	public void clearMessages()
	{
		for (RepeatingDataRow values : datas)
		{
			Iterator<String> iter = values.rtvFieldSet().iterator();
			while (iter.hasNext())
			{
				String fieldName = iter.next();
				FieldValues fieldValues = values.rtvFieldValue(fieldName);
				fieldValues.clearMessages();
			}
		}
	}

	/**
	 * Remove all messages lower than the specified message severity
	 * 
	 * @param msgSev
	 *            - message severity
	 */
	public void clearMessages(int msgSev)
	{
		for (RepeatingDataRow values : datas)
		{
			Iterator<String> iter = values.rtvFieldSet().iterator();
			while (iter.hasNext())
			{
				String fieldName = iter.next();
				FieldValues fieldValues = values.rtvFieldValue(fieldName);

				int mSev = fieldValues.getMsgSev();
				if (mSev != FunctionMessages.MSG_NONE && mSev < msgSev)
				{
					fieldValues.clearMessages();
				}
			}
		}
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Copies a row of repeating data into the specified Function Data
	 * 
	 * @param functionData
	 *            - the function data to copy to
	 * @param rowIndex
	 *            - the row index to copy (zero based)
	 * 
	 * @return true if successfully copied the row
	 */
	public boolean copyRepeatingDataTo(FunctionData functionData, int rowIndex)
	{
		RepeatingDataRow row = datas.get(rowIndex);

		for (String fieldName : row.rtvFieldSet())
		{
			if (!fieldName.startsWith(FunctionData.FLD_SEL))
			{
				FieldValues sourceFieldValues = row.rtvFieldValue(fieldName);
				FieldData fieldData = functionData.rtvFieldData(fieldName);
				fieldData.copyFieldValues(sourceFieldValues);
				fieldData.setFunctionMessages(sourceFieldValues.getFunctionMessages());
			}
		}

		// successfully copied a row
		return true;
	}

	/**
	 * Saves a repeating data from the specified function data into the row
	 * 
	 * @param functionData
	 *            - the function data to copy from
	 * @param rowIndex
	 *            - the row index to save to (zero based)
	 * 
	 * @return true if successfully save to the row
	 */
	public boolean saveRepeatingData(FunctionData functionData, int rowIndex)
	{
		RepeatingDataRow row = datas.get(rowIndex);

		for (String fieldName : row.rtvFieldSet())
		{
			if (!fieldName.startsWith(FunctionData.FLD_SEL))
			{
				FieldValues targetFieldValues = row.rtvFieldValue(fieldName);
				FieldData fieldData = functionData.rtvFieldData(fieldName);
				targetFieldValues.copyFieldValues(fieldData);
				targetFieldValues.setFunctionMessages(fieldData.getFunctionMessages());
			}
		}

		// successfully save
		return true;
	}

	/**
	 * Delete a row
	 * 
	 * @param rowIndex
	 *            - the row to delete (zero based)
	 * 
	 * @return true if deleted successfully
	 */
	public boolean deleteRow(int rowIndex)
	{
		Object obj = datas.remove(rowIndex);
		return (obj != null);
	}

	/**
	 * Duplicate the data of the Repeating Manager
	 * 
	 * @return a duplicate of this repeating manager
	 */
	public RepeatingDataManager duplicate()
	{
		RepeatingDataManager copyManager = new RepeatingDataManager(id);

		// no need to duplicate field def as it should be the same and should never be changed
		copyManager.initialise((LinkedHashMap<String, FieldDefinition>) getFieldDefinitions());

		// loop through the row and copy
		for (RepeatingDataRow row : datas)
		{
			copyManager.addRow();
			Iterator<String> iter = row.rtvFieldSet().iterator();
			while (iter.hasNext())
			{
				String fieldName = iter.next();
				FieldValues fieldValue = row.rtvFieldValue(fieldName);
				copyManager.currentRow.addFieldValue(fieldName, new FieldValues(fieldValue));
			}
		}

		return copyManager;
	}

	/**
	 * Return the root selection option id for the repeating group
	 * 
	 * @return the root selection option id for the repeating group
	 */
	public String rtvSelectionOptionId()
	{
		return FunctionData.FLD_SEL + id;
	}

	/**
	 * Return the root selection option id for the repeating group
	 * 
	 * @param repeatingGroupId
	 *            - the repeating group id
	 * 
	 * @return the root selection option id for the repeating group
	 */
	public static String rtvSelectionOptionId(String repeatingGroupId)
	{
		return FunctionData.FLD_SEL + repeatingGroupId;
	}

	/**
	 * Check if the given details already exists
	 * 
	 * @param keyValue
	 *            - the keys (e.g. 0000:000001)
	 * @param keyFields
	 *            - the keys (e.g. HZAB:HZAN)
	 * @param sourceIndex
	 *            - the source index to be excluded from the checking. This is to allow checking of duplicate for existing details
	 * 
	 * @return the index that causing the duplicate
	 */
	public int chkUniqueKey(String keyValues, String keyFields, int sourceIndex)
	{
		// no keys, then not duplicate
		if (keyFields.trim().length() == 0)
		{
			return -1;
		}

		String[] list = keyFields.split(Toolbox.CONTEXT_DELIMETER);
		for (int rowIndex = 0; rowIndex < datas.size(); rowIndex++)
		{
			if (rowIndex != sourceIndex)
			{
				// get the row and construct the list of value
				RepeatingDataRow row = datas.get(rowIndex);
				FieldValues fieldValue;
				StringBuilder buffer = new StringBuilder();
				for (String element : list)
				{
					fieldValue = row.rtvFieldValue(element);
					buffer.append(fieldValue.getDbValue());
					buffer.append(Toolbox.CONTEXT_DELIMETER);
				}
				buffer.setLength(buffer.length() - 1);

				// compare against the key values
				if (keyValues.equals(buffer.toString()))
				{
					return rowIndex;
				}
			}
		}
		return -1;
	}

	/**
	 * Return the first field name that is different between the data
	 * 
	 * @param comparisonData
	 *            - true if the Function Data is the same
	 * 
	 * @return the field name (and row number - 1-based) that causes the comparison to fail
	 */
	public String equalFd(InputFieldSet inputFieldSet, RepeatingDataManager comparisonDataManager)
	{
		// list to contain all fields names belonging to this repeating group
		LinkedList<String> inputFieldNames = null;

		// compare each row?
		for (int rowIndex = 0; rowIndex < datas.size(); rowIndex++)
		{
			RepeatingDataRow data1Row = datas.get(rowIndex);

			// ensure this row also exists
			RepeatingDataRow data2Row = null;
			if (rowIndex < comparisonDataManager.datas.size())
			{
				data2Row = comparisonDataManager.datas.get(rowIndex);
			}
			else
			{
				return LanguageResources.getFormattedString("RepeatingDataManager.BeforeImageHasMoreRow", new String[] { id,
								Toolbox.leftZeroPad(rowIndex + 1, LIST_INDEX_LEN) });
			}

			// get all the field names for this repeating group
			if (inputFieldNames == null)
			{
				inputFieldNames = new LinkedList<String>();
				for (InputField inputField : inputFieldSet.getInputFields())
				{
					if (inputField.getRepeatingGroup().equals(id))
					{
						inputFieldNames.add(inputField.getId());
					}
				}
			}

			// loop through all the field for this repeating group
			for (String fieldName : inputFieldNames)
			{
				FieldValues afd = data1Row.rtvFieldValue(fieldName);
				FieldValues bfd = data2Row.rtvFieldValue(fieldName);

				// comparison field data does not contain the field
				if (bfd == null)
				{
					return fieldName + INDEX_DELIMITER + Toolbox.leftZeroPad(rowIndex + 1, LIST_INDEX_LEN);
				}

				// check input field value
				if (!afd.getInputValue().equals(bfd.getInputValue()))
				{
					return fieldName + INDEX_DELIMITER + Toolbox.leftZeroPad(rowIndex + 1, LIST_INDEX_LEN);
				}

				// check database field value
				if (!afd.getDbValue().equals(bfd.getDbValue()))
				{
					return fieldName + INDEX_DELIMITER + Toolbox.leftZeroPad(rowIndex + 1, LIST_INDEX_LEN);
				}
			}
		}

		// if this is different, then the comparision data manager has more rows
		if (datas.size() != comparisonDataManager.datas.size())
		{
			return LanguageResources.getFormattedString("RepeatingDataManager.BeforeImageHasLessRow",
							new String[] { id, Toolbox.leftZeroPad(datas.size() + 1, LIST_INDEX_LEN) });
		}

		return "";
	}

	/**
	 * Copy the content of a data manager
	 * 
	 * @param sourceDataManager
	 *            - the data manager to copy from
	 */
	public void reSyncData(RepeatingDataManager sourceDataManager)
	{
		// get all the row from the source data manager
		for (RepeatingDataRow row : sourceDataManager.datas)
		{
			// add a row
			this.addRow();

			// copy the content
			Iterator<String> iter = this.currentRow.rtvFieldSet().iterator();
			while (iter.hasNext())
			{
				String fieldName = iter.next();
				FieldValues sourceFieldValue = row.rtvFieldValue(fieldName);
				if (sourceFieldValue != null)
				{
					FieldValues fieldValue = this.currentRow.rtvFieldValue(fieldName);
					fieldValue.copyFieldValues(sourceFieldValue);
					fieldValue.setFunctionMessages(sourceFieldValue.getFunctionMessages());
				}
			}
		}
	}

	/**
	 * Search the specified value from the data
	 * 
	 * @param contextFields
	 *            - the fields to be validated
	 * @param contextValues
	 *            - the values
	 * @param inputValue
	 *            - compare against input value (true) or db value (false)
	 * @param functionData
	 *            - the Function data to access non-repeating fields
	 * 
	 * @return the row index that satisfies the condition
	 */
	public int search(String contextFields, String contextValues, boolean inputValue, FunctionData functionData) throws EQException
	{
		return search(contextFields, contextValues, inputValue, 0, functionData);
	}

	/**
	 * Search the specified value from the data starting from the given row index
	 * 
	 * @param contextFields
	 *            - the fields to be validated
	 * @param contextValues
	 *            - the values
	 * @param inputValue
	 *            - compare against input value (true) or db value (false)
	 * @param startingIndex
	 *            - start row to check
	 * @param functionData
	 *            - the Function data to access non-repeating fields
	 * 
	 * @return the row index that satisfies the condition
	 */
	public int search(String contextFields, String contextValues, boolean inputValue, int startingIndex, FunctionData functionData)
					throws EQException
	{
		String[] arrContextFields = contextFields.split(Toolbox.CONTEXT_DELIMETER);
		String[] arrContextValues = contextValues.split(Toolbox.CONTEXT_DELIMETER);
		for (int rowIndex = startingIndex; rowIndex < datas.size(); rowIndex++)
		{
			RepeatingDataRow values = datas.get(rowIndex);

			boolean equal = true;
			for (int j = 0; j < arrContextFields.length; j++)
			{
				String value = "";
				String fieldName = arrContextFields[j].trim();
				FieldValues fieldValue = values.rtvFieldValue(fieldName);
				if (fieldValue == null)
				{
					FieldData fieldData = functionData.rtvFieldData(fieldName);
					if (fieldData == null)
					{
						throw new EQException(LanguageResources.getFormattedString("RepeatingDataManager.LoadJoinInputKeyNotFound",
										fieldName));
					}
					else
					{
						value = inputValue ? fieldData.getInputValue() : fieldData.getDbValue();
					}
				}
				else
				{
					value = inputValue ? fieldValue.getInputValue() : fieldValue.getDbValue();
				}
				equal = value.equals(arrContextValues[j]);
				if (!equal)
				{
					break;
				}
			}
			if (equal)
			{
				return rowIndex;
			}
		}
		return -1;
	}

	/**
	 * Checks whether the specified field does actually exist in this RepeatingDataManager. If it does not, then an error is logged
	 * <p>
	 * This method is called from the UserRepeatingDataManager to assist with troubleshooting user exit code
	 * 
	 * @param fieldName
	 *            Name of the field to check.
	 */
	public void checkFieldExists(String fieldName)
	{
		if (fieldDefinitions != null && !fieldDefinitions.containsKey(fieldName))
		{
			LOG.error("Field [" + fieldName + "] does not exist");
		}
	}

	/**
	 * Set the field column that identifies the unit mnemonic where the record belongs to
	 * 
	 * @param unitMnemonicField
	 *            - the field column that identifies the unit mnemonic where the record belongs to
	 */
	public void setGPUnitMnemonicField(String unitMnemonicField)
	{
		this.unitMnemonicField = unitMnemonicField;
	}

	/**
	 * Set the field column that identifies the system ID where the record belongs to
	 * 
	 * @param systemIdField
	 *            - the field column that identifies the system ID where the record belongs to
	 */
	public void setGPSystemIdField(String systemIdField)
	{
		this.systemIdField = systemIdField;
	}

	/**
	 * Return the field column that identifies the unit mnemonic where the record belongs to
	 * 
	 * @return the field column that identifies the unit mnemonic where the record belongs to
	 */
	public String getGPUnitMnemonicField()
	{
		return unitMnemonicField;
	}

	/**
	 * Return the unit mnemonic where the current record belongs to
	 * 
	 * @return the unit mnemonic where the current record belongs to
	 */
	public String rtvGPUnit()
	{
		return getDbValue(unitMnemonicField);
	}

	/**
	 * Return the system ID where the current record belongs to
	 * 
	 * @return the system ID where the current record belongs to
	 */
	public String rtvGPSystem()
	{
		return getDbValue(systemIdField);
	}

	/**
	 * Return the details as specified by the context fields
	 * 
	 * @param contextFields
	 *            - the context fields
	 * 
	 * @return the list of record containing the data specified by the context fields
	 */
	public List<String> rtvDataAsList(String contextFields)
	{
		ArrayList<String> list = new ArrayList<String>();
		String[] arrContextFields = contextFields.split(Toolbox.CONTEXT_DELIMETER);
		for (int i = 0; i < datas.size(); i++)
		{
			RepeatingDataRow values = datas.get(i);
			StringBuffer value = new StringBuffer();
			for (String contextField : arrContextFields)
			{
				FieldValues fieldValue = values.rtvFieldValue(contextField);
				if (fieldValue != null)
				{
					FieldDefinition fieldDefinition = fieldDefinitions.get(contextField);
					if (EqDataType.isNumeric(fieldDefinition.getFieldType()))
					{
						boolean negative = false;
						String str = fieldValue.getDbValue();
						if (str.indexOf("-") >= 0)
						{
							str = str.replaceAll("-", "");
							negative = true;
						}

						str = Toolbox.leftPad(str, "0", fieldDefinition.getFieldLength());

						// now, add some control character at the front to make negative numbers appear first
						if (negative)
						{
							str = "0" + Toolbox.negate(str);
						}
						else
						{
							str = "1" + str;
						}

						value.append(str);
					}
					else
					{
						value.append(Toolbox.pad(fieldValue.getDbValue(), fieldDefinition.getFieldLength()));
					}
				}
			}
			value.append(EqDataType.GLOBAL_DELIMETER + Toolbox.leftZeroPad(i, LIST_INDEX_LEN));
			list.add(value.toString());
		}
		return list;
	}

}