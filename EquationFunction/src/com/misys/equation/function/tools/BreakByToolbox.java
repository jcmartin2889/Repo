package com.misys.equation.function.tools;

import java.math.BigDecimal;
import java.util.Hashtable;

import com.misys.equation.common.utilities.EqDataType;

/**
 * This keeps tracks of the totals for all possible groupings
 * 
 * @author Lima12
 */
public class BreakByToolbox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: BreakByToolbox.java 10856 2011-04-26 10:45:41Z MACDONP1 $";
	private final Hashtable<String, SummationToolbox> breakBy = new Hashtable<String, SummationToolbox>();

	/**
	 * Add a value to the existing value for the specified field name
	 * 
	 * @param breakBy
	 *            - the list of fields on how to do the totalling
	 * @param fieldName
	 *            - the field name
	 * @param value
	 *            - the number to be added
	 * 
	 * @return the new total for the field name
	 */
	public void add(String listBreakBy, String fieldName, BigDecimal value)
	{
		// add it for the grand total
		addWithin("", fieldName, value);

		// add it for each of the group
		if (listBreakBy.length() > 0)
		{
			// does this ends with a delimeter, if so, add a space
			if (listBreakBy.endsWith(EqDataType.GLOBAL_DELIMETER))
			{
				listBreakBy += " ";
			}

			String[] listBreakBys = listBreakBy.split(EqDataType.GLOBAL_DELIMETER);
			String grouping = "";
			for (String listBreakBy2 : listBreakBys)
			{
				grouping += listBreakBy2.trim();
				addWithin(grouping, fieldName, value);
				grouping += EqDataType.GLOBAL_DELIMETER;
			}
		}
	}

	/**
	 * Add a value into a particular group/field name
	 * 
	 * @param grouping
	 *            - the group
	 * @param fieldName
	 *            - the field name
	 * @param bigDecimal
	 *            - the number to be added
	 */
	private void addWithin(String grouping, String fieldName, BigDecimal bigDecimal)
	{
		SummationToolbox summation = breakBy.get(grouping);
		if (summation == null)
		{
			summation = new SummationToolbox();
			breakBy.put(grouping, summation);
		}

		// add
		summation.add(fieldName, bigDecimal);

		// put it back to the hashtable
		breakBy.put(grouping, summation);
	}

	/**
	 * Return the list of totals
	 * 
	 * @return the list of totals
	 */
	@Override
	public String toString()
	{
		return breakBy.toString();
	}

	/**
	 * Return the totals
	 * 
	 * @return the totals
	 */
	public Hashtable<String, SummationToolbox> getBreakBy()
	{
		return breakBy;
	}

	/**
	 * Return the totals
	 * 
	 * @return the totals
	 */
	public SummationToolbox getBreakBy(String grouping)
	{
		return breakBy.get(grouping);
	}
}