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

public class AIR2 extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AIR2.java 8213 2010-07-15 16:56:49Z CHALLIP1 $";
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;

	String paymentReference = "LOND00105X000183";
	String inputBrnmStr = "0543";
	String paymentType = "2CA";
	String receiveCurrency = "GBP";
	String payCurrency = "GBP";
	String remitterId = "FREDDY";
	String totalReceiveAmount = "500000";
	String receiveNostro = "GBP P";
	String payNostro = "GBP P";
	String sender = "s";
	String beneficiaryAccountLine = "/1234567890";
	String beneficiaryName = "Chris Weddell";
	String accountWithBranch = "//CN123456";
	String accountwithBICBank = "CNAB";
	String accountwithBICCountry = "CN";
	String accountwithBICLocation = "22";
	String accountwithBICSuffix = "XXX";
	String bankOpCode = "CRED";
	String chargesFor = "OUR";
	String payAccountBranch = "0543";
	String payAccountNumber = "123467";
	String payAccountSuffix = "001";
	String orderingCustomer = "Ordering Customer";
	String orderingCustomerAdd = "Ordering Customer Address";

	private String programName = "K61ARR";
	private String optionId = "AIR";

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
			EquationStandardTransaction addInwardCleanPayment = getTransaction();

			// Do the retrieve
			addInwardCleanPayment.setFieldValue("GZREF", paymentReference);
			addInwardCleanPayment.setFieldValue("GZRCCY", receiveCurrency);
			addInwardCleanPayment.setFieldValue("GZPCCY", payCurrency);
			addInwardCleanPayment.setFieldValue("GZPYT", paymentType);
			addInwardCleanPayment.setFieldValue("GZUSNL", session.getUserId());
			addInwardCleanPayment.setFieldValue("GZAPP", "CP");
			addInwardCleanPayment.setFieldValue("GZIAB", inputBrnmStr);
			addInwardCleanPayment.setFieldValue("GZPNMT", "250000");
			addInwardCleanPayment.setFieldValue("GZAMTE", "4");
			addInwardCleanPayment.setFieldValue("GZOAN7", "oan7");
			addInwardCleanPayment.setFieldValue("GZOAN9", "oan9");
			addInwardCleanPayment.setFieldValue("GZBAD1", "ben 1 address");
			addInwardCleanPayment.setFieldValue("GZBAD5", "ben 5 address");
			addInwardCleanPayment.setFieldValue("GZAAD1", "account 1 address");
			addInwardCleanPayment.setFieldValue("GZAAD5", "account 5 address");
			addInwardCleanPayment.setFieldValue("GZAB2", payAccountBranch);
			addInwardCleanPayment.setFieldValue("GZAN2", payAccountNumber);
			addInwardCleanPayment.setFieldValue("GZAS2", payAccountSuffix);
			addInwardCleanPayment.setFieldValue("GZOCA", orderingCustomer);
			addInwardCleanPayment.setFieldValue("GZOCA1", orderingCustomerAdd);

			// Do the retrieve invocation
			addInwardCleanPayment.setMode("R");
			addInwardCleanPayment.setByteCP("H");
			applyRetrieval(addInwardCleanPayment, true);
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
			EquationStandardTransaction addInwardCleanPayment = getTransaction();

			// **********************************************
			// Set the fields required for the validate call
			// **********************************************
			addInwardCleanPayment.setFieldValue("GZREF", paymentReference);
			addInwardCleanPayment.setFieldValue("GZRCCY", receiveCurrency);
			addInwardCleanPayment.setFieldValue("GZPCCY", payCurrency);
			addInwardCleanPayment.setFieldValue("GZPYT", paymentType);
			addInwardCleanPayment.setFieldValue("GZUSNL", session.getUserId());
			addInwardCleanPayment.setFieldValue("GZAPP", "CP");
			addInwardCleanPayment.setFieldValue("GZIAB", inputBrnmStr);
			addInwardCleanPayment.setFieldValue("GZPNMT", "250000");
			addInwardCleanPayment.setFieldValue("GZAMTE", "4");
			addInwardCleanPayment.setFieldValue("GZOAN7", "oan7");
			addInwardCleanPayment.setFieldValue("GZOAN9", "oan9");
			addInwardCleanPayment.setFieldValue("GZBAD1", "ben 1 address");
			addInwardCleanPayment.setFieldValue("GZBAD5", "ben 5 address");
			addInwardCleanPayment.setFieldValue("GZAAD1", "account 1 address");
			addInwardCleanPayment.setFieldValue("GZAAD5", "account 5 address");
			addInwardCleanPayment.setFieldValue("GZPYP", "CB");
			addInwardCleanPayment.setFieldValue("GZSAV", "N");
			addInwardCleanPayment.setFieldValue("GZNST1", receiveNostro);
			addInwardCleanPayment.setFieldValue("GZNST2", payNostro);
			addInwardCleanPayment.setFieldValue("GZAB2", payAccountBranch);
			addInwardCleanPayment.setFieldValue("GZAN2", payAccountNumber);
			addInwardCleanPayment.setFieldValue("GZAS2", payAccountSuffix);
			addInwardCleanPayment.setFieldValue("GZOCA", orderingCustomer);
			addInwardCleanPayment.setFieldValue("GZOCA1", orderingCustomerAdd);

			// Do the invocation
			addInwardCleanPayment.setMode("A");
			addInwardCleanPayment.setByteCP("H");
			applyNoCommitTransaction(addInwardCleanPayment, true);
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
			EquationStandardTransaction addInwardCleanPayment = getTransaction();

			// **********************************************
			// Set the fields required for the update call
			// **********************************************
			addInwardCleanPayment.setFieldValue("GZREF", paymentReference);
			addInwardCleanPayment.setFieldValue("GZRCCY", receiveCurrency);
			addInwardCleanPayment.setFieldValue("GZPCCY", payCurrency);
			addInwardCleanPayment.setFieldValue("GZPYT", paymentType);
			addInwardCleanPayment.setFieldValue("GZUSNL", session.getUserId());
			addInwardCleanPayment.setFieldValue("GZAPP", "CP");
			addInwardCleanPayment.setFieldValue("GZIAB", inputBrnmStr);
			addInwardCleanPayment.setFieldValue("GZPNMT", "250000");
			addInwardCleanPayment.setFieldValue("GZAMTE", "4");
			addInwardCleanPayment.setFieldValue("GZOAN7", "oan7");
			addInwardCleanPayment.setFieldValue("GZOAN9", "oan9");
			addInwardCleanPayment.setFieldValue("GZBAD1", "ben 1 address");
			addInwardCleanPayment.setFieldValue("GZBAD5", "ben 5 address");
			addInwardCleanPayment.setFieldValue("GZAAD1", "account 1 address");
			addInwardCleanPayment.setFieldValue("GZAAD5", "account 5 address");
			addInwardCleanPayment.setFieldValue("GZPYP", "CB");
			addInwardCleanPayment.setFieldValue("GZSAV", "N");
			addInwardCleanPayment.setFieldValue("GZAB2", payAccountBranch);
			addInwardCleanPayment.setFieldValue("GZAN2", payAccountNumber);
			addInwardCleanPayment.setFieldValue("GZAS2", payAccountSuffix);
			addInwardCleanPayment.setFieldValue("GZOCA", orderingCustomer);
			addInwardCleanPayment.setFieldValue("GZOCA1", orderingCustomerAdd);

			// Do the invocation
			addInwardCleanPayment.setMode("A");
			addInwardCleanPayment.setByteCP("Z");
			applyTransaction(addInwardCleanPayment, true);
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