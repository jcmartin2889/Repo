package com.misys.equation.function.adaptor;

import com.misys.equation.function.runtime.UserData;

/**
 * Interface of methods applicable to FieldSets exit processing.
 * <p>
 * This currently allows user control over whether the API or PV module represented by this FieldSet should be executed or not.
 * <p>
 * This interface is used at runtime by the FieldSetAdaptor which uses this interface to interact with the user exit code. The
 * AbstractFieldSetAdaptor implements this interface, and is in turn intended to be extended by the user code.
 * 
 * @see FieldSetAdaptor
 * @see AbstractFieldSetAdaptor
 */
public interface IFieldSetAdaptor
{
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
	public boolean shouldExecuteModule(UserData userData);
}
