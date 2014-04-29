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

public class AOP_WLC extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AOP_WLC.java 8213 2010-07-15 16:56:49Z CHALLIP1 $";
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;

	String paymentReference = "LOND00105X001999";
	String inputBrnmStr = "0543";
	String paymentType = "3CA";
	String receiveCurrency = "GBP";
	String payCurrency = "USD";
	String remitterId = "FREDDY";
	String totalReceiveAmount = "500000";
	String receiveNostro = "GBP R";
	String payNostro = "USD P";
	String sender = "s";
	String beneficiaryAccountLine = "/1234567890";
	String beneficiaryName = "Osama Bin Laden";
	String accountWithBranch = "//CN123456";
	String accountwithBICBank = "CNAB";
	String accountwithBICCountry = "CN";
	String accountwithBICLocation = "22";
	String accountwithBICSuffix = "XXX";
	String bankOpCode = "CRED";
	String chargesFor = "OUR";
	private String programName = "K61ARR";
	private String optionId = "AOP";

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

	public void test00100RetrieveDefaults()
	{
		try
		{
			// Get a new transaction for Clean Payment
			EquationStandardTransaction addOutwardCleanPayment = getTransaction();

			// Do the retrieve
			addOutwardCleanPayment.setFieldValue("GZREF", paymentReference);
			addOutwardCleanPayment.setFieldValue("GZTSET", "3CAGBPUSD");
			addOutwardCleanPayment.setFieldValue("GZPYT", paymentType);
			addOutwardCleanPayment.setFieldValue("GZUSNL", session.getUserId());
			addOutwardCleanPayment.setFieldValue("GZAPP", "CP");
			addOutwardCleanPayment.setFieldValue("GZIAB", inputBrnmStr);
			addOutwardCleanPayment.setFieldValue("GZAB1", "0132");
			addOutwardCleanPayment.setFieldValue("GZAN1", "012008");
			addOutwardCleanPayment.setFieldValue("GZAS1", "050");
			addOutwardCleanPayment.setFieldValue("GZPNMT", "250000");
			addOutwardCleanPayment.setFieldValue("GZAMTE", "4");
			addOutwardCleanPayment.setFieldValue("GZOAN7", "oan7");
			addOutwardCleanPayment.setFieldValue("GZOAN9", "oan9");
			addOutwardCleanPayment.setFieldValue("GZBAD1", "ben 1 address");
			addOutwardCleanPayment.setFieldValue("GZBAD5", "ben 5 address");
			addOutwardCleanPayment.setFieldValue("GZAAD1", "account 1 address");
			addOutwardCleanPayment.setFieldValue("GZAAD5", "account 5 address");

			// Do the retrieve invocation
			addOutwardCleanPayment.setMode("R");
			addOutwardCleanPayment.setByteCP("H");
			applyRetrieval(addOutwardCleanPayment, true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void test00200Validate()
	{
		try
		{
			EquationStandardTransaction addOutwardCleanPayment = getTransaction();

			// **********************************************
			// Set the fields required for the validate call
			// **********************************************
			addOutwardCleanPayment.setFieldValue("GZREF", paymentReference);
			addOutwardCleanPayment.setFieldValue("GZTSET", "3CAGBPUSD");
			addOutwardCleanPayment.setFieldValue("GZPYT", paymentType);
			addOutwardCleanPayment.setFieldValue("GZUSNL", session.getUserId());
			addOutwardCleanPayment.setFieldValue("GZAPP", "CP");
			addOutwardCleanPayment.setFieldValue("GZIAB", inputBrnmStr);
			addOutwardCleanPayment.setFieldValue("GZAB1", "0132");
			addOutwardCleanPayment.setFieldValue("GZAN1", "012008");
			addOutwardCleanPayment.setFieldValue("GZAS1", "050");
			addOutwardCleanPayment.setFieldValue("GZPNMT", "250000");
			addOutwardCleanPayment.setFieldValue("GZAMTE", "4");
			addOutwardCleanPayment.setFieldValue("GZOAN7", "oan7");
			addOutwardCleanPayment.setFieldValue("GZOAN9", "oan9");
			addOutwardCleanPayment.setFieldValue("GZBAD1", "ben 1 address");
			addOutwardCleanPayment.setFieldValue("GZBAD5", "ben 5 address");
			addOutwardCleanPayment.setFieldValue("GZAAD1", "account 1 address");
			addOutwardCleanPayment.setFieldValue("GZAAD5", "account 5 address");
			addOutwardCleanPayment.setFieldValue("GZPYP", "CB");
			addOutwardCleanPayment.setFieldValue("GZSAV", "N");
			addOutwardCleanPayment.setFieldValue("GZNST2", payNostro);

			// Do the invocation
			addOutwardCleanPayment.setMode("A");
			addOutwardCleanPayment.setByteCP("H");
			applyNoCommitTransaction(addOutwardCleanPayment, true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void test00300Update()
	{
		try
		{
			EquationStandardTransaction addOutwardCleanPayment = getTransaction();

			// **********************************************
			// Set the fields required for the update call
			// **********************************************
			addOutwardCleanPayment.setFieldValue("GZREF", paymentReference);
			addOutwardCleanPayment.setFieldValue("GZTSET", "3CAGBPUSD");
			addOutwardCleanPayment.setFieldValue("GZPYT", paymentType);
			addOutwardCleanPayment.setFieldValue("GZUSNL", session.getUserId());
			addOutwardCleanPayment.setFieldValue("GZAPP", "CP");
			addOutwardCleanPayment.setFieldValue("GZIAB", inputBrnmStr);
			addOutwardCleanPayment.setFieldValue("GZAB1", "0132");
			addOutwardCleanPayment.setFieldValue("GZAN1", "012008");
			addOutwardCleanPayment.setFieldValue("GZAS1", "050");
			addOutwardCleanPayment.setFieldValue("GZPNMT", "250000");
			addOutwardCleanPayment.setFieldValue("GZAMTE", "4");
			addOutwardCleanPayment.setFieldValue("GZOAN7", "oan7");
			addOutwardCleanPayment.setFieldValue("GZOAN9", "oan9");
			addOutwardCleanPayment.setFieldValue("GZBAD1", "ben 1 address");
			addOutwardCleanPayment.setFieldValue("GZBAD5", "ben 5 address");
			addOutwardCleanPayment.setFieldValue("GZAAD1", "account 1 address");
			addOutwardCleanPayment.setFieldValue("GZAAD5", "account 5 address");
			addOutwardCleanPayment.setFieldValue("GZPYP", "CB");
			addOutwardCleanPayment.setFieldValue("GZSAV", "N");

			// Do the invocation
			addOutwardCleanPayment.setMode("A");
			addOutwardCleanPayment.setByteCP("Z");
			applyTransaction(addOutwardCleanPayment, true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void assertTestNoCommitTransaction(EquationStandardTransaction transaction, boolean expectedResult) throws Exception
	{
		System.out.println("JobId = " + session.getConnectionWrapper().getJobId() + "\n");
		session.addEquationTransaction(transaction);
		session.applyTransactions();
		System.out.println(transaction);
		System.out.println(transaction.getWarningList());
		String errorMessage = "\r\n" + "Errors: " + transaction.getErrorList() + "\r\n";
		assertEquals(errorMessage, expectedResult, transaction.getValid());
	}
	public boolean applyNoCommitTransaction(EquationStandardTransaction transaction, boolean expectedResult) throws Exception
	{
		assertTestNoCommitTransaction(transaction, expectedResult);
		return (transaction.getValid() == expectedResult);
	}
}
