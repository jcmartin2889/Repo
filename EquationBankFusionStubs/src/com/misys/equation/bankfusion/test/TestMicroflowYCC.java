/***********************************************************************************************************************************
 * 
 * **********************************************************************************
 * 
 * $Id: TestMicroflowYCC.java 11323 2011-06-29 11:11:42Z GOLDSMC1 $
 * 
 * 
 */

package com.misys.equation.bankfusion.test;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bf.com.misys.eq4.service.ycc.YCC;
import bf.com.misys.eqf.types.header.RqHeader;
import bf.com.misys.eqf.types.header.ServiceRqHeader;

import com.trapedza.bankfusion.client.BankFusionClientFactory;
import com.trapedza.bankfusion.client.IBankFusionClient;
import com.trapedza.bankfusion.client.requests.ExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * DemoLogin
 */
public class TestMicroflowYCC
{
	/**
	 * <code>CVS_REVISION</code> = $Revision$
	 */
	public static final String CVS_REVISION = "$Revision$"; // NON-NLS-$1
	static
	{
		com.trapedza.bankfusion.utils.Tracer.register(CVS_REVISION);
	}

	private static final transient Log logger = LogFactory.getLog(TestMicroflowYCC.class.getName());

	public static void main(String arguments[])
	{
		DemoLogin demoLogin = new DemoLogin();
		// valid login
		demoLogin.demoLogin("retail", "retail");

		try
		{
			IBankFusionClient bankFusionClient = BankFusionClientFactory.getInstance().getBankFusionClient();
			IExecuteMicroflowRequest executeMicroflowRequst = new ExecuteMicroflowRequest("retail", "YCC");
			try
			{
				YCC bf_functionData = new YCC();
				bf_functionData.setCPNC("555727");

				ServiceRqHeader header = new ServiceRqHeader();
				RqHeader rqHeader = header.getRqHeader();
				rqHeader.setSystemId("slough1");
				rqHeader.setUnitId("EQX");
				rqHeader.setUserId("EQUIADMIN");
				rqHeader.setSessionId("Bankfusion");
				header.setOptionId("YCA");
				executeMicroflowRequst.addInputTag("ServiceHeader", header);
				executeMicroflowRequst.addInputTag("ServiceData", bf_functionData);
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
