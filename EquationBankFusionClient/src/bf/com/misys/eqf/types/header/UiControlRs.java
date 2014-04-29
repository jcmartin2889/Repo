/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Class UiControlRs.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class UiControlRs implements java.io.Serializable
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
	 * Field _nextScrn.
	 */
	private java.lang.String _nextScrn;

	/**
	 * Field _nextProcess.
	 */
	private java.lang.Integer _nextProcess;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public UiControlRs()
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

		if (obj instanceof UiControlRs)
		{

			UiControlRs temp = (UiControlRs) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._nextScrn != null)
			{
				if (temp._nextScrn == null)
					return false;
				if (this._nextScrn != temp._nextScrn)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._nextScrn);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._nextScrn);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nextScrn);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nextScrn);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._nextScrn.equals(temp._nextScrn))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nextScrn);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nextScrn);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nextScrn);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nextScrn);
					}
				}
			}
			else if (temp._nextScrn != null)
				return false;
			if (this._nextProcess != null)
			{
				if (temp._nextProcess == null)
					return false;
				if (this._nextProcess != temp._nextProcess)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._nextProcess);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._nextProcess);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nextProcess);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nextProcess);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._nextProcess.equals(temp._nextProcess))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nextProcess);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nextProcess);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nextProcess);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nextProcess);
					}
				}
			}
			else if (temp._nextProcess != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'nextProcess'.
	 * 
	 * @return the value of field 'NextProcess'.
	 */
	public java.lang.Integer getNextProcess()
	{
		return this._nextProcess;
	}

	/**
	 * Returns the value of field 'nextScrn'.
	 * 
	 * @return the value of field 'NextScrn'.
	 */
	public java.lang.String getNextScrn()
	{
		return this._nextScrn;
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
		if (_nextScrn != null && !org.castor.core.util.CycleBreaker.startingToCycle(_nextScrn))
		{
			result = 37 * result + _nextScrn.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_nextScrn);
		}
		if (_nextProcess != null && !org.castor.core.util.CycleBreaker.startingToCycle(_nextProcess))
		{
			result = 37 * result + _nextProcess.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_nextProcess);
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
	 * Sets the value of field 'nextProcess'.
	 * 
	 * @param nextProcess
	 *            the value of field 'nextProcess'.
	 */
	public void setNextProcess(final java.lang.Integer nextProcess)
	{
		this._nextProcess = nextProcess;
	}

	/**
	 * Sets the value of field 'nextScrn'.
	 * 
	 * @param nextScrn
	 *            the value of field 'nextScrn'.
	 */
	public void setNextScrn(final java.lang.String nextScrn)
	{
		this._nextScrn = nextScrn;
	}

	/**
	 * Method unmarshalUiControlRs.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.UiControlRs
	 */
	public static bf.com.misys.eqf.types.header.UiControlRs unmarshalUiControlRs(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.UiControlRs) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.UiControlRs.class, reader);
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
