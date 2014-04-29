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

public class ASI
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ASI.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	EQSession session;
	EQTransaction debitAddSundryItem;
	EQTransaction creditAddSundryItem;
	String debitAccountBranch = "0543";
	String debitAccountBasicNumber = "012826";
	String debitAccountSuffix = "002";
	String debitCurrency = "GBP";
	String debitTransactionCode = "010";
	String creditAccountBranch = "0543";
	String creditAccountBasicNumber = "012826";
	String creditAccountSuffix = "001";
	String creditCurrency = "GBP";
	String creditTransactionCode = "510";
	String amount = "10000";
	String valueDate = "010300";
	String reference = "MISSLI";
	String chargeAmount = "100";
	String chargeCode = "AC";
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	public ASI()
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
			ASI test = new ASI();
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
			debitAddSundryItem = (EQTransaction) session.createEQObject("ASI");
			for (int i = 0; i < 1; i++)
			{
				debitAddSundryItem.reset();
				debitAddSundryItem.setAuditUserID("Sm@rt");
				debitAddSundryItem.setReference(reference);
				// Set the fields
				debitAddSundryItem.setFieldValue("ZLAB", debitAccountBranch);
				debitAddSundryItem.setFieldValue("ZLAN", debitAccountBasicNumber);
				debitAddSundryItem.setFieldValue("ZLAS", debitAccountSuffix);
				debitAddSundryItem.setFieldValue("ZLTCCY", debitCurrency);
				debitAddSundryItem.setFieldValue("ZLTAMZ", amount);
				debitAddSundryItem.setFieldValue("ZLTCD", debitTransactionCode);
				debitAddSundryItem.setFieldValue("ZLVFRZ", valueDate);
				debitAddSundryItem.setFieldValue("ZLDRF", reference);
				// retrieve default values
				debitAddSundryItem.add(session, EQTransaction.ACTION_VALIDATE);
				// ************
				Toolbox.log("Debit validate");
				// ************
				debitAddSundryItem.setAutoOverride(true);
				debitAddSundryItem.add(session, EQTransaction.ACTION_UPDATE);
				// ************
				Toolbox.log("Debit update");
				// ************
				System.out.println("Debit errors   :" + debitAddSundryItem.getMessages());
				System.out.println("Debit warnings :" + debitAddSundryItem.getOverrides());
			}
			// Got this far so we may as well have a bash at a couple of transactions...
			creditAddSundryItem = (EQTransaction) session.createEQObject("ASI");
			for (int i = 0; i < 1; i++)
			{
				creditAddSundryItem.reset();
				creditAddSundryItem.setAuditUserID("Sm@rt");
				creditAddSundryItem.setReference(reference);
				// Set the fields
				creditAddSundryItem.setFieldValue("ZLAB", creditAccountBranch);
				creditAddSundryItem.setFieldValue("ZLAN", creditAccountBasicNumber);
				creditAddSundryItem.setFieldValue("ZLAS", creditAccountSuffix);
				creditAddSundryItem.setFieldValue("ZLTCCY", creditCurrency);
				creditAddSundryItem.setFieldValue("ZLTAMZ", amount);
				creditAddSundryItem.setFieldValue("ZLTCD", creditTransactionCode);
				creditAddSundryItem.setFieldValue("ZLVFRZ", valueDate);
				creditAddSundryItem.setFieldValue("ZLDRF", reference);
				// retrieve default values
				creditAddSundryItem.add(session, EQTransaction.ACTION_VALIDATE);
				// ************
				Toolbox.log("credit validate");
				// ************
				creditAddSundryItem.setAutoOverride(true);
				creditAddSundryItem.add(session, EQTransaction.ACTION_UPDATE);
				// ************
				Toolbox.log("credit update");
				// ************
				System.out.println("Debit errors   :" + debitAddSundryItem.getMessages());
				System.out.println("Debit warnings :" + debitAddSundryItem.getOverrides());
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
