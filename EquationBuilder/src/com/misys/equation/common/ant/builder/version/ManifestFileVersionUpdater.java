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
 * This class will update a MANIFEST.MF file and replace the version number in
 *  - 1. The Bundle-Version property
 *  - 2. The equation jar names in the classpath if any exist
 *  
 *  The files are updated by the EquationVersionNumberTag ant custom task. The list of files to be updated is in the
 *  EquationBuilder/build.properties property EQUATION_VERSION_NUMBER_IN_MANIFEST
 *  Note: An alternative method would be to use the Manifest class but this would not preserve the neat layout of the file
 * @author whittap1
 *
 */
public class ManifestFileVersionUpdater
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CommandHandler.java 12017 2011-10-06 02:51:11Z fraramos $";

	/**
	 * Logger for this class
	 */
	private static final EquationLogger LOG = new EquationLogger(ManifestFileVersionUpdater.class);
	

	static final String BUNDLE_VERSION_TAG = "Bundle-Version";
	
	/**
	 * Process a manifest file replacing the old version number with the new version number
	 * @param filePath
	 * @param oldVersion
	 * @param newVersion
	 */
	public static void processFile(String filePath, String oldVersion, String newVersion)
	{

		StringBuilder currentFileBuffer = new StringBuilder();

		String equationJars[] = { "equation-bankfusionclient", "equation-bankfusionserver",
						"equation-common", "equation-eclipse-common", "equation-function" };
		try
		{
			File currentFile = new File(filePath);
			BufferedReader in = new BufferedReader(new FileReader(currentFile));
			String line;

			while ((line = in.readLine()) != null)
			{
				// Scan for the equation jar names in the line and replace with new version number
				for (String jarName : equationJars)
				{
					String oldJarName = jarName + "-" + oldVersion + ".jar";
					String newJarName = jarName + "-" + newVersion + ".jar";
					if (line.contains(oldJarName))
					{
						line = line.replace(oldJarName, newJarName);
					}
				}
				
				// Scan for Bundle version in the line and replace the version number
				if (line.contains(BUNDLE_VERSION_TAG) && line.contains(oldVersion))
				{
					line = line.replace(oldVersion, newVersion);
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