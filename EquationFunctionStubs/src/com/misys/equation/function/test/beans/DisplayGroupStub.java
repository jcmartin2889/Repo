package com.misys.equation.function.test.beans;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.function.beans.DisplayGroup;

public class DisplayGroupStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DisplayGroupStub.java 10215 2011-01-06 12:06:08Z MACDONP1 $";
	EquationUnit unit;
	EquationStandardSession session;

	public DisplayGroupStub()
	{
		unit = TestEnvironment.getTestEnvironment().getEquationUnit();
		session = TestEnvironment.getTestEnvironment().getStandardSession();
	}

	public static void main(String[] inputParameters)
	{
		DisplayGroupStub test = new DisplayGroupStub();
		test.test();
	}

	private void test()
	{
		// Have a bash...
		try
		{
			// message

			DisplayGroup displayGroup = new DisplayGroup();
			displayGroup.setUnitMnemonic("GP5");
			displayGroup.setDescription("Test");

			// print the field values
			EqBeanFactory eqBeanFactory = EqBeanFactory.getEqBeanFactory();
			String xml = eqBeanFactory.serialiseBeanAsXML(displayGroup);
			System.out.println(xml);
			// convert to another function data
			System.err.println("====================");
			DisplayGroup copy = (DisplayGroup) eqBeanFactory.deserialiseXMLAsBean(xml, DisplayGroup.class);
			String xml2 = eqBeanFactory.serialiseBeanAsXML(copy);
			System.out.println(xml2);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
