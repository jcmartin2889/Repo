package com.misys.equation.common.dao.beans;


/**
 * Data transfer object for a ACORecordDataModel (ANCPF record)
 * 
 * These filters are inclusion filters; for a validation program they specify the combination of screen and mode that will result in
 * a call out to the Java User Exit code
 * 
 */
public class ACORecordDataModel extends AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACORecordDataModel.java 12399 2011-12-13 03:57:50Z fraramos $";

	private static final String RECORD_NAME = "ACOPF";
	private String userExit; // ACOPGM
	private int screen; // ACOSCN
	private char mode; // ACOMOD

	/**
	 * Constructor
	 */
	public ACORecordDataModel()
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
