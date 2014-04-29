package com.misys.equation.function.adaptor;

import com.misys.equation.function.runtime.UserData;

/**
 * Interface of methods applicable to DisplayButtonLink exit processing.
 * <p>
 * This currently allows user control over a field's visible and protected states
 * <p>
 * This is used at runtime by the DisplayButtonLinkAdaptor which uses this interface to interact with the user exit code. The
 * AbstractDisplayButtonLinkAdaptor implements this interface, and is in turn intended to be extended by the user code.
 * 
 * @see DisplayButtonLinkAdaptor
 * @see AbstractDisplayButtonLinkAdaptor
 */
public interface IDisplayButtonLinkAdaptor
{
	/**
	 * Determine whether the button or link is visible or not
	 * 
	 * @param userData
	 *            - the User Data
	 * 
	 * @return true if visible
	 */
	public boolean isVisible(UserData userData);

	/**
	 * Determine whether the button or link is protected or not
	 * 
	 * @param userData
	 *            - the User Data
	 * 
	 * @return true if protected
	 */
	public boolean isProtected(UserData userData);

	/**
	 * Return the command and parameters
	 * 
	 * @param userData
	 *            - the User Data
	 * 
	 * @return the command and parameters
	 */
	public String getCommandParameter(UserData userData);

}
