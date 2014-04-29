/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Class ServiceUserExitRsHeader.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ServiceUserExitRsHeader implements java.io.Serializable
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
	 * Field _rsHeader.
	 */
	private bf.com.misys.eqf.types.header.RsHeader _rsHeader;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public ServiceUserExitRsHeader()
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

		if (obj instanceof ServiceUserExitRsHeader)
		{

			ServiceUserExitRsHeader temp = (ServiceUserExitRsHeader) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._rsHeader != null)
			{
				if (temp._rsHeader == null)
					return false;
				if (this._rsHeader != temp._rsHeader)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._rsHeader);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._rsHeader);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._rsHeader);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._rsHeader);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._rsHeader.equals(temp._rsHeader))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._rsHeader);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._rsHeader);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._rsHeader);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._rsHeader);
					}
				}
			}
			else if (temp._rsHeader != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'rsHeader'.
	 * 
	 * @return the value of field 'RsHeader'.
	 */
	public bf.com.misys.eqf.types.header.RsHeader getRsHeader()
	{
		return this._rsHeader;
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
		if (_rsHeader != null && !org.castor.core.util.CycleBreaker.startingToCycle(_rsHeader))
		{
			result = 37 * result + _rsHeader.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_rsHeader);
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
	 * Sets the value of field 'rsHeader'.
	 * 
	 * @param rsHeader
	 *            the value of field 'rsHeader'.
	 */
	public void setRsHeader(final bf.com.misys.eqf.types.header.RsHeader rsHeader)
	{
		this._rsHeader = rsHeader;
	}

	/**
	 * Method unmarshalServiceUserExitRsHeader.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.ServiceUserExitRsHeader
	 */
	public static bf.com.misys.eqf.types.header.ServiceUserExitRsHeader unmarshalServiceUserExitRsHeader(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.ServiceUserExitRsHeader) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.ServiceUserExitRsHeader.class, reader);
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
