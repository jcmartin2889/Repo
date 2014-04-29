package com.misys.equation.function.adaptor;

import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandlerData;
import com.misys.equation.function.runtime.UserData;
import com.misys.equation.function.runtime.UserModifyData;

public class Adaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: Adaptor.java 14796 2012-11-05 11:52:03Z williae1 $";
	protected FunctionHandlerData fhd;
	protected FunctionData functionData;
	protected UserData userData;

	/**
	 * Set the function data
	 * 
	 * @param functionData
	 *            - the Function data
	 */
	public void setFunctionData(FunctionHandlerData fhd, FunctionData functionData)
	{
		this.fhd = fhd;
		this.functionData = functionData;
		this.userData = new UserData(fhd, functionData);
	}

	/**
	 * Return the user modifiable data
	 * 
	 * @return the user modifiable data
	 */
	public UserModifyData getUserModifyData()
	{
		return new UserModifyData(fhd, functionData);
	}

	/**
	 * Return the user modifiable data
	 * 
	 * @param fd
	 *            - the function data
	 * 
	 * @return the user modifiable data
	 */
	public UserModifyData getUserModifyData(FunctionData fd)
	{
		return new UserModifyData(fhd, fd);
	}

}
