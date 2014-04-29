package com.misys.equation.common.ant.builder.helpText.models;

public class AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbsRecord.java 4741 2009-09-16 16:33:23Z esther.williams $";
	// file name
	private String eqFileName;

	// library where the file name is located
	private String library;

	/**
	 * Return the file name
	 * 
	 */
	public String getEqFileName()
	{
		return eqFileName;
	}

	/**
	 * Set the file name
	 * 
	 * @param fileName
	 *            - the file name
	 */
	public void setEqFileName(String eqFileName)
	{
		this.eqFileName = eqFileName;
	}

	/**
	 * Return the library where the file is located
	 * 
	 * @return the library where the file is located
	 */
	public String getLibrary()
	{
		return library;
	}

	/**
	 * Set the library where the file is located
	 * 
	 * @param library
	 *            - the library where the file is located
	 */
	public void setLibrary(String library)
	{
		this.library = library;
	}
}
