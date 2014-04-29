/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1.1</a>, using an XML
 * Schema.
 * $Id: PV_row.java 7214 2010-05-10 14:20:19Z goldsmc1 $
 */

package bf.com.misys.eq4.search.gfr70r;

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
public class PV_row extends bf.com.misys.eq4.search.gfr70r.Row 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _GFCUS_customerMnemonic.
     */
    private java.lang.String _GFCUS_customerMnemonic;

    /**
     * Field _GFCLC_customerLocation.
     */
    private java.lang.String _GFCLC_customerLocation;

    /**
     * Field _GFCUN_customerName.
     */
    private java.lang.String _GFCUN_customerName;

    /**
     * Field _GFCPN_customerNumber.
     */
    private java.lang.String _GFCPN_customerNumber;

    /**
     * Field _GFCUB_blockedStatus.
     */
    private java.lang.String _GFCUB_blockedStatus;

    /**
     * Field _GFGRP_group.
     */
    private java.lang.String _GFGRP_group;

    /**
     * Field _GFBRM_customerDefaultBranch.
     */
    private java.lang.String _GFBRM_customerDefaultBranch;

    /**
     * Field _GFAT1_firstAccountType.
     */
    private java.lang.String _GFAT1_firstAccountType;

    /**
     * Field _GFAT2_secondAccountType.
     */
    private java.lang.String _GFAT2_secondAccountType;

    /**
     * Field _GFCR1_firstTaxCode.
     */
    private java.lang.String _GFCR1_firstTaxCode;

    /**
     * Field _GFCR2_secondTaxCode.
     */
    private java.lang.String _GFCR2_secondTaxCode;

    /**
     * Field _GFCNL_localResidence.
     */
    private java.lang.String _GFCNL_localResidence;

    /**
     * Field _GFDAS_customerShortName.
     */
    private java.lang.String _GFDAS_customerShortName;

    /**
     * Field _GFCNP_parentCountry.
     */
    private java.lang.String _GFCNP_parentCountry;

    /**
     * Field _GFCNR_riskCountry.
     */
    private java.lang.String _GFCNR_riskCountry;

    /**
     * Field _GFCTP_customerType.
     */
    private java.lang.String _GFCTP_customerType;

    /**
     * Field _GFSAC_sundryAnalysisCode.
     */
    private java.lang.String _GFSAC_sundryAnalysisCode;

    /**
     * Field _GFACO_accountOfficer.
     */
    private java.lang.String _GFACO_accountOfficer;

    /**
     * Field _GFLNM_language.
     */
    private java.lang.String _GFLNM_language;

    /**
     * Field _GFCA2_analysisCode.
     */
    private java.lang.String _GFCA2_analysisCode;

    /**
     * Field _GFCNI_internalRiskCountry.
     */
    private java.lang.String _GFCNI_internalRiskCountry;

    /**
     * Field _GFPGR_principalGroup.
     */
    private java.lang.String _GFPGR_principalGroup;

    /**
     * Field _GFLCC_limitCurrencyCrm.
     */
    private java.lang.String _GFLCC_limitCurrencyCrm;

    /**
     * Field _GFLST_limitStructureCrm.
     */
    private java.lang.String _GFLST_limitStructureCrm;

    /**
     * Field _GFAT3_thirdAccountType.
     */
    private java.lang.String _GFAT3_thirdAccountType;

    /**
     * Field _GFAT4_fourthAccountType.
     */
    private java.lang.String _GFAT4_fourthAccountType;

    /**
     * Field _r70HG_branchCustomerAccountsecurityControl.
     */
    private java.lang.String _r70HG_branchCustomerAccountsecurityControl;


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
     * Returns the value of field 'GFACO_accountOfficer'.
     * 
     * @return the value of field 'GFACO_accountOfficer'.
     */
    public java.lang.String getGFACO_accountOfficer(
    ) {
        return this._GFACO_accountOfficer;
    }

    /**
     * Returns the value of field 'GFAT1_firstAccountType'.
     * 
     * @return the value of field 'GFAT1_firstAccountType'.
     */
    public java.lang.String getGFAT1_firstAccountType(
    ) {
        return this._GFAT1_firstAccountType;
    }

    /**
     * Returns the value of field 'GFAT2_secondAccountType'.
     * 
     * @return the value of field 'GFAT2_secondAccountType'.
     */
    public java.lang.String getGFAT2_secondAccountType(
    ) {
        return this._GFAT2_secondAccountType;
    }

    /**
     * Returns the value of field 'GFAT3_thirdAccountType'.
     * 
     * @return the value of field 'GFAT3_thirdAccountType'.
     */
    public java.lang.String getGFAT3_thirdAccountType(
    ) {
        return this._GFAT3_thirdAccountType;
    }

    /**
     * Returns the value of field 'GFAT4_fourthAccountType'.
     * 
     * @return the value of field 'GFAT4_fourthAccountType'.
     */
    public java.lang.String getGFAT4_fourthAccountType(
    ) {
        return this._GFAT4_fourthAccountType;
    }

    /**
     * Returns the value of field 'GFBRM_customerDefaultBranch'.
     * 
     * @return the value of field 'GFBRM_customerDefaultBranch'.
     */
    public java.lang.String getGFBRM_customerDefaultBranch(
    ) {
        return this._GFBRM_customerDefaultBranch;
    }

    /**
     * Returns the value of field 'GFCA2_analysisCode'.
     * 
     * @return the value of field 'GFCA2_analysisCode'.
     */
    public java.lang.String getGFCA2_analysisCode(
    ) {
        return this._GFCA2_analysisCode;
    }

    /**
     * Returns the value of field 'GFCLC_customerLocation'.
     * 
     * @return the value of field 'GFCLC_customerLocation'.
     */
    public java.lang.String getGFCLC_customerLocation(
    ) {
        return this._GFCLC_customerLocation;
    }

    /**
     * Returns the value of field 'GFCNI_internalRiskCountry'.
     * 
     * @return the value of field 'GFCNI_internalRiskCountry'.
     */
    public java.lang.String getGFCNI_internalRiskCountry(
    ) {
        return this._GFCNI_internalRiskCountry;
    }

    /**
     * Returns the value of field 'GFCNL_localResidence'.
     * 
     * @return the value of field 'GFCNL_localResidence'.
     */
    public java.lang.String getGFCNL_localResidence(
    ) {
        return this._GFCNL_localResidence;
    }

    /**
     * Returns the value of field 'GFCNP_parentCountry'.
     * 
     * @return the value of field 'GFCNP_parentCountry'.
     */
    public java.lang.String getGFCNP_parentCountry(
    ) {
        return this._GFCNP_parentCountry;
    }

    /**
     * Returns the value of field 'GFCNR_riskCountry'.
     * 
     * @return the value of field 'GFCNR_riskCountry'.
     */
    public java.lang.String getGFCNR_riskCountry(
    ) {
        return this._GFCNR_riskCountry;
    }

    /**
     * Returns the value of field 'GFCPN_customerNumber'.
     * 
     * @return the value of field 'GFCPN_customerNumber'.
     */
    public java.lang.String getGFCPN_customerNumber(
    ) {
        return this._GFCPN_customerNumber;
    }

    /**
     * Returns the value of field 'GFCR1_firstTaxCode'.
     * 
     * @return the value of field 'GFCR1_firstTaxCode'.
     */
    public java.lang.String getGFCR1_firstTaxCode(
    ) {
        return this._GFCR1_firstTaxCode;
    }

    /**
     * Returns the value of field 'GFCR2_secondTaxCode'.
     * 
     * @return the value of field 'GFCR2_secondTaxCode'.
     */
    public java.lang.String getGFCR2_secondTaxCode(
    ) {
        return this._GFCR2_secondTaxCode;
    }

    /**
     * Returns the value of field 'GFCTP_customerType'.
     * 
     * @return the value of field 'GFCTP_customerType'.
     */
    public java.lang.String getGFCTP_customerType(
    ) {
        return this._GFCTP_customerType;
    }

    /**
     * Returns the value of field 'GFCUB_blockedStatus'.
     * 
     * @return the value of field 'GFCUB_blockedStatus'.
     */
    public java.lang.String getGFCUB_blockedStatus(
    ) {
        return this._GFCUB_blockedStatus;
    }

    /**
     * Returns the value of field 'GFCUN_customerName'.
     * 
     * @return the value of field 'GFCUN_customerName'.
     */
    public java.lang.String getGFCUN_customerName(
    ) {
        return this._GFCUN_customerName;
    }

    /**
     * Returns the value of field 'GFCUS_customerMnemonic'.
     * 
     * @return the value of field 'GFCUS_customerMnemonic'.
     */
    public java.lang.String getGFCUS_customerMnemonic(
    ) {
        return this._GFCUS_customerMnemonic;
    }

    /**
     * Returns the value of field 'GFDAS_customerShortName'.
     * 
     * @return the value of field 'GFDAS_customerShortName'.
     */
    public java.lang.String getGFDAS_customerShortName(
    ) {
        return this._GFDAS_customerShortName;
    }

    /**
     * Returns the value of field 'GFGRP_group'.
     * 
     * @return the value of field 'GFGRP_group'.
     */
    public java.lang.String getGFGRP_group(
    ) {
        return this._GFGRP_group;
    }

    /**
     * Returns the value of field 'GFLCC_limitCurrencyCrm'.
     * 
     * @return the value of field 'GFLCC_limitCurrencyCrm'.
     */
    public java.lang.String getGFLCC_limitCurrencyCrm(
    ) {
        return this._GFLCC_limitCurrencyCrm;
    }

    /**
     * Returns the value of field 'GFLNM_language'.
     * 
     * @return the value of field 'GFLNM_language'.
     */
    public java.lang.String getGFLNM_language(
    ) {
        return this._GFLNM_language;
    }

    /**
     * Returns the value of field 'GFLST_limitStructureCrm'.
     * 
     * @return the value of field 'GFLST_limitStructureCrm'.
     */
    public java.lang.String getGFLST_limitStructureCrm(
    ) {
        return this._GFLST_limitStructureCrm;
    }

    /**
     * Returns the value of field 'GFPGR_principalGroup'.
     * 
     * @return the value of field 'GFPGR_principalGroup'.
     */
    public java.lang.String getGFPGR_principalGroup(
    ) {
        return this._GFPGR_principalGroup;
    }

    /**
     * Returns the value of field 'GFSAC_sundryAnalysisCode'.
     * 
     * @return the value of field 'GFSAC_sundryAnalysisCode'.
     */
    public java.lang.String getGFSAC_sundryAnalysisCode(
    ) {
        return this._GFSAC_sundryAnalysisCode;
    }

    /**
     * Returns the value of field
     * 'r70HG_branchCustomerAccountsecurityControl'.
     * 
     * @return the value of field
     * 'R70HG_branchCustomerAccountsecurityControl'.
     */
    public java.lang.String getR70HG_branchCustomerAccountsecurityControl(
    ) {
        return this._r70HG_branchCustomerAccountsecurityControl;
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
     * Sets the value of field 'GFACO_accountOfficer'.
     * 
     * @param GFACO_accountOfficer the value of field
     * 'GFACO_accountOfficer'.
     */
    public void setGFACO_accountOfficer(
            final java.lang.String GFACO_accountOfficer) {
        this._GFACO_accountOfficer = GFACO_accountOfficer;
    }

    /**
     * Sets the value of field 'GFAT1_firstAccountType'.
     * 
     * @param GFAT1_firstAccountType the value of field
     * 'GFAT1_firstAccountType'.
     */
    public void setGFAT1_firstAccountType(
            final java.lang.String GFAT1_firstAccountType) {
        this._GFAT1_firstAccountType = GFAT1_firstAccountType;
    }

    /**
     * Sets the value of field 'GFAT2_secondAccountType'.
     * 
     * @param GFAT2_secondAccountType the value of field
     * 'GFAT2_secondAccountType'.
     */
    public void setGFAT2_secondAccountType(
            final java.lang.String GFAT2_secondAccountType) {
        this._GFAT2_secondAccountType = GFAT2_secondAccountType;
    }

    /**
     * Sets the value of field 'GFAT3_thirdAccountType'.
     * 
     * @param GFAT3_thirdAccountType the value of field
     * 'GFAT3_thirdAccountType'.
     */
    public void setGFAT3_thirdAccountType(
            final java.lang.String GFAT3_thirdAccountType) {
        this._GFAT3_thirdAccountType = GFAT3_thirdAccountType;
    }

    /**
     * Sets the value of field 'GFAT4_fourthAccountType'.
     * 
     * @param GFAT4_fourthAccountType the value of field
     * 'GFAT4_fourthAccountType'.
     */
    public void setGFAT4_fourthAccountType(
            final java.lang.String GFAT4_fourthAccountType) {
        this._GFAT4_fourthAccountType = GFAT4_fourthAccountType;
    }

    /**
     * Sets the value of field 'GFBRM_customerDefaultBranch'.
     * 
     * @param GFBRM_customerDefaultBranch the value of field
     * 'GFBRM_customerDefaultBranch'.
     */
    public void setGFBRM_customerDefaultBranch(
            final java.lang.String GFBRM_customerDefaultBranch) {
        this._GFBRM_customerDefaultBranch = GFBRM_customerDefaultBranch;
    }

    /**
     * Sets the value of field 'GFCA2_analysisCode'.
     * 
     * @param GFCA2_analysisCode the value of field
     * 'GFCA2_analysisCode'.
     */
    public void setGFCA2_analysisCode(
            final java.lang.String GFCA2_analysisCode) {
        this._GFCA2_analysisCode = GFCA2_analysisCode;
    }

    /**
     * Sets the value of field 'GFCLC_customerLocation'.
     * 
     * @param GFCLC_customerLocation the value of field
     * 'GFCLC_customerLocation'.
     */
    public void setGFCLC_customerLocation(
            final java.lang.String GFCLC_customerLocation) {
        this._GFCLC_customerLocation = GFCLC_customerLocation;
    }

    /**
     * Sets the value of field 'GFCNI_internalRiskCountry'.
     * 
     * @param GFCNI_internalRiskCountry the value of field
     * 'GFCNI_internalRiskCountry'.
     */
    public void setGFCNI_internalRiskCountry(
            final java.lang.String GFCNI_internalRiskCountry) {
        this._GFCNI_internalRiskCountry = GFCNI_internalRiskCountry;
    }

    /**
     * Sets the value of field 'GFCNL_localResidence'.
     * 
     * @param GFCNL_localResidence the value of field
     * 'GFCNL_localResidence'.
     */
    public void setGFCNL_localResidence(
            final java.lang.String GFCNL_localResidence) {
        this._GFCNL_localResidence = GFCNL_localResidence;
    }

    /**
     * Sets the value of field 'GFCNP_parentCountry'.
     * 
     * @param GFCNP_parentCountry the value of field
     * 'GFCNP_parentCountry'.
     */
    public void setGFCNP_parentCountry(
            final java.lang.String GFCNP_parentCountry) {
        this._GFCNP_parentCountry = GFCNP_parentCountry;
    }

    /**
     * Sets the value of field 'GFCNR_riskCountry'.
     * 
     * @param GFCNR_riskCountry the value of field
     * 'GFCNR_riskCountry'.
     */
    public void setGFCNR_riskCountry(
            final java.lang.String GFCNR_riskCountry) {
        this._GFCNR_riskCountry = GFCNR_riskCountry;
    }

    /**
     * Sets the value of field 'GFCPN_customerNumber'.
     * 
     * @param GFCPN_customerNumber the value of field
     * 'GFCPN_customerNumber'.
     */
    public void setGFCPN_customerNumber(
            final java.lang.String GFCPN_customerNumber) {
        this._GFCPN_customerNumber = GFCPN_customerNumber;
    }

    /**
     * Sets the value of field 'GFCR1_firstTaxCode'.
     * 
     * @param GFCR1_firstTaxCode the value of field
     * 'GFCR1_firstTaxCode'.
     */
    public void setGFCR1_firstTaxCode(
            final java.lang.String GFCR1_firstTaxCode) {
        this._GFCR1_firstTaxCode = GFCR1_firstTaxCode;
    }

    /**
     * Sets the value of field 'GFCR2_secondTaxCode'.
     * 
     * @param GFCR2_secondTaxCode the value of field
     * 'GFCR2_secondTaxCode'.
     */
    public void setGFCR2_secondTaxCode(
            final java.lang.String GFCR2_secondTaxCode) {
        this._GFCR2_secondTaxCode = GFCR2_secondTaxCode;
    }

    /**
     * Sets the value of field 'GFCTP_customerType'.
     * 
     * @param GFCTP_customerType the value of field
     * 'GFCTP_customerType'.
     */
    public void setGFCTP_customerType(
            final java.lang.String GFCTP_customerType) {
        this._GFCTP_customerType = GFCTP_customerType;
    }

    /**
     * Sets the value of field 'GFCUB_blockedStatus'.
     * 
     * @param GFCUB_blockedStatus the value of field
     * 'GFCUB_blockedStatus'.
     */
    public void setGFCUB_blockedStatus(
            final java.lang.String GFCUB_blockedStatus) {
        this._GFCUB_blockedStatus = GFCUB_blockedStatus;
    }

    /**
     * Sets the value of field 'GFCUN_customerName'.
     * 
     * @param GFCUN_customerName the value of field
     * 'GFCUN_customerName'.
     */
    public void setGFCUN_customerName(
            final java.lang.String GFCUN_customerName) {
        this._GFCUN_customerName = GFCUN_customerName;
    }

    /**
     * Sets the value of field 'GFCUS_customerMnemonic'.
     * 
     * @param GFCUS_customerMnemonic the value of field
     * 'GFCUS_customerMnemonic'.
     */
    public void setGFCUS_customerMnemonic(
            final java.lang.String GFCUS_customerMnemonic) {
        this._GFCUS_customerMnemonic = GFCUS_customerMnemonic;
    }

    /**
     * Sets the value of field 'GFDAS_customerShortName'.
     * 
     * @param GFDAS_customerShortName the value of field
     * 'GFDAS_customerShortName'.
     */
    public void setGFDAS_customerShortName(
            final java.lang.String GFDAS_customerShortName) {
        this._GFDAS_customerShortName = GFDAS_customerShortName;
    }

    /**
     * Sets the value of field 'GFGRP_group'.
     * 
     * @param GFGRP_group the value of field 'GFGRP_group'.
     */
    public void setGFGRP_group(
            final java.lang.String GFGRP_group) {
        this._GFGRP_group = GFGRP_group;
    }

    /**
     * Sets the value of field 'GFLCC_limitCurrencyCrm'.
     * 
     * @param GFLCC_limitCurrencyCrm the value of field
     * 'GFLCC_limitCurrencyCrm'.
     */
    public void setGFLCC_limitCurrencyCrm(
            final java.lang.String GFLCC_limitCurrencyCrm) {
        this._GFLCC_limitCurrencyCrm = GFLCC_limitCurrencyCrm;
    }

    /**
     * Sets the value of field 'GFLNM_language'.
     * 
     * @param GFLNM_language the value of field 'GFLNM_language'.
     */
    public void setGFLNM_language(
            final java.lang.String GFLNM_language) {
        this._GFLNM_language = GFLNM_language;
    }

    /**
     * Sets the value of field 'GFLST_limitStructureCrm'.
     * 
     * @param GFLST_limitStructureCrm the value of field
     * 'GFLST_limitStructureCrm'.
     */
    public void setGFLST_limitStructureCrm(
            final java.lang.String GFLST_limitStructureCrm) {
        this._GFLST_limitStructureCrm = GFLST_limitStructureCrm;
    }

    /**
     * Sets the value of field 'GFPGR_principalGroup'.
     * 
     * @param GFPGR_principalGroup the value of field
     * 'GFPGR_principalGroup'.
     */
    public void setGFPGR_principalGroup(
            final java.lang.String GFPGR_principalGroup) {
        this._GFPGR_principalGroup = GFPGR_principalGroup;
    }

    /**
     * Sets the value of field 'GFSAC_sundryAnalysisCode'.
     * 
     * @param GFSAC_sundryAnalysisCode the value of field
     * 'GFSAC_sundryAnalysisCode'.
     */
    public void setGFSAC_sundryAnalysisCode(
            final java.lang.String GFSAC_sundryAnalysisCode) {
        this._GFSAC_sundryAnalysisCode = GFSAC_sundryAnalysisCode;
    }

    /**
     * Sets the value of field
     * 'r70HG_branchCustomerAccountsecurityControl'.
     * 
     * @param r70HG_branchCustomerAccountsecurityControl the value
     * of field 'r70HG_branchCustomerAccountsecurityControl'.
     */
    public void setR70HG_branchCustomerAccountsecurityControl(
            final java.lang.String r70HG_branchCustomerAccountsecurityControl) {
        this._r70HG_branchCustomerAccountsecurityControl = r70HG_branchCustomerAccountsecurityControl;
    }

    /**
     * Method unmarshalPV_row.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled bf.com.misys.eq4.search.gfr70r.Row
     */
    public static bf.com.misys.eq4.search.gfr70r.Row unmarshalPV_row(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (bf.com.misys.eq4.search.gfr70r.Row) Unmarshaller.unmarshal(bf.com.misys.eq4.search.gfr70r.PV_row.class, reader);
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
