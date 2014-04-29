package com.misys.equation.function.test.option;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;

public class VV5 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: VV5.java 4735 2009-09-15 16:58:25Z lima12 $";

	public VV5()
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
		VV5 test = new VV5();
		test.test();
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("REC1", "Record 1", "Description 1");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// add all the fields in this section --------------------------------------- CHANGES

		VV1.addPromptField(session, inputFieldSet, attributeSet, "FBR01R", "FBR01R", "A", 50, "FBR01R", "", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "GWV82R", "GWV82R", "A", 50, "GWV82R", "A", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "UHV02R", "UHV02R", "A", 50, "UHV02R", "", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "XZV02R", "XZV02R", "A", 50, "XZV02R", "", false, "");

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
