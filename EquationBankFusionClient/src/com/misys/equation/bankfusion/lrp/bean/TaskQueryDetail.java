package com.misys.equation.bankfusion.lrp.bean;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import bf.com.misys.bankfusion.workflow.attributes.BasicTaskAttributes;

import com.misys.equation.bankfusion.lrp.engine.TaskEngineToolbox;
import com.misys.equation.bankfusion.tools.LRPToolbox;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;

/*************************************************************************************
 * Program History
 * 
 * <pre>
 * Project Name : BankFusion Equation Long Running Processes 
 * Version : BFEQ phase 4 
 * Package Name : com.misys.equation.bankfusion.lrp.bean 
 * Program ID : TaskQueryDetail.java 
 * Program Description : A Bean class containing details of task as needed by Equation, 
 *      specifically for the work load processing. 
 * Environment : Java 1.5 
 * Author : rpatrici 
 * Creation Date : 09 August 2011
 * 
 * Modification History : Version Date Person Name Remarks
 * 
 * Copyright(C) 2011-Misys Plc. All Rights Reserved.
 * </pre>
 *************************************************************************************/

public class TaskQueryDetail
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TaskQueryDetail.java 17371 2013-10-04 07:36:12Z perkinj1 $";

	// Log
	private static final EquationLogger LOG = new EquationLogger(TaskQueryDetail.class);

	private String tkiid;
	private String createDate;
	private String createTime;
	private String originator;
	private String state;
	private String optionId;
	private String type;
	private String owner;
	private String zone;
	private boolean isClaimed;

	public TaskQueryDetail(BasicTaskAttributes taskDetail)
	{
		tkiid = taskDetail.getTaskID();
		// optionId = taskDetail.getBfTaskCustom3();
		zone = taskDetail.getZone();
		if (zone != null)
		{
			String[] temp = zone.split("_");
			if (temp.length == 2)
			{
				optionId = temp[0];
				type = temp[1];
			}
		}

		originator = taskDetail.getTaskOriginator();
		state = taskDetail.getTaskStatus();
		// type = taskDetail.getBfTaskCustom1();
		// type = taskDetail.getTaskType();
		owner = taskDetail.getOwner();
		chgTime(taskDetail.getFirstActivatedDate());
		setClaimed(state.equals(TaskEngineToolbox.CLAIMED_STATE));

		// make sure these are not null
		if (optionId == null)
		{
			optionId = "";
		}
		if (zone == null)
		{
			zone = "";
		}
		if (type == null)
		{
			type = "";
		}
	}

	/**
	 * Get Task Id property
	 * 
	 * @return the property Task Id
	 */
	public String getTkiid()
	{
		return tkiid;
	}

	/**
	 * Sets the property Task Id
	 * 
	 * @param tkiid
	 *            the Task Id property to set
	 */
	public void setTkiid(String tkiid)
	{
		this.tkiid = tkiid;
	}

	/**
	 * Get Creation Date property
	 * 
	 * @return the createDate
	 */
	public String getCreateDate()
	{
		return createDate;
	}

	/**
	 * Sets the creation date property
	 * 
	 * @param createDate
	 *            the Creation Date property to set
	 */
	public void setCreateDate(String createDate)
	{
		this.createDate = createDate;
	}

	/**
	 * Get the creation time property
	 * 
	 * @return the createTime
	 */
	public String getCreateTime()
	{
		return createTime;
	}

	/**
	 * Sets the creation time property
	 * 
	 * @param createTime
	 *            the Creation Time property to set
	 */
	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	/**
	 * Get the User property
	 * 
	 * @return the originator
	 */
	public String getOriginator()
	{
		return originator;
	}

	/**
	 * Sets the User property
	 * 
	 * @param originator
	 *            the User property to set
	 */
	public void setOriginator(String originator)
	{
		this.originator = originator;
	}

	/**
	 * Get the State property
	 * 
	 * @return the state
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * Sets the State property
	 * 
	 * @param state
	 *            the State property to set
	 */
	public void setState(String state)
	{
		this.state = state;
	}

	/**
	 * Get the Option Id property
	 * 
	 * @return the optionId
	 */
	public String getOptionId()
	{
		return optionId;
	}

	/**
	 * Sets the Option Id property
	 * 
	 * @param optionId
	 *            the Option Id property to set
	 */
	public void setOptionId(String optionId)
	{
		this.optionId = optionId;
	}

	/**
	 * Get the Task Type property
	 * 
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Sets the Task Type property
	 * 
	 * @param type
	 *            the Task Type property to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * Get the Task Owner property
	 * 
	 * @return the owner
	 */
	public String getOwner()
	{
		return owner;
	}

	/**
	 * Sets the Task Owner propert
	 * 
	 * @param owner
	 *            the Task Owner property to set
	 */
	public void setOwner(String owner)
	{
		this.owner = owner;
	}

	/**
	 * Get the Zone property
	 * 
	 * @return the zone
	 */
	public String getZone()
	{
		return zone;
	}

	/**
	 * Set the Zone property
	 * 
	 * @param zone
	 *            the zone to set
	 */
	public void setZone(String zone)
	{
		this.zone = zone;
	}

	/**
	 * Parse the returned date/time from BankFusion
	 * 
	 * @param timebf
	 *            - the date/time in XML Gregorian Calendar format
	 */
	public void chgTime(Timestamp timebf)
	{
		Calendar cal = GregorianCalendar.getInstance();

		// parse it
		Date date = new Date();
		date.setTime(timebf.getTime());
		if (date != null)
		{
			cal.setTime(date);
		}
		else
		{
			LOG.error("Incorrect date format for task " + tkiid + " - " + timebf);
		}

		createDate = Toolbox.leftPad(String.valueOf(cal.get(Calendar.YEAR) - 1900), "0", 3)
						+ Toolbox.leftPad(String.valueOf(cal.get(Calendar.MONTH) + 1), "0", 2)
						+ Toolbox.leftPad(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)), "0", 2);

		createTime = Toolbox.leftPad(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), "0", 2)
						+ Toolbox.leftPad(String.valueOf(cal.get(Calendar.MINUTE)), "0", 2)
						+ Toolbox.leftPad(String.valueOf(cal.get(Calendar.SECOND)), "0", 2);

	}

	/**
	 * Determine if task is claimed
	 * 
	 * @return true if task is claimed
	 */
	public boolean isClaimed()
	{
		return isClaimed;
	}

	/**
	 * Set if task is claimed
	 * 
	 * @param isClaimed
	 *            - true if task is claimed
	 */
	public void setClaimed(boolean isClaimed)
	{
		this.isClaimed = isClaimed;
	}

	/**
	 * Determine if task is already complete
	 * 
	 * @return true if task is already complete
	 */
	public boolean isTaskCompleted()
	{
		return !(state.equals(TaskEngineToolbox.CLAIMED_STATE) || state.equals(TaskEngineToolbox.READY_STATE));
	}

	/**
	 * Determine if task is valid for the user
	 * 
	 * @return true if task is valid for the user
	 */
	public boolean isTaskValidForUser(EquationUser equationUser)
	{
		String userId = equationUser.getUserId();
		if (EquationCommonContext.isCASAuthentication())
		{
			userId = equationUser.getCASUserId();
		}

		// valid for the user?
		if (LRPToolbox.isValidTask(this, userId))
		{
			// make sure it is also valid for the unit
			return isTaskValid(equationUser.getEquationUnit());
		}
		else
		{
			return false;
		}
	}

	/**
	 * Determine if task is valid for the unit
	 * 
	 * @param equationUnit
	 *            - the EquationUnit
	 * 
	 * @return true if valid for the unit
	 */
	public boolean isTaskValid(EquationUnit equationUnit)
	{
		// make sure a valid Equation service
		try
		{
			if (equationUnit.getRecords().getGBRecord(optionId) == null)
			{
				LOG.error("The task " + tkiid + " is not linked to a valid Equation service " + optionId);
				return false;
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			return false;
		}

		return true;
	}

}
