package com.misys.equation.function.test.bf;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.function.adaptor.FunctionAdaptor;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionBankFusion;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.test.run.FunctionToolboxStub;

// Via Save and Restore
public class TestComplexTypeLoading
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TestComplexTypeLoading.java 17191 2013-09-03 11:50:52Z Lima12 $";

	EquationStandardSession session;
	EquationUnit unit;
	EquationUser user;

	public TestComplexTypeLoading()
	{
		try
		{
			unit = TestEnvironment.getTestEnvironment().getEquationUnit();
			user = new EquationUser(unit, "EQUIADMIN", "EQUIADMIN", null);
			session = user.getSession();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] inputParameters)
	{
		TestComplexTypeLoading test = new TestComplexTypeLoading();
		test.test();
	}

	private void test()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			// create the function handler
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			functionHandler.getFhd().getFunctionInfo().setWorkStationId("STB1");

			// create the function
			functionHandler.doNewTransaction("ALZ", "");

			// Get a handler to the function data
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			// This is how you setup the field given its input value (as if entered through the desktop)
			functionData.chgFieldInputValue("AB", "9132");
			functionData.chgFieldInputValue("AN", "234567");
			functionData.chgFieldInputValue("AS", "001");
			functionData.chgFieldInputValue("EAN", "1840KBWD870900840");
			functionData.chgFieldInputValue("TCD", "510");
			functionData.chgFieldInputValue("AMT", "15T");
			functionData.chgFieldInputValue("CCY", "GBP");
			functionData.chgFieldInputValue("BRNM", "LOND");
			functionData.chgFieldInputValue("DRF", "via STUB1");
			functionData.chgFieldInputValue("NR1", "test NR1");
			functionData.chgFieldInputValue("NR2", "test NR2");
			functionData.chgFieldInputValue("NR3", "Atest NR3");
			functionData.chgFieldInputValue("NR4", "test NR4");
			functionData.chgFieldInputValue("SRC", "SR");
			functionData.chgFieldInputValue("UC1", "UC1");
			functionData.chgFieldInputValue("UC2", "UC2");

			functionData.chgFieldInputValue("DIGIT", "15");
			functionData.chgFieldInputValue("DECI", "0");
			functionData.chgFieldInputValue("XValid2", "110");
			functionData.chgFieldInputValue("XValid3", "170");
			functionData.chgFieldInputValue("XReg", "REGULAR STUB 1");
			functionData.chgFieldInputValue("XMask", "02033205082");
			functionData.chgFieldInputValue("FLD3B", "0991231CITY@@CH 0000001");
			functionData.chgFieldInputValue("FLD3C", "BBB9132120005100  0991231KBSL@@MM 0000080");

			// This is how you setup the field given its database value
			functionData.chgFieldDbValue("PBR", "STB1");
			functionData.chgFieldDbValue("NPE", "1");
			functionData.chgFieldDbValue("AC", "9132234567001");
			functionData.chgFieldDbValue("FRQ", "L01");
			functionData.chgFieldDbValue("YNO", "Y");
			functionData.chgFieldDbValue("YNO2", "N");
			functionData.chgFieldDbValue("RAT1", "123.45");
			functionData.chgFieldDbValue("RAT2", "12345678.9012345");
			functionData.chgFieldDbValue("VFR", "1000501");

			// get the function
			Function function = functionHandler.getFhd().getScreenSetHandler().rtvScreenSetMain().getFunction();
			FunctionAdaptor functionAdaptor = functionHandler.getFhd().getFunctionAdaptorHandler().getFunctionAdaptor(session,
							functionHandler.getFhd().getOptionId());

			// get the complex data type
			FunctionBankFusion functionBankFusion = new FunctionBankFusion();
			Object obj = functionBankFusion.getBankFusionDataType(session, functionAdaptor, function, functionData, true, null);

			// get the function data
			FunctionData data = new FunctionData(function, null);
			functionBankFusion.loadToFunctionData(function, data, obj, true, null);

			System.out.println("Done");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
