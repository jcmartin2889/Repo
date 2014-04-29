package com.misys.equation.ui.tools;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * This class represents a message
 */
public class Messages
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: Messages.java 4499 2009-08-20 14:34:36Z lima12 $";
	private static final String BUNDLE_NAME = "com.misys.equation.ui.resources.ApplicationResources";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	/**
	 * Construct an empty message
	 */
	private Messages()
	{
	}

	/**
	 * Return the resource message given the resource key
	 * 
	 * @param key
	 *            - resource key
	 * 
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

}