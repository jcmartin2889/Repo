package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.ibm.as400.access.BidiStringType;
import com.misys.equation.common.dao.ICHRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.CHRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.CHRecordRowMapper;
import com.misys.equation.common.utilities.Toolbox;

/**
 * This the CH-Record Dao implementation. <br>
 * This class is going to provide all back-end services for CH-Record.
 * 
 * @author deroset
 */
public class CHRecordDao extends AbsEquationDao implements ICHRecordDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CHRecordDao.java 8910 2010-08-26 15:25:20Z MACDONP1 $";
	// defualt constructor
	public CHRecordDao()
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
	public boolean checkIfThisCHRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisCHRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public CHRecordDataModel getMyDataModel()
	{
		CHRecordDataModel CHRecordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof CHRecordDataModel)
		{
			CHRecordDataModel = (CHRecordDataModel) getRecord();

		}
		return CHRecordDataModel;
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
		StringBuilder whereCondition = new StringBuilder("CHENM='");
		whereCondition.append(getMyDataModel().getEnhancementMnemonic());
		whereCondition.append("' and CHLVL='");
		whereCondition.append(getMyDataModel().getEnhancementLevel());
		whereCondition.append("' and CHUPG='");
		whereCondition.append(getMyDataModel().getEnhancementUpgrade());
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
		String fields = " CHENM=?, CHEND=?, CHENL=?, CHENB=?, CHEEM=?, CHEID=?, CHEIT=?, CHLVL=?, CHUPG=? ";
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
		String fields = " CHENM, CHEND, CHENL, CHENB, CHEEM, CHEID, CHEIT, CHLVL, CHUPG ";
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
		String fields = " ?, ?, ?, ?, ?, ?, ?, ?, ? ";
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
		int ccsid = 37;

		CHRecordDataModel dataModel = getMyDataModel();
		Object[] object = new Object[] {

						Toolbox.convertTextIntoAS400BytesCCSID(dataModel.getEnhancementMnemonic(), 10, ccsid, BidiStringType.ST9),
						dataModel.getEnhancementDescription(),
						Toolbox.convertTextIntoAS400BytesCCSID(dataModel.getEnhancementLibraryName(), 10, ccsid, BidiStringType.ST9),
						Toolbox.convertTextIntoAS400BytesCCSID(dataModel.getEnhancementInstalledToBase(), 1, ccsid,
										BidiStringType.ST9),
						Toolbox.convertTextIntoAS400BytesCCSID(dataModel.getEncryptedEnhancementMnemonic(), 10, ccsid,
										BidiStringType.ST9), dataModel.getInstallationDate(), dataModel.getInstallationTime(),
						Toolbox.convertTextIntoAS400BytesCCSID(dataModel.getEnhancementLevel(), 1, ccsid, BidiStringType.ST9),
						Toolbox.convertTextIntoAS400BytesCCSID(dataModel.getEnhancementUpgrade(), 1, ccsid, BidiStringType.ST9), };
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
		return getRecordBy(whereClause, new CHRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new CHRecordRowMapper());
	}

	/**
	 * This method is going to return a <code>CHRecordDataModel</code> base on CHFID
	 * 
	 * @return a <code>CHRecordDataModel</code> base on CHFID
	 */
	public CHRecordDataModel getCHRecord()
	{
		CHRecordDataModel CHRecordDataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new CHRecordRowMapper());

		if (!results.isEmpty())
		{

			CHRecordDataModel = (CHRecordDataModel) results.get(0);
		}

		return CHRecordDataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}
