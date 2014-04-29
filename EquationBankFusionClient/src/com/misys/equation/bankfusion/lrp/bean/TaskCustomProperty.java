package com.misys.equation.bankfusion.lrp.bean;

import bf.com.misys.bankfusion.workflow.attributes.CustomProperty;

public class TaskCustomProperty
{
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TaskCustomProperty.java 11954 2011-09-30 09:07:06Z rpatrici $";

	String name;
	String value;

	/**
	 * Constructor
	 * 
	 * @param property
	 *            - the custom property
	 */
	public TaskCustomProperty(CustomProperty property)
	{
		this.name = property.getName();
		this.value = property.getValue();
	}

	/**
	 * Return the custom property name
	 * 
	 * @return the custom property name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the custom property name
	 * 
	 * @param name
	 *            - the custom property name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Return the custom property value
	 * 
	 * @return the custom property value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Set the custom property value
	 * 
	 * @param value
	 *            - the custom property value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

}
