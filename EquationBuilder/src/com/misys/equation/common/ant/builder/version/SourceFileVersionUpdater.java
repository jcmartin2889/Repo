package com.misys.equation.common.ant.builder.version;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.misys.equation.common.ant.builder.core.EquationLogger;

/**
 * This class will update a java source file and replace the version number in the PLUGIN_VERSION property
 *  
 *  The files are updated by the EquationVersionNumberTag ant custom task. The list of files to be updated is in the
 *  EquationBuilder/build.properties property 
 * @author whittap1
 *
 */
public class SourceFileVersionUpdater
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CommandHandler.java 12017 2011-10-06 02:51:11Z fraramos $";

	/**
	 * Logger for this class
	 */
	private static final EquationLogger LOG = new EquationLogger(SourceFileVersionUpdater.class);
	

	static final String PLUGIN_VERSION_TAG = "String PLUGIN_VERSION";
	
	/**
	 * Process a java source file replacing the old version number with the new version number
	 * @param filePath
	 * @param oldVersion
	 * @param newVersion
	 */
	public static void processFile(String filePath, String oldVersion, String newVersion)
	{

		StringBuilder currentFileBuffer = new StringBuilder();
		boolean found = false;

		try
		{
			File currentFile = new File(filePath);
			BufferedReader in = new BufferedReader(new FileReader(currentFile));
			String line;

			while ((line = in.readLine()) != null)
			{
		
				// Scan for Plugin version in the line and replace the version number
				// This should only be found once per file
				if (line.contains(PLUGIN_VERSION_TAG) && line.contains(oldVersion))
				{
					if (found == true)
					{
						LOG.error("Error multiple PLUGIN_VERSION definitions found");
					}
					else
					{
						line = line.replace(oldVersion, newVersion);
						found = true;
					}
				}
				
				
				currentFileBuffer.append(line);
				currentFileBuffer.append("\r\n");

			}
			in.close();
	
			// Write the data back to the file
			Writer output = null;
			output = new BufferedWriter(new FileWriter(currentFile));
			output.write(currentFileBuffer.toString());

			output.close();
			
		}
		catch (FileNotFoundException e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(new StringBuilder("There was a problem trying to load ").append(filePath).toString(), e);
			}
		}
		catch (IOException e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(new StringBuilder("There was an IO problem accessing the file ").append(filePath).toString(), e);
			}

		}
	}
	
}