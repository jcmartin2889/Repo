/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Either branch code and/or branch mnemonic can be specified. If both keys are specified then it is assumed that branch mnemonic
 * takes priority. Reserved for future use.
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class BranchKeys implements java.io.Serializable
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
	 * Equivalent to Equation branch number.
	 * 
	 */
	private java.lang.String _branchCode;

	/**
	 * Equivalent to Equation branch mnemonic.
	 * 
	 */
	private java.lang.String _branchMnemonic;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public BranchKeys()
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

		if (obj instanceof BranchKeys)
		{

			BranchKeys temp = (BranchKeys) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._branchCode != null)
			{
				if (temp._branchCode == null)
					return false;
				if (this._branchCode != temp._branchCode)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._branchCode);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._branchCode);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._branchCode);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._branchCode);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._branchCode.equals(temp._branchCode))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._branchCode);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._branchCode);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._branchCode);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._branchCode);
					}
				}
			}
			else if (temp._branchCode != null)
				return false;
			if (this._branchMnemonic != null)
			{
				if (temp._branchMnemonic == null)
					return false;
				if (this._branchMnemonic != temp._branchMnemonic)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._branchMnemonic);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._branchMnemonic);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._branchMnemonic);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._branchMnemonic);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._branchMnemonic.equals(temp._branchMnemonic))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._branchMnemonic);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._branchMnemonic);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._branchMnemonic);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._branchMnemonic);
					}
				}
			}
			else if (temp._branchMnemonic != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'branchCode'. The field 'branchCode' has the following description: Equivalent to Equation branch
	 * number.
	 * 
	 * 
	 * @return the value of field 'BranchCode'.
	 */
	public java.lang.String getBranchCode()
	{
		return this._branchCode;
	}

	/**
	 * Returns the value of field 'branchMnemonic'. The field 'branchMnemonic' has the following description: Equivalent to Equation
	 * branch mnemonic.
	 * 
	 * 
	 * @return the value of field 'BranchMnemonic'.
	 */
	public java.lang.String getBranchMnemonic()
	{
		return this._branchMnemonic;
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
		if (_branchCode != null && !org.castor.core.util.CycleBreaker.startingToCycle(_branchCode))
		{
			result = 37 * result + _branchCode.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_branchCode);
		}
		if (_branchMnemonic != null && !org.castor.core.util.CycleBreaker.startingToCycle(_branchMnemonic))
		{
			result = 37 * result + _branchMnemonic.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_branchMnemonic);
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
	 * Sets the value of field 'branchCode'. The field 'branchCode' has the following description: Equivalent to Equation branch
	 * number.
	 * 
	 * 
	 * @param branchCode
	 *            the value of field 'branchCode'.
	 */
	public void setBranchCode(final java.lang.String branchCode)
	{
		this._branchCode = branchCode;
	}

	/**
	 * Sets the value of field 'branchMnemonic'. The field 'branchMnemonic' has the following description: Equivalent to Equation
	 * branch mnemonic.
	 * 
	 * 
	 * @param branchMnemonic
	 *            the value of field 'branchMnemonic'.
	 */
	public void setBranchMnemonic(final java.lang.String branchMnemonic)
	{
		this._branchMnemonic = branchMnemonic;
	}

	/**
	 * Method unmarshalBranchKeys.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.BranchKeys
	 */
	public static bf.com.misys.eqf.types.header.BranchKeys unmarshalBranchKeys(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.BranchKeys) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.BranchKeys.class, reader);
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
