package com.misys.equation.bankfusion.lrp.bean;

import java.util.ArrayList;
import java.util.List;

import bf.com.misys.bankfusion.workflow.attributes.User;
import bf.com.misys.bankfusion.workflow.attributes.UserList;

public class TaskUsers
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TaskUsers.java 17371 2013-10-04 07:36:12Z perkinj1 $";
	List<TaskUser> users;

	/**
	 * Constructor
	 * 
	 * @param userList
	 *            - the list of users
	 */
	public TaskUsers(UserList userList)
	{
		users = new ArrayList<TaskUser>();
		setupUsers(userList);
	}

	/**
	 * Return the list of users
	 * 
	 * @return the list of users
	 */
	public List<TaskUser> getUsers()
	{
		return users;
	}

	/**
	 * Setup the list of users
	 * 
	 * @param userList
	 *            - the list of users
	 */
	private void setupUsers(UserList userList)
	{
		for (User user : userList.getUsers())
		{
			users.add(new TaskUser(user));
		}
	}

	/**
	 * Determine if user exists in the list
	 * 
	 * @param userName
	 *            - the user name to be searched
	 * 
	 * @return true if user name exists in the list
	 */
	public boolean isExists(String userName)
	{
		// no users, then assume everyone is authorised
		if (users.size() == 0)
		{
			return true;
		}

		// search list
		for (TaskUser user : users)
		{
			if (user.getUserName().equals(userName))
			{
				return true;
			}
		}
		return false;
	}

}