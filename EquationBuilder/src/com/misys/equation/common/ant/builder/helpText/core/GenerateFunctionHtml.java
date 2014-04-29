package com.misys.equation.common.ant.builder.helpText.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import com.misys.equation.common.ant.builder.core.EquationLogger;
import com.misys.equation.common.ant.builder.core.EquationStandardSession;
import com.misys.equation.common.ant.builder.helpText.backEnd.DaoFactory;
import com.misys.equation.common.ant.builder.helpText.backEnd.GAERecordDao;
import com.misys.equation.common.ant.builder.helpText.helpers.CommonDefinitions;
import com.misys.equation.common.ant.builder.helpText.helpers.FileErrorLog;
import com.misys.equation.common.ant.builder.helpText.helpers.FileUtils;
import com.misys.equation.common.ant.builder.helpText.helpers.HelpTextHelper;
import com.misys.equation.common.ant.builder.helpText.helpers.Toolbox;
import com.misys.equation.common.ant.builder.helpText.models.GAERecordDataModel;

/**
 * This class will processes all html files. Processing a function html will consist on <br>
 * 1)Move the file from the resource to the target.<br>
 * 2)Check if the current option is valid. <br>
 * 3)add the link tag at the end of the html file. <br>
 * 
 * @author deroset
 * 
 */
public class GenerateFunctionHtml
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GenerateFunctionHtml.java 9442 2010-10-12 09:42:28Z MACDONP1 $";
	private final EquationStandardSession session = HelpTextlEnvironment.getHelpTextEnvironment().getStandardSession();
	private final DaoFactory daoFactory = new DaoFactory();
	private final FileUtils fileUtils = FileUtils.getInstance();
	private final HelpTextProperties helpTextProperties = HelpTextProperties.getInstance();
	private static final EquationLogger LOG = new EquationLogger(GenerateFunctionHtml.class);
	private final FileErrorLog fileErrorLog = FileErrorLog.getInstance();

	public GenerateFunctionHtml()
	{
		createDirectory();
	}

	public void createDirectory()
	{
		fileUtils.createADirectory(helpTextProperties.getOutputDirectory());
	}

	/**
	 * Main program
	 * 
	 * @param inputParameters
	 *            - input parameters
	 */
	public static void main(String[] inputParameters)
	{
		GenerateFunctionHtml generator;
		generator = new GenerateFunctionHtml();
		generator.process();
	}

	/**
	 * This method will create the function Html files.
	 */
	public void process()
	{
		String optionId = helpTextProperties.getOptionId().trim();

		if (optionId.equals(CommonDefinitions.ALL.toString()))
		{
			processAll();
		}
		else
		{
			processOption(optionId);
		}
	}

	/**
	 * This method will copy all files to be processed.
	 */
	private void copyFiles()
	{

		File source = new File(helpTextProperties.getInputDir());
		File target = new File(helpTextProperties.getOutputDirectory());

		fileUtils.copyDirectory(source, target);
	}

	private void cleanOutputDirectory()
	{

		File target = new File(helpTextProperties.getOutputDirectory());
		fileUtils.deleteDir(target);
	}

	/**
	 * This method will process all valid options.
	 */
	public void processAll()
	{
		String optionId = null;
		HelpTextHelper helpTextHelper = HelpTextHelper.getInstance();
		cleanOutputDirectory();
		copyFiles();

		// retrieve the GAEPF record to retrieve the Equation Journal File
		Hashtable<String, GAERecordDataModel> gaeRecords = getGAERecords();
		TreeMap<String, GAERecordDataModel> gaeRecordsSorted = new TreeMap<String, GAERecordDataModel>(gaeRecords);
		Iterator<GAERecordDataModel> functions = gaeRecordsSorted.values().iterator();

		while (functions.hasNext())
		{
			GAERecordDataModel gaeRecord = functions.next();
			optionId = gaeRecord.getId();
			if (helpTextHelper.validateOption(gaeRecord))
			{
				if (LOG.isInfoEnabled())
				{
					LOG.info(new StringBuilder("The option: ").append(optionId).append(" will be processed.").toString());
				}
				generateFunctionHtml(gaeRecord);
			}
		}

		fileErrorLog.persistFunctionErrorLogFile();
	}

	/**
	 * This method will process the passed option.
	 * 
	 * @param optionId
	 *            this is the option to be processed.
	 */
	public void processOption(String optionId)
	{

		HelpTextHelper helpTextHelper = HelpTextHelper.getInstance();
		cleanOutputDirectory();
		copyFiles();

		// retrieve the Gaepf record to retrieve the Equation Journal File
		GAERecordDataModel gaeRecord = getGAERecords(optionId);

		if (helpTextHelper.validateOption(gaeRecord))
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder("The option: ").append(optionId).append(" will be processed.").toString());
			}
			generateFunctionHtml(gaeRecord);
		}
		else
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(new StringBuilder("GAEPF entry does not exist for Option - ").append(optionId).toString());
			}
		}

		fileErrorLog.persistFunctionErrorLogFile();
	}

	private GAERecordDao getGAEDao()
	{
		GAERecordDao dao = daoFactory.getGAEDao(session, new GAERecordDataModel());
		return dao;
	}

	public Hashtable<String, GAERecordDataModel> getGAERecords()
	{

		Hashtable<String, GAERecordDataModel> gaeRecords = null;
		GAERecordDao gaeRecordDao = getGAEDao();
		// TODO - More types are required. Maybe sort before doing them. GS as well.
		String whereClause = "GAEJFH <> '' OR GAEJFD <> ''";
		gaeRecords = gaeRecordDao.getGAERecordBy(whereClause);
		return gaeRecords;
	}
	public GAERecordDataModel getGAERecords(String optionId)
	{
		optionId = optionId.trim();
		Hashtable<String, GAERecordDataModel> gaeRecords = null;
		GAERecordDao gaeRecordDao = getGAEDao();
		String whereClause = new StringBuilder("GAEFID = '").append(optionId).append("' AND (GAEJFH <> '' OR GAEJFD <> '')")
						.toString();
		gaeRecords = gaeRecordDao.getGAERecordBy(whereClause);
		return gaeRecords.get(optionId);
	}
	/**
	 * This method will add a a link a the end of the current html.
	 */
	public void generateFunctionHtml(GAERecordDataModel gaeRecord)
	{

		StringBuilder outputFile = null;
		BufferedWriter bufferedWriter = null;
		String optionId = gaeRecord.getId();
		String linkLine = helpTextProperties.getApiLinkLine();

		AuthorItHtmlSource authorItSource = new AuthorItHtmlSource(optionId);
		authorItSource.readAuthorItHtmlSource();
		List<String> htmlSource = authorItSource.getAuthorItHtml();

		// If AuthorIt source is not found then use ERROR404.HTM as default
		if (htmlSource.size() == 0)
		{
			authorItSource = new AuthorItHtmlSource("Error404");
			authorItSource.readAuthorItHtmlSource();
			htmlSource = authorItSource.getAuthorItHtml();
		}

		try
		{
			outputFile = new StringBuilder(helpTextProperties.getOutputDirectory());
			// outputFile.append(CommonDefinitions.FILE_SEPARATOR);
			// outputFile.append(helpTextProperties.getLanguage());
			outputFile.append(CommonDefinitions.FILE_SEPARATOR);
			outputFile.append(optionId);
			outputFile.append(".htm");

			// BufferedWriter out = new BufferedWriter(new FileWriter("WebContent\\GB\\FunctionHtml\\" + optionId + ".htm"));
			bufferedWriter = new BufferedWriter(new FileWriter(outputFile.toString()));

			Iterator<String> templateSourceIter = htmlSource.iterator();

			while (templateSourceIter.hasNext())
			{
				String htmlLine = templateSourceIter.next();
				if (htmlLine.contains("</body>"))
				{
					linkLine = linkLine.replace("%Option", Toolbox.rplSpaces(optionId));
					bufferedWriter.write(new StringBuilder(linkLine).append("\r\n").toString());
				}
				bufferedWriter.write(new StringBuilder(htmlLine).append("\r\n").toString());
			}
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder("Function HTML generated for Option - ").append(optionId).toString());
			}
		}
		catch (RuntimeException runException)
		{
			if (LOG.isErrorEnabled())
			{
				StringBuilder message = new StringBuilder("Function HTML failed for Option - ");
				message.append(optionId);
				LOG.error(message.toString(), runException);
			}
			fileErrorLog.appendFunctionMessage(optionId);
		}
		catch (IOException exception)
		{
			if (LOG.isErrorEnabled())
			{
				StringBuilder message = new StringBuilder("Function HTML failed for Option - ");
				message.append(optionId);
				LOG.error(message.toString(), exception);
			}
			fileErrorLog.appendFunctionMessage(optionId);
		}
		finally
		{
			fileUtils.close(bufferedWriter);
		}

	}
}
