/***********************************************************************************************************************************
 * 
 * **********************************************************************************
 * 
 * $Id: TestMicroflowX76.java 11323 2011-06-29 11:11:42Z GOLDSMC1 $
 * 
 * 
 */

package com.misys.equation.bankfusion.test;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bf.com.misys.eq4.service.x76.X76;
import bf.com.misys.eqf.types.header.RqHeader;
import bf.com.misys.eqf.types.header.ServiceRqHeader;

import com.trapedza.bankfusion.client.BankFusionClientFactory;
import com.trapedza.bankfusion.client.IBankFusionClient;
import com.trapedza.bankfusion.client.requests.ExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;
import com.trapedza.bankfusion.core.CommonConstants;

/**
 * DemoLogin
 */
public class TestMicroflowX76
{
	/**
	 * <code>CVS_REVISION</code> = $Revision$
	 */
	public static final String CVS_REVISION = "$Revision$"; // NON-NLS-$1
	static
	{
		com.trapedza.bankfusion.utils.Tracer.register(CVS_REVISION);
	}

	private static final transient Log logger = LogFactory.getLog(TestMicroflowX76.class.getName());

	public static void main(String arguments[])
	{
		DemoLogin demoLogin = new DemoLogin();
		// valid login
		demoLogin.demoLogin("publish", "publish");

		try
		{
			IBankFusionClient bankFusionClient = BankFusionClientFactory.getInstance().getBankFusionClient();
			IExecuteMicroflowRequest executeMicroflowRequst = new ExecuteMicroflowRequest("publish", "X76_x76");
			try
			{
				X76 bf_functionData = new X76();

				bf_functionData.setHCI_HRC_holdCode("CG1");
				bf_functionData.setHCI_HRD_holdDescription("DESC0076");

				bf_functionData.setABE_HZAB_accountBranch("0543");
				bf_functionData.setABE_HZAN_basicPartOfAccountNumber("123467");
				bf_functionData.setABE_HZAS_accountSuffix("008");

				bf_functionData.setCRG1_crg1("AAA");

				ServiceRqHeader header = new ServiceRqHeader();
				RqHeader rqHeader = header.getRqHeader();
				rqHeader.setSystemId("SLOUGH1");
				rqHeader.setUnitId("AL9");
				rqHeader.setUserId("EQUIADMIN");
				rqHeader.setSessionId("Bankfusion");
				header.setOptionId("X76");
				header.getUiControlRq().setCurScrn(CommonConstants.EMPTY_STRING);
				header.setReference("");
				header.setServiceMode("");
				executeMicroflowRequst.addInputTag("ServiceHeader", header);
				executeMicroflowRequst.addInputTag("ServiceData", bf_functionData);
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
			exception.printStackTrace();
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
