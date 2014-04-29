package com.misys.equation.ui.forms;

import org.apache.struts.action.ActionForm;

public class GetPwdForm extends ActionForm
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GetPwdForm.java 15473 2013-03-08 16:08:07Z whittap1 $";

	private String systemName;
	private String unit;
	private String user;
	private String password;
	private String mode;
	private String option;
	private String context;
	private String mainFhName;

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getSystemName()
	{
		return systemName;
	}

	public void setSystemName(String systemName)
	{
		this.systemName = systemName;
	}

	public String getUnit()
	{
		return unit;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getMode()
	{
		return mode;
	}

	public void setMode(String mode)
	{
		this.mode = mode;
	}

	public String getOption()
	{
		return option;
	}

	public void setOption(String option)
	{
		this.option = option;
	}

	public String getContext()
	{
		return context;
	}

	public void setContext(String context)
	{
		this.context = context;
	}

	public String getMainFhName()
	{
		return mainFhName;
	}

	public void setMainFhName(String mainFhName)
	{
		this.mainFhName = mainFhName;
	}

}