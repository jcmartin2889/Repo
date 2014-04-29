package com.misys.equation.screens;

import com.misys.equation.function.adaptor.AbstractFunctionAdaptor;
import com.misys.equation.function.runtime.UserModifyData;
import com.misys.equation.function.runtime.UserModifyRepeatingDataManager;

public class ZY1 extends AbstractFunctionAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ZY1.java 6507 2010-03-05 13:00:11Z lima12 $";

	@Override
	public String postLoad(UserModifyData userData)
	{
		UserModifyRepeatingDataManager dataManager = userData.getRepeatingDataManager("RS1");

		dataManager.moveFirst();
		if (dataManager.next())
		{
			dataManager.chgFieldDbValue("A_HZ_DTE", "1000101");
		}
		if (dataManager.next())
		{
			dataManager.chgFieldDbValue("A_HZ_DTE", "1000104");
		}
		if (dataManager.next())
		{
			dataManager.chgFieldDbValue("A_HZ_DTE", "1000102");
		}
		if (dataManager.next())
		{
		}
		if (dataManager.next())
		{
			dataManager.chgFieldDbValue("A_HZ_DTE", "1000103");
		}

		return null;
	}

}
