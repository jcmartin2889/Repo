/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Class MessageStatus.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class MessageStatus implements java.io.Serializable
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
	 * Field _overallStatus.
	 */
	private java.lang.String _overallStatus;

	/**
	 * Field _eqMessagesList.
	 */
	private java.util.Vector<bf.com.misys.eqf.types.header.EqMessage> _eqMessagesList;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public MessageStatus()
	{
		super();
		this._eqMessagesList = new java.util.Vector<bf.com.misys.eqf.types.header.EqMessage>();
	}

	// -----------/
	// - Methods -/
	// -----------/

	/**
	 * 
	 * 
	 * @param vEqMessages
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void addEqMessages(final bf.com.misys.eqf.types.header.EqMessage vEqMessages) throws java.lang.IndexOutOfBoundsException
	{
		this._eqMessagesList.addElement(vEqMessages);
	}

	/**
	 * 
	 * 
	 * @param index
	 * @param vEqMessages
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void addEqMessages(final int index, final bf.com.misys.eqf.types.header.EqMessage vEqMessages)
					throws java.lang.IndexOutOfBoundsException
	{
		this._eqMessagesList.add(index, vEqMessages);
	}

	/**
	 * Method enumerateEqMessages.
	 * 
	 * @return an Enumeration over all bf.com.misys.eqf.types.header.EqMessage elements
	 */
	public java.util.Enumeration<? extends bf.com.misys.eqf.types.header.EqMessage> enumerateEqMessages()
	{
		return this._eqMessagesList.elements();
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

		if (obj instanceof MessageStatus)
		{

			MessageStatus temp = (MessageStatus) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._overallStatus != null)
			{
				if (temp._overallStatus == null)
					return false;
				if (this._overallStatus != temp._overallStatus)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._overallStatus);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._overallStatus);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._overallStatus);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._overallStatus);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._overallStatus.equals(temp._overallStatus))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._overallStatus);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._overallStatus);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._overallStatus);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._overallStatus);
					}
				}
			}
			else if (temp._overallStatus != null)
				return false;
			if (this._eqMessagesList != null)
			{
				if (temp._eqMessagesList == null)
					return false;
				if (this._eqMessagesList != temp._eqMessagesList)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._eqMessagesList);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._eqMessagesList);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessagesList);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessagesList);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._eqMessagesList.equals(temp._eqMessagesList))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessagesList);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessagesList);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessagesList);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessagesList);
					}
				}
			}
			else if (temp._eqMessagesList != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Method getEqMessages.
	 * 
	 * @param index
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 * @return the value of the bf.com.misys.eqf.types.header.EqMessage at the given index
	 */
	public bf.com.misys.eqf.types.header.EqMessage getEqMessages(final int index) throws java.lang.IndexOutOfBoundsException
	{
		// check bounds for index
		if (index < 0 || index >= this._eqMessagesList.size())
		{
			throw new IndexOutOfBoundsException("getEqMessages: Index value '" + index + "' not in range [0.."
							+ (this._eqMessagesList.size() - 1) + "]");
		}

		return (bf.com.misys.eqf.types.header.EqMessage) _eqMessagesList.get(index);
	}

	/**
	 * Method getEqMessages.Returns the contents of the collection in an Array.
	 * <p>
	 * Note: Just in case the collection contents are changing in another thread, we pass a 0-length Array of the correct type into
	 * the API call. This way we <i>know</i> that the Array returned is of exactly the correct length.
	 * 
	 * @return this collection as an Array
	 */
	public bf.com.misys.eqf.types.header.EqMessage[] getEqMessages()
	{
		bf.com.misys.eqf.types.header.EqMessage[] array = new bf.com.misys.eqf.types.header.EqMessage[0];
		return (bf.com.misys.eqf.types.header.EqMessage[]) this._eqMessagesList.toArray(array);
	}

	/**
	 * Method getEqMessagesCount.
	 * 
	 * @return the size of this collection
	 */
	public int getEqMessagesCount()
	{
		return this._eqMessagesList.size();
	}

	/**
	 * Returns the value of field 'overallStatus'.
	 * 
	 * @return the value of field 'OverallStatus'.
	 */
	public java.lang.String getOverallStatus()
	{
		return this._overallStatus;
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
		if (_overallStatus != null && !org.castor.core.util.CycleBreaker.startingToCycle(_overallStatus))
		{
			result = 37 * result + _overallStatus.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_overallStatus);
		}
		if (_eqMessagesList != null && !org.castor.core.util.CycleBreaker.startingToCycle(_eqMessagesList))
		{
			result = 37 * result + _eqMessagesList.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_eqMessagesList);
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
     */
	public void removeAllEqMessages()
	{
		this._eqMessagesList.clear();
	}

	/**
	 * Method removeEqMessages.
	 * 
	 * @param vEqMessages
	 * @return true if the object was removed from the collection.
	 */
	public boolean removeEqMessages(final bf.com.misys.eqf.types.header.EqMessage vEqMessages)
	{
		boolean removed = _eqMessagesList.remove(vEqMessages);
		return removed;
	}

	/**
	 * Method removeEqMessagesAt.
	 * 
	 * @param index
	 * @return the element removed from the collection
	 */
	public bf.com.misys.eqf.types.header.EqMessage removeEqMessagesAt(final int index)
	{
		java.lang.Object obj = this._eqMessagesList.remove(index);
		return (bf.com.misys.eqf.types.header.EqMessage) obj;
	}

	/**
	 * 
	 * 
	 * @param index
	 * @param vEqMessages
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void setEqMessages(final int index, final bf.com.misys.eqf.types.header.EqMessage vEqMessages)
					throws java.lang.IndexOutOfBoundsException
	{
		// check bounds for index
		if (index < 0 || index >= this._eqMessagesList.size())
		{
			throw new IndexOutOfBoundsException("setEqMessages: Index value '" + index + "' not in range [0.."
							+ (this._eqMessagesList.size() - 1) + "]");
		}

		this._eqMessagesList.set(index, vEqMessages);
	}

	/**
	 * 
	 * 
	 * @param vEqMessagesArray
	 */
	public void setEqMessages(final bf.com.misys.eqf.types.header.EqMessage[] vEqMessagesArray)
	{
		// -- copy array
		_eqMessagesList.clear();

		for (int i = 0; i < vEqMessagesArray.length; i++)
		{
			this._eqMessagesList.add(vEqMessagesArray[i]);
		}
	}

	/**
	 * Sets the value of field 'overallStatus'.
	 * 
	 * @param overallStatus
	 *            the value of field 'overallStatus'.
	 */
	public void setOverallStatus(final java.lang.String overallStatus)
	{
		this._overallStatus = overallStatus;
	}

	/**
	 * Method unmarshalMessageStatus.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.MessageStatus
	 */
	public static bf.com.misys.eqf.types.header.MessageStatus unmarshalMessageStatus(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.MessageStatus) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.MessageStatus.class, reader);
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
