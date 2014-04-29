package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class FWR23R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FWR23R.java 7610 2010-06-01 17:10:41Z MACDONP1 $";

	// This is a wrapper for PV FWR20R in Account Mode
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "CITYCDDY2CL101        1010101C1000000000000001";
		invalidCCN = "CITYCDDY2CL101      PC1000229BR             99";
	}
}
