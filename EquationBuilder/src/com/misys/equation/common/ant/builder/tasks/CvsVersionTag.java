package com.misys.equation.common.ant.builder.tasks;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.misys.equation.common.ant.builder.CvsVersion;

/**
 * This task is going to add the cvs-version attribute in the java files.<br>
 * This task will get all java files from the specific folder passed as a tag's attribute.<br>
 * If the java file has not this attribute it will be added in otherwise non action will take place.
 * 
 * @author deroset.
 * 
 */
public class CvsVersionTag extends Task
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CvsVersionTag.java 5233 2009-10-30 12:12:47Z macdonp1 $";
	// This is the directory which contains all input files.
	private String inputDirectory;

	@Override
	public void execute() throws BuildException
	{
		CvsVersion cvsVersion = new CvsVersion(inputDirectory);
		cvsVersion.processFiles();
	}

	public void setInputDirectory(String inputDirectory)
	{
		this.inputDirectory = inputDirectory;
	}

}
