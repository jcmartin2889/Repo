/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1.1</a>, using an XML
 * Schema.
 * $Id: PV_row.java 7215 2010-05-10 14:41:27Z goldsmc1 $
 */

package bf.com.misys.eq4.search.hwr20r;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class PV_row.
 * 
 * @version $Revision$ $Date$
 */
public class PV_row extends bf.com.misys.eq4.search.hwr20r.Row 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _HWCLP_collateralType.
     */
    private java.lang.String _HWCLP_collateralType;

    /**
     * Field _HWCPD_collateralTypeDesc.
     */
    private java.lang.String _HWCPD_collateralTypeDesc;

    /**
     * Field _HWBVMS_editedBankValuationMargin.
     */
    private java.lang.String _HWBVMS_editedBankValuationMargin;

    /**
     * Field _HWBVM_bankValuationMargin.
     */
    private java.lang.String _HWBVM_bankValuationMargin;

    /**
     * Field _HWINS_insuranceRequired.
     */
    private java.lang.String _HWINS_insuranceRequired;


      //----------------/
     //- Constructors -/
    //----------------/

    public PV_row() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field
     * 'HWBVMS_editedBankValuationMargin'.
     * 
     * @return the value of field 'HWBVMS_editedBankValuationMargin'
     */
    public java.lang.String getHWBVMS_editedBankValuationMargin(
    ) {
        return this._HWBVMS_editedBankValuationMargin;
    }

    /**
     * Returns the value of field 'HWBVM_bankValuationMargin'.
     * 
     * @return the value of field 'HWBVM_bankValuationMargin'.
     */
    public java.lang.String getHWBVM_bankValuationMargin(
    ) {
        return this._HWBVM_bankValuationMargin;
    }

    /**
     * Returns the value of field 'HWCLP_collateralType'.
     * 
     * @return the value of field 'HWCLP_collateralType'.
     */
    public java.lang.String getHWCLP_collateralType(
    ) {
        return this._HWCLP_collateralType;
    }

    /**
     * Returns the value of field 'HWCPD_collateralTypeDesc'.
     * 
     * @return the value of field 'HWCPD_collateralTypeDesc'.
     */
    public java.lang.String getHWCPD_collateralTypeDesc(
    ) {
        return this._HWCPD_collateralTypeDesc;
    }

    /**
     * Returns the value of field 'HWINS_insuranceRequired'.
     * 
     * @return the value of field 'HWINS_insuranceRequired'.
     */
    public java.lang.String getHWINS_insuranceRequired(
    ) {
        return this._HWINS_insuranceRequired;
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid(
    ) {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, handler);
    }

    /**
     * Sets the value of field 'HWBVMS_editedBankValuationMargin'.
     * 
     * @param HWBVMS_editedBankValuationMargin the value of field
     * 'HWBVMS_editedBankValuationMargin'.
     */
    public void setHWBVMS_editedBankValuationMargin(
            final java.lang.String HWBVMS_editedBankValuationMargin) {
        this._HWBVMS_editedBankValuationMargin = HWBVMS_editedBankValuationMargin;
    }

    /**
     * Sets the value of field 'HWBVM_bankValuationMargin'.
     * 
     * @param HWBVM_bankValuationMargin the value of field
     * 'HWBVM_bankValuationMargin'.
     */
    public void setHWBVM_bankValuationMargin(
            final java.lang.String HWBVM_bankValuationMargin) {
        this._HWBVM_bankValuationMargin = HWBVM_bankValuationMargin;
    }

    /**
     * Sets the value of field 'HWCLP_collateralType'.
     * 
     * @param HWCLP_collateralType the value of field
     * 'HWCLP_collateralType'.
     */
    public void setHWCLP_collateralType(
            final java.lang.String HWCLP_collateralType) {
        this._HWCLP_collateralType = HWCLP_collateralType;
    }

    /**
     * Sets the value of field 'HWCPD_collateralTypeDesc'.
     * 
     * @param HWCPD_collateralTypeDesc the value of field
     * 'HWCPD_collateralTypeDesc'.
     */
    public void setHWCPD_collateralTypeDesc(
            final java.lang.String HWCPD_collateralTypeDesc) {
        this._HWCPD_collateralTypeDesc = HWCPD_collateralTypeDesc;
    }

    /**
     * Sets the value of field 'HWINS_insuranceRequired'.
     * 
     * @param HWINS_insuranceRequired the value of field
     * 'HWINS_insuranceRequired'.
     */
    public void setHWINS_insuranceRequired(
            final java.lang.String HWINS_insuranceRequired) {
        this._HWINS_insuranceRequired = HWINS_insuranceRequired;
    }

    /**
     * Method unmarshalPV_row.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled bf.com.misys.eq4.search.hwr20r.Row
     */
    public static bf.com.misys.eq4.search.hwr20r.Row unmarshalPV_row(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (bf.com.misys.eq4.search.hwr20r.Row) Unmarshaller.unmarshal(bf.com.misys.eq4.search.hwr20r.PV_row.class, reader);
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate(
    )
    throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

}