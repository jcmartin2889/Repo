package com.misys.equation.screens.layout;

import com.misys.equation.function.adaptor.AbstractAttributesAdaptor;
import com.misys.equation.function.adaptor.AbstractDisplayGroupAdaptor;
import com.misys.equation.function.adaptor.AbstractLayoutAdaptor;
import com.misys.equation.function.runtime.UserData;
import com.misys.equation.function.useraccess.UserExitListColumnData;

public class ZY7 extends AbstractLayoutAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ZY7.java 9643 2010-10-29 14:16:08Z lima12 $";
	// -------------------------------------------------------------- INNER CLASS

	public class A_ASG_DisplayGroupAdaptor extends AbstractDisplayGroupAdaptor
	{
		public UserExitListColumnData getTopColumnDetails(UserData userData, String fieldName)
		{
			if (fieldName.equals("A_OMDTE"))
			{
				UserExitListColumnData returnValues = new UserExitListColumnData("", "1000105", "own desc");
				returnValues.setStyle("wf_INFO");
				return returnValues;
			}
			else if (fieldName.equals("A_OMMVT"))
			{
				return new UserExitListColumnData("testing" + fieldName, "testing" + fieldName, "");
			}
			else if (fieldName.equals("A_OMMVTS"))
			{
				return new UserExitListColumnData("testing" + fieldName, "testing" + fieldName, "");
			}
			else if (fieldName.equals("A_OMNS3"))
			{
				return new UserExitListColumnData("testing" + fieldName, "testing" + fieldName, "");
			}
			else if (fieldName.equals("A_OMPRF"))
			{
				return new UserExitListColumnData("testing" + fieldName, "testing" + fieldName, "");
			}
			else if (fieldName.equals("A_OMCCY"))
			{
				return new UserExitListColumnData("testing" + fieldName, "testing" + fieldName, "");
			}
			else if (fieldName.equals("A_OMNWP"))
			{
				return new UserExitListColumnData("testing" + fieldName, "testing" + fieldName, "");
			}
			else if (fieldName.equals("A_OMNWR"))
			{
				return new UserExitListColumnData("testing" + fieldName, "testing" + fieldName, "");
			}
			else if (fieldName.equals("A_LSAMTD"))
			{
				return new UserExitListColumnData("testing" + fieldName, "testing" + fieldName, "");
			}
			else if (fieldName.equals("A_LSAMTP"))
			{
				return new UserExitListColumnData("testing" + fieldName, "testing" + fieldName, "");
			}
			else if (fieldName.equals("A_LSLSC"))
			{
				return new UserExitListColumnData("testing" + fieldName, "testing" + fieldName, "");
			}
			else if (fieldName.equals("A_LSUP"))
			{
				return new UserExitListColumnData("testing" + fieldName, "testing" + fieldName, "");
			}
			else if (fieldName.equals("A_C8CED"))
			{
				return new UserExitListColumnData("", "3", "");
			}

			return null;
		}

		public UserExitListColumnData getBottomColumnDetails(UserData userData, String fieldName)
		{
			if (fieldName.equals("A_OMDTE"))
			{
			}
			else if (fieldName.equals("A_OMMVT"))
			{
				UserExitListColumnData returnValues = new UserExitListColumnData("", fieldName, "");
				returnValues.setRowStyle("wf_INFO");
				returnValues.setStyle("repeatingDataRowStyle2");
				return returnValues;
			}
			else if (fieldName.equals("A_OMMVTS"))
			{
			}
			else if (fieldName.equals("A_OMNS3"))
			{
			}
			else if (fieldName.equals("A_OMPRF"))
			{
			}
			else if (fieldName.equals("A_OMCCY"))
			{
				return new UserExitListColumnData("", fieldName, "");
			}
			else if (fieldName.equals("A_OMNWP"))
			{
				return new UserExitListColumnData("10T", "1000000", "");
			}
			else if (fieldName.equals("A_OMNWR"))
			{
			}
			else if (fieldName.equals("A_LSAMTD"))
			{
				return new UserExitListColumnData("", fieldName, "");
			}
			else if (fieldName.equals("A_LSAMTP"))
			{
			}
			else if (fieldName.equals("A_LSLSC"))
			{
			}
			else if (fieldName.equals("A_LSUP"))
			{
			}
			else if (fieldName.equals("A_C8CED"))
			{
			}
			return null;
		}

		public UserExitListColumnData getAboveRowColumnDetails(UserData userData, String fieldName)
		{
			String value = userData.rtvFieldInputValue("A_OMMVT") + userData.rtvFieldInputValue("A_OMMVTS");

			if (value.equals("PC"))
			{
				if (fieldName.equals("A_OMDTE"))
				{
					return new UserExitListColumnData("2D", "1000104", "");
				}
				else if (fieldName.equals("A_OMMVT"))
				{
					UserExitListColumnData returnValues = new UserExitListColumnData(
									"",
									"START DATE RECORD ABOVE IN MULTILINE!N_L! this is the second line !N_L! this is the third line",
									"");
					returnValues.setRowStyle("wf_INFO");
					return returnValues;
				}
				else if (fieldName.equals("A_OMCCY"))
				{
					return new UserExitListColumnData("", fieldName, "");
				}
				else if (fieldName.equals("A_OMNWP"))
				{
					return new UserExitListColumnData("", fieldName, "");
				}
				else if (fieldName.equals("A_OMNWR"))
				{
					return new UserExitListColumnData("", fieldName, "");
				}
				else if (fieldName.equals("A_LSAMTD"))
				{
					return new UserExitListColumnData("", fieldName, "");
				}
				else if (fieldName.equals("A_LSAMTP"))
				{
					return new UserExitListColumnData("", fieldName, "");
				}
				else if (fieldName.equals("A_LSLSC"))
				{
					return new UserExitListColumnData("", fieldName, "");
				}
				else if (fieldName.equals("A_LSUP"))
				{
					return new UserExitListColumnData("", fieldName, "");
				}
				else if (fieldName.equals("A_C8CED"))
				{
					return new UserExitListColumnData("", fieldName, "");
				}
			}

			if (value.equals("PU"))
			{
				if (fieldName.equals("A_OMDTE"))
				{
					return new UserExitListColumnData("", "MATURITY DATE ABOVE", "");
				}
			}

			return null;
		}

		public UserExitListColumnData getBottomRowColumnDetails(UserData userData, String fieldName)
		{
			String value = userData.rtvFieldInputValue("A_OMMVT") + userData.rtvFieldInputValue("A_OMMVTS");

			if (value.equals("PC"))
			{
				if (fieldName.equals("A_OMMVT"))
				{
					return new UserExitListColumnData("", "START DATE RECORD BELOW", "");
				}
				else if (fieldName.equals("A_C8CED"))
				{
					return new UserExitListColumnData("", fieldName, "");
				}
			}

			if (value.equals("IV"))
			{
				if (fieldName.equals("A_OMDTE"))
				{
					return new UserExitListColumnData("", "INTEREST RECORD BELOW", "");
				}
				else if (fieldName.equals("A_LSAMTD"))
				{
					return new UserExitListColumnData("", fieldName, "");
				}
			}

			return null;
		}

	}

	public class A_OMNWP_AttributesAdaptor extends AbstractAttributesAdaptor
	{
		public String getEditingParameter(UserData userData)
		{
			return "USD";
		}
	}

	public class A_LSAMTD_AttributesAdaptor extends AbstractAttributesAdaptor
	{
		public String getEditingParameter(UserData userData)
		{
			return "ARA";
		}
	}

	public class A_LSAMTP_AttributesAdaptor extends AbstractAttributesAdaptor
	{
		public String getEditingParameter(UserData userData)
		{
			return "MYC";
		}
	}

	public class B_ASG_DisplayGroupAdaptor extends AbstractDisplayGroupAdaptor
	{
		public UserExitListColumnData getTopColumnDetails(UserData userData, String fieldName)
		{
			if (fieldName.equals("B_OMDTE"))
			{
				return new UserExitListColumnData("", "1000501", "");
			}

			return null;
		}

		public UserExitListColumnData getBottomColumnDetails(UserData userData, String fieldName)
		{
			if (fieldName.equals("B_C8CED"))
			{
				return new UserExitListColumnData("", "B_ASG_CED", "");
			}
			return null;
		}

		public UserExitListColumnData getAboveRowColumnDetails(UserData userData, String fieldName)
		{
			return null;
		}

		public UserExitListColumnData getBottomRowColumnDetails(UserData userData, String fieldName)
		{
			return null;
		}

	}

	public class C_ASG_DisplayGroupAdaptor extends AbstractDisplayGroupAdaptor
	{
		public UserExitListColumnData getTopColumnDetails(UserData userData, String fieldName)
		{
			if (fieldName.equals("C_C8CED"))
			{
				return new UserExitListColumnData("", "2", "my output");
			}

			return null;
		}

		public UserExitListColumnData getBottomColumnDetails(UserData userData, String fieldName)
		{
			if (fieldName.equals("C_OMDTE"))
			{
				return new UserExitListColumnData("", "1000104", "");
			}
			return null;
		}

		public UserExitListColumnData getAboveRowColumnDetails(UserData userData, String fieldName)
		{
			return null;
		}

		public UserExitListColumnData getBottomRowColumnDetails(UserData userData, String fieldName)
		{
			return null;
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
		if (curScreen == 1 && userData.rtvFieldInputValue("FLD1A").equals("ALZLIMA"))
		{
			return 3;
		}
		return 0;
	}

}
