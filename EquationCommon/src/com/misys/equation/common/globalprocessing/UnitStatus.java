package com.misys.equation.common.globalprocessing;

import java.io.Serializable;
import java.util.Observable;

import com.misys.equation.common.language.LanguageResources;

/**
 * This class is a bean to represent the current phase/mode of an Equation unit that extends Observable
 */
public class UnitStatus extends Observable implements Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UnitStatus.java 15717 2013-04-30 15:46:31Z whittap1 $";
	private String unitName = "";
	private String systemName = "";
	private boolean isAvailable;
	private InputMode mode;
	private String phase = "";
	private String today = "";
	private String tomorrow = "";
	private String yesterday = "";
	private String description = "";
	private String jobActiveDataAreas;
	private String suspendRequest = "";

	public enum InputMode
	{
		SUSP, NORM, EXTN, DISA;

		public String getLabel()
		{
			return LanguageResources.getString("Language." + name());
		}
	}
	/**
	 * Return the unit id
	 * 
	 * @return the unit id
	 * @equation.external
	 */
	public String getUnitName()
	{
		return validateNullValues(unitName);
	}

	public void setUnitName(String unitName)
	{
		if (this.unitName != null ? !this.unitName.equals(unitName) : unitName != null)
		{
			setChanged();
		}

		this.unitName = unitName;
	}
	/**
	 * Return the Equation system name where this unit resides
	 * 
	 * @return the Equation system name where this unit resides
	 */
	public String getSystemName()
	{
		return validateNullValues(systemName);
	}

	public void setSystemName(String systemName)
	{
		if (this.systemName != null ? !this.systemName.equals(systemName) : systemName != null)
		{
			setChanged();
		}

		this.systemName = systemName;
	}
	/**
	 * @return whether the unit is available
	 * @equation.external
	 */
	public boolean isAvailable()
	{
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable)
	{
		if (this.isAvailable != isAvailable)
		{
			setChanged();
		}

		this.isAvailable = isAvailable;
	}
	/**
	 * @return the mode of the unit
	 * @equation.external
	 */
	public String getMode()
	{
		return mode.getLabel();
	}
	/**
	 * @return equation mode
	 * @equation.external
	 */
	public String getEqnMode()
	{
		return mode.toString();
	}

	public void setMode(InputMode mode)
	{
		if (this.mode != null ? !this.mode.equals(mode) : mode != null)
		{
			setChanged();
		}
		this.mode = mode;
	}

	public String getPhase()
	{
		return validateNullValues(phase);
	}

	public void setPhase(String phase)
	{
		if (this.phase != null ? !this.phase.equals(phase) : phase != null)
		{
			setChanged();
		}

		this.phase = phase;
	}
	/**
	 * @return today's processing date
	 * @equation.external
	 */
	public String getToday()
	{
		return validateNullValues(today);
	}

	public void setToday(String today)
	{
		if (this.today != null ? !this.today.equals(today) : today != null)
		{
			setChanged();
		}

		this.today = today;
	}

	public String getTomorrow()
	{
		return validateNullValues(tomorrow);
	}

	public void setTomorrow(String tomorrow)
	{
		if (this.tomorrow != null ? !this.tomorrow.equals(tomorrow) : tomorrow != null)
		{
			setChanged();
		}

		this.tomorrow = tomorrow;
	}

	public String getYesterday()
	{
		return validateNullValues(yesterday);
	}

	public void setYesterday(String yesterday)
	{
		if (this.yesterday != null ? !this.yesterday.equals(yesterday) : yesterday != null)
		{
			setChanged();
		}

		this.yesterday = yesterday;
	}

	public boolean isEod()
	{
		return phase.substring(0, 1).equals("C");
	}

	public String getDescription()
	{
		return validateNullValues(description);
	}

	public void setDescription(String description)
	{
		if (this.description != null ? !this.description.equals(description) : description != null)
		{
			setChanged();
		}
		this.description = description;
	}

	public String getJobActiveDataAreas()
	{
		return jobActiveDataAreas;
	}

	public void setJobActiveDataAreas(String jobActiveDataAreas)
	{
		if (this.jobActiveDataAreas != null ? !this.jobActiveDataAreas.equals(jobActiveDataAreas) : jobActiveDataAreas != null)
		{
			setChanged();
		}

		this.jobActiveDataAreas = jobActiveDataAreas;
	}
	/**
	 * @return suspend request
	 * @equation.external
	 */
	public String getSuspendRequest()
	{
		return suspendRequest;
	}

	public void setSuspendRequest(String suspendRequest)
	{
		if (this.suspendRequest != null ? !this.suspendRequest.equals(suspendRequest) : suspendRequest != null)
		{
			setChanged();
		}
		this.suspendRequest = suspendRequest;
	}

	/**
	 * This method will validate the null values.
	 * 
	 * @param value
	 *            this is the value to be evaluated.
	 * @return the value or "" is case the value is null.
	 */
	private String validateNullValues(String value)
	{
		if (value == null)
		{
			return "";
		}
		return value;
	}
}