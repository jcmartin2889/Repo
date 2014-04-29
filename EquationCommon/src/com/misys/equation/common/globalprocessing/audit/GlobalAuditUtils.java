package com.misys.equation.common.globalprocessing.audit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.SystemValue;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationSystem;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IDao;
import com.misys.equation.common.dao.IGAVRecordDao;
import com.misys.equation.common.dao.IGPRRecordDao;
import com.misys.equation.common.dao.IGPURecordDao;
import com.misys.equation.common.dao.IGPXRecordDao;
import com.misys.equation.common.dao.beans.APJRecordDataModel;
import com.misys.equation.common.dao.beans.GAARecordDataModel;
import com.misys.equation.common.dao.beans.GAVRecordDataModel;
import com.misys.equation.common.dao.beans.GPRRecordDataModel;
import com.misys.equation.common.dao.beans.GPURecordDataModel;
import com.misys.equation.common.dao.beans.GPXRecordDataModel;
import com.misys.equation.common.internal.dao.GAARecordDao;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;

public class GlobalAuditUtils
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalAuditUtils.java 13723 2012-07-02 09:55:17Z whittap1 $";
	/** */
	public static final String GAU_SEQ = "GAUPF_SEQ";
	/** */
	public static final String GAV_SEQ = "GAVPF_SEQ";
	/** Logger for this class */
	private static final EquationLogger LOG = new EquationLogger(GlobalAuditUtils.class);

	// FIXME
	public static long generateNextSequence(IDao dao, String seqTable)
	{
		// TODO seqTable is the sequence table to get the sequence from i.e. "GAUPF_SEQ" for GAU10LF
		/*
		 * TODO this can be externalize into a properties file or xml file with mappings using dao's name mapped to the sequence
		 * table. for now we'e using String constants..
		 */
		String sql = "SELECT next value for " + seqTable + " FROM sysibm/sysdummy1";

		List<Map<String, Object>> result;
		result = dao.runSql(sql);
		Long sequence = null;

		for (Map<String, Object> listOrderedMap : result)
		{
			sequence = ((Number) listOrderedMap.get("00001")).longValue();
		}

		if (sequence == null)
		{
			throw new RuntimeException("There was problem trying to create the sequence.");
		}

		return sequence;
	}

	private GlobalAuditUtils()
	{
	}

	public enum SeqType
	{
		AUDITHEADER("AUDITHEADER"), AUDITPROPDATA("AUDITPROPDATA");

		private final String value;

		SeqType(String value)
		{
			this.value = value;
		}

		public String getValue()
		{
			return value;
		}
	}

	/**
	 * Runs custom SQL updates
	 * 
	 * @param query
	 * @param session
	 * @return boolean
	 * @throws EQException
	 */
	public static boolean updateGlobalGeneric(EquationStandardSession session, String query) throws EQException
	{
		boolean success = false;
		final Connection conn = session.getConnectionWrapper().getGlobalConnection();

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
	public static List<Object[]> runGlobalGenericQuery(EquationStandardSession session, String query) throws EQException
	{
		final Connection conn = session.getConnectionWrapper().getGlobalConnection();
		try
		{
			try
			{
				// store results into a list representing the rows
				final List<Object[]> results = new ArrayList<Object[]>();

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
						SQLToolbox.close(rs);
					}
				}
				finally
				{
					SQLToolbox.close(stmt);
				}
			}
			finally
			{
				SQLToolbox.close(conn);
			}
		}
		catch (SQLException sqle)
		{
			// wrap in EQException
			throw new EQException(sqle);
		}
	}

	/**
	 * Retrieves ALL units from GPXPF except the defined fromSystem and fromUnit.
	 * 
	 * @param session
	 * @param ruleId
	 * @param fromSystem
	 * @param fromUnit
	 * @return
	 */
	public static Set<SystemUnit> getNonOriginatingSystemUnits(EquationStandardSession session, String ruleId, String fromSystem,
					String fromUnit)
	{
		Set<SystemUnit> systemUnit = new HashSet<SystemUnit>();
		String whereClause = "NOT (GPXSYS = '" + fromSystem + "' AND GPXUNC = '" + fromUnit + "')";

		final IGPXRecordDao dao = new DaoFactory().getGPXRecordDao(session, new GPXRecordDataModel());
		List<GPXRecordDataModel> records = coerce(dao.getRecordBy(whereClause.toString()));
		for (GPXRecordDataModel record : records)
		{
			SystemUnit unit = new SystemUnit(record.getSystemName(), record.getUnitMnemonic());
			if (!systemUnit.contains(unit))
			{
				systemUnit.add(unit);
			}
		}
		return systemUnit;
	}

	/**
	 * Retrieves all system/units from GPUPF for a specified rule.
	 * 
	 * @param session
	 *            - session
	 * @param ruleId
	 *            - ruleId
	 * @param type
	 *            - 'O' for orginating units, 'T' for terminating units
	 * @return
	 */
	public static Set<SystemUnit> getGPUPFUnits(EquationStandardSession session, String ruleId, String type)
	{
		String whereClause = "GPURID='" + ruleId + "' AND GPUTYP='" + type + "'";

		Set<SystemUnit> systemUnit = new HashSet<SystemUnit>();

		final IGPURecordDao dao = new DaoFactory().getGPUDao(session, new GPURecordDataModel());
		List<GPURecordDataModel> records = coerce(dao.getRecordBy(whereClause.toString()));

		for (GPURecordDataModel record : records)
		{
			SystemUnit unit = new SystemUnit(record.getServerId(), record.getUnitMnemonic());
			if (!systemUnit.contains(unit))
			{
				systemUnit.add(unit);

			}
		}
		return systemUnit;
	}

	public static List<GPRRecordDataModel> getGPRPFUnits(EquationStandardSession session, String whereClause)
	{
		final IGPRRecordDao dao = new DaoFactory().getGPRDao(session, new GPRRecordDataModel());
		return coerce(dao.getRecordBy(whereClause.toString()));
	}

	/**
	 * Converts a generic list from one type to another (assuming the conversion is valid).
	 * 
	 * @param genericList
	 *            A generic List
	 * @return A generics-enabled list of type List&lg;T&gt;
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> coerce(List genericList)
	{
		return genericList;
	}

	/**
	 * Returns the QDATETIME system value on the iseries converted to milliseconds precision (local time).
	 * 
	 * @param as400
	 * @return The QDATETIME value returned by the iseries converted to millisecond precision.
	 * 
	 * @throws EQException
	 */
	public static Calendar getQDateTime(AS400 as400) throws EQException
	{
		LOG.debug("getServerTime(" + as400.getSystemName() + ")");
		try
		{
			final SystemValue qDateTime = new SystemValue(as400, "QDATETIME");
			final String dateTime = (String) qDateTime.getValue();
			if (dateTime == null || dateTime.length() != 20)
			{
				throw new EQException("QDATETIME is not available or is invalid: " + dateTime);
			}

			Calendar serverTime = Toolbox.convertAS400TimeStampToCalendar(dateTime);

			return serverTime;
		}
		catch (Exception e)
		{
			LOG.error("Failed to retrieve QDATETIME system value for [" + as400.getSystemName() + "].", e);
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
	}

	public static int getNextSequenceGlobal(EquationStandardSession session, String query) throws EQException
	{
		int seq = 0;
		final Connection conn = session.getConnectionWrapper().getGlobalConnection();
		try
		{

			Statement stmt = conn.createStatement();
			try
			{
				final ResultSet rs = stmt.executeQuery(query);
				try
				{
					if (rs.next())
					{
						seq = rs.getInt(1) + 1;
					}
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

		return seq;
	}

	public static GAVRecordDataModel getGlobalPropData(EquationStandardSession session, long sequenceId) throws EQException
	{
		final IGAVRecordDao dao = new DaoFactory().getGAVDao(session, new GAVRecordDataModel());
		final List<GAVRecordDataModel> data = coerce(dao.getRecordBy("GAVDSEQ = " + sequenceId));
		if (data.size() >= 1)
		{
			return data.get(0);
		}
		else
		{
			return null;
		}
	}

	public static void insertPropData(EquationStandardSession session, long auditHeaderId, AuditStatus status, ApplyType applyType,
					String errorMessages) throws EQException
	{
		EquationSystem eqSystem = session.getUnit().getEquationSystem();
		final AS400 as400 = eqSystem.getAS400();
		try
		{
			Timestamp timeStamp = new java.sql.Timestamp(getQDateTime(as400).getTimeInMillis());

			int maxRetrySeq = getNextApplyDataRetrySeq(session, auditHeaderId);

			// create GAAPF record
			GAARecordDataModel gaaGlobal = new GAARecordDataModel();
			gaaGlobal.setSequenceId(auditHeaderId);
			gaaGlobal.setRetrySequence(maxRetrySeq); // increment retry seq
			gaaGlobal.setRetryUser(session.getEquationUserId()); // use user Id from session
			gaaGlobal.setApplyType(applyType.getValue()); // NONE("0"), AUTO("1"),MANUAL("2"), ACK("3"),DEL("4"),RESET("5")
			// RETRY("6");
			gaaGlobal.setApplyStatus(status.getValue());
			gaaGlobal.setApplicationMessages(errorMessages);
			gaaGlobal.setApplyTimestamp(timeStamp);

			// gloabl connection
			GAARecordDao dao = (GAARecordDao) new DaoFactory().getGAADao(session, new GAARecordDataModel());
			dao.insertRecord(gaaGlobal);
		}
		finally
		{
			eqSystem.returnAS400(as400);
		}
	}

	private static int getNextApplyDataRetrySeq(EquationStandardSession session, long auditHeaderId) throws EQException
	{
		String query = "SELECT COALESCE(MAX(GAARSEQ),0) FROM GAAPF WHERE GAASEQ=" + auditHeaderId;
		return getNextSequenceGlobal(session, query);
	}

	public static int getGALActionSequence(EquationStandardSession session, long auditHeaderId) throws EQException
	{
		String query = "SELECT COALESCE(MAX(GALLSEQ),0) FROM GALPF WHERE GALSEQ = " + auditHeaderId;

		return getNextSequenceGlobal(session, query);
	}

	public static int getSQLCountValue(EquationStandardSession session, String query) throws EQException
	{
		List<Object[]> results = GlobalAuditUtils.runGlobalGenericQuery(session, query);
		int count = 0;
		if (results.size() > 0 && results.get(0).length > 0)
		{
			count = ((Number) (results.get(0)[0])).intValue();
		}
		// indicating not found
		return count;
	}

	public byte[] convertCCSIDGZImage(String optionId, String gzFile, byte[] dsaim, int sourceCCSID, int targetCCSID,
					EquationStandardSession session)
	{
		String query = "SELECT * FROM APJPF WHERE APJARF = '" + optionId + "' and APJFIL = '" + gzFile + "'";
		List<Object[]> resultSet = null;
		byte[] convertedByteData = new byte[9999];
		int length = 0;

		try
		{
			String convertedData = "";
			resultSet = runGlobalGenericQuery(session, query);
			for (int x = 0; x < resultSet.size(); x++)
			{
				Object rowData = resultSet.get(x);
				APJRecordDataModel dataModel = (APJRecordDataModel) rowData;
				length = Integer.parseInt(dataModel.getApiFieldLength());
				convertedData = Toolbox.convertAS400TextIntoCCSID(dsaim, length, sourceCCSID);
				if (convertedData.contains("3F"))
				{
					// use system option GP_SUBST_CHAR to set the value

				}
				convertedByteData = Toolbox.convertTextIntoAS400BytesCCSID(convertedData, length, targetCCSID, 0);
			}
		}
		catch (EQException e)
		{
			LOG.error(e);
		}
		return convertedByteData;
	}
}