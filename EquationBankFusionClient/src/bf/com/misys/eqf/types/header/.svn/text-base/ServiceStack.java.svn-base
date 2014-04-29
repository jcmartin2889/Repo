/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Class ServiceStack.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ServiceStack implements java.io.Serializable
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
	 * Field _serviceStackEntryList.
	 */
	private java.util.Vector<java.lang.String> _serviceStackEntryList;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public ServiceStack()
	{
		super();
		this._serviceStackEntryList = new java.util.Vector<java.lang.String>();
	}

	// -----------/
	// - Methods -/
	// -----------/

	/**
	 * 
	 * 
	 * @param vServiceStackEntry
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void addServiceStackEntry(final java.lang.String vServiceStackEntry) throws java.lang.IndexOutOfBoundsException
	{
		this._serviceStackEntryList.addElement(vServiceStackEntry);
	}

	/**
	 * 
	 * 
	 * @param index
	 * @param vServiceStackEntry
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void addServiceStackEntry(final int index, final java.lang.String vServiceStackEntry)
					throws java.lang.IndexOutOfBoundsException
	{
		this._serviceStackEntryList.add(index, vServiceStackEntry);
	}

	/**
	 * Method enumerateServiceStackEntry.
	 * 
	 * @return an Enumeration over all java.lang.String elements
	 */
	public java.util.Enumeration<? extends java.lang.String> enumerateServiceStackEntry()
	{
		return this._serviceStackEntryList.elements();
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

		if (obj instanceof ServiceStack)
		{

			ServiceStack temp = (ServiceStack) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._serviceStackEntryList != null)
			{
				if (temp._serviceStackEntryList == null)
					return false;
				if (this._serviceStackEntryList != temp._serviceStackEntryList)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._serviceStackEntryList);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._serviceStackEntryList);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._serviceStackEntryList);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._serviceStackEntryList);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._serviceStackEntryList.equals(temp._serviceStackEntryList))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._serviceStackEntryList);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._serviceStackEntryList);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._serviceStackEntryList);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._serviceStackEntryList);
					}
				}
			}
			else if (temp._serviceStackEntryList != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Method getServiceStackEntry.
	 * 
	 * @param index
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 * @return the value of the java.lang.String at the given index
	 */
	public java.lang.String getServiceStackEntry(final int index) throws java.lang.IndexOutOfBoundsException
	{
		// check bounds for index
		if (index < 0 || index >= this._serviceStackEntryList.size())
		{
			throw new IndexOutOfBoundsException("getServiceStackEntry: Index value '" + index + "' not in range [0.."
							+ (this._serviceStackEntryList.size() - 1) + "]");
		}

		return (java.lang.String) _serviceStackEntryList.get(index);
	}

	/**
	 * Method getServiceStackEntry.Returns the contents of the collection in an Array.
	 * <p>
	 * Note: Just in case the collection contents are changing in another thread, we pass a 0-length Array of the correct type into
	 * the API call. This way we <i>know</i> that the Array returned is of exactly the correct length.
	 * 
	 * @return this collection as an Array
	 */
	public java.lang.String[] getServiceStackEntry()
	{
		java.lang.String[] array = new java.lang.String[0];
		return (java.lang.String[]) this._serviceStackEntryList.toArray(array);
	}

	/**
	 * Method getServiceStackEntryCount.
	 * 
	 * @return the size of this collection
	 */
	public int getServiceStackEntryCount()
	{
		return this._serviceStackEntryList.size();
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
		if (_serviceStackEntryList != null && !org.castor.core.util.CycleBreaker.startingToCycle(_serviceStackEntryList))
		{
			result = 37 * result + _serviceStackEntryList.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_serviceStackEntryList);
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
	public void removeAllServiceStackEntry()
	{
		this._serviceStackEntryList.clear();
	}

	/**
	 * Method removeServiceStackEntry.
	 * 
	 * @param vServiceStackEntry
	 * @return true if the object was removed from the collection.
	 */
	public boolean removeServiceStackEntry(final java.lang.String vServiceStackEntry)
	{
		boolean removed = _serviceStackEntryList.remove(vServiceStackEntry);
		return removed;
	}

	/**
	 * Method removeServiceStackEntryAt.
	 * 
	 * @param index
	 * @return the element removed from the collection
	 */
	public java.lang.String removeServiceStackEntryAt(final int index)
	{
		java.lang.Object obj = this._serviceStackEntryList.remove(index);
		return (java.lang.String) obj;
	}

	/**
	 * 
	 * 
	 * @param index
	 * @param vServiceStackEntry
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void setServiceStackEntry(final int index, final java.lang.String vServiceStackEntry)
					throws java.lang.IndexOutOfBoundsException
	{
		// check bounds for index
		if (index < 0 || index >= this._serviceStackEntryList.size())
		{
			throw new IndexOutOfBoundsException("setServiceStackEntry: Index value '" + index + "' not in range [0.."
							+ (this._serviceStackEntryList.size() - 1) + "]");
		}

		this._serviceStackEntryList.set(index, vServiceStackEntry);
	}

	/**
	 * 
	 * 
	 * @param vServiceStackEntryArray
	 */
	public void setServiceStackEntry(final java.lang.String[] vServiceStackEntryArray)
	{
		// -- copy array
		_serviceStackEntryList.clear();

		for (int i = 0; i < vServiceStackEntryArray.length; i++)
		{
			this._serviceStackEntryList.add(vServiceStackEntryArray[i]);
		}
	}

	/**
	 * Method unmarshalServiceStack.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.ServiceStack
	 */
	public static bf.com.misys.eqf.types.header.ServiceStack unmarshalServiceStack(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.ServiceStack) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.ServiceStack.class, reader);
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
