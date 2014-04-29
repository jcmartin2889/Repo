package com.misys.equation.common.ant.builder.helpText.helpers;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.misys.equation.common.ant.builder.core.EquationLogger;

/**
 * This class contains all helper methods for file system services.
 * 
 * @author deroset
 * 
 */
public class FileUtils
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FileUtils.java 7606 2010-06-01 17:04:23Z MACDONP1 $";
	private static FileUtils currentInstance;
	/**
	 * Logger for this class
	 */
	private static final EquationLogger LOG = new EquationLogger(FileUtils.class);

	private FileUtils()
	{

	}

	public static FileUtils getInstance()
	{

		synchronized (FileUtils.class)
		{
			if (currentInstance == null)
			{
				currentInstance = new FileUtils();
			}
		}

		return currentInstance;
	}

	/**
	 * this method will copy a file from the source to the target.
	 * 
	 * @param source
	 *            this is the source file.
	 * @param target
	 *            this is the target file.
	 */
	public void copyFile(File source, File target)
	{
		byte[] buffer = new byte[1024];
		int length;
		InputStream inputStream = null;
		OutputStream outputStream = null;

		try
		{
			inputStream = new FileInputStream(source);
			outputStream = new FileOutputStream(target);

			while ((length = inputStream.read(buffer)) > 0)
			{
				outputStream.write(buffer, 0, length);
			}
		}
		catch (Exception exception)
		{
			if (LOG.isErrorEnabled())
			{

				StringBuilder message = new StringBuilder("The was problem copying this file: ");
				message.append(source.getAbsolutePath());
				message.append(" to ");
				message.append(target.getAbsolutePath());
				LOG.error(message.toString(), exception);
			}

		}
		finally
		{

			if (outputStream != null)
			{
				close(outputStream);
			}
			if (inputStream != null)
			{
				close(inputStream);
			}
		}

	}

	/**
	 * This method will copy all files from the sourceDirectory to the targetDirectory
	 * 
	 * @param sourceDirectory
	 *            this is the directory that contains all files to be copied.
	 * @param targetDirectory
	 *            this is the directory where all files will be copied.
	 */
	public void copyDirectory(File sourceDirectory, File targetDirectory)
	{
		if (sourceDirectory.isDirectory())
		{
			if (!targetDirectory.exists())
			{
				targetDirectory.mkdir();
			}

			String[] children = sourceDirectory.list();
			for (int index = 0; index < children.length; index++)
			{
				copyDirectory(new File(sourceDirectory, children[index]), new File(targetDirectory, children[index]));
			}
		}
		else
		{
			copyFile(sourceDirectory, targetDirectory);
		}
	}

	/**
	 * this method will create a directory.
	 * 
	 * @param directoryPath
	 *            this is the directory path to be created.
	 */
	public void createADirectory(String directoryPath)
	{

		File directory = new File(directoryPath);
		boolean result = true;

		if (!directory.exists())
		{
			result = directory.mkdirs();
		}

		if (!result)
		{
			String message = new StringBuilder("The directory ").append(directoryPath).append("could not be created").toString();
			System.out.println(message);
			LOG.error(message);
			throw new RuntimeException(message);
		}
	}

	/**
	 * Deletes all files and sub-directories under dir.
	 * 
	 * @param this is the directory to deleted.
	 * @return Returns true if all deletions were successful.<br>
	 *         If a deletion fails, the method stops attempting to delete and returns false.
	 */
	public void deleteDir(File directory)
	{
		if (directory.isDirectory())
		{

			String[] children = directory.list();

			for (int index = 0; index < children.length; index++)
			{
				deleteDir(new File(directory, children[index]));
			}
		}

		// The directory is now empty so delete it
		directory.delete();

	}

	/**
	 * This method will close all close-able resources.
	 * 
	 * @param resource
	 *            this is the resource to be closed.
	 */
	public void close(Closeable resource)
	{

		if (resource == null)
		{
			return;
		}
		try
		{
			resource.close();
		}
		catch (Exception exception)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("The was problem trying to close a resource", exception);
			}
		}
	}

	/**
	 * This method will create a file.
	 * 
	 * @param filePath
	 *            this is the file name.
	 */
	public void createAFile(String filePath)
	{
		File file = null;

		try
		{
			file = new File(filePath);
			file.createNewFile();
		}
		catch (IOException iOException)
		{
			if (LOG.isErrorEnabled())
			{

				LOG.error(new StringBuilder("The was problem trying to create ").append(filePath).append(" file.").toString(),
								iOException);
			}
		}
	}

	/**
	 * This method will persist this passed <code>String</code> in a flie.
	 * 
	 * @param content
	 *            this the content to be persisted.
	 * @param fileName
	 *            this is the file where are content will be persisted.
	 */
	public void writeInAFile(String content, String fileName)
	{

		BufferedWriter out = null;

		try
		{
			out = new BufferedWriter(new FileWriter(fileName));
			out.write(content);

		}
		catch (IOException iOException)
		{
			if (LOG.isErrorEnabled())
			{
				StringBuilder errorMessage = new StringBuilder("The was problem trying to write ");
				errorMessage.append(fileName);
				errorMessage.append(" file.");
				LOG.error(errorMessage.toString(), iOException);
			}
		}
		finally
		{
			close(out);
		}
	}

}
