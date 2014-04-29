package com.misys.equation.bf.lrp;

import bf.com.ibm.xmlns.prod.websphere.bpc_commontypes._7._0.QueryResultSetType;
import bf.com.ibm.xmlns.prod.websphere.bpc_commontypes._7._0.UserDataType;
import bf.com.misys.bankfusion.workflow.attributes.BasicTaskAttributesList;
import bf.com.misys.bankfusion.workflow.attributes.CommentBOList;
import bf.com.misys.bankfusion.workflow.attributes.CustomProperties;
import bf.com.misys.bankfusion.workflow.attributes.TaskDetailBOList;
import bf.com.misys.bankfusion.workflow.attributes.TaskInstance;
import bf.com.misys.bankfusion.workflow.attributes.UserList;
import bf.com.misys.bankfusion.workflow.attributes.WorkflowResponse;
import bf.com.misys.bankfusion.workflow.attributes.WorkflowTaskAttributes;

import com.misys.equation.bf.TestMicroflow;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;

public abstract class TestMicroflowWPS extends TestMicroflow
{
	private static final String wsdl5 = "http://localhost:9087/HTMJAXWSAPI/HTMJAXWSService/WEB-INF/wsdl/com/ibm/task/api/jaxws/HTMJAXWSService.wsdl";

	// /**
	// * Return the WPS HTM WSDL
	// *
	// * @return the WPS HTM WSDL
	// */
	// public String getWPS_HTM_WSDL()
	// {
	// return Constant.WPS_HTM_SERVICE_NAME;
	// }
	//
	// /**
	// * Return the BF Task WSDL
	// *
	// * @return the BF Task WSDL
	// */
	// public String getBF_Task_WSDL()
	// {
	// return Constant.BF_TASK_SERVICE_NAME;
	// }
	//
	// /**
	// * Return the BF Human WSDL
	// *
	// * @return the BF Human WSDL
	// */
	// public String getBF_Human_WSDL()
	// {
	// return Constant.BF_HUMAN_SERVICE_NAME;
	// }

	/**
	 * Return the Task Id
	 * 
	 * @return the Task Id
	 */
	public String getTaskId()
	{
		return Constant.taskId;
	}

	/**
	 * Return the Process Id
	 * 
	 * @return the Process Id
	 */
	public String getProcessId()
	{
		return Constant.processId;
	}

	/**
	 * Print details for ClaimTask
	 * 
	 * @param executeMicroflowResponse
	 *            - the MicroFlow response
	 */
	public void printClaimTaskResponse(IExecuteMicroflowResponse executeMicroflowResponse)
	{
		// Specific return type
		System.out.println("Return details of ClaimTask: ");
		Object o = executeMicroflowResponse.getOutputTags().get("WorkflowResponse");

		WorkflowResponse workflowResponse = (WorkflowResponse) executeMicroflowResponse.getOutputTags().get("WorkflowResponse");
		BFWPSToolbox.printWorkflowResponse(workflowResponse);
	}

	/**
	 * Print details for CancelClaim
	 * 
	 * @param executeMicroflowResponse
	 *            - the MicroFlow response
	 */
	public void printCancelClaimResponse(IExecuteMicroflowResponse executeMicroflowResponse)
	{
		// Specific return type
		System.out.println("Return details of CancelClaim: ");
		Object o = executeMicroflowResponse.getOutputTags().get("WorkflowResponse");

		WorkflowResponse workflowResponse = (WorkflowResponse) executeMicroflowResponse.getOutputTags().get("WorkflowResponse");
		BFWPSToolbox.printWorkflowResponse(workflowResponse);
	}

	/**
	 * Print details for responses using a WorkflowResponse tag
	 * 
	 * @param executeMicroflowResponse
	 *            - the MicroFlow response
	 */
	public void printWorkflowResponse(IExecuteMicroflowResponse executeMicroflowResponse)
	{
		// Specific return type
		System.out.println("Return details of type WorkflowResponse: ");
		WorkflowResponse workflowResponse = (WorkflowResponse) executeMicroflowResponse.getOutputTags().get("WorkflowResponse");
		BFWPSToolbox.printWorkflowResponse(workflowResponse);
	}

	/**
	 * Print details for responses using a workflowResponse tag
	 * 
	 * @param executeMicroflowResponse
	 *            - the MicroFlow response
	 */
	public void printworkflowResponse(IExecuteMicroflowResponse executeMicroflowResponse)
	{
		// Specific return type
		System.out.println("Return details of type workflowResponse: ");
		WorkflowResponse workflowResponse = (WorkflowResponse) executeMicroflowResponse.getOutputTags().get("workflowResponse");
		BFWPSToolbox.printWorkflowResponse(workflowResponse);
	}

	/**
	 * Print details for Get Input Message
	 * 
	 * @param executeMicroflowResponse
	 *            - the MicroFlow response
	 */
	public void printGetInputMessage(IExecuteMicroflowResponse executeMicroflowResponse)
	{
		// Specific return type
		System.out.println("Return details of Get Input Message: ");
		UserDataType userDataType = (UserDataType) executeMicroflowResponse.getOutputTags().get("OutputObject");
		BFWPSToolbox.printUserDataType(userDataType);
	}

	/**
	 * Print details for Get Input Message
	 * 
	 * @param executeMicroflowResponse
	 *            - the MicroFlow response
	 */
	public void printGetCustomProperties(IExecuteMicroflowResponse executeMicroflowResponse)
	{
		// Specific return type
		System.out.println("Return details of Get Custom Properties: ");
		CustomProperties customProperties = (CustomProperties) executeMicroflowResponse.getOutputTags().get("customProperties");
		BFWPSToolbox.printCustomProperties(customProperties.getCustomProperty());
	}

	/**
	 * Print details for Get Output Message
	 * 
	 * @param executeMicroflowResponse
	 *            - the MicroFlow response
	 */
	public void printGetOutputMessage(IExecuteMicroflowResponse executeMicroflowResponse)
	{
		// Specific return type
		System.out.println("Return details of Get Output Message: ");
		UserDataType userDataType = (UserDataType) executeMicroflowResponse.getOutputTags().get("userDataType");
		BFWPSToolbox.printUserDataType(userDataType);
	}

	/**
	 * Print details for Get Task Data
	 * 
	 * @param executeMicroflowResponse
	 *            - the MicroFlow response
	 */
	public void printGetTaskData(IExecuteMicroflowResponse executeMicroflowResponse)
	{
		// Specific return type
		System.out.println("Return details of Get Task Data: ");
		TaskInstance taskInstance = (TaskInstance) executeMicroflowResponse.getOutputTags().get("TaskInstance");
		BFWPSToolbox.printTaskInstance(taskInstance);
	}

	/**
	 * Print details for Get Input Message
	 * 
	 * @param executeMicroflowResponse
	 *            - the MicroFlow response
	 */
	public void printGetComments(IExecuteMicroflowResponse executeMicroflowResponse)
	{
		// Specific return type
		System.out.println("Return details of Get Comments: ");
		CommentBOList commentBOList = (CommentBOList) executeMicroflowResponse.getOutputTags().get("CommentBOList");
		BFWPSToolbox.printGetComment(commentBOList.getCommentBO());
	}

	/**
	 * Print details for Get Input Message
	 * 
	 * @param executeMicroflowResponse
	 *            - the MicroFlow response
	 */
	public void printGetTaskList(IExecuteMicroflowResponse executeMicroflowResponse)
	{
		// Specific return type
		System.out.println("Return details of Get Task List: ");
		TaskDetailBOList taskDetailBOList = (TaskDetailBOList) executeMicroflowResponse.getOutputTags().get("TaskDetailsBOList");
		BFWPSToolbox.printGetTaskList(taskDetailBOList.getTaskDetailBO());
	}

	/**
	 * Print details for Get Input Message
	 * 
	 * @param executeMicroflowResponse
	 *            - the MicroFlow response
	 */
	public void printGetBasicTaskAttributesList(IExecuteMicroflowResponse executeMicroflowResponse)
	{
		// Specific return type
		System.out.println("Return details of Get Basic Task Attributes List: ");
		BasicTaskAttributesList list = (BasicTaskAttributesList) executeMicroflowResponse.getOutputTags().get("basicTaskList");
		BFWPSToolbox.printGetBasicTaskAttributesList(list.getBasicTaskAttributesList());
	}

	/**
	 * Print details for Get Input Message
	 * 
	 * @param executeMicroflowResponse
	 *            - the MicroFlow response
	 */
	public void printQueryTask(IExecuteMicroflowResponse executeMicroflowResponse)
	{
		// Specific return type
		System.out.println("Return details of Get Query Task: ");

		if (executeMicroflowResponse.getOutputTags().get("QueryObject") instanceof TaskDetailBOList)
		{
			TaskDetailBOList taskDetailBOList = (TaskDetailBOList) executeMicroflowResponse.getOutputTags().get("QueryObject");
			BFWPSToolbox.printGetTaskList(taskDetailBOList.getTaskDetailBO());
			return;
		}

		QueryResultSetType queryTask = (QueryResultSetType) executeMicroflowResponse.getOutputTags().get("QueryObject");
		BFWPSToolbox.printQueryResultSet(queryTask);
	}

	/**
	 * Print details for Get Input Message
	 * 
	 * @param executeMicroflowResponse
	 *            - the MicroFlow response
	 */
	public void printGetAssignees(IExecuteMicroflowResponse executeMicroflowResponse)
	{
		// Specific return type
		System.out.println("Return details of Get Assignees: ");
		UserList userList = (UserList) executeMicroflowResponse.getOutputTags().get("UserList");
		BFWPSToolbox.printGetAssignees(userList.getUsers());
	}

	/**
	 * Print details for Get Task Data
	 * 
	 * @param executeMicroflowResponse
	 *            - the MicroFlow response
	 */
	public void printGetWorkflowTaskAttributes(IExecuteMicroflowResponse executeMicroflowResponse)
	{
		// Specific return type
		System.out.println("Return details of GetWorkflowTaskAttributes: ");
		WorkflowTaskAttributes data = (WorkflowTaskAttributes) executeMicroflowResponse.getOutputTags().get(
						"workflowTaskAttributes");
		BFWPSToolbox.printWorkflowTaskAttributes(data);
	}
}
