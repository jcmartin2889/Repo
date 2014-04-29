package com.misys.equation.common.test.connectivity;

import java.util.Map;
import java.util.Map.Entry;

import junit.framework.TestCase;

import com.misys.equation.common.access.EquationDB2Table;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.EquationControl;

public class EquationUniqueLogicals extends TestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationUniqueLogicals.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	/**
	 * This test is going to test get tables and unique logicals.
	 */
	public void testUniqueLogicalsList()
	{
		TestEnvironment environment = TestEnvironment.getTestEnvironment();

		Map<String, EquationDB2Table> tables;

		tables = EquationControl.getTables(environment.getStandardSession().getConnection(), environment.getEquationUnit()
						.getKFILLibrary());

		for (Entry<String, EquationDB2Table> entry : tables.entrySet())
		{
			if (entry.getValue().isUnique())
			{
				System.out.println(entry.getKey() + " - " + entry.getValue());
			}
		}

	}
	/**
	 * This test is going to test get unique keys.
	 */
	public void testUniqueKeys()
	{
		String keys = "";
		try
		{
			keys = TestEnvironment.getTestEnvironment().getEquationUnit().getUniqueKeys("KFIL", "OM10LF",false);
		}
		catch (EQException e)
		{
			e.printStackTrace();
		}
		System.out.println(keys);
	}
}
