package com.misys.equation.common.globalprocessing.audit;

import java.io.Serializable;

/**
 * Encapsulates a System ID and Unit ID with the equals() and hashCode() methods implemented over the two IDs.
 */
@SuppressWarnings("serial")
public class SystemUnit implements Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SystemUnit.java 8880 2010-08-26 06:48:56Z berzosa $";
	private String system;
	private String unit;

	public SystemUnit(String system, String unit)
	{
		this.system = system;
		this.unit = unit;
	}

	public String getSystem()
	{
		return system;
	}

	public void setSystem(String system)
	{
		this.system = system;
	}

	public String getUnit()
	{
		return unit;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	@Override
	public String toString()
	{
		return getSystem() + "-" + getUnit();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof SystemUnit)
		{
			return obj.toString().equals(this.toString());
		}

		return false;
	}

	@Override
	public int hashCode()
	{
		return this.toString().hashCode();
	}
}
