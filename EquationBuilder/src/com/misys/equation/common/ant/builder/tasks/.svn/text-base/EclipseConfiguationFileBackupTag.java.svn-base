package com.misys.equation.common.ant.builder.tasks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.misys.equation.common.ant.builder.helpers.FileHelper;
import com.misys.equation.common.ant.builder.helpers.PropertiesFileHelper;

/**
 * This task is going to replace the old version number for the new one.<br>
 * This task will read the file  passed as argument and replace the old version number for the new one .<br>
 * @author deroset.
 * 
 */
public class EclipseConfiguationFileBackupTag extends Task
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationVersionNumberTag.java,v 1.9 2012/01/17 17:32:21 deroset Exp $";
	
	// This is a list that contains all files whose its attribute must be updated..
	private String inputVersionNumberAttributeFiles;
	
	// This is a list that contains all files whose jars list names must be updated..
	private String inputVersionNumberInJarNamesFiles;
	
	// This is a list that contains all eclipse .classpath files whose jars list names must be updated..
	private String eclipseClassPathFiles;
	
	// #This is the property file which contains the the file location of the eclipse plugin.xml. This file have some hadcoded equation version numbers.
	private String eclipsePluginPathFiles;
	
	private String[] eclipseClassPathFilesPaths;
	private String[] versionAttributeFilesPaths;
	private String[] versionNumberInJarFilesNames;
	private String[] eclipsePluginFilesPaths;
	private String backupFilepath;
	private List<String> eclipseConfigurationFiles;
	private PropertiesFileHelper propertiesFileHelper ;
	
	
		

	@Override
	public void execute() throws BuildException
	{
		try {
			
			setUp();
			createBackupFile();
			createBackupDirectories();
			backupEclipseConfigurationFiles();
			
		} catch (Exception exceptio) {
			
			exceptio.printStackTrace();
		}
	}

	
	/**
	 * This method will prepare the basic setup for the Eclipse configuration files backup.    
	 */
	private void setUp(){
		
		eclipseConfigurationFiles= new ArrayList<String>();
		eclipseConfigurationFiles.addAll(Arrays.asList(eclipsePluginFilesPaths));
		eclipseConfigurationFiles.addAll(Arrays.asList(eclipseClassPathFilesPaths));
		eclipseConfigurationFiles.addAll(Arrays.asList(versionAttributeFilesPaths));
		eclipseConfigurationFiles.addAll(Arrays.asList(versionNumberInJarFilesNames));
		
		propertiesFileHelper= PropertiesFileHelper.getInstance();
		propertiesFileHelper.createNewPropertyFile(backupFilepath);
		
	}
	
	/**
	 * This method will create a backup property file that contains all files to be copied in the backup temp directory.
	 * The backup property file has the following format. key=original location value= backup location.
	 */
	private void createBackupFile(){
		
		for (String configurationFilePath : eclipseConfigurationFiles){
			
			propertiesFileHelper.setProperty(configurationFilePath, buildBackupDirectory(configurationFilePath));
		}
		
		propertiesFileHelper.saveProperties();
	}
	
	/**
	 * 
	 */
	private void createBackupDirectories(){
		
		String currentBackupFilePath=null;
		String currentBackupDirectory=null;
		
		FileHelper fileHelper= FileHelper.getInstance();
		
		for (String configurationFilePath : eclipseConfigurationFiles){
						
			currentBackupFilePath=propertiesFileHelper.getProperty(configurationFilePath);
			currentBackupDirectory=getbackupConfigurationDirectories(currentBackupFilePath);
			File targetDirectory = new File(currentBackupDirectory.toString());
			
			if (!targetDirectory.exists())
			{
				fileHelper.cretateDirectoties(targetDirectory);
			}			
		}
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	private void backupEclipseConfigurationFiles() throws IOException{
		
		String currentBackupFilePath=null;
		
		FileHelper fileHelper= FileHelper.getInstance();
		
		for (String configurationFilePath : eclipseConfigurationFiles){
			
			currentBackupFilePath=propertiesFileHelper.getProperty(configurationFilePath);
			
			fileHelper.copy(configurationFilePath, currentBackupFilePath);
		}
	}
	
	/**
	 * 
	 * @param currentBackupFilePath
	 * @return
	 */
	private String getbackupConfigurationDirectories(String currentBackupFilePath){
		
		String [] directories= currentBackupFilePath.split("/");
		StringBuilder  directoriesBuffer = new StringBuilder();
		
		for (int index = 0; index 	< directories.length-1; index++)
		{
			directoriesBuffer.append(directories[index]);
			directoriesBuffer.append("/");
		}
		
		return directoriesBuffer.toString().substring(0, directoriesBuffer.length()-1	);         
	}
	
	/**
	 * 
	 * @param configurationFilePath
	 * @return
	 */
	private String buildBackupDirectory(String configurationFilePath){
		
		StringBuilder backupDirectory=new StringBuilder("./eclipse-configuration-backup");
		
		configurationFilePath=configurationFilePath.replace("..","");
		backupDirectory.append(configurationFilePath);
		return backupDirectory.toString();
		
	}

	/**
	 * This method will set all eclipse plugin.xml files. 
	 * @param eclipsePluginPathFiles This is an <code>String</code> representation of all eclipse files plugin.xml.
	 */	
	public void setEclipsePluginPathFiles(String eclipsePluginPathFiles)
	{
		this.eclipsePluginPathFiles = eclipsePluginPathFiles.replace("\\|", "");
	
		System.out.println(new StringBuilder("eclipsePluginPathFiles : ").append(this.eclipsePluginPathFiles .toString()) );
		
		if(this.eclipsePluginPathFiles .contains(",")){
			
			eclipsePluginFilesPaths= this.eclipsePluginPathFiles .split(",");
		}else{			
			
			eclipsePluginFilesPaths = new String[]{eclipsePluginPathFiles};
		}	
	}
	
	
	/**
	 * This method will set all eclipse .classpath files. 
	 * @param eclipseClassPathFiles This is an <code>String</code> representation of all eclipse files class path (.classpath).
	 */	
	public void setEclipseClassPathFiles(String eclipseClassPathFiles)
	{
		this.eclipseClassPathFiles = eclipseClassPathFiles.replace("\\|", "");
	
		System.out.println(new StringBuilder("eclipseClassPathFiles : ").append(this.eclipseClassPathFiles .toString()) );
		
		if(this.eclipseClassPathFiles .contains(",")){
			
			eclipseClassPathFilesPaths= this.eclipseClassPathFiles .split(",");
		}else{			
			
			eclipseClassPathFilesPaths= new String[]{eclipseClassPathFiles};
		}	
	}
	
	/**
	 * This method will set the properties inputs files. 
	 * @param inputFiles This is an <code>String</code> representation of all properties file path.
	 */
	public void setInputVersionNumberAttributeFiles(String inputFiles)
	{

		this.inputVersionNumberAttributeFiles = inputFiles.replace("\\|", "");
		
		System.out.println(new StringBuilder("inputVersionNumberAttributeFiles: ").append(this.inputVersionNumberAttributeFiles.toString()) );
		
		if(this.inputVersionNumberAttributeFiles.contains(",")){
			
			versionAttributeFilesPaths= this.inputVersionNumberAttributeFiles.split(",");				
		}else{			
			
			versionAttributeFilesPaths= new String[]{inputFiles};
		}
		
	}
	
	/**
	 * This method will set the properties inputs files which have hardcoded EQ version number in the jar names.
	 * @param inputVersionNumberInJarNamesFiles This is an <code>String</code> representation of all properties file path.
	 */
	public void setInputVersionNumberInJarNamesFiles(String inputVersionNumberInJarNamesFiles) {
		
		this.inputVersionNumberInJarNamesFiles = inputVersionNumberInJarNamesFiles.replace("\\|", "");
		
		System.out.println(new StringBuilder("inputVersionNumberInJarNamesFiles: ").append(this.inputVersionNumberInJarNamesFiles.toString()) );
		
		if(this.inputVersionNumberInJarNamesFiles.contains(",")){
			
			versionNumberInJarFilesNames= this.inputVersionNumberInJarNamesFiles.split(",");
			
		}else{			
			
			versionNumberInJarFilesNames= new String[]{inputVersionNumberInJarNamesFiles};
		}
	
	}
	
	
	

	public String[] getEclipseClassPathFilesPaths()
	{
		return eclipseClassPathFilesPaths;
	}


	public String[] getVersionAttributeFilesPaths()
	{
		return versionAttributeFilesPaths;
	}


	public String[] getVersionNumberInJarFilesNames()
	{
		return versionNumberInJarFilesNames;
	}


	public String[] getEclipsePluginFilesPaths()
	{
		return eclipsePluginFilesPaths;
	}


	public String getBackupFilepath()
	{
		return backupFilepath;
	}


	public void setBackupFilepath(String backupFilepath)
	{
		this.backupFilepath = backupFilepath;
	}		
		
	
	
	
	
}
