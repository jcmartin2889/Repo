package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseFullyAdd;

/**
 * Equation test cases for Add/Maintain Deal Type Details
 */
public class AddTest_DTM extends EquationTestCaseFullyAdd
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AddTest_DTM.java 5103 2009-10-08 14:57:09Z macdonp1 $";
	String programName = "G16FRR";
	String optionId = "DTM";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
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
		transaction.setFieldValue("GZDLP", "BO1"); // Deal type
		transaction.setFieldValue("GZAPP", "MM"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR,
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDPD", "Bonus Deposit"); // Deal type description
		transaction.setFieldValue("GZTDT", "D"); // Term deal type; L=Loan, D=Deposit
		transaction.setFieldValue("GZACTA", "R2"); // Contingent account type
		transaction.setFieldValue("GZACTB", "X2"); // Term deal account type

		transaction.setFieldValue("GZTCDA", "610"); // We pay transaction code
		transaction.setFieldValue("GZTCDB", "110"); // We receive transaction code
		transaction.setFieldValue("GZTCDC", "595"); // Cancellation transaction code - credit
		transaction.setFieldValue("GZTCDD", "095"); // Cancellation transaction code - debit
		transaction.setFieldValue("GZTCDE", "860"); // Term deal transaction code
		transaction.setFieldValue("GZTCDF", "PD1"); // Term principal increase - deal debit
		transaction.setFieldValue("GZTCDG", "PC1"); // Term principal decrease - deal credit
		transaction.setFieldValue("GZTCDH", "PD1"); // Principal decrease funding tran code - debit
		transaction.setFieldValue("GZTCDI", "PC1"); // Principal increase funding tran code - credit
		transaction.setFieldValue("GZMPR", "12"); // Number of months interest is forecast for (3P,0)
		transaction.setFieldValue("GZIIG6", "N"); // Extra day's interest?
		transaction.setFieldValue("GZYDDB", "N"); // Force default interest day's basis on deal input?
		transaction.setFieldValue("GZAIF5", "Y"); // Liable for tax?
		transaction.setFieldValue("GZYPSF", "N"); // Exclude from positions?
		transaction.setFieldValue("GZCASH", "N"); // Cashier system deal type?
		transaction.setFieldValue("GZRETP", "01"); // Retention period

		transaction.setFieldValue("GZINTT", "2"); // Interest rate type for this deal type
		transaction.setFieldValue("GZWIIP", "3"); // When is interest posted for this deal type
		transaction.setFieldValue("GZARTC", "2"); // Application of rate change for this deal type
		transaction.setFieldValue("GZYROL", "A"); // Are rollovers allowed for deals of this type?
		transaction.setFieldValue("GZYADJ", "Y"); // Are principal adjustments allowed?
		transaction.setFieldValue("GZCOMT", "N"); // Commitment required?
		transaction.setFieldValue("GZYSDA", "Y"); // Sundry deal allowed?
		transaction.setFieldValue("GZYCHQ", "N"); // Discounted cheques only ?
		transaction.setFieldValue("GZCRCL", "N"); // Constant repayment loan?
		transaction.setFieldValue("GZYTFO", "Y"); // Transfer to front office?
		transaction.setFieldValue("GZEXMM", "Y"); // Exclude from MM Limits?
		transaction.setFieldValue("GZSODM", "N"); // Mature at start of day?
		transaction.setFieldValue("GZCFI", "N"); // SWIFT term inceptionconfirmation
		transaction.setFieldValue("GZCFM", "N"); // SWIFT term maturity confirmation
		transaction.setFieldValue("GZCLR", "N"); // Collateral required
		transaction.setFieldValue("GZFXIL", "N"); // FX index loan
		transaction.setFieldValue("GZAIRA", "Y"); // Assign internal rates in advance

		transaction.setFieldValue("GZDAY1", "N"); // Interest value date 1 business day forward?
		transaction.setFieldValue("GZIOD", "Y"); // Irregular first rollover date allowed?
		transaction.setFieldValue("GZIOP", "Y"); // Irregular rollover period allowed?
		transaction.setFieldValue("GZMIN", "N"); // Minimum rate allowed?
		transaction.setFieldValue("GZRTN", "-9999.9999999"); // Default minimum rate
		transaction.setFieldValue("GZMAX", "N"); // Maximum rate allowed?
		transaction.setFieldValue("GZRTX", "-9999.9999999"); // Default maximum rate
		transaction.setFieldValue("GZSPA", "N"); // SWIFT pay advice?
		transaction.setFieldValue("GZSRA", "N"); // SWIFT receive advice?
		transaction.setFieldValue("GZNDA", "N"); // Narrative on deal account postings ?
		transaction.setFieldValue("GZCLBA", "N"); // Constant loans use basis A

		transaction.setFieldValue("GZBTM", "N"); // Bank transfer multiples
		transaction.setFieldValue("GZCTM", "N"); // Customer tfr multiples
		transaction.setFieldValue("GZNRM", "N"); // Notice to rec multiples
		transaction.setFieldValue("GZAFX", "N"); // Gen MT320/330 w/o auth?
		transaction.setFieldValue("GZDVC", "1"); // Date validation calendar
		transaction.setFieldValue("GZCALL", "N"); // Call deal type?

		transaction.setFieldValue("GZGDR", "N"); // Generate deal reference
		transaction.setFieldValue("GZINM", "N"); // Interest numbers method
		transaction.setFieldValue("GZASVI", "N"); // Ask to save instructions
		transaction.setFieldValue("GZATPP", "N"); // Allow 3rd party payments
		transaction.setFieldValue("GZYFBO", "N"); // Fund BO permitted
		transaction.setFieldValue("GZEXF", "1"); // Spot/user/retail rate
	}

}
