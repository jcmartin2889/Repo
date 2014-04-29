package com.misys.equation.common.access;

import java.util.HashMap;

public interface IEquationSQLPagingListCallback
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: IEquationSQLPagingListCallback.java 17303 2013-09-19 10:47:03Z williae1 $";
	/**
	 * Execute method to be called when processing an Equation SQL Paging List row
	 * 
	 * @param rowData
	 *            - row data
	 * @return return true if the row is to be included in the results
	 * 
	 */
	public boolean validateRow(HashMap<String, String> rowData);

}
