package com.misys.equation.common.internal.eapi.core;

import java.sql.Connection;
import java.sql.SQLException;

/***********************************************************************************************************************************
 * Maintains the database connection and specifies how commitment control will be carried out.
 * <P>
 * This API set uses SQL Stored Procedures over a JDBC Connection to invoke business objects on the Equation host and perform
 * database enquiries and updates.
 * <P>
 * This object handles the java.sql.Connection with the host.
 * <P>
 * Great care should be taken if the application intends to share connections between different users; only one logical transaction
 * at a time should ever be processed on a connection.
 * <P>
 * Note that invocations of Equation on this connection are synchronous.
 * <P>
 * The EQSession object also supports methods for handling commitment control.
 * <p>
 * If a connection is supplied and XA is not used then the EQSession commit method must be used. StartTrans and setAutoCommit
 * methods are not appropriate in this case.
 * <P>
 * If a connection is not supplied, by default commitment control is handled automatically. However, if the application wishes to
 * handle commit and rollback operations, it must call setAutoCommit with false, and then use the startTrans, commit and rollback
 * methods appropriately.
 * <P>
 * See the samples for usage.
 * <P>
 * 
 * @see com.misys.equation.ebs.samples.EQTransactionSample
 * @see com.misys.equation.ebs.samples.EQTransactionManagementSample
 * @author Misys International Banking Systems Ltd.
 */
public interface EQSession
{
	/*******************************************************************************************************************************
	 * Commitment control - commit transaction.
	 * <P>
	 * This will make permanent (commit) all updates from the commit start boundary to the point where the commit command is issued
	 * according to the transaction isolation specified in the EQSessionProperties object.
	 * <P>
	 * All database locks currently held by Equation (via the Connection) are released.
	 * <P>
	 * Note: If all locks currently help by the entire Connection need to be released or an XA connections is used, execute a
	 * commit() on the SQL Connection held inside the EQSession.
	 * <P>
	 * This method should be used only when a connection has been supplied or autoCommit has been disabled. startTrans must be
	 * called before this method if autoCommit has been disabled.
	 * <P>
	 * 
	 * @throws SQLException
	 *             if a SQL error occurs.
	 * @throws EQException
	 *             if called in autoCommit mode, or if startTrans has not been called previously.
	 */
	public abstract void commit() throws SQLException, EQException;
	/**
	 * Creates an EQObject for a given identifier.
	 * <P>
	 * The identifier for an enquiry or transaction is the menu option for the function (e.g. "ASI" Add Sundry Item). This is also
	 * the identifier used in the user guide.
	 * <P>
	 * The identifier for a prompt is the identifier found in the Global Modules Manual less the 'R' characters (e.g. "GW76"
	 * Accounts Prompt).
	 * <P>
	 * For a complete list of possible objects use the GA10 Prompt.
	 * <P>
	 * This EQObject should be cast to an EQTransaction, EQEnquiry, or EQPrompt for use.
	 * <P>
	 * 
	 * @param identifier
	 *            a String that is the unique identifier for the Equation object.
	 * @return the EQObject that was created.
	 * @throws EQException
	 *             if an error occurs during processing
	 */
	public abstract EQObject createEQObject(String identifier) throws EQException;
	/**
	 * Get the underlying JDBC Connection to the database.
	 * <P>
	 * 
	 * @return the JDBC Connection object assoicated with the EQSession
	 * @throws EQException
	 *             if an error occurs during processing
	 */
	public abstract Connection getConnection() throws EQException;
	/**
	 * Creates an EQObjectMetaData for a given option identifier.
	 * <P>
	 * To use an EQObject, its meta data is retrieved from the Equation host at runtime. The retrieval of the meta data happens
	 * during createObject the first time an object of this type is used for the particular system/unit.
	 * <P>
	 * As this retrieval of meta data will make the first process slower, the meta data for EQObjects can be preloaded at
	 * application start using this method.
	 * 
	 * @param identifier
	 *            a String that is the unique identifier for the Equation object.
	 * @return the EQObjectMeatData that was created.
	 * @throws EQException
	 *             if an error occurs during processing
	 */
	public EQObjectMetaData initialiseMetaData(String identifier) throws EQException;
	/*******************************************************************************************************************************
	 * Determine if the connection is ready for use for API calls.
	 * <P>
	 * 
	 * @return true if the connection has been establised and the user has been authenticated on Equation. API calls can then be
	 *         made.
	 * @throws EQException
	 *             if an error occurs during processing
	 */
	public abstract boolean isLoggedOn() throws EQException;
	/*******************************************************************************************************************************
	 * Logoff the user from Equation using an existing connection.
	 * <P>
	 * This method is provided for applications which wish to create their own SQL connections to the host.
	 * <P>
	 * The connection is reset.
	 * 
	 * @throws EQException
	 *             if any connection, authentication of configuration error occurs
	 */
	public abstract void logOff() throws EQException;
	/**
	 * Commitment control - prepare transaction.
	 * <P>
	 * This will prepare Equation so that its current group of transactions has been finished so that it is ready for the commit on
	 * the XAResource.
	 * <P>
	 * 
	 * @throws EQException
	 *             if an error occurs during processing
	 */
	public abstract void prepare() throws EQException;
	/*******************************************************************************************************************************
	 * Commitment control - rollback transaction.
	 * <P>
	 * This will drop (rollback) all changes from the commit start boundary to the point where the rollback command is issued
	 * according to the transaction isolation specified in the EQSessionProperties object.
	 * <P>
	 * All database locks currently held by Equation (via the Connection) are released.
	 * <P>
	 * Note: If all locks currently help by the entire Connection need to be released or an XA connections is used, execute a
	 * rollback() on the SQL Connection held inside the EQSession.
	 * <P>
	 * This method should be used only when a connection has been supplied or autoCommit has been disabled. startTrans must be
	 * called before this method if autoCommit has been disabled.
	 * <P>
	 * 
	 * @throws SQLException
	 *             if a SQL error occurs.
	 * @throws EQException
	 *             if called in autoCommit mode, or if startTrans has not been called previously.
	 */
	public abstract void rollback() throws SQLException, EQException;
	/*******************************************************************************************************************************
	 * Get the properties of this session.
	 * <P>
	 * Returns a clone of the EQSessionProperties object associated with the session.
	 * <P>
	 * 
	 * @return Equation session properties
	 */
	public EQSessionProperties getProperties();
	/*******************************************************************************************************************************
	 * Get the Equation user identifier.
	 * <P>
	 * Get the Equation user identifier for the profile associated with this EQSession.
	 * <P>
	 * 
	 * @return the user identifier.
	 */
	public String getEquationUserIdentifier();
	/*******************************************************************************************************************************
	 * Determine if Equation automatic commitment control is being used.
	 * <P>
	 * 
	 * @return true if autoCommit is on.
	 * @throws EQException
	 *             if an error occurs during processing
	 */
	public boolean isAutoEQCommit() throws EQException;
	/*******************************************************************************************************************************
	 * Specifies whether Equation automatic commitment control is applied.
	 * <p>
	 * The autoCommit state is relevent only if a connection has not been supplied and the transaction isolation level is
	 * TRANSACTION_ISOLATION_EQUATION_ONLY.
	 * <P>
	 * In autoCommit mode EQTransaction objects perform their own commitment control around their updates. When off, the external
	 * application must perform transaction management control using EQSessionImpl startTrans, commit and rollback methods.
	 * <p>
	 * The default is on (true) when the transaction isolation level is EQUATION_ONLY. In this case each EQTransaction will perform
	 * a commit on its database updates each time the defaultAction, add, maintain or delete method is called. It will also rollback
	 * any failed updates if required.
	 * <p>
	 * If set to off (false), no commitment control will be carried out by these methods and the caller must invoke startTrans
	 * before any database update method is called, the commit method when all transactions have updated succesfully, and rollback
	 * in the event of a failure.
	 * <p>
	 * The autoCommit state cannot be changed if an uncommitted transaction exists on the connection.
	 * <P>
	 * 
	 * @param autoCommit
	 *            whether to use automatic commitment control.
	 * @throws EQException
	 *             if an error occurs during processing
	 */
	public void setAutoEQCommit(boolean autoCommit) throws EQException;
	/*******************************************************************************************************************************
	 * Starts a new transaction boundary.
	 * <P>
	 * If a transaction has already been started, and it has not been commited or rolledback, calling startTrans again will cause a
	 * rollback.
	 * <P>
	 * This method should be used only when the connection has not been supplied and the transaction isolation level is
	 * EQUATION_ONLY. Also this method should be used only when autoCommit has been disabled.
	 * <P>
	 * 
	 * @throws SQLException
	 *             if a SQL error occurs.
	 * @throws EQException
	 *             if called in autoCommit mode.
	 */
	public void startTrans() throws SQLException, EQException;
	/*******************************************************************************************************************************
	 * Determines if a transaction is in progress on this connection.
	 * <P>
	 * 
	 * @return true if an uncommited transaction exists.
	 */
	public boolean isTransactionStarted();
	/**
	 * Get system name.
	 * <P>
	 * 
	 * @return the system name for this session.
	 */
	public String getSystemName();
	/**
	 * Get unit mnemonic.
	 * <P>
	 * 
	 * @return the unit mnemonic for this session.
	 */
	public String getUnitMnemonic();
	/*******************************************************************************************************************************
	 * Get the Equation unit's processing date.
	 * <P>
	 * This is the date under which transactions are processed. If this method is used during the EOD swap an EQException will be
	 * thrown.
	 * <P>
	 * 
	 * @return the date in ISO format (yyyy-mm-dd).
	 */
	public String getProcessingDate() throws SQLException, EQException;
}