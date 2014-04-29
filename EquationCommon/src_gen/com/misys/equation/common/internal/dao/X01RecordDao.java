package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IX01RecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.X01RecordDataModel;
import com.misys.equation.common.internal.dao.mappers.X01RecordRowMapper;

/**
 * This the X01 -Record Dao implementation. <br>
 * This class is going to provide all back-end services for X01 -Record.
 * 
 * @author deroset
 */
public class X01RecordDao extends AbsEquationDao implements IX01RecordDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1284999546135l;

	public X01RecordDao()
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
	public boolean checkIfThisX01RecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisX01RecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public X01RecordDataModel getMyDataModel()
	{
		X01RecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof X01RecordDataModel)
		{
			dataModel = (X01RecordDataModel) getRecord();

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
		whereCondition.append("X0DTE ='");
		whereCondition.append(getMyDataModel().getTransferDate());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("X0AB1 ='");
		whereCondition.append(getMyDataModel().getDebitAccountBranch());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("X0AN1 ='");
		whereCondition.append(getMyDataModel().getDebitAccountBasic());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("X0AS1 ='");
		whereCondition.append(getMyDataModel().getDebitAccountSuffix());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("X0AB2 ='");
		whereCondition.append(getMyDataModel().getCreditAccountBranch());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("X0AN2 ='");
		whereCondition.append(getMyDataModel().getCreditAccountBasic());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("X0AS2 ='");
		whereCondition.append(getMyDataModel().getCreditAccountSuffix());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("X0FON ='");
		whereCondition.append(getMyDataModel().getFontisTransfer());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("X0TREF ='");
		whereCondition.append(getMyDataModel().getTransferReference());
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
		String fields = "X0REF= ?, X0DTE= ?, X0AB1= ?, X0AN1= ?, X0AS1= ?, X0AB2= ?, X0AN2= ?, X0AS2= ?, X0POD1= ?, X0BRN1= ?, X0PBR1= ?, X0PSQ1= ?, X0POD2= ?, X0BRN2= ?, X0PBR2= ?, X0PSQ2= ?, X0POD3= ?, X0BRN3= ?, X0PBR3= ?, X0PSQ3= ?, X0POD4= ?, X0BRN4= ?, X0PBR4= ?, X0PSQ4= ?, X0FON= ?, X0SOT= ?, X0TREF= ?";
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
		String fields = "X0REF, X0DTE, X0AB1, X0AN1, X0AS1, X0AB2, X0AN2, X0AS2, X0POD1, X0BRN1, X0PBR1, X0PSQ1, X0POD2, X0BRN2, X0PBR2, X0PSQ2, X0POD3, X0BRN3, X0PBR3, X0PSQ3, X0POD4, X0BRN4, X0PBR4, X0PSQ4, X0FON, X0SOT, X0TREF ";
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
		String fields = "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
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
		X01RecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getRedundant(), dataModel.getTransferDate(), dataModel.getDebitAccountBranch(),
						dataModel.getDebitAccountBasic(), dataModel.getDebitAccountSuffix(), dataModel.getCreditAccountBranch(),
						dataModel.getCreditAccountBasic(), dataModel.getCreditAccountSuffix(), dataModel.getPosting1Date(),
						dataModel.getPosting1Branch(), dataModel.getPosting1BatchId(), dataModel.getPosting1Sequence(),
						dataModel.getPosting2Date(), dataModel.getPosting2Branch(), dataModel.getPosting2BatchId(),
						dataModel.getPosting2Sequence(), dataModel.getPosting3Date(), dataModel.getPosting3Branch(),
						dataModel.getPosting3BatchId(), dataModel.getPosting3Sequence(), dataModel.getPosting4Date(),
						dataModel.getPosting4Branch(), dataModel.getPosting4BatchId(), dataModel.getPosting4Sequence(),
						dataModel.getFontisTransfer(), dataModel.getStandingOrderTransfer(), dataModel.getTransferReference() };
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
		return getRecordBy(whereClause, new X01RecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> that contains the records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new X01RecordRowMapper());
	}

	/**
	 * This method is going to return a <code> X01RecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> X01RecordDataModel </code> base on $tablePrefix-ID
	 */
	public X01RecordDataModel getX01Record()
	{
		X01RecordDataModel dataModel = null;
		List results = getRecordBy(getWhereConditionBaseOnIdRecord(), new X01RecordRowMapper());

		if (!results.isEmpty())
		{

			dataModel = (X01RecordDataModel) results.get(0);
		}

		return dataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}