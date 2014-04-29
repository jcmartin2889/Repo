package com.misys.equation.common.perf;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ibm.as400.access.AS400JDBCConnection;
import com.ibm.as400.access.AS400JDBCDataSource;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;

/**
 * Implementation for SQLTest
 */
public class SQLTest
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SQLTest.java 14411 2012-09-04 12:34:23Z williae1 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(SQLTest.class);

	private static final String server = "10.117.200.78";
	private static final String user = "TIDBA";
	private static final String password = "TIDBA1";
	private static final String librariesT23 = "KINPT23,KFILT23,KWRKT23,KLIBT23,REPEQNT23,REPBASET23,CMN_FX310,CMN_FX309,CMN_FX308,CMN_FX307,CMN_FX306,CMN_EQ39,EQLIB39,EQLIB382,EQLIB381,EQLIB38,EQLIB37,EQLIB36,V36BASELIB,QGPL,QTEMP,QRPG,QMQM, INTBASELIB";
	private static final String libraries = librariesT23;
	private static final String unitId = "T02";
	private static final int numberOfRuns = 5;

	// private static final String server = "slough1";
	// private static final String user = "equiadmin";
	// private static final String password = "equiadmin";
	// private static final String librariesEQ7 =
	// "KINPEQ7,KFILEQ7,KWRKEQ7,KLIBEQ7,CMN_FX504,CMN_FX503,CMN_FX502,CMN_FX501,CMN_CX040,SMC_FX402,CMN_FX401,EQLIB391,EQLIB39,EQLIB382,EQLIB381,EQLIB38,EQLIB37,EQLIB36,V36BASELIB,QGPL,QTEMP,QRPG,QMQM,EQUTILITY,UTILITY";
	// private static final String libraries = librariesEQ7;
	// private static final String unitId = "EQ7";
	// private static final int numberOfRuns = 5;

	private static final String statementCommand = "SELECT GFCUS || GFCLC AS DTAKEY, UTW06RFNC('','GFR71R', GFCUS || GFCLC,'N','N','H','') AS DTA FROM GF01LF  WHERE  (GFCUS>'      ' OR (GFCUS='      ' AND GFCLC>'   ') ) AND SUBSTR(UTW06RFNC('','GFR71R', GFCUS || GFCLC,'N','N','H','') ,2,2)<>'SM'  ORDER BY GFCUS ASC, GFCLC ASC FETCH FIRST 251 ROWS ONLY";

	public static void main(String[] args)
	{
		try
		{

			new SQLTest().test();

		}
		catch (EQException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void test() throws Exception
	{
		try
		{
			Connection connection = getSingleConnection(server, user, password);
			String jobId = ((AS400JDBCConnection) connection).getServerJobIdentifier();
			System.out.println("STARTED Unit: " + unitId + " Job: " + jobId);

			logIntoEQ3(connection, unitId);

			// If we have a connection delete existing record and insert new record
			if (connection != null)
			{
				for (int runNumber = 0; runNumber < numberOfRuns; runNumber++)
				{
					if (runNumber == 0)
					{
						runQuery(connection, true, runNumber);
					}
					else
					{

						runQuery(connection, false, runNumber);
					}
				}

				connection.close();

			}
			System.out.println("FINISHED SUCCESSFULLY");
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	public void runQuery(Connection connection, boolean printDetails, int runNumber) throws SQLException
	{
		try
		{

			// record time
			long startTime = System.nanoTime();

			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = statement.executeQuery(statementCommand);

			// record time
			long endTime = System.nanoTime();
			int rowNumber = 0;
			if (printDetails)
			{
				// show the rows returned

				while (resultSet.next())
				{
					rowNumber++;
					System.out.println(Integer.toString(rowNumber) + " " + resultSet.getString("DTAKEY"));
				}
			}

			resultSet.close();
			statement.close();

			// log the time taken to make the SQL call
			System.out.println("Run number: " + Integer.toString(runNumber + 1) + " Time taken : "
							+ ((endTime - startTime) / 1000000) + "ms " + "SQL: " + statementCommand);

		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	public Connection getSingleConnection(String systemId, String userId, String password) throws SQLException
	{
		AS400JDBCDataSource as400dataSource = null;

		Connection connection = null;

		try
		{
			as400dataSource = new AS400JDBCDataSource(systemId);
			as400dataSource.setUser(userId);
			as400dataSource.setPassword(password);

			as400dataSource.setPrompt(false);
			as400dataSource.setTranslateBinary(false);
			as400dataSource.setNaming("SYSTEM");
			as400dataSource.setTrace(false);

			as400dataSource.setLibraries(libraries);

			connection = as400dataSource.getConnection();
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			// connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			// connection.setReadOnly(true);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return connection;

	}
	/**
	 * Log the user into Equation 3
	 * 
	 * @param connection
	 *            - the AS400 connection
	 * @param unitId
	 *            - the unit Id
	 * @throws Exception
	 */
	private static void logIntoEQ3(Connection connection, String unitId) throws Exception
	{
		// Have a bash...
		try
		{
			// create the statement
			Statement statement = connection.createStatement();

			// Clear QTEMP
			statement.execute(SQLToolbox.getQcmdexcString("CLRLIB QTEMP"));
			connection.commit();

			// Set the library list
			statement.execute("CALL KAPUNLIBL ('" + unitId + "','    ')");
			connection.commit();

			// Set DAJOBCTLE
			statement.execute("CALL UTM83C (' ')");
			connection.commit();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

}