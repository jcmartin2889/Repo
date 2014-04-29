package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IWERecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.WERecordDataModel;
import com.misys.equation.common.internal.dao.mappers.WERecordRowMapper;

/**
 * This the WE-Record Dao implementation. <br>
 * This class is going to provide all back-end services for WE-Record.
 * 
 * @author deroset
 */
public class WERecordDao extends AbsEquationDao implements IWERecordDao
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WERecordDao.java 12452 2012-01-12 17:14:25Z lima12 $";
	public WERecordDao()
	{
		super();
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @param sqlWhereStatement
	 *            - the WHERE clause of the SQL statement
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisWERecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisWERecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public WERecordDataModel getMyDataModel()
	{
		WERecordDataModel WERecordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof WERecordDataModel)
		{
			WERecordDataModel = (WERecordDataModel) getRecord();
		}
		return WERecordDataModel;
	}

	/**
	 * This method will return a <code>String</code> which represents and sql where condition base on the record id.<br>
	 * For example <code>id = getDataModel.getId()</code>
	 * 
	 * @return a <code>String</code> which represents and sql filter.
	 */
	@Override
	protected String getWhereConditionBaseOnIdRecord()
	{
		StringBuilder whereCondition = new StringBuilder("WESID='");
		whereCondition.append(getMyDataModel().getSessionId());
		whereCondition.append("'and WETID='");
		whereCondition.append(getMyDataModel().getTransactionId());
		whereCondition.append("'and WEOID='");
		whereCondition.append(getMyDataModel().getOptionId());
		whereCondition.append("'and WEUID2='");
		whereCondition.append(getMyDataModel().getUserId());
		whereCondition.append("'");
		return whereCondition.toString();
	}

	/**
	 * Returns a list of name and ? pairs in the format of:
	 * <p>
	 * field1=?, field2=?, field3=?, ...
	 * 
	 * @return a list of name and ? pairs
	 */
	@Override
	protected String getParameterizedFields()
	{
		String fields = " WEJBD=?, WEJBN=?, WEAUID=?, WEASTS=?, WEAUTH=?, WEOID=?, WEONM=?, WEAPPC=?, WESAPC=?, WESLT=?, WESCRN=?, WEVFRY=?, WEONOM=?, WEINP=?, WEMNT=?, WECNL=?, WECKEY=?, WEUID2=?, WESID=?, WETID=?, WEDTE=?, WETIM=?, WEALRT=?, WESSET=?, WELSST=?, WEOFFL=?, WEUID=?, WERTXT=?, @EM1=?, @EM2=?, @EM3=?, @EM4=?, @EM5=?, @EM6=?, @EM7=?, @EM8=?, @EM9=?, @EM10=?, @EM11=?, @EM12=?, @EM13=?, @EM14=?, @EM15=?, @EM16=?, @EM17=?, @EM18=?, @EM19=?, @EM20=?, WEABRN=?, WEAAMT=? ";
		return fields;
	}

	/**
	 * Returns a list of the filed's name
	 * 
	 * @return a list of the filed's name
	 */
	@Override
	protected String getFields()
	{
		String fields = " WEJBD, WEJBN, WEAUID, WEASTS, WEAUTH, WEOID, WEONM, WEAPPC, WESAPC, WESLT, WESCRN, WEVFRY, WEONOM, WEINP, WEMNT, WECNL, WECKEY, WEUID2, WESID, WETID, WEDTE, WETIM, WEALRT, WESSET, WELSST, WEOFFL, WEUID, WERTXT, @EM1, @EM2, @EM3, @EM4, @EM5, @EM6, @EM7, @EM8, @EM9, @EM10, @EM11, @EM12, @EM13, @EM14, @EM15, @EM16, @EM17, @EM18, @EM19, @EM20, WEABRN, WEAAMT ";
		return fields;
	}

	/**
	 * Returns a list of the filed's parameters
	 * 
	 * @return the list of the filed's parameters
	 */
	@Override
	protected String getParameters()
	{
		String fields = " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
		return fields;
	}

	/**
	 * This method create an array that contains the fields values and Its types. <br>
	 * It will be used by jdbc <code>PreparedStatement</code>
	 * 
	 * @return An array that contains the fields values and Its types.
	 */
	@Override
	public Object[] getParameterizedFieldsValues()
	{

		WERecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getJobDesc(), dataModel.getJobNum(), dataModel.getAuthorisor(),
						dataModel.getAuthStat(), dataModel.getAuthLevel(), dataModel.getOptionId(), dataModel.getOptionTitle(),
						dataModel.getAppCode(), dataModel.getAuthAppCode(), dataModel.getDefEntryData(), dataModel.getScrnNo(),
						dataModel.getVeriStat(), dataModel.getOneOrMany(), dataModel.getInputAuth(), dataModel.getMaintAuth(),
						dataModel.getCancelAuth(), dataModel.getCommandKey(), dataModel.getUserId(), dataModel.getSessionId(),
						dataModel.getTransactionId(), dataModel.getTranDate(), dataModel.getTranTime(), dataModel.getUserAlerted(),
						dataModel.getScreenSetId(), dataModel.getLastScrnSetId(), dataModel.getOfflineRequest(),
						dataModel.getUserId4(), dataModel.getReasonRejection(), dataModel.getMessages()[0],
						dataModel.getMessages()[1], dataModel.getMessages()[2], dataModel.getMessages()[3],
						dataModel.getMessages()[4], dataModel.getMessages()[5], dataModel.getMessages()[6],
						dataModel.getMessages()[7], dataModel.getMessages()[8], dataModel.getMessages()[9],
						dataModel.getMessages()[10], dataModel.getMessages()[11], dataModel.getMessages()[12],
						dataModel.getMessages()[13], dataModel.getMessages()[14], dataModel.getMessages()[15],
						dataModel.getMessages()[16], dataModel.getMessages()[17], dataModel.getMessages()[18],
						dataModel.getMessages()[19], dataModel.getMessageBranches(), dataModel.getMessageAmounts() };

		return object;
	}

	/**
	 * This method is going execute a Sql query using the filter criteria.
	 * 
	 * @param whereClause
	 *            this is the <code>String</code> that represent the sql filter.
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecordBy(String whereClause)
	{
		return getRecordBy(whereClause, new WERecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new WERecordRowMapper());
	}

	/**
	 * This method is going to return a <code>WERecordDataModel</code> base on WEFID
	 * 
	 * @return a <code>WERecordDataModel</code> base on WEFID
	 */
	public WERecordDataModel getWERecord()
	{
		WERecordDataModel WERecordDataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new WERecordRowMapper());

		if (!results.isEmpty())
		{
			WERecordDataModel = (WERecordDataModel) results.get(0);
		}
		return WERecordDataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}

	/**
	 * This method will update all records base on alerted-sessions.
	 * 
	 * @param alertSessions
	 *            this is the alerted-sessions which are going to be used as filter.
	 */
	public void updateAllRecords(String alertSessions)
	{
		StringBuilder sqlStatement = new StringBuilder("UPDATE WEPF SET WEALRT='Y' WHERE ").append(alertSessions);
		executeStatement(sqlStatement.toString());
	}

	/**
	 * This method will delete all records base on deleteWEXSessions and deleteSessions.
	 * 
	 * @param deleteWEXSessions
	 *            this is the deleteWEXSessions which are going to be used as filter.
	 * @param deleteSessions
	 *            this is the delete-sessions which are going to be used as filter.
	 * @param deleteWEYSessions
	 *            this is the deleteWEXSessions which are going to be used as filter.
	 * 
	 */
	public void deleteSessions(String deleteWEXSessions, String deleteSessions, String deleteWEYSessions)
	{
		StringBuilder sqlStatement = new StringBuilder("DELETE FROM WEPF WHERE ").append(deleteSessions);
		executeStatement(sqlStatement.toString());

		sqlStatement = new StringBuilder("DELETE FROM WEXPF WHERE ").append(deleteWEXSessions);
		executeStatement(sqlStatement.toString());

		sqlStatement = new StringBuilder("DELETE FROM WEYPF WHERE ").append(deleteWEYSessions);
		executeStatement(sqlStatement.toString());
	}
}
