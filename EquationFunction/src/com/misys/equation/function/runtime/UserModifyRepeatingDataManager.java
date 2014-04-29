package com.misys.equation.function.runtime;

import com.misys.equation.function.beans.RepeatingDataManager;

public class UserModifyRepeatingDataManager extends UserRepeatingDataManager
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UserModifyRepeatingDataManager.java 11346 2011-07-01 11:34:49Z ESTHER.WILLIAMS $";

	protected UserModifyData userModifyData;
	private boolean changed;

	/**
	 * Construct a User data repeating data manager
	 * 
	 * @param dataManager
	 *            - the repeating data manager
	 */
	public UserModifyRepeatingDataManager(UserModifyData userModifyData, RepeatingDataManager dataManager)
	{
		super(dataManager);
		this.userModifyData = userModifyData;
		this.changed = false;
	}

	/**
	 * Change the field input value of the specified field name
	 * 
	 * @param fieldName
	 *            - field name
	 * @param inputValue
	 *            - input value
	 * @equation.external
	 */
	public void chgFieldInputValue(String fieldName, String fieldValue)
	{
		String str = rtvFieldInputValue(fieldName);

		// not the same
		if (!str.equals(fieldValue))
		{
			this.userModifyData.chgFieldInputValue(fieldName, fieldValue);
			this.dataManager.setInputValue(fieldName, this.userModifyData.rtvFieldInputValue(fieldName));
			userModifyData.setChanged(true);
		}
	}

	/**
	 * Change the field input value of the specified field name
	 * 
	 * @param fieldName
	 *            - field name
	 * @param inputValue
	 *            - input value
	 * @equation.external
	 */
	public void chgFieldOutputValue(String fieldName, String fieldValue)
	{
		String str = rtvFieldOutputValue(fieldName);

		// not the same
		if (!str.equals(fieldValue))
		{
			this.dataManager.setOutputValue(fieldName, fieldValue);
			userModifyData.setChanged(true);
		}
	}

	/**
	 * Change the field input value of the specified field name
	 * 
	 * @param fieldName
	 *            - field name
	 * @param inputValue
	 *            - input value
	 * @equation.external
	 */
	public void chgFieldDbValue(String fieldName, String fieldValue)
	{
		String str = rtvFieldDbValue(fieldName);

		// not the same
		if (!str.equals(fieldValue))
		{
			this.userModifyData.chgFieldDbValue(fieldName, fieldValue);
			this.dataManager.setDbValue(fieldName, this.userModifyData.rtvFieldDbValue(fieldName));
			this.dataManager.setInputValue(fieldName, this.userModifyData.rtvFieldInputValue(fieldName));
			userModifyData.setChanged(true);
		}
	}

	/**
	 * Determine whether data has been changed
	 * 
	 * @return true if data has been changed
	 */
	public boolean isChanged()
	{
		return changed;
	}

	/**
	 * Set whether the data has been changed
	 * 
	 * @param changed
	 *            - true if data has been changed
	 */
	protected void setChanged(boolean changed)
	{
		this.changed = changed;
	}

	/**
	 * Adds a row of data at the end
	 * 
	 * @return the row number (1-based)
	 * @equation.external
	 */
	public int addRow()
	{
		return this.dataManager.addRow() + 1;
	}

	/**
	 * Insert a row of data on the specified index
	 * 
	 * @return the row number (1-based)
	 * @equation.external
	 */
	public int addRow(int index)
	{
		return this.dataManager.addRow(index - 1);
	}

	/**
	 * Delete a row
	 * 
	 * @param rowIndex
	 *            - the row to delete (1-based)
	 * 
	 * @return true if deleted successfully
	 * @equation.external
	 */
	public boolean deleteRow(int rowIndex)
	{
		return this.dataManager.deleteRow(rowIndex - 1);
	}
}