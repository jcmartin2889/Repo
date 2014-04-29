package com.misys.equation.function.beans;

/**
 * This class represents a user exit filter
 * 
 */
public class ValidationUserExitFilter
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ValidationUserExitFilter.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private int screen;
	private String mode;

	/**
	 * Construct a new empty user exit filter
	 * 
	 */
	public ValidationUserExitFilter()
	{
		super();
		mode = "";
	}

	/**
	 * Construct a new user exit filter
	 * 
	 * @param screen
	 *            - screen number
	 * @param mode
	 *            - mode
	 */
	public ValidationUserExitFilter(int screen, String mode)
	{
		this.screen = screen;
		this.mode = mode;
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
	 * Return the mode
	 * 
	 * @return the mode
	 */
	public String getMode()
	{
		return mode;
	}

	/**
	 * Set the mode
	 * 
	 * @param mode
	 *            - the mode
	 */
	public void setMode(String mode)
	{
		this.mode = mode;
	}

}
