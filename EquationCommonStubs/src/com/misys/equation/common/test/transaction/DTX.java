package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseFully;

/**
 * Equation test cases for Add/Maintain FX Deal Type Details
 */
public class DTX extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DTX.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "L04FRR";
	String optionId = "DTX";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		retrieveBeforeCancel = true;
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
		transaction.setFieldValue("GZDLP", "BIA"); // Deal type
		transaction.setFieldValue("GZAPP", "FX"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR,
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDPD", "Carl's Deal Type"); // Deal type description
		transaction.setFieldValue("GZYSDA", "Y"); // Sundry deal allowed?
		transaction.setFieldValue("GZACTC", "V0"); // Purchase account type
		transaction.setFieldValue("GZACTD", "V0"); // Sale account type
		transaction.setFieldValue("GZTCDA", "510"); // We pay transaction code
		transaction.setFieldValue("GZTCDB", "010"); // We receive transaction code
		transaction.setFieldValue("GZTCDC", "FC2"); // Cancellation transaction code - credit
		transaction.setFieldValue("GZTCDD", "FD2"); // Cancellation transaction code - debit
		transaction.setFieldValue("GZTCDJ", "FD1"); // Exchange purchase inception (receive)
		transaction.setFieldValue("GZTCDK", "FC1"); // Exchange sale inception (pay)
		transaction.setFieldValue("GZYSD", "Y"); // Spot deals allowed?
		transaction.setFieldValue("GZYFD", "Y"); // Forward deals allowed?
		transaction.setFieldValue("GZSCCA", "SP102"); // Spot contra account
		transaction.setFieldValue("GZFCCA", "SP102"); // Forward contra account
		transaction.setFieldValue("GZYACR", "N"); // Accrual P&L postings?
		transaction.setFieldValue("GZRPA", "SP105"); // Revaluation profit
		transaction.setFieldValue("GZRLA", "SP105"); // Revaluation loss
		transaction.setFieldValue("GZSODM", "N"); // Mature at start of day?
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDPD", "Carl's Deal Type"); // Deal type description
		transaction.setFieldValue("GZYSDA", "N"); // Sundry deal allowed?
		transaction.setFieldValue("GZACTC", "V0"); // Purchase account type
		transaction.setFieldValue("GZACTD", "V0"); // Sale account type
		transaction.setFieldValue("GZTCDA", "510"); // We pay transaction code
		transaction.setFieldValue("GZTCDB", "010"); // We receive transaction code
		transaction.setFieldValue("GZTCDC", "FC2"); // Cancellation transaction code - credit
		transaction.setFieldValue("GZTCDD", "FD2"); // Cancellation transaction code - debit
		transaction.setFieldValue("GZTCDJ", "FD1"); // Exchange purchase inception (receive)
		transaction.setFieldValue("GZTCDK", "FC1"); // Exchange sale inception (pay)
		transaction.setFieldValue("GZYSD", "Y"); // Spot deals allowed?
		transaction.setFieldValue("GZYFD", "Y"); // Forward deals allowed?
		transaction.setFieldValue("GZSCCA", "SP102"); // Spot contra account
		transaction.setFieldValue("GZFCCA", "SP102"); // Forward contra account
		transaction.setFieldValue("GZYACR", "N"); // Accrual P&L postings?
		transaction.setFieldValue("GZRPA", "SP105"); // Revaluation profit
		transaction.setFieldValue("GZRLA", "SP105"); // Revaluation loss
		transaction.setFieldValue("GZSODM", "N"); // Mature at start of day?
	}

}
