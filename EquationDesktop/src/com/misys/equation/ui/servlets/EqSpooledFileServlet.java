package com.misys.equation.ui.servlets;

import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.as400.access.AS400;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationLogin;
import com.misys.equation.common.access.EquationSystem;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.ui.context.EquationDesktopContext;
import com.misys.equation.ui.helpers.EqSpooledFileToPDF;

/**
 * Spooled file servlet
 */
public class EqSpooledFileServlet extends HttpServlet
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqSpooledFileServlet.java 12404 2011-12-16 13:47:08Z lima12 $";
	private static final long serialVersionUID = 2788260006560387781L;

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		makePdf(request, response, "GET");
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		makePdf(request, response, "POST");
	}

	/**
	 * Performs the action: generate a PDF from a GET or POST.
	 * 
	 * @param request
	 *            the Servlet's request object
	 * @param response
	 *            the Servlet's request object
	 * @param methodGetPost
	 *            the method that was used in the form
	 */
	public void makePdf(HttpServletRequest request, HttpServletResponse response, String methodGetPost)
	{
		AS400 eqAS400 = null;
		EquationSystem eqSystem = null;

		String spoolName = request.getParameter("spoolName");
		String jobName = request.getParameter("jobName");
		String jobUser = request.getParameter("jobUser");
		String jobNumber = request.getParameter("jobNumber");
		int spoolNumber = Integer.parseInt(request.getParameter("spoolNumber"));
		int fromPageNo = Toolbox.parseInt(request.getParameter("fromPageNo"), 0);
		int toPageNo = Toolbox.parseInt(request.getParameter("toPageNo"), 0);
		try
		{
			// Get the session Connection pool
			EquationLogin eqLogin = (EquationLogin) request.getSession().getAttribute(EquationDesktopContext.SESSION_LOGIN);
			EquationUnit eqUnit = EquationCommonContext.getContext().getEquationUnit(eqLogin.getSessionId());

			// Get the user who requested the spooled file printing
			EquationUser eqUser = EquationCommonContext.getContext().getEquationUser(eqLogin.getSessionId());
			eqSystem = eqUnit.getEquationSystem();

			// Get a connection from the pool...
			eqAS400 = eqSystem.getAS400();

			// setting the content type
			OutputStream baos = response.getOutputStream();
			EqSpooledFileToPDF fPDF = new EqSpooledFileToPDF(eqAS400, spoolName, jobName, jobUser, jobNumber, spoolNumber, eqUser);
			fPDF.createPDF(baos, fromPageNo, toPageNo);

			// setting some response headers
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");

			// setting the content type
			response.setContentType("application/pdf");

			// the content length is needed for MSIE
			// response.setContentLength(baos.size());

			// write spooled files bytes to the ServletOutputStream
			ServletOutputStream out = response.getOutputStream();
			// baos.writeTo(out);
			out.flush();
		}
		catch (Exception e)
		{
			EquationCommonContext.getContext().getLOG().error(e);
			String errMsg = Toolbox.getExceptionMessage(e);
			try
			{
				response.sendRedirect("eqSpooledFile.jsp?ErrorMsg=" + URLEncoder.encode(errMsg, "UTF-8") + "&spoolName="
								+ URLEncoder.encode(spoolName, "UTF-8") + "&jobName=" + URLEncoder.encode(jobName, "UTF-8")
								+ "&jobUser=" + URLEncoder.encode(jobUser, "UTF-8") + "&jobNumber="
								+ URLEncoder.encode(jobNumber, "UTF-8") + "&spoolNumber="
								+ URLEncoder.encode(String.valueOf(spoolNumber), "UTF-8"));
			}
			catch (Exception e2)
			{
				EquationCommonContext.getContext().getLOG().error(e);
			}
		}
		finally
		{
			// Return the connection to the pool...
			if (eqAS400 != null)
			{
				eqSystem.returnAS400(eqAS400);
			}
		}
	}

	/**
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy()
	{
	}
}
