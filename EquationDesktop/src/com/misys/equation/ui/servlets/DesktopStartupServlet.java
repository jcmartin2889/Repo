package com.misys.equation.ui.servlets;

import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationGroupConfiguration;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.SessionLimitConfiguration;
import com.misys.equation.common.utilities.EquationAPICacheUpdater;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.context.EquationFunctionContext;
import com.misys.equation.function.tools.BankFusionSynchronizer;

public class DesktopStartupServlet extends HttpServlet
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DesktopStartupServlet.java 17823 2014-01-30 09:23:00Z perkinj1 $";

	/** UID */
	private static final long serialVersionUID = 1L;

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(DesktopStartupServlet.class);

	/** Performs synchronisation of BankFusion users with the Equation users */
	private transient BankFusionSynchronizer bankFusionSynchronizer;

	/** Delay in seconds for the API cache updater */
	private final int apiDefaultCacheDelay = 60;

	/** Project context name */
	private static String projectContextName = "";

	/**
	 * Called by the servlet container to indicate to a servlet that the servlet is being placed into service.
	 * 
	 * @param javax
	 *            .servlet.ServletConfig config
	 * @throws javax.servlet.ServletException
	 *             ServletException
	 */
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);

		String[] unitIds = { "" };
		String[] systemIds = { "" };
		String[] userIds = { "" };
		String[] passwords = { "" };
		String unitStatusInterval = "";

		// Get the context name
		projectContextName = config.getServletContext().getContextPath();

		// Output plugin version
		String plugin = "BFEQ version " + EquationCommonContext.PLUGIN_VERSION;
		LOG.info(plugin);

		// API/PV Cache job updater
		String delayStr = EquationCommonContext.getConfigProperty("eq.apicache");
		if (!delayStr.equals("N"))
		{
			long delay = Toolbox.parseInt(delayStr, apiDefaultCacheDelay);
			delay = delay * 1000;
			EquationAPICacheUpdater equationAPICacheUpdater = new EquationAPICacheUpdater(0, delay);
			getServletContext().setAttribute(DesktopContextListener.API_CACHE_CONTEXT_ATTRIBUTE, equationAPICacheUpdater);
		}

		// Call EquationGroupConfiguration for initialisation
		EquationGroupConfiguration.getContext().loadGroupsAndMembersFromProperties();

		// Initialise the Session Limit for every user
		SessionLimitConfiguration.getContext().initialiseSessionLimit();

		// Initialise the context
		EquationCommonContext.getContext();

		// Branch server setup - then stop processing
		// In a branch server setup, it should not make any connection to the iSeries
		if (EquationFunctionContext.isBranchServerSetup())
		{
			LOG.info("Branch server setup");
			return;
		}

		// PROCESSING BELOW SHOULD ONLY BE APPLICABLE ON A NON-BRANCH SERVER SETUP

		// Check if Global Processing is enabled
		if (checkGlobalProcessingEnabled())
		{
			EquationCommonContext.getContext().loadEquationConfigurationPropertiesBean();
			EquationCommonContext.getContext().createGlobalSessionHandler();
		}

		LOG.info("Starting Desktop");
		unitIds = EquationCommonContext.getConfigProperty("eq.units").split(",");
		systemIds = EquationCommonContext.getConfigProperty("eq.systems").split(",");
		userIds = EquationCommonContext.getConfigProperty("eq.admin.users").split(",");
		passwords = EquationCommonContext.getConfigProperty("eq.admin.passwords").split(",");
		unitStatusInterval = EquationCommonContext.getConfigProperty("eq.unitStatus.unitStatusPollInterval").trim();

		Map<String, String[]> aliasDetails = EquationCommonContext.getAdminAlias();
		if (aliasDetails != null)
		{
			userIds = aliasDetails.get("Users");
			passwords = aliasDetails.get("Passwords");
		}

		for (int i = 0; i < unitIds.length && !unitIds[i].equals(""); i++)
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info("Initialising unit: [" + unitIds[i] + "]");
			}
			if (systemIds.length <= i || systemIds[i].equals(""))
			{
				LOG.error("Cannot initialise unit " + unitIds[i] + " due to unspecified system id");
				break;
			}
			if (userIds.length <= i || userIds[i].equals(""))
			{
				LOG.error("Cannot initialise unit " + unitIds[i] + " due to unspecified user id");
				break;
			}
			if (passwords.length <= i || passwords[i].equals(""))
			{
				LOG.error("Cannot initialise unit " + unitIds[i] + " due to unspecified password");
				break;
			}
			try
			{
				EquationCommonContext.getContext().initialiseUnitEnvironment(systemIds[i].trim(), unitIds[i].trim(),
								userIds[i].trim(), passwords[i].trim(), EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN);
			}
			catch (Exception e)
			{
				LOG.error("Unable to initialise unit " + unitIds[i], e);
			}

			// Pre-load cache on the first unit defined
			try
			{
				if (i == 0)
				{
					LOG.info("Pre loading cache for " + systemIds[i].trim() + "-" + unitIds[i].trim());
					EquationUnit equationUnit = EquationCommonContext.getContext().getEquationUnit(systemIds[i].trim(),
									unitIds[i].trim());
					if (equationUnit != null)
					{
						equationUnit.getRecords().getHBRecords(EquationCommonContext.getConfigProperty("eq.cache.hb"));
					}
				}
			}
			catch (Exception e)
			{
				LOG.error("Caching issue", e);
			}

			// If we have the system status monitor available then check the availability of the unit first...
			if (checkGlobalProcessingEnabled() || !unitStatusInterval.equals(""))
			{
				EquationCommonContext.getContext().runSystemStatusManagerMonitor();
			}
		}

		// Other startup processing, namely cluster service
		EquationCommonContext.getContext().startup();

		// Note that we want the synchronisation process to start 'late' and end 'early'.
		// Therefore the process is started here, after the servlet has finished started, but will be
		// ended by the ContextListener, which is called BEFORE the servlet is shutdown.

		bankFusionSynchronizer = new BankFusionSynchronizer(0);
		getServletContext().setAttribute(DesktopContextListener.BANKFUSION_SYNCH_CONTEXT_ATTRIBUTE, bankFusionSynchronizer);
	}

	/**
	 * This method will check if Global Processing is enabled.
	 * <p>
	 * If the "GlobalProcessing" context parameter is true, then Global Processing is enabled.
	 * 
	 * @return true if Global Processing is enabled.
	 */
	private boolean checkGlobalProcessingEnabled()
	{
		String globalProcessingInitParameter = getServletContext().getInitParameter("GlobalProcessing");
		return Boolean.toString(true).equals(globalProcessingInitParameter);
	}

	/**
	 * Return the project context name
	 * 
	 * @return the project context name
	 */
	public static String getProjectContextName()
	{
		return projectContextName;
	}
}