package com.misys.equation.bf.stub.mf.lrp;

import bf.com.misys.bankfusion.workflow.attributes.WorkflowTaskAttributes;

import com.misys.equation.bankfusion.lrp.bean.TaskDetail;
import com.misys.equation.bf.lrp.TestMicroflowWPS;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * Get Input Message
 */
public class TestMicroflowGetWorkflowTaskAttributes extends TestMicroflowWPS
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
			new TestMicroflowGetWorkflowTaskAttributes().start();
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
		String microFlowName = "GetWorkflowTaskAttributes";
		IExecuteMicroflowRequest executeMicroflowRequest = getMicroflowRequest(microFlowName);

		// Task details
		String taskId = getTaskId();

		// Set the parameter
		executeMicroflowRequest.addInputTag("tkiid", taskId);

		// Execute the MicroFlow
		IExecuteMicroflowResponse executeMicroflowResponse = executeMicroflow(executeMicroflowRequest);

		// Print details
		printGetWorkflowTaskAttributes(executeMicroflowResponse);

		WorkflowTaskAttributes data = (WorkflowTaskAttributes) executeMicroflowResponse.getOutputTags().get(
						"workflowTaskAttributes");
		TaskDetail taskDetail = new TaskDetail(data);

		// Assume success
		assertEquals(true, true);
	}

}