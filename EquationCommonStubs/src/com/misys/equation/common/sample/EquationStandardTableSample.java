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

import java.net.UnknownHostException;
import java.util.Calendar;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTable;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.Toolbox;

/**
 * An table sample for illustration of basic table processing steps. Note: Equation database files should not be updated using this
 * approach as Equation APIs should be used.
 */
public class EquationStandardTableSample
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationStandardTableSample.java 15460 2013-03-08 13:11:48Z williae1 $";
	public static final String USER_FILE = "UJVPF";

	public static void main(String[] args) throws Exception
	{
		EquationStandardTableSample transactionSample = new EquationStandardTableSample();
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

			// Make sure user file exists.
			// User files should be in unit KFIL library and OHPF file to be journaled and backed up.
			// User files should start with a "U".
			// User file UJVPF will be created with key, description and timestamp.
			String library = session.getUnit().getKFILLibrary();
			boolean fileExists = EquationSQLToolboxSample.fileExists(session, library, USER_FILE);
			if (!fileExists)
			{
				String sqlStatement = "CREATE TABLE " + EquationSQLToolboxSample.rtvFullPath(library, USER_FILE)
								+ " (UJVHRC CHAR(3) PRIMARY KEY, UJVHRD	CHAR(35), UJVTIM CHAR(35))";

				EquationSQLToolboxSample.executeStatement(session, sqlStatement);
				EquationSQLToolboxSample.strjrnpf(session, library, USER_FILE);
			}
			// Retrieve record 001 - this record is deleted later in the test so should not exist
			boolean recordExists1 = retrieveHoldCode(session, "001");
			if (!recordExists1)
			{
				addHoldCode(session, "001");
			}
			else
			{
				maintainHoldCode(session, "001");
			}
			recordExists1 = retrieveHoldCode(session, "001");
			if (recordExists1)
			{
				System.out.println("EquationStandardTable record should not exist");
			}
			// Retrieve record 002 - this record should exist if this test run more than once
			boolean recordExists2 = retrieveHoldCode(session, "002");
			if (!recordExists2)
			{
				addHoldCode(session, "002");
			}
			else
			{
				maintainHoldCode(session, "002");
			}
			deleteHoldCode(session, "001");

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
	 * Process a retrieval of hold code with hardcoded values
	 * 
	 * @param session
	 * @return status of posted ASI transaction
	 * @throws UnknownHostException
	 * @throws EQException
	 */
	private boolean retrieveHoldCode(EquationStandardSession session, String holdCode) throws UnknownHostException, EQException
	{
		// Setup data
		EquationStandardTable tableTransaction = new EquationStandardTable(USER_FILE, USER_FILE, "UJVHRC", session);
		tableTransaction.setFieldValue("UJVHRC", holdCode);
		// Execute
		session.retrieveEquationTable(tableTransaction);
		// Process output
		System.out.println("Retrieve Hold Description = " + tableTransaction.getFieldValue("UJVHRD") + " "
						+ tableTransaction.getFieldValue("UJVTIM"));
		System.out.println("Warnings = " + tableTransaction.getWarningList());
		System.out.println("Errors = " + tableTransaction.getErrorList());

		return tableTransaction.getValid();
	}
	/**
	 * Add hold code with hardcoded values
	 * 
	 * @param session
	 * @return status of posted ASI transaction
	 * @throws UnknownHostException
	 * @throws EQException
	 */
	private boolean addHoldCode(EquationStandardSession session, String holdCode) throws UnknownHostException, EQException
	{
		// Start a logical transaction
		session.startEquationTransaction();
		// Setup data
		EquationStandardTable tableTransaction = new EquationStandardTable(USER_FILE, USER_FILE, "UJVHRC", session);
		tableTransaction.setMode(EquationStandardTable.FCT_ADD);
		tableTransaction.setFieldValue("UJVHRC", holdCode);
		tableTransaction.setFieldValue("UJVHRD", "Add EquationStandardTable");
		// Set timestamp
		tableTransaction.setFieldValue("UJVTIM", Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT));
		// Execute
		session.applyEquationTable(tableTransaction);
		// Process output
		System.out.println("Add Hold Description =" + tableTransaction.getFieldValue("UJVHRD") + " "
						+ tableTransaction.getFieldValue("UJVTIM"));
		System.out.println("Warnings = " + tableTransaction.getWarningList());
		System.out.println("Errors = " + tableTransaction.getErrorList());
		// Transaction Control
		if (tableTransaction.getValid())
		{
			// End the logical transaction
			session.endEquationTransaction();
			// Commit the logical transaction
			session.commitTransactions();
		}
		else
		{
			// Rollback the transaction as a result of a non-fatal error
			session.rollbackTransactions();
		}
		return tableTransaction.getValid();
	}
	/**
	 * Maintain hold code with hardcoded values
	 * 
	 * @param session
	 * @return status of posted ASI transaction
	 * @throws UnknownHostException
	 * @throws EQException
	 */
	private boolean maintainHoldCode(EquationStandardSession session, String holdCode) throws UnknownHostException, EQException
	{
		// Start a logical transaction
		session.startEquationTransaction();
		// Setup data
		EquationStandardTable tableTransaction = new EquationStandardTable(USER_FILE, USER_FILE, "UJVHRC", session);
		tableTransaction.setFieldValue("UJVHRC", holdCode);
		// Retrieve
		session.retrieveEquationTable(tableTransaction);
		// Setup data
		tableTransaction.setMode(EquationStandardTable.FCT_MNT);
		tableTransaction.setFieldValue("UJVHRD", "Maint EquationStandardTable");
		// Set timestamp
		tableTransaction.setFieldValue("UJVTIM", Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT));
		// Execute
		session.applyEquationTable(tableTransaction);
		// Process output
		System.out.println("Maintain Hold Description = " + tableTransaction.getFieldValue("UJVHRD") + " "
						+ tableTransaction.getFieldValue("UJVTIM"));
		System.out.println("Warnings = " + tableTransaction.getWarningList());
		System.out.println("Errors = " + tableTransaction.getErrorList());
		// Transaction Control
		if (tableTransaction.getValid())
		{
			// End the logical transaction
			session.endEquationTransaction();
			// Commit the logical transaction
			session.commitTransactions();
		}
		else
		{
			// Rollback the transaction as a result of a non-fatal error
			session.rollbackTransactions();
		}
		return tableTransaction.getValid();
	}
	/**
	 * Delete hold code with hardcoded values
	 * 
	 * @param session
	 * @return status of posted ASI transaction
	 * @throws UnknownHostException
	 * @throws EQException
	 */
	private boolean deleteHoldCode(EquationStandardSession session, String holdCode) throws UnknownHostException, EQException
	{
		// Start a logical transaction
		session.startEquationTransaction();
		// Setup data
		EquationStandardTable tableTransaction = new EquationStandardTable(USER_FILE, USER_FILE, "UJVHRC", session);
		tableTransaction.setFieldValue("UJVHRC", holdCode);
		// Retrieve
		session.retrieveEquationTable(tableTransaction);
		// Setup data
		tableTransaction.setMode(EquationStandardTable.FCT_DEL);
		// Execute
		session.applyEquationTable(tableTransaction);
		// Process output
		System.out.println("Delete Hold Description = " + tableTransaction.getFieldValue("UJVHRD") + " "
						+ tableTransaction.getFieldValue("UJVTIM"));
		System.out.println("Warnings = " + tableTransaction.getWarningList());
		System.out.println("Errors = " + tableTransaction.getErrorList());
		// Transaction Control
		if (tableTransaction.getValid())
		{
			// End the logical transaction
			session.endEquationTransaction();
			// Commit the logical transaction
			session.commitTransactions();
		}
		else
		{
			// Rollback the transaction as a result of a non-fatal error
			session.rollbackTransactions();
		}
		return tableTransaction.getValid();
	}
}
