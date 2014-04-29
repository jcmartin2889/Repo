package com.misys.equation.function.adaptor;

import com.misys.equation.function.runtime.UserData;

/**
 * Interface of methods applicable to DisplayLabel exit processing.
 * <p>
 * This currently allows user control over a field's visible and protected states
 * <p>
 * This is used at runtime by the DisplayLabelAdaptor which uses this interface to interact with the user exit code. The
 * AbstractDisplayLabelAdaptor implements this interface, and is in turn intended to be extended by the user code.
 * 
 * @see DisplayLabelAdaptor
 * @see AbstractDisplayLabelAdaptor
 */
public interface IDisplayLabelAdaptor
{
	/**
	 * Determine whether the label is visible or not
	 * 
	 * @param userData
	 *            - the User Data
	 * 
	 * @return true if visible
	 */
	public boolean isVisible(UserData userData);
}
