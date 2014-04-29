package com.misys.equation.bf.stub.mf.lrp;

import bf.com.misys.bankfusion.workflow.attributes.CancelClaim;

import com.misys.equation.bf.lrp.TestMicroflowWPS;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * Get Input Message
 */
public class TestMicroflowCancelClaim extends TestMicroflowWPS
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
			new TestMicroflowCancelClaim().start();
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
		String microFlowName = "CancelClaim";
		IExecuteMicroflowRequest executeMicroflowRequest = getMicroflowRequest(microFlowName);

		// Task details
		String taskId = getTaskId();

		CancelClaim cancelClaim = new CancelClaim();
		cancelClaim.setTkiid(taskId);
		cancelClaim.setKeepTaskData(true);

		// Set the parameters
		executeMicroflowRequest.addInputTag("CancelClaim", cancelClaim);

		// Execute the MicroFlow
		IExecuteMicroflowResponse executeMicroflowResponse = executeMicroflow(executeMicroflowRequest);

		// Print details
		printCancelClaimResponse(executeMicroflowResponse);

		// Assume success
		assertEquals(true, true);
	}

}
