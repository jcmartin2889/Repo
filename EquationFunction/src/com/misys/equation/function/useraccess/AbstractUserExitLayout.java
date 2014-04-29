package com.misys.equation.function.useraccess;

import com.misys.equation.function.runtime.UserData;

public class AbstractUserExitLayout implements IUserExitLayout
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractUserExitLayout.java 14805 2012-11-05 11:57:52Z williae1 $";

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
	@Override
	public int prevScreen(int curScreen, UserData userData)
	{
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
	@Override
	public int nextScreen(int curScreen, UserData userData)
	{
		return 0;
	}

}
