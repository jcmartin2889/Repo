package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseFully;

/**
 * Equation test cases for Cashier System Branch Details
 */
public class CDT extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CDT.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "I28FRR";
	String optionId = "CDT";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		retrieveBeforeCancel = false;
	}

	// ------------------------------------------------------------------------ Helper methods
	/**
	 * Return a transaction
	 * 
	 * @return a transaction
	 * 
	 * @throws Exception
	 */
	@Override
	public EquationStandardTransaction getTransaction() throws Exception
	{
		EquationStandardTransaction transaction = getEquationStandardTransaction(programName + optionId);
		transaction.setWorkStationId(WORKSTATIONID);
		return transaction;
	}

	// ------------------------------------------------------------------------ Field setups

	/**
	 * Setup a non-existing key fields only
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBBN", "9132"); // Branch number
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZOSTS", "I"); // Operational status; L=Live, I=Implementing (1A)
		transaction.setFieldValue("GZMNOV", "N"); // Business day override - Monday (Y,N,H) (1A)
		transaction.setFieldValue("GZTUOV", "N"); // Business day override - Tuesday (Y,N,H) (1A)
		transaction.setFieldValue("GZWEOV", "N"); // Business day override - Wednesday (Y,N,H) (1A)
		transaction.setFieldValue("GZTHOV", "N"); // Business day override - Thursday (Y,N,H) (1A)
		transaction.setFieldValue("GZFROV", "N"); // Business day override - Friday (Y,N,H) (1A)
		transaction.setFieldValue("GZSAOV", "N"); // Business day override - Saturday (Y,N,H) (1A)
		transaction.setFieldValue("GZSUOV", "N"); // Business day override - Sunday (Y,N,H)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZOSTS", "I"); // Operational status; L=Live, I=Implementing
		transaction.setFieldValue("GZMNOV", "Y"); // Business day override - Monday (Y,N,H) (1A)
		transaction.setFieldValue("GZTUOV", "N"); // Business day override - Tuesday (Y,N,H) (1A)
		transaction.setFieldValue("GZWEOV", "N"); // Business day override - Wednesday (Y,N,H) (1A)
		transaction.setFieldValue("GZTHOV", "N"); // Business day override - Thursday (Y,N,H) (1A)
		transaction.setFieldValue("GZFROV", "N"); // Business day override - Friday (Y,N,H) (1A)
		transaction.setFieldValue("GZSAOV", "N"); // Business day override - Saturday (Y,N,H) (1A)
		transaction.setFieldValue("GZSUOV", "N"); // Business day override - Sunday (Y,N,H)
	}

}
