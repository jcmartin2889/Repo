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

public class RIP2 extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RIP2.java 8213 2010-07-15 16:56:49Z CHALLIP1 $";
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;

	String paymentReference = "LOND00105X000183";
	String inputBrnmStr = "0543";
	String paymentType = "2CA";
	String receiveCurrency = "GBP";
	String payCurrency = "GBP";
	String remitterId = "FREDDY";
	String totalReceiveAmount = "500000";
	String receiveNostro = "GBP R";
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

	private String programName = "K61MRR";
	private String optionId = "RIP";

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
			EquationStandardTransaction reviewInwardCleanPayment = getTransaction();

			reviewInwardCleanPayment.setFieldValue("GZUSNL", session.getUserId());
			reviewInwardCleanPayment.setFieldValue("GZREF", paymentReference);
			reviewInwardCleanPayment.setFieldValue("GZPYT", paymentType);
			reviewInwardCleanPayment.setFieldValue("GZUSNR", session.getUserId());
			reviewInwardCleanPayment.setFieldValue("GZAPP", "CP");
			reviewInwardCleanPayment.setFieldValue("GZIAB", inputBrnmStr);
			reviewInwardCleanPayment.setFieldValue("GZNST1", receiveNostro);
			reviewInwardCleanPayment.setFieldValue("GZRCCY", receiveCurrency);
			reviewInwardCleanPayment.setFieldValue("GZPCCY", payCurrency);
			reviewInwardCleanPayment.setFieldValue("GZRAMT", totalReceiveAmount);
			reviewInwardCleanPayment.setFieldValue("GZAMTE", "1");
			reviewInwardCleanPayment.setFieldValue("GZNTR", "N");
			reviewInwardCleanPayment.setFieldValue("GZPMC", "N");
			reviewInwardCleanPayment.setFieldValue("GZBAR", "N");
			reviewInwardCleanPayment.setFieldValue("GZPRVW", "N");
			reviewInwardCleanPayment.setFieldValue("GZRMTR", sender);
			reviewInwardCleanPayment.setFieldValue("GZSAD1", sender);
			reviewInwardCleanPayment.setFieldValue("GZNST2", payNostro);
			reviewInwardCleanPayment.setFieldValue("GZOAN1", beneficiaryAccountLine);
			reviewInwardCleanPayment.setFieldValue("GZBAD1", beneficiaryName);
			reviewInwardCleanPayment.setFieldValue("GZOAN2", accountWithBranch);
			reviewInwardCleanPayment.setFieldValue("GZSWB3", accountwithBICBank);
			reviewInwardCleanPayment.setFieldValue("GZCNA3", accountwithBICCountry);
			reviewInwardCleanPayment.setFieldValue("GZSWL3", accountwithBICLocation);
			reviewInwardCleanPayment.setFieldValue("GZSWR3", accountwithBICSuffix);
			reviewInwardCleanPayment.setFieldValue("GZBOPC", bankOpCode);
			reviewInwardCleanPayment.setFieldValue("GZCHRG", chargesFor);
			reviewInwardCleanPayment.setFieldValue("GZSAV", "N");
			reviewInwardCleanPayment.setFieldValue("GZPNMT", "250000");
			reviewInwardCleanPayment.setFieldValue("GZAMTE", "4");
			reviewInwardCleanPayment.setFieldValue("GZOAN7", "oan7");
			reviewInwardCleanPayment.setFieldValue("GZOAN9", "oan9");
			reviewInwardCleanPayment.setFieldValue("GZBAD1", "ben 1 address");
			reviewInwardCleanPayment.setFieldValue("GZBAD5", "ben 5 address");
			reviewInwardCleanPayment.setFieldValue("GZAAD1", "account 1 address");
			reviewInwardCleanPayment.setFieldValue("GZAAD5", "account 5 address");

			// Do the retrieve invocation
			reviewInwardCleanPayment.setByteCP("H");
			applyRetrieval(reviewInwardCleanPayment, true);
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
			EquationStandardTransaction reviewInwardCleanPayment = getTransaction();

			reviewInwardCleanPayment.setFieldValue("GZUSNL", session.getUserId());
			reviewInwardCleanPayment.setFieldValue("GZREF", paymentReference);
			reviewInwardCleanPayment.setFieldValue("GZPYT", paymentType);
			reviewInwardCleanPayment.setFieldValue("GZUSNR", session.getUserId());
			reviewInwardCleanPayment.setFieldValue("GZAPP", "CP");
			reviewInwardCleanPayment.setFieldValue("GZIAB", inputBrnmStr);
			reviewInwardCleanPayment.setFieldValue("GZNST1", receiveNostro);
			reviewInwardCleanPayment.setFieldValue("GZRCCY", receiveCurrency);
			reviewInwardCleanPayment.setFieldValue("GZPCCY", payCurrency);
			reviewInwardCleanPayment.setFieldValue("GZRAMT", totalReceiveAmount);
			reviewInwardCleanPayment.setFieldValue("GZAMTE", "1");
			reviewInwardCleanPayment.setFieldValue("GZNTR", "N");
			reviewInwardCleanPayment.setFieldValue("GZPMC", "N");
			reviewInwardCleanPayment.setFieldValue("GZBAR", "N");
			reviewInwardCleanPayment.setFieldValue("GZPRVW", "N");
			reviewInwardCleanPayment.setFieldValue("GZRMTR", sender);
			reviewInwardCleanPayment.setFieldValue("GZSAD1", sender);
			reviewInwardCleanPayment.setFieldValue("GZNST2", payNostro);
			reviewInwardCleanPayment.setFieldValue("GZOAN1", beneficiaryAccountLine);
			reviewInwardCleanPayment.setFieldValue("GZBAD1", beneficiaryName);
			reviewInwardCleanPayment.setFieldValue("GZOAN2", accountWithBranch);
			reviewInwardCleanPayment.setFieldValue("GZSWB3", accountwithBICBank);
			reviewInwardCleanPayment.setFieldValue("GZCNA3", accountwithBICCountry);
			reviewInwardCleanPayment.setFieldValue("GZSWL3", accountwithBICLocation);
			reviewInwardCleanPayment.setFieldValue("GZSWR3", accountwithBICSuffix);
			reviewInwardCleanPayment.setFieldValue("GZBOPC", bankOpCode);
			reviewInwardCleanPayment.setFieldValue("GZCHRG", chargesFor);
			reviewInwardCleanPayment.setFieldValue("GZSAV", "N");
			reviewInwardCleanPayment.setFieldValue("GZSNRF", sender);
			reviewInwardCleanPayment.setFieldValue("GZPYP", "CB");
			reviewInwardCleanPayment.setFieldValue("GZCTT", "2");
			reviewInwardCleanPayment.setFieldValue("GZPNMT", "250000");
			reviewInwardCleanPayment.setFieldValue("GZAMTE", "4");
			reviewInwardCleanPayment.setFieldValue("GZOAN7", "oan7");
			reviewInwardCleanPayment.setFieldValue("GZOAN9", "oan9");
			reviewInwardCleanPayment.setFieldValue("GZBAD1", "ben 1 address");
			reviewInwardCleanPayment.setFieldValue("GZBAD5", "ben 5 address");
			reviewInwardCleanPayment.setFieldValue("GZAAD1", "account 1 address");
			reviewInwardCleanPayment.setFieldValue("GZAAD5", "account 5 address");
			reviewInwardCleanPayment.setFieldValue("GZAB2", payAccountBranch);
			reviewInwardCleanPayment.setFieldValue("GZAN2", payAccountNumber);
			reviewInwardCleanPayment.setFieldValue("GZAS2", payAccountSuffix);
			reviewInwardCleanPayment.setFieldValue("GZOCA", orderingCustomer);
			reviewInwardCleanPayment.setFieldValue("GZOCA1", orderingCustomerAdd);

			// Do the invocation
			reviewInwardCleanPayment.setMode("M");
			reviewInwardCleanPayment.setByteCP("H");
			applyNoCommitTransaction(reviewInwardCleanPayment, true);
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
			EquationStandardTransaction reviewInwardCleanPayment = getTransaction();

			reviewInwardCleanPayment.setFieldValue("GZUSNL", session.getUserId());
			reviewInwardCleanPayment.setFieldValue("GZREF", paymentReference);
			reviewInwardCleanPayment.setFieldValue("GZPYT", paymentType);
			reviewInwardCleanPayment.setFieldValue("GZUSNR", session.getUserId());
			reviewInwardCleanPayment.setFieldValue("GZAPP", "CP");
			reviewInwardCleanPayment.setFieldValue("GZIAB", inputBrnmStr);
			reviewInwardCleanPayment.setFieldValue("GZNST1", receiveNostro);
			reviewInwardCleanPayment.setFieldValue("GZRCCY", receiveCurrency);
			reviewInwardCleanPayment.setFieldValue("GZPCCY", payCurrency);
			reviewInwardCleanPayment.setFieldValue("GZRAMT", totalReceiveAmount);
			reviewInwardCleanPayment.setFieldValue("GZAMTE", "1");
			reviewInwardCleanPayment.setFieldValue("GZNTR", "N");
			reviewInwardCleanPayment.setFieldValue("GZPMC", "N");
			reviewInwardCleanPayment.setFieldValue("GZBAR", "N");
			reviewInwardCleanPayment.setFieldValue("GZPRVW", "N");
			reviewInwardCleanPayment.setFieldValue("GZRMTR", sender);
			reviewInwardCleanPayment.setFieldValue("GZSAD1", sender);
			reviewInwardCleanPayment.setFieldValue("GZNST2", payNostro);
			reviewInwardCleanPayment.setFieldValue("GZOAN1", beneficiaryAccountLine);
			reviewInwardCleanPayment.setFieldValue("GZBAD1", beneficiaryName);
			reviewInwardCleanPayment.setFieldValue("GZOAN2", accountWithBranch);
			reviewInwardCleanPayment.setFieldValue("GZSWB3", accountwithBICBank);
			reviewInwardCleanPayment.setFieldValue("GZCNA3", accountwithBICCountry);
			reviewInwardCleanPayment.setFieldValue("GZSWL3", accountwithBICLocation);
			reviewInwardCleanPayment.setFieldValue("GZSWR3", accountwithBICSuffix);
			reviewInwardCleanPayment.setFieldValue("GZBOPC", bankOpCode);
			reviewInwardCleanPayment.setFieldValue("GZCHRG", chargesFor);
			reviewInwardCleanPayment.setFieldValue("GZSAV", "N");

			reviewInwardCleanPayment.setFieldValue("GZPNMT", "250000");
			reviewInwardCleanPayment.setFieldValue("GZAMTE", "4");
			reviewInwardCleanPayment.setFieldValue("GZOAN7", "oan7");
			reviewInwardCleanPayment.setFieldValue("GZOAN9", "oan9");
			reviewInwardCleanPayment.setFieldValue("GZBAD1", "ben 1 address");
			reviewInwardCleanPayment.setFieldValue("GZBAD5", "ben 5 address");
			reviewInwardCleanPayment.setFieldValue("GZAAD1", "account 1 address");
			reviewInwardCleanPayment.setFieldValue("GZAAD5", "account 5 address");
			reviewInwardCleanPayment.setFieldValue("GZAB2", payAccountBranch);
			reviewInwardCleanPayment.setFieldValue("GZAN2", payAccountNumber);
			reviewInwardCleanPayment.setFieldValue("GZAS2", payAccountSuffix);
			reviewInwardCleanPayment.setFieldValue("GZOCA", orderingCustomer);
			reviewInwardCleanPayment.setFieldValue("GZOCA1", orderingCustomerAdd);

			// Do the invocation
			reviewInwardCleanPayment.setMode("M");
			reviewInwardCleanPayment.setByteCP("Z");
			applyTransaction(reviewInwardCleanPayment, true);
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
