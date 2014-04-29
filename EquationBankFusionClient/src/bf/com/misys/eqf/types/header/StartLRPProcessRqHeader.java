/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Contains input parameter to start a process
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class StartLRPProcessRqHeader implements java.io.Serializable
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
	 * Operation name
	 * 
	 */
	private java.lang.String _operationName;

	/**
	 * Service name
	 * 
	 */
	private java.lang.String _serviceName;

	/**
	 * This is the payload
	 * 
	 */
	private java.lang.Object _payload;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public StartLRPProcessRqHeader()
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

		if (obj instanceof StartLRPProcessRqHeader)
		{

			StartLRPProcessRqHeader temp = (StartLRPProcessRqHeader) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._operationName != null)
			{
				if (temp._operationName == null)
					return false;
				if (this._operationName != temp._operationName)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._operationName);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._operationName);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._operationName);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._operationName);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._operationName.equals(temp._operationName))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._operationName);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._operationName);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._operationName);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._operationName);
					}
				}
			}
			else if (temp._operationName != null)
				return false;
			if (this._serviceName != null)
			{
				if (temp._serviceName == null)
					return false;
				if (this._serviceName != temp._serviceName)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._serviceName);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._serviceName);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._serviceName);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._serviceName);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._serviceName.equals(temp._serviceName))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._serviceName);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._serviceName);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._serviceName);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._serviceName);
					}
				}
			}
			else if (temp._serviceName != null)
				return false;
			if (this._payload != null)
			{
				if (temp._payload == null)
					return false;
				if (this._payload != temp._payload)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._payload);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._payload);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._payload);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._payload);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._payload.equals(temp._payload))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._payload);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._payload);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._payload);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._payload);
					}
				}
			}
			else if (temp._payload != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'operationName'. The field 'operationName' has the following description: Operation name
	 * 
	 * 
	 * @return the value of field 'OperationName'.
	 */
	public java.lang.String getOperationName()
	{
		return this._operationName;
	}

	/**
	 * Returns the value of field 'payload'. The field 'payload' has the following description: This is the payload
	 * 
	 * 
	 * @return the value of field 'Payload'.
	 */
	public java.lang.Object getPayload()
	{
		return this._payload;
	}

	/**
	 * Returns the value of field 'serviceName'. The field 'serviceName' has the following description: Service name
	 * 
	 * 
	 * @return the value of field 'ServiceName'.
	 */
	public java.lang.String getServiceName()
	{
		return this._serviceName;
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
		if (_operationName != null && !org.castor.core.util.CycleBreaker.startingToCycle(_operationName))
		{
			result = 37 * result + _operationName.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_operationName);
		}
		if (_serviceName != null && !org.castor.core.util.CycleBreaker.startingToCycle(_serviceName))
		{
			result = 37 * result + _serviceName.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_serviceName);
		}
		if (_payload != null && !org.castor.core.util.CycleBreaker.startingToCycle(_payload))
		{
			result = 37 * result + _payload.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_payload);
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
	 * Sets the value of field 'operationName'. The field 'operationName' has the following description: Operation name
	 * 
	 * 
	 * @param operationName
	 *            the value of field 'operationName'.
	 */
	public void setOperationName(final java.lang.String operationName)
	{
		this._operationName = operationName;
	}

	/**
	 * Sets the value of field 'payload'. The field 'payload' has the following description: This is the payload
	 * 
	 * 
	 * @param payload
	 *            the value of field 'payload'.
	 */
	public void setPayload(final java.lang.Object payload)
	{
		this._payload = payload;
	}

	/**
	 * Sets the value of field 'serviceName'. The field 'serviceName' has the following description: Service name
	 * 
	 * 
	 * @param serviceName
	 *            the value of field 'serviceName'.
	 */
	public void setServiceName(final java.lang.String serviceName)
	{
		this._serviceName = serviceName;
	}

	/**
	 * Method unmarshalStartLRPProcessRqHeader.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.StartLRPProcessRqHeader
	 */
	public static bf.com.misys.eqf.types.header.StartLRPProcessRqHeader unmarshalStartLRPProcessRqHeader(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.StartLRPProcessRqHeader) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.StartLRPProcessRqHeader.class, reader);
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
