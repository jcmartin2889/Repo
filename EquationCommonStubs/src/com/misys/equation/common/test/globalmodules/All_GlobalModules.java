package com.misys.equation.common.test.globalmodules;

import junit.framework.Test;
import junit.framework.TestSuite;

public class All_GlobalModules
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: All_GlobalModules.java 11511 2011-07-26 12:25:21Z MACDONP1 $";
	public static Test suite()
	{
		// Create Test Suite
		TestSuite suite = new TestSuite("Test for com.misys.equation.common.test.standard.transaction");

		suite.addTestSuite(SC501RW.class); // Retrieve default interest characteristics (for account type/currency)
		suite.addTestSuite(SC899RW.class); // Process account security for currency
		suite.addTestSuite(SDR01RW.class); // Account number check digit service
		suite.addTestSuite(SSC03RW.class); // Determine interest P&L category
		suite.addTestSuite(SSC06RW.class); // Enquiry security check
		suite.addTestSuite(SSC10RW.class); // Retrieve account balance details
		suite.addTestSuite(SSC99RW.class); // Process account security for accounts
		suite.addTestSuite(SDS01RW.class); // Retrieve edited cheque serial number
		suite.addTestSuite(SOB01RW1.class); // Retrieve deal type parameter details - basic
		suite.addTestSuite(SOB01RW2.class); // Retrieve deal type parameter details - FX
		suite.addTestSuite(SOB01RW3.class); // Retrieve deal type parameter details - derivative (FRA/IRS)
		suite.addTestSuite(SOB01RW4.class); // Retrieve deal type parameter details - term
		suite.addTestSuite(SOB01RW5.class); // Retrieve deal type parameter details - retail loan
		suite.addTestSuite(SD401RW.class); // Retrieve rate information
		suite.addTestSuite(SD402RW.class); // Retrieve historical rate information
		suite.addTestSuite(SGF01RW.class); // Customer number check digit service
		suite.addTestSuite(SPS10RW.class); // Retrieve SWIFT address
		suite.addTestSuite(SS502RW.class); // Account APR calculation service
		suite.addTestSuite(SWD01RW.class); // Test Inter-branch User Authority
		suite.addTestSuite(SWD06RW.class); // Test A Branch For User Limits
		suite.addTestSuite(UTM84RW.class); // Retrieve code description
		suite.addTestSuite(SN301RW.class); // Exchange rate conversion service
		suite.addTestSuite(UTR71R.class); // Validate user limits for branch
		suite.addTestSuite(UTM19RW.class); // Edit database date in screen format
		suite.addTestSuite(UTM56RW.class); // Edit amount in abbreviated format
		suite.addTestSuite(SGV02RW.class); // Retrieve settlement instructions
		suite.addTestSuite(SHV01RW.class); // Retrieve branch address
		suite.addTestSuite(UTM87RW.class); // Calculate interest
		suite.addTestSuite(UTH22RW.class); // Edit amount for display
		suite.addTestSuite(UTM57RW.class); // Edit database rate for display
		suite.addTestSuite(UTM58RW.class); // Edit database exchange rate for display
		suite.addTestSuite(UTM85RW.class); // Edit amount in billions format
		suite.addTestSuite(SSV01RW.class); // Address retrieval service

		// These global modules have parameter lists longer than 256
		// SSA21R - Post Amount (ITP)
		// SSA22R - Maintain Posting (ITP)
		// SSA23R - Cancel Posting (ITP)

		// Superceded by SGV02R
		// SGV01R - Retrieve default settlement instructions

		return suite;
	}
}
