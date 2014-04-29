/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Contains input parameter to be passed from WPS to BFEQ during ESF processing.
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class TaskEsfRqHeader implements java.io.Serializable
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
	 * The task basic details
	 * 
	 */
	private bf.com.misys.eqf.types.header.TaskBasicDetails _basicDetail;

	/**
	 * Field _systemId.
	 */
	private java.lang.String _systemId;

	/**
	 * Field _unitId.
	 */
	private java.lang.String _unitId;

	/**
	 * The ESF key details
	 * 
	 */
	private bf.com.misys.eqf.types.header.EsfKey _esfKey;

	/**
	 * This is the requested supervisor. It is up to the WPS designer if it will use this supervisor Id or nor. Also, WPS designer
	 * needs to cater for blank supervisor Id
	 * 
	 */
	private java.lang.String _supervisorId;

	/**
	 * This determines whether warning is for the main option or for CRM or for EFC
	 * 
	 */
	private java.lang.String _currentScreenFieldSet;

	/**
	 * This determines the screen with warning
	 * 
	 */
	private java.lang.String _currentFieldSet;

	/**
	 * This will be the Equation Service complex type
	 * 
	 */
	private java.lang.Object _serviceData;

	/**
	 * This will be the CRM complex type
	 * 
	 */
	private java.lang.Object _crmData;

	/**
	 * This will be the EFC complex type
	 * 
	 */
	private java.lang.Object _efcData;

	/**
	 * Any error message to be displayed
	 * 
	 */
	private bf.com.misys.eqf.types.header.MessageStatus _messages;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public TaskEsfRqHeader()
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

		if (obj instanceof TaskEsfRqHeader)
		{

			TaskEsfRqHeader temp = (TaskEsfRqHeader) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._basicDetail != null)
			{
				if (temp._basicDetail == null)
					return false;
				if (this._basicDetail != temp._basicDetail)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._basicDetail);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._basicDetail);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._basicDetail);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._basicDetail);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._basicDetail.equals(temp._basicDetail))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._basicDetail);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._basicDetail);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._basicDetail);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._basicDetail);
					}
				}
			}
			else if (temp._basicDetail != null)
				return false;
			if (this._systemId != null)
			{
				if (temp._systemId == null)
					return false;
				if (this._systemId != temp._systemId)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._systemId);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._systemId);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._systemId);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._systemId);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._systemId.equals(temp._systemId))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._systemId);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._systemId);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._systemId);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._systemId);
					}
				}
			}
			else if (temp._systemId != null)
				return false;
			if (this._unitId != null)
			{
				if (temp._unitId == null)
					return false;
				if (this._unitId != temp._unitId)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._unitId);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._unitId);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._unitId);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._unitId);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._unitId.equals(temp._unitId))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._unitId);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._unitId);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._unitId);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._unitId);
					}
				}
			}
			else if (temp._unitId != null)
				return false;
			if (this._esfKey != null)
			{
				if (temp._esfKey == null)
					return false;
				if (this._esfKey != temp._esfKey)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._esfKey);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._esfKey);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._esfKey);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._esfKey);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._esfKey.equals(temp._esfKey))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._esfKey);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._esfKey);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._esfKey);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._esfKey);
					}
				}
			}
			else if (temp._esfKey != null)
				return false;
			if (this._supervisorId != null)
			{
				if (temp._supervisorId == null)
					return false;
				if (this._supervisorId != temp._supervisorId)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._supervisorId);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._supervisorId);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._supervisorId);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._supervisorId);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._supervisorId.equals(temp._supervisorId))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._supervisorId);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._supervisorId);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._supervisorId);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._supervisorId);
					}
				}
			}
			else if (temp._supervisorId != null)
				return false;
			if (this._currentScreenFieldSet != null)
			{
				if (temp._currentScreenFieldSet == null)
					return false;
				if (this._currentScreenFieldSet != temp._currentScreenFieldSet)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._currentScreenFieldSet);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._currentScreenFieldSet);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._currentScreenFieldSet);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._currentScreenFieldSet);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._currentScreenFieldSet.equals(temp._currentScreenFieldSet))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._currentScreenFieldSet);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._currentScreenFieldSet);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._currentScreenFieldSet);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._currentScreenFieldSet);
					}
				}
			}
			else if (temp._currentScreenFieldSet != null)
				return false;
			if (this._currentFieldSet != null)
			{
				if (temp._currentFieldSet == null)
					return false;
				if (this._currentFieldSet != temp._currentFieldSet)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._currentFieldSet);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._currentFieldSet);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._currentFieldSet);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._currentFieldSet);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._currentFieldSet.equals(temp._currentFieldSet))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._currentFieldSet);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._currentFieldSet);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._currentFieldSet);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._currentFieldSet);
					}
				}
			}
			else if (temp._currentFieldSet != null)
				return false;
			if (this._serviceData != null)
			{
				if (temp._serviceData == null)
					return false;
				if (this._serviceData != temp._serviceData)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._serviceData);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._serviceData);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._serviceData);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._serviceData);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._serviceData.equals(temp._serviceData))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._serviceData);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._serviceData);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._serviceData);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._serviceData);
					}
				}
			}
			else if (temp._serviceData != null)
				return false;
			if (this._crmData != null)
			{
				if (temp._crmData == null)
					return false;
				if (this._crmData != temp._crmData)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._crmData);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._crmData);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._crmData);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._crmData);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._crmData.equals(temp._crmData))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._crmData);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._crmData);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._crmData);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._crmData);
					}
				}
			}
			else if (temp._crmData != null)
				return false;
			if (this._efcData != null)
			{
				if (temp._efcData == null)
					return false;
				if (this._efcData != temp._efcData)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._efcData);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._efcData);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._efcData);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._efcData);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._efcData.equals(temp._efcData))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._efcData);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._efcData);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._efcData);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._efcData);
					}
				}
			}
			else if (temp._efcData != null)
				return false;
			if (this._messages != null)
			{
				if (temp._messages == null)
					return false;
				if (this._messages != temp._messages)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._messages);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._messages);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._messages);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._messages);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._messages.equals(temp._messages))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._messages);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._messages);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._messages);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._messages);
					}
				}
			}
			else if (temp._messages != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'basicDetail'. The field 'basicDetail' has the following description: The task basic details
	 * 
	 * 
	 * @return the value of field 'BasicDetail'.
	 */
	public bf.com.misys.eqf.types.header.TaskBasicDetails getBasicDetail()
	{
		return this._basicDetail;
	}

	/**
	 * Returns the value of field 'crmData'. The field 'crmData' has the following description: This will be the CRM complex type
	 * 
	 * 
	 * @return the value of field 'CrmData'.
	 */
	public java.lang.Object getCrmData()
	{
		return this._crmData;
	}

	/**
	 * Returns the value of field 'currentFieldSet'. The field 'currentFieldSet' has the following description: This determines the
	 * screen with warning
	 * 
	 * 
	 * @return the value of field 'CurrentFieldSet'.
	 */
	public java.lang.String getCurrentFieldSet()
	{
		return this._currentFieldSet;
	}

	/**
	 * Returns the value of field 'currentScreenFieldSet'. The field 'currentScreenFieldSet' has the following description: This
	 * determines whether warning is for the main option or for CRM or for EFC
	 * 
	 * 
	 * @return the value of field 'CurrentScreenFieldSet'.
	 */
	public java.lang.String getCurrentScreenFieldSet()
	{
		return this._currentScreenFieldSet;
	}

	/**
	 * Returns the value of field 'efcData'. The field 'efcData' has the following description: This will be the EFC complex type
	 * 
	 * 
	 * @return the value of field 'EfcData'.
	 */
	public java.lang.Object getEfcData()
	{
		return this._efcData;
	}

	/**
	 * Returns the value of field 'esfKey'. The field 'esfKey' has the following description: The ESF key details
	 * 
	 * 
	 * @return the value of field 'EsfKey'.
	 */
	public bf.com.misys.eqf.types.header.EsfKey getEsfKey()
	{
		return this._esfKey;
	}

	/**
	 * Returns the value of field 'messages'. The field 'messages' has the following description: Any error message to be displayed
	 * 
	 * 
	 * @return the value of field 'Messages'.
	 */
	public bf.com.misys.eqf.types.header.MessageStatus getMessages()
	{
		return this._messages;
	}

	/**
	 * Returns the value of field 'serviceData'. The field 'serviceData' has the following description: This will be the Equation
	 * Service complex type
	 * 
	 * 
	 * @return the value of field 'ServiceData'.
	 */
	public java.lang.Object getServiceData()
	{
		return this._serviceData;
	}

	/**
	 * Returns the value of field 'supervisorId'. The field 'supervisorId' has the following description: This is the requested
	 * supervisor. It is up to the WPS designer if it will use this supervisor Id or nor. Also, WPS designer needs to cater for
	 * blank supervisor Id
	 * 
	 * 
	 * @return the value of field 'SupervisorId'.
	 */
	public java.lang.String getSupervisorId()
	{
		return this._supervisorId;
	}

	/**
	 * Returns the value of field 'systemId'.
	 * 
	 * @return the value of field 'SystemId'.
	 */
	public java.lang.String getSystemId()
	{
		return this._systemId;
	}

	/**
	 * Returns the value of field 'unitId'.
	 * 
	 * @return the value of field 'UnitId'.
	 */
	public java.lang.String getUnitId()
	{
		return this._unitId;
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
		if (_basicDetail != null && !org.castor.core.util.CycleBreaker.startingToCycle(_basicDetail))
		{
			result = 37 * result + _basicDetail.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_basicDetail);
		}
		if (_systemId != null && !org.castor.core.util.CycleBreaker.startingToCycle(_systemId))
		{
			result = 37 * result + _systemId.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_systemId);
		}
		if (_unitId != null && !org.castor.core.util.CycleBreaker.startingToCycle(_unitId))
		{
			result = 37 * result + _unitId.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_unitId);
		}
		if (_esfKey != null && !org.castor.core.util.CycleBreaker.startingToCycle(_esfKey))
		{
			result = 37 * result + _esfKey.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_esfKey);
		}
		if (_supervisorId != null && !org.castor.core.util.CycleBreaker.startingToCycle(_supervisorId))
		{
			result = 37 * result + _supervisorId.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_supervisorId);
		}
		if (_currentScreenFieldSet != null && !org.castor.core.util.CycleBreaker.startingToCycle(_currentScreenFieldSet))
		{
			result = 37 * result + _currentScreenFieldSet.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_currentScreenFieldSet);
		}
		if (_currentFieldSet != null && !org.castor.core.util.CycleBreaker.startingToCycle(_currentFieldSet))
		{
			result = 37 * result + _currentFieldSet.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_currentFieldSet);
		}
		if (_serviceData != null && !org.castor.core.util.CycleBreaker.startingToCycle(_serviceData))
		{
			result = 37 * result + _serviceData.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_serviceData);
		}
		if (_crmData != null && !org.castor.core.util.CycleBreaker.startingToCycle(_crmData))
		{
			result = 37 * result + _crmData.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_crmData);
		}
		if (_efcData != null && !org.castor.core.util.CycleBreaker.startingToCycle(_efcData))
		{
			result = 37 * result + _efcData.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_efcData);
		}
		if (_messages != null && !org.castor.core.util.CycleBreaker.startingToCycle(_messages))
		{
			result = 37 * result + _messages.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_messages);
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
	 * Sets the value of field 'basicDetail'. The field 'basicDetail' has the following description: The task basic details
	 * 
	 * 
	 * @param basicDetail
	 *            the value of field 'basicDetail'.
	 */
	public void setBasicDetail(final bf.com.misys.eqf.types.header.TaskBasicDetails basicDetail)
	{
		this._basicDetail = basicDetail;
	}

	/**
	 * Sets the value of field 'crmData'. The field 'crmData' has the following description: This will be the CRM complex type
	 * 
	 * 
	 * @param crmData
	 *            the value of field 'crmData'.
	 */
	public void setCrmData(final java.lang.Object crmData)
	{
		this._crmData = crmData;
	}

	/**
	 * Sets the value of field 'currentFieldSet'. The field 'currentFieldSet' has the following description: This determines the
	 * screen with warning
	 * 
	 * 
	 * @param currentFieldSet
	 *            the value of field 'currentFieldSet'.
	 */
	public void setCurrentFieldSet(final java.lang.String currentFieldSet)
	{
		this._currentFieldSet = currentFieldSet;
	}

	/**
	 * Sets the value of field 'currentScreenFieldSet'. The field 'currentScreenFieldSet' has the following description: This
	 * determines whether warning is for the main option or for CRM or for EFC
	 * 
	 * 
	 * @param currentScreenFieldSet
	 *            the value of field 'currentScreenFieldSet'.
	 */
	public void setCurrentScreenFieldSet(final java.lang.String currentScreenFieldSet)
	{
		this._currentScreenFieldSet = currentScreenFieldSet;
	}

	/**
	 * Sets the value of field 'efcData'. The field 'efcData' has the following description: This will be the EFC complex type
	 * 
	 * 
	 * @param efcData
	 *            the value of field 'efcData'.
	 */
	public void setEfcData(final java.lang.Object efcData)
	{
		this._efcData = efcData;
	}

	/**
	 * Sets the value of field 'esfKey'. The field 'esfKey' has the following description: The ESF key details
	 * 
	 * 
	 * @param esfKey
	 *            the value of field 'esfKey'.
	 */
	public void setEsfKey(final bf.com.misys.eqf.types.header.EsfKey esfKey)
	{
		this._esfKey = esfKey;
	}

	/**
	 * Sets the value of field 'messages'. The field 'messages' has the following description: Any error message to be displayed
	 * 
	 * 
	 * @param messages
	 *            the value of field 'messages'.
	 */
	public void setMessages(final bf.com.misys.eqf.types.header.MessageStatus messages)
	{
		this._messages = messages;
	}

	/**
	 * Sets the value of field 'serviceData'. The field 'serviceData' has the following description: This will be the Equation
	 * Service complex type
	 * 
	 * 
	 * @param serviceData
	 *            the value of field 'serviceData'.
	 */
	public void setServiceData(final java.lang.Object serviceData)
	{
		this._serviceData = serviceData;
	}

	/**
	 * Sets the value of field 'supervisorId'. The field 'supervisorId' has the following description: This is the requested
	 * supervisor. It is up to the WPS designer if it will use this supervisor Id or nor. Also, WPS designer needs to cater for
	 * blank supervisor Id
	 * 
	 * 
	 * @param supervisorId
	 *            the value of field 'supervisorId'.
	 */
	public void setSupervisorId(final java.lang.String supervisorId)
	{
		this._supervisorId = supervisorId;
	}

	/**
	 * Sets the value of field 'systemId'.
	 * 
	 * @param systemId
	 *            the value of field 'systemId'.
	 */
	public void setSystemId(final java.lang.String systemId)
	{
		this._systemId = systemId;
	}

	/**
	 * Sets the value of field 'unitId'.
	 * 
	 * @param unitId
	 *            the value of field 'unitId'.
	 */
	public void setUnitId(final java.lang.String unitId)
	{
		this._unitId = unitId;
	}

	/**
	 * Method unmarshalTaskEsfRqHeader.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.TaskEsfRqHeader
	 */
	public static bf.com.misys.eqf.types.header.TaskEsfRqHeader unmarshalTaskEsfRqHeader(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.TaskEsfRqHeader) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.TaskEsfRqHeader.class, reader);
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
