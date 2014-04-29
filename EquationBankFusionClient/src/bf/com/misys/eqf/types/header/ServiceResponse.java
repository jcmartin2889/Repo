/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id: ServiceResponse.java 17759 2014-01-10 15:43:31Z lima12 $
 */

package bf.com.misys.eqf.types.header;

/**
 * A single type which includes both service request header and service data elements. The service data is an anyType to make this
 * useable with any BFEQ Service.
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ServiceResponse implements java.io.Serializable
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ServiceResponse.java 17759 2014-01-10 15:43:31Z lima12 $";
	// --------------------------/
	// - Class/Member Variables -/
	// --------------------------/

	/**
	 * Field serialVersionUID.
	 */
	public static final long serialVersionUID = 1L;

	/**
	 * Field _header.
	 */
	private bf.com.misys.eqf.types.header.ServiceRsHeader _header;

	/**
	 * Field _data.
	 */
	private java.lang.Object _data;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public ServiceResponse()
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

		if (obj instanceof ServiceResponse)
		{

			ServiceResponse temp = (ServiceResponse) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._header != null)
			{
				if (temp._header == null)
					return false;
				if (this._header != temp._header)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._header);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._header);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._header);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._header);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._header.equals(temp._header))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._header);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._header);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._header);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._header);
					}
				}
			}
			else if (temp._header != null)
				return false;
			if (this._data != null)
			{
				if (temp._data == null)
					return false;
				if (this._data != temp._data)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._data);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._data);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._data);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._data);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._data.equals(temp._data))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._data);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._data);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._data);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._data);
					}
				}
			}
			else if (temp._data != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'data'.
	 * 
	 * @return the value of field 'Data'.
	 */
	public java.lang.Object getData()
	{
		return this._data;
	}

	/**
	 * Returns the value of field 'header'.
	 * 
	 * @return the value of field 'Header'.
	 */
	public bf.com.misys.eqf.types.header.ServiceRsHeader getHeader()
	{
		return this._header;
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
		if (_header != null && !org.castor.core.util.CycleBreaker.startingToCycle(_header))
		{
			result = 37 * result + _header.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_header);
		}
		if (_data != null && !org.castor.core.util.CycleBreaker.startingToCycle(_data))
		{
			result = 37 * result + _data.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_data);
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
	 * Sets the value of field 'data'.
	 * 
	 * @param data
	 *            the value of field 'data'.
	 */
	public void setData(final java.lang.Object data)
	{
		this._data = data;
	}

	/**
	 * Sets the value of field 'header'.
	 * 
	 * @param header
	 *            the value of field 'header'.
	 */
	public void setHeader(final bf.com.misys.eqf.types.header.ServiceRsHeader header)
	{
		this._header = header;
	}

	/**
	 * Method unmarshalServiceResponse.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.ServiceResponse
	 */
	public static bf.com.misys.eqf.types.header.ServiceResponse unmarshalServiceResponse(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.ServiceResponse) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.ServiceResponse.class, reader);
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
