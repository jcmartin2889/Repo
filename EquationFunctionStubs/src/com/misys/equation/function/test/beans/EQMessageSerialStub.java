package com.misys.equation.function.test.beans;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.EqBeanFactory;

public class EQMessageSerialStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQMessageSerialStub.java 4735 2009-09-15 16:58:25Z lima12 $";
	EquationUnit unit;
	EquationStandardSession session;

	public EQMessageSerialStub()
	{
		unit = TestEnvironment.getTestEnvironment().getEquationUnit();
		session = TestEnvironment.getTestEnvironment().getStandardSession();
	}

	public static void main(String[] inputParameters)
	{
		EQMessageSerialStub test = new EQMessageSerialStub();
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

			// print the field values
			EqBeanFactory eqBeanFactory = EqBeanFactory.getEqBeanFactory();
			String xml = eqBeanFactory.serialiseBeanAsXML(eqMessage);
			System.out.println(xml);

			// convert to another function data
			System.err.println("====================");
			EQMessage copy = (EQMessage) eqBeanFactory.deserialiseXMLAsBean(xml, EQMessage.class);
			String xml2 = eqBeanFactory.serialiseBeanAsXML(copy);
			System.out.println(xml2);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
