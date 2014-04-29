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

import com.misys.equation.common.internal.eapi.core.EQField;
import com.misys.equation.common.internal.eapi.core.EQSession;
import com.misys.equation.common.internal.eapi.core.EQTransaction;
import com.misys.equation.common.test.TestEnvironment;

public class RLA
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RLA.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	EQSession session;
	EQTransaction loan;
	public RLA()
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
			RLA test = new RLA();
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
			// Got this far so we may as well have a bash at a couple of
			// transactions...
			loan = (EQTransaction) session.createEQObject("RLA");
			loan.setAuditUserID("whoever");
			// Set the fields
			loan.setFieldValue("ZLLNP", "CD2");
			loan.setFieldValue("ZLLNR", "WHATEVER");
			loan.setFieldValue("ZLCPNC", "Z00001");
			loan.setFieldValue("ZLCCY", "GBP");
			loan.setFieldValue("ZLDLAZ", "100000");
			// retrieve default values
			loan.add(session, EQTransaction.ACTION_VALIDATE);
			// Loop through the fields and print their values, also clear
			// messages for non key fields
			Iterator<EQField> iterFields = loan.getFields().values().iterator();
			while (iterFields.hasNext())
			{
				EQField accountField = iterFields.next();
				// Write out the fields values
				if (!accountField.getValue().trim().equals(""))
				{
					System.out.println(accountField.getDefinition().getFieldName() + ":" + accountField.getValue());
				}
				// Only interested in error messages for key fields
				if (accountField.getDefinition().isKeyField() && !(accountField.getMessages() == null))
				{
					System.out.println(accountField.getMessages());
				}
			}
			// Bang them in
			loan.setAutoOverride(true);
			loan.add(session, EQTransaction.ACTION_UPDATE);
			System.out.println(loan.getMessages());
			System.out.println(loan.getOverrides());
			System.out.println(loan.toString());
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
