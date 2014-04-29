package com.misys.equation.common.dao.beans;

/**
 * ACNRecord data-model.
 * 
 * The ACN file holds Validation Java user exit control records
 */
public class ACNRecordDataModel extends AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACNRecordDataModel.java 11052 2011-05-26 08:08:54Z perkinj1 $";
	/**  
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static String RECORD_NAME = "ACNPF";

	private String program; // ACNPGM
	private String className; // ACNCLN

	/**
	 * Default constructor
	 */
	public ACNRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);

	}

	// ---getters and setters----//

	/**
	 * Return the program
	 * 
	 * @return the program name
	 */
	public String getProgram()
	{
		return program;
	}
	/**
	 * Set the program
	 * 
	 * @param program
	 *            - the program name
	 */
	public void setProgram(String program)
	{
		this.program = program.trim();
	}

	/**
	 * Return the className
	 * 
	 * @return the className
	 */
	public String getClassName()
	{
		return className;
	}

	/**
	 * Set the className
	 * 
	 * @param className
	 *            - the className to set
	 */
	public void setClassName(String className)
	{
		this.className = className.trim();
	}

}
