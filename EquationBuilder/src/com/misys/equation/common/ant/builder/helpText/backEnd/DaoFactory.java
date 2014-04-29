package com.misys.equation.common.ant.builder.helpText.backEnd;

import com.misys.equation.common.ant.builder.core.EquationStandardSession;
import com.misys.equation.common.ant.builder.helpText.models.AbsRecord;

/**
 * This class is used as a Factory. It will be used to create Daos. <br>
 * <code>DaoFactory</code> will hide the bean factory implementation and I will works as bridge between the application code and the
 * daos.
 * 
 * @author deroset
 * 
 */
public class DaoFactory
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DaoFactory.java 4741 2009-09-16 16:33:23Z esther.williams $";

	/**
	 * this is the default constructor.
	 */
	public DaoFactory()
	{

	}
	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public GAERecordDao getGAEDao(EquationStandardSession session, AbsRecord record)
	{
		GAERecordDao dao = new GAERecordDao();
		dao.initialiseDao(session.getConnection());
		dao.setRecord(record);
		return dao;
	}
}
