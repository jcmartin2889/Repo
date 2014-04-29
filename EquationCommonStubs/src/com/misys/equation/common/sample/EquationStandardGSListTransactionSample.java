/**
 * Copyright and all other intellectual property rights in this software, in any form, is vested in Misys International Banking
 * Systems Ltd ("Misys") or a related company.
 * 
 * This software may not be copied, amended, compiled, translated, or developed; or sold, leased, hired, rented, or disclosed to any
 * third party without the prior written consent of Misys.
 * 
 * Copyright Misys International Banking Systems Ltd, 1975 and later
 */

package com.misys.equation.common.sample;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardGSListTransaction;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.Toolbox;

/**
 * A transaction sample for illustration of basic transaction processing steps - multiple GS records are is supported.
 */
public class EquationStandardGSListTransactionSample
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationStandardGSListTransactionSample.java 15460 2013-03-08 13:11:48Z williae1 $";

	public static void main(String[] args) throws Exception
	{
		EquationStandardGSListTransactionSample transactionSample = new EquationStandardGSListTransactionSample();
		transactionSample.process();
	}
	public boolean process()
	{
		boolean continueProcessing = true;
		EquationStandardSession session = null;
		try
		{
			// Get a connection to the iSeries. A QZDASONIT job is created and a session identifier is returned.
			session = EquationContextSample.getContext().getSampleSession();

			// Start a logical transaction
			session.startEquationTransaction();

			// First transaction
			continueProcessing = processRAATransaction(session);

			// Check transaction status. Rollback and do other failure processing if required.
			if (!continueProcessing)
			{
				// Rollback the transaction as a result of a non-fatal error
				session.rollbackTransactions();
				return continueProcessing;
			}

			// Do more transactions here

			// End the logical transaction
			session.endEquationTransaction();

			// Commit the logical transaction
			session.commitTransactions();

			// Log off the session
			EquationCommonContext.getContext().logOffSession(session.getSessionIdentifier());
		}
		catch (Exception e)
		{
			try
			{
				// Rollback the transaction as a result of a fatal error
				if (session != null)
				{
					session.rollbackTransactions();
				}
			}
			catch (EQException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
			continueProcessing = false;
		}
		return continueProcessing;
	}
	/**
	 * Process an RAA transaction with hardcoded values
	 * 
	 * @param session
	 * @return status of posted ASI transaction
	 * @throws UnknownHostException
	 * @throws EQException
	 */
	private boolean processRAATransaction(EquationStandardSession session) throws UnknownHostException, EQException
	{
		// Create an EquationStandardTransaction instance for storing the data using recovery module and menu option
		EquationStandardGSListTransaction transaction = new EquationStandardGSListTransaction("N20FRR" + "CGL", session);
		// Set workstation id. This should ideally be unique across all jobs on the iSeries.
		transaction.setWorkStationId(session.getConnectionWrapper().getJobId().substring(22));
		// Create a transaction id based on timestamp
		String transactionId = Toolbox.getTimeBasedReference13();
		// Set transaction id that will appear in messages
		transaction.setId(session.getUserId() + " " + InetAddress.getLocalHost().getHostAddress() + transactionId);
		// Set transaction mode
		transaction.setMode(IEquationStandardObject.FCT_ADD);
		// Set transaction key values
		transaction.setFieldValue("GZCCY", "ARA"); // Currency (3A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch (4A)
		transaction.setFieldValue("GZLED", "1010105"); // Expiry Date (7Z)
		transaction.setFieldValue("GZXLCY", "USD"); // Currency (3A)
		transaction.clearList();
		transaction.addRow();
		transaction.setFieldValue("GSIMG", "A"); // Image (1A,0)
		transaction.setFieldValue("GSNBP", "2"); // Number of days overdue (3P,0)
		transaction.setFieldValue("GSTMP", "M"); // Time period (1A)
		transaction.setFieldValue("GSXGL", "12312435"); // Period gap limit (15P,0)
		transaction.setFieldValue("GSXCL", "654345354"); // Cumulative gap limit (15P,0)
		transaction.addRow();
		transaction.setFieldValue("GSIMG", "A"); // Image (1A,0)
		transaction.setFieldValue("GSNBP", "21"); // Number of days overdue (3P,0)
		transaction.setFieldValue("GSTMP", "D"); // Time period (1A)
		transaction.setFieldValue("GSXGL", "212121"); // Period gap limit (15P,0)
		transaction.setFieldValue("GSXCL", "212121"); // Cumulative gap limit (15P,0)
		transaction.addRow();
		// Add the transaction to session.
		session.addEquationTransaction(transaction);
		// Apply the Equation transaction
		session.applyEquationTransaction(transaction);
		// Check for Warnings and Errors
		System.out.println("Warnings = " + transaction.getWarningList());
		System.out.println("Errors = " + transaction.getErrorList());
		return transaction.getValid();
	}
}
