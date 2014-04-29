package com.misys.equation.function.test.beans;

import java.util.ArrayList;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.function.runtime.FunctionMessages;

public class FunctionMessagesSerialStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionMessagesSerialStub.java 4735 2009-09-15 16:58:25Z lima12 $";
	EquationUnit unit;
	EquationStandardSession session;

	public FunctionMessagesSerialStub()
	{
		unit = TestEnvironment.getTestEnvironment().getEquationUnit();
		session = TestEnvironment.getTestEnvironment().getStandardSession();
	}

	public static void main(String[] inputParameters)
	{
		FunctionMessagesSerialStub test = new FunctionMessagesSerialStub();
		test.test();
	}

	private void test()
	{
		// Have a bash...
		try
		{
			// message
			String messageText = "DSM0002" + "param1    " + "param2    ";
			EQMessage eqMessage = session.getMessage(messageText);

			// list
			ArrayList<EQMessage> messages = new ArrayList<EQMessage>();
			ArrayList<String> dsepms = new ArrayList<String>();
			messages.add(eqMessage);
			dsepms.add(messageText);

			// field data
			FunctionMessages functionMessages = new FunctionMessages();
			functionMessages.insertMessage(1, 1, "X", 0, eqMessage, "prefixText", "suffixText");

			// print the field values
			EqBeanFactory eqBeanFactory = EqBeanFactory.getEqBeanFactory();
			String xml = eqBeanFactory.serialiseBeanAsXML(functionMessages);
			System.out.println(xml);

			// convert to another function data
			System.err.println("====================");
			FunctionMessages copy = (FunctionMessages) eqBeanFactory.deserialiseXMLAsBean(xml, FunctionMessages.class);
			String xml2 = eqBeanFactory.serialiseBeanAsXML(copy);
			System.out.println(xml2);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
