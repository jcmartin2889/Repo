/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Class UpdateEsfStatusRsHeader.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class UpdateEsfStatusRsHeader implements java.io.Serializable
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
	 * Field _errorCode.
	 */
	private java.lang.String _errorCode;

	/**
	 * Field _errorMessage.
	 */
	private java.lang.String _errorMessage;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public UpdateEsfStatusRsHeader()
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

		if (obj instanceof UpdateEsfStatusRsHeader)
		{

			UpdateEsfStatusRsHeader temp = (UpdateEsfStatusRsHeader) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._errorCode != null)
			{
				if (temp._errorCode == null)
					return false;
				if (this._errorCode != temp._errorCode)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._errorCode);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._errorCode);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._errorCode);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._errorCode);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._errorCode.equals(temp._errorCode))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._errorCode);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._errorCode);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._errorCode);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._errorCode);
					}
				}
			}
			else if (temp._errorCode != null)
				return false;
			if (this._errorMessage != null)
			{
				if (temp._errorMessage == null)
					return false;
				if (this._errorMessage != temp._errorMessage)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._errorMessage);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._errorMessage);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._errorMessage);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._errorMessage);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._errorMessage.equals(temp._errorMessage))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._errorMessage);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._errorMessage);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._errorMessage);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._errorMessage);
					}
				}
			}
			else if (temp._errorMessage != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'errorCode'.
	 * 
	 * @return the value of field 'ErrorCode'.
	 */
	public java.lang.String getErrorCode()
	{
		return this._errorCode;
	}

	/**
	 * Returns the value of field 'errorMessage'.
	 * 
	 * @return the value of field 'ErrorMessage'.
	 */
	public java.lang.String getErrorMessage()
	{
		return this._errorMessage;
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
		if (_errorCode != null && !org.castor.core.util.CycleBreaker.startingToCycle(_errorCode))
		{
			result = 37 * result + _errorCode.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_errorCode);
		}
		if (_errorMessage != null && !org.castor.core.util.CycleBreaker.startingToCycle(_errorMessage))
		{
			result = 37 * result + _errorMessage.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_errorMessage);
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
	 * Sets the value of field 'errorCode'.
	 * 
	 * @param errorCode
	 *            the value of field 'errorCode'.
	 */
	public void setErrorCode(final java.lang.String errorCode)
	{
		this._errorCode = errorCode;
	}

	/**
	 * Sets the value of field 'errorMessage'.
	 * 
	 * @param errorMessage
	 *            the value of field 'errorMessage'.
	 */
	public void setErrorMessage(final java.lang.String errorMessage)
	{
		this._errorMessage = errorMessage;
	}

	/**
	 * Method unmarshalUpdateEsfStatusRsHeader.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.UpdateEsfStatusRsHeader
	 */
	public static bf.com.misys.eqf.types.header.UpdateEsfStatusRsHeader unmarshalUpdateEsfStatusRsHeader(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.UpdateEsfStatusRsHeader) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.UpdateEsfStatusRsHeader.class, reader);
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
