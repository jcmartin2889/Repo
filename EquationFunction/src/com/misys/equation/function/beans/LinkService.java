package com.misys.equation.function.beans;

import java.util.ArrayList;

public class LinkService extends Element
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LinkService.java 14799 2012-11-05 11:54:35Z williae1 $";

	/** Primary id */
	private String primaryId = "";

	/** Secondary id */
	private ArrayList<String> secondaryIds = new ArrayList<String>();

	/** Flag indicating if Desktop UI is not allowed in Production **/
	private boolean noDesktopInProduction = false;

	/** Version of plug-in used to create the service */
	private String pluginVersion = "";

	/** Release version of the patch/enhancement of the service */
	private String releaseVersion = "";

	/**
	 * Constructor
	 */
	public LinkService()
	{
		super();
	}

	/**
	 * Return the primary id
	 * 
	 * @return the primary id
	 */
	public String getPrimaryId()
	{
		return primaryId;
	}

	/**
	 * Set the primary id
	 * 
	 * @param primaryId
	 *            - the primary id
	 */
	public void setPrimaryId(String primaryId)
	{
		this.primaryId = primaryId;
	}

	/**
	 * Return the secondary ids
	 * 
	 * @return the secondary ids
	 */
	public ArrayList<String> getSecondaryIds()
	{
		return secondaryIds;
	}

	/**
	 * Set the secondary id
	 * 
	 * @param secondaryIds
	 *            - the secondary ids
	 */
	public void setSecondaryIds(ArrayList<String> secondaryIds)
	{
		this.secondaryIds = secondaryIds;
	}

	/**
	 * Add a secondary id
	 * 
	 * @param secondaryId
	 *            - the secondary id
	 */
	public void addSecondaryId(String secondaryId)
	{
		this.secondaryIds.add(secondaryId);
	}

	/**
	 * Gets the plug-in version
	 * 
	 * @return The version of plug-in used to create the service
	 */
	public String getPluginVersion()
	{
		return pluginVersion;
	}

	/**
	 * Sets the plug-in version
	 * 
	 * @param pluginVersion
	 *            - the version of plug-in used to create the service
	 */
	public void setPluginVersion(String pluginVersion)
	{
		this.pluginVersion = pluginVersion;
	}

	/**
	 * Gets the release version
	 * 
	 * @return The patch/enhancement used to despatch/install the service
	 */
	public String getReleaseVersion()
	{
		return releaseVersion;
	}

	/**
	 * Sets the release version
	 * 
	 * @param releaseVersion
	 *            The patch/enhancement used to despatch/install the service
	 */
	public void setReleaseVersion(String releaseVersion)
	{
		this.releaseVersion = releaseVersion;
	}

	/**
	 * Set the secondary id based on the list
	 * 
	 * @param ids
	 *            - the list of ids
	 */
	public void resetSecondaryId(String... ids)
	{
		secondaryIds.clear();
		for (String id : ids)
		{
			secondaryIds.add(id);
		}
	}

	/**
	 * Retrieve the secondary id specified in the index
	 * 
	 * @param index
	 *            - the index number
	 */
	public String rtvSecondaryId(int index)
	{
		if (index < this.secondaryIds.size())
		{
			return this.secondaryIds.get(index);
		}
		else
		{
			return null;
		}
	}

	/**
	 * Determine if there are secondary functions
	 * 
	 * @return true if there are secondary functions
	 */
	public boolean chkSecondaryServiceExist()
	{
		return secondaryIds.size() > 0;
	}

	/**
	 * Return the number of secondary service
	 * 
	 * @return the number of secondary service
	 */
	public int rtvSecondaryServiceCount()
	{
		return secondaryIds.size();
	}
	/**
	 * @return a <code>boolean</code> indicating if Desktop UI is not allow in Production
	 */
	public boolean isNoDesktopInProduction()
	{
		return noDesktopInProduction;
	}

	/**
	 * Set No Desktop UI In Production Flag, true if Desktop UI not allow in Production, otherwise false
	 * 
	 * @param noDesktopInProduction
	 */
	public void setNoDesktopInProduction(boolean noDesktopInProduction)
	{
		this.noDesktopInProduction = noDesktopInProduction;
	}
}
