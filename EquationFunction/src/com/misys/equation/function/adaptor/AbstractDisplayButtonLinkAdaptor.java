package com.misys.equation.function.adaptor;

import com.misys.equation.function.runtime.UserData;

/**
 * Default implementation of Display Button or Link exit points for a field
 * <p>
 * This class must be used as the super class for the inner class corresponding to Display Button or Link for a field. The user
 * implementation can override these default implementations of the methods in the IAttributesAdaptor interface.
 * <p>
 * TODO: These exit points require review.
 * 
 * @see IDisplayButtonLinkAdaptor
 * @see LayoutAdaptor
 */
public abstract class AbstractDisplayButtonLinkAdaptor implements IDisplayButtonLinkAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractDisplayButtonLinkAdaptor.java 11239 2011-06-21 06:40:49Z yzobdabu $";

	/**
	 * Determine if the button or link is visible or not. This user exit is invoked whenever the button or link is to be displayed
	 * 
	 * @param userData
	 *            - the user data
	 * 
	 * @return true if the field is visible
	 */
	public boolean isVisible(UserData userData)
	{
		return true;
	}

	/**
	 * Determine if the button or link is protected or not. This user exit is invoked whenever the button or link is to be displayed
	 * 
	 * @param userData
	 *            - the user data
	 * 
	 * @return true if the field is protected
	 */
	public boolean isProtected(UserData userData)
	{
		return false;
	}

	/**
	 * Returns the command and its parameters
	 * 
	 * @param userData
	 *            - the user data
	 * 
	 * @return the command and its parameters
	 */
	public String getCommandParameter(UserData userData)
	{
		return "";
	}
}
