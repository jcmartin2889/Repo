package com.misys.equation.function.test.run;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;

/**
 * FunctionHandler Stub 1
 * 
 * This is how to use the Function Handler with validate and update processing done separately
 * 
 */
public class FunctionHandlerStub10Enquire extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub10Enquire.java 7610 2010-06-01 17:10:41Z MACDONP1 $";

	public FunctionHandlerStub10Enquire()
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

	public FunctionHandlerStub10Enquire(EquationUnit unit, EquationUser user, EquationStandardSession session)
	{
		FunctionHandlerStubTestCase.unit = unit;
		FunctionHandlerStubTestCase.user = user;
		FunctionHandlerStubTestCase.session = session;
	}

	public static void main(String[] inputParameters)
	{
		FunctionHandlerStub10Enquire test = new FunctionHandlerStub10Enquire();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			System.out.println("------------------------------- 1");
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			functionHandler.doNewTransaction("ALY", "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
			functionData.chgFieldInputValue("HLD", "GCF");
			functionData.chgFieldInputValue("BRNM", "LOND");
			functionData.chgFieldInputValue("DLP", "FXF");
			functionData.chgFieldInputValue("DLR", "1001");
			functionData.chgFieldInputValue("AB", "0132");
			functionData.chgFieldInputValue("AN", "123467");
			functionData.chgFieldInputValue("AS", "001");
			functionData.chgFieldDbValue("HLD2", functionData.rtvFieldInputValue("HLD2"));
			functionHandler.applyRetrieveTransaction();

			FunctionToolboxStub.printMessages(functionHandler.rtvFunctionMessages().getMessages());
			System.out.println(functionHandler.getFhd().getScreenSetHandler().rtvScreenSetMain().getFunctionData());

			boolean bool1 = functionData.rtvFieldOutputValue("HLD").contains("GUARA");
			boolean bool2 = functionData.rtvFieldInputValue("DSC").contains("CERTIFIED");
			boolean bool3 = functionData.rtvFieldOutputValue("AB").contains("Atlant");

			JournalHeader journalHeader = functionHandler.getFhd().getJournalHeader();
			return (journalHeader == null && bool1 && bool2 && bool3);
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

	public void testStub10Enquire_001()
	{
		FunctionHandlerStub10Enquire stub = new FunctionHandlerStub10Enquire();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
