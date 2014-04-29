package com.misys.equation.bf.stub.mf.lrp;

import com.misys.equation.bf.lrp.TestMicroflowWPS;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * Get Input Message
 */
public class TestMicroflowGetComments extends TestMicroflowWPS
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
			new TestMicroflowGetComments().start();
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
		String microFlowName = "GetComments";
		IExecuteMicroflowRequest executeMicroflowRequest = getMicroflowRequest(microFlowName);

		// Task details
		String processId = getProcessId();

		// Set the parameters
		executeMicroflowRequest.addInputTag("ProcessIinstanceID", processId);
		executeMicroflowRequest.addInputTag("TimeOut", "30000");

		// Execute the MicroFlow
		IExecuteMicroflowResponse executeMicroflowResponse = executeMicroflow(executeMicroflowRequest);

		// Print details
		printGetComments(executeMicroflowResponse);

		// Assume success
		assertEquals(true, true);
	}

}
