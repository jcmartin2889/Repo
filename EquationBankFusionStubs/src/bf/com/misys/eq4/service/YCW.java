/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.1.1</a>, using an XML Schema. $Id:
 * YCW.java,v 1.2 2009/05/19 06:41:51 weddelc1 Exp $
 */

package bf.com.misys.eq4.service;

// ---------------------------------/
// - Imported classes and packages -/
// ---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class YCW.
 * 
 * @version $Revision$ $Date$
 */
public class YCW implements java.io.Serializable
{

	// --------------------------/
	// - Class/Member Variables -/
	// --------------------------/

	/**
	 * Field _OS_OSBRNM.
	 */
	private java.lang.String _OS_OSBRNM;

	/**
	 * Field _OS_OSDLP.
	 */
	private java.lang.String _OS_OSDLP;

	/**
	 * Field _OS_OSDLR.
	 */
	private java.lang.String _OS_OSDLR;

	/**
	 * Field _OS_OSCUS.
	 */
	private java.lang.String _OS_OSCUS;

	/**
	 * Field _OS_OSCLC.
	 */
	private java.lang.String _OS_OSCLC;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public YCW()
	{
		super();
	}

	// -----------/
	// - Methods -/
	// -----------/

	/**
	 * Returns the value of field 'OS_OSBRNM'.
	 * 
	 * @return the value of field 'OS_OSBRNM'.
	 */
	public java.lang.String getOS_OSBRNM()
	{
		return this._OS_OSBRNM;
	}

	/**
	 * Returns the value of field 'OS_OSCLC'.
	 * 
	 * @return the value of field 'OS_OSCLC'.
	 */
	public java.lang.String getOS_OSCLC()
	{
		return this._OS_OSCLC;
	}

	/**
	 * Returns the value of field 'OS_OSCUS'.
	 * 
	 * @return the value of field 'OS_OSCUS'.
	 */
	public java.lang.String getOS_OSCUS()
	{
		return this._OS_OSCUS;
	}

	/**
	 * Returns the value of field 'OS_OSDLP'.
	 * 
	 * @return the value of field 'OS_OSDLP'.
	 */
	public java.lang.String getOS_OSDLP()
	{
		return this._OS_OSDLP;
	}

	/**
	 * Returns the value of field 'OS_OSDLR'.
	 * 
	 * @return the value of field 'OS_OSDLR'.
	 */
	public java.lang.String getOS_OSDLR()
	{
		return this._OS_OSDLR;
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
		Marshaller.marshal(this, out);
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
		Marshaller.marshal(this, handler);
	}

	/**
	 * Sets the value of field 'OS_OSBRNM'.
	 * 
	 * @param OS_OSBRNM
	 *            the value of field 'OS_OSBRNM'.
	 */
	public void setOS_OSBRNM(final java.lang.String OS_OSBRNM)
	{
		this._OS_OSBRNM = OS_OSBRNM;
	}

	/**
	 * Sets the value of field 'OS_OSCLC'.
	 * 
	 * @param OS_OSCLC
	 *            the value of field 'OS_OSCLC'.
	 */
	public void setOS_OSCLC(final java.lang.String OS_OSCLC)
	{
		this._OS_OSCLC = OS_OSCLC;
	}

	/**
	 * Sets the value of field 'OS_OSCUS'.
	 * 
	 * @param OS_OSCUS
	 *            the value of field 'OS_OSCUS'.
	 */
	public void setOS_OSCUS(final java.lang.String OS_OSCUS)
	{
		this._OS_OSCUS = OS_OSCUS;
	}

	/**
	 * Sets the value of field 'OS_OSDLP'.
	 * 
	 * @param OS_OSDLP
	 *            the value of field 'OS_OSDLP'.
	 */
	public void setOS_OSDLP(final java.lang.String OS_OSDLP)
	{
		this._OS_OSDLP = OS_OSDLP;
	}

	/**
	 * Sets the value of field 'OS_OSDLR'.
	 * 
	 * @param OS_OSDLR
	 *            the value of field 'OS_OSDLR'.
	 */
	public void setOS_OSDLR(final java.lang.String OS_OSDLR)
	{
		this._OS_OSDLR = OS_OSDLR;
	}

	/**
	 * Method unmarshalYCW.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eq4.service.YCW
	 */
	public static bf.com.misys.eq4.service.YCW unmarshalYCW(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eq4.service.YCW) Unmarshaller.unmarshal(bf.com.misys.eq4.service.YCW.class, reader);
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
