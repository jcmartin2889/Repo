package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.ISC1RecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.SC1RecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestSC1RecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1281524639080l;

	public TestSC1RecordStub()
	{

	}

	/**
	 * This is method is going to set part of the kFilLibName.<br>
	 * This method can be overwritten if another library needs to be used.<br>
	 * <code>kFilLibName=  kFilLibName + unitName</code>
	 */
	public void setKLibName()
	{
		setKLibName("KFILGP4");
	}

	/**
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	protected void setDao()
	{
		dao = daoFactory.getSC1Dao(session, dataModel);
	}

	/**
	 * This method will test if the record has been already inserted in the dataBase.
	 */
	public void isRecord(boolean assertValue)
	{
		boolean result;

		result = checkIfThisRecordIsInTheDB();
		if (assertValue)
		{
			assertTrue(result);
		}
		else
		{
			assertFalse(result);
		}
	}
	/**
	 * This method is going to check the getRecord dao's service.<br>
	 * The obtained result should be the same than the preset in the dao data-model.
	 */
	public SC1RecordDataModel getRecord()
	{
		SC1RecordDataModel record = null;
		record = ((ISC1RecordDao) dao).getSC1Record();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	public void updateRecord()
	{
		SC1RecordDataModel record = null;
		this.getMyModel().setAccountBranch("Stri");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getAccountBranch(), record.getAccountBranch());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		SC1RecordDataModel record = (SC1RecordDataModel) dataModel;
		assertEquals(getMyModel().getAccountBranch(), record.getAccountBranch());
		assertEquals(getMyModel().getBasicNumber(), record.getBasicNumber());
		assertEquals(getMyModel().getAccountSuffix(), record.getAccountSuffix());
		assertEquals(getMyModel().getApplication(), record.getApplication());
		assertEquals(getMyModel().getAccountStructure(), record.getAccountStructure());
		assertEquals(getMyModel().getContingent(), record.getContingent());
		assertEquals(getMyModel().getInternalAccount(), record.getInternalAccount());
		assertEquals(getMyModel().getAdjustedBalanceLastNight(), record.getAdjustedBalanceLastNight());
		assertEquals(getMyModel().getAdjustedBalanceLastPeriod(), record.getAdjustedBalanceLastPeriod());
		assertEquals(getMyModel().getClosingBalance(), record.getClosingBalance());
		assertEquals(getMyModel().getNetClearingToday(), record.getNetClearingToday());
		assertEquals(getMyModel().getNetClearingTomorrow(), record.getNetClearingTomorrow());
		assertEquals(getMyModel().getNetClearingAfterTomorrow(), record.getNetClearingAfterTomorrow());
		assertEquals(getMyModel().getShadowDebits(), record.getShadowDebits());
		assertEquals(getMyModel().getShadowCredits(), record.getShadowCredits());
		assertEquals(getMyModel().getReservedBalance(), record.getReservedBalance());
		assertEquals(getMyModel().getDaylightReservedBalance(), record.getDaylightReservedBalance());
		assertEquals(getMyModel().getShadowAdjustments(), record.getShadowAdjustments());
		assertEquals(getMyModel().getAccountShortName(), record.getAccountShortName());
		assertEquals(getMyModel().getCustomerType(), record.getCustomerType());
		assertEquals(getMyModel().getAcType(), record.getAcType());
		assertEquals(getMyModel().getAnalysisCode(), record.getAnalysisCode());
		assertEquals(getMyModel().getSundryAnalysisCode(), record.getSundryAnalysisCode());
		assertEquals(getMyModel().getNumericAnalysisCode(), record.getNumericAnalysisCode());
		assertEquals(getMyModel().getResidenceCountry(), record.getResidenceCountry());
		assertEquals(getMyModel().getCcy(), record.getCcy());
		assertEquals(getMyModel().getRiskCountry(), record.getRiskCountry());
		assertEquals(getMyModel().getParentCountry(), record.getParentCountry());
		assertEquals(getMyModel().getPostageCharged(), record.getPostageCharged());
		assertEquals(getMyModel().getSecurityHeld(), record.getSecurityHeld());
		assertEquals(getMyModel().getAdviseTrans(), record.getAdviseTrans());
		assertEquals(getMyModel().getStoppedCheques(), record.getStoppedCheques());
		assertEquals(getMyModel().getStandingOrder(), record.getStandingOrder());
		assertEquals(getMyModel().getDebitStats(), record.getDebitStats());
		assertEquals(getMyModel().getCreditStats(), record.getCreditStats());
		assertEquals(getMyModel().getHighBalStats(), record.getHighBalStats());
		assertEquals(getMyModel().getLowBalStats(), record.getLowBalStats());
		assertEquals(getMyModel().getDrIntStats(), record.getDrIntStats());
		assertEquals(getMyModel().getCrIntStats(), record.getCrIntStats());
		assertEquals(getMyModel().getPLStats(), record.getPLStats());
		assertEquals(getMyModel().getDrToStats(), record.getDrToStats());
		assertEquals(getMyModel().getCrToStats(), record.getCrToStats());
		assertEquals(getMyModel().getEligibleForExtSystemTransfer(), record.getEligibleForExtSystemTransfer());
		assertEquals(getMyModel().getBulk(), record.getBulk());
		assertEquals(getMyModel().getSeparateInterestCharges(), record.getSeparateInterestCharges());
		assertEquals(getMyModel().getPassBook(), record.getPassBook());
		assertEquals(getMyModel().getTaxCharges(), record.getTaxCharges());
		assertEquals(getMyModel().getTaxInterest(), record.getTaxInterest());
		assertEquals(getMyModel().getCharges(), record.getCharges());
		assertEquals(getMyModel().getBoHeld(), record.getBoHeld());
		assertEquals(getMyModel().getFundingReceiving(), record.getFundingReceiving());
		assertEquals(getMyModel().getDiaryNotesHeld(), record.getDiaryNotesHeld());
		assertEquals(getMyModel().getSpecialInstructions(), record.getSpecialInstructions());
		assertEquals(getMyModel().getReferCredits(), record.getReferCredits());
		assertEquals(getMyModel().getReferDebits(), record.getReferDebits());
		assertEquals(getMyModel().getReferCreditBalance(), record.getReferCreditBalance());
		assertEquals(getMyModel().getDoNotReferDrBalance(), record.getDoNotReferDrBalance());
		assertEquals(getMyModel().getDeceasedOrLiquidated(), record.getDeceasedOrLiquidated());
		assertEquals(getMyModel().getBlocked(), record.getBlocked());
		assertEquals(getMyModel().getInactive(), record.getInactive());
		assertEquals(getMyModel().getZeroBalanceRequired(), record.getZeroBalanceRequired());
		assertEquals(getMyModel().getClearedBalance(), record.getClearedBalance());
		assertEquals(getMyModel().getClearOnlyCredits(), record.getClearOnlyCredits());
		assertEquals(getMyModel().getStatementFrequency(), record.getStatementFrequency());
		assertEquals(getMyModel().getStatementBalance(), record.getStatementBalance());
		assertEquals(getMyModel().getNoPostingsNotOnStatement(), record.getNoPostingsNotOnStatement());
		assertEquals(getMyModel().getLastStatementDate(), record.getLastStatementDate());
		assertEquals(getMyModel().getLastStatementNumber(), record.getLastStatementNumber());
		assertEquals(getMyModel().getShortNameOnStatement(), record.getShortNameOnStatement());
		assertEquals(getMyModel().getSuppressStatement(), record.getSuppressStatement());
		assertEquals(getMyModel().getStatementOnlyOnFrequency(), record.getStatementOnlyOnFrequency());
		assertEquals(getMyModel().getForceCycleStatement(), record.getForceCycleStatement());
		assertEquals(getMyModel().getCopyStatementToAccount(), record.getCopyStatementToAccount());
		assertEquals(getMyModel().getCopyStatementToCustomer(), record.getCopyStatementToCustomer());
		assertEquals(getMyModel().getHoldStatement(), record.getHoldStatement());
		assertEquals(getMyModel().getActionStatement(), record.getActionStatement());
		assertEquals(getMyModel().getStatementsOnPrinter2(), record.getStatementsOnPrinter2());
		assertEquals(getMyModel().getConfirmationsOnPrinter2(), record.getConfirmationsOnPrinter2());
		assertEquals(getMyModel().getSuppressConfirmations(), record.getSuppressConfirmations());
		assertEquals(getMyModel().getSuppressAddress(), record.getSuppressAddress());
		assertEquals(getMyModel().getNoStopConfirmations(), record.getNoStopConfirmations());
		assertEquals(getMyModel().getNoStandingOrderConfirmations(), record.getNoStandingOrderConfirmations());
		assertEquals(getMyModel().getNoBalanceOrderConfirmations(), record.getNoBalanceOrderConfirmations());
		assertEquals(getMyModel().getRetentionPeriod(), record.getRetentionPeriod());
		assertEquals(getMyModel().getSc080(), record.getSc080());
		assertEquals(getMyModel().getSc081(), record.getSc081());
		assertEquals(getMyModel().getSc082(), record.getSc082());
		assertEquals(getMyModel().getSc083(), record.getSc083());
		assertEquals(getMyModel().getSc084(), record.getSc084());
		assertEquals(getMyModel().getSc085(), record.getSc085());
		assertEquals(getMyModel().getSc086(), record.getSc086());
		assertEquals(getMyModel().getSc087(), record.getSc087());
		assertEquals(getMyModel().getSc090(), record.getSc090());
		assertEquals(getMyModel().getSc091(), record.getSc091());
		assertEquals(getMyModel().getSc092(), record.getSc092());
		assertEquals(getMyModel().getSc093(), record.getSc093());
		assertEquals(getMyModel().getSc094(), record.getSc094());
		assertEquals(getMyModel().getSc095(), record.getSc095());
		assertEquals(getMyModel().getSc096(), record.getSc096());
		assertEquals(getMyModel().getSc097(), record.getSc097());
		assertEquals(getMyModel().getNumberOfReconciliationChars(), record.getNumberOfReconciliationChars());
		assertEquals(getMyModel().getDateAcOpened(), record.getDateAcOpened());
		assertEquals(getMyModel().getDateLastEntry(), record.getDateLastEntry());
		assertEquals(getMyModel().getDateAccountClosed(), record.getDateAccountClosed());
		assertEquals(getMyModel().getTotalOverdraft(), record.getTotalOverdraft());
		assertEquals(getMyModel().getApprovedOverdraftExpiryDate(), record.getApprovedOverdraftExpiryDate());
		assertEquals(getMyModel().getDateLastMaintained(), record.getDateLastMaintained());
		assertEquals(getMyModel().getP1Rating(), record.getP1Rating());
		assertEquals(getMyModel().getP2Rating(), record.getP2Rating());
		assertEquals(getMyModel().getP3Rating(), record.getP3Rating());
		assertEquals(getMyModel().getP4Rating(), record.getP4Rating());
		assertEquals(getMyModel().getP5Rating(), record.getP5Rating());
		assertEquals(getMyModel().getC1Rating(), record.getC1Rating());
		assertEquals(getMyModel().getC2Rating(), record.getC2Rating());
		assertEquals(getMyModel().getC3Rating(), record.getC3Rating());
		assertEquals(getMyModel().getC4Rating(), record.getC4Rating());
		assertEquals(getMyModel().getC5Rating(), record.getC5Rating());
		assertEquals(getMyModel().getAccountOfficer(), record.getAccountOfficer());
		assertEquals(getMyModel().getLanguage(), record.getLanguage());
		assertEquals(getMyModel().getCapital(), record.getCapital());
		assertEquals(getMyModel().getTradeDate(), record.getTradeDate());
		assertEquals(getMyModel().getAccountClosing(), record.getAccountClosing());
		assertEquals(getMyModel().getDealInterestDaysBasis(), record.getDealInterestDaysBasis());
		assertEquals(getMyModel().getNominatedAccount(), record.getNominatedAccount());
		assertEquals(getMyModel().getDebitInterest(), record.getDebitInterest());
		assertEquals(getMyModel().getCreditInterest(), record.getCreditInterest());
		assertEquals(getMyModel().getCreditInterestAdjusted(), record.getCreditInterestAdjusted());
		assertEquals(getMyModel().getDebitInterestAdjusted(), record.getDebitInterestAdjusted());
		assertEquals(getMyModel().getAdviseRateChanges(), record.getAdviseRateChanges());
		assertEquals(getMyModel().getAcMovedSinceReorganisation(), record.getAcMovedSinceReorganisation());
		assertEquals(getMyModel().getGeneratedPostingsOnly(), record.getGeneratedPostingsOnly());
		assertEquals(getMyModel().getInterestBearing(), record.getInterestBearing());
		assertEquals(getMyModel().getMarketableSecurity(), record.getMarketableSecurity());
		assertEquals(getMyModel().getSc0g0(), record.getSc0g0());
		assertEquals(getMyModel().getSc0g1(), record.getSc0g1());
		assertEquals(getMyModel().getSc0g2(), record.getSc0g2());
		assertEquals(getMyModel().getSc0g3(), record.getSc0g3());
		assertEquals(getMyModel().getSc0g4(), record.getSc0g4());
		assertEquals(getMyModel().getSc0g5(), record.getSc0g5());
		assertEquals(getMyModel().getSc0g6(), record.getSc0g6());
		assertEquals(getMyModel().getSc0g7(), record.getSc0g7());
		assertEquals(getMyModel().getNetClearingLocal(), record.getNetClearingLocal());
		assertEquals(getMyModel().getPrintChargesStatement(), record.getPrintChargesStatement());
		assertEquals(getMyModel().getChargesStatementFrequency(), record.getChargesStatementFrequency());
		assertEquals(getMyModel().getLastChargesStatementDate(), record.getLastChargesStatementDate());
		assertEquals(getMyModel().getNextChargesStatementDate(), record.getNextChargesStatementDate());
		assertEquals(getMyModel().getArabicShortName(), record.getArabicShortName());
		assertEquals(getMyModel().getMirroredArabicShortName(), record.getMirroredArabicShortName());
		assertEquals(getMyModel().getInternalRiskCountry(), record.getInternalRiskCountry());
		assertEquals(getMyModel().getJointAc(), record.getJointAc());
		assertEquals(getMyModel().getIbTransactions(), record.getIbTransactions());
		assertEquals(getMyModel().getFontisAccount(), record.getFontisAccount());
		assertEquals(getMyModel().getFontisDownFreq(), record.getFontisDownFreq());
		assertEquals(getMyModel().getLastFontisStatBal(), record.getLastFontisStatBal());
		assertEquals(getMyModel().getLastFontisStatNo(), record.getLastFontisStatNo());
		assertEquals(getMyModel().getFontisDate(), record.getFontisDate());
		assertEquals(getMyModel().getDebitBalanceNotAllowed(), record.getDebitBalanceNotAllowed());
		assertEquals(getMyModel().getRealtimeBalanceOrder(), record.getRealtimeBalanceOrder());
		assertEquals(getMyModel().getNonaccrual(), record.getNonaccrual());
		assertEquals(getMyModel().getUserStatus(), record.getUserStatus());
		assertEquals(getMyModel().getCurrentStatus(), record.getCurrentStatus());
		assertEquals(getMyModel().getIntBillingAdvice(), record.getIntBillingAdvice());
		assertEquals(getMyModel().getDiscretionaryOverdraftsEffectiveToday(), record.getDiscretionaryOverdraftsEffectiveToday());
		assertEquals(getMyModel().getApprovedOverdraftLimit(), record.getApprovedOverdraftLimit());
		assertEquals(getMyModel().getOdAgainstUcCheques(), record.getOdAgainstUcCheques());
		assertEquals(getMyModel().getSuspOdUcCheques(), record.getSuspOdUcCheques());
		assertEquals(getMyModel().getDoesActive(), record.getDoesActive());
		assertEquals(getMyModel().getNoticeAccount(), record.getNoticeAccount());
		assertEquals(getMyModel().getSc190UserSpecialCondition(), record.getSc190UserSpecialCondition());
		assertEquals(getMyModel().getSc191UserSpecialCondition(), record.getSc191UserSpecialCondition());
		assertEquals(getMyModel().getSc192UserSpecialCondition(), record.getSc192UserSpecialCondition());
		assertEquals(getMyModel().getSc193UserSpecialCondition(), record.getSc193UserSpecialCondition());
		assertEquals(getMyModel().getSc194UserSpecialCondition(), record.getSc194UserSpecialCondition());
		assertEquals(getMyModel().getSc195UserSpecialCondition(), record.getSc195UserSpecialCondition());
		assertEquals(getMyModel().getSc196UserSpecialCondition(), record.getSc196UserSpecialCondition());
		assertEquals(getMyModel().getSc197UserSpecialCondition(), record.getSc197UserSpecialCondition());
		assertEquals(getMyModel().getCalculateInterestOnAverageDailyCreditBalance(), record
						.getCalculateInterestOnAverageDailyCreditBalance());
		assertEquals(getMyModel().getCalculateInterestOnAverageDailyCrdrBalance(), record
						.getCalculateInterestOnAverageDailyCrdrBalance());
		assertEquals(getMyModel().getSuppressConsolidatedStatements(), record.getSuppressConsolidatedStatements());
		assertEquals(getMyModel().getSuppressDetailedConsolidatedStmt(), record.getSuppressDetailedConsolidatedStmt());
		assertEquals(getMyModel().getInterestAccrualsAtStartOfDay(), record.getInterestAccrualsAtStartOfDay());
		assertEquals(getMyModel().getUserDefinedInterestAccounting(), record.getUserDefinedInterestAccounting());
		assertEquals(getMyModel().getSuppressForwardItemsOnStatements(), record.getSuppressForwardItemsOnStatements());
		assertEquals(getMyModel().getSc207ReservedForFutureUse(), record.getSc207ReservedForFutureUse());
		assertEquals(getMyModel().getSc210ReservedForFutureUse(), record.getSc210ReservedForFutureUse());
		assertEquals(getMyModel().getSuppressChequebookConfirmation(), record.getSuppressChequebookConfirmation());
		assertEquals(getMyModel().getDoNotIssueChequebook(), record.getDoNotIssueChequebook());
		assertEquals(getMyModel().getEligibleForQueuing(), record.getEligibleForQueuing());
		assertEquals(getMyModel().getQueueAllDebitTransaction(), record.getQueueAllDebitTransaction());
		assertEquals(getMyModel().getSuspendQueuedTransaction(), record.getSuspendQueuedTransaction());
		assertEquals(getMyModel().getCourtOrderExists(), record.getCourtOrderExists());
		assertEquals(getMyModel().getSc217ReservedForFutureUse(), record.getSc217ReservedForFutureUse());
		assertEquals(getMyModel().getParallelIasAccountInUse(), record.getParallelIasAccountInUse());
		assertEquals(getMyModel().getAddOverdraftConfirmations(), record.getAddOverdraftConfirmations());
		assertEquals(getMyModel().getSc222SelfserviceStatement(), record.getSc222SelfserviceStatement());
		assertEquals(getMyModel().getSc223SubjectToS24cReporting(), record.getSc223SubjectToS24cReporting());
		assertEquals(getMyModel().getAmendOverdraftConfirmation(), record.getAmendOverdraftConfirmation());
		assertEquals(getMyModel().getIncludeInAuditLetters(), record.getIncludeInAuditLetters());
		assertEquals(getMyModel().getSc226MdsAccount(), record.getSc226MdsAccount());
		assertEquals(getMyModel().getCorporateFxLimitApplies(), record.getCorporateFxLimitApplies());
		assertEquals(getMyModel().getSc230ReservedForFutureUse(), record.getSc230ReservedForFutureUse());
		assertEquals(getMyModel().getSc231ReservedForFutureUse(), record.getSc231ReservedForFutureUse());
		assertEquals(getMyModel().getSc232ReservedForFutureUse(), record.getSc232ReservedForFutureUse());
		assertEquals(getMyModel().getSc233ReservedForFutureUse(), record.getSc233ReservedForFutureUse());
		assertEquals(getMyModel().getSc234ReservedForFutureUse(), record.getSc234ReservedForFutureUse());
		assertEquals(getMyModel().getSc235ReservedForFutureUse(), record.getSc235ReservedForFutureUse());
		assertEquals(getMyModel().getSc236ReservedForFutureUse(), record.getSc236ReservedForFutureUse());
		assertEquals(getMyModel().getSc237ReservedForFutureUse(), record.getSc237ReservedForFutureUse());
		assertEquals(getMyModel().getCollateralRequired(), record.getCollateralRequired());
		assertEquals(getMyModel().getSourceOriginalBranch(), record.getSourceOriginalBranch());
		assertEquals(getMyModel().getOldAccountAlternativeKey(), record.getOldAccountAlternativeKey());
		assertEquals(getMyModel().getBranchChangeDate(), record.getBranchChangeDate());
		assertEquals(getMyModel().getLedgerBalanceInBaseCurrency(), record.getLedgerBalanceInBaseCurrency());
		assertEquals(getMyModel().getCreditShadowPostingsInBase(), record.getCreditShadowPostingsInBase());
		assertEquals(getMyModel().getDebitShadowPostingsInBase(), record.getDebitShadowPostingsInBase());
		assertEquals(getMyModel().getShadowAdjustmentsInBaseCurrency(), record.getShadowAdjustmentsInBaseCurrency());
		assertEquals(getMyModel().getNetItemsClearingTodayInBaseCurrency(), record.getNetItemsClearingTodayInBaseCurrency());
		assertEquals(getMyModel().getNetItemsClrgNextBusDayBaseCurrency(), record.getNetItemsClrgNextBusDayBaseCurrency());
		assertEquals(getMyModel().getNetItemsClrgAfterNextBusDayBaseCcy(), record.getNetItemsClrgAfterNextBusDayBaseCcy());
		assertEquals(getMyModel().getLedgerBalanceInUserCurrency(), record.getLedgerBalanceInUserCurrency());
		assertEquals(getMyModel().getCreditShadowPostingsInUser(), record.getCreditShadowPostingsInUser());
		assertEquals(getMyModel().getDebitShadowPostingsInUser(), record.getDebitShadowPostingsInUser());
		assertEquals(getMyModel().getShadowAdjustmentsInUserCurrency(), record.getShadowAdjustmentsInUserCurrency());
		assertEquals(getMyModel().getNetItemsClearingTodayInUserCurrency(), record.getNetItemsClearingTodayInUserCurrency());
		assertEquals(getMyModel().getNetItemsClrgNextBusDayUserCurrency(), record.getNetItemsClrgNextBusDayUserCurrency());
		assertEquals(getMyModel().getNetItemsClrgAfterNextBusDayUserCcy(), record.getNetItemsClrgAfterNextBusDayUserCcy());
		assertEquals(getMyModel().getReservedBalanceForCollateralHoldsAssignments(), record
						.getReservedBalanceForCollateralHoldsAssignments());
		assertEquals(getMyModel().getYesterdaysUserDefinedIntAccounting(), record.getYesterdaysUserDefinedIntAccounting());
		assertEquals(getMyModel().getHoldQueuedAmount(), record.getHoldQueuedAmount());
		assertEquals(getMyModel().getCountingPeriodDefined(), record.getCountingPeriodDefined());
		assertEquals(getMyModel().getChargeStructureId(), record.getChargeStructureId());
		assertEquals(getMyModel().getProductIdentifier(), record.getProductIdentifier());
		assertEquals(getMyModel().getBundleIdentifier(), record.getBundleIdentifier());
		assertEquals(getMyModel().getSegmentIdentifier(), record.getSegmentIdentifier());
		assertEquals(getMyModel().getTemporaryShadowAdjustments(), record.getTemporaryShadowAdjustments());
		assertEquals(getMyModel().getTemporaryShadowAdjustmentsInBaseCurrency(), record
						.getTemporaryShadowAdjustmentsInBaseCurrency());
		assertEquals(getMyModel().getTemporaryShadowAdjustmentsInUserCurrency(), record
						.getTemporaryShadowAdjustmentsInUserCurrency());
		assertEquals(getMyModel().getConfirmedSelfService(), record.getConfirmedSelfService());
		assertEquals(getMyModel().getFailedSelfService(), record.getFailedSelfService());
		assertEquals(getMyModel().getForcedSelfService(), record.getForcedSelfService());
		assertEquals(getMyModel().getTaxAdviceType(), record.getTaxAdviceType());
		assertEquals(getMyModel().getTaxAdviceTypeNextYear(), record.getTaxAdviceTypeNextYear());

	}
	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((ISC1RecordDao) dao).checkIfThisSC1RecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		SC1RecordDataModel record = new SC1RecordDataModel("Stri", "String", "Str");
		record.setApplication("St");
		record.setAccountStructure("S");
		record.setContingent("S");
		record.setInternalAccount("S");
		record.setAdjustedBalanceLastNight(9688888223L);
		record.setAdjustedBalanceLastPeriod(9688888223L);
		record.setClosingBalance(9688888223L);
		record.setNetClearingToday(9688888223L);
		record.setNetClearingTomorrow(9688888223L);
		record.setNetClearingAfterTomorrow(9688888223L);
		record.setShadowDebits(9688888223L);
		record.setShadowCredits(9688888223L);
		record.setReservedBalance(9688888223L);
		record.setDaylightReservedBalance("S");
		record.setShadowAdjustments(9688888223L);
		record.setAccountShortName("String_TEST");
		record.setCustomerType("St");
		record.setAcType("St");
		record.setAnalysisCode("St");
		record.setSundryAnalysisCode("St");
		record.setNumericAnalysisCode("Strin");
		record.setResidenceCountry("St");
		record.setCcy("Str");
		record.setRiskCountry("St");
		record.setParentCountry("St");
		record.setPostageCharged("S");
		record.setSecurityHeld("S");
		record.setAdviseTrans("S");
		record.setStoppedCheques("S");
		record.setStandingOrder("S");
		record.setDebitStats("S");
		record.setCreditStats("S");
		record.setHighBalStats("S");
		record.setLowBalStats("S");
		record.setDrIntStats("S");
		record.setCrIntStats("S");
		record.setPLStats("S");
		record.setDrToStats("S");
		record.setCrToStats("S");
		record.setEligibleForExtSystemTransfer("S");
		record.setBulk("S");
		record.setSeparateInterestCharges("S");
		record.setPassBook("S");
		record.setTaxCharges("S");
		record.setTaxInterest("S");
		record.setCharges("S");
		record.setBoHeld("S");
		record.setFundingReceiving("S");
		record.setDiaryNotesHeld("S");
		record.setSpecialInstructions("S");
		record.setReferCredits("S");
		record.setReferDebits("S");
		record.setReferCreditBalance("S");
		record.setDoNotReferDrBalance("S");
		record.setDeceasedOrLiquidated("S");
		record.setBlocked("S");
		record.setInactive("S");
		record.setZeroBalanceRequired("S");
		record.setClearedBalance("S");
		record.setClearOnlyCredits("S");
		record.setStatementFrequency("Str");
		record.setStatementBalance(9688888223L);
		record.setNoPostingsNotOnStatement(33);
		record.setLastStatementDate(1);
		record.setLastStatementNumber(33);
		record.setShortNameOnStatement("S");
		record.setSuppressStatement("S");
		record.setStatementOnlyOnFrequency("S");
		record.setForceCycleStatement("S");
		record.setCopyStatementToAccount("S");
		record.setCopyStatementToCustomer("S");
		record.setHoldStatement("S");
		record.setActionStatement("S");
		record.setStatementsOnPrinter2("S");
		record.setConfirmationsOnPrinter2("S");
		record.setSuppressConfirmations("S");
		record.setSuppressAddress("S");
		record.setNoStopConfirmations("S");
		record.setNoStandingOrderConfirmations("S");
		record.setNoBalanceOrderConfirmations("S");
		record.setRetentionPeriod("St");
		record.setSc080("S");
		record.setSc081("S");
		record.setSc082("S");
		record.setSc083("S");
		record.setSc084("S");
		record.setSc085("S");
		record.setSc086("S");
		record.setSc087("S");
		record.setSc090("S");
		record.setSc091("S");
		record.setSc092("S");
		record.setSc093("S");
		record.setSc094("S");
		record.setSc095("S");
		record.setSc096("S");
		record.setSc097("S");
		record.setNumberOfReconciliationChars(33);
		record.setDateAcOpened(1);
		record.setDateLastEntry(1);
		record.setDateAccountClosed(1);
		record.setTotalOverdraft(9688888223L);
		record.setApprovedOverdraftExpiryDate(1);
		record.setDateLastMaintained(1);
		record.setP1Rating("Str");
		record.setP2Rating("Str");
		record.setP3Rating("Str");
		record.setP4Rating("Str");
		record.setP5Rating("Str");
		record.setC1Rating("St");
		record.setC2Rating("St");
		record.setC3Rating("St");
		record.setC4Rating("St");
		record.setC5Rating("St");
		record.setAccountOfficer("Str");
		record.setLanguage("St");
		record.setCapital("S");
		record.setTradeDate("S");
		record.setAccountClosing("S");
		record.setDealInterestDaysBasis("S");
		record.setNominatedAccount("S");
		record.setDebitInterest("S");
		record.setCreditInterest("S");
		record.setCreditInterestAdjusted("S");
		record.setDebitInterestAdjusted("S");
		record.setAdviseRateChanges("S");
		record.setAcMovedSinceReorganisation("S");
		record.setGeneratedPostingsOnly("S");
		record.setInterestBearing("S");
		record.setMarketableSecurity("S");
		record.setSc0g0("S");
		record.setSc0g1("S");
		record.setSc0g2("S");
		record.setSc0g3("S");
		record.setSc0g4("S");
		record.setSc0g5("S");
		record.setSc0g6("S");
		record.setSc0g7("S");
		record.setNetClearingLocal(9688888223L);
		record.setPrintChargesStatement("S");
		record.setChargesStatementFrequency("Str");
		record.setLastChargesStatementDate(1);
		record.setNextChargesStatementDate(1);
		record.setArabicShortName("String_TEST");
		record.setMirroredArabicShortName("String_TEST");
		record.setInternalRiskCountry("St");
		record.setJointAc("S");
		record.setIbTransactions("S");
		record.setFontisAccount("S");
		record.setFontisDownFreq(33);
		record.setLastFontisStatBal(9688888223L);
		record.setLastFontisStatNo(33);
		record.setFontisDate(1);
		record.setDebitBalanceNotAllowed("S");
		record.setRealtimeBalanceOrder("S");
		record.setNonaccrual("S");
		record.setUserStatus("S");
		record.setCurrentStatus("St");
		record.setIntBillingAdvice("S");
		record.setDiscretionaryOverdraftsEffectiveToday(9688888223L);
		record.setApprovedOverdraftLimit(9688888223L);
		record.setOdAgainstUcCheques("S");
		record.setSuspOdUcCheques("S");
		record.setDoesActive("S");
		record.setNoticeAccount("S");
		record.setSc190UserSpecialCondition("S");
		record.setSc191UserSpecialCondition("S");
		record.setSc192UserSpecialCondition("S");
		record.setSc193UserSpecialCondition("S");
		record.setSc194UserSpecialCondition("S");
		record.setSc195UserSpecialCondition("S");
		record.setSc196UserSpecialCondition("S");
		record.setSc197UserSpecialCondition("S");
		record.setCalculateInterestOnAverageDailyCreditBalance("S");
		record.setCalculateInterestOnAverageDailyCrdrBalance("S");
		record.setSuppressConsolidatedStatements("S");
		record.setSuppressDetailedConsolidatedStmt("S");
		record.setInterestAccrualsAtStartOfDay("S");
		record.setUserDefinedInterestAccounting("S");
		record.setSuppressForwardItemsOnStatements("S");
		record.setSc207ReservedForFutureUse("S");
		record.setSc210ReservedForFutureUse("S");
		record.setSuppressChequebookConfirmation("S");
		record.setDoNotIssueChequebook("S");
		record.setEligibleForQueuing("S");
		record.setQueueAllDebitTransaction("S");
		record.setSuspendQueuedTransaction("S");
		record.setCourtOrderExists("S");
		record.setSc217ReservedForFutureUse("S");
		record.setParallelIasAccountInUse("S");
		record.setAddOverdraftConfirmations("S");
		record.setSc222SelfserviceStatement("S");
		record.setSc223SubjectToS24cReporting("S");
		record.setAmendOverdraftConfirmation("S");
		record.setIncludeInAuditLetters("S");
		record.setSc226MdsAccount("S");
		record.setCorporateFxLimitApplies("S");
		record.setSc230ReservedForFutureUse("S");
		record.setSc231ReservedForFutureUse("S");
		record.setSc232ReservedForFutureUse("S");
		record.setSc233ReservedForFutureUse("S");
		record.setSc234ReservedForFutureUse("S");
		record.setSc235ReservedForFutureUse("S");
		record.setSc236ReservedForFutureUse("S");
		record.setSc237ReservedForFutureUse("S");
		record.setCollateralRequired("S");
		record.setSourceOriginalBranch("Stri");
		record.setOldAccountAlternativeKey("String_TEST");
		record.setBranchChangeDate(1);
		record.setLedgerBalanceInBaseCurrency(9688888223L);
		record.setCreditShadowPostingsInBase(9688888223L);
		record.setDebitShadowPostingsInBase(9688888223L);
		record.setShadowAdjustmentsInBaseCurrency(9688888223L);
		record.setNetItemsClearingTodayInBaseCurrency(9688888223L);
		record.setNetItemsClrgNextBusDayBaseCurrency(9688888223L);
		record.setNetItemsClrgAfterNextBusDayBaseCcy(9688888223L);
		record.setLedgerBalanceInUserCurrency(9688888223L);
		record.setCreditShadowPostingsInUser(9688888223L);
		record.setDebitShadowPostingsInUser(9688888223L);
		record.setShadowAdjustmentsInUserCurrency(9688888223L);
		record.setNetItemsClearingTodayInUserCurrency(9688888223L);
		record.setNetItemsClrgNextBusDayUserCurrency(9688888223L);
		record.setNetItemsClrgAfterNextBusDayUserCcy(9688888223L);
		record.setReservedBalanceForCollateralHoldsAssignments(9688888223L);
		record.setYesterdaysUserDefinedIntAccounting("S");
		record.setHoldQueuedAmount(9688888223L);
		record.setCountingPeriodDefined("S");
		record.setChargeStructureId(1);
		record.setProductIdentifier("String");
		record.setBundleIdentifier("String");
		record.setSegmentIdentifier("String");
		record.setTemporaryShadowAdjustments(9688888223L);
		record.setTemporaryShadowAdjustmentsInBaseCurrency(9688888223L);
		record.setTemporaryShadowAdjustmentsInUserCurrency(9688888223L);
		record.setConfirmedSelfService(33);
		record.setFailedSelfService(33);
		record.setForcedSelfService(33);
		record.setTaxAdviceType("S");
		record.setTaxAdviceTypeNextYear("S");

		record.setLibrary(kFilLibName);
		this.dataModel = record;
	}
	/**
	 * This method is going to return a cast data model.
	 * 
	 * @return a cast data model
	 */
	public SC1RecordDataModel getMyModel()
	{
		SC1RecordDataModel record = null;

		if (dataModel instanceof SC1RecordDataModel)
		{
			record = (SC1RecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}