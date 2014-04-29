/***********************************************************************************************************************************
 * 
 * **********************************************************************************
 * 
 * $Id: TestMicroflowGFR70R.java 11323 2011-06-29 11:11:42Z GOLDSMC1 $
 * 
 * 
 */

package com.misys.equation.bankfusion.test;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bf.com.misys.eq4.search.gfr70r.GFR70R;
import bf.com.misys.eq4.search.gfr70r.PV_row;
import bf.com.misys.eqf.types.header.RqHeader;
import bf.com.misys.eqf.types.header.SearchRqHeader;
import bf.com.misys.eqf.types.header.UiControlRq;

import com.trapedza.bankfusion.client.BankFusionClientFactory;
import com.trapedza.bankfusion.client.IBankFusionClient;
import com.trapedza.bankfusion.client.requests.ExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * DemoLogin
 */
public class TestMicroflowGFR70R
{
	/**
	 * <code>CVS_REVISION</code> = $Revision$
	 */
	public static final String CVS_REVISION = "$Revision$"; // NON-NLS-$1
	static
	{
		com.trapedza.bankfusion.utils.Tracer.register(CVS_REVISION);
	}

	private static final transient Log logger = LogFactory.getLog(TestMicroflowGFR70R.class.getName());

	/*
	 * Test PV microflow - GFR70R
	 * 
	 * implemented by SearchHandlerActivityStep
	 */

	public static void main(String arguments[])
	{
		DemoLogin demoLogin = new DemoLogin();
		// valid login
		demoLogin.demoLogin("publish", "publish");

		try
		{
			IBankFusionClient bankFusionClient = BankFusionClientFactory.getInstance().getBankFusionClient();
			IExecuteMicroflowRequest executeMicroflowRequst = new ExecuteMicroflowRequest("publish", "GFR70R");
			try
			{
				SearchRqHeader header = new SearchRqHeader();
				RqHeader rqHeader = new RqHeader();
				header.setRqHeader(rqHeader);
				header.setDecode(" ");
				header.setDirection("B");
				header.setMaxResults(new Integer(0));
				header.setFilterFields("*:00*:");
				header.setPagingFields("XAVIER:003:");
				header.setPvId("GFR70R");
				rqHeader = header.getRqHeader();
				rqHeader.setSessionId("junk364");
				rqHeader.setSystemId("SLOUGH1");
				rqHeader.setUnitId("EQ6");
				rqHeader.setUserId("EQUIADMIN");
				header.setUiControlRq(new UiControlRq());

				executeMicroflowRequst.addInputTag("SearchHeader", header);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}

			executeMicroflowRequst.setPassword("publish");
			IExecuteMicroflowResponse executeMicroflowResponse = bankFusionClient.executeMicroflow(executeMicroflowRequst);

			GFR70R outputSearchData = (GFR70R) executeMicroflowResponse.getOutputTags().get("OutputSearchData");

			print(executeMicroflowRequst, executeMicroflowResponse);
			print(outputSearchData);
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

	private static void print(GFR70R outputSearchData)
	{
		System.out.println("PV data: ");
		for (PV_row row : outputSearchData.getPV_row())
		{
			System.out.print(" - ");
			System.out.print(row.getGFCUS_customerMnemonic() + ":");
			System.out.print(row.getGFCLC_customerLocation() + ":");
			System.out.print(row.getGFCUN_customerName() + ":");
			System.out.print(row.getGFGRP_group() + ":");
			System.out.print(row.getGFCPN_customerNumber() + ":");
			System.out.println(row.getGFACO_accountOfficer());
			// etc, etc
		}
	}
}
