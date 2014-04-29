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

import java.util.Iterator;

import com.misys.equation.common.internal.eapi.core.EQEnquiry;
import com.misys.equation.common.internal.eapi.core.EQField;
import com.misys.equation.common.internal.eapi.core.EQSession;
import com.misys.equation.common.internal.eapi.core.EQTransaction;
import com.misys.equation.common.test.TestEnvironment;

public class OSM
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OSM.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	EQSession session;
	EQTransaction loanInterest;
	EQTransaction accountInterest;
	EQTransaction accountSpecialCondition;
	String suffix = "07";
	public OSM()
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
			OSM test = new OSM();
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
			// Maintain the loan interest rate...
			loanInterest = (EQTransaction) session.createEQObject("MLI");
			loanInterest.setAuditUserID("Chris W");
			loanInterest.setReference("TRN" + suffix);
			// Set the key fields
			loanInterest.setFieldValue("ZLDLP", "COA");
			loanInterest.setFieldValue("ZLDLR", "OFFSET001");
			loanInterest.setFieldValue("ZLBRNM", "LOND");
			// retrieve default values
			loanInterest.setMaintenanceMode(EQTransaction.MAINTENANCE_MODE_MAINTAIN);
			loanInterest.maintain(session, EQEnquiry.ACTION_RETRIEVE);
			printKeyFieldErrors(loanInterest);
			printFields(loanInterest);
			// Now set detail fields...
			loanInterest.setFieldValue("ZLRATZ", "16.0");
			// Bang them in
			loanInterest.setAutoOverride(true);
			loanInterest.setMaintenanceMode(EQTransaction.MAINTENANCE_MODE_MAINTAIN);
			loanInterest.maintain(session, EQTransaction.ACTION_UPDATE);
			printFields(loanInterest);
			System.out.println("loanInterest errors   :" + loanInterest.getMessages());
			System.out.println("loanInterest warnings :" + loanInterest.getOverrides());
			// Maintain the savings account interest rate...
			accountInterest = (EQTransaction) session.createEQObject("MIC");
			accountInterest.setAuditUserID("Chris W");
			accountInterest.setReference("TRN" + suffix);
			// Set the key fields
			accountInterest.setFieldValue("ZLAB", "1000");
			accountInterest.setFieldValue("ZLAN", "D00001");
			accountInterest.setFieldValue("ZLAS", "002");
			// retrieve default values
			accountInterest.setMaintenanceMode(EQTransaction.MAINTENANCE_MODE_MAINTAIN);
			accountInterest.maintain(session, EQEnquiry.ACTION_RETRIEVE);
			printKeyFieldErrors(accountInterest);
			printFields(accountInterest);
			// Now set detail fields...
			accountInterest.setFieldValue("ZNRATZ", "16.0");
			accountInterest.setFieldValue("ZLCLT", "");
			// Add
			accountInterest.setAutoOverride(true);
			accountInterest.setMaintenanceMode(EQTransaction.MAINTENANCE_MODE_MAINTAIN);
			accountInterest.maintain(session, EQTransaction.ACTION_UPDATE);
			printFields(accountInterest);
			System.out.println("accountInterest errors   :" + accountInterest.getMessages());
			System.out.println("accountInterest warnings :" + accountInterest.getOverrides());
			// Maintain the savings account special condition 170...
			accountSpecialCondition = (EQTransaction) session.createEQObject("ASC");
			accountSpecialCondition.setAuditUserID("Chris W");
			accountSpecialCondition.setReference("TRN" + suffix);
			// Set the key fields
			accountSpecialCondition.setFieldValue("ZLAB", "1000");
			accountSpecialCondition.setFieldValue("ZLAN", "D00001");
			accountSpecialCondition.setFieldValue("ZLAS", "002");
			// retrieve default values
			accountSpecialCondition.setMaintenanceMode(EQTransaction.MAINTENANCE_MODE_MAINTAIN);
			accountSpecialCondition.maintain(session, EQEnquiry.ACTION_RETRIEVE);
			printKeyFieldErrors(accountSpecialCondition);
			printFields(accountSpecialCondition);
			// Now set detail fields...
			accountSpecialCondition.setFieldValue("ZLMACT", "N");
			for (int i = 0; i < accountSpecialCondition.getList().getRows().size(); i++)
			{
				if (accountSpecialCondition.getList().getFieldValue(i, "ZLSCR").equals("170"))
				{
					accountSpecialCondition.getList().setFieldValue(i, "ZLSINP", "1");
				}
			}
			// Add
			accountSpecialCondition.setAutoOverride(true);
			accountSpecialCondition.setMaintenanceMode(EQTransaction.MAINTENANCE_MODE_MAINTAIN);
			accountSpecialCondition.maintain(session, EQTransaction.ACTION_UPDATE);
			printFields(accountSpecialCondition);
			System.out.println("accountSpecialCondition errors   :" + accountSpecialCondition.getMessages());
			System.out.println("accountSpecialCondition warnings :" + accountSpecialCondition.getOverrides());
			// now we are in a position to commit the cheeky chappies...
			session.commit();
			// log out
			session.logOff();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public void printKeyFieldErrors(EQTransaction trannie)
	{
		try
		{
			Iterator<EQField> iterFields = trannie.getFields().values().iterator();
			while (iterFields.hasNext())
			{
				EQField trannieField = iterFields.next();
				// Only interested in error messages for key fields
				if (trannieField.getDefinition().isKeyField() && !(trannieField.getMessages() == null))
				{
					System.out.println(trannieField.getMessages());
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public void printFields(EQTransaction trannie)
	{
		try
		{
			// Loop through the fields and print their values
			Iterator<EQField> iterFields = trannie.getFields().values().iterator();
			while (iterFields.hasNext())
			{
				EQField trannieField = iterFields.next();
				// Write out the fields values
				if (!trannieField.getValue().trim().equals("")
								&& !trannieField.getValue().trim().equals(new String(new byte[] { 0x7F })))
				{
					System.out.println(trannieField.getDefinition().getFieldName() + ":" + trannieField.getValue());
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public static void outputByteArray(String s, byte[] bytes)
	{
		// Bung the bytes into a string
		StringBuffer stringBuffer = new StringBuffer();
		String string = "";
		int i = 0;
		for (i = 0; i < bytes.length; i++)
		{
			int bytesInteger = Integer.parseInt(Byte.toString(bytes[i]));
			if (bytesInteger < 0)
			{
				bytesInteger = bytesInteger + 256;
			}
			String byteHex = Integer.toHexString(bytesInteger);
			while (byteHex.length() < 2)
			{
				byteHex = "0" + byteHex;
			}
			// Suppress leading 0x00 values to aid presentation
			if (!byteHex.equals("00"))
			{
				stringBuffer.append(byteHex + " ");
			}
		}
		string = stringBuffer.toString().toUpperCase();
		System.out.println(s + string);
	}
}
