package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IINORDRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.INORDRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.INORDRecordRowMapper;

/**
 * This the INORD-Record Dao implementation. <br>
 * This class is going to provide all back-end services for INORD-Record.
 * 
 * @author deroset
 */
public class INORDRecordDao extends AbsEquationDao implements IINORDRecordDao
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: INORDRecordDao.java 9725 2010-11-08 12:34:45Z MACDONP1 $";
	// defualt constructor
	public INORDRecordDao()
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
	public boolean checkIfThisINORDRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisINORDRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public INORDRecordDataModel getMyDataModel()
	{
		INORDRecordDataModel INORDRecordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof INORDRecordDataModel)
		{
			INORDRecordDataModel = (INORDRecordDataModel) getRecord();

		}
		return INORDRecordDataModel;
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
		StringBuilder whereCondition = new StringBuilder("INDDRNO='");
		whereCondition.append(getMyDataModel().getOrderNumber());
		whereCondition.append("'and INDDMCH='");
		whereCondition.append(getMyDataModel().getMachine());
		whereCondition.append("'and INDDSEQ='");
		whereCondition.append(getMyDataModel().getInstallSequence());
		whereCondition.append("'and INDDPRD='");
		whereCondition.append(getMyDataModel().getProductCode());
		whereCondition.append("'and INDCPRD='");
		whereCondition.append(getMyDataModel().getComponent());
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
		String fields = " INDDRNO=?, INDDPRD=?, INDDCTP=?, INDDMCH=?, INDDSEQ=?, INDDPARREL=?, INDCPRD=?, INDCDES=?, INDCMCH=?, INDCLVL=?, INDCOBJ=?, INDCUROBJ=?, INDCMB=?, INDDSEL=?, INDDENLIB=?, INDDENFIL=?, INDDENWRK=?, INDDENINP=? ";
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
		String fields = " INDDRNO, INDDPRD, INDDCTP, INDDMCH, INDDSEQ, INDDPARREL, INDCPRD, INDCDES, INDCMCH, INDCLVL, INDCOBJ, INDCUROBJ, INDCMB, INDDSEL, INDDENLIB, INDDENFIL, INDDENWRK, INDDENINP ";
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
		String fields = " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ";
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
		INORDRecordDataModel dataModel = getMyDataModel();
		Object[] object = new Object[] { dataModel.getOrderNumber(), dataModel.getProductCode(), dataModel.getComponentType1(),
						dataModel.getMachine(), dataModel.getInstallSequence(), dataModel.getParentReleaseFlag(),
						dataModel.getComponent(), dataModel.getComponentDescription(), dataModel.getComponentMachine(),
						dataModel.getComponentType2(), dataModel.getNumberOfObjects(), dataModel.getCurrentNumberOfObjects(),
						dataModel.getSizeMB(), dataModel.getSelectionField(), dataModel.getLibLibraryName(),
						dataModel.getFilLibraryName(), dataModel.getWrkLibraryName(), dataModel.getInpLibraryName(), };

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
		return getRecordBy(whereClause, new INORDRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new INORDRecordRowMapper());
	}

	/**
	 * This method is going to return a <code>INORDRecordDataModel</code> base on INORDFID
	 * 
	 * @return a <code>INORDRecordDataModel</code> base on INORDFID
	 */
	public INORDRecordDataModel getINORDRecord()
	{
		INORDRecordDataModel INORDRecordDataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new INORDRecordRowMapper());

		if (!results.isEmpty())
		{
			INORDRecordDataModel = (INORDRecordDataModel) results.get(0);
		}

		return INORDRecordDataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}
