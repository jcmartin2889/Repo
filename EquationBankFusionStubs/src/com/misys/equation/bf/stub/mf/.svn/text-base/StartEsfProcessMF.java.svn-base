package com.misys.equation.bf.stub.mf;

import bf.com.misys.eqf.types.header.EqMessage;
import bf.com.misys.eqf.types.header.EsfKey;
import bf.com.misys.eqf.types.header.StartEsfRqProcessHeader;
import bf.com.misys.eqf.types.header.StartEsfRsProcessHeader;
import bf.com.misys.eqf.types.header.TaskBasicDetails;
import bf.com.misys.eqf.types.header.TaskEsfRqHeader;

import com.misys.equation.bankfusion.lrp.engine.TaskEngineToolbox;
import com.misys.equation.bankfusion.tools.LRPToolbox;
import com.misys.equation.bf.TestMicroflow;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * Get Input Message
 */
public class StartEsfProcessMF extends TestMicroflow
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
			new StartEsfProcessMF().start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Test data
	 */
	public void test() throws Exception
	{
		// Retrieve the MicroFlow
		String microFlowName = "EQ_CMN_StartEsfProcess_SRV";
		IExecuteMicroflowRequest executeMicroflowRequest = getMicroflowRequest(microFlowName);

		EsfKey esfKey = new EsfKey();
		esfKey.setOptionId("ALZ");
		esfKey.setUserId("EQCHINESE");
		esfKey.setTransactionId("A2010.07.23 17:30:52 656");
		esfKey.setSessionId("SESSIONID");

		TaskBasicDetails basicDetails = new TaskBasicDetails();
		basicDetails.setBfeqType(TaskEngineToolbox.BFEQ_TYPE);
		basicDetails.setOptionId("ALZ");
		basicDetails.setTaskType(TaskEngineToolbox.TASK_TYPE_ESF);
		basicDetails.setZone("SLOUGH1-AL9");

		TaskEsfRqHeader taskEsfRqHeader = LRPToolbox.getRequestEsfPayload("ALZ", TaskEngineToolbox.TASK_TYPE_ESF, "SLOUGH1", "AL9");
		taskEsfRqHeader.setEsfKey(esfKey);
		taskEsfRqHeader.setServiceData(esfKey);
		taskEsfRqHeader.setBasicDetail(basicDetails);
		taskEsfRqHeader.setSystemId("SLOUGH1");
		taskEsfRqHeader.setUnitId("AL9");

		EqMessage eqMessage = new EqMessage();
		eqMessage.setFirstLevelText("XXXX");
		eqMessage.setEqMessageId("KSM1234");
		eqMessage.setEqMessageParameter1("eqMessageParameter1");

		taskEsfRqHeader.setCrmData(eqMessage);
		taskEsfRqHeader.setEfcData(null);
		taskEsfRqHeader.getMessages().addEqMessages(eqMessage);

		StartEsfRqProcessHeader input = new StartEsfRqProcessHeader();
		input.setTaskEsfRqHeader(taskEsfRqHeader);
		// input.setServiceName(TaskEngineToolbox.getInstance().getEqDefaultEsfProcess());
		input.setServiceName("BFEQEsfInterfaceExport1_BFEQEsfInterfaceHttpService");

		// Set the parameters
		executeMicroflowRequest.addInputTag("input", input);

		// Execute the MicroFlow
		IExecuteMicroflowResponse executeMicroflowResponse = executeMicroflow(executeMicroflowRequest);

		StartEsfRsProcessHeader output = (StartEsfRsProcessHeader) executeMicroflowResponse.getOutputTags().get("output");

		System.out.println(output.getProcessId());

		// assume success
		assertEquals(output.getProcessId().trim().length() > 0, true);
	}
}
