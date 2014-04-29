package com.misys.equation.common.utilities;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.ibm.as400.access.UserSpace;

public class FileUtils
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FileUtils.java 14409 2012-09-04 12:28:38Z williae1 $";

	/**
	 * Logger for this class
	 */
	private static final EquationLogger LOG = new EquationLogger(FileUtils.class);

	/**
	 * This method will copy a file.
	 * 
	 * @param source
	 *            this is the file name to be copied.
	 * @param target
	 *            this is the file name to be created.
	 */
	public static void copyFile(File source, File target)
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
	 * This method will copy a directory.
	 * 
	 * @param sourceDirectory
	 *            this is the directory to be copied.
	 * @param targetDirectory
	 *            this is the directory to be created.
	 */
	public static void copyDirectory(File sourceDirectory, File targetDirectory)
	{
		if (sourceDirectory.isDirectory())
		{
			if (!targetDirectory.exists())
			{
				targetDirectory.mkdir();
			}

			String[] children = sourceDirectory.list();
			for (String element : children)
			{
				copyDirectory(new File(sourceDirectory, element), new File(targetDirectory, element));
			}
		}
		else
		{
			copyFile(sourceDirectory, targetDirectory);
		}
	}
	/**
	 * This method will copy a directory.
	 * 
	 * @param sourceDirectory
	 *            this is the directory to be copied.
	 * @param targetDirectory
	 *            this is the directory to be created.
	 */
	public static void copyDirectoryWithoutFiles(File sourceDirectory, File targetDirectory)
	{
		if (sourceDirectory.isDirectory())
		{
			if (!targetDirectory.exists())
			{
				targetDirectory.mkdir();
			}

			String[] children = sourceDirectory.list();
			for (String element : children)
			{
				copyDirectoryWithoutFiles(new File(sourceDirectory, element), new File(targetDirectory, element));
			}
		}

	}
	/**
	 * this method will create a directory.
	 * 
	 * @param directoryPath
	 *            this is the directory path to be created.
	 */
	public static void createADirectory(String directoryPath)
	{
		File directory = new File(directoryPath);

		if (!directory.exists())
		{
			directory.mkdir();
		}
	}

	/**
	 * Deletes all files and sub-directories under a main directory.
	 * 
	 * @param directory
	 *            this is the main directory to deleted.
	 * 
	 */
	public static void deleteDir(File directory)
	{
		if (directory.isDirectory())
		{

			String[] children = directory.list();

			for (String element : children)
			{
				deleteDir(new File(directory, element));
			}
		}

		// The directory is now empty so delete it
		directory.delete();

	}
	/**
	 * This method will close a resource.
	 * 
	 * @param resource
	 *            this is the resource to be closed.
	 */
	public static void close(Closeable resource)
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
	 *            this is the file to be created.
	 */
	public static void createAFile(String filePath)
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
	 * This method will write a message to a file.
	 * 
	 * @param message
	 *            this is the message to be written to the file.
	 * @param fileName
	 *            this is the file to contain the message.
	 */
	public static void writeInAFile(String message, String fileName)
	{
		BufferedWriter out = null;

		try
		{
			out = new BufferedWriter(new FileWriter(fileName));
			out.write(message);

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

	/**
	 * Close an AS400 UserSpace Object
	 * 
	 * @param userSpace
	 */
	public static void close(UserSpace userSpace)
	{
		try
		{
			if (userSpace != null)
			{
				userSpace.close();
			}
		}
		catch (IOException e)
		{
			LOG.error(e);
		}
	}
}