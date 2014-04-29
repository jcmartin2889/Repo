package com.misys.equation.function.test.option;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;

public class PV5 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: PV5.java 7610 2010-06-01 17:10:41Z MACDONP1 $";

	public PV5()
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
		PV5 test = new PV5();
		test.test();
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Record 1", "Description 1");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// add all the fields in this section --------------------------------------- CHANGES

		VV1.addPromptField(session, inputFieldSet, attributeSet, "OMR01R", "OMR01R", "A", 88, "OMR01R", " ", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "OMR01R A", "OMR01R A", "A", 88, "OMR01R", "A", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "OMR01R B", "OMR01R B", "A", 88, "OMR01R", "B", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "OMR01R C", "OMR01R C", "A", 88, "OMR01R", "C", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "OMR01R D", "OMR01R D", "A", 88, "OMR01R", "D", false, "");

		// VV1.addPromptField(session, inputFieldSet, attributeSet, "OMR02R", "OMR02R", "A", 88, "OMR02R", " ", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "OMR02R A", "OMR02R A", "A", 88, "OMR02R", "A", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "OMR02R B", "OMR02R B", "A", 88, "OMR02R", "B", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "OMR02R C", "OMR02R C", "A", 88, "OMR02R", "C", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "OMR02R D", "OMR02R D", "A", 88, "OMR02R", "D", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "OMR02R E", "OMR02R E", "A", 88, "OMR02R", "E", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "OMR02R F", "OMR02R F", "A", 88, "OMR02R", "F", false, "");

		// VV1.addPromptField(session, inputFieldSet, attributeSet, "DTR01R", "DTR01R", "A", 100, "DTR01R", " ", false, "");

		// VV1.addPromptField(session, inputFieldSet, attributeSet, "HCR10R", "HCR10R", "A", 100, "HCR10R", " ", false, "");

		// VV1.addPromptField(session, inputFieldSet, attributeSet, "AA5R10R", "AA5R10R", "A", 100, "AA5R10R", " ", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "D4R47R", "D4R47R", "A", 100, "D4R47R", " ", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "Q8R10R", "Q8R10R", "A", 123, "Q8R10R", " ", false, "");

		VV1.addPromptField(session, inputFieldSet, attributeSet, "GFR70R", "GFR70R", "A", 88, "GFR70R", " ", false, "");

		// Validates
		VV1.addField(session, inputFieldSet, attributeSet, "GWV30R", "GWV30R", "A", 21, "GWV30R", " ", false, "N");
		// VV1.addField(session, inputFieldSet, attributeSet, "JYR04R", "JYR04R", "A", 25, "JYR04R", " ", false, "N");

		// VV1.addField(session, inputFieldSet, attributeSet, "DUR01R R", "DUR01R R", "A", 88, "DUR01R", "R", false, "N");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "DUR01R", "DUR01R", "A", 88, "DUR01R", " ", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "DUR01R R", "DUR01R R", "A", 88, "DUR01R", "R", false, "");

		VV1.addPromptField(session, inputFieldSet, attributeSet, "TVR20R", "TVR20R", "A", 88, "TVR20R", " ", false, "N");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "TVR20R T", "TVR20R T", "A", 88, "TVR20R", "T", false, "N");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "HGR01R", "HGR01R", "A", 88, "HGR01R", " ", false, "N");

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
