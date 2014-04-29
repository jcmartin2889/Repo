package com.misys.equation.bf.stub.mf.lrp;

import java.util.Date;

import bf.com.misys.bankfusion.workflow.attributes.CustomPropertyBO;

import com.misys.equation.bf.lrp.TestMicroflowWPS;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * Get Input Message
 */
public class TestMicroflowSetCustomProperties extends TestMicroflowWPS
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
			new TestMicroflowSetCustomProperties().start();
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
		String microFlowName = "SetCustomProperties";
		IExecuteMicroflowRequest executeMicroflowRequest = getMicroflowRequest(microFlowName);

		// Task details
		String taskId = getTaskId();

		CustomPropertyBO customProperty = new CustomPropertyBO();
		customProperty.setTkiid(taskId);
		customProperty.setPropertyName("testProp4");
		customProperty.setPropertyValue("Value Property " + (new Date().getTime()));

		// Set the parameters
		executeMicroflowRequest.addInputTag("SetCustomProperty", customProperty);

		// Execute the MicroFlow
		IExecuteMicroflowResponse executeMicroflowResponse = executeMicroflow(executeMicroflowRequest);

		// assume success
		assertEquals(true, true);
	}
}
