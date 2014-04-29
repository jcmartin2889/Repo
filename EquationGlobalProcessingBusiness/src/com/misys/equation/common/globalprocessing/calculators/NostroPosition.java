package com.misys.equation.common.globalprocessing.calculators;

import java.io.Serializable;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Map;

import com.misys.equation.common.utilities.Toolbox;

public class NostroPosition
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: NostroPosition.java 10365 2011-01-27 12:26:29Z hempensp $";
	private String systemUnitNostroKey;
	private String nostroMnemonic;
	private String nostroUnit;
	private String nostroSystem;

	private Map<String, DateAndBalance> currentDayAndBalances;

	private String currency;

	public NostroPosition()
	{
		currentDayAndBalances = new LinkedHashMap<String, DateAndBalance>();
	}

	public String getNostroMnemonic()
	{
		return nostroMnemonic;
	}

	public void setNostroMnemonic(String nostroMnemonic)
	{
		this.nostroMnemonic = nostroMnemonic;
	}

	public String getNostroUnit()
	{
		return nostroUnit;
	}

	public void setNostroUnit(String nostroUnit)
	{
		this.nostroUnit = nostroUnit;
	}

	public String getCurrency()
	{
		return currency;
	}
	public void setCurrency(String currency)
	{
		this.currency = currency;
	}

	public Map<String, DateAndBalance> getCurrentDayAndBalances()
	{
		return currentDayAndBalances;
	}

	public void addCurrentDayAndBalance(String dbFormatDate, DateAndBalance currentDayAndBlances)
	{
		GregorianCalendar now = Toolbox.parseEqDate(dbFormatDate);
		this.currentDayAndBalances.put(Toolbox.convertCalendarDay(now), currentDayAndBlances);
	}

	public void setCurrentDayAndBalances(Map<String, DateAndBalance> currentDayAndBlances)
	{
		this.currentDayAndBalances = currentDayAndBlances;
	}

	public String getNostroSystem()
	{
		return nostroSystem;
	}
	public void setNostroSystem(String nostroSystem)
	{
		this.nostroSystem = nostroSystem;
	}

	public String getSystemUnitNostroKey()
	{
		return systemUnitNostroKey;
	}

	public void setSystemUnitNostroKey(String systemUnitNostroKey)
	{
		this.systemUnitNostroKey = systemUnitNostroKey;
	}
	/**
	 * Inner class to provide a sort comparator for the collections framework
	 * 
	 * @author HempensP
	 * 
	 */
	public static class NostroPositionComparator implements Comparator<NostroPosition>, Serializable
	{
		/**	 */
		private static final long serialVersionUID = 1L;

		public int compare(NostroPosition o1, NostroPosition o2)
		{
			if (o1 == null || o2 == null)
			{
				return 1;
			}
			return o1.getSystemUnitNostroKey().compareTo(o2.getSystemUnitNostroKey());
		}
	}
}