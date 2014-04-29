package com.misys.equation.screens;

import com.misys.equation.function.adaptor.AbstractFieldAdaptor;
import com.misys.equation.function.adaptor.AbstractFunctionAdaptor;
import com.misys.equation.function.runtime.UserData;

public class XHCX extends AbstractFunctionAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: XHCX.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	public class DSC_FieldAdaptor extends AbstractFieldAdaptor
	{
		@Override
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("DSC").equals("INVALID"))
			{
				getReturnMessages().addError("This is INVALID value");
				return false;
			}
			return true;
		}
	}

}
