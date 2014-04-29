package com.misys.equation.function.adaptor;

import com.misys.equation.function.runtime.UserModifyData;

/**
 * Interface of methods applicable to InputFieldSets exit processing.
 * <p>
 * This currently allows user control over this InputFieldSet's initialisation.
 * <p>
 * This interface is used at runtime by the InputFieldSetAdaptor which uses this interface to interact with the user exit code. The
 * AbstractInputFieldSetAdaptor implements this interface, and is in turn intended to be extended by the user code.
 * 
 * @see InputFieldSetAdaptor
 * @see AbstractInputFieldSetAdaptor
 */
public interface IInputFieldSetAdaptor
{
	/**
	 * Controls the InputFieldSet's initialisation.
	 * <p>
	 * 
	 * @param userModifyData
	 *            - the User Modify Data
	 */
	public void initialiseMode(UserModifyData userModifyData);

	/**
	 * Controls the InputFieldSet's default mode.
	 * <p>
	 * 
	 * @param userModifyData
	 *            - the User Modify Data
	 */
	public void defaultMode(UserModifyData userModifyData);

	/**
	 * Controls the InputFieldSet's validate mode.
	 * <p>
	 * 
	 * @param userModifyData
	 *            - the User Modify Data
	 */
	public void validateMode(UserModifyData userModifyData);

}
