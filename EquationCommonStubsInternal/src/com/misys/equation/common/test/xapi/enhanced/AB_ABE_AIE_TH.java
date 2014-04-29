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

import com.misys.equation.common.internal.eapi.core.EQEnquiry;
import com.misys.equation.common.internal.eapi.core.EQList;
import com.misys.equation.common.internal.eapi.core.EQObject;
import com.misys.equation.common.internal.eapi.core.EQSession;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.Toolbox;

public class AB_ABE_AIE_TH
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AB_ABE_AIE_TH.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	EQSession session;
	EQEnquiry accountBalance;
	EQEnquiry accountBasicDetails;
	EQEnquiry accountInterest;
	EQEnquiry transactionHistory;
	String accountBranch = "0001";
	String accountBasicNumber = "100001";
	String accountSuffix = "001";
	public AB_ABE_AIE_TH()
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
			AB_ABE_AIE_TH test = new AB_ABE_AIE_TH();
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
			accountBalance = (EQEnquiry) session.createEQObject("AB ");
			accountBalance.setAuditUserID("Sm@rt");
			accountBalance.setFieldValue("ZLAB", accountBranch);
			accountBalance.setFieldValue("ZLAN", accountBasicNumber);
			accountBalance.setFieldValue("ZLAS", accountSuffix);
			accountBalance.retrieve(session);
			Toolbox.printEQObjectKeyFieldErrors(accountBalance);
			Toolbox.printEQObject(accountBalance);
			Toolbox.log("accountBalance");
			accountBasicDetails = (EQEnquiry) session.createEQObject("ABE");
			accountBasicDetails.setAuditUserID("Sm@rt");
			accountBasicDetails.setFieldValue("ZLAB", accountBranch);
			accountBasicDetails.setFieldValue("ZLAN", accountBasicNumber);
			accountBasicDetails.setFieldValue("ZLAS", accountSuffix);
			accountBasicDetails.retrieve(session);
			Toolbox.printEQObjectKeyFieldErrors(accountBasicDetails);
			Toolbox.printEQObject(accountBasicDetails);
			Toolbox.log("accountBasicDetails");
			accountInterest = (EQEnquiry) session.createEQObject("AIE");
			accountInterest.setAuditUserID("Sm@rt");
			accountInterest.setFieldValue("ZLAB", accountBranch);
			accountInterest.setFieldValue("ZLAN", accountBasicNumber);
			accountInterest.setFieldValue("ZLAS", accountSuffix);
			accountInterest.retrieve(session);
			Toolbox.printEQObjectKeyFieldErrors(accountInterest);
			Toolbox.printEQObject(accountInterest);
			Toolbox.log("accountInterest");
			transactionHistory = (EQEnquiry) session.createEQObject("TH ");
			transactionHistory.setAuditUserID("Sm@rt");
			transactionHistory.setFieldValue("ZLAB", accountBranch);
			transactionHistory.setFieldValue("ZLAN", accountBasicNumber);
			transactionHistory.setFieldValue("ZLAS", accountSuffix);
			transactionHistory.retrieve(session);
			Toolbox.printEQObjectKeyFieldErrors(transactionHistory);
			Toolbox.printEQObject(transactionHistory);
			EQList transactionHistoryList = transactionHistory.getList();
			while (transactionHistory.getStatus() == EQObject.STATUS_INCOMPLETE)
			{
				transactionHistory.retrieve(session);
			}
			Toolbox.printEQList(transactionHistoryList);
			Toolbox.log("transactionHistory");
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
