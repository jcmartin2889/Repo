package com.misys.equation.function.test.run;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.function.journal.JournalRecord;
import com.misys.equation.function.runtime.FunctionAPI;
import com.misys.equation.function.runtime.FunctionHandler;

// TESTING FOR JOURNAL FILE
public class FunctionHandlerStub06API1 extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub06API1.java 6793 2010-03-31 12:10:20Z deroset $";

	public FunctionHandlerStub06API1()
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
		FunctionHandlerStub06API1 test = new FunctionHandlerStub06API1();
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
			functionHandler.doNewTransaction("ALZ", "");

			// Setup the journal key
			JournalRecord journalRecord = new JournalRecord(functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent()
							.getFunction());
			journalRecord.setWorkstationID("EQ40");
			journalRecord.setJrnDay(12);
			journalRecord.setJrnTime(105746);
			journalRecord.setJrnSequence(1);
			journalRecord.setImage(JournalRecord.IMAGE_AFT);

			// Setup the journal file
			journalRecord.rtvRecord(session);
			journalRecord.getJrnData().setFieldValue("DRF", "API-1");
			journalRecord.getJrnData().setFieldValue("NR3", "API- narrative 3");
			journalRecord.getJrnData().setFieldValue("FLD2A", "GBP");

			// not found?
			if (journalRecord.getJrnData() == null)
			{
				System.err.println("Journal record not found");
				return false;
			}

			// Setup the API
			FunctionAPI functionAPI = new FunctionAPI(functionHandler.getFhd());
			functionAPI.applyTransaction(journalRecord);

			// Re-apply same transaction
			// functionAPI.applyTransaction(journalRecord);

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

	public void testStub06API1_001()
	{
		FunctionHandlerStub06API1 stub = new FunctionHandlerStub06API1();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
