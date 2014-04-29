package com.misys.equation.function.test.run;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
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
public class FunctionHandlerStub08ALY3Cancel extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub08ALY3Cancel.java 7610 2010-06-01 17:10:41Z MACDONP1 $";

	public FunctionHandlerStub08ALY3Cancel()
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

	public FunctionHandlerStub08ALY3Cancel(EquationUnit unit, EquationUser user, EquationStandardSession session)
	{
		FunctionHandlerStubTestCase.unit = unit;
		FunctionHandlerStubTestCase.user = user;
		FunctionHandlerStubTestCase.session = session;
	}

	public static void main(String[] inputParameters)
	{
		FunctionHandlerStub08ALY3Cancel test = new FunctionHandlerStub08ALY3Cancel();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler3 = null;
		try
		{
			// Cancel XX4
			{
				System.out.println("------------------------------- 3");
				functionHandler3 = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
				functionHandler3.doNewTransaction("ALY", "");
				FunctionData functionData3 = functionHandler3.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
				functionData3.chgFieldInputValue("AB", "0132");
				functionData3.chgFieldInputValue("AN", "891250");
				functionData3.chgFieldInputValue("AS", "380");
				functionData3.chgFieldInputValue("HLD", "XX4");
				functionData3.chgFieldInputValue("BRNM", "BRNM");
				functionData3.chgFieldInputValue("DLP", "DLP");
				functionData3.chgFieldInputValue("DLR", "DLR");
				functionData3.chgFieldDbValue("HLD2", "SS4");
				functionHandler3.applyRetrieveTransaction();
				// functionHandler3.validate(0);
				// functionHandler3.loadKeyFieldSet("PRIMARY", "HLD");

				ScreenSet screenSet = functionHandler3.getFhd().getScreenSetHandler().rtvScrnSetCurrent();
				while (screenSet.nextScreen())
				{
					functionData3.chgFieldInputValue("HLD2", "SS4");
					screenSet.validate(screenSet.getScrnNo(), screenSet.getScrnNo());
					screenSet.nextScreen();
				}

				functionHandler3.applyTransactionDelete();
				Toolbox.printList(functionHandler3.print());
				FunctionToolboxStub.printMessages(functionHandler3.rtvFunctionMessages().getMessages());
			}

			JournalHeader journalHeader = functionHandler3.getFhd().getJournalHeader();
			if (journalHeader != null)
			{
				System.out.println("Journal 3=" + journalHeader);
			}
			else
			{
				System.out.println("Journal 3=" + "ERROR");
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

	public void testStub08ALY3Cancel()
	{
		FunctionHandlerStub08ALY3Cancel stub = new FunctionHandlerStub08ALY3Cancel();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
