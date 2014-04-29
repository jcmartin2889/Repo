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
public class AOP
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AOP.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
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
	EquationStandardSession session;
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	private AOP()
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
		AOP test = new AOP();
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
			EquationStandardTransaction addOutwardCleanPayment = new EquationStandardTransaction("K61ARRAOP", session);

			// **********************************************
			// Set the fields required for the retrieval call
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

			// Do the invocation
			addOutwardCleanPayment.setMode("R");
			addOutwardCleanPayment.setByteCP("H");
			session.retrieveEquationTransaction(addOutwardCleanPayment);
			Toolbox.printMessages(addOutwardCleanPayment.getErrorList());
			Toolbox.printMessages(addOutwardCleanPayment.getWarningList());

			// Do the invocation
			addOutwardCleanPayment.setMode("R");
			addOutwardCleanPayment.setByteCP("C");
			session.retrieveEquationTransaction(addOutwardCleanPayment);
			Toolbox.printMessages(addOutwardCleanPayment.getErrorList());
			Toolbox.printMessages(addOutwardCleanPayment.getWarningList());

			// **********************************************
			// Set the fields required for the validate call
			// **********************************************
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
			addOutwardCleanPayment.setByteCP("H");
			session.addEquationTransaction(addOutwardCleanPayment);
			session.applyTransactions();
			Toolbox.printMessages(addOutwardCleanPayment.getErrorList());
			Toolbox.printMessages(addOutwardCleanPayment.getWarningList());

			// **********************************************
			// Set the fields required for the update call
			// **********************************************

			// Do the invocation
			addOutwardCleanPayment.setMode("A");
			addOutwardCleanPayment.setByteCP("Z");
			session.addEquationTransaction(addOutwardCleanPayment);
			session.applyTransactions();
			Toolbox.printMessages(addOutwardCleanPayment.getErrorList());
			Toolbox.printMessages(addOutwardCleanPayment.getWarningList());

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
