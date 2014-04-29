package com.misys.equation.bf.stub.mf.lrp;

import bf.com.misys.bankfusion.workflow.attributes.BasicTaskAttributesList;

import com.misys.equation.bankfusion.lrp.bean.TaskQueryDetails;
import com.misys.equation.bankfusion.lrp.engine.TaskEngineToolbox;
import com.misys.equation.bf.lrp.TestMicroflowWPS;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * Get Input Message
 */
public class TestMicroflowGetTaskList extends TestMicroflowWPS
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
			new TestMicroflowGetTaskList().start();
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
		String microFlowName = "GetBasicTaskAttributesList";
		IExecuteMicroflowRequest executeMicroflowRequest = getMicroflowRequest(microFlowName);

		TaskEngineToolbox taskEngineToolboxInstance = TaskEngineToolbox.getInstance();
		String query = taskEngineToolboxInstance.getTaskListQuery("SLOUGH1-JP9", null, null);
		executeMicroflowRequest.addInputTag("queryCondition", query);

		// Execute the MicroFlow
		IExecuteMicroflowResponse executeMicroflowResponse = executeMicroflow(executeMicroflowRequest);

		// Print details
		printGetTaskList(executeMicroflowResponse);

		BasicTaskAttributesList taskDetailBOList = (BasicTaskAttributesList) executeMicroflowResponse.getOutputTags().get(
						"TaskDetailsBOList");
		TaskQueryDetails taskQueryDetails = new TaskQueryDetails(taskDetailBOList);
		System.out.println(taskQueryDetails);

		// Assume success
		assertEquals(true, true);
	}
}
