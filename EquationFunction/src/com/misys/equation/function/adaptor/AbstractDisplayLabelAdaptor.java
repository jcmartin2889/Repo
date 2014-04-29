package com.misys.equation.function.adaptor;

import com.misys.equation.function.runtime.UserData;

/**
 * Default implementation of Display Label exit points for a field
 * <p>
 * This class must be used as the super class for the inner class corresponding to Display Label for a field. The user
 * implementation can override these default implementations of the methods in the IAttributesAdaptor interface.
 * <p>
 * TODO: These exit points require review.
 * 
 * @see IDisplayLabelAdaptor
 * @see LayoutAdaptor
 */
public abstract class AbstractDisplayLabelAdaptor implements IDisplayLabelAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractDisplayLabelAdaptor.java 10552 2011-02-25 11:17:20Z capilid1 $";

	/**
	 * Determine if the label is visible or not. This user exit is invoked whenever the label is to be displayed
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
}
