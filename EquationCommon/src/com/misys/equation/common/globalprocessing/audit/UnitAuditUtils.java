package com.misys.equation.common.globalprocessing.audit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IGAVRecordDao;
import com.misys.equation.common.dao.beans.GAVRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.SQLToolbox;

public class UnitAuditUtils
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UnitAuditUtils.java 11588 2011-08-11 02:34:12Z bterrado $";

	/**
	 * Runs custom SQL updates
	 * 
	 * @param query
	 * @param session
	 * @return boolean
	 * @throws EQException
	 */
	public static boolean updateUnitGeneric(EquationStandardSession session, String query) throws EQException
	{
		boolean success = false;
		final Connection conn = session.getConnection();

		try
		{
			Statement stmt = null;

			try
			{
				stmt = conn.createStatement();
				int result = stmt.executeUpdate(query);
				if (result > 0)
				{
					success = true;
				}
			}
			finally
			{
				// Close statement since pooled connections don't do this automatically (but this DOES close the ResultSet as well!)
				SQLToolbox.close(stmt);
			}
		}
		catch (SQLException sqle)
		{
			// wrap in EQException
			throw new EQException(sqle);
		}

		return success;
	}

	/**
	 * Runs custom SQL queries
	 * 
	 * @param query
	 * @return
	 * @throws EQException
	 */
	public static List<Object[]> runUnitGenericQuery(EquationStandardSession session, String query) throws EQException
	{
		final Connection conn = session.getConnection();

		try
		{
			// store results into a list representing the rows
			final List<Object[]> results = new ArrayList<Object[]>();

			Statement stmt = null;
			try
			{
				stmt = conn.createStatement();
				final ResultSet rs = stmt.executeQuery(query);
				final ResultSetMetaData rsmd = rs.getMetaData();
				final int cols = rsmd.getColumnCount();

				while (rs.next())
				{
					// store the row's columns into an object array
					final Object[] row = new Object[cols];
					for (int i = 0; i < cols; i++)
					{
						// for maximum flexibility, use the getObject method
						row[i] = rs.getObject(i + 1);
					}

					// aggregate results into a list
					results.add(row);
				}

				// return aggregated results
				return results;
			}
			finally
			{
				// Close statement since pooled connections don't do this automatically (but this DOES close the ResultSet as well!)
				SQLToolbox.close(stmt);
			}
		}
		catch (SQLException sqle)
		{
			// wrap in EQException
			throw new EQException(sqle);
		}
	}

	public static GAVRecordDataModel getUnitPropData(EquationStandardSession session, long sequenceId) throws EQException
	{
		final IGAVRecordDao dao = new DaoFactory().getGAVDao(session, new GAVRecordDataModel(), true);
		final List<GAVRecordDataModel> data = GlobalAuditUtils.coerce(dao.getRecordBy("GAVDSEQ = " + sequenceId));
		if (data.size() >= 1)
		{
			return data.get(0);
		}
		else
		{
			return null;
		}
	}

	public static int getSQLCountValue(EquationStandardSession session, String query) throws EQException
	{
		List<Object[]> results = UnitAuditUtils.runUnitGenericQuery(session, query);
		if (results.size() > 0 && results.get(0).length > 0)
		{
			return ((Number) (results.get(0)[0])).intValue();
		}
		// indicating not found
		return 0;
	}

	/**
	 * Run a custom SQL query and store result to a HashMap
	 * 
	 * @param query
	 * @return
	 * @throws EQException
	 */
	public static List<Map<String, Object>> getGZRowObject(EquationStandardSession session, String query) throws EQException
	{
		final Connection conn = session.getConnection();
		try
		{
			// store results into a list representing the rows
			final List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();

			Statement stmt = null;
			try
			{
				stmt = conn.createStatement();
				final ResultSet rs = stmt.executeQuery(query);
				try
				{
					final ResultSetMetaData rsmd = rs.getMetaData();
					final int cols = rsmd.getColumnCount();

					while (rs.next())
					{
						// store the row's columns into an object array
						final Map<String, Object> row = new HashMap<String, Object>();
						for (int i = 0; i < cols; i++)
						{
							// for maximum flexibility, use the getObject method
							row.put(rsmd.getColumnName(i + 1), rs.getObject(i + 1));
						}

						// aggregate results into a list
						results.add(row);
					}

					// return aggregated results
					return results;
				}
				finally
				{
					SQLToolbox.close(rs);
				}
			}
			finally
			{
				SQLToolbox.close(stmt);
			}
		}
		catch (SQLException sqle)
		{
			// wrap in EQException
			throw new EQException(sqle);
		}
	}
}
