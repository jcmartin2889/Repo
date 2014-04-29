package com.misys.equation.bankfusion.tools;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

import com.misys.bankfusion.common.runtime.service.ServiceManager;
import com.misys.bankfusion.common.runtime.service.ServiceManagerFactory;
import com.misys.bankfusion.subsystem.persistence.IPersistenceObjectsFactory;
import com.misys.bankfusion.subsystem.persistence.IPersistenceService;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.language.LanguageResources;
import com.misys.equation.common.utilities.EquationLogger;
import com.trapedza.bankfusion.persistence.hibernatesupport.ClassLoaderFactory;

public class BFFunctionClassLoader extends ClassLoader
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: BFFunctionClassLoader.java 15716 2013-04-30 15:46:08Z whittap1 $";
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(BFFunctionClassLoader.class);

	/* List of loaded class names */
	private final List<String> classNames = new ArrayList<String>();

	private final DaoFactory daoFactory = new DaoFactory();

	private static BFFunctionClassLoader bfClassLoader;

	/**
	 * Construct a function class loader
	 * 
	 */
	public BFFunctionClassLoader()
	{

	}

	/**
	 * Load a class
	 * 
	 * @param connection
	 *            - the connection
	 * @param optionId
	 *            - the option id
	 * 
	 * @return the class
	 * 
	 * @throws ClassNotFoundException
	 */
	public synchronized Class loadClass(EquationStandardSession session, String fullClassName) throws EQException
	{
		Class<?> aclass = null;
		IPersistenceObjectsFactory factory = null;
		IPersistenceService pService = null;
		if (LOG.isDebugEnabled())
		{
			LOG.debug("loadClass - attempting to load class definition [" + fullClassName + "]");
		}

		// From version 3.0 if using XA, BF requires a transaction to be started before calling the class loader
		if (EquationCommonContext.getContext().isXAUsed())
		{
			pService = (IPersistenceService) ServiceManagerFactory.getInstance().getServiceManager().getServiceForName(
							ServiceManager.PERSISTENCE_SERVICE);
			factory = pService.getPersistenceFactory();
			factory.beginTransaction();
		}

		try
		{
			aclass = ClassLoaderFactory.getDynamicClassLoader().getNewClassInstance(fullClassName, true);
		}
		catch (ClassFormatError e)
		{
			if (LOG.isFatalEnabled())
			{
				LOG.fatal("loadClass - unable to load class definition [" + fullClassName + "]");
			}
			throw new EQException(LanguageResources.getFormattedString("LoadClass.ClassFormatError", fullClassName));
		}
		catch (NoClassDefFoundError e)
		{
			LOG.fatal("loadClass - unable to load class definition [" + fullClassName + "] due to [" + e.getMessage()
							+ "] NoClassDefFoundError ");
			// re-throw the error
			throw e;
		}
		catch (Exception e)
		{
			LOG.error("loadClass", e);
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
		finally
		{
			// End the XA transaction if one was started
			if (EquationCommonContext.getContext().isXAUsed() && factory != null)
			{
				factory.commitTransaction();
				factory.closePrivateSession();
			}
		}
		// store the class name in the ArrayList
		if (!classNames.contains(fullClassName))
		{
			classNames.add(fullClassName);
		}

		if (LOG.isDebugEnabled())
		{
			LOG.debug("loadClass - found and loaded a class definition for [" + fullClassName + "]");
		}

		return aclass;
	}
	/**
	 * Expose the findLoadedClass method
	 * 
	 * This allows the caller to check whether this instance has loaded a class. This is used to determine whether it is necessary
	 * to re-create this class loader when invalidating a class, and to avoid the overhead of obtaining an EqSession object at
	 * runtime.
	 * 
	 * @param className
	 * @return Class<?> The class definition if found, otherwise null
	 */
	public Class<?> getLoadedClass(String className)
	{
		return findLoadedClass(className);
	}

	/**
	 * Load a class
	 * 
	 * @param className
	 *            - the full name of the class
	 * 
	 * @return the class
	 * 
	 * @throws ClassNotFoundException
	 */
	@Override
	public Class<?> loadClass(String className) throws ClassNotFoundException
	{
		// See if we have already loaded this class:
		if (classNames.contains(className))
		{
			return findLoadedClass(className);
		}
		else
		{
			return super.loadClass(className);
		}
	}
	/**
	 * Return the BankFusion Function type class loader
	 * 
	 * @return the Function type class loader
	 */
	public static BFFunctionClassLoader getBFClassLoader()
	{
		if (bfClassLoader == null)
		{
			createNewBFClassLoader();

		}
		return bfClassLoader;
	}

	/**
	 * Create a new BankFusion Function type class loader
	 */
	private static void createNewBFClassLoader()
	{
		AccessController.doPrivileged(new PrivilegedAction<Void>()
		{
			public Void run()
			{
				bfClassLoader = new BFFunctionClassLoader();
				return null;
			}
		});
	}

}
