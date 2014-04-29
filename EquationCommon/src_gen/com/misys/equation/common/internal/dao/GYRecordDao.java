package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IGYRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GYRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.GYRecordRowMapper;

/**
 * This the GY -Record Dao implementation. <br>
 * This class is going to provide all back-end services for GY -Record.
 * 
 * @author deroset
 */
public class GYRecordDao extends AbsEquationDao implements IGYRecordDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1278481551853l;

	public GYRecordDao()
	{
		super();
	}

	/**
	 * This method will check if the current record is already in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @param sqlWhereStatement
	 *            - the WHERE clause of the SQL statement
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisGYRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisGYRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public GYRecordDataModel getMyDataModel()
	{
		GYRecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof GYRecordDataModel)
		{
			dataModel = (GYRecordDataModel) getRecord();

		}
		return dataModel;
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
		StringBuilder whereCondition = new StringBuilder(1024);
		whereCondition.append("GYDIM ='");
		whereCondition.append(getMyDataModel().getDayInMonth());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GYTIM ='");
		whereCondition.append(getMyDataModel().getTimeHhmmss());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GYSEQ ='");
		whereCondition.append(getMyDataModel().getSequenceNumber());
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
		String fields = "GYWSID= ?, GYUSID= ?, GYDIM= ?, GYTIM= ?, GYSEQ= ?, GYFRO= ?, GYJTT= ?, GYBRNM= ?, GYPRN= ?, GYREC= ?, GYOID= ?, GYAPP= ?, GYWHO= ?, GYSHN= ?, GYJREF= ?, GYDLM= ?, GYMES1= ?, GYMES2= ?, GYMES3= ?, GYMES4= ?, GYMES5= ?, GYMES6= ?, GYMES7= ?, GYMES8= ?, GYKSC= ?, GYECBU= ?, GYSMN= ?, GYREF= ?, GYDPI= ?, GYETS= ?, GYETK= ?, GYETN= ?, GYLJOB= ?, GYLTIM= ?, GYLSET= ?, GYTFRS= ?, GYMCL= ?, GYAEXT= ?, GYAREC= ?, GYCDT= ?, GYCLTM= ?, GYCOMT= ?, GYJOB= ?, GYCSEQ= ?, GYAPID= ?, GYTCP= ?, GYIREF= ?, GYAUID= ?, GYSUID= ?";
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
		String fields = "GYWSID, GYUSID, GYDIM, GYTIM, GYSEQ, GYFRO, GYJTT, GYBRNM, GYPRN, GYREC, GYOID, GYAPP, GYWHO, GYSHN, GYJREF, GYDLM, GYMES1, GYMES2, GYMES3, GYMES4, GYMES5, GYMES6, GYMES7, GYMES8, GYKSC, GYECBU, GYSMN, GYREF, GYDPI, GYETS, GYETK, GYETN, GYLJOB, GYLTIM, GYLSET, GYTFRS, GYMCL, GYAEXT, GYAREC, GYCDT, GYCLTM, GYCOMT, GYJOB, GYCSEQ, GYAPID, GYTCP, GYIREF, GYAUID, GYSUID ";
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
		String fields = "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
		return fields;
	}

	/**
	 * This method create an array that contains the fields values and Its types. <br>
	 * It will be used by JDBC <code>PreparedStatement</code>
	 * 
	 * @return An array that contains the field values and their types.
	 */
	@Override
	public Object[] getParameterizedFieldsValues()
	{
		GYRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getWorkstationId(), dataModel.getUserId(), dataModel.getDayInMonth(),
						dataModel.getTimeHhmmss(), dataModel.getSequenceNumber(), dataModel.getProgramRoot(),
						dataModel.getJournalTransType(), dataModel.getBranchMnem(), dataModel.getPrintedFlag(),
						dataModel.getRecoveryStatus(), dataModel.getUserDefinedOptionBeingJournalled(), dataModel.getApplication(),
						dataModel.getAccount(), dataModel.getAccountORCustomerIdentity(), dataModel.getJournalReference(),
						dataModel.getDateLastMaintained(), dataModel.getWarningMessage1(), dataModel.getWarningMessage2(),
						dataModel.getWarningMessage3(), dataModel.getWarningMessage4(), dataModel.getWarningMessage5(),
						dataModel.getWarningMessage6(), dataModel.getErrorMessageFromRecovery(),
						dataModel.getErrorMessageFromBackgroundTask(), dataModel.getCoreSystemStatus(),
						dataModel.getSupervisorUserId(), dataModel.getSecurityMnemonic(), dataModel.getPortfolioReference(),
						dataModel.getDepotId(), dataModel.getTransactionStatus(), dataModel.getEnigmaIdentifier(),
						dataModel.getEnigmaSubTransaction(), dataModel.getLinkJobNumber(), dataModel.getLinkTime(),
						dataModel.getLinkSet(), dataModel.getTransferStatus(), dataModel.getMaintainedOrcancelledLater(),
						dataModel.getApplyDuringExternalInput(), dataModel.getApplyDuringRecovery(), dataModel.getCreateDate(),
						dataModel.getCcLinkTime(), dataModel.getCommitIssuedEot(), dataModel.getJobNumber(),
						dataModel.getCcLinkSeqNo(), dataModel.getApplicationId(), dataModel.getTcpipAddress(),
						dataModel.getInputReference(), dataModel.getInputtingUserId(), dataModel.getAuthorisingUserId() };
		return object;
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
		return getRecordBy(whereClause, new GYRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> that contains the records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new GYRecordRowMapper());
	}

	/**
	 * This method is going to return a <code> GYRecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> GYRecordDataModel </code> base on $tablePrefix-ID
	 */
	public GYRecordDataModel getGYRecord()
	{
		GYRecordDataModel dataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new GYRecordRowMapper());

		if (!results.isEmpty())
		{
			dataModel = (GYRecordDataModel) results.get(0);
		}

		return dataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}