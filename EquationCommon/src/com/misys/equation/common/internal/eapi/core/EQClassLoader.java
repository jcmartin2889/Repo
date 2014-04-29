// ------------------------------------------------------------------------------
// Copyright Misys IBS
//
// Owner: Des Middlemass
//
// Class: EQClassLoaded: Provides interface for loading new classes and locating
// files on the class path.
// ------------------------------------------------------------------------------
package com.misys.equation.common.internal.eapi.core;

import java.io.InputStream;
import java.util.Properties;

import com.misys.equation.common.utilities.Toolbox;

/**
 * Responsible for loading classes.
 * <P>
 * Extends the java.lang.ClassLoader to provide additional static members for obtaining Class inforamation and locating Properties
 * files in the class path.
 * <P>
 */
public class EQClassLoader extends ClassLoader
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQClassLoader.java 9706 2010-11-05 14:59:15Z MACDONP1 $";
	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006 </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";
	/**
	 * Default Constructor.
	 */
	public EQClassLoader()
	{
		super();
	}
	/*******************************************************************************************************************************
	 * Returns the Class object associated with the class or interface with the given string name. A call to forName("X") causes the
	 * class named X to be initialized.
	 * <P>
	 * 
	 * @param strClass
	 *            the fully qualified name of the desired class.
	 * @return the Class object for the class with the specified name.
	 * @throws EQException
	 *             if the class is not found.
	 */
	public static final Class<?> load(String strClass) throws EQException
	{
		try
		{
			return Class.forName(strClass);
		}
		catch (ClassNotFoundException ex)
		{
			throw new EQException("class [" + strClass + "] : Not Found " + Toolbox.getExceptionMessage(ex));
		}
	}
	/*******************************************************************************************************************************
	 * Find a properties file and return a Properties object loaded from the file.
	 * <P>
	 * The class path is searched for a file of the specified name. If located a Properties object is created and the files contents
	 * are loaded.
	 * <P>
	 * 
	 * @param strFileName
	 *            the name fo the configuration file to load.
	 * @return the Properties object containing the files properties.
	 * @throws EQException
	 *             if the file is not found or an IO error occurs.
	 */
	public static final Properties loadPropertiesFile(String strFileName) throws EQException
	{
		try
		{
			InputStream input = null;
			// load the resource via application class loader (and its parents)
			Class<EQClassLoader> cls = EQClassLoader.class;
			input = cls.getClassLoader().getResourceAsStream(strFileName);
			if (input == null)
			{
				throw new java.io.FileNotFoundException();
			}
			Properties appProp = new java.util.Properties();
			appProp.load(input);
			input.close();

			return appProp;
		}
		catch (java.io.FileNotFoundException ex)
		{
			throw new EQException(strFileName + " : Config File not Found : " + Toolbox.getExceptionMessage(ex));
		}
		catch (java.io.IOException ex)
		{
			throw new EQException(strFileName + " : Config File I/O Exception : " + Toolbox.getExceptionMessage(ex));
		}
	}
}
