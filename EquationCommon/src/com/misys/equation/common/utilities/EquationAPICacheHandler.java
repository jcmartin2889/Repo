package com.misys.equation.common.utilities;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Iterator;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardValidation;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.internal.eapi.core.EQException;

public class EquationAPICacheHandler implements Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationAPICacheHandler.java 12209 2011-11-01 15:23:33Z lima12 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EquationAPICacheHandler.class);

	/** Serial UID */
	private static final long serialVersionUID = 1L;

	/** Cache */
	private final Hashtable<String, EquationAPICache> equationAPICaches = new Hashtable<String, EquationAPICache>();

	/**
	 * Return the API cache given the Equation Standard Object
	 * 
	 * @param language
	 *            - the user's language
	 * @param iEquationStandardObject
	 *            - the Equation Standard Object
	 * 
	 * @return the API cache of the Equation Standard Object
	 */
	public EquationAPICache getFromCache(String language, IEquationStandardObject iEquationStandardObject)
	{
		// PV modules
		if (iEquationStandardObject instanceof EquationStandardValidation)
		{
			EquationStandardValidation equationStandardValidation = (EquationStandardValidation) iEquationStandardObject;
			equationStandardValidation.setDataInput(equationStandardValidation.getEquationPVData().parseFieldsIntoDSCCN("N"));
			return getFromCache(language, equationStandardValidation);
		}
		return null;
	}

	/**
	 * Return the PV cache given the Equation Standard Validation
	 * 
	 * @param language
	 *            - the user's language
	 * @param equationStandardValidation
	 *            - the Equation Standard Validation
	 * 
	 * @return the PV cache of the Equation Standard Validation
	 */
	public EquationAPICache getFromCache(String language, EquationStandardValidation equationStandardValidation)
	{
		EquationAPICache equationAPICache = getAPICache(getEquationAPICacheKey(language, equationStandardValidation));
		if (equationAPICache != null)
		{
			EquationStandardValidation cache = equationAPICache.getEquationStandardValidation();
			equationStandardValidation.setData(cache.getDataOutput());
			equationStandardValidation.setErrorList(cache.getErrorList());
			equationStandardValidation.setEquationPVData(cache.getEquationPVData());

			return equationAPICache;
		}
		return null;
	}

	/**
	 * Return the API cache based on the API cache key
	 * 
	 * @param equationAPICacheKey
	 *            - the Equation API cache key
	 * 
	 * @return the API cache based on the API cache key
	 */
	public EquationAPICache getAPICache(EquationAPICacheKey equationAPICacheKey)
	{
		return equationAPICaches.get(equationAPICacheKey.toString());
	}

	/**
	 * Add the Equation Standard Object to the cache
	 * 
	 * @param language
	 *            - the user's language
	 * @param iEquationStandardObject
	 *            - the Equation Standard Object
	 */
	public void addAPICache(String language, IEquationStandardObject iEquationStandardObject)
	{
		if (iEquationStandardObject instanceof EquationStandardValidation)
		{
			EquationAPICache equationAPICache = new EquationAPICache(iEquationStandardObject);
			addAPICache(getEquationAPICacheKey(language, iEquationStandardObject), equationAPICache);
		}
	}

	/**
	 * Add the Equation API Cache to the cache
	 * 
	 * @param equationAPICacheKey
	 *            - the Equation API Cache key
	 * @param equationAPICache
	 *            - the Equation API Cache
	 */
	public void addAPICache(EquationAPICacheKey equationAPICacheKey, EquationAPICache equationAPICache)
	{
		equationAPICaches.put(equationAPICacheKey.toString(), equationAPICache);
	}

	/**
	 * Return the Equation API Cache key of an Equation Standard Object
	 * 
	 * @param language
	 *            - the user's language
	 * @param iEquationStandardObject
	 *            - the Equation Standard Object
	 * 
	 * @return the Equation API Cache key of an Equation Standard Object
	 */
	public EquationAPICacheKey getEquationAPICacheKey(String language, IEquationStandardObject iEquationStandardObject)
	{
		// PV
		if (iEquationStandardObject instanceof EquationStandardValidation)
		{
			EquationStandardValidation equationStandardValidation = (EquationStandardValidation) iEquationStandardObject;
			EquationAPICacheKey equationAPICacheKey = new EquationAPICacheKey(language, equationStandardValidation.getService(),
							equationStandardValidation.getDecode(), equationStandardValidation.getBlankAllowed(),
							equationStandardValidation.getNewCode(), equationStandardValidation.getSecurity(),
							equationStandardValidation.getDataInput());
			equationAPICacheKey.generateResultKey();
			return equationAPICacheKey;
		}
		return null;
	}

	/**
	 * Update the cache
	 * 
	 * @param session
	 *            - the Equation standard session
	 */
	public void updateAll(EquationStandardSession session) throws EQException
	{
		Iterator<String> keys = equationAPICaches.keySet().iterator();
		while (keys.hasNext())
		{
			String key = keys.next();
			EquationAPICache equationAPICache = equationAPICaches.get(key);
			String beforeString = equationAPICache.getEquationStandardValidation().getDataOutput();
			EquationStandardValidation equationStandardValidation = equationAPICache.getEquationStandardValidation();
			session.executeValidate(equationStandardValidation);
			String afterString = equationAPICache.getEquationStandardValidation().getDataOutput();
			LOG.debug("API cache updater:");
			LOG.debug(beforeString);
			LOG.debug(afterString);
		}
	}

	/**
	 * Clear cache
	 */
	public void clear()
	{
		equationAPICaches.clear();
	}

}