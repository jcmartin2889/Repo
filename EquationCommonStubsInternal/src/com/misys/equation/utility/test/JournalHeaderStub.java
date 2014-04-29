package com.misys.equation.utility.test;

import java.sql.Connection;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.test.EquationTestCase;

public class JournalHeaderStub extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: JournalHeaderStub.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public void test()
	{
		// Have a bash...
		try
		{
			// get the connection and the default unit names
			Connection connection = session.getConnection();
			String unitName = session.getUnitId();
			String kInpLibName = session.getUnit().getKINPLibrary();
			boolean flag;

			// create the GA record
			JournalHeader record = new JournalHeader();
			record.chgJournalKey();

			// set the fields
			record.setLibrary(kInpLibName);
			record.setAext("X");
			record.setApplication("application");
			record.setArec("Y");
			record.setBranch("LOND");
			record.setFunctionMode("A");
			record.setIdentity("identity");
			record.setIdentityShortName("identityShortName");
			record.setFunctionMode("A");
			record.setJournalRef("journalRef");
			record.setOption("TTT");
			record.setProgramRoot("TEMP");
			record.setUser("USER");

			// add the record
			flag = record.update(session);
			if (flag)
			{
				System.out.println("1. Record added...");
			}
			else
			{
				System.out.println("1. Record updated...");
			}

			// retrieval
			JournalHeader rtv = new JournalHeader();
			rtv.chgJournalKey(record.getWorkstationID(), record.getJrnDay(), record.getJrnTime(), record.getJrnSequence(), record
							.getProgramRoot(), record.getFunctionMode());
			flag = rtv.rtvRecord(session);
			if (flag)
			{
				System.out.println("2. Record found...");
			}
			else
			{
				System.out.println("2. Record not found.....");
			}

			// delete the record
			flag = record.delete(session);
			if (flag)
			{
				System.out.println("2. Record deleted...");
			}
			else
			{
				System.out.println("2. Record not found.....");
			}

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