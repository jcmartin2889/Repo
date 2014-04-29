package com.misys.equation.common.globalprocessing;

import java.util.Observable;
import java.util.Observer;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.utilities.EquationLogger;

public class UnitStatusObserver implements Observer
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UnitStatusObserver.java 17823 2014-01-30 09:23:00Z perkinj1 $";
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(UnitStatusObserver.class);

	boolean available = true;
	public void update(Observable o, Object arg)
	{
		UnitStatus unitStatus = (UnitStatus) o;
		LOG.info("Unit Status Observer is called.");

		if (available != unitStatus.isAvailable())
		{
			LOG.info("Unit Status changed.");
			available = unitStatus.isAvailable();
			if (available == false)
			{
				EquationCommonContext.getContext().removeEquationUnit(unitStatus.getSystemName(), unitStatus.getUnitName());
			}
		}
		LOG.info("Is available:" + ((UnitStatus) o).isAvailable());
	}

}
