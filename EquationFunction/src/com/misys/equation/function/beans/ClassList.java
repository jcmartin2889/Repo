package com.misys.equation.function.beans;

/**
 * Represents a class list definition
 */
public class ClassList
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ClassList.java 11238 2011-06-21 06:39:39Z yzobdabu $";

	private String nameSpace;
	private String className;
	private String jarLocation;

	/**
	 * Default constructor
	 */
	public ClassList()
	{
	}

	/**
	 * @param nameSpace
	 *            - name space of the user class
	 * @param className
	 *            - class name of the user class
	 * @param jarLocation
	 *            - (when specified) jar location of the user class
	 */
	public ClassList(String nameSpace, String className, String jarLocation)
	{
		this.nameSpace = nameSpace;
		this.className = className;
		this.jarLocation = jarLocation;
	}

	public String getNameSpace()
	{
		return nameSpace;
	}

	public void setNameSpace(String nameSpace)
	{
		this.nameSpace = nameSpace;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public String getJarLocation()
	{
		return jarLocation;
	}

	public void setJarLocation(String jarLocation)
	{
		this.jarLocation = jarLocation;
	}
}