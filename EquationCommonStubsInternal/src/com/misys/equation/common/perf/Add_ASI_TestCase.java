package com.misys.equation.common.perf;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.test.EquationTestCase;

public abstract class Add_ASI_TestCase extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: Add_ASI_TestCase.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	abstract public void setupFields(EquationStandardTransaction transaction, int ref);
	abstract public EquationStandardTransaction getTransaction() throws Exception;

	public void test00100Add_NewRecord() throws Exception
	{
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_ADD);

		long start = System.nanoTime();
		for (int x = 1; x < 100; x++)
		{
			setupFields(transaction, 1);
			applyTransaction(transaction, true);
		}

		System.err.print("Duration : " + ((System.nanoTime() - start) / 1000000) + "ms");
	}
}
