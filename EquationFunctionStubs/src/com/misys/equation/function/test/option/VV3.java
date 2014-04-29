package com.misys.equation.function.test.option;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;

public class VV3 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: VV3.java 4735 2009-09-15 16:58:25Z lima12 $";

	public VV3()
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
		VV3 test = new VV3();
		test.test();
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("REC1", "Record 1", "Description 1");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// add all the fields in this section --------------------------------------- CHANGES

		VV1.addField(session, inputFieldSet, attributeSet, "MSV13R", "MSV13R", "A", 3, "MSV13R", "", false, "N");
		VV1.addField(session, inputFieldSet, attributeSet, "MSV32R", "MSV32R", "A", 50, "MSV32R", "B", false, "N");
		VV1.addField(session, inputFieldSet, attributeSet, "GWV99R", "GWV99R", "A", 127, "GWV99R", "", false, "N");
		VV1.addField(session, inputFieldSet, attributeSet, "R4V01R", "R4V01R", "A", 30, "R4V01R", "", false, "N");
		VV1.addField(session, inputFieldSet, attributeSet, "UTR71R", "UTR71R", "A", 40, "UTR71R", "", false, "N");
		VV1.addField(session, inputFieldSet, attributeSet, "GWV20R", "GWV20R", "A", 25, "GWV20R", "A", false, "N");
		VV1.addField(session, inputFieldSet, attributeSet, "GFV71R", "GFV71R", "A", 25, "GFV71R", "C", false, "N");
		VV1.addField(session, inputFieldSet, attributeSet, "UHV02R", "UHV02R", "A", 25, "UHV02R", "", true, "N");
		VV1.addField(session, inputFieldSet, attributeSet, "NEV01R", "NEV01R", "A", 40, "NEV01R", "C", false, "N");
		VV1.addField(session, inputFieldSet, attributeSet, "GWV61R", "GWV61R", "A", 25, "GWV61R", "", false, "N");
		VV1.addField(session, inputFieldSet, attributeSet, "GWV53R", "GWV53R", "A", 25, "GWV53R", "", false, "N");
		VV1.addField(session, inputFieldSet, attributeSet, "GWV51R", "GWV51R", "A", 112, "GWV51R", "", false, "N");
		VV1.addField(session, inputFieldSet, attributeSet, "XZV02R", "XZV02R", "A", 30, "XZV02R", "", false, "N");
		VV1.addField(session, inputFieldSet, attributeSet, "XFV10R", "XFV10R", "A", 30, "XFV10R", "", false, "N");
		VV1.addField(session, inputFieldSet, attributeSet, "GWV97R", "GWV97R", "A", 30, "GWV97R", "", false, "N");
		VV1.addField(session, inputFieldSet, attributeSet, "GWV40R", "GWV40R", "A", 30, "GWV40R", "", false, "N");
		VV1.addField(session, inputFieldSet, attributeSet, "GWV45R", "GWV45R", "A", 110, "GWV45R", "A", false, "N");
		VV1.addField(session, inputFieldSet, attributeSet, "GWV06R", "GWV06R", "A", 30, "GWV06R", "", false, "N");
		VV1.addField(session, inputFieldSet, attributeSet, "ACGV10R", "ACGV10R", "A", 60, "ACGV10R", "", false, "N");
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