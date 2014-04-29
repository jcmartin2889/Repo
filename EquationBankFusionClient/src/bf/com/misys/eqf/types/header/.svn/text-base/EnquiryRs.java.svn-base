/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Class EnquiryRs.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class EnquiryRs implements java.io.Serializable
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
	 * Field _totalPages.
	 */
	private java.lang.Integer _totalPages;

	/**
	 * Information to be returned next call to enable the process to resume at the correct point.
	 * 
	 */
	private java.lang.String _pagingInformation;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public EnquiryRs()
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

		if (obj instanceof EnquiryRs)
		{

			EnquiryRs temp = (EnquiryRs) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._totalPages != null)
			{
				if (temp._totalPages == null)
					return false;
				if (this._totalPages != temp._totalPages)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._totalPages);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._totalPages);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._totalPages);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._totalPages);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._totalPages.equals(temp._totalPages))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._totalPages);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._totalPages);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._totalPages);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._totalPages);
					}
				}
			}
			else if (temp._totalPages != null)
				return false;
			if (this._pagingInformation != null)
			{
				if (temp._pagingInformation == null)
					return false;
				if (this._pagingInformation != temp._pagingInformation)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._pagingInformation);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._pagingInformation);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._pagingInformation);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._pagingInformation);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._pagingInformation.equals(temp._pagingInformation))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._pagingInformation);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._pagingInformation);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._pagingInformation);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._pagingInformation);
					}
				}
			}
			else if (temp._pagingInformation != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'pagingInformation'. The field 'pagingInformation' has the following description: Information to
	 * be returned next call to enable the process to resume at the correct point.
	 * 
	 * 
	 * @return the value of field 'PagingInformation'.
	 */
	public java.lang.String getPagingInformation()
	{
		return this._pagingInformation;
	}

	/**
	 * Returns the value of field 'totalPages'.
	 * 
	 * @return the value of field 'TotalPages'.
	 */
	public java.lang.Integer getTotalPages()
	{
		return this._totalPages;
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
		if (_totalPages != null && !org.castor.core.util.CycleBreaker.startingToCycle(_totalPages))
		{
			result = 37 * result + _totalPages.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_totalPages);
		}
		if (_pagingInformation != null && !org.castor.core.util.CycleBreaker.startingToCycle(_pagingInformation))
		{
			result = 37 * result + _pagingInformation.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_pagingInformation);
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
	 * Sets the value of field 'pagingInformation'. The field 'pagingInformation' has the following description: Information to be
	 * returned next call to enable the process to resume at the correct point.
	 * 
	 * 
	 * @param pagingInformation
	 *            the value of field 'pagingInformation'.
	 */
	public void setPagingInformation(final java.lang.String pagingInformation)
	{
		this._pagingInformation = pagingInformation;
	}

	/**
	 * Sets the value of field 'totalPages'.
	 * 
	 * @param totalPages
	 *            the value of field 'totalPages'.
	 */
	public void setTotalPages(final java.lang.Integer totalPages)
	{
		this._totalPages = totalPages;
	}

	/**
	 * Method unmarshalEnquiryRs.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.EnquiryRs
	 */
	public static bf.com.misys.eqf.types.header.EnquiryRs unmarshalEnquiryRs(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.EnquiryRs) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.EnquiryRs.class, reader);
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
