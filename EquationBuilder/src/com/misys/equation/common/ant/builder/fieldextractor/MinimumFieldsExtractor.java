package com.misys.equation.common.ant.builder.fieldextractor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import com.misys.equation.common.ant.builder.core.EquationLogger;

public class MinimumFieldsExtractor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MinimumFieldsExtractor.java 9442 2010-10-12 09:42:28Z MACDONP1 $";
	private static final EquationLogger LOG = new EquationLogger(MinimumFieldsExtractor.class);
	private final static String TARGET_FILE = "E:\\\\EquationDevelopmentGalilieo\\whatever.xml";
	private final static String SOURCE_DIR = "E:\\\\EquationDevelopmentGalilieo\\workbench\\workspace\\EquationCommonStubs\\src\\com\\misys\\equation\\common\\test\\standard\\transaction";
	private final static String PROGRAM_NAME_SRC = "programName =";
	private final static String OPTION_ID_SRC = "optionId =";
	private final static String SETFIELD_SRC = "transaction.setFieldValue";
	private final String inputDirectory;
	private final Hashtable<String, MinimumFields> minimumFieldsList = new Hashtable<String, MinimumFields>();
	// This List contains the files to process.
	private List<File> filesToProcess;
	// This is the extension file to be processed.
	private static final String FILE_EXTENSION = ".java";
	private File sourceFile = null;
	private File targetFile = null;
	private final StringBuilder targetFileBuffer = new StringBuilder();

	// This is the filter
	private static FileFilter javaFilter = new FileFilter()
	{
		public boolean accept(File pathname)
		{
			return pathname.getName().endsWith(FILE_EXTENSION);
		}
	};

	public MinimumFieldsExtractor(String inputDirectory, String targetFileLocation)
	{
		this.inputDirectory = inputDirectory;
		targetFile = new File(targetFileLocation);
	}

	private void readFile()
	{
		try
		{

			BufferedReader inputBufferedReader = new BufferedReader(new FileReader(sourceFile));
			try
			{
				String line = null;
				String pgmId = null;
				String optId = null;
				HashSet<String> fldIds = new HashSet<String>();

				// it loops the current file.
				while ((line = inputBufferedReader.readLine()) != null)
				{

					if (line.contains(OPTION_ID_SRC))
					{
						optId = getStringValue(line);
					}
					else if (line.contains(PROGRAM_NAME_SRC))
					{
						pgmId = getStringValue(line);
					}
					else if (line.contains(SETFIELD_SRC))
					{
						fldIds.add(getStringValue(line));
					}
				}

				// See if we already have this option...
				if (optId != null)
				{
					MinimumFields minimumFields = null;
					if (minimumFieldsList.containsKey(optId))
					{
						minimumFields = minimumFieldsList.get(optId);
						System.out.println("ReProcessing: " + optId);
					}
					else
					{
						minimumFields = new MinimumFields();
						minimumFields.setOptId(optId);
						minimumFields.setPgmId(pgmId);
						minimumFieldsList.put(optId, minimumFields);
						System.out.println("  Processing: " + optId);
					}
					for (String fldId : fldIds)
					{
						minimumFields.addFldId(fldId);
					}
				}
			}
			finally
			{
				inputBufferedReader.close();
			}
		}
		catch (IOException iOException)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("There was an error trying to close the file reader .", iOException);
			}
		}
	}
	/**
	 * This is going to write the file using the current buffer.
	 */
	private void writeFiles()
	{
		Writer output = null;

		try
		{
			Enumeration<MinimumFields> enumeration = minimumFieldsList.elements();
			while (enumeration.hasMoreElements())
			{
				MinimumFields minimumFields = enumeration.nextElement();
				if (minimumFields.getOptId() != null && minimumFields.getPgmId() != null && !minimumFields.getFldIds().isEmpty())
				{
					targetFileBuffer.append(minimumFields.toString() + "\r\n");
				}

			}
			output = new BufferedWriter(new FileWriter(targetFile));
			output.write(targetFileBuffer.toString());
		}
		catch (Exception exception)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("There was an error trying to write the file.", exception);
			}
		}
		finally
		{
			try
			{
				output.close();
			}
			catch (IOException iOException)
			{
				if (LOG.isErrorEnabled())
				{
					LOG.error("There was an error closing the file writer.", iOException);
				}
			}
		}
	}

	/**
	 * This method is going to process the current file. if the current file has not the cvs version attribute it will be added.
	 */
	public void processFile()
	{
		readFile();
	}
	public String getStringValue(String line)
	{
		int firstQuote = line.indexOf("\"");
		int secondQuote = line.indexOf("\"", firstQuote + 1);
		return line.substring(firstQuote + 1, secondQuote);
	}
	/**
	 * This method will process all files.
	 * <ul>
	 * <li>1)The <code>filesToProcess</code> attribute will be populated.</li>
	 * <li>2)The <code>filesToProcess</code> will be iterated and each file will be processed.</li>
	 * </ul>
	 */
	public void processFiles()
	{
		File directory = new File(inputDirectory);
		File[] files = null;

		files = listAllFiles(directory, javaFilter);
		filesToProcess = getFileList(files);

		for (Iterator<File> iterator = filesToProcess.iterator(); iterator.hasNext();)
		{
			sourceFile = iterator.next();
			processFile(sourceFile);
		}

		if (!minimumFieldsList.isEmpty())
		{
			writeFiles();
		}
	}

	/**
	 * This method will process the current file.
	 * 
	 * @param currentFile
	 *            this is the current file to be processed.
	 */
	public void processFile(File currentFile)
	{
		processFile();
	}

	/**
	 * This is a useful method that will populate the passed data into a <code>List</code>
	 * 
	 * @return The List with passed data
	 */
	private List<File> getFileList(File[] array)
	{
		List<File> arrayList = new ArrayList<File>();

		for (File element : array)
		{
			arrayList.add(element);
		}
		return arrayList;
	}

	/**
	 * Gets an array of <tt>File</tt> objects with a directory and its sub-directories.
	 * 
	 * @param file
	 *            either a single file or the root directory
	 * @param filter
	 *            the filename filter - if null <b>all</b> files will be returned
	 * @return the array of <tt>File</tt>
	 */
	private File[] listAllFiles(File file, FileFilter filter)
	{
		ArrayList<File> fileList = new ArrayList<File>();
		File[] files = null;
		int length = 0;

		if (file == null)
		{
			throw new IllegalArgumentException("The root file or directory cannot be null");
		}
		findFiles(fileList, file, filter);

		length = fileList.size();
		files = new File[length];

		for (int index = 0; index < length; index++)
		{
			files[index] = fileList.get(index);
		}
		return files;
	}

	/**
	 * Recursive method for finding files
	 * 
	 * @param fileList
	 *            the list of all files found
	 * @param file
	 * @param filter
	 */
	private void findFiles(List<File> fileList, File file, FileFilter filter)
	{
		File[] dList = null;

		if (file.isDirectory())
		{
			// lists all '*.xxx' files
			File[] fList = file.listFiles(filter);

			for (File element : fList)
			{
				// add ASD file to list
				fileList.add(element);
			}

			dList = file.listFiles();

			for (File element : dList)
			{

				if (element.isDirectory())
				{
					// recursive
					findFiles(fileList, element, filter);
				}
			}
		}
		else
		{
			if (filter.accept(file))
			{
				fileList.add(file);
			}
		}
	}

	/** Simple test harness. */
	public static void main(String... aArguments)
	{
		MinimumFieldsExtractor processFile = new MinimumFieldsExtractor(SOURCE_DIR, TARGET_FILE);
		processFile.processFiles();
	}

}
