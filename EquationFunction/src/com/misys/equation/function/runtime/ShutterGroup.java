package com.misys.equation.function.runtime;

public class ShutterGroup
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ShutterGroup.java 6793 2010-03-31 12:10:20Z deroset $";
	private boolean open;

	/**
	 * Construct the default shutter status
	 */
	public ShutterGroup()
	{
		open = true;
	}

	/**
	 * Construct the shutter with the specified status
	 */
	public ShutterGroup(boolean open)
	{
		this.open = open;
	}

	/**
	 * Determine if shutter is open
	 * 
	 * @return true if shutter is open
	 */
	public boolean isOpen()
	{
		return open;
	}

	/**
	 * Set the shutter
	 * 
	 * @param open
	 *            - true if the shutter is open
	 */
	public void setOpen(boolean open)
	{
		this.open = open;
	}

	/**
	 * Return the String representation
	 * 
	 * @param the
	 *            String representation
	 */
	@Override
	public String toString()
	{
		return String.valueOf(open);
	}

}
