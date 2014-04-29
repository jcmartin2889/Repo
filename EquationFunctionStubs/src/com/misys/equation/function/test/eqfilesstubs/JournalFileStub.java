package com.misys.equation.function.test.eqfilesstubs;

import java.sql.Connection;

import com.misys.equation.function.beans.Function;
import com.misys.equation.function.journal.JournalFile;
import com.misys.equation.function.test.run.FunctionHandlerStubTestCase;
import com.misys.equation.function.tools.XMLToolbox;

public class JournalFileStub extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: JournalFileStub.java 12212 2011-11-01 15:45:23Z lima12 $";

	public JournalFileStub()
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
		JournalFileStub test = new JournalFileStub();
		test.test();
	}

	private void test()
	{
		// Have a bash...
		try
		{
			Connection connection = session.getConnection();
			Function function = XMLToolbox.getXMLToolbox().getFunction(session, "AC2", true);
			JournalFile journalFile = new JournalFile(function, "KINPG39");

			// add the GZ (delete if needed)
			if (journalFile.isFileExists(connection))
			{
				journalFile.dropFile(connection);
			}
			journalFile.writeFile(session, unit.getKINPLibrary());
			connection.commit();

			System.out.println("Done");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}