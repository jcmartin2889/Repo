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
import com.misys.equation.common.internal.eapi.core.EQActionErrorException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionHandlerData;
import com.misys.equation.function.runtime.FunctionHandlerTable;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;
import com.misys.equation.ui.context.EquationDesktopContext;
import com.misys.equation.ui.forms.GetPwdForm;

public class GetPwdAction extends Action
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GetPwdAction.java 17139 2013-08-29 16:00:56Z whittap1 $";

	private static final EquationLogger LOG = new EquationLogger(GetPwdAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
					HttpServletResponse httpservletresponse) throws Exception
	{

		EquationCommonContext eqContext = EquationCommonContext.getContext();
		GetPwdForm gpf = (GetPwdForm) form;

		String password = gpf.getPassword();
		password = password.toUpperCase();

		String name = gpf.getMainFhName();

		// Check for cancel
		if (this.isCancelled(request))
		{
			// If WebFacing option is not a child, return to welcome screen
			if (name.equals("EXCLUDED"))
			{
				return mapping.findForward("welcome");
			}
			else
			{
				// If option would be a child of a service, reset function handler to parent and return to function.jsp
				resetFH(request, name);
				return mapping.findForward("valid");
			}
		}

		// Authenticate the password that was entered
		try
		{
			eqContext.authenticate(gpf.getSystemName(), gpf.getUnit(), gpf.getUser(), password,
							EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN);

			// Get the current session
			// get the function handler
			FunctionHandlerTable functionHandlerTable = (FunctionHandlerTable) request.getSession().getAttribute(
							FunctionRuntimeToolbox.NAME);
			FunctionHandler functionHandler = functionHandlerTable.getFunctionHandler("");
			FunctionHandlerData fhd = functionHandler.getFhd();
			String sessionId = fhd.getFunctionInfo().getSessionId();

			// If no errors then set the stored password to the password entered
			eqContext.getEquationLogin(sessionId).setPassword(password);
			eqContext.getEquationLogin(sessionId).setPasswordType(EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN);
		}
		catch (EQActionErrorException e)
		{
			LOG.error(e);

			// pass the error message in a request attribute, it will be processed in the jsp
			request.setAttribute("errmsg", "['" + e.getParameter() + "']");

			// re-display the page
			return mapping.findForward("invalid");

		}

		// If successful then forward to function.jsp
		if (gpf.getOption() != null && !gpf.getOption().equals("null"))
		{
			// Pass back the original parameters for the mode, option etc
			return new ActionForward(mapping.findForward("valid").getPath() + "?mode=" + gpf.getMode() + "&optionId="
							+ gpf.getOption() + "&unit=" + gpf.getUnit() + "&context=" + gpf.getContext(), false);
		}
		else
		{
			// If called from a service, parameters are stored in the fhd
			return mapping.findForward("valid");
		}

	}

	/**
	 * If the webfacing function was a child of a service and the user clicks cancel reset the FunctionHanlder to the parent (copied
	 * from RedisplayFHAction)
	 * 
	 * @param request
	 * @param name
	 */
	void resetFH(HttpServletRequest request, String name)
	{
		// Initialise list of errors for the strut
		ActionErrors ae = new ActionErrors();

		// Retrieve the function handler
		FunctionHandlerTable functionHandlerTable = null;
		FunctionHandler functionHandler = null;

		String removechild = "Y"; // flag to determine if child needs to be removed

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

	}
}
