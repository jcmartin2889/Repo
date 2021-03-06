package com.misys.equation.screens;

import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.function.adaptor.AbstractFieldAdaptor;
import com.misys.equation.function.adaptor.AbstractFunctionAdaptor;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.UserData;

public class ALW extends AbstractFunctionAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ALW.java 7610 2010-06-01 17:10:41Z MACDONP1 $";

	// -------------------------------------------------------------- INNER CLASS

	public class DSC_FieldAdaptor extends AbstractFieldAdaptor
	{
		@Override
		public boolean isValid(UserData userData)
		{
			getReturnMessages().addError("Warning - field");
			return false;
		}
	}

	public void validateMode(int curScreen, UserData userData)
	{
		if (userData.rtvFieldInputValue(FunctionData.FLD_KEYLOADED).equals(EqDataType.NO))
		{
			getReturnMessages().addWarning("Warning - validate user exit");
		}
	}

}