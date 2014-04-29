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

public class AOP extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AOP.java 8213 2010-07-15 16:56:49Z CHALLIP1 $";
	String inputBrnmStr = "0543";
	String paymentType = "KBC";
	String receiveCurrency = "GBP";
	String payCurrency = "USD";
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
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
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
			EquationStandardTransaction addOutwardCleanPayment = getTransaction();

			// **********************************************
			// Set the fields required for the retrieval call
			// **********************************************
			addOutwardCleanPayment.setFieldValue("GZUSNL", session.getUserId()); // Logged by (4A)
			addOutwardCleanPayment.setFieldValue("GZAPP", "CP"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
			addOutwardCleanPayment.setFieldValue("GZIAB", inputBrnmStr); // Input branch (4A)
			addOutwardCleanPayment.setFieldValue("GZPYT", paymentType); // Payment type (3A)
			addOutwardCleanPayment.setFieldValue("GZNST1", receiveNostro); // Receive nostro (6A)
			addOutwardCleanPayment.setFieldValue("GZRCCY", receiveCurrency); // Receive currency (3A)
			addOutwardCleanPayment.setFieldValue("GZPCCY", payCurrency); // Pay currency (3A)
			addOutwardCleanPayment.setFieldValue("GZRAMT", totalReceiveAmount); // Receive total amount (15P,0)
			addOutwardCleanPayment.setFieldValue("GZAMTE", "1"); // Amount entered; 1=RTot,2=RNet,3=PTot,4=PNet (1A)

			// Do the invocation
			addOutwardCleanPayment.setMode("R");
			addOutwardCleanPayment.setByteCP("H");
			applyRetrieval(addOutwardCleanPayment, true);

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
			EquationStandardTransaction addOutwardCleanPayment = getTransaction();

			// **********************************************
			// Set the fields required for the charges call
			// **********************************************
			addOutwardCleanPayment.setFieldValue("GZUSNL", session.getUserId()); // Logged by (4A)
			addOutwardCleanPayment.setFieldValue("GZAPP", "CP"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
			addOutwardCleanPayment.setFieldValue("GZIAB", inputBrnmStr); // Input branch (4A)
			addOutwardCleanPayment.setFieldValue("GZPYT", paymentType); // Payment type (3A)
			addOutwardCleanPayment.setFieldValue("GZNST1", receiveNostro); // Receive nostro (6A)
			addOutwardCleanPayment.setFieldValue("GZRCCY", receiveCurrency); // Receive currency (3A)
			addOutwardCleanPayment.setFieldValue("GZPCCY", payCurrency); // Pay currency (3A)
			addOutwardCleanPayment.setFieldValue("GZRAMT", totalReceiveAmount); // Receive total amount (15P,0)
			addOutwardCleanPayment.setFieldValue("GZAMTE", "1"); // Amount entered; 1=RTot,2=RNet,3=PTot,4=PNet (1A)

			// Do the invocation
			addOutwardCleanPayment.setMode("R");
			addOutwardCleanPayment.setByteCP("C");
			applyRetrieval(addOutwardCleanPayment, true);

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
			EquationStandardTransaction addOutwardCleanPayment = getTransaction();

			// **********************************************
			// Set the fields required for the validate call
			// **********************************************
			addOutwardCleanPayment.setFieldValue("GZUSNL", session.getUserId()); // Logged by (4A)
			addOutwardCleanPayment.setFieldValue("GZAPP", "CP"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
			addOutwardCleanPayment.setFieldValue("GZIAB", inputBrnmStr); // Input branch (4A)
			addOutwardCleanPayment.setFieldValue("GZPYT", paymentType); // Payment type (3A)
			addOutwardCleanPayment.setFieldValue("GZNST1", receiveNostro); // Receive nostro (6A)
			addOutwardCleanPayment.setFieldValue("GZRCCY", receiveCurrency); // Receive currency (3A)
			addOutwardCleanPayment.setFieldValue("GZPCCY", payCurrency); // Pay currency (3A)
			addOutwardCleanPayment.setFieldValue("GZRAMT", totalReceiveAmount); // Receive total amount (15P,0)
			addOutwardCleanPayment.setFieldValue("GZAMTE", "1"); // Amount entered; 1=RTot,2=RNet,3=PTot,4=PNet (1A)
			addOutwardCleanPayment.setFieldValue("GZRMTR", sender); // Remitter or beneficiary id (35A)
			addOutwardCleanPayment.setFieldValue("GZSAD1", sender); // Sender name & address 1 (35A)
			addOutwardCleanPayment.setFieldValue("GZNST2", payNostro); // Pay nostro (6A)
			addOutwardCleanPayment.setFieldValue("GZOAN1", beneficiaryAccountLine); // Beneficiary a/c (34A)
			addOutwardCleanPayment.setFieldValue("GZBAD1", beneficiaryName); // Beneficiary name & address 1 (35A)
			addOutwardCleanPayment.setFieldValue("GZOAN2", accountWithBranch); // A/c with institution a/c (34A)
			addOutwardCleanPayment.setFieldValue("GZSWB3", accountwithBICBank); // A/c with institution SWIFT bank id (4A)
			addOutwardCleanPayment.setFieldValue("GZCNA3", accountwithBICCountry); // A/c with institution SWIFT country code (2A)
			addOutwardCleanPayment.setFieldValue("GZSWL3", accountwithBICLocation); // A/c with institution SWIFT location (2A)
			addOutwardCleanPayment.setFieldValue("GZSWR3", accountwithBICSuffix); // A/c with institution SWIFT branch id (3A)
			addOutwardCleanPayment.setFieldValue("GZBOPC", bankOpCode); // Bank operation code (4A)
			addOutwardCleanPayment.setFieldValue("GZCHRG", chargesFor); // Charges for BEN/OUR/SHA (3A)
			addOutwardCleanPayment.setFieldValue("GZSAV", "N"); // Save instructions? (1A)

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
	public void test00400Update()
	{
		try
		{
			EquationStandardTransaction addOutwardCleanPayment = getTransaction();

			// **********************************************
			// Set the fields required for the update call
			// **********************************************
			addOutwardCleanPayment.setFieldValue("GZUSNL", session.getUserId());
			addOutwardCleanPayment.setFieldValue("GZAPP", "CP");
			addOutwardCleanPayment.setFieldValue("GZIAB", inputBrnmStr);
			addOutwardCleanPayment.setFieldValue("GZPYT", paymentType);
			addOutwardCleanPayment.setFieldValue("GZNST1", receiveNostro);
			addOutwardCleanPayment.setFieldValue("GZRCCY", receiveCurrency);
			addOutwardCleanPayment.setFieldValue("GZPCCY", payCurrency);
			addOutwardCleanPayment.setFieldValue("GZRAMT", totalReceiveAmount);
			addOutwardCleanPayment.setFieldValue("GZAMTE", "1");
			addOutwardCleanPayment.setFieldValue("GZRMTR", sender);
			addOutwardCleanPayment.setFieldValue("GZSAD1", sender);
			addOutwardCleanPayment.setFieldValue("GZNST2", payNostro);
			addOutwardCleanPayment.setFieldValue("GZOAN1", beneficiaryAccountLine);
			addOutwardCleanPayment.setFieldValue("GZBAD1", beneficiaryName);
			addOutwardCleanPayment.setFieldValue("GZOAN2", accountWithBranch);
			addOutwardCleanPayment.setFieldValue("GZSWB3", accountwithBICBank);
			addOutwardCleanPayment.setFieldValue("GZCNA3", accountwithBICCountry);
			addOutwardCleanPayment.setFieldValue("GZSWL3", accountwithBICLocation);
			addOutwardCleanPayment.setFieldValue("GZSWR3", accountwithBICSuffix);
			addOutwardCleanPayment.setFieldValue("GZBOPC", bankOpCode);
			addOutwardCleanPayment.setFieldValue("GZCHRG", chargesFor);
			addOutwardCleanPayment.setFieldValue("GZSAV", "N");

			// Do the invocation
			addOutwardCleanPayment.setMode("A");
			addOutwardCleanPayment.setByteCP("Z");
			applyTransaction(addOutwardCleanPayment, true);

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
