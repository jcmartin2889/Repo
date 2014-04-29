package com.misys.equation.bf.stub.mf.lrp;

import java.util.Date;

import bf.com.misys.bankfusion.attributes.SetCommentsAndTransferTaskBO;

import com.misys.equation.bf.lrp.TestMicroflowWPS;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * Get Input Message
 */
public class TestMicroflowStartLrpProcess extends TestMicroflowWPS
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
			new TestMicroflowStartLrpProcess().start();
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
		// String wsdl = getWPS_HTM_WSDL();

		SetCommentsAndTransferTaskBO workItem = new SetCommentsAndTransferTaskBO();
		workItem.setAssignmentReason(4);
		workItem.setFromOwner("RETAIL");
		workItem.setToOwner("perkinj1");
		workItem.setComments("comment from SetCommentAndTransfer " + (new Date()).getTime());
		workItem.setTkiid(taskId);

		// Set the parameters
		executeMicroflowRequest.addInputTag("setCommentsAndTransferTaskBO", workItem);
		// executeMicroflowRequest.addInputTag("serviceName", wsdl);

		// Execute the MicroFlow
		IExecuteMicroflowResponse executeMicroflowResponse = executeMicroflow(executeMicroflowRequest);

		// Print details
		printworkflowResponse(executeMicroflowResponse);

		// assume success
		assertEquals(true, true);
	}

}
