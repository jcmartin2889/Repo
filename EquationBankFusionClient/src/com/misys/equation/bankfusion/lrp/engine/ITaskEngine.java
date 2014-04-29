package com.misys.equation.bankfusion.lrp.engine;

import java.util.HashMap;

import bf.com.misys.eqf.types.header.StartLRPProcessRsHeader;
import bf.com.misys.eqf.types.header.TaskEsfRqHeader;
import bf.com.misys.eqf.types.header.TaskEsfRsHeader;
import bf.com.misys.eqf.types.header.TaskRqHeader;
import bf.com.misys.eqf.types.header.TaskRsHeader;

import com.misys.equation.bankfusion.lrp.bean.TaskComments;
import com.misys.equation.bankfusion.lrp.bean.TaskCustomProperties;
import com.misys.equation.bankfusion.lrp.bean.TaskDetail;
import com.misys.equation.bankfusion.lrp.bean.TaskQueryDetails;
import com.misys.equation.bankfusion.lrp.bean.TaskUsers;
import com.misys.equation.common.internal.eapi.core.EQException;

/**
 * This interface will expose functionalities required for LRP processing
 * 
 * @author yzobdabu
 * 
 */
public interface ITaskEngine
{

	/** Entry task */
	public static final String TASK_TYPE_ENTRY = "ENTRY";

	/** Authorisation task */
	public static final String TASK_TYPE_AUTH = "AUTH";

	/** Entry + Authorisation task */
	public static final String TASK_TYPE__EAUTH = "EAUTH";

	/** ESF task */
	public static final String TASK_TYPE_ESF = "ESF";

	/**
	 * This will retrieve the task details of the specified task id
	 * 
	 * @param taskId
	 *            - the task id to retrieve
	 * 
	 * @return the TaskDetail of the specified task id
	 * 
	 * @throws Exception
	 */
	public TaskDetail getTaskData(String taskId) throws EQException;

	/**
	 * This will retrieve tasks assigned or can be assigned to a user
	 * 
	 * @return the Task Details
	 * 
	 * @throws Exception
	 */
	public TaskQueryDetails getTaskLists() throws EQException;

	/**
	 * This will retrieve tasks that should be alerted to the user
	 * 
	 * @return the Task Details
	 * 
	 * @throws Exception
	 */
	public TaskQueryDetails getAlertTaskLists() throws EQException;

	/**
	 * This will claim a task
	 * 
	 * @param taskId
	 *            - the task id to claim
	 * 
	 * @return the Payload details
	 * 
	 * @throws Exception
	 */
	public TaskRqHeader claimTask(String taskId) throws EQException;

	/**
	 * This will claim an ESF Task
	 * 
	 * @param taskId
	 *            - the task id to claim
	 * 
	 * @return the Payload details
	 * 
	 * @throws Exception
	 */
	public TaskEsfRqHeader claimEsfTask(String taskId) throws EQException;

	/**
	 * This will cancel a claimed task
	 * 
	 * @param taskId
	 *            - the task id
	 * 
	 * @throws Exception
	 */
	public void cancelClaim(String taskId) throws EQException;

	/**
	 * This will complete a task
	 * 
	 * @param taskId
	 *            - the task id
	 * @param payload
	 *            - the payload
	 * @param comment
	 *            - the comment
	 * 
	 * @throws Exception
	 */
	public void completeTask(String taskId, TaskRsHeader payload, String comment) throws EQException;

	/**
	 * This will complete an ESF task
	 * 
	 * @param taskId
	 *            - the task id
	 * @param payload
	 *            - the payload
	 * @param comment
	 *            - the comment
	 * 
	 * @throws Exception
	 */
	public void completEsfTask(String taskId, TaskEsfRsHeader payload, String comment) throws EQException;

	/**
	 * This will retrieve the payload for the task
	 * 
	 * @param taskId
	 *            - the task id
	 * 
	 * @return - the returned payload
	 * 
	 * @throws Exception
	 */
	public TaskRqHeader getPayloadForTask(String taskId) throws EQException;

	/**
	 * This will retrieve the payload for the task
	 * 
	 * @param taskId
	 *            - the task id
	 * 
	 * @return - the returned payload
	 * 
	 * @throws Exception
	 */
	public TaskEsfRqHeader getPayloadForESFTask(String taskId) throws EQException;

	/**
	 * This will retrieve all the comments for the given process
	 * 
	 * @param processId
	 *            - the process id
	 * 
	 * @return the Task Comments
	 * 
	 * @throws Exception
	 */
	public TaskComments getComments(String processId) throws EQException;

	/**
	 * This will re-refer a task to another user
	 * 
	 * @param taskId
	 *            - the task Id
	 * @param fromUserId
	 *            - the current user who owns the task
	 * @param toUserId
	 *            - the new user
	 * @param comment
	 *            - the comment
	 * 
	 * @throws Exception
	 */
	public void reReferTask(String taskId, String fromUserId, String toUserId, String comment) throws EQException;

	/**
	 * This will be responsible for starting LRP processing
	 * 
	 * @param serviceName
	 *            - the webservice process URL
	 * @param payload
	 *            - the payload
	 * 
	 * @throws Exception
	 */
	public String startLrpEsfProcess(String serviceName, TaskEsfRqHeader payload) throws EQException;

	/**
	 * This will determine list of potential users who can claim a task
	 * 
	 * @param taskId
	 *            - the task id
	 * 
	 * @return - the list of users
	 * 
	 * @throws Exception
	 */
	public TaskUsers getAssignees(String taskId) throws EQException;

	/**
	 * This will determine if the user can claim the specified task id and will use the <code>getAssignees</code> method to
	 * determine if the user can claim the task
	 * 
	 * @param taskId
	 *            - the task id
	 * @param userId
	 *            - the user id
	 * 
	 * @return true if user can claim the task
	 * 
	 * @throws Exception
	 */
	public Boolean isUserCanClaimTask(String taskId, String userId) throws EQException;

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
	public TaskCustomProperties getCustomProperties(String taskId) throws EQException;

	/**
	 * This will determine if the user is currently the owner of the task
	 * 
	 * @param taskId
	 *            - the task id
	 * @param userId
	 *            - the user id
	 * 
	 * @return true if the user is currently the owner of the task
	 * 
	 * @throws Exception
	 */
	public Boolean isTaskOwnedByUser(String taskId, String userId) throws EQException;

	/**
	 * Return the zone
	 * 
	 * @return the zone
	 */
	public String getZone();

	/**
	 * Start a LRP process
	 * 
	 * @param serviceName
	 *            - the webservice process URL
	 * @param operationName
	 *            - the operation name
	 * @param payload
	 *            - the payload
	 * 
	 * @return the output of the LRP process
	 * 
	 * @throws EQException
	 */
	public StartLRPProcessRsHeader startLrpProcess(String serviceName, String operationName, Object payload) throws EQException;

	/**
	 * Call to microflow
	 * 
	 * @param microflowName
	 *            - the microflow name
	 * @param inputParms
	 *            - the input parameters
	 * 
	 * @return the output parameters
	 */
	public HashMap<?, ?> callMicroflow(String microflowName, HashMap<?, ?> inputParms) throws EQException;

}
