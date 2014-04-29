package com.misys.equation.function.adaptor;

import com.misys.equation.function.runtime.UserData;

/**
 * Default implementation of Display Attributes Set exit points for a field
 * <p>
 * This class must be used as the super class for the inner class corresponding to Display Attributes Set for a field. The user
 * implementation can override these default implementations of the methods in the IAttributesSetAdaptor interface.
 * <p>
 * TODO: These exit points require review.
 * 
 * @see IAttributesSetAdaptor
 * @see LayoutAdaptor
 */
public abstract class AbstractAttributesSetAdaptor implements IAttributesSetAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractAttributesSetAdaptor.java 10932 2011-05-10 08:10:39Z capilid1 $";

	/**
	 * Determine if the finish button is enabled or not. This user exit is invoked whenever the finish button is to be displayed
	 * 
	 * @param userData
	 *            - the user data
	 * 
	 * @return true if the finish button is enabled
	 */
	public boolean isFinishButtonEnabled(UserData userData)
	{
		return true;
	}
}
