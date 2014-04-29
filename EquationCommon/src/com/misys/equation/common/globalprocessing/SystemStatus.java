package com.misys.equation.common.globalprocessing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SystemStatus
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SystemStatus.java 8910 2010-08-26 15:25:20Z MACDONP1 $";
	private String systemName;
	private boolean isAvailable;
	private final Map<String, UnitStatus> unitStatus;

	public SystemStatus()
	{
		unitStatus = new HashMap<String, UnitStatus>();
	}

	public String getSystemName()
	{
		return systemName;
	}

	public void setSystemName(String systemName)
	{
		this.systemName = systemName;
	}

	public boolean isAvailable()
	{
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable)
	{
		this.isAvailable = isAvailable;
	}

	public UnitStatus getUnitStatus(String unitName)
	{
		return unitStatus.get(unitName);
	}

	public void setUnitStatus(String unitName, UnitStatus unitStatus)
	{
		this.unitStatus.put(unitName, unitStatus);
	}

	public Map<String, UnitStatus> getUnitStatus()
	{
		return unitStatus;
	}

	public Set<String> getUnitNames()
	{
		return unitStatus.keySet();
	}
}