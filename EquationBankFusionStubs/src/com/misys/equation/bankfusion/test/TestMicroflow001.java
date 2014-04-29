/***********************************************************************************************************************************
 * Copyright (c) 2003,2008 Trapedza Financial Systems Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of Trapedza Financial Systems Ltd. Use is subject to license terms.
 * 
 * **********************************************************************************
 * 
 * $Id: TestMicroflow001.java 5050 2009-10-06 08:09:40Z weddelc1 $
 * 
 * $Log$ Revision 1.2 2009/08/14 09:07:23 deroset *** empty log message *** Revision 1.1 2009/03/04
 * 15:15:03 misysroot\weddelc1 *** empty log message *** Revision 1.1.2.1 2008/12/17 19:09:56 nmacmaghnais Req: US497 Summary:
 * Classes for demo Fix: Correction to exception-handling
 * 
 * Revision 1.1.2.2 2008/12/16 13:21:57 nmacmaghnais Req: US497 Summary: Support for guaranteed closing of MFs on Server; Delayed
 * log-out, refactoring Fix:
 * 
 * Revision 1.1.2.1 2008/12/12 16:13:40 nmacmaghnais Req: US497 Summary: Refactoring of packages Fix:
 * 
 * Revision 1.1.2.1 2008/12/12 16:02:55 nmacmaghnais Req: US497 Summary: Refactoring of packages, more unit-tests Fix:
 * 
 * Revision 1.1.2.1 2008/12/11 20:02:05 nmacmaghnais Req: US497 Summary: Implementation is close to completion Fix:
 * 
 */

package com.misys.equation.bankfusion.test;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.trapedza.bankfusion.client.BankFusionClientFactory;
import com.trapedza.bankfusion.client.IBankFusionClient;
import com.trapedza.bankfusion.client.requests.ExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * DemoLogin
 */
public class TestMicroflow001
{
	/**
	 * <code>CVS_REVISION</code> = $Revision$
	 */
	public static final String CVS_REVISION = "$Revision$"; // NON-NLS-$1
	static
	{
		com.trapedza.bankfusion.utils.Tracer.register(CVS_REVISION);
	}

	private static final transient Log logger = LogFactory.getLog(TestMicroflow001.class.getName());

	public static void main(String arguments[])
	{
		DemoLogin demoLogin = new DemoLogin();

		// valid login
		demoLogin.demoLogin("retail", "retail");

		try
		{
			IBankFusionClient bankFusionClient = BankFusionClientFactory.getInstance().getBankFusionClient();
			IExecuteMicroflowRequest executeMicroflowRequst = new ExecuteMicroflowRequest("retail", "TestMF004");
			try
			{
				executeMicroflowRequst.addInputTag("mfInny", "fred");
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			executeMicroflowRequst.setPassword("retail");
			IExecuteMicroflowResponse executeMicroflowResponse = bankFusionClient.executeMicroflow(executeMicroflowRequst);
			print(executeMicroflowRequst, executeMicroflowResponse);
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
		}

		// valid logouts
		demoLogin.demoLogout("retail");
	}

	public void demoTaggedMicroflow(String username, String microflowName, String value)
	{
	}

	private IExecuteMicroflowRequest createExecuteMicroflowRequest(String username, String microflowName, String value)
	{
		IExecuteMicroflowRequest executeMicroflowRequst = new ExecuteMicroflowRequest(username, microflowName);

		try
		{
			executeMicroflowRequst.addInputTag("Value", new Integer(value));
			executeMicroflowRequst.addInputTag("Value", 5);
		}
		catch (Exception ex)
		{
			executeMicroflowRequst.addInputTag("Value", value);
		}

		executeMicroflowRequst.setPassword(username);

		return executeMicroflowRequst;
	}

	private static void print(IExecuteMicroflowResponse executeMicroflowResponse)
	{
		System.out.println("Results: ");
		printTags(executeMicroflowResponse.getOutputTags());
	}

	private static void print(IExecuteMicroflowRequest executeMicroflowRequest, IExecuteMicroflowResponse executeMicroflowResponse)
	{
		System.out.println(executeMicroflowRequest.getUsername() + " running " + executeMicroflowRequest.getMicroflowName()
						+ " with inputs ");
		printTags(executeMicroflowRequest.getInputTags());
		print(executeMicroflowResponse);
	}

	private static void printTags(Map<String, Object> tags)
	{
		Iterator<String> iterator = tags.keySet().iterator();
		String key = null;
		Object value = null;

		while (iterator.hasNext())
		{
			key = iterator.next();
			value = tags.get(key);

			System.out.println(" - Key = " + key + "; Value = " + value);
		}
	}
}
