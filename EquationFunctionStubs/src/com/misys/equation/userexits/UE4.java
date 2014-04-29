package com.misys.equation.userexits;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.adaptor.AbstractFieldAdaptor;
import com.misys.equation.function.adaptor.AbstractFunctionAdaptor;
import com.misys.equation.function.adaptor.AbstractValueAdaptor;
import com.misys.equation.function.runtime.UserData;
import com.misys.equation.function.runtime.UserModifyData;

public class UE4 extends AbstractFunctionAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UE4.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	// -------------------------------------------------------------- INNER CLASS

	public class XMandatory_FieldAdaptor extends AbstractFieldAdaptor
	{
		@Override
		public boolean isMandatory(UserData userData)
		{
			return false;
		}
	}

	public class XMandatory2_FieldAdaptor extends AbstractFieldAdaptor
	{
		@Override
		public boolean isMandatory(UserData userData)
		{
			return (userData.rtvFieldInputValue("XMandatory").equals("MAND"));
		}
	}

	public class XValid_FieldAdaptor extends AbstractFieldAdaptor
	{
		@Override
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("XValid").toUpperCase().equals("ERR"))
			{
				getReturnMessages().addMessage("KSM0017");
				getReturnMessages().addMessageParam("KSM0026", "parm1", "parm2", "parm3");
				getReturnMessages().addError("This is an error!");
				return false;
			}
			else if (userData.rtvFieldInputValue("XValid").toUpperCase().equals("WARN"))
			{
				getReturnMessages().addWarning("Warning");
				return false;
			}
			else if (userData.rtvFieldInputValue("XValid").toUpperCase().equals("INFO"))
			{
				getReturnMessages().addInfo("Info");
				return false;
			}

			return true;
		}
	}

	public class XInput_FieldAdaptor extends AbstractFieldAdaptor
	{
		@Override
		public String getOutputValue(UserData userData)
		{
			return "Changed by user exit - output!";
		}

		@Override
		public String getInputValue(UserData userData)
		{
			return "Changed by user exit - input!";
		}
	}

	public class XAMT_GWV30R_$NDPAM_ValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "0";
		}

	}

	public class XAMT_GWV30R_$NODIG_ValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "15";
		}
	}

	public class AMT_UTR71R_X69LCY_ValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "GBP";
		}
	}

	public class AMT_UTR71R_X69DBR_ValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "0543";
		}
	}

	public class AMT_UTR71R_X69DR_ValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			if (Toolbox.parseInt(userData.rtvFieldDbValue("AMT"), 0) >= 0)
			{
				return "N";
			}
			else
			{
				return "Y";
			}
		}
	}

	public class AMT_UTR71R_X69USR_ValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "EQUI";
		}
	}

	public class ASIID_GZNR4_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "value adaptor user exit on update API";
		}
	}

	public class ASIID_GZNR4_LoadValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return "value adaptor user exit on load API";
		}
	}

	// -------------------------------------------------------------- METHODS
	@Override
	public void defaultMode(int curScreen, UserModifyData userModifyData)
	{
		if (curScreen == 3)
		{
			userModifyData.chgFieldInputValue("XReg", userModifyData.rtvFieldInputValue("XReg").trim() + "changed by default mode");
		}
	}

	@Override
	public void validateMode(int curScreen, UserModifyData userModifyData)
	{
		if (curScreen == 3)
		{
			if (userModifyData.rtvFieldInputValue("XMandatory").toUpperCase().equals("ERRR"))
			{
				getReturnMessages().addMessage("KSM0017");
				getReturnMessages().addMessageParam("KSM0026", "validate", "user exit", "mode");
				getReturnMessages().addError("This error from user exit validate mode!");
			}
			else if (userModifyData.rtvFieldInputValue("XMandatory").toUpperCase().equals("WARN"))
			{
				getReturnMessages().addWarning("Warning - validate user exit");
			}
			else if (userModifyData.rtvFieldInputValue("XMandatory").toUpperCase().equals("INFO"))
			{
				getReturnMessages().addInfo("Info - validate user exit");
			}
		}
	}
	@Override
	public void preUpdate(JournalHeader journalHeader, UserData userData)
	{
		if (userData.rtvFieldDbValue("NR1").equals("WARN1"))
		{
			getReturnMessages().addWarning("Warning from update mode 1");
		}
		else if (userData.rtvFieldDbValue("NR1").equals("ERROR1"))
		{
			getReturnMessages().addError("Error from update mode 1");
		}
		else if (userData.rtvFieldDbValue("NR1").equals("INFO1"))
		{
			getReturnMessages().addInfo("Info from update mode 1");
		}
	}

	@Override
	public void postUpdate(JournalHeader journalHeader, UserData userData)
	{
		if (userData.rtvFieldDbValue("NR2").equals("WARN2"))
		{
			getReturnMessages().addWarning("Warning from update mode 2");
		}
		else if (userData.rtvFieldDbValue("NR2").equals("ERROR2"))
		{
			getReturnMessages().addError("Error from update mode 2");
		}
		else if (userData.rtvFieldDbValue("NR2").equals("INFO2"))
		{
			getReturnMessages().addInfo("Info from update mode 2");
		}
	}

	@Override
	public String postLoad(UserModifyData userModifyData)
	{
		if (userModifyData.rtvFieldDbValue("NR2").equals("WARN2"))
		{
			getReturnMessages().addWarning("Warning from load mode 2");
		}
		else if (userModifyData.rtvFieldDbValue("NR2").equals("ERROR2"))
		{
			getReturnMessages().addError("Error from load mode 2");
		}
		else if (userModifyData.rtvFieldDbValue("NR2").equals("INFO2"))
		{
			getReturnMessages().addInfo("Info from load mode 2");
		}
		return null;
	}
}
