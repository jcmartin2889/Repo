package com.misys.equation.common.globalprocessing;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.utilities.EquationLogger;

/**
 * This is the Thread task that monitors the systems.
 */
public class SystemStatusManagerThread implements Runnable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SystemStatusManagerThread.java 11855 2011-09-26 20:29:50Z esther.williams $";

	/** Logger for this class */
	private static final EquationLogger LOG = new EquationLogger(SystemStatusManagerThread.class);

	private final int sleepTime;
	private final SystemStatusManager systemStatusManager;

	public SystemStatusManagerThread()
	{
		systemStatusManager = SystemStatusManager.getInstance();
		sleepTime = Integer.parseInt(EquationCommonContext.getConfigProperty("eq.sleepTime").trim());
	}
	/**
	 * This constructor is for 3rd party applications that do not maintain equationConfiguration.properties
	 * 
	 */
	public SystemStatusManagerThread(String[] unitIds, String[] systemIds, String[] userNames, String[] passwords,
					int sleepTimeParam)
	{
		systemStatusManager = SystemStatusManager.getInstance(unitIds, systemIds, userNames, passwords);
		sleepTime = sleepTimeParam;
	}

	public void runFirst()
	{
		systemStatusManager.refreshAllSystemStatus();
	}

	public void run()
	{
		monitorSystems();
	}

	/**
	 * This method will execute the monitoring mechanism.
	 */
	private void monitorSystems()
	{
		while (true)
		{
			try
			{
				systemStatusManager.refreshAllSystemStatus();
				Thread.sleep(sleepTime);
			}
			catch (InterruptedException e)
			{
				LOG.error(e);
			}
		}
	}
}
