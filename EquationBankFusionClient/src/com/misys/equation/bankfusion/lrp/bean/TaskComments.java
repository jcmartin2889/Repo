package com.misys.equation.bankfusion.lrp.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import bf.com.misys.bankfusion.workflow.attributes.CommentBO;
import bf.com.misys.bankfusion.workflow.attributes.CommentBOList;

/*************************************************************************************
 * Program History
 * 
 * <pre>
 * Project Name            :  BankFusion Equation Long Running Processes
 * Version                 :  BFEQ phase 4
 * Package Name            :  com.misys.equation.bankfusion.lrp.bean
 * Program ID              :  TaskComments.java
 * Program Description     :  contains the list of comments attached to the task and WPS process
 * Environment             :  Java 1.4.5
 * Author                  :  rpatrici
 * Creation Date           :  09 August 2011
 * 
 * Modification History     :
 * Version    Date         Person Name        Remarks
 * 
 * Copyright(C) 2011-Misys Plc. All Rights Reserved.
 * </pre>
 *************************************************************************************/

public class TaskComments
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TaskComments.java 12110 2011-10-19 13:41:07Z lima12 $";

	/** List of comment */
	private ArrayList<TaskComment> comments = new ArrayList<TaskComment>();

	/**
	 * Constructor
	 * 
	 * @param commentList
	 *            - the list of comments
	 */
	public TaskComments(CommentBOList commentList)
	{
		for (CommentBO commentDetail : commentList.getCommentBO())
		{
			comments.add(new TaskComment(commentDetail));
		}

		// sort comments
		Collections.sort(comments, new CommentComparator());
	}

	/**
	 * Iterator
	 * 
	 * @return iterator to the list of comments
	 */
	public Iterator<TaskComment> iterator()
	{
		return comments.iterator();
	}

	/**
	 * Return the list of comments
	 * 
	 * @return the comments
	 */
	public ArrayList<TaskComment> getComments()
	{
		return comments;
	}

	/**
	 * Set the list of comment
	 * 
	 * @param comments
	 *            the list of comments
	 */
	public void setComments(ArrayList<TaskComment> comments)
	{
		this.comments = comments;
	}

	/**
	 * Comparator class to compare comments
	 */
	private class CommentComparator implements Comparator<TaskComment>
	{

		public int compare(TaskComment comment1, TaskComment comment2)
		{
			long date1 = comment1.getTimestamp().getTime();
			long date2 = comment2.getTimestamp().getTime();

			int comparison = 0;

			if (date1 < date2)
			{
				comparison = -1;
			}
			else if (date1 > date2)
			{
				comparison = 1;
			}

			return comparison;
		}
	}

}
