package com.misys.equation.bf.stub.mf;

import bf.com.misys.eqf.types.header.EsfKey;
import bf.com.misys.eqf.types.header.UpdateEsfStatusRqHeader;
import bf.com.misys.eqf.types.header.UpdateEsfStatusRsHeader;

import com.misys.equation.bankfusion.lrp.engine.TaskEngineToolbox;
import com.misys.equation.bf.TestMicroflow;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * Get Input Message
 */
public class UpdateEsfStatusMF extends TestMicroflow
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
			new UpdateEsfStatusMF().start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Test data
	 */
	public void test()
	{
		// Retrieve the MicroFlow
		String microFlowName = "EQ_CMN_UpdateEsfStatus_SRV";
		IExecuteMicroflowRequest executeMicroflowRequest = getMicroflowRequest(microFlowName);

		EsfKey esfKey = new EsfKey();
		esfKey.setOptionId("ALZ");
		esfKey.setUserId("EQCHINESE");
		esfKey.setTransactionId("A2010.07.23 17:30:52 656");
		esfKey.setSessionId("SESSIONID");

		UpdateEsfStatusRqHeader input = new UpdateEsfStatusRqHeader();
		input.setEsfKey(esfKey);
		input.setAction(TaskEngineToolbox.TASK_ACTION_DECL);
		input.setReason("No way out");
		input.setSystemId("SLOUGH1");
		input.setUnitId("AL9");
		input.setUserId("LIMA1");

		// Set the parameters
		executeMicroflowRequest.addInputTag("input", input);

		// Execute the MicroFlow
		IExecuteMicroflowResponse executeMicroflowResponse = executeMicroflow(executeMicroflowRequest);

		UpdateEsfStatusRsHeader output = (UpdateEsfStatusRsHeader) executeMicroflowResponse.getOutputTags().get("output");

		System.out.println(output.getErrorCode() + " " + output.getErrorMessage());

		// assume success
		assertEquals(output.getErrorCode().trim(), "");
	}
}
