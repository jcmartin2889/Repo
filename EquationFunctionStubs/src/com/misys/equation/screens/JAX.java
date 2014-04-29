package com.misys.equation.screens;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTable;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.adaptor.AbstractFieldAdaptor;
import com.misys.equation.function.adaptor.AbstractFieldSetAdaptor;
import com.misys.equation.function.adaptor.AbstractFunctionAdaptor;
import com.misys.equation.function.adaptor.AbstractValueAdaptor;
import com.misys.equation.function.runtime.UserData;
import com.misys.equation.function.runtime.UserModifyData;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;

public class JAX extends AbstractFunctionAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: JAX.java 17139 2013-08-29 16:00:56Z whittap1 $";

	int MAX_FILE_REC = 20;
	int MAX_REC = 20;

	// -------------------------------------------------------------- INNER CLASS
	public class SINP_1_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_1").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_1").equals(userData.rtvFieldInputValue("OCUS_1"))
								&& userData.rtvFieldInputValue("SCLC_1").equals(userData.rtvFieldInputValue("OCLC_1"))
								&& userData.rtvFieldInputValue("SREL_1").equals(userData.rtvFieldInputValue("OREL_1"))
								&& userData.rtvFieldInputValue("SDUP_1").equals(userData.rtvFieldInputValue("ODUP_1"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_1__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_1").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_1").trim().equals("");
		}
	}
	public class SCUS_1_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_1").trim().equals("");
		}
	}

	public class SCLC_1_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_1").trim().equals("");
		}
	}

	public class wfFCT_1_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_1").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_1").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_1").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_1_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_1");
		}
	}

	public class wfFCT_1_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_1").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_1_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}

	public class SINP_2_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_2").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_2").equals(userData.rtvFieldInputValue("OCUS_2"))
								&& userData.rtvFieldInputValue("SCLC_2").equals(userData.rtvFieldInputValue("OCLC_2"))
								&& userData.rtvFieldInputValue("SREL_2").equals(userData.rtvFieldInputValue("OREL_2"))
								&& userData.rtvFieldInputValue("SDUP_2").equals(userData.rtvFieldInputValue("ODUP_2"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_2__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_2").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_2").trim().equals("");
		}
	}
	public class SCUS_2_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_2").trim().equals("");
		}
	}

	public class SCLC_2_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_2").trim().equals("");
		}
	}

	public class wfFCT_2_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_2").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_2").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_2").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_2_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_2");
		}
	}

	public class wfFCT_2_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_2").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_2_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}

	public class SINP_3_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_3").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_3").equals(userData.rtvFieldInputValue("OCUS_3"))
								&& userData.rtvFieldInputValue("SCLC_3").equals(userData.rtvFieldInputValue("OCLC_3"))
								&& userData.rtvFieldInputValue("SREL_3").equals(userData.rtvFieldInputValue("OREL_3"))
								&& userData.rtvFieldInputValue("SDUP_3").equals(userData.rtvFieldInputValue("ODUP_3"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_3__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_3").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_3").trim().equals("");
		}
	}
	public class SCUS_3_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_3").trim().equals("");
		}
	}

	public class SCLC_3_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_3").trim().equals("");
		}
	}

	public class wfFCT_3_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_3").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_3").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_3").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_3_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_3");
		}
	}

	public class wfFCT_3_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_3").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_3_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}

	public class SINP_4_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_4").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_4").equals(userData.rtvFieldInputValue("OCUS_4"))
								&& userData.rtvFieldInputValue("SCLC_4").equals(userData.rtvFieldInputValue("OCLC_4"))
								&& userData.rtvFieldInputValue("SREL_4").equals(userData.rtvFieldInputValue("OREL_4"))
								&& userData.rtvFieldInputValue("SDUP_4").equals(userData.rtvFieldInputValue("ODUP_4"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_4__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_4").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_4").trim().equals("");
		}
	}
	public class SCUS_4_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_4").trim().equals("");
		}
	}

	public class SCLC_4_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_4").trim().equals("");
		}
	}

	public class wfFCT_4_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_4").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_4").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_4").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_4_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_4");
		}
	}

	public class wfFCT_4_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_4").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_4_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}

	public class SINP_5_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_5").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_5").equals(userData.rtvFieldInputValue("OCUS_5"))
								&& userData.rtvFieldInputValue("SCLC_5").equals(userData.rtvFieldInputValue("OCLC_5"))
								&& userData.rtvFieldInputValue("SREL_5").equals(userData.rtvFieldInputValue("OREL_5"))
								&& userData.rtvFieldInputValue("SDUP_5").equals(userData.rtvFieldInputValue("ODUP_5"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_5__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_5").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_5").trim().equals("");
		}
	}
	public class SCUS_5_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_5").trim().equals("");
		}
	}

	public class SCLC_5_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_5").trim().equals("");
		}
	}

	public class wfFCT_5_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_5").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_5").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_5").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_5_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_5");
		}
	}

	public class wfFCT_5_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_5").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_5_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}

	public class SINP_6_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_6").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_6").equals(userData.rtvFieldInputValue("OCUS_6"))
								&& userData.rtvFieldInputValue("SCLC_6").equals(userData.rtvFieldInputValue("OCLC_6"))
								&& userData.rtvFieldInputValue("SREL_6").equals(userData.rtvFieldInputValue("OREL_6"))
								&& userData.rtvFieldInputValue("SDUP_6").equals(userData.rtvFieldInputValue("ODUP_6"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_6__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_6").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_6").trim().equals("");
		}
	}
	public class SCUS_6_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_6").trim().equals("");
		}
	}

	public class SCLC_6_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_6").trim().equals("");
		}
	}

	public class wfFCT_6_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_6").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_6").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_6").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_6_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_6");
		}
	}

	public class wfFCT_6_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_6").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_6_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}

	public class SINP_7_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_7").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_7").equals(userData.rtvFieldInputValue("OCUS_7"))
								&& userData.rtvFieldInputValue("SCLC_7").equals(userData.rtvFieldInputValue("OCLC_7"))
								&& userData.rtvFieldInputValue("SREL_7").equals(userData.rtvFieldInputValue("OREL_7"))
								&& userData.rtvFieldInputValue("SDUP_7").equals(userData.rtvFieldInputValue("ODUP_7"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_7__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_7").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_7").trim().equals("");
		}
	}
	public class SCUS_7_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_7").trim().equals("");
		}
	}

	public class SCLC_7_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_7").trim().equals("");
		}
	}

	public class wfFCT_7_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_7").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_7").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_7").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_7_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_7");
		}
	}

	public class wfFCT_7_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_7").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_7_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}

	public class SINP_8_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_8").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_8").equals(userData.rtvFieldInputValue("OCUS_8"))
								&& userData.rtvFieldInputValue("SCLC_8").equals(userData.rtvFieldInputValue("OCLC_8"))
								&& userData.rtvFieldInputValue("SREL_8").equals(userData.rtvFieldInputValue("OREL_8"))
								&& userData.rtvFieldInputValue("SDUP_8").equals(userData.rtvFieldInputValue("ODUP_8"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_8__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_8").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_8").trim().equals("");
		}
	}
	public class SCUS_8_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_8").trim().equals("");
		}
	}

	public class SCLC_8_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_8").trim().equals("");
		}
	}

	public class wfFCT_8_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_8").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_8").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_8").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_8_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_8");
		}
	}

	public class wfFCT_8_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_8").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_8_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}

	public class SINP_9_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_9").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_9").equals(userData.rtvFieldInputValue("OCUS_9"))
								&& userData.rtvFieldInputValue("SCLC_9").equals(userData.rtvFieldInputValue("OCLC_9"))
								&& userData.rtvFieldInputValue("SREL_9").equals(userData.rtvFieldInputValue("OREL_9"))
								&& userData.rtvFieldInputValue("SDUP_9").equals(userData.rtvFieldInputValue("ODUP_9"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_9__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_9").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_9").trim().equals("");
		}
	}
	public class SCUS_9_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_9").trim().equals("");
		}
	}

	public class SCLC_9_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_9").trim().equals("");
		}
	}

	public class wfFCT_9_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_9").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_9").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_9").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_9_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_9");
		}
	}

	public class wfFCT_9_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_9").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_9_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}

	public class SINP_10_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_10").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_10").equals(userData.rtvFieldInputValue("OCUS_10"))
								&& userData.rtvFieldInputValue("SCLC_10").equals(userData.rtvFieldInputValue("OCLC_10"))
								&& userData.rtvFieldInputValue("SREL_10").equals(userData.rtvFieldInputValue("OREL_10"))
								&& userData.rtvFieldInputValue("SDUP_10").equals(userData.rtvFieldInputValue("ODUP_10"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_10__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_10").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_10").trim().equals("");
		}
	}
	public class SCUS_10_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_10").trim().equals("");
		}
	}

	public class SCLC_10_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_10").trim().equals("");
		}
	}

	public class wfFCT_10_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_10").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_10").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_10").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_10_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_10");
		}
	}

	public class wfFCT_10_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_10").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_10_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}

	public class SINP_11_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_11").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_11").equals(userData.rtvFieldInputValue("OCUS_11"))
								&& userData.rtvFieldInputValue("SCLC_11").equals(userData.rtvFieldInputValue("OCLC_11"))
								&& userData.rtvFieldInputValue("SREL_11").equals(userData.rtvFieldInputValue("OREL_11"))
								&& userData.rtvFieldInputValue("SDUP_11").equals(userData.rtvFieldInputValue("ODUP_11"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_11__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_11").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_11").trim().equals("");
		}
	}
	public class SCUS_11_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_11").trim().equals("");
		}
	}

	public class SCLC_11_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_11").trim().equals("");
		}
	}

	public class wfFCT_11_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_11").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_11").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_11").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_11_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_11");
		}
	}

	public class wfFCT_11_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_11").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_11_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}

	public class SINP_12_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_12").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_12").equals(userData.rtvFieldInputValue("OCUS_12"))
								&& userData.rtvFieldInputValue("SCLC_12").equals(userData.rtvFieldInputValue("OCLC_12"))
								&& userData.rtvFieldInputValue("SREL_12").equals(userData.rtvFieldInputValue("OREL_12"))
								&& userData.rtvFieldInputValue("SDUP_12").equals(userData.rtvFieldInputValue("ODUP_12"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_12__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_12").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_12").trim().equals("");
		}
	}
	public class SCUS_12_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_12").trim().equals("");
		}
	}

	public class SCLC_12_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_12").trim().equals("");
		}
	}

	public class wfFCT_12_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_12").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_12").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_12").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_12_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_12");
		}
	}

	public class wfFCT_12_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_12").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_12_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}

	public class SINP_13_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_13").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_13").equals(userData.rtvFieldInputValue("OCUS_13"))
								&& userData.rtvFieldInputValue("SCLC_13").equals(userData.rtvFieldInputValue("OCLC_13"))
								&& userData.rtvFieldInputValue("SREL_13").equals(userData.rtvFieldInputValue("OREL_13"))
								&& userData.rtvFieldInputValue("SDUP_13").equals(userData.rtvFieldInputValue("ODUP_13"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_13__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_13").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_13").trim().equals("");
		}
	}
	public class SCUS_13_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_13").trim().equals("");
		}
	}

	public class SCLC_13_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_13").trim().equals("");
		}
	}

	public class wfFCT_13_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_13").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_13").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_13").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_13_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_13");
		}
	}

	public class wfFCT_13_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_13").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_13_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}

	public class SINP_14_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_14").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_14").equals(userData.rtvFieldInputValue("OCUS_14"))
								&& userData.rtvFieldInputValue("SCLC_14").equals(userData.rtvFieldInputValue("OCLC_14"))
								&& userData.rtvFieldInputValue("SREL_14").equals(userData.rtvFieldInputValue("OREL_14"))
								&& userData.rtvFieldInputValue("SDUP_14").equals(userData.rtvFieldInputValue("ODUP_14"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_14__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_14").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_14").trim().equals("");
		}
	}
	public class SCUS_14_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_14").trim().equals("");
		}
	}

	public class SCLC_14_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_14").trim().equals("");
		}
	}

	public class wfFCT_14_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_14").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_14").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_14").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_14_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_14");
		}
	}

	public class wfFCT_14_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_14").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_14_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}

	public class SINP_15_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_15").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_15").equals(userData.rtvFieldInputValue("OCUS_15"))
								&& userData.rtvFieldInputValue("SCLC_15").equals(userData.rtvFieldInputValue("OCLC_15"))
								&& userData.rtvFieldInputValue("SREL_15").equals(userData.rtvFieldInputValue("OREL_15"))
								&& userData.rtvFieldInputValue("SDUP_15").equals(userData.rtvFieldInputValue("ODUP_15"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_15__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_15").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_15").trim().equals("");
		}
	}
	public class SCUS_15_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_15").trim().equals("");
		}
	}

	public class SCLC_15_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_15").trim().equals("");
		}
	}

	public class wfFCT_15_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_15").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_15").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_15").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_15_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_15");
		}
	}

	public class wfFCT_15_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_15").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_15_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}

	public class SINP_16_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_16").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_16").equals(userData.rtvFieldInputValue("OCUS_16"))
								&& userData.rtvFieldInputValue("SCLC_16").equals(userData.rtvFieldInputValue("OCLC_16"))
								&& userData.rtvFieldInputValue("SREL_16").equals(userData.rtvFieldInputValue("OREL_16"))
								&& userData.rtvFieldInputValue("SDUP_16").equals(userData.rtvFieldInputValue("ODUP_16"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_16__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_16").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_16").trim().equals("");
		}
	}
	public class SCUS_16_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_16").trim().equals("");
		}
	}

	public class SCLC_16_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_16").trim().equals("");
		}
	}

	public class wfFCT_16_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_16").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_16").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_16").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_16_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_16");
		}
	}

	public class wfFCT_16_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_16").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_16_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}

	public class SINP_17_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_17").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_17").equals(userData.rtvFieldInputValue("OCUS_17"))
								&& userData.rtvFieldInputValue("SCLC_17").equals(userData.rtvFieldInputValue("OCLC_17"))
								&& userData.rtvFieldInputValue("SREL_17").equals(userData.rtvFieldInputValue("OREL_17"))
								&& userData.rtvFieldInputValue("SDUP_17").equals(userData.rtvFieldInputValue("ODUP_17"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_17__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_17").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_17").trim().equals("");
		}
	}
	public class SCUS_17_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_17").trim().equals("");
		}
	}

	public class SCLC_17_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_17").trim().equals("");
		}
	}

	public class wfFCT_17_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_17").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_17").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_17").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_17_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_17");
		}
	}

	public class wfFCT_17_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_17").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_17_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}

	public class SINP_18_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_18").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_18").equals(userData.rtvFieldInputValue("OCUS_18"))
								&& userData.rtvFieldInputValue("SCLC_18").equals(userData.rtvFieldInputValue("OCLC_18"))
								&& userData.rtvFieldInputValue("SREL_18").equals(userData.rtvFieldInputValue("OREL_18"))
								&& userData.rtvFieldInputValue("SDUP_18").equals(userData.rtvFieldInputValue("ODUP_18"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_18__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_18").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_18").trim().equals("");
		}
	}
	public class SCUS_18_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_18").trim().equals("");
		}
	}

	public class SCLC_18_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_18").trim().equals("");
		}
	}

	public class wfFCT_18_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_18").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_18").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_18").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_18_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_18");
		}
	}

	public class wfFCT_18_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_18").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_18_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}

	public class SINP_19_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_19").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_19").equals(userData.rtvFieldInputValue("OCUS_19"))
								&& userData.rtvFieldInputValue("SCLC_19").equals(userData.rtvFieldInputValue("OCLC_19"))
								&& userData.rtvFieldInputValue("SREL_19").equals(userData.rtvFieldInputValue("OREL_19"))
								&& userData.rtvFieldInputValue("SDUP_19").equals(userData.rtvFieldInputValue("ODUP_19"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_19__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_19").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_19").trim().equals("");
		}
	}
	public class SCUS_19_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_19").trim().equals("");
		}
	}

	public class SCLC_19_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_19").trim().equals("");
		}
	}

	public class wfFCT_19_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_19").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_19").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_19").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_19_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_19");
		}
	}

	public class wfFCT_19_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_19").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_19_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}

	public class SINP_20_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isValid(UserData userData)
		{
			if (userData.rtvFieldInputValue("SINP_20").equals("D"))
			{
				boolean valid = userData.rtvFieldInputValue("SCUS_20").equals(userData.rtvFieldInputValue("OCUS_20"))
								&& userData.rtvFieldInputValue("SCLC_20").equals(userData.rtvFieldInputValue("OCLC_20"))
								&& userData.rtvFieldInputValue("SREL_20").equals(userData.rtvFieldInputValue("OREL_20"))
								&& userData.rtvFieldInputValue("SDUP_20").equals(userData.rtvFieldInputValue("ODUP_20"));
				if (!valid)
				{
					getReturnMessages().addMessage("KSM2539");
				}
				return valid;
			}
			return true;
		}
	}
	public class SCLC_20__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor
	{
		public boolean shouldExecuteModule(UserData userdata)
		{
			return !userdata.rtvFieldDbValue("SCUS_20").trim().equals("") || !userdata.rtvFieldDbValue("SCLC_20").trim().equals("");
		}
	}
	public class SCUS_20_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_20").trim().equals("");
		}
	}

	public class SCLC_20_FieldAdaptor extends AbstractFieldAdaptor
	{
		public boolean isMandatory(UserData userData)
		{
			return !userData.rtvFieldDbValue("OCUS_20").trim().equals("");
		}
	}

	public class wfFCT_20_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("SINP_20").equals("D"))
			{
				return "D";
			}
			else if (!userData.rtvFieldDbValue("OCUS_20").trim().equals(""))
			{
				return "M";
			}
			else if (!userData.rtvFieldDbValue("SCUS_20").trim().equals(""))
			{
				return "A";
			}
			else
			{
				return "";
			}
		}
	}

	public class SDUP_20_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("ODUP_20");
		}
	}

	public class wfFCT_20_LoadValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("OCUS_20").trim().equals(""))
			{
				return "";
			}
			else
			{
				return "M";
			}
		}
	}

	public class JAR_20_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			return userData.rtvFieldDbValue("PAB");
		}
	}
	// -------------------------------------------------------------- METHODS

	public void defaultMode(int curScreen, UserModifyData userModifyData)
	{
	}

	public void validateMode(int curScreen, UserData userData)
	{
	}

	public void preUpdate(UserData userData)
	{
	}

	public void postUpdate(JournalHeader journalHeader, UserData userData)
	{
		try
		{
			// check if RLPF Joint account narrative record exists
			EquationStandardSession session = userData.getUserAccessHandler().getSession();
			EquationStandardTable table = new EquationStandardTable("RLPF", "RL10LF", "RLAB:RLAN:RLAS", session);
			table.setFieldValue("RLAB", userData.rtvFieldDbValue("PAB"));
			table.setFieldValue("RLAN", userData.rtvFieldDbValue("PAN"));
			table.setFieldValue("RLAS", userData.rtvFieldDbValue("PAS"));
			session.retrieveEquationTable(table);

			String mode = "A";
			if (table.getValid())
			{
				mode = "M";
			}

			// check if RJPF Customer relationships record exists
			EquationStandardTable tableRJPF = new EquationStandardTable("RKPF", "RK10LF", "RKAB:RKAN:RKAS", session);
			tableRJPF.setFieldValue("RKAB", userData.rtvFieldDbValue("PAB"));
			tableRJPF.setFieldValue("RKAN", userData.rtvFieldDbValue("PAN"));
			tableRJPF.setFieldValue("RKAS", userData.rtvFieldDbValue("PAS"));
			session.retrieveEquationTable(tableRJPF);

			// if there is no RJ record then it needs to remove the RL record
			if (!tableRJPF.getValid())
			{
				// ignore when RL record does not exist
				if (!table.getValid())
				{
					return;
				}
				mode = "D";
			}

			EquationStandardTransaction transaction = new EquationStandardTransaction("C59FRRJAN", session);
			transaction.setMode(mode);
			transaction.setFieldValue("GZAB", userData.rtvFieldDbValue("PAB"));
			transaction.setFieldValue("GZAN", userData.rtvFieldDbValue("PAN"));
			transaction.setFieldValue("GZAS", userData.rtvFieldDbValue("PAS"));
			transaction.setFieldValue("GZPCUS", userData.rtvFieldDbValue("CUS"));
			transaction.setFieldValue("GZPCLC", userData.rtvFieldDbValue("CLC"));
			transaction.setFieldValue("GZNAR1", userData.rtvFieldDbValue("NR1"));
			transaction.setFieldValue("GZNAR2", userData.rtvFieldDbValue("NR2"));
			transaction.setFieldValue("GZNAR3", userData.rtvFieldDbValue("NR3"));
			transaction.setFieldValue("GZNAR4", userData.rtvFieldDbValue("NR4"));
			FunctionRuntimeToolbox.setupTransaction(transaction, journalHeader, false, false);
			session.applyEquationTransaction(transaction);

			if (!transaction.getValid())
			{
				getReturnMessages().addMessage(transaction.getErrorList().get(0).getDsepms());
			}
		}
		catch (Exception e)
		{
			getReturnMessages().addError("C59FRR call fails");
		}
	}
	public String postLoad(UserModifyData userModifyData)
	{
		try
		{
			loadFile(userModifyData);

			// special account then turn to enquiry
			String ab = userModifyData.rtvFieldDbValue("PAB");
			String an = userModifyData.rtvFieldDbValue("PAN");
			String as = userModifyData.rtvFieldDbValue("PAS");
			if (ab.equals("0000") && an.equals("500035") && as.equals("101"))
			{
				return "E";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private void loadFile(UserModifyData userModifyData) throws EQException
	{
		// initialise fields
		int index = 0;
		EquationStandardSession session = userModifyData.getUserAccessHandler().getSession();
		String ab = userModifyData.rtvFieldDbValue("PAB");
		String an = userModifyData.rtvFieldDbValue("PAN");
		String as = userModifyData.rtvFieldDbValue("PAS");
		String tableId = "RKPF";
		String indexId = "RK98LF";
		String keys = "RKAB:RKAN:RKAS:RKSQN";

		System.out.println("Inside !");

		// try to read the maximum records from RK
		for (int i = 0; i < MAX_FILE_REC; i++)
		{
			EquationStandardTable table = new EquationStandardTable(tableId, indexId, keys, session);
			table.setFieldValue("RKAB", ab);
			table.setFieldValue("RKAN", an);
			table.setFieldValue("RKAS", as);
			table.setFieldValue("RKSQN", String.valueOf(i));
			session.retrieveEquationTable(table);

			// able to retrieve details?
			if (table.getValid())
			{
				System.out.println("Valid for " + i);
				index++;
				String is = "_" + index;
				userModifyData.chgFieldDbValue("SCUS" + is, table.getFieldValue("RKSCUS"));
				userModifyData.chgFieldDbValue("SCLC" + is, table.getFieldValue("RKSCLC"));
				userModifyData.chgFieldDbValue("SREL" + is, table.getFieldValue("RKREL"));
				userModifyData.chgFieldDbValue("SDUP" + is, table.getFieldValue("RKDUPS"));
				userModifyData.chgFieldDbValue("OCUS" + is, table.getFieldValue("RKSCUS"));
				userModifyData.chgFieldDbValue("OCLC" + is, table.getFieldValue("RKSCLC"));
				userModifyData.chgFieldDbValue("OREL" + is, table.getFieldValue("RKREL"));
				userModifyData.chgFieldDbValue("ODUP" + is, table.getFieldValue("RKDUPS"));
				userModifyData.chgFieldDbValue("wfFCT" + is, "M");
			}
		}
	}

	// public static void main(String[] inputParameters)
	// {
	// try
	// {
	// EqUser user = TestEnvironment.getTestEnvironment().getEqUser();
	// FunctionHandler functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
	// functionHandler.doNewTransaction("JAX", "");
	// FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
	// functionData.chgFieldDbValue("PAB", "0000");
	// functionData.chgFieldDbValue("PAN", "000001");
	// functionData.chgFieldDbValue("PAS", "001");
	// UserModifyData userModifyData = new UserModifyData(functionHandler.getFhd(), functionData);
	//
	// JAX jax = new JAX();
	// jax.loadFile(userModifyData);
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// }
	//
	// }

	/**
	 * Inner class to implement user exit handling for the ASI_ field
	 */
	public class JAR_1__UpdateFieldSet extends AbstractFieldSetAdaptor
	{
		@Override
		public boolean shouldExecuteModule(UserData userdata)
		{
			// no change?
			if (userdata.rtvFieldDbValue("SCUS_1").trim().equals(userdata.rtvFieldDbValue("OCUS_1").trim())
							&& userdata.rtvFieldDbValue("SCLC_1").trim().equals(userdata.rtvFieldDbValue("OCLC_1").trim())
							&& userdata.rtvFieldDbValue("SREL_1").trim().equals(userdata.rtvFieldDbValue("OREL_1").trim())
							&& userdata.rtvFieldDbValue("SDUP_1").trim().equals(userdata.rtvFieldDbValue("ODUP_1").trim()))
			{
				return userdata.rtvFieldDbValue("SINP_1").equals("D");
			}
			return true;
		}
	}

	public class PAB_Primitive_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("wfAB").trim().equals(""))
			{
				return null;
			}
			else
			{
				return userData.rtvFieldDbValue("wfAB");
			}
		}
	}

	public class PAN_Primitive_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("wfAN").trim().equals(""))
			{
				return null;
			}
			else
			{
				return userData.rtvFieldDbValue("wfAN");
			}
		}
	}

	public class PAS_Primitive_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("wfAS").trim().equals(""))
			{
				return null;
			}
			else
			{
				return userData.rtvFieldDbValue("wfAS");
			}
		}
	}

	public class PEAN_Primitive_ValueAdaptor extends AbstractValueAdaptor
	{
		public String getValue(String curValue, UserData userData)
		{
			if (userData.rtvFieldDbValue("wfEAN").trim().equals(""))
			{
				return null;
			}
			else
			{
				return userData.rtvFieldDbValue("wfEAN");
			}
		}
	}

}
