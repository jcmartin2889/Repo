package com.misys.equation.ui.actions;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.SessionLimitConfiguration;
import com.misys.equation.common.internal.eapi.core.EQActionErrorException;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQPasswordExpiredException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.ui.beans.EquationUserExtension;
import com.misys.equation.ui.context.EquationDesktopContext;
import com.misys.equation.ui.forms.LoginForm;
import com.misys.equation.ui.tools.AuthenticateLogin;
import com.misys.equation.ui.tools.EqDesktopToolBox;

public class LoginAction extends Action
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LoginAction.java 16593 2013-06-24 15:32:19Z perkinj1 $";
	private static final EquationLogger LOG = new EquationLogger(LoginAction.class);
	public LoginAction()
	{
		LOG.debug("LoginAction created");
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ActionErrors ae = null;
		LoginForm lf = (LoginForm) form;
		AuthenticateLogin auth = null;
		boolean newSystem = false;
		boolean success = false;
		boolean isPasswordExpired = false;
		String sessionIdentifier = null;

		try
		{
			// Checks if session limit reached, do not proceed to authentication
			// .. but do not check if this is a direct transaction
			if (lf.getOptId().equals("")) // this logic must be kept in-sync with the logic in AuthenticateLogin
			{
				EquationUserExtension.validate(SessionLimitConfiguration.getContext().getSessionLimit(lf.getUserId()));
				EquationUserExtension userExt = EquationDesktopContext.getContext().getEquationUserExtension().get(lf.getUserId());
				if (userExt != null && userExt.isSessionLimitReached())
				{
					throw new EQActionErrorException("error.parameterised", "error.user.sessionlimit.reached");
				}
			}

			// Authenticate login
			auth = new AuthenticateLogin(lf, request, getServlet(), true);
			newSystem = auth.isNewSystem();
			sessionIdentifier = auth.getSessionId();

			if (!auth.isTransactionValid())
			{
				ae = new ActionErrors();
				ae.add("actionError", new ActionMessage("error.invalid.transaction"));
			}
			else
			{
				success = true;
			}
			isPasswordExpired = auth.isAutoChangePassword();

		}
		catch (EQPasswordExpiredException e)
		{
			LOG.error(e);
			ae = new ActionErrors();
			ae.add(Toolbox.getExceptionMessage(e), new ActionMessage(e.getActionError()));
			isPasswordExpired = true;

		}
		catch (EQActionErrorException e)
		{
			LOG.error(e);
			ae = new ActionErrors();
			ae.add(Toolbox.getExceptionMessage(e), new ActionMessage(e.getActionError(), e.getParameter()));
		}
		catch (EQException e)
		{
			LOG.error(e);
			ae = new ActionErrors();
			ae.add(Toolbox.getExceptionMessage(e), new ActionMessage("error.parameterised", Toolbox.getExceptionMessage(e)));
		}
		finally
		{
			if (ae != null)
			{
				// Log detailed error information so the operator can determine
				// the underlying problem:
				LOG.error("Login failure for system [" + lf.getSystemName() + "], unit [" + lf.getUnitName() + "], user ["
								+ lf.getUserId() + "] error details are" + getMessageText(request, ae));
				// Generic login message processing:
				if ("true".equalsIgnoreCase(EquationCommonContext.getConfigProperty("eq.generic.login.message")))
				{
					ae = replaceGenericLoginMessages(ae);
				}
				super.saveErrors(request, ae);
			}

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

		// store details into the attribute
		request.setAttribute(EquationDesktopContext.PARAM_WORKSTATION, request
						.getParameter(EquationDesktopContext.PARAM_WORKSTATION));
		request.setAttribute(EqDesktopToolBox.STYLE_PARAMETER, request.getParameter(EqDesktopToolBox.STYLE_PARAMETER));
		request.setAttribute("unit", lf.getUnitName());
		request.setAttribute("system", lf.getSystemName());
		request.setAttribute("user", lf.getUserId());
		request.setAttribute("isClassic", lf.getIsClassic());
		request.setAttribute("optId", request.getParameter("optId"));
		request.setAttribute("optParm", request.getParameter("optParm"));
		if (isPasswordExpired)
		{
			return mapping.findForward("password_expired");
		}
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
			return mapping.findForward(page);
		}
		else
		{
			String sessionId = EquationDesktopContext.getSessionId(request);
			EquationDesktopContext.getContext().decrementEquationUserExtension(lf.getUserId(), sessionId);
			return mapping.findForward("invalid");
		}
	}

	/**
	 * Processes all the messages in the ActionErrors object and replaces them with a generic login message. This is a security
	 * enhancement to prevent a specific error message text providing information to an attacker. For example, an invalid password
	 * error indicates that the supplied user id was valid.
	 * 
	 * Note that configuration or environmental problems (e.g. browser session reuse) must not be replaced with a generic error
	 * message as the user has provided correct login information.
	 * 
	 * @param actionErrors
	 *            The original ActionErrors instance
	 * 
	 * @return a new ActionErrors instance containing the new set of message(s)
	 */
	private ActionErrors replaceGenericLoginMessages(ActionErrors actionErrors)
	{
		ActionErrors result = new ActionErrors();
		boolean addedGeneric = false;

		Iterator<Object> it = actionErrors.get();
		while (it.hasNext())
		{
			Object o = it.next();
			if (o instanceof ActionMessage)
			{
				ActionMessage am = (ActionMessage) o;
				// If the message is not related to the login information entered
				// by the user, then copy across unchanged:
				if ("error.duplicate.session".equals(am.getKey()))
				{
					result.add(am.getKey(), am);
				}
				else
				{
					// Errors regarding the entered login details must be replaced
					// with a single generic message:
					if (!addedGeneric)
					{
						result.add("actionError", new ActionMessage("error.login.failed"));
						addedGeneric = true;
					}
				}
			}
		}
		return result;
	}

	/**
	 * Generate a String with all the ActionMessage details in the ActionErrors object
	 * 
	 * @param request
	 *            the HttpServletRequest
	 * @param actionErrors
	 *            an ActionErrors instance
	 * @return A String containing the message details
	 */
	private String getMessageText(HttpServletRequest request, ActionErrors actionErrors)
	{
		// Note that the "equation" value is the message resource key.
		// See the message-resources definition in struts-eqinv.xml
		MessageResources mc = getResources(request, "equation");
		Iterator<Object> it = actionErrors.get();
		StringBuilder errorBuilder = new StringBuilder();
		while (it.hasNext())
		{
			Object o = it.next();
			if (o instanceof ActionMessage)
			{
				ActionMessage am = (ActionMessage) o;
				if (errorBuilder.length() > 0)
				{
					errorBuilder.append(", ");
				}
				errorBuilder.append(" id [");
				errorBuilder.append(am.getKey());
				errorBuilder.append("] text [");
				errorBuilder.append(mc.getMessage(am.getKey(), am.getValues()));
				errorBuilder.append("]");
			}
		}
		return errorBuilder.toString();
	}
}
