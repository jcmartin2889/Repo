package com.misys.equation.bf.stub.mf;

import bf.com.misys.eqf.types.header.EqProcessHeader;
import bf.com.misys.eqf.types.header.EsfKey;
import bf.com.misys.eqf.types.header.StartLRPProcessRqHeader;
import bf.com.misys.eqf.types.header.StartLRPProcessRsHeader;

import com.misys.equation.bankfusion.lrp.engine.TaskEngineToolbox;
import com.misys.equation.bf.TestMicroflow;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * Get Input Message
 */
public class StartLrpProcessMF extends TestMicroflow
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
			new StartLrpProcessMF().start();
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
		String microFlowName = "EQ_CMN_StartLRPProcess_SRV";
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

		StartLRPProcessRqHeader startLrpProcessRqHeader = new StartLRPProcessRqHeader();
		startLrpProcessRqHeader.setOperationName(operationName);
		startLrpProcessRqHeader.setServiceName(serviceName);
		startLrpProcessRqHeader.setPayload(eqProcessHeader);

		// Set the parameters
		executeMicroflowRequest.addInputTag("startLrpProcessRqHeader", startLrpProcessRqHeader);

		// Execute the MicroFlow
		IExecuteMicroflowResponse executeMicroflowResponse = executeMicroflow(executeMicroflowRequest);

		StartLRPProcessRsHeader output = (StartLRPProcessRsHeader) executeMicroflowResponse.getOutputTags().get(
						"startLrpProcessRsHeader");

		System.out.println(output.getPayload());

		// assume success
		assertEquals(output.getPayload() == null, false);
	}
}
