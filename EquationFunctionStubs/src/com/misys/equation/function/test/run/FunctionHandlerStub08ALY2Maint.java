package com.misys.equation.function.test.run;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.ScreenSet;

/**
 * FunctionHandler Stub 1
 * 
 * This is how to use the Function Handler with validate and update processing done separately
 * 
 */
public class FunctionHandlerStub08ALY2Maint extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub08ALY2Maint.java 6793 2010-03-31 12:10:20Z deroset $";

	public FunctionHandlerStub08ALY2Maint()
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
		FunctionHandlerStub08ALY2Maint test = new FunctionHandlerStub08ALY2Maint();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler2 = null;
		try
		{
			// Maintain XX4
			{
				System.out.println("------------------------------- 2");
				functionHandler2 = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
				functionHandler2.doNewTransaction("ALY", "");
				FunctionData functionData2 = functionHandler2.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
				functionData2.chgFieldInputValue("AB", "0132");
				functionData2.chgFieldInputValue("AN", "891250");
				functionData2.chgFieldInputValue("AS", "380");
				functionData2.chgFieldInputValue("HLD", "XX4");
				functionData2.chgFieldInputValue("BRNM", "BRNM");
				functionData2.chgFieldInputValue("DLP", "DLP");
				functionData2.chgFieldInputValue("DLR", "DLR");
				functionData2.chgFieldDbValue("HLD2", "SS4");
				functionHandler2.applyRetrieveTransaction();
				// functionHandler2.validate(0);
				// functionHandler2.loadKeyFieldSet("PRIMARY", "HLD");

				ScreenSet screenSet = functionHandler2.getFhd().getScreenSetHandler().rtvScrnSetCurrent();
				while (screenSet.nextScreen())
				{
					functionData2.chgFieldInputValue("CUS", "1");
					functionData2.chgFieldInputValue("CLC", "2");
					functionData2.chgFieldInputValue("DOS", "DOS-AMEND");
					functionData2.chgFieldInputValue("DSC", "XX4 amended description");
					functionData2.chgFieldInputValue("HLD2", "SS4");
					functionData2.chgFieldInputValue("DSC2", "SS4 amended description");
					screenSet.validate(screenSet.getScrnNo(), screenSet.getScrnNo());
					screenSet.nextScreen();
				}

				functionHandler2.applyTransaction();
				Toolbox.printList(functionHandler2.print());
				FunctionToolboxStub.printMessages(functionHandler2.rtvFunctionMessages().getMessages());
			}

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

	public void testStub08ALY2Maint_001()
	{
		FunctionHandlerStub08ALY2Maint stub = new FunctionHandlerStub08ALY2Maint();
		boolean success = stub.test();
		assertEquals(true, success);
	}
}
