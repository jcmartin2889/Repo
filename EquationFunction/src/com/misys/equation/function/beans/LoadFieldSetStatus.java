package com.misys.equation.function.beans;

public class LoadFieldSetStatus
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LoadFieldSetStatus.java 6793 2010-03-31 12:10:20Z deroset $";

	private String id;
	private String parent;
	private int level;
	private boolean keyExist;
	private boolean detailOpen;

	/**
	 * Construct an empty field set status
	 */
	public LoadFieldSetStatus()
	{
	}

	/**
	 * Construct a new Display Input Set Status
	 * 
	 * @param id
	 *            - id
	 * @param parents
	 *            - the top most parent
	 * @param level
	 *            - the nth level from the parent
	 * @param keyExist
	 *            - key field exists
	 */
	public LoadFieldSetStatus(String id, String parent, int level, boolean keyExist)
	{
		this.id = id;
		this.parent = parent;
		this.level = level;
		this.keyExist = keyExist;

		// there is no key, then detail should always be open
		this.detailOpen = !keyExist;
	}

	/**
	 * Return the id
	 * 
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Set the id
	 * 
	 * @param id
	 *            - the id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Return the list of parents
	 * 
	 * @return the list of parents
	 */
	public String getParent()
	{
		return parent;
	}

	/**
	 * Set the list of parent. The parent is in the format of root.parent1.parent2.parent3
	 * 
	 * @param parents
	 *            - the parent
	 */
	public void setParent(String parent)
	{
		this.parent = parent;
	}

	/**
	 * Return the level
	 * 
	 * @return the level
	 */
	public int getLevel()
	{
		return level;
	}

	/**
	 * Set the level
	 * 
	 * @param level
	 *            - the level
	 */
	public void setLevel(int level)
	{
		this.level = level;
	}

	/**
	 * Determine whether key fields exists in the input field set
	 * 
	 * @return true, if key fields exists in the input field set
	 */
	public boolean isKeyExist()
	{
		return keyExist;
	}

	/**
	 * Set whether key fields exists in the input field set
	 * 
	 * @param keyExist
	 *            - true, if key fields exists in the input field set
	 */
	public void setKeyExist(boolean keyExist)
	{
		this.keyExist = keyExist;
	}

	/**
	 * Determine if the detail screen is open
	 * 
	 * @return true, if the detail screen is open
	 */
	public boolean isDetailOpen()
	{
		return detailOpen;
	}

	/**
	 * Set whether the detail screen is open
	 * 
	 * @param detailOpen
	 *            - true, if the detail screen is open
	 */
	public void setDetailOpen(boolean detailOpen)
	{
		this.detailOpen = detailOpen;
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
		StringBuffer buffer = new StringBuffer();
		buffer.append("id=" + id + " ");
		buffer.append("parent=" + parent + " ");
		buffer.append("level=" + level + " ");
		buffer.append("isKeyExist=" + keyExist + " ");
		buffer.append("isDetailOpen=" + detailOpen + " ");
		return buffer.toString();
	}
}
