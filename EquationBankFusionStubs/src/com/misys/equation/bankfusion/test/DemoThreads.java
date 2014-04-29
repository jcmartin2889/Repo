/* ***********************************************************************************
 * Copyright (c) 2003,2008 Trapedza Financial Systems Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of Trapedza Financial Systems Ltd. Use is subject to license terms.
 * 
 * **********************************************************************************
 * 
 * $Id: DemoThreads.java 4455 2009-08-14 09:07:24Z deroset $
 * 
 * $Log$ Revision 1.1 2009/03/04 15:15:03 misysroot\weddelc1 *** empty log message ***
 * 
 * Revision 1.1.2.1 2008/12/17 19:09:56 nmacmaghnais Req: US497 Summary: Classes for demo Fix: Correction to exception-handling
 */

package com.misys.equation.bankfusion.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * DemoThreads
 */
public class DemoThreads
{
	/**
	 * <code>CVS_REVISION</code> = $Revision$
	 */
	public static final String CVS_REVISION = "$Revision$"; // NON-NLS-$1
	static
	{
		com.trapedza.bankfusion.utils.Tracer.register(CVS_REVISION);
	}

	private static final transient Log logger = LogFactory.getLog(DemoThreads.class.getName());

	public static void main(String[] arguments)
	{
		Thread t1 = new Thread(new MFExecuter());
		Thread t2 = new Thread(new MFExecuter());

		t1.start();
		t2.start();

		try
		{
			Thread.sleep(5000);
		}
		catch (Exception ex)
		{

		}

		DemoLogin dl = new DemoLogin();

		dl.demoLogout("retail");
		dl.demoLogout("brad");
	}
}