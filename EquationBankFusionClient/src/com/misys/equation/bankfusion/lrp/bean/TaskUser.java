package com.misys.equation.bankfusion.lrp.bean;

import bf.com.misys.bankfusion.workflow.attributes.User;

public class TaskUser
{
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TaskUser.java 11954 2011-09-30 09:07:06Z rpatrici $";
	private String userName;

	/**
	 * Constructor
	 * 
	 * @param user
	 *            - the user
	 */
	public TaskUser(User user)
	{
		userName = user.getUser();
	}

	/**
	 * Return the user name
	 * 
	 * @return the user name
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * Set the user name
	 * 
	 * @param userName
	 *            - the user name
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

}
