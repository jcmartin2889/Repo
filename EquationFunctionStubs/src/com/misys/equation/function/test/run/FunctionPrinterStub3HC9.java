package com.misys.equation.function.test.run;

import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.journal.JournalRecord;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionPrinter;

/**
 * FunctionPrinter stub
 * 
 * This is how to use the Function Printer given the journal record
 * 
 */
public class FunctionPrinterStub3HC9 extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionPrinterStub3HC9.java 6793 2010-03-31 12:10:20Z deroset $";

	public FunctionPrinterStub3HC9()
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
		FunctionPrinterStub3HC9 test = new FunctionPrinterStub3HC9();
		test.test();
	}

	private void test()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			// create the function handler
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");

			// create the function
			functionHandler.doNewTransaction("HC9", "");

			// Setup the journal key (after image)
			JournalRecord journalRecordAft = new JournalRecord(functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent()
							.getFunction());
			journalRecordAft.setWorkstationID("QZDA");
			journalRecordAft.setJrnDay(16);
			journalRecordAft.setJrnTime(104016);
			journalRecordAft.setJrnSequence(2);
			journalRecordAft.setImage(JournalRecord.IMAGE_AFT);
			journalRecordAft.rtvRecord(session);
			FunctionPrinter functionPrinter = new FunctionPrinter(functionHandler.getFhd());
			functionPrinter.setPrintHeader(true);
			functionPrinter.setPrintBlankLine(true);
			functionPrinter.print(journalRecordAft);
			Toolbox.printList(functionPrinter.getLines());

			System.out.println("Done");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cleanUp();
		}
	}
}