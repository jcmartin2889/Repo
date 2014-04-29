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
public class AQI
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AQI.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	String inputBrnmStr = "0543";
	String paymentType = "1BN";
	String templateReference = "1BNGBPUSD";
	String payAccountBranch = "0543";
	String payAccountNumber = "132755";
	String payAccountSuffix = "040";
	// String receiveCurrency = "GBP";
	// String payCurrency = "USD";
	// String remitterId = "FREDDY";
	// String totalReceiveAmount = "500000";
	// String receiveNostro = "GBP R";
	// String payNostro = "USD P";
	// String sender = "s";
	// String beneficiaryAccountLine = "/1234567890";
	// String beneficiaryName = "Chris Weddell";
	// String accountWithBranch = "//CN123456";
	// String accountwithBICBank = "CNAB";
	// String accountwithBICCountry = "CN";
	// String accountwithBICLocation = "22";
	// String accountwithBICSuffix = "XXX";
	// String bankOpCode = "CRED";
	// String chargesFor = "OUR";
	EquationStandardSession session;
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	private AQI()
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
		AQI test = new AQI();
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
			// Get a new transaction for Clean Payment
			EquationStandardTransaction addQuickInwardCleanPayment = new EquationStandardTransaction("K61ARRAQI", session);

			// **********************************************
			// Set the fields required for the retrieval call
			// **********************************************
			addQuickInwardCleanPayment.setFieldValue("GZUSNL", session.getUserId());
			// addOutwardCleanPayment.setField("GZAPP", "CP");
			addQuickInwardCleanPayment.setFieldValue("GZIAB", inputBrnmStr);
			addQuickInwardCleanPayment.setFieldValue("GZTSET", templateReference);
			addQuickInwardCleanPayment.setFieldValue("GZPYT", paymentType);
			addQuickInwardCleanPayment.setFieldValue("GZAB2", "payAccountBranch"); // Pay a/c branch (4A)
			addQuickInwardCleanPayment.setFieldValue("GZAN2", "payAccountNumber"); // Pay a/c number (6A)
			addQuickInwardCleanPayment.setFieldValue("GZAS2", "payAccountSuffix"); // Pay a/c suffix (3A)

			// Do the invocation
			addQuickInwardCleanPayment.setMode("R");
			addQuickInwardCleanPayment.setByteCP("H");
			session.retrieveEquationTransaction(addQuickInwardCleanPayment);
			Toolbox.printMessages(addQuickInwardCleanPayment.getErrorList());
			Toolbox.printMessages(addQuickInwardCleanPayment.getWarningList());

			// Do the invocation
			// addOutwardCleanPayment.setJTT("R");
			// addOutwardCleanPayment.setByte4000("C");
			// session.retrieveEquationTransaction(addOutwardCleanPayment);
			// Toolbox.printMessages(addOutwardCleanPayment.getErrorList());
			// Toolbox.printMessages(addOutwardCleanPayment.getWarningList());

			// **********************************************
			// Set the fields required for the validate call
			// **********************************************
			// addOutwardCleanPayment.setField("GZRMTR", sender);
			// addOutwardCleanPayment.setField("GZSAD1", sender);
			// addOutwardCleanPayment.setField("GZNST2", payNostro);
			// addOutwardCleanPayment.setField("GZOAN1", beneficiaryAccountLine);
			// addOutwardCleanPayment.setField("GZBAD1", beneficiaryName);
			// addOutwardCleanPayment.setField("GZOAN2", accountWithBranch);
			// addOutwardCleanPayment.setField("GZSWB3", accountwithBICBank);
			// addOutwardCleanPayment.setField("GZCNA3", accountwithBICCountry);
			// addOutwardCleanPayment.setField("GZSWL3", accountwithBICLocation);
			// addOutwardCleanPayment.setField("GZSWR3", accountwithBICSuffix);
			// addOutwardCleanPayment.setField("GZBOPC", bankOpCode);
			// addOutwardCleanPayment.setField("GZCHRG", chargesFor);
			// addOutwardCleanPayment.setField("GZSAV", "N");

			// Do the invocation
			addQuickInwardCleanPayment.setMode("A");
			addQuickInwardCleanPayment.setByteCP("H");
			session.addEquationTransaction(addQuickInwardCleanPayment);
			session.applyTransactions();
			Toolbox.printMessages(addQuickInwardCleanPayment.getErrorList());
			Toolbox.printMessages(addQuickInwardCleanPayment.getWarningList());

			// **********************************************
			// Set the fields required for the update call
			// **********************************************

			// Do the invocation
			addQuickInwardCleanPayment.setMode("A");
			addQuickInwardCleanPayment.setByteCP("Z");
			session.addEquationTransaction(addQuickInwardCleanPayment);
			session.applyTransactions();
			Toolbox.printMessages(addQuickInwardCleanPayment.getErrorList());
			Toolbox.printMessages(addQuickInwardCleanPayment.getWarningList());

			// **********************************************
			// Finish
			// **********************************************
			System.out.println("finished!");
			session.commitTransactions();
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
