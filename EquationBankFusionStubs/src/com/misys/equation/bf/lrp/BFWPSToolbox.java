package com.misys.equation.bf.lrp;

import org.exolab.castor.types.AnyNode;

import bf.com.ibm.xmlns.prod.websphere.bpc_commontypes._7._0.QueryResultRowType;
import bf.com.ibm.xmlns.prod.websphere.bpc_commontypes._7._0.QueryResultSetType;
import bf.com.ibm.xmlns.prod.websphere.bpc_commontypes._7._0.UserDataType;
import bf.com.misys.bankfusion.workflow.attributes.BasicTaskAttributes;
import bf.com.misys.bankfusion.workflow.attributes.CommentBO;
import bf.com.misys.bankfusion.workflow.attributes.CustomProperty;
import bf.com.misys.bankfusion.workflow.attributes.TaskDetailBO;
import bf.com.misys.bankfusion.workflow.attributes.TaskInstance;
import bf.com.misys.bankfusion.workflow.attributes.User;
import bf.com.misys.bankfusion.workflow.attributes.WorkflowResponse;
import bf.com.misys.bankfusion.workflow.attributes.WorkflowTaskAttributes;

public class BFWPSToolbox
{
	/**
	 * Print details for User Data Type
	 * 
	 * @param userDataType
	 *            - the user data type
	 */
	public static void printUserDataType(UserDataType userDataType)
	{
		AnyNode anyNode = (AnyNode) userDataType.getAnyObject();
		System.out.println(anyNode);
	}

	public static void printTaskInstance(TaskInstance taskInstance)
	{
		System.out.println(taskInstance.getAssignmentType());
		System.out.println(taskInstance.getTaskDefinition());
		System.out.println(taskInstance.getTaskDefinitionNamespace());
		System.out.println(taskInstance.getTaskStartTime());
		System.out.println(taskInstance.getTaskState());
		System.out.println(taskInstance.getTaskName());
		System.out.println(taskInstance.getTaskExpiryDuration());
		System.out.println(taskInstance.getTaskStarter());
		System.out.println(taskInstance.getTaskOwner());
		System.out.println(taskInstance.getTaskTemplateID());
		System.out.println(taskInstance.getTaskTemplateName());
		System.out.println(taskInstance.getTaskDeletionTime());
		System.out.println(taskInstance.getTaskInputType());
		System.out.println(taskInstance.getFirstActivationTime());
		System.out.println(taskInstance.getParentContextID());
	}

	public static void printWorkflowTaskAttributes(WorkflowTaskAttributes data)
	{
		System.out.println(data.getBasicTaskAttributes().getTaskID());
		System.out.println(data.getBasicTaskAttributes().getDescription());
		System.out.println(data.getBasicTaskAttributes().getProcessInstanceID());
		System.out.println(data.getBasicTaskAttributes().getTaskName());
		System.out.println(data.getBasicTaskAttributes().getTaskType());
		System.out.println(data.getAdditionalTaskAttributes().getActivityID());
		System.out.println(data.getAdditionalTaskAttributes().getEventNumber());
		System.out.println(data.getAdditionalTaskAttributes().getActivatedDate());
		System.out.println(data.getCustomTaskAttributes().getTaskCustomProp1());
		System.out.println(data.getCustomTaskAttributes().getTaskCustomProp2());
		System.out.println(data.getCustomTaskAttributes().getTaskCustomProp3());
		System.out.println(data.getCustomTaskAttributes().getTaskCustomProp4());
		System.out.println(data.getCustomTaskAttributes().getTaskCustomProp5());

	}
	public static void printCustomProperties(CustomProperty[] customProperties)
	{
		for (CustomProperty type : customProperties)
		{
			System.out.println(type.getName() + " =" + type.getValue());
		}
	}

	public static void printGetComment(CommentBO[] comments)
	{
		for (CommentBO comment : comments)
		{
			System.out.println(comment.getTkiid() + " =" + comment.getTimestamp() + " = " + comment.getUser() + " = "
							+ comment.getComment());
		}
	}

	public static void printGetTaskList(TaskDetailBO[] taskDetails)
	{
		for (TaskDetailBO task : taskDetails)
		{
			System.out.println(task.getId() + " =" + task.getName() + " =" + task.getOriginator() + " = " + task.getOwner() + " = "
							+ task.getState() + " = " + task.getDescription() + " = " + task.getBfTaskCustom1() + " = "
							+ task.getBfTaskCustom2() + " = " + task.getBfTaskCustom3() + " = " + task.getBfTaskCustom4() + " = "
							+ task.getBfTaskCustom5());
		}
	}

	public static void printGetBasicTaskAttributesList(BasicTaskAttributes[] taskDetails)
	{
		for (BasicTaskAttributes task : taskDetails)
		{
			System.out.println(task.getTaskID() + " =" + task.getTaskName() + " =" + task.getTaskOriginator() + " = "
							+ task.getTaskStatus() + " = " + task.getDescription());
		}
	}
	public static void printGetAssignees(User[] users)
	{
		for (User user : users)
		{
			System.out.println(user.getUser());
		}
	}

	/**
	 * Print query result
	 * 
	 * @param queryResult
	 */
	public static void printQueryResultSet(QueryResultSetType queryResult)
	{
		for (QueryResultRowType row : queryResult.getResult().getQueryResultRow())
		{
			String[] rowValues = row.getValue();
			for (Object value : rowValues)
			{
				System.out.print(value == null ? "null, " : value.toString() + " , ");
			}
			System.out.println("");
		}
	}

	public static void printWorkflowResponse(WorkflowResponse workflowResponse)
	{
		System.out.println("Successful? [" + workflowResponse.getResult().booleanValue() + "]");
	}
}
