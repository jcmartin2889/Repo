package com.misys.equation.common.ant.builder.tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.misys.equation.common.ant.builder.helpers.FileHelper;
import com.misys.equation.common.ant.builder.helpers.PropertiesFileHelper;

public class EclipseConfigurationFilesRestoreTag extends Task {

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationVersionNumberTag.java,v 1.9 2012/01/17 17:32:21 deroset Exp $";
	
		
	private List<String> eclipseConfigurationFiles;
	private PropertiesFileHelper propertiesFileHelper ;
	
	private String backupFilepath;
	
	
		

	@Override
	public void execute() throws BuildException
	{
		try {
			
			setUp();
			restoreEclipseConfigurationFiles();
			
		} catch (Exception exceptio) {
			
			exceptio.printStackTrace();
		}
	}

	
	/**
	 * This method will prepare the basic setup for the Eclipse configuration files backup.    
	 */
	private void setUp(){
		
		eclipseConfigurationFiles= new ArrayList<String>();
		
		propertiesFileHelper= PropertiesFileHelper.getInstance();
		propertiesFileHelper.setFileName(backupFilepath);
		
	}
	/**
	 * 
	 * @throws IOException
	 */
	private void restoreEclipseConfigurationFiles() throws IOException{
		
		String currentBackupFilePath=null;
		
		FileHelper fileHelper= FileHelper.getInstance();
		Enumeration<Object> keys= propertiesFileHelper.getKeys();
		
		while (keys.hasMoreElements()) {
			
		  String key = (String)keys.nextElement();
		  currentBackupFilePath=propertiesFileHelper.getProperty(key);
		  
		  fileHelper.copy(currentBackupFilePath, key);
		  
		}
		
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
		
	
