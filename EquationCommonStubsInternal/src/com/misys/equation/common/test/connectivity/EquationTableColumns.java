package com.misys.equation.common.test.connectivity;

import java.util.Map;
import java.util.Map.Entry;

import junit.framework.TestCase;

import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.EquationControl;

public class EquationTableColumns extends TestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationTableColumns.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	/**
	 * This test is going to evaluate the whether table column metadata method is working.
	 */
	public void testTableColumns()
	{
		TestEnvironment environment = TestEnvironment.getTestEnvironment();

		Map<String, String> columns;

		columns = EquationControl.getTableColumnMetaData(environment.getStandardSession().getConnection(), environment
						.getEquationUnit().getKFILLibrary(), "OMPF");

		for (Entry<String, String> entry : columns.entrySet())
		{

			System.out.println(entry.getKey() + " - " + entry.getValue());

		}

	}
}
