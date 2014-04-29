/*
 * This sample code is provided by Misys for illustrative purposes only.
 * 
 * These examples have not been thoroughly tested under all conditions.
 * 
 * Misys, therefore, cannot guarantee or imply reliability, serviceability, or function of these programs.
 * 
 * All programs contained herein are provided to you "AS IS" without any warranties of any kind. The implied warranties of
 * non-infringement, merchantability and fitness for a particular purpose are expressly disclaimed.
 */
package com.misys.equation.common.test.xapi.standard;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.Toolbox;

// *************************************************************************************************
/**
 * 
 * 
 */
// *************************************************************************************************
public class MTM
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MTM.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	String inputBrnmStr = "LOND";
	EquationStandardSession session;
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	private MTM()
	{
		try
		{
			session = TestEnvironment.getTestEnvironment().getStandardSession();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public static void main(String[] inputParameters)
	{
		MTM test = new MTM();
		test.test();
	}
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	public void test()
	{
		// Have a bash...
		try
		{
			// Get a new transaction for a sundry item
			EquationStandardTransaction maintainTransferMethod = new EquationStandardTransaction("S43FRRMTM", session);
			// Set the transaction type
			maintainTransferMethod.setMode("A");
			// Set up fields
			maintainTransferMethod.setFieldValue("GZXM", "QC");
			maintainTransferMethod.setFieldValue("GZXMD", "Look I've changed!");
			maintainTransferMethod.setFieldValue("GZEXF", "1");
			maintainTransferMethod.setFieldValue("GZNSF1", "N");
			maintainTransferMethod.setFieldValue("GZNSF2", "N");
			maintainTransferMethod.setFieldValue("GZNSF3", "N");
			maintainTransferMethod.setFieldValue("GZNSF4", "N");
			maintainTransferMethod.setFieldValue("GZNSF5", "N");
			maintainTransferMethod.setFieldValue("GZNSF6", "N");
			maintainTransferMethod.setFieldValue("GZASI", "N");
			maintainTransferMethod.setFieldValue("GZVXD", "N");
			session.addEquationTransaction(maintainTransferMethod);
			// Apply the transaction
			session.applyTransactions();
			session.commitTransactions();
			Toolbox.printMessages(maintainTransferMethod.getErrorList());
			Toolbox.printMessages(maintainTransferMethod.getWarningList());
			System.out.println("finished!");
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			// Rollback any transactions for the unit
		}
	}
}
