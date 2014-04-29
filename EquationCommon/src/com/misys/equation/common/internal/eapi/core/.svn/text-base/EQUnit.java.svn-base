/***********************************************************************************************************************************
 * Represents an Equation unit.
 * <P>
 * This is an internal class for storing API mapping information for the system/unit. There are no public methods available.
 * <P>
 * 
 * @author Misys International Banking Systems Ltd.
 */
package com.misys.equation.common.internal.eapi.core;

import java.util.HashMap;

/**
 * @author weddelc1
 */
class EQUnit
{
	private HashMap<String, EQObjectMetaData> eqMetaDataHash = new HashMap<String, EQObjectMetaData>();
	private String systemName;
	private String unit;
	protected EQUnit(String systemName, String unit)
	{
		this.systemName = systemName;
		this.unit = unit;
	}
	/*******************************************************************************************************************************
	 * Clones are not suppported for this singleton (per server) class
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}
	/*******************************************************************************************************************************
	 * get meta data for this API
	 * 
	 * @uml.property name="unit"
	 */
	protected EQObjectMetaData getMap(String eqAPIID)
	{
		return eqMetaDataHash.get(eqAPIID);
	}
	/*******************************************************************************************************************************
	 * get the name of the system
	 * 
	 * @uml.property name="systemName"
	 */
	protected String getSystemName()
	{
		return systemName;
	}
	/*******************************************************************************************************************************
	 * get the name of the unit
	 * 
	 * @uml.property name="unit"
	 */
	protected String getUnitMnemonic()
	{
		return unit;
	}
	/*******************************************************************************************************************************
	 * determine if the API's meta data is already loaded
	 * 
	 * @uml.property name="unit"
	 */
	protected boolean hasMap(String eqAPIID)
	{
		return eqMetaDataHash.containsKey(eqAPIID);
	}
	/*******************************************************************************************************************************
	 * add the API's meta data to the stored collection of maps
	 * 
	 * @uml.property name="unit"
	 */
	protected void addMap(EQObjectMetaData map)
	{
		eqMetaDataHash.put(map.getIdentifier(), map);
	}
}
