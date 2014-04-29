/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Class JournalKey.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class JournalKey implements java.io.Serializable
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FileProcessor.java 7606 2010-06-01 17:04:23Z MACDONP1 $";
	// --------------------------/
	// - Class/Member Variables -/
	// --------------------------/

	/**
	 * Field serialVersionUID.
	 */
	public static final long serialVersionUID = 1L;

	/**
	 * Field _workstationId.
	 */
	private java.lang.String _workstationId;

	/**
	 * Field _dayInMonth.
	 */
	private java.lang.String _dayInMonth;

	/**
	 * Field _time.
	 */
	private java.lang.String _time;

	/**
	 * Field _sequence.
	 */
	private java.lang.String _sequence;

	/**
	 * Field _programRoot.
	 */
	private java.lang.String _programRoot;

	/**
	 * Field _transactionType.
	 */
	private java.lang.String _transactionType;

	/**
	 * Field _createDate.
	 */
	private java.lang.String _createDate;

	/**
	 * Field _ccLinkTime.
	 */
	private java.lang.String _ccLinkTime;

	/**
	 * Field _ccSequence.
	 */
	private java.lang.String _ccSequence;

	/**
	 * Field _jobNumber.
	 */
	private java.lang.String _jobNumber;

	/**
	 * Field _recoveryStatus.
	 */
	private java.lang.String _recoveryStatus;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public JournalKey()
	{
		super();
	}

	// -----------/
	// - Methods -/
	// -----------/

	/**
	 * Overrides the java.lang.Object.equals method.
	 * 
	 * @param obj
	 * @return true if the objects are equal.
	 */
	@Override()
	public boolean equals(final java.lang.Object obj)
	{
		if (this == obj)
			return true;

		if (obj instanceof JournalKey)
		{

			JournalKey temp = (JournalKey) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._workstationId != null)
			{
				if (temp._workstationId == null)
					return false;
				if (this._workstationId != temp._workstationId)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._workstationId);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._workstationId);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._workstationId);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._workstationId);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._workstationId.equals(temp._workstationId))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._workstationId);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._workstationId);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._workstationId);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._workstationId);
					}
				}
			}
			else if (temp._workstationId != null)
				return false;
			if (this._dayInMonth != null)
			{
				if (temp._dayInMonth == null)
					return false;
				if (this._dayInMonth != temp._dayInMonth)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._dayInMonth);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._dayInMonth);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._dayInMonth);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._dayInMonth);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._dayInMonth.equals(temp._dayInMonth))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._dayInMonth);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._dayInMonth);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._dayInMonth);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._dayInMonth);
					}
				}
			}
			else if (temp._dayInMonth != null)
				return false;
			if (this._time != null)
			{
				if (temp._time == null)
					return false;
				if (this._time != temp._time)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._time);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._time);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._time);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._time);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._time.equals(temp._time))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._time);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._time);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._time);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._time);
					}
				}
			}
			else if (temp._time != null)
				return false;
			if (this._sequence != null)
			{
				if (temp._sequence == null)
					return false;
				if (this._sequence != temp._sequence)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._sequence);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._sequence);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sequence);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sequence);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._sequence.equals(temp._sequence))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sequence);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sequence);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sequence);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sequence);
					}
				}
			}
			else if (temp._sequence != null)
				return false;
			if (this._programRoot != null)
			{
				if (temp._programRoot == null)
					return false;
				if (this._programRoot != temp._programRoot)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._programRoot);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._programRoot);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._programRoot);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._programRoot);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._programRoot.equals(temp._programRoot))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._programRoot);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._programRoot);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._programRoot);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._programRoot);
					}
				}
			}
			else if (temp._programRoot != null)
				return false;
			if (this._transactionType != null)
			{
				if (temp._transactionType == null)
					return false;
				if (this._transactionType != temp._transactionType)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._transactionType);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._transactionType);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._transactionType);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._transactionType);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._transactionType.equals(temp._transactionType))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._transactionType);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._transactionType);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._transactionType);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._transactionType);
					}
				}
			}
			else if (temp._transactionType != null)
				return false;
			if (this._createDate != null)
			{
				if (temp._createDate == null)
					return false;
				if (this._createDate != temp._createDate)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._createDate);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._createDate);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._createDate);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._createDate);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._createDate.equals(temp._createDate))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._createDate);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._createDate);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._createDate);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._createDate);
					}
				}
			}
			else if (temp._createDate != null)
				return false;
			if (this._ccLinkTime != null)
			{
				if (temp._ccLinkTime == null)
					return false;
				if (this._ccLinkTime != temp._ccLinkTime)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._ccLinkTime);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._ccLinkTime);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._ccLinkTime);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._ccLinkTime);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._ccLinkTime.equals(temp._ccLinkTime))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._ccLinkTime);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._ccLinkTime);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._ccLinkTime);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._ccLinkTime);
					}
				}
			}
			else if (temp._ccLinkTime != null)
				return false;
			if (this._ccSequence != null)
			{
				if (temp._ccSequence == null)
					return false;
				if (this._ccSequence != temp._ccSequence)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._ccSequence);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._ccSequence);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._ccSequence);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._ccSequence);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._ccSequence.equals(temp._ccSequence))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._ccSequence);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._ccSequence);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._ccSequence);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._ccSequence);
					}
				}
			}
			else if (temp._ccSequence != null)
				return false;
			if (this._jobNumber != null)
			{
				if (temp._jobNumber == null)
					return false;
				if (this._jobNumber != temp._jobNumber)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._jobNumber);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._jobNumber);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._jobNumber);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._jobNumber);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._jobNumber.equals(temp._jobNumber))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._jobNumber);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._jobNumber);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._jobNumber);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._jobNumber);
					}
				}
			}
			else if (temp._jobNumber != null)
				return false;
			if (this._recoveryStatus != null)
			{
				if (temp._recoveryStatus == null)
					return false;
				if (this._recoveryStatus != temp._recoveryStatus)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._recoveryStatus);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._recoveryStatus);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._recoveryStatus);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._recoveryStatus);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._recoveryStatus.equals(temp._recoveryStatus))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._recoveryStatus);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._recoveryStatus);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._recoveryStatus);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._recoveryStatus);
					}
				}
			}
			else if (temp._recoveryStatus != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'ccLinkTime'.
	 * 
	 * @return the value of field 'CcLinkTime'.
	 */
	public java.lang.String getCcLinkTime()
	{
		return this._ccLinkTime;
	}

	/**
	 * Returns the value of field 'ccSequence'.
	 * 
	 * @return the value of field 'CcSequence'.
	 */
	public java.lang.String getCcSequence()
	{
		return this._ccSequence;
	}

	/**
	 * Returns the value of field 'createDate'.
	 * 
	 * @return the value of field 'CreateDate'.
	 */
	public java.lang.String getCreateDate()
	{
		return this._createDate;
	}

	/**
	 * Returns the value of field 'dayInMonth'.
	 * 
	 * @return the value of field 'DayInMonth'.
	 */
	public java.lang.String getDayInMonth()
	{
		return this._dayInMonth;
	}

	/**
	 * Returns the value of field 'jobNumber'.
	 * 
	 * @return the value of field 'JobNumber'.
	 */
	public java.lang.String getJobNumber()
	{
		return this._jobNumber;
	}

	/**
	 * Returns the value of field 'programRoot'.
	 * 
	 * @return the value of field 'ProgramRoot'.
	 */
	public java.lang.String getProgramRoot()
	{
		return this._programRoot;
	}

	/**
	 * Returns the value of field 'recoveryStatus'.
	 * 
	 * @return the value of field 'RecoveryStatus'.
	 */
	public java.lang.String getRecoveryStatus()
	{
		return this._recoveryStatus;
	}

	/**
	 * Returns the value of field 'sequence'.
	 * 
	 * @return the value of field 'Sequence'.
	 */
	public java.lang.String getSequence()
	{
		return this._sequence;
	}

	/**
	 * Returns the value of field 'time'.
	 * 
	 * @return the value of field 'Time'.
	 */
	public java.lang.String getTime()
	{
		return this._time;
	}

	/**
	 * Returns the value of field 'transactionType'.
	 * 
	 * @return the value of field 'TransactionType'.
	 */
	public java.lang.String getTransactionType()
	{
		return this._transactionType;
	}

	/**
	 * Returns the value of field 'workstationId'.
	 * 
	 * @return the value of field 'WorkstationId'.
	 */
	public java.lang.String getWorkstationId()
	{
		return this._workstationId;
	}

	/**
	 * Overrides the java.lang.Object.hashCode method.
	 * <p>
	 * The following steps came from <b>Effective Java Programming Language Guide</b> by Joshua Bloch, Chapter 3
	 * 
	 * @return a hash code value for the object.
	 */
	public int hashCode()
	{
		int result = 17;

		long tmp;
		if (_workstationId != null && !org.castor.core.util.CycleBreaker.startingToCycle(_workstationId))
		{
			result = 37 * result + _workstationId.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_workstationId);
		}
		if (_dayInMonth != null && !org.castor.core.util.CycleBreaker.startingToCycle(_dayInMonth))
		{
			result = 37 * result + _dayInMonth.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_dayInMonth);
		}
		if (_time != null && !org.castor.core.util.CycleBreaker.startingToCycle(_time))
		{
			result = 37 * result + _time.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_time);
		}
		if (_sequence != null && !org.castor.core.util.CycleBreaker.startingToCycle(_sequence))
		{
			result = 37 * result + _sequence.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_sequence);
		}
		if (_programRoot != null && !org.castor.core.util.CycleBreaker.startingToCycle(_programRoot))
		{
			result = 37 * result + _programRoot.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_programRoot);
		}
		if (_transactionType != null && !org.castor.core.util.CycleBreaker.startingToCycle(_transactionType))
		{
			result = 37 * result + _transactionType.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_transactionType);
		}
		if (_createDate != null && !org.castor.core.util.CycleBreaker.startingToCycle(_createDate))
		{
			result = 37 * result + _createDate.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_createDate);
		}
		if (_ccLinkTime != null && !org.castor.core.util.CycleBreaker.startingToCycle(_ccLinkTime))
		{
			result = 37 * result + _ccLinkTime.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_ccLinkTime);
		}
		if (_ccSequence != null && !org.castor.core.util.CycleBreaker.startingToCycle(_ccSequence))
		{
			result = 37 * result + _ccSequence.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_ccSequence);
		}
		if (_jobNumber != null && !org.castor.core.util.CycleBreaker.startingToCycle(_jobNumber))
		{
			result = 37 * result + _jobNumber.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_jobNumber);
		}
		if (_recoveryStatus != null && !org.castor.core.util.CycleBreaker.startingToCycle(_recoveryStatus))
		{
			result = 37 * result + _recoveryStatus.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_recoveryStatus);
		}

		return result;
	}

	/**
	 * Method isValid.
	 * 
	 * @return true if this object is valid according to the schema
	 */
	public boolean isValid()
	{
		try
		{
			validate();
		}
		catch (org.exolab.castor.xml.ValidationException vex)
		{
			return false;
		}
		return true;
	}

	/**
	 * 
	 * 
	 * @param out
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 */
	public void marshal(final java.io.Writer out) throws org.exolab.castor.xml.MarshalException,
					org.exolab.castor.xml.ValidationException
	{
		org.exolab.castor.xml.Marshaller.marshal(this, out);
	}

	/**
	 * 
	 * 
	 * @param handler
	 * @throws java.io.IOException
	 *             if an IOException occurs during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 */
	public void marshal(final org.xml.sax.ContentHandler handler) throws java.io.IOException,
					org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		org.exolab.castor.xml.Marshaller.marshal(this, handler);
	}

	/**
	 * Sets the value of field 'ccLinkTime'.
	 * 
	 * @param ccLinkTime
	 *            the value of field 'ccLinkTime'.
	 */
	public void setCcLinkTime(final java.lang.String ccLinkTime)
	{
		this._ccLinkTime = ccLinkTime;
	}

	/**
	 * Sets the value of field 'ccSequence'.
	 * 
	 * @param ccSequence
	 *            the value of field 'ccSequence'.
	 */
	public void setCcSequence(final java.lang.String ccSequence)
	{
		this._ccSequence = ccSequence;
	}

	/**
	 * Sets the value of field 'createDate'.
	 * 
	 * @param createDate
	 *            the value of field 'createDate'.
	 */
	public void setCreateDate(final java.lang.String createDate)
	{
		this._createDate = createDate;
	}

	/**
	 * Sets the value of field 'dayInMonth'.
	 * 
	 * @param dayInMonth
	 *            the value of field 'dayInMonth'.
	 */
	public void setDayInMonth(final java.lang.String dayInMonth)
	{
		this._dayInMonth = dayInMonth;
	}

	/**
	 * Sets the value of field 'jobNumber'.
	 * 
	 * @param jobNumber
	 *            the value of field 'jobNumber'.
	 */
	public void setJobNumber(final java.lang.String jobNumber)
	{
		this._jobNumber = jobNumber;
	}

	/**
	 * Sets the value of field 'programRoot'.
	 * 
	 * @param programRoot
	 *            the value of field 'programRoot'.
	 */
	public void setProgramRoot(final java.lang.String programRoot)
	{
		this._programRoot = programRoot;
	}

	/**
	 * Sets the value of field 'recoveryStatus'.
	 * 
	 * @param recoveryStatus
	 *            the value of field 'recoveryStatus'.
	 */
	public void setRecoveryStatus(final java.lang.String recoveryStatus)
	{
		this._recoveryStatus = recoveryStatus;
	}

	/**
	 * Sets the value of field 'sequence'.
	 * 
	 * @param sequence
	 *            the value of field 'sequence'.
	 */
	public void setSequence(final java.lang.String sequence)
	{
		this._sequence = sequence;
	}

	/**
	 * Sets the value of field 'time'.
	 * 
	 * @param time
	 *            the value of field 'time'.
	 */
	public void setTime(final java.lang.String time)
	{
		this._time = time;
	}

	/**
	 * Sets the value of field 'transactionType'.
	 * 
	 * @param transactionType
	 *            the value of field 'transactionType'.
	 */
	public void setTransactionType(final java.lang.String transactionType)
	{
		this._transactionType = transactionType;
	}

	/**
	 * Sets the value of field 'workstationId'.
	 * 
	 * @param workstationId
	 *            the value of field 'workstationId'.
	 */
	public void setWorkstationId(final java.lang.String workstationId)
	{
		this._workstationId = workstationId;
	}

	/**
	 * Method unmarshalJournalKey.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.JournalKey
	 */
	public static bf.com.misys.eqf.types.header.JournalKey unmarshalJournalKey(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.JournalKey) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.JournalKey.class, reader);
	}

	/**
	 * 
	 * 
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 */
	public void validate() throws org.exolab.castor.xml.ValidationException
	{
		org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
		validator.validate(this);
	}

}
