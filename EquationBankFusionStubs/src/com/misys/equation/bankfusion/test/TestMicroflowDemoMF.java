/***********************************************************************************************************************************
 * 
 * **********************************************************************************
 * 
 * $Id: TestMicroflowDemoMF.java 7094 2010-04-28 10:58:02Z goldsmc1 $
 * 
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
public class TestMicroflowDemoMF
{
	/**
	 * <code>CVS_REVISION</code> = $Revision$
	 */
	public static final String CVS_REVISION = "$Revision$"; // NON-NLS-$1
	static
	{
		com.trapedza.bankfusion.utils.Tracer.register(CVS_REVISION);
	}

	private static final transient Log logger = LogFactory.getLog(TestMicroflowDemoMF.class.getName());

	public static void main(String arguments[])
	{
		DemoLogin demoLogin = new DemoLogin();
		// valid login
		demoLogin.demoLogin("publish", "publish");

		try
		{
			IBankFusionClient bankFusionClient = BankFusionClientFactory.getInstance().getBankFusionClient();
			IExecuteMicroflowRequest executeMicroflowRequst = new ExecuteMicroflowRequest("publish", "DemoMF");
			try
			{
				// String inputString = "Chris";
				String inputString = "*     *  *                                  *     **     *   * * * * * *              * * * * *  * * * *     *  *    * *      *                                                                                                                                 ";
				System.out.println("inputString: " + inputString);

				executeMicroflowRequst.addInputTag("Input", inputString);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			executeMicroflowRequst.setPassword("publish");
			IExecuteMicroflowResponse executeMicroflowResponse = bankFusionClient.executeMicroflow(executeMicroflowRequst);
			print(executeMicroflowRequst, executeMicroflowResponse);
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
		}

		// valid logouts
		demoLogin.demoLogout("publish");
	}

	public void demoTaggedMicroflow(String username, String microflowName, String value)
	{
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
