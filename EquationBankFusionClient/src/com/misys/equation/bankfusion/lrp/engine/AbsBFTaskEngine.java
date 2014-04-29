package com.misys.equation.bankfusion.lrp.engine;

import java.util.HashMap;

import bf.com.misys.bankfusion.attributes.SetCommentsAndCompleteTaskBO;
import bf.com.misys.bankfusion.attributes.SetCommentsAndTransferTaskBO;
import bf.com.misys.bankfusion.workflow.attributes.BasicTaskAttributesList;
import bf.com.misys.bankfusion.workflow.attributes.CancelClaim;
import bf.com.misys.bankfusion.workflow.attributes.CommentBOList;
import bf.com.misys.bankfusion.workflow.attributes.CustomProperties;
import bf.com.misys.bankfusion.workflow.attributes.GetBasicTaskAttributesListInputBO;
import bf.com.misys.bankfusion.workflow.attributes.OutputMessageBO;
import bf.com.misys.bankfusion.workflow.attributes.Tkiid_PropertyValueBO;
import bf.com.misys.bankfusion.workflow.attributes.TransferWorkItemBO;
import bf.com.misys.bankfusion.workflow.attributes.UserList;
import bf.com.misys.bankfusion.workflow.attributes.WorkflowTaskAttributes;
import bf.com.misys.eqf.types.header.StartEsfRqProcessHeader;
import bf.com.misys.eqf.types.header.StartEsfRsProcessHeader;
import bf.com.misys.eqf.types.header.StartLRPProcessRqHeader;
import bf.com.misys.eqf.types.header.StartLRPProcessRsHeader;
import bf.com.misys.eqf.types.header.TaskEsfRqHeader;
import bf.com.misys.eqf.types.header.TaskEsfRsHeader;
import bf.com.misys.eqf.types.header.TaskRqHeader;
import bf.com.misys.eqf.types.header.TaskRsHeader;

import com.misys.equation.bankfusion.lrp.bean.TaskComments;
import com.misys.equation.bankfusion.lrp.bean.TaskCustomProperties;
import com.misys.equation.bankfusion.lrp.bean.TaskCustomProperty;
import com.misys.equation.bankfusion.lrp.bean.TaskDetail;
import com.misys.equation.bankfusion.lrp.bean.TaskQueryDetails;
import com.misys.equation.bankfusion.lrp.bean.TaskUsers;
import com.misys.equation.bankfusion.tools.BFToolbox;
import com.misys.equation.bankfusion.tools.LRPToolbox;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;

/**
 * The AbsBFTaskEngine is an implementation of ITaskEngine, which calls the BankFusion Microflow API to interact with the LRP
 * processing.
 * 
 * @author yzobdabu
 */
public abstract class AbsBFTaskEngine
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbsBFTaskEngine.java 16960 2013-08-13 13:31:20Z perkinj1 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(AbsBFTaskEngine.class);

	/**
	 * BF Microflows
	 */
	public static final String BF_MF_GET_TASK_DATA = "GetWorkflowTaskAttributes";
	public static final String BF_MF_GET_TASKS_LIST = "GetBasicTaskAttributesList";
	public static final String BF_MF_CLAIM_TASK = "ClaimTask";
	public static final String BF_MF_CANCEL_CLAIMED_TASK = "CancelClaim";
	public static final String BF_MF_COMPLETE_TASK = "CompleteTask";
	public static final String BF_MF_SET_OUTPUT_TAG = "SetOutputMessage";
	public static final String BF_MF_SET_COMMENTS = "SetComments";
	public static final String BF_MF_GET_PAYLOAD_FOR_TASK = "GetInputMessage";
	public static final String BF_MF_GET_COMMENTS = "GetComments";
	public static final String BF_MF_RE_REFER_TASK = "TransferWorkItem";
	public static final String BF_MF_RE_REFER_TASK_AND_COMMENT = "SetCommentsAndTransferTask";
	public static final String BF_MF_START_LRP_ESF_PROCESS = "";
	public static final String BF_MF_GET_ASSIGNEES = "GetAssignees";
	public static final String BF_MF_GET_CUSTOM_PROPERTIES = "GetCustomProperties";
	public static final String BF_MF_COMMENT_COMPLETE_TASK = "SetCommentsAndCompleteTask";

	public static final String BFEQ_MF_STARTESFPROCESS = "EQ_CMN_StartEsfProcess_SRV";
	public static final String BFEQ_MF_STARTLRPPROCESS = "EQ_CMN_StartLRPProcess_SRV";

	public static final String COMMENT_CUSTOM_PROP_TKIID = "tkiid";
	public static final String COMMENT_CUSTOM_PROP_COMMENT = "comment";
	public static final String COMMENT_CUSTOM_PROP_TIME = "timestamp";
	public static final int COMMENT_CUSTOM_PROP_MAXLENGTH = 250;

	/** The TaskEngineToolbox instance */
	protected TaskEngineToolbox taskEngineToolboxInstance = null;

	/** The BF Toolbox instance */
	protected BFToolbox bfToolbox = null;

	/** The zone */
	private String zone = null;

	/** First time call */
	private static boolean flagSetOutputMessage = true;
	private static boolean flagSetComments = true;

	/**
	 * Constructor
	 */
	public AbsBFTaskEngine(String zone)
	{
		this.taskEngineToolboxInstance = TaskEngineToolbox.getInstance();
		this.bfToolbox = new BFToolbox();
		this.zone = zone;
	}

	/**
	 * Call to microflow. This is abstract to differentiate between command API call vs MFExecuter call
	 * 
	 * @param microflowName
	 *            - the microflow name
	 * @param inputParms
	 *            - the input parameters
	 * 
	 * @return the output parameters
	 */
	public abstract HashMap<?, ?> callMicroflow(String microflowName, HashMap<?, ?> inputParms) throws EQException;

	/**
	 * Return input parameter for GetTaskList MF
	 * 
	 * @param zone
	 *            - zone filtering
	 * @param filterTaskType
	 *            - task type filtering
	 * @param filterOID
	 *            - task option id filtering
	 * 
	 * @return the input parameter for GetTaskList MF
	 * 
	 * @throws EQException
	 */
	protected HashMap<String, Object> getTaskListsInput(String zone, String filterTaskType, String filterOID) throws EQException
	{
		// build input parameters
		HashMap<String, Object> inputParms = new HashMap<String, Object>(5);
		String queryCondition = taskEngineToolboxInstance.getTaskListQuery(zone, filterTaskType, filterOID);
		GetBasicTaskAttributesListInputBO x = new GetBasicTaskAttributesListInputBO();
		x.setQueryCondition(queryCondition);
		inputParms.put("getBasicTaskAttributesListInput", x);

		return inputParms;
	}

	/**
	 * Return the task list output of the GetTaskList MF
	 * 
	 * @param outputParms
	 *            - the output parameter of the GetTaskList MF
	 * 
	 * @return the task detail
	 */
	protected TaskQueryDetails getTaskListsOutput(HashMap<?, ?> outputParms)
	{
		// return task list details
		BasicTaskAttributesList taskDetailBOList = (BasicTaskAttributesList) outputParms.get("basicTaskList");
		TaskQueryDetails taskQueryDetails = null;
		if (taskDetailBOList != null)
		{
			taskQueryDetails = new TaskQueryDetails(taskDetailBOList);
		}

		return taskQueryDetails;
	}

	/**
	 * Return input parameter for ClaimTask MF
	 * 
	 * @param taskId
	 *            - the task Id
	 * 
	 * @return the input parameter for ClaimTask MF
	 * 
	 * @throws EQException
	 */
	protected HashMap<String, Object> claimTaskInput(String taskId) throws EQException
	{
		HashMap<String, Object> inputParms = new HashMap<String, Object>(5);
		taskEngineToolboxInstance.getWpsCommonInputParameters(inputParms);
		inputParms.put("tkiid", taskId);
		return inputParms;
	}

	/**
	 * Return input parameter for CancelClaim MF
	 * 
	 * @param taskId
	 *            - the task Id
	 * 
	 * @return the input parameter for CancelClaim MF
	 * 
	 * @throws EQException
	 */
	protected HashMap<String, Object> cancelClaimInput(String taskId) throws EQException
	{
		HashMap<String, Object> inputParms = new HashMap<String, Object>(5);
		taskEngineToolboxInstance.getWpsCommonInputParameters(inputParms);

		CancelClaim cancelClaim = new CancelClaim();
		cancelClaim.setTkiid(taskId);
		cancelClaim.setKeepTaskData(false);

		// Set the parameters
		inputParms.put("CancelClaim", cancelClaim);

		return inputParms;
	}

	/**
	 * Return input parameter for CompleteTask MF
	 * 
	 * @param taskId
	 *            - the task Id
	 * 
	 * @return the input parameter for CompleteTask MF
	 * 
	 * @throws EQException
	 */
	protected HashMap<String, Object> completeTaskInput(String taskId) throws EQException
	{
		HashMap<String, Object> inputParms = new HashMap<String, Object>(5);
		taskEngineToolboxInstance.getWpsCommonInputParameters(inputParms);
		inputParms.put("tkid", taskId);
		return inputParms;
	}

	/**
	 * Return input parameter for GetInputMessage MF
	 * 
	 * @param taskId
	 *            - the task Id
	 * @param mappingClassName
	 *            - the type of the payload
	 * 
	 * @return the input parameter for GetInputMessage MF
	 * 
	 * @throws EQException
	 */
	protected HashMap<String, Object> getInputMessageInput(String taskId, String mappingClassName) throws EQException
	{
		HashMap<String, Object> inputParms = new HashMap<String, Object>(5);
		taskEngineToolboxInstance.getWpsCommonInputParameters(inputParms);
		inputParms.put("tkiid", taskId);
		inputParms.put("MappingClassName", mappingClassName);
		return inputParms;
	}

	/**
	 * Call the ConvertClientObjectMicroflow to convert the AnyType object into an actual object
	 * 
	 * @param object
	 *            - the AnyType object to be converted
	 * @param className
	 *            - the expected class name of the object
	 * 
	 * @return the actual object
	 * 
	 * @throws EQException
	 */
	public Object convertClientObject(Object object, String className) throws EQException
	{
		HashMap<String, Object> inputParms = new HashMap<String, Object>(5);
		inputParms.put("ClientObject", object);
		inputParms.put("mappingClassName", className);

		String microflowName = "ConvertClientObjectMicroflow";
		HashMap<?, ?> result = callMicroflow(microflowName, inputParms);

		Object convertedObject = result.get("ConvertedObject");
		return convertedObject;
	}

	/**
	 * Return the task detail
	 * 
	 * @param taskId
	 *            - the task Id
	 * 
	 * @throws EQException
	 */
	public TaskDetail getTaskData(String taskId) throws EQException
	{
		// build input parameters
		HashMap<String, Object> inputParms = new HashMap<String, Object>(5);
		taskEngineToolboxInstance.getWpsCommonInputParameters(inputParms);
		inputParms.put("tkiid", taskId);

		// call microflow
		HashMap<?, ?> result = callMicroflow(BF_MF_GET_TASK_DATA, inputParms);

		// return the task detail
		TaskDetail taskDetail = null;
		if (result != null)
		{
			WorkflowTaskAttributes taskInstance = (WorkflowTaskAttributes) result.get("workflowTaskAttributes");
			if (taskInstance != null && taskInstance.getBasicTaskAttributes() != null
							&& taskInstance.getBasicTaskAttributes().getTaskID() != null)
			{
				taskDetail = new TaskDetail(taskInstance);
			}
		}
		return taskDetail;
	}

	/**
	 * Return the user's list of task
	 */
	public TaskQueryDetails getTaskLists() throws EQException
	{
		TaskQueryDetails taskQueryDetails = getTaskLists(zone, null, null);
		return taskQueryDetails;
	}

	/**
	 * Return the user's list of task
	 */
	public TaskQueryDetails getAlertTaskLists() throws EQException
	{
		TaskQueryDetails taskQueryDetails = getTaskLists(zone, ITaskEngine.TASK_TYPE_ESF, null);
		return taskQueryDetails;
	}

	/**
	 * Return the user's list of task with filtering
	 * 
	 * @param filterTaskType
	 *            - task type filtering
	 * @param filterOID
	 *            - task option id filtering
	 * 
	 * @throws EQException
	 */
	protected TaskQueryDetails getTaskLists(String zone, String filterTaskType, String filterOID) throws EQException
	{
		// build input parameters
		HashMap<String, Object> inputParms = getTaskListsInput(zone, filterTaskType, filterOID);

		// call microflow
		HashMap<?, ?> result = callMicroflow(BF_MF_GET_TASKS_LIST, inputParms);

		// return task details
		TaskQueryDetails taskQueryDetails = getTaskListsOutput(result);
		return taskQueryDetails;
	}

	/**
	 * Claim a task
	 * 
	 * @param taskId
	 *            - the task id
	 * 
	 * @throws EQException
	 */
	public TaskRqHeader claimTask(String taskId) throws EQException
	{
		HashMap<String, Object> inputParms = claimTaskInput(taskId);
		HashMap<?, ?> result = callMicroflow(BF_MF_CLAIM_TASK, inputParms);

		return null;
	}

	/**
	 * Claim an ESF task
	 * 
	 * @param taskId
	 *            - the task id
	 * 
	 * @throws EQException
	 */
	public TaskEsfRqHeader claimEsfTask(String taskId) throws EQException
	{
		HashMap<String, Object> inputParms = claimTaskInput(taskId);
		HashMap<?, ?> result = callMicroflow(BF_MF_CLAIM_TASK, inputParms);

		return null;
	}

	/**
	 * Cancel a claimed task
	 * 
	 * @param taskId
	 *            - the task id
	 * 
	 * @throws EQException
	 */
	public void cancelClaim(String taskId) throws EQException
	{
		HashMap<String, Object> inputParms = cancelClaimInput(taskId);
		callMicroflow(BF_MF_CANCEL_CLAIMED_TASK, inputParms);
	}

	/**
	 * Complete task
	 * 
	 * @param taskId
	 *            - the task id
	 * @param payload
	 *            - the task payload
	 * @param comment
	 *            - comment
	 * 
	 * @throws EQException
	 */
	public void completeTask(String taskId, TaskRsHeader payload, String comment) throws EQException
	{
		compleTask1(taskId, payload, comment);
	}

	/**
	 * Complete an ESF task
	 * 
	 * @param taskId
	 *            - the task id
	 * @param payload
	 *            - the task payload
	 * @param comment
	 *            - comment
	 * 
	 * @throws EQException
	 */
	public void completEsfTask(String taskId, TaskEsfRsHeader payload, String comment) throws EQException
	{
		compleTask1(taskId, payload, comment);
	}

	/**
	 * Complete a task
	 * 
	 * @param taskId
	 *            - the task id
	 * @param payload
	 *            - the task payload
	 * @param comment
	 *            - comment
	 * 
	 * @throws EQException
	 */
	private void compleTask1(String taskId, Object payload, String comment) throws EQException
	{
		// Set the parameters
		SetCommentsAndCompleteTaskBO workItem = new SetCommentsAndCompleteTaskBO();
		workItem.setTkiid(taskId);
		workItem.setPayload(payload);

		// comment specified
		if (comment != null && comment.trim().length() > 0)
		{
			workItem.setComments(comment);
		}

		// complete the task
		HashMap<String, Object> inputParms = new HashMap<String, Object>(5);
		inputParms.put("setCommentsAndCompleteTaskBO", workItem);
		callMicroflow(BF_MF_COMMENT_COMPLETE_TASK, inputParms);
	}

	/**
	 * Set the output payload
	 * 
	 * @param taskId
	 *            - the task id
	 * @param payload
	 *            - the payload
	 * 
	 * @throws EQException
	 */
	private void setOutputPayload(String taskId, Object payload) throws EQException
	{
		HashMap<String, Object> inputParms = new HashMap<String, Object>(5);
		OutputMessageBO outputMessage = new OutputMessageBO();
		outputMessage.setTkiid(taskId);
		outputMessage.setAnyType(payload);
		inputParms.put("setOutputMessage", outputMessage);
		inputParms.put("serviceName", taskEngineToolboxInstance.getBfHumanTaskWrapperService());
		callMicroflow(BF_MF_SET_OUTPUT_TAG, inputParms);

		// delay
		if (flagSetOutputMessage)
		{
			delay(500);
			flagSetOutputMessage = false;
		}
	}

	/**
	 * Add comment to the task
	 * 
	 * @param taskId
	 *            - the task id
	 * @param comment
	 *            - the comment
	 * 
	 * @return true if comment has been added
	 * 
	 * @throws EQException
	 */
	private boolean addComment(String taskId, String comment) throws EQException
	{
		if (comment == null || comment.trim().length() == 0)
		{
			return false;
		}

		// Exceed comments?
		if (exceedComment(taskId, comment))
		{
			LOG.error("Exceeded comment length for task " + taskId);
			return false;
		}

		HashMap<String, Object> inputParms = new HashMap<String, Object>(5);
		Tkiid_PropertyValueBO propertyValues = new Tkiid_PropertyValueBO();
		propertyValues.setTkiid(taskId);
		propertyValues.setPropertyValue(comment);
		inputParms.put("PropertyValues", propertyValues);
		inputParms.put("serviceName", taskEngineToolboxInstance.getBfHumanTaskWrapperService());
		callMicroflow(BF_MF_SET_COMMENTS, inputParms);

		// delay
		if (flagSetComments)
		{
			delay(500);
			flagSetComments = false;
		}

		return true;
	}

	/**
	 * Return payload of the task
	 * 
	 * @param taskId
	 *            - the task id
	 * 
	 * @return the payload
	 * 
	 * @throws EQException
	 */
	public TaskRqHeader getPayloadForTask(String taskId) throws EQException
	{
		HashMap<String, Object> inputParms = getInputMessageInput(taskId, "bf.com.misys.eqf.types.header.TaskRqHeader");
		HashMap<?, ?> result = callMicroflow(BF_MF_GET_PAYLOAD_FOR_TASK, inputParms);
		TaskRqHeader convertedObject = (TaskRqHeader) result.get("OutputObject");

		// Initialise to default value if null
		if (convertedObject.isPerformUpdate() == null)
		{
			convertedObject.setPerformUpdate(false);
		}

		if (convertedObject.getFunctionMode() == null)
		{
			convertedObject.setFunctionMode("");
		}

		if (convertedObject.getTaskActionList() == null)
		{
			convertedObject.setTaskActionList("");
		}

		return convertedObject;
	}

	/**
	 * Return payload of the ESF task
	 * 
	 * @param taskId
	 *            - the task id
	 * 
	 * @return the payload
	 * 
	 * @throws EQException
	 */
	public TaskEsfRqHeader getPayloadForESFTask(String taskId) throws EQException
	{
		HashMap<String, Object> inputParms = getInputMessageInput(taskId, "bf.com.misys.eqf.types.header.TaskEsfRqHeader");
		HashMap<?, ?> result = callMicroflow(BF_MF_GET_PAYLOAD_FOR_TASK, inputParms);
		TaskEsfRqHeader convertedObject = (TaskEsfRqHeader) result.get("OutputObject");

		// Initialise to default value if null
		if (convertedObject.getCurrentFieldSet() == null)
		{
			convertedObject.setCurrentFieldSet("");
		}

		if (convertedObject.getCurrentScreenFieldSet() == null)
		{
			convertedObject.setCurrentScreenFieldSet("");
		}

		if (convertedObject.getSupervisorId() == null)
		{
			convertedObject.setSupervisorId("");
		}

		if (convertedObject.getSystemId() == null)
		{
			convertedObject.setSystemId("");
		}

		if (convertedObject.getUnitId() == null)
		{
			convertedObject.setUnitId("");
		}

		return convertedObject;
	}

	/**
	 * Return the comments
	 * 
	 * @param processId
	 *            - the process id
	 * 
	 * @return the list of comments
	 * 
	 * @throws EQException
	 */
	public TaskComments getComments(String processId) throws EQException
	{
		HashMap<String, Object> inputParms = new HashMap<String, Object>(5);
		inputParms.put("ServiceName", taskEngineToolboxInstance.getBfHumanTaskWrapperService());
		inputParms.put("ProcessIinstanceID", processId);
		inputParms.put("TimeOut", "50000");

		HashMap<?, ?> result = callMicroflow(BF_MF_GET_COMMENTS, inputParms);
		CommentBOList commentBOList = (CommentBOList) result.get("CommentBOList");

		TaskComments taskComments = new TaskComments(commentBOList);
		return taskComments;
	}

	/**
	 * Re-refer a task to another user with comment
	 * 
	 * @param taskId
	 *            - the task id to transfer
	 * @param fromUserId
	 *            - the current owner of the task
	 * @param toUserId
	 *            - the new user to transfer it to
	 * @param comment
	 *            - the comment
	 * 
	 * @throws EQException
	 */
	public void reReferTask(String taskId, String fromUserId, String toUserId, String comment) throws EQException
	{
		fromUserId = LRPToolbox.correctUserCase(fromUserId);
		toUserId = LRPToolbox.correctUserCase(toUserId);

		SetCommentsAndTransferTaskBO workItem = new SetCommentsAndTransferTaskBO();
		workItem.setAssignmentReason(4);
		workItem.setFromOwner(fromUserId);
		workItem.setToOwner(toUserId);
		workItem.setTkiid(taskId);

		if (comment != null && comment.trim().length() > 0)
		{
			workItem.setComments(comment);
		}

		// Set the parameters
		HashMap<String, Object> inputParms = new HashMap<String, Object>(5);
		inputParms.put("setCommentsAndTransferTaskBO", workItem);
		callMicroflow(BF_MF_RE_REFER_TASK_AND_COMMENT, inputParms);
	}

	/**
	 * Re-refer a task to another user
	 * 
	 * @param taskId
	 *            - the task id to transfer
	 * @param fromUserId
	 *            - the current owner of the task
	 * @param toUserId
	 *            - the new user to transfer it to
	 * 
	 * @throws EQException
	 */
	private void reReferTask(String taskId, String fromUserId, String toUserId) throws EQException
	{
		HashMap<String, Object> inputParms = new HashMap<String, Object>(5);
		taskEngineToolboxInstance.getWpsCommonInputParameters(inputParms);

		TransferWorkItemBO workItem = new TransferWorkItemBO();
		workItem.setAssignmentReason(4);
		workItem.setFromOwner(fromUserId);
		workItem.setToOwner(toUserId);
		workItem.setIdentifier(taskId);

		// Set the parameters
		inputParms.put("transferWorkItem", workItem);
		callMicroflow(BF_MF_RE_REFER_TASK, inputParms);
	}

	/**
	 * Start an ESF process
	 * 
	 * @param serviceName
	 *            - the webservice process URL
	 * @param payload
	 *            - the payload
	 * 
	 * @return the process id
	 * 
	 * @throws EQException
	 */
	public String startLrpEsfProcess(String serviceName, TaskEsfRqHeader payload) throws EQException
	{
		// when service name is not specified, then use default service name
		if (serviceName == null || serviceName.trim().length() == 0)
		{
			serviceName = taskEngineToolboxInstance.getEqDefaultEsfProcess();
		}

		HashMap<String, Object> inputParms = new HashMap<String, Object>(5);
		inputParms.put("serviceName", serviceName);

		StartEsfRqProcessHeader input = new StartEsfRqProcessHeader();
		input.setTaskEsfRqHeader(payload);
		input.setServiceName(serviceName);

		// Set the parameters
		inputParms.put("input", input);
		HashMap<?, ?> result = callMicroflow(BFEQ_MF_STARTESFPROCESS, inputParms);

		// Output
		StartEsfRsProcessHeader output = (StartEsfRsProcessHeader) result.get("output");
		LOG.info("Process id created: " + output.getProcessId());

		return output.getProcessId();
	}

	/**
	 * Return the list of users who can own the task
	 * 
	 * @param taskId
	 *            - the task id
	 * 
	 * @return the list of users who can own the task
	 * 
	 * @throws EQException
	 */
	public TaskUsers getAssignees(String taskId) throws EQException
	{
		HashMap<String, Object> inputParms = new HashMap<String, Object>(5);
		inputParms.put("TaskIID", taskId);

		HashMap<?, ?> result = callMicroflow(BF_MF_GET_ASSIGNEES, inputParms);

		UserList userList = (UserList) result.get("UserList");
		TaskUsers taskUsers = new TaskUsers(userList);
		return taskUsers;
	}

	/**
	 * Determine if user can own the task
	 * 
	 * @param taskId
	 *            - the task id
	 * @param userId
	 *            - the user id to be verified
	 * 
	 * @return true if the user can own the task
	 * 
	 * @throws EQException
	 */
	public Boolean isUserCanClaimTask(String taskId, String userId) throws EQException
	{
		TaskUsers taskUsers = getAssignees(taskId);
		if (taskUsers.isExists(userId))
		{
			return true;
		}

		return false;
	}

	/**
	 * Add delay
	 * 
	 * @param delay
	 *            - delay in milliseconds
	 */
	private void delay(int delay)
	{
		try
		{
			Thread.sleep(delay);
		}
		catch (InterruptedException e)
		{
		}
	}

	/**
	 * This will return the custom properties defined for the task
	 * 
	 * @param taskId
	 *            - the task id
	 * 
	 * @return the list of custom properties
	 * 
	 * @throws EQException
	 */
	public TaskCustomProperties getCustomProperties(String taskId) throws EQException
	{
		HashMap<String, Object> inputParms = new HashMap<String, Object>(5);
		inputParms.put("tkiid", taskId);
		inputParms.put("ServiceName", taskEngineToolboxInstance.getHtmJaxWsService());

		HashMap<?, ?> result = callMicroflow(BF_MF_GET_CUSTOM_PROPERTIES, inputParms);

		CustomProperties customProperties = (CustomProperties) result.get("customProperties");
		TaskCustomProperties taskCustomProperties = new TaskCustomProperties(customProperties);
		return taskCustomProperties;
	}

	/**
	 * Determine if user can still add more comments. <br>
	 * 
	 * This is provided as currently BF has implemented task comments as a custom property of the task, and this is limited to a
	 * maximum length of 254
	 * 
	 * @param taskId
	 *            - the task id
	 * @param comment
	 *            - the comment to be added
	 * 
	 * @return true if user can still add more comments
	 * 
	 * @throws EQException
	 */
	private boolean exceedComment(String taskId, String comment) throws EQException
	{
		TaskCustomProperties taskCustomProperties = getCustomProperties(taskId);

		TaskCustomProperty taskCustomProperty = taskCustomProperties.rtvValue(COMMENT_CUSTOM_PROP_TKIID);
		if (taskCustomProperty != null)
		{
			if (exceedCustomProperty(taskCustomProperty.getValue(), 40))
			{
				return true;
			}
		}

		taskCustomProperty = taskCustomProperties.rtvValue(COMMENT_CUSTOM_PROP_TIME);
		if (taskCustomProperty != null)
		{
			if (exceedCustomProperty(taskCustomProperty.getValue(), 40))
			{
				return true;
			}
		}

		taskCustomProperty = taskCustomProperties.rtvValue(COMMENT_CUSTOM_PROP_COMMENT);
		if (taskCustomProperty != null)
		{
			if (exceedCustomProperty(taskCustomProperty.getValue(), comment.length()))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Determine if can add the specified length to the custom property.
	 * 
	 * @param value
	 *            - the current value
	 * @param length
	 *            - length of the text to be appended
	 * 
	 * @return true if it will exceed the custom property length
	 */
	private boolean exceedCustomProperty(String value, int length)
	{
		return value.length() + length > COMMENT_CUSTOM_PROP_MAXLENGTH;
	}

	/**
	 * Determine if user is currently the owner of the task
	 * 
	 * @param taskId
	 *            - the task id
	 * @param userId
	 *            - the user id to be verified
	 * 
	 * @return true if the user is currently the owner of the task
	 * 
	 * @throws EQException
	 */
	public Boolean isTaskOwnedByUser(String taskId, String userId) throws EQException
	{
		TaskDetail taskDetail = getTaskData(taskId);
		if (taskDetail != null && taskDetail.getOwner() != null && taskDetail.isClaimed()
						&& taskDetail.getOwner().equalsIgnoreCase(userId))
		{
			return true;
		}

		return false;
	}

	/**
	 * Start a LRP process
	 * 
	 * @param serviceName
	 *            - the webservice process
	 * @param operationName
	 *            - the operation name
	 * @param payload
	 *            - the payload
	 * 
	 * @return the output of the LRP process
	 * 
	 * @throws EQException
	 */
	public StartLRPProcessRsHeader startLrpProcess(String serviceName, String operationName, Object payload) throws EQException
	{
		StartLRPProcessRqHeader startLrpProcessRqHeader = new StartLRPProcessRqHeader();
		startLrpProcessRqHeader.setServiceName(serviceName);
		startLrpProcessRqHeader.setOperationName(operationName);
		startLrpProcessRqHeader.setPayload(payload);

		// Set the parameters
		HashMap<String, Object> inputParms = new HashMap<String, Object>(5);
		inputParms.put("startLrpProcessRqHeader", startLrpProcessRqHeader);

		// Call the Microflow
		HashMap<?, ?> result = callMicroflow(BFEQ_MF_STARTLRPPROCESS, inputParms);

		// Output
		StartLRPProcessRsHeader output = (StartLRPProcessRsHeader) result.get("startLrpProcessRsHeader");

		// Return output
		return output;
	}

	/**
	 * Return the zone
	 * 
	 * @return the zone
	 */
	public String getZone()
	{
		return zone;
	}

}
