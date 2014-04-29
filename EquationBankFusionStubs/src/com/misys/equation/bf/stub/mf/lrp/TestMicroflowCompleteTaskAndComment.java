package com.misys.equation.bf.stub.mf.lrp;

import java.util.Date;

import bf.com.misys.bankfusion.attributes.SetCommentsAndCompleteTaskBO;
import bf.com.misys.eqf.types.header.EqMessage;
import bf.com.misys.eqf.types.header.MessageStatus;
import bf.com.misys.eqf.types.header.TaskRsHeader;

import com.misys.equation.bf.lrp.TestMicroflowWPS;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

/**
 * Get Input Message
 */
public class TestMicroflowCompleteTaskAndComment extends TestMicroflowWPS
{
	public static final String CVS_REVISION = "$Revision: 1.1 $";

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
			new TestMicroflowCompleteTaskAndComment().start();
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
		String microFlowName = "SetCommentsAndCompleteTask";
		IExecuteMicroflowRequest executeMicroflowRequest = getMicroflowRequest(microFlowName);

		// Payload -------------------------

		// Service data
		// EQ_CMN_lp1EnterAnAccountLP1_SRV lp1ServiceData = new EQ_CMN_lp1EnterAnAccountLP1_SRV();
		// lp1ServiceData.setAB_accountBranch("9132");
		// lp1ServiceData.setAN_accountNumber("234567");
		// lp1ServiceData.setAS_accountSuffix("001");

		// Parameter
		EqMessage eqMessage = new EqMessage();
		eqMessage.setFirstLevelText("TEST");
		MessageStatus messageStatus = new MessageStatus();
		messageStatus.setOverallStatus("F");
		messageStatus.addEqMessages(eqMessage);

		TaskRsHeader output = new TaskRsHeader();
		output.setFunctionMode("A");
		output.setReason("Reason from SetCommentsAndCompleteTask");
		output.setTaskAction("AUTH");
		output.setReferToUserId("LIMA");
		output.setMessages(messageStatus);

		// EQ_CMN_creditRiskManagementCRM_SRV crmData = null;
		// crmData = new EQ_CMN_creditRiskManagementCRM_SRV();
		// crmData.setCURDATA_curdata("10");
		//
		// EQ_CMN_enhancedFeesAndChargesAC2_SRV efcData = null;
		// efcData = new EQ_CMN_enhancedFeesAndChargesAC2_SRV();
		// efcData.setADDS_adds("ADDS");
		// efcData.setAREF_aref("AREF_aref");

		// output.setServiceData(lp1ServiceData);
		// output.setCrmData(crmData);
		// output.setEfcData(efcData);

		Object payload = output;

		// Task details
		String taskId = getTaskId();

		// Payload -------------------------

		SetCommentsAndCompleteTaskBO workItem = new SetCommentsAndCompleteTaskBO();
		workItem.setComments("comment from SetCommentAndComplete " + (new Date()).getTime());
		workItem.setTkiid(taskId);
		workItem.setPayload(payload);

		// Set the parameters
		executeMicroflowRequest.addInputTag("setCommentsAndCompleteTaskBO", workItem);

		// Execute the MicroFlow
		IExecuteMicroflowResponse executeMicroflowResponse = executeMicroflow(executeMicroflowRequest);

		// Print details
		printWorkflowResponse(executeMicroflowResponse);

		// assume success
		assertEquals(true, true);
	}

}
