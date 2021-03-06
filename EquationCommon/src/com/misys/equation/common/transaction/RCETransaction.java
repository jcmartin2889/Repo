package com.misys.equation.common.transaction;

import java.util.List;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.conversion.InputParameterConversion;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.transaction.rce.RCETransactionDetails;
import com.misys.equation.common.transaction.rce.RCETransactionInstruction;
import com.misys.equation.common.transaction.rce.RCETransactionKeys;
import com.misys.equation.common.transaction.rce.RCETransactionPrincipalIncrease;
import com.misys.equation.common.transaction.rce.RCETransactionRateChange;

/**
 * To use this class - <br>
 * 
 * Set the keys <br>
 * setupKey(keys) - setup and retrieve loan (or default if loan reference is blank) <br>
 * 
 * Set the details <br>
 * setupDetail(details) - for non-existing loan <br>
 * setupDetail() - for existing loan <br>
 * 
 * Retrieve schedule
 * 
 */
public class RCETransaction
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RCETransaction.java 17493 2013-10-28 09:55:10Z lima12 $";

	/** RCE Extract program */
	public static final String RCE_EXTRACT_PROGRAM = "I31DER";

	/** Enquiry mode */
	public static final String RCE_MODE_API = "0";
	public static final String RCE_MODE_KEY = "1";
	public static final String RCE_MODE_DETAIL = "2";
	public static final String RCE_MODE_GENERATE = "3";
	public static final String RCE_MODE_RETRIEVE = "4";
	public static final String RCE_MODE_ADD_INSTRUCTION = "5";
	public static final String RCE_MODE_MAINTAIN_INSTRUCTION = "6";
	public static final String RCE_MODE_RATE_CHANGES = "7";
	public static final String RCE_MODE_PRINCIPAL_INCREASE = "8";
	public static final String RCE_MODE_ADD_MAINTAIN_INSTRUCTION = "G";

	/** RCE transaction */
	private EquationStandardListEnquiry rce;

	/** Equation session */
	private EquationStandardSession session;

	/** Suppress retrieve */
	private String apiMode = "Y";
	private String suppressRetrieve = "Y";
	private String suppressEFC = "Y";

	/**
	 * Constructor
	 * 
	 * @param session
	 *            - the Equation session
	 * 
	 * @throws EQException
	 */
	public RCETransaction(EquationStandardSession session) throws EQException
	{
		this.session = session;
		this.rce = new EquationStandardListEnquiry(RCE_EXTRACT_PROGRAM, session);
	}

	/**
	 * Return the HZ API flag
	 * 
	 * @return the HZ API flag
	 */
	public String getApiMode()
	{
		return apiMode;
	}

	/**
	 * Set the HZ API flag
	 * 
	 * @param apiMode
	 *            - the HZ API flag
	 */
	public void setApiMode(String apiMode)
	{
		this.apiMode = apiMode;
	}

	/**
	 * Return the HZ suppress retrieve flag
	 * 
	 * @return the HZ suppress retrieve flag
	 */
	public String getSuppressRetrieve()
	{
		return suppressRetrieve;
	}

	/**
	 * Set the HZ suppress retrieve flag
	 * 
	 * @param suppressRetrieve
	 *            - the HZ suppress retrieve flag
	 */
	public void setSuppressRetrieve(String suppressRetrieve)
	{
		this.suppressRetrieve = suppressRetrieve;
	}

	/**
	 * Get the HZ suppress EFC flag
	 * 
	 * @return the HZ suppress EFC flag
	 */
	public String getSuppressEFC()
	{
		return suppressEFC;
	}

	/**
	 * Set the HZ suppress EFC flag
	 * 
	 * @param suppressEFC
	 *            - the HZ suppress EFC flag
	 */
	public void setSuppressEFC(String suppressEFC)
	{
		this.suppressEFC = suppressEFC;
	}

	/**
	 * Initial value of RCE
	 */
	protected void defaultValues()
	{
		rce.setFieldValue("HZAPI", apiMode);
		rce.setFieldValue("HZSRTV", suppressRetrieve);
		rce.setFieldValue("HZSEFC", suppressEFC);
	}

	/**
	 * Determine if transaction has been executed without error
	 * 
	 * @return true if transaction has been executed without error
	 */
	public boolean isValid()
	{
		return rce.getValid();
	}

	/**
	 * Return the list of errors
	 * 
	 * @return the list of errors
	 */
	public List<EQMessage> getErrors()
	{
		return rce.getErrorList();
	}

	/**
	 * Return the Equation Standard List Enquiry
	 * 
	 * @return the Equation Standard List Enquiry
	 */
	public EquationStandardListEnquiry getRce()
	{
		return rce;
	}

	/**
	 * Call the enquiry
	 * 
	 * @throws EQException
	 */
	public boolean callEnquiry(String mode) throws EQException
	{
		rce.setFieldValue("HZMODE", mode);
		session.executeIncrementalListEnquiry(rce);
		return rce.getValid();
	}

	/**
	 * Set the HZ field
	 * 
	 * @param fieldName
	 *            - the HZ field name
	 * @param fieldValue
	 *            - the field value
	 */
	public void setField(String fieldName, String fieldValue)
	{
		rce.setFieldValue(fieldName, fieldValue);
	}

	/**
	 * Return the HZ field value
	 * 
	 * @param fieldName
	 *            - the HZ field name
	 * 
	 * @return the field value
	 */
	public String getField(String fieldName)
	{
		return rce.getFieldValue(fieldName);
	}

	/**
	 * Return the HZ field value in the schedules
	 * 
	 * @param fieldName
	 *            - the HZ field name
	 * @param row
	 *            - the row index (starting index is 1)
	 * 
	 * @return the field value
	 */
	public String getListField(String fieldName, int row) throws EQException
	{
		if (rce.isComplete() && rce.getRecordCount() >= row)
		{
			return rce.getListField(fieldName, row - 1);
		}
		else
		{
			return null;
		}
	}

	/**
	 * Return the number of schedule lines
	 * 
	 * @return the number of schedule lines
	 */
	public int size()
	{
		return rce.getRecordCount();
	}

	/**
	 * Key screen
	 * 
	 * @param keys
	 *            - the key screen
	 * @param callRCE
	 *            - true if also invoke RCE RPG
	 * 
	 * @throws EQException
	 */
	public void setupKey(RCETransactionKeys keys, boolean callRCE) throws EQException
	{
		setField("HZDLP", keys.getLoanType());
		setField("HZDLR", keys.getLoanReference());
		setField("HZBRNM", keys.getLoanBranch());
		setField("HZCUS", keys.getCustomerMnemonic());
		setField("HZCLC", keys.getCustomerLocation());

		if (callRCE)
		{
			setupKey();
		}
	}

	/**
	 * Detail screen
	 * 
	 * @param details
	 *            - the detail screen
	 * @param callRCE
	 *            - true if also invoke RCE RPG
	 * 
	 * @throws EQException
	 */
	public void setupDetail(RCETransactionDetails details, boolean callRCE) throws EQException
	{
		setField("HZCCY", details.getCurrency());
		setField("HZDLA", InputParameterConversion.convertSignedAmount(details.getDealAmount()));
		setField("HZDLA2", getField("HZDLA"));
		setField("HZSDTE", details.getStartDate());
		setField("HZIDB", details.getIdb());
		setField("HZRAT", InputParameterConversion.convertSignedRate(details.getActualRate()));
		setField("HZBRR", details.getBaseRate());
		setField("HZDRR", details.getDifferentialRate());
		setField("HZRTM", InputParameterConversion.convertSignedRate(details.getMarginRate()));
		setField("HZRTM2", getField("HZRTM"));
		setField("HZIFRQ", details.getInterestFrequency());
		setField("HZNCD", details.getFirstInterestDate());
		setField("HZFIDT", getField("HZNCD"));
		setField("HZRFRQ", details.getRepaymentFrequency());
		setField("HZNPY", details.getNumberOfPayments());
		setField("HZNDT", details.getFirstRepaymentDate());
		setField("HZMDT", details.getMaturityDate());
		setField("HZLTRM", details.getLoanTerm());
		setField("HZAMTR", InputParameterConversion.convertSignedAmount(details.getRepaymentAmount()));
		setField("HZFAM", InputParameterConversion.convertSignedAmount(details.getFirstAmount()));
		setField("HZDIF", details.getFirstAmountCanDiffer());
		setField("HZCCD1", details.getChargeCode1());
		setField("HZCAM1", InputParameterConversion.convertSignedAmount(details.getChargeAmount1()));
		setField("HZCCD2", details.getChargeCode2());
		setField("HZCAM2", InputParameterConversion.convertSignedAmount(details.getChargeAmount2()));
		setField("HZCCD3", details.getChargeCode3());
		setField("HZCAM3", InputParameterConversion.convertSignedAmount(details.getChargeAmount3()));
		setField("HZAD1", details.getAddress1());
		setField("HZAD2", details.getAddress2());
		setField("HZAD3", details.getAddress3());
		setField("HZAD4", details.getAddress4());
		setField("HZAD5", details.getAddress5());
		setField("HZEVNT", details.getChargeEvent());
		setField("HZCHAN", details.getChannel());
		setField("HZSCHC", details.getScheduleRecalculate());
		setField("HZPRV", InputParameterConversion.convertSignedAmount(details.getProjectValue()));
		setField("HZADVP", InputParameterConversion.convertSignedAmount(details.getContractorAdvancePayment()));
		setField("HZEOC", details.getConstructionEndDate());
		setField("HZFRT", InputParameterConversion.convertSignedRate(details.getFlatRate()));
		setField("HZINA", InputParameterConversion.convertSignedAmount(details.getInterestAmount()));
		setField("HZDIA", InputParameterConversion.convertSignedAmount(details.getDiscountAmount()));
		setField("HZAAP", details.getAdditionalProfit());
		setField("HZARP", details.getReductionProfit());
		setField("HZCAMT", InputParameterConversion.convertSignedAmount(details.getConstantAmount()));

		if (callRCE)
		{
			setupDetail();
		}
	}
	/**
	 * Principal increase
	 * 
	 * @param details
	 *            - the principal increase details
	 * @param callRCE
	 *            - true if also invoke RCE RPG
	 * 
	 * @throws EQException
	 */
	public void principalIncrease(RCETransactionPrincipalIncrease details, boolean callRCE) throws EQException
	{
		setField("HZ4DTE", String.valueOf(details.getPrincipalDate()));
		setField("HZ4AMT", InputParameterConversion.convertSignedAmount(details.getPrincipalAmount()));
		setField("HZ4DEL", details.isDelete() ? "Y" : "N");

		if (callRCE)
		{
			principalIncrease();
		}
	}

	/**
	 * Rate changes
	 * 
	 * @param details
	 *            - the rate changes details
	 * @param callRCE
	 *            - true if also invoke RCE RPG
	 * 
	 * @throws EQException
	 */
	public void rateChange(RCETransactionRateChange details, boolean callRCE) throws EQException
	{
		setField("HZ4DTE", String.valueOf(details.getChangeDate()));
		setField("HZ4BRR", details.getBaseRate());
		setField("HZ4DRR", details.getDifferentialRate());
		setField("HZ4RAT", InputParameterConversion.convertSignedRate(details.getActualRate()));
		setField("HZ4RTM", InputParameterConversion.convertSignedRate(details.getMarginRate()));
		setField("HZ4DEL", details.isDelete() ? "Y" : "N");

		if (callRCE)
		{
			rateChange();
		}
	}

	/**
	 * Add instruction
	 * 
	 * @param details
	 *            - the instruction details
	 * @param callRCE
	 *            - true if also invoke RCE RPG
	 * 
	 * @throws EQException
	 */
	public void addInstruction(RCETransactionInstruction details, boolean callRCE) throws EQException
	{
		setField("HZ4DTE", String.valueOf(details.getInstructionDate()));
		setField("HZ4MDT", String.valueOf(details.getMaturityDate()));
		setField("HZ4NPY", details.getNumberOfPayments());
		setField("HZ4AMT", InputParameterConversion.convertSignedAmount(details.getRepaymentAmount()));
		setField("HZ4FRQ", details.getRepaymentFrequency());
		setField("HZ4DIF", details.getFirstAmountCanDiffer());
		setField("HZ4FAM", InputParameterConversion.convertSignedAmount(details.getFirstAmount()));
		setField("HZ4NDT", String.valueOf(details.getFirstRepaymentDate()));
		setField("HZ4DEL", details.isDelete() ? "Y" : "N");

		if (callRCE)
		{
			addInstruction();
		}
	}

	/**
	 * Maintain instruction
	 * 
	 * @param details
	 *            - the instruction details
	 * @param callRCE
	 *            - true if also invoke RCE RPG
	 * 
	 * @throws EQException
	 */
	public void maintainInstruction(RCETransactionInstruction details, boolean callRCE) throws EQException
	{
		addInstruction(details, false);

		if (callRCE)
		{
			maintainInstruction();
		}
	}

	/**
	 * Add/maintain instruction
	 * 
	 * @param details
	 *            - the instruction details
	 * @param callRCE
	 *            - true if also invoke RCE RPG
	 * 
	 * @throws EQException
	 */
	public void addMaintainInstruction(RCETransactionInstruction details, boolean callRCE) throws EQException
	{
		addInstruction(details, false);

		if (callRCE)
		{
			addMaintainInstruction();
		}
	}

	/**
	 * Delete principal increase
	 * 
	 * @param details
	 *            - the principal increase details
	 * @param callRCE
	 *            - true if also invoke RCE RPG
	 * 
	 * @throws EQException
	 */
	public void deletePrincipalIncrease(RCETransactionPrincipalIncrease details, boolean callRCE) throws EQException
	{
		details.setDelete(true);
		principalIncrease(details, callRCE);
	}

	/**
	 * Delete rate changes
	 * 
	 * @param details
	 *            - the rate changes details
	 * @param callRCE
	 *            - true if also invoke RCE RPG
	 * 
	 * @throws EQException
	 */
	public void deleteRateChange(RCETransactionRateChange details, boolean callRCE) throws EQException
	{
		details.setDelete(true);
		rateChange(details, callRCE);
	}

	/**
	 * Delete instruction
	 * 
	 * @param details
	 *            - the instruction details
	 * @param callRCE
	 *            - true if also invoke RCE RPG
	 * 
	 * @throws EQException
	 */
	public void deleteInstruction(RCETransactionInstruction details, boolean callRCE) throws EQException
	{
		details.setDelete(true);
		maintainInstruction(details, callRCE);
	}

	/**
	 * Call RCE in mode 0
	 * 
	 * @throws EQException
	 */
	public void setup0() throws EQException
	{
		defaultValues();
		callEnquiry(RCE_MODE_API);
	}

	/**
	 * Call RCE in key mode
	 * 
	 * @throws EQException
	 */
	public void setupKey() throws EQException
	{
		defaultValues();
		callEnquiry(RCE_MODE_KEY);
	}

	/**
	 * Call RCE in detail mode
	 * 
	 * @throws EQException
	 */
	public void setupDetail() throws EQException
	{
		defaultValues();
		callEnquiry(RCE_MODE_DETAIL);
	}

	/**
	 * Generate the schedule
	 * 
	 * @throws EQException
	 */
	public void generateSchedule() throws EQException
	{
		defaultValues();
		callEnquiry(RCE_MODE_GENERATE);
	}

	/**
	 * Add instruction
	 * 
	 * @throws EQException
	 */
	public void addInstruction() throws EQException
	{
		defaultValues();
		callEnquiry(RCE_MODE_ADD_INSTRUCTION);
	}

	/**
	 * Maintain instruction
	 * 
	 * @throws EQException
	 */
	public void maintainInstruction() throws EQException
	{
		defaultValues();
		callEnquiry(RCE_MODE_MAINTAIN_INSTRUCTION);
	}

	/**
	 * Add/maintain instruction
	 * 
	 * @throws EQException
	 */
	public void addMaintainInstruction() throws EQException
	{
		defaultValues();
		callEnquiry(RCE_MODE_ADD_MAINTAIN_INSTRUCTION);
	}

	/**
	 * Rate changes
	 * 
	 * @throws EQException
	 */
	public void rateChange() throws EQException
	{
		defaultValues();
		callEnquiry(RCE_MODE_RATE_CHANGES);
	}

	/**
	 * Principal increase
	 * 
	 * @throws EQException
	 */
	public void principalIncrease() throws EQException
	{
		defaultValues();
		callEnquiry(RCE_MODE_PRINCIPAL_INCREASE);
	}

	/**
	 * Retrieve the schedule
	 * 
	 * @throws EQException
	 */
	public void retrieveSchedules() throws EQException
	{
		while (!rce.isComplete())
		{
			defaultValues();
			callEnquiry(RCE_MODE_RETRIEVE);
		}
	}

	/**
	 * Return the RCE transaction
	 * 
	 * @return the RCE transaction
	 */
	@Override
	public String toString()
	{
		return rce.toString();
	}

}
