package com.misys.equation.function.test.beans;

import junit.framework.TestCase;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayGroup;
import com.misys.equation.function.beans.Element;
import com.misys.equation.function.beans.IDisplayItem;
import com.misys.equation.function.beans.Layout;

/**
 * This class tests the serialization and de-serialization of the Layout bean.
 * <p>
 * It ensures that the polymorphic DisplayItems collections are correctly processed by betwixt
 * 
 */
public class LayoutBeanStub extends TestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LayoutBeanStub.java 4735 2009-09-15 16:58:25Z lima12 $";
	EquationStandardSession session;
	String generatedXml;

	@Override
	public void setUp()
	{
		Layout layout = new Layout("JBP", "JBP Layout Title", "JBP Layout Description");
		DisplayAttributesSet set1 = new DisplayAttributesSet("FS1", "FS1 Label", "FS1 Description");
		layout.addDisplayAttributesSet(set1);

		IDisplayItem child1 = new DisplayAttributes("FIELD1", "FIELD1", "FIELD1");
		set1.getDisplayItems().add(child1);

		IDisplayItem child2 = new DisplayAttributes("FIELD2", "FIELD2", "FIELD2");
		set1.getDisplayItems().add(child2);

		IDisplayItem child3 = new DisplayAttributes("FIELD3", "FIELD3", "FIELD3");
		set1.getDisplayItems().add(child3);

		DisplayGroup group1 = new DisplayGroup("GROUP1", "GROUP1", "GROUP1");
		set1.getDisplayItems().add(group1);

		DisplayGroup group2 = new DisplayGroup("EMPTYGROUP", "EMPTYGROUP", "EMPTYGROUP");
		set1.getDisplayItems().add(group2);

		DisplayGroup altGroup1 = new DisplayGroup("ALTGROUP1", "ALTGROUP1", "ALTGROUP1");
		group1.getDisplayItems().add(altGroup1);

		IDisplayItem field4 = new DisplayAttributes("FIELD4", "FIELD4", "FIELD4");
		altGroup1.getDisplayItems().add(field4);

		DisplayGroup altGroup2 = new DisplayGroup("ALT2", "ALT2", "ALT2");
		group1.getDisplayItems().add(altGroup2);

		IDisplayItem field5 = new DisplayAttributes("FIELD5", "FIELD5", "FIELD5");
		altGroup2.getDisplayItems().add(field5);

		//
		// IDisplayItem field10 = new DisplayAttributes("FIELD10", "FIELD10", "FIELD10");
		// group1.addDisplayItem(field10);

		// IDisplayItem field5 = new DisplayAttributes("FIELD5", "FIELD5", "FIELD5");
		// set1.addDisplayItem(field5);

		// Print the XML
		EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
		try
		{
			generatedXml = beanFactory.serialiseBeanAsXML(layout);
		}
		catch (EQException e)
		{
			throw new RuntimeException(e);
		}
		System.out.println(generatedXml);
	}

	public void test() throws EQException
	{
		EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
		Layout layout = (Layout) beanFactory.deserialiseXMLAsBean(generatedXml, Layout.class);
		assertNotNull(layout);

		DisplayAttributesSet set1 = layout.getDisplayAttributesSet("FS1");
		assertNotNull(set1);
		assertEquals("FS1", set1.getId());

		assertEquals(5, set1.getDisplayItems().size());
		IDisplayItem group1Item = set1.getDisplayItems().get("GROUP1");
		assertNotNull(group1Item);
		assertTrue(group1Item instanceof DisplayGroup);
		assertTrue(group1Item instanceof Element);

		Element group1 = (Element) group1Item;
		assertEquals("GROUP1", group1.getId());
		assertNotNull(group1.rtvParent());
		assertEquals(set1, group1.rtvParent());

		IDisplayItem altGroupItem1 = ((DisplayGroup) group1).getDisplayItems().get("ALTGROUP1");
		assertNotNull(altGroupItem1);

		assertTrue(altGroupItem1 instanceof DisplayGroup);
		DisplayGroup altGroup1 = (DisplayGroup) altGroupItem1;
		assertEquals("ALTGROUP1", altGroup1.getId());
		// Alt group 1 should contain FIELD4
		assertEquals(1, altGroup1.getDisplayItems().size());

		IDisplayItem field4 = altGroup1.getDisplayItems().get("FIELD4");
		assertTrue(field4 instanceof DisplayAttributes);

	}
}
