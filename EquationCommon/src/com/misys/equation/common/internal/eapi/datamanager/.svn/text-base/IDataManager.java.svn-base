package com.misys.equation.common.internal.eapi.datamanager;

public interface IDataManager
{
	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006 </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";

	/**
	 * Gets the database connection based on the logical name passed. The userid and password should have been specified while
	 * defining the datasource.
	 */
	java.sql.Connection getDatabaseConnection(String dsnName) throws DataManagerException;

	/**
	 * Gets the database connection based on the url and connects to the database using the userid and password passed to it.
	 * <P>
	 * The url is of the form jdbc:as400://(host) or jdbc:db2:(dbalias)
	 */
	java.sql.Connection getDatabaseConnection(String strURL, String uid, char[] pwd) throws DataManagerException;

	/**
	 * Gets the database connection based on the application config file
	 */
	java.sql.Connection getDatabaseConnection() throws DataManagerException;

	/**
	 * Gets the database connection based on the application config file, except credentials which must be passed
	 */
	java.sql.Connection getDatabaseConnection(String strUid, char[] strPwd) throws DataManagerException;
	java.sql.Connection getDatabaseConnection(String strDriver, String strURL, String strUid, char[] strPwd)
					throws com.misys.equation.common.internal.eapi.datamanager.DataManagerException;
}
