package com.misys.equation.ui.helpers;

import java.util.Observable;
import java.util.Observer;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.globalprocessing.UnitStatus;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.ui.context.EquationDesktopContext;

/**
 * This is a unit status observer. When the unit status changes this class will be called.
 */
public class DesktopUnitStatusObserver implements Observer
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DesktopUnitStatusObserver.java 17823 2014-01-30 09:23:00Z perkinj1 $";
	private static String suspendUnitOnSuspendRequest;
	private static String breakMessageOnSuspendRequest;
	private static String intervalBetweenBreakMessageAndSuspendStr;
	private static long intervalBetweenBreakMessageAndSuspend = 0;
	private boolean available = true;
	private String suspendRequest = "";

	static
	{

		suspendUnitOnSuspendRequest = EquationCommonContext.getConfigProperty("eq.unitStatus.suspendUnitOnSuspendRequest").trim();
		breakMessageOnSuspendRequest = EquationCommonContext.getConfigProperty("eq.unitStatus.breakMessageOnSuspendRequest").trim();
		intervalBetweenBreakMessageAndSuspendStr = EquationCommonContext.getConfigProperty(
						"eq.unitStatus.intervalBetweenBreakMessageAndSuspend").trim();

		if (intervalBetweenBreakMessageAndSuspendStr != null && intervalBetweenBreakMessageAndSuspendStr.length() > 0)
		{
			intervalBetweenBreakMessageAndSuspend = Toolbox.parseLong(intervalBetweenBreakMessageAndSuspendStr, 0);
		}
	}
	public void update(Observable o, Object arg)
	{
		UnitStatus unitStatus = (UnitStatus) o;
		boolean resumeCanProceed = true;

		if (available != unitStatus.isAvailable())
		{
			System.out.println("Unit Status changed.");
			available = unitStatus.isAvailable();
			if (available == false)
			{
				resumeCanProceed = false;
				try
				{
					EquationDesktopContext.getContext().destroyUnit(unitStatus.getSystemName(), unitStatus.getUnitName());
				}
				catch (EQException e)
				{
					throw new RuntimeException(e);
				}
			}
		}
		if (!suspendRequest.equals(unitStatus.getSuspendRequest().trim()))
		{
			suspendRequest = unitStatus.getSuspendRequest().trim();
			if (!suspendRequest.equals(""))
			{
				resumeCanProceed = false;
				if (!breakMessageOnSuspendRequest.trim().equals(""))
				{
					EquationCommonContext.getContext().addSessionMessageLocal(null, "",
									EquationCommonContext.MESSAGE_SEVERITY_INFORMATION, breakMessageOnSuspendRequest);
				}
				if (intervalBetweenBreakMessageAndSuspend > 0)
				{
					try
					{
						Thread.sleep(intervalBetweenBreakMessageAndSuspend * 1000);
					}
					catch (InterruptedException e)
					{
						throw new RuntimeException(e);
					}
				}
				if (suspendUnitOnSuspendRequest.equals("true"))
				{
					try
					{
						EquationDesktopContext.getContext().destroyUnit(unitStatus.getSystemName(), unitStatus.getUnitName());
					}
					catch (EQException e)
					{
						throw new RuntimeException(e);
					}
				}

			}
		}
		if (resumeCanProceed)
		{
			// initialise the unit
			try
			{
				EquationDesktopContext.getContext().initialiseUnit(unitStatus.getSystemName(), unitStatus.getUnitName());
			}
			catch (EQException e)
			{
				throw new RuntimeException(e);
			}
		}
	}
}
