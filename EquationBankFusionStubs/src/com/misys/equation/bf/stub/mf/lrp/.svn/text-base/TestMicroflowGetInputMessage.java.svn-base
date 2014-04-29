package com.misys.equation.bf.stub.mf.lrp;

import bf.com.ibm.xmlns.prod.websphere.bpc_commontypes._7._0.UserDataType;
import bf.com.misys.eqf.types.header.TaskEsfRqHeader;
import bf.com.misys.eqf.types.header.TaskRqHeader;
import bf.com.misys.eqf.types.header.TaskRsHeader;

import com.misys.equation.bf.lrp.TestMicroflowWPS;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * Get Input Message
 */
public class TestMicroflowGetInputMessage extends TestMicroflowWPS
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
			new TestMicroflowGetInputMessage().start();
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
		String microFlowName = "GetInputMessage";
		IExecuteMicroflowRequest executeMicroflowRequest = getMicroflowRequest(microFlowName);

		// Task details
		String taskId = getTaskId();
		String mappingClassName = "bf.com.misys.eqf.types.header.TaskRqHeader";

		// Set the parameters
		executeMicroflowRequest.addInputTag("tkiid", taskId);
		executeMicroflowRequest.addInputTag("MappingClassName", mappingClassName);

		// Execute the MicroFlow
		IExecuteMicroflowResponse executeMicroflowResponse = executeMicroflow(executeMicroflowRequest);

		// Convert manually
		Object convertedObject = executeMicroflowResponse.getOutputTags().get("OutputObject");

		if (convertedObject instanceof UserDataType)
		{
			convertedObject = converter(convertedObject, mappingClassName);

			// Print details
			printGetInputMessage(executeMicroflowResponse);
		}

		if (convertedObject instanceof TaskRqHeader)
		{
			TaskRqHeader task = (TaskRqHeader) convertedObject;
			System.out.println(task.getBasicDetail().getZone());
			System.out.println(task.getBasicDetail().getOptionId());
			System.out.println(task.getBasicDetail().getTaskType());

			if (task.getServiceData() != null)
			{
				System.out.println(task.getServiceData().getClass().getSimpleName());
				System.out.println(task.getServiceData().toString());
				// Map<Object, Object> map = LRPToolbox.constructListFromElement((AnyNode) task.getServiceData());
				// System.out.println(map);
			}
		}
		else if (convertedObject instanceof TaskRsHeader)
		{
			TaskRsHeader task = (TaskRsHeader) convertedObject;
			System.out.println(task.getFunctionMode());
			System.out.println(task.getReason());
			System.out.println(task.getTaskAction());
			System.out.println(task.getReferToUserId());
		}
		else if (convertedObject instanceof TaskEsfRqHeader)
		{
			TaskEsfRqHeader task = (TaskEsfRqHeader) convertedObject;
			System.out.println(task.getSupervisorId());
			System.out.println(task.getMessages());
		}
		else if (convertedObject instanceof UserDataType)
		{
			assertEquals(true, false);
		}

		// assume success
		assertEquals(true, true);
	}
	public Object converter(Object object, String className)
	{
		String converterMF = "ConvertClientObjectMicroflow";
		IExecuteMicroflowRequest converterMicroflowRequest = getMicroflowRequest(converterMF);
		converterMicroflowRequest.addInputTag("ClientObject", object);
		converterMicroflowRequest.addInputTag("mappingClassName", className);
		IExecuteMicroflowResponse converterMicroflowResponse = executeMicroflow(converterMicroflowRequest);
		Object convertedObject = converterMicroflowResponse.getOutputTags().get("ConvertedObject");
		System.out.println("Success " + convertedObject.getClass().getName());
		System.out.println(convertedObject);
		return convertedObject;
	}
}
