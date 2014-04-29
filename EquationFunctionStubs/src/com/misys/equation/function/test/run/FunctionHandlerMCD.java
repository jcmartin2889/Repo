package com.misys.equation.function.test.run;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;

// Via API
public class FunctionHandlerMCD extends FunctionHandlerStubTestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerMCD.java 6793 2010-03-31 12:10:20Z deroset $";
	public FunctionHandlerMCD()
	{
		try
		{
			setUp();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] inputParameters)
	{
		FunctionHandlerMCD test = new FunctionHandlerMCD();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler2 = null;
		try
		{
			// Maintain
			System.out.println("--------------------------- MC3");
			functionHandler2 = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			functionHandler2.doNewTransaction("MC3", "");

			FunctionData functionData2 = functionHandler2.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			functionData2.chgFieldInputValue("MCD_CUS", "ABRAHA");
			functionData2.chgFieldInputValue("MCD_CLC", "ISA");
			functionHandler2.applyRetrieveTransaction();

			functionData2.chgFieldInputValue("MCD_SRC", "IA");
			functionData2.chgFieldInputValue("MCD_GRP", "AIC");
			functionData2.chgFieldInputValue("MCD_LNM", "AR");
			functionData2.chgFieldInputValue("MCD_LNM", "RRC");

			functionData2.chgFieldInputValue("MCD_SAC", "BA");
			functionData2.chgFieldInputValue("MCD_CNAP", "GB");
			functionData2.chgFieldInputValue("MCD_LNM", "ES");
			functionData2.chgFieldInputValue("MCD_ACO", "AC");

			functionData2.chgFieldInputValue("MCD_CA2", "IA");

			functionHandler2.applyTransaction();

			Toolbox.printList(functionHandler2.print());
			FunctionToolboxStub.printMessages(functionHandler2.rtvFunctionMessages().getMessages());

			JournalHeader journalHeader = functionHandler2.getFhd().getJournalHeader();
			if (journalHeader != null)
			{
				System.out.println("Journal 2=" + journalHeader);
			}
			else
			{
				System.out.println("Journal 2=" + "ERROR");
			}

			return (journalHeader != null);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			cleanUp();
		}
	}

}
