package com.misys.equation.function.test.beans;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.function.beans.LoadFieldSetStatus;

// Text the context string loading
public class LoadFieldSetStatusStubs
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LoadFieldSetStatusStubs.java 16593 2013-06-24 15:32:19Z perkinj1 $";
	EquationStandardSession session;
	EquationUnit unit;
	EquationUser user;

	public LoadFieldSetStatusStubs()
	{
		try
		{
			session = TestEnvironment.getTestEnvironment().getStandardSession();
			unit = TestEnvironment.getTestEnvironment().getEquationUnit();
			user = new EquationUser(unit, "EQUIADMIN", "EQUIADMIN", null);
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] inputParameters)
	{
		LoadFieldSetStatusStubs test = new LoadFieldSetStatusStubs();
		test.test();
	}

	private void test()
	{
		// Have a bash...
		try
		{
			LoadFieldSetStatus data = new LoadFieldSetStatus();
			data.setId("id");
			data.setKeyExist(true);
			data.setLevel(5);
			data.setParent("parent");
			System.out.println("data1=" + data.toString());

			// serialise
			EqBeanFactory eqBeanFactory = EqBeanFactory.getEqBeanFactory();
			String xml = eqBeanFactory.serialiseBeanAsXML(data);

			// deserialise
			System.out.println(xml);
			LoadFieldSetStatus data2 = (LoadFieldSetStatus) eqBeanFactory.deserialiseXMLAsBean(xml, LoadFieldSetStatus.class);
			System.out.println("data2=" + data2.toString());

			System.out.println("Done");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
