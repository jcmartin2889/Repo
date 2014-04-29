/* ***********************************************************************************
 * Copyright (c) 2003,2008 Trapedza Financial Systems Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of Trapedza Financial Systems Ltd. Use is subject to license terms.
 * 
 * **********************************************************************************
 * 
 * $Id: DemoMicroflow.java 4455 2009-08-14 09:07:24Z deroset $
 * 
 * $Log$ Revision 1.2 2009/07/31 15:15:28 macdonp1 *** empty log message *** Revision 1.1 2009/03/04 15:15:03
 * misysroot\weddelc1 *** empty log message ***
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

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.trapedza.bankfusion.client.BankFusionClientFactory;
import com.trapedza.bankfusion.client.IBankFusionClient;
import com.trapedza.bankfusion.client.exceptions.BankFusionClientWarning;
import com.trapedza.bankfusion.client.requests.ExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * DemoLogin
 */
public class DemoMicroflow
{
	/**
	 * <code>CVS_REVISION</code> = $Revision$
	 */
	public static final String CVS_REVISION = "$Revision$"; // NON-NLS-$1
	static
	{
		com.trapedza.bankfusion.utils.Tracer.register(CVS_REVISION);
	}

	private static final transient Log logger = LogFactory.getLog(DemoMicroflow.class.getName());

	public static void main(String arguments[])
	{
		DemoLogin demoLogin = new DemoLogin();
		DemoMicroflow demoMicroflow = new DemoMicroflow();

		// valid login
		demoLogin.demoLogin("retail", "retail");

		// no user name is specified
		demoMicroflow.demoSimpleMicroflow("", "DoubleValue");

		// no microflow name is specified
		demoMicroflow.demoSimpleMicroflow("retail", "");

		// invalid user name
		demoMicroflow.demoSimpleMicroflow("retai", "DoubleValue");

		// invalid microflow name
		demoMicroflow.demoSimpleMicroflow("retail", "TrebleValue");

		// default value is 0, so a warning will result
		demoMicroflow.demoSimpleMicroflow("retail", "DoubleValue");

		// value is not numeric; an error is expected
		demoMicroflow.demoTaggedMicroflow("retail", "DoubleValue", "A");

		// value is valid
		demoMicroflow.demoTaggedMicroflow("retail", "DoubleValue", "5");

		// value is valid
		// a different user though
		demoMicroflow.demoTaggedMicroflow("brad", "DoubleValue", "3");

		// input is revised
		demoMicroflow.demoRevisedInput("retail", "DoubleValue", "0");

		// warning is manually suppressed by client
		demoMicroflow.demoSuppressPreviousWarning("retail", "DoubleValue", "0");

		// alls warnings are suppressed by client
		demoMicroflow.demoSuppressAllWarnings("retail", "DoubleValue", "0");

		// valid logouts
		demoLogin.demoLogout("retail");
		demoLogin.demoLogout("brad");
		// verify that one can login through the BFTC
	}

	private void demoSimpleMicroflow(String username, String microflowName)
	{
		try
		{
			IBankFusionClient bankFusionClient = BankFusionClientFactory.getInstance().getBankFusionClient();

			IExecuteMicroflowResponse microflowResponse = bankFusionClient.executeMicroflow(username, microflowName);

			print(microflowResponse);
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
		}
	}

	public void demoTaggedMicroflow(String username, String microflowName, String value)
	{
		try
		{
			IBankFusionClient bankFusionClient = BankFusionClientFactory.getInstance().getBankFusionClient();
			IExecuteMicroflowRequest executeMicroflowRequst = createExecuteMicroflowRequest(username, microflowName, value);
			IExecuteMicroflowResponse executeMicroflowResponse = bankFusionClient.executeMicroflow(executeMicroflowRequst);

			print(executeMicroflowRequst, executeMicroflowResponse);
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
		}
	}

	private void demoRevisedInput(String username, String microflowName, String value)
	{
		try
		{
			IBankFusionClient bankFusionClient = BankFusionClientFactory.getInstance().getBankFusionClient();
			IExecuteMicroflowRequest executeMicroflowRequst = createExecuteMicroflowRequest(username, microflowName, value);
			IExecuteMicroflowResponse executeMicroflowResponse = null;
			try
			{
				executeMicroflowResponse = bankFusionClient.executeMicroflow(executeMicroflowRequst);
			}
			catch (BankFusionClientWarning bankFusionClientWarning)
			{
				Integer newValue = new Integer(value);
				newValue = newValue.intValue() + 1;
				executeMicroflowRequst = createExecuteMicroflowRequest(username, microflowName, newValue.toString());
				executeMicroflowResponse = bankFusionClient.executeMicroflow(executeMicroflowRequst);

			}
			print(executeMicroflowRequst, executeMicroflowResponse);
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
		}
	}

	private void demoSuppressPreviousWarning(String username, String microflowName, String value)
	{
		try
		{
			IBankFusionClient bankFusionClient = BankFusionClientFactory.getInstance().getBankFusionClient();
			IExecuteMicroflowRequest executeMicroflowRequst = createExecuteMicroflowRequest(username, microflowName, value);
			IExecuteMicroflowResponse executeMicroflowResponse = null;
			try
			{
				executeMicroflowResponse = bankFusionClient.executeMicroflow(executeMicroflowRequst);
			}
			catch (BankFusionClientWarning bankFusionClientWarning)
			{
				executeMicroflowRequst = bankFusionClientWarning.getReExecutableMicroflowRequest();
				executeMicroflowResponse = bankFusionClient.executeMicroflow(executeMicroflowRequst);

			}
			print(executeMicroflowRequst, executeMicroflowResponse);
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
		}
	}

	private void demoSuppressAllWarnings(String username, String microflowName, String value)
	{
		try
		{
			IBankFusionClient bankFusionClient = BankFusionClientFactory.getInstance().getBankFusionClient();
			IExecuteMicroflowRequest executeMicroflowRequst = createExecuteMicroflowRequest(username, microflowName, value);

			executeMicroflowRequst.setSuppressingAllWarnings(true);

			IExecuteMicroflowResponse executeMicroflowResponse = bankFusionClient.executeMicroflow(executeMicroflowRequst);

			print(executeMicroflowRequst, executeMicroflowResponse);
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
		}
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

	private void print(IExecuteMicroflowResponse executeMicroflowResponse)
	{
		System.out.println("Results: ");
		printTags(executeMicroflowResponse.getOutputTags());
	}

	private void print(IExecuteMicroflowRequest executeMicroflowRequest, IExecuteMicroflowResponse executeMicroflowResponse)
	{
		System.out.println(executeMicroflowRequest.getUsername() + " running " + executeMicroflowRequest.getMicroflowName()
						+ " with inputs ");
		printTags(executeMicroflowRequest.getInputTags());
		print(executeMicroflowResponse);
	}

	private void printTags(Map<String, Object> tags)
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
