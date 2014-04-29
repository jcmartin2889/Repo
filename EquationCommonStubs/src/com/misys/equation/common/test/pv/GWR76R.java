package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class GWR76R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWR76R.java 11324 2011-06-29 11:21:04Z GOLDSMC1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		// validCCN = "0543123467106";
		validCCN = "*   *     *  USD*              *              CA";
		invalidCCN = "ZZZZXXXXXX001";
	}
}
