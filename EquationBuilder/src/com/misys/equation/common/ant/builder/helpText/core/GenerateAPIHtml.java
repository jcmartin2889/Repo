package com.misys.equation.common.ant.builder.helpText.core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.misys.equation.common.ant.builder.core.EqDS_DSPFFD;
import com.misys.equation.common.ant.builder.core.EquationDataStructure;
import com.misys.equation.common.ant.builder.core.EquationFieldDefinition;
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
 * This class will create all html function files
 */
public class GenerateAPIHtml
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GenerateAPIHtml.java 10856 2011-04-26 10:45:41Z MACDONP1 $";
	private final FileUtils fileUtils = FileUtils.getInstance();
	private final EquationStandardSession session = HelpTextlEnvironment.getHelpTextEnvironment().getStandardSession();
	private static HelpTextProperties helpTextProperties = HelpTextProperties.getInstance();
	private final DaoFactory daoFactory = new DaoFactory();
	private static final EquationLogger LOG = new EquationLogger(GenerateAPIHtml.class);
	private final FileErrorLog fileErrorLog = FileErrorLog.getInstance();

	/**
	 * Construct a GenerateAPIHtml to process all Options
	 * 
	 */
	public GenerateAPIHtml()
	{

	}

	/**
	 * Main program
	 * 
	 * @param inputParameters
	 *            - input parameters
	 */
	public static void main(String[] inputParameters)
	{
		GenerateAPIHtml generator;

		generator = new GenerateAPIHtml();
		generator.process();
	}

	/**
	 * This method will create the GenerateAPIHtml files.
	 */
	public void process()
	{
		String optionId = helpTextProperties.getOptionId().trim();
		createAPIHtmlDirectory();

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
	 * This method will process all valid options.
	 */
	private void processAll()
	{
		String optionId = null;
		HelpTextHelper helpTextHelper = HelpTextHelper.getInstance();

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
				generateJournalFileHtml(gaeRecord);
			}
		}
		fileErrorLog.persistAPIHtmlErrorLogFile();
	}

	/**
	 * This method will process the passed option.
	 * 
	 * @param optionId
	 *            this is the option to be processed.
	 */
	private void processOption(String optionId)
	{
		HelpTextHelper helpTextHelper = HelpTextHelper.getInstance();

		optionId.trim();

		// retrieve the Gaepf record to retrieve the Equation Journal File
		GAERecordDataModel gaeRecord = getGAERecords(optionId);

		if (helpTextHelper.validateOption(gaeRecord))
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder("The option: ").append(optionId).append(" will be processed.").toString());
			}
			generateJournalFileHtml(gaeRecord);
		}
		else
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(new StringBuilder("GAEPF entry does not exist for Option - ").append(optionId).toString());
			}
		}
		fileErrorLog.persistAPIHtmlErrorLogFile();
	}

	private void createAPIHtmlDirectory()
	{
		StringBuilder outputFile = new StringBuilder(helpTextProperties.getOutputDirectory());
		outputFile.append(CommonDefinitions.FILE_SEPARATOR);
		outputFile.append(CommonDefinitions.APIHtml);
		fileUtils.createADirectory(outputFile.toString());
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

	private GAERecordDataModel getGAERecords(String optionId)
	{
		optionId = optionId.trim();
		Hashtable<String, GAERecordDataModel> gaeRecords = null;
		GAERecordDao gaeRecordDao = getGAEDao();
		String whereClause = "GAEFID = '" + optionId + "'";
		gaeRecords = gaeRecordDao.getGAERecordBy(whereClause);
		return gaeRecords.get(optionId);
	}

	/**
	 * Save result Read Equation Journal File source file comments and generate HTML from those comments.
	 * 
	 * Both Journal File Header and Detail formats are processed
	 */
	private void generateJournalFileHtml(GAERecordDataModel gaeRecord)
	{
		StringBuilder outputFile = null;
		BufferedWriter bufferedWriter = null;

		if (!gaeRecord.getHeaderJournalFileName().trim().equals("") || !gaeRecord.getDetailJournalFileName().trim().equals(""))
		{
			String optionId = gaeRecord.getId();
			String description = gaeRecord.getDescription();
			try
			{
				outputFile = new StringBuilder(helpTextProperties.getOutputDirectory());
				outputFile.append(CommonDefinitions.FILE_SEPARATOR);
				outputFile.append(CommonDefinitions.APIHtml);
				outputFile.append(CommonDefinitions.FILE_SEPARATOR);
				outputFile.append(optionId);
				outputFile.append("_API.htm");

				// Journal File Header
				// Note: There should always be a header file if there is a detail file
				String journalFile = gaeRecord.getHeaderJournalFileName();
				if (!journalFile.trim().equals(""))
				{
					bufferedWriter = new BufferedWriter(new FileWriter(outputFile.toString()));

					generateJournalFileHtml(bufferedWriter, optionId, description, helpTextProperties.getApiHtmlHeaderTemplate());

					generateJournalFileHtml(bufferedWriter, optionId, journalFile, description,
									helpTextProperties.getApiHtmlRepeatTemplate());

					journalFile = gaeRecord.getDetailJournalFileName();
					if (!journalFile.trim().equals(""))
					{
						String[] detailFiles = journalFile.split(":");
						for (String detailFile : detailFiles)
						{
							generateJournalFileHtml(bufferedWriter, optionId, detailFile, description,
											helpTextProperties.getApiHtmlRepeatTemplate());
						}
					}
					generateJournalFileHtml(bufferedWriter, optionId, description, helpTextProperties.getApiHtmlFooterTemplate());
				}
			}
			catch (IOException iOException)
			{
				if (LOG.isErrorEnabled())
				{
					StringBuilder message = new StringBuilder("API HTML failed for Option - ");
					message.append(optionId);
					message.append(". there was problem trying to process ");
					message.append(outputFile);
					message.append(" file.");
					LOG.error(message.toString(), iOException);
				}
				fileErrorLog.appendAPIHtmlMessage(optionId);
			}
			catch (RuntimeException runException)
			{
				if (LOG.isErrorEnabled())
				{
					StringBuilder message = new StringBuilder("API HTML failed for Option - ");
					message.append(optionId);
					message.append(". there was problem trying to process ");
					message.append(outputFile);
					message.append(" file.");
					LOG.error(message.toString().toString(), runException);
				}
				fileErrorLog.appendAPIHtmlMessage(optionId);
			}
			finally
			{
				fileUtils.close(bufferedWriter);
			}
		}
	}

	/**
	 * Read Equation Journal File source file comments, merge with HTML template file and generate new HTML.
	 */
	private void generateJournalFileHtml(BufferedWriter bufferedWriter, String optionId, String journalFile, String description,
					String templateFileName) throws IOException
	{

		String title = new StringBuilder(optionId).append(Toolbox.TEXT_DELIMITER).append(description).toString();
		EquationJournalFileSource journalFileSource = null;

		journalFileSource = new EquationJournalFileSource(optionId, journalFile);
		List<String> purpose = journalFileSource.getPurpose();
		List<String> details = journalFileSource.getDetails();
		List<String> keys = journalFileSource.getKeys();

		APIHtmlTemplateSource apiHtmlTemplate = new APIHtmlTemplateSource(templateFileName);
		apiHtmlTemplate.readAPIHtmlTemplateSource();
		List<String> templateSource = apiHtmlTemplate.getTemplateSource();

		Map<String, EquationFieldDefinition> fieldDefinitions = rtvGZFile(journalFile).getFieldDefinitions();

		Iterator<String> templateSourceIter = templateSource.iterator();

		while (templateSourceIter.hasNext())
		{
			String htmlLine = templateSourceIter.next();
			if (htmlLine.contains("%Title"))
			{
				htmlLine = htmlLine.replace("%Title", Toolbox.rplSpacesAndSpecialCharacters(title));
			}
			if (htmlLine.contains("%Option"))
			{
				htmlLine = htmlLine.replace("%Option", Toolbox.rplSpacesAndSpecialCharacters(optionId));
			}
			if (htmlLine.contains("%Journal"))
			{
				htmlLine = htmlLine.replace("%Journal", Toolbox.rplSpacesAndSpecialCharacters(journalFile));
			}
			if (htmlLine.contains("%FunctionDescription"))
			{
				htmlLine = htmlLine.replace("%FunctionDescription", Toolbox.rplSpacesAndSpecialCharacters(description));
			}
			if (htmlLine.contains("%NumberOfKeyFields"))
			{
				htmlLine = htmlLine.replace("%NumberOfKeyFields",
								Toolbox.rplSpacesAndSpecialCharacters(Integer.valueOf(keys.size()).toString()));
			}
			if (htmlLine.contains("%NumberOfFields"))
			{
				htmlLine = htmlLine.replace("%NumberOfFields",
								Toolbox.rplSpacesAndSpecialCharacters(Integer.valueOf(fieldDefinitions.size()).toString()));
			}
			if (htmlLine.contains("%FieldName") || htmlLine.contains("%FieldDescription") || htmlLine.contains("%FieldType")
							|| htmlLine.contains("%FieldSize") || htmlLine.contains("%FieldDecimals"))
			{
				fieldHtml(bufferedWriter, htmlLine, fieldDefinitions);
				continue;
			}
			// TODO Determine what information is needed for keys. Keys may be irrelevant.
			if (htmlLine.contains("%KeyFieldName") || htmlLine.contains("%KeyFieldDescription")
							|| htmlLine.contains("%KeyFieldType") || htmlLine.contains("%KeyFieldSize")
							|| htmlLine.contains("%KeyFieldDecimals"))
			{
				keyFieldHtml(bufferedWriter, htmlLine, fieldDefinitions, keys);
				continue;
			}

			if (htmlLine.contains("%Purpose"))
			{
				purposeHtml(bufferedWriter, htmlLine, purpose);
				continue;
			}

			if (htmlLine.contains("%Details"))
			{
				detailsHtml(bufferedWriter, htmlLine, details);
				continue;
			}

			bufferedWriter.write(htmlLine + "\r\n");
		}

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder("API HTML generated for Option - ").append(optionId).append(" Journal File - ")
							.append(journalFile).toString());
		}
	}

	/**
	 * Read HTML template file and generate new HTML. Use for HTML heading and footing where template variables are limited.
	 */
	private void generateJournalFileHtml(BufferedWriter bufferedWriter, String optionId, String description, String templateFileName)
					throws IOException
	{
		String title = new StringBuilder(optionId).append(Toolbox.TEXT_DELIMITER).append(description).toString();

		APIHtmlTemplateSource apiHtmlTemplate = new APIHtmlTemplateSource(templateFileName);
		apiHtmlTemplate.readAPIHtmlTemplateSource();
		List<String> templateSource = apiHtmlTemplate.getTemplateSource();

		Iterator<String> templateSourceIter = templateSource.iterator();

		while (templateSourceIter.hasNext())
		{
			String htmlLine = templateSourceIter.next();
			if (htmlLine.contains("%Title"))
			{
				htmlLine = htmlLine.replace("%Title", Toolbox.rplSpacesAndSpecialCharacters(title));
			}

			bufferedWriter.write(htmlLine + "\r\n");
		}

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder("API HTML generated for Option - ").append(optionId).toString());
		}
	}

	/**
	 * This method will replace the tokens for the information from Equation Journal File source file.
	 * 
	 * @param bufferedWriter
	 *            this is the file buffer where the html will be persisted.
	 * @param originalHtmlLine
	 *            this is the current html line.
	 * @param fieldDefinitions
	 *            this is a list that contains the <code>EquationFieldDefinition</code>
	 */
	private void fieldHtml(BufferedWriter bufferedWriter, String originalHtmlLine,
					Map<String, EquationFieldDefinition> fieldDefinitions)
	{
		try
		{
			String fieldPositions = null;
			int calcStart = 1;
			Integer fieldStart = null;
			Integer fieldEnd = null;

			for (Entry<String, EquationFieldDefinition> entry : fieldDefinitions.entrySet())
			{
				EquationFieldDefinition fieldDefinition = entry.getValue();
				fieldStart = Integer.valueOf(calcStart);
				fieldEnd = Integer.valueOf(calcStart + fieldDefinition.getInternalFieldLength() - 1);
				calcStart = calcStart + fieldDefinition.getInternalFieldLength();
				fieldPositions = fieldStart.toString() + Toolbox.TEXT_DELIMITER + fieldEnd.toString();

				String htmlLine = originalHtmlLine;

				// replace the field name
				htmlLine = htmlLine.replace("%FieldName", Toolbox.rplSpacesAndSpecialCharacters(fieldDefinition.getFieldName()));

				// replace the field label
				htmlLine = htmlLine.replace("%FieldDescription",
								Toolbox.rplSpacesAndSpecialCharacters(fieldDefinition.getFieldLabel()));

				// replace the field type
				htmlLine = htmlLine.replace("%FieldType",
								Toolbox.rplSpacesAndSpecialCharacters(fieldDefinition.getFieldDataTypeString()));

				// replace the field positions
				htmlLine = htmlLine.replace("%FieldPositions", Toolbox.rplSpacesAndSpecialCharacters(fieldPositions));

				// replace the field length
				htmlLine = htmlLine.replace("%FieldSize",
								Toolbox.rplSpacesAndSpecialCharacters(String.valueOf(fieldDefinition.getFieldLength())));

				// replace the field decimal
				htmlLine = htmlLine.replace("%FieldDecimals",
								Toolbox.rplSpacesAndSpecialCharacters(String.valueOf(fieldDefinition.getFieldDecimal())));

				bufferedWriter.write(new StringBuilder(htmlLine).append("\r\n").toString());
			}
		}
		catch (IOException exception)
		{
			if (LOG.isErrorEnabled())
			{
				StringBuilder message = new StringBuilder("API HTML failed for fields.");
				LOG.error(message.toString());
			}
		}
	}

	/**
	 * Save result for key field information
	 */
	private void keyFieldHtml(BufferedWriter out, String originalHtmlLine, Map<String, EquationFieldDefinition> fieldDefinitions,
					List<String> keys)
	{
		try
		{
			String fieldPositions = null;
			int calcStart = 1;
			Integer fieldStart = null;
			Integer fieldEnd = null;

			for (String key : keys)
			{
				EquationFieldDefinition fieldDefinition = fieldDefinitions.get(key);
				fieldStart = Integer.valueOf(calcStart);
				fieldEnd = Integer.valueOf(calcStart + fieldDefinition.getInternalFieldLength() - 1);
				calcStart = calcStart + fieldDefinition.getInternalFieldLength();
				fieldPositions = fieldStart.toString() + Toolbox.TEXT_DELIMITER + fieldEnd.toString();

				String htmlLine = originalHtmlLine;

				// replace the field name
				htmlLine = htmlLine.replace("%KeyFieldName", Toolbox.rplSpacesAndSpecialCharacters(fieldDefinition.getFieldName()));

				// replace the field label
				htmlLine = htmlLine.replace("%KeyFieldDescription",
								Toolbox.rplSpacesAndSpecialCharacters(fieldDefinition.getFieldLabel()));

				// replace the field type
				htmlLine = htmlLine.replace("%KeyFieldType",
								Toolbox.rplSpacesAndSpecialCharacters(fieldDefinition.getFieldDataTypeString()));

				// replace the field positions
				htmlLine = htmlLine.replace("%KeyFieldPositions", Toolbox.rplSpacesAndSpecialCharacters(fieldPositions));

				// replace the field length
				htmlLine = htmlLine.replace("%KeyFieldSize",
								Toolbox.rplSpacesAndSpecialCharacters(String.valueOf(fieldDefinition.getFieldLength())));

				// replace the field decimal
				htmlLine = htmlLine.replace("%KeyFieldDecimals",
								Toolbox.rplSpacesAndSpecialCharacters(String.valueOf(fieldDefinition.getFieldDecimal())));

				out.write(htmlLine + "\r\n");
			}
		}
		catch (IOException e)
		{
			LOG.error("API HTML failed for key fields", e);
		}
	}

	/**
	 * Save result for purpose information
	 */
	private void purposeHtml(BufferedWriter out, String originalHtmlLine, List<String> purpose)
	{
		try
		{
			Iterator<String> purposeIter = purpose.iterator();

			while (purposeIter.hasNext())
			{
				String text = purposeIter.next();
				String htmlLine = originalHtmlLine;

				// replace the field name
				htmlLine = htmlLine.replace("%Purpose", Toolbox.rplSpacesAndSpecialCharacters(text));

				out.write(htmlLine + "\r\n");
			}
		}
		catch (IOException e)
		{
			LOG.error("API HTML failed for purpose", e);
		}
	}

	/**
	 * Save result for detail information
	 */
	private void detailsHtml(BufferedWriter out, String originalHtmlLine, List<String> details)
	{
		try
		{
			for (String text : details)
			{
				String htmlLine = originalHtmlLine;

				// replace the field name
				htmlLine = htmlLine.replace("%Details", Toolbox.rplSpacesAndSpecialCharacters(text));

				out.write(htmlLine + "\r\n");
			}
		}
		catch (IOException e)
		{
			LOG.error("API HTML failed for details", e);
		}
	}

	/**
	 * Return the Equation Data Structure of the GZ file
	 * 
	 * @return the Equation Data Structure of the GZ file
	 */
	private EquationDataStructure rtvGZFile(String gzFile)
	{
		EquationDataStructure eqDs = new EqDS_DSPFFD(gzFile, session);
		return eqDs;
	}
}