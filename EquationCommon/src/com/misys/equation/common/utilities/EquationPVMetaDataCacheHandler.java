package com.misys.equation.common.utilities;

import java.io.Serializable;
import java.util.Hashtable;

import com.misys.equation.common.access.EquationPVMetaData;

public class EquationPVMetaDataCacheHandler implements Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationPVMetaDataCacheHandler.java 12209 2011-11-01 15:23:33Z lima12 $";

	/** Serial UID */
	private static final long serialVersionUID = 679477482269654466L;

	private final Hashtable<String, EquationPVMetaDataCache> pvMetaDataCaches;

	/**
	 * Construct an empty PV meta data cache
	 */
	public EquationPVMetaDataCacheHandler()
	{
		this.pvMetaDataCaches = new Hashtable<String, EquationPVMetaDataCache>();
	}

	/**
	 * Return the Equation PV Meta Data cache
	 * 
	 * @param pvName
	 *            - the PV name
	 * 
	 * @return the Equation PV Meta Data cache
	 */
	public EquationPVMetaDataCache getPvMetaDataCache(String pvName)
	{
		return pvMetaDataCaches.get(pvName);
	}

	/**
	 * Return the Equation PV Meta data of the PV if in the cache
	 * 
	 * @param pvName
	 *            - the PV name
	 * 
	 * @return the Equation PV Meta Data if existing, otherwise null
	 */
	public EquationPVMetaData getPvMetaData(String pvName)
	{
		if (isExists(pvName))
		{
			EquationPVMetaDataCache pvMetaDataCache = pvMetaDataCaches.get(pvName);
			return pvMetaDataCache.getPvMetaData();
		}
		else
		{
			return null;
		}
	}

	/**
	 * Add the Equation PV Meta data to the cache
	 * 
	 * @param pvName
	 *            - the PV name
	 * @param pvMetaData
	 *            - the PV meta data
	 * 
	 * @return true if the pv meta data already exists, false if has been added
	 */
	public boolean addPvMetaData(String pvName, EquationPVMetaData pvMetaData)
	{
		boolean exist = isExists(pvName);
		if (pvName.length() > 0)
		{
			EquationPVMetaDataCache pvMetaDataCache = new EquationPVMetaDataCache(pvMetaData);
			pvMetaDataCaches.put(pvName, pvMetaDataCache);
		}
		return exist;
	}

	/**
	 * Determine if the Equation PV Meta data already exists or not
	 * 
	 * @param pvName
	 *            - the PV name
	 * 
	 * @return true if already in the cache
	 */
	public boolean isExists(String pvName)
	{
		return pvMetaDataCaches.containsKey(pvName);
	}

	/**
	 * Clear cache
	 */
	public void clear()
	{
		pvMetaDataCaches.clear();
	}
}
