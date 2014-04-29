package com.misys.equation.common.access;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import javax.naming.NamingException;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.utilities.CRMLimitCheckData;
import com.misys.equation.common.utilities.EqJobWatcher;

/**
 * This class provides methods for processing Equation transactions, enquiries, prompt/validates, queries and tables - a connection
 * to the iSeries is established at instantiation.
 */
public interface EquationStandardSession
{
	/**
	 * Return the AS400 connection
	 */
	public abstract Connection getConnection();

	/**
	 * This method will initialise the transaction.
	 * <ul>
	 * <li>The <code>EQTXM</code> stored procedure will be called to initialise the transaction in RPG back-end</li>
	 * </ul>
	 * 
	 * @return true if the transaction was initialised successfully.<br>
	 *         Otherwise false will be returned or an <code>EQException</code> will be thrown.
	 * @throws EQException
	 *             if there is an <code>EQException</code> will be thrown.
	 * @equation.external
	 */
	public abstract boolean startEquationTransaction() throws EQException;

	/**
	 * This method will end the transaction and clear the transaction list.
	 * <ul>
	 * <li>The <code>EQTXM</code> stored procedure will be called to end the initialise the transaction in RPG back-end</li>
	 * </ul>
	 * 
	 * @return true if the transaction was ended successfully.<br>
	 *         Otherwise false will be returned or an <code>EQException</code> will be thrown.
	 * @throws EQException
	 *             if there is an <code>EQException</code> will be thrown.
	 * @equation.external
	 */
	public abstract boolean endEquationTransaction() throws EQException;

	/**
	 * This method will roll-back all associated transactions.
	 * 
	 * @return true if the transactions were roll-backed successfully.<br>
	 *         Otherwise false will be returned or an <code>EQException</code> will be thrown.
	 * @throws EQException
	 *             if there is an <code>EQException</code> will be thrown.
	 * @equation.external
	 */
	public abstract boolean rollbackTransactions() throws EQException;

	/**
	 * This method will commit the transaction/s associated to the current session.<br>
	 * It will be done only if the transaction/s was in initialised using <code>startEquationTransaction()</code>.
	 * <ul>
	 * <li>The session's database connection will be committed</li>
	 * <li>The <code>EQTXM</code> store procedure will called to fire the transaction/s in the RPG back-end</li>
	 * </ul>
	 * 
	 * @return true if the session was committed, in other case false will be returned.
	 * 
	 * @throws EQException
	 *             if the transaction level set in the properties is different to EQ transaction a <code>EQException</code> will be
	 *             thrown.
	 * @equation.external
	 */
	public abstract boolean commitTransactions() throws EQException;

	/**
	 * This method will commit the transaction associated to the current session.<br>
	 * It will be done only if the transaction was in initialised using <code>startEquationTransaction()</code>.
	 * <ul>
	 * <li>The session's database connection will be committed</li>
	 * <li>The <code>EQTXM</code> store procedure will called to fire the transaction in the RPG back-end</li>
	 * </ul>
	 * 
	 * @return true if the session was committed, in other case false will be returned.
	 * 
	 * @throws EQException
	 *             if the transaction level set in the properties is different to EQ transaction a <code>EQException</code> will be
	 *             thrown.
	 * @equation.external
	 */
	public abstract boolean commitTransaction() throws EQException;

	/**
	 * Add an <code>EquationStandardTransaction</code> to the session
	 * 
	 * @param transaction
	 *            - the transaction to add
	 * @equation.external
	 */
	public abstract EquationStandardTransaction addEquationTransaction(EquationStandardTransaction transaction);

	/**
	 * Remove an <code>EquationStandardTransaction</code> from the session
	 * 
	 * @param transaction
	 *            - the transaction to remove
	 * @return true if this transaction was removed
	 */
	public abstract boolean removeEquationTransaction(EquationStandardTransaction transaction);

	/**
	 * Apply a specific transaction in validate mode
	 * 
	 * @param transaction
	 *            - the transaction to apply
	 * @equation.external
	 */
	public abstract EquationStandardTransaction validateEquationTransaction(EquationStandardTransaction transaction)
					throws EQException;

	/**
	 * Apply a specific transaction in retrieve mode
	 * 
	 * @param transaction
	 *            - the transaction to apply
	 * @equation.external
	 */
	public abstract EquationStandardTransaction retrieveEquationTransaction(EquationStandardTransaction transaction)
					throws EQException;

	/**
	 * Apply a specific transaction
	 * 
	 * @param transaction
	 *            - the transaction to apply
	 * @equation.external
	 */
	public abstract EquationStandardTransaction applyEquationTransaction(EquationStandardTransaction transaction)
					throws EQException;

	/**
	 * Apply the transactions currently assigned to the session
	 * 
	 * @return true if successful otherwise false
	 * @throws EQException
	 * @equation.external
	 */
	public abstract boolean applyTransactions() throws EQException;

	/**
	 * Call the UTW52R to retrieve a data area
	 * 
	 * @param name
	 *            - the data area name
	 * 
	 * @return the content of the data area
	 */
	public abstract byte[] callUTW52R(String name) throws EQException;

	/**
	 * Validate the password
	 * 
	 * @param userName
	 *            - the user name
	 * @param password
	 *            - the user password
	 * @param tranType
	 *            - the transaction type
	 * 
	 * @return an error message if user name/password and any validation are not valid
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public abstract String supervisorPassword(String userName, String password, String tranType) throws EQException;

	/**
	 * Get user token
	 * 
	 * @param user
	 *            id - the user id
	 * 
	 * @return the user token
	 * 
	 * @throws EQException
	 */
	public abstract byte[] getUserToken(String userId, String password, String tokenType) throws EQException;

	/**
	 * Update the current GH record
	 * 
	 * @param mode
	 *            - Add or Maintain or Delete
	 * @param optionId
	 *            - option Id
	 * @param program
	 *            - program name
	 * @param cc
	 *            - cc flag?
	 * @param app
	 *            - application
	 * @param tcpIp
	 *            - tcp ip address
	 * 
	 * @return the error message (if any)
	 * 
	 * @throws EQException
	 */
	public abstract EQMessage updateGH(String mode, String optionId, String program, String cc, String app, String tcpIp)
					throws EQException;

	/**
	 * Retrieve the user's DAJOBCTLE
	 * 
	 * @return the DAJOBCTLE data structure data
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public abstract EquationDataStructureData getDajobctle() throws EQException;

	/**
	 * Retrieve the user's DAWSIDCTXT
	 * 
	 * @return the DAWSIDCTXT data structure data
	 * 
	 * @throws EQException
	 */
	public abstract EquationDataStructureData getDawsidctxt() throws EQException;

	/**
	 * Retrieve the user's DAWSIDCTX2
	 * 
	 * @return the DAWSIDCTX2 data structure data
	 * 
	 * @throws EQException
	 */
	public abstract EquationDataStructureData getDawsidctx2() throws EQException;

	/**
	 * Retrieve the job's SVJOB4
	 * 
	 * @return the SVJOB4 data structure data
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public EquationDataStructureData getSVJOB4EX() throws EQException;

	/**
	 * Retrieve the object path
	 * 
	 * @param objName
	 *            - the object name
	 * @param objType
	 *            - the type of object
	 * @param objLib
	 *            - the object library
	 * @param type
	 *            - full path or library name
	 * 
	 * @return the returned object library or the object path
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public abstract String getObjectPath(String objName, String objType, String objLib, int type) throws EQException;

	/**
	 * Check whether an iSeries object exists
	 * 
	 * @param objectName
	 *            - the object name
	 * @param objectType
	 *            - the type of object
	 * @param library
	 *            - the library (can be "*LIBL")
	 * 
	 * @return a boolean indicating whether the object exists
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public abstract boolean doesObjectExist(String objectName, String objectType, String library) throws EQException;

	/**
	 * Determine if user has inter-branch authority
	 * 
	 * @param userId
	 *            - the user Id
	 * @param defBranchNo
	 *            - the user's default branch number
	 * 
	 * @return true if user has inter-branch authority
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public abstract boolean isUserInterBranchAllowed(String userId, String defBranchNo) throws EQException;

	/**
	 * Perform CRM limit check
	 * 
	 * @param dshh03
	 *            - the DSHH03 data
	 * 
	 * @return the CRM limit check data
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public abstract CRMLimitCheckData checkCRMLimit(EquationDataStructureData dshh03) throws EQException;

	/**
	 * Call an Equation standard Enquiry
	 * 
	 * @param enquiry
	 *            - the Equation standard enquiry
	 * 
	 * @throws EQException
	 * @equation.external
	 * 
	 */
	public abstract EquationStandardEnquiry executeEnquiry(EquationStandardEnquiry enquiry) throws EQException;

	/**
	 * Call an Equation Standard List Enquiry until it retrieves all the details
	 * 
	 * @param enquiry
	 *            - the Equation standard list enquiry
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public abstract EquationStandardListEnquiry executeListEnquiry(EquationStandardListEnquiry enquiry) throws EQException;

	/**
	 * Call an Equation Standard List Enquiry until it retrieves all the details or limit has been reached
	 * 
	 * @param enquiry
	 *            - the Equation standard list enquiry
	 * @param limitBytes
	 *            - the maximum bytes to be retrieved
	 * 
	 * @throws EQException
	 * @equation.external
	 * 
	 */
	public abstract EquationStandardListEnquiry executeListEnquiry(EquationStandardListEnquiry enquiry, long limitBytes)
					throws EQException;

	/**
	 * Call an Equation Standard List Enquiry once
	 * 
	 * @param enquiry
	 *            - the Equation standard list enquiry
	 * 
	 * @throws EQException
	 * @equation.external
	 * 
	 */
	public abstract EquationStandardListEnquiry executeIncrementalListEnquiry(EquationStandardListEnquiry enquiry)
					throws EQException;

	/**
	 * Perform validation
	 * 
	 * @param validation
	 *            - the EquationStandardValidation
	 * @equation.external
	 */
	public abstract EquationStandardValidation executeValidate(EquationStandardValidation validation) throws EQException;

	/**
	 * Retrieve list validation
	 * 
	 * @param validation
	 *            - the EquationStandardValidation
	 * 
	 * @throws EQException
	 * @equation.external
	 * 
	 */
	public abstract EquationStandardListValidation executeListValidate(EquationUser eqUser,
					EquationStandardListValidation validation) throws EQException;

	/**
	 * Retrieve list validation
	 * 
	 * @param validation
	 *            - the EquationStandardValidation
	 * 
	 * @throws EQException
	 * @equation.external
	 * 
	 */
	public abstract EquationStandardListValidation executeNextListValidate(EquationUser eqUser,
					EquationStandardListValidation validation) throws EQException;

	/**
	 * Perform validation
	 * 
	 * @param validation
	 *            - the EquationStandardValidation
	 * 
	 * @throws EQException
	 */
	public abstract EquationStandardMultipleValidation executeMultipleValidate(EquationStandardMultipleValidation validation)
					throws EQException;

	/**
	 * Validate an Equation table
	 * 
	 * @param table
	 *            - the Equation table
	 */
	public abstract EquationStandardTable validateEquationTable(EquationStandardTable table);

	/**
	 * Retrieve an Equation table
	 * 
	 * @param table
	 *            - the Equation table
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public abstract EquationStandardTable retrieveEquationTable(EquationStandardTable table) throws EQException;

	/**
	 * Validate details from a PV crated via the PV editor
	 * 
	 * @param validation
	 *            - the Equation PV Editor validation
	 * 
	 * @throws EQException
	 */
	public abstract EquationPVEditorValidation executeEquationPVEditorValidation(EquationPVEditorValidation validation)
					throws EQException;

	/**
	 * Performs retrieval processing for a list database table until it retrieves all the details
	 * 
	 * @param table
	 *            the EquationStandardListTable to retrieve data for
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public abstract void retrieveEquationListTable(EquationStandardListTable table) throws EQException;

	/**
	 * Performs retrieval processing for a list database table until it retrieves all the details or limit has been reached
	 * 
	 * @param table
	 *            the EquationStandardListTable to retrieve data for
	 * @param limitBytes
	 *            - the maximum bytes to be retrieved
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public abstract void retrieveEquationListTable(EquationStandardListTable table, long limitBytes) throws EQException;

	/**
	 * Performs retrieval processing for a list database table until it retrieves all the details
	 * 
	 * @param query
	 *            - the EquationStandardQueryList
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public abstract void retrieveEquationQueryList(EquationStandardQueryList query) throws EQException;

	/**
	 * Performs retrieval processing for a list query until it retrieves all the details or limit has been reached
	 * 
	 * @param query
	 *            - the EquationStandardQueryList
	 * @param limitBytes
	 *            - the maximum bytes to be retrieved
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public abstract void retrieveEquationQueryList(EquationStandardQueryList query, long limitBytes) throws EQException;

	/**
	 * Performs retrieval processing for a query
	 * 
	 * @param query
	 *            - the EquationStandardQueryList
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public abstract void retrieveEquationQuery(EquationStandardQuery query) throws EQException;

	/**
	 * Add/maintain/delete the table
	 * 
	 * @param table
	 *            - the Equation standard table
	 * 
	 * @return the Equation standard table
	 * @equation.external
	 */
	public abstract EquationStandardTable applyEquationTable(EquationStandardTable table) throws EQException;

	/**
	 * Execute report
	 * 
	 * @param report
	 *            - the Equation standard report
	 */
	public abstract EquationStandardReport executeReport(EquationStandardReport report);

	/**
	 * Convert a list of messages in the format of DSEPMS into list of EQMessage
	 * 
	 * @param messageText
	 *            - the list of messages in DSEPMS format
	 * 
	 * @return the list of EQMessage
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public abstract List<EQMessage> getMessages(String messageText) throws EQException;

	/**
	 * Convert a message in the format of DSEPMS into EQMessage
	 * 
	 * @param messageText
	 *            - the message in DSEPMS format
	 * 
	 * @return the message in EQMessage format
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public abstract EQMessage getMessage(String messageText) throws EQException;

	/**
	 * This method will return an <code>LinkedHashMap</code> that contains all KSM messages.
	 * 
	 * @return array that contains all KSM messages.
	 * @equation.external
	 */
	public abstract Map<String, String> getKsmmsgs() throws EQException;

	/**
	 * This method will return an <code>LinkedHashMap</code> that contains all KSM messages with the specified severity
	 * 
	 * @param severity
	 *            - the severity
	 * @param compartor
	 *            - comparison operator <=, ==, >=
	 * 
	 * @return array that contains all KSM messages with the specified severity
	 * @equation.external
	 */
	public abstract Map<String, String> getKsmmsgs(int severity, String comparator) throws EQException;

	/**
	 * Return the data structure for a file
	 * 
	 * @param formatName
	 *            - the file name
	 * @return the data structure of the file
	 * 
	 * @throws EQException
	 */
	public abstract EquationDataStructure getEquationDataStructure(String formatName) throws EQException;

	/**
	 * Remove from data structure cache
	 * 
	 * @param formatName
	 *            - the format name to be removed
	 * 
	 * @return the deleted data structure
	 */
	public abstract EquationDataStructure removeEquationDataStructure(String formatName);

	/**
	 * @return the branch mnemonic for the session defaulted from the user or the session pool depending on the constructor, this
	 *         value can be over-riden using the setter method
	 */
	public abstract String getBranchId();

	/**
	 * @return the system name
	 */
	public abstract String getSystemId();

	/**
	 * @return the 3 character unit identifier
	 */
	public abstract String getUnitId();

	/**
	 * @return the user identifier
	 */
	public abstract String getUserId();

	/**
	 * Set the user id. This method should only be used when using pooled connections.
	 * 
	 * @param userId
	 *            - the user id
	 */
	public void setUserId(String userId);

	/**
	 * @return the Equation user identifier
	 */
	public abstract String getEquationUserId();

	/**
	 * @param branchId
	 *            to be used when calling the APIs
	 * @equation.external
	 */
	public abstract void setBranchId(String branchId);

	/**
	 * This method will commit the transaction associated to the current session.<br>
	 * <ul>
	 * <li>The session's database connection will be committed</li>
	 */
	public abstract void connectionCommit() throws SQLException, EQException;

	/**
	 * This method will rollback the transaction associated to the current session.<br>
	 * <ul>
	 * <li>The session's database connection will be rolled back.</li>
	 * 
	 * @throws NamingException
	 */
	public abstract void connectionRollback() throws SQLException, EQException, IllegalStateException, NamingException;

	/**
	 * Reset the connection for next XA transaction.
	 * <P>
	 * Reset of the connection should only be necessary for XA connections.
	 * <P>
	 * 
	 * @throws SQLException
	 *             if a SQL error occurs.
	 * @throws EQException
	 *             if called in autoCommit mode, or if startTrans has not been called previously.
	 */
	public abstract void reset() throws SQLException, EQException;

	/**
	 * Return the ccsid of the session
	 * 
	 * @return the ccsid of the session
	 */
	public abstract int getCcsid();

	/**
	 * @return the unit
	 * @equation.external
	 */
	public abstract EquationUnit getUnit();

	/**
	 * @return the <code>EquationConnectionWrapper</code> object
	 * @equation.external
	 */
	public abstract EquationConnectionWrapper getConnectionWrapper();

	/**
	 * This method will close the current connection if that one was not closed before.<br>
	 * When connection is closed it means that this one will be returned to the pool.
	 * 
	 * @equation.external
	 */
	public abstract void closeConnection();

	/**
	 * Return the String representation
	 * 
	 * @return the String representation
	 */
	public abstract String toString();

	/**
	 * Return an EqJobWatcher related to the Job used for processing SQL statements
	 * 
	 * @return an EqJobWatcher
	 */
	public abstract EqJobWatcher getConnectionJobWatcher() throws EQException;

	/**
	 * Execute lightweight command to ensure connection is valid
	 * 
	 * @return true if system ok or false if an exception was thrown
	 */
	public abstract boolean isConnectionValid();

	/**
	 * @return the session identifier
	 */
	public abstract String getSessionIdentifier();

	/**
	 * Set the session identifier
	 * 
	 * @param sessionIdentifier
	 */
	public abstract void setSessionIdentifier(String sessionIdentifier);

	/**
	 * @return the absolute path to the KSMMSGF
	 */
	public abstract String getKsmmsgfPath();

	/**
	 * Call the UTR00R to determine whether enhancement is installed
	 * 
	 * @param enhancement
	 *            - the enhancement to be check
	 * 
	 * @return true if enhancement is installed
	 * 
	 * @throws EQException
	 */
	public abstract boolean callUTR00R(String enhancement) throws EQException;

	/**
	 * Call UTW01R to generate the language property of the unit
	 * 
	 * @throws EQException
	 */
	public abstract void callUTW01R() throws EQException;

	/**
	 * Call the UTW19R to retrieve the PV meta data
	 * 
	 * @param pvName
	 *            - PV program name
	 * 
	 * @return the PV meta data
	 */
	public abstract EquationPVMetaData callUTW19R(String pvName) throws EQException;

	/**
	 * Close the Statement instance variables
	 */
	public abstract void closeStatements();
	/**
	 * Check the WEYPF for specific column
	 */
	public abstract boolean isWEYPFBdtaInstalled() throws SQLException;
	/**
	 * Return the session lock
	 * 
	 * @return the session lock
	 */
	public Lock getSessionLock();
	/**
	 * Return the outstanding break messages
	 * 
	 * @return the outstanding break messages
	 */
	public ArrayList<String> getBreakMessages();
	/**
	 * Set the outstanding break messages
	 * 
	 * @param breakMessages
	 */
	public void setBreakMessages(ArrayList<String> breakMessages);
}
