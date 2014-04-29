package com.misys.equation.common.internal.dao;

import java.util.List;

import com.ibm.ws.exception.RuntimeError;
import com.misys.equation.common.dao.IDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.utilities.EquationLogger;

/**
 * @author deroset This class is a Dao abstraction which is going to have all abstract Dao features and behaviours
 */
public abstract class AbsEquationReadOnlyDao extends AbsEquationDao implements IDao
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbsEquationReadOnlyDao.java 14832 2012-11-05 19:03:33Z williae1 $";

	protected final EquationLogger LOG = new EquationLogger(this.getClass());

	public AbsEquationReadOnlyDao()
	{
		super();
	}

	/**
	 * Delete records from the file given a where clause
	 * 
	 * @param whereClause
	 *            - where clause in static SQL to execute
	 * 
	 * @return the number of rows affected
	 */
	public int deleteRecordsBy(String whereClause)
	{
		new RuntimeError("Delete not supportd");
		return 0;
	}

	/**
	 * This method is going to delete a new the record using the data-model values.
	 */
	public void deleteRecord()
	{
		new RuntimeError("Delete not supportd");
	}

	/**
	 * This method is going to add a new the records using the data-model values. <br>
	 * Bear in mind since this method is invoked the current <code>Dao</code> instance will be related to the data-model passed as
	 * parameter. <br>
	 * please use <code>setRecord(AbsRecord record)</code>to set other data-model.
	 * 
	 * <p>
	 * note: this method is synchronized. since it uses internally <code>setRecord(AbsRecord record)</code> for each
	 * <code>record</code> to retrieve the parameter values, it is necessary to block any invocation of
	 * <code>setRecord(AbsRecord record)</code> from other threads
	 * 
	 * </p>
	 * 
	 * @param records
	 *            this the list of records to be inserted.
	 */
	public synchronized void insertRecords(final List<? extends AbsRecord> records)
	{
		new RuntimeError("Insert not supportd");
	}

	/**
	 * This method is going to add a new the record using the data-model values.
	 */
	public void insertRecord()
	{
		new RuntimeError("Insert not supportd");
	}

	/**
	 * This method is going to update the record using the data-model values.
	 */
	public void updateRecord()
	{
		new RuntimeError("Update not supportd");
	}

	/**
	 * This method is going to commit the current transaction. The preset connection will be used. <br>
	 * <code>currentConnection.commit()</code> will be executed.
	 */
	public void commit()
	{
		new RuntimeError("Commit not supportd");
	}

}