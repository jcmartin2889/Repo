/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Contains output parameter to be passed from BFEQ to WPS task
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class TaskRsHeader implements java.io.Serializable
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
	 * Determines whether the task was approved, declined or re-referred
	 * 
	 * Possible values are : AUTH - authorized DECL - declined REFER - re-referred UPD - updated
	 * 
	 */
	private java.lang.String _taskAction;

	/**
	 * User ID if re-referred
	 * 
	 */
	private java.lang.String _referToUserId;

	/**
	 * Comments or remarks for the action taken, if any
	 * 
	 */
	private java.lang.String _reason;

	/**
	 * This lets the user knows whether this transaction is Add or Maintain or Delete
	 * 
	 * 'A'-Add 'M'-Maintain 'D'-Delete
	 * 
	 */
	private java.lang.String _functionMode;

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

	public TaskRsHeader()
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

		if (obj instanceof TaskRsHeader)
		{

			TaskRsHeader temp = (TaskRsHeader) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._taskAction != null)
			{
				if (temp._taskAction == null)
					return false;
				if (this._taskAction != temp._taskAction)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._taskAction);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._taskAction);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._taskAction);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._taskAction);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._taskAction.equals(temp._taskAction))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._taskAction);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._taskAction);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._taskAction);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._taskAction);
					}
				}
			}
			else if (temp._taskAction != null)
				return false;
			if (this._referToUserId != null)
			{
				if (temp._referToUserId == null)
					return false;
				if (this._referToUserId != temp._referToUserId)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._referToUserId);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._referToUserId);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._referToUserId);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._referToUserId);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._referToUserId.equals(temp._referToUserId))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._referToUserId);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._referToUserId);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._referToUserId);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._referToUserId);
					}
				}
			}
			else if (temp._referToUserId != null)
				return false;
			if (this._reason != null)
			{
				if (temp._reason == null)
					return false;
				if (this._reason != temp._reason)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._reason);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._reason);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._reason);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._reason);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._reason.equals(temp._reason))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._reason);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._reason);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._reason);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._reason);
					}
				}
			}
			else if (temp._reason != null)
				return false;
			if (this._functionMode != null)
			{
				if (temp._functionMode == null)
					return false;
				if (this._functionMode != temp._functionMode)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._functionMode);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._functionMode);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._functionMode);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._functionMode);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._functionMode.equals(temp._functionMode))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._functionMode);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._functionMode);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._functionMode);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._functionMode);
					}
				}
			}
			else if (temp._functionMode != null)
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
	 * Returns the value of field 'functionMode'. The field 'functionMode' has the following description: This lets the user knows
	 * whether this transaction is Add or Maintain or Delete
	 * 
	 * 'A'-Add 'M'-Maintain 'D'-Delete
	 * 
	 * 
	 * @return the value of field 'FunctionMode'.
	 */
	public java.lang.String getFunctionMode()
	{
		return this._functionMode;
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
	 * Returns the value of field 'reason'. The field 'reason' has the following description: Comments or remarks for the action
	 * taken, if any
	 * 
	 * 
	 * @return the value of field 'Reason'.
	 */
	public java.lang.String getReason()
	{
		return this._reason;
	}

	/**
	 * Returns the value of field 'referToUserId'. The field 'referToUserId' has the following description: User ID if re-referred
	 * 
	 * 
	 * @return the value of field 'ReferToUserId'.
	 */
	public java.lang.String getReferToUserId()
	{
		return this._referToUserId;
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
	 * Returns the value of field 'taskAction'. The field 'taskAction' has the following description: Determines whether the task
	 * was approved, declined or re-referred
	 * 
	 * Possible values are : AUTH - authorized DECL - declined REFER - re-referred UPD - updated
	 * 
	 * 
	 * @return the value of field 'TaskAction'.
	 */
	public java.lang.String getTaskAction()
	{
		return this._taskAction;
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
		if (_taskAction != null && !org.castor.core.util.CycleBreaker.startingToCycle(_taskAction))
		{
			result = 37 * result + _taskAction.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_taskAction);
		}
		if (_referToUserId != null && !org.castor.core.util.CycleBreaker.startingToCycle(_referToUserId))
		{
			result = 37 * result + _referToUserId.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_referToUserId);
		}
		if (_reason != null && !org.castor.core.util.CycleBreaker.startingToCycle(_reason))
		{
			result = 37 * result + _reason.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_reason);
		}
		if (_functionMode != null && !org.castor.core.util.CycleBreaker.startingToCycle(_functionMode))
		{
			result = 37 * result + _functionMode.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_functionMode);
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
	 * Sets the value of field 'functionMode'. The field 'functionMode' has the following description: This lets the user knows
	 * whether this transaction is Add or Maintain or Delete
	 * 
	 * 'A'-Add 'M'-Maintain 'D'-Delete
	 * 
	 * 
	 * @param functionMode
	 *            the value of field 'functionMode'.
	 */
	public void setFunctionMode(final java.lang.String functionMode)
	{
		this._functionMode = functionMode;
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
	 * Sets the value of field 'reason'. The field 'reason' has the following description: Comments or remarks for the action taken,
	 * if any
	 * 
	 * 
	 * @param reason
	 *            the value of field 'reason'.
	 */
	public void setReason(final java.lang.String reason)
	{
		this._reason = reason;
	}

	/**
	 * Sets the value of field 'referToUserId'. The field 'referToUserId' has the following description: User ID if re-referred
	 * 
	 * 
	 * @param referToUserId
	 *            the value of field 'referToUserId'.
	 */
	public void setReferToUserId(final java.lang.String referToUserId)
	{
		this._referToUserId = referToUserId;
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
	 * Sets the value of field 'taskAction'. The field 'taskAction' has the following description: Determines whether the task was
	 * approved, declined or re-referred
	 * 
	 * Possible values are : AUTH - authorized DECL - declined REFER - re-referred UPD - updated
	 * 
	 * 
	 * @param taskAction
	 *            the value of field 'taskAction'.
	 */
	public void setTaskAction(final java.lang.String taskAction)
	{
		this._taskAction = taskAction;
	}

	/**
	 * Method unmarshalTaskRsHeader.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.TaskRsHeader
	 */
	public static bf.com.misys.eqf.types.header.TaskRsHeader unmarshalTaskRsHeader(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.TaskRsHeader) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.TaskRsHeader.class, reader);
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
