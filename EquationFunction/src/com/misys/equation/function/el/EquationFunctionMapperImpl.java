package com.misys.equation.function.el;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.utilities.EquationLogger;

/**
 * This class makes Java methods available to the EL script as function definitions.
 * <P>
 * Misys-defined functions will have a prefix of fn and non-Misys defined functions will have a prefix that starts with letter "u"
 * <P>
 * Non-Misys defined functions or user defined functions are loaded from a configuration property. The config property contains list
 * of user defined class names, location of the jar where the class can be found and unique prefixes to identify each functions.
 * <P>
 * Given a prefix of fn and a function name of substring, an example EL script could look like the following:
 * <P>
 * <code>${fn:substring(parm1,'1','3')}</code>
 * <P>
 * For user defined functions, an example EL script could look like the following:
 * <P>
 * <code>${ua:getPassword(parm1,'1','3')}</code>
 * <P>
 * <code>${ub:startsWith('abc')}</code>
 */
public class EquationFunctionMapperImpl extends org.apache.el.lang.FunctionMapperImpl
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationFunctionMapperImpl.java 11238 2011-06-21 06:39:39Z yzobdabu $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EquationFunctionMapperImpl.class);

	/** The namespace for EQ4 functions */
	public static final String FUNCTION_NAMESPACE = "fn";

	/** The config property name from equationConfiguration.properties */
	public static final String EQ_USEREL = "eq.userel";

	/** Separator for the list of class entries in eq.userel configuration property */
	public static final String EQ_USEREL_CLASSLIST_DELIMITER = ",";

	/** Separator for the namespace, jar location and classname */
	public static final String EQ_USEREL_CLASS_ENTRY_DELIMITER = "|";

	/** Prefix used by the name space */
	public static final String USER_DEFINED_FUNCTION_NAMESPACE_PREFIX = "u";

	/** The first class defined in the eq.userel config property */
	private Class<?> firstClassFromEqUserElConfig;

	/**
	 * Constructor that adds all the public, static methods in the <code>StringFunctions</code> class to the collection of
	 * Functions.
	 * <p>
	 * It also adds all the public, static methods of the user classes that are defined in equationConfiguration.properties
	 * 
	 * @see StringFunctions
	 */
	public EquationFunctionMapperImpl()
	{
		super();
		try
		{
			Method[] methods = StringFunctions.class.getMethods();
			for (int i = 0; i < methods.length; i++)
			{
				Method method = methods[i];
				if (method.getModifiers() == Modifier.PUBLIC + Modifier.STATIC)
				{
					addFunction(FUNCTION_NAMESPACE, method.getName(), method);
				}
			}

			addFunctionFromConfiguration(EquationCommonContext.getConfigProperty(EQ_USEREL));
		}
		catch (SecurityException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Splits and processes every class name in the list. This will keep track of the first class in the list as the first class may
	 * contain the getPassword() method that is used for encrypting passwords.
	 * 
	 * @param classList
	 *            - class list from equation configuration property defined in <code>EQ_USEREL</code>
	 */
	private void addFunctionFromConfiguration(String classList)
	{
		Class<?> classObject = null;
		ArrayList<String> nameSpaceArray = new ArrayList<String>();

		if (!classList.equals(""))
		{

			// Split entries by comma
			String[] entries = classList.split(EQ_USEREL_CLASSLIST_DELIMITER);

			// For each entry
			boolean isFirstClass = true;
			for (String entry : entries)
			{
				if (entry == null || entry.trim().length() == 0)
				{
					continue;
				}

				// Split the entry by "|", an entry should contain a namespace, jar file and classname
				String[] namespaceJarAndClassname = entry.split("\\" + EQ_USEREL_CLASS_ENTRY_DELIMITER);

				int entryLength = namespaceJarAndClassname.length;
				if (namespaceJarAndClassname == null || (entryLength < 2 && entryLength > 3))
				{
					LOG.error("List of jars and classes defined in \"" + EQ_USEREL + "\" config property is not in a valid format");
					return;
				}

				String namespace = namespaceJarAndClassname[0].trim();
				String className = namespaceJarAndClassname[1].trim();
				String jarFileLoc = null;
				if (entryLength == 3)
				{
					jarFileLoc = namespaceJarAndClassname[2].trim();
				}

				// namespace should start with the right prefix
				if (!namespace.startsWith(USER_DEFINED_FUNCTION_NAMESPACE_PREFIX))
				{
					LOG.error("Identifiers in \"" + EQ_USEREL + "\" config property should start with \""
									+ USER_DEFINED_FUNCTION_NAMESPACE_PREFIX + "\"");
					continue;
				}

				if (nameSpaceArray.contains(namespace))
				{
					LOG.error("Identifiers in \"" + EQ_USEREL + "\" config property should be unique");
					continue;
				}

				nameSpaceArray.add(namespace);

				try
				{
					classObject = addFunction(namespace, className, jarFileLoc);
					LOG.debug("Class " + className + " loaded successfully");
				}
				catch (Exception e)
				{
					LOG.error("System was not able to load class " + entry);
					LOG.error(e);
					continue;
				}

				// Get the first class for and store for later use
				if (isFirstClass)
				{
					firstClassFromEqUserElConfig = classObject;
					isFirstClass = false;
				}

			}

		}
	}

	/**
	 * Loads the jar and class
	 * 
	 * @param nameSpace
	 *            - unique identifier for the function
	 * @param className
	 *            - class full name
	 * @param jarFileLoc
	 *            - jar file where the class can be found, if not specified the class should be defined in the class path
	 * 
	 * @return the Class if the class has been loaded successfully, otherwise returns a null
	 * 
	 * @throws Exception
	 */
	public Class<?> addFunction(String nameSpace, String className, String jarFileLoc) throws Exception
	{
		Class<?> classReflect = null;
		if (jarFileLoc == null || jarFileLoc.length() == 0)
		{
			classReflect = Class.forName(className);
			addFunction(nameSpace, classReflect);
		}
		else
		{
			File file = new File(jarFileLoc);
			URLClassLoader classLoader = new URLClassLoader(new URL[] { file.toURL() });
			classReflect = classLoader.loadClass(className);
			addFunction(nameSpace, classReflect);
		}
		return classReflect;
	}

	/**
	 * Adds the public static methods of the class to the el context
	 * 
	 * @param nameSpace
	 *            - unique identifier for the function
	 * @param classReflect
	 *            - class containing the methods to add to the El content
	 */
	private void addFunction(String nameSpace, Class<?> classReflect)
	{
		Method[] methods = classReflect.getMethods();
		StringBuilder builder = new StringBuilder("User defined function(s) loaded for class {" + classReflect.getName() + "}: ");
		for (int i = 0; i < methods.length; i++)
		{
			Method method = methods[i];
			if (method.getModifiers() == Modifier.PUBLIC + Modifier.STATIC)
			{
				builder.append(method.getName() + ",");
				addFunction(nameSpace, method.getName(), method);
			}
		}

		LOG.debug(builder.toString());
	}

	/**
	 * Calls the getPassword method from the user defined class to encrypt the user password
	 * <p>
	 * If the first class retrieved from the equationConfiguration property does not contain the getPassword method, the method will
	 * return blank
	 * 
	 * @param userId
	 *            - the user Id
	 * @param password
	 *            - the user password
	 * @param passwordType
	 *            - this will identify where the password provided is a token or a user password
	 * 
	 * @return the encrypted password
	 */
	public String getPassword(String userId, String password, String passwordType)
	{
		if (firstClassFromEqUserElConfig == null)
		{
			return "";
		}

		try
		{
			Method methodGetPassword = firstClassFromEqUserElConfig.getMethod("getPassword", String.class, String.class,
							String.class);
			return (String) methodGetPassword.invoke(firstClassFromEqUserElConfig, userId, password, passwordType);

		}
		catch (Exception e)
		{
			LOG.error(e);
			LOG.debug("Password is requested but the signature getPassword(String,String,String)"
							+ " does not exist in the user class " + firstClassFromEqUserElConfig.getName());
		}

		return "";
	}
}
