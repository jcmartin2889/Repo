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
package com.misys.equation.common.test.xapi.enhanced;

import com.misys.equation.common.internal.eapi.core.EQSession;
import com.misys.equation.common.internal.eapi.core.EQTransaction;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.Toolbox;

public class AOP
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AOP.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	EQSession session;
	EQTransaction addOutwardCleanPayment;
	String paymentType = "T01";
	String receiveCurrency = "CNY";
	String totalReceiveAmount = "500000";
	String receiveAccountBranch = "0001";
	String receiveAccountBasicNumber = "100001";
	String receiveAccountSuffix = "001";
	String beneficiaryAccountLine = "/1234567890";
	String beneficiaryName = "Tong Qing";
	String accountWithBranch = "//CN123456";
	String accountwithBICBank = "CNAB";
	String accountwithBICCountry = "CN";
	String accountwithBICLocation = "22";
	String accountwithBICSuffix = "XXX";
	String bankOpCode = "CRED";
	String chargesFor = "OUR";
	String saveInstructions = "N";
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	public AOP()
	{
		try
		{
			session = TestEnvironment.getTestEnvironment().getEnhancedAPISession();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		try
		{
			AOP test = new AOP();
			test.test();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public void test()
	{
		try
		{
			// Got this far so we may as well have a bash at a couple of transactions...
			addOutwardCleanPayment = (EQTransaction) session.createEQObject("AOP");
			for (int i = 0; i < 1; i++)
			{
				addOutwardCleanPayment.reset();
				addOutwardCleanPayment.setAuditUserID("Sm@rt");
				addOutwardCleanPayment.setReference("TESTAOP");
				// Set the fields
				addOutwardCleanPayment.setFieldValue("ZLPYT", paymentType);
				addOutwardCleanPayment.setFieldValue("ZLRCCY", receiveCurrency);
				addOutwardCleanPayment.setFieldValue("ZLRTAI", totalReceiveAmount);
				addOutwardCleanPayment.setFieldValue("ZLAB", receiveAccountBranch);
				addOutwardCleanPayment.setFieldValue("ZLAN", receiveAccountBasicNumber);
				addOutwardCleanPayment.setFieldValue("ZLAS", receiveAccountSuffix);
				addOutwardCleanPayment.setFieldValue("ZLOAN1", beneficiaryAccountLine);
				addOutwardCleanPayment.setFieldValue("ZLBAD1", beneficiaryName);
				addOutwardCleanPayment.setFieldValue("ZLOAN2", accountWithBranch);
				addOutwardCleanPayment.setFieldValue("ZLSWB3", accountwithBICBank);
				addOutwardCleanPayment.setFieldValue("ZLCNA3", accountwithBICCountry);
				addOutwardCleanPayment.setFieldValue("ZLSWL3", accountwithBICLocation);
				addOutwardCleanPayment.setFieldValue("ZLSWR3", accountwithBICSuffix);
				addOutwardCleanPayment.setFieldValue("ZLBOPC", bankOpCode);
				addOutwardCleanPayment.setFieldValue("ZLCHRG", chargesFor);
				addOutwardCleanPayment.setFieldValue("ZLSAV", saveInstructions);
				// retrieve default values
				addOutwardCleanPayment.add(session, EQTransaction.ACTION_VALIDATE);
				// ************
				Toolbox.printEQObject(addOutwardCleanPayment);
				Toolbox.log("validate");
				Toolbox.printFieldMessages(addOutwardCleanPayment.getMessages());
				// ************
				addOutwardCleanPayment.setAutoOverride(true);
				addOutwardCleanPayment.add(session, EQTransaction.ACTION_UPDATE);
				// ************
				Toolbox.log("update");
				Toolbox.printFieldMessages(addOutwardCleanPayment.getMessages());
				// ************
			}
			// now we are in a position to commit ...
			session.commit();
			// ************
			Toolbox.log("Commiting");
			// ************
			// log out
			session.logOff();
			// ************
			Toolbox.log("Returning the session");
			// ************
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
