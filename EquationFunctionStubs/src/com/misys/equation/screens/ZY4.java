package com.misys.equation.screens;

import com.misys.equation.function.adaptor.AbstractFieldAdaptor;
import com.misys.equation.function.adaptor.AbstractFunctionAdaptor;
import com.misys.equation.function.runtime.UserData;

public class ZY4 extends AbstractFunctionAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ZY4.java 7610 2010-06-01 17:10:41Z MACDONP1 $";

	public class A_OMCCY_FieldAdaptor extends AbstractFieldAdaptor
	{
		@Override
		public boolean isMandatory(UserData userData)
		{
			return true;
		}
	}

	public class B_OMCCY_FieldAdaptor extends AbstractFieldAdaptor
	{
		@Override
		public boolean isMandatory(UserData userData)
		{
			return true;
		}
	}

	public class C_OMCCY_FieldAdaptor extends AbstractFieldAdaptor
	{
		@Override
		public boolean isMandatory(UserData userData)
		{
			return true;
		}
	}

}