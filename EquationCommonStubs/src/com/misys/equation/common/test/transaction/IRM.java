package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseMaintain;

/**
 * Equation test cases for Maintain Interest Rate Swap
 */
public class IRM extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: IRM.java 4721 2009-09-15 11:14:17Z weddelc1 $";
	String programName = "N43MRR";
	String optionId = "IRM";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
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
	public EquationStandardTransaction getTransaction() throws Exception
	{
		EquationStandardTransaction transaction = getEquationStandardTransaction(programName + optionId);
		transaction.setWorkStationId(WORKSTATIONID);
		return transaction;
	}

	// ------------------------------------------------------------------------ Field setups

	/**
	 * Setup a non-existing key fields
	 */
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDLR", "XXX"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "XXXX"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZDLP", "XXX"); // Deal type (3A)
	}

	/**
	 * Setup an existing key fields
	 */
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDLR", "IRSL342"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "FRRS"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZDLP", "IDD"); // Deal type (3A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZIDLP", "IVD"); // Deal type (3A)
		transaction.setFieldValue("GZTDT", "L"); // Term deal type; L=Loan, D=Deposit (1A)
		transaction.setFieldValue("GZBDT", "T"); // Basic deal type; X=FX, T=Term, M=Not a deal (1A)
		transaction.setFieldValue("GZDIC", "F"); // Deal interest characteristic; V=Var, F=Fix, D=Dis (1A)
		transaction.setFieldValue("GZYPSF", "N"); // Exclude from positions? (1A)
		transaction.setFieldValue("GZAPP", "IR"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
		transaction.setFieldValue("GZSTDT", "D"); // Term deal type; L=Loan, D=Deposit (1A)
		transaction.setFieldValue("GZSDIC", "V"); // Deal interest characteristic; V=Var, F=Fix, D=Dis (1A)
		transaction.setFieldValue("GZSYPS", "N"); // Exclude from positions? (1A)
		transaction.setFieldValue("GZCUS", "DAIICH"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", "LON"); // Customer location (3A)
		transaction.setFieldValue("GZCCY", "USD"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZDLA", "100000000"); // Deal amount (15P,0)
		transaction.setFieldValue("GZSDT", "991231"); // Start date (7S,0)
		transaction.setFieldValue("GZMDT", "1000229"); // Maturity date (7S,0)
		transaction.setFieldValue("GZCTRD", "991130"); // Contract date (7S,0)
		transaction.setFieldValue("GZINPY", "N"); // Y/N indicators governing interest activities? (1A)
		transaction.setFieldValue("GZBKA", "0"); // Brokerage amount (15P,0)
		transaction.setFieldValue("GZSRC", "99"); // Deal sundry ref code (2A)
		transaction.setFieldValue("GZUC1", "999"); // Deal user code 1 (3A)
		transaction.setFieldValue("GZUC2", "000"); // Deal user code 2 (3A)
		transaction.setFieldValue("GZPMR", "0.0000000"); // Margin rate - primary (11P,7)
		transaction.setFieldValue("GZPAC", "11.0000000"); // Actual rate - primary (11P,7)
		transaction.setFieldValue("GZPIDB", "1"); // Int days basis - primary (1A)
		transaction.setFieldValue("GZPNC", "1000228"); // Next interest date - primary (7S,0)
		transaction.setFieldValue("GZPNR", "1000228"); // Next reset date - primary (7S,0)
		transaction.setFieldValue("GZRBRR", "03"); // Base rate code - secondary (2A)
		transaction.setFieldValue("GZRDRR", "02"); // Diff rate code - secondary (2A)
		transaction.setFieldValue("GZRMR", "0.0500000"); // Margin rate - secondary (11P,7)
		transaction.setFieldValue("GZRAC", "0.0000000"); // Actual rate - secondary (11P,7)
		transaction.setFieldValue("GZRFFD", "00"); // Floating fix days secondary (2A)
		transaction.setFieldValue("GZRIDB", "1"); // Int days basis - secondary (1A)
		transaction.setFieldValue("GZRIFQ", "V31"); // Interest freq - secondary (3A)
		transaction.setFieldValue("GZRNC", "991231"); // Next int date - secondary (7S,0)
		transaction.setFieldValue("GZRNRF", "V31"); // Reset frequency - secondary (3A)
		transaction.setFieldValue("GZRNR", "1000107"); // Next reset date - secondary (7S,0
		transaction.setFieldValue("GZABF", "0543"); // Branch - primary (4A)
		transaction.setFieldValue("GZANF", "123457"); // Account basic no - primary (6A)
		transaction.setFieldValue("GZASF", "050"); // Account suffix - primary (3A)
		transaction.setFieldValue("GZCA1", "0"); // Primary charge amount 1 (15P,0)
		transaction.setFieldValue("GZCA2", "0"); // Primary charge amount 2 (15P,0)
		transaction.setFieldValue("GZCA3", "0"); // Primary charge amount 3 (15P,0)
		transaction.setFieldValue("GZABM", "0543"); // Branch - secondary (4A)
		transaction.setFieldValue("GZANM", "123457"); // Account basic no - secondary (6A)
		transaction.setFieldValue("GZASM", "050"); // Account suffix - secondary (3A)
		transaction.setFieldValue("GZCA1A", "0"); // Secondary charge amount 1 (15P,0)
		transaction.setFieldValue("GZCA2A", "0"); // Secondary charge amount 2 (15P,0)
		transaction.setFieldValue("GZCA3A", "0"); // Secondary charge amount 3 (15P,0)
		transaction.setFieldValue("GZYNAA", "N"); // Automatically authorise the deal? (1A)
		transaction.setFieldValue("GZYSSI", "N"); // Deal has standard settlement instructions? (1A)
		transaction.setFieldValue("GZYNPR", "N"); // Suppress pay and receive advices? (1A)
		transaction.setFieldValue("GZMAIN", "Y"); // Can the deal be maintained (1A)
		transaction.setFieldValue("GZBCR1", "0.0000000"); // Brokerage conversion rate 1 (13P,7)
		transaction.setFieldValue("GZBCR2", "0.0000000"); // Brokerage conversion rate 2 (13P,7)
		transaction.setFieldValue("GZING", "IR"); // Instruction group (2A)
		transaction.setFieldValue("GZEXMM", "N"); // Exclude from MM limits (1A)
		transaction.setFieldValue("GZSEXM", "N"); // Exclude from MM limits (1A)
		transaction.setFieldValue("GZLFDT", "991215"); // Next loan fix date (7S,0)
		transaction.setFieldValue("GZDFDT", "991215"); // Next deposit fix date (7S,0)
		transaction.setFieldValue("GZBCT1", "0.000000000"); // Brokerage conversion rate 1 (15P,9)
		transaction.setFieldValue("GZBCT2", "0.000000000"); // Brokerage conversion rate 2 (15P,9)
		transaction.setFieldValue("GZCCYF", "USD"); // Receive settlement currency (3A)
		transaction.setFieldValue("GZCCYM", "USD"); // Pay settlement currency (3A)
		transaction.setFieldValue("GZIIFD", "N"); // Include interest frequency date ? (1A)
		transaction.setFieldValue("GZIBFD", "N"); // Post interest before frequency date? (1A)
		transaction.setFieldValue("GZVAFD", "N"); // Interest value dated before frequency date? (1A)
		transaction.setFieldValue("GZCMR", "N"); // Cover message required? (1A)
		transaction.setFieldValue("GZBKC", "BC"); // Broker/FeeS
	}

}
