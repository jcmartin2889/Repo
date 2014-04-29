package com.misys.equation.common.globalprocessing;

/**
 * Represents an individual 'Code' within the MPM file
 */
public class UnitServerUnitValue
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UnitServerUnitValue.java 9659 2010-11-02 17:25:07Z MACDONP1 $";
	private String unit;
	private String server;
	private String unitValue;

	public UnitServerUnitValue(String unit, String server, String unitValue)
	{
		this.unit = unit;
		this.server = server;
		this.unitValue = unitValue;
	}

	public String getUnit()
	{
		return unit;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public String getServer()
	{
		return server;
	}

	public void setServer(String server)
	{
		this.server = server;
	}

	public String getUnitValue()
	{
		return unitValue;
	}

	public void setUnitValue(String unitValue)
	{
		this.unitValue = unitValue;
	}
}
