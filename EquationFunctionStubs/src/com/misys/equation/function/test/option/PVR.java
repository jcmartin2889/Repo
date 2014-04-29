package com.misys.equation.function.test.option;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;

public class PVR extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: PVR.java 4735 2009-09-15 16:58:25Z lima12 $";

	public PVR()
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
		PVR test = new PVR();
		test.test();
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("REC1", "Record 1", "Description 1");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// add all the fields in this section --------------------------------------- CHANGES

		VV1.addPromptField(session, inputFieldSet, attributeSet, "V2R10R", "V2R10R", "A", 50, "V2R10R", "", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "GIR33R", "GIR33R", "A", 72, "GIR33R", " ", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "GIR33RA", "GIR33RA", "A", 72, "GIR33R", "A", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "GIR33RB", "GIR33RB", "A", 72, "GIR33R", "B", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "GIR33R&", "GIR33R&", "A", 72, "GIR33R", "&", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "HAR10R", "HAR10R", "A", 50, "HAR10R", " ", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "HAR10RB", "HAR10RB", "A", 50, "HAR10R", "B", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "UTU37R", "UTU37R", "A", 50, "UTU37R", " ", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "SAR10R", "SAR10R", "A", 50, "SAR10R", " ", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "SAR10RA", "SAR10RA", "A", 50, "SAR10R", "A", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "SAR10RB", "SAR10RB", "A", 50, "SAR10R", "B", false, "");

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