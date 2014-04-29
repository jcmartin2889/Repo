/* ***********************************************************************************
 * Copyright (c) 2003,2008 Trapedza Financial Systems Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of Trapedza Financial Systems Ltd. Use is subject to license terms.
 * 
 * **********************************************************************************
 * 
 * $Id: DemoLogin.java 8582 2010-08-11 01:26:18Z esther.williams $
 * 
 * $Log$ Revision 1.3 2009/08/14 09:07:23 deroset *** empty log message *** Revision 1.2 2009/07/31 15:15:28
 * macdonp1 *** empty log message *** Revision 1.1 2009/03/04 15:15:03 misysroot\weddelc1 *** empty log message ***
 * 
 * Revision 1.1.2.1 2008/12/17 19:09:56 nmacmaghnais Req: US497 Summary: Classes for demo Fix: Correction to exception-handling
 * 
 * Revision 1.1.2.2 2008/12/16 13:21:57 nmacmaghnais Req: US497 Summary: Support for guaranteed closing of MFs on Server; Delayed
 * log-out, refactoring Fix:
 * 
 * Revision 1.1.2.1 2008/12/12 16:13:40 nmacmaghnais Req: US497 Summary: Refactoring of packages Fix:
 * 
 * Revision 1.1.2.1 2008/12/12 16:02:55 nmacmaghnais Req: US497 Summary: Refactoring of packages, more unit-tests Fix:
 * 
 * Revision 1.1.2.1 2008/12/11 20:02:05 nmacmaghnais Req: US497 Summary: Implementation is close to completion Fix:
 */

package com.misys.equation.bankfusion.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.misys.equation.common.utilities.Toolbox;
import com.trapedza.bankfusion.client.BankFusionClientFactory;
import com.trapedza.bankfusion.client.IBankFusionClient;

/**
 * DemoLogin
 */
public class DemoLogin
{
	/**
	 * <code>CVS_REVISION</code> = $Revision$
	 */
	public static final String CVS_REVISION = "$Revision$"; // NON-NLS-$1
	static
	{
		com.trapedza.bankfusion.utils.Tracer.register(CVS_REVISION);
	}

	private static final transient Log logger = LogFactory.getLog(DemoLogin.class.getName());

	public static void main(String arguments[])
	{
		DemoLogin demoLogin = new DemoLogin();

		// start server
		// client-side error - no username
		demoLogin.demoLogin("", "retail");
		// client-side error - no password
		demoLogin.demoLogin("retail", "");
		// server-side error - username is wrong
		demoLogin.demoLogin("retai", "retail");
		// server-side error - password is wrong
		demoLogin.demoLogin("retail", "retai");
		// login through BFTC
		// valid input but user is already logged in
		demoLogin.demoLogin("retail", "retail");
		// logout from the BFTC
		// valid login
		demoLogin.demoLogin("retail", "retail");
		// verify that one cannot login through the BFTC
		// valid logout
		demoLogin.demoLogout("retail");
		// verify that one can login through the BFTC
	}

	public void demoLogin(String username, String password)
	{
		try
		{
			IBankFusionClient bankFusionClient = BankFusionClientFactory.getInstance().getBankFusionClient();

			bankFusionClient.login(username, password);
			System.out.println(username + " is logged in");
		}
		catch (Exception e)
		{
			System.out.println(Toolbox.getExceptionMessage(e));
		}
	}

	public void demoLogout(String username)
	{
		try
		{
			IBankFusionClient bankFusionClient = BankFusionClientFactory.getInstance().getBankFusionClient();

			bankFusionClient.logout(username);
			System.out.println(username + " is logged out");
		}
		catch (Exception e)
		{
			System.out.println(Toolbox.getExceptionMessage(e));
		}
	}

}
