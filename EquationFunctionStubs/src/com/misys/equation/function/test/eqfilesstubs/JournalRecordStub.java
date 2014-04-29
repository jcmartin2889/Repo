package com.misys.equation.function.test.eqfilesstubs;

import java.sql.Connection;
import java.util.Calendar;

import com.misys.equation.common.access.EquationDataStructureData;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.journal.JournalRecord;
import com.misys.equation.function.test.run.FunctionHandlerStubTestCase;
import com.misys.equation.function.tools.XMLToolbox;

public class JournalRecordStub extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: JournalRecordStub.java 12212 2011-11-01 15:45:23Z lima12 $";

	public JournalRecordStub()
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
		JournalRecordStub test = new JournalRecordStub();
		test.test();
	}

	private void test()
	{
		// Have a bash...
		try
		{
			boolean flag;

			// get the connection and the default unit names
			Connection connection = session.getConnection();

			// create the function
			Function function = XMLToolbox.getXMLToolbox().getFunction(session, "ALZ", true);

			// create the journal record
			JournalRecord record = new JournalRecord(function);
			record.setWorkstationID("ABCD");
			record.setJrnDay(3);
			record.setJrnTime(93628);
			record.setJrnSequence(1);
			record.setImage("A");
			record.setEfc("X");

			// retrieve the record
			record.rtvRecord(session);

			// get a handle to the DS data
			EquationDataStructureData jrnData = record.getJrnData();
			jrnData.setFieldValue("AB", "1234");
			jrnData.setFieldValue("DAT3", "1234567");
			record.setWorkstationID("COPY");
			record.setJrnTime(Toolbox.getTimeDBFormat(Calendar.getInstance()));
			flag = record.update(session);
			if (flag)
				System.out.println("1. Record added...");
			else
				System.out.println("1. Record updated...");

			// commit the changes
			connection.commit();

			connection.close();
			System.out.println("Done");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
