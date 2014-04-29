package com.misys.equation.common.globalprocessing;

import java.io.Serializable;

// Stub - expand as necessary
@SuppressWarnings("serial")
public class UserDetails implements Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UserDetails.java 9210 2010-09-17 15:31:21Z deroset $";
	private String userId;
	private String userDescription;
	private String ccsid;
	private String groupProfile;
	private String homeDirectory;
	private String countryID;
	private String initialMenu;
	private String status;
	private String storageUsed;

	public UserDetails(String unitId, String userDescription, String ccsid, String groupProfile, String countryID,
					String homeDirectory, String initialMenu, String status, String storageUsed)
	{
		this.userId = unitId;
		this.userDescription = userDescription;
		this.ccsid = ccsid;
		this.groupProfile = groupProfile;
		this.homeDirectory = homeDirectory;
		this.countryID = countryID;
		this.initialMenu = initialMenu;
		this.status = status;
		this.storageUsed = storageUsed;
	}
	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getUserDescription()
	{
		return userDescription;
	}

	public void setUserDescription(String userDescription)
	{
		this.userDescription = userDescription;
	}

	public String getCcsid()
	{
		return ccsid;
	}

	public void setCcsid(String ccsid)
	{
		this.ccsid = ccsid;
	}
	public String getGroupProfile()
	{
		return groupProfile;
	}
	public void setGroupProfile(String groupProfile)
	{
		this.groupProfile = groupProfile;
	}
	public String getHomeDirectory()
	{
		return homeDirectory;
	}
	public void setHomeDirectory(String homeDirectory)
	{
		this.homeDirectory = homeDirectory;
	}
	public String getCountryID()
	{
		return countryID;
	}
	public void setCountryID(String countryID)
	{
		this.countryID = countryID;
	}
	public String getInitialMenu()
	{
		return initialMenu;
	}
	public void setInitialMenu(String initialMenu)
	{
		this.initialMenu = initialMenu;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getStorageUsed()
	{
		return storageUsed;
	}
	public void setStorageUsed(String storageUsed)
	{
		this.storageUsed = storageUsed;
	}
	@Override
	public String toString()
	{
		return userId + " " + userDescription + " " + ccsid;
	}
}