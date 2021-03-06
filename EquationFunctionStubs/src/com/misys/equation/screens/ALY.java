package com.misys.equation.screens;

import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.adaptor.AbstractFunctionAdaptor;
import com.misys.equation.function.adaptor.AbstractValueAdaptor;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.UserData;
import com.misys.equation.function.runtime.UserModifyData;

public class ALY extends AbstractFunctionAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ALY.java 9942 2010-11-17 16:56:54Z lima12 $";
	// -------------------------------------------------------------- INNER CLASS

	public class LID_GZHRD_LoadValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return curValue.trim() + "by Load";
		}
	}

	public class LID_GZHRD_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		@Override
		public String getValue(String curValue, UserData userData)
		{
			return curValue.trim() + "by Update";
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

	// -------------------------------------------------------------- METHODS

	@Override
	public String postLoad(UserModifyData userModifyData)
	{
		if (userModifyData.rtvFieldDbValue("HLD").equals("XX1"))
		{
			getReturnMessages().addWarning("Warning from load mode 2");
		}
		else if (userModifyData.rtvFieldDbValue("HLD").equals("XX2"))
		{
			getReturnMessages().addError("Error from load mode 2");
		}
		else if (userModifyData.rtvFieldDbValue("HLD").equals("XX3"))
		{
			getReturnMessages().addInfo("Info from load mode 2");
		}
		return null;
	}

	@Override
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

	@Override
	public void onCancel(UserModifyData userModifyData)
	{
		// key screen?
		if (userModifyData.rtvFieldDbValue(FunctionData.FLD_KEYLOADED).equals("Y"))
		{
			getReturnMessages().addWarning("Are you sure you want to exit function?");
		}

		// detail screeen?
		else
		{
			getReturnMessages().addWarning("Are you sure you want to abort transaction?");
		}

		getReturnMessages().addMessage("KSM0019");
		getReturnMessages().addMessage("KSMA899");
		getReturnMessages().addMessage("KSM0021");
	}

}