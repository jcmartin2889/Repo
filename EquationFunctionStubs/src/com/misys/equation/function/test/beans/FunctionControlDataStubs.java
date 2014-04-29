package com.misys.equation.function.test.beans;

import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.function.beans.FunctionControlData;
import com.misys.equation.function.beans.LoadFieldSetStatus;

// Text the context string loading
public class FunctionControlDataStubs
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionControlDataStubs.java 4735 2009-09-15 16:58:25Z lima12 $";

	public FunctionControlDataStubs()
	{
		try
		{
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] inputParameters)
	{
		FunctionControlDataStubs test = new FunctionControlDataStubs();
		test.test();
	}

	private void test()
	{
		// Have a bash...
		try
		{
			FunctionControlData functionControlData = new FunctionControlData();
			functionControlData.setData(FunctionControlData.ORG_USER, "ORIGUSER");
			functionControlData.setData(FunctionControlData.CREATED_BY, "created by");
			functionControlData.setData(FunctionControlData.OPTION, "option");
			functionControlData.addLoadFieldSetStatuses("fieldSet", new LoadFieldSetStatus());
			functionControlData.addCurrentDisplay("aa", new Integer(1));

			System.out.println("data1=" + functionControlData);

			// serialise
			EqBeanFactory eqBeanFactory = EqBeanFactory.getEqBeanFactory();
			String xml = eqBeanFactory.serialiseBeanAsXML(functionControlData);

			// deserialise
			// System.out.println(xml);
			FunctionControlData data = (FunctionControlData) eqBeanFactory.deserialiseXMLAsBean(xml, FunctionControlData.class);
			System.out.println("");
			System.out.println("data2=" + data);

			System.out.println("Done");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
