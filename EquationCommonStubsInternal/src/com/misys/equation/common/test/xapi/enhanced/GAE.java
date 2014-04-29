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

public class GAE
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GAE.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	EQSession session;
	EQEnquiry enhancedAPIMetaDataEnquiry;
	String apiMnemonic = "CAA";
	public GAE()
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
			GAE test = new GAE();
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
			enhancedAPIMetaDataEnquiry = (EQEnquiry) session.createEQObject("GAE");
			enhancedAPIMetaDataEnquiry.setAuditUserID("stubs");
			enhancedAPIMetaDataEnquiry.setFieldValue("ZLAID", apiMnemonic);
			enhancedAPIMetaDataEnquiry.retrieve(session);
			Toolbox.printEQObjectKeyFieldErrors(enhancedAPIMetaDataEnquiry);
			Toolbox.printEQObject(enhancedAPIMetaDataEnquiry);
			EQList enhancedAPIMetaData = enhancedAPIMetaDataEnquiry.getList();
			while (enhancedAPIMetaDataEnquiry.getStatus() == EQObject.STATUS_INCOMPLETE)
			{
				enhancedAPIMetaDataEnquiry.retrieve(session);
			}
			Toolbox.printEQList(enhancedAPIMetaData);
			Toolbox.log("currency list");
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
