/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.1.1</a>, using an XML Schema. $Id:
 * ZT1.java,v 1.5 2009/09/25 09:38:44 blossem1 Exp $
 */

package bf.com.misys.eq4.service.zt1;

// ---------------------------------/
// - Imported classes and packages -/
// ---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class ZT1.
 * 
 * @version $Revision$ $Date$
 */
public class ZT1 implements java.io.Serializable
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ZT1.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	// --------------------------/
	// - Class/Member Variables -/
	// --------------------------/

	/**
	 * Field _ANC_CUS_customerMnemonic.
	 */
	private java.lang.String _ANC_CUS_customerMnemonic;

	/**
	 * Field _ANC_CLC_customerLocation.
	 */
	private java.lang.String _ANC_CLC_customerLocation;

	/**
	 * Field _ANC_CUN_customerFullName.
	 */
	private java.lang.String _ANC_CUN_customerFullName;

	/**
	 * Field _ANC_CPNC_customersBasicNumber.
	 */
	private java.lang.String _ANC_CPNC_customersBasicNumber;

	/**
	 * Field _ANC_DAS_defaultAccountShortName.
	 */
	private java.lang.String _ANC_DAS_defaultAccountShortName;

	/**
	 * Field _ANC_CTP_customerType.
	 */
	private java.lang.String _ANC_CTP_customerType;

	/**
	 * Field _ANC_BRNM_branchMnemonic.
	 */
	private java.lang.String _ANC_BRNM_branchMnemonic;

	/**
	 * Field _ANC_CRB1_taxCode1.
	 */
	private java.lang.String _ANC_CRB1_taxCode1;

	/**
	 * Field _ANC_CRB2_taxCode2.
	 */
	private java.lang.String _ANC_CRB2_taxCode2;

	/**
	 * Field _ANX_EAD1_eMailAddress1.
	 */
	private java.lang.String _ANX_EAD1_eMailAddress1;

	/**
	 * Field _CAA_NA1_addressLine1.
	 */
	private java.lang.String _CAA_NA1_addressLine1;

	/**
	 * Field _CAA_NA2_addressLine2.
	 */
	private java.lang.String _CAA_NA2_addressLine2;

	/**
	 * Field _CAA_NA3_addressLine3.
	 */
	private java.lang.String _CAA_NA3_addressLine3;

	/**
	 * Field _CAA_NA4_addressLine4.
	 */
	private java.lang.String _CAA_NA4_addressLine4;

	/**
	 * Field _CAA_NA5_addressLine5.
	 */
	private java.lang.String _CAA_NA5_addressLine5;

	/**
	 * Field _MCO_C1R_customersC1Rating.
	 */
	private java.lang.String _MCO_C1R_customersC1Rating;

	/**
	 * Field _OCA_AB_accountBranch.
	 */
	private java.lang.String _OCA_AB_accountBranch;

	/**
	 * Field _OCA_AN_basicPartOfAccountNumber.
	 */
	private java.lang.String _OCA_AN_basicPartOfAccountNumber;

	/**
	 * Field _OCA_AS_accountSuffix.
	 */
	private java.lang.String _OCA_AS_accountSuffix;

	/**
	 * Field _OCA_ACT_accountType.
	 */
	private java.lang.String _OCA_ACT_accountType;

	/**
	 * Field _OCA_SHN_accountShortName.
	 */
	private java.lang.String _OCA_SHN_accountShortName;

	/**
	 * Field _OCA_CCY_currencyMnemonic.
	 */
	private java.lang.String _OCA_CCY_currencyMnemonic;

	/**
	 * Field _OCA_OAD_dateAccountOpened.
	 */
	private java.lang.String _OCA_OAD_dateAccountOpened;

	/**
	 * Field _RDS_DLP_dealType.
	 */
	private java.lang.String _RDS_DLP_dealType;

	/**
	 * Field _RDS_DLR_dealReference.
	 */
	private java.lang.String _RDS_DLR_dealReference;

	/**
	 * Field _RDS_BRNM_branchMnemonic.
	 */
	private java.lang.String _RDS_BRNM_branchMnemonic;

	/**
	 * Field _RDS_CUS_customerMnemonic.
	 */
	private java.lang.String _RDS_CUS_customerMnemonic;

	/**
	 * Field _RDS_CLC_customerLocation.
	 */
	private java.lang.String _RDS_CLC_customerLocation;

	/**
	 * Field _RDS_CCY_currencyMnemonic.
	 */
	private java.lang.String _RDS_CCY_currencyMnemonic;

	/**
	 * Field _RDS_DLA_dealAmount.
	 */
	private java.lang.String _RDS_DLA_dealAmount;

	/**
	 * Field _RDS_SDT_startDate.
	 */
	private java.lang.String _RDS_SDT_startDate;

	/**
	 * Field _RDS_MDT_maturityDate.
	 */
	private java.lang.String _RDS_MDT_maturityDate;

	/**
	 * Field _RDS_CTRD_contractDate.
	 */
	private java.lang.String _RDS_CTRD_contractDate;

	/**
	 * Field _RDS_ABF_fundingSettlementBranch.
	 */
	private java.lang.String _RDS_ABF_fundingSettlementBranch;

	/**
	 * Field _RDS_ANF_fundingSettlementACNo.
	 */
	private java.lang.String _RDS_ANF_fundingSettlementACNo;

	/**
	 * Field _RDS_ASF_fundingSettlementACSfx.
	 */
	private java.lang.String _RDS_ASF_fundingSettlementACSfx;

	/**
	 * Field _RDS_XMF_fundingTransferMethod.
	 */
	private java.lang.String _RDS_XMF_fundingTransferMethod;

	/**
	 * Field _RDS_ABM_maturitySettlementBranch.
	 */
	private java.lang.String _RDS_ABM_maturitySettlementBranch;

	/**
	 * Field _RDS_ANM_maturitySettlementACNo.
	 */
	private java.lang.String _RDS_ANM_maturitySettlementACNo;

	/**
	 * Field _RDS_ASM_maturitySettlementACSfx.
	 */
	private java.lang.String _RDS_ASM_maturitySettlementACSfx;

	/**
	 * Field _RDS_XMM_maturityTransferMethod.
	 */
	private java.lang.String _RDS_XMM_maturityTransferMethod;

	/**
	 * Field _RDS_ABI_interestSettlementBranch.
	 */
	private java.lang.String _RDS_ABI_interestSettlementBranch;

	/**
	 * Field _RDS_ANI_interestSettlementACNo.
	 */
	private java.lang.String _RDS_ANI_interestSettlementACNo;

	/**
	 * Field _RDS_ASI_interestSettlementACSfx.
	 */
	private java.lang.String _RDS_ASI_interestSettlementACSfx;

	/**
	 * Field _RDS_XMI_interestTransferMethod.
	 */
	private java.lang.String _RDS_XMI_interestTransferMethod;

	/**
	 * Field _RDS_PRC_periodCode.
	 */
	private java.lang.String _RDS_PRC_periodCode;

	/**
	 * Field _ASC_AB_accountBranch.
	 */
	private java.lang.String _ASC_AB_accountBranch;

	/**
	 * Field _ASC_AN_basicPartOfAccountNumber.
	 */
	private java.lang.String _ASC_AN_basicPartOfAccountNumber;

	/**
	 * Field _ASC_AS_accountSuffix.
	 */
	private java.lang.String _ASC_AS_accountSuffix;

	/**
	 * Field _ASC_VFR_valueFromDate.
	 */
	private java.lang.String _ASC_VFR_valueFromDate;

	/**
	 * Field _ASC_DRF_usersOwnReferenceForDealsReconciliationEtc.
	 */
	private java.lang.String _ASC_DRF_usersOwnReferenceForDealsReconciliationEtc;

	/**
	 * Field _ASC_AMA_amount.
	 */
	private java.lang.String _ASC_AMA_amount;

	/**
	 * Field _ASC_TCD_transactionCode.
	 */
	private java.lang.String _ASC_TCD_transactionCode;

	/**
	 * Field _ASD_AB_accountBranch.
	 */
	private java.lang.String _ASD_AB_accountBranch;

	/**
	 * Field _ASD_AN_basicPartOfAccountNumber.
	 */
	private java.lang.String _ASD_AN_basicPartOfAccountNumber;

	/**
	 * Field _ASD_AS_accountSuffix.
	 */
	private java.lang.String _ASD_AS_accountSuffix;

	/**
	 * Field _ASD_TCD_transactionCode.
	 */
	private java.lang.String _ASD_TCD_transactionCode;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public ZT1()
	{
		// This attribute is used to store cvs version information.
		super();
	}

	// -----------/
	// - Methods -/
	// -----------/

	/**
	 * Returns the value of field 'ANC_BRNM_branchMnemonic'.
	 * 
	 * @return the value of field 'ANC_BRNM_branchMnemonic'.
	 */
	public java.lang.String getANC_BRNM_branchMnemonic()
	{
		return this._ANC_BRNM_branchMnemonic;
	}

	/**
	 * Returns the value of field 'ANC_CLC_customerLocation'.
	 * 
	 * @return the value of field 'ANC_CLC_customerLocation'.
	 */
	public java.lang.String getANC_CLC_customerLocation()
	{
		return this._ANC_CLC_customerLocation;
	}

	/**
	 * Returns the value of field 'ANC_CPNC_customersBasicNumber'.
	 * 
	 * @return the value of field 'ANC_CPNC_customersBasicNumber'.
	 */
	public java.lang.String getANC_CPNC_customersBasicNumber()
	{
		return this._ANC_CPNC_customersBasicNumber;
	}

	/**
	 * Returns the value of field 'ANC_CRB1_taxCode1'.
	 * 
	 * @return the value of field 'ANC_CRB1_taxCode1'.
	 */
	public java.lang.String getANC_CRB1_taxCode1()
	{
		return this._ANC_CRB1_taxCode1;
	}

	/**
	 * Returns the value of field 'ANC_CRB2_taxCode2'.
	 * 
	 * @return the value of field 'ANC_CRB2_taxCode2'.
	 */
	public java.lang.String getANC_CRB2_taxCode2()
	{
		return this._ANC_CRB2_taxCode2;
	}

	/**
	 * Returns the value of field 'ANC_CTP_customerType'.
	 * 
	 * @return the value of field 'ANC_CTP_customerType'.
	 */
	public java.lang.String getANC_CTP_customerType()
	{
		return this._ANC_CTP_customerType;
	}

	/**
	 * Returns the value of field 'ANC_CUN_customerFullName'.
	 * 
	 * @return the value of field 'ANC_CUN_customerFullName'.
	 */
	public java.lang.String getANC_CUN_customerFullName()
	{
		return this._ANC_CUN_customerFullName;
	}

	/**
	 * Returns the value of field 'ANC_CUS_customerMnemonic'.
	 * 
	 * @return the value of field 'ANC_CUS_customerMnemonic'.
	 */
	public java.lang.String getANC_CUS_customerMnemonic()
	{
		return this._ANC_CUS_customerMnemonic;
	}

	/**
	 * Returns the value of field 'ANC_DAS_defaultAccountShortName'.
	 * 
	 * @return the value of field 'ANC_DAS_defaultAccountShortName'.
	 */
	public java.lang.String getANC_DAS_defaultAccountShortName()
	{
		return this._ANC_DAS_defaultAccountShortName;
	}

	/**
	 * Returns the value of field 'ANX_EAD1_eMailAddress1'.
	 * 
	 * @return the value of field 'ANX_EAD1_eMailAddress1'.
	 */
	public java.lang.String getANX_EAD1_eMailAddress1()
	{
		return this._ANX_EAD1_eMailAddress1;
	}

	/**
	 * Returns the value of field 'ASC_AB_accountBranch'.
	 * 
	 * @return the value of field 'ASC_AB_accountBranch'.
	 */
	public java.lang.String getASC_AB_accountBranch()
	{
		return this._ASC_AB_accountBranch;
	}

	/**
	 * Returns the value of field 'ASC_AMA_amount'.
	 * 
	 * @return the value of field 'ASC_AMA_amount'.
	 */
	public java.lang.String getASC_AMA_amount()
	{
		return this._ASC_AMA_amount;
	}

	/**
	 * Returns the value of field 'ASC_AN_basicPartOfAccountNumber'.
	 * 
	 * @return the value of field 'ASC_AN_basicPartOfAccountNumber'.
	 */
	public java.lang.String getASC_AN_basicPartOfAccountNumber()
	{
		return this._ASC_AN_basicPartOfAccountNumber;
	}

	/**
	 * Returns the value of field 'ASC_AS_accountSuffix'.
	 * 
	 * @return the value of field 'ASC_AS_accountSuffix'.
	 */
	public java.lang.String getASC_AS_accountSuffix()
	{
		return this._ASC_AS_accountSuffix;
	}

	/**
	 * Returns the value of field 'ASC_DRF_usersOwnReferenceForDealsReconciliationEtc'.
	 * 
	 * @return the value of field 'ASC_DRF_usersOwnReferenceForDealsReconciliationEtc'.
	 */
	public java.lang.String getASC_DRF_usersOwnReferenceForDealsReconciliationEtc()
	{
		return this._ASC_DRF_usersOwnReferenceForDealsReconciliationEtc;
	}

	/**
	 * Returns the value of field 'ASC_TCD_transactionCode'.
	 * 
	 * @return the value of field 'ASC_TCD_transactionCode'.
	 */
	public java.lang.String getASC_TCD_transactionCode()
	{
		return this._ASC_TCD_transactionCode;
	}

	/**
	 * Returns the value of field 'ASC_VFR_valueFromDate'.
	 * 
	 * @return the value of field 'ASC_VFR_valueFromDate'.
	 */
	public java.lang.String getASC_VFR_valueFromDate()
	{
		return this._ASC_VFR_valueFromDate;
	}

	/**
	 * Returns the value of field 'ASD_AB_accountBranch'.
	 * 
	 * @return the value of field 'ASD_AB_accountBranch'.
	 */
	public java.lang.String getASD_AB_accountBranch()
	{
		return this._ASD_AB_accountBranch;
	}

	/**
	 * Returns the value of field 'ASD_AN_basicPartOfAccountNumber'.
	 * 
	 * @return the value of field 'ASD_AN_basicPartOfAccountNumber'.
	 */
	public java.lang.String getASD_AN_basicPartOfAccountNumber()
	{
		return this._ASD_AN_basicPartOfAccountNumber;
	}

	/**
	 * Returns the value of field 'ASD_AS_accountSuffix'.
	 * 
	 * @return the value of field 'ASD_AS_accountSuffix'.
	 */
	public java.lang.String getASD_AS_accountSuffix()
	{
		return this._ASD_AS_accountSuffix;
	}

	/**
	 * Returns the value of field 'ASD_TCD_transactionCode'.
	 * 
	 * @return the value of field 'ASD_TCD_transactionCode'.
	 */
	public java.lang.String getASD_TCD_transactionCode()
	{
		return this._ASD_TCD_transactionCode;
	}

	/**
	 * Returns the value of field 'CAA_NA1_addressLine1'.
	 * 
	 * @return the value of field 'CAA_NA1_addressLine1'.
	 */
	public java.lang.String getCAA_NA1_addressLine1()
	{
		return this._CAA_NA1_addressLine1;
	}

	/**
	 * Returns the value of field 'CAA_NA2_addressLine2'.
	 * 
	 * @return the value of field 'CAA_NA2_addressLine2'.
	 */
	public java.lang.String getCAA_NA2_addressLine2()
	{
		return this._CAA_NA2_addressLine2;
	}

	/**
	 * Returns the value of field 'CAA_NA3_addressLine3'.
	 * 
	 * @return the value of field 'CAA_NA3_addressLine3'.
	 */
	public java.lang.String getCAA_NA3_addressLine3()
	{
		return this._CAA_NA3_addressLine3;
	}

	/**
	 * Returns the value of field 'CAA_NA4_addressLine4'.
	 * 
	 * @return the value of field 'CAA_NA4_addressLine4'.
	 */
	public java.lang.String getCAA_NA4_addressLine4()
	{
		return this._CAA_NA4_addressLine4;
	}

	/**
	 * Returns the value of field 'CAA_NA5_addressLine5'.
	 * 
	 * @return the value of field 'CAA_NA5_addressLine5'.
	 */
	public java.lang.String getCAA_NA5_addressLine5()
	{
		return this._CAA_NA5_addressLine5;
	}

	/**
	 * Returns the value of field 'MCO_C1R_customersC1Rating'.
	 * 
	 * @return the value of field 'MCO_C1R_customersC1Rating'.
	 */
	public java.lang.String getMCO_C1R_customersC1Rating()
	{
		return this._MCO_C1R_customersC1Rating;
	}

	/**
	 * Returns the value of field 'OCA_AB_accountBranch'.
	 * 
	 * @return the value of field 'OCA_AB_accountBranch'.
	 */
	public java.lang.String getOCA_AB_accountBranch()
	{
		return this._OCA_AB_accountBranch;
	}

	/**
	 * Returns the value of field 'OCA_ACT_accountType'.
	 * 
	 * @return the value of field 'OCA_ACT_accountType'.
	 */
	public java.lang.String getOCA_ACT_accountType()
	{
		return this._OCA_ACT_accountType;
	}

	/**
	 * Returns the value of field 'OCA_AN_basicPartOfAccountNumber'.
	 * 
	 * @return the value of field 'OCA_AN_basicPartOfAccountNumber'.
	 */
	public java.lang.String getOCA_AN_basicPartOfAccountNumber()
	{
		return this._OCA_AN_basicPartOfAccountNumber;
	}

	/**
	 * Returns the value of field 'OCA_AS_accountSuffix'.
	 * 
	 * @return the value of field 'OCA_AS_accountSuffix'.
	 */
	public java.lang.String getOCA_AS_accountSuffix()
	{
		return this._OCA_AS_accountSuffix;
	}

	/**
	 * Returns the value of field 'OCA_CCY_currencyMnemonic'.
	 * 
	 * @return the value of field 'OCA_CCY_currencyMnemonic'.
	 */
	public java.lang.String getOCA_CCY_currencyMnemonic()
	{
		return this._OCA_CCY_currencyMnemonic;
	}

	/**
	 * Returns the value of field 'OCA_OAD_dateAccountOpened'.
	 * 
	 * @return the value of field 'OCA_OAD_dateAccountOpened'.
	 */
	public java.lang.String getOCA_OAD_dateAccountOpened()
	{
		return this._OCA_OAD_dateAccountOpened;
	}

	/**
	 * Returns the value of field 'OCA_SHN_accountShortName'.
	 * 
	 * @return the value of field 'OCA_SHN_accountShortName'.
	 */
	public java.lang.String getOCA_SHN_accountShortName()
	{
		return this._OCA_SHN_accountShortName;
	}

	/**
	 * Returns the value of field 'RDS_ABF_fundingSettlementBranch'.
	 * 
	 * @return the value of field 'RDS_ABF_fundingSettlementBranch'.
	 */
	public java.lang.String getRDS_ABF_fundingSettlementBranch()
	{
		return this._RDS_ABF_fundingSettlementBranch;
	}

	/**
	 * Returns the value of field 'RDS_ABI_interestSettlementBranch'.
	 * 
	 * @return the value of field 'RDS_ABI_interestSettlementBranch'
	 */
	public java.lang.String getRDS_ABI_interestSettlementBranch()
	{
		return this._RDS_ABI_interestSettlementBranch;
	}

	/**
	 * Returns the value of field 'RDS_ABM_maturitySettlementBranch'.
	 * 
	 * @return the value of field 'RDS_ABM_maturitySettlementBranch'
	 */
	public java.lang.String getRDS_ABM_maturitySettlementBranch()
	{
		return this._RDS_ABM_maturitySettlementBranch;
	}

	/**
	 * Returns the value of field 'RDS_ANF_fundingSettlementACNo'.
	 * 
	 * @return the value of field 'RDS_ANF_fundingSettlementACNo'.
	 */
	public java.lang.String getRDS_ANF_fundingSettlementACNo()
	{
		return this._RDS_ANF_fundingSettlementACNo;
	}

	/**
	 * Returns the value of field 'RDS_ANI_interestSettlementACNo'.
	 * 
	 * @return the value of field 'RDS_ANI_interestSettlementACNo'.
	 */
	public java.lang.String getRDS_ANI_interestSettlementACNo()
	{
		return this._RDS_ANI_interestSettlementACNo;
	}

	/**
	 * Returns the value of field 'RDS_ANM_maturitySettlementACNo'.
	 * 
	 * @return the value of field 'RDS_ANM_maturitySettlementACNo'.
	 */
	public java.lang.String getRDS_ANM_maturitySettlementACNo()
	{
		return this._RDS_ANM_maturitySettlementACNo;
	}

	/**
	 * Returns the value of field 'RDS_ASF_fundingSettlementACSfx'.
	 * 
	 * @return the value of field 'RDS_ASF_fundingSettlementACSfx'.
	 */
	public java.lang.String getRDS_ASF_fundingSettlementACSfx()
	{
		return this._RDS_ASF_fundingSettlementACSfx;
	}

	/**
	 * Returns the value of field 'RDS_ASI_interestSettlementACSfx'.
	 * 
	 * @return the value of field 'RDS_ASI_interestSettlementACSfx'.
	 */
	public java.lang.String getRDS_ASI_interestSettlementACSfx()
	{
		return this._RDS_ASI_interestSettlementACSfx;
	}

	/**
	 * Returns the value of field 'RDS_ASM_maturitySettlementACSfx'.
	 * 
	 * @return the value of field 'RDS_ASM_maturitySettlementACSfx'.
	 */
	public java.lang.String getRDS_ASM_maturitySettlementACSfx()
	{
		return this._RDS_ASM_maturitySettlementACSfx;
	}

	/**
	 * Returns the value of field 'RDS_BRNM_branchMnemonic'.
	 * 
	 * @return the value of field 'RDS_BRNM_branchMnemonic'.
	 */
	public java.lang.String getRDS_BRNM_branchMnemonic()
	{
		return this._RDS_BRNM_branchMnemonic;
	}

	/**
	 * Returns the value of field 'RDS_CCY_currencyMnemonic'.
	 * 
	 * @return the value of field 'RDS_CCY_currencyMnemonic'.
	 */
	public java.lang.String getRDS_CCY_currencyMnemonic()
	{
		return this._RDS_CCY_currencyMnemonic;
	}

	/**
	 * Returns the value of field 'RDS_CLC_customerLocation'.
	 * 
	 * @return the value of field 'RDS_CLC_customerLocation'.
	 */
	public java.lang.String getRDS_CLC_customerLocation()
	{
		return this._RDS_CLC_customerLocation;
	}

	/**
	 * Returns the value of field 'RDS_CTRD_contractDate'.
	 * 
	 * @return the value of field 'RDS_CTRD_contractDate'.
	 */
	public java.lang.String getRDS_CTRD_contractDate()
	{
		return this._RDS_CTRD_contractDate;
	}

	/**
	 * Returns the value of field 'RDS_CUS_customerMnemonic'.
	 * 
	 * @return the value of field 'RDS_CUS_customerMnemonic'.
	 */
	public java.lang.String getRDS_CUS_customerMnemonic()
	{
		return this._RDS_CUS_customerMnemonic;
	}

	/**
	 * Returns the value of field 'RDS_DLA_dealAmount'.
	 * 
	 * @return the value of field 'RDS_DLA_dealAmount'.
	 */
	public java.lang.String getRDS_DLA_dealAmount()
	{
		return this._RDS_DLA_dealAmount;
	}

	/**
	 * Returns the value of field 'RDS_DLP_dealType'.
	 * 
	 * @return the value of field 'RDS_DLP_dealType'.
	 */
	public java.lang.String getRDS_DLP_dealType()
	{
		return this._RDS_DLP_dealType;
	}

	/**
	 * Returns the value of field 'RDS_DLR_dealReference'.
	 * 
	 * @return the value of field 'RDS_DLR_dealReference'.
	 */
	public java.lang.String getRDS_DLR_dealReference()
	{
		return this._RDS_DLR_dealReference;
	}

	/**
	 * Returns the value of field 'RDS_MDT_maturityDate'.
	 * 
	 * @return the value of field 'RDS_MDT_maturityDate'.
	 */
	public java.lang.String getRDS_MDT_maturityDate()
	{
		return this._RDS_MDT_maturityDate;
	}

	/**
	 * Returns the value of field 'RDS_PRC_periodCode'.
	 * 
	 * @return the value of field 'RDS_PRC_periodCode'.
	 */
	public java.lang.String getRDS_PRC_periodCode()
	{
		return this._RDS_PRC_periodCode;
	}

	/**
	 * Returns the value of field 'RDS_SDT_startDate'.
	 * 
	 * @return the value of field 'RDS_SDT_startDate'.
	 */
	public java.lang.String getRDS_SDT_startDate()
	{
		return this._RDS_SDT_startDate;
	}

	/**
	 * Returns the value of field 'RDS_XMF_fundingTransferMethod'.
	 * 
	 * @return the value of field 'RDS_XMF_fundingTransferMethod'.
	 */
	public java.lang.String getRDS_XMF_fundingTransferMethod()
	{
		return this._RDS_XMF_fundingTransferMethod;
	}

	/**
	 * Returns the value of field 'RDS_XMI_interestTransferMethod'.
	 * 
	 * @return the value of field 'RDS_XMI_interestTransferMethod'.
	 */
	public java.lang.String getRDS_XMI_interestTransferMethod()
	{
		return this._RDS_XMI_interestTransferMethod;
	}

	/**
	 * Returns the value of field 'RDS_XMM_maturityTransferMethod'.
	 * 
	 * @return the value of field 'RDS_XMM_maturityTransferMethod'.
	 */
	public java.lang.String getRDS_XMM_maturityTransferMethod()
	{
		return this._RDS_XMM_maturityTransferMethod;
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
	 * Sets the value of field 'ANC_BRNM_branchMnemonic'.
	 * 
	 * @param ANC_BRNM_branchMnemonic
	 *            the value of field 'ANC_BRNM_branchMnemonic'.
	 */
	public void setANC_BRNM_branchMnemonic(final java.lang.String ANC_BRNM_branchMnemonic)
	{
		this._ANC_BRNM_branchMnemonic = ANC_BRNM_branchMnemonic;
	}

	/**
	 * Sets the value of field 'ANC_CLC_customerLocation'.
	 * 
	 * @param ANC_CLC_customerLocation
	 *            the value of field 'ANC_CLC_customerLocation'.
	 */
	public void setANC_CLC_customerLocation(final java.lang.String ANC_CLC_customerLocation)
	{
		this._ANC_CLC_customerLocation = ANC_CLC_customerLocation;
	}

	/**
	 * Sets the value of field 'ANC_CPNC_customersBasicNumber'.
	 * 
	 * @param ANC_CPNC_customersBasicNumber
	 *            the value of field 'ANC_CPNC_customersBasicNumber'.
	 */
	public void setANC_CPNC_customersBasicNumber(final java.lang.String ANC_CPNC_customersBasicNumber)
	{
		this._ANC_CPNC_customersBasicNumber = ANC_CPNC_customersBasicNumber;
	}

	/**
	 * Sets the value of field 'ANC_CRB1_taxCode1'.
	 * 
	 * @param ANC_CRB1_taxCode1
	 *            the value of field 'ANC_CRB1_taxCode1'.
	 */
	public void setANC_CRB1_taxCode1(final java.lang.String ANC_CRB1_taxCode1)
	{
		this._ANC_CRB1_taxCode1 = ANC_CRB1_taxCode1;
	}

	/**
	 * Sets the value of field 'ANC_CRB2_taxCode2'.
	 * 
	 * @param ANC_CRB2_taxCode2
	 *            the value of field 'ANC_CRB2_taxCode2'.
	 */
	public void setANC_CRB2_taxCode2(final java.lang.String ANC_CRB2_taxCode2)
	{
		this._ANC_CRB2_taxCode2 = ANC_CRB2_taxCode2;
	}

	/**
	 * Sets the value of field 'ANC_CTP_customerType'.
	 * 
	 * @param ANC_CTP_customerType
	 *            the value of field 'ANC_CTP_customerType'.
	 */
	public void setANC_CTP_customerType(final java.lang.String ANC_CTP_customerType)
	{
		this._ANC_CTP_customerType = ANC_CTP_customerType;
	}

	/**
	 * Sets the value of field 'ANC_CUN_customerFullName'.
	 * 
	 * @param ANC_CUN_customerFullName
	 *            the value of field 'ANC_CUN_customerFullName'.
	 */
	public void setANC_CUN_customerFullName(final java.lang.String ANC_CUN_customerFullName)
	{
		this._ANC_CUN_customerFullName = ANC_CUN_customerFullName;
	}

	/**
	 * Sets the value of field 'ANC_CUS_customerMnemonic'.
	 * 
	 * @param ANC_CUS_customerMnemonic
	 *            the value of field 'ANC_CUS_customerMnemonic'.
	 */
	public void setANC_CUS_customerMnemonic(final java.lang.String ANC_CUS_customerMnemonic)
	{
		this._ANC_CUS_customerMnemonic = ANC_CUS_customerMnemonic;
	}

	/**
	 * Sets the value of field 'ANC_DAS_defaultAccountShortName'.
	 * 
	 * @param ANC_DAS_defaultAccountShortName
	 *            the value of field 'ANC_DAS_defaultAccountShortName'.
	 */
	public void setANC_DAS_defaultAccountShortName(final java.lang.String ANC_DAS_defaultAccountShortName)
	{
		this._ANC_DAS_defaultAccountShortName = ANC_DAS_defaultAccountShortName;
	}

	/**
	 * Sets the value of field 'ANX_EAD1_eMailAddress1'.
	 * 
	 * @param ANX_EAD1_eMailAddress1
	 *            the value of field 'ANX_EAD1_eMailAddress1'.
	 */
	public void setANX_EAD1_eMailAddress1(final java.lang.String ANX_EAD1_eMailAddress1)
	{
		this._ANX_EAD1_eMailAddress1 = ANX_EAD1_eMailAddress1;
	}

	/**
	 * Sets the value of field 'ASC_AB_accountBranch'.
	 * 
	 * @param ASC_AB_accountBranch
	 *            the value of field 'ASC_AB_accountBranch'.
	 */
	public void setASC_AB_accountBranch(final java.lang.String ASC_AB_accountBranch)
	{
		this._ASC_AB_accountBranch = ASC_AB_accountBranch;
	}

	/**
	 * Sets the value of field 'ASC_AMA_amount'.
	 * 
	 * @param ASC_AMA_amount
	 *            the value of field 'ASC_AMA_amount'.
	 */
	public void setASC_AMA_amount(final java.lang.String ASC_AMA_amount)
	{
		this._ASC_AMA_amount = ASC_AMA_amount;
	}

	/**
	 * Sets the value of field 'ASC_AN_basicPartOfAccountNumber'.
	 * 
	 * @param ASC_AN_basicPartOfAccountNumber
	 *            the value of field 'ASC_AN_basicPartOfAccountNumber'.
	 */
	public void setASC_AN_basicPartOfAccountNumber(final java.lang.String ASC_AN_basicPartOfAccountNumber)
	{
		this._ASC_AN_basicPartOfAccountNumber = ASC_AN_basicPartOfAccountNumber;
	}

	/**
	 * Sets the value of field 'ASC_AS_accountSuffix'.
	 * 
	 * @param ASC_AS_accountSuffix
	 *            the value of field 'ASC_AS_accountSuffix'.
	 */
	public void setASC_AS_accountSuffix(final java.lang.String ASC_AS_accountSuffix)
	{
		this._ASC_AS_accountSuffix = ASC_AS_accountSuffix;
	}

	/**
	 * Sets the value of field 'ASC_DRF_usersOwnReferenceForDealsReconciliationEtc'.
	 * 
	 * @param ASC_DRF_usersOwnReferenceForDealsReconciliationEtc
	 *            the value of field 'ASC_DRF_usersOwnReferenceForDealsReconciliationEtc'.
	 */
	public void setASC_DRF_usersOwnReferenceForDealsReconciliationEtc(
					final java.lang.String ASC_DRF_usersOwnReferenceForDealsReconciliationEtc)
	{
		this._ASC_DRF_usersOwnReferenceForDealsReconciliationEtc = ASC_DRF_usersOwnReferenceForDealsReconciliationEtc;
	}

	/**
	 * Sets the value of field 'ASC_TCD_transactionCode'.
	 * 
	 * @param ASC_TCD_transactionCode
	 *            the value of field 'ASC_TCD_transactionCode'.
	 */
	public void setASC_TCD_transactionCode(final java.lang.String ASC_TCD_transactionCode)
	{
		this._ASC_TCD_transactionCode = ASC_TCD_transactionCode;
	}

	/**
	 * Sets the value of field 'ASC_VFR_valueFromDate'.
	 * 
	 * @param ASC_VFR_valueFromDate
	 *            the value of field 'ASC_VFR_valueFromDate'.
	 */
	public void setASC_VFR_valueFromDate(final java.lang.String ASC_VFR_valueFromDate)
	{
		this._ASC_VFR_valueFromDate = ASC_VFR_valueFromDate;
	}

	/**
	 * Sets the value of field 'ASD_AB_accountBranch'.
	 * 
	 * @param ASD_AB_accountBranch
	 *            the value of field 'ASD_AB_accountBranch'.
	 */
	public void setASD_AB_accountBranch(final java.lang.String ASD_AB_accountBranch)
	{
		this._ASD_AB_accountBranch = ASD_AB_accountBranch;
	}

	/**
	 * Sets the value of field 'ASD_AN_basicPartOfAccountNumber'.
	 * 
	 * @param ASD_AN_basicPartOfAccountNumber
	 *            the value of field 'ASD_AN_basicPartOfAccountNumber'.
	 */
	public void setASD_AN_basicPartOfAccountNumber(final java.lang.String ASD_AN_basicPartOfAccountNumber)
	{
		this._ASD_AN_basicPartOfAccountNumber = ASD_AN_basicPartOfAccountNumber;
	}

	/**
	 * Sets the value of field 'ASD_AS_accountSuffix'.
	 * 
	 * @param ASD_AS_accountSuffix
	 *            the value of field 'ASD_AS_accountSuffix'.
	 */
	public void setASD_AS_accountSuffix(final java.lang.String ASD_AS_accountSuffix)
	{
		this._ASD_AS_accountSuffix = ASD_AS_accountSuffix;
	}

	/**
	 * Sets the value of field 'ASD_TCD_transactionCode'.
	 * 
	 * @param ASD_TCD_transactionCode
	 *            the value of field 'ASD_TCD_transactionCode'.
	 */
	public void setASD_TCD_transactionCode(final java.lang.String ASD_TCD_transactionCode)
	{
		this._ASD_TCD_transactionCode = ASD_TCD_transactionCode;
	}

	/**
	 * Sets the value of field 'CAA_NA1_addressLine1'.
	 * 
	 * @param CAA_NA1_addressLine1
	 *            the value of field 'CAA_NA1_addressLine1'.
	 */
	public void setCAA_NA1_addressLine1(final java.lang.String CAA_NA1_addressLine1)
	{
		this._CAA_NA1_addressLine1 = CAA_NA1_addressLine1;
	}

	/**
	 * Sets the value of field 'CAA_NA2_addressLine2'.
	 * 
	 * @param CAA_NA2_addressLine2
	 *            the value of field 'CAA_NA2_addressLine2'.
	 */
	public void setCAA_NA2_addressLine2(final java.lang.String CAA_NA2_addressLine2)
	{
		this._CAA_NA2_addressLine2 = CAA_NA2_addressLine2;
	}

	/**
	 * Sets the value of field 'CAA_NA3_addressLine3'.
	 * 
	 * @param CAA_NA3_addressLine3
	 *            the value of field 'CAA_NA3_addressLine3'.
	 */
	public void setCAA_NA3_addressLine3(final java.lang.String CAA_NA3_addressLine3)
	{
		this._CAA_NA3_addressLine3 = CAA_NA3_addressLine3;
	}

	/**
	 * Sets the value of field 'CAA_NA4_addressLine4'.
	 * 
	 * @param CAA_NA4_addressLine4
	 *            the value of field 'CAA_NA4_addressLine4'.
	 */
	public void setCAA_NA4_addressLine4(final java.lang.String CAA_NA4_addressLine4)
	{
		this._CAA_NA4_addressLine4 = CAA_NA4_addressLine4;
	}

	/**
	 * Sets the value of field 'CAA_NA5_addressLine5'.
	 * 
	 * @param CAA_NA5_addressLine5
	 *            the value of field 'CAA_NA5_addressLine5'.
	 */
	public void setCAA_NA5_addressLine5(final java.lang.String CAA_NA5_addressLine5)
	{
		this._CAA_NA5_addressLine5 = CAA_NA5_addressLine5;
	}

	/**
	 * Sets the value of field 'MCO_C1R_customersC1Rating'.
	 * 
	 * @param MCO_C1R_customersC1Rating
	 *            the value of field 'MCO_C1R_customersC1Rating'.
	 */
	public void setMCO_C1R_customersC1Rating(final java.lang.String MCO_C1R_customersC1Rating)
	{
		this._MCO_C1R_customersC1Rating = MCO_C1R_customersC1Rating;
	}

	/**
	 * Sets the value of field 'OCA_AB_accountBranch'.
	 * 
	 * @param OCA_AB_accountBranch
	 *            the value of field 'OCA_AB_accountBranch'.
	 */
	public void setOCA_AB_accountBranch(final java.lang.String OCA_AB_accountBranch)
	{
		this._OCA_AB_accountBranch = OCA_AB_accountBranch;
	}

	/**
	 * Sets the value of field 'OCA_ACT_accountType'.
	 * 
	 * @param OCA_ACT_accountType
	 *            the value of field 'OCA_ACT_accountType'.
	 */
	public void setOCA_ACT_accountType(final java.lang.String OCA_ACT_accountType)
	{
		this._OCA_ACT_accountType = OCA_ACT_accountType;
	}

	/**
	 * Sets the value of field 'OCA_AN_basicPartOfAccountNumber'.
	 * 
	 * @param OCA_AN_basicPartOfAccountNumber
	 *            the value of field 'OCA_AN_basicPartOfAccountNumber'.
	 */
	public void setOCA_AN_basicPartOfAccountNumber(final java.lang.String OCA_AN_basicPartOfAccountNumber)
	{
		this._OCA_AN_basicPartOfAccountNumber = OCA_AN_basicPartOfAccountNumber;
	}

	/**
	 * Sets the value of field 'OCA_AS_accountSuffix'.
	 * 
	 * @param OCA_AS_accountSuffix
	 *            the value of field 'OCA_AS_accountSuffix'.
	 */
	public void setOCA_AS_accountSuffix(final java.lang.String OCA_AS_accountSuffix)
	{
		this._OCA_AS_accountSuffix = OCA_AS_accountSuffix;
	}

	/**
	 * Sets the value of field 'OCA_CCY_currencyMnemonic'.
	 * 
	 * @param OCA_CCY_currencyMnemonic
	 *            the value of field 'OCA_CCY_currencyMnemonic'.
	 */
	public void setOCA_CCY_currencyMnemonic(final java.lang.String OCA_CCY_currencyMnemonic)
	{
		this._OCA_CCY_currencyMnemonic = OCA_CCY_currencyMnemonic;
	}

	/**
	 * Sets the value of field 'OCA_OAD_dateAccountOpened'.
	 * 
	 * @param OCA_OAD_dateAccountOpened
	 *            the value of field 'OCA_OAD_dateAccountOpened'.
	 */
	public void setOCA_OAD_dateAccountOpened(final java.lang.String OCA_OAD_dateAccountOpened)
	{
		this._OCA_OAD_dateAccountOpened = OCA_OAD_dateAccountOpened;
	}

	/**
	 * Sets the value of field 'OCA_SHN_accountShortName'.
	 * 
	 * @param OCA_SHN_accountShortName
	 *            the value of field 'OCA_SHN_accountShortName'.
	 */
	public void setOCA_SHN_accountShortName(final java.lang.String OCA_SHN_accountShortName)
	{
		this._OCA_SHN_accountShortName = OCA_SHN_accountShortName;
	}

	/**
	 * Sets the value of field 'RDS_ABF_fundingSettlementBranch'.
	 * 
	 * @param RDS_ABF_fundingSettlementBranch
	 *            the value of field 'RDS_ABF_fundingSettlementBranch'.
	 */
	public void setRDS_ABF_fundingSettlementBranch(final java.lang.String RDS_ABF_fundingSettlementBranch)
	{
		this._RDS_ABF_fundingSettlementBranch = RDS_ABF_fundingSettlementBranch;
	}

	/**
	 * Sets the value of field 'RDS_ABI_interestSettlementBranch'.
	 * 
	 * @param RDS_ABI_interestSettlementBranch
	 *            the value of field 'RDS_ABI_interestSettlementBranch'.
	 */
	public void setRDS_ABI_interestSettlementBranch(final java.lang.String RDS_ABI_interestSettlementBranch)
	{
		this._RDS_ABI_interestSettlementBranch = RDS_ABI_interestSettlementBranch;
	}

	/**
	 * Sets the value of field 'RDS_ABM_maturitySettlementBranch'.
	 * 
	 * @param RDS_ABM_maturitySettlementBranch
	 *            the value of field 'RDS_ABM_maturitySettlementBranch'.
	 */
	public void setRDS_ABM_maturitySettlementBranch(final java.lang.String RDS_ABM_maturitySettlementBranch)
	{
		this._RDS_ABM_maturitySettlementBranch = RDS_ABM_maturitySettlementBranch;
	}

	/**
	 * Sets the value of field 'RDS_ANF_fundingSettlementACNo'.
	 * 
	 * @param RDS_ANF_fundingSettlementACNo
	 *            the value of field 'RDS_ANF_fundingSettlementACNo'.
	 */
	public void setRDS_ANF_fundingSettlementACNo(final java.lang.String RDS_ANF_fundingSettlementACNo)
	{
		this._RDS_ANF_fundingSettlementACNo = RDS_ANF_fundingSettlementACNo;
	}

	/**
	 * Sets the value of field 'RDS_ANI_interestSettlementACNo'.
	 * 
	 * @param RDS_ANI_interestSettlementACNo
	 *            the value of field 'RDS_ANI_interestSettlementACNo'.
	 */
	public void setRDS_ANI_interestSettlementACNo(final java.lang.String RDS_ANI_interestSettlementACNo)
	{
		this._RDS_ANI_interestSettlementACNo = RDS_ANI_interestSettlementACNo;
	}

	/**
	 * Sets the value of field 'RDS_ANM_maturitySettlementACNo'.
	 * 
	 * @param RDS_ANM_maturitySettlementACNo
	 *            the value of field 'RDS_ANM_maturitySettlementACNo'.
	 */
	public void setRDS_ANM_maturitySettlementACNo(final java.lang.String RDS_ANM_maturitySettlementACNo)
	{
		this._RDS_ANM_maturitySettlementACNo = RDS_ANM_maturitySettlementACNo;
	}

	/**
	 * Sets the value of field 'RDS_ASF_fundingSettlementACSfx'.
	 * 
	 * @param RDS_ASF_fundingSettlementACSfx
	 *            the value of field 'RDS_ASF_fundingSettlementACSfx'.
	 */
	public void setRDS_ASF_fundingSettlementACSfx(final java.lang.String RDS_ASF_fundingSettlementACSfx)
	{
		this._RDS_ASF_fundingSettlementACSfx = RDS_ASF_fundingSettlementACSfx;
	}

	/**
	 * Sets the value of field 'RDS_ASI_interestSettlementACSfx'.
	 * 
	 * @param RDS_ASI_interestSettlementACSfx
	 *            the value of field 'RDS_ASI_interestSettlementACSfx'.
	 */
	public void setRDS_ASI_interestSettlementACSfx(final java.lang.String RDS_ASI_interestSettlementACSfx)
	{
		this._RDS_ASI_interestSettlementACSfx = RDS_ASI_interestSettlementACSfx;
	}

	/**
	 * Sets the value of field 'RDS_ASM_maturitySettlementACSfx'.
	 * 
	 * @param RDS_ASM_maturitySettlementACSfx
	 *            the value of field 'RDS_ASM_maturitySettlementACSfx'.
	 */
	public void setRDS_ASM_maturitySettlementACSfx(final java.lang.String RDS_ASM_maturitySettlementACSfx)
	{
		this._RDS_ASM_maturitySettlementACSfx = RDS_ASM_maturitySettlementACSfx;
	}

	/**
	 * Sets the value of field 'RDS_BRNM_branchMnemonic'.
	 * 
	 * @param RDS_BRNM_branchMnemonic
	 *            the value of field 'RDS_BRNM_branchMnemonic'.
	 */
	public void setRDS_BRNM_branchMnemonic(final java.lang.String RDS_BRNM_branchMnemonic)
	{
		this._RDS_BRNM_branchMnemonic = RDS_BRNM_branchMnemonic;
	}

	/**
	 * Sets the value of field 'RDS_CCY_currencyMnemonic'.
	 * 
	 * @param RDS_CCY_currencyMnemonic
	 *            the value of field 'RDS_CCY_currencyMnemonic'.
	 */
	public void setRDS_CCY_currencyMnemonic(final java.lang.String RDS_CCY_currencyMnemonic)
	{
		this._RDS_CCY_currencyMnemonic = RDS_CCY_currencyMnemonic;
	}

	/**
	 * Sets the value of field 'RDS_CLC_customerLocation'.
	 * 
	 * @param RDS_CLC_customerLocation
	 *            the value of field 'RDS_CLC_customerLocation'.
	 */
	public void setRDS_CLC_customerLocation(final java.lang.String RDS_CLC_customerLocation)
	{
		this._RDS_CLC_customerLocation = RDS_CLC_customerLocation;
	}

	/**
	 * Sets the value of field 'RDS_CTRD_contractDate'.
	 * 
	 * @param RDS_CTRD_contractDate
	 *            the value of field 'RDS_CTRD_contractDate'.
	 */
	public void setRDS_CTRD_contractDate(final java.lang.String RDS_CTRD_contractDate)
	{
		this._RDS_CTRD_contractDate = RDS_CTRD_contractDate;
	}

	/**
	 * Sets the value of field 'RDS_CUS_customerMnemonic'.
	 * 
	 * @param RDS_CUS_customerMnemonic
	 *            the value of field 'RDS_CUS_customerMnemonic'.
	 */
	public void setRDS_CUS_customerMnemonic(final java.lang.String RDS_CUS_customerMnemonic)
	{
		this._RDS_CUS_customerMnemonic = RDS_CUS_customerMnemonic;
	}

	/**
	 * Sets the value of field 'RDS_DLA_dealAmount'.
	 * 
	 * @param RDS_DLA_dealAmount
	 *            the value of field 'RDS_DLA_dealAmount'.
	 */
	public void setRDS_DLA_dealAmount(final java.lang.String RDS_DLA_dealAmount)
	{
		this._RDS_DLA_dealAmount = RDS_DLA_dealAmount;
	}

	/**
	 * Sets the value of field 'RDS_DLP_dealType'.
	 * 
	 * @param RDS_DLP_dealType
	 *            the value of field 'RDS_DLP_dealType'
	 */
	public void setRDS_DLP_dealType(final java.lang.String RDS_DLP_dealType)
	{
		this._RDS_DLP_dealType = RDS_DLP_dealType;
	}

	/**
	 * Sets the value of field 'RDS_DLR_dealReference'.
	 * 
	 * @param RDS_DLR_dealReference
	 *            the value of field 'RDS_DLR_dealReference'.
	 */
	public void setRDS_DLR_dealReference(final java.lang.String RDS_DLR_dealReference)
	{
		this._RDS_DLR_dealReference = RDS_DLR_dealReference;
	}

	/**
	 * Sets the value of field 'RDS_MDT_maturityDate'.
	 * 
	 * @param RDS_MDT_maturityDate
	 *            the value of field 'RDS_MDT_maturityDate'.
	 */
	public void setRDS_MDT_maturityDate(final java.lang.String RDS_MDT_maturityDate)
	{
		this._RDS_MDT_maturityDate = RDS_MDT_maturityDate;
	}

	/**
	 * Sets the value of field 'RDS_PRC_periodCode'.
	 * 
	 * @param RDS_PRC_periodCode
	 *            the value of field 'RDS_PRC_periodCode'.
	 */
	public void setRDS_PRC_periodCode(final java.lang.String RDS_PRC_periodCode)
	{
		this._RDS_PRC_periodCode = RDS_PRC_periodCode;
	}

	/**
	 * Sets the value of field 'RDS_SDT_startDate'.
	 * 
	 * @param RDS_SDT_startDate
	 *            the value of field 'RDS_SDT_startDate'.
	 */
	public void setRDS_SDT_startDate(final java.lang.String RDS_SDT_startDate)
	{
		this._RDS_SDT_startDate = RDS_SDT_startDate;
	}

	/**
	 * Sets the value of field 'RDS_XMF_fundingTransferMethod'.
	 * 
	 * @param RDS_XMF_fundingTransferMethod
	 *            the value of field 'RDS_XMF_fundingTransferMethod'.
	 */
	public void setRDS_XMF_fundingTransferMethod(final java.lang.String RDS_XMF_fundingTransferMethod)
	{
		this._RDS_XMF_fundingTransferMethod = RDS_XMF_fundingTransferMethod;
	}

	/**
	 * Sets the value of field 'RDS_XMI_interestTransferMethod'.
	 * 
	 * @param RDS_XMI_interestTransferMethod
	 *            the value of field 'RDS_XMI_interestTransferMethod'.
	 */
	public void setRDS_XMI_interestTransferMethod(final java.lang.String RDS_XMI_interestTransferMethod)
	{
		this._RDS_XMI_interestTransferMethod = RDS_XMI_interestTransferMethod;
	}

	/**
	 * Sets the value of field 'RDS_XMM_maturityTransferMethod'.
	 * 
	 * @param RDS_XMM_maturityTransferMethod
	 *            the value of field 'RDS_XMM_maturityTransferMethod'.
	 */
	public void setRDS_XMM_maturityTransferMethod(final java.lang.String RDS_XMM_maturityTransferMethod)
	{
		this._RDS_XMM_maturityTransferMethod = RDS_XMM_maturityTransferMethod;
	}

	/**
	 * Method unmarshalZT1.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eq4.service.zt1.ZT1
	 */
	public static bf.com.misys.eq4.service.zt1.ZT1 unmarshalZT1(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eq4.service.zt1.ZT1) Unmarshaller.unmarshal(bf.com.misys.eq4.service.zt1.ZT1.class, reader);
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
