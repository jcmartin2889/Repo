package com.misys.equation.function.test.beans;

import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.function.beans.XSDStructure;

public class XSDStructureBeanTest
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: XSDStructureBeanTest.java 17191 2013-09-03 11:50:52Z Lima12 $";
	public static void main(String[] inputParameters)
	{
		XSDStructureBeanTest test = new XSDStructureBeanTest();
		test.test();
	}

	private void test()
	{
		// Have a bash...
		try
		{
			XSDStructure subfield = XSDStructure.createField("test", "test label");
			subfield.setDescription("this is a subfield");
			subfield.setLabel("this is a sample label");

			XSDStructure xsd = XSDStructure.createGroup("GP5", "test label", true);
			xsd.setDescription("Test");
			xsd.setLabel("Label");
			xsd.addSubField(subfield);

			// print the field values
			EqBeanFactory eqBeanFactory = EqBeanFactory.getEqBeanFactory();
			String xml = eqBeanFactory.serialiseBeanAsXML(xsd);
			System.out.println(xml);

			// convert to another function data
			System.out.println("====================");
			XSDStructure copy = (XSDStructure) eqBeanFactory.deserialiseXMLAsBean(xml, XSDStructure.class);
			String xml2 = eqBeanFactory.serialiseBeanAsXML(copy);
			System.out.println(xml2);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
