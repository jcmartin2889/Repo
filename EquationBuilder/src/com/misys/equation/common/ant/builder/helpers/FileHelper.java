package com.misys.equation.common.ant.builder.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.misys.equation.common.ant.builder.core.EquationLogger;

/**
 * This is a helper class which provide file handling facilities.
 * 
 * @author deroset
 * 
 */
public class FileHelper
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FileHelper.java 13766 2012-07-02 16:13:46Z williae1 $";
	/**
	 * Logger for this class
	 */
	private static final EquationLogger LOG = new EquationLogger(FileHelper.class);
	private static FileHelper currentInstance;

	private FileHelper()
	{

	}

	public static FileHelper getInstance()
	{

		synchronized (FileHelper.class)
		{
			if (currentInstance == null)
			{
				currentInstance = new FileHelper();
			}
		}
		return currentInstance;
	}

	/**
	 * Deletes all files and subdirectories under directories.
	 * 
	 * @param this is the directory to delete.
	 * @return Returns true if all deletions were successful. <br>
	 *         If a deletion fails, the method stops attempting to delete and returns false.
	 */
	public boolean deleteDirectoryButLeaveSVNFiles(File directory)
	{

		boolean success;

		if (directory.isDirectory())
		{
			String[] children = directory.list();
			for (int i = 0; i < children.length; i++)
			{
				File file = new File(directory, children[i]);
				if (!file.getName().equals(".svn"))
				{
					success = deleteDirectoryButLeaveSVNFiles(file);
					if (!success)
					{
						if (LOG.isErrorEnabled())
						{
							LOG.error(new StringBuilder("The directory ").append(directory).append(" could not be deleted.")
											.toString());
						}
						return false;
					}
				}
			}
		}

		// The directory is now empty so delete it
		return directory.delete();
	}
	/**
	 * This method will create a directory.
	 * 
	 * @param directory
	 *            this the directory to be created.
	 */
	public void cretateDirectoties(File directory)
	{

		boolean success;
		success = directory.mkdirs();

		if (!success)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(new StringBuilder("The directory ").append(directory).append(" could not be created.").toString());
			}
		}
	}

	public void copy(String fromFileName, String toFileName) throws IOException
	{
		File fromFile = new File(fromFileName);
		File toFile = new File(toFileName);

		if (!fromFile.exists())
			throw new IOException("FileCopy: " + "no such source file: " + fromFileName);
		if (!fromFile.isFile())
			throw new IOException("FileCopy: " + "can't copy directory: " + fromFileName);
		if (!fromFile.canRead())
			throw new IOException("FileCopy: " + "source file is unreadable: " + fromFileName);

		if (toFile.isDirectory())
		{
			toFile = new File(toFile, fromFile.getName());
		}

		String parent = toFile.getParent();
		if (parent == null)
			parent = System.getProperty("user.dir");
		File dir = new File(parent);
		if (!dir.exists())
			throw new IOException("FileCopy: " + "destination directory doesn't exist: " + parent);
		if (dir.isFile())
			throw new IOException("FileCopy: " + "destination is not a directory: " + parent);
		if (!dir.canWrite())
			throw new IOException("FileCopy: " + "destination directory is unwriteable: " + parent);

		FileInputStream from = null;
		FileOutputStream to = null;
		try
		{
			from = new FileInputStream(fromFile);
			to = new FileOutputStream(toFile);
			byte[] buffer = new byte[4096];
			int bytesRead;

			while ((bytesRead = from.read(buffer)) != -1)
				to.write(buffer, 0, bytesRead); // write
		}
		finally
		{
			if (from != null)
				try
				{
					from.close();
				}
				catch (IOException e)
				{
					;
				}
			if (to != null)
				try
				{
					to.close();
				}
				catch (IOException e)
				{
					;
				}
		}
	}
	/**
	 * Copy file contents from one path to another
	 * 
	 * @param inputFilePath
	 * @param outputFilePath
	 * @throws IOException
	 */
	public void copyFile(String inputFilePath, String outputFilePath) throws IOException
	{
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;

		fileInputStream = new FileInputStream(inputFilePath);
		fileOutputStream = new FileOutputStream(outputFilePath);

		// Use a sensible sized buffer for performance
		byte[] buffer = new byte[8192];
		int bytesRead;
		while ((bytesRead = fileInputStream.read(buffer)) >= 0)
		{
			fileOutputStream.write(buffer, 0, bytesRead);
		}
		fileInputStream.close();
		fileOutputStream.close();

	}
	/**
	 * This method will create a jar.
	 * 
	 * @param jarName
	 * @param folderPath
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void createJar(String jarName, String folderPath) throws IOException, InterruptedException
	{

		Runtime systemShell = Runtime.getRuntime();
		Process output = systemShell.exec("jar cvf " + jarName + " -C " + folderPath + " .");
		BufferedReader br = new BufferedReader(new InputStreamReader(output.getInputStream()));
		String line = null;
		while ((line = br.readLine()) != null)
		{
		} // display process output
		int exitVal = output.waitFor(); // get process exit value

	}
	/**
	 * This method will move a file.
	 * 
	 * @param filename
	 * @param newPath
	 * @throws Exception
	 */
	public void moveFile(String filename, String newPath) throws Exception
	{

		File file = new File(filename);
		File dir = new File(newPath);
		boolean success = file.renameTo(new File(dir, file.getName()));
		if (!success)
		{
			throw new Exception(file.getAbsolutePath() + " File was not successfully moved");
		}
	}
}
