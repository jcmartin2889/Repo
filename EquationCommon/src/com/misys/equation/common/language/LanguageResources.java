package com.misys.equation.common.language;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * This class represents a message
 */
public class LanguageResources
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LanguageResources.java 10719 2011-04-04 09:36:56Z bterrado $";
	private static final String BUNDLE_NAME = "equationCommonMessages";
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
			return RESOURCE_BUNDLE.getString(key).trim();
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
}