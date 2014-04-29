package com.misys.equation.common.sample;

import java.util.Observable;
import java.util.Observer;
import java.util.Map.Entry;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.globalprocessing.UnitStatus;

/**
 * This is a unit status observer. When the unit status changes this class will be called.
 */
public class UnitStatusObserverSample implements Observer
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UnitStatusObserverSample.java 17823 2014-01-30 09:23:00Z perkinj1 $";
	boolean available = true;
	public void update(Observable o, Object arg)
	{
		UnitStatus unitStatus = (UnitStatus) o;
		System.out.println("Unit Status Observer is called.");

		if (available != unitStatus.isAvailable())
		{
			System.out.println("Unit Status changed.");
			available = unitStatus.isAvailable();
			if (available == false)
			{

				removeAllUsersOfUnit(unitStatus.getSystemName(), unitStatus.getUnitName());

				EquationCommonContext.getContext().removeEquationUnit(unitStatus.getSystemName(), unitStatus.getUnitName());
			}
		}
		System.out.println("Is available:" + ((UnitStatus) o).isAvailable());
	}
	/**
	 * Log-off all users on the given system and unit id
	 * 
	 * @param systemId
	 *            - the system where the unit is located
	 * @param unitId
	 *            - the unit id
	 */
	public void removeAllUsersOfUnit(String systemId, String unitId)
	{
		// retrieve the list of users log into the unit
		EquationCommonContext singletonCommonContext = EquationCommonContext.getContext();

		for (Entry<String, EquationUser> equationUserEntry : singletonCommonContext.getEqUsers().entrySet())
		{
			EquationUser equationUser = equationUserEntry.getValue();
			if (equationUser != null)
			{
				if (equationUser.getSession() != null)
				{
					String userSystemId = equationUser.getSession().getSystemId();

					String userUnitId = equationUser.getSession().getUnitId();
					if (userUnitId.equalsIgnoreCase(unitId) && userSystemId.equalsIgnoreCase(systemId))
					{
						singletonCommonContext.logOffSession(equationUser.getSession().getSessionIdentifier());
					}
				}
			}
		}

	}

}
