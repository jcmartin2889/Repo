package com.misys.equation.function.test.eqfilesstubs;

import java.sql.Connection;

import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.journal.JournalRecord;
import com.misys.equation.function.runtime.FunctionHandlerData;
import com.misys.equation.function.runtime.FunctionInfo;
import com.misys.equation.function.test.run.FunctionHandlerStubTestCase;
import com.misys.equation.function.tools.XMLToolbox;

public class JournalRecordStubFuncData extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: JournalRecordStubFuncData.java 12212 2011-11-01 15:45:23Z lima12 $";

	public JournalRecordStubFuncData()
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
		JournalRecordStubFuncData test = new JournalRecordStubFuncData();
		test.test();
	}

	private void test()
	{
		// Have a bash...
		try
		{
			// get the connection and the default unit names
			Connection connection = session.getConnection();
			boolean flag;

			// create the function
			Function function = XMLToolbox.getXMLToolbox().getFunction(session, "ALZ", true);

			// create the journal record
			JournalRecord record = new JournalRecord(function);
			record.setWorkstationID("STUB");
			record.setJrnDay(15);
			record.setJrnTime(123456);
			record.setJrnSequence(789);
			record.setImage("A");

			// set the fields via data structure
			FunctionHandlerData fhd = new FunctionHandlerData(null, user, new FunctionInfo("s", "n"));
			FunctionData functionData = new FunctionData(function, fhd);
			functionData.chgFieldDbValue("AB", "9132");
			functionData.chgFieldDbValue("AN", "234567");
			functionData.chgFieldDbValue("AS", "001");
			functionData.chgFieldDbValue("EAN", "1840KBWD870900840");
			functionData.chgFieldDbValue("TCD", "510");
			functionData.chgFieldDbValue("AMT", "10000");
			functionData.chgFieldDbValue("CCY", "GBP");
			functionData.chgFieldDbValue("BRNM", "LOND");
			functionData.chgFieldDbValue("DRF", "via STUB1");
			functionData.chgFieldDbValue("NR1", "test NR1");
			functionData.chgFieldDbValue("NR2", "test NR2");
			functionData.chgFieldDbValue("NR3", "test NR3");
			functionData.chgFieldDbValue("NR4", "test NR4");
			functionData.chgFieldDbValue("SRC", "SR");
			functionData.chgFieldDbValue("UC1", "UC1");
			functionData.chgFieldDbValue("UC2", "UC2");
			functionData.chgFieldDbValue("PBR", "STB1");
			functionData.chgFieldDbValue("NPE", "1");
			functionData.chgFieldDbValue("AC", "9132234567001");
			functionData.chgFieldDbValue("FRQ", "L01");
			functionData.chgFieldDbValue("YNO", "Y");
			functionData.chgFieldDbValue("YNO2", "N");
			functionData.chgFieldDbValue("RAT1", "123.45");
			functionData.chgFieldDbValue("RAT2", "12345678.9012345");
			functionData.chgFieldDbValue("VFR", "1000501");

			// add the record
			flag = record.update(session, functionData);
			if (flag)
				System.out.println("1. Record added...");
			else
				System.out.println("1. Record updated...");

			// delete the record
			flag = record.delete(session);
			if (flag)
				System.out.println("2. Record deleted...");
			else
				System.out.println("2. Record not found.....");

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
