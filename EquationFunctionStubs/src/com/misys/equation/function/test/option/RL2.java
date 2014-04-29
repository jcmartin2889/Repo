package com.misys.equation.function.test.option;

import java.io.File;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;

public class RL2 extends RLX
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RL2.java 8739 2010-08-19 10:59:04Z lima12 $";
	public RL2()
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
		RL2 test = new RL2();
		test.test();
	}

	@Override
	public void generate(FunctionGenerator fg) throws EQException
	{
		super.generate(fg);

		APIFieldSet apiFieldSet;

		apiFieldSet = fg.getFunction().addReservedFieldSet(Function.CRM_FIELDSET, "Description of CRM");
		apiFieldSet.getAPIField("HHAMC").setUpdateAssignment(Field.ASSIGNMENT_CODE);
		apiFieldSet.getAPIField("HHFCT").setUpdateAssignment(Field.ASSIGNMENT_CODE);
		fg.addUpdateMapping("PRIMARY", "BRNM", Function.CRM_FIELDSET, "HHBRN");
		fg.addUpdateMapping("PRIMARY", "LNP", Function.CRM_FIELDSET, "HHDLP");
		fg.addUpdateMapping("PRIMARY", "LNR", Function.CRM_FIELDSET, "HHDLR");
		// fg.addUpdateMapping("PRIMARY", "AB", Function.CRM_FIELDSET, "HHAB");
		// fg.addUpdateMapping("PRIMARY", "AN", Function.CRM_FIELDSET, "HHAN");
		// fg.addUpdateMapping("PRIMARY", "AS", Function.CRM_FIELDSET, "HHAS");
		fg.addUpdateMapping("PRIMARY", "CCY", Function.CRM_FIELDSET, "HHCY1");
		fg.addUpdateMapping("PRIMARY", "CUS", Function.CRM_FIELDSET, "HHCUS");
		fg.addUpdateMapping("PRIMARY", "CLC", Function.CRM_FIELDSET, "HHCLC");
		fg.addUpdateMapping("PRIMARY", "SDT", Function.CRM_FIELDSET, "HHSDT");

		apiFieldSet = fg.getFunction().addReservedFieldSet(Function.EFC_FIELDSET, "Description of EFC");
		apiFieldSet.getAPIField("ENEW").setUpdateAssignment(Field.ASSIGNMENT_CODE);
		fg.addUpdateMapping("PRIMARY", "BRNM", Function.EFC_FIELDSET, "EBRNM"); // deal details
		fg.addUpdateMapping("PRIMARY", "LNP", Function.EFC_FIELDSET, "EDLP");
		fg.addUpdateMapping("PRIMARY", "LNR", Function.EFC_FIELDSET, "EDLR");
		fg.addUpdateMapping("PRIMARY", "DLA", Function.EFC_FIELDSET, "EDAMT");
		fg.addUpdateMapping("PRIMARY", "CCY", Function.EFC_FIELDSET, "EDCCY");
		fg.addUpdateMapping("PRIMARY", "SDT", Function.EFC_FIELDSET, "ETDTE");

		apiFieldSet = fg.getFunction().addReservedFieldSet(Function.GY_FIELDSET, "Description of GY");
		apiFieldSet.getAPIField("GYWHO").setUpdateAssignment(Field.ASSIGNMENT_CODE);
		apiFieldSet.getAPIField("GYSHN").setUpdateAssignment(Field.ASSIGNMENT_CODE);
		apiFieldSet.getAPIField("GYJREF").setUpdateAssignment(Field.ASSIGNMENT_CODE);
		apiFieldSet.getAPIField("GYIREF").setUpdateAssignment(Field.ASSIGNMENT_CODE);
		apiFieldSet.getAPIField("GYAPP").setUpdateAssignment(Field.ASSIGNMENT_CODE);
	}

	@Override
	public void test()
	{
		// Have a bash...
		try
		{
			printStartStatus(this.getClass().getSimpleName());
			FunctionGenerator fg = new FunctionGenerator("RL2", "Add Retail Loan + CP/CRM/EFC",
							"Description of Add Retail Loan with Clean Payment", "com.misys.equation.screens",
							"com.misys.equation.screens.layout");
			generate(fg);

			fg.getFunction().setApplyDuringExtInput(true);
			fg.getFunction().setApplyDuringRecovery(true);

			// Write to DB
			File serviceClass = new File(workSpace + "com\\misys\\equation\\screens\\RL2.class");
			FunctionToolboxStub.writeToDB(unit, fg.getFunctionBean(), fg.getLayoutBean(), serviceClass, null);

			// print
			printCompleteStatus(fg, this.getClass().getSimpleName(), printXML);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
