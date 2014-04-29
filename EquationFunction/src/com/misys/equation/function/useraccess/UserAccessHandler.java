package com.misys.equation.function.useraccess;

import bf.com.misys.eqf.types.header.Formatting;
import bf.com.misys.eqf.types.header.RqHeader;
import bf.com.misys.eqf.types.header.ServiceRqHeader;
import bf.com.misys.eqf.types.header.StartLRPProcessRsHeader;

import com.misys.equation.bankfusion.lrp.engine.ITaskEngine;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.context.EquationFunctionContext;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionHandlerData;
import com.misys.equation.function.runtime.ScreenSet;
import com.misys.equation.function.runtime.ScreenSetAC2;
import com.misys.equation.function.runtime.ScreenSetCRM;
import com.misys.equation.function.runtime.ScreenSetHandler;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;

public class UserAccessHandler
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UserAccessHandler.java 17343 2013-09-23 12:04:01Z perkinj1 $";

	// Log
	private static final EquationLogger LOG = new EquationLogger(UserAccessHandler.class);

	private final FunctionHandler fh;
	private final FunctionHandlerData fhd;

	/**
	 * Construct a client service handler using the given Function Handler
	 * 
	 * @param fh
	 *            - the FunctionHandler
	 */
	public UserAccessHandler(FunctionHandler fh)
	{
		this.fh = fh;
		this.fhd = fh.getFhd();
	}

	/**
	 * Construct a client service handler using the given Function Handler Data
	 * 
	 * @param fhd
	 *            - the FunctionHandlerData
	 */
	public UserAccessHandler(FunctionHandlerData fhd)
	{
		this.fh = null;
		this.fhd = fhd;
	}

	/**
	 * Return the function handler
	 * 
	 * @return the function handler
	 */
	public FunctionHandler getFunctionHandler()
	{
		return fh;
	}

	/**
	 * Return the function handler
	 * 
	 * @return the function handler
	 */
	public FunctionHandlerData getFunctionHandlerData()
	{
		return fhd;
	}

	/**
	 * Return the user's session
	 * 
	 * @return the user's session
	 * 
	 * @equation.external
	 */
	public EquationStandardSession getSession()
	{
		return fhd.getEquationUser().getSession();
	}

	/**
	 * Return the user's session for non-update transaction
	 * 
	 * @return the user's session for non-update transaction
	 * 
	 * @equation.external
	 */
	public EquationStandardSession getSessionForNonUpdate()
	{
		return fhd.getEquationUser().getSessionForNonUpdate();
	}

	/**
	 * Return the Equation user
	 * 
	 * @return the Equation user
	 */
	public EquationUser getEquationUser()
	{
		return fhd.getEquationUser();
	}

	/**
	 * Return the Equation unit
	 * 
	 * @return the Equation unit
	 * 
	 * @equation.external
	 */
	public EquationUnit getEquationUnit()
	{
		return fhd.getEquationUser().getEquationUnit();
	}

	/**
	 * Return the main service definition
	 * 
	 * @return the main service definition
	 */
	public Function getFunction()
	{
		return fhd.getScreenSetHandler().rtvScreenSetMain().getFunction();
	}

	/**
	 * Return the main service data
	 * 
	 * @return the main service data
	 */
	public FunctionData getFunctionData()
	{
		return fhd.getScreenSetHandler().rtvScreenSetMain().getFunctionData();
	}

	/**
	 * Return the current service definition
	 * 
	 * @return the current service definition
	 */
	public Function getCurrentFunction()
	{
		return fhd.getScreenSetHandler().rtvScrnSetCurrent().getFunction();
	}

	/**
	 * Return the current service data
	 * 
	 * @return the current service data
	 */
	public FunctionData getCurrentFunctionData()
	{
		return fhd.getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
	}

	/**
	 * Return the current service current screen
	 * 
	 * @return the current service current screen
	 */
	public int getCurrentScreen()
	{
		return fhd.getScreenSetHandler().rtvScrnSetCurrent().getScrnNo() + 1;
	}

	/**
	 * Return the current service maximum screen number
	 * 
	 * @return the current service maximum screen number
	 */
	public int getMaxScreen()
	{
		return fhd.getScreenSetHandler().rtvScrnSetCurrent().getMaxScrnNo();
	}

	/**
	 * Determine if key screen
	 * 
	 * @return true if key screen
	 */
	public boolean isKeyScreen()
	{
		return fhd.getScreenSetHandler().rtvScreenSetMain().getFunctionData().rtvFieldDbValue(FunctionData.FLD_KEYLOADED).trim()
						.equals(EqDataType.YES);
	}

	/**
	 * Return the option id
	 * 
	 * @return the option id
	 */
	public String getOptionId()
	{
		return fhd.getScreenSetHandler().rtvScreenSetMain().getOptionId();
	}

	/**
	 * Return the option id
	 * 
	 * @return the option id
	 */
	public String getFunctionId()
	{
		return fhd.getScreenSetHandler().rtvScreenSetMain().getFunctionId();
	}

	/**
	 * Return the task engine
	 * 
	 * @equation.external
	 * 
	 * @return the task engine
	 */
	public ITaskEngine getTaskEngine()
	{
		String sessionIdentifer = fhd.getFunctionInfo().getSessionId();
		ITaskEngine taskEngine = EquationFunctionContext.getContext().getTaskEngine(sessionIdentifer);
		return taskEngine;
	}

	/**
	 * Start a LRP process
	 * 
	 * @param serviceName
	 *            - the service name
	 * @param operationName
	 *            - the operation name
	 * 
	 * @return the output
	 * 
	 * @equation.external
	 * 
	 * @throws EQException
	 */
	public StartLRPProcessRsHeader startLRPProcess(String serviceName, String operationName) throws EQException
	{
		ScreenSetHandler screenSetHandler = fhd.getScreenSetHandler();
		ScreenSet screenSet = screenSetHandler.rtvScreenSetMain();
		ScreenSetCRM screenSetCRM = null;
		ScreenSetAC2 screenSetAC2 = null;

		// Update CRM
		if (screenSetHandler.isScreenSetCRMExist())
		{
			screenSetCRM = (ScreenSetCRM) screenSetHandler.rtvScreenSet(ScreenSetHandler.FUNCTION_CRM_SCREEN);
		}

		// Update EFC
		if (screenSetHandler.isScreenSetEFCExist())
		{
			screenSetAC2 = (ScreenSetAC2) screenSetHandler.rtvScreenSet(ScreenSetHandler.FUNCTION_EFC_SCREEN_1);
		}

		return FunctionRuntimeToolbox.startLRPProcess(screenSet, screenSetCRM, screenSetAC2, serviceName, operationName);
	}

	/**
	 * Add a response filter
	 * 
	 * @param resposneFilter
	 *            - the resposne filter xpath
	 */
	public void addResponseFilter(String resposneFilter)
	{
		fhd.addResponseFilter(resposneFilter);
	}

	/**
	 * Determine if returning all formats
	 * 
	 * @return if returning all formats
	 */
	public boolean isReturnAllFormats()
	{
		ServiceRqHeader head = fhd.getServiceRqHeader();
		if (head == null)
		{
			return true;
		}
		RqHeader rqHeader = head.getRqHeader();
		if (rqHeader == null)
		{
			return true;
		}
		Formatting formatting = rqHeader.getFormatting();
		if (formatting == null)
		{
			return true;
		}

		if (formatting.getReturnAllFormats() == null)
		{
			return true;
		}

		return !formatting.getReturnAllFormats().equals("N");
	}

}