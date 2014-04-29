package com.misys.equation.function.test.option;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;

public class ELW extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ELW.java 6793 2010-03-31 12:10:20Z deroset $";

	public ELW()
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
		ELW test = new ELW();
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

		VV1.addPromptField(session, inputFieldSet, attributeSet, "LTR95R", "LTR95R", "A", 50, "LTR95R", "", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "GWR76R", "GWR76R", "A", 50, "GWR76R", "", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "AAHR10R", "AAHR10R", "A", 50, "AAHR10R", "", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "CANR20R", "CANR20R", "A", 50, "CANR20R", "", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "FWR10R", "FWR10R", "A", 50, "FWR10R", "", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "JAR31R", "JAR31R", "A", 50, "JAR31R", "", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "NER40R", "NER40R", "A", 50, "NER40R", "", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "U8R01R", "U8R01R", "A", 50, "U8R01R", "", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "OMR03R", "OMR03R", "A", 50, "OMR03R", "", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "TPR10R", "TPR10R", "A", 100, "TPR10R", "", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "TPR10R_U", "TPR10R", "A", 100, "TPR10R", "U", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "TYR01R", "TYR01R", "A", 50, "TYR01R", "", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "TYR10R", "TYR10R", "A", 50, "TYR10R", "", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "TYR10R_A", "TYR10R", "A", 50, "TYR10R", "A", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "OMR03R", "OMR03R", "A", 128, "OMR03R", "", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "RJR20R", "RJR20R", "A", 90, "RJR20R", "", false, "");
		VV1.addPromptField(session, inputFieldSet, attributeSet, "UTU37R", "UTU37R", "A", 50, "UTU37R", "", false, "");
		// VV1.addPromptField(session,
		// inputFieldSet,
		// attributeSet,
		// "U8R01R",
		// "U8R01R",
		// "A", 50,
		// "U8R01R",
		// "",
		// false,
		// "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "UTU37R", "UTU37R", "A", 50, "UTU37R", "", false, "");
		// VV1.addPromptField(session, inputFieldSet, attributeSet, "XFV10R", "XFV10R", "A", 50, "XFV10R", "", false, "");

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
