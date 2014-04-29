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
import com.misys.equation.common.internal.eapi.core.EQObject;
import com.misys.equation.common.internal.eapi.core.EQSession;
import com.misys.equation.common.internal.eapi.core.EQTransaction;
import com.misys.equation.common.test.TestEnvironment;

public class CFF
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CFF.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	EQSession session;
	EQTransaction customerFreeFormatDetails;
	String suffix = "01";
	boolean addMode = false;
	public CFF()
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
			CFF test = new CFF();
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
			customerFreeFormatDetails = (EQTransaction) session.createEQObject("CFF");
			customerFreeFormatDetails.setAuditUserID("Chris W");
			customerFreeFormatDetails.setReference("TRN" + suffix);
			// Set the fields
			customerFreeFormatDetails.setFieldValue("ZLCUS", "HARR" + suffix);
			customerFreeFormatDetails.setFieldValue("ZLCLC", "   ");
			// retrieve default values
			customerFreeFormatDetails.maintain(session, EQEnquiry.ACTION_RETRIEVE);
			if (customerFreeFormatDetails.getStatus() == EQObject.STATUS_ERRORS)
			{
				addMode = true;
			}
			printKeyFieldErrors(customerFreeFormatDetails);
			printFields(customerFreeFormatDetails);
			// Now set detail fields...
			customerFreeFormatDetails.setFieldValue("ZLCF01", "This customer is costing us money.");
			customerFreeFormatDetails.setFieldValue("ZLCF02", "Please encourage him to leave the bank.");
			// Bang them in
			customerFreeFormatDetails.setAutoOverride(true);
			if (addMode)
			{
				customerFreeFormatDetails.add(session, EQTransaction.ACTION_UPDATE);
			}
			else
			{
				customerFreeFormatDetails.maintain(session, EQTransaction.ACTION_UPDATE);
			}
			System.out.println("Customer errors   :" + customerFreeFormatDetails.getMessages());
			System.out.println("Customer warnings :" + customerFreeFormatDetails.getOverrides());
			// now we are in a position to commit the cheeky chappies...
			// Maybe pop in another breakpoint to see the state of play of the iSeries job?
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
