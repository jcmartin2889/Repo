/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1.1</a>, using an XML
 * Schema.
 * $Id: EQ_CMN_addMaintainHoldCodeHCX_SRV.java 11568 2011-08-05 11:56:12Z lima12 $
 */

package bf.com.misys.equation.bankfusion.service.cmn;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class EQ_CMN_addMaintainHoldCodeHCX_SRV.
 * 
 * @version $Revision$ $Date$
 */
public class EQ_CMN_addMaintainHoldCodeHCX_SRV implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _HCI_HRC_holdCode.
     */
    private java.lang.String _HCI_HRC_holdCode;

    /**
     * Field _HCI_HRD_holdDescription.
     */
    private java.lang.String _HCI_HRD_holdDescription;

    /**
     * Field _HCI_DED_defaultExpiryDate.
     */
    private java.lang.String _HCI_DED_defaultExpiryDate;


      //----------------/
     //- Constructors -/
    //----------------/

    public EQ_CMN_addMaintainHoldCodeHCX_SRV() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Overrides the java.lang.Object.equals method.
     * 
     * @param obj
     * @return true if the objects are equal.
     */
    public boolean equals(
            final java.lang.Object obj) {
        if ( this == obj )
            return true;
        
        if (obj instanceof EQ_CMN_addMaintainHoldCodeHCX_SRV) {
        
            EQ_CMN_addMaintainHoldCodeHCX_SRV temp = (EQ_CMN_addMaintainHoldCodeHCX_SRV)obj;
            if (this._HCI_HRC_holdCode != null) {
                if (temp._HCI_HRC_holdCode == null) return false;
                else if (!(this._HCI_HRC_holdCode.equals(temp._HCI_HRC_holdCode))) 
                    return false;
            }
            else if (temp._HCI_HRC_holdCode != null)
                return false;
            if (this._HCI_HRD_holdDescription != null) {
                if (temp._HCI_HRD_holdDescription == null) return false;
                else if (!(this._HCI_HRD_holdDescription.equals(temp._HCI_HRD_holdDescription))) 
                    return false;
            }
            else if (temp._HCI_HRD_holdDescription != null)
                return false;
            if (this._HCI_DED_defaultExpiryDate != null) {
                if (temp._HCI_DED_defaultExpiryDate == null) return false;
                else if (!(this._HCI_DED_defaultExpiryDate.equals(temp._HCI_DED_defaultExpiryDate))) 
                    return false;
            }
            else if (temp._HCI_DED_defaultExpiryDate != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'HCI_DED_defaultExpiryDate'.
     * 
     * @return the value of field 'HCI_DED_defaultExpiryDate'.
     */
    public java.lang.String getHCI_DED_defaultExpiryDate(
    ) {
        return this._HCI_DED_defaultExpiryDate;
    }

    /**
     * Returns the value of field 'HCI_HRC_holdCode'.
     * 
     * @return the value of field 'HCI_HRC_holdCode'.
     */
    public java.lang.String getHCI_HRC_holdCode(
    ) {
        return this._HCI_HRC_holdCode;
    }

    /**
     * Returns the value of field 'HCI_HRD_holdDescription'.
     * 
     * @return the value of field 'HCI_HRD_holdDescription'.
     */
    public java.lang.String getHCI_HRD_holdDescription(
    ) {
        return this._HCI_HRD_holdDescription;
    }

    /**
     * Overrides the java.lang.Object.hashCode method.
     * <p>
     * The following steps came from <b>Effective Java Programming
     * Language Guide</b> by Joshua Bloch, Chapter 3
     * 
     * @return a hash code value for the object.
     */
    public int hashCode(
    ) {
        int result = 17;
        
        long tmp;
        if (_HCI_HRC_holdCode != null) {
           result = 37 * result + _HCI_HRC_holdCode.hashCode();
        }
        if (_HCI_HRD_holdDescription != null) {
           result = 37 * result + _HCI_HRD_holdDescription.hashCode();
        }
        if (_HCI_DED_defaultExpiryDate != null) {
           result = 37 * result + _HCI_DED_defaultExpiryDate.hashCode();
        }
        
        return result;
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
     * Sets the value of field 'HCI_DED_defaultExpiryDate'.
     * 
     * @param HCI_DED_defaultExpiryDate the value of field
     * 'HCI_DED_defaultExpiryDate'.
     */
    public void setHCI_DED_defaultExpiryDate(
            final java.lang.String HCI_DED_defaultExpiryDate) {
        this._HCI_DED_defaultExpiryDate = HCI_DED_defaultExpiryDate;
    }

    /**
     * Sets the value of field 'HCI_HRC_holdCode'.
     * 
     * @param HCI_HRC_holdCode the value of field 'HCI_HRC_holdCode'
     */
    public void setHCI_HRC_holdCode(
            final java.lang.String HCI_HRC_holdCode) {
        this._HCI_HRC_holdCode = HCI_HRC_holdCode;
    }

    /**
     * Sets the value of field 'HCI_HRD_holdDescription'.
     * 
     * @param HCI_HRD_holdDescription the value of field
     * 'HCI_HRD_holdDescription'.
     */
    public void setHCI_HRD_holdDescription(
            final java.lang.String HCI_HRD_holdDescription) {
        this._HCI_HRD_holdDescription = HCI_HRD_holdDescription;
    }

    /**
     * Method unmarshalEQ_CMN_addMaintainHoldCodeHCX_SRV.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * bf.com.misys.equation.bankfusion.service.cmn.EQ_CMN_addMaintainHoldCodeHCX_SRV
     */
    public static bf.com.misys.equation.bankfusion.service.cmn.EQ_CMN_addMaintainHoldCodeHCX_SRV unmarshalEQ_CMN_addMaintainHoldCodeHCX_SRV(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (bf.com.misys.equation.bankfusion.service.cmn.EQ_CMN_addMaintainHoldCodeHCX_SRV) Unmarshaller.unmarshal(bf.com.misys.equation.bankfusion.service.cmn.EQ_CMN_addMaintainHoldCodeHCX_SRV.class, reader);
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