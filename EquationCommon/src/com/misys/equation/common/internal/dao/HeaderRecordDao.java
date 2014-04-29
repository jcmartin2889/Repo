package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.misys.equation.common.dao.IHeaderRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.HeaderRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.HeaderRecordRowMapper;

/**
 * This the HeaderRecord DAO implementation. <br>
 * This class is going to provide all back-end services for Data Propagation Audit Log Headers.
 * 
 * @author barcelr1
 */
public class HeaderRecordDao extends AbsEquationDao implements IHeaderRecordDao
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: HeaderRecordDao.java 9781 2010-11-10 14:23:12Z MACDONP1 $";
	private static final String GAUPF_TABLE = "GAU10LF";
	private static final String GAAPF_TABLE = "GAA10LF";

	private static final String FROM_CLAUSE = " FROM " + GAUPF_TABLE + " A LEFT OUTER JOIN " + GAAPF_TABLE
					+ " B ON A.GAUSEQ = B.GAASEQ " + " WHERE (B.GAASEQ IS NULL OR (B.GAARSEQ IN  (SELECT MAX(GAARSEQ) from "
					+ GAAPF_TABLE + " " + " where GAASEQ=B.GAASEQ))) ";

	private static final String GAUPF_FIELDS = " A.GAUSEQ, A.GAUWSID, A.GAUUSID, A.GAUTSTP, A.GAUSO, A.GAUUNSO, "
					+ "A.GAUSVSO, A.GAUPDTE, A.GAUBRNM, A.GAUOID, A.GAURTYP, A.GAUREF, A.GAUSTS, "
					+ "A.GAUACK, A.GAUUNTO, A.GAUSVTO, A.GAUDSEQ, A.GAUUSEQ, A.GAUGLO ";

	private static final String GAAPF_FIELDS = " B.GAATSTP, B.GAATYP, B.GAARSEQ ";

	public HeaderRecordDao()
	{
		super();
	}

	public HeaderRecordDataModel getMyDataModel()
	{
		HeaderRecordDataModel dataModel = null;
		AbsRecord record = getRecord();
		if (record instanceof HeaderRecordDataModel)
		{
			dataModel = (HeaderRecordDataModel) getRecord();
		}
		return dataModel;
	}

	/**
	 * Checks is a Header record exists.
	 */

	public boolean checkIfHeaderRecordIsInTheDB()
	{
		StringBuilder sqlBuilder = new StringBuilder(1024);
		int result = 0;

		sqlBuilder.append("SELECT COUNT(*) ");
		sqlBuilder.append(FROM_CLAUSE);
		sqlBuilder.append(" AND ");
		sqlBuilder.append("A.GAUSEQ='" + getMyDataModel().getSequenceId() + "'");
		JdbcTemplate select = getJdbcTemplate();

		if (LOG.isDebugEnabled())
		{

			LOG.debug(new StringBuilder("The executed sql is: ").append(sqlBuilder.toString()).toString());
		}

		result = select.queryForInt(sqlBuilder.toString());
		return result != 0;
	}

	/**
	 * Checks is a Header record exists.
	 */

	public boolean checkIfHeaderRecordIsInTheDB(long seqId)
	{
		getMyDataModel().setSequenceId(seqId);
		return checkIfHeaderRecordIsInTheDB();

	}

	@Override
	protected String getParameterizedFields()
	{
		return null;
	}

	@Override
	protected String getFields()
	{
		return null;
	}

	@Override
	protected String getParameters()
	{
		return null;
	}

	@Override
	public Object[] getParameterizedFieldsValues()
	{
		return null;
	}

	/**
	 * This method is going execute an SQL query using the filter criteria.
	 * 
	 * @param whereClause
	 *            this is the <code>String</code> that represent the SQL filter.
	 * @return a <code>List</code> that contains the records
	 */
	public List<AbsRecord> getRecordBy(String whereClause)
	{
		return getRecordBy(whereClause, new HeaderRecordRowMapper());
	}

	@SuppressWarnings("unchecked")
	public List<AbsRecord> getRecords()
	{
		StringBuilder sqlBuilder = new StringBuilder(1024);
		List<AbsRecord> dataModels = null;

		sqlBuilder.append("SELECT ");
		sqlBuilder.append(GAUPF_FIELDS + ", ");
		sqlBuilder.append(GAAPF_FIELDS);
		sqlBuilder.append(FROM_CLAUSE);
		JdbcTemplate select = getJdbcTemplate();

		if (LOG.isDebugEnabled())
		{

			LOG.debug(new StringBuilder("The executed sql is: ").append(sqlBuilder.toString()).toString());
		}
		dataModels = select.query(sqlBuilder.toString(), new HeaderRecordRowMapper());
		return dataModels;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}

	/**
	 * This method is going execute an SQL query using the filter criteria. A Left outer join of table GAUPF and GAAPF is done to
	 * extract last update and last action fields on the AuditLogHeader.
	 * 
	 * @param whereClause
	 *            this is the <code>String</code> that represent the sql filter.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	@SuppressWarnings("unchecked")
	public List<AbsRecord> getRecordsBy(String whereClause)
	{
		StringBuilder sqlBuilder = new StringBuilder(1024);
		List<AbsRecord> dataModels = null;

		sqlBuilder.append("SELECT ");
		sqlBuilder.append(GAUPF_FIELDS + ", ");
		sqlBuilder.append(GAAPF_FIELDS);
		sqlBuilder.append(FROM_CLAUSE);

		// Only add the where clause if there is one:
		if (whereClause != null && whereClause.length() > 0)
		{
			sqlBuilder.append("AND A.");
			sqlBuilder.append(whereClause);
		}

		JdbcTemplate select = getJdbcTemplate();

		if (LOG.isDebugEnabled())
		{
			LOG.debug(new StringBuilder("The executed sql is: ").append(sqlBuilder.toString()).toString());
		}
		dataModels = select.query(sqlBuilder.toString(), new HeaderRecordRowMapper());
		return dataModels;
	}

	/**
	 * This method will get the latest copy of the AuditHeader.
	 * 
	 * @param seqId
	 *            this is the <code>sequenceId</code> of the GAURecordDataModel
	 */
	@SuppressWarnings("unchecked")
	public HeaderRecordDataModel getAuditHeader(long seqId)
	{
		StringBuilder sqlBuilder = new StringBuilder(1024);
		List<HeaderRecordDataModel> dataModels = null;

		// build left outer join query
		sqlBuilder.append("SELECT ");
		sqlBuilder.append(GAUPF_FIELDS + ", ");
		sqlBuilder.append(GAAPF_FIELDS);
		sqlBuilder.append(FROM_CLAUSE);
		sqlBuilder.append("AND A.GAUSEQ = " + seqId);
		JdbcTemplate select = getJdbcTemplate();

		if (LOG.isDebugEnabled())
		{
			LOG.debug(new StringBuilder("The executed sql is: ").append(sqlBuilder.toString()).toString());
		}

		dataModels = select.query(sqlBuilder.toString(), new HeaderRecordRowMapper());
		for (Object dataModel : dataModels)
		{
			if (dataModel instanceof HeaderRecordDataModel)
			{
				return (HeaderRecordDataModel) dataModel;
			}
		}
		return new HeaderRecordDataModel();
	}

	public HeaderRecordDataModel getHeaderRecord()
	{
		long seqId = getMyDataModel().getSequenceId();
		return getAuditHeader(seqId);
	}

	@Override
	protected String getWhereConditionBaseOnIdRecord()
	{
		return null;
	}
}