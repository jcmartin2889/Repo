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
import com.misys.equation.common.internal.eapi.core.EQSession;
import com.misys.equation.common.internal.eapi.core.EQTransaction;
import com.misys.equation.common.test.TestEnvironment;

public class LMT
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LMT.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	EQSession session;
	EQTransaction customerLimits;
	public LMT()
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
			LMT test = new LMT();
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
			// ******************************************************************************************
			// Lets try and maintain it!
			// ******************************************************************************************
			// Got this far so we may as well have a bash at a couple of
			// transactions...
			customerLimits = (EQTransaction) session.createEQObject("LMT");
			customerLimits.setAuditUserID("whoever");
			// Set the fields
			customerLimits.setFieldValue("ZLCUS", "BBI");
			customerLimits.setMaintenanceMode(EQTransaction.MAINTENANCE_MODE_MAINTAIN);
			customerLimits.maintain(session, EQEnquiry.ACTION_RETRIEVE);
			// Now set a record
			System.out.println(customerLimits.getStatusString());
			System.out.println(customerLimits.getMessages());
			System.out.println(customerLimits.getOverrides());
			// customerLimits.getList().setFieldValue(3,"ZLMON", "02");
			// customerLimits.getList().setFieldValue(3,"ZLRAT", "7");
			// Bang them in
			// customerLimits.setAutoOverride(true);
			// customerLimits.maintain(session, EQTransaction.ACTION_UPDATE);
			System.out.println(customerLimits.getMessages());
			System.out.println(customerLimits.getOverrides());
			// commit the bad boys
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
}
