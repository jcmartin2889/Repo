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
import org.apache.struts.util.MessageResources;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.internal.eapi.core.EQActionErrorException;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.ui.context.EquationDesktopContext;
import com.misys.equation.ui.forms.ChgPwdForm;
import com.misys.equation.ui.forms.LoginForm;
import com.misys.equation.ui.tools.AuthenticateLogin;
import com.misys.equation.ui.tools.EqDesktopToolBox;

public class ChgPwdAction extends Action
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ChgPwdAction.java 16593 2013-06-24 15:32:19Z perkinj1 $";
	private static final EquationLogger LOG = new EquationLogger(ChgPwdAction.class);
	public ChgPwdAction()
	{
	}
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ActionErrors ae = null;
		EquationCommonContext eqContext = EquationCommonContext.getContext();
		ChgPwdForm cpf = (ChgPwdForm) form;
		LoginForm lf = null;

		AuthenticateLogin auth = null;
		boolean newSystem = false;
		boolean success = false;
		String sessionIdentifier = null;
		boolean isLoginPage = true;
		try
		{
			eqContext.changePassword(cpf.getSystemName(), cpf.getUserId(), cpf.getOldPassword(), cpf.getNewPassword());
			lf = populateLoginForm(cpf, request);
			auth = new AuthenticateLogin(lf, request, getServlet(), false);
			newSystem = auth.isNewSystem();
			sessionIdentifier = auth.getSessionId();

			if (!auth.isTransactionValid())
			{
				ae = new ActionErrors();
				ae.add("actionError", new ActionMessage("error.invalid.transaction"));
				super.saveErrors(request, ae);
			}
			else
			{
				success = true;
			}
		}
		catch (EQActionErrorException e)
		{
			MessageResources messages = MessageResources
							.getMessageResources("com/misys/equation/ui/resources/ApplicationResources");
			ae = new ActionErrors();
			ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.parameterised", e.getActionError()));
			super.saveErrors(request, ae);
			if (e.getActionError().equals(messages.getMessage("PASSWORD.INVALID.LEN.MSG"))
							|| e.getActionError().equals(messages.getMessage("PASSWORD.LEN.TOO.SHORT"))
							|| e.getActionError().indexOf(messages.getMessage("PASSWORD.INCORRECT.MSG"), 0) != -1
							|| e.getActionError().trim().equals(messages.getMessage("PASSWORD.ALREADY.USED"))
							|| e.getActionError().trim().equals(messages.getMessage("PASSWORD.LEN.TOO.LONG")))
			{
				isLoginPage = false;
			}

		}
		catch (EQException e)
		{
			LOG.error(e);
			ae = new ActionErrors();
			ae.add(Toolbox.getExceptionMessage(e), new ActionMessage("error.parameterised", Toolbox.getExceptionMessage(e)));
			super.saveErrors(request, ae);
		}
		finally
		{
			// user has signed in, but something else has happened, then logs-out the user
			if (sessionIdentifier != null && !success)
			{
				EquationDesktopContext.getContext().logOffDesktop(sessionIdentifier, "", true);
			}

			// user unable to log-in and it created a new system, then ditch the system
			if (newSystem && !success)
			{
				EquationCommonContext.getContext().removeEquationSystem(lf.getSystemName());
			}

		}
		request.setAttribute(Globals.ERROR_KEY, ae);
		request.setAttribute(EquationDesktopContext.PARAM_WORKSTATION, request
						.getParameter(EquationDesktopContext.PARAM_WORKSTATION));
		request.setAttribute(EqDesktopToolBox.STYLE_PARAMETER, request.getParameter(EqDesktopToolBox.STYLE_PARAMETER));

		// Return mapping if valid or invalid
		if (ae == null || ae.size() == 0)
		{
			String page = "";
			try
			{
				page = auth.getPageName();

			}
			catch (Exception e)
			{
				LOG.error(e);
			}
			if (cpf.getIsMainPage() != null)
			{
				return mapping.findForward("welcome");
			}
			else
			{
				return mapping.findForward(page);
			}

		}
		else
		{
			request.setAttribute("unit", request.getParameter("unit"));
			request.setAttribute("system", request.getParameter("systemName"));
			request.setAttribute("user", request.getParameter("userId"));
			if (isLoginPage)
			{
				request.setAttribute("isLogin", "true");
				request.setAttribute("tempError", ae);
				return mapping.findForward("login");
			}
			String sessionId = EquationDesktopContext.getSessionId(request);
			EquationDesktopContext.getContext().decrementEquationUserExtension(request.getParameter("userId"), sessionId);
			return mapping.findForward("invalid");
		}
	}
	private LoginForm populateLoginForm(ChgPwdForm form, HttpServletRequest request)
	{
		LoginForm lf = new LoginForm();
		lf.setUserId(request.getParameter("userId"));
		lf.setUnitName(request.getParameter("unit"));
		lf.setSystemName(request.getParameter("systemName"));
		lf.setPassWord(form.getNewPassword());
		lf.setOptParm(request.getParameter("optParm"));

		// option id
		if (request.getParameter("optId") != null)
		{
			lf.setOptId(request.getParameter("optId"));
		}
		else
		{
			lf.setOptId("");
		}

		// classic login
		if (request.getParameter("isClassic").equals(""))
		{
			lf.setIsClassic("0");
		}
		else
		{
			lf.setIsClassic(request.getParameter("isClassic"));
		}

		// workstation id
		if (request.getParameter("workstationId") != null)
		{
			lf.setWorkstationId(request.getParameter("workstationId"));
		}
		else
		{
			lf.setWorkstationId("");
		}

		return lf;
	}
}
