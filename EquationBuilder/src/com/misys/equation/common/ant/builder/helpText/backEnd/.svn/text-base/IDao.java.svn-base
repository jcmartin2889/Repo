package com.misys.equation.common.ant.builder.helpText.backEnd;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.List;

import javax.sql.DataSource;

import com.misys.equation.common.ant.builder.helpText.models.AbsRecord;

/**
 * This is an interface which is going to expose all common Dao services. It is going to behave as a bridge between the Dao and the
 * client code.
 * 
 * @author deroset
 * 
 */
public interface IDao
{
	/**
	 * This method is going to initialise the database connection and ensure that it is unique.
	 * 
	 * @param this is the <code>ConnectionWrapper</code> which will provide the connection attached to this session.
	 */
	public void initialiseDao(Connection connection);

	/**
	 * This method is used to set the current connection-Pool
	 * 
	 * @param dataSource
	 *            This is the current connection pool which is going to provide the data base connection.
	 */
	public void setDataSource(DataSource dataSource);

	/**
	 * This method is going to commit the current transaction. The preset connection will be used. <br>
	 * <code>currentConnection.commit()</code> will be executed.
	 */
	public void commit();

	/**
	 * This method is going to close the current connection. The preset connection will be used. <br>
	 * <code>currentConnection.destroy()</code> will be executed.
	 */
	public void destroy();

	public boolean isAutocommitable();

	public void setAutocommitable(boolean autocommitable);

	/**
	 * This method will be used to set the data-model which will be used by the DAO.
	 * 
	 * @param record
	 *            This is the data model to be used.
	 */
	public void setRecord(AbsRecord record);

	/**
	 * This method is going to update the record using the data-model values.
	 */
	public void updateRecord();

	/**
	 * This method is going to add a new the record using the data-model values.
	 */
	public void insertRecord();

	/**
	 * This method is going to delete a new the record using the data-model values.
	 */
	public void deleteRecord();

	/**
	 * This method will check if the current record has already been inserted in the database.<br>
	 * if the record has already been inserted in the data base it will be updated.In other case it will be inserted.
	 */
	public void insertOrUpdateRecord();

	/**
	 * This method is going to add a new the record using the data-model values. <br>
	 * Bear in mind since this method is invoked the current <code>Dao</code> instance will be related to the data-model passed as
	 * parameter. <br>
	 * please use <code>setRecord(AbsRecord record)</code>to set other data-model.
	 * 
	 * @param record
	 *            this the record to be inserted.
	 */
	public void insertRecord(AbsRecord record);

	/**
	 * This method is going to update the record using the data-model values. <br>
	 * Bear in mind since this method is invoked the current <code>Dao</code> instance will be related to the data-model passed as
	 * parameter. <br>
	 * please use <code>setRecord(AbsRecord record)</code>to set other data-model.
	 * 
	 * @param record
	 *            this the record to be updated.
	 */
	public void updateRecord(AbsRecord record);

	/**
	 * This method will check if the current record has already been inserted in the database.<br>
	 * if the record has already been inserted in the data base it will be updated.In other case it will be inserted. <br>
	 * Bear in mind since this method is invoked the current <code>Dao</code> instance will be related to the data-model passed as
	 * parameter. <br>
	 * please use <code>setRecord(AbsRecord record)</code>to set other data-model.
	 * 
	 * @param record
	 *            this the record to be evaluated.
	 */
	public void insertOrUpdateRecord(AbsRecord record);

	/**
	 * This method is going to update the record using the data-model values. <br>
	 * Bear in mind since this method is invoked the current <code>Dao</code> instance will be related to the data-model passed as
	 * parameter. <br>
	 * please use <code>setRecord(AbsRecord record)</code>to set other data-model.
	 * 
	 * @param record
	 *            this the record to be deleted.
	 */
	public void deleteRecord(AbsRecord record);

	/**
	 * This method is going execute a Sql query using the filter criteria.
	 * 
	 * @param whereClause
	 *            this is the <code>String</code> that represent the sql filter.
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecordBy(String whereClause);

	/**
	 * This method is going execute a Sql query using the filter criteria.<br>
	 * The result will be a <code>HashTable<String,AbsRecord> </code> which use the key records as hashtable's key. <br>
	 * 
	 * <code>HashTable<String,AbsRecord> createHashtableRecordModel(List<AbsRecord> records)</code> has to be implemented. <br>
	 * 
	 * <code>HashTable<String,AbsRecord> createHashtableRecordModel(List<AbsRecord> records)</code> has to be implemented. <br>
	 * 
	 * @param whereClause
	 *            this is the <code>String</code> that represent the sql filter.
	 * 
	 * @return a <code>HashTable<String,AbsRecord></code> which contains a records
	 */
	public Hashtable<String, AbsRecord> getHashtableRecordBy(String whereClause);

	/**
	 * This method is going execute a Sql query using the filter criteria.<br>
	 * The result will be a <code>HashTable<String,AbsRecord> </code> which use the key records as hashtable's key. <br>
	 * 
	 * <code>HashTable<String,AbsRecord> createHashtableRecordModel(List<AbsRecord> records)</code> has to be implemented. <br>
	 * 
	 * <code>HashTable<String,AbsRecord> createHashtableRecordModel(List<AbsRecord> records)</code> has to be implemented. <br>
	 * 
	 * @return a <code>HashTable<String,AbsRecord></code> which contains a records
	 */
	public Hashtable<String, AbsRecord> getHashtableRecordBy();

	/**
	 * This method is going to end the journaling.
	 * 
	 * @param unitName
	 *            this is the unit name.
	 * @return false if the error is <code>CPF7032</code>, it means that the journal file has already been closed.
	 */
	public boolean endJournal(String unitName);

	/**
	 * This method is going to end the journaling.
	 * 
	 * @param unitName
	 *            this is the unit name.
	 * @return false if the error is <code>CPF7032</code>, it means that the journal file has already been opened.
	 */
	public boolean startJournal(String unitName);

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords();

}
