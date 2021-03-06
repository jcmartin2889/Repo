package com.misys.equation.bankfusion.lrp.bean;

import java.util.Date;

import bf.com.misys.bankfusion.workflow.attributes.CommentBO;

import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;

/*************************************************************************************
 * Program History
 * 
 * <pre>
 * Project Name : BankFusion Equation Long Running Processes 
 * Version : BFEQ phase 4 
 * Package Name : com.misys.equation.bankfusion.lrp.bean 
 * Program ID : TaskComment.java 
 * Program Description : A bean class containing detail of a Comment 
 * Environment : Java 1.5
 * Author : rpatrici 
 * Creation Date : 09 August 2011
 * 
 * Modification History : Version Date Person Name Remarks
 * 
 * Copyright(C) 2011-Misys Plc. All Rights Reserved.
 *</pre>
 *************************************************************************************/

public class TaskComment
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TaskComment.java 12483 2012-01-31 17:43:52Z desilva1 $";

	// Log
	private static final EquationLogger LOG = new EquationLogger(TaskComment.class);

	private String tkiid;
	private Date timestamp;
	private String user;
	private String comment;

	/**
	 * Constructor
	 * 
	 * @param taskComment
	 *            - the comment
	 */
	public TaskComment(CommentBO taskComment)
	{
		setUser(taskComment.getUser());
		setComment(taskComment.getComment());
		setTkiid(taskComment.getTkiid());
		setTimestamp(taskComment.getTimestamp());
	}

	/**
	 * Return the task id
	 * 
	 * @return the task id
	 */
	public String getTkiid()
	{
		return tkiid;
	}

	/**
	 * Set the task id
	 * 
	 * @param tkiid
	 *            - the task id
	 */
	public void setTkiid(String tkiid)
	{
		this.tkiid = tkiid;
	}

	/**
	 * Return the timestamp
	 * 
	 * @return the timestamp
	 */
	public Date getTimestamp()
	{
		return timestamp;
	}

	/**
	 * Set the timestamp
	 * 
	 * @param timestamp
	 *            - the timestamp
	 */
	public void setTimestamp(Date timestamp)
	{
		this.timestamp = timestamp;
	}

	/**
	 * Return the user
	 * 
	 * @return the user
	 */
	public String getUser()
	{
		return user;
	}

	/**
	 * Set the user
	 * 
	 * @param user
	 *            - the user
	 */
	public void setUser(String user)
	{
		this.user = user;
	}

	/**
	 * Return the comment
	 * 
	 * @return the comment
	 */
	public String getComment()
	{
		return comment;
	}

	/**
	 * Set the comment
	 * 
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment)
	{
		this.comment = comment;
	}

}
