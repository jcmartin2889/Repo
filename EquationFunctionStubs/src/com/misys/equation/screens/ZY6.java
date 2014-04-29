package com.misys.equation.screens;

import com.misys.equation.function.adaptor.AbstractFunctionAdaptor;
import com.misys.equation.function.runtime.UserModifyData;
import com.misys.equation.function.runtime.UserModifyRepeatingDataManager;

public class ZY6 extends AbstractFunctionAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ZY6.java 5740 2010-01-05 15:13:12Z lima12 $";
	// -------------------------------------------------------------- METHODS

	@Override
	public String postLoad(UserModifyData userModifyData)
	{
		UserModifyRepeatingDataManager mgr = userModifyData.getRepeatingDataManager("RS1");

		mgr.setRow(1);
		mgr.chgFieldInputValue("A_C8CUR", "smallcase");

		// add a row
		int added = mgr.addRow();
		mgr.setRow(added);
		mgr.chgFieldInputValue("A_C8CCY", "XXX");
		mgr.chgFieldInputValue("A_C8CUR", "smallcase");

		return "";
	}
}
