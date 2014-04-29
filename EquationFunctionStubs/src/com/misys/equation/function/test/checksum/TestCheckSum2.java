package com.misys.equation.function.test.checksum;

import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionInfo;
import com.misys.equation.function.test.run.FunctionHandlerStubTestCase;
import com.misys.equation.function.tools.XMLToolbox;
import com.misys.equation.function.tools.XSDStructureLink;

// Text the context string loading
public class TestCheckSum2 extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TestCheckSum2.java 17236 2013-09-09 17:16:50Z Lima12 $";

	public TestCheckSum2()
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
		TestCheckSum2 test = new TestCheckSum2();
		test.test();
	}

	private void test()
	{
		FunctionHandler functionHandler = null;
		// Have a bash...
		try
		{
			// create the function handler
			FunctionInfo functionInfo = new FunctionInfo("SESSIONID", "");
			functionHandler = new FunctionHandler(user, functionInfo);
			functionHandler.rtvEquationSession();

			// create the function
			functionHandler.doNewTransaction("XX4", "");

			// get the function
			Function function = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunction();

			// get the function data
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
			functionData.chgFieldDbValue("DATE", "1000101");
			functionData.chgFieldDbValue("DATE2", "1011231");
			functionData.chgFieldDbValue("DATE3", "1050615");
			functionData.chgFieldDbValue("CCY1", "GBP");
			functionData.chgFieldDbValue("CCY2", "ITL");
			functionData.chgFieldDbValue("CCY3", "PHP");
			functionData.chgFieldDbValue("CCY4", "JPY");
			functionData.chgFieldDbValue("AMT1", "123456789012345");
			functionData.chgFieldDbValue("AMT2", "111112222233333");
			functionData.chgFieldDbValue("AMT3", "123451234512345");
			functionData.chgFieldDbValue("AMT4", "678906789067890");

			// print the function data
			System.out.println(functionData);
			XSDStructureLink link = XMLToolbox.getXMLToolbox().getXSDStructureLink(session.getUnitId(), function.getId());
			System.out.println("Checksum = " + functionData.rtvChecksum(function, null, true, link));
			System.out.println("Checksum = " + functionData.getChecksum());
			System.out.println("Done");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
