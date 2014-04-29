// ------------------------------------------------------------------------------
// Copyright Misys IBS
//
// Owner: Des Middlemass
//
// Class: EQAppEnviroment: Class for providing an API to a configuration file.
// ------------------------------------------------------------------------------
package com.misys.equation.common.internal.eapi.core;

import java.sql.Connection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Entry point for Equation Enhanced API's.
 * <P>
 * This is the first class that should be instantiated. From this singleton, EQSession objects can be created. From EQSession
 * instances, EQEnquiry, EQTransaction, and EQPrompt objects and their subclasses can be created.
 * <P>
 * This class also provides an easy way to access the application's configuration (property) file and its simple name value pairs.
 * <P>
 * The application can specify the name of the properties file or rely on the default name of EQ.config. The file must be on the
 * class path for the application.
 * <P>
 * An application obtains access to this instance by calling getAppEnvironment and then calls getProprty to read the file.
 * <P>
 * This class is used by other classes to obtain configuration information. For example, the ebs apis use it to determine parameters
 * for the connection to the host, and for certain behavioural characteristics of the api's themselves.
 * <P>
 * See the sample EQ.config file provided for details.
 */
public class EQEnvironment
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQEnvironment.java 5234 2009-10-30 12:16:51Z macdonp1 $";
	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006 </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";
	// Whether we are enabled or not
	private static boolean enabled = true;
	/* private EQEnvironment _appEnvironment; */
	private static EQEnvironment _appEnvironment = null;
	// Application environment properties file name
	private static final String APPENV_PROPERTIES = "EQ.config";
	private static String APPLICATION_CONFIG_FILE = null;
	// Application environment folder name
	private static String configPath = null;
	// Application environment properties repository
	private Properties appProp = null;
	// Application environment hash maps of Equation units
	private static Map<String, EQUnit> eqUnitHash = new HashMap<String, EQUnit>();
	/*******************************************************************************************************************************
	 * The default constructor which will read the environment information from the config file.
	 */
	protected EQEnvironment() throws EQException
	{
		if (enabled)
		{
			// try the application file first
			if (APPLICATION_CONFIG_FILE != null)
			{
				loadConfig(APPLICATION_CONFIG_FILE);
			}
			else
			{
				// try our default
				loadConfig(APPENV_PROPERTIES);
			}
		}
		else
		{
			appProp = new Properties();
		}
	}
	/**
	 * Creates new instance of this class with the given properties.
	 * 
	 * @param properties
	 */
	protected EQEnvironment(Properties properties)
	{
		this.appProp = properties;
	}
	/**
	 * Disable the user of the environment for applications which don't require it.
	 * <P>
	 * This should be called prior to any classes dependant on the environment are loaded.
	 */
	public static void disable()
	{
		enabled = false;
	}
	/**
	 * Determine if the environment is enabled.
	 * <P>
	 * 
	 * @return if the environment is enabled
	 */
	public static boolean isEnabled()
	{
		return enabled;
	}
	/**
	 * Specify the application's configuration file.
	 * <P>
	 * This should be either a full path name to the file, or a simple file name which is then located on the class path.
	 * <P>
	 * This must be called prior to the first instantiation of the EQEnvironment class. This is triggered by the loading of most of
	 * the Equation API classes.
	 * <P>
	 * If no file is set the default "EQ.config" is searched for.
	 * <P>
	 * 
	 * @param configFileName
	 *            the name or path of the configuration file
	 */
	public static void setConfigFileName(String configFileName)
	{
		APPLICATION_CONFIG_FILE = configFileName;
	}
	/**
	 * Get the static EQEnvironment instance.
	 * <P>
	 * The first call to this method in the JVM causes the singleton instance to be instantiated. The class looks for the specified
	 * configuration file on the class path.
	 * <P>
	 * 
	 * @return the EQEnvironment.
	 * @throws EQException
	 *             if the configuration file is not found.
	 */
	public static synchronized EQEnvironment getAppEnvironment() throws EQException
	{
		// Create our one and only instance of this class
		if (_appEnvironment == null)
		{
			_appEnvironment = new EQEnvironment();
		}
		return _appEnvironment;
	}
	/**
	 * Get the static EQEnvironment instance.
	 * <P>
	 * The first call to this method in the JVM causes the singleton instance to be instantiated. The class looks for the specified
	 * configuration file on the class path.
	 * <P>
	 * 
	 * @param properties
	 * 
	 * @return the EQEnvironment.
	 */
	public static synchronized EQEnvironment getAppEnvironment(Properties properties)
	{
		// Create our one and only instance of this class
		if (_appEnvironment == null)
		{
			_appEnvironment = new EQEnvironment(properties);
		}
		return _appEnvironment;
	}
	/**
	 * Get the names of all properties in the configuration file.
	 * <P>
	 * 
	 * @return an enumeration of all of the keys in the property list.
	 */
	public Enumeration<?> getPropertyNames()
	{
		if (appProp != null)
		{
			return appProp.propertyNames();
		}
		else
		{
			return null;
		}
	}
	/**
	 * Get the value for a property from the configuration file.
	 * <P>
	 * 
	 * @param sKey
	 *            the name of the property to read from the file.
	 * @return the value of the property, or "" if the property is not found.
	 */
	public String getProperty(String sKey)
	{
		String sValue = "";
		if (appProp != null)
		{
			Object val = appProp.get(sKey);
			if (val != null)
			{
				sValue = val.toString();
			}
		}
		return sValue;
	}
	/**
	 * Get the value for a property from the configuration file.
	 * <P>
	 * 
	 * @param sKey
	 *            the name of the property to read from the file.
	 * @param defValue
	 *            a default value to return if the property is not found.
	 * @return the value of the property, or the param defValue if the property is not found.
	 */
	public String getProperty(String sKey, String defValue)
	{
		String sValue = defValue;
		if (appProp != null)
		{
			Object val = appProp.get(sKey);
			if (val != null)
			{
				sValue = val.toString();
			}
		}
		return sValue;
	}
	/**
	 * Get the file system path for the location of the configuration file.
	 * <P>
	 * When the specified configuration file is located on the class path the full path for the file is recored. This path can then
	 * be read using this method.
	 * <P>
	 * This is useful if the application wishes to read other files from the same location.
	 * <P>
	 * 
	 * @return the path for the location of the application environment configuration file.
	 */
	public String getPath()
	{
		return configPath;
	}
	/*******************************************************************************************************************************
	 * Look for the file on the class path.
	 */
	private void loadConfig(String file) throws EQException
	{
		appProp = EQClassLoader.loadPropertiesFile(file);
		// Find and store the location of the configuration files
		Class<EQClassLoader> cls = EQClassLoader.class;
		java.net.URL location = cls.getClassLoader().getResource(file);
		if (location == null)
		{
			throw new EQException(file + " : Config File not Found");
		}
		configPath = location.getPath();
		configPath = configPath.substring(0, configPath.length() - file.length());
	}
	/*******************************************************************************************************************************
	 * get a unit using the system name and unit name
	 */
	protected EQUnit getUnit(String system, String unit)
	{
		return eqUnitHash.get(system + unit);
	}
	/*******************************************************************************************************************************
	 * deterimine if the system/unit already exists
	 */
	protected boolean hasUnit(String system, String unit)
	{
		return eqUnitHash.containsKey(system + unit);
	}
	/*******************************************************************************************************************************
	 * add the unit to the collection of system/units
	 */
	protected void addUnit(EQUnit unit)
	{
		eqUnitHash.put(unit.getSystemName() + unit.getUnitMnemonic(), unit);
	}
	/*******************************************************************************************************************************
	 * Create an EQSession using an existing connection to the host.
	 * <P>
	 * The credentials for Equation must be provided
	 * <P>
	 * 
	 * @param connection
	 *            an existing connection to the host.
	 * @param properties
	 *            the properties of the new EQSession.
	 * @param equationUserIdentifier
	 *            the user id of the Equation user for this session.
	 * @param equationUserPassword
	 *            the Eqaution user's password.
	 * @return the EQSession that was created.
	 * @throws EQException
	 *             if the connection cannot be established.
	 */
	public EQSession createEQSession(Connection connection, EQSessionProperties properties, String equationUserIdentifier,
					char[] equationUserPassword) throws EQException
	{
		EQSessionImpl session = new EQSessionImpl(connection, properties, equationUserIdentifier, equationUserPassword);
		return session;
	}
	/*******************************************************************************************************************************
	 * Create a new connection to the host and an EQSession.
	 * <P>
	 * The connection will be established using the DB parameters configured in the application config file, which is found on the
	 * CLASSPATH. The credentials for the connection and Equation must be provided
	 * <P>
	 * 
	 * @param properties
	 *            the properties of the new EQSession.
	 * @param DBUserIdentifier
	 *            a valid database user id.
	 * @param DBUserPassword
	 *            the database user's password.
	 * @param equationUserIdentifier
	 *            the user id of the Equation user for this session.
	 * @param equationUserPassword
	 *            the Equation user's password.
	 * @return the EQSession that was created.
	 * @throws EQException
	 *             if the connection cannot be established.
	 */
	public EQSession createEQSession(EQSessionProperties properties, String DBUserIdentifier, char[] DBUserPassword,
					String equationUserIdentifier, char[] equationUserPassword) throws EQException
	{
		EQSessionImpl session = new EQSessionImpl(properties, DBUserIdentifier, DBUserPassword, equationUserIdentifier,
						equationUserPassword);
		return session;
	}
}
