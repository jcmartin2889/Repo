package com.misys.equation.common.ant.builder.helpText.helpers;

/**
 * This class will buffer all error messages and will persist them in a error file.
 * 
 * @author deroset
 * 
 */
public class FileErrorLog
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FileErrorLog.java 4624 2009-09-03 23:17:40Z esther.williams $";
	private static FileErrorLog currentInstance;
	// This is a flag that will check if FileErrorLog is currently being used.
	private boolean hasBeenUsed = false;
	private FileUtils fileUtils = FileUtils.getInstance();
	// This is the current buffer.
	private StringBuilder buffer;

	private FileErrorLog()
	{

		initialise();
	}
	/**
	 * It will initialise the FileErrorLog.
	 */
	private void initialise()
	{
		buffer = new StringBuilder();
		hasBeenUsed = false;
	}

	public static FileErrorLog getInstance()
	{

		synchronized (FileErrorLog.class)
		{
			if (currentInstance == null)
			{

				currentInstance = new FileErrorLog();
			}
		}
		return currentInstance;
	}

	/**
	 * This method will create am error message for the current option.
	 * 
	 * @param oprion
	 *            this is the option which could no be processed.
	 */
	public void appendFunctionMessage(String oprion)
	{

		appendMessage(oprion, "function");
	}

	/**
	 * This method will create am error message for the current option.
	 * 
	 * @param oprion
	 *            this is the option which could no be processed.
	 */
	public void appendAPIHtmlMessage(String oprion)
	{

		appendMessage(oprion, "APIHtml");
	}

	public void appendMessage(String oprion, String processesName)
	{

		if (hasBeenUsed == false)
		{
			hasBeenUsed = true;
		}
		buffer.append(processesName);
		buffer.append(": for option: ");
		buffer.append(oprion);
		buffer.append(" is missing.\r\n");
	}

	/**
	 * This method will persist the all functions messages in the function error logFile.
	 */
	public void persistFunctionErrorLogFile()
	{
		persistErrorLogFile(CommonDefinitions.FUNCTION_ERROR_FILES.toString());
	}

	/**
	 * This method will persist the all functions messages in the function error logFile.
	 */
	public void persistAPIHtmlErrorLogFile()
	{
		persistErrorLogFile(CommonDefinitions.APIHTML_ERROR_FILES.toString());
	}

	/**
	 * This method will persist the current buffer in a file.
	 * 
	 * @param fileName
	 *            this is the file name where the buffer will be persisted.
	 */
	private void persistErrorLogFile(String fileName)
	{

		if (hasBeenUsed == true)
		{
			fileUtils.writeInAFile(buffer.toString(), fileName);
			// it cleans the buffer.
			initialise();
		}
	}

}
