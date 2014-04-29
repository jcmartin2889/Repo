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
package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCase;
import com.misys.equation.common.test.TestEnvironment;

public class CIU extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CIU.java 8213 2010-07-15 16:56:49Z CHALLIP1 $";
	String paymentType = "KIC";
	String paymentReference = "KBSL00103H000001";

	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	private String programName = "K63MRR";
	private String optionId = "CIU";

	@Override
	public void setUp() throws Exception
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

	/**
	 * Return a transaction
	 * 
	 * @return a transaction
	 * 
	 * @throws Exception
	 */
	public EquationStandardTransaction getTransaction() throws Exception
	{
		EquationStandardTransaction transaction = getEquationStandardTransaction(programName + optionId);
		transaction.setWorkStationId(WORKSTATIONID);
		return transaction;
	}

	public void test00100RetrieveDetails()
	{
		try
		{
			// Get a new transaction for Clean Payment
			EquationStandardTransaction requestCancelCleanPayment = getTransaction();

			// **********************************************
			// Set the fields required for the retrieval call
			// **********************************************
			requestCancelCleanPayment.setFieldValue("GZDECD", "D"); // Decode; R/S=Canc req,C/D=Canc conf,A/B=Canc abort (1A)
			requestCancelCleanPayment.setFieldValue("GZREF", paymentReference); // Payment reference (16A)
			requestCancelCleanPayment.setFieldValue("GZXREF", ""); // External payment reference (16A)
			requestCancelCleanPayment.setFieldValue("GZCCR", "N"); // Cancellation confirmation required? (1A)
			requestCancelCleanPayment.setFieldValue("GZPYT", paymentType); // Payment type (3A)
			requestCancelCleanPayment.setFieldValue("GZUSNC", session.getUserId()); // Cancel requested by (4A)

			// Do the invocation
			requestCancelCleanPayment.setMode("A");
			requestCancelCleanPayment.setByteCP("H");
			applyRetrieval(requestCancelCleanPayment, true);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void test00200Update()
	{
		try
		{
			EquationStandardTransaction requestCancelCleanPayment = getTransaction();

			// **********************************************
			// Set the fields required for the update call
			// **********************************************
			requestCancelCleanPayment.setFieldValue("GZDECD", "D"); // Decode; R/S=Canc req,C/D=Canc conf,A/B=Canc abort (1A)
			requestCancelCleanPayment.setFieldValue("GZREF", paymentReference); // Payment reference (16A)
			requestCancelCleanPayment.setFieldValue("GZXREF", ""); // External payment reference (16A)
			requestCancelCleanPayment.setFieldValue("GZCCR", "N"); // Cancellation confirmation required? (1A)
			requestCancelCleanPayment.setFieldValue("GZPYT", paymentType); // Payment type (3A)
			requestCancelCleanPayment.setFieldValue("GZUSNC", session.getUserId()); // Cancel requested by (4A)

			// Do the invocation
			requestCancelCleanPayment.setMode("A");
			requestCancelCleanPayment.setByteCP("Z");
			applyTransaction(requestCancelCleanPayment, true);

			System.out.println("finished!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}