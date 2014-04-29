package com.misys.equation.userexits;

import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.function.adaptor.AbstractFieldAdaptor;
import com.misys.equation.function.adaptor.AbstractFieldSetAdaptor;
import com.misys.equation.function.adaptor.AbstractFunctionAdaptor;
import com.misys.equation.function.adaptor.AbstractValueAdaptor;
import com.misys.equation.function.runtime.UserData;

public class UE6 extends AbstractFunctionAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UE6.java 4741 2009-09-16 16:33:23Z esther.williams $";
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(UE6.class);

	/**
	 * Inner class to implement user exit handling for the AAA_GZHRC field
	 */
	public class AAA_GZHRC_LoadValueAdaptor extends AbstractValueAdaptor
	{
		// Load Java processing for an API (key) field
		@Override
		public String getValue(String curValue, UserData userdata)
		{
			return "TS6";
		}
	}

	/**
	 * Inner class to implement user exit handling for the WORK_LOAD1 field
	 */
	public class WORK_LOAD1_LoadValueAdaptor extends AbstractValueAdaptor
	{
		// Load assignment of a WorkField
		@Override
		public String getValue(String curValue, UserData userdata)
		{
			return userdata.rtvFieldDbValue("WORK_INIT");
		}
	}

	/**
	 * Inner class to implement user exit handling for the AAA_HRC field
	 */
	public class AAA_HRC_JVR01R_$JVHRC_ValueAdaptor extends AbstractValueAdaptor
	{

		@Override
		public String getValue(String curValue, UserData userdata)
		{
			return "TS6";
		}
	}

	/**
	 * Inner class to implement user exit handling for the VALID1_Primitive field
	 */
	public class VALID1_Primitive_ValueAdaptor extends AbstractValueAdaptor
	{

		@Override
		public String getValue(String curValue, UserData userdata)
		{
			return "VALIDATE (PRIMITIVE) JAVA";
		}
	}

	/**
	 * Inner class to implement user exit handling for the VALID1_Output field
	 */
	public class VALID1_Output_ValueAdaptor extends AbstractValueAdaptor
	{

		@Override
		public String getValue(String curValue, UserData userdata)
		{
			return "VALIDATE (OUTPUT) JAVA";
		}
	}

	/**
	 * Inner class to implement user exit handling for the VALID1_FieldAdaptor field
	 */
	public class VALID1_FieldAdaptor extends AbstractFieldAdaptor
	{

		@Override
		public boolean isValid(UserData data)
		{
			return true;
		}
	}

	/**
	 * Inner class to implement user exit handling for the MAND_TST field
	 */
	public class MAND_TST_FieldAdaptor extends AbstractFieldAdaptor
	{

		@Override
		public boolean isMandatory(UserData data)
		{
			LOG.error("Assigning the MAND field mandatoryness");
			return data.rtvFieldDbValue("MAND_CTL").equals("Y");
		}
	}

	/**
	 * Inner class to implement user exit handling for the LOAD1_LoadValueAdaptor field
	 */
	public class LOAD1_LoadValueAdaptor extends AbstractValueAdaptor
	{

		// Load assignment of a InputField
		@Override
		public String getValue(String curValue, UserData userdata)
		{
			return "Load Value";
		}
	}

	/**
	 * Inner class to implement user exit handling for the VALID_TST field
	 */
	public class VALID_TST_FieldAdaptor extends AbstractFieldAdaptor
	{

		@Override
		public boolean isValid(UserData data)
		{
			if (data.rtvFieldDbValue("VALID_TST").equals("FAIL"))
			{
				getReturnMessages().addMessageParam("KSM0177", "PARM000001", "PARM000002", "PARM000003");
				return false;
			}
			else
			{
				return true;
			}
		}
	}

	/**
	 * Inner class to implement user exit handling for the PRIMARY_ field
	 */
	public class PRIMARY__BBB__LoadFieldSet extends AbstractFieldSetAdaptor
	{

		@Override
		public boolean shouldExecuteModule(UserData userdata)
		{
			return userdata.rtvFieldDbValue("LOAD_COND").equals("Y");
		}
	}

	/**
	 * Inner class to implement user exit handling for the BBB_ field
	 */
	public class BBB__UpdateFieldSet extends AbstractFieldSetAdaptor
	{

		@Override
		public boolean shouldExecuteModule(UserData userdata)
		{
			return userdata.rtvFieldDbValue("UPD_COND").equals("Y");
		}
	}

	/**
	 * Inner class to implement user exit handling for the BBB_HRC field
	 */
	public class BBB_HRC__JVR01R__PVFieldSet extends AbstractFieldSetAdaptor
	{

		@Override
		public boolean shouldExecuteModule(UserData userdata)
		{
			return userdata.rtvFieldDbValue("PV_COND").equals("Y");
		}
	}

	/**
	 * Inner class to implement user exit handling for the WORK_VALID field
	 */
	public class WORK_VALID_ValueAdaptor extends AbstractValueAdaptor
	{

		@Override
		public String getValue(String curValue, UserData userdata)
		{
			return "VALIDATE (WORKFIELD) JAVA";
		}
	}
}
