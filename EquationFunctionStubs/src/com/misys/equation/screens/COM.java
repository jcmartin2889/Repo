package com.misys.equation.screens;

import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.adaptor.AbstractFieldSetAdaptor;
import com.misys.equation.function.adaptor.AbstractFunctionAdaptor;
import com.misys.equation.function.adaptor.AbstractValueAdaptor;
import com.misys.equation.function.runtime.UserData;
import com.misys.equation.function.runtime.UserModifyData;

public class COM extends AbstractFunctionAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: COM.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	// -------------------------------------------------------------- METHODS

	public class DLA_GWV30R_$NDPAM_ValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "0";
		}

	}

	public class DLA_GWV30R_$NODIG_ValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "15";
		}
	}

	public class CRM_HHAMC_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			String amount = userData.rtvFieldDbValue("DLA").trim();
			if (amount.length() > 15)
			{
				return amount.substring(amount.length() - 15);
			}
			else
			{
				return amount;
			}
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

	public class EFC_ENEW_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("wfAB").trim().length() > 0)
			{
				return "N";
			}
			else
			{
				return "Y";
			}
		}
	}

	public class CRM__UpdateFieldSet extends AbstractFieldSetAdaptor
	{
		@Override
		public boolean shouldExecuteModule(UserData userData)
		{
			if (userData.rtvFieldDbValue("wfAB").trim().length() > 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}

	public class EFC__UpdateFieldSet extends AbstractFieldSetAdaptor
	{
		@Override
		public boolean shouldExecuteModule(UserData userData)
		{
			return true;
		}
	}

	@Override
	public void postLoadEFC(UserData userData, UserModifyData userDataEFC)
	{
		int curData = Toolbox.parseInt(userDataEFC.rtvFieldDbValue("CURDATA"), 0);

		for (int i = 1; i <= curData; i++)
		{
			String is = "_" + i;
			userDataEFC.chgFieldDbValue("GSSDT" + is, "991231");
			userDataEFC.chgFieldDbValue("GSEND" + is, "991231");

			userDataEFC.chgFieldDbValue("GSFAB" + is, "9132");
			userDataEFC.chgFieldDbValue("GSFAN" + is, "234567");
			userDataEFC.chgFieldDbValue("GSFAS" + is, "001");

			if (userDataEFC.rtvFieldInputValue("GSFRQ" + is).trim().equals(""))
			{
				userDataEFC.chgFieldDbValue("GSFRQ" + is, "V31");
			}
		}
	}

}
