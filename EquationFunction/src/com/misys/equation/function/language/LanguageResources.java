package com.misys.equation.function.language;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.misys.equation.common.access.EquationUser;

/**
 * This class represents a message
 */
public class LanguageResources
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LanguageResources.java 4681 2009-09-10 11:54:42Z lima12 $";
	private static final String BUNDLE_NAME = "equationFunctionMessages";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	/**
	 * Construct an empty message
	 */
	private LanguageResources()
	{
	}

	/**
	 * Return the resource message given the resource key
	 * 
	 * @param key
	 *            - resource key
	 * @return the resource message associated with the resource key
	 */
	public static String getString(String key)
	{
		try
		{
			return RESOURCE_BUNDLE.getString(key);
		}
		catch (MissingResourceException e)
		{
			return '!' + key + '!';
		}
	}

	/**
	 * Determine if resource key exists
	 * 
	 * @param key
	 *            - resource key
	 * 
	 * @return true if resource key exists
	 */
	public static boolean isExists(String key)
	{
		try
		{
			RESOURCE_BUNDLE.getString(key);
			return true;
		}
		catch (MissingResourceException e)
		{
			return false;
		}
	}

	/**
	 * Obtains parameterised strings (e.g. error messages)
	 * 
	 * @param key
	 *            the ID of the text resource
	 * @param params
	 *            a String[] containing parameters
	 * 
	 * @return the formatted String
	 */
	public static String getFormattedString(String key, String[] params)
	{
		try
		{
			String text = RESOURCE_BUNDLE.getString(key);
			Object[] objects = params;
			return MessageFormat.format(text, objects);
		}
		catch (MissingResourceException e)
		{
			return '!' + key + '!';
		}
	}

	/**
	 * Obtains parameterised strings (e.g. for messages)
	 * <p>
	 * This method delegates to the getFormattedString(String, String[]) version of this method
	 * 
	 * @param key
	 *            the ID of the text resource
	 * @param param1
	 *            a String parameter
	 * 
	 * @return the formatted String
	 */
	public static String getFormattedString(String key, String param1)
	{
		String[] params = new String[] { param1 };
		return getFormattedString(key, params);
	}

	/**
	 * Returns a Map of the keys in the message file with the root specified
	 * 
	 * @param root
	 *            - A String defining the root of the key to be retrieved
	 * @param maxdepth
	 *            - The maximum depth of message to retrieve from the file. Each level of depth is denoted by a '.' within the
	 *            properties file If 0 is passed then only the root element specified will be returned
	 * @return - A <code>Map</code> containing the String key and String values found
	 */
	public static Map<String, String> getList(String root, int maxdepth)
	{
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		Enumeration<String> eKeys = RESOURCE_BUNDLE.getKeys();
		while (eKeys.hasMoreElements())
		{
			String currentElement = eKeys.nextElement();
			String[] splitEle = currentElement.split("\\.");
			if (splitEle.length > 0 && splitEle[0].split("_")[0].equals(root) && splitEle.length < maxdepth + 1)
			{
				map.put(currentElement, getString(currentElement));
			}
		}
		return map;
	}

	/**
	 * Return the resource message in user's language given the resource key
	 * 
	 * @param langId
	 *            - the language Id
	 * @param key
	 *            - the resource key
	 * 
	 * @return the resource message in user's language associated with the resource key
	 */
	public static String getStringInUserLanguage(String langId, String key)
	{
		// get key in user's language
		String fullKey = langId + "." + key;
		if (isExists(fullKey))
		{
			return getString(fullKey);
		}

		// get key in default language
		fullKey = EquationUser.DEF_LANG + "." + key;
		if (isExists(fullKey))
		{
			return getString(fullKey);
		}

		// still not found
		return '!' + langId + "." + key + '!';
	}
}
