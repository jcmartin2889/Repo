package com.misys.equation.function.adaptor;

import java.lang.reflect.Constructor;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.beans.GAZRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.FunctionClassLoader;
import com.misys.equation.function.runtime.UserData;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;
import com.misys.equation.function.useraccess.IUserExitLayout;

public class UserExitLayoutAdaptor extends Adaptor implements IUserExitLayout
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UserExitLayoutAdaptor.java 14797 2012-11-05 11:53:28Z williae1 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(UserExitLayoutAdaptor.class);

	protected IUserExitLayout userExitLayoutImpl;
	/**
	 * Construct a User Exit Layout Adaptor
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param classLoader
	 * @param optionId
	 *            - the option id of the Equation service
	 * @param isMisysUserExit
	 *            - isMisysUserExit
	 * @throws EQException
	 */
	public UserExitLayoutAdaptor(EquationStandardSession session, FunctionClassLoader classLoader, String optionId,
					boolean isMisysUserExit) throws EQException
	{
		this.userExitLayoutImpl = loadUserExitInstance(session, classLoader, optionId, isMisysUserExit);
	}
	/**
	 * Load the user exit class
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param classLoader
	 * @param optionId
	 *            - the option id of the Equation service
	 * @param isMisysUserExit
	 *            - isMisysUserExit
	 * @return the class
	 */
	public IUserExitLayout loadUserExitInstance(EquationStandardSession session, FunctionClassLoader classLoader, String optionId,
					boolean isMisysUserExit)
	{
		try
		{
			Class c = null;
			if (isMisysUserExit)
			{
				c = getMisysUserExitLayoutClass(session, classLoader, optionId);
			}
			else
			{
				c = getUserExitLayoutClass(session, classLoader, optionId);
			}

			if (c != null)
			{
				Constructor constructor = c.getConstructor();
				Object instance = constructor.newInstance();

				if (instance instanceof IUserExitLayout)
				{
					return (IUserExitLayout) instance;
				}
				else
				{
					return null;
				}
			}
			else
			{
				return null;
			}
		}

		catch (Exception e)
		{
			LOG.error(e);
			return null;
		}
	}
	/**
	 * Return the class definition of a User Exit Layout Adaptor
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param classLoader
	 *            - the Function class loader
	 * @param option
	 *            id - the option id of the Equation service
	 * 
	 * @return the class definition for the option
	 */
	public Class<IUserExitLayout> getUserExitLayoutClass(EquationStandardSession session, FunctionClassLoader classLoader,
					String option) throws EQException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("getInstance - loading definition of class for option [" + option + "]");
		}

		Class<IUserExitLayout> c = classLoader.loadClass(session, "", option + FunctionRuntimeToolbox.USER_EXIT_LAYOUT_SUFFIX, "",
						GAZRecordDataModel.TYP_SERVICE_USEREXIT);
		return c;
	}
	/**
	 * Return the class definition of a Misys User Exit Layout Adaptor
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param classLoader
	 *            - the Function class loader
	 * @param option
	 *            id - the option id of the Equation service
	 * 
	 * @return the class definition for the option
	 */
	public Class<IUserExitLayout> getMisysUserExitLayoutClass(EquationStandardSession session, FunctionClassLoader classLoader,
					String option) throws EQException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("getInstance - loading definition of class for option [" + option + "]");
		}

		Class<IUserExitLayout> c = classLoader.loadClass(session, "",
						option + FunctionRuntimeToolbox.MISYS_USER_EXIT_LAYOUT_SUFFIX, "", GAZRecordDataModel.TYP_SERVICE_USEREXIT);
		return c;
	}
	/**
	 * Determine the previous screen
	 * 
	 * @param curScreen
	 *            - the current screen
	 * @param userData
	 *            - the User Data
	 * 
	 * @return the previous screen (or 0 to ignore the user exit)
	 */
	public int prevScreen(int curScreen, UserData userData)
	{

		// call a bank-defined default user exit
		if (userExitLayoutImpl != null)
		{
			return userExitLayoutImpl.prevScreen(curScreen, userData);

		}
		return 0;

	}

	/**
	 * Determine the next screen
	 * 
	 * @param curScreen
	 *            - the current screen
	 * @param userData
	 *            - the User Data
	 * 
	 * @return the next screen (or 0 to ignore the user exit)
	 */
	public int nextScreen(int curScreen, UserData userData)
	{

		// call a bank-defined default user exit
		if (userExitLayoutImpl != null)
		{
			return userExitLayoutImpl.nextScreen(curScreen, userData);
		}

		return 0;
	}

	/**
	 * Determine if the user exit exists
	 * 
	 * @return if the user exit exists
	 */
	public boolean userExitExists()
	{

		// call a bank-defined default user exit
		if (userExitLayoutImpl != null)
		{
			return true;
		}

		return false;
	}

}
