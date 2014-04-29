/*
 * Created on 17-Jan-06
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.misys.equation.common.internal.eapi.core;

import java.util.ArrayList;

/***********************************************************************************************************************************
 * Controls an Equation transaction.
 * <P>
 * Transactions typically fall into two categories:
 * <P>
 * <strong>Single Action</strong>, which supports only one action; Add, Maintain or Delete.<br>
 * Three Single Action transactions are required to support the lifecycle of their related database objects. An example of such an
 * object is the Sundry Item. It is supported by Add Sundry Item (ASI - ASITransaction - Add) Maintain Sundry Item (MSI -
 * MSITransaction - Maintain) and Cancel Sundry Item (CSI - CSITransaction - Delete).
 * <P>
 * <strong>Fully Functional</strong>, which supports all three actions.<br>
 * Only one Fully Functional transaction is required to support the lifecycle of their related database objects. An example of such
 * an object is a Settlement Instruction. It is supported by Standing Settlement Instruction (DCI - DCITransaction), which supports
 * all actions.
 * <P>
 * Using a Single Action transaction is straight forward as it can only perform one action. When such a transaction is constructed
 * it automatically sets the maintenace mode to the action it can perform (add, maintain or delete). For example to add an object to
 * the database create the transaction object, set the required fields, (optionally perform a validation), and then call add.
 * <P>
 * When using a Fully Functional transaction, however, the application must set the required maintenance mode prior to calling the
 * database access methods on the EQTransaction interface. This is because the desired action must be known in order to validate the
 * data.
 * <P>
 * Transactions extend the read-only behaviour of the EQEnquiry by offering the ability to update the database.
 * <P>
 * When processing the transaction, the data is always validated before the database is updated.
 * <P>
 * An EQTransaction or subclass instance is created through the createObject method of the EQSessionImpl class.
 * <P>
 * Also, see the EQTransactionSample for example usage.
 * <P>
 * 
 * @see com.misys.equation.ebs.samples.EQTransactionSample
 * @author Misys International Banking Systems Ltd.
 */
public interface EQTransaction extends EQEnquiry
{
	// **************************************************************************************************
	// Define Constants
	// **************************************************************************************************
	/**
	 * A constant indicating that idetifies that and add, update or delete for the transaction is to be validated only.
	 */
	public static final int ACTION_VALIDATE = 1;
	/**
	 * A constant indicating that idetifies that and add, update or delete for the transaction is to be validated and updated.
	 */
	public static final int ACTION_UPDATE = 2;
	/*******************************************************************************************************************************
	 * Maintenance Mode constant - add the object to the database.
	 */
	public static final char MAINTENANCE_MODE_ADD = 'A';
	/*******************************************************************************************************************************
	 * Maintenance Mode constant - delete the object from the database.
	 */
	public static final char MAINTENANCE_MODE_DELETE = 'D';
	/*******************************************************************************************************************************
	 * Maintenance Mode constant - update an existing object in the database.
	 */
	public static final char MAINTENANCE_MODE_MAINTAIN = 'M';
	/*******************************************************************************************************************************
	 * Maintenance Mode constant - mode not yet determined.
	 */
	public static final char MAINTENANCE_MODE_UNKNOWN = ' ';
	/*******************************************************************************************************************************
	 * Gets the maintenance mode for the transaction.
	 * <P>
	 * Valid values are:
	 * <ul>
	 * <li>MAINT_MODE_ADD - Add new data
	 * <li>MAINT_MODE_MAINTAIN - Modify existing data
	 * <li>MAINT_MODE_DELETE - Delete existing data
	 * </ul>
	 * <P>
	 * 
	 * @return maintenance mode for the transaction
	 */
	public abstract char getMaintenanceMode();
	/*******************************************************************************************************************************
	 * Specifies the maintenance mode for the transaction.
	 * <P>
	 * Valid values are:
	 * <ul>
	 * <li>MAINT_MODE_ADD - Add new data
	 * <li>MAINT_MODE_MAINTAIN - Modify existing data
	 * <li>MAINT_MODE_DELETE - Delete existing data
	 * </ul>
	 * <P>
	 * 
	 * @param cMaintenanceMode
	 *            the maintenance mode for the transaction.
	 */
	public abstract void setMaintenanceMode(char cMaintenanceMode);
	/**
	 * Execute action on transaction.
	 * <P>
	 * The transaction should have its maintenance mode and other data set prior to this method being called.
	 * <P>
	 * The action can be performed in two modes: TRANS_MODE_VALIDATE which validates the transaction data and TRANS_MODE_UPDATE
	 * which validates the transaction data and if valid, will update the database.
	 * <P>
	 * 
	 * @param session
	 *            the session connected to the server.
	 * @param iAction
	 *            the transaction mode constant.
	 * @return whether the call succeeded. Use getStatus for detailed status information.
	 * @throws EQException
	 *             if an error occurred during processing.
	 */
	public boolean performAction(EQSession session, int iAction) throws EQException;
	/**
	 * Execute action on transaction.
	 * <P>
	 * The transaction should have its maintenance mode and other data set prior to this method being called.
	 * <P>
	 * The action can be performed in two modes: TRANS_MODE_VALIDATE which validates the transaction data and TRANS_MODE_UPDATE
	 * which validates the transaction data and if valid, will update the database.
	 * <P>
	 * The maintenance modes can be of three types: MAINTENANCE_MODE_ADD which adds the transaction data, MAINTENANCE_MODE_MAINTAIN
	 * which maintains the transaction data and MAINTENANCE_MODE_DELETE which deletes the transaction data.
	 * <P>
	 * 
	 * @param session
	 *            the session connected to the server.
	 * @param iAction
	 *            the transaction mode constant.
	 * @param iMaintenanceMode
	 *            the maintennace mode constant.
	 * @return whether the call succeeded. Use getStatus for detailed status information.
	 * @throws EQException
	 *             if an error occurred during processing.
	 */
	public boolean performAction(EQSession session, int iAction, char iMaintenanceMode) throws EQException;
	/**
	 * Add transaction.
	 * <P>
	 * Add calls Equation to add the object to the database.
	 * <P>
	 * The call to add can be performed in two modes: TRANS_MODE_VALIDATE which validates the transaction data and TRANS_MODE_UPDATE
	 * which validates the transaction data and if valid, will update the database.
	 * <P>
	 * 
	 * @param session
	 *            the session connected to the server.
	 * @param iAction
	 *            the transaction mode constant.
	 * @return whether the call succeeded. Use getStatus for detailed status information.
	 * @throws EQException
	 *             if an error occurred during processing.
	 */
	public abstract boolean add(EQSession session, int iAction) throws EQException;
	/**
	 * Maintain transaction.
	 * <P>
	 * Maintain calls Equation to modify the data associated with the key data The transaction should have its (key) data set prior
	 * to this method being called Preferably, Retrieve should be used prior to this call and the data returned examined to ensure
	 * that it is the correct data to maintain
	 * <P>
	 * The call to maintain can be performed in two modes: TRANS_MODE_VALIDATE which validates the transaction data and
	 * TRANS_MODE_UPDATE which validates the transaction data and if valid, will update the database.
	 * <P>
	 * 
	 * @param session
	 *            the session connected to the server.
	 * @param iAction
	 *            the transaction mode constant.
	 * @return whether the call succeeded. Use getStatus for detailed status information.
	 * @throws EQException
	 *             if an error occurred during processing.
	 */
	public abstract boolean maintain(EQSession session, int iAction) throws EQException;
	/**
	 * Delete transaction.
	 * <P>
	 * Delete calls Equation to delete the data associated with the key data The transaction should have its (key) data set prior to
	 * this method being called Preferably, Retrieve should be used prior to this call and the data returned examined to ensure that
	 * it is the correct data to delete
	 * <P>
	 * The call to delete can be performed in two modes: TRANS_MODE_VALIDATE which validates the transaction data and
	 * TRANS_MODE_UPDATE which validates the transaction data and if valid, will update the database.
	 * <P>
	 * 
	 * @param session
	 *            the session connected to the server.
	 * @param iAction
	 *            the transaction mode constant.
	 * @return whether the call succeeded. Use getStatus for detailed status information.
	 * @throws EQException
	 *             if an error occurred during processing.
	 */
	public abstract boolean delete(EQSession session, int iAction) throws EQException;
	/*******************************************************************************************************************************
	 * Determine whether the Add operation is supported.
	 * <P>
	 * 
	 * @return whether the Add operation is supported
	 */
	public abstract boolean supportsAdd();
	/*******************************************************************************************************************************
	 * Determine whether the Maintain operation is supported.
	 * <P>
	 * 
	 * @return whether the Maintain operation is supported
	 */
	public abstract boolean supportsMaintain();
	/*******************************************************************************************************************************
	 * Determine whether the Delete operation is supported.
	 * <P>
	 * 
	 * @return whether the Delete operation is supported
	 */
	public abstract boolean supportsDelete();
	/*******************************************************************************************************************************
	 * Specifies the auto override value.
	 * <P>
	 * 
	 * @param autoOverride
	 *            whether automatic override will occur
	 */
	public void setAutoOverride(boolean autoOverride);
	/*******************************************************************************************************************************
	 * Get the automatic override value.
	 * <P>
	 * 
	 * @return whether automatic override will occur.
	 */
	public boolean isAutoOverride();
	/*******************************************************************************************************************************
	 * Get the list of overriden warning messages for the object.
	 * <P>
	 * If during the lifetime of this transaction warning messages are overriden by the user or supervisor they are moved from the
	 * standard list of messages to the list of overrides.
	 * <P>
	 * 
	 * @return any overridden messages for the object
	 */
	public ArrayList<EQMessage> getOverrides();
	/*******************************************************************************************************************************
	 * Calls Equation to override warnings.
	 * <P>
	 * If the warnings were generated in a previous validation call, the warnings are overriden in validation mode. If the warnings
	 * were generated in an update call (e.g. add/maintain/delete) the warning are overriden in update mode and if no further errors
	 * or warnings are generated an update to the database is attempted.
	 * <P>
	 * 
	 * @param session
	 *            the session connected to the server.
	 * @return whether the call succeeded. Use getStatus for detailed status information.
	 * @throws EQException
	 *             if an error occurs during processing
	 */
	public boolean override(EQSession session) throws EQException;
	/*******************************************************************************************************************************
	 * Accept - called when the transaction has been referred and accepted.
	 * <P>
	 * Calls Equation to perform the update.
	 * <P>
	 * Once accepted and updated the transaction is marked as read-only and cannot be modified.
	 * <P>
	 * 
	 * @param session
	 *            the EQSession the call is to be made on.
	 * @param supervisorID
	 *            the userid of the supervisor performing the override.
	 * @param password
	 *            the supervisor's password (not their OS password, their Equation supervisor password).
	 * @param comment
	 *            an optional comment.
	 * @return whether the call succeeded. Use getStatus for more detailed information
	 * @throws EQException
	 *             if an error occurs during processing
	 */
	public boolean accept(EQSession session, String supervisorID, char[] password, String comment) throws EQException;
	/*******************************************************************************************************************************
	 * Reject - called when the transaction has been referred and rejected.
	 * <P>
	 * 
	 * @param supervisorID
	 *            the userid of the supervisor performing the override.
	 * @param reason
	 *            the reason the transaction was rejected.
	 * @return whether the call succeeded. Use getStatus for more detailed information
	 * @throws EQException
	 *             if an error occurs during processing
	 */
	public boolean reject(String supervisorID, String reason) throws EQException;
	/*******************************************************************************************************************************
	 * Get any comment added by a supervisor in an override.
	 * <P>
	 * 
	 * @return a string containing the supervisor comment.
	 */
	public String getSupervisorComment();
	/*******************************************************************************************************************************
	 * Get the user id of the supervisor used for overrides.
	 * <P>
	 * 
	 * @return a string containing the supervisor user id.
	 */
	public String getSupervisorID();
	/*******************************************************************************************************************************
	 * Set the credentials to be used for a supervisor override.
	 * <P>
	 * This data is stored only for the lifetime of the current call. The password is encrypted immediately, and the passed
	 * character array is overwritten with ***** in memory.
	 * <P>
	 * Call setSupervisor with a null EQSession to reset.
	 * <P>
	 * 
	 * @param session
	 *            the EQSession the call is to be made on.
	 * @param userID
	 *            the userid of the supervisor performing the override.
	 * @param password
	 *            the supervisor's password (not their OS password, their Equation supervisor password).
	 * @param comment
	 *            an optional comment.
	 * @throws EQException
	 *             if an error occurs whilst encripting the password
	 */
	public void setSupervisor(EQSession session, String userID, char[] password, String comment) throws EQException;
	/*******************************************************************************************************************************
	 * Specifies a unique reference for the transaction.
	 * <P>
	 * 
	 * @param uniqueRef
	 *            any application specific reference for the transaction. Maximum of 16 charaters.
	 */
	public void setReference(String uniqueRef);
	/*******************************************************************************************************************************
	 * Get the unique reference associated with the transaction.
	 * <P>
	 * 
	 * @return the application specific unique reference for the transaction
	 */
	public String getReference();
	/*******************************************************************************************************************************
	 * Specifies the input branch for the transaction.
	 * <P>
	 * 
	 * @param inputBranch
	 *            an input branch for the transaction. Maximum of 16 charaters.
	 */
	public void setInputBranch(String inputBranch);
	/*******************************************************************************************************************************
	 * Get the input branch associated with the transaction.
	 * <P>
	 * 
	 * @return the input branch for the transaction
	 */
	public String getInputBranch();
	/*******************************************************************************************************************************
	 * Specifies whether Equation should use default charges for this transaction.
	 * <P>
	 * Toggles Equation's default Fees and Charges processing. See the documentation on Enhanced Fees and Charges.
	 * <P>
	 * 
	 * @param useDefaultCharges
	 *            whether to use default charges.
	 */
	public void setUseDefaultCharges(boolean useDefaultCharges);
	/*******************************************************************************************************************************
	 * Determine if Equation is applying default charges for this transaction.
	 * <P>
	 * 
	 * @return true if default fees and charges are being applied.
	 */
	public boolean isUseDefaultCharges();
	/*******************************************************************************************************************************
	 * Specifies whether to check the reference for the transaction.
	 * <P>
	 * 
	 * @param checkReference
	 *            whether or not to check the reference for this transaction.
	 */
	public void setCheckReference(String checkReference);
	/*******************************************************************************************************************************
	 * Get the Check Reference value associated with the transaction.
	 * <P>
	 * 
	 * @return the Check Reference value for the transaction
	 */
	public String getCheckReference();
}