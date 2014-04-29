package com.misys.equation.function.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.function.beans.RepeatingDataManager;

public class SortedRepeatingData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SortedRepeatingData.java 9784 2010-11-10 14:25:42Z MACDONP1 $";
	private final RepeatingDataManager repeatingDataManager;

	private int currentIndex;
	private List<Integer> sortedDataIndexes;

	/**
	 * Construct a sorted list
	 * 
	 * @param repeatingDataManager
	 *            - the repeating data to be sorted
	 */
	public SortedRepeatingData(RepeatingDataManager repeatingDataManager)
	{
		this.repeatingDataManager = repeatingDataManager;
		this.currentIndex = -1;
		this.sortedDataIndexes = null;
	}

	/**
	 * Return the repeating data manager id
	 * 
	 * @return the repeating data manager id
	 */
	public String getId()
	{
		return repeatingDataManager.getId();
	}

	/**
	 * Return the repeating data manager
	 * 
	 * @return the repeating data manager
	 */
	public RepeatingDataManager getRepeatingDataManager()
	{
		return repeatingDataManager;
	}

	/**
	 * Return the total rows
	 * 
	 * @return the total rows
	 */
	public int totalRows()
	{
		return repeatingDataManager.totalRows();
	}

	/**
	 * Set the initial row
	 * 
	 * @param row
	 *            - the row index to position the pointer to (0 based)
	 * 
	 * @return true if record has been read
	 */
	public boolean setRow(int row)
	{
		// sorted?
		if (sortedDataIndexes != null)
		{
			// invalid row
			if (row <= -1)
			{
				currentIndex = -1; // position to first record
				return false;
			}
			else if (row > repeatingDataManager.totalRows())
			{
				row = repeatingDataManager.totalRows() - 1;
			}
			else
			{
				currentIndex = row;
			}
		}

		// unsorted
		else
		{
			return repeatingDataManager.setRow(row);
		}

		return true;
	}

	/**
	 * Read the next record
	 * 
	 * @return true indicating a record has been read
	 */
	public boolean next()
	{
		// sorted?
		if (sortedDataIndexes != null)
		{
			currentIndex++;
			if (currentIndex < sortedDataIndexes.size())
			{
				return repeatingDataManager.setRow(sortedDataIndexes.get(currentIndex));
			}
			else
			{
				return false;
			}
		}

		// unsorted
		else
		{
			return repeatingDataManager.next();
		}
	}

	/**
	 * Read the next record
	 * 
	 * @return true indicating a record has been read
	 */
	public boolean prev()
	{
		// sorted?
		if (sortedDataIndexes != null)
		{
			currentIndex--;
			if (currentIndex >= 0)
			{
				return repeatingDataManager.setRow(sortedDataIndexes.get(currentIndex));
			}
			else
			{
				return false;
			}
		}

		// unsorted
		else
		{
			return repeatingDataManager.prev();
		}
	}

	/**
	 * Return the current row number
	 * 
	 * @return the current row number
	 */
	public int currentRowNumber()
	{
		// sorted?
		if (sortedDataIndexes != null)
		{
			return currentIndex;
		}

		// unsorted
		else
		{
			return repeatingDataManager.currentRowNumber();
		}
	}

	/**
	 * Sort the repeating group
	 * 
	 * @param contextFields
	 *            - the list of fields
	 */
	public void sort(String contextFields)
	{
		// create the list containing the value of the context fields
		List<String> list = repeatingDataManager.rtvDataAsList(contextFields);

		// sort the list
		Collections.sort(list);

		// create the list containing the row indexes to the repeating data manager
		sortedDataIndexes = new ArrayList<Integer>();
		for (String item : list)
		{
			int i = item.indexOf(EqDataType.GLOBAL_DELIMETER);
			Integer rowIndex = new Integer(item.substring(i + EqDataType.GLOBAL_DELIMETER.length()));
			sortedDataIndexes.add(rowIndex);
		}
	}

	/**
	 * Reverse the sort order of the repeating group
	 * 
	 */
	public void reverseSort()
	{
		// not yet sorted, then do nothing
		if (sortedDataIndexes == null)
		{
			return;
		}

		// reverse the order
		Collections.reverse(sortedDataIndexes);
	}

}
