/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Class Formatting.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Formatting implements java.io.Serializable
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
	 * This specifies the request and response data format: ' '-Automatically determined. If enhanced XSD, then it is YYYY-MM-DD
	 * format otherwise Equation date format. '1'-YYYYMMDD '2'-YYYY-MM-DD '3'-Equation date format CYYMMDD
	 * 
	 */
	private java.lang.String _dateFormat;

	/**
	 * This specifies the request and response amount format: ' '-Automatically determined. If enhanced XSD, major currency units
	 * otherwise minor currency units. '1'-Major currency units '2'-Minor currency units eg 100099 is GBP 1000.99
	 * 
	 */
	private java.lang.String _amountFormat;

	/**
	 * This specifies whether all formats for entities is returned.
	 * 
	 * Selecting to retrieve all formats will affect performance as round trips to Equation will be needed: ' '-Standard formats
	 * only '1'-Convert all '2'-Convert only customer '3'-Convert only account
	 * 
	 */
	private java.lang.String _returnAllFormats;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public Formatting()
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

		if (obj instanceof Formatting)
		{

			Formatting temp = (Formatting) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._dateFormat != null)
			{
				if (temp._dateFormat == null)
					return false;
				if (this._dateFormat != temp._dateFormat)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._dateFormat);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._dateFormat);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._dateFormat);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._dateFormat);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._dateFormat.equals(temp._dateFormat))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._dateFormat);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._dateFormat);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._dateFormat);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._dateFormat);
					}
				}
			}
			else if (temp._dateFormat != null)
				return false;
			if (this._amountFormat != null)
			{
				if (temp._amountFormat == null)
					return false;
				if (this._amountFormat != temp._amountFormat)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._amountFormat);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._amountFormat);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._amountFormat);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._amountFormat);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._amountFormat.equals(temp._amountFormat))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._amountFormat);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._amountFormat);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._amountFormat);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._amountFormat);
					}
				}
			}
			else if (temp._amountFormat != null)
				return false;
			if (this._returnAllFormats != null)
			{
				if (temp._returnAllFormats == null)
					return false;
				if (this._returnAllFormats != temp._returnAllFormats)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._returnAllFormats);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._returnAllFormats);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._returnAllFormats);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._returnAllFormats);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._returnAllFormats.equals(temp._returnAllFormats))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._returnAllFormats);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._returnAllFormats);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._returnAllFormats);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._returnAllFormats);
					}
				}
			}
			else if (temp._returnAllFormats != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'amountFormat'. The field 'amountFormat' has the following description: This specifies the request
	 * and response amount format: ' '-Automatically determined. If enhanced XSD, major currency units otherwise minor currency
	 * units. '1'-Major currency units '2'-Minor currency units eg 100099 is GBP 1000.99
	 * 
	 * 
	 * @return the value of field 'AmountFormat'.
	 */
	public java.lang.String getAmountFormat()
	{
		return this._amountFormat;
	}

	/**
	 * Returns the value of field 'dateFormat'. The field 'dateFormat' has the following description: This specifies the request and
	 * response data format: ' '-Automatically determined. If enhanced XSD, then it is YYYY-MM-DD format otherwise Equation date
	 * format. '1'-YYYYMMDD '2'-YYYY-MM-DD '3'-Equation date format CYYMMDD
	 * 
	 * 
	 * @return the value of field 'DateFormat'.
	 */
	public java.lang.String getDateFormat()
	{
		return this._dateFormat;
	}

	/**
	 * Returns the value of field 'returnAllFormats'. The field 'returnAllFormats' has the following description: This specifies
	 * whether all formats for entities is returned. Selecting to retrieve all formats will affect performance as round trips to
	 * Equation will be needed: ' '-Standard formats only '1'-Convert all '2'-Convert only customer '3'-Convert only account
	 * 
	 * 
	 * @return the value of field 'ReturnAllFormats'.
	 */
	public java.lang.String getReturnAllFormats()
	{
		return this._returnAllFormats;
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
		if (_dateFormat != null && !org.castor.core.util.CycleBreaker.startingToCycle(_dateFormat))
		{
			result = 37 * result + _dateFormat.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_dateFormat);
		}
		if (_amountFormat != null && !org.castor.core.util.CycleBreaker.startingToCycle(_amountFormat))
		{
			result = 37 * result + _amountFormat.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_amountFormat);
		}
		if (_returnAllFormats != null && !org.castor.core.util.CycleBreaker.startingToCycle(_returnAllFormats))
		{
			result = 37 * result + _returnAllFormats.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_returnAllFormats);
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
	 * Sets the value of field 'amountFormat'. The field 'amountFormat' has the following description: This specifies the request
	 * and response amount format: ' '-Automatically determined. If enhanced XSD, major currency units otherwise minor currency
	 * units. '1'-Major currency units '2'-Minor currency units eg 100099 is GBP 1000.99
	 * 
	 * 
	 * @param amountFormat
	 *            the value of field 'amountFormat'.
	 */
	public void setAmountFormat(final java.lang.String amountFormat)
	{
		this._amountFormat = amountFormat;
	}

	/**
	 * Sets the value of field 'dateFormat'. The field 'dateFormat' has the following description: This specifies the request and
	 * response data format: ' '-Automatically determined. If enhanced XSD, then it is YYYY-MM-DD format otherwise Equation date
	 * format. '1'-YYYYMMDD '2'-YYYY-MM-DD '3'-Equation date format CYYMMDD
	 * 
	 * 
	 * @param dateFormat
	 *            the value of field 'dateFormat'.
	 */
	public void setDateFormat(final java.lang.String dateFormat)
	{
		this._dateFormat = dateFormat;
	}

	/**
	 * Sets the value of field 'returnAllFormats'. The field 'returnAllFormats' has the following description: This specifies
	 * whether all formats for entities is returned. Selecting to retrieve all formats will affect performance as round trips to
	 * Equation will be needed: ' '-Standard formats only '1'-Convert all '2'-Convert only customer '3'-Convert only account
	 * 
	 * 
	 * @param returnAllFormats
	 *            the value of field 'returnAllFormats'
	 */
	public void setReturnAllFormats(final java.lang.String returnAllFormats)
	{
		this._returnAllFormats = returnAllFormats;
	}

	/**
	 * Method unmarshalFormatting.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.Formatting
	 */
	public static bf.com.misys.eqf.types.header.Formatting unmarshalFormatting(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.Formatting) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.Formatting.class, reader);
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
