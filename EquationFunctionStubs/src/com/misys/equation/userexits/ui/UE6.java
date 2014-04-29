package com.misys.equation.userexits.ui;

import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.function.adaptor.AbstractAttributesAdaptor;
import com.misys.equation.function.adaptor.AbstractLayoutAdaptor;
import com.misys.equation.function.runtime.UserData;

public class UE6 extends AbstractLayoutAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UE6.java 4741 2009-09-16 16:33:23Z esther.williams $";
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(UE6.class);
	// -------------------------------------------------------------- INNER CLASS

	@Override
	public int prevScreen(int curScreen, UserData userData)
	{
		if (curScreen == 3 && userData.rtvFieldInputValue("PREVSCRN").length() > 0)
		{
			String nextScreen = userData.rtvFieldInputValue("PREVSCRN");
			return Integer.parseInt(nextScreen);
		}
		return 0;
	}

	@Override
	public int nextScreen(int curScreen, UserData userData)
	{
		LOG.error("nextScreen curScreen=[" + curScreen + "]");
		if (curScreen == 2 && userData.rtvFieldInputValue("NEXTSCRN").length() > 0)
		{
			String nextScreen = userData.rtvFieldInputValue("NEXTSCRN");
			return Integer.parseInt(nextScreen);
		}
		return 0;
	}

	/**
	 * Inner class to implement user exit handling for the TEST1_AttributesAdaptor field display attributes
	 */
	public class TEST1_AttributesAdaptor extends AbstractAttributesAdaptor
	{

		@Override
		public boolean isVisible(UserData data)
		{
			return !data.rtvFieldInputValue("VIS_CTL").equals("N");
		}

		@Override
		public boolean isProtected(UserData data)
		{
			return data.rtvFieldInputValue("PROT_CTL").equals("Y");
		}
	}

}
