/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Class StartEsfRsProcessHeader.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class StartEsfRsProcessHeader implements java.io.Serializable
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
	 * The process id
	 * 
	 */
	private java.lang.String _processId;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public StartEsfRsProcessHeader()
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

		if (obj instanceof StartEsfRsProcessHeader)
		{

			StartEsfRsProcessHeader temp = (StartEsfRsProcessHeader) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._processId != null)
			{
				if (temp._processId == null)
					return false;
				if (this._processId != temp._processId)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._processId);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._processId);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._processId);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._processId);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._processId.equals(temp._processId))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._processId);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._processId);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._processId);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._processId);
					}
				}
			}
			else if (temp._processId != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'processId'. The field 'processId' has the following description: The process id
	 * 
	 * 
	 * @return the value of field 'ProcessId'.
	 */
	public java.lang.String getProcessId()
	{
		return this._processId;
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
		if (_processId != null && !org.castor.core.util.CycleBreaker.startingToCycle(_processId))
		{
			result = 37 * result + _processId.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_processId);
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
	 * Sets the value of field 'processId'. The field 'processId' has the following description: The process id
	 * 
	 * 
	 * @param processId
	 *            the value of field 'processId'.
	 */
	public void setProcessId(final java.lang.String processId)
	{
		this._processId = processId;
	}

	/**
	 * Method unmarshalStartEsfRsProcessHeader.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.StartEsfRsProcessHeader
	 */
	public static bf.com.misys.eqf.types.header.StartEsfRsProcessHeader unmarshalStartEsfRsProcessHeader(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.StartEsfRsProcessHeader) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.StartEsfRsProcessHeader.class, reader);
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
