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

import com.misys.equation.common.internal.eapi.core.EQEnquiry;
import com.misys.equation.common.internal.eapi.core.EQSession;
import com.misys.equation.common.internal.eapi.core.EQTransaction;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.Toolbox;

public class AOC
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AOC.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	EQSession session;
	EQTransaction authoriseOutwardCleanPayment;
	String paymentReference = "000180404H000013";
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	public AOC()
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
			AOC test = new AOC();
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
			authoriseOutwardCleanPayment = (EQTransaction) session.createEQObject("AOC");
			for (int i = 0; i < 1; i++)
			{
				authoriseOutwardCleanPayment.reset();
				authoriseOutwardCleanPayment.setAuditUserID("Sm@rt");
				authoriseOutwardCleanPayment.setReference("TESTAOC");
				// Set the fields
				authoriseOutwardCleanPayment.setFieldValue("ZLREF", paymentReference);
				// retrieve default values
				authoriseOutwardCleanPayment.maintain(session, EQEnquiry.ACTION_RETRIEVE);
				// ************
				Toolbox.printEQObject(authoriseOutwardCleanPayment);
				Toolbox.log("validate");
				Toolbox.printFieldMessages(authoriseOutwardCleanPayment.getMessages());
				// ************
				authoriseOutwardCleanPayment.setAutoOverride(true);
				authoriseOutwardCleanPayment.maintain(session, EQTransaction.ACTION_UPDATE);
				// ************
				Toolbox.log("update");
				Toolbox.printFieldMessages(authoriseOutwardCleanPayment.getMessages());
				// ************
			}
			// now we are in a position to commit ...
			session.commit();
			// ************
			Toolbox.log("Commiting");
			// ************
			// log out
			session.logOff();
			// ************
			Toolbox.log("Returning the session");
			// ************
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
