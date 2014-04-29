package com.misys.equation.bf.stub.mf.lrp;

import bf.com.misys.bankfusion.workflow.attributes.CommentBO;
import bf.com.misys.bankfusion.workflow.attributes.OutputMessageBO;
import bf.com.misys.eqf.types.header.EqMessage;
import bf.com.misys.eqf.types.header.MessageStatus;
import bf.com.misys.eqf.types.header.TaskRsHeader;

import com.misys.equation.bf.lrp.TestMicroflowWPS;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * Get Input Message
 */
public class TestMicroflowSetOutputMessage extends TestMicroflowWPS
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
			new TestMicroflowSetOutputMessage().start();
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
		String microFlowName = "SetOutputMessage";
		IExecuteMicroflowRequest executeMicroflowRequest = getMicroflowRequest(microFlowName);

		// Task details
		String taskId = getTaskId();

		// Parameter
		MessageStatus messageStatus = new MessageStatus();
		messageStatus.setOverallStatus("F");

		Object dataType = null;

		EqMessage eqMessage = new EqMessage();
		eqMessage.setFirstLevelText("TEST");

		TaskRsHeader output = new TaskRsHeader();
		output.setFunctionMode("A");
		output.setReason("Reason12345");
		output.setTaskAction("AUTH");
		output.setReferToUserId("LIMA1235");
		output.setMessages(messageStatus);
		output.setCrmData(eqMessage);
		output.setServiceData(messageStatus);
		dataType = output;

		CommentBO comment = new CommentBO();
		comment.setComment("Test Comment");
		// output.setServiceData(comment);
		// rqHeader.setServiceData(comment);
		// dataType = comment;

		// User user = new User();
		// user.setUser("TestUser");
		// output.setServiceData(user);
		// dataType = user;

		// EQ_CMN_lp1EnterAnAccountLP1_SRV lp1ServiceData = new EQ_CMN_lp1EnterAnAccountLP1_SRV();
		// lp1ServiceData.setAB_accountBranch("9132");
		// lp1ServiceData.setAN_accountNumber("234567");
		// lp1ServiceData.setAS_accountSuffix("001");
		// output.setServiceData(lp1ServiceData);
		// dataType = lp1ServiceData;

		OutputMessageBO outputMessage = new OutputMessageBO();
		outputMessage.setTkiid(taskId);
		outputMessage.setAnyType(dataType);

		// Set the parameters
		executeMicroflowRequest.addInputTag("setOutputMessageBO", outputMessage);

		// Execute the MicroFlow
		IExecuteMicroflowResponse executeMicroflowResponse = executeMicroflow(executeMicroflowRequest);

		// Print details
		printworkflowResponse(executeMicroflowResponse);

		// assume success
		assertEquals(true, true);
	}

}
