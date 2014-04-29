package com.misys.equation.screens;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.adaptor.AbstractFieldAdaptor;
import com.misys.equation.function.adaptor.AbstractFunctionAdaptor;
import com.misys.equation.function.adaptor.AbstractValueAdaptor;
import com.misys.equation.function.runtime.UserData;
import com.misys.equation.function.runtime.UserModifyData;

public class ALZ extends AbstractFunctionAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ALZ.java 9942 2010-11-17 16:56:54Z lima12 $";
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

	public class XVisible_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isVisible(UserData userData)
		{
			return (userData.rtvFieldInputValue("XMandatory").equals("VISI"));
		}
	}

	public class XVisible2_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isVisible(UserData userData)
		{
			return true;
		}
	}

	public class XProtected_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isProtected(UserData userData)
		{
			return false;
		}
	}

	public class XProtected2_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isProtected(UserData userData)
		{
			return true;
		}
	}

	public class XValid_FieldAdaptor extends AbstractFieldAdaptor
	{
		@Override
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("XValid").toUpperCase().equals("ERR"))
			{
				getReturnMessages().addMessage("KSM0017", "XMandatory");
				getReturnMessages().addMessageParam("KSM0026", "parm1 that is quite long", "parm2  that is quite long",
								"parm3  that is quite long - END.");
				getReturnMessages().addError("This is an error that is quite long as well !!!!!!!! Lonnnnnnggggg  -END.");
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

	public class XPrimitive_FieldAdaptor extends AbstractFieldAdaptor
	{
		@Override
		public String getPrimitiveValue(UserData userData)
		{
			return "Changed by user exit - primitive!";
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
		public String getValue(String curValue, UserData userData)
		{
			return "0543";
		}
	}

	public class AMT_UTR71R_X69DR_ValueAdaptor extends AbstractValueAdaptor
	{
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
		public String getValue(String curValue, UserData userData)
		{
			return "EQUI";
		}
	}

	public class ASIID_GZNR4_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return "value adaptor user exit on update API";
		}
	}

	public class ASIID_GZNR4_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return "value adaptor user exit on load API";
		}
	}

	// -------------------------------------------------------------- METHODS

	public int prevScreen(int curScreen, UserData userData)
	{
		if (curScreen == 3 && userData.rtvFieldInputValue("XMandatory").equals("PAG1"))
		{
			return 1;
		}
		return 0;
	}

	public int nextScreen(int curScreen, UserData userData)
	{
		if (curScreen == 1 && userData.rtvFieldInputValue("FLD1A").equals("PAG3"))
		{
			return 3;
		}
		return 0;
	}

	public void defaultMode(int curScreen, UserModifyData userModifyData)
	{
		if (curScreen == 3)
		{
			userModifyData.chgFieldInputValue("XReg", userModifyData.rtvFieldInputValue("XReg").trim() + "changed by default mode");
		}
	}

	public void validateMode(int curScreen, UserModifyData userData)
	{
		if (curScreen == 3)
		{
			if (userData.rtvFieldInputValue("XMandatory").toUpperCase().equals("ERRR"))
			{
				getReturnMessages().addMessage("KSM0017", "XMandatory");
				getReturnMessages().addMessageParam("KSM0026", "validate", "user exit", "mode");
				getReturnMessages().addError("This error from user exit validate mode!");
			}
			else if (userData.rtvFieldInputValue("XMandatory").toUpperCase().equals("WARN"))
			{
				getReturnMessages().addWarning("Warning - validate user exit", "XMandatory");
			}
			else if (userData.rtvFieldInputValue("XMandatory").toUpperCase().equals("INFO"))
			{
				getReturnMessages().addInfo("Info - validate user exit", "XMandatory");
			}
			else if (userData.rtvFieldInputValue("XMandatory").toUpperCase().equals("CHG"))
			{
				userData.chgFieldInputValue("XMandatory", "CHG1");
			}
		}
	}

	public void preUpdate(JournalHeader journalHeader, UserData userData)
	{
		if (userData.rtvFieldDbValue("NR1").equals("WARN1"))
		{
			getReturnMessages().addWarning("Warning from update mode 1");
		}
		else if (userData.rtvFieldDbValue("NR1").equals("ERROR1"))
		{
			getReturnMessages().addError("Error from update mode 1", "NR1");
		}
		else if (userData.rtvFieldDbValue("NR1").equals("INFO1"))
		{
			getReturnMessages().addInfo("Info from update mode 1", "NR1");
		}
	}

	public void postUpdate(JournalHeader journalHeader, UserData userData)
	{
		if (userData.rtvFieldDbValue("NR2").equals("WARN2"))
		{
			getReturnMessages().addWarning("Warning from update mode 2", "NR2");
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
		getMethodCallStatus().setAbstractPostLoadMethodExecute(true);
		return null;
	}

	public void postLoadEFC(UserData userData, UserModifyData userDataEFC)
	{
		int curData = Toolbox.parseInt(userDataEFC.rtvFieldDbValue("CURDATA"), 0);

		for (int i = 1; i <= curData; i++)
		{
			String is = "_" + i;
			if (userDataEFC.rtvFieldInputValue("GSFRQ" + is).trim().equals(""))
			{
				String day = userData.getUserAccessHandler().getEquationUser().getEquationUnit().getProcessingDate()
								.substring(0, 2);
				userDataEFC.chgFieldDbValue("GSFRQ" + is, "V" + day);
			}
		}
	}

}
