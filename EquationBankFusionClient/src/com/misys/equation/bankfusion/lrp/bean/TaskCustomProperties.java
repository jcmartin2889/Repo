package com.misys.equation.bankfusion.lrp.bean;

import java.util.HashMap;

import bf.com.misys.bankfusion.workflow.attributes.CustomProperties;
import bf.com.misys.bankfusion.workflow.attributes.CustomProperty;

public class TaskCustomProperties
{
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TaskCustomProperties.java 11954 2011-09-30 09:07:06Z rpatrici $";

	HashMap<String, TaskCustomProperty> customProperties = new HashMap<String, TaskCustomProperty>();

	/**
	 * Constructor
	 * 
	 * @param customProperties
	 *            - the list of custom properties
	 */
	public TaskCustomProperties(CustomProperties customProperties)
	{
		for (CustomProperty type : customProperties.getCustomProperty())
		{
			this.customProperties.put(type.getName(), new TaskCustomProperty(type));
		}
	}

	/**
	 * Return the custom property
	 * 
	 * @param name
	 *            - the name of the custom property
	 * 
	 * @return the custom property
	 */
	public TaskCustomProperty rtvValue(String name)
	{
		return customProperties.get(name);
	}
}
