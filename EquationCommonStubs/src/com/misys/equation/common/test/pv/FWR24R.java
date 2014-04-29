package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class FWR24R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FWR24R.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	// This is a wrapper for PV FWR20R in Account Mode
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "CD1          1010101AC000000000000002";
		invalidCCN = "CD1          1010101AC000000000000099";
	}
}
