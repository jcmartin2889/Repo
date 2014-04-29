package com.misys.equation.function.adaptor;

import com.misys.equation.function.runtime.UserData;

/**
 * Interface of methods applicable to DisplayAttributes exit processing.
 * <p>
 * This currently allows user control over a field's visible and protected states
 * <p>
 * This is used at runtime by the AttributesAdaptor which uses this interface to interact with the user exit code. The
 * AbstractAttributesAdaptor implements this interface, and is in turn intended to be extended by the user code.
 * 
 * @see AttributesAdaptor
 * @see AbstractAttributesAdaptor
 */
public interface IAttributesAdaptor
{
	/**
	 * Determine whether the field is visible or not
	 * 
	 * @param userData
	 *            - the User Data
	 * 
	 * @return true if visible
	 */
	public boolean isVisible(UserData userData);

	/**
	 * Determine whether the field is protected or not
	 * 
	 * @param userData
	 *            - the User Data
	 * 
	 * @return true if protected
	 */
	public boolean isProtected(UserData userData);

	/**
	 * Return the parameter to use to edit this field
	 * 
	 * @param userData
	 *            - the User Data
	 * 
	 * @return the parameter to use to edit this field
	 */
	public String getEditingParameter(UserData userData);

	/**
	 * Determine whether the field is visible or not in the list
	 * 
	 * @param userData
	 *            - the User Data
	 * 
	 * @return true if visible
	 */
	public boolean isInGridVisible(UserData userData);

}
