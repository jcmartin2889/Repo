package com.misys.equation.function.adaptor;

import com.misys.equation.function.runtime.UserData;

public interface ILayoutAdaptor
{
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
	public int prevScreen(int curScreen, UserData userData);

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
	public int nextScreen(int curScreen, UserData userData);

}
