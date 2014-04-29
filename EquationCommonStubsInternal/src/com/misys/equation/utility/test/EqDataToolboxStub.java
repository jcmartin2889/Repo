package com.misys.equation.utility.test;

import com.misys.equation.common.test.EquationTestCase;
import com.misys.equation.common.utilities.EqDataToolbox;

// Via Save and Restore
public class EqDataToolboxStub extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqDataToolboxStub.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public void test()
	{
		// Have a bash...
		try
		{
			String error = EqDataToolbox.validateOption(session, "V", "N", "N", "AAC", "LIMA");
			System.out.println("AAC check = " + error);

			error = EqDataToolbox.validateOption(session, "V", "N", "N", "RLA", "LIMA");
			System.out.println("RLA check = " + error);

			error = EqDataToolbox.validateAmount(session, "", "N", "N", "1000", "GBP", 0, 3);
			System.out.println("Amount check = " + error);

			error = EqDataToolbox.validateAmount(session, "", "N", "N", "1000", "GBP", 0, 15);
			System.out.println("Amount check = " + error);

			error = EqDataToolbox.editAmount(session, "", "123456", "", 3, 6);
			System.out.println("Amount edit = " + error);

			error = EqDataToolbox.editAmount(session, "", "1000", "GBP", 0, 15);
			System.out.println("Amount edit = " + error);

			error = EqDataToolbox.editChargeComponent(session, "", "ALZ001", "ALZ");
			System.out.println("Charge component edit = " + error);

			error = EqDataToolbox.editAccount(session, "", "0000", "000001", "001");
			System.out.println("Account edit = " + error);

			error = EqDataToolbox.editDealType(session, "", "CR2");
			System.out.println("Deal type edit = " + error);

			error = EqDataToolbox.editChargeCode(session, "", "AC");
			System.out.println("Charge code edit = " + error);

			error = EqDataToolbox.editBaseCode(session, "", "B0");
			System.out.println("Base code edit = " + error);

			error = EqDataToolbox.editCcy(session, "", "GBP");
			System.out.println("Ccy code edit = " + error);

			System.out.println("Date edit = " + EqDataToolbox.editDate(session, "", "010101", EqDataToolbox.DATEFORMAT_DB));
			System.out.println("Date edit = " + EqDataToolbox.editDate(session, "", "010101", EqDataToolbox.DATEFORMAT_DOC));
			System.out.println("Date edit = " + EqDataToolbox.editDate(session, "", "010101", EqDataToolbox.DATEFORMAT_EXT));
			System.out.println("Date edit = " + EqDataToolbox.editDate(session, "", "010101", EqDataToolbox.DATEFORMAT_USER));
			System.out.println("Date edit = " + EqDataToolbox.editDate(session, "", "010101", EqDataToolbox.DATEFORMAT_PRT));

			error = EqDataToolbox.editFreq(session, "", "V13");
			System.out.println("Freq code edit = " + error);

			error = EqDataToolbox.editEvent(session, "", "ASI");
			System.out.println("Event edit = " + error);

			error = EqDataToolbox.editParameter(session, "", "", "C8", "GBP");
			System.out.println("C8 edit = " + error);

			error = EqDataToolbox.editParameter(session, "", "", "SNC75");
			System.out.println("Parameter edit = " + error);

			error = EqDataToolbox.editGroup(session, "", "ATLANT");
			System.out.println("Group edit = " + error);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
