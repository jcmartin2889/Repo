package com.misys.equation.common.globalprocessing;

import java.io.Serializable;
import java.util.Calendar;

import com.ibm.as400.access.SystemStatus;
import com.misys.equation.common.globalprocessing.audit.GlobalAuditUtils;

public class ServerStatus implements Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ServerStatus.java 9210 2010-09-17 15:31:21Z deroset $";
	/** */
	private static final long serialVersionUID = -2477226262092711623L;
	/** */
	private String systemId;
	/** */
	private float percentCPUUsage;
	/** */
	private float percentDiskUsage;
	/** */
	private long numberOfUnrepliedMessages = -1;
	/** */
	private Calendar serverTime;

	public ServerStatus()
	{
	}

	public ServerStatus(SystemStatus systemStatus) throws Exception
	{
		systemStatus.refreshCache();
		percentDiskUsage = systemStatus.getPercentSystemASPUsed();
		percentCPUUsage = systemStatus.getPercentProcessingUnitUsed();
		serverTime = GlobalAuditUtils.getQDateTime(systemStatus.getSystem());
	}

	public ServerStatus(String systemId, float percentCPUUsage, float percentDiskUsage, long numberOfUnrepliedMessages,
					Calendar serverTime)
	{
		super();
		this.systemId = systemId;
		this.percentCPUUsage = percentCPUUsage;
		this.percentDiskUsage = percentDiskUsage;
		this.numberOfUnrepliedMessages = numberOfUnrepliedMessages;
		this.serverTime = serverTime;
	}

	/**
	 * @return the systemId
	 */
	public String getSystemId()
	{
		return systemId;
	}

	/**
	 * @param systemId
	 *            the systemId to set
	 */
	public void setSystemId(String systemId)
	{
		this.systemId = systemId;
	}

	/**
	 * @return the percentCPUUsage
	 */
	public float getPercentCPUUsage()
	{
		return percentCPUUsage;
	}

	/**
	 * @param percentCPUUsage
	 *            the percentCPUUsage to set
	 */
	public void setPercentCPUUsage(float percentCPUUsage)
	{
		this.percentCPUUsage = percentCPUUsage;
	}

	/**
	 * @return the percentDiskUsage
	 */
	public float getPercentDiskUsage()
	{
		return percentDiskUsage;
	}

	/**
	 * @param percentDiskUsage
	 *            the percentDiskUsage to set
	 */
	public void setPercentDiskUsage(float percentDiskUsage)
	{
		this.percentDiskUsage = percentDiskUsage;
	}

	/**
	 * @return the numberOfUnrepliedMessages
	 */
	public long getNumberOfUnrepliedMessages()
	{
		return numberOfUnrepliedMessages;
	}

	/**
	 * @param numberOfUnrepliedMessages
	 *            the numberOfUnrepliedMessages to set
	 */
	public void setNumberOfUnrepliedMessages(long numberOfUnrepliedMessages)
	{
		this.numberOfUnrepliedMessages = numberOfUnrepliedMessages;
	}

	/**
	 * @return the serverTime
	 */
	public Calendar getServerTime()
	{
		return serverTime;
	}

	/**
	 * @param serverTime
	 *            the serverTime to set
	 */
	public void setServerTime(Calendar serverTime)
	{
		this.serverTime = serverTime;
	}
}
