package com.misys.equation.common.globalprocessing.mbeans;

import java.util.List;

import com.misys.equation.common.dao.beans.CLCRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;

/**
 * Contains service methods for customer linkage management.
 * 
 * @author berzosa
 */
public interface LinkedCustomerSynchCodesService
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LinkedCustomerSynchCodesService.java 9315 2010-09-27 08:50:51Z berzosa $";

	/**
	 * Retrieves the customer supplied Linked Customer Synchronisation Code records; given a sequence ID, it returns the record
	 * matching the provided code
	 * 
	 * @param searchString
	 *            Linked Customer Synchronisation Code ID
	 * @param sessionID
	 *            session ID
	 * @return The record corresponding to the sequence ID or all records when searchString is empty
	 * @throws EQException
	 *             If the transaction must be rolled back, then simply throw this exception
	 */
	@MBeanOperation("Retrieves the customer supplied Linked Customer Synchronisation Code records; given a sequence ID, it returns the record matching the provided code")
	public List<CLCRecordDataModel> getCLCRecords(@Meta(name = "sessionID", desc = "The Session identifier") String sessionID,
					@Meta(name = "whereString", desc = "The filter for the sql query") String whereString) throws EQException;

	/**
	 * Retrieves the system supplied Linked Customer Synchronisation Code records; given a sequence ID, it returns the record
	 * matching the provided code
	 * 
	 * @param sessionID
	 *            session ID
	 * @return The record corresponding to the sequence ID or all records when searchString is empty
	 * @throws EQException
	 *             If the transaction must be rolled back, then simply throw this exception
	 */
	@MBeanOperation("Retrieves the system supplied Linked Customer Synchronisation Code records; given a sequence ID, it returns the record matching the provided code")
	public List<CLCRecordDataModel> getMisysCLCRecords(@Meta(name = "sessionID", desc = "The Session identifier") String sessionID)
					throws EQException;

	/**
	 * Erase the record for a given sequence ID
	 * 
	 * @param record
	 *            Linked Customer Synchronisation Code record
	 * @param sessionID
	 *            session ID
	 * @throws EQException
	 *             If the transaction must be rolled back, then simply throw this exception
	 */
	@MBeanOperation("Erase the record for a given sequence ID")
	public void eraseCLCRecord(@Meta(name = "sessionID", desc = "The Session identifier") String sessionID,
					@Meta(name = "record", desc = "The record to be purged") CLCRecordDataModel record) throws EQException;

	/**
	 * Update the record for a given the sync code record
	 * 
	 * @param record
	 *            Linked Customer Synchronisation Code record
	 * @param sessionID
	 *            session ID
	 * @throws EQException
	 *             If the transaction must be rolled back, then simply throw this exception
	 */
	@MBeanOperation("Update the record for a given sync code record")
	public void updateCLCRecord(@Meta(name = "sessionID", desc = "The Session identifier") String sessionID,
					@Meta(name = "record", desc = "The record to be updated") CLCRecordDataModel record) throws EQException;

	/**
	 * Insert the record for a given the sync code record
	 * 
	 * @param record
	 *            Linked Customer Synchronisation Code record
	 * @param sessionID
	 *            session ID
	 * @throws EQException
	 *             If the transaction must be rolled back, then simply throw this exception
	 */
	@MBeanOperation("Insert the record for a given sync code record")
	public void insertCLCRecord(@Meta(name = "sessionID", desc = "The Session identifier") String sessionID,
					@Meta(name = "record", desc = "The record to be updated") CLCRecordDataModel record) throws EQException;

	/**
	 * Verifies if record exists the record for a given the sync code record
	 * 
	 * @param sessionID
	 *            session ID
	 * @param record
	 *            Linked Customer Synchronisation Code record to check
	 * @return true if record exists, false otherwise
	 * 
	 * @throws EQException
	 *             If the transaction must be rolled back, then simply throw this exception
	 */
	@MBeanOperation("Checks for the existense of the record for a given sync code record")
	public boolean isExistingCLCRecord(@Meta(name = "sessionID", desc = "The Session identifier") String sessionID,
					@Meta(name = "record", desc = "The record to check") CLCRecordDataModel record) throws EQException;
}
