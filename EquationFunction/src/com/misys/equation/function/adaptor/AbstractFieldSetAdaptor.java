package com.misys.equation.function.adaptor;

import com.misys.equation.function.runtime.UserData;

/**
 * Default implementation of FieldSet exit methods in the IFieldSetAdaptor interface.
 * <p>
 * This class must be used as the super class for the user exit inner class corresponding to FieldSets. The user implementation will
 * override these default implementations as required.
 * <p>
 * Note that unlike the bean model, a flat model is used. For example, in the bean model, Validate PVs are children of fields, but
 * there will not be a similar inner class hierarchy.
 * 
 * @see FieldSetAdaptor
 * @see IFieldSetAdaptor
 */
public abstract class AbstractFieldSetAdaptor implements IFieldSetAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractFieldSetAdaptor.java 14796 2012-11-05 11:52:03Z williae1 $";
	/**
	 * Controls whether the API or PV module represented by this FieldSet should be executed or not.
	 * <p>
	 * Note that this is only relevant to API and PV FieldSets
	 * 
	 * @param userData
	 *            - the User Data
	 * 
	 * @return true to indicate the module should be executed
	 */
	public boolean shouldExecuteModule(UserData userData)
	{
		// Default implementation is to return true
		return true;
	}
}
