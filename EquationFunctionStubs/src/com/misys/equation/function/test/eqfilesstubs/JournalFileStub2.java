package com.misys.equation.function.test.eqfilesstubs;

import java.sql.Connection;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IOHRecordDao;
import com.misys.equation.common.dao.beans.OHRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.journal.JournalFile;
import com.misys.equation.function.journal.JournalRecord;
import com.misys.equation.function.test.run.FunctionHandlerStubTestCase;
import com.misys.equation.function.tools.XMLToolbox;

public class JournalFileStub2 extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: JournalFileStub2.java 12212 2011-11-01 15:45:23Z lima12 $";
	private DaoFactory daoFactory = new DaoFactory();

	public JournalFileStub2()
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
		JournalFileStub2 test = new JournalFileStub2();
		test.test();
	}

	private void test()
	{
		// Have a bash...
		try
		{
			Connection connection = session.getConnection();
			String unitName = session.getUnitId();
			String kInpLibName = session.getUnit().getKINPLibrary();

			Function function = XMLToolbox.getXMLToolbox().getFunction(session, "ALY", true);

			JournalFile journalFile = new JournalFile(function, kInpLibName);

			session.startEquationTransaction();
			// add the GZ (delete if needed)
			if (journalFile.isFileExists(connection))
			{
				journalFile.dropFile(connection);
			}
			journalFile.writeFile(session, unit.getKINPLibrary());
			session.commitTransaction();

			// add to OH

			session.startEquationTransaction();
			// add a record to the OH file
			IOHRecordDao ohRecordDao = getOHDao(session);
			OHRecordDataModel oHRecordDataModel = new OHRecordDataModel(journalFile.getFileName(), "INP", "PF");
			ohRecordDao.setRecord(oHRecordDataModel);
			ohRecordDao.updateRecord();
			session.commitTransaction();

			// OHRecord oh = new OHRecord(journalFile.getFileName(), "INP", "PF");
			// oh.update(session);
			// connection.commit();

			// start the journalling of the journal file
			JournalRecord record = new JournalRecord(function);
			record.startJournal(session, unitName);
			record.endJournal(session, unitName);
			record.startJournal(session, unitName);

			connection.close();
			System.out.println("Done");
		}
		catch (Exception e)
		{
			try
			{
				session.rollbackTransactions();
			}
			catch (EQException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	private IOHRecordDao getOHDao(EquationStandardSession session)
	{
		IOHRecordDao dao = daoFactory.getOHDao(session, new OHRecordDataModel());
		return dao;
	}
}
