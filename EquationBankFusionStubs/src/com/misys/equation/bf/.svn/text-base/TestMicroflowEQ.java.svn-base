package com.misys.equation.bf;

import bf.com.misys.eqf.types.header.EqMessage;
import bf.com.misys.eqf.types.header.RqHeader;
import bf.com.misys.eqf.types.header.ServiceRqHeader;
import bf.com.misys.eqf.types.header.ServiceRsHeader;
import bf.com.misys.eqf.types.header.UiControlRq;

import com.misys.equation.common.test.TestEnvironment;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

public abstract class TestMicroflowEQ extends TestMicroflow
{
	// Default Equation details
	private String sessionId = "BankFusion";

	/**
	 * Return the Function Header
	 * 
	 * @return the Function Header
	 */
	public ServiceRqHeader getServiceRqHeader(String optionId)
	{
		ServiceRqHeader serviceHeader = new ServiceRqHeader();
		serviceHeader.setOptionId("");
		serviceHeader.setReference("");
		serviceHeader.setServiceMode("");
		serviceHeader.setSupervisor("");
		serviceHeader.setUiControlRq(new UiControlRq());
		serviceHeader.setRqHeader(new RqHeader());

		RqHeader rqHeader = serviceHeader.getRqHeader();
		rqHeader.setSystemId(TestEnvironment.getTestEnvironment().getSystem());
		rqHeader.setUnitId(TestEnvironment.getTestEnvironment().getUnit());
		rqHeader.setUserId("");
		rqHeader.setSessionId(sessionId);
		serviceHeader.setOptionId(optionId);
		return serviceHeader;
	}
	/**
	 * Execute an Equation service
	 * 
	 * @param microFlowName
	 *            - the MicroFlow name
	 * @param serviceRqHeader
	 *            - the Service Header
	 * @param serviceData
	 *            - the Function Data
	 * 
	 * @return the MicroFlow response
	 */
	public IExecuteMicroflowResponse executeEquationService(String microFlowName, ServiceRqHeader serviceRqHeader,
					Object serviceData)
	{
		// Set the parameters
		IExecuteMicroflowRequest executeMicroflowRequest = getMicroflowRequest(microFlowName);
		executeMicroflowRequest.addInputTag("ServiceHeader", serviceRqHeader);
		executeMicroflowRequest.addInputTag("ServiceData", serviceData);

		// Execute the MicroFlow
		IExecuteMicroflowResponse executeMicroflowResponse = executeMicroflow(executeMicroflowRequest);

		// Retrieve respond
		ServiceRsHeader serviceRsHeader = (ServiceRsHeader) executeMicroflowResponse.getOutputTags().get("OutputServiceHeader");
		Object serviceRsData = executeMicroflowResponse.getOutputTags().get("OutputServiceData");

		// Print messages
		System.out.println("Messages returned:");
		for (EqMessage eqMessage : serviceRsHeader.getRsHeader().getStatus().getEqMessages())
		{
			System.out.println(eqMessage.getFormattedMessage());
		}

		return executeMicroflowResponse;
	}
}
