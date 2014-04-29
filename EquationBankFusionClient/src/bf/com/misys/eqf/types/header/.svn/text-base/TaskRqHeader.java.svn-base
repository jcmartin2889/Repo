/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Contains input parameter to be passed from WPS task to BFEQ
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class TaskRqHeader implements java.io.Serializable
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
	 * This is colon delimited values. It will have the following possible values: AUTH - user can authorize task; DECL - user can
	 * decline task; REFER - user can re-refer task UPD - user can update the payload data
	 * 
	 * For ENTRY task, only UPD is valid. For AUTH task, only AUTH, DECL, REFER are valid. For ESF task, this field is not
	 * applicable.
	 * 
	 * This determines the possible options a user can do when it claims this task.
	 * 
	 * If this is not specified, then it will have the following default: - For data entry, the user can only update the details -
	 * For authorization, the user can authorize, decline or re-refer
	 * 
	 */
	private java.lang.String _taskActionList;

	/**
	 * This controls the mode of the service
	 * 
	 * 'A'-Add 'M'-Maintain 'D'-Delete ' '-Any mode allowed
	 * 
	 */
	private java.lang.String _functionMode;

	/**
	 * If true, then in addition to claiming and completing the task, it will also perform all update processing defined in the
	 * Equation Service.
	 * 
	 */
	private java.lang.Boolean _performUpdate = new java.lang.Boolean("true");

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
	 * List of messages to be displayed
	 * 
	 */
	private java.util.Vector<bf.com.misys.eqf.types.header.EqMessage> _messagesList;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public TaskRqHeader()
	{
		super();
		this._messagesList = new java.util.Vector<bf.com.misys.eqf.types.header.EqMessage>();
	}

	// -----------/
	// - Methods -/
	// -----------/

	/**
	 * 
	 * 
	 * @param vMessages
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void addMessages(final bf.com.misys.eqf.types.header.EqMessage vMessages) throws java.lang.IndexOutOfBoundsException
	{
		this._messagesList.addElement(vMessages);
	}

	/**
	 * 
	 * 
	 * @param index
	 * @param vMessages
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void addMessages(final int index, final bf.com.misys.eqf.types.header.EqMessage vMessages)
					throws java.lang.IndexOutOfBoundsException
	{
		this._messagesList.add(index, vMessages);
	}

	/**
	 * Method enumerateMessages.
	 * 
	 * @return an Enumeration over all bf.com.misys.eqf.types.header.EqMessage elements
	 */
	public java.util.Enumeration<? extends bf.com.misys.eqf.types.header.EqMessage> enumerateMessages()
	{
		return this._messagesList.elements();
	}

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

		if (obj instanceof TaskRqHeader)
		{

			TaskRqHeader temp = (TaskRqHeader) obj;
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
			if (this._taskActionList != null)
			{
				if (temp._taskActionList == null)
					return false;
				if (this._taskActionList != temp._taskActionList)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._taskActionList);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._taskActionList);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._taskActionList);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._taskActionList);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._taskActionList.equals(temp._taskActionList))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._taskActionList);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._taskActionList);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._taskActionList);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._taskActionList);
					}
				}
			}
			else if (temp._taskActionList != null)
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
			if (this._performUpdate != null)
			{
				if (temp._performUpdate == null)
					return false;
				if (this._performUpdate != temp._performUpdate)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._performUpdate);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._performUpdate);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._performUpdate);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._performUpdate);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._performUpdate.equals(temp._performUpdate))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._performUpdate);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._performUpdate);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._performUpdate);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._performUpdate);
					}
				}
			}
			else if (temp._performUpdate != null)
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
			if (this._messagesList != null)
			{
				if (temp._messagesList == null)
					return false;
				if (this._messagesList != temp._messagesList)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._messagesList);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._messagesList);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._messagesList);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._messagesList);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._messagesList.equals(temp._messagesList))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._messagesList);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._messagesList);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._messagesList);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._messagesList);
					}
				}
			}
			else if (temp._messagesList != null)
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
	 * Returns the value of field 'functionMode'. The field 'functionMode' has the following description: This controls the mode of
	 * the service
	 * 
	 * 'A'-Add 'M'-Maintain 'D'-Delete ' '-Any mode allowed
	 * 
	 * 
	 * @return the value of field 'FunctionMode'.
	 */
	public java.lang.String getFunctionMode()
	{
		return this._functionMode;
	}

	/**
	 * Method getMessages.
	 * 
	 * @param index
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 * @return the value of the bf.com.misys.eqf.types.header.EqMessage at the given index
	 */
	public bf.com.misys.eqf.types.header.EqMessage getMessages(final int index) throws java.lang.IndexOutOfBoundsException
	{
		// check bounds for index
		if (index < 0 || index >= this._messagesList.size())
		{
			throw new IndexOutOfBoundsException("getMessages: Index value '" + index + "' not in range [0.."
							+ (this._messagesList.size() - 1) + "]");
		}

		return (bf.com.misys.eqf.types.header.EqMessage) _messagesList.get(index);
	}

	/**
	 * Method getMessages.Returns the contents of the collection in an Array.
	 * <p>
	 * Note: Just in case the collection contents are changing in another thread, we pass a 0-length Array of the correct type into
	 * the API call. This way we <i>know</i> that the Array returned is of exactly the correct length.
	 * 
	 * @return this collection as an Array
	 */
	public bf.com.misys.eqf.types.header.EqMessage[] getMessages()
	{
		bf.com.misys.eqf.types.header.EqMessage[] array = new bf.com.misys.eqf.types.header.EqMessage[0];
		return (bf.com.misys.eqf.types.header.EqMessage[]) this._messagesList.toArray(array);
	}

	/**
	 * Method getMessagesCount.
	 * 
	 * @return the size of this collection
	 */
	public int getMessagesCount()
	{
		return this._messagesList.size();
	}

	/**
	 * Returns the value of field 'performUpdate'. The field 'performUpdate' has the following description: If true, then in
	 * addition to claiming and completing the task, it will also perform all update processing defined in the Equation Service.
	 * 
	 * 
	 * @return the value of field 'PerformUpdate'.
	 */
	public java.lang.Boolean getPerformUpdate()
	{
		return this._performUpdate;
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
	 * Returns the value of field 'taskActionList'. The field 'taskActionList' has the following description: This is colon
	 * delimited values. It will have the following possible values: AUTH - user can authorize task; DECL - user can decline task;
	 * REFER - user can re-refer task UPD - user can update the payload data
	 * 
	 * For ENTRY task, only UPD is valid. For AUTH task, only AUTH, DECL, REFER are valid. For ESF task, this field is not
	 * applicable.
	 * 
	 * This determines the possible options a user can do when it claims this task.
	 * 
	 * If this is not specified, then it will have the following default: - For data entry, the user can only update the details -
	 * For authorization, the user can authorize, decline or re-refer
	 * 
	 * 
	 * @return the value of field 'TaskActionList'.
	 */
	public java.lang.String getTaskActionList()
	{
		return this._taskActionList;
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
		if (_taskActionList != null && !org.castor.core.util.CycleBreaker.startingToCycle(_taskActionList))
		{
			result = 37 * result + _taskActionList.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_taskActionList);
		}
		if (_functionMode != null && !org.castor.core.util.CycleBreaker.startingToCycle(_functionMode))
		{
			result = 37 * result + _functionMode.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_functionMode);
		}
		if (_performUpdate != null && !org.castor.core.util.CycleBreaker.startingToCycle(_performUpdate))
		{
			result = 37 * result + _performUpdate.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_performUpdate);
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
		if (_messagesList != null && !org.castor.core.util.CycleBreaker.startingToCycle(_messagesList))
		{
			result = 37 * result + _messagesList.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_messagesList);
		}

		return result;
	}

	/**
	 * Returns the value of field 'performUpdate'. The field 'performUpdate' has the following description: If true, then in
	 * addition to claiming and completing the task, it will also perform all update processing defined in the Equation Service.
	 * 
	 * 
	 * @return the value of field 'PerformUpdate'.
	 */
	public java.lang.Boolean isPerformUpdate()
	{
		return this._performUpdate;
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
     */
	public void removeAllMessages()
	{
		this._messagesList.clear();
	}

	/**
	 * Method removeMessages.
	 * 
	 * @param vMessages
	 * @return true if the object was removed from the collection.
	 */
	public boolean removeMessages(final bf.com.misys.eqf.types.header.EqMessage vMessages)
	{
		boolean removed = _messagesList.remove(vMessages);
		return removed;
	}

	/**
	 * Method removeMessagesAt.
	 * 
	 * @param index
	 * @return the element removed from the collection
	 */
	public bf.com.misys.eqf.types.header.EqMessage removeMessagesAt(final int index)
	{
		java.lang.Object obj = this._messagesList.remove(index);
		return (bf.com.misys.eqf.types.header.EqMessage) obj;
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
	 * Sets the value of field 'functionMode'. The field 'functionMode' has the following description: This controls the mode of the
	 * service
	 * 
	 * 'A'-Add 'M'-Maintain 'D'-Delete ' '-Any mode allowed
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
	 * 
	 * 
	 * @param index
	 * @param vMessages
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void setMessages(final int index, final bf.com.misys.eqf.types.header.EqMessage vMessages)
					throws java.lang.IndexOutOfBoundsException
	{
		// check bounds for index
		if (index < 0 || index >= this._messagesList.size())
		{
			throw new IndexOutOfBoundsException("setMessages: Index value '" + index + "' not in range [0.."
							+ (this._messagesList.size() - 1) + "]");
		}

		this._messagesList.set(index, vMessages);
	}

	/**
	 * 
	 * 
	 * @param vMessagesArray
	 */
	public void setMessages(final bf.com.misys.eqf.types.header.EqMessage[] vMessagesArray)
	{
		// -- copy array
		_messagesList.clear();

		for (int i = 0; i < vMessagesArray.length; i++)
		{
			this._messagesList.add(vMessagesArray[i]);
		}
	}

	/**
	 * Sets the value of field 'performUpdate'. The field 'performUpdate' has the following description: If true, then in addition
	 * to claiming and completing the task, it will also perform all update processing defined in the Equation Service.
	 * 
	 * 
	 * @param performUpdate
	 *            the value of field 'performUpdate'.
	 */
	public void setPerformUpdate(final java.lang.Boolean performUpdate)
	{
		this._performUpdate = performUpdate;
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
	 * Sets the value of field 'taskActionList'. The field 'taskActionList' has the following description: This is colon delimited
	 * values. It will have the following possible values: AUTH - user can authorize task; DECL - user can decline task; REFER -
	 * user can re-refer task UPD - user can update the payload data
	 * 
	 * For ENTRY task, only UPD is valid. For AUTH task, only AUTH, DECL, REFER are valid. For ESF task, this field is not
	 * applicable.
	 * 
	 * This determines the possible options a user can do when it claims this task.
	 * 
	 * If this is not specified, then it will have the following default: - For data entry, the user can only update the details -
	 * For authorization, the user can authorize, decline or re-refer
	 * 
	 * 
	 * @param taskActionList
	 *            the value of field 'taskActionList'.
	 */
	public void setTaskActionList(final java.lang.String taskActionList)
	{
		this._taskActionList = taskActionList;
	}

	/**
	 * Method unmarshalTaskRqHeader.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.TaskRqHeader
	 */
	public static bf.com.misys.eqf.types.header.TaskRqHeader unmarshalTaskRqHeader(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.TaskRqHeader) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.TaskRqHeader.class, reader);
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
