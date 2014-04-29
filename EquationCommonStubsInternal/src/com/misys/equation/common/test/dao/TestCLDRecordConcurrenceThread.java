package com.misys.equation.common.test.dao;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.ICLDRecordDao;
import com.misys.equation.common.dao.IDao;
import com.misys.equation.common.utilities.EquationLogger;

public class TestCLDRecordConcurrenceThread implements Runnable
{
	
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TestCLDRecordConcurrenceThread.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	private IDao dao;
	private EquationStandardSession session;
	protected final EquationLogger LOG = new EquationLogger(this.getClass());
	private String customValue;
	
	
	public void run()
	{
		updateRecord();
	}
	
	public void updateRecord()
	{
		
		
		try
		{
			 
			((ICLDRecordDao) dao).setAutocommitable(false);
			session.startEquationTransaction();
			((ICLDRecordDao) dao).getCLDRecord().setCustName(customValue);
			((ICLDRecordDao) dao).updateRecord();
			session.commitTransaction();
			session.endEquationTransaction();
		}
		catch (Exception exception)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("There was a problem when the testRecordDaoServices() was executed ", exception);
			}
		}


		
		
	}

	
	
	
	public EquationStandardSession getSession()
	{
		return session;
	}


	public void setSession(EquationStandardSession session)
	{
		this.session = session;
	}

	public IDao getDao()
	{
		return dao;
	}

	public void setDao(IDao dao)
	{
		this.dao = dao;
	}

	public String getCustomValue()
	{
		return customValue;
	}

	public void setCustomValue(String customValue)
	{
		this.customValue = customValue;
	}




}
