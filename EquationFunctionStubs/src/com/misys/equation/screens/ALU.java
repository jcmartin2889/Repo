package com.misys.equation.screens;

import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.adaptor.AbstractFunctionAdaptor;
import com.misys.equation.function.adaptor.AbstractValueAdaptor;
import com.misys.equation.function.runtime.UserData;
import com.misys.equation.function.runtime.UserModifyData;

public class ALU extends AbstractFunctionAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ALU.java 7879 2010-06-21 11:16:25Z lima12 $";
	// -------------------------------------------------------------- INNER CLASS

	public class CRM_HHAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "9132";
		}
	}

	public class CRM_HHAN_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "234567";
		}
	}

	public class CRM_HHAS_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "001";
		}
	}

	public class CRM_HHCY1_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "GBP";
		}
	}

	public class CRM_HHAMC_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "00000000000100";
		}
	}

	public class CRM_HHFCT_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "A";
		}
	}

	public class EFC_EBRNM_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "CUTE";
		}
	}

	public class EFC_EDLP_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "COA";
		}
	}

	public class EFC_EDLR_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "COA1";
		}
	}

	public class EFC_EDCCY_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "GBP";
		}
	}

	public class GY_GYIREF_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "ALU-IREF";
		}
	}

	public class GY_GYAPID_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "ALU-APID";
		}
	}

	// -------------------------------------------------------------- METHODS

	@Override
	public void postLoadEFC(UserData userData, UserModifyData userDataEFC)
	{
		int curData = Toolbox.parseInt(userDataEFC.rtvFieldDbValue("CURDATA"), 0);

		for (int i = 1; i <= curData; i++)
		{
			String is = "_" + i;
			if (userDataEFC.rtvFieldInputValue("GSFRQ" + is).trim().equals(""))
			{
				userDataEFC.chgFieldDbValue("GSFRQ" + is, "V31");
			}
		}
	}

	@Override
	public String postLoad(UserModifyData userModifyData)
	{
		if (userModifyData.rtvFieldInputValue("ADSC").trim().length() > 0)
		{
			return "F";
		}
		else
		{
			return "A";
		}
	}

}
