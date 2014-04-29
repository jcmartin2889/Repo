package com.misys.equation.function.beans;

import com.misys.equation.function.tools.SortedRepeatingData;

public class RepeatingGroupStatus
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RepeatingGroupStatus.java 8034 2010-06-30 13:08:04Z lima12 $";

	// repeating group id
	private String id;

	// the column name on how the repeating group is sorted
	private String fieldOrder;

	// asc (true) or desc (false) sort order
	private boolean sortAsAsc;

	// the breakby name on how the repeating group is filtered
	private String breakBy;

	// linked function exists?
	private boolean linkFuncExists;

	// page size
	private int pageSize;

	// the top index currently displayed on the user (only used when paging is enabled)
	private int rrnTop; // 0-based index
	private boolean firstRecord;

	// the bottom index currently displayed on the user (only used when paging is enabled)
	private int rrnBottom; // 0-based index
	private boolean lastRecord;

	// the sorted version of the repeating data
	private SortedRepeatingData currentSortedData;

	/**
	 * Construct an empty repeating group status
	 */
	public RepeatingGroupStatus()
	{
		commonInitialisation();
	}

	/**
	 * Construct a default repeating group status
	 * 
	 * @param id
	 *            - the repeating group id
	 */
	public RepeatingGroupStatus(String id)
	{
		commonInitialisation();
		this.id = id;
	}

	/**
	 * Common initialisation
	 */
	private void commonInitialisation()
	{
		this.id = "";
		this.fieldOrder = "";
		this.sortAsAsc = true;
		this.breakBy = "";
		this.pageSize = 0;
		this.rrnTop = 0;
		this.rrnBottom = 0;
		this.firstRecord = false;
		this.lastRecord = false;
		this.linkFuncExists = false;
	}

	/**
	 * Return the repeating group id
	 * 
	 * @return the repeating group id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Set the repeating group id
	 * 
	 * @param id
	 *            - the repeating group id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Return the column name on how the data will be sorted
	 * 
	 * @return the column name on how the data will be sorted
	 */
	public String getFieldOrder()
	{
		return fieldOrder;
	}

	/**
	 * Set the column name on how the data will be sorted
	 * 
	 * @param fieldOrder
	 *            - the column name on how the data will be sorted
	 */
	public void setFieldOrder(String fieldOrder)
	{
		this.fieldOrder = fieldOrder;
	}

	/**
	 * Determine whether sorted in ascending order or not
	 * 
	 * @return true if sorted is in ascending order
	 */
	public boolean isSortAsAsc()
	{
		return sortAsAsc;
	}

	/**
	 * Set if sorted is in ascending order
	 * 
	 * @param sortAsAsc
	 *            - true if sorted is in ascending order
	 */
	public void setSortAsAsc(boolean sortAsAsc)
	{
		this.sortAsAsc = sortAsAsc;
	}

	/**
	 * Return the grouping that is currently displayed
	 * 
	 * @return the grouping that is currently displayed
	 */
	public String getBreakBy()
	{
		return breakBy;
	}

	/**
	 * Set the grouping that is currently displayed
	 * 
	 * @param breakBy
	 *            - the grouping that is currently displayed
	 */
	public void setBreakBy(String breakBy)
	{
		this.breakBy = breakBy;
	}

	/**
	 * Return the page size
	 * 
	 * @return the page size
	 */
	public int getPageSize()
	{
		return pageSize;
	}

	/**
	 * Return the page size
	 * 
	 * @return the page size
	 */
	public int rtvPageSize()
	{
		if (pageSize == 0)
		{
			return Integer.MAX_VALUE;
		}
		else
		{
			return pageSize;
		}
	}

	/**
	 * Set the page size
	 * 
	 * @param pagingSize
	 *            - the page size
	 */
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	/**
	 * Return the top relative record number of the current record displayed
	 * 
	 * @return the top relative record number of the current record displayed
	 */
	public int getRrnTop()
	{
		return rrnTop;
	}

	/**
	 * Set the top relative record number of the current record displayed
	 * 
	 * @param rrnTop
	 *            - the top relative record number of the current record displayed
	 */
	public void setRrnTop(int rrnTop)
	{
		this.rrnTop = rrnTop;
	}

	/**
	 * Return the bottom relative record number of the current record displayed
	 * 
	 * @return the bottom relative record number of the current record displayed
	 */
	public int getRrnBottom()
	{
		return rrnBottom;
	}

	/**
	 * Set the bottom relative record number of the current record displayed
	 * 
	 * @param rrnBottom
	 *            - the bottom relative record number of the current record displayed
	 */
	public void setRrnBottom(int rrnBottom)
	{
		this.rrnBottom = rrnBottom;
	}

	/**
	 * Determine whether this is the first record
	 * 
	 * @return true if this is the first record
	 */
	public boolean isFirstRecord()
	{
		return firstRecord;
	}

	/**
	 * Set whether this is the first record
	 * 
	 * @param firstRecord
	 *            - true if this is the first record
	 */
	public void setFirstRecord(boolean firstRecord)
	{
		this.firstRecord = firstRecord;
	}

	/**
	 * Determine whether this is the last record
	 * 
	 * @return true if this is the last record
	 */
	public boolean isLastRecord()
	{
		return lastRecord;
	}

	/**
	 * Set whether this is the last record
	 * 
	 * @param lastRecord
	 *            - true if this is the last record
	 */
	public void setLastRecord(boolean lastRecord)
	{
		this.lastRecord = lastRecord;
	}

	/**
	 * Determine whether linked function exists
	 * 
	 * @return true if linked function exists
	 */
	public boolean isLinkFuncExists()
	{
		return linkFuncExists;
	}

	/**
	 * Set whether linked function exists
	 * 
	 * @param linkFuncExists
	 *            - true if linked function exists
	 */
	public void setLinkFuncExists(boolean linkFuncExists)
	{
		this.linkFuncExists = linkFuncExists;
	}

	/**
	 * Return the last sorted repeating data as returned by the getSortedRepeatingData()
	 * 
	 * @return the last sorted repeating data as returned by the getSortedRepeatingData()
	 */
	public SortedRepeatingData currentSortedRepeatingData()
	{
		return currentSortedData;
	}

	/**
	 * Add the list of repeating data managers
	 * 
	 * @param repeatingDataManagers
	 *            - the list of repeating data managers
	 */
	public void setRepeatingDataManager(RepeatingDataManager repeatingDataManager)
	{
		currentSortedData = new SortedRepeatingData(repeatingDataManager);
	}

	/**
	 * Sort the repeating group
	 * 
	 * @param repeatingGroupId
	 *            - the repeating group id to be sorted
	 * @param contextFields
	 *            - the list of fields
	 */
	public void sort(String repeatingGroupId, String contextFields)
	{
		// set the context fields
		setFieldOrder(contextFields);

		// get the sorted repeating data
		currentSortedData.sort(contextFields);
	}

	/**
	 * Reverse the sort order of the repeating group
	 * 
	 * @param repeatingGroupId
	 *            - the repeating group id to be sorted
	 * @param contextFields
	 *            - the list of fields
	 */
	public void reverseSort(String repeatingGroupId, String contextFields)
	{
		// reverse the order
		setSortAsAsc(!sortAsAsc);

		// get the sorted repeating data
		currentSortedData.reverseSort();
	}

}
