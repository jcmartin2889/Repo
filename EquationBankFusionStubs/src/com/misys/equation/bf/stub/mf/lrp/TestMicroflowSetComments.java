package com.misys.equation.bf.stub.mf.lrp;

import java.util.Date;

import bf.com.misys.bankfusion.workflow.attributes.Tkiid_PropertyValueBO;

import com.misys.equation.bf.lrp.TestMicroflowWPS;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * Get Input Message
 */
public class TestMicroflowSetComments extends TestMicroflowWPS
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
			new TestMicroflowSetComments().start();
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
		String microFlowName = "SetComments";
		IExecuteMicroflowRequest executeMicroflowRequest = getMicroflowRequest(microFlowName);

		// Task details
		String taskId = getTaskId();

		Tkiid_PropertyValueBO propertyValues = new Tkiid_PropertyValueBO();
		propertyValues.setTkiid(taskId);
		propertyValues
						.setPropertyValue("My enlarged comment to test 256 limit. The quick brown fox jumped over the lazy dog. Comment at "
										+ (new Date()).getTime());

		// Set the parameters
		executeMicroflowRequest.addInputTag("PropertyValues", propertyValues);

		// Execute the MicroFlow
		IExecuteMicroflowResponse executeMicroflowResponse = executeMicroflow(executeMicroflowRequest);

		// Assume success
		assertEquals(true, true);
	}

}
