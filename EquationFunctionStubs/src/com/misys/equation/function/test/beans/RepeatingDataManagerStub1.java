package com.misys.equation.function.test.beans;

import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.RepeatingDataManager;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionInfo;
import com.misys.equation.function.test.run.FunctionHandlerStubTestCase;

// Text the context string loading
public class RepeatingDataManagerStub1 extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RepeatingDataManagerStub1.java 5406 2009-11-14 12:02:56Z lima12 $";

	public RepeatingDataManagerStub1()
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
		RepeatingDataManagerStub1 test = new RepeatingDataManagerStub1();
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
			functionHandler.doNewTransaction("ZY1", "");
			functionHandler.applyRetrieveTransaction();

			// get the function
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
			RepeatingDataManager dataManager = functionData.getRepeatingDataManagers().get(0);
			System.out.println("CONTENT");
			System.out.println(dataManager);
			System.out.println("=====");

			// serialise
			EqBeanFactory eqBeanFactory = EqBeanFactory.getEqBeanFactory();
			String xml = eqBeanFactory.serialiseBeanAsXML(dataManager);

			// deserialise
			RepeatingDataManager copyManager = (RepeatingDataManager) eqBeanFactory.deserialiseXMLAsBean(xml,
							RepeatingDataManager.class);
			System.out.println("COPY");
			System.out.println(copyManager);
			System.out.println("=====");

			System.out.println("Done");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
