package com.misys.equation.function.test.option;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;

public class PV1 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: PV1.java 8427 2010-07-29 17:33:24Z CHALLIP1 $";

	public PV1()
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
		PV1 test = new PV1();
		test.test();
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Record 1", "Description 1");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// add all the fields in this section --------------------------------------- CHANGES

		VV1.addPromptField(session, inputFieldSet, attributeSet, "GWR76R", "GWR76R", "", 50, "GWR76R", "", false, "N");

		// setWrapper.addDisplayFieldWrapper(getField("CNA", "Country", 2, "C7R05R", ""));
		/*
		 * VV1.addField(session, inputFieldSet, attributeSet, "GWV94R", "GWV94R", "A", 25, "GWV94R", "", false, "N");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "JVR01R", "JVR01R", "A", 3, "JVR01R", "", false, "N");
		 * VV1.addField(session, inputFieldSet, attributeSet, "DVR01R", "DVR01R", "A", 13, "DVR01R", "", false, "N");
		 * VV1.addField(session, inputFieldSet, attributeSet, "GWV28R", "GWV28R", "A", 15, "GWV28R", "", false, "N");
		 * VV1.addField(session, inputFieldSet, attributeSet, "GWV28R-H", "GWV28R-H", "A", 15, "GWV28R", "H", false, "N");
		 * VV1.addField(session, inputFieldSet, attributeSet, "JYR04R", "JYR04R", "A", 25, "JYR04R", "", false, "N");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GBR39R", "GBR39R", "A", 15, "GBR39R", "", false, "N");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GBR39R-V", "GBR39R-V", "A", 15, "GBR39R", "V", false, "N");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "JTR01R", "JTR01R", "A", 35, "JTR01R", "", false, "N");
		 * 
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "AAAR10R", "AAAR10R", "A", 25, "AAAR10R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "AAJR10R", "AAJR10R", "A", 25, "AAJR10R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GCR36R", "GCR36R", "A", 25, "GCR36R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GKR40R", "GKR40R", "A", 25, "GKR40R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GLR41R", "GLR41R", "A", 25, "GLR41R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GMR42R", "GMR42R", "A", 25, "GMR42R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GNR43R", "GNR43R", "A", 25, "GNR43R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GOR44R", "GOR44R", "A", 25, "GOR44R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GPR06R", "GPR06R", "A", 25, "GPR06R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GQR07R", "GQR07R", "A", 25, "GQR07R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GRR08R", "GRR08R", "A", 25, "GRR08R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GTR09R", "GTR09R", "A", 25, "GTR09R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GUR10R", "GUR10R", "A", 25, "GUR10R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GFR70R", "GFR70R", "A", 9, "GFR70R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GWR79R", "GWR79R", "A", 25, "GWR79R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "HSR10R", "HSR10R", "A", 25, "HSR10R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "HWR20R", "HWR20R", "A", 25, "HWR20R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "I2R01R", "I2R01R", "A", 25, "I2R01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "I4R01R", "I4R01R", "A", 25, "I4R01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "I5R01R", "I5R01R", "A", 25, "I5R01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "I6R01R", "I6R01R", "A", 25, "I6R01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "HBR30R", "HBR30R", "A", 25, "HBR30R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "P3R01R", "P3R01R", "A", 25, "P3R01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "IYR01R", "IYR01R", "A", 25, "IYR01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "J1R01R", "J1R01R", "A", 25, "J1R01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "J2R20R", "J2R20R", "A", 25, "J2R20R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "JQR02R", "JQR02R", "A", 25, "JQR02R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "JSR10R", "JSR10R", "A", 25, "JSR10R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "JTR02R", "JTR02R", "A", 25, "JTR02R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "LCR01R", "LCR01R", "A", 25, "LCR01R", "", false, "");
		 * 
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "N2R01R", "N2R01R", "A", 25, "N2R01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "N4R01R", "N4R01R", "A", 25, "N4R01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "O5R01R", "O5R01R", "A", 25, "O5R01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "O9R01R", "O9R01R", "A", 25, "O9R01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "OBR37R", "OBR37R", "A", 25, "OBR37R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "OCR15R", "OCR15R", "A", 25, "OCR15R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "OSR38R", "OSR38R", "A", 25, "OSR38R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "P6R01R", "P6R01R", "A", 25, "P6R01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "TPR10R", "TPR10R", "A", 25, "TPR10R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "U8R01R", "U8R01R", "A", 25, "U8R01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "C8R01R", "C8R01R", "A", 25, "C8R01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GWR76R", "GWR76R", "A", 25, "GWR76R", "", false, "");
		 * 
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "A2R10R", "A2R10R", "A", 25, "A2R10R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "AANR10R", "AANR10R", "A", 25, "AANR10R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "AAWR10R", "AAWR10R", "A", 25, "AAWR10R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "ALCR10R", "ALCR10R", "A", 25, "ALCR10R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "BBAR10R", "BBAR10R", "A", 25, "BBAR10R", "", false, "");
		 * 
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "C3R12R", "C3R12R", "A", 25, "C3R12R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "C6R53R", "C6R53R", "A", 25, "C6R53R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "C7R05R", "C7R05R", "A", 25, "C7R05R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "CAXR10R", "CAXR10R", "A", 25, "CAXR10R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "CAR73R", "CAR73R", "A", 25, "CAR73R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "CBR21R", "CBR21R", "A", 25, "CBR21R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "CTR56R", "CTR56R", "A", 25, "CTR56R", "", false, "");
		 * 
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "D3R27R", "D3R27R", "A", 25, "D3R27R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "D8R01R", "D8R01R", "A", 25, "D8R01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "FSR01R", "FSR01R", "A", 25, "FSR01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GAR49R", "GAR49R", "A", 25, "GAR49R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GBR35R", "GBR35R", "A", 25, "GBR35R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "U6R01R", "U6R01R", "A", 25, "U6R01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "WFR01R", "WFR01R", "A", 25, "WFR01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "V2R10R", "V2R10R", "A", 25, "V2R10R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "WIR01R", "WIR01R", "A", 25, "WIR01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "WJR10R", "WJR10R", "A", 25, "WJR10R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "WKR10R", "WKR10R", "A", 25, "WKR10R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "ZJR01R", "ZJR01R", "A", 25, "ZJR01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "OSR34R", "OSR34R", "A", 25, "OSR34R", "", false, "");
		 * 
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "H7R10R", "H7R10R", "A", 25, "H7R10R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "Z1R01R", "Z1R01R", "A", 25, "Z1R01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "D1R02R", "D1R02R", "A", 25, "D1R02R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "N7R01R", "N7R01R", "A", 25, "N7R01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "ACFR10R", "ACFR10R", "A", 25, "ACFR10R", "", false, "");
		 * 
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "AAIR10R", "AAIR10R", "A", 25, "AAIR10R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "Q1R01R", "Q1R01R", "A", 25, "Q1R01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "R6R01R", "R6R01R", "A", 25, "R6R01R", "", false, ""); //
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "R4R01R", "R4R01R", "A", 25, "R4R01R", "", false, "");
		 * 
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "Q6R10R", "Q6R10R", "A", 25, "Q6R10R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "SYR01R", "SYR01R", "A", 25, "SYR01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "MJR10R", "MJR10R", "A", 25, "MJR10R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "EXER20R", "EXER20R", "A", 25, "EXER20R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GWV20R", "GWV20R", "A", 25, "GWV20R", "", false, ""); //
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "NEV01R", "NEV01R", "A", 25, "NEV01R", "", false, "");
		 * VV1.addPromptField(session, inputFieldSet, attributeSet, "GWV29R", "GWV29R", "A", 25, "GWV29R", "", false, "");
		 */

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
