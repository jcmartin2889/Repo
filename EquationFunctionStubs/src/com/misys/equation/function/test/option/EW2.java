package com.misys.equation.function.test.option;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;

public class EW2 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EW2.java 6793 2010-03-31 12:10:20Z deroset $";

	public EW2()
	{
		try
		{
			setUp();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] inputParameters)
	{
		EW2 test = new EW2();
		test.test();
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Record 1", "Description 1");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// add all the fields in this section --------------------------------------- CHANGES

		// VV1.addPromptField(session, inputFieldSet, attributeSet, "CLOSE", "CLOSE", "A", 50, "CLOSE", "", false, "");

		VV1.addPromptField(session, inputFieldSet, attributeSet, "CANR20R", "CANR20R", "A", 100, "CANR20R", "", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "FWR22R", "FWR22R", "A", 100, "FWR22R", "", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "FWR23R", "FWR23R", "A", 100, "FWR23R", "", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "FWR24R", "FWR24R", "A", 100, "FWR24R", "", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "FWR10R", "FWR10R", "A", 50, "FWR10R", "", false, "");

		return fieldSetWrapper;
	}

	private void test()
	{
		// Have a bash...
		try
		{
			FunctionGenerator fg = new FunctionGenerator(this.getClass().getSimpleName(), "Test Stub",
							"This is the description of Add/Maintain Hold Code", "com.misys.equation.screens",
							"com.misys.equation.screens.layout");
			addRecord1(fg);

			// Write to DB
			FunctionToolboxStub.writeToDB(unit, fg, true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
