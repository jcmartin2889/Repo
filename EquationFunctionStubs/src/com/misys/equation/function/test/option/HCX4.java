package com.misys.equation.function.test.option;

import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;

public class HCX4 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: HCX4.java 7138 2010-05-04 14:59:54Z lima12 $";

	public HCX4()
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
		HCX4 test = new HCX4();
		test.test();
	}

	public void test()
	{
		// Have a bash...
		try
		{
			printStartStatus(this.getClass().getSimpleName());
			HCX2 hcx2 = new HCX2();
			FunctionGenerator fg = hcx2.test("HC4", "Add Hold Code", IEquationStandardObject.FCT_ADD, false);
			fg.getFunction().setAllowedAdd(true);
			fg.getFunction().setAllowedMaint(false);
			fg.getFunction().setAllowedDel(false);

			// Write to DB
			FunctionToolboxStub.writeToDB(unit, fg.getFunctionBean(), fg.getLayoutBean(), null, null);

			// print
			printCompleteStatus(fg, this.getClass().getSimpleName(), printXML);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
