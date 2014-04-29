package com.misys.equation.function.test.option;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;

public class VV4 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: VV4.java 4735 2009-09-15 16:58:25Z lima12 $";

	public VV4()
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
		VV4 test = new VV4();
		test.test();
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("REC1", "Record 1", "Description 1");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// add all the fields in this section --------------------------------------- CHANGES

		VV1.addPromptField(session, inputFieldSet, attributeSet, "GWV07R", "GWV07R", "A", 25, "GWV07R", "", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "GWV13R", "GWV13R", "A", 25, "GWV13R", "F", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "GWV20R", "GWV20R", "A", 25, "GWV20R", "", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "GWV29R", "GWV29R", "A", 25, "GWV29R", "", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "GWV80R", "GWV80R", "A", 25, "GWV80R", "", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "NEV01R", "NEV01R", "A", 25, "NEV01R", "", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "GWV94R", "GWV94R", "A", 25, "GWV94R", "", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "GWV59R", "GWV59R", "A", 25, "GWV59R", "", false, "");

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
