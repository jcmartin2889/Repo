package com.misys.equation.function.test.run;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionMessages;

/**
 * FunctionHandler Stub 1
 * 
 * This is how to use the Function Handler with validate and update processing done separately
 * 
 */
public class FunctionHandlerStub08ALY7Add extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub08ALY7Add.java 6793 2010-03-31 12:10:20Z deroset $";

	public FunctionHandlerStub08ALY7Add()
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
		FunctionHandlerStub08ALY7Add test = new FunctionHandlerStub08ALY7Add();
		test.test();
	}
	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			// Add XX4
			int msgSev;
			System.out.println("------------------------------- 1");
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			functionHandler.doNewTransaction("ALY", "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			// test retrieve of something
			functionData.chgFieldInputValue("AB", "0132");
			functionData.chgFieldInputValue("AN", "891250");
			functionData.chgFieldInputValue("AS", "380");
			functionData.chgFieldInputValue("HLD", "RTY");
			functionData.chgFieldDbValue("HLD2", "S55");
			msgSev = functionHandler.applyValidateTransaction();

			if (msgSev < FunctionMessages.MSG_ERROR)
			{
				msgSev = functionHandler.applyRetrieveTransaction();
			}
			else
			{
				throw new EQException("error at first apply validate transaction");
			}

			// test retrieve of something
			functionHandler.applyUnloadTransaction();
			functionData.chgFieldInputValue("HLD", "X55");
			functionData.chgFieldDbValue("HLD2", "S55");
			if (msgSev < FunctionMessages.MSG_ERROR)
			{
				msgSev = functionHandler.applyRetrieveTransaction();
			}
			else
			{
				throw new EQException("error at second apply validate transaction");
			}

			if (msgSev < FunctionMessages.MSG_ERROR)
			{
				functionData.chgFieldInputValue("HLD", ""); // will not be set as protected already
				functionData.chgFieldInputValue("BRNM", "BRNM");
				functionData.chgFieldInputValue("DLP", "DLP");
				functionData.chgFieldInputValue("DLR", "DLR");
				functionData.chgFieldDbValue("HLD2", "S55");
				functionData.chgFieldDbValue("DSC", "S55");
				functionData.chgFieldDbValue("DSC2", "S55");
				msgSev = functionHandler.applyTransaction();
			}

			Toolbox.printList(functionHandler.print());
			FunctionToolboxStub.printMessages(functionHandler.rtvFunctionMessages().getMessages());

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

			return (journalHeader != null && msgSev < FunctionMessages.MSG_ERROR);
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

	public void testStub08ALY7Add()
	{
		FunctionHandlerStub08ALY7Add stub = new FunctionHandlerStub08ALY7Add();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
