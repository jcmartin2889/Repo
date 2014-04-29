package com.misys.equation.function.adaptor;

import com.misys.equation.function.runtime.UserData;
import com.misys.equation.function.useraccess.UserExitMessages;

public interface IFieldAdaptor
{
	/**
	 * Return the messages
	 * 
	 * @return the list of messages
	 */
	public UserExitMessages getReturnMessages();

	/**
	 * Clear the messages
	 */
	public void clearMessages();

	/**
	 * Determine whether the field is mandatory or not
	 * 
	 * @param userData
	 *            - the User Data
	 * 
	 * @return true if mandatory
	 */
	public boolean isMandatory(UserData userData);

	/**
	 * Determine whether the field is valid or not
	 * 
	 * @param userData
	 *            - the User Data
	 * 
	 * @return true if valid
	 */
	public boolean isValid(UserData userData);

	/**
	 * Return the primitive value of the field
	 * 
	 * @param userData
	 *            - the User Data
	 * 
	 * @return the primitive value of the field (or null if the value is to be ignored)
	 */
	public String getPrimitiveValue(UserData userData);

	/**
	 * Return the output value of the field
	 * 
	 * @param userData
	 *            - the User Data
	 * 
	 * @return the output value of the field (or null if the value is to be ignored)
	 */
	public String getOutputValue(UserData userData);

	/**
	 * Return the input value of the field
	 * 
	 * @param userData
	 *            - the User Data
	 * 
	 * @return the input value of the field (or null if the value is to be ignored)
	 */
	public String getInputValue(UserData userData);

	/**
	 * Return the initial value of the field
	 * 
	 * @param userData
	 *            - the User Data
	 * 
	 * @return the initial value of the field (or null if the value is to be ignored)
	 */

	public String initialValue(UserData userData);

	/**
	 * Return the default value of the field
	 * 
	 * @param userData
	 *            - the User Data
	 * 
	 * @return the default value of the field (or null if the value is to be ignored)
	 */

	public String defaultValue(UserData userData);

}
