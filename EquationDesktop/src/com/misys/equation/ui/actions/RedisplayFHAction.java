package com.misys.equation.ui.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationLogin;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionHandlerTable;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;
import com.misys.equation.ui.context.EquationDesktopContext;

public class RedisplayFHAction extends Action
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RedisplayFHAction.java 8910 2010-08-26 15:25:20Z MACDONP1 $";
	public RedisplayFHAction()
	{
	}

	/**
	 * Perform processing
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		// Initialise list of errors for the strut
		ActionErrors ae = new ActionErrors();

		// Retrieve the function handler
		FunctionHandlerTable functionHandlerTable = null;
		FunctionHandler functionHandler = null;

		// Determine name of the function handler
		String name = request.getParameter("name"); // name of function handler
		String removechild = request.getParameter("removechild"); // flag to determine if child needs to be removed

		try
		{
			// get the function handler
			functionHandlerTable = (FunctionHandlerTable) request.getSession().getAttribute(FunctionRuntimeToolbox.NAME);
			functionHandler = functionHandlerTable.getFunctionHandler(name);

			if (removechild.equals("Y"))
			{
				functionHandler.removeDrillDownChildProcessing();
			}

			// save the function handler back to the request
			request.getSession().setAttribute(FunctionRuntimeToolbox.NAME, functionHandlerTable);
		}
		catch (Exception e)
		{
			EquationCommonContext.getContext().getLOG().error(e);
			ae = new ActionErrors();
			ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.parameterised", Toolbox.getExceptionMessage(e)));
			super.saveErrors(request, ae);
		}
		finally
		{
			request.getSession().setAttribute(FunctionRuntimeToolbox.UNIQUE_NAME, name);
			request.getSession().setAttribute(FunctionRuntimeToolbox.REFRESH_MAIN_WINDOW, "");
		}

		// Save the errors back to the HTTP request
		request.setAttribute(Globals.ERROR_KEY, ae);

		// Save context in case it has changed
		FunctionHandler childHandler = functionHandlerTable.getFunctionHandler(name);
		if (childHandler != null && childHandler != functionHandler)
		{
			EquationLogin existinglogin = (EquationLogin) request.getSession().getAttribute(EquationDesktopContext.SESSION_LOGIN);
			existinglogin.setContextData1(childHandler.getFhd().getContextHandler().getDswsid1Str());
			existinglogin.setContextData2(childHandler.getFhd().getContextHandler().getDswsid2Str());
		}

		// Reload function
		return mapping.findForward("function");
	}

}
