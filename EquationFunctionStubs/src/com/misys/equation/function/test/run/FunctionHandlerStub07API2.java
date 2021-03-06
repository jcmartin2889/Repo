package com.misys.equation.function.test.run;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.journal.JournalRecord;
import com.misys.equation.function.runtime.FunctionAPI;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;

// TESTING FOR JOURNAL FILE in BYTES
public class FunctionHandlerStub07API2 extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub07API2.java 17139 2013-08-29 16:00:56Z whittap1 $";

	public FunctionHandlerStub07API2()
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

	public FunctionHandlerStub07API2(EquationUnit unit, EquationUser user, EquationStandardSession session)
	{
		FunctionHandlerStubTestCase.unit = unit;
		FunctionHandlerStubTestCase.user = user;
		FunctionHandlerStubTestCase.session = session;
	}

	public static void main(String[] inputParameters)
	{
		FunctionHandlerStub07API2 test = new FunctionHandlerStub07API2();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			// create the function handler
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");

			// create the function
			functionHandler.rtvEquationSession();
			functionHandler.doNewTransaction("ALZ", "");

			// Setup the journal key
			Function function = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunction();
			JournalRecord journalRecord = FunctionRuntimeToolbox.initialiseJournalRecord(function, "EQ40", 12, 105746, 1,
							JournalRecord.IMAGE_AFT, "", "", null);

			// Setup the journal file
			journalRecord.rtvRecord(session);
			journalRecord.getJrnData().setFieldValue("DRF", "API-2");
			journalRecord.getJrnData().setFieldValue("NR3", "API- narrative 3");
			journalRecord.getJrnData().setFieldValue("FLD2A", "USD");

			// not found?
			if (journalRecord.getJrnData() == null)
			{
				System.err.println("Journal record not found");
				return false;
			}

			// Setup the API
			FunctionAPI functionAPI = new FunctionAPI(functionHandler.getFhd());
			functionAPI.applyTransaction(journalRecord.getJrnData().getBytes());

			// Re-apply same transaction
			// functionAPI.applyTransaction(journalRecord.getJrnData().getBytes());

			// Display messages
			FunctionToolboxStub.printMessages(functionAPI.getFunctionMessages().getMessages());

			// retrieve journal header
			JournalHeader journalHeader = functionHandler.getFhd().getJournalHeader();
			if (journalHeader != null)
			{
				System.out.println(journalHeader);
			}

			System.out.println("Done");
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

	public void testStub07API2_001()
	{
		FunctionHandlerStub07API2 stub = new FunctionHandlerStub07API2();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
