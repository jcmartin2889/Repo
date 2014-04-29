/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Class StartEsfRqProcessHeader.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class StartEsfRqProcessHeader implements java.io.Serializable
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
	 * The Task ESF request Header
	 * 
	 */
	private bf.com.misys.eqf.types.header.TaskEsfRqHeader _taskEsfRqHeader;

	/**
	 * The service name
	 * 
	 */
	private java.lang.String _serviceName;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public StartEsfRqProcessHeader()
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

		if (obj instanceof StartEsfRqProcessHeader)
		{

			StartEsfRqProcessHeader temp = (StartEsfRqProcessHeader) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._taskEsfRqHeader != null)
			{
				if (temp._taskEsfRqHeader == null)
					return false;
				if (this._taskEsfRqHeader != temp._taskEsfRqHeader)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._taskEsfRqHeader);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._taskEsfRqHeader);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._taskEsfRqHeader);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._taskEsfRqHeader);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._taskEsfRqHeader.equals(temp._taskEsfRqHeader))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._taskEsfRqHeader);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._taskEsfRqHeader);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._taskEsfRqHeader);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._taskEsfRqHeader);
					}
				}
			}
			else if (temp._taskEsfRqHeader != null)
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
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'serviceName'. The field 'serviceName' has the following description: The service name
	 * 
	 * 
	 * @return the value of field 'ServiceName'.
	 */
	public java.lang.String getServiceName()
	{
		return this._serviceName;
	}

	/**
	 * Returns the value of field 'taskEsfRqHeader'. The field 'taskEsfRqHeader' has the following description: The Task ESF request
	 * Header
	 * 
	 * 
	 * @return the value of field 'TaskEsfRqHeader'.
	 */
	public bf.com.misys.eqf.types.header.TaskEsfRqHeader getTaskEsfRqHeader()
	{
		return this._taskEsfRqHeader;
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
		if (_taskEsfRqHeader != null && !org.castor.core.util.CycleBreaker.startingToCycle(_taskEsfRqHeader))
		{
			result = 37 * result + _taskEsfRqHeader.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_taskEsfRqHeader);
		}
		if (_serviceName != null && !org.castor.core.util.CycleBreaker.startingToCycle(_serviceName))
		{
			result = 37 * result + _serviceName.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_serviceName);
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
	 * Sets the value of field 'serviceName'. The field 'serviceName' has the following description: The service name
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
	 * Sets the value of field 'taskEsfRqHeader'. The field 'taskEsfRqHeader' has the following description: The Task ESF request
	 * Header
	 * 
	 * 
	 * @param taskEsfRqHeader
	 *            the value of field 'taskEsfRqHeader'.
	 */
	public void setTaskEsfRqHeader(final bf.com.misys.eqf.types.header.TaskEsfRqHeader taskEsfRqHeader)
	{
		this._taskEsfRqHeader = taskEsfRqHeader;
	}

	/**
	 * Method unmarshalStartEsfRqProcessHeader.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.StartEsfRqProcessHeader
	 */
	public static bf.com.misys.eqf.types.header.StartEsfRqProcessHeader unmarshalStartEsfRqProcessHeader(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.StartEsfRqProcessHeader) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.StartEsfRqProcessHeader.class, reader);
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
