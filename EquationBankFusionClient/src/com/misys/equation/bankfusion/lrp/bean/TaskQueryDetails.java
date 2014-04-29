package com.misys.equation.bankfusion.lrp.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bf.com.misys.bankfusion.workflow.attributes.BasicTaskAttributes;
import bf.com.misys.bankfusion.workflow.attributes.BasicTaskAttributesList;

/*************************************************************************************
 * Program History
 * 
 * <pre>
 * Project Name : BankFusion Equation Long Running Processes 
 * Version : BFEQ phase 4 
 * Package Name : com.misys.equation.bankfusion.lrp.bean 
 * Program ID : TaskQueryDetails.java 
 * Program Description : Generate list of TaskQueryDetail based on the QueryResultSetType 
 * Environment : Java 1.5
 * Author : rpatrici 
 * Creation Date : 09 August 2011
 * 
 * Modification History : Version Date Person Name Remarks
 * 
 * Copyright(C) 2011-Misys Plc. All Rights Reserved.
 * </pre>
 *************************************************************************************/

public class TaskQueryDetails
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TaskQueryDetails.java 16894 2013-08-05 08:41:07Z perkinj1 $";

	private ArrayList<TaskQueryDetail> taskDetails;

	/**
	 * Construct the task details
	 * 
	 * @param taskList
	 *            - the list of tasks from BankFusion
	 */
	public TaskQueryDetails(BasicTaskAttributesList taskList)
	{
		taskDetails = new ArrayList<TaskQueryDetail>();
		for (BasicTaskAttributes taskDetail : taskList.getBasicTaskAttributesList())
		{
			taskDetails.add(new TaskQueryDetail(taskDetail));
		}
	}

	/**
	 * Return an iterator for the list of tasks
	 * 
	 * @return an iterator for the list of tasks
	 */
	public Iterator<TaskQueryDetail> iterator()
	{
		return taskDetails.iterator();
	}

	/**
	 * Return the list of tasks
	 * 
	 * @return the list of tasks
	 */
	public List<TaskQueryDetail> getTaskDetails()
	{
		return taskDetails;
	}

}
