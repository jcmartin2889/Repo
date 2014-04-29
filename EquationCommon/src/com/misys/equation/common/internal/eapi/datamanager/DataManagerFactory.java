package com.misys.equation.common.internal.eapi.datamanager;

import com.misys.equation.common.internal.eapi.core.EQException;

public class DataManagerFactory
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DataManagerFactory.java 9442 2010-10-12 09:42:28Z MACDONP1 $";

	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006 </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";

	static final String DATAMGR_ERROR = "COM001";

	// stores the logical name which will be queried from the environment
	static String DATAMANAGER_IMPL = "DataManagerImplementation";

	// singleton instance
	private static DataManagerFactory _dmFactory = null;

	// cache the Class instance
	private Class dmCls;

	/**
	 * DataManagerFactory default constructor.
	 */
	protected DataManagerFactory() throws EQException
	{
		String dmClassName = com.misys.equation.common.internal.eapi.core.EQEnvironment.getAppEnvironment().getProperty(
						DATAMANAGER_IMPL);
		try
		{
			dmCls = Class.forName(dmClassName);
		}
		catch (ClassNotFoundException clfex)
		{
			throw new DataManagerException(DATAMGR_ERROR);
		}
	}

	/**
	 * Returns the instance of the data manager implementation class specified in the environment file
	 * 
	 * @return an instance of the data manager
	 * 
	 * @throws DataManagerException
	 */
	public IDataManager getDataManager() throws DataManagerException
	{
		IDataManager dm = null;
		try
		{
			dm = (IDataManager) dmCls.newInstance();
		}
		catch (Exception ex)
		{
			throw new DataManagerException(DATAMGR_ERROR);
		}
		return dm;
	}

	/**
	 * Creates the one and only instance of DataManagerFactory
	 * 
	 * @return the data manager factory
	 */
	public static DataManagerFactory getDMFactory() throws EQException
	{
		if (_dmFactory == null)
		{
			// create the instance in a thread safe manner
			synchronized (DataManagerFactory.class)
			{
				if (_dmFactory == null)
				{
					_dmFactory = new DataManagerFactory();
				}
			}
		}
		return _dmFactory;
	}
}