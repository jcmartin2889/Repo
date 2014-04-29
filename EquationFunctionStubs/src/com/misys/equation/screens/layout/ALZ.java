package com.misys.equation.screens.layout;

import com.misys.equation.function.adaptor.AbstractAttributesAdaptor;
import com.misys.equation.function.adaptor.AbstractDisplayGroupAdaptor;
import com.misys.equation.function.adaptor.AbstractLayoutAdaptor;
import com.misys.equation.function.runtime.UserData;

public class ALZ extends AbstractLayoutAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ALZ.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	// -------------------------------------------------------------- INNER CLASS

	public class XVisible_AttributesAdaptor extends AbstractAttributesAdaptor
	{
		@Override
		public boolean isVisible(UserData userData)
		{
			return (userData.rtvFieldInputValue("XMandatory").equals("VISI"));
		}
	}

	public class XVisible2_AttributesAdaptor extends AbstractAttributesAdaptor
	{
		@Override
		public boolean isVisible(UserData userData)
		{
			return true;
		}
	}

	public class XProtected_AttributesAdaptor extends AbstractAttributesAdaptor
	{
		@Override
		public boolean isProtected(UserData userData)
		{
			return false;
		}
	}

	public class XProtected2_AttributesAdaptor extends AbstractAttributesAdaptor
	{
		@Override
		public boolean isProtected(UserData userData)
		{
			return true;
		}
	}

	public class TA1_EQN_DisplayGroupAdaptor extends AbstractDisplayGroupAdaptor
	{
		@Override
		public boolean isVisible(UserData userData)
		{
			return true;
		}
	}

	// -------------------------------------------------------------- METHODS

	@Override
	public int prevScreen(int curScreen, UserData userData)
	{
		if (curScreen == 3 && userData.rtvFieldInputValue("XMandatory").equals("PAG1"))
		{
			return 1;
		}
		return 0;
	}

	@Override
	public int nextScreen(int curScreen, UserData userData)
	{
		if (curScreen == 1 && userData.rtvFieldInputValue("FLD1A").equals("ALZLIMA"))
		{
			return 3;
		}
		return 0;
	}

}
