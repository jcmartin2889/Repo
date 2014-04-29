package com.misys.equation.common.access;

/**
 * Interface used by transactions that support repeating data
 */
public interface IListTransaction
{
	/**
	 * Indicates whether a row data is available.
	 * 
	 * @return a boolean indicating if a row of data is available
	 */
	public boolean next();

	/**
	 * Moves back to (before) the first item of repeating data
	 */
	public void moveFirst();

	/**
	 * Indicates whether all the records have been retrieved and not truncated by the upper limit
	 */
	public boolean isComplete();

	/**
	 * Return the maximum length per record
	 */
	public int getRecordLength();

	/**
	 * Return the number of records currently being held
	 */
	public int getRecordCount();

}
