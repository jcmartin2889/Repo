package com.misys.equation.bf.stub.mf.lrp;

import com.misys.equation.bf.lrp.TestMicroflowWPS;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * Get Input Message
 */
public class TestMicroflowGetCustomProperties extends TestMicroflowWPS
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
			new TestMicroflowGetCustomProperties().start();
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
		String microFlowName = "GetCustomProperties";
		IExecuteMicroflowRequest executeMicroflowRequest = getMicroflowRequest(microFlowName);

		// Task details
		String taskId = getTaskId();

		// Set the parameters
		executeMicroflowRequest.addInputTag("tkiid", taskId);

		// Execute the MicroFlow
		IExecuteMicroflowResponse executeMicroflowResponse = executeMicroflow(executeMicroflowRequest);

		// Print details
		printGetCustomProperties(executeMicroflowResponse);

		// assume success
		assertEquals(true, true);
	}

}
