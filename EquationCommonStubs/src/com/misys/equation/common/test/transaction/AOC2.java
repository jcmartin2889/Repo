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

public class AOC2 extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AOC2.java 8213 2010-07-15 16:56:49Z CHALLIP1 $";
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;

	String paymentReference = "KBSL00103H000032";
	private String programName = "K62MRR";
	private String optionId = "AOC";

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

	public EquationStandardTransaction getTransaction() throws Exception
	{
		EquationStandardTransaction transaction = getEquationStandardTransaction(programName + optionId);
		transaction.setWorkStationId(WORKSTATIONID);
		return transaction;
	}

	public void test00100Retrieve()
	{
		try
		{

			EquationStandardTransaction authoriseOutwardCleanPayment = getTransaction();

			authoriseOutwardCleanPayment.setFieldValue("GZDECD", "O"); // Decode; O/I=Auth,P/J=Susp,C=Conf,L=Coll (1A)
			authoriseOutwardCleanPayment.setFieldValue("GZREF", paymentReference); // Payment reference (16A)
			authoriseOutwardCleanPayment.setFieldValue("GZUSID", session.getUserId()); // Authorised by (4A)

			// Do the retrieve invocation
			applyRetrieval(authoriseOutwardCleanPayment, true);
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

			EquationStandardTransaction authoriseOutwardCleanPayment = getTransaction();

			authoriseOutwardCleanPayment.setFieldValue("GZDECD", "O"); // Decode; O/I=Auth,P/J=Susp,C=Conf,L=Coll (1A)
			authoriseOutwardCleanPayment.setFieldValue("GZREF", paymentReference); // Payment reference (16A)
			authoriseOutwardCleanPayment.setFieldValue("GZUSID", session.getUserId()); // Authorised by (4A)

			// Do the invocation
			authoriseOutwardCleanPayment.setMode("M");
			applyTransaction(authoriseOutwardCleanPayment, true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
