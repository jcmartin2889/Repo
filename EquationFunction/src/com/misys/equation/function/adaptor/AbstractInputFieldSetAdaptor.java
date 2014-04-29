package com.misys.equation.function.adaptor;

import com.misys.equation.function.runtime.UserModifyData;

/**
 * Default implementation of InputFieldSet exit methods in the IInputFieldSetAdaptor interface.
 * <p>
 * This class must be used as the super class for the user exit inner class corresponding to InputFieldSets. The user implementation
 * will override these default implementations as required.
 * <p>
 * Note that unlike the bean model, a flat model is used. For example, in the bean model, Validate PVs are children of fields, but
 * there will not be a similar inner class hierarchy.
 * 
 * @see InputFieldSetAdaptor
 * @see IInputFieldSetAdaptor
 */
public abstract class AbstractInputFieldSetAdaptor implements IInputFieldSetAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractInputFieldSetAdaptor.java 14796 2012-11-05 11:52:03Z williae1 $";

	/**
	 * Controls the InputFieldSet's initialisation.
	 * <p>
	 * 
	 * @param userModifyData
	 *            - the User Modify Data
	 */
	public void initialiseMode(UserModifyData userModifyData)
	{
	}

	/**
	 * Controls the InputFieldSet's default mode.
	 * <p>
	 * 
	 * @param userModifyData
	 *            - the User Modify Data
	 */
	public void defaultMode(UserModifyData userModifyData)
	{
	}

	/**
	 * Controls the InputFieldSet's validate mode.
	 * <p>
	 * 
	 * @param userModifyData
	 *            - the User Modify Data
	 */
	public void validateMode(UserModifyData userModifyData)
	{
	}

}
