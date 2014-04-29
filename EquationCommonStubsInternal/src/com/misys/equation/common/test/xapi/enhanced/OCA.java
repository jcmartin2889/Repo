/*
 * This sample code is provided by Misys for illustrative purposes only.
 * 
 * These examples have not been thoroughly tested under all conditions.
 * 
 * Misys, therefore, cannot guarantee or imply reliability, serviceability, or function of these programs.
 * 
 * All programs contained herein are provided to you "AS IS" without any warranties of any kind. The implied warranties of
 * non-infringement, merchantability and fitness for a particular purpose are expressly disclaimed.
 */
package com.misys.equation.common.test.xapi.enhanced;

import java.util.Iterator;

import com.misys.equation.common.internal.eapi.core.EQEnquiry;
import com.misys.equation.common.internal.eapi.core.EQField;
import com.misys.equation.common.internal.eapi.core.EQSession;
import com.misys.equation.common.internal.eapi.core.EQTransaction;
import com.misys.equation.common.test.TestEnvironment;

public class OCA
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OCA.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	EQSession session;
	EQTransaction customer;
	String suffix = "01";
	public OCA()
	{
		try
		{
			session = TestEnvironment.getTestEnvironment().getEnhancedAPISession();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		try
		{
			OCA test = new OCA();
			test.test();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public void test()
	{
		try
		{
			// Got this far so we may as well have a bash at a couple of transactions...
			customer = (EQTransaction) session.createEQObject("ANC");
			customer.setAuditUserID("Chris W");
			// Set the fields
			customer.setFieldValue("ZLCUS", "WEDD" + suffix);
			customer.setFieldValue("ZLCLC", "   ");
			// retrieve default values
			customer.add(session, EQEnquiry.ACTION_RETRIEVE);
			// Loop through the fields and print their values, also clear
			// messages for non key fields
			Iterator<EQField> iterFields = customer.getFields().values().iterator();
			while (iterFields.hasNext())
			{
				EQField customerField = iterFields.next();
				// Write out the fields values
				if (!customerField.getValue().trim().equals(""))
				{
					System.out.println(customerField.getDefinition().getFieldName() + ":" + customerField.getValue());
				}
				// Only interested in error messages for key fields
				if (customerField.getDefinition().isKeyField() && !(customerField.getMessages() == null))
				{
					System.out.println(customerField.getMessages());
				}
			}
			// Now set detail fields...
			customer.setFieldValue("ZLCTP", "EA");
			customer.setFieldValue("ZLCPNC", "5555" + suffix);
			customer.setFieldValue("ZLCUN", "Chris Weddell");
			customer.setFieldValue("ZLDAS", "WEDDELC1");
			customer.setFieldValue("ZLCRB1", "00");
			customer.setFieldValue("ZLCRB2", "00");
			// Bang them in
			customer.setAutoOverride(true);
			customer.add(session, EQTransaction.ACTION_UPDATE);
			System.out.println(customer.getMessages());
			System.out.println(customer.getOverrides());
			// commit the bad boys
			session.commit();
			// log out
			session.logOff();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
