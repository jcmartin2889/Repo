/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Class RsHeader.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class RsHeader implements java.io.Serializable
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
	 * Field _status.
	 */
	private bf.com.misys.eqf.types.header.MessageStatus _status;

	/**
	 * This user extension data will be returned from ThreadData with a key of "UserExtension"
	 * 
	 */
	private java.lang.Object _userExtension;

	/**
	 * Field _sessionId.
	 */
	private java.lang.String _sessionId;

	/**
	 * This echoes the MessageId sent in the request.
	 * 
	 */
	private java.lang.String _correlationId;

	/**
	 * The runtime environment build.
	 * 
	 */
	private java.lang.String _buid;

	/**
	 * The version used.
	 * 
	 */
	private java.lang.String _version;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public RsHeader()
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

		if (obj instanceof RsHeader)
		{

			RsHeader temp = (RsHeader) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._status != null)
			{
				if (temp._status == null)
					return false;
				if (this._status != temp._status)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._status);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._status);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._status);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._status);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._status.equals(temp._status))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._status);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._status);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._status);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._status);
					}
				}
			}
			else if (temp._status != null)
				return false;
			if (this._userExtension != null)
			{
				if (temp._userExtension == null)
					return false;
				if (this._userExtension != temp._userExtension)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._userExtension);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._userExtension);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._userExtension);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._userExtension);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._userExtension.equals(temp._userExtension))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._userExtension);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._userExtension);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._userExtension);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._userExtension);
					}
				}
			}
			else if (temp._userExtension != null)
				return false;
			if (this._sessionId != null)
			{
				if (temp._sessionId == null)
					return false;
				if (this._sessionId != temp._sessionId)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._sessionId);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._sessionId);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sessionId);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sessionId);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._sessionId.equals(temp._sessionId))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sessionId);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sessionId);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sessionId);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sessionId);
					}
				}
			}
			else if (temp._sessionId != null)
				return false;
			if (this._correlationId != null)
			{
				if (temp._correlationId == null)
					return false;
				if (this._correlationId != temp._correlationId)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._correlationId);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._correlationId);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._correlationId);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._correlationId);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._correlationId.equals(temp._correlationId))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._correlationId);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._correlationId);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._correlationId);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._correlationId);
					}
				}
			}
			else if (temp._correlationId != null)
				return false;
			if (this._buid != null)
			{
				if (temp._buid == null)
					return false;
				if (this._buid != temp._buid)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._buid);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._buid);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._buid);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._buid);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._buid.equals(temp._buid))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._buid);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._buid);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._buid);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._buid);
					}
				}
			}
			else if (temp._buid != null)
				return false;
			if (this._version != null)
			{
				if (temp._version == null)
					return false;
				if (this._version != temp._version)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._version);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._version);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._version);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._version);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._version.equals(temp._version))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._version);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._version);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._version);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._version);
					}
				}
			}
			else if (temp._version != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'buid'. The field 'buid' has the following description: The runtime environment build.
	 * 
	 * 
	 * @return the value of field 'Buid'.
	 */
	public java.lang.String getBuid()
	{
		return this._buid;
	}

	/**
	 * Returns the value of field 'correlationId'. The field 'correlationId' has the following description: This echoes the
	 * MessageId sent in the request.
	 * 
	 * 
	 * @return the value of field 'CorrelationId'.
	 */
	public java.lang.String getCorrelationId()
	{
		return this._correlationId;
	}

	/**
	 * Returns the value of field 'sessionId'.
	 * 
	 * @return the value of field 'SessionId'.
	 */
	public java.lang.String getSessionId()
	{
		return this._sessionId;
	}

	/**
	 * Returns the value of field 'status'.
	 * 
	 * @return the value of field 'Status'.
	 */
	public bf.com.misys.eqf.types.header.MessageStatus getStatus()
	{
		return this._status;
	}

	/**
	 * Returns the value of field 'userExtension'. The field 'userExtension' has the following description: This user extension data
	 * will be returned from ThreadData with a key of "UserExtension"
	 * 
	 * 
	 * @return the value of field 'UserExtension'.
	 */
	public java.lang.Object getUserExtension()
	{
		return this._userExtension;
	}

	/**
	 * Returns the value of field 'version'. The field 'version' has the following description: The version used.
	 * 
	 * 
	 * @return the value of field 'Version'.
	 */
	public java.lang.String getVersion()
	{
		return this._version;
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
		if (_status != null && !org.castor.core.util.CycleBreaker.startingToCycle(_status))
		{
			result = 37 * result + _status.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_status);
		}
		if (_userExtension != null && !org.castor.core.util.CycleBreaker.startingToCycle(_userExtension))
		{
			result = 37 * result + _userExtension.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_userExtension);
		}
		if (_sessionId != null && !org.castor.core.util.CycleBreaker.startingToCycle(_sessionId))
		{
			result = 37 * result + _sessionId.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_sessionId);
		}
		if (_correlationId != null && !org.castor.core.util.CycleBreaker.startingToCycle(_correlationId))
		{
			result = 37 * result + _correlationId.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_correlationId);
		}
		if (_buid != null && !org.castor.core.util.CycleBreaker.startingToCycle(_buid))
		{
			result = 37 * result + _buid.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_buid);
		}
		if (_version != null && !org.castor.core.util.CycleBreaker.startingToCycle(_version))
		{
			result = 37 * result + _version.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_version);
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
	 * Sets the value of field 'buid'. The field 'buid' has the following description: The runtime environment build.
	 * 
	 * 
	 * @param buid
	 *            the value of field 'buid'.
	 */
	public void setBuid(final java.lang.String buid)
	{
		this._buid = buid;
	}

	/**
	 * Sets the value of field 'correlationId'. The field 'correlationId' has the following description: This echoes the MessageId
	 * sent in the request.
	 * 
	 * 
	 * @param correlationId
	 *            the value of field 'correlationId'.
	 */
	public void setCorrelationId(final java.lang.String correlationId)
	{
		this._correlationId = correlationId;
	}

	/**
	 * Sets the value of field 'sessionId'.
	 * 
	 * @param sessionId
	 *            the value of field 'sessionId'.
	 */
	public void setSessionId(final java.lang.String sessionId)
	{
		this._sessionId = sessionId;
	}

	/**
	 * Sets the value of field 'status'.
	 * 
	 * @param status
	 *            the value of field 'status'.
	 */
	public void setStatus(final bf.com.misys.eqf.types.header.MessageStatus status)
	{
		this._status = status;
	}

	/**
	 * Sets the value of field 'userExtension'. The field 'userExtension' has the following description: This user extension data
	 * will be returned from ThreadData with a key of "UserExtension"
	 * 
	 * 
	 * @param userExtension
	 *            the value of field 'userExtension'.
	 */
	public void setUserExtension(final java.lang.Object userExtension)
	{
		this._userExtension = userExtension;
	}

	/**
	 * Sets the value of field 'version'. The field 'version' has the following description: The version used.
	 * 
	 * 
	 * @param version
	 *            the value of field 'version'.
	 */
	public void setVersion(final java.lang.String version)
	{
		this._version = version;
	}

	/**
	 * Method unmarshalRsHeader.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.RsHeade
	 */
	public static bf.com.misys.eqf.types.header.RsHeader unmarshalRsHeader(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.RsHeader) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.RsHeader.class, reader);
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
