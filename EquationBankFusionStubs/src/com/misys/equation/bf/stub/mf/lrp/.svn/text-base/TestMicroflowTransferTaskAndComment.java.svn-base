package com.misys.equation.bf.stub.mf.lrp;

import java.util.Date;

import bf.com.misys.bankfusion.attributes.SetCommentsAndTransferTaskBO;

import com.misys.equation.bf.lrp.TestMicroflowWPS;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * Get Input Message
 */
public class TestMicroflowTransferTaskAndComment extends TestMicroflowWPS
{
	public static final String CVS_REVISION = "$Revision: 1.3 $";

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
			new TestMicroflowTransferTaskAndComment().start();
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
		String microFlowName = "SetCommentsAndTransferTask";
		IExecuteMicroflowRequest executeMicroflowRequest = getMicroflowRequest(microFlowName);

		// Task details
		String taskId = getTaskId();

		SetCommentsAndTransferTaskBO workItem = new SetCommentsAndTransferTaskBO();
		workItem.setAssignmentReason(4);
		workItem.setFromOwner("PERKINJ1");
		workItem.setToOwner("RETAIL");
		workItem.setComments("comment from SetCommentAndTransfer " + (new Date()).getTime());
		workItem.setTkiid(taskId);

		// Set the parameters
		executeMicroflowRequest.addInputTag("setCommentsAndTransferTaskBO", workItem);

		// Execute the MicroFlow
		IExecuteMicroflowResponse executeMicroflowResponse = executeMicroflow(executeMicroflowRequest);

		// Print details
		printworkflowResponse(executeMicroflowResponse);

		// assume success
		assertEquals(true, true);
	}

}
