package com.misys.equation.common.test;

/**
 * This is a Pojo class that contains all database configuration properties. <br>
 * The attributes values are defined in the local configuration file as bean definition. <br>
 * 
 * @see commonStubsContext.xml
 * @author deroset
 */
public class TestEnvironmentConnectionProperties
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TestEnvironmentConnectionProperties.java 5234 2009-10-30 12:16:51Z macdonp1 $";
	private String system;
	private String inputBranch;
	private String unit;
	private String user;
	private String password;

	/* -- getter and setters-- */

	public String getSystem()
	{
		return system;
	}
	public void setSystem(String system)
	{
		this.system = system;
	}
	public String getInputBranch()
	{
		return inputBranch;
	}
	public void setInputBranch(String inputBranch)
	{
		this.inputBranch = inputBranch;
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
		if (user != null)
		{
			// Ensure that the user is upper case so that
			// OCPF retrieval will work OK
			this.user = user.toUpperCase();
		}
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
}
