package com.misys.equation.function.adaptor;

import com.misys.equation.function.runtime.UserData;

/**
 * Interface of methods applicable to DisplayAttributesSet exit processing.
 * <p>
 * This currently allows user control over the finish button's enable state
 * <p>
 * This is used at runtime by the AttributesSetAdaptor which uses this interface to interact with the user exit code. The
 * AbstractAttributesSetAdaptor implements this interface, and is in turn intended to be extended by the user code.
 * 
 * @see AttributesSetAdaptor
 * @see AbstractAttributesSetAdaptor
 */
public interface IAttributesSetAdaptor
{
	/**
	 * Determine whether the finish button is enabled or not
	 * 
	 * @param userData
	 *            - the User Data
	 * 
	 * @return true if enabled
	 */
	public boolean isFinishButtonEnabled(UserData userData);
}
