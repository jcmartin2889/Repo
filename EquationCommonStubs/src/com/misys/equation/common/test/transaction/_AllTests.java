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

public class _AllTests extends AbstractTestSuite
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: _AllTests.java 11567 2011-08-05 08:50:10Z GOLDSMC1 $";
	public static Test suite() throws InterruptedException 
	{
		// test suite
		TestSuite suite = new TestSuite("Test for com.misys.equation.common.test.standard.transaction");
		//Thread.sleep(200000);
		addTests(suite, TRC_Part1.class); // Cancel Repayment Transaction (Principal)   =moved up
		addTests(suite, TRC_Part2.class); // Cancel Repayment Transaction (Interest)    =moved up
		suite.addTest(MCR_Suite.suite()); // Maintain Constant Repayment Loan
		suite.addTest(RED_Suite.suite()); // Returned Cheque Debtor Id    ==moved up
		// Commercial Paper
		//Thread.sleep(200000);
		suite.addTest(_AllCommercialPaperTests.suite());  //==moved up
		
		addTests(suite, CTM.class); // Add/Maintain Collateral Type
		addTests(suite, ARG.class); // Add/Maintain Region Codes
		addTests(suite, DSO.class); // Define System Options
		
		// MCD.class to set Consolidated Statement flag to 'Y' for CSQ
		addTests(suite, MCD.class); // Maintain Customer Basic Details
		addTests(suite, CSQ.class); // Consolidated Statement Request

		addTests(suite, HCI.class); // Add/Maintain/Delete Account Hold

		addTests(suite, M07.class); // Add/Maintain Residence Codes
		addTests(suite, MLN.class); // Add/Maintain Language Numbers
		addTests(suite, MR2.class); // Add/Maintain Responsibility codes
		addTests(suite, MSK.class); // Add/Maintain Charge Codes
		addTests(suite, MTM.class); // Add/Maintain Transfer Method

		addTests(suite, BHD.class); // Branch Header Details
		addTests(suite, DCM.class); // Maintain Default Charges
		addTests(suite, MMT.class); // Add/Maintain Message Type
		addTests(suite, MMF.class); // Add/Maintain Message Field Code
		addTests(suite, CCF.class); // Add/Maintain CLS Currency Details
		addTests(suite, ALR.class); // Add/Maintain Limit Reservations

		addTests(suite, APR.class); // Add/Maintain Posting Rule
		addTests(suite, AHO.class); // Add/Maintain Hold Rule

		addTests(suite, DRT.class); // Add/Maintain Retail Rate Type
		addTests(suite, RRT.class); // Add/Maintain Retail Rates

		addTests(suite, DIF.class); // Define Incoming Message Format
		addTests(suite, DPP.class); // Add/Maintain Payment Purpose
		addTests(suite, APM.class); // Add/Maintain Access Point
		addTests(suite, RTM.class); // Maintain Message Routing Table
		addTests(suite, ABF.class); // Add/Maintain Brokerage Fees
		addTests(suite, MWS.class); // Maintain Withdrawal Statistics
		addTests(suite, MIW.class); // Maintain Int Withdrawal Statistics
		addTests(suite, MIT.class); // Add/Maintain Customer Identity Type
		addTests(suite, CZD.class); // Maintain Clearing Zone Details
		addTests(suite, DTM.class); // Add/Maintain Deal Type Details
		addTests(suite, LTM.class); // Add/Maintain Retail Loan Type
		addTests(suite, ODL.class); // Add/Maint Overdraft Limit Charges
		addTests(suite, ODL_char_format.class); // Add/Maint Overdraft Limit Charges

		addTests(suite, ATA.class); // Add Threshold Utilisation Adj
		addTests(suite, MLB.class); // Ledger Balance Equivalent Amounts

		// addTests(suite, MZP.class); // Add/Maintain Prompter Screen Titles
		addTests(suite, MSG.class); // Maintain Marketing Messages

		// Generic Deals
		addTests(suite, AddTest_MAT_Generic.class); // Maintain Account Type
		addTests(suite, AddTest_MAT_GenericX.class); // Maintain Account Type
		addTests(suite, AddTest_MAT_GenericT.class); // Maintain Account Type
		addTests(suite, GDT.class); // Add/Maintain Generic Deal Types

		addTests(suite, AddTest_GDT_Parent.class); // Add Generic Exchange Deal Type
		addTests(suite, GAP.class); // Add Generic Parent Deal
		addTests(suite, GMP.class); // Maintain Generic Parent Deal
		addTests(suite, GCP.class); // Cancel Generic Parent Deal
		// addTests(suite, CancelTest_GDT_Parent.class); // Add Generic Exchange Deal Type

		addTests(suite, AddTest_GDT_Term.class); // Add Generic Exchange Deal Type
		addTests(suite, GDA.class); // Add Generic Term Deal
		addTests(suite, GDM.class); // Maintain Generic Term Deal
		addTests(suite, MMW_Add.class); // Add Manual Cashflow Maintenance
		addTests(suite, MMW_MaintainCancel.class); // Maintain Manual Cashflow Maintenance
		addTests(suite, GDC.class); // Cancel Generic Term Deal
		// addTests(suite, CancelTest_GDT_Term.class); // Add Generic Exchange Deal Type

		// Interest Profit Stats
		// addTests(suite, AddTest_GDT_Term.class); // Add Generic Exchange Deal Type
		addTests(suite, AddTest_GDA.class); // Add generic deal then use this deal for GPI
		addTests(suite, GPI.class); // AAdd/Maintain Interest Profit Stats
		// addTests(suite, CancelTest_GDC.class); // Add generic deal then use this deal for GPI
		// addTests(suite, CancelTest_GDT_Term.class); // Add Generic Exchange Deal Type

		// Generic Exchange Deal
		addTests(suite, AddTest_GDT_Exchange.class); // Add Generic Exchange Deal Type
		addTests(suite, GXA.class); // Add Generic Exchange Deal
		addTests(suite, GXM.class); // Maintain Generic Exchange Deal
		addTests(suite, GXC.class); // Cancel Generic Exchange Deal

		// Add/Maintain FX Profit Stats

		addTests(suite, AddTest_MCP.class); // Maintain Customer Profit Structure
		addTests(suite, AddTest_GXA.class); // Add generic exchange deal then use this deal for GPX and GPC
		addTests(suite, GPX.class); // Add/Maintain FX Profit Stats
		addTests(suite, GPC.class); // Add Charge Profitability Statistics
		// addTests(suite, CancelTest_GXC.class); // Cancel Generic Exchange Deal

		// addTests(suite, CancelTest_GDT_Exchange.class); // Add Generic Exchange Deal Type

		// Add Grouped not coded!
		// addTests(suite, AddTest_GDT_Term.class); // Add Generic Exchange Deal Type
		// addTests(suite, AddTest_GDT_Term2.class); // Add Generic Exchange Deal Type
		// addTests(suite, GDG.class); // Group Generic Deal Types
		// addTests(suite, CancelTest_GDT_Term2.class); // Add Generic Exchange Deal Type
		// addTests(suite, CancelTest_GDT_Term.class); // Add Generic Exchange Deal Type

		// static data
		addTests(suite, RDT.class); // Reserve Deal Type
		addTests(suite, RRC.class); // Maintain Reason Codes
		addTests(suite, SCA.class); // Add/Maintain Status Code
		addTests(suite, AMT.class); // Add/Maintain Mandate Types
		addTests(suite, DSG.class); // Define System Options Group
		addTests(suite, MDP.class); // Cheque deposit parms maint journal file

		// Accounts
		// TODO: need to work out way to remove account from database on the close
		addTests(suite, OCA.class); // Open Customer Account
		addTests(suite, CLA.class); // Close Customer Account
		addTests(suite, OJA.class); // Open Joint Account
		addTests(suite, DEL_CLA.class); // Add Test for OJA

		// Maintain Field Access
		addTests(suite, CancelTest_CBA.class); // Maintain Account Basic Field Access
		addTests(suite, CBA.class); // Maintain Account Basic Field Access
		addTests(suite, AddTest_CBA.class); // Maintain Account Basic Field Access
		addTests(suite, CancelTest_CIA.class); // Maintain A/c Interest Field Access
		addTests(suite, CIA.class); // Maintain A/c Interest Field Access
		addTests(suite, AddTest_CIA.class); // Maintain A/c Interest Field Access
		addTests(suite, CancelTest_COA.class); // Maintain Account Other Field Access
		addTests(suite, COA.class); // Maintain Account Other Field Access
		addTests(suite, AddTest_COA.class); // Maintain Account Other Field Access

		// Customer Relationships
		// Test ACS and MCS
		suite.addTest(CustRelationSuite.suite());

		// Customer Details
		addTests(suite, MCT.class); // Add/Maintain Customer Type
		addTests(suite, MCO.class); // Maintain Customer Other Details
		addTests(suite, DCO.class); // Define Customer Offsets
		addTests(suite, CFF.class); // Customer Free-Format Maintenance
		addTests(suite, MAO.class); // Maintain Customer A/C Other Details
		addTests(suite, MAB.class); // Maintain Customer A/C Basic Details
		addTests(suite, CMN.class); // Maintain Customer Mnemonic

		addTests(suite, CSU_ANC.class); // Consolidated Customer Stmnt Set Up - Create Customer with consolidated statements
		addTests(suite, CSU_OCA.class); // Consolidated Customer Stmnt Set Up - Create Account for customer with consolidated
		// statements
		addTests(suite, CSU.class); // Consolidated Customer Stmnt Set Up

		addTests(suite, AIB.class); // Add/Maintain Issuing Bank/Branches
		addTests(suite, AGT.class); // Add/Maintain Guarantee Type
		addTests(suite, _16R.class);// Customer and Account Tax Details
		addTests(suite, BCU.class); // Borrow Customer Limits
		addTests(suite, MCP.class); // Maint customer profit structure journal

		// Customer Groups
		addTests(suite, CGD.class); // Customer Group Definition
		addTests(suite, CIG.class); // Maintain Additional Info Groups

		// Addresses
		addTests(suite, CAA.class); // Customer and Account Addresses
		addTests(suite, SAA.class); // SWIFT Customer and Account Addresses (exactly like CAA)

		// Account Conversion
		addTests(suite, MAR.class); // Maintain Account Conversion Request

		// Manual Interest - Accounts
		addTests(suite, MIA.class); // Post Manual Interest - Account

		// Account status
		addTests(suite, AddTest_SCA.class); // Maintain Account Status
		addTests(suite, ASM_1.class); // Maintain Account Status
		addTests(suite, ASM_2.class); // Maintain Account Status
		// addTests(suite, CancelTest_SCA.class); // Maintain Account Status

		// Account Conditions
		addTests(suite, ASC.class); // Customer Account Special Conditions
		addTests(suite, ISC.class); // Internal Account Special Conditions

		// Account Non-Accrual Adjustments
		addTests(suite, MAN.class); // Maintain Account Non-accrual
		addTests(suite, ANA.class); // Add Non-accrual Adjustment

		// Account Holds
		addTests(suite, AAH.class); // Input Basic Account Hold
		addTests(suite, MAH.class); // Maintain/Delete Basic Account Hold

		addTests(suite, AXH.class); // Add Extended Account Holds
		addTests(suite, MXH.class); // Maintain/Delete Extended a/c holds

		// Sundry items
		suite.addTest(SundryItemSuite.suite()); // Add, Maintain, Cancel Sundry Item
		addTests(suite, SSI.class); // Add Special Sundry Item
		suite.addTest(AGI_Suite.suite()); // Add Grouped Sundry Item
		addTests(suite, SFM_Add_Only.class); // Sundry Postings (TI) - Not in the future
		// SFM with future date does not maintain and cancel - posting must be before the next business day
		addTests(suite, SFM_Future_Posting.class); // Sundry Postings (TI) - In the future

		// Inter Account Transfers
		addTests(suite, ITA.class); // Add Inter Account Transfer
		addTests(suite, ITM.class); // Maint/Cancel Inter Account Transfer

		// Loans Status
		addTests(suite, LST.class); // Add/Maintain Loan Status Codes
		addTests(suite, MSL.class); // Maintain Loan Status
		addTests(suite, CCL.class); // Maintain Customer Loan Status

		// Loan Non-accrual Status
		addTests(suite, MNS.class); // Maintain Loan Non-accrual Status

		// Drawdown
		addTests(suite, ADW.class); // Add Drawdown
		addTests(suite, MDW.class); // Maintain Drawdown
		addTests(suite, CDW.class); // Cancel Drawdown
	

		addTests(suite, ADS.class); // Add Drawdown with Settlement details
		addTests(suite, CancelTest_CDW.class); // Cancel Drawdown


		
		// Loan Repayments
		addTests(suite, ALP.class); // Add Loan Repayments
		addTests(suite, MLP.class); // Maintain Loan Repayment
		addTests(suite, CLP.class); // Cancel Loan Repayment
		addTests(suite, AddTest_DQP_LRP.class); // Define Queue Priority
		addTests(suite, LRP.class); // Assign loan repay priorities journal file
		// addTests(suite, CancelTest_DQP_LRP.class); // Define Queue Priority

		// Penalty Interest
		addTests(suite, APA.class); // Adjust Penalty Interest

		// Repayment Transactions
		addTests(suite, RTN.class); // Add Repayment Transaction
		addTests(suite, MTN.class); // Maintain Repayment Transaction
		//Thread.sleep(200000);
		addTests(suite, CTN_Part1.class); // Cancel Repayment Transaction (Principal)
		//Thread.sleep(200000);
		addTests(suite, CTN_Part2.class); // Cancel Repayment Transaction (Interest)

		addTests(suite, TRA.class); // Add Repayment Transaction
		addTests(suite, TRM.class); // Maintain Repayment Transaction
		//addTests(suite, TRC_Part1.class); // Cancel Repayment Transaction (Principal)
		//addTests(suite, TRC_Part2.class); // Cancel Repayment Transaction (Interest)

		// Cheques
		addTests(suite, CBR.class); // Cheque Book Request
		addTests(suite, CBI.class); // Cheque Book Issue
		addTests(suite, CBS.class); // Cheque Book Status Review

		addTests(suite, CBR.class); // Cheque Book Request
		addTests(suite, CBD.class); // Delete Cheque Book Request

		// Cheque Book Requests
		suite.addTest(CBR_MCB_Suite.suite()); // Add and Maintain chequebook requests

		addTests(suite, ACI.class); // Add/Maintain Cheque Deposit - Items
		addTests(suite, A2D.class); // Add Cheque Deposit and Items
		addTests(suite, A2P.class); // Add Post Dated Chq Deposit & Items
		addTests(suite, MDC.class);// Maintain Cheque Deposit - Cust CR

		addTests(suite, OCD.class); // Add Outport Cheque Clearing Date

		addTests(suite, CBT.class); // Cheque Book Type Maintenance

		addTests(suite, ACF.class); // Add Guaranteed Fate Cheque
		addTests(suite, DCF.class); // Delete Guaranteed Fate Cheque
		addTests(suite, GCC.class); // Add Certified Customer Cheque
		addTests(suite, DGC.class); // Delete Certified Customer Cheque
		// addTests(suite, ACD.class); // Add Cheque Deposit - Cust CR
		addTests(suite, AID.class); // Add Cheque Deposit and Items
		addTests(suite, DCD.class); // Delete Cheque Deposit
		addTests(suite, GCT.class); // Certified Cheque Charge/Tax Details

		addTests(suite, AIC.class); // Add/Delete Incorrect Inward Cheque

		addTests(suite, CRI.class); // Clear Referred Inward Cheques

		// Collateral
		//addTests(suite, CLL.class); // Add/Maintain Collateral Locations

		// External Event
		addTests(suite, AEV.class); // Add External Event
		addTests(suite, CEV.class); // Cancel External Event

		// Balance Orders
		addTests(suite, ABO.class); // Add Balance Order
		addTests(suite, MBO.class); // Maintain Balance Order
		addTests(suite, CBO.class); // Cancel Balance Order

		// Stop Orders
		addTests(suite, AST.class); // Add Stop Order
		addTests(suite, MST.class); // Maintain Stop Order
		addTests(suite, CST.class); // Cancel Stop Order

		// Standing Orders
		// Note: Reference numbers in the following classes need to be changed after run
		// as CSO flags Standing Order as Cancelled. Reference number cannot be reused.
		// Note: in order to test authorisation of standing orders (RSO) the Major Processing
		// Characteristic ASO must be set to Yes in the test unit.
		addTests(suite, ASO_1_Settlement.class); // Add Settlement Instructions for Standing Order
		addTests(suite, ASO_2_StandingOrder.class); // Add Standing Order
		addTests(suite, MSO.class); // Maintain Standing Order
		addTests(suite, RSO.class); // Authorise Standing Order
		addTests(suite, CSO.class); // Cancel Standing Order

		// deals
		addTests(suite, ADD.class); // Additional Deal Narrative

		// Money Markets
		addTests(suite, AMM.class); // Add Money Market Deal
		addTests(suite, MMM.class); // Maintain Money Market Deal
		addTests(suite, CMM.class); // Cancel Money Market Deal

		addTests(suite, AMS.class); // Add Money Market Deal with Settlements
		// Addtitional tests for AMS
		addTests(suite, AddTest_MMM.class); // Maintain Money Market Deal
		addTests(suite, AddTest_CMM.class); // Cancel Money Market Deal	

		// Manual Interest - Deals
		addTests(suite, MID.class); // Post Manual Interest - Deal

		// Commitments
		addTests(suite, NCM.class); // New Commitment
		addTests(suite, MCM.class); // Maintain Commitment
		addTests(suite, MCA.class); // Maintain Commitment Amount
		addTests(suite, CCM.class); // Cancel Commitment

		// Settlements
		addTests(suite, DCI.class); // Standing Settlement Instructions
		addTests(suite, DCI_AddMaintain.class); // Standing Settlement Instructions
		addTests(suite, DCA.class); // Auto-Authorise Standard Settlements

		addTests(suite, AMM_JS.class); // Add Money Market Deal
		addTests(suite, DSI.class); // Maintain Deal Settlement Details
		addTests(suite, DAX.class); // Authorise Deals                            
		addTests(suite, CMM_JS.class); // ACancel Money Market Deal               

		// Branch Calendars
		addTests(suite, LBC.class); // Load Branch Calendars
		addTests(suite, RDB.class); // Reload Branch Calendars (Delete)

		// TODO: Not repeatable, because CDN does not completely delete the diary note
		// Diary Note
		addTests(suite, ADN.class); // Add Diary Note
		addTests(suite, MDN.class); // Maintain Diary Note
		addTests(suite, CDN.class); // Cancel Diary Note

		// Webfacing
		addTests(suite, AddTest_MDF.class); // Needed for AWW
		addTests(suite, AWW.class); // Assign WebFacing Widgets
		addTests(suite, CancelTest_MDF.class); // Needed for AWW

		addTests(suite, MDF.class); // Add/Maintain WebFacing Display File
		addTests(suite, MWC.class); // Add/Maintain WebFacing codes
		addTests(suite, DFW.class); // Define WebFacing Widgets

		// Reports
		addTests(suite, MRR.class); // Maintain Report Requests

		addTests(suite, URD.class); // Maintain Report Requests
		// addTests(suite, CancelTest_MRR.class); // Maintain Report Requests

		addTests(suite, COU.class); // Maintain User Class
		addTests(suite, MAC.class); // Maintain Availability Code - not coded
		addTests(suite, AddTest_MAC.class); // Maintain Availability Code - not coded
		addTests(suite, MUA.class); // Maintain User Additional Info - will work if MAC works!
		addTests(suite, GDL.class); // User Group Definition
		addTests(suite, MUL.class); // Maintain User Limits

		addTests(suite, NAB.class); // Maintain Customer Acc./Deal Branch

		addTests(suite, DPS.class); // Add/Maintain Posting Set

		addTests(suite, MDE.class); // Maintain Default/Internal Accounts

		addTests(suite, MLC_part3.class); // Translate Code Descriptions
		addTests(suite, MLC_part1.class); // Translate Code Descriptions
		addTests(suite, MLC_part2.class); // Translate Code Descriptions
		addTests(suite, MLC_part3.class); // Translate Code Descriptions

		addTests(suite, MLS.class); // Translate System Dictionary

		addTests(suite, AddTest_MMT.class); // Add/Maintain Message Type
		addTests(suite, MAP.class); // Maintain Mapping Table
		// addTests(suite, CancelTest_MMT.class); // Add/Maintain Message Type

		// Maintain Exchange Rate
		addTests(suite, MER.class); // Maintain Exchange Rate
		addTests(suite, MER_part1.class); // Maintain Exchange Rate
		addTests(suite, MER_part2.class); // Maintain Exchange Rate
		addTests(suite, JB_part1.class); // Maintain Exchange Rate
		addTests(suite, JB_part2.class); // Maintain Exchange Rate
		addTests(suite, JC_part1.class); // Maintain Exchange Rate
		addTests(suite, JC_part2.class); // Maintain Exchange Rate
		addTests(suite, JD_part1.class); // Maintain Exchange Rate
		addTests(suite, JD_part2.class); // Maintain Exchange Rate

		addTests(suite, MCE_spot.class); // Maintain Specified Currency
		/*
		 * MCE currently does not work for future rates Issue Tracker = 278
		 * 
		 * addTests(suite, MCE_part1.class); // Maintain Specified Currency addTests(suite, MCE_part2.class); // Maintain Specified
		 * Currency
		 */

		addTests(suite, DCH.class); // Add/Maintain Charge Set
		addTests(suite, AddTest_DCH.class); // Add/Maintain Charge Set
		addTests(suite, ACR.class); // Add/Maintain Charge Rule
		// addTests(suite, CancelTest_DCH.class); // Add/Maintain Charge Set
		// addTests(suite, DHS.class); // Add/Maintain Posting Set
		// addTests(suite, DRP.class); // Add/Maintain Report Set
		// addTests(suite, DAS.class); // Add/Maintain Accounting Set

		addTests(suite, CII.class); // Maintain Additional Info Items

		addTests(suite, DTX.class); // Add/Maintain FX Deal Type Details

		// TODO AFC in correct location?
		addTests(suite, AFC.class); // Add FX Compensation Deal
		
		//Thread.sleep(200000);

		addTests(suite, RDU_JS.class); // Add Retail Deposit
		addTests(suite, MHD.class); // Maintain Historical Deal Details
		addTests(suite, CMM_cancel_for_MHD.class); // Cancel the Retail Deposit used for MHD

		addTests(suite, DCT.class); // Add/Maintain Currency Cutoff Times

		// Account Type
		addTests(suite, MAT.class); // Maintain Account Types

		// CPY removed - all cancels have their own options!
		// addTests(suite, CPY.class); // Cancel Repayment

		addTests(suite, MBC.class); // Add/Maintain/Delete Broker Codes

		// Interest Instructions
		addTests(suite, AAI.class); // Add Interest Instructions
		// Notes on AII (Authorise Interest Instructions):
		// 1. Authorisation for Int Instructions should be 'Y' for this to work (KMENU0 opt5 AFII).
		// 2. User id used when AAI.java is run should not be the same as AII.java
		// addTests(suite, AII.class); // Authorise Interest Instructions
		addTests(suite, MNN.class); // Maintain Interest Instructions
		addTests(suite, DII.class); // Cancel Interest Instructions
		addTests(suite, AAI.class); // Add Interest Instructions
		addTests(suite, MaintainTest_ASC.class); // Add Interest Instructions

		// RIN - needs End of Day
		// TODO Find a way to set AC1OUT to 'Y' in order for RIN to work.
		// addTests(suite, RIN.class); // Repair Interest Instructions

		// Borrow Country Limits
		addTests(suite, BCN.class); // Add Borrow Country Limits

		// Define Combined Account Group
		addTests(suite, DCB_Part1.class); // Add Define Combined Account Group
		addTests(suite, DCB_Part2.class); // Maintain/Cancel Define Combined Account Group

		// Market Swap Deal
		addTests(suite, FSA.class); // Add Market Swap Deal
		addTests(suite, FSC.class); // Cancel Market Swap Deal
		addTests(suite, FSI.class); // Add Market Swap With Settlement Det
		addTests(suite, FSC_2.class); // Cancel Market Swap Deal (from FSI)

		addTests(suite, MNI.class); // Maintain Notice Account Interest
		suite.addTest(MCJ_Suite.suite()); // Customer Type Interface Information
		suite.addTest(MDB_Suite.suite()); // Deal Interface Information

		addTests(suite, MRC.class); // Add/Maintain Relationship Codes
		addTests(suite, MTR.class); // Maintain Transaction Codes
		addTests(suite, MBA.class); // Maintain Branch Address
		addTests(suite, SPI.class); // Standing Payment Instruction
		addTests(suite, AddTest_SPI.class); // Standing Payment Instruction
		addTests(suite, SPA.class); // Authorise Standing Payment Instr
		// addTests(suite, CancelTest_SPI.class); // Standing Payment Instruction

		// Add/Maintain Authorisation Limits
		addTests(suite, DAL_Header_AddMaintain.class); // Header
		addTests(suite, DAL_List.class); // Add/Maintain Authorisation Limits
		addTests(suite, DAL_Header_Cancel.class); // Delete Header

		// OIA can only be run once - need to change account number
		addTests(suite, OIA.class); // Open Internal Account
		addTests(suite, CTM.class); // Add/Maintain Collateral Type
		addTests(suite, CLT.class); // Add/Maintain Collateral Items
		addTests(suite, MAI.class); // Maintain ATM Interface Information
		addTests(suite, AGC.class); // Add/Maintain Grouping Code
		// addTests(suite, AMP.class); // Add/Maintain Counting Period - Obsolete!
		// addTests(suite, MIN_Part1.class); // Add/Maintain Derivative Deal Types
		addTests(suite, CAN.class); // Add/Maintain Collateral Assignments
		addTests(suite, CTU.class); // Add/Maintain Collateral Assignments
		addTests(suite, CRV.class); // Add/Maintain Collateral Reservation
		addTests(suite, ANO.class); // Customer Number Characteristics
		addTests(suite, MCU.class); // Customer Number Ranges

		// TODO: MNR can only be run once - need to change currency?
		addTests(suite, MNR.class); // Maintain Notice Account Rules
		addTests(suite, MIO.class); // Maintain Internal A/C Other Details
		addTests(suite, MIB.class); // Maintain Internal A/C Basic Details
		addTests(suite, DDA_All.class); // Direct Debit Returned Advices

		// addTests(suite, AMC.class); // Add/Maintain Yield Curve - LIST function

		// PCHC Transaction Function
		addTests(suite, AA1.class); // Add PCHC Transaction Function
		addTests(suite, AA0.class); // Maintain PCHC Transaction Function

		// Forward Rate Agreement
		addTests(suite, FRA.class); // Add Forward Rate Agreement
		addTests(suite, FRS.class); // Add FRA with Settlement Details
		addTests(suite, FRM.class); // Maintain Forward Rate Agreement
		addTests(suite, FRC.class); // Cancel Forward Rate Agreement
		// addTests(suite, CancelTest_FRC.class); // Cancel Forward Rate Agreement

		// ATM Account Information
		addTests(suite, MIN_Part1.class); // Add ATM Account Information
		addTests(suite, MIN.class); // Maintain/Cancel ATM Account Information

		// Withdrawal Penalties
		addTests(suite, DRM.class);// Maintain Early Withdrawal Penalties

		// Principal Increase/Decrease
		addTests(suite, AddTest_AMM.class); // Add Money Market Deal
		addTests(suite, MLI.class); // Maintain Loan Interest Rate
		addTests(suite, API.class); // Add Principal Increase/Decrease
		addTests(suite, MPI.class); // Maintain Principal Increase/Decrease
		addTests(suite, CPI.class); // Cancel Principal Increase/Decrease
		//
		addTests(suite, BMP.class);// Block Maturity Payment
		// addTests(suite, CancelTest_CMM.class); // Cancel Money Market Deal

		// Cashier System Branch
		addTests(suite, CDT.class); // Cashier System Branch Details

		// Stock
		addTests(suite, AddTest_CDT.class); // Cashier System Branch Details
		addTests(suite, SDD.class); // Maintain Other Stock Items
		addTests(suite, AddTest_SDD.class); // Maintain Other Stock Items
		addTests(suite, SDI.class); // Maintain Stock Issuer Details
		suite.addTest(SDS_Suite.suite()); // Maintain Stock Management Levels
		// addTests(suite, CancelTest_SDD.class); // Maintain Other Stock Items
		// addTests(suite, CancelTest_CDT.class); // Cashier System Branch Details

		// Issue on ANC still pending
		// addTests(suite, AddTest_ANC.class); // Add New Customer
		// addTests(suite, CDD.class); // Customer Deletion

		// Loans and Principal Increase
		addTests(suite, RLA.class); // Add Retail Loan
		addTests(suite, RPI.class); // Add Principal Increase
		addTests(suite, RMI.class); // Maintain Principal Increase
		addTests(suite, RCI.class); // Cancel Principal Increase
		addTests(suite, LRQ.class); // Loan Statment Requests
		addTests(suite, RLM.class); // Maintain Retail Loan
		addTests(suite, RLC.class); // Cancel Retail Loan
		addTests(suite, RLQ.class); // Add Quick Retail Loan
		// addTests(suite, CancelTest_RLC.class); // Cancel Retail Loan
		suite.addTest(CHA_Suite.suite()); // Add Direct Debit Claim History (including RLA and ADO)
		// Add/Maintain Authorisation Tiers
		addTests(suite, RAT_Header.class); // Add/Maintain Authorisation Tiers
		addTests(suite, RAT_List.class); // Add/Maintain Authorisation Tiers
		addTests(suite, RAT_Header_Del.class); // Return ccy to USD to allow test to be repeated

		addTests(suite, BOE.class); // Reporting Group Definition

		addTests(suite, AddTest_BOE.class); // Reporting Group Definition
		addTests(suite, BGL.class); // Reporting Group Linkages
		// addTests(suite, CancelTest_BOE.class); // Reporting Group Definition

		addTests(suite, CGC.class); // Add/Maintain Channel Code

		addTests(suite, AddTest_CGC.class); // Add/Maintain Channel Code
		addTests(suite, ACM.class); // Add/Maintain Charge Component
		// addTests(suite, CancelTest_CGC.class); // Add/Maintain Channel Code

		// K477
		addTests(suite, MCG.class); // Add/Maintain Card Limit Groups
		addTests(suite, AddTest_MCG.class); // Add/Maintain Card Limit Groups
		addTests(suite, BCT.class); // Add/Maintain Card Type
		//
		// Need to think about how to delete Card Details entered in ZZ9
		//
		addTests(suite, AddTest_BCT.class); // Add/Maintain Card Type
		addTests(suite, ZZ9.class); // Add/Maintain Card Details
		addTests(suite, MaintainTest_BCT.class); // Add/Maintain Card Type
		addTests(suite, ZZ9_AutoGenerateCardNumber.class); // Add/Maintain Card Details - Auto Generated Card Number
		//
		// should now unblock then cancel card type and limit group
		// BBC: Card number and sequence number should be set up by ZZ9.
		addTests(suite, BBC.class); // Block/Unblock Bank Card
		// addTests(suite, CancelTest_BCT.class); // Add/Maintain Card Type
		// addTests(suite, CancelTest_MCG.class); // Add/Maintain Card Limit Groups

		// K505
		addTests(suite, PTA.class); // Add/Maintain Customer Payment Type
		addTests(suite, TID.class); // Add/Maintain Template ID

		addTests(suite, POA.class); // Add Customer Payment Order
		addTests(suite, POM.class); // Maintain Customer Payment Order
		addTests(suite, POD.class); // Cancel Customer Payment Order

		// Retail Deposit
		addTests(suite, RDU.class); // Add Retail Deposit
		addTests(suite, RDW.class); // Withdraw Retail Deposit

		addTests(suite, RDS.class); // Add Retail Deposit
		// addTests(suite, CancelTest_CMM3.class); // Cancel the RDS above
		//Thread.sleep(200000);
		addTests(suite, AddTest_RDU.class); // Add Retail Deposit
		//Thread.sleep(200000);
		addTests(suite, RAF.class); // Maintain Deposit Instructions
		// addTests(suite, CancelTest_CMM4.class); // Cancel the RDU above

		// Loan Pay-off
		addTests(suite, RLA.class); // Add Retail Loan
		addTests(suite, RLP.class); // Add Retail Pay-off
		addTests(suite, LPM.class); // Maintain Loan Pay-off
		// addTests(suite, RLC.class); // Cancel Retail Loan

		// Schedule Generation
		suite.addTest(MGS_ScheduleGeneration_Suite.suite());

		addTests(suite, MNV.class); // Add/Maintain NV Type

		// Maintain FX Gap Periods
		addTests(suite, GPM_Part1.class); // Maintain FX gap periods
		addTests(suite, GPM_Part2.class); // Maintain FX gap periods

		// Commercial Paper
		//Thread.sleep(200000);
		//suite.addTest(_AllCommercialPaperTests.suite());

		// Interest Rate Swap
		addTests(suite, IRA.class);// Add Interest Rate Swap
		addTests(suite, IRM.class);// Maintain Interest Rate Swap
		addTests(suite, DIR.class);// Cancel Interest Rate Swap

		// Interest Rate Swap with Settlements
		addTests(suite, IRS.class);// Add Interest Rate Swap with Settlements
		addTests(suite, AddTest_IRM.class);// Maintain Interest Rate Swap
		addTests(suite, AddTest_DIR.class);// Cancel Interest Rate Swap

		// TODO OPT, OPS in correct location?
		addTests(suite, OPT.class);// Option Take-up
		addTests(suite, OPS.class);// Option Take-up with Settlements

		addTests(suite, QRM.class);// Add/Maintain/Delete ZP Parameters

		addTests(suite, MDG.class);// Add/Maintain Check Digit Codes

		addTests(suite, MOA.class);// Maintain OPICS Accounting Type

		addTests(suite, MOC.class);// Maintain Customer Classification

		addTests(suite, ADO.class);// Add/Maintain DD Origination

		addTests(suite, MGC.class);// Add/Maintain Organ-Schl�ssel Code

		addTests(suite, MLD.class);// Add/Maintain Clearing Days

		addTests(suite, AEA.class);// Add/Maintain Account Numbers

		addTests(suite, GVR.class);// Add/Maint Generic Validation Rules

		addTests(suite, MBI.class);// Maintain Bonus Increments

		// Branch Total Input Function
		addTests(suite, AA2_part1.class);// Add
		addTests(suite, AA2_part2.class);// Maintain/Delete

		addTests(suite, MRM.class);// MRL Record Maintenance

		addTests(suite, SAH.class);// Customer Salary History

		addTests(suite, MFU.class);// Maintain Funding Increments

		addTests(suite, MIR.class);// Post Manual Interest - Loan

		addTests(suite, PDL.class);// Past Due Letter DocId

		addTests(suite, ALO.class);// Add/Maintain Almonde Product

		addTests(suite, BMI.class);// BMS Initialisation Details

		addTests(suite, LBC.class);// Load Branch Calendar
		addTests(suite, MBH.class);// Maintain Branch Holid/Business days
		addTests(suite, RDB.class);// Reload Branch Holid/Business days

		addTests(suite, AMX.class);// Add/Maintain Expense Statistics

		addTests(suite, TCP.class); // Maintain Transaction Cost Parameter

		addTests(suite, DMC_account.class);// DD mandate confirmation - account key
		addTests(suite, DMC_branch.class);// DD mandate confirmation - branch key
		addTests(suite, DMC_customer.class);// DD mandate confirmation - customer key
		addTests(suite, DMC_printall.class);// DD mandate confirmation - print all

		addTests(suite, MCK.class); // Customer Interface Information
		addTests(suite, MGE.class); // Group Interface Information

		// Watch List Checking (K473C)
		//suite.addTest(_AllWLCTests.suite()); // Watch List Checking (WLC)              === MOVED DOWN      

		// Profitability Analysis (K342)
		addTests(suite, PRX.class);// Add/Maintain Pool Exchange Rates
		// End of Profitability Analysis (K342) tests

		addTests(suite, ADC.class); // Maintain Deal Charge Component

		addTests(suite, SOM.class); // Maintain Warning Overrides
		addTests(suite, MaintainTest_SOM.class); // Maintain Warning Overrides

		addTests(suite, MZP.class); // Add/Maintain Prompter Screen Titles

		// Notice Withdrawal
		suite.addTest(_AllNoticeWithdrawl.suite()); // Add Notice Withdrawal

		// Maintain Account Settlement Details
		// - Add and Cancel only to be used in conjunction with other associated transactions
		suite.addTest(_All_MSD.suite()); // Maintain Account Settlement Details

		addTests(suite, GUR.class); // Add/Maintain User Group
		addTests(suite, AddTest_GUR.class); // Add/Maintain User Group
		addTests(suite, RRA.class); // EESF Access Restriction
		addTests(suite, AddTest_RRA.class); // EESF Access Restriction
		addTests(suite, REF.class); // EESF Rule Exceptions
		// addTests(suite, CancelTest_RRA.class); // EESF Access Restriction
		addTests(suite, GUB.class); // Add/Maintain Group Branch Linkage
		addTests(suite, GUL.class); // Add/Maintain Group User Linkage
		addTests(suite, GAO.class); // Add/Maintain Group Auth Options
		// addTests(suite, CancelTest_GUR.class); // Add/Maintain User Group

		addTests(suite, SRQ.class); // Statement Requests

		// Almonde Field Mapping
		addTests(suite, AddTest_CII.class); // Maintain Additional Info Items
		addTests(suite, ALN.class); // Maintain Almonde Field Mapping
		addTests(suite, MaintainTest_ALN.class); // Maintain Almonde Field Mapping
		// addTests(suite, CancelTest_CII.class); // Maintain Additional Info Items

		// An add is necessay since SL1 is mainly a "maintain function".
		// AddTest_SL1 should be done only once.
		addTests(suite, AddTest_SL1.class); // Salary Loans Static Data
		suite.addTest(SalaryLoansStaticData_Suite.suite()); // SL1, SL2 and SL3

		suite.addTest(_All_DNI.suite()); // Add/Maintain/Cancel Nostros

		addTests(suite, RHA.class);// Add Repayment Holiday - single loan
		addTests(suite, LHA.class);// Add Repayment Holiday - all loans

		addTests(suite, GLM.class); // Group Limits Maintenance

		addTests(suite, NDR_FullyAdd.class); // Zero Days Rate Fix Process

		addTests(suite, IGP.class); // Add/Maintain Instruction Group

		addTests(suite, MOI.class); // Maintain OPICS Information
		addTests(suite, MaintainTest_MOI.class); // Maintain OPICS Information

		addTests(suite, DAF.class); // Define Address Format

		addTests(suite, BGV.class); // Reporting Variables

		// Cashier Transactions
		suite.addTest(_AllCashierTests.suite());

		// Interface Code Relations group
		addTests(suite, MGB.class); // Add/Maintain Interface Groups fully functional
		addTests(suite, MGB_add1.class); // Add group TESTGROUP1 for MGT & MGD
		addTests(suite, MGB_add2.class); // Add group TESTGROUP2 for MGT
		addTests(suite, MGD.class); // Add/Maintain Interface Codes fully functional
		addTests(suite, MGD_add1.class); // Add code GROUP1ONE
		addTests(suite, MGD_add2.class); // Add code CRG1
		addTests(suite, MGD_add3.class); // Add code CRG3
		addTests(suite, MGT.class); // Add/Maintain Interface Relations fully functional
		// addTests(suite, MGD_cancel1.class); // Delete code GROUP1ONE
		// addTests(suite, MGD_cancel2.class); // Delete code CRG1
		// addTests(suite, MGD_cancel3.class); // Delete code CRG3
		// addTests(suite, MGB_cancel1.class); // Delete group TESTGROUP1
		// addTests(suite, MGB_cancel2.class); // Delete group TESTGROUP2
		addTests(suite, MGD_Add4.class); // Add code INTERFCODE for GSKLASUKLA
		addTests(suite, MGD_Add5.class); // Add code INTERFCODE for GSART
		addTests(suite, MGD_Add6.class); // Add code INTERFCODE for GSART
		addTests(suite, MAE.class); // Account Type Interface Information
		addTests(suite, MAJ.class); // Account Type Interface Information
		// addTests(suite, MGD_Cancel4.class); // Delete code INTERFCODE
		// addTests(suite, MGD_Cancel5.class); // Delete code INTERFCODE
		// addTests(suite, MGD_Cancel6.class); // Delete code INTERFCODE

		addTests(suite, MTL.class); // Maintain Title Codes

		addTests(suite, ETN.class); // Add/Maint Extended Tran Narrative
		suite.addTest(_All_AIG.suite()); // Assign Information Groups

		addTests(suite, LPA.class); // Add Loan Provision
		addTests(suite, LPR.class); // Recover Loan Provision
		addTests(suite, ALW.class); // Add Loan Write-off

		suite.addTest(AUD_Suite.suite()); // Add/Maintain Returned Unpaid DD

		//suite.addTest(CHA_Suite.suite()); // Add Direct Debit Claim History (including RLA and ADO)     moved up

		// addTests(suite, CancelTest_MAT_Generic.class); // Maintain Account Type
		// addTests(suite, CancelTest_MAT_GenericX.class); // Maintain Account Type
		// addTests(suite, CancelTest_MAT_GenericT.class); // Maintain Account Type

		addTests(suite, DTD.class); // Add/Maintain Derivative Deal Types

		addTests(suite, AFX.class); // Add FX Deal
		addTests(suite, MFX.class); // Maintain FX Deal
		addTests(suite, CFX.class); // Cancel FX Deal
		addTests(suite, AFS.class); // Add FX Deal with Settlement Details
		addTests(suite, CFX_part2.class); // Cancel FX Deal (for AFS above)

		addTests(suite, AddTest_TID.class); // Add/Maintain Template ID
		addTests(suite, AddTest_PTA.class); // Add/Maintain Customer Payment Type
		addTests(suite, AEC.class); // Add/Maint EPD Customer Instruction
		// addTests(suite, CancelTest_PTA.class); // Add/Maintain Customer Payment Type
		// addTests(suite, CancelTest_TID.class); // Add/Maintain Template ID

		addTests(suite, AddTest_OKF.class); // Maintain Debtor
		addTests(suite, OLA.class); // Maintain Blacklisted Debtor
		// addTests(suite, CancelTest_OKF.class); // Maintain Debtor

		addTests(suite, AIS.class); // Add Internal Swap Deal
		addTests(suite, CIS.class); // Cancel Internal Swap Deal

		addTests(suite, DQP.class); // Define Queue Priority

		addTests(suite, AddTest_DQP.class); // Define Queue Priority
		addTests(suite, AQP.class); // Assign Queue Priority
		// addTests(suite, CancelTest_DQP.class); // Define Queue Priority

		suite.addTest(EQP_Suite.suite()); // Edit Queue Priority (including ITA to provide queued item)

		addTests(suite, STA.class); // Add/Maintain Stock Item Types
		addTests(suite, AMI.class); // Add/Maintain Method of ID Codes
		addTests(suite, ATS.class); // Add/maintain a/c type restraints
		addTests(suite, DLR.class); // A/M/D Deal Type Restraints
		addTests(suite, APT.class); // Account type pair restraints

		// Cannot do repeated testing because for AUP ADD function to be successful,
		// a new UII number has to be entered. Use the WMENU1 AMU function to create
		// a new UII number to be used in AUP ADD function.
		// addTests(suite, AUP.class); // Add/Maintain UII Password
		// addTests(suite, CPU.class); // Check UII Password

		addTests(suite, DDN_part1.class); // Add/Maintain Default Nostros ADD
		addTests(suite, DDN_part2.class); // Add/Maintain Default Nostros MAINTAIN / CANCEL

		//addTests(suite, CCO.class); // Allow AEI Recording to Continue                          =Commented

		// Cheques
		// Cheque Deposit - ARS test commented - Authorisation validation fails.
		suite.addTest(Cheque_Deposit_Suite.suite()); // Cheque Deposit
		// Cheque Deposit Post Dated - CPD test commented - Cancellation cannot occur on same day as entry.
		suite.addTest(Cheque_Deposit_Post_Dated_Suite.suite()); // Cheque Deposit Post Dated

		// Users and Authorities
		addTests(suite, AFU.class); // User Authorisation for a Unit
		addTests(suite, DUS.class); // User Specific Information
		suite.addTest(DUO_1_Option_Suite.suite()); // Add 1 Menu Option to User
		suite.addTest(DUO_Clone_Suite.suite()); // Clone a User's Menu Options - This only runs once. No Cancel/Delete of all user
		// options.

		// Customer Functions
		addTests(suite, ANC.class); // Add New Customer
		addTests(suite, ANX.class); // Add / Maintain Customer Extended Details (German Features) - No delete so only works once
		addTests(suite, CDD.class); // Delete Customer

		// Test Stubs only for documentation. Menu option bundles other options
		addTests(suite, AGW.class); // Add Grouped Inward Clearing Cheques
		addTests(suite, AIP.class); // Add Post Dated Chq Deposit & Items
		addTests(suite, AIW.class); // Add Inward Clearing Cheque
		addTests(suite, APD.class); // Add Post Dated Cheque Deposit and Items
		addTests(suite, CDP.class); // Cancel Discounted Post Dated Cheque
		addTests(suite, DAU.class); // Authorise Deals
		addTests(suite, DPD.class); // Discount Post Dated Cheques
		addTests(suite, GDI.class); // Generic Deal Entry
		addTests(suite, CP2.class); // Cancel Post Dated Cheques

		// Account Interest
		addTests(suite, MIC.class); // Maintain Customer Account Interest


		// Miscellaneous
		// addTests(suite, FGD.class); // Fix Generated Deals - Cannot create data do fix

		addTests(suite, ABL.class); // Add/Maintain Bonus Rules

		addTests(suite, AddTest_DTM.class); // Add/Maintain Deal Type Details
		addTests(suite, SRU.class); // Add/Maintain Savings Rules
		// addTests(suite, CancelTest_DTM.class); // Add/Maintain Deal Type Details

		// addTests(suite, AddTest_DTM.class); // Add/Maintain Deal Type Details
		addTests(suite, AddTest_ABL.class); // Add/Maintain Bonus Rules
		//Thread.sleep(200000);
		addTests(suite, AddTest_SRU.class); // Add/Maintain Savings Rules
		addTests(suite, AddTest_RDU_2.class); // Add Retail Deposit
		
		addTests(suite, PMB.class); // Post Manual Bonus - Deal
		// addTests(suite, CancelTest_RDU_2.class); // Add Retail Deposit
		// addTests(suite, CancelTest_SRU.class); // Add/Maintain Savings Rules
		// addTests(suite, CancelTest_ABL.class); // Add/Maintain Bonus Rules
		// addTests(suite, CancelTest_DTM.class); // Add/Maintain Deal Type Details

		// addTests(suite, AddTest_DTM.class); // Add/Maintain Deal Type Details
		// addTests(suite, AddTest_ABL.class); // Add/Maintain Bonus Rules
		// addTests(suite, AddTest_SRU.class); // Add/Maintain Savings Rules
		addTests(suite, AddTest_RDU_MBT.class);// Add Retail Deposit
		//Thread.sleep(200000);
		suite.addTest(MBT_Suite.suite()); // Maintain Deal Bonus Rate
		//Thread.sleep(200000);
		
		// addTests(suite, CancelTest_CMM_MBT.class);// Cancel Money Market Deal
		// addTests(suite, CancelTest_SRU.class); // Add/Maintain Savings Rules
		// addTests(suite, CancelTest_ABL.class); // Add/Maintain Bonus Rules
		// addTests(suite, CancelTest_DTM.class); // Add/Maintain Deal Type Details

		addTests(suite, RLF.class); // Release Local Funds

		addTests(suite, IEA.class); // Add Inter Account Transfer, equivalent amounts
		addTests(suite, IEM.class); // Maintain Inter Account Transfer, equivalent amounts
		addTests(suite, IGA.class); // Add Generic Inter Account Transfer
		addTests(suite, ITM_for_IGA.class); // Maintain Inter Account Transfer, generic transfer

		suite.addTest(QCO_Suite.suite()); // Add Court Order
		//Thread.sleep(200000);
		addTests(suite, BSC_Part1.class); // Add/Maintain Bank Sort Codes
		addTests(suite, BSC_Part2.class); // Add/Maintain Bank Sort Codes

		addTests(suite, BCZ.class); // Maintain Issuing Bank Clearing Zone
		addTests(suite, LAI.class); // Account Local Clearing Information

		// Direct Debits
		// Test AMD, PDD, RDD and INR
		suite.addTest(DirectDebitSuite.suite());

		addTests(suite, CLM.class); // Customer Limits Maintenance

		addTests(suite, MCC.class); // Maintain Cheque Clearing Date
		//Thread.sleep(200000);
		//suite.addTest(MCR_Suite.suite()); // Maintain Constant Repayment Loan      ====moved up

		suite.addTest(ARR_Suite.suite()); // Report Rules
		suite.addTest(AAC_Suite.suite()); // Accounting Rules

		addTests(suite, DDS.class); // Define Customer Document Schema

		addTests(suite, Add_ADW_1.class); // add a drawdown to use with NRE / CRE
		addTests(suite, NRE.class); // New Repayment Schedule
		addTests(suite, CRE.class); // Cancel Repayment Schedule
		// addTests(suite, Cancel_ADW_1.class); // cancel drawdown to use with NRE / CRE

		addTests(suite, RET.class); // Returned Cheques

		addTests(suite, MaintainTest_MAT.class); // Maintain Account Types
		addTests(suite, AddTest_OIA.class); // Open Internal Account
		addTests(suite, MaintainTest_ISC.class); // Internal Account Special Conditions
		addTests(suite, MIX.class); // Post Manual Interest - Internal A/C

		addTests(suite, JAR_ACC_AddMaintain.class); // Add/Maint Joint A/c Relationships
		addTests(suite, JAN.class); // Maintain Joint A/c narrative
		addTests(suite, JAR_ACC_Cancel.class); // Add/Maint Joint A/c Relationships

		addTests(suite, LMT_TestRepeatingData.class); // Add/Maintain Limits

		addTests(suite, CCD.class); // Currency description (Print) Maint.

		// ERF/SPR are not repeatable - there is no function to 'un-reconcile' postings
		addTests(suite, ERF.class); // Manual Posting Reconciliation
		addTests(suite, ERF_2.class); // matching posting to ERF above
		addTests(suite, SPR.class); // Supervisor Posting Reconciliation
		addTests(suite, SPR_2.class); // matching posting to SPR above

		addTests(suite, CPT.class); // Add/Maintain Clean Payment Template
		addTests(suite, MCN.class); // Maintain Customer FX Netting Rules

		addTests(suite, CDM.class); // Add/Maint cor credit limit

		// K493 Modified Disbursement Scheme Enhancement
		// REMOVED - needs full K493 set-up - only used by 1 bank!
		// Note: ACQ and ADA functions are repeatable if record is not yet authorized.
		// F22=Cancel will work only if the ADA & ACIC/Cheque record is not yet authorized.
		// NCA reference must already exist and authorized. (To create, use WMENU1 NCA)
		// NCA java test not yet assigned/created. (NCA is phase 2)
		// addTests(suite, NCA.class); // Add/Maintain Notice of Cash Alloc
		// addTests(suite, ACQ.class); // Add/Maintain ACIC Details
		// addTests(suite, ADA.class); // Add/Maintain ADA Details
		// addTests(suite, NTA.class); // Transfer of Cash Allocations

		//suite.addTest(RED_Suite.suite()); // Returned Cheque Debtor Id
		addTests(suite, MSW.class); // SWIFT Variables Maintenance

		addTests(suite, CDF.class); // Customer For Charges
		addTests(suite, AddTest_CDF.class); // Customer For Charges
		addTests(suite, CPA.class); // Accounts For Charges

		// ACT needs End of Day!
		// addTests(suite, ACT.class); // Action Standing Order

		// CNA and CNC need End of Day!
		// After adding a drawdown, an EOD is needed before testing the following.
		// Unit QQE was used to test these.
		// addTests(suite, CNA.class); // Cancel Overdue DD Repayment
		// addTests(suite, CNC.class); // Cancel Overdue DD Repayment

		addTests(suite, BCD.class); // Branch Communication Control Detail
		addTests(suite, MaintainTest_BCD.class); // Branch Communication Control Detail

		//
		// Group level reference and Deal reference are hard coded in ANS_Part2.
		// They are automatically generated by ANS_Part1.
		// suite.addTest(ANS_Suite.suite()); // Authorise Net Settlements Summary

		// This test stub as been created, but setting up test data is difficult.
		// Only run this when necessary.
		// addTests(suite, CHQ.class); // Authorise returned cheque

		addTests(suite, MWL.class); // Add/Maintain Cash Withdrawal Limit
		addTests(suite, FRU.class); // Add/Maintain Funding Rules
		addTests(suite, INC.class); // Maintain Invalid Characters
		addTests(suite, MAU.class); // Add/Maintain A/c Closure User Codes
		addTests(suite, MDI.class); // Maintain Deal Interest Rate
		addTests(suite, PRC.class); // Add/Maint Postal Region Codes/Rates
		addTests(suite, RCZ.class); // Region to Region Clearing Zones
		addTests(suite, RNW.class); // Review Notice Withdrawal
		addTests(suite, RUW.class); // Add/Maintain Withdrawal Rules
		addTests(suite, TDA.class); // Add/Maintain Tax Details
		// EWT removed as cheque added by ZZ8 always has a "new" id
		// addTests(suite, EWT.class); // Add Sub Case Status

		// Added phase 2 list functions:
		addTests(suite, DLC.class); // Define Linked Currencies
		addTests(suite, FXO_FullyAdd.class); // Maintain FX Stepped Option Details
		addTests(suite, FXO_MaintainCancel.class); // Maintain FX Stepped Option Details
		addTests(suite, KSV.class); // Maintain System Option function
		addTests(suite, KPC.class); // Maintain System Option function
		addTests(suite, KCS.class); // Maintain System Option function
		addTests(suite, LPB_AddMaintain.class); // Maintain Loan Penalty Base Rates
		addTests(suite, LPB_Cancel.class); // Maintain Loan Penalty Base Rates
		addTests(suite, LPD_AddMaintain.class); // Maintain Loan Penalty Diff Rates
		addTests(suite, LPD_Cancel.class); // Maintain Loan Penalty Diff Rates

		addTests(suite, GCM.class); // Add/Maintain Generic Deal Cashflows

		addTests(suite, AddTest_AGT.class); // Add/Maintain Guarantee Type
		addTests(suite, AGD_account.class); // Add/Maintain Guarantee Details
		addTests(suite, AddTest_AGD_deal.class); // Add/Maintain Guarantee Details
		addTests(suite, AddTest_AGD_account.class); // Add/Maintain Guarantee Details

		addTests(suite, ABR.class); // Add/Maintain Base Rate References

		addTests(suite, PMC.class); // Post Manual Charges                         
		addTests(suite, MMC_Account_Maintain.class); // Maintain Charge
		addTests(suite, MMC_Account_Cancel.class); // Maintain Charge

		addTests(suite, AddTest_ACM.class); // Add/Maintain Charge Component
		addTests(suite, ACJ.class); // Add/Maintain Charge Structure
		addTests(suite, RLA_for_ACG.class); // Add Retail Loan
		addTests(suite, ACG.class); // Add Event Charge

		addTests(suite, CPF.class); // Report/Process Requests
		addTests(suite, DRR.class); // Draft Reconciliation

		// Clean Payments
		suite.addTest(_AllCleanPayments.suite()); // Clean Payments

		// Currency Movement
		suite.addTest(CurrencyMovementSuite.suite());

		// K492 Equation Local Clearing Interface
		// Removed as does not always run successfuly - German banks only!
		// addTests(suite, CIW.class); // Inward Local Clearing

		addTests(suite, ZZ8.class); // Maintain Group Settlement Details
		// addTests(suite, ZZ7.class); // Authorise Net Settlements - Details

		// K545 Equation MBI Interface
		addTests(suite, BIR.class); // Define MBI Report Extract
		
		//Thread.sleep(200000);
		
		
		//Thread.sleep(200000);
	
		addTests(suite, MII.class); // Maintain Internal Account Interest (Like MIC)

		
		// Collateral
		addTests(suite, CLL.class); // Add/Maintain Collateral Locations    = moved down
		
		
		// K539 Islamic Profit Distribution
		// addTests(suite, V61.class); // Add/Maintain IPD Pool Definition
		// addTests(suite, V65.class); // IPD Product Definition
		
		//Thread.sleep(200000);
		
		// Watch List Checking (K473C)
		suite.addTest(_AllWLCTests.suite()); // Watch List Checking (WLC)    
		
		//Thread.sleep(200000);
		return suite;
	}
}
