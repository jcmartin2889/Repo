package com.misys.equation.common.access;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import org.apache.commons.collections.map.LinkedMap;

import com.ibm.as400.access.AS400Text;
import com.misys.equation.common.globalprocessing.SystemStatus;
import com.misys.equation.common.globalprocessing.SystemStatusManager;
import com.misys.equation.common.globalprocessing.UnitStatus;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;

public class EquationConsolidator
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationConsolidator.java 12498 2012-02-24 12:05:07Z lima12 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EquationConsolidator.class);
	public static final int MAXRESULTS = 1000000;
	private List<EquationStandardSession> sessions;
	private EquationPVMetaData pvMetaData;
	private byte[][] systemBytes;
	private byte[][] unitIdsBytes;
	private byte[][] unitDescriptionBytes;

	public EquationConsolidator(List<EquationStandardSession> sessions, EquationPVMetaData pvMetaData)
	{
		try
		{
			this.sessions = sessions;
			this.pvMetaData = pvMetaData;
		}
		catch (Exception e)
		{
			LOG.error("Cannot construct EquationConsolidator", e);
		}
	}

	public String executeConsolidatedTableQuery(EquationConsolidatedTable table, String zlslct, String keyLast, int direction,
					int maxResults)
	{
		ResultSet[] resultSets = null;
		Statement[] statements = null;

		ConsolidatorQueryExecThread[] workerThreads = null;
		String sql;
		StringBuilder resultsBuffer = new StringBuilder();

		// determine the list of unavailable units
		String errorCodes = getUnavailableSessions();

		// if this is not a global prompter, then remove the list of error codes! Prompting should work even without GP!
		if (!pvMetaData.isGlobalLibraryPrompt())
		{
			errorCodes = "";
		}

		try
		{
			statements = new Statement[sessions.size()];
			resultSets = new ResultSet[sessions.size()];
			workerThreads = new ConsolidatorQueryExecThread[sessions.size()];
			systemBytes = new byte[sessions.size()][8];
			unitIdsBytes = new byte[sessions.size()][3];
			unitDescriptionBytes = new byte[sessions.size()][20];

			// Place holders for the unit and unit description ...
			String unit = null;
			String system = null;
			String unitDesc = null;
			// Get the index for the unit and unit description positioning (this has been appended to zlslct
			// by getGlobalProcessingFlags) ...
			int start = zlslct.indexOf('[');
			int end = zlslct.indexOf(']');

			// First we check if anything has been appended. There might not be anything if we are not in global processing mode
			// If there is we continue ...
			if (start > -1 && end > -1)
			{
				// Read the string containing the unit and unit description positioning
				String globalParameters = zlslct.substring(start + 1, end);
				// Remove this string from 'zlslct'. We do not want to pass this info as a where clause!
				zlslct = zlslct.substring(end + 1, zlslct.length());

				// Now read the positioning info
				StringTokenizer gpTkn = new StringTokenizer(globalParameters, ",");
				int systemLength = 0;
				int unitLength = 0;
				int unitDescLength = 0;
				while (gpTkn.hasMoreTokens())
				{
					final String name = gpTkn.nextToken();
					if (name.equals("$SYSTEM"))
					{
						final int systemStart = Integer.parseInt(gpTkn.nextToken());
						systemLength = Integer.parseInt(gpTkn.nextToken());
						system = zlslct.substring(systemStart, systemStart + systemLength);
					}

					if (name.equals("$UNIT"))
					{
						final int unitStart = Integer.parseInt(gpTkn.nextToken());
						unitLength = Integer.parseInt(gpTkn.nextToken());
						unit = zlslct.substring(unitStart, unitStart + unitLength);
					}

					if (name.equals("$UNITDESC"))
					{
						final int unitDescStart = Integer.parseInt(gpTkn.nextToken());
						unitDescLength = Integer.parseInt(gpTkn.nextToken());
						unitDesc = zlslct.substring(unitDescStart, unitDescStart + unitDescLength);
					}
				}

				// And finally remove this data from the zlslct. We do not want to include it in the query as there are no columns
				// with zone and description ...
				zlslct = zlslct.substring(systemLength + unitLength + unitDescLength, zlslct.length());
			}

			// Need to convert the ZLSLCT to a SQL WHERE Statement...
			String zlslctWhere = SQLToolbox.getWhereFromZLSLCT(table.getDataStructure(), zlslct);
			sql = SQLToolbox.injectWhereToSQLString(table.getBaseSQL(), zlslctWhere);

			// For each session we create query and pass it to the ConsolidatorQueryExecThread for execution on a separate thread
			for (int i = 0; i < sessions.size(); i++)
			{
				EquationStandardSession session = sessions.get(i);
				// check if wildcards match for server and unit
				boolean wildcardsMatch = true;

				if (system != null && !SQLToolbox.matches(system, session.getSystemId()))
				{
					// unit doesn't match
					wildcardsMatch = false;
				}

				// check if 'unit' doesn't match
				final EquationUnit eqUnit = session.getUnit();
				if (unit != null && !SQLToolbox.matches(unit, eqUnit.getUnitId()))
				{
					// unit doesn't match
					wildcardsMatch = false;
				}

				// check if 'unit' description doesn't match
				if (unitDesc != null && !SQLToolbox.matches(unitDesc, eqUnit.getDescription()))
				{
					// unit doesn't match
					wildcardsMatch = false;
				}

				// populate unit specific information
				systemBytes[i] = (new AS400Text(8, session.getUnit().getCcsid())).toBytes(session.getSystemId());
				unitIdsBytes[i] = (new AS400Text(3, session.getUnit().getCcsid())).toBytes(session.getUnitId());
				unitDescriptionBytes[i] = (new AS400Text(20, session.getUnit().getCcsid())).toBytes(session.getUnit()
								.getDescription().trim());

				// If the unit passed is a wild card (i.e. all units) then we always execute the query on each of the sessions
				// available.
				// If the unit passed matches the current session's unit then we execute the query
				if (wildcardsMatch)
				{
					String adjSQL = SQLToolbox.injectWhereToSQLString(sql, "");

					// inject session information into query
					adjSQL = SQLToolbox.injectVariables(adjSQL, session);

					// Create a thread to do the work for us ... we can do other stuff instead of waiting for this
					// complex query to complete !
					Connection connection;
					if (pvMetaData.isGlobalLibraryPrompt())
					{
						if (i > 0)
						{
							break;
						}
						connection = session.getConnectionWrapper().getGlobalConnection();
					}
					else
					{
						connection = session.getConnectionWrapper().getConnection();
					}

					workerThreads[i] = new ConsolidatorQueryExecThread(i, connection, adjSQL, resultSets);
					workerThreads[i].start();
				}
				// If the unit passed does not match the current session's unit then we DO NOT execute the query
				else
				{
					// no worker thread for me!
					resultSets[i] = SQLToolbox.newEmptyResultSet();
					statements[i] = null;
					workerThreads[i] = null;
				}
			}

			// We need to make sure that all the threads have finished executing the queries on all units prior to
			// proceeding to consolidate the results ...
			for (int i = 0; i < sessions.size(); i++)
			{
				final ConsolidatorQueryExecThread worker = workerThreads[i];
				if (worker != null)
				{
					worker.join();
					resultSets[i] = workerThreads[i].getResultSet();
					statements[i] = workerThreads[i].getStatement();
				}
			}

			// reset the table to reload...
			table.reset();

			Toolbox.printTime("Start:");

			boolean hasNext = table.next(resultSets);
			Map<String, String> records = new LinkedHashMap<String, String>();
			while (hasNext)
			{
				table.getOutputBytes();
				String fullRecord = "";

				// It should pass the pvMetaData into the getOutputString() as it needs to process individual fields in order to
				// parse packed and zoned fields properly. As this has been changed by GP, change the condition to cater only for
				// non-GP PV
				if (pvMetaData.isGlobalConsolidatedPrompt())
				{
					fullRecord = table.getOutputString(null);
				}
				else
				{
					fullRecord = table.getOutputString(pvMetaData);
				}

				// global prompt then include the pre-defined keys of length 31 + table key length
				String key = "";
				if (pvMetaData.isGlobalConsolidatedPrompt())
				{
					key = fullRecord.substring(0, 31 + table.getKeyLength());
				}

				// non-global prompt
				else
				{
					key = fullRecord.substring(0, table.getKeyLength());
				}

				records.put(key, fullRecord);
				hasNext = table.next(resultSets);
			}

			processPaging(records, keyLast, direction, maxResults, resultsBuffer);
		}
		catch (InterruptedException e)
		{
			LOG.error("Cannot execute executeConsolidatedTableQuery", e);
		}
		catch (SQLException e)
		{
			LOG.error("Cannot execute executeConsolidatedTableQuery", e);
		}
		catch (EQException e)
		{
			LOG.error("Cannot execute executeConsolidatedTableQuery", e);
		}
		finally
		{
			for (int i = 0; i < sessions.size(); i++)
			{
				SQLToolbox.close(resultSets[i]);
				SQLToolbox.close(statements[i]);
			}
		}
		return resultsBuffer.append("!#ERRORS#!").append(errorCodes).toString();
	}

	private void processPaging(Map<String, String> records, String keyLast, int direction, int maxResults,
					StringBuilder resultsBuffer)
	{
		LinkedMap linkedMap = new LinkedMap();
		linkedMap.putAll(records);

		// If we are moving forward, its simple!
		// We select the first 'maxcount' rows for the result.
		// All the rest are ignored given that we only need to show maxCount rows in the prompt.
		if (direction == 1)
		{
			int count = -1;
			for (int x = 0; x < linkedMap.size(); x++)
			{
				String key = (String) linkedMap.get(x);
				if (keyLast.equals(key) || keyLast.equals("") || count > -1)
				{
					if (keyLast.equals(key))
					{
						count++;
						continue;
					}
					resultsBuffer.append(linkedMap.getValue(x));
					count++;
					if (count < maxResults && linkedMap.values().size() > (x + 1))
					{
						resultsBuffer.append(EqDataType.GLOBAL_DELIMETER);
					}
					else
					{
						break;
					}
				}
			}
		}
		// If we are moving backward
		else
		{
			int endIndex = linkedMap.indexOf(keyLast) - 1;
			int startIndex = endIndex - maxResults + 1;
			int y = 0;
			for (int x = endIndex; x > startIndex; x--)
			{
				resultsBuffer.append(linkedMap.getValue(x));
				y++;
				if (y < maxResults && x > 0)
				{
					resultsBuffer.append(EqDataType.GLOBAL_DELIMETER);
				}
				else
				{
					break;
				}
			}
		}
	}

	/**
	 * @return a pipe delimited list of unavailable systems/units
	 */
	private String getUnavailableSessions()
	{
		StringBuilder unavailables = new StringBuilder("");
		for (Entry<String, SystemStatus> sStatus : SystemStatusManager.getInstance().getSystemStatusList().entrySet())
		{
			for (Entry<String, UnitStatus> uStatus : sStatus.getValue().getUnitStatus().entrySet())
			{
				if (!uStatus.getValue().isAvailable())
				{
					if (unavailables.toString().equals(""))
					{
						unavailables.append("KSM8010   ");
					}
					if (unavailables.toString().length() > 10)
					{
						unavailables.append("|");
					}
					unavailables.append(sStatus.getValue().getSystemName()).append("/").append(uStatus.getKey());
				}
			}
		}
		return unavailables.toString();
	}

	public List<EquationStandardSession> getSessions()
	{
		return sessions;
	}

	public void returnSessions() throws EQException
	{
		for (EquationStandardSession session : sessions)
		{
			EquationUnit unit = session.getUnit();
			unit.getEquationSessionPool().returnEquationStandardSession(session);
		}
	}

	public EquationPVMetaData getPvMetaData()
	{
		return pvMetaData;
	}

	public byte[] getSystemIdBytes(int i)
	{
		return systemBytes[i];
	}

	public boolean isSystemNull()
	{
		return systemBytes == null;
	}

	public byte[] getUnitIdBytes(int i)
	{
		return unitIdsBytes[i];
	}

	public byte[] getUnitDescriptionBytes(int i)
	{
		return unitDescriptionBytes[i];
	}

	public EquationStandardSession getSession(int i)
	{
		return sessions.get(i);
	}

	private String createSecFilterQuery(final String table)
	{
		if (table.equals("SC01LF")) // Accounts
		{
			return "("
							+ "SCAB||"
							+ "SCAN||"
							+ "SCAS) IN ( SELECT DTAKEY FROM RS WHERE SUBSTRING(RS.DTA,0,8) NOT LIKE 'KSM%' AND SUBSTRING(RS.DTA,0,8) NOT LIKE 'USM%') ";
		}
		else if (table.equals("GF03LF")) // Customers
		{
			return "("
							+ "GFCUS||"
							+ "GFCLC) IN ( SELECT DTAKEY FROM RS WHERE SUBSTRING(RS.DTA,0,8) NOT LIKE 'KSM%' AND SUBSTRING(RS.DTA,0,8) NOT LIKE 'USM%') ";
		}
		else if (table.equals("CA20LF")) // Branches
		{
			return "("
							+ "CABRNM) IN ( SELECT DTAKEY FROM RS WHERE SUBSTRING(RS.DTA,0,8) NOT LIKE 'KSM%' AND SUBSTRING(RS.DTA,0,8) NOT LIKE 'USM%') ";
		}
		else if (table.equals("OS10LF")) // Deals
		{
			return "("
							+ "OSBRNM||"
							+ "OSDLP||"
							+ "OSDLR) IN ( SELECT DTAKEY FROM RS WHERE SUBSTRING(RS.DTA,0,8) NOT LIKE 'KSM%' AND SUBSTRING(RS.DTA,0,8) NOT LIKE 'USM%') ";
		}
		return null;
	}

	private String createTempQuery(final char secType, final String table)
	{
		if (table.equals("SC01LF")) // Accounts
		{
			return "WITH RS AS (SELECT UTW06RFNC(' ','GWR76R',SCAB||SCAN||SCAS,' ',' ','" + secType
							+ "','') AS DTA, SCAB||SCAN||SCAS AS DTAKEY FROM " + table + ")";
		}
		else if (table.equals("GF03LF")) // Customers
		{
			return "WITH RS AS (SELECT UTW06RFNC(' ','GFR70R',GFCUS||GFCLC,' ',' ','" + secType
							+ "','') AS DTA, GFCUS||GFCLC AS DTAKEY FROM " + table + ") ";
		}
		else if (table.equals("CA20LF")) // Branches
		{
			return "WITH RS AS (SELECT UTW06RFNC(' ','CAR73R',CABRNM,' ',' ','" + secType + "','') AS DTA, CABRNM AS DTAKEY FROM "
							+ table + ") ";
		}
		else if (table.equals("OS10LF")) // Deals
		{
			return "WITH RS AS (SELECT UTW06RFNC(' ','OSR34R',OSBRNM||OSDLP||OSDLR,' ',' ','" + secType
							+ "','') AS DTA, OSBRNM||OSDLP||OSDLR AS DTAKEY FROM " + table + ") ";
		}
		return null;

	}

	/**
	 * Given an SQL query this function will generate an new SQL query which includes the security filtering by calling the PV
	 * module functions.
	 * 
	 * @param table
	 *            - The EquationConsolidatedTable
	 * @param sql
	 *            - The old SQL string
	 * 
	 * @param mode
	 *            - the mode of the PV (G/H/T)
	 * @return String - The new SQL string
	 */
	private String getSecureSql(final EquationConsolidatedTable table, final String sql, final char mode)
	{
		// Divide the SQL statement into various part such that we can build the new query with security included
		String[] sqlParts = SQLToolbox.dissectSQLStatement(sql);
		// Read the various parts of the original query ...
		final String selection = (sqlParts[0] != null ? sqlParts[0] : "");
		final String from = (sqlParts[1] != null ? sqlParts[1] : "");
		final String where = (sqlParts[2] != null ? sqlParts[2] : "");
		final String orderBy = (sqlParts[3] != null ? sqlParts[3] : "");

		// Get the table name from the query ...
		String tableName = from.substring(4, from.length()).trim();
		int end = tableName.indexOf(" ");
		if (end != -1)
		{
			tableName = tableName.substring(0, end);
		}

		String tempTableSql = createTempQuery(mode, tableName);
		if (tempTableSql == null)
		{
			return sql;
		}

		// Create the security filtering SQL statement which works on the temporary table defined above ...
		String secFilterSql = null;
		if (where == null || where.trim().length() == 0)
		{
			secFilterSql = "WHERE " + createSecFilterQuery(tableName);
		}
		else
		{
			secFilterSql = "AND " + createSecFilterQuery(tableName);
		}

		String sqlString = tempTableSql + selection + from + where + secFilterSql + orderBy;

		// Finally return the whole query which includes the security SQL ...
		return sqlString;
	}
}