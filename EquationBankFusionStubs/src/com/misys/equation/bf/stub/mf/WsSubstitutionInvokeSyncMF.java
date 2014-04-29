package com.misys.equation.bf.stub.mf;

import bf.com.misys.bankfusion.attributes.WebService;
import bf.com.misys.eqf.types.header.EqProcessHeader;
import bf.com.misys.eqf.types.header.EsfKey;
import bf.com.misys.eqf.types.header.StartLRPProcessRsHeader;

import com.misys.equation.bankfusion.lrp.engine.TaskEngineToolbox;
import com.misys.equation.bf.TestMicroflow;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * Get Input Message
 */
public class WsSubstitutionInvokeSyncMF extends TestMicroflow
{
	public static final String CVS_REVISION = "$Revision$";

	static
	{
		com.trapedza.bankfusion.utils.Tracer.register(CVS_REVISION);
	}

	/**
	 * Main
	 * 
	 * @param arguments
	 *            - arguments
	 */
	public static void main(String arguments[])
	{
		try
		{
			new WsSubstitutionInvokeSyncMF().start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Test data
	 */
	public void test() throws Exception
	{
		// Retrieve the MicroFlow
		String microFlowName = "WsSubstitutionInvokeSync";
		IExecuteMicroflowRequest executeMicroflowRequest = getMicroflowRequest(microFlowName);

		EsfKey esfKey = new EsfKey();
		esfKey.setOptionId("ALZ");
		esfKey.setUserId("EQCHINESE");
		esfKey.setTransactionId("A2010.07.23 17:30:52 656");
		esfKey.setSessionId("SESSIONID");

		EqProcessHeader eqProcessHeader = new EqProcessHeader();
		eqProcessHeader.setBfeqType(TaskEngineToolbox.BFEQ_TYPE);
		eqProcessHeader.setZone("SLOUGH1-AL9");
		eqProcessHeader.setPayload(esfKey);

		String serviceName = "RateMaintenaceInterfaceExport1_RateMaintenaceInterfaceHttpService";
		String operationName = "rateMaintenanceOperation";

		WebService webService = new WebService();
		webService.setOperationName(operationName);
		webService.setRequestPayload(eqProcessHeader);
		webService.setSecurityCheckRequired(true);
		webService.setServiceName(serviceName);
		webService.setWSSecurityType("UserNameToken");

		// Set the parameters
		executeMicroflowRequest.addInputTag("WebService", webService);

		// Execute the MicroFlow
		IExecuteMicroflowResponse executeMicroflowResponse = executeMicroflow(executeMicroflowRequest);

		StartLRPProcessRsHeader output = (StartLRPProcessRsHeader) executeMicroflowResponse.getOutputTags().get("output");

		System.out.println(output.getPayload());

		// assume success
		assertEquals(output.getPayload() instanceof String, true);
	}
}
