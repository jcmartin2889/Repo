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

public class COP extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: COP.java 8213 2010-07-15 16:56:49Z CHALLIP1 $";
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;

	String paymentReference = "LOND00105H000030";
	private String programName = "K62MRR";
	private String optionId = "COP";

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

			EquationStandardTransaction confirmOutwardCleanPayment = getTransaction();

			confirmOutwardCleanPayment.setFieldValue("GZDECD", "C"); // Decode; O/I=Auth,P/J=Susp,C=Conf,L=Coll (1A)
			confirmOutwardCleanPayment.setFieldValue("GZREF", paymentReference); // Payment reference (16A)
			confirmOutwardCleanPayment.setFieldValue("GZUSID", session.getUserId()); // Authorised by (4A)

			// Do the retrieve invocation
			applyRetrieval(confirmOutwardCleanPayment, true);
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

			EquationStandardTransaction confirmOutwardCleanPayment = getTransaction();

			confirmOutwardCleanPayment.setFieldValue("GZDECD", "C"); // Decode; O/I=Auth,P/J=Susp,C=Conf,L=Coll (1A)
			confirmOutwardCleanPayment.setFieldValue("GZREF", paymentReference); // Payment reference (16A)
			confirmOutwardCleanPayment.setFieldValue("GZUSID", session.getUserId()); // Authorised by (4A)

			// Do the invocation
			confirmOutwardCleanPayment.setMode("M");
			applyTransaction(confirmOutwardCleanPayment, true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
