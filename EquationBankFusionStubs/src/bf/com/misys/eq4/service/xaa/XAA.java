/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1.1</a>, using an XML
 * Schema.
 * $Id: XAA.java 7214 2010-05-10 14:20:19Z goldsmc1 $
 */

package bf.com.misys.eq4.service.xaa;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class XAA.
 * 
 * @version $Revision$ $Date$
 */
public class XAA implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _HH_HRC_holdCode.
     */
    private java.lang.String _HH_HRC_holdCode;

    /**
     * Field _HH_HRD_holdDescription.
     */
    private java.lang.String _HH_HRD_holdDescription;

    /**
     * Field _HH_DED_defaultExpiryDate.
     */
    private java.lang.String _HH_DED_defaultExpiryDate;

    /**
     * Field _AA_HZAB_accountBranch.
     */
    private java.lang.String _AA_HZAB_accountBranch;

    /**
     * Field _AA_HZAN_basicPartOfAccountNumber.
     */
    private java.lang.String _AA_HZAN_basicPartOfAccountNumber;

    /**
     * Field _AA_HZAS_accountSuffix.
     */
    private java.lang.String _AA_HZAS_accountSuffix;

    /**
     * Field _AA_HZSHN_accountShortName.
     */
    private java.lang.String _AA_HZSHN_accountShortName;

    /**
     * Field _AA_HZSHNA_arabicShortName.
     */
    private java.lang.String _AA_HZSHNA_arabicShortName;

    /**
     * Field _AA_HZCUS_customerMnemonic.
     */
    private java.lang.String _AA_HZCUS_customerMnemonic;

    /**
     * Field _AA_HZCLC_customerLocation.
     */
    private java.lang.String _AA_HZCLC_customerLocation;

    /**
     * Field _AA_HZCUN_customerFullName.
     */
    private java.lang.String _AA_HZCUN_customerFullName;

    /**
     * Field _AA_HZCUNA_arabicCustomerFullName.
     */
    private java.lang.String _AA_HZCUNA_arabicCustomerFullName;

    /**
     * Field _AA_HZACT_accountType.
     */
    private java.lang.String _AA_HZACT_accountType;

    /**
     * Field _AA_HZAPP_owningApplicationCode.
     */
    private java.lang.String _AA_HZAPP_owningApplicationCode;

    /**
     * Field _AA_HZACS_accountStructure.
     */
    private java.lang.String _AA_HZACS_accountStructure;

    /**
     * Field _AA_HZCCY_currencyMnemonic.
     */
    private java.lang.String _AA_HZCCY_currencyMnemonic;

    /**
     * Field _AA_HZCED_currencyEditField.
     */
    private java.lang.String _AA_HZCED_currencyEditField;

    /**
     * Field _AA_HZAI47_internalNonCustomerAccount.
     */
    private java.lang.String _AA_HZAI47_internalNonCustomerAccount;

    /**
     * Field _AA_HZAI14_deceasedOrLiquidated.
     */
    private java.lang.String _AA_HZAI14_deceasedOrLiquidated;

    /**
     * Field _AA_HZAI17_accountStatusBlocked.
     */
    private java.lang.String _AA_HZAI17_accountStatusBlocked;

    /**
     * Field _AA_HZAI20_accountStatusInactive.
     */
    private java.lang.String _AA_HZAI20_accountStatusInactive;

    /**
     * Field _AA_HZAI30_accountClosing.
     */
    private java.lang.String _AA_HZAI30_accountClosing;

    /**
     * Field _AA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay.
     */
    private java.lang.String _AA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay;

    /**
     * Field _AA_HZCTP_customerType.
     */
    private java.lang.String _AA_HZCTP_customerType;

    /**
     * Field _AA_HZACD_analysisCode.
     */
    private java.lang.String _AA_HZACD_analysisCode;

    /**
     * Field _AA_HZSAC_sundryAnalysisCode.
     */
    private java.lang.String _AA_HZSAC_sundryAnalysisCode;

    /**
     * Field _AA_HZNANC_numericAnalysisCode.
     */
    private java.lang.String _AA_HZNANC_numericAnalysisCode;

    /**
     * Field _AA_HZCNAL_residenceCountry.
     */
    private java.lang.String _AA_HZCNAL_residenceCountry;

    /**
     * Field _AA_HZCNAR_riskCountry.
     */
    private java.lang.String _AA_HZCNAR_riskCountry;

    /**
     * Field _AA_HZCNAP_parentCountry.
     */
    private java.lang.String _AA_HZCNAP_parentCountry;

    /**
     * Field _AA_HZSFC_statementFrequencyCode.
     */
    private java.lang.String _AA_HZSFC_statementFrequencyCode;

    /**
     * Field _AA_HZBALS_balanceOnThePreviousStatement.
     */
    private java.lang.String _AA_HZBALS_balanceOnThePreviousStatement;

    /**
     * Field _AA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement.
     */
    private java.lang.String _AA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement;

    /**
     * Field _AA_HZSTML_lastStatementDate.
     */
    private java.lang.String _AA_HZSTML_lastStatementDate;

    /**
     * Field _AA_HZSTMN_nextStatementDate.
     */
    private java.lang.String _AA_HZSTMN_nextStatementDate;

    /**
     * Field _AA_HZSTNL_lastStatementNumber.
     */
    private java.lang.String _AA_HZSTNL_lastStatementNumber;

    /**
     * Field _AA_HZRETP_retentionPeriod00None011MthSysDflt.
     */
    private java.lang.String _AA_HZRETP_retentionPeriod00None011MthSysDflt;

    /**
     * Field _AA_HZNS3_numberOfReferenceCharactersForReconciliation.
     */
    private java.lang.String _AA_HZNS3_numberOfReferenceCharactersForReconciliation;

    /**
     * Field _AA_HZOAD_dateAccountOpened.
     */
    private java.lang.String _AA_HZOAD_dateAccountOpened;

    /**
     * Field _AA_HZDLE_dateOfLastCustomerGeneratedEntry.
     */
    private java.lang.String _AA_HZDLE_dateOfLastCustomerGeneratedEntry;

    /**
     * Field _AA_HZCAD_dateAccountClosed.
     */
    private java.lang.String _AA_HZCAD_dateAccountClosed;

    /**
     * Field _AA_HZDLM_dateLastMaintained.
     */
    private java.lang.String _AA_HZDLM_dateLastMaintained;

    /**
     * Field _AA_HZP1R_p1RatingForAccount.
     */
    private java.lang.String _AA_HZP1R_p1RatingForAccount;

    /**
     * Field _AA_HZP2R_p2RatingForAccount.
     */
    private java.lang.String _AA_HZP2R_p2RatingForAccount;

    /**
     * Field _AA_HZP3R_p3RatingForAccount.
     */
    private java.lang.String _AA_HZP3R_p3RatingForAccount;

    /**
     * Field _AA_HZP4R_p4RatingForAccount.
     */
    private java.lang.String _AA_HZP4R_p4RatingForAccount;

    /**
     * Field _AA_HZP5R_p5RatingForAccount.
     */
    private java.lang.String _AA_HZP5R_p5RatingForAccount;

    /**
     * Field _AA_HZC1R_c1RatingForAccount.
     */
    private java.lang.String _AA_HZC1R_c1RatingForAccount;

    /**
     * Field _AA_HZC2R_c2RatingForAccount.
     */
    private java.lang.String _AA_HZC2R_c2RatingForAccount;

    /**
     * Field _AA_HZC3R_c3RatingForAccount.
     */
    private java.lang.String _AA_HZC3R_c3RatingForAccount;

    /**
     * Field _AA_HZC4R_c4RatingForAccount.
     */
    private java.lang.String _AA_HZC4R_c4RatingForAccount;

    /**
     * Field _AA_HZC5R_c5RatingForAccount.
     */
    private java.lang.String _AA_HZC5R_c5RatingForAccount;

    /**
     * Field _AA_HZACO_accountOfficer.
     */
    private java.lang.String _AA_HZACO_accountOfficer;

    /**
     * Field _AA_HZLNM_languageCode.
     */
    private java.lang.String _AA_HZLNM_languageCode;

    /**
     * Field _AA_HZAI33_printChargesStatement.
     */
    private java.lang.String _AA_HZAI33_printChargesStatement;

    /**
     * Field _AA_HZCSFC_chargesStatementFrequency.
     */
    private java.lang.String _AA_HZCSFC_chargesStatementFrequency;

    /**
     * Field _AA_HZCSTL_lastChargesStatementDate.
     */
    private java.lang.String _AA_HZCSTL_lastChargesStatementDate;

    /**
     * Field _AA_HZCSTN_nextChargesStatementDate.
     */
    private java.lang.String _AA_HZCSTN_nextChargesStatementDate;

    /**
     * Field _AA_HZABND_debitNominatedAccountBranch.
     */
    private java.lang.String _AA_HZABND_debitNominatedAccountBranch;

    /**
     * Field _AA_HZANND_debitNominatedAccountBasic.
     */
    private java.lang.String _AA_HZANND_debitNominatedAccountBasic;

    /**
     * Field _AA_HZASND_debitNominatedAccountSuffix.
     */
    private java.lang.String _AA_HZASND_debitNominatedAccountSuffix;

    /**
     * Field _AA_HZSNND_debitNominatedAccountShortName.
     */
    private java.lang.String _AA_HZSNND_debitNominatedAccountShortName;

    /**
     * Field _AA_HZSNDA_debitNominatedAccountArabicShortName.
     */
    private java.lang.String _AA_HZSNDA_debitNominatedAccountArabicShortName;

    /**
     * Field _AA_HZABNC_creditNominatedAccountBranch.
     */
    private java.lang.String _AA_HZABNC_creditNominatedAccountBranch;

    /**
     * Field _AA_HZANNC_creditNominatedAccountBasic.
     */
    private java.lang.String _AA_HZANNC_creditNominatedAccountBasic;

    /**
     * Field _AA_HZASNC_creditNominatedAccountSuffix.
     */
    private java.lang.String _AA_HZASNC_creditNominatedAccountSuffix;

    /**
     * Field _AA_HZSNNC_creditNominatedAccountShortName.
     */
    private java.lang.String _AA_HZSNNC_creditNominatedAccountShortName;

    /**
     * Field _AA_HZSNCA_creditNominatedAccountArabicShortName.
     */
    private java.lang.String _AA_HZSNCA_creditNominatedAccountArabicShortName;

    /**
     * Field _AA_HZABNG_chargeNominatedAccountBranch.
     */
    private java.lang.String _AA_HZABNG_chargeNominatedAccountBranch;

    /**
     * Field _AA_HZANNG_chargeNominatedAccountBasic.
     */
    private java.lang.String _AA_HZANNG_chargeNominatedAccountBasic;

    /**
     * Field _AA_HZASNG_chargeNominatedAccountSuffix.
     */
    private java.lang.String _AA_HZASNG_chargeNominatedAccountSuffix;

    /**
     * Field _AA_HZSNNG_chargeNominatedAccountShortName.
     */
    private java.lang.String _AA_HZSNNG_chargeNominatedAccountShortName;

    /**
     * Field _AA_HZSNGA_chargeNominatedAccountArabicShortName.
     */
    private java.lang.String _AA_HZSNGA_chargeNominatedAccountArabicShortName;

    /**
     * Field _AA_HZABN_chargeNominatedAccountBranch.
     */
    private java.lang.String _AA_HZABN_chargeNominatedAccountBranch;

    /**
     * Field _AA_HZANN_chargeNominatedAccountBasic.
     */
    private java.lang.String _AA_HZANN_chargeNominatedAccountBasic;

    /**
     * Field _AA_HZASN_chargeNominatedAccountSuffix.
     */
    private java.lang.String _AA_HZASN_chargeNominatedAccountSuffix;

    /**
     * Field _AA_HZSNN_chargeNominatedAccountShortName.
     */
    private java.lang.String _AA_HZSNN_chargeNominatedAccountShortName;

    /**
     * Field _AA_HZSNA_chargeNominatedAccountArabicShortName.
     */
    private java.lang.String _AA_HZSNA_chargeNominatedAccountArabicShortName;

    /**
     * Field _AA_HZABM_masterInterestAccountBranch.
     */
    private java.lang.String _AA_HZABM_masterInterestAccountBranch;

    /**
     * Field _AA_HZANM_masterInterestAccountBasic.
     */
    private java.lang.String _AA_HZANM_masterInterestAccountBasic;

    /**
     * Field _AA_HZASM_masterInterestAccountSuffix.
     */
    private java.lang.String _AA_HZASM_masterInterestAccountSuffix;

    /**
     * Field _AA_HZSNM_masterInterestAccountShortName.
     */
    private java.lang.String _AA_HZSNM_masterInterestAccountShortName;

    /**
     * Field _AA_HZSNMA_masterInterestAccountArabicShortName.
     */
    private java.lang.String _AA_HZSNMA_masterInterestAccountArabicShortName;

    /**
     * Field _AA_HZAIC7_jointAccount.
     */
    private java.lang.String _AA_HZAIC7_jointAccount;

    /**
     * Field _AA_HZYFON_fontisAccount.
     */
    private java.lang.String _AA_HZYFON_fontisAccount;

    /**
     * Field _AA_HZDFRQ_fontisDownloadFrequency.
     */
    private java.lang.String _AA_HZDFRQ_fontisDownloadFrequency;

    /**
     * Field _AA_HZSN20_externalAccountNumber.
     */
    private java.lang.String _AA_HZSN20_externalAccountNumber;

    /**
     * Field _AA_HZCAN_accountNumber.
     */
    private java.lang.String _AA_HZCAN_accountNumber;

    /**
     * Field _AA_HZUNMW_unmaturedWithdrawal.
     */
    private java.lang.String _AA_HZUNMW_unmaturedWithdrawal;

    /**
     * Field _AA_HZAII7_noticeAC.
     */
    private java.lang.String _AA_HZAII7_noticeAC;

    /**
     * Field _AA_HZCLS_closingWithdrawal.
     */
    private java.lang.String _AA_HZCLS_closingWithdrawal;

    /**
     * Field _AA_HZPAB_sourceOriginalBranch.
     */
    private java.lang.String _AA_HZPAB_sourceOriginalBranch;

    /**
     * Field _AA_HZPCHD_branchChangeDate.
     */
    private java.lang.String _AA_HZPCHD_branchChangeDate;

    /**
     * Field _AA_HZABD_previousDescription.
     */
    private java.lang.String _AA_HZABD_previousDescription;

    /**
     * Field _AA_HZDFRM_fontisDownloadFrequencyMeridian.
     */
    private java.lang.String _AA_HZDFRM_fontisDownloadFrequencyMeridian;

    /**
     * Field _AA_HZTRM_transferMethod.
     */
    private java.lang.String _AA_HZTRM_transferMethod;

    /**
     * Field _AA_HZXMD_transferMethodDescription.
     */
    private java.lang.String _AA_HZXMD_transferMethodDescription;

    /**
     * Field _AA_HZHLD_held.
     */
    private java.lang.String _AA_HZHLD_held;

    /**
     * Field _AA_HZAUT_authorised.
     */
    private java.lang.String _AA_HZAUT_authorised;

    /**
     * Field _AA_HZSTA_status.
     */
    private java.lang.String _AA_HZSTA_status;

    /**
     * Field _AA_HZDLMZ_instructionDateLastMaintained.
     */
    private java.lang.String _AA_HZDLMZ_instructionDateLastMaintained;

    /**
     * Field _AA_HZREF_lastCpReference.
     */
    private java.lang.String _AA_HZREF_lastCpReference;

    /**
     * Field _AA_HZASIP_aCSettlInstPresent.
     */
    private java.lang.String _AA_HZASIP_aCSettlInstPresent;

    /**
     * Field _AA_HZREFA_settlementReference.
     */
    private java.lang.String _AA_HZREFA_settlementReference;

    /**
     * Field _AA_HZTADT_taxAdviceType.
     */
    private java.lang.String _AA_HZTADT_taxAdviceType;

    /**
     * Field _AA_HZNTAD_taxAdviceTypeNextYear.
     */
    private java.lang.String _AA_HZNTAD_taxAdviceTypeNextYear;

    /**
     * Field _AA_HZAUNO_lastAuditLetterNumber.
     */
    private java.lang.String _AA_HZAUNO_lastAuditLetterNumber;

    /**
     * Field _AA_HZAUDT_lastAuditLetterDate.
     */
    private java.lang.String _AA_HZAUDT_lastAuditLetterDate;


      //----------------/
     //- Constructors -/
    //----------------/

    public XAA() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'AA_HZABD_previousDescription'.
     * 
     * @return the value of field 'AA_HZABD_previousDescription'.
     */
    public java.lang.String getAA_HZABD_previousDescription(
    ) {
        return this._AA_HZABD_previousDescription;
    }

    /**
     * Returns the value of field
     * 'AA_HZABM_masterInterestAccountBranch'.
     * 
     * @return the value of field
     * 'AA_HZABM_masterInterestAccountBranch'.
     */
    public java.lang.String getAA_HZABM_masterInterestAccountBranch(
    ) {
        return this._AA_HZABM_masterInterestAccountBranch;
    }

    /**
     * Returns the value of field
     * 'AA_HZABNC_creditNominatedAccountBranch'.
     * 
     * @return the value of field
     * 'AA_HZABNC_creditNominatedAccountBranch'.
     */
    public java.lang.String getAA_HZABNC_creditNominatedAccountBranch(
    ) {
        return this._AA_HZABNC_creditNominatedAccountBranch;
    }

    /**
     * Returns the value of field
     * 'AA_HZABND_debitNominatedAccountBranch'.
     * 
     * @return the value of field
     * 'AA_HZABND_debitNominatedAccountBranch'.
     */
    public java.lang.String getAA_HZABND_debitNominatedAccountBranch(
    ) {
        return this._AA_HZABND_debitNominatedAccountBranch;
    }

    /**
     * Returns the value of field
     * 'AA_HZABNG_chargeNominatedAccountBranch'.
     * 
     * @return the value of field
     * 'AA_HZABNG_chargeNominatedAccountBranch'.
     */
    public java.lang.String getAA_HZABNG_chargeNominatedAccountBranch(
    ) {
        return this._AA_HZABNG_chargeNominatedAccountBranch;
    }

    /**
     * Returns the value of field
     * 'AA_HZABN_chargeNominatedAccountBranch'.
     * 
     * @return the value of field
     * 'AA_HZABN_chargeNominatedAccountBranch'.
     */
    public java.lang.String getAA_HZABN_chargeNominatedAccountBranch(
    ) {
        return this._AA_HZABN_chargeNominatedAccountBranch;
    }

    /**
     * Returns the value of field 'AA_HZAB_accountBranch'.
     * 
     * @return the value of field 'AA_HZAB_accountBranch'.
     */
    public java.lang.String getAA_HZAB_accountBranch(
    ) {
        return this._AA_HZAB_accountBranch;
    }

    /**
     * Returns the value of field 'AA_HZACD_analysisCode'.
     * 
     * @return the value of field 'AA_HZACD_analysisCode'.
     */
    public java.lang.String getAA_HZACD_analysisCode(
    ) {
        return this._AA_HZACD_analysisCode;
    }

    /**
     * Returns the value of field 'AA_HZACO_accountOfficer'.
     * 
     * @return the value of field 'AA_HZACO_accountOfficer'.
     */
    public java.lang.String getAA_HZACO_accountOfficer(
    ) {
        return this._AA_HZACO_accountOfficer;
    }

    /**
     * Returns the value of field 'AA_HZACS_accountStructure'.
     * 
     * @return the value of field 'AA_HZACS_accountStructure'.
     */
    public java.lang.String getAA_HZACS_accountStructure(
    ) {
        return this._AA_HZACS_accountStructure;
    }

    /**
     * Returns the value of field 'AA_HZACT_accountType'.
     * 
     * @return the value of field 'AA_HZACT_accountType'.
     */
    public java.lang.String getAA_HZACT_accountType(
    ) {
        return this._AA_HZACT_accountType;
    }

    /**
     * Returns the value of field 'AA_HZAI14_deceasedOrLiquidated'.
     * 
     * @return the value of field 'AA_HZAI14_deceasedOrLiquidated'.
     */
    public java.lang.String getAA_HZAI14_deceasedOrLiquidated(
    ) {
        return this._AA_HZAI14_deceasedOrLiquidated;
    }

    /**
     * Returns the value of field 'AA_HZAI17_accountStatusBlocked'.
     * 
     * @return the value of field 'AA_HZAI17_accountStatusBlocked'.
     */
    public java.lang.String getAA_HZAI17_accountStatusBlocked(
    ) {
        return this._AA_HZAI17_accountStatusBlocked;
    }

    /**
     * Returns the value of field
     * 'AA_HZAI20_accountStatusInactive'.
     * 
     * @return the value of field 'AA_HZAI20_accountStatusInactive'.
     */
    public java.lang.String getAA_HZAI20_accountStatusInactive(
    ) {
        return this._AA_HZAI20_accountStatusInactive;
    }

    /**
     * Returns the value of field 'AA_HZAI30_accountClosing'.
     * 
     * @return the value of field 'AA_HZAI30_accountClosing'.
     */
    public java.lang.String getAA_HZAI30_accountClosing(
    ) {
        return this._AA_HZAI30_accountClosing;
    }

    /**
     * Returns the value of field
     * 'AA_HZAI33_printChargesStatement'.
     * 
     * @return the value of field 'AA_HZAI33_printChargesStatement'.
     */
    public java.lang.String getAA_HZAI33_printChargesStatement(
    ) {
        return this._AA_HZAI33_printChargesStatement;
    }

    /**
     * Returns the value of field
     * 'AA_HZAI47_internalNonCustomerAccount'.
     * 
     * @return the value of field
     * 'AA_HZAI47_internalNonCustomerAccount'.
     */
    public java.lang.String getAA_HZAI47_internalNonCustomerAccount(
    ) {
        return this._AA_HZAI47_internalNonCustomerAccount;
    }

    /**
     * Returns the value of field 'AA_HZAIC7_jointAccount'.
     * 
     * @return the value of field 'AA_HZAIC7_jointAccount'.
     */
    public java.lang.String getAA_HZAIC7_jointAccount(
    ) {
        return this._AA_HZAIC7_jointAccount;
    }

    /**
     * Returns the value of field 'AA_HZAII7_noticeAC'.
     * 
     * @return the value of field 'AA_HZAII7_noticeAC'.
     */
    public java.lang.String getAA_HZAII7_noticeAC(
    ) {
        return this._AA_HZAII7_noticeAC;
    }

    /**
     * Returns the value of field
     * 'AA_HZANM_masterInterestAccountBasic'.
     * 
     * @return the value of field
     * 'AA_HZANM_masterInterestAccountBasic'.
     */
    public java.lang.String getAA_HZANM_masterInterestAccountBasic(
    ) {
        return this._AA_HZANM_masterInterestAccountBasic;
    }

    /**
     * Returns the value of field
     * 'AA_HZANNC_creditNominatedAccountBasic'.
     * 
     * @return the value of field
     * 'AA_HZANNC_creditNominatedAccountBasic'.
     */
    public java.lang.String getAA_HZANNC_creditNominatedAccountBasic(
    ) {
        return this._AA_HZANNC_creditNominatedAccountBasic;
    }

    /**
     * Returns the value of field
     * 'AA_HZANND_debitNominatedAccountBasic'.
     * 
     * @return the value of field
     * 'AA_HZANND_debitNominatedAccountBasic'.
     */
    public java.lang.String getAA_HZANND_debitNominatedAccountBasic(
    ) {
        return this._AA_HZANND_debitNominatedAccountBasic;
    }

    /**
     * Returns the value of field
     * 'AA_HZANNG_chargeNominatedAccountBasic'.
     * 
     * @return the value of field
     * 'AA_HZANNG_chargeNominatedAccountBasic'.
     */
    public java.lang.String getAA_HZANNG_chargeNominatedAccountBasic(
    ) {
        return this._AA_HZANNG_chargeNominatedAccountBasic;
    }

    /**
     * Returns the value of field
     * 'AA_HZANN_chargeNominatedAccountBasic'.
     * 
     * @return the value of field
     * 'AA_HZANN_chargeNominatedAccountBasic'.
     */
    public java.lang.String getAA_HZANN_chargeNominatedAccountBasic(
    ) {
        return this._AA_HZANN_chargeNominatedAccountBasic;
    }

    /**
     * Returns the value of field
     * 'AA_HZAN_basicPartOfAccountNumber'.
     * 
     * @return the value of field 'AA_HZAN_basicPartOfAccountNumber'
     */
    public java.lang.String getAA_HZAN_basicPartOfAccountNumber(
    ) {
        return this._AA_HZAN_basicPartOfAccountNumber;
    }

    /**
     * Returns the value of field 'AA_HZAPP_owningApplicationCode'.
     * 
     * @return the value of field 'AA_HZAPP_owningApplicationCode'.
     */
    public java.lang.String getAA_HZAPP_owningApplicationCode(
    ) {
        return this._AA_HZAPP_owningApplicationCode;
    }

    /**
     * Returns the value of field 'AA_HZASIP_aCSettlInstPresent'.
     * 
     * @return the value of field 'AA_HZASIP_aCSettlInstPresent'.
     */
    public java.lang.String getAA_HZASIP_aCSettlInstPresent(
    ) {
        return this._AA_HZASIP_aCSettlInstPresent;
    }

    /**
     * Returns the value of field
     * 'AA_HZASM_masterInterestAccountSuffix'.
     * 
     * @return the value of field
     * 'AA_HZASM_masterInterestAccountSuffix'.
     */
    public java.lang.String getAA_HZASM_masterInterestAccountSuffix(
    ) {
        return this._AA_HZASM_masterInterestAccountSuffix;
    }

    /**
     * Returns the value of field
     * 'AA_HZASNC_creditNominatedAccountSuffix'.
     * 
     * @return the value of field
     * 'AA_HZASNC_creditNominatedAccountSuffix'.
     */
    public java.lang.String getAA_HZASNC_creditNominatedAccountSuffix(
    ) {
        return this._AA_HZASNC_creditNominatedAccountSuffix;
    }

    /**
     * Returns the value of field
     * 'AA_HZASND_debitNominatedAccountSuffix'.
     * 
     * @return the value of field
     * 'AA_HZASND_debitNominatedAccountSuffix'.
     */
    public java.lang.String getAA_HZASND_debitNominatedAccountSuffix(
    ) {
        return this._AA_HZASND_debitNominatedAccountSuffix;
    }

    /**
     * Returns the value of field
     * 'AA_HZASNG_chargeNominatedAccountSuffix'.
     * 
     * @return the value of field
     * 'AA_HZASNG_chargeNominatedAccountSuffix'.
     */
    public java.lang.String getAA_HZASNG_chargeNominatedAccountSuffix(
    ) {
        return this._AA_HZASNG_chargeNominatedAccountSuffix;
    }

    /**
     * Returns the value of field
     * 'AA_HZASN_chargeNominatedAccountSuffix'.
     * 
     * @return the value of field
     * 'AA_HZASN_chargeNominatedAccountSuffix'.
     */
    public java.lang.String getAA_HZASN_chargeNominatedAccountSuffix(
    ) {
        return this._AA_HZASN_chargeNominatedAccountSuffix;
    }

    /**
     * Returns the value of field 'AA_HZAS_accountSuffix'.
     * 
     * @return the value of field 'AA_HZAS_accountSuffix'.
     */
    public java.lang.String getAA_HZAS_accountSuffix(
    ) {
        return this._AA_HZAS_accountSuffix;
    }

    /**
     * Returns the value of field 'AA_HZAUDT_lastAuditLetterDate'.
     * 
     * @return the value of field 'AA_HZAUDT_lastAuditLetterDate'.
     */
    public java.lang.String getAA_HZAUDT_lastAuditLetterDate(
    ) {
        return this._AA_HZAUDT_lastAuditLetterDate;
    }

    /**
     * Returns the value of field
     * 'AA_HZAUNO_lastAuditLetterNumber'.
     * 
     * @return the value of field 'AA_HZAUNO_lastAuditLetterNumber'.
     */
    public java.lang.String getAA_HZAUNO_lastAuditLetterNumber(
    ) {
        return this._AA_HZAUNO_lastAuditLetterNumber;
    }

    /**
     * Returns the value of field 'AA_HZAUT_authorised'.
     * 
     * @return the value of field 'AA_HZAUT_authorised'.
     */
    public java.lang.String getAA_HZAUT_authorised(
    ) {
        return this._AA_HZAUT_authorised;
    }

    /**
     * Returns the value of field
     * 'AA_HZBALS_balanceOnThePreviousStatement'.
     * 
     * @return the value of field
     * 'AA_HZBALS_balanceOnThePreviousStatement'.
     */
    public java.lang.String getAA_HZBALS_balanceOnThePreviousStatement(
    ) {
        return this._AA_HZBALS_balanceOnThePreviousStatement;
    }

    /**
     * Returns the value of field
     * 'AA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay'.
     * 
     * @return the value of field
     * 'AA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay'.
     */
    public java.lang.String getAA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay(
    ) {
        return this._AA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay;
    }

    /**
     * Returns the value of field 'AA_HZC1R_c1RatingForAccount'.
     * 
     * @return the value of field 'AA_HZC1R_c1RatingForAccount'.
     */
    public java.lang.String getAA_HZC1R_c1RatingForAccount(
    ) {
        return this._AA_HZC1R_c1RatingForAccount;
    }

    /**
     * Returns the value of field 'AA_HZC2R_c2RatingForAccount'.
     * 
     * @return the value of field 'AA_HZC2R_c2RatingForAccount'.
     */
    public java.lang.String getAA_HZC2R_c2RatingForAccount(
    ) {
        return this._AA_HZC2R_c2RatingForAccount;
    }

    /**
     * Returns the value of field 'AA_HZC3R_c3RatingForAccount'.
     * 
     * @return the value of field 'AA_HZC3R_c3RatingForAccount'.
     */
    public java.lang.String getAA_HZC3R_c3RatingForAccount(
    ) {
        return this._AA_HZC3R_c3RatingForAccount;
    }

    /**
     * Returns the value of field 'AA_HZC4R_c4RatingForAccount'.
     * 
     * @return the value of field 'AA_HZC4R_c4RatingForAccount'.
     */
    public java.lang.String getAA_HZC4R_c4RatingForAccount(
    ) {
        return this._AA_HZC4R_c4RatingForAccount;
    }

    /**
     * Returns the value of field 'AA_HZC5R_c5RatingForAccount'.
     * 
     * @return the value of field 'AA_HZC5R_c5RatingForAccount'.
     */
    public java.lang.String getAA_HZC5R_c5RatingForAccount(
    ) {
        return this._AA_HZC5R_c5RatingForAccount;
    }

    /**
     * Returns the value of field 'AA_HZCAD_dateAccountClosed'.
     * 
     * @return the value of field 'AA_HZCAD_dateAccountClosed'.
     */
    public java.lang.String getAA_HZCAD_dateAccountClosed(
    ) {
        return this._AA_HZCAD_dateAccountClosed;
    }

    /**
     * Returns the value of field 'AA_HZCAN_accountNumber'.
     * 
     * @return the value of field 'AA_HZCAN_accountNumber'.
     */
    public java.lang.String getAA_HZCAN_accountNumber(
    ) {
        return this._AA_HZCAN_accountNumber;
    }

    /**
     * Returns the value of field 'AA_HZCCY_currencyMnemonic'.
     * 
     * @return the value of field 'AA_HZCCY_currencyMnemonic'.
     */
    public java.lang.String getAA_HZCCY_currencyMnemonic(
    ) {
        return this._AA_HZCCY_currencyMnemonic;
    }

    /**
     * Returns the value of field 'AA_HZCED_currencyEditField'.
     * 
     * @return the value of field 'AA_HZCED_currencyEditField'.
     */
    public java.lang.String getAA_HZCED_currencyEditField(
    ) {
        return this._AA_HZCED_currencyEditField;
    }

    /**
     * Returns the value of field 'AA_HZCLC_customerLocation'.
     * 
     * @return the value of field 'AA_HZCLC_customerLocation'.
     */
    public java.lang.String getAA_HZCLC_customerLocation(
    ) {
        return this._AA_HZCLC_customerLocation;
    }

    /**
     * Returns the value of field 'AA_HZCLS_closingWithdrawal'.
     * 
     * @return the value of field 'AA_HZCLS_closingWithdrawal'.
     */
    public java.lang.String getAA_HZCLS_closingWithdrawal(
    ) {
        return this._AA_HZCLS_closingWithdrawal;
    }

    /**
     * Returns the value of field 'AA_HZCNAL_residenceCountry'.
     * 
     * @return the value of field 'AA_HZCNAL_residenceCountry'.
     */
    public java.lang.String getAA_HZCNAL_residenceCountry(
    ) {
        return this._AA_HZCNAL_residenceCountry;
    }

    /**
     * Returns the value of field 'AA_HZCNAP_parentCountry'.
     * 
     * @return the value of field 'AA_HZCNAP_parentCountry'.
     */
    public java.lang.String getAA_HZCNAP_parentCountry(
    ) {
        return this._AA_HZCNAP_parentCountry;
    }

    /**
     * Returns the value of field 'AA_HZCNAR_riskCountry'.
     * 
     * @return the value of field 'AA_HZCNAR_riskCountry'.
     */
    public java.lang.String getAA_HZCNAR_riskCountry(
    ) {
        return this._AA_HZCNAR_riskCountry;
    }

    /**
     * Returns the value of field
     * 'AA_HZCSFC_chargesStatementFrequency'.
     * 
     * @return the value of field
     * 'AA_HZCSFC_chargesStatementFrequency'.
     */
    public java.lang.String getAA_HZCSFC_chargesStatementFrequency(
    ) {
        return this._AA_HZCSFC_chargesStatementFrequency;
    }

    /**
     * Returns the value of field
     * 'AA_HZCSTL_lastChargesStatementDate'.
     * 
     * @return the value of field
     * 'AA_HZCSTL_lastChargesStatementDate'.
     */
    public java.lang.String getAA_HZCSTL_lastChargesStatementDate(
    ) {
        return this._AA_HZCSTL_lastChargesStatementDate;
    }

    /**
     * Returns the value of field
     * 'AA_HZCSTN_nextChargesStatementDate'.
     * 
     * @return the value of field
     * 'AA_HZCSTN_nextChargesStatementDate'.
     */
    public java.lang.String getAA_HZCSTN_nextChargesStatementDate(
    ) {
        return this._AA_HZCSTN_nextChargesStatementDate;
    }

    /**
     * Returns the value of field 'AA_HZCTP_customerType'.
     * 
     * @return the value of field 'AA_HZCTP_customerType'.
     */
    public java.lang.String getAA_HZCTP_customerType(
    ) {
        return this._AA_HZCTP_customerType;
    }

    /**
     * Returns the value of field
     * 'AA_HZCUNA_arabicCustomerFullName'.
     * 
     * @return the value of field 'AA_HZCUNA_arabicCustomerFullName'
     */
    public java.lang.String getAA_HZCUNA_arabicCustomerFullName(
    ) {
        return this._AA_HZCUNA_arabicCustomerFullName;
    }

    /**
     * Returns the value of field 'AA_HZCUN_customerFullName'.
     * 
     * @return the value of field 'AA_HZCUN_customerFullName'.
     */
    public java.lang.String getAA_HZCUN_customerFullName(
    ) {
        return this._AA_HZCUN_customerFullName;
    }

    /**
     * Returns the value of field 'AA_HZCUS_customerMnemonic'.
     * 
     * @return the value of field 'AA_HZCUS_customerMnemonic'.
     */
    public java.lang.String getAA_HZCUS_customerMnemonic(
    ) {
        return this._AA_HZCUS_customerMnemonic;
    }

    /**
     * Returns the value of field
     * 'AA_HZDFRM_fontisDownloadFrequencyMeridian'.
     * 
     * @return the value of field
     * 'AA_HZDFRM_fontisDownloadFrequencyMeridian'.
     */
    public java.lang.String getAA_HZDFRM_fontisDownloadFrequencyMeridian(
    ) {
        return this._AA_HZDFRM_fontisDownloadFrequencyMeridian;
    }

    /**
     * Returns the value of field
     * 'AA_HZDFRQ_fontisDownloadFrequency'.
     * 
     * @return the value of field
     * 'AA_HZDFRQ_fontisDownloadFrequency'.
     */
    public java.lang.String getAA_HZDFRQ_fontisDownloadFrequency(
    ) {
        return this._AA_HZDFRQ_fontisDownloadFrequency;
    }

    /**
     * Returns the value of field
     * 'AA_HZDLE_dateOfLastCustomerGeneratedEntry'.
     * 
     * @return the value of field
     * 'AA_HZDLE_dateOfLastCustomerGeneratedEntry'.
     */
    public java.lang.String getAA_HZDLE_dateOfLastCustomerGeneratedEntry(
    ) {
        return this._AA_HZDLE_dateOfLastCustomerGeneratedEntry;
    }

    /**
     * Returns the value of field
     * 'AA_HZDLMZ_instructionDateLastMaintained'.
     * 
     * @return the value of field
     * 'AA_HZDLMZ_instructionDateLastMaintained'.
     */
    public java.lang.String getAA_HZDLMZ_instructionDateLastMaintained(
    ) {
        return this._AA_HZDLMZ_instructionDateLastMaintained;
    }

    /**
     * Returns the value of field 'AA_HZDLM_dateLastMaintained'.
     * 
     * @return the value of field 'AA_HZDLM_dateLastMaintained'.
     */
    public java.lang.String getAA_HZDLM_dateLastMaintained(
    ) {
        return this._AA_HZDLM_dateLastMaintained;
    }

    /**
     * Returns the value of field 'AA_HZHLD_held'.
     * 
     * @return the value of field 'AA_HZHLD_held'.
     */
    public java.lang.String getAA_HZHLD_held(
    ) {
        return this._AA_HZHLD_held;
    }

    /**
     * Returns the value of field 'AA_HZLNM_languageCode'.
     * 
     * @return the value of field 'AA_HZLNM_languageCode'.
     */
    public java.lang.String getAA_HZLNM_languageCode(
    ) {
        return this._AA_HZLNM_languageCode;
    }

    /**
     * Returns the value of field 'AA_HZNANC_numericAnalysisCode'.
     * 
     * @return the value of field 'AA_HZNANC_numericAnalysisCode'.
     */
    public java.lang.String getAA_HZNANC_numericAnalysisCode(
    ) {
        return this._AA_HZNANC_numericAnalysisCode;
    }

    /**
     * Returns the value of field
     * 'AA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement'.
     * 
     * @return the value of field
     * 'AA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement'.
     */
    public java.lang.String getAA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement(
    ) {
        return this._AA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement;
    }

    /**
     * Returns the value of field
     * 'AA_HZNS3_numberOfReferenceCharactersForReconciliation'.
     * 
     * @return the value of field
     * 'AA_HZNS3_numberOfReferenceCharactersForReconciliation'.
     */
    public java.lang.String getAA_HZNS3_numberOfReferenceCharactersForReconciliation(
    ) {
        return this._AA_HZNS3_numberOfReferenceCharactersForReconciliation;
    }

    /**
     * Returns the value of field
     * 'AA_HZNTAD_taxAdviceTypeNextYear'.
     * 
     * @return the value of field 'AA_HZNTAD_taxAdviceTypeNextYear'.
     */
    public java.lang.String getAA_HZNTAD_taxAdviceTypeNextYear(
    ) {
        return this._AA_HZNTAD_taxAdviceTypeNextYear;
    }

    /**
     * Returns the value of field 'AA_HZOAD_dateAccountOpened'.
     * 
     * @return the value of field 'AA_HZOAD_dateAccountOpened'.
     */
    public java.lang.String getAA_HZOAD_dateAccountOpened(
    ) {
        return this._AA_HZOAD_dateAccountOpened;
    }

    /**
     * Returns the value of field 'AA_HZP1R_p1RatingForAccount'.
     * 
     * @return the value of field 'AA_HZP1R_p1RatingForAccount'.
     */
    public java.lang.String getAA_HZP1R_p1RatingForAccount(
    ) {
        return this._AA_HZP1R_p1RatingForAccount;
    }

    /**
     * Returns the value of field 'AA_HZP2R_p2RatingForAccount'.
     * 
     * @return the value of field 'AA_HZP2R_p2RatingForAccount'.
     */
    public java.lang.String getAA_HZP2R_p2RatingForAccount(
    ) {
        return this._AA_HZP2R_p2RatingForAccount;
    }

    /**
     * Returns the value of field 'AA_HZP3R_p3RatingForAccount'.
     * 
     * @return the value of field 'AA_HZP3R_p3RatingForAccount'.
     */
    public java.lang.String getAA_HZP3R_p3RatingForAccount(
    ) {
        return this._AA_HZP3R_p3RatingForAccount;
    }

    /**
     * Returns the value of field 'AA_HZP4R_p4RatingForAccount'.
     * 
     * @return the value of field 'AA_HZP4R_p4RatingForAccount'.
     */
    public java.lang.String getAA_HZP4R_p4RatingForAccount(
    ) {
        return this._AA_HZP4R_p4RatingForAccount;
    }

    /**
     * Returns the value of field 'AA_HZP5R_p5RatingForAccount'.
     * 
     * @return the value of field 'AA_HZP5R_p5RatingForAccount'.
     */
    public java.lang.String getAA_HZP5R_p5RatingForAccount(
    ) {
        return this._AA_HZP5R_p5RatingForAccount;
    }

    /**
     * Returns the value of field 'AA_HZPAB_sourceOriginalBranch'.
     * 
     * @return the value of field 'AA_HZPAB_sourceOriginalBranch'.
     */
    public java.lang.String getAA_HZPAB_sourceOriginalBranch(
    ) {
        return this._AA_HZPAB_sourceOriginalBranch;
    }

    /**
     * Returns the value of field 'AA_HZPCHD_branchChangeDate'.
     * 
     * @return the value of field 'AA_HZPCHD_branchChangeDate'.
     */
    public java.lang.String getAA_HZPCHD_branchChangeDate(
    ) {
        return this._AA_HZPCHD_branchChangeDate;
    }

    /**
     * Returns the value of field 'AA_HZREFA_settlementReference'.
     * 
     * @return the value of field 'AA_HZREFA_settlementReference'.
     */
    public java.lang.String getAA_HZREFA_settlementReference(
    ) {
        return this._AA_HZREFA_settlementReference;
    }

    /**
     * Returns the value of field 'AA_HZREF_lastCpReference'.
     * 
     * @return the value of field 'AA_HZREF_lastCpReference'.
     */
    public java.lang.String getAA_HZREF_lastCpReference(
    ) {
        return this._AA_HZREF_lastCpReference;
    }

    /**
     * Returns the value of field
     * 'AA_HZRETP_retentionPeriod00None011MthSysDflt'.
     * 
     * @return the value of field
     * 'AA_HZRETP_retentionPeriod00None011MthSysDflt'.
     */
    public java.lang.String getAA_HZRETP_retentionPeriod00None011MthSysDflt(
    ) {
        return this._AA_HZRETP_retentionPeriod00None011MthSysDflt;
    }

    /**
     * Returns the value of field 'AA_HZSAC_sundryAnalysisCode'.
     * 
     * @return the value of field 'AA_HZSAC_sundryAnalysisCode'.
     */
    public java.lang.String getAA_HZSAC_sundryAnalysisCode(
    ) {
        return this._AA_HZSAC_sundryAnalysisCode;
    }

    /**
     * Returns the value of field
     * 'AA_HZSFC_statementFrequencyCode'.
     * 
     * @return the value of field 'AA_HZSFC_statementFrequencyCode'.
     */
    public java.lang.String getAA_HZSFC_statementFrequencyCode(
    ) {
        return this._AA_HZSFC_statementFrequencyCode;
    }

    /**
     * Returns the value of field 'AA_HZSHNA_arabicShortName'.
     * 
     * @return the value of field 'AA_HZSHNA_arabicShortName'.
     */
    public java.lang.String getAA_HZSHNA_arabicShortName(
    ) {
        return this._AA_HZSHNA_arabicShortName;
    }

    /**
     * Returns the value of field 'AA_HZSHN_accountShortName'.
     * 
     * @return the value of field 'AA_HZSHN_accountShortName'.
     */
    public java.lang.String getAA_HZSHN_accountShortName(
    ) {
        return this._AA_HZSHN_accountShortName;
    }

    /**
     * Returns the value of field
     * 'AA_HZSN20_externalAccountNumber'.
     * 
     * @return the value of field 'AA_HZSN20_externalAccountNumber'.
     */
    public java.lang.String getAA_HZSN20_externalAccountNumber(
    ) {
        return this._AA_HZSN20_externalAccountNumber;
    }

    /**
     * Returns the value of field
     * 'AA_HZSNA_chargeNominatedAccountArabicShortName'.
     * 
     * @return the value of field
     * 'AA_HZSNA_chargeNominatedAccountArabicShortName'.
     */
    public java.lang.String getAA_HZSNA_chargeNominatedAccountArabicShortName(
    ) {
        return this._AA_HZSNA_chargeNominatedAccountArabicShortName;
    }

    /**
     * Returns the value of field
     * 'AA_HZSNCA_creditNominatedAccountArabicShortName'.
     * 
     * @return the value of field
     * 'AA_HZSNCA_creditNominatedAccountArabicShortName'.
     */
    public java.lang.String getAA_HZSNCA_creditNominatedAccountArabicShortName(
    ) {
        return this._AA_HZSNCA_creditNominatedAccountArabicShortName;
    }

    /**
     * Returns the value of field
     * 'AA_HZSNDA_debitNominatedAccountArabicShortName'.
     * 
     * @return the value of field
     * 'AA_HZSNDA_debitNominatedAccountArabicShortName'.
     */
    public java.lang.String getAA_HZSNDA_debitNominatedAccountArabicShortName(
    ) {
        return this._AA_HZSNDA_debitNominatedAccountArabicShortName;
    }

    /**
     * Returns the value of field
     * 'AA_HZSNGA_chargeNominatedAccountArabicShortName'.
     * 
     * @return the value of field
     * 'AA_HZSNGA_chargeNominatedAccountArabicShortName'.
     */
    public java.lang.String getAA_HZSNGA_chargeNominatedAccountArabicShortName(
    ) {
        return this._AA_HZSNGA_chargeNominatedAccountArabicShortName;
    }

    /**
     * Returns the value of field
     * 'AA_HZSNMA_masterInterestAccountArabicShortName'.
     * 
     * @return the value of field
     * 'AA_HZSNMA_masterInterestAccountArabicShortName'.
     */
    public java.lang.String getAA_HZSNMA_masterInterestAccountArabicShortName(
    ) {
        return this._AA_HZSNMA_masterInterestAccountArabicShortName;
    }

    /**
     * Returns the value of field
     * 'AA_HZSNM_masterInterestAccountShortName'.
     * 
     * @return the value of field
     * 'AA_HZSNM_masterInterestAccountShortName'.
     */
    public java.lang.String getAA_HZSNM_masterInterestAccountShortName(
    ) {
        return this._AA_HZSNM_masterInterestAccountShortName;
    }

    /**
     * Returns the value of field
     * 'AA_HZSNNC_creditNominatedAccountShortName'.
     * 
     * @return the value of field
     * 'AA_HZSNNC_creditNominatedAccountShortName'.
     */
    public java.lang.String getAA_HZSNNC_creditNominatedAccountShortName(
    ) {
        return this._AA_HZSNNC_creditNominatedAccountShortName;
    }

    /**
     * Returns the value of field
     * 'AA_HZSNND_debitNominatedAccountShortName'.
     * 
     * @return the value of field
     * 'AA_HZSNND_debitNominatedAccountShortName'.
     */
    public java.lang.String getAA_HZSNND_debitNominatedAccountShortName(
    ) {
        return this._AA_HZSNND_debitNominatedAccountShortName;
    }

    /**
     * Returns the value of field
     * 'AA_HZSNNG_chargeNominatedAccountShortName'.
     * 
     * @return the value of field
     * 'AA_HZSNNG_chargeNominatedAccountShortName'.
     */
    public java.lang.String getAA_HZSNNG_chargeNominatedAccountShortName(
    ) {
        return this._AA_HZSNNG_chargeNominatedAccountShortName;
    }

    /**
     * Returns the value of field
     * 'AA_HZSNN_chargeNominatedAccountShortName'.
     * 
     * @return the value of field
     * 'AA_HZSNN_chargeNominatedAccountShortName'.
     */
    public java.lang.String getAA_HZSNN_chargeNominatedAccountShortName(
    ) {
        return this._AA_HZSNN_chargeNominatedAccountShortName;
    }

    /**
     * Returns the value of field 'AA_HZSTA_status'.
     * 
     * @return the value of field 'AA_HZSTA_status'.
     */
    public java.lang.String getAA_HZSTA_status(
    ) {
        return this._AA_HZSTA_status;
    }

    /**
     * Returns the value of field 'AA_HZSTML_lastStatementDate'.
     * 
     * @return the value of field 'AA_HZSTML_lastStatementDate'.
     */
    public java.lang.String getAA_HZSTML_lastStatementDate(
    ) {
        return this._AA_HZSTML_lastStatementDate;
    }

    /**
     * Returns the value of field 'AA_HZSTMN_nextStatementDate'.
     * 
     * @return the value of field 'AA_HZSTMN_nextStatementDate'.
     */
    public java.lang.String getAA_HZSTMN_nextStatementDate(
    ) {
        return this._AA_HZSTMN_nextStatementDate;
    }

    /**
     * Returns the value of field 'AA_HZSTNL_lastStatementNumber'.
     * 
     * @return the value of field 'AA_HZSTNL_lastStatementNumber'.
     */
    public java.lang.String getAA_HZSTNL_lastStatementNumber(
    ) {
        return this._AA_HZSTNL_lastStatementNumber;
    }

    /**
     * Returns the value of field 'AA_HZTADT_taxAdviceType'.
     * 
     * @return the value of field 'AA_HZTADT_taxAdviceType'.
     */
    public java.lang.String getAA_HZTADT_taxAdviceType(
    ) {
        return this._AA_HZTADT_taxAdviceType;
    }

    /**
     * Returns the value of field 'AA_HZTRM_transferMethod'.
     * 
     * @return the value of field 'AA_HZTRM_transferMethod'.
     */
    public java.lang.String getAA_HZTRM_transferMethod(
    ) {
        return this._AA_HZTRM_transferMethod;
    }

    /**
     * Returns the value of field 'AA_HZUNMW_unmaturedWithdrawal'.
     * 
     * @return the value of field 'AA_HZUNMW_unmaturedWithdrawal'.
     */
    public java.lang.String getAA_HZUNMW_unmaturedWithdrawal(
    ) {
        return this._AA_HZUNMW_unmaturedWithdrawal;
    }

    /**
     * Returns the value of field
     * 'AA_HZXMD_transferMethodDescription'.
     * 
     * @return the value of field
     * 'AA_HZXMD_transferMethodDescription'.
     */
    public java.lang.String getAA_HZXMD_transferMethodDescription(
    ) {
        return this._AA_HZXMD_transferMethodDescription;
    }

    /**
     * Returns the value of field 'AA_HZYFON_fontisAccount'.
     * 
     * @return the value of field 'AA_HZYFON_fontisAccount'.
     */
    public java.lang.String getAA_HZYFON_fontisAccount(
    ) {
        return this._AA_HZYFON_fontisAccount;
    }

    /**
     * Returns the value of field 'HH_DED_defaultExpiryDate'.
     * 
     * @return the value of field 'HH_DED_defaultExpiryDate'.
     */
    public java.lang.String getHH_DED_defaultExpiryDate(
    ) {
        return this._HH_DED_defaultExpiryDate;
    }

    /**
     * Returns the value of field 'HH_HRC_holdCode'.
     * 
     * @return the value of field 'HH_HRC_holdCode'.
     */
    public java.lang.String getHH_HRC_holdCode(
    ) {
        return this._HH_HRC_holdCode;
    }

    /**
     * Returns the value of field 'HH_HRD_holdDescription'.
     * 
     * @return the value of field 'HH_HRD_holdDescription'.
     */
    public java.lang.String getHH_HRD_holdDescription(
    ) {
        return this._HH_HRD_holdDescription;
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
     * Sets the value of field 'AA_HZABD_previousDescription'.
     * 
     * @param AA_HZABD_previousDescription the value of field
     * 'AA_HZABD_previousDescription'.
     */
    public void setAA_HZABD_previousDescription(
            final java.lang.String AA_HZABD_previousDescription) {
        this._AA_HZABD_previousDescription = AA_HZABD_previousDescription;
    }

    /**
     * Sets the value of field
     * 'AA_HZABM_masterInterestAccountBranch'.
     * 
     * @param AA_HZABM_masterInterestAccountBranch the value of
     * field 'AA_HZABM_masterInterestAccountBranch'.
     */
    public void setAA_HZABM_masterInterestAccountBranch(
            final java.lang.String AA_HZABM_masterInterestAccountBranch) {
        this._AA_HZABM_masterInterestAccountBranch = AA_HZABM_masterInterestAccountBranch;
    }

    /**
     * Sets the value of field
     * 'AA_HZABNC_creditNominatedAccountBranch'.
     * 
     * @param AA_HZABNC_creditNominatedAccountBranch the value of
     * field 'AA_HZABNC_creditNominatedAccountBranch'.
     */
    public void setAA_HZABNC_creditNominatedAccountBranch(
            final java.lang.String AA_HZABNC_creditNominatedAccountBranch) {
        this._AA_HZABNC_creditNominatedAccountBranch = AA_HZABNC_creditNominatedAccountBranch;
    }

    /**
     * Sets the value of field
     * 'AA_HZABND_debitNominatedAccountBranch'.
     * 
     * @param AA_HZABND_debitNominatedAccountBranch the value of
     * field 'AA_HZABND_debitNominatedAccountBranch'.
     */
    public void setAA_HZABND_debitNominatedAccountBranch(
            final java.lang.String AA_HZABND_debitNominatedAccountBranch) {
        this._AA_HZABND_debitNominatedAccountBranch = AA_HZABND_debitNominatedAccountBranch;
    }

    /**
     * Sets the value of field
     * 'AA_HZABNG_chargeNominatedAccountBranch'.
     * 
     * @param AA_HZABNG_chargeNominatedAccountBranch the value of
     * field 'AA_HZABNG_chargeNominatedAccountBranch'.
     */
    public void setAA_HZABNG_chargeNominatedAccountBranch(
            final java.lang.String AA_HZABNG_chargeNominatedAccountBranch) {
        this._AA_HZABNG_chargeNominatedAccountBranch = AA_HZABNG_chargeNominatedAccountBranch;
    }

    /**
     * Sets the value of field
     * 'AA_HZABN_chargeNominatedAccountBranch'.
     * 
     * @param AA_HZABN_chargeNominatedAccountBranch the value of
     * field 'AA_HZABN_chargeNominatedAccountBranch'.
     */
    public void setAA_HZABN_chargeNominatedAccountBranch(
            final java.lang.String AA_HZABN_chargeNominatedAccountBranch) {
        this._AA_HZABN_chargeNominatedAccountBranch = AA_HZABN_chargeNominatedAccountBranch;
    }

    /**
     * Sets the value of field 'AA_HZAB_accountBranch'.
     * 
     * @param AA_HZAB_accountBranch the value of field
     * 'AA_HZAB_accountBranch'.
     */
    public void setAA_HZAB_accountBranch(
            final java.lang.String AA_HZAB_accountBranch) {
        this._AA_HZAB_accountBranch = AA_HZAB_accountBranch;
    }

    /**
     * Sets the value of field 'AA_HZACD_analysisCode'.
     * 
     * @param AA_HZACD_analysisCode the value of field
     * 'AA_HZACD_analysisCode'.
     */
    public void setAA_HZACD_analysisCode(
            final java.lang.String AA_HZACD_analysisCode) {
        this._AA_HZACD_analysisCode = AA_HZACD_analysisCode;
    }

    /**
     * Sets the value of field 'AA_HZACO_accountOfficer'.
     * 
     * @param AA_HZACO_accountOfficer the value of field
     * 'AA_HZACO_accountOfficer'.
     */
    public void setAA_HZACO_accountOfficer(
            final java.lang.String AA_HZACO_accountOfficer) {
        this._AA_HZACO_accountOfficer = AA_HZACO_accountOfficer;
    }

    /**
     * Sets the value of field 'AA_HZACS_accountStructure'.
     * 
     * @param AA_HZACS_accountStructure the value of field
     * 'AA_HZACS_accountStructure'.
     */
    public void setAA_HZACS_accountStructure(
            final java.lang.String AA_HZACS_accountStructure) {
        this._AA_HZACS_accountStructure = AA_HZACS_accountStructure;
    }

    /**
     * Sets the value of field 'AA_HZACT_accountType'.
     * 
     * @param AA_HZACT_accountType the value of field
     * 'AA_HZACT_accountType'.
     */
    public void setAA_HZACT_accountType(
            final java.lang.String AA_HZACT_accountType) {
        this._AA_HZACT_accountType = AA_HZACT_accountType;
    }

    /**
     * Sets the value of field 'AA_HZAI14_deceasedOrLiquidated'.
     * 
     * @param AA_HZAI14_deceasedOrLiquidated the value of field
     * 'AA_HZAI14_deceasedOrLiquidated'.
     */
    public void setAA_HZAI14_deceasedOrLiquidated(
            final java.lang.String AA_HZAI14_deceasedOrLiquidated) {
        this._AA_HZAI14_deceasedOrLiquidated = AA_HZAI14_deceasedOrLiquidated;
    }

    /**
     * Sets the value of field 'AA_HZAI17_accountStatusBlocked'.
     * 
     * @param AA_HZAI17_accountStatusBlocked the value of field
     * 'AA_HZAI17_accountStatusBlocked'.
     */
    public void setAA_HZAI17_accountStatusBlocked(
            final java.lang.String AA_HZAI17_accountStatusBlocked) {
        this._AA_HZAI17_accountStatusBlocked = AA_HZAI17_accountStatusBlocked;
    }

    /**
     * Sets the value of field 'AA_HZAI20_accountStatusInactive'.
     * 
     * @param AA_HZAI20_accountStatusInactive the value of field
     * 'AA_HZAI20_accountStatusInactive'.
     */
    public void setAA_HZAI20_accountStatusInactive(
            final java.lang.String AA_HZAI20_accountStatusInactive) {
        this._AA_HZAI20_accountStatusInactive = AA_HZAI20_accountStatusInactive;
    }

    /**
     * Sets the value of field 'AA_HZAI30_accountClosing'.
     * 
     * @param AA_HZAI30_accountClosing the value of field
     * 'AA_HZAI30_accountClosing'.
     */
    public void setAA_HZAI30_accountClosing(
            final java.lang.String AA_HZAI30_accountClosing) {
        this._AA_HZAI30_accountClosing = AA_HZAI30_accountClosing;
    }

    /**
     * Sets the value of field 'AA_HZAI33_printChargesStatement'.
     * 
     * @param AA_HZAI33_printChargesStatement the value of field
     * 'AA_HZAI33_printChargesStatement'.
     */
    public void setAA_HZAI33_printChargesStatement(
            final java.lang.String AA_HZAI33_printChargesStatement) {
        this._AA_HZAI33_printChargesStatement = AA_HZAI33_printChargesStatement;
    }

    /**
     * Sets the value of field
     * 'AA_HZAI47_internalNonCustomerAccount'.
     * 
     * @param AA_HZAI47_internalNonCustomerAccount the value of
     * field 'AA_HZAI47_internalNonCustomerAccount'.
     */
    public void setAA_HZAI47_internalNonCustomerAccount(
            final java.lang.String AA_HZAI47_internalNonCustomerAccount) {
        this._AA_HZAI47_internalNonCustomerAccount = AA_HZAI47_internalNonCustomerAccount;
    }

    /**
     * Sets the value of field 'AA_HZAIC7_jointAccount'.
     * 
     * @param AA_HZAIC7_jointAccount the value of field
     * 'AA_HZAIC7_jointAccount'.
     */
    public void setAA_HZAIC7_jointAccount(
            final java.lang.String AA_HZAIC7_jointAccount) {
        this._AA_HZAIC7_jointAccount = AA_HZAIC7_jointAccount;
    }

    /**
     * Sets the value of field 'AA_HZAII7_noticeAC'.
     * 
     * @param AA_HZAII7_noticeAC the value of field
     * 'AA_HZAII7_noticeAC'.
     */
    public void setAA_HZAII7_noticeAC(
            final java.lang.String AA_HZAII7_noticeAC) {
        this._AA_HZAII7_noticeAC = AA_HZAII7_noticeAC;
    }

    /**
     * Sets the value of field
     * 'AA_HZANM_masterInterestAccountBasic'.
     * 
     * @param AA_HZANM_masterInterestAccountBasic the value of
     * field 'AA_HZANM_masterInterestAccountBasic'.
     */
    public void setAA_HZANM_masterInterestAccountBasic(
            final java.lang.String AA_HZANM_masterInterestAccountBasic) {
        this._AA_HZANM_masterInterestAccountBasic = AA_HZANM_masterInterestAccountBasic;
    }

    /**
     * Sets the value of field
     * 'AA_HZANNC_creditNominatedAccountBasic'.
     * 
     * @param AA_HZANNC_creditNominatedAccountBasic the value of
     * field 'AA_HZANNC_creditNominatedAccountBasic'.
     */
    public void setAA_HZANNC_creditNominatedAccountBasic(
            final java.lang.String AA_HZANNC_creditNominatedAccountBasic) {
        this._AA_HZANNC_creditNominatedAccountBasic = AA_HZANNC_creditNominatedAccountBasic;
    }

    /**
     * Sets the value of field
     * 'AA_HZANND_debitNominatedAccountBasic'.
     * 
     * @param AA_HZANND_debitNominatedAccountBasic the value of
     * field 'AA_HZANND_debitNominatedAccountBasic'.
     */
    public void setAA_HZANND_debitNominatedAccountBasic(
            final java.lang.String AA_HZANND_debitNominatedAccountBasic) {
        this._AA_HZANND_debitNominatedAccountBasic = AA_HZANND_debitNominatedAccountBasic;
    }

    /**
     * Sets the value of field
     * 'AA_HZANNG_chargeNominatedAccountBasic'.
     * 
     * @param AA_HZANNG_chargeNominatedAccountBasic the value of
     * field 'AA_HZANNG_chargeNominatedAccountBasic'.
     */
    public void setAA_HZANNG_chargeNominatedAccountBasic(
            final java.lang.String AA_HZANNG_chargeNominatedAccountBasic) {
        this._AA_HZANNG_chargeNominatedAccountBasic = AA_HZANNG_chargeNominatedAccountBasic;
    }

    /**
     * Sets the value of field
     * 'AA_HZANN_chargeNominatedAccountBasic'.
     * 
     * @param AA_HZANN_chargeNominatedAccountBasic the value of
     * field 'AA_HZANN_chargeNominatedAccountBasic'.
     */
    public void setAA_HZANN_chargeNominatedAccountBasic(
            final java.lang.String AA_HZANN_chargeNominatedAccountBasic) {
        this._AA_HZANN_chargeNominatedAccountBasic = AA_HZANN_chargeNominatedAccountBasic;
    }

    /**
     * Sets the value of field 'AA_HZAN_basicPartOfAccountNumber'.
     * 
     * @param AA_HZAN_basicPartOfAccountNumber the value of field
     * 'AA_HZAN_basicPartOfAccountNumber'.
     */
    public void setAA_HZAN_basicPartOfAccountNumber(
            final java.lang.String AA_HZAN_basicPartOfAccountNumber) {
        this._AA_HZAN_basicPartOfAccountNumber = AA_HZAN_basicPartOfAccountNumber;
    }

    /**
     * Sets the value of field 'AA_HZAPP_owningApplicationCode'.
     * 
     * @param AA_HZAPP_owningApplicationCode the value of field
     * 'AA_HZAPP_owningApplicationCode'.
     */
    public void setAA_HZAPP_owningApplicationCode(
            final java.lang.String AA_HZAPP_owningApplicationCode) {
        this._AA_HZAPP_owningApplicationCode = AA_HZAPP_owningApplicationCode;
    }

    /**
     * Sets the value of field 'AA_HZASIP_aCSettlInstPresent'.
     * 
     * @param AA_HZASIP_aCSettlInstPresent the value of field
     * 'AA_HZASIP_aCSettlInstPresent'.
     */
    public void setAA_HZASIP_aCSettlInstPresent(
            final java.lang.String AA_HZASIP_aCSettlInstPresent) {
        this._AA_HZASIP_aCSettlInstPresent = AA_HZASIP_aCSettlInstPresent;
    }

    /**
     * Sets the value of field
     * 'AA_HZASM_masterInterestAccountSuffix'.
     * 
     * @param AA_HZASM_masterInterestAccountSuffix the value of
     * field 'AA_HZASM_masterInterestAccountSuffix'.
     */
    public void setAA_HZASM_masterInterestAccountSuffix(
            final java.lang.String AA_HZASM_masterInterestAccountSuffix) {
        this._AA_HZASM_masterInterestAccountSuffix = AA_HZASM_masterInterestAccountSuffix;
    }

    /**
     * Sets the value of field
     * 'AA_HZASNC_creditNominatedAccountSuffix'.
     * 
     * @param AA_HZASNC_creditNominatedAccountSuffix the value of
     * field 'AA_HZASNC_creditNominatedAccountSuffix'.
     */
    public void setAA_HZASNC_creditNominatedAccountSuffix(
            final java.lang.String AA_HZASNC_creditNominatedAccountSuffix) {
        this._AA_HZASNC_creditNominatedAccountSuffix = AA_HZASNC_creditNominatedAccountSuffix;
    }

    /**
     * Sets the value of field
     * 'AA_HZASND_debitNominatedAccountSuffix'.
     * 
     * @param AA_HZASND_debitNominatedAccountSuffix the value of
     * field 'AA_HZASND_debitNominatedAccountSuffix'.
     */
    public void setAA_HZASND_debitNominatedAccountSuffix(
            final java.lang.String AA_HZASND_debitNominatedAccountSuffix) {
        this._AA_HZASND_debitNominatedAccountSuffix = AA_HZASND_debitNominatedAccountSuffix;
    }

    /**
     * Sets the value of field
     * 'AA_HZASNG_chargeNominatedAccountSuffix'.
     * 
     * @param AA_HZASNG_chargeNominatedAccountSuffix the value of
     * field 'AA_HZASNG_chargeNominatedAccountSuffix'.
     */
    public void setAA_HZASNG_chargeNominatedAccountSuffix(
            final java.lang.String AA_HZASNG_chargeNominatedAccountSuffix) {
        this._AA_HZASNG_chargeNominatedAccountSuffix = AA_HZASNG_chargeNominatedAccountSuffix;
    }

    /**
     * Sets the value of field
     * 'AA_HZASN_chargeNominatedAccountSuffix'.
     * 
     * @param AA_HZASN_chargeNominatedAccountSuffix the value of
     * field 'AA_HZASN_chargeNominatedAccountSuffix'.
     */
    public void setAA_HZASN_chargeNominatedAccountSuffix(
            final java.lang.String AA_HZASN_chargeNominatedAccountSuffix) {
        this._AA_HZASN_chargeNominatedAccountSuffix = AA_HZASN_chargeNominatedAccountSuffix;
    }

    /**
     * Sets the value of field 'AA_HZAS_accountSuffix'.
     * 
     * @param AA_HZAS_accountSuffix the value of field
     * 'AA_HZAS_accountSuffix'.
     */
    public void setAA_HZAS_accountSuffix(
            final java.lang.String AA_HZAS_accountSuffix) {
        this._AA_HZAS_accountSuffix = AA_HZAS_accountSuffix;
    }

    /**
     * Sets the value of field 'AA_HZAUDT_lastAuditLetterDate'.
     * 
     * @param AA_HZAUDT_lastAuditLetterDate the value of field
     * 'AA_HZAUDT_lastAuditLetterDate'.
     */
    public void setAA_HZAUDT_lastAuditLetterDate(
            final java.lang.String AA_HZAUDT_lastAuditLetterDate) {
        this._AA_HZAUDT_lastAuditLetterDate = AA_HZAUDT_lastAuditLetterDate;
    }

    /**
     * Sets the value of field 'AA_HZAUNO_lastAuditLetterNumber'.
     * 
     * @param AA_HZAUNO_lastAuditLetterNumber the value of field
     * 'AA_HZAUNO_lastAuditLetterNumber'.
     */
    public void setAA_HZAUNO_lastAuditLetterNumber(
            final java.lang.String AA_HZAUNO_lastAuditLetterNumber) {
        this._AA_HZAUNO_lastAuditLetterNumber = AA_HZAUNO_lastAuditLetterNumber;
    }

    /**
     * Sets the value of field 'AA_HZAUT_authorised'.
     * 
     * @param AA_HZAUT_authorised the value of field
     * 'AA_HZAUT_authorised'.
     */
    public void setAA_HZAUT_authorised(
            final java.lang.String AA_HZAUT_authorised) {
        this._AA_HZAUT_authorised = AA_HZAUT_authorised;
    }

    /**
     * Sets the value of field
     * 'AA_HZBALS_balanceOnThePreviousStatement'.
     * 
     * @param AA_HZBALS_balanceOnThePreviousStatement the value of
     * field 'AA_HZBALS_balanceOnThePreviousStatement'.
     */
    public void setAA_HZBALS_balanceOnThePreviousStatement(
            final java.lang.String AA_HZBALS_balanceOnThePreviousStatement) {
        this._AA_HZBALS_balanceOnThePreviousStatement = AA_HZBALS_balanceOnThePreviousStatement;
    }

    /**
     * Sets the value of field
     * 'AA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay'.
     * 
     * @param AA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay the
     * value of field
     * 'AA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay'.
     */
    public void setAA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay(
            final java.lang.String AA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay) {
        this._AA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay = AA_HZBAL_balanceAtCloseOfBusinessLastBusinessDay;
    }

    /**
     * Sets the value of field 'AA_HZC1R_c1RatingForAccount'.
     * 
     * @param AA_HZC1R_c1RatingForAccount the value of field
     * 'AA_HZC1R_c1RatingForAccount'.
     */
    public void setAA_HZC1R_c1RatingForAccount(
            final java.lang.String AA_HZC1R_c1RatingForAccount) {
        this._AA_HZC1R_c1RatingForAccount = AA_HZC1R_c1RatingForAccount;
    }

    /**
     * Sets the value of field 'AA_HZC2R_c2RatingForAccount'.
     * 
     * @param AA_HZC2R_c2RatingForAccount the value of field
     * 'AA_HZC2R_c2RatingForAccount'.
     */
    public void setAA_HZC2R_c2RatingForAccount(
            final java.lang.String AA_HZC2R_c2RatingForAccount) {
        this._AA_HZC2R_c2RatingForAccount = AA_HZC2R_c2RatingForAccount;
    }

    /**
     * Sets the value of field 'AA_HZC3R_c3RatingForAccount'.
     * 
     * @param AA_HZC3R_c3RatingForAccount the value of field
     * 'AA_HZC3R_c3RatingForAccount'.
     */
    public void setAA_HZC3R_c3RatingForAccount(
            final java.lang.String AA_HZC3R_c3RatingForAccount) {
        this._AA_HZC3R_c3RatingForAccount = AA_HZC3R_c3RatingForAccount;
    }

    /**
     * Sets the value of field 'AA_HZC4R_c4RatingForAccount'.
     * 
     * @param AA_HZC4R_c4RatingForAccount the value of field
     * 'AA_HZC4R_c4RatingForAccount'.
     */
    public void setAA_HZC4R_c4RatingForAccount(
            final java.lang.String AA_HZC4R_c4RatingForAccount) {
        this._AA_HZC4R_c4RatingForAccount = AA_HZC4R_c4RatingForAccount;
    }

    /**
     * Sets the value of field 'AA_HZC5R_c5RatingForAccount'.
     * 
     * @param AA_HZC5R_c5RatingForAccount the value of field
     * 'AA_HZC5R_c5RatingForAccount'.
     */
    public void setAA_HZC5R_c5RatingForAccount(
            final java.lang.String AA_HZC5R_c5RatingForAccount) {
        this._AA_HZC5R_c5RatingForAccount = AA_HZC5R_c5RatingForAccount;
    }

    /**
     * Sets the value of field 'AA_HZCAD_dateAccountClosed'.
     * 
     * @param AA_HZCAD_dateAccountClosed the value of field
     * 'AA_HZCAD_dateAccountClosed'.
     */
    public void setAA_HZCAD_dateAccountClosed(
            final java.lang.String AA_HZCAD_dateAccountClosed) {
        this._AA_HZCAD_dateAccountClosed = AA_HZCAD_dateAccountClosed;
    }

    /**
     * Sets the value of field 'AA_HZCAN_accountNumber'.
     * 
     * @param AA_HZCAN_accountNumber the value of field
     * 'AA_HZCAN_accountNumber'.
     */
    public void setAA_HZCAN_accountNumber(
            final java.lang.String AA_HZCAN_accountNumber) {
        this._AA_HZCAN_accountNumber = AA_HZCAN_accountNumber;
    }

    /**
     * Sets the value of field 'AA_HZCCY_currencyMnemonic'.
     * 
     * @param AA_HZCCY_currencyMnemonic the value of field
     * 'AA_HZCCY_currencyMnemonic'.
     */
    public void setAA_HZCCY_currencyMnemonic(
            final java.lang.String AA_HZCCY_currencyMnemonic) {
        this._AA_HZCCY_currencyMnemonic = AA_HZCCY_currencyMnemonic;
    }

    /**
     * Sets the value of field 'AA_HZCED_currencyEditField'.
     * 
     * @param AA_HZCED_currencyEditField the value of field
     * 'AA_HZCED_currencyEditField'.
     */
    public void setAA_HZCED_currencyEditField(
            final java.lang.String AA_HZCED_currencyEditField) {
        this._AA_HZCED_currencyEditField = AA_HZCED_currencyEditField;
    }

    /**
     * Sets the value of field 'AA_HZCLC_customerLocation'.
     * 
     * @param AA_HZCLC_customerLocation the value of field
     * 'AA_HZCLC_customerLocation'.
     */
    public void setAA_HZCLC_customerLocation(
            final java.lang.String AA_HZCLC_customerLocation) {
        this._AA_HZCLC_customerLocation = AA_HZCLC_customerLocation;
    }

    /**
     * Sets the value of field 'AA_HZCLS_closingWithdrawal'.
     * 
     * @param AA_HZCLS_closingWithdrawal the value of field
     * 'AA_HZCLS_closingWithdrawal'.
     */
    public void setAA_HZCLS_closingWithdrawal(
            final java.lang.String AA_HZCLS_closingWithdrawal) {
        this._AA_HZCLS_closingWithdrawal = AA_HZCLS_closingWithdrawal;
    }

    /**
     * Sets the value of field 'AA_HZCNAL_residenceCountry'.
     * 
     * @param AA_HZCNAL_residenceCountry the value of field
     * 'AA_HZCNAL_residenceCountry'.
     */
    public void setAA_HZCNAL_residenceCountry(
            final java.lang.String AA_HZCNAL_residenceCountry) {
        this._AA_HZCNAL_residenceCountry = AA_HZCNAL_residenceCountry;
    }

    /**
     * Sets the value of field 'AA_HZCNAP_parentCountry'.
     * 
     * @param AA_HZCNAP_parentCountry the value of field
     * 'AA_HZCNAP_parentCountry'.
     */
    public void setAA_HZCNAP_parentCountry(
            final java.lang.String AA_HZCNAP_parentCountry) {
        this._AA_HZCNAP_parentCountry = AA_HZCNAP_parentCountry;
    }

    /**
     * Sets the value of field 'AA_HZCNAR_riskCountry'.
     * 
     * @param AA_HZCNAR_riskCountry the value of field
     * 'AA_HZCNAR_riskCountry'.
     */
    public void setAA_HZCNAR_riskCountry(
            final java.lang.String AA_HZCNAR_riskCountry) {
        this._AA_HZCNAR_riskCountry = AA_HZCNAR_riskCountry;
    }

    /**
     * Sets the value of field
     * 'AA_HZCSFC_chargesStatementFrequency'.
     * 
     * @param AA_HZCSFC_chargesStatementFrequency the value of
     * field 'AA_HZCSFC_chargesStatementFrequency'.
     */
    public void setAA_HZCSFC_chargesStatementFrequency(
            final java.lang.String AA_HZCSFC_chargesStatementFrequency) {
        this._AA_HZCSFC_chargesStatementFrequency = AA_HZCSFC_chargesStatementFrequency;
    }

    /**
     * Sets the value of field
     * 'AA_HZCSTL_lastChargesStatementDate'.
     * 
     * @param AA_HZCSTL_lastChargesStatementDate the value of field
     * 'AA_HZCSTL_lastChargesStatementDate'.
     */
    public void setAA_HZCSTL_lastChargesStatementDate(
            final java.lang.String AA_HZCSTL_lastChargesStatementDate) {
        this._AA_HZCSTL_lastChargesStatementDate = AA_HZCSTL_lastChargesStatementDate;
    }

    /**
     * Sets the value of field
     * 'AA_HZCSTN_nextChargesStatementDate'.
     * 
     * @param AA_HZCSTN_nextChargesStatementDate the value of field
     * 'AA_HZCSTN_nextChargesStatementDate'.
     */
    public void setAA_HZCSTN_nextChargesStatementDate(
            final java.lang.String AA_HZCSTN_nextChargesStatementDate) {
        this._AA_HZCSTN_nextChargesStatementDate = AA_HZCSTN_nextChargesStatementDate;
    }

    /**
     * Sets the value of field 'AA_HZCTP_customerType'.
     * 
     * @param AA_HZCTP_customerType the value of field
     * 'AA_HZCTP_customerType'.
     */
    public void setAA_HZCTP_customerType(
            final java.lang.String AA_HZCTP_customerType) {
        this._AA_HZCTP_customerType = AA_HZCTP_customerType;
    }

    /**
     * Sets the value of field 'AA_HZCUNA_arabicCustomerFullName'.
     * 
     * @param AA_HZCUNA_arabicCustomerFullName the value of field
     * 'AA_HZCUNA_arabicCustomerFullName'.
     */
    public void setAA_HZCUNA_arabicCustomerFullName(
            final java.lang.String AA_HZCUNA_arabicCustomerFullName) {
        this._AA_HZCUNA_arabicCustomerFullName = AA_HZCUNA_arabicCustomerFullName;
    }

    /**
     * Sets the value of field 'AA_HZCUN_customerFullName'.
     * 
     * @param AA_HZCUN_customerFullName the value of field
     * 'AA_HZCUN_customerFullName'.
     */
    public void setAA_HZCUN_customerFullName(
            final java.lang.String AA_HZCUN_customerFullName) {
        this._AA_HZCUN_customerFullName = AA_HZCUN_customerFullName;
    }

    /**
     * Sets the value of field 'AA_HZCUS_customerMnemonic'.
     * 
     * @param AA_HZCUS_customerMnemonic the value of field
     * 'AA_HZCUS_customerMnemonic'.
     */
    public void setAA_HZCUS_customerMnemonic(
            final java.lang.String AA_HZCUS_customerMnemonic) {
        this._AA_HZCUS_customerMnemonic = AA_HZCUS_customerMnemonic;
    }

    /**
     * Sets the value of field
     * 'AA_HZDFRM_fontisDownloadFrequencyMeridian'.
     * 
     * @param AA_HZDFRM_fontisDownloadFrequencyMeridian the value
     * of field 'AA_HZDFRM_fontisDownloadFrequencyMeridian'.
     */
    public void setAA_HZDFRM_fontisDownloadFrequencyMeridian(
            final java.lang.String AA_HZDFRM_fontisDownloadFrequencyMeridian) {
        this._AA_HZDFRM_fontisDownloadFrequencyMeridian = AA_HZDFRM_fontisDownloadFrequencyMeridian;
    }

    /**
     * Sets the value of field 'AA_HZDFRQ_fontisDownloadFrequency'.
     * 
     * @param AA_HZDFRQ_fontisDownloadFrequency the value of field
     * 'AA_HZDFRQ_fontisDownloadFrequency'.
     */
    public void setAA_HZDFRQ_fontisDownloadFrequency(
            final java.lang.String AA_HZDFRQ_fontisDownloadFrequency) {
        this._AA_HZDFRQ_fontisDownloadFrequency = AA_HZDFRQ_fontisDownloadFrequency;
    }

    /**
     * Sets the value of field
     * 'AA_HZDLE_dateOfLastCustomerGeneratedEntry'.
     * 
     * @param AA_HZDLE_dateOfLastCustomerGeneratedEntry the value
     * of field 'AA_HZDLE_dateOfLastCustomerGeneratedEntry'.
     */
    public void setAA_HZDLE_dateOfLastCustomerGeneratedEntry(
            final java.lang.String AA_HZDLE_dateOfLastCustomerGeneratedEntry) {
        this._AA_HZDLE_dateOfLastCustomerGeneratedEntry = AA_HZDLE_dateOfLastCustomerGeneratedEntry;
    }

    /**
     * Sets the value of field
     * 'AA_HZDLMZ_instructionDateLastMaintained'.
     * 
     * @param AA_HZDLMZ_instructionDateLastMaintained the value of
     * field 'AA_HZDLMZ_instructionDateLastMaintained'.
     */
    public void setAA_HZDLMZ_instructionDateLastMaintained(
            final java.lang.String AA_HZDLMZ_instructionDateLastMaintained) {
        this._AA_HZDLMZ_instructionDateLastMaintained = AA_HZDLMZ_instructionDateLastMaintained;
    }

    /**
     * Sets the value of field 'AA_HZDLM_dateLastMaintained'.
     * 
     * @param AA_HZDLM_dateLastMaintained the value of field
     * 'AA_HZDLM_dateLastMaintained'.
     */
    public void setAA_HZDLM_dateLastMaintained(
            final java.lang.String AA_HZDLM_dateLastMaintained) {
        this._AA_HZDLM_dateLastMaintained = AA_HZDLM_dateLastMaintained;
    }

    /**
     * Sets the value of field 'AA_HZHLD_held'.
     * 
     * @param AA_HZHLD_held the value of field 'AA_HZHLD_held'.
     */
    public void setAA_HZHLD_held(
            final java.lang.String AA_HZHLD_held) {
        this._AA_HZHLD_held = AA_HZHLD_held;
    }

    /**
     * Sets the value of field 'AA_HZLNM_languageCode'.
     * 
     * @param AA_HZLNM_languageCode the value of field
     * 'AA_HZLNM_languageCode'.
     */
    public void setAA_HZLNM_languageCode(
            final java.lang.String AA_HZLNM_languageCode) {
        this._AA_HZLNM_languageCode = AA_HZLNM_languageCode;
    }

    /**
     * Sets the value of field 'AA_HZNANC_numericAnalysisCode'.
     * 
     * @param AA_HZNANC_numericAnalysisCode the value of field
     * 'AA_HZNANC_numericAnalysisCode'.
     */
    public void setAA_HZNANC_numericAnalysisCode(
            final java.lang.String AA_HZNANC_numericAnalysisCode) {
        this._AA_HZNANC_numericAnalysisCode = AA_HZNANC_numericAnalysisCode;
    }

    /**
     * Sets the value of field
     * 'AA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement'.
     * 
     * @param AA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement
     * the value of field
     * 'AA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement'.
     */
    public void setAA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement(
            final java.lang.String AA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement) {
        this._AA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement = AA_HZNPE_numberOfPostingsNotYetPrintedOnAStatement;
    }

    /**
     * Sets the value of field
     * 'AA_HZNS3_numberOfReferenceCharactersForReconciliation'.
     * 
     * @param AA_HZNS3_numberOfReferenceCharactersForReconciliation
     * the value of field
     * 'AA_HZNS3_numberOfReferenceCharactersForReconciliation'.
     */
    public void setAA_HZNS3_numberOfReferenceCharactersForReconciliation(
            final java.lang.String AA_HZNS3_numberOfReferenceCharactersForReconciliation) {
        this._AA_HZNS3_numberOfReferenceCharactersForReconciliation = AA_HZNS3_numberOfReferenceCharactersForReconciliation;
    }

    /**
     * Sets the value of field 'AA_HZNTAD_taxAdviceTypeNextYear'.
     * 
     * @param AA_HZNTAD_taxAdviceTypeNextYear the value of field
     * 'AA_HZNTAD_taxAdviceTypeNextYear'.
     */
    public void setAA_HZNTAD_taxAdviceTypeNextYear(
            final java.lang.String AA_HZNTAD_taxAdviceTypeNextYear) {
        this._AA_HZNTAD_taxAdviceTypeNextYear = AA_HZNTAD_taxAdviceTypeNextYear;
    }

    /**
     * Sets the value of field 'AA_HZOAD_dateAccountOpened'.
     * 
     * @param AA_HZOAD_dateAccountOpened the value of field
     * 'AA_HZOAD_dateAccountOpened'.
     */
    public void setAA_HZOAD_dateAccountOpened(
            final java.lang.String AA_HZOAD_dateAccountOpened) {
        this._AA_HZOAD_dateAccountOpened = AA_HZOAD_dateAccountOpened;
    }

    /**
     * Sets the value of field 'AA_HZP1R_p1RatingForAccount'.
     * 
     * @param AA_HZP1R_p1RatingForAccount the value of field
     * 'AA_HZP1R_p1RatingForAccount'.
     */
    public void setAA_HZP1R_p1RatingForAccount(
            final java.lang.String AA_HZP1R_p1RatingForAccount) {
        this._AA_HZP1R_p1RatingForAccount = AA_HZP1R_p1RatingForAccount;
    }

    /**
     * Sets the value of field 'AA_HZP2R_p2RatingForAccount'.
     * 
     * @param AA_HZP2R_p2RatingForAccount the value of field
     * 'AA_HZP2R_p2RatingForAccount'.
     */
    public void setAA_HZP2R_p2RatingForAccount(
            final java.lang.String AA_HZP2R_p2RatingForAccount) {
        this._AA_HZP2R_p2RatingForAccount = AA_HZP2R_p2RatingForAccount;
    }

    /**
     * Sets the value of field 'AA_HZP3R_p3RatingForAccount'.
     * 
     * @param AA_HZP3R_p3RatingForAccount the value of field
     * 'AA_HZP3R_p3RatingForAccount'.
     */
    public void setAA_HZP3R_p3RatingForAccount(
            final java.lang.String AA_HZP3R_p3RatingForAccount) {
        this._AA_HZP3R_p3RatingForAccount = AA_HZP3R_p3RatingForAccount;
    }

    /**
     * Sets the value of field 'AA_HZP4R_p4RatingForAccount'.
     * 
     * @param AA_HZP4R_p4RatingForAccount the value of field
     * 'AA_HZP4R_p4RatingForAccount'.
     */
    public void setAA_HZP4R_p4RatingForAccount(
            final java.lang.String AA_HZP4R_p4RatingForAccount) {
        this._AA_HZP4R_p4RatingForAccount = AA_HZP4R_p4RatingForAccount;
    }

    /**
     * Sets the value of field 'AA_HZP5R_p5RatingForAccount'.
     * 
     * @param AA_HZP5R_p5RatingForAccount the value of field
     * 'AA_HZP5R_p5RatingForAccount'.
     */
    public void setAA_HZP5R_p5RatingForAccount(
            final java.lang.String AA_HZP5R_p5RatingForAccount) {
        this._AA_HZP5R_p5RatingForAccount = AA_HZP5R_p5RatingForAccount;
    }

    /**
     * Sets the value of field 'AA_HZPAB_sourceOriginalBranch'.
     * 
     * @param AA_HZPAB_sourceOriginalBranch the value of field
     * 'AA_HZPAB_sourceOriginalBranch'.
     */
    public void setAA_HZPAB_sourceOriginalBranch(
            final java.lang.String AA_HZPAB_sourceOriginalBranch) {
        this._AA_HZPAB_sourceOriginalBranch = AA_HZPAB_sourceOriginalBranch;
    }

    /**
     * Sets the value of field 'AA_HZPCHD_branchChangeDate'.
     * 
     * @param AA_HZPCHD_branchChangeDate the value of field
     * 'AA_HZPCHD_branchChangeDate'.
     */
    public void setAA_HZPCHD_branchChangeDate(
            final java.lang.String AA_HZPCHD_branchChangeDate) {
        this._AA_HZPCHD_branchChangeDate = AA_HZPCHD_branchChangeDate;
    }

    /**
     * Sets the value of field 'AA_HZREFA_settlementReference'.
     * 
     * @param AA_HZREFA_settlementReference the value of field
     * 'AA_HZREFA_settlementReference'.
     */
    public void setAA_HZREFA_settlementReference(
            final java.lang.String AA_HZREFA_settlementReference) {
        this._AA_HZREFA_settlementReference = AA_HZREFA_settlementReference;
    }

    /**
     * Sets the value of field 'AA_HZREF_lastCpReference'.
     * 
     * @param AA_HZREF_lastCpReference the value of field
     * 'AA_HZREF_lastCpReference'.
     */
    public void setAA_HZREF_lastCpReference(
            final java.lang.String AA_HZREF_lastCpReference) {
        this._AA_HZREF_lastCpReference = AA_HZREF_lastCpReference;
    }

    /**
     * Sets the value of field
     * 'AA_HZRETP_retentionPeriod00None011MthSysDflt'.
     * 
     * @param AA_HZRETP_retentionPeriod00None011MthSysDflt the
     * value of field 'AA_HZRETP_retentionPeriod00None011MthSysDflt'
     */
    public void setAA_HZRETP_retentionPeriod00None011MthSysDflt(
            final java.lang.String AA_HZRETP_retentionPeriod00None011MthSysDflt) {
        this._AA_HZRETP_retentionPeriod00None011MthSysDflt = AA_HZRETP_retentionPeriod00None011MthSysDflt;
    }

    /**
     * Sets the value of field 'AA_HZSAC_sundryAnalysisCode'.
     * 
     * @param AA_HZSAC_sundryAnalysisCode the value of field
     * 'AA_HZSAC_sundryAnalysisCode'.
     */
    public void setAA_HZSAC_sundryAnalysisCode(
            final java.lang.String AA_HZSAC_sundryAnalysisCode) {
        this._AA_HZSAC_sundryAnalysisCode = AA_HZSAC_sundryAnalysisCode;
    }

    /**
     * Sets the value of field 'AA_HZSFC_statementFrequencyCode'.
     * 
     * @param AA_HZSFC_statementFrequencyCode the value of field
     * 'AA_HZSFC_statementFrequencyCode'.
     */
    public void setAA_HZSFC_statementFrequencyCode(
            final java.lang.String AA_HZSFC_statementFrequencyCode) {
        this._AA_HZSFC_statementFrequencyCode = AA_HZSFC_statementFrequencyCode;
    }

    /**
     * Sets the value of field 'AA_HZSHNA_arabicShortName'.
     * 
     * @param AA_HZSHNA_arabicShortName the value of field
     * 'AA_HZSHNA_arabicShortName'.
     */
    public void setAA_HZSHNA_arabicShortName(
            final java.lang.String AA_HZSHNA_arabicShortName) {
        this._AA_HZSHNA_arabicShortName = AA_HZSHNA_arabicShortName;
    }

    /**
     * Sets the value of field 'AA_HZSHN_accountShortName'.
     * 
     * @param AA_HZSHN_accountShortName the value of field
     * 'AA_HZSHN_accountShortName'.
     */
    public void setAA_HZSHN_accountShortName(
            final java.lang.String AA_HZSHN_accountShortName) {
        this._AA_HZSHN_accountShortName = AA_HZSHN_accountShortName;
    }

    /**
     * Sets the value of field 'AA_HZSN20_externalAccountNumber'.
     * 
     * @param AA_HZSN20_externalAccountNumber the value of field
     * 'AA_HZSN20_externalAccountNumber'.
     */
    public void setAA_HZSN20_externalAccountNumber(
            final java.lang.String AA_HZSN20_externalAccountNumber) {
        this._AA_HZSN20_externalAccountNumber = AA_HZSN20_externalAccountNumber;
    }

    /**
     * Sets the value of field
     * 'AA_HZSNA_chargeNominatedAccountArabicShortName'.
     * 
     * @param AA_HZSNA_chargeNominatedAccountArabicShortName the
     * value of field
     * 'AA_HZSNA_chargeNominatedAccountArabicShortName'.
     */
    public void setAA_HZSNA_chargeNominatedAccountArabicShortName(
            final java.lang.String AA_HZSNA_chargeNominatedAccountArabicShortName) {
        this._AA_HZSNA_chargeNominatedAccountArabicShortName = AA_HZSNA_chargeNominatedAccountArabicShortName;
    }

    /**
     * Sets the value of field
     * 'AA_HZSNCA_creditNominatedAccountArabicShortName'.
     * 
     * @param AA_HZSNCA_creditNominatedAccountArabicShortName the
     * value of field
     * 'AA_HZSNCA_creditNominatedAccountArabicShortName'.
     */
    public void setAA_HZSNCA_creditNominatedAccountArabicShortName(
            final java.lang.String AA_HZSNCA_creditNominatedAccountArabicShortName) {
        this._AA_HZSNCA_creditNominatedAccountArabicShortName = AA_HZSNCA_creditNominatedAccountArabicShortName;
    }

    /**
     * Sets the value of field
     * 'AA_HZSNDA_debitNominatedAccountArabicShortName'.
     * 
     * @param AA_HZSNDA_debitNominatedAccountArabicShortName the
     * value of field
     * 'AA_HZSNDA_debitNominatedAccountArabicShortName'.
     */
    public void setAA_HZSNDA_debitNominatedAccountArabicShortName(
            final java.lang.String AA_HZSNDA_debitNominatedAccountArabicShortName) {
        this._AA_HZSNDA_debitNominatedAccountArabicShortName = AA_HZSNDA_debitNominatedAccountArabicShortName;
    }

    /**
     * Sets the value of field
     * 'AA_HZSNGA_chargeNominatedAccountArabicShortName'.
     * 
     * @param AA_HZSNGA_chargeNominatedAccountArabicShortName the
     * value of field
     * 'AA_HZSNGA_chargeNominatedAccountArabicShortName'.
     */
    public void setAA_HZSNGA_chargeNominatedAccountArabicShortName(
            final java.lang.String AA_HZSNGA_chargeNominatedAccountArabicShortName) {
        this._AA_HZSNGA_chargeNominatedAccountArabicShortName = AA_HZSNGA_chargeNominatedAccountArabicShortName;
    }

    /**
     * Sets the value of field
     * 'AA_HZSNMA_masterInterestAccountArabicShortName'.
     * 
     * @param AA_HZSNMA_masterInterestAccountArabicShortName the
     * value of field
     * 'AA_HZSNMA_masterInterestAccountArabicShortName'.
     */
    public void setAA_HZSNMA_masterInterestAccountArabicShortName(
            final java.lang.String AA_HZSNMA_masterInterestAccountArabicShortName) {
        this._AA_HZSNMA_masterInterestAccountArabicShortName = AA_HZSNMA_masterInterestAccountArabicShortName;
    }

    /**
     * Sets the value of field
     * 'AA_HZSNM_masterInterestAccountShortName'.
     * 
     * @param AA_HZSNM_masterInterestAccountShortName the value of
     * field 'AA_HZSNM_masterInterestAccountShortName'.
     */
    public void setAA_HZSNM_masterInterestAccountShortName(
            final java.lang.String AA_HZSNM_masterInterestAccountShortName) {
        this._AA_HZSNM_masterInterestAccountShortName = AA_HZSNM_masterInterestAccountShortName;
    }

    /**
     * Sets the value of field
     * 'AA_HZSNNC_creditNominatedAccountShortName'.
     * 
     * @param AA_HZSNNC_creditNominatedAccountShortName the value
     * of field 'AA_HZSNNC_creditNominatedAccountShortName'.
     */
    public void setAA_HZSNNC_creditNominatedAccountShortName(
            final java.lang.String AA_HZSNNC_creditNominatedAccountShortName) {
        this._AA_HZSNNC_creditNominatedAccountShortName = AA_HZSNNC_creditNominatedAccountShortName;
    }

    /**
     * Sets the value of field
     * 'AA_HZSNND_debitNominatedAccountShortName'.
     * 
     * @param AA_HZSNND_debitNominatedAccountShortName the value of
     * field 'AA_HZSNND_debitNominatedAccountShortName'.
     */
    public void setAA_HZSNND_debitNominatedAccountShortName(
            final java.lang.String AA_HZSNND_debitNominatedAccountShortName) {
        this._AA_HZSNND_debitNominatedAccountShortName = AA_HZSNND_debitNominatedAccountShortName;
    }

    /**
     * Sets the value of field
     * 'AA_HZSNNG_chargeNominatedAccountShortName'.
     * 
     * @param AA_HZSNNG_chargeNominatedAccountShortName the value
     * of field 'AA_HZSNNG_chargeNominatedAccountShortName'.
     */
    public void setAA_HZSNNG_chargeNominatedAccountShortName(
            final java.lang.String AA_HZSNNG_chargeNominatedAccountShortName) {
        this._AA_HZSNNG_chargeNominatedAccountShortName = AA_HZSNNG_chargeNominatedAccountShortName;
    }

    /**
     * Sets the value of field
     * 'AA_HZSNN_chargeNominatedAccountShortName'.
     * 
     * @param AA_HZSNN_chargeNominatedAccountShortName the value of
     * field 'AA_HZSNN_chargeNominatedAccountShortName'.
     */
    public void setAA_HZSNN_chargeNominatedAccountShortName(
            final java.lang.String AA_HZSNN_chargeNominatedAccountShortName) {
        this._AA_HZSNN_chargeNominatedAccountShortName = AA_HZSNN_chargeNominatedAccountShortName;
    }

    /**
     * Sets the value of field 'AA_HZSTA_status'.
     * 
     * @param AA_HZSTA_status the value of field 'AA_HZSTA_status'.
     */
    public void setAA_HZSTA_status(
            final java.lang.String AA_HZSTA_status) {
        this._AA_HZSTA_status = AA_HZSTA_status;
    }

    /**
     * Sets the value of field 'AA_HZSTML_lastStatementDate'.
     * 
     * @param AA_HZSTML_lastStatementDate the value of field
     * 'AA_HZSTML_lastStatementDate'.
     */
    public void setAA_HZSTML_lastStatementDate(
            final java.lang.String AA_HZSTML_lastStatementDate) {
        this._AA_HZSTML_lastStatementDate = AA_HZSTML_lastStatementDate;
    }

    /**
     * Sets the value of field 'AA_HZSTMN_nextStatementDate'.
     * 
     * @param AA_HZSTMN_nextStatementDate the value of field
     * 'AA_HZSTMN_nextStatementDate'.
     */
    public void setAA_HZSTMN_nextStatementDate(
            final java.lang.String AA_HZSTMN_nextStatementDate) {
        this._AA_HZSTMN_nextStatementDate = AA_HZSTMN_nextStatementDate;
    }

    /**
     * Sets the value of field 'AA_HZSTNL_lastStatementNumber'.
     * 
     * @param AA_HZSTNL_lastStatementNumber the value of field
     * 'AA_HZSTNL_lastStatementNumber'.
     */
    public void setAA_HZSTNL_lastStatementNumber(
            final java.lang.String AA_HZSTNL_lastStatementNumber) {
        this._AA_HZSTNL_lastStatementNumber = AA_HZSTNL_lastStatementNumber;
    }

    /**
     * Sets the value of field 'AA_HZTADT_taxAdviceType'.
     * 
     * @param AA_HZTADT_taxAdviceType the value of field
     * 'AA_HZTADT_taxAdviceType'.
     */
    public void setAA_HZTADT_taxAdviceType(
            final java.lang.String AA_HZTADT_taxAdviceType) {
        this._AA_HZTADT_taxAdviceType = AA_HZTADT_taxAdviceType;
    }

    /**
     * Sets the value of field 'AA_HZTRM_transferMethod'.
     * 
     * @param AA_HZTRM_transferMethod the value of field
     * 'AA_HZTRM_transferMethod'.
     */
    public void setAA_HZTRM_transferMethod(
            final java.lang.String AA_HZTRM_transferMethod) {
        this._AA_HZTRM_transferMethod = AA_HZTRM_transferMethod;
    }

    /**
     * Sets the value of field 'AA_HZUNMW_unmaturedWithdrawal'.
     * 
     * @param AA_HZUNMW_unmaturedWithdrawal the value of field
     * 'AA_HZUNMW_unmaturedWithdrawal'.
     */
    public void setAA_HZUNMW_unmaturedWithdrawal(
            final java.lang.String AA_HZUNMW_unmaturedWithdrawal) {
        this._AA_HZUNMW_unmaturedWithdrawal = AA_HZUNMW_unmaturedWithdrawal;
    }

    /**
     * Sets the value of field
     * 'AA_HZXMD_transferMethodDescription'.
     * 
     * @param AA_HZXMD_transferMethodDescription the value of field
     * 'AA_HZXMD_transferMethodDescription'.
     */
    public void setAA_HZXMD_transferMethodDescription(
            final java.lang.String AA_HZXMD_transferMethodDescription) {
        this._AA_HZXMD_transferMethodDescription = AA_HZXMD_transferMethodDescription;
    }

    /**
     * Sets the value of field 'AA_HZYFON_fontisAccount'.
     * 
     * @param AA_HZYFON_fontisAccount the value of field
     * 'AA_HZYFON_fontisAccount'.
     */
    public void setAA_HZYFON_fontisAccount(
            final java.lang.String AA_HZYFON_fontisAccount) {
        this._AA_HZYFON_fontisAccount = AA_HZYFON_fontisAccount;
    }

    /**
     * Sets the value of field 'HH_DED_defaultExpiryDate'.
     * 
     * @param HH_DED_defaultExpiryDate the value of field
     * 'HH_DED_defaultExpiryDate'.
     */
    public void setHH_DED_defaultExpiryDate(
            final java.lang.String HH_DED_defaultExpiryDate) {
        this._HH_DED_defaultExpiryDate = HH_DED_defaultExpiryDate;
    }

    /**
     * Sets the value of field 'HH_HRC_holdCode'.
     * 
     * @param HH_HRC_holdCode the value of field 'HH_HRC_holdCode'.
     */
    public void setHH_HRC_holdCode(
            final java.lang.String HH_HRC_holdCode) {
        this._HH_HRC_holdCode = HH_HRC_holdCode;
    }

    /**
     * Sets the value of field 'HH_HRD_holdDescription'.
     * 
     * @param HH_HRD_holdDescription the value of field
     * 'HH_HRD_holdDescription'.
     */
    public void setHH_HRD_holdDescription(
            final java.lang.String HH_HRD_holdDescription) {
        this._HH_HRD_holdDescription = HH_HRD_holdDescription;
    }

    /**
     * Method unmarshalXAA.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled bf.com.misys.eq4.service.xaa.XAA
     */
    public static bf.com.misys.eq4.service.xaa.XAA unmarshalXAA(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (bf.com.misys.eq4.service.xaa.XAA) Unmarshaller.unmarshal(bf.com.misys.eq4.service.xaa.XAA.class, reader);
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
