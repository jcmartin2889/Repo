/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Contains output parameter to be passed from BFEQ to WPS task during ESF processing.
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class TaskEsfRsHeader implements java.io.Serializable
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
	 * Approve or Decline or Authorize first warning
	 * 
	 */
	private java.lang.String _taskAction;

	/**
	 * Comments or remarks for the action taken, if any
	 * 
	 */
	private java.lang.String _reason;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public TaskEsfRsHeader()
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

		if (obj instanceof TaskEsfRsHeader)
		{

			TaskEsfRsHeader temp = (TaskEsfRsHeader) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._taskAction != null)
			{
				if (temp._taskAction == null)
					return false;
				if (this._taskAction != temp._taskAction)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._taskAction);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._taskAction);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._taskAction);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._taskAction);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._taskAction.equals(temp._taskAction))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._taskAction);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._taskAction);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._taskAction);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._taskAction);
					}
				}
			}
			else if (temp._taskAction != null)
				return false;
			if (this._reason != null)
			{
				if (temp._reason == null)
					return false;
				if (this._reason != temp._reason)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._reason);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._reason);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._reason);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._reason);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._reason.equals(temp._reason))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._reason);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._reason);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._reason);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._reason);
					}
				}
			}
			else if (temp._reason != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'reason'. The field 'reason' has the following description: Comments or remarks for the action
	 * taken, if any
	 * 
	 * 
	 * @return the value of field 'Reason'.
	 */
	public java.lang.String getReason()
	{
		return this._reason;
	}

	/**
	 * Returns the value of field 'taskAction'. The field 'taskAction' has the following description: Approve or Decline or
	 * Authorize first warning
	 * 
	 * 
	 * @return the value of field 'TaskAction'.
	 */
	public java.lang.String getTaskAction()
	{
		return this._taskAction;
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
		if (_taskAction != null && !org.castor.core.util.CycleBreaker.startingToCycle(_taskAction))
		{
			result = 37 * result + _taskAction.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_taskAction);
		}
		if (_reason != null && !org.castor.core.util.CycleBreaker.startingToCycle(_reason))
		{
			result = 37 * result + _reason.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_reason);
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
	 * Sets the value of field 'reason'. The field 'reason' has the following description: Comments or remarks for the action taken,
	 * if any
	 * 
	 * 
	 * @param reason
	 *            the value of field 'reason'.
	 */
	public void setReason(final java.lang.String reason)
	{
		this._reason = reason;
	}

	/**
	 * Sets the value of field 'taskAction'. The field 'taskAction' has the following description: Approve or Decline or Authorize
	 * first warning
	 * 
	 * 
	 * @param taskAction
	 *            the value of field 'taskAction'.
	 */
	public void setTaskAction(final java.lang.String taskAction)
	{
		this._taskAction = taskAction;
	}

	/**
	 * Method unmarshalTaskEsfRsHeader.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.TaskEsfRsHeader
	 */
	public static bf.com.misys.eqf.types.header.TaskEsfRsHeader unmarshalTaskEsfRsHeader(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.TaskEsfRsHeader) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.TaskEsfRsHeader.class, reader);
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
