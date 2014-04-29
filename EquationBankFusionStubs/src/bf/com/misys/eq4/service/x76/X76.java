/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1.1</a>, using an XML
 * Schema.
 * $Id: X76.java 7094 2010-04-28 10:58:02Z goldsmc1 $
 */

package bf.com.misys.eq4.service.x76;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class X76.
 * 
 * @version $Revision$ $Date$
 */
public class X76 implements java.io.Serializable {


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

    /**
     * Field _ABE_HZAB_accountBranch.
     */
    private java.lang.String _ABE_HZAB_accountBranch;

    /**
     * Field _ABE_HZAN_basicPartOfAccountNumber.
     */
    private java.lang.String _ABE_HZAN_basicPartOfAccountNumber;

    /**
     * Field _ABE_HZAS_accountSuffix.
     */
    private java.lang.String _ABE_HZAS_accountSuffix;

    /**
     * Field _ABE_HZSHN_accountShortName.
     */
    private java.lang.String _ABE_HZSHN_accountShortName;

    /**
     * Field _ABE_HZSHNA_arabicShortName.
     */
    private java.lang.String _ABE_HZSHNA_arabicShortName;

    /**
     * Field _ABE_HZCUS_customerMnemonic.
     */
    private java.lang.String _ABE_HZCUS_customerMnemonic;

    /**
     * Field _ABE_HZCLC_customerLocation.
     */
    private java.lang.String _ABE_HZCLC_customerLocation;

    /**
     * Field _ABE_HZCUN_customerFullName.
     */
    private java.lang.String _ABE_HZCUN_customerFullName;

    /**
     * Field _ABE_HZCUNA_arabicCustomerFullName.
     */
    private java.lang.String _ABE_HZCUNA_arabicCustomerFullName;

    /**
     * Field _ABE_HZACT_accountType.
     */
    private java.lang.String _ABE_HZACT_accountType;

    /**
     * Field _ABE_HZAPP_owningApplicationCode.
     */
    private java.lang.String _ABE_HZAPP_owningApplicationCode;

    /**
     * Field _ABE_HZACS_accountStructure.
     */
    private java.lang.String _ABE_HZACS_accountStructure;

    /**
     * Field _ABE_HZCCY_currencyMnemonic.
     */
    private java.lang.String _ABE_HZCCY_currencyMnemonic;

    /**
     * Field _ABE_HZCED_currencyEditField.
     */
    private java.lang.String _ABE_HZCED_currencyEditField;

    /**
     * Field _ABE_HZAI47_internalNonCustomerAccount.
     */
    private java.lang.String _ABE_HZAI47_internalNonCustomerAccount;

    /**
     * Field _ABE_HZAI14_deceasedOrLiquidated.
     */
    private java.lang.String _ABE_HZAI14_deceasedOrLiquidated;

    /**
     * Field _ABE_HZAI17_accountStatusBlocked.
     */
    private java.lang.String _ABE_HZAI17_accountStatusBlocked;

    /**
     * Field _ABE_HZAI20_accountStatusInactive.
     */
    private java.lang.String _ABE_HZAI20_accountStatusInactive;

    /**
     * Field _ABE_HZAI30_accountClosing.
     */
    private java.lang.String _ABE_HZAI30_accountClosing;

    /**
     * Field _ABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay.
     */
    private java.lang.String _ABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay;

    /**
     * Field _ABE_HZCTP_customerType.
     */
    private java.lang.String _ABE_HZCTP_customerType;

    /**
     * Field _ABE_HZACD_analysisCode.
     */
    private java.lang.String _ABE_HZACD_analysisCode;

    /**
     * Field _ABE_HZSAC_sundryAnalysisCode.
     */
    private java.lang.String _ABE_HZSAC_sundryAnalysisCode;

    /**
     * Field _ABE_HZNANC_numericAnalysisCode.
     */
    private java.lang.String _ABE_HZNANC_numericAnalysisCode;

    /**
     * Field _ABE_HZCNAL_residenceCountry.
     */
    private java.lang.String _ABE_HZCNAL_residenceCountry;

    /**
     * Field _ABE_HZCNAR_riskCountry.
     */
    private java.lang.String _ABE_HZCNAR_riskCountry;

    /**
     * Field _ABE_HZCNAP_parentCountry.
     */
    private java.lang.String _ABE_HZCNAP_parentCountry;

    /**
     * Field _ABE_HZSFC_statementFrequencyCode.
     */
    private java.lang.String _ABE_HZSFC_statementFrequencyCode;

    /**
     * Field _ABE_HZBALS_balanceOnThePreviousStatement.
     */
    private java.lang.String _ABE_HZBALS_balanceOnThePreviousStatement;

    /**
     * Field _ABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement.
     */
    private java.lang.String _ABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement;

    /**
     * Field _ABE_HZSTML_lastStatementDate.
     */
    private java.lang.String _ABE_HZSTML_lastStatementDate;

    /**
     * Field _ABE_HZSTMN_nextStatementDate.
     */
    private java.lang.String _ABE_HZSTMN_nextStatementDate;

    /**
     * Field _ABE_HZSTNL_lastStatementNumber.
     */
    private java.lang.String _ABE_HZSTNL_lastStatementNumber;

    /**
     * Field _ABE_HZRETP_retentionPeriod00None011MthSysDflt.
     */
    private java.lang.String _ABE_HZRETP_retentionPeriod00None011MthSysDflt;

    /**
     * Field _ABE_HZNS3_numberOfReferenceCharactersForReconciliation
     */
    private java.lang.String _ABE_HZNS3_numberOfReferenceCharactersForReconciliation;

    /**
     * Field _ABE_HZOAD_dateAccountOpened.
     */
    private java.lang.String _ABE_HZOAD_dateAccountOpened;

    /**
     * Field _ABE_HZDLE_dateOfLastCustomerGeneratedEntry.
     */
    private java.lang.String _ABE_HZDLE_dateOfLastCustomerGeneratedEntry;

    /**
     * Field _ABE_HZCAD_dateAccountClosed.
     */
    private java.lang.String _ABE_HZCAD_dateAccountClosed;

    /**
     * Field _ABE_HZDLM_dateLastMaintained.
     */
    private java.lang.String _ABE_HZDLM_dateLastMaintained;

    /**
     * Field _ABE_HZP1R_p1RatingForAccount.
     */
    private java.lang.String _ABE_HZP1R_p1RatingForAccount;

    /**
     * Field _ABE_HZP2R_p2RatingForAccount.
     */
    private java.lang.String _ABE_HZP2R_p2RatingForAccount;

    /**
     * Field _ABE_HZP3R_p3RatingForAccount.
     */
    private java.lang.String _ABE_HZP3R_p3RatingForAccount;

    /**
     * Field _ABE_HZP4R_p4RatingForAccount.
     */
    private java.lang.String _ABE_HZP4R_p4RatingForAccount;

    /**
     * Field _ABE_HZP5R_p5RatingForAccount.
     */
    private java.lang.String _ABE_HZP5R_p5RatingForAccount;

    /**
     * Field _ABE_HZC1R_c1RatingForAccount.
     */
    private java.lang.String _ABE_HZC1R_c1RatingForAccount;

    /**
     * Field _ABE_HZC2R_c2RatingForAccount.
     */
    private java.lang.String _ABE_HZC2R_c2RatingForAccount;

    /**
     * Field _ABE_HZC3R_c3RatingForAccount.
     */
    private java.lang.String _ABE_HZC3R_c3RatingForAccount;

    /**
     * Field _ABE_HZC4R_c4RatingForAccount.
     */
    private java.lang.String _ABE_HZC4R_c4RatingForAccount;

    /**
     * Field _ABE_HZC5R_c5RatingForAccount.
     */
    private java.lang.String _ABE_HZC5R_c5RatingForAccount;

    /**
     * Field _ABE_HZACO_accountOfficer.
     */
    private java.lang.String _ABE_HZACO_accountOfficer;

    /**
     * Field _ABE_HZLNM_languageCode.
     */
    private java.lang.String _ABE_HZLNM_languageCode;

    /**
     * Field _ABE_HZAI33_printChargesStatement.
     */
    private java.lang.String _ABE_HZAI33_printChargesStatement;

    /**
     * Field _ABE_HZCSFC_chargesStatementFrequency.
     */
    private java.lang.String _ABE_HZCSFC_chargesStatementFrequency;

    /**
     * Field _ABE_HZCSTL_lastChargesStatementDate.
     */
    private java.lang.String _ABE_HZCSTL_lastChargesStatementDate;

    /**
     * Field _ABE_HZCSTN_nextChargesStatementDate.
     */
    private java.lang.String _ABE_HZCSTN_nextChargesStatementDate;

    /**
     * Field _ABE_HZABND_debitNominatedAccountBranch.
     */
    private java.lang.String _ABE_HZABND_debitNominatedAccountBranch;

    /**
     * Field _ABE_HZANND_debitNominatedAccountBasic.
     */
    private java.lang.String _ABE_HZANND_debitNominatedAccountBasic;

    /**
     * Field _ABE_HZASND_debitNominatedAccountSuffix.
     */
    private java.lang.String _ABE_HZASND_debitNominatedAccountSuffix;

    /**
     * Field _ABE_HZSNND_debitNominatedAccountShortName.
     */
    private java.lang.String _ABE_HZSNND_debitNominatedAccountShortName;

    /**
     * Field _ABE_HZSNDA_debitNominatedAccountArabicShortName.
     */
    private java.lang.String _ABE_HZSNDA_debitNominatedAccountArabicShortName;

    /**
     * Field _ABE_HZABNC_creditNominatedAccountBranch.
     */
    private java.lang.String _ABE_HZABNC_creditNominatedAccountBranch;

    /**
     * Field _ABE_HZANNC_creditNominatedAccountBasic.
     */
    private java.lang.String _ABE_HZANNC_creditNominatedAccountBasic;

    /**
     * Field _ABE_HZASNC_creditNominatedAccountSuffix.
     */
    private java.lang.String _ABE_HZASNC_creditNominatedAccountSuffix;

    /**
     * Field _ABE_HZSNNC_creditNominatedAccountShortName.
     */
    private java.lang.String _ABE_HZSNNC_creditNominatedAccountShortName;

    /**
     * Field _ABE_HZSNCA_creditNominatedAccountArabicShortName.
     */
    private java.lang.String _ABE_HZSNCA_creditNominatedAccountArabicShortName;

    /**
     * Field _ABE_HZABNG_chargeNominatedAccountBranch.
     */
    private java.lang.String _ABE_HZABNG_chargeNominatedAccountBranch;

    /**
     * Field _ABE_HZANNG_chargeNominatedAccountBasic.
     */
    private java.lang.String _ABE_HZANNG_chargeNominatedAccountBasic;

    /**
     * Field _ABE_HZASNG_chargeNominatedAccountSuffix.
     */
    private java.lang.String _ABE_HZASNG_chargeNominatedAccountSuffix;

    /**
     * Field _ABE_HZSNNG_chargeNominatedAccountShortName.
     */
    private java.lang.String _ABE_HZSNNG_chargeNominatedAccountShortName;

    /**
     * Field _ABE_HZSNGA_chargeNominatedAccountArabicShortName.
     */
    private java.lang.String _ABE_HZSNGA_chargeNominatedAccountArabicShortName;

    /**
     * Field _ABE_HZABN_chargeNominatedAccountBranch.
     */
    private java.lang.String _ABE_HZABN_chargeNominatedAccountBranch;

    /**
     * Field _ABE_HZANN_chargeNominatedAccountBasic.
     */
    private java.lang.String _ABE_HZANN_chargeNominatedAccountBasic;

    /**
     * Field _ABE_HZASN_chargeNominatedAccountSuffix.
     */
    private java.lang.String _ABE_HZASN_chargeNominatedAccountSuffix;

    /**
     * Field _ABE_HZSNN_chargeNominatedAccountShortName.
     */
    private java.lang.String _ABE_HZSNN_chargeNominatedAccountShortName;

    /**
     * Field _ABE_HZSNA_chargeNominatedAccountArabicShortName.
     */
    private java.lang.String _ABE_HZSNA_chargeNominatedAccountArabicShortName;

    /**
     * Field _ABE_HZABM_masterInterestAccountBranch.
     */
    private java.lang.String _ABE_HZABM_masterInterestAccountBranch;

    /**
     * Field _ABE_HZANM_masterInterestAccountBasic.
     */
    private java.lang.String _ABE_HZANM_masterInterestAccountBasic;

    /**
     * Field _ABE_HZASM_masterInterestAccountSuffix.
     */
    private java.lang.String _ABE_HZASM_masterInterestAccountSuffix;

    /**
     * Field _ABE_HZSNM_masterInterestAccountShortName.
     */
    private java.lang.String _ABE_HZSNM_masterInterestAccountShortName;

    /**
     * Field _ABE_HZSNMA_masterInterestAccountArabicShortName.
     */
    private java.lang.String _ABE_HZSNMA_masterInterestAccountArabicShortName;

    /**
     * Field _ABE_HZAIC7_jointAccount.
     */
    private java.lang.String _ABE_HZAIC7_jointAccount;

    /**
     * Field _ABE_HZYFON_fontisAccount.
     */
    private java.lang.String _ABE_HZYFON_fontisAccount;

    /**
     * Field _ABE_HZDFRQ_fontisDownloadFrequency.
     */
    private java.lang.String _ABE_HZDFRQ_fontisDownloadFrequency;

    /**
     * Field _ABE_HZSN20_externalAccountNumber.
     */
    private java.lang.String _ABE_HZSN20_externalAccountNumber;

    /**
     * Field _ABE_HZCAN_accountNumber.
     */
    private java.lang.String _ABE_HZCAN_accountNumber;

    /**
     * Field _ABE_HZUNMW_unmaturedWithdrawal.
     */
    private java.lang.String _ABE_HZUNMW_unmaturedWithdrawal;

    /**
     * Field _ABE_HZAII7_noticeAC.
     */
    private java.lang.String _ABE_HZAII7_noticeAC;

    /**
     * Field _ABE_HZCLS_closingWithdrawal.
     */
    private java.lang.String _ABE_HZCLS_closingWithdrawal;

    /**
     * Field _ABE_HZPAB_sourceOriginalBranch.
     */
    private java.lang.String _ABE_HZPAB_sourceOriginalBranch;

    /**
     * Field _ABE_HZPCHD_branchChangeDate.
     */
    private java.lang.String _ABE_HZPCHD_branchChangeDate;

    /**
     * Field _ABE_HZABD_previousDescription.
     */
    private java.lang.String _ABE_HZABD_previousDescription;

    /**
     * Field _ABE_HZDFRM_fontisDownloadFrequencyMeridian.
     */
    private java.lang.String _ABE_HZDFRM_fontisDownloadFrequencyMeridian;

    /**
     * Field _ABE_HZTRM_transferMethod.
     */
    private java.lang.String _ABE_HZTRM_transferMethod;

    /**
     * Field _ABE_HZXMD_transferMethodDescription.
     */
    private java.lang.String _ABE_HZXMD_transferMethodDescription;

    /**
     * Field _ABE_HZHLD_held.
     */
    private java.lang.String _ABE_HZHLD_held;

    /**
     * Field _ABE_HZAUT_authorised.
     */
    private java.lang.String _ABE_HZAUT_authorised;

    /**
     * Field _ABE_HZSTA_status.
     */
    private java.lang.String _ABE_HZSTA_status;

    /**
     * Field _ABE_HZDLMZ_instructionDateLastMaintained.
     */
    private java.lang.String _ABE_HZDLMZ_instructionDateLastMaintained;

    /**
     * Field _ABE_HZREF_lastCpReference.
     */
    private java.lang.String _ABE_HZREF_lastCpReference;

    /**
     * Field _ABE_HZASIP_aCSettlInstPresent.
     */
    private java.lang.String _ABE_HZASIP_aCSettlInstPresent;

    /**
     * Field _ABE_HZREFA_settlementReference.
     */
    private java.lang.String _ABE_HZREFA_settlementReference;

    /**
     * Field _ABE_HZTADT_taxAdviceType.
     */
    private java.lang.String _ABE_HZTADT_taxAdviceType;

    /**
     * Field _ABE_HZNTAD_taxAdviceTypeNextYear.
     */
    private java.lang.String _ABE_HZNTAD_taxAdviceTypeNextYear;

    /**
     * Field _ABE_HZAUNO_lastAuditLetterNumber.
     */
    private java.lang.String _ABE_HZAUNO_lastAuditLetterNumber;

    /**
     * Field _ABE_HZAUDT_lastAuditLetterDate.
     */
    private java.lang.String _ABE_HZAUDT_lastAuditLetterDate;

    /**
     * Field _CRG1_crg1.
     */
    private java.lang.String _CRG1_crg1;


      //----------------/
     //- Constructors -/
    //----------------/

    public X76() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'ABE_HZABD_previousDescription'.
     * 
     * @return the value of field 'ABE_HZABD_previousDescription'.
     */
    public java.lang.String getABE_HZABD_previousDescription(
    ) {
        return this._ABE_HZABD_previousDescription;
    }

    /**
     * Returns the value of field
     * 'ABE_HZABM_masterInterestAccountBranch'.
     * 
     * @return the value of field
     * 'ABE_HZABM_masterInterestAccountBranch'.
     */
    public java.lang.String getABE_HZABM_masterInterestAccountBranch(
    ) {
        return this._ABE_HZABM_masterInterestAccountBranch;
    }

    /**
     * Returns the value of field
     * 'ABE_HZABNC_creditNominatedAccountBranch'.
     * 
     * @return the value of field
     * 'ABE_HZABNC_creditNominatedAccountBranch'.
     */
    public java.lang.String getABE_HZABNC_creditNominatedAccountBranch(
    ) {
        return this._ABE_HZABNC_creditNominatedAccountBranch;
    }

    /**
     * Returns the value of field
     * 'ABE_HZABND_debitNominatedAccountBranch'.
     * 
     * @return the value of field
     * 'ABE_HZABND_debitNominatedAccountBranch'.
     */
    public java.lang.String getABE_HZABND_debitNominatedAccountBranch(
    ) {
        return this._ABE_HZABND_debitNominatedAccountBranch;
    }

    /**
     * Returns the value of field
     * 'ABE_HZABNG_chargeNominatedAccountBranch'.
     * 
     * @return the value of field
     * 'ABE_HZABNG_chargeNominatedAccountBranch'.
     */
    public java.lang.String getABE_HZABNG_chargeNominatedAccountBranch(
    ) {
        return this._ABE_HZABNG_chargeNominatedAccountBranch;
    }

    /**
     * Returns the value of field
     * 'ABE_HZABN_chargeNominatedAccountBranch'.
     * 
     * @return the value of field
     * 'ABE_HZABN_chargeNominatedAccountBranch'.
     */
    public java.lang.String getABE_HZABN_chargeNominatedAccountBranch(
    ) {
        return this._ABE_HZABN_chargeNominatedAccountBranch;
    }

    /**
     * Returns the value of field 'ABE_HZAB_accountBranch'.
     * 
     * @return the value of field 'ABE_HZAB_accountBranch'.
     */
    public java.lang.String getABE_HZAB_accountBranch(
    ) {
        return this._ABE_HZAB_accountBranch;
    }

    /**
     * Returns the value of field 'ABE_HZACD_analysisCode'.
     * 
     * @return the value of field 'ABE_HZACD_analysisCode'.
     */
    public java.lang.String getABE_HZACD_analysisCode(
    ) {
        return this._ABE_HZACD_analysisCode;
    }

    /**
     * Returns the value of field 'ABE_HZACO_accountOfficer'.
     * 
     * @return the value of field 'ABE_HZACO_accountOfficer'.
     */
    public java.lang.String getABE_HZACO_accountOfficer(
    ) {
        return this._ABE_HZACO_accountOfficer;
    }

    /**
     * Returns the value of field 'ABE_HZACS_accountStructure'.
     * 
     * @return the value of field 'ABE_HZACS_accountStructure'.
     */
    public java.lang.String getABE_HZACS_accountStructure(
    ) {
        return this._ABE_HZACS_accountStructure;
    }

    /**
     * Returns the value of field 'ABE_HZACT_accountType'.
     * 
     * @return the value of field 'ABE_HZACT_accountType'.
     */
    public java.lang.String getABE_HZACT_accountType(
    ) {
        return this._ABE_HZACT_accountType;
    }

    /**
     * Returns the value of field
     * 'ABE_HZAI14_deceasedOrLiquidated'.
     * 
     * @return the value of field 'ABE_HZAI14_deceasedOrLiquidated'.
     */
    public java.lang.String getABE_HZAI14_deceasedOrLiquidated(
    ) {
        return this._ABE_HZAI14_deceasedOrLiquidated;
    }

    /**
     * Returns the value of field
     * 'ABE_HZAI17_accountStatusBlocked'.
     * 
     * @return the value of field 'ABE_HZAI17_accountStatusBlocked'.
     */
    public java.lang.String getABE_HZAI17_accountStatusBlocked(
    ) {
        return this._ABE_HZAI17_accountStatusBlocked;
    }

    /**
     * Returns the value of field
     * 'ABE_HZAI20_accountStatusInactive'.
     * 
     * @return the value of field 'ABE_HZAI20_accountStatusInactive'
     */
    public java.lang.String getABE_HZAI20_accountStatusInactive(
    ) {
        return this._ABE_HZAI20_accountStatusInactive;
    }

    /**
     * Returns the value of field 'ABE_HZAI30_accountClosing'.
     * 
     * @return the value of field 'ABE_HZAI30_accountClosing'.
     */
    public java.lang.String getABE_HZAI30_accountClosing(
    ) {
        return this._ABE_HZAI30_accountClosing;
    }

    /**
     * Returns the value of field
     * 'ABE_HZAI33_printChargesStatement'.
     * 
     * @return the value of field 'ABE_HZAI33_printChargesStatement'
     */
    public java.lang.String getABE_HZAI33_printChargesStatement(
    ) {
        return this._ABE_HZAI33_printChargesStatement;
    }

    /**
     * Returns the value of field
     * 'ABE_HZAI47_internalNonCustomerAccount'.
     * 
     * @return the value of field
     * 'ABE_HZAI47_internalNonCustomerAccount'.
     */
    public java.lang.String getABE_HZAI47_internalNonCustomerAccount(
    ) {
        return this._ABE_HZAI47_internalNonCustomerAccount;
    }

    /**
     * Returns the value of field 'ABE_HZAIC7_jointAccount'.
     * 
     * @return the value of field 'ABE_HZAIC7_jointAccount'.
     */
    public java.lang.String getABE_HZAIC7_jointAccount(
    ) {
        return this._ABE_HZAIC7_jointAccount;
    }

    /**
     * Returns the value of field 'ABE_HZAII7_noticeAC'.
     * 
     * @return the value of field 'ABE_HZAII7_noticeAC'.
     */
    public java.lang.String getABE_HZAII7_noticeAC(
    ) {
        return this._ABE_HZAII7_noticeAC;
    }

    /**
     * Returns the value of field
     * 'ABE_HZANM_masterInterestAccountBasic'.
     * 
     * @return the value of field
     * 'ABE_HZANM_masterInterestAccountBasic'.
     */
    public java.lang.String getABE_HZANM_masterInterestAccountBasic(
    ) {
        return this._ABE_HZANM_masterInterestAccountBasic;
    }

    /**
     * Returns the value of field
     * 'ABE_HZANNC_creditNominatedAccountBasic'.
     * 
     * @return the value of field
     * 'ABE_HZANNC_creditNominatedAccountBasic'.
     */
    public java.lang.String getABE_HZANNC_creditNominatedAccountBasic(
    ) {
        return this._ABE_HZANNC_creditNominatedAccountBasic;
    }

    /**
     * Returns the value of field
     * 'ABE_HZANND_debitNominatedAccountBasic'.
     * 
     * @return the value of field
     * 'ABE_HZANND_debitNominatedAccountBasic'.
     */
    public java.lang.String getABE_HZANND_debitNominatedAccountBasic(
    ) {
        return this._ABE_HZANND_debitNominatedAccountBasic;
    }

    /**
     * Returns the value of field
     * 'ABE_HZANNG_chargeNominatedAccountBasic'.
     * 
     * @return the value of field
     * 'ABE_HZANNG_chargeNominatedAccountBasic'.
     */
    public java.lang.String getABE_HZANNG_chargeNominatedAccountBasic(
    ) {
        return this._ABE_HZANNG_chargeNominatedAccountBasic;
    }

    /**
     * Returns the value of field
     * 'ABE_HZANN_chargeNominatedAccountBasic'.
     * 
     * @return the value of field
     * 'ABE_HZANN_chargeNominatedAccountBasic'.
     */
    public java.lang.String getABE_HZANN_chargeNominatedAccountBasic(
    ) {
        return this._ABE_HZANN_chargeNominatedAccountBasic;
    }

    /**
     * Returns the value of field
     * 'ABE_HZAN_basicPartOfAccountNumber'.
     * 
     * @return the value of field
     * 'ABE_HZAN_basicPartOfAccountNumber'.
     */
    public java.lang.String getABE_HZAN_basicPartOfAccountNumber(
    ) {
        return this._ABE_HZAN_basicPartOfAccountNumber;
    }

    /**
     * Returns the value of field
     * 'ABE_HZAPP_owningApplicationCode'.
     * 
     * @return the value of field 'ABE_HZAPP_owningApplicationCode'.
     */
    public java.lang.String getABE_HZAPP_owningApplicationCode(
    ) {
        return this._ABE_HZAPP_owningApplicationCode;
    }

    /**
     * Returns the value of field 'ABE_HZASIP_aCSettlInstPresent'.
     * 
     * @return the value of field 'ABE_HZASIP_aCSettlInstPresent'.
     */
    public java.lang.String getABE_HZASIP_aCSettlInstPresent(
    ) {
        return this._ABE_HZASIP_aCSettlInstPresent;
    }

    /**
     * Returns the value of field
     * 'ABE_HZASM_masterInterestAccountSuffix'.
     * 
     * @return the value of field
     * 'ABE_HZASM_masterInterestAccountSuffix'.
     */
    public java.lang.String getABE_HZASM_masterInterestAccountSuffix(
    ) {
        return this._ABE_HZASM_masterInterestAccountSuffix;
    }

    /**
     * Returns the value of field
     * 'ABE_HZASNC_creditNominatedAccountSuffix'.
     * 
     * @return the value of field
     * 'ABE_HZASNC_creditNominatedAccountSuffix'.
     */
    public java.lang.String getABE_HZASNC_creditNominatedAccountSuffix(
    ) {
        return this._ABE_HZASNC_creditNominatedAccountSuffix;
    }

    /**
     * Returns the value of field
     * 'ABE_HZASND_debitNominatedAccountSuffix'.
     * 
     * @return the value of field
     * 'ABE_HZASND_debitNominatedAccountSuffix'.
     */
    public java.lang.String getABE_HZASND_debitNominatedAccountSuffix(
    ) {
        return this._ABE_HZASND_debitNominatedAccountSuffix;
    }

    /**
     * Returns the value of field
     * 'ABE_HZASNG_chargeNominatedAccountSuffix'.
     * 
     * @return the value of field
     * 'ABE_HZASNG_chargeNominatedAccountSuffix'.
     */
    public java.lang.String getABE_HZASNG_chargeNominatedAccountSuffix(
    ) {
        return this._ABE_HZASNG_chargeNominatedAccountSuffix;
    }

    /**
     * Returns the value of field
     * 'ABE_HZASN_chargeNominatedAccountSuffix'.
     * 
     * @return the value of field
     * 'ABE_HZASN_chargeNominatedAccountSuffix'.
     */
    public java.lang.String getABE_HZASN_chargeNominatedAccountSuffix(
    ) {
        return this._ABE_HZASN_chargeNominatedAccountSuffix;
    }

    /**
     * Returns the value of field 'ABE_HZAS_accountSuffix'.
     * 
     * @return the value of field 'ABE_HZAS_accountSuffix'.
     */
    public java.lang.String getABE_HZAS_accountSuffix(
    ) {
        return this._ABE_HZAS_accountSuffix;
    }

    /**
     * Returns the value of field 'ABE_HZAUDT_lastAuditLetterDate'.
     * 
     * @return the value of field 'ABE_HZAUDT_lastAuditLetterDate'.
     */
    public java.lang.String getABE_HZAUDT_lastAuditLetterDate(
    ) {
        return this._ABE_HZAUDT_lastAuditLetterDate;
    }

    /**
     * Returns the value of field
     * 'ABE_HZAUNO_lastAuditLetterNumber'.
     * 
     * @return the value of field 'ABE_HZAUNO_lastAuditLetterNumber'
     */
    public java.lang.String getABE_HZAUNO_lastAuditLetterNumber(
    ) {
        return this._ABE_HZAUNO_lastAuditLetterNumber;
    }

    /**
     * Returns the value of field 'ABE_HZAUT_authorised'.
     * 
     * @return the value of field 'ABE_HZAUT_authorised'.
     */
    public java.lang.String getABE_HZAUT_authorised(
    ) {
        return this._ABE_HZAUT_authorised;
    }

    /**
     * Returns the value of field
     * 'ABE_HZBALS_balanceOnThePreviousStatement'.
     * 
     * @return the value of field
     * 'ABE_HZBALS_balanceOnThePreviousStatement'.
     */
    public java.lang.String getABE_HZBALS_balanceOnThePreviousStatement(
    ) {
        return this._ABE_HZBALS_balanceOnThePreviousStatement;
    }

    /**
     * Returns the value of field
     * 'ABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay'.
     * 
     * @return the value of field
     * 'ABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay'.
     */
    public java.lang.String getABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay(
    ) {
        return this._ABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay;
    }

    /**
     * Returns the value of field 'ABE_HZC1R_c1RatingForAccount'.
     * 
     * @return the value of field 'ABE_HZC1R_c1RatingForAccount'.
     */
    public java.lang.String getABE_HZC1R_c1RatingForAccount(
    ) {
        return this._ABE_HZC1R_c1RatingForAccount;
    }

    /**
     * Returns the value of field 'ABE_HZC2R_c2RatingForAccount'.
     * 
     * @return the value of field 'ABE_HZC2R_c2RatingForAccount'.
     */
    public java.lang.String getABE_HZC2R_c2RatingForAccount(
    ) {
        return this._ABE_HZC2R_c2RatingForAccount;
    }

    /**
     * Returns the value of field 'ABE_HZC3R_c3RatingForAccount'.
     * 
     * @return the value of field 'ABE_HZC3R_c3RatingForAccount'.
     */
    public java.lang.String getABE_HZC3R_c3RatingForAccount(
    ) {
        return this._ABE_HZC3R_c3RatingForAccount;
    }

    /**
     * Returns the value of field 'ABE_HZC4R_c4RatingForAccount'.
     * 
     * @return the value of field 'ABE_HZC4R_c4RatingForAccount'.
     */
    public java.lang.String getABE_HZC4R_c4RatingForAccount(
    ) {
        return this._ABE_HZC4R_c4RatingForAccount;
    }

    /**
     * Returns the value of field 'ABE_HZC5R_c5RatingForAccount'.
     * 
     * @return the value of field 'ABE_HZC5R_c5RatingForAccount'.
     */
    public java.lang.String getABE_HZC5R_c5RatingForAccount(
    ) {
        return this._ABE_HZC5R_c5RatingForAccount;
    }

    /**
     * Returns the value of field 'ABE_HZCAD_dateAccountClosed'.
     * 
     * @return the value of field 'ABE_HZCAD_dateAccountClosed'.
     */
    public java.lang.String getABE_HZCAD_dateAccountClosed(
    ) {
        return this._ABE_HZCAD_dateAccountClosed;
    }

    /**
     * Returns the value of field 'ABE_HZCAN_accountNumber'.
     * 
     * @return the value of field 'ABE_HZCAN_accountNumber'.
     */
    public java.lang.String getABE_HZCAN_accountNumber(
    ) {
        return this._ABE_HZCAN_accountNumber;
    }

    /**
     * Returns the value of field 'ABE_HZCCY_currencyMnemonic'.
     * 
     * @return the value of field 'ABE_HZCCY_currencyMnemonic'.
     */
    public java.lang.String getABE_HZCCY_currencyMnemonic(
    ) {
        return this._ABE_HZCCY_currencyMnemonic;
    }

    /**
     * Returns the value of field 'ABE_HZCED_currencyEditField'.
     * 
     * @return the value of field 'ABE_HZCED_currencyEditField'.
     */
    public java.lang.String getABE_HZCED_currencyEditField(
    ) {
        return this._ABE_HZCED_currencyEditField;
    }

    /**
     * Returns the value of field 'ABE_HZCLC_customerLocation'.
     * 
     * @return the value of field 'ABE_HZCLC_customerLocation'.
     */
    public java.lang.String getABE_HZCLC_customerLocation(
    ) {
        return this._ABE_HZCLC_customerLocation;
    }

    /**
     * Returns the value of field 'ABE_HZCLS_closingWithdrawal'.
     * 
     * @return the value of field 'ABE_HZCLS_closingWithdrawal'.
     */
    public java.lang.String getABE_HZCLS_closingWithdrawal(
    ) {
        return this._ABE_HZCLS_closingWithdrawal;
    }

    /**
     * Returns the value of field 'ABE_HZCNAL_residenceCountry'.
     * 
     * @return the value of field 'ABE_HZCNAL_residenceCountry'.
     */
    public java.lang.String getABE_HZCNAL_residenceCountry(
    ) {
        return this._ABE_HZCNAL_residenceCountry;
    }

    /**
     * Returns the value of field 'ABE_HZCNAP_parentCountry'.
     * 
     * @return the value of field 'ABE_HZCNAP_parentCountry'.
     */
    public java.lang.String getABE_HZCNAP_parentCountry(
    ) {
        return this._ABE_HZCNAP_parentCountry;
    }

    /**
     * Returns the value of field 'ABE_HZCNAR_riskCountry'.
     * 
     * @return the value of field 'ABE_HZCNAR_riskCountry'.
     */
    public java.lang.String getABE_HZCNAR_riskCountry(
    ) {
        return this._ABE_HZCNAR_riskCountry;
    }

    /**
     * Returns the value of field
     * 'ABE_HZCSFC_chargesStatementFrequency'.
     * 
     * @return the value of field
     * 'ABE_HZCSFC_chargesStatementFrequency'.
     */
    public java.lang.String getABE_HZCSFC_chargesStatementFrequency(
    ) {
        return this._ABE_HZCSFC_chargesStatementFrequency;
    }

    /**
     * Returns the value of field
     * 'ABE_HZCSTL_lastChargesStatementDate'.
     * 
     * @return the value of field
     * 'ABE_HZCSTL_lastChargesStatementDate'.
     */
    public java.lang.String getABE_HZCSTL_lastChargesStatementDate(
    ) {
        return this._ABE_HZCSTL_lastChargesStatementDate;
    }

    /**
     * Returns the value of field
     * 'ABE_HZCSTN_nextChargesStatementDate'.
     * 
     * @return the value of field
     * 'ABE_HZCSTN_nextChargesStatementDate'.
     */
    public java.lang.String getABE_HZCSTN_nextChargesStatementDate(
    ) {
        return this._ABE_HZCSTN_nextChargesStatementDate;
    }

    /**
     * Returns the value of field 'ABE_HZCTP_customerType'.
     * 
     * @return the value of field 'ABE_HZCTP_customerType'.
     */
    public java.lang.String getABE_HZCTP_customerType(
    ) {
        return this._ABE_HZCTP_customerType;
    }

    /**
     * Returns the value of field
     * 'ABE_HZCUNA_arabicCustomerFullName'.
     * 
     * @return the value of field
     * 'ABE_HZCUNA_arabicCustomerFullName'.
     */
    public java.lang.String getABE_HZCUNA_arabicCustomerFullName(
    ) {
        return this._ABE_HZCUNA_arabicCustomerFullName;
    }

    /**
     * Returns the value of field 'ABE_HZCUN_customerFullName'.
     * 
     * @return the value of field 'ABE_HZCUN_customerFullName'.
     */
    public java.lang.String getABE_HZCUN_customerFullName(
    ) {
        return this._ABE_HZCUN_customerFullName;
    }

    /**
     * Returns the value of field 'ABE_HZCUS_customerMnemonic'.
     * 
     * @return the value of field 'ABE_HZCUS_customerMnemonic'.
     */
    public java.lang.String getABE_HZCUS_customerMnemonic(
    ) {
        return this._ABE_HZCUS_customerMnemonic;
    }

    /**
     * Returns the value of field
     * 'ABE_HZDFRM_fontisDownloadFrequencyMeridian'.
     * 
     * @return the value of field
     * 'ABE_HZDFRM_fontisDownloadFrequencyMeridian'.
     */
    public java.lang.String getABE_HZDFRM_fontisDownloadFrequencyMeridian(
    ) {
        return this._ABE_HZDFRM_fontisDownloadFrequencyMeridian;
    }

    /**
     * Returns the value of field
     * 'ABE_HZDFRQ_fontisDownloadFrequency'.
     * 
     * @return the value of field
     * 'ABE_HZDFRQ_fontisDownloadFrequency'.
     */
    public java.lang.String getABE_HZDFRQ_fontisDownloadFrequency(
    ) {
        return this._ABE_HZDFRQ_fontisDownloadFrequency;
    }

    /**
     * Returns the value of field
     * 'ABE_HZDLE_dateOfLastCustomerGeneratedEntry'.
     * 
     * @return the value of field
     * 'ABE_HZDLE_dateOfLastCustomerGeneratedEntry'.
     */
    public java.lang.String getABE_HZDLE_dateOfLastCustomerGeneratedEntry(
    ) {
        return this._ABE_HZDLE_dateOfLastCustomerGeneratedEntry;
    }

    /**
     * Returns the value of field
     * 'ABE_HZDLMZ_instructionDateLastMaintained'.
     * 
     * @return the value of field
     * 'ABE_HZDLMZ_instructionDateLastMaintained'.
     */
    public java.lang.String getABE_HZDLMZ_instructionDateLastMaintained(
    ) {
        return this._ABE_HZDLMZ_instructionDateLastMaintained;
    }

    /**
     * Returns the value of field 'ABE_HZDLM_dateLastMaintained'.
     * 
     * @return the value of field 'ABE_HZDLM_dateLastMaintained'.
     */
    public java.lang.String getABE_HZDLM_dateLastMaintained(
    ) {
        return this._ABE_HZDLM_dateLastMaintained;
    }

    /**
     * Returns the value of field 'ABE_HZHLD_held'.
     * 
     * @return the value of field 'ABE_HZHLD_held'.
     */
    public java.lang.String getABE_HZHLD_held(
    ) {
        return this._ABE_HZHLD_held;
    }

    /**
     * Returns the value of field 'ABE_HZLNM_languageCode'.
     * 
     * @return the value of field 'ABE_HZLNM_languageCode'.
     */
    public java.lang.String getABE_HZLNM_languageCode(
    ) {
        return this._ABE_HZLNM_languageCode;
    }

    /**
     * Returns the value of field 'ABE_HZNANC_numericAnalysisCode'.
     * 
     * @return the value of field 'ABE_HZNANC_numericAnalysisCode'.
     */
    public java.lang.String getABE_HZNANC_numericAnalysisCode(
    ) {
        return this._ABE_HZNANC_numericAnalysisCode;
    }

    /**
     * Returns the value of field
     * 'ABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement'.
     * 
     * @return the value of field
     * 'ABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement'.
     */
    public java.lang.String getABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement(
    ) {
        return this._ABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement;
    }

    /**
     * Returns the value of field
     * 'ABE_HZNS3_numberOfReferenceCharactersForReconciliation'.
     * 
     * @return the value of field
     * 'ABE_HZNS3_numberOfReferenceCharactersForReconciliation'.
     */
    public java.lang.String getABE_HZNS3_numberOfReferenceCharactersForReconciliation(
    ) {
        return this._ABE_HZNS3_numberOfReferenceCharactersForReconciliation;
    }

    /**
     * Returns the value of field
     * 'ABE_HZNTAD_taxAdviceTypeNextYear'.
     * 
     * @return the value of field 'ABE_HZNTAD_taxAdviceTypeNextYear'
     */
    public java.lang.String getABE_HZNTAD_taxAdviceTypeNextYear(
    ) {
        return this._ABE_HZNTAD_taxAdviceTypeNextYear;
    }

    /**
     * Returns the value of field 'ABE_HZOAD_dateAccountOpened'.
     * 
     * @return the value of field 'ABE_HZOAD_dateAccountOpened'.
     */
    public java.lang.String getABE_HZOAD_dateAccountOpened(
    ) {
        return this._ABE_HZOAD_dateAccountOpened;
    }

    /**
     * Returns the value of field 'ABE_HZP1R_p1RatingForAccount'.
     * 
     * @return the value of field 'ABE_HZP1R_p1RatingForAccount'.
     */
    public java.lang.String getABE_HZP1R_p1RatingForAccount(
    ) {
        return this._ABE_HZP1R_p1RatingForAccount;
    }

    /**
     * Returns the value of field 'ABE_HZP2R_p2RatingForAccount'.
     * 
     * @return the value of field 'ABE_HZP2R_p2RatingForAccount'.
     */
    public java.lang.String getABE_HZP2R_p2RatingForAccount(
    ) {
        return this._ABE_HZP2R_p2RatingForAccount;
    }

    /**
     * Returns the value of field 'ABE_HZP3R_p3RatingForAccount'.
     * 
     * @return the value of field 'ABE_HZP3R_p3RatingForAccount'.
     */
    public java.lang.String getABE_HZP3R_p3RatingForAccount(
    ) {
        return this._ABE_HZP3R_p3RatingForAccount;
    }

    /**
     * Returns the value of field 'ABE_HZP4R_p4RatingForAccount'.
     * 
     * @return the value of field 'ABE_HZP4R_p4RatingForAccount'.
     */
    public java.lang.String getABE_HZP4R_p4RatingForAccount(
    ) {
        return this._ABE_HZP4R_p4RatingForAccount;
    }

    /**
     * Returns the value of field 'ABE_HZP5R_p5RatingForAccount'.
     * 
     * @return the value of field 'ABE_HZP5R_p5RatingForAccount'.
     */
    public java.lang.String getABE_HZP5R_p5RatingForAccount(
    ) {
        return this._ABE_HZP5R_p5RatingForAccount;
    }

    /**
     * Returns the value of field 'ABE_HZPAB_sourceOriginalBranch'.
     * 
     * @return the value of field 'ABE_HZPAB_sourceOriginalBranch'.
     */
    public java.lang.String getABE_HZPAB_sourceOriginalBranch(
    ) {
        return this._ABE_HZPAB_sourceOriginalBranch;
    }

    /**
     * Returns the value of field 'ABE_HZPCHD_branchChangeDate'.
     * 
     * @return the value of field 'ABE_HZPCHD_branchChangeDate'.
     */
    public java.lang.String getABE_HZPCHD_branchChangeDate(
    ) {
        return this._ABE_HZPCHD_branchChangeDate;
    }

    /**
     * Returns the value of field 'ABE_HZREFA_settlementReference'.
     * 
     * @return the value of field 'ABE_HZREFA_settlementReference'.
     */
    public java.lang.String getABE_HZREFA_settlementReference(
    ) {
        return this._ABE_HZREFA_settlementReference;
    }

    /**
     * Returns the value of field 'ABE_HZREF_lastCpReference'.
     * 
     * @return the value of field 'ABE_HZREF_lastCpReference'.
     */
    public java.lang.String getABE_HZREF_lastCpReference(
    ) {
        return this._ABE_HZREF_lastCpReference;
    }

    /**
     * Returns the value of field
     * 'ABE_HZRETP_retentionPeriod00None011MthSysDflt'.
     * 
     * @return the value of field
     * 'ABE_HZRETP_retentionPeriod00None011MthSysDflt'.
     */
    public java.lang.String getABE_HZRETP_retentionPeriod00None011MthSysDflt(
    ) {
        return this._ABE_HZRETP_retentionPeriod00None011MthSysDflt;
    }

    /**
     * Returns the value of field 'ABE_HZSAC_sundryAnalysisCode'.
     * 
     * @return the value of field 'ABE_HZSAC_sundryAnalysisCode'.
     */
    public java.lang.String getABE_HZSAC_sundryAnalysisCode(
    ) {
        return this._ABE_HZSAC_sundryAnalysisCode;
    }

    /**
     * Returns the value of field
     * 'ABE_HZSFC_statementFrequencyCode'.
     * 
     * @return the value of field 'ABE_HZSFC_statementFrequencyCode'
     */
    public java.lang.String getABE_HZSFC_statementFrequencyCode(
    ) {
        return this._ABE_HZSFC_statementFrequencyCode;
    }

    /**
     * Returns the value of field 'ABE_HZSHNA_arabicShortName'.
     * 
     * @return the value of field 'ABE_HZSHNA_arabicShortName'.
     */
    public java.lang.String getABE_HZSHNA_arabicShortName(
    ) {
        return this._ABE_HZSHNA_arabicShortName;
    }

    /**
     * Returns the value of field 'ABE_HZSHN_accountShortName'.
     * 
     * @return the value of field 'ABE_HZSHN_accountShortName'.
     */
    public java.lang.String getABE_HZSHN_accountShortName(
    ) {
        return this._ABE_HZSHN_accountShortName;
    }

    /**
     * Returns the value of field
     * 'ABE_HZSN20_externalAccountNumber'.
     * 
     * @return the value of field 'ABE_HZSN20_externalAccountNumber'
     */
    public java.lang.String getABE_HZSN20_externalAccountNumber(
    ) {
        return this._ABE_HZSN20_externalAccountNumber;
    }

    /**
     * Returns the value of field
     * 'ABE_HZSNA_chargeNominatedAccountArabicShortName'.
     * 
     * @return the value of field
     * 'ABE_HZSNA_chargeNominatedAccountArabicShortName'.
     */
    public java.lang.String getABE_HZSNA_chargeNominatedAccountArabicShortName(
    ) {
        return this._ABE_HZSNA_chargeNominatedAccountArabicShortName;
    }

    /**
     * Returns the value of field
     * 'ABE_HZSNCA_creditNominatedAccountArabicShortName'.
     * 
     * @return the value of field
     * 'ABE_HZSNCA_creditNominatedAccountArabicShortName'.
     */
    public java.lang.String getABE_HZSNCA_creditNominatedAccountArabicShortName(
    ) {
        return this._ABE_HZSNCA_creditNominatedAccountArabicShortName;
    }

    /**
     * Returns the value of field
     * 'ABE_HZSNDA_debitNominatedAccountArabicShortName'.
     * 
     * @return the value of field
     * 'ABE_HZSNDA_debitNominatedAccountArabicShortName'.
     */
    public java.lang.String getABE_HZSNDA_debitNominatedAccountArabicShortName(
    ) {
        return this._ABE_HZSNDA_debitNominatedAccountArabicShortName;
    }

    /**
     * Returns the value of field
     * 'ABE_HZSNGA_chargeNominatedAccountArabicShortName'.
     * 
     * @return the value of field
     * 'ABE_HZSNGA_chargeNominatedAccountArabicShortName'.
     */
    public java.lang.String getABE_HZSNGA_chargeNominatedAccountArabicShortName(
    ) {
        return this._ABE_HZSNGA_chargeNominatedAccountArabicShortName;
    }

    /**
     * Returns the value of field
     * 'ABE_HZSNMA_masterInterestAccountArabicShortName'.
     * 
     * @return the value of field
     * 'ABE_HZSNMA_masterInterestAccountArabicShortName'.
     */
    public java.lang.String getABE_HZSNMA_masterInterestAccountArabicShortName(
    ) {
        return this._ABE_HZSNMA_masterInterestAccountArabicShortName;
    }

    /**
     * Returns the value of field
     * 'ABE_HZSNM_masterInterestAccountShortName'.
     * 
     * @return the value of field
     * 'ABE_HZSNM_masterInterestAccountShortName'.
     */
    public java.lang.String getABE_HZSNM_masterInterestAccountShortName(
    ) {
        return this._ABE_HZSNM_masterInterestAccountShortName;
    }

    /**
     * Returns the value of field
     * 'ABE_HZSNNC_creditNominatedAccountShortName'.
     * 
     * @return the value of field
     * 'ABE_HZSNNC_creditNominatedAccountShortName'.
     */
    public java.lang.String getABE_HZSNNC_creditNominatedAccountShortName(
    ) {
        return this._ABE_HZSNNC_creditNominatedAccountShortName;
    }

    /**
     * Returns the value of field
     * 'ABE_HZSNND_debitNominatedAccountShortName'.
     * 
     * @return the value of field
     * 'ABE_HZSNND_debitNominatedAccountShortName'.
     */
    public java.lang.String getABE_HZSNND_debitNominatedAccountShortName(
    ) {
        return this._ABE_HZSNND_debitNominatedAccountShortName;
    }

    /**
     * Returns the value of field
     * 'ABE_HZSNNG_chargeNominatedAccountShortName'.
     * 
     * @return the value of field
     * 'ABE_HZSNNG_chargeNominatedAccountShortName'.
     */
    public java.lang.String getABE_HZSNNG_chargeNominatedAccountShortName(
    ) {
        return this._ABE_HZSNNG_chargeNominatedAccountShortName;
    }

    /**
     * Returns the value of field
     * 'ABE_HZSNN_chargeNominatedAccountShortName'.
     * 
     * @return the value of field
     * 'ABE_HZSNN_chargeNominatedAccountShortName'.
     */
    public java.lang.String getABE_HZSNN_chargeNominatedAccountShortName(
    ) {
        return this._ABE_HZSNN_chargeNominatedAccountShortName;
    }

    /**
     * Returns the value of field 'ABE_HZSTA_status'.
     * 
     * @return the value of field 'ABE_HZSTA_status'.
     */
    public java.lang.String getABE_HZSTA_status(
    ) {
        return this._ABE_HZSTA_status;
    }

    /**
     * Returns the value of field 'ABE_HZSTML_lastStatementDate'.
     * 
     * @return the value of field 'ABE_HZSTML_lastStatementDate'.
     */
    public java.lang.String getABE_HZSTML_lastStatementDate(
    ) {
        return this._ABE_HZSTML_lastStatementDate;
    }

    /**
     * Returns the value of field 'ABE_HZSTMN_nextStatementDate'.
     * 
     * @return the value of field 'ABE_HZSTMN_nextStatementDate'.
     */
    public java.lang.String getABE_HZSTMN_nextStatementDate(
    ) {
        return this._ABE_HZSTMN_nextStatementDate;
    }

    /**
     * Returns the value of field 'ABE_HZSTNL_lastStatementNumber'.
     * 
     * @return the value of field 'ABE_HZSTNL_lastStatementNumber'.
     */
    public java.lang.String getABE_HZSTNL_lastStatementNumber(
    ) {
        return this._ABE_HZSTNL_lastStatementNumber;
    }

    /**
     * Returns the value of field 'ABE_HZTADT_taxAdviceType'.
     * 
     * @return the value of field 'ABE_HZTADT_taxAdviceType'.
     */
    public java.lang.String getABE_HZTADT_taxAdviceType(
    ) {
        return this._ABE_HZTADT_taxAdviceType;
    }

    /**
     * Returns the value of field 'ABE_HZTRM_transferMethod'.
     * 
     * @return the value of field 'ABE_HZTRM_transferMethod'.
     */
    public java.lang.String getABE_HZTRM_transferMethod(
    ) {
        return this._ABE_HZTRM_transferMethod;
    }

    /**
     * Returns the value of field 'ABE_HZUNMW_unmaturedWithdrawal'.
     * 
     * @return the value of field 'ABE_HZUNMW_unmaturedWithdrawal'.
     */
    public java.lang.String getABE_HZUNMW_unmaturedWithdrawal(
    ) {
        return this._ABE_HZUNMW_unmaturedWithdrawal;
    }

    /**
     * Returns the value of field
     * 'ABE_HZXMD_transferMethodDescription'.
     * 
     * @return the value of field
     * 'ABE_HZXMD_transferMethodDescription'.
     */
    public java.lang.String getABE_HZXMD_transferMethodDescription(
    ) {
        return this._ABE_HZXMD_transferMethodDescription;
    }

    /**
     * Returns the value of field 'ABE_HZYFON_fontisAccount'.
     * 
     * @return the value of field 'ABE_HZYFON_fontisAccount'.
     */
    public java.lang.String getABE_HZYFON_fontisAccount(
    ) {
        return this._ABE_HZYFON_fontisAccount;
    }

    /**
     * Returns the value of field 'CRG1_crg1'.
     * 
     * @return the value of field 'CRG1_crg1'.
     */
    public java.lang.String getCRG1_crg1(
    ) {
        return this._CRG1_crg1;
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
     * Sets the value of field 'ABE_HZABD_previousDescription'.
     * 
     * @param ABE_HZABD_previousDescription the value of field
     * 'ABE_HZABD_previousDescription'.
     */
    public void setABE_HZABD_previousDescription(
            final java.lang.String ABE_HZABD_previousDescription) {
        this._ABE_HZABD_previousDescription = ABE_HZABD_previousDescription;
    }

    /**
     * Sets the value of field
     * 'ABE_HZABM_masterInterestAccountBranch'.
     * 
     * @param ABE_HZABM_masterInterestAccountBranch the value of
     * field 'ABE_HZABM_masterInterestAccountBranch'.
     */
    public void setABE_HZABM_masterInterestAccountBranch(
            final java.lang.String ABE_HZABM_masterInterestAccountBranch) {
        this._ABE_HZABM_masterInterestAccountBranch = ABE_HZABM_masterInterestAccountBranch;
    }

    /**
     * Sets the value of field
     * 'ABE_HZABNC_creditNominatedAccountBranch'.
     * 
     * @param ABE_HZABNC_creditNominatedAccountBranch the value of
     * field 'ABE_HZABNC_creditNominatedAccountBranch'.
     */
    public void setABE_HZABNC_creditNominatedAccountBranch(
            final java.lang.String ABE_HZABNC_creditNominatedAccountBranch) {
        this._ABE_HZABNC_creditNominatedAccountBranch = ABE_HZABNC_creditNominatedAccountBranch;
    }

    /**
     * Sets the value of field
     * 'ABE_HZABND_debitNominatedAccountBranch'.
     * 
     * @param ABE_HZABND_debitNominatedAccountBranch the value of
     * field 'ABE_HZABND_debitNominatedAccountBranch'.
     */
    public void setABE_HZABND_debitNominatedAccountBranch(
            final java.lang.String ABE_HZABND_debitNominatedAccountBranch) {
        this._ABE_HZABND_debitNominatedAccountBranch = ABE_HZABND_debitNominatedAccountBranch;
    }

    /**
     * Sets the value of field
     * 'ABE_HZABNG_chargeNominatedAccountBranch'.
     * 
     * @param ABE_HZABNG_chargeNominatedAccountBranch the value of
     * field 'ABE_HZABNG_chargeNominatedAccountBranch'.
     */
    public void setABE_HZABNG_chargeNominatedAccountBranch(
            final java.lang.String ABE_HZABNG_chargeNominatedAccountBranch) {
        this._ABE_HZABNG_chargeNominatedAccountBranch = ABE_HZABNG_chargeNominatedAccountBranch;
    }

    /**
     * Sets the value of field
     * 'ABE_HZABN_chargeNominatedAccountBranch'.
     * 
     * @param ABE_HZABN_chargeNominatedAccountBranch the value of
     * field 'ABE_HZABN_chargeNominatedAccountBranch'.
     */
    public void setABE_HZABN_chargeNominatedAccountBranch(
            final java.lang.String ABE_HZABN_chargeNominatedAccountBranch) {
        this._ABE_HZABN_chargeNominatedAccountBranch = ABE_HZABN_chargeNominatedAccountBranch;
    }

    /**
     * Sets the value of field 'ABE_HZAB_accountBranch'.
     * 
     * @param ABE_HZAB_accountBranch the value of field
     * 'ABE_HZAB_accountBranch'.
     */
    public void setABE_HZAB_accountBranch(
            final java.lang.String ABE_HZAB_accountBranch) {
        this._ABE_HZAB_accountBranch = ABE_HZAB_accountBranch;
    }

    /**
     * Sets the value of field 'ABE_HZACD_analysisCode'.
     * 
     * @param ABE_HZACD_analysisCode the value of field
     * 'ABE_HZACD_analysisCode'.
     */
    public void setABE_HZACD_analysisCode(
            final java.lang.String ABE_HZACD_analysisCode) {
        this._ABE_HZACD_analysisCode = ABE_HZACD_analysisCode;
    }

    /**
     * Sets the value of field 'ABE_HZACO_accountOfficer'.
     * 
     * @param ABE_HZACO_accountOfficer the value of field
     * 'ABE_HZACO_accountOfficer'.
     */
    public void setABE_HZACO_accountOfficer(
            final java.lang.String ABE_HZACO_accountOfficer) {
        this._ABE_HZACO_accountOfficer = ABE_HZACO_accountOfficer;
    }

    /**
     * Sets the value of field 'ABE_HZACS_accountStructure'.
     * 
     * @param ABE_HZACS_accountStructure the value of field
     * 'ABE_HZACS_accountStructure'.
     */
    public void setABE_HZACS_accountStructure(
            final java.lang.String ABE_HZACS_accountStructure) {
        this._ABE_HZACS_accountStructure = ABE_HZACS_accountStructure;
    }

    /**
     * Sets the value of field 'ABE_HZACT_accountType'.
     * 
     * @param ABE_HZACT_accountType the value of field
     * 'ABE_HZACT_accountType'.
     */
    public void setABE_HZACT_accountType(
            final java.lang.String ABE_HZACT_accountType) {
        this._ABE_HZACT_accountType = ABE_HZACT_accountType;
    }

    /**
     * Sets the value of field 'ABE_HZAI14_deceasedOrLiquidated'.
     * 
     * @param ABE_HZAI14_deceasedOrLiquidated the value of field
     * 'ABE_HZAI14_deceasedOrLiquidated'.
     */
    public void setABE_HZAI14_deceasedOrLiquidated(
            final java.lang.String ABE_HZAI14_deceasedOrLiquidated) {
        this._ABE_HZAI14_deceasedOrLiquidated = ABE_HZAI14_deceasedOrLiquidated;
    }

    /**
     * Sets the value of field 'ABE_HZAI17_accountStatusBlocked'.
     * 
     * @param ABE_HZAI17_accountStatusBlocked the value of field
     * 'ABE_HZAI17_accountStatusBlocked'.
     */
    public void setABE_HZAI17_accountStatusBlocked(
            final java.lang.String ABE_HZAI17_accountStatusBlocked) {
        this._ABE_HZAI17_accountStatusBlocked = ABE_HZAI17_accountStatusBlocked;
    }

    /**
     * Sets the value of field 'ABE_HZAI20_accountStatusInactive'.
     * 
     * @param ABE_HZAI20_accountStatusInactive the value of field
     * 'ABE_HZAI20_accountStatusInactive'.
     */
    public void setABE_HZAI20_accountStatusInactive(
            final java.lang.String ABE_HZAI20_accountStatusInactive) {
        this._ABE_HZAI20_accountStatusInactive = ABE_HZAI20_accountStatusInactive;
    }

    /**
     * Sets the value of field 'ABE_HZAI30_accountClosing'.
     * 
     * @param ABE_HZAI30_accountClosing the value of field
     * 'ABE_HZAI30_accountClosing'.
     */
    public void setABE_HZAI30_accountClosing(
            final java.lang.String ABE_HZAI30_accountClosing) {
        this._ABE_HZAI30_accountClosing = ABE_HZAI30_accountClosing;
    }

    /**
     * Sets the value of field 'ABE_HZAI33_printChargesStatement'.
     * 
     * @param ABE_HZAI33_printChargesStatement the value of field
     * 'ABE_HZAI33_printChargesStatement'.
     */
    public void setABE_HZAI33_printChargesStatement(
            final java.lang.String ABE_HZAI33_printChargesStatement) {
        this._ABE_HZAI33_printChargesStatement = ABE_HZAI33_printChargesStatement;
    }

    /**
     * Sets the value of field
     * 'ABE_HZAI47_internalNonCustomerAccount'.
     * 
     * @param ABE_HZAI47_internalNonCustomerAccount the value of
     * field 'ABE_HZAI47_internalNonCustomerAccount'.
     */
    public void setABE_HZAI47_internalNonCustomerAccount(
            final java.lang.String ABE_HZAI47_internalNonCustomerAccount) {
        this._ABE_HZAI47_internalNonCustomerAccount = ABE_HZAI47_internalNonCustomerAccount;
    }

    /**
     * Sets the value of field 'ABE_HZAIC7_jointAccount'.
     * 
     * @param ABE_HZAIC7_jointAccount the value of field
     * 'ABE_HZAIC7_jointAccount'.
     */
    public void setABE_HZAIC7_jointAccount(
            final java.lang.String ABE_HZAIC7_jointAccount) {
        this._ABE_HZAIC7_jointAccount = ABE_HZAIC7_jointAccount;
    }

    /**
     * Sets the value of field 'ABE_HZAII7_noticeAC'.
     * 
     * @param ABE_HZAII7_noticeAC the value of field
     * 'ABE_HZAII7_noticeAC'.
     */
    public void setABE_HZAII7_noticeAC(
            final java.lang.String ABE_HZAII7_noticeAC) {
        this._ABE_HZAII7_noticeAC = ABE_HZAII7_noticeAC;
    }

    /**
     * Sets the value of field
     * 'ABE_HZANM_masterInterestAccountBasic'.
     * 
     * @param ABE_HZANM_masterInterestAccountBasic the value of
     * field 'ABE_HZANM_masterInterestAccountBasic'.
     */
    public void setABE_HZANM_masterInterestAccountBasic(
            final java.lang.String ABE_HZANM_masterInterestAccountBasic) {
        this._ABE_HZANM_masterInterestAccountBasic = ABE_HZANM_masterInterestAccountBasic;
    }

    /**
     * Sets the value of field
     * 'ABE_HZANNC_creditNominatedAccountBasic'.
     * 
     * @param ABE_HZANNC_creditNominatedAccountBasic the value of
     * field 'ABE_HZANNC_creditNominatedAccountBasic'.
     */
    public void setABE_HZANNC_creditNominatedAccountBasic(
            final java.lang.String ABE_HZANNC_creditNominatedAccountBasic) {
        this._ABE_HZANNC_creditNominatedAccountBasic = ABE_HZANNC_creditNominatedAccountBasic;
    }

    /**
     * Sets the value of field
     * 'ABE_HZANND_debitNominatedAccountBasic'.
     * 
     * @param ABE_HZANND_debitNominatedAccountBasic the value of
     * field 'ABE_HZANND_debitNominatedAccountBasic'.
     */
    public void setABE_HZANND_debitNominatedAccountBasic(
            final java.lang.String ABE_HZANND_debitNominatedAccountBasic) {
        this._ABE_HZANND_debitNominatedAccountBasic = ABE_HZANND_debitNominatedAccountBasic;
    }

    /**
     * Sets the value of field
     * 'ABE_HZANNG_chargeNominatedAccountBasic'.
     * 
     * @param ABE_HZANNG_chargeNominatedAccountBasic the value of
     * field 'ABE_HZANNG_chargeNominatedAccountBasic'.
     */
    public void setABE_HZANNG_chargeNominatedAccountBasic(
            final java.lang.String ABE_HZANNG_chargeNominatedAccountBasic) {
        this._ABE_HZANNG_chargeNominatedAccountBasic = ABE_HZANNG_chargeNominatedAccountBasic;
    }

    /**
     * Sets the value of field
     * 'ABE_HZANN_chargeNominatedAccountBasic'.
     * 
     * @param ABE_HZANN_chargeNominatedAccountBasic the value of
     * field 'ABE_HZANN_chargeNominatedAccountBasic'.
     */
    public void setABE_HZANN_chargeNominatedAccountBasic(
            final java.lang.String ABE_HZANN_chargeNominatedAccountBasic) {
        this._ABE_HZANN_chargeNominatedAccountBasic = ABE_HZANN_chargeNominatedAccountBasic;
    }

    /**
     * Sets the value of field 'ABE_HZAN_basicPartOfAccountNumber'.
     * 
     * @param ABE_HZAN_basicPartOfAccountNumber the value of field
     * 'ABE_HZAN_basicPartOfAccountNumber'.
     */
    public void setABE_HZAN_basicPartOfAccountNumber(
            final java.lang.String ABE_HZAN_basicPartOfAccountNumber) {
        this._ABE_HZAN_basicPartOfAccountNumber = ABE_HZAN_basicPartOfAccountNumber;
    }

    /**
     * Sets the value of field 'ABE_HZAPP_owningApplicationCode'.
     * 
     * @param ABE_HZAPP_owningApplicationCode the value of field
     * 'ABE_HZAPP_owningApplicationCode'.
     */
    public void setABE_HZAPP_owningApplicationCode(
            final java.lang.String ABE_HZAPP_owningApplicationCode) {
        this._ABE_HZAPP_owningApplicationCode = ABE_HZAPP_owningApplicationCode;
    }

    /**
     * Sets the value of field 'ABE_HZASIP_aCSettlInstPresent'.
     * 
     * @param ABE_HZASIP_aCSettlInstPresent the value of field
     * 'ABE_HZASIP_aCSettlInstPresent'.
     */
    public void setABE_HZASIP_aCSettlInstPresent(
            final java.lang.String ABE_HZASIP_aCSettlInstPresent) {
        this._ABE_HZASIP_aCSettlInstPresent = ABE_HZASIP_aCSettlInstPresent;
    }

    /**
     * Sets the value of field
     * 'ABE_HZASM_masterInterestAccountSuffix'.
     * 
     * @param ABE_HZASM_masterInterestAccountSuffix the value of
     * field 'ABE_HZASM_masterInterestAccountSuffix'.
     */
    public void setABE_HZASM_masterInterestAccountSuffix(
            final java.lang.String ABE_HZASM_masterInterestAccountSuffix) {
        this._ABE_HZASM_masterInterestAccountSuffix = ABE_HZASM_masterInterestAccountSuffix;
    }

    /**
     * Sets the value of field
     * 'ABE_HZASNC_creditNominatedAccountSuffix'.
     * 
     * @param ABE_HZASNC_creditNominatedAccountSuffix the value of
     * field 'ABE_HZASNC_creditNominatedAccountSuffix'.
     */
    public void setABE_HZASNC_creditNominatedAccountSuffix(
            final java.lang.String ABE_HZASNC_creditNominatedAccountSuffix) {
        this._ABE_HZASNC_creditNominatedAccountSuffix = ABE_HZASNC_creditNominatedAccountSuffix;
    }

    /**
     * Sets the value of field
     * 'ABE_HZASND_debitNominatedAccountSuffix'.
     * 
     * @param ABE_HZASND_debitNominatedAccountSuffix the value of
     * field 'ABE_HZASND_debitNominatedAccountSuffix'.
     */
    public void setABE_HZASND_debitNominatedAccountSuffix(
            final java.lang.String ABE_HZASND_debitNominatedAccountSuffix) {
        this._ABE_HZASND_debitNominatedAccountSuffix = ABE_HZASND_debitNominatedAccountSuffix;
    }

    /**
     * Sets the value of field
     * 'ABE_HZASNG_chargeNominatedAccountSuffix'.
     * 
     * @param ABE_HZASNG_chargeNominatedAccountSuffix the value of
     * field 'ABE_HZASNG_chargeNominatedAccountSuffix'.
     */
    public void setABE_HZASNG_chargeNominatedAccountSuffix(
            final java.lang.String ABE_HZASNG_chargeNominatedAccountSuffix) {
        this._ABE_HZASNG_chargeNominatedAccountSuffix = ABE_HZASNG_chargeNominatedAccountSuffix;
    }

    /**
     * Sets the value of field
     * 'ABE_HZASN_chargeNominatedAccountSuffix'.
     * 
     * @param ABE_HZASN_chargeNominatedAccountSuffix the value of
     * field 'ABE_HZASN_chargeNominatedAccountSuffix'.
     */
    public void setABE_HZASN_chargeNominatedAccountSuffix(
            final java.lang.String ABE_HZASN_chargeNominatedAccountSuffix) {
        this._ABE_HZASN_chargeNominatedAccountSuffix = ABE_HZASN_chargeNominatedAccountSuffix;
    }

    /**
     * Sets the value of field 'ABE_HZAS_accountSuffix'.
     * 
     * @param ABE_HZAS_accountSuffix the value of field
     * 'ABE_HZAS_accountSuffix'.
     */
    public void setABE_HZAS_accountSuffix(
            final java.lang.String ABE_HZAS_accountSuffix) {
        this._ABE_HZAS_accountSuffix = ABE_HZAS_accountSuffix;
    }

    /**
     * Sets the value of field 'ABE_HZAUDT_lastAuditLetterDate'.
     * 
     * @param ABE_HZAUDT_lastAuditLetterDate the value of field
     * 'ABE_HZAUDT_lastAuditLetterDate'.
     */
    public void setABE_HZAUDT_lastAuditLetterDate(
            final java.lang.String ABE_HZAUDT_lastAuditLetterDate) {
        this._ABE_HZAUDT_lastAuditLetterDate = ABE_HZAUDT_lastAuditLetterDate;
    }

    /**
     * Sets the value of field 'ABE_HZAUNO_lastAuditLetterNumber'.
     * 
     * @param ABE_HZAUNO_lastAuditLetterNumber the value of field
     * 'ABE_HZAUNO_lastAuditLetterNumber'.
     */
    public void setABE_HZAUNO_lastAuditLetterNumber(
            final java.lang.String ABE_HZAUNO_lastAuditLetterNumber) {
        this._ABE_HZAUNO_lastAuditLetterNumber = ABE_HZAUNO_lastAuditLetterNumber;
    }

    /**
     * Sets the value of field 'ABE_HZAUT_authorised'.
     * 
     * @param ABE_HZAUT_authorised the value of field
     * 'ABE_HZAUT_authorised'.
     */
    public void setABE_HZAUT_authorised(
            final java.lang.String ABE_HZAUT_authorised) {
        this._ABE_HZAUT_authorised = ABE_HZAUT_authorised;
    }

    /**
     * Sets the value of field
     * 'ABE_HZBALS_balanceOnThePreviousStatement'.
     * 
     * @param ABE_HZBALS_balanceOnThePreviousStatement the value of
     * field 'ABE_HZBALS_balanceOnThePreviousStatement'.
     */
    public void setABE_HZBALS_balanceOnThePreviousStatement(
            final java.lang.String ABE_HZBALS_balanceOnThePreviousStatement) {
        this._ABE_HZBALS_balanceOnThePreviousStatement = ABE_HZBALS_balanceOnThePreviousStatement;
    }

    /**
     * Sets the value of field
     * 'ABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay'.
     * 
     * @param ABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay the
     * value of field
     * 'ABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay'.
     */
    public void setABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay(
            final java.lang.String ABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay) {
        this._ABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay = ABE_HZBAL_balanceAtCloseOfBusinessLastBusinessDay;
    }

    /**
     * Sets the value of field 'ABE_HZC1R_c1RatingForAccount'.
     * 
     * @param ABE_HZC1R_c1RatingForAccount the value of field
     * 'ABE_HZC1R_c1RatingForAccount'.
     */
    public void setABE_HZC1R_c1RatingForAccount(
            final java.lang.String ABE_HZC1R_c1RatingForAccount) {
        this._ABE_HZC1R_c1RatingForAccount = ABE_HZC1R_c1RatingForAccount;
    }

    /**
     * Sets the value of field 'ABE_HZC2R_c2RatingForAccount'.
     * 
     * @param ABE_HZC2R_c2RatingForAccount the value of field
     * 'ABE_HZC2R_c2RatingForAccount'.
     */
    public void setABE_HZC2R_c2RatingForAccount(
            final java.lang.String ABE_HZC2R_c2RatingForAccount) {
        this._ABE_HZC2R_c2RatingForAccount = ABE_HZC2R_c2RatingForAccount;
    }

    /**
     * Sets the value of field 'ABE_HZC3R_c3RatingForAccount'.
     * 
     * @param ABE_HZC3R_c3RatingForAccount the value of field
     * 'ABE_HZC3R_c3RatingForAccount'.
     */
    public void setABE_HZC3R_c3RatingForAccount(
            final java.lang.String ABE_HZC3R_c3RatingForAccount) {
        this._ABE_HZC3R_c3RatingForAccount = ABE_HZC3R_c3RatingForAccount;
    }

    /**
     * Sets the value of field 'ABE_HZC4R_c4RatingForAccount'.
     * 
     * @param ABE_HZC4R_c4RatingForAccount the value of field
     * 'ABE_HZC4R_c4RatingForAccount'.
     */
    public void setABE_HZC4R_c4RatingForAccount(
            final java.lang.String ABE_HZC4R_c4RatingForAccount) {
        this._ABE_HZC4R_c4RatingForAccount = ABE_HZC4R_c4RatingForAccount;
    }

    /**
     * Sets the value of field 'ABE_HZC5R_c5RatingForAccount'.
     * 
     * @param ABE_HZC5R_c5RatingForAccount the value of field
     * 'ABE_HZC5R_c5RatingForAccount'.
     */
    public void setABE_HZC5R_c5RatingForAccount(
            final java.lang.String ABE_HZC5R_c5RatingForAccount) {
        this._ABE_HZC5R_c5RatingForAccount = ABE_HZC5R_c5RatingForAccount;
    }

    /**
     * Sets the value of field 'ABE_HZCAD_dateAccountClosed'.
     * 
     * @param ABE_HZCAD_dateAccountClosed the value of field
     * 'ABE_HZCAD_dateAccountClosed'.
     */
    public void setABE_HZCAD_dateAccountClosed(
            final java.lang.String ABE_HZCAD_dateAccountClosed) {
        this._ABE_HZCAD_dateAccountClosed = ABE_HZCAD_dateAccountClosed;
    }

    /**
     * Sets the value of field 'ABE_HZCAN_accountNumber'.
     * 
     * @param ABE_HZCAN_accountNumber the value of field
     * 'ABE_HZCAN_accountNumber'.
     */
    public void setABE_HZCAN_accountNumber(
            final java.lang.String ABE_HZCAN_accountNumber) {
        this._ABE_HZCAN_accountNumber = ABE_HZCAN_accountNumber;
    }

    /**
     * Sets the value of field 'ABE_HZCCY_currencyMnemonic'.
     * 
     * @param ABE_HZCCY_currencyMnemonic the value of field
     * 'ABE_HZCCY_currencyMnemonic'.
     */
    public void setABE_HZCCY_currencyMnemonic(
            final java.lang.String ABE_HZCCY_currencyMnemonic) {
        this._ABE_HZCCY_currencyMnemonic = ABE_HZCCY_currencyMnemonic;
    }

    /**
     * Sets the value of field 'ABE_HZCED_currencyEditField'.
     * 
     * @param ABE_HZCED_currencyEditField the value of field
     * 'ABE_HZCED_currencyEditField'.
     */
    public void setABE_HZCED_currencyEditField(
            final java.lang.String ABE_HZCED_currencyEditField) {
        this._ABE_HZCED_currencyEditField = ABE_HZCED_currencyEditField;
    }

    /**
     * Sets the value of field 'ABE_HZCLC_customerLocation'.
     * 
     * @param ABE_HZCLC_customerLocation the value of field
     * 'ABE_HZCLC_customerLocation'.
     */
    public void setABE_HZCLC_customerLocation(
            final java.lang.String ABE_HZCLC_customerLocation) {
        this._ABE_HZCLC_customerLocation = ABE_HZCLC_customerLocation;
    }

    /**
     * Sets the value of field 'ABE_HZCLS_closingWithdrawal'.
     * 
     * @param ABE_HZCLS_closingWithdrawal the value of field
     * 'ABE_HZCLS_closingWithdrawal'.
     */
    public void setABE_HZCLS_closingWithdrawal(
            final java.lang.String ABE_HZCLS_closingWithdrawal) {
        this._ABE_HZCLS_closingWithdrawal = ABE_HZCLS_closingWithdrawal;
    }

    /**
     * Sets the value of field 'ABE_HZCNAL_residenceCountry'.
     * 
     * @param ABE_HZCNAL_residenceCountry the value of field
     * 'ABE_HZCNAL_residenceCountry'.
     */
    public void setABE_HZCNAL_residenceCountry(
            final java.lang.String ABE_HZCNAL_residenceCountry) {
        this._ABE_HZCNAL_residenceCountry = ABE_HZCNAL_residenceCountry;
    }

    /**
     * Sets the value of field 'ABE_HZCNAP_parentCountry'.
     * 
     * @param ABE_HZCNAP_parentCountry the value of field
     * 'ABE_HZCNAP_parentCountry'.
     */
    public void setABE_HZCNAP_parentCountry(
            final java.lang.String ABE_HZCNAP_parentCountry) {
        this._ABE_HZCNAP_parentCountry = ABE_HZCNAP_parentCountry;
    }

    /**
     * Sets the value of field 'ABE_HZCNAR_riskCountry'.
     * 
     * @param ABE_HZCNAR_riskCountry the value of field
     * 'ABE_HZCNAR_riskCountry'.
     */
    public void setABE_HZCNAR_riskCountry(
            final java.lang.String ABE_HZCNAR_riskCountry) {
        this._ABE_HZCNAR_riskCountry = ABE_HZCNAR_riskCountry;
    }

    /**
     * Sets the value of field
     * 'ABE_HZCSFC_chargesStatementFrequency'.
     * 
     * @param ABE_HZCSFC_chargesStatementFrequency the value of
     * field 'ABE_HZCSFC_chargesStatementFrequency'.
     */
    public void setABE_HZCSFC_chargesStatementFrequency(
            final java.lang.String ABE_HZCSFC_chargesStatementFrequency) {
        this._ABE_HZCSFC_chargesStatementFrequency = ABE_HZCSFC_chargesStatementFrequency;
    }

    /**
     * Sets the value of field
     * 'ABE_HZCSTL_lastChargesStatementDate'.
     * 
     * @param ABE_HZCSTL_lastChargesStatementDate the value of
     * field 'ABE_HZCSTL_lastChargesStatementDate'.
     */
    public void setABE_HZCSTL_lastChargesStatementDate(
            final java.lang.String ABE_HZCSTL_lastChargesStatementDate) {
        this._ABE_HZCSTL_lastChargesStatementDate = ABE_HZCSTL_lastChargesStatementDate;
    }

    /**
     * Sets the value of field
     * 'ABE_HZCSTN_nextChargesStatementDate'.
     * 
     * @param ABE_HZCSTN_nextChargesStatementDate the value of
     * field 'ABE_HZCSTN_nextChargesStatementDate'.
     */
    public void setABE_HZCSTN_nextChargesStatementDate(
            final java.lang.String ABE_HZCSTN_nextChargesStatementDate) {
        this._ABE_HZCSTN_nextChargesStatementDate = ABE_HZCSTN_nextChargesStatementDate;
    }

    /**
     * Sets the value of field 'ABE_HZCTP_customerType'.
     * 
     * @param ABE_HZCTP_customerType the value of field
     * 'ABE_HZCTP_customerType'.
     */
    public void setABE_HZCTP_customerType(
            final java.lang.String ABE_HZCTP_customerType) {
        this._ABE_HZCTP_customerType = ABE_HZCTP_customerType;
    }

    /**
     * Sets the value of field 'ABE_HZCUNA_arabicCustomerFullName'.
     * 
     * @param ABE_HZCUNA_arabicCustomerFullName the value of field
     * 'ABE_HZCUNA_arabicCustomerFullName'.
     */
    public void setABE_HZCUNA_arabicCustomerFullName(
            final java.lang.String ABE_HZCUNA_arabicCustomerFullName) {
        this._ABE_HZCUNA_arabicCustomerFullName = ABE_HZCUNA_arabicCustomerFullName;
    }

    /**
     * Sets the value of field 'ABE_HZCUN_customerFullName'.
     * 
     * @param ABE_HZCUN_customerFullName the value of field
     * 'ABE_HZCUN_customerFullName'.
     */
    public void setABE_HZCUN_customerFullName(
            final java.lang.String ABE_HZCUN_customerFullName) {
        this._ABE_HZCUN_customerFullName = ABE_HZCUN_customerFullName;
    }

    /**
     * Sets the value of field 'ABE_HZCUS_customerMnemonic'.
     * 
     * @param ABE_HZCUS_customerMnemonic the value of field
     * 'ABE_HZCUS_customerMnemonic'.
     */
    public void setABE_HZCUS_customerMnemonic(
            final java.lang.String ABE_HZCUS_customerMnemonic) {
        this._ABE_HZCUS_customerMnemonic = ABE_HZCUS_customerMnemonic;
    }

    /**
     * Sets the value of field
     * 'ABE_HZDFRM_fontisDownloadFrequencyMeridian'.
     * 
     * @param ABE_HZDFRM_fontisDownloadFrequencyMeridian the value
     * of field 'ABE_HZDFRM_fontisDownloadFrequencyMeridian'.
     */
    public void setABE_HZDFRM_fontisDownloadFrequencyMeridian(
            final java.lang.String ABE_HZDFRM_fontisDownloadFrequencyMeridian) {
        this._ABE_HZDFRM_fontisDownloadFrequencyMeridian = ABE_HZDFRM_fontisDownloadFrequencyMeridian;
    }

    /**
     * Sets the value of field
     * 'ABE_HZDFRQ_fontisDownloadFrequency'.
     * 
     * @param ABE_HZDFRQ_fontisDownloadFrequency the value of field
     * 'ABE_HZDFRQ_fontisDownloadFrequency'.
     */
    public void setABE_HZDFRQ_fontisDownloadFrequency(
            final java.lang.String ABE_HZDFRQ_fontisDownloadFrequency) {
        this._ABE_HZDFRQ_fontisDownloadFrequency = ABE_HZDFRQ_fontisDownloadFrequency;
    }

    /**
     * Sets the value of field
     * 'ABE_HZDLE_dateOfLastCustomerGeneratedEntry'.
     * 
     * @param ABE_HZDLE_dateOfLastCustomerGeneratedEntry the value
     * of field 'ABE_HZDLE_dateOfLastCustomerGeneratedEntry'.
     */
    public void setABE_HZDLE_dateOfLastCustomerGeneratedEntry(
            final java.lang.String ABE_HZDLE_dateOfLastCustomerGeneratedEntry) {
        this._ABE_HZDLE_dateOfLastCustomerGeneratedEntry = ABE_HZDLE_dateOfLastCustomerGeneratedEntry;
    }

    /**
     * Sets the value of field
     * 'ABE_HZDLMZ_instructionDateLastMaintained'.
     * 
     * @param ABE_HZDLMZ_instructionDateLastMaintained the value of
     * field 'ABE_HZDLMZ_instructionDateLastMaintained'.
     */
    public void setABE_HZDLMZ_instructionDateLastMaintained(
            final java.lang.String ABE_HZDLMZ_instructionDateLastMaintained) {
        this._ABE_HZDLMZ_instructionDateLastMaintained = ABE_HZDLMZ_instructionDateLastMaintained;
    }

    /**
     * Sets the value of field 'ABE_HZDLM_dateLastMaintained'.
     * 
     * @param ABE_HZDLM_dateLastMaintained the value of field
     * 'ABE_HZDLM_dateLastMaintained'.
     */
    public void setABE_HZDLM_dateLastMaintained(
            final java.lang.String ABE_HZDLM_dateLastMaintained) {
        this._ABE_HZDLM_dateLastMaintained = ABE_HZDLM_dateLastMaintained;
    }

    /**
     * Sets the value of field 'ABE_HZHLD_held'.
     * 
     * @param ABE_HZHLD_held the value of field 'ABE_HZHLD_held'.
     */
    public void setABE_HZHLD_held(
            final java.lang.String ABE_HZHLD_held) {
        this._ABE_HZHLD_held = ABE_HZHLD_held;
    }

    /**
     * Sets the value of field 'ABE_HZLNM_languageCode'.
     * 
     * @param ABE_HZLNM_languageCode the value of field
     * 'ABE_HZLNM_languageCode'.
     */
    public void setABE_HZLNM_languageCode(
            final java.lang.String ABE_HZLNM_languageCode) {
        this._ABE_HZLNM_languageCode = ABE_HZLNM_languageCode;
    }

    /**
     * Sets the value of field 'ABE_HZNANC_numericAnalysisCode'.
     * 
     * @param ABE_HZNANC_numericAnalysisCode the value of field
     * 'ABE_HZNANC_numericAnalysisCode'.
     */
    public void setABE_HZNANC_numericAnalysisCode(
            final java.lang.String ABE_HZNANC_numericAnalysisCode) {
        this._ABE_HZNANC_numericAnalysisCode = ABE_HZNANC_numericAnalysisCode;
    }

    /**
     * Sets the value of field
     * 'ABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement'.
     * 
     * @param ABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement
     * the value of field
     * 'ABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement'.
     */
    public void setABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement(
            final java.lang.String ABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement) {
        this._ABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement = ABE_HZNPE_numberOfPostingsNotYetPrintedOnAStatement;
    }

    /**
     * Sets the value of field
     * 'ABE_HZNS3_numberOfReferenceCharactersForReconciliation'.
     * 
     * @param
     * ABE_HZNS3_numberOfReferenceCharactersForReconciliation the
     * value of field
     * 'ABE_HZNS3_numberOfReferenceCharactersForReconciliation'.
     */
    public void setABE_HZNS3_numberOfReferenceCharactersForReconciliation(
            final java.lang.String ABE_HZNS3_numberOfReferenceCharactersForReconciliation) {
        this._ABE_HZNS3_numberOfReferenceCharactersForReconciliation = ABE_HZNS3_numberOfReferenceCharactersForReconciliation;
    }

    /**
     * Sets the value of field 'ABE_HZNTAD_taxAdviceTypeNextYear'.
     * 
     * @param ABE_HZNTAD_taxAdviceTypeNextYear the value of field
     * 'ABE_HZNTAD_taxAdviceTypeNextYear'.
     */
    public void setABE_HZNTAD_taxAdviceTypeNextYear(
            final java.lang.String ABE_HZNTAD_taxAdviceTypeNextYear) {
        this._ABE_HZNTAD_taxAdviceTypeNextYear = ABE_HZNTAD_taxAdviceTypeNextYear;
    }

    /**
     * Sets the value of field 'ABE_HZOAD_dateAccountOpened'.
     * 
     * @param ABE_HZOAD_dateAccountOpened the value of field
     * 'ABE_HZOAD_dateAccountOpened'.
     */
    public void setABE_HZOAD_dateAccountOpened(
            final java.lang.String ABE_HZOAD_dateAccountOpened) {
        this._ABE_HZOAD_dateAccountOpened = ABE_HZOAD_dateAccountOpened;
    }

    /**
     * Sets the value of field 'ABE_HZP1R_p1RatingForAccount'.
     * 
     * @param ABE_HZP1R_p1RatingForAccount the value of field
     * 'ABE_HZP1R_p1RatingForAccount'.
     */
    public void setABE_HZP1R_p1RatingForAccount(
            final java.lang.String ABE_HZP1R_p1RatingForAccount) {
        this._ABE_HZP1R_p1RatingForAccount = ABE_HZP1R_p1RatingForAccount;
    }

    /**
     * Sets the value of field 'ABE_HZP2R_p2RatingForAccount'.
     * 
     * @param ABE_HZP2R_p2RatingForAccount the value of field
     * 'ABE_HZP2R_p2RatingForAccount'.
     */
    public void setABE_HZP2R_p2RatingForAccount(
            final java.lang.String ABE_HZP2R_p2RatingForAccount) {
        this._ABE_HZP2R_p2RatingForAccount = ABE_HZP2R_p2RatingForAccount;
    }

    /**
     * Sets the value of field 'ABE_HZP3R_p3RatingForAccount'.
     * 
     * @param ABE_HZP3R_p3RatingForAccount the value of field
     * 'ABE_HZP3R_p3RatingForAccount'.
     */
    public void setABE_HZP3R_p3RatingForAccount(
            final java.lang.String ABE_HZP3R_p3RatingForAccount) {
        this._ABE_HZP3R_p3RatingForAccount = ABE_HZP3R_p3RatingForAccount;
    }

    /**
     * Sets the value of field 'ABE_HZP4R_p4RatingForAccount'.
     * 
     * @param ABE_HZP4R_p4RatingForAccount the value of field
     * 'ABE_HZP4R_p4RatingForAccount'.
     */
    public void setABE_HZP4R_p4RatingForAccount(
            final java.lang.String ABE_HZP4R_p4RatingForAccount) {
        this._ABE_HZP4R_p4RatingForAccount = ABE_HZP4R_p4RatingForAccount;
    }

    /**
     * Sets the value of field 'ABE_HZP5R_p5RatingForAccount'.
     * 
     * @param ABE_HZP5R_p5RatingForAccount the value of field
     * 'ABE_HZP5R_p5RatingForAccount'.
     */
    public void setABE_HZP5R_p5RatingForAccount(
            final java.lang.String ABE_HZP5R_p5RatingForAccount) {
        this._ABE_HZP5R_p5RatingForAccount = ABE_HZP5R_p5RatingForAccount;
    }

    /**
     * Sets the value of field 'ABE_HZPAB_sourceOriginalBranch'.
     * 
     * @param ABE_HZPAB_sourceOriginalBranch the value of field
     * 'ABE_HZPAB_sourceOriginalBranch'.
     */
    public void setABE_HZPAB_sourceOriginalBranch(
            final java.lang.String ABE_HZPAB_sourceOriginalBranch) {
        this._ABE_HZPAB_sourceOriginalBranch = ABE_HZPAB_sourceOriginalBranch;
    }

    /**
     * Sets the value of field 'ABE_HZPCHD_branchChangeDate'.
     * 
     * @param ABE_HZPCHD_branchChangeDate the value of field
     * 'ABE_HZPCHD_branchChangeDate'.
     */
    public void setABE_HZPCHD_branchChangeDate(
            final java.lang.String ABE_HZPCHD_branchChangeDate) {
        this._ABE_HZPCHD_branchChangeDate = ABE_HZPCHD_branchChangeDate;
    }

    /**
     * Sets the value of field 'ABE_HZREFA_settlementReference'.
     * 
     * @param ABE_HZREFA_settlementReference the value of field
     * 'ABE_HZREFA_settlementReference'.
     */
    public void setABE_HZREFA_settlementReference(
            final java.lang.String ABE_HZREFA_settlementReference) {
        this._ABE_HZREFA_settlementReference = ABE_HZREFA_settlementReference;
    }

    /**
     * Sets the value of field 'ABE_HZREF_lastCpReference'.
     * 
     * @param ABE_HZREF_lastCpReference the value of field
     * 'ABE_HZREF_lastCpReference'.
     */
    public void setABE_HZREF_lastCpReference(
            final java.lang.String ABE_HZREF_lastCpReference) {
        this._ABE_HZREF_lastCpReference = ABE_HZREF_lastCpReference;
    }

    /**
     * Sets the value of field
     * 'ABE_HZRETP_retentionPeriod00None011MthSysDflt'.
     * 
     * @param ABE_HZRETP_retentionPeriod00None011MthSysDflt the
     * value of field
     * 'ABE_HZRETP_retentionPeriod00None011MthSysDflt'.
     */
    public void setABE_HZRETP_retentionPeriod00None011MthSysDflt(
            final java.lang.String ABE_HZRETP_retentionPeriod00None011MthSysDflt) {
        this._ABE_HZRETP_retentionPeriod00None011MthSysDflt = ABE_HZRETP_retentionPeriod00None011MthSysDflt;
    }

    /**
     * Sets the value of field 'ABE_HZSAC_sundryAnalysisCode'.
     * 
     * @param ABE_HZSAC_sundryAnalysisCode the value of field
     * 'ABE_HZSAC_sundryAnalysisCode'.
     */
    public void setABE_HZSAC_sundryAnalysisCode(
            final java.lang.String ABE_HZSAC_sundryAnalysisCode) {
        this._ABE_HZSAC_sundryAnalysisCode = ABE_HZSAC_sundryAnalysisCode;
    }

    /**
     * Sets the value of field 'ABE_HZSFC_statementFrequencyCode'.
     * 
     * @param ABE_HZSFC_statementFrequencyCode the value of field
     * 'ABE_HZSFC_statementFrequencyCode'.
     */
    public void setABE_HZSFC_statementFrequencyCode(
            final java.lang.String ABE_HZSFC_statementFrequencyCode) {
        this._ABE_HZSFC_statementFrequencyCode = ABE_HZSFC_statementFrequencyCode;
    }

    /**
     * Sets the value of field 'ABE_HZSHNA_arabicShortName'.
     * 
     * @param ABE_HZSHNA_arabicShortName the value of field
     * 'ABE_HZSHNA_arabicShortName'.
     */
    public void setABE_HZSHNA_arabicShortName(
            final java.lang.String ABE_HZSHNA_arabicShortName) {
        this._ABE_HZSHNA_arabicShortName = ABE_HZSHNA_arabicShortName;
    }

    /**
     * Sets the value of field 'ABE_HZSHN_accountShortName'.
     * 
     * @param ABE_HZSHN_accountShortName the value of field
     * 'ABE_HZSHN_accountShortName'.
     */
    public void setABE_HZSHN_accountShortName(
            final java.lang.String ABE_HZSHN_accountShortName) {
        this._ABE_HZSHN_accountShortName = ABE_HZSHN_accountShortName;
    }

    /**
     * Sets the value of field 'ABE_HZSN20_externalAccountNumber'.
     * 
     * @param ABE_HZSN20_externalAccountNumber the value of field
     * 'ABE_HZSN20_externalAccountNumber'.
     */
    public void setABE_HZSN20_externalAccountNumber(
            final java.lang.String ABE_HZSN20_externalAccountNumber) {
        this._ABE_HZSN20_externalAccountNumber = ABE_HZSN20_externalAccountNumber;
    }

    /**
     * Sets the value of field
     * 'ABE_HZSNA_chargeNominatedAccountArabicShortName'.
     * 
     * @param ABE_HZSNA_chargeNominatedAccountArabicShortName the
     * value of field
     * 'ABE_HZSNA_chargeNominatedAccountArabicShortName'.
     */
    public void setABE_HZSNA_chargeNominatedAccountArabicShortName(
            final java.lang.String ABE_HZSNA_chargeNominatedAccountArabicShortName) {
        this._ABE_HZSNA_chargeNominatedAccountArabicShortName = ABE_HZSNA_chargeNominatedAccountArabicShortName;
    }

    /**
     * Sets the value of field
     * 'ABE_HZSNCA_creditNominatedAccountArabicShortName'.
     * 
     * @param ABE_HZSNCA_creditNominatedAccountArabicShortName the
     * value of field
     * 'ABE_HZSNCA_creditNominatedAccountArabicShortName'.
     */
    public void setABE_HZSNCA_creditNominatedAccountArabicShortName(
            final java.lang.String ABE_HZSNCA_creditNominatedAccountArabicShortName) {
        this._ABE_HZSNCA_creditNominatedAccountArabicShortName = ABE_HZSNCA_creditNominatedAccountArabicShortName;
    }

    /**
     * Sets the value of field
     * 'ABE_HZSNDA_debitNominatedAccountArabicShortName'.
     * 
     * @param ABE_HZSNDA_debitNominatedAccountArabicShortName the
     * value of field
     * 'ABE_HZSNDA_debitNominatedAccountArabicShortName'.
     */
    public void setABE_HZSNDA_debitNominatedAccountArabicShortName(
            final java.lang.String ABE_HZSNDA_debitNominatedAccountArabicShortName) {
        this._ABE_HZSNDA_debitNominatedAccountArabicShortName = ABE_HZSNDA_debitNominatedAccountArabicShortName;
    }

    /**
     * Sets the value of field
     * 'ABE_HZSNGA_chargeNominatedAccountArabicShortName'.
     * 
     * @param ABE_HZSNGA_chargeNominatedAccountArabicShortName the
     * value of field
     * 'ABE_HZSNGA_chargeNominatedAccountArabicShortName'.
     */
    public void setABE_HZSNGA_chargeNominatedAccountArabicShortName(
            final java.lang.String ABE_HZSNGA_chargeNominatedAccountArabicShortName) {
        this._ABE_HZSNGA_chargeNominatedAccountArabicShortName = ABE_HZSNGA_chargeNominatedAccountArabicShortName;
    }

    /**
     * Sets the value of field
     * 'ABE_HZSNMA_masterInterestAccountArabicShortName'.
     * 
     * @param ABE_HZSNMA_masterInterestAccountArabicShortName the
     * value of field
     * 'ABE_HZSNMA_masterInterestAccountArabicShortName'.
     */
    public void setABE_HZSNMA_masterInterestAccountArabicShortName(
            final java.lang.String ABE_HZSNMA_masterInterestAccountArabicShortName) {
        this._ABE_HZSNMA_masterInterestAccountArabicShortName = ABE_HZSNMA_masterInterestAccountArabicShortName;
    }

    /**
     * Sets the value of field
     * 'ABE_HZSNM_masterInterestAccountShortName'.
     * 
     * @param ABE_HZSNM_masterInterestAccountShortName the value of
     * field 'ABE_HZSNM_masterInterestAccountShortName'.
     */
    public void setABE_HZSNM_masterInterestAccountShortName(
            final java.lang.String ABE_HZSNM_masterInterestAccountShortName) {
        this._ABE_HZSNM_masterInterestAccountShortName = ABE_HZSNM_masterInterestAccountShortName;
    }

    /**
     * Sets the value of field
     * 'ABE_HZSNNC_creditNominatedAccountShortName'.
     * 
     * @param ABE_HZSNNC_creditNominatedAccountShortName the value
     * of field 'ABE_HZSNNC_creditNominatedAccountShortName'.
     */
    public void setABE_HZSNNC_creditNominatedAccountShortName(
            final java.lang.String ABE_HZSNNC_creditNominatedAccountShortName) {
        this._ABE_HZSNNC_creditNominatedAccountShortName = ABE_HZSNNC_creditNominatedAccountShortName;
    }

    /**
     * Sets the value of field
     * 'ABE_HZSNND_debitNominatedAccountShortName'.
     * 
     * @param ABE_HZSNND_debitNominatedAccountShortName the value
     * of field 'ABE_HZSNND_debitNominatedAccountShortName'.
     */
    public void setABE_HZSNND_debitNominatedAccountShortName(
            final java.lang.String ABE_HZSNND_debitNominatedAccountShortName) {
        this._ABE_HZSNND_debitNominatedAccountShortName = ABE_HZSNND_debitNominatedAccountShortName;
    }

    /**
     * Sets the value of field
     * 'ABE_HZSNNG_chargeNominatedAccountShortName'.
     * 
     * @param ABE_HZSNNG_chargeNominatedAccountShortName the value
     * of field 'ABE_HZSNNG_chargeNominatedAccountShortName'.
     */
    public void setABE_HZSNNG_chargeNominatedAccountShortName(
            final java.lang.String ABE_HZSNNG_chargeNominatedAccountShortName) {
        this._ABE_HZSNNG_chargeNominatedAccountShortName = ABE_HZSNNG_chargeNominatedAccountShortName;
    }

    /**
     * Sets the value of field
     * 'ABE_HZSNN_chargeNominatedAccountShortName'.
     * 
     * @param ABE_HZSNN_chargeNominatedAccountShortName the value
     * of field 'ABE_HZSNN_chargeNominatedAccountShortName'.
     */
    public void setABE_HZSNN_chargeNominatedAccountShortName(
            final java.lang.String ABE_HZSNN_chargeNominatedAccountShortName) {
        this._ABE_HZSNN_chargeNominatedAccountShortName = ABE_HZSNN_chargeNominatedAccountShortName;
    }

    /**
     * Sets the value of field 'ABE_HZSTA_status'.
     * 
     * @param ABE_HZSTA_status the value of field 'ABE_HZSTA_status'
     */
    public void setABE_HZSTA_status(
            final java.lang.String ABE_HZSTA_status) {
        this._ABE_HZSTA_status = ABE_HZSTA_status;
    }

    /**
     * Sets the value of field 'ABE_HZSTML_lastStatementDate'.
     * 
     * @param ABE_HZSTML_lastStatementDate the value of field
     * 'ABE_HZSTML_lastStatementDate'.
     */
    public void setABE_HZSTML_lastStatementDate(
            final java.lang.String ABE_HZSTML_lastStatementDate) {
        this._ABE_HZSTML_lastStatementDate = ABE_HZSTML_lastStatementDate;
    }

    /**
     * Sets the value of field 'ABE_HZSTMN_nextStatementDate'.
     * 
     * @param ABE_HZSTMN_nextStatementDate the value of field
     * 'ABE_HZSTMN_nextStatementDate'.
     */
    public void setABE_HZSTMN_nextStatementDate(
            final java.lang.String ABE_HZSTMN_nextStatementDate) {
        this._ABE_HZSTMN_nextStatementDate = ABE_HZSTMN_nextStatementDate;
    }

    /**
     * Sets the value of field 'ABE_HZSTNL_lastStatementNumber'.
     * 
     * @param ABE_HZSTNL_lastStatementNumber the value of field
     * 'ABE_HZSTNL_lastStatementNumber'.
     */
    public void setABE_HZSTNL_lastStatementNumber(
            final java.lang.String ABE_HZSTNL_lastStatementNumber) {
        this._ABE_HZSTNL_lastStatementNumber = ABE_HZSTNL_lastStatementNumber;
    }

    /**
     * Sets the value of field 'ABE_HZTADT_taxAdviceType'.
     * 
     * @param ABE_HZTADT_taxAdviceType the value of field
     * 'ABE_HZTADT_taxAdviceType'.
     */
    public void setABE_HZTADT_taxAdviceType(
            final java.lang.String ABE_HZTADT_taxAdviceType) {
        this._ABE_HZTADT_taxAdviceType = ABE_HZTADT_taxAdviceType;
    }

    /**
     * Sets the value of field 'ABE_HZTRM_transferMethod'.
     * 
     * @param ABE_HZTRM_transferMethod the value of field
     * 'ABE_HZTRM_transferMethod'.
     */
    public void setABE_HZTRM_transferMethod(
            final java.lang.String ABE_HZTRM_transferMethod) {
        this._ABE_HZTRM_transferMethod = ABE_HZTRM_transferMethod;
    }

    /**
     * Sets the value of field 'ABE_HZUNMW_unmaturedWithdrawal'.
     * 
     * @param ABE_HZUNMW_unmaturedWithdrawal the value of field
     * 'ABE_HZUNMW_unmaturedWithdrawal'.
     */
    public void setABE_HZUNMW_unmaturedWithdrawal(
            final java.lang.String ABE_HZUNMW_unmaturedWithdrawal) {
        this._ABE_HZUNMW_unmaturedWithdrawal = ABE_HZUNMW_unmaturedWithdrawal;
    }

    /**
     * Sets the value of field
     * 'ABE_HZXMD_transferMethodDescription'.
     * 
     * @param ABE_HZXMD_transferMethodDescription the value of
     * field 'ABE_HZXMD_transferMethodDescription'.
     */
    public void setABE_HZXMD_transferMethodDescription(
            final java.lang.String ABE_HZXMD_transferMethodDescription) {
        this._ABE_HZXMD_transferMethodDescription = ABE_HZXMD_transferMethodDescription;
    }

    /**
     * Sets the value of field 'ABE_HZYFON_fontisAccount'.
     * 
     * @param ABE_HZYFON_fontisAccount the value of field
     * 'ABE_HZYFON_fontisAccount'.
     */
    public void setABE_HZYFON_fontisAccount(
            final java.lang.String ABE_HZYFON_fontisAccount) {
        this._ABE_HZYFON_fontisAccount = ABE_HZYFON_fontisAccount;
    }

    /**
     * Sets the value of field 'CRG1_crg1'.
     * 
     * @param CRG1_crg1 the value of field 'CRG1_crg1'.
     */
    public void setCRG1_crg1(
            final java.lang.String CRG1_crg1) {
        this._CRG1_crg1 = CRG1_crg1;
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
     * Method unmarshalX76.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled bf.com.misys.eq4.service.x76.X76
     */
    public static bf.com.misys.eq4.service.x76.X76 unmarshalX76(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (bf.com.misys.eq4.service.x76.X76) Unmarshaller.unmarshal(bf.com.misys.eq4.service.x76.X76.class, reader);
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
