package com.misys.equation.common.files;

import com.misys.equation.common.dao.beans.AbsRecord;

/**
 * As a standard, please follow the persistence framework. Use ACORecordDataModel instead.
 * 
 * Data transfer object for a ValidationJavaUserExitFilter (ANCPF record)
 * 
 * These filters are inclusion filters; for a validation program they specify the combination of screen and mode that will result in
 * a call out to the Java User Exit code
 * 
 * @author PERKINJ1
 */
@Deprecated
public class ValidationJavaUserExitFilterRecord extends AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ValidationJavaUserExitFilterRecord.java 13958 2012-07-30 07:17:11Z mamiguel $";

	private static final String RECORD_NAME = "ACOPF";
	private String userExit; // ACOPGM
	private int screen; // ACOSCN
	private char mode; // ACOMOD

	/**
	 * Constructor
	 */
	public ValidationJavaUserExitFilterRecord()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	/**
	 * Return the user exit name
	 * 
	 * @return the user exit name
	 */
	public String getUserExit()
	{
		return userExit;
	}

	/**
	 * Set the user exit name
	 * 
	 * @param userExit
	 *            - the user exit name
	 */
	public void setUserExit(String userExit)
	{
		this.userExit = userExit;
	}

	/**
	 * Return the screen number
	 * 
	 * @return the screen number
	 */
	public int getScreen()
	{
		return screen;
	}

	/**
	 * Set the screen number
	 * 
	 * @param screen
	 *            - the screen number
	 */
	public void setScreen(int screen)
	{
		this.screen = screen;
	}

	/**
	 * Return the user exit mode
	 * 
	 * @return the user exit mode
	 */
	public char getMode()
	{
		return mode;
	}

	/**
	 * Set the user exit mode
	 * 
	 * @param mode
	 *            - the user exit mode
	 */
	public void setMode(char mode)
	{
		this.mode = mode;
	}

}
