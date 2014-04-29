/**
 * Copyright and all other intellectual property rights in this software, in any form, is vested in Misys International Banking
 * Systems Ltd ("Misys") or a related company.
 * 
 * This software may not be copied, amended, compiled, translated, or developed; or sold, leased, hired, rented, or disclosed to any
 * third party without the prior written consent of Misys.
 * 
 * Copyright Misys International Banking Systems Ltd, 1975 and later
 */

package com.misys.equation.common.test.transaction;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.misys.equation.common.test.AbstractTestSuite;

public class _AllCashierTests extends AbstractTestSuite
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: _AllCashierTests.java 4967 2009-09-28 15:54:02Z challip1 $";
	public static Test suite()
	{
		// test suite
		TestSuite suite = new TestSuite("Test for All Cashier functions");

		// Cashier Transactions

		addTests(suite, AddTest_CDT_Cashier.class); // Cashier System Branch Details

		// addTests(suite, ACA.class); // Add CS Cash Deposit
		// addTests(suite, CSH.class); // Cancel CS Cash Deposit
		suite.addTest(ACA_CSH_Suite.suite());

		// addTests(suite, ACW.class); // Add CS Cheque Withdrawal
		// addTests(suite, CCW.class); // Cancel CS Cheque Withdrawal
		suite.addTest(ACW_CCW_Suite.suite());

		// addTests(suite, AAD.class); // Add CS Cheque Deposit
		// addTests(suite, CDS.class); // Cancel CS Cheque Deposit
		suite.addTest(AAD_CDS_Suite.suite());

		// addTests(suite, AAS.class); // Add CS Sundry Withdrawal
		// addTests(suite, CSW.class); // Cancel CS Sundry Withdrawal
		suite.addTest(AAS_CSW_Suite.suite());

		// addTests(suite, ACE.class); // Add CS Exchange Cash
		// addTests(suite, CAE.class); // Cancel CS Exchange Cash
		suite.addTest(ACE_CAE_Suite.suite());

		// addTests(suite, AAB.class); // Add CS Buy Travellers Cheques
		// addTests(suite, CCB.class); // Cancel CS Buy Travellers Cheques
		suite.addTest(AAB_CCB_Suite.suite());

		// addTests(suite, AAT.class); // Add CS Sell Travellers Cheques
		// addTests(suite, CAS.class); // Cancel CS Sell Travellers Cheques
		suite.addTest(AAT_CAS_Suite.suite());

		// addTests(suite, AAO.class); // Add CS Other FX Debit
		// addTests(suite, CAF.class); // Cancel CS Other FX Debit
		suite.addTest(AAO_CAF_Suite.suite());

		// addTests(suite, AAF.class); // Add CS Other FX Debit
		// addTests(suite, CAO.class); // Cancel CS Other FX Debit
		suite.addTest(AAF_CAO_Suite.suite());

		// addTests(suite, AAX.class); // Add CS Other FX Debit
		// addTests(suite, CAX.class); // Cancel CS Other FX Debit
		suite.addTest(AAX_CAX_Suite.suite());

		// addTests(suite, AAW.class); // Add CS Other FX Debit
		// addTests(suite, CAW.class); // Cancel CS Other FX Debit
		suite.addTest(AAW_CAW_Suite.suite());

		// addTests(suite, AAA.class); // Add CS Account Transfer
		// addTests(suite, CTA.class); // Cancel CS Account Transfer
		suite.addTest(AAA_CTA_Suite.suite());

		// addTests(suite, ADR.class); // Add CS draft
		// addTests(suite, CSD.class); // Cancel CS draft
		suite.addTest(ADR_CSD_Suite.suite());

		// * NOTE: The draft number comes from IYPF record created by ADR function
		// * The same record cannot be maintained twice in DRR function.
		// addTests(suite, DRR.class); // Cheque/Draft Reconciliation

		// addTests(suite, ABC.class); // Add CS Balance Cash
		// addTests(suite, CSB.class); // Cancel CS balance cash
		suite.addTest(ABC_CSB_Suite.suite());

		// addTests(suite, ADT_1_AddDraft.class); // Add CS draft for reprint
		// addTests(suite, ADT_2_ReprintDraft.class); // Add CS draft reprint
		// addTests(suite, CRP.class); // Cancel CS draft reprint
		suite.addTest(ADT_CRP_Suite.suite());

		// addTests(suite, A01.class); // Add CS X-Currency Cheque Deposit
		// addTests(suite, A02.class); // Cancel CS X-Currency Cheque Deposit
		suite.addTest(A01_A02_Suite.suite());

		// addTests(suite, CEA_1_AddDraft.class); // Add CS draft for reprint
		// addTests(suite, CEA_2_DraftEncashment.class); // Add CS draft reprint
		// addTests(suite, CEC.class); // Cancel CS draft reprint
		suite.addTest(CEA_CEC_Suite.suite());

		addTests(suite, CancelTest_CDT_Cashier.class); // Cashier System Branch Details

		return suite;
	}
}
