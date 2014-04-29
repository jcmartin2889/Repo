package com.misys.equation.global.test;

import java.util.List;

/**
 * This is a bean class that will be used to parametrise the pool connection properties.
 * 
 * @author deroset.
 * 
 */
public class LocalPoolConnectionProperties
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LocalPoolConnectionProperties.java 15058 2012-12-18 15:09:12Z williae1 $";
	private String user;
	private String password;

	private String url;
	private String driverClassName;
	private String maxWait;

	private String timeBetweenEvictionRunsMillis;
	private String setMaxActive;

	private List<String> binds;

	private String maxConnections;

	public String getUser()
	{
		return user;
	}
	public void setUser(String user)
	{
		this.user = user;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getDriverClassName()
	{
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName)
	{
		this.driverClassName = driverClassName;
	}
	public String getMaxWait()
	{
		return maxWait;
	}

	public int getMaxWaitIntValue()
	{
		return Integer.parseInt(maxWait);
	}

	public void setMaxWait(String maxWait)
	{
		this.maxWait = maxWait;
	}

	public String getTimeBetweenEvictionRunsMillis()
	{
		return timeBetweenEvictionRunsMillis;
	}

	public int getTimeBetweenEvictionRunsMillisInt()
	{
		return Integer.parseInt(timeBetweenEvictionRunsMillis);
	}

	public void setTimeBetweenEvictionRunsMillis(String timeBetweenEvictionRunsMillis)
	{
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public String getSetMaxActive()
	{
		return setMaxActive;
	}

	public int getSetMaxActiveInt()
	{
		return Integer.parseInt(setMaxActive);
	}

	public void setSetMaxActive(String setMaxActive)
	{
		this.setMaxActive = setMaxActive;
	}

	public String getMaxConnections()
	{
		return maxConnections;
	}
	public void setMaxConnections(String maxConnections)
	{
		this.maxConnections = maxConnections;
	}
	public int getMaxConnectionsInt()
	{
		return Integer.parseInt(maxConnections);
	}
	public List<String> getBinds()
	{
		return binds;
	}
	public void setBinds(List<String> binds)
	{
		this.binds = binds;
	}
}