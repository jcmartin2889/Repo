/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1.1</a>, using an XML
 * Schema.
 * $Id: PV_row.java 7272 2010-05-13 15:40:49Z goldsmc1 $
 */

package bf.com.misys.eq4.search.osr34r;

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
public class PV_row extends bf.com.misys.eq4.search.osr34r.Row 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _DEALREF_fullDealRef.
     */
    private java.lang.String _DEALREF_fullDealRef;

    /**
     * Field _BRNM_branchMnemonic.
     */
    private java.lang.String _BRNM_branchMnemonic;

    /**
     * Field _DLP_dealPrefix.
     */
    private java.lang.String _DLP_dealPrefix;

    /**
     * Field _DLR_dealReference.
     */
    private java.lang.String _DLR_dealReference;

    /**
     * Field _CUS_customerName.
     */
    private java.lang.String _CUS_customerName;

    /**
     * Field _CLC_customerLocation.
     */
    private java.lang.String _CLC_customerLocation;

    /**
     * Field _CCY_currency.
     */
    private java.lang.String _CCY_currency;

    /**
     * Field _DLA_dealAmountPurchaseAmount.
     */
    private java.lang.String _DLA_dealAmountPurchaseAmount;

    /**
     * Field _ITRT_interestRate.
     */
    private java.lang.String _ITRT_interestRate;

    /**
     * Field _MDT_maturityDatePurchaseDate.
     */
    private java.lang.String _MDT_maturityDatePurchaseDate;

    /**
     * Field _OPT_optionDealFlag.
     */
    private java.lang.String _OPT_optionDealFlag;

    /**
     * Field _BDT_basicDealType.
     */
    private java.lang.String _BDT_basicDealType;

    /**
     * Field _DLAZ_editedDealAmtPurchaseAmt.
     */
    private java.lang.String _DLAZ_editedDealAmtPurchaseAmt;

    /**
     * Field _ITRZ_editedInterestRate.
     */
    private java.lang.String _ITRZ_editedInterestRate;

    /**
     * Field _MDTZ_editedMaturityDatePurchase.
     */
    private java.lang.String _MDTZ_editedMaturityDatePurchase;

    /**
     * Field _APP_applicationCode.
     */
    private java.lang.String _APP_applicationCode;

    /**
     * Field _ARC_archivedFlag.
     */
    private java.lang.String _ARC_archivedFlag;

    /**
     * Field _CANR_canr.
     */
    private java.lang.String _CANR_canr;

    /**
     * Field _FCT_functionTypeIfServerInput.
     */
    private java.lang.String _FCT_functionTypeIfServerInput;

    /**
     * Field _DLPD_fxSwapForwardDealType.
     */
    private java.lang.String _DLPD_fxSwapForwardDealType;


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
     * Returns the value of field 'APP_applicationCode'.
     * 
     * @return the value of field 'APP_applicationCode'.
     */
    public java.lang.String getAPP_applicationCode(
    ) {
        return this._APP_applicationCode;
    }

    /**
     * Returns the value of field 'ARC_archivedFlag'.
     * 
     * @return the value of field 'ARC_archivedFlag'.
     */
    public java.lang.String getARC_archivedFlag(
    ) {
        return this._ARC_archivedFlag;
    }

    /**
     * Returns the value of field 'BDT_basicDealType'.
     * 
     * @return the value of field 'BDT_basicDealType'.
     */
    public java.lang.String getBDT_basicDealType(
    ) {
        return this._BDT_basicDealType;
    }

    /**
     * Returns the value of field 'BRNM_branchMnemonic'.
     * 
     * @return the value of field 'BRNM_branchMnemonic'.
     */
    public java.lang.String getBRNM_branchMnemonic(
    ) {
        return this._BRNM_branchMnemonic;
    }

    /**
     * Returns the value of field 'CANR_canr'.
     * 
     * @return the value of field 'CANR_canr'.
     */
    public java.lang.String getCANR_canr(
    ) {
        return this._CANR_canr;
    }

    /**
     * Returns the value of field 'CCY_currency'.
     * 
     * @return the value of field 'CCY_currency'.
     */
    public java.lang.String getCCY_currency(
    ) {
        return this._CCY_currency;
    }

    /**
     * Returns the value of field 'CLC_customerLocation'.
     * 
     * @return the value of field 'CLC_customerLocation'.
     */
    public java.lang.String getCLC_customerLocation(
    ) {
        return this._CLC_customerLocation;
    }

    /**
     * Returns the value of field 'CUS_customerName'.
     * 
     * @return the value of field 'CUS_customerName'.
     */
    public java.lang.String getCUS_customerName(
    ) {
        return this._CUS_customerName;
    }

    /**
     * Returns the value of field 'DEALREF_fullDealRef'.
     * 
     * @return the value of field 'DEALREF_fullDealRef'.
     */
    public java.lang.String getDEALREF_fullDealRef(
    ) {
        return this._DEALREF_fullDealRef;
    }

    /**
     * Returns the value of field 'DLAZ_editedDealAmtPurchaseAmt'.
     * 
     * @return the value of field 'DLAZ_editedDealAmtPurchaseAmt'.
     */
    public java.lang.String getDLAZ_editedDealAmtPurchaseAmt(
    ) {
        return this._DLAZ_editedDealAmtPurchaseAmt;
    }

    /**
     * Returns the value of field 'DLA_dealAmountPurchaseAmount'.
     * 
     * @return the value of field 'DLA_dealAmountPurchaseAmount'.
     */
    public java.lang.String getDLA_dealAmountPurchaseAmount(
    ) {
        return this._DLA_dealAmountPurchaseAmount;
    }

    /**
     * Returns the value of field 'DLPD_fxSwapForwardDealType'.
     * 
     * @return the value of field 'DLPD_fxSwapForwardDealType'.
     */
    public java.lang.String getDLPD_fxSwapForwardDealType(
    ) {
        return this._DLPD_fxSwapForwardDealType;
    }

    /**
     * Returns the value of field 'DLP_dealPrefix'.
     * 
     * @return the value of field 'DLP_dealPrefix'.
     */
    public java.lang.String getDLP_dealPrefix(
    ) {
        return this._DLP_dealPrefix;
    }

    /**
     * Returns the value of field 'DLR_dealReference'.
     * 
     * @return the value of field 'DLR_dealReference'.
     */
    public java.lang.String getDLR_dealReference(
    ) {
        return this._DLR_dealReference;
    }

    /**
     * Returns the value of field 'FCT_functionTypeIfServerInput'.
     * 
     * @return the value of field 'FCT_functionTypeIfServerInput'.
     */
    public java.lang.String getFCT_functionTypeIfServerInput(
    ) {
        return this._FCT_functionTypeIfServerInput;
    }

    /**
     * Returns the value of field 'ITRT_interestRate'.
     * 
     * @return the value of field 'ITRT_interestRate'.
     */
    public java.lang.String getITRT_interestRate(
    ) {
        return this._ITRT_interestRate;
    }

    /**
     * Returns the value of field 'ITRZ_editedInterestRate'.
     * 
     * @return the value of field 'ITRZ_editedInterestRate'.
     */
    public java.lang.String getITRZ_editedInterestRate(
    ) {
        return this._ITRZ_editedInterestRate;
    }

    /**
     * Returns the value of field
     * 'MDTZ_editedMaturityDatePurchase'.
     * 
     * @return the value of field 'MDTZ_editedMaturityDatePurchase'.
     */
    public java.lang.String getMDTZ_editedMaturityDatePurchase(
    ) {
        return this._MDTZ_editedMaturityDatePurchase;
    }

    /**
     * Returns the value of field 'MDT_maturityDatePurchaseDate'.
     * 
     * @return the value of field 'MDT_maturityDatePurchaseDate'.
     */
    public java.lang.String getMDT_maturityDatePurchaseDate(
    ) {
        return this._MDT_maturityDatePurchaseDate;
    }

    /**
     * Returns the value of field 'OPT_optionDealFlag'.
     * 
     * @return the value of field 'OPT_optionDealFlag'.
     */
    public java.lang.String getOPT_optionDealFlag(
    ) {
        return this._OPT_optionDealFlag;
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
     * Sets the value of field 'APP_applicationCode'.
     * 
     * @param APP_applicationCode the value of field
     * 'APP_applicationCode'.
     */
    public void setAPP_applicationCode(
            final java.lang.String APP_applicationCode) {
        this._APP_applicationCode = APP_applicationCode;
    }

    /**
     * Sets the value of field 'ARC_archivedFlag'.
     * 
     * @param ARC_archivedFlag the value of field 'ARC_archivedFlag'
     */
    public void setARC_archivedFlag(
            final java.lang.String ARC_archivedFlag) {
        this._ARC_archivedFlag = ARC_archivedFlag;
    }

    /**
     * Sets the value of field 'BDT_basicDealType'.
     * 
     * @param BDT_basicDealType the value of field
     * 'BDT_basicDealType'.
     */
    public void setBDT_basicDealType(
            final java.lang.String BDT_basicDealType) {
        this._BDT_basicDealType = BDT_basicDealType;
    }

    /**
     * Sets the value of field 'BRNM_branchMnemonic'.
     * 
     * @param BRNM_branchMnemonic the value of field
     * 'BRNM_branchMnemonic'.
     */
    public void setBRNM_branchMnemonic(
            final java.lang.String BRNM_branchMnemonic) {
        this._BRNM_branchMnemonic = BRNM_branchMnemonic;
    }

    /**
     * Sets the value of field 'CANR_canr'.
     * 
     * @param CANR_canr the value of field 'CANR_canr'.
     */
    public void setCANR_canr(
            final java.lang.String CANR_canr) {
        this._CANR_canr = CANR_canr;
    }

    /**
     * Sets the value of field 'CCY_currency'.
     * 
     * @param CCY_currency the value of field 'CCY_currency'.
     */
    public void setCCY_currency(
            final java.lang.String CCY_currency) {
        this._CCY_currency = CCY_currency;
    }

    /**
     * Sets the value of field 'CLC_customerLocation'.
     * 
     * @param CLC_customerLocation the value of field
     * 'CLC_customerLocation'.
     */
    public void setCLC_customerLocation(
            final java.lang.String CLC_customerLocation) {
        this._CLC_customerLocation = CLC_customerLocation;
    }

    /**
     * Sets the value of field 'CUS_customerName'.
     * 
     * @param CUS_customerName the value of field 'CUS_customerName'
     */
    public void setCUS_customerName(
            final java.lang.String CUS_customerName) {
        this._CUS_customerName = CUS_customerName;
    }

    /**
     * Sets the value of field 'DEALREF_fullDealRef'.
     * 
     * @param DEALREF_fullDealRef the value of field
     * 'DEALREF_fullDealRef'.
     */
    public void setDEALREF_fullDealRef(
            final java.lang.String DEALREF_fullDealRef) {
        this._DEALREF_fullDealRef = DEALREF_fullDealRef;
    }

    /**
     * Sets the value of field 'DLAZ_editedDealAmtPurchaseAmt'.
     * 
     * @param DLAZ_editedDealAmtPurchaseAmt the value of field
     * 'DLAZ_editedDealAmtPurchaseAmt'.
     */
    public void setDLAZ_editedDealAmtPurchaseAmt(
            final java.lang.String DLAZ_editedDealAmtPurchaseAmt) {
        this._DLAZ_editedDealAmtPurchaseAmt = DLAZ_editedDealAmtPurchaseAmt;
    }

    /**
     * Sets the value of field 'DLA_dealAmountPurchaseAmount'.
     * 
     * @param DLA_dealAmountPurchaseAmount the value of field
     * 'DLA_dealAmountPurchaseAmount'.
     */
    public void setDLA_dealAmountPurchaseAmount(
            final java.lang.String DLA_dealAmountPurchaseAmount) {
        this._DLA_dealAmountPurchaseAmount = DLA_dealAmountPurchaseAmount;
    }

    /**
     * Sets the value of field 'DLPD_fxSwapForwardDealType'.
     * 
     * @param DLPD_fxSwapForwardDealType the value of field
     * 'DLPD_fxSwapForwardDealType'.
     */
    public void setDLPD_fxSwapForwardDealType(
            final java.lang.String DLPD_fxSwapForwardDealType) {
        this._DLPD_fxSwapForwardDealType = DLPD_fxSwapForwardDealType;
    }

    /**
     * Sets the value of field 'DLP_dealPrefix'.
     * 
     * @param DLP_dealPrefix the value of field 'DLP_dealPrefix'.
     */
    public void setDLP_dealPrefix(
            final java.lang.String DLP_dealPrefix) {
        this._DLP_dealPrefix = DLP_dealPrefix;
    }

    /**
     * Sets the value of field 'DLR_dealReference'.
     * 
     * @param DLR_dealReference the value of field
     * 'DLR_dealReference'.
     */
    public void setDLR_dealReference(
            final java.lang.String DLR_dealReference) {
        this._DLR_dealReference = DLR_dealReference;
    }

    /**
     * Sets the value of field 'FCT_functionTypeIfServerInput'.
     * 
     * @param FCT_functionTypeIfServerInput the value of field
     * 'FCT_functionTypeIfServerInput'.
     */
    public void setFCT_functionTypeIfServerInput(
            final java.lang.String FCT_functionTypeIfServerInput) {
        this._FCT_functionTypeIfServerInput = FCT_functionTypeIfServerInput;
    }

    /**
     * Sets the value of field 'ITRT_interestRate'.
     * 
     * @param ITRT_interestRate the value of field
     * 'ITRT_interestRate'.
     */
    public void setITRT_interestRate(
            final java.lang.String ITRT_interestRate) {
        this._ITRT_interestRate = ITRT_interestRate;
    }

    /**
     * Sets the value of field 'ITRZ_editedInterestRate'.
     * 
     * @param ITRZ_editedInterestRate the value of field
     * 'ITRZ_editedInterestRate'.
     */
    public void setITRZ_editedInterestRate(
            final java.lang.String ITRZ_editedInterestRate) {
        this._ITRZ_editedInterestRate = ITRZ_editedInterestRate;
    }

    /**
     * Sets the value of field 'MDTZ_editedMaturityDatePurchase'.
     * 
     * @param MDTZ_editedMaturityDatePurchase the value of field
     * 'MDTZ_editedMaturityDatePurchase'.
     */
    public void setMDTZ_editedMaturityDatePurchase(
            final java.lang.String MDTZ_editedMaturityDatePurchase) {
        this._MDTZ_editedMaturityDatePurchase = MDTZ_editedMaturityDatePurchase;
    }

    /**
     * Sets the value of field 'MDT_maturityDatePurchaseDate'.
     * 
     * @param MDT_maturityDatePurchaseDate the value of field
     * 'MDT_maturityDatePurchaseDate'.
     */
    public void setMDT_maturityDatePurchaseDate(
            final java.lang.String MDT_maturityDatePurchaseDate) {
        this._MDT_maturityDatePurchaseDate = MDT_maturityDatePurchaseDate;
    }

    /**
     * Sets the value of field 'OPT_optionDealFlag'.
     * 
     * @param OPT_optionDealFlag the value of field
     * 'OPT_optionDealFlag'.
     */
    public void setOPT_optionDealFlag(
            final java.lang.String OPT_optionDealFlag) {
        this._OPT_optionDealFlag = OPT_optionDealFlag;
    }

    /**
     * Method unmarshalPV_row.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled bf.com.misys.eq4.search.osr34r.Row
     */
    public static bf.com.misys.eq4.search.osr34r.Row unmarshalPV_row(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (bf.com.misys.eq4.search.osr34r.Row) Unmarshaller.unmarshal(bf.com.misys.eq4.search.osr34r.PV_row.class, reader);
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
