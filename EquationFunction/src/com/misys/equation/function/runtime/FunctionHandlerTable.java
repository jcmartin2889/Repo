package com.misys.equation.function.runtime;

import java.util.Hashtable;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.function.context.EquationFunctionContext;

public class FunctionHandlerTable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerTable.java 16960 2013-08-13 13:31:20Z perkinj1 $";

	private FunctionHandler functionHandler;
	private final EquationUser eqUser;
	private final Hashtable<String, FunctionHandler> childHandlers;

	/**
	 * Construct a Function Handler table with the parent Function Handler
	 */
	public FunctionHandlerTable(String sessionIdentifier, EquationUser eqUser, int sessionType)
	{
		// Store a handle to the user that was passed in.
		this.eqUser = eqUser;
		this.functionHandler = generateFunctionHandler(sessionIdentifier, sessionType, "");
		this.childHandlers = new Hashtable<String, FunctionHandler>();
	}

	/**
	 * Return the function handler
	 * 
	 * @return the function handler
	 */
	public FunctionHandler getFunctionHandler()
	{
		return functionHandler;
	}

	/**
	 * Return a function handler
	 * 
	 * @return the function handler
	 */
	public FunctionHandler getFunctionHandler(String id)
	{
		// retrieve the appropriate function handler
		FunctionHandler fh = null;
		if (id.equals(""))
		{
			fh = functionHandler;
		}
		else
		{
			fh = childHandlers.get(id);
		}

		// no function handler?
		if (fh == null)
		{
			return fh;
		}

		// return the drilled down child
		FunctionHandler drillDownChild = fh.getFhd().rtvLastDrillDownChild();
		if (drillDownChild == null)
		{
			return fh;
		}
		else
		{
			return drillDownChild;
		}
	}

	/**
	 * Set the function handler
	 * 
	 * @param id
	 *            - the function handler id
	 * @param functionHandler
	 *            - the function handler
	 */
	public void setFunctionHandler(String id, FunctionHandler functionHandler)
	{
		// get the top most parent
		FunctionHandler fh = functionHandler.getFhd().rtvTopDrillDownParent();
		if (fh == null)
		{
			fh = functionHandler;
		}

		// update it
		if (id.equals(""))
		{
			this.functionHandler = fh;
		}
		else
		{
			this.childHandlers.put(id, fh);
		}
	}

	/**
	 * Add a child Function Handler
	 * 
	 * @param id
	 *            - id of the child function handler
	 * 
	 * @return the child function handler
	 */
	public FunctionHandler addChild(String id, int childType)
	{
		FunctionInfo parentFunctionInfo = functionHandler.getFhd().getFunctionInfo();
		FunctionHandler childHandler = generateFunctionHandler(parentFunctionInfo.getSessionId(), childType, id);

		FunctionInfo childFunctionInfo = childHandler.getFhd().getFunctionInfo();
		childFunctionInfo.setWorkStationId(parentFunctionInfo.getWorkStationId());
		childFunctionInfo.setDebugMode(parentFunctionInfo.isDebugMode());
		childFunctionInfo.setRollback(parentFunctionInfo.isRollback());
		childFunctionInfo.setGenerateWarningInfo(parentFunctionInfo.isGenerateWarningInfo());
		childFunctionInfo.setWebFacingInstalled(parentFunctionInfo.isWebFacingInstalled());

		childHandler.getFhd().getContextHandler().copy(functionHandler.getFhd().getContextHandler());
		childHandlers.put(id, childHandler);
		return childHandler;
	}

	/**
	 * Remove a child Function Handler
	 * 
	 * @param id
	 *            - id of the child function handler
	 * 
	 * @return the child function handler
	 */
	public FunctionHandler removeChild(String id)
	{
		FunctionHandler child = childHandlers.remove(id);
		if (child != null)
		{
			// Perform LRP tidy
			EquationFunctionContext.getContext().unclaimTask(child);
		}
		return child;
	}

	/**
	 * Create a Function Handler
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 * @param eqUser
	 *            - the user
	 * @param eqUnit
	 *            - the unit
	 * @param sessionType
	 *            - the session type
	 * 
	 * @return the Function Handler
	 */
	private FunctionHandler generateFunctionHandler(String sessionIdentifier, int sessionType, String name)
	{
		// setup the function ifno
		FunctionInfo functionInfo = new FunctionInfo(sessionIdentifier, name);
		functionInfo.setSessionType(sessionType);
		functionInfo.setWebFacingInstalled(EquationCommonContext.isWebFacing());

		// generate the function handler
		FunctionHandler functionHandler = new FunctionHandler(eqUser, functionInfo);
		return functionHandler;
	}
}
