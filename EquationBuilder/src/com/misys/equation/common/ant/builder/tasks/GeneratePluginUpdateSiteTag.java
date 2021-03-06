package com.misys.equation.common.ant.builder.tasks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.misys.equation.common.ant.builder.helpers.FileHelper;
import com.misys.equation.common.ant.builder.helpers.PropertiesFileHelper;

/**
 * This class is used to make an update site folder containing the EquationFunctionWizard and ServiceComposerHelp jars.
 */
public class GeneratePluginUpdateSiteTag extends Task
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GeneratePluginUpdateSiteTag.java 14786 2012-11-05 09:14:50Z Whittap1 $";

	// Properties passed as parameters
	private String builderProjectPropertyFile;
	private String pluginProjectManifests[];
	private String pluginJarFolders[];

	// Properties from the BankFusion Builder property file
	private String versionNumber;
	private String updateSiteRootPath;

	/**
	 * This is the main method for creating the update site folder.
	 */
	public void execute() throws BuildException
	{
		try
		{
			getInputParametersFromPropertyFile();
			createUpdateSite();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new BuildException(e);
		}

	}
	private void createUpdateSite() throws Exception
	{
		FileHelper fileHelper = FileHelper.getInstance();
		String updateSitePath = updateSiteRootPath + "/BankFusionEquationUpdateSite_" + versionNumber;
		fileHelper.deleteDirectoryButLeaveSVNFiles(new File(updateSitePath));
		System.out.println("Creating Plugin Update Site " + updateSitePath + "\n");
		File features_path_dir = new File(updateSitePath);
		features_path_dir.mkdirs();

		File features_path = new File(updateSitePath + File.separator + "features");
		features_path.mkdirs();
		File plugins_path = new File(updateSitePath + File.separator + "plugins");
		plugins_path.mkdirs();

		BufferedWriter featurexml = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(features_path.getAbsoluteFile()
						+ File.separator + "feature.xml"), "UTF-8"));
		BufferedWriter sitexml = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(updateSitePath + File.separator
						+ "site.xml"), "UTF-8"));

		String line = "";

		sitexml.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sitexml.write("<site>\n");
		sitexml.write("<feature url=\"features/BankFusionEquationFeature_" + versionNumber + ".jar\""
						+ " id=\"BankFusionEquationFeature\" version=\"" + versionNumber + "\"/>\n");

		sitexml.write("</site>");
		sitexml.close();

		featurexml.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		featurexml.write("<feature \n");
		featurexml.write(" id=\"BankFusionEquationFeature\"\n");
		featurexml.write("label=\"BankFusion Equation Feature\"\n");
		featurexml.write("version=\"" + versionNumber + "\">\n");

		featurexml.write("<description url=\"http://www.example.com/description\">\n");
		featurexml.write("BankFusion Equation\n");
		featurexml.write("</description>\n\n");
		featurexml.write("<copyright>\n");
		featurexml.write("Copyright � Misys International Banking Systems Ltd, 2009.\n");
		featurexml.write("</copyright>\n\n");
		featurexml.write("<license>\n");
		featurexml.write("All rights reserved. No part of the programs and data contained herein may be\n");
		featurexml.write("form or by any means whether digital, electronic, mechanical or otherwise\n");
		featurexml.write("without the written permission of Misys to which application for such permission\n");
		featurexml.write("should be addressed. The use of the programs and data contained herein is\n");
		featurexml.write("subject to the terms and conditions of the Licence Agreement under which they\n");
		featurexml.write("are supplied.\n");
		featurexml.write("</license>\n\n");

		for (int i = 0; i < pluginProjectManifests.length; i++)
		{
			String manifestPath = pluginProjectManifests[i];

			FileReader fr = new FileReader(manifestPath);
			BufferedReader br = new BufferedReader(fr);
			String jarName = "";
			String jarVersion = "";
			while ((line = br.readLine()) != null)
			{
				if (line.startsWith("Bundle-SymbolicName:") && line.contains(";"))
				{
					featurexml.write("<plugin \n");
					jarName = line.substring(21, line.indexOf(";"));
					featurexml.write("id=\"" + jarName + "\"\n");
					featurexml.write("download-size=\"0\"\n");
					featurexml.write("install-size=\"0\"\n");
				}
				else if (line.startsWith("Bundle-SymbolicName:") && !line.contains(";"))
				{
					featurexml.write("<plugin \n");
					jarName = line.substring(21);
					featurexml.write("id=\"" + jarName + "\"\n");
					featurexml.write("download-size=\"0\"\n");
					featurexml.write("install-size=\"0\"\n");
				}
				if (line.startsWith("Bundle-Version:"))
				{
					jarVersion = line.substring(16);
					if (!jarVersion.equals(versionNumber))
					{
						throw new Exception(pluginProjectManifests[i] + " manifest version " + jarVersion
										+ " not equal to EquationBuilder version " + versionNumber);
					}
					featurexml.write("version=\"" + jarVersion + "\"/>\n");
					// featurexml.write("unpack=\"" + "false" + "\"/>\n");
				}
			}

			String jarToCopy = jarName + "_" + jarVersion + ".jar";
			fileHelper.copyFile(pluginJarFolders[i] + "\\" + jarToCopy, plugins_path.getAbsolutePath() + "\\" + jarToCopy);

		}
		featurexml.write("</feature>");
		featurexml.close();
		fileHelper.createJar("BankFusionEquationFeature_" + versionNumber + ".jar", features_path.getAbsolutePath());
		fileHelper.moveFile("BankFusionEquationFeature_" + versionNumber + ".jar", features_path.getAbsolutePath());
		File tmpFeaturexml = new File(features_path.getAbsoluteFile() + File.separator + "feature.xml");
		tmpFeaturexml.delete();
		System.out.println("Created Plugin Update Site " + updateSitePath + "\n");
	}
	/**
	 * This method will load properties from the EquationBuilder build.properties file. It is assumed that version numbers have
	 * already been changed by previous build processes.
	 * 
	 * @throws Exception
	 *             if there is any property with non valid values.
	 */
	private void getInputParametersFromPropertyFile() throws Exception
	{

		PropertiesFileHelper propertiesFileHelper = PropertiesFileHelper.getInstance();
		propertiesFileHelper.setFileName(builderProjectPropertyFile);
		// propertiesFileHelper.printProperties();

		versionNumber = propertiesFileHelper.getPropertyValue("VERSION");
		updateSiteRootPath = propertiesFileHelper.getPropertyValue("UPDATESITEPATH");
	}

	/**
	 * This method will set the EquationBuilder Project property file.
	 * 
	 * @param builderProjectPropertyFile
	 */
	public void setBuilderProjectPropertyFile(String builderProjectPropertyFile)
	{
		// System.out.println(new StringBuilder("Builder Project Property File: ").append(builderProjectPropertyFile));
		this.builderProjectPropertyFile = builderProjectPropertyFile;

	}
	/**
	 * This method will set the plug-in project manifests.
	 * 
	 * @param pluginProjectManifestsProperty
	 */
	public void setPluginProjectManifests(String pluginProjectManifestsProperty)
	{
		// System.out.println(new StringBuilder("PluginProjectsProperty: ").append(pluginProjectManifestsProperty));
		if (pluginProjectManifestsProperty.contains(","))
		{
			pluginProjectManifests = pluginProjectManifestsProperty.split(",");
		}
		else
		{
			pluginProjectManifests = new String[] { pluginProjectManifestsProperty };
		}

	}
	/**
	 * This method will set the plug-in jar folders.
	 * 
	 * @param pluginJarFoldersProperty
	 */
	public void setPluginJarFolders(String pluginJarFoldersProperty)
	{
		// System.out.println(new StringBuilder("PluginJarFoldersProperty: ").append(pluginJarFoldersProperty));
		if (pluginJarFoldersProperty.contains(","))
		{
			pluginJarFolders = pluginJarFoldersProperty.split(",");
		}
		else
		{
			pluginJarFolders = new String[] { pluginJarFoldersProperty };
		}
	}

}
