package com.misys.equation.common.globalprocessing;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IMPMRecordDao;
import com.misys.equation.common.dao.beans.MPMRecordDataModel;

public class GlobalParameterMap
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalParameterMap.java 9467 2010-10-14 15:56:27Z MACDONP1 $";
	private static GlobalParameterMap singletonGlobalParamaterMap;
	private final Map<String, Map<String, String>> mpmValues;

	private GlobalParameterMap()
	{
		mpmValues = new HashMap<String, Map<String, String>>();
	}

	/**
	 * Get the singleton context
	 */
	public static synchronized GlobalParameterMap getParameterMap()
	{
		// Create our one and only instance of this class
		if (singletonGlobalParamaterMap == null)
		{
			singletonGlobalParamaterMap = new GlobalParameterMap();
		}
		return singletonGlobalParamaterMap;
	}

	private synchronized Map<String, String> getParametersForCode(EquationStandardSession session, String code)
	{
		DaoFactory d = new DaoFactory();
		IMPMRecordDao loompa = d.getMPMRecordDao(session, new MPMRecordDataModel());
		Map<String, String> masterValuesByUSUV = new HashMap<String, String>();
		// check the cached parameters and return them if present
		if (!mpmValues.isEmpty())
		{
			if (mpmValues.containsKey(code))
			{
				masterValuesByUSUV = mpmValues.get(code);
			}
		}
		else
		{
			// use the MPM dao to get values for code.
			masterValuesByUSUV = loompa.getMapByCode(code);
			// add retrieved values to cached MPMValues
			mpmValues.put(code, masterValuesByUSUV);

		}
		// return the collection of values
		return masterValuesByUSUV;
	}

	public synchronized String getMasterValue(EquationStandardSession session, String code, String unit, String system,
					String unitsystemValue)
	{
		Map<String, String> masterValues = getParametersForCode(session, code);
		String keyUSUV = unit + ":" + system + ":" + unitsystemValue;
		if (masterValues.containsKey(keyUSUV))
		{
			return masterValues.get(keyUSUV);
		}
		else
		{
			return "";
		}
	}

	public synchronized String getUnitAndSystemValue(EquationStandardSession session, String code, String unit, String system,
					String masterValue)
	{
		Map<String, String> unitValues = getParametersForCode(session, code);
		String unitValue = "";
		for (Entry<String, String> entry : unitValues.entrySet())
		{
			if (entry.getValue().equals(masterValue))
			{
				// Split the key into it's components
				String keyUSUV = entry.getKey().toString();
				String[] keys = keyUSUV.split(":");
				// If we haven't got the right key layout, throw a wobbly
				if (keys.length < 3)
				{
					throw new IllegalArgumentException();
				}

				// Otherwise see if eHarmony has a match for us
				else
				{
					String keyUnit = keys[0];
					String keySystem = keys[1];
					String keyValue = keys[2];
					// check the unit and system from the MPM against those passed from the consolidator
					if ((keyUnit.equals(unit)) && (keySystem.equals(system)))
					{
						unitValue = keyValue;
					}
				}
			}
		}
		return unitValue;
	}
}
