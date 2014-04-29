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
public class FunctionHandlerStub08ALY1Add extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub08ALY1Add.java 6793 2010-03-31 12:10:20Z deroset $";

	public FunctionHandlerStub08ALY1Add()
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
		FunctionHandlerStub08ALY1Add test = new FunctionHandlerStub08ALY1Add();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			// Add XX4
			{
				System.out.println("------------------------------- 1");
				functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
				functionHandler.doNewTransaction("ALY", "");
				FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
				functionData.chgFieldInputValue("AB", "0132");
				functionData.chgFieldInputValue("AN", "891250");
				functionData.chgFieldInputValue("AS", "380");
				functionData.chgFieldInputValue("HLD", "XX4");
				functionData.chgFieldInputValue("BRNM", "BRNM");
				functionData.chgFieldInputValue("DLP", "DLP");
				functionData.chgFieldInputValue("DLR", "DLR");
				functionData.chgFieldDbValue("HLD2", "SS4");
				functionHandler.applyRetrieveTransaction();

				ScreenSet screenSet = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent();
				while (screenSet.nextScreen())
				{
					functionData.chgFieldInputValue("CUS", "CUSSS");
					functionData.chgFieldInputValue("CLC", "CLC");
					functionData.chgFieldInputValue("DOS", "DOS-ORG-SYS");
					functionData.chgFieldInputValue("DSC", "XX4 Description");
					functionData.chgFieldInputValue("HLD2", "SS4");
					functionData.chgFieldInputValue("DSC2", "SS4 Description");
					screenSet.validate(screenSet.getScrnNo(), screenSet.getScrnNo());
					screenSet.nextScreen();
				}

				functionHandler.applyTransaction();
				Toolbox.printList(functionHandler.print());
				FunctionToolboxStub.printMessages(functionHandler.rtvFunctionMessages().getMessages());
			}

			// retrieve journal header
			JournalHeader journalHeader = functionHandler.getFhd().getJournalHeader();
			if (journalHeader != null)
			{
				System.out.println("Journal 1=" + journalHeader);
			}
			else
			{
				System.out.println("Journal 1=" + "ERROR");
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

	public void testStub08ALY1Add_001()
	{
		FunctionHandlerStub08ALY1Add stub = new FunctionHandlerStub08ALY1Add();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
