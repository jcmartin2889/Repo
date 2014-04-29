package com.misys.equation.function.test.beans;

import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.function.beans.FieldData;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandlerData;
import com.misys.equation.function.runtime.FunctionInfo;
import com.misys.equation.function.runtime.FunctionMessage;
import com.misys.equation.function.test.run.FunctionHandlerStubTestCase;
import com.misys.equation.function.tools.XMLToolbox;

public class FunctionDataSerialStub extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionDataSerialStub.java 12212 2011-11-01 15:45:23Z lima12 $";

	public FunctionDataSerialStub()
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
		FunctionDataSerialStub test = new FunctionDataSerialStub();
		test.test();
	}

	private void test()
	{
		// Have a bash...
		try
		{
			FunctionHandlerData fhd = new FunctionHandlerData(null, user, new FunctionInfo("s", "n"));

			Function function = XMLToolbox.getXMLToolbox().getFunction(session, "ALZ", true);
			FunctionData functionData = new FunctionData(function, fhd);
			functionData.chgFieldInputValue("AB", "9132");
			functionData.chgFieldInputValue("AN", "234567");
			functionData.chgFieldInputValue("AS", "001");
			functionData.chgFieldDbValue("PBR", "STB1");
			functionData.chgFieldDbValue("NPE", "1");

			// add an error
			String messageText = "DSM0001";
			EQMessage eqMessage = session.getMessage(messageText);
			FunctionMessage functionMessage = new FunctionMessage(1, 1, "AB", 2, eqMessage, "prefixText", "suffixText");

			FieldData fieldData = functionData.rtvFieldData("AB");
			fieldData.addMessage(functionMessage);

			// print the field values
			EqBeanFactory eqBeanFactory = EqBeanFactory.getEqBeanFactory();
			String xml = eqBeanFactory.serialiseBeanAsXML(functionData);
			System.out.println(xml);

			// convert to another function data
			System.err.println("====================");
			FunctionData copy = (FunctionData) eqBeanFactory.deserialiseXMLAsBean(xml, FunctionData.class);
			String xml2 = eqBeanFactory.serialiseBeanAsXML(copy);
			System.out.println(xml2);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
