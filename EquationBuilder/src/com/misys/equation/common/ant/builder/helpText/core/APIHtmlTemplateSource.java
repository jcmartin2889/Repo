package com.misys.equation.common.ant.builder.helpText.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.misys.equation.common.ant.builder.core.EquationLogger;
import com.misys.equation.common.ant.builder.helpText.helpers.CommonDefinitions;
import com.misys.equation.common.ant.builder.helpText.helpers.FileUtils;

public class APIHtmlTemplateSource
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: APIHtmlTemplateSource.java 9442 2010-10-12 09:42:28Z MACDONP1 $";
	private static final EquationLogger LOG = new EquationLogger(APIHtmlTemplateSource.class);
	private final FileUtils fileUtils = FileUtils.getInstance();
	private final HelpTextProperties helpTextProperties = HelpTextProperties.getInstance();
	private String templateFile = null;

	private final ArrayList<String> templateSource = new ArrayList<String>();

	public APIHtmlTemplateSource(String templateFileName)
	{

		StringBuilder propertyFilePath = new StringBuilder(helpTextProperties.getResources())
						.append(CommonDefinitions.FILE_SEPARATOR);
		propertyFilePath.append(CommonDefinitions.FILE_SEPARATOR.toString());
		propertyFilePath.append(templateFileName);
		templateFile = propertyFilePath.toString();

	}

	public void readAPIHtmlTemplateSource()
	{
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try
		{
			fileReader = new FileReader(new File(templateFile));
			bufferedReader = new BufferedReader(fileReader);
			String str;
			int templateIndex = 0;
			while ((str = bufferedReader.readLine()) != null)
			{
				templateSource.add(templateIndex, str);
				templateIndex++;
			}

		}
		catch (Exception exception)
		{
			if (LOG.isErrorEnabled())
			{
				StringBuilder message = new StringBuilder("There was a problem trying to process the file");
				message.append(templateFile);
				LOG.error(message.toString(), exception);
			}

		}
		finally
		{
			fileUtils.close(bufferedReader);

		}
	}

	public List<String> getTemplateSource()
	{
		return templateSource;
	}
}
