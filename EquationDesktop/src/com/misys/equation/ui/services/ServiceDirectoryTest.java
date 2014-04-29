package com.misys.equation.ui.services;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Calendar;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.transaction.NotSupportedException;

import org.springframework.jdbc.support.nativejdbc.WebSphereNativeJdbcExtractor;

import bf.com.misys.eqf.types.header.Orig;
import bf.com.misys.eqf.types.header.Overrides;
import bf.com.misys.eqf.types.header.RqHeader;
import bf.com.misys.eqf.types.header.ServiceRqHeader;
import bf.com.misys.eqf.types.header.ServiceRsHeader;

import com.ibm.as400.access.AS400JDBCConnectionHandle;
import com.misys.equation.bf.EquationServiceHandler;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationDataStructureData;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.EquationTransactionManager;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.globalprocessing.SystemStatusManager;
import com.misys.equation.common.globalprocessing.UnitStatus;
import com.misys.equation.common.globalprocessing.UnitStatusObserver;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqTimingTest;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.context.BFEQCredentials;
import com.misys.equation.function.context.EquationFunctionContext;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionInfo;
import com.misys.equation.function.tools.SupervisorToolbox;
import com.misys.equation.ui.tools.EqDesktopToolBox;

public class ServiceDirectoryTest
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ServiceDirectoryTest.java 16593 2013-06-24 15:32:19Z perkinj1 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(ServiceDirectoryTest.class);

	private static String SESSIONID = "SESSIONID";

	private static boolean xaTest = true;
	private static boolean delay = true;
	ServiceDirectory serviceDirectory = null;
	EquationTransactionManager utx = null;
	InitialContext initialContext = null;
	public static final String PARAM_DELIMETER = ":";

	public String xaTests(String sessionIdentifier, String transactionIdentifier) throws NotSupportedException,
					javax.transaction.SystemException
	{
		// Setup ThreadData as soon as we have a session identifier to enable logging by user / ip address
		EqDesktopToolBox.setupThreadData(sessionIdentifier);

		if (transactionIdentifier != null && transactionIdentifier.equals("TEST_CONFIG_FILE"))
		{

			String equationConfiguration = System.getProperty("equationConfiguration.properties");
			if (equationConfiguration != null)
			{

				System.out.println(equationConfiguration);
				System.out.println("tonation D !!");
			}

			return "TRUE";
		}

		if (transactionIdentifier != null && transactionIdentifier.equals("PERF"))
		{
			performanceTest();
			return "TRUE";
		}
		if (transactionIdentifier != null && transactionIdentifier.equals("ExternalInput"))
		{
			// applyUT2();
			return "TRUE";
		}
		if (transactionIdentifier != null && transactionIdentifier.startsWith("userPoolTest"))
		{
			return new Boolean(userPoolTest(transactionIdentifier)).toString();

		}
		if (transactionIdentifier != null && transactionIdentifier.startsWith("closeUserPoolTest"))
		{
			return new Boolean(closeUserPoolTest(transactionIdentifier)).toString();

		}
		if (transactionIdentifier != null && transactionIdentifier.equals("XA"))
		{
			// Conclusion - cleanup of XA connection must be before commit
			// Reuse of connection after cleanup requires logon again
			// UTX Commit destroys the prepared statements. This means logging per LUW.

			// THERE SEEMS TO BE A PROBLEM WITH TOO MANY TRANSACTIONS IN THE SAME SECOND FOR THE SAME OPTION
			try
			{
				serviceDirectory = new ServiceDirectory();
				utx = new EquationTransactionManager();
				initialContext = new InitialContext();

				// No point in doing other XA tests if XA connections fail
				try
				{
					equationXAConnect();
				}
				catch (Exception e)
				{
					e.printStackTrace();
					throw new Exception(e);
				}

				deleteRecordsSQL();
				insertRecordsSQLXA();
				equationXACommit();
				equationXACommitMultipleEQ1DataSourceSameUser();
				equationXACommitMultipleEQ1DataSourceDifferentUser();
				equationXACommitMultipleEQ2DataSource();
				equationXARollback();
				equationXACommitNoBoundaries();
				applyUT2();
				return "TRUE";
			}
			catch (Exception e)
			{
				LOG.error(e);
				return "FALSE";
			}
		}
		if (transactionIdentifier != null && transactionIdentifier.equals("UNITPASS"))
		{
			unitObserverTestPass();
			return "TRUE";
		}
		if (transactionIdentifier != null && transactionIdentifier.equals("UNITFAIL"))
		{
			unitObserverTestFail();
			return "TRUE";
		}
		return "TRUE";
	}
	private void deleteRecordsSQL() throws Exception
	{
		LOG.info("deleteRecordsSQL - start");
		try
		{
			utx.begin();

			DataSource dataSource1 = (DataSource) initialContext.lookup("jdbc/EQ-SLOUGH1-EQ4-XA");
			Connection connection1 = dataSource1.getConnection();

			WebSphereNativeJdbcExtractor jdbcExtractor = new WebSphereNativeJdbcExtractor();
			AS400JDBCConnectionHandle nativeconnection = (AS400JDBCConnectionHandle) jdbcExtractor.getNativeConnection(connection1);
			String jobId1 = nativeconnection.getServerJobIdentifier().substring(20);
			LOG.info(jobId1);

			Statement statement1 = connection1.createStatement();
			statement1.execute("DELETE FROM KFILEQ4/JVPF WHERE JVHRC LIKE 'E%'");
			statement1.close();

			Statement statement2 = connection1.createStatement();
			statement2.execute("DELETE FROM KFILEQ5/JVPF WHERE JVHRC LIKE 'E%'");
			statement2.close();

			utx.commit();
		}
		catch (Exception e)
		{
			LOG.info("deleteRecordsSQL - catch");
			utx.rollback();
			throw (e);
		}
		LOG.info("deleteRecordsSQL - end");
	}

	private void insertRecordsSQLXA() throws Exception
	{
		LOG.info("insertRecordsSQLXA - start");
		try
		{
			utx.begin();

			// Insert the row transaction 1
			DataSource dataSource1 = (DataSource) initialContext.lookup("jdbc/EQ-SLOUGH1-EQ4-XA");
			Connection connection1 = dataSource1.getConnection();

			WebSphereNativeJdbcExtractor jdbcExtractor = new WebSphereNativeJdbcExtractor();
			AS400JDBCConnectionHandle nativeconnection = (AS400JDBCConnectionHandle) jdbcExtractor.getNativeConnection(connection1);
			String jobId1 = nativeconnection.getServerJobIdentifier().substring(20);
			LOG.info(jobId1);

			Statement statement1 = connection1.createStatement();
			String text = jobId1 + " 1 SQL Insert";
			statement1.execute("INSERT INTO KFILEQ4/JVPF (JVHRC, JVHRD) VALUES('E4A', '" + text + "')");
			statement1.close();

			// Insert the row transaction 2
			DataSource dataSource2 = (DataSource) initialContext.lookup("jdbc/EQ-SLOUGH1-EQ5-XA");
			Connection connection2 = dataSource2.getConnection();

			nativeconnection = (AS400JDBCConnectionHandle) jdbcExtractor.getNativeConnection(connection2);
			String jobId2 = nativeconnection.getServerJobIdentifier().substring(20);
			LOG.info(jobId2);

			if (!jobId1.equals(jobId2))
			{
				LOG.info("Different jobs as expected.");
			}
			else
			{
				LOG.info("Same job with 2 different datasources is an error.");
			}

			Statement statement2 = connection2.createStatement();
			text = jobId2 + " 2 SQL Insert";
			statement2.execute("INSERT INTO KFILEQ5/JVPF (JVHRC, JVHRD) VALUES('E5A', '" + text + "')");

			statement2.close();

			utx.commit();

		}
		catch (Exception e)
		{
			LOG.info("insertRecordsSQLXA - catch");
			utx.rollback();
			throw (e);
		}
		LOG.info("insertRecordsSQLXA - end");
	}
	private void equationXAConnect() throws Exception
	{
		LOG.info("equationXACommit - start");
		EquationStandardSession session = null;
		try
		{
			// Try SLOUGH1 to see if CC starts
			EquationCommonContext.getContext().initialiseUnitEnvironment("SLOUGH1", "EQ4", "williae1", "williae1",
							EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN);
			EquationCommonContext.getContext().setXAUsed(xaTest);
			EquationCommonContext.getContext().getEquationUnit("SLOUGH1", "X05").createXADataSource();
			utx.begin();
			String sessionId2 = EquationCommonContext.getContext().getEqSession("SLOUGH1", "EQ4", "WILLIAE1", "WILLIAE1", "",
							"localhost", EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN, EquationCommonContext.SESSION_OTHER_MODE,
							xaTest);

			session = EquationCommonContext.getContext().getEquationUser(sessionId2).getSession();

			utx.commit();
		}
		catch (Exception e)
		{
			LOG.info("equationXACommit - catch");
			utx.rollback();

		}
		finally
		{
			if (session != null)
			{
				session.getConnectionWrapper().cleanupXAConnection();
			}

		}

		LOG.info("equationXACommit - end");
	}
	private void equationXACommit() throws Exception
	{
		LOG.info("equationXACommit - start");
		EquationStandardSession session = null;
		try
		{
			utx.begin();

			String sessionId = EquationCommonContext.getContext().getEqSession("SLOUGH1", "EQ4", "WILLIAE1", "WILLIAE1", "",
							"localhost", EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN, EquationCommonContext.SESSION_OTHER_MODE,
							xaTest, null);

			session = EquationCommonContext.getContext().getEquationUser(sessionId).getSession();
			String jobId1 = session.getConnectionWrapper().getJobId().substring(20);
			LOG.info(jobId1);

			DataSource dataSource2 = (DataSource) initialContext.lookup("jdbc/EQ-SLOUGH1-EQ4-XA");
			Connection connection2 = dataSource2.getConnection();
			WebSphereNativeJdbcExtractor jdbcExtractor = new WebSphereNativeJdbcExtractor();
			AS400JDBCConnectionHandle nativeconnection = (AS400JDBCConnectionHandle) jdbcExtractor.getNativeConnection(connection2);
			// Do we get the same connection as same datasoource?
			String jobId2 = nativeconnection.getServerJobIdentifier().substring(20);
			LOG.info(jobId2);

			int status = utx.getStatus();

			applyHCI(session, "E4B", jobId1 + " 1 Commit");

			Statement statement2 = connection2.createStatement();
			String text = jobId2 + " 2 SQL Insert with E4B";
			statement2.execute("INSERT INTO KFILEQ4/JVPF (JVHRC, JVHRD) VALUES('E4C', '" + text + "')");

			statement2.close();

			session.getConnectionWrapper().cleanupXAConnection();
			EquationCommonContext.getContext().logOffSession(session.getSessionIdentifier());
			utx.commit();
		}
		catch (Exception e)
		{
			LOG.info("equationXACommit - catch");
			session.getConnectionWrapper().cleanupXAConnection();
			EquationCommonContext.getContext().logOffSession(session.getSessionIdentifier());
			utx.rollback();
			throw (e);
		}
		LOG.info("equationXACommit - end");
	}

	private void equationXACommitMultipleEQ1DataSourceSameUser() throws Exception
	{
		LOG.info("equationXACommitMultipleEQ1DataSourceSameUser - start");
		EquationStandardSession session = null;
		try
		{
			utx.begin();
			String sessionId1 = EquationCommonContext.getContext().getEqSession("SLOUGH1", "EQ4", "WILLIAE1", "WILLIAE1", "",
							"localhost", EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN, EquationCommonContext.SESSION_OTHER_MODE,
							xaTest, null);

			session = EquationCommonContext.getContext().getEquationUser(sessionId1).getSession();
			String jobId1 = session.getConnectionWrapper().getJobId().substring(20);
			LOG.info(jobId1);

			int status = utx.getStatus();

			applyHCI(session, "E4D", jobId1 + " 1");

			applyHCI(session, "E4E", jobId1 + " 2");

			session.getConnectionWrapper().cleanupXAConnection();
			EquationCommonContext.getContext().logOffSession(session.getSessionIdentifier());
			utx.commit();
		}
		catch (Exception e)
		{
			session.getConnectionWrapper().cleanupXAConnection();
			EquationCommonContext.getContext().logOffSession(session.getSessionIdentifier());
			utx.rollback();
			throw (e);
		}
		// ----------------------------------
		try
		{
			utx.begin();
			String sessionId2 = EquationCommonContext.getContext().getEqSession("SLOUGH1", "EQ4", "WILLIAE1", "WILLIAE1", "",
							"localhost", EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN, EquationCommonContext.SESSION_OTHER_MODE,
							xaTest, null);

			session = EquationCommonContext.getContext().getEquationUser(sessionId2).getSession();
			String jobId1 = session.getConnectionWrapper().getJobId().substring(20);
			LOG.info(jobId1);

			int status = utx.getStatus();

			applyHCI(session, "E4F", jobId1 + " 3");

			applyHCI(session, "E4G", jobId1 + " 4");

			session.getConnectionWrapper().cleanupXAConnection();
			EquationCommonContext.getContext().logOffSession(session.getSessionIdentifier());
			utx.commit();
		}
		catch (Exception e)
		{
			LOG.info("equationXACommitMultipleEQ1DataSourceSameUser - catch");
			session.getConnectionWrapper().cleanupXAConnection();
			EquationCommonContext.getContext().logOffSession(session.getSessionIdentifier());
			utx.rollback();
			throw (e);
		}
		LOG.info("equationXACommitMultipleEQ1DataSourceSameUser - end");
	}
	private void equationXACommitMultipleEQ1DataSourceDifferentUser() throws Exception
	{
		LOG.info("equationXACommitMultipleEQ1DataSourceDifferentUser - start");

		EquationStandardSession session = null;
		EquationStandardSession session2 = null;
		try
		{
			utx.begin();
			// 1
			String sessionId = EquationCommonContext.getContext().getEqSession("SLOUGH1", "EQ4", "WILLIAE1", "WILLIAE1", "",
							"localhost", EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN, EquationCommonContext.SESSION_OTHER_MODE,
							xaTest, null);
			session = EquationCommonContext.getContext().getEquationUser(sessionId).getSession();
			String jobId1 = session.getConnectionWrapper().getJobId().substring(20);
			LOG.info(jobId1);

			// 2
			String sessionId2 = EquationCommonContext.getContext().getEqSession("SLOUGH1", "EQ4", "EQUIADMIN", "EQUIADMIN", "",
							"localhost", EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN, EquationCommonContext.SESSION_OTHER_MODE,
							xaTest, null);
			session2 = EquationCommonContext.getContext().getEquationUser(sessionId2).getSession();
			String jobId2 = session2.getConnectionWrapper().getJobId().substring(20);
			LOG.info(jobId2);

			int status = utx.getStatus();

			applyHCI(session, "E4H", jobId1 + " 1");

			applyHCI(session2, "E4I", jobId2 + " 2");

			// ----------------------------------

			status = utx.getStatus();

			applyHCI(session, "E4J", jobId1 + " 3");

			applyHCI(session2, "E4K", jobId2 + " 4");

			session.getConnectionWrapper().cleanupXAConnection();
			EquationCommonContext.getContext().logOffSession(session.getSessionIdentifier());
			session2.getConnectionWrapper().cleanupXAConnection();
			EquationCommonContext.getContext().logOffSession(session2.getSessionIdentifier());
			utx.commit();

		}
		catch (Exception e)
		{
			LOG.info("equationXACommitMultipleEQ1DataSourceDifferentUser - catch");
			session.getConnectionWrapper().cleanupXAConnection();
			EquationCommonContext.getContext().logOffSession(session.getSessionIdentifier());
			session2.getConnectionWrapper().cleanupXAConnection();
			EquationCommonContext.getContext().logOffSession(session2.getSessionIdentifier());
			utx.rollback();
			throw (e);
		}
		LOG.info("equationXACommitMultipleEQ1DataSourceDifferentUser - end");
	}
	private void equationXACommitMultipleEQ2DataSource() throws Exception
	{
		LOG.info("equationXACommitMultipleEQ2DataSource - start");
		EquationStandardSession session = null;
		EquationStandardSession session2 = null;
		try
		{
			utx.begin();
			// 1
			String sessionId = EquationCommonContext.getContext().getEqSession("SLOUGH1", "EQ4", "WILLIAE1", "WILLIAE1", "",
							"localhost", EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN, EquationCommonContext.SESSION_OTHER_MODE,
							xaTest, null);

			session = EquationCommonContext.getContext().getEquationUser(sessionId).getSession();
			String jobId1 = session.getConnectionWrapper().getJobId().substring(20);
			LOG.info(jobId1);
			// 2
			String sessionId2 = EquationCommonContext.getContext().getEqSession("SLOUGH1", "EQ5", "WILLIAE1", "WILLIAE1", "",
							"localhost", EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN, EquationCommonContext.SESSION_OTHER_MODE,
							xaTest, null);

			session2 = EquationCommonContext.getContext().getEquationUser(sessionId2).getSession();
			String jobId2 = session2.getConnectionWrapper().getJobId().substring(20);
			LOG.info(jobId2);

			int status = utx.getStatus();

			applyHCI(session, "E4L", jobId1 + " 1");

			applyHCI(session2, "E5M", jobId2 + " 2");

			// ----------------------------------

			status = utx.getStatus();

			applyHCI(session, "E4N", jobId1 + " 3");

			applyHCI(session2, "E5O", jobId2 + " 4");

			session.getConnectionWrapper().cleanupXAConnection();
			EquationCommonContext.getContext().logOffSession(session.getSessionIdentifier());
			session2.getConnectionWrapper().cleanupXAConnection();
			EquationCommonContext.getContext().logOffSession(session2.getSessionIdentifier());
			utx.commit();

		}
		catch (Exception e)
		{
			LOG.info("equationXACommitMultipleEQ2DataSource - catch");
			session.getConnectionWrapper().cleanupXAConnection();
			EquationCommonContext.getContext().logOffSession(session.getSessionIdentifier());
			session2.getConnectionWrapper().cleanupXAConnection();
			EquationCommonContext.getContext().logOffSession(session2.getSessionIdentifier());
			utx.rollback();
			throw (e);
		}

		LOG.info("equationXACommitMultipleEQ2DataSource - end");
	}

	private void equationXARollback() throws Exception
	{
		LOG.info("equationXARollback - start");
		utx.begin();
		String sessionId = EquationCommonContext.getContext().getEqSession("SLOUGH1", "EQ4", "WILLIAE1", "WILLIAE1", "",
						"localhost", EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN, EquationCommonContext.SESSION_OTHER_MODE,
						xaTest, null);

		EquationStandardSession session = EquationCommonContext.getContext().getEquationUser(sessionId).getSession();
		String jobId1 = session.getConnectionWrapper().getJobId().substring(20);
		LOG.info(jobId1);

		DataSource dataSource2 = (DataSource) initialContext.lookup("jdbc/EQ-SLOUGH1-EQ4-XA");
		Connection connection2 = dataSource2.getConnection();
		WebSphereNativeJdbcExtractor jdbcExtractor = new WebSphereNativeJdbcExtractor();
		AS400JDBCConnectionHandle nativeconnection = (AS400JDBCConnectionHandle) jdbcExtractor.getNativeConnection(connection2);
		String jobId2 = nativeconnection.getServerJobIdentifier().substring(20);
		LOG.info(jobId2);

		int status = utx.getStatus();

		applyHCI(session, "E4P", jobId1 + " 1 Rollback");

		Statement statement2 = connection2.createStatement();
		String text = jobId2 + " 2 SQL Insert Rollback";
		statement2.execute("INSERT INTO KFILEQ4/JVPF (JVHRC, JVHRD) VALUES('E4Q', '" + text + "')");

		statement2.close();

		session.getConnectionWrapper().cleanupXAConnection();
		EquationCommonContext.getContext().logOffSession(session.getSessionIdentifier());
		utx.rollback();
		LOG.info("equationXARollback - end");
	}

	private void equationXACommitNoBoundaries() throws Exception
	{
		LOG.info("equationXACommitNoBoundaries - start");
		String sessionId = EquationCommonContext.getContext().getEqSession("SLOUGH1", "EQ4", "WILLIAE1", "WILLIAE1", "",
						"localhost", EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN, EquationCommonContext.SESSION_OTHER_MODE,
						xaTest, null);

		EquationStandardSession session = EquationCommonContext.getContext().getEquationUser(sessionId).getSession();
		String jobId1 = session.getConnectionWrapper().getJobId().substring(20);
		LOG.info(jobId1);

		try
		{

			int status = utx.getStatus();

			applyHCI(session, "E4R", jobId1 + " 1 No boundary");
			// WHAT happens? Is the transaction committed?
		}
		catch (Exception e)
		{
			LOG.info("equationXACommitNoBoundaries - catch");
			session.getConnectionWrapper().cleanupXAConnection();
			EquationCommonContext.getContext().logOffSession(session.getSessionIdentifier());
			session.getConnection().rollback();
			throw (e);
		}
		LOG.info("equationXACommitNoBoundaries - end");
	}
	private void applyHCI(EquationStandardSession session, String code, String description) throws EQException
	{

		ServiceDirectory serviceDirectory = new ServiceDirectory();

		try
		{
			if (delay == true)
			{
				delay();
			}
			// Format the API data
			EquationStandardTransaction hold1 = new EquationStandardTransaction("J46FRRHCI", session);
			// Set the transaction type
			hold1.setMode("A");

			hold1.setFieldValue("GZWSID", session.getConnectionWrapper().getJobId().substring(22, 26));
			hold1.setFieldValue("GZHRC", code);
			hold1.setFieldValue("GZHRD", description);

			String data = Toolbox.cvtBytesToHexString(hold1.getBytes());
			// Post the transaction 1 API
			String result = serviceDirectory.applyTransactionData(session.getSessionIdentifier(), data, "A", "A", "HCI", "");
			if (!result.trim().equals(""))
			{
				throw new EQException("API failure HCI - " + result);
			}

		}
		catch (EQException e)
		{
			throw e;
		}
	}
	private void applyUT2() throws Exception
	{
		EquationStandardSession session = null;
		try
		{
			BFEQCredentials credentials = new BFEQCredentials("williae1", "williae1",
							EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN, "");
			String sessionId = EquationFunctionContext.getContext().getEqSession("SLOUGH1", "EQ5", credentials, "localhost",
							EquationCommonContext.SESSION_OTHER_MODE, null);

			session = EquationCommonContext.getContext().getEquationUser(sessionId).getSession();
			String jobId1 = session.getConnectionWrapper().getJobId().substring(20);
			LOG.info(jobId1);

			// Format the API data

			EquationUser user = EquationCommonContext.getContext().getEquationUser(sessionId);
			FunctionInfo functionInfo = new FunctionInfo(sessionId, "");
			FunctionHandler functionHandler = new FunctionHandler(user, functionInfo);

			// Maintain HCI using service UT2
			System.out.println("--------------------------- 2");

			functionHandler.doNewTransaction("UT2", "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
			functionData.chgFieldInputValue("A_HRC", "GBP");
			// Retrieve does not work.
			// functionHandler.applyRetrieveTransaction();

			functionData.chgFieldInputValue("A_HRD", "UT2 Service");
			EquationDataStructureData journalData = functionData.cvtToDS(session);
			// ALEX, applyTransaction does not work now because of BF.
			// functionHandler.applyTransaction();
			// USER LOCATOR NOT FOUND.!!!!
			functionHandler.applyTransaction(journalData, "M", false, null);
			if (!functionHandler.getFhd().isUpdateMade())
			{
				throw new EQException("API failure UW1 - AREC = "
								+ functionHandler.getFhd().getFunctionMsgManager().getFunctionMessages().rtvMessagesAsStringArray());
			}

		}
		catch (EQException e)
		{
			session.getConnectionWrapper().cleanupXAConnection();
			EquationCommonContext.getContext().logOffSession(session.getSessionIdentifier());
			session.getConnection().rollback();
			throw e;
		}
	}
	private void delay()
	{// Cause a delay to prevent sequence number clash
		int x = 0;
		for (int i = 0; i < 100; i++)
		{
			x = x + 1;
		}
	}
	private void performanceTest()
	{
		try
		{
			boolean more = true;
			BFEQCredentials credentials = new BFEQCredentials("EQUIADMIN", "EQUIADMIN",
							EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN, SESSIONID);
			EquationFunctionContext.getContext().getEqSession("SLOUGH1", "AL7", credentials, "",
							EquationCommonContext.SESSION_API_MODE, null);
			testAA1();
			if (more)
			{
				testAA1();
				testAA1();
				testAA1();
				testAA1();
			}
			testAA2();
			if (more)
			{
				testAA2();
				testAA2();
				testAA2();
				testAA2();
			}
			testAA3();
			if (more)
			{
				testAA3();
				testAA3();
				testAA3();
				testAA3();
			}
			testAA4();
			if (more)
			{
				testAA4();
				testAA4();
				testAA4();
				testAA4();
			}
			testAA5();
			if (more)
			{
				testAA5();
				testAA5();
				testAA5();
				testAA5();
			}
			testAA6();
			if (more)
			{
				testAA6();
				testAA6();
				testAA6();
				testAA6();
			}
			testAA7();
			if (more)
			{
				testAA7();
				testAA7();
				testAA7();
				testAA7();
			}
			testAA8();
			if (more)
			{
				testAA8();
				testAA8();
				testAA8();
				testAA8();
				testAA8();
			}

			testAZ1();
			if (more)
			{
				testAZ1();
				testAZ1();
				testAZ1();
				testAZ1();
			}
			testAZ2();
			if (more)
			{
				testAZ2();
				testAZ2();
				testAZ2();
				testAZ2();
			}

		}
		catch (Exception e)
		{
			LOG.error(e);
		}
		finally
		{
			EquationFunctionContext.getContext().logOffSession(SESSIONID);
		}
	}
	// create the function handler
	private FunctionHandler getFunctionHandler(EquationUser user, String sessionId, String name)
	{
		FunctionInfo functionInfo = new FunctionInfo(sessionId, name);
		FunctionHandler functionHandler = new FunctionHandler(user, functionInfo);
		return functionHandler;
	}

	public boolean testAA1()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			String id = "TEST_AA1";
			String option = "AA1";
			EquationUser user = EquationCommonContext.getContext().getEquationUser(SESSIONID);
			String date = Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT).replaceAll(" ", "").replaceAll(":",
							"");
			System.out.println("------------------------------- 1");
			functionHandler = getFunctionHandler(user, SESSIONID, "");

			EqTimingTest.printStartTime(id, "");
			functionHandler.doNewTransaction(option, "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			functionData.chgFieldInputValue("ASI_AB", "9132");
			functionData.chgFieldInputValue("ASI_AN", "234567");
			functionData.chgFieldInputValue("ASI_AS", "001");
			functionData.chgFieldInputValue("ASI_TCD", "510");
			EqTimingTest.printInterimTime(id, "retrieving....");
			functionHandler.applyRetrieveTransaction();
			functionData.chgFieldInputValue("ASI_TCCY", "GBP");
			functionData.chgFieldInputValue("ASI_TAMA", "1T");
			functionData.chgFieldInputValue("ASI_DRF", "Test-Stub");
			functionData.chgFieldInputValue("ASI_NR1", date);

			EqTimingTest.printInterimTime(id, "apply....");
			functionHandler.applyTransaction();
			EqTimingTest.printTime(id, "");
			Toolbox.printList(functionHandler.rtvFunctionMessages().rtvDsepms());

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

			return (journalHeader != null);
		}
		catch (Exception e)
		{
			LOG.error(e);
			return false;
		}
		finally
		{
			if (functionHandler != null)
			{
				if (functionHandler.getFhd().getFunctionSession() != null)
				{
					SupervisorToolbox.removeSession(functionHandler.getFhd().getFunctionSession(), functionHandler.getFhd()
									.getEquationUser().getEquationUnit());
				}
			}
		}
	}

	public boolean testAA2()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			String id = "TEST_AA2";
			String option = "AA2";
			String date = Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT).replaceAll(" ", "").replaceAll(":",
							"");
			System.out.println("------------------------------- 1");
			EquationUser user = EquationCommonContext.getContext().getEquationUser(SESSIONID);
			functionHandler = getFunctionHandler(user, SESSIONID, "");

			EqTimingTest.printStartTime(id, "");
			functionHandler.doNewTransaction(option, "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			functionData.chgFieldInputValue("ASI_AB", "9132");
			functionData.chgFieldInputValue("ASI_AN", "234567");
			functionData.chgFieldInputValue("ASI_AS", "001");
			functionData.chgFieldInputValue("ASI_TCD", "510");
			EqTimingTest.printInterimTime(id, "retrieving....");
			functionHandler.applyRetrieveTransaction();
			functionData.chgFieldInputValue("ASI_TCCY", "GBP");
			functionData.chgFieldInputValue("ASI_TAMA", "1T");
			functionData.chgFieldInputValue("ASI_DRF", "Test-Stub");
			functionData.chgFieldInputValue("ASI_NR1", date);

			EqTimingTest.printInterimTime(id, "apply....");
			functionHandler.applyTransaction();
			EqTimingTest.printTime(id, "");
			Toolbox.printList(functionHandler.rtvFunctionMessages().rtvDsepms());

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

			return (journalHeader != null);
		}
		catch (Exception e)
		{
			LOG.error(e);
			return false;
		}
		finally
		{
			if (functionHandler != null)
			{
				if (functionHandler.getFhd().getFunctionSession() != null)
				{
					SupervisorToolbox.removeSession(functionHandler.getFhd().getFunctionSession(), functionHandler.getFhd()
									.getEquationUser().getEquationUnit());
				}
			}
		}
	}

	public boolean testAA3()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			String id = "TEST_AA3";
			String option = "AA3";
			String date = Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT).replaceAll(" ", "").replaceAll(":",
							"");
			System.out.println("------------------------------- 1");
			EquationUser user = EquationCommonContext.getContext().getEquationUser(SESSIONID);
			functionHandler = getFunctionHandler(user, SESSIONID, "");

			EqTimingTest.printStartTime(id, "");
			functionHandler.doNewTransaction(option, "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			functionData.chgFieldInputValue("TH_HZAB", "3000");
			functionData.chgFieldInputValue("TH_HZAN", "L00001");
			functionData.chgFieldInputValue("TH_HZAS", "001");
			EqTimingTest.printInterimTime(id, "retrieving....");
			functionHandler.applyRetrieveTransaction();
			EqTimingTest.printTime(id, "");

			int totalRows = functionData.getRepeatingDataManagers().get(0).totalRows();
			System.out.println("total records retrieve is : " + totalRows);
			Toolbox.printList(functionHandler.rtvFunctionMessages().rtvDsepms());

			// validate number of records
			if (totalRows > 300)
			{
				System.out.println("OK");
			}
			else
			{
				System.out.println("ERROR");
			}
			return (totalRows > 300);
		}
		catch (Exception e)
		{
			LOG.error(e);
			return false;
		}
		finally
		{
			if (functionHandler != null)
			{
				if (functionHandler.getFhd().getFunctionSession() != null)
				{
					SupervisorToolbox.removeSession(functionHandler.getFhd().getFunctionSession(), functionHandler.getFhd()
									.getEquationUser().getEquationUnit());
				}
			}
		}
	}

	public boolean testAA4()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			String id = "TEST_AA4";
			String option = "AA4";
			String date = Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT).replaceAll(" ", "").replaceAll(":",
							"");
			System.out.println("------------------------------- 1");
			EquationUser user = EquationCommonContext.getContext().getEquationUser(SESSIONID);
			functionHandler = getFunctionHandler(user, SESSIONID, "");

			EqTimingTest.printStartTime(id, "");
			functionHandler.doNewTransaction(option, "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			functionData.chgFieldInputValue("TH_HZAB", "3000");
			functionData.chgFieldInputValue("TH_HZAN", "L00001");
			functionData.chgFieldInputValue("TH_HZAS", "001");
			EqTimingTest.printInterimTime(id, "retrieving....");
			functionHandler.applyRetrieveTransaction();
			EqTimingTest.printTime(id, "");

			int totalRows = functionData.getRepeatingDataManagers().get(0).totalRows();
			System.out.println("total records retrieve is : " + totalRows);
			Toolbox.printList(functionHandler.rtvFunctionMessages().rtvDsepms());

			// validate number of records
			if (totalRows > 300)
			{
				System.out.println("OK");
			}
			else
			{
				System.out.println("ERROR");
			}
			return (totalRows > 300);
		}
		catch (Exception e)
		{
			LOG.error(e);
			return false;
		}
		finally
		{
			if (functionHandler != null)
			{
				if (functionHandler.getFhd().getFunctionSession() != null)
				{
					SupervisorToolbox.removeSession(functionHandler.getFhd().getFunctionSession(), functionHandler.getFhd()
									.getEquationUser().getEquationUnit());
				}
			}
		}
	}

	public boolean testAA5()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			String id = "TEST_AA5";
			String option = "AA5";
			String date = Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT).replaceAll(" ", "").replaceAll(":",
							"");
			System.out.println("------------------------------- 1");
			EquationUser user = EquationCommonContext.getContext().getEquationUser(SESSIONID);
			functionHandler = getFunctionHandler(user, SESSIONID, "");

			EqTimingTest.printStartTime(id, "");
			functionHandler.doNewTransaction(option, "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			functionData.chgFieldInputValue("TH_HZAB", "3000");
			functionData.chgFieldInputValue("TH_HZAN", "L00001");
			functionData.chgFieldInputValue("TH_HZAS", "001");
			EqTimingTest.printInterimTime(id, "retrieving....");
			functionHandler.applyRetrieveTransaction();
			EqTimingTest.printTime(id, "");

			int totalRows = functionData.getRepeatingDataManagers().get(0).totalRows();
			System.out.println("total records retrieve is : " + totalRows);
			Toolbox.printList(functionHandler.rtvFunctionMessages().rtvDsepms());

			// validate number of records
			if (totalRows > 300)
			{
				System.out.println("OK");
			}
			else
			{
				System.out.println("ERROR");
			}
			return (totalRows > 300);
		}
		catch (Exception e)
		{
			LOG.error(e);
			return false;
		}
		finally
		{
			if (functionHandler != null)
			{
				if (functionHandler.getFhd().getFunctionSession() != null)
				{
					SupervisorToolbox.removeSession(functionHandler.getFhd().getFunctionSession(), functionHandler.getFhd()
									.getEquationUser().getEquationUnit());
				}
			}
		}
	}

	public boolean testAA6()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			String id = "TEST_AA6";
			String option = "AA6";
			String date = Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT).replaceAll(" ", "").replaceAll(":",
							"");
			System.out.println("------------------------------- 1");
			EquationUser user = EquationCommonContext.getContext().getEquationUser(SESSIONID);
			functionHandler = getFunctionHandler(user, SESSIONID, "");

			EqTimingTest.printStartTime(id, "");
			functionHandler.doNewTransaction(option, "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
			EqTimingTest.printTime(id, "");

			int totalRows = functionData.getRepeatingDataManagers().get(0).totalRows();
			System.out.println("total records retrieve is : " + totalRows);
			Toolbox.printList(functionHandler.rtvFunctionMessages().rtvDsepms());

			// validate number of records
			if (totalRows > 300)
			{
				System.out.println("OK");
			}
			else
			{
				System.out.println("ERROR");
			}
			return (totalRows > 300);
		}
		catch (Exception e)
		{
			LOG.error(e);
			return false;
		}
		finally
		{
			if (functionHandler != null)
			{
				if (functionHandler.getFhd().getFunctionSession() != null)
				{
					SupervisorToolbox.removeSession(functionHandler.getFhd().getFunctionSession(), functionHandler.getFhd()
									.getEquationUser().getEquationUnit());
				}
			}
		}
	}

	public boolean testAA7()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			String id = "TEST_AA7";
			String option = "AA7";
			String date = Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT).replaceAll(" ", "").replaceAll(":",
							"");
			System.out.println("------------------------------- 1");
			EquationUser user = EquationCommonContext.getContext().getEquationUser(SESSIONID);
			functionHandler = getFunctionHandler(user, SESSIONID, "");

			EqTimingTest.printStartTime(id, "");
			functionHandler.doNewTransaction(option, "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
			EqTimingTest.printTime(id, "");

			int totalRows = functionData.getRepeatingDataManagers().get(0).totalRows();
			System.out.println("total records retrieve is : " + totalRows);
			Toolbox.printList(functionHandler.rtvFunctionMessages().rtvDsepms());

			// validate number of records
			if (totalRows > 300)
			{
				System.out.println("OK");
			}
			else
			{
				System.out.println("ERROR");
			}
			return (totalRows > 300);
		}
		catch (Exception e)
		{
			LOG.error(e);
			return false;
		}
		finally
		{
			if (functionHandler != null)
			{
				if (functionHandler.getFhd().getFunctionSession() != null)
				{
					SupervisorToolbox.removeSession(functionHandler.getFhd().getFunctionSession(), functionHandler.getFhd()
									.getEquationUser().getEquationUnit());
				}
			}
		}
	}

	public boolean testAA8()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			String id = "TEST_AA8";
			String option = "AA8";
			String date = Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT).replaceAll(" ", "").replaceAll(":",
							"");
			System.out.println("------------------------------- 1");
			EquationUser user = EquationCommonContext.getContext().getEquationUser(SESSIONID);
			functionHandler = getFunctionHandler(user, SESSIONID, "");

			EqTimingTest.printStartTime(id, "");
			functionHandler.doNewTransaction(option, "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
			EqTimingTest.printTime(id, "");

			int totalRows = functionData.getRepeatingDataManagers().get(0).totalRows();
			System.out.println("total records retrieve is : " + totalRows);
			Toolbox.printList(functionHandler.rtvFunctionMessages().rtvDsepms());

			// validate number of records
			if (totalRows > 300)
			{
				System.out.println("OK");
			}
			else
			{
				System.out.println("ERROR");
			}
			return (totalRows > 300);
		}
		catch (Exception e)
		{
			LOG.error(e);
			return false;
		}
		finally
		{
			if (functionHandler != null)
			{
				if (functionHandler.getFhd().getFunctionSession() != null)
				{
					SupervisorToolbox.removeSession(functionHandler.getFhd().getFunctionSession(), functionHandler.getFhd()
									.getEquationUser().getEquationUnit());
				}
			}
		}
	}

	public boolean testAZ1()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			String id = "TEST_AZ1";
			String option = "AZ1";
			Calendar cal = Calendar.getInstance();

			String date = String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + String.valueOf(cal.getTimeInMillis());
			System.out.println("------------------------------- 1");
			EquationUser user = EquationCommonContext.getContext().getEquationUser(SESSIONID);
			functionHandler = getFunctionHandler(user, SESSIONID, "");

			EqTimingTest.printStartTime(id, "");
			functionHandler.doNewTransaction(option, "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			EqTimingTest.printInterimTime(id, "retrieving....");
			functionHandler.applyRetrieveTransaction();
			functionData.chgFieldInputValue("DLR", date);

			EqTimingTest.printInterimTime(id, "apply....");
			functionHandler.applyTransaction();
			EqTimingTest.printTime(id, "");
			Toolbox.printList(functionHandler.rtvFunctionMessages().rtvDsepms());

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

			return (journalHeader != null);
		}
		catch (Exception e)
		{
			LOG.error(e);
			return false;
		}
		finally
		{
			if (functionHandler != null)
			{
				if (functionHandler.getFhd().getFunctionSession() != null)
				{
					SupervisorToolbox.removeSession(functionHandler.getFhd().getFunctionSession(), functionHandler.getFhd()
									.getEquationUser().getEquationUnit());
				}
			}
		}
	}

	public boolean testAZ2()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			String id = "TEST_AZ2";
			String option = "AZ2";
			Calendar cal = Calendar.getInstance();

			String date = String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + String.valueOf(cal.getTimeInMillis());
			System.out.println("------------------------------- 1");
			EquationUser user = EquationCommonContext.getContext().getEquationUser(SESSIONID);
			functionHandler = getFunctionHandler(user, SESSIONID, "");

			EqTimingTest.printStartTime(id, "");
			functionHandler.doNewTransaction(option, "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			EqTimingTest.printInterimTime(id, "retrieving....");
			functionHandler.applyRetrieveTransaction();
			functionData.chgFieldDbValue("DLR", date);

			EqTimingTest.printInterimTime(id, "apply....");
			functionHandler.applyTransaction();
			EqTimingTest.printTime(id, "");
			Toolbox.printList(functionHandler.rtvFunctionMessages().rtvDsepms());

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

			return (journalHeader != null);
		}
		catch (Exception e)
		{
			LOG.error(e);
			return false;
		}
		finally
		{
			if (functionHandler != null)
			{
				if (functionHandler.getFhd().getFunctionSession() != null)
				{
					SupervisorToolbox.removeSession(functionHandler.getFhd().getFunctionSession(), functionHandler.getFhd()
									.getEquationUser().getEquationUnit());
				}
			}
		}
	}
	public boolean unitObserverTestPass()

	{
		String[] unitIds = new String[1];
		String[] systemIds = new String[1];
		String[] userNames = new String[1];
		String[] passwords = new String[1];
		int sleepTime = 5000;

		unitIds[0] = "EQ6";
		systemIds[0] = "SLOUGH1";
		userNames[0] = "EQUIADMIN";
		passwords[0] = "EQUIADMIN";

		EquationStandardSession session = EquationCommonContext.getContext().getEquationStandardSession("SLOUGH1", "EQ6",
						"EQUIADMIN", "EQUIADMIN");
		try
		{
			EquationCommonContext.getContext().runSystemStatusManagerMonitor(unitIds, systemIds, userNames, passwords, sleepTime);
		}
		catch (EQException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UnitStatus unitStatus = SystemStatusManager.getInstance().getUnitStatus(session.getSystemId(), session.getUnitId());

		unitStatus.addObserver(new UnitStatusObserver());

		return true;

	}
	public boolean unitObserverTestFail()

	{
		String[] unitIds = new String[2];
		String[] systemIds = new String[1];
		String[] userNames = new String[1];
		String[] passwords = new String[1];
		int sleepTime = 5000;

		unitIds[0] = "EQ6";
		unitIds[1] = "EQ7";
		systemIds[0] = "SLOUGH1";
		userNames[0] = "EQUIADMIN";
		passwords[0] = "EQUIADMIN";

		EquationStandardSession session = EquationCommonContext.getContext().getEquationStandardSession("SLOUGH1", "EQ6",
						"EQUIADMIN", "EQUIADMIN");
		try
		{
			EquationCommonContext.getContext().runSystemStatusManagerMonitor(unitIds, systemIds, userNames, passwords, sleepTime);
		}
		catch (EQException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UnitStatus unitStatus = SystemStatusManager.getInstance().getUnitStatus(session.getSystemId(), session.getUnitId());

		unitStatus.addObserver(new UnitStatusObserver());

		return true;

	}
	public boolean userPoolTest(String testParameter)
	{
		try
		{
			String[] paramList = testParameter.split(PARAM_DELIMETER);
			String dataSourceName = paramList[1].trim();
			String optionId = paramList[2].trim();
			ServiceRqHeader serviceRqHeader = new ServiceRqHeader();

			Orig orig = new Orig();
			orig.setAppId("appId");

			Overrides overrides = new Overrides();
			// Generate warning messages
			overrides.setOverrideType("N");

			RqHeader rqHeader = new RqHeader();
			rqHeader.setOrig(orig);
			rqHeader.setOverrides(overrides);

			serviceRqHeader.setRqHeader(rqHeader);
			serviceRqHeader.setOptionId(optionId);
			serviceRqHeader.setServiceMode("M");

			EquationServiceHandler equationServiceHandler = new EquationServiceHandler(dataSourceName);

			Object inputServiceData = equationServiceHandler.getServiceDataClass(optionId);
			equationServiceHandler.setFieldValue(inputServiceData, "HCI_HRC_holdCode", "XXX");
			equationServiceHandler.setFieldValue(inputServiceData, "HCI_HRD_holdDescription", "description");
			// TODO try setting non-string data. More helper methods required in EquationServiceHandler
			// equationServiceHandler.setFieldValue(inputServiceData, "HCI_DED_defaultExpiryDate", "0");

			equationServiceHandler.process(serviceRqHeader, inputServiceData);

			ServiceRsHeader serviceRsHeader = equationServiceHandler.getServiceRsHeader();
			Object outputServiceData = equationServiceHandler.getOutputServiceData();

			// TODO create good toPrint methods for the headers
			System.out.println("---------Applied Txn ------");

			System.out.println("ServiceRsHeader " + serviceRsHeader.toString());
			System.out.println("OutputServiceData " + outputServiceData.toString());

			System.out.println("Done");

			return true;
		}
		catch (EQException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean closeUserPoolTest(String testParameter)
	{
		try
		{
			String[] paramList = testParameter.split(PARAM_DELIMETER);
			String dataSourceName = paramList[1].trim();
			String[] dataSourceList = dataSourceName.split("-");
			String system = dataSourceList[1].trim();
			String unit = dataSourceList[2].trim();
			EquationCommonContext.getContext().getEquationUnit(system, unit).close(dataSourceName);
			return true;
		}
		catch (EQException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
}