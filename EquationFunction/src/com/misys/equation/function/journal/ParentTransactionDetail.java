package com.misys.equation.function.journal;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.EqDataType;

public class ParentTransactionDetail
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ParentTransactionDetail.java 12310 2011-11-10 22:22:58Z perkinj1 $";
	// This is the journal header of the parent
	JournalHeader journalHeader;

	// Additional information passed from the parent
	boolean applyDuringExternalInput;
	boolean applyDuringRecovery;

	/**
	 * Construct class given the journal record of the parent transaction
	 * 
	 * @param journalHeader
	 *            - journal header of the parent
	 */
	public ParentTransactionDetail(JournalHeader journalHeader)
	{
		this.journalHeader = journalHeader;
		this.applyDuringExternalInput = journalHeader.getArec().equals(EqDataType.NO);
		this.applyDuringRecovery = journalHeader.getAext().equals(EqDataType.NO);
	}

	/**
	 * Set the journal header
	 * 
	 * @return the journal header
	 */
	public JournalHeader getJournalHeader()
	{
		return journalHeader;
	}

	/**
	 * Get the journal header
	 * 
	 * @param journalHeader
	 *            - the journal header
	 */
	public void setJournalHeader(JournalHeader journalHeader)
	{
		this.journalHeader = journalHeader;
	}

	/**
	 * Determine if this linked function should be applied during external input
	 * 
	 * @return true if this linked function should be applied during external input
	 */
	public boolean isApplyDuringExternalInput()
	{
		return applyDuringExternalInput;
	}

	/**
	 * Set if this linked function should be applied during external input or not
	 * 
	 * @param applyDuringExternalInput
	 *            - true if this linked function should be applied during external input
	 */
	public void setApplyDuringExternalInput(boolean applyDuringExternalInput)
	{
		this.applyDuringExternalInput = applyDuringExternalInput;
	}

	/**
	 * Determine if this linked function should be applied during recovery or not
	 * 
	 * @return true if if this linked function should be applied during recovery
	 */
	public boolean isApplyDuringRecovery()
	{
		return applyDuringRecovery;
	}

	/**
	 * Set if this linked function should be applied during recovery or not
	 * 
	 * @param applyDuringRecovery
	 *            - true if this linked function should be applied during recovery
	 */
	public void setApplyDuringRecovery(boolean applyDuringRecovery)
	{
		this.applyDuringRecovery = applyDuringRecovery;
	}

}