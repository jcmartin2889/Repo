package com.misys.equation.common.ant.builder.tasks;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.misys.equation.common.ant.builder.core.EquationLogger;
import com.misys.equation.common.ant.builder.helpText.core.GenerateAPIHtml;
import com.misys.equation.common.ant.builder.helpText.core.GenerateFunctionHtml;
import com.misys.equation.common.ant.builder.helpText.core.HelpTextProperties;

/**
 * This class is a ant tag which wrap the helper text process.
 * 
 * @author deroset
 */
public class HelpTextTag extends Task
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: HelpTextTag.java 7606 2010-06-01 17:04:23Z MACDONP1 $";
	private HelpTextProperties helpTextProperties;
	private GenerateFunctionHtml generateFunctionHtml;
	private GenerateAPIHtml generateAPIHtml;
	private static final EquationLogger LOG = new EquationLogger(HelpTextTag.class);
	// This is the directory that contain all helper text resources files.
	private String resources;
	// This is the directory which contains all input files.
	private String inputSource;
	// This is the output directory.
	private String outputProject;

	/**
	 * This method will set all parameters and perform the helper text processes.<br>
	 * <code>GenerateFunctionHtml</code> will be executed. <code>GenerateAPIHtml</code> will be executed.
	 */
	@Override
	public void execute() throws BuildException
	{
		helpTextProperties = HelpTextProperties.getInstance(resources, inputSource, outputProject);
		generateFunctionHtml = new GenerateFunctionHtml();
		generateAPIHtml = new GenerateAPIHtml();

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder("inputSource: ").append(helpTextProperties.getInputDir()).toString());
			LOG.info(new StringBuilder("outputDir: ").append(helpTextProperties.getOutputDirectory()).toString());
		}
		generateFunctionHtml.process();
		generateAPIHtml.process();
	}

	// -------------------------GETTER AND SETTERS ----------------------------------//

	public String getResources()
	{
		return resources;
	}

	public void setResources(String resources)
	{
		this.resources = resources;
	}

	public String getInputSource()
	{
		return inputSource;
	}

	public void setInputSource(String inputSource)
	{
		this.inputSource = inputSource;
	}

	public String getOutputProject()
	{
		return outputProject;
	}

	public void setOutputProject(String outputProject)
	{
		this.outputProject = outputProject;
	}

}
