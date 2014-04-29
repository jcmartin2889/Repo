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
package com.misys.equation.common.test.pv.list;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationPVData;
import com.misys.equation.common.test.EquationTestCase;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.Toolbox;

// *************************************************************************************************
/**
 * 
 * 
 */
// *************************************************************************************************
public class AAA extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AAA.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
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
			// Validate using EquationPVData
			System.out.println("Query on *");
			String decode = " ";
			String pvModule = "AAA";
			EquationPVData equationPVData = new EquationPVData(unit.getPVMetaData(pvModule), session.getCcsid());
			String zlslt = equationPVData.parseFieldsIntoDSCCN(EqDataType.YES);
			String result = EquationCommonContext.getContext().getQueryResults(session.getSessionIdentifier(), pvModule, decode,
							"", "", "", zlslt, "", 1, 20);
			System.out.println(Toolbox.printArray(result.split(EqDataType.GLOBAL_DELIMETER)));

			// // Validate using EquationPVData
			// System.out.println("Query on ALL");
			// equationPVData = new EquationPVData(unit.getPVMetaData(pvModule), session.getCcsid());
			// equationPVData.setFieldValue("C8@CCY", "*");
			// listValidation = new EquationStandardListValidation(decode, pvModule, equationPVData, "N", "N", "", "", 1, 16);
			// session.executeListValidate(user, listValidation);
			// System.out.println("PVDATA: " + listValidation);
			//
			// int page = 1;
			// while (listValidation.isMoreRecords())
			// {
			// System.out.println("page " + ++page);
			// session.executeNextListValidate(user, listValidation);
			// System.out.println("PVDATA: " + listValidation);
			// }

		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			// Rollback any transactions for the unit
		}
	}
}
