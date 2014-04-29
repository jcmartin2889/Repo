/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Class CrmMessages.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class CrmMessages implements java.io.Serializable
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
	 * Field _crmMessageList.
	 */
	private java.util.Vector<bf.com.misys.eqf.types.header.CrmMessage> _crmMessageList;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public CrmMessages()
	{
		super();
		this._crmMessageList = new java.util.Vector<bf.com.misys.eqf.types.header.CrmMessage>();
	}

	// -----------/
	// - Methods -/
	// -----------/

	/**
	 * 
	 * 
	 * @param vCrmMessage
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void addCrmMessage(final bf.com.misys.eqf.types.header.CrmMessage vCrmMessage)
					throws java.lang.IndexOutOfBoundsException
	{
		this._crmMessageList.addElement(vCrmMessage);
	}

	/**
	 * 
	 * 
	 * @param index
	 * @param vCrmMessage
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void addCrmMessage(final int index, final bf.com.misys.eqf.types.header.CrmMessage vCrmMessage)
					throws java.lang.IndexOutOfBoundsException
	{
		this._crmMessageList.add(index, vCrmMessage);
	}

	/**
	 * Method enumerateCrmMessage.
	 * 
	 * @return an Enumeration over all bf.com.misys.eqf.types.header.CrmMessage elements
	 */
	public java.util.Enumeration<? extends bf.com.misys.eqf.types.header.CrmMessage> enumerateCrmMessage()
	{
		return this._crmMessageList.elements();
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

		if (obj instanceof CrmMessages)
		{

			CrmMessages temp = (CrmMessages) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._crmMessageList != null)
			{
				if (temp._crmMessageList == null)
					return false;
				if (this._crmMessageList != temp._crmMessageList)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._crmMessageList);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._crmMessageList);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._crmMessageList);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._crmMessageList);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._crmMessageList.equals(temp._crmMessageList))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._crmMessageList);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._crmMessageList);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._crmMessageList);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._crmMessageList);
					}
				}
			}
			else if (temp._crmMessageList != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Method getCrmMessage.
	 * 
	 * @param index
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 * @return the value of the bf.com.misys.eqf.types.header.CrmMessage at the given index
	 */
	public bf.com.misys.eqf.types.header.CrmMessage getCrmMessage(final int index) throws java.lang.IndexOutOfBoundsException
	{
		// check bounds for index
		if (index < 0 || index >= this._crmMessageList.size())
		{
			throw new IndexOutOfBoundsException("getCrmMessage: Index value '" + index + "' not in range [0.."
							+ (this._crmMessageList.size() - 1) + "]");
		}

		return (bf.com.misys.eqf.types.header.CrmMessage) _crmMessageList.get(index);
	}

	/**
	 * Method getCrmMessage.Returns the contents of the collection in an Array.
	 * <p>
	 * Note: Just in case the collection contents are changing in another thread, we pass a 0-length Array of the correct type into
	 * the API call. This way we <i>know</i> that the Array returned is of exactly the correct length.
	 * 
	 * @return this collection as an Array
	 */
	public bf.com.misys.eqf.types.header.CrmMessage[] getCrmMessage()
	{
		bf.com.misys.eqf.types.header.CrmMessage[] array = new bf.com.misys.eqf.types.header.CrmMessage[0];
		return (bf.com.misys.eqf.types.header.CrmMessage[]) this._crmMessageList.toArray(array);
	}

	/**
	 * Method getCrmMessageCount.
	 * 
	 * @return the size of this collection
	 */
	public int getCrmMessageCount()
	{
		return this._crmMessageList.size();
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
		if (_crmMessageList != null && !org.castor.core.util.CycleBreaker.startingToCycle(_crmMessageList))
		{
			result = 37 * result + _crmMessageList.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_crmMessageList);
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
	public void removeAllCrmMessage()
	{
		this._crmMessageList.clear();
	}

	/**
	 * Method removeCrmMessage.
	 * 
	 * @param vCrmMessage
	 * @return true if the object was removed from the collection.
	 */
	public boolean removeCrmMessage(final bf.com.misys.eqf.types.header.CrmMessage vCrmMessage)
	{
		boolean removed = _crmMessageList.remove(vCrmMessage);
		return removed;
	}

	/**
	 * Method removeCrmMessageAt.
	 * 
	 * @param index
	 * @return the element removed from the collection
	 */
	public bf.com.misys.eqf.types.header.CrmMessage removeCrmMessageAt(final int index)
	{
		java.lang.Object obj = this._crmMessageList.remove(index);
		return (bf.com.misys.eqf.types.header.CrmMessage) obj;
	}

	/**
	 * 
	 * 
	 * @param index
	 * @param vCrmMessage
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void setCrmMessage(final int index, final bf.com.misys.eqf.types.header.CrmMessage vCrmMessage)
					throws java.lang.IndexOutOfBoundsException
	{
		// check bounds for index
		if (index < 0 || index >= this._crmMessageList.size())
		{
			throw new IndexOutOfBoundsException("setCrmMessage: Index value '" + index + "' not in range [0.."
							+ (this._crmMessageList.size() - 1) + "]");
		}

		this._crmMessageList.set(index, vCrmMessage);
	}

	/**
	 * 
	 * 
	 * @param vCrmMessageArray
	 */
	public void setCrmMessage(final bf.com.misys.eqf.types.header.CrmMessage[] vCrmMessageArray)
	{
		// -- copy array
		_crmMessageList.clear();

		for (int i = 0; i < vCrmMessageArray.length; i++)
		{
			this._crmMessageList.add(vCrmMessageArray[i]);
		}
	}

	/**
	 * Method unmarshalCrmMessages.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.CrmMessages
	 */
	public static bf.com.misys.eqf.types.header.CrmMessages unmarshalCrmMessages(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.CrmMessages) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.CrmMessages.class, reader);
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
