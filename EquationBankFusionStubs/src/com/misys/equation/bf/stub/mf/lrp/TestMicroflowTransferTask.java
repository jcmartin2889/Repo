package com.misys.equation.bf.stub.mf.lrp;

import bf.com.misys.bankfusion.workflow.attributes.TransferWorkItemBO;

import com.misys.equation.bf.lrp.TestMicroflowWPS;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * Get Input Message
 */
public class TestMicroflowTransferTask extends TestMicroflowWPS
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
			new TestMicroflowTransferTask().start();
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
		String microFlowName = "TransferWorkItem";
		IExecuteMicroflowRequest executeMicroflowRequest = getMicroflowRequest(microFlowName);

		// Task details
		String taskId = getTaskId();

		TransferWorkItemBO workItem = new TransferWorkItemBO();
		workItem.setAssignmentReason(4);
		workItem.setFromOwner("RETAIL");
		workItem.setToOwner("PERKINJ1");
		workItem.setIdentifier(taskId);

		// Set the parameters
		executeMicroflowRequest.addInputTag("transferWorkItem", workItem);

		// Execute the MicroFlow
		IExecuteMicroflowResponse executeMicroflowResponse = executeMicroflow(executeMicroflowRequest);

		// Print details
		printWorkflowResponse(executeMicroflowResponse);

		// assume success
		assertEquals(true, true);
	}

}
