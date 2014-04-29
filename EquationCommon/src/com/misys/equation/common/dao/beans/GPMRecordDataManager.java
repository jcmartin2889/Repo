package com.misys.equation.common.dao.beans;

import java.util.ArrayList;
import java.util.List;

public class GPMRecordDataManager extends GPMRecordDataModel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GPMRecordDataManager.java 8910 2010-08-26 15:25:20Z MACDONP1 $";

	private static final long serialVersionUID = 1L;

	public GPMRecordDataManager()
	{

	}

	/**
	 * This method will return the <code>Boolean </code> System option value.
	 * 
	 * @return
	 */
	public Boolean getSystemOptionBooleanValue()
	{
		String systemOptionValue = getSystemOptionValue();
		return Boolean.valueOf(systemOptionValue);
	}

	/**
	 * This method will return the <code>Integer</code> System option value.
	 * 
	 * @return
	 */
	public Integer getSystemOptionIntegerValue()
	{
		String systemOptionValue = getSystemOptionValue();
		return new Integer(systemOptionValue);
	}

	/**
	 * This method will return the <code>List</code> System option value.
	 * 
	 * @return
	 */
	public List<Integer> getSystemOptionIntegerArrayListValue()
	{
		String systemOptionValue = getSystemOptionValue();
		String[] systemOptionValueArray = systemOptionValue.split(",");
		List<Integer> systemOptions = new ArrayList<Integer>();
		String systemOptionCurrentValue;

		for (String element : systemOptionValueArray)
		{
			systemOptionCurrentValue = element.trim();
			systemOptions.add(new Integer(systemOptionCurrentValue));
		}
		return systemOptions;
	}

	/**
	 * This method will return the <code>List</code> System option value.
	 * 
	 * @return
	 */
	public List<String> getSystemOptionStringArrayListValue()
	{
		String systemOptionValue = getSystemOptionValue();
		String[] systemOptionValueArray = systemOptionValue.split(",");
		List<String> systemOptions = new ArrayList<String>();
		String systemOptionCurrentValue;

		for (String element : systemOptionValueArray)
		{
			systemOptionCurrentValue = element.trim();
			systemOptions.add(systemOptionCurrentValue);
		}
		return systemOptions;
	}
}
