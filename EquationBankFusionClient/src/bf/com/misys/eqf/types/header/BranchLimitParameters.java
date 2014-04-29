/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Class BranchLimitParameters.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class BranchLimitParameters implements java.io.Serializable
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
	 * Field _affectedBranch.
	 */
	private bf.com.misys.eqf.types.header.BranchKeys _affectedBranch;

	/**
	 * Field _localCurrencyAmount.
	 */
	private java.lang.String _localCurrencyAmount;

	/**
	 * Field _debitCredit.
	 */
	private java.lang.String _debitCredit;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public BranchLimitParameters()
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

		if (obj instanceof BranchLimitParameters)
		{

			BranchLimitParameters temp = (BranchLimitParameters) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._affectedBranch != null)
			{
				if (temp._affectedBranch == null)
					return false;
				if (this._affectedBranch != temp._affectedBranch)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._affectedBranch);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._affectedBranch);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._affectedBranch);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._affectedBranch);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._affectedBranch.equals(temp._affectedBranch))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._affectedBranch);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._affectedBranch);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._affectedBranch);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._affectedBranch);
					}
				}
			}
			else if (temp._affectedBranch != null)
				return false;
			if (this._localCurrencyAmount != null)
			{
				if (temp._localCurrencyAmount == null)
					return false;
				if (this._localCurrencyAmount != temp._localCurrencyAmount)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._localCurrencyAmount);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._localCurrencyAmount);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._localCurrencyAmount);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._localCurrencyAmount);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._localCurrencyAmount.equals(temp._localCurrencyAmount))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._localCurrencyAmount);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._localCurrencyAmount);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._localCurrencyAmount);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._localCurrencyAmount);
					}
				}
			}
			else if (temp._localCurrencyAmount != null)
				return false;
			if (this._debitCredit != null)
			{
				if (temp._debitCredit == null)
					return false;
				if (this._debitCredit != temp._debitCredit)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._debitCredit);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._debitCredit);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._debitCredit);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._debitCredit);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._debitCredit.equals(temp._debitCredit))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._debitCredit);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._debitCredit);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._debitCredit);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._debitCredit);
					}
				}
			}
			else if (temp._debitCredit != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'affectedBranch'.
	 * 
	 * @return the value of field 'AffectedBranch'.
	 */
	public bf.com.misys.eqf.types.header.BranchKeys getAffectedBranch()
	{
		return this._affectedBranch;
	}

	/**
	 * Returns the value of field 'debitCredit'.
	 * 
	 * @return the value of field 'DebitCredit'.
	 */
	public java.lang.String getDebitCredit()
	{
		return this._debitCredit;
	}

	/**
	 * Returns the value of field 'localCurrencyAmount'.
	 * 
	 * @return the value of field 'LocalCurrencyAmount'.
	 */
	public java.lang.String getLocalCurrencyAmount()
	{
		return this._localCurrencyAmount;
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
		if (_affectedBranch != null && !org.castor.core.util.CycleBreaker.startingToCycle(_affectedBranch))
		{
			result = 37 * result + _affectedBranch.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_affectedBranch);
		}
		if (_localCurrencyAmount != null && !org.castor.core.util.CycleBreaker.startingToCycle(_localCurrencyAmount))
		{
			result = 37 * result + _localCurrencyAmount.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_localCurrencyAmount);
		}
		if (_debitCredit != null && !org.castor.core.util.CycleBreaker.startingToCycle(_debitCredit))
		{
			result = 37 * result + _debitCredit.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_debitCredit);
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
	 * Sets the value of field 'affectedBranch'.
	 * 
	 * @param affectedBranch
	 *            the value of field 'affectedBranch'.
	 */
	public void setAffectedBranch(final bf.com.misys.eqf.types.header.BranchKeys affectedBranch)
	{
		this._affectedBranch = affectedBranch;
	}

	/**
	 * Sets the value of field 'debitCredit'.
	 * 
	 * @param debitCredit
	 *            the value of field 'debitCredit'.
	 */
	public void setDebitCredit(final java.lang.String debitCredit)
	{
		this._debitCredit = debitCredit;
	}

	/**
	 * Sets the value of field 'localCurrencyAmount'.
	 * 
	 * @param localCurrencyAmount
	 *            the value of field 'localCurrencyAmount'.
	 */
	public void setLocalCurrencyAmount(final java.lang.String localCurrencyAmount)
	{
		this._localCurrencyAmount = localCurrencyAmount;
	}

	/**
	 * Method unmarshalBranchLimitParameters.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.BranchLimitParameters
	 */
	public static bf.com.misys.eqf.types.header.BranchLimitParameters unmarshalBranchLimitParameters(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.BranchLimitParameters) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.BranchLimitParameters.class, reader);
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
