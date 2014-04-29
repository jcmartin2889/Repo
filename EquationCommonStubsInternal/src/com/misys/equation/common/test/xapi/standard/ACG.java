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
public class ACG
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACG.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	String inputBrnmStr = "0000";
	String Dealtype = "BUF";
	String Dealref = "001";
	String Dealbranch = "HEAD";
	String Commitmentref = "";
	String Accountbranch = "";
	String Accountnumber = "";
	String Accountsuffix = "";
	String GZUSER = "LIMA";
	// originating transaction
	String GZEVNM = "RLA";
	String GZTREF = "";
	String GZCAB = "";
	String GZCAN = "";
	String GZCAS = "";
	String GZDDTD = "991231";
	String GZAREF = "";
	String GZESQN = "0";
	String DefaultCharge = "N";
	String TranFlag = "A";
	String Startdate = "1000104";
	String Enddate = "1000204";
	String Frequency = "V01";
	String Calculationnarrative1 = "";
	String Calculationnarrative2 = "";
	String Calculationnarrative3 = "";
	String Calculationnarrative4 = "";
	String Eventmnem = "RLA1";
	String Chargecode = "AD";
	String Chargeamount = "92000";
	String Waivedamount = "0";
	String Minimumcharge = "0";
	String Maximumcharge = "0";
	String Fundingaccountbranch = "9132";
	String Fundingaccountnumber = "234567";
	String Fundingaccountsuffix = "001";
	String Capitalisedcharge = "N";
	String Postingnarrative1 = "  ";
	String Postingnarrative2 = " ";
	String Postingnarrative3 = " ";
	String Postingnarrative4 = " ";
	String Usercode1 = " ";
	String Usercode2 = " ";
	String Taxrate = "0";
	String Forcetofee = "N";
	EquationStandardSession session;
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	private ACG()
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
		ACG test = new ACG();
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
			// Get a new transaction for a sundry item
			EquationStandardTransaction eventCharge = new EquationStandardTransaction("C62ARRACG", session, 1000);
			// Set the transaction type
			eventCharge.setMode("A");
			eventCharge.setFieldValue("GZDLP", Dealtype);
			eventCharge.setFieldValue("GZDLR", Dealref);
			eventCharge.setFieldValue("GZDBRM", Dealbranch);
			eventCharge.setFieldValue("GZCMR", Commitmentref);
			eventCharge.setFieldValue("GZAB", Accountbranch);
			eventCharge.setFieldValue("GZAN", Accountnumber);
			eventCharge.setFieldValue("GZAS", Accountsuffix);
			eventCharge.setFieldValue("GZVAL", Startdate);
			eventCharge.setFieldValue("GZUSER", GZUSER);
			eventCharge.setFieldValue("GZEVNM", GZEVNM);
			eventCharge.setFieldValue("GZTREF", GZTREF);
			eventCharge.setFieldValue("GZCAB", GZCAB);
			eventCharge.setFieldValue("GZCAN", GZCAN);
			eventCharge.setFieldValue("GZCAS", GZCAS);
			eventCharge.setFieldValue("GZDDTD", GZDDTD);
			eventCharge.setFieldValue("GZAREF", GZAREF);
			eventCharge.setFieldValue("GZESQN", GZESQN);
			eventCharge.setFieldValue("GZDCHG", DefaultCharge);
			eventCharge.setFieldValue("GZFLAG", TranFlag);
			eventCharge.setGSFieldValue("GSECNM", Eventmnem);
			eventCharge.setGSFieldValue("GSCHGC", Chargecode);
			eventCharge.setGSFieldValue("GSCHA", Chargeamount);
			eventCharge.setGSFieldValue("GSSDT", Startdate);
			eventCharge.setGSFieldValue("GSEND", Enddate);
			eventCharge.setGSFieldValue("GSFRQ", Frequency);
			eventCharge.setGSFieldValue("GSNA1", Calculationnarrative1);
			eventCharge.setGSFieldValue("GSNA2", Calculationnarrative2);
			eventCharge.setGSFieldValue("GSNA3", Calculationnarrative3);
			eventCharge.setGSFieldValue("GSNA4", Calculationnarrative4);
			eventCharge.setGSFieldValue("GSWAMD", Waivedamount);
			eventCharge.setGSFieldValue("GSMIND", Minimumcharge);
			eventCharge.setGSFieldValue("GSMAXD", Maximumcharge);
			eventCharge.setGSFieldValue("GSFAB", Fundingaccountbranch);
			eventCharge.setGSFieldValue("GSFAN", Fundingaccountnumber);
			eventCharge.setGSFieldValue("GSFAS", Fundingaccountsuffix);
			eventCharge.setGSFieldValue("GSCAP", Capitalisedcharge);
			eventCharge.setGSFieldValue("GSNP1", Postingnarrative1);
			eventCharge.setGSFieldValue("GSNP2", Postingnarrative2);
			eventCharge.setGSFieldValue("GSNP3", Postingnarrative3);
			eventCharge.setGSFieldValue("GSNP4", Postingnarrative4);
			eventCharge.setGSFieldValue("GSUC1", Usercode1);
			eventCharge.setGSFieldValue("GSUC2", Usercode2);
			eventCharge.setGSFieldValue("GSTAX", Taxrate);
			eventCharge.setGSFieldValue("GSFTF", Forcetofee);
			// loan = unit.validateEquationTransaction(loan);
			session.addEquationTransaction(eventCharge);
			// Apply the transaction
			session.applyTransactions();
			session.commitTransactions();
			Toolbox.printMessages(eventCharge.getErrorList());
			Toolbox.printMessages(eventCharge.getWarningList());
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			// Rollback any transactions for the unit
		}
	}
}
