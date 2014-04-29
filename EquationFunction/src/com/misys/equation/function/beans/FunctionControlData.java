package com.misys.equation.function.beans;

import java.util.HashMap;
import java.util.Map;

import com.misys.equation.function.runtime.ShutterGroup;

public class FunctionControlData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionControlData.java 10856 2011-04-26 10:45:41Z MACDONP1 $";

	public static final String OPTION = "Option";
	public static final String ORG_USER = "OrigUser";
	public static final String CREATED_BY = "CreatedBy";
	public static final String REPEATING_GROUP = "RepeatingGroup";
	public static final String REPEATING_GROUP_ROW = "RepeatingGroupRow";
	public static final String REPEATING_GROUP_OPER = "RepeatingGroupOper";

	private Map<String, String> data;
	private Map<String, LoadFieldSetStatus> loadFieldSetStatuses;
	private Map<String, Integer> currentDisplay;
	private Map<String, ShutterGroup> shutters;
	private Map<String, RepeatingGroupStatus> repeatingGroupStatuses;

	/**
	 * Construct the default list of data
	 */
	public FunctionControlData()
	{
		data = new HashMap<String, String>();
		loadFieldSetStatuses = new HashMap<String, LoadFieldSetStatus>();
		currentDisplay = new HashMap<String, Integer>();
		shutters = new HashMap<String, ShutterGroup>();
		repeatingGroupStatuses = new HashMap<String, RepeatingGroupStatus>();
		addData(OPTION, "");
		addData(ORG_USER, "");
		addData(CREATED_BY, "");
		addData(REPEATING_GROUP, "");
		addData(REPEATING_GROUP_ROW, "");
		addData(REPEATING_GROUP_OPER, "");
	}

	/**
	 * Set the data
	 * 
	 * @param datas
	 *            - data
	 */
	public void setData(HashMap<String, String> data)
	{
		this.data = data;
	}

	/**
	 * Return the data
	 * 
	 * @return the data
	 */
	public Map<String, String> getData()
	{
		return data;
	}

	/**
	 * Add a data
	 * 
	 * @param fieldName
	 *            - the field name
	 * @param fieldValue
	 *            - the field value
	 */
	public void addData(String fieldName, String fieldValue)
	{
		data.put(fieldName, fieldValue);
	}

	/**
	 * Determine whether a field name exists in the data
	 * 
	 * @param fieldName
	 *            - the field name
	 * 
	 * @return true if field name exist in the data
	 */
	public boolean isDataExist(String fieldName)
	{
		return data.containsKey(fieldName);
	}

	/**
	 * Set the field name
	 * 
	 * @param fieldName
	 *            - the field name
	 * @param fieldValue
	 *            - the field value
	 * 
	 * @return true if field data has been updated
	 */
	public boolean setData(String fieldName, String fieldValue)
	{
		if (isDataExist(fieldName))
		{
			data.put(fieldName, fieldValue);
			return true;
		}

		return false;
	}

	/**
	 * Return the field value of the field name
	 * 
	 * @param fieldName
	 *            - the field name
	 * 
	 * @return the field value
	 */
	public String getFieldValue(String fieldName)
	{
		return data.get(fieldName);
	}

	/**
	 * Return the field set status
	 * 
	 * @return the field set status
	 */
	public Map<String, LoadFieldSetStatus> getLoadFieldSetStatuses()
	{
		return loadFieldSetStatuses;
	}

	/**
	 * Set the field set status
	 * 
	 * @param loadFieldSetStatuses
	 *            - the field set status
	 */
	public void setLoadFieldSetStatuses(Map<String, LoadFieldSetStatus> loadFieldSetStatuses)
	{
		this.loadFieldSetStatuses = loadFieldSetStatuses;
	}

	/**
	 * Add a field set status
	 * 
	 * @param fieldSet
	 *            - field set id
	 * @param loadFieldSetStatus
	 *            - load field set status
	 */
	public void addLoadFieldSetStatuses(String fieldSet, LoadFieldSetStatus loadFieldSetStatus)
	{
		this.loadFieldSetStatuses.put(fieldSet, loadFieldSetStatus);
	}

	/**
	 * Return the group display status
	 * 
	 * @return the group display status
	 */
	public Map<String, Integer> getCurrentDisplay()
	{
		return currentDisplay;
	}

	/**
	 * Set the group display status
	 * 
	 * @param currentDisplay
	 *            - the group display status
	 */
	public void setCurrentDisplay(Map<String, Integer> currentDisplay)
	{
		this.currentDisplay = currentDisplay;
	}

	/**
	 * Add a display status
	 * 
	 * @param group
	 *            - the group
	 * @param currentDisplay
	 *            - the current subgroup to be displayed
	 */
	public void addCurrentDisplay(String group, Integer currentDisplay)
	{
		this.currentDisplay.put(group, currentDisplay);
	}

	/**
	 * Return the shutter status
	 * 
	 * @return the shutter status
	 */
	public Map<String, ShutterGroup> getShutters()
	{
		return shutters;
	}

	/**
	 * Set the shutter status
	 * 
	 * @param shutters
	 *            - the shutter status
	 */
	public void setShutters(Map<String, ShutterGroup> shutters)
	{
		this.shutters = shutters;
	}

	/**
	 * Add a shutter status tot he list
	 * 
	 * @param group
	 *            - the group id
	 * @param shutter
	 *            - the shutter to be added
	 */
	public void addShutters(String group, ShutterGroup shutter)
	{
		this.shutters.put(group, shutter);
	}

	/**
	 * Return the repeating group statuses
	 * 
	 * @return the repeating group statuses
	 */
	public Map<String, RepeatingGroupStatus> getRepeatingGroupStatuses()
	{
		return repeatingGroupStatuses;
	}

	/**
	 * Set the repeating group statuses
	 * 
	 * @param repeatingGroupStatuses
	 *            - the repeating group statuses
	 */
	public void setRepeatingGroupStatuses(Map<String, RepeatingGroupStatus> repeatingGroupStatuses)
	{
		this.repeatingGroupStatuses = repeatingGroupStatuses;
	}

	/**
	 * Add a repeating group status
	 * 
	 * @param id
	 *            - the repeating group id
	 * @param repeatingGroupStatus
	 *            - the repeating group status
	 */
	public void addRepeatingGroupStatuses(String id, RepeatingGroupStatus repeatingGroupStatus)
	{
		this.repeatingGroupStatuses.put(id, repeatingGroupStatus);
	}

	/**
	 * Return the string representation
	 * 
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append(data);
		buffer.append(loadFieldSetStatuses);
		buffer.append(currentDisplay);
		buffer.append(shutters);
		buffer.append(repeatingGroupStatuses);
		return buffer.toString();
	}

}
