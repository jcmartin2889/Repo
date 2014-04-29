package com.misys.equation.common.messages;

public class MessageServiceConfig
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MessageServiceConfig.java 7607 2010-06-01 17:05:56Z MACDONP1 $";
	private String host = null;
	private String userName = null;
	private String password = null;

	/**
	 * @return the host
	 */
	public String getHost()
	{
		return host;
	}

	/**
	 * @param host
	 *            the host
	 */
	public void setHost(String host)
	{
		this.host = host;
	}
	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password
	 *            the password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return the user name
	 */
	public String getUserName()
	{
		return userName;
	}
	/**
	 * @param userName
	 *            the user name
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
}
