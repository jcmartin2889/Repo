package com.misys.equation.common.access;

public class UnitPropertiesBean
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UnitPropertiesBean.java 9725 2010-11-08 12:34:45Z MACDONP1 $";

	private final String systemId;
	private final String unitId;
	private final String unitType;
	private final String unitDescription;

	public UnitPropertiesBean(String systemId, String unitId, String unitType, String unitDescription)
	{
		this.systemId = systemId;
		this.unitId = unitId;
		this.unitType = unitType;
		this.unitDescription = unitDescription;
	}

	public String getSystemId()
	{
		return systemId;
	}

	public String getUnitId()
	{
		return unitId;
	}

	public String getUnitType()
	{
		return unitType;
	}

	public String getUnitDescription()
	{
		return unitDescription;
	}
}
