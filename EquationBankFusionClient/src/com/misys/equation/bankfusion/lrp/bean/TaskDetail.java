package com.misys.equation.bankfusion.lrp.bean;

import bf.com.misys.bankfusion.workflow.attributes.WorkflowTaskAttributes;

import com.misys.equation.bankfusion.lrp.engine.TaskEngineToolbox;

/*************************************************************************************
 * Program History
 * 
 * <pre>
 * Project Name : BankFusion Equation Long Running Processes 
 * Version : BFEQ phase 4 
 * Package Name : com.misys.equation.bankfusion.lrp.bean 
 * Program ID : TaskDetail.java 
 * Program Description : A bean class containing details of task as needed by Equation. 
 * Environment : Java 1.5
 * Author : rpatrici 
 * Creation Date : 09 August 2011
 * 
 * Modification History : Version Date Person Name Remarks
 * 
 * Copyright(C) 2011-Misys Plc. All Rights Reserved.
 * </pre>
 *************************************************************************************/

public class TaskDetail
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TaskDetail.java 16960 2013-08-13 13:31:20Z perkinj1 $";

	private String tkiid;
	private String templateName;
	private String originator;
	private String state;
	private String owner;
	private String parent;
	private boolean isClaimed;

	/**
	 * Construct
	 * 
	 * @param taskInstance
	 *            - the task instance
	 */
	public TaskDetail(WorkflowTaskAttributes taskInstance)
	{
		setTkiid(taskInstance.getBasicTaskAttributes().getTaskID());
		setTemplateName(taskInstance.getAdditionalTaskAttributes().getTaskTemplateName());
		setOriginator(taskInstance.getBasicTaskAttributes().getTaskOriginator());
		setState(taskInstance.getBasicTaskAttributes().getTaskStatus());
		setOwner(null);
		setParent(taskInstance.getBasicTaskAttributes().getProcessInstanceID());
		if (state == null)
		{
			state = "";
		}
		setClaimed(state.equals(TaskEngineToolbox.CLAIMED_STATE));
		if (isClaimed)
		{
			setOwner(taskInstance.getBasicTaskAttributes().getOwner());
		}
	}

	/**
	 * Get Task Id property
	 * 
	 * @return the property Task Id
	 */
	public String getTkiid()
	{
		return tkiid;
	}

	/**
	 * Sets the property Task Id
	 * 
	 * @param tkiid
	 */
	public void setTkiid(String tkiid)
	{
		this.tkiid = tkiid;
	}

	/**
	 * Get Template Name property
	 * 
	 * @return the property Template Name
	 */
	public String getTemplateName()
	{
		return templateName;
	}

	/**
	 * Sets the property Template Name
	 * 
	 * @param templateName
	 */
	public void setTemplateName(String templateName)
	{
		this.templateName = templateName;
	}

	/**
	 * Get User property
	 * 
	 * @return the property User
	 */
	public String getOriginator()
	{
		return originator;
	}

	/**
	 * Sets the User property
	 * 
	 * @param originator
	 */
	public void setOriginator(String originator)
	{
		this.originator = originator;
	}

	/**
	 * Get State property
	 * 
	 * @return the property state
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * Sets the State property
	 * 
	 * @param state
	 */
	public void setState(String state)
	{
		this.state = state;
	}

	/**
	 * Return the owner
	 * 
	 * @return the owner
	 */
	public String getOwner()
	{
		return owner;
	}

	/**
	 * Set the owner
	 * 
	 * @param owner
	 *            - the owner
	 */
	public void setOwner(String owner)
	{
		this.owner = owner;
	}

	/**
	 * Return the owning process
	 * 
	 * @return the owning process
	 */
	public String getParent()
	{
		return parent;
	}

	/**
	 * Set the owning process
	 * 
	 * @param parent
	 *            - the owning process
	 */
	public void setParent(String parent)
	{
		this.parent = parent;
	}

	/**
	 * Determine if task is claimed
	 * 
	 * @return true if task is claimed
	 */
	public boolean isClaimed()
	{
		return isClaimed;
	}

	/**
	 * Set if task is claimed
	 * 
	 * @param isClaimed
	 *            - true if task is claimed
	 */
	public void setClaimed(boolean isClaimed)
	{
		this.isClaimed = isClaimed;
	}

	/**
	 * Determine if task is already complete
	 * 
	 * @return true if task is already complete
	 */
	public boolean isTaskCompleted()
	{
		return !(state.equals(TaskEngineToolbox.CLAIMED_STATE) || state.equals(TaskEngineToolbox.READY_STATE));
	}

}
