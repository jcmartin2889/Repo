package com.misys.equation.function.tools;

import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionInfo;

public class GlobalPromptGenerator
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalPromptGenerator.java 10420 2011-02-03 12:22:26Z MACDONP1 $";
	/** Logger for this class */
	private static final EquationLogger LOG = new EquationLogger(GlobalPromptGenerator.class);

	private FunctionHandler functionHandler;
	private FunctionInfo functionInfo;
	private HTMLToolbox htmlToolbox;

	public GlobalPromptGenerator(EquationUser user)
	{
		try
		{
			// create the function handler
			functionInfo = new FunctionInfo("SESSIONID", "NAME");
			functionHandler = new FunctionHandler(user, functionInfo);

			// Initialise HTMLToolbox
			htmlToolbox = new HTMLToolbox();
			htmlToolbox.setSession(functionHandler.rtvEquationSession());
			htmlToolbox.setEqUser(user);

		}
		catch (Exception e)
		{
			LOG.error(e);
		}
	}

	public String getPVString(String pvName, Function function, InputField inputField, FunctionData functionData)
	{
		htmlToolbox.setFunctionData(functionData);
		String returnString = "";
		try
		{
			returnString = htmlToolbox.getScriptPMT(function, inputField, pvName, "", "");

			// replace with header
			int end = returnString.indexOf(',');
			returnString = "setPromptButtonNoWF(header" + returnString.substring(end, returnString.length());
		}
		catch (EQException e)
		{
			LOG.error(e);
		}
		return returnString;
	}
}
