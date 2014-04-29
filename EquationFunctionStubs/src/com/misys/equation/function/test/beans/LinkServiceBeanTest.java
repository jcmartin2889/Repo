package com.misys.equation.function.test.beans;

import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.function.beans.LinkService;

/**
 * Set of test cases to test the validation of InputField beans
 * 
 */
public class LinkServiceBeanTest extends BeanTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LinkServiceBeanTest.java 14808 2012-11-05 11:58:49Z williae1 $";

	/** Upper case is only disallowed for boolean fields */
	public void testInvalidUpperCase()
	{
		EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();

		// Create a link service
		LinkService link = new LinkService();
		link.setId("id");
		link.setPrimaryId("primary");
		link.getSecondaryIds().add("secondary");

		// Serialise it
		try
		{
			String linkServiceXML = beanFactory.serialiseBeanAsXML(link);

			// De-serialise it
			LinkService reborn = (LinkService) beanFactory.deserialiseXMLAsBean(linkServiceXML, LinkService.class);

			assertEquals(link.getId().equals(reborn.getId()), true);
			assertEquals(link.getPrimaryId().equals(reborn.getPrimaryId()), true);
			assertEquals(link.getSecondaryIds().get(0).equals(reborn.getSecondaryIds().get(0)), true);
		}
		catch (Exception e)
		{
			assertEquals(false, true);
		}

	}
}
