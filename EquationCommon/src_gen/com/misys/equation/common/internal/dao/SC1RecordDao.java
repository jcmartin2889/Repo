package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.ISC1RecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.SC1RecordDataModel;
import com.misys.equation.common.internal.dao.mappers.SC1RecordRowMapper;

/**
 * This the SC1 -Record Dao implementation. <br>
 * This class is going to provide all back-end services for SC1 -Record.
 * 
 * @author deroset
 */
public class SC1RecordDao extends AbsEquationDao implements ISC1RecordDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1281524634939l;

	public SC1RecordDao()
	{
		super();
	}

	/**
	 * This method will check if the current record is already in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @param sqlWhereStatement
	 *            - the WHERE clause of the SQL statement
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisSC1RecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisSC1RecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public SC1RecordDataModel getMyDataModel()
	{
		SC1RecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof SC1RecordDataModel)
		{
			dataModel = (SC1RecordDataModel) getRecord();
		}
		return dataModel;
	}

	/**
	 * This method will return a <code>String</code> which represents and sql where condition base on the record id.<br>
	 * For example <code>id = getDataModel.getId()</code>
	 * 
	 * @return a <code>String</code> which represents and sql filter.
	 */
	@Override
	protected String getWhereConditionBaseOnIdRecord()
	{
		StringBuilder whereCondition = new StringBuilder(1024);
		whereCondition.append("SCAB ='");
		whereCondition.append(getMyDataModel().getAccountBranch());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("SCAN ='");
		whereCondition.append(getMyDataModel().getBasicNumber());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("SCAS ='");
		whereCondition.append(getMyDataModel().getAccountSuffix());
		whereCondition.append("'");

		return whereCondition.toString();
	}

	/**
	 * Returns a list of name and ? pairs in the format of:
	 * <p>
	 * field1=?, field2=?, field3=?, ...
	 * 
	 * @return a list of name and ? pairs
	 */
	@Override
	protected String getParameterizedFields()
	{
		String fields = "SCAB= ?, SCAN= ?, SCAS= ?, SCAPP= ?, SCACS= ?, SCAI46= ?, SCAI47= ?, SCBALL= ?, SCBALP= ?, SCBAL= ?, SCSUM0= ?, SCSUM1= ?, SCSUM2= ?, SCSUMD= ?, SCSUMC= ?, SCRBA= ?, SCAIE6= ?, SCSUMA= ?, SCSHN= ?, SCCTP= ?, SCACT= ?, SCACD= ?, SCSAC= ?, SCNANC= ?, SCCNAL= ?, SCCCY= ?, SCCNAR= ?, SCCNAP= ?, SCAI35= ?, SCAI36= ?, SCAI66= ?, SCAI71= ?, SCAI72= ?, SCAIA0= ?, SCAIA1= ?, SCAIA2= ?, SCAIA3= ?, SCAIB0= ?, SCAIB1= ?, SCAIB2= ?, SCAIB3= ?, SCAIB4= ?, SCAIC5= ?, SCAIE0= ?, SCAIE4= ?, SCAIF0= ?, SCAIH2= ?, SCAIF5= ?, SCAIH1= ?, SCAIH3= ?, SCAIH5= ?, SCAIH4= ?, SCAI10= ?, SCAI11= ?, SCAI12= ?, SCAI31= ?, SCAI32= ?, SCAI14= ?, SCAI17= ?, SCAI20= ?, SCAIE1= ?, SCAID2= ?, SCAIF2= ?, SCSFC= ?, SCBALS= ?, SCNPE= ?, SCSTML= ?, SCSTNL= ?, SCAI21= ?, SCAID4= ?, SCAID5= ?, SCAI37= ?, SCAI60= ?, SCAI61= ?, SCAI64= ?, SCAI65= ?, SCAID6= ?, SCAID7= ?, SCAID3= ?, SCAI73= ?, SCAIF6= ?, SCAIF7= ?, SCAIH6= ?, SCRETP= ?, SCAI80= ?, SCAI81= ?, SCAI82= ?, SCAI83= ?, SCAI84= ?, SCAI85= ?, SCAI86= ?, SCAI87= ?, SCAI90= ?, SCAI91= ?, SCAI92= ?, SCAI93= ?, SCAI94= ?, SCAI95= ?, SCAI96= ?, SCAI97= ?, SCNS3= ?, SCOAD= ?, SCDLE= ?, SCCAD= ?, SCODL= ?, SCLED= ?, SCDLM= ?, SCP1R= ?, SCP2R= ?, SCP3R= ?, SCP4R= ?, SCP5R= ?, SCC1R= ?, SCC2R= ?, SCC3R= ?, SCC4R= ?, SCC5R= ?, SCACO= ?, SCLNM= ?, SCAI62= ?, SCAI63= ?, SCAI30= ?, SCIDB= ?, SCAI13= ?, SCAI15= ?, SCAI16= ?, SCAI24= ?, SCAI25= ?, SCAI67= ?, SCAI26= ?, SCAIC4= ?, SCII0A= ?, SCAI54= ?, SCAIG0= ?, SCAIG1= ?, SCAIG2= ?, SCAIG3= ?, SCAIG4= ?, SCAIG5= ?, SCAIG6= ?, SCAIG7= ?, SCSUML= ?, SCAI33= ?, SCCSFC= ?, SCCSTL= ?, SCCSTN= ?, SCSHNA= ?, SCSHNM= ?, SCCNAI= ?, SCAIC7= ?, SCAIA5= ?, SCYFON= ?, SCDFRQ= ?, SCFBLS= ?, SCFSTL= ?, SCFDT= ?, SCAIA6= ?, SCAIA7= ?, SCAIB5= ?, SCAIB6= ?, SCSTC= ?, SCAIB7= ?, SCDODL= ?, SCAODL= ?, SCAII4= ?, SCAII5= ?, SCAII6= ?, SCAII7= ?, SCAIJ0= ?, SCAIJ1= ?, SCAIJ2= ?, SCAIJ3= ?, SCAIJ4= ?, SCAIJ5= ?, SCAIJ6= ?, SCAIJ7= ?, SCAIK0= ?, SCAIK1= ?, SCAIK2= ?, SCAIK3= ?, SCAIK4= ?, SCAIK5= ?, SCAIK6= ?, SCAIK7= ?, SCAIL0= ?, SCAIL1= ?, SCAIL2= ?, SCAIL3= ?, SCAIL4= ?, SCAIL5= ?, SCAIL6= ?, SCAIL7= ?, SCAIM0= ?, SCAIM1= ?, SCAIM2= ?, SCAIM3= ?, SCAIM4= ?, SCAIM5= ?, SCAIM6= ?, SCAIM7= ?, SCAIN0= ?, SCAIN1= ?, SCAIN2= ?, SCAIN3= ?, SCAIN4= ?, SCAIN5= ?, SCAIN6= ?, SCAIN7= ?, SCCLT= ?, SCPAB= ?, SCOLDA= ?, SCPCHD= ?, SCBALB= ?, SCSMBC= ?, SCSMBD= ?, SCSMBA= ?, SCSMB0= ?, SCSMB1= ?, SCSMB2= ?, SCBALO= ?, SCSMOC= ?, SCSMOD= ?, SCSMOA= ?, SCSMO0= ?, SCSMO1= ?, SCSMO2= ?, SCRBB= ?, SCYIK5= ?, SCRBC= ?, SCCNTP= ?, SCCHGS= ?, SCPRID= ?, SCBID= ?, SCSEID= ?, SCSHD= ?, SCSHDB= ?, SCSHDO= ?, SCSSCF= ?, SCSSFL= ?, SCSSFR= ?, SCTADT= ?, SCTADN= ?";
		return fields;
	}

	/**
	 * Returns a list of the filed's name
	 * 
	 * @return a list of the filed's name
	 */
	@Override
	protected String getFields()
	{
		String fields = "SCAB, SCAN, SCAS, SCAPP, SCACS, SCAI46, SCAI47, SCBALL, SCBALP, SCBAL, SCSUM0, SCSUM1, SCSUM2, SCSUMD, SCSUMC, SCRBA, SCAIE6, SCSUMA, SCSHN, SCCTP, SCACT, SCACD, SCSAC, SCNANC, SCCNAL, SCCCY, SCCNAR, SCCNAP, SCAI35, SCAI36, SCAI66, SCAI71, SCAI72, SCAIA0, SCAIA1, SCAIA2, SCAIA3, SCAIB0, SCAIB1, SCAIB2, SCAIB3, SCAIB4, SCAIC5, SCAIE0, SCAIE4, SCAIF0, SCAIH2, SCAIF5, SCAIH1, SCAIH3, SCAIH5, SCAIH4, SCAI10, SCAI11, SCAI12, SCAI31, SCAI32, SCAI14, SCAI17, SCAI20, SCAIE1, SCAID2, SCAIF2, SCSFC, SCBALS, SCNPE, SCSTML, SCSTNL, SCAI21, SCAID4, SCAID5, SCAI37, SCAI60, SCAI61, SCAI64, SCAI65, SCAID6, SCAID7, SCAID3, SCAI73, SCAIF6, SCAIF7, SCAIH6, SCRETP, SCAI80, SCAI81, SCAI82, SCAI83, SCAI84, SCAI85, SCAI86, SCAI87, SCAI90, SCAI91, SCAI92, SCAI93, SCAI94, SCAI95, SCAI96, SCAI97, SCNS3, SCOAD, SCDLE, SCCAD, SCODL, SCLED, SCDLM, SCP1R, SCP2R, SCP3R, SCP4R, SCP5R, SCC1R, SCC2R, SCC3R, SCC4R, SCC5R, SCACO, SCLNM, SCAI62, SCAI63, SCAI30, SCIDB, SCAI13, SCAI15, SCAI16, SCAI24, SCAI25, SCAI67, SCAI26, SCAIC4, SCII0A, SCAI54, SCAIG0, SCAIG1, SCAIG2, SCAIG3, SCAIG4, SCAIG5, SCAIG6, SCAIG7, SCSUML, SCAI33, SCCSFC, SCCSTL, SCCSTN, SCSHNA, SCSHNM, SCCNAI, SCAIC7, SCAIA5, SCYFON, SCDFRQ, SCFBLS, SCFSTL, SCFDT, SCAIA6, SCAIA7, SCAIB5, SCAIB6, SCSTC, SCAIB7, SCDODL, SCAODL, SCAII4, SCAII5, SCAII6, SCAII7, SCAIJ0, SCAIJ1, SCAIJ2, SCAIJ3, SCAIJ4, SCAIJ5, SCAIJ6, SCAIJ7, SCAIK0, SCAIK1, SCAIK2, SCAIK3, SCAIK4, SCAIK5, SCAIK6, SCAIK7, SCAIL0, SCAIL1, SCAIL2, SCAIL3, SCAIL4, SCAIL5, SCAIL6, SCAIL7, SCAIM0, SCAIM1, SCAIM2, SCAIM3, SCAIM4, SCAIM5, SCAIM6, SCAIM7, SCAIN0, SCAIN1, SCAIN2, SCAIN3, SCAIN4, SCAIN5, SCAIN6, SCAIN7, SCCLT, SCPAB, SCOLDA, SCPCHD, SCBALB, SCSMBC, SCSMBD, SCSMBA, SCSMB0, SCSMB1, SCSMB2, SCBALO, SCSMOC, SCSMOD, SCSMOA, SCSMO0, SCSMO1, SCSMO2, SCRBB, SCYIK5, SCRBC, SCCNTP, SCCHGS, SCPRID, SCBID, SCSEID, SCSHD, SCSHDB, SCSHDO, SCSSCF, SCSSFL, SCSSFR, SCTADT, SCTADN ";
		return fields;
	}

	/**
	 * Returns a list of the filed's parameters
	 * 
	 * @return the list of the filed's parameters
	 */
	@Override
	protected String getParameters()
	{
		String fields = "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
		return fields;
	}

	/**
	 * This method create an array that contains the fields values and Its types. <br>
	 * It will be used by JDBC <code>PreparedStatement</code>
	 * 
	 * @return An array that contains the field values and their types.
	 */
	@Override
	public Object[] getParameterizedFieldsValues()
	{
		SC1RecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getAccountBranch(), dataModel.getBasicNumber(), dataModel.getAccountSuffix(),
						dataModel.getApplication(), dataModel.getAccountStructure(), dataModel.getContingent(),
						dataModel.getInternalAccount(), dataModel.getAdjustedBalanceLastNight(),
						dataModel.getAdjustedBalanceLastPeriod(), dataModel.getClosingBalance(), dataModel.getNetClearingToday(),
						dataModel.getNetClearingTomorrow(), dataModel.getNetClearingAfterTomorrow(), dataModel.getShadowDebits(),
						dataModel.getShadowCredits(), dataModel.getReservedBalance(), dataModel.getDaylightReservedBalance(),
						dataModel.getShadowAdjustments(), dataModel.getAccountShortName(), dataModel.getCustomerType(),
						dataModel.getAcType(), dataModel.getAnalysisCode(), dataModel.getSundryAnalysisCode(),
						dataModel.getNumericAnalysisCode(), dataModel.getResidenceCountry(), dataModel.getCcy(),
						dataModel.getRiskCountry(), dataModel.getParentCountry(), dataModel.getPostageCharged(),
						dataModel.getSecurityHeld(), dataModel.getAdviseTrans(), dataModel.getStoppedCheques(),
						dataModel.getStandingOrder(), dataModel.getDebitStats(), dataModel.getCreditStats(),
						dataModel.getHighBalStats(), dataModel.getLowBalStats(), dataModel.getDrIntStats(),
						dataModel.getCrIntStats(), dataModel.getPLStats(), dataModel.getDrToStats(), dataModel.getCrToStats(),
						dataModel.getEligibleForExtSystemTransfer(), dataModel.getBulk(), dataModel.getSeparateInterestCharges(),
						dataModel.getPassBook(), dataModel.getTaxCharges(), dataModel.getTaxInterest(), dataModel.getCharges(),
						dataModel.getBoHeld(), dataModel.getFundingReceiving(), dataModel.getDiaryNotesHeld(),
						dataModel.getSpecialInstructions(), dataModel.getReferCredits(), dataModel.getReferDebits(),
						dataModel.getReferCreditBalance(), dataModel.getDoNotReferDrBalance(), dataModel.getDeceasedOrLiquidated(),
						dataModel.getBlocked(), dataModel.getInactive(), dataModel.getZeroBalanceRequired(),
						dataModel.getClearedBalance(), dataModel.getClearOnlyCredits(), dataModel.getStatementFrequency(),
						dataModel.getStatementBalance(), dataModel.getNoPostingsNotOnStatement(), dataModel.getLastStatementDate(),
						dataModel.getLastStatementNumber(), dataModel.getShortNameOnStatement(), dataModel.getSuppressStatement(),
						dataModel.getStatementOnlyOnFrequency(), dataModel.getForceCycleStatement(),
						dataModel.getCopyStatementToAccount(), dataModel.getCopyStatementToCustomer(),
						dataModel.getHoldStatement(), dataModel.getActionStatement(), dataModel.getStatementsOnPrinter2(),
						dataModel.getConfirmationsOnPrinter2(), dataModel.getSuppressConfirmations(),
						dataModel.getSuppressAddress(), dataModel.getNoStopConfirmations(),
						dataModel.getNoStandingOrderConfirmations(), dataModel.getNoBalanceOrderConfirmations(),
						dataModel.getRetentionPeriod(), dataModel.getSc080(), dataModel.getSc081(), dataModel.getSc082(),
						dataModel.getSc083(), dataModel.getSc084(), dataModel.getSc085(), dataModel.getSc086(),
						dataModel.getSc087(), dataModel.getSc090(), dataModel.getSc091(), dataModel.getSc092(),
						dataModel.getSc093(), dataModel.getSc094(), dataModel.getSc095(), dataModel.getSc096(),
						dataModel.getSc097(), dataModel.getNumberOfReconciliationChars(), dataModel.getDateAcOpened(),
						dataModel.getDateLastEntry(), dataModel.getDateAccountClosed(), dataModel.getTotalOverdraft(),
						dataModel.getApprovedOverdraftExpiryDate(), dataModel.getDateLastMaintained(), dataModel.getP1Rating(),
						dataModel.getP2Rating(), dataModel.getP3Rating(), dataModel.getP4Rating(), dataModel.getP5Rating(),
						dataModel.getC1Rating(), dataModel.getC2Rating(), dataModel.getC3Rating(), dataModel.getC4Rating(),
						dataModel.getC5Rating(), dataModel.getAccountOfficer(), dataModel.getLanguage(), dataModel.getCapital(),
						dataModel.getTradeDate(), dataModel.getAccountClosing(), dataModel.getDealInterestDaysBasis(),
						dataModel.getNominatedAccount(), dataModel.getDebitInterest(), dataModel.getCreditInterest(),
						dataModel.getCreditInterestAdjusted(), dataModel.getDebitInterestAdjusted(),
						dataModel.getAdviseRateChanges(), dataModel.getAcMovedSinceReorganisation(),
						dataModel.getGeneratedPostingsOnly(), dataModel.getInterestBearing(), dataModel.getMarketableSecurity(),
						dataModel.getSc0g0(), dataModel.getSc0g1(), dataModel.getSc0g2(), dataModel.getSc0g3(),
						dataModel.getSc0g4(), dataModel.getSc0g5(), dataModel.getSc0g6(), dataModel.getSc0g7(),
						dataModel.getNetClearingLocal(), dataModel.getPrintChargesStatement(),
						dataModel.getChargesStatementFrequency(), dataModel.getLastChargesStatementDate(),
						dataModel.getNextChargesStatementDate(), dataModel.getArabicShortName(),
						dataModel.getMirroredArabicShortName(), dataModel.getInternalRiskCountry(), dataModel.getJointAc(),
						dataModel.getIbTransactions(), dataModel.getFontisAccount(), dataModel.getFontisDownFreq(),
						dataModel.getLastFontisStatBal(), dataModel.getLastFontisStatNo(), dataModel.getFontisDate(),
						dataModel.getDebitBalanceNotAllowed(), dataModel.getRealtimeBalanceOrder(), dataModel.getNonaccrual(),
						dataModel.getUserStatus(), dataModel.getCurrentStatus(), dataModel.getIntBillingAdvice(),
						dataModel.getDiscretionaryOverdraftsEffectiveToday(), dataModel.getApprovedOverdraftLimit(),
						dataModel.getOdAgainstUcCheques(), dataModel.getSuspOdUcCheques(), dataModel.getDoesActive(),
						dataModel.getNoticeAccount(), dataModel.getSc190UserSpecialCondition(),
						dataModel.getSc191UserSpecialCondition(), dataModel.getSc192UserSpecialCondition(),
						dataModel.getSc193UserSpecialCondition(), dataModel.getSc194UserSpecialCondition(),
						dataModel.getSc195UserSpecialCondition(), dataModel.getSc196UserSpecialCondition(),
						dataModel.getSc197UserSpecialCondition(), dataModel.getCalculateInterestOnAverageDailyCreditBalance(),
						dataModel.getCalculateInterestOnAverageDailyCrdrBalance(), dataModel.getSuppressConsolidatedStatements(),
						dataModel.getSuppressDetailedConsolidatedStmt(), dataModel.getInterestAccrualsAtStartOfDay(),
						dataModel.getUserDefinedInterestAccounting(), dataModel.getSuppressForwardItemsOnStatements(),
						dataModel.getSc207ReservedForFutureUse(), dataModel.getSc210ReservedForFutureUse(),
						dataModel.getSuppressChequebookConfirmation(), dataModel.getDoNotIssueChequebook(),
						dataModel.getEligibleForQueuing(), dataModel.getQueueAllDebitTransaction(),
						dataModel.getSuspendQueuedTransaction(), dataModel.getCourtOrderExists(),
						dataModel.getSc217ReservedForFutureUse(), dataModel.getParallelIasAccountInUse(),
						dataModel.getAddOverdraftConfirmations(), dataModel.getSc222SelfserviceStatement(),
						dataModel.getSc223SubjectToS24cReporting(), dataModel.getAmendOverdraftConfirmation(),
						dataModel.getIncludeInAuditLetters(), dataModel.getSc226MdsAccount(),
						dataModel.getCorporateFxLimitApplies(), dataModel.getSc230ReservedForFutureUse(),
						dataModel.getSc231ReservedForFutureUse(), dataModel.getSc232ReservedForFutureUse(),
						dataModel.getSc233ReservedForFutureUse(), dataModel.getSc234ReservedForFutureUse(),
						dataModel.getSc235ReservedForFutureUse(), dataModel.getSc236ReservedForFutureUse(),
						dataModel.getSc237ReservedForFutureUse(), dataModel.getCollateralRequired(),
						dataModel.getSourceOriginalBranch(), dataModel.getOldAccountAlternativeKey(),
						dataModel.getBranchChangeDate(), dataModel.getLedgerBalanceInBaseCurrency(),
						dataModel.getCreditShadowPostingsInBase(), dataModel.getDebitShadowPostingsInBase(),
						dataModel.getShadowAdjustmentsInBaseCurrency(), dataModel.getNetItemsClearingTodayInBaseCurrency(),
						dataModel.getNetItemsClrgNextBusDayBaseCurrency(), dataModel.getNetItemsClrgAfterNextBusDayBaseCcy(),
						dataModel.getLedgerBalanceInUserCurrency(), dataModel.getCreditShadowPostingsInUser(),
						dataModel.getDebitShadowPostingsInUser(), dataModel.getShadowAdjustmentsInUserCurrency(),
						dataModel.getNetItemsClearingTodayInUserCurrency(), dataModel.getNetItemsClrgNextBusDayUserCurrency(),
						dataModel.getNetItemsClrgAfterNextBusDayUserCcy(),
						dataModel.getReservedBalanceForCollateralHoldsAssignments(),
						dataModel.getYesterdaysUserDefinedIntAccounting(), dataModel.getHoldQueuedAmount(),
						dataModel.getCountingPeriodDefined(), dataModel.getChargeStructureId(), dataModel.getProductIdentifier(),
						dataModel.getBundleIdentifier(), dataModel.getSegmentIdentifier(),
						dataModel.getTemporaryShadowAdjustments(), dataModel.getTemporaryShadowAdjustmentsInBaseCurrency(),
						dataModel.getTemporaryShadowAdjustmentsInUserCurrency(), dataModel.getConfirmedSelfService(),
						dataModel.getFailedSelfService(), dataModel.getForcedSelfService(), dataModel.getTaxAdviceType(),
						dataModel.getTaxAdviceTypeNextYear() };
		return object;
	}

	/**
	 * This method is going execute an SQL query using the filter criteria.
	 * 
	 * @param whereClause
	 *            this is the <code>String</code> that represent the SQL filter.
	 * @return a <code>List</code> that contains the records
	 */
	public List<AbsRecord> getRecordBy(String whereClause)
	{
		return getRecordBy(whereClause, new SC1RecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> that contains the records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new SC1RecordRowMapper());
	}

	/**
	 * This method is going to return a <code> SC1RecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> SC1RecordDataModel </code> base on $tablePrefix-ID
	 */
	public SC1RecordDataModel getSC1Record()
	{
		SC1RecordDataModel dataModel = null;
		List results = getRecordBy(getWhereConditionBaseOnIdRecord(), new SC1RecordRowMapper());

		if (!results.isEmpty())
		{
			dataModel = (SC1RecordDataModel) results.get(0);
		}
		return dataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}