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

public class AIR extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AIR.java 8213 2010-07-15 16:56:49Z CHALLIP1 $";
	String inputBrnmStr = "0543";
	String paymentType = "KIC";
	String receiveCurrency = "GBP";
	String payCurrency = "GBP";
	String remitterId = "FREDDY";
	String totalReceiveAmount = "500000";
	String receiveNostro = "GBP R";
	String payNostro = "USD P";
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

	long startTime = System.currentTimeMillis();
	long currentTime = startTime;

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

	public void test00100RetrieveDefaults()
	{
		try
		{
			// Get a new transaction for Clean Payment
			EquationStandardTransaction addInwardCleanPayment = getTransaction();

			// **********************************************
			// Set the fields required for the retrieval call
			// **********************************************
			addInwardCleanPayment.setFieldValue("GZUSNL", session.getUserId()); // Logged by (4A)
			addInwardCleanPayment.setFieldValue("GZAPP", "CP"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
			addInwardCleanPayment.setFieldValue("GZIAB", inputBrnmStr); // Input branch (4A)
			addInwardCleanPayment.setFieldValue("GZPYT", paymentType); // Payment type (3A)
			addInwardCleanPayment.setFieldValue("GZNST1", receiveNostro); // Receive nostro (6A)
			addInwardCleanPayment.setFieldValue("GZRCCY", receiveCurrency); // Receive currency (3A)
			addInwardCleanPayment.setFieldValue("GZPCCY", payCurrency); // Pay currency (3A)
			addInwardCleanPayment.setFieldValue("GZRAMT", totalReceiveAmount); // Receive total amount (15P,0)
			addInwardCleanPayment.setFieldValue("GZAMTE", "1"); // Amount entered; 1=RTot,2=RNet,3=PTot,4=PNet (1A)

			addInwardCleanPayment.setFieldValue("GZAB2", payAccountBranch); // Pay a/c branch (4A)
			addInwardCleanPayment.setFieldValue("GZAN2", payAccountNumber); // Pay a/c number (6A)
			addInwardCleanPayment.setFieldValue("GZAS2", payAccountSuffix); // Pay a/c suffix (3A)
			addInwardCleanPayment.setFieldValue("GZOCA", orderingCustomer); // Ordering Customer Account Number (34A)
			addInwardCleanPayment.setFieldValue("GZOCA1", orderingCustomerAdd); // Ordering customer name & address 1 (35A)

			// Do the invocation
			addInwardCleanPayment.setMode("R");
			addInwardCleanPayment.setByteCP("H");
			applyRetrieval(addInwardCleanPayment, true);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void test00200Charges()
	{
		try
		{
			EquationStandardTransaction addInwardCleanPayment = getTransaction();

			// **********************************************
			// Set the fields required for the charges call
			// **********************************************
			addInwardCleanPayment.setFieldValue("GZUSNL", session.getUserId()); // Logged by (4A)
			addInwardCleanPayment.setFieldValue("GZAPP", "CP"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
			addInwardCleanPayment.setFieldValue("GZIAB", inputBrnmStr); // Input branch (4A)
			addInwardCleanPayment.setFieldValue("GZPYT", paymentType); // Payment type (3A)
			addInwardCleanPayment.setFieldValue("GZNST1", receiveNostro); // Receive nostro (6A)
			addInwardCleanPayment.setFieldValue("GZRCCY", receiveCurrency); // Receive currency (3A)
			addInwardCleanPayment.setFieldValue("GZPCCY", payCurrency); // Pay currency (3A)
			addInwardCleanPayment.setFieldValue("GZRAMT", totalReceiveAmount); // Receive total amount (15P,0)
			addInwardCleanPayment.setFieldValue("GZAMTE", "1"); // Amount entered; 1=RTot,2=RNet,3=PTot,4=PNet (1A)

			// Do the invocation
			addInwardCleanPayment.setMode("R");
			addInwardCleanPayment.setByteCP("C");
			applyRetrieval(addInwardCleanPayment, true);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void test00300Validate()
	{
		try
		{
			EquationStandardTransaction addInwardCleanPayment = getTransaction();

			// **********************************************
			// Set the fields required for the validate call
			// **********************************************
			addInwardCleanPayment.setFieldValue("GZUSNL", session.getUserId()); // Logged by (4A)
			addInwardCleanPayment.setFieldValue("GZAPP", "CP"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
			addInwardCleanPayment.setFieldValue("GZIAB", inputBrnmStr); // Input branch (4A)
			addInwardCleanPayment.setFieldValue("GZPYT", paymentType); // Payment type (3A)
			addInwardCleanPayment.setFieldValue("GZNST1", receiveNostro); // Receive nostro (6A)
			addInwardCleanPayment.setFieldValue("GZRCCY", receiveCurrency); // Receive currency (3A)
			addInwardCleanPayment.setFieldValue("GZPCCY", payCurrency); // Pay currency (3A)
			addInwardCleanPayment.setFieldValue("GZRAMT", totalReceiveAmount); // Receive total amount (15P,0)
			addInwardCleanPayment.setFieldValue("GZAMTE", "1"); // Amount entered; 1=RTot,2=RNet,3=PTot,4=PNet (1A)
			addInwardCleanPayment.setFieldValue("GZRMTR", sender); // Remitter or beneficiary id (35A)
			addInwardCleanPayment.setFieldValue("GZSAD1", sender); // Sender name & address 1 (35A)
			addInwardCleanPayment.setFieldValue("GZNST2", payNostro); // Pay nostro (6A)
			addInwardCleanPayment.setFieldValue("GZOAN1", beneficiaryAccountLine); // Beneficiary a/c (34A)
			addInwardCleanPayment.setFieldValue("GZBAD1", beneficiaryName); // Beneficiary name & address 1 (35A)
			addInwardCleanPayment.setFieldValue("GZOAN2", accountWithBranch); // A/c with institution a/c (34A)
			addInwardCleanPayment.setFieldValue("GZSWB3", accountwithBICBank); // A/c with institution SWIFT bank id (4A)
			addInwardCleanPayment.setFieldValue("GZCNA3", accountwithBICCountry); // A/c with institution SWIFT country code (2A)
			addInwardCleanPayment.setFieldValue("GZSWL3", accountwithBICLocation); // A/c with institution SWIFT location (2A)
			addInwardCleanPayment.setFieldValue("GZSWR3", accountwithBICSuffix); // A/c with institution SWIFT branch id (3A)
			addInwardCleanPayment.setFieldValue("GZBOPC", bankOpCode); // Bank operation code (4A)
			addInwardCleanPayment.setFieldValue("GZCHRG", chargesFor); // Charges for BEN/OUR/SHA (3A)
			addInwardCleanPayment.setFieldValue("GZSAV", "N"); // Save instructions? (1A)
			addInwardCleanPayment.setFieldValue("GZAB2", payAccountBranch); // Pay a/c branch (4A)
			addInwardCleanPayment.setFieldValue("GZAN2", payAccountNumber); // Pay a/c number (6A)
			addInwardCleanPayment.setFieldValue("GZAS2", payAccountSuffix); // Pay a/c suffix (3A)
			addInwardCleanPayment.setFieldValue("GZOCA", orderingCustomer); // Ordering Customer Account Number (34A)
			addInwardCleanPayment.setFieldValue("GZOCA1", orderingCustomerAdd); // Ordering customer name & address 1 (35A)

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
	public void test00400Update()
	{
		try
		{
			EquationStandardTransaction addInwardCleanPayment = getTransaction();

			// **********************************************
			// Set the fields required for the update call
			// **********************************************
			addInwardCleanPayment.setFieldValue("GZUSNL", session.getUserId()); // Logged by (4A)
			addInwardCleanPayment.setFieldValue("GZAPP", "CP"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
			addInwardCleanPayment.setFieldValue("GZIAB", inputBrnmStr); // Input branch (4A)
			addInwardCleanPayment.setFieldValue("GZPYT", paymentType); // Payment type (3A)
			addInwardCleanPayment.setFieldValue("GZNST1", receiveNostro); // Receive nostro (6A)
			addInwardCleanPayment.setFieldValue("GZRCCY", receiveCurrency); // Receive currency (3A)
			addInwardCleanPayment.setFieldValue("GZPCCY", payCurrency); // Pay currency (3A)
			addInwardCleanPayment.setFieldValue("GZRAMT", totalReceiveAmount); // Receive total amount (15P,0)
			addInwardCleanPayment.setFieldValue("GZAMTE", "1"); // Amount entered; 1=RTot,2=RNet,3=PTot,4=PNet (1A)
			addInwardCleanPayment.setFieldValue("GZRMTR", sender); // Remitter or beneficiary id (35A)
			addInwardCleanPayment.setFieldValue("GZSAD1", sender); // Sender name & address 1 (35A)
			addInwardCleanPayment.setFieldValue("GZNST2", payNostro); // Pay nostro (6A)
			addInwardCleanPayment.setFieldValue("GZOAN1", beneficiaryAccountLine); // Beneficiary a/c (34A)
			addInwardCleanPayment.setFieldValue("GZBAD1", beneficiaryName); // Beneficiary name & address 1 (35A)
			addInwardCleanPayment.setFieldValue("GZOAN2", accountWithBranch); // A/c with institution a/c (34A)
			addInwardCleanPayment.setFieldValue("GZSWB3", accountwithBICBank); // A/c with institution SWIFT bank id (4A)
			addInwardCleanPayment.setFieldValue("GZCNA3", accountwithBICCountry); // A/c with institution SWIFT country code (2A)
			addInwardCleanPayment.setFieldValue("GZSWL3", accountwithBICLocation); // A/c with institution SWIFT location (2A)
			addInwardCleanPayment.setFieldValue("GZSWR3", accountwithBICSuffix); // A/c with institution SWIFT branch id (3A)
			addInwardCleanPayment.setFieldValue("GZBOPC", bankOpCode); // Bank operation code (4A)
			addInwardCleanPayment.setFieldValue("GZCHRG", chargesFor); // Charges for BEN/OUR/SHA (3A)
			addInwardCleanPayment.setFieldValue("GZSAV", "N"); // Save instructions? (1A)
			addInwardCleanPayment.setFieldValue("GZAB2", payAccountBranch); // Pay a/c branch (4A)
			addInwardCleanPayment.setFieldValue("GZAN2", payAccountNumber); // Pay a/c number (6A)
			addInwardCleanPayment.setFieldValue("GZAS2", payAccountSuffix); // Pay a/c suffix (3A)
			addInwardCleanPayment.setFieldValue("GZOCA", orderingCustomer); // Ordering Customer Account Number (34A)
			addInwardCleanPayment.setFieldValue("GZOCA1", orderingCustomerAdd); // Ordering customer name & address 1 (35A)

			// Do the invocation
			addInwardCleanPayment.setMode("A");
			addInwardCleanPayment.setByteCP("Z");
			applyTransaction(addInwardCleanPayment, true);

			System.out.println("finished!");
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
