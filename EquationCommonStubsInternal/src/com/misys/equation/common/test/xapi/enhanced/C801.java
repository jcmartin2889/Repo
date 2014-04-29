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

import com.misys.equation.common.internal.eapi.core.EQList;
import com.misys.equation.common.internal.eapi.core.EQObject;
import com.misys.equation.common.internal.eapi.core.EQPrompt;
import com.misys.equation.common.internal.eapi.core.EQSession;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.Toolbox;

public class C801
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: C801.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	EQSession session;
	EQPrompt currencies;
	String currencyMnemonic = "*";
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	public C801()
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
			C801 test = new C801();
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
			currencies = (EQPrompt) session.createEQObject("C801");
			currencies.setDecode(" ".toCharArray()[0]);
			currencies.setFieldValue("ZLCNMC", currencyMnemonic);
			currencies.retrieve(session);
			Toolbox.printEQObjectKeyFieldErrors(currencies);
			Toolbox.printEQObject(currencies);
			EQList currencyList = currencies.getList();
			while (currencies.getStatus() == EQObject.STATUS_INCOMPLETE)
			{
				currencies.retrieve(session);
			}
			Toolbox.printEQList(currencyList);
			Toolbox.log("currency list");
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